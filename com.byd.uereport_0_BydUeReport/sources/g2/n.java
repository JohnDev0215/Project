package g2;

import com.alibaba.fastjson.JSONException;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: JSONPDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class n implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final n f5038a = new n();

    /* JADX WARN: Type inference failed for: r1v1, types: [T, c2.c] */
    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        int iQ;
        f2.c cVar = (f2.c) aVar.C();
        String strG0 = cVar.g0(aVar.I());
        cVar.E();
        int iQ2 = cVar.q();
        if (iQ2 == 25) {
            String str = strG0 + ".";
            strG0 = str + cVar.g0(aVar.I());
            cVar.E();
            iQ2 = cVar.q();
        }
        ?? r12 = (T) new c2.c(strG0);
        if (iQ2 != 10) {
            throw new JSONException("illegal jsonp : " + cVar.g());
        }
        cVar.E();
        while (true) {
            r12.b(aVar.N());
            iQ = cVar.q();
            if (iQ != 16) {
                break;
            }
            cVar.E();
        }
        if (iQ == 11) {
            cVar.E();
            if (cVar.q() == 24) {
                cVar.E();
            }
            return r12;
        }
        throw new JSONException("illegal jsonp : " + cVar.g());
    }

    @Override // g2.t
    public int e() {
        return 0;
    }
}
