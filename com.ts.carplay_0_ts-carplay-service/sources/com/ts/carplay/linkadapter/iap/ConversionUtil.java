package com.ts.carplay.linkadapter.iap;

import com.ts.carplay.linkadapter.iap.data.CarplayStartSessionInfo;
import com.ts.carplay.linkadapter.iap.data.CommunicationsUpdate;
import com.ts.carplay.linkadapter.iap.data.IapConfig;
import com.ts.carplay.linkadapter.iap.data.MediaLibraryUpdate;
import com.ts.carplay.linkadapter.iap.data.PowerUpdate;
import java.util.ArrayList;
import java.util.List;
import ts.car.iap.protocol.common.CarplayStartSessionInfo;
import ts.car.iap.protocol.common.IapConfig;
import ts.car.iap.protocol.common.MediaItem;
import ts.car.iap.protocol.common.MediaPlaylist;
import ts.car.iap.protocol.common.WifiChannelType;
import ts.car.iap.protocol.common.WifiSecurityType;

/* JADX INFO: loaded from: classes.dex */
public class ConversionUtil {
    private static final String TAG = ConversionUtil.class.getSimpleName();
    private static volatile ConversionUtil mInstance;

    private ConversionUtil() {
    }

    public static ConversionUtil getInstance() {
        if (mInstance == null) {
            synchronized (ConversionUtil.class) {
                if (mInstance == null) {
                    mInstance = new ConversionUtil();
                }
            }
        }
        return mInstance;
    }

    public IapConfig convertIapConfig(com.ts.carplay.linkadapter.iap.data.IapConfig item) {
        IapConfig info = null;
        if (item != null) {
            info = new IapConfig();
            info.setProductPlanUuid(item.getProductPlanUuid());
            info.setIapFeature(item.getIapFeature());
            info.setIapFeatureOption(item.getIapFeatureOption());
            info.setPlaybackHid(item.getPlaybackHid());
            info.setNmeaSentence(item.getNmeaSentence());
            info.setDeviceId(item.getDeviceId());
            info.setTransportType(item.getTransportType());
            info.setDeviceNode(item.getDeviceNode());
            info.setIapAppMode(item.getIapAppMode());
            info.setIsWirelessCarPlayActive(item.getIsWirelessCarPlayActive());
            info.setBtMacAddress(item.getBtMacAddress());
            info.setAccessoryName(item.getAccessoryName());
            info.setAccessoryModel(item.getAccessoryModel());
            info.setManufacturer(item.getManufacturer());
            info.setSerialNumber(item.getSerialNumber());
            info.setCurrentLang(item.getCurrentLang());
            info.setSupportedLangList(item.getSupportedLangList());
            info.setFwVersion(item.getFwVersion());
            info.setHwVersion(item.getHwVersion());
            info.setVid(item.getVid());
            info.setPid(item.getPid());
            info.setAvailableCurrent(item.getAvailableCurrent());
            info.setEngineType(item.getEngineType());
            info.setDisplayName(item.getDisplayName());
            info.setRouteguidanceDisplayName(item.getRouteGuidanceDisplayName());
            info.setMaxCurrentRoadNameLength(item.getMaxCurrentRoadNameLength());
            info.setMaxDestinationNameLength(item.getMaxDestinationNameLength());
            info.setMaxAfterManeuverRoadNameLength(item.getMaxAfterManeuverRoadNameLength());
            info.setMaxManeuverDescriptionLength(item.getMaxManeuverDescriptionLength());
            info.setMaxGuidanceManeuverCapacity(item.getMaxGuidanceManeuverCapacity());
            info.setMaxLaneGuidanceStorageCapacity(item.getMaxLaneGuidanceCapacity());
            info.setMaxLaneGuidanceDescriptionLength(item.getMaxLaneGuidanceDescriptionLength());
            info.setNcmInterfaceNum(item.getUsbHostTransportInterfaceNum());
            info.setIsVehicleInformationComponentDeleted(item.getIsVehicleInformationComponentDeleted());
            ArrayList<IapConfig.EapInfo> eapInfoList = item.getEapInfoList();
            if (eapInfoList != null) {
                for (IapConfig.EapInfo eapInfo : eapInfoList) {
                    if (eapInfo != null) {
                        IapConfig.EapInfo addInfo = new IapConfig.EapInfo(eapInfo.getEapIdentifier(), eapInfo.getEapName(), eapInfo.getEapMatchAction(), eapInfo.getIsNativeTransport(), eapInfo.getNativeTransportId(), eapInfo.getIsEapCarPlay());
                        info.addEapInfo(addInfo);
                    }
                }
            }
        }
        return info;
    }

