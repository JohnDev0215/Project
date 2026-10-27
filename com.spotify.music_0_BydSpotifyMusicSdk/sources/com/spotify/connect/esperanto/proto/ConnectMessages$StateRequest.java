package com.spotify.connect.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.ox;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectMessages$StateRequest extends i implements yj1 {
    private static final ConnectMessages$StateRequest DEFAULT_INSTANCE;
    public static final int EXCLUDE_NON_LOCAL_DEVICES_FIELD_NUMBER = 4;
    public static final int INCLUDE_LOCAL_DEVICE_FIELD_NUMBER = 3;
    private static volatile ds1 PARSER = null;
    public static final int SHOW_OFFLINE_AMAZON_DEVICES_AS_ONLINE_FIELD_NUMBER = 2;
    public static final int SHOW_OFFLINE_DEVICES_IN_CORE_FIELD_NUMBER = 1;
    private boolean excludeNonLocalDevices_;
    private boolean includeLocalDevice_;
    private boolean showOfflineAmazonDevicesAsOnline_;
    private boolean showOfflineDevicesInCore_;

    static {
        ConnectMessages$StateRequest connectMessages$StateRequest = new ConnectMessages$StateRequest();
        DEFAULT_INSTANCE = connectMessages$StateRequest;
        i.registerDefaultInstance(ConnectMessages$StateRequest.class, connectMessages$StateRequest);
    }

    private ConnectMessages$StateRequest() {
    }

    public static void e(ConnectMessages$StateRequest connectMessages$StateRequest) {
        connectMessages$StateRequest.includeLocalDevice_ = true;
    }

    public static void f(ConnectMessages$StateRequest connectMessages$StateRequest) {
        connectMessages$StateRequest.excludeNonLocalDevices_ = true;
    }

    public static ox g() {
        return (ox) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0007\u0002\u0007\u0003\u0007\u0004\u0007", new Object[]{"showOfflineDevicesInCore_", "showOfflineAmazonDevicesAsOnline_", "includeLocalDevice_", "excludeNonLocalDevices_"});
            case 3:
                return new ConnectMessages$StateRequest();
            case 4:
                return new ox(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (ConnectMessages$StateRequest.class) {
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
