package net.bytebuddy.asm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;

/* JADX INFO: loaded from: classes3.dex */
public interface AsmVisitorWrapper {
    public static final int NO_FLAGS = 0;

    public static abstract class AbstractBase implements AsmVisitorWrapper {
        @Override // net.bytebuddy.asm.AsmVisitorWrapper
        public int mergeReader(int i) {
            return i;
        }

        @Override // net.bytebuddy.asm.AsmVisitorWrapper
        public int mergeWriter(int i) {
            return i;
        }
    }

    public enum NoOp implements AsmVisitorWrapper {
        INSTANCE;

        @Override // net.bytebuddy.asm.AsmVisitorWrapper
        public int mergeReader(int i) {
            return i;
        }

        @Override // net.bytebuddy.asm.AsmVisitorWrapper
        public int mergeWriter(int i) {
            return i;
        }

        @Override // net.bytebuddy.asm.AsmVisitorWrapper
        public ClassVisitor wrap(TypeDescription typeDescription, ClassVisitor classVisitor, Implementation.Context context, TypePool typePool, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, int i, int i2) {
            return classVisitor;
        }
    }

    int mergeReader(int i);

    int mergeWriter(int i);

    ClassVisitor wrap(TypeDescription typeDescription, ClassVisitor classVisitor, Implementation.Context context, TypePool typePool, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, int i, int i2);

    public static class ForDeclaredFields extends AbstractBase {
        private final List<Entry> entries;

