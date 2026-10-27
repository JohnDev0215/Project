package org.apache.shiro.subject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.shiro.util.CollectionUtils;

/* JADX INFO: loaded from: classes.dex */
public class SimplePrincipalMap implements PrincipalMap {
    private Map<String, Object> combinedPrincipals;
    private Map<String, Map<String, Object>> realmPrincipals;

    public SimplePrincipalMap() {
        this(null);
    }

    public SimplePrincipalMap(Map<String, Map<String, Object>> map) {
        if (CollectionUtils.isEmpty(map)) {
            return;
        }
        this.realmPrincipals = map;
        for (Map<String, Object> map2 : map.values()) {
            if (!CollectionUtils.isEmpty(map2)) {
                ensureCombinedPrincipals().putAll(map2);
            }
        }
    }

    @Override // java.util.Map
    public int size() {
        return CollectionUtils.size(this.combinedPrincipals);
    }

    protected Map<String, Object> ensureCombinedPrincipals() {
        if (this.combinedPrincipals == null) {
            this.combinedPrincipals = new HashMap();
        }
        return this.combinedPrincipals;
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        Map<String, Object> map = this.combinedPrincipals;
        return map != null && map.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        Map<String, Object> map = this.combinedPrincipals;
        return map != null && map.containsKey(obj);
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        Map<String, Object> map = this.combinedPrincipals;
        return Boolean.valueOf(map != null && map.containsKey(obj));
    }

    @Override // java.util.Map
    public Object put(String str, Object obj) {
        return ensureCombinedPrincipals().put(str, obj);
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        Map<String, Object> map = this.combinedPrincipals;
        if (map != null) {
            return map.remove(obj);
        }
        return null;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends Object> map) {
        if (CollectionUtils.isEmpty(map)) {
            return;
        }
        ensureCombinedPrincipals().putAll(map);
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        if (CollectionUtils.isEmpty(this.combinedPrincipals)) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(this.combinedPrincipals.keySet());
    }

