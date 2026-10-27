package y1;

import android.graphics.Color;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: ColorParser.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements j0<Integer> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f f9188a = new f();

    @Override // y1.j0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public Integer a(JsonReader jsonReader, float f8) throws IOException {
        boolean z7 = jsonReader.x() == JsonReader.Token.BEGIN_ARRAY;
        if (z7) {
            jsonReader.g();
        }
        double dQ = jsonReader.q();
        double dQ2 = jsonReader.q();
        double dQ3 = jsonReader.q();
        double dQ4 = jsonReader.q();
        if (z7) {
            jsonReader.j();
        }
        if (dQ <= 1.0d && dQ2 <= 1.0d && dQ3 <= 1.0d) {
            dQ *= 255.0d;
            dQ2 *= 255.0d;
            dQ3 *= 255.0d;
            if (dQ4 <= 1.0d) {
                dQ4 *= 255.0d;
            }
        }
        return Integer.valueOf(Color.argb((int) dQ4, (int) dQ, (int) dQ2, (int) dQ3));
    }
}
