package defpackage;

import J.N;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.app.NotificationCompat$Builder;
import defpackage.de1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.chromium.base.CollectionUtil;
import org.chromium.components.browser_ui.media.R$drawable;
import org.chromium.components.browser_ui.media.R$string;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;
import org.chromium.content.browser.MediaSessionImpl;
import org.chromium.services.media_session.MediaMetadata;
import org.chromium.services.media_session.MediaPosition;

/* JADX INFO: compiled from: MediaNotificationController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ed1 {
    public Service a;
    public final b b;
    public final SparseArray<c> c;
    public mp1 d;
    public gd1 e;
    public MediaSessionCompat f;
    public final d g;
    public final a h = new a();

    /* JADX INFO: compiled from: MediaNotificationController.java */
    public class a extends MediaSessionCompat.Callback {
        public a() {
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public final void onFastForward() {
            ed1.this.d(5);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public final void onPause() {
            ed1.this.e(1001);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public final void onPlay() {
            ed1.this.f(1001);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public final void onRewind() {
            ed1.this.d(4);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public final void onSeekTo(long j) {
            fe1 fe1Var;
            gd1 gd1Var = ed1.this.e;
            if (gd1Var == null || (fe1Var = de1.this.d) == null) {
                return;
            }
            MediaSessionImpl mediaSessionImpl = fe1Var.a;
            N.MK7btVfc(mediaSessionImpl.k, mediaSessionImpl, j);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public final void onSkipToNext() {
            ed1.this.d(3);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public final void onSkipToPrevious() {
            ed1.this.d(2);
        }
    }

    /* JADX INFO: compiled from: MediaNotificationController.java */
    public interface b {
        qq a();

        void b(lp1 lp1Var);

        Intent c();

        String d();

        String e();

        void f(MediaSessionCompat mediaSessionCompat);
    }

    /* JADX INFO: compiled from: MediaNotificationController.java */
    public static final class c {
        public final int a;
        public final int b;
        public final String c;
        public final int d;

        public c(int i, int i2, int i3, String str) {
            this.a = i;
            this.b = i2;
            this.c = str;
            this.d = i3;
        }
    }

    /* JADX INFO: compiled from: MediaNotificationController.java */
    public static class d {
        public final ed1 a;
        public final Handler b = new Handler();
        public a c;
        public gd1 d;

        /* JADX INFO: compiled from: MediaNotificationController.java */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                d dVar = d.this;
                gd1 gd1Var = dVar.d;
                if (gd1Var == null) {
                    dVar.c = null;
                } else {
                    dVar.a(gd1Var);
                    dVar.d = null;
                }
            }
        }

        public d(ed1 ed1Var) {
            this.a = ed1Var;
        }

        public final void a(gd1 gd1Var) {
            ed1 ed1Var = this.a;
            if (!ed1.g(ed1Var.e, gd1Var)) {
                ed1Var.e = gd1Var;
                Service service = ed1Var.a;
                if (service != null || !gd1Var.c) {
                    if (service == null) {
                        ed1Var.h();
                        ed1Var.j();
                        try {
                            Intent intentC = ed1Var.b.c();
                            Context context = xz.a;
                            Object obj = bz.a;
                            context.startForegroundService(intentC);
                        } catch (RuntimeException unused) {
                        }
                    } else {
                        ed1Var.i(false, false);
                    }
                }
            }
            a aVar = new a();
            this.c = aVar;
            if (this.b.postDelayed(aVar, 500L)) {
                return;
            }
            Log.w("cr_MediaNotification", "Failed to post the throttler task.");
            this.c = null;
        }
    }

    public ed1(b bVar) {
        this.b = bVar;
        SparseArray<c> sparseArray = new SparseArray<>();
        this.c = sparseArray;
        sparseArray.put(0, new c(R$drawable.ic_play_arrow_white_24dp, R$string.accessibility_play, 17, "org.chromium.components.browser_ui.media.ACTION_PLAY"));
        sparseArray.put(1, new c(R$drawable.ic_pause_white_24dp, R$string.accessibility_pause, 18, "org.chromium.components.browser_ui.media.ACTION_PAUSE"));
        sparseArray.put(7, new c(R$drawable.ic_stop_white_24dp, R$string.accessibility_stop, 19, "org.chromium.components.browser_ui.media.ACTION_STOP"));
        sparseArray.put(2, new c(R$drawable.ic_skip_previous_white_24dp, R$string.accessibility_previous_track, 20, "org.chromium.components.browser_ui.media.ACTION_PREVIOUS_TRACK"));
        sparseArray.put(3, new c(R$drawable.ic_skip_next_white_24dp, R$string.accessibility_next_track, 21, "org.chromium.components.browser_ui.media.ACTION_NEXT_TRACK"));
        sparseArray.put(5, new c(R$drawable.ic_fast_forward_white_24dp, R$string.accessibility_seek_forward, 22, "org.chromium.components.browser_ui.media.ACTION_SEEK_FORWARD"));
        sparseArray.put(4, new c(R$drawable.ic_fast_rewind_white_24dp, R$string.accessibility_seek_backward, 23, "MediaNotificationmanager.ListenerService.SEEK_BACKWARD"));
        this.g = new d(this);
    }

    public static void c(Service service, lp1 lp1Var) {
        int i = lp1Var.b.b;
        Notification notification = lp1Var.a;
        if (notification == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 31) {
            g6.a(service, i, notification, 2);
        } else {
            service.startForeground(i, notification, 2);
        }
    }

    public static boolean g(gd1 gd1Var, gd1 gd1Var2) {
        Set<Integer> set = gd1Var2.n;
        if ((set == null || !set.isEmpty()) && !gd1Var2.equals(gd1Var)) {
            return (!gd1Var2.c || gd1Var == null || gd1Var2.e == gd1Var.e) ? false : true;
        }
        return true;
    }

    public final void a(int i) {
        MediaSessionCompat mediaSessionCompat;
        gd1 gd1Var = this.e;
        if (gd1Var != null && gd1Var.e == i) {
            if (!((gd1Var.a & 1) != 0) || gd1Var.c || (mediaSessionCompat = this.f) == null) {
                return;
            }
            mediaSessionCompat.setActive(true);
        }
    }

    public final void b() {
        d dVar = this.g;
        dVar.b.removeCallbacks(dVar.c);
        dVar.d = null;
        dVar.c = null;
        if (this.e == null) {
            return;
        }
        ro1 ro1Var = new ro1(xz.a);
        ro1Var.b.cancel(null, this.e.k);
        MediaSessionCompat mediaSessionCompat = this.f;
        if (mediaSessionCompat != null) {
            mediaSessionCompat.setCallback(null);
            this.f.setActive(false);
            this.f.release();
            this.f = null;
        }
        Service service = this.a;
        if (service != null) {
            try {
                service.stopForeground(1);
            } catch (NullPointerException e) {
                Log.e("cr_ForegroundService", "Failed to stop foreground service, ", e);
            }
            this.a.stopSelf();
        }
        this.e = null;
        this.d = null;
    }

    public final void d(int i) {
        fe1 fe1Var;
        gd1 gd1Var = this.e;
        if (gd1Var == null) {
            return;
        }
        de1.a aVar = (de1.a) gd1Var.m;
        aVar.getClass();
        if ((i >= 0 && i <= 19) && (fe1Var = de1.this.d) != null) {
            MediaSessionImpl mediaSessionImpl = fe1Var.a;
            N.MAqRqyJa(mediaSessionImpl.k, mediaSessionImpl, i);
        }
    }

    public final void e(int i) {
        gd1 gd1Var = this.e;
        if (gd1Var == null || gd1Var.c) {
            return;
        }
        de1 de1Var = de1.this;
        if (de1Var.g()) {
            return;
        }
        Integer numC = de1.c(i);
        if (numC != null) {
            oj2.U(numC.intValue(), 3, "Media.Session.Pause");
        }
        MediaSessionImpl mediaSessionImpl = de1Var.d.a;
        if (mediaSessionImpl == null) {
            return;
        }
        N.M5LC9gX$(mediaSessionImpl.k, mediaSessionImpl);
    }

    public final void f(int i) {
        gd1 gd1Var = this.e;
        if (gd1Var == null || !gd1Var.c) {
            return;
        }
        de1 de1Var = de1.this;
        if (de1Var.g()) {
            return;
        }
        Integer numC = de1.c(i);
        if (numC != null) {
            oj2.U(numC.intValue(), 3, "Media.Session.Play");
        }
        MediaSessionImpl mediaSessionImpl = de1Var.d.a;
        if (mediaSessionImpl == null) {
            return;
        }
        N.MlezJYnz(mediaSessionImpl.k, mediaSessionImpl);
    }

    public final void h() {
        MediaMetadataCompat mediaMetadataCompatBuild;
        if ((this.e.a & 1) != 0) {
            MediaSessionCompat mediaSessionCompat = this.f;
            b bVar = this.b;
            if (mediaSessionCompat == null) {
                MediaSessionCompat mediaSessionCompat2 = new MediaSessionCompat(xz.a, bVar.e());
                mediaSessionCompat2.setCallback(this.h);
                mediaSessionCompat2.setActive(true);
                this.f = mediaSessionCompat2;
            }
            a(this.e.e);
            bVar.f(this.f);
            MediaSessionCompat mediaSessionCompat3 = this.f;
            MediaMetadataCompat.Builder builder = new MediaMetadataCompat.Builder();
            gd1 gd1Var = this.e;
            if (gd1Var.f) {
                mediaMetadataCompatBuild = builder.build();
            } else {
                builder.putString(MediaMetadataCompat.METADATA_KEY_TITLE, gd1Var.b.a);
                builder.putString(MediaMetadataCompat.METADATA_KEY_ARTIST, this.e.d);
                if (!TextUtils.isEmpty(this.e.b.b)) {
                    builder.putString(MediaMetadataCompat.METADATA_KEY_ARTIST, this.e.b.b);
                }
                if (!TextUtils.isEmpty(this.e.b.c)) {
                    builder.putString(MediaMetadataCompat.METADATA_KEY_ALBUM, this.e.b.c);
                }
                Bitmap bitmap = this.e.j;
                if (bitmap != null) {
                    builder.putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, bitmap);
                }
                MediaPosition mediaPosition = this.e.o;
                if (mediaPosition != null) {
                    builder.putLong(MediaMetadataCompat.METADATA_KEY_DURATION, mediaPosition.a());
                }
                mediaMetadataCompatBuild = builder.build();
            }
            mediaSessionCompat3.setMetadata(mediaMetadataCompatBuild);
            MediaSessionCompat mediaSessionCompat4 = this.f;
            PlaybackStateCompat.Builder builder2 = new PlaybackStateCompat.Builder();
            Set<Integer> set = this.e.n;
            if (set != null) {
                j = set.contains(2) ? 22L : 6L;
                if (set.contains(3)) {
                    j |= 32;
                }
                if (set.contains(5)) {
                    j |= 64;
                }
                if (set.contains(4)) {
                    j |= 8;
                }
                if (set.contains(8)) {
                    j |= 256;
                }
            }
            PlaybackStateCompat.Builder actions = builder2.setActions(j);
            gd1 gd1Var2 = this.e;
            int i = gd1Var2.c ? 2 : 3;
            MediaPosition mediaPosition2 = gd1Var2.o;
            if (mediaPosition2 != null) {
                actions.setState(i, mediaPosition2.b.longValue(), this.e.o.c.floatValue(), this.e.o.d.longValue());
            } else {
                actions.setState(i, -1L, 1.0f);
            }
            mediaSessionCompat4.setPlaybackState(actions.build());
        }
    }

    public final void i(boolean z, boolean z2) {
        boolean z3;
        Service service = this.a;
        if (service == null) {
            return;
        }
        gd1 gd1Var = this.e;
        b bVar = this.b;
        if (gd1Var == null) {
            if (z) {
                c(service, bVar.a().f());
                try {
                    this.a.stopForeground(1);
                    return;
                } catch (NullPointerException e) {
                    Log.e("cr_ForegroundService", "Failed to stop foreground service, ", e);
                    return;
                }
            }
            return;
        }
        h();
        j();
        lp1 lp1VarF = ((np1) this.d).f();
        if (z) {
            c(this.a, lp1VarF);
            z3 = true;
        } else {
            z3 = false;
        }
        gd1 gd1Var2 = this.e;
        if (((gd1Var2.a & 4) != 0) && gd1Var2.c) {
            try {
                this.a.stopForeground(2);
            } catch (NullPointerException e2) {
                Log.e("cr_ForegroundService", "Failed to stop foreground service, ", e2);
            }
            new NotificationManagerProxyImpl(xz.a).c(lp1VarF);
        } else if (!z3) {
            try {
                Service service2 = this.a;
                int i = gd1Var2.k;
                Notification notification = lp1VarF.a;
                if (notification != null) {
                    if (Build.VERSION.SDK_INT >= 31) {
                        g6.a(service2, i, notification, 2);
                    } else {
                        service2.startForeground(i, notification, 2);
                    }
                }
            } catch (RuntimeException unused) {
                new NotificationManagerProxyImpl(xz.a).c(lp1VarF);
            }
        }
        if (z2) {
            bVar.b(lp1VarF);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v43 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v30 */
    /* JADX WARN: Type inference failed for: r3v38 */
    /* JADX WARN: Type inference failed for: r3v43 */
    /* JADX WARN: Type inference failed for: r3v44 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    public final void j() {
        int[] iArrC;
        b bVar = this.b;
        qq qqVarA = bVar.a();
        this.d = qqVarA;
        gd1 gd1Var = this.e;
        if (gd1Var.f) {
            qqVarA.k(xz.a.getResources().getString(R$string.media_notification_incognito));
            qqVarA.r(xz.a.getResources().getString(R$string.notification_incognito_tab));
        } else {
            qqVarA.k(gd1Var.b.a);
            MediaMetadata mediaMetadata = this.e.b;
            String str = mediaMetadata.b;
            if (str == null) {
                str = "";
            }
            String str2 = mediaMetadata.c;
            String str3 = str2 != null ? str2 : "";
            qqVarA.j((str.isEmpty() || str3.isEmpty()) ? str.concat(str3) : C0175b.k(str, " - ", str3));
            qqVarA.r(this.e.d);
        }
        gd1 gd1Var2 = this.e;
        if (((gd1Var2.a & 1) != 0) == true) {
            Bitmap bitmap = gd1Var2.h;
            if (bitmap != null && !gd1Var2.f) {
                qqVarA.m(bitmap);
            }
        } else {
            qqVarA.m(null);
        }
        HashSet hashSet = new HashSet();
        if (((this.e.a & 1) != 0) != false) {
            Set<Integer> set = this.e.n;
            if (set != null) {
                hashSet.addAll(set);
            }
            if (this.e.c) {
                hashSet.remove(1);
                hashSet.add(0);
            } else {
                hashSet.remove(0);
                hashSet.add(1);
            }
        }
        if (((this.e.a & 2) != 0) == true) {
            hashSet.add(7);
        } else {
            hashSet.remove(7);
        }
        int[] iArr = {2, 4, 0, 1, 5, 3, 7};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 7; i++) {
            int i2 = iArr[i];
            if (hashSet.contains(Integer.valueOf(i2))) {
                arrayList.add(Integer.valueOf(i2));
            }
            if (arrayList.size() == 5) {
                break;
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            c cVar = this.c.get(((Integer) it.next()).intValue());
            int i3 = cVar.a;
            String string = xz.a.getResources().getString(cVar.b);
            Intent action = bVar.c().setAction(cVar.c);
            Context context = xz.a;
            int iD = 268435456 | q11.d(false) | q11.d(false);
            qqVarA.b(i3, string, new o8(PendingIntent.getService(context, 0, action, iD), iD, 0), cVar.d);
        }
        if (((this.e.a & 1) != 0) != false) {
            MediaSessionCompat mediaSessionCompat = this.f;
            if (arrayList.size() <= 3) {
                iArrC = new int[arrayList.size()];
                for (int i4 = 0; i4 < arrayList.size(); i4++) {
                    iArrC[i4] = i4;
                }
            } else if (arrayList.contains(2) && arrayList.contains(3)) {
                int[] iArr2 = new int[3];
                iArr2[0] = arrayList.indexOf(2);
                if (arrayList.contains(0)) {
                    iArr2[1] = arrayList.indexOf(0);
                } else {
                    iArr2[1] = arrayList.indexOf(1);
                }
                iArr2[2] = arrayList.indexOf(3);
                iArrC = iArr2;
            } else if (arrayList.contains(4) && arrayList.contains(5)) {
                int[] iArr3 = new int[3];
                iArr3[0] = arrayList.indexOf(4);
                if (arrayList.contains(0)) {
                    iArr3[1] = arrayList.indexOf(0);
                } else {
                    iArr3[1] = arrayList.indexOf(1);
                }
                iArr3[2] = arrayList.indexOf(5);
                iArrC = iArr3;
            } else {
                ArrayList arrayList2 = new ArrayList();
                if (arrayList.contains(1)) {
                    arrayList2.add(Integer.valueOf(arrayList.indexOf(1)));
                } else {
                    arrayList2.add(Integer.valueOf(arrayList.indexOf(0)));
                    if (arrayList.contains(7)) {
                        arrayList2.add(Integer.valueOf(arrayList.indexOf(7)));
                    }
                }
                iArrC = CollectionUtil.c(arrayList2);
            }
            qqVarA.getClass();
            mo1 mo1Var = new mo1();
            mo1Var.e = mediaSessionCompat.getSessionToken();
            mo1Var.d = iArrC;
            NotificationCompat$Builder notificationCompat$Builder = qqVarA.a;
            if (notificationCompat$Builder.k != mo1Var) {
                notificationCompat$Builder.k = mo1Var;
                mo1Var.f(notificationCompat$Builder);
            }
        }
        mp1 mp1Var = this.d;
        NotificationCompat$Builder notificationCompat$Builder2 = ((np1) mp1Var).a;
        notificationCompat$Builder2.j = false;
        notificationCompat$Builder2.A.when = 0L;
        ((np1) mp1Var).p(this.e.g);
        ((np1) this.d).h(false);
        np1 np1Var = (np1) this.d;
        np1Var.a.r = true;
        np1Var.a.p = bVar.d();
        mp1 mp1Var2 = this.d;
        ((np1) mp1Var2).a.q = true;
        if (((4 & this.e.a) != 0) != false) {
            ((np1) mp1Var2).o(!r2.c);
            mp1 mp1Var3 = this.d;
            Intent action2 = bVar.c().setAction("org.chromium.components.browser_ui.media.ACTION_SWIPE");
            Context context2 = xz.a;
            int iD2 = q11.d(false) | 268435456 | q11.d(false);
            mp1Var3.a(new o8(PendingIntent.getService(context2, 0, action2, iD2), iD2, 0));
        }
        gd1 gd1Var3 = this.e;
        Intent intent = gd1Var3.l;
        if (intent != null) {
            this.d.c(o8.a(xz.a, gd1Var3.e, intent, q11.d(false) | 134217728));
        }
        ((np1) this.d).a.v = !this.e.f ? 1 : 0;
    }
}
