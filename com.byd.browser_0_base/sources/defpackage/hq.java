package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.SparseArray;
import androidx.mediarouter.media.h;
import androidx.mediarouter.media.h.d.C0019d;
import defpackage.ed1;
import defpackage.kp1;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.base.SplitCompatService;
import org.chromium.chrome.browser.media.ui.ChromeMediaNotificationControllerServices$CastListenerService;
import org.chromium.chrome.browser.media.ui.ChromeMediaNotificationControllerServices$PlaybackListenerService;
import org.chromium.chrome.browser.media.ui.ChromeMediaNotificationControllerServices$PresentationListenerService;

/* JADX INFO: compiled from: ChromeMediaNotificationControllerDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hq implements ed1.b {
    public static final SparseArray<c> b;
    public final int a;

    /* JADX INFO: compiled from: ChromeMediaNotificationControllerDelegate.java */
    public static final class a extends b {
        public static final int c = R$id.remote_playback_notification;

        public a() {
            super(c);
        }
    }

    /* JADX INFO: compiled from: ChromeMediaNotificationControllerDelegate.java */
    public static abstract class b extends SplitCompatService.a {
        public final int b;

        public b(int i) {
            this.b = i;
        }

        @Override // org.chromium.chrome.browser.base.SplitCompatService.a
        public final IBinder a() {
            return null;
        }

        @Override // org.chromium.chrome.browser.base.SplitCompatService.a
        public void c() {
            SparseArray<ed1> sparseArray = id1.a;
            int i = this.b;
            ed1 ed1Var = sparseArray.get(i);
            if (ed1Var != null) {
                ed1Var.a = null;
            }
            ed1 ed1Var2 = sparseArray.get(i);
            if (ed1Var2 == null) {
                return;
            }
            ed1Var2.b();
            sparseArray.remove(i);
        }

        /* JADX WARN: Removed duplicated region for block: B:65:0x00ec  */
        @Override // org.chromium.chrome.browser.base.SplitCompatService.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int e(android.content.Intent r8, int r9, int r10) {
            /*
                Method dump skipped, instruction units count: 279
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: hq.b.e(android.content.Intent, int, int):int");
        }
    }

    /* JADX INFO: compiled from: ChromeMediaNotificationControllerDelegate.java */
    public static class c {
        public final Class<?> a;
        public final String b;

        public c(Class<?> cls, String str) {
            this.a = cls;
            this.b = str;
        }
    }

    /* JADX INFO: compiled from: ChromeMediaNotificationControllerDelegate.java */
    public static final class d extends b {
        public static final int d = R$id.media_playback_notification;
        public final a c;

        /* JADX INFO: compiled from: ChromeMediaNotificationControllerDelegate.java */
        public class a extends BroadcastReceiver {
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                boolean z;
                if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
                    SparseArray<c> sparseArray = hq.b;
                    Intent intent2 = new Intent(xz.a, (Class<?>) ChromeMediaNotificationControllerServices$PlaybackListenerService.class);
                    intent2.setAction(intent.getAction());
                    try {
                        xz.a.startService(intent2);
                        z = true;
                    } catch (RuntimeException unused) {
                        z = false;
                    }
                    oj2.O("Media.Android.BecomingNoisy", z);
                }
            }
        }

        public d() {
            super(d);
            this.c = new a();
        }

        @Override // org.chromium.chrome.browser.base.SplitCompatService.a
        public final void b() {
            xz.g(this.a, this.c, new IntentFilter("android.media.AUDIO_BECOMING_NOISY"));
        }

        @Override // hq.b, org.chromium.chrome.browser.base.SplitCompatService.a
        public final void c() {
            this.a.unregisterReceiver(this.c);
            super.c();
        }
    }

    /* JADX INFO: compiled from: ChromeMediaNotificationControllerDelegate.java */
    public static final class e extends b {
        public static final int c = R$id.presentation_notification;

        public e() {
            super(c);
        }
    }

    static {
        SparseArray<c> sparseArray = new SparseArray<>();
        b = sparseArray;
        sparseArray.put(d.d, new c(ChromeMediaNotificationControllerServices$PlaybackListenerService.class, "MediaPlayback"));
        sparseArray.put(e.c, new c(ChromeMediaNotificationControllerServices$PresentationListenerService.class, "MediaPresentation"));
        sparseArray.put(a.c, new c(ChromeMediaNotificationControllerServices$CastListenerService.class, "MediaRemote"));
    }

    public hq(int i) {
        this.a = i;
    }

    @Override // ed1.b
    public final qq a() {
        return n3.D("media", new o8(6, this.a, (String) null));
    }

    @Override // ed1.b
    public final void b(lp1 lp1Var) {
        kp1.a.a.b(6, lp1Var.a);
    }

    @Override // ed1.b
    public final Intent c() {
        Class<?> cls = b.get(this.a).a;
        if (cls != null) {
            return new Intent(xz.a, cls);
        }
        return null;
    }

    @Override // ed1.b
    public final String d() {
        return b.get(this.a).b;
    }

    @Override // ed1.b
    public final String e() {
        return xz.a.getString(R$string.app_name);
    }

    @Override // ed1.b
    public final void f(MediaSessionCompat mediaSessionCompat) {
        h.d(xz.a);
        h.b();
        h.d dVarC = h.c();
        dVarC.B = mediaSessionCompat;
        h.d.C0019d c0019d = mediaSessionCompat != null ? dVarC.new C0019d(mediaSessionCompat) : null;
        h.d.C0019d c0019d2 = dVarC.A;
        if (c0019d2 != null) {
            c0019d2.a();
        }
        dVarC.A = c0019d;
        if (c0019d != null) {
            dVarC.m();
        }
    }
}
