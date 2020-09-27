
# Exemplo de leitura de arquivo com diferentes referências no Spring Batch

> Arquivo está no formato '.dat' , porém é o mesmo que fazer em '.txt'. <br>
> Presente em main/java/resource/customer.dat

### Leitura de arquivo conforme abaixo:
````
TRANS,1165965,2011-01-22 00:13:29,51.43
CUST,Ann,V,Gates,9247 Infinite Loop Drive,Hollywood,NE,37612
TRANS,8116369,2011-01-21 20:40:52,-14.83
TRANS,8116369,2011-01-21 15:50:17,-45.45
TRANS,8116369,2011-01-21 16:52:46,-74.6
CUST,Erica,I,Jobs,8875 Farnam Street,Aurora,IL,36314
TRANS,8116369,2011-01-22 13:51:05,48.55
TRANS,8116369,2011-01-21 16:51:59,98.53
CUST,Warren,Q,Darrow,8272 4th Street,New York,IL,76091
````

### Saída:
```
Transaction{accountNumber='1165965', transactionDate=Sat Jan 22 00:13:29 BRST 2011, amount=51.43}
Customer{firstName='Ann', middleInitial ='V' , lastName='Gates' , street='9247 Infinite Loop Drive' , city='Hollywood' , state='NE' , zipCode='37612}
Transaction{accountNumber='8116369', transactionDate=Fri Jan 21 20:40:52 BRST 2011, amount=-14.83}
Transaction{accountNumber='8116369', transactionDate=Fri Jan 21 15:50:17 BRST 2011, amount=-45.45}
Transaction{accountNumber='8116369', transactionDate=Fri Jan 21 16:52:46 BRST 2011, amount=-74.6}
Customer{firstName='Erica', middleInitial ='I' , lastName='Jobs' , street='8875 Farnam Street' , city='Aurora' , state='IL' , zipCode='36314}
Transaction{accountNumber='8116369', transactionDate=Sat Jan 22 13:51:05 BRST 2011, amount=48.55}
Transaction{accountNumber='8116369', transactionDate=Fri Jan 21 16:51:59 BRST 2011, amount=98.53}
Customer{firstName='Warren', middleInitial ='Q' , lastName='Darrow' , street='8272 4th Street' , city='New York' , state='IL' , zipCode='76091}
```

Referências:
- Leitura do livro "The Definitive Guide to Spring Batch", Michael T. Minella (Apress, 2019)
- Repositório: https://github.com/Apress/def-guide-spring-batch/tree/master/Chapter07/src/main/java/com/example/Chapter07
- https://spring.io/guides/gs/batch-processing/
- Clone inicial: https://github.com/spring-guides/gs-batch-processing.git

<br>
Porto Alegre, RS - Brasil<br>
Setembro, 2020
