package a5;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;

/* JADX INFO: loaded from: classes.dex */
public final class m implements View.OnTouchListener {
    public final /* synthetic */ AutoCompleteTextView l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ o f11158m;

    public m(o oVar, AutoCompleteTextView autoCompleteTextView) {
        this.f11158m = oVar;
        this.l = autoCompleteTextView;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            o oVar = this.f11158m;
            oVar.getClass();
            long jCurrentTimeMillis = System.currentTimeMillis() - oVar.f11166k;
            if (jCurrentTimeMillis < 0 || jCurrentTimeMillis > 300) {
                oVar.f11164i = false;
            }
            o.d(oVar, this.l);
        }
        return false;
    }
}
