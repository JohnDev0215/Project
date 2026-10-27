package com.google.api;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessageV3;

/* JADX INFO: loaded from: classes2.dex */
public final class VisibilityProto {
    public static final int API_VISIBILITY_FIELD_NUMBER = 72295727;
    public static final int ENUM_VISIBILITY_FIELD_NUMBER = 72295727;
    public static final int FIELD_VISIBILITY_FIELD_NUMBER = 72295727;
    public static final int MESSAGE_VISIBILITY_FIELD_NUMBER = 72295727;
    public static final int METHOD_VISIBILITY_FIELD_NUMBER = 72295727;
    public static final int VALUE_VISIBILITY_FIELD_NUMBER = 72295727;
    public static final GeneratedMessage.GeneratedExtension<DescriptorProtos.ServiceOptions, VisibilityRule> apiVisibility;
    private static Descriptors.FileDescriptor descriptor;
    public static final GeneratedMessage.GeneratedExtension<DescriptorProtos.EnumOptions, VisibilityRule> enumVisibility;
    public static final GeneratedMessage.GeneratedExtension<DescriptorProtos.FieldOptions, VisibilityRule> fieldVisibility;
    static final Descriptors.Descriptor internal_static_google_api_VisibilityRule_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_VisibilityRule_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_api_Visibility_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_Visibility_fieldAccessorTable;
    public static final GeneratedMessage.GeneratedExtension<DescriptorProtos.MessageOptions, VisibilityRule> messageVisibility;
    public static final GeneratedMessage.GeneratedExtension<DescriptorProtos.MethodOptions, VisibilityRule> methodVisibility;
    public static final GeneratedMessage.GeneratedExtension<DescriptorProtos.EnumValueOptions, VisibilityRule> valueVisibility;

    private VisibilityProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
        extensionRegistryLite.add(enumVisibility);
        extensionRegistryLite.add(valueVisibility);
        extensionRegistryLite.add(fieldVisibility);
        extensionRegistryLite.add(messageVisibility);
        extensionRegistryLite.add(methodVisibility);
        extensionRegistryLite.add(apiVisibility);
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    static {
        GeneratedMessage.GeneratedExtension<DescriptorProtos.EnumOptions, VisibilityRule> generatedExtensionNewFileScopedGeneratedExtension = GeneratedMessage.newFileScopedGeneratedExtension(VisibilityRule.class, VisibilityRule.getDefaultInstance());
        enumVisibility = generatedExtensionNewFileScopedGeneratedExtension;
        GeneratedMessage.GeneratedExtension<DescriptorProtos.EnumValueOptions, VisibilityRule> generatedExtensionNewFileScopedGeneratedExtension2 = GeneratedMessage.newFileScopedGeneratedExtension(VisibilityRule.class, VisibilityRule.getDefaultInstance());
        valueVisibility = generatedExtensionNewFileScopedGeneratedExtension2;
        GeneratedMessage.GeneratedExtension<DescriptorProtos.FieldOptions, VisibilityRule> generatedExtensionNewFileScopedGeneratedExtension3 = GeneratedMessage.newFileScopedGeneratedExtension(VisibilityRule.class, VisibilityRule.getDefaultInstance());
        fieldVisibility = generatedExtensionNewFileScopedGeneratedExtension3;
        GeneratedMessage.GeneratedExtension<DescriptorProtos.MessageOptions, VisibilityRule> generatedExtensionNewFileScopedGeneratedExtension4 = GeneratedMessage.newFileScopedGeneratedExtension(VisibilityRule.class, VisibilityRule.getDefaultInstance());
        messageVisibility = generatedExtensionNewFileScopedGeneratedExtension4;
        GeneratedMessage.GeneratedExtension<DescriptorProtos.MethodOptions, VisibilityRule> generatedExtensionNewFileScopedGeneratedExtension5 = GeneratedMessage.newFileScopedGeneratedExtension(VisibilityRule.class, VisibilityRule.getDefaultInstance());
        methodVisibility = generatedExtensionNewFileScopedGeneratedExtension5;
        GeneratedMessage.GeneratedExtension<DescriptorProtos.ServiceOptions, VisibilityRule> generatedExtensionNewFileScopedGeneratedExtension6 = GeneratedMessage.newFileScopedGeneratedExtension(VisibilityRule.class, VisibilityRule.getDefaultInstance());
        apiVisibility = generatedExtensionNewFileScopedGeneratedExtension6;
        descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001bgoogle/api/visibility.proto\u0012\ngoogle.api\u001a google/protobuf/descriptor.proto\"7\n\nVisibility\u0012)\n\u0005rules\u0018\u0001 \u0003(\u000b2\u001a.google.api.VisibilityRule\"7\n\u000eVisibilityRule\u0012\u0010\n\bselector\u0018\u0001 \u0001(\t\u0012\u0013\n\u000brestriction\u0018\u0002 \u0001(\t:T\n\u000fenum_visibility\u0012\u001c.google.protobuf.EnumOptions\u0018¯Ê¼\" \u0001(\u000b2\u001a.google.api.VisibilityRule:Z\n\u0010value_visibility\u0012!.google.protobuf.EnumValueOptions\u0018¯Ê¼\" \u0001(\u000b2\u001a.google.api.VisibilityRule:V\n\u0010field_visibility\u0012\u001d.google.protobuf.FieldOptions\u0018¯Ê¼\" \u0001(\u000b2\u001a.google.api.VisibilityRule:Z\n\u0012message_visibility\u0012\u001f.google.protobuf.MessageOptions\u0018¯Ê¼\" \u0001(\u000b2\u001a.google.api.VisibilityRule:X\n\u0011method_visibility\u0012\u001e.google.protobuf.MethodOptions\u0018¯Ê¼\" \u0001(\u000b2\u001a.google.api.VisibilityRule:V\n\u000eapi_visibility\u0012\u001f.google.protobuf.ServiceOptions\u0018¯Ê¼\" \u0001(\u000b2\u001a.google.api.VisibilityRuleBn\n\u000ecom.google.apiB\u000fVisibilityProtoP\u0001Z?google.golang.org/genproto/googleapis/api/visibility;visibilityø\u0001\u0001¢\u0002\u0004GAPIb\u0006proto3"}, new Descriptors.FileDescriptor[]{DescriptorProtos.getDescriptor()});
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_google_api_Visibility_descriptor = descriptor2;
        internal_static_google_api_Visibility_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Rules"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        internal_static_google_api_VisibilityRule_descriptor = descriptor3;
        internal_static_google_api_VisibilityRule_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Selector", "Restriction"});
        generatedExtensionNewFileScopedGeneratedExtension.internalInit(descriptor.getExtensions().get(0));
        generatedExtensionNewFileScopedGeneratedExtension2.internalInit(descriptor.getExtensions().get(1));
        generatedExtensionNewFileScopedGeneratedExtension3.internalInit(descriptor.getExtensions().get(2));
        generatedExtensionNewFileScopedGeneratedExtension4.internalInit(descriptor.getExtensions().get(3));
        generatedExtensionNewFileScopedGeneratedExtension5.internalInit(descriptor.getExtensions().get(4));
        generatedExtensionNewFileScopedGeneratedExtension6.internalInit(descriptor.getExtensions().get(5));
        DescriptorProtos.getDescriptor();
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
