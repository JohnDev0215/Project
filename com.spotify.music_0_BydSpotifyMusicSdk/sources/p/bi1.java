package p;

import android.app.Application;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.media.MediaMetadata;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.BadParcelableException;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import androidx.media.session.MediaButtonReceiver;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class bi1 {
    public static int d;
    public final wh1 a;
    public final kk0 b;
    public final ArrayList c = new ArrayList();

    public bi1(Application application) {
        ComponentName componentName;
        if (application == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        if (TextUtils.isEmpty("spotify-music-auto")) {
            throw new IllegalArgumentException("tag must not be null or empty");
        }
        int i = MediaButtonReceiver.a;
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setPackage(application.getPackageName());
        List<ResolveInfo> listQueryBroadcastReceivers = application.getPackageManager().queryBroadcastReceivers(intent, 0);
        PendingIntent broadcast = null;
        if (listQueryBroadcastReceivers.size() == 1) {
            ActivityInfo activityInfo = listQueryBroadcastReceivers.get(0).activityInfo;
            componentName = new ComponentName(activityInfo.packageName, activityInfo.name);
        } else {
            listQueryBroadcastReceivers.size();
            componentName = null;
        }
        if (componentName != null) {
            Intent intent2 = new Intent("android.intent.action.MEDIA_BUTTON");
            intent2.setComponent(componentName);
            broadcast = PendingIntent.getBroadcast(application, 0, intent2, Build.VERSION.SDK_INT >= 31 ? 33554432 : 0);
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            this.a = new yh1(application);
        } else if (i2 >= 28) {
            this.a = new xh1(application);
        } else {
            this.a = new wh1(application);
        }
        this.a.g(new qh1(), new Handler(Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper()));
        this.a.h(broadcast);
        this.b = new kk0(application, this);
        if (d == 0) {
            d = (int) (TypedValue.applyDimension(1, 320.0f, application.getResources().getDisplayMetrics()) + 0.5f);
        }
    }

    public static void a(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(bi1.class.getClassLoader());
        }
    }

    public static PlaybackStateCompat b(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat) {
        int i;
        if (playbackStateCompat != null) {
            long j = playbackStateCompat.e;
            long j2 = -1;
            if (j != -1 && ((i = playbackStateCompat.c) == 3 || i == 4 || i == 5)) {
                if (playbackStateCompat.f2p > 0) {
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    long j3 = ((long) (playbackStateCompat.l * (jElapsedRealtime - r6))) + j;
                    if (mediaMetadataCompat != null) {
                        Bundle bundle = mediaMetadataCompat.c;
                        if (bundle.containsKey("android.media.metadata.DURATION")) {
                            j2 = bundle.getLong("android.media.metadata.DURATION", 0L);
                        }
                    }
                    long j4 = (j2 < 0 || j3 <= j2) ? j3 < 0 ? 0L : j3 : j2;
                    ArrayList arrayList = new ArrayList();
                    long j5 = playbackStateCompat.k;
                    long j6 = playbackStateCompat.m;
                    int i2 = playbackStateCompat.n;
                    CharSequence charSequence = playbackStateCompat.o;
                    ArrayList arrayList2 = playbackStateCompat.q;
                    if (arrayList2 != null) {
                        arrayList.addAll(arrayList2);
                    }
                    return new PlaybackStateCompat(playbackStateCompat.c, j4, j5, playbackStateCompat.l, j6, i2, charSequence, jElapsedRealtime, arrayList, playbackStateCompat.r, playbackStateCompat.s);
                }
            }
        }
        return playbackStateCompat;
    }

    public static Bundle f(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        a(bundle);
        try {
            bundle.isEmpty();
            return bundle;
        } catch (BadParcelableException unused) {
            Log.e("MediaSessionCompat", "Could not unparcel the data.");
            return null;
        }
    }

    public final void c() {
        wh1 wh1Var = this.a;
        wh1Var.f.kill();
        int i = Build.VERSION.SDK_INT;
        MediaSession mediaSession = wh1Var.a;
        if (i == 27) {
            try {
                Field declaredField = mediaSession.getClass().getDeclaredField("mCallback");
                declaredField.setAccessible(true);
                Handler handler = (Handler) declaredField.get(mediaSession);
                if (handler != null) {
                    handler.removeCallbacksAndMessages(null);
                }
            } catch (Exception unused) {
            }
        }
        mediaSession.setCallback(null);
        wh1Var.b.b.set(null);
        mediaSession.release();
    }

    public final void d(MediaMetadataCompat mediaMetadataCompat) {
        MediaMetadata mediaMetadata;
        wh1 wh1Var = this.a;
        wh1Var.i = mediaMetadataCompat;
        if (mediaMetadataCompat == null) {
            mediaMetadata = null;
        } else {
            if (mediaMetadataCompat.e == null) {
                Parcel parcelObtain = Parcel.obtain();
                mediaMetadataCompat.writeToParcel(parcelObtain, 0);
                parcelObtain.setDataPosition(0);
                mediaMetadataCompat.e = (MediaMetadata) MediaMetadata.CREATOR.createFromParcel(parcelObtain);
                parcelObtain.recycle();
            }
            mediaMetadata = mediaMetadataCompat.e;
        }
        wh1Var.a.setMetadata(mediaMetadata);
    }

    public final void e(PlaybackStateCompat playbackStateCompat) {
        wh1 wh1Var = this.a;
        wh1Var.g = playbackStateCompat;
        synchronized (wh1Var.d) {
            for (int iBeginBroadcast = wh1Var.f.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                try {
                    ((s21) wh1Var.f.getBroadcastItem(iBeginBroadcast)).c0(playbackStateCompat);
                } catch (RemoteException unused) {
                }
            }
            wh1Var.f.finishBroadcast();
        }
        MediaSession mediaSession = wh1Var.a;
        if (playbackStateCompat.t == null) {
            PlaybackState.Builder builderD = ut1.d();
            ut1.x(builderD, playbackStateCompat.c, playbackStateCompat.e, playbackStateCompat.l, playbackStateCompat.f2p);
            ut1.u(builderD, playbackStateCompat.k);
            ut1.s(builderD, playbackStateCompat.m);
            ut1.v(builderD, playbackStateCompat.o);
            for (PlaybackStateCompat.CustomAction customAction : playbackStateCompat.q) {
                PlaybackState.CustomAction customActionB = customAction.m;
                if (customActionB == null) {
                    PlaybackState.CustomAction.Builder builderE = ut1.e(customAction.c, customAction.e, customAction.k);
                    ut1.w(builderE, customAction.l);
                    customActionB = ut1.b(builderE);
                }
                ut1.a(builderD, customActionB);
            }
            ut1.t(builderD, playbackStateCompat.r);
            vt1.b(builderD, playbackStateCompat.s);
            playbackStateCompat.t = ut1.c(builderD);
        }
        mediaSession.setPlaybackState(playbackStateCompat.t);
    }
}
