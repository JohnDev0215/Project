package com.airbnb.lottie.t;

import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: ShapeDataParser.java */
/* JADX INFO: loaded from: classes.dex */
public class d0 implements j0<com.airbnb.lottie.model.content.h> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d0 f2762a = new d0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final JsonReader.a f2763b = JsonReader.a.a("c", "v", "i", "o");

    private d0() {
    }

    @Override // com.airbnb.lottie.t.j0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public com.airbnb.lottie.model.content.h a(JsonReader jsonReader, float f) throws IOException {
        if (jsonReader.w() == JsonReader.Token.BEGIN_ARRAY) {
            jsonReader.k();
        }
        jsonReader.l();
        List<PointF> listF = null;
        List<PointF> listF2 = null;
        List<PointF> listF3 = null;
        boolean zQ = false;
        while (jsonReader.p()) {
            int iY = jsonReader.y(f2763b);
            if (iY == 0) {
                zQ = jsonReader.q();
            } else if (iY == 1) {
                listF = p.f(jsonReader, f);
            } else if (iY == 2) {
                listF2 = p.f(jsonReader, f);
            } else if (iY != 3) {
                jsonReader.z();
                jsonReader.A();
            } else {
                listF3 = p.f(jsonReader, f);
            }
        }
        jsonReader.n();
        if (jsonReader.w() == JsonReader.Token.END_ARRAY) {
            jsonReader.m();
        }
        if (listF == null || listF2 == null || listF3 == null) {
            throw new IllegalArgumentException("Shape data was missing information.");
        }
        if (listF.isEmpty()) {
            return new com.airbnb.lottie.model.content.h(new PointF(), false, Collections.emptyList());
        }
        int size = listF.size();
        PointF pointF = listF.get(0);
        ArrayList arrayList = new ArrayList(size);
        for (int i = 1; i < size; i++) {
            PointF pointF2 = listF.get(i);
            int i2 = i - 1;
            arrayList.add(new com.airbnb.lottie.model.a(com.airbnb.lottie.u.g.a(listF.get(i2), listF3.get(i2)), com.airbnb.lottie.u.g.a(pointF2, listF2.get(i)), pointF2));
        }
        if (zQ) {
            PointF pointF3 = listF.get(0);
            int i3 = size - 1;
            arrayList.add(new com.airbnb.lottie.model.a(com.airbnb.lottie.u.g.a(listF.get(i3), listF3.get(i3)), com.airbnb.lottie.u.g.a(pointF3, listF2.get(0)), pointF3));
        }
        return new com.airbnb.lottie.model.content.h(pointF, zQ, arrayList);
    }
}
