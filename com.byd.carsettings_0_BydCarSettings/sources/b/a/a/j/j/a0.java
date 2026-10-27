package b.a.a.j.j;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.Feature;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: StackTraceElementDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class a0 implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a0 f269a = new a0();

    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        b.a.a.j.b bVar = aVar.g;
        if (bVar.j() == 8) {
            bVar.u();
            return null;
        }
        if (bVar.j() != 12 && bVar.j() != 16) {
            throw new JSONException("syntax error: " + b.a.a.j.f.a(bVar.j()));
        }
        int iZ = 0;
        String strV = null;
        String strV2 = null;
        String strV3 = null;
        while (true) {
            String strO = bVar.o(aVar.w());
            if (strO == null) {
                if (bVar.j() == 13) {
                    bVar.F(16);
                    break;
                }
                if (bVar.j() != 16 || !bVar.y(Feature.AllowArbitraryCommas)) {
                }
            }
            bVar.U(4);
            if ("className".equals(strO)) {
                if (bVar.j() == 8) {
                    strV = null;
                } else {
                    if (bVar.j() != 4) {
                        throw new JSONException("syntax error");
                    }
                    strV = bVar.V();
                }
            } else if ("methodName".equals(strO)) {
                if (bVar.j() == 8) {
                    strV2 = null;
                } else {
                    if (bVar.j() != 4) {
                        throw new JSONException("syntax error");
                    }
                    strV2 = bVar.V();
                }
            } else if ("fileName".equals(strO)) {
                if (bVar.j() == 8) {
                    strV3 = null;
                } else {
                    if (bVar.j() != 4) {
                        throw new JSONException("syntax error");
                    }
                    strV3 = bVar.V();
                }
            } else if ("lineNumber".equals(strO)) {
                if (bVar.j() == 8) {
                    iZ = 0;
                } else {
                    if (bVar.j() != 2) {
                        throw new JSONException("syntax error");
                    }
                    iZ = bVar.z();
                }
            } else if ("nativeMethod".equals(strO)) {
                if (bVar.j() != 8 && bVar.j() != 6 && bVar.j() != 7) {
                    throw new JSONException("syntax error");
                }
                bVar.F(16);
            } else if (strO == b.a.a.a.DEFAULT_TYPE_KEY) {
                if (bVar.j() == 4) {
                    String strV4 = bVar.V();
                    if (!strV4.equals("java.lang.StackTraceElement")) {
                        throw new JSONException("syntax error : " + strV4);
                    }
                } else if (bVar.j() != 8) {
                    throw new JSONException("syntax error");
                }
            } else if ("moduleName".equals(strO)) {
                if (bVar.j() != 8) {
                    if (bVar.j() != 4) {
                        throw new JSONException("syntax error");
                    }
                    bVar.V();
                }
            } else if ("moduleVersion".equals(strO)) {
                if (bVar.j() != 8) {
                    if (bVar.j() != 4) {
                        throw new JSONException("syntax error");
                    }
                    bVar.V();
                }
            } else {
                if (!"classLoaderName".equals(strO)) {
                    throw new JSONException("syntax error : " + strO);
                }
                if (bVar.j() != 8) {
                    if (bVar.j() != 4) {
                        throw new JSONException("syntax error");
                    }
                    bVar.V();
                }
            }
            if (bVar.j() == 13) {
                bVar.F(16);
                break;
            }
        }
        return (T) new StackTraceElement(strV, strV2, strV3, iZ);
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 12;
    }
}
