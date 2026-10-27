package net.bytebuddy.matcher;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class CollectionOneToOneMatcher<T> extends ElementMatcher.Junction.AbstractBase<Iterable<? extends T>> {
    private final List<? extends ElementMatcher<? super T>> matchers;

    protected boolean canEqual(Object obj) {
        return obj instanceof CollectionOneToOneMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CollectionOneToOneMatcher)) {
            return false;
        }
        CollectionOneToOneMatcher collectionOneToOneMatcher = (CollectionOneToOneMatcher) obj;
        if (!collectionOneToOneMatcher.canEqual(this)) {
            return false;
        }
        List<? extends ElementMatcher<? super T>> list = this.matchers;
        List<? extends ElementMatcher<? super T>> list2 = collectionOneToOneMatcher.matchers;
        return list != null ? list.equals(list2) : list2 == null;
    }

    public int hashCode() {
        List<? extends ElementMatcher<? super T>> list = this.matchers;
        return 59 + (list == null ? 43 : list.hashCode());
    }

    public CollectionOneToOneMatcher(List<? extends ElementMatcher<? super T>> list) {
        this.matchers = list;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(Iterable<? extends T> iterable) {
        if ((iterable instanceof Collection) && ((Collection) iterable).size() != this.matchers.size()) {
            return false;
        }
        Iterator<? extends ElementMatcher<? super T>> it = this.matchers.iterator();
        for (T t : iterable) {
            if (!it.hasNext() || !it.next().matches(t)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("containing(");
        boolean z = true;
        for (ElementMatcher<? super T> elementMatcher : this.matchers) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(elementMatcher);
        }
        return sb.append(")").toString();
    }
}
