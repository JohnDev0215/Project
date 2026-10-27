package net.bytebuddy.matcher;

import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class AccessibilityMatcher<T extends ByteCodeElement> extends ElementMatcher.Junction.AbstractBase<T> {
    private final TypeDescription typeDescription;

    protected boolean canEqual(Object obj) {
        return obj instanceof AccessibilityMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AccessibilityMatcher)) {
            return false;
        }
        AccessibilityMatcher accessibilityMatcher = (AccessibilityMatcher) obj;
        if (!accessibilityMatcher.canEqual(this)) {
            return false;
        }
        TypeDescription typeDescription = this.typeDescription;
        TypeDescription typeDescription2 = accessibilityMatcher.typeDescription;
        return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
    }

    public int hashCode() {
        TypeDescription typeDescription = this.typeDescription;
        return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
    }

    public AccessibilityMatcher(TypeDescription typeDescription) {
        this.typeDescription = typeDescription;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return t.isAccessibleTo(this.typeDescription);
    }

    public String toString() {
        return "isAccessibleTo(" + this.typeDescription + ")";
    }
}
