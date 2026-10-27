package net.bytebuddy.dynamic.loading;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

/* JADX INFO: loaded from: classes3.dex */
public class MultipleParentClassLoader extends ClassLoader {
    private final List<? extends ClassLoader> parents;

    public MultipleParentClassLoader(List<? extends ClassLoader> list) {
        this(ClassLoadingStrategy.BOOTSTRAP_LOADER, list);
    }

    public MultipleParentClassLoader(ClassLoader classLoader, List<? extends ClassLoader> list) {
        super(classLoader);
        this.parents = list;
    }

    @Override // java.lang.ClassLoader
    protected Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
        Iterator<? extends ClassLoader> it = this.parents.iterator();
        while (it.hasNext()) {
            try {
                Class<?> clsLoadClass = it.next().loadClass(str);
                if (z) {
                    resolveClass(clsLoadClass);
                }
                return clsLoadClass;
            } catch (ClassNotFoundException unused) {
            }
        }
        return super.loadClass(str, z);
    }

    @Override // java.lang.ClassLoader
    public URL getResource(String str) {
        Iterator<? extends ClassLoader> it = this.parents.iterator();
        while (it.hasNext()) {
            URL resource = it.next().getResource(str);
            if (resource != null) {
                return resource;
            }
        }
        return super.getResource(str);
    }

    @Override // java.lang.ClassLoader
    public Enumeration<URL> getResources(String str) throws IOException {
        ArrayList arrayList = new ArrayList(this.parents.size() + 1);
        Iterator<? extends ClassLoader> it = this.parents.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getResources(str));
        }
        arrayList.add(super.getResources(str));
        return new CompoundEnumeration(arrayList);
    }

    protected static class CompoundEnumeration implements Enumeration<URL> {
        private static final int FIRST = 0;
        private Enumeration<URL> currentEnumeration;
        private final List<Enumeration<URL>> enumerations;

        protected CompoundEnumeration(List<Enumeration<URL>> list) {
            this.enumerations = list;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            Enumeration<URL> enumeration = this.currentEnumeration;
            if (enumeration != null && enumeration.hasMoreElements()) {
                return true;
            }
            if (this.enumerations.isEmpty()) {
                return false;
            }
            this.currentEnumeration = this.enumerations.remove(0);
            return hasMoreElements();
        }

        @Override // java.util.Enumeration
        public URL nextElement() {
            if (hasMoreElements()) {
                return this.currentEnumeration.nextElement();
            }
            throw new NoSuchElementException();
        }
    }

    public static class Builder {
        private static final int ONLY = 0;
        private final List<? extends ClassLoader> classLoaders;

        protected boolean canEqual(Object obj) {
            return obj instanceof Builder;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Builder)) {
                return false;
            }
            Builder builder = (Builder) obj;
            if (!builder.canEqual(this)) {
                return false;
            }
            List<? extends ClassLoader> list = this.classLoaders;
            List<? extends ClassLoader> list2 = builder.classLoaders;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            List<? extends ClassLoader> list = this.classLoaders;
            return 59 + (list == null ? 43 : list.hashCode());
        }

        public Builder() {
            this(Collections.emptyList());
        }

        private Builder(List<? extends ClassLoader> list) {
            this.classLoaders = list;
        }

        public Builder append(Class<?>... clsArr) {
            return append((Collection<? extends Class<?>>) Arrays.asList(clsArr));
        }

        public Builder append(Collection<? extends Class<?>> collection) {
            ArrayList arrayList = new ArrayList(collection.size());
            Iterator<? extends Class<?>> it = collection.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getClassLoader());
            }
            return append((List<? extends ClassLoader>) arrayList);
        }

        public Builder append(ClassLoader... classLoaderArr) {
            return append(Arrays.asList(classLoaderArr));
        }

        public Builder append(List<? extends ClassLoader> list) {
            ArrayList arrayList = new ArrayList(this.classLoaders.size() + list.size());
            HashSet hashSet = new HashSet(this.classLoaders);
            arrayList.addAll(this.classLoaders);
            for (ClassLoader classLoader : list) {
                if (classLoader != null && hashSet.add(classLoader)) {
                    arrayList.add(classLoader);
                }
            }
            return new Builder(arrayList);
        }

        public Builder filter(ElementMatcher<? super ClassLoader> elementMatcher) {
            ArrayList arrayList = new ArrayList(this.classLoaders.size());
            for (ClassLoader classLoader : this.classLoaders) {
                if (elementMatcher.matches(classLoader)) {
                    arrayList.add(classLoader);
                }
            }
            return new Builder(arrayList);
        }

        public ClassLoader build() {
            return this.classLoaders.size() == 1 ? this.classLoaders.get(0) : new MultipleParentClassLoader(this.classLoaders);
        }

        public ClassLoader build(ClassLoader classLoader) {
            return !this.classLoaders.isEmpty() ? (this.classLoaders.size() == 1 && this.classLoaders.contains(classLoader)) ? classLoader : filter(ElementMatchers.not(ElementMatchers.is(classLoader))).doBuild(classLoader) : classLoader;
        }

        private ClassLoader doBuild(ClassLoader classLoader) {
            return new MultipleParentClassLoader(classLoader, this.classLoaders);
        }
    }
}