    public CommunicationsUpdate convertCommunicationsUpdate(ts.car.iap.protocol.common.CommunicationsUpdate item) {
        CommunicationsUpdate info = null;
        if (item != null) {
            info = new CommunicationsUpdate();
            info.setSignalStrength(item.getSignalStrength());
            info.setRegistrationStatus(item.getRegistrationStatus());
            info.setAirplaneModeStatus(item.getAirplaneModeStatus());
            if (item.isCarrierNameValid()) {
                info.setCarrierName(item.getCarrierName());
            }
            info.setCellularSupported(item.getCellularSupported());
            info.setTelephonyEnabled(item.getTelephonyEnabled());
            info.setFaceTimeAudioEnabled(item.getFaceTimeAudioEnabled());
            info.setFaceTimeVideoEnabled(item.getFaceTimeVideoEnabled());
            info.setMuteStatus(item.getMuteStatus());
            info.setCurrentCallCount(item.getCurrentCallCount());
            info.setNewVoicemailCount(item.getNewVoicemailCount());
            info.setInitiateCallAvailable(item.getInitiateCallAvailable());
            info.setEndAndAcceptAvailable(item.getEndAndAcceptAvailable());
            info.setHoldAndAcceptAvailable(item.getHoldAndAcceptAvailable());
            info.setSwapAvailable(item.getSwapAvailable());
            info.setMergeAvailable(item.getMergeAvailable());
            info.setHoldAvailable(item.getHoldAvailable());
        }
        return info;
    }

    public MediaLibraryUpdate convertMediaLibraryUpdate(ts.car.iap.protocol.common.MediaLibraryUpdate item) {
        MediaLibraryUpdate info = null;
        if (item != null) {
            info = new MediaLibraryUpdate();
            info.setMediaLibName(item.getMediaLibName());
            info.setMediaLibUid(item.getMediaLibUid());
            info.setMediaLibType(item.getMediaLibType());
            info.setMediaLibRevision(item.getMediaLibRevision());
            info.setMediaLibUpdateProgress(item.getMediaLibUpdateProgress());
            info.setMediaLibReset(item.getMediaLibReset());
            List<MediaItem> mediaItems = item.getMediaItems();
            for (int i = 0; i < mediaItems.size(); i++) {
                info.addMediaItem(getInstance().convertMediaItem(mediaItems.get(i)));
            }
            List<MediaPlaylist> mediaPlaylists = item.getMediaPlaylists();
            for (int i2 = 0; i2 < mediaPlaylists.size(); i2++) {
                info.addMediaPlaylist(getInstance().convertMediaPlaylist(mediaPlaylists.get(i2)));
            }
            List<String> mediaItemDeletePids = item.getMediaItemDeletPids();
            for (int i3 = 0; i3 < mediaItemDeletePids.size(); i3++) {
                info.addMediaItemDeletePid(mediaItemDeletePids.get(i3));
            }
            List<String> mediaPlaylistDeletePids = item.getMediaPlaylistDeletPids();
            for (int i4 = 0; i4 < mediaPlaylistDeletePids.size(); i4++) {
                info.addMediaPlaylistDeletePid(mediaPlaylistDeletePids.get(i4));
            }
        }
        return info;
    }

    public PowerUpdate convertPowerUpdate(ts.car.iap.protocol.common.PowerUpdate item) {
        if (item == null) {
            return null;
        }
        PowerUpdate info = new PowerUpdate();
        info.setMaximumCurrentDrawnFromAccessory(item.getMaximumCurrentDrawnFromAccessory());
        info.setDeviceBatteryWillChargeIfPowerIsPresent(item.getDeviceBatteryWillChargeIfPowerIsPresent());
        info.setIsExternalChargerConnected(item.getIsExternalChargerConnected());
        info.setBatteryChargingState(item.getBatteryChargingState());
        info.setBatteryChargeLevel(item.getBatteryChargeLevel());
        return info;
    }