    @Override // java.util.Map
    public Collection<Object> values() {
        if (CollectionUtils.isEmpty(this.combinedPrincipals)) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableCollection(this.combinedPrincipals.values());
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, Object>> entrySet() {
        if (CollectionUtils.isEmpty(this.combinedPrincipals)) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(this.combinedPrincipals.entrySet());
    }

    @Override // java.util.Map
    public void clear() {
        this.realmPrincipals = null;
        this.combinedPrincipals = null;
    }

    @Override // org.apache.shiro.subject.PrincipalCollection
    public Object getPrimaryPrincipal() {
        if (CollectionUtils.isEmpty(this.combinedPrincipals)) {
            return null;
        }
        return this.combinedPrincipals.values().iterator().next();
    }

    @Override // org.apache.shiro.subject.PrincipalCollection
    public <T> T oneByType(Class<T> cls) {
        if (CollectionUtils.isEmpty(this.combinedPrincipals)) {
            return null;
        }
        for (Object obj : this.combinedPrincipals.values()) {
            if (cls.isInstance(obj)) {
                return cls.cast(obj);
            }
        }
        return null;
    }

    @Override // org.apache.shiro.subject.PrincipalCollection
    public <T> Collection<T> byType(Class<T> cls) {
        if (CollectionUtils.isEmpty(this.combinedPrincipals)) {
            return Collections.emptySet();
        }
        ArrayList arrayList = null;
        for (Object obj : this.combinedPrincipals.values()) {
            if (cls.isInstance(obj)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(cls.cast(obj));
            }
        }
        return arrayList != null ? arrayList : Collections.emptyList();
    }

    @Override // org.apache.shiro.subject.PrincipalCollection
    public List asList() {
        if (CollectionUtils.isEmpty(this.combinedPrincipals)) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(this.combinedPrincipals.size());
        arrayList.addAll(this.combinedPrincipals.values());
        return arrayList;
    }

    @Override // org.apache.shiro.subject.PrincipalCollection
    public Set asSet() {
        if (CollectionUtils.isEmpty(this.combinedPrincipals)) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet(this.combinedPrincipals.size());
        hashSet.addAll(this.combinedPrincipals.values());
        return hashSet;
    }

    @Override // org.apache.shiro.subject.PrincipalCollection
    public Collection fromRealm(String str) {
        if (CollectionUtils.isEmpty(this.realmPrincipals)) {
            return Collections.emptySet();
        }
        Map<String, Object> map = this.realmPrincipals.get(str);
        if (CollectionUtils.isEmpty(map)) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableCollection(map.values());
    }

    @Override // org.apache.shiro.subject.PrincipalCollection
    public Set<String> getRealmNames() {
        if (CollectionUtils.isEmpty(this.realmPrincipals)) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(this.realmPrincipals.keySet());
    }

    @Override // org.apache.shiro.subject.PrincipalCollection
    public boolean isEmpty() {
        return CollectionUtils.isEmpty(this.combinedPrincipals);
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return asList().iterator();
    }

    @Override // org.apache.shiro.subject.PrincipalMap
    public Map<String, Object> getRealmPrincipals(String str) {
        Map<String, Object> map;
        Map<String, Map<String, Object>> map2 = this.realmPrincipals;
        if (map2 == null || (map = map2.get(str)) == null) {
            return null;
        }
        return Collections.unmodifiableMap(map);
    }

    @Override // org.apache.shiro.subject.PrincipalMap
    public Map<String, Object> setRealmPrincipals(String str, Map<String, Object> map) {
        Objects.requireNonNull(str, "realmName argument cannot be null.");
        Map<String, Map<String, Object>> map2 = this.realmPrincipals;
        if (map2 == null) {
            if (CollectionUtils.isEmpty(map)) {
                return null;
            }
            HashMap map3 = new HashMap();
            this.realmPrincipals = map3;
            return (Map) map3.put(str, new HashMap(map));
        }
        Map<String, Object> mapRemove = map2.remove(str);
        if (!CollectionUtils.isEmpty(map)) {
            this.realmPrincipals.put(str, new HashMap(map));
        }
        return mapRemove;
    }

    @Override // org.apache.shiro.subject.PrincipalMap
    public Object setRealmPrincipal(String str, String str2, Object obj) {
        Objects.requireNonNull(str, "realmName argument cannot be null.");
        Objects.requireNonNull(str2, "principalName argument cannot be null.");
        if (obj == null) {
            return removeRealmPrincipal(str, str2);
        }
        if (this.realmPrincipals == null) {
            this.realmPrincipals = new HashMap();
        }
        Map<String, Object> map = this.realmPrincipals.get(str);
        if (map == null) {
            map = new HashMap<>();
            this.realmPrincipals.put(str, map);
        }
        return map.put(str2, obj);
    }

    @Override // org.apache.shiro.subject.PrincipalMap
    public Object getRealmPrincipal(String str, String str2) {
        Map<String, Object> map;
        Objects.requireNonNull(str, "realmName argument cannot be null.");
        Objects.requireNonNull(str2, "principalName argument cannot be null.");
        Map<String, Map<String, Object>> map2 = this.realmPrincipals;
        if (map2 == null || (map = map2.get(str)) == null) {
            return null;
        }
        return map.get(str2);
    }

    @Override // org.apache.shiro.subject.PrincipalMap
    public Object removeRealmPrincipal(String str, String str2) {
        Map<String, Object> map;
        Objects.requireNonNull(str, "realmName argument cannot be null.");
        Objects.requireNonNull(str2, "principalName argument cannot be null.");
        Map<String, Map<String, Object>> map2 = this.realmPrincipals;
        if (map2 == null || (map = map2.get(str)) == null) {
            return null;
        }
        return map.remove(str2);
    }
}
