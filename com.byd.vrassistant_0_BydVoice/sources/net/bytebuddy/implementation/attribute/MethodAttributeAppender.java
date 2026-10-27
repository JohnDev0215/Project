package net.bytebuddy.implementation.attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.attribute.AnnotationAppender;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatchers;

/* JADX INFO: loaded from: classes3.dex */
public interface MethodAttributeAppender {

    public enum NoOp implements MethodAttributeAppender, Factory {
        INSTANCE;

        @Override // net.bytebuddy.implementation.attribute.MethodAttributeAppender
        public void apply(MethodVisitor methodVisitor, MethodDescription methodDescription, AnnotationValueFilter annotationValueFilter) {
        }

        @Override // net.bytebuddy.implementation.attribute.MethodAttributeAppender.Factory
        public MethodAttributeAppender make(TypeDescription typeDescription) {
            return this;
        }
    }

    void apply(MethodVisitor methodVisitor, MethodDescription methodDescription, AnnotationValueFilter annotationValueFilter);

    public interface Factory {
        MethodAttributeAppender make(TypeDescription typeDescription);

        public static class Compound implements Factory {
            private final List<Factory> factories;

            protected boolean canEqual(Object obj) {
                return obj instanceof Compound;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Compound)) {
                    return false;
                }
                Compound compound = (Compound) obj;
                if (!compound.canEqual(this)) {
                    return false;
                }
                List<Factory> list = this.factories;
                List<Factory> list2 = compound.factories;
                return list != null ? list.equals(list2) : list2 == null;
            }

            public int hashCode() {
                List<Factory> list = this.factories;
                return 59 + (list == null ? 43 : list.hashCode());
            }

            public Compound(Factory... factoryArr) {
                this((List<? extends Factory>) Arrays.asList(factoryArr));
            }

            public Compound(List<? extends Factory> list) {
                this.factories = new ArrayList();
                for (Factory factory : list) {
                    if (factory instanceof Compound) {
                        this.factories.addAll(((Compound) factory).factories);
                    } else if (!(factory instanceof NoOp)) {
                        this.factories.add(factory);
                    }
                }
            }

