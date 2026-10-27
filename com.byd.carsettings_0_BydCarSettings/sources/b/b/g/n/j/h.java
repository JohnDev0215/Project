package b.b.g.n.j;

import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import b.b.g.o.c;
import b.b.g.r.w;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: DynaSoundFieldPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class h extends b.b.m.e.g<b.b.g.q.f.b.l> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.b.g.o.c f1088a = c.d.a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.b.g.o.h.a f1089b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a f1090c;

    /* JADX INFO: compiled from: DynaSoundFieldPresenter.java */
    public class a extends AbsBYDAutoAudioListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final WeakReference<h> f1091b;

        public a(h hVar) {
            this.f1091b = new WeakReference<>(hVar);
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onSoundFieldChanged(int i, int i2) {
            WeakReference<h> weakReference = this.f1091b;
            if (weakReference == null || weakReference.get() == null || this.f1091b.get().getV() == null) {
                return;
            }
            b.b.m.c.a.a("DynaSoundFieldPresenter", "onSoundFieldChanged--0--x: " + i + ", y: " + i2);
            int i3 = i + 9;
            int i4 = i2 + 9;
            b.b.m.c.a.a("DynaSoundFieldPresenter", "onSoundFieldChanged--1--x: " + i3 + ", y: " + i4);
            ((b.b.g.q.f.b.l) this.f1091b.get().getV()).sendUIMessage(21, new int[]{i3, i4});
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onSoundFieldPositionChanged(int i) {
            WeakReference<h> weakReference = this.f1091b;
            if (weakReference == null || weakReference.get() == null || this.f1091b.get().getV() == null) {
                return;
            }
            int i2 = w.i(this.f1091b.get().f1089b, i);
            b.b.m.c.a.a("DynaSoundFieldPresenter", "onSoundFieldPositionChanged type: " + i + ", index: " + i2);
            ((b.b.g.q.f.b.l) this.f1091b.get().getV()).sendUIMessage(22, Integer.valueOf(i2));
        }
    }

    public b.b.g.o.h.a h() {
        return this.f1088a.d();
    }

    public int[] i() {
        int[] iArrI = this.f1088a.i();
        iArrI[0] = iArrI[0] + 9;
        iArrI[1] = iArrI[1] + 9;
        return iArrI;
    }

    public int j() {
        return w.i(this.f1089b, this.f1088a.l());
    }

    public void l() {
        if (this.f1090c == null) {
            this.f1090c = new a(this);
        }
        this.f1088a.u(h.class.getName(), this.f1090c);
        this.f1089b = this.f1088a.d();
    }

    public int[] m() {
        this.f1088a.C();
        return i();
    }

    public int n(int i, int i2) {
        return this.f1088a.M(i - 9, i2 - 9);
    }

    public int o(int i) {
        return this.f1088a.P(w.h(this.f1089b, i));
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        this.f1090c = null;
        this.f1088a.Z(h.class.getName());
    }
}
