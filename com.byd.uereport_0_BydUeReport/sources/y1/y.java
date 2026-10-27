package y1;

import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: PointFParser.java */
/* JADX INFO: loaded from: classes.dex */
public class y implements j0<PointF> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final y f9223a = new y();

    @Override // y1.j0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public PointF a(JsonReader jsonReader, float f8) throws IOException {
        JsonReader.Token tokenX = jsonReader.x();
        if (tokenX == JsonReader.Token.BEGIN_ARRAY) {
            return p.e(jsonReader, f8);
        }
        if (tokenX == JsonReader.Token.BEGIN_OBJECT) {
            return p.e(jsonReader, f8);
        }
        if (tokenX == JsonReader.Token.NUMBER) {
            PointF pointF = new PointF(((float) jsonReader.q()) * f8, ((float) jsonReader.q()) * f8);
            while (jsonReader.n()) {
                jsonReader.F();
            }
            return pointF;
        }
        throw new IllegalArgumentException("Cannot convert json to point. Next token is " + tokenX);
    }
}
