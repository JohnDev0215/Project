package y1;

import android.graphics.Color;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: GradientColorParser.java */
/* JADX INFO: loaded from: classes.dex */
public class l implements j0<v1.c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9201a;

    public l(int i8) {
        this.f9201a = i8;
    }

    public final void b(v1.c cVar, List<Float> list) {
        int i8 = this.f9201a * 4;
        if (list.size() <= i8) {
            return;
        }
        int size = (list.size() - i8) / 2;
        double[] dArr = new double[size];
        double[] dArr2 = new double[size];
        int i9 = 0;
        while (i8 < list.size()) {
            if (i8 % 2 == 0) {
                dArr[i9] = list.get(i8).floatValue();
            } else {
                dArr2[i9] = list.get(i8).floatValue();
                i9++;
            }
            i8++;
        }
        for (int i10 = 0; i10 < cVar.c(); i10++) {
            int i11 = cVar.a()[i10];
            cVar.a()[i10] = Color.argb(c(cVar.b()[i10], dArr, dArr2), Color.red(i11), Color.green(i11), Color.blue(i11));
        }
    }

    public final int c(double d8, double[] dArr, double[] dArr2) {
        double dI;
        int i8 = 1;
        while (true) {
            if (i8 >= dArr.length) {
                dI = dArr2[dArr2.length - 1];
                break;
            }
            int i9 = i8 - 1;
            double d9 = dArr[i9];
            double d10 = dArr[i8];
            if (dArr[i8] >= d8) {
                dI = a2.g.i(dArr2[i9], dArr2[i8], (d8 - d9) / (d10 - d9));
                break;
            }
            i8++;
        }
        return (int) (dI * 255.0d);
    }

    @Override // y1.j0
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public v1.c a(JsonReader jsonReader, float f8) throws IOException {
        ArrayList arrayList = new ArrayList();
        boolean z7 = jsonReader.x() == JsonReader.Token.BEGIN_ARRAY;
        if (z7) {
            jsonReader.g();
        }
        while (jsonReader.n()) {
            arrayList.add(Float.valueOf((float) jsonReader.q()));
        }
        if (z7) {
            jsonReader.j();
        }
        if (this.f9201a == -1) {
            this.f9201a = arrayList.size() / 4;
        }
        int i8 = this.f9201a;
        float[] fArr = new float[i8];
        int[] iArr = new int[i8];
        int i9 = 0;
        int i10 = 0;
        for (int i11 = 0; i11 < this.f9201a * 4; i11++) {
            int i12 = i11 / 4;
            double dFloatValue = arrayList.get(i11).floatValue();
            int i13 = i11 % 4;
            if (i13 == 0) {
                fArr[i12] = (float) dFloatValue;
            } else if (i13 == 1) {
                i9 = (int) (dFloatValue * 255.0d);
            } else if (i13 == 2) {
                i10 = (int) (dFloatValue * 255.0d);
            } else if (i13 == 3) {
                iArr[i12] = Color.argb(255, i9, i10, (int) (dFloatValue * 255.0d));
            }
        }
        v1.c cVar = new v1.c(fArr, iArr);
        b(cVar, arrayList);
        return cVar;
    }
}
