package org.chromium.chrome.browser.download;

import defpackage.bc0;
import defpackage.fd0;
import org.chromium.base.ApplicationStatus;
import org.chromium.build.annotations.UsedByReflection;
import org.chromium.chrome.browser.profiles.OTRProfileID;

/* JADX INFO: loaded from: classes.dex */
@UsedByReflection
public class DownloadNotificationServiceObserver implements bc0 {
    @UsedByReflection
    public DownloadNotificationServiceObserver() {
    }

    @Override // defpackage.bc0
    public final void a() {
        fd0 fd0Var = fd0.a.a;
        fd0Var.getClass();
        if (ApplicationStatus.a.isEmpty()) {
            fd0Var.b();
        }
    }

    @Override // defpackage.bc0
    public final void b() {
        fd0 fd0Var = fd0.a.a;
        fd0Var.b();
        for (f fVar : fd0Var.d.a) {
            OTRProfileID oTRProfileID = fVar.b;
            OTRProfileID oTRProfileID2 = OTRProfileID.b;
            if (!(oTRProfileID != null)) {
                fd0Var.e(fVar.f, fVar.d, true, true, false, false, 1);
            }
        }
    }
}
