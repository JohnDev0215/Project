package net.bytebuddy.matcher;

import net.bytebuddy.description.DeclaredByType;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class DeclaringTypeMatcher<T extends DeclaredByType> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super TypeDescription.Generic> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof DeclaringTypeMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeclaringTypeMatcher)) {
            return false;
        }
        DeclaringTypeMatcher declaringTypeMatcher = (DeclaringTypeMatcher) obj;
        if (!declaringTypeMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super TypeDescription.Generic> elementMatcher = this.matcher;
        ElementMatcher<? super TypeDescription.Generic> elementMatcher2 = declaringTypeMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<? super TypeDescription.Generic> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public DeclaringTypeMatcher(ElementMatcher<? super TypeDescription.Generic> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        TypeDefinition declaringType = t.getDeclaringType();
        return declaringType != null && this.matcher.matches(declaringType.asGenericType());
    }

    public String toString() {
        return "declaredBy(" + this.matcher + ")";
    }
}
