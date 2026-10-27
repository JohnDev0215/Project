package org.chromium.components.feature_engagement;

import android.text.TextUtils;
import defpackage.xe3;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class CppWrappedTestTracker implements xe3 {
    @Override // defpackage.xe3
    public final String b() {
        return null;
    }

    @Override // defpackage.xe3
    public final void c(Callback<Boolean> callback) {
        callback.onResult(Boolean.TRUE);
    }

    @Override // defpackage.xe3
    @CalledByNative
    public void dismissed(String str) {
        TextUtils.equals(null, str);
    }

    @CalledByNative
    public void dismissedWithSnooze(String str, int i) {
        TextUtils.equals(null, str);
    }

    @Override // defpackage.xe3
    @CalledByNative
    public int getTriggerState(String str) {
        return TextUtils.equals(null, str) ? 1 : 0;
    }

    @CalledByNative
    public boolean hasEverTriggered(String str, boolean z) {
        return true;
    }

    @Override // defpackage.xe3
    @CalledByNative
    public boolean isInitialized() {
        return true;
    }

    @Override // defpackage.xe3
    @CalledByNative
    public boolean shouldTriggerHelpUI(String str) {
        return TextUtils.equals(null, str);
    }

    @Override // defpackage.xe3
    @CalledByNative
    public boolean wouldTriggerHelpUI(String str) {
        return TextUtils.equals(null, str);
    }

    @Override // defpackage.xe3
    public final void a(String str) {
    }

    @Override // defpackage.xe3
    public final void e(String str) {
    }

    @Override // defpackage.xe3
    @CalledByNative
    public void notifyEvent(String str) {
    }

    @Override // defpackage.xe3
    public final void d(String str, Runnable runnable) {
    }
}
