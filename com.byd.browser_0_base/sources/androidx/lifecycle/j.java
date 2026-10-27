package androidx.lifecycle;

import android.os.Handler;
import androidx.lifecycle.ReportFragment;
import androidx.lifecycle.d;
import defpackage.b71;
import defpackage.i21;
import defpackage.wv;

/* JADX INFO: compiled from: ProcessLifecycleOwner.kt */
/* JADX INFO: loaded from: classes.dex */
public final class j implements b71 {
    public static final j l = new j();
    public int e;
    public int f;
    public Handler i;
    public boolean g = true;
    public boolean h = true;
    public final g j = new g(this);
    public final wv k = new wv(5, this);

    public j() {
        new a();
    }

    public final void a() {
        int i = this.f + 1;
        this.f = i;
        if (i == 1) {
            if (this.g) {
                this.j.e(d.a.ON_RESUME);
                this.g = false;
            } else {
                Handler handler = this.i;
                i21.b(handler);
                handler.removeCallbacks(this.k);
            }
        }
    }

    @Override // defpackage.b71
    public final g j1() {
        return this.j;
    }

    /* JADX INFO: compiled from: ProcessLifecycleOwner.kt */
    public static final class a implements ReportFragment.a {
        public a() {
        }

        @Override // androidx.lifecycle.ReportFragment.a
        public final void a() {
            j jVar = j.this;
            int i = jVar.e + 1;
            jVar.e = i;
            if (i == 1 && jVar.h) {
                jVar.j.e(d.a.ON_START);
                jVar.h = false;
            }
        }

        @Override // androidx.lifecycle.ReportFragment.a
        public final void onResume() {
            j.this.a();
        }

        @Override // androidx.lifecycle.ReportFragment.a
        public final void onCreate() {
        }
    }
}
