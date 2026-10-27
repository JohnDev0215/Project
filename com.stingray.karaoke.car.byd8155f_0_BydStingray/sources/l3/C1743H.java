package l3;

import android.content.Intent;
import android.media.Rating;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import androidx.versionedparcelable.ParcelImpl;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import x3.InterfaceC2676d;

/* JADX INFO: renamed from: l3.H, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1743H extends MediaSession.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AbstractC1757m f18522a;

    public C1743H(AbstractC1757m abstractC1757m) {
        this.f18522a = abstractC1757m;
    }

    public final K a() {
        K k10;
        synchronized (this.f18522a.f18585a) {
            k10 = (K) ((WeakReference) this.f18522a.c).get();
        }
        if (k10 == null || this.f18522a != k10.b()) {
            return null;
        }
        return k10;
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
        InterfaceC2676d interfaceC2676d;
        K kA = a();
        if (kA == null) {
            return;
        }
        P.o(bundle);
        M m10 = null;
        IBinder iBinderAsBinder = null;
        m10 = null;
        try {
            if (str.equals("android.support.v4.media.session.command.GET_EXTRA_BINDER")) {
                if (resultReceiver != null) {
                    Bundle bundle2 = new Bundle();
                    O o = kA.c;
                    InterfaceC1750f interfaceC1750fA = o.a();
                    if (interfaceC1750fA != null) {
                        iBinderAsBinder = interfaceC1750fA.asBinder();
                    }
                    bundle2.putBinder("android.support.v4.media.session.EXTRA_BINDER", iBinderAsBinder);
                    synchronized (o.l) {
                        interfaceC2676d = o.o;
                    }
                    if (interfaceC2676d != null) {
                        Bundle bundle3 = new Bundle();
                        bundle3.putParcelable("a", new ParcelImpl(interfaceC2676d));
                        bundle2.putParcelable("android.support.v4.media.session.SESSION_TOKEN2", bundle3);
                    }
                    resultReceiver.send(0, bundle2);
                }
            } else if (str.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM")) {
                if (bundle != null) {
                    this.f18522a.a((C1740E) h6.n.g(bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"), C1740E.CREATOR));
                }
            } else if (str.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT")) {
                if (bundle != null) {
                    this.f18522a.b((C1740E) h6.n.g(bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"), C1740E.CREATOR), bundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX"));
                }
            } else if (str.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM")) {
                if (bundle != null) {
                    this.f18522a.p((C1740E) h6.n.g(bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"), C1740E.CREATOR));
                }
            } else if (str.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT")) {
                ArrayList arrayList = kA.f18528h;
                if (arrayList != null && bundle != null) {
                    int i7 = bundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX", -1);
                    if (i7 >= 0 && i7 < arrayList.size()) {
                        m10 = (M) arrayList.get(i7);
                    }
                    if (m10 != null) {
                        this.f18522a.p(m10.l);
                    }
                }
            } else {
                this.f18522a.c(str, bundle, resultReceiver);
            }
        } catch (BadParcelableException unused) {
            h6.n.j("MediaSessionCompat", "Could not unparcel the extra data.");
        }
        kA.getClass();
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onCustomAction(String str, Bundle bundle) {
        K kA = a();
        if (kA == null) {
            return;
        }
        P.o(bundle);
        try {
            boolean zEquals = str.equals("android.support.v4.media.session.action.PLAY_FROM_URI");
            AbstractC1757m abstractC1757m = this.f18522a;
            if (zEquals) {
                if (bundle != null) {
                    Uri uri = (Uri) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
                    Bundle bundle2 = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                    P.o(bundle2);
                    abstractC1757m.k(uri, bundle2);
                }
            } else if (str.equals("android.support.v4.media.session.action.PREPARE")) {
                abstractC1757m.l();
            } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID")) {
                if (bundle != null) {
                    String string = bundle.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID");
                    Bundle bundle3 = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                    P.o(bundle3);
                    abstractC1757m.m(string, bundle3);
                }
            } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH")) {
                if (bundle != null) {
                    String string2 = bundle.getString("android.support.v4.media.session.action.ARGUMENT_QUERY");
                    Bundle bundle4 = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                    P.o(bundle4);
                    abstractC1757m.n(string2, bundle4);
                }
            } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_URI")) {
                if (bundle != null) {
                    Uri uri2 = (Uri) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
                    Bundle bundle5 = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                    P.o(bundle5);
                    abstractC1757m.o(uri2, bundle5);
                }
            } else if (str.equals("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED")) {
                if (bundle != null) {
                    bundle.getBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED");
                    abstractC1757m.getClass();
                }
            } else if (str.equals("android.support.v4.media.session.action.SET_REPEAT_MODE")) {
                if (bundle != null) {
                    abstractC1757m.v(bundle.getInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE"));
                }
            } else if (str.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE")) {
                if (bundle != null) {
                    abstractC1757m.w(bundle.getInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE"));
                }
            } else if (str.equals("android.support.v4.media.session.action.SET_RATING")) {
                if (bundle != null) {
                    Y y10 = (Y) h6.n.g(bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_RATING"), Y.CREATOR);
                    P.o(bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                    abstractC1757m.u(y10);
                }
            } else if (!str.equals("android.support.v4.media.session.action.SET_PLAYBACK_SPEED")) {
                abstractC1757m.d(str, bundle);
            } else if (bundle != null) {
                abstractC1757m.s(bundle.getFloat("android.support.v4.media.session.action.ARGUMENT_PLAYBACK_SPEED", 1.0f));
            }
        } catch (BadParcelableException unused) {
            h6.n.j("MediaSessionCompat", "Could not unparcel the data.");
        }
        kA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onFastForward() {
        K kA = a();
        if (kA == null) {
            return;
        }
        this.f18522a.e();
        kA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final boolean onMediaButtonEvent(Intent intent) {
        K kA = a();
        if (kA == null) {
            return false;
        }
        boolean zF = this.f18522a.f(intent);
        kA.a(null);
        return zF || super.onMediaButtonEvent(intent);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPause() {
        K kA = a();
        if (kA == null) {
            return;
        }
        this.f18522a.g();
        kA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPlay() {
        K kA = a();
        if (kA == null) {
            return;
        }
        this.f18522a.h();
        kA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPlayFromMediaId(String str, Bundle bundle) {
        K kA = a();
        if (kA == null) {
            return;
        }
        P.o(bundle);
        this.f18522a.i(str, bundle);
        kA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPlayFromSearch(String str, Bundle bundle) {
        K kA = a();
        if (kA == null) {
            return;
        }
        P.o(bundle);
        this.f18522a.j(str, bundle);
        kA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPlayFromUri(Uri uri, Bundle bundle) {
        K kA = a();
        if (kA == null) {
            return;
        }
        P.o(bundle);
        this.f18522a.k(uri, bundle);
        kA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPrepare() {
        K kA = a();
        if (kA == null) {
            return;
        }
        this.f18522a.l();
        kA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPrepareFromMediaId(String str, Bundle bundle) {
        K kA = a();
        if (kA == null) {
            return;
        }
        P.o(bundle);
        this.f18522a.m(str, bundle);
        kA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPrepareFromSearch(String str, Bundle bundle) {
        K kA = a();
        if (kA == null) {
            return;
        }
        P.o(bundle);
        this.f18522a.n(str, bundle);
        kA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPrepareFromUri(Uri uri, Bundle bundle) {
        K kA = a();
        if (kA == null) {
            return;
        }
        P.o(bundle);
        this.f18522a.o(uri, bundle);
        kA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onRewind() {
        K kA = a();
        if (kA == null) {
            return;
        }
        this.f18522a.q();
        kA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onSeekTo(long j3) {
        K kA = a();
        if (kA == null) {
            return;
        }
        this.f18522a.r(j3);
        kA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onSetPlaybackSpeed(float f10) {
        K kA = a();
        if (kA == null) {
            return;
        }
        this.f18522a.s(f10);
        kA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onSetRating(Rating rating) {
        K kA = a();
        if (kA == null) {
            return;
        }
        this.f18522a.t(Y.a(rating));
        kA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onSkipToNext() {
        K kA = a();
        if (kA == null) {
            return;
        }
        this.f18522a.x();
        kA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onSkipToPrevious() {
        K kA = a();
        if (kA == null) {
            return;
        }
        this.f18522a.y();
        kA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onSkipToQueueItem(long j3) {
        K kA = a();
        if (kA == null) {
            return;
        }
        this.f18522a.z(j3);
        kA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onStop() {
        K kA = a();
        if (kA == null) {
            return;
        }
        this.f18522a.A();
        kA.a(null);
    }
}
