package net.bytebuddy.matcher;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class InstanceTypeMatcher<T> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super TypeDescription> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof InstanceTypeMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InstanceTypeMatcher)) {
            return false;
        }
        InstanceTypeMatcher instanceTypeMatcher = (InstanceTypeMatcher) obj;
        if (!instanceTypeMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super TypeDescription> elementMatcher = this.matcher;
        ElementMatcher<? super TypeDescription> elementMatcher2 = instanceTypeMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<? super TypeDescription> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public InstanceTypeMatcher(ElementMatcher<? super TypeDescription> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return t != null && this.matcher.matches(new TypeDescription.ForLoadedType(t.getClass()));
    }

    public String toString() {
        return "ofType(" + this.matcher + ")";
    }
}
