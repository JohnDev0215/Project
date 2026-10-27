package T7;

import Q7.InterfaceC0657j;
import o8.C2061f;
import q8.C2203g;

/* JADX INFO: renamed from: T7.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0794m extends A9.b implements InterfaceC0657j {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C2061f f8854n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractC0794m(R7.h hVar, C2061f c2061f) {
        super(hVar);
        if (hVar == null) {
            K0(0);
            throw null;
        }
        if (c2061f == null) {
            K0(1);
            throw null;
        }
        this.f8854n = c2061f;
    }

    public static /* synthetic */ void K0(int i7) {
        String str = (i7 == 2 || i7 == 3 || i7 == 5 || i7 == 6) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 2 || i7 == 3 || i7 == 5 || i7 == 6) ? 2 : 3];
        switch (i7) {
            case 1:
                objArr[0] = "name";
                break;
            case 2:
            case 3:
            case 5:
            case 6:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/DeclarationDescriptorImpl";
                break;
            case 4:
                objArr[0] = "descriptor";
                break;
            default:
                objArr[0] = "annotations";
                break;
        }
        if (i7 == 2) {
            objArr[1] = "getName";
        } else if (i7 == 3) {
            objArr[1] = "getOriginal";
        } else if (i7 == 5 || i7 == 6) {
            objArr[1] = "toString";
        } else {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/DeclarationDescriptorImpl";
        }
        if (i7 != 2 && i7 != 3) {
            if (i7 == 4) {
                objArr[2] = "toString";
            } else if (i7 != 5 && i7 != 6) {
                objArr[2] = "<init>";
            }
        }
        String str2 = String.format(str, objArr);
        if (i7 != 2 && i7 != 3 && i7 != 5 && i7 != 6) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static String a1(InterfaceC0657j interfaceC0657j) {
        try {
            String str = C2203g.f21280e.w(interfaceC0657j) + "[" + interfaceC0657j.getClass().getSimpleName() + "@" + Integer.toHexString(System.identityHashCode(interfaceC0657j)) + "]";
            if (str != null) {
                return str;
            }
            K0(5);
            throw null;
        } catch (Throwable unused) {
            String str2 = interfaceC0657j.getClass().getSimpleName() + " " + interfaceC0657j.getName();
            if (str2 != null) {
                return str2;
            }
            K0(6);
            throw null;
        }
    }

    /* JADX INFO: renamed from: a */
    public InterfaceC0657j b1() {
        return this;
    }

    @Override // Q7.InterfaceC0657j
    public final C2061f getName() {
        C2061f c2061f = this.f8854n;
        if (c2061f != null) {
            return c2061f;
        }
        K0(2);
        throw null;
    }

    @Override // A9.b
    public String toString() {
        return a1(this);
    }
}
