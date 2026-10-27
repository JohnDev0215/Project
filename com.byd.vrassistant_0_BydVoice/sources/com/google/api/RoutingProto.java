package com.google.api;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessageV3;

/* JADX INFO: loaded from: classes2.dex */
public final class RoutingProto {
    public static final int ROUTING_FIELD_NUMBER = 72295729;
    private static Descriptors.FileDescriptor descriptor;
    static final Descriptors.Descriptor internal_static_google_api_RoutingParameter_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_RoutingParameter_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_api_RoutingRule_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_RoutingRule_fieldAccessorTable;
    public static final GeneratedMessage.GeneratedExtension<DescriptorProtos.MethodOptions, RoutingRule> routing;

    private RoutingProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
        extensionRegistryLite.add(routing);
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    static {
        GeneratedMessage.GeneratedExtension<DescriptorProtos.MethodOptions, RoutingRule> generatedExtensionNewFileScopedGeneratedExtension = GeneratedMessage.newFileScopedGeneratedExtension(RoutingRule.class, RoutingRule.getDefaultInstance());
        routing = generatedExtensionNewFileScopedGeneratedExtension;
        descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0018google/api/routing.proto\u0012\ngoogle.api\u001a google/protobuf/descriptor.proto\"G\n\u000bRoutingRule\u00128\n\u0012routing_parameters\u0018\u0002 \u0003(\u000b2\u001c.google.api.RoutingParameter\"8\n\u0010RoutingParameter\u0012\r\n\u0005field\u0018\u0001 \u0001(\t\u0012\u0015\n\rpath_template\u0018\u0002 \u0001(\t:K\n\u0007routing\u0012\u001e.google.protobuf.MethodOptions\u0018±Ê¼\" \u0001(\u000b2\u0017.google.api.RoutingRuleBj\n\u000ecom.google.apiB\fRoutingProtoP\u0001ZAgoogle.golang.org/genproto/googleapis/api/annotations;annotations¢\u0002\u0004GAPIb\u0006proto3"}, new Descriptors.FileDescriptor[]{DescriptorProtos.getDescriptor()});
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_google_api_RoutingRule_descriptor = descriptor2;
        internal_static_google_api_RoutingRule_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"RoutingParameters"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        internal_static_google_api_RoutingParameter_descriptor = descriptor3;
        internal_static_google_api_RoutingParameter_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Field", "PathTemplate"});
        generatedExtensionNewFileScopedGeneratedExtension.internalInit(descriptor.getExtensions().get(0));
        DescriptorProtos.getDescriptor();
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
