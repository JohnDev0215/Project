package b.b.g.n.j;

import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import b.b.g.o.c;
import b.b.g.r.w;
import com.byd.carsettings.model.dilink.param.EqualizerParams;
import com.byd.carsettings.sound.model.Manufactor;
import java.lang.ref.WeakReference;
import java.util.Map;

/* JADX INFO: compiled from: DynaEqualizerPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class g extends b.b.m.e.g<b.b.g.q.f.b.k> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.b.g.o.c f1083a = c.d.a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f1084b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Manufactor f1085c;

    /* JADX INFO: compiled from: DynaEqualizerPresenter.java */
    public class a extends AbsBYDAutoAudioListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final WeakReference<g> f1086b;

        public a(g gVar) {
            this.f1086b = new WeakReference<>(gVar);
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onEqualizerChanged(int i, int i2) {
            WeakReference<g> weakReference;
            if (g.this.f1085c != Manufactor.DYNAUDIO || (weakReference = this.f1086b) == null || weakReference.get() == null || this.f1086b.get().getV() == null) {
                return;
            }
            b.b.m.c.a.a("DynaEqualizerPresenter", "onEqualizerChanged freq:" + i + ",value:" + i2);
            ((b.b.g.q.f.b.k) this.f1086b.get().getV()).sendUIMessage(19, new EqualizerParams(i, i2 + 9));
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onSoundEffectChanged(int i) {
            WeakReference<g> weakReference;
            if (g.this.f1085c != Manufactor.DYNAUDIO || (weakReference = this.f1086b) == null || weakReference.get() == null || this.f1086b.get().getV() == null) {
                return;
            }
            int iF = w.f(i);
            b.b.m.c.a.a("DynaEqualizerPresenter", "onSoundEffectChanged index:" + iF + ", effect:" + i);
            ((b.b.g.q.f.b.k) this.f1086b.get().getV()).sendUIMessage(20, Integer.valueOf(iF));
        }
    }

    public Map<Integer, Integer> h() {
        return o(this.f1083a.k());
    }

    public int i() {
        return w.f(this.f1083a.j());
    }

    public void j() {
        if (this.f1084b == null) {
            this.f1084b = new a(this);
        }
        this.f1083a.u(g.class.getName(), this.f1084b);
        b.b.g.o.h.a aVarD = this.f1083a.d();
        if (aVarD != null) {
            this.f1085c = aVarD.f1342a;
        }
    }

    public Map<Integer, Integer> l() {
        return o(this.f1083a.B());
    }

    public int m(b.b.g.o.h.c cVar) {
        return this.f1083a.O(cVar.a().intValue(), cVar.b() - 9);
    }

    public int n(int i) {
        return this.f1083a.N(w.g(i));
    }

    public final Map<Integer, Integer> o(Map<Integer, Integer> map) {
        for (Integer num : map.keySet()) {
            map.put(num, Integer.valueOf(map.get(num).intValue() + 9));
        }
        return map;
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        this.f1084b = null;
        this.f1083a.Z(g.class.getName());
    }
}
