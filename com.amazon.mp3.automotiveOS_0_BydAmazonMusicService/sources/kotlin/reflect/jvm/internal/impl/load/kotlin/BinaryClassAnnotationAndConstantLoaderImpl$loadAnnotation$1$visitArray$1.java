package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils;
import kotlin.reflect.jvm.internal.impl.load.kotlin.BinaryClassAnnotationAndConstantLoaderImpl;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ClassLiteralValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;

/* JADX INFO: compiled from: BinaryClassAnnotationAndConstantLoaderImpl.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1$visitArray$1 implements KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor {
    final /* synthetic */ ClassDescriptor $annotationClass;
    final /* synthetic */ ClassId $annotationClassId;
    final /* synthetic */ Name $name;
    final /* synthetic */ List<AnnotationDescriptor> $result;
    private final ArrayList<ConstantValue<?>> elements = new ArrayList<>();
    final /* synthetic */ BinaryClassAnnotationAndConstantLoaderImpl.AnonymousClass1 this$0;
    final /* synthetic */ BinaryClassAnnotationAndConstantLoaderImpl this$1;

    BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1$visitArray$1(BinaryClassAnnotationAndConstantLoaderImpl.AnonymousClass1 anonymousClass1, Name name, BinaryClassAnnotationAndConstantLoaderImpl binaryClassAnnotationAndConstantLoaderImpl, ClassDescriptor classDescriptor, ClassId classId, List<AnnotationDescriptor> list) {
        this.this$0 = anonymousClass1;
        this.$name = name;
        this.this$1 = binaryClassAnnotationAndConstantLoaderImpl;
        this.$annotationClass = classDescriptor;
        this.$annotationClassId = classId;
        this.$result = list;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor
    public void visit(Object obj) {
        this.elements.add(this.this$0.createConstant(this.$name, obj));
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor
    public void visitEnum(ClassId enumClassId, Name enumEntryName) {
        Intrinsics.checkNotNullParameter(enumClassId, "enumClassId");
        Intrinsics.checkNotNullParameter(enumEntryName, "enumEntryName");
        this.elements.add(new EnumValue(enumClassId, enumEntryName));
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor
    public void visitClassLiteral(ClassLiteralValue value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.elements.add(new KClassValue(value));
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor
    public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(ClassId classId) {
        Intrinsics.checkNotNullParameter(classId, "classId");
        final ArrayList arrayList = new ArrayList();
        BinaryClassAnnotationAndConstantLoaderImpl binaryClassAnnotationAndConstantLoaderImpl = this.this$1;
        SourceElement NO_SOURCE = SourceElement.NO_SOURCE;
        Intrinsics.checkNotNullExpressionValue(NO_SOURCE, "NO_SOURCE");
        final KotlinJvmBinaryClass.AnnotationArgumentVisitor annotationArgumentVisitorLoadAnnotation = binaryClassAnnotationAndConstantLoaderImpl.loadAnnotation(classId, NO_SOURCE, arrayList);
        Intrinsics.checkNotNull(annotationArgumentVisitorLoadAnnotation);
        return new KotlinJvmBinaryClass.AnnotationArgumentVisitor(this, arrayList) { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1$visitArray$1$visitAnnotation$1
            private final /* synthetic */ KotlinJvmBinaryClass.AnnotationArgumentVisitor $$delegate_0;
            final /* synthetic */ ArrayList<AnnotationDescriptor> $list;
            final /* synthetic */ BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1$visitArray$1 this$0;

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
            public void visit(Name name, Object obj) {
                this.$$delegate_0.visit(name, obj);
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
            public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(Name name, ClassId classId2) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(classId2, "classId");
                return this.$$delegate_0.visitAnnotation(name, classId2);
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
            public KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor visitArray(Name name) {
                Intrinsics.checkNotNullParameter(name, "name");
                return this.$$delegate_0.visitArray(name);
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
            public void visitClassLiteral(Name name, ClassLiteralValue value) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(value, "value");
                this.$$delegate_0.visitClassLiteral(name, value);
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
            public void visitEnum(Name name, ClassId enumClassId, Name enumEntryName) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(enumClassId, "enumClassId");
                Intrinsics.checkNotNullParameter(enumEntryName, "enumEntryName");
                this.$$delegate_0.visitEnum(name, enumClassId, enumEntryName);
            }

            {
                this.this$0 = this;
                this.$list = arrayList;
                this.$$delegate_0 = this.$visitor;
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor
            public void visitEnd() {
                this.$visitor.visitEnd();
                this.this$0.elements.add(new AnnotationValue((AnnotationDescriptor) CollectionsKt___CollectionsKt.single((List) this.$list)));
            }
        };
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor
    public void visitEnd() {
        ValueParameterDescriptor annotationParameterByName = DescriptorResolverUtils.getAnnotationParameterByName(this.$name, this.$annotationClass);
        if (annotationParameterByName != null) {
            HashMap map = this.this$0.arguments;
            Name name = this.$name;
            ConstantValueFactory constantValueFactory = ConstantValueFactory.INSTANCE;
            List<? extends ConstantValue<?>> listCompact = CollectionsKt.compact(this.elements);
            KotlinType type = annotationParameterByName.getType();
            Intrinsics.checkNotNullExpressionValue(type, "parameter.type");
            map.put(name, constantValueFactory.createArrayValue(listCompact, type));
            return;
        }
        if (this.this$1.isImplicitRepeatableContainer(this.$annotationClassId) && Intrinsics.areEqual(this.$name.asString(), "value")) {
            ArrayList<ConstantValue<?>> arrayList = this.elements;
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (obj instanceof AnnotationValue) {
                    arrayList2.add(obj);
                }
            }
            List<AnnotationDescriptor> list = this.$result;
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                list.add(((AnnotationValue) it.next()).getValue());
            }
        }
    }
}
