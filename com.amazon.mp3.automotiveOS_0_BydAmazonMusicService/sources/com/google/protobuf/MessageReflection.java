package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.FieldSet;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes7.dex */
class MessageReflection {

    interface MergeTarget {

        public enum ContainerType {
            MESSAGE,
            EXTENSION_SET
        }

        MergeTarget addRepeatedField(Descriptors.FieldDescriptor field, Object value);

        ExtensionRegistry.ExtensionInfo findExtensionByNumber(ExtensionRegistry registry, Descriptors.Descriptor containingType, int fieldNumber);

        ContainerType getContainerType();

        WireFormat.Utf8Validation getUtf8Validation(Descriptors.FieldDescriptor descriptor);

        boolean hasField(Descriptors.FieldDescriptor field);

        void mergeGroup(CodedInputStream input, ExtensionRegistryLite extensionRegistry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException;

        void mergeMessage(CodedInputStream input, ExtensionRegistryLite extensionRegistry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException;

        Object parseMessage(CodedInputStream input, ExtensionRegistryLite registry, Descriptors.FieldDescriptor descriptor, Message defaultInstance) throws IOException;

        Object parseMessageFromBytes(ByteString bytes, ExtensionRegistryLite registry, Descriptors.FieldDescriptor descriptor, Message defaultInstance) throws IOException;

        MergeTarget setField(Descriptors.FieldDescriptor field, Object value);
    }

    MessageReflection() {
    }

    static void writeMessageTo(Message message, Map<Descriptors.FieldDescriptor, Object> fields, CodedOutputStream output, boolean alwaysWriteRequiredFields) throws IOException {
        boolean messageSetWireFormat = message.getDescriptorForType().getOptions().getMessageSetWireFormat();
        if (alwaysWriteRequiredFields) {
            TreeMap treeMap = new TreeMap(fields);
            for (Descriptors.FieldDescriptor fieldDescriptor : message.getDescriptorForType().getFields()) {
                if (fieldDescriptor.isRequired() && !treeMap.containsKey(fieldDescriptor)) {
                    treeMap.put(fieldDescriptor, message.getField(fieldDescriptor));
                }
            }
            fields = treeMap;
        }
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : fields.entrySet()) {
            Descriptors.FieldDescriptor key = entry.getKey();
            Object value = entry.getValue();
            if (messageSetWireFormat && key.isExtension() && key.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && !key.isRepeated()) {
                output.writeMessageSetExtension(key.getNumber(), (Message) value);
            } else {
                FieldSet.writeField(key, value, output);
            }
        }
        UnknownFieldSet unknownFields = message.getUnknownFields();
        if (messageSetWireFormat) {
            unknownFields.writeAsMessageSetTo(output);
        } else {
            unknownFields.writeTo(output);
        }
    }

    static int getSerializedSize(Message message, Map<Descriptors.FieldDescriptor, Object> fields) {
        int serializedSize;
        int iComputeFieldSize;
        boolean messageSetWireFormat = message.getDescriptorForType().getOptions().getMessageSetWireFormat();
        int i = 0;
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : fields.entrySet()) {
            Descriptors.FieldDescriptor key = entry.getKey();
            Object value = entry.getValue();
            if (messageSetWireFormat && key.isExtension() && key.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && !key.isRepeated()) {
                iComputeFieldSize = CodedOutputStream.computeMessageSetExtensionSize(key.getNumber(), (Message) value);
            } else {
                iComputeFieldSize = FieldSet.computeFieldSize(key, value);
            }
            i += iComputeFieldSize;
        }
        UnknownFieldSet unknownFields = message.getUnknownFields();
        if (messageSetWireFormat) {
            serializedSize = unknownFields.getSerializedSizeAsMessageSet();
        } else {
            serializedSize = unknownFields.getSerializedSize();
        }
        return i + serializedSize;
    }

