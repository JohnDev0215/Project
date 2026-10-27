package na.pb;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class DataCollectionOuterClass {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0014DataCollection.proto\u0012\u0005na.pb\"R\n\u000eDataCollection\u0012\u001d\n\u0006header\u0018\u0001 \u0001(\u000b2\r.na.pb.Header\u0012!\n\bdataBlob\u0018\u0002 \u0003(\u000b2\u000f.na.pb.DataBlob\"³\u0001\n\u0006Header\u0012\u0017\n\u0003oem\u0018\u0001 \u0001(\u000e2\n.na.pb.OEM\u0012\u000f\n\u0007country\u0018\u0002 \u0001(\t\u0012\u0010\n\bdeviceId\u0018\u0003 \u0001(\t\u0012\u000f\n\u0007carMode\u0018\u0004 \u0001(\t\u0012\u0013\n\u000bcollectTime\u0018\u0005 \u0001(\t\u0012\u0012\n\nappVersion\u0018\u0006 \u0001(\t\u0012\u0010\n\blanguage\u0018\u0007 \u0001(\t\u0012\u000e\n\u0006userId\u0018\b \u0001(\t\u0012\u0011\n\tdbVersion\u0018\t \u0001(\t\"\u0091\u0001\n\bDataBlob\u0012-\n\u000eeventComponent\u0018\u0001 \u0001(\u000e2\u0015.na.pb.EventComponent\u0012\u001f\n\u0007eventId\u0018\u0002 \u0001(\u000e2\u000e.na.pb.EventID\u0012#\n\teventType\u0018\u0003 \u0001(\u000e2\u0010.na.pb.EventType\u0012\u0010\n\bdataBlob\u0018\u0004 \u0001(\t\"/\n\rGuidanceStart\u0012\u0011\n\ttimeStamp\u0018\u0001 \u0001(\t\u0012\u000b\n\u0003eta\u0018\u0002 \u0001(\t\"-\n\u000bGuidanceEnd\u0012\u0011\n\ttimeStamp\u0018\u0001 \u0001(\t\u0012\u000b\n\u0003eta\u0018\u0002 \u0001(\t\"'\n\u0012NavigationAppStart\u0012\u0011\n\ttimeStamp\u0018\u0001 \u0001(\t\".\n\bNameInfo\u0012\u0014\n\flanguageCode\u0018\u0001 \u0001(\t\u0012\f\n\u0004name\u0018\u0002 \u0001(\t\"ò\u0001\n\u0011CalculateRouteEnd\u0012\u0011\n\ttimeStamp\u0018\u0001 \u0001(\t\u0012$\n\u000brouteSource\u0018\u0002 \u0001(\u000e2\u000f.na.pb.RouteSrc\u0012\u000b\n\u0003lat\u0018\u0003 \u0001(\t\u0012\u000b\n\u0003lon\u0018\u0004 \u0001(\t\u0012!\n\bdestType\u0018\u0005 \u0001(\u000e2\u000f.na.pb.DestType\u0012\u000f\n\u0007address\u0018\u0006 \u0001(\t\u0012\u001d\n\u0006poiSrc\u0018\u0007 \u0001(\u000e2\r.na.pb.PoiSrc\u0012\u0013\n\u000bpoiCategory\u0018\b \u0001(\r\u0012\"\n\tnameInfos\u0018\t \u0003(\u000b2\u000f.na.pb.NameInfo\"Z\n\nRegionInfo\u0012\u0013\n\u000bproductName\u0018\u0001 \u0001(\t\u0012\u0012\n\nregionName\u0018\u0002 \u0001(\t\u0012\u0010\n\bregionId\u0018\u0003 \u0001(\r\u0012\u0011\n\tversionId\u0018\u0004 \u0001(\t\"à\u0001\n\u000eMapUpdateStart\u0012\u0011\n\ttimeStamp\u0018\u0001 \u0001(\t\u0012\u0019\n\u0011currentMapVersion\u0018\u0002 \u0001(\t\u0012%\n\nupdateType\u0018\u0003 \u0001(\u000e2\u0011.na.pb.UpdateType\u0012)\n\fupdateSource\u0018\u0004 \u0001(\u000e2\u0013.na.pb.UpdateSource\u0012\u0012\n\nmapVersion\u0018\u0005 \u0001(\t\u0012\u0013\n\u000bregionCount\u0018\u0006 \u0001(\r\u0012%\n\nregionInfo\u0018\u0007 \u0003(\u000b2\u0011.na.pb.RegionInfo\"É\u0001\n\fMapUpdateEnd\u0012\u0011\n\ttimeStamp\u0018\u0001 \u0001(\t\u0012%\n\nupdateType\u0018\u0002 \u0001(\u000e2\u0011.na.pb.UpdateType\u0012)\n\fupdateSource\u0018\u0003 \u0001(\u000e2\u0013.na.pb.UpdateSource\u0012)\n\fupdateResult\u0018\u0004 \u0001(\u000e2\u0013.na.pb.UpdateResult\u0012)\n\fupdateStatus\u0018\u0005 \u0001(\u000e2\u0013.na.pb.UpdateStatus*1\n\u0003OEM\u0012\u000f\n\u000bOEM_RESERVE\u0010\u0000\u0012\n\n\u0006PROTON\u0010\u0001\u0012\r\n\tGREATWALL\u0010\u0002*\u0090\u0001\n\u000eEventComponent\u0012\u001a\n\u0016EVENTCOMPONENT_RESERVE\u0010\u0000\u0012\n\n\u0006COMMON\u0010\u0001\u0012\f\n\bGUIDANCE\u0010\u0002\u0012\t\n\u0005ROUTE\u0010\u0003\u0012\n\n\u0006SEARCH\u0010\u0004\u0012\u0007\n\u0003MAP\u0010\u0005\u0012\f\n\bPOSITION\u0010\u0006\u0012\u000b\n\u0007TRAFFIC\u0010\u0007\u0012\r\n\tMAPUPDATE\u0010\b*\u0091\u0001\n\u0007EventID\u0012\u0013\n\u000fEVENTID_RESERVE\u0010\u0000\u0012\u0012\n\u000eGUIDANCE_START\u0010\u0001\u0012\u0010\n\fGUIDANCE_END\u0010\u0002\u0012\u000f\n\u000bNAVI_STARUP\u0010\u0003\u0012\u0012\n\u000eCALC_ROUTE_END\u0010\u0004\u0012\u0013\n\u000fMAPUPDATE_START\u0010\u0005\u0012\u0011\n\rMAPUPDATE_END\u0010\u0006*F\n\tEventType\u0012\u0015\n\u0011EVENTTYPE_RESERVE\u0010\u0000\u0012\b\n\u0004PAGE\u0010\u0001\u0012\t\n\u0005CLICK\u0010\u0002\u0012\r\n\tCUSTOMIZE\u0010\u0003*E\n\bRouteSrc\u0012\u0014\n\u0010ROUTESRC_RESERVE\u0010\u0000\u0012\u0010\n\fONLINE_ROUTE\u0010\u0001\u0012\u0011\n\rOFFLINE_ROUTE\u0010\u0002*J\n\bDestType\u0012\u0014\n\u0010DESTTYPE_RESERVE\u0010\u0000\u0012\u0007\n\u0003POI\u0010\u0001\u0012\u000b\n\u0007ADDRESS\u0010\u0002\u0012\u0012\n\u000eDESTTYPE_OTHER\u0010\u0003*A\n\u0006PoiSrc\u0012\u0012\n\u000ePOISRC_RESERVE\u0010\u0000\u0012\b\n\u0004HERE\u0010\u0001\u0012\u0007\n\u0003OSM\u0010\u0002\u0012\u0010\n\fPOISRC_OTHER\u0010\u0003*<\n\nUpdateType\u0012\u0016\n\u0012UPDATETYPE_RESERVE\u0010\u0000\u0012\u0006\n\u0002FU\u0010\u0001\u0012\u0006\n\u0002IU\u0010\u0002\u0012\u0006\n\u0002PU\u0010\u0003*U\n\fUpdateSource\u0012\u0018\n\u0014UPDATESOURCE_RESERVE\u0010\u0000\u0012\u0007\n\u0003USB\u0010\u0001\u0012\n\n\u0006ONLINE\u0010\u0002\u0012\u0016\n\u0012UPDATESOURCE_OTHER\u0010\u0003*M\n\fUpdateResult\u0012\u0018\n\u0014UPDATERESULT_RESERVE\u0010\u0000\u0012\u000b\n\u0007SUCCESS\u0010\u0001\u0012\n\n\u0006CANCEL\u0010\u0002\u0012\n\n\u0006FAILED\u0010\u0003*ó\u0004\n\fUpdateStatus\u0012\u0018\n\u0014UPDATESTATUS_RESERVE\u0010\u0000\u0012\u000f\n\u000bSTATUS_IDLE\u0010\u0001\u0012\u0016\n\u0012STATUS_DOWNLOADING\u0010\u0002\u0012\u0015\n\u0011STATUS_INSTALLING\u0010\u0003\u0012\u0013\n\u000fSTATUS_CANCELED\u0010\u0004\u0012\u0010\n\fSTATUS_ERROR\u0010\u0005\u0012\u0011\n\rSTATUS_BACKUP\u0010\u0006\u0012\u0015\n\u0011STATUS_NO_NETWORK\u0010\u0007\u0012%\n!STATUS_FAIL_NETWORK_COMMUNICATION\u0010\b\u0012\u0018\n\u0014STATUS_NOT_SUPPORTED\u0010\t\u0012#\n\u001fSTATUS_GETTING_AVAILABLEUPDATES\u0010\n\u0012\u0014\n\u0010STATUS_VACUUMING\u0010\u000b\u0012\u001c\n\u0018STATUS_DOWNLOAD_FINISHED\u0010\f\u0012\u001b\n\u0017STATUS_INSTALL_FINISHED\u0010\r\u0012\u001a\n\u0016STATUS_BACKUP_FINISHED\u0010\u000e\u0012\u001a\n\u0016STATUS_VACUUM_FINISHED\u0010\u000f\u0012\u001a\n\u0016STATUS_DB_INCONSISTENT\u0010\u0010\u0012\u0015\n\u0011STATUS_EXTRACTING\u0010\u0011\u0012\u001b\n\u0017STATUS_EXTRACT_FINISHED\u0010\u0012\u0012\u001c\n\u0018STATUS_UPDATES_AVAILABLE\u0010\u0013\u0012 \n\u001cSTATUS_DISK_SPACE_NOT_ENOUGH\u0010\u0014\u0012\u0019\n\u0015STATUS_NO_UPDATE_DATA\u0010\u0015\u0012\u001e\n\u001aSTATUS_INVALID_UPDATE_DATA\u0010\u0016b\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    private static final Descriptors.Descriptor internal_static_na_pb_CalculateRouteEnd_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_na_pb_CalculateRouteEnd_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_na_pb_DataBlob_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_na_pb_DataBlob_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_na_pb_DataCollection_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_na_pb_DataCollection_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_na_pb_GuidanceEnd_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_na_pb_GuidanceEnd_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_na_pb_GuidanceStart_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_na_pb_GuidanceStart_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_na_pb_Header_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_na_pb_Header_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_na_pb_MapUpdateEnd_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_na_pb_MapUpdateEnd_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_na_pb_MapUpdateStart_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_na_pb_MapUpdateStart_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_na_pb_NameInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_na_pb_NameInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_na_pb_NavigationAppStart_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_na_pb_NavigationAppStart_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_na_pb_RegionInfo_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_na_pb_RegionInfo_fieldAccessorTable;

    public interface CalculateRouteEndOrBuilder extends MessageOrBuilder {
        String getAddress();

        ByteString getAddressBytes();

        DestType getDestType();

        int getDestTypeValue();

        String getLat();

        ByteString getLatBytes();

        String getLon();

        ByteString getLonBytes();

        NameInfo getNameInfos(int i);

        int getNameInfosCount();

        List<NameInfo> getNameInfosList();

        NameInfoOrBuilder getNameInfosOrBuilder(int i);

        List<? extends NameInfoOrBuilder> getNameInfosOrBuilderList();

        int getPoiCategory();

        PoiSrc getPoiSrc();

        int getPoiSrcValue();

        RouteSrc getRouteSource();

        int getRouteSourceValue();

        String getTimeStamp();

        ByteString getTimeStampBytes();
    }

    public interface DataBlobOrBuilder extends MessageOrBuilder {
        String getDataBlob();

        ByteString getDataBlobBytes();

        EventComponent getEventComponent();

        int getEventComponentValue();

        EventID getEventId();

        int getEventIdValue();

        EventType getEventType();

        int getEventTypeValue();
    }

    public interface DataCollectionOrBuilder extends MessageOrBuilder {
        DataBlob getDataBlob(int i);

        int getDataBlobCount();

        List<DataBlob> getDataBlobList();

        DataBlobOrBuilder getDataBlobOrBuilder(int i);

        List<? extends DataBlobOrBuilder> getDataBlobOrBuilderList();

        Header getHeader();

        HeaderOrBuilder getHeaderOrBuilder();

        boolean hasHeader();
    }

    public interface GuidanceEndOrBuilder extends MessageOrBuilder {
        String getEta();

        ByteString getEtaBytes();

        String getTimeStamp();

        ByteString getTimeStampBytes();
    }

    public interface GuidanceStartOrBuilder extends MessageOrBuilder {
        String getEta();

        ByteString getEtaBytes();

        String getTimeStamp();

        ByteString getTimeStampBytes();
    }

    public interface HeaderOrBuilder extends MessageOrBuilder {
        String getAppVersion();

        ByteString getAppVersionBytes();

        String getCarMode();

        ByteString getCarModeBytes();

        String getCollectTime();

        ByteString getCollectTimeBytes();

        String getCountry();

        ByteString getCountryBytes();

        String getDbVersion();

        ByteString getDbVersionBytes();

        String getDeviceId();

        ByteString getDeviceIdBytes();

        String getLanguage();

        ByteString getLanguageBytes();

        OEM getOem();

        int getOemValue();

        String getUserId();

        ByteString getUserIdBytes();
    }

    public interface MapUpdateEndOrBuilder extends MessageOrBuilder {
        String getTimeStamp();

        ByteString getTimeStampBytes();

        UpdateResult getUpdateResult();

        int getUpdateResultValue();

        UpdateSource getUpdateSource();

        int getUpdateSourceValue();

        UpdateStatus getUpdateStatus();

        int getUpdateStatusValue();

        UpdateType getUpdateType();

        int getUpdateTypeValue();
    }

    public interface MapUpdateStartOrBuilder extends MessageOrBuilder {
        String getCurrentMapVersion();

        ByteString getCurrentMapVersionBytes();

        String getMapVersion();

        ByteString getMapVersionBytes();

        int getRegionCount();

        RegionInfo getRegionInfo(int i);

        int getRegionInfoCount();

        List<RegionInfo> getRegionInfoList();

        RegionInfoOrBuilder getRegionInfoOrBuilder(int i);

        List<? extends RegionInfoOrBuilder> getRegionInfoOrBuilderList();

        String getTimeStamp();

        ByteString getTimeStampBytes();

        UpdateSource getUpdateSource();

        int getUpdateSourceValue();

        UpdateType getUpdateType();

        int getUpdateTypeValue();
    }

    public interface NameInfoOrBuilder extends MessageOrBuilder {
        String getLanguageCode();

        ByteString getLanguageCodeBytes();

        String getName();

        ByteString getNameBytes();
    }

    public interface NavigationAppStartOrBuilder extends MessageOrBuilder {
        String getTimeStamp();

        ByteString getTimeStampBytes();
    }

    public interface RegionInfoOrBuilder extends MessageOrBuilder {
        String getProductName();

        ByteString getProductNameBytes();

        int getRegionId();

        String getRegionName();

        ByteString getRegionNameBytes();

        String getVersionId();

        ByteString getVersionIdBytes();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private DataCollectionOuterClass() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public enum OEM implements ProtocolMessageEnum {
        OEM_RESERVE(0),
        PROTON(1),
        GREATWALL(2),
        UNRECOGNIZED(-1);

        public static final int GREATWALL_VALUE = 2;
        public static final int OEM_RESERVE_VALUE = 0;
        public static final int PROTON_VALUE = 1;
        private final int value;
        private static final Internal.EnumLiteMap<OEM> internalValueMap = new Internal.EnumLiteMap<OEM>() { // from class: na.pb.DataCollectionOuterClass.OEM.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public OEM findValueByNumber(int i) {
                return OEM.forNumber(i);
            }
        };
        private static final OEM[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static OEM valueOf(int i) {
            return forNumber(i);
        }

        public static OEM forNumber(int i) {
            if (i == 0) {
                return OEM_RESERVE;
            }
            if (i == 1) {
                return PROTON;
            }
            if (i != 2) {
                return null;
            }
            return GREATWALL;
        }

        public static Internal.EnumLiteMap<OEM> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return getDescriptor().getValues().get(ordinal());
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return DataCollectionOuterClass.getDescriptor().getEnumTypes().get(0);
        }

        public static OEM valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        OEM(int i) {
            this.value = i;
        }
    }

    public enum EventComponent implements ProtocolMessageEnum {
        EVENTCOMPONENT_RESERVE(0),
        COMMON(1),
        GUIDANCE(2),
        ROUTE(3),
        SEARCH(4),
        MAP(5),
        POSITION(6),
        TRAFFIC(7),
        MAPUPDATE(8),
        UNRECOGNIZED(-1);

        public static final int COMMON_VALUE = 1;
        public static final int EVENTCOMPONENT_RESERVE_VALUE = 0;
        public static final int GUIDANCE_VALUE = 2;
        public static final int MAPUPDATE_VALUE = 8;
        public static final int MAP_VALUE = 5;
        public static final int POSITION_VALUE = 6;
        public static final int ROUTE_VALUE = 3;
        public static final int SEARCH_VALUE = 4;
        public static final int TRAFFIC_VALUE = 7;
        private final int value;
        private static final Internal.EnumLiteMap<EventComponent> internalValueMap = new Internal.EnumLiteMap<EventComponent>() { // from class: na.pb.DataCollectionOuterClass.EventComponent.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public EventComponent findValueByNumber(int i) {
                return EventComponent.forNumber(i);
            }
        };
        private static final EventComponent[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static EventComponent valueOf(int i) {
            return forNumber(i);
        }

        public static EventComponent forNumber(int i) {
            switch (i) {
                case 0:
                    return EVENTCOMPONENT_RESERVE;
                case 1:
                    return COMMON;
                case 2:
                    return GUIDANCE;
                case 3:
                    return ROUTE;
                case 4:
                    return SEARCH;
                case 5:
                    return MAP;
                case 6:
                    return POSITION;
                case 7:
                    return TRAFFIC;
                case 8:
                    return MAPUPDATE;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<EventComponent> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return getDescriptor().getValues().get(ordinal());
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return DataCollectionOuterClass.getDescriptor().getEnumTypes().get(1);
        }

        public static EventComponent valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        EventComponent(int i) {
            this.value = i;
        }
    }

    public enum EventID implements ProtocolMessageEnum {
        EVENTID_RESERVE(0),
        GUIDANCE_START(1),
        GUIDANCE_END(2),
        NAVI_STARUP(3),
        CALC_ROUTE_END(4),
        MAPUPDATE_START(5),
        MAPUPDATE_END(6),
        UNRECOGNIZED(-1);

        public static final int CALC_ROUTE_END_VALUE = 4;
        public static final int EVENTID_RESERVE_VALUE = 0;
        public static final int GUIDANCE_END_VALUE = 2;
        public static final int GUIDANCE_START_VALUE = 1;
        public static final int MAPUPDATE_END_VALUE = 6;
        public static final int MAPUPDATE_START_VALUE = 5;
        public static final int NAVI_STARUP_VALUE = 3;
        private final int value;
        private static final Internal.EnumLiteMap<EventID> internalValueMap = new Internal.EnumLiteMap<EventID>() { // from class: na.pb.DataCollectionOuterClass.EventID.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public EventID findValueByNumber(int i) {
                return EventID.forNumber(i);
            }
        };
        private static final EventID[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static EventID valueOf(int i) {
            return forNumber(i);
        }

        public static EventID forNumber(int i) {
            switch (i) {
                case 0:
                    return EVENTID_RESERVE;
                case 1:
                    return GUIDANCE_START;
                case 2:
                    return GUIDANCE_END;
                case 3:
                    return NAVI_STARUP;
                case 4:
                    return CALC_ROUTE_END;
                case 5:
                    return MAPUPDATE_START;
                case 6:
                    return MAPUPDATE_END;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<EventID> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return getDescriptor().getValues().get(ordinal());
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return DataCollectionOuterClass.getDescriptor().getEnumTypes().get(2);
        }

        public static EventID valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        EventID(int i) {
            this.value = i;
        }
    }

    public enum EventType implements ProtocolMessageEnum {
        EVENTTYPE_RESERVE(0),
        PAGE(1),
        CLICK(2),
        CUSTOMIZE(3),
        UNRECOGNIZED(-1);

        public static final int CLICK_VALUE = 2;
        public static final int CUSTOMIZE_VALUE = 3;
        public static final int EVENTTYPE_RESERVE_VALUE = 0;
        public static final int PAGE_VALUE = 1;
        private final int value;
        private static final Internal.EnumLiteMap<EventType> internalValueMap = new Internal.EnumLiteMap<EventType>() { // from class: na.pb.DataCollectionOuterClass.EventType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public EventType findValueByNumber(int i) {
                return EventType.forNumber(i);
            }
        };
        private static final EventType[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static EventType valueOf(int i) {
            return forNumber(i);
        }

        public static EventType forNumber(int i) {
            if (i == 0) {
                return EVENTTYPE_RESERVE;
            }
            if (i == 1) {
                return PAGE;
            }
            if (i == 2) {
                return CLICK;
            }
            if (i != 3) {
                return null;
            }
            return CUSTOMIZE;
        }

        public static Internal.EnumLiteMap<EventType> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return getDescriptor().getValues().get(ordinal());
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return DataCollectionOuterClass.getDescriptor().getEnumTypes().get(3);
        }

        public static EventType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        EventType(int i) {
            this.value = i;
        }
    }

    public enum RouteSrc implements ProtocolMessageEnum {
        ROUTESRC_RESERVE(0),
        ONLINE_ROUTE(1),
        OFFLINE_ROUTE(2),
        UNRECOGNIZED(-1);

        public static final int OFFLINE_ROUTE_VALUE = 2;
        public static final int ONLINE_ROUTE_VALUE = 1;
        public static final int ROUTESRC_RESERVE_VALUE = 0;
        private final int value;
        private static final Internal.EnumLiteMap<RouteSrc> internalValueMap = new Internal.EnumLiteMap<RouteSrc>() { // from class: na.pb.DataCollectionOuterClass.RouteSrc.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public RouteSrc findValueByNumber(int i) {
                return RouteSrc.forNumber(i);
            }
        };
        private static final RouteSrc[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static RouteSrc valueOf(int i) {
            return forNumber(i);
        }

        public static RouteSrc forNumber(int i) {
            if (i == 0) {
                return ROUTESRC_RESERVE;
            }
            if (i == 1) {
                return ONLINE_ROUTE;
            }
            if (i != 2) {
                return null;
            }
            return OFFLINE_ROUTE;
        }

        public static Internal.EnumLiteMap<RouteSrc> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return getDescriptor().getValues().get(ordinal());
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return DataCollectionOuterClass.getDescriptor().getEnumTypes().get(4);
        }

        public static RouteSrc valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        RouteSrc(int i) {
            this.value = i;
        }
    }

    public enum DestType implements ProtocolMessageEnum {
        DESTTYPE_RESERVE(0),
        POI(1),
        ADDRESS(2),
        DESTTYPE_OTHER(3),
        UNRECOGNIZED(-1);

        public static final int ADDRESS_VALUE = 2;
        public static final int DESTTYPE_OTHER_VALUE = 3;
        public static final int DESTTYPE_RESERVE_VALUE = 0;
        public static final int POI_VALUE = 1;
        private final int value;
        private static final Internal.EnumLiteMap<DestType> internalValueMap = new Internal.EnumLiteMap<DestType>() { // from class: na.pb.DataCollectionOuterClass.DestType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public DestType findValueByNumber(int i) {
                return DestType.forNumber(i);
            }
        };
        private static final DestType[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static DestType valueOf(int i) {
            return forNumber(i);
        }

        public static DestType forNumber(int i) {
            if (i == 0) {
                return DESTTYPE_RESERVE;
            }
            if (i == 1) {
                return POI;
            }
            if (i == 2) {
                return ADDRESS;
            }
            if (i != 3) {
                return null;
            }
            return DESTTYPE_OTHER;
        }

        public static Internal.EnumLiteMap<DestType> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return getDescriptor().getValues().get(ordinal());
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return DataCollectionOuterClass.getDescriptor().getEnumTypes().get(5);
        }

        public static DestType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        DestType(int i) {
            this.value = i;
        }
    }

    public enum PoiSrc implements ProtocolMessageEnum {
        POISRC_RESERVE(0),
        HERE(1),
        OSM(2),
        POISRC_OTHER(3),
        UNRECOGNIZED(-1);

        public static final int HERE_VALUE = 1;
        public static final int OSM_VALUE = 2;
        public static final int POISRC_OTHER_VALUE = 3;
        public static final int POISRC_RESERVE_VALUE = 0;
        private final int value;
        private static final Internal.EnumLiteMap<PoiSrc> internalValueMap = new Internal.EnumLiteMap<PoiSrc>() { // from class: na.pb.DataCollectionOuterClass.PoiSrc.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public PoiSrc findValueByNumber(int i) {
                return PoiSrc.forNumber(i);
            }
        };
        private static final PoiSrc[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static PoiSrc valueOf(int i) {
            return forNumber(i);
        }

        public static PoiSrc forNumber(int i) {
            if (i == 0) {
                return POISRC_RESERVE;
            }
            if (i == 1) {
                return HERE;
            }
            if (i == 2) {
                return OSM;
            }
            if (i != 3) {
                return null;
            }
            return POISRC_OTHER;
        }

        public static Internal.EnumLiteMap<PoiSrc> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return getDescriptor().getValues().get(ordinal());
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return DataCollectionOuterClass.getDescriptor().getEnumTypes().get(6);
        }

        public static PoiSrc valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        PoiSrc(int i) {
            this.value = i;
        }
    }

    public enum UpdateType implements ProtocolMessageEnum {
        UPDATETYPE_RESERVE(0),
        FU(1),
        IU(2),
        PU(3),
        UNRECOGNIZED(-1);

        public static final int FU_VALUE = 1;
        public static final int IU_VALUE = 2;
        public static final int PU_VALUE = 3;
        public static final int UPDATETYPE_RESERVE_VALUE = 0;
        private final int value;
        private static final Internal.EnumLiteMap<UpdateType> internalValueMap = new Internal.EnumLiteMap<UpdateType>() { // from class: na.pb.DataCollectionOuterClass.UpdateType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public UpdateType findValueByNumber(int i) {
                return UpdateType.forNumber(i);
            }
        };
        private static final UpdateType[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static UpdateType valueOf(int i) {
            return forNumber(i);
        }

        public static UpdateType forNumber(int i) {
            if (i == 0) {
                return UPDATETYPE_RESERVE;
            }
            if (i == 1) {
                return FU;
            }
            if (i == 2) {
                return IU;
            }
            if (i != 3) {
                return null;
            }
            return PU;
        }

        public static Internal.EnumLiteMap<UpdateType> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return getDescriptor().getValues().get(ordinal());
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return DataCollectionOuterClass.getDescriptor().getEnumTypes().get(7);
        }

        public static UpdateType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        UpdateType(int i) {
            this.value = i;
        }
    }

    public enum UpdateSource implements ProtocolMessageEnum {
        UPDATESOURCE_RESERVE(0),
        USB(1),
        ONLINE(2),
        UPDATESOURCE_OTHER(3),
        UNRECOGNIZED(-1);

        public static final int ONLINE_VALUE = 2;
        public static final int UPDATESOURCE_OTHER_VALUE = 3;
        public static final int UPDATESOURCE_RESERVE_VALUE = 0;
        public static final int USB_VALUE = 1;
        private final int value;
        private static final Internal.EnumLiteMap<UpdateSource> internalValueMap = new Internal.EnumLiteMap<UpdateSource>() { // from class: na.pb.DataCollectionOuterClass.UpdateSource.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public UpdateSource findValueByNumber(int i) {
                return UpdateSource.forNumber(i);
            }
        };
        private static final UpdateSource[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static UpdateSource valueOf(int i) {
            return forNumber(i);
        }

        public static UpdateSource forNumber(int i) {
            if (i == 0) {
                return UPDATESOURCE_RESERVE;
            }
            if (i == 1) {
                return USB;
            }
            if (i == 2) {
                return ONLINE;
            }
            if (i != 3) {
                return null;
            }
            return UPDATESOURCE_OTHER;
        }

        public static Internal.EnumLiteMap<UpdateSource> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return getDescriptor().getValues().get(ordinal());
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return DataCollectionOuterClass.getDescriptor().getEnumTypes().get(8);
        }

        public static UpdateSource valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        UpdateSource(int i) {
            this.value = i;
        }
    }

    public enum UpdateResult implements ProtocolMessageEnum {
        UPDATERESULT_RESERVE(0),
        SUCCESS(1),
        CANCEL(2),
        FAILED(3),
        UNRECOGNIZED(-1);

        public static final int CANCEL_VALUE = 2;
        public static final int FAILED_VALUE = 3;
        public static final int SUCCESS_VALUE = 1;
        public static final int UPDATERESULT_RESERVE_VALUE = 0;
        private final int value;
        private static final Internal.EnumLiteMap<UpdateResult> internalValueMap = new Internal.EnumLiteMap<UpdateResult>() { // from class: na.pb.DataCollectionOuterClass.UpdateResult.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public UpdateResult findValueByNumber(int i) {
                return UpdateResult.forNumber(i);
            }
        };
        private static final UpdateResult[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static UpdateResult valueOf(int i) {
            return forNumber(i);
        }

        public static UpdateResult forNumber(int i) {
            if (i == 0) {
                return UPDATERESULT_RESERVE;
            }
            if (i == 1) {
                return SUCCESS;
            }
            if (i == 2) {
                return CANCEL;
            }
            if (i != 3) {
                return null;
            }
            return FAILED;
        }

        public static Internal.EnumLiteMap<UpdateResult> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return getDescriptor().getValues().get(ordinal());
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return DataCollectionOuterClass.getDescriptor().getEnumTypes().get(9);
        }

        public static UpdateResult valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        UpdateResult(int i) {
            this.value = i;
        }
    }

    public enum UpdateStatus implements ProtocolMessageEnum {
        UPDATESTATUS_RESERVE(0),
        STATUS_IDLE(1),
        STATUS_DOWNLOADING(2),
        STATUS_INSTALLING(3),
        STATUS_CANCELED(4),
        STATUS_ERROR(5),
        STATUS_BACKUP(6),
        STATUS_NO_NETWORK(7),
        STATUS_FAIL_NETWORK_COMMUNICATION(8),
        STATUS_NOT_SUPPORTED(9),
        STATUS_GETTING_AVAILABLEUPDATES(10),
        STATUS_VACUUMING(11),
        STATUS_DOWNLOAD_FINISHED(12),
        STATUS_INSTALL_FINISHED(13),
        STATUS_BACKUP_FINISHED(14),
        STATUS_VACUUM_FINISHED(15),
        STATUS_DB_INCONSISTENT(16),
        STATUS_EXTRACTING(17),
        STATUS_EXTRACT_FINISHED(18),
        STATUS_UPDATES_AVAILABLE(19),
        STATUS_DISK_SPACE_NOT_ENOUGH(20),
        STATUS_NO_UPDATE_DATA(21),
        STATUS_INVALID_UPDATE_DATA(22),
        UNRECOGNIZED(-1);

        public static final int STATUS_BACKUP_FINISHED_VALUE = 14;
        public static final int STATUS_BACKUP_VALUE = 6;
        public static final int STATUS_CANCELED_VALUE = 4;
        public static final int STATUS_DB_INCONSISTENT_VALUE = 16;
        public static final int STATUS_DISK_SPACE_NOT_ENOUGH_VALUE = 20;
        public static final int STATUS_DOWNLOADING_VALUE = 2;
        public static final int STATUS_DOWNLOAD_FINISHED_VALUE = 12;
        public static final int STATUS_ERROR_VALUE = 5;
        public static final int STATUS_EXTRACTING_VALUE = 17;
        public static final int STATUS_EXTRACT_FINISHED_VALUE = 18;
        public static final int STATUS_FAIL_NETWORK_COMMUNICATION_VALUE = 8;
        public static final int STATUS_GETTING_AVAILABLEUPDATES_VALUE = 10;
        public static final int STATUS_IDLE_VALUE = 1;
        public static final int STATUS_INSTALLING_VALUE = 3;
        public static final int STATUS_INSTALL_FINISHED_VALUE = 13;
        public static final int STATUS_INVALID_UPDATE_DATA_VALUE = 22;
        public static final int STATUS_NOT_SUPPORTED_VALUE = 9;
        public static final int STATUS_NO_NETWORK_VALUE = 7;
        public static final int STATUS_NO_UPDATE_DATA_VALUE = 21;
        public static final int STATUS_UPDATES_AVAILABLE_VALUE = 19;
        public static final int STATUS_VACUUMING_VALUE = 11;
        public static final int STATUS_VACUUM_FINISHED_VALUE = 15;
        public static final int UPDATESTATUS_RESERVE_VALUE = 0;
        private final int value;
        private static final Internal.EnumLiteMap<UpdateStatus> internalValueMap = new Internal.EnumLiteMap<UpdateStatus>() { // from class: na.pb.DataCollectionOuterClass.UpdateStatus.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public UpdateStatus findValueByNumber(int i) {
                return UpdateStatus.forNumber(i);
            }
        };
        private static final UpdateStatus[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static UpdateStatus valueOf(int i) {
            return forNumber(i);
        }

        public static UpdateStatus forNumber(int i) {
            switch (i) {
                case 0:
                    return UPDATESTATUS_RESERVE;
                case 1:
                    return STATUS_IDLE;
                case 2:
                    return STATUS_DOWNLOADING;
                case 3:
                    return STATUS_INSTALLING;
                case 4:
                    return STATUS_CANCELED;
                case 5:
                    return STATUS_ERROR;
                case 6:
                    return STATUS_BACKUP;
                case 7:
                    return STATUS_NO_NETWORK;
                case 8:
                    return STATUS_FAIL_NETWORK_COMMUNICATION;
                case 9:
                    return STATUS_NOT_SUPPORTED;
                case 10:
                    return STATUS_GETTING_AVAILABLEUPDATES;
                case 11:
                    return STATUS_VACUUMING;
                case 12:
                    return STATUS_DOWNLOAD_FINISHED;
                case 13:
                    return STATUS_INSTALL_FINISHED;
                case 14:
                    return STATUS_BACKUP_FINISHED;
                case 15:
                    return STATUS_VACUUM_FINISHED;
                case 16:
                    return STATUS_DB_INCONSISTENT;
                case 17:
                    return STATUS_EXTRACTING;
                case 18:
                    return STATUS_EXTRACT_FINISHED;
                case 19:
                    return STATUS_UPDATES_AVAILABLE;
                case 20:
                    return STATUS_DISK_SPACE_NOT_ENOUGH;
                case 21:
                    return STATUS_NO_UPDATE_DATA;
                case 22:
                    return STATUS_INVALID_UPDATE_DATA;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<UpdateStatus> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return getDescriptor().getValues().get(ordinal());
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return DataCollectionOuterClass.getDescriptor().getEnumTypes().get(10);
        }

        public static UpdateStatus valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        UpdateStatus(int i) {
            this.value = i;
        }
    }

    public static final class DataCollection extends GeneratedMessageV3 implements DataCollectionOrBuilder {
        public static final int DATABLOB_FIELD_NUMBER = 2;
        public static final int HEADER_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private List<DataBlob> dataBlob_;
        private Header header_;
        private byte memoizedIsInitialized;
        private static final DataCollection DEFAULT_INSTANCE = new DataCollection();
        private static final Parser<DataCollection> PARSER = new AbstractParser<DataCollection>() { // from class: na.pb.DataCollectionOuterClass.DataCollection.1
            @Override // com.google.protobuf.Parser
            public DataCollection parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new DataCollection(codedInputStream, extensionRegistryLite);
            }
        };

        private DataCollection(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private DataCollection() {
            this.memoizedIsInitialized = (byte) -1;
            this.dataBlob_ = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new DataCollection();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private DataCollection(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 10) {
                                    Header header = this.header_;
                                    Header.Builder builder = header != null ? header.toBuilder() : null;
                                    Header header2 = (Header) codedInputStream.readMessage(Header.parser(), extensionRegistryLite);
                                    this.header_ = header2;
                                    if (builder != null) {
                                        builder.mergeFrom(header2);
                                        this.header_ = builder.buildPartial();
                                    }
                                } else if (tag == 18) {
                                    if (!(z2 & true)) {
                                        this.dataBlob_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.dataBlob_.add((DataBlob) codedInputStream.readMessage(DataBlob.parser(), extensionRegistryLite));
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            }
                            z = true;
                        } catch (UninitializedMessageException e) {
                            throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e3) {
                        throw e3.setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.dataBlob_ = Collections.unmodifiableList(this.dataBlob_);
                    }
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DataCollectionOuterClass.internal_static_na_pb_DataCollection_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DataCollectionOuterClass.internal_static_na_pb_DataCollection_fieldAccessorTable.ensureFieldAccessorsInitialized(DataCollection.class, Builder.class);
        }

        @Override // na.pb.DataCollectionOuterClass.DataCollectionOrBuilder
        public boolean hasHeader() {
            return this.header_ != null;
        }

        @Override // na.pb.DataCollectionOuterClass.DataCollectionOrBuilder
        public Header getHeader() {
            Header header = this.header_;
            return header == null ? Header.getDefaultInstance() : header;
        }

        @Override // na.pb.DataCollectionOuterClass.DataCollectionOrBuilder
        public HeaderOrBuilder getHeaderOrBuilder() {
            return getHeader();
        }

        @Override // na.pb.DataCollectionOuterClass.DataCollectionOrBuilder
        public List<DataBlob> getDataBlobList() {
            return this.dataBlob_;
        }

        @Override // na.pb.DataCollectionOuterClass.DataCollectionOrBuilder
        public List<? extends DataBlobOrBuilder> getDataBlobOrBuilderList() {
            return this.dataBlob_;
        }

        @Override // na.pb.DataCollectionOuterClass.DataCollectionOrBuilder
        public int getDataBlobCount() {
            return this.dataBlob_.size();
        }

        @Override // na.pb.DataCollectionOuterClass.DataCollectionOrBuilder
        public DataBlob getDataBlob(int i) {
            return this.dataBlob_.get(i);
        }

        @Override // na.pb.DataCollectionOuterClass.DataCollectionOrBuilder
        public DataBlobOrBuilder getDataBlobOrBuilder(int i) {
            return this.dataBlob_.get(i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.header_ != null) {
                codedOutputStream.writeMessage(1, getHeader());
            }
            for (int i = 0; i < this.dataBlob_.size(); i++) {
                codedOutputStream.writeMessage(2, this.dataBlob_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeMessageSize = this.header_ != null ? CodedOutputStream.computeMessageSize(1, getHeader()) + 0 : 0;
            for (int i2 = 0; i2 < this.dataBlob_.size(); i2++) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(2, this.dataBlob_.get(i2));
            }
            int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof DataCollection)) {
                return super.equals(obj);
            }
            DataCollection dataCollection = (DataCollection) obj;
            if (hasHeader() != dataCollection.hasHeader()) {
                return false;
            }
            return (!hasHeader() || getHeader().equals(dataCollection.getHeader())) && getDataBlobList().equals(dataCollection.getDataBlobList()) && this.unknownFields.equals(dataCollection.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasHeader()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getHeader().hashCode();
            }
            if (getDataBlobCount() > 0) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getDataBlobList().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        public static DataCollection parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static DataCollection parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static DataCollection parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static DataCollection parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static DataCollection parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static DataCollection parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static DataCollection parseFrom(InputStream inputStream) throws IOException {
            return (DataCollection) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static DataCollection parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DataCollection) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static DataCollection parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (DataCollection) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static DataCollection parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DataCollection) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static DataCollection parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (DataCollection) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static DataCollection parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DataCollection) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(DataCollection dataCollection) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(dataCollection);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DataCollectionOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<DataBlob, DataBlob.Builder, DataBlobOrBuilder> dataBlobBuilder_;
            private List<DataBlob> dataBlob_;
            private SingleFieldBuilderV3<Header, Header.Builder, HeaderOrBuilder> headerBuilder_;
            private Header header_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DataCollectionOuterClass.internal_static_na_pb_DataCollection_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DataCollectionOuterClass.internal_static_na_pb_DataCollection_fieldAccessorTable.ensureFieldAccessorsInitialized(DataCollection.class, Builder.class);
            }

            private Builder() {
                this.dataBlob_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.dataBlob_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (DataCollection.alwaysUseFieldBuilders) {
                    getDataBlobFieldBuilder();
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                if (this.headerBuilder_ == null) {
                    this.header_ = null;
                } else {
                    this.header_ = null;
                    this.headerBuilder_ = null;
                }
                RepeatedFieldBuilderV3<DataBlob, DataBlob.Builder, DataBlobOrBuilder> repeatedFieldBuilderV3 = this.dataBlobBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.dataBlob_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DataCollectionOuterClass.internal_static_na_pb_DataCollection_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public DataCollection getDefaultInstanceForType() {
                return DataCollection.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public DataCollection build() {
                DataCollection dataCollectionBuildPartial = buildPartial();
                if (dataCollectionBuildPartial.isInitialized()) {
                    return dataCollectionBuildPartial;
                }
                throw newUninitializedMessageException((Message) dataCollectionBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public DataCollection buildPartial() {
                DataCollection dataCollection = new DataCollection(this);
                SingleFieldBuilderV3<Header, Header.Builder, HeaderOrBuilder> singleFieldBuilderV3 = this.headerBuilder_;
                if (singleFieldBuilderV3 == null) {
                    dataCollection.header_ = this.header_;
                } else {
                    dataCollection.header_ = (Header) singleFieldBuilderV3.build();
                }
                RepeatedFieldBuilderV3<DataBlob, DataBlob.Builder, DataBlobOrBuilder> repeatedFieldBuilderV3 = this.dataBlobBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    dataCollection.dataBlob_ = repeatedFieldBuilderV3.build();
                } else {
                    if ((this.bitField0_ & 1) != 0) {
                        this.dataBlob_ = Collections.unmodifiableList(this.dataBlob_);
                        this.bitField0_ &= -2;
                    }
                    dataCollection.dataBlob_ = this.dataBlob_;
                }
                onBuilt();
                return dataCollection;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo93clone() {
                return (Builder) super.mo93clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof DataCollection) {
                    return mergeFrom((DataCollection) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(DataCollection dataCollection) {
                if (dataCollection == DataCollection.getDefaultInstance()) {
                    return this;
                }
                if (dataCollection.hasHeader()) {
                    mergeHeader(dataCollection.getHeader());
                }
                if (this.dataBlobBuilder_ == null) {
                    if (!dataCollection.dataBlob_.isEmpty()) {
                        if (this.dataBlob_.isEmpty()) {
                            this.dataBlob_ = dataCollection.dataBlob_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureDataBlobIsMutable();
                            this.dataBlob_.addAll(dataCollection.dataBlob_);
                        }
                        onChanged();
                    }
                } else if (!dataCollection.dataBlob_.isEmpty()) {
                    if (!this.dataBlobBuilder_.isEmpty()) {
                        this.dataBlobBuilder_.addAllMessages(dataCollection.dataBlob_);
                    } else {
                        this.dataBlobBuilder_.dispose();
                        this.dataBlobBuilder_ = null;
                        this.dataBlob_ = dataCollection.dataBlob_;
                        this.bitField0_ &= -2;
                        this.dataBlobBuilder_ = DataCollection.alwaysUseFieldBuilders ? getDataBlobFieldBuilder() : null;
                    }
                }
                mergeUnknownFields(dataCollection.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public na.pb.DataCollectionOuterClass.DataCollection.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = na.pb.DataCollectionOuterClass.DataCollection.access$1000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    na.pb.DataCollectionOuterClass$DataCollection r3 = (na.pb.DataCollectionOuterClass.DataCollection) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    na.pb.DataCollectionOuterClass$DataCollection r4 = (na.pb.DataCollectionOuterClass.DataCollection) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: na.pb.DataCollectionOuterClass.DataCollection.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):na.pb.DataCollectionOuterClass$DataCollection$Builder");
            }

            @Override // na.pb.DataCollectionOuterClass.DataCollectionOrBuilder
            public boolean hasHeader() {
                return (this.headerBuilder_ == null && this.header_ == null) ? false : true;
            }

            @Override // na.pb.DataCollectionOuterClass.DataCollectionOrBuilder
            public Header getHeader() {
                SingleFieldBuilderV3<Header, Header.Builder, HeaderOrBuilder> singleFieldBuilderV3 = this.headerBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Header header = this.header_;
                    return header == null ? Header.getDefaultInstance() : header;
                }
                return (Header) singleFieldBuilderV3.getMessage();
            }

            public Builder setHeader(Header header) {
                SingleFieldBuilderV3<Header, Header.Builder, HeaderOrBuilder> singleFieldBuilderV3 = this.headerBuilder_;
                if (singleFieldBuilderV3 == null) {
                    header.getClass();
                    this.header_ = header;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(header);
                }
                return this;
            }

            public Builder setHeader(Header.Builder builder) {
                SingleFieldBuilderV3<Header, Header.Builder, HeaderOrBuilder> singleFieldBuilderV3 = this.headerBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.header_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeHeader(Header header) {
                SingleFieldBuilderV3<Header, Header.Builder, HeaderOrBuilder> singleFieldBuilderV3 = this.headerBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Header header2 = this.header_;
                    if (header2 != null) {
                        this.header_ = Header.newBuilder(header2).mergeFrom(header).buildPartial();
                    } else {
                        this.header_ = header;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(header);
                }
                return this;
            }

            public Builder clearHeader() {
                if (this.headerBuilder_ == null) {
                    this.header_ = null;
                    onChanged();
                } else {
                    this.header_ = null;
                    this.headerBuilder_ = null;
                }
                return this;
            }

            public Header.Builder getHeaderBuilder() {
                onChanged();
                return (Header.Builder) getHeaderFieldBuilder().getBuilder();
            }

            @Override // na.pb.DataCollectionOuterClass.DataCollectionOrBuilder
            public HeaderOrBuilder getHeaderOrBuilder() {
                SingleFieldBuilderV3<Header, Header.Builder, HeaderOrBuilder> singleFieldBuilderV3 = this.headerBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (HeaderOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                Header header = this.header_;
                return header == null ? Header.getDefaultInstance() : header;
            }

            private SingleFieldBuilderV3<Header, Header.Builder, HeaderOrBuilder> getHeaderFieldBuilder() {
                if (this.headerBuilder_ == null) {
                    this.headerBuilder_ = new SingleFieldBuilderV3<>(getHeader(), getParentForChildren(), isClean());
                    this.header_ = null;
                }
                return this.headerBuilder_;
            }

            private void ensureDataBlobIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.dataBlob_ = new ArrayList(this.dataBlob_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // na.pb.DataCollectionOuterClass.DataCollectionOrBuilder
            public List<DataBlob> getDataBlobList() {
                RepeatedFieldBuilderV3<DataBlob, DataBlob.Builder, DataBlobOrBuilder> repeatedFieldBuilderV3 = this.dataBlobBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.dataBlob_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // na.pb.DataCollectionOuterClass.DataCollectionOrBuilder
            public int getDataBlobCount() {
                RepeatedFieldBuilderV3<DataBlob, DataBlob.Builder, DataBlobOrBuilder> repeatedFieldBuilderV3 = this.dataBlobBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.dataBlob_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // na.pb.DataCollectionOuterClass.DataCollectionOrBuilder
            public DataBlob getDataBlob(int i) {
                RepeatedFieldBuilderV3<DataBlob, DataBlob.Builder, DataBlobOrBuilder> repeatedFieldBuilderV3 = this.dataBlobBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.dataBlob_.get(i);
                }
                return (DataBlob) repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setDataBlob(int i, DataBlob dataBlob) {
                RepeatedFieldBuilderV3<DataBlob, DataBlob.Builder, DataBlobOrBuilder> repeatedFieldBuilderV3 = this.dataBlobBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    dataBlob.getClass();
                    ensureDataBlobIsMutable();
                    this.dataBlob_.set(i, dataBlob);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, dataBlob);
                }
                return this;
            }

            public Builder setDataBlob(int i, DataBlob.Builder builder) {
                RepeatedFieldBuilderV3<DataBlob, DataBlob.Builder, DataBlobOrBuilder> repeatedFieldBuilderV3 = this.dataBlobBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDataBlobIsMutable();
                    this.dataBlob_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addDataBlob(DataBlob dataBlob) {
                RepeatedFieldBuilderV3<DataBlob, DataBlob.Builder, DataBlobOrBuilder> repeatedFieldBuilderV3 = this.dataBlobBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    dataBlob.getClass();
                    ensureDataBlobIsMutable();
                    this.dataBlob_.add(dataBlob);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(dataBlob);
                }
                return this;
            }

            public Builder addDataBlob(int i, DataBlob dataBlob) {
                RepeatedFieldBuilderV3<DataBlob, DataBlob.Builder, DataBlobOrBuilder> repeatedFieldBuilderV3 = this.dataBlobBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    dataBlob.getClass();
                    ensureDataBlobIsMutable();
                    this.dataBlob_.add(i, dataBlob);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, dataBlob);
                }
                return this;
            }

            public Builder addDataBlob(DataBlob.Builder builder) {
                RepeatedFieldBuilderV3<DataBlob, DataBlob.Builder, DataBlobOrBuilder> repeatedFieldBuilderV3 = this.dataBlobBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDataBlobIsMutable();
                    this.dataBlob_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addDataBlob(int i, DataBlob.Builder builder) {
                RepeatedFieldBuilderV3<DataBlob, DataBlob.Builder, DataBlobOrBuilder> repeatedFieldBuilderV3 = this.dataBlobBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDataBlobIsMutable();
                    this.dataBlob_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllDataBlob(Iterable<? extends DataBlob> iterable) {
                RepeatedFieldBuilderV3<DataBlob, DataBlob.Builder, DataBlobOrBuilder> repeatedFieldBuilderV3 = this.dataBlobBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDataBlobIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.dataBlob_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearDataBlob() {
                RepeatedFieldBuilderV3<DataBlob, DataBlob.Builder, DataBlobOrBuilder> repeatedFieldBuilderV3 = this.dataBlobBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.dataBlob_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeDataBlob(int i) {
                RepeatedFieldBuilderV3<DataBlob, DataBlob.Builder, DataBlobOrBuilder> repeatedFieldBuilderV3 = this.dataBlobBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDataBlobIsMutable();
                    this.dataBlob_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public DataBlob.Builder getDataBlobBuilder(int i) {
                return (DataBlob.Builder) getDataBlobFieldBuilder().getBuilder(i);
            }

            @Override // na.pb.DataCollectionOuterClass.DataCollectionOrBuilder
            public DataBlobOrBuilder getDataBlobOrBuilder(int i) {
                RepeatedFieldBuilderV3<DataBlob, DataBlob.Builder, DataBlobOrBuilder> repeatedFieldBuilderV3 = this.dataBlobBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.dataBlob_.get(i);
                }
                return (DataBlobOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // na.pb.DataCollectionOuterClass.DataCollectionOrBuilder
            public List<? extends DataBlobOrBuilder> getDataBlobOrBuilderList() {
                RepeatedFieldBuilderV3<DataBlob, DataBlob.Builder, DataBlobOrBuilder> repeatedFieldBuilderV3 = this.dataBlobBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.dataBlob_);
            }

            public DataBlob.Builder addDataBlobBuilder() {
                return (DataBlob.Builder) getDataBlobFieldBuilder().addBuilder(DataBlob.getDefaultInstance());
            }

            public DataBlob.Builder addDataBlobBuilder(int i) {
                return (DataBlob.Builder) getDataBlobFieldBuilder().addBuilder(i, DataBlob.getDefaultInstance());
            }

            public List<DataBlob.Builder> getDataBlobBuilderList() {
                return getDataBlobFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<DataBlob, DataBlob.Builder, DataBlobOrBuilder> getDataBlobFieldBuilder() {
                if (this.dataBlobBuilder_ == null) {
                    this.dataBlobBuilder_ = new RepeatedFieldBuilderV3<>(this.dataBlob_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                    this.dataBlob_ = null;
                }
                return this.dataBlobBuilder_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static DataCollection getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DataCollection> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<DataCollection> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public DataCollection getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class Header extends GeneratedMessageV3 implements HeaderOrBuilder {
        public static final int APPVERSION_FIELD_NUMBER = 6;
        public static final int CARMODE_FIELD_NUMBER = 4;
        public static final int COLLECTTIME_FIELD_NUMBER = 5;
        public static final int COUNTRY_FIELD_NUMBER = 2;
        public static final int DBVERSION_FIELD_NUMBER = 9;
        public static final int DEVICEID_FIELD_NUMBER = 3;
        public static final int LANGUAGE_FIELD_NUMBER = 7;
        public static final int OEM_FIELD_NUMBER = 1;
        public static final int USERID_FIELD_NUMBER = 8;
        private static final long serialVersionUID = 0;
        private volatile Object appVersion_;
        private volatile Object carMode_;
        private volatile Object collectTime_;
        private volatile Object country_;
        private volatile Object dbVersion_;
        private volatile Object deviceId_;
        private volatile Object language_;
        private byte memoizedIsInitialized;
        private int oem_;
        private volatile Object userId_;
        private static final Header DEFAULT_INSTANCE = new Header();
        private static final Parser<Header> PARSER = new AbstractParser<Header>() { // from class: na.pb.DataCollectionOuterClass.Header.1
            @Override // com.google.protobuf.Parser
            public Header parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Header(codedInputStream, extensionRegistryLite);
            }
        };

        private Header(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Header() {
            this.memoizedIsInitialized = (byte) -1;
            this.oem_ = 0;
            this.country_ = "";
            this.deviceId_ = "";
            this.carMode_ = "";
            this.collectTime_ = "";
            this.appVersion_ = "";
            this.language_ = "";
            this.userId_ = "";
            this.dbVersion_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new Header();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Header(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            try {
                                int tag = codedInputStream.readTag();
                                if (tag != 0) {
                                    if (tag == 8) {
                                        this.oem_ = codedInputStream.readEnum();
                                    } else if (tag == 18) {
                                        this.country_ = codedInputStream.readStringRequireUtf8();
                                    } else if (tag == 26) {
                                        this.deviceId_ = codedInputStream.readStringRequireUtf8();
                                    } else if (tag == 34) {
                                        this.carMode_ = codedInputStream.readStringRequireUtf8();
                                    } else if (tag == 42) {
                                        this.collectTime_ = codedInputStream.readStringRequireUtf8();
                                    } else if (tag == 50) {
                                        this.appVersion_ = codedInputStream.readStringRequireUtf8();
                                    } else if (tag == 58) {
                                        this.language_ = codedInputStream.readStringRequireUtf8();
                                    } else if (tag == 66) {
                                        this.userId_ = codedInputStream.readStringRequireUtf8();
                                    } else if (tag == 74) {
                                        this.dbVersion_ = codedInputStream.readStringRequireUtf8();
                                    } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                    }
                                }
                                z = true;
                            } catch (UninitializedMessageException e) {
                                throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
                            }
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e3) {
                        throw e3.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DataCollectionOuterClass.internal_static_na_pb_Header_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DataCollectionOuterClass.internal_static_na_pb_Header_fieldAccessorTable.ensureFieldAccessorsInitialized(Header.class, Builder.class);
        }

        @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
        public int getOemValue() {
            return this.oem_;
        }

        @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
        public OEM getOem() {
            OEM oemValueOf = OEM.valueOf(this.oem_);
            return oemValueOf == null ? OEM.UNRECOGNIZED : oemValueOf;
        }

        @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
        public String getCountry() {
            Object obj = this.country_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.country_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
        public ByteString getCountryBytes() {
            Object obj = this.country_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.country_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
        public String getDeviceId() {
            Object obj = this.deviceId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.deviceId_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
        public ByteString getDeviceIdBytes() {
            Object obj = this.deviceId_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.deviceId_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
        public String getCarMode() {
            Object obj = this.carMode_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.carMode_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
        public ByteString getCarModeBytes() {
            Object obj = this.carMode_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.carMode_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
        public String getCollectTime() {
            Object obj = this.collectTime_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.collectTime_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
        public ByteString getCollectTimeBytes() {
            Object obj = this.collectTime_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.collectTime_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
        public String getAppVersion() {
            Object obj = this.appVersion_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.appVersion_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
        public ByteString getAppVersionBytes() {
            Object obj = this.appVersion_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.appVersion_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
        public String getLanguage() {
            Object obj = this.language_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.language_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
        public ByteString getLanguageBytes() {
            Object obj = this.language_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.language_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
        public String getUserId() {
            Object obj = this.userId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.userId_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
        public ByteString getUserIdBytes() {
            Object obj = this.userId_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.userId_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
        public String getDbVersion() {
            Object obj = this.dbVersion_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.dbVersion_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
        public ByteString getDbVersionBytes() {
            Object obj = this.dbVersion_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.dbVersion_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.oem_ != OEM.OEM_RESERVE.getNumber()) {
                codedOutputStream.writeEnum(1, this.oem_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.country_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.country_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.deviceId_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.deviceId_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.carMode_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 4, this.carMode_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.collectTime_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 5, this.collectTime_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.appVersion_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 6, this.appVersion_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.language_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 7, this.language_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.userId_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 8, this.userId_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.dbVersion_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 9, this.dbVersion_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeEnumSize = this.oem_ != OEM.OEM_RESERVE.getNumber() ? 0 + CodedOutputStream.computeEnumSize(1, this.oem_) : 0;
            if (!GeneratedMessageV3.isStringEmpty(this.country_)) {
                iComputeEnumSize += GeneratedMessageV3.computeStringSize(2, this.country_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.deviceId_)) {
                iComputeEnumSize += GeneratedMessageV3.computeStringSize(3, this.deviceId_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.carMode_)) {
                iComputeEnumSize += GeneratedMessageV3.computeStringSize(4, this.carMode_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.collectTime_)) {
                iComputeEnumSize += GeneratedMessageV3.computeStringSize(5, this.collectTime_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.appVersion_)) {
                iComputeEnumSize += GeneratedMessageV3.computeStringSize(6, this.appVersion_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.language_)) {
                iComputeEnumSize += GeneratedMessageV3.computeStringSize(7, this.language_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.userId_)) {
                iComputeEnumSize += GeneratedMessageV3.computeStringSize(8, this.userId_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.dbVersion_)) {
                iComputeEnumSize += GeneratedMessageV3.computeStringSize(9, this.dbVersion_);
            }
            int serializedSize = iComputeEnumSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Header)) {
                return super.equals(obj);
            }
            Header header = (Header) obj;
            return this.oem_ == header.oem_ && getCountry().equals(header.getCountry()) && getDeviceId().equals(header.getDeviceId()) && getCarMode().equals(header.getCarMode()) && getCollectTime().equals(header.getCollectTime()) && getAppVersion().equals(header.getAppVersion()) && getLanguage().equals(header.getLanguage()) && getUserId().equals(header.getUserId()) && getDbVersion().equals(header.getDbVersion()) && this.unknownFields.equals(header.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((((((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.oem_) * 37) + 2) * 53) + getCountry().hashCode()) * 37) + 3) * 53) + getDeviceId().hashCode()) * 37) + 4) * 53) + getCarMode().hashCode()) * 37) + 5) * 53) + getCollectTime().hashCode()) * 37) + 6) * 53) + getAppVersion().hashCode()) * 37) + 7) * 53) + getLanguage().hashCode()) * 37) + 8) * 53) + getUserId().hashCode()) * 37) + 9) * 53) + getDbVersion().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        public static Header parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static Header parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Header parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static Header parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Header parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Header parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Header parseFrom(InputStream inputStream) throws IOException {
            return (Header) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Header parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Header) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Header parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Header) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Header parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Header) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Header parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Header) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Header parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Header) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Header header) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(header);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HeaderOrBuilder {
            private Object appVersion_;
            private Object carMode_;
            private Object collectTime_;
            private Object country_;
            private Object dbVersion_;
            private Object deviceId_;
            private Object language_;
            private int oem_;
            private Object userId_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DataCollectionOuterClass.internal_static_na_pb_Header_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DataCollectionOuterClass.internal_static_na_pb_Header_fieldAccessorTable.ensureFieldAccessorsInitialized(Header.class, Builder.class);
            }

            private Builder() {
                this.oem_ = 0;
                this.country_ = "";
                this.deviceId_ = "";
                this.carMode_ = "";
                this.collectTime_ = "";
                this.appVersion_ = "";
                this.language_ = "";
                this.userId_ = "";
                this.dbVersion_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.oem_ = 0;
                this.country_ = "";
                this.deviceId_ = "";
                this.carMode_ = "";
                this.collectTime_ = "";
                this.appVersion_ = "";
                this.language_ = "";
                this.userId_ = "";
                this.dbVersion_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Header.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.oem_ = 0;
                this.country_ = "";
                this.deviceId_ = "";
                this.carMode_ = "";
                this.collectTime_ = "";
                this.appVersion_ = "";
                this.language_ = "";
                this.userId_ = "";
                this.dbVersion_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DataCollectionOuterClass.internal_static_na_pb_Header_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Header getDefaultInstanceForType() {
                return Header.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Header build() {
                Header headerBuildPartial = buildPartial();
                if (headerBuildPartial.isInitialized()) {
                    return headerBuildPartial;
                }
                throw newUninitializedMessageException((Message) headerBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Header buildPartial() {
                Header header = new Header(this);
                header.oem_ = this.oem_;
                header.country_ = this.country_;
                header.deviceId_ = this.deviceId_;
                header.carMode_ = this.carMode_;
                header.collectTime_ = this.collectTime_;
                header.appVersion_ = this.appVersion_;
                header.language_ = this.language_;
                header.userId_ = this.userId_;
                header.dbVersion_ = this.dbVersion_;
                onBuilt();
                return header;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo93clone() {
                return (Builder) super.mo93clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof Header) {
                    return mergeFrom((Header) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Header header) {
                if (header == Header.getDefaultInstance()) {
                    return this;
                }
                if (header.oem_ != 0) {
                    setOemValue(header.getOemValue());
                }
                if (!header.getCountry().isEmpty()) {
                    this.country_ = header.country_;
                    onChanged();
                }
                if (!header.getDeviceId().isEmpty()) {
                    this.deviceId_ = header.deviceId_;
                    onChanged();
                }
                if (!header.getCarMode().isEmpty()) {
                    this.carMode_ = header.carMode_;
                    onChanged();
                }
                if (!header.getCollectTime().isEmpty()) {
                    this.collectTime_ = header.collectTime_;
                    onChanged();
                }
                if (!header.getAppVersion().isEmpty()) {
                    this.appVersion_ = header.appVersion_;
                    onChanged();
                }
                if (!header.getLanguage().isEmpty()) {
                    this.language_ = header.language_;
                    onChanged();
                }
                if (!header.getUserId().isEmpty()) {
                    this.userId_ = header.userId_;
                    onChanged();
                }
                if (!header.getDbVersion().isEmpty()) {
                    this.dbVersion_ = header.dbVersion_;
                    onChanged();
                }
                mergeUnknownFields(header.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public na.pb.DataCollectionOuterClass.Header.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = na.pb.DataCollectionOuterClass.Header.access$2800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    na.pb.DataCollectionOuterClass$Header r3 = (na.pb.DataCollectionOuterClass.Header) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    na.pb.DataCollectionOuterClass$Header r4 = (na.pb.DataCollectionOuterClass.Header) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: na.pb.DataCollectionOuterClass.Header.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):na.pb.DataCollectionOuterClass$Header$Builder");
            }

            @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
            public int getOemValue() {
                return this.oem_;
            }

            public Builder setOemValue(int i) {
                this.oem_ = i;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
            public OEM getOem() {
                OEM oemValueOf = OEM.valueOf(this.oem_);
                return oemValueOf == null ? OEM.UNRECOGNIZED : oemValueOf;
            }

            public Builder setOem(OEM oem) {
                oem.getClass();
                this.oem_ = oem.getNumber();
                onChanged();
                return this;
            }

            public Builder clearOem() {
                this.oem_ = 0;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
            public String getCountry() {
                Object obj = this.country_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.country_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
            public ByteString getCountryBytes() {
                Object obj = this.country_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.country_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setCountry(String str) {
                str.getClass();
                this.country_ = str;
                onChanged();
                return this;
            }

            public Builder clearCountry() {
                this.country_ = Header.getDefaultInstance().getCountry();
                onChanged();
                return this;
            }

            public Builder setCountryBytes(ByteString byteString) {
                byteString.getClass();
                Header.checkByteStringIsUtf8(byteString);
                this.country_ = byteString;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
            public String getDeviceId() {
                Object obj = this.deviceId_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.deviceId_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
            public ByteString getDeviceIdBytes() {
                Object obj = this.deviceId_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.deviceId_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setDeviceId(String str) {
                str.getClass();
                this.deviceId_ = str;
                onChanged();
                return this;
            }

            public Builder clearDeviceId() {
                this.deviceId_ = Header.getDefaultInstance().getDeviceId();
                onChanged();
                return this;
            }

            public Builder setDeviceIdBytes(ByteString byteString) {
                byteString.getClass();
                Header.checkByteStringIsUtf8(byteString);
                this.deviceId_ = byteString;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
            public String getCarMode() {
                Object obj = this.carMode_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.carMode_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
            public ByteString getCarModeBytes() {
                Object obj = this.carMode_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.carMode_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setCarMode(String str) {
                str.getClass();
                this.carMode_ = str;
                onChanged();
                return this;
            }

            public Builder clearCarMode() {
                this.carMode_ = Header.getDefaultInstance().getCarMode();
                onChanged();
                return this;
            }

            public Builder setCarModeBytes(ByteString byteString) {
                byteString.getClass();
                Header.checkByteStringIsUtf8(byteString);
                this.carMode_ = byteString;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
            public String getCollectTime() {
                Object obj = this.collectTime_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.collectTime_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
            public ByteString getCollectTimeBytes() {
                Object obj = this.collectTime_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.collectTime_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setCollectTime(String str) {
                str.getClass();
                this.collectTime_ = str;
                onChanged();
                return this;
            }

            public Builder clearCollectTime() {
                this.collectTime_ = Header.getDefaultInstance().getCollectTime();
                onChanged();
                return this;
            }

            public Builder setCollectTimeBytes(ByteString byteString) {
                byteString.getClass();
                Header.checkByteStringIsUtf8(byteString);
                this.collectTime_ = byteString;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
            public String getAppVersion() {
                Object obj = this.appVersion_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.appVersion_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
            public ByteString getAppVersionBytes() {
                Object obj = this.appVersion_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.appVersion_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setAppVersion(String str) {
                str.getClass();
                this.appVersion_ = str;
                onChanged();
                return this;
            }

            public Builder clearAppVersion() {
                this.appVersion_ = Header.getDefaultInstance().getAppVersion();
                onChanged();
                return this;
            }

            public Builder setAppVersionBytes(ByteString byteString) {
                byteString.getClass();
                Header.checkByteStringIsUtf8(byteString);
                this.appVersion_ = byteString;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
            public String getLanguage() {
                Object obj = this.language_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.language_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
            public ByteString getLanguageBytes() {
                Object obj = this.language_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.language_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setLanguage(String str) {
                str.getClass();
                this.language_ = str;
                onChanged();
                return this;
            }

            public Builder clearLanguage() {
                this.language_ = Header.getDefaultInstance().getLanguage();
                onChanged();
                return this;
            }

            public Builder setLanguageBytes(ByteString byteString) {
                byteString.getClass();
                Header.checkByteStringIsUtf8(byteString);
                this.language_ = byteString;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
            public String getUserId() {
                Object obj = this.userId_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.userId_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
            public ByteString getUserIdBytes() {
                Object obj = this.userId_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.userId_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setUserId(String str) {
                str.getClass();
                this.userId_ = str;
                onChanged();
                return this;
            }

            public Builder clearUserId() {
                this.userId_ = Header.getDefaultInstance().getUserId();
                onChanged();
                return this;
            }

            public Builder setUserIdBytes(ByteString byteString) {
                byteString.getClass();
                Header.checkByteStringIsUtf8(byteString);
                this.userId_ = byteString;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
            public String getDbVersion() {
                Object obj = this.dbVersion_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.dbVersion_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.HeaderOrBuilder
            public ByteString getDbVersionBytes() {
                Object obj = this.dbVersion_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.dbVersion_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setDbVersion(String str) {
                str.getClass();
                this.dbVersion_ = str;
                onChanged();
                return this;
            }

            public Builder clearDbVersion() {
                this.dbVersion_ = Header.getDefaultInstance().getDbVersion();
                onChanged();
                return this;
            }

            public Builder setDbVersionBytes(ByteString byteString) {
                byteString.getClass();
                Header.checkByteStringIsUtf8(byteString);
                this.dbVersion_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static Header getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Header> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Header> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Header getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class DataBlob extends GeneratedMessageV3 implements DataBlobOrBuilder {
        public static final int DATABLOB_FIELD_NUMBER = 4;
        public static final int EVENTCOMPONENT_FIELD_NUMBER = 1;
        public static final int EVENTID_FIELD_NUMBER = 2;
        public static final int EVENTTYPE_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private volatile Object dataBlob_;
        private int eventComponent_;
        private int eventId_;
        private int eventType_;
        private byte memoizedIsInitialized;
        private static final DataBlob DEFAULT_INSTANCE = new DataBlob();
        private static final Parser<DataBlob> PARSER = new AbstractParser<DataBlob>() { // from class: na.pb.DataCollectionOuterClass.DataBlob.1
            @Override // com.google.protobuf.Parser
            public DataBlob parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new DataBlob(codedInputStream, extensionRegistryLite);
            }
        };

        private DataBlob(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private DataBlob() {
            this.memoizedIsInitialized = (byte) -1;
            this.eventComponent_ = 0;
            this.eventId_ = 0;
            this.eventType_ = 0;
            this.dataBlob_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new DataBlob();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private DataBlob(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 8) {
                                    this.eventComponent_ = codedInputStream.readEnum();
                                } else if (tag == 16) {
                                    this.eventId_ = codedInputStream.readEnum();
                                } else if (tag == 24) {
                                    this.eventType_ = codedInputStream.readEnum();
                                } else if (tag == 34) {
                                    this.dataBlob_ = codedInputStream.readStringRequireUtf8();
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (UninitializedMessageException e2) {
                            throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(this);
                        }
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DataCollectionOuterClass.internal_static_na_pb_DataBlob_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DataCollectionOuterClass.internal_static_na_pb_DataBlob_fieldAccessorTable.ensureFieldAccessorsInitialized(DataBlob.class, Builder.class);
        }

        @Override // na.pb.DataCollectionOuterClass.DataBlobOrBuilder
        public int getEventComponentValue() {
            return this.eventComponent_;
        }

        @Override // na.pb.DataCollectionOuterClass.DataBlobOrBuilder
        public EventComponent getEventComponent() {
            EventComponent eventComponentValueOf = EventComponent.valueOf(this.eventComponent_);
            return eventComponentValueOf == null ? EventComponent.UNRECOGNIZED : eventComponentValueOf;
        }

        @Override // na.pb.DataCollectionOuterClass.DataBlobOrBuilder
        public int getEventIdValue() {
            return this.eventId_;
        }

        @Override // na.pb.DataCollectionOuterClass.DataBlobOrBuilder
        public EventID getEventId() {
            EventID eventIDValueOf = EventID.valueOf(this.eventId_);
            return eventIDValueOf == null ? EventID.UNRECOGNIZED : eventIDValueOf;
        }

        @Override // na.pb.DataCollectionOuterClass.DataBlobOrBuilder
        public int getEventTypeValue() {
            return this.eventType_;
        }

        @Override // na.pb.DataCollectionOuterClass.DataBlobOrBuilder
        public EventType getEventType() {
            EventType eventTypeValueOf = EventType.valueOf(this.eventType_);
            return eventTypeValueOf == null ? EventType.UNRECOGNIZED : eventTypeValueOf;
        }

        @Override // na.pb.DataCollectionOuterClass.DataBlobOrBuilder
        public String getDataBlob() {
            Object obj = this.dataBlob_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.dataBlob_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.DataBlobOrBuilder
        public ByteString getDataBlobBytes() {
            Object obj = this.dataBlob_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.dataBlob_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.eventComponent_ != EventComponent.EVENTCOMPONENT_RESERVE.getNumber()) {
                codedOutputStream.writeEnum(1, this.eventComponent_);
            }
            if (this.eventId_ != EventID.EVENTID_RESERVE.getNumber()) {
                codedOutputStream.writeEnum(2, this.eventId_);
            }
            if (this.eventType_ != EventType.EVENTTYPE_RESERVE.getNumber()) {
                codedOutputStream.writeEnum(3, this.eventType_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.dataBlob_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 4, this.dataBlob_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeEnumSize = this.eventComponent_ != EventComponent.EVENTCOMPONENT_RESERVE.getNumber() ? 0 + CodedOutputStream.computeEnumSize(1, this.eventComponent_) : 0;
            if (this.eventId_ != EventID.EVENTID_RESERVE.getNumber()) {
                iComputeEnumSize += CodedOutputStream.computeEnumSize(2, this.eventId_);
            }
            if (this.eventType_ != EventType.EVENTTYPE_RESERVE.getNumber()) {
                iComputeEnumSize += CodedOutputStream.computeEnumSize(3, this.eventType_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.dataBlob_)) {
                iComputeEnumSize += GeneratedMessageV3.computeStringSize(4, this.dataBlob_);
            }
            int serializedSize = iComputeEnumSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof DataBlob)) {
                return super.equals(obj);
            }
            DataBlob dataBlob = (DataBlob) obj;
            return this.eventComponent_ == dataBlob.eventComponent_ && this.eventId_ == dataBlob.eventId_ && this.eventType_ == dataBlob.eventType_ && getDataBlob().equals(dataBlob.getDataBlob()) && this.unknownFields.equals(dataBlob.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.eventComponent_) * 37) + 2) * 53) + this.eventId_) * 37) + 3) * 53) + this.eventType_) * 37) + 4) * 53) + getDataBlob().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        public static DataBlob parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static DataBlob parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static DataBlob parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static DataBlob parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static DataBlob parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static DataBlob parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static DataBlob parseFrom(InputStream inputStream) throws IOException {
            return (DataBlob) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static DataBlob parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DataBlob) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static DataBlob parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (DataBlob) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static DataBlob parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DataBlob) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static DataBlob parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (DataBlob) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static DataBlob parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DataBlob) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(DataBlob dataBlob) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(dataBlob);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DataBlobOrBuilder {
            private Object dataBlob_;
            private int eventComponent_;
            private int eventId_;
            private int eventType_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DataCollectionOuterClass.internal_static_na_pb_DataBlob_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DataCollectionOuterClass.internal_static_na_pb_DataBlob_fieldAccessorTable.ensureFieldAccessorsInitialized(DataBlob.class, Builder.class);
            }

            private Builder() {
                this.eventComponent_ = 0;
                this.eventId_ = 0;
                this.eventType_ = 0;
                this.dataBlob_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.eventComponent_ = 0;
                this.eventId_ = 0;
                this.eventType_ = 0;
                this.dataBlob_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = DataBlob.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.eventComponent_ = 0;
                this.eventId_ = 0;
                this.eventType_ = 0;
                this.dataBlob_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DataCollectionOuterClass.internal_static_na_pb_DataBlob_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public DataBlob getDefaultInstanceForType() {
                return DataBlob.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public DataBlob build() {
                DataBlob dataBlobBuildPartial = buildPartial();
                if (dataBlobBuildPartial.isInitialized()) {
                    return dataBlobBuildPartial;
                }
                throw newUninitializedMessageException((Message) dataBlobBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public DataBlob buildPartial() {
                DataBlob dataBlob = new DataBlob(this);
                dataBlob.eventComponent_ = this.eventComponent_;
                dataBlob.eventId_ = this.eventId_;
                dataBlob.eventType_ = this.eventType_;
                dataBlob.dataBlob_ = this.dataBlob_;
                onBuilt();
                return dataBlob;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo93clone() {
                return (Builder) super.mo93clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof DataBlob) {
                    return mergeFrom((DataBlob) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(DataBlob dataBlob) {
                if (dataBlob == DataBlob.getDefaultInstance()) {
                    return this;
                }
                if (dataBlob.eventComponent_ != 0) {
                    setEventComponentValue(dataBlob.getEventComponentValue());
                }
                if (dataBlob.eventId_ != 0) {
                    setEventIdValue(dataBlob.getEventIdValue());
                }
                if (dataBlob.eventType_ != 0) {
                    setEventTypeValue(dataBlob.getEventTypeValue());
                }
                if (!dataBlob.getDataBlob().isEmpty()) {
                    this.dataBlob_ = dataBlob.dataBlob_;
                    onChanged();
                }
                mergeUnknownFields(dataBlob.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public na.pb.DataCollectionOuterClass.DataBlob.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = na.pb.DataCollectionOuterClass.DataBlob.access$4900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    na.pb.DataCollectionOuterClass$DataBlob r3 = (na.pb.DataCollectionOuterClass.DataBlob) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    na.pb.DataCollectionOuterClass$DataBlob r4 = (na.pb.DataCollectionOuterClass.DataBlob) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: na.pb.DataCollectionOuterClass.DataBlob.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):na.pb.DataCollectionOuterClass$DataBlob$Builder");
            }

            @Override // na.pb.DataCollectionOuterClass.DataBlobOrBuilder
            public int getEventComponentValue() {
                return this.eventComponent_;
            }

            public Builder setEventComponentValue(int i) {
                this.eventComponent_ = i;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.DataBlobOrBuilder
            public EventComponent getEventComponent() {
                EventComponent eventComponentValueOf = EventComponent.valueOf(this.eventComponent_);
                return eventComponentValueOf == null ? EventComponent.UNRECOGNIZED : eventComponentValueOf;
            }

            public Builder setEventComponent(EventComponent eventComponent) {
                eventComponent.getClass();
                this.eventComponent_ = eventComponent.getNumber();
                onChanged();
                return this;
            }

            public Builder clearEventComponent() {
                this.eventComponent_ = 0;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.DataBlobOrBuilder
            public int getEventIdValue() {
                return this.eventId_;
            }

            public Builder setEventIdValue(int i) {
                this.eventId_ = i;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.DataBlobOrBuilder
            public EventID getEventId() {
                EventID eventIDValueOf = EventID.valueOf(this.eventId_);
                return eventIDValueOf == null ? EventID.UNRECOGNIZED : eventIDValueOf;
            }

            public Builder setEventId(EventID eventID) {
                eventID.getClass();
                this.eventId_ = eventID.getNumber();
                onChanged();
                return this;
            }

            public Builder clearEventId() {
                this.eventId_ = 0;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.DataBlobOrBuilder
            public int getEventTypeValue() {
                return this.eventType_;
            }

            public Builder setEventTypeValue(int i) {
                this.eventType_ = i;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.DataBlobOrBuilder
            public EventType getEventType() {
                EventType eventTypeValueOf = EventType.valueOf(this.eventType_);
                return eventTypeValueOf == null ? EventType.UNRECOGNIZED : eventTypeValueOf;
            }

            public Builder setEventType(EventType eventType) {
                eventType.getClass();
                this.eventType_ = eventType.getNumber();
                onChanged();
                return this;
            }

            public Builder clearEventType() {
                this.eventType_ = 0;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.DataBlobOrBuilder
            public String getDataBlob() {
                Object obj = this.dataBlob_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.dataBlob_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.DataBlobOrBuilder
            public ByteString getDataBlobBytes() {
                Object obj = this.dataBlob_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.dataBlob_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setDataBlob(String str) {
                str.getClass();
                this.dataBlob_ = str;
                onChanged();
                return this;
            }

            public Builder clearDataBlob() {
                this.dataBlob_ = DataBlob.getDefaultInstance().getDataBlob();
                onChanged();
                return this;
            }

            public Builder setDataBlobBytes(ByteString byteString) {
                byteString.getClass();
                DataBlob.checkByteStringIsUtf8(byteString);
                this.dataBlob_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static DataBlob getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DataBlob> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<DataBlob> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public DataBlob getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class GuidanceStart extends GeneratedMessageV3 implements GuidanceStartOrBuilder {
        public static final int ETA_FIELD_NUMBER = 2;
        public static final int TIMESTAMP_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private volatile Object eta_;
        private byte memoizedIsInitialized;
        private volatile Object timeStamp_;
        private static final GuidanceStart DEFAULT_INSTANCE = new GuidanceStart();
        private static final Parser<GuidanceStart> PARSER = new AbstractParser<GuidanceStart>() { // from class: na.pb.DataCollectionOuterClass.GuidanceStart.1
            @Override // com.google.protobuf.Parser
            public GuidanceStart parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new GuidanceStart(codedInputStream, extensionRegistryLite);
            }
        };

        private GuidanceStart(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private GuidanceStart() {
            this.memoizedIsInitialized = (byte) -1;
            this.timeStamp_ = "";
            this.eta_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new GuidanceStart();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private GuidanceStart(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 10) {
                                    this.timeStamp_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 18) {
                                    this.eta_ = codedInputStream.readStringRequireUtf8();
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            }
                            z = true;
                        } catch (UninitializedMessageException e) {
                            throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e3) {
                        throw e3.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DataCollectionOuterClass.internal_static_na_pb_GuidanceStart_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DataCollectionOuterClass.internal_static_na_pb_GuidanceStart_fieldAccessorTable.ensureFieldAccessorsInitialized(GuidanceStart.class, Builder.class);
        }

        @Override // na.pb.DataCollectionOuterClass.GuidanceStartOrBuilder
        public String getTimeStamp() {
            Object obj = this.timeStamp_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.timeStamp_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.GuidanceStartOrBuilder
        public ByteString getTimeStampBytes() {
            Object obj = this.timeStamp_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.timeStamp_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // na.pb.DataCollectionOuterClass.GuidanceStartOrBuilder
        public String getEta() {
            Object obj = this.eta_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.eta_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.GuidanceStartOrBuilder
        public ByteString getEtaBytes() {
            Object obj = this.eta_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.eta_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!GeneratedMessageV3.isStringEmpty(this.timeStamp_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.timeStamp_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.eta_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.eta_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = GeneratedMessageV3.isStringEmpty(this.timeStamp_) ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.timeStamp_);
            if (!GeneratedMessageV3.isStringEmpty(this.eta_)) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.eta_);
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof GuidanceStart)) {
                return super.equals(obj);
            }
            GuidanceStart guidanceStart = (GuidanceStart) obj;
            return getTimeStamp().equals(guidanceStart.getTimeStamp()) && getEta().equals(guidanceStart.getEta()) && this.unknownFields.equals(guidanceStart.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getTimeStamp().hashCode()) * 37) + 2) * 53) + getEta().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        public static GuidanceStart parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static GuidanceStart parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static GuidanceStart parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static GuidanceStart parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static GuidanceStart parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static GuidanceStart parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static GuidanceStart parseFrom(InputStream inputStream) throws IOException {
            return (GuidanceStart) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static GuidanceStart parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GuidanceStart) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static GuidanceStart parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GuidanceStart) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static GuidanceStart parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GuidanceStart) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static GuidanceStart parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GuidanceStart) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static GuidanceStart parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GuidanceStart) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(GuidanceStart guidanceStart) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(guidanceStart);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements GuidanceStartOrBuilder {
            private Object eta_;
            private Object timeStamp_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DataCollectionOuterClass.internal_static_na_pb_GuidanceStart_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DataCollectionOuterClass.internal_static_na_pb_GuidanceStart_fieldAccessorTable.ensureFieldAccessorsInitialized(GuidanceStart.class, Builder.class);
            }

            private Builder() {
                this.timeStamp_ = "";
                this.eta_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.timeStamp_ = "";
                this.eta_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GuidanceStart.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.timeStamp_ = "";
                this.eta_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DataCollectionOuterClass.internal_static_na_pb_GuidanceStart_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public GuidanceStart getDefaultInstanceForType() {
                return GuidanceStart.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GuidanceStart build() {
                GuidanceStart guidanceStartBuildPartial = buildPartial();
                if (guidanceStartBuildPartial.isInitialized()) {
                    return guidanceStartBuildPartial;
                }
                throw newUninitializedMessageException((Message) guidanceStartBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GuidanceStart buildPartial() {
                GuidanceStart guidanceStart = new GuidanceStart(this);
                guidanceStart.timeStamp_ = this.timeStamp_;
                guidanceStart.eta_ = this.eta_;
                onBuilt();
                return guidanceStart;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo93clone() {
                return (Builder) super.mo93clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof GuidanceStart) {
                    return mergeFrom((GuidanceStart) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(GuidanceStart guidanceStart) {
                if (guidanceStart == GuidanceStart.getDefaultInstance()) {
                    return this;
                }
                if (!guidanceStart.getTimeStamp().isEmpty()) {
                    this.timeStamp_ = guidanceStart.timeStamp_;
                    onChanged();
                }
                if (!guidanceStart.getEta().isEmpty()) {
                    this.eta_ = guidanceStart.eta_;
                    onChanged();
                }
                mergeUnknownFields(guidanceStart.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public na.pb.DataCollectionOuterClass.GuidanceStart.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = na.pb.DataCollectionOuterClass.GuidanceStart.access$6100()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    na.pb.DataCollectionOuterClass$GuidanceStart r3 = (na.pb.DataCollectionOuterClass.GuidanceStart) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    na.pb.DataCollectionOuterClass$GuidanceStart r4 = (na.pb.DataCollectionOuterClass.GuidanceStart) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: na.pb.DataCollectionOuterClass.GuidanceStart.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):na.pb.DataCollectionOuterClass$GuidanceStart$Builder");
            }

            @Override // na.pb.DataCollectionOuterClass.GuidanceStartOrBuilder
            public String getTimeStamp() {
                Object obj = this.timeStamp_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.timeStamp_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.GuidanceStartOrBuilder
            public ByteString getTimeStampBytes() {
                Object obj = this.timeStamp_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.timeStamp_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setTimeStamp(String str) {
                str.getClass();
                this.timeStamp_ = str;
                onChanged();
                return this;
            }

            public Builder clearTimeStamp() {
                this.timeStamp_ = GuidanceStart.getDefaultInstance().getTimeStamp();
                onChanged();
                return this;
            }

            public Builder setTimeStampBytes(ByteString byteString) {
                byteString.getClass();
                GuidanceStart.checkByteStringIsUtf8(byteString);
                this.timeStamp_ = byteString;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.GuidanceStartOrBuilder
            public String getEta() {
                Object obj = this.eta_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.eta_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.GuidanceStartOrBuilder
            public ByteString getEtaBytes() {
                Object obj = this.eta_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.eta_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setEta(String str) {
                str.getClass();
                this.eta_ = str;
                onChanged();
                return this;
            }

            public Builder clearEta() {
                this.eta_ = GuidanceStart.getDefaultInstance().getEta();
                onChanged();
                return this;
            }

            public Builder setEtaBytes(ByteString byteString) {
                byteString.getClass();
                GuidanceStart.checkByteStringIsUtf8(byteString);
                this.eta_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static GuidanceStart getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<GuidanceStart> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<GuidanceStart> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public GuidanceStart getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class GuidanceEnd extends GeneratedMessageV3 implements GuidanceEndOrBuilder {
        public static final int ETA_FIELD_NUMBER = 2;
        public static final int TIMESTAMP_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private volatile Object eta_;
        private byte memoizedIsInitialized;
        private volatile Object timeStamp_;
        private static final GuidanceEnd DEFAULT_INSTANCE = new GuidanceEnd();
        private static final Parser<GuidanceEnd> PARSER = new AbstractParser<GuidanceEnd>() { // from class: na.pb.DataCollectionOuterClass.GuidanceEnd.1
            @Override // com.google.protobuf.Parser
            public GuidanceEnd parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new GuidanceEnd(codedInputStream, extensionRegistryLite);
            }
        };

        private GuidanceEnd(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private GuidanceEnd() {
            this.memoizedIsInitialized = (byte) -1;
            this.timeStamp_ = "";
            this.eta_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new GuidanceEnd();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private GuidanceEnd(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 10) {
                                    this.timeStamp_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 18) {
                                    this.eta_ = codedInputStream.readStringRequireUtf8();
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            }
                            z = true;
                        } catch (UninitializedMessageException e) {
                            throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e3) {
                        throw e3.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DataCollectionOuterClass.internal_static_na_pb_GuidanceEnd_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DataCollectionOuterClass.internal_static_na_pb_GuidanceEnd_fieldAccessorTable.ensureFieldAccessorsInitialized(GuidanceEnd.class, Builder.class);
        }

        @Override // na.pb.DataCollectionOuterClass.GuidanceEndOrBuilder
        public String getTimeStamp() {
            Object obj = this.timeStamp_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.timeStamp_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.GuidanceEndOrBuilder
        public ByteString getTimeStampBytes() {
            Object obj = this.timeStamp_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.timeStamp_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // na.pb.DataCollectionOuterClass.GuidanceEndOrBuilder
        public String getEta() {
            Object obj = this.eta_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.eta_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.GuidanceEndOrBuilder
        public ByteString getEtaBytes() {
            Object obj = this.eta_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.eta_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!GeneratedMessageV3.isStringEmpty(this.timeStamp_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.timeStamp_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.eta_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.eta_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = GeneratedMessageV3.isStringEmpty(this.timeStamp_) ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.timeStamp_);
            if (!GeneratedMessageV3.isStringEmpty(this.eta_)) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.eta_);
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof GuidanceEnd)) {
                return super.equals(obj);
            }
            GuidanceEnd guidanceEnd = (GuidanceEnd) obj;
            return getTimeStamp().equals(guidanceEnd.getTimeStamp()) && getEta().equals(guidanceEnd.getEta()) && this.unknownFields.equals(guidanceEnd.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getTimeStamp().hashCode()) * 37) + 2) * 53) + getEta().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        public static GuidanceEnd parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static GuidanceEnd parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static GuidanceEnd parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static GuidanceEnd parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static GuidanceEnd parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static GuidanceEnd parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static GuidanceEnd parseFrom(InputStream inputStream) throws IOException {
            return (GuidanceEnd) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static GuidanceEnd parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GuidanceEnd) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static GuidanceEnd parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (GuidanceEnd) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static GuidanceEnd parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GuidanceEnd) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static GuidanceEnd parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (GuidanceEnd) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static GuidanceEnd parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (GuidanceEnd) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(GuidanceEnd guidanceEnd) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(guidanceEnd);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements GuidanceEndOrBuilder {
            private Object eta_;
            private Object timeStamp_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DataCollectionOuterClass.internal_static_na_pb_GuidanceEnd_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DataCollectionOuterClass.internal_static_na_pb_GuidanceEnd_fieldAccessorTable.ensureFieldAccessorsInitialized(GuidanceEnd.class, Builder.class);
            }

            private Builder() {
                this.timeStamp_ = "";
                this.eta_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.timeStamp_ = "";
                this.eta_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GuidanceEnd.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.timeStamp_ = "";
                this.eta_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DataCollectionOuterClass.internal_static_na_pb_GuidanceEnd_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public GuidanceEnd getDefaultInstanceForType() {
                return GuidanceEnd.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GuidanceEnd build() {
                GuidanceEnd guidanceEndBuildPartial = buildPartial();
                if (guidanceEndBuildPartial.isInitialized()) {
                    return guidanceEndBuildPartial;
                }
                throw newUninitializedMessageException((Message) guidanceEndBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public GuidanceEnd buildPartial() {
                GuidanceEnd guidanceEnd = new GuidanceEnd(this);
                guidanceEnd.timeStamp_ = this.timeStamp_;
                guidanceEnd.eta_ = this.eta_;
                onBuilt();
                return guidanceEnd;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo93clone() {
                return (Builder) super.mo93clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof GuidanceEnd) {
                    return mergeFrom((GuidanceEnd) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(GuidanceEnd guidanceEnd) {
                if (guidanceEnd == GuidanceEnd.getDefaultInstance()) {
                    return this;
                }
                if (!guidanceEnd.getTimeStamp().isEmpty()) {
                    this.timeStamp_ = guidanceEnd.timeStamp_;
                    onChanged();
                }
                if (!guidanceEnd.getEta().isEmpty()) {
                    this.eta_ = guidanceEnd.eta_;
                    onChanged();
                }
                mergeUnknownFields(guidanceEnd.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public na.pb.DataCollectionOuterClass.GuidanceEnd.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = na.pb.DataCollectionOuterClass.GuidanceEnd.access$7400()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    na.pb.DataCollectionOuterClass$GuidanceEnd r3 = (na.pb.DataCollectionOuterClass.GuidanceEnd) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    na.pb.DataCollectionOuterClass$GuidanceEnd r4 = (na.pb.DataCollectionOuterClass.GuidanceEnd) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: na.pb.DataCollectionOuterClass.GuidanceEnd.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):na.pb.DataCollectionOuterClass$GuidanceEnd$Builder");
            }

            @Override // na.pb.DataCollectionOuterClass.GuidanceEndOrBuilder
            public String getTimeStamp() {
                Object obj = this.timeStamp_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.timeStamp_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.GuidanceEndOrBuilder
            public ByteString getTimeStampBytes() {
                Object obj = this.timeStamp_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.timeStamp_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setTimeStamp(String str) {
                str.getClass();
                this.timeStamp_ = str;
                onChanged();
                return this;
            }

            public Builder clearTimeStamp() {
                this.timeStamp_ = GuidanceEnd.getDefaultInstance().getTimeStamp();
                onChanged();
                return this;
            }

            public Builder setTimeStampBytes(ByteString byteString) {
                byteString.getClass();
                GuidanceEnd.checkByteStringIsUtf8(byteString);
                this.timeStamp_ = byteString;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.GuidanceEndOrBuilder
            public String getEta() {
                Object obj = this.eta_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.eta_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.GuidanceEndOrBuilder
            public ByteString getEtaBytes() {
                Object obj = this.eta_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.eta_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setEta(String str) {
                str.getClass();
                this.eta_ = str;
                onChanged();
                return this;
            }

            public Builder clearEta() {
                this.eta_ = GuidanceEnd.getDefaultInstance().getEta();
                onChanged();
                return this;
            }

            public Builder setEtaBytes(ByteString byteString) {
                byteString.getClass();
                GuidanceEnd.checkByteStringIsUtf8(byteString);
                this.eta_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static GuidanceEnd getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<GuidanceEnd> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<GuidanceEnd> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public GuidanceEnd getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class NavigationAppStart extends GeneratedMessageV3 implements NavigationAppStartOrBuilder {
        private static final NavigationAppStart DEFAULT_INSTANCE = new NavigationAppStart();
        private static final Parser<NavigationAppStart> PARSER = new AbstractParser<NavigationAppStart>() { // from class: na.pb.DataCollectionOuterClass.NavigationAppStart.1
            @Override // com.google.protobuf.Parser
            public NavigationAppStart parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new NavigationAppStart(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int TIMESTAMP_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private volatile Object timeStamp_;

        private NavigationAppStart(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private NavigationAppStart() {
            this.memoizedIsInitialized = (byte) -1;
            this.timeStamp_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new NavigationAppStart();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private NavigationAppStart(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 10) {
                                    this.timeStamp_ = codedInputStream.readStringRequireUtf8();
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            }
                            z = true;
                        } catch (UninitializedMessageException e) {
                            throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e3) {
                        throw e3.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DataCollectionOuterClass.internal_static_na_pb_NavigationAppStart_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DataCollectionOuterClass.internal_static_na_pb_NavigationAppStart_fieldAccessorTable.ensureFieldAccessorsInitialized(NavigationAppStart.class, Builder.class);
        }

        @Override // na.pb.DataCollectionOuterClass.NavigationAppStartOrBuilder
        public String getTimeStamp() {
            Object obj = this.timeStamp_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.timeStamp_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.NavigationAppStartOrBuilder
        public ByteString getTimeStampBytes() {
            Object obj = this.timeStamp_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.timeStamp_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!GeneratedMessageV3.isStringEmpty(this.timeStamp_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.timeStamp_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (GeneratedMessageV3.isStringEmpty(this.timeStamp_) ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.timeStamp_)) + this.unknownFields.getSerializedSize();
            this.memoizedSize = iComputeStringSize;
            return iComputeStringSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof NavigationAppStart)) {
                return super.equals(obj);
            }
            NavigationAppStart navigationAppStart = (NavigationAppStart) obj;
            return getTimeStamp().equals(navigationAppStart.getTimeStamp()) && this.unknownFields.equals(navigationAppStart.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getTimeStamp().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        public static NavigationAppStart parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static NavigationAppStart parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static NavigationAppStart parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static NavigationAppStart parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static NavigationAppStart parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static NavigationAppStart parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static NavigationAppStart parseFrom(InputStream inputStream) throws IOException {
            return (NavigationAppStart) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static NavigationAppStart parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NavigationAppStart) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static NavigationAppStart parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NavigationAppStart) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static NavigationAppStart parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NavigationAppStart) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static NavigationAppStart parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (NavigationAppStart) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static NavigationAppStart parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NavigationAppStart) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(NavigationAppStart navigationAppStart) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(navigationAppStart);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements NavigationAppStartOrBuilder {
            private Object timeStamp_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DataCollectionOuterClass.internal_static_na_pb_NavigationAppStart_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DataCollectionOuterClass.internal_static_na_pb_NavigationAppStart_fieldAccessorTable.ensureFieldAccessorsInitialized(NavigationAppStart.class, Builder.class);
            }

            private Builder() {
                this.timeStamp_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.timeStamp_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = NavigationAppStart.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.timeStamp_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DataCollectionOuterClass.internal_static_na_pb_NavigationAppStart_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public NavigationAppStart getDefaultInstanceForType() {
                return NavigationAppStart.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public NavigationAppStart build() {
                NavigationAppStart navigationAppStartBuildPartial = buildPartial();
                if (navigationAppStartBuildPartial.isInitialized()) {
                    return navigationAppStartBuildPartial;
                }
                throw newUninitializedMessageException((Message) navigationAppStartBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public NavigationAppStart buildPartial() {
                NavigationAppStart navigationAppStart = new NavigationAppStart(this);
                navigationAppStart.timeStamp_ = this.timeStamp_;
                onBuilt();
                return navigationAppStart;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo93clone() {
                return (Builder) super.mo93clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof NavigationAppStart) {
                    return mergeFrom((NavigationAppStart) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(NavigationAppStart navigationAppStart) {
                if (navigationAppStart == NavigationAppStart.getDefaultInstance()) {
                    return this;
                }
                if (!navigationAppStart.getTimeStamp().isEmpty()) {
                    this.timeStamp_ = navigationAppStart.timeStamp_;
                    onChanged();
                }
                mergeUnknownFields(navigationAppStart.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public na.pb.DataCollectionOuterClass.NavigationAppStart.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = na.pb.DataCollectionOuterClass.NavigationAppStart.access$8600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    na.pb.DataCollectionOuterClass$NavigationAppStart r3 = (na.pb.DataCollectionOuterClass.NavigationAppStart) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    na.pb.DataCollectionOuterClass$NavigationAppStart r4 = (na.pb.DataCollectionOuterClass.NavigationAppStart) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: na.pb.DataCollectionOuterClass.NavigationAppStart.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):na.pb.DataCollectionOuterClass$NavigationAppStart$Builder");
            }

            @Override // na.pb.DataCollectionOuterClass.NavigationAppStartOrBuilder
            public String getTimeStamp() {
                Object obj = this.timeStamp_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.timeStamp_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.NavigationAppStartOrBuilder
            public ByteString getTimeStampBytes() {
                Object obj = this.timeStamp_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.timeStamp_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setTimeStamp(String str) {
                str.getClass();
                this.timeStamp_ = str;
                onChanged();
                return this;
            }

            public Builder clearTimeStamp() {
                this.timeStamp_ = NavigationAppStart.getDefaultInstance().getTimeStamp();
                onChanged();
                return this;
            }

            public Builder setTimeStampBytes(ByteString byteString) {
                byteString.getClass();
                NavigationAppStart.checkByteStringIsUtf8(byteString);
                this.timeStamp_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static NavigationAppStart getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<NavigationAppStart> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<NavigationAppStart> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public NavigationAppStart getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class NameInfo extends GeneratedMessageV3 implements NameInfoOrBuilder {
        public static final int LANGUAGECODE_FIELD_NUMBER = 1;
        public static final int NAME_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private volatile Object languageCode_;
        private byte memoizedIsInitialized;
        private volatile Object name_;
        private static final NameInfo DEFAULT_INSTANCE = new NameInfo();
        private static final Parser<NameInfo> PARSER = new AbstractParser<NameInfo>() { // from class: na.pb.DataCollectionOuterClass.NameInfo.1
            @Override // com.google.protobuf.Parser
            public NameInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new NameInfo(codedInputStream, extensionRegistryLite);
            }
        };

        private NameInfo(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private NameInfo() {
            this.memoizedIsInitialized = (byte) -1;
            this.languageCode_ = "";
            this.name_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new NameInfo();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private NameInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 10) {
                                    this.languageCode_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 18) {
                                    this.name_ = codedInputStream.readStringRequireUtf8();
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            }
                            z = true;
                        } catch (UninitializedMessageException e) {
                            throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e3) {
                        throw e3.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DataCollectionOuterClass.internal_static_na_pb_NameInfo_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DataCollectionOuterClass.internal_static_na_pb_NameInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(NameInfo.class, Builder.class);
        }

        @Override // na.pb.DataCollectionOuterClass.NameInfoOrBuilder
        public String getLanguageCode() {
            Object obj = this.languageCode_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.languageCode_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.NameInfoOrBuilder
        public ByteString getLanguageCodeBytes() {
            Object obj = this.languageCode_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.languageCode_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // na.pb.DataCollectionOuterClass.NameInfoOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.NameInfoOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!GeneratedMessageV3.isStringEmpty(this.languageCode_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.languageCode_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.name_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.name_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = GeneratedMessageV3.isStringEmpty(this.languageCode_) ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.languageCode_);
            if (!GeneratedMessageV3.isStringEmpty(this.name_)) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.name_);
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof NameInfo)) {
                return super.equals(obj);
            }
            NameInfo nameInfo = (NameInfo) obj;
            return getLanguageCode().equals(nameInfo.getLanguageCode()) && getName().equals(nameInfo.getName()) && this.unknownFields.equals(nameInfo.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getLanguageCode().hashCode()) * 37) + 2) * 53) + getName().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        public static NameInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static NameInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static NameInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static NameInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static NameInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static NameInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static NameInfo parseFrom(InputStream inputStream) throws IOException {
            return (NameInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static NameInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NameInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static NameInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NameInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static NameInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NameInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static NameInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (NameInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static NameInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NameInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(NameInfo nameInfo) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(nameInfo);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements NameInfoOrBuilder {
            private Object languageCode_;
            private Object name_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DataCollectionOuterClass.internal_static_na_pb_NameInfo_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DataCollectionOuterClass.internal_static_na_pb_NameInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(NameInfo.class, Builder.class);
            }

            private Builder() {
                this.languageCode_ = "";
                this.name_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.languageCode_ = "";
                this.name_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = NameInfo.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.languageCode_ = "";
                this.name_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DataCollectionOuterClass.internal_static_na_pb_NameInfo_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public NameInfo getDefaultInstanceForType() {
                return NameInfo.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public NameInfo build() {
                NameInfo nameInfoBuildPartial = buildPartial();
                if (nameInfoBuildPartial.isInitialized()) {
                    return nameInfoBuildPartial;
                }
                throw newUninitializedMessageException((Message) nameInfoBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public NameInfo buildPartial() {
                NameInfo nameInfo = new NameInfo(this);
                nameInfo.languageCode_ = this.languageCode_;
                nameInfo.name_ = this.name_;
                onBuilt();
                return nameInfo;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo93clone() {
                return (Builder) super.mo93clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof NameInfo) {
                    return mergeFrom((NameInfo) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(NameInfo nameInfo) {
                if (nameInfo == NameInfo.getDefaultInstance()) {
                    return this;
                }
                if (!nameInfo.getLanguageCode().isEmpty()) {
                    this.languageCode_ = nameInfo.languageCode_;
                    onChanged();
                }
                if (!nameInfo.getName().isEmpty()) {
                    this.name_ = nameInfo.name_;
                    onChanged();
                }
                mergeUnknownFields(nameInfo.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public na.pb.DataCollectionOuterClass.NameInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = na.pb.DataCollectionOuterClass.NameInfo.access$9800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    na.pb.DataCollectionOuterClass$NameInfo r3 = (na.pb.DataCollectionOuterClass.NameInfo) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    na.pb.DataCollectionOuterClass$NameInfo r4 = (na.pb.DataCollectionOuterClass.NameInfo) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: na.pb.DataCollectionOuterClass.NameInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):na.pb.DataCollectionOuterClass$NameInfo$Builder");
            }

            @Override // na.pb.DataCollectionOuterClass.NameInfoOrBuilder
            public String getLanguageCode() {
                Object obj = this.languageCode_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.languageCode_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.NameInfoOrBuilder
            public ByteString getLanguageCodeBytes() {
                Object obj = this.languageCode_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.languageCode_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setLanguageCode(String str) {
                str.getClass();
                this.languageCode_ = str;
                onChanged();
                return this;
            }

            public Builder clearLanguageCode() {
                this.languageCode_ = NameInfo.getDefaultInstance().getLanguageCode();
                onChanged();
                return this;
            }

            public Builder setLanguageCodeBytes(ByteString byteString) {
                byteString.getClass();
                NameInfo.checkByteStringIsUtf8(byteString);
                this.languageCode_ = byteString;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.NameInfoOrBuilder
            public String getName() {
                Object obj = this.name_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.name_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.NameInfoOrBuilder
            public ByteString getNameBytes() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.name_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setName(String str) {
                str.getClass();
                this.name_ = str;
                onChanged();
                return this;
            }

            public Builder clearName() {
                this.name_ = NameInfo.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                byteString.getClass();
                NameInfo.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static NameInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<NameInfo> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<NameInfo> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public NameInfo getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class CalculateRouteEnd extends GeneratedMessageV3 implements CalculateRouteEndOrBuilder {
        public static final int ADDRESS_FIELD_NUMBER = 6;
        public static final int DESTTYPE_FIELD_NUMBER = 5;
        public static final int LAT_FIELD_NUMBER = 3;
        public static final int LON_FIELD_NUMBER = 4;
        public static final int NAMEINFOS_FIELD_NUMBER = 9;
        public static final int POICATEGORY_FIELD_NUMBER = 8;
        public static final int POISRC_FIELD_NUMBER = 7;
        public static final int ROUTESOURCE_FIELD_NUMBER = 2;
        public static final int TIMESTAMP_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private volatile Object address_;
        private int destType_;
        private volatile Object lat_;
        private volatile Object lon_;
        private byte memoizedIsInitialized;
        private List<NameInfo> nameInfos_;
        private int poiCategory_;
        private int poiSrc_;
        private int routeSource_;
        private volatile Object timeStamp_;
        private static final CalculateRouteEnd DEFAULT_INSTANCE = new CalculateRouteEnd();
        private static final Parser<CalculateRouteEnd> PARSER = new AbstractParser<CalculateRouteEnd>() { // from class: na.pb.DataCollectionOuterClass.CalculateRouteEnd.1
            @Override // com.google.protobuf.Parser
            public CalculateRouteEnd parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CalculateRouteEnd(codedInputStream, extensionRegistryLite);
            }
        };

        private CalculateRouteEnd(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private CalculateRouteEnd() {
            this.memoizedIsInitialized = (byte) -1;
            this.timeStamp_ = "";
            this.routeSource_ = 0;
            this.lat_ = "";
            this.lon_ = "";
            this.destType_ = 0;
            this.address_ = "";
            this.poiSrc_ = 0;
            this.nameInfos_ = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new CalculateRouteEnd();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private CalculateRouteEnd(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 10) {
                                    this.timeStamp_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 16) {
                                    this.routeSource_ = codedInputStream.readEnum();
                                } else if (tag == 26) {
                                    this.lat_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 34) {
                                    this.lon_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 40) {
                                    this.destType_ = codedInputStream.readEnum();
                                } else if (tag == 50) {
                                    this.address_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 56) {
                                    this.poiSrc_ = codedInputStream.readEnum();
                                } else if (tag == 64) {
                                    this.poiCategory_ = codedInputStream.readUInt32();
                                } else if (tag == 74) {
                                    if (!(z2 & true)) {
                                        this.nameInfos_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.nameInfos_.add((NameInfo) codedInputStream.readMessage(NameInfo.parser(), extensionRegistryLite));
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (UninitializedMessageException e2) {
                            throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(this);
                        }
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.nameInfos_ = Collections.unmodifiableList(this.nameInfos_);
                    }
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DataCollectionOuterClass.internal_static_na_pb_CalculateRouteEnd_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DataCollectionOuterClass.internal_static_na_pb_CalculateRouteEnd_fieldAccessorTable.ensureFieldAccessorsInitialized(CalculateRouteEnd.class, Builder.class);
        }

        @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
        public String getTimeStamp() {
            Object obj = this.timeStamp_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.timeStamp_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
        public ByteString getTimeStampBytes() {
            Object obj = this.timeStamp_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.timeStamp_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
        public int getRouteSourceValue() {
            return this.routeSource_;
        }

        @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
        public RouteSrc getRouteSource() {
            RouteSrc routeSrcValueOf = RouteSrc.valueOf(this.routeSource_);
            return routeSrcValueOf == null ? RouteSrc.UNRECOGNIZED : routeSrcValueOf;
        }

        @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
        public String getLat() {
            Object obj = this.lat_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.lat_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
        public ByteString getLatBytes() {
            Object obj = this.lat_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.lat_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
        public String getLon() {
            Object obj = this.lon_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.lon_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
        public ByteString getLonBytes() {
            Object obj = this.lon_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.lon_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
        public int getDestTypeValue() {
            return this.destType_;
        }

        @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
        public DestType getDestType() {
            DestType destTypeValueOf = DestType.valueOf(this.destType_);
            return destTypeValueOf == null ? DestType.UNRECOGNIZED : destTypeValueOf;
        }

        @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
        public String getAddress() {
            Object obj = this.address_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.address_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
        public ByteString getAddressBytes() {
            Object obj = this.address_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.address_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
        public int getPoiSrcValue() {
            return this.poiSrc_;
        }

        @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
        public PoiSrc getPoiSrc() {
            PoiSrc poiSrcValueOf = PoiSrc.valueOf(this.poiSrc_);
            return poiSrcValueOf == null ? PoiSrc.UNRECOGNIZED : poiSrcValueOf;
        }

        @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
        public int getPoiCategory() {
            return this.poiCategory_;
        }

        @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
        public List<NameInfo> getNameInfosList() {
            return this.nameInfos_;
        }

        @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
        public List<? extends NameInfoOrBuilder> getNameInfosOrBuilderList() {
            return this.nameInfos_;
        }

        @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
        public int getNameInfosCount() {
            return this.nameInfos_.size();
        }

        @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
        public NameInfo getNameInfos(int i) {
            return this.nameInfos_.get(i);
        }

        @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
        public NameInfoOrBuilder getNameInfosOrBuilder(int i) {
            return this.nameInfos_.get(i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!GeneratedMessageV3.isStringEmpty(this.timeStamp_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.timeStamp_);
            }
            if (this.routeSource_ != RouteSrc.ROUTESRC_RESERVE.getNumber()) {
                codedOutputStream.writeEnum(2, this.routeSource_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.lat_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.lat_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.lon_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 4, this.lon_);
            }
            if (this.destType_ != DestType.DESTTYPE_RESERVE.getNumber()) {
                codedOutputStream.writeEnum(5, this.destType_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.address_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 6, this.address_);
            }
            if (this.poiSrc_ != PoiSrc.POISRC_RESERVE.getNumber()) {
                codedOutputStream.writeEnum(7, this.poiSrc_);
            }
            int i = this.poiCategory_;
            if (i != 0) {
                codedOutputStream.writeUInt32(8, i);
            }
            for (int i2 = 0; i2 < this.nameInfos_.size(); i2++) {
                codedOutputStream.writeMessage(9, this.nameInfos_.get(i2));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = !GeneratedMessageV3.isStringEmpty(this.timeStamp_) ? GeneratedMessageV3.computeStringSize(1, this.timeStamp_) + 0 : 0;
            if (this.routeSource_ != RouteSrc.ROUTESRC_RESERVE.getNumber()) {
                iComputeStringSize += CodedOutputStream.computeEnumSize(2, this.routeSource_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.lat_)) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.lat_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.lon_)) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(4, this.lon_);
            }
            if (this.destType_ != DestType.DESTTYPE_RESERVE.getNumber()) {
                iComputeStringSize += CodedOutputStream.computeEnumSize(5, this.destType_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.address_)) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(6, this.address_);
            }
            if (this.poiSrc_ != PoiSrc.POISRC_RESERVE.getNumber()) {
                iComputeStringSize += CodedOutputStream.computeEnumSize(7, this.poiSrc_);
            }
            int i2 = this.poiCategory_;
            if (i2 != 0) {
                iComputeStringSize += CodedOutputStream.computeUInt32Size(8, i2);
            }
            for (int i3 = 0; i3 < this.nameInfos_.size(); i3++) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(9, this.nameInfos_.get(i3));
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CalculateRouteEnd)) {
                return super.equals(obj);
            }
            CalculateRouteEnd calculateRouteEnd = (CalculateRouteEnd) obj;
            return getTimeStamp().equals(calculateRouteEnd.getTimeStamp()) && this.routeSource_ == calculateRouteEnd.routeSource_ && getLat().equals(calculateRouteEnd.getLat()) && getLon().equals(calculateRouteEnd.getLon()) && this.destType_ == calculateRouteEnd.destType_ && getAddress().equals(calculateRouteEnd.getAddress()) && this.poiSrc_ == calculateRouteEnd.poiSrc_ && getPoiCategory() == calculateRouteEnd.getPoiCategory() && getNameInfosList().equals(calculateRouteEnd.getNameInfosList()) && this.unknownFields.equals(calculateRouteEnd.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getTimeStamp().hashCode()) * 37) + 2) * 53) + this.routeSource_) * 37) + 3) * 53) + getLat().hashCode()) * 37) + 4) * 53) + getLon().hashCode()) * 37) + 5) * 53) + this.destType_) * 37) + 6) * 53) + getAddress().hashCode()) * 37) + 7) * 53) + this.poiSrc_) * 37) + 8) * 53) + getPoiCategory();
            if (getNameInfosCount() > 0) {
                iHashCode = (((iHashCode * 37) + 9) * 53) + getNameInfosList().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        public static CalculateRouteEnd parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static CalculateRouteEnd parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static CalculateRouteEnd parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static CalculateRouteEnd parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static CalculateRouteEnd parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static CalculateRouteEnd parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static CalculateRouteEnd parseFrom(InputStream inputStream) throws IOException {
            return (CalculateRouteEnd) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static CalculateRouteEnd parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CalculateRouteEnd) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CalculateRouteEnd parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CalculateRouteEnd) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static CalculateRouteEnd parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CalculateRouteEnd) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static CalculateRouteEnd parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CalculateRouteEnd) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static CalculateRouteEnd parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CalculateRouteEnd) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CalculateRouteEnd calculateRouteEnd) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(calculateRouteEnd);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CalculateRouteEndOrBuilder {
            private Object address_;
            private int bitField0_;
            private int destType_;
            private Object lat_;
            private Object lon_;
            private RepeatedFieldBuilderV3<NameInfo, NameInfo.Builder, NameInfoOrBuilder> nameInfosBuilder_;
            private List<NameInfo> nameInfos_;
            private int poiCategory_;
            private int poiSrc_;
            private int routeSource_;
            private Object timeStamp_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DataCollectionOuterClass.internal_static_na_pb_CalculateRouteEnd_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DataCollectionOuterClass.internal_static_na_pb_CalculateRouteEnd_fieldAccessorTable.ensureFieldAccessorsInitialized(CalculateRouteEnd.class, Builder.class);
            }

            private Builder() {
                this.timeStamp_ = "";
                this.routeSource_ = 0;
                this.lat_ = "";
                this.lon_ = "";
                this.destType_ = 0;
                this.address_ = "";
                this.poiSrc_ = 0;
                this.nameInfos_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.timeStamp_ = "";
                this.routeSource_ = 0;
                this.lat_ = "";
                this.lon_ = "";
                this.destType_ = 0;
                this.address_ = "";
                this.poiSrc_ = 0;
                this.nameInfos_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (CalculateRouteEnd.alwaysUseFieldBuilders) {
                    getNameInfosFieldBuilder();
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.timeStamp_ = "";
                this.routeSource_ = 0;
                this.lat_ = "";
                this.lon_ = "";
                this.destType_ = 0;
                this.address_ = "";
                this.poiSrc_ = 0;
                this.poiCategory_ = 0;
                RepeatedFieldBuilderV3<NameInfo, NameInfo.Builder, NameInfoOrBuilder> repeatedFieldBuilderV3 = this.nameInfosBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.nameInfos_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DataCollectionOuterClass.internal_static_na_pb_CalculateRouteEnd_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public CalculateRouteEnd getDefaultInstanceForType() {
                return CalculateRouteEnd.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CalculateRouteEnd build() {
                CalculateRouteEnd calculateRouteEndBuildPartial = buildPartial();
                if (calculateRouteEndBuildPartial.isInitialized()) {
                    return calculateRouteEndBuildPartial;
                }
                throw newUninitializedMessageException((Message) calculateRouteEndBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CalculateRouteEnd buildPartial() {
                CalculateRouteEnd calculateRouteEnd = new CalculateRouteEnd(this);
                calculateRouteEnd.timeStamp_ = this.timeStamp_;
                calculateRouteEnd.routeSource_ = this.routeSource_;
                calculateRouteEnd.lat_ = this.lat_;
                calculateRouteEnd.lon_ = this.lon_;
                calculateRouteEnd.destType_ = this.destType_;
                calculateRouteEnd.address_ = this.address_;
                calculateRouteEnd.poiSrc_ = this.poiSrc_;
                calculateRouteEnd.poiCategory_ = this.poiCategory_;
                RepeatedFieldBuilderV3<NameInfo, NameInfo.Builder, NameInfoOrBuilder> repeatedFieldBuilderV3 = this.nameInfosBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    calculateRouteEnd.nameInfos_ = repeatedFieldBuilderV3.build();
                } else {
                    if ((this.bitField0_ & 1) != 0) {
                        this.nameInfos_ = Collections.unmodifiableList(this.nameInfos_);
                        this.bitField0_ &= -2;
                    }
                    calculateRouteEnd.nameInfos_ = this.nameInfos_;
                }
                onBuilt();
                return calculateRouteEnd;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo93clone() {
                return (Builder) super.mo93clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof CalculateRouteEnd) {
                    return mergeFrom((CalculateRouteEnd) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(CalculateRouteEnd calculateRouteEnd) {
                if (calculateRouteEnd == CalculateRouteEnd.getDefaultInstance()) {
                    return this;
                }
                if (!calculateRouteEnd.getTimeStamp().isEmpty()) {
                    this.timeStamp_ = calculateRouteEnd.timeStamp_;
                    onChanged();
                }
                if (calculateRouteEnd.routeSource_ != 0) {
                    setRouteSourceValue(calculateRouteEnd.getRouteSourceValue());
                }
                if (!calculateRouteEnd.getLat().isEmpty()) {
                    this.lat_ = calculateRouteEnd.lat_;
                    onChanged();
                }
                if (!calculateRouteEnd.getLon().isEmpty()) {
                    this.lon_ = calculateRouteEnd.lon_;
                    onChanged();
                }
                if (calculateRouteEnd.destType_ != 0) {
                    setDestTypeValue(calculateRouteEnd.getDestTypeValue());
                }
                if (!calculateRouteEnd.getAddress().isEmpty()) {
                    this.address_ = calculateRouteEnd.address_;
                    onChanged();
                }
                if (calculateRouteEnd.poiSrc_ != 0) {
                    setPoiSrcValue(calculateRouteEnd.getPoiSrcValue());
                }
                if (calculateRouteEnd.getPoiCategory() != 0) {
                    setPoiCategory(calculateRouteEnd.getPoiCategory());
                }
                if (this.nameInfosBuilder_ == null) {
                    if (!calculateRouteEnd.nameInfos_.isEmpty()) {
                        if (this.nameInfos_.isEmpty()) {
                            this.nameInfos_ = calculateRouteEnd.nameInfos_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureNameInfosIsMutable();
                            this.nameInfos_.addAll(calculateRouteEnd.nameInfos_);
                        }
                        onChanged();
                    }
                } else if (!calculateRouteEnd.nameInfos_.isEmpty()) {
                    if (!this.nameInfosBuilder_.isEmpty()) {
                        this.nameInfosBuilder_.addAllMessages(calculateRouteEnd.nameInfos_);
                    } else {
                        this.nameInfosBuilder_.dispose();
                        this.nameInfosBuilder_ = null;
                        this.nameInfos_ = calculateRouteEnd.nameInfos_;
                        this.bitField0_ &= -2;
                        this.nameInfosBuilder_ = CalculateRouteEnd.alwaysUseFieldBuilders ? getNameInfosFieldBuilder() : null;
                    }
                }
                mergeUnknownFields(calculateRouteEnd.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public na.pb.DataCollectionOuterClass.CalculateRouteEnd.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = na.pb.DataCollectionOuterClass.CalculateRouteEnd.access$11900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    na.pb.DataCollectionOuterClass$CalculateRouteEnd r3 = (na.pb.DataCollectionOuterClass.CalculateRouteEnd) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    na.pb.DataCollectionOuterClass$CalculateRouteEnd r4 = (na.pb.DataCollectionOuterClass.CalculateRouteEnd) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: na.pb.DataCollectionOuterClass.CalculateRouteEnd.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):na.pb.DataCollectionOuterClass$CalculateRouteEnd$Builder");
            }

            @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
            public String getTimeStamp() {
                Object obj = this.timeStamp_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.timeStamp_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
            public ByteString getTimeStampBytes() {
                Object obj = this.timeStamp_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.timeStamp_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setTimeStamp(String str) {
                str.getClass();
                this.timeStamp_ = str;
                onChanged();
                return this;
            }

            public Builder clearTimeStamp() {
                this.timeStamp_ = CalculateRouteEnd.getDefaultInstance().getTimeStamp();
                onChanged();
                return this;
            }

            public Builder setTimeStampBytes(ByteString byteString) {
                byteString.getClass();
                CalculateRouteEnd.checkByteStringIsUtf8(byteString);
                this.timeStamp_ = byteString;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
            public int getRouteSourceValue() {
                return this.routeSource_;
            }

            public Builder setRouteSourceValue(int i) {
                this.routeSource_ = i;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
            public RouteSrc getRouteSource() {
                RouteSrc routeSrcValueOf = RouteSrc.valueOf(this.routeSource_);
                return routeSrcValueOf == null ? RouteSrc.UNRECOGNIZED : routeSrcValueOf;
            }

            public Builder setRouteSource(RouteSrc routeSrc) {
                routeSrc.getClass();
                this.routeSource_ = routeSrc.getNumber();
                onChanged();
                return this;
            }

            public Builder clearRouteSource() {
                this.routeSource_ = 0;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
            public String getLat() {
                Object obj = this.lat_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.lat_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
            public ByteString getLatBytes() {
                Object obj = this.lat_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.lat_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setLat(String str) {
                str.getClass();
                this.lat_ = str;
                onChanged();
                return this;
            }

            public Builder clearLat() {
                this.lat_ = CalculateRouteEnd.getDefaultInstance().getLat();
                onChanged();
                return this;
            }

            public Builder setLatBytes(ByteString byteString) {
                byteString.getClass();
                CalculateRouteEnd.checkByteStringIsUtf8(byteString);
                this.lat_ = byteString;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
            public String getLon() {
                Object obj = this.lon_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.lon_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
            public ByteString getLonBytes() {
                Object obj = this.lon_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.lon_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setLon(String str) {
                str.getClass();
                this.lon_ = str;
                onChanged();
                return this;
            }

            public Builder clearLon() {
                this.lon_ = CalculateRouteEnd.getDefaultInstance().getLon();
                onChanged();
                return this;
            }

            public Builder setLonBytes(ByteString byteString) {
                byteString.getClass();
                CalculateRouteEnd.checkByteStringIsUtf8(byteString);
                this.lon_ = byteString;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
            public int getDestTypeValue() {
                return this.destType_;
            }

            public Builder setDestTypeValue(int i) {
                this.destType_ = i;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
            public DestType getDestType() {
                DestType destTypeValueOf = DestType.valueOf(this.destType_);
                return destTypeValueOf == null ? DestType.UNRECOGNIZED : destTypeValueOf;
            }

            public Builder setDestType(DestType destType) {
                destType.getClass();
                this.destType_ = destType.getNumber();
                onChanged();
                return this;
            }

            public Builder clearDestType() {
                this.destType_ = 0;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
            public String getAddress() {
                Object obj = this.address_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.address_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
            public ByteString getAddressBytes() {
                Object obj = this.address_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.address_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setAddress(String str) {
                str.getClass();
                this.address_ = str;
                onChanged();
                return this;
            }

            public Builder clearAddress() {
                this.address_ = CalculateRouteEnd.getDefaultInstance().getAddress();
                onChanged();
                return this;
            }

            public Builder setAddressBytes(ByteString byteString) {
                byteString.getClass();
                CalculateRouteEnd.checkByteStringIsUtf8(byteString);
                this.address_ = byteString;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
            public int getPoiSrcValue() {
                return this.poiSrc_;
            }

            public Builder setPoiSrcValue(int i) {
                this.poiSrc_ = i;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
            public PoiSrc getPoiSrc() {
                PoiSrc poiSrcValueOf = PoiSrc.valueOf(this.poiSrc_);
                return poiSrcValueOf == null ? PoiSrc.UNRECOGNIZED : poiSrcValueOf;
            }

            public Builder setPoiSrc(PoiSrc poiSrc) {
                poiSrc.getClass();
                this.poiSrc_ = poiSrc.getNumber();
                onChanged();
                return this;
            }

            public Builder clearPoiSrc() {
                this.poiSrc_ = 0;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
            public int getPoiCategory() {
                return this.poiCategory_;
            }

            public Builder setPoiCategory(int i) {
                this.poiCategory_ = i;
                onChanged();
                return this;
            }

            public Builder clearPoiCategory() {
                this.poiCategory_ = 0;
                onChanged();
                return this;
            }

            private void ensureNameInfosIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.nameInfos_ = new ArrayList(this.nameInfos_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
            public List<NameInfo> getNameInfosList() {
                RepeatedFieldBuilderV3<NameInfo, NameInfo.Builder, NameInfoOrBuilder> repeatedFieldBuilderV3 = this.nameInfosBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.nameInfos_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
            public int getNameInfosCount() {
                RepeatedFieldBuilderV3<NameInfo, NameInfo.Builder, NameInfoOrBuilder> repeatedFieldBuilderV3 = this.nameInfosBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.nameInfos_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
            public NameInfo getNameInfos(int i) {
                RepeatedFieldBuilderV3<NameInfo, NameInfo.Builder, NameInfoOrBuilder> repeatedFieldBuilderV3 = this.nameInfosBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.nameInfos_.get(i);
                }
                return (NameInfo) repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setNameInfos(int i, NameInfo nameInfo) {
                RepeatedFieldBuilderV3<NameInfo, NameInfo.Builder, NameInfoOrBuilder> repeatedFieldBuilderV3 = this.nameInfosBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    nameInfo.getClass();
                    ensureNameInfosIsMutable();
                    this.nameInfos_.set(i, nameInfo);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, nameInfo);
                }
                return this;
            }

            public Builder setNameInfos(int i, NameInfo.Builder builder) {
                RepeatedFieldBuilderV3<NameInfo, NameInfo.Builder, NameInfoOrBuilder> repeatedFieldBuilderV3 = this.nameInfosBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureNameInfosIsMutable();
                    this.nameInfos_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addNameInfos(NameInfo nameInfo) {
                RepeatedFieldBuilderV3<NameInfo, NameInfo.Builder, NameInfoOrBuilder> repeatedFieldBuilderV3 = this.nameInfosBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    nameInfo.getClass();
                    ensureNameInfosIsMutable();
                    this.nameInfos_.add(nameInfo);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(nameInfo);
                }
                return this;
            }

            public Builder addNameInfos(int i, NameInfo nameInfo) {
                RepeatedFieldBuilderV3<NameInfo, NameInfo.Builder, NameInfoOrBuilder> repeatedFieldBuilderV3 = this.nameInfosBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    nameInfo.getClass();
                    ensureNameInfosIsMutable();
                    this.nameInfos_.add(i, nameInfo);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, nameInfo);
                }
                return this;
            }

            public Builder addNameInfos(NameInfo.Builder builder) {
                RepeatedFieldBuilderV3<NameInfo, NameInfo.Builder, NameInfoOrBuilder> repeatedFieldBuilderV3 = this.nameInfosBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureNameInfosIsMutable();
                    this.nameInfos_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addNameInfos(int i, NameInfo.Builder builder) {
                RepeatedFieldBuilderV3<NameInfo, NameInfo.Builder, NameInfoOrBuilder> repeatedFieldBuilderV3 = this.nameInfosBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureNameInfosIsMutable();
                    this.nameInfos_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllNameInfos(Iterable<? extends NameInfo> iterable) {
                RepeatedFieldBuilderV3<NameInfo, NameInfo.Builder, NameInfoOrBuilder> repeatedFieldBuilderV3 = this.nameInfosBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureNameInfosIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.nameInfos_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearNameInfos() {
                RepeatedFieldBuilderV3<NameInfo, NameInfo.Builder, NameInfoOrBuilder> repeatedFieldBuilderV3 = this.nameInfosBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.nameInfos_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeNameInfos(int i) {
                RepeatedFieldBuilderV3<NameInfo, NameInfo.Builder, NameInfoOrBuilder> repeatedFieldBuilderV3 = this.nameInfosBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureNameInfosIsMutable();
                    this.nameInfos_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public NameInfo.Builder getNameInfosBuilder(int i) {
                return (NameInfo.Builder) getNameInfosFieldBuilder().getBuilder(i);
            }

            @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
            public NameInfoOrBuilder getNameInfosOrBuilder(int i) {
                RepeatedFieldBuilderV3<NameInfo, NameInfo.Builder, NameInfoOrBuilder> repeatedFieldBuilderV3 = this.nameInfosBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.nameInfos_.get(i);
                }
                return (NameInfoOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // na.pb.DataCollectionOuterClass.CalculateRouteEndOrBuilder
            public List<? extends NameInfoOrBuilder> getNameInfosOrBuilderList() {
                RepeatedFieldBuilderV3<NameInfo, NameInfo.Builder, NameInfoOrBuilder> repeatedFieldBuilderV3 = this.nameInfosBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.nameInfos_);
            }

            public NameInfo.Builder addNameInfosBuilder() {
                return (NameInfo.Builder) getNameInfosFieldBuilder().addBuilder(NameInfo.getDefaultInstance());
            }

            public NameInfo.Builder addNameInfosBuilder(int i) {
                return (NameInfo.Builder) getNameInfosFieldBuilder().addBuilder(i, NameInfo.getDefaultInstance());
            }

            public List<NameInfo.Builder> getNameInfosBuilderList() {
                return getNameInfosFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<NameInfo, NameInfo.Builder, NameInfoOrBuilder> getNameInfosFieldBuilder() {
                if (this.nameInfosBuilder_ == null) {
                    this.nameInfosBuilder_ = new RepeatedFieldBuilderV3<>(this.nameInfos_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                    this.nameInfos_ = null;
                }
                return this.nameInfosBuilder_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static CalculateRouteEnd getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CalculateRouteEnd> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<CalculateRouteEnd> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public CalculateRouteEnd getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class RegionInfo extends GeneratedMessageV3 implements RegionInfoOrBuilder {
        private static final RegionInfo DEFAULT_INSTANCE = new RegionInfo();
        private static final Parser<RegionInfo> PARSER = new AbstractParser<RegionInfo>() { // from class: na.pb.DataCollectionOuterClass.RegionInfo.1
            @Override // com.google.protobuf.Parser
            public RegionInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new RegionInfo(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PRODUCTNAME_FIELD_NUMBER = 1;
        public static final int REGIONID_FIELD_NUMBER = 3;
        public static final int REGIONNAME_FIELD_NUMBER = 2;
        public static final int VERSIONID_FIELD_NUMBER = 4;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private volatile Object productName_;
        private int regionId_;
        private volatile Object regionName_;
        private volatile Object versionId_;

        private RegionInfo(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private RegionInfo() {
            this.memoizedIsInitialized = (byte) -1;
            this.productName_ = "";
            this.regionName_ = "";
            this.versionId_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new RegionInfo();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private RegionInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 10) {
                                    this.productName_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 18) {
                                    this.regionName_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 24) {
                                    this.regionId_ = codedInputStream.readUInt32();
                                } else if (tag == 34) {
                                    this.versionId_ = codedInputStream.readStringRequireUtf8();
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (UninitializedMessageException e2) {
                            throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(this);
                        }
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DataCollectionOuterClass.internal_static_na_pb_RegionInfo_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DataCollectionOuterClass.internal_static_na_pb_RegionInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(RegionInfo.class, Builder.class);
        }

        @Override // na.pb.DataCollectionOuterClass.RegionInfoOrBuilder
        public String getProductName() {
            Object obj = this.productName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.productName_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.RegionInfoOrBuilder
        public ByteString getProductNameBytes() {
            Object obj = this.productName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.productName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // na.pb.DataCollectionOuterClass.RegionInfoOrBuilder
        public String getRegionName() {
            Object obj = this.regionName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.regionName_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.RegionInfoOrBuilder
        public ByteString getRegionNameBytes() {
            Object obj = this.regionName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.regionName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // na.pb.DataCollectionOuterClass.RegionInfoOrBuilder
        public int getRegionId() {
            return this.regionId_;
        }

        @Override // na.pb.DataCollectionOuterClass.RegionInfoOrBuilder
        public String getVersionId() {
            Object obj = this.versionId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.versionId_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.RegionInfoOrBuilder
        public ByteString getVersionIdBytes() {
            Object obj = this.versionId_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.versionId_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!GeneratedMessageV3.isStringEmpty(this.productName_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.productName_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.regionName_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.regionName_);
            }
            int i = this.regionId_;
            if (i != 0) {
                codedOutputStream.writeUInt32(3, i);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.versionId_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 4, this.versionId_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = GeneratedMessageV3.isStringEmpty(this.productName_) ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.productName_);
            if (!GeneratedMessageV3.isStringEmpty(this.regionName_)) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.regionName_);
            }
            int i2 = this.regionId_;
            if (i2 != 0) {
                iComputeStringSize += CodedOutputStream.computeUInt32Size(3, i2);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.versionId_)) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(4, this.versionId_);
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RegionInfo)) {
                return super.equals(obj);
            }
            RegionInfo regionInfo = (RegionInfo) obj;
            return getProductName().equals(regionInfo.getProductName()) && getRegionName().equals(regionInfo.getRegionName()) && getRegionId() == regionInfo.getRegionId() && getVersionId().equals(regionInfo.getVersionId()) && this.unknownFields.equals(regionInfo.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getProductName().hashCode()) * 37) + 2) * 53) + getRegionName().hashCode()) * 37) + 3) * 53) + getRegionId()) * 37) + 4) * 53) + getVersionId().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        public static RegionInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static RegionInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static RegionInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static RegionInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static RegionInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static RegionInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static RegionInfo parseFrom(InputStream inputStream) throws IOException {
            return (RegionInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static RegionInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RegionInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RegionInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RegionInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static RegionInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RegionInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static RegionInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RegionInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static RegionInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RegionInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RegionInfo regionInfo) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(regionInfo);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RegionInfoOrBuilder {
            private Object productName_;
            private int regionId_;
            private Object regionName_;
            private Object versionId_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DataCollectionOuterClass.internal_static_na_pb_RegionInfo_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DataCollectionOuterClass.internal_static_na_pb_RegionInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(RegionInfo.class, Builder.class);
            }

            private Builder() {
                this.productName_ = "";
                this.regionName_ = "";
                this.versionId_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.productName_ = "";
                this.regionName_ = "";
                this.versionId_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RegionInfo.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.productName_ = "";
                this.regionName_ = "";
                this.regionId_ = 0;
                this.versionId_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DataCollectionOuterClass.internal_static_na_pb_RegionInfo_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public RegionInfo getDefaultInstanceForType() {
                return RegionInfo.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public RegionInfo build() {
                RegionInfo regionInfoBuildPartial = buildPartial();
                if (regionInfoBuildPartial.isInitialized()) {
                    return regionInfoBuildPartial;
                }
                throw newUninitializedMessageException((Message) regionInfoBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public RegionInfo buildPartial() {
                RegionInfo regionInfo = new RegionInfo(this);
                regionInfo.productName_ = this.productName_;
                regionInfo.regionName_ = this.regionName_;
                regionInfo.regionId_ = this.regionId_;
                regionInfo.versionId_ = this.versionId_;
                onBuilt();
                return regionInfo;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo93clone() {
                return (Builder) super.mo93clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof RegionInfo) {
                    return mergeFrom((RegionInfo) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(RegionInfo regionInfo) {
                if (regionInfo == RegionInfo.getDefaultInstance()) {
                    return this;
                }
                if (!regionInfo.getProductName().isEmpty()) {
                    this.productName_ = regionInfo.productName_;
                    onChanged();
                }
                if (!regionInfo.getRegionName().isEmpty()) {
                    this.regionName_ = regionInfo.regionName_;
                    onChanged();
                }
                if (regionInfo.getRegionId() != 0) {
                    setRegionId(regionInfo.getRegionId());
                }
                if (!regionInfo.getVersionId().isEmpty()) {
                    this.versionId_ = regionInfo.versionId_;
                    onChanged();
                }
                mergeUnknownFields(regionInfo.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public na.pb.DataCollectionOuterClass.RegionInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = na.pb.DataCollectionOuterClass.RegionInfo.access$13600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    na.pb.DataCollectionOuterClass$RegionInfo r3 = (na.pb.DataCollectionOuterClass.RegionInfo) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    na.pb.DataCollectionOuterClass$RegionInfo r4 = (na.pb.DataCollectionOuterClass.RegionInfo) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: na.pb.DataCollectionOuterClass.RegionInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):na.pb.DataCollectionOuterClass$RegionInfo$Builder");
            }

            @Override // na.pb.DataCollectionOuterClass.RegionInfoOrBuilder
            public String getProductName() {
                Object obj = this.productName_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.productName_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.RegionInfoOrBuilder
            public ByteString getProductNameBytes() {
                Object obj = this.productName_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.productName_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setProductName(String str) {
                str.getClass();
                this.productName_ = str;
                onChanged();
                return this;
            }

            public Builder clearProductName() {
                this.productName_ = RegionInfo.getDefaultInstance().getProductName();
                onChanged();
                return this;
            }

            public Builder setProductNameBytes(ByteString byteString) {
                byteString.getClass();
                RegionInfo.checkByteStringIsUtf8(byteString);
                this.productName_ = byteString;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.RegionInfoOrBuilder
            public String getRegionName() {
                Object obj = this.regionName_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.regionName_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.RegionInfoOrBuilder
            public ByteString getRegionNameBytes() {
                Object obj = this.regionName_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.regionName_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setRegionName(String str) {
                str.getClass();
                this.regionName_ = str;
                onChanged();
                return this;
            }

            public Builder clearRegionName() {
                this.regionName_ = RegionInfo.getDefaultInstance().getRegionName();
                onChanged();
                return this;
            }

            public Builder setRegionNameBytes(ByteString byteString) {
                byteString.getClass();
                RegionInfo.checkByteStringIsUtf8(byteString);
                this.regionName_ = byteString;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.RegionInfoOrBuilder
            public int getRegionId() {
                return this.regionId_;
            }

            public Builder setRegionId(int i) {
                this.regionId_ = i;
                onChanged();
                return this;
            }

            public Builder clearRegionId() {
                this.regionId_ = 0;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.RegionInfoOrBuilder
            public String getVersionId() {
                Object obj = this.versionId_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.versionId_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.RegionInfoOrBuilder
            public ByteString getVersionIdBytes() {
                Object obj = this.versionId_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.versionId_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setVersionId(String str) {
                str.getClass();
                this.versionId_ = str;
                onChanged();
                return this;
            }

            public Builder clearVersionId() {
                this.versionId_ = RegionInfo.getDefaultInstance().getVersionId();
                onChanged();
                return this;
            }

            public Builder setVersionIdBytes(ByteString byteString) {
                byteString.getClass();
                RegionInfo.checkByteStringIsUtf8(byteString);
                this.versionId_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static RegionInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RegionInfo> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<RegionInfo> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public RegionInfo getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class MapUpdateStart extends GeneratedMessageV3 implements MapUpdateStartOrBuilder {
        public static final int CURRENTMAPVERSION_FIELD_NUMBER = 2;
        public static final int MAPVERSION_FIELD_NUMBER = 5;
        public static final int REGIONCOUNT_FIELD_NUMBER = 6;
        public static final int REGIONINFO_FIELD_NUMBER = 7;
        public static final int TIMESTAMP_FIELD_NUMBER = 1;
        public static final int UPDATESOURCE_FIELD_NUMBER = 4;
        public static final int UPDATETYPE_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private volatile Object currentMapVersion_;
        private volatile Object mapVersion_;
        private byte memoizedIsInitialized;
        private int regionCount_;
        private List<RegionInfo> regionInfo_;
        private volatile Object timeStamp_;
        private int updateSource_;
        private int updateType_;
        private static final MapUpdateStart DEFAULT_INSTANCE = new MapUpdateStart();
        private static final Parser<MapUpdateStart> PARSER = new AbstractParser<MapUpdateStart>() { // from class: na.pb.DataCollectionOuterClass.MapUpdateStart.1
            @Override // com.google.protobuf.Parser
            public MapUpdateStart parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new MapUpdateStart(codedInputStream, extensionRegistryLite);
            }
        };

        private MapUpdateStart(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private MapUpdateStart() {
            this.memoizedIsInitialized = (byte) -1;
            this.timeStamp_ = "";
            this.currentMapVersion_ = "";
            this.updateType_ = 0;
            this.updateSource_ = 0;
            this.mapVersion_ = "";
            this.regionInfo_ = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new MapUpdateStart();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private MapUpdateStart(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 10) {
                                    this.timeStamp_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 18) {
                                    this.currentMapVersion_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 24) {
                                    this.updateType_ = codedInputStream.readEnum();
                                } else if (tag == 32) {
                                    this.updateSource_ = codedInputStream.readEnum();
                                } else if (tag == 42) {
                                    this.mapVersion_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 48) {
                                    this.regionCount_ = codedInputStream.readUInt32();
                                } else if (tag == 58) {
                                    if (!(z2 & true)) {
                                        this.regionInfo_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.regionInfo_.add((RegionInfo) codedInputStream.readMessage(RegionInfo.parser(), extensionRegistryLite));
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (UninitializedMessageException e2) {
                            throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(this);
                        }
                    } catch (IOException e3) {
                        throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.regionInfo_ = Collections.unmodifiableList(this.regionInfo_);
                    }
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DataCollectionOuterClass.internal_static_na_pb_MapUpdateStart_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DataCollectionOuterClass.internal_static_na_pb_MapUpdateStart_fieldAccessorTable.ensureFieldAccessorsInitialized(MapUpdateStart.class, Builder.class);
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
        public String getTimeStamp() {
            Object obj = this.timeStamp_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.timeStamp_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
        public ByteString getTimeStampBytes() {
            Object obj = this.timeStamp_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.timeStamp_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
        public String getCurrentMapVersion() {
            Object obj = this.currentMapVersion_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.currentMapVersion_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
        public ByteString getCurrentMapVersionBytes() {
            Object obj = this.currentMapVersion_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.currentMapVersion_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
        public int getUpdateTypeValue() {
            return this.updateType_;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
        public UpdateType getUpdateType() {
            UpdateType updateTypeValueOf = UpdateType.valueOf(this.updateType_);
            return updateTypeValueOf == null ? UpdateType.UNRECOGNIZED : updateTypeValueOf;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
        public int getUpdateSourceValue() {
            return this.updateSource_;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
        public UpdateSource getUpdateSource() {
            UpdateSource updateSourceValueOf = UpdateSource.valueOf(this.updateSource_);
            return updateSourceValueOf == null ? UpdateSource.UNRECOGNIZED : updateSourceValueOf;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
        public String getMapVersion() {
            Object obj = this.mapVersion_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.mapVersion_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
        public ByteString getMapVersionBytes() {
            Object obj = this.mapVersion_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.mapVersion_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
        public int getRegionCount() {
            return this.regionCount_;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
        public List<RegionInfo> getRegionInfoList() {
            return this.regionInfo_;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
        public List<? extends RegionInfoOrBuilder> getRegionInfoOrBuilderList() {
            return this.regionInfo_;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
        public int getRegionInfoCount() {
            return this.regionInfo_.size();
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
        public RegionInfo getRegionInfo(int i) {
            return this.regionInfo_.get(i);
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
        public RegionInfoOrBuilder getRegionInfoOrBuilder(int i) {
            return this.regionInfo_.get(i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!GeneratedMessageV3.isStringEmpty(this.timeStamp_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.timeStamp_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.currentMapVersion_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.currentMapVersion_);
            }
            if (this.updateType_ != UpdateType.UPDATETYPE_RESERVE.getNumber()) {
                codedOutputStream.writeEnum(3, this.updateType_);
            }
            if (this.updateSource_ != UpdateSource.UPDATESOURCE_RESERVE.getNumber()) {
                codedOutputStream.writeEnum(4, this.updateSource_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.mapVersion_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 5, this.mapVersion_);
            }
            int i = this.regionCount_;
            if (i != 0) {
                codedOutputStream.writeUInt32(6, i);
            }
            for (int i2 = 0; i2 < this.regionInfo_.size(); i2++) {
                codedOutputStream.writeMessage(7, this.regionInfo_.get(i2));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = !GeneratedMessageV3.isStringEmpty(this.timeStamp_) ? GeneratedMessageV3.computeStringSize(1, this.timeStamp_) + 0 : 0;
            if (!GeneratedMessageV3.isStringEmpty(this.currentMapVersion_)) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.currentMapVersion_);
            }
            if (this.updateType_ != UpdateType.UPDATETYPE_RESERVE.getNumber()) {
                iComputeStringSize += CodedOutputStream.computeEnumSize(3, this.updateType_);
            }
            if (this.updateSource_ != UpdateSource.UPDATESOURCE_RESERVE.getNumber()) {
                iComputeStringSize += CodedOutputStream.computeEnumSize(4, this.updateSource_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.mapVersion_)) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(5, this.mapVersion_);
            }
            int i2 = this.regionCount_;
            if (i2 != 0) {
                iComputeStringSize += CodedOutputStream.computeUInt32Size(6, i2);
            }
            for (int i3 = 0; i3 < this.regionInfo_.size(); i3++) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(7, this.regionInfo_.get(i3));
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MapUpdateStart)) {
                return super.equals(obj);
            }
            MapUpdateStart mapUpdateStart = (MapUpdateStart) obj;
            return getTimeStamp().equals(mapUpdateStart.getTimeStamp()) && getCurrentMapVersion().equals(mapUpdateStart.getCurrentMapVersion()) && this.updateType_ == mapUpdateStart.updateType_ && this.updateSource_ == mapUpdateStart.updateSource_ && getMapVersion().equals(mapUpdateStart.getMapVersion()) && getRegionCount() == mapUpdateStart.getRegionCount() && getRegionInfoList().equals(mapUpdateStart.getRegionInfoList()) && this.unknownFields.equals(mapUpdateStart.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getTimeStamp().hashCode()) * 37) + 2) * 53) + getCurrentMapVersion().hashCode()) * 37) + 3) * 53) + this.updateType_) * 37) + 4) * 53) + this.updateSource_) * 37) + 5) * 53) + getMapVersion().hashCode()) * 37) + 6) * 53) + getRegionCount();
            if (getRegionInfoCount() > 0) {
                iHashCode = (((iHashCode * 37) + 7) * 53) + getRegionInfoList().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        public static MapUpdateStart parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static MapUpdateStart parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static MapUpdateStart parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static MapUpdateStart parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static MapUpdateStart parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static MapUpdateStart parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static MapUpdateStart parseFrom(InputStream inputStream) throws IOException {
            return (MapUpdateStart) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static MapUpdateStart parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MapUpdateStart) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MapUpdateStart parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (MapUpdateStart) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static MapUpdateStart parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MapUpdateStart) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MapUpdateStart parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (MapUpdateStart) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static MapUpdateStart parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MapUpdateStart) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(MapUpdateStart mapUpdateStart) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(mapUpdateStart);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MapUpdateStartOrBuilder {
            private int bitField0_;
            private Object currentMapVersion_;
            private Object mapVersion_;
            private int regionCount_;
            private RepeatedFieldBuilderV3<RegionInfo, RegionInfo.Builder, RegionInfoOrBuilder> regionInfoBuilder_;
            private List<RegionInfo> regionInfo_;
            private Object timeStamp_;
            private int updateSource_;
            private int updateType_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DataCollectionOuterClass.internal_static_na_pb_MapUpdateStart_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DataCollectionOuterClass.internal_static_na_pb_MapUpdateStart_fieldAccessorTable.ensureFieldAccessorsInitialized(MapUpdateStart.class, Builder.class);
            }

            private Builder() {
                this.timeStamp_ = "";
                this.currentMapVersion_ = "";
                this.updateType_ = 0;
                this.updateSource_ = 0;
                this.mapVersion_ = "";
                this.regionInfo_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.timeStamp_ = "";
                this.currentMapVersion_ = "";
                this.updateType_ = 0;
                this.updateSource_ = 0;
                this.mapVersion_ = "";
                this.regionInfo_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (MapUpdateStart.alwaysUseFieldBuilders) {
                    getRegionInfoFieldBuilder();
                }
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.timeStamp_ = "";
                this.currentMapVersion_ = "";
                this.updateType_ = 0;
                this.updateSource_ = 0;
                this.mapVersion_ = "";
                this.regionCount_ = 0;
                RepeatedFieldBuilderV3<RegionInfo, RegionInfo.Builder, RegionInfoOrBuilder> repeatedFieldBuilderV3 = this.regionInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.regionInfo_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DataCollectionOuterClass.internal_static_na_pb_MapUpdateStart_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MapUpdateStart getDefaultInstanceForType() {
                return MapUpdateStart.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MapUpdateStart build() {
                MapUpdateStart mapUpdateStartBuildPartial = buildPartial();
                if (mapUpdateStartBuildPartial.isInitialized()) {
                    return mapUpdateStartBuildPartial;
                }
                throw newUninitializedMessageException((Message) mapUpdateStartBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MapUpdateStart buildPartial() {
                MapUpdateStart mapUpdateStart = new MapUpdateStart(this);
                mapUpdateStart.timeStamp_ = this.timeStamp_;
                mapUpdateStart.currentMapVersion_ = this.currentMapVersion_;
                mapUpdateStart.updateType_ = this.updateType_;
                mapUpdateStart.updateSource_ = this.updateSource_;
                mapUpdateStart.mapVersion_ = this.mapVersion_;
                mapUpdateStart.regionCount_ = this.regionCount_;
                RepeatedFieldBuilderV3<RegionInfo, RegionInfo.Builder, RegionInfoOrBuilder> repeatedFieldBuilderV3 = this.regionInfoBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    mapUpdateStart.regionInfo_ = repeatedFieldBuilderV3.build();
                } else {
                    if ((this.bitField0_ & 1) != 0) {
                        this.regionInfo_ = Collections.unmodifiableList(this.regionInfo_);
                        this.bitField0_ &= -2;
                    }
                    mapUpdateStart.regionInfo_ = this.regionInfo_;
                }
                onBuilt();
                return mapUpdateStart;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo93clone() {
                return (Builder) super.mo93clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof MapUpdateStart) {
                    return mergeFrom((MapUpdateStart) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(MapUpdateStart mapUpdateStart) {
                if (mapUpdateStart == MapUpdateStart.getDefaultInstance()) {
                    return this;
                }
                if (!mapUpdateStart.getTimeStamp().isEmpty()) {
                    this.timeStamp_ = mapUpdateStart.timeStamp_;
                    onChanged();
                }
                if (!mapUpdateStart.getCurrentMapVersion().isEmpty()) {
                    this.currentMapVersion_ = mapUpdateStart.currentMapVersion_;
                    onChanged();
                }
                if (mapUpdateStart.updateType_ != 0) {
                    setUpdateTypeValue(mapUpdateStart.getUpdateTypeValue());
                }
                if (mapUpdateStart.updateSource_ != 0) {
                    setUpdateSourceValue(mapUpdateStart.getUpdateSourceValue());
                }
                if (!mapUpdateStart.getMapVersion().isEmpty()) {
                    this.mapVersion_ = mapUpdateStart.mapVersion_;
                    onChanged();
                }
                if (mapUpdateStart.getRegionCount() != 0) {
                    setRegionCount(mapUpdateStart.getRegionCount());
                }
                if (this.regionInfoBuilder_ == null) {
                    if (!mapUpdateStart.regionInfo_.isEmpty()) {
                        if (this.regionInfo_.isEmpty()) {
                            this.regionInfo_ = mapUpdateStart.regionInfo_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureRegionInfoIsMutable();
                            this.regionInfo_.addAll(mapUpdateStart.regionInfo_);
                        }
                        onChanged();
                    }
                } else if (!mapUpdateStart.regionInfo_.isEmpty()) {
                    if (!this.regionInfoBuilder_.isEmpty()) {
                        this.regionInfoBuilder_.addAllMessages(mapUpdateStart.regionInfo_);
                    } else {
                        this.regionInfoBuilder_.dispose();
                        this.regionInfoBuilder_ = null;
                        this.regionInfo_ = mapUpdateStart.regionInfo_;
                        this.bitField0_ &= -2;
                        this.regionInfoBuilder_ = MapUpdateStart.alwaysUseFieldBuilders ? getRegionInfoFieldBuilder() : null;
                    }
                }
                mergeUnknownFields(mapUpdateStart.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public na.pb.DataCollectionOuterClass.MapUpdateStart.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = na.pb.DataCollectionOuterClass.MapUpdateStart.access$15600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    na.pb.DataCollectionOuterClass$MapUpdateStart r3 = (na.pb.DataCollectionOuterClass.MapUpdateStart) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    na.pb.DataCollectionOuterClass$MapUpdateStart r4 = (na.pb.DataCollectionOuterClass.MapUpdateStart) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: na.pb.DataCollectionOuterClass.MapUpdateStart.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):na.pb.DataCollectionOuterClass$MapUpdateStart$Builder");
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
            public String getTimeStamp() {
                Object obj = this.timeStamp_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.timeStamp_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
            public ByteString getTimeStampBytes() {
                Object obj = this.timeStamp_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.timeStamp_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setTimeStamp(String str) {
                str.getClass();
                this.timeStamp_ = str;
                onChanged();
                return this;
            }

            public Builder clearTimeStamp() {
                this.timeStamp_ = MapUpdateStart.getDefaultInstance().getTimeStamp();
                onChanged();
                return this;
            }

            public Builder setTimeStampBytes(ByteString byteString) {
                byteString.getClass();
                MapUpdateStart.checkByteStringIsUtf8(byteString);
                this.timeStamp_ = byteString;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
            public String getCurrentMapVersion() {
                Object obj = this.currentMapVersion_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.currentMapVersion_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
            public ByteString getCurrentMapVersionBytes() {
                Object obj = this.currentMapVersion_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.currentMapVersion_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setCurrentMapVersion(String str) {
                str.getClass();
                this.currentMapVersion_ = str;
                onChanged();
                return this;
            }

            public Builder clearCurrentMapVersion() {
                this.currentMapVersion_ = MapUpdateStart.getDefaultInstance().getCurrentMapVersion();
                onChanged();
                return this;
            }

            public Builder setCurrentMapVersionBytes(ByteString byteString) {
                byteString.getClass();
                MapUpdateStart.checkByteStringIsUtf8(byteString);
                this.currentMapVersion_ = byteString;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
            public int getUpdateTypeValue() {
                return this.updateType_;
            }

            public Builder setUpdateTypeValue(int i) {
                this.updateType_ = i;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
            public UpdateType getUpdateType() {
                UpdateType updateTypeValueOf = UpdateType.valueOf(this.updateType_);
                return updateTypeValueOf == null ? UpdateType.UNRECOGNIZED : updateTypeValueOf;
            }

            public Builder setUpdateType(UpdateType updateType) {
                updateType.getClass();
                this.updateType_ = updateType.getNumber();
                onChanged();
                return this;
            }

            public Builder clearUpdateType() {
                this.updateType_ = 0;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
            public int getUpdateSourceValue() {
                return this.updateSource_;
            }

            public Builder setUpdateSourceValue(int i) {
                this.updateSource_ = i;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
            public UpdateSource getUpdateSource() {
                UpdateSource updateSourceValueOf = UpdateSource.valueOf(this.updateSource_);
                return updateSourceValueOf == null ? UpdateSource.UNRECOGNIZED : updateSourceValueOf;
            }

            public Builder setUpdateSource(UpdateSource updateSource) {
                updateSource.getClass();
                this.updateSource_ = updateSource.getNumber();
                onChanged();
                return this;
            }

            public Builder clearUpdateSource() {
                this.updateSource_ = 0;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
            public String getMapVersion() {
                Object obj = this.mapVersion_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.mapVersion_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
            public ByteString getMapVersionBytes() {
                Object obj = this.mapVersion_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.mapVersion_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setMapVersion(String str) {
                str.getClass();
                this.mapVersion_ = str;
                onChanged();
                return this;
            }

            public Builder clearMapVersion() {
                this.mapVersion_ = MapUpdateStart.getDefaultInstance().getMapVersion();
                onChanged();
                return this;
            }

            public Builder setMapVersionBytes(ByteString byteString) {
                byteString.getClass();
                MapUpdateStart.checkByteStringIsUtf8(byteString);
                this.mapVersion_ = byteString;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
            public int getRegionCount() {
                return this.regionCount_;
            }

            public Builder setRegionCount(int i) {
                this.regionCount_ = i;
                onChanged();
                return this;
            }

            public Builder clearRegionCount() {
                this.regionCount_ = 0;
                onChanged();
                return this;
            }

            private void ensureRegionInfoIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.regionInfo_ = new ArrayList(this.regionInfo_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
            public List<RegionInfo> getRegionInfoList() {
                RepeatedFieldBuilderV3<RegionInfo, RegionInfo.Builder, RegionInfoOrBuilder> repeatedFieldBuilderV3 = this.regionInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.regionInfo_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
            public int getRegionInfoCount() {
                RepeatedFieldBuilderV3<RegionInfo, RegionInfo.Builder, RegionInfoOrBuilder> repeatedFieldBuilderV3 = this.regionInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.regionInfo_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
            public RegionInfo getRegionInfo(int i) {
                RepeatedFieldBuilderV3<RegionInfo, RegionInfo.Builder, RegionInfoOrBuilder> repeatedFieldBuilderV3 = this.regionInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.regionInfo_.get(i);
                }
                return (RegionInfo) repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setRegionInfo(int i, RegionInfo regionInfo) {
                RepeatedFieldBuilderV3<RegionInfo, RegionInfo.Builder, RegionInfoOrBuilder> repeatedFieldBuilderV3 = this.regionInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    regionInfo.getClass();
                    ensureRegionInfoIsMutable();
                    this.regionInfo_.set(i, regionInfo);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, regionInfo);
                }
                return this;
            }

            public Builder setRegionInfo(int i, RegionInfo.Builder builder) {
                RepeatedFieldBuilderV3<RegionInfo, RegionInfo.Builder, RegionInfoOrBuilder> repeatedFieldBuilderV3 = this.regionInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureRegionInfoIsMutable();
                    this.regionInfo_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addRegionInfo(RegionInfo regionInfo) {
                RepeatedFieldBuilderV3<RegionInfo, RegionInfo.Builder, RegionInfoOrBuilder> repeatedFieldBuilderV3 = this.regionInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    regionInfo.getClass();
                    ensureRegionInfoIsMutable();
                    this.regionInfo_.add(regionInfo);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(regionInfo);
                }
                return this;
            }

            public Builder addRegionInfo(int i, RegionInfo regionInfo) {
                RepeatedFieldBuilderV3<RegionInfo, RegionInfo.Builder, RegionInfoOrBuilder> repeatedFieldBuilderV3 = this.regionInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    regionInfo.getClass();
                    ensureRegionInfoIsMutable();
                    this.regionInfo_.add(i, regionInfo);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, regionInfo);
                }
                return this;
            }

            public Builder addRegionInfo(RegionInfo.Builder builder) {
                RepeatedFieldBuilderV3<RegionInfo, RegionInfo.Builder, RegionInfoOrBuilder> repeatedFieldBuilderV3 = this.regionInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureRegionInfoIsMutable();
                    this.regionInfo_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.build());
                }
                return this;
            }

            public Builder addRegionInfo(int i, RegionInfo.Builder builder) {
                RepeatedFieldBuilderV3<RegionInfo, RegionInfo.Builder, RegionInfoOrBuilder> repeatedFieldBuilderV3 = this.regionInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureRegionInfoIsMutable();
                    this.regionInfo_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllRegionInfo(Iterable<? extends RegionInfo> iterable) {
                RepeatedFieldBuilderV3<RegionInfo, RegionInfo.Builder, RegionInfoOrBuilder> repeatedFieldBuilderV3 = this.regionInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureRegionInfoIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.regionInfo_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearRegionInfo() {
                RepeatedFieldBuilderV3<RegionInfo, RegionInfo.Builder, RegionInfoOrBuilder> repeatedFieldBuilderV3 = this.regionInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.regionInfo_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeRegionInfo(int i) {
                RepeatedFieldBuilderV3<RegionInfo, RegionInfo.Builder, RegionInfoOrBuilder> repeatedFieldBuilderV3 = this.regionInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureRegionInfoIsMutable();
                    this.regionInfo_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public RegionInfo.Builder getRegionInfoBuilder(int i) {
                return (RegionInfo.Builder) getRegionInfoFieldBuilder().getBuilder(i);
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
            public RegionInfoOrBuilder getRegionInfoOrBuilder(int i) {
                RepeatedFieldBuilderV3<RegionInfo, RegionInfo.Builder, RegionInfoOrBuilder> repeatedFieldBuilderV3 = this.regionInfoBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.regionInfo_.get(i);
                }
                return (RegionInfoOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateStartOrBuilder
            public List<? extends RegionInfoOrBuilder> getRegionInfoOrBuilderList() {
                RepeatedFieldBuilderV3<RegionInfo, RegionInfo.Builder, RegionInfoOrBuilder> repeatedFieldBuilderV3 = this.regionInfoBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.regionInfo_);
            }

            public RegionInfo.Builder addRegionInfoBuilder() {
                return (RegionInfo.Builder) getRegionInfoFieldBuilder().addBuilder(RegionInfo.getDefaultInstance());
            }

            public RegionInfo.Builder addRegionInfoBuilder(int i) {
                return (RegionInfo.Builder) getRegionInfoFieldBuilder().addBuilder(i, RegionInfo.getDefaultInstance());
            }

            public List<RegionInfo.Builder> getRegionInfoBuilderList() {
                return getRegionInfoFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<RegionInfo, RegionInfo.Builder, RegionInfoOrBuilder> getRegionInfoFieldBuilder() {
                if (this.regionInfoBuilder_ == null) {
                    this.regionInfoBuilder_ = new RepeatedFieldBuilderV3<>(this.regionInfo_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                    this.regionInfo_ = null;
                }
                return this.regionInfoBuilder_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static MapUpdateStart getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<MapUpdateStart> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<MapUpdateStart> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MapUpdateStart getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class MapUpdateEnd extends GeneratedMessageV3 implements MapUpdateEndOrBuilder {
        private static final MapUpdateEnd DEFAULT_INSTANCE = new MapUpdateEnd();
        private static final Parser<MapUpdateEnd> PARSER = new AbstractParser<MapUpdateEnd>() { // from class: na.pb.DataCollectionOuterClass.MapUpdateEnd.1
            @Override // com.google.protobuf.Parser
            public MapUpdateEnd parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new MapUpdateEnd(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int TIMESTAMP_FIELD_NUMBER = 1;
        public static final int UPDATERESULT_FIELD_NUMBER = 4;
        public static final int UPDATESOURCE_FIELD_NUMBER = 3;
        public static final int UPDATESTATUS_FIELD_NUMBER = 5;
        public static final int UPDATETYPE_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private volatile Object timeStamp_;
        private int updateResult_;
        private int updateSource_;
        private int updateStatus_;
        private int updateType_;

        private MapUpdateEnd(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private MapUpdateEnd() {
            this.memoizedIsInitialized = (byte) -1;
            this.timeStamp_ = "";
            this.updateType_ = 0;
            this.updateSource_ = 0;
            this.updateResult_ = 0;
            this.updateStatus_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new MapUpdateEnd();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private MapUpdateEnd(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 10) {
                                    this.timeStamp_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 16) {
                                    this.updateType_ = codedInputStream.readEnum();
                                } else if (tag == 24) {
                                    this.updateSource_ = codedInputStream.readEnum();
                                } else if (tag == 32) {
                                    this.updateResult_ = codedInputStream.readEnum();
                                } else if (tag == 40) {
                                    this.updateStatus_ = codedInputStream.readEnum();
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            }
                            z = true;
                        } catch (UninitializedMessageException e) {
                            throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e3) {
                        throw e3.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DataCollectionOuterClass.internal_static_na_pb_MapUpdateEnd_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DataCollectionOuterClass.internal_static_na_pb_MapUpdateEnd_fieldAccessorTable.ensureFieldAccessorsInitialized(MapUpdateEnd.class, Builder.class);
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateEndOrBuilder
        public String getTimeStamp() {
            Object obj = this.timeStamp_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.timeStamp_ = stringUtf8;
            return stringUtf8;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateEndOrBuilder
        public ByteString getTimeStampBytes() {
            Object obj = this.timeStamp_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.timeStamp_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateEndOrBuilder
        public int getUpdateTypeValue() {
            return this.updateType_;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateEndOrBuilder
        public UpdateType getUpdateType() {
            UpdateType updateTypeValueOf = UpdateType.valueOf(this.updateType_);
            return updateTypeValueOf == null ? UpdateType.UNRECOGNIZED : updateTypeValueOf;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateEndOrBuilder
        public int getUpdateSourceValue() {
            return this.updateSource_;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateEndOrBuilder
        public UpdateSource getUpdateSource() {
            UpdateSource updateSourceValueOf = UpdateSource.valueOf(this.updateSource_);
            return updateSourceValueOf == null ? UpdateSource.UNRECOGNIZED : updateSourceValueOf;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateEndOrBuilder
        public int getUpdateResultValue() {
            return this.updateResult_;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateEndOrBuilder
        public UpdateResult getUpdateResult() {
            UpdateResult updateResultValueOf = UpdateResult.valueOf(this.updateResult_);
            return updateResultValueOf == null ? UpdateResult.UNRECOGNIZED : updateResultValueOf;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateEndOrBuilder
        public int getUpdateStatusValue() {
            return this.updateStatus_;
        }

        @Override // na.pb.DataCollectionOuterClass.MapUpdateEndOrBuilder
        public UpdateStatus getUpdateStatus() {
            UpdateStatus updateStatusValueOf = UpdateStatus.valueOf(this.updateStatus_);
            return updateStatusValueOf == null ? UpdateStatus.UNRECOGNIZED : updateStatusValueOf;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!GeneratedMessageV3.isStringEmpty(this.timeStamp_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.timeStamp_);
            }
            if (this.updateType_ != UpdateType.UPDATETYPE_RESERVE.getNumber()) {
                codedOutputStream.writeEnum(2, this.updateType_);
            }
            if (this.updateSource_ != UpdateSource.UPDATESOURCE_RESERVE.getNumber()) {
                codedOutputStream.writeEnum(3, this.updateSource_);
            }
            if (this.updateResult_ != UpdateResult.UPDATERESULT_RESERVE.getNumber()) {
                codedOutputStream.writeEnum(4, this.updateResult_);
            }
            if (this.updateStatus_ != UpdateStatus.UPDATESTATUS_RESERVE.getNumber()) {
                codedOutputStream.writeEnum(5, this.updateStatus_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = GeneratedMessageV3.isStringEmpty(this.timeStamp_) ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.timeStamp_);
            if (this.updateType_ != UpdateType.UPDATETYPE_RESERVE.getNumber()) {
                iComputeStringSize += CodedOutputStream.computeEnumSize(2, this.updateType_);
            }
            if (this.updateSource_ != UpdateSource.UPDATESOURCE_RESERVE.getNumber()) {
                iComputeStringSize += CodedOutputStream.computeEnumSize(3, this.updateSource_);
            }
            if (this.updateResult_ != UpdateResult.UPDATERESULT_RESERVE.getNumber()) {
                iComputeStringSize += CodedOutputStream.computeEnumSize(4, this.updateResult_);
            }
            if (this.updateStatus_ != UpdateStatus.UPDATESTATUS_RESERVE.getNumber()) {
                iComputeStringSize += CodedOutputStream.computeEnumSize(5, this.updateStatus_);
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MapUpdateEnd)) {
                return super.equals(obj);
            }
            MapUpdateEnd mapUpdateEnd = (MapUpdateEnd) obj;
            return getTimeStamp().equals(mapUpdateEnd.getTimeStamp()) && this.updateType_ == mapUpdateEnd.updateType_ && this.updateSource_ == mapUpdateEnd.updateSource_ && this.updateResult_ == mapUpdateEnd.updateResult_ && this.updateStatus_ == mapUpdateEnd.updateStatus_ && this.unknownFields.equals(mapUpdateEnd.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getTimeStamp().hashCode()) * 37) + 2) * 53) + this.updateType_) * 37) + 3) * 53) + this.updateSource_) * 37) + 4) * 53) + this.updateResult_) * 37) + 5) * 53) + this.updateStatus_) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        public static MapUpdateEnd parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static MapUpdateEnd parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static MapUpdateEnd parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static MapUpdateEnd parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static MapUpdateEnd parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static MapUpdateEnd parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static MapUpdateEnd parseFrom(InputStream inputStream) throws IOException {
            return (MapUpdateEnd) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static MapUpdateEnd parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MapUpdateEnd) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MapUpdateEnd parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (MapUpdateEnd) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static MapUpdateEnd parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MapUpdateEnd) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MapUpdateEnd parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (MapUpdateEnd) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static MapUpdateEnd parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MapUpdateEnd) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(MapUpdateEnd mapUpdateEnd) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(mapUpdateEnd);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MapUpdateEndOrBuilder {
            private Object timeStamp_;
            private int updateResult_;
            private int updateSource_;
            private int updateStatus_;
            private int updateType_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return DataCollectionOuterClass.internal_static_na_pb_MapUpdateEnd_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DataCollectionOuterClass.internal_static_na_pb_MapUpdateEnd_fieldAccessorTable.ensureFieldAccessorsInitialized(MapUpdateEnd.class, Builder.class);
            }

            private Builder() {
                this.timeStamp_ = "";
                this.updateType_ = 0;
                this.updateSource_ = 0;
                this.updateResult_ = 0;
                this.updateStatus_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.timeStamp_ = "";
                this.updateType_ = 0;
                this.updateSource_ = 0;
                this.updateResult_ = 0;
                this.updateStatus_ = 0;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = MapUpdateEnd.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.timeStamp_ = "";
                this.updateType_ = 0;
                this.updateSource_ = 0;
                this.updateResult_ = 0;
                this.updateStatus_ = 0;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DataCollectionOuterClass.internal_static_na_pb_MapUpdateEnd_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MapUpdateEnd getDefaultInstanceForType() {
                return MapUpdateEnd.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MapUpdateEnd build() {
                MapUpdateEnd mapUpdateEndBuildPartial = buildPartial();
                if (mapUpdateEndBuildPartial.isInitialized()) {
                    return mapUpdateEndBuildPartial;
                }
                throw newUninitializedMessageException((Message) mapUpdateEndBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MapUpdateEnd buildPartial() {
                MapUpdateEnd mapUpdateEnd = new MapUpdateEnd(this);
                mapUpdateEnd.timeStamp_ = this.timeStamp_;
                mapUpdateEnd.updateType_ = this.updateType_;
                mapUpdateEnd.updateSource_ = this.updateSource_;
                mapUpdateEnd.updateResult_ = this.updateResult_;
                mapUpdateEnd.updateStatus_ = this.updateStatus_;
                onBuilt();
                return mapUpdateEnd;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo93clone() {
                return (Builder) super.mo93clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof MapUpdateEnd) {
                    return mergeFrom((MapUpdateEnd) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(MapUpdateEnd mapUpdateEnd) {
                if (mapUpdateEnd == MapUpdateEnd.getDefaultInstance()) {
                    return this;
                }
                if (!mapUpdateEnd.getTimeStamp().isEmpty()) {
                    this.timeStamp_ = mapUpdateEnd.timeStamp_;
                    onChanged();
                }
                if (mapUpdateEnd.updateType_ != 0) {
                    setUpdateTypeValue(mapUpdateEnd.getUpdateTypeValue());
                }
                if (mapUpdateEnd.updateSource_ != 0) {
                    setUpdateSourceValue(mapUpdateEnd.getUpdateSourceValue());
                }
                if (mapUpdateEnd.updateResult_ != 0) {
                    setUpdateResultValue(mapUpdateEnd.getUpdateResultValue());
                }
                if (mapUpdateEnd.updateStatus_ != 0) {
                    setUpdateStatusValue(mapUpdateEnd.getUpdateStatusValue());
                }
                mergeUnknownFields(mapUpdateEnd.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public na.pb.DataCollectionOuterClass.MapUpdateEnd.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = na.pb.DataCollectionOuterClass.MapUpdateEnd.access$17300()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    na.pb.DataCollectionOuterClass$MapUpdateEnd r3 = (na.pb.DataCollectionOuterClass.MapUpdateEnd) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    na.pb.DataCollectionOuterClass$MapUpdateEnd r4 = (na.pb.DataCollectionOuterClass.MapUpdateEnd) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: na.pb.DataCollectionOuterClass.MapUpdateEnd.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):na.pb.DataCollectionOuterClass$MapUpdateEnd$Builder");
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateEndOrBuilder
            public String getTimeStamp() {
                Object obj = this.timeStamp_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.timeStamp_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateEndOrBuilder
            public ByteString getTimeStampBytes() {
                Object obj = this.timeStamp_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.timeStamp_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setTimeStamp(String str) {
                str.getClass();
                this.timeStamp_ = str;
                onChanged();
                return this;
            }

            public Builder clearTimeStamp() {
                this.timeStamp_ = MapUpdateEnd.getDefaultInstance().getTimeStamp();
                onChanged();
                return this;
            }

            public Builder setTimeStampBytes(ByteString byteString) {
                byteString.getClass();
                MapUpdateEnd.checkByteStringIsUtf8(byteString);
                this.timeStamp_ = byteString;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateEndOrBuilder
            public int getUpdateTypeValue() {
                return this.updateType_;
            }

            public Builder setUpdateTypeValue(int i) {
                this.updateType_ = i;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateEndOrBuilder
            public UpdateType getUpdateType() {
                UpdateType updateTypeValueOf = UpdateType.valueOf(this.updateType_);
                return updateTypeValueOf == null ? UpdateType.UNRECOGNIZED : updateTypeValueOf;
            }

            public Builder setUpdateType(UpdateType updateType) {
                updateType.getClass();
                this.updateType_ = updateType.getNumber();
                onChanged();
                return this;
            }

            public Builder clearUpdateType() {
                this.updateType_ = 0;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateEndOrBuilder
            public int getUpdateSourceValue() {
                return this.updateSource_;
            }

            public Builder setUpdateSourceValue(int i) {
                this.updateSource_ = i;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateEndOrBuilder
            public UpdateSource getUpdateSource() {
                UpdateSource updateSourceValueOf = UpdateSource.valueOf(this.updateSource_);
                return updateSourceValueOf == null ? UpdateSource.UNRECOGNIZED : updateSourceValueOf;
            }

            public Builder setUpdateSource(UpdateSource updateSource) {
                updateSource.getClass();
                this.updateSource_ = updateSource.getNumber();
                onChanged();
                return this;
            }

            public Builder clearUpdateSource() {
                this.updateSource_ = 0;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateEndOrBuilder
            public int getUpdateResultValue() {
                return this.updateResult_;
            }

            public Builder setUpdateResultValue(int i) {
                this.updateResult_ = i;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateEndOrBuilder
            public UpdateResult getUpdateResult() {
                UpdateResult updateResultValueOf = UpdateResult.valueOf(this.updateResult_);
                return updateResultValueOf == null ? UpdateResult.UNRECOGNIZED : updateResultValueOf;
            }

            public Builder setUpdateResult(UpdateResult updateResult) {
                updateResult.getClass();
                this.updateResult_ = updateResult.getNumber();
                onChanged();
                return this;
            }

            public Builder clearUpdateResult() {
                this.updateResult_ = 0;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateEndOrBuilder
            public int getUpdateStatusValue() {
                return this.updateStatus_;
            }

            public Builder setUpdateStatusValue(int i) {
                this.updateStatus_ = i;
                onChanged();
                return this;
            }

            @Override // na.pb.DataCollectionOuterClass.MapUpdateEndOrBuilder
            public UpdateStatus getUpdateStatus() {
                UpdateStatus updateStatusValueOf = UpdateStatus.valueOf(this.updateStatus_);
                return updateStatusValueOf == null ? UpdateStatus.UNRECOGNIZED : updateStatusValueOf;
            }

            public Builder setUpdateStatus(UpdateStatus updateStatus) {
                updateStatus.getClass();
                this.updateStatus_ = updateStatus.getNumber();
                onChanged();
                return this;
            }

            public Builder clearUpdateStatus() {
                this.updateStatus_ = 0;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static MapUpdateEnd getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<MapUpdateEnd> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<MapUpdateEnd> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MapUpdateEnd getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_na_pb_DataCollection_descriptor = descriptor2;
        internal_static_na_pb_DataCollection_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Header", "DataBlob"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        internal_static_na_pb_Header_descriptor = descriptor3;
        internal_static_na_pb_Header_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Oem", "Country", "DeviceId", "CarMode", "CollectTime", "AppVersion", "Language", "UserId", "DbVersion"});
        Descriptors.Descriptor descriptor4 = getDescriptor().getMessageTypes().get(2);
        internal_static_na_pb_DataBlob_descriptor = descriptor4;
        internal_static_na_pb_DataBlob_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"EventComponent", "EventId", "EventType", "DataBlob"});
        Descriptors.Descriptor descriptor5 = getDescriptor().getMessageTypes().get(3);
        internal_static_na_pb_GuidanceStart_descriptor = descriptor5;
        internal_static_na_pb_GuidanceStart_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"TimeStamp", "Eta"});
        Descriptors.Descriptor descriptor6 = getDescriptor().getMessageTypes().get(4);
        internal_static_na_pb_GuidanceEnd_descriptor = descriptor6;
        internal_static_na_pb_GuidanceEnd_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"TimeStamp", "Eta"});
        Descriptors.Descriptor descriptor7 = getDescriptor().getMessageTypes().get(5);
        internal_static_na_pb_NavigationAppStart_descriptor = descriptor7;
        internal_static_na_pb_NavigationAppStart_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"TimeStamp"});
        Descriptors.Descriptor descriptor8 = getDescriptor().getMessageTypes().get(6);
        internal_static_na_pb_NameInfo_descriptor = descriptor8;
        internal_static_na_pb_NameInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[]{"LanguageCode", "Name"});
        Descriptors.Descriptor descriptor9 = getDescriptor().getMessageTypes().get(7);
        internal_static_na_pb_CalculateRouteEnd_descriptor = descriptor9;
        internal_static_na_pb_CalculateRouteEnd_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor9, new String[]{"TimeStamp", "RouteSource", "Lat", "Lon", "DestType", "Address", "PoiSrc", "PoiCategory", "NameInfos"});
        Descriptors.Descriptor descriptor10 = getDescriptor().getMessageTypes().get(8);
        internal_static_na_pb_RegionInfo_descriptor = descriptor10;
        internal_static_na_pb_RegionInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor10, new String[]{"ProductName", "RegionName", "RegionId", "VersionId"});
        Descriptors.Descriptor descriptor11 = getDescriptor().getMessageTypes().get(9);
        internal_static_na_pb_MapUpdateStart_descriptor = descriptor11;
        internal_static_na_pb_MapUpdateStart_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor11, new String[]{"TimeStamp", "CurrentMapVersion", "UpdateType", "UpdateSource", "MapVersion", "RegionCount", "RegionInfo"});
        Descriptors.Descriptor descriptor12 = getDescriptor().getMessageTypes().get(10);
        internal_static_na_pb_MapUpdateEnd_descriptor = descriptor12;
        internal_static_na_pb_MapUpdateEnd_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor12, new String[]{"TimeStamp", "UpdateType", "UpdateSource", "UpdateResult", "UpdateStatus"});
    }
}
