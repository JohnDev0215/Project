package com.squareup.moshi;

import com.squareup.moshi.f;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
final class n<K, V> extends f<Map<K, V>> {
    public static final f.e c = new a();
    private final f<K> a;
    private final f<V> b;

    public class a implements f.e {
        @Override // com.squareup.moshi.f.e
        public f<?> a(Type type, Set<? extends Annotation> set, o oVar) {
            Class<?> clsG;
            if (!set.isEmpty() || (clsG = zo2.g(type)) != Map.class) {
                return null;
            }
            Type[] typeArrI = zo2.i(type, clsG);
            return new n(oVar, typeArrI[0], typeArrI[1]).nullSafe();
        }
    }

    public n(o oVar, Type type, Type type2) {
        this.a = oVar.d(type);
        this.b = oVar.d(type2);
    }

    @Override // com.squareup.moshi.f
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Map<K, V> fromJson(JsonReader jsonReader) {
        LinkedHashTreeMap linkedHashTreeMap = new LinkedHashTreeMap();
        jsonReader.c();
        while (jsonReader.hasNext()) {
            jsonReader.J0();
            K kFromJson = this.a.fromJson(jsonReader);
            V vFromJson = this.b.fromJson(jsonReader);
            V vPut = linkedHashTreeMap.put(kFromJson, vFromJson);
            if (vPut != null) {
                throw new JsonDataException("Map key '" + kFromJson + "' has multiple values at path " + jsonReader.J() + ": " + vPut + " and " + vFromJson);
            }
        }
        jsonReader.q();
        return linkedHashTreeMap;
    }

    @Override // com.squareup.moshi.f
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public void toJson(m mVar, Map<K, V> map) {
        mVar.i();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getKey() == null) {
                throw new JsonDataException("Map key is null at " + mVar.E0());
            }
            mVar.N0();
            this.a.toJson(mVar, entry.getKey());
            this.b.toJson(mVar, entry.getValue());
        }
        mVar.X();
    }

    public String toString() {
        return "JsonAdapter(" + this.a + "=" + this.b + ")";
    }
}
