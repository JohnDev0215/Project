package kotlinx.metadata.impl;

import java.util.Iterator;
import java.util.List;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.InconsistentKotlinMetadataException;
import kotlinx.metadata.KmClassVisitor;
import kotlinx.metadata.KmConstructorVisitor;
import kotlinx.metadata.KmContractVisitor;
import kotlinx.metadata.KmDeclarationContainerVisitor;
import kotlinx.metadata.KmEffectExpressionVisitor;
import kotlinx.metadata.KmEffectInvocationKind;
import kotlinx.metadata.KmEffectType;
import kotlinx.metadata.KmEffectVisitor;
import kotlinx.metadata.KmFunctionVisitor;
import kotlinx.metadata.KmPropertyVisitor;
import kotlinx.metadata.KmTypeAliasVisitor;
import kotlinx.metadata.KmTypeParameterVisitor;
import kotlinx.metadata.KmTypeVisitor;
import kotlinx.metadata.KmValueParameterVisitor;
import kotlinx.metadata.KmVariance;
import kotlinx.metadata.KmVersionRequirementLevel;
import kotlinx.metadata.KmVersionRequirementVersionKind;
import kotlinx.metadata.KmVersionRequirementVisitor;
import kotlinx.metadata.impl.extensions.MetadataExtensions;
import kotlinx.metadata.internal.metadata.ProtoBuf$Annotation;
import kotlinx.metadata.internal.metadata.ProtoBuf$Class;
import kotlinx.metadata.internal.metadata.ProtoBuf$Constructor;
import kotlinx.metadata.internal.metadata.ProtoBuf$Contract;
import kotlinx.metadata.internal.metadata.ProtoBuf$Effect;
import kotlinx.metadata.internal.metadata.ProtoBuf$EnumEntry;
import kotlinx.metadata.internal.metadata.ProtoBuf$Expression;
import kotlinx.metadata.internal.metadata.ProtoBuf$Function;
import kotlinx.metadata.internal.metadata.ProtoBuf$Property;
import kotlinx.metadata.internal.metadata.ProtoBuf$Type;
import kotlinx.metadata.internal.metadata.ProtoBuf$TypeAlias;
import kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameter;
import kotlinx.metadata.internal.metadata.ProtoBuf$TypeTable;
import kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameter;
import kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirement;
import kotlinx.metadata.internal.metadata.ProtoBuf$VersionRequirementTable;
import kotlinx.metadata.internal.metadata.deserialization.Flags;
import kotlinx.metadata.internal.metadata.deserialization.NameResolver;
import kotlinx.metadata.internal.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlinx.metadata.internal.metadata.deserialization.TypeTable;
import kotlinx.metadata.internal.metadata.deserialization.VersionRequirement;
import kotlinx.metadata.internal.metadata.deserialization.VersionRequirementTable;

