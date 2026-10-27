package net.bytebuddy.implementation.attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.implementation.attribute.AnnotationAppender;
import net.bytebuddy.jar.asm.ClassVisitor;

/* JADX INFO: loaded from: classes3.dex */
public interface TypeAttributeAppender {

    public enum NoOp implements TypeAttributeAppender {
        INSTANCE;

        @Override // net.bytebuddy.implementation.attribute.TypeAttributeAppender
        public void apply(ClassVisitor classVisitor, TypeDescription typeDescription, AnnotationValueFilter annotationValueFilter) {
        }
    }

    void apply(ClassVisitor classVisitor, TypeDescription typeDescription, AnnotationValueFilter annotationValueFilter);

    public enum ForInstrumentedType implements TypeAttributeAppender {
        INSTANCE;

        @Override // net.bytebuddy.implementation.attribute.TypeAttributeAppender
        public void apply(ClassVisitor classVisitor, TypeDescription typeDescription, AnnotationValueFilter annotationValueFilter) {
            AnnotationAppender annotationAppenderOfTypeVariable = AnnotationAppender.ForTypeAnnotations.ofTypeVariable(new AnnotationAppender.Default(new AnnotationAppender.Target.OnType(classVisitor)), annotationValueFilter, true, typeDescription.getTypeVariables());
            TypeDescription.Generic superClass = typeDescription.getSuperClass();
            if (superClass != null) {
                annotationAppenderOfTypeVariable = (AnnotationAppender) superClass.accept(AnnotationAppender.ForTypeAnnotations.ofSuperClass(annotationAppenderOfTypeVariable, annotationValueFilter));
            }
            Iterator it = typeDescription.getInterfaces().iterator();
            int i = 0;
            while (it.hasNext()) {
                annotationAppenderOfTypeVariable = (AnnotationAppender) ((TypeDescription.Generic) it.next()).accept(AnnotationAppender.ForTypeAnnotations.ofInterfaceType(annotationAppenderOfTypeVariable, annotationValueFilter, i));
                i++;
            }
            Iterator it2 = typeDescription.getDeclaredAnnotations().iterator();
            while (it2.hasNext()) {
                annotationAppenderOfTypeVariable = annotationAppenderOfTypeVariable.append((AnnotationDescription) it2.next(), annotationValueFilter);
            }
        }

        public static class Differentiating implements TypeAttributeAppender {
            private final int annotationIndex;
            private final int interfaceTypeIndex;
            private final int typeVariableIndex;

            protected boolean canEqual(Object obj) {
                return obj instanceof Differentiating;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Differentiating)) {
                    return false;
                }
                Differentiating differentiating = (Differentiating) obj;
                return differentiating.canEqual(this) && this.annotationIndex == differentiating.annotationIndex && this.typeVariableIndex == differentiating.typeVariableIndex && this.interfaceTypeIndex == differentiating.interfaceTypeIndex;
            }

            public int hashCode() {
                return ((((this.annotationIndex + 59) * 59) + this.typeVariableIndex) * 59) + this.interfaceTypeIndex;
            }

            public Differentiating(TypeDescription typeDescription) {
                this(typeDescription.getDeclaredAnnotations().size(), typeDescription.getTypeVariables().size(), typeDescription.getInterfaces().size());
            }

            protected Differentiating(int i, int i2, int i3) {
                this.annotationIndex = i;
                this.typeVariableIndex = i2;
                this.interfaceTypeIndex = i3;
            }

            @Override // net.bytebuddy.implementation.attribute.TypeAttributeAppender
            public void apply(ClassVisitor classVisitor, TypeDescription typeDescription, AnnotationValueFilter annotationValueFilter) {
                AnnotationAppender annotationAppenderAppend = new AnnotationAppender.Default(new AnnotationAppender.Target.OnType(classVisitor));
                AnnotationAppender.ForTypeAnnotations.ofTypeVariable(annotationAppenderAppend, annotationValueFilter, true, this.typeVariableIndex, typeDescription.getTypeVariables());
                TypeList.Generic interfaces = typeDescription.getInterfaces();
                int i = this.interfaceTypeIndex;
                Iterator it = interfaces.subList(i, interfaces.size()).iterator();
                while (it.hasNext()) {
                    annotationAppenderAppend = (AnnotationAppender) ((TypeDescription.Generic) it.next()).accept(AnnotationAppender.ForTypeAnnotations.ofInterfaceType(annotationAppenderAppend, annotationValueFilter, i));
                    i++;
                }
                AnnotationList declaredAnnotations = typeDescription.getDeclaredAnnotations();
                Iterator it2 = declaredAnnotations.subList(this.annotationIndex, declaredAnnotations.size()).iterator();
                while (it2.hasNext()) {
                    annotationAppenderAppend = annotationAppenderAppend.append((AnnotationDescription) it2.next(), annotationValueFilter);
                }
            }
        }
    }

    public static class Explicit implements TypeAttributeAppender {
        private final List<? extends AnnotationDescription> annotations;

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
            List<? extends AnnotationDescription> list = this.annotations;
            List<? extends AnnotationDescription> list2 = explicit.annotations;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            List<? extends AnnotationDescription> list = this.annotations;
            return 59 + (list == null ? 43 : list.hashCode());
        }

        public Explicit(List<? extends AnnotationDescription> list) {
            this.annotations = list;
        }

        @Override // net.bytebuddy.implementation.attribute.TypeAttributeAppender
        public void apply(ClassVisitor classVisitor, TypeDescription typeDescription, AnnotationValueFilter annotationValueFilter) {
            AnnotationAppender annotationAppenderAppend = new AnnotationAppender.Default(new AnnotationAppender.Target.OnType(classVisitor));
            Iterator<? extends AnnotationDescription> it = this.annotations.iterator();
            while (it.hasNext()) {
                annotationAppenderAppend = annotationAppenderAppend.append(it.next(), annotationValueFilter);
            }
        }
    }

    public static class Compound implements TypeAttributeAppender {
        private final List<TypeAttributeAppender> typeAttributeAppenders;

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
            List<TypeAttributeAppender> list = this.typeAttributeAppenders;
            List<TypeAttributeAppender> list2 = compound.typeAttributeAppenders;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            List<TypeAttributeAppender> list = this.typeAttributeAppenders;
            return 59 + (list == null ? 43 : list.hashCode());
        }

        public Compound(TypeAttributeAppender... typeAttributeAppenderArr) {
            this((List<? extends TypeAttributeAppender>) Arrays.asList(typeAttributeAppenderArr));
        }

        public Compound(List<? extends TypeAttributeAppender> list) {
            this.typeAttributeAppenders = new ArrayList();
            for (TypeAttributeAppender typeAttributeAppender : list) {
                if (typeAttributeAppender instanceof Compound) {
                    this.typeAttributeAppenders.addAll(((Compound) typeAttributeAppender).typeAttributeAppenders);
                } else if (!(typeAttributeAppender instanceof NoOp)) {
                    this.typeAttributeAppenders.add(typeAttributeAppender);
                }
            }
        }

        @Override // net.bytebuddy.implementation.attribute.TypeAttributeAppender
        public void apply(ClassVisitor classVisitor, TypeDescription typeDescription, AnnotationValueFilter annotationValueFilter) {
            Iterator<TypeAttributeAppender> it = this.typeAttributeAppenders.iterator();
            while (it.hasNext()) {
                it.next().apply(classVisitor, typeDescription, annotationValueFilter);
            }
        }
    }
}
