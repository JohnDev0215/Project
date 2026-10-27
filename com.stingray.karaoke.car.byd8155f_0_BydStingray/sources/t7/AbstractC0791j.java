package T7;

import Q7.InterfaceC0657j;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import o8.C2061f;

/* JADX INFO: renamed from: T7.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0791j extends AbstractC0783b {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final InterfaceC0657j f8844p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Q7.N f8845q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractC0791j(E8.o oVar, InterfaceC0657j interfaceC0657j, C2061f c2061f, Q7.N n10) {
        super(oVar, c2061f);
        if (oVar == null) {
            W(0);
            throw null;
        }
        if (interfaceC0657j == null) {
            W(1);
            throw null;
        }
        if (c2061f == null) {
            W(2);
            throw null;
        }
        this.f8844p = interfaceC0657j;
        this.f8845q = n10;
    }

    public static /* synthetic */ void W(int i7) {
        String str = (i7 == 4 || i7 == 5) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 4 || i7 == 5) ? 2 : 3];
        if (i7 == 1) {
            objArr[0] = "containingDeclaration";
        } else if (i7 == 2) {
            objArr[0] = "name";
        } else if (i7 == 3) {
            objArr[0] = BYDAutoMultimediaDevice.PARAM_SOURCE;
        } else if (i7 == 4 || i7 == 5) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassDescriptorBase";
        } else {
            objArr[0] = "storageManager";
        }
        if (i7 == 4) {
            objArr[1] = "getContainingDeclaration";
        } else if (i7 != 5) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassDescriptorBase";
        } else {
            objArr[1] = "getSource";
        }
        if (i7 != 4 && i7 != 5) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i7 != 4 && i7 != 5) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // Q7.InterfaceC0658k
    public final Q7.N g() {
        Q7.N n10 = this.f8845q;
        if (n10 != null) {
            return n10;
        }
        W(5);
        throw null;
    }

    @Override // Q7.InterfaceC0657j
    public final InterfaceC0657j o() {
        InterfaceC0657j interfaceC0657j = this.f8844p;
        if (interfaceC0657j != null) {
            return interfaceC0657j;
        }
        W(4);
        throw null;
    }

    public boolean z() {
        return false;
    }
}
