package net.bytebuddy.matcher;

import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class EqualityMatcher<T> extends ElementMatcher.Junction.AbstractBase<T> {
    private final Object value;

    protected boolean canEqual(Object obj) {
        return obj instanceof EqualityMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EqualityMatcher)) {
            return false;
        }
        EqualityMatcher equalityMatcher = (EqualityMatcher) obj;
        if (!equalityMatcher.canEqual(this)) {
            return false;
        }
        Object obj2 = this.value;
        Object obj3 = equalityMatcher.value;
        return obj2 != null ? obj2.equals(obj3) : obj3 == null;
    }

    public int hashCode() {
        Object obj = this.value;
        return 59 + (obj == null ? 43 : obj.hashCode());
    }

    public EqualityMatcher(Object obj) {
        this.value = obj;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return this.value.equals(t);
    }

    public String toString() {
        return "is(" + this.value + ")";
    }
}
