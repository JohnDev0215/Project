package net.bytebuddy.matcher;

import java.lang.Iterable;
import java.util.Collection;
import java.util.Iterator;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class CollectionSizeMatcher<T extends Iterable<?>> extends ElementMatcher.Junction.AbstractBase<T> {
    private final int size;

    protected boolean canEqual(Object obj) {
        return obj instanceof CollectionSizeMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CollectionSizeMatcher)) {
            return false;
        }
        CollectionSizeMatcher collectionSizeMatcher = (CollectionSizeMatcher) obj;
        return collectionSizeMatcher.canEqual(this) && this.size == collectionSizeMatcher.size;
    }

    public int hashCode() {
        return 59 + this.size;
    }

    public CollectionSizeMatcher(int i) {
        this.size = i;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        if (t instanceof Collection) {
            return ((Collection) t).size() == this.size;
        }
        Iterator it = t.iterator();
        int i = 0;
        while (it.hasNext()) {
            it.next();
            i++;
        }
        return i == this.size;
    }

    public String toString() {
        return "ofSize(" + this.size + ')';
    }
}