    static boolean isInitialized(MessageOrBuilder message) {
        for (Descriptors.FieldDescriptor fieldDescriptor : message.getDescriptorForType().getFields()) {
            if (fieldDescriptor.isRequired() && !message.hasField(fieldDescriptor)) {
                return false;
            }
        }
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : message.getAllFields().entrySet()) {
            Descriptors.FieldDescriptor key = entry.getKey();
            if (key.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                if (key.isRepeated()) {
                    Iterator it = ((List) entry.getValue()).iterator();
                    while (it.hasNext()) {
                        if (!((Message) it.next()).isInitialized()) {
                            return false;
                        }
                    }
                } else if (!((Message) entry.getValue()).isInitialized()) {
                    return false;
                }
            }
        }
        return true;
    }

    private static String subMessagePrefix(final String prefix, final Descriptors.FieldDescriptor field, final int index) {
        StringBuilder sb = new StringBuilder(prefix);
        if (field.isExtension()) {
            sb.append('(');
            sb.append(field.getFullName());
            sb.append(')');
        } else {
            sb.append(field.getName());
        }
        if (index != -1) {
            sb.append('[');
            sb.append(index);
            sb.append(']');
        }
        sb.append('.');
        return sb.toString();
    }

    private static void findMissingFields(final MessageOrBuilder message, final String prefix, final List<String> results) {
        for (Descriptors.FieldDescriptor fieldDescriptor : message.getDescriptorForType().getFields()) {
            if (fieldDescriptor.isRequired() && !message.hasField(fieldDescriptor)) {
                results.add(prefix + fieldDescriptor.getName());
            }
        }
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : message.getAllFields().entrySet()) {
            Descriptors.FieldDescriptor key = entry.getKey();
            Object value = entry.getValue();
            if (key.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                if (key.isRepeated()) {
                    int i = 0;
                    Iterator it = ((List) value).iterator();
                    while (it.hasNext()) {
                        findMissingFields((MessageOrBuilder) it.next(), subMessagePrefix(prefix, key, i), results);
                        i++;
                    }
                } else if (message.hasField(key)) {
                    findMissingFields((MessageOrBuilder) value, subMessagePrefix(prefix, key, -1), results);
                }
            }
        }
    }

    static List<String> findMissingFields(final MessageOrBuilder message) {
        ArrayList arrayList = new ArrayList();
        findMissingFields(message, "", arrayList);
        return arrayList;
    }

    static class BuilderAdapter implements MergeTarget {
        private final Message.Builder builder;
        private boolean hasNestedBuilders = true;

        public BuilderAdapter(Message.Builder builder) {
            this.builder = builder;
        }

        public Object getField(Descriptors.FieldDescriptor field) {
            return this.builder.getField(field);
        }

        private Message.Builder getFieldBuilder(Descriptors.FieldDescriptor field) {
            if (!this.hasNestedBuilders) {
                return null;
            }
            try {
                return this.builder.getFieldBuilder(field);
            } catch (UnsupportedOperationException unused) {
                this.hasNestedBuilders = false;
                return null;
            }
        }

        @Override // com.google.protobuf.MessageReflection.MergeTarget
        public boolean hasField(Descriptors.FieldDescriptor field) {
            return this.builder.hasField(field);
        }

        @Override // com.google.protobuf.MessageReflection.MergeTarget
        public MergeTarget setField(Descriptors.FieldDescriptor field, Object value) {
            if (!field.isRepeated() && (value instanceof MessageLite.Builder)) {
                if (value != getFieldBuilder(field)) {
                    this.builder.setField(field, ((MessageLite.Builder) value).buildPartial());
                }
                return this;
            }
            this.builder.setField(field, value);
            return this;
        }

        @Override // com.google.protobuf.MessageReflection.MergeTarget
        public MergeTarget addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            if (value instanceof MessageLite.Builder) {
                value = ((MessageLite.Builder) value).buildPartial();
            }
            this.builder.addRepeatedField(field, value);
            return this;
        }

        @Override // com.google.protobuf.MessageReflection.MergeTarget
        public MergeTarget.ContainerType getContainerType() {
            return MergeTarget.ContainerType.MESSAGE;
        }

        @Override // com.google.protobuf.MessageReflection.MergeTarget
        public ExtensionRegistry.ExtensionInfo findExtensionByNumber(ExtensionRegistry registry, Descriptors.Descriptor containingType, int fieldNumber) {
            return registry.findImmutableExtensionByNumber(containingType, fieldNumber);
        }

        @Override // com.google.protobuf.MessageReflection.MergeTarget
        public Object parseMessage(CodedInputStream input, ExtensionRegistryLite extensionRegistry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
            Message.Builder builderNewBuilderForField;
            Message message;
            if (defaultInstance != null) {
                builderNewBuilderForField = defaultInstance.newBuilderForType();
            } else {
                builderNewBuilderForField = this.builder.newBuilderForField(field);
            }
            if (!field.isRepeated() && (message = (Message) getField(field)) != null) {
                builderNewBuilderForField.mergeFrom(message);
            }
            input.readMessage(builderNewBuilderForField, extensionRegistry);
            return builderNewBuilderForField.buildPartial();
        }

        @Override // com.google.protobuf.MessageReflection.MergeTarget
        public Object parseMessageFromBytes(ByteString bytes, ExtensionRegistryLite extensionRegistry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
            Message.Builder builderNewBuilderForField;
            Message message;
            if (defaultInstance != null) {
                builderNewBuilderForField = defaultInstance.newBuilderForType();
            } else {
                builderNewBuilderForField = this.builder.newBuilderForField(field);
            }
            if (!field.isRepeated() && (message = (Message) getField(field)) != null) {
                builderNewBuilderForField.mergeFrom(message);
            }
            builderNewBuilderForField.mergeFrom(bytes, extensionRegistry);
            return builderNewBuilderForField.buildPartial();
        }

        @Override // com.google.protobuf.MessageReflection.MergeTarget
        public void mergeGroup(CodedInputStream input, ExtensionRegistryLite extensionRegistry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
            Message.Builder builderNewMessageFieldInstance;
            if (!field.isRepeated()) {
                if (hasField(field)) {
                    Message.Builder fieldBuilder = getFieldBuilder(field);
                    if (fieldBuilder != null) {
                        input.readGroup(field.getNumber(), fieldBuilder, extensionRegistry);
                        return;
                    } else {
                        builderNewMessageFieldInstance = newMessageFieldInstance(field, defaultInstance);
                        builderNewMessageFieldInstance.mergeFrom((Message) getField(field));
                    }
                } else {
                    builderNewMessageFieldInstance = newMessageFieldInstance(field, defaultInstance);
                }
                input.readGroup(field.getNumber(), builderNewMessageFieldInstance, extensionRegistry);
                setField(field, builderNewMessageFieldInstance.buildPartial());
                return;
            }
            Message.Builder builderNewMessageFieldInstance2 = newMessageFieldInstance(field, defaultInstance);
            input.readGroup(field.getNumber(), builderNewMessageFieldInstance2, extensionRegistry);
            addRepeatedField(field, builderNewMessageFieldInstance2.buildPartial());
        }

        @Override // com.google.protobuf.MessageReflection.MergeTarget
        public void mergeMessage(CodedInputStream input, ExtensionRegistryLite extensionRegistry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
            Message.Builder builderNewMessageFieldInstance;
            if (!field.isRepeated()) {
                if (hasField(field)) {
                    Message.Builder fieldBuilder = getFieldBuilder(field);
                    if (fieldBuilder != null) {
                        input.readMessage(fieldBuilder, extensionRegistry);
                        return;
                    } else {
                        builderNewMessageFieldInstance = newMessageFieldInstance(field, defaultInstance);
                        builderNewMessageFieldInstance.mergeFrom((Message) getField(field));
                    }
                } else {
                    builderNewMessageFieldInstance = newMessageFieldInstance(field, defaultInstance);
                }
                input.readMessage(builderNewMessageFieldInstance, extensionRegistry);
                setField(field, builderNewMessageFieldInstance.buildPartial());
                return;
            }
            Message.Builder builderNewMessageFieldInstance2 = newMessageFieldInstance(field, defaultInstance);
            input.readMessage(builderNewMessageFieldInstance2, extensionRegistry);
            addRepeatedField(field, builderNewMessageFieldInstance2.buildPartial());
        }

        private Message.Builder newMessageFieldInstance(Descriptors.FieldDescriptor field, Message defaultInstance) {
            if (defaultInstance != null) {
                return defaultInstance.newBuilderForType();
            }
            return this.builder.newBuilderForField(field);
        }

        @Override // com.google.protobuf.MessageReflection.MergeTarget
        public WireFormat.Utf8Validation getUtf8Validation(Descriptors.FieldDescriptor descriptor) {
            if (descriptor.needsUtf8Check()) {
                return WireFormat.Utf8Validation.STRICT;
            }
            if (!descriptor.isRepeated() && (this.builder instanceof GeneratedMessage.Builder)) {
                return WireFormat.Utf8Validation.LAZY;
            }
            return WireFormat.Utf8Validation.LOOSE;
        }
    }

    static class ExtensionBuilderAdapter implements MergeTarget {
        private final FieldSet.Builder<Descriptors.FieldDescriptor> extensions;

        ExtensionBuilderAdapter(FieldSet.Builder<Descriptors.FieldDescriptor> extensions) {
            this.extensions = extensions;
        }

        public Object getField(Descriptors.FieldDescriptor field) {
            return this.extensions.getField(field);
        }

        @Override // com.google.protobuf.MessageReflection.MergeTarget
        public boolean hasField(Descriptors.FieldDescriptor field) {
            return this.extensions.hasField(field);
        }

        @Override // com.google.protobuf.MessageReflection.MergeTarget
        public MergeTarget setField(Descriptors.FieldDescriptor field, Object value) {
            this.extensions.setField(field, value);
            return this;
        }

        @Override // com.google.protobuf.MessageReflection.MergeTarget
        public MergeTarget addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            this.extensions.addRepeatedField(field, value);
            return this;
        }

        @Override // com.google.protobuf.MessageReflection.MergeTarget
        public MergeTarget.ContainerType getContainerType() {
            return MergeTarget.ContainerType.EXTENSION_SET;
        }

        @Override // com.google.protobuf.MessageReflection.MergeTarget
        public ExtensionRegistry.ExtensionInfo findExtensionByNumber(ExtensionRegistry registry, Descriptors.Descriptor containingType, int fieldNumber) {
            return registry.findImmutableExtensionByNumber(containingType, fieldNumber);
        }

        @Override // com.google.protobuf.MessageReflection.MergeTarget
        public Object parseMessage(CodedInputStream input, ExtensionRegistryLite registry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
            Message message;
            Message.Builder builderNewBuilderForType = defaultInstance.newBuilderForType();
            if (!field.isRepeated() && (message = (Message) getField(field)) != null) {
                builderNewBuilderForType.mergeFrom(message);
            }
            input.readMessage(builderNewBuilderForType, registry);
            return builderNewBuilderForType.buildPartial();
        }

        @Override // com.google.protobuf.MessageReflection.MergeTarget
        public void mergeGroup(CodedInputStream input, ExtensionRegistryLite extensionRegistry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
            MessageLite.Builder builder;
            if (!field.isRepeated()) {
                if (hasField(field)) {
                    Object fieldAllowBuilders = this.extensions.getFieldAllowBuilders(field);
                    if (fieldAllowBuilders instanceof MessageLite.Builder) {
                        builder = (MessageLite.Builder) fieldAllowBuilders;
                    } else {
                        builder = ((MessageLite) fieldAllowBuilders).toBuilder();
                        this.extensions.setField(field, builder);
                    }
                    input.readGroup(field.getNumber(), builder, extensionRegistry);
                    return;
                }
                Message.Builder builderNewBuilderForType = defaultInstance.newBuilderForType();
                input.readGroup(field.getNumber(), builderNewBuilderForType, extensionRegistry);
                setField(field, builderNewBuilderForType);
                return;
            }
            Message.Builder builderNewBuilderForType2 = defaultInstance.newBuilderForType();
            input.readGroup(field.getNumber(), builderNewBuilderForType2, extensionRegistry);
            addRepeatedField(field, builderNewBuilderForType2.buildPartial());
        }

        @Override // com.google.protobuf.MessageReflection.MergeTarget
        public void mergeMessage(CodedInputStream input, ExtensionRegistryLite extensionRegistry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
            MessageLite.Builder builder;
            if (!field.isRepeated()) {
                if (hasField(field)) {
                    Object fieldAllowBuilders = this.extensions.getFieldAllowBuilders(field);
                    if (fieldAllowBuilders instanceof MessageLite.Builder) {
                        builder = (MessageLite.Builder) fieldAllowBuilders;
                    } else {
                        builder = ((MessageLite) fieldAllowBuilders).toBuilder();
                        this.extensions.setField(field, builder);
                    }
                    input.readMessage(builder, extensionRegistry);
                    return;
                }
                Message.Builder builderNewBuilderForType = defaultInstance.newBuilderForType();
                input.readMessage(builderNewBuilderForType, extensionRegistry);
                setField(field, builderNewBuilderForType);
                return;
            }
            Message.Builder builderNewBuilderForType2 = defaultInstance.newBuilderForType();
            input.readMessage(builderNewBuilderForType2, extensionRegistry);
            addRepeatedField(field, builderNewBuilderForType2.buildPartial());
        }

        @Override // com.google.protobuf.MessageReflection.MergeTarget
        public Object parseMessageFromBytes(ByteString bytes, ExtensionRegistryLite registry, Descriptors.FieldDescriptor field, Message defaultInstance) throws IOException {
            Message message;
            Message.Builder builderNewBuilderForType = defaultInstance.newBuilderForType();
            if (!field.isRepeated() && (message = (Message) getField(field)) != null) {
                builderNewBuilderForType.mergeFrom(message);
            }
            builderNewBuilderForType.mergeFrom(bytes, registry);
            return builderNewBuilderForType.buildPartial();
        }

        @Override // com.google.protobuf.MessageReflection.MergeTarget
        public WireFormat.Utf8Validation getUtf8Validation(Descriptors.FieldDescriptor descriptor) {
            if (descriptor.needsUtf8Check()) {
                return WireFormat.Utf8Validation.STRICT;
            }
            return WireFormat.Utf8Validation.LOOSE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0071  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static boolean mergeFieldFrom(com.google.protobuf.CodedInputStream r7, com.google.protobuf.UnknownFieldSet.Builder r8, com.google.protobuf.ExtensionRegistryLite r9, com.google.protobuf.Descriptors.Descriptor r10, com.google.protobuf.MessageReflection.MergeTarget r11, int r12) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 350
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageReflection.mergeFieldFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.UnknownFieldSet$Builder, com.google.protobuf.ExtensionRegistryLite, com.google.protobuf.Descriptors$Descriptor, com.google.protobuf.MessageReflection$MergeTarget, int):boolean");
    }

    /* JADX INFO: renamed from: com.google.protobuf.MessageReflection$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type;

        static {
            int[] iArr = new int[Descriptors.FieldDescriptor.Type.values().length];
            $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type = iArr;
            try {
                iArr[Descriptors.FieldDescriptor.Type.GROUP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.MESSAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.ENUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    static void mergeMessageFrom(Message.Builder target, UnknownFieldSet.Builder unknownFields, CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        int tag;
        BuilderAdapter builderAdapter = new BuilderAdapter(target);
        Descriptors.Descriptor descriptorForType = target.getDescriptorForType();
        do {
            tag = input.readTag();
            if (tag == 0) {
                return;
            }
        } while (mergeFieldFrom(input, unknownFields, extensionRegistry, descriptorForType, builderAdapter, tag));
    }

    private static void mergeMessageSetExtensionFromCodedStream(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, Descriptors.Descriptor type, MergeTarget target) throws IOException {
        int uInt32 = 0;
        ByteString bytes = null;
        ExtensionRegistry.ExtensionInfo extensionInfoFindExtensionByNumber = null;
        while (true) {
            int tag = input.readTag();
            if (tag == 0) {
                break;
            }
            if (tag == WireFormat.MESSAGE_SET_TYPE_ID_TAG) {
                uInt32 = input.readUInt32();
                if (uInt32 != 0 && (extensionRegistry instanceof ExtensionRegistry)) {
                    extensionInfoFindExtensionByNumber = target.findExtensionByNumber((ExtensionRegistry) extensionRegistry, type, uInt32);
                }
            } else if (tag == WireFormat.MESSAGE_SET_MESSAGE_TAG) {
                if (uInt32 != 0 && extensionInfoFindExtensionByNumber != null && ExtensionRegistryLite.isEagerlyParseMessageSets()) {
                    eagerlyMergeMessageSetExtension(input, extensionInfoFindExtensionByNumber, extensionRegistry, target);
                    bytes = null;
                } else {
                    bytes = input.readBytes();
                }
            } else if (!input.skipField(tag)) {
                break;
            }
        }
        input.checkLastTagWas(WireFormat.MESSAGE_SET_ITEM_END_TAG);
        if (bytes == null || uInt32 == 0) {
            return;
        }
        if (extensionInfoFindExtensionByNumber != null) {
            mergeMessageSetExtensionFromBytes(bytes, extensionInfoFindExtensionByNumber, extensionRegistry, target);
        } else if (unknownFields != null) {
            unknownFields.mergeField(uInt32, UnknownFieldSet.Field.newBuilder().addLengthDelimited(bytes).build());
        }
    }

    private static void mergeMessageSetExtensionFromBytes(ByteString rawBytes, ExtensionRegistry.ExtensionInfo extension, ExtensionRegistryLite extensionRegistry, MergeTarget target) throws IOException {
        Descriptors.FieldDescriptor fieldDescriptor = extension.descriptor;
        if (target.hasField(fieldDescriptor) || ExtensionRegistryLite.isEagerlyParseMessageSets()) {
            target.setField(fieldDescriptor, target.parseMessageFromBytes(rawBytes, extensionRegistry, fieldDescriptor, extension.defaultInstance));
        } else {
            target.setField(fieldDescriptor, new LazyField(extension.defaultInstance, extensionRegistry, rawBytes));
        }
    }

    private static void eagerlyMergeMessageSetExtension(CodedInputStream input, ExtensionRegistry.ExtensionInfo extension, ExtensionRegistryLite extensionRegistry, MergeTarget target) throws IOException {
        Descriptors.FieldDescriptor fieldDescriptor = extension.descriptor;
        target.setField(fieldDescriptor, target.parseMessage(input, extensionRegistry, fieldDescriptor, extension.defaultInstance));
    }
}
