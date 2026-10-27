package p;

import android.content.Intent;
import android.media.Rating;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Build;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.MediaSessionCompat$QueueItem;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.text.TextUtils;
import android.util.Log;
import androidx.versionedparcelable.ParcelImpl;

/* JADX INFO: loaded from: classes.dex */
public final class sh1 extends MediaSession.Callback {
    public final /* synthetic */ th1 a;

    public sh1(th1 th1Var) {
        this.a = th1Var;
    }

    public static void b(vh1 vh1Var) {
        if (Build.VERSION.SDK_INT >= 28) {
            return;
        }
        String strF = vh1Var.f();
        if (TextUtils.isEmpty(strF)) {
            strF = "android.media.session.MediaController";
        }
        vh1Var.b(new gi1(strF, -1, -1));
    }

    public final vh1 a() {
        vh1 vh1Var;
        synchronized (this.a.a) {
            vh1Var = (vh1) this.a.d.get();
        }
        if (vh1Var == null || this.a != vh1Var.c()) {
            return null;
        }
        return vh1Var;
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
        at2 at2Var;
        vh1 vh1VarA = a();
        if (vh1VarA == null) {
            return;
        }
        bi1.a(bundle);
        b(vh1VarA);
        try {
            if (str.equals("android.support.v4.media.session.command.GET_EXTRA_BINDER")) {
                Bundle bundle2 = new Bundle();
                MediaSessionCompat$Token mediaSessionCompat$Token = vh1VarA.c;
                android.support.v4.media.session.b bVarA = mediaSessionCompat$Token.a();
                uo.b(bundle2, "android.support.v4.media.session.EXTRA_BINDER", bVarA == null ? null : bVarA.asBinder());
                synchronized (mediaSessionCompat$Token.c) {
                    at2Var = mediaSessionCompat$Token.l;
                }
                if (at2Var != null) {
                    Bundle bundle3 = new Bundle();
                    bundle3.putParcelable("a", new ParcelImpl(at2Var));
                    bundle2.putParcelable("android.support.v4.media.session.SESSION_TOKEN2", bundle3);
                }
                resultReceiver.send(0, bundle2);
            } else if (str.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM")) {
                this.a.b((MediaDescriptionCompat) bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
            } else if (str.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT")) {
                th1 th1Var = this.a;
                MediaDescriptionCompat mediaDescriptionCompat = (MediaDescriptionCompat) bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION");
                bundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX");
                th1Var.c(mediaDescriptionCompat);
            } else if (str.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM")) {
                this.a.l((MediaDescriptionCompat) bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
            } else if (!str.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT")) {
                this.a.getClass();
            } else if (vh1VarA.h != null) {
                int i = bundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX", -1);
                MediaSessionCompat$QueueItem mediaSessionCompat$QueueItem = (i < 0 || i >= vh1VarA.h.size()) ? null : (MediaSessionCompat$QueueItem) vh1VarA.h.get(i);
                if (mediaSessionCompat$QueueItem != null) {
                    this.a.l(mediaSessionCompat$QueueItem.c);
                }
            }
        } catch (BadParcelableException unused) {
            Log.e("MediaSessionCompat", "Could not unparcel the extra data.");
        }
        vh1VarA.b(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onCustomAction(String str, Bundle bundle) {
        vh1 vh1VarA = a();
        if (vh1VarA == null) {
            return;
        }
        bi1.a(bundle);
        b(vh1VarA);
        try {
            boolean zEquals = str.equals("android.support.v4.media.session.action.PLAY_FROM_URI");
            th1 th1Var = this.a;
            if (zEquals) {
                Uri uri = (Uri) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
                Bundle bundle2 = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                bi1.a(bundle2);
                th1Var.j(uri, bundle2);
            } else if (str.equals("android.support.v4.media.session.action.PREPARE")) {
                th1Var.getClass();
            } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID")) {
                bundle.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID");
                bi1.a(bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                th1Var.getClass();
            } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH")) {
                bundle.getString("android.support.v4.media.session.action.ARGUMENT_QUERY");
                bi1.a(bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                th1Var.getClass();
            } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_URI")) {
                Uri uri2 = (Uri) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
                Bundle bundle3 = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                bi1.a(bundle3);
                th1Var.k(uri2, bundle3);
            } else if (str.equals("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED")) {
                bundle.getBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED");
                th1Var.getClass();
            } else if (str.equals("android.support.v4.media.session.action.SET_REPEAT_MODE")) {
                th1Var.n(bundle.getInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE"));
            } else if (str.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE")) {
                th1Var.o(bundle.getInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE"));
            } else if (str.equals("android.support.v4.media.session.action.SET_RATING")) {
                bi1.a(bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                th1Var.getClass();
            } else if (str.equals("android.support.v4.media.session.action.SET_PLAYBACK_SPEED")) {
                bundle.getFloat("android.support.v4.media.session.action.ARGUMENT_PLAYBACK_SPEED", 1.0f);
                th1Var.getClass();
            } else {
                th1Var.d(str);
            }
        } catch (BadParcelableException unused) {
            Log.e("MediaSessionCompat", "Could not unparcel the data.");
        }
        vh1VarA.b(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onFastForward() {
        vh1 vh1VarA = a();
        if (vh1VarA == null) {
            return;
        }
        b(vh1VarA);
        this.a.getClass();
        vh1VarA.b(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final boolean onMediaButtonEvent(Intent intent) {
        vh1 vh1VarA = a();
        if (vh1VarA == null) {
            return false;
        }
        b(vh1VarA);
        boolean zE = this.a.e(intent);
        vh1VarA.b(null);
        return zE || super.onMediaButtonEvent(intent);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPause() {
        vh1 vh1VarA = a();
        if (vh1VarA == null) {
            return;
        }
        b(vh1VarA);
        this.a.f();
        vh1VarA.b(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPlay() {
        vh1 vh1VarA = a();
        if (vh1VarA == null) {
            return;
        }
        b(vh1VarA);
        this.a.g();
        vh1VarA.b(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPlayFromMediaId(String str, Bundle bundle) {
        vh1 vh1VarA = a();
        if (vh1VarA == null) {
            return;
        }
        bi1.a(bundle);
        b(vh1VarA);
        this.a.h(str);
        vh1VarA.b(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPlayFromSearch(String str, Bundle bundle) {
        vh1 vh1VarA = a();
        if (vh1VarA == null) {
            return;
        }
        bi1.a(bundle);
        b(vh1VarA);
        this.a.i(str, bundle);
        vh1VarA.b(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPlayFromUri(Uri uri, Bundle bundle) {
        vh1 vh1VarA = a();
        if (vh1VarA == null) {
            return;
        }
        bi1.a(bundle);
        b(vh1VarA);
        this.a.j(uri, bundle);
        vh1VarA.b(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPrepare() {
        vh1 vh1VarA = a();
        if (vh1VarA == null) {
            return;
        }
        b(vh1VarA);
        this.a.getClass();
        vh1VarA.b(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPrepareFromMediaId(String str, Bundle bundle) {
        vh1 vh1VarA = a();
        if (vh1VarA == null) {
            return;
        }
        bi1.a(bundle);
        b(vh1VarA);
        this.a.getClass();
        vh1VarA.b(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPrepareFromSearch(String str, Bundle bundle) {
        vh1 vh1VarA = a();
        if (vh1VarA == null) {
            return;
        }
        bi1.a(bundle);
        b(vh1VarA);
        this.a.getClass();
        vh1VarA.b(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPrepareFromUri(Uri uri, Bundle bundle) {
        vh1 vh1VarA = a();
        if (vh1VarA == null) {
            return;
        }
        bi1.a(bundle);
        b(vh1VarA);
        this.a.k(uri, bundle);
        vh1VarA.b(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onRewind() {
        vh1 vh1VarA = a();
        if (vh1VarA == null) {
            return;
        }
        b(vh1VarA);
        this.a.getClass();
        vh1VarA.b(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onSeekTo(long j) {
        vh1 vh1VarA = a();
        if (vh1VarA == null) {
            return;
        }
        b(vh1VarA);
        this.a.m(j);
        vh1VarA.b(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onSetPlaybackSpeed(float f) {
        vh1 vh1VarA = a();
        if (vh1VarA == null) {
            return;
        }
        b(vh1VarA);
        this.a.getClass();
        vh1VarA.b(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onSetRating(Rating rating) {
        vh1 vh1VarA = a();
        if (vh1VarA == null) {
            return;
        }
        b(vh1VarA);
        RatingCompat.a(rating);
        this.a.getClass();
        vh1VarA.b(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onSkipToNext() {
        vh1 vh1VarA = a();
        if (vh1VarA == null) {
            return;
        }
        b(vh1VarA);
        this.a.p();
        vh1VarA.b(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onSkipToPrevious() {
        vh1 vh1VarA = a();
        if (vh1VarA == null) {
            return;
        }
        b(vh1VarA);
        this.a.q();
        vh1VarA.b(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onSkipToQueueItem(long j) {
        vh1 vh1VarA = a();
        if (vh1VarA == null) {
            return;
        }
        b(vh1VarA);
        this.a.r(j);
        vh1VarA.b(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onStop() {
        vh1 vh1VarA = a();
        if (vh1VarA == null) {
            return;
        }
        b(vh1VarA);
        this.a.s();
        vh1VarA.b(null);
    }
}
