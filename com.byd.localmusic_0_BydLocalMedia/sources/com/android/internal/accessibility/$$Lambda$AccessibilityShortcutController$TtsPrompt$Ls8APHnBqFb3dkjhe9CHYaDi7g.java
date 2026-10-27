package com.android.internal.accessibility;

import com.android.internal.accessibility.AccessibilityShortcutController;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.internal.accessibility.-$$Lambda$AccessibilityShortcutController$TtsPrompt$Ls8APHnBqFb3-dkjhe9CHYaDi7g, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class $$Lambda$AccessibilityShortcutController$TtsPrompt$Ls8APHnBqFb3dkjhe9CHYaDi7g implements Consumer {
    public static final /* synthetic */ $$Lambda$AccessibilityShortcutController$TtsPrompt$Ls8APHnBqFb3dkjhe9CHYaDi7g INSTANCE = new $$Lambda$AccessibilityShortcutController$TtsPrompt$Ls8APHnBqFb3dkjhe9CHYaDi7g();

    private /* synthetic */ $$Lambda$AccessibilityShortcutController$TtsPrompt$Ls8APHnBqFb3dkjhe9CHYaDi7g() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((AccessibilityShortcutController.TtsPrompt) obj).waitForTtsReady();
    }
}
