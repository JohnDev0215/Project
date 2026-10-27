package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.StructuralMessageInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
final class DescriptorMessageInfoFactory implements MessageInfoFactory {
    private static final String GET_DEFAULT_INSTANCE_METHOD_NAME = "getDefaultInstance";
    private static final DescriptorMessageInfoFactory instance = new DescriptorMessageInfoFactory();
    private static final Set<String> specialFieldNames = new HashSet(Arrays.asList("Class", "DefaultInstanceForType", "ParserForType", "SerializedSize", "AllFields", "DescriptorForType", "InitializationErrorString", "UnknownFields", "CachedSize"));
    private static IsInitializedCheckAnalyzer isInitializedCheckAnalyzer = new IsInitializedCheckAnalyzer();

    private DescriptorMessageInfoFactory() {
    }

    public static DescriptorMessageInfoFactory getInstance() {
        return instance;
    }

    @Override // com.google.protobuf.MessageInfoFactory
    public boolean isSupported(Class<?> messageType) {
        return GeneratedMessageV3.class.isAssignableFrom(messageType);
    }

    @Override // com.google.protobuf.MessageInfoFactory
    public MessageInfo messageInfoFor(Class<?> messageType) {
        if (!GeneratedMessageV3.class.isAssignableFrom(messageType)) {
            throw new IllegalArgumentException("Unsupported message type: " + messageType.getName());
        }
        return convert(messageType, descriptorForType(messageType));
    }

