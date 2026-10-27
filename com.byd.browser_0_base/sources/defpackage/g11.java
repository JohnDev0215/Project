package defpackage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.Iterator;
import org.chromium.base.Callback;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.f;

/* JADX INFO: compiled from: InstanceSwitcherCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g11 {

    @SuppressLint({"StaticFieldLeak"})
    public static g11 n;
    public final Context a;
    public final Callback<b11> b;
    public final Callback<b11> c;
    public final Runnable d;
    public final bi1 e;
    public final ya1 f;
    public final th3 g;
    public final View h;
    public final Drawable i;
    public PropertyModel j;
    public b11 k;
    public PropertyModel l;
    public boolean m;

    public g11(Activity activity, bi1 bi1Var, LargeIconBridge largeIconBridge, lj1 lj1Var, ko0 ko0Var, zc0 zc0Var) {
        ya1 ya1Var = new ya1();
        this.f = ya1Var;
        this.a = activity;
        this.e = bi1Var;
        this.b = lj1Var;
        this.c = ko0Var;
        this.g = new th3(activity, largeIconBridge);
        this.d = zc0Var;
        this.i = uh3.e(activity, R$drawable.ic_arrow_back_24dp, R$color.default_icon_color_tint_list);
        f fVar = new f(ya1Var);
        fVar.a(0, new za1() { // from class: c11
            @Override // defpackage.za1
            public final View a(ViewGroup viewGroup) {
                return LayoutInflater.from(this.a.a).inflate(R$layout.instance_switcher_item, (ViewGroup) null);
            }
        }, new zf(10));
        fVar.a(1, new uu0(1, this), new bg(12));
        View viewInflate = LayoutInflater.from(activity).inflate(R$layout.instance_switcher_dialog, (ViewGroup) null);
        this.h = viewInflate;
        ((ListView) viewInflate.findViewById(R$id.list_view)).setAdapter((ListAdapter) fVar);
    }

    public final void a(boolean z) {
        if (this.m && z) {
            return;
        }
        this.l.l(h11.b, z);
        if (z) {
            this.l.p(h11.g, new by1(13, this));
        }
        this.m = z;
    }

    public final void b(b11 b11Var) {
        int i = b11Var.a;
        ya1 ya1Var = this.f;
        Iterator<xa1> it = ya1Var.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            xa1 next = it.next();
            if (next.b.h(h11.f) == i) {
                ya1Var.x(next);
                break;
            }
        }
        this.c.onResult(b11Var);
        n3.X0("Android.WindowManager.CloseWindow");
        a(true);
    }
}
