package W2;

import A7.m;
import B2.N;
import B2.O;
import B2.P;
import B2.t;
import B5.RunnableC0079c;
import N0.q;
import Q7.InterfaceC0658k;
import Q7.Q;
import S0.p;
import W7.B;
import android.opengl.GLES20;
import android.util.SparseArray;
import android.util.SparseIntArray;
import c8.C1036a;
import d8.C1113E;
import f8.InterfaceC1269e;
import h2.AbstractC1346c;
import h2.y;
import h3.D;
import h3.F;
import h3.H;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import v1.C2535b;

/* JADX INFO: loaded from: classes.dex */
public final class j implements c8.e, D {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f9750m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f9751n;
    public final Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Cloneable f9752p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Object f9753q;

    public j(q qVar, InterfaceC0658k interfaceC0658k, InterfaceC1269e interfaceC1269e, int i7) {
        this.l = 2;
        m.f(qVar, "c");
        m.f(interfaceC1269e, "typeParameterOwner");
        this.f9751n = qVar;
        this.o = interfaceC0658k;
        this.f9750m = i7;
        ArrayList arrayListT = interfaceC1269e.t();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it = arrayListT.iterator();
        int i10 = 0;
        while (it.hasNext()) {
            linkedHashMap.put(it.next(), Integer.valueOf(i10));
            i10++;
        }
        this.f9752p = linkedHashMap;
        this.f9753q = ((C1036a) ((q) this.f9751n).f4399m).f12411a.c(new p(this, 10));
    }

    public static void d(String str, int i7, int i10) throws h2.j {
        int iGlCreateShader = GLES20.glCreateShader(i10);
        GLES20.glShaderSource(iGlCreateShader, str);
        GLES20.glCompileShader(iGlCreateShader);
        int[] iArr = {0};
        GLES20.glGetShaderiv(iGlCreateShader, 35713, iArr, 0);
        AbstractC1346c.e(GLES20.glGetShaderInfoLog(iGlCreateShader) + ", source: \n" + str, iArr[0] == 1);
        GLES20.glAttachShader(i7, iGlCreateShader);
        GLES20.glDeleteShader(iGlCreateShader);
        AbstractC1346c.d();
    }

    public static boolean o(X1.f fVar) {
        if (fVar == null) {
            return true;
        }
        return fVar.o;
    }

    @Override // h3.D
    public void a(y yVar, t tVar, H h10) {
    }

