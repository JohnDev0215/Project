package defpackage;

import defpackage.al;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.ProfileKey;

/* JADX INFO: compiled from: ImageFetcherFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dx0 {
    public static ax0 a(int i, cx0 cx0Var, na0 na0Var, int i2) {
        if (i == 0) {
            return new wl1(cx0Var);
        }
        if (i == 1) {
            return new al(cx0Var, new al.a());
        }
        if (i == 2) {
            return new qx0(a(0, cx0Var, na0Var, i2), na0Var, i2);
        }
        if (i != 3) {
            return null;
        }
        return new qx0(a(1, cx0Var, na0Var, i2), na0Var, i2);
    }

    public static ax0 b(int i, ProfileKey profileKey) {
        Object obj = ThreadUtils.a;
        return a(i, new cx0(profileKey), null, 20971520);
    }

    public static ax0 c(ProfileKey profileKey, na0 na0Var) {
        Object obj = ThreadUtils.a;
        return a(3, new cx0(profileKey), na0Var, 20971520);
    }
}