            @Override // net.bytebuddy.implementation.attribute.MethodAttributeAppender.Factory
            public MethodAttributeAppender make(TypeDescription typeDescription) {
                ArrayList arrayList = new ArrayList(this.factories.size());
                Iterator<Factory> it = this.factories.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().make(typeDescription));
                }
                return new Compound(arrayList);
            }
        }
    }

    public enum ForInstrumentedMethod implements MethodAttributeAppender, Factory {
        EXCLUDING_RECEIVER { // from class: net.bytebuddy.implementation.attribute.MethodAttributeAppender.ForInstrumentedMethod.1
            @Override // net.bytebuddy.implementation.attribute.MethodAttributeAppender.ForInstrumentedMethod
            protected AnnotationAppender appendReceiver(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter, MethodDescription methodDescription) {
                return annotationAppender;
            }
        },
        INCLUDING_RECEIVER { // from class: net.bytebuddy.implementation.attribute.MethodAttributeAppender.ForInstrumentedMethod.2
            @Override // net.bytebuddy.implementation.attribute.MethodAttributeAppender.ForInstrumentedMethod
            protected AnnotationAppender appendReceiver(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter, MethodDescription methodDescription) {
                TypeDescription.Generic receiverType = methodDescription.getReceiverType();
                return receiverType == null ? annotationAppender : (AnnotationAppender) receiverType.accept(AnnotationAppender.ForTypeAnnotations.ofReceiverType(annotationAppender, annotationValueFilter));
            }
        };

        protected abstract AnnotationAppender appendReceiver(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter, MethodDescription methodDescription);

        @Override // net.bytebuddy.implementation.attribute.MethodAttributeAppender.Factory
        public MethodAttributeAppender make(TypeDescription typeDescription) {
            return this;
        }

        @Override // net.bytebuddy.implementation.attribute.MethodAttributeAppender
        public void apply(MethodVisitor methodVisitor, MethodDescription methodDescription, AnnotationValueFilter annotationValueFilter) {
            int i = 0;
            AnnotationAppender annotationAppenderOfTypeVariable = AnnotationAppender.ForTypeAnnotations.ofTypeVariable((AnnotationAppender) methodDescription.getReturnType().accept(AnnotationAppender.ForTypeAnnotations.ofMethodReturnType(new AnnotationAppender.Default(new AnnotationAppender.Target.OnMethod(methodVisitor)), annotationValueFilter)), annotationValueFilter, false, methodDescription.getTypeVariables());
            Iterator it = methodDescription.getDeclaredAnnotations().filter(ElementMatchers.not(ElementMatchers.annotationType(ElementMatchers.nameStartsWith("jdk.internal.")))).iterator();
            while (it.hasNext()) {
                annotationAppenderOfTypeVariable = annotationAppenderOfTypeVariable.append((AnnotationDescription) it.next(), annotationValueFilter);
            }
            Iterator it2 = methodDescription.getParameters().iterator();
            while (it2.hasNext()) {
                ParameterDescription parameterDescription = (ParameterDescription) it2.next();
                AnnotationAppender annotationAppenderAppend = (AnnotationAppender) parameterDescription.getType().accept(AnnotationAppender.ForTypeAnnotations.ofMethodParameterType(new AnnotationAppender.Default(new AnnotationAppender.Target.OnMethodParameter(methodVisitor, parameterDescription.getIndex())), annotationValueFilter, parameterDescription.getIndex()));
                Iterator it3 = parameterDescription.getDeclaredAnnotations().iterator();
                while (it3.hasNext()) {
                    annotationAppenderAppend = annotationAppenderAppend.append((AnnotationDescription) it3.next(), annotationValueFilter);
                }
            }
            AnnotationAppender annotationAppenderAppendReceiver = appendReceiver(annotationAppenderOfTypeVariable, annotationValueFilter, methodDescription);
            Iterator it4 = methodDescription.getExceptionTypes().iterator();
            while (it4.hasNext()) {
                annotationAppenderAppendReceiver = (AnnotationAppender) ((TypeDescription.Generic) it4.next()).accept(AnnotationAppender.ForTypeAnnotations.ofExceptionType(annotationAppenderAppendReceiver, annotationValueFilter, i));
                i++;
            }
        }
    }

    public static class Explicit implements MethodAttributeAppender, Factory {
        private final List<? extends AnnotationDescription> annotations;
        private final Target target;

        @Override // net.bytebuddy.implementation.attribute.MethodAttributeAppender.Factory
        public MethodAttributeAppender make(TypeDescription typeDescription) {
            return this;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof Explicit;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Explicit)) {
                return false;
            }
            Explicit explicit = (Explicit) obj;
            if (!explicit.canEqual(this)) {
                return false;
            }
            Target target = this.target;
            Target target2 = explicit.target;
            if (target != null ? !target.equals(target2) : target2 != null) {
                return false;
            }
            List<? extends AnnotationDescription> list = this.annotations;
            List<? extends AnnotationDescription> list2 = explicit.annotations;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            Target target = this.target;
            int iHashCode = target == null ? 43 : target.hashCode();
            List<? extends AnnotationDescription> list = this.annotations;
            return ((iHashCode + 59) * 59) + (list != null ? list.hashCode() : 43);
        }

        public Explicit(int i, List<? extends AnnotationDescription> list) {
            this(new Target.OnMethodParameter(i), list);
        }

        public Explicit(List<? extends AnnotationDescription> list) {
            this(Target.OnMethod.INSTANCE, list);
        }

        protected Explicit(Target target, List<? extends AnnotationDescription> list) {
            this.target = target;
            this.annotations = list;
        }

        public static Factory of(MethodDescription methodDescription) {
            ParameterList<?> parameters = methodDescription.getParameters();
            ArrayList arrayList = new ArrayList(parameters.size() + 1);
            arrayList.add(new Explicit(methodDescription.getDeclaredAnnotations()));
            Iterator it = parameters.iterator();
            while (it.hasNext()) {
                ParameterDescription parameterDescription = (ParameterDescription) it.next();
                arrayList.add(new Explicit(parameterDescription.getIndex(), parameterDescription.getDeclaredAnnotations()));
            }
            return new Factory.Compound(arrayList);
        }

        @Override // net.bytebuddy.implementation.attribute.MethodAttributeAppender
        public void apply(MethodVisitor methodVisitor, MethodDescription methodDescription, AnnotationValueFilter annotationValueFilter) {
            AnnotationAppender annotationAppenderAppend = new AnnotationAppender.Default(this.target.make(methodVisitor, methodDescription));
            Iterator<? extends AnnotationDescription> it = this.annotations.iterator();
            while (it.hasNext()) {
                annotationAppenderAppend = annotationAppenderAppend.append(it.next(), annotationValueFilter);
            }
        }

        protected interface Target {
            AnnotationAppender.Target make(MethodVisitor methodVisitor, MethodDescription methodDescription);

            public enum OnMethod implements Target {
                INSTANCE;

                @Override // net.bytebuddy.implementation.attribute.MethodAttributeAppender.Explicit.Target
                public AnnotationAppender.Target make(MethodVisitor methodVisitor, MethodDescription methodDescription) {
                    return new AnnotationAppender.Target.OnMethod(methodVisitor);
                }
            }

            public static class OnMethodParameter implements Target {
                private final int parameterIndex;

                protected boolean canEqual(Object obj) {
                    return obj instanceof OnMethodParameter;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof OnMethodParameter)) {
                        return false;
                    }
                    OnMethodParameter onMethodParameter = (OnMethodParameter) obj;
                    return onMethodParameter.canEqual(this) && this.parameterIndex == onMethodParameter.parameterIndex;
                }

                public int hashCode() {
                    return 59 + this.parameterIndex;
                }

                protected OnMethodParameter(int i) {
                    this.parameterIndex = i;
                }

                @Override // net.bytebuddy.implementation.attribute.MethodAttributeAppender.Explicit.Target
                public AnnotationAppender.Target make(MethodVisitor methodVisitor, MethodDescription methodDescription) {
                    if (this.parameterIndex >= methodDescription.getParameters().size()) {
                        throw new IllegalArgumentException("Method " + methodDescription + " has less then " + this.parameterIndex + " parameters");
                    }
                    return new AnnotationAppender.Target.OnMethodParameter(methodVisitor, this.parameterIndex);
                }
            }
        }
    }

    public static class ForReceiverType implements MethodAttributeAppender, Factory {
        private final TypeDescription.Generic receiverType;

        @Override // net.bytebuddy.implementation.attribute.MethodAttributeAppender.Factory
        public MethodAttributeAppender make(TypeDescription typeDescription) {
            return this;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof ForReceiverType;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForReceiverType)) {
                return false;
            }
            ForReceiverType forReceiverType = (ForReceiverType) obj;
            if (!forReceiverType.canEqual(this)) {
                return false;
            }
            TypeDescription.Generic generic = this.receiverType;
            TypeDescription.Generic generic2 = forReceiverType.receiverType;
            return generic != null ? generic.equals(generic2) : generic2 == null;
        }

        public int hashCode() {
            TypeDescription.Generic generic = this.receiverType;
            return 59 + (generic == null ? 43 : generic.hashCode());
        }

        public ForReceiverType(TypeDescription.Generic generic) {
            this.receiverType = generic;
        }

        @Override // net.bytebuddy.implementation.attribute.MethodAttributeAppender
        public void apply(MethodVisitor methodVisitor, MethodDescription methodDescription, AnnotationValueFilter annotationValueFilter) {
            this.receiverType.accept(AnnotationAppender.ForTypeAnnotations.ofReceiverType(new AnnotationAppender.Default(new AnnotationAppender.Target.OnMethod(methodVisitor)), annotationValueFilter));
        }
    }

    public static class Compound implements MethodAttributeAppender {
        private final List<MethodAttributeAppender> methodAttributeAppenders;

        protected boolean canEqual(Object obj) {
            return obj instanceof Compound;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Compound)) {
                return false;
            }
            Compound compound = (Compound) obj;
            if (!compound.canEqual(this)) {
                return false;
            }
            List<MethodAttributeAppender> list = this.methodAttributeAppenders;
            List<MethodAttributeAppender> list2 = compound.methodAttributeAppenders;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            List<MethodAttributeAppender> list = this.methodAttributeAppenders;
            return 59 + (list == null ? 43 : list.hashCode());
        }

        public Compound(MethodAttributeAppender... methodAttributeAppenderArr) {
            this((List<? extends MethodAttributeAppender>) Arrays.asList(methodAttributeAppenderArr));
        }

        public Compound(List<? extends MethodAttributeAppender> list) {
            this.methodAttributeAppenders = new ArrayList();
            for (MethodAttributeAppender methodAttributeAppender : list) {
                if (methodAttributeAppender instanceof Compound) {
                    this.methodAttributeAppenders.addAll(((Compound) methodAttributeAppender).methodAttributeAppenders);
                } else if (!(methodAttributeAppender instanceof NoOp)) {
                    this.methodAttributeAppenders.add(methodAttributeAppender);
                }
            }
        }

        @Override // net.bytebuddy.implementation.attribute.MethodAttributeAppender
        public void apply(MethodVisitor methodVisitor, MethodDescription methodDescription, AnnotationValueFilter annotationValueFilter) {
            Iterator<MethodAttributeAppender> it = this.methodAttributeAppenders.iterator();
            while (it.hasNext()) {
                it.next().apply(methodVisitor, methodDescription, annotationValueFilter);
            }
        }
    }
}
