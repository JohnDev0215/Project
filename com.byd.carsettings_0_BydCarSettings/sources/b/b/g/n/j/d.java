package b.b.g.n.j;

import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import b.b.g.o.c;
import b.b.g.r.w;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: BsSoundFieldPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class d extends b.b.m.e.g<b.b.g.q.f.b.f> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.b.g.o.c f1076a = c.d.a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.b.g.o.h.a f1077b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a f1078c;

    /* JADX INFO: compiled from: BsSoundFieldPresenter.java */
    public class a extends AbsBYDAutoAudioListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final WeakReference<d> f1079b;

        public a(d dVar) {
            this.f1079b = new WeakReference<>(dVar);
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onSoundFieldChanged(int i, int i2) {
            WeakReference<d> weakReference = this.f1079b;
            if (weakReference == null || weakReference.get() == null || this.f1079b.get().getV() == null) {
                return;
            }
            b.b.m.c.a.a("BsSoundFieldPresenter", "onSoundFieldChanged--0--x: " + i + ", y: " + i2);
            int i3 = i + 9;
            int i4 = i2 + 9;
            b.b.m.c.a.a("BsSoundFieldPresenter", "onSoundFieldChanged--1--x: " + i3 + ", y: " + i4);
            ((b.b.g.q.f.b.f) this.f1079b.get().getV()).sendUIMessage(21, new int[]{i3, i4});
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onSoundFieldPositionChanged(int i) {
            WeakReference<d> weakReference = this.f1079b;
            if (weakReference == null || weakReference.get() == null || this.f1079b.get().getV() == null) {
                return;
            }
            int i2 = w.i(this.f1079b.get().f1077b, i);
            b.b.m.c.a.a("BsSoundFieldPresenter", "onSoundFieldPositionChanged type: " + i + ", index: " + i2);
            ((b.b.g.q.f.b.f) this.f1079b.get().getV()).sendUIMessage(22, Integer.valueOf(i2));
        }
    }

    public b.b.g.o.h.a h() {
        return this.f1076a.d();
    }

    public int[] i() {
        int[] iArrI = this.f1076a.i();
        iArrI[0] = iArrI[0] + 9;
        iArrI[1] = iArrI[1] + 9;
        return iArrI;
    }

    public int j() {
        return w.d(this.f1077b, this.f1076a.l());
    }

    public void l() {
        if (this.f1078c == null) {
            this.f1078c = new a(this);
        }
        this.f1076a.u(d.class.getName(), this.f1078c);
        this.f1077b = this.f1076a.d();
    }

    public int[] m() {
        this.f1076a.C();
        return i();
    }

    public int n(int i, int i2) {
        return this.f1076a.M(i - 9, i2 - 9);
    }

    public int o(int i) {
        return this.f1076a.P(w.c(this.f1077b, i));
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        this.f1078c = null;
        this.f1076a.Z(d.class.getName());
    }
}
