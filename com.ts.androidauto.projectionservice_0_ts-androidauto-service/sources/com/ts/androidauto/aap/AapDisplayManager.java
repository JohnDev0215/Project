package com.ts.androidauto.aap;

import com.ts.androidauto.aap.data.MediaMetadata;
import com.ts.androidauto.aap.data.NavigationPositionData;
import com.ts.androidauto.aap.data.NavigationStateData;
import com.ts.androidauto.aap.data.PhoneStatusData;
import com.ts.androidauto.fsdk.common.LogUtil;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AapDisplayManager {
    private static final String TAG = "AapDisplayManager";
    private static AapDisplayManager mAapDisplayManager;
    private AapDisPlayCallback mDisPlayCallback = null;

    public static synchronized AapDisplayManager getInstance() {
        if (mAapDisplayManager == null) {
            mAapDisplayManager = new AapDisplayManager();
        }
        return mAapDisplayManager;
    }

    public void registDisPlayCallback(AapDisPlayCallback disPlayCallback) {
        this.mDisPlayCallback = disPlayCallback;
    }

    public void unRegistDisPlayCallback() {
        this.mDisPlayCallback = null;
    }

    public void updateNaviStatus(int status) {
        LogUtil.verbose(TAG, "updateNaviStatus status:" + status);
        AapDisPlayCallback aapDisPlayCallback = this.mDisPlayCallback;
        if (aapDisPlayCallback != null) {
            aapDisPlayCallback.onStatus(status);
        }
    }

    public void onNavigationState(NavigationStateData navigationStateData) {
        LogUtil.info(TAG, "updateNavigationState navigationStateData:" + navigationStateData);
        AapDisPlayCallback aapDisPlayCallback = this.mDisPlayCallback;
        if (aapDisPlayCallback != null) {
            aapDisPlayCallback.onNavigationState(navigationStateData);
        }
    }

    public void onNavigationCurrentPosition(NavigationPositionData navigationPositionData) {
        LogUtil.info(TAG, "updateNaviCurrentPosition navigationPositionData:" + navigationPositionData);
        AapDisPlayCallback aapDisPlayCallback = this.mDisPlayCallback;
        if (aapDisPlayCallback != null) {
            aapDisPlayCallback.onNavigationCurrentPosition(navigationPositionData);
        }
    }

    public void updateMediaStatus(int mediaStatus) {
        LogUtil.verbose(TAG, "updateMediaStatus mediaStatus: " + mediaStatus);
        AapDisPlayCallback aapDisPlayCallback = this.mDisPlayCallback;
        if (aapDisPlayCallback != null) {
            aapDisPlayCallback.notifyMediaStatusChange(mediaStatus);
        }
    }

    public void notifyMediaProgressChange(int playbackSeconds) {
        LogUtil.verbose(TAG, "notifyMediaProgressChange playbackSeconds: " + playbackSeconds);
        AapDisPlayCallback aapDisPlayCallback = this.mDisPlayCallback;
        if (aapDisPlayCallback != null) {
            aapDisPlayCallback.notifyMediaProgressChange(playbackSeconds);
        }
    }

    public void notifyMediaMetadataChange(MediaMetadata mediaMetadata) {
        AapDisPlayCallback aapDisPlayCallback = this.mDisPlayCallback;
        if (aapDisPlayCallback != null) {
            aapDisPlayCallback.notifyMediaMetadataChange(mediaMetadata);
        }
    }

    public void updatePhoneStatus(ArrayList<PhoneStatusData> phoneStatusList) {
        AapDisPlayCallback aapDisPlayCallback = this.mDisPlayCallback;
        if (aapDisPlayCallback != null) {
            aapDisPlayCallback.notifyPhoneStatus(phoneStatusList);
        }
    }
}
