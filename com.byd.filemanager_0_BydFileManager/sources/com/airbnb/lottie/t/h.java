package com.airbnb.lottie.t;

import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: DocumentDataParser.java */
/* JADX INFO: loaded from: classes.dex */
public class h implements j0<DocumentData> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h f2770a = new h();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final JsonReader.a f2771b = JsonReader.a.a("t", "f", "s", "j", "tr", "lh", "ls", "fc", "sc", "sw", "of");

    private h() {
    }

    @Override // com.airbnb.lottie.t.j0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public DocumentData a(JsonReader jsonReader, float f) throws IOException {
        DocumentData.Justification justification = DocumentData.Justification.CENTER;
        jsonReader.l();
        DocumentData.Justification justification2 = justification;
        String strU = null;
        String strU2 = null;
        int iS = 0;
        int iD = 0;
        int iD2 = 0;
        float fR = 0.0f;
        float fR2 = 0.0f;
        float fR3 = 0.0f;
        float fR4 = 0.0f;
        boolean zQ = true;
        while (jsonReader.p()) {
            switch (jsonReader.y(f2771b)) {
                case 0:
                    strU = jsonReader.u();
                    break;
                case 1:
                    strU2 = jsonReader.u();
                    break;
                case 2:
                    fR = (float) jsonReader.r();
                    break;
                case 3:
                    int iS2 = jsonReader.s();
                    justification2 = DocumentData.Justification.CENTER;
                    if (iS2 <= justification2.ordinal() && iS2 >= 0) {
                        justification2 = DocumentData.Justification.values()[iS2];
                    }
                    break;
                case 4:
                    iS = jsonReader.s();
                    break;
                case 5:
                    fR2 = (float) jsonReader.r();
                    break;
                case 6:
                    fR3 = (float) jsonReader.r();
                    break;
                case 7:
                    iD = p.d(jsonReader);
                    break;
                case 8:
                    iD2 = p.d(jsonReader);
                    break;
                case 9:
                    fR4 = (float) jsonReader.r();
                    break;
                case 10:
                    zQ = jsonReader.q();
                    break;
                default:
                    jsonReader.z();
                    jsonReader.A();
                    break;
            }
        }
        jsonReader.n();
        return new DocumentData(strU, strU2, fR, justification2, iS, fR2, fR3, iD, iD2, fR4, zQ);
    }
}
