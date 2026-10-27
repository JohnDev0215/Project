package kotlinx.metadata.internal.metadata;

import kotlinx.metadata.internal.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes7.dex */
public interface ProtoBuf$ClassOrBuilder extends MessageLiteOrBuilder {
    int getCompanionObjectName();

    int getConstructorCount();

    int getContextReceiverTypeCount();

    int getContextReceiverTypeIdCount();

    int getEnumEntryCount();

    int getFlags();

    int getFqName();

    int getFunctionCount();

    int getInlineClassUnderlyingPropertyName();

    int getInlineClassUnderlyingTypeId();

    int getMultiFieldValueClassUnderlyingNameCount();

    int getMultiFieldValueClassUnderlyingTypeCount();

    int getMultiFieldValueClassUnderlyingTypeIdCount();

    int getNestedClassNameCount();

    int getPropertyCount();

    int getSealedSubclassFqNameCount();

    int getSupertypeCount();

    int getSupertypeIdCount();

    int getTypeAliasCount();

    int getTypeParameterCount();

    int getVersionRequirementCount();
}
