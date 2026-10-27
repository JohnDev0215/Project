package com.amazon.client.metrics.thirdparty.internal;

import com.amazon.client.metrics.thirdparty.DataPoint;
import com.amazon.client.metrics.thirdparty.DataPointType;
import com.amazon.client.metrics.thirdparty.MetricEvent;
import com.amazon.client.metrics.thirdparty.MetricEventType;
import com.amazon.client.metrics.thirdparty.clickstream.internal.ClickStreamData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class BasicMetricEvent implements MetricEvent {
    private boolean mAllowRunningTimers;
    private boolean mAnonymous;
    private MetricEventType mMetricEventType;
    private String mNonAnonymousCustomerId;
    private String mNonAnonymousSessionId;
    private String mProgram;
    private String mSource;
    protected Map<String, Double> mCounters = new HashMap();
    protected Map<String, AggregatingTimer> mTimers = new HashMap();
    protected Map<String, List<String>> mStringDiscreteValues = new HashMap();
    protected Map<String, String> mStringClickstreamValues = new HashMap();

    public BasicMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        validateString(str);
        validateString(str2);
        this.mProgram = str;
        this.mSource = str2;
        this.mMetricEventType = metricEventType;
        this.mAllowRunningTimers = z;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getNonAnonymousSessionId() {
        return this.mNonAnonymousSessionId;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getNonAnonymousCustomerId() {
        return this.mNonAnonymousCustomerId;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void setNonAnonymousSessionId(String str) {
        this.mNonAnonymousSessionId = str;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void setNonAnonymousCustomerId(String str) {
        this.mNonAnonymousCustomerId = str;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void setAnonymous(boolean z) {
        this.mAnonymous = z;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public boolean getAnonymous() {
        return this.mAnonymous;
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
    public void addCounter(String str, double d) {
        incrementCounter(str, d);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void incrementCounter(String str, double d) {
        DoubleValidator.validateDouble(d);
        validateString(str);
        this.mCounters.put(str, Double.valueOf((this.mCounters.containsKey(str) ? this.mCounters.get(str).doubleValue() : 0.0d) + d));
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addTimer(String str, double d) {
        addTimer(str, d, 1);
    }

    public void addTimer(String str, double d, int i) {
        validateString(str);
        AggregatingTimer orCreateTimer = getOrCreateTimer(str);
        orCreateTimer.addTime(d, i);
        this.mTimers.put(str, orCreateTimer);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void startTimer(String str) {
        validateString(str);
        AggregatingTimer orCreateTimer = getOrCreateTimer(str);
        orCreateTimer.startTimer();
        this.mTimers.put(str, orCreateTimer);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void stopTimer(String str) {
        validateString(str);
        AggregatingTimer aggregatingTimer = this.mTimers.get(str);
        if (aggregatingTimer == null) {
            return;
        }
        aggregatingTimer.stopTimer();
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addString(String str, String str2) {
        appendString(str, str2);
    }

    public void appendString(String str, String str2) {
        validateString(str);
        if (validateStringValue(str2)) {
            List<String> arrayList = this.mStringDiscreteValues.get(str);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            arrayList.add(str2);
            this.mStringDiscreteValues.put(str, arrayList);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void clear() {
        this.mCounters.clear();
        this.mTimers.clear();
        this.mStringDiscreteValues.clear();
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public List<DataPoint> getAsDataPoints() {
        DataPoint dataPoint;
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, Double> entry : this.mCounters.entrySet()) {
            arrayList.add(new DataPoint(entry.getKey(), Double.toString(entry.getValue().doubleValue()), 1, DataPointType.CT));
        }
        for (Map.Entry<String, AggregatingTimer> entry2 : this.mTimers.entrySet()) {
            try {
                if (entry2.getValue().getSamples() == 0) {
                    if (!this.mAllowRunningTimers || entry2.getValue().numRunningTimers() <= 0) {
                        throw new IllegalStateException("Discarding timer as sample count is 0. Timer name: " + entry2.getKey());
                    }
                } else {
                    if (this.mMetricEventType.equals(MetricEventType.AGGREGATING)) {
                        dataPoint = new DataPoint(entry2.getKey(), Double.toString(entry2.getValue().getElapsedTime()), entry2.getValue().getSamples(), DataPointType.TI);
                    } else if (this.mMetricEventType.equals(MetricEventType.AVERAGING)) {
                        dataPoint = new DataPoint(entry2.getKey(), Double.toString(entry2.getValue().getElapsedTime() / ((double) entry2.getValue().getSamples())), 1, DataPointType.TI);
                    } else {
                        throw new IllegalArgumentException("Unknown Metric event type. Metric event type: " + this.mMetricEventType);
                    }
                    arrayList.add(dataPoint);
                }
            } catch (IllegalArgumentException | IllegalStateException unused) {
            }
        }
        for (Map.Entry<String, List<String>> entry3 : this.mStringDiscreteValues.entrySet()) {
            boolean z = false;
            for (ClickStreamData clickStreamData : ClickStreamData.values()) {
                if (clickStreamData.getName().equals(entry3.getKey()) && !ClickStreamData.isOverrideAllowed(clickStreamData)) {
                    z = true;
                }
            }
            if (!z) {
                arrayList.add(new DataPoint(entry3.getKey(), listToString(entry3.getValue()), 1, DataPointType.DV));
            }
        }
        for (Map.Entry<String, String> entry4 : this.mStringClickstreamValues.entrySet()) {
            arrayList.add(new DataPoint(entry4.getKey(), entry4.getValue(), 1, DataPointType.CK));
        }
        return arrayList;
    }

    private String listToString(List<?> list) {
        if (list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0).toString());
        for (int i = 1; i < list.size(); i++) {
            sb.append(",");
            sb.append(list.get(i).toString());
        }
        return sb.toString();
    }

    private AggregatingTimer getOrCreateTimer(String str) {
        AggregatingTimer aggregatingTimer = this.mTimers.get(str);
        return aggregatingTimer == null ? new AggregatingTimer(this.mAllowRunningTimers) : aggregatingTimer;
    }

    private void validateString(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Invalid string. Cannot be null or empty");
        }
    }

    private boolean validateStringValue(String str) {
        return (str == null || str.contains(" ; ")) ? false : true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mProgram);
        sb.append(" ");
        sb.append(this.mSource);
        sb.append(" ");
        List<DataPoint> asDataPoints = getAsDataPoints();
        for (int i = 0; i < asDataPoints.size(); i++) {
            sb.append(asDataPoints.get(i).toString());
        }
        sb.append("\n");
        return sb.toString();
    }
}
