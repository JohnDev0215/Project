package com.ts.lib.cluster.adapter;

import android.os.Bundle;
import com.ts.lib.cluster.adapter.ClusterConstants;
import com.ts.lib.cluster.utils.LogUtil;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ClusterPackageUtils {
    private static final int ACK_FAILURE = 1;
    private static final int ACK_SUCCESS = 0;
    private static final int DEFAULT_INT = -1;
    private static final int DEFAULT_MEDIA_COVER = -1;
    private static final String DEFAULT_STRING = "default_string";
    private static final int DESTINATION_UNIT = 1;
    private static final int DISTANCE_MARK = 2;
    private static final int EXIST_COVER = 1;
    private static final int GUIDANCE_UNIT = 2;
    private static final String HAVE_MEDIA_COVER = "haveMediaCover";
    private static final int INEXIST_COVER = 0;
    private static final String INVALID_DISTANCE = "-1";
    private static final int INVALID_LANE_SWITCH = -1;
    private static final byte MASK_BYTE = -1;
    private static final int MEDIA_STATE_DEFAULT = -2;
    private static final int MS_TO_S = 1000;
    private static final int NAME_MARK = 1;
    private static final int ONE_HOUR_SECONDS = 3600;
    private static final int ONE_MINUTE_SECONDS = 60;
    private static final int ONE_THOUSAND_METERS = 1000;
    private static final String TAG = "ClusterPackageUtils";
    private static volatile ClusterPackageUtils sInstance = null;
    private byte[] mStateMsg;
    private byte[] mPhoneNumMsg = new byte[64];
    private byte[] mMsgForMediaInfo = new byte[64];
    private byte mMediaPlayStatusForMediaInfo = 0;
    private byte mHasCoverForMediaInfo = 0;
    private byte mMediaSourceForMediaInfo = 0;
    private byte[] mMsgPhoneInfo = new byte[64];
    private byte[] mMsgNaviAlarmInfo = new byte[64];
    private byte[] mMsgNaviInfo = new byte[64];
    private byte destinationUnit = 0;
    private byte guidanceUnit = 0;
    private byte[] mMsgDistanceInfo = new byte[64];
    private byte[] callNameMsg = new byte[64];
    private byte[] mMsgFmData = new byte[64];
    private byte[] mMsgAmData = new byte[64];

    public static ClusterPackageUtils getInstance() {
        if (sInstance == null) {
            synchronized (ClusterPackageUtils.class) {
                if (sInstance == null) {
                    sInstance = new ClusterPackageUtils();
                }
            }
        }
        return sInstance;
    }

    protected byte[] packageMediaName(String inputMediaName) {
        byte[] msg = new byte[64];
        msg[0] = 19;
        if (isEmptyString(inputMediaName)) {
            LogUtil.logE(TAG, "inputMediaName is empty");
            appendEndMark(msg, 1);
            printHexBinary(msg);
            return msg;
        }
        byte[] mediaNameBytes = inputMediaName.getBytes();
        LogUtil.logD(TAG, "mediaNameBytes length " + mediaNameBytes.length);
        if (mediaNameBytes.length > ONE_MINUTE_SECONDS) {
            byte[] tmpBytes = new byte[ONE_MINUTE_SECONDS];
            System.arraycopy(mediaNameBytes, 0, tmpBytes, 0, ONE_MINUTE_SECONDS);
            mediaNameBytes = tmpBytes;
        }
        System.arraycopy(mediaNameBytes, 0, msg, 1, mediaNameBytes.length);
        appendEndMark(msg, 1);
        printHexBinary(msg);
        return msg;
    }

    protected byte[] packageArtistName(String inputArtistName) {
        byte[] msg = new byte[64];
        msg[0] = 35;
        if (isEmptyString(inputArtistName)) {
            LogUtil.logE(TAG, "inputArtistName is empty");
            appendEndMark(msg, 1);
            printHexBinary(msg);
            return msg;
        }
        byte[] artistName = inputArtistName.getBytes();
        LogUtil.logD(TAG, "artistName length " + artistName.length);
        if (artistName.length > ONE_MINUTE_SECONDS) {
            byte[] tmpBytes = new byte[ONE_MINUTE_SECONDS];
            System.arraycopy(artistName, 0, tmpBytes, 0, ONE_MINUTE_SECONDS);
            artistName = tmpBytes;
        }
        System.arraycopy(artistName, 0, msg, 1, artistName.length);
        appendEndMark(msg, 1);
        printHexBinary(msg);
        return msg;
    }

    protected byte[] packageMediaInfo(Bundle bundle) {
        this.mMsgForMediaInfo[0] = 51;
        int inputMediaPlayStatus = bundle.getInt("MediaState", MEDIA_STATE_DEFAULT);
        if (inputMediaPlayStatus != MEDIA_STATE_DEFAULT) {
            this.mMediaPlayStatusForMediaInfo = mediaPlayStatusTransform(inputMediaPlayStatus);
        }
        int haveMediaCover = bundle.getInt(HAVE_MEDIA_COVER, -1);
        if (haveMediaCover == 1) {
            this.mHasCoverForMediaInfo = (byte) 16;
            LogUtil.logD(TAG, "-----------EXIST_COVER----------");
        } else if (haveMediaCover == 0) {
            this.mHasCoverForMediaInfo = (byte) 0;
            LogUtil.logD(TAG, "-----------INEXIST_COVER----------");
        }
        int inputMediaSource = bundle.getInt("MusicSource", -1);
        if (inputMediaSource != -1) {
            this.mMediaSourceForMediaInfo = mediaPlaySourceTransform(inputMediaSource);
        }
        byte[] bArr = this.mMsgForMediaInfo;
        bArr[1] = (byte) (this.mMediaPlayStatusForMediaInfo | this.mHasCoverForMediaInfo);
        bArr[2] = this.mMediaSourceForMediaInfo;
        long inputPlayedTime = bundle.getLong("MediaCurTime", -1L);
        if (inputPlayedTime != -1) {
            byte[] playedTimeBytes = timeTransform(inputPlayedTime / 1000);
            System.arraycopy(playedTimeBytes, 0, this.mMsgForMediaInfo, 3, 2);
        }
        long inputTotalTime = bundle.getLong("MediaTotalTime", -1L);
        if (inputTotalTime != -1) {
            byte[] totalTime = timeTransform(inputTotalTime / 1000);
            System.arraycopy(totalTime, 0, this.mMsgForMediaInfo, 5, 2);
        }
        LogUtil.logD(TAG, "packageMediaInfo  inputMediaPlayStatus " + inputMediaPlayStatus + " haveMediaCover " + haveMediaCover + " inputMediaSource " + inputMediaSource + " inputPlayedTime " + inputPlayedTime + " inputTotalTime " + inputTotalTime);
        printHexBinary(this.mMsgForMediaInfo);
        return this.mMsgForMediaInfo;
    }

    protected byte[] synchronizeStateToCluster(ClusterConstants.IviState state) {
        LogUtil.logD(TAG, "SynchronizeStateToCluster IviState " + state);
        switch (AnonymousClass1.$SwitchMap$com$ts$lib$cluster$adapter$ClusterConstants$IviState[state.ordinal()]) {
            case 1:
                this.mStateMsg[1] = 1;
                break;
            case 2:
                this.mStateMsg[1] = 0;
                break;
            case 3:
                this.mStateMsg[1] = 2;
                break;
            case 4:
                this.mStateMsg[1] = 3;
                break;
            case 5:
                this.mStateMsg[2] = 0;
                break;
            case 6:
                this.mStateMsg[2] = 1;
                break;
            case 7:
                this.mStateMsg[3] = 0;
                break;
            case 8:
                this.mStateMsg[3] = 1;
                break;
            default:
                LogUtil.logE(TAG, "SynchronizeStateToCluster unhandle IviState " + state);
                break;
        }
        printHexBinary(this.mStateMsg);
        return this.mStateMsg;
    }

    /* JADX INFO: renamed from: com.ts.lib.cluster.adapter.ClusterPackageUtils$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ts$lib$cluster$adapter$ClusterConstants$IviState;

        static {
            int[] iArr = new int[ClusterConstants.IviState.values().length];
            $SwitchMap$com$ts$lib$cluster$adapter$ClusterConstants$IviState = iArr;
            try {
                iArr[ClusterConstants.IviState.RADIO_OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ts$lib$cluster$adapter$ClusterConstants$IviState[ClusterConstants.IviState.NO_MEDIA.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$ts$lib$cluster$adapter$ClusterConstants$IviState[ClusterConstants.IviState.MUSIC_OPEN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$ts$lib$cluster$adapter$ClusterConstants$IviState[ClusterConstants.IviState.OTHER_OPEN.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$ts$lib$cluster$adapter$ClusterConstants$IviState[ClusterConstants.IviState.NO_PHONE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$ts$lib$cluster$adapter$ClusterConstants$IviState[ClusterConstants.IviState.CALL_CAME.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$ts$lib$cluster$adapter$ClusterConstants$IviState[ClusterConstants.IviState.NO_NAVI_INFO.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$ts$lib$cluster$adapter$ClusterConstants$IviState[ClusterConstants.IviState.NAVI_INFO_INPUT.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    protected byte[] packagePhoneInfo(int inputPhoneSource, String photoUri, int phoneState, long callTime) {
        byte hasPhoto;
        this.mMsgPhoneInfo[0] = 20;
        if (isEmptyString(photoUri)) {
            hasPhoto = 0;
        } else {
            hasPhoto = 16;
        }
        byte[] callTimeBytes = timeTransform(callTime / 1000);
        byte[] bArr = this.mMsgPhoneInfo;
        bArr[1] = (byte) (inputPhoneSource | hasPhoto);
        updateMsgForInt(bArr, 2, phoneState);
        System.arraycopy(callTimeBytes, 0, this.mMsgPhoneInfo, 3, 2);
        printHexBinary(this.mMsgPhoneInfo);
        return this.mMsgPhoneInfo;
    }

    protected byte[] packagePhoneNum(String phoneNum) {
        Arrays.fill(this.mPhoneNumMsg, (byte) -1);
        this.mPhoneNumMsg[0] = 36;
        if (!isEmptyString(phoneNum) && !phoneNum.equals(DEFAULT_STRING)) {
            byte[] numBytes = phoneNumToBcd(phoneNum);
            System.arraycopy(numBytes, 0, this.mPhoneNumMsg, 1, numBytes.length);
        }
        printHexBinary(this.mPhoneNumMsg);
        return this.mPhoneNumMsg;
    }

    protected byte[] packageNaviAlarmInfo(int naviWarningDiagramIcon, int speedLimitIcon, int navigationalAlarmIcon) {
        byte[] bArr = this.mMsgNaviAlarmInfo;
        bArr[0] = 21;
        updateMsgForInt(bArr, 1, naviWarningDiagramIcon);
        updateMsgForInt(this.mMsgNaviAlarmInfo, 2, speedLimitIcon);
        updateMsgForInt(this.mMsgNaviAlarmInfo, 3, navigationalAlarmIcon);
        printHexBinary(this.mMsgNaviAlarmInfo);
        return this.mMsgNaviAlarmInfo;
    }

    private void updateMsgForInt(byte[] msgs, int num, int data) {
        LogUtil.logD(TAG, "updateMsgForInt num:" + num + " data:" + data);
        if (data != -1 && msgs[num] != ((byte) data)) {
            msgs[num] = (byte) data;
            return;
        }
        LogUtil.logD(TAG, "updateMsgForInt the data is default:" + ((int) msgs[num]));
    }

    protected byte[] packageNaviInfo(Bundle bundle) {
        this.mMsgNaviInfo[0] = 37;
        int guidanceInformation = bundle.getInt("NaviIconTbt", -1);
        LogUtil.logE(TAG, "packageNaviInfo guidanceInformation:" + guidanceInformation);
        updateMsgForInt(this.mMsgNaviInfo, 1, guidanceInformation);
        this.mMsgNaviInfo[2] = (byte) 0;
        int estimateArrivalTimeSecond = bundle.getInt("NaviToDestTime", -1);
        LogUtil.logE(TAG, "packageNaviInfo estimateArrivalTimeSecond:" + estimateArrivalTimeSecond);
        if (estimateArrivalTimeSecond != -1) {
            int estimateArrivalTimeHour = estimateArrivalTimeSecond / ONE_HOUR_SECONDS;
            int estimateArrivalTimeMinute = (estimateArrivalTimeSecond % ONE_HOUR_SECONDS) / ONE_MINUTE_SECONDS;
            byte[] bArr = this.mMsgNaviInfo;
            bArr[3] = (byte) estimateArrivalTimeHour;
            bArr[4] = (byte) estimateArrivalTimeMinute;
        }
        int dataType = bundle.getInt("DataType", 0);
        if (dataType == 16) {
            packageNormalNaviInfo(bundle);
        } else if (dataType == 17) {
            packageCarpalyNaviInfo(bundle);
        } else {
            LogUtil.logE(TAG, "dataType is invalid:  " + dataType);
        }
        printHexBinary(this.mMsgNaviInfo);
        return this.mMsgNaviInfo;
    }

    private void packageNormalNaviInfo(Bundle bundle) {
        LogUtil.logE(TAG, "enter packageNormalNaviInfo");
        int naviIconLaneSwitch = bundle.getInt("NaviIconLaneSwitch", -1);
        int[] roadDirectionBytes = bundle.getIntArray("NaviIconLaneInfo");
        if (naviIconLaneSwitch == 1) {
            LogUtil.logD(TAG, "Hide naviIconLane information");
            for (int i = 0; i < 8; i++) {
                this.mMsgNaviInfo[i + 13] = 0;
            }
        } else if (naviIconLaneSwitch != 2 || roadDirectionBytes == null) {
            LogUtil.logE(TAG, "packageNaviInfo NAVI_ICON_LANE_INFO is null");
        } else {
            LogUtil.logE(TAG, "packageNaviInfo roadDirectionBytes.length:" + roadDirectionBytes.length);
            for (int i2 = 0; i2 < roadDirectionBytes.length; i2++) {
                this.mMsgNaviInfo[i2 + 13] = (byte) roadDirectionBytes[i2];
            }
        }
        int naviNextRoadDistance = bundle.getInt("NaviNextRoadDistance", -1);
        int naviDistanceToDest = bundle.getInt("NaviDistanceToDest", -1);
        if (naviDistanceToDest != -1) {
            if (naviDistanceToDest >= 1000) {
                updateMsgForUnit(0, 1);
            } else {
                updateMsgForUnit(2, 1);
            }
        }
        if (naviNextRoadDistance != -1) {
            if (naviNextRoadDistance >= 1000) {
                updateMsgForUnit(0, 2);
            } else {
                updateMsgForUnit(2, 2);
            }
        }
        LogUtil.logE(TAG, "exit packageNormalNaviInfo");
    }

    private void packageCarpalyNaviInfo(Bundle bundle) {
        LogUtil.logE(TAG, "enter packageCarpalyNaviInfo");
        for (int i = 0; i < 8; i++) {
            this.mMsgNaviInfo[i + 13] = 0;
        }
        int nextDistanceUnit = bundle.getInt("NaviNextDistanceUnit", -1);
        int distanceToDestUnit = bundle.getInt("NaviDistanceToDestUnit", -1);
        LogUtil.logE(TAG, "nextDistanceUnit:  " + nextDistanceUnit + "  distanceToDestUnit:  " + distanceToDestUnit);
        if (nextDistanceUnit != -1) {
            updateMsgForUnit(nextDistanceUnit, 2);
        }
        if (distanceToDestUnit != -1) {
            updateMsgForUnit(distanceToDestUnit, 1);
        }
        LogUtil.logE(TAG, "exit packageCarpalyNaviInfo");
    }

    private void updateMsgForUnit(int unit, int flag) {
        if (flag == 1) {
            byte bDistanceToDestUnitTransform = distanceToDestUnitTransform(unit);
            this.destinationUnit = bDistanceToDestUnitTransform;
            this.mMsgNaviInfo[21] = (byte) (bDistanceToDestUnitTransform | this.guidanceUnit);
        } else if (flag == 2) {
            byte bNextDistanceUnitTransform = nextDistanceUnitTransform(unit);
            this.guidanceUnit = bNextDistanceUnitTransform;
            this.mMsgNaviInfo[21] = (byte) (bNextDistanceUnitTransform | this.destinationUnit);
        }
    }

    protected byte[] packageDistanceInfo(String destination, String guidance) {
        LogUtil.logE(TAG, "enter packageDistanceInfo");
        this.mMsgDistanceInfo[0] = 69;
        if (isEmptyString(destination) || destination.equals(INVALID_DISTANCE)) {
            LogUtil.logE(TAG, "destinationDistance is invalid");
        } else {
            LogUtil.logE(TAG, "destinationDistance is : " + destination);
            updateMsgForDistance(destination, 1);
        }
        if (isEmptyString(guidance) || guidance.equals(INVALID_DISTANCE)) {
            LogUtil.logE(TAG, "guidanceDistance is invalid");
        } else {
            LogUtil.logE(TAG, "guidanceDistance is : " + guidance);
            updateMsgForDistance(guidance, 21);
        }
        appendEndMark(this.mMsgDistanceInfo, 2);
        printHexBinary(this.mMsgDistanceInfo);
        LogUtil.logE(TAG, "exit packageDistanceInfo");
        return this.mMsgDistanceInfo;
    }

    private void updateMsgForDistance(String data, int start) {
        if (!isEmptyString(data)) {
            for (int i = start; i < start + 20; i++) {
                this.mMsgDistanceInfo[i] = -1;
            }
            byte[] distance = data.getBytes();
            LogUtil.logD(TAG, "distance length " + distance.length);
            if (distance.length > 20) {
                byte[] tmpBytes = new byte[20];
                System.arraycopy(distance, 0, tmpBytes, 0, 20);
                distance = tmpBytes;
            }
            System.arraycopy(distance, 0, this.mMsgDistanceInfo, start, distance.length);
            return;
        }
        LogUtil.logE(TAG, "distance is invalid.");
    }

    protected byte[] packageNextIntersectionName(String nextIntersectionName) {
        byte[] msg = new byte[64];
        msg[0] = 53;
        if (isEmptyString(nextIntersectionName)) {
            LogUtil.logE(TAG, "nextIntersectionName is empty");
            appendEndMark(msg, 1);
            printHexBinary(msg);
            return msg;
        }
        byte[] nextIntersectionNameBytes = nextIntersectionName.getBytes();
        LogUtil.logD(TAG, "nextIntersectionNameBytes length " + nextIntersectionNameBytes.length);
        if (nextIntersectionNameBytes.length > ONE_MINUTE_SECONDS) {
            byte[] tmpBytes = new byte[ONE_MINUTE_SECONDS];
            System.arraycopy(nextIntersectionNameBytes, 0, tmpBytes, 0, ONE_MINUTE_SECONDS);
            nextIntersectionNameBytes = tmpBytes;
        }
        System.arraycopy(nextIntersectionNameBytes, 0, msg, 1, nextIntersectionNameBytes.length);
        appendEndMark(msg, 1);
        printHexBinary(msg);
        return msg;
    }

    protected byte[] packageCallName(String inputCallName) {
        Arrays.fill(this.callNameMsg, (byte) -1);
        this.callNameMsg[0] = 52;
        if (isEmptyString(inputCallName)) {
            LogUtil.logE(TAG, "inputCallName is empty");
            appendEndMark(this.callNameMsg, 1);
            printHexBinary(this.callNameMsg);
            return this.callNameMsg;
        }
        byte[] callNameBytes = inputCallName.getBytes();
        LogUtil.logD(TAG, "callNameBytes length " + callNameBytes.length);
        if (callNameBytes.length > ONE_MINUTE_SECONDS) {
            byte[] tmpBytes = new byte[ONE_MINUTE_SECONDS];
            System.arraycopy(callNameBytes, 0, tmpBytes, 0, ONE_MINUTE_SECONDS);
            callNameBytes = tmpBytes;
        }
        System.arraycopy(callNameBytes, 0, this.callNameMsg, 1, callNameBytes.length);
        appendEndMark(this.callNameMsg, 1);
        printHexBinary(this.callNameMsg);
        return this.callNameMsg;
    }

    protected byte[] packageNaviMediaStatus(int multimediaState, int phoneState, int naviState) {
        byte[] msg = new byte[64];
        msg[0] = 6;
        msg[1] = (byte) multimediaState;
        msg[2] = (byte) phoneState;
        msg[3] = (byte) naviState;
        printHexBinary(msg);
        return msg;
    }

    protected byte[] packageFmData(String coverUri, int integerPart, int fractionalPart) {
        this.mMsgFmData[0] = 17;
        byte hasCover = 0;
        if (!isEmptyString(coverUri)) {
            hasCover = 1;
        }
        byte[] bArr = this.mMsgFmData;
        bArr[1] = (byte) (hasCover | 0);
        bArr[2] = (byte) integerPart;
        bArr[3] = (byte) fractionalPart;
        printHexBinary(bArr);
        return this.mMsgFmData;
    }

    protected byte[] packageAmData(String coverUri, int frequency) {
        this.mMsgAmData[0] = 18;
        byte hasCover = 0;
        if (!isEmptyString(coverUri)) {
            hasCover = 1;
        }
        byte[] bArr = this.mMsgAmData;
        bArr[1] = (byte) (hasCover | 0);
        bArr[2] = (byte) ((frequency >>> 8) & (-1));
        bArr[3] = (byte) (frequency & (-1));
        printHexBinary(bArr);
        return this.mMsgAmData;
    }

    protected byte[] packageFullMapModeResp(int ack, byte[] fullMapReq) {
        byte[] msg = new byte[5];
        msg[0] = 1;
        if (fullMapReq != null && fullMapReq.length >= 4) {
            msg[1] = fullMapReq[1];
            msg[2] = fullMapReq[2];
            msg[3] = fullMapReq[3];
        }
        if (ack == 0) {
            msg[4] = 0;
        } else if (ack == 1) {
            msg[4] = 1;
        }
        printHexBinary(msg);
        return msg;
    }

    protected void clearNaviInfor() {
        this.mMsgNaviInfo = null;
        this.mMsgNaviInfo = new byte[64];
        this.mMsgNaviAlarmInfo = null;
        this.mMsgNaviAlarmInfo = new byte[64];
        this.mMsgDistanceInfo = null;
        this.mMsgDistanceInfo = new byte[64];
        LogUtil.logD(TAG, "clearNaviInfor");
    }

    protected void clearMusicInfor() {
        this.mHasCoverForMediaInfo = (byte) 0;
        this.mMediaPlayStatusForMediaInfo = (byte) 0;
        this.mMediaSourceForMediaInfo = (byte) 0;
        this.mMsgAmData = null;
        this.mMsgAmData = new byte[64];
        this.mMsgFmData = null;
        this.mMsgFmData = new byte[64];
        this.mMsgForMediaInfo = null;
        this.mMsgForMediaInfo = new byte[64];
        LogUtil.logD(TAG, "clearMusicInfor");
    }

    protected void clearPhoneInfor() {
        this.mMsgPhoneInfo = null;
        this.mMsgPhoneInfo = new byte[64];
        this.mPhoneNumMsg = null;
        this.mPhoneNumMsg = new byte[64];
        LogUtil.logD(TAG, "clearPhoneInfor");
    }

    private byte[] phoneNumToBcd(String phoneNum) {
        ByteArrayOutputStream numBytes = new ByteArrayOutputStream();
        if (phoneNum.length() % 2 != 0) {
            phoneNum = phoneNum + "?";
        }
        char[] cs = phoneNum.toCharArray();
        for (int i = 0; i < cs.length; i += 2) {
            int low = cs[i] - '0';
            int high = cs[i + 1] - '0';
            numBytes.write((high << 4) | low);
        }
        return numBytes.toByteArray();
    }

    private byte mediaPlayStatusTransform(int inputMediaPlayStatus) {
        byte mediaPlayStatus = 0;
        if (inputMediaPlayStatus == -1) {
            mediaPlayStatus = 0;
        } else if (inputMediaPlayStatus == 0) {
            mediaPlayStatus = 2;
        } else if (inputMediaPlayStatus == 1) {
            mediaPlayStatus = 1;
        } else if (inputMediaPlayStatus == 2) {
            mediaPlayStatus = 3;
        } else if (inputMediaPlayStatus == 3) {
            mediaPlayStatus = 4;
        } else {
            LogUtil.logE(TAG, "unhandled mediaPlayStatus " + inputMediaPlayStatus);
        }
        LogUtil.logD(TAG, "mediaPlayStatusTransform " + ((int) mediaPlayStatus));
        return mediaPlayStatus;
    }

    private boolean isEmptyString(String inputStr) {
        if (inputStr == null || inputStr.length() == 0) {
            return true;
        }
        return false;
    }

    private byte mediaPlaySourceTransform(int inputMediaSource) {
        byte mediaSource = 0;
        if (inputMediaSource == 0) {
            mediaSource = 0;
        } else if (inputMediaSource == 1) {
            mediaSource = 2;
        } else if (inputMediaSource == 2) {
            mediaSource = 1;
        } else if (inputMediaSource == 3) {
            mediaSource = 0;
        } else if (inputMediaSource == 4) {
            mediaSource = 3;
        } else if (inputMediaSource == 5) {
            mediaSource = 4;
        } else {
            LogUtil.logE(TAG, "unexpected inputMediaSource in switch " + inputMediaSource);
        }
        LogUtil.logD(TAG, "mediaPlaySourceTransform " + ((int) mediaSource));
        return mediaSource;
    }

    private byte nextDistanceUnitTransform(int nextDistanceUnit) {
        if (nextDistanceUnit == 0) {
            return (byte) 16;
        }
        if (nextDistanceUnit == 1) {
            return (byte) 32;
        }
        if (nextDistanceUnit == 2) {
            return (byte) 48;
        }
        if (nextDistanceUnit == 3) {
            return (byte) 64;
        }
        if (nextDistanceUnit == 4) {
            return ClusterConstants.FormatOfTextData.NaviData.NextDistanceUnit.UNIT_FT;
        }
        LogUtil.logE(TAG, "unexpected nextDistanceUnit in switch " + nextDistanceUnit);
        return (byte) 48;
    }

    private byte distanceToDestUnitTransform(int distanceToDestUnit) {
        if (distanceToDestUnit != 0) {
            if (distanceToDestUnit == 1) {
                return (byte) 2;
            }
            if (distanceToDestUnit == 2) {
                return (byte) 3;
            }
            if (distanceToDestUnit == 3) {
                return (byte) 4;
            }
            if (distanceToDestUnit == 4) {
                return (byte) 5;
            }
            LogUtil.logE(TAG, "unexpected distanceToDestUnit in switch " + distanceToDestUnit);
            return (byte) 3;
        }
        return (byte) 1;
    }

    private byte[] timeTransform(long inputTime) {
        short time = (short) inputTime;
        byte[] timeBytes = {(byte) ((time >>> 8) & (-1)), (byte) (time & (-1))};
        return timeBytes;
    }

    private void appendEndMark(byte[] msg, int type) {
        if (type == 1) {
            msg[61] = -1;
            msg[62] = -1;
            msg[63] = -1;
        } else if (type == 2) {
            for (int i = 41; i < 64; i++) {
                msg[i] = -1;
            }
        }
    }

    protected void printHexBinary(byte[] data) {
        char[] hexCode = "0123456789ABCDEF".toCharArray();
        StringBuilder strBuilder = new StringBuilder(data.length * 2);
        short byteCount = 0;
        for (byte b : data) {
            strBuilder.append("[");
            strBuilder.append(hexCode[(b >> 4) & 15]);
            strBuilder.append(hexCode[b & 15]);
            strBuilder.append("]");
            byteCount = (short) (byteCount + 1);
            if (byteCount % 16 == 0) {
                strBuilder.append("\n");
            }
        }
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        String caller = "unknown";
        if (stack.length >= 4) {
            caller = stack[3].getMethodName();
        }
        LogUtil.logD(TAG, "printHexBinary===> " + caller + " size:" + data.length + "\n" + strBuilder.toString());
    }

    private ClusterPackageUtils() {
        byte[] bArr = new byte[64];
        this.mStateMsg = bArr;
        bArr[0] = 6;
    }

    private byte[] intToByteArray(int i) {
        byte[] result = {(byte) ((i >> 24) & (-1)), (byte) ((i >> 16) & (-1)), (byte) ((i >> 8) & (-1)), (byte) (i & (-1))};
        return result;
    }
}
