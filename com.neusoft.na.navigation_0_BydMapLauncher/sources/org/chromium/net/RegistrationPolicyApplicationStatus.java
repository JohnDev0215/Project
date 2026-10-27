package org.chromium.net;

import org.chromium.base.ApplicationStatus;
import org.chromium.net.NetworkChangeNotifierAutoDetect;

/* JADX INFO: loaded from: classes3.dex */
public class RegistrationPolicyApplicationStatus extends NetworkChangeNotifierAutoDetect.RegistrationPolicy implements ApplicationStatus.ApplicationStateListener {
    private boolean mDestroyed = false;

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.RegistrationPolicy
    protected void init(NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect) {
        super.init(networkChangeNotifierAutoDetect);
        ApplicationStatus.registerApplicationStateListener(this);
        onApplicationStateChange(getApplicationState());
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.RegistrationPolicy
    protected void destroy() {
        if (this.mDestroyed) {
            return;
        }
        ApplicationStatus.unregisterApplicationStateListener(this);
        this.mDestroyed = true;
    }

    @Override // org.chromium.base.ApplicationStatus.ApplicationStateListener
    public void onApplicationStateChange(int i) {
        if (i == 1) {
            register();
        } else if (i == 2) {
            unregister();
        }
    }

    int getApplicationState() {
        return ApplicationStatus.getStateForApplication();
    }
}
