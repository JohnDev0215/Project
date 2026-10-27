package T7;

import Q5.C0606p0;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0658k;
import Q7.InterfaceC0659l;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.util.List;
import o8.C2061f;

/* JADX INFO: renamed from: T7.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0789h extends AbstractC0795n implements Q7.Q {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f8837q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f8838r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f8839s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final E8.i f8840t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final E8.i f8841u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final E8.l f8842v;

    /* JADX WARN: Illegal instructions before constructor call */
    public AbstractC0789h(E8.o oVar, InterfaceC0657j interfaceC0657j, R7.h hVar, C2061f c2061f, int i7, boolean z6, int i10, Q7.O o) {
        Q7.O o4 = Q7.N.c;
        if (oVar == null) {
            K0(0);
            throw null;
        }
        if (interfaceC0657j == null) {
            K0(1);
            throw null;
        }
        if (hVar == null) {
            K0(2);
            throw null;
        }
        if (c2061f == null) {
            K0(3);
            throw null;
        }
        if (i7 == 0) {
            K0(4);
            throw null;
        }
        if (o == null) {
            K0(6);
            throw null;
        }
        super(interfaceC0657j, hVar, c2061f, o4);
        this.f8837q = i7;
        this.f8838r = z6;
        this.f8839s = i10;
        C0787f c0787f = new C0787f(this, oVar, o);
        E8.l lVar = (E8.l) oVar;
        this.f8840t = new E8.i(lVar, c0787f);
        this.f8841u = new E8.i(lVar, new C0606p0(this, 1, c2061f));
        this.f8842v = lVar;
    }

    public static /* synthetic */ void K0(int i7) {
        String str;
        int i10;
        switch (i7) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
                str = "@NotNull method %s.%s must not return null";
                break;
            case 12:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i7) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
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
                objArr[0] = "containingDeclaration";
                break;
            case 2:
                objArr[0] = "annotations";
                break;
            case 3:
                objArr[0] = "name";
                break;
            case 4:
                objArr[0] = "variance";
                break;
            case 5:
                objArr[0] = BYDAutoMultimediaDevice.PARAM_SOURCE;
                break;
            case 6:
                objArr[0] = "supertypeLoopChecker";
                break;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractTypeParameterDescriptor";
                break;
            case 12:
                objArr[0] = "bounds";
                break;
            default:
                objArr[0] = "storageManager";
                break;
        }
        switch (i7) {
            case 7:
                objArr[1] = "getVariance";
                break;
            case 8:
                objArr[1] = "getUpperBounds";
                break;
            case 9:
                objArr[1] = "getTypeConstructor";
                break;
            case 10:
                objArr[1] = "getDefaultType";
                break;
            case 11:
                objArr[1] = "getOriginal";
                break;
            case 12:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractTypeParameterDescriptor";
                break;
            case 13:
                objArr[1] = "processBoundsWithoutCycles";
                break;
            case 14:
                objArr[1] = "getStorageManager";
                break;
        }
        switch (i7) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
                break;
            case 12:
                objArr[2] = "processBoundsWithoutCycles";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i7) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
                throw new IllegalStateException(str2);
            case 12:
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    @Override // Q7.InterfaceC0654g
    public final F8.K C() {
        F8.K k10 = (F8.K) this.f8840t.a();
        if (k10 != null) {
            return k10;
        }
        K0(9);
        throw null;
    }

    @Override // Q7.Q
    public final E8.o D() {
        E8.l lVar = this.f8842v;
        if (lVar != null) {
            return lVar;
        }
        K0(14);
        throw null;
    }

    @Override // Q7.Q
    public final boolean Q() {
        return false;
    }

    @Override // Q7.Q
    public final boolean R() {
        return this.f8838r;
    }

    @Override // Q7.InterfaceC0657j
    public final Object S(InterfaceC0659l interfaceC0659l, Object obj) {
        return interfaceC0659l.z(this, obj);
    }

    @Override // T7.AbstractC0795n, T7.AbstractC0794m, Q7.InterfaceC0657j, Q7.InterfaceC0654g
    /* JADX INFO: renamed from: a */
    public final InterfaceC0654g b1() {
        return this;
    }

    @Override // T7.AbstractC0795n
    public final InterfaceC0658k b1() {
        return this;
    }

    public List c1(List list) {
        return list;
    }

    public abstract List d1();

    @Override // Q7.Q
    public final int e0() {
        int i7 = this.f8837q;
        if (i7 != 0) {
            return i7;
        }
        K0(7);
        throw null;
    }

    @Override // Q7.Q
    public final int getIndex() {
        return this.f8839s;
    }

    @Override // Q7.Q
    public final List getUpperBounds() {
        List listO = ((C0788g) C()).o();
        if (listO != null) {
            return listO;
        }
        K0(8);
        throw null;
    }

    @Override // Q7.InterfaceC0654g
    public final F8.A j() {
        F8.A a10 = (F8.A) this.f8841u.a();
        if (a10 != null) {
            return a10;
        }
        K0(10);
        throw null;
    }

    @Override // T7.AbstractC0795n, T7.AbstractC0794m, Q7.InterfaceC0657j, Q7.InterfaceC0654g
    /* JADX INFO: renamed from: a */
    public final InterfaceC0657j b1() {
        return this;
    }

    @Override // T7.AbstractC0795n, T7.AbstractC0794m, Q7.InterfaceC0657j, Q7.InterfaceC0654g
    /* JADX INFO: renamed from: a */
    public final Q7.Q b1() {
        return this;
    }
}
