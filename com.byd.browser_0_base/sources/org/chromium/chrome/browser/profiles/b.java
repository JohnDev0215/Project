package org.chromium.chrome.browser.profiles;

import defpackage.nw2;
import defpackage.ps;
import java.util.HashMap;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.profiles.ProfileManager;

/* JADX INFO: compiled from: ProfileKeyedMap.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b<T> {
    public static final ps d = new ps(9);
    public final HashMap a = new HashMap();
    public final Callback<T> b;
    public a c;

    public b(Callback<T> callback) {
        this.b = callback;
    }

    public final T a(Profile profile, nw2<T> nw2Var) {
        HashMap map = this.a;
        T t = (T) map.get(profile);
        if (t == null) {
            t = nw2Var.get();
            map.put(profile, t);
        }
        if (this.c == null) {
            a aVar = new a();
            this.c = aVar;
            ProfileManager.a(aVar);
        }
        return t;
    }

    /* JADX INFO: compiled from: ProfileKeyedMap.java */
    public class a implements ProfileManager.a {
        public a() {
        }

        @Override // org.chromium.chrome.browser.profiles.ProfileManager.a
        public final void e(Profile profile) {
            b bVar = b.this;
            Object objRemove = bVar.a.remove(profile);
            if (objRemove == null) {
                return;
            }
            bVar.b.onResult((T) objRemove);
        }

        @Override // org.chromium.chrome.browser.profiles.ProfileManager.a
        public final void b(Profile profile) {
        }
    }
}
