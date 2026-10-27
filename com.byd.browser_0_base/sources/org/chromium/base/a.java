package org.chromium.base;

import android.app.Activity;
import org.chromium.base.ApplicationStatus;

/* JADX INFO: compiled from: ApplicationStatus.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements ApplicationStatus.g {
    @Override // org.chromium.base.ApplicationStatus.g
    public final void e(Activity activity, boolean z) {
        int iC;
        if (!z || activity == ApplicationStatus.c || (iC = ApplicationStatus.c(activity)) == 6 || iC == 5) {
            return;
        }
        ApplicationStatus.c = activity;
    }
}
