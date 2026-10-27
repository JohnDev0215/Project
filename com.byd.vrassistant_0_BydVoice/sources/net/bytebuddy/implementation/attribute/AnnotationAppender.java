package net.bytebuddy.implementation.attribute;

import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.jar.asm.TypePath;
import net.bytebuddy.jar.asm.TypeReference;

/* JADX INFO: loaded from: classes3.dex */
public interface AnnotationAppender {
    public static final String NO_NAME = null;

    AnnotationAppender append(AnnotationDescription annotationDescription, AnnotationValueFilter annotationValueFilter);

    AnnotationAppender append(AnnotationDescription annotationDescription, AnnotationValueFilter annotationValueFilter, int i, String str);

    public interface Target {
        AnnotationVisitor visit(String str, boolean z);

        AnnotationVisitor visit(String str, boolean z, int i, String str2);

        public static class OnType implements Target {
            private final ClassVisitor classVisitor;

            protected boolean canEqual(Object obj) {
                return obj instanceof OnType;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof OnType)) {
                    return false;
                }
                OnType onType = (OnType) obj;
                if (!onType.canEqual(this)) {
                    return false;
                }
                ClassVisitor classVisitor = this.classVisitor;
                ClassVisitor classVisitor2 = onType.classVisitor;
                return classVisitor != null ? classVisitor.equals(classVisitor2) : classVisitor2 == null;
            }

            public int hashCode() {
                ClassVisitor classVisitor = this.classVisitor;
                return 59 + (classVisitor == null ? 43 : classVisitor.hashCode());
            }

            public OnType(ClassVisitor classVisitor) {
                this.classVisitor = classVisitor;
            }

            @Override // net.bytebuddy.implementation.attribute.AnnotationAppender.Target
            public AnnotationVisitor visit(String str, boolean z) {
                return this.classVisitor.visitAnnotation(str, z);
            }

