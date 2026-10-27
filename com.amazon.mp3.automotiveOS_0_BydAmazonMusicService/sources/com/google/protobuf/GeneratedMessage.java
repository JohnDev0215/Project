package com.google.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.Message;
import com.google.protobuf.UnknownFieldSet;
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
public abstract class GeneratedMessage extends AbstractMessage implements Serializable {
    protected static boolean alwaysUseFieldBuilders = false;
    protected UnknownFieldSet unknownFields;

    /* JADX INFO: Access modifiers changed from: protected */
    public interface BuilderParent extends AbstractMessage.BuilderParent {
    }

    protected abstract FieldAccessorTable internalGetFieldAccessorTable();

    protected void makeExtensionsImmutable() {
    }

    protected abstract Message.Builder newBuilderForType(BuilderParent parent);

    protected GeneratedMessage() {
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }

    protected GeneratedMessage(Builder<?> builder) {
        this.unknownFields = builder.getUnknownFields();
    }

    @Override // com.google.protobuf.MessageLite
    public Parser<? extends GeneratedMessage> getParserForType() {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public Descriptors.Descriptor getDescriptorForType() {
        return internalGetFieldAccessorTable().descriptor;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0066  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.Map<com.google.protobuf.Descriptors.FieldDescriptor, java.lang.Object> getAllFieldsMutable(boolean r7) {
        /*
            r6 = this;
            java.util.TreeMap r0 = new java.util.TreeMap
            r0.<init>()
            com.google.protobuf.GeneratedMessage$FieldAccessorTable r1 = r6.internalGetFieldAccessorTable()
            com.google.protobuf.Descriptors$Descriptor r1 = com.google.protobuf.GeneratedMessage.FieldAccessorTable.access$000(r1)
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.GeneratedMessage.getAllFieldsMutable(boolean):java.util.Map");
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
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    protected boolean parseUnknownField(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
        return unknownFields.mergeFieldFrom(tag, input);
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

    @Override // com.google.protobuf.AbstractMessage
    protected Message.Builder newBuilderForType(final AbstractMessage.BuilderParent parent) {
        return newBuilderForType(new BuilderParent() { // from class: com.google.protobuf.GeneratedMessage.1
            @Override // com.google.protobuf.AbstractMessage.BuilderParent
            public void markDirty() {
                parent.markDirty();
            }
        });
    }

    public static abstract class Builder<BuilderType extends Builder<BuilderType>> extends AbstractMessage.Builder<BuilderType> {
        private BuilderParent builderParent;
        private boolean isClean;
        private UnknownFieldSet unknownFields;

        protected abstract FieldAccessorTable internalGetFieldAccessorTable();

        protected Builder() {
            this(null);
        }

        protected Builder(BuilderParent builderParent) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
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

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
        /* JADX INFO: renamed from: clone */
        public BuilderType mo76clone() {
            BuilderType buildertype = (BuilderType) getDefaultInstanceForType().newBuilderForType();
            buildertype.mergeFrom(buildPartial());
            return buildertype;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return internalGetFieldAccessorTable().descriptor;
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
            return Collections.unmodifiableMap(getAllFieldsMutable());
        }

        private Map<Descriptors.FieldDescriptor, Object> getAllFieldsMutable() {
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
            Object obj = internalGetFieldAccessorTable().getField(field).get(this);
            return field.isRepeated() ? Collections.unmodifiableList((List) obj) : obj;
        }

        @Override // com.google.protobuf.Message.Builder
        public BuilderType setField(final Descriptors.FieldDescriptor field, final Object value) {
            internalGetFieldAccessorTable().getField(field).set(this, value);
            return this;
        }

        @Override // com.google.protobuf.Message.Builder
        public BuilderType addRepeatedField(final Descriptors.FieldDescriptor field, final Object value) {
            internalGetFieldAccessorTable().getField(field).addRepeated(this, value);
            return this;
        }

        @Override // com.google.protobuf.Message.Builder
        public BuilderType setUnknownFields(final UnknownFieldSet unknownFields) {
            this.unknownFields = unknownFields;
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.AbstractMessage.Builder
        public BuilderType mergeUnknownFields(final UnknownFieldSet unknownFields) {
            this.unknownFields = UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(unknownFields).build();
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
            return this.unknownFields;
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
            throw new RuntimeException("No map fields found in " + getClass().getName());
        }

        protected MapField internalGetMutableMapField(int fieldNumber) {
            throw new RuntimeException("No map fields found in " + getClass().getName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Method getMethodOrDie(final Class clazz, final String name, final Class... params) {
        try {
            return clazz.getMethod(name, params);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Generated message class \"" + clazz.getName() + "\" missing method \"" + name + "\".", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object invokeOrDie(final Method method, final Object object, final Object... params) {
        try {
            return method.invoke(object, params);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    protected MapField internalGetMapField(int fieldNumber) {
        throw new RuntimeException("No map fields found in " + getClass().getName());
    }

    public static final class FieldAccessorTable {
        private String[] camelCaseNames;
        private final Descriptors.Descriptor descriptor;
        private final FieldAccessor[] fields;
        private volatile boolean initialized = false;
        private final OneofAccessor[] oneofs;

        private interface FieldAccessor {
            void addRepeated(Builder builder, Object value);

            Object get(Builder builder);

            Object get(GeneratedMessage message);

            Message.Builder getBuilder(Builder builder);

            Object getRaw(GeneratedMessage message);

            boolean has(Builder builder);

            boolean has(GeneratedMessage message);

            Message.Builder newBuilder();

            void set(Builder builder, Object value);
        }

        private boolean isMapFieldEnabled(Descriptors.FieldDescriptor field) {
            return true;
        }

        public FieldAccessorTable(final Descriptors.Descriptor descriptor, final String[] camelCaseNames) {
            this.descriptor = descriptor;
            this.camelCaseNames = camelCaseNames;
            this.fields = new FieldAccessor[descriptor.getFields().size()];
            this.oneofs = new OneofAccessor[descriptor.getOneofs().size()];
        }

        public FieldAccessorTable ensureFieldAccessorsInitialized(Class<? extends GeneratedMessage> messageClass, Class<? extends Builder> builderClass) {
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
                            if (fieldDescriptor.isMapField() && isMapFieldEnabled(fieldDescriptor)) {
                                this.fields[i] = new MapFieldAccessor(fieldDescriptor, this.camelCaseNames[i], messageClass, builderClass);
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
                    this.oneofs[i2] = new OneofAccessor(this.descriptor, this.camelCaseNames[i2 + length], messageClass, builderClass);
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

            OneofAccessor(final Descriptors.Descriptor descriptor, final String camelCaseName, final Class<? extends GeneratedMessage> messageClass, final Class<? extends Builder> builderClass) {
                this.descriptor = descriptor;
                this.caseMethod = GeneratedMessage.getMethodOrDie(messageClass, "get" + camelCaseName + "Case", new Class[0]);
                this.caseMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName + "Case", new Class[0]);
                StringBuilder sb = new StringBuilder();
                sb.append("clear");
                sb.append(camelCaseName);
                this.clearMethod = GeneratedMessage.getMethodOrDie(builderClass, sb.toString(), new Class[0]);
            }

            public boolean has(final GeneratedMessage message) {
                return ((Internal.EnumLite) GeneratedMessage.invokeOrDie(this.caseMethod, message, new Object[0])).getNumber() != 0;
            }

            public boolean has(Builder builder) {
                return ((Internal.EnumLite) GeneratedMessage.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber() != 0;
            }

            public Descriptors.FieldDescriptor get(final GeneratedMessage message) {
                int number = ((Internal.EnumLite) GeneratedMessage.invokeOrDie(this.caseMethod, message, new Object[0])).getNumber();
                if (number > 0) {
                    return this.descriptor.findFieldByNumber(number);
                }
                return null;
            }

            public Descriptors.FieldDescriptor get(Builder builder) {
                int number = ((Internal.EnumLite) GeneratedMessage.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber();
                if (number > 0) {
                    return this.descriptor.findFieldByNumber(number);
                }
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean supportFieldPresence(Descriptors.FileDescriptor file) {
            return file.getSyntax() == Descriptors.FileDescriptor.Syntax.PROTO2;
        }

        private static class SingularFieldAccessor implements FieldAccessor {
            protected final Method caseMethod;
            protected final Method caseMethodBuilder;
            protected final Method clearMethod;
            protected final Descriptors.FieldDescriptor field;
            protected final Method getMethod;
            protected final Method getMethodBuilder;
            protected final boolean hasHasMethod;
            protected final Method hasMethod;
            protected final Method hasMethodBuilder;
            protected final boolean isOneofField;
            protected final Method setMethod;
            protected final Class<?> type;

            SingularFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class<? extends GeneratedMessage> messageClass, final Class<? extends Builder> builderClass, final String containingOneofCamelCaseName) {
                Method methodOrDie;
                Method methodOrDie2;
                Method methodOrDie3;
                this.field = descriptor;
                boolean z = descriptor.getContainingOneof() != null;
                this.isOneofField = z;
                boolean z2 = FieldAccessorTable.supportFieldPresence(descriptor.getFile()) || (!z && descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE);
                this.hasHasMethod = z2;
                Method methodOrDie4 = GeneratedMessage.getMethodOrDie(messageClass, "get" + camelCaseName, new Class[0]);
                this.getMethod = methodOrDie4;
                this.getMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName, new Class[0]);
                Class<?> returnType = methodOrDie4.getReturnType();
                this.type = returnType;
                this.setMethod = GeneratedMessage.getMethodOrDie(builderClass, "set" + camelCaseName, returnType);
                Method methodOrDie5 = null;
                if (z2) {
                    methodOrDie = GeneratedMessage.getMethodOrDie(messageClass, "has" + camelCaseName, new Class[0]);
                } else {
                    methodOrDie = null;
                }
                this.hasMethod = methodOrDie;
                if (z2) {
                    methodOrDie2 = GeneratedMessage.getMethodOrDie(builderClass, "has" + camelCaseName, new Class[0]);
                } else {
                    methodOrDie2 = null;
                }
                this.hasMethodBuilder = methodOrDie2;
                this.clearMethod = GeneratedMessage.getMethodOrDie(builderClass, "clear" + camelCaseName, new Class[0]);
                if (z) {
                    methodOrDie3 = GeneratedMessage.getMethodOrDie(messageClass, "get" + containingOneofCamelCaseName + "Case", new Class[0]);
                } else {
                    methodOrDie3 = null;
                }
                this.caseMethod = methodOrDie3;
                if (z) {
                    methodOrDie5 = GeneratedMessage.getMethodOrDie(builderClass, "get" + containingOneofCamelCaseName + "Case", new Class[0]);
                }
                this.caseMethodBuilder = methodOrDie5;
            }

            private int getOneofFieldNumber(final GeneratedMessage message) {
                return ((Internal.EnumLite) GeneratedMessage.invokeOrDie(this.caseMethod, message, new Object[0])).getNumber();
            }

            private int getOneofFieldNumber(final Builder builder) {
                return ((Internal.EnumLite) GeneratedMessage.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber();
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(final GeneratedMessage message) {
                return GeneratedMessage.invokeOrDie(this.getMethod, message, new Object[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(Builder builder) {
                return GeneratedMessage.invokeOrDie(this.getMethodBuilder, builder, new Object[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object getRaw(final GeneratedMessage message) {
                return get(message);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void set(final Builder builder, final Object value) {
                GeneratedMessage.invokeOrDie(this.setMethod, builder, value);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void addRepeated(final Builder builder, final Object value) {
                throw new UnsupportedOperationException("addRepeatedField() called on a singular field.");
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public boolean has(final GeneratedMessage message) {
                if (this.hasHasMethod) {
                    return ((Boolean) GeneratedMessage.invokeOrDie(this.hasMethod, message, new Object[0])).booleanValue();
                }
                if (this.isOneofField) {
                    return getOneofFieldNumber(message) == this.field.getNumber();
                }
                return !get(message).equals(this.field.getDefaultValue());
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public boolean has(Builder builder) {
                if (this.hasHasMethod) {
                    return ((Boolean) GeneratedMessage.invokeOrDie(this.hasMethodBuilder, builder, new Object[0])).booleanValue();
                }
                if (this.isOneofField) {
                    return getOneofFieldNumber(builder) == this.field.getNumber();
                }
                return !get(builder).equals(this.field.getDefaultValue());
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Message.Builder newBuilder() {
                throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Message.Builder getBuilder(Builder builder) {
                throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
            }
        }

        private static class RepeatedFieldAccessor implements FieldAccessor {
            protected final Method addRepeatedMethod;
            protected final Method clearMethod;
            protected final Method getCountMethod;
            protected final Method getCountMethodBuilder;
            protected final Method getMethod;
            protected final Method getMethodBuilder;
            protected final Method getRepeatedMethod;
            protected final Method getRepeatedMethodBuilder;
            protected final Method setRepeatedMethod;
            protected final Class type;

            RepeatedFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class<? extends GeneratedMessage> messageClass, final Class<? extends Builder> builderClass) {
                this.getMethod = GeneratedMessage.getMethodOrDie(messageClass, "get" + camelCaseName + "List", new Class[0]);
                this.getMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName + "List", new Class[0]);
                StringBuilder sb = new StringBuilder();
                sb.append("get");
                sb.append(camelCaseName);
                String string = sb.toString();
                Class cls = Integer.TYPE;
                Method methodOrDie = GeneratedMessage.getMethodOrDie(messageClass, string, cls);
                this.getRepeatedMethod = methodOrDie;
                this.getRepeatedMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName, cls);
                Class<?> returnType = methodOrDie.getReturnType();
                this.type = returnType;
                this.setRepeatedMethod = GeneratedMessage.getMethodOrDie(builderClass, "set" + camelCaseName, cls, returnType);
                this.addRepeatedMethod = GeneratedMessage.getMethodOrDie(builderClass, "add" + camelCaseName, returnType);
                this.getCountMethod = GeneratedMessage.getMethodOrDie(messageClass, "get" + camelCaseName + "Count", new Class[0]);
                this.getCountMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName + "Count", new Class[0]);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("clear");
                sb2.append(camelCaseName);
                this.clearMethod = GeneratedMessage.getMethodOrDie(builderClass, sb2.toString(), new Class[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(final GeneratedMessage message) {
                return GeneratedMessage.invokeOrDie(this.getMethod, message, new Object[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(Builder builder) {
                return GeneratedMessage.invokeOrDie(this.getMethodBuilder, builder, new Object[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object getRaw(final GeneratedMessage message) {
                return get(message);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void set(final Builder builder, final Object value) {
                clear(builder);
                Iterator it = ((List) value).iterator();
                while (it.hasNext()) {
                    addRepeated(builder, it.next());
                }
            }

            public Object getRepeated(final GeneratedMessage message, final int index) {
                return GeneratedMessage.invokeOrDie(this.getRepeatedMethod, message, Integer.valueOf(index));
            }

            public Object getRepeated(Builder builder, int index) {
                return GeneratedMessage.invokeOrDie(this.getRepeatedMethodBuilder, builder, Integer.valueOf(index));
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void addRepeated(final Builder builder, final Object value) {
                GeneratedMessage.invokeOrDie(this.addRepeatedMethod, builder, value);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public boolean has(final GeneratedMessage message) {
                throw new UnsupportedOperationException("hasField() called on a repeated field.");
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public boolean has(Builder builder) {
                throw new UnsupportedOperationException("hasField() called on a repeated field.");
            }

            public int getRepeatedCount(final GeneratedMessage message) {
                return ((Integer) GeneratedMessage.invokeOrDie(this.getCountMethod, message, new Object[0])).intValue();
            }

            public int getRepeatedCount(Builder builder) {
                return ((Integer) GeneratedMessage.invokeOrDie(this.getCountMethodBuilder, builder, new Object[0])).intValue();
            }

            public void clear(final Builder builder) {
                GeneratedMessage.invokeOrDie(this.clearMethod, builder, new Object[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Message.Builder newBuilder() {
                throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Message.Builder getBuilder(Builder builder) {
                throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
            }
        }

        private static class MapFieldAccessor implements FieldAccessor {
            private final Descriptors.FieldDescriptor field;
            private final Message mapEntryMessageDefaultInstance;

            MapFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class<? extends GeneratedMessage> messageClass, final Class<? extends Builder> builderClass) {
                this.field = descriptor;
                this.mapEntryMessageDefaultInstance = getMapField((GeneratedMessage) GeneratedMessage.invokeOrDie(GeneratedMessage.getMethodOrDie(messageClass, "getDefaultInstance", new Class[0]), null, new Object[0])).getMapEntryMessageDefaultInstance();
            }

            private MapField<?, ?> getMapField(GeneratedMessage message) {
                return message.internalGetMapField(this.field.getNumber());
            }

            private MapField<?, ?> getMapField(Builder builder) {
                return builder.internalGetMapField(this.field.getNumber());
            }

            private MapField<?, ?> getMutableMapField(Builder builder) {
                return builder.internalGetMutableMapField(this.field.getNumber());
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(GeneratedMessage message) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < getRepeatedCount(message); i++) {
                    arrayList.add(getRepeated(message, i));
                }
                return Collections.unmodifiableList(arrayList);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(Builder builder) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < getRepeatedCount(builder); i++) {
                    arrayList.add(getRepeated(builder, i));
                }
                return Collections.unmodifiableList(arrayList);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object getRaw(GeneratedMessage message) {
                return get(message);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void set(Builder builder, Object value) {
                clear(builder);
                Iterator it = ((List) value).iterator();
                while (it.hasNext()) {
                    addRepeated(builder, it.next());
                }
            }

            public Object getRepeated(GeneratedMessage message, int index) {
                return getMapField(message).getList().get(index);
            }

            public Object getRepeated(Builder builder, int index) {
                return getMapField(builder).getList().get(index);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void addRepeated(Builder builder, Object value) {
                getMutableMapField(builder).getMutableList().add((Message) value);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public boolean has(GeneratedMessage message) {
                throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public boolean has(Builder builder) {
                throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
            }

            public int getRepeatedCount(GeneratedMessage message) {
                return getMapField(message).getList().size();
            }

            public int getRepeatedCount(Builder builder) {
                return getMapField(builder).getList().size();
            }

            public void clear(Builder builder) {
                getMutableMapField(builder).getMutableList().clear();
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Message.Builder newBuilder() {
                return this.mapEntryMessageDefaultInstance.newBuilderForType();
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Message.Builder getBuilder(Builder builder) {
                throw new UnsupportedOperationException("Nested builder not supported for map fields.");
            }
        }

        private static final class SingularEnumFieldAccessor extends SingularFieldAccessor {
            private Descriptors.EnumDescriptor enumDescriptor;
            private Method getValueDescriptorMethod;
            private Method getValueMethod;
            private Method getValueMethodBuilder;
            private Method setValueMethod;
            private boolean supportUnknownEnumValue;
            private Method valueOfMethod;

            SingularEnumFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class<? extends GeneratedMessage> messageClass, final Class<? extends Builder> builderClass, final String containingOneofCamelCaseName) {
                super(descriptor, camelCaseName, messageClass, builderClass, containingOneofCamelCaseName);
                this.enumDescriptor = descriptor.getEnumType();
                this.valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", Descriptors.EnumValueDescriptor.class);
                this.getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
                boolean zSupportsUnknownEnumValue = descriptor.getFile().supportsUnknownEnumValue();
                this.supportUnknownEnumValue = zSupportsUnknownEnumValue;
                if (zSupportsUnknownEnumValue) {
                    this.getValueMethod = GeneratedMessage.getMethodOrDie(messageClass, "get" + camelCaseName + "Value", new Class[0]);
                    this.getValueMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName + "Value", new Class[0]);
                    this.setValueMethod = GeneratedMessage.getMethodOrDie(builderClass, "set" + camelCaseName + "Value", Integer.TYPE);
                }
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.SingularFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(final GeneratedMessage message) {
                if (this.supportUnknownEnumValue) {
                    return this.enumDescriptor.findValueByNumberCreatingIfUnknown(((Integer) GeneratedMessage.invokeOrDie(this.getValueMethod, message, new Object[0])).intValue());
                }
                return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(message), new Object[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.SingularFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(final Builder builder) {
                if (this.supportUnknownEnumValue) {
                    return this.enumDescriptor.findValueByNumberCreatingIfUnknown(((Integer) GeneratedMessage.invokeOrDie(this.getValueMethodBuilder, builder, new Object[0])).intValue());
                }
                return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(builder), new Object[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.SingularFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void set(final Builder builder, final Object value) {
                if (this.supportUnknownEnumValue) {
                    GeneratedMessage.invokeOrDie(this.setValueMethod, builder, Integer.valueOf(((Descriptors.EnumValueDescriptor) value).getNumber()));
                } else {
                    super.set(builder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, value));
                }
            }
        }

        private static final class RepeatedEnumFieldAccessor extends RepeatedFieldAccessor {
            private Method addRepeatedValueMethod;
            private Descriptors.EnumDescriptor enumDescriptor;
            private Method getRepeatedValueMethod;
            private Method getRepeatedValueMethodBuilder;
            private final Method getValueDescriptorMethod;
            private Method setRepeatedValueMethod;
            private boolean supportUnknownEnumValue;
            private final Method valueOfMethod;

            RepeatedEnumFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class<? extends GeneratedMessage> messageClass, final Class<? extends Builder> builderClass) {
                super(descriptor, camelCaseName, messageClass, builderClass);
                this.enumDescriptor = descriptor.getEnumType();
                this.valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", Descriptors.EnumValueDescriptor.class);
                this.getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
                boolean zSupportsUnknownEnumValue = descriptor.getFile().supportsUnknownEnumValue();
                this.supportUnknownEnumValue = zSupportsUnknownEnumValue;
                if (zSupportsUnknownEnumValue) {
                    Class cls = Integer.TYPE;
                    this.getRepeatedValueMethod = GeneratedMessage.getMethodOrDie(messageClass, "get" + camelCaseName + "Value", cls);
                    this.getRepeatedValueMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName + "Value", cls);
                    this.setRepeatedValueMethod = GeneratedMessage.getMethodOrDie(builderClass, "set" + camelCaseName + "Value", cls, cls);
                    this.addRepeatedValueMethod = GeneratedMessage.getMethodOrDie(builderClass, "add" + camelCaseName + "Value", cls);
                }
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(final GeneratedMessage message) {
                ArrayList arrayList = new ArrayList();
                int repeatedCount = getRepeatedCount(message);
                for (int i = 0; i < repeatedCount; i++) {
                    arrayList.add(getRepeated(message, i));
                }
                return Collections.unmodifiableList(arrayList);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(final Builder builder) {
                ArrayList arrayList = new ArrayList();
                int repeatedCount = getRepeatedCount(builder);
                for (int i = 0; i < repeatedCount; i++) {
                    arrayList.add(getRepeated(builder, i));
                }
                return Collections.unmodifiableList(arrayList);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor
            public Object getRepeated(final GeneratedMessage message, final int index) {
                if (!this.supportUnknownEnumValue) {
                    return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(message, index), new Object[0]);
                }
                return this.enumDescriptor.findValueByNumberCreatingIfUnknown(((Integer) GeneratedMessage.invokeOrDie(this.getRepeatedValueMethod, message, Integer.valueOf(index))).intValue());
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor
            public Object getRepeated(final Builder builder, final int index) {
                if (!this.supportUnknownEnumValue) {
                    return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(builder, index), new Object[0]);
                }
                return this.enumDescriptor.findValueByNumberCreatingIfUnknown(((Integer) GeneratedMessage.invokeOrDie(this.getRepeatedValueMethodBuilder, builder, Integer.valueOf(index))).intValue());
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void addRepeated(final Builder builder, final Object value) {
                if (this.supportUnknownEnumValue) {
                    GeneratedMessage.invokeOrDie(this.addRepeatedValueMethod, builder, Integer.valueOf(((Descriptors.EnumValueDescriptor) value).getNumber()));
                } else {
                    super.addRepeated(builder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, value));
                }
            }
        }

        private static final class SingularStringFieldAccessor extends SingularFieldAccessor {
            private final Method getBytesMethod;
            private final Method getBytesMethodBuilder;
            private final Method setBytesMethodBuilder;

            SingularStringFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class<? extends GeneratedMessage> messageClass, final Class<? extends Builder> builderClass, final String containingOneofCamelCaseName) {
                super(descriptor, camelCaseName, messageClass, builderClass, containingOneofCamelCaseName);
                this.getBytesMethod = GeneratedMessage.getMethodOrDie(messageClass, "get" + camelCaseName + "Bytes", new Class[0]);
                this.getBytesMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName + "Bytes", new Class[0]);
                this.setBytesMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "set" + camelCaseName + "Bytes", ByteString.class);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.SingularFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object getRaw(final GeneratedMessage message) {
                return GeneratedMessage.invokeOrDie(this.getBytesMethod, message, new Object[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.SingularFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void set(Builder builder, Object value) {
                if (value instanceof ByteString) {
                    GeneratedMessage.invokeOrDie(this.setBytesMethodBuilder, builder, value);
                } else {
                    super.set(builder, value);
                }
            }
        }

        private static final class SingularMessageFieldAccessor extends SingularFieldAccessor {
            private final Method getBuilderMethodBuilder;
            private final Method newBuilderMethod;

            SingularMessageFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class<? extends GeneratedMessage> messageClass, final Class<? extends Builder> builderClass, final String containingOneofCamelCaseName) {
                super(descriptor, camelCaseName, messageClass, builderClass, containingOneofCamelCaseName);
                this.newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);
                this.getBuilderMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName + "Builder", new Class[0]);
            }

            private Object coerceType(final Object value) {
                return this.type.isInstance(value) ? value : ((Message.Builder) GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message) value).buildPartial();
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.SingularFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void set(final Builder builder, final Object value) {
                super.set(builder, coerceType(value));
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.SingularFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Message.Builder newBuilder() {
                return (Message.Builder) GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.SingularFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Message.Builder getBuilder(Builder builder) {
                return (Message.Builder) GeneratedMessage.invokeOrDie(this.getBuilderMethodBuilder, builder, new Object[0]);
            }
        }

        private static final class RepeatedMessageFieldAccessor extends RepeatedFieldAccessor {
            private final Method getBuilderMethodBuilder;
            private final Method newBuilderMethod;

            RepeatedMessageFieldAccessor(final Descriptors.FieldDescriptor descriptor, final String camelCaseName, final Class<? extends GeneratedMessage> messageClass, final Class<? extends Builder> builderClass) {
                super(descriptor, camelCaseName, messageClass, builderClass);
                this.newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);
                this.getBuilderMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName + "Builder", Integer.TYPE);
            }

            private Object coerceType(final Object value) {
                return this.type.isInstance(value) ? value : ((Message.Builder) GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message) value).build();
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void addRepeated(final Builder builder, final Object value) {
                super.addRepeated(builder, coerceType(value));
            }

            @Override // com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor, com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Message.Builder newBuilder() {
                return (Message.Builder) GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
            }
        }
    }
}
