package kotlinx.metadata.internal.metadata;

import kotlinx.metadata.internal.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes7.dex */
public interface ProtoBuf$TypeOrBuilder extends MessageLiteOrBuilder {
    int getAbbreviatedTypeId();

    int getArgumentCount();

    int getClassName();

    int getFlags();

    int getFlexibleTypeCapabilitiesId();

    int getFlexibleUpperBoundId();

    int getOuterTypeId();

    int getTypeAliasName();

    int getTypeParameter();

    int getTypeParameterName();
}
