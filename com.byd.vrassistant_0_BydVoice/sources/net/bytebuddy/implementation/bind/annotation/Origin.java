package net.bytebuddy.implementation.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.constant.ClassConstant;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.constant.MethodConstant;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.utility.JavaConstant;
import net.bytebuddy.utility.JavaType;

/* JADX INFO: loaded from: classes3.dex */
@Target({ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Origin {
    boolean cache() default true;

    public enum Binder implements TargetMethodAnnotationDrivenBinder.ParameterBinder<Origin> {
        INSTANCE;

        @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder
        public Class<Origin> getHandledType() {
            return Origin.class;
        }

        @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder
        public MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<Origin> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing) {
            StackManipulation stackManipulationForMethod;
            StackManipulation stackManipulationForMethod2;
            StackManipulation stackManipulationForMethod3;
            TypeDescription typeDescriptionAsErasure = parameterDescription.getType().asErasure();
            if (typeDescriptionAsErasure.represents(Class.class)) {
                return new MethodDelegationBinder.ParameterBinding.Anonymous(ClassConstant.of(target.getOriginType().asErasure()));
            }
            if (typeDescriptionAsErasure.represents(Method.class)) {
                if (!methodDescription.isMethod()) {
                    return MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
                }
                if (((Origin) loadable.loadSilent()).cache()) {
                    stackManipulationForMethod3 = MethodConstant.forMethod(methodDescription.asDefined()).cached();
                } else {
                    stackManipulationForMethod3 = MethodConstant.forMethod(methodDescription.asDefined());
                }
                return new MethodDelegationBinder.ParameterBinding.Anonymous(stackManipulationForMethod3);
            }
            if (typeDescriptionAsErasure.represents(Constructor.class)) {
                if (!methodDescription.isConstructor()) {
                    return MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
                }
                if (((Origin) loadable.loadSilent()).cache()) {
                    stackManipulationForMethod2 = MethodConstant.forMethod(methodDescription.asDefined()).cached();
                } else {
                    stackManipulationForMethod2 = MethodConstant.forMethod(methodDescription.asDefined());
                }
                return new MethodDelegationBinder.ParameterBinding.Anonymous(stackManipulationForMethod2);
            }
            if (JavaType.EXECUTABLE.getTypeStub().equals(typeDescriptionAsErasure)) {
                if (((Origin) loadable.loadSilent()).cache()) {
                    stackManipulationForMethod = MethodConstant.forMethod(methodDescription.asDefined()).cached();
                } else {
                    stackManipulationForMethod = MethodConstant.forMethod(methodDescription.asDefined());
                }
                return new MethodDelegationBinder.ParameterBinding.Anonymous(stackManipulationForMethod);
            }
            if (typeDescriptionAsErasure.represents(String.class)) {
                return new MethodDelegationBinder.ParameterBinding.Anonymous(new TextConstant(methodDescription.toString()));
            }
            if (typeDescriptionAsErasure.represents(Integer.TYPE)) {
                return new MethodDelegationBinder.ParameterBinding.Anonymous(IntegerConstant.forValue(methodDescription.getModifiers()));
            }
            if (typeDescriptionAsErasure.equals(JavaType.METHOD_HANDLE.getTypeStub())) {
                return new MethodDelegationBinder.ParameterBinding.Anonymous(JavaConstant.MethodHandle.of(methodDescription.asDefined()).asStackManipulation());
            }
            if (typeDescriptionAsErasure.equals(JavaType.METHOD_TYPE.getTypeStub())) {
                return new MethodDelegationBinder.ParameterBinding.Anonymous(JavaConstant.MethodType.of(methodDescription.asDefined()).asStackManipulation());
            }
            throw new IllegalStateException("The " + parameterDescription + " method's " + parameterDescription.getIndex() + " parameter is annotated with a Origin annotation with an argument not representing a Class, Method, Constructor, String, int, MethodType or MethodHandle type");
        }
    }
}
