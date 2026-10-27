package com.amazon.weblab.mobile;

import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes5.dex */
public interface IMobileWeblab {
    IMobileWeblabTreatmentAndTriggerResult getTreatmentAndRecordTrigger() throws RejectedExecutionException;

    String getTreatmentAssignment();
}
