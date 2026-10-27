package a.b.p;

import android.R;
import android.view.View;
import android.view.Window;

/* JADX INFO: loaded from: classes.dex */
public class y0 implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a.b.o.i.a f402a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ z0 f403b;

    public y0(z0 z0Var) {
        this.f403b = z0Var;
        this.f402a = new a.b.o.i.a(this.f403b.f408a.getContext(), 0, R.id.home, 0, this.f403b.i);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        z0 z0Var = this.f403b;
        Window.Callback callback = z0Var.l;
        if (callback == null || !z0Var.m) {
            return;
        }
        callback.onMenuItemSelected(0, this.f402a);
    }
}
