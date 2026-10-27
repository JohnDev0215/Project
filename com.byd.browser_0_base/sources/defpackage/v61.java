package defpackage;

import J.N;
import java.util.HashMap;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileManager;

/* JADX INFO: compiled from: LevelDBPersistedTabDataStorageFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class v61 implements r02<u61> {
    public static final HashMap e = new HashMap();
    public static ProfileManager.a f;

    public v61() {
        if (f == null) {
            a aVar = new a();
            f = aVar;
            ProfileManager.a(aVar);
        }
    }

    @Override // defpackage.r02
    public final q02 a() {
        Profile profileD = Profile.d();
        HashMap map = e;
        u61 u61Var = (u61) map.get(profileD);
        if (u61Var != null) {
            return u61Var;
        }
        u61 u61Var2 = new u61(profileD);
        map.put(profileD, u61Var2);
        return u61Var2;
    }

    /* JADX INFO: compiled from: LevelDBPersistedTabDataStorageFactory.java */
    public class a implements ProfileManager.a {
        @Override // org.chromium.chrome.browser.profiles.ProfileManager.a
        public final void e(Profile profile) {
            HashMap map = v61.e;
            u61 u61Var = (u61) map.get(profile);
            if (u61Var != null) {
                N.MJWrJ5q6(u61Var.a.a);
                map.remove(profile);
            }
            if (map.isEmpty()) {
                ProfileManager.b(v61.f);
                v61.f = null;
            }
        }

        @Override // org.chromium.chrome.browser.profiles.ProfileManager.a
        public final void b(Profile profile) {
        }
    }
}
