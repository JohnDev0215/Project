package android.accessibilityservice;

import android.accessibilityservice.FingerprintGestureController;

/* JADX INFO: renamed from: android.accessibilityservice.-$$Lambda$FingerprintGestureController$M-ZApqp96G6ZF2WdWrGDJ8Qsfck, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$FingerprintGestureController$MZApqp96G6ZF2WdWrGDJ8Qsfck implements Runnable {
    public final /* synthetic */ FingerprintGestureController.FingerprintGestureCallback f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ $$Lambda$FingerprintGestureController$MZApqp96G6ZF2WdWrGDJ8Qsfck(FingerprintGestureController.FingerprintGestureCallback fingerprintGestureCallback, boolean z) {
        this.f$0 = fingerprintGestureCallback;
        this.f$1 = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.onGestureDetectionAvailabilityChanged(this.f$1);
    }
}
