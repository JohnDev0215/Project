package R7;

import F8.A;
import F8.AbstractC0287w;
import Q7.InterfaceC0652e;
import Q7.N;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.util.Map;
import o8.C2058c;
import q8.C2203g;
import v8.AbstractC2564d;

/* JADX INFO: loaded from: classes.dex */
public final class c implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final A f7521a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map f7522b;
    public final N c;

    public c(A a10, Map map, N n10) {
        if (a10 == null) {
            d(0);
            throw null;
        }
        if (map == null) {
            d(1);
            throw null;
        }
        this.f7521a = a10;
        this.f7522b = map;
        this.c = n10;
    }

    public static /* synthetic */ void d(int i7) {
        String str = (i7 == 3 || i7 == 4 || i7 == 5) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 3 || i7 == 4 || i7 == 5) ? 2 : 3];
        if (i7 == 1) {
            objArr[0] = "valueArguments";
        } else if (i7 == 2) {
            objArr[0] = BYDAutoMultimediaDevice.PARAM_SOURCE;
        } else if (i7 == 3 || i7 == 4 || i7 == 5) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotationDescriptorImpl";
        } else {
            objArr[0] = "annotationType";
        }
        if (i7 == 3) {
            objArr[1] = "getType";
        } else if (i7 == 4) {
            objArr[1] = "getAllValueArguments";
        } else if (i7 != 5) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotationDescriptorImpl";
        } else {
            objArr[1] = "getSource";
        }
        if (i7 != 3 && i7 != 4 && i7 != 5) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i7 != 3 && i7 != 4 && i7 != 5) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // R7.b
    public final C2058c a() {
        InterfaceC0652e interfaceC0652eD = AbstractC2564d.d(this);
        if (interfaceC0652eD == null) {
            return null;
        }
        if (H8.i.f(interfaceC0652eD)) {
            interfaceC0652eD = null;
        }
        if (interfaceC0652eD != null) {
            return AbstractC2564d.c(interfaceC0652eD);
        }
        return null;
    }

    @Override // R7.b
    public final AbstractC0287w b() {
        A a10 = this.f7521a;
        if (a10 != null) {
            return a10;
        }
        d(3);
        throw null;
    }

    @Override // R7.b
    public final Map c() {
        Map map = this.f7522b;
        if (map != null) {
            return map;
        }
        d(4);
        throw null;
    }

    @Override // R7.b
    public final N g() {
        N n10 = this.c;
        if (n10 != null) {
            return n10;
        }
        d(5);
        throw null;
    }

    public final String toString() {
        return C2203g.c.x(this, null);
    }
}
