package defpackage;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: WarmupManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class yo3 extends i9<Void> {
    public final /* synthetic */ String h;
    public final /* synthetic */ zo3 i;

    public yo3(zo3 zo3Var, String str) {
        this.i = zo3Var;
        this.h = str;
    }

    @Override // defpackage.i9
    public final Void b() {
        TraceEvent traceEventH0;
        try {
            traceEventH0 = TraceEvent.H0("WarmupManager.prefetchDnsForUrlInBackground", null);
        } catch (MalformedURLException | UnknownHostException unused) {
        }
        try {
            InetAddress.getByName(new URL(this.h).getHost());
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return null;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused2) {
                }
            }
            throw th;
        }
    }

    @Override // defpackage.i9
    public final void l(Void r3) {
        zo3 zo3Var = this.i;
        HashSet hashSet = zo3Var.a;
        String str = this.h;
        hashSet.remove(str);
        HashMap map = zo3Var.b;
        if (map.containsKey(str)) {
            Profile profile = (Profile) map.get(str);
            map.remove(str);
            zo3Var.c(str, profile);
        }
    }
}
