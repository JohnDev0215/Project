package com.amazon.mp3.view.stage;

import android.os.Handler;
import android.os.Looper;
import com.amazon.mp3.skiplimit.impl.SkipLimitProviderImpl;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StageViewPresenter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/amazon/mp3/view/stage/StageViewPresenter$mSkipLimitUpdateListener$1", "Lcom/amazon/mp3/skiplimit/impl/SkipLimitProviderImpl$OnSkipLimitUpdateListener;", "onSkipLimitUpdated", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StageViewPresenter$mSkipLimitUpdateListener$1 implements SkipLimitProviderImpl.OnSkipLimitUpdateListener {
    final /* synthetic */ StageViewPresenter this$0;

    StageViewPresenter$mSkipLimitUpdateListener$1(StageViewPresenter stageViewPresenter) {
        this.this$0 = stageViewPresenter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onSkipLimitUpdated$lambda-0, reason: not valid java name */
    public static final void m1796onSkipLimitUpdated$lambda0(StageViewPresenter this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.updatePlayerControls(this$0.getStageViewModel().getEnabledStateManager());
    }

    @Override // com.amazon.mp3.skiplimit.impl.SkipLimitProviderImpl.OnSkipLimitUpdateListener
    public void onSkipLimitUpdated() {
        Handler handler = new Handler(Looper.getMainLooper());
        final StageViewPresenter stageViewPresenter = this.this$0;
        handler.post(new Runnable() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$mSkipLimitUpdateListener$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                StageViewPresenter$mSkipLimitUpdateListener$1.m1796onSkipLimitUpdated$lambda0(stageViewPresenter);
            }
        });
    }
}
