package com.amazon.music.find.metrics;

import kotlin.Metadata;

/* JADX INFO: compiled from: PerformanceData.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b$\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003¢\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003Jw\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u0003HÆ\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020-HÖ\u0001R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0010¨\u0006."}, d2 = {"Lcom/amazon/music/find/metrics/PerformanceData;", "", "requestTimestamp", "", "responseTimestamp", "processingTimestamp", "renderingTimestamp", "loadCompleteTimestamp", "responseLatency", "remoteApiLatency", "responseProcessingLatency", "conversionLatency", "renderingLatency", "operationLatency", "(JJJJJJJJJJJ)V", "getConversionLatency", "()J", "getLoadCompleteTimestamp", "getOperationLatency", "getProcessingTimestamp", "getRemoteApiLatency", "getRenderingLatency", "getRenderingTimestamp", "getRequestTimestamp", "getResponseLatency", "getResponseProcessingLatency", "getResponseTimestamp", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class PerformanceData {
    private final long conversionLatency;
    private final long loadCompleteTimestamp;
    private final long operationLatency;
    private final long processingTimestamp;
    private final long remoteApiLatency;
    private final long renderingLatency;
    private final long renderingTimestamp;
    private final long requestTimestamp;
    private final long responseLatency;
    private final long responseProcessingLatency;
    private final long responseTimestamp;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getRequestTimestamp() {
        return this.requestTimestamp;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final long getRenderingLatency() {
        return this.renderingLatency;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final long getOperationLatency() {
        return this.operationLatency;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getResponseTimestamp() {
        return this.responseTimestamp;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getProcessingTimestamp() {
        return this.processingTimestamp;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getRenderingTimestamp() {
        return this.renderingTimestamp;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getLoadCompleteTimestamp() {
        return this.loadCompleteTimestamp;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getResponseLatency() {
        return this.responseLatency;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getRemoteApiLatency() {
        return this.remoteApiLatency;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getResponseProcessingLatency() {
        return this.responseProcessingLatency;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final long getConversionLatency() {
        return this.conversionLatency;
    }

    public final PerformanceData copy(long requestTimestamp, long responseTimestamp, long processingTimestamp, long renderingTimestamp, long loadCompleteTimestamp, long responseLatency, long remoteApiLatency, long responseProcessingLatency, long conversionLatency, long renderingLatency, long operationLatency) {
        return new PerformanceData(requestTimestamp, responseTimestamp, processingTimestamp, renderingTimestamp, loadCompleteTimestamp, responseLatency, remoteApiLatency, responseProcessingLatency, conversionLatency, renderingLatency, operationLatency);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PerformanceData)) {
            return false;
        }
        PerformanceData performanceData = (PerformanceData) other;
        return this.requestTimestamp == performanceData.requestTimestamp && this.responseTimestamp == performanceData.responseTimestamp && this.processingTimestamp == performanceData.processingTimestamp && this.renderingTimestamp == performanceData.renderingTimestamp && this.loadCompleteTimestamp == performanceData.loadCompleteTimestamp && this.responseLatency == performanceData.responseLatency && this.remoteApiLatency == performanceData.remoteApiLatency && this.responseProcessingLatency == performanceData.responseProcessingLatency && this.conversionLatency == performanceData.conversionLatency && this.renderingLatency == performanceData.renderingLatency && this.operationLatency == performanceData.operationLatency;
    }

    public int hashCode() {
        return (((((((((((((((((((Long.hashCode(this.requestTimestamp) * 31) + Long.hashCode(this.responseTimestamp)) * 31) + Long.hashCode(this.processingTimestamp)) * 31) + Long.hashCode(this.renderingTimestamp)) * 31) + Long.hashCode(this.loadCompleteTimestamp)) * 31) + Long.hashCode(this.responseLatency)) * 31) + Long.hashCode(this.remoteApiLatency)) * 31) + Long.hashCode(this.responseProcessingLatency)) * 31) + Long.hashCode(this.conversionLatency)) * 31) + Long.hashCode(this.renderingLatency)) * 31) + Long.hashCode(this.operationLatency);
    }

    public String toString() {
        return "PerformanceData(requestTimestamp=" + this.requestTimestamp + ", responseTimestamp=" + this.responseTimestamp + ", processingTimestamp=" + this.processingTimestamp + ", renderingTimestamp=" + this.renderingTimestamp + ", loadCompleteTimestamp=" + this.loadCompleteTimestamp + ", responseLatency=" + this.responseLatency + ", remoteApiLatency=" + this.remoteApiLatency + ", responseProcessingLatency=" + this.responseProcessingLatency + ", conversionLatency=" + this.conversionLatency + ", renderingLatency=" + this.renderingLatency + ", operationLatency=" + this.operationLatency + ')';
    }

    public PerformanceData(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11) {
        this.requestTimestamp = j;
        this.responseTimestamp = j2;
        this.processingTimestamp = j3;
        this.renderingTimestamp = j4;
        this.loadCompleteTimestamp = j5;
        this.responseLatency = j6;
        this.remoteApiLatency = j7;
        this.responseProcessingLatency = j8;
        this.conversionLatency = j9;
        this.renderingLatency = j10;
        this.operationLatency = j11;
    }

    public final long getRequestTimestamp() {
        return this.requestTimestamp;
    }

    public final long getResponseTimestamp() {
        return this.responseTimestamp;
    }

    public final long getProcessingTimestamp() {
        return this.processingTimestamp;
    }

    public final long getRenderingTimestamp() {
        return this.renderingTimestamp;
    }

    public final long getLoadCompleteTimestamp() {
        return this.loadCompleteTimestamp;
    }

    public final long getResponseLatency() {
        return this.responseLatency;
    }

    public final long getRemoteApiLatency() {
        return this.remoteApiLatency;
    }

    public final long getResponseProcessingLatency() {
        return this.responseProcessingLatency;
    }

    public final long getConversionLatency() {
        return this.conversionLatency;
    }

    public final long getRenderingLatency() {
        return this.renderingLatency;
    }

    public final long getOperationLatency() {
        return this.operationLatency;
    }
}
