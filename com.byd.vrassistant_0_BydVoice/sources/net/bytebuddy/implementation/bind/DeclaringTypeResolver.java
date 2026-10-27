package net.bytebuddy.implementation.bind;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;

/* JADX INFO: loaded from: classes3.dex */
public enum DeclaringTypeResolver implements MethodDelegationBinder.AmbiguityResolver {
    INSTANCE;

    @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.AmbiguityResolver
    public MethodDelegationBinder.AmbiguityResolver.Resolution resolve(MethodDescription methodDescription, MethodDelegationBinder.MethodBinding methodBinding, MethodDelegationBinder.MethodBinding methodBinding2) {
        TypeDescription typeDescriptionAsErasure = methodBinding.getTarget().getDeclaringType().asErasure();
        TypeDescription typeDescriptionAsErasure2 = methodBinding2.getTarget().getDeclaringType().asErasure();
        if (typeDescriptionAsErasure.equals(typeDescriptionAsErasure2)) {
            return MethodDelegationBinder.AmbiguityResolver.Resolution.AMBIGUOUS;
        }
        if (typeDescriptionAsErasure.isAssignableFrom(typeDescriptionAsErasure2)) {
            return MethodDelegationBinder.AmbiguityResolver.Resolution.RIGHT;
        }
        if (typeDescriptionAsErasure.isAssignableTo(typeDescriptionAsErasure2)) {
            return MethodDelegationBinder.AmbiguityResolver.Resolution.LEFT;
        }
        return MethodDelegationBinder.AmbiguityResolver.Resolution.AMBIGUOUS;
    }
}
