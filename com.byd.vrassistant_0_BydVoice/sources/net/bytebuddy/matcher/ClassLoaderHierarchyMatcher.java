package net.bytebuddy.matcher;

import java.lang.ClassLoader;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class ClassLoaderHierarchyMatcher<T extends ClassLoader> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super ClassLoader> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof ClassLoaderHierarchyMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClassLoaderHierarchyMatcher)) {
            return false;
        }
        ClassLoaderHierarchyMatcher classLoaderHierarchyMatcher = (ClassLoaderHierarchyMatcher) obj;
        if (!classLoaderHierarchyMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super ClassLoader> elementMatcher = this.matcher;
        ElementMatcher<? super ClassLoader> elementMatcher2 = classLoaderHierarchyMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<? super ClassLoader> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public ClassLoaderHierarchyMatcher(ElementMatcher<? super ClassLoader> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        while (t != null) {
            if (this.matcher.matches(t)) {
                return true;
            }
            t = (T) t.getParent();
        }
        return this.matcher.matches(null);
    }

    public String toString() {
        return "hasChild(" + this.matcher + ')';
    }
}
