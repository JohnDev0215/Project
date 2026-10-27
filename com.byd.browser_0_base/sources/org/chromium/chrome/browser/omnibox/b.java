package org.chromium.chrome.browser.omnibox;

import defpackage.oj2;
import defpackage.rs1;
import defpackage.zp1;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: LocationBarCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b implements Callback<Profile> {
    public final /* synthetic */ rs1 e;
    public final /* synthetic */ zp1 f;

    public b(rs1 rs1Var, zp1 zp1Var) {
        this.e = rs1Var;
        this.f = zp1Var;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Profile profile) {
        this.e.a(oj2.q(profile));
        this.f.d(this);
    }
}
