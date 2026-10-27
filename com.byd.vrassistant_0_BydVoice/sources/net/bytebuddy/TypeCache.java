package net.bytebuddy;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import net.bytebuddy.utility.CompoundList;

/* JADX INFO: loaded from: classes3.dex */
public class TypeCache<T> extends ReferenceQueue<ClassLoader> {
    private static final Class<?> NOT_FOUND = null;
    protected final ConcurrentMap<StorageKey, ConcurrentMap<T, Reference<Class<?>>>> cache = new ConcurrentHashMap();
    protected final Sort sort;

    public enum Sort {
        WEAK { // from class: net.bytebuddy.TypeCache.Sort.1
            @Override // net.bytebuddy.TypeCache.Sort
            protected Reference<Class<?>> wrap(Class<?> cls) {
                return new WeakReference(cls);
            }
        },
        SOFT { // from class: net.bytebuddy.TypeCache.Sort.2
            @Override // net.bytebuddy.TypeCache.Sort
            protected Reference<Class<?>> wrap(Class<?> cls) {
                return new SoftReference(cls);
            }
        };

        protected abstract Reference<Class<?>> wrap(Class<?> cls);
    }

    public TypeCache(Sort sort) {
        this.sort = sort;
    }

    public Class<?> find(ClassLoader classLoader, T t) {
        ConcurrentMap<T, Reference<Class<?>>> concurrentMap = this.cache.get(new LookupKey(classLoader));
        if (concurrentMap == null) {
            return NOT_FOUND;
        }
        Reference<Class<?>> reference = concurrentMap.get(t);
        if (reference == null) {
            return NOT_FOUND;
        }
        return reference.get();
    }

    public Class<?> insert(ClassLoader classLoader, T t, Class<?> cls) {
        ConcurrentMap<T, Reference<Class<?>>> concurrentMapPutIfAbsent;
        ConcurrentMap<T, Reference<Class<?>>> concurrentHashMap = this.cache.get(new LookupKey(classLoader));
        if (concurrentHashMap == null && (concurrentMapPutIfAbsent = this.cache.putIfAbsent(new StorageKey(classLoader, this), (concurrentHashMap = new ConcurrentHashMap<>()))) != null) {
            concurrentHashMap = concurrentMapPutIfAbsent;
        }
        Reference<Class<?>> referenceWrap = this.sort.wrap(cls);
        Reference<Class<?>> referencePutIfAbsent = concurrentHashMap.putIfAbsent(t, referenceWrap);
        while (referencePutIfAbsent != null) {
            Class<?> cls2 = referencePutIfAbsent.get();
            if (cls2 != null) {
                return cls2;
            }
            if (concurrentHashMap.remove(t, referencePutIfAbsent)) {
                referencePutIfAbsent = concurrentHashMap.putIfAbsent(t, referenceWrap);
            } else {
                referencePutIfAbsent = concurrentHashMap.get(t);
                if (referencePutIfAbsent == null) {
                    referencePutIfAbsent = concurrentHashMap.putIfAbsent(t, referenceWrap);
                }
            }
        }
        return cls;
    }

    public Class<?> findOrInsert(ClassLoader classLoader, T t, Callable<Class<?>> callable) {
        Class<?> clsFind = find(classLoader, t);
        if (clsFind != null) {
            return clsFind;
        }
        try {
            return insert(classLoader, t, callable.call());
        } catch (Throwable th) {
            throw new IllegalArgumentException("Could not create type", th);
        }
    }

    public Class<?> findOrInsert(ClassLoader classLoader, T t, Callable<Class<?>> callable, Object obj) {
        Class<?> clsFindOrInsert;
        Class<?> clsFind = find(classLoader, t);
        if (clsFind != null) {
            return clsFind;
        }
        synchronized (obj) {
            clsFindOrInsert = findOrInsert(classLoader, t, callable);
        }
        return clsFindOrInsert;
    }

