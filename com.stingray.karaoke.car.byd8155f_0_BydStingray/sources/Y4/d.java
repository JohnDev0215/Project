package Y4;

/* JADX INFO: loaded from: classes.dex */
public final class d extends com.bumptech.glide.c {
    @Override // com.bumptech.glide.c
    public final void b0(t tVar, float f10, float f11) {
        tVar.d(f11 * f10, 180.0f, 90.0f);
        double d = f11;
        double d10 = f10;
        tVar.c((float) (Math.sin(Math.toRadians(90.0f)) * d * d10), (float) (Math.sin(Math.toRadians(0.0f)) * d * d10));
    }
}
