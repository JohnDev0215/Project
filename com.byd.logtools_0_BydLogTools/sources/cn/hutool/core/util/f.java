package cn.hutool.core.util;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Supplier;

/* JADX INFO: compiled from: ObjectUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class f {
    public static <T> T a(T t, Supplier<? extends T> supplier) {
        return d(t) ? supplier.get() : t;
    }

    public static boolean b(Object obj, Object obj2) {
        return ((obj instanceof BigDecimal) && (obj2 instanceof BigDecimal)) ? e.b((BigDecimal) obj, (BigDecimal) obj2) : Objects.equals(obj, obj2);
    }

    public static boolean c(Object obj, Object obj2) {
        return b(obj, obj2);
    }

    public static boolean d(Object obj) {
        return obj == null || obj.equals(null);
    }
}
