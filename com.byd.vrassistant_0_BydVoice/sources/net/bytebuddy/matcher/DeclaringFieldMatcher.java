package net.bytebuddy.matcher;

import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class DeclaringFieldMatcher<T extends TypeDefinition> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super FieldList<?>> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof DeclaringFieldMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeclaringFieldMatcher)) {
            return false;
        }
        DeclaringFieldMatcher declaringFieldMatcher = (DeclaringFieldMatcher) obj;
        if (!declaringFieldMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super FieldList<?>> elementMatcher = this.matcher;
        ElementMatcher<? super FieldList<?>> elementMatcher2 = declaringFieldMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<? super FieldList<?>> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public DeclaringFieldMatcher(ElementMatcher<? super FieldList<? extends FieldDescription>> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return this.matcher.matches(t.getDeclaredFields());
    }

    public String toString() {
        return "declaresFields(" + this.matcher + ")";
    }
}
