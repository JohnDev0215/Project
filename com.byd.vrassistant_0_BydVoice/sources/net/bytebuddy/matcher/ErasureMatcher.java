package net.bytebuddy.matcher;

import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class ErasureMatcher<T extends TypeDefinition> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super TypeDescription> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof ErasureMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ErasureMatcher)) {
            return false;
        }
        ErasureMatcher erasureMatcher = (ErasureMatcher) obj;
        if (!erasureMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super TypeDescription> elementMatcher = this.matcher;
        ElementMatcher<? super TypeDescription> elementMatcher2 = erasureMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<? super TypeDescription> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public ErasureMatcher(ElementMatcher<? super TypeDescription> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return this.matcher.matches(t.asErasure());
    }

    public String toString() {
        return "erasure(" + this.matcher + ")";
    }
}
