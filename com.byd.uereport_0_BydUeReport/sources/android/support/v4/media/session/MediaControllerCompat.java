package android.support.v4.media.session;

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
import i.a;
import i.b;
import i.c;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class MediaControllerCompat {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f167a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final MediaSessionCompat.Token f168b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashSet<a> f169c = new HashSet<>();

    public static class MediaControllerImplApi21 implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f170a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Object f171b = new Object();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final List<a> f172c = new ArrayList();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public HashMap<a, a> f173d = new HashMap<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final MediaSessionCompat.Token f174e;

        public static class ExtraBinderRequestResultReceiver extends ResultReceiver {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public WeakReference<MediaControllerImplApi21> f175d;

            public ExtraBinderRequestResultReceiver(MediaControllerImplApi21 mediaControllerImplApi21) {
                super(null);
                this.f175d = new WeakReference<>(mediaControllerImplApi21);
            }

            @Override // android.os.ResultReceiver
            public void onReceiveResult(int i8, Bundle bundle) {
                MediaControllerImplApi21 mediaControllerImplApi21 = this.f175d.get();
                if (mediaControllerImplApi21 == null || bundle == null) {
                    return;
                }
                synchronized (mediaControllerImplApi21.f171b) {
                    mediaControllerImplApi21.f174e.j(b.a.A0(d0.c.a(bundle, "android.support.v4.media.session.EXTRA_BINDER")));
                    mediaControllerImplApi21.f174e.k(bundle.getBundle("android.support.v4.media.session.SESSION_TOKEN2_BUNDLE"));
                    mediaControllerImplApi21.b();
                }
            }
        }

        public static class a extends a.c {
            public a(a aVar) {
                super(aVar);
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, i.a
            public void a(CharSequence charSequence) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, i.a
            public void c() throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, i.a
            public void f(Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, i.a
            public void g(List<MediaSessionCompat.QueueItem> list) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, i.a
            public void q(MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, i.a
            public void y0(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
                throw new AssertionError();
            }
        }

        public MediaControllerImplApi21(Context context, MediaSessionCompat.Token token) throws RemoteException {
            this.f174e = token;
            Object objC = i.c.c(context, token.i());
            this.f170a = objC;
            if (objC == null) {
                throw new RemoteException();
            }
            if (token.h() == null) {
                c();
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public boolean a(KeyEvent keyEvent) {
            return i.c.b(this.f170a, keyEvent);
        }

        public void b() {
            if (this.f174e.h() == null) {
                return;
            }
            for (a aVar : this.f172c) {
                a aVar2 = new a(aVar);
                this.f173d.put(aVar, aVar2);
                aVar.f178c = aVar2;
                try {
                    this.f174e.h().p(aVar2);
                    aVar.i(13, null, null);
                } catch (RemoteException e8) {
                    Log.e("MediaControllerCompat", "Dead object in registerCallback.", e8);
                }
            }
            this.f172c.clear();
        }

        public final void c() {
            d("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this));
        }

        public void d(String str, Bundle bundle, ResultReceiver resultReceiver) {
            i.c.d(this.f170a, str, bundle, resultReceiver);
        }
    }

    public static abstract class a implements IBinder.DeathRecipient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f176a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public HandlerC0004a f177b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public i.a f178c;

        /* JADX INFO: renamed from: android.support.v4.media.session.MediaControllerCompat$a$a, reason: collision with other inner class name */
        public class HandlerC0004a extends Handler {
        }

        public static class b implements c.a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final WeakReference<a> f179a;

            public b(a aVar) {
                this.f179a = new WeakReference<>(aVar);
            }

            @Override // i.c.a
            public void a(CharSequence charSequence) {
                a aVar = this.f179a.get();
                if (aVar != null) {
                    aVar.f(charSequence);
                }
            }

            @Override // i.c.a
            public void b(Object obj) {
                a aVar = this.f179a.get();
                if (aVar != null) {
                    aVar.c(MediaMetadataCompat.f(obj));
                }
            }

            @Override // i.c.a
            public void c() {
                a aVar = this.f179a.get();
                if (aVar != null) {
                    aVar.g();
                }
            }

            @Override // i.c.a
            public void d(int i8, int i9, int i10, int i11, int i12) {
                a aVar = this.f179a.get();
                if (aVar != null) {
                    aVar.a(new f(i8, i9, i10, i11, i12));
                }
            }

            @Override // i.c.a
            public void e(Object obj) {
                a aVar = this.f179a.get();
                if (aVar == null || aVar.f178c != null) {
                    return;
                }
                aVar.d(PlaybackStateCompat.f(obj));
            }

            @Override // i.c.a
            public void f(Bundle bundle) {
                a aVar = this.f179a.get();
                if (aVar != null) {
                    aVar.b(bundle);
                }
            }

            @Override // i.c.a
            public void g(List<?> list) {
                a aVar = this.f179a.get();
                if (aVar != null) {
                    aVar.e(MediaSessionCompat.QueueItem.g(list));
                }
            }

            @Override // i.c.a
            public void h(String str, Bundle bundle) {
                a aVar = this.f179a.get();
                if (aVar != null) {
                    if (aVar.f178c == null || Build.VERSION.SDK_INT >= 23) {
                        aVar.h(str, bundle);
                    }
                }
            }
        }

        public static class c extends a.AbstractBinderC0057a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final WeakReference<a> f180a;

            public c(a aVar) {
                this.f180a = new WeakReference<>(aVar);
            }

            @Override // i.a
            public void E(int i8) throws RemoteException {
                a aVar = this.f180a.get();
                if (aVar != null) {
                    aVar.i(12, Integer.valueOf(i8), null);
                }
            }

            @Override // i.a
            public void F() throws RemoteException {
                a aVar = this.f180a.get();
                if (aVar != null) {
                    aVar.i(13, null, null);
                }
            }

            public void a(CharSequence charSequence) throws RemoteException {
                a aVar = this.f180a.get();
                if (aVar != null) {
                    aVar.i(6, charSequence, null);
                }
            }

            public void c() throws RemoteException {
                a aVar = this.f180a.get();
                if (aVar != null) {
                    aVar.i(8, null, null);
                }
            }

            public void f(Bundle bundle) throws RemoteException {
                a aVar = this.f180a.get();
                if (aVar != null) {
                    aVar.i(7, bundle, null);
                }
            }

            public void g(List<MediaSessionCompat.QueueItem> list) throws RemoteException {
                a aVar = this.f180a.get();
                if (aVar != null) {
                    aVar.i(5, list, null);
                }
            }

            @Override // i.a
            public void g0(boolean z7) throws RemoteException {
                a aVar = this.f180a.get();
                if (aVar != null) {
                    aVar.i(11, Boolean.valueOf(z7), null);
                }
            }

            @Override // i.a
            public void l(boolean z7) throws RemoteException {
            }

            public void q(MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
                a aVar = this.f180a.get();
                if (aVar != null) {
                    aVar.i(3, mediaMetadataCompat, null);
                }
            }

            @Override // i.a
            public void s0(PlaybackStateCompat playbackStateCompat) throws RemoteException {
                a aVar = this.f180a.get();
                if (aVar != null) {
                    aVar.i(2, playbackStateCompat, null);
                }
            }

            @Override // i.a
            public void u0(String str, Bundle bundle) throws RemoteException {
                a aVar = this.f180a.get();
                if (aVar != null) {
                    aVar.i(1, str, bundle);
                }
            }

            @Override // i.a
            public void v(int i8) throws RemoteException {
                a aVar = this.f180a.get();
                if (aVar != null) {
                    aVar.i(9, Integer.valueOf(i8), null);
                }
            }

            public void y0(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
                a aVar = this.f180a.get();
                if (aVar != null) {
                    aVar.i(4, parcelableVolumeInfo != null ? new f(parcelableVolumeInfo.f194d, parcelableVolumeInfo.f195e, parcelableVolumeInfo.f196f, parcelableVolumeInfo.f197g, parcelableVolumeInfo.f198h) : null, null);
                }
            }
        }

        public a() {
            if (Build.VERSION.SDK_INT >= 21) {
                this.f176a = i.c.a(new b(this));
                return;
            }
            c cVar = new c(this);
            this.f178c = cVar;
            this.f176a = cVar;
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

        public void i(int i8, Object obj, Bundle bundle) {
            if (this.f177b != null) {
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
        public i.b f181a;

        public e(MediaSessionCompat.Token token) {
            this.f181a = b.a.A0((IBinder) token.i());
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public boolean a(KeyEvent keyEvent) {
            if (keyEvent == null) {
                throw new IllegalArgumentException("event may not be null.");
            }
            try {
                this.f181a.z0(keyEvent);
                return false;
            } catch (RemoteException e8) {
                Log.e("MediaControllerCompat", "Dead object in dispatchMediaButtonEvent.", e8);
                return false;
            }
        }
    }

    public static final class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f182a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f183b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f184c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f185d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f186e;

        public f(int i8, int i9, int i10, int i11, int i12) {
            this.f182a = i8;
            this.f183b = i9;
            this.f184c = i10;
            this.f185d = i11;
            this.f186e = i12;
        }
    }

    public MediaControllerCompat(Context context, MediaSessionCompat.Token token) throws RemoteException {
        if (token == null) {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
        this.f168b = token;
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 24) {
            this.f167a = new d(context, token);
            return;
        }
        if (i8 >= 23) {
            this.f167a = new c(context, token);
        } else if (i8 >= 21) {
            this.f167a = new MediaControllerImplApi21(context, token);
        } else {
            this.f167a = new e(token);
        }
    }

    public boolean a(KeyEvent keyEvent) {
        if (keyEvent != null) {
            return this.f167a.a(keyEvent);
        }
        throw new IllegalArgumentException("KeyEvent may not be null");
    }
}
