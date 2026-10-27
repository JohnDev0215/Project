package defpackage;

import java.util.BitSet;

/* JADX INFO: compiled from: GamepadMappings.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class es0 {

    /* JADX INFO: compiled from: GamepadMappings.java */
    public static class a extends es0 {
        @Override // defpackage.es0
        public final void j(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
            es0.d(fArr2, fArr4);
            es0.f(fArr2, fArr4);
            es0.c(fArr2, fArr4);
            es0.b(fArr2, fArr4);
            float f = fArr3[23];
            float f2 = fArr3[22];
            fArr2[6] = f;
            fArr2[7] = f2;
            es0.e(fArr2, fArr3);
            es0.g(fArr, fArr3);
            es0.h(fArr, fArr3);
        }
    }

    /* JADX INFO: compiled from: GamepadMappings.java */
    public static class b extends es0 {
        @Override // defpackage.es0
        public final void j(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
            es0.d(fArr2, fArr4);
            es0.f(fArr2, fArr4);
            es0.c(fArr2, fArr4);
            es0.b(fArr2, fArr4);
            es0.a(fArr2, fArr4);
            es0.g(fArr, fArr3);
            es0.h(fArr, fArr3);
            float f = fArr3[17];
            float f2 = fArr3[18];
            fArr2[6] = f;
            fArr2[7] = f2;
        }
    }

    /* JADX INFO: compiled from: GamepadMappings.java */
    public static class c extends es0 {
        @Override // defpackage.es0
        public final void j(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
            float f = fArr4[96];
            float f2 = fArr4[97];
            float f3 = fArr4[98];
            float f4 = fArr4[99];
            fArr2[0] = f2;
            fArr2[1] = f3;
            fArr2[2] = f;
            fArr2[3] = f4;
            float f5 = fArr4[100];
            float f6 = fArr4[101];
            fArr2[4] = f5;
            fArr2[5] = f6;
            float f7 = fArr3[12];
            float f8 = fArr3[13];
            fArr2[6] = 1.0f - ((1.0f - f7) / 2.0f);
            fArr2[7] = 1.0f - ((1.0f - f8) / 2.0f);
            float f9 = fArr4[104];
            float f10 = fArr4[105];
            fArr2[8] = f9;
            fArr2[9] = f10;
            float f11 = fArr4[109];
            float f12 = fArr4[108];
            fArr2[10] = f11;
            fArr2[11] = f12;
            fArr2[16] = fArr4[110];
            es0.e(fArr2, fArr3);
            es0.g(fArr, fArr3);
            es0.h(fArr, fArr3);
        }
    }

    /* JADX INFO: compiled from: GamepadMappings.java */
    public static class d extends es0 {
        @Override // defpackage.es0
        public final void j(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
            es0.d(fArr2, fArr4);
            float f = fArr4[102];
            float f2 = fArr4[103];
            fArr2[6] = f;
            fArr2[7] = f2;
            es0.c(fArr2, fArr4);
            es0.b(fArr2, fArr4);
            es0.e(fArr2, fArr3);
            es0.g(fArr, fArr3);
            fArr[2] = fArr3[12];
            fArr[3] = fArr3[13];
        }
    }

    /* JADX INFO: compiled from: GamepadMappings.java */
    public static class e extends es0 {
        public final boolean a;

        public e(int[] iArr) {
            int length = iArr.length;
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (iArr[i] == 12) {
                    z = true;
                    break;
                }
                i++;
            }
            this.a = z;
        }

        @Override // defpackage.es0
        public final int i() {
            return 16;
        }

        @Override // defpackage.es0
        public final void j(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
            es0.d(fArr2, fArr4);
            es0.f(fArr2, fArr4);
            es0.b(fArr2, fArr4);
            es0.g(fArr, fArr3);
            es0.e(fArr2, fArr3);
            float f = fArr4[106];
            float f2 = fArr4[107];
            float f3 = fArr4[98];
            float f4 = fArr4[101];
            fArr2[10] = Math.max(f, f3);
            fArr2[11] = Math.max(f2, f4);
            if (!this.a) {
                float f5 = fArr4[104];
                float f6 = fArr4[105];
                fArr2[6] = f5;
                fArr2[7] = f6;
                es0.h(fArr, fArr3);
                return;
            }
            float f7 = fArr3[11];
            fArr2[6] = f7 > 0.01f ? f7 : 0.0f;
            float f8 = -f7;
            fArr2[7] = f8 > 0.01f ? f8 : 0.0f;
            fArr[2] = fArr3[12];
            fArr[3] = fArr3[13];
        }
    }

    /* JADX INFO: compiled from: GamepadMappings.java */
    public static class f extends es0 {
        @Override // defpackage.es0
        public final int i() {
            return 19;
        }

        @Override // defpackage.es0
        public final void j(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
            es0.d(fArr2, fArr4);
            es0.f(fArr2, fArr4);
            float f = fArr3[23];
            float f2 = fArr3[22];
            fArr2[6] = f;
            fArr2[7] = f2;
            es0.c(fArr2, fArr4);
            es0.b(fArr2, fArr4);
            es0.e(fArr2, fArr3);
            fArr2[17] = fArr4[188];
            fArr2[18] = fArr4[189];
            es0.g(fArr, fArr3);
            es0.h(fArr, fArr3);
        }
    }

    /* JADX INFO: compiled from: GamepadMappings.java */
    public static class g extends es0 {
        public final int a;
        public final int b;
        public final int c;
        public final int d;
        public final boolean e;
        public final boolean f;

        public g(int[] iArr, BitSet bitSet) {
            this.a = -1;
            this.b = -1;
            this.c = -1;
            this.d = -1;
            this.f = bitSet.get(110);
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

        @Override // defpackage.es0
        public final int i() {
            return this.f ? 17 : 16;
        }

        @Override // defpackage.es0
        public final void j(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
            int i;
            int i2;
            es0.d(fArr2, fArr4);
            es0.f(fArr2, fArr4);
            es0.c(fArr2, fArr4);
            es0.b(fArr2, fArr4);
            es0.g(fArr, fArr3);
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
                es0.e(fArr2, fArr3);
            } else {
                es0.a(fArr2, fArr4);
            }
        }
    }

    /* JADX INFO: compiled from: GamepadMappings.java */
    public static class h extends es0 {
        @Override // defpackage.es0
        public final void j(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
            es0.d(fArr2, fArr4);
            es0.f(fArr2, fArr4);
            es0.c(fArr2, fArr4);
            es0.b(fArr2, fArr4);
            float f = fArr3[17];
            float f2 = fArr3[18];
            fArr2[6] = f;
            fArr2[7] = f2;
            es0.e(fArr2, fArr3);
            es0.g(fArr, fArr3);
            es0.h(fArr, fArr3);
        }
    }

    /* JADX INFO: compiled from: GamepadMappings.java */
    public static class i extends es0 {
        public boolean a;
        public boolean b;

        @Override // defpackage.es0
        public final int i() {
            return 16;
        }

        @Override // defpackage.es0
        public final void j(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
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
            float f = fArr3[11];
            if (f != 0.0f) {
                this.a = true;
            }
            float f2 = fArr3[14];
            if (f2 != 0.0f) {
                this.b = true;
            }
            if (this.a) {
                fArr2[6] = (f + 1.0f) / 2.0f;
            } else {
                fArr2[6] = 0.0f;
            }
            if (this.b) {
                fArr2[7] = (f2 + 1.0f) / 2.0f;
            } else {
                fArr2[7] = 0.0f;
            }
            es0.e(fArr2, fArr3);
            es0.g(fArr, fArr3);
            fArr[2] = fArr3[12];
            fArr[3] = fArr3[13];
        }
    }

    /* JADX INFO: compiled from: GamepadMappings.java */
    public static class j extends es0 {
        @Override // defpackage.es0
        public final int i() {
            return 18;
        }

        @Override // defpackage.es0
        public final void j(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
            es0.d(fArr2, fArr4);
            es0.f(fArr2, fArr4);
            es0.c(fArr2, fArr4);
            es0.b(fArr2, fArr4);
            es0.e(fArr2, fArr3);
            es0.g(fArr, fArr3);
            es0.h(fArr, fArr3);
            float f = fArr3[23];
            float f2 = fArr3[22];
            fArr2[6] = f;
            fArr2[7] = f2;
            fArr2[17] = fArr4[130];
        }
    }

    public static void a(float[] fArr, float[] fArr2) {
        float f2 = fArr2[20];
        float f3 = fArr2[19];
        float f4 = fArr2[21];
        float f5 = fArr2[22];
        fArr[13] = f2;
        fArr[12] = f3;
        fArr[14] = f4;
        fArr[15] = f5;
    }

    public static void b(float[] fArr, float[] fArr2) {
        float f2 = fArr2[108];
        float f3 = fArr2[109];
        float f4 = fArr2[110];
        fArr[9] = f2;
        fArr[8] = f3;
        fArr[16] = f4;
    }

    public static void c(float[] fArr, float[] fArr2) {
        float f2 = fArr2[106];
        float f3 = fArr2[107];
        fArr[10] = f2;
        fArr[11] = f3;
    }

    public static void d(float[] fArr, float[] fArr2) {
        float f2 = fArr2[96];
        float f3 = fArr2[97];
        float f4 = fArr2[99];
        float f5 = fArr2[100];
        fArr[0] = f2;
        fArr[1] = f3;
        fArr[2] = f4;
        fArr[3] = f5;
    }

    public static void e(float[] fArr, float[] fArr2) {
        float f2 = fArr2[15];
        float f3 = fArr2[16];
        fArr[14] = f2 < -0.5f ? 1.0f : 0.0f;
        fArr[15] = f2 > 0.5f ? 1.0f : 0.0f;
        fArr[12] = f3 < -0.5f ? 1.0f : 0.0f;
        fArr[13] = f3 <= 0.5f ? 0.0f : 1.0f;
    }

    public static void f(float[] fArr, float[] fArr2) {
        float f2 = fArr2[102];
        float f3 = fArr2[103];
        fArr[4] = f2;
        fArr[5] = f3;
    }

    public static void g(float[] fArr, float[] fArr2) {
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[1];
    }

    public static void h(float[] fArr, float[] fArr2) {
        fArr[2] = fArr2[11];
        fArr[3] = fArr2[14];
    }

    public int i() {
        return 17;
    }

    public abstract void j(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4);
}
