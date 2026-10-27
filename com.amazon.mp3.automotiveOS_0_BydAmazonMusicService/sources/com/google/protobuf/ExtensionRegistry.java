package com.google.protobuf;

import androidx.core.internal.view.SupportMenu;
import com.google.protobuf.Descriptors;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes7.dex */
public class ExtensionRegistry extends ExtensionRegistryLite {
    static final ExtensionRegistry EMPTY_REGISTRY = new ExtensionRegistry(true);
    private final Map<String, ExtensionInfo> immutableExtensionsByName;
    private final Map<DescriptorIntPair, ExtensionInfo> immutableExtensionsByNumber;
    private final Map<String, ExtensionInfo> mutableExtensionsByName;
    private final Map<DescriptorIntPair, ExtensionInfo> mutableExtensionsByNumber;

    public static final class ExtensionInfo {
        public final Message defaultInstance;
        public final Descriptors.FieldDescriptor descriptor;
    }

    public static ExtensionRegistry getEmptyRegistry() {
        return EMPTY_REGISTRY;
    }

    public ExtensionInfo findImmutableExtensionByNumber(final Descriptors.Descriptor containingType, final int fieldNumber) {
        return this.immutableExtensionsByNumber.get(new DescriptorIntPair(containingType, fieldNumber));
    }

    private ExtensionRegistry() {
        this.immutableExtensionsByName = new HashMap();
        this.mutableExtensionsByName = new HashMap();
        this.immutableExtensionsByNumber = new HashMap();
        this.mutableExtensionsByNumber = new HashMap();
    }

    ExtensionRegistry(boolean empty) {
        super(ExtensionRegistryLite.EMPTY_REGISTRY_LITE);
        this.immutableExtensionsByName = Collections.emptyMap();
        this.mutableExtensionsByName = Collections.emptyMap();
        this.immutableExtensionsByNumber = Collections.emptyMap();
        this.mutableExtensionsByNumber = Collections.emptyMap();
    }

    private static final class DescriptorIntPair {
        private final Descriptors.Descriptor descriptor;
        private final int number;

        DescriptorIntPair(final Descriptors.Descriptor descriptor, final int number) {
            this.descriptor = descriptor;
            this.number = number;
        }

        public int hashCode() {
            return (this.descriptor.hashCode() * SupportMenu.USER_MASK) + this.number;
        }

        public boolean equals(final Object obj) {
            if (!(obj instanceof DescriptorIntPair)) {
                return false;
            }
            DescriptorIntPair descriptorIntPair = (DescriptorIntPair) obj;
            return this.descriptor == descriptorIntPair.descriptor && this.number == descriptorIntPair.number;
        }
    }
}
