package org.chromium.net;

import org.chromium.net.NetworkChangeNotifierAutoDetect;

/* JADX INFO: compiled from: RegistrationPolicyAlwaysRegister.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b extends NetworkChangeNotifierAutoDetect.h {
    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.h
    public final void b(NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect) {
        this.e = networkChangeNotifierAutoDetect;
        c();
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.h
    public final void a() {
    }
}
