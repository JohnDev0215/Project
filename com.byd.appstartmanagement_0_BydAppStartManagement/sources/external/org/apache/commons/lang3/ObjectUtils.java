package external.org.apache.commons.lang3;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class ObjectUtils {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Null f798a = new Null();

    public static class Null implements Serializable {
        private static final long serialVersionUID = 7092611880189329093L;

        private Object readResolve() {
            return ObjectUtils.f798a;
        }
    }

    public static boolean a(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }
}
