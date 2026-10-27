package com.google.rpc;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class Help extends GeneratedMessageV3 implements HelpOrBuilder {
    public static final int LINKS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private List<Link> links_;
    private byte memoizedIsInitialized;
    private static final Help DEFAULT_INSTANCE = new Help();
    private static final Parser<Help> PARSER = new AbstractParser<Help>() { // from class: com.google.rpc.Help.1
        @Override // com.google.protobuf.Parser
        public Help parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Help(codedInputStream, extensionRegistryLite);
        }
    };

    public interface LinkOrBuilder extends MessageOrBuilder {
        String getDescription();

        ByteString getDescriptionBytes();

        String getUrl();

        ByteString getUrlBytes();
    }

    private Help(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Help() {
        this.memoizedIsInitialized = (byte) -1;
        this.links_ = Collections.emptyList();
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Help();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Help(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 10) {
                            if (!(z2 & true)) {
                                this.links_ = new ArrayList();
                                z2 |= true;
                            }
                            this.links_.add((Link) codedInputStream.readMessage(Link.parser(), extensionRegistryLite));
                        } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (UninitializedMessageException e2) {
                    throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(this);
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                }
            } finally {
                if (z2 & true) {
                    this.links_ = Collections.unmodifiableList(this.links_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ErrorDetailsProto.internal_static_google_rpc_Help_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.internal_static_google_rpc_Help_fieldAccessorTable.ensureFieldAccessorsInitialized(Help.class, Builder.class);
    }

    public static final class Link extends GeneratedMessageV3 implements LinkOrBuilder {
        public static final int DESCRIPTION_FIELD_NUMBER = 1;
        public static final int URL_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private volatile Object description_;
        private byte memoizedIsInitialized;
        private volatile Object url_;
        private static final Link DEFAULT_INSTANCE = new Link();
        private static final Parser<Link> PARSER = new AbstractParser<Link>() { // from class: com.google.rpc.Help.Link.1
            @Override // com.google.protobuf.Parser
            public Link parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Link(codedInputStream, extensionRegistryLite);
            }
        };

        private Link(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Link() {
            this.memoizedIsInitialized = (byte) -1;
            this.description_ = "";
            this.url_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new Link();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Link(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 10) {
                                    this.description_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 18) {
                                    this.url_ = codedInputStream.readStringRequireUtf8();
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            }
                            z = true;
                        } catch (UninitializedMessageException e) {
                            throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e3) {
                        throw e3.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_Help_Link_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_Help_Link_fieldAccessorTable.ensureFieldAccessorsInitialized(Link.class, Builder.class);
        }

        @Override // com.google.rpc.Help.LinkOrBuilder
        public String getDescription() {
            Object obj = this.description_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.description_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.rpc.Help.LinkOrBuilder
        public ByteString getDescriptionBytes() {
            Object obj = this.description_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.description_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.rpc.Help.LinkOrBuilder
        public String getUrl() {
            Object obj = this.url_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.url_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.rpc.Help.LinkOrBuilder
        public ByteString getUrlBytes() {
            Object obj = this.url_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.url_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!GeneratedMessageV3.isStringEmpty(this.description_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.description_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.url_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.url_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = GeneratedMessageV3.isStringEmpty(this.description_) ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.description_);
            if (!GeneratedMessageV3.isStringEmpty(this.url_)) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.url_);
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Link)) {
                return super.equals(obj);
            }
            Link link = (Link) obj;
            return getDescription().equals(link.getDescription()) && getUrl().equals(link.getUrl()) && this.unknownFields.equals(link.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getDescription().hashCode()) * 37) + 2) * 53) + getUrl().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        public static Link parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static Link parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Link parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static Link parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Link parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Link parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Link parseFrom(InputStream inputStream) throws IOException {
            return (Link) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Link parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Link) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Link parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Link) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Link parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Link) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Link parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Link) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Link parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Link) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Link link) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(link);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LinkOrBuilder {
            private Object description_;
            private Object url_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ErrorDetailsProto.internal_static_google_rpc_Help_Link_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ErrorDetailsProto.internal_static_google_rpc_Help_Link_fieldAccessorTable.ensureFieldAccessorsInitialized(Link.class, Builder.class);
            }

            private Builder() {
                this.description_ = "";
                this.url_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.description_ = "";
                this.url_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Link.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.description_ = "";
                this.url_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ErrorDetailsProto.internal_static_google_rpc_Help_Link_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Link getDefaultInstanceForType() {
                return Link.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Link build() {
                Link linkBuildPartial = buildPartial();
                if (linkBuildPartial.isInitialized()) {
                    return linkBuildPartial;
                }
                throw newUninitializedMessageException((Message) linkBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Link buildPartial() {
                Link link = new Link(this);
                link.description_ = this.description_;
                link.url_ = this.url_;
                onBuilt();
                return link;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo240clone() {
                return (Builder) super.mo240clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof Link) {
                    return mergeFrom((Link) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Link link) {
                if (link == Link.getDefaultInstance()) {
                    return this;
                }
                if (!link.getDescription().isEmpty()) {
                    this.description_ = link.description_;
                    onChanged();
                }
                if (!link.getUrl().isEmpty()) {
                    this.url_ = link.url_;
                    onChanged();
                }
                mergeUnknownFields(link.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public com.google.rpc.Help.Link.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.google.rpc.Help.Link.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.google.rpc.Help$Link r3 = (com.google.rpc.Help.Link) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    com.google.rpc.Help$Link r4 = (com.google.rpc.Help.Link) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.Help.Link.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.Help$Link$Builder");
            }

            @Override // com.google.rpc.Help.LinkOrBuilder
            public String getDescription() {
                Object obj = this.description_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.description_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.google.rpc.Help.LinkOrBuilder
            public ByteString getDescriptionBytes() {
                Object obj = this.description_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.description_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setDescription(String str) {
                str.getClass();
                this.description_ = str;
                onChanged();
                return this;
            }

            public Builder clearDescription() {
                this.description_ = Link.getDefaultInstance().getDescription();
                onChanged();
                return this;
            }

            public Builder setDescriptionBytes(ByteString byteString) {
                byteString.getClass();
                Link.checkByteStringIsUtf8(byteString);
                this.description_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.google.rpc.Help.LinkOrBuilder
            public String getUrl() {
                Object obj = this.url_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.url_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.google.rpc.Help.LinkOrBuilder
            public ByteString getUrlBytes() {
                Object obj = this.url_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.url_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setUrl(String str) {
                str.getClass();
                this.url_ = str;
                onChanged();
                return this;
            }

            public Builder clearUrl() {
                this.url_ = Link.getDefaultInstance().getUrl();
                onChanged();
                return this;
            }

            public Builder setUrlBytes(ByteString byteString) {
                byteString.getClass();
                Link.checkByteStringIsUtf8(byteString);
                this.url_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static Link getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Link> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Link> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Link getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    @Override // com.google.rpc.HelpOrBuilder
    public List<Link> getLinksList() {
        return this.links_;
    }

    @Override // com.google.rpc.HelpOrBuilder
    public List<? extends LinkOrBuilder> getLinksOrBuilderList() {
        return this.links_;
    }

    @Override // com.google.rpc.HelpOrBuilder
    public int getLinksCount() {
        return this.links_.size();
    }

    @Override // com.google.rpc.HelpOrBuilder
    public Link getLinks(int i) {
        return this.links_.get(i);
    }

    @Override // com.google.rpc.HelpOrBuilder
    public LinkOrBuilder getLinksOrBuilder(int i) {
        return this.links_.get(i);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.links_.size(); i++) {
            codedOutputStream.writeMessage(1, this.links_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = 0;
        for (int i2 = 0; i2 < this.links_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.links_.get(i2));
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Help)) {
            return super.equals(obj);
        }
        Help help = (Help) obj;
        return getLinksList().equals(help.getLinksList()) && this.unknownFields.equals(help.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getLinksCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getLinksList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    public static Help parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Help parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Help parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Help parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Help parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Help parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Help parseFrom(InputStream inputStream) throws IOException {
        return (Help) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Help parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Help) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Help parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Help) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Help parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Help) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Help parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Help) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Help parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Help) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Help help) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(help);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HelpOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> linksBuilder_;
        private List<Link> links_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_Help_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_Help_fieldAccessorTable.ensureFieldAccessorsInitialized(Help.class, Builder.class);
        }

        private Builder() {
            this.links_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.links_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Help.alwaysUseFieldBuilders) {
                getLinksFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.links_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_Help_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Help getDefaultInstanceForType() {
            return Help.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Help build() {
            Help helpBuildPartial = buildPartial();
            if (helpBuildPartial.isInitialized()) {
                return helpBuildPartial;
            }
            throw newUninitializedMessageException((Message) helpBuildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Help buildPartial() {
            Help help = new Help(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.links_ = Collections.unmodifiableList(this.links_);
                    this.bitField0_ &= -2;
                }
                help.links_ = this.links_;
            } else {
                help.links_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return help;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
        /* JADX INFO: renamed from: clone */
        public Builder mo240clone() {
            return (Builder) super.mo240clone();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder mergeFrom(Message message) {
            if (message instanceof Help) {
                return mergeFrom((Help) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Help help) {
            if (help == Help.getDefaultInstance()) {
                return this;
            }
            if (this.linksBuilder_ == null) {
                if (!help.links_.isEmpty()) {
                    if (this.links_.isEmpty()) {
                        this.links_ = help.links_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureLinksIsMutable();
                        this.links_.addAll(help.links_);
                    }
                    onChanged();
                }
            } else if (!help.links_.isEmpty()) {
                if (!this.linksBuilder_.isEmpty()) {
                    this.linksBuilder_.addAllMessages(help.links_);
                } else {
                    this.linksBuilder_.dispose();
                    this.linksBuilder_ = null;
                    this.links_ = help.links_;
                    this.bitField0_ &= -2;
                    this.linksBuilder_ = Help.alwaysUseFieldBuilders ? getLinksFieldBuilder() : null;
                }
            }
            mergeUnknownFields(help.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.rpc.Help.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.rpc.Help.access$1800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.rpc.Help r3 = (com.google.rpc.Help) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                if (r3 == 0) goto L10
                r2.mergeFrom(r3)
            L10:
                return r2
            L11:
                r3 = move-exception
                goto L21
            L13:
                r3 = move-exception
                com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                com.google.rpc.Help r4 = (com.google.rpc.Help) r4     // Catch: java.lang.Throwable -> L11
                java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                throw r3     // Catch: java.lang.Throwable -> L1f
            L1f:
                r3 = move-exception
                r0 = r4
            L21:
                if (r0 == 0) goto L26
                r2.mergeFrom(r0)
            L26:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.Help.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.Help$Builder");
        }

        private void ensureLinksIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.links_ = new ArrayList(this.links_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.rpc.HelpOrBuilder
        public List<Link> getLinksList() {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.links_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.rpc.HelpOrBuilder
        public int getLinksCount() {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.links_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.rpc.HelpOrBuilder
        public Link getLinks(int i) {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.links_.get(i);
            }
            return (Link) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setLinks(int i, Link link) {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                link.getClass();
                ensureLinksIsMutable();
                this.links_.set(i, link);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, link);
            }
            return this;
        }

        public Builder setLinks(int i, Link.Builder builder) {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLinksIsMutable();
                this.links_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addLinks(Link link) {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                link.getClass();
                ensureLinksIsMutable();
                this.links_.add(link);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(link);
            }
            return this;
        }

        public Builder addLinks(int i, Link link) {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                link.getClass();
                ensureLinksIsMutable();
                this.links_.add(i, link);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, link);
            }
            return this;
        }

        public Builder addLinks(Link.Builder builder) {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLinksIsMutable();
                this.links_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addLinks(int i, Link.Builder builder) {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLinksIsMutable();
                this.links_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllLinks(Iterable<? extends Link> iterable) {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLinksIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.links_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearLinks() {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.links_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeLinks(int i) {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLinksIsMutable();
                this.links_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Link.Builder getLinksBuilder(int i) {
            return (Link.Builder) getLinksFieldBuilder().getBuilder(i);
        }

        @Override // com.google.rpc.HelpOrBuilder
        public LinkOrBuilder getLinksOrBuilder(int i) {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.links_.get(i);
            }
            return (LinkOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.rpc.HelpOrBuilder
        public List<? extends LinkOrBuilder> getLinksOrBuilderList() {
            RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.links_);
        }

        public Link.Builder addLinksBuilder() {
            return (Link.Builder) getLinksFieldBuilder().addBuilder(Link.getDefaultInstance());
        }

        public Link.Builder addLinksBuilder(int i) {
            return (Link.Builder) getLinksFieldBuilder().addBuilder(i, Link.getDefaultInstance());
        }

        public List<Link.Builder> getLinksBuilderList() {
            return getLinksFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Link, Link.Builder, LinkOrBuilder> getLinksFieldBuilder() {
            if (this.linksBuilder_ == null) {
                this.linksBuilder_ = new RepeatedFieldBuilderV3<>(this.links_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.links_ = null;
            }
            return this.linksBuilder_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    public static Help getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Help> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Help> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Help getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
