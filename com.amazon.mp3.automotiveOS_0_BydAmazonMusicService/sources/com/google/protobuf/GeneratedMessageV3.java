package com.google.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DynamicMessage;
import com.google.protobuf.FieldSet;
import com.google.protobuf.Internal;
import com.google.protobuf.LazyField;
import com.google.protobuf.Message;
import com.google.protobuf.MessageReflection;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes7.dex */
public abstract class GeneratedMessageV3 extends AbstractMessage implements Serializable {
    protected static boolean alwaysUseFieldBuilders = false;
    protected UnknownFieldSet unknownFields;

    /* JADX INFO: Access modifiers changed from: protected */
    public interface BuilderParent extends AbstractMessage.BuilderParent {
    }

    public interface ExtendableMessageOrBuilder<MessageT extends ExtendableMessage<MessageT>> extends MessageOrBuilder {
        @Override // com.google.protobuf.MessageOrBuilder, com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        Message getDefaultInstanceForType();
    }

    protected abstract FieldAccessorTable internalGetFieldAccessorTable();

    protected void makeExtensionsImmutable() {
    }

    protected abstract Message.Builder newBuilderForType(BuilderParent parent);

    protected GeneratedMessageV3() {
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }

    protected GeneratedMessageV3(Builder<?> builder) {
        this.unknownFields = builder.getUnknownFields();
    }

