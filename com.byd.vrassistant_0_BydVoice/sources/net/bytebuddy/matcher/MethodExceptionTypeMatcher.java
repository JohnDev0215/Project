package net.bytebuddy.matcher;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class MethodExceptionTypeMatcher<T extends MethodDescription> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super TypeList.Generic> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof MethodExceptionTypeMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MethodExceptionTypeMatcher)) {
            return false;
        }
        MethodExceptionTypeMatcher methodExceptionTypeMatcher = (MethodExceptionTypeMatcher) obj;
        if (!methodExceptionTypeMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super TypeList.Generic> elementMatcher = this.matcher;
        ElementMatcher<? super TypeList.Generic> elementMatcher2 = methodExceptionTypeMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<? super TypeList.Generic> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public MethodExceptionTypeMatcher(ElementMatcher<? super TypeList.Generic> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return this.matcher.matches(t.getExceptionTypes());
    }

    public String toString() {
        return "exceptions(" + this.matcher + ")";
    }
}
