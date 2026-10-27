package dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin;

import java.util.Iterator;
import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.metadata.jvm.KotlinClassMetadata;

/* JADX INFO: compiled from: KotlinMetadataElement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 62\u00020\u0001:\u00016B\u0017\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b4\u00105J\u0006\u0010\u0003\u001a\u00020\u0002J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\bR\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0019\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R!\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0016\u001a\u0004\b\u001c\u0010\u001dR!\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u0016\u001a\u0004\b!\u0010\u001dR!\u0010%\u001a\b\u0012\u0004\u0012\u00020\n0\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b#\u0010\u0016\u001a\u0004\b$\u0010\u001dR\u001b\u0010*\u001a\u00020&8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\u0016\u001a\u0004\b(\u0010)R\u0018\u0010-\u001a\u00020\b*\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0011\u00101\u001a\u00020.8F¢\u0006\u0006\u001a\u0004\b/\u00100R\u0013\u00103\u001a\u0004\u0018\u00010.8F¢\u0006\u0006\u001a\u0004\b2\u00100¨\u00067"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KotlinMetadataElement;", "", "", "isInterface", "Ljavax/lang/model/element/ExecutableElement;", "method", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmFunction;", "getFunctionMetadata", "", "propertyName", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmProperty;", "getPropertyMetadata", "Ljavax/lang/model/element/Element;", "element", "Ljavax/lang/model/element/Element;", "getElement", "()Ljavax/lang/model/element/Element;", "Lkotlinx/metadata/jvm/KotlinClassMetadata$Class;", "classMetadata", "Lkotlinx/metadata/jvm/KotlinClassMetadata$Class;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmClassTypeInfo;", "typeInfo$delegate", "Lkotlin/Lazy;", "getTypeInfo", "()Landroidx/room/compiler/processing/javac/kotlin/KmClassTypeInfo;", "typeInfo", "", "functionList$delegate", "getFunctionList", "()Ljava/util/List;", "functionList", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmConstructor;", "constructorList$delegate", "getConstructorList", "constructorList", "propertyList$delegate", "getPropertyList", "propertyList", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KotlinMetadataClassFlags;", "classFlags$delegate", "getClassFlags", "()Landroidx/room/compiler/processing/javac/kotlin/KotlinMetadataClassFlags;", "classFlags", "getDescriptor", "(Ljavax/lang/model/element/ExecutableElement;)Ljava/lang/String;", "descriptor", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "getKmType", "()Landroidx/room/compiler/processing/javac/kotlin/KmType;", "kmType", "getSuperType", "superType", "<init>", "(Ljavax/lang/model/element/Element;Lkotlinx/metadata/jvm/KotlinClassMetadata$Class;)V", "Companion", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class KotlinMetadataElement {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: classFlags$delegate, reason: from kotlin metadata */
    private final Lazy classFlags;
    private final KotlinClassMetadata.Class classMetadata;

    /* JADX INFO: renamed from: constructorList$delegate, reason: from kotlin metadata */
    private final Lazy constructorList;
    private final Element element;

    /* JADX INFO: renamed from: functionList$delegate, reason: from kotlin metadata */
    private final Lazy functionList;

    /* JADX INFO: renamed from: propertyList$delegate, reason: from kotlin metadata */
    private final Lazy propertyList;

    /* JADX INFO: renamed from: typeInfo$delegate, reason: from kotlin metadata */
    private final Lazy typeInfo;

    public KotlinMetadataElement(Element element, KotlinClassMetadata.Class classMetadata) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(classMetadata, "classMetadata");
        this.element = element;
        this.classMetadata = classMetadata;
        this.typeInfo = LazyKt__LazyJVMKt.lazy(new Function0<KmClassTypeInfo>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KotlinMetadataElement$typeInfo$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function0
            public final KmClassTypeInfo invoke() {
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                this.this$0.classMetadata.accept(new ClassAsKmTypeReader(new Function1<KmClassTypeInfo, Unit>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KotlinMetadataElement$typeInfo$2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(KmClassTypeInfo kmClassTypeInfo) {
                        invoke2(kmClassTypeInfo);
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(KmClassTypeInfo it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        objectRef.element = it;
                    }
                }));
                T t = objectRef.element;
                if (t != 0) {
                    return (KmClassTypeInfo) t;
                }
                Intrinsics.throwUninitializedPropertyAccessException("result");
                return null;
            }
        });
        this.functionList = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends KmFunction>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KotlinMetadataElement$functionList$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends KmFunction> invoke() {
                return KotlinClassMetadataUtilsKt.readFunctions(this.this$0.classMetadata);
            }
        });
        this.constructorList = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends KmConstructor>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KotlinMetadataElement$constructorList$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends KmConstructor> invoke() {
                return KotlinClassMetadataUtilsKt.readConstructors(this.this$0.classMetadata);
            }
        });
        this.propertyList = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends KmProperty>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KotlinMetadataElement$propertyList$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends KmProperty> invoke() {
                return KotlinClassMetadataUtilsKt.readProperties(this.this$0.classMetadata);
            }
        });
        this.classFlags = LazyKt__LazyJVMKt.lazy(new Function0<KotlinMetadataClassFlags>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KotlinMetadataElement$classFlags$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final KotlinMetadataClassFlags invoke() {
                return new KotlinMetadataClassFlags(this.this$0.classMetadata);
            }
        });
    }

    private final KmClassTypeInfo getTypeInfo() {
        return (KmClassTypeInfo) this.typeInfo.getValue();
    }

    public final KmType getKmType() {
        return getTypeInfo().getKmType();
    }

    public final KmType getSuperType() {
        return getTypeInfo().getSuperType();
    }

    private final List<KmFunction> getFunctionList() {
        return (List) this.functionList.getValue();
    }

    private final List<KmProperty> getPropertyList() {
        return (List) this.propertyList.getValue();
    }

    private final KotlinMetadataClassFlags getClassFlags() {
        return (KotlinMetadataClassFlags) this.classFlags.getValue();
    }

    private final String getDescriptor(ExecutableElement executableElement) {
        return JvmDescriptorUtilsKt.descriptor(executableElement);
    }

    public final boolean isInterface() {
        return getClassFlags().isInterface();
    }

    public final KmFunction getFunctionMetadata(ExecutableElement method) {
        Object next;
        KmFunction setter;
        Intrinsics.checkNotNullParameter(method, "method");
        if (!(method.getKind() == ElementKind.METHOD)) {
            throw new IllegalStateException("must pass an element type of method".toString());
        }
        String descriptor = getDescriptor(method);
        Iterator<T> it = getFunctionList().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(((KmFunction) next).getDescriptor(), descriptor)) {
                break;
            }
        }
        KmFunction kmFunction = (KmFunction) next;
        if (kmFunction != null) {
            return kmFunction;
        }
        for (KmProperty kmProperty : getPropertyList()) {
            KmFunction getter = kmProperty.getGetter();
            if (Intrinsics.areEqual(getter != null ? getter.getDescriptor() : null, descriptor)) {
                setter = kmProperty.getGetter();
            } else {
                KmFunction setter2 = kmProperty.getSetter();
                setter = Intrinsics.areEqual(setter2 != null ? setter2.getDescriptor() : null, descriptor) ? kmProperty.getSetter() : null;
            }
            if (setter != null) {
                return setter;
            }
        }
        return null;
    }

    public final KmProperty getPropertyMetadata(String propertyName) {
        Object next;
        Intrinsics.checkNotNullParameter(propertyName, "propertyName");
        Iterator<T> it = getPropertyList().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(((KmProperty) next).getName(), propertyName)) {
                break;
            }
        }
        return (KmProperty) next;
    }

    /* JADX INFO: compiled from: KotlinMetadataElement.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002¨\u0006\t"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KotlinMetadataElement$Companion;", "", "()V", "createFor", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KotlinMetadataElement;", "element", "Ljavax/lang/model/element/Element;", "getMetadataAnnotation", "Lkotlin/Metadata;", "room-compiler-processing"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0043  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KotlinMetadataElement createFor(javax.lang.model.element.Element r12) {
            /*
                r11 = this;
                java.lang.String r0 = "element"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
                kotlin.Metadata r0 = r11.getMetadataAnnotation(r12)
                r1 = 0
                if (r0 == 0) goto L43
                kotlinx.metadata.jvm.KotlinClassHeader r10 = new kotlinx.metadata.jvm.KotlinClassHeader
                int r2 = r0.k()
                java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
                int[] r4 = r0.mv()
                java.lang.String[] r5 = r0.d1()
                java.lang.String[] r6 = r0.d2()
                java.lang.String r7 = r0.xs()
                java.lang.String r8 = r0.pn()
                int r0 = r0.xi()
                java.lang.Integer r9 = java.lang.Integer.valueOf(r0)
                r2 = r10
                r2.<init>(r3, r4, r5, r6, r7, r8, r9)
                kotlinx.metadata.jvm.KotlinClassMetadata$Companion r0 = kotlinx.metadata.jvm.KotlinClassMetadata.INSTANCE
                kotlinx.metadata.jvm.KotlinClassMetadata r0 = r0.read(r10)
                boolean r2 = r0 instanceof kotlinx.metadata.jvm.KotlinClassMetadata.Class
                if (r2 == 0) goto L43
                kotlinx.metadata.jvm.KotlinClassMetadata$Class r0 = (kotlinx.metadata.jvm.KotlinClassMetadata.Class) r0
                goto L44
            L43:
                r0 = r1
            L44:
                if (r0 == 0) goto L4b
                dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KotlinMetadataElement r1 = new dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KotlinMetadataElement
                r1.<init>(r12, r0)
            L4b:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KotlinMetadataElement.Companion.createFor(javax.lang.model.element.Element):dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KotlinMetadataElement");
        }

        private final Metadata getMetadataAnnotation(Element element) {
            if (element == null) {
                return null;
            }
            Metadata metadata = (Metadata) element.getAnnotation(Metadata.class);
            return metadata == null ? getMetadataAnnotation(element.getEnclosingElement()) : metadata;
        }
    }
}
