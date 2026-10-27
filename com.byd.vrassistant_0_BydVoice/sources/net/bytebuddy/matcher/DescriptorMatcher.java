package net.bytebuddy.matcher;

import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class DescriptorMatcher<T extends ByteCodeElement> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<String> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof DescriptorMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DescriptorMatcher)) {
            return false;
        }
        DescriptorMatcher descriptorMatcher = (DescriptorMatcher) obj;
        if (!descriptorMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<String> elementMatcher = this.matcher;
        ElementMatcher<String> elementMatcher2 = descriptorMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<String> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public DescriptorMatcher(ElementMatcher<String> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return this.matcher.matches(t.getDescriptor());
    }

    public String toString() {
        return "hasDescriptor(" + this.matcher + ")";
    }
}
