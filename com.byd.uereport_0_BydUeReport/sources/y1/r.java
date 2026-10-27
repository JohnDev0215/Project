package y1;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: KeyframesParser.java */
/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static JsonReader.a f9213a = JsonReader.a.a("k");

    public static <T> List<b2.a<T>> a(JsonReader jsonReader, o1.e eVar, float f8, j0<T> j0Var) throws IOException {
        ArrayList arrayList = new ArrayList();
        if (jsonReader.x() == JsonReader.Token.STRING) {
            eVar.a("Lottie doesn't support expressions.");
            return arrayList;
        }
        jsonReader.i();
        while (jsonReader.n()) {
            if (jsonReader.C(f9213a) != 0) {
                jsonReader.F();
            } else if (jsonReader.x() == JsonReader.Token.BEGIN_ARRAY) {
                jsonReader.g();
                if (jsonReader.x() == JsonReader.Token.NUMBER) {
                    arrayList.add(q.b(jsonReader, eVar, f8, j0Var, false));
                } else {
                    while (jsonReader.n()) {
                        arrayList.add(q.b(jsonReader, eVar, f8, j0Var, true));
                    }
                }
                jsonReader.j();
            } else {
                arrayList.add(q.b(jsonReader, eVar, f8, j0Var, false));
            }
        }
        jsonReader.m();
        b(arrayList);
        return arrayList;
    }

    public static <T> void b(List<? extends b2.a<T>> list) {
        int i8;
        T t7;
        int size = list.size();
        int i9 = 0;
        while (true) {
            i8 = size - 1;
            if (i9 >= i8) {
                break;
            }
            b2.a<T> aVar = list.get(i9);
            i9++;
            b2.a<T> aVar2 = list.get(i9);
            aVar.f2049f = Float.valueOf(aVar2.f2048e);
            if (aVar.f2046c == null && (t7 = aVar2.f2045b) != null) {
                aVar.f2046c = t7;
                if (aVar instanceof r1.h) {
                    ((r1.h) aVar).i();
                }
            }
        }
        b2.a<T> aVar3 = list.get(i8);
        if ((aVar3.f2045b == null || aVar3.f2046c == null) && list.size() > 1) {
            list.remove(aVar3);
        }
    }
}
