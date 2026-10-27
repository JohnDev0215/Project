package net.bytebuddy.implementation.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.utility.CompoundList;

/* JADX INFO: loaded from: classes3.dex */
@Target({ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AllArguments {
    boolean includeSelf() default false;

    Assignment value() default Assignment.STRICT;

    public enum Assignment {
        STRICT(true),
        SLACK(false);

        private final boolean strict;

        Assignment(boolean z) {
            this.strict = z;
        }

        protected boolean isStrict() {
            return this.strict;
        }
    }

    public enum Binder implements TargetMethodAnnotationDrivenBinder.ParameterBinder<AllArguments> {
        INSTANCE;

        @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder
        public Class<AllArguments> getHandledType() {
            return AllArguments.class;
        }

        @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder
        public MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<AllArguments> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing) {
            TypeDescription.Generic componentType;
            List<TypeDescription.Generic> listAsTypeList;
            if (parameterDescription.getType().represents(Object.class)) {
                componentType = TypeDescription.Generic.OBJECT;
            } else if (parameterDescription.getType().isArray()) {
                componentType = parameterDescription.getType().getComponentType();
            } else {
                throw new IllegalStateException("Expected an array type for all argument annotation on " + methodDescription);
            }
            int i = (methodDescription.isStatic() || !((AllArguments) loadable.loadSilent()).includeSelf()) ? 0 : 1;
            ArrayList arrayList = new ArrayList(methodDescription.getParameters().size() + i);
            int size = (methodDescription.isStatic() || i != 0) ? 0 : 1;
            if (i != 0) {
                listAsTypeList = CompoundList.of(target.getInstrumentedType().asGenericType(), methodDescription.getParameters().asTypeList());
            } else {
                listAsTypeList = methodDescription.getParameters().asTypeList();
            }
            for (TypeDescription.Generic generic : listAsTypeList) {
                StackManipulation.Compound compound = new StackManipulation.Compound(MethodVariableAccess.of(generic).loadFrom(size), assigner.assign(generic, componentType, typing));
                if (compound.isValid()) {
                    arrayList.add(compound);
                } else if (((AllArguments) loadable.loadSilent()).value().isStrict()) {
                    return MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
                }
                size += generic.getStackSize().getSize();
            }
            return new MethodDelegationBinder.ParameterBinding.Anonymous(ArrayFactory.forType(componentType).withValues(arrayList));
        }
    }
}
