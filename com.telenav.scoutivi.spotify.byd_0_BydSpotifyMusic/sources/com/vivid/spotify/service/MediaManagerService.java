package com.vivid.spotify.service;

import android.content.Intent;
import android.hardware.bydauto.time.tsimpl.TimeManagerImpl;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Base64;
import com.vivid.spotify.MainActivity;
import com.vivid.spotify.SearchParam;
import com.vivid.spotify.b;
import com.vivid.spotify.c0;
import com.vivid.spotify.d;
import com.vivid.spotify.e;
import com.vivid.spotify.f;
import com.vivid.spotify.nowplaying.n;
import f4.o;
import h3.i;
import h3.l;
import i3.j;
import i3.m;
import i3.t;
import i3.u;
import i3.w;
import i3.x;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class MediaManagerService extends com.vivid.spotify.service.a {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    j f5581l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    m f5582m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    f4.b f5583n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    c0 f5584o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    com.vivid.spotify.b f5585p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private PlaybackStateCompat f5586q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private MediaMetadataCompat f5587r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private long f5588s;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final String f5578i = "MediaManagerService";

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final int f5579j = 20000;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final int f5580k = 2000;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private CopyOnWriteArrayList<d> f5589t = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private m.e f5590u = new a();

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private MediaControllerCompat.a f5591v = new b();

    class a implements m.e {
        a() {
        }

        @Override // i3.m.e
        public void a(int i7) {
            MediaManagerService.this.f5588s = i7;
            MediaManagerService mediaManagerService = MediaManagerService.this;
            mediaManagerService.w(Long.valueOf(mediaManagerService.f5588s));
        }
    }

    class b extends MediaControllerCompat.a {
        b() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void d(MediaMetadataCompat mediaMetadataCompat) {
            MediaManagerService.this.f5587r = mediaMetadataCompat;
            if (mediaMetadataCompat == null) {
                MediaManagerService mediaManagerService = MediaManagerService.this;
                mediaManagerService.f5587r = mediaManagerService.f5582m.n();
                i.b("MediaManagerService").d("onMetadataChanged metadata is null", new Object[0]);
            }
            String strD = t.d(mediaMetadataCompat);
            if (TextUtils.isEmpty(strD)) {
                strD = t.a(mediaMetadataCompat);
            }
            String str = strD;
            String strJ = t.j(mediaMetadataCompat);
            if (TextUtils.equals(strJ, MediaManagerService.this.getString(w.unknown_song))) {
                strJ = MediaManagerService.this.getString(w.slogan);
            }
            MediaManagerService.this.u(strJ, str, i3.c.b(mediaMetadataCompat), t.f(mediaMetadataCompat));
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void e(PlaybackStateCompat playbackStateCompat) {
            MediaManagerService.this.f5586q = playbackStateCompat;
            if (playbackStateCompat != null) {
                i.b("MediaManagerService").b("ns " + playbackStateCompat, new Object[0]);
            } else {
                i.b("MediaManagerService").d("ns is null", new Object[0]);
            }
            MediaManagerService.this.v(playbackStateCompat);
        }
    }

    class c extends f.a {
        c() {
        }

        private x T(SearchParam searchParam) {
            if (searchParam == null) {
                return null;
            }
            String strK = searchParam.k();
            strK.hashCode();
            switch (strK) {
            }
            return null;
        }

        private String U() {
            return MediaManagerService.this.f5582m.n().m("android.media.metadata.MEDIA_ID");
        }

        private String V() {
            return MediaManagerService.this.f5583n.d().m("android.media.metadata.MEDIA_URI");
        }

        private boolean W() {
            return MediaManagerService.this.f5581l.h();
        }

        private boolean X(e eVar) {
            if (!Y()) {
                try {
                    Thread.sleep(150L);
                } catch (InterruptedException e7) {
                    i.b("MediaManagerService").e(e7, "isReady", new Object[0]);
                }
                if (!Y()) {
                    if (eVar != null) {
                        eVar.N(1);
                    }
                    i.b("MediaManagerService").d("isServiceConnected  false", new Object[0]);
                    return false;
                }
            }
            if (W()) {
                return true;
            }
            if (eVar != null) {
                eVar.N(2);
            }
            i.b("MediaManagerService").d("11  false", new Object[0]);
            return false;
        }

        private boolean Y() {
            return MediaManagerService.this.f5581l.i();
        }

        private void Z() {
            Intent intent = new Intent(MediaManagerService.this.getApplicationContext(), (Class<?>) MainActivity.class);
            intent.addFlags(268435456);
            intent.addFlags(67108864);
            intent.addFlags(536870912);
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            MediaManagerService.this.getApplicationContext().startActivity(intent);
        }

        private void a0(d dVar) {
            if (MediaManagerService.this.f5587r == null) {
                i.b("MediaManagerService").d("notifyMetadataChanged mediaMetadataCompat is null", new Object[0]);
                return;
            }
            String strD = t.d(MediaManagerService.this.f5587r);
            if (TextUtils.isEmpty(strD)) {
                strD = t.a(MediaManagerService.this.f5587r);
            }
            String str = strD;
            String strJ = t.j(MediaManagerService.this.f5587r);
            if (TextUtils.equals(strJ, MediaManagerService.this.getString(w.unknown_song))) {
                strJ = MediaManagerService.this.getString(w.slogan);
            }
            dVar.P(strJ, str, i3.c.b(MediaManagerService.this.f5587r), t.f(MediaManagerService.this.f5587r));
        }

        private void b0(d dVar) {
            MediaManagerService mediaManagerService = MediaManagerService.this;
            dVar.D(mediaManagerService.r(mediaManagerService.f5586q));
        }

        private void c0(d dVar) {
            dVar.f(MediaManagerService.this.f5588s);
        }

        @Override // com.vivid.spotify.f
        public void B(e eVar) {
            i.b("MediaManagerService").b("10", new Object[0]);
            MediaManagerService.this.f5584o.b("");
            if (X(eVar)) {
                if (TextUtils.isEmpty(V())) {
                    i.b("MediaManagerService").d("10  mediaUri is null", new Object[0]);
                    if (eVar != null) {
                        eVar.N(4);
                        return;
                    }
                    return;
                }
                PlaybackStateCompat playbackStateCompat = MediaManagerService.this.f5586q;
                if (playbackStateCompat == null || (playbackStateCompat.c() & 256) == 0) {
                    l lVarB = i.b("MediaManagerService");
                    StringBuilder sb = new StringBuilder();
                    sb.append("10  unSupport state is ");
                    sb.append(playbackStateCompat == null);
                    lVarB.d(sb.toString(), new Object[0]);
                    if (eVar != null) {
                        eVar.N(12);
                        return;
                    }
                    return;
                }
                boolean zO = MediaManagerService.this.f5582m.O(0L);
                if (zO) {
                    zO = MediaManagerService.this.f5582m.I();
                }
                if (eVar != null) {
                    if (zO) {
                        eVar.u();
                    } else {
                        eVar.N(1);
                    }
                }
            }
        }

        @Override // com.vivid.spotify.f
        public void C(d dVar) {
            boolean zRemove = MediaManagerService.this.f5589t.remove(dVar);
            i.b("MediaManagerService").b("removeMediaControllerCallback " + zRemove, new Object[0]);
        }

        @Override // com.vivid.spotify.f
        public void E(e eVar) {
            x(eVar);
            MediaManagerService.this.f5584o.b("");
        }

        @Override // com.vivid.spotify.f
        public void F(e eVar) {
            i.b("MediaManagerService").b(TimeManagerImpl.TIME_FORMAT_TWELVE, new Object[0]);
            MediaManagerService.this.f5584o.b("");
            if (X(eVar)) {
                MediaManagerService.this.f5585p.d(b.EnumC0056b.COMMAND_CLOSE);
                if (eVar != null) {
                    eVar.u();
                }
            }
        }

        @Override // com.vivid.spotify.f
        public void G(e eVar) {
            i.b("MediaManagerService").b("7", new Object[0]);
            MediaManagerService.this.f5584o.b("");
            if (X(eVar)) {
                String strV = V();
                if (TextUtils.isEmpty(strV)) {
                    i.b("MediaManagerService").d("7  mediaUri is null", new Object[0]);
                    if (eVar != null) {
                        eVar.N(4);
                        return;
                    }
                    return;
                }
                try {
                    boolean zS = MediaManagerService.this.s();
                    i.b("MediaManagerService").b("7 is " + zS, new Object[0]);
                    if (!zS) {
                        if (eVar != null) {
                            eVar.N(10);
                            return;
                        }
                        return;
                    }
                    String strU = U();
                    i.b("MediaManagerService").b("7  id = " + strU + "   uri = " + strV, new Object[0]);
                    if (MediaManagerService.this.t(strU)) {
                        MediaManagerService.this.f5582m.P(n.UnlikeTrack.getAction(), new Bundle());
                    } else {
                        MediaManagerService.this.f5582m.P(n.UnlikeEpisode.getAction(), new Bundle());
                    }
                    if (eVar != null) {
                        eVar.u();
                    }
                } catch (Exception e7) {
                    i.b("MediaManagerService").e(e7, "7 1", new Object[0]);
                    if (eVar != null) {
                        eVar.N(6);
                    }
                }
            }
        }

        @Override // com.vivid.spotify.f
        public void M(e eVar) {
            i.b("MediaManagerService").b("6", new Object[0]);
            MediaManagerService.this.f5584o.b("");
            if (X(eVar)) {
                String strV = V();
                if (TextUtils.isEmpty(strV)) {
                    i.b("MediaManagerService").d("6  mediaUri is null", new Object[0]);
                    if (eVar != null) {
                        eVar.N(4);
                        return;
                    }
                    return;
                }
                try {
                    boolean zS = MediaManagerService.this.s();
                    i.b("MediaManagerService").b("6 is " + zS, new Object[0]);
                    if (zS) {
                        if (eVar != null) {
                            eVar.N(9);
                            return;
                        }
                        return;
                    }
                    String strU = U();
                    i.b("MediaManagerService").b("6  id = " + strU + "   uri = " + strV, new Object[0]);
                    if (MediaManagerService.this.t(strU)) {
                        MediaManagerService.this.f5582m.P(n.LikeTrack.getAction(), new Bundle());
                    } else {
                        MediaManagerService.this.f5582m.P(n.LikeEpisode.getAction(), new Bundle());
                    }
                    if (eVar != null) {
                        eVar.u();
                    }
                } catch (Exception e7) {
                    i.b("MediaManagerService").e(e7, "6 1", new Object[0]);
                    if (eVar != null) {
                        eVar.N(6);
                    }
                }
            }
        }

        @Override // com.vivid.spotify.f
        public void O(e eVar) {
            if (!X(eVar) || eVar == null) {
                return;
            }
            eVar.u();
        }

        @Override // com.vivid.spotify.f
        public void R(e eVar) {
            i.b("MediaManagerService").b("4", new Object[0]);
            MediaManagerService.this.f5584o.b("");
            if (X(eVar)) {
                if (TextUtils.isEmpty(V())) {
                    i.b("MediaManagerService").d("4  mediaUri is null", new Object[0]);
                    if (eVar != null) {
                        eVar.N(4);
                        return;
                    }
                    return;
                }
                PlaybackStateCompat playbackStateCompat = MediaManagerService.this.f5586q;
                if (playbackStateCompat == null || (playbackStateCompat.c() & 16) == 0) {
                    if (eVar != null) {
                        eVar.N(13);
                        return;
                    }
                    return;
                }
                Long lA = u.a(MediaManagerService.this.f5582m.q());
                i.b("MediaManagerService").b("4  duration :  " + lA, new Object[0]);
                boolean zT = MediaManagerService.this.f5582m.T();
                if (zT && lA.longValue() > 2000) {
                    zT = MediaManagerService.this.f5582m.T();
                    i.b("MediaManagerService").b("4  second :  " + zT, new Object[0]);
                }
                if (eVar != null) {
                    if (zT) {
                        eVar.u();
                    } else {
                        eVar.N(1);
                    }
                }
            }
        }

        @Override // com.vivid.spotify.f
        public void h(e eVar) {
            i.b("MediaManagerService").b("5", new Object[0]);
            MediaManagerService.this.f5584o.b("");
            if (X(eVar)) {
                if (TextUtils.isEmpty(V())) {
                    i.b("MediaManagerService").d("5  mediaUri is null", new Object[0]);
                    if (eVar != null) {
                        eVar.N(4);
                        return;
                    }
                    return;
                }
                List<MediaSessionCompat.QueueItem> listR = MediaManagerService.this.f5582m.r();
                if (listR == null) {
                    i.b("MediaManagerService").d("5  queues is null", new Object[0]);
                } else {
                    i.b("MediaManagerService").d("5  queues size is " + listR.size(), new Object[0]);
                }
                PlaybackStateCompat playbackStateCompat = MediaManagerService.this.f5586q;
                if (playbackStateCompat == null || (playbackStateCompat.c() & 32) == 0) {
                    if (eVar != null) {
                        eVar.N(8);
                        return;
                    }
                    return;
                }
                boolean zS = MediaManagerService.this.f5582m.S();
                if (eVar != null) {
                    if (zS) {
                        eVar.u();
                    } else {
                        eVar.N(1);
                    }
                }
            }
        }

        @Override // com.vivid.spotify.f
        public void i(e eVar) {
            i.b("MediaManagerService").b("3", new Object[0]);
            MediaManagerService.this.f5584o.b("");
            if (X(eVar)) {
                if (TextUtils.isEmpty(V())) {
                    i.b("MediaManagerService").d("3  mediaUri is null", new Object[0]);
                    if (eVar != null) {
                        eVar.N(4);
                        return;
                    }
                    return;
                }
                boolean zW = MediaManagerService.this.f5582m.W();
                if (eVar != null) {
                    if (zW) {
                        eVar.u();
                    } else {
                        eVar.N(1);
                    }
                }
            }
        }

        @Override // com.vivid.spotify.f
        public String j() {
            try {
                return MediaManagerService.this.getApplication().getResources().getString(MediaManagerService.this.getApplication().getPackageManager().getPackageInfo(MediaManagerService.this.getApplication().getPackageName(), 0).applicationInfo.labelRes);
            } catch (Throwable th) {
                i.b("MediaManagerService").e(th, "getAppName", new Object[0]);
                return "";
            }
        }

        @Override // com.vivid.spotify.f
        public void l(int i7, e eVar) {
            i.b("MediaManagerService").b("8", new Object[0]);
            MediaManagerService.this.f5584o.b("");
            if (X(eVar)) {
                if (!((o) MediaManagerService.this.f5582m).Z().contains(Integer.valueOf(i7))) {
                    i.b("MediaManagerService").d("8  unsupported   " + i7, new Object[0]);
                    if (eVar != null) {
                        eVar.N(3);
                        return;
                    }
                    return;
                }
                int iS = MediaManagerService.this.f5582m.s();
                i.b("MediaManagerService").b("8 curren = " + iS + " target = " + i7, new Object[0]);
                if (iS == i7) {
                    if (eVar != null) {
                        eVar.N(11);
                        return;
                    }
                    return;
                }
                boolean zQ = MediaManagerService.this.f5582m.Q(i7);
                if (eVar != null) {
                    if (zQ) {
                        eVar.u();
                    } else {
                        eVar.N(1);
                    }
                }
            }
        }

        @Override // com.vivid.spotify.f
        public int n() {
            if (!X(null)) {
                return 7;
            }
            PlaybackStateCompat playbackStateCompatQ = MediaManagerService.this.f5582m.q();
            if (playbackStateCompatQ != null) {
                return playbackStateCompatQ.t();
            }
            i.b("MediaManagerService").d("10 playbackStateCompat is null", new Object[0]);
            return 7;
        }

        @Override // com.vivid.spotify.f
        public void r(String str, SearchParam searchParam, e eVar) {
            l lVarB = i.b("MediaManagerService");
            StringBuilder sb = new StringBuilder();
            sb.append("15 searchBean is null ");
            sb.append(searchParam == null);
            lVarB.b(sb.toString(), new Object[0]);
            MediaManagerService.this.f5584o.b("");
            if (X(eVar)) {
                boolean zJ = MediaManagerService.this.f5582m.J(str, T(searchParam));
                if (eVar != null) {
                    if (zJ) {
                        eVar.u();
                    } else {
                        eVar.N(1);
                    }
                }
            }
        }

        @Override // com.vivid.spotify.f
        public void s(e eVar) {
            i.b("MediaManagerService").b("2", new Object[0]);
            MediaManagerService.this.f5584o.b("");
            if (X(eVar)) {
                if (TextUtils.isEmpty(V())) {
                    i.b("MediaManagerService").d("2  mediaUri is null", new Object[0]);
                    if (eVar != null) {
                        eVar.N(4);
                        return;
                    }
                    return;
                }
                boolean zH = MediaManagerService.this.f5582m.H();
                if (eVar != null) {
                    if (zH) {
                        eVar.u();
                    } else {
                        eVar.N(1);
                    }
                }
            }
        }

        @Override // com.vivid.spotify.f
        public void t(e eVar) {
            i.b("MediaManagerService").b("11", new Object[0]);
            MediaManagerService.this.f5584o.b("");
            if (X(eVar)) {
                if ("#media_id_useless#".equals(U())) {
                    i.b("MediaManagerService").d("11  mediaUri is null", new Object[0]);
                    if (eVar != null) {
                        eVar.N(12);
                        return;
                    }
                    return;
                }
                PlaybackStateCompat playbackStateCompat = MediaManagerService.this.f5586q;
                if (playbackStateCompat == null || (playbackStateCompat.c() & 4096) == 0) {
                    l lVarB = i.b("MediaManagerService");
                    StringBuilder sb = new StringBuilder();
                    sb.append("11  unSupport state is ");
                    sb.append(playbackStateCompat == null);
                    lVarB.d(sb.toString(), new Object[0]);
                    if (eVar != null) {
                        eVar.N(12);
                        return;
                    }
                    return;
                }
                List<MediaSessionCompat.QueueItem> listR = MediaManagerService.this.f5582m.r();
                if (listR == null || listR.isEmpty()) {
                    i.b("MediaManagerService").d("11  queues is null", new Object[0]);
                    if (eVar != null) {
                        eVar.N(12);
                        return;
                    }
                    return;
                }
                Z();
                MediaManagerService.this.f5585p.c(b.a.COMMAND_OPEN);
                MediaManagerService.this.f5585p.d(b.EnumC0056b.COMMAND_OPEN);
                if (eVar != null) {
                    eVar.u();
                }
            }
        }

        @Override // com.vivid.spotify.f
        public void w(d dVar) {
            boolean zAddIfAbsent = MediaManagerService.this.f5589t.addIfAbsent(dVar);
            i.b("MediaManagerService").b("addMediaControllerCallback " + zAddIfAbsent, new Object[0]);
            b0(dVar);
            a0(dVar);
            c0(dVar);
        }

        @Override // com.vivid.spotify.f
        public void x(e eVar) {
            i.b("MediaManagerService").b("1", new Object[0]);
            MediaManagerService.this.f5584o.b("");
            if (X(eVar)) {
                i.b("MediaManagerService").b("getCurrentMediaUri", new Object[0]);
                if (TextUtils.isEmpty(V())) {
                    i.b("MediaManagerService").d("1  mediaUri is null", new Object[0]);
                    if (eVar != null) {
                        eVar.N(4);
                        return;
                    }
                    return;
                }
                i.b("MediaManagerService").b("getController", new Object[0]);
                boolean zI = MediaManagerService.this.f5582m.I();
                if (eVar != null) {
                    if (zI) {
                        eVar.u();
                    } else {
                        eVar.N(1);
                    }
                }
                i.b("MediaManagerService").b("finish", new Object[0]);
            }
        }

        @Override // com.vivid.spotify.f
        public void y(e eVar) {
            i.b("MediaManagerService").b("13", new Object[0]);
            MediaManagerService.this.f5584o.b("");
            Intent intent = new Intent(MediaManagerService.this.getApplicationContext(), (Class<?>) MainActivity.class);
            intent.addFlags(268435456);
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            MediaManagerService.this.getApplicationContext().startActivity(intent);
            if (eVar != null) {
                eVar.u();
            }
        }

        @Override // com.vivid.spotify.f
        public void z(int i7, e eVar) {
            i.b("MediaManagerService").b("9", new Object[0]);
            MediaManagerService.this.f5584o.b("");
            if (X(eVar)) {
                PlaybackStateCompat playbackStateCompatQ = MediaManagerService.this.f5582m.q();
                if (playbackStateCompatQ != null && playbackStateCompatQ.p() != null) {
                    boolean z6 = playbackStateCompatQ.p().getBoolean("com.spotify.music.extra.SHUFFLE_FORCE_ENABLED", false);
                    i.b("MediaManagerService").g("9 ForcedOn = " + z6);
                    if (z6) {
                        if (eVar != null) {
                            eVar.N(3);
                            return;
                        }
                        return;
                    }
                }
                if (i7 != 0 && i7 != 1) {
                    i.b("MediaManagerService").d("9  unsupported   " + i7, new Object[0]);
                    if (eVar != null) {
                        eVar.N(3);
                        return;
                    }
                    return;
                }
                int iT = MediaManagerService.this.f5582m.t();
                i.b("MediaManagerService").b("9 current = " + iT + " target = " + i7, new Object[0]);
                if (iT == i7) {
                    if (eVar != null) {
                        eVar.N(11);
                        return;
                    }
                    return;
                }
                boolean zR = MediaManagerService.this.f5582m.R(i7);
                if (eVar != null) {
                    if (zR) {
                        eVar.u();
                    } else {
                        eVar.N(1);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle r(PlaybackStateCompat playbackStateCompat) {
        Bundle bundle = new Bundle();
        if (playbackStateCompat == null) {
            bundle.putInt("KEY_PLAY_STATE", 7);
        } else {
            bundle.putInt("KEY_PLAY_STATE", playbackStateCompat.t());
        }
        ArrayList arrayList = new ArrayList();
        if (playbackStateCompat != null && playbackStateCompat.j() != null) {
            arrayList.addAll(playbackStateCompat.j());
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            PlaybackStateCompat.CustomAction customAction = (PlaybackStateCompat.CustomAction) it.next();
            i.b("MediaManagerService").b("customAction " + customAction.c(), new Object[0]);
            if (TextUtils.equals(n.LikeEpisode.getAction(), customAction.c())) {
                bundle.putBoolean("KEY_IS_EPISODE_COLLECTED", false);
                break;
            }
            if (TextUtils.equals(n.UnlikeEpisode.getAction(), customAction.c())) {
                bundle.putBoolean("KEY_IS_EPISODE_COLLECTED", true);
                break;
            }
            if (TextUtils.equals(n.LikeTrack.getAction(), customAction.c())) {
                bundle.putBoolean("KEY_IS_TRACK_COLLECTED", false);
                break;
            }
            if (TextUtils.equals(n.UnlikeTrack.getAction(), customAction.c())) {
                bundle.putBoolean("KEY_IS_TRACK_COLLECTED", true);
                break;
            }
        }
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean s() throws Exception {
        if (this.f5586q == null) {
            this.f5586q = this.f5582m.q();
        }
        if (this.f5586q == null) {
            throw new Exception("get state error");
        }
        for (PlaybackStateCompat.CustomAction customAction : new ArrayList(this.f5586q.j())) {
            i.b("MediaManagerService").b("customAction " + customAction.c(), new Object[0]);
            if (TextUtils.equals(n.LikeEpisode.getAction(), customAction.c())) {
                return false;
            }
            if (TextUtils.equals(n.UnlikeEpisode.getAction(), customAction.c())) {
                return true;
            }
            if (TextUtils.equals(n.LikeTrack.getAction(), customAction.c())) {
                return false;
            }
            if (TextUtils.equals(n.UnlikeTrack.getAction(), customAction.c())) {
                return true;
            }
        }
        throw new Exception("get state error");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean t(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("spotify:track");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(String str, String str2, String str3, long j7) {
        i.b("MediaManagerService").b("nmc t:" + Base64.encodeToString(str.getBytes(), 0) + ",s:" + Base64.encodeToString(str2.getBytes(), 0) + ",c:" + Base64.encodeToString(str3.getBytes(), 0) + ",d:" + j7, new Object[0]);
        ArrayList arrayList = new ArrayList();
        for (d dVar : this.f5589t) {
            try {
                dVar.P(str, str2, str3, j7);
            } catch (RemoteException e7) {
                i.b("MediaManagerService").e(e7, "nmc error", new Object[0]);
                arrayList.add(dVar);
            }
        }
        this.f5589t.removeAll(arrayList);
        i.b("MediaManagerService").b("nmc current callback count: " + this.f5589t.size(), new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v(PlaybackStateCompat playbackStateCompat) {
        ArrayList arrayList = new ArrayList();
        Bundle bundleR = r(playbackStateCompat);
        for (d dVar : this.f5589t) {
            try {
                dVar.D(bundleR);
            } catch (RemoteException e7) {
                i.b("MediaManagerService").e(e7, "npsc error", new Object[0]);
                arrayList.add(dVar);
            }
        }
        this.f5589t.removeAll(arrayList);
        i.b("MediaManagerService").b("npsc  current callback count: " + this.f5589t.size(), new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w(Long l7) {
        i.b("MediaManagerService").b("npc position: " + l7, new Object[0]);
        ArrayList arrayList = new ArrayList();
        for (d dVar : this.f5589t) {
            try {
                dVar.f(l7.longValue());
            } catch (RemoteException e7) {
                i.b("MediaManagerService").e(e7, "npc error", new Object[0]);
                arrayList.add(dVar);
            }
        }
        this.f5589t.removeAll(arrayList);
        i.b("MediaManagerService").b("npc  callback count:" + this.f5589t.size(), new Object[0]);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        this.f5589t.clear();
        return new c();
    }

    @Override // com.vivid.spotify.service.a, android.app.Service
    public void onCreate() {
        super.onCreate();
        i.b("MediaManagerService").g("onCreate");
        this.f5582m.h(this.f5591v);
        this.f5582m.i(this.f5590u);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        i.b("MediaManagerService").g("onDestroy");
        this.f5582m.M(this.f5591v);
        this.f5582m.N(this.f5590u);
    }
}
