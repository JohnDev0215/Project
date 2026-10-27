package org.apache.shiro.subject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;

/* JADX INFO: loaded from: classes.dex */
public class SimplePrincipalCollection implements MutablePrincipalCollection {
    private static final long serialVersionUID = -6305224034025797558L;
    private transient String cachedToString;
    private Map<String, Set> realmPrincipals;

    public SimplePrincipalCollection() {
    }

    public SimplePrincipalCollection(Object obj, String str) {
        if (obj instanceof Collection) {
            addAll((Collection) obj, str);
        } else {
            add(obj, str);
        }
    }

    public SimplePrincipalCollection(Collection collection, String str) {
        addAll(collection, str);
    }

    public SimplePrincipalCollection(PrincipalCollection principalCollection) {
        addAll(principalCollection);
    }

    protected Collection getPrincipalsLazy(String str) {
        if (this.realmPrincipals == null) {
            this.realmPrincipals = new LinkedHashMap();
        }
        Set set = this.realmPrincipals.get(str);
        if (set != null) {
            return set;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        this.realmPrincipals.put(str, linkedHashSet);
        return linkedHashSet;
    }

    @Override // org.apache.shiro.subject.PrincipalCollection
    public Object getPrimaryPrincipal() {
        if (isEmpty()) {
            return null;
        }
        return iterator().next();
    }

    @Override // org.apache.shiro.subject.MutablePrincipalCollection
    public void add(Object obj, String str) {
        Objects.requireNonNull(str, "realmName argument cannot be null.");
        Objects.requireNonNull(obj, "principal argument cannot be null.");
        this.cachedToString = null;
        getPrincipalsLazy(str).add(obj);
    }

    @Override // org.apache.shiro.subject.MutablePrincipalCollection
    public void addAll(Collection collection, String str) {
        Objects.requireNonNull(str, "realmName argument cannot be null.");
        Objects.requireNonNull(collection, "principals argument cannot be null.");
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("principals argument cannot be an empty collection.");
        }
        this.cachedToString = null;
        getPrincipalsLazy(str).addAll(collection);
    }

    @Override // org.apache.shiro.subject.MutablePrincipalCollection
    public void addAll(PrincipalCollection principalCollection) {
        if (principalCollection.getRealmNames() != null) {
            for (String str : principalCollection.getRealmNames()) {
                Iterator it = principalCollection.fromRealm(str).iterator();
                while (it.hasNext()) {
                    add(it.next(), str);
                }
            }
        }
    }

    @Override // org.apache.shiro.subject.PrincipalCollection
    public <T> T oneByType(Class<T> cls) {
        Map<String, Set> map = this.realmPrincipals;
        if (map != null && !map.isEmpty()) {
            Iterator<Set> it = this.realmPrincipals.values().iterator();
            while (it.hasNext()) {
                for (T t : it.next()) {
                    if (cls.isAssignableFrom(t.getClass())) {
                        return t;
                    }
                }
            }
        }
        return null;
    }

    @Override // org.apache.shiro.subject.PrincipalCollection
    public <T> Collection<T> byType(Class<T> cls) {
        Map<String, Set> map = this.realmPrincipals;
        if (map == null || map.isEmpty()) {
            return Collections.EMPTY_SET;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<Set> it = this.realmPrincipals.values().iterator();
        while (it.hasNext()) {
            for (Object obj : it.next()) {
                if (cls.isAssignableFrom(obj.getClass())) {
                    linkedHashSet.add(obj);
                }
            }
        }
        if (linkedHashSet.isEmpty()) {
            return Collections.EMPTY_SET;
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    @Override // org.apache.shiro.subject.PrincipalCollection
    public List asList() {
        Set setAsSet = asSet();
        if (setAsSet.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        return Collections.unmodifiableList(new ArrayList(setAsSet));
    }

    @Override // org.apache.shiro.subject.PrincipalCollection
    public Set asSet() {
        Map<String, Set> map = this.realmPrincipals;
        if (map == null || map.isEmpty()) {
            return Collections.EMPTY_SET;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<Set> it = this.realmPrincipals.values().iterator();
        while (it.hasNext()) {
            linkedHashSet.addAll(it.next());
        }
        if (linkedHashSet.isEmpty()) {
            return Collections.EMPTY_SET;
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    @Override // org.apache.shiro.subject.PrincipalCollection
    public Collection fromRealm(String str) {
        Map<String, Set> map = this.realmPrincipals;
        if (map == null || map.isEmpty()) {
            return Collections.EMPTY_SET;
        }
        Set set = this.realmPrincipals.get(str);
        if (set == null || set.isEmpty()) {
            set = Collections.EMPTY_SET;
        }
        return Collections.unmodifiableSet(set);
    }

    @Override // org.apache.shiro.subject.PrincipalCollection
    public Set<String> getRealmNames() {
        Map<String, Set> map = this.realmPrincipals;
        if (map == null) {
            return null;
        }
        return map.keySet();
    }

    @Override // org.apache.shiro.subject.PrincipalCollection
    public boolean isEmpty() {
        Map<String, Set> map = this.realmPrincipals;
        return map == null || map.isEmpty();
    }

    @Override // org.apache.shiro.subject.MutablePrincipalCollection
    public void clear() {
        this.cachedToString = null;
        Map<String, Set> map = this.realmPrincipals;
        if (map != null) {
            map.clear();
            this.realmPrincipals = null;
        }
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return asSet().iterator();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SimplePrincipalCollection)) {
            return false;
        }
        Map<String, Set> map = this.realmPrincipals;
        Map<String, Set> map2 = ((SimplePrincipalCollection) obj).realmPrincipals;
        return map != null ? map.equals(map2) : map2 == null;
    }

    public int hashCode() {
        Map<String, Set> map = this.realmPrincipals;
        if (map != null && !map.isEmpty()) {
            return this.realmPrincipals.hashCode();
        }
        return super.hashCode();
    }

    public String toString() {
        if (this.cachedToString == null) {
            Set setAsSet = asSet();
            if (!CollectionUtils.isEmpty(setAsSet)) {
                this.cachedToString = StringUtils.toString(setAsSet.toArray());
            } else {
                this.cachedToString = "empty";
            }
        }
        return this.cachedToString;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        boolean z = !CollectionUtils.isEmpty(this.realmPrincipals);
        objectOutputStream.writeBoolean(z);
        if (z) {
            objectOutputStream.writeObject(this.realmPrincipals);
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        if (objectInputStream.readBoolean()) {
            this.realmPrincipals = (Map) objectInputStream.readObject();
        }
    }
}