        public interface FieldVisitorWrapper {
            FieldVisitor wrap(TypeDescription typeDescription, FieldDescription.InDefinedShape inDefinedShape, FieldVisitor fieldVisitor);
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof ForDeclaredFields;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForDeclaredFields)) {
                return false;
            }
            ForDeclaredFields forDeclaredFields = (ForDeclaredFields) obj;
            if (!forDeclaredFields.canEqual(this)) {
                return false;
            }
            List<Entry> list = this.entries;
            List<Entry> list2 = forDeclaredFields.entries;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            List<Entry> list = this.entries;
            return 59 + (list == null ? 43 : list.hashCode());
        }

        public ForDeclaredFields() {
            this(Collections.emptyList());
        }

        protected ForDeclaredFields(List<Entry> list) {
            this.entries = list;
        }

        public ForDeclaredFields field(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher, FieldVisitorWrapper... fieldVisitorWrapperArr) {
            return field(elementMatcher, Arrays.asList(fieldVisitorWrapperArr));
        }

        public ForDeclaredFields field(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher, List<? extends FieldVisitorWrapper> list) {
            return new ForDeclaredFields(CompoundList.of(this.entries, new Entry(elementMatcher, list)));
        }

        @Override // net.bytebuddy.asm.AsmVisitorWrapper
        public ClassVisitor wrap(TypeDescription typeDescription, ClassVisitor classVisitor, Implementation.Context context, TypePool typePool, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, int i, int i2) {
            HashMap map = new HashMap();
            for (FieldDescription.InDefinedShape inDefinedShape : fieldList) {
                map.put(inDefinedShape.getInternalName() + inDefinedShape.getDescriptor(), inDefinedShape);
            }
            return new DispatchingVisitor(classVisitor, typeDescription, map);
        }

        protected static class Entry implements ElementMatcher<FieldDescription.InDefinedShape>, FieldVisitorWrapper {
            private final List<? extends FieldVisitorWrapper> fieldVisitorWrappers;
            private final ElementMatcher<? super FieldDescription.InDefinedShape> matcher;

            protected boolean canEqual(Object obj) {
                return obj instanceof Entry;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Entry)) {
                    return false;
                }
                Entry entry = (Entry) obj;
                if (!entry.canEqual(this)) {
                    return false;
                }
                ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher = this.matcher;
                ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher2 = entry.matcher;
                if (elementMatcher != null ? !elementMatcher.equals(elementMatcher2) : elementMatcher2 != null) {
                    return false;
                }
                List<? extends FieldVisitorWrapper> list = this.fieldVisitorWrappers;
                List<? extends FieldVisitorWrapper> list2 = entry.fieldVisitorWrappers;
                return list != null ? list.equals(list2) : list2 == null;
            }

            public int hashCode() {
                ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher = this.matcher;
                int iHashCode = elementMatcher == null ? 43 : elementMatcher.hashCode();
                List<? extends FieldVisitorWrapper> list = this.fieldVisitorWrappers;
                return ((iHashCode + 59) * 59) + (list != null ? list.hashCode() : 43);
            }

            protected Entry(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher, List<? extends FieldVisitorWrapper> list) {
                this.matcher = elementMatcher;
                this.fieldVisitorWrappers = list;
            }

            @Override // net.bytebuddy.matcher.ElementMatcher
            public boolean matches(FieldDescription.InDefinedShape inDefinedShape) {
                return inDefinedShape != null && this.matcher.matches(inDefinedShape);
            }

            @Override // net.bytebuddy.asm.AsmVisitorWrapper.ForDeclaredFields.FieldVisitorWrapper
            public FieldVisitor wrap(TypeDescription typeDescription, FieldDescription.InDefinedShape inDefinedShape, FieldVisitor fieldVisitor) {
                Iterator<? extends FieldVisitorWrapper> it = this.fieldVisitorWrappers.iterator();
                while (it.hasNext()) {
                    fieldVisitor = it.next().wrap(typeDescription, inDefinedShape, fieldVisitor);
                }
                return fieldVisitor;
            }
        }

        protected class DispatchingVisitor extends ClassVisitor {
            private final Map<String, FieldDescription.InDefinedShape> fields;
            private final TypeDescription instrumentedType;

            protected DispatchingVisitor(ClassVisitor classVisitor, TypeDescription typeDescription, Map<String, FieldDescription.InDefinedShape> map) {
                super(Opcodes.ASM6, classVisitor);
                this.instrumentedType = typeDescription;
                this.fields = map;
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            public FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
                FieldVisitor fieldVisitorVisitField = super.visitField(i, str, str2, str3, obj);
                FieldDescription.InDefinedShape inDefinedShape = this.fields.get(str + str2);
                if (fieldVisitorVisitField != null && inDefinedShape != null) {
                    for (Entry entry : ForDeclaredFields.this.entries) {
                        if (entry.matches(inDefinedShape)) {
                            fieldVisitorVisitField = entry.wrap(this.instrumentedType, inDefinedShape, fieldVisitorVisitField);
                        }
                    }
                }
                return fieldVisitorVisitField;
            }
        }
    }

    public static class ForDeclaredMethods implements AsmVisitorWrapper {
        private final List<Entry> entries;
        private final int readerFlags;
        private final int writerFlags;

        public interface MethodVisitorWrapper {
            MethodVisitor wrap(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, TypePool typePool, int i, int i2);
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof ForDeclaredMethods;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForDeclaredMethods)) {
                return false;
            }
            ForDeclaredMethods forDeclaredMethods = (ForDeclaredMethods) obj;
            if (!forDeclaredMethods.canEqual(this)) {
                return false;
            }
            List<Entry> list = this.entries;
            List<Entry> list2 = forDeclaredMethods.entries;
            if (list != null ? list.equals(list2) : list2 == null) {
                return this.writerFlags == forDeclaredMethods.writerFlags && this.readerFlags == forDeclaredMethods.readerFlags;
            }
            return false;
        }

        public int hashCode() {
            List<Entry> list = this.entries;
            return (((((list == null ? 43 : list.hashCode()) + 59) * 59) + this.writerFlags) * 59) + this.readerFlags;
        }

        public ForDeclaredMethods() {
            this(Collections.emptyList(), 0, 0);
        }

        protected ForDeclaredMethods(List<Entry> list, int i, int i2) {
            this.entries = list;
            this.writerFlags = i;
            this.readerFlags = i2;
        }

        public ForDeclaredMethods method(ElementMatcher<? super MethodDescription> elementMatcher, MethodVisitorWrapper... methodVisitorWrapperArr) {
            return method(elementMatcher, Arrays.asList(methodVisitorWrapperArr));
        }

        public ForDeclaredMethods method(ElementMatcher<? super MethodDescription> elementMatcher, List<? extends MethodVisitorWrapper> list) {
            return new ForDeclaredMethods(CompoundList.of(this.entries, new Entry(elementMatcher, list)), this.writerFlags, this.readerFlags);
        }

        public ForDeclaredMethods writerFlags(int i) {
            return new ForDeclaredMethods(this.entries, i | this.writerFlags, this.readerFlags);
        }

        public ForDeclaredMethods readerFlags(int i) {
            return new ForDeclaredMethods(this.entries, this.writerFlags, i | this.readerFlags);
        }

        @Override // net.bytebuddy.asm.AsmVisitorWrapper
        public int mergeWriter(int i) {
            return i | this.writerFlags;
        }

        @Override // net.bytebuddy.asm.AsmVisitorWrapper
        public int mergeReader(int i) {
            return i | this.readerFlags;
        }

        @Override // net.bytebuddy.asm.AsmVisitorWrapper
        public ClassVisitor wrap(TypeDescription typeDescription, ClassVisitor classVisitor, Implementation.Context context, TypePool typePool, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, int i, int i2) {
            HashMap map = new HashMap();
            for (MethodDescription methodDescription : CompoundList.of(methodList, new MethodDescription.Latent.TypeInitializer(typeDescription))) {
                map.put(methodDescription.getInternalName() + methodDescription.getDescriptor(), methodDescription);
            }
            return new DispatchingVisitor(classVisitor, typeDescription, context, typePool, map, i, i2);
        }

        protected static class Entry implements ElementMatcher<MethodDescription>, MethodVisitorWrapper {
            private final ElementMatcher<? super MethodDescription> matcher;
            private final List<? extends MethodVisitorWrapper> methodVisitorWrappers;

            protected boolean canEqual(Object obj) {
                return obj instanceof Entry;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Entry)) {
                    return false;
                }
                Entry entry = (Entry) obj;
                if (!entry.canEqual(this)) {
                    return false;
                }
                ElementMatcher<? super MethodDescription> elementMatcher = this.matcher;
                ElementMatcher<? super MethodDescription> elementMatcher2 = entry.matcher;
                if (elementMatcher != null ? !elementMatcher.equals(elementMatcher2) : elementMatcher2 != null) {
                    return false;
                }
                List<? extends MethodVisitorWrapper> list = this.methodVisitorWrappers;
                List<? extends MethodVisitorWrapper> list2 = entry.methodVisitorWrappers;
                return list != null ? list.equals(list2) : list2 == null;
            }

            public int hashCode() {
                ElementMatcher<? super MethodDescription> elementMatcher = this.matcher;
                int iHashCode = elementMatcher == null ? 43 : elementMatcher.hashCode();
                List<? extends MethodVisitorWrapper> list = this.methodVisitorWrappers;
                return ((iHashCode + 59) * 59) + (list != null ? list.hashCode() : 43);
            }

            protected Entry(ElementMatcher<? super MethodDescription> elementMatcher, List<? extends MethodVisitorWrapper> list) {
                this.matcher = elementMatcher;
                this.methodVisitorWrappers = list;
            }

            @Override // net.bytebuddy.matcher.ElementMatcher
            public boolean matches(MethodDescription methodDescription) {
                return methodDescription != null && this.matcher.matches(methodDescription);
            }

            @Override // net.bytebuddy.asm.AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper
            public MethodVisitor wrap(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, TypePool typePool, int i, int i2) {
                Iterator<? extends MethodVisitorWrapper> it = this.methodVisitorWrappers.iterator();
                MethodVisitor methodVisitorWrap = methodVisitor;
                while (it.hasNext()) {
                    methodVisitorWrap = it.next().wrap(typeDescription, methodDescription, methodVisitorWrap, context, typePool, i, i2);
                }
                return methodVisitorWrap;
            }
        }

        protected class DispatchingVisitor extends ClassVisitor {
            private final Implementation.Context implementationContext;
            private final TypeDescription instrumentedType;
            private final Map<String, MethodDescription> methods;
            private final int readerFlags;
            private final TypePool typePool;
            private final int writerFlags;

            protected DispatchingVisitor(ClassVisitor classVisitor, TypeDescription typeDescription, Implementation.Context context, TypePool typePool, Map<String, MethodDescription> map, int i, int i2) {
                super(Opcodes.ASM6, classVisitor);
                this.instrumentedType = typeDescription;
                this.implementationContext = context;
                this.typePool = typePool;
                this.methods = map;
                this.writerFlags = i;
                this.readerFlags = i2;
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
                MethodVisitor methodVisitorVisitMethod = super.visitMethod(i, str, str2, str3, strArr);
                MethodDescription methodDescription = this.methods.get(str + str2);
                if (methodVisitorVisitMethod == null || methodDescription == null) {
                    return methodVisitorVisitMethod;
                }
                MethodVisitor methodVisitorWrap = methodVisitorVisitMethod;
                for (Entry entry : ForDeclaredMethods.this.entries) {
                    if (entry.matches(methodDescription)) {
                        methodVisitorWrap = entry.wrap(this.instrumentedType, methodDescription, methodVisitorWrap, this.implementationContext, this.typePool, this.writerFlags, this.readerFlags);
                    }
                }
                return methodVisitorWrap;
            }
        }
    }

    public static class Compound implements AsmVisitorWrapper {
        private final List<AsmVisitorWrapper> asmVisitorWrappers;

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
            List<AsmVisitorWrapper> list = this.asmVisitorWrappers;
            List<AsmVisitorWrapper> list2 = compound.asmVisitorWrappers;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            List<AsmVisitorWrapper> list = this.asmVisitorWrappers;
            return 59 + (list == null ? 43 : list.hashCode());
        }

        public Compound(AsmVisitorWrapper... asmVisitorWrapperArr) {
            this((List<? extends AsmVisitorWrapper>) Arrays.asList(asmVisitorWrapperArr));
        }

        public Compound(List<? extends AsmVisitorWrapper> list) {
            this.asmVisitorWrappers = new ArrayList();
            for (AsmVisitorWrapper asmVisitorWrapper : list) {
                if (asmVisitorWrapper instanceof Compound) {
                    this.asmVisitorWrappers.addAll(((Compound) asmVisitorWrapper).asmVisitorWrappers);
                } else if (!(asmVisitorWrapper instanceof NoOp)) {
                    this.asmVisitorWrappers.add(asmVisitorWrapper);
                }
            }
        }

        @Override // net.bytebuddy.asm.AsmVisitorWrapper
        public int mergeWriter(int i) {
            Iterator<AsmVisitorWrapper> it = this.asmVisitorWrappers.iterator();
            while (it.hasNext()) {
                i = it.next().mergeWriter(i);
            }
            return i;
        }

        @Override // net.bytebuddy.asm.AsmVisitorWrapper
        public int mergeReader(int i) {
            Iterator<AsmVisitorWrapper> it = this.asmVisitorWrappers.iterator();
            while (it.hasNext()) {
                i = it.next().mergeReader(i);
            }
            return i;
        }

        @Override // net.bytebuddy.asm.AsmVisitorWrapper
        public ClassVisitor wrap(TypeDescription typeDescription, ClassVisitor classVisitor, Implementation.Context context, TypePool typePool, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, int i, int i2) {
            Iterator<AsmVisitorWrapper> it = this.asmVisitorWrappers.iterator();
            ClassVisitor classVisitorWrap = classVisitor;
            while (it.hasNext()) {
                classVisitorWrap = it.next().wrap(typeDescription, classVisitorWrap, context, typePool, fieldList, methodList, i, i2);
            }
            return classVisitorWrap;
        }
    }
}
