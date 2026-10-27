package com.amazon.music.featuregating.weblabs;

import com.amazon.music.platform.data.WeblabTreatment;
import kotlin.Metadata;

/* JADX INFO: compiled from: WeblabClient.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H&J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\nH&J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H&R\u001c\u0010\u0013\u001a\u00020\u000e8&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/featuregating/weblabs/WeblabClient;", "", "", "weblabName", "Lcom/amazon/music/featuregating/weblabs/WeblabTreatmentSnapshot;", "treatmentSnapshot", "", "asynchronous", "", "triggerUpdate", "Lcom/amazon/music/platform/data/WeblabTreatment;", "treatment", "lockWeblab", "unlockWeblab", "Lcom/amazon/music/featuregating/weblabs/WeblabCustomerInfo;", "getCustomerInfo", "()Lcom/amazon/music/featuregating/weblabs/WeblabCustomerInfo;", "setCustomerInfo", "(Lcom/amazon/music/featuregating/weblabs/WeblabCustomerInfo;)V", "customerInfo", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public interface WeblabClient {
    WeblabCustomerInfo getCustomerInfo();

    void lockWeblab(String weblabName, WeblabTreatment treatment);

    void setCustomerInfo(WeblabCustomerInfo weblabCustomerInfo);

    WeblabTreatmentSnapshot treatmentSnapshot(String weblabName);

    void triggerUpdate(boolean asynchronous);

    void unlockWeblab(String weblabName);
}
