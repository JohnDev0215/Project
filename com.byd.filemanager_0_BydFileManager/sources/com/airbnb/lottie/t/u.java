package com.airbnb.lottie.t;

import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: MaskParser.java */
/* JADX INFO: loaded from: classes.dex */
class u {
    static Mask a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        String strT;
        jsonReader.l();
        Mask.MaskMode maskMode = null;
        boolean zQ = false;
        com.airbnb.lottie.model.i.h hVarK = null;
        com.airbnb.lottie.model.i.d dVarH = null;
        while (jsonReader.p()) {
            strT = jsonReader.t();
            strT.hashCode();
            switch (strT) {
                case "o":
                    dVarH = d.h(jsonReader, dVar);
                    break;
                case "pt":
                    hVarK = d.k(jsonReader, dVar);
                    break;
                case "inv":
                    zQ = jsonReader.q();
                    break;
                case "mode":
                    String strU = jsonReader.u();
                    strU.hashCode();
                    switch (strU) {
                        case "a":
                            maskMode = Mask.MaskMode.MASK_MODE_ADD;
                            break;
                        case "i":
                            dVar.a("Animation contains intersect masks. They are not supported but will be treated like add masks.");
                            maskMode = Mask.MaskMode.MASK_MODE_INTERSECT;
                            break;
                        case "n":
                            maskMode = Mask.MaskMode.MASK_MODE_NONE;
                            break;
                        case "s":
                            maskMode = Mask.MaskMode.MASK_MODE_SUBTRACT;
                            break;
                        default:
                            com.airbnb.lottie.u.d.c("Unknown mask mode " + strT + ". Defaulting to Add.");
                            maskMode = Mask.MaskMode.MASK_MODE_ADD;
                            break;
                    }
                    break;
                default:
                    jsonReader.A();
                    break;
            }
        }
        jsonReader.n();
        return new Mask(maskMode, hVarK, dVarH, zQ);
    }
}
