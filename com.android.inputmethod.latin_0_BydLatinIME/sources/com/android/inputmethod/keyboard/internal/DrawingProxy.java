package com.android.inputmethod.keyboard.internal;

import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.keyboard.MoreKeysPanel;
import com.android.inputmethod.keyboard.PointerTracker;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface DrawingProxy {
    public static final int FADE_IN = 0;
    public static final int FADE_OUT = 1;

    void dismissGestureFloatingPreviewTextWithoutDelay();

    void onKeyPressed(@Nonnull Key key, boolean withPreview);

    void onKeyReleased(@Nonnull Key key, boolean withAnimation);

    void showGestureTrail(@Nonnull PointerTracker tracker, boolean showsFloatingPreviewText);

    @Nullable
    MoreKeysPanel showMoreKeysKeyboard(@Nonnull Key key, @Nonnull PointerTracker tracker);

    void showSlidingKeyInputPreview(@Nullable PointerTracker tracker);

    void startWhileTypingAnimation(int fadeInOrOut);
}
