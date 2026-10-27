package spi;

import android.car.XCarRoute;
import android.car.utils.Logs;
import android.content.Context;
import android.net.Uri;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import com.alibaba.android.arouter.facade.annotation.Route;
import java.util.Objects;
import spi.a7;
import spi.r7;

/* JADX INFO: compiled from: BydCarMediaServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
@Route(path = XCarRoute.CAR_MEDIA_SERVICE)
public final class p0 extends n0 {
    public transient Context a;
    public transient String b;
    public transient String c;
    public transient String d;
    public transient String e = "";
    public transient int f = 0;
    public long g;
    public long h;

    public p0() {
        new r0();
        this.g = -1L;
        this.h = -1L;
    }

    @Override // spi.n0, com.alibaba.android.arouter.facade.template.IProvider
    public final void init(Context context) {
        super.init(context);
        Logs.i("BydCarMediaServiceImpl", "init");
        this.a = context;
    }

    @Override // spi.n0, android.car.media.IXCarMediaService
    public final void onPlayStateChanged(MediaMetadataCompat mediaMetadataCompat, PlaybackStateCompat playbackStateCompat) {
        Logs.i("BydCarMediaServiceImpl", "onPlayStateChanged");
        super.onPlayStateChanged(mediaMetadataCompat, playbackStateCompat);
        this.e = "";
        r7 r7Var = r7.a.a;
        Context context = this.a;
        Objects.requireNonNull(r7Var);
        if (playbackStateCompat != null) {
            int state = playbackStateCompat.getState();
            Logs.d("MusicWidgetUtil", "playState:" + state);
            a7.a aVar = new a7.a();
            if (state == 3) {
                aVar.a(1);
                a7.a(context, 75, aVar);
            } else if (state == 7) {
                aVar.a(2);
                a7.a(context, 255, aVar);
            } else {
                aVar.a(2);
                a7.a(context, 75, aVar);
            }
        }
        if (mediaMetadataCompat != null) {
            this.d = null;
            MediaDescriptionCompat description = mediaMetadataCompat.getDescription();
            if (description != null) {
                if (description.getTitle() != null) {
                    this.b = description.getTitle().toString();
                }
                Uri iconUri = description.getIconUri();
                if (iconUri != null) {
                    String string = iconUri.toString();
                    this.d = string;
                    Logs.d("BydCarMediaServiceImpl", string);
                }
            }
            String string2 = mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_ARTIST);
            this.c = string2;
            if (string2 == null) {
                this.c = "";
            }
        }
        if (playbackStateCompat != null) {
            this.f = playbackStateCompat.getState();
        }
    }

    @Override // spi.n0, android.car.media.IXCarMediaService
    public final void onProgressChanged(long j, long j2) {
        r7 r7Var = r7.a.a;
        if (r7Var.a) {
            r7Var.a(this.a);
            return;
        }
        long j3 = j / 1000;
        long j4 = j2 / 1000;
        if (this.g == j3 && this.h == j4) {
            return;
        }
        this.g = j3;
        this.h = j4;
        if (this.e.isEmpty()) {
            String strValueOf = String.valueOf(j2);
            this.e = strValueOf;
            String str = this.b;
            String str2 = this.c;
            String str3 = this.d;
            Context context = this.a;
            Logs.d("BydCarMediaServiceImpl", "title: " + str + "---artist: " + str2 + "---album: " + str3 + "---duration: " + strValueOf);
            a7.a aVar = new a7.a();
            int i = this.f;
            if (i == 7 || i == 0) {
                aVar.a(2);
                a7.a(context, 255, aVar);
            } else {
                aVar.b("mediatitle", str);
                aVar.b("mediaartist", str2);
                aVar.b("mediaduration", strValueOf);
                aVar.b("mediaalbum", str3);
                a7.a(context, 75, aVar);
            }
        }
        Context context2 = this.a;
        Logs.d("MusicWidgetUtil", "current: " + j + "---duration: " + j2);
        a7.a aVar2 = new a7.a();
        aVar2.b("mediaposition", String.valueOf(j));
        a7.a(context2, 75, aVar2);
    }
}
