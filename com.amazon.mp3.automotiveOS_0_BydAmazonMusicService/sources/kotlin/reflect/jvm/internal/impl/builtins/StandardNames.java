package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassKind;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;

/* JADX INFO: compiled from: StandardNames.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class StandardNames {

    @JvmField
    public static final FqName ANNOTATION_PACKAGE_FQ_NAME;

    @JvmField
    public static final FqName BUILT_INS_PACKAGE_FQ_NAME;

    @JvmField
    public static final Set<FqName> BUILT_INS_PACKAGE_FQ_NAMES;

    @JvmField
    public static final Name BUILT_INS_PACKAGE_NAME;

    @JvmField
    public static final Name CHAR_CODE;

    @JvmField
    public static final FqName COLLECTIONS_PACKAGE_FQ_NAME;

    @JvmField
    public static final FqName CONTINUATION_INTERFACE_FQ_NAME;

    @JvmField
    public static final FqName COROUTINES_INTRINSICS_PACKAGE_FQ_NAME;

    @JvmField
    public static final FqName COROUTINES_JVM_INTERNAL_PACKAGE_FQ_NAME;

    @JvmField
    public static final FqName COROUTINES_PACKAGE_FQ_NAME;

    @JvmField
    public static final Name ENUM_VALUES;

    @JvmField
    public static final Name ENUM_VALUE_OF;
    public static final StandardNames INSTANCE = new StandardNames();

    @JvmField
    public static final FqName KOTLIN_REFLECT_FQ_NAME;

    @JvmField
    public static final List<String> PREFIXES;

    @JvmField
    public static final FqName RANGES_PACKAGE_FQ_NAME;

    @JvmField
    public static final FqName RESULT_FQ_NAME;

    @JvmField
    public static final FqName TEXT_PACKAGE_FQ_NAME;

    private StandardNames() {
    }

    static {
        Name nameIdentifier = Name.identifier("values");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(\"values\")");
        ENUM_VALUES = nameIdentifier;
        Name nameIdentifier2 = Name.identifier("valueOf");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier2, "identifier(\"valueOf\")");
        ENUM_VALUE_OF = nameIdentifier2;
        Name nameIdentifier3 = Name.identifier("code");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier3, "identifier(\"code\")");
        CHAR_CODE = nameIdentifier3;
        FqName fqName = new FqName("kotlin.coroutines");
        COROUTINES_PACKAGE_FQ_NAME = fqName;
        COROUTINES_JVM_INTERNAL_PACKAGE_FQ_NAME = new FqName("kotlin.coroutines.jvm.internal");
        COROUTINES_INTRINSICS_PACKAGE_FQ_NAME = new FqName("kotlin.coroutines.intrinsics");
        FqName fqNameChild = fqName.child(Name.identifier("Continuation"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild, "COROUTINES_PACKAGE_FQ_NA…entifier(\"Continuation\"))");
        CONTINUATION_INTERFACE_FQ_NAME = fqNameChild;
        RESULT_FQ_NAME = new FqName("kotlin.Result");
        FqName fqName2 = new FqName("kotlin.reflect");
        KOTLIN_REFLECT_FQ_NAME = fqName2;
        PREFIXES = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"KProperty", "KMutableProperty", "KFunction", "KSuspendFunction"});
        Name nameIdentifier4 = Name.identifier("kotlin");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier4, "identifier(\"kotlin\")");
        BUILT_INS_PACKAGE_NAME = nameIdentifier4;
        FqName fqName3 = FqName.topLevel(nameIdentifier4);
        Intrinsics.checkNotNullExpressionValue(fqName3, "topLevel(BUILT_INS_PACKAGE_NAME)");
        BUILT_INS_PACKAGE_FQ_NAME = fqName3;
        FqName fqNameChild2 = fqName3.child(Name.identifier("annotation"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild2, "BUILT_INS_PACKAGE_FQ_NAM…identifier(\"annotation\"))");
        ANNOTATION_PACKAGE_FQ_NAME = fqNameChild2;
        FqName fqNameChild3 = fqName3.child(Name.identifier("collections"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild3, "BUILT_INS_PACKAGE_FQ_NAM…dentifier(\"collections\"))");
        COLLECTIONS_PACKAGE_FQ_NAME = fqNameChild3;
        FqName fqNameChild4 = fqName3.child(Name.identifier("ranges"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild4, "BUILT_INS_PACKAGE_FQ_NAM…ame.identifier(\"ranges\"))");
        RANGES_PACKAGE_FQ_NAME = fqNameChild4;
        FqName fqNameChild5 = fqName3.child(Name.identifier("text"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild5, "BUILT_INS_PACKAGE_FQ_NAM…(Name.identifier(\"text\"))");
        TEXT_PACKAGE_FQ_NAME = fqNameChild5;
        FqName fqNameChild6 = fqName3.child(Name.identifier("internal"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild6, "BUILT_INS_PACKAGE_FQ_NAM…e.identifier(\"internal\"))");
        BUILT_INS_PACKAGE_FQ_NAMES = SetsKt__SetsKt.setOf((Object[]) new FqName[]{fqName3, fqNameChild3, fqNameChild4, fqNameChild2, fqName2, fqNameChild6, fqName});
    }

    /* JADX INFO: compiled from: StandardNames.kt */
    public static final class FqNames {
        public static final FqNames INSTANCE;

        @JvmField
        public static final FqNameUnsafe _boolean;

        @JvmField
        public static final FqNameUnsafe _byte;

        @JvmField
        public static final FqNameUnsafe _char;

        @JvmField
        public static final FqNameUnsafe _double;

        @JvmField
        public static final FqNameUnsafe _enum;

        @JvmField
        public static final FqNameUnsafe _float;

        @JvmField
        public static final FqNameUnsafe _int;

        @JvmField
        public static final FqNameUnsafe _long;

        @JvmField
        public static final FqNameUnsafe _short;

        @JvmField
        public static final FqName annotation;

        @JvmField
        public static final FqName annotationRetention;

        @JvmField
        public static final FqName annotationTarget;

        @JvmField
        public static final FqNameUnsafe any;

        @JvmField
        public static final FqNameUnsafe array;

        @JvmField
        public static final Map<FqNameUnsafe, PrimitiveType> arrayClassFqNameToPrimitiveType;

        @JvmField
        public static final FqNameUnsafe charSequence;

        @JvmField
        public static final FqNameUnsafe cloneable;

        @JvmField
        public static final FqName collection;

        @JvmField
        public static final FqName comparable;

        @JvmField
        public static final FqName deprecated;

        @JvmField
        public static final FqName deprecatedSinceKotlin;

        @JvmField
        public static final FqName deprecationLevel;

        @JvmField
        public static final FqName extensionFunctionType;

        @JvmField
        public static final Map<FqNameUnsafe, PrimitiveType> fqNameToPrimitiveType;

        @JvmField
        public static final FqNameUnsafe functionSupertype;

        @JvmField
        public static final FqNameUnsafe intRange;

        @JvmField
        public static final FqName iterable;

        @JvmField
        public static final FqName iterator;

        @JvmField
        public static final FqNameUnsafe kCallable;

        @JvmField
        public static final FqNameUnsafe kClass;

        @JvmField
        public static final FqNameUnsafe kDeclarationContainer;

        @JvmField
        public static final FqNameUnsafe kMutableProperty0;

        @JvmField
        public static final FqNameUnsafe kMutableProperty1;

        @JvmField
        public static final FqNameUnsafe kMutableProperty2;

        @JvmField
        public static final FqNameUnsafe kMutablePropertyFqName;

        @JvmField
        public static final ClassId kProperty;

        @JvmField
        public static final FqNameUnsafe kProperty0;

        @JvmField
        public static final FqNameUnsafe kProperty1;

        @JvmField
        public static final FqNameUnsafe kProperty2;

        @JvmField
        public static final FqNameUnsafe kPropertyFqName;

        @JvmField
        public static final FqName list;

        @JvmField
        public static final FqName listIterator;

        @JvmField
        public static final FqNameUnsafe longRange;

        @JvmField
        public static final FqName map;

        @JvmField
        public static final FqName mapEntry;

        @JvmField
        public static final FqName mustBeDocumented;

        @JvmField
        public static final FqName mutableCollection;

        @JvmField
        public static final FqName mutableIterable;

        @JvmField
        public static final FqName mutableIterator;

        @JvmField
        public static final FqName mutableList;

        @JvmField
        public static final FqName mutableListIterator;

        @JvmField
        public static final FqName mutableMap;

        @JvmField
        public static final FqName mutableMapEntry;

        @JvmField
        public static final FqName mutableSet;

        @JvmField
        public static final FqNameUnsafe nothing;

        @JvmField
        public static final FqNameUnsafe number;

        @JvmField
        public static final FqName parameterName;

        @JvmField
        public static final Set<Name> primitiveArrayTypeShortNames;

        @JvmField
        public static final Set<Name> primitiveTypeShortNames;

        @JvmField
        public static final FqName publishedApi;

        @JvmField
        public static final FqName repeatable;

        @JvmField
        public static final FqName replaceWith;

        @JvmField
        public static final FqName retention;

        @JvmField
        public static final FqName set;

        @JvmField
        public static final FqNameUnsafe string;

        @JvmField
        public static final FqName suppress;

        @JvmField
        public static final FqName target;

        @JvmField
        public static final FqName throwable;

        @JvmField
        public static final ClassId uByte;

        @JvmField
        public static final FqName uByteArrayFqName;

        @JvmField
        public static final FqName uByteFqName;

        @JvmField
        public static final ClassId uInt;

        @JvmField
        public static final FqName uIntArrayFqName;

        @JvmField
        public static final FqName uIntFqName;

        @JvmField
        public static final ClassId uLong;

        @JvmField
        public static final FqName uLongArrayFqName;

        @JvmField
        public static final FqName uLongFqName;

        @JvmField
        public static final ClassId uShort;

        @JvmField
        public static final FqName uShortArrayFqName;

        @JvmField
        public static final FqName uShortFqName;

        @JvmField
        public static final FqNameUnsafe unit;

        @JvmField
        public static final FqName unsafeVariance;

        private FqNames() {
        }

        static {
            FqNames fqNames = new FqNames();
            INSTANCE = fqNames;
            any = fqNames.fqNameUnsafe("Any");
            nothing = fqNames.fqNameUnsafe("Nothing");
            cloneable = fqNames.fqNameUnsafe("Cloneable");
            suppress = fqNames.fqName("Suppress");
            unit = fqNames.fqNameUnsafe("Unit");
            charSequence = fqNames.fqNameUnsafe("CharSequence");
            string = fqNames.fqNameUnsafe("String");
            array = fqNames.fqNameUnsafe("Array");
            _boolean = fqNames.fqNameUnsafe("Boolean");
            _char = fqNames.fqNameUnsafe("Char");
            _byte = fqNames.fqNameUnsafe("Byte");
            _short = fqNames.fqNameUnsafe("Short");
            _int = fqNames.fqNameUnsafe("Int");
            _long = fqNames.fqNameUnsafe("Long");
            _float = fqNames.fqNameUnsafe("Float");
            _double = fqNames.fqNameUnsafe("Double");
            number = fqNames.fqNameUnsafe("Number");
            _enum = fqNames.fqNameUnsafe("Enum");
            functionSupertype = fqNames.fqNameUnsafe("Function");
            throwable = fqNames.fqName("Throwable");
            comparable = fqNames.fqName("Comparable");
            intRange = fqNames.rangesFqName("IntRange");
            longRange = fqNames.rangesFqName("LongRange");
            deprecated = fqNames.fqName("Deprecated");
            deprecatedSinceKotlin = fqNames.fqName("DeprecatedSinceKotlin");
            deprecationLevel = fqNames.fqName("DeprecationLevel");
            replaceWith = fqNames.fqName("ReplaceWith");
            extensionFunctionType = fqNames.fqName("ExtensionFunctionType");
            parameterName = fqNames.fqName("ParameterName");
            annotation = fqNames.fqName("Annotation");
            target = fqNames.annotationName("Target");
            annotationTarget = fqNames.annotationName("AnnotationTarget");
            annotationRetention = fqNames.annotationName("AnnotationRetention");
            retention = fqNames.annotationName("Retention");
            repeatable = fqNames.annotationName("Repeatable");
            mustBeDocumented = fqNames.annotationName("MustBeDocumented");
            unsafeVariance = fqNames.fqName("UnsafeVariance");
            publishedApi = fqNames.fqName("PublishedApi");
            iterator = fqNames.collectionsFqName("Iterator");
            iterable = fqNames.collectionsFqName("Iterable");
            collection = fqNames.collectionsFqName("Collection");
            list = fqNames.collectionsFqName("List");
            listIterator = fqNames.collectionsFqName("ListIterator");
            set = fqNames.collectionsFqName("Set");
            FqName fqNameCollectionsFqName = fqNames.collectionsFqName("Map");
            map = fqNameCollectionsFqName;
            FqName fqNameChild = fqNameCollectionsFqName.child(Name.identifier("Entry"));
            Intrinsics.checkNotNullExpressionValue(fqNameChild, "map.child(Name.identifier(\"Entry\"))");
            mapEntry = fqNameChild;
            mutableIterator = fqNames.collectionsFqName("MutableIterator");
            mutableIterable = fqNames.collectionsFqName("MutableIterable");
            mutableCollection = fqNames.collectionsFqName("MutableCollection");
            mutableList = fqNames.collectionsFqName("MutableList");
            mutableListIterator = fqNames.collectionsFqName("MutableListIterator");
            mutableSet = fqNames.collectionsFqName("MutableSet");
            FqName fqNameCollectionsFqName2 = fqNames.collectionsFqName("MutableMap");
            mutableMap = fqNameCollectionsFqName2;
            FqName fqNameChild2 = fqNameCollectionsFqName2.child(Name.identifier("MutableEntry"));
            Intrinsics.checkNotNullExpressionValue(fqNameChild2, "mutableMap.child(Name.identifier(\"MutableEntry\"))");
            mutableMapEntry = fqNameChild2;
            kClass = reflect("KClass");
            kCallable = reflect("KCallable");
            kProperty0 = reflect("KProperty0");
            kProperty1 = reflect("KProperty1");
            kProperty2 = reflect("KProperty2");
            kMutableProperty0 = reflect("KMutableProperty0");
            kMutableProperty1 = reflect("KMutableProperty1");
            kMutableProperty2 = reflect("KMutableProperty2");
            FqNameUnsafe fqNameUnsafeReflect = reflect("KProperty");
            kPropertyFqName = fqNameUnsafeReflect;
            kMutablePropertyFqName = reflect("KMutableProperty");
            ClassId classId = ClassId.topLevel(fqNameUnsafeReflect.toSafe());
            Intrinsics.checkNotNullExpressionValue(classId, "topLevel(kPropertyFqName.toSafe())");
            kProperty = classId;
            kDeclarationContainer = reflect("KDeclarationContainer");
            FqName fqName = fqNames.fqName("UByte");
            uByteFqName = fqName;
            FqName fqName2 = fqNames.fqName("UShort");
            uShortFqName = fqName2;
            FqName fqName3 = fqNames.fqName("UInt");
            uIntFqName = fqName3;
            FqName fqName4 = fqNames.fqName("ULong");
            uLongFqName = fqName4;
            ClassId classId2 = ClassId.topLevel(fqName);
            Intrinsics.checkNotNullExpressionValue(classId2, "topLevel(uByteFqName)");
            uByte = classId2;
            ClassId classId3 = ClassId.topLevel(fqName2);
            Intrinsics.checkNotNullExpressionValue(classId3, "topLevel(uShortFqName)");
            uShort = classId3;
            ClassId classId4 = ClassId.topLevel(fqName3);
            Intrinsics.checkNotNullExpressionValue(classId4, "topLevel(uIntFqName)");
            uInt = classId4;
            ClassId classId5 = ClassId.topLevel(fqName4);
            Intrinsics.checkNotNullExpressionValue(classId5, "topLevel(uLongFqName)");
            uLong = classId5;
            uByteArrayFqName = fqNames.fqName("UByteArray");
            uShortArrayFqName = fqNames.fqName("UShortArray");
            uIntArrayFqName = fqNames.fqName("UIntArray");
            uLongArrayFqName = fqNames.fqName("ULongArray");
            HashSet hashSetNewHashSetWithExpectedSize = CollectionsKt.newHashSetWithExpectedSize(PrimitiveType.values().length);
            PrimitiveType[] primitiveTypeArrValues = PrimitiveType.values();
            int length = primitiveTypeArrValues.length;
            int i = 0;
            int i2 = 0;
            while (i2 < length) {
                PrimitiveType primitiveType = primitiveTypeArrValues[i2];
                i2++;
                hashSetNewHashSetWithExpectedSize.add(primitiveType.getTypeName());
            }
            primitiveTypeShortNames = hashSetNewHashSetWithExpectedSize;
            HashSet hashSetNewHashSetWithExpectedSize2 = CollectionsKt.newHashSetWithExpectedSize(PrimitiveType.values().length);
            PrimitiveType[] primitiveTypeArrValues2 = PrimitiveType.values();
            int length2 = primitiveTypeArrValues2.length;
            int i3 = 0;
            while (i3 < length2) {
                PrimitiveType primitiveType2 = primitiveTypeArrValues2[i3];
                i3++;
                hashSetNewHashSetWithExpectedSize2.add(primitiveType2.getArrayTypeName());
            }
            primitiveArrayTypeShortNames = hashSetNewHashSetWithExpectedSize2;
            HashMap mapNewHashMapWithExpectedSize = CollectionsKt.newHashMapWithExpectedSize(PrimitiveType.values().length);
            PrimitiveType[] primitiveTypeArrValues3 = PrimitiveType.values();
            int length3 = primitiveTypeArrValues3.length;
            int i4 = 0;
            while (i4 < length3) {
                PrimitiveType primitiveType3 = primitiveTypeArrValues3[i4];
                i4++;
                FqNames fqNames2 = INSTANCE;
                String strAsString = primitiveType3.getTypeName().asString();
                Intrinsics.checkNotNullExpressionValue(strAsString, "primitiveType.typeName.asString()");
                mapNewHashMapWithExpectedSize.put(fqNames2.fqNameUnsafe(strAsString), primitiveType3);
            }
            fqNameToPrimitiveType = mapNewHashMapWithExpectedSize;
            HashMap mapNewHashMapWithExpectedSize2 = CollectionsKt.newHashMapWithExpectedSize(PrimitiveType.values().length);
            PrimitiveType[] primitiveTypeArrValues4 = PrimitiveType.values();
            int length4 = primitiveTypeArrValues4.length;
            while (i < length4) {
                PrimitiveType primitiveType4 = primitiveTypeArrValues4[i];
                i++;
                FqNames fqNames3 = INSTANCE;
                String strAsString2 = primitiveType4.getArrayTypeName().asString();
                Intrinsics.checkNotNullExpressionValue(strAsString2, "primitiveType.arrayTypeName.asString()");
                mapNewHashMapWithExpectedSize2.put(fqNames3.fqNameUnsafe(strAsString2), primitiveType4);
            }
            arrayClassFqNameToPrimitiveType = mapNewHashMapWithExpectedSize2;
        }

        private final FqNameUnsafe fqNameUnsafe(String str) {
            FqNameUnsafe unsafe = fqName(str).toUnsafe();
            Intrinsics.checkNotNullExpressionValue(unsafe, "fqName(simpleName).toUnsafe()");
            return unsafe;
        }

        private final FqName fqName(String str) {
            FqName fqNameChild = StandardNames.BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier(str));
            Intrinsics.checkNotNullExpressionValue(fqNameChild, "BUILT_INS_PACKAGE_FQ_NAM…e.identifier(simpleName))");
            return fqNameChild;
        }

        private final FqName collectionsFqName(String str) {
            FqName fqNameChild = StandardNames.COLLECTIONS_PACKAGE_FQ_NAME.child(Name.identifier(str));
            Intrinsics.checkNotNullExpressionValue(fqNameChild, "COLLECTIONS_PACKAGE_FQ_N…e.identifier(simpleName))");
            return fqNameChild;
        }

        private final FqNameUnsafe rangesFqName(String str) {
            FqNameUnsafe unsafe = StandardNames.RANGES_PACKAGE_FQ_NAME.child(Name.identifier(str)).toUnsafe();
            Intrinsics.checkNotNullExpressionValue(unsafe, "RANGES_PACKAGE_FQ_NAME.c…r(simpleName)).toUnsafe()");
            return unsafe;
        }

        @JvmStatic
        public static final FqNameUnsafe reflect(String simpleName) {
            Intrinsics.checkNotNullParameter(simpleName, "simpleName");
            FqNameUnsafe unsafe = StandardNames.KOTLIN_REFLECT_FQ_NAME.child(Name.identifier(simpleName)).toUnsafe();
            Intrinsics.checkNotNullExpressionValue(unsafe, "KOTLIN_REFLECT_FQ_NAME.c…r(simpleName)).toUnsafe()");
            return unsafe;
        }

        private final FqName annotationName(String str) {
            FqName fqNameChild = StandardNames.ANNOTATION_PACKAGE_FQ_NAME.child(Name.identifier(str));
            Intrinsics.checkNotNullExpressionValue(fqNameChild, "ANNOTATION_PACKAGE_FQ_NA…e.identifier(simpleName))");
            return fqNameChild;
        }
    }

    @JvmStatic
    public static final String getFunctionName(int i) {
        return Intrinsics.stringPlus("Function", Integer.valueOf(i));
    }

    @JvmStatic
    public static final ClassId getFunctionClassId(int i) {
        return new ClassId(BUILT_INS_PACKAGE_FQ_NAME, Name.identifier(getFunctionName(i)));
    }

    @JvmStatic
    public static final String getSuspendFunctionName(int i) {
        return Intrinsics.stringPlus(FunctionClassKind.SuspendFunction.getClassNamePrefix(), Integer.valueOf(i));
    }

    @JvmStatic
    public static final boolean isPrimitiveArray(FqNameUnsafe arrayFqName) {
        Intrinsics.checkNotNullParameter(arrayFqName, "arrayFqName");
        return FqNames.arrayClassFqNameToPrimitiveType.get(arrayFqName) != null;
    }

    @JvmStatic
    public static final FqName getPrimitiveFqName(PrimitiveType primitiveType) {
        Intrinsics.checkNotNullParameter(primitiveType, "primitiveType");
        FqName fqNameChild = BUILT_INS_PACKAGE_FQ_NAME.child(primitiveType.getTypeName());
        Intrinsics.checkNotNullExpressionValue(fqNameChild, "BUILT_INS_PACKAGE_FQ_NAM…d(primitiveType.typeName)");
        return fqNameChild;
    }
}
