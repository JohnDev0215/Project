package k3;

import android.os.Bundle;

/* JADX INFO: compiled from: LoginException.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends Exception {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Bundle f7261e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final String f7262g;

    public a(String str, Bundle bundle) {
        super(str);
        this.f7262g = str;
        this.f7261e = bundle;
    }

    public Bundle a() {
        return this.f7261e;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.f7262g;
    }
}
