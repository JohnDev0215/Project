package com.amazon.music.featuregating.weblabs;

import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.weblab.mobile.IMobileWeblab;
import com.amazon.weblab.mobile.IMobileWeblabTreatmentAndTriggerResult;
import com.amazon.weblab.mobile.model.MobileWeblabTriggerResult;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WeblabTreatmentSnapshotImpl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0012\u0010\u0013J&\u0010\b\u001a\u0004\u0018\u00010\u00072\u001a\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002j\u0004\u0018\u0001`\u0005H\u0016R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000bR$\u0010\f\u001a\u0004\u0018\u00010\u00078\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/featuregating/weblabs/WeblabTreatmentSnapshotImpl;", "Lcom/amazon/music/featuregating/weblabs/WeblabTreatmentSnapshot;", "Lkotlin/Function1;", "Lcom/amazon/music/featuregating/weblabs/WeblabTriggerResult;", "", "Lcom/amazon/music/featuregating/weblabs/WeblabTriggerResultCallback;", "triggerResult", "Lcom/amazon/music/platform/data/WeblabTreatment;", "getTreatmentAndTrigger", "Lcom/amazon/weblab/mobile/IMobileWeblab;", "mobileWeblab", "Lcom/amazon/weblab/mobile/IMobileWeblab;", "treatment", "Lcom/amazon/music/platform/data/WeblabTreatment;", "getTreatment", "()Lcom/amazon/music/platform/data/WeblabTreatment;", "setTreatment", "(Lcom/amazon/music/platform/data/WeblabTreatment;)V", "<init>", "(Lcom/amazon/weblab/mobile/IMobileWeblab;)V", "Companion", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public final class WeblabTreatmentSnapshotImpl implements WeblabTreatmentSnapshot {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = WeblabTreatmentSnapshotImpl.class.getSimpleName();
    private static final WeblabMetrics metrics = new WeblabMetrics();
    private final IMobileWeblab mobileWeblab;
    private WeblabTreatment treatment;

    /* JADX INFO: compiled from: WeblabTreatmentSnapshotImpl.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/music/featuregating/weblabs/WeblabTreatmentSnapshotImpl$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "metrics", "Lcom/amazon/music/featuregating/weblabs/WeblabMetrics;", "convertTreatmentResultEnum", "Lcom/amazon/music/featuregating/weblabs/WeblabTriggerResult;", "triggerResult", "Lcom/amazon/weblab/mobile/model/MobileWeblabTriggerResult;", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: WeblabTreatmentSnapshotImpl.kt */
        @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[MobileWeblabTriggerResult.values().length];
                iArr[MobileWeblabTriggerResult.SUCCESSFUL.ordinal()] = 1;
                iArr[MobileWeblabTriggerResult.REJECTED_FOR_ALLOCATION_MISMATCH.ordinal()] = 2;
                iArr[MobileWeblabTriggerResult.OVERRIDDEN.ordinal()] = 3;
                iArr[MobileWeblabTriggerResult.OVERRIDDEN_LOCALLY.ordinal()] = 4;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WeblabTriggerResult convertTreatmentResultEnum(MobileWeblabTriggerResult triggerResult) {
            Intrinsics.checkNotNullParameter(triggerResult, "triggerResult");
            int i = WhenMappings.$EnumSwitchMapping$0[triggerResult.ordinal()];
            if (i == 1) {
                return WeblabTriggerResult.SUCCESSFUL;
            }
            if (i == 2) {
                return WeblabTriggerResult.REJECTED_FOR_ALLOCATION_MISMATCH;
            }
            if (i == 3) {
                return WeblabTriggerResult.OVERRIDDEN;
            }
            if (i == 4) {
                return WeblabTriggerResult.OVERRIDDEN_LOCALLY;
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    public WeblabTreatmentSnapshotImpl(IMobileWeblab mobileWeblab) {
        Intrinsics.checkNotNullParameter(mobileWeblab, "mobileWeblab");
        this.mobileWeblab = mobileWeblab;
        WeblabTreatment.Companion companion = WeblabTreatment.INSTANCE;
        String treatmentAssignment = mobileWeblab.getTreatmentAssignment();
        Intrinsics.checkNotNullExpressionValue(treatmentAssignment, "mobileWeblab.treatmentAssignment");
        this.treatment = companion.from(treatmentAssignment);
    }

    @Override // com.amazon.music.featuregating.weblabs.WeblabTreatmentSnapshot
    public WeblabTreatment getTreatment() {
        return this.treatment;
    }

    @Override // com.amazon.music.featuregating.weblabs.WeblabTreatmentSnapshot
    public WeblabTreatment getTreatmentAndTrigger(final Function1<? super WeblabTriggerResult, Unit> triggerResult) {
        String treatment;
        try {
            final IMobileWeblabTreatmentAndTriggerResult treatmentAndRecordTrigger = this.mobileWeblab.getTreatmentAndRecordTrigger();
            treatment = treatmentAndRecordTrigger.getTreatment();
            if (triggerResult != null) {
                try {
                    WeblabManager.INSTANCE.getBackgroundHandler().post(new Runnable() { // from class: com.amazon.music.featuregating.weblabs.WeblabTreatmentSnapshotImpl$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            WeblabTreatmentSnapshotImpl.m2019getTreatmentAndTrigger$lambda1(treatmentAndRecordTrigger, triggerResult);
                        }
                    });
                } catch (Exception e) {
                    e = e;
                    metrics.trackException(TAG, "getTreatmentAndTrigger", e);
                }
            }
        } catch (Exception e2) {
            e = e2;
            treatment = null;
        }
        if (treatment != null) {
            return WeblabTreatment.INSTANCE.from(treatment);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getTreatmentAndTrigger$lambda-1, reason: not valid java name */
    public static final void m2019getTreatmentAndTrigger$lambda1(IMobileWeblabTreatmentAndTriggerResult iMobileWeblabTreatmentAndTriggerResult, Function1 function1) {
        try {
            MobileWeblabTriggerResult it = iMobileWeblabTreatmentAndTriggerResult.getFutureMobileWeblabTrigger().get();
            Companion companion = INSTANCE;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            function1.invoke(companion.convertTreatmentResultEnum(it));
        } catch (Exception unused) {
        }
    }
}
