package g2;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Layout;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: renamed from: g2.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1289b {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final String f14769A;
    public static final String B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final String f14770C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final String f14771D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final String f14772E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final String f14773F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public static final String f14774G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public static final String f14775H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public static final String f14776I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final String f14777J;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final String f14778r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final String f14779s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final String f14780t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final String f14781u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final String f14782v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final String f14783w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final String f14784x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final String f14785y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final String f14786z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CharSequence f14787a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Layout.Alignment f14788b;
    public final Layout.Alignment c;
    public final Bitmap d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f14789e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f14790f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f14791g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f14792h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f14793i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float f14794j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final float f14795k;
    public final boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f14796m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f14797n;
    public final float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f14798p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final float f14799q;

    static {
        new C1289b("", null, null, null, -3.4028235E38f, Integer.MIN_VALUE, Integer.MIN_VALUE, -3.4028235E38f, Integer.MIN_VALUE, Integer.MIN_VALUE, -3.4028235E38f, -3.4028235E38f, -3.4028235E38f, false, -16777216, Integer.MIN_VALUE, 0.0f);
        int i7 = AbstractC1343A.f15195a;
        f14778r = Integer.toString(0, 36);
        f14779s = Integer.toString(17, 36);
        f14780t = Integer.toString(1, 36);
        f14781u = Integer.toString(2, 36);
        f14782v = Integer.toString(3, 36);
        f14783w = Integer.toString(18, 36);
        f14784x = Integer.toString(4, 36);
        f14785y = Integer.toString(5, 36);
        f14786z = Integer.toString(6, 36);
        f14769A = Integer.toString(7, 36);
        B = Integer.toString(8, 36);
        f14770C = Integer.toString(9, 36);
        f14771D = Integer.toString(10, 36);
        f14772E = Integer.toString(11, 36);
        f14773F = Integer.toString(12, 36);
        f14774G = Integer.toString(13, 36);
        f14775H = Integer.toString(14, 36);
        f14776I = Integer.toString(15, 36);
        f14777J = Integer.toString(16, 36);
    }

    public C1289b(CharSequence charSequence, Layout.Alignment alignment, Layout.Alignment alignment2, Bitmap bitmap, float f10, int i7, int i10, float f11, int i11, int i12, float f12, float f13, float f14, boolean z6, int i13, int i14, float f15) {
        if (charSequence == null) {
            bitmap.getClass();
        } else {
            AbstractC1346c.c(bitmap == null);
        }
        if (charSequence instanceof Spanned) {
            this.f14787a = SpannedString.valueOf(charSequence);
        } else if (charSequence != null) {
            this.f14787a = charSequence.toString();
        } else {
            this.f14787a = null;
        }
        this.f14788b = alignment;
        this.c = alignment2;
        this.d = bitmap;
        this.f14789e = f10;
        this.f14790f = i7;
        this.f14791g = i10;
        this.f14792h = f11;
        this.f14793i = i11;
        this.f14794j = f13;
        this.f14795k = f14;
        this.l = z6;
        this.f14796m = i13;
        this.f14797n = i12;
        this.o = f12;
        this.f14798p = i14;
        this.f14799q = f15;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0159  */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r1v35 */
    /* JADX WARN: Type inference failed for: r1v36, types: [android.text.Spannable, android.text.SpannableString] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static g2.C1289b b(android.os.Bundle r29) {
        /*
            Method dump skipped, instruction units count: 457
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g2.C1289b.b(android.os.Bundle):g2.b");
    }

    public final C1288a a() {
        C1288a c1288a = new C1288a();
        c1288a.f14756a = this.f14787a;
        c1288a.f14757b = this.d;
        c1288a.c = this.f14788b;
        c1288a.d = this.c;
        c1288a.f14758e = this.f14789e;
        c1288a.f14759f = this.f14790f;
        c1288a.f14760g = this.f14791g;
        c1288a.f14761h = this.f14792h;
        c1288a.f14762i = this.f14793i;
        c1288a.f14763j = this.f14797n;
        c1288a.f14764k = this.o;
        c1288a.l = this.f14794j;
        c1288a.f14765m = this.f14795k;
        c1288a.f14766n = this.l;
        c1288a.o = this.f14796m;
        c1288a.f14767p = this.f14798p;
        c1288a.f14768q = this.f14799q;
        return c1288a;
    }

    public final Bundle c() {
        Bundle bundle = new Bundle();
        CharSequence charSequence = this.f14787a;
        if (charSequence != null) {
            bundle.putCharSequence(f14778r, charSequence);
            if (charSequence instanceof Spanned) {
                Spanned spanned = (Spanned) charSequence;
                String str = AbstractC1291d.f14803a;
                ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
                for (g gVar : (g[]) spanned.getSpans(0, spanned.length(), g.class)) {
                    gVar.getClass();
                    Bundle bundle2 = new Bundle();
                    bundle2.putString(g.c, gVar.f14806a);
                    bundle2.putInt(g.d, gVar.f14807b);
                    arrayList.add(AbstractC1291d.a(spanned, gVar, 1, bundle2));
                }
                for (h hVar : (h[]) spanned.getSpans(0, spanned.length(), h.class)) {
                    hVar.getClass();
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt(h.d, hVar.f14810a);
                    bundle3.putInt(h.f14808e, hVar.f14811b);
                    bundle3.putInt(h.f14809f, hVar.c);
                    arrayList.add(AbstractC1291d.a(spanned, hVar, 2, bundle3));
                }
                for (e eVar : (e[]) spanned.getSpans(0, spanned.length(), e.class)) {
                    arrayList.add(AbstractC1291d.a(spanned, eVar, 3, null));
                }
                for (i iVar : (i[]) spanned.getSpans(0, spanned.length(), i.class)) {
                    iVar.getClass();
                    Bundle bundle4 = new Bundle();
                    bundle4.putString(i.f14812b, iVar.f14813a);
                    arrayList.add(AbstractC1291d.a(spanned, iVar, 4, bundle4));
                }
                if (!arrayList.isEmpty()) {
                    bundle.putParcelableArrayList(f14779s, arrayList);
                }
            }
        }
        bundle.putSerializable(f14780t, this.f14788b);
        bundle.putSerializable(f14781u, this.c);
        bundle.putFloat(f14784x, this.f14789e);
        bundle.putInt(f14785y, this.f14790f);
        bundle.putInt(f14786z, this.f14791g);
        bundle.putFloat(f14769A, this.f14792h);
        bundle.putInt(B, this.f14793i);
        bundle.putInt(f14770C, this.f14797n);
        bundle.putFloat(f14771D, this.o);
        bundle.putFloat(f14772E, this.f14794j);
        bundle.putFloat(f14773F, this.f14795k);
        bundle.putBoolean(f14775H, this.l);
        bundle.putInt(f14774G, this.f14796m);
        bundle.putInt(f14776I, this.f14798p);
        bundle.putFloat(f14777J, this.f14799q);
        return bundle;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1289b.class != obj.getClass()) {
            return false;
        }
        C1289b c1289b = (C1289b) obj;
        if (TextUtils.equals(this.f14787a, c1289b.f14787a) && this.f14788b == c1289b.f14788b && this.c == c1289b.c) {
            Bitmap bitmap = c1289b.d;
            Bitmap bitmap2 = this.d;
            if (bitmap2 != null ? !(bitmap == null || !bitmap2.sameAs(bitmap)) : bitmap == null) {
                if (this.f14789e == c1289b.f14789e && this.f14790f == c1289b.f14790f && this.f14791g == c1289b.f14791g && this.f14792h == c1289b.f14792h && this.f14793i == c1289b.f14793i && this.f14794j == c1289b.f14794j && this.f14795k == c1289b.f14795k && this.l == c1289b.l && this.f14796m == c1289b.f14796m && this.f14797n == c1289b.f14797n && this.o == c1289b.o && this.f14798p == c1289b.f14798p && this.f14799q == c1289b.f14799q) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f14787a, this.f14788b, this.c, this.d, Float.valueOf(this.f14789e), Integer.valueOf(this.f14790f), Integer.valueOf(this.f14791g), Float.valueOf(this.f14792h), Integer.valueOf(this.f14793i), Float.valueOf(this.f14794j), Float.valueOf(this.f14795k), Boolean.valueOf(this.l), Integer.valueOf(this.f14796m), Integer.valueOf(this.f14797n), Float.valueOf(this.o), Integer.valueOf(this.f14798p), Float.valueOf(this.f14799q)});
    }
}
