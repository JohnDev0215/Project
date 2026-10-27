package com.google.cloud.location;

import com.google.api.AnnotationsProto;
import com.google.api.ClientProto;
import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessageV3;

/* JADX INFO: loaded from: classes2.dex */
public final class LocationsProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n%google/cloud/location/locations.proto\u0012\u0015google.cloud.location\u001a\u001cgoogle/api/annotations.proto\u001a\u0019google/protobuf/any.proto\u001a\u0017google/api/client.proto\"[\n\u0014ListLocationsRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u000e\n\u0006filter\u0018\u0002 \u0001(\t\u0012\u0011\n\tpage_size\u0018\u0003 \u0001(\u0005\u0012\u0012\n\npage_token\u0018\u0004 \u0001(\t\"d\n\u0015ListLocationsResponse\u00122\n\tlocations\u0018\u0001 \u0003(\u000b2\u001f.google.cloud.location.Location\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t\"\"\n\u0012GetLocationRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\"×\u0001\n\bLocation\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0013\n\u000blocation_id\u0018\u0004 \u0001(\t\u0012\u0014\n\fdisplay_name\u0018\u0005 \u0001(\t\u0012;\n\u0006labels\u0018\u0002 \u0003(\u000b2+.google.cloud.location.Location.LabelsEntry\u0012&\n\bmetadata\u0018\u0003 \u0001(\u000b2\u0014.google.protobuf.Any\u001a-\n\u000bLabelsEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t:\u00028\u00012¤\u0003\n\tLocations\u0012«\u0001\n\rListLocations\u0012+.google.cloud.location.ListLocationsRequest\u001a,.google.cloud.location.ListLocationsResponse\"?\u0082Óä\u0093\u00029\u0012\u0014/v1/{name=locations}Z!\u0012\u001f/v1/{name=projects/*}/locations\u0012\u009e\u0001\n\u000bGetLocation\u0012).google.cloud.location.GetLocationRequest\u001a\u001f.google.cloud.location.Location\"C\u0082Óä\u0093\u0002=\u0012\u0016/v1/{name=locations/*}Z#\u0012!/v1/{name=projects/*/locations/*}\u001aHÊA\u0014cloud.googleapis.comÒA.https://www.googleapis.com/auth/cloud-platformBo\n\u0019com.google.cloud.locationB\u000eLocationsProtoP\u0001Z=google.golang.org/genproto/googleapis/cloud/location;locationø\u0001\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), AnyProto.getDescriptor(), ClientProto.getDescriptor()});
    static final Descriptors.Descriptor internal_static_google_cloud_location_GetLocationRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_cloud_location_GetLocationRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_cloud_location_ListLocationsRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_cloud_location_ListLocationsRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_cloud_location_ListLocationsResponse_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_cloud_location_ListLocationsResponse_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_cloud_location_Location_LabelsEntry_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_cloud_location_Location_LabelsEntry_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_cloud_location_Location_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_cloud_location_Location_fieldAccessorTable;

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private LocationsProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_google_cloud_location_ListLocationsRequest_descriptor = descriptor2;
        internal_static_google_cloud_location_ListLocationsRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Name", "Filter", "PageSize", "PageToken"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        internal_static_google_cloud_location_ListLocationsResponse_descriptor = descriptor3;
        internal_static_google_cloud_location_ListLocationsResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Locations", "NextPageToken"});
        Descriptors.Descriptor descriptor4 = getDescriptor().getMessageTypes().get(2);
        internal_static_google_cloud_location_GetLocationRequest_descriptor = descriptor4;
        internal_static_google_cloud_location_GetLocationRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Name"});
        Descriptors.Descriptor descriptor5 = getDescriptor().getMessageTypes().get(3);
        internal_static_google_cloud_location_Location_descriptor = descriptor5;
        internal_static_google_cloud_location_Location_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"Name", "LocationId", "DisplayName", "Labels", "Metadata"});
        Descriptors.Descriptor descriptor6 = descriptor5.getNestedTypes().get(0);
        internal_static_google_cloud_location_Location_LabelsEntry_descriptor = descriptor6;
        internal_static_google_cloud_location_Location_LabelsEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"Key", "Value"});
        ExtensionRegistry extensionRegistryNewInstance = ExtensionRegistry.newInstance();
        extensionRegistryNewInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ClientProto.defaultHost);
        extensionRegistryNewInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) AnnotationsProto.http);
        extensionRegistryNewInstance.add((GeneratedMessage.GeneratedExtension<?, ?>) ClientProto.oauthScopes);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, extensionRegistryNewInstance);
        AnnotationsProto.getDescriptor();
        AnyProto.getDescriptor();
        ClientProto.getDescriptor();
    }
}
