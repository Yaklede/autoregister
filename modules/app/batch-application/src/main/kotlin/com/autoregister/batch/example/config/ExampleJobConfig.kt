package com.autoregister.batch.example.config

import com.autoregister.batch.example.tasklet.ExampleTasklet
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class ExampleJobConfig: DefaultBatchConfiguration() {

    @Bean
    fun testJob(
        jobRepository: JobRepository,
        @Qualifier("testTasklet") testTasklet: Step
    ): Job {
        return JobBuilder("testJob", jobRepository)
            .start(testTasklet)
            .build()
    }

    @Bean("testTasklet")
    fun testTasklet(
        jobRepository: JobRepository,
        @Qualifier("exampleTasklet") exampleTasklet: ExampleTasklet,
        transactionManager: PlatformTransactionManager
    ): Step {
        return StepBuilder("testTasklet", jobRepository)
            .tasklet(exampleTasklet, transactionManager)
            .build()
    }
}