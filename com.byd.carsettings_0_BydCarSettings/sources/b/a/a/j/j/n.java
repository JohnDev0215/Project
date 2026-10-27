package b.a.a.j.j;

import com.alibaba.fastjson.JSONException;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: JSONPDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class n implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final n f284a = new n();

    /* JADX WARN: Type inference failed for: r1v1, types: [T, b.a.a.c] */
    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        int iJ;
        b.a.a.j.c cVar = (b.a.a.j.c) aVar.t();
        String strQ = cVar.Q(aVar.w());
        cVar.u();
        int iJ2 = cVar.j();
        if (iJ2 == 25) {
            String str = strQ + ".";
            strQ = str + cVar.Q(aVar.w());
            cVar.u();
            iJ2 = cVar.j();
        }
        ?? r1 = (T) new b.a.a.c(strQ);
        if (iJ2 != 10) {
            throw new JSONException("illegal jsonp : " + cVar.c());
        }
        cVar.u();
        while (true) {
            r1.b(aVar.z());
            iJ = cVar.j();
            if (iJ != 16) {
                break;
            }
            cVar.u();
        }
        if (iJ == 11) {
            cVar.u();
            if (cVar.j() == 24) {
                cVar.u();
            }
            return r1;
        }
        throw new JSONException("illegal jsonp : " + cVar.c());
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 0;
    }
}
