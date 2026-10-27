package k3;

import android.content.ComponentName;
import android.media.session.MediaSession;
import android.os.Bundle;
import h2.AbstractC1343A;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class G1 implements D1 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f17781f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f17782g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f17783h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f17784i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f17785j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f17786k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f17787a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f17788b;
    public final ComponentName c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Bundle f17789e;

    static {
        int i7 = AbstractC1343A.f15195a;
        f17781f = Integer.toString(0, 36);
        f17782g = Integer.toString(1, 36);
        f17783h = Integer.toString(2, 36);
        f17784i = Integer.toString(3, 36);
        f17785j = Integer.toString(4, 36);
        f17786k = Integer.toString(5, 36);
    }

    public G1(ComponentName componentName, int i7) {
        String packageName = componentName.getPackageName();
        Bundle bundle = Bundle.EMPTY;
        this.f17787a = i7;
        this.f17788b = 101;
        this.c = componentName;
        this.d = packageName;
        this.f17789e = bundle;
    }

    @Override // k3.D1
    public final int a() {
        return this.f17787a;
    }

    @Override // k3.D1
    public final int b() {
        return this.f17788b != 101 ? 0 : 2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof G1)) {
            return false;
        }
        G1 g12 = (G1) obj;
        int i7 = g12.f17788b;
        int i10 = this.f17788b;
        if (i10 != i7) {
            return false;
        }
        if (i10 == 100) {
            int i11 = AbstractC1343A.f15195a;
            return true;
        }
        if (i10 != 101) {
            return false;
        }
        int i12 = AbstractC1343A.f15195a;
        return Objects.equals(this.c, g12.c);
    }

    @Override // k3.D1
    public final String getPackageName() {
        return this.d;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f17788b), this.c, null});
    }

    @Override // k3.D1
    public final Bundle m() {
        return new Bundle(this.f17789e);
    }

    @Override // k3.D1
    public final boolean n() {
        return true;
    }

    @Override // k3.D1
    public final ComponentName o() {
        return this.c;
    }

    @Override // k3.D1
    public final Object p() {
        return null;
    }

    @Override // k3.D1
    public final String q() {
        ComponentName componentName = this.c;
        return componentName == null ? "" : componentName.getClassName();
    }

    @Override // k3.D1
    public final int r() {
        return 0;
    }

    @Override // k3.D1
    public final Bundle s() {
        Bundle bundle = new Bundle();
        bundle.putBundle(f17781f, null);
        bundle.putInt(f17782g, this.f17787a);
        bundle.putInt(f17783h, this.f17788b);
        bundle.putParcelable(f17784i, this.c);
        bundle.putString(f17785j, this.d);
        bundle.putBundle(f17786k, this.f17789e);
        return bundle;
    }

    @Override // k3.D1
    public final MediaSession.Token t() {
        return null;
    }

    public final String toString() {
        return M5.b.h(new StringBuilder("SessionToken {legacy, uid="), this.f17787a, "}");
    }
}
