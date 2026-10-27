package k3;

import android.app.PendingIntent;
import android.os.Bundle;
import com.stingray.karaoke.car.player.PlayerService;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class A0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f17713b = new Object();
    public static final HashMap c = new HashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final I0 f17714a;

    public A0(PlayerService playerService, N5.b0 b0Var, PendingIntent pendingIntent, d5.d0 d0Var, d5.d0 d0Var2, d5.d0 d0Var3, l3.P p10, Bundle bundle, Bundle bundle2, io.sentry.internal.debugmeta.c cVar) {
        synchronized (f17713b) {
            HashMap map = c;
            if (map.containsKey("")) {
                throw new IllegalStateException("Session ID must be unique. ID=");
            }
            map.put("", this);
        }
        this.f17714a = new I0(this, playerService, b0Var, pendingIntent, d0Var, d0Var2, d0Var3, p10, bundle, bundle2, cVar);
    }

    public final C1679y0 a() {
        I0 i02 = this.f17714a;
        C1679y0 c1679y0 = i02.f17815x;
        if (c1679y0 != null) {
            return i02.r(c1679y0);
        }
        return null;
    }

    public final e2.W b() {
        return (e2.W) this.f17714a.f17811t.f14069b;
    }
}
