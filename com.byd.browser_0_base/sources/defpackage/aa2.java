package defpackage;

import android.content.ComponentName;
import android.content.Intent;
import android.os.SystemClock;
import java.util.HashSet;

/* JADX INFO: compiled from: RedirectHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public final class aa2 {
    public b a;
    public boolean b;
    public c c;
    public int d = -2;
    public long e;

    /* JADX INFO: compiled from: RedirectHandler.java */
    public class a {
        public final boolean a;
        public final boolean b;
        public final boolean c;
        public boolean d;

        public a(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
            this.a = z;
            this.d = z2;
            this.b = z3;
            this.c = z5;
        }
    }

    /* JADX INFO: compiled from: RedirectHandler.java */
    public static class b {
        public final Intent a;
        public final boolean b;
        public final boolean c;
        public final boolean d;
        public final HashSet<ComponentName> e = new HashSet<>();

        public b(Intent intent, boolean z, boolean z2, boolean z3) {
            this.a = intent;
            this.c = z;
            this.b = z2;
            this.d = z3;
        }
    }

    /* JADX INFO: compiled from: RedirectHandler.java */
    public class c {
        public final boolean a;
        public boolean b = true;
        public boolean c;
        public final long d;
        public boolean e;
        public final a f;

        public c(aa2 aa2Var, boolean z, a aVar) {
            aa2Var.getClass();
            this.d = SystemClock.elapsedRealtime();
            this.a = z;
            this.f = aVar;
        }
    }

    public final void a(int i, boolean z, boolean z2, long j, int i2, boolean z3, boolean z4) {
        this.e = j;
        boolean z5 = z || (z4 && !z2);
        c cVar = this.c;
        if (cVar == null || !z5) {
            int i3 = i & 255;
            boolean z6 = ((134217728 & i) != 0) && (this.a != null || this.b);
            boolean z7 = i3 == 8;
            boolean z8 = i3 == 1;
            if (!z6) {
                this.a = null;
                this.b = false;
            }
            this.c = new c(this, !z3, new a(z4, z2, z7, z8, z6));
            this.d = i2;
        } else {
            cVar.b = false;
        }
        if ((i & 16777216) != 0) {
            this.c.e = true;
        }
    }
}
