package y1;

import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: MaskParser.java */
/* JADX INFO: loaded from: classes.dex */
public class u {
    public static Mask a(JsonReader jsonReader, o1.e eVar) throws IOException {
        String strT;
        jsonReader.i();
        Mask.MaskMode maskMode = null;
        boolean zO = false;
        u1.h hVarK = null;
        u1.d dVarH = null;
        while (jsonReader.n()) {
            strT = jsonReader.t();
            strT.hashCode();
            switch (strT) {
                case "o":
                    dVarH = d.h(jsonReader, eVar);
                    break;
                case "pt":
                    hVarK = d.k(jsonReader, eVar);
                    break;
                case "inv":
                    zO = jsonReader.o();
                    break;
                case "mode":
                    String strV = jsonReader.v();
                    strV.hashCode();
                    switch (strV) {
                        case "a":
                            maskMode = Mask.MaskMode.MASK_MODE_ADD;
                            break;
                        case "i":
                            eVar.a("Animation contains intersect masks. They are not supported but will be treated like add masks.");
                            maskMode = Mask.MaskMode.MASK_MODE_INTERSECT;
                            break;
                        case "n":
                            maskMode = Mask.MaskMode.MASK_MODE_NONE;
                            break;
                        case "s":
                            maskMode = Mask.MaskMode.MASK_MODE_SUBTRACT;
                            break;
                        default:
                            a2.d.c("Unknown mask mode " + strT + ". Defaulting to Add.");
                            maskMode = Mask.MaskMode.MASK_MODE_ADD;
                            break;
                    }
                    break;
                default:
                    jsonReader.F();
                    break;
            }
        }
        jsonReader.m();
        return new Mask(maskMode, hVarK, dVarH, zO);
    }
}
