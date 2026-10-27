package com.airbnb.lottie.t;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: FloatParser.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements j0<Float> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i f3154a = new i();

    private i() {
    }

    @Override // com.airbnb.lottie.t.j0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public Float a(JsonReader jsonReader, float f) throws IOException {
        return Float.valueOf(p.g(jsonReader) * f);
    }
}
