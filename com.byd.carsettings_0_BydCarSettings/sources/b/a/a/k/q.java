package b.a.a.k;

import com.alibaba.fastjson.JSONException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: CharArrayCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class q implements b.a.a.j.j.t {
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T c(b.a.a.j.a aVar) {
        boolean z;
        b.a.a.j.b bVar = aVar.g;
        if (bVar.j() == 4) {
            String strV = bVar.V();
            bVar.F(16);
            return (T) strV.toCharArray();
        }
        if (bVar.j() == 2) {
            Number numberF = bVar.f();
            bVar.F(16);
            return (T) numberF.toString().toCharArray();
        }
        Object objZ = aVar.z();
        if (objZ instanceof String) {
            return (T) ((String) objZ).toCharArray();
        }
        if (!(objZ instanceof Collection)) {
            if (objZ == null) {
                return null;
            }
            return (T) b.a.a.a.toJSONString(objZ).toCharArray();
        }
        Collection collection = (Collection) objZ;
        Iterator it = collection.iterator();
        while (true) {
            z = true;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if ((next instanceof String) && ((String) next).length() != 1) {
                z = false;
                break;
            }
        }
        if (!z) {
            throw new JSONException("can not cast to char[]");
        }
        char[] cArr = new char[collection.size()];
        Iterator it2 = collection.iterator();
        int i = 0;
        while (it2.hasNext()) {
            cArr[i] = ((String) it2.next()).charAt(0);
            i++;
        }
        return cArr;
    }

    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        return (T) c(aVar);
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 4;
    }
}
