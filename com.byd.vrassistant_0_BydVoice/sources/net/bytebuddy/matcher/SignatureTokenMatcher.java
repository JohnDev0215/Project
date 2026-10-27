package net.bytebuddy.matcher;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class SignatureTokenMatcher<T extends MethodDescription> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super MethodDescription.SignatureToken> matcher;

    protected boolean canEqual(Object obj) {
        return obj instanceof SignatureTokenMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SignatureTokenMatcher)) {
            return false;
        }
        SignatureTokenMatcher signatureTokenMatcher = (SignatureTokenMatcher) obj;
        if (!signatureTokenMatcher.canEqual(this)) {
            return false;
        }
        ElementMatcher<? super MethodDescription.SignatureToken> elementMatcher = this.matcher;
        ElementMatcher<? super MethodDescription.SignatureToken> elementMatcher2 = signatureTokenMatcher.matcher;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        ElementMatcher<? super MethodDescription.SignatureToken> elementMatcher = this.matcher;
        return 59 + (elementMatcher == null ? 43 : elementMatcher.hashCode());
    }

    public SignatureTokenMatcher(ElementMatcher<? super MethodDescription.SignatureToken> elementMatcher) {
        this.matcher = elementMatcher;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(T t) {
        return this.matcher.matches(t.asSignatureToken());
    }

    public String toString() {
        return "signature(" + this.matcher + ")";
    }
}
