package org.chromium.chrome.browser.profiles;

import defpackage.rs1;
import defpackage.w80;
import org.chromium.chrome.browser.profiles.ProfileManager;

/* JADX INFO: compiled from: OriginalProfileSupplier.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a extends rs1<Profile> implements w80, ProfileManager.a {
    public a() {
        ProfileManager.a(this);
    }

    @Override // org.chromium.chrome.browser.profiles.ProfileManager.a
    public final void b(Profile profile) {
        if (profile.h()) {
            return;
        }
        a(profile);
        ProfileManager.b(this);
    }

    @Override // defpackage.w80
    public final void destroy() {
        if (f()) {
            return;
        }
        ProfileManager.b(this);
    }

    @Override // org.chromium.chrome.browser.profiles.ProfileManager.a
    public final void e(Profile profile) {
    }
}
