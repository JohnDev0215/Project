package b8;

import A7.m;
import F8.AbstractC0287w;
import F8.W;
import L8.i;
import L8.s;
import Q7.C0661n;
import Q7.InterfaceC0648a;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0666t;
import Q7.N;
import T7.C0799s;
import T7.K;
import T7.t;
import T7.u;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import c8.C1038c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import l9.l;
import n7.v;
import o8.C2061f;

/* JADX INFO: renamed from: b8.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1011f extends K implements InterfaceC1006a {

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public static final C1010e f12327R = new C1010e();

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public static final C1010e f12328S = new C1010e();

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public int f12329P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public final boolean f12330Q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1011f(InterfaceC0657j interfaceC0657j, K k10, R7.h hVar, C2061f c2061f, int i7, N n10, boolean z6) {
        super(interfaceC0657j, k10, hVar, c2061f, i7, n10);
        if (interfaceC0657j == null) {
            K0(0);
            throw null;
        }
        if (hVar == null) {
            K0(1);
            throw null;
        }
        if (c2061f == null) {
            K0(2);
            throw null;
        }
        if (i7 == 0) {
            K0(3);
            throw null;
        }
        this.f12329P = 0;
        this.f12330Q = z6;
    }

    public static /* synthetic */ void K0(int i7) {
        String str = (i7 == 13 || i7 == 18 || i7 == 21) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 13 || i7 == 18 || i7 == 21) ? 2 : 3];
        switch (i7) {
            case 1:
            case 6:
            case 16:
                objArr[0] = "annotations";
                break;
            case 2:
            case 7:
                objArr[0] = "name";
                break;
            case 3:
            case 15:
                objArr[0] = "kind";
                break;
            case 4:
            case 8:
            case 17:
                objArr[0] = BYDAutoMultimediaDevice.PARAM_SOURCE;
                break;
            case 5:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 9:
                objArr[0] = "contextReceiverParameters";
                break;
            case 10:
                objArr[0] = "typeParameters";
                break;
            case 11:
                objArr[0] = "unsubstitutedValueParameters";
                break;
            case 12:
                objArr[0] = "visibility";
                break;
            case 13:
            case 18:
            case 21:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaMethodDescriptor";
                break;
            case 14:
                objArr[0] = "newOwner";
                break;
            case 19:
                objArr[0] = "enhancedValueParameterTypes";
                break;
            case 20:
                objArr[0] = "enhancedReturnType";
                break;
        }
        if (i7 == 13) {
            objArr[1] = "initialize";
        } else if (i7 == 18) {
            objArr[1] = "createSubstitutedCopy";
        } else if (i7 != 21) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaMethodDescriptor";
        } else {
            objArr[1] = "enhance";
        }
        switch (i7) {
            case 5:
            case 6:
            case 7:
            case 8:
                objArr[2] = "createJavaMethod";
                break;
            case 9:
            case 10:
            case 11:
            case 12:
                objArr[2] = "initialize";
                break;
            case 13:
            case 18:
            case 21:
                break;
            case 14:
            case 15:
            case 16:
            case 17:
                objArr[2] = "createSubstitutedCopy";
                break;
            case 19:
            case 20:
                objArr[2] = "enhance";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i7 != 13 && i7 != 18 && i7 != 21) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static C1011f r1(InterfaceC0657j interfaceC0657j, C1038c c1038c, C2061f c2061f, V7.g gVar, boolean z6) {
        if (interfaceC0657j == null) {
            K0(5);
            throw null;
        }
        if (c2061f != null) {
            return new C1011f(interfaceC0657j, null, c1038c, c2061f, 1, gVar, z6);
        }
        K0(7);
        throw null;
    }

    @Override // T7.t, Q7.InterfaceC0649b
    public final boolean T() {
        return android.hardware.bydauto.ac.tsimpl.a.a(this.f12329P);
    }

    @Override // T7.K, T7.t
    public final t e1(int i7, InterfaceC0657j interfaceC0657j, InterfaceC0666t interfaceC0666t, N n10, R7.h hVar, C2061f c2061f) {
        if (interfaceC0657j == null) {
            K0(14);
            throw null;
        }
        if (i7 == 0) {
            K0(15);
            throw null;
        }
        if (hVar == null) {
            K0(16);
            throw null;
        }
        K k10 = (K) interfaceC0666t;
        if (c2061f == null) {
            c2061f = getName();
        }
        C1011f c1011f = new C1011f(interfaceC0657j, k10, hVar, c2061f, i7, n10, this.f12330Q);
        int i10 = this.f12329P;
        boolean z6 = false;
        if (i10 != 1) {
            if (i10 == 2) {
                z6 = true;
            } else if (i10 != 3) {
                if (i10 != 4) {
                    throw null;
                }
                z6 = true;
            }
        }
        c1011f.s1(z6, android.hardware.bydauto.ac.tsimpl.a.a(i10));
        return c1011f;
    }

    @Override // T7.K
    public final K q1(u uVar, u uVar2, List list, List list2, List list3, AbstractC0287w abstractC0287w, int i7, C0661n c0661n, v vVar) {
        L8.f fVar;
        if (list == null) {
            K0(9);
            throw null;
        }
        if (list2 == null) {
            K0(10);
            throw null;
        }
        if (list3 == null) {
            K0(11);
            throw null;
        }
        if (c0661n == null) {
            K0(12);
            throw null;
        }
        super.q1(uVar, uVar2, list, list2, list3, abstractC0287w, i7, c0661n, vVar);
        for (i iVar : s.d) {
            iVar.getClass();
            C2061f c2061f = iVar.f4083a;
            if (c2061f == null || m.a(getName(), c2061f)) {
                Q8.g gVar = iVar.f4084b;
                if (gVar != null) {
                    String strB = getName().b();
                    m.e(strB, "functionDescriptor.name.asString()");
                    if (!gVar.b(strB)) {
                        continue;
                    }
                }
                Collection collection = iVar.c;
                if (collection == null || collection.contains(getName())) {
                    L8.e[] eVarArr = iVar.f4085e;
                    int length = eVarArr.length;
                    int i10 = 0;
                    while (true) {
                        if (i10 >= length) {
                            fVar = ((String) iVar.d.b(this)) != null ? new L8.f(false) : L8.f.c;
                        } else {
                            if (eVarArr[i10].b(this) != null) {
                                fVar = new L8.f(false);
                                break;
                            }
                            i10++;
                        }
                    }
                    this.f8906y = fVar.f4073a;
                    return this;
                }
            }
        }
        fVar = L8.f.f4072b;
        this.f8906y = fVar.f4073a;
        return this;
    }

    public final void s1(boolean z6, boolean z10) {
        this.f12329P = z6 ? z10 ? 4 : 2 : z10 ? 3 : 1;
    }

    @Override // b8.InterfaceC1006a
    public final InterfaceC1006a y(AbstractC0287w abstractC0287w, ArrayList arrayList, AbstractC0287w abstractC0287w2, m7.i iVar) {
        ArrayList arrayListH = l.H(arrayList, D0(), this);
        u uVarK = abstractC0287w == null ? null : r8.m.k(this, abstractC0287w, R7.g.f7535a);
        C0799s c0799sI1 = i1(W.f2454b);
        c0799sI1.f8875r = arrayListH;
        c0799sI1.f8879v = abstractC0287w2;
        c0799sI1.f8877t = uVarK;
        c0799sI1.f8863A = true;
        c0799sI1.f8883z = true;
        C1011f c1011f = (C1011f) c0799sI1.f8870I.f1(c0799sI1);
        if (iVar != null) {
            c1011f.j1((InterfaceC0648a) iVar.l, iVar.f19259m);
        }
        if (c1011f != null) {
            return c1011f;
        }
        K0(21);
        throw null;
    }
}
