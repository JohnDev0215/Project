package kotlinx.metadata.internal.metadata.deserialization;

import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.internal.metadata.ProtoBuf$Class;
import kotlinx.metadata.internal.metadata.ProtoBuf$Expression;
import kotlinx.metadata.internal.metadata.ProtoBuf$Function;
import kotlinx.metadata.internal.metadata.ProtoBuf$Property;
import kotlinx.metadata.internal.metadata.ProtoBuf$Type;
import kotlinx.metadata.internal.metadata.ProtoBuf$TypeAlias;
import kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameter;
import kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameter;

/* JADX INFO: compiled from: protoTypeTableUtil.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0014\u0010\b\u001a\u0004\u0018\u00010\u0004*\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0014\u0010\t\u001a\u0004\u0018\u00010\u0004*\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0018\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003*\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0012\u0010\r\u001a\u00020\u0004*\u00020\f2\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u0004*\u00020\f2\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0012\u0010\r\u001a\u00020\u0004*\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u0004*\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0012\u0010\b\u001a\u00020\u0004*\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u0004*\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u0004*\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0014\u0010\u0013\u001a\u0004\u0018\u00010\u0004*\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0012\u0010\u0015\u001a\u00020\u0004*\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0012\u0010\u0016\u001a\u00020\u0004*\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u0004*\u00020\u00172\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0018\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0018\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003*\u00020\f2\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0018\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003*\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u0001¨\u0006\u001a"}, d2 = {"Lkotlinx/metadata/internal/metadata/ProtoBuf$Class;", "Lkotlinx/metadata/internal/metadata/deserialization/TypeTable;", "typeTable", "", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Type;", "supertypes", "inlineClassUnderlyingType", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Type$Argument;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "flexibleUpperBound", "Lkotlinx/metadata/internal/metadata/ProtoBuf$TypeParameter;", "upperBounds", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Function;", "returnType", "receiverType", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Property;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$ValueParameter;", "varargElementType", "outerType", "abbreviatedType", "Lkotlinx/metadata/internal/metadata/ProtoBuf$TypeAlias;", "underlyingType", "expandedType", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Expression;", "isInstanceType", "contextReceiverTypes", "metadata"}, k = 2, mv = {1, 7, 1})
public final class ProtoTypeTableUtilKt {
    public static final List<ProtoBuf$Type> supertypes(ProtoBuf$Class protoBuf$Class, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Class, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        List<ProtoBuf$Type> supertypeList = protoBuf$Class.getSupertypeList();
        if (!(!supertypeList.isEmpty())) {
            supertypeList = null;
        }
        if (supertypeList == null) {
            List<Integer> supertypeIdList = protoBuf$Class.getSupertypeIdList();
            Intrinsics.checkNotNullExpressionValue(supertypeIdList, "supertypeIdList");
            supertypeList = new ArrayList<>(CollectionsKt__IterablesKt.collectionSizeOrDefault(supertypeIdList, 10));
            for (Integer it : supertypeIdList) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                supertypeList.add(typeTable.get(it.intValue()));
            }
        }
        return supertypeList;
    }

    public static final ProtoBuf$Type inlineClassUnderlyingType(ProtoBuf$Class protoBuf$Class, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Class, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$Class.hasInlineClassUnderlyingType()) {
            return protoBuf$Class.getInlineClassUnderlyingType();
        }
        if (protoBuf$Class.hasInlineClassUnderlyingTypeId()) {
            return typeTable.get(protoBuf$Class.getInlineClassUnderlyingTypeId());
        }
        return null;
    }

    public static final ProtoBuf$Type type(ProtoBuf$Type.Argument argument, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(argument, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (argument.hasType()) {
            return argument.getType();
        }
        if (argument.hasTypeId()) {
            return typeTable.get(argument.getTypeId());
        }
        return null;
    }

    public static final ProtoBuf$Type flexibleUpperBound(ProtoBuf$Type protoBuf$Type, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Type, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$Type.hasFlexibleUpperBound()) {
            return protoBuf$Type.getFlexibleUpperBound();
        }
        if (protoBuf$Type.hasFlexibleUpperBoundId()) {
            return typeTable.get(protoBuf$Type.getFlexibleUpperBoundId());
        }
        return null;
    }

    public static final List<ProtoBuf$Type> upperBounds(ProtoBuf$TypeParameter protoBuf$TypeParameter, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$TypeParameter, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        List<ProtoBuf$Type> upperBoundList = protoBuf$TypeParameter.getUpperBoundList();
        if (!(!upperBoundList.isEmpty())) {
            upperBoundList = null;
        }
        if (upperBoundList == null) {
            List<Integer> upperBoundIdList = protoBuf$TypeParameter.getUpperBoundIdList();
            Intrinsics.checkNotNullExpressionValue(upperBoundIdList, "upperBoundIdList");
            upperBoundList = new ArrayList<>(CollectionsKt__IterablesKt.collectionSizeOrDefault(upperBoundIdList, 10));
            for (Integer it : upperBoundIdList) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                upperBoundList.add(typeTable.get(it.intValue()));
            }
        }
        return upperBoundList;
    }

    public static final ProtoBuf$Type returnType(ProtoBuf$Function protoBuf$Function, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Function, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$Function.hasReturnType()) {
            ProtoBuf$Type returnType = protoBuf$Function.getReturnType();
            Intrinsics.checkNotNullExpressionValue(returnType, "returnType");
            return returnType;
        }
        if (protoBuf$Function.hasReturnTypeId()) {
            return typeTable.get(protoBuf$Function.getReturnTypeId());
        }
        throw new IllegalStateException("No returnType in ProtoBuf.Function".toString());
    }

    public static final ProtoBuf$Type receiverType(ProtoBuf$Function protoBuf$Function, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Function, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$Function.hasReceiverType()) {
            return protoBuf$Function.getReceiverType();
        }
        if (protoBuf$Function.hasReceiverTypeId()) {
            return typeTable.get(protoBuf$Function.getReceiverTypeId());
        }
        return null;
    }

    public static final ProtoBuf$Type returnType(ProtoBuf$Property protoBuf$Property, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Property, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$Property.hasReturnType()) {
            ProtoBuf$Type returnType = protoBuf$Property.getReturnType();
            Intrinsics.checkNotNullExpressionValue(returnType, "returnType");
            return returnType;
        }
        if (protoBuf$Property.hasReturnTypeId()) {
            return typeTable.get(protoBuf$Property.getReturnTypeId());
        }
        throw new IllegalStateException("No returnType in ProtoBuf.Property".toString());
    }

    public static final ProtoBuf$Type receiverType(ProtoBuf$Property protoBuf$Property, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Property, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$Property.hasReceiverType()) {
            return protoBuf$Property.getReceiverType();
        }
        if (protoBuf$Property.hasReceiverTypeId()) {
            return typeTable.get(protoBuf$Property.getReceiverTypeId());
        }
        return null;
    }

    public static final ProtoBuf$Type type(ProtoBuf$ValueParameter protoBuf$ValueParameter, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$ValueParameter, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$ValueParameter.hasType()) {
            ProtoBuf$Type type = protoBuf$ValueParameter.getType();
            Intrinsics.checkNotNullExpressionValue(type, "type");
            return type;
        }
        if (protoBuf$ValueParameter.hasTypeId()) {
            return typeTable.get(protoBuf$ValueParameter.getTypeId());
        }
        throw new IllegalStateException("No type in ProtoBuf.ValueParameter".toString());
    }

    public static final ProtoBuf$Type varargElementType(ProtoBuf$ValueParameter protoBuf$ValueParameter, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$ValueParameter, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$ValueParameter.hasVarargElementType()) {
            return protoBuf$ValueParameter.getVarargElementType();
        }
        if (protoBuf$ValueParameter.hasVarargElementTypeId()) {
            return typeTable.get(protoBuf$ValueParameter.getVarargElementTypeId());
        }
        return null;
    }

    public static final ProtoBuf$Type outerType(ProtoBuf$Type protoBuf$Type, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Type, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$Type.hasOuterType()) {
            return protoBuf$Type.getOuterType();
        }
        if (protoBuf$Type.hasOuterTypeId()) {
            return typeTable.get(protoBuf$Type.getOuterTypeId());
        }
        return null;
    }

    public static final ProtoBuf$Type abbreviatedType(ProtoBuf$Type protoBuf$Type, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Type, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$Type.hasAbbreviatedType()) {
            return protoBuf$Type.getAbbreviatedType();
        }
        if (protoBuf$Type.hasAbbreviatedTypeId()) {
            return typeTable.get(protoBuf$Type.getAbbreviatedTypeId());
        }
        return null;
    }

    public static final ProtoBuf$Type underlyingType(ProtoBuf$TypeAlias protoBuf$TypeAlias, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$TypeAlias, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$TypeAlias.hasUnderlyingType()) {
            ProtoBuf$Type underlyingType = protoBuf$TypeAlias.getUnderlyingType();
            Intrinsics.checkNotNullExpressionValue(underlyingType, "underlyingType");
            return underlyingType;
        }
        if (protoBuf$TypeAlias.hasUnderlyingTypeId()) {
            return typeTable.get(protoBuf$TypeAlias.getUnderlyingTypeId());
        }
        throw new IllegalStateException("No underlyingType in ProtoBuf.TypeAlias".toString());
    }

    public static final ProtoBuf$Type expandedType(ProtoBuf$TypeAlias protoBuf$TypeAlias, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$TypeAlias, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$TypeAlias.hasExpandedType()) {
            ProtoBuf$Type expandedType = protoBuf$TypeAlias.getExpandedType();
            Intrinsics.checkNotNullExpressionValue(expandedType, "expandedType");
            return expandedType;
        }
        if (protoBuf$TypeAlias.hasExpandedTypeId()) {
            return typeTable.get(protoBuf$TypeAlias.getExpandedTypeId());
        }
        throw new IllegalStateException("No expandedType in ProtoBuf.TypeAlias".toString());
    }

    public static final ProtoBuf$Type isInstanceType(ProtoBuf$Expression protoBuf$Expression, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Expression, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$Expression.hasIsInstanceType()) {
            return protoBuf$Expression.getIsInstanceType();
        }
        if (protoBuf$Expression.hasIsInstanceTypeId()) {
            return typeTable.get(protoBuf$Expression.getIsInstanceTypeId());
        }
        return null;
    }

    public static final List<ProtoBuf$Type> contextReceiverTypes(ProtoBuf$Class protoBuf$Class, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Class, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        List<ProtoBuf$Type> contextReceiverTypeList = protoBuf$Class.getContextReceiverTypeList();
        if (!(!contextReceiverTypeList.isEmpty())) {
            contextReceiverTypeList = null;
        }
        if (contextReceiverTypeList == null) {
            List<Integer> contextReceiverTypeIdList = protoBuf$Class.getContextReceiverTypeIdList();
            Intrinsics.checkNotNullExpressionValue(contextReceiverTypeIdList, "contextReceiverTypeIdList");
            contextReceiverTypeList = new ArrayList<>(CollectionsKt__IterablesKt.collectionSizeOrDefault(contextReceiverTypeIdList, 10));
            for (Integer it : contextReceiverTypeIdList) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                contextReceiverTypeList.add(typeTable.get(it.intValue()));
            }
        }
        return contextReceiverTypeList;
    }

    public static final List<ProtoBuf$Type> contextReceiverTypes(ProtoBuf$Function protoBuf$Function, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Function, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        List<ProtoBuf$Type> contextReceiverTypeList = protoBuf$Function.getContextReceiverTypeList();
        if (!(!contextReceiverTypeList.isEmpty())) {
            contextReceiverTypeList = null;
        }
        if (contextReceiverTypeList == null) {
            List<Integer> contextReceiverTypeIdList = protoBuf$Function.getContextReceiverTypeIdList();
            Intrinsics.checkNotNullExpressionValue(contextReceiverTypeIdList, "contextReceiverTypeIdList");
            contextReceiverTypeList = new ArrayList<>(CollectionsKt__IterablesKt.collectionSizeOrDefault(contextReceiverTypeIdList, 10));
            for (Integer it : contextReceiverTypeIdList) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                contextReceiverTypeList.add(typeTable.get(it.intValue()));
            }
        }
        return contextReceiverTypeList;
    }

    public static final List<ProtoBuf$Type> contextReceiverTypes(ProtoBuf$Property protoBuf$Property, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Property, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        List<ProtoBuf$Type> contextReceiverTypeList = protoBuf$Property.getContextReceiverTypeList();
        if (!(!contextReceiverTypeList.isEmpty())) {
            contextReceiverTypeList = null;
        }
        if (contextReceiverTypeList == null) {
            List<Integer> contextReceiverTypeIdList = protoBuf$Property.getContextReceiverTypeIdList();
            Intrinsics.checkNotNullExpressionValue(contextReceiverTypeIdList, "contextReceiverTypeIdList");
            contextReceiverTypeList = new ArrayList<>(CollectionsKt__IterablesKt.collectionSizeOrDefault(contextReceiverTypeIdList, 10));
            for (Integer it : contextReceiverTypeIdList) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                contextReceiverTypeList.add(typeTable.get(it.intValue()));
            }
        }
        return contextReceiverTypeList;
    }
}
