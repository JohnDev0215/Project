package com.neusoft.na.common.util;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KeyboardUtil.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\u001a\u0010\u0017\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/neusoft/na/common/util/KeyboardUtil;", "", "mContext", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "getMContext", "()Landroid/app/Activity;", "setMContext", "rootView", "Landroid/view/View;", "getRootView", "()Landroid/view/View;", "setRootView", "(Landroid/view/View;)V", "screenHeight", "", "getScreenHeight", "()I", "setScreenHeight", "(I)V", "screenHeight6", "getScreenHeight6", "setScreenHeight6", "virtualKeyboardHeight", "getVirtualKeyboardHeight", "setVirtualKeyboardHeight", "setOnKeyboardChangeListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/neusoft/na/common/util/KeyboardUtil$KeyboardChangeListener;", "KeyboardChangeListener", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class KeyboardUtil {
    private Activity mContext;
    private View rootView;
    private int screenHeight;
    private int screenHeight6;
    private int virtualKeyboardHeight;

    /* JADX INFO: compiled from: KeyboardUtil.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/neusoft/na/common/util/KeyboardUtil$KeyboardChangeListener;", "", "onKeyboardHide", "", "onKeyboardShow", "keyboardHight", "", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public interface KeyboardChangeListener {
        void onKeyboardHide();

        void onKeyboardShow(int keyboardHight);
    }

    public KeyboardUtil(Activity mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.mContext = mContext;
        int i = mContext.getResources().getDisplayMetrics().heightPixels;
        this.screenHeight = i;
        this.screenHeight6 = i / 6;
        View decorView = this.mContext.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "mContext.window.decorView");
        this.rootView = decorView;
    }

    public final Activity getMContext() {
        return this.mContext;
    }

    public final void setMContext(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<set-?>");
        this.mContext = activity;
    }

    public final int getVirtualKeyboardHeight() {
        return this.virtualKeyboardHeight;
    }

    public final void setVirtualKeyboardHeight(int i) {
        this.virtualKeyboardHeight = i;
    }

    public final int getScreenHeight() {
        return this.screenHeight;
    }

    public final void setScreenHeight(int i) {
        this.screenHeight = i;
    }

    public final int getScreenHeight6() {
        return this.screenHeight6;
    }

    public final void setScreenHeight6(int i) {
        this.screenHeight6 = i;
    }

    public final View getRootView() {
        return this.rootView;
    }

    public final void setRootView(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.rootView = view;
    }

    public final void setOnKeyboardChangeListener(final KeyboardChangeListener listener) {
        this.rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.neusoft.na.common.util.KeyboardUtil$$ExternalSyntheticLambda1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                KeyboardUtil.setOnKeyboardChangeListener$lambda$1(this.f$0, listener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setOnKeyboardChangeListener$lambda$1(final KeyboardUtil this$0, final KeyboardChangeListener keyboardChangeListener) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.rootView.post(new Runnable() { // from class: com.neusoft.na.common.util.KeyboardUtil$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                KeyboardUtil.setOnKeyboardChangeListener$lambda$1$lambda$0(this.f$0, keyboardChangeListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setOnKeyboardChangeListener$lambda$1$lambda$0(KeyboardUtil this$0, KeyboardChangeListener keyboardChangeListener) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Rect rect = new Rect();
        this$0.rootView.getWindowVisibleDisplayFrame(rect);
        int i = this$0.screenHeight - rect.bottom;
        if (i >= this$0.screenHeight6) {
            if (keyboardChangeListener != null) {
                keyboardChangeListener.onKeyboardShow(i - this$0.virtualKeyboardHeight);
            }
        } else {
            this$0.virtualKeyboardHeight = i;
            if (keyboardChangeListener != null) {
                keyboardChangeListener.onKeyboardHide();
            }
        }
    }
}
