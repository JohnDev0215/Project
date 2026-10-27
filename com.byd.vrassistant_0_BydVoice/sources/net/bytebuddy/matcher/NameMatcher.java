package net.bytebuddy.matcher;

import net.bytebuddy.description.NamedElement;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class NameMatcher<T extends NamedElement> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<String> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof NameMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NameMatcher)) {
            return false;
        }
        NameMatcher nameMatcher = (NameMatcher) obj;
        if (!nameMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<String> elementMatcher = this.matcher;
        ElementMatcher<String> elementMatcher2 = nameMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<String> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public NameMatcher(ElementMatcher<String> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return this.matcher.matches(t.getActualName());
    }

    public String toString() {
        return "name(" + this.matcher + ")";
    }
}
