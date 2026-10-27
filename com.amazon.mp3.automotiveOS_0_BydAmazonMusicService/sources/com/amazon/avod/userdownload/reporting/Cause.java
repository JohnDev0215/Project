package com.amazon.avod.userdownload.reporting;

import com.amazon.avod.metrics.pmet.MetricParameter;

/* JADX INFO: loaded from: classes2.dex */
public interface Cause extends MetricParameter {
    String getCauseMessage();

    String getEventSubType();
}
