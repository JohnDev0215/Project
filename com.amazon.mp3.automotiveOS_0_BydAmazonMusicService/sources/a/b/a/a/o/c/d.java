package a.b.a.a.o.c;

import a.b.a.a.o.d.b.k;
import android.util.Range;

/* JADX INFO: loaded from: classes.dex */
public final class d implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f96a;

    public d(c cVar) {
        this.f96a = cVar;
    }

    @Override // a.b.a.a.o.c.b
    public a.b.a.a.k.e.d a() {
        c cVar = this.f96a;
        cVar.getClass();
        cVar.f95a = k.b.b("video/hevc");
        if (!this.f96a.b(1280, 720)) {
            return a.b.a.a.k.e.d.HEVCMainTierLevel3;
        }
        if (!this.f96a.b(1920, 1080)) {
            return a.b.a.a.k.e.d.HEVCMainTierLevel31;
        }
        if (!this.f96a.b(3840, 2160)) {
            return ((Number) this.f96a.a(1920, 1080).getUpper()).doubleValue() < 60.0d ? a.b.a.a.k.e.d.HEVCMainTierLevel4 : a.b.a.a.k.e.d.HEVCMainTierLevel41;
        }
        if (this.f96a.b(7680, 4320)) {
            Range<Double> rangeA = this.f96a.a(7680, 4320);
            return ((Number) rangeA.getUpper()).doubleValue() < 60.0d ? a.b.a.a.k.e.d.HEVCMainTierLevel6 : Double.compare(((Number) rangeA.getUpper()).doubleValue(), (double) 120) < 0 ? a.b.a.a.k.e.d.HEVCMainTierLevel61 : a.b.a.a.k.e.d.HEVCMainTierLevel62;
        }
        Range<Double> rangeA2 = this.f96a.a(3840, 2160);
        return ((Number) rangeA2.getUpper()).doubleValue() < 60.0d ? a.b.a.a.k.e.d.HEVCMainTierLevel5 : Double.compare(((Number) rangeA2.getUpper()).doubleValue(), (double) 120) < 0 ? a.b.a.a.k.e.d.HEVCMainTierLevel51 : a.b.a.a.k.e.d.HEVCMainTierLevel52;
    }
}
