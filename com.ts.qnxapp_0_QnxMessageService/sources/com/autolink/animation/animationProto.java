package com.autolink.animation;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;

/* JADX INFO: loaded from: classes.dex */
public final class animationProto {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_autolink_animation_Animation_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_animation_Animation_fieldAccessorTable;

    public interface AnimationOrBuilder extends MessageOrBuilder {
        AndroidStatus getAndroidStatus();

        boolean hasAndroidStatus();
    }

    private animationProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
    }

    public enum EventId implements ProtocolMessageEnum {
        ANIMATION_STATUS(0, 1);

        public static final int ANIMATION_STATUS_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<EventId> internalValueMap = new Internal.EnumLiteMap<EventId>() { // from class: com.autolink.animation.animationProto.EventId.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public EventId findValueByNumber(int number) {
                return EventId.valueOf(number);
            }
        };
        private static final EventId[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static EventId valueOf(int value) {
            if (value == 1) {
                return ANIMATION_STATUS;
            }
            return null;
        }

        public static Internal.EnumLiteMap<EventId> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return animationProto.getDescriptor().getEnumTypes().get(0);
        }

        public static EventId valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        EventId(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum AndroidStatus implements ProtocolMessageEnum {
        WAIT(0, 0),
        READY(1, 1);

        public static final int READY_VALUE = 1;
        public static final int WAIT_VALUE = 0;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<AndroidStatus> internalValueMap = new Internal.EnumLiteMap<AndroidStatus>() { // from class: com.autolink.animation.animationProto.AndroidStatus.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public AndroidStatus findValueByNumber(int number) {
                return AndroidStatus.valueOf(number);
            }
        };
        private static final AndroidStatus[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static AndroidStatus valueOf(int value) {
            if (value == 0) {
                return WAIT;
            }
            if (value == 1) {
                return READY;
            }
            return null;
        }

        public static Internal.EnumLiteMap<AndroidStatus> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return animationProto.getDescriptor().getEnumTypes().get(1);
        }

        public static AndroidStatus valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        AndroidStatus(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public static final class Animation extends GeneratedMessage implements AnimationOrBuilder {
        public static final int ANDROID_STATUS_FIELD_NUMBER = 1;
        public static Parser<Animation> PARSER = new AbstractParser<Animation>() { // from class: com.autolink.animation.animationProto.Animation.1
            @Override // com.google.protobuf.Parser
            public Animation parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Animation(input, extensionRegistry);
            }
        };
        private static final Animation defaultInstance;
        private static final long serialVersionUID = 0;
        private AndroidStatus androidStatus_;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        private Animation(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Animation(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Animation getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Animation getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Animation(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag != 8) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            int rawValue = input.readEnum();
                            AndroidStatus value = AndroidStatus.valueOf(rawValue);
                            if (value != null) {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.androidStatus_ = value;
                            } else {
                                unknownFields.mergeVarintField(1, rawValue);
                            }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return animationProto.internal_static_autolink_animation_Animation_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return animationProto.internal_static_autolink_animation_Animation_fieldAccessorTable.ensureFieldAccessorsInitialized(Animation.class, Builder.class);
        }

        static {
            Animation animation = new Animation(true);
            defaultInstance = animation;
            animation.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Animation> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.animation.animationProto.AnimationOrBuilder
        public boolean hasAndroidStatus() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.animation.animationProto.AnimationOrBuilder
        public AndroidStatus getAndroidStatus() {
            return this.androidStatus_;
        }

        private void initFields() {
            this.androidStatus_ = AndroidStatus.WAIT;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.androidStatus_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.androidStatus_.getNumber()) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Animation parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Animation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Animation parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Animation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Animation parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Animation parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Animation parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Animation parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Animation parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Animation parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Animation prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements AnimationOrBuilder {
            private AndroidStatus androidStatus_;
            private int bitField0_;

            public static final Descriptors.Descriptor getDescriptor() {
                return animationProto.internal_static_autolink_animation_Animation_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return animationProto.internal_static_autolink_animation_Animation_fieldAccessorTable.ensureFieldAccessorsInitialized(Animation.class, Builder.class);
            }

            private Builder() {
                this.androidStatus_ = AndroidStatus.WAIT;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.androidStatus_ = AndroidStatus.WAIT;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Animation.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.androidStatus_ = AndroidStatus.WAIT;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return animationProto.internal_static_autolink_animation_Animation_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Animation getDefaultInstanceForType() {
                return Animation.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Animation build() {
                Animation result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Animation buildPartial() {
                Animation result = new Animation(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.androidStatus_ = this.androidStatus_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof Animation) {
                    return mergeFrom((Animation) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Animation other) {
                if (other == Animation.getDefaultInstance()) {
                    return this;
                }
                if (other.hasAndroidStatus()) {
                    setAndroidStatus(other.getAndroidStatus());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Animation parsedMessage = null;
                try {
                    try {
                        parsedMessage = Animation.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.animation.animationProto.AnimationOrBuilder
            public boolean hasAndroidStatus() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.animation.animationProto.AnimationOrBuilder
            public AndroidStatus getAndroidStatus() {
                return this.androidStatus_;
            }

            public Builder setAndroidStatus(AndroidStatus value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.androidStatus_ = value;
                onChanged();
                return this;
            }

            public Builder clearAndroidStatus() {
                this.bitField0_ &= -2;
                this.androidStatus_ = AndroidStatus.WAIT;
                onChanged();
                return this;
            }
        }
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = {"\n\u001ccom.autolink.animation.proto\u0012\u0012autolink.animation\"F\n\tAnimation\u00129\n\u000eandroid_status\u0018\u0001 \u0001(\u000e2!.autolink.animation.AndroidStatus*\u001f\n\u0007EventId\u0012\u0014\n\u0010ANIMATION_STATUS\u0010\u0001*$\n\rAndroidStatus\u0012\b\n\u0004WAIT\u0010\u0000\u0012\t\n\u0005READY\u0010\u0001B(\n\u0016com.autolink.animationB\u000eanimationProto"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.autolink.animation.animationProto.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                Descriptors.FileDescriptor unused = animationProto.descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0], assigner);
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_autolink_animation_Animation_descriptor = descriptor2;
        internal_static_autolink_animation_Animation_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor2, new String[]{"AndroidStatus"});
    }
}
