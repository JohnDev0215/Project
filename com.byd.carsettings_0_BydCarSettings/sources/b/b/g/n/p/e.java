package b.b.g.n.p;

import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import b.b.g.o.b;
import b.b.g.o.c;
import b.b.g.q.f.d.i;
import b.b.g.r.w;
import b.b.m.e.g;
import com.byd.carsettings.BydApp;
import com.byd.carsettings.sound.model.DiracMode;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: SoundPagerPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class e extends g<i> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.b.g.o.c f1256a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.b.g.o.h.a f1257b;

    /* JADX INFO: compiled from: SoundPagerPresenter.java */
    public class a extends AbsBYDAutoAudioListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final WeakReference<e> f1258b;

        public a(e eVar) {
            this.f1258b = new WeakReference<>(eVar);
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onGearSpeedChanged(int i, int i2) {
            WeakReference<e> weakReference = this.f1258b;
            if (weakReference == null || weakReference.get() == null || this.f1258b.get().getV() == null) {
                return;
            }
            ((i) this.f1258b.get().getV()).sendUIMessage(849, i, Integer.valueOf(i2));
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onSoundEffectChanged(int i) {
            int iF = w.f(i);
            b.b.m.c.a.a(AbsBYDAutoAudioListener.TAG, "onSoundEffectChanged effect:" + i + ",index:" + iF);
            WeakReference<e> weakReference = this.f1258b;
            if (weakReference == null || weakReference.get() == null || this.f1258b.get().getV() == null) {
                return;
            }
            ((i) this.f1258b.get().getV()).sendUIMessage(851, Integer.valueOf(iF));
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onSoundFieldPositionChanged(int i) {
            WeakReference<e> weakReference = this.f1258b;
            if (weakReference == null || weakReference.get() == null || this.f1258b.get().getV() == null) {
                return;
            }
            int i2 = w.i(this.f1258b.get().f1257b, i);
            b.b.m.c.a.a(AbsBYDAutoAudioListener.TAG, "onSoundFieldPositionChanged type:" + i + ",index:" + i2);
            ((i) this.f1258b.get().getV()).sendUIMessage(850, Integer.valueOf(i2));
        }
    }

    public e() {
        b.b.g.o.c cVarA = c.d.a();
        this.f1256a = cVarA;
        cVarA.r(BydApp.getContext());
    }

    public int A(int i) {
        return this.f1256a.U(w.j(i));
    }

    public void B(boolean z) {
        this.f1256a.W(z);
    }

    public b.b.g.o.h.a j() {
        return this.f1256a.d();
    }

    public int l() {
        return w.a(this.f1256a.j());
    }

    public int m() {
        return w.d(this.f1257b, this.f1256a.l());
    }

    public DiracMode n() {
        return this.f1256a.f();
    }

    public int[] o() {
        return this.f1256a.g();
    }

    public int q() {
        return b.b.g.o.b.a(this.f1256a.h());
    }

    public int r() {
        return w.f(this.f1256a.j());
    }

    public int s() {
        return w.i(this.f1257b, this.f1256a.l());
    }

    public int t() {
        return this.f1256a.m();
    }

    public int u() {
        return b.C0059b.a().i(getV().getContext());
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        b.b.g.o.c cVar = this.f1256a;
        if (cVar != null) {
            cVar.Z(e.class.getName());
        }
    }

    public int v() {
        return w.k(this.f1256a.o());
    }

    public void w() {
        this.f1256a.u(e.class.getName(), new a(this));
        this.f1257b = this.f1256a.d();
    }

    public boolean x() {
        return this.f1256a.t();
    }

    public void y(DiracMode diracMode) {
        this.f1256a.K(diracMode);
    }

    public void z(int i) {
        this.f1256a.Q(i);
    }
}
