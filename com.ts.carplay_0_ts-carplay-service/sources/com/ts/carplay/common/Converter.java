package com.ts.carplay.common;

import com.ts.carplay.common.data.config.CarPlayConfig;
import com.ts.carplay.common.data.config.ScreenInfo;
import java.util.ArrayList;
import java.util.List;
import ts.car.service.carplay.data.CallStateInfo;
import ts.car.service.carplay.data.CarPlayDevice;
import ts.car.service.carplay.data.FavoriteContactListInfo;
import ts.car.service.carplay.data.LaneInformation;
import ts.car.service.carplay.data.NowPlayingInfo;
import ts.car.service.carplay.data.PlaybackMediaItemInfo;
import ts.car.service.carplay.data.RecentContactListInfo;
import ts.car.service.carplay.data.RouteGuidanceInfo;
import ts.car.service.carplay.data.config.ButtonInfo;
import ts.car.service.carplay.data.config.SafeArea;
import ts.car.service.carplay.data.config.ViewArea;

/* JADX INFO: loaded from: classes.dex */
public abstract class Converter {
    public static CallStateInfo aidlToCallStateInfo(com.ts.carplay.common.data.CallStateInfo info) {
        if (info == null) {
            return null;
        }
        CallStateInfo callStateInfo = new CallStateInfo();
        if (true == info.isRemoteIdValid()) {
            callStateInfo.setRemoteId(info.getRemoteId());
        }
        if (true == info.isDisplayNameValid()) {
            callStateInfo.setDisplayName(info.getDisplayName());
        }
        callStateInfo.setStatus(info.getStatus());
        if (true == info.isDurationValid()) {
            callStateInfo.setDuration(info.getDuration());
        }
        if (true == info.isConferencedValid()) {
            callStateInfo.setConferenceGroup(info.getConferenceGroup());
        }
        if (true == info.isCallUuidValid()) {
            callStateInfo.setCallUuid(info.getCallUuid());
        }
        return callStateInfo;
    }

    public static RouteGuidanceInfo aidlToRouteGuidanceInfo(com.ts.carplay.common.data.RouteGuidanceInfo info) {
        if (info == null) {
            return null;
        }
        RouteGuidanceInfo routeGuidanceInfo = new RouteGuidanceInfo();
        routeGuidanceInfo.setManeuverType(info.getManeuverType());
        routeGuidanceInfo.setDistanceToNextManeuverDisplayStr(info.getDistanceToNextManeuverDisplayStr());
        routeGuidanceInfo.setDistanceToNextManeuverDisplayUnits(info.getDistanceToNextManeuverDisplayUnits());
        routeGuidanceInfo.setAfterManeuverRoadName(info.getAfterManeuverRoadName());
        routeGuidanceInfo.setDistanceRemainingDisplayStr(info.getDistanceRemainingDisplayStr());
        routeGuidanceInfo.setDistanceRemainingDisplayUnits(info.getDistanceRemainingDisplayUnits());
        routeGuidanceInfo.setTimeRemainingToDestination(info.getTimeRemainingToDestination());
        routeGuidanceInfo.setRouteGuidanceState(info.getRouteGuidanceState());
        routeGuidanceInfo.setJunctionType(info.getJunctionType());
        routeGuidanceInfo.setDrivingSide(info.getDrivingSide());
        routeGuidanceInfo.setJunctionElementExitAngle(info.getJunctionElementExitAngle());
        routeGuidanceInfo.setJunctionElementAngles(info.getJunctionElementAngles());
        routeGuidanceInfo.setCurrentRoadName(info.getCurrentRoadName());
        routeGuidanceInfo.setDestinationName(info.getDestinationName());
        routeGuidanceInfo.setDistanceToNextManeuver(info.getDistanceToNextManeuver());
        routeGuidanceInfo.setDistanceRemaining(info.getDistanceRemaining());
        routeGuidanceInfo.setRouteGuidanceVisibleInApp(info.isRouteGuidanceVisibleInApp());
        routeGuidanceInfo.setLaneGuidanceBeingShowInApp(info.isLaneGuidanceBeingShowInApp());
        routeGuidanceInfo.setManeuverState(info.getManeuverState());
        routeGuidanceInfo.setManeuverDescription(info.getManeuverDescription());
        routeGuidanceInfo.setEstimatedTimeOfArrival(info.getEstimatedTimeOfArrival());
        routeGuidanceInfo.setManeuverGuidanceTotalCount(info.getManeuverGuidanceTotalCount());
        routeGuidanceInfo.setLaneGuidanceTotalCount(info.getLaneGuidanceTotalCount());
        routeGuidanceInfo.setDistanceBetweenManeuver(info.getDistanceBetweenManeuver());
        routeGuidanceInfo.setDistanceBetweenManeuverDisplayStr(info.getDistanceBetweenManeuverDisplayStr());
        routeGuidanceInfo.setDistanceBetweenManeuverDisplayUnits(info.getDistanceBetweenManeuverDisplayUnits());
        routeGuidanceInfo.setLaneInformationList(aidlToLaneInfo(info.getLaneInformationList()));
        routeGuidanceInfo.setInstructionText(info.getInstructionText());
        routeGuidanceInfo.setSourceName(info.getSourceName());
        routeGuidanceInfo.setSourceSupportsRouteGuidance(info.getSourceSupportsRouteGuidance());
        routeGuidanceInfo.setExitInfo(info.getExitInfo());
        routeGuidanceInfo.setDestinationTimeZoneOffsetMinutes(info.getDestinationTimeZoneOffsetMinutes());
        return routeGuidanceInfo;
    }

