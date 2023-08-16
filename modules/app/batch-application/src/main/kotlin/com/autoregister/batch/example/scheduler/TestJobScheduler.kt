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
    @Qualifier("testJob") val testJob: Job
) {
    @Scheduled(cron = "0/5 * * * * ?")
    fun runJob() {
        val param = JobParametersBuilder()
            .addString("JobId", System.currentTimeMillis().toString())
            .toJobParameters()
        jobLauncher.run(testJob, param)
    }
}