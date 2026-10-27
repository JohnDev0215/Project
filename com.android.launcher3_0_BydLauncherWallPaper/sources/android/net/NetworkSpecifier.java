package android.net;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes2.dex */
public abstract class NetworkSpecifier {
    @SystemApi
    public boolean canBeSatisfiedBy(NetworkSpecifier networkSpecifier) {
        return false;
    }

    @SystemApi
    public NetworkSpecifier redact() {
        return this;
    }
}
