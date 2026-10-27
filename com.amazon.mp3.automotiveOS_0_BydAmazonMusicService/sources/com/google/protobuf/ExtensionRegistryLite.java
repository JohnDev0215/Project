package com.google.protobuf;

import androidx.core.internal.view.SupportMenu;
import com.google.protobuf.GeneratedMessageLite;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes7.dex */
public class ExtensionRegistryLite {
    static final ExtensionRegistryLite EMPTY_REGISTRY_LITE = new ExtensionRegistryLite(true);
    private static boolean doFullRuntimeInheritanceCheck = true;
    private static volatile boolean eagerlyParseMessageSets = false;
    private static volatile ExtensionRegistryLite emptyRegistry;
    private final Map<ObjectIntPair, GeneratedMessageLite.GeneratedExtension<?, ?>> extensionsByNumber;

    public static boolean isEagerlyParseMessageSets() {
        return eagerlyParseMessageSets;
    }

    public static ExtensionRegistryLite getEmptyRegistry() {
        if (!doFullRuntimeInheritanceCheck) {
            return EMPTY_REGISTRY_LITE;
        }
        ExtensionRegistryLite extensionRegistryLiteCreateEmpty = emptyRegistry;
        if (extensionRegistryLiteCreateEmpty == null) {
            synchronized (ExtensionRegistryLite.class) {
                extensionRegistryLiteCreateEmpty = emptyRegistry;
                if (extensionRegistryLiteCreateEmpty == null) {
                    extensionRegistryLiteCreateEmpty = ExtensionRegistryFactory.createEmpty();
                    emptyRegistry = extensionRegistryLiteCreateEmpty;
                }
            }
        }
        return extensionRegistryLiteCreateEmpty;
    }

    public <ContainingType extends MessageLite> GeneratedMessageLite.GeneratedExtension<ContainingType, ?> findLiteExtensionByNumber(final ContainingType containingTypeDefaultInstance, final int fieldNumber) {
        return (GeneratedMessageLite.GeneratedExtension) this.extensionsByNumber.get(new ObjectIntPair(containingTypeDefaultInstance, fieldNumber));
    }

    ExtensionRegistryLite() {
        this.extensionsByNumber = new HashMap();
    }

    ExtensionRegistryLite(ExtensionRegistryLite other) {
        if (other == EMPTY_REGISTRY_LITE) {
            this.extensionsByNumber = Collections.emptyMap();
        } else {
            this.extensionsByNumber = Collections.unmodifiableMap(other.extensionsByNumber);
        }
    }

    ExtensionRegistryLite(boolean empty) {
        this.extensionsByNumber = Collections.emptyMap();
    }

    private static final class ObjectIntPair {
        private final int number;
        private final Object object;

        ObjectIntPair(final Object object, final int number) {
            this.object = object;
            this.number = number;
        }

        public int hashCode() {
            return (System.identityHashCode(this.object) * SupportMenu.USER_MASK) + this.number;
        }

        public boolean equals(final Object obj) {
            if (!(obj instanceof ObjectIntPair)) {
                return false;
            }
            ObjectIntPair objectIntPair = (ObjectIntPair) obj;
            return this.object == objectIntPair.object && this.number == objectIntPair.number;
        }
    }
}
