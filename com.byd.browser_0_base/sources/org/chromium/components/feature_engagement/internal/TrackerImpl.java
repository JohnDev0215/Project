package org.chromium.components.feature_engagement.internal;

import J.N;
import defpackage.iv;
import defpackage.xe3;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.feature_engagement.TriggerDetails;

/* JADX INFO: loaded from: classes2.dex */
public class TrackerImpl implements xe3 {
    public long a;

    public TrackerImpl(long j) {
        this.a = j;
    }

    @CalledByNative
    public static TrackerImpl create(long j) {
        return new TrackerImpl(j);
    }

    @CalledByNative
    public static TriggerDetails createTriggerDetails(boolean z, boolean z2) {
        return new TriggerDetails(z);
    }

    @Override // defpackage.xe3
    public final void a(String str) {
        N.M67MuK6a(this.a, this, str);
    }

    @Override // defpackage.xe3
    public final String b() {
        return N.MU51Y3Q_(this.a, this);
    }

    @Override // defpackage.xe3
    public final void c(Callback<Boolean> callback) {
        N.MLFWzkLW(this.a, this, callback);
    }

    @CalledByNative
    public final void clearNativePtr() {
        this.a = 0L;
    }

    @Override // defpackage.xe3
    public final void d(String str, Runnable runnable) {
        N.M4jLRdty(this.a, this, str, runnable);
    }

    @Override // defpackage.xe3
    public final void dismissed(String str) {
        N.M21A_pP$(this.a, this, str);
    }

    @Override // defpackage.xe3
    public final void e(String str) {
        N.MmhduNJ3(this.a, this, str);
    }

    @CalledByNative
    public final long getNativePtr() {
        return this.a;
    }

    @Override // defpackage.xe3
    public final int getTriggerState(String str) {
        return N.MtnFGh0Q(this.a, this, "IPH_PwaInstallAvailableFeature");
    }

    @Override // defpackage.xe3
    public final boolean isInitialized() {
        return N.MzNVGr12(this.a, this);
    }

    @Override // defpackage.xe3
    public final void notifyEvent(String str) {
        N.M0aLPz1m(this.a, this, str);
    }

    @Override // defpackage.xe3
    public final boolean shouldTriggerHelpUI(String str) {
        if (iv.e().g("enable-screenshot-ui-mode")) {
            return false;
        }
        return N.Mr$ygyBZ(this.a, this, str);
    }

    @Override // defpackage.xe3
    public final boolean wouldTriggerHelpUI(String str) {
        return N.ME$bTNVi(this.a, this, str);
    }

    public static class DisplayLockHandleAndroid {
        @CalledByNative
        public static DisplayLockHandleAndroid create(long j) {
            return new DisplayLockHandleAndroid();
        }

        @CalledByNative
        public final void clearNativePtr() {
        }
    }
}