    public static NowPlayingInfo aidlToNowPlayingInfo(com.ts.carplay.common.data.NowPlayingInfo info) {
        if (info == null) {
            return null;
        }
        NowPlayingInfo nowPlayingInfo = new NowPlayingInfo();
        nowPlayingInfo.setMediaItemTitle(info.getMediaItemTitle());
        nowPlayingInfo.setMediaItemArtist(info.getMediaItemArtist());
        nowPlayingInfo.setMediaItemPlaybackDurationMs(info.getMediaItemPlaybackDurationMs());
        nowPlayingInfo.setPlaybackStatus(info.getPlaybackStatus());
        nowPlayingInfo.setPlaybackElapsedTimeMs(info.getPlaybackElapsedTimeMs());
        nowPlayingInfo.setPlaybackShuffleMode(info.getPlaybackShuffleMode());
        nowPlayingInfo.setPlaybackRepeatMode(info.getPlaybackRepeatMode());
        nowPlayingInfo.setArtworkPath(info.getArtworkPath());
        if (info.getArtworkData() != null) {
            nowPlayingInfo.setArtworkSize(info.getArtworkSize());
            nowPlayingInfo.setArtworkData(info.getArtworkData());
        }
        nowPlayingInfo.setAlbumTitle(info.getAlbumTitle());
        nowPlayingInfo.setPid(info.getPid());
        return nowPlayingInfo;
    }

    public static CarPlayDevice aidlToCarPlayDevice(com.ts.carplay.common.data.CarPlayDevice dev) {
        if (dev == null) {
            return null;
        }
        CarPlayDevice device = new CarPlayDevice();
        device.setDeviceName(dev.getDeviceName());
        device.setUsbSerialNumber(dev.getUsbSerialNumber());
        device.setBtAddr(dev.getBtAddr());
        device.setWifiAddr(dev.getWifiAddr());
        device.setDeviceStatus(dev.getDeviceStatus());
        device.setUsbDevice(dev.isUsbDevice());
        device.setProjectionConnectType(dev.getProjectionConnectType());
        device.setProjectionConnectedTimeMillis(dev.getProjectionConnectedTimeMillis());
        device.setIsFirstConntect(dev.isFirstConntect());
        device.setCurWirelessCap(dev.getCurWirelessCap());
        device.setWirelessAvailableState(dev.getWirelessAvailableState());
        return device;
    }

