package T7;

import F8.AbstractC0287w;
import Q7.C0661n;
import Q7.InterfaceC0659l;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public final class I extends F implements Q7.K {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public AbstractC0287w f8810y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final I f8811z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public I(Q7.L l, R7.h hVar, int i7, C0661n c0661n, boolean z6, boolean z10, boolean z11, int i10, I i11, Q7.N n10) {
        I i12;
        I i13;
        super(i7, c0661n, l, hVar, C2061f.g("<get-" + l.getName() + ">"), z6, z10, z11, i10, n10);
        if (hVar == null) {
            K0(1);
            throw null;
        }
        if (i7 == 0) {
            K0(2);
            throw null;
        }
        if (c0661n == null) {
            K0(3);
            throw null;
        }
        if (i10 == 0) {
            K0(4);
            throw null;
        }
        if (n10 == null) {
            K0(5);
            throw null;
        }
        if (i11 != null) {
            i13 = this;
            i12 = i11;
        } else {
            i12 = this;
            i13 = i12;
        }
        i13.f8811z = i12;
    }

    public static /* synthetic */ void K0(int i7) {
        String str = (i7 == 6 || i7 == 7 || i7 == 8) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 6 || i7 == 7 || i7 == 8) ? 2 : 3];
        switch (i7) {
            case 1:
                objArr[0] = "annotations";
                break;
            case 2:
                objArr[0] = "modality";
                break;
            case 3:
                objArr[0] = "visibility";
                break;
            case 4:
                objArr[0] = "kind";
                break;
            case 5:
                objArr[0] = BYDAutoMultimediaDevice.PARAM_SOURCE;
                break;
            case 6:
            case 7:
            case 8:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyGetterDescriptorImpl";
                break;
            default:
                objArr[0] = "correspondingProperty";
                break;
        }
        if (i7 == 6) {
            objArr[1] = "getOverriddenDescriptors";
        } else if (i7 == 7) {
            objArr[1] = "getValueParameters";
        } else if (i7 != 8) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyGetterDescriptorImpl";
        } else {
            objArr[1] = "getOriginal";
        }
        if (i7 != 6 && i7 != 7 && i7 != 8) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i7 != 6 && i7 != 7 && i7 != 8) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // Q7.InterfaceC0649b
    public final List D0() {
        List listEmptyList = Collections.emptyList();
        if (listEmptyList != null) {
            return listEmptyList;
        }
        K0(7);
        throw null;
    }

    @Override // Q7.InterfaceC0657j
    public final Object S(InterfaceC0659l interfaceC0659l, Object obj) {
        return interfaceC0659l.O(this, obj);
    }

    @Override // T7.AbstractC0795n
    /* JADX INFO: renamed from: e1, reason: merged with bridge method [inline-methods] */
    public final I b1() {
        I i7 = this.f8811z;
        if (i7 != null) {
            return i7;
        }
        K0(8);
        throw null;
    }

    public final void f1(AbstractC0287w abstractC0287w) {
        if (abstractC0287w == null) {
            abstractC0287w = c1().b();
        }
        this.f8810y = abstractC0287w;
    }

    @Override // Q7.InterfaceC0649b
    public final AbstractC0287w p() {
        return this.f8810y;
    }

    @Override // Q7.InterfaceC0650c, Q7.InterfaceC0649b
    public final Collection q() {
        return d1(true);
    }
}
