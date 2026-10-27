package com.amazon.client.metrics.thirdparty;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface MetricEvent {
    void addCounter(String str, double d);

    void addString(String str, String str2);

    void addTimer(String str, double d);

    void clear();

    boolean getAnonymous();

    List<DataPoint> getAsDataPoints();

    String getNonAnonymousCustomerId();

    String getNonAnonymousSessionId();

    String getProgram();

    String getSource();

    void incrementCounter(String str, double d);

    void setAnonymous(boolean z);

    void setNonAnonymousCustomerId(String str);

    void setNonAnonymousSessionId(String str);

    void startTimer(String str);

    void stopTimer(String str);
}
