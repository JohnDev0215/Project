package android.support.v4.media.session;

import a.b.a.a.f.a;
import a.b.a.a.f.b;
import a.b.a.a.f.c;
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
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.BundleCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class MediaControllerCompat {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f101a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final MediaSessionCompat.Token f102b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashSet<a> f103c = new HashSet<>();

    @RequiresApi(21)
    public static class MediaControllerImplApi21 implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f104a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Object f105b = new Object();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @GuardedBy("mLock")
        public final List<a> f106c = new ArrayList();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public HashMap<a, a> f107d = new HashMap<>();
        public final MediaSessionCompat.Token e;

        public static class ExtraBinderRequestResultReceiver extends ResultReceiver {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public WeakReference<MediaControllerImplApi21> f108b;

            public ExtraBinderRequestResultReceiver(MediaControllerImplApi21 mediaControllerImplApi21) {
                super(null);
                this.f108b = new WeakReference<>(mediaControllerImplApi21);
            }

            @Override // android.os.ResultReceiver
            public void onReceiveResult(int i, Bundle bundle) {
                MediaControllerImplApi21 mediaControllerImplApi21 = this.f108b.get();
                if (mediaControllerImplApi21 == null || bundle == null) {
                    return;
                }
                synchronized (mediaControllerImplApi21.f105b) {
                    mediaControllerImplApi21.e.e(b.a.B(BundleCompat.getBinder(bundle, "android.support.v4.media.session.EXTRA_BINDER")));
                    mediaControllerImplApi21.e.f(bundle.getBundle("android.support.v4.media.session.SESSION_TOKEN2_BUNDLE"));
                    mediaControllerImplApi21.b();
                }
            }
        }

        public static class a extends a.c {
            public a(a aVar) {
                super(aVar);
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, a.b.a.a.f.a
            public void a(CharSequence charSequence) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, a.b.a.a.f.a
            public void b() throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, a.b.a.a.f.a
            public void c(Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, a.b.a.a.f.a
            public void d(List<MediaSessionCompat.QueueItem> list) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, a.b.a.a.f.a
            public void i(MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, a.b.a.a.f.a
            public void z(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
                throw new AssertionError();
            }
        }

        public MediaControllerImplApi21(Context context, MediaSessionCompat.Token token) throws RemoteException {
            this.e = token;
            Object objC = a.b.a.a.f.c.c(context, token.d());
            this.f104a = objC;
            if (objC == null) {
                throw new RemoteException();
            }
            if (token.c() == null) {
                c();
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public boolean a(KeyEvent keyEvent) {
            return a.b.a.a.f.c.b(this.f104a, keyEvent);
        }

        @GuardedBy("mLock")
        public void b() {
            if (this.e.c() == null) {
                return;
            }
            for (a aVar : this.f106c) {
                a aVar2 = new a(aVar);
                this.f107d.put(aVar, aVar2);
                aVar.f111c = aVar2;
                try {
                    this.e.c().h(aVar2);
                    aVar.i(13, null, null);
                } catch (RemoteException e) {
                    Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                }
            }
            this.f106c.clear();
        }

        public final void c() {
            d("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this));
        }

        public void d(String str, Bundle bundle, ResultReceiver resultReceiver) {
            a.b.a.a.f.c.d(this.f104a, str, bundle, resultReceiver);
        }
    }

    public static abstract class a implements IBinder.DeathRecipient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f109a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public HandlerC0010a f110b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public a.b.a.a.f.a f111c;

        /* JADX INFO: renamed from: android.support.v4.media.session.MediaControllerCompat$a$a, reason: collision with other inner class name */
        public class HandlerC0010a extends Handler {
        }

        public static class b implements c.a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final WeakReference<a> f112a;

            public b(a aVar) {
                this.f112a = new WeakReference<>(aVar);
            }

            @Override // a.b.a.a.f.c.a
            public void a(CharSequence charSequence) {
                a aVar = this.f112a.get();
                if (aVar != null) {
                    aVar.f(charSequence);
                }
            }

            @Override // a.b.a.a.f.c.a
            public void b() {
                a aVar = this.f112a.get();
                if (aVar != null) {
                    aVar.g();
                }
            }

            @Override // a.b.a.a.f.c.a
            public void c(Bundle bundle) {
                a aVar = this.f112a.get();
                if (aVar != null) {
                    aVar.b(bundle);
                }
            }

            @Override // a.b.a.a.f.c.a
            public void d(List<?> list) {
                a aVar = this.f112a.get();
                if (aVar != null) {
                    aVar.e(MediaSessionCompat.QueueItem.b(list));
                }
            }

            @Override // a.b.a.a.f.c.a
            public void e(Object obj) {
                a aVar = this.f112a.get();
                if (aVar != null) {
                    aVar.c(MediaMetadataCompat.a(obj));
                }
            }

            @Override // a.b.a.a.f.c.a
            public void f(int i, int i2, int i3, int i4, int i5) {
                a aVar = this.f112a.get();
                if (aVar != null) {
                    aVar.a(new f(i, i2, i3, i4, i5));
                }
            }

            @Override // a.b.a.a.f.c.a
            public void g(Object obj) {
                a aVar = this.f112a.get();
                if (aVar == null || aVar.f111c != null) {
                    return;
                }
                aVar.d(PlaybackStateCompat.a(obj));
            }

            @Override // a.b.a.a.f.c.a
            public void h(String str, Bundle bundle) {
                a aVar = this.f112a.get();
                if (aVar != null) {
                    if (aVar.f111c == null || Build.VERSION.SDK_INT >= 23) {
                        aVar.h(str, bundle);
                    }
                }
            }
        }

        public static class c extends a.AbstractBinderC0003a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final WeakReference<a> f113a;

            public c(a aVar) {
                this.f113a = new WeakReference<>(aVar);
            }

            public void a(CharSequence charSequence) throws RemoteException {
                a aVar = this.f113a.get();
                if (aVar != null) {
                    aVar.i(6, charSequence, null);
                }
            }

            public void b() throws RemoteException {
                a aVar = this.f113a.get();
                if (aVar != null) {
                    aVar.i(8, null, null);
                }
            }

            public void c(Bundle bundle) throws RemoteException {
                a aVar = this.f113a.get();
                if (aVar != null) {
                    aVar.i(7, bundle, null);
                }
            }

            public void d(List<MediaSessionCompat.QueueItem> list) throws RemoteException {
                a aVar = this.f113a.get();
                if (aVar != null) {
                    aVar.i(5, list, null);
                }
            }

            @Override // a.b.a.a.f.a
            public void g(boolean z) throws RemoteException {
            }

            public void i(MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
                a aVar = this.f113a.get();
                if (aVar != null) {
                    aVar.i(3, mediaMetadataCompat, null);
                }
            }

            @Override // a.b.a.a.f.a
            public void j(int i) throws RemoteException {
                a aVar = this.f113a.get();
                if (aVar != null) {
                    aVar.i(9, Integer.valueOf(i), null);
                }
            }

            @Override // a.b.a.a.f.a
            public void l(int i) throws RemoteException {
                a aVar = this.f113a.get();
                if (aVar != null) {
                    aVar.i(12, Integer.valueOf(i), null);
                }
            }

            @Override // a.b.a.a.f.a
            public void m() throws RemoteException {
                a aVar = this.f113a.get();
                if (aVar != null) {
                    aVar.i(13, null, null);
                }
            }

            @Override // a.b.a.a.f.a
            public void s(boolean z) throws RemoteException {
                a aVar = this.f113a.get();
                if (aVar != null) {
                    aVar.i(11, Boolean.valueOf(z), null);
                }
            }

            @Override // a.b.a.a.f.a
            public void w(PlaybackStateCompat playbackStateCompat) throws RemoteException {
                a aVar = this.f113a.get();
                if (aVar != null) {
                    aVar.i(2, playbackStateCompat, null);
                }
            }

            @Override // a.b.a.a.f.a
            public void x(String str, Bundle bundle) throws RemoteException {
                a aVar = this.f113a.get();
                if (aVar != null) {
                    aVar.i(1, str, bundle);
                }
            }

            public void z(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
                a aVar = this.f113a.get();
                if (aVar != null) {
                    aVar.i(4, parcelableVolumeInfo != null ? new f(parcelableVolumeInfo.f128b, parcelableVolumeInfo.f129c, parcelableVolumeInfo.f130d, parcelableVolumeInfo.e, parcelableVolumeInfo.f) : null, null);
                }
            }
        }

        public a() {
            if (Build.VERSION.SDK_INT >= 21) {
                this.f109a = a.b.a.a.f.c.a(new b(this));
                return;
            }
            c cVar = new c(this);
            this.f111c = cVar;
            this.f109a = cVar;
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

        public void i(int i, Object obj, Bundle bundle) {
            if (this.f110b != null) {
                throw null;
            }
        }
    }

    public interface b {
        boolean a(KeyEvent keyEvent);
    }

    @RequiresApi(23)
    public static class c extends MediaControllerImplApi21 {
        public c(Context context, MediaSessionCompat.Token token) throws RemoteException {
            super(context, token);
        }
    }

    @RequiresApi(24)
    public static class d extends c {
        public d(Context context, MediaSessionCompat.Token token) throws RemoteException {
            super(context, token);
        }
    }

    public static class e implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public a.b.a.a.f.b f114a;

        public e(MediaSessionCompat.Token token) {
            this.f114a = b.a.B((IBinder) token.d());
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public boolean a(KeyEvent keyEvent) {
            if (keyEvent == null) {
                throw new IllegalArgumentException("event may not be null.");
            }
            try {
                this.f114a.A(keyEvent);
                return false;
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in dispatchMediaButtonEvent.", e);
                return false;
            }
        }
    }

    public static final class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f115a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f116b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f117c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f118d;
        public final int e;

        public f(int i, int i2, int i3, int i4, int i5) {
            this.f115a = i;
            this.f116b = i2;
            this.f117c = i3;
            this.f118d = i4;
            this.e = i5;
        }
    }

    public MediaControllerCompat(Context context, @NonNull MediaSessionCompat.Token token) throws RemoteException {
        if (token == null) {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
        this.f102b = token;
        int i = Build.VERSION.SDK_INT;
        if (i >= 24) {
            this.f101a = new d(context, token);
            return;
        }
        if (i >= 23) {
            this.f101a = new c(context, token);
        } else if (i >= 21) {
            this.f101a = new MediaControllerImplApi21(context, token);
        } else {
            this.f101a = new e(token);
        }
    }

    public boolean a(KeyEvent keyEvent) {
        if (keyEvent != null) {
            return this.f101a.a(keyEvent);
        }
        throw new IllegalArgumentException("KeyEvent may not be null");
    }
}
