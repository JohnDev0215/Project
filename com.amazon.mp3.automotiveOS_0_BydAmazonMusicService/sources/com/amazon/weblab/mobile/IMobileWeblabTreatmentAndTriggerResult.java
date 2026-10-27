package com.amazon.weblab.mobile;

import com.amazon.weblab.mobile.model.MobileWeblabTriggerResult;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes5.dex */
public interface IMobileWeblabTreatmentAndTriggerResult {
    Future<MobileWeblabTriggerResult> getFutureMobileWeblabTrigger();

    String getTreatment();
}
