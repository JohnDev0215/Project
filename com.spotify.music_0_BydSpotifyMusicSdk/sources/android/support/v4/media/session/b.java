package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IInterface;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.view.KeyEvent;
import java.util.List;
import p.s21;

/* JADX INFO: loaded from: classes.dex */
public interface b extends IInterface {
    void A();

    void B();

    void C(Bundle bundle, String str);

    void D(String str, Bundle bundle, MediaSessionCompat$ResultReceiverWrapper mediaSessionCompat$ResultReceiverWrapper);

    List E();

    void F(int i, int i2);

    void H(int i);

    CharSequence I();

    void J(Bundle bundle, String str);

    Bundle K();

    void L(Bundle bundle, String str);

    int N();

    void O(long j);

    void P(int i, int i2);

    ParcelableVolumeInfo R();

    void S();

    Bundle T();

    void U(Uri uri, Bundle bundle);

    void X(long j);

    void Y(int i);

    void Z(s21 s21Var);

    PlaybackStateCompat a();

    String a0();

    String b();

    void b0(Bundle bundle, String str);

    void d0(float f);

    boolean f0(KeyEvent keyEvent);

    void g();

    long getFlags();

    MediaMetadataCompat getMetadata();

    void h();

    void i(RatingCompat ratingCompat, Bundle bundle);

    void j();

    void k(MediaDescriptionCompat mediaDescriptionCompat, int i);

    void l();

    void m(boolean z);

    void n(RatingCompat ratingCompat);

    void next();

    void o(Bundle bundle, String str);

    void p(Uri uri, Bundle bundle);

    void previous();

    void q(MediaDescriptionCompat mediaDescriptionCompat);

    boolean r();

    void s();

    void t(MediaDescriptionCompat mediaDescriptionCompat);

    PendingIntent u();

    int v();

    void w(s21 s21Var);

    void x(int i);

    void y();

    void z();
}
