package p;

import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class m8 implements Window.Callback {
    public final Window.Callback c;
    public boolean e;
    public boolean k;
    public boolean l;
    public final /* synthetic */ s8 m;

    public m8(s8 s8Var, Window.Callback callback) {
        this.m = s8Var;
        if (callback == null) {
            throw new IllegalArgumentException("Window callback may not be null");
        }
        this.c = callback;
    }

    public final void a(Window.Callback callback) {
        try {
            this.e = true;
            callback.onContentChanged();
        } finally {
            this.e = false;
        }
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final void onActionModeFinished(ActionMode actionMode) {
        this.c.onActionModeFinished(actionMode);
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final void onActionModeStarted(ActionMode actionMode) {
        this.c.onActionModeStarted(actionMode);
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final void onAttachedToWindow() {
        this.c.onAttachedToWindow();
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return this.c.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        boolean z = this.k;
        Window.Callback callback = this.c;
        return z ? callback.dispatchKeyEvent(keyEvent) : this.m.s(keyEvent) || callback.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        nw2 nw2Var;
        si1 si1Var;
        if (this.c.dispatchKeyShortcutEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        s8 s8Var = this.m;
        s8Var.y();
        ow2 ow2Var = s8Var.w;
        if (ow2Var != null && (nw2Var = ow2Var.q) != null && (si1Var = nw2Var.l) != null) {
            si1Var.setQwertyMode(KeyCharacterMap.load(keyEvent.getDeviceId()).getKeyboardType() != 1);
            if (si1Var.performShortcut(keyCode, keyEvent, 0)) {
                return true;
            }
        }
        r8 r8Var = s8Var.U;
        if (r8Var != null && s8Var.D(r8Var, keyEvent.getKeyCode(), keyEvent)) {
            r8 r8Var2 = s8Var.U;
            if (r8Var2 == null) {
                return true;
            }
            r8Var2.l = true;
            return true;
        }
        if (s8Var.U == null) {
            r8 r8VarX = s8Var.x(0);
            s8Var.E(r8VarX, keyEvent);
            boolean zD = s8Var.D(r8VarX, keyEvent.getKeyCode(), keyEvent);
            r8VarX.k = false;
            if (zD) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return this.c.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.c.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return this.c.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public final void onDetachedFromWindow() {
        this.c.onDetachedFromWindow();
    }

    public final boolean f(int i, Menu menu) {
        return this.c.onMenuOpened(i, menu);
    }

    public final void g(int i, Menu menu) {
        this.c.onPanelClosed(i, menu);
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public final void onPointerCaptureChanged(boolean z) {
        lw2.a(this.c, z);
    }

    public final void i(List list, Menu menu, int i) {
        kw2.a(this.c, list, menu, i);
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public final void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        this.c.onWindowAttributesChanged(layoutParams);
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final void onWindowFocusChanged(boolean z) {
        this.c.onWindowFocusChanged(z);
    }

    @Override // android.view.Window.Callback
    public final void onContentChanged() {
        if (this.e) {
            this.c.onContentChanged();
        }
    }

    @Override // android.view.Window.Callback
    public final boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0 || (menu instanceof si1)) {
            return this.c.onCreatePanelMenu(i, menu);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public final View onCreatePanelView(int i) {
        return this.c.onCreatePanelView(i);
    }

    @Override // android.view.Window.Callback
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        return this.c.onMenuItemSelected(i, menuItem);
    }

    @Override // android.view.Window.Callback
    public final boolean onMenuOpened(int i, Menu menu) {
        f(i, menu);
        s8 s8Var = this.m;
        if (i == 108) {
            s8Var.y();
            ow2 ow2Var = s8Var.w;
            if (ow2Var != null && true != ow2Var.t) {
                ow2Var.t = true;
                ArrayList arrayList = ow2Var.u;
                if (arrayList.size() > 0) {
                    u4.z(arrayList.get(0));
                    throw null;
                }
            }
        } else {
            s8Var.getClass();
        }
        return true;
    }

    @Override // android.view.Window.Callback
    public final void onPanelClosed(int i, Menu menu) {
        if (this.l) {
            this.c.onPanelClosed(i, menu);
            return;
        }
        g(i, menu);
        s8 s8Var = this.m;
        if (i != 108) {
            if (i != 0) {
                s8Var.getClass();
                return;
            }
            r8 r8VarX = s8Var.x(i);
            if (r8VarX.m) {
                s8Var.q(r8VarX, false);
                return;
            }
            return;
        }
        s8Var.y();
        ow2 ow2Var = s8Var.w;
        if (ow2Var == null || !ow2Var.t) {
            return;
        }
        ow2Var.t = false;
        ArrayList arrayList = ow2Var.u;
        if (arrayList.size() <= 0) {
            return;
        }
        u4.z(arrayList.get(0));
        throw null;
    }

    @Override // android.view.Window.Callback
    public final boolean onPreparePanel(int i, View view, Menu menu) {
        si1 si1Var = menu instanceof si1 ? (si1) menu : null;
        if (i == 0 && si1Var == null) {
            return false;
        }
        if (si1Var != null) {
            si1Var.x = true;
        }
        boolean zOnPreparePanel = this.c.onPreparePanel(i, view, menu);
        if (si1Var != null) {
            si1Var.x = false;
        }
        return zOnPreparePanel;
    }

    @Override // android.view.Window.Callback
    public final void onProvideKeyboardShortcuts(List list, Menu menu, int i) {
        si1 si1Var = this.m.x(0).h;
        if (si1Var != null) {
            i(list, si1Var, i);
        } else {
            i(list, menu, i);
        }
    }

    @Override // android.view.Window.Callback
    public final boolean onSearchRequested(SearchEvent searchEvent) {
        return jw2.a(this.c, searchEvent);
    }

    @Override // android.view.Window.Callback
    public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.Window.Callback
    public final boolean onSearchRequested() {
        return this.c.onSearchRequested();
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01e0  */
    @Override // android.view.Window.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback r10, int r11) {
        /*
            Method dump skipped, instruction units count: 522
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.m8.onWindowStartingActionMode(android.view.ActionMode$Callback, int):android.view.ActionMode");
    }
}
