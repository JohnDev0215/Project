package org.chromium.net;

import org.chromium.base.ApplicationStatus;
import org.chromium.net.NetworkChangeNotifierAutoDetect;

/* JADX INFO: compiled from: RegistrationPolicyApplicationStatus.java */
/* JADX INFO: loaded from: classes3.dex */
public final class c extends NetworkChangeNotifierAutoDetect.h implements ApplicationStatus.d {
    public boolean f;

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.h
    public final void a() {
        if (this.f) {
            return;
        }
        org.chromium.base.b<ApplicationStatus.d> bVar = ApplicationStatus.f;
        if (bVar != null) {
            bVar.c(this);
        }
        this.f = true;
    }

    @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.h
    public final void b(NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect) {
        this.e = networkChangeNotifierAutoDetect;
        ApplicationStatus.e(this);
        v(0);
    }

    @Override // org.chromium.base.ApplicationStatus.d
    public final void v(int i) {
        if (ApplicationStatus.hasVisibleActivities()) {
            c();
        } else {
            this.e.f();
        }
    }
}