    public static List<RecentContactListInfo> aidlToRecentContactList(List<com.ts.carplay.common.data.RecentContactListInfo> list) {
        if (list == null) {
            return null;
        }
        List<RecentContactListInfo> recentContactList = new ArrayList<>();
        for (com.ts.carplay.common.data.RecentContactListInfo info : list) {
            RecentContactListInfo recentContactListInfo = new RecentContactListInfo();
            recentContactListInfo.mIndex = info.mIndex;
            recentContactListInfo.mRemoteId = info.mRemoteId;
            recentContactListInfo.mDisplayName = info.mDisplayName;
            recentContactListInfo.mLabel = info.mLabel;
            recentContactListInfo.mAddressBookId = info.mAddressBookId;
            recentContactListInfo.mService = info.mService;
            recentContactListInfo.mType = info.mType;
            recentContactListInfo.mUnixTimestamp = info.mUnixTimestamp;
            recentContactListInfo.mDuration = info.mDuration;
            recentContactListInfo.mOccurrences = info.mOccurrences;
            recentContactList.add(recentContactListInfo);
        }
        return recentContactList;
    }

    public static List<FavoriteContactListInfo> aidlToFavoriteContactList(List<com.ts.carplay.common.data.FavoriteContactListInfo> list) {
        if (list == null) {
            return null;
        }
        List<FavoriteContactListInfo> favoriteContactList = new ArrayList<>();
        for (com.ts.carplay.common.data.FavoriteContactListInfo info : list) {
            FavoriteContactListInfo favoriteContactListInfo = new FavoriteContactListInfo();
            favoriteContactListInfo.mIndex = info.mIndex;
            favoriteContactListInfo.mRemoteId = info.mRemoteId;
            favoriteContactListInfo.mDisplayName = info.mDisplayName;
            favoriteContactListInfo.mLabel = info.mLabel;
            favoriteContactListInfo.mAddressBookId = info.mAddressBookId;
            favoriteContactListInfo.mService = info.mService;
            favoriteContactList.add(favoriteContactListInfo);
        }
        return favoriteContactList;
    }

    public static List<PlaybackMediaItemInfo> aidlToPlaybackMediaItemInfo(List<com.ts.carplay.common.data.PlaybackMediaItemInfo> list) {
        if (list == null) {
            return null;
        }
        List<PlaybackMediaItemInfo> playbackMediaItemInfoList = new ArrayList<>();
        for (com.ts.carplay.common.data.PlaybackMediaItemInfo info : list) {
            PlaybackMediaItemInfo playbackMediaItemInfo = new PlaybackMediaItemInfo();
            playbackMediaItemInfo.mPid = info.mPid;
            playbackMediaItemInfo.mTitle = info.mTitle;
            playbackMediaItemInfo.mAlbum = info.mAlbum;
            playbackMediaItemInfo.mArtist = info.mArtist;
            playbackMediaItemInfo.mAlbumArtist = info.mAlbumArtist;
            playbackMediaItemInfo.mGenre = info.mGenre;
            playbackMediaItemInfo.mComposer = info.mComposer;
            playbackMediaItemInfoList.add(playbackMediaItemInfo);
        }
        return playbackMediaItemInfoList;
    }

