package W8;

import S8.AbstractC0727y;
import S8.EnumC0726x;
import S8.InterfaceC0725w;
import U8.EnumC0810a;
import V8.InterfaceC0817f;
import V8.InterfaceC0818g;
import java.util.ArrayList;
import n7.AbstractC1998m;
import q7.C2195i;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import r7.EnumC2303a;
import x3.AbstractC2673a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class g implements w {
    public final InterfaceC2194h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f9916m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final EnumC0810a f9917n;

    public g(InterfaceC2194h interfaceC2194h, int i7, EnumC0810a enumC0810a) {
        this.l = interfaceC2194h;
        this.f9916m = i7;
        this.f9917n = enumC0810a;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0015  */
    @Override // W8.w
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final V8.InterfaceC0817f a(q7.InterfaceC2194h r5, int r6, U8.EnumC0810a r7) {
        /*
            r4 = this;
            q7.h r0 = r4.l
            q7.h r5 = r5.v(r0)
            U8.a r1 = U8.EnumC0810a.l
            U8.a r2 = r4.f9917n
            int r3 = r4.f9916m
            if (r7 == r1) goto Lf
            goto L26
        Lf:
            r7 = -3
            if (r3 != r7) goto L13
            goto L25
        L13:
            if (r6 != r7) goto L17
        L15:
            r6 = r3
            goto L25
        L17:
            r7 = -2
            if (r3 != r7) goto L1b
            goto L25
        L1b:
            if (r6 != r7) goto L1e
            goto L15
        L1e:
            int r6 = r6 + r3
            if (r6 < 0) goto L22
            goto L25
        L22:
            r6 = 2147483647(0x7fffffff, float:NaN)
        L25:
            r7 = r2
        L26:
            boolean r0 = A7.m.a(r5, r0)
            if (r0 == 0) goto L31
            if (r6 != r3) goto L31
            if (r7 != r2) goto L31
            return r4
        L31:
            W8.g r4 = r4.e(r5, r6, r7)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: W8.g.a(q7.h, int, U8.a):V8.f");
    }

    @Override // V8.InterfaceC0817f
    public Object b(InterfaceC0818g interfaceC0818g, InterfaceC2189c interfaceC2189c) {
        Object objH = AbstractC0727y.h(new C0845e(interfaceC0818g, this, null), interfaceC2189c);
        return objH == EnumC2303a.l ? objH : m7.q.f19267a;
    }

    public String c() {
        return null;
    }

    public abstract Object d(U8.x xVar, C0846f c0846f);

    public abstract g e(InterfaceC2194h interfaceC2194h, int i7, EnumC0810a enumC0810a);

    public InterfaceC0817f f() {
        return null;
    }

    public U8.z g(InterfaceC0725w interfaceC0725w) {
        int i7 = this.f9916m;
        if (i7 == -3) {
            i7 = -2;
        }
        EnumC0726x enumC0726x = EnumC0726x.f8142n;
        InterfaceC2785n c0846f = new C0846f(this, null);
        U8.w wVar = new U8.w(AbstractC0727y.x(interfaceC0725w, this.l), AbstractC2673a.a(i7, 4, this.f9917n));
        wVar.i0(enumC0726x, wVar, c0846f);
        return wVar;
    }

    public String toString() {
        ArrayList arrayList = new ArrayList(4);
        String strC = c();
        if (strC != null) {
            arrayList.add(strC);
        }
        C2195i c2195i = C2195i.l;
        InterfaceC2194h interfaceC2194h = this.l;
        if (interfaceC2194h != c2195i) {
            arrayList.add("context=" + interfaceC2194h);
        }
        int i7 = this.f9916m;
        if (i7 != -3) {
            arrayList.add("capacity=" + i7);
        }
        EnumC0810a enumC0810a = EnumC0810a.l;
        EnumC0810a enumC0810a2 = this.f9917n;
        if (enumC0810a2 != enumC0810a) {
            arrayList.add("onBufferOverflow=" + enumC0810a2);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append('[');
        return android.hardware.bydauto.ac.tsimpl.a.k(sb, AbstractC1998m.f0(arrayList, ", ", null, null, null, 62), ']');
    }
}
