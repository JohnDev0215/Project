package net.bytebuddy.matcher;

import java.lang.ClassLoader;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class ClassLoaderParentMatcher<T extends ClassLoader> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ClassLoader classLoader;

    protected boolean canEqual(Object obj) {
        return obj instanceof ClassLoaderParentMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClassLoaderParentMatcher)) {
            return false;
        }
        ClassLoaderParentMatcher classLoaderParentMatcher = (ClassLoaderParentMatcher) obj;
        if (!classLoaderParentMatcher.canEqual(this)) {
            return false;
        }
        ClassLoader classLoader = this.classLoader;
        ClassLoader classLoader2 = classLoaderParentMatcher.classLoader;
        return classLoader != null ? classLoader.equals(classLoader2) : classLoader2 == null;
    }

    public int hashCode() {
        ClassLoader classLoader = this.classLoader;
        return 59 + (classLoader == null ? 43 : classLoader.hashCode());
    }

    public ClassLoaderParentMatcher(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        for (ClassLoader parent = this.classLoader; parent != null; parent = parent.getParent()) {
            if (parent == t) {
                return true;
            }
        }
        return t == null;
    }

    public String toString() {
        return "isParentOf(" + this.classLoader + ')';
    }
}
