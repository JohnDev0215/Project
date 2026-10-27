package p;

import android.view.View;
import android.view.ViewGroup;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class cx0 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ int c = 1;
    public final /* synthetic */ androidx.fragment.app.b e;
    public final /* synthetic */ Object k;

    public cx0(dx0 dx0Var, androidx.fragment.app.b bVar) {
        this.k = dx0Var;
        this.e = bVar;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        int i = this.c;
        Object obj = this.k;
        switch (i) {
            case 0:
                androidx.fragment.app.b bVar = this.e;
                ww0 ww0Var = bVar.c;
                bVar.k();
                df2.f((ViewGroup) ww0Var.M.getParent(), ((dx0) obj).c).e();
                break;
            default:
                View view2 = (View) obj;
                view2.removeOnAttachStateChangeListener(this);
                WeakHashMap weakHashMap = zt2.a;
                mt2.c(view2);
                break;
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
    }

    public cx0(androidx.fragment.app.b bVar, View view) {
        this.e = bVar;
        this.k = view;
    }
}
