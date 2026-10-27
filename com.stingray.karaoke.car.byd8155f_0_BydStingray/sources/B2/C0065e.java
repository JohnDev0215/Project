package B2;

import h2.AbstractC1348e;
import java.util.ArrayList;

/* JADX INFO: renamed from: B2.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0065e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f557a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f558b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f559e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f560f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f561g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f562h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f563i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f564j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final float f565k;
    public final String l;

    public C0065e(ArrayList arrayList, int i7, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, float f10, String str) {
        this.f557a = arrayList;
        this.f558b = i7;
        this.c = i10;
        this.d = i11;
        this.f559e = i12;
        this.f560f = i13;
        this.f561g = i14;
        this.f562h = i15;
        this.f563i = i16;
        this.f564j = i17;
        this.f565k = f10;
        this.l = str;
    }

    public static C0065e a(h2.t tVar) {
        int i7;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        float f10;
        String str;
        int i16;
        try {
            tVar.I(4);
            int iV = (tVar.v() & 3) + 1;
            if (iV == 3) {
                throw new IllegalStateException();
            }
            ArrayList arrayList = new ArrayList();
            int iV2 = tVar.v() & 31;
            for (int i17 = 0; i17 < iV2; i17++) {
                int iB = tVar.B();
                int i18 = tVar.f15242b;
                tVar.I(iB);
                byte[] bArr = tVar.f15241a;
                byte[] bArr2 = AbstractC1348e.f15208a;
                byte[] bArr3 = new byte[iB + 4];
                System.arraycopy(AbstractC1348e.f15208a, 0, bArr3, 0, 4);
                System.arraycopy(bArr, i18, bArr3, 4, iB);
                arrayList.add(bArr3);
            }
            int iV3 = tVar.v();
            for (int i19 = 0; i19 < iV3; i19++) {
                int iB2 = tVar.B();
                int i20 = tVar.f15242b;
                tVar.I(iB2);
                byte[] bArr4 = tVar.f15241a;
                byte[] bArr5 = AbstractC1348e.f15208a;
                byte[] bArr6 = new byte[iB2 + 4];
                System.arraycopy(AbstractC1348e.f15208a, 0, bArr6, 0, 4);
                System.arraycopy(bArr4, i20, bArr6, 4, iB2);
                arrayList.add(bArr6);
            }
            if (iV2 > 0) {
                i2.m mVarI = i2.n.i(iV, ((byte[]) arrayList.get(0)).length, (byte[]) arrayList.get(0));
                int i21 = mVarI.f15700e;
                int i22 = mVarI.f15701f;
                int i23 = mVarI.f15703h + 8;
                int i24 = mVarI.f15704i + 8;
                int i25 = mVarI.f15709p;
                int i26 = mVarI.f15710q;
                int i27 = mVarI.f15711r;
                int i28 = mVarI.f15712s;
                float f11 = mVarI.f15702g;
                int i29 = mVarI.f15698a;
                int i30 = mVarI.f15699b;
                int i31 = mVarI.c;
                byte[] bArr7 = AbstractC1348e.f15208a;
                str = String.format("avc1.%02X%02X%02X", Integer.valueOf(i29), Integer.valueOf(i30), Integer.valueOf(i31));
                i14 = i26;
                i15 = i27;
                i16 = i28;
                f10 = f11;
                i10 = i22;
                i11 = i23;
                i12 = i24;
                i13 = i25;
                i7 = i21;
            } else {
                i7 = -1;
                i10 = -1;
                i11 = -1;
                i12 = -1;
                i13 = -1;
                i14 = -1;
                i15 = -1;
                f10 = 1.0f;
                str = null;
                i16 = 16;
            }
            return new C0065e(arrayList, iV, i7, i10, i11, i12, i13, i14, i15, i16, f10, str);
        } catch (ArrayIndexOutOfBoundsException e10) {
            throw e2.M.a(e10, "Error parsing AVC config");
        }
    }
}
