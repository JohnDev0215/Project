package T7;

import Q7.InterfaceC0657j;
import Q7.InterfaceC0658k;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import o8.C2061f;

/* JADX INFO: renamed from: T7.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0795n extends AbstractC0794m implements InterfaceC0658k {
    public final InterfaceC0657j o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Q7.N f8855p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractC0795n(InterfaceC0657j interfaceC0657j, R7.h hVar, C2061f c2061f, Q7.N n10) {
        super(hVar, c2061f);
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
        if (n10 == null) {
            K0(3);
            throw null;
        }
        this.o = interfaceC0657j;
        this.f8855p = n10;
    }

    public static /* synthetic */ void K0(int i7) {
        String str = (i7 == 4 || i7 == 5 || i7 == 6) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 4 || i7 == 5 || i7 == 6) ? 2 : 3];
        switch (i7) {
            case 1:
                objArr[0] = "annotations";
                break;
            case 2:
                objArr[0] = "name";
                break;
            case 3:
                objArr[0] = BYDAutoMultimediaDevice.PARAM_SOURCE;
                break;
            case 4:
            case 5:
            case 6:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/DeclarationDescriptorNonRootImpl";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        if (i7 == 4) {
            objArr[1] = "getOriginal";
        } else if (i7 == 5) {
            objArr[1] = "getContainingDeclaration";
        } else if (i7 != 6) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/DeclarationDescriptorNonRootImpl";
        } else {
            objArr[1] = "getSource";
        }
        if (i7 != 4 && i7 != 5 && i7 != 6) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i7 != 4 && i7 != 5 && i7 != 6) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // T7.AbstractC0794m, Q7.InterfaceC0657j, Q7.InterfaceC0654g
    public InterfaceC0658k b1() {
        return this;
    }

    public Q7.N g() {
        Q7.N n10 = this.f8855p;
        if (n10 != null) {
            return n10;
        }
        K0(6);
        throw null;
    }

    public InterfaceC0657j o() {
        InterfaceC0657j interfaceC0657j = this.o;
        if (interfaceC0657j != null) {
            return interfaceC0657j;
        }
        K0(5);
        throw null;
    }
}
