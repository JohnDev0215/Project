package p;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;

/* JADX INFO: loaded from: classes.dex */
public final class nb0 implements View.OnTouchListener {
    public final /* synthetic */ AutoCompleteTextView c;
    public final /* synthetic */ pb0 e;

    public nb0(pb0 pb0Var, AutoCompleteTextView autoCompleteTextView) {
        this.e = pb0Var;
        this.c = autoCompleteTextView;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            pb0 pb0Var = this.e;
            pb0Var.getClass();
            long jCurrentTimeMillis = System.currentTimeMillis() - pb0Var.l;
            if (jCurrentTimeMillis < 0 || jCurrentTimeMillis > 300) {
                pb0Var.j = false;
            }
            pb0.d(pb0Var, this.c);
        }
        return false;
    }
}
