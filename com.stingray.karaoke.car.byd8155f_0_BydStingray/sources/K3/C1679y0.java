package k3;

import android.os.Bundle;
import h2.AbstractC1343A;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: renamed from: k3.y0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1679y0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l3.T f18160a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18161b;
    public final int c;
    public final InterfaceC1677x0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Bundle f18162e;

    public C1679y0(l3.T t10, int i7, int i10, boolean z6, InterfaceC1677x0 interfaceC1677x0, Bundle bundle) {
        this.f18160a = t10;
        this.f18161b = i7;
        this.c = i10;
        this.d = interfaceC1677x0;
        this.f18162e = bundle;
    }

    public final Bundle a() {
        return new Bundle(this.f18162e);
    }

    public final String b() {
        return this.f18160a.f18543a.f18539a;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C1679y0)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        C1679y0 c1679y0 = (C1679y0) obj;
        InterfaceC1677x0 interfaceC1677x0 = this.d;
        if (interfaceC1677x0 == null && c1679y0.d == null) {
            return this.f18160a.equals(c1679y0.f18160a);
        }
        InterfaceC1677x0 interfaceC1677x02 = c1679y0.d;
        int i7 = AbstractC1343A.f15195a;
        return Objects.equals(interfaceC1677x0, interfaceC1677x02);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.d, this.f18160a});
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ControllerInfo {pkg=");
        l3.T t10 = this.f18160a;
        sb.append(t10.f18543a.f18539a);
        sb.append(", uid=");
        return M5.b.h(sb, t10.f18543a.c, "}");
    }
}
