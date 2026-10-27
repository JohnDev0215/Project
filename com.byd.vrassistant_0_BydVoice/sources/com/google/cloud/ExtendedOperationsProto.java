package com.google.cloud;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;

/* JADX INFO: loaded from: classes2.dex */
public final class ExtendedOperationsProto {
    public static final int OPERATION_FIELD_FIELD_NUMBER = 1149;
    public static final int OPERATION_POLLING_METHOD_FIELD_NUMBER = 1250;
    public static final int OPERATION_REQUEST_FIELD_FIELD_NUMBER = 1150;
    public static final int OPERATION_RESPONSE_FIELD_FIELD_NUMBER = 1151;
    public static final int OPERATION_SERVICE_FIELD_NUMBER = 1249;
    private static Descriptors.FileDescriptor descriptor;
    public static final GeneratedMessage.GeneratedExtension<DescriptorProtos.FieldOptions, OperationResponseMapping> operationField;
    public static final GeneratedMessage.GeneratedExtension<DescriptorProtos.MethodOptions, Boolean> operationPollingMethod;
    public static final GeneratedMessage.GeneratedExtension<DescriptorProtos.FieldOptions, String> operationRequestField;
    public static final GeneratedMessage.GeneratedExtension<DescriptorProtos.FieldOptions, String> operationResponseField;
    public static final GeneratedMessage.GeneratedExtension<DescriptorProtos.MethodOptions, String> operationService;

    private ExtendedOperationsProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
        extensionRegistryLite.add(operationField);
        extensionRegistryLite.add(operationRequestField);
        extensionRegistryLite.add(operationResponseField);
        extensionRegistryLite.add(operationService);
        extensionRegistryLite.add(operationPollingMethod);
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    static {
        GeneratedMessage.GeneratedExtension<DescriptorProtos.FieldOptions, OperationResponseMapping> generatedExtensionNewFileScopedGeneratedExtension = GeneratedMessage.newFileScopedGeneratedExtension(OperationResponseMapping.class, null);
        operationField = generatedExtensionNewFileScopedGeneratedExtension;
        GeneratedMessage.GeneratedExtension<DescriptorProtos.FieldOptions, String> generatedExtensionNewFileScopedGeneratedExtension2 = GeneratedMessage.newFileScopedGeneratedExtension(String.class, null);
        operationRequestField = generatedExtensionNewFileScopedGeneratedExtension2;
        GeneratedMessage.GeneratedExtension<DescriptorProtos.FieldOptions, String> generatedExtensionNewFileScopedGeneratedExtension3 = GeneratedMessage.newFileScopedGeneratedExtension(String.class, null);
        operationResponseField = generatedExtensionNewFileScopedGeneratedExtension3;
        GeneratedMessage.GeneratedExtension<DescriptorProtos.MethodOptions, String> generatedExtensionNewFileScopedGeneratedExtension4 = GeneratedMessage.newFileScopedGeneratedExtension(String.class, null);
        operationService = generatedExtensionNewFileScopedGeneratedExtension4;
        GeneratedMessage.GeneratedExtension<DescriptorProtos.MethodOptions, Boolean> generatedExtensionNewFileScopedGeneratedExtension5 = GeneratedMessage.newFileScopedGeneratedExtension(Boolean.class, null);
        operationPollingMethod = generatedExtensionNewFileScopedGeneratedExtension5;
        Descriptors.FileDescriptor fileDescriptorInternalBuildGeneratedFileFrom = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n&google/cloud/extended_operations.proto\u0012\fgoogle.cloud\u001a google/protobuf/descriptor.proto*b\n\u0018OperationResponseMapping\u0012\r\n\tUNDEFINED\u0010\u0000\u0012\b\n\u0004NAME\u0010\u0001\u0012\n\n\u0006STATUS\u0010\u0002\u0012\u000e\n\nERROR_CODE\u0010\u0003\u0012\u0011\n\rERROR_MESSAGE\u0010\u0004:_\n\u000foperation_field\u0012\u001d.google.protobuf.FieldOptions\u0018ý\b \u0001(\u000e2&.google.cloud.OperationResponseMapping:?\n\u0017operation_request_field\u0012\u001d.google.protobuf.FieldOptions\u0018þ\b \u0001(\t:@\n\u0018operation_response_field\u0012\u001d.google.protobuf.FieldOptions\u0018ÿ\b \u0001(\t::\n\u0011operation_service\u0012\u001e.google.protobuf.MethodOptions\u0018á\t \u0001(\t:A\n\u0018operation_polling_method\u0012\u001e.google.protobuf.MethodOptions\u0018â\t \u0001(\bBy\n\u0010com.google.cloudB\u0017ExtendedOperationsProtoP\u0001ZCgoogle.golang.org/genproto/googleapis/cloud/extendedops;extendedops¢\u0002\u0004GAPIb\u0006proto3"}, new Descriptors.FileDescriptor[]{DescriptorProtos.getDescriptor()});
        descriptor = fileDescriptorInternalBuildGeneratedFileFrom;
        generatedExtensionNewFileScopedGeneratedExtension.internalInit(fileDescriptorInternalBuildGeneratedFileFrom.getExtensions().get(0));
        generatedExtensionNewFileScopedGeneratedExtension2.internalInit(descriptor.getExtensions().get(1));
        generatedExtensionNewFileScopedGeneratedExtension3.internalInit(descriptor.getExtensions().get(2));
        generatedExtensionNewFileScopedGeneratedExtension4.internalInit(descriptor.getExtensions().get(3));
        generatedExtensionNewFileScopedGeneratedExtension5.internalInit(descriptor.getExtensions().get(4));
        DescriptorProtos.getDescriptor();
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
