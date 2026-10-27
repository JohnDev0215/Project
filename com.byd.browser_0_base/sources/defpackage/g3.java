package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.b;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.base.c;
import org.chromium.ui.base.d;

/* JADX INFO: compiled from: ActivityWindowAndroid.java */
/* JADX INFO: loaded from: classes3.dex */
public class g3 extends WindowAndroid implements ApplicationStatus.c, ApplicationStatus.g {
    public final boolean B;
    public nx0<Activity> C;

    public g3(Context context, boolean z, c cVar) {
        this(context, z, new l2(new WeakReference(xz.a(context))), new p2(new WeakReference(xz.a(context))), cVar);
    }

    @Override // org.chromium.base.ApplicationStatus.c
    public final void d(Activity activity, int i) {
        if (i == 5) {
            long j = this.g;
            if (j == 0) {
                return;
            }
            N.MMLuxHp6(j, this);
            return;
        }
        if (i == 2) {
            long j2 = this.g;
            if (j2 == 0) {
                return;
            }
            N.MbyUPhMo(j2, this);
            return;
        }
        b<WindowAndroid.b> bVar = this.v;
        if (i == 4) {
            Iterator<WindowAndroid.b> it = bVar.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    return;
                } else {
                    ((WindowAndroid.b) aVar.next()).c();
                }
            }
        } else {
            if (i != 3) {
                if (i == 6) {
                    Iterator<WindowAndroid.b> it2 = bVar.iterator();
                    while (true) {
                        b.a aVar2 = (b.a) it2;
                        if (!aVar2.hasNext()) {
                            break;
                        } else {
                            ((WindowAndroid.b) aVar2.next()).e();
                        }
                    }
                    b<ApplicationStatus.g> bVar2 = ApplicationStatus.g;
                    if (bVar2 == null) {
                        return;
                    }
                    bVar2.c(this);
                    return;
                }
                return;
            }
            Iterator<WindowAndroid.b> it3 = bVar.iterator();
            while (true) {
                b.a aVar3 = (b.a) it3;
                if (!aVar3.hasNext()) {
                    return;
                } else {
                    ((WindowAndroid.b) aVar3.next()).b();
                }
            }
        }
    }

    @Override // org.chromium.base.ApplicationStatus.g
    public final void e(Activity activity, boolean z) {
        if (k().get() == activity) {
            this.t = z;
            if (z) {
                j(this.s);
            } else {
                j(0.0f);
            }
        }
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public final nx0 k() {
        if (this.C == null) {
            this.C = new nx0<>(xz.a(this.i.get()));
        }
        return this.C;
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public final int l() {
        if (this.B) {
            return ApplicationStatus.c((Activity) k().get());
        }
        return 6;
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public final t41 m() {
        return (p2) this.f;
    }

    public g3(Context context, vn2 vn2Var, d dVar) {
        this(context, true, new l2(new WeakReference(xz.a(context))), vn2Var, dVar);
    }

    public g3(Context context, boolean z, l2 l2Var, p2 p2Var, c cVar) {
        super(context, cVar);
        Activity activityA = xz.a(context);
        if (activityA != null) {
            this.B = z;
            if (z) {
                ApplicationStatus.f(this, activityA);
                ApplicationStatus.h(this);
            }
            this.f = p2Var;
            t41.f = p2Var;
            this.p = l2Var;
            return;
        }
        throw new IllegalArgumentException("Context is not and does not wrap an Activity");
    }
}