    public static CarPlayConfig aidlToCarPlayConfig(ts.car.service.carplay.data.config.CarPlayConfig config) {
        if (config == null) {
            return null;
        }
        CarPlayConfig.Builder builder = new CarPlayConfig.Builder();
        builder.manufacturer(config.getManufacturer()).accessoryModel(config.getAccessoryModel()).accessoryModelId(config.getAccessoryModelId()).hardwareRevision(config.getHardwareRevision()).firmwareRevision(config.getFirmwareRevision()).serialNumber(config.getSerialNumber()).displayName(config.getDisPlayName()).engineType(config.getEngineType()).vendorId(config.getVendorId()).productId(config.getProductId()).accessoryName(config.getAccessoryName()).productPlanUuid(config.getProductPlanUuid()).oemIconLabel(config.getOemIconLabel()).oemIconPaths(config.getOemIconPaths()).accessoryOsInfo(config.getAccessoryOsInfo()).rightHandDrive(config.getRightHandDrive()).nightMode(config.isNightMode()).oemIconVisible(config.isOemIconVisible()).disPlayFeatures(config.getDisPlayFeatures()).screenInfos(aidlToScreenInfoArrays(config.getScreenInfos())).primaryInputDevice(config.getPrimaryInputDevice()).extendedFeatures(config.getExtendedFeatures()).iapFeatures(config.getIapFeatures()).features(config.getFeatures()).hidLanguages(config.getHidLanguages()).keepAliveLowPower(config.isKeepAliveLowPower()).keepAliveSendStatsAsBody(config.isKeepAliveSendStatsAsBody()).protocolVersion(config.getProtocolVersion()).limitedUi(config.isLimitedUi()).limitedUiElements(config.getLimitedUiElements()).sourceVersion(config.getSourceVersion()).statusFlags(config.getStatusFlags()).appearanceDefault(config.getAppearanceDefault()).uiContextLastOnDisplayUrls(config.getUiContextLastOnDisplayUrls()).uiContextNowOnDisplayUrls(config.getUiContextNowOnDisplayUrls()).playKeyMask(config.getPlayKeyMask()).telKeyMask(config.getTelKeyMask()).maxDestinationNameLength(config.getMaxDestinationNameLength()).maxGuidanceManeuverCapacity(config.getMaxGuidanceManeuverCapacity()).maxLaneGuidanceCapacity(config.getMaxLaneGuidanceCapacity()).maxLaneGuidanceDescriptionLength(config.getMaxLaneGuidanceDescriptionLength()).availableCurrentForDevice(config.getAvailableCurrentForDevice()).maxCurrentRoadNameLength(config.getMaxCurrentRoadNameLength()).maxAfterManeuverRoadNameLength(config.getMaxAfterManeuverRoadNameLength()).maxManeuverDescriptionLength(config.getMaxManeuverDescriptionLength()).callControlFeature(config.getCallControlFeature()).iapPlaybackHid(config.getIapPlaybackHid()).carplayCapability(config.getCarPlayCapability()).currentLanguage(config.getCurrentLanguage()).supportedLanguage(config.getSupportedLanguage()).usbHostTransportInterfaceNum(config.getUsbHostTransportInterfaceNum()).iapDeviceNode(config.getIapDeviceNode()).disPlayOffExpected(config.getDisPlayOffExpected()).supportedFeatures(config.getSupportedFeatures()).supportedEnhancedSiriType(config.getSupportedEnhancedSiriType()).viewAreaIndex(config.getViewAreaIndex()).suggestUrl(config.getSuggestUrl()).showUiParameters(config.getShowUiParameters()).useMainViewArea(config.isUseMainViewArea()).useAlt1ViewArea(config.isUseAlt1ViewArea());
        ViewArea[] viewAreas = config.getViewAreas();
        if (viewAreas != null) {
            int viewAreaSize = viewAreas.length;
            builder.viewAreas(aidlToViewArea(viewAreas, viewAreaSize));
        }
        ViewArea[] alt1ViewAreas = config.getAlt1ViewAreas();
        if (alt1ViewAreas != null) {
            int alt1ViewAreaSize = alt1ViewAreas.length;
            builder.alt1ViewAreas(aidlToViewArea(alt1ViewAreas, alt1ViewAreaSize));
        }
        SafeArea[] alt1SafeAreas = config.getAlt1SafeAreas();
        if (alt1SafeAreas != null) {
            int alt1SafeAreaSize = alt1SafeAreas.length;
            builder.alt1SafeAreas(aidlToSafeArea(alt1SafeAreas, alt1SafeAreaSize));
        }
        ButtonInfo[] buttonInfo = config.getButtonInfo();
        if (buttonInfo != null) {
            int buttonInfoSize = buttonInfo.length;
            builder.buttonInfo(aidlToButtonInfo(buttonInfo, buttonInfoSize));
        }
        CarPlayConfig carPlayConfig = new CarPlayConfig(builder);
        return carPlayConfig;
    }

