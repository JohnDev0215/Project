package T7;

import F8.AbstractC0268c;
import Q7.InterfaceC0657j;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.util.ArrayList;
import java.util.List;
import o8.C2061f;
import v8.AbstractC2564d;

/* JADX INFO: loaded from: classes.dex */
public final class O extends AbstractC0789h {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final ArrayList f8819w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f8820x;

    /* JADX WARN: Illegal instructions before constructor call */
    public O(InterfaceC0657j interfaceC0657j, R7.h hVar, boolean z6, int i7, C2061f c2061f, int i10, E8.o oVar) {
        Q7.O o = Q7.O.f7181n;
        if (interfaceC0657j == null) {
            K0(19);
            throw null;
        }
        if (hVar == null) {
            K0(20);
            throw null;
        }
        if (i7 == 0) {
            K0(21);
            throw null;
        }
        if (c2061f == null) {
            K0(22);
            throw null;
        }
        if (oVar == null) {
            K0(25);
            throw null;
        }
        super(oVar, interfaceC0657j, hVar, c2061f, i7, z6, i10, o);
        this.f8819w = new ArrayList(1);
        this.f8820x = false;
    }

    public static /* synthetic */ void K0(int i7) {
        String str = (i7 == 5 || i7 == 28) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 5 || i7 == 28) ? 2 : 3];
        switch (i7) {
            case 1:
            case 7:
            case 13:
            case 20:
                objArr[0] = "annotations";
                break;
            case 2:
            case 8:
            case 14:
            case 21:
                objArr[0] = "variance";
                break;
            case 3:
            case 9:
            case 15:
            case 22:
                objArr[0] = "name";
                break;
            case 4:
            case 11:
            case 18:
            case 25:
                objArr[0] = "storageManager";
                break;
            case 5:
            case 28:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/TypeParameterDescriptorImpl";
                break;
            case 6:
            case 12:
            case 19:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 10:
            case 16:
            case 23:
                objArr[0] = BYDAutoMultimediaDevice.PARAM_SOURCE;
                break;
            case 17:
                objArr[0] = "supertypeLoopsResolver";
                break;
            case 24:
                objArr[0] = "supertypeLoopsChecker";
                break;
            case 26:
                objArr[0] = "bound";
                break;
            case 27:
                objArr[0] = "type";
                break;
        }
        if (i7 == 5) {
            objArr[1] = "createWithDefaultBound";
        } else if (i7 != 28) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/TypeParameterDescriptorImpl";
        } else {
            objArr[1] = "resolveUpperBounds";
        }
        switch (i7) {
            case 5:
            case 28:
                break;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                objArr[2] = "createForFurtherModification";
                break;
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
                objArr[2] = "<init>";
                break;
            case 26:
                objArr[2] = "addUpperBound";
                break;
            case 27:
                objArr[2] = "reportSupertypeLoopError";
                break;
            default:
                objArr[2] = "createWithDefaultBound";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i7 != 5 && i7 != 28) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static O e1(InterfaceC0657j interfaceC0657j, R7.h hVar, boolean z6, int i7, C2061f c2061f, int i10, E8.o oVar) {
        if (interfaceC0657j == null) {
            K0(6);
            throw null;
        }
        if (hVar == null) {
            K0(7);
            throw null;
        }
        if (i7 == 0) {
            K0(8);
            throw null;
        }
        if (c2061f == null) {
            K0(9);
            throw null;
        }
        if (oVar == null) {
            K0(11);
            throw null;
        }
        if (i7 != 0) {
            return new O(interfaceC0657j, hVar, z6, i7, c2061f, i10, oVar);
        }
        K0(14);
        throw null;
    }

    public static O f1(AbstractC0783b abstractC0783b, int i7, C2061f c2061f, int i10, E8.o oVar) {
        R7.f fVar = R7.g.f7535a;
        if (abstractC0783b == null) {
            K0(0);
            throw null;
        }
        if (i7 == 0) {
            K0(2);
            throw null;
        }
        if (oVar == null) {
            K0(4);
            throw null;
        }
        O oE1 = e1(abstractC0783b, fVar, false, i7, c2061f, i10, oVar);
        F8.A aN = AbstractC2564d.e(abstractC0783b).n();
        if (oE1.f8820x) {
            throw new IllegalStateException("Type parameter descriptor is already initialized: " + oE1.g1());
        }
        if (!AbstractC0268c.i(aN)) {
            oE1.f8819w.add(aN);
        }
        if (!oE1.f8820x) {
            oE1.f8820x = true;
            return oE1;
        }
        throw new IllegalStateException("Type parameter descriptor is already initialized: " + oE1.g1());
    }

    @Override // T7.AbstractC0789h
    public final List d1() {
        if (!this.f8820x) {
            throw new IllegalStateException("Type parameter descriptor is not initialized: " + g1());
        }
        ArrayList arrayList = this.f8819w;
        if (arrayList != null) {
            return arrayList;
        }
        K0(28);
        throw null;
    }

    public final String g1() {
        return getName() + " declared in " + r8.d.g(o());
    }
}
