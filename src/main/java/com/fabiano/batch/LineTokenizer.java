package com.fabiano.batch;


import org.springframework.batch.item.file.transform.FieldSet;

public interface LineTokenizer {
    FieldSet tokenize(String line);
}