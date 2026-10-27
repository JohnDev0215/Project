package net.bytebuddy.implementation.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.Callable;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.auxiliary.MethodCallProxy;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.constant.NullConstant;

/* JADX INFO: loaded from: classes3.dex */
@Target({ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface SuperCall {
    boolean fallbackToDefault() default true;

    boolean nullIfImpossible() default false;

    boolean serializableProxy() default false;

    public enum Binder implements TargetMethodAnnotationDrivenBinder.ParameterBinder<SuperCall> {
        INSTANCE;

        @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder
        public Class<SuperCall> getHandledType() {
            return SuperCall.class;
        }

        @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder
        public MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<SuperCall> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing) {
            Implementation.SpecialMethodInvocation specialMethodInvocationInvokeSuper;
            StackManipulation assignableSignatureCall;
            TypeDescription typeDescriptionAsErasure = parameterDescription.getType().asErasure();
            if (!typeDescriptionAsErasure.represents(Runnable.class) && !typeDescriptionAsErasure.represents(Callable.class) && !typeDescriptionAsErasure.represents(Object.class)) {
                throw new IllegalStateException("A super method call proxy can only be assigned to Runnable or Callable types: " + parameterDescription);
            }
            if (methodDescription.isConstructor()) {
                return ((SuperCall) loadable.loadSilent()).nullIfImpossible() ? new MethodDelegationBinder.ParameterBinding.Anonymous(NullConstant.INSTANCE) : MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
            }
            if (((SuperCall) loadable.loadSilent()).fallbackToDefault()) {
                specialMethodInvocationInvokeSuper = target.invokeDominant(methodDescription.asSignatureToken());
            } else {
                specialMethodInvocationInvokeSuper = target.invokeSuper(methodDescription.asSignatureToken());
            }
            if (specialMethodInvocationInvokeSuper.isValid()) {
                assignableSignatureCall = new MethodCallProxy.AssignableSignatureCall(specialMethodInvocationInvokeSuper, ((SuperCall) loadable.loadSilent()).serializableProxy());
            } else if (((SuperCall) loadable.loadSilent()).nullIfImpossible()) {
                assignableSignatureCall = NullConstant.INSTANCE;
            } else {
                return MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
            }
            return new MethodDelegationBinder.ParameterBinding.Anonymous(assignableSignatureCall);
        }
    }
}
