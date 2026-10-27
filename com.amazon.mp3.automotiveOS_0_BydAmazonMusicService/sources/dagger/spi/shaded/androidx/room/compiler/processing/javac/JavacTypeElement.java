package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.squareup.javapoet.ClassName;
import dagger.spi.shaded.androidx.room.compiler.codegen.XClassName;
import dagger.spi.shaded.androidx.room.compiler.codegen.XTypeName;
import dagger.spi.shaded.androidx.room.compiler.processing.DeclarationCollectorKt;
import dagger.spi.shaded.androidx.room.compiler.processing.XEnumTypeElement;
import dagger.spi.shaded.androidx.room.compiler.processing.XEquality;
import dagger.spi.shaded.androidx.room.compiler.processing.XFieldElement;
import dagger.spi.shaded.androidx.room.compiler.processing.XHasModifiers;
import dagger.spi.shaded.androidx.room.compiler.processing.XMemberContainer;
import dagger.spi.shaded.androidx.room.compiler.processing.XMethodElement;
import dagger.spi.shaded.androidx.room.compiler.processing.XMethodElementKt;
import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacProcessingEnv;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacTypeElement;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KmType;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KotlinMetadataElement;
import dagger.spi.shaded.androidx.room.compiler.processing.util.MemoizedSequence;
import dagger.spi.shaded.auto.common.MoreElements;
import dagger.spi.shaded.auto.common.MoreTypes;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* JADX INFO: compiled from: JavacTypeElement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000 Z2\u00020\u00012\u00020\u00022\u00020\u0003:\u0003Z[\\B\u0019\b\u0004\u0012\u0006\u0010W\u001a\u00020V\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\bX\u0010YJ\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u0004H\u0016J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004H\u0016R\u001a\u0010\r\u001a\u00020\f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0016\u001a\u0004\u0018\u00010\u00118FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001b\u0010\u001b\u001a\u00020\u00178VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010 \u001a\u00020\u001c8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u0013\u001a\u0004\b\u001e\u0010\u001fR\u001b\u0010%\u001a\u00020!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u0013\u001a\u0004\b#\u0010$R\u001d\u0010*\u001a\u0004\u0018\u00010&8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\u0013\u001a\u0004\b(\u0010)R!\u0010/\u001a\b\u0012\u0004\u0012\u00020+0\u00048VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\u0013\u001a\u0004\b-\u0010.R\u001d\u00103\u001a\u0004\u0018\u00010\u00028VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b0\u0010\u0013\u001a\u0004\b1\u00102R!\u00107\u001a\b\u0012\u0004\u0012\u0002040\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b5\u0010\u0013\u001a\u0004\b6\u0010.R\u001a\u0010:\u001a\b\u0012\u0004\u0012\u000209088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R\u001a\u0010<\u001a\b\u0012\u0004\u0012\u00020\u0005088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010;R!\u0010?\u001a\b\u0012\u0004\u0012\u00020\t0\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b=\u0010\u0013\u001a\u0004\b>\u0010.R\u001b\u0010D\u001a\u00020@8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bA\u0010\u0013\u001a\u0004\bB\u0010CR\u001d\u0010I\u001a\u0004\u0018\u00010E8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bF\u0010\u0013\u001a\u0004\bG\u0010HR!\u0010L\u001a\b\u0012\u0004\u0012\u00020E0\u00048VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bJ\u0010\u0013\u001a\u0004\bK\u0010.R\u0014\u0010N\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bM\u0010\u001aR\u001a\u0010R\u001a\u00020\u00178VX\u0096\u0004¢\u0006\f\u0012\u0004\bP\u0010Q\u001a\u0004\bO\u0010\u001aR\u0014\u0010U\u001a\u00020\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bS\u0010T\u0082\u0001\u0002]^¨\u0006_"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacTypeElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XTypeElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XHasModifiers;", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/XFieldElement;", "getDeclaredFields", "", "isInterface", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacMethodElement;", "getDeclaredMethods", "getSuperInterfaceElements", "Ljavax/lang/model/element/TypeElement;", "element", "Ljavax/lang/model/element/TypeElement;", "getElement", "()Ljavax/lang/model/element/TypeElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KotlinMetadataElement;", "kotlinMetadata$delegate", "Lkotlin/Lazy;", "getKotlinMetadata", "()Landroidx/room/compiler/processing/javac/kotlin/KotlinMetadataElement;", "kotlinMetadata", "", "qualifiedName$delegate", "getQualifiedName", "()Ljava/lang/String;", "qualifiedName", "Lcom/squareup/javapoet/ClassName;", "className$delegate", "getClassName", "()Lcom/squareup/javapoet/ClassName;", "className", "Ldagger/spi/shaded/androidx/room/compiler/codegen/XClassName;", "xClassName$delegate", "getXClassName", "()Landroidx/room/compiler/codegen/XClassName;", "xClassName", "Ldagger/spi/shaded/androidx/room/compiler/processing/XMemberContainer;", "enclosingElement$delegate", "getEnclosingElement", "()Landroidx/room/compiler/processing/XMemberContainer;", "enclosingElement", "Ldagger/spi/shaded/androidx/room/compiler/processing/XTypeParameterElement;", "typeParameters$delegate", "getTypeParameters", "()Ljava/util/List;", "typeParameters", "enclosingTypeElement$delegate", "getEnclosingTypeElement", "()Landroidx/room/compiler/processing/XTypeElement;", "enclosingTypeElement", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacFieldElement;", "_declaredFields$delegate", "get_declaredFields", "_declaredFields", "Ldagger/spi/shaded/androidx/room/compiler/processing/util/MemoizedSequence;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XMethodElement;", "allMethods", "Ldagger/spi/shaded/androidx/room/compiler/processing/util/MemoizedSequence;", "allFieldsIncludingPrivateSupers", "_declaredMethods$delegate", "get_declaredMethods", "_declaredMethods", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacDeclaredType;", "type$delegate", "getType", "()Landroidx/room/compiler/processing/javac/JavacDeclaredType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacType;", "superClass$delegate", "getSuperClass", "()Landroidx/room/compiler/processing/javac/JavacType;", "superClass", "superInterfaces$delegate", "getSuperInterfaces", "superInterfaces", "getName", "name", "getPackageName", "getPackageName$annotations", "()V", CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, "getClosestMemberContainer", "()Landroidx/room/compiler/processing/javac/JavacTypeElement;", "closestMemberContainer", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "env", "<init>", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/element/TypeElement;)V", "Companion", "DefaultJavacTypeElement", "JavacEnumTypeElement", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacTypeElement$DefaultJavacTypeElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacTypeElement$JavacEnumTypeElement;", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public abstract class JavacTypeElement extends JavacElement implements XTypeElement, XHasModifiers {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final /* synthetic */ JavacHasModifiers $$delegate_0;

    /* JADX INFO: renamed from: _declaredFields$delegate, reason: from kotlin metadata */
    private final Lazy _declaredFields;

    /* JADX INFO: renamed from: _declaredMethods$delegate, reason: from kotlin metadata */
    private final Lazy _declaredMethods;
    private final MemoizedSequence<XFieldElement> allFieldsIncludingPrivateSupers;
    private final MemoizedSequence<XMethodElement> allMethods;

    /* JADX INFO: renamed from: className$delegate, reason: from kotlin metadata */
    private final Lazy className;
    private final TypeElement element;

    /* JADX INFO: renamed from: enclosingElement$delegate, reason: from kotlin metadata */
    private final Lazy enclosingElement;

    /* JADX INFO: renamed from: enclosingTypeElement$delegate, reason: from kotlin metadata */
    private final Lazy enclosingTypeElement;

    /* JADX INFO: renamed from: kotlinMetadata$delegate, reason: from kotlin metadata */
    private final Lazy kotlinMetadata;

    /* JADX INFO: renamed from: qualifiedName$delegate, reason: from kotlin metadata */
    private final Lazy qualifiedName;

    /* JADX INFO: renamed from: superClass$delegate, reason: from kotlin metadata */
    private final Lazy superClass;

    /* JADX INFO: renamed from: superInterfaces$delegate, reason: from kotlin metadata */
    private final Lazy superInterfaces;

    /* JADX INFO: renamed from: type$delegate, reason: from kotlin metadata */
    private final Lazy type;

    /* JADX INFO: renamed from: typeParameters$delegate, reason: from kotlin metadata */
    private final Lazy typeParameters;

    /* JADX INFO: renamed from: xClassName$delegate, reason: from kotlin metadata */
    private final Lazy xClassName;

    public /* synthetic */ JavacTypeElement(JavacProcessingEnv javacProcessingEnv, TypeElement typeElement, DefaultConstructorMarker defaultConstructorMarker) {
        this(javacProcessingEnv, typeElement);
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacElement
    public TypeElement getElement() {
        return this.element;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    private JavacTypeElement(final JavacProcessingEnv javacProcessingEnv, TypeElement typeElement) {
        Element element = (Element) typeElement;
        super(javacProcessingEnv, element);
        this.element = typeElement;
        this.$$delegate_0 = new JavacHasModifiers(element);
        this.kotlinMetadata = LazyKt__LazyJVMKt.lazy(new Function0<KotlinMetadataElement>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacTypeElement$kotlinMetadata$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final KotlinMetadataElement invoke() {
                return KotlinMetadataElement.INSTANCE.createFor((Element) this.this$0.getElement());
            }
        });
        this.qualifiedName = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacTypeElement$qualifiedName$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return this.this$0.getElement().getQualifiedName().toString();
            }
        });
        this.className = LazyKt__LazyJVMKt.lazy(new Function0<ClassName>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacTypeElement$className$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ClassName invoke() {
                return this.this$0.getXClassName().getJava();
            }
        });
        this.xClassName = LazyKt__LazyJVMKt.lazy(new Function0<XClassName>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacTypeElement$xClassName$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final XClassName invoke() {
                ClassName className = ClassName.get(this.this$0.getElement());
                Intrinsics.checkNotNullExpressionValue(className, "get(element)");
                return new XClassName(className, XTypeName.INSTANCE.getUNAVAILABLE_KTYPE_NAME$room_compiler_processing(), XNullability.NONNULL);
            }
        });
        this.enclosingElement = LazyKt__LazyJVMKt.lazy(new Function0<XTypeElement>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacTypeElement$enclosingElement$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final XTypeElement invoke() {
                return this.this$0.getEnclosingTypeElement();
            }
        });
        this.typeParameters = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends JavacTypeParameterElement>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacTypeElement$typeParameters$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends JavacTypeParameterElement> invoke() {
                KmType kmType;
                List<KmType> typeArguments;
                List typeParameters = this.this$0.getElement().getTypeParameters();
                Intrinsics.checkNotNullExpressionValue(typeParameters, "element.typeParameters");
                JavacTypeElement javacTypeElement = this.this$0;
                JavacProcessingEnv javacProcessingEnv2 = javacProcessingEnv;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(typeParameters, 10));
                int i = 0;
                for (Object obj : typeParameters) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    TypeParameterElement typeParameter = (TypeParameterElement) obj;
                    KotlinMetadataElement kotlinMetadata = javacTypeElement.getKotlinMetadata();
                    KmType kmType2 = (kotlinMetadata == null || (kmType = kotlinMetadata.getKmType()) == null || (typeArguments = kmType.getTypeArguments()) == null) ? null : typeArguments.get(i);
                    Intrinsics.checkNotNullExpressionValue(typeParameter, "typeParameter");
                    arrayList.add(new JavacTypeParameterElement(javacProcessingEnv2, javacTypeElement, typeParameter, kmType2));
                    i = i2;
                }
                return arrayList;
            }
        });
        this.enclosingTypeElement = LazyKt__LazyJVMKt.lazy(new Function0<JavacTypeElement>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacTypeElement$enclosingTypeElement$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final JavacTypeElement invoke() {
                return ElementExtKt.enclosingType(this.this$0.getElement(), javacProcessingEnv);
            }
        });
        this._declaredFields = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends JavacFieldElement>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacTypeElement$_declaredFields$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends JavacFieldElement> invoke() {
                List listFieldsIn = ElementFilter.fieldsIn(this.this$0.getElement().getEnclosedElements());
                Intrinsics.checkNotNullExpressionValue(listFieldsIn, "fieldsIn(element.enclosedElements)");
                ArrayList<VariableElement> arrayList = new ArrayList();
                for (Object obj : listFieldsIn) {
                    if (!(((VariableElement) obj).getKind() == ElementKind.ENUM_CONSTANT)) {
                        arrayList.add(obj);
                    }
                }
                JavacProcessingEnv javacProcessingEnv2 = javacProcessingEnv;
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
                for (VariableElement it : arrayList) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    arrayList2.add(new JavacFieldElement(javacProcessingEnv2, it));
                }
                return arrayList2;
            }
        });
        this.allMethods = new MemoizedSequence<>(new Function0<Sequence<? extends XMethodElement>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacTypeElement$allMethods$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Sequence<? extends XMethodElement> invoke() {
                return DeclarationCollectorKt.collectAllMethods(this.this$0);
            }
        });
        this.allFieldsIncludingPrivateSupers = new MemoizedSequence<>(new Function0<Sequence<? extends XFieldElement>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacTypeElement$allFieldsIncludingPrivateSupers$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Sequence<? extends XFieldElement> invoke() {
                return DeclarationCollectorKt.collectFieldsIncludingPrivateSupers(this.this$0);
            }
        });
        this._declaredMethods = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends JavacMethodElement>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacTypeElement$_declaredMethods$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends JavacMethodElement> invoke() {
                List<ExecutableElement> listMethodsIn = ElementFilter.methodsIn(this.this$0.getElement().getEnclosedElements());
                Intrinsics.checkNotNullExpressionValue(listMethodsIn, "methodsIn(element.enclosedElements)");
                JavacProcessingEnv javacProcessingEnv2 = javacProcessingEnv;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listMethodsIn, 10));
                for (ExecutableElement it : listMethodsIn) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    arrayList.add(new JavacMethodElement(javacProcessingEnv2, it));
                }
                return XMethodElementKt.filterMethodsByConfig(arrayList, javacProcessingEnv);
            }
        });
        this.type = LazyKt__LazyJVMKt.lazy(new Function0<JavacDeclaredType>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacTypeElement$type$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final JavacDeclaredType invoke() {
                XEquality javacArrayType;
                XEquality javacArrayType2;
                JavacProcessingEnv javacProcessingEnv2 = javacProcessingEnv;
                TypeMirror typeMirrorAsType = this.getElement().asType();
                Intrinsics.checkNotNullExpressionValue(typeMirrorAsType, "element.asType()");
                KotlinMetadataElement kotlinMetadata = this.getKotlinMetadata();
                KmType kmType = kotlinMetadata != null ? kotlinMetadata.getKmType() : null;
                XNullability nullability = ElementExtKt.getNullability(this.getElement());
                TypeKind kind = typeMirrorAsType.getKind();
                int i = kind == null ? -1 : JavacProcessingEnv.WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
                if (i != 1) {
                    if (i != 2) {
                        if (kmType != null) {
                            javacArrayType2 = new DefaultJavacType(javacProcessingEnv2, typeMirrorAsType, kmType);
                        } else if (nullability != null) {
                            javacArrayType2 = new DefaultJavacType(javacProcessingEnv2, typeMirrorAsType, nullability);
                        } else {
                            javacArrayType2 = new DefaultJavacType(javacProcessingEnv2, typeMirrorAsType);
                        }
                    } else if (kmType != null) {
                        DeclaredType declaredTypeAsDeclared = MoreTypes.asDeclared(typeMirrorAsType);
                        Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared, "asDeclared(typeMirror)");
                        javacArrayType2 = new JavacDeclaredType(javacProcessingEnv2, declaredTypeAsDeclared, kmType);
                    } else {
                        if (nullability != null) {
                            DeclaredType declaredTypeAsDeclared2 = MoreTypes.asDeclared(typeMirrorAsType);
                            Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared2, "asDeclared(typeMirror)");
                            javacArrayType = new JavacDeclaredType(javacProcessingEnv2, declaredTypeAsDeclared2, nullability);
                        } else {
                            DeclaredType declaredTypeAsDeclared3 = MoreTypes.asDeclared(typeMirrorAsType);
                            Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared3, "asDeclared(typeMirror)");
                            javacArrayType = new JavacDeclaredType(javacProcessingEnv2, declaredTypeAsDeclared3);
                        }
                        javacArrayType2 = javacArrayType;
                    }
                } else if (kmType != null) {
                    ArrayType arrayTypeAsArray = MoreTypes.asArray(typeMirrorAsType);
                    Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray, "asArray(typeMirror)");
                    javacArrayType2 = new JavacArrayType(javacProcessingEnv2, arrayTypeAsArray, kmType);
                } else {
                    if (nullability != null) {
                        ArrayType arrayTypeAsArray2 = MoreTypes.asArray(typeMirrorAsType);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray2, "asArray(typeMirror)");
                        javacArrayType = new JavacArrayType(javacProcessingEnv2, arrayTypeAsArray2, nullability, null);
                    } else {
                        ArrayType arrayTypeAsArray3 = MoreTypes.asArray(typeMirrorAsType);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray3, "asArray(typeMirror)");
                        javacArrayType = new JavacArrayType(javacProcessingEnv2, arrayTypeAsArray3);
                    }
                    javacArrayType2 = javacArrayType;
                }
                return (JavacDeclaredType) javacArrayType2;
            }
        });
        this.superClass = LazyKt__LazyJVMKt.lazy(new Function0<JavacType>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacTypeElement$superClass$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final JavacType invoke() {
                JavacDeclaredType javacDeclaredType;
                TypeMirror superClass = this.this$0.getElement().getSuperclass();
                if (superClass.getKind() == TypeKind.NONE) {
                    return null;
                }
                JavacProcessingEnv javacProcessingEnv2 = javacProcessingEnv;
                Intrinsics.checkNotNullExpressionValue(superClass, "superClass");
                KotlinMetadataElement kotlinMetadata = this.this$0.getKotlinMetadata();
                KmType superType = kotlinMetadata != null ? kotlinMetadata.getSuperType() : null;
                XNullability nullability = ElementExtKt.getNullability(this.this$0.getElement());
                TypeKind kind = superClass.getKind();
                int i = kind == null ? -1 : JavacProcessingEnv.WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
                if (i == 1) {
                    if (superType != null) {
                        ArrayType arrayTypeAsArray = MoreTypes.asArray(superClass);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray, "asArray(typeMirror)");
                        return new JavacArrayType(javacProcessingEnv2, arrayTypeAsArray, superType);
                    }
                    if (nullability != null) {
                        ArrayType arrayTypeAsArray2 = MoreTypes.asArray(superClass);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray2, "asArray(typeMirror)");
                        return new JavacArrayType(javacProcessingEnv2, arrayTypeAsArray2, nullability, null);
                    }
                    ArrayType arrayTypeAsArray3 = MoreTypes.asArray(superClass);
                    Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray3, "asArray(typeMirror)");
                    return new JavacArrayType(javacProcessingEnv2, arrayTypeAsArray3);
                }
                if (i != 2) {
                    if (superType != null) {
                        return new DefaultJavacType(javacProcessingEnv2, superClass, superType);
                    }
                    if (nullability != null) {
                        return new DefaultJavacType(javacProcessingEnv2, superClass, nullability);
                    }
                    return new DefaultJavacType(javacProcessingEnv2, superClass);
                }
                if (superType != null) {
                    DeclaredType declaredTypeAsDeclared = MoreTypes.asDeclared(superClass);
                    Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared, "asDeclared(typeMirror)");
                    javacDeclaredType = new JavacDeclaredType(javacProcessingEnv2, declaredTypeAsDeclared, superType);
                } else if (nullability != null) {
                    DeclaredType declaredTypeAsDeclared2 = MoreTypes.asDeclared(superClass);
                    Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared2, "asDeclared(typeMirror)");
                    javacDeclaredType = new JavacDeclaredType(javacProcessingEnv2, declaredTypeAsDeclared2, nullability);
                } else {
                    DeclaredType declaredTypeAsDeclared3 = MoreTypes.asDeclared(superClass);
                    Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared3, "asDeclared(typeMirror)");
                    javacDeclaredType = new JavacDeclaredType(javacProcessingEnv2, declaredTypeAsDeclared3);
                }
                return javacDeclaredType;
            }
        });
        this.superInterfaces = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends JavacType>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacTypeElement$superInterfaces$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends JavacType> invoke() {
                XEquality javacArrayType;
                XEquality javacArrayType2;
                List<TypeMirror> interfaces = this.this$0.getElement().getInterfaces();
                Intrinsics.checkNotNullExpressionValue(interfaces, "element.interfaces");
                JavacProcessingEnv javacProcessingEnv2 = javacProcessingEnv;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(interfaces, 10));
                for (TypeMirror it : interfaces) {
                    Element element2 = MoreTypes.asTypeElement(it);
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    KotlinMetadataElement.Companion companion = KotlinMetadataElement.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(element2, "element");
                    Element element3 = element2;
                    KotlinMetadataElement kotlinMetadataElementCreateFor = companion.createFor(element3);
                    KmType kmType = kotlinMetadataElementCreateFor != null ? kotlinMetadataElementCreateFor.getKmType() : null;
                    XNullability nullability = ElementExtKt.getNullability(element3);
                    TypeKind kind = it.getKind();
                    int i = kind == null ? -1 : JavacProcessingEnv.WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
                    if (i != 1) {
                        if (i != 2) {
                            if (kmType != null) {
                                javacArrayType = new DefaultJavacType(javacProcessingEnv2, it, kmType);
                            } else if (nullability != null) {
                                javacArrayType2 = new DefaultJavacType(javacProcessingEnv2, it, nullability);
                                javacArrayType = javacArrayType2;
                            } else {
                                javacArrayType = new DefaultJavacType(javacProcessingEnv2, it);
                            }
                        } else if (kmType != null) {
                            DeclaredType declaredTypeAsDeclared = MoreTypes.asDeclared(it);
                            Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared, "asDeclared(typeMirror)");
                            javacArrayType = new JavacDeclaredType(javacProcessingEnv2, declaredTypeAsDeclared, kmType);
                        } else if (nullability != null) {
                            DeclaredType declaredTypeAsDeclared2 = MoreTypes.asDeclared(it);
                            Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared2, "asDeclared(typeMirror)");
                            javacArrayType2 = new JavacDeclaredType(javacProcessingEnv2, declaredTypeAsDeclared2, nullability);
                            javacArrayType = javacArrayType2;
                        } else {
                            DeclaredType declaredTypeAsDeclared3 = MoreTypes.asDeclared(it);
                            Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared3, "asDeclared(typeMirror)");
                            javacArrayType = new JavacDeclaredType(javacProcessingEnv2, declaredTypeAsDeclared3);
                        }
                    } else if (kmType != null) {
                        ArrayType arrayTypeAsArray = MoreTypes.asArray(it);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray, "asArray(typeMirror)");
                        javacArrayType = new JavacArrayType(javacProcessingEnv2, arrayTypeAsArray, kmType);
                    } else if (nullability != null) {
                        ArrayType arrayTypeAsArray2 = MoreTypes.asArray(it);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray2, "asArray(typeMirror)");
                        javacArrayType2 = new JavacArrayType(javacProcessingEnv2, arrayTypeAsArray2, nullability, null);
                        javacArrayType = javacArrayType2;
                    } else {
                        ArrayType arrayTypeAsArray3 = MoreTypes.asArray(it);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray3, "asArray(typeMirror)");
                        javacArrayType = new JavacArrayType(javacProcessingEnv2, arrayTypeAsArray3);
                    }
                    arrayList.add(javacArrayType);
                }
                return arrayList;
            }
        });
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XElement
    public String getName() {
        return getElement().getSimpleName().toString();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement
    public String getPackageName() {
        return MoreElements.getPackage(getElement()).getQualifiedName().toString();
    }

    public final KotlinMetadataElement getKotlinMetadata() {
        return (KotlinMetadataElement) this.kotlinMetadata.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement
    public String getQualifiedName() {
        return (String) this.qualifiedName.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement, dagger.spi.shaded.androidx.room.compiler.processing.XMemberContainer
    public ClassName getClassName() {
        return (ClassName) this.className.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final XClassName getXClassName() {
        return (XClassName) this.xClassName.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XElement
    public XMemberContainer getEnclosingElement() {
        return (XMemberContainer) this.enclosingElement.getValue();
    }

    public XTypeElement getEnclosingTypeElement() {
        return (XTypeElement) this.enclosingTypeElement.getValue();
    }

    private final List<JavacFieldElement> get_declaredFields() {
        return (List) this._declaredFields.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement
    public List<XFieldElement> getDeclaredFields() {
        return get_declaredFields();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement
    public boolean isInterface() {
        KotlinMetadataElement kotlinMetadata = getKotlinMetadata();
        return kotlinMetadata != null ? kotlinMetadata.isInterface() : getElement().getKind() == ElementKind.INTERFACE;
    }

    private final List<JavacMethodElement> get_declaredMethods() {
        return (List) this._declaredMethods.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement
    public List<JavacMethodElement> getDeclaredMethods() {
        return get_declaredMethods();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement
    public List<XTypeElement> getSuperInterfaceElements() {
        List<TypeMirror> interfaces = getElement().getInterfaces();
        Intrinsics.checkNotNullExpressionValue(interfaces, "element.interfaces");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(interfaces, 10));
        for (TypeMirror typeMirror : interfaces) {
            JavacProcessingEnv env = getEnv();
            TypeElement typeElementAsTypeElement = MoreTypes.asTypeElement(typeMirror);
            Intrinsics.checkNotNullExpressionValue(typeElementAsTypeElement, "asTypeElement(it)");
            arrayList.add(env.wrapTypeElement(typeElementAsTypeElement));
        }
        return arrayList;
    }

    public JavacDeclaredType getType() {
        return (JavacDeclaredType) this.type.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement
    public JavacType getSuperClass() {
        return (JavacType) this.superClass.getValue();
    }

    /* JADX INFO: compiled from: JavacTypeElement.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacTypeElement$DefaultJavacTypeElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacTypeElement;", "env", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "element", "Ljavax/lang/model/element/TypeElement;", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/element/TypeElement;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultJavacTypeElement extends JavacTypeElement {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DefaultJavacTypeElement(JavacProcessingEnv env, TypeElement element) {
            super(env, element, null);
            Intrinsics.checkNotNullParameter(env, "env");
            Intrinsics.checkNotNullParameter(element, "element");
        }
    }

    /* JADX INFO: compiled from: JavacTypeElement.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fR!\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacTypeElement$JavacEnumTypeElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacTypeElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XEnumTypeElement;", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/XEnumEntry;", "entries$delegate", "Lkotlin/Lazy;", "getEntries", "()Ljava/util/Set;", "entries", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "env", "Ljavax/lang/model/element/TypeElement;", "element", "<init>", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/element/TypeElement;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
    public static final class JavacEnumTypeElement extends JavacTypeElement implements XEnumTypeElement {

        /* JADX INFO: renamed from: entries$delegate, reason: from kotlin metadata */
        private final Lazy entries;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public JavacEnumTypeElement(final JavacProcessingEnv env, final TypeElement element) {
            super(env, element, null);
            Intrinsics.checkNotNullParameter(env, "env");
            Intrinsics.checkNotNullParameter(element, "element");
            if (!(element.getKind() == ElementKind.ENUM)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            this.entries = LazyKt__LazyJVMKt.lazy(new Function0<Set<JavacEnumEntry>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacTypeElement$JavacEnumTypeElement$entries$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Set<JavacEnumEntry> invoke() {
                    List enclosedElements = element.getEnclosedElements();
                    Intrinsics.checkNotNullExpressionValue(enclosedElements, "element.enclosedElements");
                    ArrayList<Element> arrayList = new ArrayList();
                    for (Object obj : enclosedElements) {
                        if (((Element) obj).getKind() == ElementKind.ENUM_CONSTANT) {
                            arrayList.add(obj);
                        }
                    }
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    JavacProcessingEnv javacProcessingEnv = env;
                    JavacTypeElement.JavacEnumTypeElement javacEnumTypeElement = this;
                    for (Element it : arrayList) {
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        linkedHashSet.add(new JavacEnumEntry(javacProcessingEnv, it, javacEnumTypeElement));
                    }
                    return linkedHashSet;
                }
            });
        }
    }

    /* JADX INFO: compiled from: JavacTypeElement.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacTypeElement$Companion;", "", "()V", "create", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacTypeElement;", "env", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "typeElement", "Ljavax/lang/model/element/TypeElement;", "room-compiler-processing"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: JavacTypeElement.kt */
        @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ElementKind.values().length];
                try {
                    iArr[ElementKind.ENUM.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final JavacTypeElement create(JavacProcessingEnv env, TypeElement typeElement) {
            Intrinsics.checkNotNullParameter(env, "env");
            Intrinsics.checkNotNullParameter(typeElement, "typeElement");
            ElementKind kind = typeElement.getKind();
            if ((kind == null ? -1 : WhenMappings.$EnumSwitchMapping$0[kind.ordinal()]) == 1) {
                return new JavacEnumTypeElement(env, typeElement);
            }
            return new DefaultJavacTypeElement(env, typeElement);
        }
    }
}
