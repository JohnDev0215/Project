package p3;

import E5.y;
import android.graphics.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final y f20874f = new y(9);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f20875a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f20876b;
    public final ArrayList c;
    public final c[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float[] f20877e = new float[3];

    public b(int[] iArr, c[] cVarArr) {
        C2101a c2101a;
        this.d = cVarArr;
        int[] iArr2 = new int[32768];
        this.f20876b = iArr2;
        int i7 = 0;
        for (int i10 = 0; i10 < iArr.length; i10++) {
            int i11 = iArr[i10];
            int iB = b(Color.blue(i11), 8, 5) | (b(Color.red(i11), 8, 5) << 10) | (b(Color.green(i11), 8, 5) << 5);
            iArr[i10] = iB;
            iArr2[iB] = iArr2[iB] + 1;
        }
        int i12 = 0;
        for (int i13 = 0; i13 < 32768; i13++) {
            if (iArr2[i13] > 0) {
                int iRgb = Color.rgb(b((i13 >> 10) & 31, 5, 8), b((i13 >> 5) & 31, 5, 8), b(i13 & 31, 5, 8));
                ThreadLocal threadLocal = y1.a.f24111a;
                int iRed = Color.red(iRgb);
                int iGreen = Color.green(iRgb);
                int iBlue = Color.blue(iRgb);
                float[] fArr = this.f20877e;
                y1.a.a(iRed, iGreen, iBlue, fArr);
                if (c(fArr)) {
                    iArr2[i13] = 0;
                }
            }
            if (iArr2[i13] > 0) {
                i12++;
            }
        }
        int[] iArr3 = new int[i12];
        this.f20875a = iArr3;
        int i14 = 0;
        for (int i15 = 0; i15 < 32768; i15++) {
            if (iArr2[i15] > 0) {
                iArr3[i14] = i15;
                i14++;
            }
        }
        if (i12 <= 16) {
            this.c = new ArrayList();
            while (i7 < i12) {
                int i16 = iArr3[i7];
                this.c.add(new d(Color.rgb(b((i16 >> 10) & 31, 5, 8), b((i16 >> 5) & 31, 5, 8), b(i16 & 31, 5, 8)), iArr2[i16]));
                i7++;
            }
            return;
        }
        PriorityQueue<C2101a> priorityQueue = new PriorityQueue(16, f20874f);
        priorityQueue.offer(new C2101a(this, 0, this.f20875a.length - 1));
        for (int i17 = 16; priorityQueue.size() < i17 && (c2101a = (C2101a) priorityQueue.poll()) != null; i17 = 16) {
            int i18 = c2101a.f20867b;
            int iMin = c2101a.f20866a;
            if ((i18 + 1) - iMin <= 1) {
                break;
            }
            if ((i18 + 1) - iMin <= 1) {
                throw new IllegalStateException("Can not split a box with only 1 color");
            }
            int i19 = c2101a.f20868e - c2101a.d;
            int i20 = c2101a.f20870g - c2101a.f20869f;
            int i21 = c2101a.f20872i - c2101a.f20871h;
            int i22 = (i19 < i20 || i19 < i21) ? (i20 < i19 || i20 < i21) ? -1 : -2 : -3;
            b bVar = c2101a.f20873j;
            int[] iArr4 = bVar.f20875a;
            a(iArr4, i22, iMin, i18);
            Arrays.sort(iArr4, iMin, c2101a.f20867b + 1);
            a(iArr4, i22, iMin, c2101a.f20867b);
            int i23 = c2101a.c / 2;
            int i24 = 0;
            int i25 = iMin;
            while (true) {
                int i26 = c2101a.f20867b;
                if (i25 <= i26) {
                    i24 += bVar.f20876b[iArr4[i25]];
                    if (i24 >= i23) {
                        iMin = Math.min(i26 - 1, i25);
                        break;
                    }
                    i25++;
                }
            }
            C2101a c2101a2 = new C2101a(bVar, iMin + 1, c2101a.f20867b);
            c2101a.f20867b = iMin;
            c2101a.a();
            priorityQueue.offer(c2101a2);
            priorityQueue.offer(c2101a);
        }
        ArrayList arrayList = new ArrayList(priorityQueue.size());
        for (C2101a c2101a3 : priorityQueue) {
            b bVar2 = c2101a3.f20873j;
            int[] iArr5 = bVar2.f20875a;
            int i27 = i7;
            int i28 = i27;
            int i29 = i28;
            int i30 = i29;
            for (int i31 = c2101a3.f20866a; i31 <= c2101a3.f20867b; i31++) {
                int i32 = iArr5[i31];
                int i33 = bVar2.f20876b[i32];
                i28 += i33;
                i27 = (((i32 >> 10) & 31) * i33) + i27;
                i29 = (((i32 >> 5) & 31) * i33) + i29;
                i30 += i33 * (i32 & 31);
            }
            float f10 = i28;
            d dVar = new d(Color.rgb(b(Math.round(i27 / f10), 5, 8), b(Math.round(i29 / f10), 5, 8), b(Math.round(i30 / f10), 5, 8)), i28);
            if (!c(dVar.b())) {
                arrayList.add(dVar);
            }
            i7 = 0;
        }
        this.c = arrayList;
    }

    public static void a(int[] iArr, int i7, int i10, int i11) {
        if (i7 == -2) {
            while (i10 <= i11) {
                int i12 = iArr[i10];
                iArr[i10] = (i12 & 31) | (((i12 >> 5) & 31) << 10) | (((i12 >> 10) & 31) << 5);
                i10++;
            }
            return;
        }
        if (i7 != -1) {
            return;
        }
        while (i10 <= i11) {
            int i13 = iArr[i10];
            iArr[i10] = ((i13 >> 10) & 31) | ((i13 & 31) << 10) | (((i13 >> 5) & 31) << 5);
            i10++;
        }
    }

    public static int b(int i7, int i10, int i11) {
        return (i11 > i10 ? i7 << (i11 - i10) : i7 >> (i10 - i11)) & ((1 << i11) - 1);
    }

    public final boolean c(float[] fArr) {
        c[] cVarArr = this.d;
        if (cVarArr != null && cVarArr.length > 0) {
            for (c cVar : cVarArr) {
                cVar.getClass();
                float f10 = fArr[2];
                if (f10 < 0.95f && f10 > 0.05f) {
                    float f11 = fArr[0];
                    if (f11 < 10.0f || f11 > 37.0f || fArr[1] > 0.82f) {
                    }
                }
                return true;
            }
        }
        return false;
    }
}
