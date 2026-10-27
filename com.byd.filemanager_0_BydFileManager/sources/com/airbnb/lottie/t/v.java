package com.airbnb.lottie.t;

import com.airbnb.lottie.model.content.MergePaths;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: MergePathsParser.java */
/* JADX INFO: loaded from: classes.dex */
class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final JsonReader.a f2799a = JsonReader.a.a("nm", "mm", "hd");

    static MergePaths a(JsonReader jsonReader) throws IOException {
        String strU = null;
        boolean zQ = false;
        MergePaths.MergePathsMode mergePathsModeForId = null;
        while (jsonReader.p()) {
            int iY = jsonReader.y(f2799a);
            if (iY == 0) {
                strU = jsonReader.u();
            } else if (iY == 1) {
                mergePathsModeForId = MergePaths.MergePathsMode.forId(jsonReader.s());
            } else if (iY != 2) {
                jsonReader.z();
                jsonReader.A();
            } else {
                zQ = jsonReader.q();
            }
        }
        return new MergePaths(strU, mergePathsModeForId, zQ);
    }
}
