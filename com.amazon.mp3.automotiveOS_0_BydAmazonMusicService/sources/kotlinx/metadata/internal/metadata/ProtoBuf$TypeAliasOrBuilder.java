package kotlinx.metadata.internal.metadata;

import kotlinx.metadata.internal.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes7.dex */
public interface ProtoBuf$TypeAliasOrBuilder extends MessageLiteOrBuilder {
    int getAnnotationCount();

    int getExpandedTypeId();

    int getFlags();

    int getName();

    int getTypeParameterCount();

    int getUnderlyingTypeId();

    int getVersionRequirementCount();
}
