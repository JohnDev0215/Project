package com.byd.vrassistant.conversation;

import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.TagUtil;

/* JADX INFO: loaded from: classes.dex */
public class HvacLevelHelper {
    private static final String TAG = TagUtil.getTagName(HvacLevelHelper.class);
    private BYDAutoAcDevice acDevice;
    private BYDAutoStudy autoStudy;
    private BYDAutoSettingDevice settingDevice;

    private static final class SingletonHolder {
        private static final HvacLevelHelper INSTANCE = new HvacLevelHelper();

        private SingletonHolder() {
        }
    }

    public static HvacLevelHelper getInstance() {
        RLog.i(TAG, "getInstance()");
        return SingletonHolder.INSTANCE;
    }

    private HvacLevelHelper() {
        try {
            RLog.i(TAG, "HvacLevelHelper()");
            this.acDevice = BYDAutoAcDevice.getInstance(BydVoiceApplication.getContext());
            this.settingDevice = BYDAutoSettingDevice.getInstance(BydVoiceApplication.getContext());
            this.autoStudy = BYDAutoStudy.getInstance(BydVoiceApplication.getContext());
        } catch (Exception e) {
            RLog.i(TAG, "HvacLevelHelper Exception ", e);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:116)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:71)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0292 A[Catch: Exception -> 0x0474, TryCatch #5 {Exception -> 0x0474, blocks: (B:79:0x01f4, B:80:0x023c, B:111:0x02bb, B:145:0x0430, B:146:0x0445, B:113:0x02c0, B:115:0x02e3, B:117:0x02f0, B:116:0x02ea, B:118:0x030e, B:120:0x0323, B:122:0x032a, B:121:0x0327, B:123:0x033d, B:125:0x0352, B:127:0x0359, B:126:0x0356, B:128:0x036c, B:129:0x0391, B:131:0x03b4, B:132:0x03bc, B:133:0x03c4, B:134:0x03da, B:135:0x03f0, B:137:0x0405, B:139:0x040c, B:138:0x0409, B:140:0x0410, B:142:0x0425, B:144:0x042c, B:143:0x0429, B:82:0x0245, B:85:0x0250, B:88:0x025e, B:91:0x0269, B:95:0x0278, B:98:0x0285, B:101:0x0292, B:104:0x029d, B:107:0x02aa, B:150:0x0477), top: B:220:0x00a6 }] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x029d A[Catch: Exception -> 0x0474, TryCatch #5 {Exception -> 0x0474, blocks: (B:79:0x01f4, B:80:0x023c, B:111:0x02bb, B:145:0x0430, B:146:0x0445, B:113:0x02c0, B:115:0x02e3, B:117:0x02f0, B:116:0x02ea, B:118:0x030e, B:120:0x0323, B:122:0x032a, B:121:0x0327, B:123:0x033d, B:125:0x0352, B:127:0x0359, B:126:0x0356, B:128:0x036c, B:129:0x0391, B:131:0x03b4, B:132:0x03bc, B:133:0x03c4, B:134:0x03da, B:135:0x03f0, B:137:0x0405, B:139:0x040c, B:138:0x0409, B:140:0x0410, B:142:0x0425, B:144:0x042c, B:143:0x0429, B:82:0x0245, B:85:0x0250, B:88:0x025e, B:91:0x0269, B:95:0x0278, B:98:0x0285, B:101:0x0292, B:104:0x029d, B:107:0x02aa, B:150:0x0477), top: B:220:0x00a6 }] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x02aa A[Catch: Exception -> 0x0474, TryCatch #5 {Exception -> 0x0474, blocks: (B:79:0x01f4, B:80:0x023c, B:111:0x02bb, B:145:0x0430, B:146:0x0445, B:113:0x02c0, B:115:0x02e3, B:117:0x02f0, B:116:0x02ea, B:118:0x030e, B:120:0x0323, B:122:0x032a, B:121:0x0327, B:123:0x033d, B:125:0x0352, B:127:0x0359, B:126:0x0356, B:128:0x036c, B:129:0x0391, B:131:0x03b4, B:132:0x03bc, B:133:0x03c4, B:134:0x03da, B:135:0x03f0, B:137:0x0405, B:139:0x040c, B:138:0x0409, B:140:0x0410, B:142:0x0425, B:144:0x042c, B:143:0x0429, B:82:0x0245, B:85:0x0250, B:88:0x025e, B:91:0x0269, B:95:0x0278, B:98:0x0285, B:101:0x0292, B:104:0x029d, B:107:0x02aa, B:150:0x0477), top: B:220:0x00a6 }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x02b9 A[PHI: r6 r7
  0x02b9: PHI (r6v21 java.lang.String) = (r6v9 java.lang.String), (r6v10 java.lang.String), (r6v11 java.lang.String), (r6v22 java.lang.String) binds: [B:108:0x02b4, B:105:0x02a5, B:102:0x029a, B:81:0x023f] A[DONT_GENERATE, DONT_INLINE]
  0x02b9: PHI (r7v23 java.lang.String) = (r7v14 java.lang.String), (r7v15 java.lang.String), (r7v16 java.lang.String), (r7v24 java.lang.String) binds: [B:108:0x02b4, B:105:0x02a5, B:102:0x029a, B:81:0x023f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02c0 A[Catch: Exception -> 0x0474, TryCatch #5 {Exception -> 0x0474, blocks: (B:79:0x01f4, B:80:0x023c, B:111:0x02bb, B:145:0x0430, B:146:0x0445, B:113:0x02c0, B:115:0x02e3, B:117:0x02f0, B:116:0x02ea, B:118:0x030e, B:120:0x0323, B:122:0x032a, B:121:0x0327, B:123:0x033d, B:125:0x0352, B:127:0x0359, B:126:0x0356, B:128:0x036c, B:129:0x0391, B:131:0x03b4, B:132:0x03bc, B:133:0x03c4, B:134:0x03da, B:135:0x03f0, B:137:0x0405, B:139:0x040c, B:138:0x0409, B:140:0x0410, B:142:0x0425, B:144:0x042c, B:143:0x0429, B:82:0x0245, B:85:0x0250, B:88:0x025e, B:91:0x0269, B:95:0x0278, B:98:0x0285, B:101:0x0292, B:104:0x029d, B:107:0x02aa, B:150:0x0477), top: B:220:0x00a6 }] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x030e A[Catch: Exception -> 0x0474, TryCatch #5 {Exception -> 0x0474, blocks: (B:79:0x01f4, B:80:0x023c, B:111:0x02bb, B:145:0x0430, B:146:0x0445, B:113:0x02c0, B:115:0x02e3, B:117:0x02f0, B:116:0x02ea, B:118:0x030e, B:120:0x0323, B:122:0x032a, B:121:0x0327, B:123:0x033d, B:125:0x0352, B:127:0x0359, B:126:0x0356, B:128:0x036c, B:129:0x0391, B:131:0x03b4, B:132:0x03bc, B:133:0x03c4, B:134:0x03da, B:135:0x03f0, B:137:0x0405, B:139:0x040c, B:138:0x0409, B:140:0x0410, B:142:0x0425, B:144:0x042c, B:143:0x0429, B:82:0x0245, B:85:0x0250, B:88:0x025e, B:91:0x0269, B:95:0x0278, B:98:0x0285, B:101:0x0292, B:104:0x029d, B:107:0x02aa, B:150:0x0477), top: B:220:0x00a6 }] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x033d A[Catch: Exception -> 0x0474, TryCatch #5 {Exception -> 0x0474, blocks: (B:79:0x01f4, B:80:0x023c, B:111:0x02bb, B:145:0x0430, B:146:0x0445, B:113:0x02c0, B:115:0x02e3, B:117:0x02f0, B:116:0x02ea, B:118:0x030e, B:120:0x0323, B:122:0x032a, B:121:0x0327, B:123:0x033d, B:125:0x0352, B:127:0x0359, B:126:0x0356, B:128:0x036c, B:129:0x0391, B:131:0x03b4, B:132:0x03bc, B:133:0x03c4, B:134:0x03da, B:135:0x03f0, B:137:0x0405, B:139:0x040c, B:138:0x0409, B:140:0x0410, B:142:0x0425, B:144:0x042c, B:143:0x0429, B:82:0x0245, B:85:0x0250, B:88:0x025e, B:91:0x0269, B:95:0x0278, B:98:0x0285, B:101:0x0292, B:104:0x029d, B:107:0x02aa, B:150:0x0477), top: B:220:0x00a6 }] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x036c A[Catch: Exception -> 0x0474, TryCatch #5 {Exception -> 0x0474, blocks: (B:79:0x01f4, B:80:0x023c, B:111:0x02bb, B:145:0x0430, B:146:0x0445, B:113:0x02c0, B:115:0x02e3, B:117:0x02f0, B:116:0x02ea, B:118:0x030e, B:120:0x0323, B:122:0x032a, B:121:0x0327, B:123:0x033d, B:125:0x0352, B:127:0x0359, B:126:0x0356, B:128:0x036c, B:129:0x0391, B:131:0x03b4, B:132:0x03bc, B:133:0x03c4, B:134:0x03da, B:135:0x03f0, B:137:0x0405, B:139:0x040c, B:138:0x0409, B:140:0x0410, B:142:0x0425, B:144:0x042c, B:143:0x0429, B:82:0x0245, B:85:0x0250, B:88:0x025e, B:91:0x0269, B:95:0x0278, B:98:0x0285, B:101:0x0292, B:104:0x029d, B:107:0x02aa, B:150:0x0477), top: B:220:0x00a6 }] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0391 A[Catch: Exception -> 0x0474, TryCatch #5 {Exception -> 0x0474, blocks: (B:79:0x01f4, B:80:0x023c, B:111:0x02bb, B:145:0x0430, B:146:0x0445, B:113:0x02c0, B:115:0x02e3, B:117:0x02f0, B:116:0x02ea, B:118:0x030e, B:120:0x0323, B:122:0x032a, B:121:0x0327, B:123:0x033d, B:125:0x0352, B:127:0x0359, B:126:0x0356, B:128:0x036c, B:129:0x0391, B:131:0x03b4, B:132:0x03bc, B:133:0x03c4, B:134:0x03da, B:135:0x03f0, B:137:0x0405, B:139:0x040c, B:138:0x0409, B:140:0x0410, B:142:0x0425, B:144:0x042c, B:143:0x0429, B:82:0x0245, B:85:0x0250, B:88:0x025e, B:91:0x0269, B:95:0x0278, B:98:0x0285, B:101:0x0292, B:104:0x029d, B:107:0x02aa, B:150:0x0477), top: B:220:0x00a6 }] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x03c4 A[Catch: Exception -> 0x0474, TryCatch #5 {Exception -> 0x0474, blocks: (B:79:0x01f4, B:80:0x023c, B:111:0x02bb, B:145:0x0430, B:146:0x0445, B:113:0x02c0, B:115:0x02e3, B:117:0x02f0, B:116:0x02ea, B:118:0x030e, B:120:0x0323, B:122:0x032a, B:121:0x0327, B:123:0x033d, B:125:0x0352, B:127:0x0359, B:126:0x0356, B:128:0x036c, B:129:0x0391, B:131:0x03b4, B:132:0x03bc, B:133:0x03c4, B:134:0x03da, B:135:0x03f0, B:137:0x0405, B:139:0x040c, B:138:0x0409, B:140:0x0410, B:142:0x0425, B:144:0x042c, B:143:0x0429, B:82:0x0245, B:85:0x0250, B:88:0x025e, B:91:0x0269, B:95:0x0278, B:98:0x0285, B:101:0x0292, B:104:0x029d, B:107:0x02aa, B:150:0x0477), top: B:220:0x00a6 }] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x03da A[Catch: Exception -> 0x0474, TryCatch #5 {Exception -> 0x0474, blocks: (B:79:0x01f4, B:80:0x023c, B:111:0x02bb, B:145:0x0430, B:146:0x0445, B:113:0x02c0, B:115:0x02e3, B:117:0x02f0, B:116:0x02ea, B:118:0x030e, B:120:0x0323, B:122:0x032a, B:121:0x0327, B:123:0x033d, B:125:0x0352, B:127:0x0359, B:126:0x0356, B:128:0x036c, B:129:0x0391, B:131:0x03b4, B:132:0x03bc, B:133:0x03c4, B:134:0x03da, B:135:0x03f0, B:137:0x0405, B:139:0x040c, B:138:0x0409, B:140:0x0410, B:142:0x0425, B:144:0x042c, B:143:0x0429, B:82:0x0245, B:85:0x0250, B:88:0x025e, B:91:0x0269, B:95:0x0278, B:98:0x0285, B:101:0x0292, B:104:0x029d, B:107:0x02aa, B:150:0x0477), top: B:220:0x00a6 }] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x03f0 A[Catch: Exception -> 0x0474, TryCatch #5 {Exception -> 0x0474, blocks: (B:79:0x01f4, B:80:0x023c, B:111:0x02bb, B:145:0x0430, B:146:0x0445, B:113:0x02c0, B:115:0x02e3, B:117:0x02f0, B:116:0x02ea, B:118:0x030e, B:120:0x0323, B:122:0x032a, B:121:0x0327, B:123:0x033d, B:125:0x0352, B:127:0x0359, B:126:0x0356, B:128:0x036c, B:129:0x0391, B:131:0x03b4, B:132:0x03bc, B:133:0x03c4, B:134:0x03da, B:135:0x03f0, B:137:0x0405, B:139:0x040c, B:138:0x0409, B:140:0x0410, B:142:0x0425, B:144:0x042c, B:143:0x0429, B:82:0x0245, B:85:0x0250, B:88:0x025e, B:91:0x0269, B:95:0x0278, B:98:0x0285, B:101:0x0292, B:104:0x029d, B:107:0x02aa, B:150:0x0477), top: B:220:0x00a6 }] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0410 A[Catch: Exception -> 0x0474, TryCatch #5 {Exception -> 0x0474, blocks: (B:79:0x01f4, B:80:0x023c, B:111:0x02bb, B:145:0x0430, B:146:0x0445, B:113:0x02c0, B:115:0x02e3, B:117:0x02f0, B:116:0x02ea, B:118:0x030e, B:120:0x0323, B:122:0x032a, B:121:0x0327, B:123:0x033d, B:125:0x0352, B:127:0x0359, B:126:0x0356, B:128:0x036c, B:129:0x0391, B:131:0x03b4, B:132:0x03bc, B:133:0x03c4, B:134:0x03da, B:135:0x03f0, B:137:0x0405, B:139:0x040c, B:138:0x0409, B:140:0x0410, B:142:0x0425, B:144:0x042c, B:143:0x0429, B:82:0x0245, B:85:0x0250, B:88:0x025e, B:91:0x0269, B:95:0x0278, B:98:0x0285, B:101:0x0292, B:104:0x029d, B:107:0x02aa, B:150:0x0477), top: B:220:0x00a6 }] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0477 A[Catch: Exception -> 0x0474, TRY_LEAVE, TryCatch #5 {Exception -> 0x0474, blocks: (B:79:0x01f4, B:80:0x023c, B:111:0x02bb, B:145:0x0430, B:146:0x0445, B:113:0x02c0, B:115:0x02e3, B:117:0x02f0, B:116:0x02ea, B:118:0x030e, B:120:0x0323, B:122:0x032a, B:121:0x0327, B:123:0x033d, B:125:0x0352, B:127:0x0359, B:126:0x0356, B:128:0x036c, B:129:0x0391, B:131:0x03b4, B:132:0x03bc, B:133:0x03c4, B:134:0x03da, B:135:0x03f0, B:137:0x0405, B:139:0x040c, B:138:0x0409, B:140:0x0410, B:142:0x0425, B:144:0x042c, B:143:0x0429, B:82:0x0245, B:85:0x0250, B:88:0x025e, B:91:0x0269, B:95:0x0278, B:98:0x0285, B:101:0x0292, B:104:0x029d, B:107:0x02aa, B:150:0x0477), top: B:220:0x00a6 }] */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0552  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x014a A[Catch: Exception -> 0x0156, TRY_LEAVE, TryCatch #4 {Exception -> 0x0156, blocks: (B:33:0x00d9, B:35:0x00f8, B:37:0x0106, B:43:0x0125, B:44:0x012b, B:48:0x014a, B:36:0x0100, B:56:0x0182), top: B:219:0x00cd }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0245 A[Catch: Exception -> 0x0474, TryCatch #5 {Exception -> 0x0474, blocks: (B:79:0x01f4, B:80:0x023c, B:111:0x02bb, B:145:0x0430, B:146:0x0445, B:113:0x02c0, B:115:0x02e3, B:117:0x02f0, B:116:0x02ea, B:118:0x030e, B:120:0x0323, B:122:0x032a, B:121:0x0327, B:123:0x033d, B:125:0x0352, B:127:0x0359, B:126:0x0356, B:128:0x036c, B:129:0x0391, B:131:0x03b4, B:132:0x03bc, B:133:0x03c4, B:134:0x03da, B:135:0x03f0, B:137:0x0405, B:139:0x040c, B:138:0x0409, B:140:0x0410, B:142:0x0425, B:144:0x042c, B:143:0x0429, B:82:0x0245, B:85:0x0250, B:88:0x025e, B:91:0x0269, B:95:0x0278, B:98:0x0285, B:101:0x0292, B:104:0x029d, B:107:0x02aa, B:150:0x0477), top: B:220:0x00a6 }] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0250 A[Catch: Exception -> 0x0474, TryCatch #5 {Exception -> 0x0474, blocks: (B:79:0x01f4, B:80:0x023c, B:111:0x02bb, B:145:0x0430, B:146:0x0445, B:113:0x02c0, B:115:0x02e3, B:117:0x02f0, B:116:0x02ea, B:118:0x030e, B:120:0x0323, B:122:0x032a, B:121:0x0327, B:123:0x033d, B:125:0x0352, B:127:0x0359, B:126:0x0356, B:128:0x036c, B:129:0x0391, B:131:0x03b4, B:132:0x03bc, B:133:0x03c4, B:134:0x03da, B:135:0x03f0, B:137:0x0405, B:139:0x040c, B:138:0x0409, B:140:0x0410, B:142:0x0425, B:144:0x042c, B:143:0x0429, B:82:0x0245, B:85:0x0250, B:88:0x025e, B:91:0x0269, B:95:0x0278, B:98:0x0285, B:101:0x0292, B:104:0x029d, B:107:0x02aa, B:150:0x0477), top: B:220:0x00a6 }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x025e A[Catch: Exception -> 0x0474, TryCatch #5 {Exception -> 0x0474, blocks: (B:79:0x01f4, B:80:0x023c, B:111:0x02bb, B:145:0x0430, B:146:0x0445, B:113:0x02c0, B:115:0x02e3, B:117:0x02f0, B:116:0x02ea, B:118:0x030e, B:120:0x0323, B:122:0x032a, B:121:0x0327, B:123:0x033d, B:125:0x0352, B:127:0x0359, B:126:0x0356, B:128:0x036c, B:129:0x0391, B:131:0x03b4, B:132:0x03bc, B:133:0x03c4, B:134:0x03da, B:135:0x03f0, B:137:0x0405, B:139:0x040c, B:138:0x0409, B:140:0x0410, B:142:0x0425, B:144:0x042c, B:143:0x0429, B:82:0x0245, B:85:0x0250, B:88:0x025e, B:91:0x0269, B:95:0x0278, B:98:0x0285, B:101:0x0292, B:104:0x029d, B:107:0x02aa, B:150:0x0477), top: B:220:0x00a6 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0269 A[Catch: Exception -> 0x0474, TryCatch #5 {Exception -> 0x0474, blocks: (B:79:0x01f4, B:80:0x023c, B:111:0x02bb, B:145:0x0430, B:146:0x0445, B:113:0x02c0, B:115:0x02e3, B:117:0x02f0, B:116:0x02ea, B:118:0x030e, B:120:0x0323, B:122:0x032a, B:121:0x0327, B:123:0x033d, B:125:0x0352, B:127:0x0359, B:126:0x0356, B:128:0x036c, B:129:0x0391, B:131:0x03b4, B:132:0x03bc, B:133:0x03c4, B:134:0x03da, B:135:0x03f0, B:137:0x0405, B:139:0x040c, B:138:0x0409, B:140:0x0410, B:142:0x0425, B:144:0x042c, B:143:0x0429, B:82:0x0245, B:85:0x0250, B:88:0x025e, B:91:0x0269, B:95:0x0278, B:98:0x0285, B:101:0x0292, B:104:0x029d, B:107:0x02aa, B:150:0x0477), top: B:220:0x00a6 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0278 A[Catch: Exception -> 0x0474, TryCatch #5 {Exception -> 0x0474, blocks: (B:79:0x01f4, B:80:0x023c, B:111:0x02bb, B:145:0x0430, B:146:0x0445, B:113:0x02c0, B:115:0x02e3, B:117:0x02f0, B:116:0x02ea, B:118:0x030e, B:120:0x0323, B:122:0x032a, B:121:0x0327, B:123:0x033d, B:125:0x0352, B:127:0x0359, B:126:0x0356, B:128:0x036c, B:129:0x0391, B:131:0x03b4, B:132:0x03bc, B:133:0x03c4, B:134:0x03da, B:135:0x03f0, B:137:0x0405, B:139:0x040c, B:138:0x0409, B:140:0x0410, B:142:0x0425, B:144:0x042c, B:143:0x0429, B:82:0x0245, B:85:0x0250, B:88:0x025e, B:91:0x0269, B:95:0x0278, B:98:0x0285, B:101:0x0292, B:104:0x029d, B:107:0x02aa, B:150:0x0477), top: B:220:0x00a6 }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0285 A[Catch: Exception -> 0x0474, TryCatch #5 {Exception -> 0x0474, blocks: (B:79:0x01f4, B:80:0x023c, B:111:0x02bb, B:145:0x0430, B:146:0x0445, B:113:0x02c0, B:115:0x02e3, B:117:0x02f0, B:116:0x02ea, B:118:0x030e, B:120:0x0323, B:122:0x032a, B:121:0x0327, B:123:0x033d, B:125:0x0352, B:127:0x0359, B:126:0x0356, B:128:0x036c, B:129:0x0391, B:131:0x03b4, B:132:0x03bc, B:133:0x03c4, B:134:0x03da, B:135:0x03f0, B:137:0x0405, B:139:0x040c, B:138:0x0409, B:140:0x0410, B:142:0x0425, B:144:0x042c, B:143:0x0429, B:82:0x0245, B:85:0x0250, B:88:0x025e, B:91:0x0269, B:95:0x0278, B:98:0x0285, B:101:0x0292, B:104:0x029d, B:107:0x02aa, B:150:0x0477), top: B:220:0x00a6 }] */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v92 */
    /* JADX WARN: Type inference failed for: r0v93 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v25 */
    /* JADX WARN: Type inference failed for: r7v26 */
    /* JADX WARN: Type inference failed for: r7v27 */
    /* JADX WARN: Type inference failed for: r7v28 */
    /* JADX WARN: Type inference failed for: r7v3, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getHavcLevel(cerence.ark.assistant.controller.domain.hvac.param.GetHavcLevelParam r32, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.hvac.result.GetHavcLevelResult> r33) {
        /*
            Method dump skipped, instruction units count: 1734
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.HvacLevelHelper.getHavcLevel(cerence.ark.assistant.controller.domain.hvac.param.GetHavcLevelParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(17:28|(7:447|29|30|449|31|(1:33)(1:34)|35)|(5:(18:37|(1:39)|42|43|455|44|(1:48)|49|SW:50|80|457|SW:81|179|180|441|181|182|183)(1:40)|441|181|182|183)|41|42|43|455|44|(1:46)|48|49|SW:50|80|457|SW:81|179|180) */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x03f1, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x03f3, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:116)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:71)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0285 A[Catch: Exception -> 0x03f3, TryCatch #11 {Exception -> 0x03f3, blocks: (B:44:0x0134, B:49:0x0156, B:50:0x019e, B:83:0x0210, B:179:0x03a0, B:87:0x021b, B:89:0x022e, B:93:0x023d, B:95:0x024c, B:101:0x0261, B:107:0x0276, B:109:0x0285, B:115:0x029a, B:121:0x02af, B:123:0x02be, B:132:0x02d9, B:134:0x02e8, B:141:0x0301, B:148:0x031a, B:150:0x0329, B:157:0x0342, B:164:0x035b, B:166:0x036a, B:171:0x037d, B:173:0x038c, B:52:0x01a2, B:55:0x01ad, B:58:0x01b6, B:61:0x01c1, B:64:0x01cc, B:67:0x01d5, B:70:0x01de, B:73:0x01e9, B:76:0x01f4, B:48:0x014e), top: B:455:0x0134 }] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x02be A[Catch: Exception -> 0x03f3, TryCatch #11 {Exception -> 0x03f3, blocks: (B:44:0x0134, B:49:0x0156, B:50:0x019e, B:83:0x0210, B:179:0x03a0, B:87:0x021b, B:89:0x022e, B:93:0x023d, B:95:0x024c, B:101:0x0261, B:107:0x0276, B:109:0x0285, B:115:0x029a, B:121:0x02af, B:123:0x02be, B:132:0x02d9, B:134:0x02e8, B:141:0x0301, B:148:0x031a, B:150:0x0329, B:157:0x0342, B:164:0x035b, B:166:0x036a, B:171:0x037d, B:173:0x038c, B:52:0x01a2, B:55:0x01ad, B:58:0x01b6, B:61:0x01c1, B:64:0x01cc, B:67:0x01d5, B:70:0x01de, B:73:0x01e9, B:76:0x01f4, B:48:0x014e), top: B:455:0x0134 }] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x02e8 A[Catch: Exception -> 0x03f3, TryCatch #11 {Exception -> 0x03f3, blocks: (B:44:0x0134, B:49:0x0156, B:50:0x019e, B:83:0x0210, B:179:0x03a0, B:87:0x021b, B:89:0x022e, B:93:0x023d, B:95:0x024c, B:101:0x0261, B:107:0x0276, B:109:0x0285, B:115:0x029a, B:121:0x02af, B:123:0x02be, B:132:0x02d9, B:134:0x02e8, B:141:0x0301, B:148:0x031a, B:150:0x0329, B:157:0x0342, B:164:0x035b, B:166:0x036a, B:171:0x037d, B:173:0x038c, B:52:0x01a2, B:55:0x01ad, B:58:0x01b6, B:61:0x01c1, B:64:0x01cc, B:67:0x01d5, B:70:0x01de, B:73:0x01e9, B:76:0x01f4, B:48:0x014e), top: B:455:0x0134 }] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0329 A[Catch: Exception -> 0x03f3, TryCatch #11 {Exception -> 0x03f3, blocks: (B:44:0x0134, B:49:0x0156, B:50:0x019e, B:83:0x0210, B:179:0x03a0, B:87:0x021b, B:89:0x022e, B:93:0x023d, B:95:0x024c, B:101:0x0261, B:107:0x0276, B:109:0x0285, B:115:0x029a, B:121:0x02af, B:123:0x02be, B:132:0x02d9, B:134:0x02e8, B:141:0x0301, B:148:0x031a, B:150:0x0329, B:157:0x0342, B:164:0x035b, B:166:0x036a, B:171:0x037d, B:173:0x038c, B:52:0x01a2, B:55:0x01ad, B:58:0x01b6, B:61:0x01c1, B:64:0x01cc, B:67:0x01d5, B:70:0x01de, B:73:0x01e9, B:76:0x01f4, B:48:0x014e), top: B:455:0x0134 }] */
    /* JADX WARN: Removed duplicated region for block: B:166:0x036a A[Catch: Exception -> 0x03f3, TryCatch #11 {Exception -> 0x03f3, blocks: (B:44:0x0134, B:49:0x0156, B:50:0x019e, B:83:0x0210, B:179:0x03a0, B:87:0x021b, B:89:0x022e, B:93:0x023d, B:95:0x024c, B:101:0x0261, B:107:0x0276, B:109:0x0285, B:115:0x029a, B:121:0x02af, B:123:0x02be, B:132:0x02d9, B:134:0x02e8, B:141:0x0301, B:148:0x031a, B:150:0x0329, B:157:0x0342, B:164:0x035b, B:166:0x036a, B:171:0x037d, B:173:0x038c, B:52:0x01a2, B:55:0x01ad, B:58:0x01b6, B:61:0x01c1, B:64:0x01cc, B:67:0x01d5, B:70:0x01de, B:73:0x01e9, B:76:0x01f4, B:48:0x014e), top: B:455:0x0134 }] */
    /* JADX WARN: Removed duplicated region for block: B:173:0x038c A[Catch: Exception -> 0x03f3, TryCatch #11 {Exception -> 0x03f3, blocks: (B:44:0x0134, B:49:0x0156, B:50:0x019e, B:83:0x0210, B:179:0x03a0, B:87:0x021b, B:89:0x022e, B:93:0x023d, B:95:0x024c, B:101:0x0261, B:107:0x0276, B:109:0x0285, B:115:0x029a, B:121:0x02af, B:123:0x02be, B:132:0x02d9, B:134:0x02e8, B:141:0x0301, B:148:0x031a, B:150:0x0329, B:157:0x0342, B:164:0x035b, B:166:0x036a, B:171:0x037d, B:173:0x038c, B:52:0x01a2, B:55:0x01ad, B:58:0x01b6, B:61:0x01c1, B:64:0x01cc, B:67:0x01d5, B:70:0x01de, B:73:0x01e9, B:76:0x01f4, B:48:0x014e), top: B:455:0x0134 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0517  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0518 A[Catch: Exception -> 0x073f, TryCatch #15 {Exception -> 0x073f, blocks: (B:229:0x04ab, B:234:0x04c6, B:235:0x0514, B:268:0x0580, B:364:0x06ec, B:272:0x058b, B:274:0x059a, B:278:0x05a5, B:280:0x05b4, B:286:0x05c5, B:292:0x05da, B:294:0x05e9, B:300:0x05fa, B:306:0x060f, B:308:0x061e, B:317:0x0635, B:319:0x0644, B:326:0x0659, B:333:0x0672, B:335:0x0681, B:342:0x0696, B:349:0x06af, B:351:0x06be, B:356:0x06cd, B:358:0x06dc, B:237:0x0518, B:240:0x0523, B:243:0x052c, B:246:0x0537, B:249:0x0542, B:252:0x054b, B:255:0x0554, B:258:0x055f, B:261:0x056a, B:233:0x04bf), top: B:460:0x04ab }] */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0523 A[Catch: Exception -> 0x073f, TryCatch #15 {Exception -> 0x073f, blocks: (B:229:0x04ab, B:234:0x04c6, B:235:0x0514, B:268:0x0580, B:364:0x06ec, B:272:0x058b, B:274:0x059a, B:278:0x05a5, B:280:0x05b4, B:286:0x05c5, B:292:0x05da, B:294:0x05e9, B:300:0x05fa, B:306:0x060f, B:308:0x061e, B:317:0x0635, B:319:0x0644, B:326:0x0659, B:333:0x0672, B:335:0x0681, B:342:0x0696, B:349:0x06af, B:351:0x06be, B:356:0x06cd, B:358:0x06dc, B:237:0x0518, B:240:0x0523, B:243:0x052c, B:246:0x0537, B:249:0x0542, B:252:0x054b, B:255:0x0554, B:258:0x055f, B:261:0x056a, B:233:0x04bf), top: B:460:0x04ab }] */
    /* JADX WARN: Removed duplicated region for block: B:243:0x052c A[Catch: Exception -> 0x073f, TryCatch #15 {Exception -> 0x073f, blocks: (B:229:0x04ab, B:234:0x04c6, B:235:0x0514, B:268:0x0580, B:364:0x06ec, B:272:0x058b, B:274:0x059a, B:278:0x05a5, B:280:0x05b4, B:286:0x05c5, B:292:0x05da, B:294:0x05e9, B:300:0x05fa, B:306:0x060f, B:308:0x061e, B:317:0x0635, B:319:0x0644, B:326:0x0659, B:333:0x0672, B:335:0x0681, B:342:0x0696, B:349:0x06af, B:351:0x06be, B:356:0x06cd, B:358:0x06dc, B:237:0x0518, B:240:0x0523, B:243:0x052c, B:246:0x0537, B:249:0x0542, B:252:0x054b, B:255:0x0554, B:258:0x055f, B:261:0x056a, B:233:0x04bf), top: B:460:0x04ab }] */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0537 A[Catch: Exception -> 0x073f, TryCatch #15 {Exception -> 0x073f, blocks: (B:229:0x04ab, B:234:0x04c6, B:235:0x0514, B:268:0x0580, B:364:0x06ec, B:272:0x058b, B:274:0x059a, B:278:0x05a5, B:280:0x05b4, B:286:0x05c5, B:292:0x05da, B:294:0x05e9, B:300:0x05fa, B:306:0x060f, B:308:0x061e, B:317:0x0635, B:319:0x0644, B:326:0x0659, B:333:0x0672, B:335:0x0681, B:342:0x0696, B:349:0x06af, B:351:0x06be, B:356:0x06cd, B:358:0x06dc, B:237:0x0518, B:240:0x0523, B:243:0x052c, B:246:0x0537, B:249:0x0542, B:252:0x054b, B:255:0x0554, B:258:0x055f, B:261:0x056a, B:233:0x04bf), top: B:460:0x04ab }] */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0542 A[Catch: Exception -> 0x073f, TryCatch #15 {Exception -> 0x073f, blocks: (B:229:0x04ab, B:234:0x04c6, B:235:0x0514, B:268:0x0580, B:364:0x06ec, B:272:0x058b, B:274:0x059a, B:278:0x05a5, B:280:0x05b4, B:286:0x05c5, B:292:0x05da, B:294:0x05e9, B:300:0x05fa, B:306:0x060f, B:308:0x061e, B:317:0x0635, B:319:0x0644, B:326:0x0659, B:333:0x0672, B:335:0x0681, B:342:0x0696, B:349:0x06af, B:351:0x06be, B:356:0x06cd, B:358:0x06dc, B:237:0x0518, B:240:0x0523, B:243:0x052c, B:246:0x0537, B:249:0x0542, B:252:0x054b, B:255:0x0554, B:258:0x055f, B:261:0x056a, B:233:0x04bf), top: B:460:0x04ab }] */
    /* JADX WARN: Removed duplicated region for block: B:252:0x054b A[Catch: Exception -> 0x073f, TryCatch #15 {Exception -> 0x073f, blocks: (B:229:0x04ab, B:234:0x04c6, B:235:0x0514, B:268:0x0580, B:364:0x06ec, B:272:0x058b, B:274:0x059a, B:278:0x05a5, B:280:0x05b4, B:286:0x05c5, B:292:0x05da, B:294:0x05e9, B:300:0x05fa, B:306:0x060f, B:308:0x061e, B:317:0x0635, B:319:0x0644, B:326:0x0659, B:333:0x0672, B:335:0x0681, B:342:0x0696, B:349:0x06af, B:351:0x06be, B:356:0x06cd, B:358:0x06dc, B:237:0x0518, B:240:0x0523, B:243:0x052c, B:246:0x0537, B:249:0x0542, B:252:0x054b, B:255:0x0554, B:258:0x055f, B:261:0x056a, B:233:0x04bf), top: B:460:0x04ab }] */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0554 A[Catch: Exception -> 0x073f, TryCatch #15 {Exception -> 0x073f, blocks: (B:229:0x04ab, B:234:0x04c6, B:235:0x0514, B:268:0x0580, B:364:0x06ec, B:272:0x058b, B:274:0x059a, B:278:0x05a5, B:280:0x05b4, B:286:0x05c5, B:292:0x05da, B:294:0x05e9, B:300:0x05fa, B:306:0x060f, B:308:0x061e, B:317:0x0635, B:319:0x0644, B:326:0x0659, B:333:0x0672, B:335:0x0681, B:342:0x0696, B:349:0x06af, B:351:0x06be, B:356:0x06cd, B:358:0x06dc, B:237:0x0518, B:240:0x0523, B:243:0x052c, B:246:0x0537, B:249:0x0542, B:252:0x054b, B:255:0x0554, B:258:0x055f, B:261:0x056a, B:233:0x04bf), top: B:460:0x04ab }] */
    /* JADX WARN: Removed duplicated region for block: B:258:0x055f A[Catch: Exception -> 0x073f, TryCatch #15 {Exception -> 0x073f, blocks: (B:229:0x04ab, B:234:0x04c6, B:235:0x0514, B:268:0x0580, B:364:0x06ec, B:272:0x058b, B:274:0x059a, B:278:0x05a5, B:280:0x05b4, B:286:0x05c5, B:292:0x05da, B:294:0x05e9, B:300:0x05fa, B:306:0x060f, B:308:0x061e, B:317:0x0635, B:319:0x0644, B:326:0x0659, B:333:0x0672, B:335:0x0681, B:342:0x0696, B:349:0x06af, B:351:0x06be, B:356:0x06cd, B:358:0x06dc, B:237:0x0518, B:240:0x0523, B:243:0x052c, B:246:0x0537, B:249:0x0542, B:252:0x054b, B:255:0x0554, B:258:0x055f, B:261:0x056a, B:233:0x04bf), top: B:460:0x04ab }] */
    /* JADX WARN: Removed duplicated region for block: B:261:0x056a A[Catch: Exception -> 0x073f, TRY_LEAVE, TryCatch #15 {Exception -> 0x073f, blocks: (B:229:0x04ab, B:234:0x04c6, B:235:0x0514, B:268:0x0580, B:364:0x06ec, B:272:0x058b, B:274:0x059a, B:278:0x05a5, B:280:0x05b4, B:286:0x05c5, B:292:0x05da, B:294:0x05e9, B:300:0x05fa, B:306:0x060f, B:308:0x061e, B:317:0x0635, B:319:0x0644, B:326:0x0659, B:333:0x0672, B:335:0x0681, B:342:0x0696, B:349:0x06af, B:351:0x06be, B:356:0x06cd, B:358:0x06dc, B:237:0x0518, B:240:0x0523, B:243:0x052c, B:246:0x0537, B:249:0x0542, B:252:0x054b, B:255:0x0554, B:258:0x055f, B:261:0x056a, B:233:0x04bf), top: B:460:0x04ab }] */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0573 A[PHI: r3
  0x0573: PHI (r3v43 ??) = (r3v75 ??), (r3v76 ??), (r3v77 ??), (r3v78 ??), (r3v79 ??), (r3v80 ??), (r3v81 ??), (r3v82 ??), (r3v83 ??), (r3v84 ??) binds: [B:262:0x056e, B:259:0x0565, B:256:0x055a, B:253:0x054f, B:250:0x0546, B:247:0x053d, B:244:0x0532, B:241:0x0529, B:238:0x051e, B:236:0x0517] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:267:0x057a  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0580 A[Catch: Exception -> 0x073f, TRY_ENTER, TryCatch #15 {Exception -> 0x073f, blocks: (B:229:0x04ab, B:234:0x04c6, B:235:0x0514, B:268:0x0580, B:364:0x06ec, B:272:0x058b, B:274:0x059a, B:278:0x05a5, B:280:0x05b4, B:286:0x05c5, B:292:0x05da, B:294:0x05e9, B:300:0x05fa, B:306:0x060f, B:308:0x061e, B:317:0x0635, B:319:0x0644, B:326:0x0659, B:333:0x0672, B:335:0x0681, B:342:0x0696, B:349:0x06af, B:351:0x06be, B:356:0x06cd, B:358:0x06dc, B:237:0x0518, B:240:0x0523, B:243:0x052c, B:246:0x0537, B:249:0x0542, B:252:0x054b, B:255:0x0554, B:258:0x055f, B:261:0x056a, B:233:0x04bf), top: B:460:0x04ab }] */
    /* JADX WARN: Removed duplicated region for block: B:274:0x059a A[Catch: Exception -> 0x073f, TryCatch #15 {Exception -> 0x073f, blocks: (B:229:0x04ab, B:234:0x04c6, B:235:0x0514, B:268:0x0580, B:364:0x06ec, B:272:0x058b, B:274:0x059a, B:278:0x05a5, B:280:0x05b4, B:286:0x05c5, B:292:0x05da, B:294:0x05e9, B:300:0x05fa, B:306:0x060f, B:308:0x061e, B:317:0x0635, B:319:0x0644, B:326:0x0659, B:333:0x0672, B:335:0x0681, B:342:0x0696, B:349:0x06af, B:351:0x06be, B:356:0x06cd, B:358:0x06dc, B:237:0x0518, B:240:0x0523, B:243:0x052c, B:246:0x0537, B:249:0x0542, B:252:0x054b, B:255:0x0554, B:258:0x055f, B:261:0x056a, B:233:0x04bf), top: B:460:0x04ab }] */
    /* JADX WARN: Removed duplicated region for block: B:280:0x05b4 A[Catch: Exception -> 0x073f, TryCatch #15 {Exception -> 0x073f, blocks: (B:229:0x04ab, B:234:0x04c6, B:235:0x0514, B:268:0x0580, B:364:0x06ec, B:272:0x058b, B:274:0x059a, B:278:0x05a5, B:280:0x05b4, B:286:0x05c5, B:292:0x05da, B:294:0x05e9, B:300:0x05fa, B:306:0x060f, B:308:0x061e, B:317:0x0635, B:319:0x0644, B:326:0x0659, B:333:0x0672, B:335:0x0681, B:342:0x0696, B:349:0x06af, B:351:0x06be, B:356:0x06cd, B:358:0x06dc, B:237:0x0518, B:240:0x0523, B:243:0x052c, B:246:0x0537, B:249:0x0542, B:252:0x054b, B:255:0x0554, B:258:0x055f, B:261:0x056a, B:233:0x04bf), top: B:460:0x04ab }] */
    /* JADX WARN: Removed duplicated region for block: B:294:0x05e9 A[Catch: Exception -> 0x073f, TryCatch #15 {Exception -> 0x073f, blocks: (B:229:0x04ab, B:234:0x04c6, B:235:0x0514, B:268:0x0580, B:364:0x06ec, B:272:0x058b, B:274:0x059a, B:278:0x05a5, B:280:0x05b4, B:286:0x05c5, B:292:0x05da, B:294:0x05e9, B:300:0x05fa, B:306:0x060f, B:308:0x061e, B:317:0x0635, B:319:0x0644, B:326:0x0659, B:333:0x0672, B:335:0x0681, B:342:0x0696, B:349:0x06af, B:351:0x06be, B:356:0x06cd, B:358:0x06dc, B:237:0x0518, B:240:0x0523, B:243:0x052c, B:246:0x0537, B:249:0x0542, B:252:0x054b, B:255:0x0554, B:258:0x055f, B:261:0x056a, B:233:0x04bf), top: B:460:0x04ab }] */
    /* JADX WARN: Removed duplicated region for block: B:308:0x061e A[Catch: Exception -> 0x073f, TryCatch #15 {Exception -> 0x073f, blocks: (B:229:0x04ab, B:234:0x04c6, B:235:0x0514, B:268:0x0580, B:364:0x06ec, B:272:0x058b, B:274:0x059a, B:278:0x05a5, B:280:0x05b4, B:286:0x05c5, B:292:0x05da, B:294:0x05e9, B:300:0x05fa, B:306:0x060f, B:308:0x061e, B:317:0x0635, B:319:0x0644, B:326:0x0659, B:333:0x0672, B:335:0x0681, B:342:0x0696, B:349:0x06af, B:351:0x06be, B:356:0x06cd, B:358:0x06dc, B:237:0x0518, B:240:0x0523, B:243:0x052c, B:246:0x0537, B:249:0x0542, B:252:0x054b, B:255:0x0554, B:258:0x055f, B:261:0x056a, B:233:0x04bf), top: B:460:0x04ab }] */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0644 A[Catch: Exception -> 0x073f, TryCatch #15 {Exception -> 0x073f, blocks: (B:229:0x04ab, B:234:0x04c6, B:235:0x0514, B:268:0x0580, B:364:0x06ec, B:272:0x058b, B:274:0x059a, B:278:0x05a5, B:280:0x05b4, B:286:0x05c5, B:292:0x05da, B:294:0x05e9, B:300:0x05fa, B:306:0x060f, B:308:0x061e, B:317:0x0635, B:319:0x0644, B:326:0x0659, B:333:0x0672, B:335:0x0681, B:342:0x0696, B:349:0x06af, B:351:0x06be, B:356:0x06cd, B:358:0x06dc, B:237:0x0518, B:240:0x0523, B:243:0x052c, B:246:0x0537, B:249:0x0542, B:252:0x054b, B:255:0x0554, B:258:0x055f, B:261:0x056a, B:233:0x04bf), top: B:460:0x04ab }] */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0681 A[Catch: Exception -> 0x073f, TryCatch #15 {Exception -> 0x073f, blocks: (B:229:0x04ab, B:234:0x04c6, B:235:0x0514, B:268:0x0580, B:364:0x06ec, B:272:0x058b, B:274:0x059a, B:278:0x05a5, B:280:0x05b4, B:286:0x05c5, B:292:0x05da, B:294:0x05e9, B:300:0x05fa, B:306:0x060f, B:308:0x061e, B:317:0x0635, B:319:0x0644, B:326:0x0659, B:333:0x0672, B:335:0x0681, B:342:0x0696, B:349:0x06af, B:351:0x06be, B:356:0x06cd, B:358:0x06dc, B:237:0x0518, B:240:0x0523, B:243:0x052c, B:246:0x0537, B:249:0x0542, B:252:0x054b, B:255:0x0554, B:258:0x055f, B:261:0x056a, B:233:0x04bf), top: B:460:0x04ab }] */
    /* JADX WARN: Removed duplicated region for block: B:351:0x06be A[Catch: Exception -> 0x073f, TryCatch #15 {Exception -> 0x073f, blocks: (B:229:0x04ab, B:234:0x04c6, B:235:0x0514, B:268:0x0580, B:364:0x06ec, B:272:0x058b, B:274:0x059a, B:278:0x05a5, B:280:0x05b4, B:286:0x05c5, B:292:0x05da, B:294:0x05e9, B:300:0x05fa, B:306:0x060f, B:308:0x061e, B:317:0x0635, B:319:0x0644, B:326:0x0659, B:333:0x0672, B:335:0x0681, B:342:0x0696, B:349:0x06af, B:351:0x06be, B:356:0x06cd, B:358:0x06dc, B:237:0x0518, B:240:0x0523, B:243:0x052c, B:246:0x0537, B:249:0x0542, B:252:0x054b, B:255:0x0554, B:258:0x055f, B:261:0x056a, B:233:0x04bf), top: B:460:0x04ab }] */
    /* JADX WARN: Removed duplicated region for block: B:358:0x06dc A[Catch: Exception -> 0x073f, TryCatch #15 {Exception -> 0x073f, blocks: (B:229:0x04ab, B:234:0x04c6, B:235:0x0514, B:268:0x0580, B:364:0x06ec, B:272:0x058b, B:274:0x059a, B:278:0x05a5, B:280:0x05b4, B:286:0x05c5, B:292:0x05da, B:294:0x05e9, B:300:0x05fa, B:306:0x060f, B:308:0x061e, B:317:0x0635, B:319:0x0644, B:326:0x0659, B:333:0x0672, B:335:0x0681, B:342:0x0696, B:349:0x06af, B:351:0x06be, B:356:0x06cd, B:358:0x06dc, B:237:0x0518, B:240:0x0523, B:243:0x052c, B:246:0x0537, B:249:0x0542, B:252:0x054b, B:255:0x0554, B:258:0x055f, B:261:0x056a, B:233:0x04bf), top: B:460:0x04ab }] */
    /* JADX WARN: Removed duplicated region for block: B:361:0x06e7  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x077e A[Catch: Exception -> 0x0812, TryCatch #12 {Exception -> 0x0812, blocks: (B:391:0x076a, B:395:0x077e, B:417:0x07c3, B:402:0x079a, B:405:0x07a4, B:408:0x07af), top: B:456:0x00b4 }] */
    /* JADX WARN: Removed duplicated region for block: B:411:0x07b9  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x07bd  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x07d3 A[Catch: Exception -> 0x0818, TryCatch #6 {Exception -> 0x0818, blocks: (B:425:0x07ee, B:420:0x07ca, B:422:0x07d3, B:424:0x07dc, B:423:0x07d8), top: B:445:0x07ca }] */
    /* JADX WARN: Removed duplicated region for block: B:423:0x07d8 A[Catch: Exception -> 0x0818, TryCatch #6 {Exception -> 0x0818, blocks: (B:425:0x07ee, B:420:0x07ca, B:422:0x07d3, B:424:0x07dc, B:423:0x07d8), top: B:445:0x07ca }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01a2 A[Catch: Exception -> 0x03f3, TryCatch #11 {Exception -> 0x03f3, blocks: (B:44:0x0134, B:49:0x0156, B:50:0x019e, B:83:0x0210, B:179:0x03a0, B:87:0x021b, B:89:0x022e, B:93:0x023d, B:95:0x024c, B:101:0x0261, B:107:0x0276, B:109:0x0285, B:115:0x029a, B:121:0x02af, B:123:0x02be, B:132:0x02d9, B:134:0x02e8, B:141:0x0301, B:148:0x031a, B:150:0x0329, B:157:0x0342, B:164:0x035b, B:166:0x036a, B:171:0x037d, B:173:0x038c, B:52:0x01a2, B:55:0x01ad, B:58:0x01b6, B:61:0x01c1, B:64:0x01cc, B:67:0x01d5, B:70:0x01de, B:73:0x01e9, B:76:0x01f4, B:48:0x014e), top: B:455:0x0134 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01ad A[Catch: Exception -> 0x03f3, TryCatch #11 {Exception -> 0x03f3, blocks: (B:44:0x0134, B:49:0x0156, B:50:0x019e, B:83:0x0210, B:179:0x03a0, B:87:0x021b, B:89:0x022e, B:93:0x023d, B:95:0x024c, B:101:0x0261, B:107:0x0276, B:109:0x0285, B:115:0x029a, B:121:0x02af, B:123:0x02be, B:132:0x02d9, B:134:0x02e8, B:141:0x0301, B:148:0x031a, B:150:0x0329, B:157:0x0342, B:164:0x035b, B:166:0x036a, B:171:0x037d, B:173:0x038c, B:52:0x01a2, B:55:0x01ad, B:58:0x01b6, B:61:0x01c1, B:64:0x01cc, B:67:0x01d5, B:70:0x01de, B:73:0x01e9, B:76:0x01f4, B:48:0x014e), top: B:455:0x0134 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01b6 A[Catch: Exception -> 0x03f3, TryCatch #11 {Exception -> 0x03f3, blocks: (B:44:0x0134, B:49:0x0156, B:50:0x019e, B:83:0x0210, B:179:0x03a0, B:87:0x021b, B:89:0x022e, B:93:0x023d, B:95:0x024c, B:101:0x0261, B:107:0x0276, B:109:0x0285, B:115:0x029a, B:121:0x02af, B:123:0x02be, B:132:0x02d9, B:134:0x02e8, B:141:0x0301, B:148:0x031a, B:150:0x0329, B:157:0x0342, B:164:0x035b, B:166:0x036a, B:171:0x037d, B:173:0x038c, B:52:0x01a2, B:55:0x01ad, B:58:0x01b6, B:61:0x01c1, B:64:0x01cc, B:67:0x01d5, B:70:0x01de, B:73:0x01e9, B:76:0x01f4, B:48:0x014e), top: B:455:0x0134 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01c1 A[Catch: Exception -> 0x03f3, TryCatch #11 {Exception -> 0x03f3, blocks: (B:44:0x0134, B:49:0x0156, B:50:0x019e, B:83:0x0210, B:179:0x03a0, B:87:0x021b, B:89:0x022e, B:93:0x023d, B:95:0x024c, B:101:0x0261, B:107:0x0276, B:109:0x0285, B:115:0x029a, B:121:0x02af, B:123:0x02be, B:132:0x02d9, B:134:0x02e8, B:141:0x0301, B:148:0x031a, B:150:0x0329, B:157:0x0342, B:164:0x035b, B:166:0x036a, B:171:0x037d, B:173:0x038c, B:52:0x01a2, B:55:0x01ad, B:58:0x01b6, B:61:0x01c1, B:64:0x01cc, B:67:0x01d5, B:70:0x01de, B:73:0x01e9, B:76:0x01f4, B:48:0x014e), top: B:455:0x0134 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01cc A[Catch: Exception -> 0x03f3, TryCatch #11 {Exception -> 0x03f3, blocks: (B:44:0x0134, B:49:0x0156, B:50:0x019e, B:83:0x0210, B:179:0x03a0, B:87:0x021b, B:89:0x022e, B:93:0x023d, B:95:0x024c, B:101:0x0261, B:107:0x0276, B:109:0x0285, B:115:0x029a, B:121:0x02af, B:123:0x02be, B:132:0x02d9, B:134:0x02e8, B:141:0x0301, B:148:0x031a, B:150:0x0329, B:157:0x0342, B:164:0x035b, B:166:0x036a, B:171:0x037d, B:173:0x038c, B:52:0x01a2, B:55:0x01ad, B:58:0x01b6, B:61:0x01c1, B:64:0x01cc, B:67:0x01d5, B:70:0x01de, B:73:0x01e9, B:76:0x01f4, B:48:0x014e), top: B:455:0x0134 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01d5 A[Catch: Exception -> 0x03f3, TryCatch #11 {Exception -> 0x03f3, blocks: (B:44:0x0134, B:49:0x0156, B:50:0x019e, B:83:0x0210, B:179:0x03a0, B:87:0x021b, B:89:0x022e, B:93:0x023d, B:95:0x024c, B:101:0x0261, B:107:0x0276, B:109:0x0285, B:115:0x029a, B:121:0x02af, B:123:0x02be, B:132:0x02d9, B:134:0x02e8, B:141:0x0301, B:148:0x031a, B:150:0x0329, B:157:0x0342, B:164:0x035b, B:166:0x036a, B:171:0x037d, B:173:0x038c, B:52:0x01a2, B:55:0x01ad, B:58:0x01b6, B:61:0x01c1, B:64:0x01cc, B:67:0x01d5, B:70:0x01de, B:73:0x01e9, B:76:0x01f4, B:48:0x014e), top: B:455:0x0134 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01de A[Catch: Exception -> 0x03f3, TryCatch #11 {Exception -> 0x03f3, blocks: (B:44:0x0134, B:49:0x0156, B:50:0x019e, B:83:0x0210, B:179:0x03a0, B:87:0x021b, B:89:0x022e, B:93:0x023d, B:95:0x024c, B:101:0x0261, B:107:0x0276, B:109:0x0285, B:115:0x029a, B:121:0x02af, B:123:0x02be, B:132:0x02d9, B:134:0x02e8, B:141:0x0301, B:148:0x031a, B:150:0x0329, B:157:0x0342, B:164:0x035b, B:166:0x036a, B:171:0x037d, B:173:0x038c, B:52:0x01a2, B:55:0x01ad, B:58:0x01b6, B:61:0x01c1, B:64:0x01cc, B:67:0x01d5, B:70:0x01de, B:73:0x01e9, B:76:0x01f4, B:48:0x014e), top: B:455:0x0134 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01e9 A[Catch: Exception -> 0x03f3, TryCatch #11 {Exception -> 0x03f3, blocks: (B:44:0x0134, B:49:0x0156, B:50:0x019e, B:83:0x0210, B:179:0x03a0, B:87:0x021b, B:89:0x022e, B:93:0x023d, B:95:0x024c, B:101:0x0261, B:107:0x0276, B:109:0x0285, B:115:0x029a, B:121:0x02af, B:123:0x02be, B:132:0x02d9, B:134:0x02e8, B:141:0x0301, B:148:0x031a, B:150:0x0329, B:157:0x0342, B:164:0x035b, B:166:0x036a, B:171:0x037d, B:173:0x038c, B:52:0x01a2, B:55:0x01ad, B:58:0x01b6, B:61:0x01c1, B:64:0x01cc, B:67:0x01d5, B:70:0x01de, B:73:0x01e9, B:76:0x01f4, B:48:0x014e), top: B:455:0x0134 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01f4 A[Catch: Exception -> 0x03f3, TRY_LEAVE, TryCatch #11 {Exception -> 0x03f3, blocks: (B:44:0x0134, B:49:0x0156, B:50:0x019e, B:83:0x0210, B:179:0x03a0, B:87:0x021b, B:89:0x022e, B:93:0x023d, B:95:0x024c, B:101:0x0261, B:107:0x0276, B:109:0x0285, B:115:0x029a, B:121:0x02af, B:123:0x02be, B:132:0x02d9, B:134:0x02e8, B:141:0x0301, B:148:0x031a, B:150:0x0329, B:157:0x0342, B:164:0x035b, B:166:0x036a, B:171:0x037d, B:173:0x038c, B:52:0x01a2, B:55:0x01ad, B:58:0x01b6, B:61:0x01c1, B:64:0x01cc, B:67:0x01d5, B:70:0x01de, B:73:0x01e9, B:76:0x01f4, B:48:0x014e), top: B:455:0x0134 }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0210 A[Catch: Exception -> 0x03f3, TRY_ENTER, TryCatch #11 {Exception -> 0x03f3, blocks: (B:44:0x0134, B:49:0x0156, B:50:0x019e, B:83:0x0210, B:179:0x03a0, B:87:0x021b, B:89:0x022e, B:93:0x023d, B:95:0x024c, B:101:0x0261, B:107:0x0276, B:109:0x0285, B:115:0x029a, B:121:0x02af, B:123:0x02be, B:132:0x02d9, B:134:0x02e8, B:141:0x0301, B:148:0x031a, B:150:0x0329, B:157:0x0342, B:164:0x035b, B:166:0x036a, B:171:0x037d, B:173:0x038c, B:52:0x01a2, B:55:0x01ad, B:58:0x01b6, B:61:0x01c1, B:64:0x01cc, B:67:0x01d5, B:70:0x01de, B:73:0x01e9, B:76:0x01f4, B:48:0x014e), top: B:455:0x0134 }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x022e A[Catch: Exception -> 0x03f3, TryCatch #11 {Exception -> 0x03f3, blocks: (B:44:0x0134, B:49:0x0156, B:50:0x019e, B:83:0x0210, B:179:0x03a0, B:87:0x021b, B:89:0x022e, B:93:0x023d, B:95:0x024c, B:101:0x0261, B:107:0x0276, B:109:0x0285, B:115:0x029a, B:121:0x02af, B:123:0x02be, B:132:0x02d9, B:134:0x02e8, B:141:0x0301, B:148:0x031a, B:150:0x0329, B:157:0x0342, B:164:0x035b, B:166:0x036a, B:171:0x037d, B:173:0x038c, B:52:0x01a2, B:55:0x01ad, B:58:0x01b6, B:61:0x01c1, B:64:0x01cc, B:67:0x01d5, B:70:0x01de, B:73:0x01e9, B:76:0x01f4, B:48:0x014e), top: B:455:0x0134 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x024c A[Catch: Exception -> 0x03f3, TryCatch #11 {Exception -> 0x03f3, blocks: (B:44:0x0134, B:49:0x0156, B:50:0x019e, B:83:0x0210, B:179:0x03a0, B:87:0x021b, B:89:0x022e, B:93:0x023d, B:95:0x024c, B:101:0x0261, B:107:0x0276, B:109:0x0285, B:115:0x029a, B:121:0x02af, B:123:0x02be, B:132:0x02d9, B:134:0x02e8, B:141:0x0301, B:148:0x031a, B:150:0x0329, B:157:0x0342, B:164:0x035b, B:166:0x036a, B:171:0x037d, B:173:0x038c, B:52:0x01a2, B:55:0x01ad, B:58:0x01b6, B:61:0x01c1, B:64:0x01cc, B:67:0x01d5, B:70:0x01de, B:73:0x01e9, B:76:0x01f4, B:48:0x014e), top: B:455:0x0134 }] */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.byd.vrassistant.conversation.HvacLevelHelper] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v14, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v36 */
    /* JADX WARN: Type inference failed for: r1v37 */
    /* JADX WARN: Type inference failed for: r1v38 */
    /* JADX WARN: Type inference failed for: r1v39 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v40 */
    /* JADX WARN: Type inference failed for: r1v41 */
    /* JADX WARN: Type inference failed for: r1v42 */
    /* JADX WARN: Type inference failed for: r1v43 */
    /* JADX WARN: Type inference failed for: r1v44 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v41, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v43 */
    /* JADX WARN: Type inference failed for: r3v44 */
    /* JADX WARN: Type inference failed for: r3v45 */
    /* JADX WARN: Type inference failed for: r3v46 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v53, types: [int] */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v63, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v67 */
    /* JADX WARN: Type inference failed for: r3v68 */
    /* JADX WARN: Type inference failed for: r3v69 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v70 */
    /* JADX WARN: Type inference failed for: r3v71 */
    /* JADX WARN: Type inference failed for: r3v72 */
    /* JADX WARN: Type inference failed for: r3v73 */
    /* JADX WARN: Type inference failed for: r3v74 */
    /* JADX WARN: Type inference failed for: r3v75 */
    /* JADX WARN: Type inference failed for: r3v76 */
    /* JADX WARN: Type inference failed for: r3v77 */
    /* JADX WARN: Type inference failed for: r3v78 */
    /* JADX WARN: Type inference failed for: r3v79 */
    /* JADX WARN: Type inference failed for: r3v80 */
    /* JADX WARN: Type inference failed for: r3v81 */
    /* JADX WARN: Type inference failed for: r3v82 */
    /* JADX WARN: Type inference failed for: r3v83 */
    /* JADX WARN: Type inference failed for: r3v84 */
    /* JADX WARN: Type inference failed for: r3v85 */
    /* JADX WARN: Type inference failed for: r3v86 */
    /* JADX WARN: Type inference failed for: r3v87 */
    /* JADX WARN: Type inference failed for: r3v88 */
    /* JADX WARN: Type inference failed for: r3v89 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r3v90 */
    /* JADX WARN: Type inference failed for: r3v91 */
    /* JADX WARN: Type inference failed for: r3v92 */
    /* JADX WARN: Type inference failed for: r3v93 */
    /* JADX WARN: Type inference failed for: r3v94 */
    /* JADX WARN: Type inference failed for: r3v95 */
    /* JADX WARN: Type inference failed for: r3v96 */
    /* JADX WARN: Type inference failed for: r4v13, types: [int] */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v26 */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v37, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v40, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v59 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v60 */
    /* JADX WARN: Type inference failed for: r4v61 */
    /* JADX WARN: Type inference failed for: r4v62 */
    /* JADX WARN: Type inference failed for: r4v63 */
    /* JADX WARN: Type inference failed for: r4v64 */
    /* JADX WARN: Type inference failed for: r4v65 */
    /* JADX WARN: Type inference failed for: r6v29 */
    /* JADX WARN: Type inference failed for: r6v30 */
    /* JADX WARN: Type inference failed for: r6v37, types: [int] */
    /* JADX WARN: Type inference failed for: r6v51 */
    /* JADX WARN: Type inference failed for: r6v56 */
    /* JADX WARN: Type inference failed for: r6v57 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getMaxHavcLevel(cerence.ark.assistant.controller.domain.hvac.param.GetMaxHavcLevelParam r35, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.hvac.result.GetMaxHavcLevelResult> r36) {
        /*
            Method dump skipped, instruction units count: 2212
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.HvacLevelHelper.getMaxHavcLevel(cerence.ark.assistant.controller.domain.hvac.param.GetMaxHavcLevelParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    /*  JADX ERROR: Type inference failed with stack overflow
        jadx.core.utils.exceptions.JadxOverflowException
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    public void setHvacLevel(cerence.ark.assistant.controller.domain.hvac.param.SetHvacLevelParam r163, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.hvac.result.SetHvacLevelResult> r164) {
        /*
            Method dump skipped, instruction units count: 21872
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.HvacLevelHelper.setHvacLevel(cerence.ark.assistant.controller.domain.hvac.param.SetHvacLevelParam, cerence.ark.assistant.api.IResponseSender):void");
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
