package net.bytebuddy.matcher;

import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class NullMatcher<T> extends ElementMatcher.Junction.AbstractBase<T> {
    public int hashCode() {
        return 1;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return t == null;
    }

    public String toString() {
        return "isNull()";
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof NullMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof NullMatcher) && ((NullMatcher) obj).canEqual(this);
    }
}
