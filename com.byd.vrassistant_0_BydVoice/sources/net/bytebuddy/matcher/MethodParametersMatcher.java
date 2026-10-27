package net.bytebuddy.matcher;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class MethodParametersMatcher<T extends MethodDescription> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super ParameterList<?>> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof MethodParametersMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MethodParametersMatcher)) {
            return false;
        }
        MethodParametersMatcher methodParametersMatcher = (MethodParametersMatcher) obj;
        if (!methodParametersMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super ParameterList<?>> elementMatcher = this.matcher;
        ElementMatcher<? super ParameterList<?>> elementMatcher2 = methodParametersMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<? super ParameterList<?>> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public MethodParametersMatcher(ElementMatcher<? super ParameterList<? extends ParameterDescription>> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return this.matcher.matches(t.getParameters());
    }

    public String toString() {
        return "hasParameter(" + this.matcher + ")";
    }
}
