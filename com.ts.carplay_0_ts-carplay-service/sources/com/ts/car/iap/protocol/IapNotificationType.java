package com.ts.car.iap.protocol;

import com.ts.carplay.lib.common.CpConst;

/* JADX INFO: loaded from: classes.dex */
public enum IapNotificationType {
    IapNotifyTypeSession(0),
    IapNotifyTypeSessionStatus(IapNotifyTypeSession.mType + 1),
    IapNotifyTypeDeviceNotify(16),
    IapNotifyTypeDeviceName(IapNotifyTypeDeviceNotify.mType + 1),
    IapNotifyTypeDeviceUuid(IapNotifyTypeDeviceNotify.mType + 2),
    IapNotifyTypeWCPAvailable(IapNotifyTypeDeviceNotify.mType + 3),
    IapNotifyTypeDeviceBtTxId(IapNotifyTypeDeviceNotify.mType + 4),
    IapNotifyTypeDeviceUsbTxId(IapNotifyTypeDeviceNotify.mType + 5),
    IapNotifyTypeDeviceTimeUpdate(IapNotifyTypeDeviceNotify.mType + 6),
    IapNotifyTypePower(32),
    IapNotifyTypeMaxDrawnCurrent(IapNotifyTypePower.mType + 1),
    IapNotifyTypeIfDeviceCharge(IapNotifyTypePower.mType + 2),
    IapNotifyTypeIsChargerConnected(IapNotifyTypePower.mType + 3),
    IapNotifyTypeChargingState(IapNotifyTypePower.mType + 4),
    IapNotifyTypeChargeLevel(IapNotifyTypePower.mType + 5),
    IapNotifyTypeBtState(48),
    IapNotifyTypeBtConnectionUpdate(IapNotifyTypeBtState.mType + 1),
    IapNotifyTypeNowPlaying(64),
    IapNotifyTypePlaybackList(IapNotifyTypeNowPlaying.mType + 1),
    IapNotifyTypeArtwork(IapNotifyTypeNowPlaying.mType + 2),
    IapNotifyTypeNowPlayingUpdate(IapNotifyTypeNowPlaying.mType + 3),
    IapNotifyTypeDeviceAudio(80),
    IapNotifyTypeSampleRate(IapNotifyTypeDeviceAudio.mType + 1),
    IapNotifyTypeMediaLibrary(96),
    IapNotifyTypeMediaLibraryReset(IapNotifyTypeMediaLibrary.mType + 1),
    IapNotifyTypeMediaItemAdd(IapNotifyTypeMediaLibrary.mType + 2),
    IapNotifyTypeMediaItemDelete(IapNotifyTypeMediaLibrary.mType + 3),
    IapNotifyTypeMediaPlaylistAdd(IapNotifyTypeMediaLibrary.mType + 4),
    IapNotifyTypeMediaPlaylistDelete(IapNotifyTypeMediaLibrary.mType + 5),
    IapNotifyTypeMediaLibraryInfo(IapNotifyTypeMediaLibrary.mType + 6),
    IapNotifyTypeMediaPlaylistContent(IapNotifyTypeMediaLibrary.mType + 7),
    IapNotifyTypeCallState(112),
    IapNotifyTypeCallStateUpdate(IapNotifyTypeCallState.mType + 1),
    IapNotifyTypeTeleState(128),
    IapNotifyTypeTeleCommunicationsUpdate(IapNotifyTypeTeleState.mType + 1),
    IapNotifyTypeLocation(144),
    IapNotifyTypeStartLocationInfo(IapNotifyTypeLocation.mType + 1),
    IapNotifyTypeStopLocationInfo(IapNotifyTypeLocation.mType + 2),
    IapNotifyTypeOobBtPairing(160),
    IapNotifyTypeStartOobBtPairing(IapNotifyTypeOobBtPairing.mType + 1),
    IapNotifyTypeOobBtPairingLinkKey(IapNotifyTypeOobBtPairing.mType + 2),
    IapNotifyTypeStopOobBtPairing(IapNotifyTypeOobBtPairing.mType + 3),
    IapNotifyTypeWifiSharing(176),
    IapNotifyTypeReqAccWifiInfo(IapNotifyTypeWifiSharing.mType + 1),
    IapNotifyTypeEap(192),
    IapNotifyTypeStartEapSession(IapNotifyTypeEap.mType + 1),
    IapNotifyTypeEapIncomingData(IapNotifyTypeEap.mType + 2),
    IapNotifyTypeStopEapSession(IapNotifyTypeEap.mType + 3),
    IapNotifyTypeRouteGuidance(208),
    IapNotifyTypeRouteGuidanceUpdate(IapNotifyTypeRouteGuidance.mType + 1),
    IapNotifyTypeManeuverUpdate(IapNotifyTypeRouteGuidance.mType + 2),
    IapNotifyTypeLaneGuidanceInformation(IapNotifyTypeRouteGuidance.mType + 3),
    IapNotifyTypeList(224),
    IapNotifyTypeRecentListAvailable(IapNotifyTypeList.mType + 1),
    IapNotifyTypeFavoriteListAvailable(IapNotifyTypeList.mType + 2),
    IapNotifyTypeRecentListUpdate(IapNotifyTypeList.mType + 3),
    IapNotifyTypeFavoriteListUpdate(IapNotifyTypeList.mType + 4),
    IapNotifyTypeNowPlayingPlayBackList(240),
    IapNotifyTypeNowPlayingPlayBackListParsed(IapNotifyTypeNowPlayingPlayBackList.mType + 1),
    IapNotifyTypeOobBluetoothPairing(256),
    IapNotifyTypeStartBluetoothPairing(IapNotifyTypeOobBluetoothPairing.mType + 1),
    IapNotifyTypeStopBluetoothPairing(IapNotifyTypeOobBluetoothPairing.mType + 2),
    IapNotifyTypeCarplayConnection(512),
    IapNotifyTypeCarplayAvailability(IapNotifyTypeCarplayConnection.mType + 1),
    IapNotifyTypeAppDiscovery(CpConst.TORO_HEIGHT),
    IapNotifyTypeAppDiscoveryCarplayListUpdate(IapNotifyTypeAppDiscovery.mType + 1),
    IapNotifyTypeAppDiscoveryCarplayAppIcon(IapNotifyTypeAppDiscovery.mType + 2),
    IapNotifyTypeCarplayAppIconDate(IapNotifyTypeAppDiscovery.mType + 3),
    IapNotifyTypeDestinationInformation(1024),
    IapNotifyTypeDestinationInformationUpdate(IapNotifyTypeDestinationInformation.mType + 1),
    IapNotifyTypeVehicleStatus(2048),
    IapNotifyTypeStartVehicleStatusUpdates(IapNotifyTypeVehicleStatus.mType + 1),
    IapNotifyTypeStopVehicleStatusUpdates(IapNotifyTypeVehicleStatus.mType + 2),
    IapNotifyTypeGprmcDataStatusInfo(4096),
    IapNotifyTypeGprmcDataStatusInfoParsed(IapNotifyTypeGprmcDataStatusInfo.mType + 1),
    IapNotifyTypeOther(65536),
    IapNotifyTypeMessageParsed(IapNotifyTypeOther.mType + 1),
    IapNotifyTypeInvalid(-1);

    private int mType;

    IapNotificationType(int type) {
        this.mType = type;
    }

    public int getTypeValue() {
        return this.mType;
    }

    public static IapNotificationType fromTypeValue(int type) {
        for (IapNotificationType value : values()) {
            if (value.getTypeValue() == type) {
                return value;
            }
        }
        return IapNotifyTypeInvalid;
    }
}
