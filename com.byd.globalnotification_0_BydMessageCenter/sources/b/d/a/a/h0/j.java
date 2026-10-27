package b.d.a.a.h0;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;

/* JADX INFO: loaded from: classes.dex */
public class j implements View.OnTouchListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AutoCompleteTextView f1923a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ h f1924b;

    public j(h hVar, AutoCompleteTextView autoCompleteTextView) {
        this.f1924b = hVar;
        this.f1923a = autoCompleteTextView;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            if (this.f1924b.j()) {
                this.f1924b.i = false;
            }
            h.h(this.f1924b, this.f1923a);
        }
        return false;
    }
}
