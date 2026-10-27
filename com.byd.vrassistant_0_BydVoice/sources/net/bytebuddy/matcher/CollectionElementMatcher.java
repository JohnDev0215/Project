package net.bytebuddy.matcher;

import java.util.Iterator;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class CollectionElementMatcher<T> extends ElementMatcher.Junction.AbstractBase<Iterable<? extends T>> {
    private final int index;
    private final ElementMatcher<? super T> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof CollectionElementMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CollectionElementMatcher)) {
            return false;
        }
        CollectionElementMatcher collectionElementMatcher = (CollectionElementMatcher) obj;
        if (!collectionElementMatcher.canEqual(this) || this.index != collectionElementMatcher.index) {
            return false;
        }
        ElementMatcher<? super T> elementMatcher = this.matcher;
        ElementMatcher<? super T> elementMatcher2 = collectionElementMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        int i = this.index + 59;
        ElementMatcher<? super T> elementMatcher = this.matcher;
        return (i * 59) + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public CollectionElementMatcher(int i, ElementMatcher<? super T> elementMatcher) {
        this.index = i;
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(Iterable<? extends T> iterable) {
        Iterator<? extends T> it = iterable.iterator();
        for (int i = 0; i < this.index; i++) {
            if (!it.hasNext()) {
                return false;
            }
            it.next();
        }
        return it.hasNext() && this.matcher.matches(it.next());
    }

    public String toString() {
        return "with(" + this.index + " matches " + this.matcher + ")";
    }
}
