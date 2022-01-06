package com.cecilireid.springchallenges;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public CateringJobRepository repository;

    @Bean
    @StepScope
    public FlatFileItemReader<CateringJob> reader() {
        FlatFileItemReader<CateringJob> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("upload.csv"));
        reader.setLinesToSkip(1);

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("id","customerName","phoneNumber","email","menu","noOfGuests","status");

        DefaultLineMapper<CateringJob> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new CateringJobMapper());
        reader.setLineMapper(lineMapper);
        return reader;
    }

    @Bean
    public RepositoryItemWriter<CateringJob> writer() {
        RepositoryItemWriter<CateringJob> writer = new RepositoryItemWriter<>();
        writer.setRepository(repository);
        return writer;
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step")
                .<CateringJob, CateringJob>chunk(10)
                .reader(reader())
                .writer(writer())
                .build();
    }

    @Bean
    public Job uploadCateringJob() {
        return jobBuilderFactory.get("uploadCateringJob")
                .start(step())
                .build();
    }
}
