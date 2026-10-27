package com.airbnb.lottie.t;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: ScaleXYParser.java */
/* JADX INFO: loaded from: classes.dex */
public class c0 implements j0<com.airbnb.lottie.v.d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c0 f2761a = new c0();

    private c0() {
    }

    @Override // com.airbnb.lottie.t.j0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public com.airbnb.lottie.v.d a(JsonReader jsonReader, float f) throws IOException {
        boolean z = jsonReader.w() == JsonReader.Token.BEGIN_ARRAY;
        if (z) {
            jsonReader.k();
        }
        float fR = (float) jsonReader.r();
        float fR2 = (float) jsonReader.r();
        while (jsonReader.p()) {
            jsonReader.A();
        }
        if (z) {
            jsonReader.m();
        }
        return new com.airbnb.lottie.v.d((fR / 100.0f) * f, (fR2 / 100.0f) * f);
    }
}
