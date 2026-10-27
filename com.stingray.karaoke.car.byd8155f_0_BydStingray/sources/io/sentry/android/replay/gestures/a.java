package io.sentry.android.replay.gestures;

import A7.m;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import io.sentry.F1;
import io.sentry.android.replay.ReplayIntegration;
import io.sentry.android.replay.capture.l;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Window.Callback {
    public final Window.Callback l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final F1 f16397m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ReplayIntegration f16398n;

    public a(F1 f12, ReplayIntegration replayIntegration, Window.Callback callback) {
        this.l = callback;
        this.f16397m = f12;
        this.f16398n = replayIntegration;
    }

    public final boolean a(MotionEvent motionEvent) {
        Window.Callback callback = this.l;
        if (callback == null) {
            return false;
        }
        return callback.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        Window.Callback callback = this.l;
        if (callback == null) {
            return false;
        }
        return callback.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Window.Callback callback = this.l;
        if (callback == null) {
            return false;
        }
        return callback.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        Window.Callback callback = this.l;
        if (callback == null) {
            return false;
        }
        return callback.dispatchKeyShortcutEvent(keyEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Window.Callback callback = this.l;
        if (callback == null) {
            return false;
        }
        return callback.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent != null) {
            MotionEvent motionEventObtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
            m.e(motionEventObtainNoHistory, "obtainNoHistory(event)");
            try {
                l lVar = this.f16398n.f16324v;
                if (lVar != null) {
                    lVar.d(motionEventObtainNoHistory);
                }
            } finally {
                try {
                } finally {
                }
            }
        }
        return a(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        Window.Callback callback = this.l;
        if (callback == null) {
            return false;
        }
        return callback.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final void onActionModeFinished(ActionMode actionMode) {
        Window.Callback callback = this.l;
        if (callback == null) {
            return;
        }
        callback.onActionModeFinished(actionMode);
    }

    @Override // android.view.Window.Callback
    public final void onActionModeStarted(ActionMode actionMode) {
        Window.Callback callback = this.l;
        if (callback == null) {
            return;
        }
        callback.onActionModeStarted(actionMode);
    }

    @Override // android.view.Window.Callback
    public final void onAttachedToWindow() {
        Window.Callback callback = this.l;
        if (callback == null) {
            return;
        }
        callback.onAttachedToWindow();
    }

    @Override // android.view.Window.Callback
    public final void onContentChanged() {
        Window.Callback callback = this.l;
        if (callback == null) {
            return;
        }
        callback.onContentChanged();
    }

    @Override // android.view.Window.Callback
    public final boolean onCreatePanelMenu(int i7, Menu menu) {
        Window.Callback callback = this.l;
        if (callback == null) {
            return false;
        }
        return callback.onCreatePanelMenu(i7, menu);
    }

    @Override // android.view.Window.Callback
    public final View onCreatePanelView(int i7) {
        Window.Callback callback = this.l;
        if (callback == null) {
            return null;
        }
        return callback.onCreatePanelView(i7);
    }

    @Override // android.view.Window.Callback
    public final void onDetachedFromWindow() {
        Window.Callback callback = this.l;
        if (callback == null) {
            return;
        }
        callback.onDetachedFromWindow();
    }

    @Override // android.view.Window.Callback
    public final boolean onMenuItemSelected(int i7, MenuItem menuItem) {
        Window.Callback callback = this.l;
        if (callback == null) {
            return false;
        }
        return callback.onMenuItemSelected(i7, menuItem);
    }

    @Override // android.view.Window.Callback
    public final boolean onMenuOpened(int i7, Menu menu) {
        Window.Callback callback = this.l;
        if (callback == null) {
            return false;
        }
        return callback.onMenuOpened(i7, menu);
    }

    @Override // android.view.Window.Callback
    public final void onPanelClosed(int i7, Menu menu) {
        Window.Callback callback = this.l;
        if (callback == null) {
            return;
        }
        callback.onPanelClosed(i7, menu);
    }

    @Override // android.view.Window.Callback
    public final void onPointerCaptureChanged(boolean z6) {
        Window.Callback callback = this.l;
        if (callback == null) {
            return;
        }
        callback.onPointerCaptureChanged(z6);
    }

    @Override // android.view.Window.Callback
    public final boolean onPreparePanel(int i7, View view, Menu menu) {
        Window.Callback callback = this.l;
        if (callback == null) {
            return false;
        }
        return callback.onPreparePanel(i7, view, menu);
    }

    @Override // android.view.Window.Callback
    public final void onProvideKeyboardShortcuts(List list, Menu menu, int i7) {
        Window.Callback callback = this.l;
        if (callback == null) {
            return;
        }
        callback.onProvideKeyboardShortcuts(list, menu, i7);
    }

    @Override // android.view.Window.Callback
    public final boolean onSearchRequested() {
        Window.Callback callback = this.l;
        if (callback == null) {
            return false;
        }
        return callback.onSearchRequested();
    }

    @Override // android.view.Window.Callback
    public final void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        Window.Callback callback = this.l;
        if (callback == null) {
            return;
        }
        callback.onWindowAttributesChanged(layoutParams);
    }

    @Override // android.view.Window.Callback
    public final void onWindowFocusChanged(boolean z6) {
        Window.Callback callback = this.l;
        if (callback == null) {
            return;
        }
        callback.onWindowFocusChanged(z6);
    }

    @Override // android.view.Window.Callback
    public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        Window.Callback callback2 = this.l;
        if (callback2 == null) {
            return null;
        }
        return callback2.onWindowStartingActionMode(callback);
    }

    @Override // android.view.Window.Callback
    public final boolean onSearchRequested(SearchEvent searchEvent) {
        Window.Callback callback = this.l;
        if (callback == null) {
            return false;
        }
        return callback.onSearchRequested(searchEvent);
    }

    @Override // android.view.Window.Callback
    public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i7) {
        Window.Callback callback2 = this.l;
        if (callback2 == null) {
            return null;
        }
        return callback2.onWindowStartingActionMode(callback, i7);
    }
}
