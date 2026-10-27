package T7;

import F8.C0275j;
import Q7.AbstractC0662o;
import Q7.C0661n;
import Q7.InterfaceC0652e;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import o8.C2061f;

/* JADX INFO: renamed from: T7.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0798q extends AbstractC0791j {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C0275j f8859r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final C0797p f8860s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final E8.i f8861t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final R7.h f8862u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0798q(E8.o oVar, InterfaceC0652e interfaceC0652e, F8.A a10, C2061f c2061f, E8.i iVar, R7.h hVar, Q7.N n10) {
        super(oVar, interfaceC0652e, c2061f, n10);
        if (oVar == null) {
            W(6);
            throw null;
        }
        if (interfaceC0652e == null) {
            W(7);
            throw null;
        }
        if (a10 == null) {
            W(8);
            throw null;
        }
        if (c2061f == null) {
            W(9);
            throw null;
        }
        if (iVar == null) {
            W(10);
            throw null;
        }
        this.f8862u = hVar;
        this.f8859r = new C0275j(this, Collections.emptyList(), Collections.singleton(a10), oVar);
        this.f8860s = new C0797p(this, oVar);
        this.f8861t = iVar;
    }

    public static /* synthetic */ void W(int i7) {
        String str;
        int i10;
        switch (i7) {
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i7) {
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                i10 = 2;
                break;
            default:
                i10 = 3;
                break;
        }
        Object[] objArr = new Object[i10];
        switch (i7) {
            case 1:
                objArr[0] = "enumClass";
                break;
            case 2:
            case 9:
                objArr[0] = "name";
                break;
            case 3:
            case 10:
                objArr[0] = "enumMemberNames";
                break;
            case 4:
            case 11:
                objArr[0] = "annotations";
                break;
            case 5:
            case 12:
                objArr[0] = BYDAutoMultimediaDevice.PARAM_SOURCE;
                break;
            case 6:
            default:
                objArr[0] = "storageManager";
                break;
            case 7:
                objArr[0] = "containingClass";
                break;
            case 8:
                objArr[0] = "supertype";
                break;
            case 13:
                objArr[0] = "kotlinTypeRefiner";
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/EnumEntrySyntheticClassDescriptor";
                break;
        }
        switch (i7) {
            case 14:
                objArr[1] = "getUnsubstitutedMemberScope";
                break;
            case 15:
                objArr[1] = "getStaticScope";
                break;
            case 16:
                objArr[1] = "getConstructors";
                break;
            case 17:
                objArr[1] = "getTypeConstructor";
                break;
            case 18:
                objArr[1] = "getKind";
                break;
            case 19:
                objArr[1] = "getModality";
                break;
            case 20:
                objArr[1] = "getVisibility";
                break;
            case 21:
                objArr[1] = "getAnnotations";
                break;
            case 22:
                objArr[1] = "getDeclaredTypeParameters";
                break;
            case 23:
                objArr[1] = "getSealedSubclasses";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/EnumEntrySyntheticClassDescriptor";
                break;
        }
        switch (i7) {
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                objArr[2] = "<init>";
                break;
            case 13:
                objArr[2] = "getUnsubstitutedMemberScope";
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                break;
            default:
                objArr[2] = "create";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i7) {
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                throw new IllegalStateException(str2);
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    public static C0798q o0(E8.o oVar, InterfaceC0652e interfaceC0652e, C2061f c2061f, E8.i iVar, R7.h hVar, Q7.N n10) {
        if (oVar == null) {
            W(0);
            throw null;
        }
        if (interfaceC0652e == null) {
            W(1);
            throw null;
        }
        if (c2061f == null) {
            W(2);
            throw null;
        }
        if (iVar != null) {
            return new C0798q(oVar, interfaceC0652e, interfaceC0652e.j(), c2061f, iVar, hVar, n10);
        }
        W(3);
        throw null;
    }

    @Override // T7.y
    public final y8.n A(G8.f fVar) {
        C0797p c0797p = this.f8860s;
        if (c0797p != null) {
            return c0797p;
        }
        W(14);
        throw null;
    }

    @Override // Q7.InterfaceC0654g
    public final F8.K C() {
        C0275j c0275j = this.f8859r;
        if (c0275j != null) {
            return c0275j;
        }
        W(17);
        throw null;
    }

    @Override // Q7.InterfaceC0652e
    public final int E() {
        return 4;
    }

    @Override // Q7.InterfaceC0652e
    public final boolean F() {
        return false;
    }

    @Override // Q7.InterfaceC0652e
    public final Collection H() {
        List listEmptyList = Collections.emptyList();
        if (listEmptyList != null) {
            return listEmptyList;
        }
        W(16);
        throw null;
    }

    @Override // Q7.InterfaceC0652e
    public final boolean H0() {
        return false;
    }

    @Override // Q7.InterfaceC0652e
    public final boolean L() {
        return false;
    }

    @Override // Q7.InterfaceC0652e
    public final Collection X() {
        List listEmptyList = Collections.emptyList();
        if (listEmptyList != null) {
            return listEmptyList;
        }
        W(23);
        throw null;
    }

    @Override // Q7.InterfaceC0652e
    public final boolean b0() {
        return false;
    }

    @Override // Q7.InterfaceC0670x
    public final boolean c0() {
        return false;
    }

    @Override // Q7.InterfaceC0655h
    public final boolean d0() {
        return false;
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0660m, Q7.InterfaceC0670x
    public final C0661n e() {
        C0661n c0661n = AbstractC0662o.f7191e;
        if (c0661n != null) {
            return c0661n;
        }
        W(20);
        throw null;
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0670x
    public final int h() {
        return 1;
    }

    @Override // R7.a
    public final R7.h k() {
        R7.h hVar = this.f8862u;
        if (hVar != null) {
            return hVar;
        }
        W(21);
        throw null;
    }

    @Override // Q7.InterfaceC0652e
    public final Q7.S m0() {
        return null;
    }

    @Override // Q7.InterfaceC0652e
    public final boolean n() {
        return false;
    }

    @Override // Q7.InterfaceC0652e
    public final C0790i p0() {
        return null;
    }

    @Override // Q7.InterfaceC0652e
    public final y8.n q0() {
        return y8.m.f24213b;
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0655h
    public final List s() {
        List listEmptyList = Collections.emptyList();
        if (listEmptyList != null) {
            return listEmptyList;
        }
        W(22);
        throw null;
    }

    public final String toString() {
        return "enum entry " + getName();
    }

    @Override // Q7.InterfaceC0670x
    public final boolean y0() {
        return false;
    }
}
