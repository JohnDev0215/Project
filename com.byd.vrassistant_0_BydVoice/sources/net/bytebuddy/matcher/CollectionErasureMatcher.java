package net.bytebuddy.matcher;

import java.lang.Iterable;
import java.util.ArrayList;
import java.util.Iterator;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class CollectionErasureMatcher<T extends Iterable<? extends TypeDefinition>> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super Iterable<? extends TypeDescription>> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof CollectionErasureMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CollectionErasureMatcher)) {
            return false;
        }
        CollectionErasureMatcher collectionErasureMatcher = (CollectionErasureMatcher) obj;
        if (!collectionErasureMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super Iterable<? extends TypeDescription>> elementMatcher = this.matcher;
        ElementMatcher<? super Iterable<? extends TypeDescription>> elementMatcher2 = collectionErasureMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<? super Iterable<? extends TypeDescription>> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public CollectionErasureMatcher(ElementMatcher<? super Iterable<? extends TypeDescription>> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        ArrayList arrayList = new ArrayList();
        Iterator it = t.iterator();
        while (it.hasNext()) {
            arrayList.add(((TypeDefinition) it.next()).asErasure());
        }
        return this.matcher.matches(arrayList);
    }

    public String toString() {
        return "erasures(" + this.matcher + ')';
    }
}
