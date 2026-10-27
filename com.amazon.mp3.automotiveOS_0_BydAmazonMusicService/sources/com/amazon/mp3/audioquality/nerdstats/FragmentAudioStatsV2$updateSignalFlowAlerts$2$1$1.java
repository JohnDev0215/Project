package com.amazon.mp3.audioquality.nerdstats;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: FragmentAudioStatsV2.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* synthetic */ class FragmentAudioStatsV2$updateSignalFlowAlerts$2$1$1 extends FunctionReferenceImpl implements Function0<Unit> {
    FragmentAudioStatsV2$updateSignalFlowAlerts$2$1$1(Object obj) {
        super(0, obj, FragmentAudioStatsV2.class, "enqueueViewModelRefresh", "enqueueViewModelRefresh()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        ((FragmentAudioStatsV2) this.receiver).enqueueViewModelRefresh();
    }
}
