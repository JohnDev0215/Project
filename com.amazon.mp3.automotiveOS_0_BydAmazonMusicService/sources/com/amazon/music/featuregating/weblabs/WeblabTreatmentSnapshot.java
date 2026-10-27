package com.amazon.music.featuregating.weblabs;

import com.amazon.music.platform.data.WeblabTreatment;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: WeblabTreatmentSnapshot.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J&\u0010\b\u001a\u0004\u0018\u00010\u00072\u001a\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002j\u0004\u0018\u0001`\u0005H&R\u001e\u0010\r\u001a\u0004\u0018\u00010\u00078&@&X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/featuregating/weblabs/WeblabTreatmentSnapshot;", "", "Lkotlin/Function1;", "Lcom/amazon/music/featuregating/weblabs/WeblabTriggerResult;", "", "Lcom/amazon/music/featuregating/weblabs/WeblabTriggerResultCallback;", "triggerResult", "Lcom/amazon/music/platform/data/WeblabTreatment;", "getTreatmentAndTrigger", "getTreatment", "()Lcom/amazon/music/platform/data/WeblabTreatment;", "setTreatment", "(Lcom/amazon/music/platform/data/WeblabTreatment;)V", "treatment", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public interface WeblabTreatmentSnapshot {
    WeblabTreatment getTreatment();

    WeblabTreatment getTreatmentAndTrigger(Function1<? super WeblabTriggerResult, Unit> triggerResult);
}
