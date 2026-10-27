package com.airbnb.lottie.t;

import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: PointFParser.java */
/* JADX INFO: loaded from: classes.dex */
public class y implements j0<PointF> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final y f3181a = new y();

    private y() {
    }

    @Override // com.airbnb.lottie.t.j0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public PointF a(JsonReader jsonReader, float f) throws IOException {
        JsonReader.Token tokenX = jsonReader.x();
        if (tokenX == JsonReader.Token.BEGIN_ARRAY) {
            return p.e(jsonReader, f);
        }
        if (tokenX == JsonReader.Token.BEGIN_OBJECT) {
            return p.e(jsonReader, f);
        }
        if (tokenX == JsonReader.Token.NUMBER) {
            PointF pointF = new PointF(((float) jsonReader.s()) * f, ((float) jsonReader.s()) * f);
            while (jsonReader.q()) {
                jsonReader.B();
            }
            return pointF;
        }
        throw new IllegalArgumentException("Cannot convert json to point. Next token is " + tokenX);
    }
}
