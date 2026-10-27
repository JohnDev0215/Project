package com.amazon.client.metrics.thirdparty;

import com.amazon.client.metrics.thirdparty.internal.BasicMetricEvent;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ConcurrentMetricEvent implements MetricEvent {
    private final MetricEvent mDelegate;
    private final Object mCounterLock = new Object();
    private final Object mTimerLock = new Object();
    private final Object mDVLock = new Object();

    public ConcurrentMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        this.mDelegate = new BasicMetricEvent(str, str2, metricEventType, z);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getProgram() {
        return this.mDelegate.getProgram();
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getSource() {
        return this.mDelegate.getSource();
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addCounter(String str, double d) {
        synchronized (this.mCounterLock) {
            this.mDelegate.addCounter(str, d);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void incrementCounter(String str, double d) {
        synchronized (this.mCounterLock) {
            this.mDelegate.incrementCounter(str, d);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addTimer(String str, double d) {
        synchronized (this.mTimerLock) {
            this.mDelegate.addTimer(str, d);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void startTimer(String str) {
        synchronized (this.mTimerLock) {
            this.mDelegate.startTimer(str);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void stopTimer(String str) {
        synchronized (this.mTimerLock) {
            this.mDelegate.stopTimer(str);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addString(String str, String str2) {
        synchronized (this.mDVLock) {
            this.mDelegate.addString(str, str2);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void clear() {
        synchronized (this.mCounterLock) {
            synchronized (this.mTimerLock) {
                synchronized (this.mDVLock) {
                    this.mDelegate.clear();
                }
            }
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public List<DataPoint> getAsDataPoints() {
        List<DataPoint> asDataPoints;
        synchronized (this.mCounterLock) {
            synchronized (this.mTimerLock) {
                synchronized (this.mDVLock) {
                    asDataPoints = this.mDelegate.getAsDataPoints();
                }
            }
        }
        return asDataPoints;
    }

    public String toString() {
        String string;
        synchronized (this.mCounterLock) {
            synchronized (this.mTimerLock) {
                synchronized (this.mDVLock) {
                    string = this.mDelegate.toString();
                }
            }
        }
        return string;
    }

    public List<DataPoint> getAsDataPointsAndClear() {
        List<DataPoint> asDataPoints;
        synchronized (this.mCounterLock) {
            synchronized (this.mTimerLock) {
                synchronized (this.mDVLock) {
                    asDataPoints = this.mDelegate.getAsDataPoints();
                    this.mDelegate.clear();
                }
            }
        }
        return asDataPoints;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getNonAnonymousSessionId() {
        return this.mDelegate.getNonAnonymousSessionId();
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getNonAnonymousCustomerId() {
        return this.mDelegate.getNonAnonymousCustomerId();
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void setNonAnonymousSessionId(String str) {
        synchronized (this.mDVLock) {
            this.mDelegate.setNonAnonymousSessionId(str);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void setNonAnonymousCustomerId(String str) {
        synchronized (this.mDVLock) {
            this.mDelegate.setNonAnonymousCustomerId(str);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void setAnonymous(boolean z) {
        synchronized (this.mDVLock) {
            this.mDelegate.setAnonymous(z);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public boolean getAnonymous() {
        return this.mDelegate.getAnonymous();
    }
}
