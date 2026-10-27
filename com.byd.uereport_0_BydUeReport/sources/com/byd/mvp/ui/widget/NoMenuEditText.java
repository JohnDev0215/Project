package com.byd.mvp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

/* JADX INFO: loaded from: classes2.dex */
public class NoMenuEditText extends BydEditText {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b f2893d;

    public class a implements ActionMode.Callback {
        public a() {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            x3.a.a("NoMenuEditText", "---onActionItemClicked--mode = " + actionMode);
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            x3.a.a("NoMenuEditText", "---onCreateActionMode--mode = " + actionMode);
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            x3.a.a("NoMenuEditText", "---onPrepareActionMode--mode = " + actionMode);
            return false;
        }
    }

    public interface b {
        void onBackPress();
    }

    public NoMenuEditText(Context context) {
        super(context);
    }

    @Override // com.byd.mvp.ui.widget.BydEditText
    public void a(Context context) {
        super.a(context);
        setLongClickable(false);
        setTextIsSelectable(false);
        a aVar = new a();
        setCustomSelectionActionModeCallback(aVar);
        setCustomInsertionActionModeCallback(aVar);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onKeyPreIme(int i8, KeyEvent keyEvent) {
        if (i8 == 4 && keyEvent.getAction() == 1) {
            x3.a.a("NoMenuEditText", "---onKeyPreIme--on back event.");
            clearFocus();
            b bVar = this.f2893d;
            if (bVar != null) {
                bVar.onBackPress();
            }
        }
        return super.onKeyPreIme(i8, keyEvent);
    }

    @Override // android.widget.EditText, android.widget.TextView
    public boolean onTextContextMenuItem(int i8) {
        x3.a.a("NoMenuEditText", "---onTextContextMenuItem--id = " + i8);
        return true;
    }

    public void setOnBackEventListener(b bVar) {
        this.f2893d = bVar;
    }

    public NoMenuEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NoMenuEditText(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
    }
}
