package T7;

import F8.AbstractC0287w;
import F8.T;
import Q7.C0661n;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0665s;
import Q7.InterfaceC0666t;
import b8.C1011f;
import java.util.LinkedHashMap;
import java.util.List;
import o8.C2061f;

/* JADX INFO: renamed from: T7.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0799s implements InterfaceC0665s {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f8863A;
    public boolean B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public n7.u f8864C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public R7.h f8865D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public boolean f8866E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final LinkedHashMap f8867F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public Boolean f8868G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public boolean f8869H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final /* synthetic */ t f8870I;
    public T l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public InterfaceC0657j f8871m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f8872n;
    public C0661n o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public InterfaceC0666t f8873p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f8874q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public List f8875r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final List f8876s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public u f8877t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public u f8878u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public AbstractC0287w f8879v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public C2061f f8880w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f8881x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f8882y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f8883z;

    public C0799s(t tVar, T t10, InterfaceC0657j interfaceC0657j, int i7, C0661n c0661n, int i10, List list, List list2, u uVar, AbstractC0287w abstractC0287w) {
        if (t10 == null) {
            b(0);
            throw null;
        }
        if (interfaceC0657j == null) {
            b(1);
            throw null;
        }
        if (i7 == 0) {
            b(2);
            throw null;
        }
        if (c0661n == null) {
            b(3);
            throw null;
        }
        if (i10 == 0) {
            b(4);
            throw null;
        }
        if (list == null) {
            b(5);
            throw null;
        }
        if (list2 == null) {
            b(6);
            throw null;
        }
        if (abstractC0287w == null) {
            b(7);
            throw null;
        }
        this.f8870I = tVar;
        this.f8873p = null;
        this.f8878u = tVar.f8903v;
        this.f8881x = true;
        this.f8882y = false;
        this.f8883z = false;
        this.f8863A = false;
        this.B = tVar.f8887E;
        this.f8864C = null;
        this.f8865D = null;
        this.f8866E = tVar.f8888F;
        this.f8867F = new LinkedHashMap();
        this.f8868G = null;
        this.f8869H = false;
        this.l = t10;
        this.f8871m = interfaceC0657j;
        this.f8872n = i7;
        this.o = c0661n;
        this.f8874q = i10;
        this.f8875r = list;
        this.f8876s = list2;
        this.f8877t = uVar;
        this.f8879v = abstractC0287w;
        this.f8880w = null;
    }

    public static /* synthetic */ void b(int i7) {
        String str;
        int i10;
        switch (i7) {
            case 9:
            case 11:
            case 13:
            case 15:
            case 16:
            case 18:
            case 20:
            case 22:
            case 24:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 36:
            case 38:
            case 40:
            case 41:
            case 42:
                str = "@NotNull method %s.%s must not return null";
                break;
            case 10:
            case 12:
            case 14:
            case 17:
            case 19:
            case 21:
            case 23:
            case 25:
            case 35:
            case 37:
            case 39:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i7) {
            case 9:
            case 11:
            case 13:
            case 15:
            case 16:
            case 18:
            case 20:
            case 22:
            case 24:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 36:
            case 38:
            case 40:
            case 41:
            case 42:
                i10 = 2;
                break;
            case 10:
            case 12:
            case 14:
            case 17:
            case 19:
            case 21:
            case 23:
            case 25:
            case 35:
            case 37:
            case 39:
            default:
                i10 = 3;
                break;
        }
        Object[] objArr = new Object[i10];
        switch (i7) {
            case 1:
                objArr[0] = "newOwner";
                break;
            case 2:
                objArr[0] = "newModality";
                break;
            case 3:
                objArr[0] = "newVisibility";
                break;
            case 4:
            case 14:
                objArr[0] = "kind";
                break;
            case 5:
                objArr[0] = "newValueParameterDescriptors";
                break;
            case 6:
                objArr[0] = "newContextReceiverParameters";
                break;
            case 7:
                objArr[0] = "newReturnType";
                break;
            case 8:
                objArr[0] = "owner";
                break;
            case 9:
            case 11:
            case 13:
            case 15:
            case 16:
            case 18:
            case 20:
            case 22:
            case 24:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 36:
            case 38:
            case 40:
            case 41:
            case 42:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl$CopyConfiguration";
                break;
            case 10:
                objArr[0] = "modality";
                break;
            case 12:
                objArr[0] = "visibility";
                break;
            case 17:
                objArr[0] = "name";
                break;
            case 19:
            case 21:
                objArr[0] = "parameters";
                break;
            case 23:
                objArr[0] = "type";
                break;
            case 25:
                objArr[0] = "contextReceiverParameters";
                break;
            case 35:
                objArr[0] = "additionalAnnotations";
                break;
            case 37:
            default:
                objArr[0] = "substitution";
                break;
            case 39:
                objArr[0] = "userDataKey";
                break;
        }
        switch (i7) {
            case 9:
                objArr[1] = "setOwner";
                break;
            case 10:
            case 12:
            case 14:
            case 17:
            case 19:
            case 21:
            case 23:
            case 25:
            case 35:
            case 37:
            case 39:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl$CopyConfiguration";
                break;
            case 11:
                objArr[1] = "setModality";
                break;
            case 13:
                objArr[1] = "setVisibility";
                break;
            case 15:
                objArr[1] = "setKind";
                break;
            case 16:
                objArr[1] = "setCopyOverrides";
                break;
            case 18:
                objArr[1] = "setName";
                break;
            case 20:
                objArr[1] = "setValueParameters";
                break;
            case 22:
                objArr[1] = "setTypeParameters";
                break;
            case 24:
                objArr[1] = "setReturnType";
                break;
            case 26:
                objArr[1] = "setContextReceiverParameters";
                break;
            case 27:
                objArr[1] = "setExtensionReceiverParameter";
                break;
            case 28:
                objArr[1] = "setDispatchReceiverParameter";
                break;
            case 29:
                objArr[1] = "setOriginal";
                break;
            case 30:
                objArr[1] = "setSignatureChange";
                break;
            case 31:
                objArr[1] = "setPreserveSourceElement";
                break;
            case 32:
                objArr[1] = "setDropOriginalInContainingParts";
                break;
            case 33:
                objArr[1] = "setHiddenToOvercomeSignatureClash";
                break;
            case 34:
                objArr[1] = "setHiddenForResolutionEverywhereBesideSupercalls";
                break;
            case 36:
                objArr[1] = "setAdditionalAnnotations";
                break;
            case 38:
                objArr[1] = "setSubstitution";
                break;
            case 40:
                objArr[1] = "putUserData";
                break;
            case 41:
                objArr[1] = "getSubstitution";
                break;
            case 42:
                objArr[1] = "setJustForTypeSubstitution";
                break;
        }
        switch (i7) {
            case 8:
                objArr[2] = "setOwner";
                break;
            case 9:
            case 11:
            case 13:
            case 15:
            case 16:
            case 18:
            case 20:
            case 22:
            case 24:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 36:
            case 38:
            case 40:
            case 41:
            case 42:
                break;
            case 10:
                objArr[2] = "setModality";
                break;
            case 12:
                objArr[2] = "setVisibility";
                break;
            case 14:
                objArr[2] = "setKind";
                break;
            case 17:
                objArr[2] = "setName";
                break;
            case 19:
                objArr[2] = "setValueParameters";
                break;
            case 21:
                objArr[2] = "setTypeParameters";
                break;
            case 23:
                objArr[2] = "setReturnType";
                break;
            case 25:
                objArr[2] = "setContextReceiverParameters";
                break;
            case 35:
                objArr[2] = "setAdditionalAnnotations";
                break;
            case 37:
                objArr[2] = "setSubstitution";
                break;
            case 39:
                objArr[2] = "putUserData";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i7) {
            case 9:
            case 11:
            case 13:
            case 15:
            case 16:
            case 18:
            case 20:
            case 22:
            case 24:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 36:
            case 38:
            case 40:
            case 41:
            case 42:
                throw new IllegalStateException(str2);
            case 10:
            case 12:
            case 14:
            case 17:
            case 19:
            case 21:
            case 23:
            case 25:
            case 35:
            case 37:
            case 39:
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    @Override // Q7.InterfaceC0665s
    public final InterfaceC0665s E(AbstractC0287w abstractC0287w) {
        if (abstractC0287w != null) {
            this.f8879v = abstractC0287w;
            return this;
        }
        b(23);
        throw null;
    }

    @Override // Q7.InterfaceC0665s
    public final InterfaceC0665s F(int i7) {
        if (i7 != 0) {
            this.f8872n = i7;
            return this;
        }
        b(10);
        throw null;
    }

    @Override // Q7.InterfaceC0665s
    public final InterfaceC0665s H() {
        this.f8867F.put(C1011f.f12328S, Boolean.TRUE);
        return this;
    }

    @Override // Q7.InterfaceC0665s
    public final InterfaceC0665s J() {
        this.f8866E = true;
        return this;
    }

    @Override // Q7.InterfaceC0665s
    public final InterfaceC0665s L(InterfaceC0652e interfaceC0652e) {
        if (interfaceC0652e != null) {
            this.f8871m = interfaceC0652e;
            return this;
        }
        b(8);
        throw null;
    }

    @Override // Q7.InterfaceC0665s
    public final InterfaceC0665s Q() {
        this.f8882y = true;
        return this;
    }

    @Override // Q7.InterfaceC0665s
    /* JADX INFO: renamed from: a */
    public final InterfaceC0666t mo0a() {
        return this.f8870I.f1(this);
    }

    @Override // Q7.InterfaceC0665s
    public final InterfaceC0665s c() {
        this.f8881x = false;
        return this;
    }

    @Override // Q7.InterfaceC0665s
    public final InterfaceC0665s d(u uVar) {
        this.f8878u = uVar;
        return this;
    }

    @Override // Q7.InterfaceC0665s
    public final InterfaceC0665s h(List list) {
        this.f8875r = list;
        return this;
    }

    @Override // Q7.InterfaceC0665s
    public final InterfaceC0665s i(R7.h hVar) {
        if (hVar != null) {
            this.f8865D = hVar;
            return this;
        }
        b(35);
        throw null;
    }

    @Override // Q7.InterfaceC0665s
    public final InterfaceC0665s p() {
        this.f8864C = n7.u.l;
        return this;
    }

    @Override // Q7.InterfaceC0665s
    public final InterfaceC0665s r() {
        this.B = true;
        return this;
    }

    @Override // Q7.InterfaceC0665s
    public final InterfaceC0665s s(C2061f c2061f) {
        if (c2061f != null) {
            this.f8880w = c2061f;
            return this;
        }
        b(17);
        throw null;
    }

    @Override // Q7.InterfaceC0665s
    public final InterfaceC0665s t(int i7) {
        if (i7 != 0) {
            this.f8874q = i7;
            return this;
        }
        b(14);
        throw null;
    }

    @Override // Q7.InterfaceC0665s
    public final InterfaceC0665s u(C0661n c0661n) {
        if (c0661n != null) {
            this.o = c0661n;
            return this;
        }
        b(12);
        throw null;
    }

    @Override // Q7.InterfaceC0665s
    public final InterfaceC0665s y() {
        this.f8883z = true;
        return this;
    }
}
