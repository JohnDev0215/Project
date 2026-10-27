package T7;

import F8.AbstractC0287w;
import Q7.C0661n;
import Q7.InterfaceC0659l;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import o8.AbstractC2063h;
import o8.C2061f;
import v8.AbstractC2564d;

/* JADX INFO: loaded from: classes.dex */
public final class J extends F implements Q7.K {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Q f8812y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final J f8813z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public J(Q7.L l, R7.h hVar, int i7, C0661n c0661n, boolean z6, boolean z10, boolean z11, int i10, J j3, Q7.N n10) {
        J j10;
        J j11;
        super(i7, c0661n, l, hVar, C2061f.g("<set-" + l.getName() + ">"), z6, z10, z11, i10, n10);
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
        if (j3 != null) {
            j11 = this;
            j10 = j3;
        } else {
            j10 = this;
            j11 = j10;
        }
        j11.f8813z = j10;
    }

    public static /* synthetic */ void K0(int i7) {
        String str;
        int i10;
        switch (i7) {
            case 10:
            case 11:
            case 12:
            case 13:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i7) {
            case 10:
            case 11:
            case 12:
            case 13:
                i10 = 2;
                break;
            default:
                i10 = 3;
                break;
        }
        Object[] objArr = new Object[i10];
        switch (i7) {
            case 1:
            case 9:
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
                objArr[0] = "parameter";
                break;
            case 7:
                objArr[0] = "setterDescriptor";
                break;
            case 8:
                objArr[0] = "type";
                break;
            case 10:
            case 11:
            case 12:
            case 13:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertySetterDescriptorImpl";
                break;
            default:
                objArr[0] = "correspondingProperty";
                break;
        }
        switch (i7) {
            case 10:
                objArr[1] = "getOverriddenDescriptors";
                break;
            case 11:
                objArr[1] = "getValueParameters";
                break;
            case 12:
                objArr[1] = "getReturnType";
                break;
            case 13:
                objArr[1] = "getOriginal";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertySetterDescriptorImpl";
                break;
        }
        switch (i7) {
            case 6:
                objArr[2] = "initialize";
                break;
            case 7:
            case 8:
            case 9:
                objArr[2] = "createSetterParameter";
                break;
            case 10:
            case 11:
            case 12:
            case 13:
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i7) {
            case 10:
            case 11:
            case 12:
            case 13:
                throw new IllegalStateException(str2);
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    public static Q e1(J j3, AbstractC0287w abstractC0287w, R7.h hVar) {
        if (abstractC0287w == null) {
            K0(8);
            throw null;
        }
        if (hVar != null) {
            return new Q(j3, null, 0, hVar, AbstractC2063h.f20579g, abstractC0287w, false, false, false, null, Q7.N.c);
        }
        K0(9);
        throw null;
    }

    @Override // Q7.InterfaceC0649b
    public final List D0() {
        Q q4 = this.f8812y;
        if (q4 == null) {
            throw new IllegalStateException();
        }
        List listSingletonList = Collections.singletonList(q4);
        if (listSingletonList != null) {
            return listSingletonList;
        }
        K0(11);
        throw null;
    }

    @Override // Q7.InterfaceC0657j
    public final Object S(InterfaceC0659l interfaceC0659l, Object obj) {
        return interfaceC0659l.e(this, obj);
    }

    @Override // T7.AbstractC0795n
    /* JADX INFO: renamed from: f1, reason: merged with bridge method [inline-methods] */
    public final J b1() {
        J j3 = this.f8813z;
        if (j3 != null) {
            return j3;
        }
        K0(13);
        throw null;
    }

    @Override // Q7.InterfaceC0649b
    public final AbstractC0287w p() {
        return AbstractC2564d.e(this).v();
    }

    @Override // Q7.InterfaceC0650c, Q7.InterfaceC0649b
    public final Collection q() {
        return d1(false);
    }
}
