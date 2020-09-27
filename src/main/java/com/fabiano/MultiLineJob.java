package com.fabiano;

import com.fabiano.batch.CustomerFieldSetMapper;
import com.fabiano.batch.TransactionFieldSetMapper;
import com.fabiano.entity.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableBatchProcessing
@SpringBootApplication
public class MultiLineJob {
//
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public FlatFileItemReader newReader() {
		return new FlatFileItemReaderBuilder<Customer>()
				.name("customerItemReader")
				.lineMapper(lineTokenizer())
				.resource(new ClassPathResource("customers.dat"))
				.build();
	}

	@Bean
	public PatternMatchingCompositeLineMapper lineTokenizer() {
		Map<String, DelimitedLineTokenizer> lineTokenizers =
				new HashMap<>(2);

		lineTokenizers.put("CUST*", customerLineTokenizer());
		lineTokenizers.put("TRANS*", transactionLineTokenizer());

		Map<String, FieldSetMapper> fieldSetMappers =
				new HashMap<>(2);

//		Can use BeanWrapperFieldSetMapper object instead of new class FieldSetMapper
//		BeanWrapperFieldSetMapper<Customer> customerFieldSetMapper =
//				new BeanWrapperFieldSetMapper<>();
//		customerFieldSetMapper.setTargetType(Customer.class);

		fieldSetMappers.put("CUST*", new CustomerFieldSetMapper());
		fieldSetMappers.put("TRANS*", new TransactionFieldSetMapper());

		PatternMatchingCompositeLineMapper lineMappers =
				new PatternMatchingCompositeLineMapper();

		lineMappers.setTokenizers(lineTokenizers);
		lineMappers.setFieldSetMappers(fieldSetMappers);

		return lineMappers;
	}

	@Bean
	public DelimitedLineTokenizer transactionLineTokenizer() {
		DelimitedLineTokenizer lineTokenizer =
				new DelimitedLineTokenizer();

		lineTokenizer.setNames("prefix",
				"accountNumber",
				"transactionDate",
				"amount");

		return lineTokenizer;
	}

	@Bean
	public DelimitedLineTokenizer customerLineTokenizer() {
		DelimitedLineTokenizer lineTokenizer =
				new DelimitedLineTokenizer();

		lineTokenizer.setNames("prefix",
				"firstName",
				"middleInitial",
				"lastName",
				"street",
				"city",
				"state",
				"zipCode");

		return lineTokenizer;
	}

//	@Bean
//	public ItemWriter itemWriter() {
//		return (items) -> items.forEach(System.out::println);
//	}

	@Bean
	public FlatFileItemWriter<String> newWriter() {
		return new FlatFileItemWriterBuilder<String>()
				.name("greetingItemWriter")
//				.resource(new FileSystemResource(
//						"src/test/customer-transactions.txt"))
				.resource(new FileSystemResource("result.dat"))
				.lineAggregator(new PassThroughLineAggregator<>()).build();
	}

	@Bean
	public Step copyFileStep() {
		return this.stepBuilderFactory.get("copyFileStep")
				.<Customer, Customer>chunk(10)
				.reader(newReader())
				.writer(newWriter())
				.build();
	}

	@Bean
	public Job job() {
		return this.jobBuilderFactory.get("job")
				.start(copyFileStep())
				.build();
	}


	public static void main(String[] args) {
		List<String> realArgs = Collections.singletonList("customerFile=customer.dat");

		SpringApplication.run(MultiLineJob.class, realArgs.toArray(new String[1]));
	}

}
