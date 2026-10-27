package com.amazon.client.metrics.thirdparty;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class NullMetricEvent implements MetricEvent {
    private final MetricEventType mMetricEventType;
    private final String mProgram;
    private final String mSource;

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addCounter(String str, double d) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addString(String str, String str2) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addTimer(String str, double d) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void clear() {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public boolean getAnonymous() {
        return false;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getNonAnonymousCustomerId() {
        return null;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getNonAnonymousSessionId() {
        return null;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void incrementCounter(String str, double d) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void setAnonymous(boolean z) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void setNonAnonymousCustomerId(String str) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void setNonAnonymousSessionId(String str) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void startTimer(String str) {
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void stopTimer(String str) {
    }

    public NullMetricEvent(String str, String str2, MetricEventType metricEventType) {
        this.mProgram = str;
        this.mSource = str2;
        this.mMetricEventType = metricEventType;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getProgram() {
        return this.mProgram;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getSource() {
        return this.mSource;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public List<DataPoint> getAsDataPoints() {
        return new ArrayList(0);
    }
}
