package com.android.launcher3.widget;

import android.appwidget.AppWidgetHostView;
import android.content.Context;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewDebug;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public abstract class NavigableAppWidgetHostView extends AppWidgetHostView {

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private boolean mChildrenFocused;

    protected abstract boolean shouldAllowDirectClick();

    public NavigableAppWidgetHostView(Context context) {
        super(context);
    }

    @Override // android.view.ViewGroup
    public int getDescendantFocusability() {
        return this.mChildrenFocused ? 131072 : 393216;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (this.mChildrenFocused && event.getKeyCode() == 111 && event.getAction() == 1) {
            this.mChildrenFocused = false;
            requestFocus();
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!this.mChildrenFocused && keyCode == 66) {
            event.startTracking();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (event.isTracking() && !this.mChildrenFocused && keyCode == 66) {
            this.mChildrenFocused = true;
            ArrayList<View> focusables = getFocusables(2);
            focusables.remove(this);
            int size = focusables.size();
            if (size == 0) {
                this.mChildrenFocused = false;
            } else {
                if (size == 1 && shouldAllowDirectClick()) {
                    focusables.get(0).performClick();
                    this.mChildrenFocused = false;
                    return true;
                }
                focusables.get(0).requestFocus();
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        if (gainFocus) {
            this.mChildrenFocused = false;
            dispatchChildFocus(false);
        }
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View child, View focused) {
        super.requestChildFocus(child, focused);
        dispatchChildFocus(this.mChildrenFocused && focused != null);
        if (focused != null) {
            focused.setFocusableInTouchMode(false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void clearChildFocus(View child) {
        super.clearChildFocus(child);
        dispatchChildFocus(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchUnhandledMove(View focused, int direction) {
        return this.mChildrenFocused;
    }

    private void dispatchChildFocus(boolean childIsFocused) {
        setSelected(childIsFocused);
    }
}
