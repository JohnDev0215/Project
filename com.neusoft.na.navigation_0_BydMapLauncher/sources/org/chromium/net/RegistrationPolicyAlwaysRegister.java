package org.chromium.net;

import org.chromium.net.NetworkChangeNotifierAutoDetect;

/* JADX INFO: loaded from: classes3.dex */
public class RegistrationPolicyAlwaysRegister extends NetworkChangeNotifierAutoDetect.RegistrationPolicy {
    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.RegistrationPolicy
    protected void destroy() {
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.RegistrationPolicy
    protected void init(NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect) {
        super.init(networkChangeNotifierAutoDetect);
        register();
    }
}