    @Override // com.google.protobuf.MessageLite
    public Parser<? extends GeneratedMessageV3> getParserForType() {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public Descriptors.Descriptor getDescriptorForType() {
        return internalGetFieldAccessorTable().descriptor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0066  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Map<com.google.protobuf.Descriptors.FieldDescriptor, java.lang.Object> getAllFieldsMutable(boolean r7) {
        /*
            r6 = this;
            java.util.TreeMap r0 = new java.util.TreeMap
            r0.<init>()
            com.google.protobuf.GeneratedMessageV3$FieldAccessorTable r1 = r6.internalGetFieldAccessorTable()
            com.google.protobuf.Descriptors$Descriptor r1 = com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.access$000(r1)
            java.util.List r1 = r1.getFields()
            r2 = 0
        L12:
            int r3 = r1.size()
            if (r2 >= r3) goto L70
            java.lang.Object r3 = r1.get(r2)
            com.google.protobuf.Descriptors$FieldDescriptor r3 = (com.google.protobuf.Descriptors.FieldDescriptor) r3
            com.google.protobuf.Descriptors$OneofDescriptor r4 = r3.getContainingOneof()
            if (r4 == 0) goto L37
            int r3 = r4.getFieldCount()
            int r3 = r3 + (-1)
            int r2 = r2 + r3
            boolean r3 = r6.hasOneof(r4)
            if (r3 != 0) goto L32
            goto L6d
        L32:
            com.google.protobuf.Descriptors$FieldDescriptor r3 = r6.getOneofFieldDescriptor(r4)
            goto L54
        L37:
            boolean r4 = r3.isRepeated()
            if (r4 == 0) goto L4d
            java.lang.Object r4 = r6.getField(r3)
            java.util.List r4 = (java.util.List) r4
            boolean r5 = r4.isEmpty()
            if (r5 != 0) goto L6d
            r0.put(r3, r4)
            goto L6d
        L4d:
            boolean r4 = r6.hasField(r3)
            if (r4 != 0) goto L54
            goto L6d
        L54:
            if (r7 == 0) goto L66
            com.google.protobuf.Descriptors$FieldDescriptor$JavaType r4 = r3.getJavaType()
            com.google.protobuf.Descriptors$FieldDescriptor$JavaType r5 = com.google.protobuf.Descriptors.FieldDescriptor.JavaType.STRING
            if (r4 != r5) goto L66
            java.lang.Object r4 = r6.getFieldRaw(r3)
            r0.put(r3, r4)
            goto L6d
        L66:
            java.lang.Object r4 = r6.getField(r3)
            r0.put(r3, r4)
        L6d:
            int r2 = r2 + 1
            goto L12
        L70:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.GeneratedMessageV3.getAllFieldsMutable(boolean):java.util.Map");
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
    public boolean isInitialized() {
        for (Descriptors.FieldDescriptor fieldDescriptor : getDescriptorForType().getFields()) {
            if (fieldDescriptor.isRequired() && !hasField(fieldDescriptor)) {
                return false;
            }
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                if (fieldDescriptor.isRepeated()) {
                    Iterator it = ((List) getField(fieldDescriptor)).iterator();
                    while (it.hasNext()) {
                        if (!((Message) it.next()).isInitialized()) {
                            return false;
                        }
                    }
                } else if (hasField(fieldDescriptor) && !((Message) getField(fieldDescriptor)).isInitialized()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
        return Collections.unmodifiableMap(getAllFieldsMutable(false));
    }

    Map<Descriptors.FieldDescriptor, Object> getAllFieldsRaw() {
        return Collections.unmodifiableMap(getAllFieldsMutable(true));
    }

    public boolean hasOneof(final Descriptors.OneofDescriptor oneof) {
        return internalGetFieldAccessorTable().getOneof(oneof).has(this);
    }

    public Descriptors.FieldDescriptor getOneofFieldDescriptor(final Descriptors.OneofDescriptor oneof) {
        return internalGetFieldAccessorTable().getOneof(oneof).get(this);
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public boolean hasField(final Descriptors.FieldDescriptor field) {
        return internalGetFieldAccessorTable().getField(field).has(this);
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public Object getField(final Descriptors.FieldDescriptor field) {
        return internalGetFieldAccessorTable().getField(field).get(this);
    }

    Object getFieldRaw(final Descriptors.FieldDescriptor field) {
        return internalGetFieldAccessorTable().getField(field).getRaw(this);
    }

    public UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected boolean parseUnknownField(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
        if (input.shouldDiscardUnknownFields()) {
            return input.skipField(tag);
        }
        return unknownFields.mergeFieldFrom(tag, input);
    }

    protected static Internal.IntList emptyIntList() {
        return IntArrayList.emptyList();
    }

    protected static Internal.IntList mutableCopy(Internal.IntList list) {
        return (Internal.IntList) makeMutableCopy(list);
    }

    protected static <ListT extends Internal.ProtobufList<?>> ListT makeMutableCopy(ListT listt) {
        int size = listt.size();
        return (ListT) listt.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public void writeTo(final CodedOutputStream output) throws IOException {
        MessageReflection.writeMessageTo(this, getAllFieldsRaw(), output, false);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int serializedSize = MessageReflection.getSerializedSize(this, getAllFieldsRaw());
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    protected static final class UnusedPrivateParameter {
        static final UnusedPrivateParameter INSTANCE = new UnusedPrivateParameter();

        private UnusedPrivateParameter() {
        }
    }

    protected Object newInstance(UnusedPrivateParameter unused) {
        throw new UnsupportedOperationException("This method must be overridden by the subclass.");
    }

    @Override // com.google.protobuf.AbstractMessage
    protected Message.Builder newBuilderForType(final AbstractMessage.BuilderParent parent) {
        return newBuilderForType(new BuilderParent() { // from class: com.google.protobuf.GeneratedMessageV3.1
            @Override // com.google.protobuf.AbstractMessage.BuilderParent
            public void markDirty() {
                parent.markDirty();
            }
        });
    }

    public static abstract class Builder<BuilderT extends Builder<BuilderT>> extends AbstractMessage.Builder<BuilderT> {
        private BuilderParent builderParent;
        private boolean isClean;
        private Builder<BuilderT>.BuilderParentImpl meAsParent;
        private Object unknownFieldsOrBuilder;

        protected abstract FieldAccessorTable internalGetFieldAccessorTable();

        protected Builder() {
            this(null);
        }

        protected Builder(BuilderParent builderParent) {
            this.unknownFieldsOrBuilder = UnknownFieldSet.getDefaultInstance();
            this.builderParent = builderParent;
        }

        protected void onBuilt() {
            if (this.builderParent != null) {
                markClean();
            }
        }

        @Override // com.google.protobuf.AbstractMessage.Builder
        protected void markClean() {
            this.isClean = true;
        }

        protected boolean isClean() {
            return this.isClean;
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
        /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
        public BuilderT mo76clone() {
            BuilderT buildert = (BuilderT) getDefaultInstanceForType().newBuilderForType();
            buildert.mergeFrom(buildPartial());
            return buildert;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return internalGetFieldAccessorTable().descriptor;
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
            return Collections.unmodifiableMap(getAllFieldsMutable());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Map<Descriptors.FieldDescriptor, Object> getAllFieldsMutable() {
            TreeMap treeMap = new TreeMap();
            List<Descriptors.FieldDescriptor> fields = internalGetFieldAccessorTable().descriptor.getFields();
            int fieldCount = 0;
            while (fieldCount < fields.size()) {
                Descriptors.FieldDescriptor oneofFieldDescriptor = fields.get(fieldCount);
                Descriptors.OneofDescriptor containingOneof = oneofFieldDescriptor.getContainingOneof();
                if (containingOneof != null) {
                    fieldCount += containingOneof.getFieldCount() - 1;
                    if (hasOneof(containingOneof)) {
                        oneofFieldDescriptor = getOneofFieldDescriptor(containingOneof);
                        treeMap.put(oneofFieldDescriptor, getField(oneofFieldDescriptor));
                    }
                } else if (oneofFieldDescriptor.isRepeated()) {
                    List list = (List) getField(oneofFieldDescriptor);
                    if (!list.isEmpty()) {
                        treeMap.put(oneofFieldDescriptor, list);
                    }
                } else if (hasField(oneofFieldDescriptor)) {
                    treeMap.put(oneofFieldDescriptor, getField(oneofFieldDescriptor));
                }
                fieldCount++;
            }
            return treeMap;
        }

        @Override // com.google.protobuf.Message.Builder
        public Message.Builder newBuilderForField(final Descriptors.FieldDescriptor field) {
            return internalGetFieldAccessorTable().getField(field).newBuilder();
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Message.Builder getFieldBuilder(final Descriptors.FieldDescriptor field) {
            return internalGetFieldAccessorTable().getField(field).getBuilder(this);
        }

        public boolean hasOneof(final Descriptors.OneofDescriptor oneof) {
            return internalGetFieldAccessorTable().getOneof(oneof).has((Builder<?>) this);
        }

        public Descriptors.FieldDescriptor getOneofFieldDescriptor(final Descriptors.OneofDescriptor oneof) {
            return internalGetFieldAccessorTable().getOneof(oneof).get((Builder<?>) this);
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public boolean hasField(final Descriptors.FieldDescriptor field) {
            return internalGetFieldAccessorTable().getField(field).has((Builder<?>) this);
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public Object getField(final Descriptors.FieldDescriptor field) {
            Object obj = internalGetFieldAccessorTable().getField(field).get((Builder<?>) this);
            return field.isRepeated() ? Collections.unmodifiableList((List) obj) : obj;
        }

        public BuilderT setField(final Descriptors.FieldDescriptor field, final Object value) {
            internalGetFieldAccessorTable().getField(field).set(this, value);
            return this;
        }

        public BuilderT addRepeatedField(final Descriptors.FieldDescriptor field, final Object value) {
            internalGetFieldAccessorTable().getField(field).addRepeated(this, value);
            return this;
        }

        private BuilderT setUnknownFieldsInternal(final UnknownFieldSet unknownFields) {
            this.unknownFieldsOrBuilder = unknownFields;
            onChanged();
            return this;
        }

        public BuilderT setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (BuilderT) setUnknownFieldsInternal(unknownFieldSet);
        }

        @Override // com.google.protobuf.AbstractMessage.Builder
        public BuilderT mergeUnknownFields(final UnknownFieldSet unknownFields) {
            if (UnknownFieldSet.getDefaultInstance().equals(unknownFields)) {
                return this;
            }
            if (UnknownFieldSet.getDefaultInstance().equals(this.unknownFieldsOrBuilder)) {
                this.unknownFieldsOrBuilder = unknownFields;
                onChanged();
                return this;
            }
            getUnknownFieldSetBuilder().mergeFrom(unknownFields);
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder
        public boolean isInitialized() {
            for (Descriptors.FieldDescriptor fieldDescriptor : getDescriptorForType().getFields()) {
                if (fieldDescriptor.isRequired() && !hasField(fieldDescriptor)) {
                    return false;
                }
                if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    if (fieldDescriptor.isRepeated()) {
                        Iterator it = ((List) getField(fieldDescriptor)).iterator();
                        while (it.hasNext()) {
                            if (!((Message) it.next()).isInitialized()) {
                                return false;
                            }
                        }
                    } else if (hasField(fieldDescriptor) && !((Message) getField(fieldDescriptor)).isInitialized()) {
                        return false;
                    }
                }
            }
            return true;
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            Object obj = this.unknownFieldsOrBuilder;
            if (obj instanceof UnknownFieldSet) {
                return (UnknownFieldSet) obj;
            }
            return ((UnknownFieldSet.Builder) obj).buildPartial();
        }

        protected boolean parseUnknownField(CodedInputStream input, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
            if (input.shouldDiscardUnknownFields()) {
                return input.skipField(tag);
            }
            return getUnknownFieldSetBuilder().mergeFieldFrom(tag, input);
        }

        protected final void mergeUnknownVarintField(int number, int value) {
            getUnknownFieldSetBuilder().mergeVarintField(number, value);
        }

        @Override // com.google.protobuf.AbstractMessage.Builder
        protected UnknownFieldSet.Builder getUnknownFieldSetBuilder() {
            Object obj = this.unknownFieldsOrBuilder;
            if (obj instanceof UnknownFieldSet) {
                this.unknownFieldsOrBuilder = ((UnknownFieldSet) obj).toBuilder();
            }
            onChanged();
            return (UnknownFieldSet.Builder) this.unknownFieldsOrBuilder;
        }

        @Override // com.google.protobuf.AbstractMessage.Builder
        protected void setUnknownFieldSetBuilder(UnknownFieldSet.Builder builder) {
            this.unknownFieldsOrBuilder = builder;
            onChanged();
        }

        private class BuilderParentImpl implements BuilderParent {
            private BuilderParentImpl() {
            }

            @Override // com.google.protobuf.AbstractMessage.BuilderParent
            public void markDirty() {
                Builder.this.onChanged();
            }
        }

        protected BuilderParent getParentForChildren() {
            if (this.meAsParent == null) {
                this.meAsParent = new BuilderParentImpl();
            }
            return this.meAsParent;
        }

        protected final void onChanged() {
            BuilderParent builderParent;
            if (!this.isClean || (builderParent = this.builderParent) == null) {
                return;
            }
            builderParent.markDirty();
            this.isClean = false;
        }

        protected MapField internalGetMapField(int fieldNumber) {
            throw new IllegalArgumentException("No map fields found in " + getClass().getName());
        }

        protected MapField internalGetMutableMapField(int fieldNumber) {
            throw new RuntimeException("No map fields found in " + getClass().getName());
        }
    }

    public static abstract class ExtendableMessage<MessageT extends ExtendableMessage<MessageT>> extends GeneratedMessageV3 implements ExtendableMessageOrBuilder<MessageT> {
        private final FieldSet<Descriptors.FieldDescriptor> extensions;

        protected ExtendableMessage() {
            this.extensions = FieldSet.newFieldSet();
        }

        protected ExtendableMessage(ExtendableBuilder<MessageT, ?> builder) {
            super(builder);
            this.extensions = builder.buildExtensions();
        }

        protected boolean extensionsAreInitialized() {
            return this.extensions.isInitialized();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public boolean isInitialized() {
            return super.isInitialized() && extensionsAreInitialized();
        }

        protected class ExtensionWriter {
            private final Iterator<Map.Entry<Descriptors.FieldDescriptor, Object>> iter;
            private final boolean messageSetWireFormat;
            private Map.Entry<Descriptors.FieldDescriptor, Object> next;

            private ExtensionWriter(final boolean messageSetWireFormat) {
                Iterator<Map.Entry<Descriptors.FieldDescriptor, Object>> it = ExtendableMessage.this.extensions.iterator();
                this.iter = it;
                if (it.hasNext()) {
                    this.next = it.next();
                }
                this.messageSetWireFormat = messageSetWireFormat;
            }

            public void writeUntil(final int end, final CodedOutputStream output) throws IOException {
                while (true) {
                    Map.Entry<Descriptors.FieldDescriptor, Object> entry = this.next;
                    if (entry == null || entry.getKey().getNumber() >= end) {
                        return;
                    }
                    Descriptors.FieldDescriptor key = this.next.getKey();
                    if (this.messageSetWireFormat && key.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !key.isRepeated()) {
                        if (this.next instanceof LazyField.LazyEntry) {
                            output.writeRawMessageSetExtension(key.getNumber(), ((LazyField.LazyEntry) this.next).getField().toByteString());
                        } else {
                            output.writeMessageSetExtension(key.getNumber(), (Message) this.next.getValue());
                        }
                    } else {
                        FieldSet.writeField(key, this.next.getValue(), output);
                    }
                    if (this.iter.hasNext()) {
                        this.next = this.iter.next();
                    } else {
                        this.next = null;
                    }
                }
            }
        }

        protected ExtendableMessage<MessageT>.ExtensionWriter newExtensionWriter() {
            return new ExtensionWriter(false);
        }

        protected int extensionsSerializedSize() {
            return this.extensions.getSerializedSize();
        }

        protected Map<Descriptors.FieldDescriptor, Object> getExtensionFields() {
            return this.extensions.getAllFields();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
            Map allFieldsMutable = getAllFieldsMutable(false);
            allFieldsMutable.putAll(getExtensionFields());
            return Collections.unmodifiableMap(allFieldsMutable);
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        public Map<Descriptors.FieldDescriptor, Object> getAllFieldsRaw() {
            Map allFieldsMutable = getAllFieldsMutable(false);
            allFieldsMutable.putAll(getExtensionFields());
            return Collections.unmodifiableMap(allFieldsMutable);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public boolean hasField(final Descriptors.FieldDescriptor field) {
            if (field.isExtension()) {
                verifyContainingType(field);
                return this.extensions.hasField(field);
            }
            return super.hasField(field);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public Object getField(final Descriptors.FieldDescriptor field) {
            if (field.isExtension()) {
                verifyContainingType(field);
                Object field2 = this.extensions.getField(field);
                if (field2 != null) {
                    return field2;
                }
                if (field.isRepeated()) {
                    return Collections.emptyList();
                }
                if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    return DynamicMessage.getDefaultInstance(field.getMessageType());
                }
                return field.getDefaultValue();
            }
            return super.getField(field);
        }

        private void verifyContainingType(final Descriptors.FieldDescriptor field) {
            if (field.getContainingType() != getDescriptorForType()) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }
    }

    public static abstract class ExtendableBuilder<MessageT extends ExtendableMessage<MessageT>, BuilderT extends ExtendableBuilder<MessageT, BuilderT>> extends Builder<BuilderT> implements ExtendableMessageOrBuilder<MessageT> {
        private FieldSet.Builder<Descriptors.FieldDescriptor> extensions;

        protected ExtendableBuilder() {
        }

        protected ExtendableBuilder(BuilderParent parent) {
            super(parent);
        }

        private void ensureExtensionsIsMutable() {
            if (this.extensions == null) {
                this.extensions = FieldSet.newBuilder();
            }
        }

        protected boolean extensionsAreInitialized() {
            FieldSet.Builder<Descriptors.FieldDescriptor> builder = this.extensions;
            return builder == null || builder.isInitialized();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public FieldSet<Descriptors.FieldDescriptor> buildExtensions() {
            FieldSet.Builder<Descriptors.FieldDescriptor> builder = this.extensions;
            if (builder == null) {
                return FieldSet.emptySet();
            }
            return builder.buildPartial();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public boolean isInitialized() {
            return super.isInitialized() && extensionsAreInitialized();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageOrBuilder
        public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
            Map allFieldsMutable = getAllFieldsMutable();
            FieldSet.Builder<Descriptors.FieldDescriptor> builder = this.extensions;
            if (builder != null) {
                allFieldsMutable.putAll(builder.getAllFields());
            }
            return Collections.unmodifiableMap(allFieldsMutable);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageOrBuilder
        public Object getField(final Descriptors.FieldDescriptor field) {
            if (field.isExtension()) {
                verifyContainingType(field);
                FieldSet.Builder<Descriptors.FieldDescriptor> builder = this.extensions;
                Object field2 = builder == null ? null : builder.getField(field);
                if (field2 != null) {
                    return field2;
                }
                if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    return DynamicMessage.getDefaultInstance(field.getMessageType());
                }
                return field.getDefaultValue();
            }
            return super.getField(field);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Message.Builder getFieldBuilder(final Descriptors.FieldDescriptor field) {
            if (field.isExtension()) {
                verifyContainingType(field);
                if (field.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
                }
                ensureExtensionsIsMutable();
                Object fieldAllowBuilders = this.extensions.getFieldAllowBuilders(field);
                if (fieldAllowBuilders == null) {
                    DynamicMessage.Builder builderNewBuilder = DynamicMessage.newBuilder(field.getMessageType());
                    this.extensions.setField(field, builderNewBuilder);
                    onChanged();
                    return builderNewBuilder;
                }
                if (fieldAllowBuilders instanceof Message.Builder) {
                    return (Message.Builder) fieldAllowBuilders;
                }
                if (fieldAllowBuilders instanceof Message) {
                    Message.Builder builder = ((Message) fieldAllowBuilders).toBuilder();
                    this.extensions.setField(field, builder);
                    onChanged();
                    return builder;
                }
                throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
            }
            return super.getFieldBuilder(field);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageOrBuilder
        public boolean hasField(final Descriptors.FieldDescriptor field) {
            if (field.isExtension()) {
                verifyContainingType(field);
                FieldSet.Builder<Descriptors.FieldDescriptor> builder = this.extensions;
                return builder != null && builder.hasField(field);
            }
            return super.hasField(field);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public BuilderT setField(final Descriptors.FieldDescriptor field, final Object value) {
            if (field.isExtension()) {
                verifyContainingType(field);
                ensureExtensionsIsMutable();
                this.extensions.setField(field, value);
                onChanged();
                return this;
            }
            return (BuilderT) super.setField(field, value);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public BuilderT addRepeatedField(final Descriptors.FieldDescriptor field, final Object value) {
            if (field.isExtension()) {
                verifyContainingType(field);
                ensureExtensionsIsMutable();
                this.extensions.addRepeatedField(field, value);
                onChanged();
                return this;
            }
            return (BuilderT) super.addRepeatedField(field, value);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Message.Builder newBuilderForField(final Descriptors.FieldDescriptor field) {
            if (field.isExtension()) {
                return DynamicMessage.newBuilder(field.getMessageType());
            }
            return super.newBuilderForField(field);
        }

        protected final void mergeExtensionFields(final ExtendableMessage<?> other) {
            if (((ExtendableMessage) other).extensions != null) {
                ensureExtensionsIsMutable();
                this.extensions.mergeFrom(((ExtendableMessage) other).extensions);
                onChanged();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected boolean parseUnknownField(CodedInputStream input, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
            ensureExtensionsIsMutable();
            return MessageReflection.mergeFieldFrom(input, input.shouldDiscardUnknownFields() ? null : getUnknownFieldSetBuilder(), extensionRegistry, getDescriptorForType(), new MessageReflection.ExtensionBuilderAdapter(this.extensions), tag);
        }

        private void verifyContainingType(final Descriptors.FieldDescriptor field) {
            if (field.getContainingType() != getDescriptorForType()) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Method getMethodOrDie(final Class<?> clazz, final String name, final Class<?>... params) {
        try {
            return clazz.getMethod(name, params);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Generated message class \"" + clazz.getName() + "\" missing method \"" + name + "\".", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object invokeOrDie(final Method method, final Object object, final Object... params) {
        try {
            return method.invoke(object, params);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new IllegalStateException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    protected MapField internalGetMapField(int fieldNumber) {
        throw new IllegalArgumentException("No map fields found in " + getClass().getName());
    }

    public static final class FieldAccessorTable {
        private String[] camelCaseNames;
        private final Descriptors.Descriptor descriptor;
        private final FieldAccessor[] fields;
        private volatile boolean initialized = false;
        private final OneofAccessor[] oneofs;

        private interface FieldAccessor {
            void addRepeated(Builder<?> builder, Object value);

            Object get(Builder<?> builder);

            Object get(GeneratedMessageV3 message);

            Message.Builder getBuilder(Builder<?> builder);

            Object getRaw(GeneratedMessageV3 message);

            boolean has(Builder<?> builder);

            boolean has(GeneratedMessageV3 message);

            Message.Builder newBuilder();

            void set(Builder<?> builder, Object value);
        }

        public FieldAccessorTable(final Descriptors.Descriptor descriptor, final String[] camelCaseNames) {
            this.descriptor = descriptor;
            this.camelCaseNames = camelCaseNames;
            this.fields = new FieldAccessor[descriptor.getFields().size()];
            this.oneofs = new OneofAccessor[descriptor.getOneofs().size()];
        }

        public FieldAccessorTable ensureFieldAccessorsInitialized(Class<? extends GeneratedMessageV3> messageClass, Class<? extends Builder<?>> builderClass) {
            if (this.initialized) {
                return this;
            }
            synchronized (this) {
                if (this.initialized) {
                    return this;
                }
                int length = this.fields.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Descriptors.FieldDescriptor fieldDescriptor = this.descriptor.getFields().get(i);
                    String str = fieldDescriptor.getContainingOneof() != null ? this.camelCaseNames[fieldDescriptor.getContainingOneof().getIndex() + length] : null;
                    if (fieldDescriptor.isRepeated()) {
                        if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                            if (fieldDescriptor.isMapField()) {
                                this.fields[i] = new MapFieldAccessor(fieldDescriptor, messageClass);
                            } else {
                                this.fields[i] = new RepeatedMessageFieldAccessor(fieldDescriptor, this.camelCaseNames[i], messageClass, builderClass);
                            }
                        } else if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
                            this.fields[i] = new RepeatedEnumFieldAccessor(fieldDescriptor, this.camelCaseNames[i], messageClass, builderClass);
                        } else {
                            this.fields[i] = new RepeatedFieldAccessor(fieldDescriptor, this.camelCaseNames[i], messageClass, builderClass);
                        }
                    } else if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                        this.fields[i] = new SingularMessageFieldAccessor(fieldDescriptor, this.camelCaseNames[i], messageClass, builderClass, str);
                    } else if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
                        this.fields[i] = new SingularEnumFieldAccessor(fieldDescriptor, this.camelCaseNames[i], messageClass, builderClass, str);
                    } else if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.STRING) {
                        this.fields[i] = new SingularStringFieldAccessor(fieldDescriptor, this.camelCaseNames[i], messageClass, builderClass, str);
                    } else {
                        this.fields[i] = new SingularFieldAccessor(fieldDescriptor, this.camelCaseNames[i], messageClass, builderClass, str);
                    }
                    i++;
                }
                int length2 = this.oneofs.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    this.oneofs[i2] = new OneofAccessor(this.descriptor, i2, this.camelCaseNames[i2 + length], messageClass, builderClass);
                }
                this.initialized = true;
                this.camelCaseNames = null;
                return this;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public FieldAccessor getField(final Descriptors.FieldDescriptor field) {
            if (field.getContainingType() != this.descriptor) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
            if (field.isExtension()) {
                throw new IllegalArgumentException("This type does not have extensions.");
            }
            return this.fields[field.getIndex()];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public OneofAccessor getOneof(final Descriptors.OneofDescriptor oneof) {
            if (oneof.getContainingType() != this.descriptor) {
                throw new IllegalArgumentException("OneofDescriptor does not match message type.");
            }
            return this.oneofs[oneof.getIndex()];
        }

        private static class OneofAccessor {
            private final Method caseMethod;
            private final Method caseMethodBuilder;
            private final Method clearMethod;
            private final Descriptors.Descriptor descriptor;
            private final Descriptors.FieldDescriptor fieldDescriptor;

            OneofAccessor(final Descriptors.Descriptor descriptor, final int oneofIndex, final String camelCaseName, final Class<? extends GeneratedMessageV3> messageClass, final Class<? extends Builder<?>> builderClass) {
                this.descriptor = descriptor;
                Descriptors.OneofDescriptor oneofDescriptor = descriptor.getOneofs().get(oneofIndex);
                if (oneofDescriptor.isSynthetic()) {
                    this.caseMethod = null;
                    this.caseMethodBuilder = null;
                    this.fieldDescriptor = oneofDescriptor.getFields().get(0);
                } else {
                    this.caseMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName + "Case", new Class[0]);
                    this.caseMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "Case", new Class[0]);
                    this.fieldDescriptor = null;
                }
                this.clearMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "clear" + camelCaseName, new Class[0]);
            }

            public boolean has(final GeneratedMessageV3 message) {
                Descriptors.FieldDescriptor fieldDescriptor = this.fieldDescriptor;
                if (fieldDescriptor != null) {
                    return message.hasField(fieldDescriptor);
                }
                return ((Internal.EnumLite) GeneratedMessageV3.invokeOrDie(this.caseMethod, message, new Object[0])).getNumber() != 0;
            }

            public boolean has(Builder<?> builder) {
                Descriptors.FieldDescriptor fieldDescriptor = this.fieldDescriptor;
                if (fieldDescriptor != null) {
                    return builder.hasField(fieldDescriptor);
                }
                return ((Internal.EnumLite) GeneratedMessageV3.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber() != 0;
            }

            public Descriptors.FieldDescriptor get(final GeneratedMessageV3 message) {
                Descriptors.FieldDescriptor fieldDescriptor = this.fieldDescriptor;
                if (fieldDescriptor == null) {
                    int number = ((Internal.EnumLite) GeneratedMessageV3.invokeOrDie(this.caseMethod, message, new Object[0])).getNumber();
                    if (number > 0) {
                        return this.descriptor.findFieldByNumber(number);
                    }
                    return null;
                }
                if (message.hasField(fieldDescriptor)) {
                    return this.fieldDescriptor;
                }
                return null;
            }

            public Descriptors.FieldDescriptor get(Builder<?> builder) {
                Descriptors.FieldDescriptor fieldDescriptor = this.fieldDescriptor;
                if (fieldDescriptor == null) {
                    int number = ((Internal.EnumLite) GeneratedMessageV3.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber();
                    if (number > 0) {
                        return this.descriptor.findFieldByNumber(number);
                    }
                    return null;
                }
                if (builder.hasField(fieldDescriptor)) {
                    return this.fieldDescriptor;
                }
                return null;
            }
        }

        private static class SingularFieldAccessor implements FieldAccessor {
            protected final Descriptors.FieldDescriptor field;
            protected final boolean hasHasMethod;
            protected final MethodInvoker invoker;
            protected final boolean isOneofField;
            protected final Class<?> type;

            private interface MethodInvoker {
                Object get(Builder<?> builder);

                Object get(final GeneratedMessageV3 message);

                int getOneofFieldNumber(final Builder<?> builder);

                int getOneofFieldNumber(final GeneratedMessageV3 message);

                boolean has(Builder<?> builder);

                boolean has(final GeneratedMessageV3 message);

                void set(final Builder<?> builder, final Object value);
            }

            static MethodInvoker getMethodInvoker(ReflectionInvoker accessor) {
                return accessor;
            }

            private static final class ReflectionInvoker implements MethodInvoker {
                private final Method caseMethod;
                private final Method caseMethodBuilder;
                private final Method clearMethod;
                private final Method getMethod;
                private final Method getMethodBuilder;
                private final Method hasMethod;
                private final Method hasMethodBuilder;
                private final Method setMethod;

                ReflectionInvoker(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class<? extends GeneratedMessageV3> messageClass, final Class<? extends Builder<?>> builderClass, final String containingOneofCamelCaseName, boolean isOneofField, boolean hasHasMethod) {
                    Method methodOrDie;
                    Method methodOrDie2;
                    Method methodOrDie3;
                    Method methodOrDie4 = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName, new Class[0]);
                    this.getMethod = methodOrDie4;
                    this.getMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName, new Class[0]);
                    this.setMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "set" + camelCaseName, methodOrDie4.getReturnType());
                    Method methodOrDie5 = null;
                    if (hasHasMethod) {
                        methodOrDie = GeneratedMessageV3.getMethodOrDie(messageClass, "has" + camelCaseName, new Class[0]);
                    } else {
                        methodOrDie = null;
                    }
                    this.hasMethod = methodOrDie;
                    if (hasHasMethod) {
                        methodOrDie2 = GeneratedMessageV3.getMethodOrDie(builderClass, "has" + camelCaseName, new Class[0]);
                    } else {
                        methodOrDie2 = null;
                    }
                    this.hasMethodBuilder = methodOrDie2;
                    this.clearMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "clear" + camelCaseName, new Class[0]);
                    if (isOneofField) {
                        methodOrDie3 = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + containingOneofCamelCaseName + "Case", new Class[0]);
                    } else {
                        methodOrDie3 = null;
                    }
                    this.caseMethod = methodOrDie3;
                    if (isOneofField) {
                        methodOrDie5 = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + containingOneofCamelCaseName + "Case", new Class[0]);
                    }
                    this.caseMethodBuilder = methodOrDie5;
                }

                @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.SingularFieldAccessor.MethodInvoker
                public Object get(final GeneratedMessageV3 message) {
                    return GeneratedMessageV3.invokeOrDie(this.getMethod, message, new Object[0]);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.SingularFieldAccessor.MethodInvoker
                public Object get(Builder<?> builder) {
                    return GeneratedMessageV3.invokeOrDie(this.getMethodBuilder, builder, new Object[0]);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.SingularFieldAccessor.MethodInvoker
                public int getOneofFieldNumber(final GeneratedMessageV3 message) {
                    return ((Internal.EnumLite) GeneratedMessageV3.invokeOrDie(this.caseMethod, message, new Object[0])).getNumber();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.SingularFieldAccessor.MethodInvoker
                public int getOneofFieldNumber(final Builder<?> builder) {
                    return ((Internal.EnumLite) GeneratedMessageV3.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.SingularFieldAccessor.MethodInvoker
                public void set(final Builder<?> builder, final Object value) {
                    GeneratedMessageV3.invokeOrDie(this.setMethod, builder, value);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.SingularFieldAccessor.MethodInvoker
                public boolean has(final GeneratedMessageV3 message) {
                    return ((Boolean) GeneratedMessageV3.invokeOrDie(this.hasMethod, message, new Object[0])).booleanValue();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.SingularFieldAccessor.MethodInvoker
                public boolean has(Builder<?> builder) {
                    return ((Boolean) GeneratedMessageV3.invokeOrDie(this.hasMethodBuilder, builder, new Object[0])).booleanValue();
                }
            }

            SingularFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class<? extends GeneratedMessageV3> messageClass, final Class<? extends Builder<?>> builderClass, final String containingOneofCamelCaseName) {
                boolean z = (descriptor.getContainingOneof() == null || descriptor.getContainingOneof().isSynthetic()) ? false : true;
                this.isOneofField = z;
                boolean z2 = descriptor.getFile().getSyntax() == Descriptors.FileDescriptor.Syntax.PROTO2 || descriptor.hasOptionalKeyword() || (!z && descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE);
                this.hasHasMethod = z2;
                ReflectionInvoker reflectionInvoker = new ReflectionInvoker(descriptor, camelCaseName, messageClass, builderClass, containingOneofCamelCaseName, z, z2);
                this.field = descriptor;
                this.type = reflectionInvoker.getMethod.getReturnType();
                this.invoker = getMethodInvoker(reflectionInvoker);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Object get(final GeneratedMessageV3 message) {
                return this.invoker.get(message);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Object get(Builder<?> builder) {
                return this.invoker.get(builder);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Object getRaw(final GeneratedMessageV3 message) {
                return get(message);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public void set(final Builder<?> builder, final Object value) {
                this.invoker.set(builder, value);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public void addRepeated(final Builder<?> builder, final Object value) {
                throw new UnsupportedOperationException("addRepeatedField() called on a singular field.");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public boolean has(final GeneratedMessageV3 message) {
                if (!this.hasHasMethod) {
                    if (this.isOneofField) {
                        return this.invoker.getOneofFieldNumber(message) == this.field.getNumber();
                    }
                    return !get(message).equals(this.field.getDefaultValue());
                }
                return this.invoker.has(message);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public boolean has(Builder<?> builder) {
                if (!this.hasHasMethod) {
                    if (this.isOneofField) {
                        return this.invoker.getOneofFieldNumber(builder) == this.field.getNumber();
                    }
                    return !get(builder).equals(this.field.getDefaultValue());
                }
                return this.invoker.has(builder);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Message.Builder newBuilder() {
                throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Message.Builder getBuilder(Builder<?> builder) {
                throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
            }
        }

        private static class RepeatedFieldAccessor implements FieldAccessor {
            protected final MethodInvoker invoker;
            protected final Class<?> type;

            interface MethodInvoker {
                void addRepeated(final Builder<?> builder, final Object value);

                void clear(final Builder<?> builder);

                Object get(Builder<?> builder);

                Object get(final GeneratedMessageV3 message);

                Object getRepeated(Builder<?> builder, int index);

                Object getRepeated(final GeneratedMessageV3 message, final int index);

                int getRepeatedCount(Builder<?> builder);

                int getRepeatedCount(final GeneratedMessageV3 message);
            }

            static MethodInvoker getMethodInvoker(ReflectionInvoker accessor) {
                return accessor;
            }

            private static final class ReflectionInvoker implements MethodInvoker {
                private final Method addRepeatedMethod;
                private final Method clearMethod;
                private final Method getCountMethod;
                private final Method getCountMethodBuilder;
                private final Method getMethod;
                private final Method getMethodBuilder;
                private final Method getRepeatedMethod;
                private final Method getRepeatedMethodBuilder;
                private final Method setRepeatedMethod;

                ReflectionInvoker(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class<? extends GeneratedMessageV3> messageClass, final Class<? extends Builder<?>> builderClass) {
                    this.getMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName + "List", new Class[0]);
                    this.getMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "List", new Class[0]);
                    StringBuilder sb = new StringBuilder();
                    sb.append("get");
                    sb.append(camelCaseName);
                    String string = sb.toString();
                    Class cls = Integer.TYPE;
                    Method methodOrDie = GeneratedMessageV3.getMethodOrDie(messageClass, string, cls);
                    this.getRepeatedMethod = methodOrDie;
                    this.getRepeatedMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName, cls);
                    Class<?> returnType = methodOrDie.getReturnType();
                    this.setRepeatedMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "set" + camelCaseName, cls, returnType);
                    this.addRepeatedMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "add" + camelCaseName, returnType);
                    this.getCountMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName + "Count", new Class[0]);
                    this.getCountMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "Count", new Class[0]);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("clear");
                    sb2.append(camelCaseName);
                    this.clearMethod = GeneratedMessageV3.getMethodOrDie(builderClass, sb2.toString(), new Class[0]);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.RepeatedFieldAccessor.MethodInvoker
                public Object get(final GeneratedMessageV3 message) {
                    return GeneratedMessageV3.invokeOrDie(this.getMethod, message, new Object[0]);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.RepeatedFieldAccessor.MethodInvoker
                public Object get(Builder<?> builder) {
                    return GeneratedMessageV3.invokeOrDie(this.getMethodBuilder, builder, new Object[0]);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.RepeatedFieldAccessor.MethodInvoker
                public Object getRepeated(final GeneratedMessageV3 message, final int index) {
                    return GeneratedMessageV3.invokeOrDie(this.getRepeatedMethod, message, Integer.valueOf(index));
                }

                @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.RepeatedFieldAccessor.MethodInvoker
                public Object getRepeated(Builder<?> builder, int index) {
                    return GeneratedMessageV3.invokeOrDie(this.getRepeatedMethodBuilder, builder, Integer.valueOf(index));
                }

                @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.RepeatedFieldAccessor.MethodInvoker
                public void addRepeated(final Builder<?> builder, final Object value) {
                    GeneratedMessageV3.invokeOrDie(this.addRepeatedMethod, builder, value);
                }

                @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.RepeatedFieldAccessor.MethodInvoker
                public int getRepeatedCount(final GeneratedMessageV3 message) {
                    return ((Integer) GeneratedMessageV3.invokeOrDie(this.getCountMethod, message, new Object[0])).intValue();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.RepeatedFieldAccessor.MethodInvoker
                public int getRepeatedCount(Builder<?> builder) {
                    return ((Integer) GeneratedMessageV3.invokeOrDie(this.getCountMethodBuilder, builder, new Object[0])).intValue();
                }

                @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.RepeatedFieldAccessor.MethodInvoker
                public void clear(final Builder<?> builder) {
                    GeneratedMessageV3.invokeOrDie(this.clearMethod, builder, new Object[0]);
                }
            }

            RepeatedFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class<? extends GeneratedMessageV3> messageClass, final Class<? extends Builder<?>> builderClass) {
                ReflectionInvoker reflectionInvoker = new ReflectionInvoker(descriptor, camelCaseName, messageClass, builderClass);
                this.type = reflectionInvoker.getRepeatedMethod.getReturnType();
                this.invoker = getMethodInvoker(reflectionInvoker);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Object get(final GeneratedMessageV3 message) {
                return this.invoker.get(message);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Object get(Builder<?> builder) {
                return this.invoker.get(builder);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Object getRaw(final GeneratedMessageV3 message) {
                return get(message);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public void set(final Builder<?> builder, final Object value) {
                clear(builder);
                Iterator it = ((List) value).iterator();
                while (it.hasNext()) {
                    addRepeated(builder, it.next());
                }
            }

            public Object getRepeated(final GeneratedMessageV3 message, final int index) {
                return this.invoker.getRepeated(message, index);
            }

            public Object getRepeated(Builder<?> builder, int index) {
                return this.invoker.getRepeated(builder, index);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public void addRepeated(final Builder<?> builder, final Object value) {
                this.invoker.addRepeated(builder, value);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public boolean has(final GeneratedMessageV3 message) {
                throw new UnsupportedOperationException("hasField() called on a repeated field.");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public boolean has(Builder<?> builder) {
                throw new UnsupportedOperationException("hasField() called on a repeated field.");
            }

            public int getRepeatedCount(final GeneratedMessageV3 message) {
                return this.invoker.getRepeatedCount(message);
            }

            public int getRepeatedCount(Builder<?> builder) {
                return this.invoker.getRepeatedCount(builder);
            }

            public void clear(final Builder<?> builder) {
                this.invoker.clear(builder);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Message.Builder newBuilder() {
                throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Message.Builder getBuilder(Builder<?> builder) {
                throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
            }
        }

        private static class MapFieldAccessor implements FieldAccessor {
            private final Descriptors.FieldDescriptor field;
            private final Message mapEntryMessageDefaultInstance;

            MapFieldAccessor(final Descriptors.FieldDescriptor descriptor, final Class<? extends GeneratedMessageV3> messageClass) {
                this.field = descriptor;
                this.mapEntryMessageDefaultInstance = getMapField((GeneratedMessageV3) GeneratedMessageV3.invokeOrDie(GeneratedMessageV3.getMethodOrDie(messageClass, "getDefaultInstance", new Class[0]), null, new Object[0])).getMapEntryMessageDefaultInstance();
            }

            private MapField<?, ?> getMapField(GeneratedMessageV3 message) {
                return message.internalGetMapField(this.field.getNumber());
            }

            private MapField<?, ?> getMapField(Builder<?> builder) {
                return builder.internalGetMapField(this.field.getNumber());
            }

            private MapField<?, ?> getMutableMapField(Builder<?> builder) {
                return builder.internalGetMutableMapField(this.field.getNumber());
            }

            private Message coerceType(Message value) {
                if (value == null) {
                    return null;
                }
                return this.mapEntryMessageDefaultInstance.getClass().isInstance(value) ? value : this.mapEntryMessageDefaultInstance.toBuilder().mergeFrom(value).build();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Object get(GeneratedMessageV3 message) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < getRepeatedCount(message); i++) {
                    arrayList.add(getRepeated(message, i));
                }
                return Collections.unmodifiableList(arrayList);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Object get(Builder<?> builder) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < getRepeatedCount(builder); i++) {
                    arrayList.add(getRepeated(builder, i));
                }
                return Collections.unmodifiableList(arrayList);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Object getRaw(GeneratedMessageV3 message) {
                return get(message);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public void set(Builder<?> builder, Object value) {
                clear(builder);
                Iterator it = ((List) value).iterator();
                while (it.hasNext()) {
                    addRepeated(builder, it.next());
                }
            }

            public Object getRepeated(GeneratedMessageV3 message, int index) {
                return getMapField(message).getList().get(index);
            }

            public Object getRepeated(Builder<?> builder, int index) {
                return getMapField(builder).getList().get(index);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public void addRepeated(Builder<?> builder, Object value) {
                getMutableMapField(builder).getMutableList().add(coerceType((Message) value));
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public boolean has(GeneratedMessageV3 message) {
                throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public boolean has(Builder<?> builder) {
                throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
            }

            public int getRepeatedCount(GeneratedMessageV3 message) {
                return getMapField(message).getList().size();
            }

            public int getRepeatedCount(Builder<?> builder) {
                return getMapField(builder).getList().size();
            }

            public void clear(Builder<?> builder) {
                getMutableMapField(builder).getMutableList().clear();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Message.Builder newBuilder() {
                return this.mapEntryMessageDefaultInstance.newBuilderForType();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Message.Builder getBuilder(Builder<?> builder) {
                throw new UnsupportedOperationException("Nested builder not supported for map fields.");
            }
        }

        private static final class SingularEnumFieldAccessor extends SingularFieldAccessor {
            private final Descriptors.EnumDescriptor enumDescriptor;
            private final Method getValueDescriptorMethod;
            private Method getValueMethod;
            private Method getValueMethodBuilder;
            private Method setValueMethod;
            private final boolean supportUnknownEnumValue;
            private final Method valueOfMethod;

            SingularEnumFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class<? extends GeneratedMessageV3> messageClass, final Class<? extends Builder<?>> builderClass, final String containingOneofCamelCaseName) {
                super(descriptor, camelCaseName, messageClass, builderClass, containingOneofCamelCaseName);
                this.enumDescriptor = descriptor.getEnumType();
                this.valueOfMethod = GeneratedMessageV3.getMethodOrDie(this.type, "valueOf", Descriptors.EnumValueDescriptor.class);
                this.getValueDescriptorMethod = GeneratedMessageV3.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
                boolean zSupportsUnknownEnumValue = descriptor.getFile().supportsUnknownEnumValue();
                this.supportUnknownEnumValue = zSupportsUnknownEnumValue;
                if (zSupportsUnknownEnumValue) {
                    this.getValueMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName + "Value", new Class[0]);
                    this.getValueMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "Value", new Class[0]);
                    this.setValueMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "set" + camelCaseName + "Value", Integer.TYPE);
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.SingularFieldAccessor, com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Object get(final GeneratedMessageV3 message) {
                if (this.supportUnknownEnumValue) {
                    return this.enumDescriptor.findValueByNumberCreatingIfUnknown(((Integer) GeneratedMessageV3.invokeOrDie(this.getValueMethod, message, new Object[0])).intValue());
                }
                return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.get(message), new Object[0]);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.SingularFieldAccessor, com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Object get(final Builder<?> builder) {
                if (this.supportUnknownEnumValue) {
                    return this.enumDescriptor.findValueByNumberCreatingIfUnknown(((Integer) GeneratedMessageV3.invokeOrDie(this.getValueMethodBuilder, builder, new Object[0])).intValue());
                }
                return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.get(builder), new Object[0]);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.SingularFieldAccessor, com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public void set(final Builder<?> builder, final Object value) {
                if (this.supportUnknownEnumValue) {
                    GeneratedMessageV3.invokeOrDie(this.setValueMethod, builder, Integer.valueOf(((Descriptors.EnumValueDescriptor) value).getNumber()));
                } else {
                    super.set(builder, GeneratedMessageV3.invokeOrDie(this.valueOfMethod, null, value));
                }
            }
        }

        private static final class RepeatedEnumFieldAccessor extends RepeatedFieldAccessor {
            private Method addRepeatedValueMethod;
            private final Descriptors.EnumDescriptor enumDescriptor;
            private Method getRepeatedValueMethod;
            private Method getRepeatedValueMethodBuilder;
            private final Method getValueDescriptorMethod;
            private Method setRepeatedValueMethod;
            private final boolean supportUnknownEnumValue;
            private final Method valueOfMethod;

            RepeatedEnumFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class<? extends GeneratedMessageV3> messageClass, final Class<? extends Builder<?>> builderClass) {
                super(descriptor, camelCaseName, messageClass, builderClass);
                this.enumDescriptor = descriptor.getEnumType();
                this.valueOfMethod = GeneratedMessageV3.getMethodOrDie(this.type, "valueOf", Descriptors.EnumValueDescriptor.class);
                this.getValueDescriptorMethod = GeneratedMessageV3.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
                boolean zSupportsUnknownEnumValue = descriptor.getFile().supportsUnknownEnumValue();
                this.supportUnknownEnumValue = zSupportsUnknownEnumValue;
                if (zSupportsUnknownEnumValue) {
                    Class cls = Integer.TYPE;
                    this.getRepeatedValueMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName + "Value", cls);
                    this.getRepeatedValueMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "Value", cls);
                    this.setRepeatedValueMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "set" + camelCaseName + "Value", cls, cls);
                    this.addRepeatedValueMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "add" + camelCaseName + "Value", cls);
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.RepeatedFieldAccessor, com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Object get(final GeneratedMessageV3 message) {
                ArrayList arrayList = new ArrayList();
                int repeatedCount = getRepeatedCount(message);
                for (int i = 0; i < repeatedCount; i++) {
                    arrayList.add(getRepeated(message, i));
                }
                return Collections.unmodifiableList(arrayList);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.RepeatedFieldAccessor, com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Object get(final Builder<?> builder) {
                ArrayList arrayList = new ArrayList();
                int repeatedCount = getRepeatedCount(builder);
                for (int i = 0; i < repeatedCount; i++) {
                    arrayList.add(getRepeated(builder, i));
                }
                return Collections.unmodifiableList(arrayList);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.RepeatedFieldAccessor
            public Object getRepeated(final GeneratedMessageV3 message, final int index) {
                if (!this.supportUnknownEnumValue) {
                    return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(message, index), new Object[0]);
                }
                return this.enumDescriptor.findValueByNumberCreatingIfUnknown(((Integer) GeneratedMessageV3.invokeOrDie(this.getRepeatedValueMethod, message, Integer.valueOf(index))).intValue());
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.RepeatedFieldAccessor
            public Object getRepeated(final Builder<?> builder, final int index) {
                if (!this.supportUnknownEnumValue) {
                    return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(builder, index), new Object[0]);
                }
                return this.enumDescriptor.findValueByNumberCreatingIfUnknown(((Integer) GeneratedMessageV3.invokeOrDie(this.getRepeatedValueMethodBuilder, builder, Integer.valueOf(index))).intValue());
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.RepeatedFieldAccessor, com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public void addRepeated(final Builder<?> builder, final Object value) {
                if (this.supportUnknownEnumValue) {
                    GeneratedMessageV3.invokeOrDie(this.addRepeatedValueMethod, builder, Integer.valueOf(((Descriptors.EnumValueDescriptor) value).getNumber()));
                } else {
                    super.addRepeated(builder, GeneratedMessageV3.invokeOrDie(this.valueOfMethod, null, value));
                }
            }
        }

        private static final class SingularStringFieldAccessor extends SingularFieldAccessor {
            private final Method getBytesMethod;
            private final Method setBytesMethodBuilder;

            SingularStringFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class<? extends GeneratedMessageV3> messageClass, final Class<? extends Builder<?>> builderClass, final String containingOneofCamelCaseName) {
                super(descriptor, camelCaseName, messageClass, builderClass, containingOneofCamelCaseName);
                this.getBytesMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName + "Bytes", new Class[0]);
                this.setBytesMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "set" + camelCaseName + "Bytes", ByteString.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.SingularFieldAccessor, com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Object getRaw(final GeneratedMessageV3 message) {
                return GeneratedMessageV3.invokeOrDie(this.getBytesMethod, message, new Object[0]);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.SingularFieldAccessor, com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public void set(Builder<?> builder, Object value) {
                if (value instanceof ByteString) {
                    GeneratedMessageV3.invokeOrDie(this.setBytesMethodBuilder, builder, value);
                } else {
                    super.set(builder, value);
                }
            }
        }

        private static final class SingularMessageFieldAccessor extends SingularFieldAccessor {
            private final Method getBuilderMethodBuilder;
            private final Method newBuilderMethod;

            SingularMessageFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class<? extends GeneratedMessageV3> messageClass, final Class<? extends Builder<?>> builderClass, final String containingOneofCamelCaseName) {
                super(descriptor, camelCaseName, messageClass, builderClass, containingOneofCamelCaseName);
                this.newBuilderMethod = GeneratedMessageV3.getMethodOrDie(this.type, "newBuilder", new Class[0]);
                this.getBuilderMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "Builder", new Class[0]);
            }

            private Object coerceType(final Object value) {
                return this.type.isInstance(value) ? value : ((Message.Builder) GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message) value).buildPartial();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.SingularFieldAccessor, com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public void set(final Builder<?> builder, final Object value) {
                super.set(builder, coerceType(value));
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.SingularFieldAccessor, com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Message.Builder newBuilder() {
                return (Message.Builder) GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.SingularFieldAccessor, com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Message.Builder getBuilder(Builder<?> builder) {
                return (Message.Builder) GeneratedMessageV3.invokeOrDie(this.getBuilderMethodBuilder, builder, new Object[0]);
            }
        }

        private static final class RepeatedMessageFieldAccessor extends RepeatedFieldAccessor {
            private final Method getBuilderMethodBuilder;
            private final Method newBuilderMethod;

            RepeatedMessageFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class<? extends GeneratedMessageV3> messageClass, final Class<? extends Builder<?>> builderClass) {
                super(descriptor, camelCaseName, messageClass, builderClass);
                this.newBuilderMethod = GeneratedMessageV3.getMethodOrDie(this.type, "newBuilder", new Class[0]);
                this.getBuilderMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "Builder", Integer.TYPE);
            }

            private Object coerceType(final Object value) {
                return this.type.isInstance(value) ? value : ((Message.Builder) GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message) value).build();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.RepeatedFieldAccessor, com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public void addRepeated(final Builder<?> builder, final Object value) {
                super.addRepeated(builder, coerceType(value));
            }

            @Override // com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.RepeatedFieldAccessor, com.google.protobuf.GeneratedMessageV3.FieldAccessorTable.FieldAccessor
            public Message.Builder newBuilder() {
                return (Message.Builder) GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
            }
        }
    }

    protected static int computeStringSize(final int fieldNumber, final Object value) {
        if (value instanceof String) {
            return CodedOutputStream.computeStringSize(fieldNumber, (String) value);
        }
        return CodedOutputStream.computeBytesSize(fieldNumber, (ByteString) value);
    }

    protected static int computeStringSizeNoTag(final Object value) {
        if (value instanceof String) {
            return CodedOutputStream.computeStringSizeNoTag((String) value);
        }
        return CodedOutputStream.computeBytesSizeNoTag((ByteString) value);
    }

    protected static void writeString(CodedOutputStream output, final int fieldNumber, final Object value) throws IOException {
        if (value instanceof String) {
            output.writeString(fieldNumber, (String) value);
        } else {
            output.writeBytes(fieldNumber, (ByteString) value);
        }
    }
}
