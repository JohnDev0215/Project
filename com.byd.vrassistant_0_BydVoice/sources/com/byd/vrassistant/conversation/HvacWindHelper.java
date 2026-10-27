package com.byd.vrassistant.conversation;

import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.hvac.param.HvacInterfaceWaitTimerParam;
import cerence.ark.assistant.controller.domain.hvac.result.IsPowerOnResult;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.TagUtil;

/* JADX INFO: loaded from: classes.dex */
public class HvacWindHelper {
    private static final String TAG = TagUtil.getTagName(HvacWindHelper.class);
    private BYDAutoAcDevice acDevice;
    private BYDAutoBodyworkDevice bodyworkDevice;

    private static final class SingletonHolder {
        private static final HvacWindHelper INSTANCE = new HvacWindHelper();

        private SingletonHolder() {
        }
    }

    public static HvacWindHelper getInstance() {
        RLog.i(TAG, "getInstance()");
        return SingletonHolder.INSTANCE;
    }

    private HvacWindHelper() {
        try {
            RLog.i(TAG, "HvacWindHelper()");
            this.acDevice = BYDAutoAcDevice.getInstance(BydVoiceApplication.getContext());
            this.bodyworkDevice = BYDAutoBodyworkDevice.getInstance(BydVoiceApplication.getContext());
        } catch (Exception e) {
            RLog.i(TAG, "HvacWindHelper Exception ", e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getDefrosterState(cerence.ark.assistant.controller.domain.hvac.param.GetDefrosterStateParam r20, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.hvac.result.GetDefrosterStateResult> r21) {
        /*
            Method dump skipped, instruction units count: 481
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.HvacWindHelper.getDefrosterState(cerence.ark.assistant.controller.domain.hvac.param.GetDefrosterStateParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x00f8, code lost:
    
        if (r6 == 1) goto L123;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:19:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x026f A[Catch: Exception -> 0x0330, TryCatch #0 {Exception -> 0x0330, blocks: (B:74:0x0200, B:77:0x0210, B:83:0x0257, B:78:0x0227, B:80:0x0238, B:82:0x0247, B:85:0x026f, B:93:0x02ac, B:89:0x02a3, B:94:0x02bc, B:96:0x02c2, B:98:0x02cb, B:100:0x02da, B:106:0x0318, B:101:0x02ed, B:103:0x02f9, B:105:0x0308), top: B:113:0x0092 }] */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v17, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v23 */
    /* JADX WARN: Type inference failed for: r10v24 */
    /* JADX WARN: Type inference failed for: r10v26 */
    /* JADX WARN: Type inference failed for: r10v28 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v25 */
    /* JADX WARN: Type inference failed for: r4v26 */
    /* JADX WARN: Type inference failed for: r4v27 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setDefrosterState(cerence.ark.assistant.controller.domain.hvac.param.SetDefrosterStateParam r26, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r27) {
        /*
            Method dump skipped, instruction units count: 833
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.HvacWindHelper.setDefrosterState(cerence.ark.assistant.controller.domain.hvac.param.SetDefrosterStateParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0095  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getFanDirection(cerence.ark.assistant.controller.domain.hvac.param.GetFanDirectionParam r13, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.hvac.result.GetFanDirectionResult> r14) {
        /*
            Method dump skipped, instruction units count: 408
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.HvacWindHelper.getFanDirection(cerence.ark.assistant.controller.domain.hvac.param.GetFanDirectionParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x02fa A[Catch: Exception -> 0x03bc, TryCatch #1 {Exception -> 0x03bc, blocks: (B:48:0x0112, B:50:0x0123, B:53:0x0137, B:55:0x0146, B:57:0x014f, B:65:0x01ab, B:68:0x01d1, B:69:0x01d7, B:78:0x0205, B:79:0x0237, B:81:0x023d, B:83:0x024b, B:88:0x0285, B:90:0x028e, B:130:0x034a, B:131:0x0351, B:95:0x02a3, B:97:0x02ac, B:102:0x02c2, B:104:0x02cb, B:109:0x02e0, B:111:0x02e9, B:114:0x02fa, B:116:0x0303, B:120:0x0316, B:122:0x031f, B:125:0x0330, B:127:0x0339, B:71:0x01db, B:72:0x01e2, B:73:0x01ec, B:74:0x01f0, B:75:0x01f7, B:76:0x01fb, B:77:0x0202, B:61:0x0179, B:63:0x0189), top: B:160:0x00ff }] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x034a A[Catch: Exception -> 0x03bc, TryCatch #1 {Exception -> 0x03bc, blocks: (B:48:0x0112, B:50:0x0123, B:53:0x0137, B:55:0x0146, B:57:0x014f, B:65:0x01ab, B:68:0x01d1, B:69:0x01d7, B:78:0x0205, B:79:0x0237, B:81:0x023d, B:83:0x024b, B:88:0x0285, B:90:0x028e, B:130:0x034a, B:131:0x0351, B:95:0x02a3, B:97:0x02ac, B:102:0x02c2, B:104:0x02cb, B:109:0x02e0, B:111:0x02e9, B:114:0x02fa, B:116:0x0303, B:120:0x0316, B:122:0x031f, B:125:0x0330, B:127:0x0339, B:71:0x01db, B:72:0x01e2, B:73:0x01ec, B:74:0x01f0, B:75:0x01f7, B:76:0x01fb, B:77:0x0202, B:61:0x0179, B:63:0x0189), top: B:160:0x00ff }] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0351 A[Catch: Exception -> 0x03bc, TRY_LEAVE, TryCatch #1 {Exception -> 0x03bc, blocks: (B:48:0x0112, B:50:0x0123, B:53:0x0137, B:55:0x0146, B:57:0x014f, B:65:0x01ab, B:68:0x01d1, B:69:0x01d7, B:78:0x0205, B:79:0x0237, B:81:0x023d, B:83:0x024b, B:88:0x0285, B:90:0x028e, B:130:0x034a, B:131:0x0351, B:95:0x02a3, B:97:0x02ac, B:102:0x02c2, B:104:0x02cb, B:109:0x02e0, B:111:0x02e9, B:114:0x02fa, B:116:0x0303, B:120:0x0316, B:122:0x031f, B:125:0x0330, B:127:0x0339, B:71:0x01db, B:72:0x01e2, B:73:0x01ec, B:74:0x01f0, B:75:0x01f7, B:76:0x01fb, B:77:0x0202, B:61:0x0179, B:63:0x0189), top: B:160:0x00ff }] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0378 A[Catch: Exception -> 0x03ba, TryCatch #2 {Exception -> 0x03ba, blocks: (B:138:0x0367, B:144:0x0378, B:145:0x03a1), top: B:161:0x027f }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01d1 A[Catch: Exception -> 0x03bc, TRY_ENTER, TryCatch #1 {Exception -> 0x03bc, blocks: (B:48:0x0112, B:50:0x0123, B:53:0x0137, B:55:0x0146, B:57:0x014f, B:65:0x01ab, B:68:0x01d1, B:69:0x01d7, B:78:0x0205, B:79:0x0237, B:81:0x023d, B:83:0x024b, B:88:0x0285, B:90:0x028e, B:130:0x034a, B:131:0x0351, B:95:0x02a3, B:97:0x02ac, B:102:0x02c2, B:104:0x02cb, B:109:0x02e0, B:111:0x02e9, B:114:0x02fa, B:116:0x0303, B:120:0x0316, B:122:0x031f, B:125:0x0330, B:127:0x0339, B:71:0x01db, B:72:0x01e2, B:73:0x01ec, B:74:0x01f0, B:75:0x01f7, B:76:0x01fb, B:77:0x0202, B:61:0x0179, B:63:0x0189), top: B:160:0x00ff }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x029f  */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5, types: [int] */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setFanDirection(cerence.ark.assistant.controller.domain.hvac.param.SetFanDirectionParam r26, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r27) {
        /*
            Method dump skipped, instruction units count: 1038
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.HvacWindHelper.setFanDirection(cerence.ark.assistant.controller.domain.hvac.param.SetFanDirectionParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    public void hvacInterfaceWaitTimer(HvacInterfaceWaitTimerParam hvacInterfaceWaitTimerParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "hvacInterfaceWaitTimer: json = " + hvacInterfaceWaitTimerParam.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomHvacDomain】,api【hvacInterfaceWaitTimer】");
            int time = hvacInterfaceWaitTimerParam.getTime();
            if (time <= 0) {
                time = 300;
            }
            Thread.sleep(time);
            RLog.i(str, "hvacInterfaceWaitTimer: OK");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomHvacDomain】,api【hvacInterfaceWaitTimer】,result【OK】");
            iResponseSender.send(new Response("OK"));
        } catch (Exception unused) {
            RLog.i(TAG, "hvacInterfaceWaitTimer: Exception error");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomHvacDomain】,api【hvacInterfaceWaitTimer】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    public void isPowerOn(IResponseSender<IsPowerOnResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "isPowerOn: ");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomHvacDomain】,api【isPowerOn】");
            IsPowerOnResult isPowerOnResult = new IsPowerOnResult();
            int powerLevel = this.bodyworkDevice.getPowerLevel();
            RLog.i(str, "getPowerState: powerState = " + powerLevel);
            boolean z = true;
            if (powerLevel != 1 && powerLevel != 2) {
                z = false;
            }
            isPowerOnResult.setIsOn(z);
            RLog.i(str, "isPowerOn: OK");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomHvacDomain】,api【isPowerOn】,result【OK】");
            iResponseSender.send(new Response("OK", isPowerOnResult));
        } catch (Exception unused) {
            RLog.i(TAG, "isPowerOn: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomHvacDomain】,api【isPowerOn】,result【ERROR】");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    private void sleep300() {
        try {
            RLog.i(TAG, "sleep 300ms");
            Thread.sleep(300L);
        } catch (Exception e) {
            RLog.i(TAG, "sleep Exception ", e);
        }
    }
}
