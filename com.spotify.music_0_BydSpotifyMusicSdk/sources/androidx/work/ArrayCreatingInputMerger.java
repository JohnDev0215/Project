package androidx.work;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import p.s41;
import p.v03;
import p.v50;
import p.w50;

/* JADX INFO: loaded from: classes.dex */
public final class ArrayCreatingInputMerger extends s41 {
    @Override // p.s41
    public final w50 a(ArrayList arrayList) throws Throwable {
        Object objNewInstance;
        v50 v50Var = new v50();
        HashMap map = new HashMap();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Map mapUnmodifiableMap = Collections.unmodifiableMap(((w50) it.next()).a);
            v03.i(mapUnmodifiableMap, "input.keyValueMap");
            for (Map.Entry entry : mapUnmodifiableMap.entrySet()) {
                String str = (String) entry.getKey();
                Object value = entry.getValue();
                Class cls = value != null ? value.getClass() : String.class;
                Object obj = map.get(str);
                v03.i(str, "key");
                if (obj != null) {
                    Class<?> cls2 = obj.getClass();
                    if (v03.b(cls2, cls)) {
                        v03.i(value, "value");
                        int length = Array.getLength(obj);
                        int length2 = Array.getLength(value);
                        Class<?> componentType = obj.getClass().getComponentType();
                        v03.h(componentType);
                        Object objNewInstance2 = Array.newInstance(componentType, length + length2);
                        System.arraycopy(obj, 0, objNewInstance2, 0, length);
                        System.arraycopy(value, 0, objNewInstance2, length, length2);
                        v03.i(objNewInstance2, "newArray");
                        value = objNewInstance2;
                    } else {
                        if (!v03.b(cls2.getComponentType(), cls)) {
                            throw new IllegalArgumentException();
                        }
                        int length3 = Array.getLength(obj);
                        objNewInstance = Array.newInstance(cls, length3 + 1);
                        System.arraycopy(obj, 0, objNewInstance, 0, length3);
                        Array.set(objNewInstance, length3, value);
                        v03.i(objNewInstance, "newArray");
                        value = objNewInstance;
                    }
                } else if (!cls.isArray()) {
                    objNewInstance = Array.newInstance(cls, 1);
                    Array.set(objNewInstance, 0, value);
                    v03.i(objNewInstance, "newArray");
                    value = objNewInstance;
                }
                v03.i(value, "if (existingValue == nul…      }\n                }");
                map.put(str, value);
            }
        }
        v50Var.a(map);
        w50 w50Var = new w50(v50Var.a);
        w50.b(w50Var);
        return w50Var;
    }
}
