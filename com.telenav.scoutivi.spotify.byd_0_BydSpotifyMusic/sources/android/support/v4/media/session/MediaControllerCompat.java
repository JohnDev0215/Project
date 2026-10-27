package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.a;
import android.support.v4.media.session.b;
import android.util.Log;
import androidx.media.AudioAttributesCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class MediaControllerCompat {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f229a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final MediaSessionCompat.Token f230b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @SuppressLint({"BanConcurrentHashMap"})
    private final ConcurrentHashMap<a, Boolean> f231c = new ConcurrentHashMap<>();

    static class MediaControllerImplApi21 implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected final MediaController f232a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Object f233b = new Object();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final List<a> f234c = new ArrayList();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private HashMap<a, a> f235d = new HashMap<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final MediaSessionCompat.Token f236e;

        private static class ExtraBinderRequestResultReceiver extends ResultReceiver {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            private WeakReference<MediaControllerImplApi21> f237e;

            ExtraBinderRequestResultReceiver(MediaControllerImplApi21 mediaControllerImplApi21) {
                super(null);
                this.f237e = new WeakReference<>(mediaControllerImplApi21);
            }

            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int i7, Bundle bundle) {
                MediaControllerImplApi21 mediaControllerImplApi21 = this.f237e.get();
                if (mediaControllerImplApi21 == null || bundle == null) {
                    return;
                }
                synchronized (mediaControllerImplApi21.f233b) {
                    mediaControllerImplApi21.f236e.k(b.a.T(w.e.a(bundle, "android.support.v4.media.session.EXTRA_BINDER")));
                    mediaControllerImplApi21.f236e.m(g1.a.b(bundle, "android.support.v4.media.session.SESSION_TOKEN2"));
                    mediaControllerImplApi21.m();
                }
            }
        }

        private static class a extends a.c {
            a(a aVar) {
                super(aVar);
            }

            @Override // android.support.v4.media.session.a
            public void J(Bundle bundle) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a
            public void S(ParcelableVolumeInfo parcelableVolumeInfo) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a
            public void e(List<MediaSessionCompat.QueueItem> list) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a
            public void k(CharSequence charSequence) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a
            public void o() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.a
            public void q(MediaMetadataCompat mediaMetadataCompat) {
                throw new AssertionError();
            }
        }

        MediaControllerImplApi21(Context context, MediaSessionCompat.Token token) {
            this.f236e = token;
            this.f232a = new MediaController(context, (MediaSession.Token) token.j());
            if (token.h() == null) {
                n();
            }
        }

        private void n() {
            o("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this));
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public int a() {
            if (this.f236e.h() == null) {
                return -1;
            }
            try {
                return this.f236e.h().a();
            } catch (RemoteException e7) {
                Log.e("MediaControllerCompat", "Dead object in getRepeatMode.", e7);
                return -1;
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public PlaybackStateCompat b() {
            if (this.f236e.h() != null) {
                try {
                    return this.f236e.h().b();
                } catch (RemoteException e7) {
                    Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", e7);
                }
            }
            PlaybackState playbackState = this.f232a.getPlaybackState();
            if (playbackState != null) {
                return PlaybackStateCompat.b(playbackState);
            }
            return null;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public int c() {
            if (this.f236e.h() == null) {
                return -1;
            }
            try {
                return this.f236e.h().c();
            } catch (RemoteException e7) {
                Log.e("MediaControllerCompat", "Dead object in getShuffleMode.", e7);
                return -1;
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public boolean d() {
            if (this.f236e.h() == null) {
                return false;
            }
            try {
                return this.f236e.h().d();
            } catch (RemoteException e7) {
                Log.e("MediaControllerCompat", "Dead object in isCaptioningEnabled.", e7);
                return false;
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public d e() {
            return new e(this.f232a.getTransportControls());
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public MediaMetadataCompat f() {
            MediaMetadata metadata = this.f232a.getMetadata();
            if (metadata != null) {
                return MediaMetadataCompat.b(metadata);
            }
            return null;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public final void g(a aVar) {
            this.f232a.unregisterCallback(aVar.f238a);
            synchronized (this.f233b) {
                if (this.f236e.h() != null) {
                    try {
                        a aVarRemove = this.f235d.remove(aVar);
                        if (aVarRemove != null) {
                            aVar.f240c = null;
                            this.f236e.h().p(aVarRemove);
                        }
                    } catch (RemoteException e7) {
                        Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", e7);
                    }
                }
                this.f234c.remove(aVar);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public String getPackageName() {
            return this.f232a.getPackageName();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public long h() {
            return this.f232a.getFlags();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public void i(MediaDescriptionCompat mediaDescriptionCompat) {
            if ((h() & 4) == 0) {
                throw new UnsupportedOperationException("This session doesn't support queue management operations");
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", mediaDescriptionCompat);
            o("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM", bundle, null);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public void j(MediaDescriptionCompat mediaDescriptionCompat) {
            if ((h() & 4) == 0) {
                throw new UnsupportedOperationException("This session doesn't support queue management operations");
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", mediaDescriptionCompat);
            o("android.support.v4.media.session.command.ADD_QUEUE_ITEM", bundle, null);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public final void k(a aVar, Handler handler) {
            this.f232a.registerCallback(aVar.f238a, handler);
            synchronized (this.f233b) {
                if (this.f236e.h() != null) {
                    a aVar2 = new a(aVar);
                    this.f235d.put(aVar, aVar2);
                    aVar.f240c = aVar2;
                    try {
                        this.f236e.h().m(aVar2);
                        aVar.m(13, null, null);
                    } catch (RemoteException e7) {
                        Log.e("MediaControllerCompat", "Dead object in registerCallback.", e7);
                    }
                } else {
                    aVar.f240c = null;
                    this.f234c.add(aVar);
                }
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public List<MediaSessionCompat.QueueItem> l() {
            List<MediaSession.QueueItem> queue = this.f232a.getQueue();
            if (queue != null) {
                return MediaSessionCompat.QueueItem.c(queue);
            }
            return null;
        }

        void m() {
            if (this.f236e.h() == null) {
                return;
            }
            for (a aVar : this.f234c) {
                a aVar2 = new a(aVar);
                this.f235d.put(aVar, aVar2);
                aVar.f240c = aVar2;
                try {
                    this.f236e.h().m(aVar2);
                    aVar.m(13, null, null);
                } catch (RemoteException e7) {
                    Log.e("MediaControllerCompat", "Dead object in registerCallback.", e7);
                }
            }
            this.f234c.clear();
        }

        public void o(String str, Bundle bundle, ResultReceiver resultReceiver) {
            this.f232a.sendCommand(str, bundle, resultReceiver);
        }
    }

    public static abstract class a implements IBinder.DeathRecipient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final MediaController.Callback f238a = new C0006a(this);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        b f239b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        android.support.v4.media.session.a f240c;

        /* JADX INFO: renamed from: android.support.v4.media.session.MediaControllerCompat$a$a, reason: collision with other inner class name */
        private static class C0006a extends MediaController.Callback {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final WeakReference<a> f241a;

            C0006a(a aVar) {
                this.f241a = new WeakReference<>(aVar);
            }

            @Override // android.media.session.MediaController.Callback
            public void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
                a aVar = this.f241a.get();
                if (aVar != null) {
                    aVar.a(new c(playbackInfo.getPlaybackType(), AudioAttributesCompat.c(playbackInfo.getAudioAttributes()), playbackInfo.getVolumeControl(), playbackInfo.getMaxVolume(), playbackInfo.getCurrentVolume()));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onExtrasChanged(Bundle bundle) {
                MediaSessionCompat.a(bundle);
                a aVar = this.f241a.get();
                if (aVar != null) {
                    aVar.c(bundle);
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onMetadataChanged(MediaMetadata mediaMetadata) {
                a aVar = this.f241a.get();
                if (aVar != null) {
                    aVar.d(MediaMetadataCompat.b(mediaMetadata));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onPlaybackStateChanged(PlaybackState playbackState) {
                a aVar = this.f241a.get();
                if (aVar == null || aVar.f240c != null) {
                    return;
                }
                aVar.e(PlaybackStateCompat.b(playbackState));
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueChanged(List<MediaSession.QueueItem> list) {
                a aVar = this.f241a.get();
                if (aVar != null) {
                    aVar.f(MediaSessionCompat.QueueItem.c(list));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueTitleChanged(CharSequence charSequence) {
                a aVar = this.f241a.get();
                if (aVar != null) {
                    aVar.g(charSequence);
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionDestroyed() {
                a aVar = this.f241a.get();
                if (aVar != null) {
                    aVar.i();
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionEvent(String str, Bundle bundle) {
                MediaSessionCompat.a(bundle);
                a aVar = this.f241a.get();
                if (aVar != null) {
                    android.support.v4.media.session.a aVar2 = aVar.f240c;
                    aVar.j(str, bundle);
                }
            }
        }

        private class b extends Handler {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            boolean f242a;

            b(Looper looper) {
                super(looper);
                this.f242a = false;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (this.f242a) {
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
                            a.this.a((c) message.obj);
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

        private static class c extends a.AbstractBinderC0007a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final WeakReference<a> f244a;

            c(a aVar) {
                this.f244a = new WeakReference<>(aVar);
            }

            @Override // android.support.v4.media.session.a
            public void A(boolean z6) {
                a aVar = this.f244a.get();
                if (aVar != null) {
                    aVar.m(11, Boolean.valueOf(z6), null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void H(int i7) {
                a aVar = this.f244a.get();
                if (aVar != null) {
                    aVar.m(12, Integer.valueOf(i7), null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void I() {
                a aVar = this.f244a.get();
                if (aVar != null) {
                    aVar.m(13, null, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void K(PlaybackStateCompat playbackStateCompat) {
                a aVar = this.f244a.get();
                if (aVar != null) {
                    aVar.m(2, playbackStateCompat, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void L(String str, Bundle bundle) {
                a aVar = this.f244a.get();
                if (aVar != null) {
                    aVar.m(1, str, bundle);
                }
            }

            @Override // android.support.v4.media.session.a
            public void g(boolean z6) {
            }

            @Override // android.support.v4.media.session.a
            public void v(int i7) {
                a aVar = this.f244a.get();
                if (aVar != null) {
                    aVar.m(9, Integer.valueOf(i7), null);
                }
            }
        }

        public void a(c cVar) {
        }

        public void b(boolean z6) {
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

        public void h(int i7) {
        }

        public void i() {
        }

        public void j(String str, Bundle bundle) {
        }

        public void k() {
        }

        public void l(int i7) {
        }

        void m(int i7, Object obj, Bundle bundle) {
            b bVar = this.f239b;
            if (bVar != null) {
                Message messageObtainMessage = bVar.obtainMessage(i7, obj);
                messageObtainMessage.setData(bundle);
                messageObtainMessage.sendToTarget();
            }
        }

        void n(Handler handler) {
            if (handler != null) {
                b bVar = new b(handler.getLooper());
                this.f239b = bVar;
                bVar.f242a = true;
            } else {
                b bVar2 = this.f239b;
                if (bVar2 != null) {
                    bVar2.f242a = false;
                    bVar2.removeCallbacksAndMessages(null);
                    this.f239b = null;
                }
            }
        }
    }

    interface b {
        int a();

        PlaybackStateCompat b();

        int c();

        boolean d();

        d e();

        MediaMetadataCompat f();

        void g(a aVar);

        String getPackageName();

        long h();

        void i(MediaDescriptionCompat mediaDescriptionCompat);

        void j(MediaDescriptionCompat mediaDescriptionCompat);

        void k(a aVar, Handler handler);

        List<MediaSessionCompat.QueueItem> l();
    }

    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f245a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final AudioAttributesCompat f246b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f247c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f248d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int f249e;

        c(int i7, AudioAttributesCompat audioAttributesCompat, int i8, int i9, int i10) {
            this.f245a = i7;
            this.f246b = audioAttributesCompat;
            this.f247c = i8;
            this.f248d = i9;
            this.f249e = i10;
        }
    }

    public static abstract class d {
        d() {
        }

        public abstract void a();

        public abstract void b();

        public abstract void c(String str, Bundle bundle);

        public abstract void d(String str, Bundle bundle);

        public abstract void e(long j7);

        public abstract void f(String str, Bundle bundle);

        public abstract void g(int i7);

        public abstract void h(int i7);

        public abstract void i();

        public abstract void j();

        public abstract void k(long j7);

        public abstract void l();
    }

    static class e extends d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected final MediaController.TransportControls f250a;

        e(MediaController.TransportControls transportControls) {
            this.f250a = transportControls;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.d
        public void a() {
            this.f250a.pause();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.d
        public void b() {
            this.f250a.play();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.d
        public void c(String str, Bundle bundle) {
            this.f250a.playFromMediaId(str, bundle);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.d
        public void d(String str, Bundle bundle) {
            this.f250a.playFromSearch(str, bundle);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.d
        public void e(long j7) {
            this.f250a.seekTo(j7);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.d
        public void f(String str, Bundle bundle) {
            MediaControllerCompat.o(str, bundle);
            this.f250a.sendCustomAction(str, bundle);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.d
        public void g(int i7) {
            Bundle bundle = new Bundle();
            bundle.putInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE", i7);
            f("android.support.v4.media.session.action.SET_REPEAT_MODE", bundle);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.d
        public void h(int i7) {
            Bundle bundle = new Bundle();
            bundle.putInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE", i7);
            f("android.support.v4.media.session.action.SET_SHUFFLE_MODE", bundle);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.d
        public void i() {
            this.f250a.skipToNext();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.d
        public void j() {
            this.f250a.skipToPrevious();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.d
        public void k(long j7) {
            this.f250a.skipToQueueItem(j7);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.d
        public void l() {
            this.f250a.stop();
        }
    }

    public MediaControllerCompat(Context context, MediaSessionCompat.Token token) {
        if (token == null) {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
        this.f230b = token;
        this.f229a = new MediaControllerImplApi21(context, token);
    }

    static void o(String str, Bundle bundle) {
        if (str == null) {
            return;
        }
        if (str.equals("android.support.v4.media.session.action.FOLLOW") || str.equals("android.support.v4.media.session.action.UNFOLLOW")) {
            if (bundle == null || !bundle.containsKey("android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE")) {
                throw new IllegalArgumentException("An extra field android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE is required for this action " + str + ".");
            }
        }
    }

    public void a(MediaDescriptionCompat mediaDescriptionCompat) {
        this.f229a.j(mediaDescriptionCompat);
    }

    public long b() {
        return this.f229a.h();
    }

    public MediaMetadataCompat c() {
        return this.f229a.f();
    }

    public String d() {
        return this.f229a.getPackageName();
    }

    public PlaybackStateCompat e() {
        return this.f229a.b();
    }

    public List<MediaSessionCompat.QueueItem> f() {
        return this.f229a.l();
    }

    public int g() {
        return this.f229a.a();
    }

    public int h() {
        return this.f229a.c();
    }

    public d i() {
        return this.f229a.e();
    }

    public boolean j() {
        return this.f229a.d();
    }

    public void k(a aVar) {
        l(aVar, null);
    }

    public void l(a aVar, Handler handler) {
        if (aVar == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        if (this.f231c.putIfAbsent(aVar, Boolean.TRUE) != null) {
            Log.w("MediaControllerCompat", "the callback has already been registered");
            return;
        }
        if (handler == null) {
            handler = new Handler();
        }
        aVar.n(handler);
        this.f229a.k(aVar, handler);
    }

    public void m(MediaDescriptionCompat mediaDescriptionCompat) {
        this.f229a.i(mediaDescriptionCompat);
    }

    public void n(a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        if (this.f231c.remove(aVar) == null) {
            Log.w("MediaControllerCompat", "the callback has never been registered");
            return;
        }
        try {
            this.f229a.g(aVar);
        } finally {
            aVar.n(null);
        }
    }
}
