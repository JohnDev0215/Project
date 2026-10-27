package com.ts.carplay.linkadapter.iap;

import com.ts.carplay.common.data.CallStateInfo;
import com.ts.carplay.common.data.FavoriteContactListInfo;
import com.ts.carplay.common.data.NowPlayingInfo;
import com.ts.carplay.common.data.PlaybackMediaItemInfo;
import com.ts.carplay.common.data.RecentContactListInfo;
import com.ts.carplay.common.data.RouteGuidanceInfo;
import com.ts.carplay.linkadapter.iap.data.IapSessionStatus;
import com.ts.carplay.linkadapter.iap.data.WiredAttributes;
import com.ts.carplay.linkadapter.iap.data.WirelessAttributes;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IIapAdapterListener {
    default void onIapSessionStatusChanged(int clientType, IapSessionStatus status) {
    }

    default void onDeviceInformationUpdate(int clientType, String deviceName) {
    }

    default void onDeviceTransportIdentifierNotification(int clientType, String btTransportIdentifier, String usbTransportIdentifier) {
    }

    default void onRequestAccessoryWiFiConfigurationInformation(int clientType) {
    }

    default void onWirelessCarPlayUpdate(int clientType, boolean wirelessCarPlayAvailable) {
    }

    default void onStartLocationInformation(int clientType, int sentenceType) {
    }

    default void onStopLocationInformation(int clientType) {
    }

    default void onStartOobBtPairing(int clientType) {
    }

    default void onStopOobBtPairing(int clientType) {
    }

    default void onOobBtPairingLinkKeyInformation(int clientType, byte[] linkKey, byte[] devMacAddress) {
    }

    default void onRouteGuidanceUpdate(RouteGuidanceInfo info) {
    }

    default void onCallStateUpdate(CallStateInfo info) {
    }

    default void onRecentListCount(int listCount) {
    }

    default void onRecentListUpdate(List<RecentContactListInfo> list) {
    }

    default void onFavoriteListCount(int listCount) {
    }

    default void onFavoriteListUpdate(List<FavoriteContactListInfo> list) {
    }

    default void onNowPlayingUpdate(NowPlayingInfo info) {
    }

    default void onArtworkUpdate(NowPlayingInfo info) {
    }

    default void onGprmcDataStatus(int gprmcData) {
    }

    default void onPlaybackListUpdate(int mediaItemCount, int mediaItemStartIndex, List<PlaybackMediaItemInfo> mediaItemList) {
    }

    default void onBtA2dpConnect(boolean connected) {
    }

    default void onCarplayAvialability(int clientType, WiredAttributes wired, WirelessAttributes wireless) {
    }

    default int onStartBluetoothPairing(int clientType, short btTransComId, byte[] macAddress) {
        return 0;
    }

    default int onStopBluetoothPairing(int clientType, short btTransComId, int state) {
        return 0;
    }
}