    public void expungeStaleEntries() {
        while (true) {
            Reference<? extends T> referencePoll = poll();
            if (referencePoll == null) {
                return;
            } else {
                this.cache.remove(referencePoll);
            }
        }
    }

    public void clear() {
        this.cache.clear();
    }

    protected static class LookupKey {
        private final ClassLoader classLoader;
        private final int hashCode;

        protected LookupKey(ClassLoader classLoader) {
            this.classLoader = classLoader;
            this.hashCode = System.identityHashCode(classLoader);
        }

        public int hashCode() {
            return this.hashCode;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof LookupKey) {
                return this.classLoader == ((LookupKey) obj).classLoader;
            }
            if (!(obj instanceof StorageKey)) {
                return false;
            }
            StorageKey storageKey = (StorageKey) obj;
            return this.hashCode == storageKey.hashCode && this.classLoader == storageKey.get();
        }
    }

    protected static class StorageKey extends WeakReference<ClassLoader> {
        private final int hashCode;

        protected StorageKey(ClassLoader classLoader, ReferenceQueue<? super ClassLoader> referenceQueue) {
            super(classLoader, referenceQueue);
            this.hashCode = System.identityHashCode(classLoader);
        }

        public int hashCode() {
            return this.hashCode;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof LookupKey) {
                LookupKey lookupKey = (LookupKey) obj;
                return this.hashCode == lookupKey.hashCode && get() == lookupKey.classLoader;
            }
            if (!(obj instanceof StorageKey)) {
                return false;
            }
            StorageKey storageKey = (StorageKey) obj;
            return this.hashCode == storageKey.hashCode && get() == storageKey.get();
        }
    }

    public static class WithInlineExpunction<S> extends TypeCache<S> {
        public WithInlineExpunction(Sort sort) {
            super(sort);
        }

        @Override // net.bytebuddy.TypeCache
        public Class<?> find(ClassLoader classLoader, S s) {
            try {
                return super.find(classLoader, s);
            } finally {
                expungeStaleEntries();
            }
        }

        @Override // net.bytebuddy.TypeCache
        public Class<?> insert(ClassLoader classLoader, S s, Class<?> cls) {
            try {
                return super.insert(classLoader, s, cls);
            } finally {
                expungeStaleEntries();
            }
        }

        @Override // net.bytebuddy.TypeCache
        public Class<?> findOrInsert(ClassLoader classLoader, S s, Callable<Class<?>> callable) {
            try {
                return super.findOrInsert(classLoader, s, callable);
            } finally {
                expungeStaleEntries();
            }
        }

        @Override // net.bytebuddy.TypeCache
        public Class<?> findOrInsert(ClassLoader classLoader, S s, Callable<Class<?>> callable, Object obj) {
            try {
                return super.findOrInsert(classLoader, s, callable, obj);
            } finally {
                expungeStaleEntries();
            }
        }
    }

    public static class SimpleKey {
        private final Set<String> types;

        protected boolean canEqual(Object obj) {
            return obj instanceof SimpleKey;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof SimpleKey)) {
                return false;
            }
            SimpleKey simpleKey = (SimpleKey) obj;
            if (!simpleKey.canEqual(this)) {
                return false;
            }
            Set<String> set = this.types;
            Set<String> set2 = simpleKey.types;
            return set != null ? set.equals(set2) : set2 == null;
        }

        public int hashCode() {
            Set<String> set = this.types;
            return 59 + (set == null ? 43 : set.hashCode());
        }

        public SimpleKey(Class<?> cls, Class<?>... clsArr) {
            this(cls, Arrays.asList(clsArr));
        }

        public SimpleKey(Class<?> cls, Collection<? extends Class<?>> collection) {
            this(CompoundList.of(cls, new ArrayList(collection)));
        }

        public SimpleKey(Collection<? extends Class<?>> collection) {
            this.types = new HashSet();
            Iterator<? extends Class<?>> it = collection.iterator();
            while (it.hasNext()) {
                this.types.add(it.next().getName());
            }
        }
    }
}
