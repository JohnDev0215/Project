package net.bytebuddy.matcher;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class HasSuperTypeMatcher<T extends TypeDescription> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super TypeDescription.Generic> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof HasSuperTypeMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HasSuperTypeMatcher)) {
            return false;
        }
        HasSuperTypeMatcher hasSuperTypeMatcher = (HasSuperTypeMatcher) obj;
        if (!hasSuperTypeMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super TypeDescription.Generic> elementMatcher = this.matcher;
        ElementMatcher<? super TypeDescription.Generic> elementMatcher2 = hasSuperTypeMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<? super TypeDescription.Generic> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public HasSuperTypeMatcher(ElementMatcher<? super TypeDescription.Generic> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        HashSet hashSet = new HashSet();
        Iterator it = t.iterator();
        while (it.hasNext()) {
            TypeDefinition typeDefinition = (TypeDefinition) it.next();
            if (this.matcher.matches(typeDefinition.asGenericType()) || hasInterface(typeDefinition, hashSet)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasInterface(TypeDefinition typeDefinition, Set<TypeDescription> set) {
        for (TypeDefinition typeDefinition2 : typeDefinition.getInterfaces()) {
            if (set.add(typeDefinition2.asErasure()) && (this.matcher.matches(typeDefinition2.asGenericType()) || hasInterface(typeDefinition2, set))) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "hasSuperType(" + this.matcher + ")";
    }
}
