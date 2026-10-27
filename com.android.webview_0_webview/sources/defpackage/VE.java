package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class VE extends WE {
    public boolean a;
    public boolean b;

    public VE(NE ne) {
    }

    @Override // defpackage.WE
    public void j(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
        fArr2[0] = fArr4[96];
        fArr2[1] = fArr4[97];
        fArr2[2] = fArr4[98];
        fArr2[3] = fArr4[99];
        fArr2[4] = fArr4[100];
        fArr2[5] = fArr4[101];
        fArr2[8] = fArr4[102];
        fArr2[9] = fArr4[103];
        fArr2[10] = fArr4[104];
        fArr2[11] = fArr4[105];
        if (fArr3[11] != 0.0f) {
            this.a = true;
        }
        if (fArr3[14] != 0.0f) {
            this.b = true;
        }
        if (this.a) {
            fArr2[6] = (fArr3[11] + 1.0f) / 2.0f;
        } else {
            fArr2[6] = 0.0f;
        }
        if (this.b) {
            fArr2[7] = (fArr3[14] + 1.0f) / 2.0f;
        } else {
            fArr2[7] = 0.0f;
        }
        WE.a(fArr2, fArr3);
        fArr[0] = fArr3[0];
        fArr[1] = fArr3[1];
        fArr[2] = fArr3[12];
        fArr[3] = fArr3[13];
    }
}
