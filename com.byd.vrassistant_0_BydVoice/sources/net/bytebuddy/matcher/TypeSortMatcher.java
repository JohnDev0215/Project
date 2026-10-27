package net.bytebuddy.matcher;

import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class TypeSortMatcher<T extends TypeDefinition> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super TypeDefinition.Sort> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof TypeSortMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TypeSortMatcher)) {
            return false;
        }
        TypeSortMatcher typeSortMatcher = (TypeSortMatcher) obj;
        if (!typeSortMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super TypeDefinition.Sort> elementMatcher = this.matcher;
        ElementMatcher<? super TypeDefinition.Sort> elementMatcher2 = typeSortMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<? super TypeDefinition.Sort> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public TypeSortMatcher(ElementMatcher<? super TypeDefinition.Sort> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return this.matcher.matches(t.getSort());
    }

    public String toString() {
        return "ofSort(" + this.matcher + ')';
    }
}
