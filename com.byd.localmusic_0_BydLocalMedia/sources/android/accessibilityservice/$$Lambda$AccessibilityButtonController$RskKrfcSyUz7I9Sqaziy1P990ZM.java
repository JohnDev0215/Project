package android.accessibilityservice;

import android.accessibilityservice.AccessibilityButtonController;

/* JADX INFO: renamed from: android.accessibilityservice.-$$Lambda$AccessibilityButtonController$RskKrfcSyUz7I9Sqaziy1P990ZM, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AccessibilityButtonController$RskKrfcSyUz7I9Sqaziy1P990ZM implements Runnable {
    public final /* synthetic */ AccessibilityButtonController f$0;
    public final /* synthetic */ AccessibilityButtonController.AccessibilityButtonCallback f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ $$Lambda$AccessibilityButtonController$RskKrfcSyUz7I9Sqaziy1P990ZM(AccessibilityButtonController accessibilityButtonController, AccessibilityButtonController.AccessibilityButtonCallback accessibilityButtonCallback, boolean z) {
        this.f$0 = accessibilityButtonController;
        this.f$1 = accessibilityButtonCallback;
        this.f$2 = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.lambda$dispatchAccessibilityButtonAvailabilityChanged$1$AccessibilityButtonController(this.f$1, this.f$2);
    }
}
