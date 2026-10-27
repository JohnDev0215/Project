package android.support.v4.media.session;

import a.d.a.a.f.a;
import a.d.a.a.f.b;
import a.d.a.a.f.c;
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
    public final b f120a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final MediaSessionCompat.Token f121b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashSet<a> f122c = new HashSet<>();

    @RequiresApi(21)
    public static class MediaControllerImplApi21 implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f123a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Object f124b = new Object();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @GuardedBy("mLock")
        public final List<a> f125c = new ArrayList();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public HashMap<a, a> f126d = new HashMap<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final MediaSessionCompat.Token f127e;

        public static class ExtraBinderRequestResultReceiver extends ResultReceiver {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public WeakReference<MediaControllerImplApi21> f128a;

            public ExtraBinderRequestResultReceiver(MediaControllerImplApi21 mediaControllerImplApi21) {
                super(null);
                this.f128a = new WeakReference<>(mediaControllerImplApi21);
            }

            @Override // android.os.ResultReceiver
            public void onReceiveResult(int i, Bundle bundle) {
                MediaControllerImplApi21 mediaControllerImplApi21 = this.f128a.get();
                if (mediaControllerImplApi21 == null || bundle == null) {
                    return;
                }
                synchronized (mediaControllerImplApi21.f124b) {
                    mediaControllerImplApi21.f127e.e(b.a.d0(BundleCompat.getBinder(bundle, "android.support.v4.media.session.EXTRA_BINDER")));
                    mediaControllerImplApi21.f127e.f(bundle.getBundle("android.support.v4.media.session.SESSION_TOKEN2_BUNDLE"));
                    mediaControllerImplApi21.b();
                }
            }
        }

        public static class a extends a.c {
            public a(a aVar) {
                super(aVar);
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, a.d.a.a.f.a
            public void a(CharSequence charSequence) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, a.d.a.a.f.a
            public void b0(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, a.d.a.a.f.a
            public void d() throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, a.d.a.a.f.a
            public void g(Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, a.d.a.a.f.a
            public void h(List<MediaSessionCompat.QueueItem> list) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.MediaControllerCompat.a.c, a.d.a.a.f.a
            public void s(MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
                throw new AssertionError();
            }
        }

        public MediaControllerImplApi21(Context context, MediaSessionCompat.Token token) throws RemoteException {
            this.f127e = token;
            Object objC = a.d.a.a.f.c.c(context, token.d());
            this.f123a = objC;
            if (objC == null) {
                throw new RemoteException();
            }
            if (token.c() == null) {
                c();
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public boolean a(KeyEvent keyEvent) {
            return a.d.a.a.f.c.b(this.f123a, keyEvent);
        }

        @GuardedBy("mLock")
        public void b() {
            if (this.f127e.c() == null) {
                return;
            }
            for (a aVar : this.f125c) {
                a aVar2 = new a(aVar);
                this.f126d.put(aVar, aVar2);
                aVar.f131c = aVar2;
                try {
                    this.f127e.c().r(aVar2);
                    aVar.i(13, null, null);
                } catch (RemoteException e2) {
                    Log.e("MediaControllerCompat", "Dead object in registerCallback.", e2);
                }
            }
            this.f125c.clear();
        }

        public final void c() {
            d("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this));
        }

        public void d(String str, Bundle bundle, ResultReceiver resultReceiver) {
            a.d.a.a.f.c.d(this.f123a, str, bundle, resultReceiver);
        }
    }

    public static abstract class a implements IBinder.DeathRecipient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f129a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public HandlerC0011a f130b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public a.d.a.a.f.a f131c;

        /* JADX INFO: renamed from: android.support.v4.media.session.MediaControllerCompat$a$a, reason: collision with other inner class name */
        public class HandlerC0011a extends Handler {
        }

        public static class b implements c.a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final WeakReference<a> f132a;

            public b(a aVar) {
                this.f132a = new WeakReference<>(aVar);
            }

            @Override // a.d.a.a.f.c.a
            public void a(CharSequence charSequence) {
                a aVar = this.f132a.get();
                if (aVar != null) {
                    aVar.f(charSequence);
                }
            }

            @Override // a.d.a.a.f.c.a
            public void b(Object obj) {
                a aVar = this.f132a.get();
                if (aVar != null) {
                    aVar.c(MediaMetadataCompat.a(obj));
                }
            }

            @Override // a.d.a.a.f.c.a
            public void c(int i, int i2, int i3, int i4, int i5) {
                a aVar = this.f132a.get();
                if (aVar != null) {
                    aVar.a(new f(i, i2, i3, i4, i5));
                }
            }

            @Override // a.d.a.a.f.c.a
            public void d() {
                a aVar = this.f132a.get();
                if (aVar != null) {
                    aVar.g();
                }
            }

            @Override // a.d.a.a.f.c.a
            public void e(Object obj) {
                a aVar = this.f132a.get();
                if (aVar == null || aVar.f131c != null) {
                    return;
                }
                aVar.d(PlaybackStateCompat.a(obj));
            }

            @Override // a.d.a.a.f.c.a
            public void f(String str, Bundle bundle) {
                a aVar = this.f132a.get();
                if (aVar != null) {
                    if (aVar.f131c == null || Build.VERSION.SDK_INT >= 23) {
                        aVar.h(str, bundle);
                    }
                }
            }

            @Override // a.d.a.a.f.c.a
            public void g(Bundle bundle) {
                a aVar = this.f132a.get();
                if (aVar != null) {
                    aVar.b(bundle);
                }
            }

            @Override // a.d.a.a.f.c.a
            public void h(List<?> list) {
                a aVar = this.f132a.get();
                if (aVar != null) {
                    aVar.e(MediaSessionCompat.QueueItem.b(list));
                }
            }
        }

        public static class c extends a.AbstractBinderC0004a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final WeakReference<a> f133a;

            public c(a aVar) {
                this.f133a = new WeakReference<>(aVar);
            }

            @Override // a.d.a.a.f.a
            public void B(int i) throws RemoteException {
                a aVar = this.f133a.get();
                if (aVar != null) {
                    aVar.i(12, Integer.valueOf(i), null);
                }
            }

            @Override // a.d.a.a.f.a
            public void C() throws RemoteException {
                a aVar = this.f133a.get();
                if (aVar != null) {
                    aVar.i(13, null, null);
                }
            }

            @Override // a.d.a.a.f.a
            public void P(boolean z) throws RemoteException {
                a aVar = this.f133a.get();
                if (aVar != null) {
                    aVar.i(11, Boolean.valueOf(z), null);
                }
            }

            @Override // a.d.a.a.f.a
            public void X(PlaybackStateCompat playbackStateCompat) throws RemoteException {
                a aVar = this.f133a.get();
                if (aVar != null) {
                    aVar.i(2, playbackStateCompat, null);
                }
            }

            @Override // a.d.a.a.f.a
            public void Z(String str, Bundle bundle) throws RemoteException {
                a aVar = this.f133a.get();
                if (aVar != null) {
                    aVar.i(1, str, bundle);
                }
            }

            public void a(CharSequence charSequence) throws RemoteException {
                a aVar = this.f133a.get();
                if (aVar != null) {
                    aVar.i(6, charSequence, null);
                }
            }

            public void b0(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
                a aVar = this.f133a.get();
                if (aVar != null) {
                    aVar.i(4, parcelableVolumeInfo != null ? new f(parcelableVolumeInfo.f149a, parcelableVolumeInfo.f150b, parcelableVolumeInfo.f151c, parcelableVolumeInfo.f152d, parcelableVolumeInfo.f153e) : null, null);
                }
            }

            public void d() throws RemoteException {
                a aVar = this.f133a.get();
                if (aVar != null) {
                    aVar.i(8, null, null);
                }
            }

            public void g(Bundle bundle) throws RemoteException {
                a aVar = this.f133a.get();
                if (aVar != null) {
                    aVar.i(7, bundle, null);
                }
            }

            public void h(List<MediaSessionCompat.QueueItem> list) throws RemoteException {
                a aVar = this.f133a.get();
                if (aVar != null) {
                    aVar.i(5, list, null);
                }
            }

            @Override // a.d.a.a.f.a
            public void p(boolean z) throws RemoteException {
            }

            public void s(MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
                a aVar = this.f133a.get();
                if (aVar != null) {
                    aVar.i(3, mediaMetadataCompat, null);
                }
            }

            @Override // a.d.a.a.f.a
            public void v(int i) throws RemoteException {
                a aVar = this.f133a.get();
                if (aVar != null) {
                    aVar.i(9, Integer.valueOf(i), null);
                }
            }
        }

        public a() {
            if (Build.VERSION.SDK_INT >= 21) {
                this.f129a = a.d.a.a.f.c.a(new b(this));
                return;
            }
            c cVar = new c(this);
            this.f131c = cVar;
            this.f129a = cVar;
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
            if (this.f130b != null) {
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
        public a.d.a.a.f.b f134a;

        public e(MediaSessionCompat.Token token) {
            this.f134a = b.a.d0((IBinder) token.d());
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.b
        public boolean a(KeyEvent keyEvent) {
            if (keyEvent == null) {
                throw new IllegalArgumentException("event may not be null.");
            }
            try {
                this.f134a.c0(keyEvent);
                return false;
            } catch (RemoteException e2) {
                Log.e("MediaControllerCompat", "Dead object in dispatchMediaButtonEvent.", e2);
                return false;
            }
        }
    }

    public static final class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f135a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f136b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f137c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f138d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f139e;

        public f(int i, int i2, int i3, int i4, int i5) {
            this.f135a = i;
            this.f136b = i2;
            this.f137c = i3;
            this.f138d = i4;
            this.f139e = i5;
        }
    }

    public MediaControllerCompat(Context context, @NonNull MediaSessionCompat.Token token) throws RemoteException {
        if (token == null) {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
        this.f121b = token;
        int i = Build.VERSION.SDK_INT;
        if (i >= 24) {
            this.f120a = new d(context, token);
            return;
        }
        if (i >= 23) {
            this.f120a = new c(context, token);
        } else if (i >= 21) {
            this.f120a = new MediaControllerImplApi21(context, token);
        } else {
            this.f120a = new e(token);
        }
    }

    public boolean a(KeyEvent keyEvent) {
        if (keyEvent != null) {
            return this.f120a.a(keyEvent);
        }
        throw new IllegalArgumentException("KeyEvent may not be null");
    }
}
