package com.byd.vrassistant.conversation;

import android.content.Context;
import android.hardware.bydauto.ac.AbsBYDAutoAcListener;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.util.Log;
import cerence.ark.assistant.api.ArkAssistant;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.context.grpc.UiContextGrpc;
import cerence.ark.assistant.controller.domain.hvac.HVACRequestsEnum;
import cerence.ark.assistant.controller.domain.hvac.result.GetHvacMinTempUnitResult;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.TagUtil;
import com.byd.vrassistant.utils.TtsUtils;

/* JADX INFO: loaded from: classes.dex */
public class HvacTemperatureHelper {
    private static final String TAG = TagUtil.getTagName(HvacTemperatureHelper.class);
    private BYDAutoAcDevice acDevice;
    private BYDAutoInstrumentDevice instrumentDevice;
    private AbsBYDAutoAcListener listener;
    private BYDAutoSettingDevice settingDevice;

    private static double toC(double d) {
        return (d - 32.0d) / 1.8d;
    }

    private static double toF(double d) {
        return (d * 1.8d) + 32.0d;
    }

    private static final class SingletonHolder {
        private static final HvacTemperatureHelper INSTANCE = new HvacTemperatureHelper();

        private SingletonHolder() {
        }
    }

    public static HvacTemperatureHelper getInstance() {
        RLog.i(TAG, "getInstance()");
        return SingletonHolder.INSTANCE;
    }

