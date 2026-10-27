package com.amazon.mp3.task;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public class RandomizedRecurrenceStrategy implements RecurrenceStrategy {
    protected String mTAG = getClass().getSimpleName();
    private long mRecurranceDelayMinInSeconds = 68400;
    private long mRecurranceDelayMaxInSeconds = 100800;
    private TimeUnit mRecurranceDelayTimeUnit = TimeUnit.SECONDS;
    private long mMaxRecurrance = -1;
    private Random mRandom = new Random();
}
