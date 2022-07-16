package com.example.building.common.model;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.extern.log4j.Log4j2;

/**
 * Model Log Time class  said time a method's execution indicated.
 */
@Log4j2
public class LogTime {
    private static final String START_LOG_TIME = "Start log time parent method: ";
    private static final String ELAPSED_TIME_SUB_FUNCTION = "Elapsed Time sub method -> ";
    private static final String ELAPSED_TIME_PARENT_FUNCTION = "Elapsed Time parent method -> ";

    private long beginStepTime;
    private long beginFunctionTime;
    private String methodName;

    public LogTime(String methodName) {
        this.methodName = methodName;
    }

    /**
     * Start log time parent method.
     */
    public void start() {
        beginFunctionTime = System.currentTimeMillis();
        log.debug("{} + {}", START_LOG_TIME, methodName);
    }

    /**
     * Start log time sub method.
     */
    public void startStep() {
        beginStepTime = System.currentTimeMillis();
    }

    /**
     * Stop log time sub method and show log info.
     */
    public void stopStep(String functionName) {
        log.debug("{} - {}: {} = {}", ELAPSED_TIME_SUB_FUNCTION, functionName, getElapsedTimeToMillis(beginStepTime), getElapsedTimeToMinutes(beginStepTime));
    }

    /**
     * Stop log time parent method and show log info.
     */
    public void stop() {
        log.debug("{} - {}: {} = {}", ELAPSED_TIME_PARENT_FUNCTION, methodName, getElapsedTimeToMillis(beginFunctionTime), getElapsedTimeToMinutes(beginFunctionTime));
    }

    /**
     * Get elapsed time of method by milliseconds.
     *
     * @param beginTime the begin time of start method
     * @return the elapsed time by milliseconds
     */
    private String getElapsedTimeToMillis(long beginTime) {
        return String.format("%d milliseconds",
                System.currentTimeMillis() - beginTime);
    }

    /**
     * Get elapsed time of method and convert to minutes, seconds.
     *
     * @param beginTime the begin time of start method
     * @return the elapsed time by minutes, seconds
     */
    private String getElapsedTimeToMinutes(long beginTime) {
        long result = System.currentTimeMillis() - beginTime;
        return String.format("%d minutes, %d seconds ",
                TimeUnit.MILLISECONDS.toMinutes(result),
                TimeUnit.MILLISECONDS.toSeconds(result) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(result))
        );
    }

}