/* JADX INFO: compiled from: readers.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a*\u0010\t\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u001a\u0016\u0010\r\u001a\u0004\u0018\u00010\f*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\nH\u0002\u001a>\u0010\u0015\u001a\u00020\b*\u00020\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00052\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00052\u0006\u0010\u000b\u001a\u00020\nH\u0002\u001a\u001c\u0010\t\u001a\u00020\b*\u00020\u00162\u0006\u0010\u0002\u001a\u00020\u00172\u0006\u0010\u000b\u001a\u00020\nH\u0002\u001a\u001c\u0010\t\u001a\u00020\b*\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\nH\u0002\u001a\u001a\u0010\t\u001a\u00020\b*\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\n\u001a\u001c\u0010\t\u001a\u00020\b*\u00020\u00132\u0006\u0010\u0002\u001a\u00020\u001b2\u0006\u0010\u0019\u001a\u00020\nH\u0002\u001a\u001c\u0010\t\u001a\u00020\b*\u00020\u001c2\u0006\u0010\u0002\u001a\u00020\u001d2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u001a\u001c\u0010\t\u001a\u00020\b*\u00020\u001e2\u0006\u0010\u0002\u001a\u00020\u001f2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u001a\u001c\u0010\t\u001a\u00020\b*\u00020\f2\u0006\u0010\u0002\u001a\u00020 2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u001a \u0010$\u001a\u00020\b2\u0006\u0010\"\u001a\u00020!2\u0006\u0010\u0002\u001a\u00020#2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u001a\u001c\u0010\t\u001a\u00020\b*\u00020%2\u0006\u0010\u0002\u001a\u00020&2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u001a\u001c\u0010\t\u001a\u00020\b*\u00020'2\u0006\u0010\u0002\u001a\u00020(2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u001a\u001c\u0010\t\u001a\u00020\b*\u00020)2\u0006\u0010\u0002\u001a\u00020*2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u001a\u000e\u0010,\u001a\u00060!j\u0002`+*\u00020\u0011\u001a\u000e\u0010-\u001a\u00060!j\u0002`+*\u00020\u0011\u001a\u0018\u0010/\u001a\u00060!j\u0002`+2\n\u0010.\u001a\u00060!j\u0002`+H\u0002\"\u001c\u00102\u001a\u00060!j\u0002`+*\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b0\u00101\"\u001c\u00105\u001a\u00060!j\u0002`+*\u00020\u001e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b3\u00104¨\u00066"}, d2 = {"Lkotlinx/metadata/internal/metadata/ProtoBuf$Class;", "Lkotlinx/metadata/KmClassVisitor;", "v", "Lkotlinx/metadata/internal/metadata/deserialization/NameResolver;", "strings", "", "", "contextExtensions", "", "accept", "Lkotlinx/metadata/impl/ReadContext;", "c", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Type;", "loadInlineClassUnderlyingType", "Lkotlinx/metadata/KmDeclarationContainerVisitor;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Function;", "functions", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Property;", "properties", "Lkotlinx/metadata/internal/metadata/ProtoBuf$TypeAlias;", "typeAliases", "visitDeclarations", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Constructor;", "Lkotlinx/metadata/KmConstructorVisitor;", "Lkotlinx/metadata/KmFunctionVisitor;", "outer", "Lkotlinx/metadata/KmPropertyVisitor;", "Lkotlinx/metadata/KmTypeAliasVisitor;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$ValueParameter;", "Lkotlinx/metadata/KmValueParameterVisitor;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$TypeParameter;", "Lkotlinx/metadata/KmTypeParameterVisitor;", "Lkotlinx/metadata/KmTypeVisitor;", "", "id", "Lkotlinx/metadata/KmVersionRequirementVisitor;", "acceptVersionRequirementVisitor", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Contract;", "Lkotlinx/metadata/KmContractVisitor;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Effect;", "Lkotlinx/metadata/KmEffectVisitor;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Expression;", "Lkotlinx/metadata/KmEffectExpressionVisitor;", "Lkotlinx/metadata/Flags;", "getPropertyGetterFlags", "getPropertySetterFlags", "flags", "getDefaultPropertyAccessorFlags", "getTypeFlags", "(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type;)I", "typeFlags", "getTypeParameterFlags", "(Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeParameter;)I", "typeParameterFlags", "kotlinx-metadata"}, k = 2, mv = {1, 7, 1})
public final class ReadersKt {

    /* JADX INFO: compiled from: readers.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;
        public static final /* synthetic */ int[] $EnumSwitchMapping$6;

        static {
            int[] iArr = new int[ProtoBuf$TypeParameter.Variance.values().length];
            try {
                iArr[ProtoBuf$TypeParameter.Variance.IN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ProtoBuf$TypeParameter.Variance.OUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ProtoBuf$TypeParameter.Variance.INV.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ProtoBuf$Type.Argument.Projection.values().length];
            try {
                iArr2[ProtoBuf$Type.Argument.Projection.IN.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[ProtoBuf$Type.Argument.Projection.OUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[ProtoBuf$Type.Argument.Projection.INV.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[ProtoBuf$Type.Argument.Projection.STAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[ProtoBuf$VersionRequirement.VersionKind.values().length];
            try {
                iArr3[ProtoBuf$VersionRequirement.VersionKind.LANGUAGE_VERSION.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr3[ProtoBuf$VersionRequirement.VersionKind.COMPILER_VERSION.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr3[ProtoBuf$VersionRequirement.VersionKind.API_VERSION.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            $EnumSwitchMapping$2 = iArr3;
            int[] iArr4 = new int[DeprecationLevel.values().length];
            try {
                iArr4[DeprecationLevel.WARNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr4[DeprecationLevel.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr4[DeprecationLevel.HIDDEN.ordinal()] = 3;
            } catch (NoSuchFieldError unused13) {
            }
            $EnumSwitchMapping$3 = iArr4;
            int[] iArr5 = new int[ProtoBuf$Effect.EffectType.values().length];
            try {
                iArr5[ProtoBuf$Effect.EffectType.RETURNS_CONSTANT.ordinal()] = 1;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr5[ProtoBuf$Effect.EffectType.CALLS.ordinal()] = 2;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr5[ProtoBuf$Effect.EffectType.RETURNS_NOT_NULL.ordinal()] = 3;
            } catch (NoSuchFieldError unused16) {
            }
            $EnumSwitchMapping$4 = iArr5;
            int[] iArr6 = new int[ProtoBuf$Effect.InvocationKind.values().length];
            try {
                iArr6[ProtoBuf$Effect.InvocationKind.AT_MOST_ONCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr6[ProtoBuf$Effect.InvocationKind.EXACTLY_ONCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr6[ProtoBuf$Effect.InvocationKind.AT_LEAST_ONCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused19) {
            }
            $EnumSwitchMapping$5 = iArr6;
            int[] iArr7 = new int[ProtoBuf$Expression.ConstantValue.values().length];
            try {
                iArr7[ProtoBuf$Expression.ConstantValue.TRUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr7[ProtoBuf$Expression.ConstantValue.FALSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr7[ProtoBuf$Expression.ConstantValue.NULL.ordinal()] = 3;
            } catch (NoSuchFieldError unused22) {
            }
            $EnumSwitchMapping$6 = iArr7;
        }
    }

    public static /* synthetic */ void accept$default(ProtoBuf$Class protoBuf$Class, KmClassVisitor kmClassVisitor, NameResolver nameResolver, List list, int i, Object obj) {
        if ((i & 4) != 0) {
            list = CollectionsKt__CollectionsKt.emptyList();
        }
        accept(protoBuf$Class, kmClassVisitor, nameResolver, list);
    }

    public static final void accept(ProtoBuf$Class protoBuf$Class, KmClassVisitor v, NameResolver strings, List<Object> contextExtensions) {
        KmTypeVisitor kmTypeVisitorVisitInlineClassUnderlyingType;
        KmVariance kmVariance;
        Intrinsics.checkNotNullParameter(protoBuf$Class, "<this>");
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(strings, "strings");
        Intrinsics.checkNotNullParameter(contextExtensions, "contextExtensions");
        ProtoBuf$TypeTable typeTable = protoBuf$Class.getTypeTable();
        Intrinsics.checkNotNullExpressionValue(typeTable, "typeTable");
        TypeTable typeTable2 = new TypeTable(typeTable);
        VersionRequirementTable.Companion companion = VersionRequirementTable.INSTANCE;
        ProtoBuf$VersionRequirementTable versionRequirementTable = protoBuf$Class.getVersionRequirementTable();
        Intrinsics.checkNotNullExpressionValue(versionRequirementTable, "versionRequirementTable");
        ReadContext readContext = new ReadContext(strings, typeTable2, companion.create(versionRequirementTable), null, contextExtensions, 8, null);
        List<ProtoBuf$TypeParameter> typeParameterList = protoBuf$Class.getTypeParameterList();
        Intrinsics.checkNotNullExpressionValue(typeParameterList, "typeParameterList");
        ReadContext readContextWithTypeParameters = readContext.withTypeParameters(typeParameterList);
        v.visit(protoBuf$Class.getFlags(), readContextWithTypeParameters.className(protoBuf$Class.getFqName()));
        for (ProtoBuf$TypeParameter typeParameter : protoBuf$Class.getTypeParameterList()) {
            Intrinsics.checkNotNullExpressionValue(typeParameter, "typeParameter");
            ProtoBuf$TypeParameter.Variance variance = typeParameter.getVariance();
            Intrinsics.checkNotNull(variance);
            int i = WhenMappings.$EnumSwitchMapping$0[variance.ordinal()];
            if (i == 1) {
                kmVariance = KmVariance.IN;
            } else if (i == 2) {
                kmVariance = KmVariance.OUT;
            } else {
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                kmVariance = KmVariance.INVARIANT;
            }
            KmTypeParameterVisitor kmTypeParameterVisitorVisitTypeParameter = v.visitTypeParameter(getTypeParameterFlags(typeParameter), readContextWithTypeParameters.get(typeParameter.getName()), typeParameter.getId(), kmVariance);
            if (kmTypeParameterVisitorVisitTypeParameter != null) {
                accept(typeParameter, kmTypeParameterVisitorVisitTypeParameter, readContextWithTypeParameters);
            }
        }
        for (ProtoBuf$Type protoBuf$Type : ProtoTypeTableUtilKt.supertypes(protoBuf$Class, readContextWithTypeParameters.getTypes())) {
            KmTypeVisitor kmTypeVisitorVisitSupertype = v.visitSupertype(getTypeFlags(protoBuf$Type));
            if (kmTypeVisitorVisitSupertype != null) {
                accept(protoBuf$Type, kmTypeVisitorVisitSupertype, readContextWithTypeParameters);
            }
        }
        for (ProtoBuf$Constructor constructor : protoBuf$Class.getConstructorList()) {
            KmConstructorVisitor kmConstructorVisitorVisitConstructor = v.visitConstructor(constructor.getFlags());
            if (kmConstructorVisitorVisitConstructor != null) {
                Intrinsics.checkNotNullExpressionValue(constructor, "constructor");
                accept(constructor, kmConstructorVisitorVisitConstructor, readContextWithTypeParameters);
            }
        }
        List<ProtoBuf$Function> functionList = protoBuf$Class.getFunctionList();
        Intrinsics.checkNotNullExpressionValue(functionList, "functionList");
        List<ProtoBuf$Property> propertyList = protoBuf$Class.getPropertyList();
        Intrinsics.checkNotNullExpressionValue(propertyList, "propertyList");
        List<ProtoBuf$TypeAlias> typeAliasList = protoBuf$Class.getTypeAliasList();
        Intrinsics.checkNotNullExpressionValue(typeAliasList, "typeAliasList");
        visitDeclarations(v, functionList, propertyList, typeAliasList, readContextWithTypeParameters);
        if (protoBuf$Class.hasCompanionObjectName()) {
            v.visitCompanionObject(readContextWithTypeParameters.get(protoBuf$Class.getCompanionObjectName()));
        }
        for (Integer nestedClassName : protoBuf$Class.getNestedClassNameList()) {
            Intrinsics.checkNotNullExpressionValue(nestedClassName, "nestedClassName");
            v.visitNestedClass(readContextWithTypeParameters.get(nestedClassName.intValue()));
        }
        for (ProtoBuf$EnumEntry protoBuf$EnumEntry : protoBuf$Class.getEnumEntryList()) {
            if (!protoBuf$EnumEntry.hasName()) {
                throw new InconsistentKotlinMetadataException("No name for EnumEntry", null, 2, null);
            }
            v.visitEnumEntry(readContextWithTypeParameters.get(protoBuf$EnumEntry.getName()));
        }
        for (Integer sealedSubclassFqName : protoBuf$Class.getSealedSubclassFqNameList()) {
            Intrinsics.checkNotNullExpressionValue(sealedSubclassFqName, "sealedSubclassFqName");
            v.visitSealedSubclass(readContextWithTypeParameters.className(sealedSubclassFqName.intValue()));
        }
        if (protoBuf$Class.hasInlineClassUnderlyingPropertyName()) {
            v.visitInlineClassUnderlyingPropertyName(readContextWithTypeParameters.get(protoBuf$Class.getInlineClassUnderlyingPropertyName()));
        }
        ProtoBuf$Type protoBuf$TypeLoadInlineClassUnderlyingType = loadInlineClassUnderlyingType(protoBuf$Class, readContextWithTypeParameters);
        if (protoBuf$TypeLoadInlineClassUnderlyingType != null && (kmTypeVisitorVisitInlineClassUnderlyingType = v.visitInlineClassUnderlyingType(getTypeFlags(protoBuf$TypeLoadInlineClassUnderlyingType))) != null) {
            accept(protoBuf$TypeLoadInlineClassUnderlyingType, kmTypeVisitorVisitInlineClassUnderlyingType, readContextWithTypeParameters);
        }
        for (ProtoBuf$Type protoBuf$Type2 : ProtoTypeTableUtilKt.contextReceiverTypes(protoBuf$Class, readContextWithTypeParameters.getTypes())) {
            KmTypeVisitor kmTypeVisitorVisitContextReceiverType = v.visitContextReceiverType(getTypeFlags(protoBuf$Type2));
            if (kmTypeVisitorVisitContextReceiverType != null) {
                accept(protoBuf$Type2, kmTypeVisitorVisitContextReceiverType, readContextWithTypeParameters);
            }
        }
        for (Integer versionRequirement : protoBuf$Class.getVersionRequirementList()) {
            KmVersionRequirementVisitor kmVersionRequirementVisitorVisitVersionRequirement = v.visitVersionRequirement();
            if (kmVersionRequirementVisitorVisitVersionRequirement != null) {
                Intrinsics.checkNotNullExpressionValue(versionRequirement, "versionRequirement");
                acceptVersionRequirementVisitor(versionRequirement.intValue(), kmVersionRequirementVisitorVisitVersionRequirement, readContextWithTypeParameters);
            }
        }
        Iterator<MetadataExtensions> it = readContextWithTypeParameters.getExtensions$kotlinx_metadata().iterator();
        while (it.hasNext()) {
            it.next().readClassExtensions(v, protoBuf$Class, readContextWithTypeParameters);
        }
        v.visitEnd();
    }

    private static final ProtoBuf$Type loadInlineClassUnderlyingType(ProtoBuf$Class protoBuf$Class, ReadContext readContext) {
        ProtoBuf$Type protoBuf$TypeInlineClassUnderlyingType = ProtoTypeTableUtilKt.inlineClassUnderlyingType(protoBuf$Class, readContext.getTypes());
        if (protoBuf$TypeInlineClassUnderlyingType != null) {
            return protoBuf$TypeInlineClassUnderlyingType;
        }
        if (!protoBuf$Class.hasInlineClassUnderlyingPropertyName()) {
            return null;
        }
        List<ProtoBuf$Property> propertyList = protoBuf$Class.getPropertyList();
        Intrinsics.checkNotNullExpressionValue(propertyList, "propertyList");
        Iterator<T> it = propertyList.iterator();
        Object obj = null;
        boolean z = false;
        while (true) {
            if (!it.hasNext()) {
                if (!z) {
                    break;
                }
            } else {
                Object next = it.next();
                ProtoBuf$Property it2 = (ProtoBuf$Property) next;
                Intrinsics.checkNotNullExpressionValue(it2, "it");
                if (ProtoTypeTableUtilKt.receiverType(it2, readContext.getTypes()) == null && Intrinsics.areEqual(readContext.get(it2.getName()), readContext.get(protoBuf$Class.getInlineClassUnderlyingPropertyName()))) {
                    if (z) {
                        break;
                    }
                    z = true;
                    obj = next;
                }
            }
        }
        obj = null;
        ProtoBuf$Property protoBuf$Property = (ProtoBuf$Property) obj;
        if (protoBuf$Property != null) {
            return ProtoTypeTableUtilKt.returnType(protoBuf$Property, readContext.getTypes());
        }
        return null;
    }

    private static final void visitDeclarations(KmDeclarationContainerVisitor kmDeclarationContainerVisitor, List<ProtoBuf$Function> list, List<ProtoBuf$Property> list2, List<ProtoBuf$TypeAlias> list3, ReadContext readContext) {
        for (ProtoBuf$Function protoBuf$Function : list) {
            KmFunctionVisitor kmFunctionVisitorVisitFunction = kmDeclarationContainerVisitor.visitFunction(protoBuf$Function.getFlags(), readContext.get(protoBuf$Function.getName()));
            if (kmFunctionVisitorVisitFunction != null) {
                accept(protoBuf$Function, kmFunctionVisitorVisitFunction, readContext);
            }
        }
        for (ProtoBuf$Property protoBuf$Property : list2) {
            KmPropertyVisitor kmPropertyVisitorVisitProperty = kmDeclarationContainerVisitor.visitProperty(protoBuf$Property.getFlags(), readContext.get(protoBuf$Property.getName()), getPropertyGetterFlags(protoBuf$Property), getPropertySetterFlags(protoBuf$Property));
            if (kmPropertyVisitorVisitProperty != null) {
                accept(protoBuf$Property, kmPropertyVisitorVisitProperty, readContext);
            }
        }
        for (ProtoBuf$TypeAlias protoBuf$TypeAlias : list3) {
            KmTypeAliasVisitor kmTypeAliasVisitorVisitTypeAlias = kmDeclarationContainerVisitor.visitTypeAlias(protoBuf$TypeAlias.getFlags(), readContext.get(protoBuf$TypeAlias.getName()));
            if (kmTypeAliasVisitorVisitTypeAlias != null) {
                accept(protoBuf$TypeAlias, kmTypeAliasVisitorVisitTypeAlias, readContext);
            }
        }
    }

    private static final void accept(ProtoBuf$Constructor protoBuf$Constructor, KmConstructorVisitor kmConstructorVisitor, ReadContext readContext) {
        for (ProtoBuf$ValueParameter parameter : protoBuf$Constructor.getValueParameterList()) {
            KmValueParameterVisitor kmValueParameterVisitorVisitValueParameter = kmConstructorVisitor.visitValueParameter(parameter.getFlags(), readContext.get(parameter.getName()));
            if (kmValueParameterVisitorVisitValueParameter != null) {
                Intrinsics.checkNotNullExpressionValue(parameter, "parameter");
                accept(parameter, kmValueParameterVisitorVisitValueParameter, readContext);
            }
        }
        for (Integer versionRequirement : protoBuf$Constructor.getVersionRequirementList()) {
            KmVersionRequirementVisitor kmVersionRequirementVisitorVisitVersionRequirement = kmConstructorVisitor.visitVersionRequirement();
            if (kmVersionRequirementVisitorVisitVersionRequirement != null) {
                Intrinsics.checkNotNullExpressionValue(versionRequirement, "versionRequirement");
                acceptVersionRequirementVisitor(versionRequirement.intValue(), kmVersionRequirementVisitorVisitVersionRequirement, readContext);
            }
        }
        Iterator<MetadataExtensions> it = readContext.getExtensions$kotlinx_metadata().iterator();
        while (it.hasNext()) {
            it.next().readConstructorExtensions(kmConstructorVisitor, protoBuf$Constructor, readContext);
        }
        kmConstructorVisitor.visitEnd();
    }

    private static final void accept(ProtoBuf$Function protoBuf$Function, KmFunctionVisitor kmFunctionVisitor, ReadContext readContext) {
        KmContractVisitor kmContractVisitorVisitContract;
        KmTypeVisitor kmTypeVisitorVisitReceiverParameterType;
        KmVariance kmVariance;
        List<ProtoBuf$TypeParameter> typeParameterList = protoBuf$Function.getTypeParameterList();
        Intrinsics.checkNotNullExpressionValue(typeParameterList, "typeParameterList");
        ReadContext readContextWithTypeParameters = readContext.withTypeParameters(typeParameterList);
        for (ProtoBuf$TypeParameter typeParameter : protoBuf$Function.getTypeParameterList()) {
            Intrinsics.checkNotNullExpressionValue(typeParameter, "typeParameter");
            ProtoBuf$TypeParameter.Variance variance = typeParameter.getVariance();
            Intrinsics.checkNotNull(variance);
            int i = WhenMappings.$EnumSwitchMapping$0[variance.ordinal()];
            if (i == 1) {
                kmVariance = KmVariance.IN;
            } else if (i == 2) {
                kmVariance = KmVariance.OUT;
            } else {
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                kmVariance = KmVariance.INVARIANT;
            }
            KmTypeParameterVisitor kmTypeParameterVisitorVisitTypeParameter = kmFunctionVisitor.visitTypeParameter(getTypeParameterFlags(typeParameter), readContextWithTypeParameters.get(typeParameter.getName()), typeParameter.getId(), kmVariance);
            if (kmTypeParameterVisitorVisitTypeParameter != null) {
                accept(typeParameter, kmTypeParameterVisitorVisitTypeParameter, readContextWithTypeParameters);
            }
        }
        ProtoBuf$Type protoBuf$TypeReceiverType = ProtoTypeTableUtilKt.receiverType(protoBuf$Function, readContextWithTypeParameters.getTypes());
        if (protoBuf$TypeReceiverType != null && (kmTypeVisitorVisitReceiverParameterType = kmFunctionVisitor.visitReceiverParameterType(getTypeFlags(protoBuf$TypeReceiverType))) != null) {
            accept(protoBuf$TypeReceiverType, kmTypeVisitorVisitReceiverParameterType, readContextWithTypeParameters);
        }
        for (ProtoBuf$Type protoBuf$Type : ProtoTypeTableUtilKt.contextReceiverTypes(protoBuf$Function, readContextWithTypeParameters.getTypes())) {
            KmTypeVisitor kmTypeVisitorVisitContextReceiverType = kmFunctionVisitor.visitContextReceiverType(getTypeFlags(protoBuf$Type));
            if (kmTypeVisitorVisitContextReceiverType != null) {
                accept(protoBuf$Type, kmTypeVisitorVisitContextReceiverType, readContextWithTypeParameters);
            }
        }
        for (ProtoBuf$ValueParameter parameter : protoBuf$Function.getValueParameterList()) {
            KmValueParameterVisitor kmValueParameterVisitorVisitValueParameter = kmFunctionVisitor.visitValueParameter(parameter.getFlags(), readContextWithTypeParameters.get(parameter.getName()));
            if (kmValueParameterVisitorVisitValueParameter != null) {
                Intrinsics.checkNotNullExpressionValue(parameter, "parameter");
                accept(parameter, kmValueParameterVisitorVisitValueParameter, readContextWithTypeParameters);
            }
        }
        ProtoBuf$Type protoBuf$TypeReturnType = ProtoTypeTableUtilKt.returnType(protoBuf$Function, readContextWithTypeParameters.getTypes());
        KmTypeVisitor kmTypeVisitorVisitReturnType = kmFunctionVisitor.visitReturnType(getTypeFlags(protoBuf$TypeReturnType));
        if (kmTypeVisitorVisitReturnType != null) {
            accept(protoBuf$TypeReturnType, kmTypeVisitorVisitReturnType, readContextWithTypeParameters);
        }
        if (protoBuf$Function.hasContract() && (kmContractVisitorVisitContract = kmFunctionVisitor.visitContract()) != null) {
            ProtoBuf$Contract contract = protoBuf$Function.getContract();
            Intrinsics.checkNotNullExpressionValue(contract, "contract");
            accept(contract, kmContractVisitorVisitContract, readContextWithTypeParameters);
        }
        for (Integer versionRequirement : protoBuf$Function.getVersionRequirementList()) {
            KmVersionRequirementVisitor kmVersionRequirementVisitorVisitVersionRequirement = kmFunctionVisitor.visitVersionRequirement();
            if (kmVersionRequirementVisitorVisitVersionRequirement != null) {
                Intrinsics.checkNotNullExpressionValue(versionRequirement, "versionRequirement");
                acceptVersionRequirementVisitor(versionRequirement.intValue(), kmVersionRequirementVisitorVisitVersionRequirement, readContextWithTypeParameters);
            }
        }
        Iterator<MetadataExtensions> it = readContextWithTypeParameters.getExtensions$kotlinx_metadata().iterator();
        while (it.hasNext()) {
            it.next().readFunctionExtensions(kmFunctionVisitor, protoBuf$Function, readContextWithTypeParameters);
        }
        kmFunctionVisitor.visitEnd();
    }

    public static final void accept(ProtoBuf$Property protoBuf$Property, KmPropertyVisitor v, ReadContext outer) {
        KmTypeVisitor kmTypeVisitorVisitReceiverParameterType;
        KmVariance kmVariance;
        Intrinsics.checkNotNullParameter(protoBuf$Property, "<this>");
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(outer, "outer");
        List<ProtoBuf$TypeParameter> typeParameterList = protoBuf$Property.getTypeParameterList();
        Intrinsics.checkNotNullExpressionValue(typeParameterList, "typeParameterList");
        ReadContext readContextWithTypeParameters = outer.withTypeParameters(typeParameterList);
        for (ProtoBuf$TypeParameter typeParameter : protoBuf$Property.getTypeParameterList()) {
            Intrinsics.checkNotNullExpressionValue(typeParameter, "typeParameter");
            ProtoBuf$TypeParameter.Variance variance = typeParameter.getVariance();
            Intrinsics.checkNotNull(variance);
            int i = WhenMappings.$EnumSwitchMapping$0[variance.ordinal()];
            if (i == 1) {
                kmVariance = KmVariance.IN;
            } else if (i == 2) {
                kmVariance = KmVariance.OUT;
            } else {
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                kmVariance = KmVariance.INVARIANT;
            }
            KmTypeParameterVisitor kmTypeParameterVisitorVisitTypeParameter = v.visitTypeParameter(getTypeParameterFlags(typeParameter), readContextWithTypeParameters.get(typeParameter.getName()), typeParameter.getId(), kmVariance);
            if (kmTypeParameterVisitorVisitTypeParameter != null) {
                accept(typeParameter, kmTypeParameterVisitorVisitTypeParameter, readContextWithTypeParameters);
            }
        }
        ProtoBuf$Type protoBuf$TypeReceiverType = ProtoTypeTableUtilKt.receiverType(protoBuf$Property, readContextWithTypeParameters.getTypes());
        if (protoBuf$TypeReceiverType != null && (kmTypeVisitorVisitReceiverParameterType = v.visitReceiverParameterType(getTypeFlags(protoBuf$TypeReceiverType))) != null) {
            accept(protoBuf$TypeReceiverType, kmTypeVisitorVisitReceiverParameterType, readContextWithTypeParameters);
        }
        for (ProtoBuf$Type protoBuf$Type : ProtoTypeTableUtilKt.contextReceiverTypes(protoBuf$Property, readContextWithTypeParameters.getTypes())) {
            KmTypeVisitor kmTypeVisitorVisitContextReceiverType = v.visitContextReceiverType(getTypeFlags(protoBuf$Type));
            if (kmTypeVisitorVisitContextReceiverType != null) {
                accept(protoBuf$Type, kmTypeVisitorVisitContextReceiverType, readContextWithTypeParameters);
            }
        }
        if (protoBuf$Property.hasSetterValueParameter()) {
            ProtoBuf$ValueParameter parameter = protoBuf$Property.getSetterValueParameter();
            KmValueParameterVisitor kmValueParameterVisitorVisitSetterParameter = v.visitSetterParameter(parameter.getFlags(), readContextWithTypeParameters.get(parameter.getName()));
            if (kmValueParameterVisitorVisitSetterParameter != null) {
                Intrinsics.checkNotNullExpressionValue(parameter, "parameter");
                accept(parameter, kmValueParameterVisitorVisitSetterParameter, readContextWithTypeParameters);
            }
        }
        ProtoBuf$Type protoBuf$TypeReturnType = ProtoTypeTableUtilKt.returnType(protoBuf$Property, readContextWithTypeParameters.getTypes());
        KmTypeVisitor kmTypeVisitorVisitReturnType = v.visitReturnType(getTypeFlags(protoBuf$TypeReturnType));
        if (kmTypeVisitorVisitReturnType != null) {
            accept(protoBuf$TypeReturnType, kmTypeVisitorVisitReturnType, readContextWithTypeParameters);
        }
        for (Integer versionRequirement : protoBuf$Property.getVersionRequirementList()) {
            KmVersionRequirementVisitor kmVersionRequirementVisitorVisitVersionRequirement = v.visitVersionRequirement();
            if (kmVersionRequirementVisitorVisitVersionRequirement != null) {
                Intrinsics.checkNotNullExpressionValue(versionRequirement, "versionRequirement");
                acceptVersionRequirementVisitor(versionRequirement.intValue(), kmVersionRequirementVisitorVisitVersionRequirement, readContextWithTypeParameters);
            }
        }
        Iterator<MetadataExtensions> it = readContextWithTypeParameters.getExtensions$kotlinx_metadata().iterator();
        while (it.hasNext()) {
            it.next().readPropertyExtensions(v, protoBuf$Property, readContextWithTypeParameters);
        }
        v.visitEnd();
    }

    private static final void accept(ProtoBuf$TypeAlias protoBuf$TypeAlias, KmTypeAliasVisitor kmTypeAliasVisitor, ReadContext readContext) {
        KmVariance kmVariance;
        List<ProtoBuf$TypeParameter> typeParameterList = protoBuf$TypeAlias.getTypeParameterList();
        Intrinsics.checkNotNullExpressionValue(typeParameterList, "typeParameterList");
        ReadContext readContextWithTypeParameters = readContext.withTypeParameters(typeParameterList);
        for (ProtoBuf$TypeParameter typeParameter : protoBuf$TypeAlias.getTypeParameterList()) {
            Intrinsics.checkNotNullExpressionValue(typeParameter, "typeParameter");
            ProtoBuf$TypeParameter.Variance variance = typeParameter.getVariance();
            Intrinsics.checkNotNull(variance);
            int i = WhenMappings.$EnumSwitchMapping$0[variance.ordinal()];
            if (i == 1) {
                kmVariance = KmVariance.IN;
            } else if (i == 2) {
                kmVariance = KmVariance.OUT;
            } else {
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                kmVariance = KmVariance.INVARIANT;
            }
            KmTypeParameterVisitor kmTypeParameterVisitorVisitTypeParameter = kmTypeAliasVisitor.visitTypeParameter(getTypeParameterFlags(typeParameter), readContextWithTypeParameters.get(typeParameter.getName()), typeParameter.getId(), kmVariance);
            if (kmTypeParameterVisitorVisitTypeParameter != null) {
                accept(typeParameter, kmTypeParameterVisitorVisitTypeParameter, readContextWithTypeParameters);
            }
        }
        ProtoBuf$Type protoBuf$TypeUnderlyingType = ProtoTypeTableUtilKt.underlyingType(protoBuf$TypeAlias, readContextWithTypeParameters.getTypes());
        KmTypeVisitor kmTypeVisitorVisitUnderlyingType = kmTypeAliasVisitor.visitUnderlyingType(getTypeFlags(protoBuf$TypeUnderlyingType));
        if (kmTypeVisitorVisitUnderlyingType != null) {
            accept(protoBuf$TypeUnderlyingType, kmTypeVisitorVisitUnderlyingType, readContextWithTypeParameters);
        }
        ProtoBuf$Type protoBuf$TypeExpandedType = ProtoTypeTableUtilKt.expandedType(protoBuf$TypeAlias, readContextWithTypeParameters.getTypes());
        KmTypeVisitor kmTypeVisitorVisitExpandedType = kmTypeAliasVisitor.visitExpandedType(getTypeFlags(protoBuf$TypeExpandedType));
        if (kmTypeVisitorVisitExpandedType != null) {
            accept(protoBuf$TypeExpandedType, kmTypeVisitorVisitExpandedType, readContextWithTypeParameters);
        }
        for (ProtoBuf$Annotation annotation : protoBuf$TypeAlias.getAnnotationList()) {
            Intrinsics.checkNotNullExpressionValue(annotation, "annotation");
            kmTypeAliasVisitor.visitAnnotation(ReadUtilsKt.readAnnotation(annotation, readContextWithTypeParameters.getStrings()));
        }
        for (Integer versionRequirement : protoBuf$TypeAlias.getVersionRequirementList()) {
            KmVersionRequirementVisitor kmVersionRequirementVisitorVisitVersionRequirement = kmTypeAliasVisitor.visitVersionRequirement();
            if (kmVersionRequirementVisitorVisitVersionRequirement != null) {
                Intrinsics.checkNotNullExpressionValue(versionRequirement, "versionRequirement");
                acceptVersionRequirementVisitor(versionRequirement.intValue(), kmVersionRequirementVisitorVisitVersionRequirement, readContextWithTypeParameters);
            }
        }
        Iterator<MetadataExtensions> it = readContextWithTypeParameters.getExtensions$kotlinx_metadata().iterator();
        while (it.hasNext()) {
            it.next().readTypeAliasExtensions(kmTypeAliasVisitor, protoBuf$TypeAlias, readContextWithTypeParameters);
        }
        kmTypeAliasVisitor.visitEnd();
    }

    private static final void accept(ProtoBuf$ValueParameter protoBuf$ValueParameter, KmValueParameterVisitor kmValueParameterVisitor, ReadContext readContext) {
        KmTypeVisitor kmTypeVisitorVisitVarargElementType;
        ProtoBuf$Type protoBuf$TypeType = ProtoTypeTableUtilKt.type(protoBuf$ValueParameter, readContext.getTypes());
        KmTypeVisitor kmTypeVisitorVisitType = kmValueParameterVisitor.visitType(getTypeFlags(protoBuf$TypeType));
        if (kmTypeVisitorVisitType != null) {
            accept(protoBuf$TypeType, kmTypeVisitorVisitType, readContext);
        }
        ProtoBuf$Type protoBuf$TypeVarargElementType = ProtoTypeTableUtilKt.varargElementType(protoBuf$ValueParameter, readContext.getTypes());
        if (protoBuf$TypeVarargElementType != null && (kmTypeVisitorVisitVarargElementType = kmValueParameterVisitor.visitVarargElementType(getTypeFlags(protoBuf$TypeVarargElementType))) != null) {
            accept(protoBuf$TypeVarargElementType, kmTypeVisitorVisitVarargElementType, readContext);
        }
        Iterator<MetadataExtensions> it = readContext.getExtensions$kotlinx_metadata().iterator();
        while (it.hasNext()) {
            it.next().readValueParameterExtensions(kmValueParameterVisitor, protoBuf$ValueParameter, readContext);
        }
        kmValueParameterVisitor.visitEnd();
    }

    private static final void accept(ProtoBuf$TypeParameter protoBuf$TypeParameter, KmTypeParameterVisitor kmTypeParameterVisitor, ReadContext readContext) {
        for (ProtoBuf$Type protoBuf$Type : ProtoTypeTableUtilKt.upperBounds(protoBuf$TypeParameter, readContext.getTypes())) {
            KmTypeVisitor kmTypeVisitorVisitUpperBound = kmTypeParameterVisitor.visitUpperBound(getTypeFlags(protoBuf$Type));
            if (kmTypeVisitorVisitUpperBound != null) {
                accept(protoBuf$Type, kmTypeVisitorVisitUpperBound, readContext);
            }
        }
        Iterator<MetadataExtensions> it = readContext.getExtensions$kotlinx_metadata().iterator();
        while (it.hasNext()) {
            it.next().readTypeParameterExtensions(kmTypeParameterVisitor, protoBuf$TypeParameter, readContext);
        }
        kmTypeParameterVisitor.visitEnd();
    }

    private static final void accept(ProtoBuf$Type protoBuf$Type, KmTypeVisitor kmTypeVisitor, ReadContext readContext) {
        KmTypeVisitor kmTypeVisitorVisitOuterType;
        KmTypeVisitor kmTypeVisitorVisitAbbreviatedType;
        KmVariance kmVariance;
        if (protoBuf$Type.hasClassName()) {
            kmTypeVisitor.visitClass(readContext.className(protoBuf$Type.getClassName()));
        } else if (protoBuf$Type.hasTypeAliasName()) {
            kmTypeVisitor.visitTypeAlias(readContext.className(protoBuf$Type.getTypeAliasName()));
        } else if (protoBuf$Type.hasTypeParameter()) {
            kmTypeVisitor.visitTypeParameter(protoBuf$Type.getTypeParameter());
        } else if (protoBuf$Type.hasTypeParameterName()) {
            Integer typeParameterId = readContext.getTypeParameterId(protoBuf$Type.getTypeParameterName());
            if (typeParameterId == null) {
                throw new InconsistentKotlinMetadataException("No type parameter id for " + readContext.get(protoBuf$Type.getTypeParameterName()), null, 2, null);
            }
            kmTypeVisitor.visitTypeParameter(typeParameterId.intValue());
        } else {
            throw new InconsistentKotlinMetadataException("No classifier (class, type alias or type parameter) recorded for Type", null, 2, null);
        }
        for (ProtoBuf$Type.Argument argument : protoBuf$Type.getArgumentList()) {
            ProtoBuf$Type.Argument.Projection projection = argument.getProjection();
            Intrinsics.checkNotNull(projection);
            int i = WhenMappings.$EnumSwitchMapping$1[projection.ordinal()];
            if (i == 1) {
                kmVariance = KmVariance.IN;
            } else if (i == 2) {
                kmVariance = KmVariance.OUT;
            } else if (i == 3) {
                kmVariance = KmVariance.INVARIANT;
            } else {
                if (i != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                kmVariance = null;
            }
            if (kmVariance != null) {
                Intrinsics.checkNotNullExpressionValue(argument, "argument");
                ProtoBuf$Type protoBuf$TypeType = ProtoTypeTableUtilKt.type(argument, readContext.getTypes());
                if (protoBuf$TypeType == null) {
                    throw new InconsistentKotlinMetadataException("No type argument for non-STAR projection in Type", null, 2, null);
                }
                KmTypeVisitor kmTypeVisitorVisitArgument = kmTypeVisitor.visitArgument(getTypeFlags(protoBuf$TypeType), kmVariance);
                if (kmTypeVisitorVisitArgument != null) {
                    accept(protoBuf$TypeType, kmTypeVisitorVisitArgument, readContext);
                }
            } else {
                kmTypeVisitor.visitStarProjection();
            }
        }
        ProtoBuf$Type protoBuf$TypeAbbreviatedType = ProtoTypeTableUtilKt.abbreviatedType(protoBuf$Type, readContext.getTypes());
        if (protoBuf$TypeAbbreviatedType != null && (kmTypeVisitorVisitAbbreviatedType = kmTypeVisitor.visitAbbreviatedType(getTypeFlags(protoBuf$TypeAbbreviatedType))) != null) {
            accept(protoBuf$TypeAbbreviatedType, kmTypeVisitorVisitAbbreviatedType, readContext);
        }
        ProtoBuf$Type protoBuf$TypeOuterType = ProtoTypeTableUtilKt.outerType(protoBuf$Type, readContext.getTypes());
        if (protoBuf$TypeOuterType != null && (kmTypeVisitorVisitOuterType = kmTypeVisitor.visitOuterType(getTypeFlags(protoBuf$TypeOuterType))) != null) {
            accept(protoBuf$TypeOuterType, kmTypeVisitorVisitOuterType, readContext);
        }
        ProtoBuf$Type protoBuf$TypeFlexibleUpperBound = ProtoTypeTableUtilKt.flexibleUpperBound(protoBuf$Type, readContext.getTypes());
        if (protoBuf$TypeFlexibleUpperBound != null) {
            KmTypeVisitor kmTypeVisitorVisitFlexibleTypeUpperBound = kmTypeVisitor.visitFlexibleTypeUpperBound(getTypeFlags(protoBuf$TypeFlexibleUpperBound), protoBuf$Type.hasFlexibleTypeCapabilitiesId() ? readContext.get(protoBuf$Type.getFlexibleTypeCapabilitiesId()) : null);
            if (kmTypeVisitorVisitFlexibleTypeUpperBound != null) {
                accept(protoBuf$TypeFlexibleUpperBound, kmTypeVisitorVisitFlexibleTypeUpperBound, readContext);
            }
        }
        Iterator<MetadataExtensions> it = readContext.getExtensions$kotlinx_metadata().iterator();
        while (it.hasNext()) {
            it.next().readTypeExtensions(kmTypeVisitor, protoBuf$Type, readContext);
        }
        kmTypeVisitor.visitEnd();
    }

    private static final void acceptVersionRequirementVisitor(int i, KmVersionRequirementVisitor kmVersionRequirementVisitor, ReadContext readContext) {
        KmVersionRequirementVersionKind kmVersionRequirementVersionKind;
        KmVersionRequirementLevel kmVersionRequirementLevel;
        VersionRequirement versionRequirementCreate = VersionRequirement.INSTANCE.create(i, readContext.getStrings(), readContext.getVersionRequirements());
        if (versionRequirementCreate == null) {
            throw new InconsistentKotlinMetadataException("No VersionRequirement with the given id in the table", null, 2, null);
        }
        int i2 = WhenMappings.$EnumSwitchMapping$2[versionRequirementCreate.getKind().ordinal()];
        if (i2 == 1) {
            kmVersionRequirementVersionKind = KmVersionRequirementVersionKind.LANGUAGE_VERSION;
        } else if (i2 == 2) {
            kmVersionRequirementVersionKind = KmVersionRequirementVersionKind.COMPILER_VERSION;
        } else {
            if (i2 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            kmVersionRequirementVersionKind = KmVersionRequirementVersionKind.API_VERSION;
        }
        int i3 = WhenMappings.$EnumSwitchMapping$3[versionRequirementCreate.getLevel().ordinal()];
        if (i3 == 1) {
            kmVersionRequirementLevel = KmVersionRequirementLevel.WARNING;
        } else if (i3 == 2) {
            kmVersionRequirementLevel = KmVersionRequirementLevel.ERROR;
        } else {
            if (i3 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            kmVersionRequirementLevel = KmVersionRequirementLevel.HIDDEN;
        }
        kmVersionRequirementVisitor.visit(kmVersionRequirementVersionKind, kmVersionRequirementLevel, versionRequirementCreate.getErrorCode(), versionRequirementCreate.getMessage());
        VersionRequirement.Version version = versionRequirementCreate.getVersion();
        kmVersionRequirementVisitor.visitVersion(version.getMajor(), version.getMinor(), version.getPatch());
        kmVersionRequirementVisitor.visitEnd();
    }

    private static final void accept(ProtoBuf$Contract protoBuf$Contract, KmContractVisitor kmContractVisitor, ReadContext readContext) {
        KmEffectType kmEffectType;
        KmEffectInvocationKind kmEffectInvocationKind;
        for (ProtoBuf$Effect effect : protoBuf$Contract.getEffectList()) {
            if (effect.hasEffectType()) {
                ProtoBuf$Effect.EffectType effectType = effect.getEffectType();
                Intrinsics.checkNotNull(effectType);
                int i = WhenMappings.$EnumSwitchMapping$4[effectType.ordinal()];
                if (i == 1) {
                    kmEffectType = KmEffectType.RETURNS_CONSTANT;
                } else if (i == 2) {
                    kmEffectType = KmEffectType.CALLS;
                } else {
                    if (i != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                    kmEffectType = KmEffectType.RETURNS_NOT_NULL;
                }
                if (effect.hasKind()) {
                    ProtoBuf$Effect.InvocationKind kind = effect.getKind();
                    Intrinsics.checkNotNull(kind);
                    int i2 = WhenMappings.$EnumSwitchMapping$5[kind.ordinal()];
                    if (i2 == 1) {
                        kmEffectInvocationKind = KmEffectInvocationKind.AT_MOST_ONCE;
                    } else if (i2 == 2) {
                        kmEffectInvocationKind = KmEffectInvocationKind.EXACTLY_ONCE;
                    } else {
                        if (i2 != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        kmEffectInvocationKind = KmEffectInvocationKind.AT_LEAST_ONCE;
                    }
                } else {
                    kmEffectInvocationKind = null;
                }
                KmEffectVisitor kmEffectVisitorVisitEffect = kmContractVisitor.visitEffect(kmEffectType, kmEffectInvocationKind);
                if (kmEffectVisitorVisitEffect != null) {
                    Intrinsics.checkNotNullExpressionValue(effect, "effect");
                    accept(effect, kmEffectVisitorVisitEffect, readContext);
                }
            }
        }
        kmContractVisitor.visitEnd();
    }

    private static final void accept(ProtoBuf$Effect protoBuf$Effect, KmEffectVisitor kmEffectVisitor, ReadContext readContext) {
        KmEffectExpressionVisitor kmEffectExpressionVisitorVisitConclusionOfConditionalEffect;
        for (ProtoBuf$Expression constructorArgument : protoBuf$Effect.getEffectConstructorArgumentList()) {
            KmEffectExpressionVisitor kmEffectExpressionVisitorVisitConstructorArgument = kmEffectVisitor.visitConstructorArgument();
            if (kmEffectExpressionVisitorVisitConstructorArgument != null) {
                Intrinsics.checkNotNullExpressionValue(constructorArgument, "constructorArgument");
                accept(constructorArgument, kmEffectExpressionVisitorVisitConstructorArgument, readContext);
            }
        }
        if (protoBuf$Effect.hasConclusionOfConditionalEffect() && (kmEffectExpressionVisitorVisitConclusionOfConditionalEffect = kmEffectVisitor.visitConclusionOfConditionalEffect()) != null) {
            ProtoBuf$Expression conclusionOfConditionalEffect = protoBuf$Effect.getConclusionOfConditionalEffect();
            Intrinsics.checkNotNullExpressionValue(conclusionOfConditionalEffect, "conclusionOfConditionalEffect");
            accept(conclusionOfConditionalEffect, kmEffectExpressionVisitorVisitConclusionOfConditionalEffect, readContext);
        }
        kmEffectVisitor.visitEnd();
    }

    private static final void accept(ProtoBuf$Expression protoBuf$Expression, KmEffectExpressionVisitor kmEffectExpressionVisitor, ReadContext readContext) {
        KmTypeVisitor kmTypeVisitorVisitIsInstanceType;
        Boolean bool = null;
        kmEffectExpressionVisitor.visit(protoBuf$Expression.getFlags(), protoBuf$Expression.hasValueParameterReference() ? Integer.valueOf(protoBuf$Expression.getValueParameterReference()) : null);
        if (protoBuf$Expression.hasConstantValue()) {
            ProtoBuf$Expression.ConstantValue constantValue = protoBuf$Expression.getConstantValue();
            Intrinsics.checkNotNull(constantValue);
            int i = WhenMappings.$EnumSwitchMapping$6[constantValue.ordinal()];
            if (i == 1) {
                bool = Boolean.TRUE;
            } else if (i == 2) {
                bool = Boolean.FALSE;
            } else if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            kmEffectExpressionVisitor.visitConstantValue(bool);
        }
        ProtoBuf$Type protoBuf$TypeIsInstanceType = ProtoTypeTableUtilKt.isInstanceType(protoBuf$Expression, readContext.getTypes());
        if (protoBuf$TypeIsInstanceType != null && (kmTypeVisitorVisitIsInstanceType = kmEffectExpressionVisitor.visitIsInstanceType(getTypeFlags(protoBuf$TypeIsInstanceType))) != null) {
            accept(protoBuf$TypeIsInstanceType, kmTypeVisitorVisitIsInstanceType, readContext);
        }
        for (ProtoBuf$Expression andArgument : protoBuf$Expression.getAndArgumentList()) {
            KmEffectExpressionVisitor kmEffectExpressionVisitorVisitAndArgument = kmEffectExpressionVisitor.visitAndArgument();
            if (kmEffectExpressionVisitorVisitAndArgument != null) {
                Intrinsics.checkNotNullExpressionValue(andArgument, "andArgument");
                accept(andArgument, kmEffectExpressionVisitorVisitAndArgument, readContext);
            }
        }
        for (ProtoBuf$Expression orArgument : protoBuf$Expression.getOrArgumentList()) {
            KmEffectExpressionVisitor kmEffectExpressionVisitorVisitOrArgument = kmEffectExpressionVisitor.visitOrArgument();
            if (kmEffectExpressionVisitorVisitOrArgument != null) {
                Intrinsics.checkNotNullExpressionValue(orArgument, "orArgument");
                accept(orArgument, kmEffectExpressionVisitorVisitOrArgument, readContext);
            }
        }
        kmEffectExpressionVisitor.visitEnd();
    }

    private static final int getTypeFlags(ProtoBuf$Type protoBuf$Type) {
        boolean nullable = protoBuf$Type.getNullable();
        return (nullable ? 1 : 0) + (protoBuf$Type.getFlags() << 1);
    }

    private static final int getTypeParameterFlags(ProtoBuf$TypeParameter protoBuf$TypeParameter) {
        return protoBuf$TypeParameter.getReified() ? 1 : 0;
    }

    public static final int getPropertyGetterFlags(ProtoBuf$Property protoBuf$Property) {
        Intrinsics.checkNotNullParameter(protoBuf$Property, "<this>");
        return protoBuf$Property.hasGetterFlags() ? protoBuf$Property.getGetterFlags() : getDefaultPropertyAccessorFlags(protoBuf$Property.getFlags());
    }

    public static final int getPropertySetterFlags(ProtoBuf$Property protoBuf$Property) {
        Intrinsics.checkNotNullParameter(protoBuf$Property, "<this>");
        return protoBuf$Property.hasSetterFlags() ? protoBuf$Property.getSetterFlags() : getDefaultPropertyAccessorFlags(protoBuf$Property.getFlags());
    }

    private static final int getDefaultPropertyAccessorFlags(int i) {
        Boolean bool = Flags.HAS_ANNOTATIONS.get(i);
        Intrinsics.checkNotNullExpressionValue(bool, "HAS_ANNOTATIONS.get(flags)");
        return Flags.getAccessorFlags(bool.booleanValue(), Flags.VISIBILITY.get(i), Flags.MODALITY.get(i), false, false, false);
    }
}
