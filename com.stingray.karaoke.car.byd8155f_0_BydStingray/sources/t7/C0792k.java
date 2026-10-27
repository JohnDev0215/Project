package T7;

import F8.C0275j;
import Q7.AbstractC0662o;
import Q7.C0661n;
import Q7.InterfaceC0657j;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import o8.C2061f;

/* JADX INFO: renamed from: T7.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0792k extends AbstractC0791j {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f8846r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f8847s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C0275j f8848t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public y8.n f8849u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Set f8850v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public C0790i f8851w;

    /* JADX WARN: Illegal instructions before constructor call */
    public C0792k(InterfaceC0657j interfaceC0657j, C2061f c2061f, int i7, int i10, List list, E8.l lVar) {
        Q7.O o = Q7.N.c;
        if (interfaceC0657j == null) {
            W(0);
            throw null;
        }
        if (c2061f == null) {
            W(1);
            throw null;
        }
        if (i7 == 0) {
            W(2);
            throw null;
        }
        if (i10 == 0) {
            W(3);
            throw null;
        }
        if (lVar == null) {
            W(6);
            throw null;
        }
        super(lVar, interfaceC0657j, c2061f, o);
        this.f8846r = i7;
        this.f8847s = i10;
        this.f8848t = new C0275j(this, Collections.emptyList(), list, lVar);
    }

    public static /* synthetic */ void W(int i7) {
        String str;
        int i10;
        switch (i7) {
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                str = "@NotNull method %s.%s must not return null";
                break;
            case 12:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i7) {
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                i10 = 2;
                break;
            case 12:
            default:
                i10 = 3;
                break;
        }
        Object[] objArr = new Object[i10];
        switch (i7) {
            case 1:
                objArr[0] = "name";
                break;
            case 2:
                objArr[0] = "modality";
                break;
            case 3:
                objArr[0] = "kind";
                break;
            case 4:
                objArr[0] = "supertypes";
                break;
            case 5:
                objArr[0] = BYDAutoMultimediaDevice.PARAM_SOURCE;
                break;
            case 6:
                objArr[0] = "storageManager";
                break;
            case 7:
                objArr[0] = "unsubstitutedMemberScope";
                break;
            case 8:
                objArr[0] = "constructors";
                break;
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassDescriptorImpl";
                break;
            case 12:
                objArr[0] = "kotlinTypeRefiner";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        switch (i7) {
            case 9:
                objArr[1] = "getAnnotations";
                break;
            case 10:
                objArr[1] = "getTypeConstructor";
                break;
            case 11:
                objArr[1] = "getConstructors";
                break;
            case 12:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassDescriptorImpl";
                break;
            case 13:
                objArr[1] = "getUnsubstitutedMemberScope";
                break;
            case 14:
                objArr[1] = "getStaticScope";
                break;
            case 15:
                objArr[1] = "getKind";
                break;
            case 16:
                objArr[1] = "getModality";
                break;
            case 17:
                objArr[1] = "getVisibility";
                break;
            case 18:
                objArr[1] = "getDeclaredTypeParameters";
                break;
            case 19:
                objArr[1] = "getSealedSubclasses";
                break;
        }
        switch (i7) {
            case 7:
            case 8:
                objArr[2] = "initialize";
                break;
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                break;
            case 12:
                objArr[2] = "getUnsubstitutedMemberScope";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i7) {
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                throw new IllegalStateException(str2);
            case 12:
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    @Override // T7.y
    public final y8.n A(G8.f fVar) {
        y8.n nVar = this.f8849u;
        if (nVar != null) {
            return nVar;
        }
        W(13);
        throw null;
    }

    @Override // Q7.InterfaceC0654g
    public final F8.K C() {
        C0275j c0275j = this.f8848t;
        if (c0275j != null) {
            return c0275j;
        }
        W(10);
        throw null;
    }

    @Override // Q7.InterfaceC0652e
    public final int E() {
        int i7 = this.f8847s;
        if (i7 != 0) {
            return i7;
        }
        W(15);
        throw null;
    }

    @Override // Q7.InterfaceC0652e
    public final boolean F() {
        return false;
    }

    @Override // Q7.InterfaceC0652e
    public final Collection H() {
        Set set = this.f8850v;
        if (set != null) {
            return set;
        }
        W(11);
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
        W(19);
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
        W(17);
        throw null;
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0670x
    public final int h() {
        int i7 = this.f8846r;
        if (i7 != 0) {
            return i7;
        }
        W(16);
        throw null;
    }

    @Override // R7.a
    public final R7.h k() {
        return R7.g.f7535a;
    }

    @Override // Q7.InterfaceC0652e
    public final Q7.S m0() {
        return null;
    }

    @Override // Q7.InterfaceC0652e
    public final boolean n() {
        return false;
    }

    public final void o0(y8.n nVar, Set set, C0790i c0790i) {
        this.f8849u = nVar;
        this.f8850v = set;
        this.f8851w = c0790i;
    }

    @Override // Q7.InterfaceC0652e
    public final C0790i p0() {
        return this.f8851w;
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
        W(18);
        throw null;
    }

    public String toString() {
        return "class " + getName();
    }

    @Override // Q7.InterfaceC0670x
    public final boolean y0() {
        return false;
    }
}
