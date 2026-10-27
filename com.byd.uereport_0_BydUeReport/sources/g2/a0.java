package g2;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.Feature;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: StackTraceElementDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class a0 implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a0 f5023a = new a0();

    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        f2.b bVar = aVar.f4861j;
        if (bVar.q() == 8) {
            bVar.E();
            return null;
        }
        if (bVar.q() != 12 && bVar.q() != 16) {
            throw new JSONException("syntax error: " + f2.f.a(bVar.q()));
        }
        int iN = 0;
        String strN0 = null;
        String strN02 = null;
        String strN03 = null;
        while (true) {
            String strW = bVar.w(aVar.I());
            if (strW == null) {
                if (bVar.q() == 13) {
                    bVar.V(16);
                    break;
                }
                if (bVar.q() != 16 || !bVar.M(Feature.AllowArbitraryCommas)) {
                }
            }
            bVar.m0(4);
            if ("className".equals(strW)) {
                if (bVar.q() == 8) {
                    strN0 = null;
                } else {
                    if (bVar.q() != 4) {
                        throw new JSONException("syntax error");
                    }
                    strN0 = bVar.n0();
                }
            } else if ("methodName".equals(strW)) {
                if (bVar.q() == 8) {
                    strN02 = null;
                } else {
                    if (bVar.q() != 4) {
                        throw new JSONException("syntax error");
                    }
                    strN02 = bVar.n0();
                }
            } else if ("fileName".equals(strW)) {
                if (bVar.q() == 8) {
                    strN03 = null;
                } else {
                    if (bVar.q() != 4) {
                        throw new JSONException("syntax error");
                    }
                    strN03 = bVar.n0();
                }
            } else if ("lineNumber".equals(strW)) {
                if (bVar.q() == 8) {
                    iN = 0;
                } else {
                    if (bVar.q() != 2) {
                        throw new JSONException("syntax error");
                    }
                    iN = bVar.N();
                }
            } else if ("nativeMethod".equals(strW)) {
                if (bVar.q() != 8 && bVar.q() != 6 && bVar.q() != 7) {
                    throw new JSONException("syntax error");
                }
                bVar.V(16);
            } else if (strW == c2.a.DEFAULT_TYPE_KEY) {
                if (bVar.q() == 4) {
                    String strN04 = bVar.n0();
                    if (!strN04.equals("java.lang.StackTraceElement")) {
                        throw new JSONException("syntax error : " + strN04);
                    }
                } else if (bVar.q() != 8) {
                    throw new JSONException("syntax error");
                }
            } else if ("moduleName".equals(strW)) {
                if (bVar.q() != 8) {
                    if (bVar.q() != 4) {
                        throw new JSONException("syntax error");
                    }
                    bVar.n0();
                }
            } else if ("moduleVersion".equals(strW)) {
                if (bVar.q() != 8) {
                    if (bVar.q() != 4) {
                        throw new JSONException("syntax error");
                    }
                    bVar.n0();
                }
            } else {
                if (!"classLoaderName".equals(strW)) {
                    throw new JSONException("syntax error : " + strW);
                }
                if (bVar.q() != 8) {
                    if (bVar.q() != 4) {
                        throw new JSONException("syntax error");
                    }
                    bVar.n0();
                }
            }
            if (bVar.q() == 13) {
                bVar.V(16);
                break;
            }
        }
        return (T) new StackTraceElement(strN0, strN02, strN03, iN);
    }

    @Override // g2.t
    public int e() {
        return 12;
    }
}
