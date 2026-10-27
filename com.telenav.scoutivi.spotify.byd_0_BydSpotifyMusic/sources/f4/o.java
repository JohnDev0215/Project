package f4;

import android.app.Application;
import android.content.SharedPreferences;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import i3.s;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: compiled from: SpotifyMediaPlayController.java */
/* JADX INFO: loaded from: classes.dex */
public class o extends i3.m {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final String f6399m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private final String f6400n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private final CopyOnWriteArraySet<a> f6401o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private String f6402p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private volatile PlaybackStateCompat f6403q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private List<Integer> f6404r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private SharedPreferences f6405s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private boolean f6406t;

    /* JADX INFO: compiled from: SpotifyMediaPlayController.java */
    public interface a {
        void a();
    }

    public o(Application application, s sVar, i3.b bVar) {
        super(application, sVar, bVar);
        this.f6399m = "SpotifyMediaPlayController";
        this.f6400n = "KEY_IS_PLAYING";
        this.f6401o = new CopyOnWriteArraySet<>();
        this.f6402p = "";
        this.f6404r = new ArrayList();
        this.f6406t = false;
        this.f6405s = application.getSharedPreferences("SpotifyMediaPlayController", 0);
        this.f6406t = a0();
        h3.i.b("SpotifyMediaPlayController").b("a " + this.f6406t, new Object[0]);
    }

    private boolean a0() {
        return this.f6405s.getBoolean("KEY_IS_PLAYING", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c0(PlaybackStateCompat playbackStateCompat) {
        f0(y(playbackStateCompat));
    }

    private void d0() {
        this.f6404r.clear();
        this.f6404r.add(0);
        if (this.f6403q == null || this.f6403q.p() == null) {
            return;
        }
        if (this.f6403q.p().getBoolean("com.spotify.music.extra.SET_REPEAT_ONE_AVAILABLE", false)) {
            this.f6404r.add(1);
        }
        if (this.f6403q.p().getBoolean("com.spotify.music.extra.SET_REPEAT_ALL_AVAILABLE", false)) {
            this.f6404r.add(2);
        }
    }

    private synchronized void f0(boolean z6) {
        SharedPreferences.Editor editorEdit = this.f6405s.edit();
        editorEdit.putBoolean("KEY_IS_PLAYING", z6);
        editorEdit.commit();
        h3.i.b("SpotifyMediaPlayController").b("b  " + z6, new Object[0]);
    }

    public void Y(a aVar) {
        this.f6401o.add(aVar);
        aVar.a();
    }

    public List<Integer> Z() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f6404r);
        return arrayList;
    }

    public boolean b0() {
        return this.f6406t;
    }

    public void e0(a aVar) {
        this.f6401o.remove(aVar);
    }

    @Override // i3.m
    protected void u(MediaMetadataCompat mediaMetadataCompat) {
        super.u(mediaMetadataCompat);
        String strL = com.vivid.spotify.nowplaying.b.l(mediaMetadataCompat);
        if (TextUtils.isEmpty(this.f6402p) || TextUtils.isEmpty(strL) || !TextUtils.equals(this.f6402p, strL)) {
            this.f6402p = strL;
            Iterator<a> it = this.f6401o.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }
    }

    @Override // i3.m
    protected void v(final PlaybackStateCompat playbackStateCompat) {
        super.v(playbackStateCompat);
        if (this.f6403q == null || playbackStateCompat == null || this.f6403q.t() != playbackStateCompat.t()) {
            Iterator<a> it = this.f6401o.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }
        this.f6403q = playbackStateCompat;
        d0();
        e5.a.b().b(new Runnable() { // from class: f4.n
            @Override // java.lang.Runnable
            public final void run() {
                this.f6397e.c0(playbackStateCompat);
            }
        });
    }
}
