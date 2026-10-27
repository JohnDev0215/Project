package com.byd.card.callback;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

/* JADX INFO: loaded from: classes.dex */
public class SoftKeyBoardListener {
    boolean isShow;
    private ViewTreeObserver.OnGlobalLayoutListener listener;
    private OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener;
    private View rootView;
    private int screenBottom;

    public interface OnSoftKeyBoardChangeListener {
        void keyBoardHide();

        void keyBoardShow(int height);
    }

    private SoftKeyBoardListener(Activity activity) {
        this.isShow = false;
        int i = activity.getWindow().getAttributes().softInputMode & 15;
        if (i == 4 || i == 5) {
            this.isShow = true;
        }
        this.rootView = activity.getWindow().getDecorView();
        this.screenBottom = activity.getWindowManager().getDefaultDisplay().getHeight();
        this.listener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.byd.card.callback.SoftKeyBoardListener.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                Rect rect = new Rect();
                SoftKeyBoardListener.this.rootView.getWindowVisibleDisplayFrame(rect);
                if (!SoftKeyBoardListener.this.isShow && SoftKeyBoardListener.this.screenBottom > rect.bottom) {
                    SoftKeyBoardListener.this.isShow = true;
                    if (SoftKeyBoardListener.this.onSoftKeyBoardChangeListener != null) {
                        SoftKeyBoardListener.this.onSoftKeyBoardChangeListener.keyBoardShow(SoftKeyBoardListener.this.screenBottom - rect.bottom);
                        return;
                    }
                    return;
                }
                if (!SoftKeyBoardListener.this.isShow || rect.bottom < SoftKeyBoardListener.this.screenBottom) {
                    return;
                }
                SoftKeyBoardListener.this.isShow = false;
                if (SoftKeyBoardListener.this.onSoftKeyBoardChangeListener != null) {
                    SoftKeyBoardListener.this.onSoftKeyBoardChangeListener.keyBoardHide();
                }
            }
        };
        this.rootView.getViewTreeObserver().addOnGlobalLayoutListener(this.listener);
        addLifeObServer(activity);
    }

    private void setOnSoftKeyBoardChangeListener(OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        this.onSoftKeyBoardChangeListener = onSoftKeyBoardChangeListener;
    }

    public static void setListener(Activity activity, OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        new SoftKeyBoardListener(activity).setOnSoftKeyBoardChangeListener(onSoftKeyBoardChangeListener);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addLifeObServer(Activity activity) {
        if (activity instanceof LifecycleOwner) {
            ((LifecycleOwner) activity).getLifecycle().addObserver(new GenericLifecycleObserver() { // from class: com.byd.card.callback.SoftKeyBoardListener.2
                @Override // androidx.lifecycle.LifecycleEventObserver
                public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
                    if (event != Lifecycle.Event.ON_DESTROY || SoftKeyBoardListener.this.rootView == null) {
                        return;
                    }
                    SoftKeyBoardListener.this.rootView.getViewTreeObserver().removeOnGlobalLayoutListener(SoftKeyBoardListener.this.listener);
                }
            });
        }
    }

    public static void closeKeyboard(EditText mEditText, Context mContext) {
        ((InputMethodManager) mContext.getSystemService("input_method")).hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

    public static void openKeyboard(View view, Context mContext) {
        ((InputMethodManager) mContext.getSystemService("input_method")).showSoftInput(view, 0);
    }

    public static boolean isKeyBoardShow(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        return height > rect.bottom;
    }
}
