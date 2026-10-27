package android.support.v4.media.session;

import a.d.a.b.f.a;
import a.d.a.b.f.b;
import a.d.a.b.f.c;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
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
    public final b f132a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final MediaSessionCompat.Token f133b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashSet<a> f134c = new HashSet<>();

    public static class MediaControllerImplApi21 implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f135a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Object f136b = new Object();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final List<a> f137c = new ArrayList();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public HashMap<a, a> f138d = new HashMap<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final MediaSessionCompat.Token f139e;

        public static class ExtraBinderRequestResultReceiver extends ResultReceiver {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public WeakReference<MediaControllerImplApi21> f140b;

            public ExtraBinderRequestResultReceiver(MediaControllerImplApi21 mediaControllerImplApi21) {
                super(null);
                this.f140b = new WeakReference<>(mediaControllerImplApi21);
            }

            @Override // android.os.ResultReceiver
            public void onReceiveResult(int i2, Bundle bundle) {
                MediaControllerImplApi21 mediaControllerImplApi21 = this.f140b.get();
                if (mediaControllerImplApi21 == null || bundle == null) {
                    return;
                }
                synchronized (mediaControllerImplApi21.f136b) {
                    mediaControllerImplApi21.f139e.n(b.a.K(b.h.d.e.a(bundle, "android.support.v4.media.session.EXTRA_BINDER")));
                    mediaControllerImplApi21.f139e.o(bundle.getBundle("android.support.v4.media.session.SESSION_TOKEN2_BUNDLE"));
                    mediaControllerImplApi21.b();
                }
            }
        }

        public static class a extends a.c {
            public a(a aVar) {
                super(aVar);
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, a.d.a.b.f.a
            public void I(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, a.d.a.b.f.a
            public void a(List<MediaSessionCompat.QueueItem> list) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, a.d.a.b.f.a
            public void b(CharSequence charSequence) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, a.d.a.b.f.a
            public void c() throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, a.d.a.b.f.a
            public void d(Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, a.d.a.b.f.a
            public void k(MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
                throw new AssertionError();
            }
        }

        public MediaControllerImplApi21(Context context, MediaSessionCompat.Token token) throws RemoteException {
            this.f139e = token;
            Object objC = a.d.a.b.f.c.c(context, token.m());
            this.f135a = objC;
            if (objC == null) {
                throw new RemoteException();
            }
            if (token.l() == null) {
                c();
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public boolean a(KeyEvent keyEvent) {
            return a.d.a.b.f.c.b(this.f135a, keyEvent);
        }

        public void b() {
            if (this.f139e.l() == null) {
                return;
            }
            for (a aVar : this.f137c) {
                a aVar2 = new a(aVar);
                this.f138d.put(aVar, aVar2);
                aVar.f143c = aVar2;
                try {
                    this.f139e.l().j(aVar2);
                    aVar.i(13, null, null);
                } catch (RemoteException e2) {
                    Log.e("MediaControllerCompat", "Dead object in registerCallback.", e2);
                }
            }
            this.f137c.clear();
        }

        public final void c() {
            d("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this));
        }

        public void d(String str, Bundle bundle, ResultReceiver resultReceiver) {
            a.d.a.b.f.c.d(this.f135a, str, bundle, resultReceiver);
        }
    }

    public static abstract class a implements IBinder.DeathRecipient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f141a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public HandlerC0011a f142b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public a.d.a.b.f.a f143c;

        /* JADX INFO: renamed from: android.support.v4.media.session.MediaControllerCompat$a$a, reason: collision with other inner class name */
        public class HandlerC0011a extends Handler {
        }

        public static class b implements c.a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final WeakReference<a> f144a;

            public b(a aVar) {
                this.f144a = new WeakReference<>(aVar);
            }

            @Override // a.d.a.b.f.c.a
            public void a(List<?> list) {
                a aVar = this.f144a.get();
                if (aVar != null) {
                    aVar.e(MediaSessionCompat.QueueItem.k(list));
                }
            }

            @Override // a.d.a.b.f.c.a
            public void b(CharSequence charSequence) {
                a aVar = this.f144a.get();
                if (aVar != null) {
                    aVar.f(charSequence);
                }
            }

            @Override // a.d.a.b.f.c.a
            public void c() {
                a aVar = this.f144a.get();
                if (aVar != null) {
                    aVar.g();
                }
            }

            @Override // a.d.a.b.f.c.a
            public void d(Bundle bundle) {
                a aVar = this.f144a.get();
                if (aVar != null) {
                    aVar.b(bundle);
                }
            }

            @Override // a.d.a.b.f.c.a
            public void e(Object obj) {
                a aVar = this.f144a.get();
                if (aVar == null || aVar.f143c != null) {
                    return;
                }
                aVar.d(PlaybackStateCompat.j(obj));
            }

            @Override // a.d.a.b.f.c.a
            public void f(Object obj) {
                a aVar = this.f144a.get();
                if (aVar != null) {
                    aVar.c(MediaMetadataCompat.j(obj));
                }
            }

            @Override // a.d.a.b.f.c.a
            public void g(int i2, int i3, int i4, int i5, int i6) {
                a aVar = this.f144a.get();
                if (aVar != null) {
                    aVar.a(new f(i2, i3, i4, i5, i6));
                }
            }

            @Override // a.d.a.b.f.c.a
            public void h(String str, Bundle bundle) {
                a aVar = this.f144a.get();
                if (aVar != null) {
                    if (aVar.f143c == null || Build.VERSION.SDK_INT >= 23) {
                        aVar.h(str, bundle);
                    }
                }
            }
        }

        public static class c extends a.AbstractBinderC0004a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final WeakReference<a> f145a;

            public c(a aVar) {
                this.f145a = new WeakReference<>(aVar);
            }

            @Override // a.d.a.b.f.a
            public void A(int i2) throws RemoteException {
                a aVar = this.f145a.get();
                if (aVar != null) {
                    aVar.i(12, Integer.valueOf(i2), null);
                }
            }

            @Override // a.d.a.b.f.a
            public void B() throws RemoteException {
                a aVar = this.f145a.get();
                if (aVar != null) {
                    aVar.i(13, null, null);
                }
            }

            @Override // a.d.a.b.f.a
            public void E(PlaybackStateCompat playbackStateCompat) throws RemoteException {
                a aVar = this.f145a.get();
                if (aVar != null) {
                    aVar.i(2, playbackStateCompat, null);
                }
            }

            @Override // a.d.a.b.f.a
            public void F(String str, Bundle bundle) throws RemoteException {
                a aVar = this.f145a.get();
                if (aVar != null) {
                    aVar.i(1, str, bundle);
                }
            }

            public void I(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
                a aVar = this.f145a.get();
                if (aVar != null) {
                    aVar.i(4, parcelableVolumeInfo != null ? new f(parcelableVolumeInfo.f159b, parcelableVolumeInfo.f160c, parcelableVolumeInfo.f161d, parcelableVolumeInfo.f162e, parcelableVolumeInfo.f163f) : null, null);
                }
            }

            public void a(List<MediaSessionCompat.QueueItem> list) throws RemoteException {
                a aVar = this.f145a.get();
                if (aVar != null) {
                    aVar.i(5, list, null);
                }
            }

            public void b(CharSequence charSequence) throws RemoteException {
                a aVar = this.f145a.get();
                if (aVar != null) {
                    aVar.i(6, charSequence, null);
                }
            }

            public void c() throws RemoteException {
                a aVar = this.f145a.get();
                if (aVar != null) {
                    aVar.i(8, null, null);
                }
            }

            public void d(Bundle bundle) throws RemoteException {
                a aVar = this.f145a.get();
                if (aVar != null) {
                    aVar.i(7, bundle, null);
                }
            }

            @Override // a.d.a.b.f.a
            public void g(boolean z) throws RemoteException {
            }

            public void k(MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
                a aVar = this.f145a.get();
                if (aVar != null) {
                    aVar.i(3, mediaMetadataCompat, null);
                }
            }

            @Override // a.d.a.b.f.a
            public void o(int i2) throws RemoteException {
                a aVar = this.f145a.get();
                if (aVar != null) {
                    aVar.i(9, Integer.valueOf(i2), null);
                }
            }

            @Override // a.d.a.b.f.a
            public void v(boolean z) throws RemoteException {
                a aVar = this.f145a.get();
                if (aVar != null) {
                    aVar.i(11, Boolean.valueOf(z), null);
                }
            }
        }

        public a() {
            if (Build.VERSION.SDK_INT >= 21) {
                this.f141a = a.d.a.b.f.c.a(new b(this));
                return;
            }
            c cVar = new c(this);
            this.f143c = cVar;
            this.f141a = cVar;
        }

        public void a(f fVar) {
        }

        public void b(Bundle bundle) {
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            i(8, null, null);
        }

        public void c(MediaMetadataCompat mediaMetadataCompat) {
        }

        public void d(PlaybackStateCompat playbackStateCompat) {
        }

        public void e(List<MediaSessionCompat.QueueItem> list) {
        }

        public void f(CharSequence charSequence) {
        }

        public void g() {
        }

        public void h(String str, Bundle bundle) {
        }

        public void i(int i2, Object obj, Bundle bundle) {
            if (this.f142b != null) {
                throw null;
            }
        }
    }

    public interface b {
        boolean a(KeyEvent keyEvent);
    }

    public static class c extends MediaControllerImplApi21 {
        public c(Context context, MediaSessionCompat.Token token) throws RemoteException {
            super(context, token);
        }
    }

    public static class d extends c {
        public d(Context context, MediaSessionCompat.Token token) throws RemoteException {
            super(context, token);
        }
    }

    public static class e implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public a.d.a.b.f.b f146a;

        public e(MediaSessionCompat.Token token) {
            this.f146a = b.a.K((IBinder) token.m());
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public boolean a(KeyEvent keyEvent) {
            if (keyEvent == null) {
                throw new IllegalArgumentException("event may not be null.");
            }
            try {
                this.f146a.J(keyEvent);
                return false;
            } catch (RemoteException e2) {
                Log.e("MediaControllerCompat", "Dead object in dispatchMediaButtonEvent.", e2);
                return false;
            }
        }
    }

    public static final class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f147a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f148b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f149c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f150d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f151e;

        public f(int i2, int i3, int i4, int i5, int i6) {
            this.f147a = i2;
            this.f148b = i3;
            this.f149c = i4;
            this.f150d = i5;
            this.f151e = i6;
        }
    }

    public MediaControllerCompat(Context context, MediaSessionCompat.Token token) throws RemoteException {
        if (token == null) {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
        this.f133b = token;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 24) {
            this.f132a = new d(context, token);
            return;
        }
        if (i2 >= 23) {
            this.f132a = new c(context, token);
        } else if (i2 >= 21) {
            this.f132a = new MediaControllerImplApi21(context, token);
        } else {
            this.f132a = new e(token);
        }
    }

    public boolean a(KeyEvent keyEvent) {
        if (keyEvent != null) {
            return this.f132a.a(keyEvent);
        }
        throw new IllegalArgumentException("KeyEvent may not be null");
    }
}
