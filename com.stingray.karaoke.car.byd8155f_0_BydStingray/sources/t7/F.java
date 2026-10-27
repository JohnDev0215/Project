package T7;

import F8.W;
import Q7.C0661n;
import Q7.InterfaceC0648a;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0658k;
import Q7.InterfaceC0666t;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public abstract class F extends AbstractC0795n implements Q7.K {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f8773q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f8774r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f8775s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Q7.L f8776t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final boolean f8777u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f8778v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public C0661n f8779w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public InterfaceC0666t f8780x;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public F(int i7, C0661n c0661n, Q7.L l, R7.h hVar, C2061f c2061f, boolean z6, boolean z10, boolean z11, int i10, Q7.N n10) {
        super(l.o(), hVar, c2061f, n10);
        if (i7 == 0) {
            K0(0);
            throw null;
        }
        if (c0661n == null) {
            K0(1);
            throw null;
        }
        if (hVar == null) {
            K0(3);
            throw null;
        }
        if (n10 == null) {
            K0(5);
            throw null;
        }
        this.f8780x = null;
        this.f8775s = i7;
        this.f8779w = c0661n;
        this.f8776t = l;
        this.f8773q = z6;
        this.f8774r = z10;
        this.f8777u = z11;
        this.f8778v = i10;
    }

    public static /* synthetic */ void K0(int i7) {
        String str;
        int i10;
        switch (i7) {
            case 6:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                str = "@NotNull method %s.%s must not return null";
                break;
            case 7:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i7) {
            case 6:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                i10 = 2;
                break;
            case 7:
            default:
                i10 = 3;
                break;
        }
        Object[] objArr = new Object[i10];
        switch (i7) {
            case 1:
                objArr[0] = "visibility";
                break;
            case 2:
                objArr[0] = "correspondingProperty";
                break;
            case 3:
                objArr[0] = "annotations";
                break;
            case 4:
                objArr[0] = "name";
                break;
            case 5:
                objArr[0] = BYDAutoMultimediaDevice.PARAM_SOURCE;
                break;
            case 6:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyAccessorDescriptorImpl";
                break;
            case 7:
                objArr[0] = "substitutor";
                break;
            case 16:
                objArr[0] = "overriddenDescriptors";
                break;
            default:
                objArr[0] = "modality";
                break;
        }
        switch (i7) {
            case 6:
                objArr[1] = "getKind";
                break;
            case 7:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyAccessorDescriptorImpl";
                break;
            case 8:
                objArr[1] = "substitute";
                break;
            case 9:
                objArr[1] = "getTypeParameters";
                break;
            case 10:
                objArr[1] = "getModality";
                break;
            case 11:
                objArr[1] = "getVisibility";
                break;
            case 12:
                objArr[1] = "getCorrespondingVariable";
                break;
            case 13:
                objArr[1] = "getCorrespondingProperty";
                break;
            case 14:
                objArr[1] = "getContextReceiverParameters";
                break;
            case 15:
                objArr[1] = "getOverriddenDescriptors";
                break;
        }
        switch (i7) {
            case 6:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                break;
            case 7:
                objArr[2] = "substitute";
                break;
            case 16:
                objArr[2] = "setOverriddenDescriptors";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i7) {
            case 6:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                throw new IllegalStateException(str2);
            case 7:
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    @Override // Q7.InterfaceC0649b
    public final u B() {
        return c1().B();
    }

    @Override // Q7.InterfaceC0666t
    public final boolean E0() {
        return false;
    }

    @Override // Q7.InterfaceC0649b
    public final u K() {
        return c1().K();
    }

    @Override // Q7.InterfaceC0650c
    public final int N() {
        int i7 = this.f8778v;
        if (i7 != 0) {
            return i7;
        }
        K0(6);
        throw null;
    }

    @Override // Q7.InterfaceC0649b
    public final boolean T() {
        return false;
    }

    @Override // Q7.InterfaceC0649b
    public final List V() {
        List listV = c1().V();
        if (listV != null) {
            return listV;
        }
        K0(14);
        throw null;
    }

    @Override // Q7.InterfaceC0650c
    public final InterfaceC0650c a0(InterfaceC0652e interfaceC0652e, int i7, C0661n c0661n) {
        throw new UnsupportedOperationException("Accessors must be copied by the corresponding property");
    }

    @Override // Q7.InterfaceC0670x
    public final boolean c0() {
        return false;
    }

    public final Q7.L c1() {
        Q7.L l = this.f8776t;
        if (l != null) {
            return l;
        }
        K0(13);
        throw null;
    }

    public final ArrayList d1(boolean z6) {
        ArrayList arrayList = new ArrayList(0);
        for (Q7.L l : c1().q()) {
            h8.n nVarC = z6 ? l.c() : l.d();
            if (nVarC != null) {
                arrayList.add(nVarC);
            }
        }
        return arrayList;
    }

    @Override // Q7.InterfaceC0660m, Q7.InterfaceC0670x
    public final C0661n e() {
        C0661n c0661n = this.f8779w;
        if (c0661n != null) {
            return c0661n;
        }
        K0(11);
        throw null;
    }

    @Override // Q7.InterfaceC0666t, Q7.P
    public final InterfaceC0666t f(W w10) {
        if (w10 != null) {
            return this;
        }
        K0(7);
        throw null;
    }

    @Override // Q7.InterfaceC0666t
    public final boolean g0() {
        return false;
    }

    @Override // Q7.InterfaceC0670x
    public final int h() {
        int i7 = this.f8775s;
        if (i7 != 0) {
            return i7;
        }
        K0(10);
        throw null;
    }

    @Override // Q7.InterfaceC0650c
    public final void h0(Collection collection) {
        if (collection != null) {
            return;
        }
        K0(16);
        throw null;
    }

    @Override // Q7.InterfaceC0666t
    public final boolean j0() {
        return false;
    }

    @Override // Q7.InterfaceC0666t
    public final boolean k0() {
        return false;
    }

    @Override // Q7.InterfaceC0666t
    public final boolean m() {
        return false;
    }

    @Override // Q7.InterfaceC0666t
    public final boolean n() {
        return this.f8777u;
    }

    @Override // Q7.InterfaceC0666t
    public final boolean s0() {
        return false;
    }

    @Override // Q7.InterfaceC0649b
    public final List t() {
        List listEmptyList = Collections.emptyList();
        if (listEmptyList != null) {
            return listEmptyList;
        }
        K0(9);
        throw null;
    }

    @Override // Q7.InterfaceC0649b
    public final Object w(InterfaceC0648a interfaceC0648a) {
        return null;
    }

    @Override // Q7.InterfaceC0666t
    public final InterfaceC0666t x() {
        return this.f8780x;
    }

    @Override // Q7.InterfaceC0670x
    public final boolean y0() {
        return false;
    }

    @Override // Q7.InterfaceC0670x
    public final boolean z() {
        return this.f8774r;
    }

    @Override // Q7.P
    public final /* bridge */ /* synthetic */ InterfaceC0658k f(W w10) {
        f(w10);
        return this;
    }
}
