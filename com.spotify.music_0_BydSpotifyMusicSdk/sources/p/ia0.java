package p;

import com.google.common.collect.ImmutableMap;
import com.spotify.remoteconfig.commons.AssignedPropertyValue$ValueType;
import dagger.android.DispatchingAndroidInjector$InvalidInjectorBindingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ia0 implements a7 {
    public final Map a;

    public ia0(Map map) {
        this.a = map;
    }

    @Override // p.a7
    public void a(Object obj) {
        String name = obj.getClass().getName();
        Map map = this.a;
        xy1 xy1Var = (xy1) map.get(name);
        if (xy1Var != null) {
            z6 z6Var = (z6) xy1Var.get();
            try {
                ((i50) z6Var).a(obj).a(obj);
                return;
            } catch (ClassCastException e) {
                throw new DispatchingAndroidInjector$InvalidInjectorBindingException(String.format("%s does not implement AndroidInjector.Factory<%s>", z6Var.getClass().getCanonicalName(), obj.getClass().getCanonicalName()), e);
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Class<?> superclass = obj.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
            if (map.containsKey(superclass.getCanonicalName())) {
                arrayList.add(superclass.getCanonicalName());
            }
        }
        throw new IllegalArgumentException(arrayList.isEmpty() ? String.format("No injector factory bound for Class<%s>", obj.getClass().getCanonicalName()) : String.format("No injector factory bound for Class<%1$s>. Injector factories were bound for supertypes of %1$s: %2$s. Did you mean to bind an injector factory for the subtype?", obj.getClass().getCanonicalName(), arrayList));
    }

    public boolean b(String str, String str2, boolean z) {
        Boolean bool;
        pc pcVarC = c(str, str2, AssignedPropertyValue$ValueType.c);
        return (pcVarC == null || (bool = pcVarC.c) == null) ? z : bool.booleanValue();
    }

    public pc c(String str, String str2, AssignedPropertyValue$ValueType assignedPropertyValue$ValueType) {
        m02 m02Var = m02.c;
        pc pcVar = (pc) this.a.get(zb0.w(str, str2));
        if (pcVar != null) {
            if (assignedPropertyValue$ValueType == (pcVar.c != null ? AssignedPropertyValue$ValueType.c : pcVar.d != null ? AssignedPropertyValue$ValueType.e : pcVar.e != null ? AssignedPropertyValue$ValueType.k : AssignedPropertyValue$ValueType.l)) {
                return pcVar;
            }
        }
        return null;
    }

    public ia0(Map map, Map map2) {
        ImmutableMap immutableMap = (ImmutableMap) map;
        if (!immutableMap.isEmpty()) {
            int size = map2.size() + immutableMap.size();
            LinkedHashMap linkedHashMap = new LinkedHashMap(size < 3 ? size + 1 : size < 1073741824 ? (int) ((size / 0.75f) + 1.0f) : Integer.MAX_VALUE);
            linkedHashMap.putAll(map2);
            for (Map.Entry entry : immutableMap.entrySet()) {
                linkedHashMap.put(((Class) entry.getKey()).getName(), entry.getValue());
            }
            map2 = Collections.unmodifiableMap(linkedHashMap);
        }
        this.a = map2;
    }
}
