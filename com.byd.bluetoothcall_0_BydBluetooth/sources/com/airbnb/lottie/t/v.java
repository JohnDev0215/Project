package com.airbnb.lottie.t;

import com.airbnb.lottie.model.content.MergePaths;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: MergePathsParser.java */
/* JADX INFO: loaded from: classes.dex */
class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final JsonReader.a f3179a = JsonReader.a.a("nm", "mm", "hd");

    static MergePaths a(JsonReader jsonReader) throws IOException {
        String strV = null;
        boolean zR = false;
        MergePaths.MergePathsMode mergePathsModeForId = null;
        while (jsonReader.q()) {
            int iZ = jsonReader.z(f3179a);
            if (iZ == 0) {
                strV = jsonReader.v();
            } else if (iZ == 1) {
                mergePathsModeForId = MergePaths.MergePathsMode.forId(jsonReader.t());
            } else if (iZ != 2) {
                jsonReader.A();
                jsonReader.B();
            } else {
                zR = jsonReader.r();
            }
        }
        return new MergePaths(strV, mergePathsModeForId, zR);
    }
}
