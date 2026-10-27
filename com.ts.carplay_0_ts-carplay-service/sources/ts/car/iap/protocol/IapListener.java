package ts.car.iap.protocol;

import android.util.Log;
import java.util.ArrayList;
import ts.car.iap.protocol.IapController;
import ts.car.iap.protocol.common.CallStateUpdate;
import ts.car.iap.protocol.common.CarPlayAppList;
import ts.car.iap.protocol.common.CarplayAppDiscoveryAppIcon;
import ts.car.iap.protocol.common.CarplayAppDiscoveryAppIconData;
import ts.car.iap.protocol.common.CarplayAvialability;
import ts.car.iap.protocol.common.CommunicationsUpdate;
import ts.car.iap.protocol.common.DestinationInformation;
import ts.car.iap.protocol.common.FavoriteListInfo;
import ts.car.iap.protocol.common.IapSessionStatus;
import ts.car.iap.protocol.common.LaneGuidanceInformation;
import ts.car.iap.protocol.common.MediaLibraryUpdate;
import ts.car.iap.protocol.common.NowPlayingPbMediaItemInfo;
import ts.car.iap.protocol.common.NowPlayingUpdate;
import ts.car.iap.protocol.common.PairingState;
import ts.car.iap.protocol.common.PowerUpdate;
import ts.car.iap.protocol.common.RecentListInfo;
import ts.car.iap.protocol.common.RouteGuidanceManeuverUpdate;
import ts.car.iap.protocol.common.RouteGuidanceUpdate;

/* JADX INFO: loaded from: classes.dex */
public interface IapListener {
    default int onIapSessionStatusChanged(IapController.IapSession session, IapSessionStatus status) {
        Log.i("IapListener", "onIapSessionStatusChanged: No customer callback registered!");
        return 0;
    }

    default int onDeviceInformationUpdate(IapController.IapSession session, String deviceName) {
        Log.i("IapListener", "onDeviceInformationUpdate: No customer callback registered!");
        return 0;
    }

    default int onDeviceTimeUpdate(IapController.IapSession session, long secondsSinceReferenceDate, int timeZoneOffsetMinutes, int daylightSavingsOffsetMinutes) {
        Log.i("IapListener", "onDeviceTimeUpdate: No customer callback registered!");
        return 0;
    }

    default int onDeviceUuidUpdate(IapController.IapSession session, String uuid) {
        Log.i("IapListener", "onDeviceUuidUpdate: No customer callback registered!");
        return 0;
    }

    default int onWirelessCarPlayUpdate(IapController.IapSession session, boolean wirelessCarPlayAvailable) {
        Log.i("IapListener", "onWirelessCarPlayUpdate: No customer callback registered!");
        return 0;
    }

    default int onDeviceTransportIdentifierNotification(IapController.IapSession session, String btTransportIdentifier, String usbTransportIdentifier) {
        Log.i("IapListener", "onDeviceTransportIdentifierNotification: No customer callback registered!");
        return 0;
    }

    default int onPowerUpdate(IapController.IapSession session, PowerUpdate update) {
        Log.i("IapListener", "onPowerUpdate: No customer callback registered!");
        return 0;
    }

    default int onBluetoothConnectionUpdate(IapController.IapSession session, int connectedBtProfiles) {
        Log.i("IapListener", "onBluetoothConnectionUpdate: No customer callback registered!");
        return 0;
    }

    default int onMediaLibraryUpdate(IapController.IapSession session, MediaLibraryUpdate update) {
        Log.i("IapListener", "onMediaLibraryUpdate: No customer callback registered!");
        return 0;
    }

    default int onMediaPlaylistUpdate(IapController.IapSession session, String plPid, String[] pids) {
        Log.i("IapListener", "onMediaPlaylistUpdate: No customer callback registered!");
        return 0;
    }

    default int onNowPlayingUpdate(IapController.IapSession session, NowPlayingUpdate update) {
        Log.i("IapListener", "onNowPlayingUpdate: No customer callback registered!");
        return 0;
    }

    default int onPlaybackListUpdate(IapController.IapSession session, boolean isAvailable, String[] pids) {
        Log.i("IapListener", "onPlaybackListUpdate: No customer callback registered!");
        return 0;
    }

    default int onArtworkUpdate(IapController.IapSession session, String artworkFilePath) {
        Log.i("IapListener", "onArtworkUpdate: No customer callback registered!");
        return 0;
    }

    default int onArtworkInfoUpdate(IapController.IapSession session, int artworkSize, byte[] artworkData) {
        Log.i("IapListener", "onArtworkInfoUpdate: No customer callback registered!");
        return 0;
    }

    default int onUsbDeviceModeAudioInformation(IapController.IapSession session, int sampleRate) {
        Log.i("IapListener", "onUsbDeviceModeAudioInformation: No customer callback registered!");
        return 0;
    }

    default int onCallStateUpdate(IapController.IapSession session, CallStateUpdate update) {
        Log.i("IapListener", "onCallStateUpdate: No customer callback registered!");
        return 0;
    }

    default int onCommunicationsUpdate(IapController.IapSession session, CommunicationsUpdate update) {
        Log.i("IapListener", "onCommunicationsUpdate: No customer callback registered!");
        return 0;
    }

    default int onStartLocationInformation(IapController.IapSession session, int sentenceType) {
        Log.i("IapListener", "onStartLocationInformation: No customer callback registered!");
        return 0;
    }

    default int onStopLocationInformation(IapController.IapSession session) {
        Log.i("IapListener", "onStopLocationInformation: No customer callback registered!");
        return 0;
    }

