package net.bytebuddy.implementation.bind.annotation;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.FieldLocator;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Argument;
import net.bytebuddy.implementation.bind.annotation.Default;
import net.bytebuddy.implementation.bind.annotation.DefaultCall;
import net.bytebuddy.implementation.bind.annotation.DefaultMethod;
import net.bytebuddy.implementation.bind.annotation.Empty;
import net.bytebuddy.implementation.bind.annotation.FieldValue;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.StubValue;
import net.bytebuddy.implementation.bind.annotation.Super;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.implementation.bind.annotation.SuperMethod;
import net.bytebuddy.implementation.bind.annotation.This;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.constant.ClassConstant;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import net.bytebuddy.implementation.bytecode.constant.DoubleConstant;
import net.bytebuddy.implementation.bytecode.constant.FloatConstant;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.constant.JavaConstantValue;
import net.bytebuddy.implementation.bytecode.constant.LongConstant;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaConstant;
import net.bytebuddy.utility.JavaType;

/* JADX INFO: loaded from: classes3.dex */
public class TargetMethodAnnotationDrivenBinder implements MethodDelegationBinder {
    private final DelegationProcessor delegationProcessor;

    protected boolean canEqual(Object obj) {
        return obj instanceof TargetMethodAnnotationDrivenBinder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TargetMethodAnnotationDrivenBinder)) {
            return false;
        }
        TargetMethodAnnotationDrivenBinder targetMethodAnnotationDrivenBinder = (TargetMethodAnnotationDrivenBinder) obj;
        if (!targetMethodAnnotationDrivenBinder.canEqual(this)) {
            return false;
        }
        DelegationProcessor delegationProcessor = this.delegationProcessor;
        DelegationProcessor delegationProcessor2 = targetMethodAnnotationDrivenBinder.delegationProcessor;
        return delegationProcessor != null ? delegationProcessor.equals(delegationProcessor2) : delegationProcessor2 == null;
    }

    public int hashCode() {
        DelegationProcessor delegationProcessor = this.delegationProcessor;
        return 59 + (delegationProcessor == null ? 43 : delegationProcessor.hashCode());
    }

    protected TargetMethodAnnotationDrivenBinder(DelegationProcessor delegationProcessor) {
        this.delegationProcessor = delegationProcessor;
    }

    public static MethodDelegationBinder of(List<? extends ParameterBinder<?>> list) {
        return new TargetMethodAnnotationDrivenBinder(DelegationProcessor.of(list));
    }

    @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder
    public MethodDelegationBinder.Record compile(MethodDescription methodDescription) {
        if (IgnoreForBinding.Verifier.check(methodDescription)) {
            return MethodDelegationBinder.Record.Illegal.INSTANCE;
        }
        ArrayList arrayList = new ArrayList(methodDescription.getParameters().size());
        Iterator it = methodDescription.getParameters().iterator();
        while (it.hasNext()) {
            arrayList.add(this.delegationProcessor.prepare((ParameterDescription) it.next()));
        }
        return new Record(methodDescription, arrayList, RuntimeType.Verifier.check(methodDescription));
    }

    protected static class Record implements MethodDelegationBinder.Record {
        private final MethodDescription candidate;
        private final List<DelegationProcessor.Handler> handlers;
        private final Assigner.Typing typing;

        protected boolean canEqual(Object obj) {
            return obj instanceof Record;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Record)) {
                return false;
            }
            Record record = (Record) obj;
            if (!record.canEqual(this)) {
                return false;
            }
            MethodDescription methodDescription = this.candidate;
            MethodDescription methodDescription2 = record.candidate;
            if (methodDescription != null ? !methodDescription.equals(methodDescription2) : methodDescription2 != null) {
                return false;
            }
            List<DelegationProcessor.Handler> list = this.handlers;
            List<DelegationProcessor.Handler> list2 = record.handlers;
            if (list != null ? !list.equals(list2) : list2 != null) {
                return false;
            }
            Assigner.Typing typing = this.typing;
            Assigner.Typing typing2 = record.typing;
            return typing != null ? typing.equals(typing2) : typing2 == null;
        }

        public int hashCode() {
            MethodDescription methodDescription = this.candidate;
            int iHashCode = methodDescription == null ? 43 : methodDescription.hashCode();
            List<DelegationProcessor.Handler> list = this.handlers;
            int iHashCode2 = ((iHashCode + 59) * 59) + (list == null ? 43 : list.hashCode());
            Assigner.Typing typing = this.typing;
            return (iHashCode2 * 59) + (typing != null ? typing.hashCode() : 43);
        }

        protected Record(MethodDescription methodDescription, List<DelegationProcessor.Handler> list, Assigner.Typing typing) {
            this.candidate = methodDescription;
            this.handlers = list;
            this.typing = typing;
        }

        @Override // net.bytebuddy.implementation.bind.MethodDelegationBinder.Record
        public MethodDelegationBinder.MethodBinding bind(Implementation.Target target, MethodDescription methodDescription, MethodDelegationBinder.TerminationHandler terminationHandler, MethodDelegationBinder.MethodInvoker methodInvoker, Assigner assigner) {
            if (!this.candidate.isAccessibleTo(target.getInstrumentedType())) {
                return MethodDelegationBinder.MethodBinding.Illegal.INSTANCE;
            }
            StackManipulation stackManipulationResolve = terminationHandler.resolve(assigner, this.typing, methodDescription, this.candidate);
            if (!stackManipulationResolve.isValid()) {
                return MethodDelegationBinder.MethodBinding.Illegal.INSTANCE;
            }
            MethodDelegationBinder.MethodBinding.Builder builder = new MethodDelegationBinder.MethodBinding.Builder(methodInvoker, this.candidate);
            Iterator<DelegationProcessor.Handler> it = this.handlers.iterator();
            while (it.hasNext()) {
                MethodDelegationBinder.ParameterBinding<?> parameterBindingBind = it.next().bind(methodDescription, target, assigner);
                if (!parameterBindingBind.isValid() || !builder.append(parameterBindingBind)) {
                    return MethodDelegationBinder.MethodBinding.Illegal.INSTANCE;
                }
            }
            return builder.build(stackManipulationResolve);
        }

        public String toString() {
            return this.candidate.toString();
        }
    }

    public interface ParameterBinder<T extends Annotation> {
        public static final List<ParameterBinder<?>> DEFAULTS = Collections.unmodifiableList(Arrays.asList(Argument.Binder.INSTANCE, AllArguments.Binder.INSTANCE, Origin.Binder.INSTANCE, This.Binder.INSTANCE, Super.Binder.INSTANCE, Default.Binder.INSTANCE, SuperCall.Binder.INSTANCE, DefaultCall.Binder.INSTANCE, SuperMethod.Binder.INSTANCE, DefaultMethod.Binder.INSTANCE, FieldValue.Binder.INSTANCE, StubValue.Binder.INSTANCE, Empty.Binder.INSTANCE));

        MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<T> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing);

        Class<T> getHandledType();

        public static abstract class ForFixedValue<S extends Annotation> implements ParameterBinder<S> {
            protected abstract Object bind(AnnotationDescription.Loadable<S> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription);

            @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder
            public MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<S> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing) {
                StackManipulation javaConstantValue;
                TypeDescription typeStub;
                StackManipulation stackManipulationAsStackManipulation;
                TypeDescription typeStub2;
                Object objBind = bind(loadable, methodDescription, parameterDescription);
                if (objBind == null) {
                    return new MethodDelegationBinder.ParameterBinding.Anonymous(DefaultValue.of(parameterDescription.getType()));
                }
                if (objBind instanceof Boolean) {
                    stackManipulationAsStackManipulation = IntegerConstant.forValue(((Boolean) objBind).booleanValue());
                    typeStub2 = new TypeDescription.ForLoadedType(Boolean.TYPE);
                } else if (objBind instanceof Byte) {
                    stackManipulationAsStackManipulation = IntegerConstant.forValue(((Byte) objBind).byteValue());
                    typeStub2 = new TypeDescription.ForLoadedType(Byte.TYPE);
                } else if (objBind instanceof Short) {
                    stackManipulationAsStackManipulation = IntegerConstant.forValue(((Short) objBind).shortValue());
                    typeStub2 = new TypeDescription.ForLoadedType(Short.TYPE);
                } else if (objBind instanceof Character) {
                    stackManipulationAsStackManipulation = IntegerConstant.forValue(((Character) objBind).charValue());
                    typeStub2 = new TypeDescription.ForLoadedType(Character.TYPE);
                } else if (objBind instanceof Integer) {
                    stackManipulationAsStackManipulation = IntegerConstant.forValue(((Integer) objBind).intValue());
                    typeStub2 = new TypeDescription.ForLoadedType(Integer.TYPE);
                } else if (objBind instanceof Long) {
                    stackManipulationAsStackManipulation = LongConstant.forValue(((Long) objBind).longValue());
                    typeStub2 = new TypeDescription.ForLoadedType(Long.TYPE);
                } else if (objBind instanceof Float) {
                    stackManipulationAsStackManipulation = FloatConstant.forValue(((Float) objBind).floatValue());
                    typeStub2 = new TypeDescription.ForLoadedType(Float.TYPE);
                } else if (objBind instanceof Double) {
                    stackManipulationAsStackManipulation = DoubleConstant.forValue(((Double) objBind).doubleValue());
                    typeStub2 = new TypeDescription.ForLoadedType(Double.TYPE);
                } else {
                    if (objBind instanceof String) {
                        javaConstantValue = new TextConstant((String) objBind);
                        typeStub = TypeDescription.STRING;
                    } else if (objBind instanceof Class) {
                        stackManipulationAsStackManipulation = ClassConstant.of(new TypeDescription.ForLoadedType((Class) objBind));
                        typeStub2 = TypeDescription.CLASS;
                    } else if (objBind instanceof TypeDescription) {
                        stackManipulationAsStackManipulation = ClassConstant.of((TypeDescription) objBind);
                        typeStub2 = TypeDescription.CLASS;
                    } else if (JavaType.METHOD_HANDLE.getTypeStub().isInstance(objBind)) {
                        stackManipulationAsStackManipulation = JavaConstant.MethodHandle.ofLoaded(objBind).asStackManipulation();
                        typeStub2 = JavaType.METHOD_HANDLE.getTypeStub();
                    } else if (objBind instanceof JavaConstant.MethodHandle) {
                        javaConstantValue = new JavaConstantValue((JavaConstant.MethodHandle) objBind);
                        typeStub = JavaType.METHOD_HANDLE.getTypeStub();
                    } else if (JavaType.METHOD_TYPE.getTypeStub().isInstance(objBind)) {
                        javaConstantValue = new JavaConstantValue(JavaConstant.MethodType.ofLoaded(objBind));
                        typeStub = JavaType.METHOD_HANDLE.getTypeStub();
                    } else if (objBind instanceof JavaConstant.MethodType) {
                        javaConstantValue = new JavaConstantValue((JavaConstant.MethodType) objBind);
                        typeStub = JavaType.METHOD_HANDLE.getTypeStub();
                    } else {
                        throw new IllegalStateException("Not able to save in class's constant pool: " + objBind);
                    }
                    StackManipulation stackManipulation = javaConstantValue;
                    typeStub2 = typeStub;
                    stackManipulationAsStackManipulation = stackManipulation;
                }
                return new MethodDelegationBinder.ParameterBinding.Anonymous(new StackManipulation.Compound(stackManipulationAsStackManipulation, assigner.assign(typeStub2.asGenericType(), parameterDescription.getType(), typing)));
            }

            public static class OfConstant<U extends Annotation> extends ForFixedValue<U> {
                private final Class<U> type;
                private final Object value;

                protected boolean canEqual(Object obj) {
                    return obj instanceof OfConstant;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof OfConstant)) {
                        return false;
                    }
                    OfConstant ofConstant = (OfConstant) obj;
                    if (!ofConstant.canEqual(this)) {
                        return false;
                    }
                    Class<U> cls = this.type;
                    Class<U> cls2 = ofConstant.type;
                    if (cls != null ? !cls.equals(cls2) : cls2 != null) {
                        return false;
                    }
                    Object obj2 = this.value;
                    Object obj3 = ofConstant.value;
                    return obj2 != null ? obj2.equals(obj3) : obj3 == null;
                }

                public int hashCode() {
                    Class<U> cls = this.type;
                    int iHashCode = cls == null ? 43 : cls.hashCode();
                    Object obj = this.value;
                    return ((iHashCode + 59) * 59) + (obj != null ? obj.hashCode() : 43);
                }

                protected OfConstant(Class<U> cls, Object obj) {
                    this.type = cls;
                    this.value = obj;
                }

                public static <V extends Annotation> ParameterBinder<V> of(Class<V> cls, Object obj) {
                    return new OfConstant(cls, obj);
                }

                @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder
                public Class<U> getHandledType() {
                    return this.type;
                }

                @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder.ForFixedValue
                protected Object bind(AnnotationDescription.Loadable<U> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription) {
                    return this.value;
                }
            }
        }

        public static abstract class ForFieldBinding<S extends Annotation> implements ParameterBinder<S> {
            protected static final String BEAN_PROPERTY = "";

            protected abstract MethodDelegationBinder.ParameterBinding<?> bind(FieldDescription fieldDescription, AnnotationDescription.Loadable<S> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner);

            protected abstract TypeDescription declaringType(AnnotationDescription.Loadable<S> loadable);

            protected abstract String fieldName(AnnotationDescription.Loadable<S> loadable);

            private static FieldLocator.Resolution resolveAccessor(FieldLocator fieldLocator, MethodDescription methodDescription) {
                String strSubstring;
                if (ElementMatchers.isSetter().matches(methodDescription)) {
                    strSubstring = methodDescription.getInternalName().substring(3);
                } else if (ElementMatchers.isGetter().matches(methodDescription)) {
                    strSubstring = methodDescription.getInternalName().substring(methodDescription.getInternalName().startsWith("is") ? 2 : 3);
                } else {
                    return FieldLocator.Resolution.Illegal.INSTANCE;
                }
                return fieldLocator.locate(Character.toLowerCase(strSubstring.charAt(0)) + strSubstring.substring(1));
            }

            @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder
            public MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<S> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing) {
                FieldLocator forExactType;
                FieldLocator.Resolution resolutionLocate;
                if (!declaringType(loadable).represents(Void.TYPE)) {
                    if (declaringType(loadable).isPrimitive() || declaringType(loadable).isArray()) {
                        throw new IllegalStateException("A primitive type or array type cannot declare a field: " + methodDescription);
                    }
                    if (!target.getInstrumentedType().isAssignableTo(declaringType(loadable))) {
                        return MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
                    }
                }
                if (declaringType(loadable).represents(Void.TYPE)) {
                    forExactType = new FieldLocator.ForClassHierarchy(target.getInstrumentedType());
                } else {
                    forExactType = new FieldLocator.ForExactType(declaringType(loadable), target.getInstrumentedType());
                }
                if (fieldName(loadable).equals("")) {
                    resolutionLocate = resolveAccessor(forExactType, methodDescription);
                } else {
                    resolutionLocate = forExactType.locate(fieldName(loadable));
                }
                return (!resolutionLocate.isResolved() || (methodDescription.isStatic() && !resolutionLocate.getField().isStatic())) ? MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE : bind(resolutionLocate.getField(), loadable, methodDescription, parameterDescription, target, assigner);
            }
        }
    }

    protected static class DelegationProcessor {
        private final Map<? extends TypeDescription, ? extends ParameterBinder<?>> parameterBinders;

        protected boolean canEqual(Object obj) {
            return obj instanceof DelegationProcessor;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof DelegationProcessor)) {
                return false;
            }
            DelegationProcessor delegationProcessor = (DelegationProcessor) obj;
            if (!delegationProcessor.canEqual(this)) {
                return false;
            }
            Map<? extends TypeDescription, ? extends ParameterBinder<?>> map = this.parameterBinders;
            Map<? extends TypeDescription, ? extends ParameterBinder<?>> map2 = delegationProcessor.parameterBinders;
            return map != null ? map.equals(map2) : map2 == null;
        }

        public int hashCode() {
            Map<? extends TypeDescription, ? extends ParameterBinder<?>> map = this.parameterBinders;
            return 59 + (map == null ? 43 : map.hashCode());
        }

        protected DelegationProcessor(Map<? extends TypeDescription, ? extends ParameterBinder<?>> map) {
            this.parameterBinders = map;
        }

        protected static DelegationProcessor of(List<? extends ParameterBinder<?>> list) {
            HashMap map = new HashMap();
            for (ParameterBinder<?> parameterBinder : list) {
                if (map.put(new TypeDescription.ForLoadedType(parameterBinder.getHandledType()), parameterBinder) != null) {
                    throw new IllegalArgumentException("Attempt to bind two handlers to " + parameterBinder.getHandledType());
                }
            }
            return new DelegationProcessor(map);
        }

        protected Handler prepare(ParameterDescription parameterDescription) {
            Assigner.Typing typingCheck = RuntimeType.Verifier.check(parameterDescription);
            Handler unbound = new Handler.Unbound(parameterDescription, typingCheck);
            for (AnnotationDescription annotationDescription : parameterDescription.getDeclaredAnnotations()) {
                ParameterBinder<?> parameterBinder = this.parameterBinders.get(annotationDescription.getAnnotationType());
                if (parameterBinder != null && unbound.isBound()) {
                    throw new IllegalStateException("Ambiguous binding for parameter annotated with two handled annotation types");
                }
                if (parameterBinder != null) {
                    unbound = Handler.Bound.of(parameterDescription, parameterBinder, annotationDescription, typingCheck);
                }
            }
            return unbound;
        }

        protected interface Handler {
            MethodDelegationBinder.ParameterBinding<?> bind(MethodDescription methodDescription, Implementation.Target target, Assigner assigner);

            boolean isBound();

            public static class Unbound implements Handler {
                private final ParameterDescription target;
                private final Assigner.Typing typing;

                @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.DelegationProcessor.Handler
                public boolean isBound() {
                    return false;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof Unbound;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Unbound)) {
                        return false;
                    }
                    Unbound unbound = (Unbound) obj;
                    if (!unbound.canEqual(this)) {
                        return false;
                    }
                    ParameterDescription parameterDescription = this.target;
                    ParameterDescription parameterDescription2 = unbound.target;
                    if (parameterDescription != null ? !parameterDescription.equals(parameterDescription2) : parameterDescription2 != null) {
                        return false;
                    }
                    Assigner.Typing typing = this.typing;
                    Assigner.Typing typing2 = unbound.typing;
                    return typing != null ? typing.equals(typing2) : typing2 == null;
                }

                public int hashCode() {
                    ParameterDescription parameterDescription = this.target;
                    int iHashCode = parameterDescription == null ? 43 : parameterDescription.hashCode();
                    Assigner.Typing typing = this.typing;
                    return ((iHashCode + 59) * 59) + (typing != null ? typing.hashCode() : 43);
                }

                protected Unbound(ParameterDescription parameterDescription, Assigner.Typing typing) {
                    this.target = parameterDescription;
                    this.typing = typing;
                }

                @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.DelegationProcessor.Handler
                public MethodDelegationBinder.ParameterBinding<?> bind(MethodDescription methodDescription, Implementation.Target target, Assigner assigner) {
                    return Argument.Binder.INSTANCE.bind(AnnotationDescription.ForLoadedAnnotation.of(new DefaultArgument(this.target.getIndex())), methodDescription, this.target, target, assigner, this.typing);
                }

                protected static class DefaultArgument implements Argument {
                    private static final String BINDING_MECHANIC = "bindingMechanic";
                    private static final String VALUE = "value";
                    private final int parameterIndex;

                    protected DefaultArgument(int i) {
                        this.parameterIndex = i;
                    }

                    @Override // net.bytebuddy.implementation.bind.annotation.Argument
                    public int value() {
                        return this.parameterIndex;
                    }

                    @Override // net.bytebuddy.implementation.bind.annotation.Argument
                    public Argument.BindingMechanic bindingMechanic() {
                        return Argument.BindingMechanic.UNIQUE;
                    }

                    @Override // java.lang.annotation.Annotation
                    public Class<Argument> annotationType() {
                        return Argument.class;
                    }

                    @Override // java.lang.annotation.Annotation
                    public boolean equals(Object obj) {
                        return this == obj || ((obj instanceof Argument) && this.parameterIndex == ((Argument) obj).value());
                    }

                    @Override // java.lang.annotation.Annotation
                    public int hashCode() {
                        return (Argument.BindingMechanic.UNIQUE.hashCode() ^ 1957906263) + (1335633679 ^ this.parameterIndex);
                    }

                    @Override // java.lang.annotation.Annotation
                    public String toString() {
                        return "@" + Argument.class.getName() + "(bindingMechanic=" + Argument.BindingMechanic.UNIQUE.toString() + ", value=" + this.parameterIndex + ")";
                    }
                }
            }

            public static class Bound<T extends Annotation> implements Handler {
                private final AnnotationDescription.Loadable<T> annotation;
                private final ParameterBinder<T> parameterBinder;
                private final ParameterDescription target;
                private final Assigner.Typing typing;

                @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.DelegationProcessor.Handler
                public boolean isBound() {
                    return true;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof Bound;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Bound)) {
                        return false;
                    }
                    Bound bound = (Bound) obj;
                    if (!bound.canEqual(this)) {
                        return false;
                    }
                    ParameterDescription parameterDescription = this.target;
                    ParameterDescription parameterDescription2 = bound.target;
                    if (parameterDescription != null ? !parameterDescription.equals(parameterDescription2) : parameterDescription2 != null) {
                        return false;
                    }
                    ParameterBinder<T> parameterBinder = this.parameterBinder;
                    ParameterBinder<T> parameterBinder2 = bound.parameterBinder;
                    if (parameterBinder != null ? !parameterBinder.equals(parameterBinder2) : parameterBinder2 != null) {
                        return false;
                    }
                    AnnotationDescription.Loadable<T> loadable = this.annotation;
                    AnnotationDescription.Loadable<T> loadable2 = bound.annotation;
                    if (loadable != null ? !loadable.equals(loadable2) : loadable2 != null) {
                        return false;
                    }
                    Assigner.Typing typing = this.typing;
                    Assigner.Typing typing2 = bound.typing;
                    return typing != null ? typing.equals(typing2) : typing2 == null;
                }

                public int hashCode() {
                    ParameterDescription parameterDescription = this.target;
                    int iHashCode = parameterDescription == null ? 43 : parameterDescription.hashCode();
                    ParameterBinder<T> parameterBinder = this.parameterBinder;
                    int iHashCode2 = ((iHashCode + 59) * 59) + (parameterBinder == null ? 43 : parameterBinder.hashCode());
                    AnnotationDescription.Loadable<T> loadable = this.annotation;
                    int iHashCode3 = (iHashCode2 * 59) + (loadable == null ? 43 : loadable.hashCode());
                    Assigner.Typing typing = this.typing;
                    return (iHashCode3 * 59) + (typing != null ? typing.hashCode() : 43);
                }

                protected Bound(ParameterDescription parameterDescription, ParameterBinder<T> parameterBinder, AnnotationDescription.Loadable<T> loadable, Assigner.Typing typing) {
                    this.target = parameterDescription;
                    this.parameterBinder = parameterBinder;
                    this.annotation = loadable;
                    this.typing = typing;
                }

                protected static Handler of(ParameterDescription parameterDescription, ParameterBinder<?> parameterBinder, AnnotationDescription annotationDescription, Assigner.Typing typing) {
                    return new Bound(parameterDescription, parameterBinder, annotationDescription.prepare(parameterBinder.getHandledType()), typing);
                }

                @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.DelegationProcessor.Handler
                public MethodDelegationBinder.ParameterBinding<?> bind(MethodDescription methodDescription, Implementation.Target target, Assigner assigner) {
                    return this.parameterBinder.bind(this.annotation, methodDescription, this.target, target, assigner, this.typing);
                }
            }
        }
    }
}
