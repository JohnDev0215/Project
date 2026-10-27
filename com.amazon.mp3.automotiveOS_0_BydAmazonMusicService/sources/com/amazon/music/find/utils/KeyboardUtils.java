package com.amazon.music.find.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KeyboardUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bJ \u0010\u0003\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0007\u001a\u00020\bJ>\u0010\r\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\bJ0\u0010\u0013\u001a\u00020\u0004*\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\b¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/find/utils/KeyboardUtils;", "", "()V", "hideKeyboard", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "flags", "", "context", "Landroid/content/Context;", "view", "Landroid/view/View;", "showKeyboard", "force", "", "showFlags", "toggleShowFlags", "toggleHideFlags", "focusAndShowKeyboard", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class KeyboardUtils {
    public static final KeyboardUtils INSTANCE = new KeyboardUtils();

    private KeyboardUtils() {
    }

    public static /* synthetic */ void showKeyboard$default(KeyboardUtils keyboardUtils, Context context, View view, boolean z, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            z = false;
        }
        boolean z2 = z;
        int i5 = (i4 & 8) != 0 ? 1 : i;
        if ((i4 & 16) != 0) {
            i2 = 2;
        }
        keyboardUtils.showKeyboard(context, view, z2, i5, i2, (i4 & 32) != 0 ? 1 : i3);
    }

    public final void showKeyboard(Context context, View view, boolean force, int showFlags, int toggleShowFlags, int toggleHideFlags) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(view, "view");
        Object systemService = context.getSystemService("input_method");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        }
        InputMethodManager inputMethodManager = (InputMethodManager) systemService;
        if (force) {
            inputMethodManager.toggleSoftInput(toggleShowFlags, toggleHideFlags);
        }
        inputMethodManager.showSoftInput(view, showFlags);
    }

    public static /* synthetic */ void hideKeyboard$default(KeyboardUtils keyboardUtils, Context context, View view, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        keyboardUtils.hideKeyboard(context, view, i);
    }

    public final void hideKeyboard(Context context, View view, int flags) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(view, "view");
        Object systemService = context.getSystemService("input_method");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        }
        ((InputMethodManager) systemService).hideSoftInputFromWindow(view.getWindowToken(), flags);
    }

    public static /* synthetic */ void hideKeyboard$default(KeyboardUtils keyboardUtils, Activity activity, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        keyboardUtils.hideKeyboard(activity, i);
    }

    public final void hideKeyboard(Activity activity, int flags) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Object systemService = activity.getSystemService("input_method");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        }
        InputMethodManager inputMethodManager = (InputMethodManager) systemService;
        if (inputMethodManager.isAcceptingText()) {
            View currentFocus = activity.getCurrentFocus();
            if (currentFocus == null) {
                Window window = activity.getWindow();
                currentFocus = window == null ? null : window.getDecorView();
            }
            if (currentFocus == null) {
                return;
            }
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), flags);
        }
    }

    public static /* synthetic */ void focusAndShowKeyboard$default(KeyboardUtils keyboardUtils, View view, boolean z, int i, int i2, int i3, int i4, Object obj) {
        int i5 = (i4 & 2) != 0 ? 1 : i;
        if ((i4 & 4) != 0) {
            i2 = 2;
        }
        keyboardUtils.focusAndShowKeyboard(view, z, i5, i2, (i4 & 8) != 0 ? 1 : i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void focusAndShowKeyboard$showTheKeyboardNow(final View view, final boolean z, final int i, final int i2, final int i3) {
        if (view.isFocused()) {
            view.post(new Runnable() { // from class: com.amazon.music.find.utils.KeyboardUtils$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    KeyboardUtils.m2062focusAndShowKeyboard$showTheKeyboardNow$lambda3(view, z, i, i2, i3);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: focusAndShowKeyboard$showTheKeyboardNow$lambda-3, reason: not valid java name */
    public static final void m2062focusAndShowKeyboard$showTheKeyboardNow$lambda3(View this_showTheKeyboardNow, boolean z, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this_showTheKeyboardNow, "$this_showTheKeyboardNow");
        KeyboardUtils keyboardUtils = INSTANCE;
        Context context = this_showTheKeyboardNow.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        keyboardUtils.showKeyboard(context, this_showTheKeyboardNow, z, i, i2, i3);
    }

    public final void focusAndShowKeyboard(final View view, final boolean z, final int i, final int i2, final int i3) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.requestFocus();
        if (view.hasWindowFocus()) {
            focusAndShowKeyboard$showTheKeyboardNow(view, z, i, i2, i3);
        } else {
            view.getViewTreeObserver().addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() { // from class: com.amazon.music.find.utils.KeyboardUtils.focusAndShowKeyboard.1
                @Override // android.view.ViewTreeObserver.OnWindowFocusChangeListener
                public void onWindowFocusChanged(boolean hasFocus) {
                    if (hasFocus) {
                        KeyboardUtils.focusAndShowKeyboard$showTheKeyboardNow(view, z, i, i2, i3);
                        view.getViewTreeObserver().removeOnWindowFocusChangeListener(this);
                    }
                }
            });
        }
    }
}
