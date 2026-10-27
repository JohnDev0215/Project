package com.google.type;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public final class TimeOfDay extends GeneratedMessageV3 implements TimeOfDayOrBuilder {
    public static final int HOURS_FIELD_NUMBER = 1;
    public static final int MINUTES_FIELD_NUMBER = 2;
    public static final int NANOS_FIELD_NUMBER = 4;
    public static final int SECONDS_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private int hours_;
    private byte memoizedIsInitialized;
    private int minutes_;
    private int nanos_;
    private int seconds_;
    private static final TimeOfDay DEFAULT_INSTANCE = new TimeOfDay();
    private static final Parser<TimeOfDay> PARSER = new AbstractParser<TimeOfDay>() { // from class: com.google.type.TimeOfDay.1
        @Override // com.google.protobuf.Parser
        public TimeOfDay parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new TimeOfDay(codedInputStream, extensionRegistryLite);
        }
    };

    private TimeOfDay(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private TimeOfDay() {
        this.memoizedIsInitialized = (byte) -1;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new TimeOfDay();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private TimeOfDay(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (tag == 8) {
                                this.hours_ = codedInputStream.readInt32();
                            } else if (tag == 16) {
                                this.minutes_ = codedInputStream.readInt32();
                            } else if (tag == 24) {
                                this.seconds_ = codedInputStream.readInt32();
                            } else if (tag == 32) {
                                this.nanos_ = codedInputStream.readInt32();
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (UninitializedMessageException e2) {
                        throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(this);
                    }
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return TimeOfDayProto.internal_static_google_type_TimeOfDay_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return TimeOfDayProto.internal_static_google_type_TimeOfDay_fieldAccessorTable.ensureFieldAccessorsInitialized(TimeOfDay.class, Builder.class);
    }

    @Override // com.google.type.TimeOfDayOrBuilder
    public int getHours() {
        return this.hours_;
    }

    @Override // com.google.type.TimeOfDayOrBuilder
    public int getMinutes() {
        return this.minutes_;
    }

    @Override // com.google.type.TimeOfDayOrBuilder
    public int getSeconds() {
        return this.seconds_;
    }

    @Override // com.google.type.TimeOfDayOrBuilder
    public int getNanos() {
        return this.nanos_;
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
        int i = this.hours_;
        if (i != 0) {
            codedOutputStream.writeInt32(1, i);
        }
        int i2 = this.minutes_;
        if (i2 != 0) {
            codedOutputStream.writeInt32(2, i2);
        }
        int i3 = this.seconds_;
        if (i3 != 0) {
            codedOutputStream.writeInt32(3, i3);
        }
        int i4 = this.nanos_;
        if (i4 != 0) {
            codedOutputStream.writeInt32(4, i4);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = this.hours_;
        int iComputeInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeInt32Size(1, i2) : 0;
        int i3 = this.minutes_;
        if (i3 != 0) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(2, i3);
        }
        int i4 = this.seconds_;
        if (i4 != 0) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(3, i4);
        }
        int i5 = this.nanos_;
        if (i5 != 0) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(4, i5);
        }
        int serializedSize = iComputeInt32Size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TimeOfDay)) {
            return super.equals(obj);
        }
        TimeOfDay timeOfDay = (TimeOfDay) obj;
        return getHours() == timeOfDay.getHours() && getMinutes() == timeOfDay.getMinutes() && getSeconds() == timeOfDay.getSeconds() && getNanos() == timeOfDay.getNanos() && this.unknownFields.equals(timeOfDay.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getHours()) * 37) + 2) * 53) + getMinutes()) * 37) + 3) * 53) + getSeconds()) * 37) + 4) * 53) + getNanos()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    public static TimeOfDay parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static TimeOfDay parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static TimeOfDay parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static TimeOfDay parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static TimeOfDay parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static TimeOfDay parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static TimeOfDay parseFrom(InputStream inputStream) throws IOException {
        return (TimeOfDay) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static TimeOfDay parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TimeOfDay) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static TimeOfDay parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (TimeOfDay) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static TimeOfDay parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TimeOfDay) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static TimeOfDay parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (TimeOfDay) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static TimeOfDay parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TimeOfDay) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(TimeOfDay timeOfDay) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(timeOfDay);
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

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TimeOfDayOrBuilder {
        private int hours_;
        private int minutes_;
        private int nanos_;
        private int seconds_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TimeOfDayProto.internal_static_google_type_TimeOfDay_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TimeOfDayProto.internal_static_google_type_TimeOfDay_fieldAccessorTable.ensureFieldAccessorsInitialized(TimeOfDay.class, Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = TimeOfDay.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.hours_ = 0;
            this.minutes_ = 0;
            this.seconds_ = 0;
            this.nanos_ = 0;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return TimeOfDayProto.internal_static_google_type_TimeOfDay_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public TimeOfDay getDefaultInstanceForType() {
            return TimeOfDay.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public TimeOfDay build() {
            TimeOfDay timeOfDayBuildPartial = buildPartial();
            if (timeOfDayBuildPartial.isInitialized()) {
                return timeOfDayBuildPartial;
            }
            throw newUninitializedMessageException((Message) timeOfDayBuildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public TimeOfDay buildPartial() {
            TimeOfDay timeOfDay = new TimeOfDay(this);
            timeOfDay.hours_ = this.hours_;
            timeOfDay.minutes_ = this.minutes_;
            timeOfDay.seconds_ = this.seconds_;
            timeOfDay.nanos_ = this.nanos_;
            onBuilt();
            return timeOfDay;
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
            if (message instanceof TimeOfDay) {
                return mergeFrom((TimeOfDay) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(TimeOfDay timeOfDay) {
            if (timeOfDay == TimeOfDay.getDefaultInstance()) {
                return this;
            }
            if (timeOfDay.getHours() != 0) {
                setHours(timeOfDay.getHours());
            }
            if (timeOfDay.getMinutes() != 0) {
                setMinutes(timeOfDay.getMinutes());
            }
            if (timeOfDay.getSeconds() != 0) {
                setSeconds(timeOfDay.getSeconds());
            }
            if (timeOfDay.getNanos() != 0) {
                setNanos(timeOfDay.getNanos());
            }
            mergeUnknownFields(timeOfDay.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.type.TimeOfDay.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.type.TimeOfDay.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.type.TimeOfDay r3 = (com.google.type.TimeOfDay) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.type.TimeOfDay r4 = (com.google.type.TimeOfDay) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.type.TimeOfDay.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.type.TimeOfDay$Builder");
        }

        @Override // com.google.type.TimeOfDayOrBuilder
        public int getHours() {
            return this.hours_;
        }

        public Builder setHours(int i) {
            this.hours_ = i;
            onChanged();
            return this;
        }

        public Builder clearHours() {
            this.hours_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.type.TimeOfDayOrBuilder
        public int getMinutes() {
            return this.minutes_;
        }

        public Builder setMinutes(int i) {
            this.minutes_ = i;
            onChanged();
            return this;
        }

        public Builder clearMinutes() {
            this.minutes_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.type.TimeOfDayOrBuilder
        public int getSeconds() {
            return this.seconds_;
        }

        public Builder setSeconds(int i) {
            this.seconds_ = i;
            onChanged();
            return this;
        }

        public Builder clearSeconds() {
            this.seconds_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.type.TimeOfDayOrBuilder
        public int getNanos() {
            return this.nanos_;
        }

        public Builder setNanos(int i) {
            this.nanos_ = i;
            onChanged();
            return this;
        }

        public Builder clearNanos() {
            this.nanos_ = 0;
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

    public static TimeOfDay getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TimeOfDay> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<TimeOfDay> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public TimeOfDay getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
