package net.bytebuddy.matcher;

import java.util.List;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class MethodParameterTypesMatcher<T extends ParameterList<?>> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super List<? extends TypeDescription.Generic>> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof MethodParameterTypesMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MethodParameterTypesMatcher)) {
            return false;
        }
        MethodParameterTypesMatcher methodParameterTypesMatcher = (MethodParameterTypesMatcher) obj;
        if (!methodParameterTypesMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super List<? extends TypeDescription.Generic>> elementMatcher = this.matcher;
        ElementMatcher<? super List<? extends TypeDescription.Generic>> elementMatcher2 = methodParameterTypesMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<? super List<? extends TypeDescription.Generic>> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public MethodParameterTypesMatcher(ElementMatcher<? super List<? extends TypeDescription.Generic>> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return this.matcher.matches(t.asTypeList());
    }

    public String toString() {
        return "hasTypes(" + this.matcher + ")";
    }
}
