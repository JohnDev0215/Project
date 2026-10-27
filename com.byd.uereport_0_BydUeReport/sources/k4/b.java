package k4;

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

/* JADX INFO: compiled from: BydWindowCallback.java */
/* JADX INFO: loaded from: classes2.dex */
public class b implements Window.Callback {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Window.Callback f5875d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Window.Callback f5876e;

    public b(Window.Callback callback, e eVar) {
        this.f5875d = callback;
        this.f5876e = eVar;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        Window.Callback callback = this.f5876e;
        if (callback != null) {
            callback.dispatchGenericMotionEvent(motionEvent);
        }
        Window.Callback callback2 = this.f5875d;
        if (callback2 != null) {
            return callback2.dispatchGenericMotionEvent(motionEvent);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Window.Callback callback = this.f5876e;
        if (callback != null) {
            callback.dispatchKeyEvent(keyEvent);
        }
        Window.Callback callback2 = this.f5875d;
        if (callback2 != null) {
            return callback2.dispatchKeyEvent(keyEvent);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        Window.Callback callback = this.f5876e;
        if (callback != null) {
            callback.dispatchKeyShortcutEvent(keyEvent);
        }
        Window.Callback callback2 = this.f5875d;
        if (callback2 != null) {
            return callback2.dispatchKeyShortcutEvent(keyEvent);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Window.Callback callback = this.f5876e;
        if (callback != null) {
            callback.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        Window.Callback callback2 = this.f5875d;
        if (callback2 != null) {
            return callback2.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Window.Callback callback = this.f5876e;
        if (callback != null) {
            callback.dispatchTouchEvent(motionEvent);
        }
        Window.Callback callback2 = this.f5875d;
        if (callback2 != null) {
            return callback2.dispatchTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        Window.Callback callback = this.f5876e;
        if (callback != null) {
            callback.dispatchTrackballEvent(motionEvent);
        }
        Window.Callback callback2 = this.f5875d;
        if (callback2 != null) {
            return callback2.dispatchTrackballEvent(motionEvent);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public void onActionModeFinished(ActionMode actionMode) {
        Window.Callback callback = this.f5876e;
        if (callback != null) {
            callback.onActionModeFinished(actionMode);
        }
        Window.Callback callback2 = this.f5875d;
        if (callback2 != null) {
            callback2.onActionModeFinished(actionMode);
        }
    }

    @Override // android.view.Window.Callback
    public void onActionModeStarted(ActionMode actionMode) {
        Window.Callback callback = this.f5876e;
        if (callback != null) {
            callback.onActionModeStarted(actionMode);
        }
        Window.Callback callback2 = this.f5875d;
        if (callback2 != null) {
            callback2.onActionModeStarted(actionMode);
        }
    }

    @Override // android.view.Window.Callback
    public void onAttachedToWindow() {
        Window.Callback callback = this.f5876e;
        if (callback != null) {
            callback.onAttachedToWindow();
        }
        Window.Callback callback2 = this.f5875d;
        if (callback2 != null) {
            callback2.onAttachedToWindow();
        }
    }

    @Override // android.view.Window.Callback
    public void onContentChanged() {
        Window.Callback callback = this.f5876e;
        if (callback != null) {
            callback.onContentChanged();
        }
        Window.Callback callback2 = this.f5875d;
        if (callback2 != null) {
            callback2.onContentChanged();
        }
    }

    @Override // android.view.Window.Callback
    public boolean onCreatePanelMenu(int i8, Menu menu) {
        Window.Callback callback = this.f5876e;
        if (callback != null) {
            callback.onCreatePanelMenu(i8, menu);
        }
        Window.Callback callback2 = this.f5875d;
        if (callback2 != null) {
            return callback2.onCreatePanelMenu(i8, menu);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public View onCreatePanelView(int i8) {
        Window.Callback callback = this.f5876e;
        if (callback != null) {
            callback.onCreatePanelView(i8);
        }
        Window.Callback callback2 = this.f5875d;
        if (callback2 != null) {
            return callback2.onCreatePanelView(i8);
        }
        return null;
    }

    @Override // android.view.Window.Callback
    public void onDetachedFromWindow() {
        Window.Callback callback = this.f5876e;
        if (callback != null) {
            callback.onDetachedFromWindow();
        }
        Window.Callback callback2 = this.f5875d;
        if (callback2 != null) {
            callback2.onDetachedFromWindow();
        }
    }

    @Override // android.view.Window.Callback
    public boolean onMenuItemSelected(int i8, MenuItem menuItem) {
        Window.Callback callback = this.f5876e;
        if (callback != null) {
            callback.onMenuItemSelected(i8, menuItem);
        }
        Window.Callback callback2 = this.f5875d;
        if (callback2 != null) {
            return callback2.onMenuItemSelected(i8, menuItem);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean onMenuOpened(int i8, Menu menu) {
        Window.Callback callback = this.f5876e;
        if (callback != null) {
            callback.onMenuOpened(i8, menu);
        }
        Window.Callback callback2 = this.f5875d;
        if (callback2 != null) {
            return callback2.onMenuOpened(i8, menu);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public void onPanelClosed(int i8, Menu menu) {
        Window.Callback callback = this.f5876e;
        if (callback != null) {
            callback.onPanelClosed(i8, menu);
        }
        Window.Callback callback2 = this.f5875d;
        if (callback2 != null) {
            callback2.onPanelClosed(i8, menu);
        }
    }

    @Override // android.view.Window.Callback
    public boolean onPreparePanel(int i8, View view, Menu menu) {
        Window.Callback callback = this.f5876e;
        if (callback != null) {
            callback.onPreparePanel(i8, view, menu);
        }
        Window.Callback callback2 = this.f5875d;
        if (callback2 != null) {
            return callback2.onPreparePanel(i8, view, menu);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean onSearchRequested() {
        Window.Callback callback = this.f5876e;
        if (callback != null) {
            callback.onSearchRequested();
        }
        Window.Callback callback2 = this.f5875d;
        if (callback2 != null) {
            return callback2.onSearchRequested();
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        Window.Callback callback = this.f5876e;
        if (callback != null) {
            callback.onWindowAttributesChanged(layoutParams);
        }
        Window.Callback callback2 = this.f5875d;
        if (callback2 != null) {
            callback2.onWindowAttributesChanged(layoutParams);
        }
    }

    @Override // android.view.Window.Callback
    public void onWindowFocusChanged(boolean z7) {
        Window.Callback callback = this.f5876e;
        if (callback != null) {
            callback.onWindowFocusChanged(z7);
        }
        Window.Callback callback2 = this.f5875d;
        if (callback2 != null) {
            callback2.onWindowFocusChanged(z7);
        }
    }

    @Override // android.view.Window.Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        Window.Callback callback2 = this.f5876e;
        if (callback2 != null) {
            callback2.onWindowStartingActionMode(callback);
        }
        Window.Callback callback3 = this.f5875d;
        if (callback3 != null) {
            return callback3.onWindowStartingActionMode(callback);
        }
        return null;
    }

    @Override // android.view.Window.Callback
    public boolean onSearchRequested(SearchEvent searchEvent) {
        Window.Callback callback = this.f5876e;
        if (callback != null) {
            callback.onSearchRequested(searchEvent);
        }
        Window.Callback callback2 = this.f5875d;
        if (callback2 != null) {
            return callback2.onSearchRequested(searchEvent);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i8) {
        Window.Callback callback2 = this.f5876e;
        if (callback2 != null) {
            callback2.onWindowStartingActionMode(callback, i8);
        }
        Window.Callback callback3 = this.f5875d;
        if (callback3 != null) {
            return callback3.onWindowStartingActionMode(callback, i8);
        }
        return null;
    }
}
