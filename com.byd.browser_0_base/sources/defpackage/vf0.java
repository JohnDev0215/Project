package defpackage;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

/* JADX INFO: compiled from: EditorDropdownField.java */
/* JADX INFO: loaded from: classes.dex */
public final class vf0 implements View.OnTouchListener {
    public final /* synthetic */ wf0 e;

    public vf0(wf0 wf0Var) {
        this.e = wf0Var;
    }

    @Override // android.view.View.OnTouchListener
    @SuppressLint({"ClickableViewAccessibility"})
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        wf0 wf0Var = this.e;
        wf0Var.getClass();
        t41 t41Var = t41.f;
        Spinner spinner = wf0Var.h;
        t41Var.e(spinner);
        ViewGroup viewGroup = (ViewGroup) spinner.getParent();
        if (viewGroup != null) {
            viewGroup.requestChildFocus(spinner, spinner);
        }
        spinner.sendAccessibilityEvent(8);
        return false;
    }
}
