package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;

/* JADX INFO: compiled from: UnsignedType.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class UnsignedTypes {
    public static final UnsignedTypes INSTANCE = new UnsignedTypes();
    private static final HashMap<ClassId, ClassId> arrayClassIdToUnsignedClassId;
    private static final Set<Name> arrayClassesShortNames;
    private static final Set<Name> unsignedArrayTypeNames;
    private static final HashMap<UnsignedArrayType, Name> unsignedArrayTypeToArrayCall;
    private static final HashMap<ClassId, ClassId> unsignedClassIdToArrayClassId;
    private static final Set<Name> unsignedTypeNames;

    private UnsignedTypes() {
    }

    static {
        UnsignedType[] unsignedTypeArrValues = UnsignedType.values();
        ArrayList arrayList = new ArrayList(unsignedTypeArrValues.length);
        int length = unsignedTypeArrValues.length;
        int i = 0;
        int i2 = 0;
        while (i2 < length) {
            UnsignedType unsignedType = unsignedTypeArrValues[i2];
            i2++;
            arrayList.add(unsignedType.getTypeName());
        }
        unsignedTypeNames = CollectionsKt___CollectionsKt.toSet(arrayList);
        UnsignedArrayType[] unsignedArrayTypeArrValues = UnsignedArrayType.values();
        ArrayList arrayList2 = new ArrayList(unsignedArrayTypeArrValues.length);
        int length2 = unsignedArrayTypeArrValues.length;
        int i3 = 0;
        while (i3 < length2) {
            UnsignedArrayType unsignedArrayType = unsignedArrayTypeArrValues[i3];
            i3++;
            arrayList2.add(unsignedArrayType.getTypeName());
        }
        unsignedArrayTypeNames = CollectionsKt___CollectionsKt.toSet(arrayList2);
        arrayClassIdToUnsignedClassId = new HashMap<>();
        unsignedClassIdToArrayClassId = new HashMap<>();
        unsignedArrayTypeToArrayCall = MapsKt__MapsKt.hashMapOf(TuplesKt.to(UnsignedArrayType.UBYTEARRAY, Name.identifier("ubyteArrayOf")), TuplesKt.to(UnsignedArrayType.USHORTARRAY, Name.identifier("ushortArrayOf")), TuplesKt.to(UnsignedArrayType.UINTARRAY, Name.identifier("uintArrayOf")), TuplesKt.to(UnsignedArrayType.ULONGARRAY, Name.identifier("ulongArrayOf")));
        UnsignedType[] unsignedTypeArrValues2 = UnsignedType.values();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int length3 = unsignedTypeArrValues2.length;
        int i4 = 0;
        while (i4 < length3) {
            UnsignedType unsignedType2 = unsignedTypeArrValues2[i4];
            i4++;
            linkedHashSet.add(unsignedType2.getArrayClassId().getShortClassName());
        }
        arrayClassesShortNames = linkedHashSet;
        UnsignedType[] unsignedTypeArrValues3 = UnsignedType.values();
        int length4 = unsignedTypeArrValues3.length;
        while (i < length4) {
            UnsignedType unsignedType3 = unsignedTypeArrValues3[i];
            i++;
            arrayClassIdToUnsignedClassId.put(unsignedType3.getArrayClassId(), unsignedType3.getClassId());
            unsignedClassIdToArrayClassId.put(unsignedType3.getClassId(), unsignedType3.getArrayClassId());
        }
    }

    public final boolean isShortNameOfUnsignedArray(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return arrayClassesShortNames.contains(name);
    }

    public final ClassId getUnsignedClassIdByArrayClassId(ClassId arrayClassId) {
        Intrinsics.checkNotNullParameter(arrayClassId, "arrayClassId");
        return arrayClassIdToUnsignedClassId.get(arrayClassId);
    }

    @JvmStatic
    public static final boolean isUnsignedType(KotlinType type) {
        ClassifierDescriptor classifierDescriptorMo4084getDeclarationDescriptor;
        Intrinsics.checkNotNullParameter(type, "type");
        if (TypeUtils.noExpectedType(type) || (classifierDescriptorMo4084getDeclarationDescriptor = type.getConstructor().mo4084getDeclarationDescriptor()) == null) {
            return false;
        }
        return INSTANCE.isUnsignedClass(classifierDescriptorMo4084getDeclarationDescriptor);
    }

    public final boolean isUnsignedClass(DeclarationDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        DeclarationDescriptor containingDeclaration = descriptor.getContainingDeclaration();
        return (containingDeclaration instanceof PackageFragmentDescriptor) && Intrinsics.areEqual(((PackageFragmentDescriptor) containingDeclaration).getFqName(), StandardNames.BUILT_INS_PACKAGE_FQ_NAME) && unsignedTypeNames.contains(descriptor.getName());
    }
}
