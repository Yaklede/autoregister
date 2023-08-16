package com.autoregister.batch.example.scheduler

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat

@Component
class TestJobScheduler(
    private val jobLauncher: JobLauncher,
    @Qualifier("testJob") testJob: Job
) {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")

    @Scheduled(fixedRate = 1000L)
    fun runJob(testJob: Job) {
        val param = JobParametersBuilder()
            .addString("JobId", System.currentTimeMillis().toString())
            .toJobParameters()

        jobLauncher.run(testJob, param)
    }
}