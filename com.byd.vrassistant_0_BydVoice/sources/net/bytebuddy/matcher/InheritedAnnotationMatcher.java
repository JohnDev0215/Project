package net.bytebuddy.matcher;

import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class InheritedAnnotationMatcher<T extends TypeDescription> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super AnnotationList> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof InheritedAnnotationMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InheritedAnnotationMatcher)) {
            return false;
        }
        InheritedAnnotationMatcher inheritedAnnotationMatcher = (InheritedAnnotationMatcher) obj;
        if (!inheritedAnnotationMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super AnnotationList> elementMatcher = this.matcher;
        ElementMatcher<? super AnnotationList> elementMatcher2 = inheritedAnnotationMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<? super AnnotationList> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public InheritedAnnotationMatcher(ElementMatcher<? super AnnotationList> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return this.matcher.matches(t.getInheritedAnnotations());
    }

    public String toString() {
        return "inheritsAnnotations(" + this.matcher + ")";
    }
}
