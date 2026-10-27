package com.google.protobuf;

import com.google.protobuf.Descriptors;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes7.dex */
public class TypeRegistry {
    private static final Logger logger = Logger.getLogger(TypeRegistry.class.getName());
    private final Map<String, Descriptors.Descriptor> types;

    private static class EmptyTypeRegistryHolder {
        private static final TypeRegistry EMPTY = new TypeRegistry(Collections.emptyMap());

        private EmptyTypeRegistryHolder() {
        }
    }

    public static TypeRegistry getEmptyTypeRegistry() {
        return EmptyTypeRegistryHolder.EMPTY;
    }

    public Descriptors.Descriptor find(String name) {
        return this.types.get(name);
    }

    public final Descriptors.Descriptor getDescriptorForTypeUrl(String typeUrl) throws InvalidProtocolBufferException {
        return find(getTypeName(typeUrl));
    }

    TypeRegistry(Map<String, Descriptors.Descriptor> types) {
        this.types = types;
    }

    private static String getTypeName(String typeUrl) throws InvalidProtocolBufferException {
        String[] strArrSplit = typeUrl.split("/");
        if (strArrSplit.length == 1) {
            throw new InvalidProtocolBufferException("Invalid type url found: " + typeUrl);
        }
        return strArrSplit[strArrSplit.length - 1];
    }
}
