package net.bytebuddy.matcher;

import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.NamedElement.WithOptionalName;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class IsNamedMatcher<T extends NamedElement.WithOptionalName> extends ElementMatcher.Junction.AbstractBase<T> {
    public int hashCode() {
        return 1;
    }

    public String toString() {
        return "isNamed()";
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof IsNamedMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof IsNamedMatcher) && ((IsNamedMatcher) obj).canEqual(this);
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return t.isNamed();
    }
}
