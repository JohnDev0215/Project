package p;

import android.util.Base64;
import android.util.JsonReader;
import com.spotify.connectivity.productstate.RxProductState;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class i40 {
    public static final p01 a;

    static {
        g71 g71Var = new g71();
        u11.l.u(g71Var);
        g71Var.d = true;
        a = new p01(20, g71Var);
    }

    public static uh a(JsonReader jsonReader) throws IOException {
        pi piVar = new pi();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            strNextName.getClass();
            switch (strNextName) {
                case "offset":
                    piVar.d = Long.valueOf(jsonReader.nextLong());
                    break;
                case "symbol":
                    String strNextString = jsonReader.nextString();
                    if (strNextString == null) {
                        throw new NullPointerException("Null symbol");
                    }
                    piVar.b = strNextString;
                    break;
                    break;
                case "pc":
                    piVar.a = Long.valueOf(jsonReader.nextLong());
                    break;
                case "file":
                    piVar.c = jsonReader.nextString();
                    break;
                case "importance":
                    piVar.e = Integer.valueOf(jsonReader.nextInt());
                    break;
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        return piVar.d();
    }

    public static fh b(JsonReader jsonReader) throws IOException {
        ay2 ay2Var = new ay2(10);
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            strNextName.getClass();
            if (strNextName.equals("key")) {
                String strNextString = jsonReader.nextString();
                if (strNextString == null) {
                    throw new NullPointerException("Null key");
                }
                ay2Var.c = strNextString;
            } else if (strNextName.equals("value")) {
                String strNextString2 = jsonReader.nextString();
                if (strNextString2 == null) {
                    throw new NullPointerException("Null value");
                }
                ay2Var.e = strNextString2;
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return ay2Var.b();
    }

    public static dh c(JsonReader jsonReader) throws IOException {
        uy2 uy2Var = new uy2();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            strNextName.getClass();
            switch (strNextName) {
                case "buildIdMappingForArch":
                    uy2Var.i = d(jsonReader, new iy2(22));
                    break;
                case "pid":
                    uy2Var.a = Integer.valueOf(jsonReader.nextInt());
                    break;
                case "pss":
                    uy2Var.e = Long.valueOf(jsonReader.nextLong());
                    break;
                case "rss":
                    uy2Var.f = Long.valueOf(jsonReader.nextLong());
                    break;
                case "timestamp":
                    uy2Var.g = Long.valueOf(jsonReader.nextLong());
                    break;
                case "processName":
                    String strNextString = jsonReader.nextString();
                    if (strNextString == null) {
                        throw new NullPointerException("Null processName");
                    }
                    uy2Var.b = strNextString;
                    break;
                    break;
                case "reasonCode":
                    uy2Var.c = Integer.valueOf(jsonReader.nextInt());
                    break;
                case "traceFile":
                    uy2Var.h = jsonReader.nextString();
                    break;
                case "importance":
                    uy2Var.d = Integer.valueOf(jsonReader.nextInt());
                    break;
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        return uy2Var.b();
    }

    public static z31 d(JsonReader jsonReader, iy2 iy2Var) throws IOException {
        Object objE;
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            switch (iy2Var.c) {
                case 18:
                    objE = e(jsonReader);
                    break;
                case 19:
                    g90 g90Var = new g90(9);
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String strNextName = jsonReader.nextName();
                        strNextName.getClass();
                        if (strNextName.equals("filename")) {
                            String strNextString = jsonReader.nextString();
                            if (strNextString == null) {
                                throw new NullPointerException("Null filename");
                            }
                            g90Var.e = strNextString;
                        } else if (strNextName.equals("contents")) {
                            byte[] bArrDecode = Base64.decode(jsonReader.nextString(), 2);
                            if (bArrDecode == null) {
                                throw new NullPointerException("Null contents");
                            }
                            g90Var.k = bArrDecode;
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    objE = g90Var.b();
                    break;
                case 20:
                case 21:
                    objE = b(jsonReader);
                    break;
                case 22:
                    kk0 kk0Var = new kk0(15);
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String strNextName2 = jsonReader.nextName();
                        strNextName2.getClass();
                        switch (strNextName2) {
                            case "libraryName":
                                String strNextString2 = jsonReader.nextString();
                                if (strNextString2 == null) {
                                    throw new NullPointerException("Null libraryName");
                                }
                                kk0Var.k = strNextString2;
                                break;
                                break;
                            case "arch":
                                String strNextString3 = jsonReader.nextString();
                                if (strNextString3 == null) {
                                    throw new NullPointerException("Null arch");
                                }
                                kk0Var.e = strNextString3;
                                break;
                                break;
                            case "buildId":
                                String strNextString4 = jsonReader.nextString();
                                if (strNextString4 == null) {
                                    throw new NullPointerException("Null buildId");
                                }
                                kk0Var.l = strNextString4;
                                break;
                                break;
                            default:
                                jsonReader.skipValue();
                                break;
                        }
                    }
                    jsonReader.endObject();
                    objE = kk0Var.g();
                    break;
                case 23:
                    kk0 kk0Var2 = new kk0(16);
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String strNextName3 = jsonReader.nextName();
                        strNextName3.getClass();
                        switch (strNextName3) {
                            case "frames":
                                kk0Var2.l = d(jsonReader, new iy2(25));
                                break;
                            case "name":
                                String strNextString5 = jsonReader.nextString();
                                if (strNextString5 == null) {
                                    throw new NullPointerException("Null name");
                                }
                                kk0Var2.e = strNextString5;
                                break;
                                break;
                            case "importance":
                                kk0Var2.k = Integer.valueOf(jsonReader.nextInt());
                                break;
                            default:
                                jsonReader.skipValue();
                                break;
                        }
                    }
                    jsonReader.endObject();
                    objE = kk0Var2.h();
                    break;
                case 24:
                    gh2 gh2Var = new gh2(11);
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String strNextName4 = jsonReader.nextName();
                        strNextName4.getClass();
                        switch (strNextName4) {
                            case "name":
                                String strNextString6 = jsonReader.nextString();
                                if (strNextString6 == null) {
                                    throw new NullPointerException("Null name");
                                }
                                gh2Var.l = strNextString6;
                                break;
                                break;
                            case "size":
                                gh2Var.k = Long.valueOf(jsonReader.nextLong());
                                break;
                            case "uuid":
                                gh2Var.m = new String(Base64.decode(jsonReader.nextString(), 2), g40.a);
                                break;
                            case "baseAddress":
                                gh2Var.e = Long.valueOf(jsonReader.nextLong());
                                break;
                            default:
                                jsonReader.skipValue();
                                break;
                        }
                    }
                    jsonReader.endObject();
                    objE = gh2Var.g();
                    break;
                case 25:
                default:
                    objE = a(jsonReader);
                    break;
            }
            arrayList.add(objE);
        }
        jsonReader.endArray();
        return new z31(arrayList);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static nh e(JsonReader jsonReader) throws IOException {
        byte b;
        String strConcat;
        pi piVar = new pi();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            strNextName.getClass();
            switch (strNextName.hashCode()) {
                case -1335157162:
                    b = !strNextName.equals("device") ? (byte) -1 : (byte) 0;
                    break;
                case 96801:
                    b = !strNextName.equals("app") ? (byte) -1 : (byte) 1;
                    break;
                case 107332:
                    b = !strNextName.equals("log") ? (byte) -1 : (byte) 2;
                    break;
                case 3575610:
                    b = !strNextName.equals(RxProductState.Keys.KEY_TYPE) ? (byte) -1 : (byte) 3;
                    break;
                case 55126294:
                    b = !strNextName.equals("timestamp") ? (byte) -1 : (byte) 4;
                    break;
                default:
                    b = -1;
                    break;
            }
            String str = null;
            switch (b) {
                case 0:
                    gu0 gu0Var = new gu0(6);
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String strNextName2 = jsonReader.nextName();
                        strNextName2.getClass();
                        switch (strNextName2) {
                            case "batteryLevel":
                                gu0Var.a = Double.valueOf(jsonReader.nextDouble());
                                break;
                            case "batteryVelocity":
                                gu0Var.b = Integer.valueOf(jsonReader.nextInt());
                                break;
                            case "orientation":
                                gu0Var.d = Integer.valueOf(jsonReader.nextInt());
                                break;
                            case "diskUsed":
                                gu0Var.f = Long.valueOf(jsonReader.nextLong());
                                break;
                            case "ramUsed":
                                gu0Var.e = Long.valueOf(jsonReader.nextLong());
                                break;
                            case "proximityOn":
                                gu0Var.c = Boolean.valueOf(jsonReader.nextBoolean());
                                break;
                            default:
                                jsonReader.skipValue();
                                break;
                        }
                    }
                    jsonReader.endObject();
                    piVar.d = gu0Var.c();
                    break;
                case 1:
                    jsonReader.beginObject();
                    ph phVar = null;
                    Integer numValueOf = null;
                    z31 z31VarD = null;
                    z31 z31VarD2 = null;
                    Boolean boolValueOf = null;
                    while (jsonReader.hasNext()) {
                        String strNextName3 = jsonReader.nextName();
                        strNextName3.getClass();
                        switch (strNextName3) {
                            case "background":
                                boolValueOf = Boolean.valueOf(jsonReader.nextBoolean());
                                break;
                            case "execution":
                                jsonReader.beginObject();
                                sh shVarC = null;
                                rh rhVarF = null;
                                dh dhVarC = null;
                                z31 z31VarD3 = null;
                                z31 z31VarD4 = null;
                                while (jsonReader.hasNext()) {
                                    String strNextName4 = jsonReader.nextName();
                                    strNextName4.getClass();
                                    switch (strNextName4) {
                                        case "appExitInfo":
                                            dhVarC = c(jsonReader);
                                            break;
                                        case "threads":
                                            z31VarD3 = d(jsonReader, new iy2(23));
                                            break;
                                        case "signal":
                                            v4 v4Var = new v4(15);
                                            jsonReader.beginObject();
                                            while (jsonReader.hasNext()) {
                                                String strNextName5 = jsonReader.nextName();
                                                strNextName5.getClass();
                                                switch (strNextName5) {
                                                    case "address":
                                                        v4Var.l = Long.valueOf(jsonReader.nextLong());
                                                        break;
                                                    case "code":
                                                        String strNextString = jsonReader.nextString();
                                                        if (strNextString == null) {
                                                            throw new NullPointerException("Null code");
                                                        }
                                                        v4Var.k = strNextString;
                                                        break;
                                                        break;
                                                    case "name":
                                                        String strNextString2 = jsonReader.nextString();
                                                        if (strNextString2 == null) {
                                                            throw new NullPointerException("Null name");
                                                        }
                                                        v4Var.e = strNextString2;
                                                        break;
                                                        break;
                                                    default:
                                                        jsonReader.skipValue();
                                                        break;
                                                }
                                            }
                                            jsonReader.endObject();
                                            shVarC = v4Var.c();
                                            break;
                                        case "binaries":
                                            z31VarD4 = d(jsonReader, new iy2(24));
                                            break;
                                        case "exception":
                                            rhVarF = f(jsonReader);
                                            break;
                                        default:
                                            jsonReader.skipValue();
                                            break;
                                    }
                                }
                                jsonReader.endObject();
                                String strConcat2 = shVarC == null ? " signal" : "";
                                if (z31VarD4 == null) {
                                    strConcat2 = strConcat2.concat(" binaries");
                                }
                                if (!strConcat2.isEmpty()) {
                                    throw new IllegalStateException("Missing required properties:".concat(strConcat2));
                                }
                                phVar = new ph(z31VarD3, rhVarF, dhVarC, shVarC, z31VarD4);
                                break;
                                break;
                            case "internalKeys":
                                z31VarD2 = d(jsonReader, new iy2(21));
                                break;
                            case "customAttributes":
                                z31VarD = d(jsonReader, new iy2(20));
                                break;
                            case "uiOrientation":
                                numValueOf = Integer.valueOf(jsonReader.nextInt());
                                break;
                            default:
                                jsonReader.skipValue();
                                break;
                        }
                    }
                    jsonReader.endObject();
                    strConcat = phVar == null ? " execution" : "";
                    if (numValueOf == null) {
                        strConcat = strConcat.concat(" uiOrientation");
                    }
                    if (!strConcat.isEmpty()) {
                        throw new IllegalStateException("Missing required properties:".concat(strConcat));
                    }
                    piVar.c = new oh(phVar, z31VarD, z31VarD2, boolValueOf, numValueOf.intValue());
                    break;
                    break;
                case 2:
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String strNextName6 = jsonReader.nextName();
                        strNextName6.getClass();
                        if (strNextName6.equals("content")) {
                            String strNextString3 = jsonReader.nextString();
                            if (strNextString3 == null) {
                                throw new NullPointerException("Null content");
                            }
                            str = strNextString3;
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    strConcat = str == null ? " content" : "";
                    if (!strConcat.isEmpty()) {
                        throw new IllegalStateException("Missing required properties:".concat(strConcat));
                    }
                    piVar.e = new wh(str);
                    break;
                    break;
                case 3:
                    String strNextString4 = jsonReader.nextString();
                    if (strNextString4 == null) {
                        throw new NullPointerException("Null type");
                    }
                    piVar.b = strNextString4;
                    break;
                    break;
                case 4:
                    piVar.a = Long.valueOf(jsonReader.nextLong());
                    break;
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        return piVar.b();
    }

    public static rh f(JsonReader jsonReader) throws IOException {
        pi piVar = new pi();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            strNextName.getClass();
            switch (strNextName) {
                case "frames":
                    piVar.c = d(jsonReader, new iy2(26));
                    break;
                case "reason":
                    piVar.a = jsonReader.nextString();
                    break;
                case "type":
                    String strNextString = jsonReader.nextString();
                    if (strNextString == null) {
                        throw new NullPointerException("Null type");
                    }
                    piVar.b = strNextString;
                    break;
                    break;
                case "causedBy":
                    piVar.d = f(jsonReader);
                    break;
                case "overflowCount":
                    piVar.e = Integer.valueOf(jsonReader.nextInt());
                    break;
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        return piVar.c();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static p.bh g(android.util.JsonReader r26) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1582
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.i40.g(android.util.JsonReader):p.bh");
    }

    public static bh h(String str) {
        try {
            JsonReader jsonReader = new JsonReader(new StringReader(str));
            try {
                bh bhVarG = g(jsonReader);
                jsonReader.close();
                return bhVarG;
            } finally {
            }
        } catch (IllegalStateException e) {
            throw new IOException(e);
        }
    }
}
