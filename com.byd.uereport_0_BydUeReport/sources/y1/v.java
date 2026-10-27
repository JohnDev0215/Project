package y1;

import com.airbnb.lottie.model.content.MergePaths;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: MergePathsParser.java */
/* JADX INFO: loaded from: classes.dex */
public class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final JsonReader.a f9221a = JsonReader.a.a("nm", "mm", "hd");

    public static MergePaths a(JsonReader jsonReader) throws IOException {
        String strV = null;
        boolean zO = false;
        MergePaths.MergePathsMode mergePathsModeForId = null;
        while (jsonReader.n()) {
            int iC = jsonReader.C(f9221a);
            if (iC == 0) {
                strV = jsonReader.v();
            } else if (iC == 1) {
                mergePathsModeForId = MergePaths.MergePathsMode.forId(jsonReader.r());
            } else if (iC != 2) {
                jsonReader.E();
                jsonReader.F();
            } else {
                zO = jsonReader.o();
            }
        }
        return new MergePaths(strV, mergePathsModeForId, zO);
    }
}
