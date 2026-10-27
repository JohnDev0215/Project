package defpackage;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.l;
import defpackage.cq2;
import org.chromium.chrome.browser.feed.webfeed.e;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class jg implements View.OnTouchListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ jg(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                PropertyModel propertyModel = (PropertyModel) obj;
                if (motionEvent.getActionMasked() == 0) {
                    ((l) propertyModel.j(ig.f)).u((RecyclerView.a0) propertyModel.j(ig.g));
                }
                break;
            case 1:
                int i2 = e.n;
                view.performClick();
                ((GestureDetector) obj).onTouchEvent(motionEvent);
                break;
            default:
                cq2 cq2Var = (cq2) obj;
                View view2 = cq2Var.g;
                cq2.a aVar = cq2Var.c;
                view2.removeCallbacks(aVar);
                if (motionEvent.getActionMasked() == 3 || motionEvent.getActionMasked() == 1) {
                    cq2Var.h = true;
                    cq2Var.g.postDelayed(aVar, 30L);
                } else {
                    cq2Var.h = false;
                }
                break;
        }
        return true;
    }
}
