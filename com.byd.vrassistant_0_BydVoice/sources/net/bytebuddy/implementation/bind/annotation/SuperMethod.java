package net.bytebuddy.implementation.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodAccessorFactory;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.constant.MethodConstant;
import net.bytebuddy.implementation.bytecode.constant.NullConstant;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
@Target({ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface SuperMethod {
    boolean cached() default true;

    boolean fallbackToDefault() default true;

    boolean nullIfImpossible() default false;

    public enum Binder implements TargetMethodAnnotationDrivenBinder.ParameterBinder<SuperMethod> {
        INSTANCE;

        @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder
        public Class<SuperMethod> getHandledType() {
            return SuperMethod.class;
        }

        @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder
        public MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<SuperMethod> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing) {
            Implementation.SpecialMethodInvocation specialMethodInvocationInvokeSuper;
            if (!parameterDescription.getType().asErasure().isAssignableFrom(Method.class)) {
                throw new IllegalStateException("Cannot assign Method type to " + parameterDescription);
            }
            if (methodDescription.isMethod()) {
                if (((SuperMethod) loadable.loadSilent()).fallbackToDefault()) {
                    specialMethodInvocationInvokeSuper = target.invokeDominant(methodDescription.asSignatureToken());
                } else {
                    specialMethodInvocationInvokeSuper = target.invokeSuper(methodDescription.asSignatureToken());
                }
                if (specialMethodInvocationInvokeSuper.isValid()) {
                    return new MethodDelegationBinder.ParameterBinding.Anonymous(new DelegationMethod(specialMethodInvocationInvokeSuper, ((SuperMethod) loadable.loadSilent()).cached()));
                }
                if (((SuperMethod) loadable.loadSilent()).nullIfImpossible()) {
                    return new MethodDelegationBinder.ParameterBinding.Anonymous(NullConstant.INSTANCE);
                }
                return MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
            }
            if (((SuperMethod) loadable.loadSilent()).nullIfImpossible()) {
                return new MethodDelegationBinder.ParameterBinding.Anonymous(NullConstant.INSTANCE);
            }
            return MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
        }

        protected static class DelegationMethod implements StackManipulation {
            private final boolean cached;
            private final Implementation.SpecialMethodInvocation specialMethodInvocation;

            protected boolean canEqual(Object obj) {
                return obj instanceof DelegationMethod;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof DelegationMethod)) {
                    return false;
                }
                DelegationMethod delegationMethod = (DelegationMethod) obj;
                if (!delegationMethod.canEqual(this)) {
                    return false;
                }
                Implementation.SpecialMethodInvocation specialMethodInvocation = this.specialMethodInvocation;
                Implementation.SpecialMethodInvocation specialMethodInvocation2 = delegationMethod.specialMethodInvocation;
                if (specialMethodInvocation != null ? specialMethodInvocation.equals(specialMethodInvocation2) : specialMethodInvocation2 == null) {
                    return this.cached == delegationMethod.cached;
                }
                return false;
            }

            public int hashCode() {
                Implementation.SpecialMethodInvocation specialMethodInvocation = this.specialMethodInvocation;
                return (((specialMethodInvocation == null ? 43 : specialMethodInvocation.hashCode()) + 59) * 59) + (this.cached ? 79 : 97);
            }

            protected DelegationMethod(Implementation.SpecialMethodInvocation specialMethodInvocation, boolean z) {
                this.specialMethodInvocation = specialMethodInvocation;
                this.cached = z;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return this.specialMethodInvocation.isValid();
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                StackManipulation stackManipulationForMethod = MethodConstant.forMethod(context.registerAccessorFor(this.specialMethodInvocation, MethodAccessorFactory.AccessType.PUBLIC));
                if (this.cached) {
                    stackManipulationForMethod = FieldAccess.forField(context.cache(stackManipulationForMethod, new TypeDescription.ForLoadedType(Method.class))).read();
                }
                return stackManipulationForMethod.apply(methodVisitor, context);
            }
        }
    }
}
