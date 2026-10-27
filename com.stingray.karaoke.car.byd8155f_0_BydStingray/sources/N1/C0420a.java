package N1;

import B2.C0073m;
import B2.C0074n;
import B5.RunnableC0079c;
import F8.AbstractC0287w;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import T.C0735d;
import T.C0738e0;
import T.Q0;
import V8.InterfaceC0817f;
import a7.C0929a;
import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import android.util.SparseArray;
import android.view.View;
import androidx.lifecycle.h0;
import androidx.lifecycle.k0;
import b2.AbstractC0992b;
import c9.InterfaceC1048i;
import com.google.android.material.behavior.SwipeDismissBehavior;
import d5.AbstractC1095q;
import d8.C1122i;
import e2.C1200h;
import e7.C1234d;
import e7.C1235e;
import h2.AbstractC1346c;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import q7.InterfaceC2189c;
import u.AbstractC2456D;
import u.C2453A;
import u.C2484x;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: N1.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0420a implements G1.n, O8.b, Q4.a, InterfaceC0428i, InterfaceC1048i {
    public Object l;

    public /* synthetic */ C0420a(Object obj) {
        this.l = obj;
    }

    @Override // G1.n
    public boolean a(View view) {
        SwipeDismissBehavior swipeDismissBehavior = (SwipeDismissBehavior) this.l;
        if (!swipeDismissBehavior.r(view)) {
            return false;
        }
        int[] iArr = F1.H.f2303a;
        boolean z6 = view.getLayoutDirection() == 1;
        int i7 = swipeDismissBehavior.c;
        view.offsetLeftAndRight((!(i7 == 0 && z6) && (i7 != 1 || z6)) ? view.getWidth() : -view.getWidth());
        view.setAlpha(0.0f);
        return true;
    }

    @Override // O8.b
    public Iterable b(Object obj) {
        P7.n nVar = (P7.n) this.l;
        A7.m.f(nVar, "this$0");
        Collection collectionO = ((InterfaceC0652e) obj).C().o();
        A7.m.e(collectionO, "it.typeConstructor.supertypes");
        ArrayList arrayList = new ArrayList();
        Iterator it = collectionO.iterator();
        while (it.hasNext()) {
            InterfaceC0654g interfaceC0654gN = ((AbstractC0287w) it.next()).B0().n();
            InterfaceC0654g interfaceC0654gB1 = interfaceC0654gN != null ? interfaceC0654gN.b1() : null;
            InterfaceC0652e interfaceC0652e = interfaceC0654gB1 instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0654gB1 : null;
            C1122i c1122iF = interfaceC0652e != null ? nVar.f(interfaceC0652e) : null;
            if (c1122iF != null) {
                arrayList.add(c1122iF);
            }
        }
        return arrayList;
    }

    @Override // N1.InterfaceC0428i
    public Object c(InterfaceC2785n interfaceC2785n, InterfaceC2189c interfaceC2189c) {
        return ((InterfaceC0428i) this.l).c(new S1.c(interfaceC2785n, null), interfaceC2189c);
    }

    @Override // N1.InterfaceC0428i
    public InterfaceC0817f d() {
        return ((InterfaceC0428i) this.l).d();
    }

    public void e(Object obj, Object obj2) {
        C2484x c2484x = (C2484x) this.l;
        int iD = c2484x.d(obj);
        boolean z6 = iD < 0;
        Object obj3 = z6 ? null : c2484x.c[iD];
        if (obj3 != null) {
            if (obj3 instanceof C2453A) {
                ((C2453A) obj3).a(obj2);
            } else if (obj3 != obj2) {
                C2453A c2453a = new C2453A();
                c2453a.a(obj3);
                c2453a.a(obj2);
                obj2 = c2453a;
            }
            obj2 = obj3;
        }
        if (!z6) {
            c2484x.c[iD] = obj2;
            return;
        }
        int i7 = ~iD;
        c2484x.f22644b[i7] = obj;
        c2484x.c[i7] = obj2;
    }

    public void f(int i7, int i10, C0073m c0073m) throws e2.M, EOFException, InterruptedIOException {
        int i11;
        long j3;
        int i12;
        int i13;
        int i14;
        T2.d dVar = (T2.d) this.l;
        SparseArray sparseArray = dVar.c;
        int i15 = 4;
        int i16 = 0;
        int i17 = 1;
        if (i7 != 161 && i7 != 163) {
            if (i7 == 165) {
                if (dVar.f8597I != 2) {
                    return;
                }
                T2.c cVar = (T2.c) sparseArray.get(dVar.f8603O);
                if (dVar.f8606R != 4 || !"V_VP9".equals(cVar.f8563b)) {
                    c0073m.i(i10);
                    return;
                }
                h2.t tVar = dVar.f8630p;
                tVar.E(i10);
                c0073m.c(tVar.f15241a, 0, i10, false);
                return;
            }
            if (i7 == 16877) {
                dVar.h(i7);
                T2.c cVar2 = dVar.f8637w;
                int i18 = cVar2.f8566g;
                if (i18 != 1685485123 && i18 != 1685480259) {
                    c0073m.i(i10);
                    return;
                }
                byte[] bArr = new byte[i10];
                cVar2.f8550O = bArr;
                c0073m.c(bArr, 0, i10, false);
                return;
            }
            if (i7 == 16981) {
                dVar.h(i7);
                T2.c cVar3 = dVar.f8637w;
                byte[] bArr2 = new byte[i10];
                cVar3.f8568i = bArr2;
                c0073m.c(bArr2, 0, i10, false);
                return;
            }
            if (i7 == 18402) {
                byte[] bArr3 = new byte[i10];
                c0073m.c(bArr3, 0, i10, false);
                dVar.h(i7);
                dVar.f8637w.f8569j = new B2.K(1, 0, 0, bArr3);
                return;
            }
            if (i7 == 21419) {
                h2.t tVar2 = dVar.f8627k;
                Arrays.fill(tVar2.f15241a, (byte) 0);
                c0073m.c(tVar2.f15241a, 4 - i10, i10, false);
                tVar2.H(0);
                dVar.f8639y = (int) tVar2.x();
                return;
            }
            if (i7 == 25506) {
                dVar.h(i7);
                T2.c cVar4 = dVar.f8637w;
                byte[] bArr4 = new byte[i10];
                cVar4.f8570k = bArr4;
                c0073m.c(bArr4, 0, i10, false);
                return;
            }
            if (i7 != 30322) {
                throw e2.M.a(null, "Unexpected id: " + i7);
            }
            dVar.h(i7);
            T2.c cVar5 = dVar.f8637w;
            byte[] bArr5 = new byte[i10];
            cVar5.f8580w = bArr5;
            c0073m.c(bArr5, 0, i10, false);
            return;
        }
        int i19 = dVar.f8597I;
        h2.t tVar3 = dVar.f8625i;
        if (i19 == 0) {
            T2.e eVar = dVar.f8617b;
            dVar.f8603O = (int) eVar.c(c0073m, false, true, 8);
            dVar.f8604P = eVar.c;
            dVar.f8599K = -9223372036854775807L;
            dVar.f8597I = 1;
            tVar3.E(0);
        }
        T2.c cVar6 = (T2.c) sparseArray.get(dVar.f8603O);
        if (cVar6 == null) {
            c0073m.i(i10 - dVar.f8604P);
            dVar.f8597I = 0;
            return;
        }
        cVar6.f8560Y.getClass();
        if (dVar.f8597I == 1) {
            dVar.k(c0073m, 3);
            int i20 = (tVar3.f15241a[2] & 6) >> 1;
            if (i20 == 0) {
                dVar.f8601M = 1;
                int[] iArr = dVar.f8602N;
                if (iArr == null) {
                    iArr = new int[1];
                } else if (iArr.length < 1) {
                    iArr = new int[Math.max(iArr.length * 2, 1)];
                }
                dVar.f8602N = iArr;
                iArr[0] = (i10 - dVar.f8604P) - 3;
            } else {
                dVar.k(c0073m, 4);
                int i21 = (tVar3.f15241a[3] & 255) + 1;
                dVar.f8601M = i21;
                int[] iArr2 = dVar.f8602N;
                if (iArr2 == null) {
                    iArr2 = new int[i21];
                } else if (iArr2.length < i21) {
                    iArr2 = new int[Math.max(iArr2.length * 2, i21)];
                }
                dVar.f8602N = iArr2;
                if (i20 == 2) {
                    int i22 = (i10 - dVar.f8604P) - 4;
                    int i23 = dVar.f8601M;
                    Arrays.fill(iArr2, 0, i23, i22 / i23);
                } else {
                    if (i20 != 1) {
                        if (i20 != 3) {
                            throw e2.M.a(null, "Unexpected lacing value: " + i20);
                        }
                        int i24 = 0;
                        int i25 = 0;
                        while (true) {
                            int i26 = dVar.f8601M - i17;
                            if (i24 >= i26) {
                                dVar.f8602N[i26] = ((i10 - dVar.f8604P) - i15) - i25;
                                break;
                            }
                            dVar.f8602N[i24] = i16;
                            int i27 = i15 + 1;
                            dVar.k(c0073m, i27);
                            if (tVar3.f15241a[i15] == 0) {
                                throw e2.M.a(null, "No valid varint length mask found");
                            }
                            int i28 = i16;
                            int i29 = 8;
                            while (true) {
                                if (i28 >= i29) {
                                    j3 = 0;
                                    i15 = i27;
                                    break;
                                }
                                int i30 = i17 << (7 - i28);
                                if ((tVar3.f15241a[i15] & i30) != 0) {
                                    int i31 = i27 + i28;
                                    dVar.k(c0073m, i31);
                                    j3 = tVar3.f15241a[i15] & 255 & (~i30);
                                    while (i27 < i31) {
                                        j3 = (j3 << 8) | ((long) (tVar3.f15241a[i27] & 255));
                                        i27++;
                                        i31 = i31;
                                    }
                                    int i32 = i31;
                                    if (i24 > 0) {
                                        j3 -= (1 << ((i28 * 7) + 6)) - 1;
                                    }
                                    i15 = i32;
                                } else {
                                    i28++;
                                    i29 = 8;
                                    i17 = 1;
                                }
                            }
                            if (j3 < -2147483648L || j3 > 2147483647L) {
                                break;
                            }
                            int i33 = (int) j3;
                            int[] iArr3 = dVar.f8602N;
                            if (i24 != 0) {
                                i33 += iArr3[i24 - 1];
                            }
                            iArr3[i24] = i33;
                            i25 += i33;
                            i24++;
                            i16 = 0;
                            i17 = 1;
                        }
                        throw e2.M.a(null, "EBML lacing sample size out of range.");
                    }
                    int i34 = 0;
                    int i35 = 0;
                    while (true) {
                        i12 = dVar.f8601M - 1;
                        if (i34 >= i12) {
                            break;
                        }
                        dVar.f8602N[i34] = 0;
                        while (true) {
                            i13 = i15 + 1;
                            dVar.k(c0073m, i13);
                            int i36 = tVar3.f15241a[i15] & 255;
                            int[] iArr4 = dVar.f8602N;
                            i14 = iArr4[i34] + i36;
                            iArr4[i34] = i14;
                            if (i36 != 255) {
                                break;
                            } else {
                                i15 = i13;
                            }
                        }
                        i35 += i14;
                        i34++;
                        i15 = i13;
                    }
                    dVar.f8602N[i12] = ((i10 - dVar.f8604P) - i15) - i35;
                }
            }
            byte[] bArr6 = tVar3.f15241a;
            dVar.f8598J = dVar.m((bArr6[1] & 255) | (bArr6[0] << 8)) + dVar.f8592D;
            dVar.f8605Q = (cVar6.d == 2 || (i7 == 163 && (tVar3.f15241a[2] & 128) == 128)) ? 1 : 0;
            dVar.f8597I = 2;
            dVar.f8600L = 0;
            i11 = 163;
        } else {
            i11 = 163;
        }
        if (i7 == i11) {
            while (true) {
                int i37 = dVar.f8600L;
                if (i37 >= dVar.f8601M) {
                    dVar.f8597I = 0;
                    return;
                } else {
                    dVar.i(cVar6, dVar.f8598J + ((long) ((dVar.f8600L * cVar6.f8564e) / 1000)), dVar.f8605Q, dVar.n(c0073m, cVar6, dVar.f8602N[i37], false), 0);
                    dVar.f8600L++;
                }
            }
        } else {
            while (true) {
                int i38 = dVar.f8600L;
                if (i38 >= dVar.f8601M) {
                    return;
                }
                int[] iArr5 = dVar.f8602N;
                iArr5[i38] = dVar.n(c0073m, cVar6, iArr5[i38], true);
                dVar.f8600L++;
            }
        }
    }

    public d5.Y g() {
        Map mapF = ((AbstractC1095q) this.l).f();
        d5.X x8 = new d5.X();
        d5.Y y10 = new d5.Y();
        if (!mapF.isEmpty()) {
            throw new IllegalArgumentException();
        }
        y10.o = mapF;
        y10.f13402q = x8;
        return y10;
    }

    public void h(I7.a aVar) {
        RunnableC0079c runnableC0079c = (RunnableC0079c) this.l;
        if (aVar != null) {
            b7.i.f12288D.fine("reconnect attempt error");
            b7.i iVar = (b7.i) ((H5.f) runnableC0079c.f673m).f2837m;
            iVar.f12292p = false;
            iVar.c1();
            ((b7.i) ((H5.f) runnableC0079c.f673m).f2837m).N0("reconnect_error", aVar);
            return;
        }
        b7.i.f12288D.fine("reconnect success");
        b7.i iVar2 = (b7.i) ((H5.f) runnableC0079c.f673m).f2837m;
        C0929a c0929a = iVar2.f12295s;
        int i7 = c0929a.d;
        iVar2.f12292p = false;
        c0929a.d = 0;
        iVar2.N0("reconnect", Integer.valueOf(i7));
    }

    public boolean i(f7.b bVar) {
        C1235e c1235e = (C1235e) this.l;
        if (c1235e.f13553w == 1 && "open".equals(bVar.f14641a)) {
            c1235e.f13553w = 2;
            c1235e.f13545n = true;
            c1235e.N0("open", new Object[0]);
        }
        if (!"close".equals(bVar.f14641a)) {
            c1235e.N0("packet", bVar);
            return true;
        }
        c1235e.f13553w = 3;
        c1235e.N0("close", new Object[0]);
        return false;
    }

    @Override // c9.InterfaceC1048i
    public void j(g9.j jVar, c9.H h10) {
        C1234d c1234d = (C1234d) this.l;
        c1234d.f14478r = h10;
        c1234d.N0("responseHeaders", h10.f12517q.r());
        try {
            if (h10.j()) {
                try {
                    c1234d.N0("data", c1234d.f14478r.f12518r.y());
                    c1234d.N0("success", new Object[0]);
                } catch (IOException e10) {
                    c1234d.N0("error", e10);
                }
            } else {
                c1234d.N0("error", new IOException(Integer.toString(h10.o)));
            }
        } finally {
            h10.close();
        }
    }

    public Q0 k() {
        T1.i iVarA = T1.i.a();
        if (iVarA.b() == 1) {
            return new V0.j(true);
        }
        C0738e0 c0738e0O = C0735d.O(Boolean.FALSE, T.S.f8224q);
        iVarA.g(new V0.f(c0738e0O, this));
        return c0738e0O;
    }

    public void l(long j3, int i7) throws e2.M {
        T2.d dVar = (T2.d) this.l;
        dVar.getClass();
        if (i7 == 20529) {
            if (j3 == 0) {
                return;
            }
            throw e2.M.a(null, "ContentEncodingOrder " + j3 + " not supported");
        }
        if (i7 == 20530) {
            if (j3 == 1) {
                return;
            }
            throw e2.M.a(null, "ContentEncodingScope " + j3 + " not supported");
        }
        switch (i7) {
            case 131:
                dVar.h(i7);
                dVar.f8637w.d = (int) j3;
                return;
            case 136:
                dVar.h(i7);
                dVar.f8637w.f8558W = j3 == 1;
                return;
            case 155:
                dVar.f8599K = dVar.m(j3);
                return;
            case 159:
                dVar.h(i7);
                dVar.f8637w.f8551P = (int) j3;
                return;
            case 176:
                dVar.h(i7);
                dVar.f8637w.f8571m = (int) j3;
                return;
            case 179:
                dVar.d(i7);
                dVar.f8593E.a(dVar.m(j3));
                return;
            case 186:
                dVar.h(i7);
                dVar.f8637w.f8572n = (int) j3;
                return;
            case 215:
                dVar.h(i7);
                dVar.f8637w.c = (int) j3;
                return;
            case 231:
                dVar.f8592D = dVar.m(j3);
                return;
            case 238:
                dVar.f8606R = (int) j3;
                return;
            case 241:
                if (dVar.f8595G) {
                    return;
                }
                dVar.d(i7);
                dVar.f8594F.a(j3);
                dVar.f8595G = true;
                return;
            case 251:
                dVar.f8607S = true;
                return;
            case 16871:
                dVar.h(i7);
                dVar.f8637w.f8566g = (int) j3;
                return;
            case 16980:
                if (j3 == 3) {
                    return;
                }
                throw e2.M.a(null, "ContentCompAlgo " + j3 + " not supported");
            case 17029:
                if (j3 < 1 || j3 > 2) {
                    throw e2.M.a(null, "DocTypeReadVersion " + j3 + " not supported");
                }
                return;
            case 17143:
                if (j3 == 1) {
                    return;
                }
                throw e2.M.a(null, "EBMLReadVersion " + j3 + " not supported");
            case 18401:
                if (j3 == 5) {
                    return;
                }
                throw e2.M.a(null, "ContentEncAlgo " + j3 + " not supported");
            case 18408:
                if (j3 == 1) {
                    return;
                }
                throw e2.M.a(null, "AESSettingsCipherMode " + j3 + " not supported");
            case 21420:
                dVar.f8640z = j3 + dVar.f8633s;
                return;
            case 21432:
                int i10 = (int) j3;
                dVar.h(i7);
                if (i10 == 0) {
                    dVar.f8637w.f8581x = 0;
                    return;
                }
                if (i10 == 1) {
                    dVar.f8637w.f8581x = 2;
                    return;
                } else if (i10 == 3) {
                    dVar.f8637w.f8581x = 1;
                    return;
                } else {
                    if (i10 != 15) {
                        return;
                    }
                    dVar.f8637w.f8581x = 3;
                    return;
                }
            case 21680:
                dVar.h(i7);
                dVar.f8637w.f8573p = (int) j3;
                return;
            case 21682:
                dVar.h(i7);
                dVar.f8637w.f8575r = (int) j3;
                return;
            case 21690:
                dVar.h(i7);
                dVar.f8637w.f8574q = (int) j3;
                return;
            case 21930:
                dVar.h(i7);
                dVar.f8637w.f8557V = j3 == 1;
                return;
            case 21938:
                dVar.h(i7);
                T2.c cVar = dVar.f8637w;
                cVar.f8582y = true;
                cVar.o = (int) j3;
                return;
            case 21998:
                dVar.h(i7);
                dVar.f8637w.f8565f = (int) j3;
                return;
            case 22186:
                dVar.h(i7);
                dVar.f8637w.f8554S = j3;
                return;
            case 22203:
                dVar.h(i7);
                dVar.f8637w.f8555T = j3;
                return;
            case 25188:
                dVar.h(i7);
                dVar.f8637w.f8552Q = (int) j3;
                return;
            case 30114:
                dVar.f8608T = j3;
                return;
            case 30321:
                dVar.h(i7);
                int i11 = (int) j3;
                if (i11 == 0) {
                    dVar.f8637w.f8576s = 0;
                    return;
                }
                if (i11 == 1) {
                    dVar.f8637w.f8576s = 1;
                    return;
                } else if (i11 == 2) {
                    dVar.f8637w.f8576s = 2;
                    return;
                } else {
                    if (i11 != 3) {
                        return;
                    }
                    dVar.f8637w.f8576s = 3;
                    return;
                }
            case 2352003:
                dVar.h(i7);
                dVar.f8637w.f8564e = (int) j3;
                return;
            case 2807729:
                dVar.f8634t = j3;
                return;
            default:
                switch (i7) {
                    case 21945:
                        dVar.h(i7);
                        int i12 = (int) j3;
                        if (i12 == 1) {
                            dVar.f8637w.B = 2;
                            return;
                        } else {
                            if (i12 != 2) {
                                return;
                            }
                            dVar.f8637w.B = 1;
                            return;
                        }
                    case 21946:
                        dVar.h(i7);
                        int iG = C1200h.g((int) j3);
                        if (iG != -1) {
                            dVar.f8637w.f8537A = iG;
                            return;
                        }
                        return;
                    case 21947:
                        dVar.h(i7);
                        dVar.f8637w.f8582y = true;
                        int iF = C1200h.f((int) j3);
                        if (iF != -1) {
                            dVar.f8637w.f8583z = iF;
                            return;
                        }
                        return;
                    case 21948:
                        dVar.h(i7);
                        dVar.f8637w.f8538C = (int) j3;
                        return;
                    case 21949:
                        dVar.h(i7);
                        dVar.f8637w.f8539D = (int) j3;
                        return;
                    default:
                        return;
                }
        }
    }

    public void m() {
    }

    public boolean n(Object obj, Object obj2) {
        C2484x c2484x = (C2484x) this.l;
        Object objE = c2484x.e(obj);
        if (objE == null) {
            return false;
        }
        if (!(objE instanceof C2453A)) {
            if (!objE.equals(obj2)) {
                return false;
            }
            c2484x.g(obj);
            return true;
        }
        C2453A c2453a = (C2453A) objE;
        boolean zJ = c2453a.j(obj2);
        if (zJ && c2453a.g()) {
            c2484x.g(obj);
        }
        return zJ;
    }

    public void o(Object obj) {
        boolean zG;
        C2484x c2484x = (C2484x) this.l;
        long[] jArr = c2484x.f22643a;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i7 = 0;
        while (true) {
            long j3 = jArr[i7];
            if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i10 = 8 - ((~(i7 - length)) >>> 31);
                for (int i11 = 0; i11 < i10; i11++) {
                    if ((255 & j3) < 128) {
                        int i12 = (i7 << 3) + i11;
                        Object obj2 = c2484x.f22644b[i12];
                        Object obj3 = c2484x.c[i12];
                        if (obj3 instanceof C2453A) {
                            A7.m.d(obj3, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap.removeScope$lambda$3>");
                            C2453A c2453a = (C2453A) obj3;
                            c2453a.j(obj);
                            zG = c2453a.g();
                        } else {
                            zG = obj3 == obj;
                        }
                        if (zG) {
                            c2484x.h(i12);
                        }
                    }
                    j3 >>= 8;
                }
                if (i10 != 8) {
                    return;
                }
            }
            if (i7 == length) {
                return;
            } else {
                i7++;
            }
        }
    }

    public void p(int i7, long j3, long j10) throws e2.M {
        T2.d dVar = (T2.d) this.l;
        AbstractC1346c.k(dVar.f8620d0);
        if (i7 == 160) {
            dVar.f8607S = false;
            dVar.f8608T = 0L;
            return;
        }
        if (i7 != 174) {
            if (i7 == 187) {
                dVar.f8595G = false;
                return;
            }
            if (i7 == 19899) {
                dVar.f8639y = -1;
                dVar.f8640z = -1L;
                return;
            }
            if (i7 == 20533) {
                dVar.h(i7);
                dVar.f8637w.f8567h = true;
                return;
            }
            if (i7 == 21968) {
                dVar.h(i7);
                dVar.f8637w.f8582y = true;
                return;
            }
            if (i7 == 408125543) {
                long j11 = dVar.f8633s;
                if (j11 != -1 && j11 != j3) {
                    throw e2.M.a(null, "Multiple Segment elements not supported");
                }
                dVar.f8633s = j3;
                dVar.f8632r = j10;
                return;
            }
            if (i7 == 475249515) {
                dVar.f8593E = new h2.p();
                dVar.f8594F = new h2.p();
                return;
            } else {
                if (i7 == 524531317 && !dVar.f8638x) {
                    if (dVar.d && dVar.B != -1) {
                        dVar.f8590A = true;
                        return;
                    } else {
                        dVar.f8620d0.w(new B2.w(dVar.f8636v));
                        dVar.f8638x = true;
                        return;
                    }
                }
                return;
            }
        }
        T2.c cVar = new T2.c();
        cVar.f8571m = -1;
        cVar.f8572n = -1;
        cVar.o = -1;
        cVar.f8573p = -1;
        cVar.f8574q = -1;
        cVar.f8575r = 0;
        cVar.f8576s = -1;
        cVar.f8577t = 0.0f;
        cVar.f8578u = 0.0f;
        cVar.f8579v = 0.0f;
        cVar.f8580w = null;
        cVar.f8581x = -1;
        cVar.f8582y = false;
        cVar.f8583z = -1;
        cVar.f8537A = -1;
        cVar.B = -1;
        cVar.f8538C = 1000;
        cVar.f8539D = 200;
        cVar.f8540E = -1.0f;
        cVar.f8541F = -1.0f;
        cVar.f8542G = -1.0f;
        cVar.f8543H = -1.0f;
        cVar.f8544I = -1.0f;
        cVar.f8545J = -1.0f;
        cVar.f8546K = -1.0f;
        cVar.f8547L = -1.0f;
        cVar.f8548M = -1.0f;
        cVar.f8549N = -1.0f;
        cVar.f8551P = 1;
        cVar.f8552Q = -1;
        cVar.f8553R = BYDAutoEngineDevice.ENGINE_SPEED_MAX;
        cVar.f8554S = 0L;
        cVar.f8555T = 0L;
        cVar.f8558W = true;
        cVar.f8559X = "eng";
        dVar.f8637w = cVar;
    }

    public void q(T0.y yVar, long j3, boolean z6, C0074n c0074n) {
        ((O.K) this.l).n(N0.K.b(O.K.a((O.K) this.l, yVar, j3, z6, false, c0074n, false)) ? K.P.f3195n : K.P.f3194m);
    }

    @Override // c9.InterfaceC1048i
    public void r(g9.j jVar, IOException iOException) {
        ((C1234d) this.l).N0("error", iOException);
    }

    public C0420a(int i7) {
        switch (i7) {
            case 11:
                this.l = new SparseArray(10);
                break;
            case 12:
                long[] jArr = AbstractC2456D.f22584a;
                this.l = new C2484x();
                break;
            case 26:
                TimeUnit timeUnit = TimeUnit.MINUTES;
                A7.m.f(timeUnit, "timeUnit");
                this.l = new g9.n(f9.c.f14651h, timeUnit);
                break;
            default:
                this.l = new AtomicInteger(0);
                break;
        }
    }

    public C0420a(k0 k0Var, h0 h0Var, AbstractC0992b abstractC0992b) {
        A7.m.f(k0Var, "store");
        A7.m.f(h0Var, "factory");
        A7.m.f(abstractC0992b, "defaultCreationExtras");
        this.l = new l3.P(k0Var, h0Var, abstractC0992b);
    }
}
