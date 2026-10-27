package com.android.bluetooth;

import android.net.INetd;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class BluetoothMetricsProto {

    public interface A2DPSessionOrBuilder extends MessageLiteOrBuilder {
        long getAudioDurationMillis();

        int getBufferOverrunsMaxCount();

        int getBufferOverrunsTotal();

        float getBufferUnderrunsAverage();

        int getBufferUnderrunsCount();

        boolean getIsA2DpOffload();

        int getMediaTimerAvgMillis();

        int getMediaTimerMaxMillis();

        int getMediaTimerMinMillis();

        A2dpSourceCodec getSourceCodec();

        boolean hasAudioDurationMillis();

        boolean hasBufferOverrunsMaxCount();

        boolean hasBufferOverrunsTotal();

        boolean hasBufferUnderrunsAverage();

        boolean hasBufferUnderrunsCount();

        boolean hasIsA2DpOffload();

        boolean hasMediaTimerAvgMillis();

        boolean hasMediaTimerMaxMillis();

        boolean hasMediaTimerMinMillis();

        boolean hasSourceCodec();
    }

    public interface BluetoothLogOrBuilder extends MessageLiteOrBuilder {
        HeadsetProfileConnectionStats getHeadsetProfileConnectionStats(int i);

        int getHeadsetProfileConnectionStatsCount();

        List<HeadsetProfileConnectionStats> getHeadsetProfileConnectionStatsList();

        long getNumBluetoothSession();

        int getNumBondedDevices();

        long getNumPairEvent();

        long getNumScanEvent();

        long getNumWakeEvent();

        PairEvent getPairEvent(int i);

        int getPairEventCount();

        List<PairEvent> getPairEventList();

        ProfileConnectionStats getProfileConnectionStats(int i);

        int getProfileConnectionStatsCount();

        List<ProfileConnectionStats> getProfileConnectionStatsList();

        ScanEvent getScanEvent(int i);

        int getScanEventCount();

        List<ScanEvent> getScanEventList();

        BluetoothSession getSession(int i);

        int getSessionCount();

        List<BluetoothSession> getSessionList();

        WakeEvent getWakeEvent(int i);

        int getWakeEventCount();

        List<WakeEvent> getWakeEventList();

        boolean hasNumBluetoothSession();

        boolean hasNumBondedDevices();

        boolean hasNumPairEvent();

        boolean hasNumScanEvent();

        boolean hasNumWakeEvent();
    }

    public interface BluetoothSessionOrBuilder extends MessageLiteOrBuilder {
        A2DPSession getA2DpSession();

        BluetoothSession.ConnectionTechnologyType getConnectionTechnologyType();

        DeviceInfo getDeviceConnectedTo();

        @Deprecated
        String getDisconnectReason();

        @Deprecated
        ByteString getDisconnectReasonBytes();

        BluetoothSession.DisconnectReasonType getDisconnectReasonType();

        RFCommSession getRfcommSession();

        long getSessionDurationSec();

        boolean hasA2DpSession();

        boolean hasConnectionTechnologyType();

        boolean hasDeviceConnectedTo();

        @Deprecated
        boolean hasDisconnectReason();

        boolean hasDisconnectReasonType();

        boolean hasRfcommSession();

        boolean hasSessionDurationSec();
    }

    public interface DeviceInfoOrBuilder extends MessageLiteOrBuilder {
        int getDeviceClass();

        DeviceInfo.DeviceType getDeviceType();

        boolean hasDeviceClass();

        boolean hasDeviceType();
    }

    public interface HeadsetProfileConnectionStatsOrBuilder extends MessageLiteOrBuilder {
        HeadsetProfileType getHeadsetProfileType();

        int getNumTimesConnected();

        boolean hasHeadsetProfileType();

        boolean hasNumTimesConnected();
    }

    public interface PairEventOrBuilder extends MessageLiteOrBuilder {
        DeviceInfo getDevicePairedWith();

        int getDisconnectReason();

        long getEventTimeMillis();

        boolean hasDevicePairedWith();

        boolean hasDisconnectReason();

        boolean hasEventTimeMillis();
    }

    public interface ProfileConnectionStatsOrBuilder extends MessageLiteOrBuilder {
        int getNumTimesConnected();

        ProfileId getProfileId();

        boolean hasNumTimesConnected();

        boolean hasProfileId();
    }

    public interface RFCommSessionOrBuilder extends MessageLiteOrBuilder {
        int getRxBytes();

        int getTxBytes();

        boolean hasRxBytes();

        boolean hasTxBytes();
    }

    public interface ScanEventOrBuilder extends MessageLiteOrBuilder {
        long getEventTimeMillis();

        String getInitiator();

        ByteString getInitiatorBytes();

        int getNumberResults();

        ScanEvent.ScanEventType getScanEventType();

        ScanEvent.ScanTechnologyType getScanTechnologyType();

        boolean hasEventTimeMillis();

        boolean hasInitiator();

        boolean hasNumberResults();

        boolean hasScanEventType();

        boolean hasScanTechnologyType();
    }

    public interface WakeEventOrBuilder extends MessageLiteOrBuilder {
        long getEventTimeMillis();

        String getName();

        ByteString getNameBytes();

        String getRequestor();

        ByteString getRequestorBytes();

        WakeEvent.WakeEventType getWakeEventType();

        boolean hasEventTimeMillis();

        boolean hasName();

        boolean hasRequestor();

        boolean hasWakeEventType();
    }

    private BluetoothMetricsProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public enum A2dpSourceCodec implements Internal.EnumLite {
        A2DP_SOURCE_CODEC_UNKNOWN(0),
        A2DP_SOURCE_CODEC_SBC(1),
        A2DP_SOURCE_CODEC_AAC(2),
        A2DP_SOURCE_CODEC_APTX(3),
        A2DP_SOURCE_CODEC_APTX_HD(4),
        A2DP_SOURCE_CODEC_LDAC(5);

        public static final int A2DP_SOURCE_CODEC_AAC_VALUE = 2;
        public static final int A2DP_SOURCE_CODEC_APTX_HD_VALUE = 4;
        public static final int A2DP_SOURCE_CODEC_APTX_VALUE = 3;
        public static final int A2DP_SOURCE_CODEC_LDAC_VALUE = 5;
        public static final int A2DP_SOURCE_CODEC_SBC_VALUE = 1;
        public static final int A2DP_SOURCE_CODEC_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<A2dpSourceCodec> internalValueMap = new Internal.EnumLiteMap<A2dpSourceCodec>() { // from class: com.android.bluetooth.BluetoothMetricsProto.A2dpSourceCodec.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public A2dpSourceCodec findValueByNumber(int number) {
                return A2dpSourceCodec.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static A2dpSourceCodec valueOf(int value) {
            return forNumber(value);
        }

        public static A2dpSourceCodec forNumber(int value) {
            if (value == 0) {
                return A2DP_SOURCE_CODEC_UNKNOWN;
            }
            if (value == 1) {
                return A2DP_SOURCE_CODEC_SBC;
            }
            if (value == 2) {
                return A2DP_SOURCE_CODEC_AAC;
            }
            if (value == 3) {
                return A2DP_SOURCE_CODEC_APTX;
            }
            if (value == 4) {
                return A2DP_SOURCE_CODEC_APTX_HD;
            }
            if (value == 5) {
                return A2DP_SOURCE_CODEC_LDAC;
            }
            return null;
        }

        public static Internal.EnumLiteMap<A2dpSourceCodec> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return A2dpSourceCodecVerifier.INSTANCE;
        }

        private static final class A2dpSourceCodecVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new A2dpSourceCodecVerifier();

            private A2dpSourceCodecVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return A2dpSourceCodec.forNumber(number) != null;
            }
        }

        A2dpSourceCodec(int value) {
            this.value = value;
        }
    }

    public enum ProfileId implements Internal.EnumLite {
        PROFILE_UNKNOWN(0),
        HEADSET(1),
        A2DP(2),
        HEALTH(3),
        HID_HOST(4),
        PAN(5),
        PBAP(6),
        GATT(7),
        GATT_SERVER(8),
        MAP(9),
        SAP(10),
        A2DP_SINK(11),
        AVRCP_CONTROLLER(12),
        AVRCP(13),
        HEADSET_CLIENT(16),
        PBAP_CLIENT(17),
        MAP_CLIENT(18),
        HID_DEVICE(19),
        OPP(20),
        HEARING_AID(21);

        public static final int A2DP_SINK_VALUE = 11;
        public static final int A2DP_VALUE = 2;
        public static final int AVRCP_CONTROLLER_VALUE = 12;
        public static final int AVRCP_VALUE = 13;
        public static final int GATT_SERVER_VALUE = 8;
        public static final int GATT_VALUE = 7;
        public static final int HEADSET_CLIENT_VALUE = 16;
        public static final int HEADSET_VALUE = 1;
        public static final int HEALTH_VALUE = 3;
        public static final int HEARING_AID_VALUE = 21;
        public static final int HID_DEVICE_VALUE = 19;
        public static final int HID_HOST_VALUE = 4;
        public static final int MAP_CLIENT_VALUE = 18;
        public static final int MAP_VALUE = 9;
        public static final int OPP_VALUE = 20;
        public static final int PAN_VALUE = 5;
        public static final int PBAP_CLIENT_VALUE = 17;
        public static final int PBAP_VALUE = 6;
        public static final int PROFILE_UNKNOWN_VALUE = 0;
        public static final int SAP_VALUE = 10;
        private static final Internal.EnumLiteMap<ProfileId> internalValueMap = new Internal.EnumLiteMap<ProfileId>() { // from class: com.android.bluetooth.BluetoothMetricsProto.ProfileId.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ProfileId findValueByNumber(int number) {
                return ProfileId.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ProfileId valueOf(int value) {
            return forNumber(value);
        }

        public static ProfileId forNumber(int value) {
            switch (value) {
                case 0:
                    return PROFILE_UNKNOWN;
                case 1:
                    return HEADSET;
                case 2:
                    return A2DP;
                case 3:
                    return HEALTH;
                case 4:
                    return HID_HOST;
                case 5:
                    return PAN;
                case 6:
                    return PBAP;
                case 7:
                    return GATT;
                case 8:
                    return GATT_SERVER;
                case 9:
                    return MAP;
                case 10:
                    return SAP;
                case 11:
                    return A2DP_SINK;
                case 12:
                    return AVRCP_CONTROLLER;
                case 13:
                    return AVRCP;
                case 14:
                case 15:
                default:
                    return null;
                case 16:
                    return HEADSET_CLIENT;
                case 17:
                    return PBAP_CLIENT;
                case 18:
                    return MAP_CLIENT;
                case 19:
                    return HID_DEVICE;
                case 20:
                    return OPP;
                case 21:
                    return HEARING_AID;
            }
        }

        public static Internal.EnumLiteMap<ProfileId> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return ProfileIdVerifier.INSTANCE;
        }

        private static final class ProfileIdVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new ProfileIdVerifier();

            private ProfileIdVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return ProfileId.forNumber(number) != null;
            }
        }

        ProfileId(int value) {
            this.value = value;
        }
    }

    public enum HeadsetProfileType implements Internal.EnumLite {
        HEADSET_PROFILE_UNKNOWN(0),
        HSP(1),
        HFP(2);

        public static final int HEADSET_PROFILE_UNKNOWN_VALUE = 0;
        public static final int HFP_VALUE = 2;
        public static final int HSP_VALUE = 1;
        private static final Internal.EnumLiteMap<HeadsetProfileType> internalValueMap = new Internal.EnumLiteMap<HeadsetProfileType>() { // from class: com.android.bluetooth.BluetoothMetricsProto.HeadsetProfileType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public HeadsetProfileType findValueByNumber(int number) {
                return HeadsetProfileType.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static HeadsetProfileType valueOf(int value) {
            return forNumber(value);
        }

        public static HeadsetProfileType forNumber(int value) {
            if (value == 0) {
                return HEADSET_PROFILE_UNKNOWN;
            }
            if (value == 1) {
                return HSP;
            }
            if (value == 2) {
                return HFP;
            }
            return null;
        }

        public static Internal.EnumLiteMap<HeadsetProfileType> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return HeadsetProfileTypeVerifier.INSTANCE;
        }

        private static final class HeadsetProfileTypeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new HeadsetProfileTypeVerifier();

            private HeadsetProfileTypeVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int number) {
                return HeadsetProfileType.forNumber(number) != null;
            }
        }

        HeadsetProfileType(int value) {
            this.value = value;
        }
    }

    public static final class BluetoothLog extends GeneratedMessageLite<BluetoothLog, Builder> implements BluetoothLogOrBuilder {
        private static final BluetoothLog DEFAULT_INSTANCE;
        public static final int HEADSET_PROFILE_CONNECTION_STATS_FIELD_NUMBER = 11;
        public static final int NUM_BLUETOOTH_SESSION_FIELD_NUMBER = 6;
        public static final int NUM_BONDED_DEVICES_FIELD_NUMBER = 5;
        public static final int NUM_PAIR_EVENT_FIELD_NUMBER = 7;
        public static final int NUM_SCAN_EVENT_FIELD_NUMBER = 9;
        public static final int NUM_WAKE_EVENT_FIELD_NUMBER = 8;
        public static final int PAIR_EVENT_FIELD_NUMBER = 2;
        private static volatile Parser<BluetoothLog> PARSER = null;
        public static final int PROFILE_CONNECTION_STATS_FIELD_NUMBER = 10;
        public static final int SCAN_EVENT_FIELD_NUMBER = 4;
        public static final int SESSION_FIELD_NUMBER = 1;
        public static final int WAKE_EVENT_FIELD_NUMBER = 3;
        private int bitField0_;
        private long numBluetoothSession_;
        private int numBondedDevices_;
        private long numPairEvent_;
        private long numScanEvent_;
        private long numWakeEvent_;
        private Internal.ProtobufList<BluetoothSession> session_ = emptyProtobufList();
        private Internal.ProtobufList<PairEvent> pairEvent_ = emptyProtobufList();
        private Internal.ProtobufList<WakeEvent> wakeEvent_ = emptyProtobufList();
        private Internal.ProtobufList<ScanEvent> scanEvent_ = emptyProtobufList();
        private Internal.ProtobufList<ProfileConnectionStats> profileConnectionStats_ = emptyProtobufList();
        private Internal.ProtobufList<HeadsetProfileConnectionStats> headsetProfileConnectionStats_ = emptyProtobufList();

        private BluetoothLog() {
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public List<BluetoothSession> getSessionList() {
            return this.session_;
        }

        public List<? extends BluetoothSessionOrBuilder> getSessionOrBuilderList() {
            return this.session_;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public int getSessionCount() {
            return this.session_.size();
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public BluetoothSession getSession(int index) {
            return this.session_.get(index);
        }

        public BluetoothSessionOrBuilder getSessionOrBuilder(int index) {
            return this.session_.get(index);
        }

        private void ensureSessionIsMutable() {
            if (!this.session_.isModifiable()) {
                this.session_ = GeneratedMessageLite.mutableCopy(this.session_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSession(int index, BluetoothSession value) {
            if (value == null) {
                throw null;
            }
            ensureSessionIsMutable();
            this.session_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSession(int index, BluetoothSession.Builder builderForValue) {
            ensureSessionIsMutable();
            this.session_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSession(BluetoothSession value) {
            if (value == null) {
                throw null;
            }
            ensureSessionIsMutable();
            this.session_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSession(int index, BluetoothSession value) {
            if (value == null) {
                throw null;
            }
            ensureSessionIsMutable();
            this.session_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSession(BluetoothSession.Builder builderForValue) {
            ensureSessionIsMutable();
            this.session_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addSession(int index, BluetoothSession.Builder builderForValue) {
            ensureSessionIsMutable();
            this.session_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllSession(Iterable<? extends BluetoothSession> values) {
            ensureSessionIsMutable();
            AbstractMessageLite.addAll((Iterable) values, (List) this.session_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSession() {
            this.session_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeSession(int index) {
            ensureSessionIsMutable();
            this.session_.remove(index);
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public List<PairEvent> getPairEventList() {
            return this.pairEvent_;
        }

        public List<? extends PairEventOrBuilder> getPairEventOrBuilderList() {
            return this.pairEvent_;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public int getPairEventCount() {
            return this.pairEvent_.size();
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public PairEvent getPairEvent(int index) {
            return this.pairEvent_.get(index);
        }

        public PairEventOrBuilder getPairEventOrBuilder(int index) {
            return this.pairEvent_.get(index);
        }

        private void ensurePairEventIsMutable() {
            if (!this.pairEvent_.isModifiable()) {
                this.pairEvent_ = GeneratedMessageLite.mutableCopy(this.pairEvent_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPairEvent(int index, PairEvent value) {
            if (value == null) {
                throw null;
            }
            ensurePairEventIsMutable();
            this.pairEvent_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPairEvent(int index, PairEvent.Builder builderForValue) {
            ensurePairEventIsMutable();
            this.pairEvent_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addPairEvent(PairEvent value) {
            if (value == null) {
                throw null;
            }
            ensurePairEventIsMutable();
            this.pairEvent_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addPairEvent(int index, PairEvent value) {
            if (value == null) {
                throw null;
            }
            ensurePairEventIsMutable();
            this.pairEvent_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addPairEvent(PairEvent.Builder builderForValue) {
            ensurePairEventIsMutable();
            this.pairEvent_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addPairEvent(int index, PairEvent.Builder builderForValue) {
            ensurePairEventIsMutable();
            this.pairEvent_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllPairEvent(Iterable<? extends PairEvent> values) {
            ensurePairEventIsMutable();
            AbstractMessageLite.addAll((Iterable) values, (List) this.pairEvent_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPairEvent() {
            this.pairEvent_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removePairEvent(int index) {
            ensurePairEventIsMutable();
            this.pairEvent_.remove(index);
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public List<WakeEvent> getWakeEventList() {
            return this.wakeEvent_;
        }

        public List<? extends WakeEventOrBuilder> getWakeEventOrBuilderList() {
            return this.wakeEvent_;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public int getWakeEventCount() {
            return this.wakeEvent_.size();
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public WakeEvent getWakeEvent(int index) {
            return this.wakeEvent_.get(index);
        }

        public WakeEventOrBuilder getWakeEventOrBuilder(int index) {
            return this.wakeEvent_.get(index);
        }

        private void ensureWakeEventIsMutable() {
            if (!this.wakeEvent_.isModifiable()) {
                this.wakeEvent_ = GeneratedMessageLite.mutableCopy(this.wakeEvent_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setWakeEvent(int index, WakeEvent value) {
            if (value == null) {
                throw null;
            }
            ensureWakeEventIsMutable();
            this.wakeEvent_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setWakeEvent(int index, WakeEvent.Builder builderForValue) {
            ensureWakeEventIsMutable();
            this.wakeEvent_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addWakeEvent(WakeEvent value) {
            if (value == null) {
                throw null;
            }
            ensureWakeEventIsMutable();
            this.wakeEvent_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addWakeEvent(int index, WakeEvent value) {
            if (value == null) {
                throw null;
            }
            ensureWakeEventIsMutable();
            this.wakeEvent_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addWakeEvent(WakeEvent.Builder builderForValue) {
            ensureWakeEventIsMutable();
            this.wakeEvent_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addWakeEvent(int index, WakeEvent.Builder builderForValue) {
            ensureWakeEventIsMutable();
            this.wakeEvent_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllWakeEvent(Iterable<? extends WakeEvent> values) {
            ensureWakeEventIsMutable();
            AbstractMessageLite.addAll((Iterable) values, (List) this.wakeEvent_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearWakeEvent() {
            this.wakeEvent_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeWakeEvent(int index) {
            ensureWakeEventIsMutable();
            this.wakeEvent_.remove(index);
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public List<ScanEvent> getScanEventList() {
            return this.scanEvent_;
        }

        public List<? extends ScanEventOrBuilder> getScanEventOrBuilderList() {
            return this.scanEvent_;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public int getScanEventCount() {
            return this.scanEvent_.size();
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public ScanEvent getScanEvent(int index) {
            return this.scanEvent_.get(index);
        }

        public ScanEventOrBuilder getScanEventOrBuilder(int index) {
            return this.scanEvent_.get(index);
        }

        private void ensureScanEventIsMutable() {
            if (!this.scanEvent_.isModifiable()) {
                this.scanEvent_ = GeneratedMessageLite.mutableCopy(this.scanEvent_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setScanEvent(int index, ScanEvent value) {
            if (value == null) {
                throw null;
            }
            ensureScanEventIsMutable();
            this.scanEvent_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setScanEvent(int index, ScanEvent.Builder builderForValue) {
            ensureScanEventIsMutable();
            this.scanEvent_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addScanEvent(ScanEvent value) {
            if (value == null) {
                throw null;
            }
            ensureScanEventIsMutable();
            this.scanEvent_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addScanEvent(int index, ScanEvent value) {
            if (value == null) {
                throw null;
            }
            ensureScanEventIsMutable();
            this.scanEvent_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addScanEvent(ScanEvent.Builder builderForValue) {
            ensureScanEventIsMutable();
            this.scanEvent_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addScanEvent(int index, ScanEvent.Builder builderForValue) {
            ensureScanEventIsMutable();
            this.scanEvent_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllScanEvent(Iterable<? extends ScanEvent> values) {
            ensureScanEventIsMutable();
            AbstractMessageLite.addAll((Iterable) values, (List) this.scanEvent_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearScanEvent() {
            this.scanEvent_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeScanEvent(int index) {
            ensureScanEventIsMutable();
            this.scanEvent_.remove(index);
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public boolean hasNumBondedDevices() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public int getNumBondedDevices() {
            return this.numBondedDevices_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNumBondedDevices(int value) {
            this.bitField0_ |= 1;
            this.numBondedDevices_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNumBondedDevices() {
            this.bitField0_ &= -2;
            this.numBondedDevices_ = 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public boolean hasNumBluetoothSession() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public long getNumBluetoothSession() {
            return this.numBluetoothSession_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNumBluetoothSession(long value) {
            this.bitField0_ |= 2;
            this.numBluetoothSession_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNumBluetoothSession() {
            this.bitField0_ &= -3;
            this.numBluetoothSession_ = 0L;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public boolean hasNumPairEvent() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public long getNumPairEvent() {
            return this.numPairEvent_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNumPairEvent(long value) {
            this.bitField0_ |= 4;
            this.numPairEvent_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNumPairEvent() {
            this.bitField0_ &= -5;
            this.numPairEvent_ = 0L;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public boolean hasNumWakeEvent() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public long getNumWakeEvent() {
            return this.numWakeEvent_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNumWakeEvent(long value) {
            this.bitField0_ |= 8;
            this.numWakeEvent_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNumWakeEvent() {
            this.bitField0_ &= -9;
            this.numWakeEvent_ = 0L;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public boolean hasNumScanEvent() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public long getNumScanEvent() {
            return this.numScanEvent_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNumScanEvent(long value) {
            this.bitField0_ |= 16;
            this.numScanEvent_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNumScanEvent() {
            this.bitField0_ &= -17;
            this.numScanEvent_ = 0L;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public List<ProfileConnectionStats> getProfileConnectionStatsList() {
            return this.profileConnectionStats_;
        }

        public List<? extends ProfileConnectionStatsOrBuilder> getProfileConnectionStatsOrBuilderList() {
            return this.profileConnectionStats_;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public int getProfileConnectionStatsCount() {
            return this.profileConnectionStats_.size();
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public ProfileConnectionStats getProfileConnectionStats(int index) {
            return this.profileConnectionStats_.get(index);
        }

        public ProfileConnectionStatsOrBuilder getProfileConnectionStatsOrBuilder(int index) {
            return this.profileConnectionStats_.get(index);
        }

        private void ensureProfileConnectionStatsIsMutable() {
            if (!this.profileConnectionStats_.isModifiable()) {
                this.profileConnectionStats_ = GeneratedMessageLite.mutableCopy(this.profileConnectionStats_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProfileConnectionStats(int index, ProfileConnectionStats value) {
            if (value == null) {
                throw null;
            }
            ensureProfileConnectionStatsIsMutable();
            this.profileConnectionStats_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProfileConnectionStats(int index, ProfileConnectionStats.Builder builderForValue) {
            ensureProfileConnectionStatsIsMutable();
            this.profileConnectionStats_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addProfileConnectionStats(ProfileConnectionStats value) {
            if (value == null) {
                throw null;
            }
            ensureProfileConnectionStatsIsMutable();
            this.profileConnectionStats_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addProfileConnectionStats(int index, ProfileConnectionStats value) {
            if (value == null) {
                throw null;
            }
            ensureProfileConnectionStatsIsMutable();
            this.profileConnectionStats_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addProfileConnectionStats(ProfileConnectionStats.Builder builderForValue) {
            ensureProfileConnectionStatsIsMutable();
            this.profileConnectionStats_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addProfileConnectionStats(int index, ProfileConnectionStats.Builder builderForValue) {
            ensureProfileConnectionStatsIsMutable();
            this.profileConnectionStats_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllProfileConnectionStats(Iterable<? extends ProfileConnectionStats> values) {
            ensureProfileConnectionStatsIsMutable();
            AbstractMessageLite.addAll((Iterable) values, (List) this.profileConnectionStats_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearProfileConnectionStats() {
            this.profileConnectionStats_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeProfileConnectionStats(int index) {
            ensureProfileConnectionStatsIsMutable();
            this.profileConnectionStats_.remove(index);
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public List<HeadsetProfileConnectionStats> getHeadsetProfileConnectionStatsList() {
            return this.headsetProfileConnectionStats_;
        }

        public List<? extends HeadsetProfileConnectionStatsOrBuilder> getHeadsetProfileConnectionStatsOrBuilderList() {
            return this.headsetProfileConnectionStats_;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public int getHeadsetProfileConnectionStatsCount() {
            return this.headsetProfileConnectionStats_.size();
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
        public HeadsetProfileConnectionStats getHeadsetProfileConnectionStats(int index) {
            return this.headsetProfileConnectionStats_.get(index);
        }

        public HeadsetProfileConnectionStatsOrBuilder getHeadsetProfileConnectionStatsOrBuilder(int index) {
            return this.headsetProfileConnectionStats_.get(index);
        }

        private void ensureHeadsetProfileConnectionStatsIsMutable() {
            if (!this.headsetProfileConnectionStats_.isModifiable()) {
                this.headsetProfileConnectionStats_ = GeneratedMessageLite.mutableCopy(this.headsetProfileConnectionStats_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeadsetProfileConnectionStats(int index, HeadsetProfileConnectionStats value) {
            if (value == null) {
                throw null;
            }
            ensureHeadsetProfileConnectionStatsIsMutable();
            this.headsetProfileConnectionStats_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeadsetProfileConnectionStats(int index, HeadsetProfileConnectionStats.Builder builderForValue) {
            ensureHeadsetProfileConnectionStatsIsMutable();
            this.headsetProfileConnectionStats_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addHeadsetProfileConnectionStats(HeadsetProfileConnectionStats value) {
            if (value == null) {
                throw null;
            }
            ensureHeadsetProfileConnectionStatsIsMutable();
            this.headsetProfileConnectionStats_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addHeadsetProfileConnectionStats(int index, HeadsetProfileConnectionStats value) {
            if (value == null) {
                throw null;
            }
            ensureHeadsetProfileConnectionStatsIsMutable();
            this.headsetProfileConnectionStats_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addHeadsetProfileConnectionStats(HeadsetProfileConnectionStats.Builder builderForValue) {
            ensureHeadsetProfileConnectionStatsIsMutable();
            this.headsetProfileConnectionStats_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addHeadsetProfileConnectionStats(int index, HeadsetProfileConnectionStats.Builder builderForValue) {
            ensureHeadsetProfileConnectionStatsIsMutable();
            this.headsetProfileConnectionStats_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllHeadsetProfileConnectionStats(Iterable<? extends HeadsetProfileConnectionStats> values) {
            ensureHeadsetProfileConnectionStatsIsMutable();
            AbstractMessageLite.addAll((Iterable) values, (List) this.headsetProfileConnectionStats_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHeadsetProfileConnectionStats() {
            this.headsetProfileConnectionStats_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeHeadsetProfileConnectionStats(int index) {
            ensureHeadsetProfileConnectionStatsIsMutable();
            this.headsetProfileConnectionStats_.remove(index);
        }

        public static BluetoothLog parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (BluetoothLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BluetoothLog parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BluetoothLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BluetoothLog parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (BluetoothLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BluetoothLog parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BluetoothLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BluetoothLog parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (BluetoothLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BluetoothLog parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BluetoothLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BluetoothLog parseFrom(InputStream input) throws IOException {
            return (BluetoothLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BluetoothLog parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BluetoothLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BluetoothLog parseDelimitedFrom(InputStream input) throws IOException {
            return (BluetoothLog) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static BluetoothLog parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BluetoothLog) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BluetoothLog parseFrom(CodedInputStream input) throws IOException {
            return (BluetoothLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BluetoothLog parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BluetoothLog) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(BluetoothLog prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<BluetoothLog, Builder> implements BluetoothLogOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(BluetoothLog.DEFAULT_INSTANCE);
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public List<BluetoothSession> getSessionList() {
                return Collections.unmodifiableList(((BluetoothLog) this.instance).getSessionList());
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public int getSessionCount() {
                return ((BluetoothLog) this.instance).getSessionCount();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public BluetoothSession getSession(int index) {
                return ((BluetoothLog) this.instance).getSession(index);
            }

            public Builder setSession(int index, BluetoothSession value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).setSession(index, value);
                return this;
            }

            public Builder setSession(int index, BluetoothSession.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothLog) this.instance).setSession(index, builderForValue);
                return this;
            }

            public Builder addSession(BluetoothSession value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addSession(value);
                return this;
            }

            public Builder addSession(int index, BluetoothSession value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addSession(index, value);
                return this;
            }

            public Builder addSession(BluetoothSession.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addSession(builderForValue);
                return this;
            }

            public Builder addSession(int index, BluetoothSession.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addSession(index, builderForValue);
                return this;
            }

            public Builder addAllSession(Iterable<? extends BluetoothSession> values) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addAllSession(values);
                return this;
            }

            public Builder clearSession() {
                copyOnWrite();
                ((BluetoothLog) this.instance).clearSession();
                return this;
            }

            public Builder removeSession(int index) {
                copyOnWrite();
                ((BluetoothLog) this.instance).removeSession(index);
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public List<PairEvent> getPairEventList() {
                return Collections.unmodifiableList(((BluetoothLog) this.instance).getPairEventList());
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public int getPairEventCount() {
                return ((BluetoothLog) this.instance).getPairEventCount();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public PairEvent getPairEvent(int index) {
                return ((BluetoothLog) this.instance).getPairEvent(index);
            }

            public Builder setPairEvent(int index, PairEvent value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).setPairEvent(index, value);
                return this;
            }

            public Builder setPairEvent(int index, PairEvent.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothLog) this.instance).setPairEvent(index, builderForValue);
                return this;
            }

            public Builder addPairEvent(PairEvent value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addPairEvent(value);
                return this;
            }

            public Builder addPairEvent(int index, PairEvent value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addPairEvent(index, value);
                return this;
            }

            public Builder addPairEvent(PairEvent.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addPairEvent(builderForValue);
                return this;
            }

            public Builder addPairEvent(int index, PairEvent.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addPairEvent(index, builderForValue);
                return this;
            }

            public Builder addAllPairEvent(Iterable<? extends PairEvent> values) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addAllPairEvent(values);
                return this;
            }

            public Builder clearPairEvent() {
                copyOnWrite();
                ((BluetoothLog) this.instance).clearPairEvent();
                return this;
            }

            public Builder removePairEvent(int index) {
                copyOnWrite();
                ((BluetoothLog) this.instance).removePairEvent(index);
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public List<WakeEvent> getWakeEventList() {
                return Collections.unmodifiableList(((BluetoothLog) this.instance).getWakeEventList());
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public int getWakeEventCount() {
                return ((BluetoothLog) this.instance).getWakeEventCount();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public WakeEvent getWakeEvent(int index) {
                return ((BluetoothLog) this.instance).getWakeEvent(index);
            }

            public Builder setWakeEvent(int index, WakeEvent value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).setWakeEvent(index, value);
                return this;
            }

            public Builder setWakeEvent(int index, WakeEvent.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothLog) this.instance).setWakeEvent(index, builderForValue);
                return this;
            }

            public Builder addWakeEvent(WakeEvent value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addWakeEvent(value);
                return this;
            }

            public Builder addWakeEvent(int index, WakeEvent value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addWakeEvent(index, value);
                return this;
            }

            public Builder addWakeEvent(WakeEvent.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addWakeEvent(builderForValue);
                return this;
            }

            public Builder addWakeEvent(int index, WakeEvent.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addWakeEvent(index, builderForValue);
                return this;
            }

            public Builder addAllWakeEvent(Iterable<? extends WakeEvent> values) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addAllWakeEvent(values);
                return this;
            }

            public Builder clearWakeEvent() {
                copyOnWrite();
                ((BluetoothLog) this.instance).clearWakeEvent();
                return this;
            }

            public Builder removeWakeEvent(int index) {
                copyOnWrite();
                ((BluetoothLog) this.instance).removeWakeEvent(index);
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public List<ScanEvent> getScanEventList() {
                return Collections.unmodifiableList(((BluetoothLog) this.instance).getScanEventList());
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public int getScanEventCount() {
                return ((BluetoothLog) this.instance).getScanEventCount();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public ScanEvent getScanEvent(int index) {
                return ((BluetoothLog) this.instance).getScanEvent(index);
            }

            public Builder setScanEvent(int index, ScanEvent value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).setScanEvent(index, value);
                return this;
            }

            public Builder setScanEvent(int index, ScanEvent.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothLog) this.instance).setScanEvent(index, builderForValue);
                return this;
            }

            public Builder addScanEvent(ScanEvent value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addScanEvent(value);
                return this;
            }

            public Builder addScanEvent(int index, ScanEvent value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addScanEvent(index, value);
                return this;
            }

            public Builder addScanEvent(ScanEvent.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addScanEvent(builderForValue);
                return this;
            }

            public Builder addScanEvent(int index, ScanEvent.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addScanEvent(index, builderForValue);
                return this;
            }

            public Builder addAllScanEvent(Iterable<? extends ScanEvent> values) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addAllScanEvent(values);
                return this;
            }

            public Builder clearScanEvent() {
                copyOnWrite();
                ((BluetoothLog) this.instance).clearScanEvent();
                return this;
            }

            public Builder removeScanEvent(int index) {
                copyOnWrite();
                ((BluetoothLog) this.instance).removeScanEvent(index);
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public boolean hasNumBondedDevices() {
                return ((BluetoothLog) this.instance).hasNumBondedDevices();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public int getNumBondedDevices() {
                return ((BluetoothLog) this.instance).getNumBondedDevices();
            }

            public Builder setNumBondedDevices(int value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).setNumBondedDevices(value);
                return this;
            }

            public Builder clearNumBondedDevices() {
                copyOnWrite();
                ((BluetoothLog) this.instance).clearNumBondedDevices();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public boolean hasNumBluetoothSession() {
                return ((BluetoothLog) this.instance).hasNumBluetoothSession();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public long getNumBluetoothSession() {
                return ((BluetoothLog) this.instance).getNumBluetoothSession();
            }

            public Builder setNumBluetoothSession(long value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).setNumBluetoothSession(value);
                return this;
            }

            public Builder clearNumBluetoothSession() {
                copyOnWrite();
                ((BluetoothLog) this.instance).clearNumBluetoothSession();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public boolean hasNumPairEvent() {
                return ((BluetoothLog) this.instance).hasNumPairEvent();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public long getNumPairEvent() {
                return ((BluetoothLog) this.instance).getNumPairEvent();
            }

            public Builder setNumPairEvent(long value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).setNumPairEvent(value);
                return this;
            }

            public Builder clearNumPairEvent() {
                copyOnWrite();
                ((BluetoothLog) this.instance).clearNumPairEvent();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public boolean hasNumWakeEvent() {
                return ((BluetoothLog) this.instance).hasNumWakeEvent();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public long getNumWakeEvent() {
                return ((BluetoothLog) this.instance).getNumWakeEvent();
            }

            public Builder setNumWakeEvent(long value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).setNumWakeEvent(value);
                return this;
            }

            public Builder clearNumWakeEvent() {
                copyOnWrite();
                ((BluetoothLog) this.instance).clearNumWakeEvent();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public boolean hasNumScanEvent() {
                return ((BluetoothLog) this.instance).hasNumScanEvent();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public long getNumScanEvent() {
                return ((BluetoothLog) this.instance).getNumScanEvent();
            }

            public Builder setNumScanEvent(long value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).setNumScanEvent(value);
                return this;
            }

            public Builder clearNumScanEvent() {
                copyOnWrite();
                ((BluetoothLog) this.instance).clearNumScanEvent();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public List<ProfileConnectionStats> getProfileConnectionStatsList() {
                return Collections.unmodifiableList(((BluetoothLog) this.instance).getProfileConnectionStatsList());
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public int getProfileConnectionStatsCount() {
                return ((BluetoothLog) this.instance).getProfileConnectionStatsCount();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public ProfileConnectionStats getProfileConnectionStats(int index) {
                return ((BluetoothLog) this.instance).getProfileConnectionStats(index);
            }

            public Builder setProfileConnectionStats(int index, ProfileConnectionStats value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).setProfileConnectionStats(index, value);
                return this;
            }

            public Builder setProfileConnectionStats(int index, ProfileConnectionStats.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothLog) this.instance).setProfileConnectionStats(index, builderForValue);
                return this;
            }

            public Builder addProfileConnectionStats(ProfileConnectionStats value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addProfileConnectionStats(value);
                return this;
            }

            public Builder addProfileConnectionStats(int index, ProfileConnectionStats value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addProfileConnectionStats(index, value);
                return this;
            }

            public Builder addProfileConnectionStats(ProfileConnectionStats.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addProfileConnectionStats(builderForValue);
                return this;
            }

            public Builder addProfileConnectionStats(int index, ProfileConnectionStats.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addProfileConnectionStats(index, builderForValue);
                return this;
            }

            public Builder addAllProfileConnectionStats(Iterable<? extends ProfileConnectionStats> values) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addAllProfileConnectionStats(values);
                return this;
            }

            public Builder clearProfileConnectionStats() {
                copyOnWrite();
                ((BluetoothLog) this.instance).clearProfileConnectionStats();
                return this;
            }

            public Builder removeProfileConnectionStats(int index) {
                copyOnWrite();
                ((BluetoothLog) this.instance).removeProfileConnectionStats(index);
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public List<HeadsetProfileConnectionStats> getHeadsetProfileConnectionStatsList() {
                return Collections.unmodifiableList(((BluetoothLog) this.instance).getHeadsetProfileConnectionStatsList());
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public int getHeadsetProfileConnectionStatsCount() {
                return ((BluetoothLog) this.instance).getHeadsetProfileConnectionStatsCount();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothLogOrBuilder
            public HeadsetProfileConnectionStats getHeadsetProfileConnectionStats(int index) {
                return ((BluetoothLog) this.instance).getHeadsetProfileConnectionStats(index);
            }

            public Builder setHeadsetProfileConnectionStats(int index, HeadsetProfileConnectionStats value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).setHeadsetProfileConnectionStats(index, value);
                return this;
            }

            public Builder setHeadsetProfileConnectionStats(int index, HeadsetProfileConnectionStats.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothLog) this.instance).setHeadsetProfileConnectionStats(index, builderForValue);
                return this;
            }

            public Builder addHeadsetProfileConnectionStats(HeadsetProfileConnectionStats value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addHeadsetProfileConnectionStats(value);
                return this;
            }

            public Builder addHeadsetProfileConnectionStats(int index, HeadsetProfileConnectionStats value) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addHeadsetProfileConnectionStats(index, value);
                return this;
            }

            public Builder addHeadsetProfileConnectionStats(HeadsetProfileConnectionStats.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addHeadsetProfileConnectionStats(builderForValue);
                return this;
            }

            public Builder addHeadsetProfileConnectionStats(int index, HeadsetProfileConnectionStats.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addHeadsetProfileConnectionStats(index, builderForValue);
                return this;
            }

            public Builder addAllHeadsetProfileConnectionStats(Iterable<? extends HeadsetProfileConnectionStats> values) {
                copyOnWrite();
                ((BluetoothLog) this.instance).addAllHeadsetProfileConnectionStats(values);
                return this;
            }

            public Builder clearHeadsetProfileConnectionStats() {
                copyOnWrite();
                ((BluetoothLog) this.instance).clearHeadsetProfileConnectionStats();
                return this;
            }

            public Builder removeHeadsetProfileConnectionStats(int index) {
                copyOnWrite();
                ((BluetoothLog) this.instance).removeHeadsetProfileConnectionStats(index);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new BluetoothLog();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "session_", BluetoothSession.class, "pairEvent_", PairEvent.class, "wakeEvent_", WakeEvent.class, "scanEvent_", ScanEvent.class, "numBondedDevices_", "numBluetoothSession_", "numPairEvent_", "numWakeEvent_", "numScanEvent_", "profileConnectionStats_", ProfileConnectionStats.class, "headsetProfileConnectionStats_", HeadsetProfileConnectionStats.class};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0006\u0000\u0001\u001b\u0002\u001b\u0003\u001b\u0004\u001b\u0005\u0004\u0000\u0006\u0002\u0001\u0007\u0002\u0002\b\u0002\u0003\t\u0002\u0004\n\u001b\u000b\u001b", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<BluetoothLog> parser = PARSER;
                    if (parser == null) {
                        synchronized (BluetoothLog.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            BluetoothLog defaultInstance = new BluetoothLog();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(BluetoothLog.class, defaultInstance);
        }

        public static BluetoothLog getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BluetoothLog> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: com.android.bluetooth.BluetoothMetricsProto$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public static final class DeviceInfo extends GeneratedMessageLite<DeviceInfo, Builder> implements DeviceInfoOrBuilder {
        private static final DeviceInfo DEFAULT_INSTANCE;
        public static final int DEVICE_CLASS_FIELD_NUMBER = 1;
        public static final int DEVICE_TYPE_FIELD_NUMBER = 2;
        private static volatile Parser<DeviceInfo> PARSER;
        private int bitField0_;
        private int deviceClass_;
        private int deviceType_;

        private DeviceInfo() {
        }

        public enum DeviceType implements Internal.EnumLite {
            DEVICE_TYPE_UNKNOWN(0),
            DEVICE_TYPE_BREDR(1),
            DEVICE_TYPE_LE(2),
            DEVICE_TYPE_DUMO(3);

            public static final int DEVICE_TYPE_BREDR_VALUE = 1;
            public static final int DEVICE_TYPE_DUMO_VALUE = 3;
            public static final int DEVICE_TYPE_LE_VALUE = 2;
            public static final int DEVICE_TYPE_UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<DeviceType> internalValueMap = new Internal.EnumLiteMap<DeviceType>() { // from class: com.android.bluetooth.BluetoothMetricsProto.DeviceInfo.DeviceType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public DeviceType findValueByNumber(int number) {
                    return DeviceType.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static DeviceType valueOf(int value) {
                return forNumber(value);
            }

            public static DeviceType forNumber(int value) {
                if (value == 0) {
                    return DEVICE_TYPE_UNKNOWN;
                }
                if (value == 1) {
                    return DEVICE_TYPE_BREDR;
                }
                if (value == 2) {
                    return DEVICE_TYPE_LE;
                }
                if (value == 3) {
                    return DEVICE_TYPE_DUMO;
                }
                return null;
            }

            public static Internal.EnumLiteMap<DeviceType> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return DeviceTypeVerifier.INSTANCE;
            }

            private static final class DeviceTypeVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = new DeviceTypeVerifier();

                private DeviceTypeVerifier() {
                }

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int number) {
                    return DeviceType.forNumber(number) != null;
                }
            }

            DeviceType(int value) {
                this.value = value;
            }
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.DeviceInfoOrBuilder
        public boolean hasDeviceClass() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.DeviceInfoOrBuilder
        public int getDeviceClass() {
            return this.deviceClass_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceClass(int value) {
            this.bitField0_ |= 1;
            this.deviceClass_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceClass() {
            this.bitField0_ &= -2;
            this.deviceClass_ = 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.DeviceInfoOrBuilder
        public boolean hasDeviceType() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.DeviceInfoOrBuilder
        public DeviceType getDeviceType() {
            DeviceType result = DeviceType.forNumber(this.deviceType_);
            return result == null ? DeviceType.DEVICE_TYPE_UNKNOWN : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceType(DeviceType value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.deviceType_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceType() {
            this.bitField0_ &= -3;
            this.deviceType_ = 0;
        }

        public static DeviceInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (DeviceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DeviceInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DeviceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DeviceInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (DeviceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DeviceInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DeviceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DeviceInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (DeviceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DeviceInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DeviceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DeviceInfo parseFrom(InputStream input) throws IOException {
            return (DeviceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DeviceInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DeviceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DeviceInfo parseDelimitedFrom(InputStream input) throws IOException {
            return (DeviceInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static DeviceInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DeviceInfo) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DeviceInfo parseFrom(CodedInputStream input) throws IOException {
            return (DeviceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DeviceInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DeviceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(DeviceInfo prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<DeviceInfo, Builder> implements DeviceInfoOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(DeviceInfo.DEFAULT_INSTANCE);
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.DeviceInfoOrBuilder
            public boolean hasDeviceClass() {
                return ((DeviceInfo) this.instance).hasDeviceClass();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.DeviceInfoOrBuilder
            public int getDeviceClass() {
                return ((DeviceInfo) this.instance).getDeviceClass();
            }

            public Builder setDeviceClass(int value) {
                copyOnWrite();
                ((DeviceInfo) this.instance).setDeviceClass(value);
                return this;
            }

            public Builder clearDeviceClass() {
                copyOnWrite();
                ((DeviceInfo) this.instance).clearDeviceClass();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.DeviceInfoOrBuilder
            public boolean hasDeviceType() {
                return ((DeviceInfo) this.instance).hasDeviceType();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.DeviceInfoOrBuilder
            public DeviceType getDeviceType() {
                return ((DeviceInfo) this.instance).getDeviceType();
            }

            public Builder setDeviceType(DeviceType value) {
                copyOnWrite();
                ((DeviceInfo) this.instance).setDeviceType(value);
                return this;
            }

            public Builder clearDeviceType() {
                copyOnWrite();
                ((DeviceInfo) this.instance).clearDeviceType();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new DeviceInfo();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "deviceClass_", "deviceType_", DeviceType.internalGetVerifier()};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0000\u0002\f\u0001", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<DeviceInfo> parser = PARSER;
                    if (parser == null) {
                        synchronized (DeviceInfo.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            DeviceInfo defaultInstance = new DeviceInfo();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(DeviceInfo.class, defaultInstance);
        }

        public static DeviceInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DeviceInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class BluetoothSession extends GeneratedMessageLite<BluetoothSession, Builder> implements BluetoothSessionOrBuilder {
        public static final int A2DP_SESSION_FIELD_NUMBER = 7;
        public static final int CONNECTION_TECHNOLOGY_TYPE_FIELD_NUMBER = 3;
        private static final BluetoothSession DEFAULT_INSTANCE;
        public static final int DEVICE_CONNECTED_TO_FIELD_NUMBER = 5;
        public static final int DISCONNECT_REASON_FIELD_NUMBER = 4;
        public static final int DISCONNECT_REASON_TYPE_FIELD_NUMBER = 8;
        private static volatile Parser<BluetoothSession> PARSER = null;
        public static final int RFCOMM_SESSION_FIELD_NUMBER = 6;
        public static final int SESSION_DURATION_SEC_FIELD_NUMBER = 2;
        private A2DPSession a2DpSession_;
        private int bitField0_;
        private int connectionTechnologyType_;
        private DeviceInfo deviceConnectedTo_;
        private int disconnectReasonType_;
        private String disconnectReason_ = INetd.NEXTHOP_NONE;
        private RFCommSession rfcommSession_;
        private long sessionDurationSec_;

        private BluetoothSession() {
        }

        public enum ConnectionTechnologyType implements Internal.EnumLite {
            CONNECTION_TECHNOLOGY_TYPE_UNKNOWN(0),
            CONNECTION_TECHNOLOGY_TYPE_LE(1),
            CONNECTION_TECHNOLOGY_TYPE_BREDR(2);

            public static final int CONNECTION_TECHNOLOGY_TYPE_BREDR_VALUE = 2;
            public static final int CONNECTION_TECHNOLOGY_TYPE_LE_VALUE = 1;
            public static final int CONNECTION_TECHNOLOGY_TYPE_UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<ConnectionTechnologyType> internalValueMap = new Internal.EnumLiteMap<ConnectionTechnologyType>() { // from class: com.android.bluetooth.BluetoothMetricsProto.BluetoothSession.ConnectionTechnologyType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public ConnectionTechnologyType findValueByNumber(int number) {
                    return ConnectionTechnologyType.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static ConnectionTechnologyType valueOf(int value) {
                return forNumber(value);
            }

            public static ConnectionTechnologyType forNumber(int value) {
                if (value == 0) {
                    return CONNECTION_TECHNOLOGY_TYPE_UNKNOWN;
                }
                if (value == 1) {
                    return CONNECTION_TECHNOLOGY_TYPE_LE;
                }
                if (value == 2) {
                    return CONNECTION_TECHNOLOGY_TYPE_BREDR;
                }
                return null;
            }

            public static Internal.EnumLiteMap<ConnectionTechnologyType> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return ConnectionTechnologyTypeVerifier.INSTANCE;
            }

            private static final class ConnectionTechnologyTypeVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = new ConnectionTechnologyTypeVerifier();

                private ConnectionTechnologyTypeVerifier() {
                }

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int number) {
                    return ConnectionTechnologyType.forNumber(number) != null;
                }
            }

            ConnectionTechnologyType(int value) {
                this.value = value;
            }
        }

        public enum DisconnectReasonType implements Internal.EnumLite {
            UNKNOWN(0),
            METRICS_DUMP(1),
            NEXT_START_WITHOUT_END_PREVIOUS(2);

            public static final int METRICS_DUMP_VALUE = 1;
            public static final int NEXT_START_WITHOUT_END_PREVIOUS_VALUE = 2;
            public static final int UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<DisconnectReasonType> internalValueMap = new Internal.EnumLiteMap<DisconnectReasonType>() { // from class: com.android.bluetooth.BluetoothMetricsProto.BluetoothSession.DisconnectReasonType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public DisconnectReasonType findValueByNumber(int number) {
                    return DisconnectReasonType.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static DisconnectReasonType valueOf(int value) {
                return forNumber(value);
            }

            public static DisconnectReasonType forNumber(int value) {
                if (value == 0) {
                    return UNKNOWN;
                }
                if (value == 1) {
                    return METRICS_DUMP;
                }
                if (value == 2) {
                    return NEXT_START_WITHOUT_END_PREVIOUS;
                }
                return null;
            }

            public static Internal.EnumLiteMap<DisconnectReasonType> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return DisconnectReasonTypeVerifier.INSTANCE;
            }

            private static final class DisconnectReasonTypeVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = new DisconnectReasonTypeVerifier();

                private DisconnectReasonTypeVerifier() {
                }

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int number) {
                    return DisconnectReasonType.forNumber(number) != null;
                }
            }

            DisconnectReasonType(int value) {
                this.value = value;
            }
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
        public boolean hasSessionDurationSec() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
        public long getSessionDurationSec() {
            return this.sessionDurationSec_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSessionDurationSec(long value) {
            this.bitField0_ |= 1;
            this.sessionDurationSec_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSessionDurationSec() {
            this.bitField0_ &= -2;
            this.sessionDurationSec_ = 0L;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
        public boolean hasConnectionTechnologyType() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
        public ConnectionTechnologyType getConnectionTechnologyType() {
            ConnectionTechnologyType result = ConnectionTechnologyType.forNumber(this.connectionTechnologyType_);
            return result == null ? ConnectionTechnologyType.CONNECTION_TECHNOLOGY_TYPE_UNKNOWN : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConnectionTechnologyType(ConnectionTechnologyType value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.connectionTechnologyType_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearConnectionTechnologyType() {
            this.bitField0_ &= -3;
            this.connectionTechnologyType_ = 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
        @Deprecated
        public boolean hasDisconnectReason() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
        @Deprecated
        public String getDisconnectReason() {
            return this.disconnectReason_;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
        @Deprecated
        public ByteString getDisconnectReasonBytes() {
            return ByteString.copyFromUtf8(this.disconnectReason_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDisconnectReason(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 4;
            this.disconnectReason_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDisconnectReason() {
            this.bitField0_ &= -5;
            this.disconnectReason_ = getDefaultInstance().getDisconnectReason();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDisconnectReasonBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 4;
            this.disconnectReason_ = value.toStringUtf8();
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
        public boolean hasDeviceConnectedTo() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
        public DeviceInfo getDeviceConnectedTo() {
            DeviceInfo deviceInfo = this.deviceConnectedTo_;
            return deviceInfo == null ? DeviceInfo.getDefaultInstance() : deviceInfo;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceConnectedTo(DeviceInfo value) {
            if (value == null) {
                throw null;
            }
            this.deviceConnectedTo_ = value;
            this.bitField0_ |= 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceConnectedTo(DeviceInfo.Builder builderForValue) {
            this.deviceConnectedTo_ = builderForValue.build();
            this.bitField0_ |= 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeDeviceConnectedTo(DeviceInfo value) {
            if (value == null) {
                throw null;
            }
            DeviceInfo deviceInfo = this.deviceConnectedTo_;
            if (deviceInfo != null && deviceInfo != DeviceInfo.getDefaultInstance()) {
                this.deviceConnectedTo_ = DeviceInfo.newBuilder(this.deviceConnectedTo_).mergeFrom(value).buildPartial();
            } else {
                this.deviceConnectedTo_ = value;
            }
            this.bitField0_ |= 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceConnectedTo() {
            this.deviceConnectedTo_ = null;
            this.bitField0_ &= -9;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
        public boolean hasRfcommSession() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
        public RFCommSession getRfcommSession() {
            RFCommSession rFCommSession = this.rfcommSession_;
            return rFCommSession == null ? RFCommSession.getDefaultInstance() : rFCommSession;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRfcommSession(RFCommSession value) {
            if (value == null) {
                throw null;
            }
            this.rfcommSession_ = value;
            this.bitField0_ |= 16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRfcommSession(RFCommSession.Builder builderForValue) {
            this.rfcommSession_ = builderForValue.build();
            this.bitField0_ |= 16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeRfcommSession(RFCommSession value) {
            if (value == null) {
                throw null;
            }
            RFCommSession rFCommSession = this.rfcommSession_;
            if (rFCommSession != null && rFCommSession != RFCommSession.getDefaultInstance()) {
                this.rfcommSession_ = RFCommSession.newBuilder(this.rfcommSession_).mergeFrom(value).buildPartial();
            } else {
                this.rfcommSession_ = value;
            }
            this.bitField0_ |= 16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRfcommSession() {
            this.rfcommSession_ = null;
            this.bitField0_ &= -17;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
        public boolean hasA2DpSession() {
            return (this.bitField0_ & 32) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
        public A2DPSession getA2DpSession() {
            A2DPSession a2DPSession = this.a2DpSession_;
            return a2DPSession == null ? A2DPSession.getDefaultInstance() : a2DPSession;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setA2DpSession(A2DPSession value) {
            if (value == null) {
                throw null;
            }
            this.a2DpSession_ = value;
            this.bitField0_ |= 32;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setA2DpSession(A2DPSession.Builder builderForValue) {
            this.a2DpSession_ = builderForValue.build();
            this.bitField0_ |= 32;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeA2DpSession(A2DPSession value) {
            if (value == null) {
                throw null;
            }
            A2DPSession a2DPSession = this.a2DpSession_;
            if (a2DPSession != null && a2DPSession != A2DPSession.getDefaultInstance()) {
                this.a2DpSession_ = A2DPSession.newBuilder(this.a2DpSession_).mergeFrom(value).buildPartial();
            } else {
                this.a2DpSession_ = value;
            }
            this.bitField0_ |= 32;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearA2DpSession() {
            this.a2DpSession_ = null;
            this.bitField0_ &= -33;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
        public boolean hasDisconnectReasonType() {
            return (this.bitField0_ & 64) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
        public DisconnectReasonType getDisconnectReasonType() {
            DisconnectReasonType result = DisconnectReasonType.forNumber(this.disconnectReasonType_);
            return result == null ? DisconnectReasonType.UNKNOWN : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDisconnectReasonType(DisconnectReasonType value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 64;
            this.disconnectReasonType_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDisconnectReasonType() {
            this.bitField0_ &= -65;
            this.disconnectReasonType_ = 0;
        }

        public static BluetoothSession parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (BluetoothSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BluetoothSession parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BluetoothSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BluetoothSession parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (BluetoothSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BluetoothSession parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BluetoothSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BluetoothSession parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (BluetoothSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static BluetoothSession parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (BluetoothSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static BluetoothSession parseFrom(InputStream input) throws IOException {
            return (BluetoothSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BluetoothSession parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BluetoothSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BluetoothSession parseDelimitedFrom(InputStream input) throws IOException {
            return (BluetoothSession) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static BluetoothSession parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BluetoothSession) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static BluetoothSession parseFrom(CodedInputStream input) throws IOException {
            return (BluetoothSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static BluetoothSession parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (BluetoothSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(BluetoothSession prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<BluetoothSession, Builder> implements BluetoothSessionOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(BluetoothSession.DEFAULT_INSTANCE);
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
            public boolean hasSessionDurationSec() {
                return ((BluetoothSession) this.instance).hasSessionDurationSec();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
            public long getSessionDurationSec() {
                return ((BluetoothSession) this.instance).getSessionDurationSec();
            }

            public Builder setSessionDurationSec(long value) {
                copyOnWrite();
                ((BluetoothSession) this.instance).setSessionDurationSec(value);
                return this;
            }

            public Builder clearSessionDurationSec() {
                copyOnWrite();
                ((BluetoothSession) this.instance).clearSessionDurationSec();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
            public boolean hasConnectionTechnologyType() {
                return ((BluetoothSession) this.instance).hasConnectionTechnologyType();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
            public ConnectionTechnologyType getConnectionTechnologyType() {
                return ((BluetoothSession) this.instance).getConnectionTechnologyType();
            }

            public Builder setConnectionTechnologyType(ConnectionTechnologyType value) {
                copyOnWrite();
                ((BluetoothSession) this.instance).setConnectionTechnologyType(value);
                return this;
            }

            public Builder clearConnectionTechnologyType() {
                copyOnWrite();
                ((BluetoothSession) this.instance).clearConnectionTechnologyType();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
            @Deprecated
            public boolean hasDisconnectReason() {
                return ((BluetoothSession) this.instance).hasDisconnectReason();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
            @Deprecated
            public String getDisconnectReason() {
                return ((BluetoothSession) this.instance).getDisconnectReason();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
            @Deprecated
            public ByteString getDisconnectReasonBytes() {
                return ((BluetoothSession) this.instance).getDisconnectReasonBytes();
            }

            @Deprecated
            public Builder setDisconnectReason(String value) {
                copyOnWrite();
                ((BluetoothSession) this.instance).setDisconnectReason(value);
                return this;
            }

            @Deprecated
            public Builder clearDisconnectReason() {
                copyOnWrite();
                ((BluetoothSession) this.instance).clearDisconnectReason();
                return this;
            }

            @Deprecated
            public Builder setDisconnectReasonBytes(ByteString value) {
                copyOnWrite();
                ((BluetoothSession) this.instance).setDisconnectReasonBytes(value);
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
            public boolean hasDeviceConnectedTo() {
                return ((BluetoothSession) this.instance).hasDeviceConnectedTo();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
            public DeviceInfo getDeviceConnectedTo() {
                return ((BluetoothSession) this.instance).getDeviceConnectedTo();
            }

            public Builder setDeviceConnectedTo(DeviceInfo value) {
                copyOnWrite();
                ((BluetoothSession) this.instance).setDeviceConnectedTo(value);
                return this;
            }

            public Builder setDeviceConnectedTo(DeviceInfo.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothSession) this.instance).setDeviceConnectedTo(builderForValue);
                return this;
            }

            public Builder mergeDeviceConnectedTo(DeviceInfo value) {
                copyOnWrite();
                ((BluetoothSession) this.instance).mergeDeviceConnectedTo(value);
                return this;
            }

            public Builder clearDeviceConnectedTo() {
                copyOnWrite();
                ((BluetoothSession) this.instance).clearDeviceConnectedTo();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
            public boolean hasRfcommSession() {
                return ((BluetoothSession) this.instance).hasRfcommSession();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
            public RFCommSession getRfcommSession() {
                return ((BluetoothSession) this.instance).getRfcommSession();
            }

            public Builder setRfcommSession(RFCommSession value) {
                copyOnWrite();
                ((BluetoothSession) this.instance).setRfcommSession(value);
                return this;
            }

            public Builder setRfcommSession(RFCommSession.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothSession) this.instance).setRfcommSession(builderForValue);
                return this;
            }

            public Builder mergeRfcommSession(RFCommSession value) {
                copyOnWrite();
                ((BluetoothSession) this.instance).mergeRfcommSession(value);
                return this;
            }

            public Builder clearRfcommSession() {
                copyOnWrite();
                ((BluetoothSession) this.instance).clearRfcommSession();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
            public boolean hasA2DpSession() {
                return ((BluetoothSession) this.instance).hasA2DpSession();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
            public A2DPSession getA2DpSession() {
                return ((BluetoothSession) this.instance).getA2DpSession();
            }

            public Builder setA2DpSession(A2DPSession value) {
                copyOnWrite();
                ((BluetoothSession) this.instance).setA2DpSession(value);
                return this;
            }

            public Builder setA2DpSession(A2DPSession.Builder builderForValue) {
                copyOnWrite();
                ((BluetoothSession) this.instance).setA2DpSession(builderForValue);
                return this;
            }

            public Builder mergeA2DpSession(A2DPSession value) {
                copyOnWrite();
                ((BluetoothSession) this.instance).mergeA2DpSession(value);
                return this;
            }

            public Builder clearA2DpSession() {
                copyOnWrite();
                ((BluetoothSession) this.instance).clearA2DpSession();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
            public boolean hasDisconnectReasonType() {
                return ((BluetoothSession) this.instance).hasDisconnectReasonType();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.BluetoothSessionOrBuilder
            public DisconnectReasonType getDisconnectReasonType() {
                return ((BluetoothSession) this.instance).getDisconnectReasonType();
            }

            public Builder setDisconnectReasonType(DisconnectReasonType value) {
                copyOnWrite();
                ((BluetoothSession) this.instance).setDisconnectReasonType(value);
                return this;
            }

            public Builder clearDisconnectReasonType() {
                copyOnWrite();
                ((BluetoothSession) this.instance).clearDisconnectReasonType();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new BluetoothSession();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "sessionDurationSec_", "connectionTechnologyType_", ConnectionTechnologyType.internalGetVerifier(), "disconnectReason_", "deviceConnectedTo_", "rfcommSession_", "a2DpSession_", "disconnectReasonType_", DisconnectReasonType.internalGetVerifier()};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0000\u0001\u0002\b\u0007\u0000\u0000\u0000\u0002\u0002\u0000\u0003\f\u0001\u0004\b\u0002\u0005\t\u0003\u0006\t\u0004\u0007\t\u0005\b\f\u0006", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<BluetoothSession> parser = PARSER;
                    if (parser == null) {
                        synchronized (BluetoothSession.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            BluetoothSession defaultInstance = new BluetoothSession();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(BluetoothSession.class, defaultInstance);
        }

        public static BluetoothSession getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<BluetoothSession> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class RFCommSession extends GeneratedMessageLite<RFCommSession, Builder> implements RFCommSessionOrBuilder {
        private static final RFCommSession DEFAULT_INSTANCE;
        private static volatile Parser<RFCommSession> PARSER = null;
        public static final int RX_BYTES_FIELD_NUMBER = 1;
        public static final int TX_BYTES_FIELD_NUMBER = 2;
        private int bitField0_;
        private int rxBytes_;
        private int txBytes_;

        private RFCommSession() {
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.RFCommSessionOrBuilder
        public boolean hasRxBytes() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.RFCommSessionOrBuilder
        public int getRxBytes() {
            return this.rxBytes_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRxBytes(int value) {
            this.bitField0_ |= 1;
            this.rxBytes_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRxBytes() {
            this.bitField0_ &= -2;
            this.rxBytes_ = 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.RFCommSessionOrBuilder
        public boolean hasTxBytes() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.RFCommSessionOrBuilder
        public int getTxBytes() {
            return this.txBytes_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTxBytes(int value) {
            this.bitField0_ |= 2;
            this.txBytes_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTxBytes() {
            this.bitField0_ &= -3;
            this.txBytes_ = 0;
        }

        public static RFCommSession parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (RFCommSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static RFCommSession parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (RFCommSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static RFCommSession parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (RFCommSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static RFCommSession parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (RFCommSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static RFCommSession parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (RFCommSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static RFCommSession parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (RFCommSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static RFCommSession parseFrom(InputStream input) throws IOException {
            return (RFCommSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static RFCommSession parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (RFCommSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static RFCommSession parseDelimitedFrom(InputStream input) throws IOException {
            return (RFCommSession) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static RFCommSession parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (RFCommSession) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static RFCommSession parseFrom(CodedInputStream input) throws IOException {
            return (RFCommSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static RFCommSession parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (RFCommSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(RFCommSession prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<RFCommSession, Builder> implements RFCommSessionOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(RFCommSession.DEFAULT_INSTANCE);
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.RFCommSessionOrBuilder
            public boolean hasRxBytes() {
                return ((RFCommSession) this.instance).hasRxBytes();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.RFCommSessionOrBuilder
            public int getRxBytes() {
                return ((RFCommSession) this.instance).getRxBytes();
            }

            public Builder setRxBytes(int value) {
                copyOnWrite();
                ((RFCommSession) this.instance).setRxBytes(value);
                return this;
            }

            public Builder clearRxBytes() {
                copyOnWrite();
                ((RFCommSession) this.instance).clearRxBytes();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.RFCommSessionOrBuilder
            public boolean hasTxBytes() {
                return ((RFCommSession) this.instance).hasTxBytes();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.RFCommSessionOrBuilder
            public int getTxBytes() {
                return ((RFCommSession) this.instance).getTxBytes();
            }

            public Builder setTxBytes(int value) {
                copyOnWrite();
                ((RFCommSession) this.instance).setTxBytes(value);
                return this;
            }

            public Builder clearTxBytes() {
                copyOnWrite();
                ((RFCommSession) this.instance).clearTxBytes();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new RFCommSession();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "rxBytes_", "txBytes_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0004\u0001", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<RFCommSession> parser = PARSER;
                    if (parser == null) {
                        synchronized (RFCommSession.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            RFCommSession defaultInstance = new RFCommSession();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(RFCommSession.class, defaultInstance);
        }

        public static RFCommSession getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RFCommSession> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class A2DPSession extends GeneratedMessageLite<A2DPSession, Builder> implements A2DPSessionOrBuilder {
        public static final int AUDIO_DURATION_MILLIS_FIELD_NUMBER = 8;
        public static final int BUFFER_OVERRUNS_MAX_COUNT_FIELD_NUMBER = 4;
        public static final int BUFFER_OVERRUNS_TOTAL_FIELD_NUMBER = 5;
        public static final int BUFFER_UNDERRUNS_AVERAGE_FIELD_NUMBER = 6;
        public static final int BUFFER_UNDERRUNS_COUNT_FIELD_NUMBER = 7;
        private static final A2DPSession DEFAULT_INSTANCE;
        public static final int IS_A2DP_OFFLOAD_FIELD_NUMBER = 10;
        public static final int MEDIA_TIMER_AVG_MILLIS_FIELD_NUMBER = 3;
        public static final int MEDIA_TIMER_MAX_MILLIS_FIELD_NUMBER = 2;
        public static final int MEDIA_TIMER_MIN_MILLIS_FIELD_NUMBER = 1;
        private static volatile Parser<A2DPSession> PARSER = null;
        public static final int SOURCE_CODEC_FIELD_NUMBER = 9;
        private long audioDurationMillis_;
        private int bitField0_;
        private int bufferOverrunsMaxCount_;
        private int bufferOverrunsTotal_;
        private float bufferUnderrunsAverage_;
        private int bufferUnderrunsCount_;
        private boolean isA2DpOffload_;
        private int mediaTimerAvgMillis_;
        private int mediaTimerMaxMillis_;
        private int mediaTimerMinMillis_;
        private int sourceCodec_;

        private A2DPSession() {
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
        public boolean hasMediaTimerMinMillis() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
        public int getMediaTimerMinMillis() {
            return this.mediaTimerMinMillis_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMediaTimerMinMillis(int value) {
            this.bitField0_ |= 1;
            this.mediaTimerMinMillis_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMediaTimerMinMillis() {
            this.bitField0_ &= -2;
            this.mediaTimerMinMillis_ = 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
        public boolean hasMediaTimerMaxMillis() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
        public int getMediaTimerMaxMillis() {
            return this.mediaTimerMaxMillis_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMediaTimerMaxMillis(int value) {
            this.bitField0_ |= 2;
            this.mediaTimerMaxMillis_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMediaTimerMaxMillis() {
            this.bitField0_ &= -3;
            this.mediaTimerMaxMillis_ = 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
        public boolean hasMediaTimerAvgMillis() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
        public int getMediaTimerAvgMillis() {
            return this.mediaTimerAvgMillis_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMediaTimerAvgMillis(int value) {
            this.bitField0_ |= 4;
            this.mediaTimerAvgMillis_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMediaTimerAvgMillis() {
            this.bitField0_ &= -5;
            this.mediaTimerAvgMillis_ = 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
        public boolean hasBufferOverrunsMaxCount() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
        public int getBufferOverrunsMaxCount() {
            return this.bufferOverrunsMaxCount_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBufferOverrunsMaxCount(int value) {
            this.bitField0_ |= 8;
            this.bufferOverrunsMaxCount_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBufferOverrunsMaxCount() {
            this.bitField0_ &= -9;
            this.bufferOverrunsMaxCount_ = 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
        public boolean hasBufferOverrunsTotal() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
        public int getBufferOverrunsTotal() {
            return this.bufferOverrunsTotal_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBufferOverrunsTotal(int value) {
            this.bitField0_ |= 16;
            this.bufferOverrunsTotal_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBufferOverrunsTotal() {
            this.bitField0_ &= -17;
            this.bufferOverrunsTotal_ = 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
        public boolean hasBufferUnderrunsAverage() {
            return (this.bitField0_ & 32) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
        public float getBufferUnderrunsAverage() {
            return this.bufferUnderrunsAverage_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBufferUnderrunsAverage(float value) {
            this.bitField0_ |= 32;
            this.bufferUnderrunsAverage_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBufferUnderrunsAverage() {
            this.bitField0_ &= -33;
            this.bufferUnderrunsAverage_ = 0.0f;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
        public boolean hasBufferUnderrunsCount() {
            return (this.bitField0_ & 64) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
        public int getBufferUnderrunsCount() {
            return this.bufferUnderrunsCount_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBufferUnderrunsCount(int value) {
            this.bitField0_ |= 64;
            this.bufferUnderrunsCount_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBufferUnderrunsCount() {
            this.bitField0_ &= -65;
            this.bufferUnderrunsCount_ = 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
        public boolean hasAudioDurationMillis() {
            return (this.bitField0_ & 128) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
        public long getAudioDurationMillis() {
            return this.audioDurationMillis_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAudioDurationMillis(long value) {
            this.bitField0_ |= 128;
            this.audioDurationMillis_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAudioDurationMillis() {
            this.bitField0_ &= -129;
            this.audioDurationMillis_ = 0L;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
        public boolean hasSourceCodec() {
            return (this.bitField0_ & 256) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
        public A2dpSourceCodec getSourceCodec() {
            A2dpSourceCodec result = A2dpSourceCodec.forNumber(this.sourceCodec_);
            return result == null ? A2dpSourceCodec.A2DP_SOURCE_CODEC_UNKNOWN : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSourceCodec(A2dpSourceCodec value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 256;
            this.sourceCodec_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSourceCodec() {
            this.bitField0_ &= -257;
            this.sourceCodec_ = 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
        public boolean hasIsA2DpOffload() {
            return (this.bitField0_ & 512) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
        public boolean getIsA2DpOffload() {
            return this.isA2DpOffload_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIsA2DpOffload(boolean value) {
            this.bitField0_ |= 512;
            this.isA2DpOffload_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIsA2DpOffload() {
            this.bitField0_ &= -513;
            this.isA2DpOffload_ = false;
        }

        public static A2DPSession parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (A2DPSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static A2DPSession parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (A2DPSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static A2DPSession parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (A2DPSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static A2DPSession parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (A2DPSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static A2DPSession parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (A2DPSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static A2DPSession parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (A2DPSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static A2DPSession parseFrom(InputStream input) throws IOException {
            return (A2DPSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static A2DPSession parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (A2DPSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static A2DPSession parseDelimitedFrom(InputStream input) throws IOException {
            return (A2DPSession) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static A2DPSession parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (A2DPSession) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static A2DPSession parseFrom(CodedInputStream input) throws IOException {
            return (A2DPSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static A2DPSession parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (A2DPSession) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(A2DPSession prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<A2DPSession, Builder> implements A2DPSessionOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(A2DPSession.DEFAULT_INSTANCE);
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
            public boolean hasMediaTimerMinMillis() {
                return ((A2DPSession) this.instance).hasMediaTimerMinMillis();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
            public int getMediaTimerMinMillis() {
                return ((A2DPSession) this.instance).getMediaTimerMinMillis();
            }

            public Builder setMediaTimerMinMillis(int value) {
                copyOnWrite();
                ((A2DPSession) this.instance).setMediaTimerMinMillis(value);
                return this;
            }

            public Builder clearMediaTimerMinMillis() {
                copyOnWrite();
                ((A2DPSession) this.instance).clearMediaTimerMinMillis();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
            public boolean hasMediaTimerMaxMillis() {
                return ((A2DPSession) this.instance).hasMediaTimerMaxMillis();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
            public int getMediaTimerMaxMillis() {
                return ((A2DPSession) this.instance).getMediaTimerMaxMillis();
            }

            public Builder setMediaTimerMaxMillis(int value) {
                copyOnWrite();
                ((A2DPSession) this.instance).setMediaTimerMaxMillis(value);
                return this;
            }

            public Builder clearMediaTimerMaxMillis() {
                copyOnWrite();
                ((A2DPSession) this.instance).clearMediaTimerMaxMillis();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
            public boolean hasMediaTimerAvgMillis() {
                return ((A2DPSession) this.instance).hasMediaTimerAvgMillis();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
            public int getMediaTimerAvgMillis() {
                return ((A2DPSession) this.instance).getMediaTimerAvgMillis();
            }

            public Builder setMediaTimerAvgMillis(int value) {
                copyOnWrite();
                ((A2DPSession) this.instance).setMediaTimerAvgMillis(value);
                return this;
            }

            public Builder clearMediaTimerAvgMillis() {
                copyOnWrite();
                ((A2DPSession) this.instance).clearMediaTimerAvgMillis();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
            public boolean hasBufferOverrunsMaxCount() {
                return ((A2DPSession) this.instance).hasBufferOverrunsMaxCount();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
            public int getBufferOverrunsMaxCount() {
                return ((A2DPSession) this.instance).getBufferOverrunsMaxCount();
            }

            public Builder setBufferOverrunsMaxCount(int value) {
                copyOnWrite();
                ((A2DPSession) this.instance).setBufferOverrunsMaxCount(value);
                return this;
            }

            public Builder clearBufferOverrunsMaxCount() {
                copyOnWrite();
                ((A2DPSession) this.instance).clearBufferOverrunsMaxCount();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
            public boolean hasBufferOverrunsTotal() {
                return ((A2DPSession) this.instance).hasBufferOverrunsTotal();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
            public int getBufferOverrunsTotal() {
                return ((A2DPSession) this.instance).getBufferOverrunsTotal();
            }

            public Builder setBufferOverrunsTotal(int value) {
                copyOnWrite();
                ((A2DPSession) this.instance).setBufferOverrunsTotal(value);
                return this;
            }

            public Builder clearBufferOverrunsTotal() {
                copyOnWrite();
                ((A2DPSession) this.instance).clearBufferOverrunsTotal();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
            public boolean hasBufferUnderrunsAverage() {
                return ((A2DPSession) this.instance).hasBufferUnderrunsAverage();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
            public float getBufferUnderrunsAverage() {
                return ((A2DPSession) this.instance).getBufferUnderrunsAverage();
            }

            public Builder setBufferUnderrunsAverage(float value) {
                copyOnWrite();
                ((A2DPSession) this.instance).setBufferUnderrunsAverage(value);
                return this;
            }

            public Builder clearBufferUnderrunsAverage() {
                copyOnWrite();
                ((A2DPSession) this.instance).clearBufferUnderrunsAverage();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
            public boolean hasBufferUnderrunsCount() {
                return ((A2DPSession) this.instance).hasBufferUnderrunsCount();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
            public int getBufferUnderrunsCount() {
                return ((A2DPSession) this.instance).getBufferUnderrunsCount();
            }

            public Builder setBufferUnderrunsCount(int value) {
                copyOnWrite();
                ((A2DPSession) this.instance).setBufferUnderrunsCount(value);
                return this;
            }

            public Builder clearBufferUnderrunsCount() {
                copyOnWrite();
                ((A2DPSession) this.instance).clearBufferUnderrunsCount();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
            public boolean hasAudioDurationMillis() {
                return ((A2DPSession) this.instance).hasAudioDurationMillis();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
            public long getAudioDurationMillis() {
                return ((A2DPSession) this.instance).getAudioDurationMillis();
            }

            public Builder setAudioDurationMillis(long value) {
                copyOnWrite();
                ((A2DPSession) this.instance).setAudioDurationMillis(value);
                return this;
            }

            public Builder clearAudioDurationMillis() {
                copyOnWrite();
                ((A2DPSession) this.instance).clearAudioDurationMillis();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
            public boolean hasSourceCodec() {
                return ((A2DPSession) this.instance).hasSourceCodec();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
            public A2dpSourceCodec getSourceCodec() {
                return ((A2DPSession) this.instance).getSourceCodec();
            }

            public Builder setSourceCodec(A2dpSourceCodec value) {
                copyOnWrite();
                ((A2DPSession) this.instance).setSourceCodec(value);
                return this;
            }

            public Builder clearSourceCodec() {
                copyOnWrite();
                ((A2DPSession) this.instance).clearSourceCodec();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
            public boolean hasIsA2DpOffload() {
                return ((A2DPSession) this.instance).hasIsA2DpOffload();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.A2DPSessionOrBuilder
            public boolean getIsA2DpOffload() {
                return ((A2DPSession) this.instance).getIsA2DpOffload();
            }

            public Builder setIsA2DpOffload(boolean value) {
                copyOnWrite();
                ((A2DPSession) this.instance).setIsA2DpOffload(value);
                return this;
            }

            public Builder clearIsA2DpOffload() {
                copyOnWrite();
                ((A2DPSession) this.instance).clearIsA2DpOffload();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new A2DPSession();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "mediaTimerMinMillis_", "mediaTimerMaxMillis_", "mediaTimerAvgMillis_", "bufferOverrunsMaxCount_", "bufferOverrunsTotal_", "bufferUnderrunsAverage_", "bufferUnderrunsCount_", "audioDurationMillis_", "sourceCodec_", A2dpSourceCodec.internalGetVerifier(), "isA2DpOffload_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\n\u0000\u0001\u0001\n\n\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0004\u0001\u0003\u0004\u0002\u0004\u0004\u0003\u0005\u0004\u0004\u0006\u0001\u0005\u0007\u0004\u0006\b\u0002\u0007\t\f\b\n\u0007\t", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<A2DPSession> parser = PARSER;
                    if (parser == null) {
                        synchronized (A2DPSession.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            A2DPSession defaultInstance = new A2DPSession();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(A2DPSession.class, defaultInstance);
        }

        public static A2DPSession getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<A2DPSession> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class PairEvent extends GeneratedMessageLite<PairEvent, Builder> implements PairEventOrBuilder {
        private static final PairEvent DEFAULT_INSTANCE;
        public static final int DEVICE_PAIRED_WITH_FIELD_NUMBER = 3;
        public static final int DISCONNECT_REASON_FIELD_NUMBER = 1;
        public static final int EVENT_TIME_MILLIS_FIELD_NUMBER = 2;
        private static volatile Parser<PairEvent> PARSER;
        private int bitField0_;
        private DeviceInfo devicePairedWith_;
        private int disconnectReason_;
        private long eventTimeMillis_;

        private PairEvent() {
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.PairEventOrBuilder
        public boolean hasDisconnectReason() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.PairEventOrBuilder
        public int getDisconnectReason() {
            return this.disconnectReason_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDisconnectReason(int value) {
            this.bitField0_ |= 1;
            this.disconnectReason_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDisconnectReason() {
            this.bitField0_ &= -2;
            this.disconnectReason_ = 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.PairEventOrBuilder
        public boolean hasEventTimeMillis() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.PairEventOrBuilder
        public long getEventTimeMillis() {
            return this.eventTimeMillis_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEventTimeMillis(long value) {
            this.bitField0_ |= 2;
            this.eventTimeMillis_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEventTimeMillis() {
            this.bitField0_ &= -3;
            this.eventTimeMillis_ = 0L;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.PairEventOrBuilder
        public boolean hasDevicePairedWith() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.PairEventOrBuilder
        public DeviceInfo getDevicePairedWith() {
            DeviceInfo deviceInfo = this.devicePairedWith_;
            return deviceInfo == null ? DeviceInfo.getDefaultInstance() : deviceInfo;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDevicePairedWith(DeviceInfo value) {
            if (value == null) {
                throw null;
            }
            this.devicePairedWith_ = value;
            this.bitField0_ |= 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDevicePairedWith(DeviceInfo.Builder builderForValue) {
            this.devicePairedWith_ = builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeDevicePairedWith(DeviceInfo value) {
            if (value == null) {
                throw null;
            }
            DeviceInfo deviceInfo = this.devicePairedWith_;
            if (deviceInfo != null && deviceInfo != DeviceInfo.getDefaultInstance()) {
                this.devicePairedWith_ = DeviceInfo.newBuilder(this.devicePairedWith_).mergeFrom(value).buildPartial();
            } else {
                this.devicePairedWith_ = value;
            }
            this.bitField0_ |= 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDevicePairedWith() {
            this.devicePairedWith_ = null;
            this.bitField0_ &= -5;
        }

        public static PairEvent parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (PairEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PairEvent parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PairEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PairEvent parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (PairEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PairEvent parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PairEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PairEvent parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (PairEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PairEvent parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PairEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PairEvent parseFrom(InputStream input) throws IOException {
            return (PairEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PairEvent parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PairEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PairEvent parseDelimitedFrom(InputStream input) throws IOException {
            return (PairEvent) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static PairEvent parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PairEvent) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PairEvent parseFrom(CodedInputStream input) throws IOException {
            return (PairEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PairEvent parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PairEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(PairEvent prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PairEvent, Builder> implements PairEventOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(PairEvent.DEFAULT_INSTANCE);
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.PairEventOrBuilder
            public boolean hasDisconnectReason() {
                return ((PairEvent) this.instance).hasDisconnectReason();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.PairEventOrBuilder
            public int getDisconnectReason() {
                return ((PairEvent) this.instance).getDisconnectReason();
            }

            public Builder setDisconnectReason(int value) {
                copyOnWrite();
                ((PairEvent) this.instance).setDisconnectReason(value);
                return this;
            }

            public Builder clearDisconnectReason() {
                copyOnWrite();
                ((PairEvent) this.instance).clearDisconnectReason();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.PairEventOrBuilder
            public boolean hasEventTimeMillis() {
                return ((PairEvent) this.instance).hasEventTimeMillis();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.PairEventOrBuilder
            public long getEventTimeMillis() {
                return ((PairEvent) this.instance).getEventTimeMillis();
            }

            public Builder setEventTimeMillis(long value) {
                copyOnWrite();
                ((PairEvent) this.instance).setEventTimeMillis(value);
                return this;
            }

            public Builder clearEventTimeMillis() {
                copyOnWrite();
                ((PairEvent) this.instance).clearEventTimeMillis();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.PairEventOrBuilder
            public boolean hasDevicePairedWith() {
                return ((PairEvent) this.instance).hasDevicePairedWith();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.PairEventOrBuilder
            public DeviceInfo getDevicePairedWith() {
                return ((PairEvent) this.instance).getDevicePairedWith();
            }

            public Builder setDevicePairedWith(DeviceInfo value) {
                copyOnWrite();
                ((PairEvent) this.instance).setDevicePairedWith(value);
                return this;
            }

            public Builder setDevicePairedWith(DeviceInfo.Builder builderForValue) {
                copyOnWrite();
                ((PairEvent) this.instance).setDevicePairedWith(builderForValue);
                return this;
            }

            public Builder mergeDevicePairedWith(DeviceInfo value) {
                copyOnWrite();
                ((PairEvent) this.instance).mergeDevicePairedWith(value);
                return this;
            }

            public Builder clearDevicePairedWith() {
                copyOnWrite();
                ((PairEvent) this.instance).clearDevicePairedWith();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new PairEvent();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "disconnectReason_", "eventTimeMillis_", "devicePairedWith_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0002\u0001\u0003\t\u0002", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<PairEvent> parser = PARSER;
                    if (parser == null) {
                        synchronized (PairEvent.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            PairEvent defaultInstance = new PairEvent();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(PairEvent.class, defaultInstance);
        }

        public static PairEvent getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PairEvent> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class WakeEvent extends GeneratedMessageLite<WakeEvent, Builder> implements WakeEventOrBuilder {
        private static final WakeEvent DEFAULT_INSTANCE;
        public static final int EVENT_TIME_MILLIS_FIELD_NUMBER = 4;
        public static final int NAME_FIELD_NUMBER = 3;
        private static volatile Parser<WakeEvent> PARSER = null;
        public static final int REQUESTOR_FIELD_NUMBER = 2;
        public static final int WAKE_EVENT_TYPE_FIELD_NUMBER = 1;
        private int bitField0_;
        private long eventTimeMillis_;
        private int wakeEventType_;
        private String requestor_ = INetd.NEXTHOP_NONE;
        private String name_ = INetd.NEXTHOP_NONE;

        private WakeEvent() {
        }

        public enum WakeEventType implements Internal.EnumLite {
            UNKNOWN(0),
            ACQUIRED(1),
            RELEASED(2);

            public static final int ACQUIRED_VALUE = 1;
            public static final int RELEASED_VALUE = 2;
            public static final int UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<WakeEventType> internalValueMap = new Internal.EnumLiteMap<WakeEventType>() { // from class: com.android.bluetooth.BluetoothMetricsProto.WakeEvent.WakeEventType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public WakeEventType findValueByNumber(int number) {
                    return WakeEventType.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static WakeEventType valueOf(int value) {
                return forNumber(value);
            }

            public static WakeEventType forNumber(int value) {
                if (value == 0) {
                    return UNKNOWN;
                }
                if (value == 1) {
                    return ACQUIRED;
                }
                if (value == 2) {
                    return RELEASED;
                }
                return null;
            }

            public static Internal.EnumLiteMap<WakeEventType> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return WakeEventTypeVerifier.INSTANCE;
            }

            private static final class WakeEventTypeVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = new WakeEventTypeVerifier();

                private WakeEventTypeVerifier() {
                }

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int number) {
                    return WakeEventType.forNumber(number) != null;
                }
            }

            WakeEventType(int value) {
                this.value = value;
            }
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.WakeEventOrBuilder
        public boolean hasWakeEventType() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.WakeEventOrBuilder
        public WakeEventType getWakeEventType() {
            WakeEventType result = WakeEventType.forNumber(this.wakeEventType_);
            return result == null ? WakeEventType.UNKNOWN : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setWakeEventType(WakeEventType value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.wakeEventType_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearWakeEventType() {
            this.bitField0_ &= -2;
            this.wakeEventType_ = 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.WakeEventOrBuilder
        public boolean hasRequestor() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.WakeEventOrBuilder
        public String getRequestor() {
            return this.requestor_;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.WakeEventOrBuilder
        public ByteString getRequestorBytes() {
            return ByteString.copyFromUtf8(this.requestor_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRequestor(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.requestor_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRequestor() {
            this.bitField0_ &= -3;
            this.requestor_ = getDefaultInstance().getRequestor();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRequestorBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.requestor_ = value.toStringUtf8();
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.WakeEventOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.WakeEventOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.WakeEventOrBuilder
        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setName(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 4;
            this.name_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearName() {
            this.bitField0_ &= -5;
            this.name_ = getDefaultInstance().getName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNameBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 4;
            this.name_ = value.toStringUtf8();
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.WakeEventOrBuilder
        public boolean hasEventTimeMillis() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.WakeEventOrBuilder
        public long getEventTimeMillis() {
            return this.eventTimeMillis_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEventTimeMillis(long value) {
            this.bitField0_ |= 8;
            this.eventTimeMillis_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEventTimeMillis() {
            this.bitField0_ &= -9;
            this.eventTimeMillis_ = 0L;
        }

        public static WakeEvent parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (WakeEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WakeEvent parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WakeEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WakeEvent parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (WakeEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WakeEvent parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WakeEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WakeEvent parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (WakeEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static WakeEvent parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (WakeEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static WakeEvent parseFrom(InputStream input) throws IOException {
            return (WakeEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WakeEvent parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WakeEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WakeEvent parseDelimitedFrom(InputStream input) throws IOException {
            return (WakeEvent) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static WakeEvent parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WakeEvent) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static WakeEvent parseFrom(CodedInputStream input) throws IOException {
            return (WakeEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static WakeEvent parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (WakeEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(WakeEvent prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<WakeEvent, Builder> implements WakeEventOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(WakeEvent.DEFAULT_INSTANCE);
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.WakeEventOrBuilder
            public boolean hasWakeEventType() {
                return ((WakeEvent) this.instance).hasWakeEventType();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.WakeEventOrBuilder
            public WakeEventType getWakeEventType() {
                return ((WakeEvent) this.instance).getWakeEventType();
            }

            public Builder setWakeEventType(WakeEventType value) {
                copyOnWrite();
                ((WakeEvent) this.instance).setWakeEventType(value);
                return this;
            }

            public Builder clearWakeEventType() {
                copyOnWrite();
                ((WakeEvent) this.instance).clearWakeEventType();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.WakeEventOrBuilder
            public boolean hasRequestor() {
                return ((WakeEvent) this.instance).hasRequestor();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.WakeEventOrBuilder
            public String getRequestor() {
                return ((WakeEvent) this.instance).getRequestor();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.WakeEventOrBuilder
            public ByteString getRequestorBytes() {
                return ((WakeEvent) this.instance).getRequestorBytes();
            }

            public Builder setRequestor(String value) {
                copyOnWrite();
                ((WakeEvent) this.instance).setRequestor(value);
                return this;
            }

            public Builder clearRequestor() {
                copyOnWrite();
                ((WakeEvent) this.instance).clearRequestor();
                return this;
            }

            public Builder setRequestorBytes(ByteString value) {
                copyOnWrite();
                ((WakeEvent) this.instance).setRequestorBytes(value);
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.WakeEventOrBuilder
            public boolean hasName() {
                return ((WakeEvent) this.instance).hasName();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.WakeEventOrBuilder
            public String getName() {
                return ((WakeEvent) this.instance).getName();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.WakeEventOrBuilder
            public ByteString getNameBytes() {
                return ((WakeEvent) this.instance).getNameBytes();
            }

            public Builder setName(String value) {
                copyOnWrite();
                ((WakeEvent) this.instance).setName(value);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((WakeEvent) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString value) {
                copyOnWrite();
                ((WakeEvent) this.instance).setNameBytes(value);
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.WakeEventOrBuilder
            public boolean hasEventTimeMillis() {
                return ((WakeEvent) this.instance).hasEventTimeMillis();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.WakeEventOrBuilder
            public long getEventTimeMillis() {
                return ((WakeEvent) this.instance).getEventTimeMillis();
            }

            public Builder setEventTimeMillis(long value) {
                copyOnWrite();
                ((WakeEvent) this.instance).setEventTimeMillis(value);
                return this;
            }

            public Builder clearEventTimeMillis() {
                copyOnWrite();
                ((WakeEvent) this.instance).clearEventTimeMillis();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new WakeEvent();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "wakeEventType_", WakeEventType.internalGetVerifier(), "requestor_", "name_", "eventTimeMillis_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\f\u0000\u0002\b\u0001\u0003\b\u0002\u0004\u0002\u0003", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<WakeEvent> parser = PARSER;
                    if (parser == null) {
                        synchronized (WakeEvent.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            WakeEvent defaultInstance = new WakeEvent();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(WakeEvent.class, defaultInstance);
        }

        public static WakeEvent getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WakeEvent> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ScanEvent extends GeneratedMessageLite<ScanEvent, Builder> implements ScanEventOrBuilder {
        private static final ScanEvent DEFAULT_INSTANCE;
        public static final int EVENT_TIME_MILLIS_FIELD_NUMBER = 5;
        public static final int INITIATOR_FIELD_NUMBER = 2;
        public static final int NUMBER_RESULTS_FIELD_NUMBER = 4;
        private static volatile Parser<ScanEvent> PARSER = null;
        public static final int SCAN_EVENT_TYPE_FIELD_NUMBER = 1;
        public static final int SCAN_TECHNOLOGY_TYPE_FIELD_NUMBER = 3;
        private int bitField0_;
        private long eventTimeMillis_;
        private String initiator_ = INetd.NEXTHOP_NONE;
        private int numberResults_;
        private int scanEventType_;
        private int scanTechnologyType_;

        private ScanEvent() {
        }

        public enum ScanTechnologyType implements Internal.EnumLite {
            SCAN_TYPE_UNKNOWN(0),
            SCAN_TECH_TYPE_LE(1),
            SCAN_TECH_TYPE_BREDR(2),
            SCAN_TECH_TYPE_BOTH(3);

            public static final int SCAN_TECH_TYPE_BOTH_VALUE = 3;
            public static final int SCAN_TECH_TYPE_BREDR_VALUE = 2;
            public static final int SCAN_TECH_TYPE_LE_VALUE = 1;
            public static final int SCAN_TYPE_UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<ScanTechnologyType> internalValueMap = new Internal.EnumLiteMap<ScanTechnologyType>() { // from class: com.android.bluetooth.BluetoothMetricsProto.ScanEvent.ScanTechnologyType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public ScanTechnologyType findValueByNumber(int number) {
                    return ScanTechnologyType.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static ScanTechnologyType valueOf(int value) {
                return forNumber(value);
            }

            public static ScanTechnologyType forNumber(int value) {
                if (value == 0) {
                    return SCAN_TYPE_UNKNOWN;
                }
                if (value == 1) {
                    return SCAN_TECH_TYPE_LE;
                }
                if (value == 2) {
                    return SCAN_TECH_TYPE_BREDR;
                }
                if (value == 3) {
                    return SCAN_TECH_TYPE_BOTH;
                }
                return null;
            }

            public static Internal.EnumLiteMap<ScanTechnologyType> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return ScanTechnologyTypeVerifier.INSTANCE;
            }

            private static final class ScanTechnologyTypeVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = new ScanTechnologyTypeVerifier();

                private ScanTechnologyTypeVerifier() {
                }

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int number) {
                    return ScanTechnologyType.forNumber(number) != null;
                }
            }

            ScanTechnologyType(int value) {
                this.value = value;
            }
        }

        public enum ScanEventType implements Internal.EnumLite {
            SCAN_EVENT_START(0),
            SCAN_EVENT_STOP(1);

            public static final int SCAN_EVENT_START_VALUE = 0;
            public static final int SCAN_EVENT_STOP_VALUE = 1;
            private static final Internal.EnumLiteMap<ScanEventType> internalValueMap = new Internal.EnumLiteMap<ScanEventType>() { // from class: com.android.bluetooth.BluetoothMetricsProto.ScanEvent.ScanEventType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public ScanEventType findValueByNumber(int number) {
                    return ScanEventType.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static ScanEventType valueOf(int value) {
                return forNumber(value);
            }

            public static ScanEventType forNumber(int value) {
                if (value == 0) {
                    return SCAN_EVENT_START;
                }
                if (value == 1) {
                    return SCAN_EVENT_STOP;
                }
                return null;
            }

            public static Internal.EnumLiteMap<ScanEventType> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return ScanEventTypeVerifier.INSTANCE;
            }

            private static final class ScanEventTypeVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = new ScanEventTypeVerifier();

                private ScanEventTypeVerifier() {
                }

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int number) {
                    return ScanEventType.forNumber(number) != null;
                }
            }

            ScanEventType(int value) {
                this.value = value;
            }
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
        public boolean hasScanEventType() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
        public ScanEventType getScanEventType() {
            ScanEventType result = ScanEventType.forNumber(this.scanEventType_);
            return result == null ? ScanEventType.SCAN_EVENT_START : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setScanEventType(ScanEventType value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.scanEventType_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearScanEventType() {
            this.bitField0_ &= -2;
            this.scanEventType_ = 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
        public boolean hasInitiator() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
        public String getInitiator() {
            return this.initiator_;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
        public ByteString getInitiatorBytes() {
            return ByteString.copyFromUtf8(this.initiator_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInitiator(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.initiator_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearInitiator() {
            this.bitField0_ &= -3;
            this.initiator_ = getDefaultInstance().getInitiator();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInitiatorBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.initiator_ = value.toStringUtf8();
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
        public boolean hasScanTechnologyType() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
        public ScanTechnologyType getScanTechnologyType() {
            ScanTechnologyType result = ScanTechnologyType.forNumber(this.scanTechnologyType_);
            return result == null ? ScanTechnologyType.SCAN_TYPE_UNKNOWN : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setScanTechnologyType(ScanTechnologyType value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 4;
            this.scanTechnologyType_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearScanTechnologyType() {
            this.bitField0_ &= -5;
            this.scanTechnologyType_ = 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
        public boolean hasNumberResults() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
        public int getNumberResults() {
            return this.numberResults_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNumberResults(int value) {
            this.bitField0_ |= 8;
            this.numberResults_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNumberResults() {
            this.bitField0_ &= -9;
            this.numberResults_ = 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
        public boolean hasEventTimeMillis() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
        public long getEventTimeMillis() {
            return this.eventTimeMillis_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEventTimeMillis(long value) {
            this.bitField0_ |= 16;
            this.eventTimeMillis_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEventTimeMillis() {
            this.bitField0_ &= -17;
            this.eventTimeMillis_ = 0L;
        }

        public static ScanEvent parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (ScanEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ScanEvent parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ScanEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ScanEvent parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ScanEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ScanEvent parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ScanEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ScanEvent parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ScanEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ScanEvent parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ScanEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ScanEvent parseFrom(InputStream input) throws IOException {
            return (ScanEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ScanEvent parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ScanEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ScanEvent parseDelimitedFrom(InputStream input) throws IOException {
            return (ScanEvent) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ScanEvent parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ScanEvent) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ScanEvent parseFrom(CodedInputStream input) throws IOException {
            return (ScanEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ScanEvent parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ScanEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(ScanEvent prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ScanEvent, Builder> implements ScanEventOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(ScanEvent.DEFAULT_INSTANCE);
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
            public boolean hasScanEventType() {
                return ((ScanEvent) this.instance).hasScanEventType();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
            public ScanEventType getScanEventType() {
                return ((ScanEvent) this.instance).getScanEventType();
            }

            public Builder setScanEventType(ScanEventType value) {
                copyOnWrite();
                ((ScanEvent) this.instance).setScanEventType(value);
                return this;
            }

            public Builder clearScanEventType() {
                copyOnWrite();
                ((ScanEvent) this.instance).clearScanEventType();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
            public boolean hasInitiator() {
                return ((ScanEvent) this.instance).hasInitiator();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
            public String getInitiator() {
                return ((ScanEvent) this.instance).getInitiator();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
            public ByteString getInitiatorBytes() {
                return ((ScanEvent) this.instance).getInitiatorBytes();
            }

            public Builder setInitiator(String value) {
                copyOnWrite();
                ((ScanEvent) this.instance).setInitiator(value);
                return this;
            }

            public Builder clearInitiator() {
                copyOnWrite();
                ((ScanEvent) this.instance).clearInitiator();
                return this;
            }

            public Builder setInitiatorBytes(ByteString value) {
                copyOnWrite();
                ((ScanEvent) this.instance).setInitiatorBytes(value);
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
            public boolean hasScanTechnologyType() {
                return ((ScanEvent) this.instance).hasScanTechnologyType();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
            public ScanTechnologyType getScanTechnologyType() {
                return ((ScanEvent) this.instance).getScanTechnologyType();
            }

            public Builder setScanTechnologyType(ScanTechnologyType value) {
                copyOnWrite();
                ((ScanEvent) this.instance).setScanTechnologyType(value);
                return this;
            }

            public Builder clearScanTechnologyType() {
                copyOnWrite();
                ((ScanEvent) this.instance).clearScanTechnologyType();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
            public boolean hasNumberResults() {
                return ((ScanEvent) this.instance).hasNumberResults();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
            public int getNumberResults() {
                return ((ScanEvent) this.instance).getNumberResults();
            }

            public Builder setNumberResults(int value) {
                copyOnWrite();
                ((ScanEvent) this.instance).setNumberResults(value);
                return this;
            }

            public Builder clearNumberResults() {
                copyOnWrite();
                ((ScanEvent) this.instance).clearNumberResults();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
            public boolean hasEventTimeMillis() {
                return ((ScanEvent) this.instance).hasEventTimeMillis();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.ScanEventOrBuilder
            public long getEventTimeMillis() {
                return ((ScanEvent) this.instance).getEventTimeMillis();
            }

            public Builder setEventTimeMillis(long value) {
                copyOnWrite();
                ((ScanEvent) this.instance).setEventTimeMillis(value);
                return this;
            }

            public Builder clearEventTimeMillis() {
                copyOnWrite();
                ((ScanEvent) this.instance).clearEventTimeMillis();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new ScanEvent();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "scanEventType_", ScanEventType.internalGetVerifier(), "initiator_", "scanTechnologyType_", ScanTechnologyType.internalGetVerifier(), "numberResults_", "eventTimeMillis_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\f\u0000\u0002\b\u0001\u0003\f\u0002\u0004\u0004\u0003\u0005\u0002\u0004", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<ScanEvent> parser = PARSER;
                    if (parser == null) {
                        synchronized (ScanEvent.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            ScanEvent defaultInstance = new ScanEvent();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(ScanEvent.class, defaultInstance);
        }

        public static ScanEvent getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ScanEvent> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ProfileConnectionStats extends GeneratedMessageLite<ProfileConnectionStats, Builder> implements ProfileConnectionStatsOrBuilder {
        private static final ProfileConnectionStats DEFAULT_INSTANCE;
        public static final int NUM_TIMES_CONNECTED_FIELD_NUMBER = 2;
        private static volatile Parser<ProfileConnectionStats> PARSER = null;
        public static final int PROFILE_ID_FIELD_NUMBER = 1;
        private int bitField0_;
        private int numTimesConnected_;
        private int profileId_;

        private ProfileConnectionStats() {
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.ProfileConnectionStatsOrBuilder
        public boolean hasProfileId() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.ProfileConnectionStatsOrBuilder
        public ProfileId getProfileId() {
            ProfileId result = ProfileId.forNumber(this.profileId_);
            return result == null ? ProfileId.PROFILE_UNKNOWN : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProfileId(ProfileId value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.profileId_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearProfileId() {
            this.bitField0_ &= -2;
            this.profileId_ = 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.ProfileConnectionStatsOrBuilder
        public boolean hasNumTimesConnected() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.ProfileConnectionStatsOrBuilder
        public int getNumTimesConnected() {
            return this.numTimesConnected_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNumTimesConnected(int value) {
            this.bitField0_ |= 2;
            this.numTimesConnected_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNumTimesConnected() {
            this.bitField0_ &= -3;
            this.numTimesConnected_ = 0;
        }

        public static ProfileConnectionStats parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (ProfileConnectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ProfileConnectionStats parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ProfileConnectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ProfileConnectionStats parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ProfileConnectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ProfileConnectionStats parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ProfileConnectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ProfileConnectionStats parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ProfileConnectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ProfileConnectionStats parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ProfileConnectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ProfileConnectionStats parseFrom(InputStream input) throws IOException {
            return (ProfileConnectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ProfileConnectionStats parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ProfileConnectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ProfileConnectionStats parseDelimitedFrom(InputStream input) throws IOException {
            return (ProfileConnectionStats) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ProfileConnectionStats parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ProfileConnectionStats) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ProfileConnectionStats parseFrom(CodedInputStream input) throws IOException {
            return (ProfileConnectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ProfileConnectionStats parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ProfileConnectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(ProfileConnectionStats prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ProfileConnectionStats, Builder> implements ProfileConnectionStatsOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(ProfileConnectionStats.DEFAULT_INSTANCE);
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.ProfileConnectionStatsOrBuilder
            public boolean hasProfileId() {
                return ((ProfileConnectionStats) this.instance).hasProfileId();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.ProfileConnectionStatsOrBuilder
            public ProfileId getProfileId() {
                return ((ProfileConnectionStats) this.instance).getProfileId();
            }

            public Builder setProfileId(ProfileId value) {
                copyOnWrite();
                ((ProfileConnectionStats) this.instance).setProfileId(value);
                return this;
            }

            public Builder clearProfileId() {
                copyOnWrite();
                ((ProfileConnectionStats) this.instance).clearProfileId();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.ProfileConnectionStatsOrBuilder
            public boolean hasNumTimesConnected() {
                return ((ProfileConnectionStats) this.instance).hasNumTimesConnected();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.ProfileConnectionStatsOrBuilder
            public int getNumTimesConnected() {
                return ((ProfileConnectionStats) this.instance).getNumTimesConnected();
            }

            public Builder setNumTimesConnected(int value) {
                copyOnWrite();
                ((ProfileConnectionStats) this.instance).setNumTimesConnected(value);
                return this;
            }

            public Builder clearNumTimesConnected() {
                copyOnWrite();
                ((ProfileConnectionStats) this.instance).clearNumTimesConnected();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new ProfileConnectionStats();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "profileId_", ProfileId.internalGetVerifier(), "numTimesConnected_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0000\u0002\u0004\u0001", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<ProfileConnectionStats> parser = PARSER;
                    if (parser == null) {
                        synchronized (ProfileConnectionStats.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            ProfileConnectionStats defaultInstance = new ProfileConnectionStats();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(ProfileConnectionStats.class, defaultInstance);
        }

        public static ProfileConnectionStats getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ProfileConnectionStats> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class HeadsetProfileConnectionStats extends GeneratedMessageLite<HeadsetProfileConnectionStats, Builder> implements HeadsetProfileConnectionStatsOrBuilder {
        private static final HeadsetProfileConnectionStats DEFAULT_INSTANCE;
        public static final int HEADSET_PROFILE_TYPE_FIELD_NUMBER = 1;
        public static final int NUM_TIMES_CONNECTED_FIELD_NUMBER = 2;
        private static volatile Parser<HeadsetProfileConnectionStats> PARSER;
        private int bitField0_;
        private int headsetProfileType_;
        private int numTimesConnected_;

        private HeadsetProfileConnectionStats() {
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.HeadsetProfileConnectionStatsOrBuilder
        public boolean hasHeadsetProfileType() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.HeadsetProfileConnectionStatsOrBuilder
        public HeadsetProfileType getHeadsetProfileType() {
            HeadsetProfileType result = HeadsetProfileType.forNumber(this.headsetProfileType_);
            return result == null ? HeadsetProfileType.HEADSET_PROFILE_UNKNOWN : result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeadsetProfileType(HeadsetProfileType value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.headsetProfileType_ = value.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHeadsetProfileType() {
            this.bitField0_ &= -2;
            this.headsetProfileType_ = 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.HeadsetProfileConnectionStatsOrBuilder
        public boolean hasNumTimesConnected() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.android.bluetooth.BluetoothMetricsProto.HeadsetProfileConnectionStatsOrBuilder
        public int getNumTimesConnected() {
            return this.numTimesConnected_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNumTimesConnected(int value) {
            this.bitField0_ |= 2;
            this.numTimesConnected_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNumTimesConnected() {
            this.bitField0_ &= -3;
            this.numTimesConnected_ = 0;
        }

        public static HeadsetProfileConnectionStats parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (HeadsetProfileConnectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static HeadsetProfileConnectionStats parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (HeadsetProfileConnectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static HeadsetProfileConnectionStats parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (HeadsetProfileConnectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static HeadsetProfileConnectionStats parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (HeadsetProfileConnectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static HeadsetProfileConnectionStats parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (HeadsetProfileConnectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static HeadsetProfileConnectionStats parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (HeadsetProfileConnectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static HeadsetProfileConnectionStats parseFrom(InputStream input) throws IOException {
            return (HeadsetProfileConnectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static HeadsetProfileConnectionStats parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (HeadsetProfileConnectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static HeadsetProfileConnectionStats parseDelimitedFrom(InputStream input) throws IOException {
            return (HeadsetProfileConnectionStats) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static HeadsetProfileConnectionStats parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (HeadsetProfileConnectionStats) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static HeadsetProfileConnectionStats parseFrom(CodedInputStream input) throws IOException {
            return (HeadsetProfileConnectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static HeadsetProfileConnectionStats parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (HeadsetProfileConnectionStats) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(HeadsetProfileConnectionStats prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<HeadsetProfileConnectionStats, Builder> implements HeadsetProfileConnectionStatsOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(HeadsetProfileConnectionStats.DEFAULT_INSTANCE);
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.HeadsetProfileConnectionStatsOrBuilder
            public boolean hasHeadsetProfileType() {
                return ((HeadsetProfileConnectionStats) this.instance).hasHeadsetProfileType();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.HeadsetProfileConnectionStatsOrBuilder
            public HeadsetProfileType getHeadsetProfileType() {
                return ((HeadsetProfileConnectionStats) this.instance).getHeadsetProfileType();
            }

            public Builder setHeadsetProfileType(HeadsetProfileType value) {
                copyOnWrite();
                ((HeadsetProfileConnectionStats) this.instance).setHeadsetProfileType(value);
                return this;
            }

            public Builder clearHeadsetProfileType() {
                copyOnWrite();
                ((HeadsetProfileConnectionStats) this.instance).clearHeadsetProfileType();
                return this;
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.HeadsetProfileConnectionStatsOrBuilder
            public boolean hasNumTimesConnected() {
                return ((HeadsetProfileConnectionStats) this.instance).hasNumTimesConnected();
            }

            @Override // com.android.bluetooth.BluetoothMetricsProto.HeadsetProfileConnectionStatsOrBuilder
            public int getNumTimesConnected() {
                return ((HeadsetProfileConnectionStats) this.instance).getNumTimesConnected();
            }

            public Builder setNumTimesConnected(int value) {
                copyOnWrite();
                ((HeadsetProfileConnectionStats) this.instance).setNumTimesConnected(value);
                return this;
            }

            public Builder clearNumTimesConnected() {
                copyOnWrite();
                ((HeadsetProfileConnectionStats) this.instance).clearNumTimesConnected();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new HeadsetProfileConnectionStats();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "headsetProfileType_", HeadsetProfileType.internalGetVerifier(), "numTimesConnected_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0000\u0002\u0004\u0001", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<HeadsetProfileConnectionStats> parser = PARSER;
                    if (parser == null) {
                        synchronized (HeadsetProfileConnectionStats.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            HeadsetProfileConnectionStats defaultInstance = new HeadsetProfileConnectionStats();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(HeadsetProfileConnectionStats.class, defaultInstance);
        }

        public static HeadsetProfileConnectionStats getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<HeadsetProfileConnectionStats> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
