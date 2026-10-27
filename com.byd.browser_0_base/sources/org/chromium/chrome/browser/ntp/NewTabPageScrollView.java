package org.chromium.chrome.browser.ntp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.ScrollView;

/* JADX INFO: loaded from: classes2.dex */
public class NewTabPageScrollView extends ScrollView {
    public final GestureDetector e;

    public class a extends GestureDetector.SimpleOnGestureListener {
        public a() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onSingleTapUp(MotionEvent motionEvent) {
            boolean zOnSingleTapUp = super.onSingleTapUp(motionEvent);
            NewTabPageScrollView.this.requestFocus();
            return zOnSingleTapUp;
        }
    }

    public NewTabPageScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new GestureDetector(getContext(), new a());
    }

    @Override // android.widget.ScrollView
    public final boolean executeKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 19 || keyCode == 20 || keyCode == 62) {
            return super.executeKeyEvent(keyEvent);
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void focusableViewAvailable(View view) {
        if (hasFocus()) {
            return;
        }
        super.focusableViewAvailable(view);
    }

    @Override // android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        editorInfo.imeOptions = 33554432;
        return super.onCreateInputConnection(editorInfo);
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.e.onTouchEvent(motionEvent);
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.ScrollView, android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 0) {
            this.e.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }
}
