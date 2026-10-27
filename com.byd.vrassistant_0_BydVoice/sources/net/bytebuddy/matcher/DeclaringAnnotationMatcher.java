package net.bytebuddy.matcher;

import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationSource;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class DeclaringAnnotationMatcher<T extends AnnotationSource> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super AnnotationList> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof DeclaringAnnotationMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeclaringAnnotationMatcher)) {
            return false;
        }
        DeclaringAnnotationMatcher declaringAnnotationMatcher = (DeclaringAnnotationMatcher) obj;
        if (!declaringAnnotationMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super AnnotationList> elementMatcher = this.matcher;
        ElementMatcher<? super AnnotationList> elementMatcher2 = declaringAnnotationMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<? super AnnotationList> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public DeclaringAnnotationMatcher(ElementMatcher<? super AnnotationList> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return this.matcher.matches(t.getDeclaredAnnotations());
    }

    public String toString() {
        return "declaresAnnotations(" + this.matcher + ")";
    }
}
