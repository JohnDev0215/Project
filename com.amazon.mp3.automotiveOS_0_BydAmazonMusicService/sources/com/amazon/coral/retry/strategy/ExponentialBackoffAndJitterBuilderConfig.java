package com.amazon.coral.retry.strategy;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public final class ExponentialBackoffAndJitterBuilderConfig {
    private double initialIntervalMillis = 50.0d;
    private double geometricRatio = 2.0d;
    private double randomizationFactor = Math.nextAfter(1.0d, 0.0d);
    private int maxAttempts = 3;
    private long maxWait = Long.MAX_VALUE;
    private Collection<Class<? extends Throwable>> recoverableThrowables = new LinkedList();
    private Collection<Class<? extends Throwable>> recoverableCauseThrowables = new LinkedList();
    private Collection<Class<? extends Throwable>> unrecoverableThrowables = new LinkedList();

    public double getInitialIntervalMillis() {
        return this.initialIntervalMillis;
    }

    public void setInitialIntervalMillis(double d) {
        this.initialIntervalMillis = d;
    }

    public double getGeometricRatio() {
        return this.geometricRatio;
    }

    public void setGeometricRatio(double d) {
        this.geometricRatio = d;
    }

    public double getRandomizationFactor() {
        return this.randomizationFactor;
    }

    public void setRandomizationFactor(double d) {
        this.randomizationFactor = d;
    }

    public int getMaxAttempts() {
        return this.maxAttempts;
    }

    public void setMaxAttempts(int i) {
        this.maxAttempts = i;
    }

    public long getMaxWait() {
        return this.maxWait;
    }

    public void addRecoverableThrowable(Class<? extends Throwable> cls) {
        this.recoverableThrowables.add(cls);
    }

    public Collection<Class<? extends Throwable>> getRecoverableThrowables() {
        return Collections.unmodifiableCollection(this.recoverableThrowables);
    }

    public Collection<Class<? extends Throwable>> getRecoverableCauseThrowables() {
        return Collections.unmodifiableCollection(this.recoverableCauseThrowables);
    }

    public void addUnrecoverableThrowable(Class<? extends Throwable> cls) {
        this.unrecoverableThrowables.add(cls);
    }

    public Collection<Class<? extends Throwable>> getUnrecoverableThrowables() {
        return Collections.unmodifiableCollection(this.unrecoverableThrowables);
    }
}
