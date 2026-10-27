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

/* JADX INFO: compiled from: WindowCallback.java */
/* JADX INFO: loaded from: classes2.dex */
public interface e extends Window.Callback {
    @Override // android.view.Window.Callback
    default boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.Window.Callback
    default boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return false;
    }

    @Override // android.view.Window.Callback
    default boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        return false;
    }

    @Override // android.view.Window.Callback
    default boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // android.view.Window.Callback
    default boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.Window.Callback
    default boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.Window.Callback
    default void onActionModeFinished(ActionMode actionMode) {
    }

    @Override // android.view.Window.Callback
    default void onActionModeStarted(ActionMode actionMode) {
    }

    @Override // android.view.Window.Callback
    default void onAttachedToWindow() {
    }

    @Override // android.view.Window.Callback
    default void onContentChanged() {
    }

    @Override // android.view.Window.Callback
    default boolean onCreatePanelMenu(int i8, Menu menu) {
        return false;
    }

    @Override // android.view.Window.Callback
    default View onCreatePanelView(int i8) {
        return null;
    }

    @Override // android.view.Window.Callback
    default void onDetachedFromWindow() {
    }

    @Override // android.view.Window.Callback
    default boolean onMenuItemSelected(int i8, MenuItem menuItem) {
        return false;
    }

    @Override // android.view.Window.Callback
    default boolean onMenuOpened(int i8, Menu menu) {
        return false;
    }

    @Override // android.view.Window.Callback
    default void onPanelClosed(int i8, Menu menu) {
    }

    @Override // android.view.Window.Callback
    default boolean onPreparePanel(int i8, View view, Menu menu) {
        return false;
    }

    @Override // android.view.Window.Callback
    default boolean onSearchRequested() {
        return false;
    }

    @Override // android.view.Window.Callback
    default boolean onSearchRequested(SearchEvent searchEvent) {
        return false;
    }

    @Override // android.view.Window.Callback
    default void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
    }

    @Override // android.view.Window.Callback
    default void onWindowFocusChanged(boolean z7) {
    }

    @Override // android.view.Window.Callback
    default ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.Window.Callback
    default ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i8) {
        return null;
    }
}
