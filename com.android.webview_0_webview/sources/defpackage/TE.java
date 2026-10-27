package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class TE extends WE {
    public int a;
    public int b;
    public int c;
    public int d;
    public boolean e;

    public TE(int[] iArr) {
        this.a = -1;
        this.b = -1;
        this.c = -1;
        this.d = -1;
        int i = 0;
        for (int i2 : iArr) {
            switch (i2) {
                case 11:
                case 12:
                    this.c = i2;
                    break;
                case 13:
                case 14:
                    this.d = i2;
                    break;
                case 15:
                case 16:
                    i++;
                    break;
                case 17:
                case 23:
                    this.a = i2;
                    break;
                case 18:
                case 19:
                case 22:
                    this.b = i2;
                    break;
            }
        }
        if (i == 2) {
            this.e = true;
        }
    }

    @Override // defpackage.WE
    public boolean i() {
        return false;
    }

    @Override // defpackage.WE
    public void j(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
        int i;
        int i2;
        WE.e(fArr2, fArr4);
        WE.f(fArr2, fArr4);
        WE.g(fArr2, fArr4);
        WE.h(fArr2, fArr4);
        fArr[0] = fArr3[0];
        fArr[1] = fArr3[1];
        int i3 = this.a;
        if (i3 == -1 || (i2 = this.b) == -1) {
            float f = fArr4[104];
            float f2 = fArr4[105];
            fArr2[6] = f;
            fArr2[7] = f2;
        } else {
            float f3 = fArr3[i3];
            float f4 = fArr3[i2];
            fArr2[6] = f3;
            fArr2[7] = f4;
        }
        int i4 = this.c;
        if (i4 != -1 && (i = this.d) != -1) {
            float f5 = fArr3[i4];
            float f6 = fArr3[i];
            fArr[2] = f5;
            fArr[3] = f6;
        }
        if (this.e) {
            WE.a(fArr2, fArr3);
        } else {
            WE.d(fArr2, fArr4);
        }
    }
}
