package y1;

import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: DocumentDataParser.java */
/* JADX INFO: loaded from: classes.dex */
public class h implements j0<DocumentData> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h f9192a = new h();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final JsonReader.a f9193b = JsonReader.a.a("t", "f", "s", "j", "tr", "lh", "ls", "fc", "sc", "sw", "of");

    @Override // y1.j0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public DocumentData a(JsonReader jsonReader, float f8) throws IOException {
        DocumentData.Justification justification = DocumentData.Justification.CENTER;
        jsonReader.i();
        DocumentData.Justification justification2 = justification;
        String strV = null;
        String strV2 = null;
        int iR = 0;
        int iD = 0;
        int iD2 = 0;
        float fQ = 0.0f;
        float fQ2 = 0.0f;
        float fQ3 = 0.0f;
        float fQ4 = 0.0f;
        boolean zO = true;
        while (jsonReader.n()) {
            switch (jsonReader.C(f9193b)) {
                case 0:
                    strV = jsonReader.v();
                    break;
                case 1:
                    strV2 = jsonReader.v();
                    break;
                case 2:
                    fQ = (float) jsonReader.q();
                    break;
                case 3:
                    int iR2 = jsonReader.r();
                    justification2 = DocumentData.Justification.CENTER;
                    if (iR2 <= justification2.ordinal() && iR2 >= 0) {
                        justification2 = DocumentData.Justification.values()[iR2];
                    }
                    break;
                case 4:
                    iR = jsonReader.r();
                    break;
                case 5:
                    fQ2 = (float) jsonReader.q();
                    break;
                case 6:
                    fQ3 = (float) jsonReader.q();
                    break;
                case 7:
                    iD = p.d(jsonReader);
                    break;
                case 8:
                    iD2 = p.d(jsonReader);
                    break;
                case 9:
                    fQ4 = (float) jsonReader.q();
                    break;
                case 10:
                    zO = jsonReader.o();
                    break;
                default:
                    jsonReader.E();
                    jsonReader.F();
                    break;
            }
        }
        jsonReader.m();
        return new DocumentData(strV, strV2, fQ, justification2, iR, fQ2, fQ3, iD, iD2, fQ4, zO);
    }
}
