package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.IMediaControllerCallback;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.view.KeyEvent;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.media.AudioAttributesCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class MediaControllerCompat {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f42a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ConcurrentHashMap<a, Boolean> f43b = new ConcurrentHashMap<>();

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(21)
    static class MediaControllerImplApi21 implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected final MediaController f44a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Object f45b = new Object();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @GuardedBy("mLock")
        private final List<a> f46c = new ArrayList();
        private HashMap<a, a> d = new HashMap<>();
        final MediaSessionCompat.Token e;

        /* JADX INFO: compiled from: caradapter-bug */
        private static class ExtraBinderRequestResultReceiver extends ResultReceiver {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private WeakReference<MediaControllerImplApi21> f47c;

            ExtraBinderRequestResultReceiver(MediaControllerImplApi21 mediaControllerImplApi21) {
                super(null);
                this.f47c = new WeakReference<>(mediaControllerImplApi21);
            }

            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int i, Bundle bundle) {
                MediaControllerImplApi21 mediaControllerImplApi21 = this.f47c.get();
                if (mediaControllerImplApi21 == null || bundle == null) {
                    return;
                }
                synchronized (mediaControllerImplApi21.f45b) {
                    mediaControllerImplApi21.e.e(IMediaSession.Stub.n0(androidx.core.app.e.a(bundle, "android.support.v4.media.session.EXTRA_BINDER")));
                    mediaControllerImplApi21.e.f(androidx.versionedparcelable.a.b(bundle, "android.support.v4.media.session.SESSION_TOKEN2"));
                    mediaControllerImplApi21.h();
                }
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        private static class a extends a.c {
            a(a aVar) {
                super(aVar);
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.IMediaControllerCallback
            public void d(List<MediaSessionCompat.QueueItem> list) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.IMediaControllerCallback
            public void d0(Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.IMediaControllerCallback
            public void h(CharSequence charSequence) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.IMediaControllerCallback
            public void l0(ParcelableVolumeInfo parcelableVolumeInfo) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.IMediaControllerCallback
            public void p() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.IMediaControllerCallback
            public void t(MediaMetadataCompat mediaMetadataCompat) {
                throw new AssertionError();
            }
        }

        MediaControllerImplApi21(Context context, MediaSessionCompat.Token token) {
            this.e = token;
            this.f44a = new MediaController(context, (MediaSession.Token) this.e.d());
            if (this.e.c() == null) {
                i();
            }
        }

        private void i() {
            j("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this));
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public MediaMetadataCompat a() {
            MediaMetadata metadata = this.f44a.getMetadata();
            if (metadata != null) {
                return MediaMetadataCompat.a(metadata);
            }
            return null;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public PlaybackStateCompat b() {
            if (this.e.c() != null) {
                try {
                    return this.e.c().b();
                } catch (RemoteException e) {
                    Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", e);
                }
            }
            PlaybackState playbackState = this.f44a.getPlaybackState();
            if (playbackState != null) {
                return PlaybackStateCompat.a(playbackState);
            }
            return null;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public e c() {
            return new f(this.f44a.getTransportControls());
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public PendingIntent d() {
            return this.f44a.getSessionActivity();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public final void e(a aVar) {
            this.f44a.unregisterCallback(aVar.f48a);
            synchronized (this.f45b) {
                if (this.e.c() != null) {
                    try {
                        a aVarRemove = this.d.remove(aVar);
                        if (aVarRemove != null) {
                            aVar.f50c = null;
                            this.e.c().r(aVarRemove);
                        }
                    } catch (RemoteException e) {
                        Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", e);
                    }
                }
                this.f46c.remove(aVar);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public boolean f(KeyEvent keyEvent) {
            return this.f44a.dispatchMediaButtonEvent(keyEvent);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public final void g(a aVar, Handler handler) {
            this.f44a.registerCallback(aVar.f48a, handler);
            synchronized (this.f45b) {
                if (this.e.c() != null) {
                    a aVar2 = new a(aVar);
                    this.d.put(aVar, aVar2);
                    aVar.f50c = aVar2;
                    try {
                        this.e.c().m(aVar2);
                        aVar.m(13, null, null);
                    } catch (RemoteException e) {
                        Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                    }
                } else {
                    aVar.f50c = null;
                    this.f46c.add(aVar);
                }
            }
        }

        @GuardedBy("mLock")
        void h() {
            if (this.e.c() == null) {
                return;
            }
            for (a aVar : this.f46c) {
                a aVar2 = new a(aVar);
                this.d.put(aVar, aVar2);
                aVar.f50c = aVar2;
                try {
                    this.e.c().m(aVar2);
                    aVar.m(13, null, null);
                } catch (RemoteException e) {
                    Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                }
            }
            this.f46c.clear();
        }

        public void j(String str, Bundle bundle, ResultReceiver resultReceiver) {
            this.f44a.sendCommand(str, bundle, resultReceiver);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class a implements IBinder.DeathRecipient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final MediaController.Callback f48a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        b f49b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        IMediaControllerCallback f50c;

        /* JADX INFO: renamed from: android.support.v4.media.session.MediaControllerCompat$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: caradapter-bug */
        @RequiresApi(21)
        private static class C0002a extends MediaController.Callback {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final WeakReference<a> f51a;

            C0002a(a aVar) {
                this.f51a = new WeakReference<>(aVar);
            }

            @Override // android.media.session.MediaController.Callback
            public void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
                a aVar = this.f51a.get();
                if (aVar != null) {
                    aVar.a(new d(playbackInfo.getPlaybackType(), AudioAttributesCompat.c(playbackInfo.getAudioAttributes()), playbackInfo.getVolumeControl(), playbackInfo.getMaxVolume(), playbackInfo.getCurrentVolume()));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onExtrasChanged(Bundle bundle) {
                MediaSessionCompat.a(bundle);
                a aVar = this.f51a.get();
                if (aVar != null) {
                    aVar.c(bundle);
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onMetadataChanged(MediaMetadata mediaMetadata) {
                a aVar = this.f51a.get();
                if (aVar != null) {
                    aVar.d(MediaMetadataCompat.a(mediaMetadata));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onPlaybackStateChanged(PlaybackState playbackState) {
                a aVar = this.f51a.get();
                if (aVar == null || aVar.f50c != null) {
                    return;
                }
                aVar.e(PlaybackStateCompat.a(playbackState));
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueChanged(List<MediaSession.QueueItem> list) {
                a aVar = this.f51a.get();
                if (aVar != null) {
                    aVar.f(MediaSessionCompat.QueueItem.b(list));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueTitleChanged(CharSequence charSequence) {
                a aVar = this.f51a.get();
                if (aVar != null) {
                    aVar.g(charSequence);
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionDestroyed() {
                a aVar = this.f51a.get();
                if (aVar != null) {
                    aVar.i();
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionEvent(String str, Bundle bundle) {
                MediaSessionCompat.a(bundle);
                a aVar = this.f51a.get();
                if (aVar != null) {
                    if (aVar.f50c == null || Build.VERSION.SDK_INT >= 23) {
                        aVar.j(str, bundle);
                    }
                }
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        private class b extends Handler {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            boolean f52a;

            b(Looper looper) {
                super(looper);
                this.f52a = false;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (this.f52a) {
                    switch (message.what) {
                        case 1:
                            Bundle data = message.getData();
                            MediaSessionCompat.a(data);
                            a.this.j((String) message.obj, data);
                            break;
                        case 2:
                            a.this.e((PlaybackStateCompat) message.obj);
                            break;
                        case 3:
                            a.this.d((MediaMetadataCompat) message.obj);
                            break;
                        case 4:
                            a.this.a((d) message.obj);
                            break;
                        case 5:
                            a.this.f((List) message.obj);
                            break;
                        case 6:
                            a.this.g((CharSequence) message.obj);
                            break;
                        case 7:
                            Bundle bundle = (Bundle) message.obj;
                            MediaSessionCompat.a(bundle);
                            a.this.c(bundle);
                            break;
                        case 8:
                            a.this.i();
                            break;
                        case 9:
                            a.this.h(((Integer) message.obj).intValue());
                            break;
                        case 11:
                            a.this.b(((Boolean) message.obj).booleanValue());
                            break;
                        case 12:
                            a.this.l(((Integer) message.obj).intValue());
                            break;
                        case 13:
                            a.this.k();
                            break;
                    }
                }
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        private static class c extends IMediaControllerCallback.Stub {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final WeakReference<a> f54a;

            c(a aVar) {
                this.f54a = new WeakReference<>(aVar);
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void I(int i) {
                a aVar = this.f54a.get();
                if (aVar != null) {
                    aVar.m(9, Integer.valueOf(i), null);
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void T(boolean z) {
                a aVar = this.f54a.get();
                if (aVar != null) {
                    aVar.m(11, Boolean.valueOf(z), null);
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void b0(int i) {
                a aVar = this.f54a.get();
                if (aVar != null) {
                    aVar.m(12, Integer.valueOf(i), null);
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void c0() {
                a aVar = this.f54a.get();
                if (aVar != null) {
                    aVar.m(13, null, null);
                }
            }

            public void d(List<MediaSessionCompat.QueueItem> list) {
                a aVar = this.f54a.get();
                if (aVar != null) {
                    aVar.m(5, list, null);
                }
            }

            public void d0(Bundle bundle) {
                a aVar = this.f54a.get();
                if (aVar != null) {
                    aVar.m(7, bundle, null);
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void e0(PlaybackStateCompat playbackStateCompat) {
                a aVar = this.f54a.get();
                if (aVar != null) {
                    aVar.m(2, playbackStateCompat, null);
                }
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void f(boolean z) {
            }

            @Override // android.support.v4.media.session.IMediaControllerCallback
            public void f0(String str, Bundle bundle) {
                a aVar = this.f54a.get();
                if (aVar != null) {
                    aVar.m(1, str, bundle);
                }
            }

            public void h(CharSequence charSequence) {
                a aVar = this.f54a.get();
                if (aVar != null) {
                    aVar.m(6, charSequence, null);
                }
            }

            public void l0(ParcelableVolumeInfo parcelableVolumeInfo) {
                a aVar = this.f54a.get();
                if (aVar != null) {
                    aVar.m(4, parcelableVolumeInfo != null ? new d(parcelableVolumeInfo.f63c, parcelableVolumeInfo.d, parcelableVolumeInfo.e, parcelableVolumeInfo.f, parcelableVolumeInfo.g) : null, null);
                }
            }

            public void p() {
                a aVar = this.f54a.get();
                if (aVar != null) {
                    aVar.m(8, null, null);
                }
            }

            public void t(MediaMetadataCompat mediaMetadataCompat) {
                a aVar = this.f54a.get();
                if (aVar != null) {
                    aVar.m(3, mediaMetadataCompat, null);
                }
            }
        }

        public a() {
            if (Build.VERSION.SDK_INT >= 21) {
                this.f48a = new C0002a(this);
            } else {
                this.f48a = null;
                this.f50c = new c(this);
            }
        }

        public void a(d dVar) {
        }

        public void b(boolean z) {
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            m(8, null, null);
        }

        public void c(Bundle bundle) {
        }

        public void d(MediaMetadataCompat mediaMetadataCompat) {
        }

        public void e(PlaybackStateCompat playbackStateCompat) {
        }

        public void f(List<MediaSessionCompat.QueueItem> list) {
        }

        public void g(CharSequence charSequence) {
        }

        public void h(int i) {
        }

        public void i() {
        }

        public void j(String str, Bundle bundle) {
        }

        public void k() {
        }

        public void l(int i) {
        }

        void m(int i, Object obj, Bundle bundle) {
            b bVar = this.f49b;
            if (bVar != null) {
                Message messageObtainMessage = bVar.obtainMessage(i, obj);
                messageObtainMessage.setData(bundle);
                messageObtainMessage.sendToTarget();
            }
        }

        void n(Handler handler) {
            if (handler != null) {
                b bVar = new b(handler.getLooper());
                this.f49b = bVar;
                bVar.f52a = true;
            } else {
                b bVar2 = this.f49b;
                if (bVar2 != null) {
                    bVar2.f52a = false;
                    bVar2.removeCallbacksAndMessages(null);
                    this.f49b = null;
                }
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    interface b {
        MediaMetadataCompat a();

        PlaybackStateCompat b();

        e c();

        PendingIntent d();

        void e(a aVar);

        boolean f(KeyEvent keyEvent);

        void g(a aVar, Handler handler);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class c implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IMediaSession f55a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private e f56b;

        c(MediaSessionCompat.Token token) {
            this.f55a = IMediaSession.Stub.n0((IBinder) token.d());
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public MediaMetadataCompat a() {
            try {
                return this.f55a.a();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getMetadata.", e);
                return null;
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public PlaybackStateCompat b() {
            try {
                return this.f55a.b();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", e);
                return null;
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public e c() {
            if (this.f56b == null) {
                this.f56b = new g(this.f55a);
            }
            return this.f56b;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public PendingIntent d() {
            try {
                return this.f55a.P();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getSessionActivity.", e);
                return null;
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public void e(a aVar) {
            if (aVar == null) {
                throw new IllegalArgumentException("callback may not be null.");
            }
            try {
                this.f55a.r(aVar.f50c);
                this.f55a.asBinder().unlinkToDeath(aVar, 0);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", e);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public boolean f(KeyEvent keyEvent) {
            if (keyEvent == null) {
                throw new IllegalArgumentException("event may not be null.");
            }
            try {
                this.f55a.m0(keyEvent);
                return false;
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in dispatchMediaButtonEvent.", e);
                return false;
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public void g(a aVar, Handler handler) {
            if (aVar == null) {
                throw new IllegalArgumentException("callback may not be null.");
            }
            try {
                this.f55a.asBinder().linkToDeath(aVar, 0);
                this.f55a.m(aVar.f50c);
                aVar.m(13, null, null);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                aVar.m(8, null, null);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static final class d {
        d(int i, int i2, int i3, int i4, int i5) {
            AudioAttributesCompat.a aVar = new AudioAttributesCompat.a();
            aVar.b(i2);
            this(i, aVar.a(), i3, i4, i5);
        }

        d(int i, @NonNull AudioAttributesCompat audioAttributesCompat, int i2, int i3, int i4) {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class e {
        e() {
        }

        public abstract void a();

        public abstract void b();

        public abstract void c();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(21)
    static class f extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected final MediaController.TransportControls f57a;

        f(MediaController.TransportControls transportControls) {
            this.f57a = transportControls;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.e
        public void a() {
            this.f57a.pause();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.e
        public void b() {
            this.f57a.play();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.e
        public void c() {
            this.f57a.stop();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class g extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IMediaSession f58a;

        public g(IMediaSession iMediaSession) {
            this.f58a = iMediaSession;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.e
        public void a() {
            try {
                this.f58a.l();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in pause.", e);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.e
        public void b() {
            try {
                this.f58a.g0();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in play.", e);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.e
        public void c() {
            try {
                this.f58a.stop();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in stop.", e);
            }
        }
    }

    public MediaControllerCompat(Context context, @NonNull MediaSessionCompat.Token token) {
        if (token == null) {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.f42a = new MediaControllerImplApi21(context, token);
        } else {
            this.f42a = new c(token);
        }
    }

    public boolean a(KeyEvent keyEvent) {
        if (keyEvent != null) {
            return this.f42a.f(keyEvent);
        }
        throw new IllegalArgumentException("KeyEvent may not be null");
    }

    public MediaMetadataCompat b() {
        return this.f42a.a();
    }

    public PlaybackStateCompat c() {
        return this.f42a.b();
    }

    public PendingIntent d() {
        return this.f42a.d();
    }

    public e e() {
        return this.f42a.c();
    }

    public void f(@NonNull a aVar) {
        g(aVar, null);
    }

    public void g(@NonNull a aVar, Handler handler) {
        if (aVar == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        if (this.f43b.putIfAbsent(aVar, Boolean.TRUE) != null) {
            Log.w("MediaControllerCompat", "the callback has already been registered");
            return;
        }
        if (handler == null) {
            handler = new Handler();
        }
        aVar.n(handler);
        this.f42a.g(aVar, handler);
    }

    public void h(@NonNull a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        if (this.f43b.remove(aVar) == null) {
            Log.w("MediaControllerCompat", "the callback has never been registered");
            return;
        }
        try {
            this.f42a.e(aVar);
        } finally {
            aVar.n(null);
        }
    }
}
