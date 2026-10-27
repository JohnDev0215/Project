package net.bytebuddy.matcher;

import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class AnnotationTypeMatcher<T extends AnnotationDescription> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super TypeDescription> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof AnnotationTypeMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AnnotationTypeMatcher)) {
            return false;
        }
        AnnotationTypeMatcher annotationTypeMatcher = (AnnotationTypeMatcher) obj;
        if (!annotationTypeMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super TypeDescription> elementMatcher = this.matcher;
        ElementMatcher<? super TypeDescription> elementMatcher2 = annotationTypeMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<? super TypeDescription> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public AnnotationTypeMatcher(ElementMatcher<? super TypeDescription> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return this.matcher.matches(t.getAnnotationType());
    }

    public String toString() {
        return "ofAnnotationType(" + this.matcher + ')';
    }
}
