package y1;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: IntegerParser.java */
/* JADX INFO: loaded from: classes.dex */
public class o implements j0<Integer> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final o f9207a = new o();

    @Override // y1.j0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public Integer a(JsonReader jsonReader, float f8) throws IOException {
        return Integer.valueOf(Math.round(p.g(jsonReader) * f8));
    }
}
