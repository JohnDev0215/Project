package com.byd.vrassistant.conversation;

import android.app.UiStyleConfig;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetInteriorLightZonesParam;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetInteriorLightZonesResult;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import cerence.ark.assistant.controller.enumeration.VehicleAreaEnum;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.TagUtil;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class VehicleLampHelper {
    private BYDAutoStudy autoStudy;
    private BYDAutoSettingDevice settingDevice;
    private UiStyleConfig uiStyleConfig;
    private static final String TAG = TagUtil.getTagName(VehicleLampHelper.class);
    private static final int[] LIGHT_COLOR_7 = {1, 2, 3, 4, 5, 6, 7};
    private static final int[] LIGHT_COLOR_31 = {1, 3, 9, 14, 20, 25, 26, 28, 29};
    private static final int[] LIGHT_COLOR_64 = {2, 6, 18, 28, 40, 50, 52, 56, 58};
    private static final int[] LIGHT_COLOR_127 = {4, 12, 36, 56, 80, 100, 104, 112, 116};

    private static final class SingletonHolder {
        private static final VehicleLampHelper INSTANCE = new VehicleLampHelper();

        private SingletonHolder() {
        }
    }

    public static VehicleLampHelper getInstance() {
        RLog.i(TAG, "getInstance()");
        return SingletonHolder.INSTANCE;
    }

    private VehicleLampHelper() {
        try {
            RLog.i(TAG, "VehicleLampHelper()");
            this.settingDevice = BYDAutoSettingDevice.getInstance(BydVoiceApplication.getContext());
            this.uiStyleConfig = UiStyleConfig.getInstance(BydVoiceApplication.getContext());
            this.autoStudy = BYDAutoStudy.getInstance(BydVoiceApplication.getContext());
        } catch (Exception e) {
            RLog.i(TAG, "VehicleLampHelper() Exception ", e);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0142  */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getInteriorLightState(cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetInteriorLightStateParam r21, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetInteriorLightStateResult> r22) {
        /*
            Method dump skipped, instruction units count: 538
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleLampHelper.getInteriorLightState(cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetInteriorLightStateParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:47:0x010e  */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v11 */
    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v13 */
    /* JADX WARN: Type inference failed for: r14v14 */
    /* JADX WARN: Type inference failed for: r14v15 */
    /* JADX WARN: Type inference failed for: r14v18, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v21, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v23 */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v6, types: [int] */
    /* JADX WARN: Type inference failed for: r14v7 */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r14v9 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v21 */
    /* JADX WARN: Type inference failed for: r4v23 */
    /* JADX WARN: Type inference failed for: r4v25 */
    /* JADX WARN: Type inference failed for: r4v27 */
    /* JADX WARN: Type inference failed for: r4v29 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v31 */
    /* JADX WARN: Type inference failed for: r4v33 */
    /* JADX WARN: Type inference failed for: r4v35 */
    /* JADX WARN: Type inference failed for: r4v36 */
    /* JADX WARN: Type inference failed for: r4v37 */
    /* JADX WARN: Type inference failed for: r4v38 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v40, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v41, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v43 */
    /* JADX WARN: Type inference failed for: r4v46, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v47, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v48, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v50 */
    /* JADX WARN: Type inference failed for: r4v51 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.lang.StringBuilder] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setInteriorLightState(cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetInteriorLightStateParam r24, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r25) {
        /*
            Method dump skipped, instruction units count: 1206
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleLampHelper.setInteriorLightState(cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetInteriorLightStateParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0125  */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getInteriorLightBrightness(cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetInteriorLightBrightnessParam r19, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.vehiclecontrol.result.GetInteriorLightBrightnessResult> r20) {
        /*
            Method dump skipped, instruction units count: 500
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleLampHelper.getInteriorLightBrightness(cerence.ark.assistant.controller.domain.vehiclecontrol.param.GetInteriorLightBrightnessParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00e6  */
    /* JADX WARN: Type inference failed for: r10v4, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setInteriorLightBrightness(cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetInteriorLightBrightnessParam r21, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r22) {
        /*
            Method dump skipped, instruction units count: 640
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleLampHelper.setInteriorLightBrightness(cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetInteriorLightBrightnessParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0105  */
    /* JADX WARN: Type inference failed for: r7v5, types: [int] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void increaseInteriorLightBrightness(cerence.ark.assistant.controller.domain.vehiclecontrol.param.IncreaseInteriorLightBrightnessParam r22, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r23) {
        /*
            Method dump skipped, instruction units count: 654
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleLampHelper.increaseInteriorLightBrightness(cerence.ark.assistant.controller.domain.vehiclecontrol.param.IncreaseInteriorLightBrightnessParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0105  */
    /* JADX WARN: Type inference failed for: r7v5, types: [int] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void decreaseInteriorLightBrightness(cerence.ark.assistant.controller.domain.vehiclecontrol.param.DecreaseInteriorLightBrightnessParam r22, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r23) {
        /*
            Method dump skipped, instruction units count: 650
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleLampHelper.decreaseInteriorLightBrightness(cerence.ark.assistant.controller.domain.vehiclecontrol.param.DecreaseInteriorLightBrightnessParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:131:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0401  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x064f A[Catch: Exception -> 0x01fc, TRY_ENTER, TryCatch #1 {Exception -> 0x01fc, blocks: (B:18:0x00bf, B:19:0x00c9, B:61:0x0159, B:80:0x01ed, B:63:0x015e, B:78:0x01d4, B:299:0x064f, B:302:0x065a, B:305:0x0664, B:308:0x066f, B:311:0x0679, B:314:0x0683, B:317:0x068d, B:320:0x0698, B:323:0x06a2, B:64:0x0165, B:65:0x016b, B:66:0x0171, B:67:0x0176, B:68:0x017b, B:69:0x0181, B:70:0x0187, B:72:0x0193, B:75:0x0199, B:76:0x01a0, B:77:0x01a3, B:21:0x00ce, B:24:0x00d9, B:27:0x00e4, B:30:0x00ef, B:33:0x00fb, B:36:0x0105, B:39:0x0110, B:42:0x011b, B:45:0x0125, B:48:0x0130, B:51:0x013a, B:54:0x0145, B:57:0x014e, B:86:0x0207, B:87:0x0211, B:132:0x02ac, B:152:0x034c, B:134:0x02b1, B:151:0x0334, B:135:0x02b7, B:136:0x02bd, B:137:0x02c4, B:138:0x02ca, B:139:0x02d0, B:140:0x02d6, B:141:0x02dc, B:142:0x02e2, B:143:0x02e7, B:145:0x02f3, B:148:0x02f9, B:149:0x0300, B:150:0x0303, B:89:0x0216, B:92:0x0222, B:95:0x022d, B:98:0x0239, B:101:0x0244, B:104:0x024f, B:107:0x0259, B:110:0x0264, B:113:0x026f, B:116:0x0279, B:119:0x0284, B:122:0x028e, B:125:0x0298, B:128:0x02a1, B:156:0x035e, B:157:0x0368, B:202:0x0403, B:222:0x04a3, B:204:0x0408, B:221:0x048b, B:205:0x040e, B:206:0x0414, B:207:0x041b, B:208:0x0421, B:209:0x0427, B:210:0x042d, B:211:0x0433, B:212:0x0439, B:213:0x043e, B:215:0x044a, B:218:0x0450, B:219:0x0457, B:220:0x045a, B:159:0x036d, B:162:0x0379, B:165:0x0384, B:168:0x0390, B:171:0x039b, B:174:0x03a6, B:177:0x03b0, B:180:0x03bb, B:183:0x03c6, B:186:0x03d0, B:189:0x03db, B:192:0x03e5, B:195:0x03ef, B:198:0x03f8, B:229:0x04c4, B:272:0x055a, B:274:0x0565, B:275:0x056a, B:276:0x056f, B:278:0x0577, B:279:0x057e, B:280:0x0585, B:281:0x058c, B:282:0x0593, B:283:0x059a, B:289:0x05ad, B:292:0x05b3, B:232:0x04d0, B:235:0x04db, B:238:0x04e7, B:241:0x04f2, B:244:0x04fd, B:247:0x0507, B:250:0x0512, B:253:0x051d, B:256:0x0527, B:259:0x0532, B:262:0x053c, B:265:0x0546, B:268:0x054f), top: B:474:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:302:0x065a A[Catch: Exception -> 0x01fc, TryCatch #1 {Exception -> 0x01fc, blocks: (B:18:0x00bf, B:19:0x00c9, B:61:0x0159, B:80:0x01ed, B:63:0x015e, B:78:0x01d4, B:299:0x064f, B:302:0x065a, B:305:0x0664, B:308:0x066f, B:311:0x0679, B:314:0x0683, B:317:0x068d, B:320:0x0698, B:323:0x06a2, B:64:0x0165, B:65:0x016b, B:66:0x0171, B:67:0x0176, B:68:0x017b, B:69:0x0181, B:70:0x0187, B:72:0x0193, B:75:0x0199, B:76:0x01a0, B:77:0x01a3, B:21:0x00ce, B:24:0x00d9, B:27:0x00e4, B:30:0x00ef, B:33:0x00fb, B:36:0x0105, B:39:0x0110, B:42:0x011b, B:45:0x0125, B:48:0x0130, B:51:0x013a, B:54:0x0145, B:57:0x014e, B:86:0x0207, B:87:0x0211, B:132:0x02ac, B:152:0x034c, B:134:0x02b1, B:151:0x0334, B:135:0x02b7, B:136:0x02bd, B:137:0x02c4, B:138:0x02ca, B:139:0x02d0, B:140:0x02d6, B:141:0x02dc, B:142:0x02e2, B:143:0x02e7, B:145:0x02f3, B:148:0x02f9, B:149:0x0300, B:150:0x0303, B:89:0x0216, B:92:0x0222, B:95:0x022d, B:98:0x0239, B:101:0x0244, B:104:0x024f, B:107:0x0259, B:110:0x0264, B:113:0x026f, B:116:0x0279, B:119:0x0284, B:122:0x028e, B:125:0x0298, B:128:0x02a1, B:156:0x035e, B:157:0x0368, B:202:0x0403, B:222:0x04a3, B:204:0x0408, B:221:0x048b, B:205:0x040e, B:206:0x0414, B:207:0x041b, B:208:0x0421, B:209:0x0427, B:210:0x042d, B:211:0x0433, B:212:0x0439, B:213:0x043e, B:215:0x044a, B:218:0x0450, B:219:0x0457, B:220:0x045a, B:159:0x036d, B:162:0x0379, B:165:0x0384, B:168:0x0390, B:171:0x039b, B:174:0x03a6, B:177:0x03b0, B:180:0x03bb, B:183:0x03c6, B:186:0x03d0, B:189:0x03db, B:192:0x03e5, B:195:0x03ef, B:198:0x03f8, B:229:0x04c4, B:272:0x055a, B:274:0x0565, B:275:0x056a, B:276:0x056f, B:278:0x0577, B:279:0x057e, B:280:0x0585, B:281:0x058c, B:282:0x0593, B:283:0x059a, B:289:0x05ad, B:292:0x05b3, B:232:0x04d0, B:235:0x04db, B:238:0x04e7, B:241:0x04f2, B:244:0x04fd, B:247:0x0507, B:250:0x0512, B:253:0x051d, B:256:0x0527, B:259:0x0532, B:262:0x053c, B:265:0x0546, B:268:0x054f), top: B:474:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0664 A[Catch: Exception -> 0x01fc, TryCatch #1 {Exception -> 0x01fc, blocks: (B:18:0x00bf, B:19:0x00c9, B:61:0x0159, B:80:0x01ed, B:63:0x015e, B:78:0x01d4, B:299:0x064f, B:302:0x065a, B:305:0x0664, B:308:0x066f, B:311:0x0679, B:314:0x0683, B:317:0x068d, B:320:0x0698, B:323:0x06a2, B:64:0x0165, B:65:0x016b, B:66:0x0171, B:67:0x0176, B:68:0x017b, B:69:0x0181, B:70:0x0187, B:72:0x0193, B:75:0x0199, B:76:0x01a0, B:77:0x01a3, B:21:0x00ce, B:24:0x00d9, B:27:0x00e4, B:30:0x00ef, B:33:0x00fb, B:36:0x0105, B:39:0x0110, B:42:0x011b, B:45:0x0125, B:48:0x0130, B:51:0x013a, B:54:0x0145, B:57:0x014e, B:86:0x0207, B:87:0x0211, B:132:0x02ac, B:152:0x034c, B:134:0x02b1, B:151:0x0334, B:135:0x02b7, B:136:0x02bd, B:137:0x02c4, B:138:0x02ca, B:139:0x02d0, B:140:0x02d6, B:141:0x02dc, B:142:0x02e2, B:143:0x02e7, B:145:0x02f3, B:148:0x02f9, B:149:0x0300, B:150:0x0303, B:89:0x0216, B:92:0x0222, B:95:0x022d, B:98:0x0239, B:101:0x0244, B:104:0x024f, B:107:0x0259, B:110:0x0264, B:113:0x026f, B:116:0x0279, B:119:0x0284, B:122:0x028e, B:125:0x0298, B:128:0x02a1, B:156:0x035e, B:157:0x0368, B:202:0x0403, B:222:0x04a3, B:204:0x0408, B:221:0x048b, B:205:0x040e, B:206:0x0414, B:207:0x041b, B:208:0x0421, B:209:0x0427, B:210:0x042d, B:211:0x0433, B:212:0x0439, B:213:0x043e, B:215:0x044a, B:218:0x0450, B:219:0x0457, B:220:0x045a, B:159:0x036d, B:162:0x0379, B:165:0x0384, B:168:0x0390, B:171:0x039b, B:174:0x03a6, B:177:0x03b0, B:180:0x03bb, B:183:0x03c6, B:186:0x03d0, B:189:0x03db, B:192:0x03e5, B:195:0x03ef, B:198:0x03f8, B:229:0x04c4, B:272:0x055a, B:274:0x0565, B:275:0x056a, B:276:0x056f, B:278:0x0577, B:279:0x057e, B:280:0x0585, B:281:0x058c, B:282:0x0593, B:283:0x059a, B:289:0x05ad, B:292:0x05b3, B:232:0x04d0, B:235:0x04db, B:238:0x04e7, B:241:0x04f2, B:244:0x04fd, B:247:0x0507, B:250:0x0512, B:253:0x051d, B:256:0x0527, B:259:0x0532, B:262:0x053c, B:265:0x0546, B:268:0x054f), top: B:474:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:308:0x066f A[Catch: Exception -> 0x01fc, TryCatch #1 {Exception -> 0x01fc, blocks: (B:18:0x00bf, B:19:0x00c9, B:61:0x0159, B:80:0x01ed, B:63:0x015e, B:78:0x01d4, B:299:0x064f, B:302:0x065a, B:305:0x0664, B:308:0x066f, B:311:0x0679, B:314:0x0683, B:317:0x068d, B:320:0x0698, B:323:0x06a2, B:64:0x0165, B:65:0x016b, B:66:0x0171, B:67:0x0176, B:68:0x017b, B:69:0x0181, B:70:0x0187, B:72:0x0193, B:75:0x0199, B:76:0x01a0, B:77:0x01a3, B:21:0x00ce, B:24:0x00d9, B:27:0x00e4, B:30:0x00ef, B:33:0x00fb, B:36:0x0105, B:39:0x0110, B:42:0x011b, B:45:0x0125, B:48:0x0130, B:51:0x013a, B:54:0x0145, B:57:0x014e, B:86:0x0207, B:87:0x0211, B:132:0x02ac, B:152:0x034c, B:134:0x02b1, B:151:0x0334, B:135:0x02b7, B:136:0x02bd, B:137:0x02c4, B:138:0x02ca, B:139:0x02d0, B:140:0x02d6, B:141:0x02dc, B:142:0x02e2, B:143:0x02e7, B:145:0x02f3, B:148:0x02f9, B:149:0x0300, B:150:0x0303, B:89:0x0216, B:92:0x0222, B:95:0x022d, B:98:0x0239, B:101:0x0244, B:104:0x024f, B:107:0x0259, B:110:0x0264, B:113:0x026f, B:116:0x0279, B:119:0x0284, B:122:0x028e, B:125:0x0298, B:128:0x02a1, B:156:0x035e, B:157:0x0368, B:202:0x0403, B:222:0x04a3, B:204:0x0408, B:221:0x048b, B:205:0x040e, B:206:0x0414, B:207:0x041b, B:208:0x0421, B:209:0x0427, B:210:0x042d, B:211:0x0433, B:212:0x0439, B:213:0x043e, B:215:0x044a, B:218:0x0450, B:219:0x0457, B:220:0x045a, B:159:0x036d, B:162:0x0379, B:165:0x0384, B:168:0x0390, B:171:0x039b, B:174:0x03a6, B:177:0x03b0, B:180:0x03bb, B:183:0x03c6, B:186:0x03d0, B:189:0x03db, B:192:0x03e5, B:195:0x03ef, B:198:0x03f8, B:229:0x04c4, B:272:0x055a, B:274:0x0565, B:275:0x056a, B:276:0x056f, B:278:0x0577, B:279:0x057e, B:280:0x0585, B:281:0x058c, B:282:0x0593, B:283:0x059a, B:289:0x05ad, B:292:0x05b3, B:232:0x04d0, B:235:0x04db, B:238:0x04e7, B:241:0x04f2, B:244:0x04fd, B:247:0x0507, B:250:0x0512, B:253:0x051d, B:256:0x0527, B:259:0x0532, B:262:0x053c, B:265:0x0546, B:268:0x054f), top: B:474:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0679 A[Catch: Exception -> 0x01fc, TryCatch #1 {Exception -> 0x01fc, blocks: (B:18:0x00bf, B:19:0x00c9, B:61:0x0159, B:80:0x01ed, B:63:0x015e, B:78:0x01d4, B:299:0x064f, B:302:0x065a, B:305:0x0664, B:308:0x066f, B:311:0x0679, B:314:0x0683, B:317:0x068d, B:320:0x0698, B:323:0x06a2, B:64:0x0165, B:65:0x016b, B:66:0x0171, B:67:0x0176, B:68:0x017b, B:69:0x0181, B:70:0x0187, B:72:0x0193, B:75:0x0199, B:76:0x01a0, B:77:0x01a3, B:21:0x00ce, B:24:0x00d9, B:27:0x00e4, B:30:0x00ef, B:33:0x00fb, B:36:0x0105, B:39:0x0110, B:42:0x011b, B:45:0x0125, B:48:0x0130, B:51:0x013a, B:54:0x0145, B:57:0x014e, B:86:0x0207, B:87:0x0211, B:132:0x02ac, B:152:0x034c, B:134:0x02b1, B:151:0x0334, B:135:0x02b7, B:136:0x02bd, B:137:0x02c4, B:138:0x02ca, B:139:0x02d0, B:140:0x02d6, B:141:0x02dc, B:142:0x02e2, B:143:0x02e7, B:145:0x02f3, B:148:0x02f9, B:149:0x0300, B:150:0x0303, B:89:0x0216, B:92:0x0222, B:95:0x022d, B:98:0x0239, B:101:0x0244, B:104:0x024f, B:107:0x0259, B:110:0x0264, B:113:0x026f, B:116:0x0279, B:119:0x0284, B:122:0x028e, B:125:0x0298, B:128:0x02a1, B:156:0x035e, B:157:0x0368, B:202:0x0403, B:222:0x04a3, B:204:0x0408, B:221:0x048b, B:205:0x040e, B:206:0x0414, B:207:0x041b, B:208:0x0421, B:209:0x0427, B:210:0x042d, B:211:0x0433, B:212:0x0439, B:213:0x043e, B:215:0x044a, B:218:0x0450, B:219:0x0457, B:220:0x045a, B:159:0x036d, B:162:0x0379, B:165:0x0384, B:168:0x0390, B:171:0x039b, B:174:0x03a6, B:177:0x03b0, B:180:0x03bb, B:183:0x03c6, B:186:0x03d0, B:189:0x03db, B:192:0x03e5, B:195:0x03ef, B:198:0x03f8, B:229:0x04c4, B:272:0x055a, B:274:0x0565, B:275:0x056a, B:276:0x056f, B:278:0x0577, B:279:0x057e, B:280:0x0585, B:281:0x058c, B:282:0x0593, B:283:0x059a, B:289:0x05ad, B:292:0x05b3, B:232:0x04d0, B:235:0x04db, B:238:0x04e7, B:241:0x04f2, B:244:0x04fd, B:247:0x0507, B:250:0x0512, B:253:0x051d, B:256:0x0527, B:259:0x0532, B:262:0x053c, B:265:0x0546, B:268:0x054f), top: B:474:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:314:0x0683 A[Catch: Exception -> 0x01fc, TryCatch #1 {Exception -> 0x01fc, blocks: (B:18:0x00bf, B:19:0x00c9, B:61:0x0159, B:80:0x01ed, B:63:0x015e, B:78:0x01d4, B:299:0x064f, B:302:0x065a, B:305:0x0664, B:308:0x066f, B:311:0x0679, B:314:0x0683, B:317:0x068d, B:320:0x0698, B:323:0x06a2, B:64:0x0165, B:65:0x016b, B:66:0x0171, B:67:0x0176, B:68:0x017b, B:69:0x0181, B:70:0x0187, B:72:0x0193, B:75:0x0199, B:76:0x01a0, B:77:0x01a3, B:21:0x00ce, B:24:0x00d9, B:27:0x00e4, B:30:0x00ef, B:33:0x00fb, B:36:0x0105, B:39:0x0110, B:42:0x011b, B:45:0x0125, B:48:0x0130, B:51:0x013a, B:54:0x0145, B:57:0x014e, B:86:0x0207, B:87:0x0211, B:132:0x02ac, B:152:0x034c, B:134:0x02b1, B:151:0x0334, B:135:0x02b7, B:136:0x02bd, B:137:0x02c4, B:138:0x02ca, B:139:0x02d0, B:140:0x02d6, B:141:0x02dc, B:142:0x02e2, B:143:0x02e7, B:145:0x02f3, B:148:0x02f9, B:149:0x0300, B:150:0x0303, B:89:0x0216, B:92:0x0222, B:95:0x022d, B:98:0x0239, B:101:0x0244, B:104:0x024f, B:107:0x0259, B:110:0x0264, B:113:0x026f, B:116:0x0279, B:119:0x0284, B:122:0x028e, B:125:0x0298, B:128:0x02a1, B:156:0x035e, B:157:0x0368, B:202:0x0403, B:222:0x04a3, B:204:0x0408, B:221:0x048b, B:205:0x040e, B:206:0x0414, B:207:0x041b, B:208:0x0421, B:209:0x0427, B:210:0x042d, B:211:0x0433, B:212:0x0439, B:213:0x043e, B:215:0x044a, B:218:0x0450, B:219:0x0457, B:220:0x045a, B:159:0x036d, B:162:0x0379, B:165:0x0384, B:168:0x0390, B:171:0x039b, B:174:0x03a6, B:177:0x03b0, B:180:0x03bb, B:183:0x03c6, B:186:0x03d0, B:189:0x03db, B:192:0x03e5, B:195:0x03ef, B:198:0x03f8, B:229:0x04c4, B:272:0x055a, B:274:0x0565, B:275:0x056a, B:276:0x056f, B:278:0x0577, B:279:0x057e, B:280:0x0585, B:281:0x058c, B:282:0x0593, B:283:0x059a, B:289:0x05ad, B:292:0x05b3, B:232:0x04d0, B:235:0x04db, B:238:0x04e7, B:241:0x04f2, B:244:0x04fd, B:247:0x0507, B:250:0x0512, B:253:0x051d, B:256:0x0527, B:259:0x0532, B:262:0x053c, B:265:0x0546, B:268:0x054f), top: B:474:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:317:0x068d A[Catch: Exception -> 0x01fc, TryCatch #1 {Exception -> 0x01fc, blocks: (B:18:0x00bf, B:19:0x00c9, B:61:0x0159, B:80:0x01ed, B:63:0x015e, B:78:0x01d4, B:299:0x064f, B:302:0x065a, B:305:0x0664, B:308:0x066f, B:311:0x0679, B:314:0x0683, B:317:0x068d, B:320:0x0698, B:323:0x06a2, B:64:0x0165, B:65:0x016b, B:66:0x0171, B:67:0x0176, B:68:0x017b, B:69:0x0181, B:70:0x0187, B:72:0x0193, B:75:0x0199, B:76:0x01a0, B:77:0x01a3, B:21:0x00ce, B:24:0x00d9, B:27:0x00e4, B:30:0x00ef, B:33:0x00fb, B:36:0x0105, B:39:0x0110, B:42:0x011b, B:45:0x0125, B:48:0x0130, B:51:0x013a, B:54:0x0145, B:57:0x014e, B:86:0x0207, B:87:0x0211, B:132:0x02ac, B:152:0x034c, B:134:0x02b1, B:151:0x0334, B:135:0x02b7, B:136:0x02bd, B:137:0x02c4, B:138:0x02ca, B:139:0x02d0, B:140:0x02d6, B:141:0x02dc, B:142:0x02e2, B:143:0x02e7, B:145:0x02f3, B:148:0x02f9, B:149:0x0300, B:150:0x0303, B:89:0x0216, B:92:0x0222, B:95:0x022d, B:98:0x0239, B:101:0x0244, B:104:0x024f, B:107:0x0259, B:110:0x0264, B:113:0x026f, B:116:0x0279, B:119:0x0284, B:122:0x028e, B:125:0x0298, B:128:0x02a1, B:156:0x035e, B:157:0x0368, B:202:0x0403, B:222:0x04a3, B:204:0x0408, B:221:0x048b, B:205:0x040e, B:206:0x0414, B:207:0x041b, B:208:0x0421, B:209:0x0427, B:210:0x042d, B:211:0x0433, B:212:0x0439, B:213:0x043e, B:215:0x044a, B:218:0x0450, B:219:0x0457, B:220:0x045a, B:159:0x036d, B:162:0x0379, B:165:0x0384, B:168:0x0390, B:171:0x039b, B:174:0x03a6, B:177:0x03b0, B:180:0x03bb, B:183:0x03c6, B:186:0x03d0, B:189:0x03db, B:192:0x03e5, B:195:0x03ef, B:198:0x03f8, B:229:0x04c4, B:272:0x055a, B:274:0x0565, B:275:0x056a, B:276:0x056f, B:278:0x0577, B:279:0x057e, B:280:0x0585, B:281:0x058c, B:282:0x0593, B:283:0x059a, B:289:0x05ad, B:292:0x05b3, B:232:0x04d0, B:235:0x04db, B:238:0x04e7, B:241:0x04f2, B:244:0x04fd, B:247:0x0507, B:250:0x0512, B:253:0x051d, B:256:0x0527, B:259:0x0532, B:262:0x053c, B:265:0x0546, B:268:0x054f), top: B:474:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:320:0x0698 A[Catch: Exception -> 0x01fc, TryCatch #1 {Exception -> 0x01fc, blocks: (B:18:0x00bf, B:19:0x00c9, B:61:0x0159, B:80:0x01ed, B:63:0x015e, B:78:0x01d4, B:299:0x064f, B:302:0x065a, B:305:0x0664, B:308:0x066f, B:311:0x0679, B:314:0x0683, B:317:0x068d, B:320:0x0698, B:323:0x06a2, B:64:0x0165, B:65:0x016b, B:66:0x0171, B:67:0x0176, B:68:0x017b, B:69:0x0181, B:70:0x0187, B:72:0x0193, B:75:0x0199, B:76:0x01a0, B:77:0x01a3, B:21:0x00ce, B:24:0x00d9, B:27:0x00e4, B:30:0x00ef, B:33:0x00fb, B:36:0x0105, B:39:0x0110, B:42:0x011b, B:45:0x0125, B:48:0x0130, B:51:0x013a, B:54:0x0145, B:57:0x014e, B:86:0x0207, B:87:0x0211, B:132:0x02ac, B:152:0x034c, B:134:0x02b1, B:151:0x0334, B:135:0x02b7, B:136:0x02bd, B:137:0x02c4, B:138:0x02ca, B:139:0x02d0, B:140:0x02d6, B:141:0x02dc, B:142:0x02e2, B:143:0x02e7, B:145:0x02f3, B:148:0x02f9, B:149:0x0300, B:150:0x0303, B:89:0x0216, B:92:0x0222, B:95:0x022d, B:98:0x0239, B:101:0x0244, B:104:0x024f, B:107:0x0259, B:110:0x0264, B:113:0x026f, B:116:0x0279, B:119:0x0284, B:122:0x028e, B:125:0x0298, B:128:0x02a1, B:156:0x035e, B:157:0x0368, B:202:0x0403, B:222:0x04a3, B:204:0x0408, B:221:0x048b, B:205:0x040e, B:206:0x0414, B:207:0x041b, B:208:0x0421, B:209:0x0427, B:210:0x042d, B:211:0x0433, B:212:0x0439, B:213:0x043e, B:215:0x044a, B:218:0x0450, B:219:0x0457, B:220:0x045a, B:159:0x036d, B:162:0x0379, B:165:0x0384, B:168:0x0390, B:171:0x039b, B:174:0x03a6, B:177:0x03b0, B:180:0x03bb, B:183:0x03c6, B:186:0x03d0, B:189:0x03db, B:192:0x03e5, B:195:0x03ef, B:198:0x03f8, B:229:0x04c4, B:272:0x055a, B:274:0x0565, B:275:0x056a, B:276:0x056f, B:278:0x0577, B:279:0x057e, B:280:0x0585, B:281:0x058c, B:282:0x0593, B:283:0x059a, B:289:0x05ad, B:292:0x05b3, B:232:0x04d0, B:235:0x04db, B:238:0x04e7, B:241:0x04f2, B:244:0x04fd, B:247:0x0507, B:250:0x0512, B:253:0x051d, B:256:0x0527, B:259:0x0532, B:262:0x053c, B:265:0x0546, B:268:0x054f), top: B:474:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:323:0x06a2 A[Catch: Exception -> 0x01fc, TRY_LEAVE, TryCatch #1 {Exception -> 0x01fc, blocks: (B:18:0x00bf, B:19:0x00c9, B:61:0x0159, B:80:0x01ed, B:63:0x015e, B:78:0x01d4, B:299:0x064f, B:302:0x065a, B:305:0x0664, B:308:0x066f, B:311:0x0679, B:314:0x0683, B:317:0x068d, B:320:0x0698, B:323:0x06a2, B:64:0x0165, B:65:0x016b, B:66:0x0171, B:67:0x0176, B:68:0x017b, B:69:0x0181, B:70:0x0187, B:72:0x0193, B:75:0x0199, B:76:0x01a0, B:77:0x01a3, B:21:0x00ce, B:24:0x00d9, B:27:0x00e4, B:30:0x00ef, B:33:0x00fb, B:36:0x0105, B:39:0x0110, B:42:0x011b, B:45:0x0125, B:48:0x0130, B:51:0x013a, B:54:0x0145, B:57:0x014e, B:86:0x0207, B:87:0x0211, B:132:0x02ac, B:152:0x034c, B:134:0x02b1, B:151:0x0334, B:135:0x02b7, B:136:0x02bd, B:137:0x02c4, B:138:0x02ca, B:139:0x02d0, B:140:0x02d6, B:141:0x02dc, B:142:0x02e2, B:143:0x02e7, B:145:0x02f3, B:148:0x02f9, B:149:0x0300, B:150:0x0303, B:89:0x0216, B:92:0x0222, B:95:0x022d, B:98:0x0239, B:101:0x0244, B:104:0x024f, B:107:0x0259, B:110:0x0264, B:113:0x026f, B:116:0x0279, B:119:0x0284, B:122:0x028e, B:125:0x0298, B:128:0x02a1, B:156:0x035e, B:157:0x0368, B:202:0x0403, B:222:0x04a3, B:204:0x0408, B:221:0x048b, B:205:0x040e, B:206:0x0414, B:207:0x041b, B:208:0x0421, B:209:0x0427, B:210:0x042d, B:211:0x0433, B:212:0x0439, B:213:0x043e, B:215:0x044a, B:218:0x0450, B:219:0x0457, B:220:0x045a, B:159:0x036d, B:162:0x0379, B:165:0x0384, B:168:0x0390, B:171:0x039b, B:174:0x03a6, B:177:0x03b0, B:180:0x03bb, B:183:0x03c6, B:186:0x03d0, B:189:0x03db, B:192:0x03e5, B:195:0x03ef, B:198:0x03f8, B:229:0x04c4, B:272:0x055a, B:274:0x0565, B:275:0x056a, B:276:0x056f, B:278:0x0577, B:279:0x057e, B:280:0x0585, B:281:0x058c, B:282:0x0593, B:283:0x059a, B:289:0x05ad, B:292:0x05b3, B:232:0x04d0, B:235:0x04db, B:238:0x04e7, B:241:0x04f2, B:244:0x04fd, B:247:0x0507, B:250:0x0512, B:253:0x051d, B:256:0x0527, B:259:0x0532, B:262:0x053c, B:265:0x0546, B:268:0x054f), top: B:474:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:329:0x06be  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x06ce A[Catch: Exception -> 0x0a8d, TRY_ENTER, TryCatch #0 {Exception -> 0x0a8d, blocks: (B:296:0x0604, B:331:0x06ce, B:339:0x06e8, B:342:0x0727, B:226:0x04b5, B:295:0x05ee, B:286:0x05a1, B:294:0x05bd), top: B:472:0x04b5 }] */
    /* JADX WARN: Removed duplicated region for block: B:350:0x073d A[Catch: Exception -> 0x0ac4, TryCatch #5 {Exception -> 0x0ac4, blocks: (B:373:0x0812, B:374:0x0819, B:404:0x087b, B:453:0x0a65, B:406:0x0880, B:410:0x0890, B:411:0x08a8, B:413:0x08b1, B:415:0x08ce, B:417:0x08dd, B:418:0x08e0, B:420:0x08fe, B:422:0x090d, B:423:0x0925, B:425:0x0931, B:426:0x0949, B:428:0x0952, B:430:0x096f, B:432:0x097e, B:433:0x0981, B:435:0x099f, B:437:0x09ae, B:438:0x09c6, B:440:0x09d2, B:441:0x09ea, B:443:0x09f3, B:445:0x0a0f, B:447:0x0a1e, B:448:0x0a21, B:450:0x0a3e, B:452:0x0a4d, B:376:0x081d, B:379:0x0828, B:382:0x0832, B:385:0x083d, B:388:0x0847, B:391:0x0850, B:394:0x085a, B:397:0x0865, B:400:0x086f, B:346:0x0733, B:350:0x073d, B:356:0x0759, B:361:0x079d, B:363:0x07ac, B:368:0x07c7, B:371:0x0803, B:454:0x0a7b, B:458:0x0a93, B:463:0x0ab0), top: B:481:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:363:0x07ac A[Catch: Exception -> 0x0ac4, TryCatch #5 {Exception -> 0x0ac4, blocks: (B:373:0x0812, B:374:0x0819, B:404:0x087b, B:453:0x0a65, B:406:0x0880, B:410:0x0890, B:411:0x08a8, B:413:0x08b1, B:415:0x08ce, B:417:0x08dd, B:418:0x08e0, B:420:0x08fe, B:422:0x090d, B:423:0x0925, B:425:0x0931, B:426:0x0949, B:428:0x0952, B:430:0x096f, B:432:0x097e, B:433:0x0981, B:435:0x099f, B:437:0x09ae, B:438:0x09c6, B:440:0x09d2, B:441:0x09ea, B:443:0x09f3, B:445:0x0a0f, B:447:0x0a1e, B:448:0x0a21, B:450:0x0a3e, B:452:0x0a4d, B:376:0x081d, B:379:0x0828, B:382:0x0832, B:385:0x083d, B:388:0x0847, B:391:0x0850, B:394:0x085a, B:397:0x0865, B:400:0x086f, B:346:0x0733, B:350:0x073d, B:356:0x0759, B:361:0x079d, B:363:0x07ac, B:368:0x07c7, B:371:0x0803, B:454:0x0a7b, B:458:0x0a93, B:463:0x0ab0), top: B:481:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:375:0x081c  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x081d A[Catch: Exception -> 0x0ac4, TryCatch #5 {Exception -> 0x0ac4, blocks: (B:373:0x0812, B:374:0x0819, B:404:0x087b, B:453:0x0a65, B:406:0x0880, B:410:0x0890, B:411:0x08a8, B:413:0x08b1, B:415:0x08ce, B:417:0x08dd, B:418:0x08e0, B:420:0x08fe, B:422:0x090d, B:423:0x0925, B:425:0x0931, B:426:0x0949, B:428:0x0952, B:430:0x096f, B:432:0x097e, B:433:0x0981, B:435:0x099f, B:437:0x09ae, B:438:0x09c6, B:440:0x09d2, B:441:0x09ea, B:443:0x09f3, B:445:0x0a0f, B:447:0x0a1e, B:448:0x0a21, B:450:0x0a3e, B:452:0x0a4d, B:376:0x081d, B:379:0x0828, B:382:0x0832, B:385:0x083d, B:388:0x0847, B:391:0x0850, B:394:0x085a, B:397:0x0865, B:400:0x086f, B:346:0x0733, B:350:0x073d, B:356:0x0759, B:361:0x079d, B:363:0x07ac, B:368:0x07c7, B:371:0x0803, B:454:0x0a7b, B:458:0x0a93, B:463:0x0ab0), top: B:481:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:379:0x0828 A[Catch: Exception -> 0x0ac4, TryCatch #5 {Exception -> 0x0ac4, blocks: (B:373:0x0812, B:374:0x0819, B:404:0x087b, B:453:0x0a65, B:406:0x0880, B:410:0x0890, B:411:0x08a8, B:413:0x08b1, B:415:0x08ce, B:417:0x08dd, B:418:0x08e0, B:420:0x08fe, B:422:0x090d, B:423:0x0925, B:425:0x0931, B:426:0x0949, B:428:0x0952, B:430:0x096f, B:432:0x097e, B:433:0x0981, B:435:0x099f, B:437:0x09ae, B:438:0x09c6, B:440:0x09d2, B:441:0x09ea, B:443:0x09f3, B:445:0x0a0f, B:447:0x0a1e, B:448:0x0a21, B:450:0x0a3e, B:452:0x0a4d, B:376:0x081d, B:379:0x0828, B:382:0x0832, B:385:0x083d, B:388:0x0847, B:391:0x0850, B:394:0x085a, B:397:0x0865, B:400:0x086f, B:346:0x0733, B:350:0x073d, B:356:0x0759, B:361:0x079d, B:363:0x07ac, B:368:0x07c7, B:371:0x0803, B:454:0x0a7b, B:458:0x0a93, B:463:0x0ab0), top: B:481:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:382:0x0832 A[Catch: Exception -> 0x0ac4, TryCatch #5 {Exception -> 0x0ac4, blocks: (B:373:0x0812, B:374:0x0819, B:404:0x087b, B:453:0x0a65, B:406:0x0880, B:410:0x0890, B:411:0x08a8, B:413:0x08b1, B:415:0x08ce, B:417:0x08dd, B:418:0x08e0, B:420:0x08fe, B:422:0x090d, B:423:0x0925, B:425:0x0931, B:426:0x0949, B:428:0x0952, B:430:0x096f, B:432:0x097e, B:433:0x0981, B:435:0x099f, B:437:0x09ae, B:438:0x09c6, B:440:0x09d2, B:441:0x09ea, B:443:0x09f3, B:445:0x0a0f, B:447:0x0a1e, B:448:0x0a21, B:450:0x0a3e, B:452:0x0a4d, B:376:0x081d, B:379:0x0828, B:382:0x0832, B:385:0x083d, B:388:0x0847, B:391:0x0850, B:394:0x085a, B:397:0x0865, B:400:0x086f, B:346:0x0733, B:350:0x073d, B:356:0x0759, B:361:0x079d, B:363:0x07ac, B:368:0x07c7, B:371:0x0803, B:454:0x0a7b, B:458:0x0a93, B:463:0x0ab0), top: B:481:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:385:0x083d A[Catch: Exception -> 0x0ac4, TryCatch #5 {Exception -> 0x0ac4, blocks: (B:373:0x0812, B:374:0x0819, B:404:0x087b, B:453:0x0a65, B:406:0x0880, B:410:0x0890, B:411:0x08a8, B:413:0x08b1, B:415:0x08ce, B:417:0x08dd, B:418:0x08e0, B:420:0x08fe, B:422:0x090d, B:423:0x0925, B:425:0x0931, B:426:0x0949, B:428:0x0952, B:430:0x096f, B:432:0x097e, B:433:0x0981, B:435:0x099f, B:437:0x09ae, B:438:0x09c6, B:440:0x09d2, B:441:0x09ea, B:443:0x09f3, B:445:0x0a0f, B:447:0x0a1e, B:448:0x0a21, B:450:0x0a3e, B:452:0x0a4d, B:376:0x081d, B:379:0x0828, B:382:0x0832, B:385:0x083d, B:388:0x0847, B:391:0x0850, B:394:0x085a, B:397:0x0865, B:400:0x086f, B:346:0x0733, B:350:0x073d, B:356:0x0759, B:361:0x079d, B:363:0x07ac, B:368:0x07c7, B:371:0x0803, B:454:0x0a7b, B:458:0x0a93, B:463:0x0ab0), top: B:481:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:388:0x0847 A[Catch: Exception -> 0x0ac4, TryCatch #5 {Exception -> 0x0ac4, blocks: (B:373:0x0812, B:374:0x0819, B:404:0x087b, B:453:0x0a65, B:406:0x0880, B:410:0x0890, B:411:0x08a8, B:413:0x08b1, B:415:0x08ce, B:417:0x08dd, B:418:0x08e0, B:420:0x08fe, B:422:0x090d, B:423:0x0925, B:425:0x0931, B:426:0x0949, B:428:0x0952, B:430:0x096f, B:432:0x097e, B:433:0x0981, B:435:0x099f, B:437:0x09ae, B:438:0x09c6, B:440:0x09d2, B:441:0x09ea, B:443:0x09f3, B:445:0x0a0f, B:447:0x0a1e, B:448:0x0a21, B:450:0x0a3e, B:452:0x0a4d, B:376:0x081d, B:379:0x0828, B:382:0x0832, B:385:0x083d, B:388:0x0847, B:391:0x0850, B:394:0x085a, B:397:0x0865, B:400:0x086f, B:346:0x0733, B:350:0x073d, B:356:0x0759, B:361:0x079d, B:363:0x07ac, B:368:0x07c7, B:371:0x0803, B:454:0x0a7b, B:458:0x0a93, B:463:0x0ab0), top: B:481:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:391:0x0850 A[Catch: Exception -> 0x0ac4, TryCatch #5 {Exception -> 0x0ac4, blocks: (B:373:0x0812, B:374:0x0819, B:404:0x087b, B:453:0x0a65, B:406:0x0880, B:410:0x0890, B:411:0x08a8, B:413:0x08b1, B:415:0x08ce, B:417:0x08dd, B:418:0x08e0, B:420:0x08fe, B:422:0x090d, B:423:0x0925, B:425:0x0931, B:426:0x0949, B:428:0x0952, B:430:0x096f, B:432:0x097e, B:433:0x0981, B:435:0x099f, B:437:0x09ae, B:438:0x09c6, B:440:0x09d2, B:441:0x09ea, B:443:0x09f3, B:445:0x0a0f, B:447:0x0a1e, B:448:0x0a21, B:450:0x0a3e, B:452:0x0a4d, B:376:0x081d, B:379:0x0828, B:382:0x0832, B:385:0x083d, B:388:0x0847, B:391:0x0850, B:394:0x085a, B:397:0x0865, B:400:0x086f, B:346:0x0733, B:350:0x073d, B:356:0x0759, B:361:0x079d, B:363:0x07ac, B:368:0x07c7, B:371:0x0803, B:454:0x0a7b, B:458:0x0a93, B:463:0x0ab0), top: B:481:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:394:0x085a A[Catch: Exception -> 0x0ac4, TryCatch #5 {Exception -> 0x0ac4, blocks: (B:373:0x0812, B:374:0x0819, B:404:0x087b, B:453:0x0a65, B:406:0x0880, B:410:0x0890, B:411:0x08a8, B:413:0x08b1, B:415:0x08ce, B:417:0x08dd, B:418:0x08e0, B:420:0x08fe, B:422:0x090d, B:423:0x0925, B:425:0x0931, B:426:0x0949, B:428:0x0952, B:430:0x096f, B:432:0x097e, B:433:0x0981, B:435:0x099f, B:437:0x09ae, B:438:0x09c6, B:440:0x09d2, B:441:0x09ea, B:443:0x09f3, B:445:0x0a0f, B:447:0x0a1e, B:448:0x0a21, B:450:0x0a3e, B:452:0x0a4d, B:376:0x081d, B:379:0x0828, B:382:0x0832, B:385:0x083d, B:388:0x0847, B:391:0x0850, B:394:0x085a, B:397:0x0865, B:400:0x086f, B:346:0x0733, B:350:0x073d, B:356:0x0759, B:361:0x079d, B:363:0x07ac, B:368:0x07c7, B:371:0x0803, B:454:0x0a7b, B:458:0x0a93, B:463:0x0ab0), top: B:481:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:397:0x0865 A[Catch: Exception -> 0x0ac4, TryCatch #5 {Exception -> 0x0ac4, blocks: (B:373:0x0812, B:374:0x0819, B:404:0x087b, B:453:0x0a65, B:406:0x0880, B:410:0x0890, B:411:0x08a8, B:413:0x08b1, B:415:0x08ce, B:417:0x08dd, B:418:0x08e0, B:420:0x08fe, B:422:0x090d, B:423:0x0925, B:425:0x0931, B:426:0x0949, B:428:0x0952, B:430:0x096f, B:432:0x097e, B:433:0x0981, B:435:0x099f, B:437:0x09ae, B:438:0x09c6, B:440:0x09d2, B:441:0x09ea, B:443:0x09f3, B:445:0x0a0f, B:447:0x0a1e, B:448:0x0a21, B:450:0x0a3e, B:452:0x0a4d, B:376:0x081d, B:379:0x0828, B:382:0x0832, B:385:0x083d, B:388:0x0847, B:391:0x0850, B:394:0x085a, B:397:0x0865, B:400:0x086f, B:346:0x0733, B:350:0x073d, B:356:0x0759, B:361:0x079d, B:363:0x07ac, B:368:0x07c7, B:371:0x0803, B:454:0x0a7b, B:458:0x0a93, B:463:0x0ab0), top: B:481:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:400:0x086f A[Catch: Exception -> 0x0ac4, TryCatch #5 {Exception -> 0x0ac4, blocks: (B:373:0x0812, B:374:0x0819, B:404:0x087b, B:453:0x0a65, B:406:0x0880, B:410:0x0890, B:411:0x08a8, B:413:0x08b1, B:415:0x08ce, B:417:0x08dd, B:418:0x08e0, B:420:0x08fe, B:422:0x090d, B:423:0x0925, B:425:0x0931, B:426:0x0949, B:428:0x0952, B:430:0x096f, B:432:0x097e, B:433:0x0981, B:435:0x099f, B:437:0x09ae, B:438:0x09c6, B:440:0x09d2, B:441:0x09ea, B:443:0x09f3, B:445:0x0a0f, B:447:0x0a1e, B:448:0x0a21, B:450:0x0a3e, B:452:0x0a4d, B:376:0x081d, B:379:0x0828, B:382:0x0832, B:385:0x083d, B:388:0x0847, B:391:0x0850, B:394:0x085a, B:397:0x0865, B:400:0x086f, B:346:0x0733, B:350:0x073d, B:356:0x0759, B:361:0x079d, B:363:0x07ac, B:368:0x07c7, B:371:0x0803, B:454:0x0a7b, B:458:0x0a93, B:463:0x0ab0), top: B:481:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:403:0x0879  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x087e  */
    /* JADX WARN: Removed duplicated region for block: B:406:0x0880 A[Catch: Exception -> 0x0ac4, TryCatch #5 {Exception -> 0x0ac4, blocks: (B:373:0x0812, B:374:0x0819, B:404:0x087b, B:453:0x0a65, B:406:0x0880, B:410:0x0890, B:411:0x08a8, B:413:0x08b1, B:415:0x08ce, B:417:0x08dd, B:418:0x08e0, B:420:0x08fe, B:422:0x090d, B:423:0x0925, B:425:0x0931, B:426:0x0949, B:428:0x0952, B:430:0x096f, B:432:0x097e, B:433:0x0981, B:435:0x099f, B:437:0x09ae, B:438:0x09c6, B:440:0x09d2, B:441:0x09ea, B:443:0x09f3, B:445:0x0a0f, B:447:0x0a1e, B:448:0x0a21, B:450:0x0a3e, B:452:0x0a4d, B:376:0x081d, B:379:0x0828, B:382:0x0832, B:385:0x083d, B:388:0x0847, B:391:0x0850, B:394:0x085a, B:397:0x0865, B:400:0x086f, B:346:0x0733, B:350:0x073d, B:356:0x0759, B:361:0x079d, B:363:0x07ac, B:368:0x07c7, B:371:0x0803, B:454:0x0a7b, B:458:0x0a93, B:463:0x0ab0), top: B:481:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:423:0x0925 A[Catch: Exception -> 0x0ac4, TryCatch #5 {Exception -> 0x0ac4, blocks: (B:373:0x0812, B:374:0x0819, B:404:0x087b, B:453:0x0a65, B:406:0x0880, B:410:0x0890, B:411:0x08a8, B:413:0x08b1, B:415:0x08ce, B:417:0x08dd, B:418:0x08e0, B:420:0x08fe, B:422:0x090d, B:423:0x0925, B:425:0x0931, B:426:0x0949, B:428:0x0952, B:430:0x096f, B:432:0x097e, B:433:0x0981, B:435:0x099f, B:437:0x09ae, B:438:0x09c6, B:440:0x09d2, B:441:0x09ea, B:443:0x09f3, B:445:0x0a0f, B:447:0x0a1e, B:448:0x0a21, B:450:0x0a3e, B:452:0x0a4d, B:376:0x081d, B:379:0x0828, B:382:0x0832, B:385:0x083d, B:388:0x0847, B:391:0x0850, B:394:0x085a, B:397:0x0865, B:400:0x086f, B:346:0x0733, B:350:0x073d, B:356:0x0759, B:361:0x079d, B:363:0x07ac, B:368:0x07c7, B:371:0x0803, B:454:0x0a7b, B:458:0x0a93, B:463:0x0ab0), top: B:481:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:438:0x09c6 A[Catch: Exception -> 0x0ac4, TryCatch #5 {Exception -> 0x0ac4, blocks: (B:373:0x0812, B:374:0x0819, B:404:0x087b, B:453:0x0a65, B:406:0x0880, B:410:0x0890, B:411:0x08a8, B:413:0x08b1, B:415:0x08ce, B:417:0x08dd, B:418:0x08e0, B:420:0x08fe, B:422:0x090d, B:423:0x0925, B:425:0x0931, B:426:0x0949, B:428:0x0952, B:430:0x096f, B:432:0x097e, B:433:0x0981, B:435:0x099f, B:437:0x09ae, B:438:0x09c6, B:440:0x09d2, B:441:0x09ea, B:443:0x09f3, B:445:0x0a0f, B:447:0x0a1e, B:448:0x0a21, B:450:0x0a3e, B:452:0x0a4d, B:376:0x081d, B:379:0x0828, B:382:0x0832, B:385:0x083d, B:388:0x0847, B:391:0x0850, B:394:0x085a, B:397:0x0865, B:400:0x086f, B:346:0x0733, B:350:0x073d, B:356:0x0759, B:361:0x079d, B:363:0x07ac, B:368:0x07c7, B:371:0x0803, B:454:0x0a7b, B:458:0x0a93, B:463:0x0ab0), top: B:481:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:506:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0157  */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v14 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v28 */
    /* JADX WARN: Type inference failed for: r11v29 */
    /* JADX WARN: Type inference failed for: r11v5, types: [boolean] */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1091)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setInteriorLightColor(cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetInteriorLightColorParam r27, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r28) {
        /*
            Method dump skipped, instruction units count: 3260
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.VehicleLampHelper.setInteriorLightColor(cerence.ark.assistant.controller.domain.vehiclecontrol.param.SetInteriorLightColorParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    public void getInteriorLightZones(GetInteriorLightZonesParam getInteriorLightZonesParam, IResponseSender<GetInteriorLightZonesResult> iResponseSender) {
        try {
            String str = TAG;
            RLog.i(str, "getInteriorLightZones: json = " + getInteriorLightZonesParam.toJsonString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getInteriorLightZones】");
            if ("AMBIENT_LIGHT".equals(getInteriorLightZonesParam.getLightType())) {
                GetInteriorLightZonesResult getInteriorLightZonesResult = new GetInteriorLightZonesResult();
                ArrayList arrayList = new ArrayList();
                int ambientAreaSupport = this.autoStudy.getAmbientAreaSupport();
                RLog.i(str, "checkVehicleFunctionAvailability: areaSpecificConfig = " + ambientAreaSupport);
                arrayList.add(VehicleAreaEnum.SEAT_ROW_FRONT);
                if (ambientAreaSupport == 2 || ambientAreaSupport == 3) {
                    arrayList.add(VehicleAreaEnum.SEAT_ROW_REAR);
                }
                getInteriorLightZonesResult.setVehicleAreaList(arrayList);
                RLog.i(str, "getInteriorLightZones: OK  result = " + getInteriorLightZonesResult.toJsonString());
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getInteriorLightZones】,result【OK】");
                iResponseSender.send(new Response("OK", getInteriorLightZonesResult));
                return;
            }
            RLog.i(str, "getInteriorLightZones: UNSUPPORTED");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getInteriorLightZones】,result【UNSUPPORTED】");
            iResponseSender.send(new Response("UNSUPPORTED"));
        } catch (Exception unused) {
            RLog.i(TAG, "getInteriorLightZones: ERROR");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomVehicleControlDomain】,api【getInteriorLightZones】,result【ERROR】");
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
