package b.b.g.n.j;

import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import b.b.g.o.c;
import b.b.g.r.w;
import com.byd.carsettings.model.dilink.param.EqualizerParams;
import com.byd.carsettings.sound.model.Manufactor;
import java.lang.ref.WeakReference;
import java.util.Map;

/* JADX INFO: compiled from: BsEqualizerPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends b.b.m.e.g<b.b.g.q.f.b.e> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.b.g.o.c f1071a = c.d.a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f1072b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Manufactor f1073c;

    /* JADX INFO: compiled from: BsEqualizerPresenter.java */
    public class a extends AbsBYDAutoAudioListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final WeakReference<c> f1074b;

        public a(c cVar) {
            this.f1074b = new WeakReference<>(cVar);
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onEqualizerChanged(int i, int i2) {
            WeakReference<c> weakReference;
            if (c.this.f1073c != Manufactor.DYNAUDIO || (weakReference = this.f1074b) == null || weakReference.get() == null || this.f1074b.get().getV() == null) {
                return;
            }
            b.b.m.c.a.a("BsEqualizerPresenter", "onEqualizerChanged freq:" + i + ",value:" + i2);
            ((b.b.g.q.f.b.e) this.f1074b.get().getV()).sendUIMessage(19, new EqualizerParams(i, i2 + 9));
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onSoundEffectChanged(int i) {
            WeakReference<c> weakReference;
            if (c.this.f1073c != Manufactor.DYNAUDIO || (weakReference = this.f1074b) == null || weakReference.get() == null || this.f1074b.get().getV() == null) {
                return;
            }
            int iF = w.f(i);
            b.b.m.c.a.a("BsEqualizerPresenter", "onSoundEffectChanged index:" + iF + ", effect:" + i);
            ((b.b.g.q.f.b.e) this.f1074b.get().getV()).sendUIMessage(20, Integer.valueOf(iF));
        }
    }

    public Map<Integer, Integer> h() {
        return o(this.f1071a.k());
    }

    public int i() {
        return w.a(this.f1071a.j());
    }

    public void j() {
        if (this.f1072b == null) {
            this.f1072b = new a(this);
        }
        this.f1071a.u(c.class.getName(), this.f1072b);
        b.b.g.o.h.a aVarD = this.f1071a.d();
        if (aVarD != null) {
            this.f1073c = aVarD.f1342a;
        }
    }

    public Map<Integer, Integer> l() {
        return o(this.f1071a.B());
    }

    public int m(b.b.g.o.h.c cVar) {
        return this.f1071a.O(cVar.a().intValue(), cVar.b() - 9);
    }

    public int n(int i) {
        return this.f1071a.N(w.b(i));
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
        this.f1072b = null;
        this.f1071a.Z(c.class.getName());
    }
}
