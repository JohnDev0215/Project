package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Extension;
import com.google.protobuf.GeneratedMessage;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ExtensionRegistry extends ExtensionRegistryLite {
    static final ExtensionRegistry EMPTY_REGISTRY = new ExtensionRegistry(true);
    private final Map<String, ExtensionInfo> immutableExtensionsByName;
    private final Map<DescriptorIntPair, ExtensionInfo> immutableExtensionsByNumber;
    private final Map<String, ExtensionInfo> mutableExtensionsByName;
    private final Map<DescriptorIntPair, ExtensionInfo> mutableExtensionsByNumber;

    public static ExtensionRegistry newInstance() {
        return new ExtensionRegistry();
    }

    public static ExtensionRegistry getEmptyRegistry() {
        return EMPTY_REGISTRY;
    }

    @Override // com.google.protobuf.ExtensionRegistryLite
    public ExtensionRegistry getUnmodifiable() {
        return new ExtensionRegistry(this);
    }

    public static final class ExtensionInfo {
        public final Message defaultInstance;
        public final Descriptors.FieldDescriptor descriptor;

        /* synthetic */ ExtensionInfo(Descriptors.FieldDescriptor x0, Message x1, AnonymousClass1 x2) {
            this(x0, x1);
        }

        private ExtensionInfo(Descriptors.FieldDescriptor descriptor) {
            this.descriptor = descriptor;
            this.defaultInstance = null;
        }

        private ExtensionInfo(Descriptors.FieldDescriptor descriptor, Message defaultInstance) {
            this.descriptor = descriptor;
            this.defaultInstance = defaultInstance;
        }
    }

    @Deprecated
    public ExtensionInfo findExtensionByName(String fullName) {
        return findImmutableExtensionByName(fullName);
    }

    public ExtensionInfo findImmutableExtensionByName(String fullName) {
        return this.immutableExtensionsByName.get(fullName);
    }

    public ExtensionInfo findMutableExtensionByName(String fullName) {
        return this.mutableExtensionsByName.get(fullName);
    }

    @Deprecated
    public ExtensionInfo findExtensionByNumber(Descriptors.Descriptor containingType, int fieldNumber) {
        return findImmutableExtensionByNumber(containingType, fieldNumber);
    }

    public ExtensionInfo findImmutableExtensionByNumber(Descriptors.Descriptor containingType, int fieldNumber) {
        return this.immutableExtensionsByNumber.get(new DescriptorIntPair(containingType, fieldNumber));
    }

    public ExtensionInfo findMutableExtensionByNumber(Descriptors.Descriptor containingType, int fieldNumber) {
        return this.mutableExtensionsByNumber.get(new DescriptorIntPair(containingType, fieldNumber));
    }

    public Set<ExtensionInfo> getAllMutableExtensionsByExtendedType(String fullName) {
        HashSet<ExtensionInfo> extensions = new HashSet<>();
        for (DescriptorIntPair pair : this.mutableExtensionsByNumber.keySet()) {
            if (pair.descriptor.getFullName().equals(fullName)) {
                extensions.add(this.mutableExtensionsByNumber.get(pair));
            }
        }
        return extensions;
    }

    public Set<ExtensionInfo> getAllImmutableExtensionsByExtendedType(String fullName) {
        HashSet<ExtensionInfo> extensions = new HashSet<>();
        for (DescriptorIntPair pair : this.immutableExtensionsByNumber.keySet()) {
            if (pair.descriptor.getFullName().equals(fullName)) {
                extensions.add(this.immutableExtensionsByNumber.get(pair));
            }
        }
        return extensions;
    }

    public void add(Extension<?, ?> extension) {
        if (extension.getExtensionType() != Extension.ExtensionType.IMMUTABLE && extension.getExtensionType() != Extension.ExtensionType.MUTABLE) {
            return;
        }
        add(newExtensionInfo(extension), extension.getExtensionType());
    }

    public void add(GeneratedMessage.GeneratedExtension<?, ?> extension) {
        add((Extension<?, ?>) extension);
    }

    static ExtensionInfo newExtensionInfo(Extension<?, ?> extension) {
        AnonymousClass1 anonymousClass1 = null;
        byte b = 0;
        byte b2 = 0;
        if (extension.getDescriptor().getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            if (extension.getMessageDefaultInstance() == null) {
                throw new IllegalStateException("Registered message-type extension had null default instance: " + extension.getDescriptor().getFullName());
            }
            return new ExtensionInfo(extension.getDescriptor(), extension.getMessageDefaultInstance(), anonymousClass1);
        }
        return new ExtensionInfo(extension.getDescriptor(), b2 == true ? 1 : 0, b == true ? 1 : 0);
    }

    public void add(Descriptors.FieldDescriptor fieldDescriptor) {
        if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            throw new IllegalArgumentException("ExtensionRegistry.add() must be provided a default instance when adding an embedded message extension.");
        }
        ExtensionInfo extensionInfo = new ExtensionInfo(fieldDescriptor, null, 0 == true ? 1 : 0);
        add(extensionInfo, Extension.ExtensionType.IMMUTABLE);
        add(extensionInfo, Extension.ExtensionType.MUTABLE);
    }

    public void add(Descriptors.FieldDescriptor type, Message defaultInstance) {
        if (type.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            throw new IllegalArgumentException("ExtensionRegistry.add() provided a default instance for a non-message extension.");
        }
        add(new ExtensionInfo(type, defaultInstance, null), Extension.ExtensionType.IMMUTABLE);
    }

    private ExtensionRegistry() {
        this.immutableExtensionsByName = new HashMap();
        this.mutableExtensionsByName = new HashMap();
        this.immutableExtensionsByNumber = new HashMap();
        this.mutableExtensionsByNumber = new HashMap();
    }

    private ExtensionRegistry(ExtensionRegistry other) {
        super(other);
        this.immutableExtensionsByName = Collections.unmodifiableMap(other.immutableExtensionsByName);
        this.mutableExtensionsByName = Collections.unmodifiableMap(other.mutableExtensionsByName);
        this.immutableExtensionsByNumber = Collections.unmodifiableMap(other.immutableExtensionsByNumber);
        this.mutableExtensionsByNumber = Collections.unmodifiableMap(other.mutableExtensionsByNumber);
    }

    ExtensionRegistry(boolean empty) {
        super(EMPTY_REGISTRY_LITE);
        this.immutableExtensionsByName = Collections.emptyMap();
        this.mutableExtensionsByName = Collections.emptyMap();
        this.immutableExtensionsByNumber = Collections.emptyMap();
        this.mutableExtensionsByNumber = Collections.emptyMap();
    }

    private void add(ExtensionInfo extension, Extension.ExtensionType extensionType) {
        Map<String, ExtensionInfo> extensionsByName;
        Map<DescriptorIntPair, ExtensionInfo> extensionsByNumber;
        if (!extension.descriptor.isExtension()) {
            throw new IllegalArgumentException("ExtensionRegistry.add() was given a FieldDescriptor for a regular (non-extension) field.");
        }
        int i = AnonymousClass1.$SwitchMap$com$google$protobuf$Extension$ExtensionType[extensionType.ordinal()];
        if (i == 1) {
            extensionsByName = this.immutableExtensionsByName;
            extensionsByNumber = this.immutableExtensionsByNumber;
        } else if (i == 2) {
            extensionsByName = this.mutableExtensionsByName;
            extensionsByNumber = this.mutableExtensionsByNumber;
        } else {
            return;
        }
        extensionsByName.put(extension.descriptor.getFullName(), extension);
        extensionsByNumber.put(new DescriptorIntPair(extension.descriptor.getContainingType(), extension.descriptor.getNumber()), extension);
        Descriptors.FieldDescriptor field = extension.descriptor;
        if (field.getContainingType().getOptions().getMessageSetWireFormat() && field.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && field.isOptional() && field.getExtensionScope() == field.getMessageType()) {
            extensionsByName.put(field.getMessageType().getFullName(), extension);
        }
    }

    /* JADX INFO: renamed from: com.google.protobuf.ExtensionRegistry$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Extension$ExtensionType;

        static {
            int[] iArr = new int[Extension.ExtensionType.values().length];
            $SwitchMap$com$google$protobuf$Extension$ExtensionType = iArr;
            try {
                iArr[Extension.ExtensionType.IMMUTABLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$Extension$ExtensionType[Extension.ExtensionType.MUTABLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    private static final class DescriptorIntPair {
        private final Descriptors.Descriptor descriptor;
        private final int number;

        DescriptorIntPair(Descriptors.Descriptor descriptor, int number) {
            this.descriptor = descriptor;
            this.number = number;
        }

        public int hashCode() {
            return (this.descriptor.hashCode() * 65535) + this.number;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof DescriptorIntPair)) {
                return false;
            }
            DescriptorIntPair other = (DescriptorIntPair) obj;
            return this.descriptor == other.descriptor && this.number == other.number;
        }
    }
}
