package net.bytebuddy.matcher;

import java.util.Iterator;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class CollectionItemMatcher<T> extends ElementMatcher.Junction.AbstractBase<Iterable<? extends T>> {
    private final ElementMatcher<? super T> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof CollectionItemMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CollectionItemMatcher)) {
            return false;
        }
        CollectionItemMatcher collectionItemMatcher = (CollectionItemMatcher) obj;
        if (!collectionItemMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super T> elementMatcher = this.matcher;
        ElementMatcher<? super T> elementMatcher2 = collectionItemMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<? super T> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public CollectionItemMatcher(ElementMatcher<? super T> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(Iterable<? extends T> iterable) {
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            if (this.matcher.matches(it.next())) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "whereOne(" + this.matcher + ")";
    }
}
