package com.byd.vrassistant.manager;

import cerence.ark.assistant.api.context.grpc.NavigationContextGrpc;
import cerence.ark.assistant.api.context.http.NavigationContext;
import com.byd.vrassistant.controller.DemoType;
import com.byd.vrassistant.utils.CarModeUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DataManager {
    public static final int BIT_1_LEFT = 1;
    public static final int BIT_1_RIGHT = 2;
    public static final int BIT_2_LEFT = 4;
    public static final int BIT_2_RIGHT = 8;
    public static final int BIT_ALL_SEAT = 15;
    public static String SEAT_ROW_1_LEFT = "SEAT_ROW_1_LEFT";
    public static String SEAT_ROW_1_RIGHT = "SEAT_ROW_1_RIGHT";
    public static String SEAT_ROW_2_LEFT = "SEAT_ROW_2_LEFT";
    public static String SEAT_ROW_2_RIGHT = "SEAT_ROW_2_RIGHT";
    private NavigationContext.Builder mNavigationContextBuilder;
    private NavigationContextGrpc.Builder mNavigationContextGrpcBuilder;
    private boolean mIsEnableWordStreaming = false;
    private boolean mIsEnableDLDH = false;
    private DemoType type = DemoType.simple;

    public boolean getIsEnableDLDH() {
        return this.mIsEnableDLDH;
    }

    public void setIsEnableDLDH(boolean z) {
        this.mIsEnableDLDH = z;
    }

    private static final class SingletonHolder {
        private static final DataManager INSTANCE = new DataManager();

        private SingletonHolder() {
        }
    }

    public static DataManager get() {
        return SingletonHolder.INSTANCE;
    }

    public boolean getEnableWordStreaming() {
        return this.mIsEnableWordStreaming;
    }

    public void setEnableWordStreaming(boolean z) {
        this.mIsEnableWordStreaming = z;
    }

    public NavigationContext.Builder getNavigationContextBuilder() {
        if (this.mNavigationContextBuilder == null) {
            this.mNavigationContextBuilder = NavigationContext.builder();
        }
        return this.mNavigationContextBuilder;
    }

    public NavigationContextGrpc.Builder getNavigationContextGrpcBuilder() {
        if (this.mNavigationContextGrpcBuilder == null) {
            this.mNavigationContextGrpcBuilder = NavigationContextGrpc.builder();
        }
        return this.mNavigationContextGrpcBuilder;
    }

    public DemoType getType() {
        return this.type;
    }

    public void setType(DemoType demoType) {
        this.type = demoType;
    }

    public static List<String> getEnableAudioZones(boolean z) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(SEAT_ROW_1_LEFT);
        if (z) {
            arrayList.add(SEAT_ROW_1_RIGHT);
            if (CarModeUtils.getZoneType().equals("1")) {
                arrayList.add(SEAT_ROW_2_LEFT);
                arrayList.add(SEAT_ROW_2_RIGHT);
            }
        }
        return arrayList;
    }

    public static List<String> getEnableAudioZones(int i) {
        ArrayList arrayList = new ArrayList();
        if ((i & 1) != 0) {
            arrayList.add(SEAT_ROW_1_LEFT);
        }
        if ((i & 2) != 0) {
            arrayList.add(SEAT_ROW_1_RIGHT);
        }
        if (CarModeUtils.getZoneType().equals("1")) {
            if ((i & 4) != 0) {
                arrayList.add(SEAT_ROW_2_LEFT);
            }
            if ((i & 8) != 0) {
                arrayList.add(SEAT_ROW_2_RIGHT);
            }
        }
        return arrayList;
    }
}