            @Override // net.bytebuddy.implementation.attribute.AnnotationAppender.Target
            public AnnotationVisitor visit(String str, boolean z, int i, String str2) {
                return this.classVisitor.visitTypeAnnotation(i, TypePath.fromString(str2), str, z);
            }
        }

        public static class OnMethod implements Target {
            private final MethodVisitor methodVisitor;

            protected boolean canEqual(Object obj) {
                return obj instanceof OnMethod;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof OnMethod)) {
                    return false;
                }
                OnMethod onMethod = (OnMethod) obj;
                if (!onMethod.canEqual(this)) {
                    return false;
                }
                MethodVisitor methodVisitor = this.methodVisitor;
                MethodVisitor methodVisitor2 = onMethod.methodVisitor;
                return methodVisitor != null ? methodVisitor.equals(methodVisitor2) : methodVisitor2 == null;
            }

            public int hashCode() {
                MethodVisitor methodVisitor = this.methodVisitor;
                return 59 + (methodVisitor == null ? 43 : methodVisitor.hashCode());
            }

            public OnMethod(MethodVisitor methodVisitor) {
                this.methodVisitor = methodVisitor;
            }

            @Override // net.bytebuddy.implementation.attribute.AnnotationAppender.Target
            public AnnotationVisitor visit(String str, boolean z) {
                return this.methodVisitor.visitAnnotation(str, z);
            }

            @Override // net.bytebuddy.implementation.attribute.AnnotationAppender.Target
            public AnnotationVisitor visit(String str, boolean z, int i, String str2) {
                return this.methodVisitor.visitTypeAnnotation(i, TypePath.fromString(str2), str, z);
            }
        }

        public static class OnMethodParameter implements Target {
            private final MethodVisitor methodVisitor;
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
                if (!onMethodParameter.canEqual(this)) {
                    return false;
                }
                MethodVisitor methodVisitor = this.methodVisitor;
                MethodVisitor methodVisitor2 = onMethodParameter.methodVisitor;
                if (methodVisitor != null ? methodVisitor.equals(methodVisitor2) : methodVisitor2 == null) {
                    return this.parameterIndex == onMethodParameter.parameterIndex;
                }
                return false;
            }

            public int hashCode() {
                MethodVisitor methodVisitor = this.methodVisitor;
                return (((methodVisitor == null ? 43 : methodVisitor.hashCode()) + 59) * 59) + this.parameterIndex;
            }

            public OnMethodParameter(MethodVisitor methodVisitor, int i) {
                this.methodVisitor = methodVisitor;
                this.parameterIndex = i;
            }

            @Override // net.bytebuddy.implementation.attribute.AnnotationAppender.Target
            public AnnotationVisitor visit(String str, boolean z) {
                return this.methodVisitor.visitParameterAnnotation(this.parameterIndex, str, z);
            }

            @Override // net.bytebuddy.implementation.attribute.AnnotationAppender.Target
            public AnnotationVisitor visit(String str, boolean z, int i, String str2) {
                return this.methodVisitor.visitTypeAnnotation(i, TypePath.fromString(str2), str, z);
            }
        }

        public static class OnField implements Target {
            private final FieldVisitor fieldVisitor;

            protected boolean canEqual(Object obj) {
                return obj instanceof OnField;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof OnField)) {
                    return false;
                }
                OnField onField = (OnField) obj;
                if (!onField.canEqual(this)) {
                    return false;
                }
                FieldVisitor fieldVisitor = this.fieldVisitor;
                FieldVisitor fieldVisitor2 = onField.fieldVisitor;
                return fieldVisitor != null ? fieldVisitor.equals(fieldVisitor2) : fieldVisitor2 == null;
            }

            public int hashCode() {
                FieldVisitor fieldVisitor = this.fieldVisitor;
                return 59 + (fieldVisitor == null ? 43 : fieldVisitor.hashCode());
            }

            public OnField(FieldVisitor fieldVisitor) {
                this.fieldVisitor = fieldVisitor;
            }

            @Override // net.bytebuddy.implementation.attribute.AnnotationAppender.Target
            public AnnotationVisitor visit(String str, boolean z) {
                return this.fieldVisitor.visitAnnotation(str, z);
            }

            @Override // net.bytebuddy.implementation.attribute.AnnotationAppender.Target
            public AnnotationVisitor visit(String str, boolean z, int i, String str2) {
                return this.fieldVisitor.visitTypeAnnotation(i, TypePath.fromString(str2), str, z);
            }
        }
    }

    public static class Default implements AnnotationAppender {
        private final Target target;

        protected boolean canEqual(Object obj) {
            return obj instanceof Default;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Default)) {
                return false;
            }
            Default r4 = (Default) obj;
            if (!r4.canEqual(this)) {
                return false;
            }
            Target target = this.target;
            Target target2 = r4.target;
            return target != null ? target.equals(target2) : target2 == null;
        }

        public int hashCode() {
            Target target = this.target;
            return 59 + (target == null ? 43 : target.hashCode());
        }

        public Default(Target target) {
            this.target = target;
        }

        private static void handle(AnnotationVisitor annotationVisitor, AnnotationDescription annotationDescription, AnnotationValueFilter annotationValueFilter) {
            for (MethodDescription.InDefinedShape inDefinedShape : annotationDescription.getAnnotationType().getDeclaredMethods()) {
                if (annotationValueFilter.isRelevant(annotationDescription, inDefinedShape)) {
                    apply(annotationVisitor, inDefinedShape.getReturnType().asErasure(), inDefinedShape.getName(), annotationDescription.getValue(inDefinedShape).resolve());
                }
            }
            annotationVisitor.visitEnd();
        }

        public static void apply(AnnotationVisitor annotationVisitor, TypeDescription typeDescription, String str, Object obj) {
            if (typeDescription.isArray()) {
                AnnotationVisitor annotationVisitorVisitArray = annotationVisitor.visitArray(str);
                int length = Array.getLength(obj);
                TypeDescription componentType = typeDescription.getComponentType();
                for (int i = 0; i < length; i++) {
                    apply(annotationVisitorVisitArray, componentType, NO_NAME, Array.get(obj, i));
                }
                annotationVisitorVisitArray.visitEnd();
                return;
            }
            if (typeDescription.isAnnotation()) {
                handle(annotationVisitor.visitAnnotation(str, typeDescription.getDescriptor()), (AnnotationDescription) obj, AnnotationValueFilter.Default.APPEND_DEFAULTS);
                return;
            }
            if (typeDescription.isEnum()) {
                annotationVisitor.visitEnum(str, typeDescription.getDescriptor(), ((EnumerationDescription) obj).getValue());
            } else if (typeDescription.represents(Class.class)) {
                annotationVisitor.visit(str, Type.getType(((TypeDescription) obj).getDescriptor()));
            } else {
                annotationVisitor.visit(str, obj);
            }
        }

        @Override // net.bytebuddy.implementation.attribute.AnnotationAppender
        public AnnotationAppender append(AnnotationDescription annotationDescription, AnnotationValueFilter annotationValueFilter) {
            int i = AnonymousClass1.$SwitchMap$java$lang$annotation$RetentionPolicy[annotationDescription.getRetention().ordinal()];
            if (i == 1) {
                doAppend(annotationDescription, true, annotationValueFilter);
            } else if (i == 2) {
                doAppend(annotationDescription, false, annotationValueFilter);
            } else if (i != 3) {
                throw new IllegalStateException("Unexpected retention policy: " + annotationDescription.getRetention());
            }
            return this;
        }

        private void doAppend(AnnotationDescription annotationDescription, boolean z, AnnotationValueFilter annotationValueFilter) {
            handle(this.target.visit(annotationDescription.getAnnotationType().getDescriptor(), z), annotationDescription, annotationValueFilter);
        }

        @Override // net.bytebuddy.implementation.attribute.AnnotationAppender
        public AnnotationAppender append(AnnotationDescription annotationDescription, AnnotationValueFilter annotationValueFilter, int i, String str) {
            int i2 = AnonymousClass1.$SwitchMap$java$lang$annotation$RetentionPolicy[annotationDescription.getRetention().ordinal()];
            if (i2 == 1) {
                doAppend(annotationDescription, true, annotationValueFilter, i, str);
            } else if (i2 == 2) {
                doAppend(annotationDescription, false, annotationValueFilter, i, str);
            } else if (i2 != 3) {
                throw new IllegalStateException("Unexpected retention policy: " + annotationDescription.getRetention());
            }
            return this;
        }

        private void doAppend(AnnotationDescription annotationDescription, boolean z, AnnotationValueFilter annotationValueFilter, int i, String str) {
            handle(this.target.visit(annotationDescription.getAnnotationType().getDescriptor(), z, i, str), annotationDescription, annotationValueFilter);
        }
    }

    /* JADX INFO: renamed from: net.bytebuddy.implementation.attribute.AnnotationAppender$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$lang$annotation$RetentionPolicy;

        static {
            int[] iArr = new int[RetentionPolicy.values().length];
            $SwitchMap$java$lang$annotation$RetentionPolicy = iArr;
            try {
                iArr[RetentionPolicy.RUNTIME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$lang$annotation$RetentionPolicy[RetentionPolicy.CLASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$lang$annotation$RetentionPolicy[RetentionPolicy.SOURCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static class ForTypeAnnotations implements TypeDescription.Generic.Visitor<AnnotationAppender> {
        private static final char COMPONENT_TYPE_PATH = '[';
        private static final String EMPTY_TYPE_PATH = "";
        private static final char INDEXED_TYPE_DELIMITER = ';';
        private static final char INNER_CLASS_PATH = '.';
        private static final int SUPER_CLASS_INDEX = -1;
        public static final boolean VARIABLE_ON_INVOKEABLE = false;
        public static final boolean VARIABLE_ON_TYPE = true;
        private static final char WILDCARD_TYPE_PATH = '*';
        private final AnnotationAppender annotationAppender;
        private final AnnotationValueFilter annotationValueFilter;
        private final String typePath;
        private final int typeReference;

        protected boolean canEqual(Object obj) {
            return obj instanceof ForTypeAnnotations;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForTypeAnnotations)) {
                return false;
            }
            ForTypeAnnotations forTypeAnnotations = (ForTypeAnnotations) obj;
            if (!forTypeAnnotations.canEqual(this)) {
                return false;
            }
            AnnotationAppender annotationAppender = this.annotationAppender;
            AnnotationAppender annotationAppender2 = forTypeAnnotations.annotationAppender;
            if (annotationAppender != null ? !annotationAppender.equals(annotationAppender2) : annotationAppender2 != null) {
                return false;
            }
            AnnotationValueFilter annotationValueFilter = this.annotationValueFilter;
            AnnotationValueFilter annotationValueFilter2 = forTypeAnnotations.annotationValueFilter;
            if (annotationValueFilter != null ? !annotationValueFilter.equals(annotationValueFilter2) : annotationValueFilter2 != null) {
                return false;
            }
            if (this.typeReference != forTypeAnnotations.typeReference) {
                return false;
            }
            String str = this.typePath;
            String str2 = forTypeAnnotations.typePath;
            return str != null ? str.equals(str2) : str2 == null;
        }

        public int hashCode() {
            AnnotationAppender annotationAppender = this.annotationAppender;
            int iHashCode = annotationAppender == null ? 43 : annotationAppender.hashCode();
            AnnotationValueFilter annotationValueFilter = this.annotationValueFilter;
            int iHashCode2 = ((((iHashCode + 59) * 59) + (annotationValueFilter == null ? 43 : annotationValueFilter.hashCode())) * 59) + this.typeReference;
            String str = this.typePath;
            return (iHashCode2 * 59) + (str != null ? str.hashCode() : 43);
        }

        protected ForTypeAnnotations(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter, TypeReference typeReference) {
            this(annotationAppender, annotationValueFilter, typeReference.getValue(), "");
        }

        protected ForTypeAnnotations(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter, int i, String str) {
            this.annotationAppender = annotationAppender;
            this.annotationValueFilter = annotationValueFilter;
            this.typeReference = i;
            this.typePath = str;
        }

        public static TypeDescription.Generic.Visitor<AnnotationAppender> ofSuperClass(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter) {
            return new ForTypeAnnotations(annotationAppender, annotationValueFilter, TypeReference.newSuperTypeReference(-1));
        }

        public static TypeDescription.Generic.Visitor<AnnotationAppender> ofInterfaceType(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter, int i) {
            return new ForTypeAnnotations(annotationAppender, annotationValueFilter, TypeReference.newSuperTypeReference(i));
        }

        public static TypeDescription.Generic.Visitor<AnnotationAppender> ofFieldType(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter) {
            return new ForTypeAnnotations(annotationAppender, annotationValueFilter, TypeReference.newTypeReference(19));
        }

        public static TypeDescription.Generic.Visitor<AnnotationAppender> ofMethodReturnType(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter) {
            return new ForTypeAnnotations(annotationAppender, annotationValueFilter, TypeReference.newTypeReference(20));
        }

        public static TypeDescription.Generic.Visitor<AnnotationAppender> ofMethodParameterType(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter, int i) {
            return new ForTypeAnnotations(annotationAppender, annotationValueFilter, TypeReference.newFormalParameterReference(i));
        }

        public static TypeDescription.Generic.Visitor<AnnotationAppender> ofExceptionType(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter, int i) {
            return new ForTypeAnnotations(annotationAppender, annotationValueFilter, TypeReference.newExceptionReference(i));
        }

        public static TypeDescription.Generic.Visitor<AnnotationAppender> ofReceiverType(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter) {
            return new ForTypeAnnotations(annotationAppender, annotationValueFilter, TypeReference.newTypeReference(21));
        }

        public static AnnotationAppender ofTypeVariable(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter, boolean z, List<? extends TypeDescription.Generic> list) {
            return ofTypeVariable(annotationAppender, annotationValueFilter, z, 0, list);
        }

        public static AnnotationAppender ofTypeVariable(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter, boolean z, int i, List<? extends TypeDescription.Generic> list) {
            int i2;
            int i3;
            if (z) {
                i2 = 17;
                i3 = 0;
            } else {
                i2 = 18;
                i3 = 1;
            }
            for (TypeDescription.Generic generic : list.subList(i, list.size())) {
                int value = TypeReference.newTypeParameterReference(i3, i).getValue();
                Iterator it = generic.getDeclaredAnnotations().iterator();
                while (it.hasNext()) {
                    annotationAppender = annotationAppender.append((AnnotationDescription) it.next(), annotationValueFilter, value, "");
                }
                int i4 = (((TypeDescription.Generic) generic.getUpperBounds().get(0)).getSort().isTypeVariable() || !((TypeDescription.Generic) generic.getUpperBounds().get(0)).isInterface()) ? 0 : 1;
                Iterator it2 = generic.getUpperBounds().iterator();
                while (it2.hasNext()) {
                    annotationAppender = (AnnotationAppender) ((TypeDescription.Generic) it2.next()).accept(new ForTypeAnnotations(annotationAppender, annotationValueFilter, TypeReference.newTypeParameterBoundReference(i2, i, i4)));
                    i4++;
                }
                i++;
            }
            return annotationAppender;
        }

        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
        public AnnotationAppender onGenericArray(TypeDescription.Generic generic) {
            return (AnnotationAppender) generic.getComponentType().accept(new ForTypeAnnotations(apply(generic, this.typePath), this.annotationValueFilter, this.typeReference, this.typePath + '['));
        }

        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
        public AnnotationAppender onWildcard(TypeDescription.Generic generic) {
            TypeDescription.Generic only;
            TypeList.Generic lowerBounds = generic.getLowerBounds();
            if (lowerBounds.isEmpty()) {
                only = generic.getUpperBounds().getOnly();
            } else {
                only = lowerBounds.getOnly();
            }
            return (AnnotationAppender) only.accept(new ForTypeAnnotations(apply(generic, this.typePath), this.annotationValueFilter, this.typeReference, this.typePath + '*'));
        }

        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
        public AnnotationAppender onParameterizedType(TypeDescription.Generic generic) {
            StringBuilder sb = new StringBuilder(this.typePath);
            int i = 0;
            for (int i2 = 0; i2 < generic.asErasure().getSegmentCount(); i2++) {
                sb = sb.append('.');
            }
            AnnotationAppender annotationAppenderApply = apply(generic, sb.toString());
            TypeDescription.Generic ownerType = generic.getOwnerType();
            if (ownerType != null) {
                annotationAppenderApply = (AnnotationAppender) ownerType.accept(new ForTypeAnnotations(annotationAppenderApply, this.annotationValueFilter, this.typeReference, this.typePath));
            }
            Iterator it = generic.getTypeArguments().iterator();
            while (it.hasNext()) {
                annotationAppenderApply = (AnnotationAppender) ((TypeDescription.Generic) it.next()).accept(new ForTypeAnnotations(annotationAppenderApply, this.annotationValueFilter, this.typeReference, sb.toString() + i + ';'));
                i++;
            }
            return annotationAppenderApply;
        }

        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
        public AnnotationAppender onTypeVariable(TypeDescription.Generic generic) {
            return apply(generic, this.typePath);
        }

        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
        public AnnotationAppender onNonGenericType(TypeDescription.Generic generic) {
            StringBuilder sb = new StringBuilder(this.typePath);
            for (int i = 0; i < generic.asErasure().getSegmentCount(); i++) {
                sb = sb.append('.');
            }
            AnnotationAppender annotationAppenderApply = apply(generic, sb.toString());
            return generic.isArray() ? (AnnotationAppender) generic.getComponentType().accept(new ForTypeAnnotations(annotationAppenderApply, this.annotationValueFilter, this.typeReference, this.typePath + '[')) : annotationAppenderApply;
        }

        private AnnotationAppender apply(TypeDescription.Generic generic, String str) {
            AnnotationAppender annotationAppenderAppend = this.annotationAppender;
            Iterator it = generic.getDeclaredAnnotations().iterator();
            while (it.hasNext()) {
                annotationAppenderAppend = annotationAppenderAppend.append((AnnotationDescription) it.next(), this.annotationValueFilter, this.typeReference, str);
            }
            return annotationAppenderAppend;
        }
    }
}