    public com.ts.carplay.linkadapter.iap.data.MediaItem convertMediaItem(MediaItem item) {
        if (item == null) {
            return null;
        }
        com.ts.carplay.linkadapter.iap.data.MediaItem info = new com.ts.carplay.linkadapter.iap.data.MediaItem();
        info.setAlbumPid(item.getAlbumPid());
        info.setTitle(item.getTitle());
        info.setMediaType(item.getMediaType());
        info.setAlbumPid(item.getAlbumPid());
        info.setAlbumTitle(item.getAlbumTitle());
        info.setAlbumTrackNum(item.getAlbumTrackNum());
        info.setArtistPid(item.getArtistPid());
        info.setArtist(item.getArtist());
        info.setGenrePid(item.getGenrePid());
        info.setGenre(item.getGenre());
        info.setComposerPid(item.getComposerPid());
        info.setComposer(item.getComposer());
        return info;
    }

    public com.ts.carplay.linkadapter.iap.data.MediaPlaylist convertMediaPlaylist(MediaPlaylist item) {
        if (item == null) {
            return null;
        }
        com.ts.carplay.linkadapter.iap.data.MediaPlaylist info = new com.ts.carplay.linkadapter.iap.data.MediaPlaylist();
        info.setPid(item.getPid());
        info.setName(item.getName());
        info.setParentPid(item.getParentPid());
        info.setIsRadio(item.getIsRadio());
        info.setIsFold(item.getIsFold());
        info.setIsGeniusMix(item.getIsGeniusMix());
        return info;
    }

    public CarplayStartSessionInfo convertStartSession(int type, com.ts.carplay.linkadapter.iap.data.CarplayStartSessionInfo startSessionInfo) {
        CarplayStartSessionInfo info = null;
        if (startSessionInfo != null) {
            info = new CarplayStartSessionInfo();
            info.setPort(startSessionInfo.getPort());
            info.setDeviceIdentifier(startSessionInfo.getDeviceIdentifier());
            info.setPublicKey(startSessionInfo.getPublicKey());
            info.setSourceVersion(startSessionInfo.getSourceVersion());
            CarplayStartSessionInfo.WirelessAttribute wirelessAttributeInfo = startSessionInfo.getWirelessAttribute();
            if (wirelessAttributeInfo != null && type == 18) {
                CarplayStartSessionInfo.WirelessAttribute wirelessAttribute = new CarplayStartSessionInfo.WirelessAttribute();
                wirelessAttribute.setIpAddress(wirelessAttributeInfo.getIpAddress());
                wirelessAttribute.setWifiSsid(wirelessAttributeInfo.getWifiSsid());
                wirelessAttribute.setPassphrase(wirelessAttributeInfo.getPassphrase());
                wirelessAttribute.setChannel(WifiChannelType.fromTypeValue(wirelessAttributeInfo.getChannel()));
                wirelessAttribute.setSecurityType(WifiSecurityType.fromTypeValue(wirelessAttributeInfo.getSecurityType()));
                info.setWirelessAttribute(wirelessAttribute);
            }
            CarplayStartSessionInfo.WiredAttribute wiredAttributeInfo = startSessionInfo.getWiredAttribute();
            if (wiredAttributeInfo != null && type == 17) {
                CarplayStartSessionInfo.WiredAttribute wiredAttribute = new CarplayStartSessionInfo.WiredAttribute();
                wiredAttribute.setIpAddress(wiredAttributeInfo.getIpAddress());
                info.setWiredAttribute(wiredAttribute);
            }
        }
        return info;
    }

    public int convertHidKeyCode(int keyCode) {
        if (keyCode == 1) {
            return 1;
        }
        if (keyCode == 2) {
            return 2;
        }
        if (keyCode == 4) {
            return 4;
        }
        if (keyCode == 8) {
            return 8;
        }
        if (keyCode == 16) {
            return 16;
        }
        if (keyCode == 32) {
            return 32;
        }
        if (keyCode != 64) {
            return -1;
        }
        return 64;
    }
}
