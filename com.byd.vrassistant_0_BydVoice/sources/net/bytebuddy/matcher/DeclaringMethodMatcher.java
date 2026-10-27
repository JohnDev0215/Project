package net.bytebuddy.matcher;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class DeclaringMethodMatcher<T extends TypeDefinition> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super MethodList<?>> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof DeclaringMethodMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeclaringMethodMatcher)) {
            return false;
        }
        DeclaringMethodMatcher declaringMethodMatcher = (DeclaringMethodMatcher) obj;
        if (!declaringMethodMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super MethodList<?>> elementMatcher = this.matcher;
        ElementMatcher<? super MethodList<?>> elementMatcher2 = declaringMethodMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<? super MethodList<?>> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public DeclaringMethodMatcher(ElementMatcher<? super MethodList<? extends MethodDescription>> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return this.matcher.matches(t.getDeclaredMethods());
    }

    public String toString() {
        return "declaresMethods(" + this.matcher + ")";
    }
}
