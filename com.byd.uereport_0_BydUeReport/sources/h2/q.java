package h2;

import com.alibaba.fastjson.JSONException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: CharArrayCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class q implements g2.t {
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T c(f2.a aVar) {
        boolean z7;
        f2.b bVar = aVar.f4861j;
        if (bVar.q() == 4) {
            String strN0 = bVar.n0();
            bVar.V(16);
            return (T) strN0.toCharArray();
        }
        if (bVar.q() == 2) {
            Number numberJ = bVar.j();
            bVar.V(16);
            return (T) numberJ.toString().toCharArray();
        }
        Object objN = aVar.N();
        if (objN instanceof String) {
            return (T) ((String) objN).toCharArray();
        }
        if (!(objN instanceof Collection)) {
            if (objN == null) {
                return null;
            }
            return (T) c2.a.toJSONString(objN).toCharArray();
        }
        Collection collection = (Collection) objN;
        Iterator it = collection.iterator();
        while (true) {
            z7 = true;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if ((next instanceof String) && ((String) next).length() != 1) {
                z7 = false;
                break;
            }
        }
        if (!z7) {
            throw new JSONException("can not cast to char[]");
        }
        char[] cArr = new char[collection.size()];
        Iterator it2 = collection.iterator();
        int i8 = 0;
        while (it2.hasNext()) {
            cArr[i8] = ((String) it2.next()).charAt(0);
            i8++;
        }
        return cArr;
    }

    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        return (T) c(aVar);
    }

    @Override // g2.t
    public int e() {
        return 4;
    }
}
