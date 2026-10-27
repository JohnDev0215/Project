package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.view.KeyEvent;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import p.bi1;
import p.gi1;
import p.s21;
import p.vh1;
import p.wh1;

/* JADX INFO: loaded from: classes.dex */
public final class d extends a {
    public final AtomicReference b;

    public d(wh1 wh1Var) {
        this.b = new AtomicReference(wh1Var);
    }

    @Override // android.support.v4.media.session.b
    public final void A() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void B() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void C(Bundle bundle, String str) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void D(String str, Bundle bundle, MediaSessionCompat$ResultReceiverWrapper mediaSessionCompat$ResultReceiverWrapper) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final List E() {
        return null;
    }

    @Override // android.support.v4.media.session.b
    public final void F(int i, int i2) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void H(int i) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final CharSequence I() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void J(Bundle bundle, String str) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final Bundle K() {
        vh1 vh1Var = (vh1) this.b.get();
        if (vh1Var.e == null) {
            return null;
        }
        return new Bundle(vh1Var.e);
    }

    @Override // android.support.v4.media.session.b
    public final void L(Bundle bundle, String str) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final int N() {
        vh1 vh1Var = (vh1) this.b.get();
        if (vh1Var != null) {
            return vh1Var.j;
        }
        return -1;
    }

    @Override // android.support.v4.media.session.b
    public final void O(long j) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void P(int i, int i2) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final ParcelableVolumeInfo R() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void S() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final Bundle T() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void U(Uri uri, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void X(long j) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void Y(int i) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void Z(s21 s21Var) {
        vh1 vh1Var = (vh1) this.b.get();
        if (vh1Var == null) {
            return;
        }
        vh1Var.f.register(s21Var, new gi1("android.media.session.MediaController", Binder.getCallingPid(), Binder.getCallingUid()));
        synchronized (vh1Var.d) {
        }
    }

    @Override // android.support.v4.media.session.b
    public final PlaybackStateCompat a() {
        vh1 vh1Var = (vh1) this.b.get();
        if (vh1Var != null) {
            return bi1.b(vh1Var.g, vh1Var.i);
        }
        return null;
    }

    @Override // android.support.v4.media.session.b
    public final String a0() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final String b() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void b0(Bundle bundle, String str) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void d0(float f) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final boolean f0(KeyEvent keyEvent) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void g() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final long getFlags() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final MediaMetadataCompat getMetadata() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void h() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void i(RatingCompat ratingCompat, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void j() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void k(MediaDescriptionCompat mediaDescriptionCompat, int i) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void l() {
    }

    @Override // android.support.v4.media.session.b
    public final void m(boolean z) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void n(RatingCompat ratingCompat) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void next() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void o(Bundle bundle, String str) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void p(Uri uri, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void previous() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void q(MediaDescriptionCompat mediaDescriptionCompat) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final boolean r() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void s() {
    }

    @Override // android.support.v4.media.session.b
    public final void t(MediaDescriptionCompat mediaDescriptionCompat) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final PendingIntent u() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final int v() {
        vh1 vh1Var = (vh1) this.b.get();
        if (vh1Var != null) {
            return vh1Var.k;
        }
        return -1;
    }

    @Override // android.support.v4.media.session.b
    public final void w(s21 s21Var) {
        vh1 vh1Var = (vh1) this.b.get();
        if (vh1Var == null) {
            return;
        }
        vh1Var.f.unregister(s21Var);
        Binder.getCallingPid();
        Binder.getCallingUid();
        synchronized (vh1Var.d) {
        }
    }

    @Override // android.support.v4.media.session.b
    public final void x(int i) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.b
    public final void y() {
    }

    @Override // android.support.v4.media.session.b
    public final void z() {
    }
}
