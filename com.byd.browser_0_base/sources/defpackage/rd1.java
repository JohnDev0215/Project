package defpackage;

import android.os.Bundle;
import androidx.mediarouter.media.g;

/* JADX INFO: compiled from: MediaRouteDiscoveryRequest.java */
/* JADX INFO: loaded from: classes.dex */
public final class rd1 {
    public final Bundle a;
    public g b;

    public rd1(g gVar, boolean z) {
        if (gVar == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        Bundle bundle = new Bundle();
        this.a = bundle;
        this.b = gVar;
        bundle.putBundle("selector", gVar.a);
        bundle.putBoolean("activeScan", z);
    }

    public final void a() {
        if (this.b == null) {
            Bundle bundle = this.a.getBundle("selector");
            g gVar = null;
            if (bundle != null) {
                gVar = new g(null, bundle);
            } else {
                g gVar2 = g.c;
            }
            this.b = gVar;
            if (gVar == null) {
                this.b = g.c;
            }
        }
    }

    public final boolean b() {
        return this.a.getBoolean("activeScan");
    }

    public final boolean c() {
        a();
        this.b.a();
        return !r1.b.contains(null);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof rd1)) {
            return false;
        }
        rd1 rd1Var = (rd1) obj;
        a();
        g gVar = this.b;
        rd1Var.a();
        return gVar.equals(rd1Var.b) && b() == rd1Var.b();
    }

    public final int hashCode() {
        a();
        return (b() ? 1 : 0) ^ this.b.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DiscoveryRequest{ selector=");
        a();
        sb.append(this.b);
        sb.append(", activeScan=");
        sb.append(b());
        sb.append(", isValid=");
        sb.append(c());
        sb.append(" }");
        return sb.toString();
    }

    public rd1(Bundle bundle) {
        this.a = bundle;
    }
}
