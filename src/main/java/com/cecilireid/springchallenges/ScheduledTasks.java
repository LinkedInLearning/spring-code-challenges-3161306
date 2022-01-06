package com.cecilireid.springchallenges;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTasks {
    @Autowired
    CateringJobRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Scheduled(cron = "*/10 * * * * *")
    public void reportOrderStats() {
        List<CateringJob> jobs = repository.findAll();
        logger.info("We have {} jobs", jobs.size());
    }
}