    private HvacTemperatureHelper() {
        try {
            RLog.i(TAG, "HvacTemperatureHelper()");
            this.acDevice = BYDAutoAcDevice.getInstance(BydVoiceApplication.getContext());
            this.instrumentDevice = BYDAutoInstrumentDevice.getInstance(BydVoiceApplication.getContext());
            this.settingDevice = BYDAutoSettingDevice.getInstance(BydVoiceApplication.getContext());
            setTemperListener();
        } catch (Exception e) {
            RLog.i(TAG, "HvacTemperatureHelper Exception ", e);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:39:0x010b  */
    /* JADX WARN: Type inference failed for: r0v10, types: [cerence.ark.assistant.controller.struct.ACTemperatureInfo, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v6, types: [cerence.ark.assistant.controller.struct.ACTemperatureInfo, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v8, types: [cerence.ark.assistant.controller.struct.ACTemperatureInfo, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r3v6, types: [cerence.ark.assistant.controller.struct.ACTemperatureInfo, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v16, types: [cerence.ark.assistant.controller.struct.ACTemperatureInfo, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v18, types: [cerence.ark.assistant.controller.struct.ACTemperatureInfo, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v19, types: [cerence.ark.assistant.controller.struct.ACTemperatureInfo, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v21, types: [cerence.ark.assistant.controller.struct.ACTemperatureInfo, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v22, types: [cerence.ark.assistant.controller.struct.ACTemperatureInfo, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v26, types: [cerence.ark.assistant.controller.struct.ACTemperatureInfo, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v27, types: [cerence.ark.assistant.controller.struct.ACTemperatureInfo, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v3, types: [cerence.ark.assistant.controller.struct.ACTemperatureInfo, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v1, types: [cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.controller.domain.hvac.result.GetACTemperatureResult] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getACTemperature(cerence.ark.assistant.controller.domain.hvac.param.GetACTemperatureParam r17, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.hvac.result.GetACTemperatureResult> r18) {
        /*
            Method dump skipped, instruction units count: 784
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.HvacTemperatureHelper.getACTemperature(cerence.ark.assistant.controller.domain.hvac.param.GetACTemperatureParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:126:0x039e  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x044a  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0471 A[Catch: Exception -> 0x1062, TryCatch #11 {Exception -> 0x1062, blocks: (B:156:0x045f, B:158:0x0471, B:166:0x0488, B:160:0x0479, B:162:0x047d), top: B:604:0x045f }] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0479 A[Catch: Exception -> 0x1062, TryCatch #11 {Exception -> 0x1062, blocks: (B:156:0x045f, B:158:0x0471, B:166:0x0488, B:160:0x0479, B:162:0x047d), top: B:604:0x045f }] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x04a0 A[Catch: Exception -> 0x105c, TryCatch #7 {Exception -> 0x105c, blocks: (B:168:0x048e, B:170:0x04a0, B:172:0x04b5), top: B:597:0x048e }] */
    /* JADX WARN: Removed duplicated region for block: B:182:0x04ea  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0502  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0541  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x054c A[Catch: Exception -> 0x0535, TryCatch #2 {Exception -> 0x0535, blocks: (B:186:0x0504, B:188:0x0517, B:193:0x0523, B:200:0x054c, B:202:0x0565, B:207:0x0571, B:211:0x058d, B:213:0x061b, B:215:0x0627, B:217:0x062f, B:219:0x0650, B:231:0x0691, B:235:0x06d0, B:236:0x06d4, B:264:0x073f, B:238:0x06d8, B:241:0x06e2, B:244:0x06ec, B:247:0x06f6, B:250:0x0700, B:218:0x0640, B:221:0x0657, B:223:0x065b, B:225:0x0663, B:227:0x066b, B:229:0x068c, B:228:0x067c), top: B:587:0x0504 }] */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0581  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x061b A[Catch: Exception -> 0x0535, TryCatch #2 {Exception -> 0x0535, blocks: (B:186:0x0504, B:188:0x0517, B:193:0x0523, B:200:0x054c, B:202:0x0565, B:207:0x0571, B:211:0x058d, B:213:0x061b, B:215:0x0627, B:217:0x062f, B:219:0x0650, B:231:0x0691, B:235:0x06d0, B:236:0x06d4, B:264:0x073f, B:238:0x06d8, B:241:0x06e2, B:244:0x06ec, B:247:0x06f6, B:250:0x0700, B:218:0x0640, B:221:0x0657, B:223:0x065b, B:225:0x0663, B:227:0x066b, B:229:0x068c, B:228:0x067c), top: B:587:0x0504 }] */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0653  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x065b A[Catch: Exception -> 0x0535, TryCatch #2 {Exception -> 0x0535, blocks: (B:186:0x0504, B:188:0x0517, B:193:0x0523, B:200:0x054c, B:202:0x0565, B:207:0x0571, B:211:0x058d, B:213:0x061b, B:215:0x0627, B:217:0x062f, B:219:0x0650, B:231:0x0691, B:235:0x06d0, B:236:0x06d4, B:264:0x073f, B:238:0x06d8, B:241:0x06e2, B:244:0x06ec, B:247:0x06f6, B:250:0x0700, B:218:0x0640, B:221:0x0657, B:223:0x065b, B:225:0x0663, B:227:0x066b, B:229:0x068c, B:228:0x067c), top: B:587:0x0504 }] */
    /* JADX WARN: Removed duplicated region for block: B:230:0x068f A[PHI: r0 r4 r12 r14 r19
  0x068f: PHI (r0v105 double) = (r0v116 double), (r0v117 double) binds: [B:222:0x0659, B:219:0x0650] A[DONT_GENERATE, DONT_INLINE]
  0x068f: PHI (r4v50 double) = (r4v2 double), (r4v54 double) binds: [B:222:0x0659, B:219:0x0650] A[DONT_GENERATE, DONT_INLINE]
  0x068f: PHI (r12v77 java.lang.String) = (r12v35 java.lang.String), (r12v78 java.lang.String) binds: [B:222:0x0659, B:219:0x0650] A[DONT_GENERATE, DONT_INLINE]
  0x068f: PHI (r14v29 double) = (r14v37 double), (r14v38 double) binds: [B:222:0x0659, B:219:0x0650] A[DONT_GENERATE, DONT_INLINE]
  0x068f: PHI (r19v9 double) = (r19v2 double), (r19v10 double) binds: [B:222:0x0659, B:219:0x0650] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:233:0x06cd  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x06cf  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x06d7  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x06d8 A[Catch: Exception -> 0x0535, TryCatch #2 {Exception -> 0x0535, blocks: (B:186:0x0504, B:188:0x0517, B:193:0x0523, B:200:0x054c, B:202:0x0565, B:207:0x0571, B:211:0x058d, B:213:0x061b, B:215:0x0627, B:217:0x062f, B:219:0x0650, B:231:0x0691, B:235:0x06d0, B:236:0x06d4, B:264:0x073f, B:238:0x06d8, B:241:0x06e2, B:244:0x06ec, B:247:0x06f6, B:250:0x0700, B:218:0x0640, B:221:0x0657, B:223:0x065b, B:225:0x0663, B:227:0x066b, B:229:0x068c, B:228:0x067c), top: B:587:0x0504 }] */
    /* JADX WARN: Removed duplicated region for block: B:241:0x06e2 A[Catch: Exception -> 0x0535, TryCatch #2 {Exception -> 0x0535, blocks: (B:186:0x0504, B:188:0x0517, B:193:0x0523, B:200:0x054c, B:202:0x0565, B:207:0x0571, B:211:0x058d, B:213:0x061b, B:215:0x0627, B:217:0x062f, B:219:0x0650, B:231:0x0691, B:235:0x06d0, B:236:0x06d4, B:264:0x073f, B:238:0x06d8, B:241:0x06e2, B:244:0x06ec, B:247:0x06f6, B:250:0x0700, B:218:0x0640, B:221:0x0657, B:223:0x065b, B:225:0x0663, B:227:0x066b, B:229:0x068c, B:228:0x067c), top: B:587:0x0504 }] */
    /* JADX WARN: Removed duplicated region for block: B:244:0x06ec A[Catch: Exception -> 0x0535, TryCatch #2 {Exception -> 0x0535, blocks: (B:186:0x0504, B:188:0x0517, B:193:0x0523, B:200:0x054c, B:202:0x0565, B:207:0x0571, B:211:0x058d, B:213:0x061b, B:215:0x0627, B:217:0x062f, B:219:0x0650, B:231:0x0691, B:235:0x06d0, B:236:0x06d4, B:264:0x073f, B:238:0x06d8, B:241:0x06e2, B:244:0x06ec, B:247:0x06f6, B:250:0x0700, B:218:0x0640, B:221:0x0657, B:223:0x065b, B:225:0x0663, B:227:0x066b, B:229:0x068c, B:228:0x067c), top: B:587:0x0504 }] */
    /* JADX WARN: Removed duplicated region for block: B:247:0x06f6 A[Catch: Exception -> 0x0535, TryCatch #2 {Exception -> 0x0535, blocks: (B:186:0x0504, B:188:0x0517, B:193:0x0523, B:200:0x054c, B:202:0x0565, B:207:0x0571, B:211:0x058d, B:213:0x061b, B:215:0x0627, B:217:0x062f, B:219:0x0650, B:231:0x0691, B:235:0x06d0, B:236:0x06d4, B:264:0x073f, B:238:0x06d8, B:241:0x06e2, B:244:0x06ec, B:247:0x06f6, B:250:0x0700, B:218:0x0640, B:221:0x0657, B:223:0x065b, B:225:0x0663, B:227:0x066b, B:229:0x068c, B:228:0x067c), top: B:587:0x0504 }] */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0700 A[Catch: Exception -> 0x0535, TRY_LEAVE, TryCatch #2 {Exception -> 0x0535, blocks: (B:186:0x0504, B:188:0x0517, B:193:0x0523, B:200:0x054c, B:202:0x0565, B:207:0x0571, B:211:0x058d, B:213:0x061b, B:215:0x0627, B:217:0x062f, B:219:0x0650, B:231:0x0691, B:235:0x06d0, B:236:0x06d4, B:264:0x073f, B:238:0x06d8, B:241:0x06e2, B:244:0x06ec, B:247:0x06f6, B:250:0x0700, B:218:0x0640, B:221:0x0657, B:223:0x065b, B:225:0x0663, B:227:0x066b, B:229:0x068c, B:228:0x067c), top: B:587:0x0504 }] */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0708  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0731  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x0ac5  */
    /* JADX WARN: Removed duplicated region for block: B:531:0x0edc  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0268  */
    /* JADX WARN: Type inference failed for: r0v118 */
    /* JADX WARN: Type inference failed for: r0v119 */
    /* JADX WARN: Type inference failed for: r0v120 */
    /* JADX WARN: Type inference failed for: r0v19, types: [double] */
    /* JADX WARN: Type inference failed for: r0v38, types: [double] */
    /* JADX WARN: Type inference failed for: r0v81, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r10v28 */
    /* JADX WARN: Type inference failed for: r10v29, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v3, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r10v38, types: [cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.controller.domain.hvac.result.SetACTemperatureResult] */
    /* JADX WARN: Type inference failed for: r10v41 */
    /* JADX WARN: Type inference failed for: r10v42 */
    /* JADX WARN: Type inference failed for: r10v44 */
    /* JADX WARN: Type inference failed for: r10v45 */
    /* JADX WARN: Type inference failed for: r10v53 */
    /* JADX WARN: Type inference failed for: r10v58 */
    /* JADX WARN: Type inference failed for: r10v59 */
    /* JADX WARN: Type inference failed for: r10v60 */
    /* JADX WARN: Type inference failed for: r10v61 */
    /* JADX WARN: Type inference failed for: r10v62 */
    /* JADX WARN: Type inference failed for: r10v63 */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v16, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v27 */
    /* JADX WARN: Type inference failed for: r12v28 */
    /* JADX WARN: Type inference failed for: r12v29 */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v30 */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r12v40, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v44 */
    /* JADX WARN: Type inference failed for: r12v45 */
    /* JADX WARN: Type inference failed for: r12v47 */
    /* JADX WARN: Type inference failed for: r12v49 */
    /* JADX WARN: Type inference failed for: r12v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v50 */
    /* JADX WARN: Type inference failed for: r12v53 */
    /* JADX WARN: Type inference failed for: r12v59 */
    /* JADX WARN: Type inference failed for: r12v61 */
    /* JADX WARN: Type inference failed for: r12v62 */
    /* JADX WARN: Type inference failed for: r12v63 */
    /* JADX WARN: Type inference failed for: r12v64 */
    /* JADX WARN: Type inference failed for: r12v65 */
    /* JADX WARN: Type inference failed for: r12v66 */
    /* JADX WARN: Type inference failed for: r12v68 */
    /* JADX WARN: Type inference failed for: r12v69 */
    /* JADX WARN: Type inference failed for: r12v70 */
    /* JADX WARN: Type inference failed for: r12v71 */
    /* JADX WARN: Type inference failed for: r12v72 */
    /* JADX WARN: Type inference failed for: r12v76 */
    /* JADX WARN: Type inference failed for: r12v89 */
    /* JADX WARN: Type inference failed for: r12v90 */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v40 */
    /* JADX WARN: Type inference failed for: r13v43 */
    /* JADX WARN: Type inference failed for: r13v44 */
    /* JADX WARN: Type inference failed for: r13v45 */
    /* JADX WARN: Type inference failed for: r13v46 */
    /* JADX WARN: Type inference failed for: r13v47 */
    /* JADX WARN: Type inference failed for: r13v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r13v50, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r13v51, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r13v52 */
    /* JADX WARN: Type inference failed for: r13v53 */
    /* JADX WARN: Type inference failed for: r13v56 */
    /* JADX WARN: Type inference failed for: r13v61 */
    /* JADX WARN: Type inference failed for: r13v63 */
    /* JADX WARN: Type inference failed for: r13v64 */
    /* JADX WARN: Type inference failed for: r13v69 */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r13v71 */
    /* JADX WARN: Type inference failed for: r13v77 */
    /* JADX WARN: Type inference failed for: r13v78 */
    /* JADX WARN: Type inference failed for: r13v82 */
    /* JADX WARN: Type inference failed for: r13v83 */
    /* JADX WARN: Type inference failed for: r13v84 */
    /* JADX WARN: Type inference failed for: r13v85 */
    /* JADX WARN: Type inference failed for: r13v86 */
    /* JADX WARN: Type inference failed for: r13v87 */
    /* JADX WARN: Type inference failed for: r13v88 */
    /* JADX WARN: Type inference failed for: r13v89 */
    /* JADX WARN: Type inference failed for: r13v90 */
    /* JADX WARN: Type inference failed for: r13v91 */
    /* JADX WARN: Type inference failed for: r14v10, types: [double] */
    /* JADX WARN: Type inference failed for: r14v17 */
    /* JADX WARN: Type inference failed for: r14v19 */
    /* JADX WARN: Type inference failed for: r14v39 */
    /* JADX WARN: Type inference failed for: r14v40 */
    /* JADX WARN: Type inference failed for: r14v41 */
    /* JADX WARN: Type inference failed for: r14v42 */
    /* JADX WARN: Type inference failed for: r14v43 */
    /* JADX WARN: Type inference failed for: r19v5, types: [double] */
    /* JADX WARN: Type inference failed for: r19v7, types: [double] */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v49 */
    /* JADX WARN: Type inference failed for: r1v50 */
    /* JADX WARN: Type inference failed for: r1v51 */
    /* JADX WARN: Type inference failed for: r1v59, types: [cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.controller.domain.hvac.result.SetACTemperatureResult] */
    /* JADX WARN: Type inference failed for: r1v72, types: [cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.controller.domain.hvac.result.SetACTemperatureResult] */
    /* JADX WARN: Type inference failed for: r1v86 */
    /* JADX WARN: Type inference failed for: r1v92 */
    /* JADX WARN: Type inference failed for: r1v93 */
    /* JADX WARN: Type inference failed for: r1v94 */
    /* JADX WARN: Type inference failed for: r1v95 */
    /* JADX WARN: Type inference failed for: r1v96 */
    /* JADX WARN: Type inference failed for: r1v97 */
    /* JADX WARN: Type inference failed for: r1v98 */
    /* JADX WARN: Type inference failed for: r1v99 */
    /* JADX WARN: Type inference failed for: r24v4, types: [double] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v15, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v32 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v40 */
    /* JADX WARN: Type inference failed for: r2v41 */
    /* JADX WARN: Type inference failed for: r2v42 */
    /* JADX WARN: Type inference failed for: r2v43 */
    /* JADX WARN: Type inference failed for: r2v44 */
    /* JADX WARN: Type inference failed for: r2v48 */
    /* JADX WARN: Type inference failed for: r2v49 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v50 */
    /* JADX WARN: Type inference failed for: r2v51, types: [double] */
    /* JADX WARN: Type inference failed for: r2v54 */
    /* JADX WARN: Type inference failed for: r2v55 */
    /* JADX WARN: Type inference failed for: r2v56 */
    /* JADX WARN: Type inference failed for: r2v57 */
    /* JADX WARN: Type inference failed for: r2v58 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v70 */
    /* JADX WARN: Type inference failed for: r2v75 */
    /* JADX WARN: Type inference failed for: r2v76 */
    /* JADX WARN: Type inference failed for: r35v4 */
    /* JADX WARN: Type inference failed for: r35v5, types: [double] */
    /* JADX WARN: Type inference failed for: r35v6 */
    /* JADX WARN: Type inference failed for: r35v7 */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v30 */
    /* JADX WARN: Type inference failed for: r3v31 */
    /* JADX WARN: Type inference failed for: r3v33 */
    /* JADX WARN: Type inference failed for: r3v42 */
    /* JADX WARN: Type inference failed for: r3v43 */
    /* JADX WARN: Type inference failed for: r3v44 */
    /* JADX WARN: Type inference failed for: r3v46 */
    /* JADX WARN: Type inference failed for: r3v47 */
    /* JADX WARN: Type inference failed for: r3v49 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v50 */
    /* JADX WARN: Type inference failed for: r3v51 */
    /* JADX WARN: Type inference failed for: r3v52 */
    /* JADX WARN: Type inference failed for: r3v55 */
    /* JADX WARN: Type inference failed for: r3v56 */
    /* JADX WARN: Type inference failed for: r3v58 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r43v10, types: [com.byd.vrassistant.conversation.BydSdkHelper] */
    /* JADX WARN: Type inference failed for: r43v4, types: [double] */
    /* JADX WARN: Type inference failed for: r43v8, types: [com.byd.vrassistant.conversation.BydSdkHelper] */
    /* JADX WARN: Type inference failed for: r43v9, types: [com.byd.vrassistant.conversation.BydSdkHelper] */
    /* JADX WARN: Type inference failed for: r4v29, types: [double] */
    /* JADX WARN: Type inference failed for: r4v30 */
    /* JADX WARN: Type inference failed for: r4v46, types: [double] */
    /* JADX WARN: Type inference failed for: r4v48, types: [double] */
    /* JADX WARN: Type inference failed for: r4v60 */
    /* JADX WARN: Type inference failed for: r4v61 */
    /* JADX WARN: Type inference failed for: r6v116 */
    /* JADX WARN: Type inference failed for: r6v117 */
    /* JADX WARN: Type inference failed for: r6v118 */
    /* JADX WARN: Type inference failed for: r6v119 */
    /* JADX WARN: Type inference failed for: r6v120 */
    /* JADX WARN: Type inference failed for: r6v121 */
    /* JADX WARN: Type inference failed for: r6v124 */
    /* JADX WARN: Type inference failed for: r6v20, types: [double] */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v22 */
    /* JADX WARN: Type inference failed for: r6v23 */
    /* JADX WARN: Type inference failed for: r6v24 */
    /* JADX WARN: Type inference failed for: r6v27 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v56, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r6v57, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r6v58 */
    /* JADX WARN: Type inference failed for: r6v59 */
    /* JADX WARN: Type inference failed for: r6v6, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r6v68 */
    /* JADX WARN: Type inference failed for: r6v76 */
    /* JADX WARN: Type inference failed for: r6v77 */
    /* JADX WARN: Type inference failed for: r6v78 */
    /* JADX WARN: Type inference failed for: r6v79 */
    /* JADX WARN: Type inference failed for: r6v80 */
    /* JADX WARN: Type inference failed for: r6v87 */
    /* JADX WARN: Type inference failed for: r6v88 */
    /* JADX WARN: Type inference failed for: r6v89 */
    /* JADX WARN: Type inference failed for: r6v90 */
    /* JADX WARN: Type inference failed for: r6v91 */
    /* JADX WARN: Type inference failed for: r6v92 */
    /* JADX WARN: Type inference failed for: r6v99 */
    /* JADX WARN: Type inference failed for: r7v23, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r7v26, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r8v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v109 */
    /* JADX WARN: Type inference failed for: r9v122 */
    /* JADX WARN: Type inference failed for: r9v123 */
    /* JADX WARN: Type inference failed for: r9v69 */
    /* JADX WARN: Type inference failed for: r9v70, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v85, types: [double] */
    /* JADX WARN: Type inference failed for: r9v95, types: [double] */
    /* JADX WARN: Type inference failed for: r9v96 */
    /* JADX WARN: Type inference failed for: r9v97 */
    /* JADX WARN: Type inference failed for: r9v98 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setACTemperature(cerence.ark.assistant.controller.domain.hvac.param.SetACTemperatureParam r84, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.hvac.result.SetACTemperatureResult> r85) {
        /*
            Method dump skipped, instruction units count: 4398
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.HvacTemperatureHelper.setACTemperature(cerence.ark.assistant.controller.domain.hvac.param.SetACTemperatureParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    private void sleep300() {
        try {
            RLog.i(TAG, "sleep 300ms");
            Thread.sleep(300L);
        } catch (Exception e) {
            RLog.i(TAG, "sleep Exception ", e);
        }
    }

    public UiContextGrpc.TemperatureExpression getTemperUnit() {
        int temperatureUnit = this.acDevice.getTemperatureUnit();
        RLog.d(TAG, "getTemperUnit: temperatureUnit = " + temperatureUnit);
        if (temperatureUnit == 1) {
            return UiContextGrpc.TemperatureExpression.CELSIUS;
        }
        return UiContextGrpc.TemperatureExpression.FAHRENHEIT;
    }

    public void setTemperListener() {
        AbsBYDAutoAcListener absBYDAutoAcListener = new AbsBYDAutoAcListener() { // from class: com.byd.vrassistant.conversation.HvacTemperatureHelper.1
            @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
            public void onTemperatureUnitChanged(int i) {
                UiContextGrpc uiContextGrpcBuild;
                Log.i("AbsBYDAutoAcListener", "onTemperatureUnitChanged: unit = " + i);
                if (i == 1) {
                    uiContextGrpcBuild = new UiContextGrpc.Builder().setTemperatureExpression(UiContextGrpc.TemperatureExpression.CELSIUS).build();
                } else {
                    uiContextGrpcBuild = new UiContextGrpc.Builder().setTemperatureExpression(UiContextGrpc.TemperatureExpression.FAHRENHEIT).build();
                }
                ArkAssistant.get().updateAssistantContext(uiContextGrpcBuild);
            }
        };
        this.listener = absBYDAutoAcListener;
        this.acDevice.registerListener(absBYDAutoAcListener);
    }

    public void setUnregisterListener() {
        RLog.d(TAG, "setUnregisterListener:  INSTRUMENT_UNIT_TEMPERATURE");
        AbsBYDAutoAcListener absBYDAutoAcListener = this.listener;
        if (absBYDAutoAcListener != null) {
            this.acDevice.unregisterListener(absBYDAutoAcListener);
        }
    }

    public void getHvacMinTempUnit(Context context, IResponseSender<GetHvacMinTempUnitResult> iResponseSender) {
        try {
            String str = TAG;
            TtsUtils.requestLog(str, HVACRequestsEnum.GET_HVAC_MIN_TEMP_UNIT);
            GetHvacMinTempUnitResult getHvacMinTempUnitResult = new GetHvacMinTempUnitResult();
            if (BydSdkHelper.getInstance().isTemp05(context)) {
                getHvacMinTempUnitResult.setTempUnit("0.5");
            } else {
                getHvacMinTempUnitResult.setTempUnit("1");
            }
            TtsUtils.tts(str, HVACRequestsEnum.GET_HVAC_MIN_TEMP_UNIT, iResponseSender, "OK", getHvacMinTempUnitResult);
        } catch (Error | Exception unused) {
            TtsUtils.tts(TAG, HVACRequestsEnum.GET_HVAC_MIN_TEMP_UNIT, iResponseSender, ResultCodeEnum.ERROR);
        }
    }

    public boolean isSingleTemperatureControl() {
        int acTemperatureControlMode = this.acDevice.getAcTemperatureControlMode();
        RLog.i(TAG, "isSync: acSync = " + acTemperatureControlMode);
        return (acTemperatureControlMode == 0 || acTemperatureControlMode == 1) ? false : true;
    }

    private static double to05C(double d) {
        return roundTo05Precision((d - 32.0d) / 1.8d);
    }

    private static double to05F(double d) {
        return roundTo05Precision((d * 1.8d) + 32.0d);
    }

    public static double roundTo05Precision(double d) {
        return Math.round(d * 2.0d) / 2.0d;
    }
}
