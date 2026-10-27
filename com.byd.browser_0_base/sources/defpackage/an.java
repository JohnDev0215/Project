package defpackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: compiled from: ChannelsUpdater.java */
/* JADX INFO: loaded from: classes2.dex */
public final class an {
    public static final Object e = new Object();
    public final zm a;
    public final SharedPreferencesManager b;
    public final boolean c = true;
    public final int d = 4;

    /* JADX INFO: compiled from: ChannelsUpdater.java */
    public static class a {
        public static final an a = new an(SharedPreferencesManager.getInstance(), new zm(new NotificationManagerProxyImpl(xz.a), xz.a.getResources()));
    }

    public an(SharedPreferencesManager sharedPreferencesManager, zm zmVar) {
        this.b = sharedPreferencesManager;
        this.a = zmVar;
    }

    public final void a() {
        synchronized (e) {
            if (this.c) {
                zm zmVar = this.a;
                zmVar.getClass();
                Iterator it = new ArrayList(Arrays.asList(xp.a)).iterator();
                while (it.hasNext()) {
                    zmVar.a.a((String) it.next());
                }
                this.a.b();
                this.b.m(this.d, "channels_version_key");
            }
        }
    }
}