    private static Message getDefaultInstance(Class<?> messageType) {
        try {
            java.lang.reflect.Method method = messageType.getDeclaredMethod(GET_DEFAULT_INSTANCE_METHOD_NAME, new Class[0]);
            return (Message) method.invoke(null, new Object[0]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to get default instance for message class " + messageType.getName(), e);
        }
    }

    private static Descriptors.Descriptor descriptorForType(Class<?> messageType) {
        return getDefaultInstance(messageType).getDescriptorForType();
    }

    private static MessageInfo convert(Class<?> messageType, Descriptors.Descriptor messageDescriptor) {
        int i = AnonymousClass3.$SwitchMap$com$google$protobuf$Descriptors$FileDescriptor$Syntax[messageDescriptor.getFile().getSyntax().ordinal()];
        if (i == 1) {
            return convertProto2(messageType, messageDescriptor);
        }
        if (i == 2) {
            return convertProto3(messageType, messageDescriptor);
        }
        throw new IllegalArgumentException("Unsupported syntax: " + messageDescriptor.getFile().getSyntax());
    }

    static class IsInitializedCheckAnalyzer {
        private final Map<Descriptors.Descriptor, Boolean> resultCache = new ConcurrentHashMap();
        private int index = 0;
        private final Stack<Node> stack = new Stack<>();
        private final Map<Descriptors.Descriptor, Node> nodeCache = new HashMap();

        IsInitializedCheckAnalyzer() {
        }

        public boolean needsIsInitializedCheck(Descriptors.Descriptor descriptor) {
            Boolean cachedValue = this.resultCache.get(descriptor);
            if (cachedValue != null) {
                return cachedValue.booleanValue();
            }
            synchronized (this) {
                Boolean cachedValue2 = this.resultCache.get(descriptor);
                if (cachedValue2 != null) {
                    return cachedValue2.booleanValue();
                }
                return dfs(descriptor).component.needsIsInitializedCheck;
            }
        }

        private static class Node {
            StronglyConnectedComponent component = null;
            final Descriptors.Descriptor descriptor;
            final int index;
            int lowLink;

            Node(Descriptors.Descriptor descriptor, int index) {
                this.descriptor = descriptor;
                this.index = index;
                this.lowLink = index;
            }
        }

        private static class StronglyConnectedComponent {
            final List<Descriptors.Descriptor> messages;
            boolean needsIsInitializedCheck;

            private StronglyConnectedComponent() {
                this.messages = new ArrayList();
                this.needsIsInitializedCheck = false;
            }
        }

        private Node dfs(Descriptors.Descriptor descriptor) {
            Node node;
            int i = this.index;
            this.index = i + 1;
            Node result = new Node(descriptor, i);
            this.stack.push(result);
            this.nodeCache.put(descriptor, result);
            for (Descriptors.FieldDescriptor field : descriptor.getFields()) {
                if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    Node child = this.nodeCache.get(field.getMessageType());
                    if (child == null) {
                        result.lowLink = Math.min(result.lowLink, dfs(field.getMessageType()).lowLink);
                    } else if (child.component == null) {
                        result.lowLink = Math.min(result.lowLink, child.lowLink);
                    }
                }
            }
            if (result.index == result.lowLink) {
                StronglyConnectedComponent component = new StronglyConnectedComponent();
                do {
                    node = this.stack.pop();
                    node.component = component;
                    component.messages.add(node.descriptor);
                } while (node != result);
                analyze(component);
            }
            return result;
        }

        private void analyze(StronglyConnectedComponent component) {
            boolean needsIsInitializedCheck = false;
            Iterator<Descriptors.Descriptor> it = component.messages.iterator();
            loop0: while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Descriptors.Descriptor descriptor = it.next();
                if (descriptor.isExtendable()) {
                    needsIsInitializedCheck = true;
                    break;
                }
                for (Descriptors.FieldDescriptor field : descriptor.getFields()) {
                    if (field.isRequired()) {
                        needsIsInitializedCheck = true;
                        break loop0;
                    } else if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                        Node node = this.nodeCache.get(field.getMessageType());
                        if (node.component != component && node.component.needsIsInitializedCheck) {
                            needsIsInitializedCheck = true;
                            break loop0;
                        }
                    }
                }
            }
            component.needsIsInitializedCheck = needsIsInitializedCheck;
            Iterator<Descriptors.Descriptor> it2 = component.messages.iterator();
            while (it2.hasNext()) {
                this.resultCache.put(it2.next(), Boolean.valueOf(component.needsIsInitializedCheck));
            }
        }
    }

    private static boolean needsIsInitializedCheck(Descriptors.Descriptor descriptor) {
        return isInitializedCheckAnalyzer.needsIsInitializedCheck(descriptor);
    }

    /* JADX WARN: Incorrect condition in loop: B:4:0x0036 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.google.protobuf.StructuralMessageInfo convertProto2(java.lang.Class<?> r21, com.google.protobuf.Descriptors.Descriptor r22) {
        /*
            Method dump skipped, instruction units count: 438
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.DescriptorMessageInfoFactory.convertProto2(java.lang.Class, com.google.protobuf.Descriptors$Descriptor):com.google.protobuf.StructuralMessageInfo");
    }

    private static StructuralMessageInfo convertProto3(Class<?> messageType, Descriptors.Descriptor messageDescriptor) {
        List<Descriptors.FieldDescriptor> fieldDescriptors = messageDescriptor.getFields();
        StructuralMessageInfo.Builder builder = StructuralMessageInfo.newBuilder(fieldDescriptors.size());
        builder.withDefaultInstance(getDefaultInstance(messageType));
        builder.withSyntax(ProtoSyntax.PROTO3);
        OneofState oneofState = new OneofState();
        for (int i = 0; i < fieldDescriptors.size(); i++) {
            Descriptors.FieldDescriptor fd = fieldDescriptors.get(i);
            if (fd.getContainingOneof() != null) {
                builder.withField(buildOneofMember(messageType, fd, oneofState, true, null));
            } else if (fd.isMapField()) {
                builder.withField(FieldInfo.forMapField(field(messageType, fd), fd.getNumber(), SchemaUtil.getMapDefaultEntry(messageType, fd.getName()), null));
            } else if (fd.isRepeated() && fd.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                builder.withField(FieldInfo.forRepeatedMessageField(field(messageType, fd), fd.getNumber(), getFieldType(fd), getTypeForRepeatedMessageField(messageType, fd)));
            } else if (fd.isPacked()) {
                builder.withField(FieldInfo.forPackedField(field(messageType, fd), fd.getNumber(), getFieldType(fd), cachedSizeField(messageType, fd)));
            } else {
                builder.withField(FieldInfo.forField(field(messageType, fd), fd.getNumber(), getFieldType(fd), true));
            }
        }
        return builder.build();
    }

    private static FieldInfo buildOneofMember(Class<?> messageType, Descriptors.FieldDescriptor fd, OneofState oneofState, boolean enforceUtf8, Internal.EnumVerifier enumVerifier) {
        OneofInfo oneof = oneofState.getOneof(messageType, fd.getContainingOneof());
        FieldType type = getFieldType(fd);
        Class<?> oneofStoredType = getOneofStoredType(messageType, fd, type);
        return FieldInfo.forOneofMemberField(fd.getNumber(), type, oneof, oneofStoredType, enforceUtf8, enumVerifier);
    }

    private static Class<?> getOneofStoredType(Class<?> messageType, Descriptors.FieldDescriptor fd, FieldType type) {
        switch (AnonymousClass3.$SwitchMap$com$google$protobuf$JavaType[type.getJavaType().ordinal()]) {
            case 1:
                return Boolean.class;
            case 2:
                return ByteString.class;
            case 3:
                return Double.class;
            case 4:
                return Float.class;
            case 5:
            case 6:
                return Integer.class;
            case 7:
                return Long.class;
            case 8:
                return String.class;
            case 9:
                return getOneofStoredTypeForMessage(messageType, fd);
            default:
                throw new IllegalArgumentException("Invalid type for oneof: " + type);
        }
    }

    /* JADX INFO: renamed from: com.google.protobuf.DescriptorMessageInfoFactory$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type;
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Descriptors$FileDescriptor$Syntax;
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$JavaType;

        static {
            int[] iArr = new int[Descriptors.FieldDescriptor.Type.values().length];
            $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type = iArr;
            try {
                iArr[Descriptors.FieldDescriptor.Type.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.ENUM.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.GROUP.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.INT32.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.INT64.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.MESSAGE.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SFIXED32.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SFIXED64.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SINT32.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SINT64.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.STRING.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.UINT32.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.UINT64.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            int[] iArr2 = new int[JavaType.values().length];
            $SwitchMap$com$google$protobuf$JavaType = iArr2;
            try {
                iArr2[JavaType.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError e19) {
            }
            try {
                $SwitchMap$com$google$protobuf$JavaType[JavaType.BYTE_STRING.ordinal()] = 2;
            } catch (NoSuchFieldError e20) {
            }
            try {
                $SwitchMap$com$google$protobuf$JavaType[JavaType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError e21) {
            }
            try {
                $SwitchMap$com$google$protobuf$JavaType[JavaType.FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError e22) {
            }
            try {
                $SwitchMap$com$google$protobuf$JavaType[JavaType.ENUM.ordinal()] = 5;
            } catch (NoSuchFieldError e23) {
            }
            try {
                $SwitchMap$com$google$protobuf$JavaType[JavaType.INT.ordinal()] = 6;
            } catch (NoSuchFieldError e24) {
            }
            try {
                $SwitchMap$com$google$protobuf$JavaType[JavaType.LONG.ordinal()] = 7;
            } catch (NoSuchFieldError e25) {
            }
            try {
                $SwitchMap$com$google$protobuf$JavaType[JavaType.STRING.ordinal()] = 8;
            } catch (NoSuchFieldError e26) {
            }
            try {
                $SwitchMap$com$google$protobuf$JavaType[JavaType.MESSAGE.ordinal()] = 9;
            } catch (NoSuchFieldError e27) {
            }
            int[] iArr3 = new int[Descriptors.FileDescriptor.Syntax.values().length];
            $SwitchMap$com$google$protobuf$Descriptors$FileDescriptor$Syntax = iArr3;
            try {
                iArr3[Descriptors.FileDescriptor.Syntax.PROTO2.ordinal()] = 1;
            } catch (NoSuchFieldError e28) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FileDescriptor$Syntax[Descriptors.FileDescriptor.Syntax.PROTO3.ordinal()] = 2;
            } catch (NoSuchFieldError e29) {
            }
        }
    }

    private static FieldType getFieldType(Descriptors.FieldDescriptor fd) {
        switch (AnonymousClass3.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[fd.getType().ordinal()]) {
            case 1:
                if (fd.isRepeated()) {
                    return fd.isPacked() ? FieldType.BOOL_LIST_PACKED : FieldType.BOOL_LIST;
                }
                return FieldType.BOOL;
            case 2:
                return fd.isRepeated() ? FieldType.BYTES_LIST : FieldType.BYTES;
            case 3:
                if (fd.isRepeated()) {
                    return fd.isPacked() ? FieldType.DOUBLE_LIST_PACKED : FieldType.DOUBLE_LIST;
                }
                return FieldType.DOUBLE;
            case 4:
                if (fd.isRepeated()) {
                    return fd.isPacked() ? FieldType.ENUM_LIST_PACKED : FieldType.ENUM_LIST;
                }
                return FieldType.ENUM;
            case 5:
                if (fd.isRepeated()) {
                    return fd.isPacked() ? FieldType.FIXED32_LIST_PACKED : FieldType.FIXED32_LIST;
                }
                return FieldType.FIXED32;
            case 6:
                if (fd.isRepeated()) {
                    return fd.isPacked() ? FieldType.FIXED64_LIST_PACKED : FieldType.FIXED64_LIST;
                }
                return FieldType.FIXED64;
            case 7:
                if (fd.isRepeated()) {
                    return fd.isPacked() ? FieldType.FLOAT_LIST_PACKED : FieldType.FLOAT_LIST;
                }
                return FieldType.FLOAT;
            case 8:
                return fd.isRepeated() ? FieldType.GROUP_LIST : FieldType.GROUP;
            case 9:
                if (fd.isRepeated()) {
                    return fd.isPacked() ? FieldType.INT32_LIST_PACKED : FieldType.INT32_LIST;
                }
                return FieldType.INT32;
            case 10:
                if (fd.isRepeated()) {
                    return fd.isPacked() ? FieldType.INT64_LIST_PACKED : FieldType.INT64_LIST;
                }
                return FieldType.INT64;
            case 11:
                if (fd.isMapField()) {
                    return FieldType.MAP;
                }
                return fd.isRepeated() ? FieldType.MESSAGE_LIST : FieldType.MESSAGE;
            case 12:
                if (fd.isRepeated()) {
                    return fd.isPacked() ? FieldType.SFIXED32_LIST_PACKED : FieldType.SFIXED32_LIST;
                }
                return FieldType.SFIXED32;
            case 13:
                if (fd.isRepeated()) {
                    return fd.isPacked() ? FieldType.SFIXED64_LIST_PACKED : FieldType.SFIXED64_LIST;
                }
                return FieldType.SFIXED64;
            case 14:
                if (fd.isRepeated()) {
                    return fd.isPacked() ? FieldType.SINT32_LIST_PACKED : FieldType.SINT32_LIST;
                }
                return FieldType.SINT32;
            case 15:
                if (fd.isRepeated()) {
                    return fd.isPacked() ? FieldType.SINT64_LIST_PACKED : FieldType.SINT64_LIST;
                }
                return FieldType.SINT64;
            case 16:
                return fd.isRepeated() ? FieldType.STRING_LIST : FieldType.STRING;
            case 17:
                if (fd.isRepeated()) {
                    return fd.isPacked() ? FieldType.UINT32_LIST_PACKED : FieldType.UINT32_LIST;
                }
                return FieldType.UINT32;
            case 18:
                if (fd.isRepeated()) {
                    return fd.isPacked() ? FieldType.UINT64_LIST_PACKED : FieldType.UINT64_LIST;
                }
                return FieldType.UINT64;
            default:
                throw new IllegalArgumentException("Unsupported field type: " + fd.getType());
        }
    }

    private static java.lang.reflect.Field bitField(Class<?> messageType, int index) {
        return field(messageType, "bitField" + index + "_");
    }

    private static java.lang.reflect.Field field(Class<?> messageType, Descriptors.FieldDescriptor fd) {
        return field(messageType, getFieldName(fd));
    }

    private static java.lang.reflect.Field cachedSizeField(Class<?> messageType, Descriptors.FieldDescriptor fd) {
        return field(messageType, getCachedSizeFieldName(fd));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static java.lang.reflect.Field field(Class<?> messageType, String fieldName) {
        try {
            return messageType.getDeclaredField(fieldName);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to find field " + fieldName + " in message class " + messageType.getName());
        }
    }

    static String getFieldName(Descriptors.FieldDescriptor fd) {
        String name;
        String suffix;
        if (fd.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
            name = fd.getMessageType().getName();
        } else {
            name = fd.getName();
        }
        String upperCamelCaseName = snakeCaseToUpperCamelCase(name);
        if (specialFieldNames.contains(upperCamelCaseName)) {
            suffix = "__";
        } else {
            suffix = "_";
        }
        return snakeCaseToLowerCamelCase(name) + suffix;
    }

    private static String getCachedSizeFieldName(Descriptors.FieldDescriptor fd) {
        return snakeCaseToLowerCamelCase(fd.getName()) + "MemoizedSerializedSize";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String snakeCaseToLowerCamelCase(String snakeCase) {
        return snakeCaseToCamelCase(snakeCase, false);
    }

    private static String snakeCaseToUpperCamelCase(String snakeCase) {
        return snakeCaseToCamelCase(snakeCase, true);
    }

    private static String snakeCaseToCamelCase(String snakeCase, boolean capFirst) {
        StringBuilder sb = new StringBuilder(snakeCase.length() + 1);
        boolean capNext = capFirst;
        for (int ctr = 0; ctr < snakeCase.length(); ctr++) {
            char next = snakeCase.charAt(ctr);
            if (next == '_') {
                capNext = true;
            } else if (Character.isDigit(next)) {
                sb.append(next);
                capNext = true;
            } else if (capNext) {
                sb.append(Character.toUpperCase(next));
                capNext = false;
            } else if (ctr == 0) {
                sb.append(Character.toLowerCase(next));
            } else {
                sb.append(next);
            }
        }
        return sb.toString();
    }

    private static Class<?> getOneofStoredTypeForMessage(Class<?> messageType, Descriptors.FieldDescriptor fd) {
        try {
            String name = fd.getType() == Descriptors.FieldDescriptor.Type.GROUP ? fd.getMessageType().getName() : fd.getName();
            java.lang.reflect.Method getter = messageType.getDeclaredMethod(getterForField(name), new Class[0]);
            return getter.getReturnType();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Class<?> getTypeForRepeatedMessageField(Class<?> messageType, Descriptors.FieldDescriptor fd) {
        try {
            String name = fd.getType() == Descriptors.FieldDescriptor.Type.GROUP ? fd.getMessageType().getName() : fd.getName();
            java.lang.reflect.Method getter = messageType.getDeclaredMethod(getterForField(name), Integer.TYPE);
            return getter.getReturnType();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getterForField(String snakeCase) {
        String camelCase = snakeCaseToLowerCamelCase(snakeCase);
        return "get" + Character.toUpperCase(camelCase.charAt(0)) + camelCase.substring(1, camelCase.length());
    }

    private static final class OneofState {
        private OneofInfo[] oneofs;

        private OneofState() {
            this.oneofs = new OneofInfo[2];
        }

        OneofInfo getOneof(Class<?> messageType, Descriptors.OneofDescriptor desc) {
            int index = desc.getIndex();
            OneofInfo[] oneofInfoArr = this.oneofs;
            if (index >= oneofInfoArr.length) {
                this.oneofs = (OneofInfo[]) Arrays.copyOf(oneofInfoArr, index * 2);
            }
            OneofInfo info = this.oneofs[index];
            if (info == null) {
                OneofInfo info2 = newInfo(messageType, desc);
                this.oneofs[index] = info2;
                return info2;
            }
            return info;
        }

        private static OneofInfo newInfo(Class<?> messageType, Descriptors.OneofDescriptor desc) {
            String camelCase = DescriptorMessageInfoFactory.snakeCaseToLowerCamelCase(desc.getName());
            String valueFieldName = camelCase + "_";
            String caseFieldName = camelCase + "Case_";
            return new OneofInfo(desc.getIndex(), DescriptorMessageInfoFactory.field(messageType, caseFieldName), DescriptorMessageInfoFactory.field(messageType, valueFieldName));
        }
    }
}
