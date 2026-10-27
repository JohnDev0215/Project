package Y4;

/* JADX INFO: loaded from: classes.dex */
public final class i extends com.bumptech.glide.c {
    @Override // com.bumptech.glide.c
    public final void b0(t tVar, float f10, float f11) {
        tVar.d(f11 * f10, 180.0f, 90.0f);
        float f12 = f11 * 2.0f * f10;
        p pVar = new p(0.0f, 0.0f, f12, f12);
        pVar.f10772f = 180.0f;
        pVar.f10773g = 90.0f;
        tVar.f10779f.add(pVar);
        n nVar = new n(pVar);
        tVar.a(180.0f);
        tVar.f10780g.add(nVar);
        tVar.d = 270.0f;
        float f13 = (0.0f + f12) * 0.5f;
        float f14 = (f12 - 0.0f) / 2.0f;
        double d = 270.0f;
        tVar.f10777b = (((float) Math.cos(Math.toRadians(d))) * f14) + f13;
        tVar.c = (f14 * ((float) Math.sin(Math.toRadians(d)))) + f13;
    }
}
