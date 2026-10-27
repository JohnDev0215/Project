package Y4;

/* JADX INFO: loaded from: classes.dex */
public final class o extends s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q f10767a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f10768b;
    public final float c;

    public o(q qVar, float f10, float f11) {
        this.f10767a = qVar;
        this.f10768b = f10;
        this.c = f11;
    }

    public final float a() {
        q qVar = this.f10767a;
        return (float) Math.toDegrees(Math.atan((qVar.c - this.c) / (qVar.f10774b - this.f10768b)));
    }
}
