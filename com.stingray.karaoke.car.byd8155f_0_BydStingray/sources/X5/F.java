package X5;

import E0.AbstractC0156f;
import E0.AbstractC0164n;
import N5.C0451g;
import N5.C0452h;
import N5.C0467x;
import S8.AbstractC0727y;
import com.stingray.karaoke.car.player.PlayerActionType;
import com.stingray.shared.logs.segment.ActionOwner;
import com.stingray.shared.logs.segment.player.PlaybackSetting;
import k3.C1672v;
import l0.AbstractC1713d;
import l0.C1714e;
import l0.C1727r;
import l0.EnumC1726q;
import l0.InterfaceC1712c;
import l0.InterfaceC1721l;
import u.C2453A;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class F extends A7.j implements InterfaceC2772a {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f10165t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ F(int i7, Object obj, Class cls, String str, String str2, int i10, int i11) {
        super(i7, i10, cls, obj, str, str2);
        this.f10165t = i11;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // z7.InterfaceC2772a
    public final Object a() {
        C1714e c1714e;
        C2453A c2453a;
        C2453A c2453a2;
        Object[] objArr;
        long[] jArr;
        C1714e c1714e2;
        C2453A c2453a3;
        C2453A c2453a4;
        Object[] objArr2;
        long[] jArr2;
        V.d dVar;
        V.d dVar2;
        C1714e c1714e3;
        C2453A c2453a5;
        C2453A c2453a6;
        C2453A c2453a7;
        int i7;
        V.d dVar3;
        Object[] objArr3;
        long[] jArr3;
        Object[] objArr4;
        long[] jArr4;
        int i10;
        V.d dVar4;
        V.d dVar5;
        Object[] objArr5;
        long[] jArr5;
        int i11;
        switch (this.f10165t) {
            case 0:
                V0 v02 = ((D0) this.f455m).f10152u;
                v02.getClass();
                AbstractC0727y.w(v02.d, null, null, new U0(v02, null), 3);
                return m7.q.f19267a;
            case 1:
                N5.Z z6 = ((D0) this.f455m).f10158x;
                Boolean bool = Boolean.FALSE;
                V8.f0 f0Var = z6.f4632K;
                f0Var.getClass();
                f0Var.m(null, bool);
                return m7.q.f19267a;
            case 2:
                D0 d02 = (D0) this.f455m;
                N5.Z z10 = d02.f10158x;
                ActionOwner actionOwner = ActionOwner.HOST;
                z10.getClass();
                A7.m.f(actionOwner, "actionOwner");
                z10.d(new C0451g(PlayerActionType.ToggleLeadVocal, new C0467x(z10, actionOwner, null), new C0452h(z10, 3)));
                d02.s();
                return m7.q.f19267a;
            case 3:
                D0 d03 = (D0) this.f455m;
                C0886u c0886u = d03.f10160y;
                V8.f0 f0Var2 = c0886u.f10387f;
                Boolean bool2 = Boolean.FALSE;
                f0Var2.getClass();
                f0Var2.m(null, bool2);
                c0886u.f10384a.w(((Number) c0886u.f10389h.getValue()).longValue(), ActionOwner.HOST);
                d03.s();
                return m7.q.f19267a;
            case 4:
                D0 d04 = (D0) this.f455m;
                d04.getClass();
                ActionOwner actionOwner2 = ActionOwner.HOST;
                N5.Z z11 = d04.f10158x;
                z11.getClass();
                A7.m.f(actionOwner2, "actionOwner");
                C1672v c1672v = z11.f4667p;
                if (c1672v == null) {
                    A7.m.j("mediaController");
                    throw null;
                }
                V8.f0 f0Var3 = z11.c.o;
                int iIntValue = ((Number) f0Var3.getValue()).intValue();
                f0Var3.m(null, Integer.valueOf(iIntValue != 0 ? (iIntValue == 1 || iIntValue != 2) ? 0 : 1 : 2));
                c1672v.h(((Number) f0Var3.getValue()).intValue());
                z11.p(PlaybackSetting.REPEAT_MODE, actionOwner2);
                d04.s();
                return m7.q.f19267a;
            case 5:
                ((D0) this.f455m).o();
                return m7.q.f19267a;
            case 6:
                ((h.x) this.f455m).e();
                return m7.q.f19267a;
            case 7:
                ((h.x) this.f455m).e();
                return m7.q.f19267a;
            case 8:
                C1714e c1714e4 = (C1714e) this.f455m;
                C2453A c2453a8 = c1714e4.f18329e;
                Object[] objArr6 = c2453a8.f22580b;
                long[] jArr6 = c2453a8.f22579a;
                int length = jArr6.length - 2;
                char c = 7;
                long j3 = -9187201950435737472L;
                C2453A c2453a9 = c1714e4.c;
                int i12 = 8;
                if (length >= 0) {
                    int i13 = 0;
                    while (true) {
                        long j10 = jArr6[i13];
                        if ((((~j10) << c) & j10 & j3) != j3) {
                            int i14 = 8 - ((~(i13 - length)) >>> 31);
                            int i15 = 0;
                            while (i15 < i14) {
                                if ((j10 & 255) < 128) {
                                    g0.n nVar = (g0.n) ((InterfaceC1721l) objArr6[(i13 << 3) + i15]);
                                    g0.n nVarF = nVar.l;
                                    if (nVarF.f14695x) {
                                        V.d dVar6 = null;
                                        while (nVarF != null) {
                                            if (nVarF instanceof C1727r) {
                                                c2453a9.a((C1727r) nVarF);
                                            } else {
                                                if ((nVarF.f14686n & 1024) != 0 && (nVarF instanceof AbstractC0164n)) {
                                                    g0.n nVar2 = ((AbstractC0164n) nVarF).f1690z;
                                                    objArr5 = objArr6;
                                                    int i16 = 0;
                                                    while (nVar2 != null) {
                                                        long[] jArr7 = jArr6;
                                                        if ((nVar2.f14686n & 1024) != 0) {
                                                            i16++;
                                                            if (i16 == 1) {
                                                                nVarF = nVar2;
                                                            } else {
                                                                if (dVar6 == null) {
                                                                    i11 = i16;
                                                                    dVar6 = new V.d(new g0.n[16]);
                                                                } else {
                                                                    i11 = i16;
                                                                }
                                                                if (nVarF != null) {
                                                                    dVar6.b(nVarF);
                                                                    nVarF = null;
                                                                }
                                                                dVar6.b(nVar2);
                                                                i16 = i11;
                                                            }
                                                        }
                                                        nVar2 = nVar2.f14688q;
                                                        jArr6 = jArr7;
                                                    }
                                                    jArr5 = jArr6;
                                                    if (i16 == 1) {
                                                    }
                                                    objArr6 = objArr5;
                                                    jArr6 = jArr5;
                                                }
                                                nVarF = AbstractC0156f.f(dVar6);
                                                objArr6 = objArr5;
                                                jArr6 = jArr5;
                                            }
                                            objArr5 = objArr6;
                                            jArr5 = jArr6;
                                            nVarF = AbstractC0156f.f(dVar6);
                                            objArr6 = objArr5;
                                            jArr6 = jArr5;
                                        }
                                        objArr4 = objArr6;
                                        jArr4 = jArr6;
                                        g0.n nVar3 = nVar.l;
                                        if (!nVar3.f14695x) {
                                            throw new IllegalStateException("visitChildren called on an unattached node");
                                        }
                                        V.d dVar7 = new V.d(new g0.n[16]);
                                        g0.n nVar4 = nVar3.f14688q;
                                        if (nVar4 == null) {
                                            AbstractC0156f.b(dVar7, nVar3);
                                        } else {
                                            dVar7.b(nVar4);
                                        }
                                        while (dVar7.m()) {
                                            g0.n nVarF2 = (g0.n) dVar7.o(dVar7.f9279n - 1);
                                            if ((nVarF2.o & 1024) == 0) {
                                                AbstractC0156f.b(dVar7, nVarF2);
                                            } else {
                                                while (true) {
                                                    if (nVarF2 == null) {
                                                    }
                                                    if ((nVarF2.f14686n & 1024) != 0) {
                                                        V.d dVar8 = null;
                                                        while (nVarF2 != null) {
                                                            if (nVarF2 instanceof C1727r) {
                                                                c2453a9.a((C1727r) nVarF2);
                                                            } else {
                                                                if ((nVarF2.f14686n & 1024) != 0 && (nVarF2 instanceof AbstractC0164n)) {
                                                                    g0.n nVar5 = ((AbstractC0164n) nVarF2).f1690z;
                                                                    int i17 = 0;
                                                                    while (nVar5 != null) {
                                                                        if ((nVar5.f14686n & 1024) != 0) {
                                                                            i17++;
                                                                            if (i17 == 1) {
                                                                                dVar5 = dVar7;
                                                                                nVarF2 = nVar5;
                                                                            } else {
                                                                                if (dVar8 == null) {
                                                                                    dVar5 = dVar7;
                                                                                    dVar8 = new V.d(new g0.n[16]);
                                                                                } else {
                                                                                    dVar5 = dVar7;
                                                                                }
                                                                                if (nVarF2 != null) {
                                                                                    dVar8.b(nVarF2);
                                                                                    nVarF2 = null;
                                                                                }
                                                                                dVar8.b(nVar5);
                                                                            }
                                                                        } else {
                                                                            dVar5 = dVar7;
                                                                        }
                                                                        nVar5 = nVar5.f14688q;
                                                                        dVar7 = dVar5;
                                                                    }
                                                                    dVar4 = dVar7;
                                                                    if (i17 == 1) {
                                                                    }
                                                                    dVar7 = dVar4;
                                                                }
                                                                nVarF2 = AbstractC0156f.f(dVar8);
                                                                dVar7 = dVar4;
                                                            }
                                                            dVar4 = dVar7;
                                                            nVarF2 = AbstractC0156f.f(dVar8);
                                                            dVar7 = dVar4;
                                                        }
                                                    } else {
                                                        nVarF2 = nVarF2.f14688q;
                                                        dVar7 = dVar7;
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        objArr4 = objArr6;
                                        jArr4 = jArr6;
                                    }
                                    i10 = 8;
                                } else {
                                    objArr4 = objArr6;
                                    jArr4 = jArr6;
                                    i10 = i12;
                                }
                                j10 >>= i10;
                                i15++;
                                i12 = i10;
                                objArr6 = objArr4;
                                jArr6 = jArr4;
                                break;
                            }
                            objArr3 = objArr6;
                            jArr3 = jArr6;
                            if (i14 == i12) {
                            }
                        } else {
                            objArr3 = objArr6;
                            jArr3 = jArr6;
                        }
                        if (i13 != length) {
                            i13++;
                            objArr6 = objArr3;
                            jArr6 = jArr3;
                            c = 7;
                            i12 = 8;
                            j3 = -9187201950435737472L;
                        }
                    }
                }
                c2453a8.b();
                C2453A c2453a10 = c1714e4.d;
                Object[] objArr7 = c2453a10.f22580b;
                long[] jArr8 = c2453a10.f22579a;
                int length2 = jArr8.length - 2;
                C2453A c2453a11 = c1714e4.f18330f;
                if (length2 >= 0) {
                    int i18 = 0;
                    while (true) {
                        long j11 = jArr8[i18];
                        if ((((~j11) << 7) & j11 & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i19 = 8 - ((~(i18 - length2)) >>> 31);
                            int i20 = 0;
                            while (i20 < i19) {
                                if ((j11 & 255) < 128) {
                                    InterfaceC1712c interfaceC1712c = (InterfaceC1712c) objArr7[(i18 << 3) + i20];
                                    g0.n nVar6 = (g0.n) interfaceC1712c;
                                    objArr2 = objArr7;
                                    g0.n nVar7 = nVar6.l;
                                    jArr2 = jArr8;
                                    boolean z12 = nVar7.f14695x;
                                    EnumC1726q enumC1726q = EnumC1726q.f18352n;
                                    if (z12) {
                                        boolean z13 = false;
                                        g0.n nVarF3 = nVar7;
                                        boolean z14 = true;
                                        C1727r c1727r = null;
                                        V.d dVar9 = null;
                                        while (nVarF3 != null) {
                                            EnumC1726q enumC1726q2 = enumC1726q;
                                            if (nVarF3 instanceof C1727r) {
                                                C1727r c1727r2 = (C1727r) nVarF3;
                                                if (c1727r != null) {
                                                    z13 = true;
                                                }
                                                if (c2453a9.c(c1727r2)) {
                                                    c2453a11.a(c1727r2);
                                                    z14 = false;
                                                }
                                                c1714e3 = c1714e4;
                                                c2453a5 = c2453a8;
                                                c2453a6 = c2453a10;
                                                c1727r = c1727r2;
                                            } else if ((nVarF3.f14686n & 1024) == 0 || !(nVarF3 instanceof AbstractC0164n)) {
                                                c1714e3 = c1714e4;
                                                c2453a5 = c2453a8;
                                                c2453a6 = c2453a10;
                                            } else {
                                                g0.n nVar8 = ((AbstractC0164n) nVarF3).f1690z;
                                                c2453a5 = c2453a8;
                                                int i21 = 0;
                                                while (nVar8 != null) {
                                                    C1714e c1714e5 = c1714e4;
                                                    if ((nVar8.f14686n & 1024) != 0) {
                                                        i21++;
                                                        if (i21 == 1) {
                                                            c2453a7 = c2453a10;
                                                            nVarF3 = nVar8;
                                                        } else {
                                                            if (dVar9 == null) {
                                                                i7 = i21;
                                                                c2453a7 = c2453a10;
                                                                dVar3 = new V.d(new g0.n[16]);
                                                            } else {
                                                                i7 = i21;
                                                                c2453a7 = c2453a10;
                                                                dVar3 = dVar9;
                                                            }
                                                            if (nVarF3 != null) {
                                                                dVar3.b(nVarF3);
                                                                nVarF3 = null;
                                                            }
                                                            dVar3.b(nVar8);
                                                            dVar9 = dVar3;
                                                            i21 = i7;
                                                        }
                                                    } else {
                                                        c2453a7 = c2453a10;
                                                    }
                                                    nVar8 = nVar8.f14688q;
                                                    c1714e4 = c1714e5;
                                                    c2453a10 = c2453a7;
                                                }
                                                c1714e3 = c1714e4;
                                                c2453a6 = c2453a10;
                                                if (i21 == 1) {
                                                }
                                                enumC1726q = enumC1726q2;
                                                c2453a8 = c2453a5;
                                                c1714e4 = c1714e3;
                                                c2453a10 = c2453a6;
                                            }
                                            nVarF3 = AbstractC0156f.f(dVar9);
                                            enumC1726q = enumC1726q2;
                                            c2453a8 = c2453a5;
                                            c1714e4 = c1714e3;
                                            c2453a10 = c2453a6;
                                        }
                                        c1714e2 = c1714e4;
                                        c2453a3 = c2453a8;
                                        c2453a4 = c2453a10;
                                        EnumC1726q enumC1726q3 = enumC1726q;
                                        g0.n nVar9 = nVar6.l;
                                        if (!nVar9.f14695x) {
                                            throw new IllegalStateException("visitChildren called on an unattached node");
                                        }
                                        V.d dVar10 = new V.d(new g0.n[16]);
                                        g0.n nVar10 = nVar9.f14688q;
                                        if (nVar10 == null) {
                                            AbstractC0156f.b(dVar10, nVar9);
                                        } else {
                                            dVar10.b(nVar10);
                                        }
                                        while (dVar10.m()) {
                                            g0.n nVarF4 = (g0.n) dVar10.o(dVar10.f9279n - 1);
                                            if ((nVarF4.o & 1024) == 0) {
                                                AbstractC0156f.b(dVar10, nVarF4);
                                            } else {
                                                while (nVarF4 != null) {
                                                    if ((nVarF4.f14686n & 1024) != 0) {
                                                        V.d dVar11 = null;
                                                        while (nVarF4 != null) {
                                                            if (nVarF4 instanceof C1727r) {
                                                                C1727r c1727r3 = (C1727r) nVarF4;
                                                                if (c1727r != null) {
                                                                    z13 = true;
                                                                }
                                                                if (c2453a9.c(c1727r3)) {
                                                                    c2453a11.a(c1727r3);
                                                                    z14 = false;
                                                                }
                                                                c1727r = c1727r3;
                                                            } else {
                                                                if ((nVarF4.f14686n & 1024) != 0 && (nVarF4 instanceof AbstractC0164n)) {
                                                                    g0.n nVar11 = ((AbstractC0164n) nVarF4).f1690z;
                                                                    int i22 = 0;
                                                                    while (nVar11 != null) {
                                                                        if ((nVar11.f14686n & 1024) != 0) {
                                                                            i22++;
                                                                            if (i22 == 1) {
                                                                                dVar2 = dVar10;
                                                                                nVarF4 = nVar11;
                                                                            } else {
                                                                                if (dVar11 == null) {
                                                                                    dVar2 = dVar10;
                                                                                    dVar11 = new V.d(new g0.n[16]);
                                                                                } else {
                                                                                    dVar2 = dVar10;
                                                                                }
                                                                                if (nVarF4 != null) {
                                                                                    dVar11.b(nVarF4);
                                                                                    nVarF4 = null;
                                                                                }
                                                                                dVar11.b(nVar11);
                                                                                nVar11 = nVar11.f14688q;
                                                                                dVar10 = dVar2;
                                                                            }
                                                                        } else {
                                                                            dVar2 = dVar10;
                                                                        }
                                                                        nVar11 = nVar11.f14688q;
                                                                        dVar10 = dVar2;
                                                                    }
                                                                    dVar = dVar10;
                                                                    if (i22 == 1) {
                                                                    }
                                                                    dVar10 = dVar;
                                                                }
                                                                nVarF4 = AbstractC0156f.f(dVar11);
                                                                dVar10 = dVar;
                                                            }
                                                            dVar = dVar10;
                                                            nVarF4 = AbstractC0156f.f(dVar11);
                                                            dVar10 = dVar;
                                                        }
                                                    } else {
                                                        nVarF4 = nVarF4.f14688q;
                                                        dVar10 = dVar10;
                                                    }
                                                }
                                            }
                                            dVar10 = dVar10;
                                        }
                                        if (z14) {
                                            interfaceC1712c.D(z13 ? AbstractC1713d.o(interfaceC1712c) : c1727r != null ? c1727r.I0() : enumC1726q3);
                                        }
                                        j11 >>= 8;
                                        i20++;
                                        objArr7 = objArr2;
                                        jArr8 = jArr2;
                                        c2453a8 = c2453a3;
                                        c1714e4 = c1714e2;
                                        c2453a10 = c2453a4;
                                    } else {
                                        interfaceC1712c.D(enumC1726q);
                                        c1714e2 = c1714e4;
                                        c2453a3 = c2453a8;
                                        c2453a4 = c2453a10;
                                    }
                                } else {
                                    c1714e2 = c1714e4;
                                    c2453a3 = c2453a8;
                                    c2453a4 = c2453a10;
                                    objArr2 = objArr7;
                                    jArr2 = jArr8;
                                }
                                j11 >>= 8;
                                i20++;
                                objArr7 = objArr2;
                                jArr8 = jArr2;
                                c2453a8 = c2453a3;
                                c1714e4 = c1714e2;
                                c2453a10 = c2453a4;
                            }
                            c1714e = c1714e4;
                            c2453a = c2453a8;
                            c2453a2 = c2453a10;
                            objArr = objArr7;
                            jArr = jArr8;
                            if (i19 == 8) {
                            }
                        } else {
                            c1714e = c1714e4;
                            c2453a = c2453a8;
                            c2453a2 = c2453a10;
                            objArr = objArr7;
                            jArr = jArr8;
                        }
                        if (i18 != length2) {
                            i18++;
                            objArr7 = objArr;
                            jArr8 = jArr;
                            c2453a8 = c2453a;
                            c1714e4 = c1714e;
                            c2453a10 = c2453a2;
                        }
                    }
                } else {
                    c1714e = c1714e4;
                    c2453a = c2453a8;
                    c2453a2 = c2453a10;
                }
                c2453a2.b();
                Object[] objArr8 = c2453a9.f22580b;
                long[] jArr9 = c2453a9.f22579a;
                int length3 = jArr9.length - 2;
                if (length3 >= 0) {
                    int i23 = 0;
                    while (true) {
                        long j12 = jArr9[i23];
                        if ((((~j12) << 7) & j12 & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i24 = 8 - ((~(i23 - length3)) >>> 31);
                            for (int i25 = 0; i25 < i24; i25++) {
                                if ((j12 & 255) < 128) {
                                    C1727r c1727r4 = (C1727r) objArr8[(i23 << 3) + i25];
                                    if (c1727r4.f14695x) {
                                        EnumC1726q enumC1726qI0 = c1727r4.I0();
                                        c1727r4.L0();
                                        if (enumC1726qI0 != c1727r4.I0() || c2453a11.c(c1727r4)) {
                                            AbstractC1713d.z(c1727r4);
                                        }
                                    }
                                }
                                j12 >>= 8;
                            }
                            if (i24 == 8) {
                            }
                        }
                        if (i23 != length3) {
                            i23++;
                        }
                    }
                }
                c2453a9.b();
                c2453a11.b();
                c1714e.f18328b.a();
                if (!c2453a.g()) {
                    l9.d.h0("Unprocessed FocusProperties nodes");
                    throw null;
                }
                if (!c2453a2.g()) {
                    l9.d.h0("Unprocessed FocusEvent nodes");
                    throw null;
                }
                if (c2453a9.g()) {
                    return m7.q.f19267a;
                }
                l9.d.h0("Unprocessed FocusTarget nodes");
                throw null;
            default:
                androidx.compose.ui.focus.b bVar = (androidx.compose.ui.focus.b) this.f455m;
                if (bVar.f11569f.I0() == EnumC1726q.f18352n) {
                    bVar.c.a();
                }
                return m7.q.f19267a;
        }
    }
}
