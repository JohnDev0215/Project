package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

/* JADX INFO: loaded from: classes2.dex */
public class NoMenuEditText extends BydEditText {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f3438b;

    public class a implements ActionMode.Callback {
        public a() {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            b.b.m.c.a.a("NoMenuEditText", "---onActionItemClicked--mode = " + actionMode);
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            b.b.m.c.a.a("NoMenuEditText", "---onCreateActionMode--mode = " + actionMode);
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            b.b.m.c.a.a("NoMenuEditText", "---onPrepareActionMode--mode = " + actionMode);
            return false;
        }
    }

    public interface b {
        void onBackPress();
    }

    public NoMenuEditText(Context context) {
        super(context);
        b();
    }

    public final void b() {
        setLongClickable(false);
        setTextIsSelectable(false);
        a aVar = new a();
        setCustomSelectionActionModeCallback(aVar);
        setCustomInsertionActionModeCallback(aVar);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getAction() == 1) {
            b.b.m.c.a.a("NoMenuEditText", "---onKeyPreIme--on back event.");
            b bVar = this.f3438b;
            if (bVar != null) {
                bVar.onBackPress();
            }
        }
        return super.onKeyPreIme(i, keyEvent);
    }

    @Override // android.widget.EditText, android.widget.TextView
    public boolean onTextContextMenuItem(int i) {
        b.b.m.c.a.a("NoMenuEditText", "---onTextContextMenuItem--id = " + i);
        return true;
    }

    public void setOnBackEventListener(b bVar) {
        this.f3438b = bVar;
    }

    public NoMenuEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public NoMenuEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }
}
