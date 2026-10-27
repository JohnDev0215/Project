package p;

import com.google.gson.JsonSyntaxException;
import com.spotify.eventsender.droppedevents.EventState;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ek0 implements fk0 {
    public final com.google.gson.a a;

    public ek0(com.google.gson.a aVar) {
        this.a = aVar;
    }

    @Override // p.fk0
    public final LinkedHashMap a(String str) {
        v03.j(str, "from");
        if (str.length() == 0) {
            return new LinkedHashMap();
        }
        try {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Type type = new dk0().b;
            v03.i(type, "object : TypeToken<Mutab…roppedCounts>>>() {}.type");
            Object objC = this.a.c(type, str);
            v03.i(objC, "gson.fromJson(from, fromType)");
            Iterator it = ((Map) objC).entrySet().iterator();
            while (it.hasNext()) {
                for (Map.Entry entry : ((Map) ((Map.Entry) it.next()).getValue()).entrySet()) {
                    String str2 = (String) entry.getKey();
                    qb0 qb0Var = (qb0) entry.getValue();
                    Object gk0Var = linkedHashMap.get(str2);
                    if (gk0Var == null) {
                        gk0Var = new gk0();
                        linkedHashMap.put(str2, gk0Var);
                    }
                    LinkedHashMap linkedHashMap2 = EventState.c;
                    v03.j(qb0Var, "count");
                    qb0[] qb0VarArr = ((gk0) gk0Var).a;
                    qb0 qb0Var2 = qb0VarArr[2];
                    qb0Var2.c(qb0Var);
                    qb0VarArr[2] = qb0Var2;
                }
            }
            return linkedHashMap;
        } catch (JsonSyntaxException unused) {
            return new LinkedHashMap();
        }
    }
}
