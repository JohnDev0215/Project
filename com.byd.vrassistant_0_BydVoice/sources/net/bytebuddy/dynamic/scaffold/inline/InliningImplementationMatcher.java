package net.bytebuddy.dynamic.scaffold.inline;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.LatentMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class InliningImplementationMatcher implements LatentMatcher<MethodDescription> {
    private final LatentMatcher<? super MethodDescription> ignoredMethods;
    private final ElementMatcher<? super MethodDescription> predefinedMethodSignatures;

    protected boolean canEqual(Object obj) {
        return obj instanceof InliningImplementationMatcher;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InliningImplementationMatcher)) {
            return false;
        }
        InliningImplementationMatcher inliningImplementationMatcher = (InliningImplementationMatcher) obj;
        if (!inliningImplementationMatcher.canEqual(this)) {
            return false;
        }
        LatentMatcher<? super MethodDescription> latentMatcher = this.ignoredMethods;
        LatentMatcher<? super MethodDescription> latentMatcher2 = inliningImplementationMatcher.ignoredMethods;
        if (latentMatcher != null ? !latentMatcher.equals(latentMatcher2) : latentMatcher2 != null) {
            return false;
        }
        ElementMatcher<? super MethodDescription> elementMatcher = this.predefinedMethodSignatures;
        ElementMatcher<? super MethodDescription> elementMatcher2 = inliningImplementationMatcher.predefinedMethodSignatures;
        return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
    }

    public int hashCode() {
        LatentMatcher<? super MethodDescription> latentMatcher = this.ignoredMethods;
        int iHashCode = latentMatcher == null ? 43 : latentMatcher.hashCode();
        ElementMatcher<? super MethodDescription> elementMatcher = this.predefinedMethodSignatures;
        return ((iHashCode + 59) * 59) + (elementMatcher != null ? elementMatcher.hashCode() : 43);
    }

    protected InliningImplementationMatcher(LatentMatcher<? super MethodDescription> latentMatcher, ElementMatcher<? super MethodDescription> elementMatcher) {
        this.ignoredMethods = latentMatcher;
        this.predefinedMethodSignatures = elementMatcher;
    }

    protected static LatentMatcher<MethodDescription> of(LatentMatcher<? super MethodDescription> latentMatcher, TypeDescription typeDescription) {
        ElementMatcher.Junction junctionNamed;
        ElementMatcher.Junction junctionNone = ElementMatchers.none();
        for (MethodDescription methodDescription : typeDescription.getDeclaredMethods()) {
            if (methodDescription.isConstructor()) {
                junctionNamed = ElementMatchers.isConstructor();
            } else {
                junctionNamed = ElementMatchers.named(methodDescription.getName());
            }
            junctionNone = junctionNone.or(junctionNamed.and(ElementMatchers.returns(methodDescription.getReturnType().asErasure())).and(ElementMatchers.takesArguments(methodDescription.getParameters().asTypeList().asErasures())));
        }
        return new InliningImplementationMatcher(latentMatcher, junctionNone);
    }

    @Override // net.bytebuddy.matcher.LatentMatcher
    public ElementMatcher<? super MethodDescription> resolve(TypeDescription typeDescription) {
        return ElementMatchers.not(this.ignoredMethods.resolve(typeDescription)).and(ElementMatchers.isVirtual().and(ElementMatchers.not(ElementMatchers.isFinal())).or(ElementMatchers.isDeclaredBy(typeDescription))).or(ElementMatchers.isDeclaredBy(typeDescription).and(ElementMatchers.not(this.predefinedMethodSignatures)));
    }
}
