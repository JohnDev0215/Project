package android.support.v4.media.session;

import android.content.Context;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.KeyEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import p.bi1;
import p.eh1;
import p.fh1;
import p.u4;
import p.ut1;
import p.vt1;

/* JADX INFO: loaded from: classes.dex */
public class c implements eh1 {
    public final MediaController c;
    public final Object e = new Object();
    public final ArrayList k = new ArrayList();
    public final HashMap l = new HashMap();
    public final MediaSessionCompat$Token m;

    public c(Context context, MediaSessionCompat$Token mediaSessionCompat$Token) {
        this.m = mediaSessionCompat$Token;
        MediaController mediaController = new MediaController(context, (MediaSession.Token) mediaSessionCompat$Token.e);
        this.c = mediaController;
        if (mediaSessionCompat$Token.a() == null) {
            MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver mediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver = new MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver(null);
            mediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver.c = new WeakReference(this);
            mediaController.sendCommand("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, mediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver);
        }
    }

    @Override // p.eh1
    public final PlaybackStateCompat a() {
        ArrayList arrayList;
        PlaybackStateCompat.CustomAction customAction;
        MediaSessionCompat$Token mediaSessionCompat$Token = this.m;
        if (mediaSessionCompat$Token.a() != null) {
            try {
                return mediaSessionCompat$Token.a().a();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", e);
            }
        }
        PlaybackState playbackState = this.c.getPlaybackState();
        if (playbackState == null) {
            return null;
        }
        List<PlaybackState.CustomAction> listJ = ut1.j(playbackState);
        if (listJ != null) {
            ArrayList arrayList2 = new ArrayList(listJ.size());
            for (PlaybackState.CustomAction customAction2 : listJ) {
                if (customAction2 != null) {
                    PlaybackState.CustomAction customAction3 = customAction2;
                    Bundle bundleL = ut1.l(customAction3);
                    bi1.a(bundleL);
                    customAction = new PlaybackStateCompat.CustomAction(ut1.f(customAction3), ut1.o(customAction3), ut1.m(customAction3), bundleL);
                    customAction.m = customAction3;
                } else {
                    customAction = null;
                }
                arrayList2.add(customAction);
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        Bundle bundleA = vt1.a(playbackState);
        bi1.a(bundleA);
        PlaybackStateCompat playbackStateCompat = new PlaybackStateCompat(ut1.r(playbackState), ut1.q(playbackState), ut1.i(playbackState), ut1.p(playbackState), ut1.g(playbackState), 0, ut1.k(playbackState), ut1.n(playbackState), arrayList, ut1.h(playbackState), bundleA);
        playbackStateCompat.t = playbackState;
        return playbackStateCompat;
    }

    public final void b() {
        if (this.m.a() == null) {
            return;
        }
        ArrayList arrayList = this.k;
        Iterator it = arrayList.iterator();
        if (!it.hasNext()) {
            arrayList.clear();
            return;
        }
        u4.z(it.next());
        this.l.put(null, new fh1());
        throw null;
    }

    @Override // p.eh1
    public final boolean p(KeyEvent keyEvent) {
        return this.c.dispatchMediaButtonEvent(keyEvent);
    }
}
