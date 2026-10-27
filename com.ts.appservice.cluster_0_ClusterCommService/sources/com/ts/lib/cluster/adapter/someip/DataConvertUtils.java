package com.ts.lib.cluster.adapter.someip;

import com.ts.appservice.cluster.ClusterBroadcastReceiver;
import com.ts.lib.cluster.adapter.someip.SomeIpConstants;
import com.ts.lib.cluster.utils.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class DataConvertUtils {
    private static final String TAG = "DataConvertUtils";
    private static volatile DataConvertUtils sInstance = null;
    private static int mBtCallState = 0;
    private static int mEbCallState = 0;
    private static int mCarplayCallState = 0;
    private static int mMusicSource = 0;
    private static String mMusicState = SomeIpConstants.StateMediaState.MEDIA_STATE_PAUSE;
    private static String mRadioState = SomeIpConstants.StateAstState.RADIO_STATE_NORMAL;
    private static int mPhoneType = 0;

    public static synchronized DataConvertUtils getInstance() {
        if (sInstance == null) {
            sInstance = new DataConvertUtils();
        }
        return sInstance;
    }

    private DataConvertUtils() {
    }

    public int musicSourceConvert(int musicSource) {
        if (musicSource == 0) {
            mMusicSource = 0;
        } else if (musicSource == 1) {
            mMusicSource = 6;
        } else if (musicSource == 2) {
            mMusicSource = 2;
        } else if (musicSource == 3) {
            mMusicSource = 7;
        } else if (musicSource == 4) {
            mMusicSource = 13;
        } else if (musicSource == 5) {
            mMusicSource = 14;
        } else if (musicSource == 7) {
            mMusicSource = 8;
        } else {
            LogUtil.logW(TAG, "unexpected musicSource in switch " + musicSource);
        }
        return mMusicSource;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String musicStateConvert(int r3) {
        /*
            r2 = this;
            r0 = -1
            if (r3 == r0) goto L2a
            if (r3 == 0) goto L2a
            r0 = 1
            if (r3 == r0) goto L25
            r0 = 2
            if (r3 == r0) goto L2a
            r0 = 3
            if (r3 == r0) goto L2a
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "unexpected musicState in switch "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "DataConvertUtils"
            com.ts.lib.cluster.utils.LogUtil.logW(r1, r0)
            goto L2f
        L25:
            java.lang.String r0 = "Play"
            com.ts.lib.cluster.adapter.someip.DataConvertUtils.mMusicState = r0
            goto L2f
        L2a:
            java.lang.String r0 = "Pause"
            com.ts.lib.cluster.adapter.someip.DataConvertUtils.mMusicState = r0
        L2f:
            java.lang.String r0 = com.ts.lib.cluster.adapter.someip.DataConvertUtils.mMusicState
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.lib.cluster.adapter.someip.DataConvertUtils.musicStateConvert(int):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String radioStateConvert(int r3, int r4) {
        /*
            r2 = this;
            r0 = -1
            r1 = 1
            if (r4 == r0) goto L2a
            if (r4 == 0) goto L2a
            if (r4 == r1) goto L25
            r0 = 2
            if (r4 == r0) goto L2a
            r0 = 3
            if (r4 == r0) goto L2a
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "unexpected radioState in switch "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "DataConvertUtils"
            com.ts.lib.cluster.utils.LogUtil.logW(r1, r0)
            goto L37
        L25:
            java.lang.String r0 = "Normal"
            com.ts.lib.cluster.adapter.someip.DataConvertUtils.mRadioState = r0
            goto L37
        L2a:
            if (r3 != 0) goto L31
            java.lang.String r0 = "AmSearching"
            com.ts.lib.cluster.adapter.someip.DataConvertUtils.mRadioState = r0
            goto L37
        L31:
            if (r3 != r1) goto L37
            java.lang.String r0 = "FmSearching"
            com.ts.lib.cluster.adapter.someip.DataConvertUtils.mRadioState = r0
        L37:
            java.lang.String r0 = com.ts.lib.cluster.adapter.someip.DataConvertUtils.mRadioState
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.lib.cluster.adapter.someip.DataConvertUtils.radioStateConvert(int, int):java.lang.String");
    }

    public int phoneTypeConvert(int callType) {
        if (callType == 0) {
            mPhoneType = 2;
        } else if (callType != 1) {
            if (callType != 2) {
                if (callType == 3) {
                    mPhoneType = 1;
                } else {
                    LogUtil.logW(TAG, "unexpected callType in switch " + callType);
                }
            }
        } else {
            mPhoneType = 3;
        }
        return mPhoneType;
    }

    public int btCallStateConvert(int callState) {
        if (callState == 0) {
            mBtCallState = 3;
        } else if (callState != 7) {
            if (callState == 2 || callState == 3) {
                mBtCallState = 2;
            } else if (callState == 4 || callState == 5) {
                mBtCallState = 1;
            } else {
                LogUtil.logW(TAG, "unexpected CallState in switch " + callState);
            }
        } else {
            mBtCallState = 4;
        }
        return mBtCallState;
    }

    public int ebCallStateConvert(int callState) {
        if (callState == 0) {
            mEbCallState = 0;
        } else if (callState == 1) {
            mEbCallState = 1;
        } else if (callState == 2) {
            mEbCallState = 2;
        } else if (callState == 3 || callState == 4) {
            mEbCallState = 3;
        } else if (callState == 5) {
            mEbCallState = 4;
        } else {
            LogUtil.logW(TAG, "unexpected CallState in switch " + callState);
        }
        return mEbCallState;
    }

    public int carplayCallStateConvert(int callState) {
        switch (callState) {
            case ClusterBroadcastReceiver.DEFAULT /* -1 */:
                mCarplayCallState = 0;
                break;
            case 0:
                mCarplayCallState = 4;
                break;
            case 1:
                mCarplayCallState = 2;
                break;
            case 2:
                mCarplayCallState = 1;
                break;
            case 3:
            case 5:
            case 6:
                break;
            case 4:
                mCarplayCallState = 3;
                break;
            default:
                LogUtil.logW(TAG, "unexpected CallState in switch " + callState);
                break;
        }
        return mCarplayCallState;
    }

    public int distanceUnitConvert(int unit) {
        if (unit == 0) {
            return 2;
        }
        if (unit == 1) {
            return 3;
        }
        if (unit == 2) {
            return 1;
        }
        if (unit == 3) {
            LogUtil.logW(TAG, "distanceUnit yards ");
            return 1;
        }
        if (unit == 4) {
            LogUtil.logW(TAG, "distanceUnit ft ");
            return 1;
        }
        LogUtil.logW(TAG, "unexpected distanceUnit in switch " + unit);
        return 1;
    }
}
