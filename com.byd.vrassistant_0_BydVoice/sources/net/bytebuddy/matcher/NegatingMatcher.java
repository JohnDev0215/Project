package net.bytebuddy.matcher;

import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class NegatingMatcher<T> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super T> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof NegatingMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NegatingMatcher)) {
            return false;
        }
        NegatingMatcher negatingMatcher = (NegatingMatcher) obj;
        if (!negatingMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super T> elementMatcher = this.matcher;
        ElementMatcher<? super T> elementMatcher2 = negatingMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<? super T> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public NegatingMatcher(ElementMatcher<? super T> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return !this.matcher.matches(t);
    }

    public String toString() {
        return "not(" + this.matcher + ')';
    }
}
