package com.airbnb.lottie.t;

import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: DocumentDataParser.java */
/* JADX INFO: loaded from: classes.dex */
public class h implements j0<DocumentData> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h f3150a = new h();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final JsonReader.a f3151b = JsonReader.a.a("t", "f", "s", "j", "tr", "lh", "ls", "fc", "sc", "sw", "of");

    private h() {
    }

    @Override // com.airbnb.lottie.t.j0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public DocumentData a(JsonReader jsonReader, float f) throws IOException {
        DocumentData.Justification justification = DocumentData.Justification.CENTER;
        jsonReader.m();
        DocumentData.Justification justification2 = justification;
        String strV = null;
        String strV2 = null;
        int iT = 0;
        int iD = 0;
        int iD2 = 0;
        double dS = 0.0d;
        double dS2 = 0.0d;
        double dS3 = 0.0d;
        double dS4 = 0.0d;
        boolean zR = true;
        while (jsonReader.q()) {
            switch (jsonReader.z(f3151b)) {
                case 0:
                    strV = jsonReader.v();
                    break;
                case 1:
                    strV2 = jsonReader.v();
                    break;
                case 2:
                    dS = jsonReader.s();
                    break;
                case 3:
                    int iT2 = jsonReader.t();
                    justification2 = DocumentData.Justification.CENTER;
                    if (iT2 <= justification2.ordinal() && iT2 >= 0) {
                        justification2 = DocumentData.Justification.values()[iT2];
                    }
                    break;
                case 4:
                    iT = jsonReader.t();
                    break;
                case 5:
                    dS2 = jsonReader.s();
                    break;
                case 6:
                    dS3 = jsonReader.s();
                    break;
                case 7:
                    iD = p.d(jsonReader);
                    break;
                case 8:
                    iD2 = p.d(jsonReader);
                    break;
                case 9:
                    dS4 = jsonReader.s();
                    break;
                case 10:
                    zR = jsonReader.r();
                    break;
                default:
                    jsonReader.A();
                    jsonReader.B();
                    break;
            }
        }
        jsonReader.o();
        return new DocumentData(strV, strV2, dS, justification2, iT, dS2, dS3, iD, iD2, dS4, zR);
    }
}
