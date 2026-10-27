package net.bytebuddy.implementation.attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.attribute.AnnotationAppender;
import net.bytebuddy.jar.asm.FieldVisitor;

/* JADX INFO: loaded from: classes3.dex */
public interface FieldAttributeAppender {

    public enum NoOp implements FieldAttributeAppender, Factory {
        INSTANCE;

        @Override // net.bytebuddy.implementation.attribute.FieldAttributeAppender
        public void apply(FieldVisitor fieldVisitor, FieldDescription fieldDescription, AnnotationValueFilter annotationValueFilter) {
        }

        @Override // net.bytebuddy.implementation.attribute.FieldAttributeAppender.Factory
        public FieldAttributeAppender make(TypeDescription typeDescription) {
            return this;
        }
    }

    void apply(FieldVisitor fieldVisitor, FieldDescription fieldDescription, AnnotationValueFilter annotationValueFilter);

    public interface Factory {
        FieldAttributeAppender make(TypeDescription typeDescription);

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

            @Override // net.bytebuddy.implementation.attribute.FieldAttributeAppender.Factory
            public FieldAttributeAppender make(TypeDescription typeDescription) {
                ArrayList arrayList = new ArrayList(this.factories.size());
                Iterator<Factory> it = this.factories.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().make(typeDescription));
                }
                return new Compound(arrayList);
            }
        }
    }

    public enum ForInstrumentedField implements FieldAttributeAppender, Factory {
        INSTANCE;

        @Override // net.bytebuddy.implementation.attribute.FieldAttributeAppender.Factory
        public FieldAttributeAppender make(TypeDescription typeDescription) {
            return this;
        }

        @Override // net.bytebuddy.implementation.attribute.FieldAttributeAppender
        public void apply(FieldVisitor fieldVisitor, FieldDescription fieldDescription, AnnotationValueFilter annotationValueFilter) {
            AnnotationAppender annotationAppenderAppend = (AnnotationAppender) fieldDescription.getType().accept(AnnotationAppender.ForTypeAnnotations.ofFieldType(new AnnotationAppender.Default(new AnnotationAppender.Target.OnField(fieldVisitor)), annotationValueFilter));
            Iterator it = fieldDescription.getDeclaredAnnotations().iterator();
            while (it.hasNext()) {
                annotationAppenderAppend = annotationAppenderAppend.append((AnnotationDescription) it.next(), annotationValueFilter);
            }
        }
    }

    public static class Explicit implements FieldAttributeAppender, Factory {
        private final List<? extends AnnotationDescription> annotations;

        @Override // net.bytebuddy.implementation.attribute.FieldAttributeAppender.Factory
        public FieldAttributeAppender make(TypeDescription typeDescription) {
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

        @Override // net.bytebuddy.implementation.attribute.FieldAttributeAppender
        public void apply(FieldVisitor fieldVisitor, FieldDescription fieldDescription, AnnotationValueFilter annotationValueFilter) {
            AnnotationAppender annotationAppenderAppend = new AnnotationAppender.Default(new AnnotationAppender.Target.OnField(fieldVisitor));
            Iterator<? extends AnnotationDescription> it = this.annotations.iterator();
            while (it.hasNext()) {
                annotationAppenderAppend = annotationAppenderAppend.append(it.next(), annotationValueFilter);
            }
        }
    }

    public static class Compound implements FieldAttributeAppender {
        private final List<FieldAttributeAppender> fieldAttributeAppenders;

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
            List<FieldAttributeAppender> list = this.fieldAttributeAppenders;
            List<FieldAttributeAppender> list2 = compound.fieldAttributeAppenders;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            List<FieldAttributeAppender> list = this.fieldAttributeAppenders;
            return 59 + (list == null ? 43 : list.hashCode());
        }

        public Compound(FieldAttributeAppender... fieldAttributeAppenderArr) {
            this((List<? extends FieldAttributeAppender>) Arrays.asList(fieldAttributeAppenderArr));
        }

        public Compound(List<? extends FieldAttributeAppender> list) {
            this.fieldAttributeAppenders = new ArrayList();
            for (FieldAttributeAppender fieldAttributeAppender : list) {
                if (fieldAttributeAppender instanceof Compound) {
                    this.fieldAttributeAppenders.addAll(((Compound) fieldAttributeAppender).fieldAttributeAppenders);
                } else if (!(fieldAttributeAppender instanceof NoOp)) {
                    this.fieldAttributeAppenders.add(fieldAttributeAppender);
                }
            }
        }

        @Override // net.bytebuddy.implementation.attribute.FieldAttributeAppender
        public void apply(FieldVisitor fieldVisitor, FieldDescription fieldDescription, AnnotationValueFilter annotationValueFilter) {
            Iterator<FieldAttributeAppender> it = this.fieldAttributeAppenders.iterator();
            while (it.hasNext()) {
                it.next().apply(fieldVisitor, fieldDescription, annotationValueFilter);
            }
        }
    }
}
