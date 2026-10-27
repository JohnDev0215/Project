package android.accessibilityservice;

import android.accessibilityservice.AccessibilityService;

/* JADX INFO: renamed from: android.accessibilityservice.-$$Lambda$AccessibilityService$QPuf76fmbA3YdpMRuW3aotBDLhc, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AccessibilityService$QPuf76fmbA3YdpMRuW3aotBDLhc implements Runnable {
    public final /* synthetic */ AccessibilityService.TakeScreenshotCallback f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ $$Lambda$AccessibilityService$QPuf76fmbA3YdpMRuW3aotBDLhc(AccessibilityService.TakeScreenshotCallback takeScreenshotCallback, int i) {
        this.f$0 = takeScreenshotCallback;
        this.f$1 = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.onFailure(this.f$1);
    }
}
