package G6;

import S8.InterfaceC0725w;
import android.graphics.Bitmap;
import android.util.SparseBooleanArray;
import h8.AbstractC1386c;
import java.util.ArrayList;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import u.C2465e;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class f extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ Bitmap l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(Bitmap bitmap, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = bitmap;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new f(this.l, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((f) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i7;
        ArrayList arrayList;
        int i10;
        boolean z6;
        int i11;
        boolean z10 = true;
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        Bitmap bitmap = this.l;
        if (bitmap == null) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        if (bitmap.isRecycled()) {
            throw new IllegalArgumentException("Bitmap is not valid");
        }
        arrayList3.add(p3.e.f20885e);
        arrayList2.add(p3.f.d);
        arrayList2.add(p3.f.f20888e);
        arrayList2.add(p3.f.f20889f);
        arrayList2.add(p3.f.f20890g);
        arrayList2.add(p3.f.f20891h);
        arrayList2.add(p3.f.f20892i);
        int height = bitmap.getHeight() * bitmap.getWidth();
        double dSqrt = height > 12544 ? Math.sqrt(((double) 12544) / ((double) height)) : -1.0d;
        int i12 = 0;
        Bitmap bitmapCreateScaledBitmap = dSqrt <= 0.0d ? bitmap : Bitmap.createScaledBitmap(bitmap, (int) Math.ceil(((double) bitmap.getWidth()) * dSqrt), (int) Math.ceil(((double) bitmap.getHeight()) * dSqrt), false);
        int width = bitmapCreateScaledBitmap.getWidth();
        int height2 = bitmapCreateScaledBitmap.getHeight();
        int[] iArr = new int[width * height2];
        bitmapCreateScaledBitmap.getPixels(iArr, 0, width, 0, 0, width, height2);
        p3.b bVar = new p3.b(iArr, arrayList3.isEmpty() ? null : (p3.c[]) arrayList3.toArray(new p3.c[arrayList3.size()]));
        if (bitmapCreateScaledBitmap != bitmap) {
            bitmapCreateScaledBitmap.recycle();
        }
        p3.e eVar = new p3.e(bVar.c, arrayList2);
        int size = arrayList2.size();
        int i13 = 0;
        while (true) {
            SparseBooleanArray sparseBooleanArray = eVar.c;
            if (i13 >= size) {
                sparseBooleanArray.clear();
                return eVar;
            }
            p3.f fVar = (p3.f) arrayList2.get(i13);
            float[] fArr = fVar.c;
            int length = fArr.length;
            boolean z11 = false;
            float f10 = 0.0f;
            for (int i14 = i12; i14 < length; i14 += z10 ? 1 : 0) {
                float f11 = fArr[i14];
                if (f11 > 0.0f) {
                    f10 += f11;
                }
            }
            if (f10 != 0.0f) {
                int length2 = fArr.length;
                for (int i15 = i12; i15 < length2; i15 += z10 ? 1 : 0) {
                    float f12 = fArr[i15];
                    if (f12 > 0.0f) {
                        fArr[i15] = f12 / f10;
                    }
                }
            }
            C2465e c2465e = eVar.f20887b;
            ArrayList arrayList4 = eVar.f20886a;
            int size2 = arrayList4.size();
            int i16 = i12;
            float f13 = 0.0f;
            p3.d dVar = null;
            while (i16 < size2) {
                p3.d dVar2 = (p3.d) arrayList4.get(i16);
                float[] fArrB = dVar2.b();
                float f14 = fArrB[z10 ? 1 : 0];
                float[] fArr2 = fVar.f20893a;
                if (f14 < fArr2[i12] || f14 > fArr2[2]) {
                    i7 = size;
                    arrayList = arrayList2;
                    i10 = i12;
                    z6 = z11;
                } else {
                    float f15 = fArrB[2];
                    float[] fArr3 = fVar.f20894b;
                    if (f15 < fArr3[i12] || f15 > fArr3[2]) {
                        i7 = size;
                        arrayList = arrayList2;
                        i10 = i12;
                        z6 = false;
                    } else if (sparseBooleanArray.get(dVar2.d)) {
                        i7 = size;
                        arrayList = arrayList2;
                        z6 = false;
                        i10 = 0;
                    } else {
                        float[] fArrB2 = dVar2.b();
                        i7 = size;
                        p3.d dVar3 = eVar.d;
                        if (dVar3 != null) {
                            i11 = dVar3.f20880e;
                            arrayList = arrayList2;
                        } else {
                            arrayList = arrayList2;
                            i11 = 1;
                        }
                        float[] fArr4 = fVar.c;
                        i10 = 0;
                        float f16 = fArr4[0];
                        float fAbs = f16 > 0.0f ? (1.0f - Math.abs(fArrB2[1] - fArr2[1])) * f16 : 0.0f;
                        float f17 = fArr4[1];
                        float fAbs2 = f17 > 0.0f ? (1.0f - Math.abs(fArrB2[2] - fArr3[1])) * f17 : 0.0f;
                        float f18 = fArr4[2];
                        z6 = false;
                        float f19 = fAbs + fAbs2 + (f18 > 0.0f ? (dVar2.f20880e / i11) * f18 : 0.0f);
                        if (dVar == null || f19 > f13) {
                            f13 = f19;
                            dVar = dVar2;
                        }
                    }
                }
                z10 = true;
                i16++;
                z11 = z6;
                i12 = i10;
                size = i7;
                arrayList2 = arrayList;
            }
            int i17 = size;
            ArrayList arrayList5 = arrayList2;
            int i18 = i12;
            if (dVar != null) {
                sparseBooleanArray.append(dVar.d, z10);
            }
            c2465e.put(fVar, dVar);
            i13 += z10 ? 1 : 0;
            i12 = i18;
            size = i17;
            arrayList2 = arrayList5;
        }
    }
}
