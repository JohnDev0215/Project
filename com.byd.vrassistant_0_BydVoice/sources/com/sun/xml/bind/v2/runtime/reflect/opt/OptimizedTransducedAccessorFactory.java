package com.sun.xml.bind.v2.runtime.reflect.opt;

import com.android.multidex.ClassPathElement;
import com.sun.xml.bind.Util;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
public abstract class OptimizedTransducedAccessorFactory {
    private static final String fieldTemplateName;
    private static final Logger logger = Util.getClassLogger();
    private static final String methodTemplateName;
    private static final Map<Class, String> suffixMap;

    private OptimizedTransducedAccessorFactory() {
    }

    static {
        fieldTemplateName = TransducedAccessor_field_Byte.class.getName().substring(0, r0.length() - 4).replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR);
        methodTemplateName = TransducedAccessor_method_Byte.class.getName().substring(0, r0.length() - 4).replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR);
        HashMap map = new HashMap();
        suffixMap = map;
        map.put(Byte.TYPE, "Byte");
        map.put(Short.TYPE, "Short");
        map.put(Integer.TYPE, "Integer");
        map.put(Long.TYPE, "Long");
        map.put(Boolean.TYPE, "Boolean");
        map.put(Float.TYPE, "Float");
        map.put(Double.TYPE, "Double");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00ac  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor get(com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo r16) {
        /*
            Method dump skipped, instruction units count: 402
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.xml.bind.v2.runtime.reflect.opt.OptimizedTransducedAccessorFactory.get(com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo):com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor");
    }
}
