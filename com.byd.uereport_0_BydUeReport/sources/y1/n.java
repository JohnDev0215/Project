package y1;

import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.model.content.ShapeStroke;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: GradientStrokeParser.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static JsonReader.a f9204a = JsonReader.a.a("nm", "g", "o", "t", "s", "e", "w", "lc", "lj", "ml", "hd", "d");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final JsonReader.a f9205b = JsonReader.a.a("p", "k");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final JsonReader.a f9206c = JsonReader.a.a("n", "v");

    public static v1.e a(JsonReader jsonReader, o1.e eVar) throws IOException {
        u1.c cVar;
        ArrayList arrayList = new ArrayList();
        float fQ = 0.0f;
        String strV = null;
        GradientType gradientType = null;
        u1.c cVarG = null;
        u1.d dVarH = null;
        u1.f fVarI = null;
        u1.f fVarI2 = null;
        u1.b bVarE = null;
        ShapeStroke.LineCapType lineCapType = null;
        ShapeStroke.LineJoinType lineJoinType = null;
        u1.b bVar = null;
        boolean zO = false;
        while (jsonReader.n()) {
            switch (jsonReader.C(f9204a)) {
                case 0:
                    strV = jsonReader.v();
                    break;
                case 1:
                    int iR = -1;
                    jsonReader.i();
                    while (jsonReader.n()) {
                        int iC = jsonReader.C(f9205b);
                        if (iC != 0) {
                            cVar = cVarG;
                            if (iC != 1) {
                                jsonReader.E();
                                jsonReader.F();
                            } else {
                                cVarG = d.g(jsonReader, eVar, iR);
                            }
                        } else {
                            cVar = cVarG;
                            iR = jsonReader.r();
                        }
                        cVarG = cVar;
                    }
                    jsonReader.m();
                    break;
                case 2:
                    dVarH = d.h(jsonReader, eVar);
                    break;
                case 3:
                    gradientType = jsonReader.r() == 1 ? GradientType.LINEAR : GradientType.RADIAL;
                    break;
                case 4:
                    fVarI = d.i(jsonReader, eVar);
                    break;
                case 5:
                    fVarI2 = d.i(jsonReader, eVar);
                    break;
                case 6:
                    bVarE = d.e(jsonReader, eVar);
                    break;
                case 7:
                    lineCapType = ShapeStroke.LineCapType.values()[jsonReader.r() - 1];
                    break;
                case 8:
                    lineJoinType = ShapeStroke.LineJoinType.values()[jsonReader.r() - 1];
                    break;
                case 9:
                    fQ = (float) jsonReader.q();
                    break;
                case 10:
                    zO = jsonReader.o();
                    break;
                case 11:
                    jsonReader.g();
                    while (jsonReader.n()) {
                        jsonReader.i();
                        String strV2 = null;
                        u1.b bVarE2 = null;
                        while (jsonReader.n()) {
                            int iC2 = jsonReader.C(f9206c);
                            if (iC2 != 0) {
                                u1.b bVar2 = bVar;
                                if (iC2 != 1) {
                                    jsonReader.E();
                                    jsonReader.F();
                                } else {
                                    bVarE2 = d.e(jsonReader, eVar);
                                }
                                bVar = bVar2;
                            } else {
                                strV2 = jsonReader.v();
                            }
                        }
                        u1.b bVar3 = bVar;
                        jsonReader.m();
                        if (strV2.equals("o")) {
                            bVar = bVarE2;
                        } else {
                            if (strV2.equals("d") || strV2.equals("g")) {
                                eVar.t(true);
                                arrayList.add(bVarE2);
                            }
                            bVar = bVar3;
                        }
                    }
                    u1.b bVar4 = bVar;
                    jsonReader.j();
                    if (arrayList.size() == 1) {
                        arrayList.add(arrayList.get(0));
                    }
                    bVar = bVar4;
                    break;
                default:
                    jsonReader.E();
                    jsonReader.F();
                    break;
            }
        }
        return new v1.e(strV, gradientType, cVarG, dVarH, fVarI, fVarI2, bVarE, lineCapType, lineJoinType, fQ, arrayList, bVar, zO);
    }
}
