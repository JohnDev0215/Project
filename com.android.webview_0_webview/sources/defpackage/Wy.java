package defpackage;

import java.util.Comparator;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Wy implements Comparator {
    public Wy(Uy uy) {
    }

    public static int b(Vy vy, Vy vy2) {
        if (vy.d && !vy2.d) {
            return -1;
        }
        if (vy.d || !vy2.d) {
            return Long.signum(vy.c - vy2.c);
        }
        return 1;
    }

    @Override // java.util.Comparator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(Vy vy, Vy vy2) {
        boolean z = (vy.b && vy.c == 0) || vy.e == 2;
        boolean z2 = (vy2.b && vy2.c == 0) || vy2.e == 2;
        if (z && z2) {
            return b(vy, vy2);
        }
        if (z && !z2) {
            return -1;
        }
        if (!z && z2) {
            return 1;
        }
        boolean z3 = (vy.b && vy.c > 0 && vy.d) || vy.e == 1;
        boolean z4 = (vy2.b && vy2.c > 0 && vy2.d) || vy2.e == 1;
        if (z3 && z4) {
            return b(vy, vy2);
        }
        if (z3 && !z4) {
            return -1;
        }
        if (!z3 && z4) {
            return 1;
        }
        if (vy.b && vy2.b) {
            return b(vy, vy2);
        }
        if (!vy.b || vy2.b) {
            return (vy.b || !vy2.b) ? 0 : 1;
        }
        return -1;
    }
}
