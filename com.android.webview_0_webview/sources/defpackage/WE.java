package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class WE {
    public static void a(float[] fArr, float[] fArr2) {
        float f = fArr2[15];
        float f2 = fArr2[16];
        fArr[14] = f < -0.5f ? 1.0f : 0.0f;
        fArr[15] = f > 0.5f ? 1.0f : 0.0f;
        fArr[12] = f2 < -0.5f ? 1.0f : 0.0f;
        fArr[13] = f2 <= 0.5f ? 0.0f : 1.0f;
    }

    public static void b(float[] fArr, float[] fArr2) {
        fArr[2] = fArr2[11];
        fArr[3] = fArr2[14];
    }

    public static void c(float[] fArr, float[] fArr2) {
        float f = fArr2[17];
        float f2 = fArr2[18];
        fArr[6] = f;
        fArr[7] = f2;
    }

    public static void d(float[] fArr, float[] fArr2) {
        float f = fArr2[20];
        float f2 = fArr2[19];
        float f3 = fArr2[21];
        float f4 = fArr2[22];
        fArr[13] = f;
        fArr[12] = f2;
        fArr[14] = f3;
        fArr[15] = f4;
    }

    public static void e(float[] fArr, float[] fArr2) {
        float f = fArr2[96];
        float f2 = fArr2[97];
        float f3 = fArr2[99];
        float f4 = fArr2[100];
        fArr[0] = f;
        fArr[1] = f2;
        fArr[2] = f3;
        fArr[3] = f4;
    }

    public static void f(float[] fArr, float[] fArr2) {
        float f = fArr2[102];
        float f2 = fArr2[103];
        fArr[4] = f;
        fArr[5] = f2;
    }

    public static void g(float[] fArr, float[] fArr2) {
        float f = fArr2[106];
        float f2 = fArr2[107];
        fArr[10] = f;
        fArr[11] = f2;
    }

    public static void h(float[] fArr, float[] fArr2) {
        float f = fArr2[108];
        float f2 = fArr2[109];
        float f3 = fArr2[110];
        fArr[9] = f;
        fArr[8] = f2;
        fArr[16] = f3;
    }

    public boolean i() {
        return true;
    }

    public abstract void j(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4);
}
