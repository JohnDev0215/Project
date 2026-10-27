package net.bytebuddy.matcher;

import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class BooleanMatcher<T> extends ElementMatcher.Junction.AbstractBase<T> {
    private final boolean matches;

    protected boolean canEqual(Object obj) {
        return obj instanceof BooleanMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BooleanMatcher)) {
            return false;
        }
        BooleanMatcher booleanMatcher = (BooleanMatcher) obj;
        return booleanMatcher.canEqual(this) && this.matches == booleanMatcher.matches;
    }

    public int hashCode() {
        return 59 + (this.matches ? 79 : 97);
    }

    public BooleanMatcher(boolean z) {
        this.matches = z;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return this.matches;
    }

    public String toString() {
        return Boolean.toString(this.matches);
    }
}
