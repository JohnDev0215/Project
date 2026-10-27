package b.a.a.j.j;

import com.alibaba.fastjson.JSONArray;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: ResolveFieldDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public final class y extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f302c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f303d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b.a.a.j.a f304e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f305f;
    public final Map g;
    public final Collection h;

    public y(b.a.a.j.a aVar, List list, int i) {
        super(null, null);
        this.f304e = aVar;
        this.f302c = i;
        this.f303d = list;
        this.f305f = null;
        this.g = null;
        this.h = null;
    }

    @Override // b.a.a.j.j.l
    public void c(b.a.a.j.a aVar, Object obj, Type type, Map<String, Object> map) {
    }

    @Override // b.a.a.j.j.l
    public void g(Object obj, Object obj2) {
        JSONArray jSONArray;
        Object relatedArray;
        Map map = this.g;
        if (map != null) {
            map.put(this.f305f, obj2);
            return;
        }
        Collection collection = this.h;
        if (collection != null) {
            collection.add(obj2);
            return;
        }
        this.f303d.set(this.f302c, obj2);
        List list = this.f303d;
        if (!(list instanceof JSONArray) || (relatedArray = (jSONArray = (JSONArray) list).getRelatedArray()) == null || Array.getLength(relatedArray) <= this.f302c) {
            return;
        }
        if (jSONArray.getComponentType() != null) {
            obj2 = b.a.a.n.l.h(obj2, jSONArray.getComponentType(), this.f304e.i());
        }
        Array.set(relatedArray, this.f302c, obj2);
    }

    public y(Map map, Object obj) {
        super(null, null);
        this.f304e = null;
        this.f302c = -1;
        this.f303d = null;
        this.f305f = obj;
        this.g = map;
        this.h = null;
    }

    public y(Collection collection) {
        super(null, null);
        this.f304e = null;
        this.f302c = -1;
        this.f303d = null;
        this.f305f = null;
        this.g = null;
        this.h = collection;
    }
}
