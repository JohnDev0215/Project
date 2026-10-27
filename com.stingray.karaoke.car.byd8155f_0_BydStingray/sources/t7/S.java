package T7;

import F8.AbstractC0287w;
import Q7.InterfaceC0657j;
import Q7.U;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.util.Collections;
import java.util.List;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public abstract class S extends AbstractC0795n implements U {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public AbstractC0287w f8828q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public S(InterfaceC0657j interfaceC0657j, R7.h hVar, C2061f c2061f, AbstractC0287w abstractC0287w, Q7.N n10) {
        super(interfaceC0657j, hVar, c2061f, n10);
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
        this.f8828q = abstractC0287w;
    }

    public static /* synthetic */ void K0(int i7) {
        String str;
        int i10;
        switch (i7) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i7) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                i10 = 2;
                break;
            default:
                i10 = 3;
                break;
        }
        Object[] objArr = new Object[i10];
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
            case 7:
            case 8:
            case 9:
            case 10:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/VariableDescriptorImpl";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        switch (i7) {
            case 4:
                objArr[1] = "getType";
                break;
            case 5:
                objArr[1] = "getOriginal";
                break;
            case 6:
                objArr[1] = "getValueParameters";
                break;
            case 7:
                objArr[1] = "getOverriddenDescriptors";
                break;
            case 8:
                objArr[1] = "getTypeParameters";
                break;
            case 9:
                objArr[1] = "getContextReceiverParameters";
                break;
            case 10:
                objArr[1] = "getReturnType";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/VariableDescriptorImpl";
                break;
        }
        switch (i7) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i7) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                throw new IllegalStateException(str2);
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    public u B() {
        return null;
    }

    @Override // Q7.InterfaceC0649b
    public final List D0() {
        List listEmptyList = Collections.emptyList();
        if (listEmptyList != null) {
            return listEmptyList;
        }
        K0(6);
        throw null;
    }

    public u K() {
        return null;
    }

    @Override // Q7.InterfaceC0649b
    public boolean T() {
        return false;
    }

    @Override // A9.b, z8.InterfaceC2796d
    public final AbstractC0287w b() {
        AbstractC0287w abstractC0287w = this.f8828q;
        if (abstractC0287w != null) {
            return abstractC0287w;
        }
        K0(4);
        throw null;
    }

    public AbstractC0287w p() {
        AbstractC0287w abstractC0287wB = b();
        if (abstractC0287wB != null) {
            return abstractC0287wB;
        }
        K0(10);
        throw null;
    }

    public List t() {
        List listEmptyList = Collections.emptyList();
        if (listEmptyList != null) {
            return listEmptyList;
        }
        K0(8);
        throw null;
    }
}
