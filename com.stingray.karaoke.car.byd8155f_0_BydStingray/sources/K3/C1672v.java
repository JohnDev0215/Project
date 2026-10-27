package k3;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.TextureView;
import e2.C1195c;
import e2.C1202j;
import g2.C1290c;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/* JADX INFO: renamed from: k3.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1672v implements e2.W {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e2.e0 f18135a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f18136b;
    public final InterfaceC1670u c;
    public final InterfaceC1668t d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Handler f18137e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f18138f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f18139g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final C1674w f18140h;

    public C1672v(Context context, E1 e12, Bundle bundle, InterfaceC1668t interfaceC1668t, Looper looper, C1674w c1674w, io.sentry.internal.debugmeta.c cVar) {
        InterfaceC1670u c1628b0;
        AbstractC1346c.h(context, "context must not be null");
        AbstractC1346c.h(e12, "token must not be null");
        AbstractC1346c.t("MediaController", "Init " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.5.0] [" + AbstractC1343A.f15197e + "]");
        this.f18135a = new e2.e0();
        this.f18138f = -9223372036854775807L;
        this.d = interfaceC1668t;
        this.f18137e = new Handler(looper);
        this.f18140h = c1674w;
        if (e12.f17758a.n()) {
            cVar.getClass();
            c1628b0 = new C1650j0(context, this, e12, bundle, looper, cVar);
        } else {
            c1628b0 = new C1628b0(context, this, e12, bundle, looper);
        }
        this.c = c1628b0;
        c1628b0.S0();
    }

    public static void W0(h5.y yVar) {
        if (yVar.cancel(false)) {
            return;
        }
        try {
            ((C1672v) l9.l.N(yVar)).a();
        } catch (CancellationException | ExecutionException e10) {
            AbstractC1346c.A("MediaController", "MediaController future failed (so we couldn't release it)", e10);
        }
    }

    @Override // e2.W
    public final e2.S A() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        return !interfaceC1670u.v() ? e2.S.f13999b : interfaceC1670u.A();
    }

    @Override // e2.W
    public final void A0(int i7, int i10) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.A0(i7, i10);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring removeMediaItems().");
        }
    }

    @Override // e2.W
    public final long B() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.B();
        }
        return 0L;
    }

    @Override // e2.W
    public final void B0(int i7) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.B0(i7);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring setDeviceVolume().");
        }
    }

    @Override // e2.W
    public final boolean C() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        return interfaceC1670u.v() && interfaceC1670u.C();
    }

    @Override // e2.W
    public final void C0() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.C0();
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring seekToNext().");
        }
    }

    @Override // e2.W
    public final void D() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.D();
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring clearMediaItems().");
        }
    }

    @Override // e2.W
    public final void D0(e2.U u4) {
        AbstractC1346c.h(u4, "listener must not be null");
        this.c.D0(u4);
    }

    @Override // e2.W
    public final void E(e2.F f10, long j3) {
        Z0();
        AbstractC1346c.h(f10, "mediaItems must not be null");
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.E(f10, j3);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring setMediaItem().");
        }
    }

    @Override // e2.W
    public final void E0() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.E0();
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring seekForward().");
        }
    }

    @Override // e2.W
    public final void F(boolean z6) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.F(z6);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring setShuffleMode().");
        }
    }

    @Override // e2.W
    public final void F0(TextureView textureView) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.F0(textureView);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring setVideoTextureView().");
        }
    }

    @Override // e2.W
    public final void G() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.G();
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring seekToNextMediaItem().");
        }
    }

    @Override // e2.W
    public final void G0(e2.I i7) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.G0(i7);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring setPlaylistMetadata().");
        }
    }

    @Override // e2.W
    public final void H(e2.U u4) {
        Z0();
        AbstractC1346c.h(u4, "listener must not be null");
        this.c.H(u4);
    }

    @Override // e2.W
    public final void H0() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.H0();
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring seekBack().");
        }
    }

    @Override // e2.W
    public final void I(int i7) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.I(i7);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring decreaseDeviceVolume().");
        }
    }

    @Override // e2.W
    public final void I0(long j3, int i7) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.I0(j3, i7);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring seekTo().");
        }
    }

    @Override // e2.W
    public final e2.n0 J() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        return interfaceC1670u.v() ? interfaceC1670u.J() : e2.n0.f14225b;
    }

    @Override // e2.W
    public final void J0(float f10) {
        Z0();
        AbstractC1346c.b("volume must be between 0 and 1", f10 >= 0.0f && f10 <= 1.0f);
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.J0(f10);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring setVolume().");
        }
    }

    @Override // e2.W
    public final int K() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.K();
        }
        return 0;
    }

    @Override // e2.W
    public final e2.I K0() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        return interfaceC1670u.v() ? interfaceC1670u.K0() : e2.I.f13914K;
    }

    @Override // e2.W
    public final long L() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.L();
        }
        return 0L;
    }

    @Override // e2.W
    public final void L0(List list) {
        Z0();
        AbstractC1346c.h(list, "mediaItems must not be null");
        for (int i7 = 0; i7 < list.size(); i7++) {
            AbstractC1346c.b("items must not contain null, index=" + i7, list.get(i7) != null);
        }
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.L0(list);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring setMediaItems().");
        }
    }

    @Override // e2.W
    public final boolean M() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        return interfaceC1670u.v() && interfaceC1670u.M();
    }

    @Override // e2.W
    public final void M0() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.M0();
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring seekToPrevious().");
        }
    }

    @Override // e2.W
    public final e2.I N() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        return interfaceC1670u.v() ? interfaceC1670u.N() : e2.I.f13914K;
    }

    @Override // e2.W
    public final long N0() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.N0();
        }
        return 0L;
    }

    @Override // e2.W
    public final boolean O() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        return interfaceC1670u.v() && interfaceC1670u.O();
    }

    @Override // e2.W
    public final long O0() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.O0();
        }
        return 0L;
    }

    @Override // e2.W
    public final long P() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.P();
        }
        return -9223372036854775807L;
    }

    @Override // e2.W
    public final boolean P0() {
        Z0();
        e2.f0 f0VarT0 = t0();
        return !f0VarT0.p() && f0VarT0.m(b0(), this.f18135a, 0L).f14090h;
    }

    @Override // e2.W
    public final int Q() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.Q();
        }
        return -1;
    }

    @Override // e2.W
    public final e2.F Q0() {
        e2.f0 f0VarT0 = t0();
        if (f0VarT0.p()) {
            return null;
        }
        return f0VarT0.m(b0(), this.f18135a, 0L).c;
    }

    @Override // e2.W
    public final C1290c R() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        return interfaceC1670u.v() ? interfaceC1670u.R() : C1290c.c;
    }

    @Override // e2.W
    public final boolean R0(int i7) {
        return A().a(i7);
    }

    @Override // e2.W
    public final void S(TextureView textureView) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.S(textureView);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring clearVideoTextureView().");
        }
    }

    @Override // e2.W
    public final boolean S0() {
        Z0();
        e2.f0 f0VarT0 = t0();
        return !f0VarT0.p() && f0VarT0.m(b0(), this.f18135a, 0L).f14091i;
    }

    @Override // e2.W
    public final e2.q0 T() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        return interfaceC1670u.v() ? interfaceC1670u.T() : e2.q0.d;
    }

    @Override // e2.W
    public final Looper T0() {
        return this.f18137e.getLooper();
    }

    @Override // e2.W
    public final void U(C1195c c1195c, boolean z6) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.U(c1195c, z6);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring setAudioAttributes().");
        }
    }

    @Override // e2.W
    public final boolean U0() {
        return false;
    }

    @Override // e2.W
    public final void V(e2.F f10) {
        Z0();
        AbstractC1346c.h(f10, "mediaItems must not be null");
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.V(f10);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring setMediaItems().");
        }
    }

    @Override // e2.W
    public final boolean V0() {
        Z0();
        e2.f0 f0VarT0 = t0();
        return !f0VarT0.p() && f0VarT0.m(b0(), this.f18135a, 0L).a();
    }

    @Override // e2.W
    public final void W() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.W();
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring seekToPreviousMediaItem().");
        }
    }

    @Override // e2.W
    public final float X() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.X();
        }
        return 1.0f;
    }

    public final void X0(Runnable runnable) {
        AbstractC1343A.K(this.f18137e, runnable);
    }

    @Override // e2.W
    public final void Y() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.Y();
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring seekTo().");
        }
    }

    public final h5.y Y0(y1 y1Var, Bundle bundle) {
        Z0();
        AbstractC1346c.b("command must be a custom command", y1Var.f18167a == 0);
        InterfaceC1670u interfaceC1670u = this.c;
        return interfaceC1670u.v() ? interfaceC1670u.T0(y1Var, bundle) : l9.l.T(new C1(-100));
    }

    @Override // e2.W
    public final C1195c Z() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        return !interfaceC1670u.v() ? C1195c.f14046g : interfaceC1670u.Z();
    }

    public final void Z0() {
        AbstractC1346c.i("MediaController method is called from a wrong thread. See javadoc of MediaController for details.", Looper.myLooper() == this.f18137e.getLooper());
    }

    @Override // e2.W
    public final void a() {
        String str;
        Z0();
        if (this.f18136b) {
            return;
        }
        StringBuilder sb = new StringBuilder("Release ");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" [AndroidXMedia3/1.5.0] [");
        sb.append(AbstractC1343A.f15197e);
        sb.append("] [");
        HashSet hashSet = e2.G.f13882a;
        synchronized (e2.G.class) {
            str = e2.G.f13883b;
        }
        sb.append(str);
        sb.append("]");
        AbstractC1346c.t("MediaController", sb.toString());
        this.f18136b = true;
        Handler handler = this.f18137e;
        handler.removeCallbacksAndMessages(null);
        try {
            this.c.a();
        } catch (Exception e10) {
            AbstractC1346c.o("MediaController", "Exception while releasing impl", e10);
        }
        if (this.f18139g) {
            AbstractC1346c.j(Looper.myLooper() == handler.getLooper());
            this.d.a();
        } else {
            this.f18139g = true;
            C1674w c1674w = this.f18140h;
            c1674w.getClass();
            c1674w.m(new SecurityException("Session rejected the connection request."));
        }
    }

    @Override // e2.W
    public final int a0() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.a0();
        }
        return -1;
    }

    @Override // e2.W
    public final void b() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.b();
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring pause().");
        }
    }

    @Override // e2.W
    public final int b0() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.b0();
        }
        return -1;
    }

    @Override // e2.W
    public final void c() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.c();
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring prepare().");
        }
    }

    @Override // e2.W
    public final void c0(int i7, boolean z6) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.c0(i7, z6);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring setDeviceMuted().");
        }
    }

    @Override // e2.W
    public final boolean d() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        return interfaceC1670u.v() && interfaceC1670u.d();
    }

    @Override // e2.W
    public final C1202j d0() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        return !interfaceC1670u.v() ? C1202j.f14122e : interfaceC1670u.d0();
    }

    @Override // e2.W
    public final int e() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.e();
        }
        return 1;
    }

    @Override // e2.W
    public final void e0() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.e0();
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring decreaseDeviceVolume().");
        }
    }

    @Override // e2.W
    public final void f(e2.P p10) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.f(p10);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring setPlaybackParameters().");
        }
    }

    @Override // e2.W
    public final void f0(e2.l0 l0Var) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (!interfaceC1670u.v()) {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring setTrackSelectionParameters().");
        }
        interfaceC1670u.f0(l0Var);
    }

    @Override // e2.W
    public final void g() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.g();
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring play().");
        }
    }

    @Override // e2.W
    public final void g0(int i7, int i10) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.g0(i7, i10);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring setDeviceVolume().");
        }
    }

    @Override // e2.W
    public final void h(int i7) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.h(i7);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring setRepeatMode().");
        }
    }

    @Override // e2.W
    public final void h0(boolean z6) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.h0(z6);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring setDeviceMuted().");
        }
    }

    @Override // e2.W
    public final int i() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.i();
        }
        return 0;
    }

    @Override // e2.W
    public final boolean i0() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        return interfaceC1670u.v() && interfaceC1670u.i0();
    }

    @Override // e2.W
    public final e2.P j() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        return interfaceC1670u.v() ? interfaceC1670u.j() : e2.P.d;
    }

    @Override // e2.W
    public final void j0(int i7) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.j0(i7);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring increaseDeviceVolume().");
        }
    }

    @Override // e2.W
    public final void k(long j3) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.k(j3);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring seekTo().");
        }
    }

    @Override // e2.W
    public final int k0() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.k0();
        }
        return -1;
    }

    @Override // e2.W
    public final void l(float f10) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.l(f10);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring setPlaybackSpeed().");
        }
    }

    @Override // e2.W
    public final void l0(SurfaceView surfaceView) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.l0(surfaceView);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring setVideoSurfaceView().");
        }
    }

    @Override // e2.W
    public final void m(List list, int i7, long j3) {
        Z0();
        AbstractC1346c.h(list, "mediaItems must not be null");
        for (int i10 = 0; i10 < list.size(); i10++) {
            AbstractC1346c.b("items must not contain null, index=" + i10, list.get(i10) != null);
        }
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.m(list, i7, j3);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring setMediaItems().");
        }
    }

    @Override // e2.W
    public final void m0(SurfaceView surfaceView) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.m0(surfaceView);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring clearVideoSurfaceView().");
        }
    }

    @Override // e2.W
    public final e2.O n() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.n();
        }
        return null;
    }

    @Override // e2.W
    public final void n0(int i7, int i10) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.n0(i7, i10);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring moveMediaItem().");
        }
    }

    @Override // e2.W
    public final int o() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.o();
        }
        return 0;
    }

    @Override // e2.W
    public final void o0(int i7, int i10, int i11) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.o0(i7, i10, i11);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring moveMediaItems().");
        }
    }

    @Override // e2.W
    public final void p(int i7, e2.F f10) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.p(i7, f10);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring replaceMediaItem().");
        }
    }

    @Override // e2.W
    public final int p0() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.p0();
        }
        return 0;
    }

    @Override // e2.W
    public final void q(boolean z6) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.q(z6);
        }
    }

    @Override // e2.W
    public final void q0(int i7, int i10, List list) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.q0(i7, i10, list);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring replaceMediaItems().");
        }
    }

    @Override // e2.W
    public final void r(Surface surface) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.r(surface);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring setVideoSurface().");
        }
    }

    @Override // e2.W
    public final void r0(List list) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.r0(list);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring addMediaItems().");
        }
    }

    @Override // e2.W
    public final boolean s() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        return interfaceC1670u.v() && interfaceC1670u.s();
    }

    @Override // e2.W
    public final long s0() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.s0();
        }
        return -9223372036854775807L;
    }

    @Override // e2.W
    public final void stop() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.stop();
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring stop().");
        }
    }

    @Override // e2.W
    public final void t(int i7) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.t(i7);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring seekTo().");
        }
    }

    @Override // e2.W
    public final e2.f0 t0() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        return interfaceC1670u.v() ? interfaceC1670u.t0() : e2.f0.f14097a;
    }

    @Override // e2.W
    public final long u() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.u();
        }
        return 0L;
    }

    @Override // e2.W
    public final boolean u0() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.u0();
        }
        return false;
    }

    public final void v() {
        AbstractC1346c.j(Looper.myLooper() == this.f18137e.getLooper());
        AbstractC1346c.j(!this.f18139g);
        this.f18139g = true;
        C1674w c1674w = this.f18140h;
        c1674w.f18146u = true;
        C1672v c1672v = c1674w.f18145t;
        if (c1672v != null) {
            c1674w.l(c1672v);
        }
    }

    @Override // e2.W
    public final void v0(int i7) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.v0(i7);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring removeMediaItem().");
        }
    }

    @Override // e2.W
    public final long w() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.w();
        }
        return -9223372036854775807L;
    }

    @Override // e2.W
    public final void w0() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.w0();
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring increaseDeviceVolume().");
        }
    }

    @Override // e2.W
    public final long x() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.x();
        }
        return 0L;
    }

    @Override // e2.W
    public final boolean x0() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        return interfaceC1670u.v() && interfaceC1670u.x0();
    }

    @Override // e2.W
    public final long y() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.y();
        }
        return 0L;
    }

    @Override // e2.W
    public final e2.l0 y0() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        return !interfaceC1670u.v() ? e2.l0.f14162C : interfaceC1670u.y0();
    }

    @Override // e2.W
    public final void z(int i7, List list) {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            interfaceC1670u.z(i7, list);
        } else {
            AbstractC1346c.z("MediaController", "The controller is not connected. Ignoring addMediaItems().");
        }
    }

    @Override // e2.W
    public final long z0() {
        Z0();
        InterfaceC1670u interfaceC1670u = this.c;
        if (interfaceC1670u.v()) {
            return interfaceC1670u.z0();
        }
        return 0L;
    }
}
