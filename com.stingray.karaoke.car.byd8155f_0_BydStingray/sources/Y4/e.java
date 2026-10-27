package Y4;

import B2.F;
import B2.L;
import F8.AbstractC0287w;
import F8.O;
import F8.P;
import F8.Y;
import Q7.Q;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import b4.C0995a;
import d5.I;
import e8.C1236a;
import g2.C1289b;
import g4.w;
import h2.AbstractC1346c;
import h4.InterfaceC1359a;
import io.sentry.C1474i1;
import io.sentry.C1489n1;
import io.sentry.EnumC1469h;
import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;
import k3.InterfaceC1668t;
import u.C2466f;
import v8.AbstractC2564d;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public class e implements B2.t, B4.a, J3.e, x2.i, R3.b, InterfaceC1359a, io.sentry.clientreport.f, InterfaceC1668t, l5.o {
    public final /* synthetic */ int l;

    public /* synthetic */ e(int i7) {
        this.l = i7;
    }

    public static final float B(float f10, float[] fArr, float[] fArr2) {
        float f11;
        float f12;
        float f13;
        float f14;
        float fMax;
        float fAbs = Math.abs(f10);
        float fSignum = Math.signum(f10);
        int iBinarySearch = Arrays.binarySearch(fArr, fAbs);
        if (iBinarySearch >= 0) {
            fMax = fSignum * fArr2[iBinarySearch];
        } else {
            int i7 = -(iBinarySearch + 1);
            int i10 = i7 - 1;
            if (i10 >= fArr.length - 1) {
                float f15 = fArr[fArr.length - 1];
                float f16 = fArr2[fArr.length - 1];
                if (f15 == 0.0f) {
                    return 0.0f;
                }
                return (f16 / f15) * f10;
            }
            if (i10 == -1) {
                float f17 = fArr[0];
                f13 = fArr2[0];
                f14 = f17;
                f12 = 0.0f;
                f11 = 0.0f;
            } else {
                float f18 = fArr[i10];
                float f19 = fArr[i7];
                f11 = fArr2[i10];
                f12 = f18;
                f13 = fArr2[i7];
                f14 = f19;
            }
            fMax = fSignum * (((f13 - f11) * Math.max(0.0f, Math.min(1.0f, f12 == f14 ? 0.0f : (fAbs - f12) / (f14 - f12)))) + f11);
        }
        return fMax;
    }

    public static O C(Q q4, C1236a c1236a, io.sentry.internal.debugmeta.c cVar, AbstractC0287w abstractC0287w) {
        A7.m.f(c1236a, "typeAttr");
        A7.m.f(cVar, "typeParameterUpperBoundEraser");
        A7.m.f(abstractC0287w, "erasedUpperBound");
        if (!c1236a.c) {
            c1236a = c1236a.b(1);
        }
        int iC = AbstractC2641j.c(c1236a.f14484b);
        if (iC != 0 && iC != 1) {
            if (iC == 2) {
                return new P(1, abstractC0287w);
            }
            throw new A9.k();
        }
        int iE0 = q4.e0();
        boolean z6 = true;
        if (iE0 != 1) {
            if (iE0 == 2) {
                z6 = false;
            } else if (iE0 != 3) {
                throw null;
            }
        }
        if (!z6) {
            return new P(1, AbstractC2564d.e(q4).m());
        }
        List listP = abstractC0287w.B0().p();
        A7.m.e(listP, "erasedUpperBound.constructor.parameters");
        return !listP.isEmpty() ? new P(3, abstractC0287w) : Y.k(q4, c1236a);
    }

    public static byte[] D(I i7, long j3) {
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>(i7.size());
        Iterator<E> it = i7.iterator();
        while (it.hasNext()) {
            C1289b c1289b = (C1289b) it.next();
            Bundle bundleC = c1289b.c();
            Bitmap bitmap = c1289b.d;
            if (bitmap != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                AbstractC1346c.j(bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream));
                bundleC.putByteArray(C1289b.f14783w, byteArrayOutputStream.toByteArray());
            }
            arrayList.add(bundleC);
        }
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("c", arrayList);
        bundle.putLong("d", j3);
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeBundle(bundle);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        return bArrMarshall;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0045, code lost:
    
        if (java.lang.Character.isHighSurrogate(r5) != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0082, code lost:
    
        if (java.lang.Character.isLowSurrogate(r5) != false) goto L58;
     */
    /* JADX WARN: Removed duplicated region for block: B:104:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x006c A[EDGE_INSN: B:91:0x006c->B:46:0x006c BREAK  A[LOOP:2: B:47:0x006e->B:58:0x0085], EDGE_INSN: B:92:0x006c->B:46:0x006c BREAK  A[LOOP:2: B:47:0x006e->B:58:0x0085, LOOP_LABEL: LOOP:2: B:47:0x006e->B:58:0x0085]] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00a2 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean E(V1.b r7, android.text.Editable r8, int r9, int r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: Y4.e.E(V1.b, android.text.Editable, int, int, boolean):boolean");
    }

    @Override // io.sentry.clientreport.f
    public void A(io.sentry.clientreport.d dVar, C1489n1 c1489n1) {
    }

    public Object F(Intent intent, int i7) {
        if (intent == null || i7 != -1) {
            return Boolean.FALSE;
        }
        int[] intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
        boolean z6 = false;
        if (intArrayExtra != null) {
            int length = intArrayExtra.length;
            int i10 = 0;
            while (true) {
                if (i10 >= length) {
                    break;
                }
                if (intArrayExtra[i10] == 0) {
                    z6 = true;
                    break;
                }
                i10++;
            }
        }
        return Boolean.valueOf(z6);
    }

    @Override // l5.o
    public Object H() {
        switch (this.l) {
            case 27:
                return new LinkedHashSet();
            case 28:
                return new ConcurrentSkipListMap();
            default:
                return new LinkedHashMap();
        }
    }

    @Override // h4.InterfaceC1359a
    public void b(int i7) {
    }

    @Override // x2.i
    public long d(C1.j jVar) {
        return Math.min(((long) Math.pow(2.0d, jVar.l - 1)) * 500, 5000L);
    }

    @Override // B2.t
    public void e() {
        throw new UnsupportedOperationException();
    }

    @Override // io.sentry.clientreport.f
    public C1489n1 f(C1489n1 c1489n1) {
        return c1489n1;
    }

    @Override // io.sentry.clientreport.f
    public void g(io.sentry.clientreport.d dVar, EnumC1469h enumC1469h) {
    }

    @Override // io.sentry.clientreport.f
    public void h(io.sentry.clientreport.d dVar, EnumC1469h enumC1469h, long j3) {
    }

    @Override // h4.InterfaceC1359a
    public Bitmap i(int i7, int i10, Bitmap.Config config) {
        return Bitmap.createBitmap(i7, i10, config);
    }

    @Override // R3.b
    public boolean isEmpty() {
        return true;
    }

    @Override // h4.InterfaceC1359a
    public Bitmap j(int i7, int i10, Bitmap.Config config) {
        return Bitmap.createBitmap(i7, i10, config);
    }

    @Override // h4.InterfaceC1359a
    public void k(Bitmap bitmap) {
        bitmap.recycle();
    }

    @Override // J3.e
    public boolean l() {
        return true;
    }

    @Override // B4.a
    public Object m() {
        switch (this.l) {
            case 2:
                return new ArrayList();
            case 21:
                return new w();
            default:
                try {
                    return new i4.f(MessageDigest.getInstance("SHA-256"));
                } catch (NoSuchAlgorithmException e10) {
                    throw new RuntimeException(e10);
                }
        }
    }

    @Override // io.sentry.clientreport.f
    public void o(io.sentry.clientreport.d dVar, C1474i1 c1474i1) {
    }

    @Override // R3.b
    public float p() {
        return 1.0f;
    }

    @Override // R3.b
    public boolean q(float f10) {
        throw new IllegalStateException("not implemented");
    }

    @Override // R3.b
    public float r() {
        return 0.0f;
    }

    @Override // J3.e
    public void shutdown() {
    }

    @Override // R3.b
    public C0995a t() {
        throw new IllegalStateException("not implemented");
    }

    @Override // B2.t
    public L u(int i7, int i10) {
        throw new UnsupportedOperationException();
    }

    @Override // B2.t
    public void w(F f10) {
        throw new UnsupportedOperationException();
    }

    @Override // R3.b
    public boolean x(float f10) {
        return false;
    }

    @Override // x2.i
    public int y(int i7) {
        return 5;
    }

    @Override // h4.InterfaceC1359a
    public void z() {
    }

    public e() {
        this.l = 7;
        new C2466f(null);
        new HashMap();
    }
}
