package com.spotify.connect.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.v51;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectMessages$StateResponse extends i implements yj1 {
    private static final ConnectMessages$StateResponse DEFAULT_INSTANCE;
    public static final int DEVICES_FIELD_NUMBER = 5;
    public static final int IS_ACTIVE_FIELD_NUMBER = 6;
    public static final int IS_ACTIVE_ON_SAME_DEVICE_FIELD_NUMBER = 7;
    public static final int IS_CONNECTED_FIELD_NUMBER = 2;
    public static final int IS_RUNNING_FIELD_NUMBER = 1;
    public static final int LOCAL_NAME_FIELD_NUMBER = 3;
    private static volatile ds1 PARSER = null;
    public static final int SHOULD_USE_LOCAL_PLAYBACK_FIELD_NUMBER = 4;
    private boolean isActiveOnSameDevice_;
    private boolean isActive_;
    private boolean isConnected_;
    private boolean isRunning_;
    private boolean shouldUseLocalPlayback_;
    private String localName_ = "";
    private v51 devices_ = i.emptyProtobufList();

    public static final class Device extends i implements yj1 {
        public static final int ALIAS_ID_FIELD_NUMBER = 35;
        public static final int AUDIO_OUTPUT_DEVICE_NAME_FIELD_NUMBER = 38;
        public static final int AUDIO_OUTPUT_DEVICE_TYPE_FIELD_NUMBER = 39;
        public static final int BRAND_DISPLAY_NAME_FIELD_NUMBER = 30;
        public static final int CAPABILITIES_FIELD_NUMBER = 33;
        public static final int CONNECT_STATE_IDENTIFIER_FIELD_NUMBER = 4;
        public static final int CREATION_TIME_MS_FIELD_NUMBER = 18;
        public static final int DEDUPLICATION_ID_FIELD_NUMBER = 3;
        private static final Device DEFAULT_INSTANCE;
        public static final int HIFI_FIELD_NUMBER = 20;
        public static final int INCARNATIONS_FIELD_NUMBER = 34;
        public static final int IS_ACTIVE_FIELD_NUMBER = 9;
        public static final int IS_BEING_ACTIVATED_FIELD_NUMBER = 10;
        public static final int IS_CONNECT_FIELD_NUMBER = 13;
        public static final int IS_DISABLED_FIELD_NUMBER = 8;
        public static final int IS_GROUP_FIELD_NUMBER = 15;
        public static final int IS_LOCAL_FIELD_NUMBER = 14;
        public static final int IS_NEWLY_DISCOVERED_FIELD_NUMBER = 28;
        public static final int IS_OFFLINE_FIELD_NUMBER = 16;
        public static final int IS_SELF_FIELD_NUMBER = 1;
        public static final int IS_SOCIAL_CONNECT_FIELD_NUMBER = 17;
        public static final int IS_VOICE_ENABLED_FIELD_NUMBER = 27;
        public static final int IS_WEBAPP_FIELD_NUMBER = 12;
        public static final int IS_ZEROCONF_FIELD_NUMBER = 11;
        public static final int LIBRARY_VERSION_FIELD_NUMBER = 37;
        public static final int LICENSE_FIELD_NUMBER = 21;
        public static final int LOCAL_IDENTIFIER_FIELD_NUMBER = 2;
        public static final int MODEL_DISPLAY_NAME_FIELD_NUMBER = 31;
        public static final int NAME_FIELD_NUMBER = 5;
        private static volatile ds1 PARSER = null;
        public static final int STATE_FIELD_NUMBER = 7;
        public static final int SUPPORTED_MEDIA_TYPES_FIELD_NUMBER = 29;
        public static final int SUPPORTS_ALARM_FIELD_NUMBER = 26;
        public static final int SUPPORTS_DJ_FIELD_NUMBER = 36;
        public static final int SUPPORTS_LOGOUT_FIELD_NUMBER = 24;
        public static final int SUPPORTS_ON_DEMAND_FIELD_NUMBER = 22;
        public static final int SUPPORTS_RENAME_FIELD_NUMBER = 25;
        public static final int SUPPORTS_VOLUME_FIELD_NUMBER = 23;
        public static final int TYPE_FIELD_NUMBER = 6;
        public static final int VOLUME_FIELD_NUMBER = 19;
        public static final int VOLUME_STEPS_FIELD_NUMBER = 32;
        private int aliasId_;
        private int bitField0_;
        private long creationTimeMs_;
        private Hifi hifi_;
        private boolean isActive_;
        private boolean isBeingActivated_;
        private boolean isConnect_;
        private boolean isDisabled_;
        private boolean isGroup_;
        private boolean isLocal_;
        private boolean isNewlyDiscovered_;
        private boolean isOffline_;
        private boolean isSelf_;
        private boolean isSocialConnect_;
        private boolean isVoiceEnabled_;
        private boolean isWebapp_;
        private boolean isZeroconf_;
        private boolean supportsAlarm_;
        private boolean supportsDj_;
        private boolean supportsLogout_;
        private boolean supportsOnDemand_;
        private boolean supportsRename_;
        private boolean supportsVolume_;
        private int volumeSteps_;
        private int volume_;
        private String localIdentifier_ = "";
        private String deduplicationId_ = "";
        private String connectStateIdentifier_ = "";
        private String name_ = "";
        private String type_ = "";
        private String state_ = "";
        private String license_ = "";
        private v51 supportedMediaTypes_ = i.emptyProtobufList();
        private String brandDisplayName_ = "";
        private String modelDisplayName_ = "";
        private String capabilities_ = "";
        private v51 incarnations_ = i.emptyProtobufList();
        private String libraryVersion_ = "";
        private String audioOutputDeviceName_ = "";
        private String audioOutputDeviceType_ = "";

        public static final class Hifi extends i implements yj1 {
            private static final Hifi DEFAULT_INSTANCE;
            public static final int DEVICE_SUPPORTED_FIELD_NUMBER = 3;
            public static final int FULLY_SUPPORTED_FIELD_NUMBER = 1;
            private static volatile ds1 PARSER = null;
            public static final int USER_ELIGIBLE_FIELD_NUMBER = 2;
            private boolean deviceSupported_;
            private boolean fullySupported_;
            private boolean userEligible_;

            static {
                Hifi hifi = new Hifi();
                DEFAULT_INSTANCE = hifi;
                i.registerDefaultInstance(Hifi.class, hifi);
            }

            private Hifi() {
            }

            public static ds1 parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            @Override // com.google.protobuf.i
            public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
                switch (generatedMessageLite$MethodToInvoke.ordinal()) {
                    case 0:
                        return (byte) 1;
                    case 1:
                        return null;
                    case 2:
                        return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0007\u0002\u0007\u0003\u0007", new Object[]{"fullySupported_", "userEligible_", "deviceSupported_"});
                    case 3:
                        return new Hifi();
                    case 4:
                        return new c(DEFAULT_INSTANCE);
                    case 5:
                        return DEFAULT_INSTANCE;
                    case 6:
                        ds1 i01Var = PARSER;
                        if (i01Var == null) {
                            synchronized (Hifi.class) {
                                try {
                                    i01Var = PARSER;
                                    if (i01Var == null) {
                                        i01Var = new i01(DEFAULT_INSTANCE);
                                        PARSER = i01Var;
                                    }
                                } finally {
                                }
                                break;
                            }
                        }
                        return i01Var;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            @Override // com.google.protobuf.i, p.yj1
            public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.google.protobuf.i, p.wj1
            public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
                return super.newBuilderForType();
            }
        }

        public static final class Incarnation extends i implements yj1 {
            private static final Incarnation DEFAULT_INSTANCE;
            public static final int IDENTIFIER_FIELD_NUMBER = 2;
            public static final int LOGIN_TYPE_FIELD_NUMBER = 1;
            private static volatile ds1 PARSER = null;
            public static final int PREFERRED_FIELD_NUMBER = 3;
            private String identifier_ = "";
            private int loginType_;
            private boolean preferred_;

            static {
                Incarnation incarnation = new Incarnation();
                DEFAULT_INSTANCE = incarnation;
                i.registerDefaultInstance(Incarnation.class, incarnation);
            }

            private Incarnation() {
            }

            public static ds1 parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            @Override // com.google.protobuf.i
            public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
                switch (generatedMessageLite$MethodToInvoke.ordinal()) {
                    case 0:
                        return (byte) 1;
                    case 1:
                        return null;
                    case 2:
                        return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0002Ȉ\u0003\u0007", new Object[]{"loginType_", "identifier_", "preferred_"});
                    case 3:
                        return new Incarnation();
                    case 4:
                        return new d(DEFAULT_INSTANCE);
                    case 5:
                        return DEFAULT_INSTANCE;
                    case 6:
                        ds1 i01Var = PARSER;
                        if (i01Var == null) {
                            synchronized (Incarnation.class) {
                                try {
                                    i01Var = PARSER;
                                    if (i01Var == null) {
                                        i01Var = new i01(DEFAULT_INSTANCE);
                                        PARSER = i01Var;
                                    }
                                } finally {
                                }
                                break;
                            }
                        }
                        return i01Var;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            @Override // com.google.protobuf.i, p.yj1
            public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.google.protobuf.i, p.wj1
            public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
                return super.newBuilderForType();
            }
        }

        static {
            Device device = new Device();
            DEFAULT_INSTANCE = device;
            i.registerDefaultInstance(Device.class, device);
        }

        private Device() {
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        @Override // com.google.protobuf.i
        public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
            switch (generatedMessageLite$MethodToInvoke.ordinal()) {
                case 0:
                    return (byte) 1;
                case 1:
                    return null;
                case 2:
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000'\u0000\u0001\u0001''\u0000\u0002\u0000\u0001\u0007\u0002Ȉ\u0003Ȉ\u0004Ȉ\u0005Ȉ\u0006Ȉ\u0007Ȉ\b\u0007\t\u0007\n\u0007\u000b\u0007\f\u0007\r\u0007\u000e\u0007\u000f\u0007\u0010\u0007\u0011\u0007\u0012\u0003\u0013\u000b\u0014\t\u0015Ȉ\u0016\u0007\u0017\u0007\u0018\u0007\u0019\u0007\u001a\u0007\u001b\u0007\u001c\u0007\u001dȚ\u001eȈ\u001fȈ ဋ\u0000!ለ\u0001\"\u001b#ဋ\u0002$ဇ\u0003%Ȉ&ለ\u0004'ለ\u0005", new Object[]{"bitField0_", "isSelf_", "localIdentifier_", "deduplicationId_", "connectStateIdentifier_", "name_", "type_", "state_", "isDisabled_", "isActive_", "isBeingActivated_", "isZeroconf_", "isWebapp_", "isConnect_", "isLocal_", "isGroup_", "isOffline_", "isSocialConnect_", "creationTimeMs_", "volume_", "hifi_", "license_", "supportsOnDemand_", "supportsVolume_", "supportsLogout_", "supportsRename_", "supportsAlarm_", "isVoiceEnabled_", "isNewlyDiscovered_", "supportedMediaTypes_", "brandDisplayName_", "modelDisplayName_", "volumeSteps_", "capabilities_", "incarnations_", Incarnation.class, "aliasId_", "supportsDj_", "libraryVersion_", "audioOutputDeviceName_", "audioOutputDeviceType_"});
                case 3:
                    return new Device();
                case 4:
                    return new b(DEFAULT_INSTANCE);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (Device.class) {
                            try {
                                i01Var = PARSER;
                                if (i01Var == null) {
                                    i01Var = new i01(DEFAULT_INSTANCE);
                                    PARSER = i01Var;
                                }
                            } finally {
                            }
                            break;
                        }
                    }
                    return i01Var;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String e() {
            return this.connectStateIdentifier_;
        }

        public final boolean f() {
            return this.isActive_;
        }

        public final boolean g() {
            return this.isSelf_;
        }

        @Override // com.google.protobuf.i, p.yj1
        public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        public final String getName() {
            return this.name_;
        }

        public final String getType() {
            return this.type_;
        }

        public final String h() {
            return this.localIdentifier_;
        }

        @Override // com.google.protobuf.i, p.wj1
        public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }
    }

    static {
        ConnectMessages$StateResponse connectMessages$StateResponse = new ConnectMessages$StateResponse();
        DEFAULT_INSTANCE = connectMessages$StateResponse;
        i.registerDefaultInstance(ConnectMessages$StateResponse.class, connectMessages$StateResponse);
    }

    private ConnectMessages$StateResponse() {
    }

    public static ConnectMessages$StateResponse j(byte[] bArr) {
        return (ConnectMessages$StateResponse) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0007\u0000\u0000\u0001\u0007\u0007\u0000\u0001\u0000\u0001\u0007\u0002\u0007\u0003Ȉ\u0004\u0007\u0005\u001b\u0006\u0007\u0007\u0007", new Object[]{"isRunning_", "isConnected_", "localName_", "shouldUseLocalPlayback_", "devices_", Device.class, "isActive_", "isActiveOnSameDevice_"});
            case 3:
                return new ConnectMessages$StateResponse();
            case 4:
                return new a(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (ConnectMessages$StateResponse.class) {
                        try {
                            i01Var = PARSER;
                            if (i01Var == null) {
                                i01Var = new i01(DEFAULT_INSTANCE);
                                PARSER = i01Var;
                            }
                        } finally {
                        }
                        break;
                    }
                }
                return i01Var;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public final v51 e() {
        return this.devices_;
    }

    public final boolean f() {
        return this.isActive_;
    }

    public final boolean g() {
        return this.isActiveOnSameDevice_;
    }

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final boolean h() {
        return this.isConnected_;
    }

    public final boolean i() {
        return this.shouldUseLocalPlayback_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
