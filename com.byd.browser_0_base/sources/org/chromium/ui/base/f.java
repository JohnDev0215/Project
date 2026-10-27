package org.chromium.ui.base;

import android.view.accessibility.AccessibilityManager;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: WindowAndroid.java */
/* JADX INFO: loaded from: classes3.dex */
public final class f implements AccessibilityManager.TouchExplorationStateChangeListener {
    public final /* synthetic */ WindowAndroid.f a;

    public f(WindowAndroid.f fVar) {
        this.a = fVar;
    }

    @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
    public final void onTouchExplorationStateChanged(boolean z) {
        WindowAndroid.f fVar = this.a;
        WindowAndroid windowAndroid = fVar.b;
        windowAndroid.n = windowAndroid.l.isTouchExplorationEnabled();
        fVar.b.s();
    }
}
