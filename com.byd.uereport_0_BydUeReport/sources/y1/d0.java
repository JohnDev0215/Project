package y1;

import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: ShapeDataParser.java */
/* JADX INFO: loaded from: classes.dex */
public class d0 implements j0<v1.h> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d0 f9184a = new d0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final JsonReader.a f9185b = JsonReader.a.a("c", "v", "i", "o");

    @Override // y1.j0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public v1.h a(JsonReader jsonReader, float f8) throws IOException {
        if (jsonReader.x() == JsonReader.Token.BEGIN_ARRAY) {
            jsonReader.g();
        }
        jsonReader.i();
        List<PointF> listF = null;
        List<PointF> listF2 = null;
        List<PointF> listF3 = null;
        boolean zO = false;
        while (jsonReader.n()) {
            int iC = jsonReader.C(f9185b);
            if (iC == 0) {
                zO = jsonReader.o();
            } else if (iC == 1) {
                listF = p.f(jsonReader, f8);
            } else if (iC == 2) {
                listF2 = p.f(jsonReader, f8);
            } else if (iC != 3) {
                jsonReader.E();
                jsonReader.F();
            } else {
                listF3 = p.f(jsonReader, f8);
            }
        }
        jsonReader.m();
        if (jsonReader.x() == JsonReader.Token.END_ARRAY) {
            jsonReader.j();
        }
        if (listF == null || listF2 == null || listF3 == null) {
            throw new IllegalArgumentException("Shape data was missing information.");
        }
        if (listF.isEmpty()) {
            return new v1.h(new PointF(), false, Collections.emptyList());
        }
        int size = listF.size();
        PointF pointF = listF.get(0);
        ArrayList arrayList = new ArrayList(size);
        for (int i8 = 1; i8 < size; i8++) {
            PointF pointF2 = listF.get(i8);
            int i9 = i8 - 1;
            arrayList.add(new t1.a(a2.g.a(listF.get(i9), listF3.get(i9)), a2.g.a(pointF2, listF2.get(i8)), pointF2));
        }
        if (zO) {
            PointF pointF3 = listF.get(0);
            int i10 = size - 1;
            arrayList.add(new t1.a(a2.g.a(listF.get(i10), listF3.get(i10)), a2.g.a(pointF3, listF2.get(0)), pointF3));
        }
        return new v1.h(pointF, zO, arrayList);
    }
}
