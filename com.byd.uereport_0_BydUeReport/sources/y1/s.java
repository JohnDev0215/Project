package y1;

import android.graphics.Color;
import android.graphics.Rect;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: compiled from: LayerParser.java */
/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final JsonReader.a f9214a = JsonReader.a.a("nm", "ind", "refId", "ty", "parent", "sw", BydAdbHelper.COMMAND_SH, "sc", "ks", "tt", "masksProperties", "shapes", "t", "ef", "sr", "st", "w", "h", "ip", "op", "tm", "cl", "hd");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final JsonReader.a f9215b = JsonReader.a.a("d", "a");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final JsonReader.a f9216c = JsonReader.a.a("nm");

    public static Layer a(o1.e eVar) {
        Rect rectB = eVar.b();
        return new Layer(Collections.emptyList(), eVar, "__container", -1L, Layer.LayerType.PRE_COMP, -1L, null, Collections.emptyList(), new u1.l(), 0, 0, 0, 0.0f, 0.0f, rectB.width(), rectB.height(), null, null, Collections.emptyList(), Layer.MatteType.NONE, null, false);
    }

    public static Layer b(JsonReader jsonReader, o1.e eVar) throws IOException {
        ArrayList arrayList;
        ArrayList arrayList2;
        float f8;
        Layer.MatteType matteType = Layer.MatteType.NONE;
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        jsonReader.i();
        Float fValueOf = Float.valueOf(1.0f);
        Float fValueOf2 = Float.valueOf(0.0f);
        Layer.MatteType matteType2 = matteType;
        float fQ = 1.0f;
        int iR = 0;
        int iR2 = 0;
        int color = 0;
        int iR3 = 0;
        int iR4 = 0;
        boolean zO = false;
        Layer.LayerType layerType = null;
        String strV = null;
        u1.l lVarG = null;
        u1.j jVarD = null;
        u1.k kVarA = null;
        u1.b bVarF = null;
        float fQ2 = 0.0f;
        float fQ3 = 0.0f;
        float fQ4 = 0.0f;
        long jR = -1;
        long jR2 = 0;
        String strV2 = null;
        String strV3 = "UNSET";
        while (jsonReader.n()) {
            switch (jsonReader.C(f9214a)) {
                case 0:
                    strV3 = jsonReader.v();
                    break;
                case 1:
                    jR2 = jsonReader.r();
                    break;
                case 2:
                    strV = jsonReader.v();
                    break;
                case 3:
                    int iR5 = jsonReader.r();
                    layerType = Layer.LayerType.UNKNOWN;
                    if (iR5 < layerType.ordinal()) {
                        layerType = Layer.LayerType.values()[iR5];
                    }
                    break;
                case 4:
                    jR = jsonReader.r();
                    break;
                case 5:
                    iR = (int) (jsonReader.r() * a2.h.e());
                    break;
                case 6:
                    iR2 = (int) (jsonReader.r() * a2.h.e());
                    break;
                case 7:
                    color = Color.parseColor(jsonReader.v());
                    break;
                case 8:
                    lVarG = c.g(jsonReader, eVar);
                    break;
                case 9:
                    matteType2 = Layer.MatteType.values()[jsonReader.r()];
                    eVar.q(1);
                    break;
                case 10:
                    jsonReader.g();
                    while (jsonReader.n()) {
                        arrayList3.add(u.a(jsonReader, eVar));
                    }
                    eVar.q(arrayList3.size());
                    jsonReader.j();
                    break;
                case 11:
                    jsonReader.g();
                    while (jsonReader.n()) {
                        v1.b bVarA = g.a(jsonReader, eVar);
                        if (bVarA != null) {
                            arrayList4.add(bVarA);
                        }
                    }
                    jsonReader.j();
                    break;
                case 12:
                    jsonReader.i();
                    while (jsonReader.n()) {
                        int iC = jsonReader.C(f9215b);
                        if (iC == 0) {
                            jVarD = d.d(jsonReader, eVar);
                        } else if (iC != 1) {
                            jsonReader.E();
                            jsonReader.F();
                        } else {
                            jsonReader.g();
                            if (jsonReader.n()) {
                                kVarA = b.a(jsonReader, eVar);
                            }
                            while (jsonReader.n()) {
                                jsonReader.F();
                            }
                            jsonReader.j();
                        }
                    }
                    jsonReader.m();
                    break;
                case 13:
                    jsonReader.g();
                    ArrayList arrayList5 = new ArrayList();
                    while (jsonReader.n()) {
                        jsonReader.i();
                        while (jsonReader.n()) {
                            if (jsonReader.C(f9216c) != 0) {
                                jsonReader.E();
                                jsonReader.F();
                            } else {
                                arrayList5.add(jsonReader.v());
                            }
                        }
                        jsonReader.m();
                    }
                    jsonReader.j();
                    eVar.a("Lottie doesn't support layer effects. If you are using them for  fills, strokes, trim paths etc. then try adding them directly as contents  in your shape. Found: " + arrayList5);
                    break;
                case 14:
                    fQ = (float) jsonReader.q();
                    break;
                case 15:
                    fQ4 = (float) jsonReader.q();
                    break;
                case 16:
                    iR3 = (int) (jsonReader.r() * a2.h.e());
                    break;
                case 17:
                    iR4 = (int) (jsonReader.r() * a2.h.e());
                    break;
                case 18:
                    fQ2 = (float) jsonReader.q();
                    break;
                case 19:
                    fQ3 = (float) jsonReader.q();
                    break;
                case 20:
                    bVarF = d.f(jsonReader, eVar, false);
                    break;
                case 21:
                    strV2 = jsonReader.v();
                    break;
                case 22:
                    zO = jsonReader.o();
                    break;
                default:
                    jsonReader.E();
                    jsonReader.F();
                    break;
            }
        }
        jsonReader.m();
        float f9 = fQ2 / fQ;
        float f10 = fQ3 / fQ;
        ArrayList arrayList6 = new ArrayList();
        if (f9 > 0.0f) {
            arrayList = arrayList3;
            arrayList2 = arrayList6;
            arrayList2.add(new b2.a(eVar, fValueOf2, fValueOf2, null, 0.0f, Float.valueOf(f9)));
            f8 = 0.0f;
        } else {
            arrayList = arrayList3;
            arrayList2 = arrayList6;
            f8 = 0.0f;
        }
        if (f10 <= f8) {
            f10 = eVar.f();
        }
        arrayList2.add(new b2.a(eVar, fValueOf, fValueOf, null, f9, Float.valueOf(f10)));
        arrayList2.add(new b2.a(eVar, fValueOf2, fValueOf2, null, f10, Float.valueOf(Float.MAX_VALUE)));
        if (strV3.endsWith(".ai") || "ai".equals(strV2)) {
            eVar.a("Convert your Illustrator layers to shape layers.");
        }
        return new Layer(arrayList4, eVar, strV3, jR2, layerType, jR, strV, arrayList, lVarG, iR, iR2, color, fQ, fQ4, iR3, iR4, jVarD, kVarA, arrayList2, matteType2, bVarF, zO);
    }
}