    private static ScreenInfo[] aidlToScreenInfoArrays(ts.car.service.carplay.data.config.ScreenInfo[] screenInfos) {
        if (screenInfos == null) {
            return null;
        }
        ScreenInfo[] screenInfosList = new ScreenInfo[screenInfos.length];
        for (int i = 0; i < screenInfos.length; i++) {
            screenInfosList[i] = aidlToScreenInfo(screenInfos[i]);
        }
        return screenInfosList;
    }

    private static ScreenInfo aidlToScreenInfo(ts.car.service.carplay.data.config.ScreenInfo info) {
        if (info == null) {
            return null;
        }
        ScreenInfo screenInfo = new ScreenInfo();
        screenInfo.setUuid(info.getUuid());
        screenInfo.setHeightPixels(info.getHeightPixels());
        screenInfo.setWidthPixels(info.getWidthPixels());
        screenInfo.setHeightPhysical(info.getHeightPhysical());
        screenInfo.setWidthPhysical(info.getWidthPhysical());
        screenInfo.setMaxFps(info.getMaxFps());
        screenInfo.setInitialUrl(info.getInitialUrl());
        screenInfo.setUiMode(info.getUiMode());
        screenInfo.setUiSetting(info.getUiSetting());
        screenInfo.setIsSupportMap(info.isIsSupportMap());
        screenInfo.setMapMode(info.getMapMode());
        screenInfo.setMapSetting(info.getMapSetting());
        return screenInfo;
    }

    private static com.ts.carplay.common.data.config.ViewArea[] aidlToViewArea(ViewArea[] viewArea, int length) {
        if (viewArea == null) {
            return null;
        }
        com.ts.carplay.common.data.config.ViewArea[] viewAreasList = new com.ts.carplay.common.data.config.ViewArea[length];
        int list = 0;
        for (ViewArea view : viewArea) {
            com.ts.carplay.common.data.config.ViewArea area = new com.ts.carplay.common.data.config.ViewArea(view.getWidthPixel(), view.getHeightPixel(), view.getOriginX(), view.getOriginY());
            viewAreasList[list] = area;
            list++;
        }
        return viewAreasList;
    }

    private static com.ts.carplay.common.data.config.SafeArea[] aidlToSafeArea(SafeArea[] safeArea, int length) {
        if (safeArea == null) {
            return null;
        }
        com.ts.carplay.common.data.config.SafeArea[] safeAreasList = new com.ts.carplay.common.data.config.SafeArea[length];
        int list = 0;
        for (SafeArea safe : safeArea) {
            com.ts.carplay.common.data.config.SafeArea area = new com.ts.carplay.common.data.config.SafeArea(safe.getWidthPixel(), safe.getHeightPixel(), safe.getOriginX(), safe.getOriginY());
            safeAreasList[list] = area;
            list++;
        }
        return safeAreasList;
    }

    private static com.ts.carplay.common.data.config.ButtonInfo[] aidlToButtonInfo(ButtonInfo[] buttonInfo, int length) {
        if (buttonInfo == null) {
            return null;
        }
        com.ts.carplay.common.data.config.ButtonInfo[] buttonInfoList = new com.ts.carplay.common.data.config.ButtonInfo[length];
        int list = 0;
        for (ButtonInfo button : buttonInfo) {
            com.ts.carplay.common.data.config.ButtonInfo tempButton = new com.ts.carplay.common.data.config.ButtonInfo(button.getButtonType(), button.getButtonLocation(), button.getButtonPressDuration());
            buttonInfoList[list] = tempButton;
            list++;
        }
        return buttonInfoList;
    }

    private static List<LaneInformation> aidlToLaneInfo(List<com.ts.carplay.common.data.LaneInformation> list) {
        List<LaneInformation> laneInformationList = new ArrayList<>();
        if (list == null || list.size() <= 0) {
            return null;
        }
        for (com.ts.carplay.common.data.LaneInformation info : list) {
            LaneInformation laneInformation = new LaneInformation(info.getIndex(), info.getLaneStatus(), info.getLaneAngles(), info.getLaneAngleHighlight());
            laneInformationList.add(laneInformation);
        }
        return laneInformationList;
    }
}