    default int onStartOobBtPairing(IapController.IapSession session) {
        Log.i("IapListener", "onStartOobBtPairing: No customer callback registered!");
        return 0;
    }

    default int onOobBtPairingLinkKeyInformation(IapController.IapSession session, byte[] linkKey, byte[] devMacAddress) {
        Log.i("IapListener", "onOobBtPairingLinkKeyInformation: No customer callback registered!");
        return 0;
    }

    default int onStopOobBtPairing(IapController.IapSession session) {
        Log.i("IapListener", "onStopOobBtPairing: No customer callback registered!");
        return 0;
    }

    default int onRequestAccessoryWiFiConfigurationInformation(IapController.IapSession session) {
        Log.i("IapListener", "onRequestAccessoryWiFiConfigInfo: No customer callback registered!");
        return 0;
    }

    default int onRouteGuidanceUpdate(IapController.IapSession session, RouteGuidanceUpdate update) {
        Log.i("IapListener", "onRouteGuidanceUpdate: No customer callback registered!");
        return 0;
    }

    default int onRouteGuidanceManeuverUpdate(IapController.IapSession session, RouteGuidanceManeuverUpdate update) {
        Log.i("IapListener", "onRouteGuidanceManeuverUpdate: No customer callback registered!");
        return 0;
    }

    default int onLaneGuidanceInformation(IapController.IapSession session, LaneGuidanceInformation info) {
        Log.i("IapListener", "LaneGuidanceInformation: No customer callback registered!");
        return 0;
    }

    default int onDestinationInformation(IapController.IapSession session, DestinationInformation info) {
        Log.i("IapListener", "onDestinationInformation: No customer callback registered!");
        return 0;
    }

    default int onStartVehicleStatusUpdates(IapController.IapSession session, int startVehicleStatusUpdateType) {
        Log.i("IapListener", "onStartVehicleStatusUpdatesn: No customer callback registered!");
        return 0;
    }

    default int onStopVehicleStatusUpdates(IapController.IapSession session) {
        Log.i("IapListener", "onStopVehicleStatusUpdates: No customer callback registered!");
        return 0;
    }

    default int onStartEapSession(IapController.IapSession session, int eapId, int sessionId) {
        Log.i("IapListener", "onStartEapSession: No customer callback registered!");
        return 0;
    }

    default int onReceiveEapData(IapController.IapSession session, int sessionId, byte[] data) {
        Log.i("IapListener", "onReceiveEapData: No customer callback registered!");
        return 0;
    }

    default int onStopEapSession(IapController.IapSession session, int sessionId) {
        Log.i("IapListener", "onStopEapSession: No customer callback registered!");
        return 0;
    }

    default int onRecentListAvailable(IapController.IapSession session, int isAvailable, int listCount) {
        Log.i("IapListener", "onRecentListAvailable: No customer callback registered!");
        return 0;
    }

    default int onFavoriteListAvailable(IapController.IapSession session, int isAvailable, int listCount) {
        Log.i("IapListener", "onFavoriteListAvailable: No customer callback registered!");
        return 0;
    }

    default int onRecentListUpdate(IapController.IapSession session, ArrayList<RecentListInfo> list) {
        Log.i("IapListener", "onRecentListUpdate: No customer callback registered!");
        return 0;
    }

    default int onFavoriteListUpdate(IapController.IapSession session, ArrayList<FavoriteListInfo> list) {
        Log.i("IapListener", "onFavoriteListUpdate: No customer callback registered!");
        return 0;
    }

    default int onPlaybackListMeiaItemInfoUpdate(IapController.IapSession session, int mediaItemCount, int mediaItemStartIndex, ArrayList<NowPlayingPbMediaItemInfo> mediaItemList) {
        Log.i("IapListener", "onPlaybackListMeiaItemInfoUpdate: No customer callback registered!");
        return 0;
    }

    default int onGprmcDataStatus(IapController.IapSession session, int gprmcData) {
        Log.i("IapListener", "onGprmcDataStatus: No customer callback registered!");
        return 0;
    }

    default int onStartBluetoothPairing(IapController.IapSession session, short btTransComId, byte[] macAddress) {
        Log.i("IapListener", "onStartBluetoothPairing: No customer callback registered!");
        return 0;
    }

    default int onStopBluetoothPairing(IapController.IapSession session, short btTransComId, PairingState state) {
        Log.i("IapListener", "onStopBluetoothPairing: No customer callback registered!");
        return 0;
    }

    default int onCarplayAvialability(IapController.IapSession session, CarplayAvialability availability) {
        Log.i("IapListener", "onCarplayAvialability: No customer callback registered!");
        return 0;
    }

    default int onCarPlayAppListAvailable(IapController.IapSession session, int availableMask, int listCount) {
        Log.i("IapListener", "onCarPlayAppListAvailable: No customer callback registered!");
        return 0;
    }

    default int onCarPlayAppList(IapController.IapSession session, ArrayList<CarPlayAppList> list) {
        Log.i("IapListener", "onCarPlayAppList: No customer callback registered!");
        return 0;
    }

    default int onCarplayAppDiscoveryAppIcon(IapController.IapSession session, CarplayAppDiscoveryAppIcon appIcon) {
        Log.i("IapListener", "onCarplayAppDiscoveryAppIcon: No customer callback registered!");
        return 0;
    }

    default int onCarplayAppDiscoveryAppIconData(IapController.IapSession session, CarplayAppDiscoveryAppIconData appIcon) {
        Log.i("IapListener", "onCarplayAppDiscoveryAppIconData: No customer callback registered!");
        return 0;
    }
}
