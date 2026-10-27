package com.ecarx.xui.adaptapi.diminteraction;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IVrInteraction {
    public static final int ACTION_BUTTON_DOWN = 10;
    public static final int ACTION_BUTTON_LEFT = 7;
    public static final int ACTION_BUTTON_RIGHT = 8;
    public static final int ACTION_BUTTON_UP = 9;
    public static final int ACTION_SEARCH_ALL_CONTACTS = 4;
    public static final int ACTION_SEARCH_CALL_LOG = 3;
    public static final int ACTION_SEARCH_CONTACTS = 1;
    public static final int ACTION_SEARCH_FAV_CONTACTS = 2;
    public static final int ACTION_SEARCH_NAVI_ADDRESS = 5;
    public static final int ACTION_VOICE_TO_TEXT = 6;
    public static final int SEARCH_STATUS_COMPLETED = 2;
    public static final int SEARCH_STATUS_NOT_RECOGNIZED = 4;
    public static final int SEARCH_STATUS_ONGOING = 1;
    public static final int SEARCH_STATUS_TIME_OUT = 3;
    public static final int VR_STATUS_IDLE = 0;
    public static final int VR_STATUS_LISTENING = 1;
    public static final int VR_STATUS_PROCESSING = 2;
    public static final int VR_STATUS_PROMTING = 4;
    public static final int VR_STATUS_WAITING = Integer.MIN_VALUE;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ActionType {
    }

    public interface IVrInteractionCallback {
        void onDoInteractionAction(int i);

        void onVrInfoUpdateReqired();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VrSearchStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VrStatus {
    }

    void notifyVoiceToTextCompleted(String str);

    void notifyVrSearchStates(int i);

    void notifyVrStatusChanged(int i, int i2);

    void notifyVrTTSText(String str);

    void registerVrCallback(IVrInteractionCallback iVrInteractionCallback);

    void unRegisterVrCallback(IVrInteractionCallback iVrInteractionCallback);
}
