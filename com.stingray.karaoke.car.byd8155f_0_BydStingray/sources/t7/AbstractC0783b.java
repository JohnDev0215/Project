package T7;

import F8.T;
import F8.W;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0659l;
import java.util.Collections;
import java.util.List;
import o8.C2061f;
import v8.AbstractC2564d;

/* JADX INFO: renamed from: T7.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0783b extends y {
    public final C2061f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final E8.i f8830m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final E8.i f8831n;
    public final E8.i o;

    public AbstractC0783b(E8.o oVar, C2061f c2061f) {
        if (oVar == null) {
            W(0);
            throw null;
        }
        if (c2061f == null) {
            W(1);
            throw null;
        }
        this.l = c2061f;
        E8.l lVar = (E8.l) oVar;
        this.f8830m = new E8.i(lVar, new C0782a(this, 0));
        this.f8831n = new E8.i(lVar, new C0782a(this, 1));
        this.o = new E8.i(lVar, new C0782a(this, 2));
    }

    public static /* synthetic */ void W(int i7) {
        String str = (i7 == 2 || i7 == 3 || i7 == 4 || i7 == 5 || i7 == 6 || i7 == 9 || i7 == 12 || i7 == 14 || i7 == 16 || i7 == 17 || i7 == 19 || i7 == 20) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 2 || i7 == 3 || i7 == 4 || i7 == 5 || i7 == 6 || i7 == 9 || i7 == 12 || i7 == 14 || i7 == 16 || i7 == 17 || i7 == 19 || i7 == 20) ? 2 : 3];
        switch (i7) {
            case 1:
                objArr[0] = "name";
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 9:
            case 12:
            case 14:
            case 16:
            case 17:
            case 19:
            case 20:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractClassDescriptor";
                break;
            case 7:
            case 13:
                objArr[0] = "typeArguments";
                break;
            case 8:
            case 11:
                objArr[0] = "kotlinTypeRefiner";
                break;
            case 10:
            case 15:
                objArr[0] = "typeSubstitution";
                break;
            case 18:
                objArr[0] = "substitutor";
                break;
            default:
                objArr[0] = "storageManager";
                break;
        }
        if (i7 == 2) {
            objArr[1] = "getName";
        } else if (i7 == 3) {
            objArr[1] = "getOriginal";
        } else if (i7 == 4) {
            objArr[1] = "getUnsubstitutedInnerClassesScope";
        } else if (i7 == 5) {
            objArr[1] = "getThisAsReceiverParameter";
        } else if (i7 == 6) {
            objArr[1] = "getContextReceivers";
        } else if (i7 == 9 || i7 == 12 || i7 == 14 || i7 == 16) {
            objArr[1] = "getMemberScope";
        } else if (i7 == 17) {
            objArr[1] = "getUnsubstitutedMemberScope";
        } else if (i7 == 19) {
            objArr[1] = "substitute";
        } else if (i7 != 20) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractClassDescriptor";
        } else {
            objArr[1] = "getDefaultType";
        }
        switch (i7) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 9:
            case 12:
            case 14:
            case 16:
            case 17:
            case 19:
            case 20:
                break;
            case 7:
            case 8:
            case 10:
            case 11:
            case 13:
            case 15:
                objArr[2] = "getMemberScope";
                break;
            case 18:
                objArr[2] = "substitute";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i7 != 2 && i7 != 3 && i7 != 4 && i7 != 5 && i7 != 6 && i7 != 9 && i7 != 12 && i7 != 14 && i7 != 16 && i7 != 17 && i7 != 19 && i7 != 20) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // Q7.InterfaceC0652e
    public List A0() {
        List listEmptyList = Collections.emptyList();
        if (listEmptyList != null) {
            return listEmptyList;
        }
        W(6);
        throw null;
    }

    @Override // Q7.InterfaceC0652e
    public final y8.n C0(T t10) {
        AbstractC2564d.i(r8.d.d(this));
        y8.n nVarI = i(t10, G8.f.f2724a);
        if (nVarI != null) {
            return nVarI;
        }
        W(16);
        throw null;
    }

    @Override // Q7.InterfaceC0652e
    public final u J0() {
        u uVar = (u) this.o.a();
        if (uVar != null) {
            return uVar;
        }
        W(5);
        throw null;
    }

    @Override // Q7.InterfaceC0657j
    public final Object S(InterfaceC0659l interfaceC0659l, Object obj) {
        return interfaceC0659l.I(this, obj);
    }

    @Override // T7.y, Q7.InterfaceC0652e, Q7.InterfaceC0657j, Q7.InterfaceC0654g
    /* JADX INFO: renamed from: a */
    public final InterfaceC0654g b1() {
        return this;
    }

    @Override // Q7.InterfaceC0657j
    public final C2061f getName() {
        C2061f c2061f = this.l;
        if (c2061f != null) {
            return c2061f;
        }
        W(2);
        throw null;
    }

    @Override // T7.y
    public y8.n i(T t10, G8.f fVar) {
        if (!t10.e()) {
            return new y8.s(A(fVar), new W(t10));
        }
        y8.n nVarA = A(fVar);
        if (nVarA != null) {
            return nVarA;
        }
        W(12);
        throw null;
    }

    @Override // Q7.P
    /* JADX INFO: renamed from: i0, reason: merged with bridge method [inline-methods] */
    public InterfaceC0652e f(W w10) {
        if (w10 != null) {
            return w10.f2455a.e() ? this : new x(this, w10);
        }
        W(18);
        throw null;
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0654g
    public final F8.A j() {
        F8.A a10 = (F8.A) this.f8830m.a();
        if (a10 != null) {
            return a10;
        }
        W(20);
        throw null;
    }

    @Override // Q7.InterfaceC0652e
    public y8.n l0() {
        y8.n nVar = (y8.n) this.f8831n.a();
        if (nVar != null) {
            return nVar;
        }
        W(4);
        throw null;
    }

    @Override // Q7.InterfaceC0652e
    public y8.n w0() {
        AbstractC2564d.i(r8.d.d(this));
        y8.n nVarA = A(G8.f.f2724a);
        if (nVarA != null) {
            return nVarA;
        }
        W(17);
        throw null;
    }

    @Override // T7.y, Q7.InterfaceC0657j, Q7.InterfaceC0654g
    /* JADX INFO: renamed from: a */
    public final InterfaceC0657j b1() {
        return this;
    }

    @Override // T7.y, Q7.InterfaceC0652e, Q7.InterfaceC0657j, Q7.InterfaceC0654g
    /* JADX INFO: renamed from: a */
    public final InterfaceC0652e b1() {
        return this;
    }
}
