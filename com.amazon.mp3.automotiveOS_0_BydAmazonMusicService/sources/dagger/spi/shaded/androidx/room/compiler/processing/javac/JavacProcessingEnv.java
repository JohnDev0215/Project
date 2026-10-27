package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import com.amazon.identity.auth.device.utils.AccountConstants;
import dagger.spi.shaded.androidx.room.compiler.processing.XEquality;
import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv;
import dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnvConfig;
import dagger.spi.shaded.androidx.room.compiler.processing.XType;
import dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement;
import dagger.spi.shaded.auto.common.MoreTypes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: JavacProcessingEnv.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\b\u0000\u0018\u0000 K2\u00020\u0001:\u0001KB\u0017\u0012\u0006\u0010\u001e\u001a\u00020\u001d\u0012\u0006\u0010#\u001a\u00020\"¢\u0006\u0004\bI\u0010JJ\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0006H\u0016J\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bJ+\u0010\u0012\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u000e2\u0012\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u000f\"\u00020\u0006H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J8\u0010\u001b\u001a\u00028\u0000\"\n\b\u0000\u0010\u0014\u0018\u0001*\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0086\b¢\u0006\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001e\u001a\u00020\u001d8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u001a\u0010#\u001a\u00020\"8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\u001a\u0010(\u001a\u00020'8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+R\u0017\u0010-\u001a\u00020,8\u0006¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u00100R\u0017\u00102\u001a\u0002018\u0006¢\u0006\f\n\u0004\b2\u00103\u001a\u0004\b4\u00105R \u00107\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0004068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108R\u001b\u0010>\u001a\u0002098VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=R\u001a\u0010@\u001a\u00020?8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010CR\u001a\u0010E\u001a\u00020D8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bE\u0010F\u001a\u0004\bG\u0010H¨\u0006L"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnv;", "", "qName", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacTypeElement;", "findTypeElement", "Ldagger/spi/shaded/androidx/room/compiler/processing/XType;", "findType", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacArrayType;", "getArrayType", "Ljavax/lang/model/element/TypeElement;", "element", "wrapTypeElement", "Ldagger/spi/shaded/androidx/room/compiler/processing/XTypeElement;", "", "types", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacType;", "getDeclaredType", "(Landroidx/room/compiler/processing/XTypeElement;[Landroidx/room/compiler/processing/XType;)Landroidx/room/compiler/processing/javac/JavacType;", "T", "Ljavax/lang/model/type/TypeMirror;", "typeMirror", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "kotlinType", "Ldagger/spi/shaded/androidx/room/compiler/processing/XNullability;", "elementNullability", "wrap", "(Ljavax/lang/model/type/TypeMirror;Landroidx/room/compiler/processing/javac/kotlin/KmType;Landroidx/room/compiler/processing/XNullability;)Landroidx/room/compiler/processing/javac/JavacType;", "Ljavax/annotation/processing/ProcessingEnvironment;", "delegate", "Ljavax/annotation/processing/ProcessingEnvironment;", "getDelegate", "()Ljavax/annotation/processing/ProcessingEnvironment;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnvConfig;", "config", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnvConfig;", "getConfig", "()Landroidx/room/compiler/processing/XProcessingEnvConfig;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnv$Backend;", "backend", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnv$Backend;", "getBackend", "()Landroidx/room/compiler/processing/XProcessingEnv$Backend;", "Ljavax/lang/model/util/Elements;", "elementUtils", "Ljavax/lang/model/util/Elements;", "getElementUtils", "()Ljavax/lang/model/util/Elements;", "Ljavax/lang/model/util/Types;", "typeUtils", "Ljavax/lang/model/util/Types;", "getTypeUtils", "()Ljavax/lang/model/util/Types;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/XTypeElementStore;", "typeElementStore", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/XTypeElementStore;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XMessager;", "messager$delegate", "Lkotlin/Lazy;", "getMessager", "()Landroidx/room/compiler/processing/XMessager;", "messager", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacFiler;", "filer", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacFiler;", "getFiler", "()Landroidx/room/compiler/processing/javac/JavacFiler;", "", "jvmVersion", "I", "getJvmVersion", "()I", "<init>", "(Ljavax/annotation/processing/ProcessingEnvironment;Landroidx/room/compiler/processing/XProcessingEnvConfig;)V", "Companion", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class JavacProcessingEnv implements XProcessingEnv {
    private static final Map<String, TypeKind> NO_TYPES;
    private static final Map<String, TypeKind> PRIMITIVE_TYPES;
    private final XProcessingEnv.Backend backend;
    private final XProcessingEnvConfig config;
    private final ProcessingEnvironment delegate;
    private final Elements elementUtils;
    private final JavacFiler filer;
    private final int jvmVersion;

    /* JADX INFO: renamed from: messager$delegate, reason: from kotlin metadata */
    private final Lazy messager;
    private final XTypeElementStore<TypeElement, JavacTypeElement> typeElementStore;
    private final Types typeUtils;

    /* JADX INFO: compiled from: JavacProcessingEnv.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 176)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[TypeKind.values().length];
            try {
                iArr[TypeKind.ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TypeKind.DECLARED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ElementKind.values().length];
            try {
                iArr2[ElementKind.CONSTRUCTOR.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[ElementKind.METHOD.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public JavacProcessingEnv(ProcessingEnvironment delegate, XProcessingEnvConfig config) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(config, "config");
        this.delegate = delegate;
        this.config = config;
        this.backend = XProcessingEnv.Backend.JAVAC;
        Elements elementUtils = delegate.getElementUtils();
        Intrinsics.checkNotNullExpressionValue(elementUtils, "delegate.elementUtils");
        this.elementUtils = elementUtils;
        Types typeUtils = delegate.getTypeUtils();
        Intrinsics.checkNotNullExpressionValue(typeUtils, "delegate.typeUtils");
        this.typeUtils = typeUtils;
        this.typeElementStore = new XTypeElementStore<>(new Function1<String, TypeElement>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacProcessingEnv$typeElementStore$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final TypeElement invoke(String qName) {
                Intrinsics.checkNotNullParameter(qName, "qName");
                return this.this$0.getDelegate().getElementUtils().getTypeElement(qName);
            }
        }, new Function1<TypeElement, String>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacProcessingEnv$typeElementStore$2
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(TypeElement it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getQualifiedName().toString();
            }
        }, new Function1<TypeElement, JavacTypeElement>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacProcessingEnv$typeElementStore$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final JavacTypeElement invoke(TypeElement typeElement) {
                Intrinsics.checkNotNullParameter(typeElement, "typeElement");
                return JavacTypeElement.INSTANCE.create(this.this$0, typeElement);
            }
        });
        this.messager = LazyKt__LazyJVMKt.lazy(new Function0<JavacProcessingEnvMessager>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacProcessingEnv$messager$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final JavacProcessingEnvMessager invoke() {
                Messager messager = this.this$0.getDelegate().getMessager();
                Intrinsics.checkNotNullExpressionValue(messager, "delegate.messager");
                return new JavacProcessingEnvMessager(messager);
            }
        });
        Filer filer = delegate.getFiler();
        Intrinsics.checkNotNullExpressionValue(filer, "delegate.filer");
        this.filer = new JavacFiler(this, filer);
        Integer intOrNull = StringsKt__StringNumberConversionsKt.toIntOrNull(StringsKt__StringsKt.substringAfter$default(delegate.getSourceVersion().name(), "RELEASE_", (String) null, 2, (Object) null));
        if (intOrNull != null) {
            this.jvmVersion = intOrNull.intValue();
            return;
        }
        throw new IllegalStateException(("Invalid source version: " + delegate.getSourceVersion()).toString());
    }

    public final ProcessingEnvironment getDelegate() {
        return this.delegate;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv
    public XProcessingEnvConfig getConfig() {
        return this.config;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv
    public XProcessingEnv.Backend getBackend() {
        return this.backend;
    }

    public final Elements getElementUtils() {
        return this.elementUtils;
    }

    public final Types getTypeUtils() {
        return this.typeUtils;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv
    public int getJvmVersion() {
        return this.jvmVersion;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv
    public JavacTypeElement findTypeElement(String qName) {
        Intrinsics.checkNotNullParameter(qName, "qName");
        return (JavacTypeElement) this.typeElementStore.get(qName);
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv
    public XType findType(String qName) {
        int i;
        XType javacArrayType;
        XType javacArrayType2;
        Intrinsics.checkNotNullParameter(qName, "qName");
        TypeKind typeKind = PRIMITIVE_TYPES.get(qName);
        if (typeKind != null) {
            TypeMirror primitiveType = this.typeUtils.getPrimitiveType(typeKind);
            Intrinsics.checkNotNullExpressionValue(primitiveType, "typeUtils.getPrimitiveType(it)");
            TypeMirror typeMirror = primitiveType;
            XNullability xNullability = XNullability.NONNULL;
            TypeKind kind = typeMirror.getKind();
            i = kind != null ? WhenMappings.$EnumSwitchMapping$0[kind.ordinal()] : -1;
            if (i != 1) {
                if (i != 2) {
                    if (xNullability != null) {
                        return new DefaultJavacType(this, typeMirror, xNullability);
                    }
                    return new DefaultJavacType(this, typeMirror);
                }
                if (xNullability != null) {
                    DeclaredType declaredTypeAsDeclared = MoreTypes.asDeclared(typeMirror);
                    Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared, "asDeclared(typeMirror)");
                    return new JavacDeclaredType(this, declaredTypeAsDeclared, xNullability);
                }
                DeclaredType declaredTypeAsDeclared2 = MoreTypes.asDeclared(typeMirror);
                Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared2, "asDeclared(typeMirror)");
                javacArrayType2 = new JavacDeclaredType(this, declaredTypeAsDeclared2);
            } else {
                if (xNullability != null) {
                    ArrayType arrayTypeAsArray = MoreTypes.asArray(typeMirror);
                    Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray, "asArray(typeMirror)");
                    return new JavacArrayType(this, arrayTypeAsArray, xNullability, null);
                }
                ArrayType arrayTypeAsArray2 = MoreTypes.asArray(typeMirror);
                Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray2, "asArray(typeMirror)");
                javacArrayType2 = new JavacArrayType(this, arrayTypeAsArray2);
            }
            return javacArrayType2;
        }
        TypeKind typeKind2 = NO_TYPES.get(qName);
        if (typeKind2 != null) {
            TypeMirror noType = this.typeUtils.getNoType(typeKind2);
            Intrinsics.checkNotNullExpressionValue(noType, "typeUtils.getNoType(it)");
            TypeMirror typeMirror2 = noType;
            XNullability xNullability2 = XNullability.NONNULL;
            TypeKind kind2 = typeMirror2.getKind();
            i = kind2 != null ? WhenMappings.$EnumSwitchMapping$0[kind2.ordinal()] : -1;
            if (i != 1) {
                if (i != 2) {
                    if (xNullability2 != null) {
                        return new DefaultJavacType(this, typeMirror2, xNullability2);
                    }
                    return new DefaultJavacType(this, typeMirror2);
                }
                if (xNullability2 != null) {
                    DeclaredType declaredTypeAsDeclared3 = MoreTypes.asDeclared(typeMirror2);
                    Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared3, "asDeclared(typeMirror)");
                    return new JavacDeclaredType(this, declaredTypeAsDeclared3, xNullability2);
                }
                DeclaredType declaredTypeAsDeclared4 = MoreTypes.asDeclared(typeMirror2);
                Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared4, "asDeclared(typeMirror)");
                javacArrayType = new JavacDeclaredType(this, declaredTypeAsDeclared4);
            } else {
                if (xNullability2 != null) {
                    ArrayType arrayTypeAsArray3 = MoreTypes.asArray(typeMirror2);
                    Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray3, "asArray(typeMirror)");
                    return new JavacArrayType(this, arrayTypeAsArray3, xNullability2, null);
                }
                ArrayType arrayTypeAsArray4 = MoreTypes.asArray(typeMirror2);
                Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray4, "asArray(typeMirror)");
                javacArrayType = new JavacArrayType(this, arrayTypeAsArray4);
            }
            return javacArrayType;
        }
        JavacTypeElement javacTypeElementFindTypeElement = findTypeElement(qName);
        if (javacTypeElementFindTypeElement != null) {
            return javacTypeElementFindTypeElement.getType();
        }
        return null;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv
    public JavacArrayType getArrayType(XType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        if (!(type instanceof JavacType)) {
            throw new IllegalStateException(("given type must be from java, " + type + " is not").toString());
        }
        ArrayType arrayType = this.typeUtils.getArrayType(((JavacType) type).getTypeMirror());
        Intrinsics.checkNotNullExpressionValue(arrayType, "typeUtils.getArrayType(type.typeMirror)");
        return new JavacArrayType(this, arrayType, XNullability.UNKNOWN, type.getNullability());
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv
    public JavacType getDeclaredType(XTypeElement type, XType... types) {
        XEquality javacArrayType;
        XEquality javacArrayType2;
        XEquality defaultJavacType;
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(types, "types");
        if (!(type instanceof JavacTypeElement)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        ArrayList arrayList = new ArrayList(types.length);
        boolean z = false;
        for (XType xType : types) {
            if (!(xType instanceof JavacType)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            arrayList.add(((JavacType) xType).getTypeMirror());
        }
        Object[] array = arrayList.toArray(new TypeMirror[0]);
        Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        TypeMirror[] typeMirrorArr = (TypeMirror[]) array;
        int length = types.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = true;
                break;
            }
            if (!(types[i] instanceof JavacType)) {
                break;
            }
            i++;
        }
        if (!z) {
            throw new IllegalStateException("Check failed.".toString());
        }
        JavacTypeElement javacTypeElement = (JavacTypeElement) type;
        TypeMirror declaredType = this.typeUtils.getDeclaredType(javacTypeElement.getElement(), (TypeMirror[]) Arrays.copyOf(typeMirrorArr, typeMirrorArr.length));
        Intrinsics.checkNotNullExpressionValue(declaredType, "typeUtils.getDeclaredType(type.element, *args)");
        TypeMirror typeMirror = declaredType;
        XNullability nullability = ElementExtKt.getNullability(javacTypeElement.getElement());
        TypeKind kind = typeMirror.getKind();
        int i2 = kind == null ? -1 : WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (nullability != null) {
                    defaultJavacType = new DefaultJavacType(this, typeMirror, nullability);
                } else {
                    defaultJavacType = new DefaultJavacType(this, typeMirror);
                }
            } else if (nullability != null) {
                DeclaredType declaredTypeAsDeclared = MoreTypes.asDeclared(typeMirror);
                Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared, "asDeclared(typeMirror)");
                javacArrayType2 = new JavacDeclaredType(this, declaredTypeAsDeclared, nullability);
                defaultJavacType = javacArrayType2;
            } else {
                DeclaredType declaredTypeAsDeclared2 = MoreTypes.asDeclared(typeMirror);
                Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared2, "asDeclared(typeMirror)");
                javacArrayType = new JavacDeclaredType(this, declaredTypeAsDeclared2);
                defaultJavacType = javacArrayType;
            }
        } else if (nullability != null) {
            ArrayType arrayTypeAsArray = MoreTypes.asArray(typeMirror);
            Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray, "asArray(typeMirror)");
            javacArrayType2 = new JavacArrayType(this, arrayTypeAsArray, nullability, null);
            defaultJavacType = javacArrayType2;
        } else {
            ArrayType arrayTypeAsArray2 = MoreTypes.asArray(typeMirror);
            Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray2, "asArray(typeMirror)");
            javacArrayType = new JavacArrayType(this, arrayTypeAsArray2);
            defaultJavacType = javacArrayType;
        }
        return (JavacDeclaredType) defaultJavacType;
    }

    public final JavacTypeElement wrapTypeElement(TypeElement element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return (JavacTypeElement) this.typeElementStore.get(element);
    }

    static {
        TypeKind[] typeKindArrValues = TypeKind.values();
        ArrayList arrayList = new ArrayList();
        for (TypeKind typeKind : typeKindArrValues) {
            if (typeKind.isPrimitive()) {
                arrayList.add(typeKind);
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10)), 16));
        for (Object obj : arrayList) {
            String strName = ((TypeKind) obj).name();
            Locale US = Locale.US;
            Intrinsics.checkNotNullExpressionValue(US, "US");
            String lowerCase = strName.toLowerCase(US);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            linkedHashMap.put(lowerCase, obj);
        }
        PRIMITIVE_TYPES = linkedHashMap;
        List listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new TypeKind[]{TypeKind.VOID, TypeKind.NONE});
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(listListOf, 10)), 16));
        for (Object obj2 : listListOf) {
            String strName2 = ((TypeKind) obj2).name();
            Locale US2 = Locale.US;
            Intrinsics.checkNotNullExpressionValue(US2, "US");
            String lowerCase2 = strName2.toLowerCase(US2);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
            linkedHashMap2.put(lowerCase2, obj2);
        }
        NO_TYPES = linkedHashMap2;
    }
}