    @Override // c8.e
    public Q b(B b10) {
        m.f(b10, "javaTypeParameter");
        C1113E c1113e = (C1113E) ((E8.j) this.f9753q).b(b10);
        return c1113e != null ? c1113e : ((c8.e) ((q) this.f9751n).f4400n).b(b10);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01d0  */
    @Override // h3.D
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(h2.t r32) {
        /*
            Method dump skipped, instruction units count: 952
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: W2.j.c(h2.t):void");
    }

    public void e(boolean z6) {
        for (X1.f fVar : ((A7.D) this.o).q()) {
            if (fVar != null && z6) {
                fVar.f9980n.e(true);
            }
        }
    }

    public boolean f() {
        if (this.f9750m < 1) {
            return false;
        }
        ArrayList arrayList = null;
        boolean z6 = false;
        for (X1.f fVar : ((A7.D) this.o).q()) {
            if (fVar != null && o(fVar) && fVar.f9980n.f()) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(fVar);
                z6 = true;
            }
        }
        if (((ArrayList) this.f9752p) != null) {
            for (int i7 = 0; i7 < ((ArrayList) this.f9752p).size(); i7++) {
                X1.f fVar2 = (X1.f) ((ArrayList) this.f9752p).get(i7);
                if (arrayList == null || !arrayList.contains(fVar2)) {
                    fVar2.getClass();
                }
            }
        }
        this.f9752p = arrayList;
        return z6;
    }

    public void g(boolean z6) {
        for (X1.f fVar : ((A7.D) this.o).q()) {
            if (fVar != null && z6) {
                fVar.f9980n.g(true);
            }
        }
    }

    public void h(boolean z6, boolean z10) {
        for (X1.f fVar : ((A7.D) this.o).q()) {
            if (fVar != null && z10) {
                fVar.f9980n.h(z6, true);
            }
        }
    }

    public boolean i() {
        if (this.f9750m < 1) {
            return false;
        }
        for (X1.f fVar : ((A7.D) this.o).q()) {
            if (fVar != null && fVar.f9980n.i()) {
                return true;
            }
        }
        return false;
    }

    public void j() {
        if (this.f9750m < 1) {
            return;
        }
        for (X1.f fVar : ((A7.D) this.o).q()) {
            if (fVar != null) {
                fVar.f9980n.j();
            }
        }
    }

    public void k(boolean z6, boolean z10) {
        for (X1.f fVar : ((A7.D) this.o).q()) {
            if (fVar != null && z10) {
                fVar.f9980n.k(z6, true);
            }
        }
    }

    public boolean l() {
        boolean z6 = false;
        if (this.f9750m < 1) {
            return false;
        }
        for (X1.f fVar : ((A7.D) this.o).q()) {
            if (fVar != null && o(fVar) && fVar.f9980n.l()) {
                z6 = true;
            }
        }
        return z6;
    }

    public void m() {
        throw new IllegalStateException("FragmentManager has not been attached to a host.");
    }

    public int n(String str) throws h2.j {
        int iGlGetAttribLocation = GLES20.glGetAttribLocation(this.f9750m, str);
        GLES20.glEnableVertexAttribArray(iGlGetAttribLocation);
        AbstractC1346c.d();
        return iGlGetAttribLocation;
    }

    public String toString() {
        switch (this.l) {
            case 1:
                StringBuilder sb = new StringBuilder(128);
                sb.append("FragmentManager{");
                sb.append(Integer.toHexString(System.identityHashCode(this)));
                sb.append(" in ");
                sb.append("null");
                sb.append("}}");
                return sb.toString();
            default:
                return super.toString();
        }
    }

    public j(String str, String str2) throws h2.j {
        byte[] bArr;
        byte[] bArr2;
        this.l = 3;
        int iGlCreateProgram = GLES20.glCreateProgram();
        this.f9750m = iGlCreateProgram;
        AbstractC1346c.d();
        d(str, iGlCreateProgram, 35633);
        d(str2, iGlCreateProgram, 35632);
        GLES20.glLinkProgram(iGlCreateProgram);
        int i7 = 0;
        int[] iArr = {0};
        GLES20.glGetProgramiv(iGlCreateProgram, 35714, iArr, 0);
        int i10 = 1;
        AbstractC1346c.e("Unable to link shader program: \n" + GLES20.glGetProgramInfoLog(iGlCreateProgram), iArr[0] == 1);
        GLES20.glUseProgram(iGlCreateProgram);
        this.f9752p = new HashMap();
        int[] iArr2 = new int[1];
        GLES20.glGetProgramiv(iGlCreateProgram, 35721, iArr2, 0);
        this.f9751n = new io.sentry.hints.i[iArr2[0]];
        int i11 = 0;
        while (i11 < iArr2[0]) {
            int i12 = this.f9750m;
            int[] iArr3 = new int[i10];
            GLES20.glGetProgramiv(i12, 35722, iArr3, 0);
            int i13 = iArr3[0];
            byte[] bArr3 = new byte[i13];
            GLES20.glGetActiveAttrib(i12, i11, i13, new int[i10], 0, new int[i10], 0, new int[i10], 0, bArr3, 0);
            int i14 = 0;
            while (true) {
                if (i14 >= i13) {
                    bArr2 = bArr3;
                    i14 = i13;
                    break;
                } else {
                    bArr2 = bArr3;
                    if (bArr2[i14] == 0) {
                        break;
                    }
                    i14++;
                    bArr3 = bArr2;
                }
            }
            String str3 = new String(bArr2, 0, i14);
            GLES20.glGetAttribLocation(i12, str3);
            io.sentry.hints.i iVar = new io.sentry.hints.i(21);
            ((io.sentry.hints.i[]) this.f9751n)[i11] = iVar;
            ((HashMap) this.f9752p).put(str3, iVar);
            i11++;
            i10 = 1;
        }
        this.f9753q = new HashMap();
        int[] iArr4 = new int[1];
        GLES20.glGetProgramiv(this.f9750m, 35718, iArr4, 0);
        this.o = new C8.d[iArr4[0]];
        for (int i15 = 0; i15 < iArr4[i7]; i15++) {
            int i16 = this.f9750m;
            int[] iArr5 = new int[1];
            GLES20.glGetProgramiv(i16, 35719, iArr5, i7);
            int i17 = iArr5[i7];
            byte[] bArr4 = new byte[i17];
            GLES20.glGetActiveUniform(i16, i15, i17, new int[1], 0, new int[1], 0, new int[1], 0, bArr4, 0);
            int i18 = 0;
            while (true) {
                if (i18 >= i17) {
                    bArr = bArr4;
                    i18 = i17;
                    break;
                } else {
                    bArr = bArr4;
                    if (bArr[i18] == 0) {
                        break;
                    }
                    i18++;
                    bArr4 = bArr;
                }
            }
            i7 = 0;
            String str4 = new String(bArr, 0, i18);
            GLES20.glGetUniformLocation(i16, str4);
            C8.d dVar = new C8.d(22);
            ((C8.d[]) this.o)[i15] = dVar;
            ((HashMap) this.f9753q).put(str4, dVar);
        }
        AbstractC1346c.d();
    }

    public j() {
        this.l = 1;
        this.f9751n = new ArrayList();
        this.o = new A7.D(4, false);
        final int i7 = 0;
        new X1.h(this, 0);
        this.f9753q = new AtomicInteger();
        Collections.synchronizedMap(new HashMap());
        Collections.synchronizedMap(new HashMap());
        Collections.synchronizedMap(new HashMap());
        new C8.d(this);
        new CopyOnWriteArrayList();
        new E1.a(this) { // from class: X1.g

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ W2.j f9987b;

            {
                this.f9987b = this;
            }

            @Override // E1.a
            public final void accept(Object obj) {
                switch (i7) {
                    case 0:
                        W2.j jVar = this.f9987b;
                        jVar.getClass();
                        jVar.e(false);
                        break;
                    case 1:
                        W2.j jVar2 = this.f9987b;
                        jVar2.getClass();
                        if (((Integer) obj).intValue() == 80) {
                            jVar2.g(false);
                        }
                        break;
                    case 2:
                        W2.j jVar3 = this.f9987b;
                        jVar3.getClass();
                        jVar3.h(((C2535b) obj).f22943a, false);
                        break;
                    default:
                        W2.j jVar4 = this.f9987b;
                        jVar4.getClass();
                        jVar4.k(((v1.k) obj).f22978a, false);
                        break;
                }
            }
        };
        final int i10 = 1;
        new E1.a(this) { // from class: X1.g

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ W2.j f9987b;

            {
                this.f9987b = this;
            }

            @Override // E1.a
            public final void accept(Object obj) {
                switch (i10) {
                    case 0:
                        W2.j jVar = this.f9987b;
                        jVar.getClass();
                        jVar.e(false);
                        break;
                    case 1:
                        W2.j jVar2 = this.f9987b;
                        jVar2.getClass();
                        if (((Integer) obj).intValue() == 80) {
                            jVar2.g(false);
                        }
                        break;
                    case 2:
                        W2.j jVar3 = this.f9987b;
                        jVar3.getClass();
                        jVar3.h(((C2535b) obj).f22943a, false);
                        break;
                    default:
                        W2.j jVar4 = this.f9987b;
                        jVar4.getClass();
                        jVar4.k(((v1.k) obj).f22978a, false);
                        break;
                }
            }
        };
        final int i11 = 2;
        new E1.a(this) { // from class: X1.g

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ W2.j f9987b;

            {
                this.f9987b = this;
            }

            @Override // E1.a
            public final void accept(Object obj) {
                switch (i11) {
                    case 0:
                        W2.j jVar = this.f9987b;
                        jVar.getClass();
                        jVar.e(false);
                        break;
                    case 1:
                        W2.j jVar2 = this.f9987b;
                        jVar2.getClass();
                        if (((Integer) obj).intValue() == 80) {
                            jVar2.g(false);
                        }
                        break;
                    case 2:
                        W2.j jVar3 = this.f9987b;
                        jVar3.getClass();
                        jVar3.h(((C2535b) obj).f22943a, false);
                        break;
                    default:
                        W2.j jVar4 = this.f9987b;
                        jVar4.getClass();
                        jVar4.k(((v1.k) obj).f22978a, false);
                        break;
                }
            }
        };
        final int i12 = 3;
        new E1.a(this) { // from class: X1.g

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ W2.j f9987b;

            {
                this.f9987b = this;
            }

            @Override // E1.a
            public final void accept(Object obj) {
                switch (i12) {
                    case 0:
                        W2.j jVar = this.f9987b;
                        jVar.getClass();
                        jVar.e(false);
                        break;
                    case 1:
                        W2.j jVar2 = this.f9987b;
                        jVar2.getClass();
                        if (((Integer) obj).intValue() == 80) {
                            jVar2.g(false);
                        }
                        break;
                    case 2:
                        W2.j jVar3 = this.f9987b;
                        jVar3.getClass();
                        jVar3.h(((C2535b) obj).f22943a, false);
                        break;
                    default:
                        W2.j jVar4 = this.f9987b;
                        jVar4.getClass();
                        jVar4.k(((v1.k) obj).f22978a, false);
                        break;
                }
            }
        };
        this.f9750m = -1;
        new ArrayDeque();
        new RunnableC0079c(this, 7);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public j(B2.D d, O o, byte[] bArr, P[] pArr, int i7) {
        this.l = 0;
        this.f9751n = d;
        this.o = o;
        this.f9752p = bArr;
        this.f9753q = pArr;
        this.f9750m = i7;
    }

    public j(F f10, int i7) {
        this.l = 4;
        this.f9753q = f10;
        this.f9751n = new N(5, new byte[5]);
        this.o = new SparseArray();
        this.f9752p = new SparseIntArray();
        this.f9750m = i7;
    }
}
