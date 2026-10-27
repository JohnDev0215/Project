package K3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import c9.u;
import h2.AbstractC1347d;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f3565a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Bitmap.Config f3566b;
    public final ColorSpace c;
    public final L3.g d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final L3.f f3567e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f3568f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f3569g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f3570h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f3571i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final u f3572j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final q f3573k;
    public final o l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final b f3574m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final b f3575n;
    public final b o;

    public m(Context context, Bitmap.Config config, ColorSpace colorSpace, L3.g gVar, L3.f fVar, boolean z6, boolean z10, boolean z11, String str, u uVar, q qVar, o oVar, b bVar, b bVar2, b bVar3) {
        this.f3565a = context;
        this.f3566b = config;
        this.c = colorSpace;
        this.d = gVar;
        this.f3567e = fVar;
        this.f3568f = z6;
        this.f3569g = z10;
        this.f3570h = z11;
        this.f3571i = str;
        this.f3572j = uVar;
        this.f3573k = qVar;
        this.l = oVar;
        this.f3574m = bVar;
        this.f3575n = bVar2;
        this.o = bVar3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof m) {
            m mVar = (m) obj;
            if (A7.m.a(this.f3565a, mVar.f3565a) && this.f3566b == mVar.f3566b && A7.m.a(this.c, mVar.c) && A7.m.a(this.d, mVar.d) && this.f3567e == mVar.f3567e && this.f3568f == mVar.f3568f && this.f3569g == mVar.f3569g && this.f3570h == mVar.f3570h && A7.m.a(this.f3571i, mVar.f3571i) && A7.m.a(this.f3572j, mVar.f3572j) && A7.m.a(this.f3573k, mVar.f3573k) && A7.m.a(this.l, mVar.l) && this.f3574m == mVar.f3574m && this.f3575n == mVar.f3575n && this.o == mVar.o) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (this.f3566b.hashCode() + (this.f3565a.hashCode() * 31)) * 31;
        ColorSpace colorSpace = this.c;
        int iD = AbstractC1347d.d(AbstractC1347d.d(AbstractC1347d.d((this.f3567e.hashCode() + ((this.d.hashCode() + ((iHashCode + (colorSpace != null ? colorSpace.hashCode() : 0)) * 31)) * 31)) * 31, 31, this.f3568f), 31, this.f3569g), 31, this.f3570h);
        String str = this.f3571i;
        return this.o.hashCode() + ((this.f3575n.hashCode() + ((this.f3574m.hashCode() + ((this.l.l.hashCode() + ((this.f3573k.f3584a.hashCode() + ((((iD + (str != null ? str.hashCode() : 0)) * 31) + Arrays.hashCode(this.f3572j.l)) * 31)) * 31)) * 31)) * 31)) * 31);
    }
}
