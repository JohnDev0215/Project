package android.support.v4.media.session;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.a;
import android.support.v4.media.session.b;
import android.support.v4.media.session.c;
import android.util.Log;
import android.view.KeyEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class MediaControllerCompat {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f107a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final MediaSessionCompat.Token f108b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final HashSet<a> f109c = new HashSet<>();

    static class MediaControllerImplApi21 implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected final Object f110a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Object f111b = new Object();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final List<a> f112c = new ArrayList();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private HashMap<a, a> f113d = new HashMap<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final MediaSessionCompat.Token f114e;

        private static class ExtraBinderRequestResultReceiver extends ResultReceiver {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private WeakReference<MediaControllerImplApi21> f115b;

            ExtraBinderRequestResultReceiver(MediaControllerImplApi21 mediaControllerImplApi21) {
                super(null);
                this.f115b = new WeakReference<>(mediaControllerImplApi21);
            }

            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int i, Bundle bundle) {
                MediaControllerImplApi21 mediaControllerImplApi21 = this.f115b.get();
                if (mediaControllerImplApi21 == null || bundle == null) {
                    return;
                }
                synchronized (mediaControllerImplApi21.f111b) {
                    mediaControllerImplApi21.f114e.j(b.a.c0(androidx.core.app.d.a(bundle, "android.support.v4.media.session.EXTRA_BINDER")));
                    mediaControllerImplApi21.f114e.k(bundle.getBundle("android.support.v4.media.session.SESSION_TOKEN2_BUNDLE"));
                    mediaControllerImplApi21.f();
                }
            }
        }

        private static class a extends a.c {
            a(a aVar) {
                super(aVar);
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.a
            public void a(List<MediaSessionCompat.QueueItem> list) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.a
            public void a0(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.a
            public void b(CharSequence charSequence) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.a
            public void d() throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.a
            public void f(Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, android.support.v4.media.session.a
            public void r(MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
                throw new AssertionError();
            }
        }

        public MediaControllerImplApi21(Context context, MediaSessionCompat.Token token) throws RemoteException {
            this.f114e = token;
            Object objC = android.support.v4.media.session.c.c(context, token.i());
            this.f110a = objC;
            if (objC == null) {
                throw new RemoteException();
            }
            if (token.f() == null) {
                g();
            }
        }

        private void g() {
            h("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this));
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public g a() {
            Object objF = android.support.v4.media.session.c.f(this.f110a);
            if (objF != null) {
                return new h(objF);
            }
            return null;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public boolean b(KeyEvent keyEvent) {
            return android.support.v4.media.session.c.b(this.f110a, keyEvent);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public MediaMetadataCompat c() {
            Object objD = android.support.v4.media.session.c.d(this.f110a);
            if (objD != null) {
                return MediaMetadataCompat.a(objD);
            }
            return null;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public final void d(a aVar, Handler handler) {
            android.support.v4.media.session.c.g(this.f110a, aVar.f116a, handler);
            synchronized (this.f111b) {
                if (this.f114e.f() != null) {
                    a aVar2 = new a(aVar);
                    this.f113d.put(aVar, aVar2);
                    aVar.f118c = aVar2;
                    try {
                        this.f114e.f().p(aVar2);
                        aVar.m(13, null, null);
                    } catch (RemoteException e2) {
                        Log.e("MediaControllerCompat", "Dead object in registerCallback.", e2);
                    }
                } else {
                    aVar.f118c = null;
                    this.f112c.add(aVar);
                }
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public PlaybackStateCompat e() {
            if (this.f114e.f() != null) {
                try {
                    return this.f114e.f().e();
                } catch (RemoteException e2) {
                    Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", e2);
                }
            }
            Object objE = android.support.v4.media.session.c.e(this.f110a);
            if (objE != null) {
                return PlaybackStateCompat.a(objE);
            }
            return null;
        }

        void f() {
            if (this.f114e.f() == null) {
                return;
            }
            for (a aVar : this.f112c) {
                a aVar2 = new a(aVar);
                this.f113d.put(aVar, aVar2);
                aVar.f118c = aVar2;
                try {
                    this.f114e.f().p(aVar2);
                    aVar.m(13, null, null);
                } catch (RemoteException e2) {
                    Log.e("MediaControllerCompat", "Dead object in registerCallback.", e2);
                }
            }
            this.f112c.clear();
        }

        public void h(String str, Bundle bundle, ResultReceiver resultReceiver) {
            android.support.v4.media.session.c.h(this.f110a, str, bundle, resultReceiver);
        }
    }

    public static abstract class a implements IBinder.DeathRecipient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Object f116a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        HandlerC0005a f117b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        android.support.v4.media.session.a f118c;

        /* JADX INFO: renamed from: android.support.v4.media.session.MediaControllerCompat$a$a, reason: collision with other inner class name */
        private class HandlerC0005a extends Handler {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            boolean f119a;

            HandlerC0005a(Looper looper) {
                super(looper);
                this.f119a = false;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (this.f119a) {
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
                            a.this.a((f) message.obj);
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

        private static class b implements c.a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final WeakReference<a> f121a;

            b(a aVar) {
                this.f121a = new WeakReference<>(aVar);
            }

            @Override // android.support.v4.media.session.c.a
            public void a(List<?> list) {
                a aVar = this.f121a.get();
                if (aVar != null) {
                    aVar.f(MediaSessionCompat.QueueItem.c(list));
                }
            }

            @Override // android.support.v4.media.session.c.a
            public void b(CharSequence charSequence) {
                a aVar = this.f121a.get();
                if (aVar != null) {
                    aVar.g(charSequence);
                }
            }

            @Override // android.support.v4.media.session.c.a
            public void c(Object obj) {
                a aVar = this.f121a.get();
                if (aVar == null || aVar.f118c != null) {
                    return;
                }
                aVar.e(PlaybackStateCompat.a(obj));
            }

            @Override // android.support.v4.media.session.c.a
            public void d() {
                a aVar = this.f121a.get();
                if (aVar != null) {
                    aVar.i();
                }
            }

            @Override // android.support.v4.media.session.c.a
            public void e(Object obj) {
                a aVar = this.f121a.get();
                if (aVar != null) {
                    aVar.d(MediaMetadataCompat.a(obj));
                }
            }

            @Override // android.support.v4.media.session.c.a
            public void f(Bundle bundle) {
                a aVar = this.f121a.get();
                if (aVar != null) {
                    aVar.c(bundle);
                }
            }

            @Override // android.support.v4.media.session.c.a
            public void g(int i, int i2, int i3, int i4, int i5) {
                a aVar = this.f121a.get();
                if (aVar != null) {
                    aVar.a(new f(i, i2, i3, i4, i5));
                }
            }

            @Override // android.support.v4.media.session.c.a
            public void h(String str, Bundle bundle) {
                a aVar = this.f121a.get();
                if (aVar != null) {
                    if (aVar.f118c == null || Build.VERSION.SDK_INT >= 23) {
                        aVar.j(str, bundle);
                    }
                }
            }
        }

        private static class c extends a.AbstractBinderC0006a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final WeakReference<a> f122a;

            c(a aVar) {
                this.f122a = new WeakReference<>(aVar);
            }

            @Override // android.support.v4.media.session.a
            public void F(boolean z) throws RemoteException {
                a aVar = this.f122a.get();
                if (aVar != null) {
                    aVar.m(11, Boolean.valueOf(z), null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void M(int i) throws RemoteException {
                a aVar = this.f122a.get();
                if (aVar != null) {
                    aVar.m(12, Integer.valueOf(i), null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void N() throws RemoteException {
                a aVar = this.f122a.get();
                if (aVar != null) {
                    aVar.m(13, null, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void S(PlaybackStateCompat playbackStateCompat) throws RemoteException {
                a aVar = this.f122a.get();
                if (aVar != null) {
                    aVar.m(2, playbackStateCompat, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void U(String str, Bundle bundle) throws RemoteException {
                a aVar = this.f122a.get();
                if (aVar != null) {
                    aVar.m(1, str, bundle);
                }
            }

            public void a(List<MediaSessionCompat.QueueItem> list) throws RemoteException {
                a aVar = this.f122a.get();
                if (aVar != null) {
                    aVar.m(5, list, null);
                }
            }

            public void a0(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
                a aVar = this.f122a.get();
                if (aVar != null) {
                    aVar.m(4, parcelableVolumeInfo != null ? new f(parcelableVolumeInfo.f139b, parcelableVolumeInfo.f140c, parcelableVolumeInfo.f141d, parcelableVolumeInfo.f142e, parcelableVolumeInfo.f) : null, null);
                }
            }

            public void b(CharSequence charSequence) throws RemoteException {
                a aVar = this.f122a.get();
                if (aVar != null) {
                    aVar.m(6, charSequence, null);
                }
            }

            public void d() throws RemoteException {
                a aVar = this.f122a.get();
                if (aVar != null) {
                    aVar.m(8, null, null);
                }
            }

            public void f(Bundle bundle) throws RemoteException {
                a aVar = this.f122a.get();
                if (aVar != null) {
                    aVar.m(7, bundle, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void i(boolean z) throws RemoteException {
            }

            public void r(MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
                a aVar = this.f122a.get();
                if (aVar != null) {
                    aVar.m(3, mediaMetadataCompat, null);
                }
            }

            @Override // android.support.v4.media.session.a
            public void z(int i) throws RemoteException {
                a aVar = this.f122a.get();
                if (aVar != null) {
                    aVar.m(9, Integer.valueOf(i), null);
                }
            }
        }

        public a() {
            if (Build.VERSION.SDK_INT >= 21) {
                this.f116a = android.support.v4.media.session.c.a(new b(this));
                return;
            }
            c cVar = new c(this);
            this.f118c = cVar;
            this.f116a = cVar;
        }

        public void a(f fVar) {
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
            HandlerC0005a handlerC0005a = this.f117b;
            if (handlerC0005a != null) {
                Message messageObtainMessage = handlerC0005a.obtainMessage(i, obj);
                messageObtainMessage.setData(bundle);
                messageObtainMessage.sendToTarget();
            }
        }

        void n(Handler handler) {
            if (handler != null) {
                HandlerC0005a handlerC0005a = new HandlerC0005a(handler.getLooper());
                this.f117b = handlerC0005a;
                handlerC0005a.f119a = true;
            } else {
                HandlerC0005a handlerC0005a2 = this.f117b;
                if (handlerC0005a2 != null) {
                    handlerC0005a2.f119a = false;
                    handlerC0005a2.removeCallbacksAndMessages(null);
                    this.f117b = null;
                }
            }
        }
    }

    interface b {
        g a();

        boolean b(KeyEvent keyEvent);

        MediaMetadataCompat c();

        void d(a aVar, Handler handler);

        PlaybackStateCompat e();
    }

    static class c extends MediaControllerImplApi21 {
        public c(Context context, MediaSessionCompat.Token token) throws RemoteException {
            super(context, token);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.MediaControllerImplApi21, android.support.v4.media.session.MediaControllerCompat.b
        public g a() {
            Object objF = android.support.v4.media.session.c.f(this.f110a);
            if (objF != null) {
                return new i(objF);
            }
            return null;
        }
    }

    static class d extends c {
        public d(Context context, MediaSessionCompat.Token token) throws RemoteException {
            super(context, token);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.c, android.support.v4.media.session.MediaControllerCompat.MediaControllerImplApi21, android.support.v4.media.session.MediaControllerCompat.b
        public g a() {
            Object objF = android.support.v4.media.session.c.f(this.f110a);
            if (objF != null) {
                return new j(objF);
            }
            return null;
        }
    }

    static class e implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private android.support.v4.media.session.b f123a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private g f124b;

        public e(MediaSessionCompat.Token token) {
            this.f123a = b.a.c0((IBinder) token.i());
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public g a() {
            if (this.f124b == null) {
                this.f124b = new k(this.f123a);
            }
            return this.f124b;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public boolean b(KeyEvent keyEvent) {
            if (keyEvent == null) {
                throw new IllegalArgumentException("event may not be null.");
            }
            try {
                this.f123a.b0(keyEvent);
                return false;
            } catch (RemoteException e2) {
                Log.e("MediaControllerCompat", "Dead object in dispatchMediaButtonEvent.", e2);
                return false;
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public MediaMetadataCompat c() {
            try {
                return this.f123a.c();
            } catch (RemoteException e2) {
                Log.e("MediaControllerCompat", "Dead object in getMetadata.", e2);
                return null;
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public void d(a aVar, Handler handler) {
            if (aVar == null) {
                throw new IllegalArgumentException("callback may not be null.");
            }
            try {
                this.f123a.asBinder().linkToDeath(aVar, 0);
                this.f123a.p((android.support.v4.media.session.a) aVar.f116a);
                aVar.m(13, null, null);
            } catch (RemoteException e2) {
                Log.e("MediaControllerCompat", "Dead object in registerCallback.", e2);
                aVar.m(8, null, null);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public PlaybackStateCompat e() {
            try {
                return this.f123a.e();
            } catch (RemoteException e2) {
                Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", e2);
                return null;
            }
        }
    }

    public static final class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f125a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f126b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f127c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f128d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int f129e;

        f(int i, int i2, int i3, int i4, int i5) {
            this.f125a = i;
            this.f126b = i2;
            this.f127c = i3;
            this.f128d = i4;
            this.f129e = i5;
        }
    }

    public static abstract class g {
        g() {
        }

        public abstract void a();

        public abstract void b();

        public abstract void c();

        public abstract void d();
    }

    static class h extends g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected final Object f130a;

        public h(Object obj) {
            this.f130a = obj;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.g
        public void a() {
            c.d.a(this.f130a);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.g
        public void b() {
            c.d.b(this.f130a);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.g
        public void c() {
            c.d.c(this.f130a);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.g
        public void d() {
            c.d.d(this.f130a);
        }
    }

    static class i extends h {
        public i(Object obj) {
            super(obj);
        }
    }

    static class j extends i {
        public j(Object obj) {
            super(obj);
        }
    }

    static class k extends g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private android.support.v4.media.session.b f131a;

        public k(android.support.v4.media.session.b bVar) {
            this.f131a = bVar;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.g
        public void a() {
            try {
                this.f131a.pause();
            } catch (RemoteException e2) {
                Log.e("MediaControllerCompat", "Dead object in pause.", e2);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.g
        public void b() {
            try {
                this.f131a.play();
            } catch (RemoteException e2) {
                Log.e("MediaControllerCompat", "Dead object in play.", e2);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.g
        public void c() {
            try {
                this.f131a.next();
            } catch (RemoteException e2) {
                Log.e("MediaControllerCompat", "Dead object in skipToNext.", e2);
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.g
        public void d() {
            try {
                this.f131a.previous();
            } catch (RemoteException e2) {
                Log.e("MediaControllerCompat", "Dead object in skipToPrevious.", e2);
            }
        }
    }

    public MediaControllerCompat(Context context, MediaSessionCompat.Token token) throws RemoteException {
        if (token == null) {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
        this.f108b = token;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 24) {
            this.f107a = new d(context, token);
            return;
        }
        if (i2 >= 23) {
            this.f107a = new c(context, token);
        } else if (i2 >= 21) {
            this.f107a = new MediaControllerImplApi21(context, token);
        } else {
            this.f107a = new e(token);
        }
    }

    public boolean a(KeyEvent keyEvent) {
        if (keyEvent != null) {
            return this.f107a.b(keyEvent);
        }
        throw new IllegalArgumentException("KeyEvent may not be null");
    }

    public MediaMetadataCompat b() {
        return this.f107a.c();
    }

    public PlaybackStateCompat c() {
        return this.f107a.e();
    }

    public g d() {
        return this.f107a.a();
    }

    public void e(a aVar) {
        f(aVar, null);
    }

    public void f(a aVar, Handler handler) {
        if (aVar == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        if (handler == null) {
            handler = new Handler();
        }
        aVar.n(handler);
        this.f107a.d(aVar, handler);
        this.f109c.add(aVar);
    }
}
