package g2;

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
    public final int f5075c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f5076d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final f2.a f5077e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f5078f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Map f5079g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Collection f5080h;

    public y(f2.a aVar, List list, int i8) {
        super(null, null);
        this.f5077e = aVar;
        this.f5075c = i8;
        this.f5076d = list;
        this.f5078f = null;
        this.f5079g = null;
        this.f5080h = null;
    }

    @Override // g2.l
    public void c(f2.a aVar, Object obj, Type type, Map<String, Object> map) {
    }

    @Override // g2.l
    public void g(Object obj, Object obj2) {
        JSONArray jSONArray;
        Object relatedArray;
        Map map = this.f5079g;
        if (map != null) {
            map.put(this.f5078f, obj2);
            return;
        }
        Collection collection = this.f5080h;
        if (collection != null) {
            collection.add(obj2);
            return;
        }
        this.f5076d.set(this.f5075c, obj2);
        List list = this.f5076d;
        if (!(list instanceof JSONArray) || (relatedArray = (jSONArray = (JSONArray) list).getRelatedArray()) == null || Array.getLength(relatedArray) <= this.f5075c) {
            return;
        }
        if (jSONArray.getComponentType() != null) {
            obj2 = l2.l.h(obj2, jSONArray.getComponentType(), this.f5077e.o());
        }
        Array.set(relatedArray, this.f5075c, obj2);
    }

    public y(Map map, Object obj) {
        super(null, null);
        this.f5077e = null;
        this.f5075c = -1;
        this.f5076d = null;
        this.f5078f = obj;
        this.f5079g = map;
        this.f5080h = null;
    }

    public y(Collection collection) {
        super(null, null);
        this.f5077e = null;
        this.f5075c = -1;
        this.f5076d = null;
        this.f5078f = null;
        this.f5079g = null;
        this.f5080h = collection;
    }
}
