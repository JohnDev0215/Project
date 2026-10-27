package com.android.launcher3;

import android.view.KeyEvent;
import android.view.View;

/* JADX INFO: compiled from: FocusHelper.java */
/* JADX INFO: loaded from: classes4.dex */
class FullscreenKeyEventListener implements View.OnKeyListener {
    FullscreenKeyEventListener() {
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == 21 || keyCode == 22 || keyCode == 93 || keyCode == 92) {
            return FocusHelper.handleIconKeyEvent(v, keyCode, event);
        }
        return false;
    }
}
