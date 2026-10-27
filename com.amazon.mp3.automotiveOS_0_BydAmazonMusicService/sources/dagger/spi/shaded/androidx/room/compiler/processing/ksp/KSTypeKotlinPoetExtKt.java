package dagger.spi.shaded.androidx.room.compiler.processing.ksp;

import com.google.devtools.ksp.processing.Resolver;
import com.google.devtools.ksp.symbol.KSDeclaration;
import com.google.devtools.ksp.symbol.KSName;
import com.google.devtools.ksp.symbol.KSType;
import com.google.devtools.ksp.symbol.KSTypeAlias;
import com.google.devtools.ksp.symbol.KSTypeArgument;
import com.google.devtools.ksp.symbol.KSTypeParameter;
import com.google.devtools.ksp.symbol.KSTypeReference;
import com.google.devtools.ksp.symbol.Variance;
import com.squareup.kotlinpoet.ClassName;
import com.squareup.kotlinpoet.KModifier;
import com.squareup.kotlinpoet.ParameterizedTypeName;
import com.squareup.kotlinpoet.TypeName;
import com.squareup.kotlinpoet.TypeNames;
import com.squareup.kotlinpoet.TypeVariableName;
import com.squareup.kotlinpoet.WildcardTypeName;
import dagger.spi.shaded.androidx.room.compiler.processing.util.ErrorMessagesKt;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.text.StringsKt__IndentKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: KSTypeKotlinPoetExt.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a6\u0010\t\u001a\u00060\u0005j\u0002`\u0006*\u0004\u0018\u00010\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u001a\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u0003j\u0002`\u0007H\u0002\u001a4\u0010\t\u001a\u00060\u0005j\u0002`\u0006*\u00020\n2\u0006\u0010\u0002\u001a\u00020\u00012\u001a\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u0003j\u0002`\u0007H\u0002\u001a4\u0010\t\u001a\u00060\u0005j\u0002`\u0006*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u00012\u001a\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u0003j\u0002`\u0007H\u0002\u001a\u0018\u0010\t\u001a\u00060\u0005j\u0002`\u0006*\u00020\f2\u0006\u0010\u0002\u001a\u00020\u0001H\u0000\u001a4\u0010\t\u001a\u00060\u0005j\u0002`\u0006*\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00012\u001a\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u0003j\u0002`\u0007H\u0002\u001a\u0018\u0010\t\u001a\u00060\u0005j\u0002`\u0006*\u00020\r2\u0006\u0010\u0002\u001a\u00020\u0001H\u0000\u001a4\u0010\t\u001a\u00060\u0005j\u0002`\u0006*\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00012\u001a\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u0003j\u0002`\u0007H\u0002\u001a&\u0010\u0014\u001a\u00060\u0012j\u0002`\u00132\u0006\u0010\u000f\u001a\u00020\u000e2\u0010\u0010\u0011\u001a\f\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u0010H\u0002\"\u001e\u0010\u0017\u001a\u00060\u0015j\u0002`\u00168\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"#\u0010!\u001a\n \u001c*\u0004\u0018\u00010\u001b0\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"#\u0010&\u001a\n \u001c*\u0004\u0018\u00010\"0\"8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b#\u0010\u001e\u001a\u0004\b$\u0010%*(\b\u0002\u0010(\"\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002`\u0006`'2\u0012\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u0003¨\u0006)"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSTypeReference;", "Lcom/google/devtools/ksp/processing/Resolver;", "resolver", "Ljava/util/LinkedHashMap;", "Lcom/google/devtools/ksp/symbol/KSName;", "Lcom/squareup/kotlinpoet/TypeName;", "Lcom/squareup/kotlinpoet/javapoet/KTypeName;", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KTypeArgumentTypeLookup;", "typeArgumentTypeLookup", "asKTypeName", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "Lcom/google/devtools/ksp/symbol/KSTypeParameter;", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "Lcom/google/devtools/ksp/symbol/KSType;", "", "name", "", "bounds", "Lcom/squareup/kotlinpoet/TypeVariableName;", "Lcom/squareup/kotlinpoet/javapoet/KTypeVariableName;", "createModifiableTypeVariableName", "Lcom/squareup/kotlinpoet/ClassName;", "Lcom/squareup/kotlinpoet/javapoet/KClassName;", "ERROR_KTYPE_NAME", "Lcom/squareup/kotlinpoet/ClassName;", "getERROR_KTYPE_NAME", "()Lcom/squareup/kotlinpoet/ClassName;", "", "kotlin.jvm.PlatformType", "typeVarNameCompanionInstance$delegate", "Lkotlin/Lazy;", "getTypeVarNameCompanionInstance", "()Ljava/lang/Object;", "typeVarNameCompanionInstance", "Ljava/lang/reflect/Method;", "typeVarNameFactoryMethod$delegate", "getTypeVarNameFactoryMethod", "()Ljava/lang/reflect/Method;", "typeVarNameFactoryMethod", "Lkotlin/collections/LinkedHashMap;", "KTypeArgumentTypeLookup", "room-compiler-processing"}, k = 2, mv = {1, 7, 1})
public final class KSTypeKotlinPoetExtKt {
    private static final ClassName ERROR_KTYPE_NAME = new ClassName("error", "NonExistentClass");
    private static final Lazy typeVarNameCompanionInstance$delegate = LazyKt__LazyJVMKt.lazy(new Function0<Object>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KSTypeKotlinPoetExtKt$typeVarNameCompanionInstance$2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            try {
                Field declaredField = TypeVariableName.class.getDeclaredField("Companion");
                declaredField.trySetAccessible();
                return declaredField.get(null);
            } catch (NoSuchFieldException e) {
                throw new IllegalStateException(StringsKt__IndentKt.trimIndent("\n            Room couldn't find the field it is looking for in KotlinPoet.\n            Please file a bug at " + ErrorMessagesKt.getISSUE_TRACKER_LINK() + ".\n            "), e);
            }
        }
    });
    private static final Lazy typeVarNameFactoryMethod$delegate = LazyKt__LazyJVMKt.lazy(new Function0<Method>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KSTypeKotlinPoetExtKt$typeVarNameFactoryMethod$2
        @Override // kotlin.jvm.functions.Function0
        public final Method invoke() {
            try {
                Method[] methods = KSTypeKotlinPoetExtKt.getTypeVarNameCompanionInstance().getClass().getMethods();
                Intrinsics.checkNotNullExpressionValue(methods, "typeVarNameCompanionInstance::class.java.methods");
                for (Method method : methods) {
                    String name = method.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "it.name");
                    boolean z = true;
                    if (!StringsKt__StringsJVMKt.startsWith$default(name, "of", false, 2, null) || method.getParameterCount() != 3 || !Intrinsics.areEqual(method.getParameters()[0].getType(), String.class) || !Intrinsics.areEqual(method.getParameters()[1].getType(), List.class) || !Intrinsics.areEqual(method.getParameters()[2].getType(), KModifier.class)) {
                        z = false;
                    }
                    if (z) {
                        method.trySetAccessible();
                        return method;
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            } catch (NoSuchElementException unused) {
                throw new IllegalStateException(StringsKt__IndentKt.trimIndent("\n            Room couldn't find the method it is looking for in KotlinPoet.\n            Please file a bug at " + ErrorMessagesKt.getISSUE_TRACKER_LINK() + ".\n            "));
            }
        }
    });

    /* JADX INFO: compiled from: KSTypeKotlinPoetExt.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Variance.values().length];
            try {
                iArr[Variance.CONTRAVARIANT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Variance.COVARIANT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Variance.STAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TypeName asKTypeName(KSTypeReference kSTypeReference, Resolver resolver, LinkedHashMap<KSName, TypeName> linkedHashMap) {
        if (kSTypeReference == null) {
            return ERROR_KTYPE_NAME;
        }
        return asKTypeName(kSTypeReference.getResolved(), resolver, linkedHashMap);
    }

    private static final TypeName asKTypeName(KSDeclaration kSDeclaration, Resolver resolver, LinkedHashMap<KSName, TypeName> linkedHashMap) {
        String strAsString;
        if (kSDeclaration instanceof KSTypeAlias) {
            return asKTypeName(((KSTypeAlias) kSDeclaration).getType(), resolver, linkedHashMap);
        }
        if (kSDeclaration instanceof KSTypeParameter) {
            return asKTypeName((KSTypeParameter) kSDeclaration, resolver, linkedHashMap);
        }
        KSName qualifiedName = kSDeclaration.getQualifiedName();
        if (qualifiedName == null || (strAsString = qualifiedName.asString()) == null) {
            return ERROR_KTYPE_NAME;
        }
        String normalizedPackageName = KSTypeExtKt.getNormalizedPackageName(kSDeclaration);
        if (!Intrinsics.areEqual(normalizedPackageName, "")) {
            strAsString = strAsString.substring(normalizedPackageName.length() + 1);
            Intrinsics.checkNotNullExpressionValue(strAsString, "this as java.lang.String).substring(startIndex)");
        }
        List listSplit$default = StringsKt__StringsKt.split$default((CharSequence) strAsString, new char[]{'.'}, false, 0, 6, (Object) null);
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.add(CollectionsKt___CollectionsKt.first(listSplit$default));
        Object[] array = CollectionsKt___CollectionsKt.drop(listSplit$default, 1).toArray(new String[0]);
        Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        spreadBuilder.addSpread(array);
        return new ClassName(normalizedPackageName, (String[]) spreadBuilder.toArray(new String[spreadBuilder.size()]));
    }

    private static final TypeName asKTypeName(KSTypeParameter kSTypeParameter, final Resolver resolver, final LinkedHashMap<KSName, TypeName> linkedHashMap) throws IllegalAccessException, InvocationTargetException {
        TypeName typeName = linkedHashMap.get(kSTypeParameter.getName());
        if (typeName != null) {
            return typeName;
        }
        ClassName className = TypeNames.ANY;
        List listMutableListOf = CollectionsKt__CollectionsKt.mutableListOf(TypeName.copy$default(className, true, null, 2, null));
        TypeVariableName typeVariableNameCreateModifiableTypeVariableName = createModifiableTypeVariableName(kSTypeParameter.getName().asString(), listMutableListOf);
        linkedHashMap.put(kSTypeParameter.getName(), typeVariableNameCreateModifiableTypeVariableName);
        List list = SequencesKt___SequencesKt.toList(SequencesKt___SequencesKt.map(kSTypeParameter.getBounds(), new Function1<KSTypeReference, TypeName>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KSTypeKotlinPoetExtKt$asKTypeName$resolvedBounds$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final TypeName invoke(KSTypeReference it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return KSTypeKotlinPoetExtKt.asKTypeName(it, resolver, (LinkedHashMap<KSName, TypeName>) linkedHashMap);
            }
        }));
        if (!list.isEmpty()) {
            listMutableListOf.addAll(list);
            listMutableListOf.remove(TypeName.copy$default(className, true, null, 2, null));
        }
        linkedHashMap.remove(kSTypeParameter.getName());
        return typeVariableNameCreateModifiableTypeVariableName;
    }

    public static final TypeName asKTypeName(KSTypeArgument kSTypeArgument, Resolver resolver) {
        Intrinsics.checkNotNullParameter(kSTypeArgument, "<this>");
        Intrinsics.checkNotNullParameter(resolver, "resolver");
        return asKTypeName(kSTypeArgument, resolver, (LinkedHashMap<KSName, TypeName>) new LinkedHashMap());
    }

    private static final TypeName asKTypeName$resolveTypeName(KSTypeArgument kSTypeArgument, Resolver resolver, LinkedHashMap<KSName, TypeName> linkedHashMap) {
        return asKTypeName(kSTypeArgument.getType(), resolver, linkedHashMap);
    }

    private static final TypeName asKTypeName(KSTypeArgument kSTypeArgument, Resolver resolver, LinkedHashMap<KSName, TypeName> linkedHashMap) {
        int i = WhenMappings.$EnumSwitchMapping$0[kSTypeArgument.getVariance().ordinal()];
        if (i == 1) {
            return WildcardTypeName.INSTANCE.consumerOf(asKTypeName$resolveTypeName(kSTypeArgument, resolver, linkedHashMap));
        }
        if (i == 2) {
            return WildcardTypeName.INSTANCE.producerOf(asKTypeName$resolveTypeName(kSTypeArgument, resolver, linkedHashMap));
        }
        if (i == 3) {
            return TypeNames.STAR;
        }
        if (KSTypeExtKt.hasJvmWildcardAnnotation(kSTypeArgument)) {
            return WildcardTypeName.INSTANCE.consumerOf(asKTypeName$resolveTypeName(kSTypeArgument, resolver, linkedHashMap));
        }
        return asKTypeName$resolveTypeName(kSTypeArgument, resolver, linkedHashMap);
    }

    public static final TypeName asKTypeName(KSType kSType, Resolver resolver) {
        Intrinsics.checkNotNullParameter(kSType, "<this>");
        Intrinsics.checkNotNullParameter(resolver, "resolver");
        return asKTypeName(kSType, resolver, (LinkedHashMap<KSName, TypeName>) new LinkedHashMap());
    }

    private static final TypeName asKTypeName(KSType kSType, Resolver resolver, LinkedHashMap<KSName, TypeName> linkedHashMap) {
        TypeName typeNameAsKTypeName;
        if ((!kSType.getArguments().isEmpty()) && !resolver.isJavaRawType(kSType)) {
            List<KSTypeArgument> arguments = kSType.getArguments();
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arguments, 10));
            Iterator<T> it = arguments.iterator();
            while (it.hasNext()) {
                arrayList.add(asKTypeName((KSTypeArgument) it.next(), resolver, linkedHashMap));
            }
            TypeName typeNameAsKTypeName2 = asKTypeName(kSType.getDeclaration(), resolver, linkedHashMap);
            if (!(typeNameAsKTypeName2 instanceof ClassName)) {
                throw new IllegalStateException(("Unexpected type name for KSType: " + typeNameAsKTypeName2).toString());
            }
            typeNameAsKTypeName = ParameterizedTypeName.INSTANCE.get((ClassName) typeNameAsKTypeName2, arrayList);
        } else {
            typeNameAsKTypeName = asKTypeName(kSType.getDeclaration(), resolver, linkedHashMap);
        }
        return TypeName.copy$default(typeNameAsKTypeName, kSType.isMarkedNullable(), null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object getTypeVarNameCompanionInstance() {
        return typeVarNameCompanionInstance$delegate.getValue();
    }

    private static final Method getTypeVarNameFactoryMethod() {
        return (Method) typeVarNameFactoryMethod$delegate.getValue();
    }

    private static final TypeVariableName createModifiableTypeVariableName(String str, List<? extends TypeName> list) throws IllegalAccessException, InvocationTargetException {
        try {
            TypeVariableName typeVariableNameNewInstance = KTypeVariableNameFactory.newInstance(str, list);
            Intrinsics.checkNotNullExpressionValue(typeVariableNameNewInstance, "{\n        KTypeVariableN…tance(name, bounds)\n    }");
            return typeVariableNameNewInstance;
        } catch (NoSuchMethodError unused) {
            Object objInvoke = getTypeVarNameFactoryMethod().invoke(getTypeVarNameCompanionInstance(), str, list, null);
            Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type com.squareup.kotlinpoet.TypeVariableName{ com.squareup.kotlinpoet.javapoet.TypeAliasesKt.KTypeVariableName }");
            return (TypeVariableName) objInvoke;
        }
    }
}
