package com.byd.vrassistant.conversation;

import android.app.UiStyleConfig;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.TagUtil;

/* JADX INFO: loaded from: classes.dex */
public class HvacCheckHelper {
    private static final String TAG = TagUtil.getTagName(HvacCheckHelper.class);
    private BYDAutoAcDevice acDevice;
    private BYDAutoStudy autoStudy;
    private BYDAutoBodyworkDevice bodyworkDevice;
    private BYDAutoSettingDevice settingDevice;
    private UiStyleConfig uiStyleConfig;

    private static final class SingletonHolder {
        private static final HvacCheckHelper INSTANCE = new HvacCheckHelper();

        private SingletonHolder() {
        }
    }

    public static HvacCheckHelper getInstance() {
        RLog.i(TAG, "getInstance()");
        return SingletonHolder.INSTANCE;
    }

    private HvacCheckHelper() {
        try {
            RLog.i(TAG, "HvacCheckHelper()");
            this.acDevice = BYDAutoAcDevice.getInstance(BydVoiceApplication.getContext());
            this.bodyworkDevice = BYDAutoBodyworkDevice.getInstance(BydVoiceApplication.getContext());
            this.settingDevice = BYDAutoSettingDevice.getInstance(BydVoiceApplication.getContext());
            this.uiStyleConfig = UiStyleConfig.getInstance(BydVoiceApplication.getContext());
            this.autoStudy = BYDAutoStudy.getInstance(BydVoiceApplication.getContext());
        } catch (Exception e) {
            RLog.i(TAG, "HvacCheckHelper Exception ", e);
        }
    }

    public boolean checkAcOn() {
        try {
            String str = TAG;
            RLog.i(str, "HvacCheckHelper checkAcOn");
            int powerLevel = this.bodyworkDevice.getPowerLevel();
            RLog.i(str, "getPowerLevel = " + powerLevel);
            return powerLevel == 1 || powerLevel == 2;
        } catch (Exception e) {
            RLog.i(TAG, "HvacCheckHelper Exception ", e);
            return false;
        }
    }

    public boolean checkAcOff() {
        return checkAcOn();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:132:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x054d  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x071a A[Catch: Exception -> 0x09ad, TryCatch #2 {Exception -> 0x09ad, blocks: (B:235:0x05f0, B:237:0x060b, B:239:0x0612, B:238:0x060f, B:240:0x0633, B:245:0x065f, B:247:0x0666, B:246:0x0663, B:248:0x0687, B:250:0x069a, B:253:0x06a4, B:255:0x06c6, B:257:0x06d4, B:263:0x06f5, B:264:0x06fb, B:285:0x07a0, B:294:0x07fa, B:296:0x0801, B:295:0x07fe, B:268:0x071a, B:256:0x06ce, B:269:0x0723, B:271:0x0745, B:273:0x0753, B:279:0x0774, B:280:0x077a, B:284:0x0799, B:272:0x074d), top: B:441:0x01e9 }] */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0799 A[Catch: Exception -> 0x09ad, TryCatch #2 {Exception -> 0x09ad, blocks: (B:235:0x05f0, B:237:0x060b, B:239:0x0612, B:238:0x060f, B:240:0x0633, B:245:0x065f, B:247:0x0666, B:246:0x0663, B:248:0x0687, B:250:0x069a, B:253:0x06a4, B:255:0x06c6, B:257:0x06d4, B:263:0x06f5, B:264:0x06fb, B:285:0x07a0, B:294:0x07fa, B:296:0x0801, B:295:0x07fe, B:268:0x071a, B:256:0x06ce, B:269:0x0723, B:271:0x0745, B:273:0x0753, B:279:0x0774, B:280:0x077a, B:284:0x0799, B:272:0x074d), top: B:441:0x01e9 }] */
    /* JADX WARN: Removed duplicated region for block: B:338:0x08d9  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x0a60  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01ba  */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.byd.vrassistant.conversation.HvacCheckHelper] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v29, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v41 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r43v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r43v2 */
    /* JADX WARN: Type inference failed for: r43v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v26 */
    /* JADX WARN: Type inference failed for: r8v27 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void checkHvacFunctionAvailability(cerence.ark.assistant.controller.domain.hvac.param.CheckHvacFunctionAvailabilityParam r51, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.hvac.result.CheckHvacFunctionAvailabilityResult> r52) {
        /*
            Method dump skipped, instruction units count: 3274
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.HvacCheckHelper.checkHvacFunctionAvailability(cerence.ark.assistant.controller.domain.hvac.param.CheckHvacFunctionAvailabilityParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    private boolean acIsOnLine() {
        int iHasMessage = this.bodyworkDevice.hasMessage(59);
        String str = TAG;
        RLog.i(str, "acIsOnLine: acOnLine = " + iHasMessage);
        if (iHasMessage == 1) {
            RLog.i(str, "acIsOnLine: 空调在线");
            return true;
        }
        RLog.i(str, "acIsOnLine: 空调不在线");
        return false;
    }
}
