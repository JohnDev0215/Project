package p2;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import e2.C1195c;
import h2.AbstractC1343A;
import java.util.Objects;
import l3.C1764u;
import o2.C2026c;

/* JADX INFO: renamed from: p2.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2090e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f20701a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2026c f20702b;
    public final Handler c;
    public final C2088c d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final h2.r f20703e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C2089d f20704f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public C2087b f20705g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public C1764u f20706h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public C1195c f20707i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f20708j;

    public C2090e(Context context, C2026c c2026c, C1195c c1195c, C1764u c1764u) {
        Context applicationContext = context.getApplicationContext();
        this.f20701a = applicationContext;
        this.f20702b = c2026c;
        this.f20707i = c1195c;
        this.f20706h = c1764u;
        Handler handler = new Handler(AbstractC1343A.q(), null);
        this.c = handler;
        this.d = AbstractC1343A.f15195a >= 23 ? new C2088c(this) : null;
        this.f20703e = new h2.r(this, 3);
        C2087b c2087b = C2087b.c;
        String str = AbstractC1343A.c;
        Uri uriFor = ("Amazon".equals(str) || "Xiaomi".equals(str)) ? Settings.Global.getUriFor("external_surround_sound_enabled") : null;
        this.f20704f = uriFor != null ? new C2089d(this, handler, applicationContext.getContentResolver(), uriFor) : null;
    }

    public final void a(C2087b c2087b) {
        w2.o oVar;
        if (!this.f20708j || c2087b.equals(this.f20705g)) {
            return;
        }
        this.f20705g = c2087b;
        v vVar = (v) this.f20702b.f20404m;
        vVar.getClass();
        Looper looperMyLooper = Looper.myLooper();
        Looper looper = vVar.f20813f0;
        if (looper != looperMyLooper) {
            String name = looper == null ? "null" : looper.getThread().getName();
            throw new IllegalStateException("Current looper (" + (looperMyLooper == null ? "null" : looperMyLooper.getThread().getName()) + ") is not the playback looper (" + name + ")");
        }
        if (c2087b.equals(vVar.f20831w)) {
            return;
        }
        vVar.f20831w = c2087b;
        C1764u c1764u = vVar.f20826r;
        if (c1764u != null) {
            x xVar = (x) c1764u.f18608m;
            synchronized (xVar.l) {
                oVar = xVar.B;
            }
            if (oVar != null) {
                synchronized (oVar.c) {
                    oVar.f23264g.getClass();
                }
            }
        }
    }

    public final void b(AudioDeviceInfo audioDeviceInfo) {
        C1764u c1764u = this.f20706h;
        AudioDeviceInfo audioDeviceInfo2 = c1764u == null ? null : (AudioDeviceInfo) c1764u.f18608m;
        int i7 = AbstractC1343A.f15195a;
        if (Objects.equals(audioDeviceInfo, audioDeviceInfo2)) {
            return;
        }
        C1764u c1764u2 = audioDeviceInfo != null ? new C1764u(audioDeviceInfo, 11) : null;
        this.f20706h = c1764u2;
        a(C2087b.c(this.f20701a, this.f20707i, c1764u2));
    }
}
