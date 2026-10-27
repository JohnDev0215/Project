package net.bytebuddy.matcher;

import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class FailSafeMatcher<T> extends ElementMatcher.Junction.AbstractBase<T> {
    private final boolean fallback;
    private final ElementMatcher<? super T> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof FailSafeMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FailSafeMatcher)) {
            return false;
        }
        FailSafeMatcher failSafeMatcher = (FailSafeMatcher) obj;
        if (!failSafeMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super T> elementMatcher = this.matcher;
        ElementMatcher<? super T> elementMatcher2 = failSafeMatcher.matcher;
        if (elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null) {
            return this.fallback == failSafeMatcher.fallback;
        }
        return false;
    }

    public int hashCode() {
        ElementMatcher<? super T> elementMatcher = this.matcher;
        return (((elementMatcher == null ? 43 : elementMatcher.hashCode()) + 59) * 59) + (this.fallback ? 79 : 97);
    }

    public FailSafeMatcher(ElementMatcher<? super T> elementMatcher, boolean z) {
        this.matcher = elementMatcher;
        this.fallback = z;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        try {
            return this.matcher.matches(t);
        } catch (Exception unused) {
            return this.fallback;
        }
    }

    public String toString() {
        return "failSafe(try(" + this.matcher + ") or " + this.fallback + ")";
    }
}
