package T7;

import F8.C0275j;
import Q7.C0661n;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public final class A extends AbstractC0791j {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f8760r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f8761s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public C0661n f8762t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public C0275j f8763u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ArrayList f8764v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final ArrayList f8765w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final E8.b f8766x;

    /* JADX WARN: Illegal instructions before constructor call */
    public A(P7.k kVar, C2061f c2061f, E8.b bVar) {
        Q7.O o = Q7.N.c;
        if (c2061f == null) {
            W(2);
            throw null;
        }
        if (bVar == null) {
            W(4);
            throw null;
        }
        super(bVar, kVar, c2061f, o);
        this.f8765w = new ArrayList();
        this.f8766x = bVar;
        this.f8760r = 2;
    }

    public static /* synthetic */ void W(int i7) {
        String str;
        int i10;
        switch (i7) {
            case 5:
            case 7:
            case 8:
            case 10:
            case 11:
            case 13:
            case 15:
            case 17:
            case 18:
            case 19:
                str = "@NotNull method %s.%s must not return null";
                break;
            case 6:
            case 9:
            case 12:
            case 14:
            case 16:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i7) {
            case 5:
            case 7:
            case 8:
            case 10:
            case 11:
            case 13:
            case 15:
            case 17:
            case 18:
            case 19:
                i10 = 2;
                break;
            case 6:
            case 9:
            case 12:
            case 14:
            case 16:
            default:
                i10 = 3;
                break;
        }
        Object[] objArr = new Object[i10];
        switch (i7) {
            case 1:
                objArr[0] = "kind";
                break;
            case 2:
                objArr[0] = "name";
                break;
            case 3:
                objArr[0] = BYDAutoMultimediaDevice.PARAM_SOURCE;
                break;
            case 4:
                objArr[0] = "storageManager";
                break;
            case 5:
            case 7:
            case 8:
            case 10:
            case 11:
            case 13:
            case 15:
            case 17:
            case 18:
            case 19:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/MutableClassDescriptor";
                break;
            case 6:
                objArr[0] = "modality";
                break;
            case 9:
                objArr[0] = "visibility";
                break;
            case 12:
                objArr[0] = "supertype";
                break;
            case 14:
                objArr[0] = "typeParameters";
                break;
            case 16:
                objArr[0] = "kotlinTypeRefiner";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        switch (i7) {
            case 5:
                objArr[1] = "getAnnotations";
                break;
            case 6:
            case 9:
            case 12:
            case 14:
            case 16:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/MutableClassDescriptor";
                break;
            case 7:
                objArr[1] = "getModality";
                break;
            case 8:
                objArr[1] = "getKind";
                break;
            case 10:
                objArr[1] = "getVisibility";
                break;
            case 11:
                objArr[1] = "getTypeConstructor";
                break;
            case 13:
                objArr[1] = "getConstructors";
                break;
            case 15:
                objArr[1] = "getDeclaredTypeParameters";
                break;
            case 17:
                objArr[1] = "getUnsubstitutedMemberScope";
                break;
            case 18:
                objArr[1] = "getStaticScope";
                break;
            case 19:
                objArr[1] = "getSealedSubclasses";
                break;
        }
        switch (i7) {
            case 5:
            case 7:
            case 8:
            case 10:
            case 11:
            case 13:
            case 15:
            case 17:
            case 18:
            case 19:
                break;
            case 6:
                objArr[2] = "setModality";
                break;
            case 9:
                objArr[2] = "setVisibility";
                break;
            case 12:
                objArr[2] = "addSupertype";
                break;
            case 14:
                objArr[2] = "setTypeParameterDescriptors";
                break;
            case 16:
                objArr[2] = "getUnsubstitutedMemberScope";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i7) {
            case 5:
            case 7:
            case 8:
            case 10:
            case 11:
            case 13:
            case 15:
            case 17:
            case 18:
            case 19:
                throw new IllegalStateException(str2);
            case 6:
            case 9:
            case 12:
            case 14:
            case 16:
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    @Override // T7.y
    public final y8.n A(G8.f fVar) {
        return y8.m.f24213b;
    }

    @Override // Q7.InterfaceC0654g
    public final F8.K C() {
        C0275j c0275j = this.f8763u;
        if (c0275j != null) {
            return c0275j;
        }
        W(11);
        throw null;
    }

    @Override // Q7.InterfaceC0652e
    public final int E() {
        int i7 = this.f8760r;
        if (i7 != 0) {
            return i7;
        }
        W(8);
        throw null;
    }

    @Override // Q7.InterfaceC0652e
    public final boolean F() {
        return false;
    }

    @Override // Q7.InterfaceC0652e
    public final Collection H() {
        Set setEmptySet = Collections.emptySet();
        if (setEmptySet != null) {
            return setEmptySet;
        }
        W(13);
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
        C0661n c0661n = this.f8762t;
        if (c0661n != null) {
            return c0661n;
        }
        W(10);
        throw null;
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0670x
    public final int h() {
        int i7 = this.f8761s;
        if (i7 != 0) {
            return i7;
        }
        W(7);
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
        ArrayList arrayList = this.f8764v;
        if (arrayList != null) {
            return arrayList;
        }
        W(15);
        throw null;
    }

    public final String toString() {
        return AbstractC0794m.a1(this);
    }

    @Override // Q7.InterfaceC0670x
    public final boolean y0() {
        return false;
    }
}
