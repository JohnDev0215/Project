package g.a.c.d;

import java.io.OutputStream;
import java.security.KeyStore;

/* JADX INFO: loaded from: classes.dex */
public class b implements KeyStore.LoadStoreParameter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public OutputStream f5234a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public KeyStore.ProtectionParameter f5235b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f5236c;

    public OutputStream a() {
        return this.f5234a;
    }

    public boolean b() {
        return this.f5236c;
    }

    @Override // java.security.KeyStore.LoadStoreParameter
    public KeyStore.ProtectionParameter getProtectionParameter() {
        return this.f5235b;
    }
}
