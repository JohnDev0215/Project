package kotlinx.metadata.internal.protobuf;

import java.io.IOException;

/* JADX INFO: loaded from: classes7.dex */
public interface MessageLite extends MessageLiteOrBuilder {

    public interface Builder extends MessageLiteOrBuilder, Cloneable {
        MessageLite build();

        Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException;
    }

    Parser<? extends MessageLite> getParserForType();

    int getSerializedSize();

    Builder newBuilderForType();

    Builder toBuilder();
}
