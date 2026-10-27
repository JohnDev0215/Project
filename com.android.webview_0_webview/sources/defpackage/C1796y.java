package defpackage;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.StateSet;

/* JADX INFO: renamed from: y, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1796y extends I {
    public C1194n1 K;
    public C1688w1 L;

    public C1796y(C1796y c1796y, D d, Resources resources) {
        super(c1796y, d, resources);
        if (c1796y != null) {
            this.K = c1796y.K;
            this.L = c1796y.L;
        } else {
            this.K = new C1194n1();
            this.L = new C1688w1(10);
        }
    }

    public static long i(int i, int i2) {
        return ((long) i2) | (((long) i) << 32);
    }

    @Override // defpackage.G
    public void e() {
        this.K = this.K.clone();
        this.L = this.L.clone();
    }

    public int h(int i, int i2, Drawable drawable, boolean z) {
        int iA = super.a(drawable);
        long jI = i(i, i2);
        long j = z ? 8589934592L : 0L;
        long j2 = iA;
        this.K.a(jI, Long.valueOf(j2 | j));
        if (z) {
            this.K.a(i(i2, i), Long.valueOf(4294967296L | j2 | j));
        }
        return iA;
    }

    public int j(int i) {
        if (i < 0) {
            return 0;
        }
        return ((Integer) this.L.e(i, 0)).intValue();
    }

    public int k(int[] iArr) {
        int iG = super.g(iArr);
        return iG >= 0 ? iG : super.g(StateSet.WILD_CARD);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable() {
        return new D(this, null);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable(Resources resources) {
        return new D(this, resources);
    }
}
