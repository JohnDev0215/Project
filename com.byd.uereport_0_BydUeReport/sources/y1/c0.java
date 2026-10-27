package y1;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: ScaleXYParser.java */
/* JADX INFO: loaded from: classes.dex */
public class c0 implements j0<b2.d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c0 f9183a = new c0();

    @Override // y1.j0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public b2.d a(JsonReader jsonReader, float f8) throws IOException {
        boolean z7 = jsonReader.x() == JsonReader.Token.BEGIN_ARRAY;
        if (z7) {
            jsonReader.g();
        }
        float fQ = (float) jsonReader.q();
        float fQ2 = (float) jsonReader.q();
        while (jsonReader.n()) {
            jsonReader.F();
        }
        if (z7) {
            jsonReader.j();
        }
        return new b2.d((fQ / 100.0f) * f8, (fQ2 / 100.0f) * f8);
    }
}
