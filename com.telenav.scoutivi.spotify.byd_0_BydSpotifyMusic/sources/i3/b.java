package i3;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: compiled from: MediaErrorHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends MediaControllerCompat.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Context f6919d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private PlaybackStateCompat f6920e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final CopyOnWriteArraySet<a> f6921f = new CopyOnWriteArraySet<>();

    /* JADX INFO: compiled from: MediaErrorHandler.java */
    public interface a {
        void a(String str);

        void b(String str, Bundle bundle);
    }

    public b(Application application) {
        this.f6919d = application;
    }

    @Override // android.support.v4.media.session.MediaControllerCompat.a
    public synchronized void e(PlaybackStateCompat playbackStateCompat) {
        h3.i.b("MediaErrorHandler").g("MediaErrorHandler:" + playbackStateCompat);
        this.f6920e = playbackStateCompat;
        if (playbackStateCompat != null) {
            int iK = playbackStateCompat.k();
            CharSequence charSequenceM = playbackStateCompat.m();
            h3.i.b("MediaErrorHandler").i("errorCode:%s, errorMsg:%s", Integer.valueOf(iK), charSequenceM);
            if (iK == 3) {
                for (a aVar : this.f6921f) {
                    String string = "";
                    if (charSequenceM != null) {
                        string = charSequenceM.toString();
                    }
                    aVar.b(string, playbackStateCompat.p());
                }
                return;
            }
            if (!TextUtils.isEmpty(charSequenceM) && !q(charSequenceM.toString())) {
                Iterator<a> it = this.f6921f.iterator();
                while (it.hasNext()) {
                    it.next().a(charSequenceM.toString());
                }
            }
        }
    }

    public void o(a aVar) {
        this.f6921f.add(aVar);
    }

    public void p(a aVar) {
        this.f6921f.remove(aVar);
    }

    public boolean q(String str) {
        return false;
    }
}
