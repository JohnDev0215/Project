package com.google.type;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX INFO: loaded from: classes3.dex */
public final class LocalizedTextProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n google/type/localized_text.proto\u0012\u000bgoogle.type\"4\n\rLocalizedText\u0012\f\n\u0004text\u0018\u0001 \u0001(\t\u0012\u0015\n\rlanguage_code\u0018\u0002 \u0001(\tBz\n\u000fcom.google.typeB\u0012LocalizedTextProtoP\u0001ZHgoogle.golang.org/genproto/googleapis/type/localized_text;localized_textø\u0001\u0001¢\u0002\u0003GTPb\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    static final Descriptors.Descriptor internal_static_google_type_LocalizedText_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_type_LocalizedText_fieldAccessorTable;

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private LocalizedTextProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_google_type_LocalizedText_descriptor = descriptor2;
        internal_static_google_type_LocalizedText_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Text", "LanguageCode"});
    }
}
