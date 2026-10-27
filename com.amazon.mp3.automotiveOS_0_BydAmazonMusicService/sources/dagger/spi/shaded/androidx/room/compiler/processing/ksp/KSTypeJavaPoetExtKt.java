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
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import com.squareup.javapoet.WildcardTypeName;
import dagger.spi.shaded.androidx.room.compiler.processing.JavaPoetExtKt;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.JvmDescriptorUtilsKt;
import dagger.spi.shaded.androidx.room.compiler.processing.util.ErrorMessagesKt;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.text.StringsKt__IndentKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: KSTypeJavaPoetExt.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a6\u0010\t\u001a\u00060\u0005j\u0002`\u0006*\u0004\u0018\u00010\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u001a\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u0003j\u0002`\u0007H\u0002\u001a4\u0010\t\u001a\u00060\u0005j\u0002`\u0006*\u00020\n2\u0006\u0010\u0002\u001a\u00020\u00012\u001a\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u0003j\u0002`\u0007H\u0002\u001a\u0018\u0010\t\u001a\u00060\u0005j\u0002`\u0006*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u0001H\u0000\u001a4\u0010\t\u001a\u00060\u0005j\u0002`\u0006*\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00012\u001a\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u0003j\u0002`\u0007H\u0002\u001a4\u0010\t\u001a\u00060\u0005j\u0002`\u0006*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u00012\u001a\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u0003j\u0002`\u0007H\u0002\u001a\u0018\u0010\t\u001a\u00060\u0005j\u0002`\u0006*\u00020\r2\u0006\u0010\u0002\u001a\u00020\u0001H\u0000\u001a4\u0010\t\u001a\u00060\u0005j\u0002`\u0006*\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00012\u001a\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u0003j\u0002`\u0007H\u0002\u001a \u0010\u000f\u001a\f\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u000e*\f\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u000eH\u0002\u001a&\u0010\u0015\u001a\u00060\u0013j\u0002`\u00142\u0006\u0010\u0011\u001a\u00020\u00102\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u000eH\u0002\"\"\u0010\u0018\u001a\n \u0017*\u0004\u0018\u00010\u00160\u00168\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"S\u0010!\u001a:\u0012\u0016\u0012\u0014 \u0017*\n\u0018\u00010\u0013j\u0004\u0018\u0001`\u00140\u0013j\u0002`\u0014 \u0017*\u001c\u0012\u0016\u0012\u0014 \u0017*\n\u0018\u00010\u0013j\u0004\u0018\u0001`\u00140\u0013j\u0002`\u0014\u0018\u00010\u001c0\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 *(\b\u0002\u0010#\"\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002`\u0006`\"2\u0012\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u0003¨\u0006$"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSTypeReference;", "Lcom/google/devtools/ksp/processing/Resolver;", "resolver", "Ljava/util/LinkedHashMap;", "Lcom/google/devtools/ksp/symbol/KSName;", "Lcom/squareup/javapoet/TypeName;", "Lcom/squareup/kotlinpoet/javapoet/JTypeName;", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/JTypeArgumentTypeLookup;", "typeArgumentTypeLookup", "asJTypeName", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "Lcom/google/devtools/ksp/symbol/KSTypeParameter;", "Lcom/google/devtools/ksp/symbol/KSType;", "", "convertToSuspendSignature", "", "name", "bounds", "Lcom/squareup/javapoet/TypeVariableName;", "Lcom/squareup/kotlinpoet/javapoet/JTypeVariableName;", "createModifiableTypeVariableName", "Lcom/squareup/javapoet/ClassName;", "kotlin.jvm.PlatformType", "ERROR_JTYPE_NAME", "Lcom/squareup/javapoet/ClassName;", "getERROR_JTYPE_NAME", "()Lcom/squareup/javapoet/ClassName;", "Ljava/lang/reflect/Constructor;", "typeVarNameConstructor$delegate", "Lkotlin/Lazy;", "getTypeVarNameConstructor", "()Ljava/lang/reflect/Constructor;", "typeVarNameConstructor", "Lkotlin/collections/LinkedHashMap;", "JTypeArgumentTypeLookup", "room-compiler-processing"}, k = 2, mv = {1, 7, 1})
public final class KSTypeJavaPoetExtKt {
    private static final ClassName ERROR_JTYPE_NAME = ClassName.get("error", "NonExistentClass", new String[0]);
    private static final Lazy typeVarNameConstructor$delegate = LazyKt__LazyJVMKt.lazy(new Function0<Constructor<TypeVariableName>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KSTypeJavaPoetExtKt$typeVarNameConstructor$2
        @Override // kotlin.jvm.functions.Function0
        public final Constructor<TypeVariableName> invoke() {
            try {
                Constructor<TypeVariableName> declaredConstructor = TypeVariableName.class.getDeclaredConstructor(String.class, List.class);
                declaredConstructor.trySetAccessible();
                return declaredConstructor;
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException(StringsKt__IndentKt.trimIndent("\n            Room couldn't find the constructor it is looking for in JavaPoet.\n            Please file a bug at " + ErrorMessagesKt.getISSUE_TRACKER_LINK() + ".\n            "), e);
            }
        }
    });

    /* JADX INFO: compiled from: KSTypeJavaPoetExt.kt */
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

    public static final ClassName getERROR_JTYPE_NAME() {
        return ERROR_JTYPE_NAME;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TypeName asJTypeName(KSTypeReference kSTypeReference, Resolver resolver, LinkedHashMap<KSName, TypeName> linkedHashMap) {
        if (kSTypeReference == null) {
            ClassName className = ERROR_JTYPE_NAME;
            Intrinsics.checkNotNullExpressionValue(className, "{\n        ERROR_JTYPE_NAME\n    }");
            return className;
        }
        return asJTypeName(kSTypeReference.getResolved(), resolver, linkedHashMap);
    }

    private static final TypeName asJTypeName(KSDeclaration kSDeclaration, Resolver resolver, LinkedHashMap<KSName, TypeName> linkedHashMap) {
        String strAsString;
        if (kSDeclaration instanceof KSTypeAlias) {
            return asJTypeName(((KSTypeAlias) kSDeclaration).getType(), resolver, linkedHashMap);
        }
        if (kSDeclaration instanceof KSTypeParameter) {
            return asJTypeName((KSTypeParameter) kSDeclaration, resolver, linkedHashMap);
        }
        KSName qualifiedName = kSDeclaration.getQualifiedName();
        if (qualifiedName == null || (strAsString = qualifiedName.asString()) == null) {
            ClassName ERROR_JTYPE_NAME2 = ERROR_JTYPE_NAME;
            Intrinsics.checkNotNullExpressionValue(ERROR_JTYPE_NAME2, "ERROR_JTYPE_NAME");
            return ERROR_JTYPE_NAME2;
        }
        String strMapToJvmSignature = resolver.mapToJvmSignature(kSDeclaration);
        if (strMapToJvmSignature != null && (!StringsKt__StringsJVMKt.isBlank(strMapToJvmSignature))) {
            return JvmDescriptorUtilsKt.typeNameFromJvmSignature(strMapToJvmSignature);
        }
        String normalizedPackageName = KSTypeExtKt.getNormalizedPackageName(kSDeclaration);
        if (!Intrinsics.areEqual(normalizedPackageName, "")) {
            strAsString = strAsString.substring(normalizedPackageName.length() + 1);
            Intrinsics.checkNotNullExpressionValue(strAsString, "this as java.lang.String).substring(startIndex)");
        }
        List listSplit$default = StringsKt__StringsKt.split$default((CharSequence) strAsString, new char[]{'.'}, false, 0, 6, (Object) null);
        String str = (String) CollectionsKt___CollectionsKt.first(listSplit$default);
        Object[] array = CollectionsKt___CollectionsKt.drop(listSplit$default, 1).toArray(new String[0]);
        Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        String[] strArr = (String[]) array;
        ClassName className = ClassName.get(normalizedPackageName, str, (String[]) Arrays.copyOf(strArr, strArr.length));
        Intrinsics.checkNotNullExpressionValue(className, "get(pkg, shortNames.firs….drop(1).toTypedArray()))");
        return className;
    }

    public static final TypeName asJTypeName(KSTypeArgument kSTypeArgument, Resolver resolver) {
        Intrinsics.checkNotNullParameter(kSTypeArgument, "<this>");
        Intrinsics.checkNotNullParameter(resolver, "resolver");
        return asJTypeName(kSTypeArgument, resolver, (LinkedHashMap<KSName, TypeName>) new LinkedHashMap());
    }

    private static final TypeName asJTypeName(KSTypeParameter kSTypeParameter, final Resolver resolver, final LinkedHashMap<KSName, TypeName> linkedHashMap) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        TypeName typeName = linkedHashMap.get(kSTypeParameter.getName());
        if (typeName != null) {
            return typeName;
        }
        ArrayList arrayList = new ArrayList();
        TypeVariableName typeVariableNameCreateModifiableTypeVariableName = createModifiableTypeVariableName(kSTypeParameter.getName().asString(), arrayList);
        linkedHashMap.put(kSTypeParameter.getName(), typeVariableNameCreateModifiableTypeVariableName);
        List list = SequencesKt___SequencesKt.toList(SequencesKt___SequencesKt.map(kSTypeParameter.getBounds(), new Function1<KSTypeReference, TypeName>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KSTypeJavaPoetExtKt$asJTypeName$resolvedBounds$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final TypeName invoke(KSTypeReference it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return JavaPoetExtKt.tryBox(KSTypeJavaPoetExtKt.asJTypeName(it, resolver, (LinkedHashMap<KSName, TypeName>) linkedHashMap));
            }
        }));
        if (!list.isEmpty()) {
            arrayList.addAll(list);
            arrayList.remove(TypeName.OBJECT);
        }
        linkedHashMap.remove(kSTypeParameter.getName());
        return typeVariableNameCreateModifiableTypeVariableName;
    }

    private static final TypeName asJTypeName$resolveTypeName(KSTypeArgument kSTypeArgument, Resolver resolver, LinkedHashMap<KSName, TypeName> linkedHashMap) {
        return JavaPoetExtKt.tryBox(asJTypeName(kSTypeArgument.getType(), resolver, linkedHashMap));
    }

    private static final TypeName asJTypeName(KSTypeArgument kSTypeArgument, Resolver resolver, LinkedHashMap<KSName, TypeName> linkedHashMap) {
        TypeName typeNameAsJTypeName$resolveTypeName;
        int i = WhenMappings.$EnumSwitchMapping$0[kSTypeArgument.getVariance().ordinal()];
        if (i == 1) {
            WildcardTypeName wildcardTypeNameSupertypeOf = WildcardTypeName.supertypeOf(asJTypeName$resolveTypeName(kSTypeArgument, resolver, linkedHashMap));
            Intrinsics.checkNotNullExpressionValue(wildcardTypeNameSupertypeOf, "supertypeOf(resolveTypeName())");
            return wildcardTypeNameSupertypeOf;
        }
        if (i == 2) {
            WildcardTypeName wildcardTypeNameSubtypeOf = WildcardTypeName.subtypeOf(asJTypeName$resolveTypeName(kSTypeArgument, resolver, linkedHashMap));
            Intrinsics.checkNotNullExpressionValue(wildcardTypeNameSubtypeOf, "subtypeOf(resolveTypeName())");
            return wildcardTypeNameSubtypeOf;
        }
        if (i == 3) {
            WildcardTypeName wildcardTypeNameSubtypeOf2 = WildcardTypeName.subtypeOf(TypeName.OBJECT);
            Intrinsics.checkNotNullExpressionValue(wildcardTypeNameSubtypeOf2, "{\n            JWildcardT…ypeName.OBJECT)\n        }");
            return wildcardTypeNameSubtypeOf2;
        }
        if (KSTypeExtKt.hasJvmWildcardAnnotation(kSTypeArgument)) {
            typeNameAsJTypeName$resolveTypeName = WildcardTypeName.subtypeOf(asJTypeName$resolveTypeName(kSTypeArgument, resolver, linkedHashMap));
        } else {
            typeNameAsJTypeName$resolveTypeName = asJTypeName$resolveTypeName(kSTypeArgument, resolver, linkedHashMap);
        }
        Intrinsics.checkNotNullExpressionValue(typeNameAsJTypeName$resolveTypeName, "{\n            if (hasJvm…)\n            }\n        }");
        return typeNameAsJTypeName$resolveTypeName;
    }

    public static final TypeName asJTypeName(KSType kSType, Resolver resolver) {
        Intrinsics.checkNotNullParameter(kSType, "<this>");
        Intrinsics.checkNotNullParameter(resolver, "resolver");
        return asJTypeName(kSType, resolver, (LinkedHashMap<KSName, TypeName>) new LinkedHashMap());
    }

    private static final TypeName asJTypeName(KSType kSType, Resolver resolver, LinkedHashMap<KSName, TypeName> linkedHashMap) {
        TypeName typeNameOf;
        if ((!kSType.getArguments().isEmpty()) && !resolver.isJavaRawType(kSType)) {
            List<KSTypeArgument> arguments = kSType.getArguments();
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arguments, 10));
            Iterator<T> it = arguments.iterator();
            while (it.hasNext()) {
                arrayList.add(asJTypeName((KSTypeArgument) it.next(), resolver, linkedHashMap));
            }
            List arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                arrayList2.add(JavaPoetExtKt.tryBox((TypeName) it2.next()));
            }
            if (kSType.isSuspendFunctionType()) {
                arrayList2 = convertToSuspendSignature(arrayList2);
            }
            Object[] array = arrayList2.toArray(new TypeName[0]);
            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            TypeName[] typeNameArr = (TypeName[]) array;
            TypeName typeNameTryBox = JavaPoetExtKt.tryBox(asJTypeName(kSType.getDeclaration(), resolver, linkedHashMap));
            if (typeNameTryBox instanceof ArrayTypeName) {
                typeNameOf = ArrayTypeName.of((TypeName) ArraysKt___ArraysKt.single(typeNameArr));
            } else if (typeNameTryBox instanceof ClassName) {
                typeNameOf = ParameterizedTypeName.get((ClassName) typeNameTryBox, (TypeName[]) Arrays.copyOf(typeNameArr, typeNameArr.length));
            } else {
                throw new IllegalStateException(("Unexpected type name for KSType: " + typeNameTryBox).toString());
            }
            Intrinsics.checkNotNullExpressionValue(typeNameOf, "{\n        val args: Arra…ypeName\")\n        }\n    }");
            return typeNameOf;
        }
        return asJTypeName(kSType.getDeclaration(), resolver, linkedHashMap);
    }

    private static final List<TypeName> convertToSuspendSignature(List<? extends TypeName> list) {
        return CollectionsKt___CollectionsKt.plus((Collection) list.subList(0, list.size() - 1), (Iterable) CollectionsKt__CollectionsKt.listOf((Object[]) new WildcardTypeName[]{WildcardTypeName.supertypeOf(ParameterizedTypeName.get(ClassName.get((Class<?>) Continuation.class), WildcardTypeName.supertypeOf((TypeName) CollectionsKt___CollectionsKt.last((List) list)))), WildcardTypeName.subtypeOf(TypeName.OBJECT)}));
    }

    private static final Constructor<TypeVariableName> getTypeVarNameConstructor() {
        return (Constructor) typeVarNameConstructor$delegate.getValue();
    }

    private static final TypeVariableName createModifiableTypeVariableName(String str, List<? extends TypeName> list) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        TypeVariableName typeVariableNameNewInstance = getTypeVarNameConstructor().newInstance(str, list);
        Intrinsics.checkNotNull(typeVariableNameNewInstance, "null cannot be cast to non-null type com.squareup.javapoet.TypeVariableName{ com.squareup.kotlinpoet.javapoet.TypeAliasesKt.JTypeVariableName }");
        return typeVariableNameNewInstance;
    }
}
