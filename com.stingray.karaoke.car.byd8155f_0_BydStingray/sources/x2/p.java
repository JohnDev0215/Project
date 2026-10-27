package x2;

import h2.AbstractC1347d;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final C1.b f23761h = new C1.b(21);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final C1.b f23762i = new C1.b(22);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f23763a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f23765e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f23766f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f23767g;
    public final o[] c = new o[5];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f23764b = new ArrayList();
    public int d = -1;

    public p(int i7) {
        this.f23763a = i7;
    }

    public final void a(float f10, int i7) {
        o oVar;
        int i10 = this.d;
        ArrayList arrayList = this.f23764b;
        if (i10 != 1) {
            Collections.sort(arrayList, f23761h);
            this.d = 1;
        }
        int i11 = this.f23767g;
        o[] oVarArr = this.c;
        if (i11 > 0) {
            int i12 = i11 - 1;
            this.f23767g = i12;
            oVar = oVarArr[i12];
        } else {
            oVar = new o();
        }
        int i13 = this.f23765e;
        this.f23765e = i13 + 1;
        oVar.f23759a = i13;
        oVar.f23760b = i7;
        oVar.c = f10;
        arrayList.add(oVar);
        this.f23766f += i7;
        while (true) {
            int i14 = this.f23766f;
            int i15 = this.f23763a;
            if (i14 <= i15) {
                return;
            }
            int i16 = i14 - i15;
            o oVar2 = (o) arrayList.get(0);
            int i17 = oVar2.f23760b;
            if (i17 <= i16) {
                this.f23766f -= i17;
                arrayList.remove(0);
                int i18 = this.f23767g;
                if (i18 < 5) {
                    this.f23767g = i18 + 1;
                    oVarArr[i18] = oVar2;
                }
            } else {
                oVar2.f23760b = i17 - i16;
                this.f23766f -= i16;
            }
        }
    }

    public final float b() {
        int i7 = this.d;
        ArrayList arrayList = this.f23764b;
        if (i7 != 0) {
            Collections.sort(arrayList, f23762i);
            this.d = 0;
        }
        float f10 = 0.5f * this.f23766f;
        int i10 = 0;
        for (int i11 = 0; i11 < arrayList.size(); i11++) {
            o oVar = (o) arrayList.get(i11);
            i10 += oVar.f23760b;
            if (i10 >= f10) {
                return oVar.c;
            }
        }
        if (arrayList.isEmpty()) {
            return Float.NaN;
        }
        return ((o) AbstractC1347d.e(1, arrayList)).c;
    }
}
