package com.android.internal.accessibility;

import com.android.internal.accessibility.AccessibilityShortcutController;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.internal.accessibility.-$$Lambda$AccessibilityShortcutController$TtsPrompt$rSvbPKgeiYtMTwvNC2DcnXNlUvU, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class $$Lambda$AccessibilityShortcutController$TtsPrompt$rSvbPKgeiYtMTwvNC2DcnXNlUvU implements Consumer {
    public static final /* synthetic */ $$Lambda$AccessibilityShortcutController$TtsPrompt$rSvbPKgeiYtMTwvNC2DcnXNlUvU INSTANCE = new $$Lambda$AccessibilityShortcutController$TtsPrompt$rSvbPKgeiYtMTwvNC2DcnXNlUvU();

    private /* synthetic */ $$Lambda$AccessibilityShortcutController$TtsPrompt$rSvbPKgeiYtMTwvNC2DcnXNlUvU() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((AccessibilityShortcutController.TtsPrompt) obj).waitForTtsReady();
    }
}
