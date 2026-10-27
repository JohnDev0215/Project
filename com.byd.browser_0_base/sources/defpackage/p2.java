package defpackage;

import android.R;
import android.app.Activity;
import android.view.View;
import defpackage.t41;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import org.chromium.base.b;

/* JADX INFO: compiled from: ActivityKeyboardVisibilityDelegate.java */
/* JADX INFO: loaded from: classes3.dex */
public class p2 extends t41 implements View.OnLayoutChangeListener {
    public boolean g;
    public final WeakReference<Activity> h;

    public p2(WeakReference<Activity> weakReference) {
        this.h = weakReference;
    }

    @Override // defpackage.t41
    public final void g() {
        Activity activity = this.h.get();
        if (activity == null) {
            return;
        }
        View viewFindViewById = activity.findViewById(R.id.content);
        this.g = f(activity, viewFindViewById);
        viewFindViewById.addOnLayoutChangeListener(this);
    }

    @Override // defpackage.t41
    public final void j() {
        Activity activity = this.h.get();
        if (activity == null) {
            return;
        }
        activity.findViewById(R.id.content).removeOnLayoutChangeListener(this);
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        boolean zF;
        Activity activity = this.h.get();
        if (activity == null || this.g == (zF = f(activity, view))) {
            return;
        }
        this.g = zF;
        Iterator<t41.b> it = this.e.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t41.b) aVar.next()).a(zF);
            }
        }
    }
}
