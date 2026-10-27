package com.ts.androidauto.aap.sink;

import android.os.Handler;
import android.os.HandlerThread;
import com.google.android.projection.proto.Protos;
import com.google.android.projection.protocol.MediaPlaybackStatus;
import com.google.android.projection.protocol.NavigationStatus;
import com.google.android.projection.protocol.PhoneStatus;
import com.ts.androidauto.aap.AapDisplayManager;
import com.ts.androidauto.aap.data.MediaMetadata;
import com.ts.androidauto.aap.data.NavigationDestDistanceData;
import com.ts.androidauto.aap.data.NavigationDirectionData;
import com.ts.androidauto.aap.data.NavigationLaneData;
import com.ts.androidauto.aap.data.NavigationPositionData;
import com.ts.androidauto.aap.data.NavigationStateData;
import com.ts.androidauto.aap.data.NavigationStepData;
import com.ts.androidauto.aap.data.PhoneStatusData;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.androidauto.fsdk.common.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class InstrumentClusterListener implements NavigationStatus.NavigationStatusListener, MediaPlaybackStatus.MediaPlaybackStatusListener, PhoneStatus.PhoneStatusListener {
    public static final int PAUSED = 3;
    public static final int PHONE_CONFERENCED = 5;
    public static final int PHONE_INACTIVE = 3;
    public static final int PHONE_INCOMING = 4;
    public static final int PHONE_IN_CALL = 1;
    public static final int PHONE_MUTED = 6;
    public static final int PHONE_ON_HOLD = 2;
    public static final int PLAYING = 2;
    public static final int STOPPED = 1;
    private static final String TAG = "AAP" + InstrumentClusterListener.class.getSimpleName();
    private static InstrumentClusterListener mInstrumentClusterListener;
    private AapDisplayManager mAapDisplayManager;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private Protos.NavigationStatus.NavigationStatusEnum mNaviStatus;
    private MediaMetadata mMediaMetadata = new MediaMetadata();
    private int mPlayState = AndroidAutoConst.MusicStatus.MUSIC_STATUS_NOT_START.ordinal();
    private int mPlayBackSeconds = 0;

    public static synchronized InstrumentClusterListener getInstance(AapDisplayManager aapDisplayManager) {
        if (mInstrumentClusterListener == null) {
            mInstrumentClusterListener = new InstrumentClusterListener(aapDisplayManager);
        }
        return mInstrumentClusterListener;
    }

    private InstrumentClusterListener(AapDisplayManager aapDisplayManager) {
        this.mAapDisplayManager = null;
        this.mAapDisplayManager = aapDisplayManager;
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
    }

    @Override // com.google.android.projection.protocol.NavigationStatus.NavigationStatusListener
    public void onStatus(int status) {
        LogUtil.verbose(TAG, "navi onStatus :" + status);
        Protos.NavigationStatus.NavigationStatusEnum navigationStatusEnumValueOf = Protos.NavigationStatus.NavigationStatusEnum.valueOf(status);
        this.mNaviStatus = navigationStatusEnumValueOf;
        AapDisplayManager aapDisplayManager = this.mAapDisplayManager;
        if (aapDisplayManager != null) {
            aapDisplayManager.updateNaviStatus(navigationStatusEnumValueOf.ordinal());
        }
    }

    @Override // com.google.android.projection.protocol.NavigationStatus.NavigationStatusListener
    public void onNextTurn(String road, int turnSide, int event, byte[] image, int turnAngle, int turnNumber) {
        LogUtil.verbose(TAG, "onNextTurn road :" + road + " turnSide: " + turnSide + " event: " + event + " turnAngle: " + turnAngle + " turnNumber: " + turnNumber);
    }

    @Override // com.google.android.projection.protocol.NavigationStatus.NavigationStatusListener
    public void onNextTurnDistance(int distanceMeters, int timeSeconds, int displayDistanceE3, int displayDistanceUnit) {
        LogUtil.verbose(TAG, "onNextTurnDistance distanceMeters :" + distanceMeters + " timeSeconds: " + timeSeconds + " displayDistanceE3: " + displayDistanceE3 + " displayDistanceUnit: " + displayDistanceUnit);
    }

    @Override // com.google.android.projection.protocol.NavigationStatus.NavigationStatusListener
    public void onNavigationState(final Protos.NavigationState navigationState) {
        LogUtil.verbose(TAG, "onNavigationState navigationState :" + navigationState);
        this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.aap.sink.-$$Lambda$InstrumentClusterListener$7yyWkCdxP9PEenGOGTr1tyJn0oY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onNavigationState$0$InstrumentClusterListener(navigationState);
            }
        });
    }

    public /* synthetic */ void lambda$onNavigationState$0$InstrumentClusterListener(Protos.NavigationState navigationState) {
        Protos.NavigationState navigationState2 = navigationState;
        try {
            NavigationStateData navigationStateData = new NavigationStateData();
            List<String> navigationDestinationList = new ArrayList<>();
            int destCount = navigationState.getDestinationsCount();
            for (int i = 0; i < destCount; i++) {
                Protos.NavigationDestination navigationDestination = navigationState2.getDestinations(i);
                navigationDestinationList.add(navigationDestination.getAddress());
            }
            navigationStateData.setNavigationDestinations(navigationDestinationList);
            int stepCount = navigationState.getStepsCount();
            List<NavigationStepData> navigationStepDataList = new ArrayList<>();
            int i2 = 0;
            while (i2 < stepCount) {
                Protos.NavigationStep navigationStep = navigationState2.getSteps(i2);
                NavigationStepData ifNavigationStepData = new NavigationStepData();
                ifNavigationStepData.setRoad(navigationStep.getRoad().getName());
                ifNavigationStepData.setEvent(navigationStep.getManeuver().getType().getNumber());
                ifNavigationStepData.setTurnAngle(navigationStep.getManeuver().getRoundaboutExitAngle());
                ifNavigationStepData.setTurnNumber(navigationStep.getManeuver().getRoundaboutExitNumber());
                ifNavigationStepData.setNavigationCue(navigationStep.getCue().getAlternateTextList());
                List<NavigationLaneData> navigationLaneDataList = new ArrayList<>();
                for (int j = 0; j < navigationStep.getLanesCount(); j++) {
                    Protos.NavigationLane navigationLane = navigationStep.getLanes(j);
                    List<NavigationDirectionData> navigationDirectionDataList = new ArrayList<>();
                    for (int k = 0; k < navigationLane.getLaneDirectionsCount(); k++) {
                        NavigationDirectionData ifNavigationDirectionData = new NavigationDirectionData();
                        int shape = navigationLane.getLaneDirections(k).getShape().getNumber();
                        boolean isHighlighted = navigationLane.getLaneDirections(k).getIsHighlighted();
                        ifNavigationDirectionData.setShape(shape);
                        int shape2 = isHighlighted ? 1 : 0;
                        ifNavigationDirectionData.setHighlighted(shape2);
                        navigationDirectionDataList.add(ifNavigationDirectionData);
                    }
                    NavigationLaneData ifNavigationLaneData = new NavigationLaneData();
                    ifNavigationLaneData.setNavigationDirectionData(navigationDirectionDataList);
                    navigationLaneDataList.add(ifNavigationLaneData);
                }
                ifNavigationStepData.setLaneData(navigationLaneDataList);
                navigationStepDataList.add(ifNavigationStepData);
                i2++;
                navigationState2 = navigationState;
            }
            navigationStateData.setStepData(navigationStepDataList);
            if (this.mAapDisplayManager != null) {
                this.mAapDisplayManager.onNavigationState(navigationStateData);
            }
        } catch (Exception exception) {
            LogUtil.error(TAG, "onNavigationState Exception ", exception);
            exception.printStackTrace();
        }
    }

    @Override // com.google.android.projection.protocol.NavigationStatus.NavigationStatusListener
    public void onNavigationCurrentPosition(Protos.NavigationCurrentPosition navigationCurrentPosition) {
        LogUtil.verbose(TAG, "onNavigationCurrentPosition navigationCurrentPosition :" + navigationCurrentPosition);
        try {
            List<NavigationDestDistanceData> naviDestDistanceData = new ArrayList<>();
            for (int i = 0; i < navigationCurrentPosition.getDestinationDistancesCount(); i++) {
                Protos.NavigationDestinationDistance naviDestDistance = navigationCurrentPosition.getDestinationDistances(i);
                NavigationDestDistanceData ifNavigationDestDistanceData = new NavigationDestDistanceData();
                ifNavigationDestDistanceData.setDisplayValue(naviDestDistance.getDistance().getDisplayValue());
                ifNavigationDestDistanceData.setMeters(naviDestDistance.getDistance().getMeters());
                ifNavigationDestDistanceData.setDisplayUnits(naviDestDistance.getDistance().getDisplayUnits().getNumber());
                ifNavigationDestDistanceData.setEstimatedTime(naviDestDistance.getEstimatedTimeAtArrival());
                ifNavigationDestDistanceData.setTimeSeconds(naviDestDistance.getTimeToArrivalSeconds());
                naviDestDistanceData.add(ifNavigationDestDistanceData);
            }
            NavigationPositionData ifNavigationPositionData = new NavigationPositionData();
            ifNavigationPositionData.setNavigationDestDistanceData(naviDestDistanceData);
            String displayValue = navigationCurrentPosition.getStepDistance().getDistance().getDisplayValue();
            int meters = navigationCurrentPosition.getStepDistance().getDistance().getMeters();
            int displayUnits = navigationCurrentPosition.getStepDistance().getDistance().getDisplayUnits().getNumber();
            long timeSeconds = navigationCurrentPosition.getStepDistance().getTimeToStepSeconds();
            String currentRoad = navigationCurrentPosition.getCurrentRoad().getName();
            ifNavigationPositionData.setDisplayValue(displayValue);
            ifNavigationPositionData.setMeters(meters);
            ifNavigationPositionData.setDisplayUnits(displayUnits);
            ifNavigationPositionData.setTimeSeconds(timeSeconds);
            ifNavigationPositionData.setCurrentRoad(currentRoad);
            if (this.mAapDisplayManager != null) {
                this.mAapDisplayManager.onNavigationCurrentPosition(ifNavigationPositionData);
            }
        } catch (Exception exception) {
            LogUtil.error(TAG, "onNavigationCurrentPosition Exception ", exception);
            exception.printStackTrace();
        }
    }

    @Override // com.google.android.projection.protocol.MediaPlaybackStatus.MediaPlaybackStatusListener
    public void onMediaStatus(final int state, String source, final int playbackSeconds, final boolean shuffle, final boolean repeat, final boolean repeatOne) {
        LogUtil.verbose(TAG, "onMediaStatus state :" + state + " source: " + source + " playbackSeconds: " + playbackSeconds + " shuffle: " + shuffle + " repeat: " + repeat + " repeatOne: " + repeatOne);
        this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.aap.sink.-$$Lambda$InstrumentClusterListener$vwmdlv0Ky4HoXHZtkY1_JtswbSM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onMediaStatus$1$InstrumentClusterListener(shuffle, repeat, repeatOne, state, playbackSeconds);
            }
        });
    }

    public /* synthetic */ void lambda$onMediaStatus$1$InstrumentClusterListener(boolean shuffle, boolean repeat, boolean repeatOne, int state, int playbackSeconds) {
        int shuffleMode;
        int repeatMode;
        if (shuffle) {
            shuffleMode = 1;
        } else {
            shuffleMode = 0;
        }
        if (repeat && repeatOne) {
            repeatMode = 2;
        } else if (repeat) {
            repeatMode = 1;
        } else {
            repeatMode = 0;
        }
        if (this.mMediaMetadata.getShuffleMode() != shuffleMode || this.mMediaMetadata.getRepeatMode() != repeatMode) {
            this.mMediaMetadata.setShuffleMode(shuffleMode);
            this.mMediaMetadata.setRepeatMode(repeatMode);
            AapDisplayManager aapDisplayManager = this.mAapDisplayManager;
            if (aapDisplayManager != null) {
                aapDisplayManager.notifyMediaMetadataChange(this.mMediaMetadata);
            }
        }
        if (state == 2) {
            this.mPlayState = AndroidAutoConst.MusicStatus.MUSIC_STATUS_PLAYING.ordinal();
        } else if (state == 3) {
            this.mPlayState = AndroidAutoConst.MusicStatus.MUSIC_STATUS_PAUSED.ordinal();
        } else if (state == 1) {
            this.mPlayState = AndroidAutoConst.MusicStatus.MUSIC_STATUS_NOT_START.ordinal();
        }
        this.mPlayBackSeconds = playbackSeconds;
        AapDisplayManager aapDisplayManager2 = this.mAapDisplayManager;
        if (aapDisplayManager2 != null) {
            aapDisplayManager2.updateMediaStatus(this.mPlayState);
            this.mAapDisplayManager.notifyMediaProgressChange(playbackSeconds);
        }
    }

    @Override // com.google.android.projection.protocol.MediaPlaybackStatus.MediaPlaybackStatusListener
    public void onMediaMetadata(final String song, final String album, final String artist, final byte[] albumArt, String playlist, final int durationSeconds, int ratings) {
        LogUtil.verbose(TAG, "onMediaMetadata song :" + song + " duration: " + durationSeconds);
        this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.aap.sink.-$$Lambda$InstrumentClusterListener$C0kUR1lZZIq51sTx8k82Rm7ty74
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onMediaMetadata$2$InstrumentClusterListener(song, album, artist, albumArt, durationSeconds);
            }
        });
    }

    public /* synthetic */ void lambda$onMediaMetadata$2$InstrumentClusterListener(String song, String album, String artist, byte[] albumArt, int durationSeconds) {
        this.mMediaMetadata.setSong(song);
        this.mMediaMetadata.setAlbum(album);
        this.mMediaMetadata.setArtist(artist);
        this.mMediaMetadata.setAlbumArt(albumArt);
        this.mMediaMetadata.setDurationSeconds(durationSeconds);
        this.mPlayBackSeconds = 0;
        AapDisplayManager aapDisplayManager = this.mAapDisplayManager;
        if (aapDisplayManager != null) {
            aapDisplayManager.notifyMediaMetadataChange(this.mMediaMetadata);
        }
    }

    @Override // com.google.android.projection.protocol.PhoneStatus.PhoneStatusListener
    public void onPhoneStatus(final Protos.PhoneStatus.Call[] calls, int signalStrength) {
        LogUtil.verbose(TAG, "onPhoneStatus calls length :" + calls.length);
        this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.aap.sink.-$$Lambda$InstrumentClusterListener$yj7PVyPGMd0hn_c4K-DpbzwnMMk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onPhoneStatus$3$InstrumentClusterListener(calls);
            }
        });
    }

    public /* synthetic */ void lambda$onPhoneStatus$3$InstrumentClusterListener(Protos.PhoneStatus.Call[] calls) {
        int status;
        ArrayList<PhoneStatusData> phoneStatusList = new ArrayList<>();
        for (Protos.PhoneStatus.Call call : calls) {
            PhoneStatusData aapPhoneStatus = new PhoneStatusData();
            if (call.hasCallerNumber()) {
                aapPhoneStatus.setCallerNumber(call.getCallerNumber());
            } else {
                aapPhoneStatus.setCallerNumber(AndroidAutoConst.DEFAULT_SCREEN_NAME);
            }
            if (call.hasCallerId()) {
                aapPhoneStatus.setCallerId(call.getCallerId());
            } else {
                aapPhoneStatus.setCallerId(AndroidAutoConst.DEFAULT_SCREEN_NAME);
            }
            if (call.hasCallerNumberType()) {
                aapPhoneStatus.setCallerNumberType(call.getCallerNumberType());
            } else {
                aapPhoneStatus.setCallerNumberType(AndroidAutoConst.DEFAULT_SCREEN_NAME);
            }
            int status2 = call.getPhoneState().getNumber();
            LogUtil.verbose(TAG, "onPhoneStatus status: " + status2);
            switch (status2) {
                case 1:
                    status = AndroidAutoConst.PhoneStatus.CALL_ACTIVED.ordinal();
                    break;
                case 2:
                    status = AndroidAutoConst.PhoneStatus.CALL_HELD.ordinal();
                    break;
                case 3:
                    status = AndroidAutoConst.PhoneStatus.PHONE_DISCONNECTED.ordinal();
                    break;
                case 4:
                    status = AndroidAutoConst.PhoneStatus.INCOMING_CALL.ordinal();
                    break;
                case 5:
                    status = AndroidAutoConst.PhoneStatus.PHONE_CONFERENCED.ordinal();
                    break;
                case 6:
                    status = AndroidAutoConst.PhoneStatus.PHONE_MUTED.ordinal();
                    break;
                default:
                    status = AndroidAutoConst.PhoneStatus.PHONE_DISCONNECTED.ordinal();
                    break;
            }
            aapPhoneStatus.setPhoneStatus(status);
            aapPhoneStatus.setCallerDurationSeconds(call.getCallDurationSeconds());
            if (call.hasCallerThumbnail()) {
                aapPhoneStatus.setCallerThumbnail(call.getCallerThumbnail().toByteArray());
            }
            phoneStatusList.add(aapPhoneStatus);
        }
        AapDisplayManager aapDisplayManager = this.mAapDisplayManager;
        if (aapDisplayManager != null) {
            aapDisplayManager.updatePhoneStatus(phoneStatusList);
        }
    }

    public int getAapMediaPlayState() {
        return this.mPlayState;
    }

    public void clearMediaStateAndMetaData() {
        LogUtil.verbose(TAG, "clearMediaStateAndMetaData is invoke");
        this.mPlayState = AndroidAutoConst.MusicStatus.MUSIC_STATUS_NOT_START.ordinal();
        this.mNaviStatus = Protos.NavigationStatus.NavigationStatusEnum.valueOf(0);
        this.mMediaMetadata.clear();
    }

    public void updateMediaMetaData() {
        MediaMetadata metaData;
        LogUtil.verbose(TAG, "updateMediaMetaData mMediaMetadata:" + this.mMediaMetadata);
        synchronized (this) {
            if (this.mMediaMetadata != null) {
                metaData = this.mMediaMetadata;
            } else {
                metaData = new MediaMetadata();
                metaData.setSong(AndroidAutoConst.DEFAULT_SCREEN_NAME);
                metaData.setAlbum(AndroidAutoConst.DEFAULT_SCREEN_NAME);
                metaData.setArtist(AndroidAutoConst.DEFAULT_SCREEN_NAME);
                metaData.setAlbumArt(null);
                metaData.setDurationSeconds(0);
            }
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("updateMediaMetaData mSubDisplayManager:");
            sb.append(this.mAapDisplayManager == null);
            LogUtil.verbose(str, sb.toString());
            if (this.mAapDisplayManager != null) {
                this.mAapDisplayManager.notifyMediaMetadataChange(metaData);
            }
        }
    }

    public MediaMetadata getMediaMetadata() {
        return this.mMediaMetadata;
    }

    public int getMediaProgress() {
        return this.mPlayBackSeconds;
    }
}
