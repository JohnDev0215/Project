package kotlinx.metadata.internal.metadata;

import kotlinx.metadata.internal.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes7.dex */
public interface ProtoBuf$FunctionOrBuilder extends MessageLiteOrBuilder {
    int getContextReceiverTypeCount();

    int getContextReceiverTypeIdCount();

    int getFlags();

    int getName();

    int getOldFlags();

    int getReceiverTypeId();

    int getReturnTypeId();

    int getTypeParameterCount();

    int getValueParameterCount();

    int getVersionRequirementCount();
}
