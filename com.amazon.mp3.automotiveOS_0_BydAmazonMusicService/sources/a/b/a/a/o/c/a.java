package a.b.a.a.o.c;

import a.b.a.a.o.d.b.k;
import android.util.Range;

/* JADX INFO: loaded from: classes.dex */
public final class a implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f94a;

    public a(c cVar) {
        this.f94a = cVar;
    }

    @Override // a.b.a.a.o.c.b
    public a.b.a.a.k.e.d a() {
        c cVar = this.f94a;
        cVar.getClass();
        cVar.f95a = k.b.b("video/avc");
        c cVar2 = this.f94a;
        if (!(cVar2.f95a != null)) {
            return a.b.a.a.k.e.d.Undefined;
        }
        if (!cVar2.b(1280, 720)) {
            return a.b.a.a.k.e.d.AVCLevel3;
        }
        if (!this.f94a.b(1920, 1080)) {
            return ((Number) this.f94a.a(1280, 720).getUpper()).doubleValue() < 60.0d ? a.b.a.a.k.e.d.AVCLevel31 : a.b.a.a.k.e.d.AVCLevel32;
        }
        if (!this.f94a.b(2560, 1440)) {
            return ((Number) this.f94a.a(1920, 1080).getUpper()).doubleValue() < 60.0d ? a.b.a.a.k.e.d.AVCLevel41 : a.b.a.a.k.e.d.AVCLevel42;
        }
        if (!this.f94a.b(3840, 2160)) {
            return a.b.a.a.k.e.d.AVCLevel5;
        }
        if (!this.f94a.b(7680, 4320)) {
            return ((Number) this.f94a.a(3840, 2160).getUpper()).doubleValue() < 60.0d ? a.b.a.a.k.e.d.AVCLevel51 : a.b.a.a.k.e.d.AVCLevel52;
        }
        Range<Double> rangeA = this.f94a.a(7680, 4320);
        return ((Number) rangeA.getUpper()).doubleValue() < 60.0d ? a.b.a.a.k.e.d.AVCLevel6 : ((Number) rangeA.getUpper()).doubleValue() < 120.0d ? a.b.a.a.k.e.d.AVCLevel61 : a.b.a.a.k.e.d.AVCLevel62;
    }
}
