package net.bytebuddy.matcher;

import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class MethodParameterTypeMatcher<T extends ParameterDescription> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super TypeDescription.Generic> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof MethodParameterTypeMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MethodParameterTypeMatcher)) {
            return false;
        }
        MethodParameterTypeMatcher methodParameterTypeMatcher = (MethodParameterTypeMatcher) obj;
        if (!methodParameterTypeMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super TypeDescription.Generic> elementMatcher = this.matcher;
        ElementMatcher<? super TypeDescription.Generic> elementMatcher2 = methodParameterTypeMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<? super TypeDescription.Generic> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public MethodParameterTypeMatcher(ElementMatcher<? super TypeDescription.Generic> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return this.matcher.matches(t.getType());
    }

    public String toString() {
        return "hasType(" + this.matcher + ")";
    }
}
