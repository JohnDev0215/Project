package net.bytebuddy.matcher;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class MethodOverrideMatcher<T extends MethodDescription> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super TypeDescription.Generic> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof MethodOverrideMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MethodOverrideMatcher)) {
            return false;
        }
        MethodOverrideMatcher methodOverrideMatcher = (MethodOverrideMatcher) obj;
        if (!methodOverrideMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super TypeDescription.Generic> elementMatcher = this.matcher;
        ElementMatcher<? super TypeDescription.Generic> elementMatcher2 = methodOverrideMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<? super TypeDescription.Generic> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public MethodOverrideMatcher(ElementMatcher<? super TypeDescription.Generic> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        HashSet hashSet = new HashSet();
        for (TypeDefinition typeDefinition : t.getDeclaringType()) {
            if (matches(t, typeDefinition) || matches(t, typeDefinition.getInterfaces(), hashSet)) {
                return true;
            }
        }
        return false;
    }

    private boolean matches(MethodDescription methodDescription, List<? extends TypeDefinition> list, Set<TypeDescription> set) {
        for (TypeDefinition typeDefinition : list) {
            if (set.add(typeDefinition.asErasure()) && (matches(methodDescription, typeDefinition) || matches(methodDescription, typeDefinition.getInterfaces(), set))) {
                return true;
            }
        }
        return false;
    }

    private boolean matches(MethodDescription methodDescription, TypeDefinition typeDefinition) {
        Iterator it = typeDefinition.getDeclaredMethods().filter(ElementMatchers.isVirtual()).iterator();
        while (it.hasNext()) {
            if (((MethodDescription) it.next()).asSignatureToken().equals(methodDescription.asSignatureToken())) {
                return this.matcher.matches(typeDefinition.asGenericType());
            }
        }
        return false;
    }

    public String toString() {
        return "isOverriddenFrom(" + this.matcher + ")";
    }
}
