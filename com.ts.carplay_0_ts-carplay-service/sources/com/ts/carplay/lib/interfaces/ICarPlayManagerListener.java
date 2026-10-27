package com.ts.carplay.lib.interfaces;

import com.ts.carplay.common.data.CallStateInfo;
import com.ts.carplay.common.data.CarPlayDevice;
import com.ts.carplay.common.data.FavoriteContactListInfo;
import com.ts.carplay.common.data.NowPlayingInfo;
import com.ts.carplay.common.data.PlaybackMediaItemInfo;
import com.ts.carplay.common.data.RecentContactListInfo;
import com.ts.carplay.common.data.RouteGuidanceInfo;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ICarPlayManagerListener {
    void onAltRequestSetup();

    void onArtworkUpdate(NowPlayingInfo nowPlayingInfo);

    void onCallStateUpdate(CallStateInfo callStateInfo);

    void onCarPlayAppStateChanged(int i, int i2, int i3);

    void onCarPlayAppStateChanged(int i, boolean z, int i2);

    void onCarPlaySessionStateChanged(int i);

    void onCarPlaySessionStateChanged(int i, boolean z, String str);

    void onCarPlaySessionStateChanged(int i, boolean z, String str, boolean z2);

    void onFavoriteListCount(int i);

    void onFavoriteListUpdate(List<FavoriteContactListInfo> list);

    void onNowPlayingUpdate(NowPlayingInfo nowPlayingInfo);

    void onRecentListCount(int i);

    void onRecentListUpdate(List<RecentContactListInfo> list);

    void onRequestVideoFocus(int i);

    void onRequestViewArea(int i);

    void onRouteGuidanceUpdate(RouteGuidanceInfo routeGuidanceInfo);

    void oniPhoneAttachedOverUsb(CarPlayDevice carPlayDevice, boolean z);

    void oniPhoneDetachedOverUsb(CarPlayDevice carPlayDevice);

    default void onEnhancedSiriParamsUpdate(int inMode, String inLanguage) {
    }

    default void onAuxInUpdateState(int state) {
    }

    default void onSuggestUi(int url) {
    }

    default void onCarPlayDisplayStatusChanged(boolean isShow) {
    }

    default void onCarPlayMediaFocusState(boolean isCarPlayMedia) {
    }

    default void onSupportFeaturesUpdate(int supportsFeatures) {
    }

    default void onPlaybackListUpdate(int mediaItemCount, int mediaItemStartIndex, List<PlaybackMediaItemInfo> mediaItemList) {
    }

    default void onRequestProjection(CarPlayDevice device, int type) {
    }

    default void onWirelessCarPlayAvailabilityChanged(String macAddress, boolean enabled) {
    }

    default void onDeviceMerged(CarPlayDevice device) {
    }

    default void onCarPlayDeviceStatusChanged(CarPlayDevice device) {
    }

    default void onCarPlayOobStartEvent(byte[] macAddress) {
    }
}
