package dagger.spi.shaded.androidx.room.compiler.processing.ksp;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.devtools.ksp.processing.Resolver;
import com.google.devtools.ksp.symbol.KSClassDeclaration;
import com.google.devtools.ksp.symbol.KSDeclaration;
import com.google.devtools.ksp.symbol.KSType;
import com.google.devtools.ksp.symbol.KSTypeArgument;
import com.google.devtools.ksp.symbol.KSTypeParameter;
import com.google.devtools.ksp.symbol.KSTypeReference;
import com.google.devtools.ksp.symbol.Nullability;
import com.google.devtools.ksp.symbol.Variance;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.WildcardTypeName;
import dagger.spi.shaded.androidx.room.compiler.codegen.XTypeName;
import dagger.spi.shaded.androidx.room.compiler.processing.XEquality;
import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import dagger.spi.shaded.androidx.room.compiler.processing.XType;
import dagger.spi.shaded.androidx.room.compiler.processing.XTypeKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;

/* JADX INFO: compiled from: KspType.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b \u0018\u00002\u00020\u00012\u00020\u0002B!\u0012\u0006\u0010 \u001a\u00020\u001f\u0012\u0006\u0010$\u001a\u00020\u0003\u0012\b\u0010T\u001a\u0004\u0018\u00010S¢\u0006\u0004\bU\u0010VJ$\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\r\u001a\u00020\fH\u0016J\f\u0010\u0010\u001a\u00060\u000ej\u0002`\u000fH$J\f\u0010\u0013\u001a\u00060\u0011j\u0002`\u0012H$J\b\u0010\u0014\u001a\u00020\nH\u0016J\u0013\u0010\u0017\u001a\u00020\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0016J\b\u0010\u001a\u001a\u00020\u0006H\u0016J\u0010\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001bH$J\u0006\u0010\u001e\u001a\u00020\u0000R\u0017\u0010 \u001a\u00020\u001f8\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u0017\u0010$\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'R\u001b\u0010-\u001a\u00020(8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,R\u001b\u00101\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b.\u0010*\u001a\u0004\b/\u00100R\u001b\u00105\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b2\u0010*\u001a\u0004\b3\u00104R\u001d\u00109\u001a\u0004\u0018\u00010\u00008BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b6\u0010*\u001a\u0004\b7\u00108R\u001b\u0010\u001c\u001a\u00020\u001b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b:\u0010*\u001a\u0004\b;\u0010<R!\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00010=8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b>\u0010*\u001a\u0004\b?\u0010@R\u001d\u0010F\u001a\u0004\u0018\u00010B8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bC\u0010*\u001a\u0004\bD\u0010ER'\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00010=8VX\u0096\u0084\u0002¢\u0006\u0012\n\u0004\bG\u0010*\u0012\u0004\bI\u0010J\u001a\u0004\bH\u0010@R%\u0010P\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00150L8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bM\u0010*\u001a\u0004\bN\u0010OR\u0014\u0010R\u001a\u00020\u00008@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bQ\u00108¨\u0006W"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XEquality;", "Lcom/google/devtools/ksp/symbol/KSType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "", "", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "resolvedTypeArguments", "resolveTypeArguments", "", "isJavaWildcardType", "Ldagger/spi/shaded/androidx/room/compiler/codegen/XTypeName;", "asTypeName", "Lcom/squareup/javapoet/TypeName;", "Lcom/squareup/kotlinpoet/javapoet/JTypeName;", "resolveJTypeName", "Lcom/squareup/kotlinpoet/TypeName;", "Lcom/squareup/kotlinpoet/javapoet/KTypeName;", "resolveKTypeName", "isError", "", "other", "equals", "", "hashCode", "toString", "Ldagger/spi/shaded/androidx/room/compiler/processing/XNullability;", "nullability", "copyWithNullability", "makeNullable", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspProcessingEnv;", "env", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspProcessingEnv;", "getEnv", "()Landroidx/room/compiler/processing/ksp/KspProcessingEnv;", "ksType", "Lcom/google/devtools/ksp/symbol/KSType;", "getKsType", "()Lcom/google/devtools/ksp/symbol/KSType;", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspRawType;", "rawType$delegate", "Lkotlin/Lazy;", "getRawType", "()Landroidx/room/compiler/processing/ksp/KspRawType;", "rawType", "typeName$delegate", "getTypeName", "()Lcom/squareup/javapoet/TypeName;", "typeName", "xTypeName$delegate", "getXTypeName", "()Landroidx/room/compiler/codegen/XTypeName;", "xTypeName", "jvmWildcardType$delegate", "getJvmWildcardType", "()Landroidx/room/compiler/processing/ksp/KspType;", "jvmWildcardType", "nullability$delegate", "getNullability", "()Landroidx/room/compiler/processing/XNullability;", "", "superTypes$delegate", "getSuperTypes", "()Ljava/util/List;", "superTypes", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspTypeElement;", "typeElement$delegate", "getTypeElement", "()Landroidx/room/compiler/processing/ksp/KspTypeElement;", "typeElement", "typeArguments$delegate", "getTypeArguments", "getTypeArguments$annotations", "()V", "typeArguments", "", "equalityItems$delegate", "getEqualityItems", "()[Ljava/lang/Object;", "equalityItems", "getJvmWildcardTypeOrSelf$room_compiler_processing", "jvmWildcardTypeOrSelf", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspJvmTypeResolver;", "jvmTypeResolver", "<init>", "(Landroidx/room/compiler/processing/ksp/KspProcessingEnv;Lcom/google/devtools/ksp/symbol/KSType;Landroidx/room/compiler/processing/ksp/KspJvmTypeResolver;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public abstract class KspType implements XType, XEquality {
    private final KspProcessingEnv env;

    /* JADX INFO: renamed from: equalityItems$delegate, reason: from kotlin metadata */
    private final Lazy equalityItems;

    /* JADX INFO: renamed from: jvmWildcardType$delegate, reason: from kotlin metadata */
    private final Lazy jvmWildcardType;
    private final KSType ksType;

    /* JADX INFO: renamed from: nullability$delegate, reason: from kotlin metadata */
    private final Lazy nullability;

    /* JADX INFO: renamed from: rawType$delegate, reason: from kotlin metadata */
    private final Lazy rawType;

    /* JADX INFO: renamed from: superTypes$delegate, reason: from kotlin metadata */
    private final Lazy superTypes;

    /* JADX INFO: renamed from: typeArguments$delegate, reason: from kotlin metadata */
    private final Lazy typeArguments;

    /* JADX INFO: renamed from: typeElement$delegate, reason: from kotlin metadata */
    private final Lazy typeElement;

    /* JADX INFO: renamed from: typeName$delegate, reason: from kotlin metadata */
    private final Lazy typeName;

    /* JADX INFO: renamed from: xTypeName$delegate, reason: from kotlin metadata */
    private final Lazy xTypeName;

    protected abstract KspType copyWithNullability(XNullability nullability);

    protected final KspJvmTypeResolver getJvmTypeResolver() {
        return null;
    }

    protected abstract TypeName resolveJTypeName();

    protected abstract com.squareup.kotlinpoet.TypeName resolveKTypeName();

    public KspType(KspProcessingEnv env, KSType ksType, KspJvmTypeResolver kspJvmTypeResolver) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(ksType, "ksType");
        this.env = env;
        this.ksType = ksType;
        this.rawType = LazyKt__LazyJVMKt.lazy(new Function0<KspRawType>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType$rawType$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final KspRawType invoke() {
                return new KspRawType(this.this$0);
            }
        });
        this.typeName = LazyKt__LazyJVMKt.lazy(new Function0<TypeName>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType$typeName$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TypeName invoke() {
                return this.this$0.getXTypeName().getJava();
            }
        });
        this.xTypeName = LazyKt__LazyJVMKt.lazy(new Function0<XTypeName>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType$xTypeName$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final XTypeName invoke() {
                TypeName typeNameResolveJTypeName;
                com.squareup.kotlinpoet.TypeName typeNameResolveKTypeName;
                XTypeName xTypeNameAsTypeName;
                XTypeName xTypeNameAsTypeName2;
                XTypeName.Companion companion = XTypeName.INSTANCE;
                KspType jvmWildcardType = this.this$0.getJvmWildcardType();
                if (jvmWildcardType == null || (xTypeNameAsTypeName2 = jvmWildcardType.asTypeName()) == null || (typeNameResolveJTypeName = xTypeNameAsTypeName2.getJava()) == null) {
                    typeNameResolveJTypeName = this.this$0.resolveJTypeName();
                }
                KspType jvmWildcardType2 = this.this$0.getJvmWildcardType();
                if (jvmWildcardType2 == null || (xTypeNameAsTypeName = jvmWildcardType2.asTypeName()) == null || (typeNameResolveKTypeName = xTypeNameAsTypeName.getKotlin()) == null) {
                    typeNameResolveKTypeName = this.this$0.resolveKTypeName();
                }
                return companion.invoke(typeNameResolveJTypeName, typeNameResolveKTypeName, this.this$0.getNullability());
            }
        });
        this.jvmWildcardType = LazyKt__LazyJVMKt.lazy(new Function0<KspType>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType$jvmWildcardType$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final KspType invoke() {
                this.this$0.getJvmTypeResolver();
                return null;
            }
        });
        this.nullability = LazyKt__LazyJVMKt.lazy(new Function0<XNullability>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType$nullability$2

            /* JADX INFO: compiled from: KspType.kt */
            @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[Nullability.values().length];
                    try {
                        iArr[Nullability.NULLABLE.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[Nullability.NOT_NULL.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final XNullability invoke() {
                int i = WhenMappings.$EnumSwitchMapping$0[this.this$0.getKsType().getNullability().ordinal()];
                if (i == 1) {
                    return XNullability.NULLABLE;
                }
                if (i == 2) {
                    return XNullability.NONNULL;
                }
                return XNullability.UNKNOWN;
            }
        });
        this.superTypes = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends XType>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType$superTypes$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends XType> invoke() {
                Collection collectionEmptyList;
                Sequence<KSTypeReference> superTypes;
                List list;
                if (Intrinsics.areEqual(this.this$0.getTypeName(), TypeName.OBJECT)) {
                    return CollectionsKt__CollectionsKt.emptyList();
                }
                List<KSTypeParameter> typeParameters = this.this$0.getKsType().getDeclaration().getTypeParameters();
                KspType kspType = this.this$0;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(typeParameters, 10));
                int i = 0;
                for (Object obj : typeParameters) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    arrayList.add(TuplesKt.to(((KSTypeParameter) obj).getName().asString(), kspType.getKsType().getArguments().get(i)));
                    i = i2;
                }
                Map map = MapsKt__MapsKt.toMap(arrayList);
                KSDeclaration declaration = this.this$0.getKsType().getDeclaration();
                KSClassDeclaration kSClassDeclaration = declaration instanceof KSClassDeclaration ? (KSClassDeclaration) declaration : null;
                if (kSClassDeclaration == null || (superTypes = kSClassDeclaration.getSuperTypes()) == null || (list = SequencesKt___SequencesKt.toList(superTypes)) == null) {
                    collectionEmptyList = CollectionsKt__CollectionsKt.emptyList();
                } else {
                    KspType kspType2 = this.this$0;
                    collectionEmptyList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        collectionEmptyList.add(kspType2.getEnv().wrap(kspType2.resolveTypeArguments(((KSTypeReference) it.next()).getResolved(), map), false));
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                Iterator it2 = collectionEmptyList.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Object next = it2.next();
                    KspTypeElement typeElement = ((KspType) next).getTypeElement();
                    if (typeElement != null && typeElement.isClass()) {
                        arrayList2.add(next);
                    } else {
                        arrayList3.add(next);
                    }
                }
                Pair pair = new Pair(arrayList2, arrayList3);
                List list2 = (List) pair.component1();
                List list3 = (List) pair.component2();
                if (list2.isEmpty()) {
                    KspProcessingEnv env2 = this.this$0.getEnv();
                    ClassName OBJECT = TypeName.OBJECT;
                    Intrinsics.checkNotNullExpressionValue(OBJECT, "OBJECT");
                    return CollectionsKt___CollectionsKt.plus((Collection) CollectionsKt__CollectionsJVMKt.listOf(env2.requireType(OBJECT)), (Iterable) list3);
                }
                if (!(list2.size() == 1)) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                return CollectionsKt___CollectionsKt.plus((Collection) list2, (Iterable) list3);
            }
        });
        this.typeElement = LazyKt__LazyJVMKt.lazy(new Function0<KspTypeElement>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType$typeElement$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final KspTypeElement invoke() {
                if (XTypeKt.isArray(this.this$0)) {
                    return null;
                }
                KspType kspType = this.this$0;
                if (kspType instanceof KspPrimitiveType) {
                    return null;
                }
                KSDeclaration declaration = kspType.getKsType().getDeclaration();
                KSClassDeclaration kSClassDeclaration = declaration instanceof KSClassDeclaration ? (KSClassDeclaration) declaration : null;
                if (kSClassDeclaration != null) {
                    return this.this$0.getEnv().wrapClassDeclaration(kSClassDeclaration);
                }
                return null;
            }
        });
        this.typeArguments = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends XType>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType$typeArguments$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends XType> invoke() {
                if (this.this$0.getEnv().getResolver().isJavaRawType(this.this$0.getKsType())) {
                    return CollectionsKt__CollectionsKt.emptyList();
                }
                List<KSTypeArgument> arguments = this.this$0.getKsType().getArguments();
                KspType kspType = this.this$0;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arguments, 10));
                int i = 0;
                for (Object obj : arguments) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    arrayList.add(kspType.getEnv().wrap(kspType.getKsType().getDeclaration().getTypeParameters().get(i), (KSTypeArgument) obj));
                    i = i2;
                }
                return arrayList;
            }
        });
        this.equalityItems = LazyKt__LazyJVMKt.lazy(new Function0<KSType[]>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspType$equalityItems$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final KSType[] invoke() {
                return new KSType[]{this.this$0.getKsType()};
            }
        });
    }

    public final KspProcessingEnv getEnv() {
        return this.env;
    }

    public final KSType getKsType() {
        return this.ksType;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XType
    public final TypeName getTypeName() {
        return (TypeName) this.typeName.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final XTypeName getXTypeName() {
        return (XTypeName) this.xTypeName.getValue();
    }

    public XTypeName asTypeName() {
        return getXTypeName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final KspType getJvmWildcardType() {
        return (KspType) this.jvmWildcardType.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XType
    public XNullability getNullability() {
        return (XNullability) this.nullability.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final KSType resolveTypeArguments(KSType type, Map<String, ? extends KSTypeArgument> resolvedTypeArguments) {
        KSType resolved;
        List<KSTypeArgument> arguments;
        KSType resolved2;
        List<KSTypeArgument> arguments2 = type.getArguments();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arguments2, 10));
        for (KSTypeArgument typeArgument : arguments2) {
            KSTypeReference type2 = typeArgument.getType();
            KSDeclaration declaration = (type2 == null || (resolved2 = type2.getResolved()) == null) ? null : resolved2.getDeclaration();
            if (declaration instanceof KSTypeParameter) {
                KSTypeArgument kSTypeArgument = resolvedTypeArguments.get(((KSTypeParameter) declaration).getName().asString());
                if (kSTypeArgument != null) {
                    typeArgument = kSTypeArgument;
                }
            } else if (typeArgument.getType() != null) {
                KSTypeReference type3 = typeArgument.getType();
                boolean z = false;
                if (type3 != null && (resolved = type3.getResolved()) != null && (arguments = resolved.getArguments()) != null && !arguments.isEmpty()) {
                    z = true;
                }
                if (z) {
                    Resolver resolver = this.env.getResolver();
                    Resolver resolver2 = this.env.getResolver();
                    KSTypeReference type4 = typeArgument.getType();
                    Intrinsics.checkNotNull(type4);
                    typeArgument = resolver.getTypeArgument(resolver2.createKSTypeReferenceFromKSType(resolveTypeArguments(type4.getResolved(), resolvedTypeArguments)), Variance.INVARIANT);
                }
            }
            arrayList.add(typeArgument);
        }
        return type.replace(CollectionsKt___CollectionsKt.toList(arrayList));
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XType
    public KspTypeElement getTypeElement() {
        return (KspTypeElement) this.typeElement.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XType
    public boolean isError() {
        return this.ksType.isError() && !isJavaWildcardType();
    }

    private final boolean isJavaWildcardType() {
        return asTypeName().getJava() instanceof WildcardTypeName;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XEquality
    public Object[] getEqualityItems() {
        return (Object[]) this.equalityItems.getValue();
    }

    public boolean equals(Object other) {
        return XEquality.INSTANCE.equals(this, other);
    }

    public int hashCode() {
        return XEquality.INSTANCE.hashCode(getEqualityItems());
    }

    public String toString() {
        return this.ksType.toString();
    }

    public final KspType makeNullable() {
        XNullability nullability = getNullability();
        XNullability xNullability = XNullability.NULLABLE;
        return nullability == xNullability ? this : copyWithNullability(xNullability);
    }
}
