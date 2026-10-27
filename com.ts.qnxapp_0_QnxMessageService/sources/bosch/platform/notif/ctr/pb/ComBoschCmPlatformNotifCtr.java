package bosch.platform.notif.ctr.pb;

import com.autolink.ota.OtaProto;
import com.bosch.cm.housekeeping.pb.susdif.ComBoschCmHousekeepingSusdif;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RepeatedFieldBuilder;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ComBoschCmPlatformNotifCtr {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_bosch_platform_notif_ctr_pb_BuildInfo_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_bosch_platform_notif_ctr_pb_BuildInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_bosch_platform_notif_ctr_pb_CompntHealthStatus_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_bosch_platform_notif_ctr_pb_CompntHealthStatus_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_bosch_platform_notif_ctr_pb_DiagDtcInfo_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_bosch_platform_notif_ctr_pb_DiagDtcInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_bosch_platform_notif_ctr_pb_DispHudRotation_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_bosch_platform_notif_ctr_pb_DispHudRotation_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_bosch_platform_notif_ctr_pb_DispPwrSplyStat_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_bosch_platform_notif_ctr_pb_DispPwrSplyStat_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_bosch_platform_notif_ctr_pb_LcmHmiModeEvents_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_bosch_platform_notif_ctr_pb_LcmHmiModeEvents_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_bosch_platform_notif_ctr_pb_LcmTimingEvents_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_bosch_platform_notif_ctr_pb_LcmTimingEvents_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_bosch_platform_notif_ctr_pb_ModuleInfo_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_bosch_platform_notif_ctr_pb_ModuleInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_bosch_platform_notif_ctr_pb_ModuleStat_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_bosch_platform_notif_ctr_pb_ModuleStat_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_bosch_platform_notif_ctr_pb_MsgLogLevel_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_bosch_platform_notif_ctr_pb_MsgLogLevel_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_bosch_platform_notif_ctr_pb_NtfThermalInfoItem_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_bosch_platform_notif_ctr_pb_NtfThermalInfoItem_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_bosch_platform_notif_ctr_pb_NtfThermalInfoMsg_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_bosch_platform_notif_ctr_pb_NtfThermalInfoMsg_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_bosch_platform_notif_ctr_pb_PsisUpdateCarCfgInfoMap_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_bosch_platform_notif_ctr_pb_PsisUpdateCarCfgInfoMap_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_bosch_platform_notif_ctr_pb_PsisUpdateCarCfgInfo_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_bosch_platform_notif_ctr_pb_PsisUpdateCarCfgInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_bosch_platform_notif_ctr_pb_PwrModeStat_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_bosch_platform_notif_ctr_pb_PwrModeStat_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_bosch_platform_notif_ctr_pb_RtcUpdateEvents_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_bosch_platform_notif_ctr_pb_RtcUpdateEvents_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_bosch_platform_notif_ctr_pb_VehicleInfo_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_bosch_platform_notif_ctr_pb_VehicleInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_bosch_platform_notif_ctr_pb_switchEfuseInfo_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_bosch_platform_notif_ctr_pb_switchEfuseInfo_fieldAccessorTable;

    public interface BuildInfoOrBuilder extends MessageOrBuilder {
        String getBootVer();

        ByteString getBootVerBytes();

        String getBuildDate();

        ByteString getBuildDateBytes();

        String getBuildId();

        ByteString getBuildIdBytes();

        String getBuildNumber();

        ByteString getBuildNumberBytes();

        String getBuildTarget();

        ByteString getBuildTargetBytes();

        String getJobName();

        ByteString getJobNameBytes();

        String getMainVer();

        ByteString getMainVerBytes();

        String getMainVerName();

        ByteString getMainVerNameBytes();

        String getManifestRev();

        ByteString getManifestRevBytes();

        String getSccAppVer();

        ByteString getSccAppVerBytes();

        String getSccBlVer();

        ByteString getSccBlVerBytes();

        String getSccBydAppVer();

        ByteString getSccBydAppVerBytes();

        String getSccBydBlVer();

        ByteString getSccBydBlVerBytes();

        String getSccVariantCode();

        ByteString getSccVariantCodeBytes();

        String getSocAndroidVer();

        ByteString getSocAndroidVerBytes();

        String getSocQnxVer();

        ByteString getSocQnxVerBytes();

        boolean hasBootVer();

        boolean hasBuildDate();

        boolean hasBuildId();

        boolean hasBuildNumber();

        boolean hasBuildTarget();

        boolean hasJobName();

        boolean hasMainVer();

        boolean hasMainVerName();

        boolean hasManifestRev();

        boolean hasSccAppVer();

        boolean hasSccBlVer();

        boolean hasSccBydAppVer();

        boolean hasSccBydBlVer();

        boolean hasSccVariantCode();

        boolean hasSocAndroidVer();

        boolean hasSocQnxVer();
    }

    public interface CompntHealthStatusOrBuilder extends MessageOrBuilder {
        int getProcessedMsg();

        HealthStatus getStatus();

        boolean hasProcessedMsg();

        boolean hasStatus();
    }

    public interface DiagDtcInfoOrBuilder extends MessageOrBuilder {
        int getDtcNumber();

        DiagDtcResult getResult();

        boolean hasDtcNumber();

        boolean hasResult();
    }

    public interface DispHudRotationOrBuilder extends MessageOrBuilder {
        HudRotateDir getDirection();

        int getLevel();

        boolean hasDirection();

        boolean hasLevel();
    }

    public interface DispPwrSplyStatOrBuilder extends MessageOrBuilder {
        PwrSplyStat getClusterStat();

        PwrSplyStat getHudStat();

        PwrSplyStat getHvacStat();

        PwrSplyStat getIviStat();

        boolean hasClusterStat();

        boolean hasHudStat();

        boolean hasHvacStat();

        boolean hasIviStat();
    }

    public interface LcmHmiModeEventsOrBuilder extends MessageOrBuilder {
        NTFLockSts getCarLocked();

        NTFChargingsts getChargingInfo();

        NTFDrvDoorSts getDoorOpened();

        NTFWarningSts getLcdWarning();

        int getTtInfo();

        NTFWarningSts getWarnInfo();

        boolean hasCarLocked();

        boolean hasChargingInfo();

        boolean hasDoorOpened();

        boolean hasLcdWarning();

        boolean hasTtInfo();

        boolean hasWarnInfo();
    }

    public interface LcmTimingEventsOrBuilder extends MessageOrBuilder {
        String getAccoffTime();

        ByteString getAccoffTimeBytes();

        String getAcconTime();

        ByteString getAcconTimeBytes();

        String getAndroidstartTime();

        ByteString getAndroidstartTimeBytes();

        String getEngoffTime();

        ByteString getEngoffTimeBytes();

        String getEngonTime();

        ByteString getEngonTimeBytes();

        String getPoweroffTime();

        ByteString getPoweroffTimeBytes();

        String getSocTime();

        ByteString getSocTimeBytes();

        boolean hasAccoffTime();

        boolean hasAcconTime();

        boolean hasAndroidstartTime();

        boolean hasEngoffTime();

        boolean hasEngonTime();

        boolean hasPoweroffTime();

        boolean hasSocTime();
    }

    public interface ModuleInfoOrBuilder extends MessageOrBuilder {
        String getCompId();

        ByteString getCompIdBytes();

        DomainId getDomain();

        String getFwVer();

        ByteString getFwVerBytes();

        String getHwId();

        ByteString getHwIdBytes();

        String getHwVer();

        ByteString getHwVerBytes();

        String getInstallerType();

        ByteString getInstallerTypeBytes();

        String getLcdTemp();

        ByteString getLcdTempBytes();

        String getName();

        ByteString getNameBytes();

        String getNodeAddr();

        ByteString getNodeAddrBytes();

        String getPcbTemp();

        ByteString getPcbTempBytes();

        String getPn();

        ByteString getPnBytes();

        String getPropVer();

        ByteString getPropVerBytes();

        String getSdbVer();

        ByteString getSdbVerBytes();

        String getSerVer();

        ByteString getSerVerBytes();

        String getSn();

        ByteString getSnBytes();

        String getSupplierInfo();

        ByteString getSupplierInfoBytes();

        String getSwVer();

        ByteString getSwVerBytes();

        String getToolVer();

        ByteString getToolVerBytes();

        boolean hasCompId();

        boolean hasDomain();

        boolean hasFwVer();

        boolean hasHwId();

        boolean hasHwVer();

        boolean hasInstallerType();

        boolean hasLcdTemp();

        boolean hasName();

        boolean hasNodeAddr();

        boolean hasPcbTemp();

        boolean hasPn();

        boolean hasPropVer();

        boolean hasSdbVer();

        boolean hasSerVer();

        boolean hasSn();

        boolean hasSupplierInfo();

        boolean hasSwVer();

        boolean hasToolVer();
    }

    public interface ModuleStatOrBuilder extends MessageOrBuilder {
        float getCpuTemperature();

        DomainId getDomain();

        int getElapsedTime();

        String getName();

        ByteString getNameBytes();

        Status getStatus();

        NtfTempLevel_t getTempLevel();

        float getTemperature();

        float getVoltage();

        boolean hasCpuTemperature();

        boolean hasDomain();

        boolean hasElapsedTime();

        boolean hasName();

        boolean hasStatus();

        boolean hasTempLevel();

        boolean hasTemperature();

        boolean hasVoltage();
    }

    public interface MsgLogLevelOrBuilder extends MessageOrBuilder {
        LogLevel getLevel();

        boolean hasLevel();
    }

    public interface NtfThermalInfoItemOrBuilder extends MessageOrBuilder {
        NtfTempSensor_t getNtftempId();

        NtfTempLevel_t getNtftempLevel();

        String getNtftempName();

        ByteString getNtftempNameBytes();

        float getNtftempValue();

        boolean hasNtftempId();

        boolean hasNtftempLevel();

        boolean hasNtftempName();

        boolean hasNtftempValue();
    }

    public interface NtfThermalInfoMsgOrBuilder extends MessageOrBuilder {
        NtfThermalInfoItem getItems(int i);

        int getItemsCount();

        List<NtfThermalInfoItem> getItemsList();

        NtfThermalInfoItemOrBuilder getItemsOrBuilder(int i);

        List<? extends NtfThermalInfoItemOrBuilder> getItemsOrBuilderList();
    }

    public interface PsisUpdateCarCfgInfoMapOrBuilder extends MessageOrBuilder {
        PsisUpdateCarCfgInfo getCarCfgList(int i);

        int getCarCfgListCount();

        List<PsisUpdateCarCfgInfo> getCarCfgListList();

        PsisUpdateCarCfgInfoOrBuilder getCarCfgListOrBuilder(int i);

        List<? extends PsisUpdateCarCfgInfoOrBuilder> getCarCfgListOrBuilderList();
    }

    public interface PsisUpdateCarCfgInfoOrBuilder extends MessageOrBuilder {
        String getKeyName();

        ByteString getKeyNameBytes();

        ByteString getKeyValueChar();

        boolean hasKeyName();

        boolean hasKeyValueChar();
    }

    public interface PwrModeStatOrBuilder extends MessageOrBuilder {
        NtfIpkPowerMode getIpkPowerMode();

        NtfPowerMode getPowerMode();

        NtfPowerSaveMode getSaveMode();

        NtfVoltageState getVoltageState();

        boolean hasIpkPowerMode();

        boolean hasPowerMode();

        boolean hasSaveMode();

        boolean hasVoltageState();
    }

    public interface RtcUpdateEventsOrBuilder extends MessageOrBuilder {
        int getResult();

        boolean hasResult();
    }

    public interface VehicleInfoOrBuilder extends MessageOrBuilder {
        String getMfr();

        ByteString getMfrBytes();

        String getVehVer();

        ByteString getVehVerBytes();

        String getVin();

        ByteString getVinBytes();

        boolean hasMfr();

        boolean hasVehVer();

        boolean hasVin();
    }

    public interface switchEfuseInfoOrBuilder extends MessageOrBuilder {
        EfuseStatus getEfuseinfo();

        boolean hasEfuseinfo();
    }

    private ComBoschCmPlatformNotifCtr() {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
    }

    public enum DomainId implements ProtocolMessageEnum {
        DOMAIN_UNKNOWN(0, 0),
        DOMAIN_NONE(1, 1),
        DOMAIN_LINUX(2, 2),
        DOMAIN_QNX(3, 3),
        DOMAIN_ANDROID(4, 4),
        DOMAIN_AUTOSAR(5, 5),
        DOMAIN_PERIPHERAL(6, 6);

        public static final int DOMAIN_ANDROID_VALUE = 4;
        public static final int DOMAIN_AUTOSAR_VALUE = 5;
        public static final int DOMAIN_LINUX_VALUE = 2;
        public static final int DOMAIN_NONE_VALUE = 1;
        public static final int DOMAIN_PERIPHERAL_VALUE = 6;
        public static final int DOMAIN_QNX_VALUE = 3;
        public static final int DOMAIN_UNKNOWN_VALUE = 0;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<DomainId> internalValueMap = new Internal.EnumLiteMap<DomainId>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DomainId.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public DomainId findValueByNumber(int number) {
                return DomainId.valueOf(number);
            }
        };
        private static final DomainId[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static DomainId valueOf(int value) {
            switch (value) {
                case 0:
                    return DOMAIN_UNKNOWN;
                case 1:
                    return DOMAIN_NONE;
                case 2:
                    return DOMAIN_LINUX;
                case 3:
                    return DOMAIN_QNX;
                case 4:
                    return DOMAIN_ANDROID;
                case 5:
                    return DOMAIN_AUTOSAR;
                case 6:
                    return DOMAIN_PERIPHERAL;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<DomainId> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.getDescriptor().getEnumTypes().get(0);
        }

        public static DomainId valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        DomainId(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum FdbusModTopicId implements ProtocolMessageEnum {
        TPC_MOD_HW_BRD(0, 0),
        TPC_MOD_HW_BRD_SCC(1, 1),
        TPC_MOD_HW_BRD_SOC(2, 2),
        TPC_MOD_HW_CTR_IVI_DISP(3, 65536),
        TPC_MOD_HW_COD_IVI_DISP(4, TPC_MOD_HW_COD_IVI_DISP_VALUE),
        TPC_MOD_HW_CLUSTER_DISP(5, TPC_MOD_HW_CLUSTER_DISP_VALUE),
        TPC_MOD_HW_HUD_DISP(6, TPC_MOD_HW_HUD_DISP_VALUE),
        TPC_MOD_HW_CTRL_PNL_DISP(7, TPC_MOD_HW_CTRL_PNL_DISP_VALUE),
        TPC_MOD_HW_ETH_SWITCH(8, TPC_MOD_HW_ETH_SWITCH_VALUE),
        TPC_MOD_HW_GNSS(9, TPC_MOD_HW_GNSS_VALUE),
        TPC_MOD_HW_EXT_AMP(10, TPC_MOD_HW_EXT_AMP_VALUE),
        TPC_MOD_HW_A2B(11, TPC_MOD_HW_A2B_VALUE),
        TPC_MOD_HW_SER_TI981(12, TPC_MOD_HW_SER_TI981_VALUE),
        TPC_MOD_HW_SER_TI983(13, TPC_MOD_HW_SER_TI983_VALUE),
        TPC_MOD_SW_DISP(14, 131072),
        TPC_MOD_SW_VEH_SVC(15, TPC_MOD_SW_VEH_SVC_VALUE),
        TPC_MOD_SW_VAR_SVC(16, TPC_MOD_SW_VAR_SVC_VALUE),
        TPC_MOD_SW_LCM(17, TPC_MOD_SW_LCM_VALUE),
        TPC_MOD_SW_DIAG_SERV(18, TPC_MOD_SW_DIAG_SERV_VALUE),
        TPC_MOD_SW_SCC_APP(19, TPC_MOD_SW_SCC_APP_VALUE),
        TPC_MOD_SW_SCC_BL(20, TPC_MOD_SW_SCC_BL_VALUE),
        TPC_MOD_SW_INTERNAL_AVM(21, 196608),
        TPC_MOD_SW_INTERNAL_RVC(22, 196609),
        TPC_MOD_SW_QNX_CLUSTER(23, TPC_MOD_SW_QNX_CLUSTER_VALUE),
        TPC_MOD_SW_SCC_CLUSTER(24, TPC_MOD_SW_SCC_CLUSTER_VALUE),
        TPC_MOD_SW_INTERNAL_HAP(25, TPC_MOD_SW_INTERNAL_HAP_VALUE),
        TPC_MOD_SW_INTERNAL_DMS(26, TPC_MOD_SW_INTERNAL_DMS_VALUE),
        TPC_MOD_SW_INTERNAL_DVR(27, TPC_MOD_SW_INTERNAL_DVR_VALUE),
        TPC_MOD_SW_INTERNAL_OMS(28, TPC_MOD_SW_INTERNAL_OMS_VALUE),
        TPC_MOD_SW_EXTERNAL_OMS(29, TPC_MOD_SW_EXTERNAL_OMS_VALUE),
        TPC_MOD_SW_INTERNAL_IMS(30, TPC_MOD_SW_INTERNAL_IMS_VALUE),
        TPC_MOD_SW_INTERNAL_FACE_ID(31, TPC_MOD_SW_INTERNAL_FACE_ID_VALUE),
        TPC_MOD_SW_INTERNAL_AVM_LEFT(32, 262144),
        TPC_MOD_SW_INTERNAL_AVM_FRONT(33, 262145),
        TPC_MOD_SW_INTERNAL_AVM_REAR(34, 262146),
        TPC_MOD_SW_INTERNAL_AVM_RIGHT(35, 262147);

        public static final int TPC_MOD_HW_A2B_VALUE = 65544;
        public static final int TPC_MOD_HW_BRD_SCC_VALUE = 1;
        public static final int TPC_MOD_HW_BRD_SOC_VALUE = 2;
        public static final int TPC_MOD_HW_BRD_VALUE = 0;
        public static final int TPC_MOD_HW_CLUSTER_DISP_VALUE = 65538;
        public static final int TPC_MOD_HW_COD_IVI_DISP_VALUE = 65537;
        public static final int TPC_MOD_HW_CTRL_PNL_DISP_VALUE = 65540;
        public static final int TPC_MOD_HW_CTR_IVI_DISP_VALUE = 65536;
        public static final int TPC_MOD_HW_ETH_SWITCH_VALUE = 65541;
        public static final int TPC_MOD_HW_EXT_AMP_VALUE = 65543;
        public static final int TPC_MOD_HW_GNSS_VALUE = 65542;
        public static final int TPC_MOD_HW_HUD_DISP_VALUE = 65539;
        public static final int TPC_MOD_HW_SER_TI981_VALUE = 65545;
        public static final int TPC_MOD_HW_SER_TI983_VALUE = 65546;
        public static final int TPC_MOD_SW_DIAG_SERV_VALUE = 131076;
        public static final int TPC_MOD_SW_DISP_VALUE = 131072;
        public static final int TPC_MOD_SW_EXTERNAL_OMS_VALUE = 196616;
        public static final int TPC_MOD_SW_INTERNAL_AVM_FRONT_VALUE = 262145;
        public static final int TPC_MOD_SW_INTERNAL_AVM_LEFT_VALUE = 262144;
        public static final int TPC_MOD_SW_INTERNAL_AVM_REAR_VALUE = 262146;
        public static final int TPC_MOD_SW_INTERNAL_AVM_RIGHT_VALUE = 262147;
        public static final int TPC_MOD_SW_INTERNAL_AVM_VALUE = 196608;
        public static final int TPC_MOD_SW_INTERNAL_DMS_VALUE = 196613;
        public static final int TPC_MOD_SW_INTERNAL_DVR_VALUE = 196614;
        public static final int TPC_MOD_SW_INTERNAL_FACE_ID_VALUE = 196618;
        public static final int TPC_MOD_SW_INTERNAL_HAP_VALUE = 196612;
        public static final int TPC_MOD_SW_INTERNAL_IMS_VALUE = 196617;
        public static final int TPC_MOD_SW_INTERNAL_OMS_VALUE = 196615;
        public static final int TPC_MOD_SW_INTERNAL_RVC_VALUE = 196609;
        public static final int TPC_MOD_SW_LCM_VALUE = 131075;
        public static final int TPC_MOD_SW_QNX_CLUSTER_VALUE = 196610;
        public static final int TPC_MOD_SW_SCC_APP_VALUE = 131077;
        public static final int TPC_MOD_SW_SCC_BL_VALUE = 131078;
        public static final int TPC_MOD_SW_SCC_CLUSTER_VALUE = 196611;
        public static final int TPC_MOD_SW_VAR_SVC_VALUE = 131074;
        public static final int TPC_MOD_SW_VEH_SVC_VALUE = 131073;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<FdbusModTopicId> internalValueMap = new Internal.EnumLiteMap<FdbusModTopicId>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.FdbusModTopicId.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public FdbusModTopicId findValueByNumber(int number) {
                return FdbusModTopicId.valueOf(number);
            }
        };
        private static final FdbusModTopicId[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static FdbusModTopicId valueOf(int value) {
            if (value == 0) {
                return TPC_MOD_HW_BRD;
            }
            if (value == 1) {
                return TPC_MOD_HW_BRD_SCC;
            }
            if (value == 2) {
                return TPC_MOD_HW_BRD_SOC;
            }
            switch (value) {
                case 65536:
                    return TPC_MOD_HW_CTR_IVI_DISP;
                case TPC_MOD_HW_COD_IVI_DISP_VALUE:
                    return TPC_MOD_HW_COD_IVI_DISP;
                case TPC_MOD_HW_CLUSTER_DISP_VALUE:
                    return TPC_MOD_HW_CLUSTER_DISP;
                case TPC_MOD_HW_HUD_DISP_VALUE:
                    return TPC_MOD_HW_HUD_DISP;
                case TPC_MOD_HW_CTRL_PNL_DISP_VALUE:
                    return TPC_MOD_HW_CTRL_PNL_DISP;
                case TPC_MOD_HW_ETH_SWITCH_VALUE:
                    return TPC_MOD_HW_ETH_SWITCH;
                case TPC_MOD_HW_GNSS_VALUE:
                    return TPC_MOD_HW_GNSS;
                case TPC_MOD_HW_EXT_AMP_VALUE:
                    return TPC_MOD_HW_EXT_AMP;
                case TPC_MOD_HW_A2B_VALUE:
                    return TPC_MOD_HW_A2B;
                case TPC_MOD_HW_SER_TI981_VALUE:
                    return TPC_MOD_HW_SER_TI981;
                case TPC_MOD_HW_SER_TI983_VALUE:
                    return TPC_MOD_HW_SER_TI983;
                default:
                    switch (value) {
                        case 131072:
                            return TPC_MOD_SW_DISP;
                        case TPC_MOD_SW_VEH_SVC_VALUE:
                            return TPC_MOD_SW_VEH_SVC;
                        case TPC_MOD_SW_VAR_SVC_VALUE:
                            return TPC_MOD_SW_VAR_SVC;
                        case TPC_MOD_SW_LCM_VALUE:
                            return TPC_MOD_SW_LCM;
                        case TPC_MOD_SW_DIAG_SERV_VALUE:
                            return TPC_MOD_SW_DIAG_SERV;
                        case TPC_MOD_SW_SCC_APP_VALUE:
                            return TPC_MOD_SW_SCC_APP;
                        case TPC_MOD_SW_SCC_BL_VALUE:
                            return TPC_MOD_SW_SCC_BL;
                        default:
                            switch (value) {
                                case 196608:
                                    return TPC_MOD_SW_INTERNAL_AVM;
                                case 196609:
                                    return TPC_MOD_SW_INTERNAL_RVC;
                                case TPC_MOD_SW_QNX_CLUSTER_VALUE:
                                    return TPC_MOD_SW_QNX_CLUSTER;
                                case TPC_MOD_SW_SCC_CLUSTER_VALUE:
                                    return TPC_MOD_SW_SCC_CLUSTER;
                                case TPC_MOD_SW_INTERNAL_HAP_VALUE:
                                    return TPC_MOD_SW_INTERNAL_HAP;
                                case TPC_MOD_SW_INTERNAL_DMS_VALUE:
                                    return TPC_MOD_SW_INTERNAL_DMS;
                                case TPC_MOD_SW_INTERNAL_DVR_VALUE:
                                    return TPC_MOD_SW_INTERNAL_DVR;
                                case TPC_MOD_SW_INTERNAL_OMS_VALUE:
                                    return TPC_MOD_SW_INTERNAL_OMS;
                                case TPC_MOD_SW_EXTERNAL_OMS_VALUE:
                                    return TPC_MOD_SW_EXTERNAL_OMS;
                                case TPC_MOD_SW_INTERNAL_IMS_VALUE:
                                    return TPC_MOD_SW_INTERNAL_IMS;
                                case TPC_MOD_SW_INTERNAL_FACE_ID_VALUE:
                                    return TPC_MOD_SW_INTERNAL_FACE_ID;
                                default:
                                    switch (value) {
                                        case 262144:
                                            return TPC_MOD_SW_INTERNAL_AVM_LEFT;
                                        case 262145:
                                            return TPC_MOD_SW_INTERNAL_AVM_FRONT;
                                        case 262146:
                                            return TPC_MOD_SW_INTERNAL_AVM_REAR;
                                        case 262147:
                                            return TPC_MOD_SW_INTERNAL_AVM_RIGHT;
                                        default:
                                            return null;
                                    }
                            }
                    }
            }
        }

        public static Internal.EnumLiteMap<FdbusModTopicId> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.getDescriptor().getEnumTypes().get(1);
        }

        public static FdbusModTopicId valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        FdbusModTopicId(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum FdbusMessageId implements ProtocolMessageEnum {
        MSG_NOTIF_CTR_BEGIN(0, 0),
        MSG_NOTIF_CTR_END(1, 65535);

        public static final int MSG_NOTIF_CTR_BEGIN_VALUE = 0;
        public static final int MSG_NOTIF_CTR_END_VALUE = 65535;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<FdbusMessageId> internalValueMap = new Internal.EnumLiteMap<FdbusMessageId>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.FdbusMessageId.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public FdbusMessageId findValueByNumber(int number) {
                return FdbusMessageId.valueOf(number);
            }
        };
        private static final FdbusMessageId[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static FdbusMessageId valueOf(int value) {
            if (value == 0) {
                return MSG_NOTIF_CTR_BEGIN;
            }
            if (value == 65535) {
                return MSG_NOTIF_CTR_END;
            }
            return null;
        }

        public static Internal.EnumLiteMap<FdbusMessageId> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.getDescriptor().getEnumTypes().get(2);
        }

        public static FdbusMessageId valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        FdbusMessageId(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum FdbusEventId implements ProtocolMessageEnum {
        EVT_COM_BEGIN(0, 0),
        EVT_COM_MOD_INFO(1, 1),
        EVT_COM_BLD_INFO(2, 2),
        EVT_COM_VEH_INFO(3, 3),
        EVT_COM_MOD_STAT(4, 4),
        EVT_COM_PWR_MOD(5, 5),
        EVT_COM_HEALTH_STAT(6, 6),
        EVT_COM_DTC_INFO(7, 7),
        EVT_COM_LOG_LEVEL(8, 8),
        EVT_COM_SWITCH_EFUSE_INFO(9, 9),
        EVT_COM_END(10, 65535),
        EVT_VAR_SVC_BEGIN(11, 65536),
        EVT_VAR_SVC_END(12, EVT_VAR_SVC_END_VALUE),
        EVT_VEH_SVC_BEGIN(13, 131072),
        EVT_VEH_SVC_END(14, EVT_VEH_SVC_END_VALUE),
        EVT_DISP_MGR_BEGIN(15, 196608),
        EVT_DISP_HUD_ROTATION_INFO(16, 196609),
        EVT_DISP_MGR_END(17, EVT_DISP_MGR_END_VALUE),
        EVT_LCM_BEGIN(18, 262144),
        EVT_LCM_DISP_PWR_SPLY_STAT(19, 262145),
        EVT_LCM_POWER_MODE_STAT(20, 262146),
        EVT_LCM_HMI_MODE_EVENTS(21, 262147),
        EVT_LCM_TIME_TRACK_EVENTS(22, EVT_LCM_TIME_TRACK_EVENTS_VALUE),
        EVT_LCM_RTC_UPDATE_EVENTS(23, EVT_LCM_RTC_UPDATE_EVENTS_VALUE),
        EVT_LCM_FREEZE_MODE_EVENTS(24, EVT_LCM_FREEZE_MODE_EVENTS_VALUE),
        EVT_LCM_END(25, EVT_LCM_END_VALUE),
        EVT_DIAG_SERV_BEGIN(26, EVT_DIAG_SERV_BEGIN_VALUE),
        EVT_DIAG_SERV_END(27, EVT_DIAG_SERV_END_VALUE),
        EVT_PSIS_SERV_BEGIN(28, EVT_PSIS_SERV_BEGIN_VALUE),
        EVT_PSIS_SERV_CAR_CFG_MAP(29, EVT_PSIS_SERV_CAR_CFG_MAP_VALUE),
        EVT_PSIS_SERV_CAR_CFG_ARRAY(30, EVT_PSIS_SERV_CAR_CFG_ARRAY_VALUE),
        EVT_PSIS_SERV_PSIS_CUS_VERSION(31, EVT_PSIS_SERV_PSIS_CUS_VERSION_VALUE),
        EVT_PSIS_SERV_PSIS_BOSCH_VERSION(32, EVT_PSIS_SERV_PSIS_BOSCH_VERSION_VALUE),
        EVT_PSIS_SERV_PSIS_CAL_XDSN(33, EVT_PSIS_SERV_PSIS_CAL_XDSN_VALUE),
        EVT_PSIS_SERV_PSIS_CAL_IHUID(34, EVT_PSIS_SERV_PSIS_CAL_IHUID_VALUE),
        EVT_PSIS_SERV_PSIS_CAL_VIN(35, EVT_PSIS_SERV_PSIS_CAL_VIN_VALUE),
        EVT_PSIS_SERV_END(36, EVT_PSIS_SERV_END_VALUE),
        EVT_THERMAL_BEGIN(37, EVT_THERMAL_BEGIN_VALUE),
        EVT_THERMAL_INFO(38, EVT_THERMAL_INFO_VALUE),
        EVT_THERMAL_END(39, EVT_THERMAL_END_VALUE);

        public static final int EVT_COM_BEGIN_VALUE = 0;
        public static final int EVT_COM_BLD_INFO_VALUE = 2;
        public static final int EVT_COM_DTC_INFO_VALUE = 7;
        public static final int EVT_COM_END_VALUE = 65535;
        public static final int EVT_COM_HEALTH_STAT_VALUE = 6;
        public static final int EVT_COM_LOG_LEVEL_VALUE = 8;
        public static final int EVT_COM_MOD_INFO_VALUE = 1;
        public static final int EVT_COM_MOD_STAT_VALUE = 4;
        public static final int EVT_COM_PWR_MOD_VALUE = 5;
        public static final int EVT_COM_SWITCH_EFUSE_INFO_VALUE = 9;
        public static final int EVT_COM_VEH_INFO_VALUE = 3;
        public static final int EVT_DIAG_SERV_BEGIN_VALUE = 327680;
        public static final int EVT_DIAG_SERV_END_VALUE = 393215;
        public static final int EVT_DISP_HUD_ROTATION_INFO_VALUE = 196609;
        public static final int EVT_DISP_MGR_BEGIN_VALUE = 196608;
        public static final int EVT_DISP_MGR_END_VALUE = 262143;
        public static final int EVT_LCM_BEGIN_VALUE = 262144;
        public static final int EVT_LCM_DISP_PWR_SPLY_STAT_VALUE = 262145;
        public static final int EVT_LCM_END_VALUE = 327679;
        public static final int EVT_LCM_FREEZE_MODE_EVENTS_VALUE = 262150;
        public static final int EVT_LCM_HMI_MODE_EVENTS_VALUE = 262147;
        public static final int EVT_LCM_POWER_MODE_STAT_VALUE = 262146;
        public static final int EVT_LCM_RTC_UPDATE_EVENTS_VALUE = 262149;
        public static final int EVT_LCM_TIME_TRACK_EVENTS_VALUE = 262148;
        public static final int EVT_PSIS_SERV_BEGIN_VALUE = 393216;
        public static final int EVT_PSIS_SERV_CAR_CFG_ARRAY_VALUE = 393218;
        public static final int EVT_PSIS_SERV_CAR_CFG_MAP_VALUE = 393217;
        public static final int EVT_PSIS_SERV_END_VALUE = 458751;
        public static final int EVT_PSIS_SERV_PSIS_BOSCH_VERSION_VALUE = 393220;
        public static final int EVT_PSIS_SERV_PSIS_CAL_IHUID_VALUE = 393222;
        public static final int EVT_PSIS_SERV_PSIS_CAL_VIN_VALUE = 393223;
        public static final int EVT_PSIS_SERV_PSIS_CAL_XDSN_VALUE = 393221;
        public static final int EVT_PSIS_SERV_PSIS_CUS_VERSION_VALUE = 393219;
        public static final int EVT_THERMAL_BEGIN_VALUE = 458752;
        public static final int EVT_THERMAL_END_VALUE = 524287;
        public static final int EVT_THERMAL_INFO_VALUE = 458753;
        public static final int EVT_VAR_SVC_BEGIN_VALUE = 65536;
        public static final int EVT_VAR_SVC_END_VALUE = 131071;
        public static final int EVT_VEH_SVC_BEGIN_VALUE = 131072;
        public static final int EVT_VEH_SVC_END_VALUE = 196607;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<FdbusEventId> internalValueMap = new Internal.EnumLiteMap<FdbusEventId>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.FdbusEventId.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public FdbusEventId findValueByNumber(int number) {
                return FdbusEventId.valueOf(number);
            }
        };
        private static final FdbusEventId[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static FdbusEventId valueOf(int value) {
            if (value == 65535) {
                return EVT_COM_END;
            }
            if (value == 65536) {
                return EVT_VAR_SVC_BEGIN;
            }
            if (value == 131071) {
                return EVT_VAR_SVC_END;
            }
            if (value == 131072) {
                return EVT_VEH_SVC_BEGIN;
            }
            if (value == 327679) {
                return EVT_LCM_END;
            }
            if (value == 327680) {
                return EVT_DIAG_SERV_BEGIN;
            }
            if (value != 524287) {
                switch (value) {
                    case 0:
                        return EVT_COM_BEGIN;
                    case 1:
                        return EVT_COM_MOD_INFO;
                    case 2:
                        return EVT_COM_BLD_INFO;
                    case 3:
                        return EVT_COM_VEH_INFO;
                    case 4:
                        return EVT_COM_MOD_STAT;
                    case 5:
                        return EVT_COM_PWR_MOD;
                    case 6:
                        return EVT_COM_HEALTH_STAT;
                    case 7:
                        return EVT_COM_DTC_INFO;
                    case 8:
                        return EVT_COM_LOG_LEVEL;
                    case 9:
                        return EVT_COM_SWITCH_EFUSE_INFO;
                    default:
                        switch (value) {
                            case EVT_VEH_SVC_END_VALUE:
                                return EVT_VEH_SVC_END;
                            case 196608:
                                return EVT_DISP_MGR_BEGIN;
                            case 196609:
                                return EVT_DISP_HUD_ROTATION_INFO;
                            default:
                                switch (value) {
                                    case EVT_DISP_MGR_END_VALUE:
                                        return EVT_DISP_MGR_END;
                                    case 262144:
                                        return EVT_LCM_BEGIN;
                                    case 262145:
                                        return EVT_LCM_DISP_PWR_SPLY_STAT;
                                    case 262146:
                                        return EVT_LCM_POWER_MODE_STAT;
                                    case 262147:
                                        return EVT_LCM_HMI_MODE_EVENTS;
                                    case EVT_LCM_TIME_TRACK_EVENTS_VALUE:
                                        return EVT_LCM_TIME_TRACK_EVENTS;
                                    case EVT_LCM_RTC_UPDATE_EVENTS_VALUE:
                                        return EVT_LCM_RTC_UPDATE_EVENTS;
                                    case EVT_LCM_FREEZE_MODE_EVENTS_VALUE:
                                        return EVT_LCM_FREEZE_MODE_EVENTS;
                                    default:
                                        switch (value) {
                                            case EVT_DIAG_SERV_END_VALUE:
                                                return EVT_DIAG_SERV_END;
                                            case EVT_PSIS_SERV_BEGIN_VALUE:
                                                return EVT_PSIS_SERV_BEGIN;
                                            case EVT_PSIS_SERV_CAR_CFG_MAP_VALUE:
                                                return EVT_PSIS_SERV_CAR_CFG_MAP;
                                            case EVT_PSIS_SERV_CAR_CFG_ARRAY_VALUE:
                                                return EVT_PSIS_SERV_CAR_CFG_ARRAY;
                                            case EVT_PSIS_SERV_PSIS_CUS_VERSION_VALUE:
                                                return EVT_PSIS_SERV_PSIS_CUS_VERSION;
                                            case EVT_PSIS_SERV_PSIS_BOSCH_VERSION_VALUE:
                                                return EVT_PSIS_SERV_PSIS_BOSCH_VERSION;
                                            case EVT_PSIS_SERV_PSIS_CAL_XDSN_VALUE:
                                                return EVT_PSIS_SERV_PSIS_CAL_XDSN;
                                            case EVT_PSIS_SERV_PSIS_CAL_IHUID_VALUE:
                                                return EVT_PSIS_SERV_PSIS_CAL_IHUID;
                                            case EVT_PSIS_SERV_PSIS_CAL_VIN_VALUE:
                                                return EVT_PSIS_SERV_PSIS_CAL_VIN;
                                            default:
                                                switch (value) {
                                                    case EVT_PSIS_SERV_END_VALUE:
                                                        return EVT_PSIS_SERV_END;
                                                    case EVT_THERMAL_BEGIN_VALUE:
                                                        return EVT_THERMAL_BEGIN;
                                                    case EVT_THERMAL_INFO_VALUE:
                                                        return EVT_THERMAL_INFO;
                                                    default:
                                                        return null;
                                                }
                                        }
                                }
                        }
                }
            }
            return EVT_THERMAL_END;
        }

        public static Internal.EnumLiteMap<FdbusEventId> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.getDescriptor().getEnumTypes().get(3);
        }

        public static FdbusEventId valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        FdbusEventId(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum Status implements ProtocolMessageEnum {
        STAT_UNKNOWN(0, 0),
        STAT_CONNECTED(1, 1),
        STAT_DISCONNECTED(2, 2),
        STAT_IN_SERVICE(3, 3),
        STAT_IN_SERVICE_ACTIVATION(4, 4),
        STAT_IN_SERVICE_DEACTIVATION(5, 5),
        STAT_IN_SERVICE_LIMIT(6, 6),
        STAT_OUT_OF_SERVICE(7, 7);

        public static final int STAT_CONNECTED_VALUE = 1;
        public static final int STAT_DISCONNECTED_VALUE = 2;
        public static final int STAT_IN_SERVICE_ACTIVATION_VALUE = 4;
        public static final int STAT_IN_SERVICE_DEACTIVATION_VALUE = 5;
        public static final int STAT_IN_SERVICE_LIMIT_VALUE = 6;
        public static final int STAT_IN_SERVICE_VALUE = 3;
        public static final int STAT_OUT_OF_SERVICE_VALUE = 7;
        public static final int STAT_UNKNOWN_VALUE = 0;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<Status> internalValueMap = new Internal.EnumLiteMap<Status>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.Status.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Status findValueByNumber(int number) {
                return Status.valueOf(number);
            }
        };
        private static final Status[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static Status valueOf(int value) {
            switch (value) {
                case 0:
                    return STAT_UNKNOWN;
                case 1:
                    return STAT_CONNECTED;
                case 2:
                    return STAT_DISCONNECTED;
                case 3:
                    return STAT_IN_SERVICE;
                case 4:
                    return STAT_IN_SERVICE_ACTIVATION;
                case 5:
                    return STAT_IN_SERVICE_DEACTIVATION;
                case 6:
                    return STAT_IN_SERVICE_LIMIT;
                case 7:
                    return STAT_OUT_OF_SERVICE;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<Status> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.getDescriptor().getEnumTypes().get(4);
        }

        public static Status valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        Status(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum NtfTempSensor_t implements ProtocolMessageEnum {
        Ntf_AOSS(0, 1),
        Ntf_CPU(1, 2),
        Ntf_GPU(2, 3),
        Ntf_CWLAN(3, 4),
        Ntf_VIDEO(4, 5),
        Ntf_INTER_DDR(5, 6),
        Ntf_Q6_HVX(6, 7),
        Ntf_CAMERA(7, 8),
        Ntf_CMPSS(8, 9),
        Ntf_MODEM(9, 10),
        Ntf_NPU(10, 11),
        Ntf_TUNER(11, 12),
        Ntf_DDR_SIP(12, 13),
        Ntf_UFS(13, 14),
        Ntf_AMP1_TJ(14, 15),
        Ntf_AMP2_TJ(15, 16),
        Ntf_AMP_PERI(16, 17),
        Ntf_BT_WIFI(17, 18),
        Ntf_EMMC(18, 19),
        Ntf_DIODE(19, 20),
        Ntf_PHTM_MPQ(20, 21),
        Ntf_GNSS(21, 22),
        Ntf_DB_CAM(22, 23),
        Ntf_GYRO(23, 24),
        Ntf_PWR_MAIN(24, 25),
        Ntf_EXT_DDR(25, 26),
        Ntf_HSM(26, 27),
        Ntf_SW_TEST(27, 99);

        public static final int Ntf_AMP1_TJ_VALUE = 15;
        public static final int Ntf_AMP2_TJ_VALUE = 16;
        public static final int Ntf_AMP_PERI_VALUE = 17;
        public static final int Ntf_AOSS_VALUE = 1;
        public static final int Ntf_BT_WIFI_VALUE = 18;
        public static final int Ntf_CAMERA_VALUE = 8;
        public static final int Ntf_CMPSS_VALUE = 9;
        public static final int Ntf_CPU_VALUE = 2;
        public static final int Ntf_CWLAN_VALUE = 4;
        public static final int Ntf_DB_CAM_VALUE = 23;
        public static final int Ntf_DDR_SIP_VALUE = 13;
        public static final int Ntf_DIODE_VALUE = 20;
        public static final int Ntf_EMMC_VALUE = 19;
        public static final int Ntf_EXT_DDR_VALUE = 26;
        public static final int Ntf_GNSS_VALUE = 22;
        public static final int Ntf_GPU_VALUE = 3;
        public static final int Ntf_GYRO_VALUE = 24;
        public static final int Ntf_HSM_VALUE = 27;
        public static final int Ntf_INTER_DDR_VALUE = 6;
        public static final int Ntf_MODEM_VALUE = 10;
        public static final int Ntf_NPU_VALUE = 11;
        public static final int Ntf_PHTM_MPQ_VALUE = 21;
        public static final int Ntf_PWR_MAIN_VALUE = 25;
        public static final int Ntf_Q6_HVX_VALUE = 7;
        public static final int Ntf_SW_TEST_VALUE = 99;
        public static final int Ntf_TUNER_VALUE = 12;
        public static final int Ntf_UFS_VALUE = 14;
        public static final int Ntf_VIDEO_VALUE = 5;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<NtfTempSensor_t> internalValueMap = new Internal.EnumLiteMap<NtfTempSensor_t>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfTempSensor_t.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public NtfTempSensor_t findValueByNumber(int number) {
                return NtfTempSensor_t.valueOf(number);
            }
        };
        private static final NtfTempSensor_t[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static NtfTempSensor_t valueOf(int value) {
            if (value != 99) {
                switch (value) {
                    case 1:
                        return Ntf_AOSS;
                    case 2:
                        return Ntf_CPU;
                    case 3:
                        return Ntf_GPU;
                    case 4:
                        return Ntf_CWLAN;
                    case 5:
                        return Ntf_VIDEO;
                    case 6:
                        return Ntf_INTER_DDR;
                    case 7:
                        return Ntf_Q6_HVX;
                    case 8:
                        return Ntf_CAMERA;
                    case 9:
                        return Ntf_CMPSS;
                    case 10:
                        return Ntf_MODEM;
                    case 11:
                        return Ntf_NPU;
                    case 12:
                        return Ntf_TUNER;
                    case 13:
                        return Ntf_DDR_SIP;
                    case 14:
                        return Ntf_UFS;
                    case 15:
                        return Ntf_AMP1_TJ;
                    case 16:
                        return Ntf_AMP2_TJ;
                    case 17:
                        return Ntf_AMP_PERI;
                    case 18:
                        return Ntf_BT_WIFI;
                    case 19:
                        return Ntf_EMMC;
                    case 20:
                        return Ntf_DIODE;
                    case 21:
                        return Ntf_PHTM_MPQ;
                    case 22:
                        return Ntf_GNSS;
                    case 23:
                        return Ntf_DB_CAM;
                    case 24:
                        return Ntf_GYRO;
                    case 25:
                        return Ntf_PWR_MAIN;
                    case 26:
                        return Ntf_EXT_DDR;
                    case 27:
                        return Ntf_HSM;
                    default:
                        return null;
                }
            }
            return Ntf_SW_TEST;
        }

        public static Internal.EnumLiteMap<NtfTempSensor_t> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.getDescriptor().getEnumTypes().get(5);
        }

        public static NtfTempSensor_t valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        NtfTempSensor_t(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum NtfTempLevel_t implements ProtocolMessageEnum {
        LV_ORDINARY(0, 0),
        LV_LIGHT(1, 1),
        LV_WARNING(2, 2),
        LV_CRITICAL(3, 3),
        LV_EMERGENCY(4, 4),
        LV_SHUTDOWN(5, 5);

        public static final int LV_CRITICAL_VALUE = 3;
        public static final int LV_EMERGENCY_VALUE = 4;
        public static final int LV_LIGHT_VALUE = 1;
        public static final int LV_ORDINARY_VALUE = 0;
        public static final int LV_SHUTDOWN_VALUE = 5;
        public static final int LV_WARNING_VALUE = 2;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<NtfTempLevel_t> internalValueMap = new Internal.EnumLiteMap<NtfTempLevel_t>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfTempLevel_t.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public NtfTempLevel_t findValueByNumber(int number) {
                return NtfTempLevel_t.valueOf(number);
            }
        };
        private static final NtfTempLevel_t[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static NtfTempLevel_t valueOf(int value) {
            if (value == 0) {
                return LV_ORDINARY;
            }
            if (value == 1) {
                return LV_LIGHT;
            }
            if (value == 2) {
                return LV_WARNING;
            }
            if (value == 3) {
                return LV_CRITICAL;
            }
            if (value == 4) {
                return LV_EMERGENCY;
            }
            if (value == 5) {
                return LV_SHUTDOWN;
            }
            return null;
        }

        public static Internal.EnumLiteMap<NtfTempLevel_t> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.getDescriptor().getEnumTypes().get(6);
        }

        public static NtfTempLevel_t valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        NtfTempLevel_t(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum PwrSplyStat implements ProtocolMessageEnum {
        PWR_SPLY_OFF(0, 0),
        PWR_SPLY_ON(1, 1);

        public static final int PWR_SPLY_OFF_VALUE = 0;
        public static final int PWR_SPLY_ON_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<PwrSplyStat> internalValueMap = new Internal.EnumLiteMap<PwrSplyStat>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PwrSplyStat.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public PwrSplyStat findValueByNumber(int number) {
                return PwrSplyStat.valueOf(number);
            }
        };
        private static final PwrSplyStat[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static PwrSplyStat valueOf(int value) {
            if (value == 0) {
                return PWR_SPLY_OFF;
            }
            if (value == 1) {
                return PWR_SPLY_ON;
            }
            return null;
        }

        public static Internal.EnumLiteMap<PwrSplyStat> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.getDescriptor().getEnumTypes().get(7);
        }

        public static PwrSplyStat valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        PwrSplyStat(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum NtfPowerMode implements ProtocolMessageEnum {
        NTF_PM_OFF(0, 0),
        NTF_PM_PRE_STARTUP(1, 1),
        NTF_PM_DEGRADED(2, 3),
        NTF_PM_STANDBY(3, 4),
        NTF_PM_STR(4, 5),
        NTF_PM_REFLASH(5, 6),
        NTF_PM_REMOTE_FOTA(6, 7),
        NTF_PM_SLEEP(7, 8),
        NTF_PM_STR_SUSPENDING(8, 9),
        NTF_PM_STARTUP(9, 2),
        NTF_PM_DISPLAY_ON(10, 10),
        NTF_PM_HALF_HOUR_MODE(11, 11),
        NTF_PM_TOD(12, 12);

        public static final int NTF_PM_DEGRADED_VALUE = 3;
        public static final int NTF_PM_DISPLAY_ON_VALUE = 10;
        public static final int NTF_PM_HALF_HOUR_MODE_VALUE = 11;
        public static final int NTF_PM_OFF_VALUE = 0;
        public static final int NTF_PM_PRE_STARTUP_VALUE = 1;
        public static final int NTF_PM_REFLASH_VALUE = 6;
        public static final int NTF_PM_REMOTE_FOTA_VALUE = 7;
        public static final int NTF_PM_SLEEP_VALUE = 8;
        public static final int NTF_PM_STANDBY_VALUE = 4;
        public static final int NTF_PM_STARTUP_VALUE = 2;
        public static final int NTF_PM_STR_SUSPENDING_VALUE = 9;
        public static final int NTF_PM_STR_VALUE = 5;
        public static final int NTF_PM_TOD_VALUE = 12;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<NtfPowerMode> internalValueMap = new Internal.EnumLiteMap<NtfPowerMode>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfPowerMode.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public NtfPowerMode findValueByNumber(int number) {
                return NtfPowerMode.valueOf(number);
            }
        };
        private static final NtfPowerMode[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static NtfPowerMode valueOf(int value) {
            switch (value) {
                case 0:
                    return NTF_PM_OFF;
                case 1:
                    return NTF_PM_PRE_STARTUP;
                case 2:
                    return NTF_PM_STARTUP;
                case 3:
                    return NTF_PM_DEGRADED;
                case 4:
                    return NTF_PM_STANDBY;
                case 5:
                    return NTF_PM_STR;
                case 6:
                    return NTF_PM_REFLASH;
                case 7:
                    return NTF_PM_REMOTE_FOTA;
                case 8:
                    return NTF_PM_SLEEP;
                case 9:
                    return NTF_PM_STR_SUSPENDING;
                case 10:
                    return NTF_PM_DISPLAY_ON;
                case 11:
                    return NTF_PM_HALF_HOUR_MODE;
                case 12:
                    return NTF_PM_TOD;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<NtfPowerMode> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.getDescriptor().getEnumTypes().get(8);
        }

        public static NtfPowerMode valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        NtfPowerMode(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum NtfVoltageState implements ProtocolMessageEnum {
        NTF_VOLTAGE_BEYOND_CRITICAL_HIGH_3(0, 1),
        NTF_VOLTAGE_CRITICAL_HIGH_2(1, 2),
        NTF_VOLTAGE_HIGH_1(2, 3),
        NTF_VOLTAGE_NORMAL(3, 4),
        NTF_VOLTAGE_LOW_1(4, 5),
        NTF_VOLTAGE_CRITICAL_LOW_2(5, 6);

        public static final int NTF_VOLTAGE_BEYOND_CRITICAL_HIGH_3_VALUE = 1;
        public static final int NTF_VOLTAGE_CRITICAL_HIGH_2_VALUE = 2;
        public static final int NTF_VOLTAGE_CRITICAL_LOW_2_VALUE = 6;
        public static final int NTF_VOLTAGE_HIGH_1_VALUE = 3;
        public static final int NTF_VOLTAGE_LOW_1_VALUE = 5;
        public static final int NTF_VOLTAGE_NORMAL_VALUE = 4;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<NtfVoltageState> internalValueMap = new Internal.EnumLiteMap<NtfVoltageState>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfVoltageState.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public NtfVoltageState findValueByNumber(int number) {
                return NtfVoltageState.valueOf(number);
            }
        };
        private static final NtfVoltageState[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static NtfVoltageState valueOf(int value) {
            switch (value) {
                case 1:
                    return NTF_VOLTAGE_BEYOND_CRITICAL_HIGH_3;
                case 2:
                    return NTF_VOLTAGE_CRITICAL_HIGH_2;
                case 3:
                    return NTF_VOLTAGE_HIGH_1;
                case 4:
                    return NTF_VOLTAGE_NORMAL;
                case 5:
                    return NTF_VOLTAGE_LOW_1;
                case 6:
                    return NTF_VOLTAGE_CRITICAL_LOW_2;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<NtfVoltageState> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.getDescriptor().getEnumTypes().get(9);
        }

        public static NtfVoltageState valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        NtfVoltageState(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum NtfPowerSaveMode implements ProtocolMessageEnum {
        NTF_TRANPORT_OFF(0, 0),
        NTF_TRANPORT_ON(1, 1),
        NTF_POWER_STA_LEVEL1(2, 2),
        NTF_POWER_STA_LEVEL2(3, 3),
        NTF_POWER_STA_LEVEL_OFF(4, 4),
        NTF_POWER_DYN_LEVEL_ON(5, 5),
        NTF_POWER_DYN_LEVEL_OFF(6, 6),
        NTF_POWER_SYS_ERROR_ON(7, 7),
        NTF_POWER_SYS_ERROR_OFF(8, 8),
        NTF_POWER_THERMAL_HIGH(9, 9),
        NTF_TRANPORT_ON_TIMEOUT(10, 10),
        NTF_POWER_STA_LEVEL2_TIMOUT(11, 11),
        NTF_POWER_THERMAL_HIGH_TIMOUT(12, 12),
        NTF_POWER_VOLTAGE_TIMOUT(13, 13),
        NTF_POWER_THERMAL_NORMAL(14, 14);

        public static final int NTF_POWER_DYN_LEVEL_OFF_VALUE = 6;
        public static final int NTF_POWER_DYN_LEVEL_ON_VALUE = 5;
        public static final int NTF_POWER_STA_LEVEL1_VALUE = 2;
        public static final int NTF_POWER_STA_LEVEL2_TIMOUT_VALUE = 11;
        public static final int NTF_POWER_STA_LEVEL2_VALUE = 3;
        public static final int NTF_POWER_STA_LEVEL_OFF_VALUE = 4;
        public static final int NTF_POWER_SYS_ERROR_OFF_VALUE = 8;
        public static final int NTF_POWER_SYS_ERROR_ON_VALUE = 7;
        public static final int NTF_POWER_THERMAL_HIGH_TIMOUT_VALUE = 12;
        public static final int NTF_POWER_THERMAL_HIGH_VALUE = 9;
        public static final int NTF_POWER_THERMAL_NORMAL_VALUE = 14;
        public static final int NTF_POWER_VOLTAGE_TIMOUT_VALUE = 13;
        public static final int NTF_TRANPORT_OFF_VALUE = 0;
        public static final int NTF_TRANPORT_ON_TIMEOUT_VALUE = 10;
        public static final int NTF_TRANPORT_ON_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<NtfPowerSaveMode> internalValueMap = new Internal.EnumLiteMap<NtfPowerSaveMode>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfPowerSaveMode.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public NtfPowerSaveMode findValueByNumber(int number) {
                return NtfPowerSaveMode.valueOf(number);
            }
        };
        private static final NtfPowerSaveMode[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static NtfPowerSaveMode valueOf(int value) {
            switch (value) {
                case 0:
                    return NTF_TRANPORT_OFF;
                case 1:
                    return NTF_TRANPORT_ON;
                case 2:
                    return NTF_POWER_STA_LEVEL1;
                case 3:
                    return NTF_POWER_STA_LEVEL2;
                case 4:
                    return NTF_POWER_STA_LEVEL_OFF;
                case 5:
                    return NTF_POWER_DYN_LEVEL_ON;
                case 6:
                    return NTF_POWER_DYN_LEVEL_OFF;
                case 7:
                    return NTF_POWER_SYS_ERROR_ON;
                case 8:
                    return NTF_POWER_SYS_ERROR_OFF;
                case 9:
                    return NTF_POWER_THERMAL_HIGH;
                case 10:
                    return NTF_TRANPORT_ON_TIMEOUT;
                case 11:
                    return NTF_POWER_STA_LEVEL2_TIMOUT;
                case 12:
                    return NTF_POWER_THERMAL_HIGH_TIMOUT;
                case 13:
                    return NTF_POWER_VOLTAGE_TIMOUT;
                case 14:
                    return NTF_POWER_THERMAL_NORMAL;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<NtfPowerSaveMode> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.getDescriptor().getEnumTypes().get(10);
        }

        public static NtfPowerSaveMode valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        NtfPowerSaveMode(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum NtfIpkPowerMode implements ProtocolMessageEnum {
        NTF_IPK_PREPARE_START(0, 0),
        NTF_IPK_PREPARE_IGNITE(1, 1),
        NTF_IPK_ANIMATION(2, 2),
        NTF_IPK_IGNITE_ON(3, 3),
        NTF_IPK_IGNITE_OFF(4, 4),
        NTF_IPK_PART_SHOW(5, 5),
        NTF_IPK_PREPARE_SLEEP(6, 6),
        NTF_IPK_SLEEP(7, 7),
        NTF_IPK_ANIMATION_NFC(8, 8),
        NTF_IPK_PREPARE_SLEEP_A(9, 9),
        NTF_IPK_PREPARE_SLEEP_B(10, 10),
        NTF_IPK_ACC_ON_MODE(11, 11);

        public static final int NTF_IPK_ACC_ON_MODE_VALUE = 11;
        public static final int NTF_IPK_ANIMATION_NFC_VALUE = 8;
        public static final int NTF_IPK_ANIMATION_VALUE = 2;
        public static final int NTF_IPK_IGNITE_OFF_VALUE = 4;
        public static final int NTF_IPK_IGNITE_ON_VALUE = 3;
        public static final int NTF_IPK_PART_SHOW_VALUE = 5;
        public static final int NTF_IPK_PREPARE_IGNITE_VALUE = 1;
        public static final int NTF_IPK_PREPARE_SLEEP_A_VALUE = 9;
        public static final int NTF_IPK_PREPARE_SLEEP_B_VALUE = 10;
        public static final int NTF_IPK_PREPARE_SLEEP_VALUE = 6;
        public static final int NTF_IPK_PREPARE_START_VALUE = 0;
        public static final int NTF_IPK_SLEEP_VALUE = 7;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<NtfIpkPowerMode> internalValueMap = new Internal.EnumLiteMap<NtfIpkPowerMode>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfIpkPowerMode.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public NtfIpkPowerMode findValueByNumber(int number) {
                return NtfIpkPowerMode.valueOf(number);
            }
        };
        private static final NtfIpkPowerMode[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static NtfIpkPowerMode valueOf(int value) {
            switch (value) {
                case 0:
                    return NTF_IPK_PREPARE_START;
                case 1:
                    return NTF_IPK_PREPARE_IGNITE;
                case 2:
                    return NTF_IPK_ANIMATION;
                case 3:
                    return NTF_IPK_IGNITE_ON;
                case 4:
                    return NTF_IPK_IGNITE_OFF;
                case 5:
                    return NTF_IPK_PART_SHOW;
                case 6:
                    return NTF_IPK_PREPARE_SLEEP;
                case 7:
                    return NTF_IPK_SLEEP;
                case 8:
                    return NTF_IPK_ANIMATION_NFC;
                case 9:
                    return NTF_IPK_PREPARE_SLEEP_A;
                case 10:
                    return NTF_IPK_PREPARE_SLEEP_B;
                case 11:
                    return NTF_IPK_ACC_ON_MODE;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<NtfIpkPowerMode> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.getDescriptor().getEnumTypes().get(11);
        }

        public static NtfIpkPowerMode valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        NtfIpkPowerMode(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum NTFDrvDoorSts implements ProtocolMessageEnum {
        NTF_DOOR_CLOSE(0, 0),
        NTF_DOOR_OPEN(1, 1);

        public static final int NTF_DOOR_CLOSE_VALUE = 0;
        public static final int NTF_DOOR_OPEN_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<NTFDrvDoorSts> internalValueMap = new Internal.EnumLiteMap<NTFDrvDoorSts>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NTFDrvDoorSts.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public NTFDrvDoorSts findValueByNumber(int number) {
                return NTFDrvDoorSts.valueOf(number);
            }
        };
        private static final NTFDrvDoorSts[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static NTFDrvDoorSts valueOf(int value) {
            if (value == 0) {
                return NTF_DOOR_CLOSE;
            }
            if (value == 1) {
                return NTF_DOOR_OPEN;
            }
            return null;
        }

        public static Internal.EnumLiteMap<NTFDrvDoorSts> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.getDescriptor().getEnumTypes().get(12);
        }

        public static NTFDrvDoorSts valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        NTFDrvDoorSts(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum NTFWarningSts implements ProtocolMessageEnum {
        NTF_WARNING_OFF(0, 0),
        NTF_WARNING_ON(1, 1);

        public static final int NTF_WARNING_OFF_VALUE = 0;
        public static final int NTF_WARNING_ON_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<NTFWarningSts> internalValueMap = new Internal.EnumLiteMap<NTFWarningSts>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NTFWarningSts.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public NTFWarningSts findValueByNumber(int number) {
                return NTFWarningSts.valueOf(number);
            }
        };
        private static final NTFWarningSts[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static NTFWarningSts valueOf(int value) {
            if (value == 0) {
                return NTF_WARNING_OFF;
            }
            if (value == 1) {
                return NTF_WARNING_ON;
            }
            return null;
        }

        public static Internal.EnumLiteMap<NTFWarningSts> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.getDescriptor().getEnumTypes().get(13);
        }

        public static NTFWarningSts valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        NTFWarningSts(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum NTFLockSts implements ProtocolMessageEnum {
        NTF_LOCK_OFF(0, 0),
        NTF_LOCK_ON(1, 1);

        public static final int NTF_LOCK_OFF_VALUE = 0;
        public static final int NTF_LOCK_ON_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<NTFLockSts> internalValueMap = new Internal.EnumLiteMap<NTFLockSts>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NTFLockSts.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public NTFLockSts findValueByNumber(int number) {
                return NTFLockSts.valueOf(number);
            }
        };
        private static final NTFLockSts[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static NTFLockSts valueOf(int value) {
            if (value == 0) {
                return NTF_LOCK_OFF;
            }
            if (value == 1) {
                return NTF_LOCK_ON;
            }
            return null;
        }

        public static Internal.EnumLiteMap<NTFLockSts> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.getDescriptor().getEnumTypes().get(14);
        }

        public static NTFLockSts valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        NTFLockSts(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum NTFChargingsts implements ProtocolMessageEnum {
        NTF_CHARGING_OFF(0, 0),
        NTF_CHARGING_ON(1, 1);

        public static final int NTF_CHARGING_OFF_VALUE = 0;
        public static final int NTF_CHARGING_ON_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<NTFChargingsts> internalValueMap = new Internal.EnumLiteMap<NTFChargingsts>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NTFChargingsts.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public NTFChargingsts findValueByNumber(int number) {
                return NTFChargingsts.valueOf(number);
            }
        };
        private static final NTFChargingsts[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static NTFChargingsts valueOf(int value) {
            if (value == 0) {
                return NTF_CHARGING_OFF;
            }
            if (value == 1) {
                return NTF_CHARGING_ON;
            }
            return null;
        }

        public static Internal.EnumLiteMap<NTFChargingsts> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.getDescriptor().getEnumTypes().get(15);
        }

        public static NTFChargingsts valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        NTFChargingsts(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum HealthStatus implements ProtocolMessageEnum {
        HLTH_STAT_UNKNOWN(0, 0),
        HLTH_STAT_SERVING(1, 1),
        HLTH_STAT_NOT_SERVING(2, 2);

        public static final int HLTH_STAT_NOT_SERVING_VALUE = 2;
        public static final int HLTH_STAT_SERVING_VALUE = 1;
        public static final int HLTH_STAT_UNKNOWN_VALUE = 0;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<HealthStatus> internalValueMap = new Internal.EnumLiteMap<HealthStatus>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.HealthStatus.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public HealthStatus findValueByNumber(int number) {
                return HealthStatus.valueOf(number);
            }
        };
        private static final HealthStatus[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static HealthStatus valueOf(int value) {
            if (value == 0) {
                return HLTH_STAT_UNKNOWN;
            }
            if (value == 1) {
                return HLTH_STAT_SERVING;
            }
            if (value == 2) {
                return HLTH_STAT_NOT_SERVING;
            }
            return null;
        }

        public static Internal.EnumLiteMap<HealthStatus> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.getDescriptor().getEnumTypes().get(16);
        }

        public static HealthStatus valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        HealthStatus(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum DiagDtcResult implements ProtocolMessageEnum {
        DTC_OK(0, 0),
        DTC_ERROR(1, 1);

        public static final int DTC_ERROR_VALUE = 1;
        public static final int DTC_OK_VALUE = 0;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<DiagDtcResult> internalValueMap = new Internal.EnumLiteMap<DiagDtcResult>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DiagDtcResult.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public DiagDtcResult findValueByNumber(int number) {
                return DiagDtcResult.valueOf(number);
            }
        };
        private static final DiagDtcResult[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static DiagDtcResult valueOf(int value) {
            if (value == 0) {
                return DTC_OK;
            }
            if (value == 1) {
                return DTC_ERROR;
            }
            return null;
        }

        public static Internal.EnumLiteMap<DiagDtcResult> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.getDescriptor().getEnumTypes().get(17);
        }

        public static DiagDtcResult valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        DiagDtcResult(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum LogLevel implements ProtocolMessageEnum {
        LOG_SHUTDOWN(0, 0),
        LOG_CRITICAL(1, 1),
        LOG_ERROR(2, 2),
        LOG_WARN(3, 3),
        LOG_NOTICE(4, 4),
        LOG_INFO(5, 5),
        LOG_DEBUG1(6, 6),
        LOG_DEBUG2(7, 7);

        public static final int LOG_CRITICAL_VALUE = 1;
        public static final int LOG_DEBUG1_VALUE = 6;
        public static final int LOG_DEBUG2_VALUE = 7;
        public static final int LOG_ERROR_VALUE = 2;
        public static final int LOG_INFO_VALUE = 5;
        public static final int LOG_NOTICE_VALUE = 4;
        public static final int LOG_SHUTDOWN_VALUE = 0;
        public static final int LOG_WARN_VALUE = 3;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<LogLevel> internalValueMap = new Internal.EnumLiteMap<LogLevel>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LogLevel.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public LogLevel findValueByNumber(int number) {
                return LogLevel.valueOf(number);
            }
        };
        private static final LogLevel[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static LogLevel valueOf(int value) {
            switch (value) {
                case 0:
                    return LOG_SHUTDOWN;
                case 1:
                    return LOG_CRITICAL;
                case 2:
                    return LOG_ERROR;
                case 3:
                    return LOG_WARN;
                case 4:
                    return LOG_NOTICE;
                case 5:
                    return LOG_INFO;
                case 6:
                    return LOG_DEBUG1;
                case 7:
                    return LOG_DEBUG2;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<LogLevel> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.getDescriptor().getEnumTypes().get(18);
        }

        public static LogLevel valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        LogLevel(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum EfuseStatus implements ProtocolMessageEnum {
        EFUSE_NO_SWITCH(0, 0),
        EFUSE_NOT_FLASHED(1, 1),
        EFUSE_FLASHED(2, 2),
        EFUSE_UNKNOWN_STATUS(3, 3);

        public static final int EFUSE_FLASHED_VALUE = 2;
        public static final int EFUSE_NOT_FLASHED_VALUE = 1;
        public static final int EFUSE_NO_SWITCH_VALUE = 0;
        public static final int EFUSE_UNKNOWN_STATUS_VALUE = 3;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<EfuseStatus> internalValueMap = new Internal.EnumLiteMap<EfuseStatus>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.EfuseStatus.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public EfuseStatus findValueByNumber(int number) {
                return EfuseStatus.valueOf(number);
            }
        };
        private static final EfuseStatus[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static EfuseStatus valueOf(int value) {
            if (value == 0) {
                return EFUSE_NO_SWITCH;
            }
            if (value == 1) {
                return EFUSE_NOT_FLASHED;
            }
            if (value == 2) {
                return EFUSE_FLASHED;
            }
            if (value == 3) {
                return EFUSE_UNKNOWN_STATUS;
            }
            return null;
        }

        public static Internal.EnumLiteMap<EfuseStatus> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.getDescriptor().getEnumTypes().get(19);
        }

        public static EfuseStatus valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        EfuseStatus(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum HudRotateDir implements ProtocolMessageEnum {
        DISP_HUD_NO_ROTATION(0, 0),
        DISP_HUD_CLOCKWISE_ROTATION(1, 1),
        DISP_HUD_ANTICLOCKWISE_ROTATION(2, 2);

        public static final int DISP_HUD_ANTICLOCKWISE_ROTATION_VALUE = 2;
        public static final int DISP_HUD_CLOCKWISE_ROTATION_VALUE = 1;
        public static final int DISP_HUD_NO_ROTATION_VALUE = 0;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<HudRotateDir> internalValueMap = new Internal.EnumLiteMap<HudRotateDir>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.HudRotateDir.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public HudRotateDir findValueByNumber(int number) {
                return HudRotateDir.valueOf(number);
            }
        };
        private static final HudRotateDir[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static HudRotateDir valueOf(int value) {
            if (value == 0) {
                return DISP_HUD_NO_ROTATION;
            }
            if (value == 1) {
                return DISP_HUD_CLOCKWISE_ROTATION;
            }
            if (value == 2) {
                return DISP_HUD_ANTICLOCKWISE_ROTATION;
            }
            return null;
        }

        public static Internal.EnumLiteMap<HudRotateDir> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.getDescriptor().getEnumTypes().get(20);
        }

        public static HudRotateDir valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        HudRotateDir(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public static final class RtcUpdateEvents extends GeneratedMessage implements RtcUpdateEventsOrBuilder {
        public static Parser<RtcUpdateEvents> PARSER = new AbstractParser<RtcUpdateEvents>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.RtcUpdateEvents.1
            @Override // com.google.protobuf.Parser
            public RtcUpdateEvents parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new RtcUpdateEvents(input, extensionRegistry);
            }
        };
        public static final int RESULT_FIELD_NUMBER = 1;
        private static final RtcUpdateEvents defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int result_;
        private final UnknownFieldSet unknownFields;

        private RtcUpdateEvents(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private RtcUpdateEvents(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static RtcUpdateEvents getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public RtcUpdateEvents getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private RtcUpdateEvents(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag != 8) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            this.bitField0_ |= 1;
                            this.result_ = input.readUInt32();
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_RtcUpdateEvents_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_RtcUpdateEvents_fieldAccessorTable.ensureFieldAccessorsInitialized(RtcUpdateEvents.class, Builder.class);
        }

        static {
            RtcUpdateEvents rtcUpdateEvents = new RtcUpdateEvents(true);
            defaultInstance = rtcUpdateEvents;
            rtcUpdateEvents.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<RtcUpdateEvents> getParserForType() {
            return PARSER;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.RtcUpdateEventsOrBuilder
        public boolean hasResult() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.RtcUpdateEventsOrBuilder
        public int getResult() {
            return this.result_;
        }

        private void initFields() {
            this.result_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasResult()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeUInt32(1, this.result_);
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeUInt32Size(1, this.result_) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static RtcUpdateEvents parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static RtcUpdateEvents parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static RtcUpdateEvents parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static RtcUpdateEvents parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static RtcUpdateEvents parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static RtcUpdateEvents parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static RtcUpdateEvents parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static RtcUpdateEvents parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static RtcUpdateEvents parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static RtcUpdateEvents parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(RtcUpdateEvents prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements RtcUpdateEventsOrBuilder {
            private int bitField0_;
            private int result_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_RtcUpdateEvents_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_RtcUpdateEvents_fieldAccessorTable.ensureFieldAccessorsInitialized(RtcUpdateEvents.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = RtcUpdateEvents.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.result_ = 0;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_RtcUpdateEvents_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public RtcUpdateEvents getDefaultInstanceForType() {
                return RtcUpdateEvents.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public RtcUpdateEvents build() {
                RtcUpdateEvents result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public RtcUpdateEvents buildPartial() {
                RtcUpdateEvents result = new RtcUpdateEvents(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.result_ = this.result_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof RtcUpdateEvents) {
                    return mergeFrom((RtcUpdateEvents) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(RtcUpdateEvents other) {
                if (other == RtcUpdateEvents.getDefaultInstance()) {
                    return this;
                }
                if (other.hasResult()) {
                    setResult(other.getResult());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (!hasResult()) {
                    return false;
                }
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                RtcUpdateEvents parsedMessage = null;
                try {
                    try {
                        parsedMessage = RtcUpdateEvents.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.RtcUpdateEventsOrBuilder
            public boolean hasResult() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.RtcUpdateEventsOrBuilder
            public int getResult() {
                return this.result_;
            }

            public Builder setResult(int value) {
                this.bitField0_ |= 1;
                this.result_ = value;
                onChanged();
                return this;
            }

            public Builder clearResult() {
                this.bitField0_ &= -2;
                this.result_ = 0;
                onChanged();
                return this;
            }
        }
    }

    public static final class VehicleInfo extends GeneratedMessage implements VehicleInfoOrBuilder {
        public static final int MFR_FIELD_NUMBER = 2;
        public static Parser<VehicleInfo> PARSER = new AbstractParser<VehicleInfo>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.VehicleInfo.1
            @Override // com.google.protobuf.Parser
            public VehicleInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new VehicleInfo(input, extensionRegistry);
            }
        };
        public static final int VEH_VER_FIELD_NUMBER = 3;
        public static final int VIN_FIELD_NUMBER = 1;
        private static final VehicleInfo defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private Object mfr_;
        private final UnknownFieldSet unknownFields;
        private Object vehVer_;
        private Object vin_;

        private VehicleInfo(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private VehicleInfo(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static VehicleInfo getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public VehicleInfo getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private VehicleInfo(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 10) {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 1;
                            this.vin_ = bs;
                        } else if (tag == 18) {
                            ByteString bs2 = input.readBytes();
                            this.bitField0_ |= 2;
                            this.mfr_ = bs2;
                        } else if (tag != 26) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            ByteString bs3 = input.readBytes();
                            this.bitField0_ |= 4;
                            this.vehVer_ = bs3;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_VehicleInfo_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_VehicleInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(VehicleInfo.class, Builder.class);
        }

        static {
            VehicleInfo vehicleInfo = new VehicleInfo(true);
            defaultInstance = vehicleInfo;
            vehicleInfo.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<VehicleInfo> getParserForType() {
            return PARSER;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.VehicleInfoOrBuilder
        public boolean hasVin() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.VehicleInfoOrBuilder
        public String getVin() {
            Object ref = this.vin_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.vin_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.VehicleInfoOrBuilder
        public ByteString getVinBytes() {
            Object ref = this.vin_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.vin_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.VehicleInfoOrBuilder
        public boolean hasMfr() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.VehicleInfoOrBuilder
        public String getMfr() {
            Object ref = this.mfr_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.mfr_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.VehicleInfoOrBuilder
        public ByteString getMfrBytes() {
            Object ref = this.mfr_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.mfr_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.VehicleInfoOrBuilder
        public boolean hasVehVer() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.VehicleInfoOrBuilder
        public String getVehVer() {
            Object ref = this.vehVer_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.vehVer_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.VehicleInfoOrBuilder
        public ByteString getVehVerBytes() {
            Object ref = this.vehVer_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.vehVer_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        private void initFields() {
            this.vin_ = "";
            this.mfr_ = "";
            this.vehVer_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasVin()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(1, getVinBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, getMfrBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBytes(3, getVehVerBytes());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeBytesSize(1, getVinBytes()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBytesSize(2, getMfrBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBytesSize(3, getVehVerBytes());
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static VehicleInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static VehicleInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static VehicleInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static VehicleInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static VehicleInfo parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static VehicleInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static VehicleInfo parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static VehicleInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static VehicleInfo parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static VehicleInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(VehicleInfo prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements VehicleInfoOrBuilder {
            private int bitField0_;
            private Object mfr_;
            private Object vehVer_;
            private Object vin_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_VehicleInfo_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_VehicleInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(VehicleInfo.class, Builder.class);
            }

            private Builder() {
                this.vin_ = "";
                this.mfr_ = "";
                this.vehVer_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.vin_ = "";
                this.mfr_ = "";
                this.vehVer_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = VehicleInfo.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.vin_ = "";
                int i = this.bitField0_ & (-2);
                this.bitField0_ = i;
                this.mfr_ = "";
                int i2 = i & (-3);
                this.bitField0_ = i2;
                this.vehVer_ = "";
                this.bitField0_ = i2 & (-5);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_VehicleInfo_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public VehicleInfo getDefaultInstanceForType() {
                return VehicleInfo.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public VehicleInfo build() {
                VehicleInfo result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public VehicleInfo buildPartial() {
                VehicleInfo result = new VehicleInfo(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.vin_ = this.vin_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.mfr_ = this.mfr_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.vehVer_ = this.vehVer_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof VehicleInfo) {
                    return mergeFrom((VehicleInfo) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(VehicleInfo other) {
                if (other == VehicleInfo.getDefaultInstance()) {
                    return this;
                }
                if (other.hasVin()) {
                    this.bitField0_ |= 1;
                    this.vin_ = other.vin_;
                    onChanged();
                }
                if (other.hasMfr()) {
                    this.bitField0_ |= 2;
                    this.mfr_ = other.mfr_;
                    onChanged();
                }
                if (other.hasVehVer()) {
                    this.bitField0_ |= 4;
                    this.vehVer_ = other.vehVer_;
                    onChanged();
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (!hasVin()) {
                    return false;
                }
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                VehicleInfo parsedMessage = null;
                try {
                    try {
                        parsedMessage = VehicleInfo.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.VehicleInfoOrBuilder
            public boolean hasVin() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.VehicleInfoOrBuilder
            public String getVin() {
                Object ref = this.vin_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.vin_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.VehicleInfoOrBuilder
            public ByteString getVinBytes() {
                Object ref = this.vin_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.vin_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setVin(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.vin_ = value;
                onChanged();
                return this;
            }

            public Builder clearVin() {
                this.bitField0_ &= -2;
                this.vin_ = VehicleInfo.getDefaultInstance().getVin();
                onChanged();
                return this;
            }

            public Builder setVinBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.vin_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.VehicleInfoOrBuilder
            public boolean hasMfr() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.VehicleInfoOrBuilder
            public String getMfr() {
                Object ref = this.mfr_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.mfr_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.VehicleInfoOrBuilder
            public ByteString getMfrBytes() {
                Object ref = this.mfr_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.mfr_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setMfr(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.mfr_ = value;
                onChanged();
                return this;
            }

            public Builder clearMfr() {
                this.bitField0_ &= -3;
                this.mfr_ = VehicleInfo.getDefaultInstance().getMfr();
                onChanged();
                return this;
            }

            public Builder setMfrBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.mfr_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.VehicleInfoOrBuilder
            public boolean hasVehVer() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.VehicleInfoOrBuilder
            public String getVehVer() {
                Object ref = this.vehVer_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.vehVer_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.VehicleInfoOrBuilder
            public ByteString getVehVerBytes() {
                Object ref = this.vehVer_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.vehVer_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setVehVer(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4;
                this.vehVer_ = value;
                onChanged();
                return this;
            }

            public Builder clearVehVer() {
                this.bitField0_ &= -5;
                this.vehVer_ = VehicleInfo.getDefaultInstance().getVehVer();
                onChanged();
                return this;
            }

            public Builder setVehVerBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4;
                this.vehVer_ = value;
                onChanged();
                return this;
            }
        }
    }

    public static final class ModuleStat extends GeneratedMessage implements ModuleStatOrBuilder {
        public static final int CPU_TEMPERATURE_FIELD_NUMBER = 8;
        public static final int DOMAIN_FIELD_NUMBER = 2;
        public static final int ELAPSED_TIME_FIELD_NUMBER = 7;
        public static final int NAME_FIELD_NUMBER = 1;
        public static Parser<ModuleStat> PARSER = new AbstractParser<ModuleStat>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStat.1
            @Override // com.google.protobuf.Parser
            public ModuleStat parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new ModuleStat(input, extensionRegistry);
            }
        };
        public static final int STATUS_FIELD_NUMBER = 3;
        public static final int TEMPERATURE_FIELD_NUMBER = 5;
        public static final int TEMP_LEVEL_FIELD_NUMBER = 6;
        public static final int VOLTAGE_FIELD_NUMBER = 4;
        private static final ModuleStat defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private float cpuTemperature_;
        private DomainId domain_;
        private int elapsedTime_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private Object name_;
        private Status status_;
        private NtfTempLevel_t tempLevel_;
        private float temperature_;
        private final UnknownFieldSet unknownFields;
        private float voltage_;

        private ModuleStat(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private ModuleStat(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ModuleStat getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ModuleStat getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ModuleStat(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 10) {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 1;
                            this.name_ = bs;
                        } else if (tag == 16) {
                            int rawValue = input.readEnum();
                            DomainId value = DomainId.valueOf(rawValue);
                            if (value != null) {
                                this.bitField0_ = 2 | this.bitField0_;
                                this.domain_ = value;
                            } else {
                                unknownFields.mergeVarintField(2, rawValue);
                            }
                        } else if (tag == 24) {
                            int rawValue2 = input.readEnum();
                            Status value2 = Status.valueOf(rawValue2);
                            if (value2 == null) {
                                unknownFields.mergeVarintField(3, rawValue2);
                            } else {
                                this.bitField0_ |= 4;
                                this.status_ = value2;
                            }
                        } else if (tag == 37) {
                            this.bitField0_ |= 8;
                            this.voltage_ = input.readFloat();
                        } else if (tag == 45) {
                            this.bitField0_ = 16 | this.bitField0_;
                            this.temperature_ = input.readFloat();
                        } else if (tag == 48) {
                            int rawValue3 = input.readEnum();
                            NtfTempLevel_t value3 = NtfTempLevel_t.valueOf(rawValue3);
                            if (value3 == null) {
                                unknownFields.mergeVarintField(6, rawValue3);
                            } else {
                                this.bitField0_ |= 32;
                                this.tempLevel_ = value3;
                            }
                        } else if (tag == 56) {
                            this.bitField0_ |= 64;
                            this.elapsedTime_ = input.readUInt32();
                        } else if (tag != 69) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                            this.cpuTemperature_ = input.readFloat();
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_ModuleStat_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_ModuleStat_fieldAccessorTable.ensureFieldAccessorsInitialized(ModuleStat.class, Builder.class);
        }

        static {
            ModuleStat moduleStat = new ModuleStat(true);
            defaultInstance = moduleStat;
            moduleStat.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<ModuleStat> getParserForType() {
            return PARSER;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
        public String getName() {
            Object ref = this.name_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.name_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
        public ByteString getNameBytes() {
            Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.name_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
        public boolean hasDomain() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
        public DomainId getDomain() {
            return this.domain_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
        public boolean hasStatus() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
        public Status getStatus() {
            return this.status_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
        public boolean hasVoltage() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
        public float getVoltage() {
            return this.voltage_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
        public boolean hasTemperature() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
        public float getTemperature() {
            return this.temperature_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
        public boolean hasTempLevel() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
        public NtfTempLevel_t getTempLevel() {
            return this.tempLevel_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
        public boolean hasElapsedTime() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
        public int getElapsedTime() {
            return this.elapsedTime_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
        public boolean hasCpuTemperature() {
            return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
        public float getCpuTemperature() {
            return this.cpuTemperature_;
        }

        private void initFields() {
            this.name_ = "";
            this.domain_ = DomainId.DOMAIN_UNKNOWN;
            this.status_ = Status.STAT_UNKNOWN;
            this.voltage_ = 0.0f;
            this.temperature_ = 0.0f;
            this.tempLevel_ = NtfTempLevel_t.LV_ORDINARY;
            this.elapsedTime_ = 0;
            this.cpuTemperature_ = 0.0f;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasName()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasDomain()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasStatus()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(1, getNameBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.domain_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeEnum(3, this.status_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeFloat(4, this.voltage_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeFloat(5, this.temperature_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeEnum(6, this.tempLevel_.getNumber());
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeUInt32(7, this.elapsedTime_);
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                output.writeFloat(8, this.cpuTemperature_);
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeBytesSize(1, getNameBytes()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(2, this.domain_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeEnumSize(3, this.status_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeFloatSize(4, this.voltage_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeFloatSize(5, this.temperature_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeEnumSize(6, this.tempLevel_.getNumber());
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeUInt32Size(7, this.elapsedTime_);
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                size2 += CodedOutputStream.computeFloatSize(8, this.cpuTemperature_);
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static ModuleStat parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ModuleStat parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ModuleStat parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ModuleStat parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ModuleStat parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ModuleStat parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static ModuleStat parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static ModuleStat parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static ModuleStat parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ModuleStat parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ModuleStat prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements ModuleStatOrBuilder {
            private int bitField0_;
            private float cpuTemperature_;
            private DomainId domain_;
            private int elapsedTime_;
            private Object name_;
            private Status status_;
            private NtfTempLevel_t tempLevel_;
            private float temperature_;
            private float voltage_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_ModuleStat_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_ModuleStat_fieldAccessorTable.ensureFieldAccessorsInitialized(ModuleStat.class, Builder.class);
            }

            private Builder() {
                this.name_ = "";
                this.domain_ = DomainId.DOMAIN_UNKNOWN;
                this.status_ = Status.STAT_UNKNOWN;
                this.tempLevel_ = NtfTempLevel_t.LV_ORDINARY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.name_ = "";
                this.domain_ = DomainId.DOMAIN_UNKNOWN;
                this.status_ = Status.STAT_UNKNOWN;
                this.tempLevel_ = NtfTempLevel_t.LV_ORDINARY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ModuleStat.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.name_ = "";
                this.bitField0_ &= -2;
                this.domain_ = DomainId.DOMAIN_UNKNOWN;
                this.bitField0_ &= -3;
                this.status_ = Status.STAT_UNKNOWN;
                int i = this.bitField0_ & (-5);
                this.bitField0_ = i;
                this.voltage_ = 0.0f;
                int i2 = i & (-9);
                this.bitField0_ = i2;
                this.temperature_ = 0.0f;
                this.bitField0_ = i2 & (-17);
                this.tempLevel_ = NtfTempLevel_t.LV_ORDINARY;
                int i3 = this.bitField0_ & (-33);
                this.bitField0_ = i3;
                this.elapsedTime_ = 0;
                int i4 = i3 & (-65);
                this.bitField0_ = i4;
                this.cpuTemperature_ = 0.0f;
                this.bitField0_ = i4 & (-129);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_ModuleStat_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ModuleStat getDefaultInstanceForType() {
                return ModuleStat.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ModuleStat build() {
                ModuleStat result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ModuleStat buildPartial() {
                ModuleStat result = new ModuleStat(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.name_ = this.name_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.domain_ = this.domain_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.status_ = this.status_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.voltage_ = this.voltage_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.temperature_ = this.temperature_;
                if ((from_bitField0_ & 32) == 32) {
                    to_bitField0_ |= 32;
                }
                result.tempLevel_ = this.tempLevel_;
                if ((from_bitField0_ & 64) == 64) {
                    to_bitField0_ |= 64;
                }
                result.elapsedTime_ = this.elapsedTime_;
                if ((from_bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                    to_bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                }
                result.cpuTemperature_ = this.cpuTemperature_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof ModuleStat) {
                    return mergeFrom((ModuleStat) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(ModuleStat other) {
                if (other == ModuleStat.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.bitField0_ |= 1;
                    this.name_ = other.name_;
                    onChanged();
                }
                if (other.hasDomain()) {
                    setDomain(other.getDomain());
                }
                if (other.hasStatus()) {
                    setStatus(other.getStatus());
                }
                if (other.hasVoltage()) {
                    setVoltage(other.getVoltage());
                }
                if (other.hasTemperature()) {
                    setTemperature(other.getTemperature());
                }
                if (other.hasTempLevel()) {
                    setTempLevel(other.getTempLevel());
                }
                if (other.hasElapsedTime()) {
                    setElapsedTime(other.getElapsedTime());
                }
                if (other.hasCpuTemperature()) {
                    setCpuTemperature(other.getCpuTemperature());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasName() && hasDomain() && hasStatus();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                ModuleStat parsedMessage = null;
                try {
                    try {
                        parsedMessage = ModuleStat.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
            public String getName() {
                Object ref = this.name_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.name_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
            public ByteString getNameBytes() {
                Object ref = this.name_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.name_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setName(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                onChanged();
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= -2;
                this.name_ = ModuleStat.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
            public boolean hasDomain() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
            public DomainId getDomain() {
                return this.domain_;
            }

            public Builder setDomain(DomainId value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.domain_ = value;
                onChanged();
                return this;
            }

            public Builder clearDomain() {
                this.bitField0_ &= -3;
                this.domain_ = DomainId.DOMAIN_UNKNOWN;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
            public boolean hasStatus() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
            public Status getStatus() {
                return this.status_;
            }

            public Builder setStatus(Status value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4;
                this.status_ = value;
                onChanged();
                return this;
            }

            public Builder clearStatus() {
                this.bitField0_ &= -5;
                this.status_ = Status.STAT_UNKNOWN;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
            public boolean hasVoltage() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
            public float getVoltage() {
                return this.voltage_;
            }

            public Builder setVoltage(float value) {
                this.bitField0_ |= 8;
                this.voltage_ = value;
                onChanged();
                return this;
            }

            public Builder clearVoltage() {
                this.bitField0_ &= -9;
                this.voltage_ = 0.0f;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
            public boolean hasTemperature() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
            public float getTemperature() {
                return this.temperature_;
            }

            public Builder setTemperature(float value) {
                this.bitField0_ |= 16;
                this.temperature_ = value;
                onChanged();
                return this;
            }

            public Builder clearTemperature() {
                this.bitField0_ &= -17;
                this.temperature_ = 0.0f;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
            public boolean hasTempLevel() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
            public NtfTempLevel_t getTempLevel() {
                return this.tempLevel_;
            }

            public Builder setTempLevel(NtfTempLevel_t value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 32;
                this.tempLevel_ = value;
                onChanged();
                return this;
            }

            public Builder clearTempLevel() {
                this.bitField0_ &= -33;
                this.tempLevel_ = NtfTempLevel_t.LV_ORDINARY;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
            public boolean hasElapsedTime() {
                return (this.bitField0_ & 64) == 64;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
            public int getElapsedTime() {
                return this.elapsedTime_;
            }

            public Builder setElapsedTime(int value) {
                this.bitField0_ |= 64;
                this.elapsedTime_ = value;
                onChanged();
                return this;
            }

            public Builder clearElapsedTime() {
                this.bitField0_ &= -65;
                this.elapsedTime_ = 0;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
            public boolean hasCpuTemperature() {
                return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleStatOrBuilder
            public float getCpuTemperature() {
                return this.cpuTemperature_;
            }

            public Builder setCpuTemperature(float value) {
                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                this.cpuTemperature_ = value;
                onChanged();
                return this;
            }

            public Builder clearCpuTemperature() {
                this.bitField0_ &= -129;
                this.cpuTemperature_ = 0.0f;
                onChanged();
                return this;
            }
        }
    }

    public static final class ModuleInfo extends GeneratedMessage implements ModuleInfoOrBuilder {
        public static final int COMP_ID_FIELD_NUMBER = 10;
        public static final int DOMAIN_FIELD_NUMBER = 2;
        public static final int FW_VER_FIELD_NUMBER = 5;
        public static final int HW_ID_FIELD_NUMBER = 9;
        public static final int HW_VER_FIELD_NUMBER = 6;
        public static final int INSTALLER_TYPE_FIELD_NUMBER = 12;
        public static final int LCD_TEMP_FIELD_NUMBER = 17;
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int NODE_ADDR_FIELD_NUMBER = 8;
        public static Parser<ModuleInfo> PARSER = new AbstractParser<ModuleInfo>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfo.1
            @Override // com.google.protobuf.Parser
            public ModuleInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new ModuleInfo(input, extensionRegistry);
            }
        };
        public static final int PCB_TEMP_FIELD_NUMBER = 18;
        public static final int PN_FIELD_NUMBER = 3;
        public static final int PROP_VER_FIELD_NUMBER = 15;
        public static final int SDB_VER_FIELD_NUMBER = 14;
        public static final int SER_VER_FIELD_NUMBER = 13;
        public static final int SN_FIELD_NUMBER = 4;
        public static final int SUPPLIER_INFO_FIELD_NUMBER = 16;
        public static final int SW_VER_FIELD_NUMBER = 7;
        public static final int TOOL_VER_FIELD_NUMBER = 11;
        private static final ModuleInfo defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private Object compId_;
        private DomainId domain_;
        private Object fwVer_;
        private Object hwId_;
        private Object hwVer_;
        private Object installerType_;
        private Object lcdTemp_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private Object name_;
        private Object nodeAddr_;
        private Object pcbTemp_;
        private Object pn_;
        private Object propVer_;
        private Object sdbVer_;
        private Object serVer_;
        private Object sn_;
        private Object supplierInfo_;
        private Object swVer_;
        private Object toolVer_;
        private final UnknownFieldSet unknownFields;

        private ModuleInfo(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private ModuleInfo(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ModuleInfo getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ModuleInfo getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ModuleInfo(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    done = true;
                                    break;
                                case 10:
                                    ByteString bs = input.readBytes();
                                    this.bitField0_ |= 1;
                                    this.name_ = bs;
                                    break;
                                case 16:
                                    int rawValue = input.readEnum();
                                    DomainId value = DomainId.valueOf(rawValue);
                                    if (value != null) {
                                        this.bitField0_ = 2 | this.bitField0_;
                                        this.domain_ = value;
                                    } else {
                                        unknownFields.mergeVarintField(2, rawValue);
                                    }
                                    break;
                                case 26:
                                    ByteString bs2 = input.readBytes();
                                    this.bitField0_ |= 4;
                                    this.pn_ = bs2;
                                    break;
                                case DescriptorProtos.MethodOptions.IDEMPOTENCY_LEVEL_FIELD_NUMBER /* 34 */:
                                    ByteString bs3 = input.readBytes();
                                    this.bitField0_ |= 8;
                                    this.sn_ = bs3;
                                    break;
                                case DescriptorProtos.FileOptions.PHP_GENERIC_SERVICES_FIELD_NUMBER /* 42 */:
                                    ByteString bs4 = input.readBytes();
                                    this.bitField0_ |= 16;
                                    this.fwVer_ = bs4;
                                    break;
                                case 50:
                                    ByteString bs5 = input.readBytes();
                                    this.bitField0_ |= 32;
                                    this.hwVer_ = bs5;
                                    break;
                                case FERRY_VALUE:
                                    ByteString bs6 = input.readBytes();
                                    this.bitField0_ |= 64;
                                    this.swVer_ = bs6;
                                    break;
                                case DOUBLE_CROSS_SMALL_ANGLE_RIGHT_LEFT_STRAIGHT_AHEAD_VALUE:
                                    ByteString bs7 = input.readBytes();
                                    this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                                    this.nodeAddr_ = bs7;
                                    break;
                                case THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_3_VALUE:
                                    ByteString bs8 = input.readBytes();
                                    this.bitField0_ |= 256;
                                    this.hwId_ = bs8;
                                    break;
                                case 82:
                                    ByteString bs9 = input.readBytes();
                                    this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE;
                                    this.compId_ = bs9;
                                    break;
                                case 90:
                                    ByteString bs10 = input.readBytes();
                                    this.bitField0_ |= OtaProto.ErrorCode.ERASE_ROUTINE_VALUE;
                                    this.toolVer_ = bs10;
                                    break;
                                case SUSD_SHUTDOWN_PREPARE_COMPLETED_VALUE:
                                    ByteString bs11 = input.readBytes();
                                    this.bitField0_ |= 2048;
                                    this.installerType_ = bs11;
                                    break;
                                case SUSD_TELL_TALE_INFO_VALUE:
                                    ByteString bs12 = input.readBytes();
                                    this.bitField0_ |= 4096;
                                    this.serVer_ = bs12;
                                    break;
                                case 114:
                                    ByteString bs13 = input.readBytes();
                                    this.bitField0_ |= 8192;
                                    this.sdbVer_ = bs13;
                                    break;
                                case 122:
                                    ByteString bs14 = input.readBytes();
                                    this.bitField0_ |= 16384;
                                    this.propVer_ = bs14;
                                    break;
                                case SUSD_POWER_IVI_UI_POPUP_VALUE:
                                    ByteString bs15 = input.readBytes();
                                    this.bitField0_ |= 32768;
                                    this.supplierInfo_ = bs15;
                                    break;
                                case 138:
                                    ByteString bs16 = input.readBytes();
                                    this.bitField0_ |= 65536;
                                    this.lcdTemp_ = bs16;
                                    break;
                                case 146:
                                    ByteString bs17 = input.readBytes();
                                    this.bitField0_ |= 131072;
                                    this.pcbTemp_ = bs17;
                                    break;
                                default:
                                    if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                        done = true;
                                    }
                                    break;
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_ModuleInfo_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_ModuleInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ModuleInfo.class, Builder.class);
        }

        static {
            ModuleInfo moduleInfo = new ModuleInfo(true);
            defaultInstance = moduleInfo;
            moduleInfo.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<ModuleInfo> getParserForType() {
            return PARSER;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public String getName() {
            Object ref = this.name_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.name_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public ByteString getNameBytes() {
            Object ref = this.name_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.name_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public boolean hasDomain() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public DomainId getDomain() {
            return this.domain_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public boolean hasPn() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public String getPn() {
            Object ref = this.pn_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.pn_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public ByteString getPnBytes() {
            Object ref = this.pn_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.pn_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public boolean hasSn() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public String getSn() {
            Object ref = this.sn_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.sn_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public ByteString getSnBytes() {
            Object ref = this.sn_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.sn_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public boolean hasFwVer() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public String getFwVer() {
            Object ref = this.fwVer_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.fwVer_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public ByteString getFwVerBytes() {
            Object ref = this.fwVer_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.fwVer_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public boolean hasHwVer() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public String getHwVer() {
            Object ref = this.hwVer_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.hwVer_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public ByteString getHwVerBytes() {
            Object ref = this.hwVer_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.hwVer_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public boolean hasSwVer() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public String getSwVer() {
            Object ref = this.swVer_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.swVer_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public ByteString getSwVerBytes() {
            Object ref = this.swVer_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.swVer_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public boolean hasNodeAddr() {
            return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public String getNodeAddr() {
            Object ref = this.nodeAddr_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.nodeAddr_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public ByteString getNodeAddrBytes() {
            Object ref = this.nodeAddr_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.nodeAddr_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public boolean hasHwId() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public String getHwId() {
            Object ref = this.hwId_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.hwId_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public ByteString getHwIdBytes() {
            Object ref = this.hwId_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.hwId_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public boolean hasCompId() {
            return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public String getCompId() {
            Object ref = this.compId_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.compId_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public ByteString getCompIdBytes() {
            Object ref = this.compId_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.compId_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public boolean hasToolVer() {
            return (this.bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public String getToolVer() {
            Object ref = this.toolVer_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.toolVer_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public ByteString getToolVerBytes() {
            Object ref = this.toolVer_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.toolVer_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public boolean hasInstallerType() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public String getInstallerType() {
            Object ref = this.installerType_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.installerType_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public ByteString getInstallerTypeBytes() {
            Object ref = this.installerType_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.installerType_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public boolean hasSerVer() {
            return (this.bitField0_ & 4096) == 4096;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public String getSerVer() {
            Object ref = this.serVer_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.serVer_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public ByteString getSerVerBytes() {
            Object ref = this.serVer_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.serVer_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public boolean hasSdbVer() {
            return (this.bitField0_ & 8192) == 8192;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public String getSdbVer() {
            Object ref = this.sdbVer_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.sdbVer_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public ByteString getSdbVerBytes() {
            Object ref = this.sdbVer_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.sdbVer_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public boolean hasPropVer() {
            return (this.bitField0_ & 16384) == 16384;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public String getPropVer() {
            Object ref = this.propVer_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.propVer_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public ByteString getPropVerBytes() {
            Object ref = this.propVer_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.propVer_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public boolean hasSupplierInfo() {
            return (this.bitField0_ & 32768) == 32768;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public String getSupplierInfo() {
            Object ref = this.supplierInfo_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.supplierInfo_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public ByteString getSupplierInfoBytes() {
            Object ref = this.supplierInfo_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.supplierInfo_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public boolean hasLcdTemp() {
            return (this.bitField0_ & 65536) == 65536;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public String getLcdTemp() {
            Object ref = this.lcdTemp_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.lcdTemp_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public ByteString getLcdTempBytes() {
            Object ref = this.lcdTemp_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.lcdTemp_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public boolean hasPcbTemp() {
            return (this.bitField0_ & 131072) == 131072;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public String getPcbTemp() {
            Object ref = this.pcbTemp_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.pcbTemp_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
        public ByteString getPcbTempBytes() {
            Object ref = this.pcbTemp_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.pcbTemp_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        private void initFields() {
            this.name_ = "";
            this.domain_ = DomainId.DOMAIN_UNKNOWN;
            this.pn_ = "";
            this.sn_ = "";
            this.fwVer_ = "";
            this.hwVer_ = "";
            this.swVer_ = "";
            this.nodeAddr_ = "";
            this.hwId_ = "";
            this.compId_ = "";
            this.toolVer_ = "";
            this.installerType_ = "";
            this.serVer_ = "";
            this.sdbVer_ = "";
            this.propVer_ = "";
            this.supplierInfo_ = "";
            this.lcdTemp_ = "";
            this.pcbTemp_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasName()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasDomain()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(1, getNameBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.domain_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBytes(3, getPnBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBytes(4, getSnBytes());
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeBytes(5, getFwVerBytes());
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeBytes(6, getHwVerBytes());
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeBytes(7, getSwVerBytes());
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                output.writeBytes(8, getNodeAddrBytes());
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeBytes(9, getHwIdBytes());
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512) {
                output.writeBytes(10, getCompIdBytes());
            }
            if ((this.bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024) {
                output.writeBytes(11, getToolVerBytes());
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeBytes(12, getInstallerTypeBytes());
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeBytes(13, getSerVerBytes());
            }
            if ((this.bitField0_ & 8192) == 8192) {
                output.writeBytes(14, getSdbVerBytes());
            }
            if ((this.bitField0_ & 16384) == 16384) {
                output.writeBytes(15, getPropVerBytes());
            }
            if ((this.bitField0_ & 32768) == 32768) {
                output.writeBytes(16, getSupplierInfoBytes());
            }
            if ((this.bitField0_ & 65536) == 65536) {
                output.writeBytes(17, getLcdTempBytes());
            }
            if ((this.bitField0_ & 131072) == 131072) {
                output.writeBytes(18, getPcbTempBytes());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeBytesSize(1, getNameBytes()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(2, this.domain_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBytesSize(3, getPnBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeBytesSize(4, getSnBytes());
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeBytesSize(5, getFwVerBytes());
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeBytesSize(6, getHwVerBytes());
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeBytesSize(7, getSwVerBytes());
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                size2 += CodedOutputStream.computeBytesSize(8, getNodeAddrBytes());
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeBytesSize(9, getHwIdBytes());
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512) {
                size2 += CodedOutputStream.computeBytesSize(10, getCompIdBytes());
            }
            if ((this.bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024) {
                size2 += CodedOutputStream.computeBytesSize(11, getToolVerBytes());
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeBytesSize(12, getInstallerTypeBytes());
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size2 += CodedOutputStream.computeBytesSize(13, getSerVerBytes());
            }
            if ((this.bitField0_ & 8192) == 8192) {
                size2 += CodedOutputStream.computeBytesSize(14, getSdbVerBytes());
            }
            if ((this.bitField0_ & 16384) == 16384) {
                size2 += CodedOutputStream.computeBytesSize(15, getPropVerBytes());
            }
            if ((this.bitField0_ & 32768) == 32768) {
                size2 += CodedOutputStream.computeBytesSize(16, getSupplierInfoBytes());
            }
            if ((this.bitField0_ & 65536) == 65536) {
                size2 += CodedOutputStream.computeBytesSize(17, getLcdTempBytes());
            }
            if ((this.bitField0_ & 131072) == 131072) {
                size2 += CodedOutputStream.computeBytesSize(18, getPcbTempBytes());
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static ModuleInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ModuleInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ModuleInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ModuleInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ModuleInfo parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ModuleInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static ModuleInfo parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static ModuleInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static ModuleInfo parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ModuleInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ModuleInfo prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements ModuleInfoOrBuilder {
            private int bitField0_;
            private Object compId_;
            private DomainId domain_;
            private Object fwVer_;
            private Object hwId_;
            private Object hwVer_;
            private Object installerType_;
            private Object lcdTemp_;
            private Object name_;
            private Object nodeAddr_;
            private Object pcbTemp_;
            private Object pn_;
            private Object propVer_;
            private Object sdbVer_;
            private Object serVer_;
            private Object sn_;
            private Object supplierInfo_;
            private Object swVer_;
            private Object toolVer_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_ModuleInfo_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_ModuleInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ModuleInfo.class, Builder.class);
            }

            private Builder() {
                this.name_ = "";
                this.domain_ = DomainId.DOMAIN_UNKNOWN;
                this.pn_ = "";
                this.sn_ = "";
                this.fwVer_ = "";
                this.hwVer_ = "";
                this.swVer_ = "";
                this.nodeAddr_ = "";
                this.hwId_ = "";
                this.compId_ = "";
                this.toolVer_ = "";
                this.installerType_ = "";
                this.serVer_ = "";
                this.sdbVer_ = "";
                this.propVer_ = "";
                this.supplierInfo_ = "";
                this.lcdTemp_ = "";
                this.pcbTemp_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.name_ = "";
                this.domain_ = DomainId.DOMAIN_UNKNOWN;
                this.pn_ = "";
                this.sn_ = "";
                this.fwVer_ = "";
                this.hwVer_ = "";
                this.swVer_ = "";
                this.nodeAddr_ = "";
                this.hwId_ = "";
                this.compId_ = "";
                this.toolVer_ = "";
                this.installerType_ = "";
                this.serVer_ = "";
                this.sdbVer_ = "";
                this.propVer_ = "";
                this.supplierInfo_ = "";
                this.lcdTemp_ = "";
                this.pcbTemp_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ModuleInfo.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.name_ = "";
                this.bitField0_ &= -2;
                this.domain_ = DomainId.DOMAIN_UNKNOWN;
                int i = this.bitField0_ & (-3);
                this.bitField0_ = i;
                this.pn_ = "";
                int i2 = i & (-5);
                this.bitField0_ = i2;
                this.sn_ = "";
                int i3 = i2 & (-9);
                this.bitField0_ = i3;
                this.fwVer_ = "";
                int i4 = i3 & (-17);
                this.bitField0_ = i4;
                this.hwVer_ = "";
                int i5 = i4 & (-33);
                this.bitField0_ = i5;
                this.swVer_ = "";
                int i6 = i5 & (-65);
                this.bitField0_ = i6;
                this.nodeAddr_ = "";
                int i7 = i6 & (-129);
                this.bitField0_ = i7;
                this.hwId_ = "";
                int i8 = i7 & (-257);
                this.bitField0_ = i8;
                this.compId_ = "";
                int i9 = i8 & (-513);
                this.bitField0_ = i9;
                this.toolVer_ = "";
                int i10 = i9 & (-1025);
                this.bitField0_ = i10;
                this.installerType_ = "";
                int i11 = i10 & (-2049);
                this.bitField0_ = i11;
                this.serVer_ = "";
                int i12 = i11 & (-4097);
                this.bitField0_ = i12;
                this.sdbVer_ = "";
                int i13 = i12 & (-8193);
                this.bitField0_ = i13;
                this.propVer_ = "";
                int i14 = i13 & (-16385);
                this.bitField0_ = i14;
                this.supplierInfo_ = "";
                int i15 = i14 & (-32769);
                this.bitField0_ = i15;
                this.lcdTemp_ = "";
                int i16 = i15 & (-65537);
                this.bitField0_ = i16;
                this.pcbTemp_ = "";
                this.bitField0_ = (-131073) & i16;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_ModuleInfo_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ModuleInfo getDefaultInstanceForType() {
                return ModuleInfo.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ModuleInfo build() {
                ModuleInfo result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ModuleInfo buildPartial() {
                ModuleInfo result = new ModuleInfo(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.name_ = this.name_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.domain_ = this.domain_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.pn_ = this.pn_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.sn_ = this.sn_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.fwVer_ = this.fwVer_;
                if ((from_bitField0_ & 32) == 32) {
                    to_bitField0_ |= 32;
                }
                result.hwVer_ = this.hwVer_;
                if ((from_bitField0_ & 64) == 64) {
                    to_bitField0_ |= 64;
                }
                result.swVer_ = this.swVer_;
                if ((from_bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                    to_bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                }
                result.nodeAddr_ = this.nodeAddr_;
                if ((from_bitField0_ & 256) == 256) {
                    to_bitField0_ |= 256;
                }
                result.hwId_ = this.hwId_;
                if ((from_bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512) {
                    to_bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE;
                }
                result.compId_ = this.compId_;
                if ((from_bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024) {
                    to_bitField0_ |= OtaProto.ErrorCode.ERASE_ROUTINE_VALUE;
                }
                result.toolVer_ = this.toolVer_;
                if ((from_bitField0_ & 2048) == 2048) {
                    to_bitField0_ |= 2048;
                }
                result.installerType_ = this.installerType_;
                if ((from_bitField0_ & 4096) == 4096) {
                    to_bitField0_ |= 4096;
                }
                result.serVer_ = this.serVer_;
                if ((from_bitField0_ & 8192) == 8192) {
                    to_bitField0_ |= 8192;
                }
                result.sdbVer_ = this.sdbVer_;
                if ((from_bitField0_ & 16384) == 16384) {
                    to_bitField0_ |= 16384;
                }
                result.propVer_ = this.propVer_;
                if ((from_bitField0_ & 32768) == 32768) {
                    to_bitField0_ |= 32768;
                }
                result.supplierInfo_ = this.supplierInfo_;
                if ((from_bitField0_ & 65536) == 65536) {
                    to_bitField0_ |= 65536;
                }
                result.lcdTemp_ = this.lcdTemp_;
                if ((from_bitField0_ & 131072) == 131072) {
                    to_bitField0_ |= 131072;
                }
                result.pcbTemp_ = this.pcbTemp_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof ModuleInfo) {
                    return mergeFrom((ModuleInfo) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(ModuleInfo other) {
                if (other == ModuleInfo.getDefaultInstance()) {
                    return this;
                }
                if (other.hasName()) {
                    this.bitField0_ |= 1;
                    this.name_ = other.name_;
                    onChanged();
                }
                if (other.hasDomain()) {
                    setDomain(other.getDomain());
                }
                if (other.hasPn()) {
                    this.bitField0_ |= 4;
                    this.pn_ = other.pn_;
                    onChanged();
                }
                if (other.hasSn()) {
                    this.bitField0_ |= 8;
                    this.sn_ = other.sn_;
                    onChanged();
                }
                if (other.hasFwVer()) {
                    this.bitField0_ |= 16;
                    this.fwVer_ = other.fwVer_;
                    onChanged();
                }
                if (other.hasHwVer()) {
                    this.bitField0_ |= 32;
                    this.hwVer_ = other.hwVer_;
                    onChanged();
                }
                if (other.hasSwVer()) {
                    this.bitField0_ |= 64;
                    this.swVer_ = other.swVer_;
                    onChanged();
                }
                if (other.hasNodeAddr()) {
                    this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                    this.nodeAddr_ = other.nodeAddr_;
                    onChanged();
                }
                if (other.hasHwId()) {
                    this.bitField0_ |= 256;
                    this.hwId_ = other.hwId_;
                    onChanged();
                }
                if (other.hasCompId()) {
                    this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE;
                    this.compId_ = other.compId_;
                    onChanged();
                }
                if (other.hasToolVer()) {
                    this.bitField0_ |= OtaProto.ErrorCode.ERASE_ROUTINE_VALUE;
                    this.toolVer_ = other.toolVer_;
                    onChanged();
                }
                if (other.hasInstallerType()) {
                    this.bitField0_ |= 2048;
                    this.installerType_ = other.installerType_;
                    onChanged();
                }
                if (other.hasSerVer()) {
                    this.bitField0_ |= 4096;
                    this.serVer_ = other.serVer_;
                    onChanged();
                }
                if (other.hasSdbVer()) {
                    this.bitField0_ |= 8192;
                    this.sdbVer_ = other.sdbVer_;
                    onChanged();
                }
                if (other.hasPropVer()) {
                    this.bitField0_ |= 16384;
                    this.propVer_ = other.propVer_;
                    onChanged();
                }
                if (other.hasSupplierInfo()) {
                    this.bitField0_ |= 32768;
                    this.supplierInfo_ = other.supplierInfo_;
                    onChanged();
                }
                if (other.hasLcdTemp()) {
                    this.bitField0_ |= 65536;
                    this.lcdTemp_ = other.lcdTemp_;
                    onChanged();
                }
                if (other.hasPcbTemp()) {
                    this.bitField0_ |= 131072;
                    this.pcbTemp_ = other.pcbTemp_;
                    onChanged();
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasName() && hasDomain();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                ModuleInfo parsedMessage = null;
                try {
                    try {
                        parsedMessage = ModuleInfo.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public String getName() {
                Object ref = this.name_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.name_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public ByteString getNameBytes() {
                Object ref = this.name_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.name_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setName(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                onChanged();
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= -2;
                this.name_ = ModuleInfo.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.name_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public boolean hasDomain() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public DomainId getDomain() {
                return this.domain_;
            }

            public Builder setDomain(DomainId value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.domain_ = value;
                onChanged();
                return this;
            }

            public Builder clearDomain() {
                this.bitField0_ &= -3;
                this.domain_ = DomainId.DOMAIN_UNKNOWN;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public boolean hasPn() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public String getPn() {
                Object ref = this.pn_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.pn_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public ByteString getPnBytes() {
                Object ref = this.pn_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.pn_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setPn(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4;
                this.pn_ = value;
                onChanged();
                return this;
            }

            public Builder clearPn() {
                this.bitField0_ &= -5;
                this.pn_ = ModuleInfo.getDefaultInstance().getPn();
                onChanged();
                return this;
            }

            public Builder setPnBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4;
                this.pn_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public boolean hasSn() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public String getSn() {
                Object ref = this.sn_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.sn_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public ByteString getSnBytes() {
                Object ref = this.sn_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.sn_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setSn(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 8;
                this.sn_ = value;
                onChanged();
                return this;
            }

            public Builder clearSn() {
                this.bitField0_ &= -9;
                this.sn_ = ModuleInfo.getDefaultInstance().getSn();
                onChanged();
                return this;
            }

            public Builder setSnBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 8;
                this.sn_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public boolean hasFwVer() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public String getFwVer() {
                Object ref = this.fwVer_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.fwVer_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public ByteString getFwVerBytes() {
                Object ref = this.fwVer_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.fwVer_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setFwVer(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 16;
                this.fwVer_ = value;
                onChanged();
                return this;
            }

            public Builder clearFwVer() {
                this.bitField0_ &= -17;
                this.fwVer_ = ModuleInfo.getDefaultInstance().getFwVer();
                onChanged();
                return this;
            }

            public Builder setFwVerBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 16;
                this.fwVer_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public boolean hasHwVer() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public String getHwVer() {
                Object ref = this.hwVer_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.hwVer_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public ByteString getHwVerBytes() {
                Object ref = this.hwVer_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.hwVer_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setHwVer(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 32;
                this.hwVer_ = value;
                onChanged();
                return this;
            }

            public Builder clearHwVer() {
                this.bitField0_ &= -33;
                this.hwVer_ = ModuleInfo.getDefaultInstance().getHwVer();
                onChanged();
                return this;
            }

            public Builder setHwVerBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 32;
                this.hwVer_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public boolean hasSwVer() {
                return (this.bitField0_ & 64) == 64;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public String getSwVer() {
                Object ref = this.swVer_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.swVer_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public ByteString getSwVerBytes() {
                Object ref = this.swVer_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.swVer_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setSwVer(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 64;
                this.swVer_ = value;
                onChanged();
                return this;
            }

            public Builder clearSwVer() {
                this.bitField0_ &= -65;
                this.swVer_ = ModuleInfo.getDefaultInstance().getSwVer();
                onChanged();
                return this;
            }

            public Builder setSwVerBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 64;
                this.swVer_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public boolean hasNodeAddr() {
                return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public String getNodeAddr() {
                Object ref = this.nodeAddr_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.nodeAddr_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public ByteString getNodeAddrBytes() {
                Object ref = this.nodeAddr_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.nodeAddr_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setNodeAddr(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                this.nodeAddr_ = value;
                onChanged();
                return this;
            }

            public Builder clearNodeAddr() {
                this.bitField0_ &= -129;
                this.nodeAddr_ = ModuleInfo.getDefaultInstance().getNodeAddr();
                onChanged();
                return this;
            }

            public Builder setNodeAddrBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                this.nodeAddr_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public boolean hasHwId() {
                return (this.bitField0_ & 256) == 256;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public String getHwId() {
                Object ref = this.hwId_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.hwId_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public ByteString getHwIdBytes() {
                Object ref = this.hwId_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.hwId_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setHwId(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 256;
                this.hwId_ = value;
                onChanged();
                return this;
            }

            public Builder clearHwId() {
                this.bitField0_ &= -257;
                this.hwId_ = ModuleInfo.getDefaultInstance().getHwId();
                onChanged();
                return this;
            }

            public Builder setHwIdBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 256;
                this.hwId_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public boolean hasCompId() {
                return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public String getCompId() {
                Object ref = this.compId_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.compId_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public ByteString getCompIdBytes() {
                Object ref = this.compId_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.compId_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setCompId(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE;
                this.compId_ = value;
                onChanged();
                return this;
            }

            public Builder clearCompId() {
                this.bitField0_ &= -513;
                this.compId_ = ModuleInfo.getDefaultInstance().getCompId();
                onChanged();
                return this;
            }

            public Builder setCompIdBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE;
                this.compId_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public boolean hasToolVer() {
                return (this.bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public String getToolVer() {
                Object ref = this.toolVer_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.toolVer_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public ByteString getToolVerBytes() {
                Object ref = this.toolVer_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.toolVer_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setToolVer(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= OtaProto.ErrorCode.ERASE_ROUTINE_VALUE;
                this.toolVer_ = value;
                onChanged();
                return this;
            }

            public Builder clearToolVer() {
                this.bitField0_ &= -1025;
                this.toolVer_ = ModuleInfo.getDefaultInstance().getToolVer();
                onChanged();
                return this;
            }

            public Builder setToolVerBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= OtaProto.ErrorCode.ERASE_ROUTINE_VALUE;
                this.toolVer_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public boolean hasInstallerType() {
                return (this.bitField0_ & 2048) == 2048;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public String getInstallerType() {
                Object ref = this.installerType_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.installerType_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public ByteString getInstallerTypeBytes() {
                Object ref = this.installerType_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.installerType_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setInstallerType(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2048;
                this.installerType_ = value;
                onChanged();
                return this;
            }

            public Builder clearInstallerType() {
                this.bitField0_ &= -2049;
                this.installerType_ = ModuleInfo.getDefaultInstance().getInstallerType();
                onChanged();
                return this;
            }

            public Builder setInstallerTypeBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2048;
                this.installerType_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public boolean hasSerVer() {
                return (this.bitField0_ & 4096) == 4096;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public String getSerVer() {
                Object ref = this.serVer_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.serVer_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public ByteString getSerVerBytes() {
                Object ref = this.serVer_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.serVer_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setSerVer(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4096;
                this.serVer_ = value;
                onChanged();
                return this;
            }

            public Builder clearSerVer() {
                this.bitField0_ &= -4097;
                this.serVer_ = ModuleInfo.getDefaultInstance().getSerVer();
                onChanged();
                return this;
            }

            public Builder setSerVerBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4096;
                this.serVer_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public boolean hasSdbVer() {
                return (this.bitField0_ & 8192) == 8192;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public String getSdbVer() {
                Object ref = this.sdbVer_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.sdbVer_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public ByteString getSdbVerBytes() {
                Object ref = this.sdbVer_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.sdbVer_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setSdbVer(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 8192;
                this.sdbVer_ = value;
                onChanged();
                return this;
            }

            public Builder clearSdbVer() {
                this.bitField0_ &= -8193;
                this.sdbVer_ = ModuleInfo.getDefaultInstance().getSdbVer();
                onChanged();
                return this;
            }

            public Builder setSdbVerBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 8192;
                this.sdbVer_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public boolean hasPropVer() {
                return (this.bitField0_ & 16384) == 16384;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public String getPropVer() {
                Object ref = this.propVer_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.propVer_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public ByteString getPropVerBytes() {
                Object ref = this.propVer_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.propVer_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setPropVer(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 16384;
                this.propVer_ = value;
                onChanged();
                return this;
            }

            public Builder clearPropVer() {
                this.bitField0_ &= -16385;
                this.propVer_ = ModuleInfo.getDefaultInstance().getPropVer();
                onChanged();
                return this;
            }

            public Builder setPropVerBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 16384;
                this.propVer_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public boolean hasSupplierInfo() {
                return (this.bitField0_ & 32768) == 32768;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public String getSupplierInfo() {
                Object ref = this.supplierInfo_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.supplierInfo_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public ByteString getSupplierInfoBytes() {
                Object ref = this.supplierInfo_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.supplierInfo_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setSupplierInfo(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 32768;
                this.supplierInfo_ = value;
                onChanged();
                return this;
            }

            public Builder clearSupplierInfo() {
                this.bitField0_ &= -32769;
                this.supplierInfo_ = ModuleInfo.getDefaultInstance().getSupplierInfo();
                onChanged();
                return this;
            }

            public Builder setSupplierInfoBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 32768;
                this.supplierInfo_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public boolean hasLcdTemp() {
                return (this.bitField0_ & 65536) == 65536;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public String getLcdTemp() {
                Object ref = this.lcdTemp_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.lcdTemp_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public ByteString getLcdTempBytes() {
                Object ref = this.lcdTemp_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.lcdTemp_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setLcdTemp(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 65536;
                this.lcdTemp_ = value;
                onChanged();
                return this;
            }

            public Builder clearLcdTemp() {
                this.bitField0_ &= -65537;
                this.lcdTemp_ = ModuleInfo.getDefaultInstance().getLcdTemp();
                onChanged();
                return this;
            }

            public Builder setLcdTempBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 65536;
                this.lcdTemp_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public boolean hasPcbTemp() {
                return (this.bitField0_ & 131072) == 131072;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public String getPcbTemp() {
                Object ref = this.pcbTemp_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.pcbTemp_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.ModuleInfoOrBuilder
            public ByteString getPcbTempBytes() {
                Object ref = this.pcbTemp_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.pcbTemp_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setPcbTemp(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 131072;
                this.pcbTemp_ = value;
                onChanged();
                return this;
            }

            public Builder clearPcbTemp() {
                this.bitField0_ &= -131073;
                this.pcbTemp_ = ModuleInfo.getDefaultInstance().getPcbTemp();
                onChanged();
                return this;
            }

            public Builder setPcbTempBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 131072;
                this.pcbTemp_ = value;
                onChanged();
                return this;
            }
        }
    }

    public static final class BuildInfo extends GeneratedMessage implements BuildInfoOrBuilder {
        public static final int BOOT_VER_FIELD_NUMBER = 16;
        public static final int BUILD_DATE_FIELD_NUMBER = 1;
        public static final int BUILD_ID_FIELD_NUMBER = 6;
        public static final int BUILD_NUMBER_FIELD_NUMBER = 5;
        public static final int BUILD_TARGET_FIELD_NUMBER = 4;
        public static final int JOB_NAME_FIELD_NUMBER = 7;
        public static final int MAIN_VER_FIELD_NUMBER = 3;
        public static final int MAIN_VER_NAME_FIELD_NUMBER = 2;
        public static final int MANIFEST_REV_FIELD_NUMBER = 12;
        public static Parser<BuildInfo> PARSER = new AbstractParser<BuildInfo>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfo.1
            @Override // com.google.protobuf.Parser
            public BuildInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new BuildInfo(input, extensionRegistry);
            }
        };
        public static final int SCC_APP_VER_FIELD_NUMBER = 10;
        public static final int SCC_BL_VER_FIELD_NUMBER = 11;
        public static final int SCC_BYD_APP_VER_FIELD_NUMBER = 13;
        public static final int SCC_BYD_BL_VER_FIELD_NUMBER = 14;
        public static final int SCC_VARIANT_CODE_FIELD_NUMBER = 15;
        public static final int SOC_ANDROID_VER_FIELD_NUMBER = 9;
        public static final int SOC_QNX_VER_FIELD_NUMBER = 8;
        private static final BuildInfo defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private Object bootVer_;
        private Object buildDate_;
        private Object buildId_;
        private Object buildNumber_;
        private Object buildTarget_;
        private Object jobName_;
        private Object mainVerName_;
        private Object mainVer_;
        private Object manifestRev_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private Object sccAppVer_;
        private Object sccBlVer_;
        private Object sccBydAppVer_;
        private Object sccBydBlVer_;
        private Object sccVariantCode_;
        private Object socAndroidVer_;
        private Object socQnxVer_;
        private final UnknownFieldSet unknownFields;

        private BuildInfo(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private BuildInfo(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static BuildInfo getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public BuildInfo getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private BuildInfo(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    done = true;
                                    break;
                                case 10:
                                    ByteString bs = input.readBytes();
                                    this.bitField0_ |= 1;
                                    this.buildDate_ = bs;
                                    break;
                                case 18:
                                    ByteString bs2 = input.readBytes();
                                    this.bitField0_ |= 2;
                                    this.mainVerName_ = bs2;
                                    break;
                                case 26:
                                    ByteString bs3 = input.readBytes();
                                    this.bitField0_ |= 4;
                                    this.mainVer_ = bs3;
                                    break;
                                case DescriptorProtos.MethodOptions.IDEMPOTENCY_LEVEL_FIELD_NUMBER /* 34 */:
                                    ByteString bs4 = input.readBytes();
                                    this.bitField0_ |= 8;
                                    this.buildTarget_ = bs4;
                                    break;
                                case DescriptorProtos.FileOptions.PHP_GENERIC_SERVICES_FIELD_NUMBER /* 42 */:
                                    ByteString bs5 = input.readBytes();
                                    this.bitField0_ |= 16;
                                    this.buildNumber_ = bs5;
                                    break;
                                case 50:
                                    ByteString bs6 = input.readBytes();
                                    this.bitField0_ |= 32;
                                    this.buildId_ = bs6;
                                    break;
                                case FERRY_VALUE:
                                    ByteString bs7 = input.readBytes();
                                    this.bitField0_ |= 64;
                                    this.jobName_ = bs7;
                                    break;
                                case DOUBLE_CROSS_SMALL_ANGLE_RIGHT_LEFT_STRAIGHT_AHEAD_VALUE:
                                    ByteString bs8 = input.readBytes();
                                    this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                                    this.socQnxVer_ = bs8;
                                    break;
                                case THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_3_VALUE:
                                    ByteString bs9 = input.readBytes();
                                    this.bitField0_ |= 256;
                                    this.socAndroidVer_ = bs9;
                                    break;
                                case 82:
                                    ByteString bs10 = input.readBytes();
                                    this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE;
                                    this.sccAppVer_ = bs10;
                                    break;
                                case 90:
                                    ByteString bs11 = input.readBytes();
                                    this.bitField0_ |= OtaProto.ErrorCode.ERASE_ROUTINE_VALUE;
                                    this.sccBlVer_ = bs11;
                                    break;
                                case SUSD_SHUTDOWN_PREPARE_COMPLETED_VALUE:
                                    ByteString bs12 = input.readBytes();
                                    this.bitField0_ |= 2048;
                                    this.manifestRev_ = bs12;
                                    break;
                                case SUSD_TELL_TALE_INFO_VALUE:
                                    ByteString bs13 = input.readBytes();
                                    this.bitField0_ |= 4096;
                                    this.sccBydAppVer_ = bs13;
                                    break;
                                case 114:
                                    ByteString bs14 = input.readBytes();
                                    this.bitField0_ |= 8192;
                                    this.sccBydBlVer_ = bs14;
                                    break;
                                case 122:
                                    ByteString bs15 = input.readBytes();
                                    this.bitField0_ |= 16384;
                                    this.sccVariantCode_ = bs15;
                                    break;
                                case SUSD_POWER_IVI_UI_POPUP_VALUE:
                                    ByteString bs16 = input.readBytes();
                                    this.bitField0_ |= 32768;
                                    this.bootVer_ = bs16;
                                    break;
                                default:
                                    if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                        done = true;
                                    }
                                    break;
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_BuildInfo_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_BuildInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(BuildInfo.class, Builder.class);
        }

        static {
            BuildInfo buildInfo = new BuildInfo(true);
            defaultInstance = buildInfo;
            buildInfo.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<BuildInfo> getParserForType() {
            return PARSER;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public boolean hasBuildDate() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public String getBuildDate() {
            Object ref = this.buildDate_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.buildDate_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public ByteString getBuildDateBytes() {
            Object ref = this.buildDate_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.buildDate_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public boolean hasMainVerName() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public String getMainVerName() {
            Object ref = this.mainVerName_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.mainVerName_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public ByteString getMainVerNameBytes() {
            Object ref = this.mainVerName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.mainVerName_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public boolean hasMainVer() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public String getMainVer() {
            Object ref = this.mainVer_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.mainVer_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public ByteString getMainVerBytes() {
            Object ref = this.mainVer_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.mainVer_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public boolean hasBuildTarget() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public String getBuildTarget() {
            Object ref = this.buildTarget_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.buildTarget_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public ByteString getBuildTargetBytes() {
            Object ref = this.buildTarget_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.buildTarget_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public boolean hasBuildNumber() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public String getBuildNumber() {
            Object ref = this.buildNumber_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.buildNumber_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public ByteString getBuildNumberBytes() {
            Object ref = this.buildNumber_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.buildNumber_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public boolean hasBuildId() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public String getBuildId() {
            Object ref = this.buildId_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.buildId_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public ByteString getBuildIdBytes() {
            Object ref = this.buildId_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.buildId_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public boolean hasJobName() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public String getJobName() {
            Object ref = this.jobName_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.jobName_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public ByteString getJobNameBytes() {
            Object ref = this.jobName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.jobName_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public boolean hasSocQnxVer() {
            return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public String getSocQnxVer() {
            Object ref = this.socQnxVer_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.socQnxVer_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public ByteString getSocQnxVerBytes() {
            Object ref = this.socQnxVer_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.socQnxVer_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public boolean hasSocAndroidVer() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public String getSocAndroidVer() {
            Object ref = this.socAndroidVer_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.socAndroidVer_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public ByteString getSocAndroidVerBytes() {
            Object ref = this.socAndroidVer_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.socAndroidVer_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public boolean hasSccAppVer() {
            return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public String getSccAppVer() {
            Object ref = this.sccAppVer_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.sccAppVer_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public ByteString getSccAppVerBytes() {
            Object ref = this.sccAppVer_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.sccAppVer_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public boolean hasSccBlVer() {
            return (this.bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public String getSccBlVer() {
            Object ref = this.sccBlVer_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.sccBlVer_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public ByteString getSccBlVerBytes() {
            Object ref = this.sccBlVer_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.sccBlVer_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public boolean hasManifestRev() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public String getManifestRev() {
            Object ref = this.manifestRev_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.manifestRev_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public ByteString getManifestRevBytes() {
            Object ref = this.manifestRev_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.manifestRev_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public boolean hasSccBydAppVer() {
            return (this.bitField0_ & 4096) == 4096;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public String getSccBydAppVer() {
            Object ref = this.sccBydAppVer_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.sccBydAppVer_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public ByteString getSccBydAppVerBytes() {
            Object ref = this.sccBydAppVer_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.sccBydAppVer_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public boolean hasSccBydBlVer() {
            return (this.bitField0_ & 8192) == 8192;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public String getSccBydBlVer() {
            Object ref = this.sccBydBlVer_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.sccBydBlVer_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public ByteString getSccBydBlVerBytes() {
            Object ref = this.sccBydBlVer_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.sccBydBlVer_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public boolean hasSccVariantCode() {
            return (this.bitField0_ & 16384) == 16384;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public String getSccVariantCode() {
            Object ref = this.sccVariantCode_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.sccVariantCode_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public ByteString getSccVariantCodeBytes() {
            Object ref = this.sccVariantCode_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.sccVariantCode_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public boolean hasBootVer() {
            return (this.bitField0_ & 32768) == 32768;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public String getBootVer() {
            Object ref = this.bootVer_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.bootVer_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
        public ByteString getBootVerBytes() {
            Object ref = this.bootVer_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.bootVer_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        private void initFields() {
            this.buildDate_ = "";
            this.mainVerName_ = "";
            this.mainVer_ = "";
            this.buildTarget_ = "";
            this.buildNumber_ = "";
            this.buildId_ = "";
            this.jobName_ = "";
            this.socQnxVer_ = "";
            this.socAndroidVer_ = "";
            this.sccAppVer_ = "";
            this.sccBlVer_ = "";
            this.manifestRev_ = "";
            this.sccBydAppVer_ = "";
            this.sccBydBlVer_ = "";
            this.sccVariantCode_ = "";
            this.bootVer_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(1, getBuildDateBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, getMainVerNameBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBytes(3, getMainVerBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBytes(4, getBuildTargetBytes());
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeBytes(5, getBuildNumberBytes());
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeBytes(6, getBuildIdBytes());
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeBytes(7, getJobNameBytes());
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                output.writeBytes(8, getSocQnxVerBytes());
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeBytes(9, getSocAndroidVerBytes());
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512) {
                output.writeBytes(10, getSccAppVerBytes());
            }
            if ((this.bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024) {
                output.writeBytes(11, getSccBlVerBytes());
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeBytes(12, getManifestRevBytes());
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeBytes(13, getSccBydAppVerBytes());
            }
            if ((this.bitField0_ & 8192) == 8192) {
                output.writeBytes(14, getSccBydBlVerBytes());
            }
            if ((this.bitField0_ & 16384) == 16384) {
                output.writeBytes(15, getSccVariantCodeBytes());
            }
            if ((this.bitField0_ & 32768) == 32768) {
                output.writeBytes(16, getBootVerBytes());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeBytesSize(1, getBuildDateBytes()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBytesSize(2, getMainVerNameBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBytesSize(3, getMainVerBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeBytesSize(4, getBuildTargetBytes());
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeBytesSize(5, getBuildNumberBytes());
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeBytesSize(6, getBuildIdBytes());
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeBytesSize(7, getJobNameBytes());
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                size2 += CodedOutputStream.computeBytesSize(8, getSocQnxVerBytes());
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeBytesSize(9, getSocAndroidVerBytes());
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512) {
                size2 += CodedOutputStream.computeBytesSize(10, getSccAppVerBytes());
            }
            if ((this.bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024) {
                size2 += CodedOutputStream.computeBytesSize(11, getSccBlVerBytes());
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeBytesSize(12, getManifestRevBytes());
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size2 += CodedOutputStream.computeBytesSize(13, getSccBydAppVerBytes());
            }
            if ((this.bitField0_ & 8192) == 8192) {
                size2 += CodedOutputStream.computeBytesSize(14, getSccBydBlVerBytes());
            }
            if ((this.bitField0_ & 16384) == 16384) {
                size2 += CodedOutputStream.computeBytesSize(15, getSccVariantCodeBytes());
            }
            if ((this.bitField0_ & 32768) == 32768) {
                size2 += CodedOutputStream.computeBytesSize(16, getBootVerBytes());
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static BuildInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static BuildInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static BuildInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static BuildInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static BuildInfo parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static BuildInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static BuildInfo parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static BuildInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static BuildInfo parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static BuildInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(BuildInfo prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements BuildInfoOrBuilder {
            private int bitField0_;
            private Object bootVer_;
            private Object buildDate_;
            private Object buildId_;
            private Object buildNumber_;
            private Object buildTarget_;
            private Object jobName_;
            private Object mainVerName_;
            private Object mainVer_;
            private Object manifestRev_;
            private Object sccAppVer_;
            private Object sccBlVer_;
            private Object sccBydAppVer_;
            private Object sccBydBlVer_;
            private Object sccVariantCode_;
            private Object socAndroidVer_;
            private Object socQnxVer_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_BuildInfo_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_BuildInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(BuildInfo.class, Builder.class);
            }

            private Builder() {
                this.buildDate_ = "";
                this.mainVerName_ = "";
                this.mainVer_ = "";
                this.buildTarget_ = "";
                this.buildNumber_ = "";
                this.buildId_ = "";
                this.jobName_ = "";
                this.socQnxVer_ = "";
                this.socAndroidVer_ = "";
                this.sccAppVer_ = "";
                this.sccBlVer_ = "";
                this.manifestRev_ = "";
                this.sccBydAppVer_ = "";
                this.sccBydBlVer_ = "";
                this.sccVariantCode_ = "";
                this.bootVer_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.buildDate_ = "";
                this.mainVerName_ = "";
                this.mainVer_ = "";
                this.buildTarget_ = "";
                this.buildNumber_ = "";
                this.buildId_ = "";
                this.jobName_ = "";
                this.socQnxVer_ = "";
                this.socAndroidVer_ = "";
                this.sccAppVer_ = "";
                this.sccBlVer_ = "";
                this.manifestRev_ = "";
                this.sccBydAppVer_ = "";
                this.sccBydBlVer_ = "";
                this.sccVariantCode_ = "";
                this.bootVer_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = BuildInfo.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.buildDate_ = "";
                int i = this.bitField0_ & (-2);
                this.bitField0_ = i;
                this.mainVerName_ = "";
                int i2 = i & (-3);
                this.bitField0_ = i2;
                this.mainVer_ = "";
                int i3 = i2 & (-5);
                this.bitField0_ = i3;
                this.buildTarget_ = "";
                int i4 = i3 & (-9);
                this.bitField0_ = i4;
                this.buildNumber_ = "";
                int i5 = i4 & (-17);
                this.bitField0_ = i5;
                this.buildId_ = "";
                int i6 = i5 & (-33);
                this.bitField0_ = i6;
                this.jobName_ = "";
                int i7 = i6 & (-65);
                this.bitField0_ = i7;
                this.socQnxVer_ = "";
                int i8 = i7 & (-129);
                this.bitField0_ = i8;
                this.socAndroidVer_ = "";
                int i9 = i8 & (-257);
                this.bitField0_ = i9;
                this.sccAppVer_ = "";
                int i10 = i9 & (-513);
                this.bitField0_ = i10;
                this.sccBlVer_ = "";
                int i11 = i10 & (-1025);
                this.bitField0_ = i11;
                this.manifestRev_ = "";
                int i12 = i11 & (-2049);
                this.bitField0_ = i12;
                this.sccBydAppVer_ = "";
                int i13 = i12 & (-4097);
                this.bitField0_ = i13;
                this.sccBydBlVer_ = "";
                int i14 = i13 & (-8193);
                this.bitField0_ = i14;
                this.sccVariantCode_ = "";
                int i15 = i14 & (-16385);
                this.bitField0_ = i15;
                this.bootVer_ = "";
                this.bitField0_ = (-32769) & i15;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_BuildInfo_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public BuildInfo getDefaultInstanceForType() {
                return BuildInfo.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public BuildInfo build() {
                BuildInfo result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public BuildInfo buildPartial() {
                BuildInfo result = new BuildInfo(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.buildDate_ = this.buildDate_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.mainVerName_ = this.mainVerName_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.mainVer_ = this.mainVer_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.buildTarget_ = this.buildTarget_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.buildNumber_ = this.buildNumber_;
                if ((from_bitField0_ & 32) == 32) {
                    to_bitField0_ |= 32;
                }
                result.buildId_ = this.buildId_;
                if ((from_bitField0_ & 64) == 64) {
                    to_bitField0_ |= 64;
                }
                result.jobName_ = this.jobName_;
                if ((from_bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                    to_bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                }
                result.socQnxVer_ = this.socQnxVer_;
                if ((from_bitField0_ & 256) == 256) {
                    to_bitField0_ |= 256;
                }
                result.socAndroidVer_ = this.socAndroidVer_;
                if ((from_bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512) {
                    to_bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE;
                }
                result.sccAppVer_ = this.sccAppVer_;
                if ((from_bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024) {
                    to_bitField0_ |= OtaProto.ErrorCode.ERASE_ROUTINE_VALUE;
                }
                result.sccBlVer_ = this.sccBlVer_;
                if ((from_bitField0_ & 2048) == 2048) {
                    to_bitField0_ |= 2048;
                }
                result.manifestRev_ = this.manifestRev_;
                if ((from_bitField0_ & 4096) == 4096) {
                    to_bitField0_ |= 4096;
                }
                result.sccBydAppVer_ = this.sccBydAppVer_;
                if ((from_bitField0_ & 8192) == 8192) {
                    to_bitField0_ |= 8192;
                }
                result.sccBydBlVer_ = this.sccBydBlVer_;
                if ((from_bitField0_ & 16384) == 16384) {
                    to_bitField0_ |= 16384;
                }
                result.sccVariantCode_ = this.sccVariantCode_;
                if ((from_bitField0_ & 32768) == 32768) {
                    to_bitField0_ |= 32768;
                }
                result.bootVer_ = this.bootVer_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof BuildInfo) {
                    return mergeFrom((BuildInfo) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(BuildInfo other) {
                if (other == BuildInfo.getDefaultInstance()) {
                    return this;
                }
                if (other.hasBuildDate()) {
                    this.bitField0_ |= 1;
                    this.buildDate_ = other.buildDate_;
                    onChanged();
                }
                if (other.hasMainVerName()) {
                    this.bitField0_ |= 2;
                    this.mainVerName_ = other.mainVerName_;
                    onChanged();
                }
                if (other.hasMainVer()) {
                    this.bitField0_ |= 4;
                    this.mainVer_ = other.mainVer_;
                    onChanged();
                }
                if (other.hasBuildTarget()) {
                    this.bitField0_ |= 8;
                    this.buildTarget_ = other.buildTarget_;
                    onChanged();
                }
                if (other.hasBuildNumber()) {
                    this.bitField0_ |= 16;
                    this.buildNumber_ = other.buildNumber_;
                    onChanged();
                }
                if (other.hasBuildId()) {
                    this.bitField0_ |= 32;
                    this.buildId_ = other.buildId_;
                    onChanged();
                }
                if (other.hasJobName()) {
                    this.bitField0_ |= 64;
                    this.jobName_ = other.jobName_;
                    onChanged();
                }
                if (other.hasSocQnxVer()) {
                    this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                    this.socQnxVer_ = other.socQnxVer_;
                    onChanged();
                }
                if (other.hasSocAndroidVer()) {
                    this.bitField0_ |= 256;
                    this.socAndroidVer_ = other.socAndroidVer_;
                    onChanged();
                }
                if (other.hasSccAppVer()) {
                    this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE;
                    this.sccAppVer_ = other.sccAppVer_;
                    onChanged();
                }
                if (other.hasSccBlVer()) {
                    this.bitField0_ |= OtaProto.ErrorCode.ERASE_ROUTINE_VALUE;
                    this.sccBlVer_ = other.sccBlVer_;
                    onChanged();
                }
                if (other.hasManifestRev()) {
                    this.bitField0_ |= 2048;
                    this.manifestRev_ = other.manifestRev_;
                    onChanged();
                }
                if (other.hasSccBydAppVer()) {
                    this.bitField0_ |= 4096;
                    this.sccBydAppVer_ = other.sccBydAppVer_;
                    onChanged();
                }
                if (other.hasSccBydBlVer()) {
                    this.bitField0_ |= 8192;
                    this.sccBydBlVer_ = other.sccBydBlVer_;
                    onChanged();
                }
                if (other.hasSccVariantCode()) {
                    this.bitField0_ |= 16384;
                    this.sccVariantCode_ = other.sccVariantCode_;
                    onChanged();
                }
                if (other.hasBootVer()) {
                    this.bitField0_ |= 32768;
                    this.bootVer_ = other.bootVer_;
                    onChanged();
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                BuildInfo parsedMessage = null;
                try {
                    try {
                        parsedMessage = BuildInfo.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public boolean hasBuildDate() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public String getBuildDate() {
                Object ref = this.buildDate_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.buildDate_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public ByteString getBuildDateBytes() {
                Object ref = this.buildDate_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.buildDate_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setBuildDate(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.buildDate_ = value;
                onChanged();
                return this;
            }

            public Builder clearBuildDate() {
                this.bitField0_ &= -2;
                this.buildDate_ = BuildInfo.getDefaultInstance().getBuildDate();
                onChanged();
                return this;
            }

            public Builder setBuildDateBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.buildDate_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public boolean hasMainVerName() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public String getMainVerName() {
                Object ref = this.mainVerName_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.mainVerName_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public ByteString getMainVerNameBytes() {
                Object ref = this.mainVerName_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.mainVerName_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setMainVerName(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.mainVerName_ = value;
                onChanged();
                return this;
            }

            public Builder clearMainVerName() {
                this.bitField0_ &= -3;
                this.mainVerName_ = BuildInfo.getDefaultInstance().getMainVerName();
                onChanged();
                return this;
            }

            public Builder setMainVerNameBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.mainVerName_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public boolean hasMainVer() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public String getMainVer() {
                Object ref = this.mainVer_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.mainVer_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public ByteString getMainVerBytes() {
                Object ref = this.mainVer_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.mainVer_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setMainVer(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4;
                this.mainVer_ = value;
                onChanged();
                return this;
            }

            public Builder clearMainVer() {
                this.bitField0_ &= -5;
                this.mainVer_ = BuildInfo.getDefaultInstance().getMainVer();
                onChanged();
                return this;
            }

            public Builder setMainVerBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4;
                this.mainVer_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public boolean hasBuildTarget() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public String getBuildTarget() {
                Object ref = this.buildTarget_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.buildTarget_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public ByteString getBuildTargetBytes() {
                Object ref = this.buildTarget_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.buildTarget_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setBuildTarget(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 8;
                this.buildTarget_ = value;
                onChanged();
                return this;
            }

            public Builder clearBuildTarget() {
                this.bitField0_ &= -9;
                this.buildTarget_ = BuildInfo.getDefaultInstance().getBuildTarget();
                onChanged();
                return this;
            }

            public Builder setBuildTargetBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 8;
                this.buildTarget_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public boolean hasBuildNumber() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public String getBuildNumber() {
                Object ref = this.buildNumber_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.buildNumber_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public ByteString getBuildNumberBytes() {
                Object ref = this.buildNumber_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.buildNumber_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setBuildNumber(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 16;
                this.buildNumber_ = value;
                onChanged();
                return this;
            }

            public Builder clearBuildNumber() {
                this.bitField0_ &= -17;
                this.buildNumber_ = BuildInfo.getDefaultInstance().getBuildNumber();
                onChanged();
                return this;
            }

            public Builder setBuildNumberBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 16;
                this.buildNumber_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public boolean hasBuildId() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public String getBuildId() {
                Object ref = this.buildId_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.buildId_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public ByteString getBuildIdBytes() {
                Object ref = this.buildId_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.buildId_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setBuildId(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 32;
                this.buildId_ = value;
                onChanged();
                return this;
            }

            public Builder clearBuildId() {
                this.bitField0_ &= -33;
                this.buildId_ = BuildInfo.getDefaultInstance().getBuildId();
                onChanged();
                return this;
            }

            public Builder setBuildIdBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 32;
                this.buildId_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public boolean hasJobName() {
                return (this.bitField0_ & 64) == 64;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public String getJobName() {
                Object ref = this.jobName_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.jobName_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public ByteString getJobNameBytes() {
                Object ref = this.jobName_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.jobName_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setJobName(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 64;
                this.jobName_ = value;
                onChanged();
                return this;
            }

            public Builder clearJobName() {
                this.bitField0_ &= -65;
                this.jobName_ = BuildInfo.getDefaultInstance().getJobName();
                onChanged();
                return this;
            }

            public Builder setJobNameBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 64;
                this.jobName_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public boolean hasSocQnxVer() {
                return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public String getSocQnxVer() {
                Object ref = this.socQnxVer_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.socQnxVer_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public ByteString getSocQnxVerBytes() {
                Object ref = this.socQnxVer_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.socQnxVer_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setSocQnxVer(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                this.socQnxVer_ = value;
                onChanged();
                return this;
            }

            public Builder clearSocQnxVer() {
                this.bitField0_ &= -129;
                this.socQnxVer_ = BuildInfo.getDefaultInstance().getSocQnxVer();
                onChanged();
                return this;
            }

            public Builder setSocQnxVerBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                this.socQnxVer_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public boolean hasSocAndroidVer() {
                return (this.bitField0_ & 256) == 256;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public String getSocAndroidVer() {
                Object ref = this.socAndroidVer_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.socAndroidVer_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public ByteString getSocAndroidVerBytes() {
                Object ref = this.socAndroidVer_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.socAndroidVer_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setSocAndroidVer(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 256;
                this.socAndroidVer_ = value;
                onChanged();
                return this;
            }

            public Builder clearSocAndroidVer() {
                this.bitField0_ &= -257;
                this.socAndroidVer_ = BuildInfo.getDefaultInstance().getSocAndroidVer();
                onChanged();
                return this;
            }

            public Builder setSocAndroidVerBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 256;
                this.socAndroidVer_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public boolean hasSccAppVer() {
                return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public String getSccAppVer() {
                Object ref = this.sccAppVer_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.sccAppVer_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public ByteString getSccAppVerBytes() {
                Object ref = this.sccAppVer_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.sccAppVer_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setSccAppVer(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE;
                this.sccAppVer_ = value;
                onChanged();
                return this;
            }

            public Builder clearSccAppVer() {
                this.bitField0_ &= -513;
                this.sccAppVer_ = BuildInfo.getDefaultInstance().getSccAppVer();
                onChanged();
                return this;
            }

            public Builder setSccAppVerBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE;
                this.sccAppVer_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public boolean hasSccBlVer() {
                return (this.bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public String getSccBlVer() {
                Object ref = this.sccBlVer_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.sccBlVer_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public ByteString getSccBlVerBytes() {
                Object ref = this.sccBlVer_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.sccBlVer_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setSccBlVer(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= OtaProto.ErrorCode.ERASE_ROUTINE_VALUE;
                this.sccBlVer_ = value;
                onChanged();
                return this;
            }

            public Builder clearSccBlVer() {
                this.bitField0_ &= -1025;
                this.sccBlVer_ = BuildInfo.getDefaultInstance().getSccBlVer();
                onChanged();
                return this;
            }

            public Builder setSccBlVerBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= OtaProto.ErrorCode.ERASE_ROUTINE_VALUE;
                this.sccBlVer_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public boolean hasManifestRev() {
                return (this.bitField0_ & 2048) == 2048;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public String getManifestRev() {
                Object ref = this.manifestRev_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.manifestRev_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public ByteString getManifestRevBytes() {
                Object ref = this.manifestRev_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.manifestRev_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setManifestRev(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2048;
                this.manifestRev_ = value;
                onChanged();
                return this;
            }

            public Builder clearManifestRev() {
                this.bitField0_ &= -2049;
                this.manifestRev_ = BuildInfo.getDefaultInstance().getManifestRev();
                onChanged();
                return this;
            }

            public Builder setManifestRevBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2048;
                this.manifestRev_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public boolean hasSccBydAppVer() {
                return (this.bitField0_ & 4096) == 4096;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public String getSccBydAppVer() {
                Object ref = this.sccBydAppVer_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.sccBydAppVer_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public ByteString getSccBydAppVerBytes() {
                Object ref = this.sccBydAppVer_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.sccBydAppVer_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setSccBydAppVer(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4096;
                this.sccBydAppVer_ = value;
                onChanged();
                return this;
            }

            public Builder clearSccBydAppVer() {
                this.bitField0_ &= -4097;
                this.sccBydAppVer_ = BuildInfo.getDefaultInstance().getSccBydAppVer();
                onChanged();
                return this;
            }

            public Builder setSccBydAppVerBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4096;
                this.sccBydAppVer_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public boolean hasSccBydBlVer() {
                return (this.bitField0_ & 8192) == 8192;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public String getSccBydBlVer() {
                Object ref = this.sccBydBlVer_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.sccBydBlVer_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public ByteString getSccBydBlVerBytes() {
                Object ref = this.sccBydBlVer_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.sccBydBlVer_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setSccBydBlVer(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 8192;
                this.sccBydBlVer_ = value;
                onChanged();
                return this;
            }

            public Builder clearSccBydBlVer() {
                this.bitField0_ &= -8193;
                this.sccBydBlVer_ = BuildInfo.getDefaultInstance().getSccBydBlVer();
                onChanged();
                return this;
            }

            public Builder setSccBydBlVerBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 8192;
                this.sccBydBlVer_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public boolean hasSccVariantCode() {
                return (this.bitField0_ & 16384) == 16384;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public String getSccVariantCode() {
                Object ref = this.sccVariantCode_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.sccVariantCode_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public ByteString getSccVariantCodeBytes() {
                Object ref = this.sccVariantCode_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.sccVariantCode_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setSccVariantCode(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 16384;
                this.sccVariantCode_ = value;
                onChanged();
                return this;
            }

            public Builder clearSccVariantCode() {
                this.bitField0_ &= -16385;
                this.sccVariantCode_ = BuildInfo.getDefaultInstance().getSccVariantCode();
                onChanged();
                return this;
            }

            public Builder setSccVariantCodeBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 16384;
                this.sccVariantCode_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public boolean hasBootVer() {
                return (this.bitField0_ & 32768) == 32768;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public String getBootVer() {
                Object ref = this.bootVer_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.bootVer_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.BuildInfoOrBuilder
            public ByteString getBootVerBytes() {
                Object ref = this.bootVer_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.bootVer_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setBootVer(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 32768;
                this.bootVer_ = value;
                onChanged();
                return this;
            }

            public Builder clearBootVer() {
                this.bitField0_ &= -32769;
                this.bootVer_ = BuildInfo.getDefaultInstance().getBootVer();
                onChanged();
                return this;
            }

            public Builder setBootVerBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 32768;
                this.bootVer_ = value;
                onChanged();
                return this;
            }
        }
    }

    public static final class DispPwrSplyStat extends GeneratedMessage implements DispPwrSplyStatOrBuilder {
        public static final int CLUSTER_STAT_FIELD_NUMBER = 1;
        public static final int HUD_STAT_FIELD_NUMBER = 4;
        public static final int HVAC_STAT_FIELD_NUMBER = 3;
        public static final int IVI_STAT_FIELD_NUMBER = 2;
        public static Parser<DispPwrSplyStat> PARSER = new AbstractParser<DispPwrSplyStat>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispPwrSplyStat.1
            @Override // com.google.protobuf.Parser
            public DispPwrSplyStat parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new DispPwrSplyStat(input, extensionRegistry);
            }
        };
        private static final DispPwrSplyStat defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private PwrSplyStat clusterStat_;
        private PwrSplyStat hudStat_;
        private PwrSplyStat hvacStat_;
        private PwrSplyStat iviStat_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        private DispPwrSplyStat(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private DispPwrSplyStat(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static DispPwrSplyStat getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public DispPwrSplyStat getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private DispPwrSplyStat(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 8) {
                                int rawValue = input.readEnum();
                                PwrSplyStat value = PwrSplyStat.valueOf(rawValue);
                                if (value != null) {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.clusterStat_ = value;
                                } else {
                                    unknownFields.mergeVarintField(1, rawValue);
                                }
                            } else if (tag == 16) {
                                int rawValue2 = input.readEnum();
                                PwrSplyStat value2 = PwrSplyStat.valueOf(rawValue2);
                                if (value2 != null) {
                                    this.bitField0_ = 2 | this.bitField0_;
                                    this.iviStat_ = value2;
                                } else {
                                    unknownFields.mergeVarintField(2, rawValue2);
                                }
                            } else if (tag == 24) {
                                int rawValue3 = input.readEnum();
                                PwrSplyStat value3 = PwrSplyStat.valueOf(rawValue3);
                                if (value3 == null) {
                                    unknownFields.mergeVarintField(3, rawValue3);
                                } else {
                                    this.bitField0_ = 4 | this.bitField0_;
                                    this.hvacStat_ = value3;
                                }
                            } else if (tag != 32) {
                                if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                    done = true;
                                }
                            } else {
                                int rawValue4 = input.readEnum();
                                PwrSplyStat value4 = PwrSplyStat.valueOf(rawValue4);
                                if (value4 == null) {
                                    unknownFields.mergeVarintField(4, rawValue4);
                                } else {
                                    this.bitField0_ = 8 | this.bitField0_;
                                    this.hudStat_ = value4;
                                }
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_DispPwrSplyStat_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_DispPwrSplyStat_fieldAccessorTable.ensureFieldAccessorsInitialized(DispPwrSplyStat.class, Builder.class);
        }

        static {
            DispPwrSplyStat dispPwrSplyStat = new DispPwrSplyStat(true);
            defaultInstance = dispPwrSplyStat;
            dispPwrSplyStat.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<DispPwrSplyStat> getParserForType() {
            return PARSER;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispPwrSplyStatOrBuilder
        public boolean hasClusterStat() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispPwrSplyStatOrBuilder
        public PwrSplyStat getClusterStat() {
            return this.clusterStat_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispPwrSplyStatOrBuilder
        public boolean hasIviStat() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispPwrSplyStatOrBuilder
        public PwrSplyStat getIviStat() {
            return this.iviStat_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispPwrSplyStatOrBuilder
        public boolean hasHvacStat() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispPwrSplyStatOrBuilder
        public PwrSplyStat getHvacStat() {
            return this.hvacStat_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispPwrSplyStatOrBuilder
        public boolean hasHudStat() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispPwrSplyStatOrBuilder
        public PwrSplyStat getHudStat() {
            return this.hudStat_;
        }

        private void initFields() {
            this.clusterStat_ = PwrSplyStat.PWR_SPLY_OFF;
            this.iviStat_ = PwrSplyStat.PWR_SPLY_OFF;
            this.hvacStat_ = PwrSplyStat.PWR_SPLY_OFF;
            this.hudStat_ = PwrSplyStat.PWR_SPLY_OFF;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasClusterStat()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasIviStat()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasHvacStat()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasHudStat()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.clusterStat_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.iviStat_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeEnum(3, this.hvacStat_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeEnum(4, this.hudStat_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.clusterStat_.getNumber()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(2, this.iviStat_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeEnumSize(3, this.hvacStat_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeEnumSize(4, this.hudStat_.getNumber());
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static DispPwrSplyStat parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static DispPwrSplyStat parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static DispPwrSplyStat parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static DispPwrSplyStat parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static DispPwrSplyStat parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static DispPwrSplyStat parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static DispPwrSplyStat parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static DispPwrSplyStat parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static DispPwrSplyStat parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static DispPwrSplyStat parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(DispPwrSplyStat prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements DispPwrSplyStatOrBuilder {
            private int bitField0_;
            private PwrSplyStat clusterStat_;
            private PwrSplyStat hudStat_;
            private PwrSplyStat hvacStat_;
            private PwrSplyStat iviStat_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_DispPwrSplyStat_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_DispPwrSplyStat_fieldAccessorTable.ensureFieldAccessorsInitialized(DispPwrSplyStat.class, Builder.class);
            }

            private Builder() {
                this.clusterStat_ = PwrSplyStat.PWR_SPLY_OFF;
                this.iviStat_ = PwrSplyStat.PWR_SPLY_OFF;
                this.hvacStat_ = PwrSplyStat.PWR_SPLY_OFF;
                this.hudStat_ = PwrSplyStat.PWR_SPLY_OFF;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.clusterStat_ = PwrSplyStat.PWR_SPLY_OFF;
                this.iviStat_ = PwrSplyStat.PWR_SPLY_OFF;
                this.hvacStat_ = PwrSplyStat.PWR_SPLY_OFF;
                this.hudStat_ = PwrSplyStat.PWR_SPLY_OFF;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = DispPwrSplyStat.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.clusterStat_ = PwrSplyStat.PWR_SPLY_OFF;
                this.bitField0_ &= -2;
                this.iviStat_ = PwrSplyStat.PWR_SPLY_OFF;
                this.bitField0_ &= -3;
                this.hvacStat_ = PwrSplyStat.PWR_SPLY_OFF;
                this.bitField0_ &= -5;
                this.hudStat_ = PwrSplyStat.PWR_SPLY_OFF;
                this.bitField0_ &= -9;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_DispPwrSplyStat_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public DispPwrSplyStat getDefaultInstanceForType() {
                return DispPwrSplyStat.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public DispPwrSplyStat build() {
                DispPwrSplyStat result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public DispPwrSplyStat buildPartial() {
                DispPwrSplyStat result = new DispPwrSplyStat(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.clusterStat_ = this.clusterStat_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.iviStat_ = this.iviStat_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.hvacStat_ = this.hvacStat_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.hudStat_ = this.hudStat_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof DispPwrSplyStat) {
                    return mergeFrom((DispPwrSplyStat) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(DispPwrSplyStat other) {
                if (other == DispPwrSplyStat.getDefaultInstance()) {
                    return this;
                }
                if (other.hasClusterStat()) {
                    setClusterStat(other.getClusterStat());
                }
                if (other.hasIviStat()) {
                    setIviStat(other.getIviStat());
                }
                if (other.hasHvacStat()) {
                    setHvacStat(other.getHvacStat());
                }
                if (other.hasHudStat()) {
                    setHudStat(other.getHudStat());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasClusterStat() && hasIviStat() && hasHvacStat() && hasHudStat();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                DispPwrSplyStat parsedMessage = null;
                try {
                    try {
                        parsedMessage = DispPwrSplyStat.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispPwrSplyStatOrBuilder
            public boolean hasClusterStat() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispPwrSplyStatOrBuilder
            public PwrSplyStat getClusterStat() {
                return this.clusterStat_;
            }

            public Builder setClusterStat(PwrSplyStat value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.clusterStat_ = value;
                onChanged();
                return this;
            }

            public Builder clearClusterStat() {
                this.bitField0_ &= -2;
                this.clusterStat_ = PwrSplyStat.PWR_SPLY_OFF;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispPwrSplyStatOrBuilder
            public boolean hasIviStat() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispPwrSplyStatOrBuilder
            public PwrSplyStat getIviStat() {
                return this.iviStat_;
            }

            public Builder setIviStat(PwrSplyStat value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.iviStat_ = value;
                onChanged();
                return this;
            }

            public Builder clearIviStat() {
                this.bitField0_ &= -3;
                this.iviStat_ = PwrSplyStat.PWR_SPLY_OFF;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispPwrSplyStatOrBuilder
            public boolean hasHvacStat() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispPwrSplyStatOrBuilder
            public PwrSplyStat getHvacStat() {
                return this.hvacStat_;
            }

            public Builder setHvacStat(PwrSplyStat value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4;
                this.hvacStat_ = value;
                onChanged();
                return this;
            }

            public Builder clearHvacStat() {
                this.bitField0_ &= -5;
                this.hvacStat_ = PwrSplyStat.PWR_SPLY_OFF;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispPwrSplyStatOrBuilder
            public boolean hasHudStat() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispPwrSplyStatOrBuilder
            public PwrSplyStat getHudStat() {
                return this.hudStat_;
            }

            public Builder setHudStat(PwrSplyStat value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 8;
                this.hudStat_ = value;
                onChanged();
                return this;
            }

            public Builder clearHudStat() {
                this.bitField0_ &= -9;
                this.hudStat_ = PwrSplyStat.PWR_SPLY_OFF;
                onChanged();
                return this;
            }
        }
    }

    public static final class PwrModeStat extends GeneratedMessage implements PwrModeStatOrBuilder {
        public static final int IPK_POWER_MODE_FIELD_NUMBER = 4;
        public static Parser<PwrModeStat> PARSER = new AbstractParser<PwrModeStat>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PwrModeStat.1
            @Override // com.google.protobuf.Parser
            public PwrModeStat parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new PwrModeStat(input, extensionRegistry);
            }
        };
        public static final int POWER_MODE_FIELD_NUMBER = 1;
        public static final int SAVE_MODE_FIELD_NUMBER = 3;
        public static final int VOLTAGE_STATE_FIELD_NUMBER = 2;
        private static final PwrModeStat defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private NtfIpkPowerMode ipkPowerMode_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private NtfPowerMode powerMode_;
        private NtfPowerSaveMode saveMode_;
        private final UnknownFieldSet unknownFields;
        private NtfVoltageState voltageState_;

        private PwrModeStat(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private PwrModeStat(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static PwrModeStat getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public PwrModeStat getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private PwrModeStat(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 8) {
                                int rawValue = input.readEnum();
                                NtfPowerMode value = NtfPowerMode.valueOf(rawValue);
                                if (value != null) {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.powerMode_ = value;
                                } else {
                                    unknownFields.mergeVarintField(1, rawValue);
                                }
                            } else if (tag == 16) {
                                int rawValue2 = input.readEnum();
                                NtfVoltageState value2 = NtfVoltageState.valueOf(rawValue2);
                                if (value2 != null) {
                                    this.bitField0_ = 2 | this.bitField0_;
                                    this.voltageState_ = value2;
                                } else {
                                    unknownFields.mergeVarintField(2, rawValue2);
                                }
                            } else if (tag == 24) {
                                int rawValue3 = input.readEnum();
                                NtfPowerSaveMode value3 = NtfPowerSaveMode.valueOf(rawValue3);
                                if (value3 == null) {
                                    unknownFields.mergeVarintField(3, rawValue3);
                                } else {
                                    this.bitField0_ = 4 | this.bitField0_;
                                    this.saveMode_ = value3;
                                }
                            } else if (tag != 32) {
                                if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                    done = true;
                                }
                            } else {
                                int rawValue4 = input.readEnum();
                                NtfIpkPowerMode value4 = NtfIpkPowerMode.valueOf(rawValue4);
                                if (value4 == null) {
                                    unknownFields.mergeVarintField(4, rawValue4);
                                } else {
                                    this.bitField0_ = 8 | this.bitField0_;
                                    this.ipkPowerMode_ = value4;
                                }
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_PwrModeStat_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_PwrModeStat_fieldAccessorTable.ensureFieldAccessorsInitialized(PwrModeStat.class, Builder.class);
        }

        static {
            PwrModeStat pwrModeStat = new PwrModeStat(true);
            defaultInstance = pwrModeStat;
            pwrModeStat.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<PwrModeStat> getParserForType() {
            return PARSER;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PwrModeStatOrBuilder
        public boolean hasPowerMode() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PwrModeStatOrBuilder
        public NtfPowerMode getPowerMode() {
            return this.powerMode_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PwrModeStatOrBuilder
        public boolean hasVoltageState() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PwrModeStatOrBuilder
        public NtfVoltageState getVoltageState() {
            return this.voltageState_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PwrModeStatOrBuilder
        public boolean hasSaveMode() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PwrModeStatOrBuilder
        public NtfPowerSaveMode getSaveMode() {
            return this.saveMode_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PwrModeStatOrBuilder
        public boolean hasIpkPowerMode() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PwrModeStatOrBuilder
        public NtfIpkPowerMode getIpkPowerMode() {
            return this.ipkPowerMode_;
        }

        private void initFields() {
            this.powerMode_ = NtfPowerMode.NTF_PM_OFF;
            this.voltageState_ = NtfVoltageState.NTF_VOLTAGE_BEYOND_CRITICAL_HIGH_3;
            this.saveMode_ = NtfPowerSaveMode.NTF_TRANPORT_OFF;
            this.ipkPowerMode_ = NtfIpkPowerMode.NTF_IPK_PREPARE_START;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasPowerMode()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasVoltageState()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.powerMode_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.voltageState_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeEnum(3, this.saveMode_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeEnum(4, this.ipkPowerMode_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.powerMode_.getNumber()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(2, this.voltageState_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeEnumSize(3, this.saveMode_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeEnumSize(4, this.ipkPowerMode_.getNumber());
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static PwrModeStat parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static PwrModeStat parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static PwrModeStat parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static PwrModeStat parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static PwrModeStat parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static PwrModeStat parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static PwrModeStat parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static PwrModeStat parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static PwrModeStat parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static PwrModeStat parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(PwrModeStat prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements PwrModeStatOrBuilder {
            private int bitField0_;
            private NtfIpkPowerMode ipkPowerMode_;
            private NtfPowerMode powerMode_;
            private NtfPowerSaveMode saveMode_;
            private NtfVoltageState voltageState_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_PwrModeStat_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_PwrModeStat_fieldAccessorTable.ensureFieldAccessorsInitialized(PwrModeStat.class, Builder.class);
            }

            private Builder() {
                this.powerMode_ = NtfPowerMode.NTF_PM_OFF;
                this.voltageState_ = NtfVoltageState.NTF_VOLTAGE_BEYOND_CRITICAL_HIGH_3;
                this.saveMode_ = NtfPowerSaveMode.NTF_TRANPORT_OFF;
                this.ipkPowerMode_ = NtfIpkPowerMode.NTF_IPK_PREPARE_START;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.powerMode_ = NtfPowerMode.NTF_PM_OFF;
                this.voltageState_ = NtfVoltageState.NTF_VOLTAGE_BEYOND_CRITICAL_HIGH_3;
                this.saveMode_ = NtfPowerSaveMode.NTF_TRANPORT_OFF;
                this.ipkPowerMode_ = NtfIpkPowerMode.NTF_IPK_PREPARE_START;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = PwrModeStat.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.powerMode_ = NtfPowerMode.NTF_PM_OFF;
                this.bitField0_ &= -2;
                this.voltageState_ = NtfVoltageState.NTF_VOLTAGE_BEYOND_CRITICAL_HIGH_3;
                this.bitField0_ &= -3;
                this.saveMode_ = NtfPowerSaveMode.NTF_TRANPORT_OFF;
                this.bitField0_ &= -5;
                this.ipkPowerMode_ = NtfIpkPowerMode.NTF_IPK_PREPARE_START;
                this.bitField0_ &= -9;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_PwrModeStat_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public PwrModeStat getDefaultInstanceForType() {
                return PwrModeStat.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public PwrModeStat build() {
                PwrModeStat result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public PwrModeStat buildPartial() {
                PwrModeStat result = new PwrModeStat(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.powerMode_ = this.powerMode_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.voltageState_ = this.voltageState_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.saveMode_ = this.saveMode_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.ipkPowerMode_ = this.ipkPowerMode_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof PwrModeStat) {
                    return mergeFrom((PwrModeStat) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(PwrModeStat other) {
                if (other == PwrModeStat.getDefaultInstance()) {
                    return this;
                }
                if (other.hasPowerMode()) {
                    setPowerMode(other.getPowerMode());
                }
                if (other.hasVoltageState()) {
                    setVoltageState(other.getVoltageState());
                }
                if (other.hasSaveMode()) {
                    setSaveMode(other.getSaveMode());
                }
                if (other.hasIpkPowerMode()) {
                    setIpkPowerMode(other.getIpkPowerMode());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasPowerMode() && hasVoltageState();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                PwrModeStat parsedMessage = null;
                try {
                    try {
                        parsedMessage = PwrModeStat.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PwrModeStatOrBuilder
            public boolean hasPowerMode() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PwrModeStatOrBuilder
            public NtfPowerMode getPowerMode() {
                return this.powerMode_;
            }

            public Builder setPowerMode(NtfPowerMode value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.powerMode_ = value;
                onChanged();
                return this;
            }

            public Builder clearPowerMode() {
                this.bitField0_ &= -2;
                this.powerMode_ = NtfPowerMode.NTF_PM_OFF;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PwrModeStatOrBuilder
            public boolean hasVoltageState() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PwrModeStatOrBuilder
            public NtfVoltageState getVoltageState() {
                return this.voltageState_;
            }

            public Builder setVoltageState(NtfVoltageState value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.voltageState_ = value;
                onChanged();
                return this;
            }

            public Builder clearVoltageState() {
                this.bitField0_ &= -3;
                this.voltageState_ = NtfVoltageState.NTF_VOLTAGE_BEYOND_CRITICAL_HIGH_3;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PwrModeStatOrBuilder
            public boolean hasSaveMode() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PwrModeStatOrBuilder
            public NtfPowerSaveMode getSaveMode() {
                return this.saveMode_;
            }

            public Builder setSaveMode(NtfPowerSaveMode value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4;
                this.saveMode_ = value;
                onChanged();
                return this;
            }

            public Builder clearSaveMode() {
                this.bitField0_ &= -5;
                this.saveMode_ = NtfPowerSaveMode.NTF_TRANPORT_OFF;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PwrModeStatOrBuilder
            public boolean hasIpkPowerMode() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PwrModeStatOrBuilder
            public NtfIpkPowerMode getIpkPowerMode() {
                return this.ipkPowerMode_;
            }

            public Builder setIpkPowerMode(NtfIpkPowerMode value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 8;
                this.ipkPowerMode_ = value;
                onChanged();
                return this;
            }

            public Builder clearIpkPowerMode() {
                this.bitField0_ &= -9;
                this.ipkPowerMode_ = NtfIpkPowerMode.NTF_IPK_PREPARE_START;
                onChanged();
                return this;
            }
        }
    }

    public static final class LcmHmiModeEvents extends GeneratedMessage implements LcmHmiModeEventsOrBuilder {
        public static final int CAR_LOCKED_FIELD_NUMBER = 3;
        public static final int CHARGING_INFO_FIELD_NUMBER = 4;
        public static final int DOOR_OPENED_FIELD_NUMBER = 1;
        public static final int LCD_WARNING_FIELD_NUMBER = 2;
        public static Parser<LcmHmiModeEvents> PARSER = new AbstractParser<LcmHmiModeEvents>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEvents.1
            @Override // com.google.protobuf.Parser
            public LcmHmiModeEvents parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new LcmHmiModeEvents(input, extensionRegistry);
            }
        };
        public static final int TT_INFO_FIELD_NUMBER = 6;
        public static final int WARN_INFO_FIELD_NUMBER = 5;
        private static final LcmHmiModeEvents defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private NTFLockSts carLocked_;
        private NTFChargingsts chargingInfo_;
        private NTFDrvDoorSts doorOpened_;
        private NTFWarningSts lcdWarning_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int ttInfo_;
        private final UnknownFieldSet unknownFields;
        private NTFWarningSts warnInfo_;

        private LcmHmiModeEvents(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private LcmHmiModeEvents(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static LcmHmiModeEvents getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public LcmHmiModeEvents getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private LcmHmiModeEvents(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 8) {
                            int rawValue = input.readEnum();
                            NTFDrvDoorSts value = NTFDrvDoorSts.valueOf(rawValue);
                            if (value != null) {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.doorOpened_ = value;
                            } else {
                                unknownFields.mergeVarintField(1, rawValue);
                            }
                        } else if (tag == 16) {
                            int rawValue2 = input.readEnum();
                            NTFWarningSts value2 = NTFWarningSts.valueOf(rawValue2);
                            if (value2 != null) {
                                this.bitField0_ = 2 | this.bitField0_;
                                this.lcdWarning_ = value2;
                            } else {
                                unknownFields.mergeVarintField(2, rawValue2);
                            }
                        } else if (tag == 24) {
                            int rawValue3 = input.readEnum();
                            NTFLockSts value3 = NTFLockSts.valueOf(rawValue3);
                            if (value3 == null) {
                                unknownFields.mergeVarintField(3, rawValue3);
                            } else {
                                this.bitField0_ |= 4;
                                this.carLocked_ = value3;
                            }
                        } else if (tag == 32) {
                            int rawValue4 = input.readEnum();
                            NTFChargingsts value4 = NTFChargingsts.valueOf(rawValue4);
                            if (value4 == null) {
                                unknownFields.mergeVarintField(4, rawValue4);
                            } else {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.chargingInfo_ = value4;
                            }
                        } else if (tag == 40) {
                            int rawValue5 = input.readEnum();
                            NTFWarningSts value5 = NTFWarningSts.valueOf(rawValue5);
                            if (value5 == null) {
                                unknownFields.mergeVarintField(5, rawValue5);
                            } else {
                                this.bitField0_ = 16 | this.bitField0_;
                                this.warnInfo_ = value5;
                            }
                        } else if (tag != 48) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            this.bitField0_ |= 32;
                            this.ttInfo_ = input.readUInt32();
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_LcmHmiModeEvents_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_LcmHmiModeEvents_fieldAccessorTable.ensureFieldAccessorsInitialized(LcmHmiModeEvents.class, Builder.class);
        }

        static {
            LcmHmiModeEvents lcmHmiModeEvents = new LcmHmiModeEvents(true);
            defaultInstance = lcmHmiModeEvents;
            lcmHmiModeEvents.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<LcmHmiModeEvents> getParserForType() {
            return PARSER;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
        public boolean hasDoorOpened() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
        public NTFDrvDoorSts getDoorOpened() {
            return this.doorOpened_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
        public boolean hasLcdWarning() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
        public NTFWarningSts getLcdWarning() {
            return this.lcdWarning_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
        public boolean hasCarLocked() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
        public NTFLockSts getCarLocked() {
            return this.carLocked_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
        public boolean hasChargingInfo() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
        public NTFChargingsts getChargingInfo() {
            return this.chargingInfo_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
        public boolean hasWarnInfo() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
        public NTFWarningSts getWarnInfo() {
            return this.warnInfo_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
        public boolean hasTtInfo() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
        public int getTtInfo() {
            return this.ttInfo_;
        }

        private void initFields() {
            this.doorOpened_ = NTFDrvDoorSts.NTF_DOOR_CLOSE;
            this.lcdWarning_ = NTFWarningSts.NTF_WARNING_OFF;
            this.carLocked_ = NTFLockSts.NTF_LOCK_OFF;
            this.chargingInfo_ = NTFChargingsts.NTF_CHARGING_OFF;
            this.warnInfo_ = NTFWarningSts.NTF_WARNING_OFF;
            this.ttInfo_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasDoorOpened()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasLcdWarning()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasCarLocked()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasChargingInfo()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasWarnInfo()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasTtInfo()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.doorOpened_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.lcdWarning_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeEnum(3, this.carLocked_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeEnum(4, this.chargingInfo_.getNumber());
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeEnum(5, this.warnInfo_.getNumber());
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeUInt32(6, this.ttInfo_);
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.doorOpened_.getNumber()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(2, this.lcdWarning_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeEnumSize(3, this.carLocked_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeEnumSize(4, this.chargingInfo_.getNumber());
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeEnumSize(5, this.warnInfo_.getNumber());
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeUInt32Size(6, this.ttInfo_);
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static LcmHmiModeEvents parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static LcmHmiModeEvents parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static LcmHmiModeEvents parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static LcmHmiModeEvents parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static LcmHmiModeEvents parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static LcmHmiModeEvents parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static LcmHmiModeEvents parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static LcmHmiModeEvents parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static LcmHmiModeEvents parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static LcmHmiModeEvents parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(LcmHmiModeEvents prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements LcmHmiModeEventsOrBuilder {
            private int bitField0_;
            private NTFLockSts carLocked_;
            private NTFChargingsts chargingInfo_;
            private NTFDrvDoorSts doorOpened_;
            private NTFWarningSts lcdWarning_;
            private int ttInfo_;
            private NTFWarningSts warnInfo_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_LcmHmiModeEvents_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_LcmHmiModeEvents_fieldAccessorTable.ensureFieldAccessorsInitialized(LcmHmiModeEvents.class, Builder.class);
            }

            private Builder() {
                this.doorOpened_ = NTFDrvDoorSts.NTF_DOOR_CLOSE;
                this.lcdWarning_ = NTFWarningSts.NTF_WARNING_OFF;
                this.carLocked_ = NTFLockSts.NTF_LOCK_OFF;
                this.chargingInfo_ = NTFChargingsts.NTF_CHARGING_OFF;
                this.warnInfo_ = NTFWarningSts.NTF_WARNING_OFF;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.doorOpened_ = NTFDrvDoorSts.NTF_DOOR_CLOSE;
                this.lcdWarning_ = NTFWarningSts.NTF_WARNING_OFF;
                this.carLocked_ = NTFLockSts.NTF_LOCK_OFF;
                this.chargingInfo_ = NTFChargingsts.NTF_CHARGING_OFF;
                this.warnInfo_ = NTFWarningSts.NTF_WARNING_OFF;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = LcmHmiModeEvents.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.doorOpened_ = NTFDrvDoorSts.NTF_DOOR_CLOSE;
                this.bitField0_ &= -2;
                this.lcdWarning_ = NTFWarningSts.NTF_WARNING_OFF;
                this.bitField0_ &= -3;
                this.carLocked_ = NTFLockSts.NTF_LOCK_OFF;
                this.bitField0_ &= -5;
                this.chargingInfo_ = NTFChargingsts.NTF_CHARGING_OFF;
                this.bitField0_ &= -9;
                this.warnInfo_ = NTFWarningSts.NTF_WARNING_OFF;
                int i = this.bitField0_ & (-17);
                this.bitField0_ = i;
                this.ttInfo_ = 0;
                this.bitField0_ = i & (-33);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_LcmHmiModeEvents_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public LcmHmiModeEvents getDefaultInstanceForType() {
                return LcmHmiModeEvents.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public LcmHmiModeEvents build() {
                LcmHmiModeEvents result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public LcmHmiModeEvents buildPartial() {
                LcmHmiModeEvents result = new LcmHmiModeEvents(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.doorOpened_ = this.doorOpened_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.lcdWarning_ = this.lcdWarning_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.carLocked_ = this.carLocked_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.chargingInfo_ = this.chargingInfo_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.warnInfo_ = this.warnInfo_;
                if ((from_bitField0_ & 32) == 32) {
                    to_bitField0_ |= 32;
                }
                result.ttInfo_ = this.ttInfo_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof LcmHmiModeEvents) {
                    return mergeFrom((LcmHmiModeEvents) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(LcmHmiModeEvents other) {
                if (other == LcmHmiModeEvents.getDefaultInstance()) {
                    return this;
                }
                if (other.hasDoorOpened()) {
                    setDoorOpened(other.getDoorOpened());
                }
                if (other.hasLcdWarning()) {
                    setLcdWarning(other.getLcdWarning());
                }
                if (other.hasCarLocked()) {
                    setCarLocked(other.getCarLocked());
                }
                if (other.hasChargingInfo()) {
                    setChargingInfo(other.getChargingInfo());
                }
                if (other.hasWarnInfo()) {
                    setWarnInfo(other.getWarnInfo());
                }
                if (other.hasTtInfo()) {
                    setTtInfo(other.getTtInfo());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasDoorOpened() && hasLcdWarning() && hasCarLocked() && hasChargingInfo() && hasWarnInfo() && hasTtInfo();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                LcmHmiModeEvents parsedMessage = null;
                try {
                    try {
                        parsedMessage = LcmHmiModeEvents.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
            public boolean hasDoorOpened() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
            public NTFDrvDoorSts getDoorOpened() {
                return this.doorOpened_;
            }

            public Builder setDoorOpened(NTFDrvDoorSts value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.doorOpened_ = value;
                onChanged();
                return this;
            }

            public Builder clearDoorOpened() {
                this.bitField0_ &= -2;
                this.doorOpened_ = NTFDrvDoorSts.NTF_DOOR_CLOSE;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
            public boolean hasLcdWarning() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
            public NTFWarningSts getLcdWarning() {
                return this.lcdWarning_;
            }

            public Builder setLcdWarning(NTFWarningSts value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.lcdWarning_ = value;
                onChanged();
                return this;
            }

            public Builder clearLcdWarning() {
                this.bitField0_ &= -3;
                this.lcdWarning_ = NTFWarningSts.NTF_WARNING_OFF;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
            public boolean hasCarLocked() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
            public NTFLockSts getCarLocked() {
                return this.carLocked_;
            }

            public Builder setCarLocked(NTFLockSts value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4;
                this.carLocked_ = value;
                onChanged();
                return this;
            }

            public Builder clearCarLocked() {
                this.bitField0_ &= -5;
                this.carLocked_ = NTFLockSts.NTF_LOCK_OFF;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
            public boolean hasChargingInfo() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
            public NTFChargingsts getChargingInfo() {
                return this.chargingInfo_;
            }

            public Builder setChargingInfo(NTFChargingsts value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 8;
                this.chargingInfo_ = value;
                onChanged();
                return this;
            }

            public Builder clearChargingInfo() {
                this.bitField0_ &= -9;
                this.chargingInfo_ = NTFChargingsts.NTF_CHARGING_OFF;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
            public boolean hasWarnInfo() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
            public NTFWarningSts getWarnInfo() {
                return this.warnInfo_;
            }

            public Builder setWarnInfo(NTFWarningSts value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 16;
                this.warnInfo_ = value;
                onChanged();
                return this;
            }

            public Builder clearWarnInfo() {
                this.bitField0_ &= -17;
                this.warnInfo_ = NTFWarningSts.NTF_WARNING_OFF;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
            public boolean hasTtInfo() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmHmiModeEventsOrBuilder
            public int getTtInfo() {
                return this.ttInfo_;
            }

            public Builder setTtInfo(int value) {
                this.bitField0_ |= 32;
                this.ttInfo_ = value;
                onChanged();
                return this;
            }

            public Builder clearTtInfo() {
                this.bitField0_ &= -33;
                this.ttInfo_ = 0;
                onChanged();
                return this;
            }
        }
    }

    public static final class LcmTimingEvents extends GeneratedMessage implements LcmTimingEventsOrBuilder {
        public static final int ACCOFF_TIME_FIELD_NUMBER = 5;
        public static final int ACCON_TIME_FIELD_NUMBER = 4;
        public static final int ANDROIDSTART_TIME_FIELD_NUMBER = 3;
        public static final int ENGOFF_TIME_FIELD_NUMBER = 7;
        public static final int ENGON_TIME_FIELD_NUMBER = 6;
        public static Parser<LcmTimingEvents> PARSER = new AbstractParser<LcmTimingEvents>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEvents.1
            @Override // com.google.protobuf.Parser
            public LcmTimingEvents parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new LcmTimingEvents(input, extensionRegistry);
            }
        };
        public static final int POWEROFF_TIME_FIELD_NUMBER = 2;
        public static final int SOC_TIME_FIELD_NUMBER = 1;
        private static final LcmTimingEvents defaultInstance;
        private static final long serialVersionUID = 0;
        private Object accoffTime_;
        private Object acconTime_;
        private Object androidstartTime_;
        private int bitField0_;
        private Object engoffTime_;
        private Object engonTime_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private Object poweroffTime_;
        private Object socTime_;
        private final UnknownFieldSet unknownFields;

        private LcmTimingEvents(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private LcmTimingEvents(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static LcmTimingEvents getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public LcmTimingEvents getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private LcmTimingEvents(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 10) {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 1;
                            this.socTime_ = bs;
                        } else if (tag == 18) {
                            ByteString bs2 = input.readBytes();
                            this.bitField0_ |= 2;
                            this.poweroffTime_ = bs2;
                        } else if (tag == 26) {
                            ByteString bs3 = input.readBytes();
                            this.bitField0_ |= 4;
                            this.androidstartTime_ = bs3;
                        } else if (tag == 34) {
                            ByteString bs4 = input.readBytes();
                            this.bitField0_ |= 8;
                            this.acconTime_ = bs4;
                        } else if (tag == 42) {
                            ByteString bs5 = input.readBytes();
                            this.bitField0_ |= 16;
                            this.accoffTime_ = bs5;
                        } else if (tag == 50) {
                            ByteString bs6 = input.readBytes();
                            this.bitField0_ |= 32;
                            this.engonTime_ = bs6;
                        } else if (tag != 58) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            ByteString bs7 = input.readBytes();
                            this.bitField0_ |= 64;
                            this.engoffTime_ = bs7;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_LcmTimingEvents_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_LcmTimingEvents_fieldAccessorTable.ensureFieldAccessorsInitialized(LcmTimingEvents.class, Builder.class);
        }

        static {
            LcmTimingEvents lcmTimingEvents = new LcmTimingEvents(true);
            defaultInstance = lcmTimingEvents;
            lcmTimingEvents.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<LcmTimingEvents> getParserForType() {
            return PARSER;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
        public boolean hasSocTime() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
        public String getSocTime() {
            Object ref = this.socTime_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.socTime_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
        public ByteString getSocTimeBytes() {
            Object ref = this.socTime_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.socTime_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
        public boolean hasPoweroffTime() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
        public String getPoweroffTime() {
            Object ref = this.poweroffTime_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.poweroffTime_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
        public ByteString getPoweroffTimeBytes() {
            Object ref = this.poweroffTime_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.poweroffTime_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
        public boolean hasAndroidstartTime() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
        public String getAndroidstartTime() {
            Object ref = this.androidstartTime_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.androidstartTime_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
        public ByteString getAndroidstartTimeBytes() {
            Object ref = this.androidstartTime_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.androidstartTime_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
        public boolean hasAcconTime() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
        public String getAcconTime() {
            Object ref = this.acconTime_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.acconTime_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
        public ByteString getAcconTimeBytes() {
            Object ref = this.acconTime_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.acconTime_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
        public boolean hasAccoffTime() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
        public String getAccoffTime() {
            Object ref = this.accoffTime_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.accoffTime_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
        public ByteString getAccoffTimeBytes() {
            Object ref = this.accoffTime_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.accoffTime_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
        public boolean hasEngonTime() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
        public String getEngonTime() {
            Object ref = this.engonTime_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.engonTime_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
        public ByteString getEngonTimeBytes() {
            Object ref = this.engonTime_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.engonTime_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
        public boolean hasEngoffTime() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
        public String getEngoffTime() {
            Object ref = this.engoffTime_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.engoffTime_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
        public ByteString getEngoffTimeBytes() {
            Object ref = this.engoffTime_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.engoffTime_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        private void initFields() {
            this.socTime_ = "";
            this.poweroffTime_ = "";
            this.androidstartTime_ = "";
            this.acconTime_ = "";
            this.accoffTime_ = "";
            this.engonTime_ = "";
            this.engoffTime_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(1, getSocTimeBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, getPoweroffTimeBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBytes(3, getAndroidstartTimeBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBytes(4, getAcconTimeBytes());
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeBytes(5, getAccoffTimeBytes());
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeBytes(6, getEngonTimeBytes());
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeBytes(7, getEngoffTimeBytes());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeBytesSize(1, getSocTimeBytes()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBytesSize(2, getPoweroffTimeBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBytesSize(3, getAndroidstartTimeBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeBytesSize(4, getAcconTimeBytes());
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeBytesSize(5, getAccoffTimeBytes());
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeBytesSize(6, getEngonTimeBytes());
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeBytesSize(7, getEngoffTimeBytes());
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static LcmTimingEvents parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static LcmTimingEvents parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static LcmTimingEvents parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static LcmTimingEvents parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static LcmTimingEvents parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static LcmTimingEvents parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static LcmTimingEvents parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static LcmTimingEvents parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static LcmTimingEvents parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static LcmTimingEvents parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(LcmTimingEvents prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements LcmTimingEventsOrBuilder {
            private Object accoffTime_;
            private Object acconTime_;
            private Object androidstartTime_;
            private int bitField0_;
            private Object engoffTime_;
            private Object engonTime_;
            private Object poweroffTime_;
            private Object socTime_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_LcmTimingEvents_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_LcmTimingEvents_fieldAccessorTable.ensureFieldAccessorsInitialized(LcmTimingEvents.class, Builder.class);
            }

            private Builder() {
                this.socTime_ = "";
                this.poweroffTime_ = "";
                this.androidstartTime_ = "";
                this.acconTime_ = "";
                this.accoffTime_ = "";
                this.engonTime_ = "";
                this.engoffTime_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.socTime_ = "";
                this.poweroffTime_ = "";
                this.androidstartTime_ = "";
                this.acconTime_ = "";
                this.accoffTime_ = "";
                this.engonTime_ = "";
                this.engoffTime_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = LcmTimingEvents.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.socTime_ = "";
                int i = this.bitField0_ & (-2);
                this.bitField0_ = i;
                this.poweroffTime_ = "";
                int i2 = i & (-3);
                this.bitField0_ = i2;
                this.androidstartTime_ = "";
                int i3 = i2 & (-5);
                this.bitField0_ = i3;
                this.acconTime_ = "";
                int i4 = i3 & (-9);
                this.bitField0_ = i4;
                this.accoffTime_ = "";
                int i5 = i4 & (-17);
                this.bitField0_ = i5;
                this.engonTime_ = "";
                int i6 = i5 & (-33);
                this.bitField0_ = i6;
                this.engoffTime_ = "";
                this.bitField0_ = i6 & (-65);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_LcmTimingEvents_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public LcmTimingEvents getDefaultInstanceForType() {
                return LcmTimingEvents.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public LcmTimingEvents build() {
                LcmTimingEvents result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public LcmTimingEvents buildPartial() {
                LcmTimingEvents result = new LcmTimingEvents(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.socTime_ = this.socTime_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.poweroffTime_ = this.poweroffTime_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.androidstartTime_ = this.androidstartTime_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.acconTime_ = this.acconTime_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.accoffTime_ = this.accoffTime_;
                if ((from_bitField0_ & 32) == 32) {
                    to_bitField0_ |= 32;
                }
                result.engonTime_ = this.engonTime_;
                if ((from_bitField0_ & 64) == 64) {
                    to_bitField0_ |= 64;
                }
                result.engoffTime_ = this.engoffTime_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof LcmTimingEvents) {
                    return mergeFrom((LcmTimingEvents) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(LcmTimingEvents other) {
                if (other == LcmTimingEvents.getDefaultInstance()) {
                    return this;
                }
                if (other.hasSocTime()) {
                    this.bitField0_ |= 1;
                    this.socTime_ = other.socTime_;
                    onChanged();
                }
                if (other.hasPoweroffTime()) {
                    this.bitField0_ |= 2;
                    this.poweroffTime_ = other.poweroffTime_;
                    onChanged();
                }
                if (other.hasAndroidstartTime()) {
                    this.bitField0_ |= 4;
                    this.androidstartTime_ = other.androidstartTime_;
                    onChanged();
                }
                if (other.hasAcconTime()) {
                    this.bitField0_ |= 8;
                    this.acconTime_ = other.acconTime_;
                    onChanged();
                }
                if (other.hasAccoffTime()) {
                    this.bitField0_ |= 16;
                    this.accoffTime_ = other.accoffTime_;
                    onChanged();
                }
                if (other.hasEngonTime()) {
                    this.bitField0_ |= 32;
                    this.engonTime_ = other.engonTime_;
                    onChanged();
                }
                if (other.hasEngoffTime()) {
                    this.bitField0_ |= 64;
                    this.engoffTime_ = other.engoffTime_;
                    onChanged();
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                LcmTimingEvents parsedMessage = null;
                try {
                    try {
                        parsedMessage = LcmTimingEvents.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
            public boolean hasSocTime() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
            public String getSocTime() {
                Object ref = this.socTime_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.socTime_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
            public ByteString getSocTimeBytes() {
                Object ref = this.socTime_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.socTime_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setSocTime(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.socTime_ = value;
                onChanged();
                return this;
            }

            public Builder clearSocTime() {
                this.bitField0_ &= -2;
                this.socTime_ = LcmTimingEvents.getDefaultInstance().getSocTime();
                onChanged();
                return this;
            }

            public Builder setSocTimeBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.socTime_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
            public boolean hasPoweroffTime() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
            public String getPoweroffTime() {
                Object ref = this.poweroffTime_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.poweroffTime_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
            public ByteString getPoweroffTimeBytes() {
                Object ref = this.poweroffTime_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.poweroffTime_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setPoweroffTime(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.poweroffTime_ = value;
                onChanged();
                return this;
            }

            public Builder clearPoweroffTime() {
                this.bitField0_ &= -3;
                this.poweroffTime_ = LcmTimingEvents.getDefaultInstance().getPoweroffTime();
                onChanged();
                return this;
            }

            public Builder setPoweroffTimeBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.poweroffTime_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
            public boolean hasAndroidstartTime() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
            public String getAndroidstartTime() {
                Object ref = this.androidstartTime_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.androidstartTime_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
            public ByteString getAndroidstartTimeBytes() {
                Object ref = this.androidstartTime_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.androidstartTime_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setAndroidstartTime(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4;
                this.androidstartTime_ = value;
                onChanged();
                return this;
            }

            public Builder clearAndroidstartTime() {
                this.bitField0_ &= -5;
                this.androidstartTime_ = LcmTimingEvents.getDefaultInstance().getAndroidstartTime();
                onChanged();
                return this;
            }

            public Builder setAndroidstartTimeBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4;
                this.androidstartTime_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
            public boolean hasAcconTime() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
            public String getAcconTime() {
                Object ref = this.acconTime_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.acconTime_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
            public ByteString getAcconTimeBytes() {
                Object ref = this.acconTime_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.acconTime_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setAcconTime(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 8;
                this.acconTime_ = value;
                onChanged();
                return this;
            }

            public Builder clearAcconTime() {
                this.bitField0_ &= -9;
                this.acconTime_ = LcmTimingEvents.getDefaultInstance().getAcconTime();
                onChanged();
                return this;
            }

            public Builder setAcconTimeBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 8;
                this.acconTime_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
            public boolean hasAccoffTime() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
            public String getAccoffTime() {
                Object ref = this.accoffTime_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.accoffTime_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
            public ByteString getAccoffTimeBytes() {
                Object ref = this.accoffTime_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.accoffTime_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setAccoffTime(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 16;
                this.accoffTime_ = value;
                onChanged();
                return this;
            }

            public Builder clearAccoffTime() {
                this.bitField0_ &= -17;
                this.accoffTime_ = LcmTimingEvents.getDefaultInstance().getAccoffTime();
                onChanged();
                return this;
            }

            public Builder setAccoffTimeBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 16;
                this.accoffTime_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
            public boolean hasEngonTime() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
            public String getEngonTime() {
                Object ref = this.engonTime_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.engonTime_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
            public ByteString getEngonTimeBytes() {
                Object ref = this.engonTime_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.engonTime_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setEngonTime(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 32;
                this.engonTime_ = value;
                onChanged();
                return this;
            }

            public Builder clearEngonTime() {
                this.bitField0_ &= -33;
                this.engonTime_ = LcmTimingEvents.getDefaultInstance().getEngonTime();
                onChanged();
                return this;
            }

            public Builder setEngonTimeBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 32;
                this.engonTime_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
            public boolean hasEngoffTime() {
                return (this.bitField0_ & 64) == 64;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
            public String getEngoffTime() {
                Object ref = this.engoffTime_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.engoffTime_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.LcmTimingEventsOrBuilder
            public ByteString getEngoffTimeBytes() {
                Object ref = this.engoffTime_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.engoffTime_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setEngoffTime(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 64;
                this.engoffTime_ = value;
                onChanged();
                return this;
            }

            public Builder clearEngoffTime() {
                this.bitField0_ &= -65;
                this.engoffTime_ = LcmTimingEvents.getDefaultInstance().getEngoffTime();
                onChanged();
                return this;
            }

            public Builder setEngoffTimeBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 64;
                this.engoffTime_ = value;
                onChanged();
                return this;
            }
        }
    }

    public static final class CompntHealthStatus extends GeneratedMessage implements CompntHealthStatusOrBuilder {
        public static Parser<CompntHealthStatus> PARSER = new AbstractParser<CompntHealthStatus>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.CompntHealthStatus.1
            @Override // com.google.protobuf.Parser
            public CompntHealthStatus parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new CompntHealthStatus(input, extensionRegistry);
            }
        };
        public static final int PROCESSED_MSG_FIELD_NUMBER = 2;
        public static final int STATUS_FIELD_NUMBER = 1;
        private static final CompntHealthStatus defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int processedMsg_;
        private HealthStatus status_;
        private final UnknownFieldSet unknownFields;

        private CompntHealthStatus(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private CompntHealthStatus(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static CompntHealthStatus getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public CompntHealthStatus getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private CompntHealthStatus(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 8) {
                                int rawValue = input.readEnum();
                                HealthStatus value = HealthStatus.valueOf(rawValue);
                                if (value != null) {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.status_ = value;
                                } else {
                                    unknownFields.mergeVarintField(1, rawValue);
                                }
                            } else if (tag != 16) {
                                if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                    done = true;
                                }
                            } else {
                                this.bitField0_ |= 2;
                                this.processedMsg_ = input.readUInt32();
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_CompntHealthStatus_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_CompntHealthStatus_fieldAccessorTable.ensureFieldAccessorsInitialized(CompntHealthStatus.class, Builder.class);
        }

        static {
            CompntHealthStatus compntHealthStatus = new CompntHealthStatus(true);
            defaultInstance = compntHealthStatus;
            compntHealthStatus.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<CompntHealthStatus> getParserForType() {
            return PARSER;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.CompntHealthStatusOrBuilder
        public boolean hasStatus() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.CompntHealthStatusOrBuilder
        public HealthStatus getStatus() {
            return this.status_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.CompntHealthStatusOrBuilder
        public boolean hasProcessedMsg() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.CompntHealthStatusOrBuilder
        public int getProcessedMsg() {
            return this.processedMsg_;
        }

        private void initFields() {
            this.status_ = HealthStatus.HLTH_STAT_UNKNOWN;
            this.processedMsg_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasStatus()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.status_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeUInt32(2, this.processedMsg_);
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.status_.getNumber()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeUInt32Size(2, this.processedMsg_);
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static CompntHealthStatus parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static CompntHealthStatus parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static CompntHealthStatus parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static CompntHealthStatus parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static CompntHealthStatus parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static CompntHealthStatus parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static CompntHealthStatus parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static CompntHealthStatus parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static CompntHealthStatus parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static CompntHealthStatus parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(CompntHealthStatus prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements CompntHealthStatusOrBuilder {
            private int bitField0_;
            private int processedMsg_;
            private HealthStatus status_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_CompntHealthStatus_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_CompntHealthStatus_fieldAccessorTable.ensureFieldAccessorsInitialized(CompntHealthStatus.class, Builder.class);
            }

            private Builder() {
                this.status_ = HealthStatus.HLTH_STAT_UNKNOWN;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.status_ = HealthStatus.HLTH_STAT_UNKNOWN;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = CompntHealthStatus.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.status_ = HealthStatus.HLTH_STAT_UNKNOWN;
                int i = this.bitField0_ & (-2);
                this.bitField0_ = i;
                this.processedMsg_ = 0;
                this.bitField0_ = i & (-3);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_CompntHealthStatus_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public CompntHealthStatus getDefaultInstanceForType() {
                return CompntHealthStatus.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CompntHealthStatus build() {
                CompntHealthStatus result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CompntHealthStatus buildPartial() {
                CompntHealthStatus result = new CompntHealthStatus(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.status_ = this.status_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.processedMsg_ = this.processedMsg_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof CompntHealthStatus) {
                    return mergeFrom((CompntHealthStatus) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(CompntHealthStatus other) {
                if (other == CompntHealthStatus.getDefaultInstance()) {
                    return this;
                }
                if (other.hasStatus()) {
                    setStatus(other.getStatus());
                }
                if (other.hasProcessedMsg()) {
                    setProcessedMsg(other.getProcessedMsg());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (!hasStatus()) {
                    return false;
                }
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                CompntHealthStatus parsedMessage = null;
                try {
                    try {
                        parsedMessage = CompntHealthStatus.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.CompntHealthStatusOrBuilder
            public boolean hasStatus() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.CompntHealthStatusOrBuilder
            public HealthStatus getStatus() {
                return this.status_;
            }

            public Builder setStatus(HealthStatus value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.status_ = value;
                onChanged();
                return this;
            }

            public Builder clearStatus() {
                this.bitField0_ &= -2;
                this.status_ = HealthStatus.HLTH_STAT_UNKNOWN;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.CompntHealthStatusOrBuilder
            public boolean hasProcessedMsg() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.CompntHealthStatusOrBuilder
            public int getProcessedMsg() {
                return this.processedMsg_;
            }

            public Builder setProcessedMsg(int value) {
                this.bitField0_ |= 2;
                this.processedMsg_ = value;
                onChanged();
                return this;
            }

            public Builder clearProcessedMsg() {
                this.bitField0_ &= -3;
                this.processedMsg_ = 0;
                onChanged();
                return this;
            }
        }
    }

    public static final class DiagDtcInfo extends GeneratedMessage implements DiagDtcInfoOrBuilder {
        public static final int DTC_NUMBER_FIELD_NUMBER = 1;
        public static Parser<DiagDtcInfo> PARSER = new AbstractParser<DiagDtcInfo>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DiagDtcInfo.1
            @Override // com.google.protobuf.Parser
            public DiagDtcInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new DiagDtcInfo(input, extensionRegistry);
            }
        };
        public static final int RESULT_FIELD_NUMBER = 2;
        private static final DiagDtcInfo defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private int dtcNumber_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private DiagDtcResult result_;
        private final UnknownFieldSet unknownFields;

        private DiagDtcInfo(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private DiagDtcInfo(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static DiagDtcInfo getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public DiagDtcInfo getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private DiagDtcInfo(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 8) {
                                this.bitField0_ |= 1;
                                this.dtcNumber_ = input.readUInt32();
                            } else if (tag != 16) {
                                if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                    done = true;
                                }
                            } else {
                                int rawValue = input.readEnum();
                                DiagDtcResult value = DiagDtcResult.valueOf(rawValue);
                                if (value != null) {
                                    this.bitField0_ = 2 | this.bitField0_;
                                    this.result_ = value;
                                } else {
                                    unknownFields.mergeVarintField(2, rawValue);
                                }
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_DiagDtcInfo_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_DiagDtcInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(DiagDtcInfo.class, Builder.class);
        }

        static {
            DiagDtcInfo diagDtcInfo = new DiagDtcInfo(true);
            defaultInstance = diagDtcInfo;
            diagDtcInfo.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<DiagDtcInfo> getParserForType() {
            return PARSER;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DiagDtcInfoOrBuilder
        public boolean hasDtcNumber() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DiagDtcInfoOrBuilder
        public int getDtcNumber() {
            return this.dtcNumber_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DiagDtcInfoOrBuilder
        public boolean hasResult() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DiagDtcInfoOrBuilder
        public DiagDtcResult getResult() {
            return this.result_;
        }

        private void initFields() {
            this.dtcNumber_ = 0;
            this.result_ = DiagDtcResult.DTC_OK;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasDtcNumber()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasResult()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeUInt32(1, this.dtcNumber_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.result_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeUInt32Size(1, this.dtcNumber_) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(2, this.result_.getNumber());
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static DiagDtcInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static DiagDtcInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static DiagDtcInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static DiagDtcInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static DiagDtcInfo parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static DiagDtcInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static DiagDtcInfo parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static DiagDtcInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static DiagDtcInfo parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static DiagDtcInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(DiagDtcInfo prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements DiagDtcInfoOrBuilder {
            private int bitField0_;
            private int dtcNumber_;
            private DiagDtcResult result_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_DiagDtcInfo_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_DiagDtcInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(DiagDtcInfo.class, Builder.class);
            }

            private Builder() {
                this.result_ = DiagDtcResult.DTC_OK;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.result_ = DiagDtcResult.DTC_OK;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = DiagDtcInfo.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.dtcNumber_ = 0;
                this.bitField0_ &= -2;
                this.result_ = DiagDtcResult.DTC_OK;
                this.bitField0_ &= -3;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_DiagDtcInfo_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public DiagDtcInfo getDefaultInstanceForType() {
                return DiagDtcInfo.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public DiagDtcInfo build() {
                DiagDtcInfo result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public DiagDtcInfo buildPartial() {
                DiagDtcInfo result = new DiagDtcInfo(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.dtcNumber_ = this.dtcNumber_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.result_ = this.result_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof DiagDtcInfo) {
                    return mergeFrom((DiagDtcInfo) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(DiagDtcInfo other) {
                if (other == DiagDtcInfo.getDefaultInstance()) {
                    return this;
                }
                if (other.hasDtcNumber()) {
                    setDtcNumber(other.getDtcNumber());
                }
                if (other.hasResult()) {
                    setResult(other.getResult());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasDtcNumber() && hasResult();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                DiagDtcInfo parsedMessage = null;
                try {
                    try {
                        parsedMessage = DiagDtcInfo.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DiagDtcInfoOrBuilder
            public boolean hasDtcNumber() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DiagDtcInfoOrBuilder
            public int getDtcNumber() {
                return this.dtcNumber_;
            }

            public Builder setDtcNumber(int value) {
                this.bitField0_ |= 1;
                this.dtcNumber_ = value;
                onChanged();
                return this;
            }

            public Builder clearDtcNumber() {
                this.bitField0_ &= -2;
                this.dtcNumber_ = 0;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DiagDtcInfoOrBuilder
            public boolean hasResult() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DiagDtcInfoOrBuilder
            public DiagDtcResult getResult() {
                return this.result_;
            }

            public Builder setResult(DiagDtcResult value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.result_ = value;
                onChanged();
                return this;
            }

            public Builder clearResult() {
                this.bitField0_ &= -3;
                this.result_ = DiagDtcResult.DTC_OK;
                onChanged();
                return this;
            }
        }
    }

    public static final class MsgLogLevel extends GeneratedMessage implements MsgLogLevelOrBuilder {
        public static final int LEVEL_FIELD_NUMBER = 1;
        public static Parser<MsgLogLevel> PARSER = new AbstractParser<MsgLogLevel>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.MsgLogLevel.1
            @Override // com.google.protobuf.Parser
            public MsgLogLevel parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new MsgLogLevel(input, extensionRegistry);
            }
        };
        private static final MsgLogLevel defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private LogLevel level_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        private MsgLogLevel(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private MsgLogLevel(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static MsgLogLevel getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MsgLogLevel getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private MsgLogLevel(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag != 8) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            int rawValue = input.readEnum();
                            LogLevel value = LogLevel.valueOf(rawValue);
                            if (value != null) {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.level_ = value;
                            } else {
                                unknownFields.mergeVarintField(1, rawValue);
                            }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_MsgLogLevel_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_MsgLogLevel_fieldAccessorTable.ensureFieldAccessorsInitialized(MsgLogLevel.class, Builder.class);
        }

        static {
            MsgLogLevel msgLogLevel = new MsgLogLevel(true);
            defaultInstance = msgLogLevel;
            msgLogLevel.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<MsgLogLevel> getParserForType() {
            return PARSER;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.MsgLogLevelOrBuilder
        public boolean hasLevel() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.MsgLogLevelOrBuilder
        public LogLevel getLevel() {
            return this.level_;
        }

        private void initFields() {
            this.level_ = LogLevel.LOG_SHUTDOWN;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasLevel()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.level_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.level_.getNumber()) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static MsgLogLevel parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static MsgLogLevel parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static MsgLogLevel parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static MsgLogLevel parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static MsgLogLevel parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static MsgLogLevel parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static MsgLogLevel parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static MsgLogLevel parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static MsgLogLevel parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static MsgLogLevel parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(MsgLogLevel prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements MsgLogLevelOrBuilder {
            private int bitField0_;
            private LogLevel level_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_MsgLogLevel_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_MsgLogLevel_fieldAccessorTable.ensureFieldAccessorsInitialized(MsgLogLevel.class, Builder.class);
            }

            private Builder() {
                this.level_ = LogLevel.LOG_SHUTDOWN;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.level_ = LogLevel.LOG_SHUTDOWN;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = MsgLogLevel.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.level_ = LogLevel.LOG_SHUTDOWN;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_MsgLogLevel_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MsgLogLevel getDefaultInstanceForType() {
                return MsgLogLevel.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MsgLogLevel build() {
                MsgLogLevel result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MsgLogLevel buildPartial() {
                MsgLogLevel result = new MsgLogLevel(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.level_ = this.level_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof MsgLogLevel) {
                    return mergeFrom((MsgLogLevel) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(MsgLogLevel other) {
                if (other == MsgLogLevel.getDefaultInstance()) {
                    return this;
                }
                if (other.hasLevel()) {
                    setLevel(other.getLevel());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (!hasLevel()) {
                    return false;
                }
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                MsgLogLevel parsedMessage = null;
                try {
                    try {
                        parsedMessage = MsgLogLevel.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.MsgLogLevelOrBuilder
            public boolean hasLevel() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.MsgLogLevelOrBuilder
            public LogLevel getLevel() {
                return this.level_;
            }

            public Builder setLevel(LogLevel value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.level_ = value;
                onChanged();
                return this;
            }

            public Builder clearLevel() {
                this.bitField0_ &= -2;
                this.level_ = LogLevel.LOG_SHUTDOWN;
                onChanged();
                return this;
            }
        }
    }

    public static final class switchEfuseInfo extends GeneratedMessage implements switchEfuseInfoOrBuilder {
        public static final int EFUSEINFO_FIELD_NUMBER = 3;
        public static Parser<switchEfuseInfo> PARSER = new AbstractParser<switchEfuseInfo>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.switchEfuseInfo.1
            @Override // com.google.protobuf.Parser
            public switchEfuseInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new switchEfuseInfo(input, extensionRegistry);
            }
        };
        private static final switchEfuseInfo defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private EfuseStatus efuseinfo_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        private switchEfuseInfo(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private switchEfuseInfo(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static switchEfuseInfo getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public switchEfuseInfo getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private switchEfuseInfo(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag != 24) {
                                if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                    done = true;
                                }
                            } else {
                                int rawValue = input.readEnum();
                                EfuseStatus value = EfuseStatus.valueOf(rawValue);
                                if (value == null) {
                                    unknownFields.mergeVarintField(3, rawValue);
                                } else {
                                    this.bitField0_ |= 1;
                                    this.efuseinfo_ = value;
                                }
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_switchEfuseInfo_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_switchEfuseInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(switchEfuseInfo.class, Builder.class);
        }

        static {
            switchEfuseInfo switchefuseinfo = new switchEfuseInfo(true);
            defaultInstance = switchefuseinfo;
            switchefuseinfo.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<switchEfuseInfo> getParserForType() {
            return PARSER;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.switchEfuseInfoOrBuilder
        public boolean hasEfuseinfo() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.switchEfuseInfoOrBuilder
        public EfuseStatus getEfuseinfo() {
            return this.efuseinfo_;
        }

        private void initFields() {
            this.efuseinfo_ = EfuseStatus.EFUSE_NO_SWITCH;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasEfuseinfo()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(3, this.efuseinfo_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(3, this.efuseinfo_.getNumber()) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static switchEfuseInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static switchEfuseInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static switchEfuseInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static switchEfuseInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static switchEfuseInfo parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static switchEfuseInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static switchEfuseInfo parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static switchEfuseInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static switchEfuseInfo parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static switchEfuseInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(switchEfuseInfo prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements switchEfuseInfoOrBuilder {
            private int bitField0_;
            private EfuseStatus efuseinfo_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_switchEfuseInfo_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_switchEfuseInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(switchEfuseInfo.class, Builder.class);
            }

            private Builder() {
                this.efuseinfo_ = EfuseStatus.EFUSE_NO_SWITCH;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.efuseinfo_ = EfuseStatus.EFUSE_NO_SWITCH;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = switchEfuseInfo.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.efuseinfo_ = EfuseStatus.EFUSE_NO_SWITCH;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_switchEfuseInfo_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public switchEfuseInfo getDefaultInstanceForType() {
                return switchEfuseInfo.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public switchEfuseInfo build() {
                switchEfuseInfo result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public switchEfuseInfo buildPartial() {
                switchEfuseInfo result = new switchEfuseInfo(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.efuseinfo_ = this.efuseinfo_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof switchEfuseInfo) {
                    return mergeFrom((switchEfuseInfo) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(switchEfuseInfo other) {
                if (other == switchEfuseInfo.getDefaultInstance()) {
                    return this;
                }
                if (other.hasEfuseinfo()) {
                    setEfuseinfo(other.getEfuseinfo());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (!hasEfuseinfo()) {
                    return false;
                }
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                switchEfuseInfo parsedMessage = null;
                try {
                    try {
                        parsedMessage = switchEfuseInfo.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.switchEfuseInfoOrBuilder
            public boolean hasEfuseinfo() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.switchEfuseInfoOrBuilder
            public EfuseStatus getEfuseinfo() {
                return this.efuseinfo_;
            }

            public Builder setEfuseinfo(EfuseStatus value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.efuseinfo_ = value;
                onChanged();
                return this;
            }

            public Builder clearEfuseinfo() {
                this.bitField0_ &= -2;
                this.efuseinfo_ = EfuseStatus.EFUSE_NO_SWITCH;
                onChanged();
                return this;
            }
        }
    }

    public static final class DispHudRotation extends GeneratedMessage implements DispHudRotationOrBuilder {
        public static final int DIRECTION_FIELD_NUMBER = 1;
        public static final int LEVEL_FIELD_NUMBER = 2;
        public static Parser<DispHudRotation> PARSER = new AbstractParser<DispHudRotation>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispHudRotation.1
            @Override // com.google.protobuf.Parser
            public DispHudRotation parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new DispHudRotation(input, extensionRegistry);
            }
        };
        private static final DispHudRotation defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private HudRotateDir direction_;
        private int level_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        private DispHudRotation(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private DispHudRotation(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static DispHudRotation getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public DispHudRotation getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private DispHudRotation(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 8) {
                                int rawValue = input.readEnum();
                                HudRotateDir value = HudRotateDir.valueOf(rawValue);
                                if (value != null) {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.direction_ = value;
                                } else {
                                    unknownFields.mergeVarintField(1, rawValue);
                                }
                            } else if (tag != 16) {
                                if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                    done = true;
                                }
                            } else {
                                this.bitField0_ |= 2;
                                this.level_ = input.readUInt32();
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_DispHudRotation_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_DispHudRotation_fieldAccessorTable.ensureFieldAccessorsInitialized(DispHudRotation.class, Builder.class);
        }

        static {
            DispHudRotation dispHudRotation = new DispHudRotation(true);
            defaultInstance = dispHudRotation;
            dispHudRotation.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<DispHudRotation> getParserForType() {
            return PARSER;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispHudRotationOrBuilder
        public boolean hasDirection() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispHudRotationOrBuilder
        public HudRotateDir getDirection() {
            return this.direction_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispHudRotationOrBuilder
        public boolean hasLevel() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispHudRotationOrBuilder
        public int getLevel() {
            return this.level_;
        }

        private void initFields() {
            this.direction_ = HudRotateDir.DISP_HUD_NO_ROTATION;
            this.level_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasDirection()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasLevel()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.direction_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeUInt32(2, this.level_);
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.direction_.getNumber()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeUInt32Size(2, this.level_);
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static DispHudRotation parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static DispHudRotation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static DispHudRotation parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static DispHudRotation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static DispHudRotation parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static DispHudRotation parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static DispHudRotation parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static DispHudRotation parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static DispHudRotation parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static DispHudRotation parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(DispHudRotation prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements DispHudRotationOrBuilder {
            private int bitField0_;
            private HudRotateDir direction_;
            private int level_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_DispHudRotation_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_DispHudRotation_fieldAccessorTable.ensureFieldAccessorsInitialized(DispHudRotation.class, Builder.class);
            }

            private Builder() {
                this.direction_ = HudRotateDir.DISP_HUD_NO_ROTATION;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.direction_ = HudRotateDir.DISP_HUD_NO_ROTATION;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = DispHudRotation.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.direction_ = HudRotateDir.DISP_HUD_NO_ROTATION;
                int i = this.bitField0_ & (-2);
                this.bitField0_ = i;
                this.level_ = 0;
                this.bitField0_ = i & (-3);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_DispHudRotation_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public DispHudRotation getDefaultInstanceForType() {
                return DispHudRotation.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public DispHudRotation build() {
                DispHudRotation result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public DispHudRotation buildPartial() {
                DispHudRotation result = new DispHudRotation(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.direction_ = this.direction_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.level_ = this.level_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof DispHudRotation) {
                    return mergeFrom((DispHudRotation) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(DispHudRotation other) {
                if (other == DispHudRotation.getDefaultInstance()) {
                    return this;
                }
                if (other.hasDirection()) {
                    setDirection(other.getDirection());
                }
                if (other.hasLevel()) {
                    setLevel(other.getLevel());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasDirection() && hasLevel();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                DispHudRotation parsedMessage = null;
                try {
                    try {
                        parsedMessage = DispHudRotation.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispHudRotationOrBuilder
            public boolean hasDirection() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispHudRotationOrBuilder
            public HudRotateDir getDirection() {
                return this.direction_;
            }

            public Builder setDirection(HudRotateDir value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.direction_ = value;
                onChanged();
                return this;
            }

            public Builder clearDirection() {
                this.bitField0_ &= -2;
                this.direction_ = HudRotateDir.DISP_HUD_NO_ROTATION;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispHudRotationOrBuilder
            public boolean hasLevel() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.DispHudRotationOrBuilder
            public int getLevel() {
                return this.level_;
            }

            public Builder setLevel(int value) {
                this.bitField0_ |= 2;
                this.level_ = value;
                onChanged();
                return this;
            }

            public Builder clearLevel() {
                this.bitField0_ &= -3;
                this.level_ = 0;
                onChanged();
                return this;
            }
        }
    }

    public static final class PsisUpdateCarCfgInfo extends GeneratedMessage implements PsisUpdateCarCfgInfoOrBuilder {
        public static final int KEY_NAME_FIELD_NUMBER = 1;
        public static final int KEY_VALUE_CHAR_FIELD_NUMBER = 2;
        public static Parser<PsisUpdateCarCfgInfo> PARSER = new AbstractParser<PsisUpdateCarCfgInfo>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfo.1
            @Override // com.google.protobuf.Parser
            public PsisUpdateCarCfgInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new PsisUpdateCarCfgInfo(input, extensionRegistry);
            }
        };
        private static final PsisUpdateCarCfgInfo defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private Object keyName_;
        private ByteString keyValueChar_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        private PsisUpdateCarCfgInfo(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private PsisUpdateCarCfgInfo(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static PsisUpdateCarCfgInfo getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public PsisUpdateCarCfgInfo getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private PsisUpdateCarCfgInfo(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 10) {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 1;
                            this.keyName_ = bs;
                        } else if (tag != 18) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            this.bitField0_ |= 2;
                            this.keyValueChar_ = input.readBytes();
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_PsisUpdateCarCfgInfo_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_PsisUpdateCarCfgInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(PsisUpdateCarCfgInfo.class, Builder.class);
        }

        static {
            PsisUpdateCarCfgInfo psisUpdateCarCfgInfo = new PsisUpdateCarCfgInfo(true);
            defaultInstance = psisUpdateCarCfgInfo;
            psisUpdateCarCfgInfo.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<PsisUpdateCarCfgInfo> getParserForType() {
            return PARSER;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfoOrBuilder
        public boolean hasKeyName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfoOrBuilder
        public String getKeyName() {
            Object ref = this.keyName_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.keyName_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfoOrBuilder
        public ByteString getKeyNameBytes() {
            Object ref = this.keyName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.keyName_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfoOrBuilder
        public boolean hasKeyValueChar() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfoOrBuilder
        public ByteString getKeyValueChar() {
            return this.keyValueChar_;
        }

        private void initFields() {
            this.keyName_ = "";
            this.keyValueChar_ = ByteString.EMPTY;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasKeyName()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasKeyValueChar()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeBytes(1, getKeyNameBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, this.keyValueChar_);
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeBytesSize(1, getKeyNameBytes()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBytesSize(2, this.keyValueChar_);
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static PsisUpdateCarCfgInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static PsisUpdateCarCfgInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static PsisUpdateCarCfgInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static PsisUpdateCarCfgInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static PsisUpdateCarCfgInfo parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static PsisUpdateCarCfgInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static PsisUpdateCarCfgInfo parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static PsisUpdateCarCfgInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static PsisUpdateCarCfgInfo parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static PsisUpdateCarCfgInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(PsisUpdateCarCfgInfo prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements PsisUpdateCarCfgInfoOrBuilder {
            private int bitField0_;
            private Object keyName_;
            private ByteString keyValueChar_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_PsisUpdateCarCfgInfo_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_PsisUpdateCarCfgInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(PsisUpdateCarCfgInfo.class, Builder.class);
            }

            private Builder() {
                this.keyName_ = "";
                this.keyValueChar_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.keyName_ = "";
                this.keyValueChar_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = PsisUpdateCarCfgInfo.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.keyName_ = "";
                this.bitField0_ &= -2;
                this.keyValueChar_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_PsisUpdateCarCfgInfo_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public PsisUpdateCarCfgInfo getDefaultInstanceForType() {
                return PsisUpdateCarCfgInfo.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public PsisUpdateCarCfgInfo build() {
                PsisUpdateCarCfgInfo result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public PsisUpdateCarCfgInfo buildPartial() {
                PsisUpdateCarCfgInfo result = new PsisUpdateCarCfgInfo(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.keyName_ = this.keyName_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.keyValueChar_ = this.keyValueChar_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof PsisUpdateCarCfgInfo) {
                    return mergeFrom((PsisUpdateCarCfgInfo) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(PsisUpdateCarCfgInfo other) {
                if (other == PsisUpdateCarCfgInfo.getDefaultInstance()) {
                    return this;
                }
                if (other.hasKeyName()) {
                    this.bitField0_ |= 1;
                    this.keyName_ = other.keyName_;
                    onChanged();
                }
                if (other.hasKeyValueChar()) {
                    setKeyValueChar(other.getKeyValueChar());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasKeyName() && hasKeyValueChar();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                PsisUpdateCarCfgInfo parsedMessage = null;
                try {
                    try {
                        parsedMessage = PsisUpdateCarCfgInfo.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfoOrBuilder
            public boolean hasKeyName() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfoOrBuilder
            public String getKeyName() {
                Object ref = this.keyName_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.keyName_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfoOrBuilder
            public ByteString getKeyNameBytes() {
                Object ref = this.keyName_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.keyName_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setKeyName(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.keyName_ = value;
                onChanged();
                return this;
            }

            public Builder clearKeyName() {
                this.bitField0_ &= -2;
                this.keyName_ = PsisUpdateCarCfgInfo.getDefaultInstance().getKeyName();
                onChanged();
                return this;
            }

            public Builder setKeyNameBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.keyName_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfoOrBuilder
            public boolean hasKeyValueChar() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfoOrBuilder
            public ByteString getKeyValueChar() {
                return this.keyValueChar_;
            }

            public Builder setKeyValueChar(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.keyValueChar_ = value;
                onChanged();
                return this;
            }

            public Builder clearKeyValueChar() {
                this.bitField0_ &= -3;
                this.keyValueChar_ = PsisUpdateCarCfgInfo.getDefaultInstance().getKeyValueChar();
                onChanged();
                return this;
            }
        }
    }

    public static final class PsisUpdateCarCfgInfoMap extends GeneratedMessage implements PsisUpdateCarCfgInfoMapOrBuilder {
        public static final int CAR_CFG_LIST_FIELD_NUMBER = 1;
        public static Parser<PsisUpdateCarCfgInfoMap> PARSER = new AbstractParser<PsisUpdateCarCfgInfoMap>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfoMap.1
            @Override // com.google.protobuf.Parser
            public PsisUpdateCarCfgInfoMap parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new PsisUpdateCarCfgInfoMap(input, extensionRegistry);
            }
        };
        private static final PsisUpdateCarCfgInfoMap defaultInstance;
        private static final long serialVersionUID = 0;
        private List<PsisUpdateCarCfgInfo> carCfgList_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        private PsisUpdateCarCfgInfoMap(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private PsisUpdateCarCfgInfoMap(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static PsisUpdateCarCfgInfoMap getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public PsisUpdateCarCfgInfoMap getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private PsisUpdateCarCfgInfoMap(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            int mutable_bitField0_ = 0;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (true) {
                if (done) {
                    break;
                }
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 10) {
                            if ((mutable_bitField0_ & 1) != 1) {
                                this.carCfgList_ = new ArrayList();
                                mutable_bitField0_ |= 1;
                            }
                            this.carCfgList_.add((PsisUpdateCarCfgInfo) input.readMessage(PsisUpdateCarCfgInfo.PARSER, extensionRegistry));
                        } else if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                            done = true;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    if ((mutable_bitField0_ & 1) == 1) {
                        this.carCfgList_ = Collections.unmodifiableList(this.carCfgList_);
                    }
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_PsisUpdateCarCfgInfoMap_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_PsisUpdateCarCfgInfoMap_fieldAccessorTable.ensureFieldAccessorsInitialized(PsisUpdateCarCfgInfoMap.class, Builder.class);
        }

        static {
            PsisUpdateCarCfgInfoMap psisUpdateCarCfgInfoMap = new PsisUpdateCarCfgInfoMap(true);
            defaultInstance = psisUpdateCarCfgInfoMap;
            psisUpdateCarCfgInfoMap.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<PsisUpdateCarCfgInfoMap> getParserForType() {
            return PARSER;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfoMapOrBuilder
        public List<PsisUpdateCarCfgInfo> getCarCfgListList() {
            return this.carCfgList_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfoMapOrBuilder
        public List<? extends PsisUpdateCarCfgInfoOrBuilder> getCarCfgListOrBuilderList() {
            return this.carCfgList_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfoMapOrBuilder
        public int getCarCfgListCount() {
            return this.carCfgList_.size();
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfoMapOrBuilder
        public PsisUpdateCarCfgInfo getCarCfgList(int index) {
            return this.carCfgList_.get(index);
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfoMapOrBuilder
        public PsisUpdateCarCfgInfoOrBuilder getCarCfgListOrBuilder(int index) {
            return this.carCfgList_.get(index);
        }

        private void initFields() {
            this.carCfgList_ = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < getCarCfgListCount(); i++) {
                if (!getCarCfgList(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            for (int i = 0; i < this.carCfgList_.size(); i++) {
                output.writeMessage(1, this.carCfgList_.get(i));
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            for (int i = 0; i < this.carCfgList_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(1, this.carCfgList_.get(i));
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static PsisUpdateCarCfgInfoMap parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static PsisUpdateCarCfgInfoMap parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static PsisUpdateCarCfgInfoMap parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static PsisUpdateCarCfgInfoMap parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static PsisUpdateCarCfgInfoMap parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static PsisUpdateCarCfgInfoMap parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static PsisUpdateCarCfgInfoMap parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static PsisUpdateCarCfgInfoMap parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static PsisUpdateCarCfgInfoMap parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static PsisUpdateCarCfgInfoMap parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(PsisUpdateCarCfgInfoMap prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements PsisUpdateCarCfgInfoMapOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilder<PsisUpdateCarCfgInfo, PsisUpdateCarCfgInfo.Builder, PsisUpdateCarCfgInfoOrBuilder> carCfgListBuilder_;
            private List<PsisUpdateCarCfgInfo> carCfgList_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_PsisUpdateCarCfgInfoMap_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_PsisUpdateCarCfgInfoMap_fieldAccessorTable.ensureFieldAccessorsInitialized(PsisUpdateCarCfgInfoMap.class, Builder.class);
            }

            private Builder() {
                this.carCfgList_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.carCfgList_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (PsisUpdateCarCfgInfoMap.alwaysUseFieldBuilders) {
                    getCarCfgListFieldBuilder();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                RepeatedFieldBuilder<PsisUpdateCarCfgInfo, PsisUpdateCarCfgInfo.Builder, PsisUpdateCarCfgInfoOrBuilder> repeatedFieldBuilder = this.carCfgListBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.carCfgList_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilder.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_PsisUpdateCarCfgInfoMap_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public PsisUpdateCarCfgInfoMap getDefaultInstanceForType() {
                return PsisUpdateCarCfgInfoMap.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public PsisUpdateCarCfgInfoMap build() {
                PsisUpdateCarCfgInfoMap result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public PsisUpdateCarCfgInfoMap buildPartial() {
                PsisUpdateCarCfgInfoMap result = new PsisUpdateCarCfgInfoMap(this);
                int i = this.bitField0_;
                RepeatedFieldBuilder<PsisUpdateCarCfgInfo, PsisUpdateCarCfgInfo.Builder, PsisUpdateCarCfgInfoOrBuilder> repeatedFieldBuilder = this.carCfgListBuilder_;
                if (repeatedFieldBuilder != null) {
                    result.carCfgList_ = repeatedFieldBuilder.build();
                } else {
                    if ((this.bitField0_ & 1) == 1) {
                        this.carCfgList_ = Collections.unmodifiableList(this.carCfgList_);
                        this.bitField0_ &= -2;
                    }
                    result.carCfgList_ = this.carCfgList_;
                }
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof PsisUpdateCarCfgInfoMap) {
                    return mergeFrom((PsisUpdateCarCfgInfoMap) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(PsisUpdateCarCfgInfoMap other) {
                if (other == PsisUpdateCarCfgInfoMap.getDefaultInstance()) {
                    return this;
                }
                if (this.carCfgListBuilder_ == null) {
                    if (!other.carCfgList_.isEmpty()) {
                        if (this.carCfgList_.isEmpty()) {
                            this.carCfgList_ = other.carCfgList_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureCarCfgListIsMutable();
                            this.carCfgList_.addAll(other.carCfgList_);
                        }
                        onChanged();
                    }
                } else if (!other.carCfgList_.isEmpty()) {
                    if (!this.carCfgListBuilder_.isEmpty()) {
                        this.carCfgListBuilder_.addAllMessages(other.carCfgList_);
                    } else {
                        this.carCfgListBuilder_.dispose();
                        RepeatedFieldBuilder<PsisUpdateCarCfgInfo, PsisUpdateCarCfgInfo.Builder, PsisUpdateCarCfgInfoOrBuilder> carCfgListFieldBuilder = null;
                        this.carCfgListBuilder_ = null;
                        this.carCfgList_ = other.carCfgList_;
                        this.bitField0_ &= -2;
                        if (PsisUpdateCarCfgInfoMap.alwaysUseFieldBuilders) {
                            carCfgListFieldBuilder = getCarCfgListFieldBuilder();
                        }
                        this.carCfgListBuilder_ = carCfgListFieldBuilder;
                    }
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getCarCfgListCount(); i++) {
                    if (!getCarCfgList(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                PsisUpdateCarCfgInfoMap parsedMessage = null;
                try {
                    try {
                        parsedMessage = PsisUpdateCarCfgInfoMap.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            private void ensureCarCfgListIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.carCfgList_ = new ArrayList(this.carCfgList_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfoMapOrBuilder
            public List<PsisUpdateCarCfgInfo> getCarCfgListList() {
                RepeatedFieldBuilder<PsisUpdateCarCfgInfo, PsisUpdateCarCfgInfo.Builder, PsisUpdateCarCfgInfoOrBuilder> repeatedFieldBuilder = this.carCfgListBuilder_;
                if (repeatedFieldBuilder == null) {
                    return Collections.unmodifiableList(this.carCfgList_);
                }
                return repeatedFieldBuilder.getMessageList();
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfoMapOrBuilder
            public int getCarCfgListCount() {
                RepeatedFieldBuilder<PsisUpdateCarCfgInfo, PsisUpdateCarCfgInfo.Builder, PsisUpdateCarCfgInfoOrBuilder> repeatedFieldBuilder = this.carCfgListBuilder_;
                if (repeatedFieldBuilder == null) {
                    return this.carCfgList_.size();
                }
                return repeatedFieldBuilder.getCount();
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfoMapOrBuilder
            public PsisUpdateCarCfgInfo getCarCfgList(int index) {
                RepeatedFieldBuilder<PsisUpdateCarCfgInfo, PsisUpdateCarCfgInfo.Builder, PsisUpdateCarCfgInfoOrBuilder> repeatedFieldBuilder = this.carCfgListBuilder_;
                if (repeatedFieldBuilder == null) {
                    return this.carCfgList_.get(index);
                }
                return (PsisUpdateCarCfgInfo) repeatedFieldBuilder.getMessage(index);
            }

            public Builder setCarCfgList(int index, PsisUpdateCarCfgInfo value) {
                RepeatedFieldBuilder<PsisUpdateCarCfgInfo, PsisUpdateCarCfgInfo.Builder, PsisUpdateCarCfgInfoOrBuilder> repeatedFieldBuilder = this.carCfgListBuilder_;
                if (repeatedFieldBuilder == null) {
                    if (value == null) {
                        throw null;
                    }
                    ensureCarCfgListIsMutable();
                    this.carCfgList_.set(index, value);
                    onChanged();
                } else {
                    repeatedFieldBuilder.setMessage(index, value);
                }
                return this;
            }

            public Builder setCarCfgList(int index, PsisUpdateCarCfgInfo.Builder builderForValue) {
                RepeatedFieldBuilder<PsisUpdateCarCfgInfo, PsisUpdateCarCfgInfo.Builder, PsisUpdateCarCfgInfoOrBuilder> repeatedFieldBuilder = this.carCfgListBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureCarCfgListIsMutable();
                    this.carCfgList_.set(index, builderForValue.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addCarCfgList(PsisUpdateCarCfgInfo value) {
                RepeatedFieldBuilder<PsisUpdateCarCfgInfo, PsisUpdateCarCfgInfo.Builder, PsisUpdateCarCfgInfoOrBuilder> repeatedFieldBuilder = this.carCfgListBuilder_;
                if (repeatedFieldBuilder == null) {
                    if (value == null) {
                        throw null;
                    }
                    ensureCarCfgListIsMutable();
                    this.carCfgList_.add(value);
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(value);
                }
                return this;
            }

            public Builder addCarCfgList(int index, PsisUpdateCarCfgInfo value) {
                RepeatedFieldBuilder<PsisUpdateCarCfgInfo, PsisUpdateCarCfgInfo.Builder, PsisUpdateCarCfgInfoOrBuilder> repeatedFieldBuilder = this.carCfgListBuilder_;
                if (repeatedFieldBuilder == null) {
                    if (value == null) {
                        throw null;
                    }
                    ensureCarCfgListIsMutable();
                    this.carCfgList_.add(index, value);
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(index, value);
                }
                return this;
            }

            public Builder addCarCfgList(PsisUpdateCarCfgInfo.Builder builderForValue) {
                RepeatedFieldBuilder<PsisUpdateCarCfgInfo, PsisUpdateCarCfgInfo.Builder, PsisUpdateCarCfgInfoOrBuilder> repeatedFieldBuilder = this.carCfgListBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureCarCfgListIsMutable();
                    this.carCfgList_.add(builderForValue.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addCarCfgList(int index, PsisUpdateCarCfgInfo.Builder builderForValue) {
                RepeatedFieldBuilder<PsisUpdateCarCfgInfo, PsisUpdateCarCfgInfo.Builder, PsisUpdateCarCfgInfoOrBuilder> repeatedFieldBuilder = this.carCfgListBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureCarCfgListIsMutable();
                    this.carCfgList_.add(index, builderForValue.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllCarCfgList(Iterable<? extends PsisUpdateCarCfgInfo> values) {
                RepeatedFieldBuilder<PsisUpdateCarCfgInfo, PsisUpdateCarCfgInfo.Builder, PsisUpdateCarCfgInfoOrBuilder> repeatedFieldBuilder = this.carCfgListBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureCarCfgListIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) values, (List) this.carCfgList_);
                    onChanged();
                } else {
                    repeatedFieldBuilder.addAllMessages(values);
                }
                return this;
            }

            public Builder clearCarCfgList() {
                RepeatedFieldBuilder<PsisUpdateCarCfgInfo, PsisUpdateCarCfgInfo.Builder, PsisUpdateCarCfgInfoOrBuilder> repeatedFieldBuilder = this.carCfgListBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.carCfgList_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilder.clear();
                }
                return this;
            }

            public Builder removeCarCfgList(int index) {
                RepeatedFieldBuilder<PsisUpdateCarCfgInfo, PsisUpdateCarCfgInfo.Builder, PsisUpdateCarCfgInfoOrBuilder> repeatedFieldBuilder = this.carCfgListBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureCarCfgListIsMutable();
                    this.carCfgList_.remove(index);
                    onChanged();
                } else {
                    repeatedFieldBuilder.remove(index);
                }
                return this;
            }

            public PsisUpdateCarCfgInfo.Builder getCarCfgListBuilder(int index) {
                return (PsisUpdateCarCfgInfo.Builder) getCarCfgListFieldBuilder().getBuilder(index);
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfoMapOrBuilder
            public PsisUpdateCarCfgInfoOrBuilder getCarCfgListOrBuilder(int index) {
                RepeatedFieldBuilder<PsisUpdateCarCfgInfo, PsisUpdateCarCfgInfo.Builder, PsisUpdateCarCfgInfoOrBuilder> repeatedFieldBuilder = this.carCfgListBuilder_;
                if (repeatedFieldBuilder == null) {
                    return this.carCfgList_.get(index);
                }
                return (PsisUpdateCarCfgInfoOrBuilder) repeatedFieldBuilder.getMessageOrBuilder(index);
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.PsisUpdateCarCfgInfoMapOrBuilder
            public List<? extends PsisUpdateCarCfgInfoOrBuilder> getCarCfgListOrBuilderList() {
                RepeatedFieldBuilder<PsisUpdateCarCfgInfo, PsisUpdateCarCfgInfo.Builder, PsisUpdateCarCfgInfoOrBuilder> repeatedFieldBuilder = this.carCfgListBuilder_;
                if (repeatedFieldBuilder != null) {
                    return repeatedFieldBuilder.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.carCfgList_);
            }

            public PsisUpdateCarCfgInfo.Builder addCarCfgListBuilder() {
                return (PsisUpdateCarCfgInfo.Builder) getCarCfgListFieldBuilder().addBuilder(PsisUpdateCarCfgInfo.getDefaultInstance());
            }

            public PsisUpdateCarCfgInfo.Builder addCarCfgListBuilder(int index) {
                return (PsisUpdateCarCfgInfo.Builder) getCarCfgListFieldBuilder().addBuilder(index, PsisUpdateCarCfgInfo.getDefaultInstance());
            }

            public List<PsisUpdateCarCfgInfo.Builder> getCarCfgListBuilderList() {
                return getCarCfgListFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<PsisUpdateCarCfgInfo, PsisUpdateCarCfgInfo.Builder, PsisUpdateCarCfgInfoOrBuilder> getCarCfgListFieldBuilder() {
                if (this.carCfgListBuilder_ == null) {
                    this.carCfgListBuilder_ = new RepeatedFieldBuilder<>(this.carCfgList_, (this.bitField0_ & 1) == 1, getParentForChildren(), isClean());
                    this.carCfgList_ = null;
                }
                return this.carCfgListBuilder_;
            }
        }
    }

    public static final class NtfThermalInfoItem extends GeneratedMessage implements NtfThermalInfoItemOrBuilder {
        public static final int NTFTEMP_ID_FIELD_NUMBER = 1;
        public static final int NTFTEMP_LEVEL_FIELD_NUMBER = 4;
        public static final int NTFTEMP_NAME_FIELD_NUMBER = 2;
        public static final int NTFTEMP_VALUE_FIELD_NUMBER = 3;
        public static Parser<NtfThermalInfoItem> PARSER = new AbstractParser<NtfThermalInfoItem>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoItem.1
            @Override // com.google.protobuf.Parser
            public NtfThermalInfoItem parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new NtfThermalInfoItem(input, extensionRegistry);
            }
        };
        private static final NtfThermalInfoItem defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private NtfTempSensor_t ntftempId_;
        private NtfTempLevel_t ntftempLevel_;
        private Object ntftempName_;
        private float ntftempValue_;
        private final UnknownFieldSet unknownFields;

        private NtfThermalInfoItem(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private NtfThermalInfoItem(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static NtfThermalInfoItem getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public NtfThermalInfoItem getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private NtfThermalInfoItem(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 8) {
                                int rawValue = input.readEnum();
                                NtfTempSensor_t value = NtfTempSensor_t.valueOf(rawValue);
                                if (value != null) {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.ntftempId_ = value;
                                } else {
                                    unknownFields.mergeVarintField(1, rawValue);
                                }
                            } else if (tag == 18) {
                                ByteString bs = input.readBytes();
                                this.bitField0_ |= 2;
                                this.ntftempName_ = bs;
                            } else if (tag == 29) {
                                this.bitField0_ |= 4;
                                this.ntftempValue_ = input.readFloat();
                            } else if (tag != 32) {
                                if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                    done = true;
                                }
                            } else {
                                int rawValue2 = input.readEnum();
                                NtfTempLevel_t value2 = NtfTempLevel_t.valueOf(rawValue2);
                                if (value2 == null) {
                                    unknownFields.mergeVarintField(4, rawValue2);
                                } else {
                                    this.bitField0_ = 8 | this.bitField0_;
                                    this.ntftempLevel_ = value2;
                                }
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        }
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_NtfThermalInfoItem_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_NtfThermalInfoItem_fieldAccessorTable.ensureFieldAccessorsInitialized(NtfThermalInfoItem.class, Builder.class);
        }

        static {
            NtfThermalInfoItem ntfThermalInfoItem = new NtfThermalInfoItem(true);
            defaultInstance = ntfThermalInfoItem;
            ntfThermalInfoItem.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<NtfThermalInfoItem> getParserForType() {
            return PARSER;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoItemOrBuilder
        public boolean hasNtftempId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoItemOrBuilder
        public NtfTempSensor_t getNtftempId() {
            return this.ntftempId_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoItemOrBuilder
        public boolean hasNtftempName() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoItemOrBuilder
        public String getNtftempName() {
            Object ref = this.ntftempName_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.ntftempName_ = s;
            }
            return s;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoItemOrBuilder
        public ByteString getNtftempNameBytes() {
            Object ref = this.ntftempName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.ntftempName_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoItemOrBuilder
        public boolean hasNtftempValue() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoItemOrBuilder
        public float getNtftempValue() {
            return this.ntftempValue_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoItemOrBuilder
        public boolean hasNtftempLevel() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoItemOrBuilder
        public NtfTempLevel_t getNtftempLevel() {
            return this.ntftempLevel_;
        }

        private void initFields() {
            this.ntftempId_ = NtfTempSensor_t.Ntf_AOSS;
            this.ntftempName_ = "";
            this.ntftempValue_ = 0.0f;
            this.ntftempLevel_ = NtfTempLevel_t.LV_ORDINARY;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasNtftempId()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasNtftempName()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasNtftempValue()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasNtftempLevel()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.ntftempId_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, getNtftempNameBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeFloat(3, this.ntftempValue_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeEnum(4, this.ntftempLevel_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.ntftempId_.getNumber()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBytesSize(2, getNtftempNameBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeFloatSize(3, this.ntftempValue_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeEnumSize(4, this.ntftempLevel_.getNumber());
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static NtfThermalInfoItem parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static NtfThermalInfoItem parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static NtfThermalInfoItem parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static NtfThermalInfoItem parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static NtfThermalInfoItem parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static NtfThermalInfoItem parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static NtfThermalInfoItem parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static NtfThermalInfoItem parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static NtfThermalInfoItem parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static NtfThermalInfoItem parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(NtfThermalInfoItem prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements NtfThermalInfoItemOrBuilder {
            private int bitField0_;
            private NtfTempSensor_t ntftempId_;
            private NtfTempLevel_t ntftempLevel_;
            private Object ntftempName_;
            private float ntftempValue_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_NtfThermalInfoItem_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_NtfThermalInfoItem_fieldAccessorTable.ensureFieldAccessorsInitialized(NtfThermalInfoItem.class, Builder.class);
            }

            private Builder() {
                this.ntftempId_ = NtfTempSensor_t.Ntf_AOSS;
                this.ntftempName_ = "";
                this.ntftempLevel_ = NtfTempLevel_t.LV_ORDINARY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.ntftempId_ = NtfTempSensor_t.Ntf_AOSS;
                this.ntftempName_ = "";
                this.ntftempLevel_ = NtfTempLevel_t.LV_ORDINARY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = NtfThermalInfoItem.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.ntftempId_ = NtfTempSensor_t.Ntf_AOSS;
                int i = this.bitField0_ & (-2);
                this.bitField0_ = i;
                this.ntftempName_ = "";
                int i2 = i & (-3);
                this.bitField0_ = i2;
                this.ntftempValue_ = 0.0f;
                this.bitField0_ = i2 & (-5);
                this.ntftempLevel_ = NtfTempLevel_t.LV_ORDINARY;
                this.bitField0_ &= -9;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_NtfThermalInfoItem_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public NtfThermalInfoItem getDefaultInstanceForType() {
                return NtfThermalInfoItem.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public NtfThermalInfoItem build() {
                NtfThermalInfoItem result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public NtfThermalInfoItem buildPartial() {
                NtfThermalInfoItem result = new NtfThermalInfoItem(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.ntftempId_ = this.ntftempId_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.ntftempName_ = this.ntftempName_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.ntftempValue_ = this.ntftempValue_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.ntftempLevel_ = this.ntftempLevel_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof NtfThermalInfoItem) {
                    return mergeFrom((NtfThermalInfoItem) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(NtfThermalInfoItem other) {
                if (other == NtfThermalInfoItem.getDefaultInstance()) {
                    return this;
                }
                if (other.hasNtftempId()) {
                    setNtftempId(other.getNtftempId());
                }
                if (other.hasNtftempName()) {
                    this.bitField0_ |= 2;
                    this.ntftempName_ = other.ntftempName_;
                    onChanged();
                }
                if (other.hasNtftempValue()) {
                    setNtftempValue(other.getNtftempValue());
                }
                if (other.hasNtftempLevel()) {
                    setNtftempLevel(other.getNtftempLevel());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasNtftempId() && hasNtftempName() && hasNtftempValue() && hasNtftempLevel();
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                NtfThermalInfoItem parsedMessage = null;
                try {
                    try {
                        parsedMessage = NtfThermalInfoItem.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoItemOrBuilder
            public boolean hasNtftempId() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoItemOrBuilder
            public NtfTempSensor_t getNtftempId() {
                return this.ntftempId_;
            }

            public Builder setNtftempId(NtfTempSensor_t value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.ntftempId_ = value;
                onChanged();
                return this;
            }

            public Builder clearNtftempId() {
                this.bitField0_ &= -2;
                this.ntftempId_ = NtfTempSensor_t.Ntf_AOSS;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoItemOrBuilder
            public boolean hasNtftempName() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoItemOrBuilder
            public String getNtftempName() {
                Object ref = this.ntftempName_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.ntftempName_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoItemOrBuilder
            public ByteString getNtftempNameBytes() {
                Object ref = this.ntftempName_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.ntftempName_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setNtftempName(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.ntftempName_ = value;
                onChanged();
                return this;
            }

            public Builder clearNtftempName() {
                this.bitField0_ &= -3;
                this.ntftempName_ = NtfThermalInfoItem.getDefaultInstance().getNtftempName();
                onChanged();
                return this;
            }

            public Builder setNtftempNameBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.ntftempName_ = value;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoItemOrBuilder
            public boolean hasNtftempValue() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoItemOrBuilder
            public float getNtftempValue() {
                return this.ntftempValue_;
            }

            public Builder setNtftempValue(float value) {
                this.bitField0_ |= 4;
                this.ntftempValue_ = value;
                onChanged();
                return this;
            }

            public Builder clearNtftempValue() {
                this.bitField0_ &= -5;
                this.ntftempValue_ = 0.0f;
                onChanged();
                return this;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoItemOrBuilder
            public boolean hasNtftempLevel() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoItemOrBuilder
            public NtfTempLevel_t getNtftempLevel() {
                return this.ntftempLevel_;
            }

            public Builder setNtftempLevel(NtfTempLevel_t value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 8;
                this.ntftempLevel_ = value;
                onChanged();
                return this;
            }

            public Builder clearNtftempLevel() {
                this.bitField0_ &= -9;
                this.ntftempLevel_ = NtfTempLevel_t.LV_ORDINARY;
                onChanged();
                return this;
            }
        }
    }

    public static final class NtfThermalInfoMsg extends GeneratedMessage implements NtfThermalInfoMsgOrBuilder {
        public static final int ITEMS_FIELD_NUMBER = 1;
        public static Parser<NtfThermalInfoMsg> PARSER = new AbstractParser<NtfThermalInfoMsg>() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoMsg.1
            @Override // com.google.protobuf.Parser
            public NtfThermalInfoMsg parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new NtfThermalInfoMsg(input, extensionRegistry);
            }
        };
        private static final NtfThermalInfoMsg defaultInstance;
        private static final long serialVersionUID = 0;
        private List<NtfThermalInfoItem> items_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        private NtfThermalInfoMsg(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private NtfThermalInfoMsg(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static NtfThermalInfoMsg getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public NtfThermalInfoMsg getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private NtfThermalInfoMsg(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            int mutable_bitField0_ = 0;
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (true) {
                if (done) {
                    break;
                }
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 10) {
                            if ((mutable_bitField0_ & 1) != 1) {
                                this.items_ = new ArrayList();
                                mutable_bitField0_ |= 1;
                            }
                            this.items_.add((NtfThermalInfoItem) input.readMessage(NtfThermalInfoItem.PARSER, extensionRegistry));
                        } else if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                            done = true;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    if ((mutable_bitField0_ & 1) == 1) {
                        this.items_ = Collections.unmodifiableList(this.items_);
                    }
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_NtfThermalInfoMsg_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_NtfThermalInfoMsg_fieldAccessorTable.ensureFieldAccessorsInitialized(NtfThermalInfoMsg.class, Builder.class);
        }

        static {
            NtfThermalInfoMsg ntfThermalInfoMsg = new NtfThermalInfoMsg(true);
            defaultInstance = ntfThermalInfoMsg;
            ntfThermalInfoMsg.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<NtfThermalInfoMsg> getParserForType() {
            return PARSER;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoMsgOrBuilder
        public List<NtfThermalInfoItem> getItemsList() {
            return this.items_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoMsgOrBuilder
        public List<? extends NtfThermalInfoItemOrBuilder> getItemsOrBuilderList() {
            return this.items_;
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoMsgOrBuilder
        public int getItemsCount() {
            return this.items_.size();
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoMsgOrBuilder
        public NtfThermalInfoItem getItems(int index) {
            return this.items_.get(index);
        }

        @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoMsgOrBuilder
        public NtfThermalInfoItemOrBuilder getItemsOrBuilder(int index) {
            return this.items_.get(index);
        }

        private void initFields() {
            this.items_ = Collections.emptyList();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            for (int i = 0; i < getItemsCount(); i++) {
                if (!getItems(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            for (int i = 0; i < this.items_.size(); i++) {
                output.writeMessage(1, this.items_.get(i));
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = 0;
            for (int i = 0; i < this.items_.size(); i++) {
                size2 += CodedOutputStream.computeMessageSize(1, this.items_.get(i));
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static NtfThermalInfoMsg parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static NtfThermalInfoMsg parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static NtfThermalInfoMsg parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static NtfThermalInfoMsg parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static NtfThermalInfoMsg parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static NtfThermalInfoMsg parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static NtfThermalInfoMsg parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static NtfThermalInfoMsg parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static NtfThermalInfoMsg parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static NtfThermalInfoMsg parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(NtfThermalInfoMsg prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements NtfThermalInfoMsgOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilder<NtfThermalInfoItem, NtfThermalInfoItem.Builder, NtfThermalInfoItemOrBuilder> itemsBuilder_;
            private List<NtfThermalInfoItem> items_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_NtfThermalInfoMsg_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_NtfThermalInfoMsg_fieldAccessorTable.ensureFieldAccessorsInitialized(NtfThermalInfoMsg.class, Builder.class);
            }

            private Builder() {
                this.items_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.items_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (NtfThermalInfoMsg.alwaysUseFieldBuilders) {
                    getItemsFieldBuilder();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                RepeatedFieldBuilder<NtfThermalInfoItem, NtfThermalInfoItem.Builder, NtfThermalInfoItemOrBuilder> repeatedFieldBuilder = this.itemsBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.items_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilder.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ComBoschCmPlatformNotifCtr.internal_static_bosch_platform_notif_ctr_pb_NtfThermalInfoMsg_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public NtfThermalInfoMsg getDefaultInstanceForType() {
                return NtfThermalInfoMsg.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public NtfThermalInfoMsg build() {
                NtfThermalInfoMsg result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public NtfThermalInfoMsg buildPartial() {
                NtfThermalInfoMsg result = new NtfThermalInfoMsg(this);
                int i = this.bitField0_;
                RepeatedFieldBuilder<NtfThermalInfoItem, NtfThermalInfoItem.Builder, NtfThermalInfoItemOrBuilder> repeatedFieldBuilder = this.itemsBuilder_;
                if (repeatedFieldBuilder != null) {
                    result.items_ = repeatedFieldBuilder.build();
                } else {
                    if ((this.bitField0_ & 1) == 1) {
                        this.items_ = Collections.unmodifiableList(this.items_);
                        this.bitField0_ &= -2;
                    }
                    result.items_ = this.items_;
                }
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof NtfThermalInfoMsg) {
                    return mergeFrom((NtfThermalInfoMsg) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(NtfThermalInfoMsg other) {
                if (other == NtfThermalInfoMsg.getDefaultInstance()) {
                    return this;
                }
                if (this.itemsBuilder_ == null) {
                    if (!other.items_.isEmpty()) {
                        if (this.items_.isEmpty()) {
                            this.items_ = other.items_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureItemsIsMutable();
                            this.items_.addAll(other.items_);
                        }
                        onChanged();
                    }
                } else if (!other.items_.isEmpty()) {
                    if (!this.itemsBuilder_.isEmpty()) {
                        this.itemsBuilder_.addAllMessages(other.items_);
                    } else {
                        this.itemsBuilder_.dispose();
                        RepeatedFieldBuilder<NtfThermalInfoItem, NtfThermalInfoItem.Builder, NtfThermalInfoItemOrBuilder> itemsFieldBuilder = null;
                        this.itemsBuilder_ = null;
                        this.items_ = other.items_;
                        this.bitField0_ &= -2;
                        if (NtfThermalInfoMsg.alwaysUseFieldBuilders) {
                            itemsFieldBuilder = getItemsFieldBuilder();
                        }
                        this.itemsBuilder_ = itemsFieldBuilder;
                    }
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getItemsCount(); i++) {
                    if (!getItems(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                NtfThermalInfoMsg parsedMessage = null;
                try {
                    try {
                        parsedMessage = NtfThermalInfoMsg.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            private void ensureItemsIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.items_ = new ArrayList(this.items_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoMsgOrBuilder
            public List<NtfThermalInfoItem> getItemsList() {
                RepeatedFieldBuilder<NtfThermalInfoItem, NtfThermalInfoItem.Builder, NtfThermalInfoItemOrBuilder> repeatedFieldBuilder = this.itemsBuilder_;
                if (repeatedFieldBuilder == null) {
                    return Collections.unmodifiableList(this.items_);
                }
                return repeatedFieldBuilder.getMessageList();
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoMsgOrBuilder
            public int getItemsCount() {
                RepeatedFieldBuilder<NtfThermalInfoItem, NtfThermalInfoItem.Builder, NtfThermalInfoItemOrBuilder> repeatedFieldBuilder = this.itemsBuilder_;
                if (repeatedFieldBuilder == null) {
                    return this.items_.size();
                }
                return repeatedFieldBuilder.getCount();
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoMsgOrBuilder
            public NtfThermalInfoItem getItems(int index) {
                RepeatedFieldBuilder<NtfThermalInfoItem, NtfThermalInfoItem.Builder, NtfThermalInfoItemOrBuilder> repeatedFieldBuilder = this.itemsBuilder_;
                if (repeatedFieldBuilder == null) {
                    return this.items_.get(index);
                }
                return (NtfThermalInfoItem) repeatedFieldBuilder.getMessage(index);
            }

            public Builder setItems(int index, NtfThermalInfoItem value) {
                RepeatedFieldBuilder<NtfThermalInfoItem, NtfThermalInfoItem.Builder, NtfThermalInfoItemOrBuilder> repeatedFieldBuilder = this.itemsBuilder_;
                if (repeatedFieldBuilder == null) {
                    if (value == null) {
                        throw null;
                    }
                    ensureItemsIsMutable();
                    this.items_.set(index, value);
                    onChanged();
                } else {
                    repeatedFieldBuilder.setMessage(index, value);
                }
                return this;
            }

            public Builder setItems(int index, NtfThermalInfoItem.Builder builderForValue) {
                RepeatedFieldBuilder<NtfThermalInfoItem, NtfThermalInfoItem.Builder, NtfThermalInfoItemOrBuilder> repeatedFieldBuilder = this.itemsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureItemsIsMutable();
                    this.items_.set(index, builderForValue.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.setMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addItems(NtfThermalInfoItem value) {
                RepeatedFieldBuilder<NtfThermalInfoItem, NtfThermalInfoItem.Builder, NtfThermalInfoItemOrBuilder> repeatedFieldBuilder = this.itemsBuilder_;
                if (repeatedFieldBuilder == null) {
                    if (value == null) {
                        throw null;
                    }
                    ensureItemsIsMutable();
                    this.items_.add(value);
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(value);
                }
                return this;
            }

            public Builder addItems(int index, NtfThermalInfoItem value) {
                RepeatedFieldBuilder<NtfThermalInfoItem, NtfThermalInfoItem.Builder, NtfThermalInfoItemOrBuilder> repeatedFieldBuilder = this.itemsBuilder_;
                if (repeatedFieldBuilder == null) {
                    if (value == null) {
                        throw null;
                    }
                    ensureItemsIsMutable();
                    this.items_.add(index, value);
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(index, value);
                }
                return this;
            }

            public Builder addItems(NtfThermalInfoItem.Builder builderForValue) {
                RepeatedFieldBuilder<NtfThermalInfoItem, NtfThermalInfoItem.Builder, NtfThermalInfoItemOrBuilder> repeatedFieldBuilder = this.itemsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureItemsIsMutable();
                    this.items_.add(builderForValue.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(builderForValue.build());
                }
                return this;
            }

            public Builder addItems(int index, NtfThermalInfoItem.Builder builderForValue) {
                RepeatedFieldBuilder<NtfThermalInfoItem, NtfThermalInfoItem.Builder, NtfThermalInfoItemOrBuilder> repeatedFieldBuilder = this.itemsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureItemsIsMutable();
                    this.items_.add(index, builderForValue.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(index, builderForValue.build());
                }
                return this;
            }

            public Builder addAllItems(Iterable<? extends NtfThermalInfoItem> values) {
                RepeatedFieldBuilder<NtfThermalInfoItem, NtfThermalInfoItem.Builder, NtfThermalInfoItemOrBuilder> repeatedFieldBuilder = this.itemsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureItemsIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) values, (List) this.items_);
                    onChanged();
                } else {
                    repeatedFieldBuilder.addAllMessages(values);
                }
                return this;
            }

            public Builder clearItems() {
                RepeatedFieldBuilder<NtfThermalInfoItem, NtfThermalInfoItem.Builder, NtfThermalInfoItemOrBuilder> repeatedFieldBuilder = this.itemsBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.items_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilder.clear();
                }
                return this;
            }

            public Builder removeItems(int index) {
                RepeatedFieldBuilder<NtfThermalInfoItem, NtfThermalInfoItem.Builder, NtfThermalInfoItemOrBuilder> repeatedFieldBuilder = this.itemsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureItemsIsMutable();
                    this.items_.remove(index);
                    onChanged();
                } else {
                    repeatedFieldBuilder.remove(index);
                }
                return this;
            }

            public NtfThermalInfoItem.Builder getItemsBuilder(int index) {
                return (NtfThermalInfoItem.Builder) getItemsFieldBuilder().getBuilder(index);
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoMsgOrBuilder
            public NtfThermalInfoItemOrBuilder getItemsOrBuilder(int index) {
                RepeatedFieldBuilder<NtfThermalInfoItem, NtfThermalInfoItem.Builder, NtfThermalInfoItemOrBuilder> repeatedFieldBuilder = this.itemsBuilder_;
                if (repeatedFieldBuilder == null) {
                    return this.items_.get(index);
                }
                return (NtfThermalInfoItemOrBuilder) repeatedFieldBuilder.getMessageOrBuilder(index);
            }

            @Override // bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.NtfThermalInfoMsgOrBuilder
            public List<? extends NtfThermalInfoItemOrBuilder> getItemsOrBuilderList() {
                RepeatedFieldBuilder<NtfThermalInfoItem, NtfThermalInfoItem.Builder, NtfThermalInfoItemOrBuilder> repeatedFieldBuilder = this.itemsBuilder_;
                if (repeatedFieldBuilder != null) {
                    return repeatedFieldBuilder.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.items_);
            }

            public NtfThermalInfoItem.Builder addItemsBuilder() {
                return (NtfThermalInfoItem.Builder) getItemsFieldBuilder().addBuilder(NtfThermalInfoItem.getDefaultInstance());
            }

            public NtfThermalInfoItem.Builder addItemsBuilder(int index) {
                return (NtfThermalInfoItem.Builder) getItemsFieldBuilder().addBuilder(index, NtfThermalInfoItem.getDefaultInstance());
            }

            public List<NtfThermalInfoItem.Builder> getItemsBuilderList() {
                return getItemsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<NtfThermalInfoItem, NtfThermalInfoItem.Builder, NtfThermalInfoItemOrBuilder> getItemsFieldBuilder() {
                if (this.itemsBuilder_ == null) {
                    this.itemsBuilder_ = new RepeatedFieldBuilder<>(this.items_, (this.bitField0_ & 1) == 1, getParentForChildren(), isClean());
                    this.items_ = null;
                }
                return this.itemsBuilder_;
            }
        }
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = {"\n%com.bosch.cm.platform.notif.ctr.proto\u0012\u001bbosch.platform.notif.ctr.pb\"!\n\u000fRtcUpdateEvents\u0012\u000e\n\u0006result\u0018\u0001 \u0002(\r\"8\n\u000bVehicleInfo\u0012\u000b\n\u0003vin\u0018\u0001 \u0002(\t\u0012\u000b\n\u0003mfr\u0018\u0002 \u0001(\t\u0012\u000f\n\u0007veh_ver\u0018\u0003 \u0001(\t\"\u009c\u0002\n\nModuleStat\u0012\f\n\u0004name\u0018\u0001 \u0002(\t\u00125\n\u0006domain\u0018\u0002 \u0002(\u000e2%.bosch.platform.notif.ctr.pb.DomainId\u00123\n\u0006status\u0018\u0003 \u0002(\u000e2#.bosch.platform.notif.ctr.pb.Status\u0012\u000f\n\u0007voltage\u0018\u0004 \u0001(\u0002\u0012\u0013\n\u000btemperature\u0018\u0005 \u0001(\u0002\u0012?\n\ntemp_level\u0018\u0006 \u0001(\u000e2+.bosch.platform.notif.ctr.pb.NtfTempLevel_", "t\u0012\u0014\n\felapsed_time\u0018\u0007 \u0001(\r\u0012\u0017\n\u000fcpu_temperature\u0018\b \u0001(\u0002\"å\u0002\n\nModuleInfo\u0012\f\n\u0004name\u0018\u0001 \u0002(\t\u00125\n\u0006domain\u0018\u0002 \u0002(\u000e2%.bosch.platform.notif.ctr.pb.DomainId\u0012\n\n\u0002pn\u0018\u0003 \u0001(\t\u0012\n\n\u0002sn\u0018\u0004 \u0001(\t\u0012\u000e\n\u0006fw_ver\u0018\u0005 \u0001(\t\u0012\u000e\n\u0006hw_ver\u0018\u0006 \u0001(\t\u0012\u000e\n\u0006sw_ver\u0018\u0007 \u0001(\t\u0012\u0011\n\tnode_addr\u0018\b \u0001(\t\u0012\r\n\u0005hw_id\u0018\t \u0001(\t\u0012\u000f\n\u0007comp_id\u0018\n \u0001(\t\u0012\u0010\n\btool_ver\u0018\u000b \u0001(\t\u0012\u0016\n\u000einstaller_type\u0018\f \u0001(\t\u0012\u000f\n\u0007ser_ver\u0018\r \u0001(\t\u0012\u000f\n\u0007sdb_ver\u0018\u000e \u0001(\t\u0012\u0010\n\bprop_ver\u0018\u000f \u0001(\t\u0012\u0015\n\rsupplier_info\u0018\u0010 \u0001(\t\u0012\u0010\n\blcd_temp\u0018\u0011 \u0001(\t\u0012\u0010\n\bpcb_te", "mp\u0018\u0012 \u0001(\t\"â\u0002\n\tBuildInfo\u0012\u0012\n\nbuild_date\u0018\u0001 \u0001(\t\u0012\u0015\n\rmain_ver_name\u0018\u0002 \u0001(\t\u0012\u0010\n\bmain_ver\u0018\u0003 \u0001(\t\u0012\u0014\n\fbuild_target\u0018\u0004 \u0001(\t\u0012\u0014\n\fbuild_number\u0018\u0005 \u0001(\t\u0012\u0010\n\bbuild_id\u0018\u0006 \u0001(\t\u0012\u0010\n\bjob_name\u0018\u0007 \u0001(\t\u0012\u0013\n\u000bsoc_qnx_ver\u0018\b \u0001(\t\u0012\u0017\n\u000fsoc_android_ver\u0018\t \u0001(\t\u0012\u0013\n\u000bscc_app_ver\u0018\n \u0001(\t\u0012\u0012\n\nscc_bl_ver\u0018\u000b \u0001(\t\u0012\u0014\n\fmanifest_rev\u0018\f \u0001(\t\u0012\u0017\n\u000fscc_byd_app_ver\u0018\r \u0001(\t\u0012\u0016\n\u000escc_byd_bl_ver\u0018\u000e \u0001(\t\u0012\u0018\n\u0010scc_variant_code\u0018\u000f \u0001(\t\u0012\u0010\n\bboot_ver\u0018\u0010 \u0001(\t\"\u0086\u0002\n\u000fDispPwrSplyStat\u0012>\n\fcluster_sta", "t\u0018\u0001 \u0002(\u000e2(.bosch.platform.notif.ctr.pb.PwrSplyStat\u0012:\n\bivi_stat\u0018\u0002 \u0002(\u000e2(.bosch.platform.notif.ctr.pb.PwrSplyStat\u0012;\n\thvac_stat\u0018\u0003 \u0002(\u000e2(.bosch.platform.notif.ctr.pb.PwrSplyStat\u0012:\n\bhud_stat\u0018\u0004 \u0002(\u000e2(.bosch.platform.notif.ctr.pb.PwrSplyStat\"\u0099\u0002\n\u000bPwrModeStat\u0012=\n\npower_mode\u0018\u0001 \u0002(\u000e2).bosch.platform.notif.ctr.pb.NtfPowerMode\u0012C\n\rvoltage_state\u0018\u0002 \u0002(\u000e2,.bosch.platform.notif.ctr.pb.NtfVoltageState\u0012@\n\tsave_mode\u0018\u0003 \u0001(\u000e2-.", "bosch.platform.notif.ctr.pb.NtfPowerSaveMode\u0012D\n\u000eipk_power_mode\u0018\u0004 \u0001(\u000e2,.bosch.platform.notif.ctr.pb.NtfIpkPowerMode\"å\u0002\n\u0010LcmHmiModeEvents\u0012?\n\u000bdoor_opened\u0018\u0001 \u0002(\u000e2*.bosch.platform.notif.ctr.pb.NTFDrvDoorSts\u0012?\n\u000blcd_warning\u0018\u0002 \u0002(\u000e2*.bosch.platform.notif.ctr.pb.NTFWarningSts\u0012;\n\ncar_locked\u0018\u0003 \u0002(\u000e2'.bosch.platform.notif.ctr.pb.NTFLockSts\u0012B\n\rcharging_info\u0018\u0004 \u0002(\u000e2+.bosch.platform.notif.ctr.pb.NTFChargingsts\u0012=\n\twa", "rn_info\u0018\u0005 \u0002(\u000e2*.bosch.platform.notif.ctr.pb.NTFWarningSts\u0012\u000f\n\u0007tt_info\u0018\u0006 \u0002(\r\"§\u0001\n\u000fLcmTimingEvents\u0012\u0010\n\bsoc_time\u0018\u0001 \u0001(\t\u0012\u0015\n\rpoweroff_time\u0018\u0002 \u0001(\t\u0012\u0019\n\u0011androidstart_time\u0018\u0003 \u0001(\t\u0012\u0012\n\naccon_time\u0018\u0004 \u0001(\t\u0012\u0013\n\u000baccoff_time\u0018\u0005 \u0001(\t\u0012\u0012\n\nengon_time\u0018\u0006 \u0001(\t\u0012\u0013\n\u000bengoff_time\u0018\u0007 \u0001(\t\"f\n\u0012CompntHealthStatus\u00129\n\u0006status\u0018\u0001 \u0002(\u000e2).bosch.platform.notif.ctr.pb.HealthStatus\u0012\u0015\n\rprocessed_msg\u0018\u0002 \u0001(\r\"]\n\u000bDiagDtcInfo\u0012\u0012\n\ndtc_number\u0018\u0001 \u0002(\r\u0012:\n\u0006result\u0018\u0002 \u0002(\u000e2", "*.bosch.platform.notif.ctr.pb.DiagDtcResult\"C\n\u000bMsgLogLevel\u00124\n\u0005level\u0018\u0001 \u0002(\u000e2%.bosch.platform.notif.ctr.pb.LogLevel\"N\n\u000fswitchEfuseInfo\u0012;\n\tefuseinfo\u0018\u0003 \u0002(\u000e2(.bosch.platform.notif.ctr.pb.EfuseStatus\"^\n\u000fDispHudRotation\u0012<\n\tdirection\u0018\u0001 \u0002(\u000e2).bosch.platform.notif.ctr.pb.HudRotateDir\u0012\r\n\u0005level\u0018\u0002 \u0002(\r\"@\n\u0014PsisUpdateCarCfgInfo\u0012\u0010\n\bkey_name\u0018\u0001 \u0002(\t\u0012\u0016\n\u000ekey_value_char\u0018\u0002 \u0002(\f\"b\n\u0017PsisUpdateCarCfgInfoMap\u0012G\n\fcar_cfg_list\u0018\u0001 ", "\u0003(\u000b21.bosch.platform.notif.ctr.pb.PsisUpdateCarCfgInfo\"Ç\u0001\n\u0012NtfThermalInfoItem\u0012@\n\nntftemp_id\u0018\u0001 \u0002(\u000e2,.bosch.platform.notif.ctr.pb.NtfTempSensor_t\u0012\u0014\n\fntftemp_name\u0018\u0002 \u0002(\t\u0012\u0015\n\rntftemp_value\u0018\u0003 \u0002(\u0002\u0012B\n\rntftemp_level\u0018\u0004 \u0002(\u000e2+.bosch.platform.notif.ctr.pb.NtfTempLevel_t\"S\n\u0011NtfThermalInfoMsg\u0012>\n\u0005items\u0018\u0001 \u0003(\u000b2/.bosch.platform.notif.ctr.pb.NtfThermalInfoItem*\u0090\u0001\n\bDomainId\u0012\u0012\n\u000eDOMAIN_UNKNOWN\u0010\u0000\u0012\u000f\n\u000bDOMAIN_NONE\u0010\u0001\u0012\u0010\n\fDOMAI", "N_LINUX\u0010\u0002\u0012\u000e\n\nDOMAIN_QNX\u0010\u0003\u0012\u0012\n\u000eDOMAIN_ANDROID\u0010\u0004\u0012\u0012\n\u000eDOMAIN_AUTOSAR\u0010\u0005\u0012\u0015\n\u0011DOMAIN_PERIPHERAL\u0010\u0006*¢\b\n\u000fFdbusModTopicId\u0012\u0012\n\u000eTPC_MOD_HW_BRD\u0010\u0000\u0012\u0016\n\u0012TPC_MOD_HW_BRD_SCC\u0010\u0001\u0012\u0016\n\u0012TPC_MOD_HW_BRD_SOC\u0010\u0002\u0012\u001d\n\u0017TPC_MOD_HW_CTR_IVI_DISP\u0010\u0080\u0080\u0004\u0012\u001d\n\u0017TPC_MOD_HW_COD_IVI_DISP\u0010\u0081\u0080\u0004\u0012\u001d\n\u0017TPC_MOD_HW_CLUSTER_DISP\u0010\u0082\u0080\u0004\u0012\u0019\n\u0013TPC_MOD_HW_HUD_DISP\u0010\u0083\u0080\u0004\u0012\u001e\n\u0018TPC_MOD_HW_CTRL_PNL_DISP\u0010\u0084\u0080\u0004\u0012\u001b\n\u0015TPC_MOD_HW_ETH_SWITCH\u0010\u0085\u0080\u0004\u0012\u0015\n\u000fTPC_MOD_HW_GNSS\u0010\u0086\u0080\u0004\u0012\u0018\n\u0012TPC_MOD_HW_EXT_A", "MP\u0010\u0087\u0080\u0004\u0012\u0014\n\u000eTPC_MOD_HW_A2B\u0010\u0088\u0080\u0004\u0012\u001a\n\u0014TPC_MOD_HW_SER_TI981\u0010\u0089\u0080\u0004\u0012\u001a\n\u0014TPC_MOD_HW_SER_TI983\u0010\u008a\u0080\u0004\u0012\u0015\n\u000fTPC_MOD_SW_DISP\u0010\u0080\u0080\b\u0012\u0018\n\u0012TPC_MOD_SW_VEH_SVC\u0010\u0081\u0080\b\u0012\u0018\n\u0012TPC_MOD_SW_VAR_SVC\u0010\u0082\u0080\b\u0012\u0014\n\u000eTPC_MOD_SW_LCM\u0010\u0083\u0080\b\u0012\u001a\n\u0014TPC_MOD_SW_DIAG_SERV\u0010\u0084\u0080\b\u0012\u0018\n\u0012TPC_MOD_SW_SCC_APP\u0010\u0085\u0080\b\u0012\u0017\n\u0011TPC_MOD_SW_SCC_BL\u0010\u0086\u0080\b\u0012\u001d\n\u0017TPC_MOD_SW_INTERNAL_AVM\u0010\u0080\u0080\f\u0012\u001d\n\u0017TPC_MOD_SW_INTERNAL_RVC\u0010\u0081\u0080\f\u0012\u001c\n\u0016TPC_MOD_SW_QNX_CLUSTER\u0010\u0082\u0080\f\u0012\u001c\n\u0016TPC_MOD_SW_SCC_CLUSTER\u0010\u0083\u0080\f\u0012\u001d\n\u0017TPC_MOD_SW_INT", "ERNAL_HAP\u0010\u0084\u0080\f\u0012\u001d\n\u0017TPC_MOD_SW_INTERNAL_DMS\u0010\u0085\u0080\f\u0012\u001d\n\u0017TPC_MOD_SW_INTERNAL_DVR\u0010\u0086\u0080\f\u0012\u001d\n\u0017TPC_MOD_SW_INTERNAL_OMS\u0010\u0087\u0080\f\u0012\u001d\n\u0017TPC_MOD_SW_EXTERNAL_OMS\u0010\u0088\u0080\f\u0012\u001d\n\u0017TPC_MOD_SW_INTERNAL_IMS\u0010\u0089\u0080\f\u0012!\n\u001bTPC_MOD_SW_INTERNAL_FACE_ID\u0010\u008a\u0080\f\u0012\"\n\u001cTPC_MOD_SW_INTERNAL_AVM_LEFT\u0010\u0080\u0080\u0010\u0012#\n\u001dTPC_MOD_SW_INTERNAL_AVM_FRONT\u0010\u0081\u0080\u0010\u0012\"\n\u001cTPC_MOD_SW_INTERNAL_AVM_REAR\u0010\u0082\u0080\u0010\u0012#\n\u001dTPC_MOD_SW_INTERNAL_AVM_RIGHT\u0010\u0083\u0080\u0010*B\n\u000eFdbusMessageId\u0012\u0017\n\u0013MSG_NOTIF_CTR_BEGIN\u0010\u0000\u0012\u0017\n\u0011MSG_", "NOTIF_CTR_END\u0010ÿÿ\u0003*Ñ\b\n\fFdbusEventId\u0012\u0011\n\rEVT_COM_BEGIN\u0010\u0000\u0012\u0014\n\u0010EVT_COM_MOD_INFO\u0010\u0001\u0012\u0014\n\u0010EVT_COM_BLD_INFO\u0010\u0002\u0012\u0014\n\u0010EVT_COM_VEH_INFO\u0010\u0003\u0012\u0014\n\u0010EVT_COM_MOD_STAT\u0010\u0004\u0012\u0013\n\u000fEVT_COM_PWR_MOD\u0010\u0005\u0012\u0017\n\u0013EVT_COM_HEALTH_STAT\u0010\u0006\u0012\u0014\n\u0010EVT_COM_DTC_INFO\u0010\u0007\u0012\u0015\n\u0011EVT_COM_LOG_LEVEL\u0010\b\u0012\u001d\n\u0019EVT_COM_SWITCH_EFUSE_INFO\u0010\t\u0012\u0011\n\u000bEVT_COM_END\u0010ÿÿ\u0003\u0012\u0017\n\u0011EVT_VAR_SVC_BEGIN\u0010\u0080\u0080\u0004\u0012\u0015\n\u000fEVT_VAR_SVC_END\u0010ÿÿ\u0007\u0012\u0017\n\u0011EVT_VEH_SVC_BEGIN\u0010\u0080\u0080\b\u0012\u0015\n\u000fEVT_VEH_SVC_END\u0010ÿÿ\u000b\u0012\u0018\n\u0012EVT_DISP_MGR_BEGIN", "\u0010\u0080\u0080\f\u0012 \n\u001aEVT_DISP_HUD_ROTATION_INFO\u0010\u0081\u0080\f\u0012\u0016\n\u0010EVT_DISP_MGR_END\u0010ÿÿ\u000f\u0012\u0013\n\rEVT_LCM_BEGIN\u0010\u0080\u0080\u0010\u0012 \n\u001aEVT_LCM_DISP_PWR_SPLY_STAT\u0010\u0081\u0080\u0010\u0012\u001d\n\u0017EVT_LCM_POWER_MODE_STAT\u0010\u0082\u0080\u0010\u0012\u001d\n\u0017EVT_LCM_HMI_MODE_EVENTS\u0010\u0083\u0080\u0010\u0012\u001f\n\u0019EVT_LCM_TIME_TRACK_EVENTS\u0010\u0084\u0080\u0010\u0012\u001f\n\u0019EVT_LCM_RTC_UPDATE_EVENTS\u0010\u0085\u0080\u0010\u0012 \n\u001aEVT_LCM_FREEZE_MODE_EVENTS\u0010\u0086\u0080\u0010\u0012\u0011\n\u000bEVT_LCM_END\u0010ÿÿ\u0013\u0012\u0019\n\u0013EVT_DIAG_SERV_BEGIN\u0010\u0080\u0080\u0014\u0012\u0017\n\u0011EVT_DIAG_SERV_END\u0010ÿÿ\u0017\u0012\u0019\n\u0013EVT_PSIS_SERV_BEGIN\u0010\u0080\u0080\u0018\u0012\u001f\n\u0019EVT_PSIS_SERV_CAR_C", "FG_MAP\u0010\u0081\u0080\u0018\u0012!\n\u001bEVT_PSIS_SERV_CAR_CFG_ARRAY\u0010\u0082\u0080\u0018\u0012$\n\u001eEVT_PSIS_SERV_PSIS_CUS_VERSION\u0010\u0083\u0080\u0018\u0012&\n EVT_PSIS_SERV_PSIS_BOSCH_VERSION\u0010\u0084\u0080\u0018\u0012!\n\u001bEVT_PSIS_SERV_PSIS_CAL_XDSN\u0010\u0085\u0080\u0018\u0012\"\n\u001cEVT_PSIS_SERV_PSIS_CAL_IHUID\u0010\u0086\u0080\u0018\u0012 \n\u001aEVT_PSIS_SERV_PSIS_CAL_VIN\u0010\u0087\u0080\u0018\u0012\u0017\n\u0011EVT_PSIS_SERV_END\u0010ÿÿ\u001b\u0012\u0017\n\u0011EVT_THERMAL_BEGIN\u0010\u0080\u0080\u001c\u0012\u0016\n\u0010EVT_THERMAL_INFO\u0010\u0081\u0080\u001c\u0012\u0015\n\u000fEVT_THERMAL_END\u0010ÿÿ\u001f*Ð\u0001\n\u0006Status\u0012\u0010\n\fSTAT_UNKNOWN\u0010\u0000\u0012\u0012\n\u000eSTAT_CONNECTED\u0010\u0001\u0012\u0015\n\u0011STAT_DISCONNECTED\u0010\u0002\u0012\u0013\n", "\u000fSTAT_IN_SERVICE\u0010\u0003\u0012\u001e\n\u001aSTAT_IN_SERVICE_ACTIVATION\u0010\u0004\u0012 \n\u001cSTAT_IN_SERVICE_DEACTIVATION\u0010\u0005\u0012\u0019\n\u0015STAT_IN_SERVICE_LIMIT\u0010\u0006\u0012\u0017\n\u0013STAT_OUT_OF_SERVICE\u0010\u0007*Ã\u0003\n\u000fNtfTempSensor_t\u0012\f\n\bNtf_AOSS\u0010\u0001\u0012\u000b\n\u0007Ntf_CPU\u0010\u0002\u0012\u000b\n\u0007Ntf_GPU\u0010\u0003\u0012\r\n\tNtf_CWLAN\u0010\u0004\u0012\r\n\tNtf_VIDEO\u0010\u0005\u0012\u0011\n\rNtf_INTER_DDR\u0010\u0006\u0012\u000e\n\nNtf_Q6_HVX\u0010\u0007\u0012\u000e\n\nNtf_CAMERA\u0010\b\u0012\r\n\tNtf_CMPSS\u0010\t\u0012\r\n\tNtf_MODEM\u0010\n\u0012\u000b\n\u0007Ntf_NPU\u0010\u000b\u0012\r\n\tNtf_TUNER\u0010\f\u0012\u000f\n\u000bNtf_DDR_SIP\u0010\r\u0012\u000b\n\u0007Ntf_UFS\u0010\u000e\u0012\u000f\n\u000bNtf_AMP1_TJ\u0010\u000f\u0012\u000f\n\u000bNtf_AMP2_TJ\u0010\u0010\u0012", "\u0010\n\fNtf_AMP_PERI\u0010\u0011\u0012\u000f\n\u000bNtf_BT_WIFI\u0010\u0012\u0012\f\n\bNtf_EMMC\u0010\u0013\u0012\r\n\tNtf_DIODE\u0010\u0014\u0012\u0010\n\fNtf_PHTM_MPQ\u0010\u0015\u0012\f\n\bNtf_GNSS\u0010\u0016\u0012\u000e\n\nNtf_DB_CAM\u0010\u0017\u0012\f\n\bNtf_GYRO\u0010\u0018\u0012\u0010\n\fNtf_PWR_MAIN\u0010\u0019\u0012\u000f\n\u000bNtf_EXT_DDR\u0010\u001a\u0012\u000b\n\u0007Ntf_HSM\u0010\u001b\u0012\u000f\n\u000bNtf_SW_TEST\u0010c*s\n\u000eNtfTempLevel_t\u0012\u000f\n\u000bLV_ORDINARY\u0010\u0000\u0012\f\n\bLV_LIGHT\u0010\u0001\u0012\u000e\n\nLV_WARNING\u0010\u0002\u0012\u000f\n\u000bLV_CRITICAL\u0010\u0003\u0012\u0010\n\fLV_EMERGENCY\u0010\u0004\u0012\u000f\n\u000bLV_SHUTDOWN\u0010\u0005*0\n\u000bPwrSplyStat\u0012\u0010\n\fPWR_SPLY_OFF\u0010\u0000\u0012\u000f\n\u000bPWR_SPLY_ON\u0010\u0001*\u009e\u0002\n\fNtfPowerMode\u0012\u000e\n\nNTF_PM_OFF\u0010\u0000\u0012\u0016\n\u0012NTF_PM", "_PRE_STARTUP\u0010\u0001\u0012\u0013\n\u000fNTF_PM_DEGRADED\u0010\u0003\u0012\u0012\n\u000eNTF_PM_STANDBY\u0010\u0004\u0012\u000e\n\nNTF_PM_STR\u0010\u0005\u0012\u0012\n\u000eNTF_PM_REFLASH\u0010\u0006\u0012\u0016\n\u0012NTF_PM_REMOTE_FOTA\u0010\u0007\u0012\u0010\n\fNTF_PM_SLEEP\u0010\b\u0012\u0019\n\u0015NTF_PM_STR_SUSPENDING\u0010\t\u0012\u0012\n\u000eNTF_PM_STARTUP\u0010\u0002\u0012\u0015\n\u0011NTF_PM_DISPLAY_ON\u0010\n\u0012\u0019\n\u0015NTF_PM_HALF_HOUR_MODE\u0010\u000b\u0012\u000e\n\nNTF_PM_TOD\u0010\f*Á\u0001\n\u000fNtfVoltageState\u0012&\n\"NTF_VOLTAGE_BEYOND_CRITICAL_HIGH_3\u0010\u0001\u0012\u001f\n\u001bNTF_VOLTAGE_CRITICAL_HIGH_2\u0010\u0002\u0012\u0016\n\u0012NTF_VOLTAGE_HIGH_1\u0010\u0003\u0012\u0016\n\u0012NTF_VOLTAGE_NORMAL\u0010\u0004\u0012\u0015\n\u0011NTF_VOLTA", "GE_LOW_1\u0010\u0005\u0012\u001e\n\u001aNTF_VOLTAGE_CRITICAL_LOW_2\u0010\u0006*¹\u0003\n\u0010NtfPowerSaveMode\u0012\u0014\n\u0010NTF_TRANPORT_OFF\u0010\u0000\u0012\u0013\n\u000fNTF_TRANPORT_ON\u0010\u0001\u0012\u0018\n\u0014NTF_POWER_STA_LEVEL1\u0010\u0002\u0012\u0018\n\u0014NTF_POWER_STA_LEVEL2\u0010\u0003\u0012\u001b\n\u0017NTF_POWER_STA_LEVEL_OFF\u0010\u0004\u0012\u001a\n\u0016NTF_POWER_DYN_LEVEL_ON\u0010\u0005\u0012\u001b\n\u0017NTF_POWER_DYN_LEVEL_OFF\u0010\u0006\u0012\u001a\n\u0016NTF_POWER_SYS_ERROR_ON\u0010\u0007\u0012\u001b\n\u0017NTF_POWER_SYS_ERROR_OFF\u0010\b\u0012\u001a\n\u0016NTF_POWER_THERMAL_HIGH\u0010\t\u0012\u001b\n\u0017NTF_TRANPORT_ON_TIMEOUT\u0010\n\u0012\u001f\n\u001bNTF_POWER_STA_LEVEL2_TIMOUT\u0010\u000b\u0012!\n\u001dNTF_P", "OWER_THERMAL_HIGH_TIMOUT\u0010\f\u0012\u001c\n\u0018NTF_POWER_VOLTAGE_TIMOUT\u0010\r\u0012\u001c\n\u0018NTF_POWER_THERMAL_NORMAL\u0010\u000e*Á\u0002\n\u000fNtfIpkPowerMode\u0012\u0019\n\u0015NTF_IPK_PREPARE_START\u0010\u0000\u0012\u001a\n\u0016NTF_IPK_PREPARE_IGNITE\u0010\u0001\u0012\u0015\n\u0011NTF_IPK_ANIMATION\u0010\u0002\u0012\u0015\n\u0011NTF_IPK_IGNITE_ON\u0010\u0003\u0012\u0016\n\u0012NTF_IPK_IGNITE_OFF\u0010\u0004\u0012\u0015\n\u0011NTF_IPK_PART_SHOW\u0010\u0005\u0012\u0019\n\u0015NTF_IPK_PREPARE_SLEEP\u0010\u0006\u0012\u0011\n\rNTF_IPK_SLEEP\u0010\u0007\u0012\u0019\n\u0015NTF_IPK_ANIMATION_NFC\u0010\b\u0012\u001b\n\u0017NTF_IPK_PREPARE_SLEEP_A\u0010\t\u0012\u001b\n\u0017NTF_IPK_PREPARE_SLEEP_B\u0010\n\u0012\u0017\n\u0013NTF_IPK_ACC", "_ON_MODE\u0010\u000b*6\n\rNTFDrvDoorSts\u0012\u0012\n\u000eNTF_DOOR_CLOSE\u0010\u0000\u0012\u0011\n\rNTF_DOOR_OPEN\u0010\u0001*8\n\rNTFWarningSts\u0012\u0013\n\u000fNTF_WARNING_OFF\u0010\u0000\u0012\u0012\n\u000eNTF_WARNING_ON\u0010\u0001*/\n\nNTFLockSts\u0012\u0010\n\fNTF_LOCK_OFF\u0010\u0000\u0012\u000f\n\u000bNTF_LOCK_ON\u0010\u0001*;\n\u000eNTFChargingsts\u0012\u0014\n\u0010NTF_CHARGING_OFF\u0010\u0000\u0012\u0013\n\u000fNTF_CHARGING_ON\u0010\u0001*W\n\fHealthStatus\u0012\u0015\n\u0011HLTH_STAT_UNKNOWN\u0010\u0000\u0012\u0015\n\u0011HLTH_STAT_SERVING\u0010\u0001\u0012\u0019\n\u0015HLTH_STAT_NOT_SERVING\u0010\u0002**\n\rDiagDtcResult\u0012\n\n\u0006DTC_OK\u0010\u0000\u0012\r\n\tDTC_ERROR\u0010\u0001*\u0089\u0001\n\bLogLevel\u0012\u0010\n\fLOG_SHUTDOWN\u0010\u0000\u0012\u0010", "\n\fLOG_CRITICAL\u0010\u0001\u0012\r\n\tLOG_ERROR\u0010\u0002\u0012\f\n\bLOG_WARN\u0010\u0003\u0012\u000e\n\nLOG_NOTICE\u0010\u0004\u0012\f\n\bLOG_INFO\u0010\u0005\u0012\u000e\n\nLOG_DEBUG1\u0010\u0006\u0012\u000e\n\nLOG_DEBUG2\u0010\u0007*f\n\u000bEfuseStatus\u0012\u0013\n\u000fEFUSE_NO_SWITCH\u0010\u0000\u0012\u0015\n\u0011EFUSE_NOT_FLASHED\u0010\u0001\u0012\u0011\n\rEFUSE_FLASHED\u0010\u0002\u0012\u0018\n\u0014EFUSE_UNKNOWN_STATUS\u0010\u0003*n\n\fHudRotateDir\u0012\u0018\n\u0014DISP_HUD_NO_ROTATION\u0010\u0000\u0012\u001f\n\u001bDISP_HUD_CLOCKWISE_ROTATION\u0010\u0001\u0012#\n\u001fDISP_HUD_ANTICLOCKWISE_ROTATION\u0010\u0002"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: bosch.platform.notif.ctr.pb.ComBoschCmPlatformNotifCtr.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                Descriptors.FileDescriptor unused = ComBoschCmPlatformNotifCtr.descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0], assigner);
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_bosch_platform_notif_ctr_pb_RtcUpdateEvents_descriptor = descriptor2;
        internal_static_bosch_platform_notif_ctr_pb_RtcUpdateEvents_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor2, new String[]{"Result"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        internal_static_bosch_platform_notif_ctr_pb_VehicleInfo_descriptor = descriptor3;
        internal_static_bosch_platform_notif_ctr_pb_VehicleInfo_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor3, new String[]{"Vin", "Mfr", "VehVer"});
        Descriptors.Descriptor descriptor4 = getDescriptor().getMessageTypes().get(2);
        internal_static_bosch_platform_notif_ctr_pb_ModuleStat_descriptor = descriptor4;
        internal_static_bosch_platform_notif_ctr_pb_ModuleStat_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor4, new String[]{"Name", "Domain", "Status", "Voltage", "Temperature", "TempLevel", "ElapsedTime", "CpuTemperature"});
        Descriptors.Descriptor descriptor5 = getDescriptor().getMessageTypes().get(3);
        internal_static_bosch_platform_notif_ctr_pb_ModuleInfo_descriptor = descriptor5;
        internal_static_bosch_platform_notif_ctr_pb_ModuleInfo_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor5, new String[]{"Name", "Domain", "Pn", "Sn", "FwVer", "HwVer", "SwVer", "NodeAddr", "HwId", "CompId", "ToolVer", "InstallerType", "SerVer", "SdbVer", "PropVer", "SupplierInfo", "LcdTemp", "PcbTemp"});
        Descriptors.Descriptor descriptor6 = getDescriptor().getMessageTypes().get(4);
        internal_static_bosch_platform_notif_ctr_pb_BuildInfo_descriptor = descriptor6;
        internal_static_bosch_platform_notif_ctr_pb_BuildInfo_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor6, new String[]{"BuildDate", "MainVerName", "MainVer", "BuildTarget", "BuildNumber", "BuildId", "JobName", "SocQnxVer", "SocAndroidVer", "SccAppVer", "SccBlVer", "ManifestRev", "SccBydAppVer", "SccBydBlVer", "SccVariantCode", "BootVer"});
        Descriptors.Descriptor descriptor7 = getDescriptor().getMessageTypes().get(5);
        internal_static_bosch_platform_notif_ctr_pb_DispPwrSplyStat_descriptor = descriptor7;
        internal_static_bosch_platform_notif_ctr_pb_DispPwrSplyStat_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor7, new String[]{"ClusterStat", "IviStat", "HvacStat", "HudStat"});
        Descriptors.Descriptor descriptor8 = getDescriptor().getMessageTypes().get(6);
        internal_static_bosch_platform_notif_ctr_pb_PwrModeStat_descriptor = descriptor8;
        internal_static_bosch_platform_notif_ctr_pb_PwrModeStat_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor8, new String[]{"PowerMode", "VoltageState", "SaveMode", "IpkPowerMode"});
        Descriptors.Descriptor descriptor9 = getDescriptor().getMessageTypes().get(7);
        internal_static_bosch_platform_notif_ctr_pb_LcmHmiModeEvents_descriptor = descriptor9;
        internal_static_bosch_platform_notif_ctr_pb_LcmHmiModeEvents_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor9, new String[]{"DoorOpened", "LcdWarning", "CarLocked", "ChargingInfo", "WarnInfo", "TtInfo"});
        Descriptors.Descriptor descriptor10 = getDescriptor().getMessageTypes().get(8);
        internal_static_bosch_platform_notif_ctr_pb_LcmTimingEvents_descriptor = descriptor10;
        internal_static_bosch_platform_notif_ctr_pb_LcmTimingEvents_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor10, new String[]{"SocTime", "PoweroffTime", "AndroidstartTime", "AcconTime", "AccoffTime", "EngonTime", "EngoffTime"});
        Descriptors.Descriptor descriptor11 = getDescriptor().getMessageTypes().get(9);
        internal_static_bosch_platform_notif_ctr_pb_CompntHealthStatus_descriptor = descriptor11;
        internal_static_bosch_platform_notif_ctr_pb_CompntHealthStatus_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor11, new String[]{"Status", "ProcessedMsg"});
        Descriptors.Descriptor descriptor12 = getDescriptor().getMessageTypes().get(10);
        internal_static_bosch_platform_notif_ctr_pb_DiagDtcInfo_descriptor = descriptor12;
        internal_static_bosch_platform_notif_ctr_pb_DiagDtcInfo_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor12, new String[]{"DtcNumber", "Result"});
        Descriptors.Descriptor descriptor13 = getDescriptor().getMessageTypes().get(11);
        internal_static_bosch_platform_notif_ctr_pb_MsgLogLevel_descriptor = descriptor13;
        internal_static_bosch_platform_notif_ctr_pb_MsgLogLevel_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor13, new String[]{"Level"});
        Descriptors.Descriptor descriptor14 = getDescriptor().getMessageTypes().get(12);
        internal_static_bosch_platform_notif_ctr_pb_switchEfuseInfo_descriptor = descriptor14;
        internal_static_bosch_platform_notif_ctr_pb_switchEfuseInfo_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor14, new String[]{"Efuseinfo"});
        Descriptors.Descriptor descriptor15 = getDescriptor().getMessageTypes().get(13);
        internal_static_bosch_platform_notif_ctr_pb_DispHudRotation_descriptor = descriptor15;
        internal_static_bosch_platform_notif_ctr_pb_DispHudRotation_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor15, new String[]{"Direction", "Level"});
        Descriptors.Descriptor descriptor16 = getDescriptor().getMessageTypes().get(14);
        internal_static_bosch_platform_notif_ctr_pb_PsisUpdateCarCfgInfo_descriptor = descriptor16;
        internal_static_bosch_platform_notif_ctr_pb_PsisUpdateCarCfgInfo_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor16, new String[]{"KeyName", "KeyValueChar"});
        Descriptors.Descriptor descriptor17 = getDescriptor().getMessageTypes().get(15);
        internal_static_bosch_platform_notif_ctr_pb_PsisUpdateCarCfgInfoMap_descriptor = descriptor17;
        internal_static_bosch_platform_notif_ctr_pb_PsisUpdateCarCfgInfoMap_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor17, new String[]{"CarCfgList"});
        Descriptors.Descriptor descriptor18 = getDescriptor().getMessageTypes().get(16);
        internal_static_bosch_platform_notif_ctr_pb_NtfThermalInfoItem_descriptor = descriptor18;
        internal_static_bosch_platform_notif_ctr_pb_NtfThermalInfoItem_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor18, new String[]{"NtftempId", "NtftempName", "NtftempValue", "NtftempLevel"});
        Descriptors.Descriptor descriptor19 = getDescriptor().getMessageTypes().get(17);
        internal_static_bosch_platform_notif_ctr_pb_NtfThermalInfoMsg_descriptor = descriptor19;
        internal_static_bosch_platform_notif_ctr_pb_NtfThermalInfoMsg_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor19, new String[]{"Items"});
    }
}
