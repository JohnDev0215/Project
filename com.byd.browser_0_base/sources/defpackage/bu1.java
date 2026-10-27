package defpackage;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: compiled from: PageAnnotationUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bu1 {
    public static final a a = new a();

    /* JADX INFO: compiled from: PageAnnotationUtils.java */
    public class a extends LinkedHashMap<Class<? extends au1>, String> {
        public a() {
            put(dk.class, "BUYABLE_PRODUCT");
            put(e62.class, "PRODUCT_PRICE_UPDATE");
        }
    }

    public static <T extends au1> T a(List<au1> list, Class<T> cls) {
        String str;
        if (list == null || list.size() == 0 || (str = a.get(cls)) == null) {
            return null;
        }
        Iterator<au1> it = list.iterator();
        while (it.hasNext()) {
            T t = (T) it.next();
            t.getClass();
            if (str.equals(null)) {
                return t;
            }
        }
        return null;
    }
}
