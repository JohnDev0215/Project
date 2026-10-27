package net.bytebuddy.matcher;

import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.ByteCodeElement.TypeDependant;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class DefinedShapeMatcher<T extends ByteCodeElement.TypeDependant<S, ?>, S extends ByteCodeElement.TypeDependant<?, ?>> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super S> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof DefinedShapeMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DefinedShapeMatcher)) {
            return false;
        }
        DefinedShapeMatcher definedShapeMatcher = (DefinedShapeMatcher) obj;
        if (!definedShapeMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super S> elementMatcher = this.matcher;
        ElementMatcher<? super S> elementMatcher2 = definedShapeMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<? super S> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public DefinedShapeMatcher(ElementMatcher<? super S> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return this.matcher.matches(t.asDefined());
    }

    public String toString() {
        return "isDefinedAs(" + this.matcher + ')';
    }
}
