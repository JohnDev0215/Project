package net.bytebuddy.dynamic.scaffold.inline;

import java.util.Map;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public class RebaseImplementationTarget extends Implementation.Target.AbstractBase {
    private final Map<MethodDescription.SignatureToken, MethodRebaseResolver.Resolution> rebaseableMethods;

    @Override // net.bytebuddy.implementation.Implementation.Target.AbstractBase
    protected boolean canEqual(Object obj) {
        return obj instanceof RebaseImplementationTarget;
    }

    @Override // net.bytebuddy.implementation.Implementation.Target.AbstractBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RebaseImplementationTarget)) {
            return false;
        }
        RebaseImplementationTarget rebaseImplementationTarget = (RebaseImplementationTarget) obj;
        if (!rebaseImplementationTarget.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        Map<MethodDescription.SignatureToken, MethodRebaseResolver.Resolution> map = this.rebaseableMethods;
        Map<MethodDescription.SignatureToken, MethodRebaseResolver.Resolution> map2 = rebaseImplementationTarget.rebaseableMethods;
        return map != null ? map.equals(map2) : map2 == null;
    }

    @Override // net.bytebuddy.implementation.Implementation.Target.AbstractBase
    public int hashCode() {
        int iHashCode = super.hashCode() + 59;
        Map<MethodDescription.SignatureToken, MethodRebaseResolver.Resolution> map = this.rebaseableMethods;
        return (iHashCode * 59) + (map == null ? 43 : map.hashCode());
    }

    protected RebaseImplementationTarget(TypeDescription typeDescription, MethodGraph.Linked linked, Implementation.Target.AbstractBase.DefaultMethodInvocation defaultMethodInvocation, Map<MethodDescription.SignatureToken, MethodRebaseResolver.Resolution> map) {
        super(typeDescription, linked, defaultMethodInvocation);
        this.rebaseableMethods = map;
    }

    protected static Implementation.Target of(TypeDescription typeDescription, MethodGraph.Linked linked, ClassFileVersion classFileVersion, MethodRebaseResolver methodRebaseResolver) {
        return new RebaseImplementationTarget(typeDescription, linked, Implementation.Target.AbstractBase.DefaultMethodInvocation.of(classFileVersion), methodRebaseResolver.asTokenMap());
    }

    @Override // net.bytebuddy.implementation.Implementation.Target
    public Implementation.SpecialMethodInvocation invokeSuper(MethodDescription.SignatureToken signatureToken) {
        MethodRebaseResolver.Resolution resolution = this.rebaseableMethods.get(signatureToken);
        if (resolution == null) {
            return invokeSuper(this.methodGraph.getSuperClassGraph().locate(signatureToken));
        }
        return invokeSuper(resolution);
    }

    private Implementation.SpecialMethodInvocation invokeSuper(MethodGraph.Node node) {
        return node.getSort().isResolved() ? Implementation.SpecialMethodInvocation.Simple.of(node.getRepresentative(), this.instrumentedType.getSuperClass().asErasure()) : Implementation.SpecialMethodInvocation.Illegal.INSTANCE;
    }

    private Implementation.SpecialMethodInvocation invokeSuper(MethodRebaseResolver.Resolution resolution) {
        if (resolution.isRebased()) {
            return RebasedMethodInvocation.of(resolution.getResolvedMethod(), this.instrumentedType, resolution.getAdditionalArguments());
        }
        return Implementation.SpecialMethodInvocation.Simple.of(resolution.getResolvedMethod(), this.instrumentedType);
    }

    @Override // net.bytebuddy.implementation.Implementation.Target
    public TypeDescription getOriginType() {
        return this.instrumentedType;
    }

    protected static class RebasedMethodInvocation extends Implementation.SpecialMethodInvocation.AbstractBase {
        private final TypeDescription instrumentedType;
        private final MethodDescription methodDescription;
        private final StackManipulation stackManipulation;

        protected RebasedMethodInvocation(MethodDescription methodDescription, TypeDescription typeDescription, StackManipulation stackManipulation) {
            this.methodDescription = methodDescription;
            this.instrumentedType = typeDescription;
            this.stackManipulation = stackManipulation;
        }

        protected static Implementation.SpecialMethodInvocation of(MethodDescription methodDescription, TypeDescription typeDescription, StackManipulation stackManipulation) {
            StackManipulation stackManipulationSpecial;
            if (methodDescription.isStatic()) {
                stackManipulationSpecial = MethodInvocation.invoke(methodDescription);
            } else {
                stackManipulationSpecial = MethodInvocation.invoke(methodDescription).special(typeDescription);
            }
            return stackManipulationSpecial.isValid() ? new RebasedMethodInvocation(methodDescription, typeDescription, new StackManipulation.Compound(stackManipulation, stackManipulationSpecial)) : Implementation.SpecialMethodInvocation.Illegal.INSTANCE;
        }

        @Override // net.bytebuddy.implementation.Implementation.SpecialMethodInvocation
        public MethodDescription getMethodDescription() {
            return this.methodDescription;
        }

        @Override // net.bytebuddy.implementation.Implementation.SpecialMethodInvocation
        public TypeDescription getTypeDescription() {
            return this.instrumentedType;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return this.stackManipulation.apply(methodVisitor, context);
        }
    }

    public static class Factory implements Implementation.Target.Factory {
        private final MethodRebaseResolver methodRebaseResolver;

        protected boolean canEqual(Object obj) {
            return obj instanceof Factory;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Factory)) {
                return false;
            }
            Factory factory = (Factory) obj;
            if (!factory.canEqual(this)) {
                return false;
            }
            MethodRebaseResolver methodRebaseResolver = this.methodRebaseResolver;
            MethodRebaseResolver methodRebaseResolver2 = factory.methodRebaseResolver;
            return methodRebaseResolver != null ? methodRebaseResolver.equals(methodRebaseResolver2) : methodRebaseResolver2 == null;
        }

        public int hashCode() {
            MethodRebaseResolver methodRebaseResolver = this.methodRebaseResolver;
            return 59 + (methodRebaseResolver == null ? 43 : methodRebaseResolver.hashCode());
        }

        public Factory(MethodRebaseResolver methodRebaseResolver) {
            this.methodRebaseResolver = methodRebaseResolver;
        }

        @Override // net.bytebuddy.implementation.Implementation.Target.Factory
        public Implementation.Target make(TypeDescription typeDescription, MethodGraph.Linked linked, ClassFileVersion classFileVersion) {
            return RebaseImplementationTarget.of(typeDescription, linked, classFileVersion, this.methodRebaseResolver);
        }
    }
}
