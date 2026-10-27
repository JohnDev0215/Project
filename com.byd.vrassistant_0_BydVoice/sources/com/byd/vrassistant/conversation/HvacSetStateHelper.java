package com.byd.vrassistant.conversation;

import android.app.UiStyleConfig;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.pm2p5.BYDAutoPM2p5Device;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.util.Log;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.controller.domain.hvac.HVACRequestsEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.TagUtil;
import com.byd.vrassistant.utils.TtsUtils;

/* JADX INFO: loaded from: classes.dex */
public class HvacSetStateHelper {
    private static final String TAG = TagUtil.getTagName(HvacSetStateHelper.class);
    private BYDAutoAcDevice acDevice;
    private BYDAutoStudy autoStudy;
    private BYDAutoInstrumentDevice instrumentDevice;
    private BYDAutoPM2p5Device pm2p5Device;
    private BYDAutoSettingDevice settingDevice;
    private UiStyleConfig uiStyleConfig;

    private static final class SingletonHolder {
        private static final HvacSetStateHelper INSTANCE = new HvacSetStateHelper();

        private SingletonHolder() {
        }
    }

    public static HvacSetStateHelper getInstance() {
        RLog.i(TAG, "getInstance()");
        return SingletonHolder.INSTANCE;
    }

    private HvacSetStateHelper() {
        try {
            RLog.i(TAG, "HvacSetStateHelper()");
            this.acDevice = BYDAutoAcDevice.getInstance(BydVoiceApplication.getContext());
            this.settingDevice = BYDAutoSettingDevice.getInstance(BydVoiceApplication.getContext());
            this.instrumentDevice = BYDAutoInstrumentDevice.getInstance(BydVoiceApplication.getContext());
            this.uiStyleConfig = UiStyleConfig.getInstance(BydVoiceApplication.getContext());
            this.autoStudy = BYDAutoStudy.getInstance(BydVoiceApplication.getContext());
            this.pm2p5Device = BYDAutoPM2p5Device.getInstance(BydVoiceApplication.getContext());
        } catch (Exception e) {
            RLog.i(TAG, "HvacSetStateHelper Exception ", e);
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(16:326|(6:(20:328|336|337|325|(5:339|340|341|342|(15:347|350|(3:1757|352|(12:357|(13:362|363|(1:368)|371|1755|372|373|1741|374|(9:376|(1:378)|386|(3:388|1749|(6:390|391|1751|392|393|394)(3:396|397|398))(1:399)|400|(2:402|(1:404)(1:406))|(2:408|(1:410)(1:412))|413|887)(1:379)|380|(1:414)(6:386|(0)(0)|400|(0)|(0)|413)|887)(1:369)|370|371|1755|372|373|1741|374|(0)(0)|380|(3:383|414|887)(3:383|414|887))(1:356))(1:359)|360|(0)(0)|370|371|1755|372|373|1741|374|(0)(0)|380|(0)(0))(1:346))(1:348)|349|350|(0)(0)|360|(0)(0)|370|371|1755|372|373|1741|374|(0)(0)|380|(0)(0))|1741|374|(0)(0)|380|(0)(0))|334|337|325|(0)(0)|349|350|(0)(0)|360|(0)(0)|370|371|1755|372|373) */
    /* JADX WARN: Code restructure failed: missing block: B:330:0x0ac1, code lost:
    
        if (com.byd.vrassistant.utils.VehicleAndHvacUtil.isLeftRudder() != false) goto L335;
     */
    /* JADX WARN: Code restructure failed: missing block: B:333:0x0ace, code lost:
    
        if (com.byd.vrassistant.utils.VehicleAndHvacUtil.isLeftRudder() != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:335:0x0ad3, code lost:
    
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:416:0x0d72, code lost:
    
        r1 = r3;
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
    /* JADX WARN: Removed duplicated region for block: B:1211:0x2656 A[Catch: Exception -> 0x31c3, TryCatch #36 {Exception -> 0x31c3, blocks: (B:1196:0x25ce, B:1198:0x25fc, B:1200:0x260b, B:1206:0x262f, B:1207:0x2635, B:1212:0x265e, B:1213:0x26ae, B:1243:0x271b, B:1245:0x2721, B:1252:0x2735, B:1254:0x273e, B:1256:0x274d, B:1257:0x2770, B:1260:0x2795, B:1261:0x27a5, B:1263:0x27af, B:1265:0x27be, B:1266:0x27db, B:1273:0x27f9, B:1275:0x2801, B:1277:0x2810, B:1278:0x282f, B:1281:0x2844, B:1282:0x2854, B:1284:0x285e, B:1286:0x286d, B:1287:0x288a, B:1291:0x28ac, B:1298:0x28b9, B:1300:0x28bf, B:1302:0x28c7, B:1304:0x28d6, B:1310:0x2911, B:1305:0x28e8, B:1307:0x28f2, B:1309:0x2901, B:1311:0x291e, B:1314:0x2931, B:1315:0x2941, B:1317:0x2947, B:1319:0x2951, B:1321:0x2960, B:1327:0x299a, B:1322:0x2972, B:1324:0x297b, B:1326:0x298a, B:1328:0x29a7, B:1332:0x29c9, B:1339:0x29d6, B:1341:0x29dc, B:1343:0x29e5, B:1345:0x29f4, B:1351:0x2a2f, B:1346:0x2a06, B:1348:0x2a10, B:1350:0x2a1f, B:1352:0x2a3c, B:1355:0x2a4f, B:1356:0x2a5f, B:1358:0x2a65, B:1360:0x2a6e, B:1362:0x2a7d, B:1368:0x2ab8, B:1363:0x2a8f, B:1365:0x2a99, B:1367:0x2aa8, B:1369:0x2ac5, B:1382:0x2af5, B:1388:0x2b22, B:1389:0x2b75, B:1391:0x2b84, B:1215:0x26b3, B:1218:0x26be, B:1221:0x26c9, B:1224:0x26d4, B:1227:0x26df, B:1230:0x26eb, B:1233:0x26f5, B:1236:0x2701, B:1239:0x270d, B:1211:0x2656, B:1199:0x2605), top: B:1773:0x25ce }] */
    /* JADX WARN: Removed duplicated region for block: B:1214:0x26b1  */
    /* JADX WARN: Removed duplicated region for block: B:1215:0x26b3 A[Catch: Exception -> 0x31c3, TryCatch #36 {Exception -> 0x31c3, blocks: (B:1196:0x25ce, B:1198:0x25fc, B:1200:0x260b, B:1206:0x262f, B:1207:0x2635, B:1212:0x265e, B:1213:0x26ae, B:1243:0x271b, B:1245:0x2721, B:1252:0x2735, B:1254:0x273e, B:1256:0x274d, B:1257:0x2770, B:1260:0x2795, B:1261:0x27a5, B:1263:0x27af, B:1265:0x27be, B:1266:0x27db, B:1273:0x27f9, B:1275:0x2801, B:1277:0x2810, B:1278:0x282f, B:1281:0x2844, B:1282:0x2854, B:1284:0x285e, B:1286:0x286d, B:1287:0x288a, B:1291:0x28ac, B:1298:0x28b9, B:1300:0x28bf, B:1302:0x28c7, B:1304:0x28d6, B:1310:0x2911, B:1305:0x28e8, B:1307:0x28f2, B:1309:0x2901, B:1311:0x291e, B:1314:0x2931, B:1315:0x2941, B:1317:0x2947, B:1319:0x2951, B:1321:0x2960, B:1327:0x299a, B:1322:0x2972, B:1324:0x297b, B:1326:0x298a, B:1328:0x29a7, B:1332:0x29c9, B:1339:0x29d6, B:1341:0x29dc, B:1343:0x29e5, B:1345:0x29f4, B:1351:0x2a2f, B:1346:0x2a06, B:1348:0x2a10, B:1350:0x2a1f, B:1352:0x2a3c, B:1355:0x2a4f, B:1356:0x2a5f, B:1358:0x2a65, B:1360:0x2a6e, B:1362:0x2a7d, B:1368:0x2ab8, B:1363:0x2a8f, B:1365:0x2a99, B:1367:0x2aa8, B:1369:0x2ac5, B:1382:0x2af5, B:1388:0x2b22, B:1389:0x2b75, B:1391:0x2b84, B:1215:0x26b3, B:1218:0x26be, B:1221:0x26c9, B:1224:0x26d4, B:1227:0x26df, B:1230:0x26eb, B:1233:0x26f5, B:1236:0x2701, B:1239:0x270d, B:1211:0x2656, B:1199:0x2605), top: B:1773:0x25ce }] */
    /* JADX WARN: Removed duplicated region for block: B:1218:0x26be A[Catch: Exception -> 0x31c3, TryCatch #36 {Exception -> 0x31c3, blocks: (B:1196:0x25ce, B:1198:0x25fc, B:1200:0x260b, B:1206:0x262f, B:1207:0x2635, B:1212:0x265e, B:1213:0x26ae, B:1243:0x271b, B:1245:0x2721, B:1252:0x2735, B:1254:0x273e, B:1256:0x274d, B:1257:0x2770, B:1260:0x2795, B:1261:0x27a5, B:1263:0x27af, B:1265:0x27be, B:1266:0x27db, B:1273:0x27f9, B:1275:0x2801, B:1277:0x2810, B:1278:0x282f, B:1281:0x2844, B:1282:0x2854, B:1284:0x285e, B:1286:0x286d, B:1287:0x288a, B:1291:0x28ac, B:1298:0x28b9, B:1300:0x28bf, B:1302:0x28c7, B:1304:0x28d6, B:1310:0x2911, B:1305:0x28e8, B:1307:0x28f2, B:1309:0x2901, B:1311:0x291e, B:1314:0x2931, B:1315:0x2941, B:1317:0x2947, B:1319:0x2951, B:1321:0x2960, B:1327:0x299a, B:1322:0x2972, B:1324:0x297b, B:1326:0x298a, B:1328:0x29a7, B:1332:0x29c9, B:1339:0x29d6, B:1341:0x29dc, B:1343:0x29e5, B:1345:0x29f4, B:1351:0x2a2f, B:1346:0x2a06, B:1348:0x2a10, B:1350:0x2a1f, B:1352:0x2a3c, B:1355:0x2a4f, B:1356:0x2a5f, B:1358:0x2a65, B:1360:0x2a6e, B:1362:0x2a7d, B:1368:0x2ab8, B:1363:0x2a8f, B:1365:0x2a99, B:1367:0x2aa8, B:1369:0x2ac5, B:1382:0x2af5, B:1388:0x2b22, B:1389:0x2b75, B:1391:0x2b84, B:1215:0x26b3, B:1218:0x26be, B:1221:0x26c9, B:1224:0x26d4, B:1227:0x26df, B:1230:0x26eb, B:1233:0x26f5, B:1236:0x2701, B:1239:0x270d, B:1211:0x2656, B:1199:0x2605), top: B:1773:0x25ce }] */
    /* JADX WARN: Removed duplicated region for block: B:1221:0x26c9 A[Catch: Exception -> 0x31c3, TryCatch #36 {Exception -> 0x31c3, blocks: (B:1196:0x25ce, B:1198:0x25fc, B:1200:0x260b, B:1206:0x262f, B:1207:0x2635, B:1212:0x265e, B:1213:0x26ae, B:1243:0x271b, B:1245:0x2721, B:1252:0x2735, B:1254:0x273e, B:1256:0x274d, B:1257:0x2770, B:1260:0x2795, B:1261:0x27a5, B:1263:0x27af, B:1265:0x27be, B:1266:0x27db, B:1273:0x27f9, B:1275:0x2801, B:1277:0x2810, B:1278:0x282f, B:1281:0x2844, B:1282:0x2854, B:1284:0x285e, B:1286:0x286d, B:1287:0x288a, B:1291:0x28ac, B:1298:0x28b9, B:1300:0x28bf, B:1302:0x28c7, B:1304:0x28d6, B:1310:0x2911, B:1305:0x28e8, B:1307:0x28f2, B:1309:0x2901, B:1311:0x291e, B:1314:0x2931, B:1315:0x2941, B:1317:0x2947, B:1319:0x2951, B:1321:0x2960, B:1327:0x299a, B:1322:0x2972, B:1324:0x297b, B:1326:0x298a, B:1328:0x29a7, B:1332:0x29c9, B:1339:0x29d6, B:1341:0x29dc, B:1343:0x29e5, B:1345:0x29f4, B:1351:0x2a2f, B:1346:0x2a06, B:1348:0x2a10, B:1350:0x2a1f, B:1352:0x2a3c, B:1355:0x2a4f, B:1356:0x2a5f, B:1358:0x2a65, B:1360:0x2a6e, B:1362:0x2a7d, B:1368:0x2ab8, B:1363:0x2a8f, B:1365:0x2a99, B:1367:0x2aa8, B:1369:0x2ac5, B:1382:0x2af5, B:1388:0x2b22, B:1389:0x2b75, B:1391:0x2b84, B:1215:0x26b3, B:1218:0x26be, B:1221:0x26c9, B:1224:0x26d4, B:1227:0x26df, B:1230:0x26eb, B:1233:0x26f5, B:1236:0x2701, B:1239:0x270d, B:1211:0x2656, B:1199:0x2605), top: B:1773:0x25ce }] */
    /* JADX WARN: Removed duplicated region for block: B:1224:0x26d4 A[Catch: Exception -> 0x31c3, TryCatch #36 {Exception -> 0x31c3, blocks: (B:1196:0x25ce, B:1198:0x25fc, B:1200:0x260b, B:1206:0x262f, B:1207:0x2635, B:1212:0x265e, B:1213:0x26ae, B:1243:0x271b, B:1245:0x2721, B:1252:0x2735, B:1254:0x273e, B:1256:0x274d, B:1257:0x2770, B:1260:0x2795, B:1261:0x27a5, B:1263:0x27af, B:1265:0x27be, B:1266:0x27db, B:1273:0x27f9, B:1275:0x2801, B:1277:0x2810, B:1278:0x282f, B:1281:0x2844, B:1282:0x2854, B:1284:0x285e, B:1286:0x286d, B:1287:0x288a, B:1291:0x28ac, B:1298:0x28b9, B:1300:0x28bf, B:1302:0x28c7, B:1304:0x28d6, B:1310:0x2911, B:1305:0x28e8, B:1307:0x28f2, B:1309:0x2901, B:1311:0x291e, B:1314:0x2931, B:1315:0x2941, B:1317:0x2947, B:1319:0x2951, B:1321:0x2960, B:1327:0x299a, B:1322:0x2972, B:1324:0x297b, B:1326:0x298a, B:1328:0x29a7, B:1332:0x29c9, B:1339:0x29d6, B:1341:0x29dc, B:1343:0x29e5, B:1345:0x29f4, B:1351:0x2a2f, B:1346:0x2a06, B:1348:0x2a10, B:1350:0x2a1f, B:1352:0x2a3c, B:1355:0x2a4f, B:1356:0x2a5f, B:1358:0x2a65, B:1360:0x2a6e, B:1362:0x2a7d, B:1368:0x2ab8, B:1363:0x2a8f, B:1365:0x2a99, B:1367:0x2aa8, B:1369:0x2ac5, B:1382:0x2af5, B:1388:0x2b22, B:1389:0x2b75, B:1391:0x2b84, B:1215:0x26b3, B:1218:0x26be, B:1221:0x26c9, B:1224:0x26d4, B:1227:0x26df, B:1230:0x26eb, B:1233:0x26f5, B:1236:0x2701, B:1239:0x270d, B:1211:0x2656, B:1199:0x2605), top: B:1773:0x25ce }] */
    /* JADX WARN: Removed duplicated region for block: B:1227:0x26df A[Catch: Exception -> 0x31c3, TryCatch #36 {Exception -> 0x31c3, blocks: (B:1196:0x25ce, B:1198:0x25fc, B:1200:0x260b, B:1206:0x262f, B:1207:0x2635, B:1212:0x265e, B:1213:0x26ae, B:1243:0x271b, B:1245:0x2721, B:1252:0x2735, B:1254:0x273e, B:1256:0x274d, B:1257:0x2770, B:1260:0x2795, B:1261:0x27a5, B:1263:0x27af, B:1265:0x27be, B:1266:0x27db, B:1273:0x27f9, B:1275:0x2801, B:1277:0x2810, B:1278:0x282f, B:1281:0x2844, B:1282:0x2854, B:1284:0x285e, B:1286:0x286d, B:1287:0x288a, B:1291:0x28ac, B:1298:0x28b9, B:1300:0x28bf, B:1302:0x28c7, B:1304:0x28d6, B:1310:0x2911, B:1305:0x28e8, B:1307:0x28f2, B:1309:0x2901, B:1311:0x291e, B:1314:0x2931, B:1315:0x2941, B:1317:0x2947, B:1319:0x2951, B:1321:0x2960, B:1327:0x299a, B:1322:0x2972, B:1324:0x297b, B:1326:0x298a, B:1328:0x29a7, B:1332:0x29c9, B:1339:0x29d6, B:1341:0x29dc, B:1343:0x29e5, B:1345:0x29f4, B:1351:0x2a2f, B:1346:0x2a06, B:1348:0x2a10, B:1350:0x2a1f, B:1352:0x2a3c, B:1355:0x2a4f, B:1356:0x2a5f, B:1358:0x2a65, B:1360:0x2a6e, B:1362:0x2a7d, B:1368:0x2ab8, B:1363:0x2a8f, B:1365:0x2a99, B:1367:0x2aa8, B:1369:0x2ac5, B:1382:0x2af5, B:1388:0x2b22, B:1389:0x2b75, B:1391:0x2b84, B:1215:0x26b3, B:1218:0x26be, B:1221:0x26c9, B:1224:0x26d4, B:1227:0x26df, B:1230:0x26eb, B:1233:0x26f5, B:1236:0x2701, B:1239:0x270d, B:1211:0x2656, B:1199:0x2605), top: B:1773:0x25ce }] */
    /* JADX WARN: Removed duplicated region for block: B:1230:0x26eb A[Catch: Exception -> 0x31c3, TryCatch #36 {Exception -> 0x31c3, blocks: (B:1196:0x25ce, B:1198:0x25fc, B:1200:0x260b, B:1206:0x262f, B:1207:0x2635, B:1212:0x265e, B:1213:0x26ae, B:1243:0x271b, B:1245:0x2721, B:1252:0x2735, B:1254:0x273e, B:1256:0x274d, B:1257:0x2770, B:1260:0x2795, B:1261:0x27a5, B:1263:0x27af, B:1265:0x27be, B:1266:0x27db, B:1273:0x27f9, B:1275:0x2801, B:1277:0x2810, B:1278:0x282f, B:1281:0x2844, B:1282:0x2854, B:1284:0x285e, B:1286:0x286d, B:1287:0x288a, B:1291:0x28ac, B:1298:0x28b9, B:1300:0x28bf, B:1302:0x28c7, B:1304:0x28d6, B:1310:0x2911, B:1305:0x28e8, B:1307:0x28f2, B:1309:0x2901, B:1311:0x291e, B:1314:0x2931, B:1315:0x2941, B:1317:0x2947, B:1319:0x2951, B:1321:0x2960, B:1327:0x299a, B:1322:0x2972, B:1324:0x297b, B:1326:0x298a, B:1328:0x29a7, B:1332:0x29c9, B:1339:0x29d6, B:1341:0x29dc, B:1343:0x29e5, B:1345:0x29f4, B:1351:0x2a2f, B:1346:0x2a06, B:1348:0x2a10, B:1350:0x2a1f, B:1352:0x2a3c, B:1355:0x2a4f, B:1356:0x2a5f, B:1358:0x2a65, B:1360:0x2a6e, B:1362:0x2a7d, B:1368:0x2ab8, B:1363:0x2a8f, B:1365:0x2a99, B:1367:0x2aa8, B:1369:0x2ac5, B:1382:0x2af5, B:1388:0x2b22, B:1389:0x2b75, B:1391:0x2b84, B:1215:0x26b3, B:1218:0x26be, B:1221:0x26c9, B:1224:0x26d4, B:1227:0x26df, B:1230:0x26eb, B:1233:0x26f5, B:1236:0x2701, B:1239:0x270d, B:1211:0x2656, B:1199:0x2605), top: B:1773:0x25ce }] */
    /* JADX WARN: Removed duplicated region for block: B:1233:0x26f5 A[Catch: Exception -> 0x31c3, TryCatch #36 {Exception -> 0x31c3, blocks: (B:1196:0x25ce, B:1198:0x25fc, B:1200:0x260b, B:1206:0x262f, B:1207:0x2635, B:1212:0x265e, B:1213:0x26ae, B:1243:0x271b, B:1245:0x2721, B:1252:0x2735, B:1254:0x273e, B:1256:0x274d, B:1257:0x2770, B:1260:0x2795, B:1261:0x27a5, B:1263:0x27af, B:1265:0x27be, B:1266:0x27db, B:1273:0x27f9, B:1275:0x2801, B:1277:0x2810, B:1278:0x282f, B:1281:0x2844, B:1282:0x2854, B:1284:0x285e, B:1286:0x286d, B:1287:0x288a, B:1291:0x28ac, B:1298:0x28b9, B:1300:0x28bf, B:1302:0x28c7, B:1304:0x28d6, B:1310:0x2911, B:1305:0x28e8, B:1307:0x28f2, B:1309:0x2901, B:1311:0x291e, B:1314:0x2931, B:1315:0x2941, B:1317:0x2947, B:1319:0x2951, B:1321:0x2960, B:1327:0x299a, B:1322:0x2972, B:1324:0x297b, B:1326:0x298a, B:1328:0x29a7, B:1332:0x29c9, B:1339:0x29d6, B:1341:0x29dc, B:1343:0x29e5, B:1345:0x29f4, B:1351:0x2a2f, B:1346:0x2a06, B:1348:0x2a10, B:1350:0x2a1f, B:1352:0x2a3c, B:1355:0x2a4f, B:1356:0x2a5f, B:1358:0x2a65, B:1360:0x2a6e, B:1362:0x2a7d, B:1368:0x2ab8, B:1363:0x2a8f, B:1365:0x2a99, B:1367:0x2aa8, B:1369:0x2ac5, B:1382:0x2af5, B:1388:0x2b22, B:1389:0x2b75, B:1391:0x2b84, B:1215:0x26b3, B:1218:0x26be, B:1221:0x26c9, B:1224:0x26d4, B:1227:0x26df, B:1230:0x26eb, B:1233:0x26f5, B:1236:0x2701, B:1239:0x270d, B:1211:0x2656, B:1199:0x2605), top: B:1773:0x25ce }] */
    /* JADX WARN: Removed duplicated region for block: B:1236:0x2701 A[Catch: Exception -> 0x31c3, TryCatch #36 {Exception -> 0x31c3, blocks: (B:1196:0x25ce, B:1198:0x25fc, B:1200:0x260b, B:1206:0x262f, B:1207:0x2635, B:1212:0x265e, B:1213:0x26ae, B:1243:0x271b, B:1245:0x2721, B:1252:0x2735, B:1254:0x273e, B:1256:0x274d, B:1257:0x2770, B:1260:0x2795, B:1261:0x27a5, B:1263:0x27af, B:1265:0x27be, B:1266:0x27db, B:1273:0x27f9, B:1275:0x2801, B:1277:0x2810, B:1278:0x282f, B:1281:0x2844, B:1282:0x2854, B:1284:0x285e, B:1286:0x286d, B:1287:0x288a, B:1291:0x28ac, B:1298:0x28b9, B:1300:0x28bf, B:1302:0x28c7, B:1304:0x28d6, B:1310:0x2911, B:1305:0x28e8, B:1307:0x28f2, B:1309:0x2901, B:1311:0x291e, B:1314:0x2931, B:1315:0x2941, B:1317:0x2947, B:1319:0x2951, B:1321:0x2960, B:1327:0x299a, B:1322:0x2972, B:1324:0x297b, B:1326:0x298a, B:1328:0x29a7, B:1332:0x29c9, B:1339:0x29d6, B:1341:0x29dc, B:1343:0x29e5, B:1345:0x29f4, B:1351:0x2a2f, B:1346:0x2a06, B:1348:0x2a10, B:1350:0x2a1f, B:1352:0x2a3c, B:1355:0x2a4f, B:1356:0x2a5f, B:1358:0x2a65, B:1360:0x2a6e, B:1362:0x2a7d, B:1368:0x2ab8, B:1363:0x2a8f, B:1365:0x2a99, B:1367:0x2aa8, B:1369:0x2ac5, B:1382:0x2af5, B:1388:0x2b22, B:1389:0x2b75, B:1391:0x2b84, B:1215:0x26b3, B:1218:0x26be, B:1221:0x26c9, B:1224:0x26d4, B:1227:0x26df, B:1230:0x26eb, B:1233:0x26f5, B:1236:0x2701, B:1239:0x270d, B:1211:0x2656, B:1199:0x2605), top: B:1773:0x25ce }] */
    /* JADX WARN: Removed duplicated region for block: B:1239:0x270d A[Catch: Exception -> 0x31c3, TryCatch #36 {Exception -> 0x31c3, blocks: (B:1196:0x25ce, B:1198:0x25fc, B:1200:0x260b, B:1206:0x262f, B:1207:0x2635, B:1212:0x265e, B:1213:0x26ae, B:1243:0x271b, B:1245:0x2721, B:1252:0x2735, B:1254:0x273e, B:1256:0x274d, B:1257:0x2770, B:1260:0x2795, B:1261:0x27a5, B:1263:0x27af, B:1265:0x27be, B:1266:0x27db, B:1273:0x27f9, B:1275:0x2801, B:1277:0x2810, B:1278:0x282f, B:1281:0x2844, B:1282:0x2854, B:1284:0x285e, B:1286:0x286d, B:1287:0x288a, B:1291:0x28ac, B:1298:0x28b9, B:1300:0x28bf, B:1302:0x28c7, B:1304:0x28d6, B:1310:0x2911, B:1305:0x28e8, B:1307:0x28f2, B:1309:0x2901, B:1311:0x291e, B:1314:0x2931, B:1315:0x2941, B:1317:0x2947, B:1319:0x2951, B:1321:0x2960, B:1327:0x299a, B:1322:0x2972, B:1324:0x297b, B:1326:0x298a, B:1328:0x29a7, B:1332:0x29c9, B:1339:0x29d6, B:1341:0x29dc, B:1343:0x29e5, B:1345:0x29f4, B:1351:0x2a2f, B:1346:0x2a06, B:1348:0x2a10, B:1350:0x2a1f, B:1352:0x2a3c, B:1355:0x2a4f, B:1356:0x2a5f, B:1358:0x2a65, B:1360:0x2a6e, B:1362:0x2a7d, B:1368:0x2ab8, B:1363:0x2a8f, B:1365:0x2a99, B:1367:0x2aa8, B:1369:0x2ac5, B:1382:0x2af5, B:1388:0x2b22, B:1389:0x2b75, B:1391:0x2b84, B:1215:0x26b3, B:1218:0x26be, B:1221:0x26c9, B:1224:0x26d4, B:1227:0x26df, B:1230:0x26eb, B:1233:0x26f5, B:1236:0x2701, B:1239:0x270d, B:1211:0x2656, B:1199:0x2605), top: B:1773:0x25ce }] */
    /* JADX WARN: Removed duplicated region for block: B:1242:0x2719  */
    /* JADX WARN: Removed duplicated region for block: B:1244:0x271e  */
    /* JADX WARN: Removed duplicated region for block: B:1245:0x2721 A[Catch: Exception -> 0x31c3, TryCatch #36 {Exception -> 0x31c3, blocks: (B:1196:0x25ce, B:1198:0x25fc, B:1200:0x260b, B:1206:0x262f, B:1207:0x2635, B:1212:0x265e, B:1213:0x26ae, B:1243:0x271b, B:1245:0x2721, B:1252:0x2735, B:1254:0x273e, B:1256:0x274d, B:1257:0x2770, B:1260:0x2795, B:1261:0x27a5, B:1263:0x27af, B:1265:0x27be, B:1266:0x27db, B:1273:0x27f9, B:1275:0x2801, B:1277:0x2810, B:1278:0x282f, B:1281:0x2844, B:1282:0x2854, B:1284:0x285e, B:1286:0x286d, B:1287:0x288a, B:1291:0x28ac, B:1298:0x28b9, B:1300:0x28bf, B:1302:0x28c7, B:1304:0x28d6, B:1310:0x2911, B:1305:0x28e8, B:1307:0x28f2, B:1309:0x2901, B:1311:0x291e, B:1314:0x2931, B:1315:0x2941, B:1317:0x2947, B:1319:0x2951, B:1321:0x2960, B:1327:0x299a, B:1322:0x2972, B:1324:0x297b, B:1326:0x298a, B:1328:0x29a7, B:1332:0x29c9, B:1339:0x29d6, B:1341:0x29dc, B:1343:0x29e5, B:1345:0x29f4, B:1351:0x2a2f, B:1346:0x2a06, B:1348:0x2a10, B:1350:0x2a1f, B:1352:0x2a3c, B:1355:0x2a4f, B:1356:0x2a5f, B:1358:0x2a65, B:1360:0x2a6e, B:1362:0x2a7d, B:1368:0x2ab8, B:1363:0x2a8f, B:1365:0x2a99, B:1367:0x2aa8, B:1369:0x2ac5, B:1382:0x2af5, B:1388:0x2b22, B:1389:0x2b75, B:1391:0x2b84, B:1215:0x26b3, B:1218:0x26be, B:1221:0x26c9, B:1224:0x26d4, B:1227:0x26df, B:1230:0x26eb, B:1233:0x26f5, B:1236:0x2701, B:1239:0x270d, B:1211:0x2656, B:1199:0x2605), top: B:1773:0x25ce }] */
    /* JADX WARN: Removed duplicated region for block: B:1266:0x27db A[Catch: Exception -> 0x31c3, TryCatch #36 {Exception -> 0x31c3, blocks: (B:1196:0x25ce, B:1198:0x25fc, B:1200:0x260b, B:1206:0x262f, B:1207:0x2635, B:1212:0x265e, B:1213:0x26ae, B:1243:0x271b, B:1245:0x2721, B:1252:0x2735, B:1254:0x273e, B:1256:0x274d, B:1257:0x2770, B:1260:0x2795, B:1261:0x27a5, B:1263:0x27af, B:1265:0x27be, B:1266:0x27db, B:1273:0x27f9, B:1275:0x2801, B:1277:0x2810, B:1278:0x282f, B:1281:0x2844, B:1282:0x2854, B:1284:0x285e, B:1286:0x286d, B:1287:0x288a, B:1291:0x28ac, B:1298:0x28b9, B:1300:0x28bf, B:1302:0x28c7, B:1304:0x28d6, B:1310:0x2911, B:1305:0x28e8, B:1307:0x28f2, B:1309:0x2901, B:1311:0x291e, B:1314:0x2931, B:1315:0x2941, B:1317:0x2947, B:1319:0x2951, B:1321:0x2960, B:1327:0x299a, B:1322:0x2972, B:1324:0x297b, B:1326:0x298a, B:1328:0x29a7, B:1332:0x29c9, B:1339:0x29d6, B:1341:0x29dc, B:1343:0x29e5, B:1345:0x29f4, B:1351:0x2a2f, B:1346:0x2a06, B:1348:0x2a10, B:1350:0x2a1f, B:1352:0x2a3c, B:1355:0x2a4f, B:1356:0x2a5f, B:1358:0x2a65, B:1360:0x2a6e, B:1362:0x2a7d, B:1368:0x2ab8, B:1363:0x2a8f, B:1365:0x2a99, B:1367:0x2aa8, B:1369:0x2ac5, B:1382:0x2af5, B:1388:0x2b22, B:1389:0x2b75, B:1391:0x2b84, B:1215:0x26b3, B:1218:0x26be, B:1221:0x26c9, B:1224:0x26d4, B:1227:0x26df, B:1230:0x26eb, B:1233:0x26f5, B:1236:0x2701, B:1239:0x270d, B:1211:0x2656, B:1199:0x2605), top: B:1773:0x25ce }] */
    /* JADX WARN: Removed duplicated region for block: B:1287:0x288a A[Catch: Exception -> 0x31c3, TryCatch #36 {Exception -> 0x31c3, blocks: (B:1196:0x25ce, B:1198:0x25fc, B:1200:0x260b, B:1206:0x262f, B:1207:0x2635, B:1212:0x265e, B:1213:0x26ae, B:1243:0x271b, B:1245:0x2721, B:1252:0x2735, B:1254:0x273e, B:1256:0x274d, B:1257:0x2770, B:1260:0x2795, B:1261:0x27a5, B:1263:0x27af, B:1265:0x27be, B:1266:0x27db, B:1273:0x27f9, B:1275:0x2801, B:1277:0x2810, B:1278:0x282f, B:1281:0x2844, B:1282:0x2854, B:1284:0x285e, B:1286:0x286d, B:1287:0x288a, B:1291:0x28ac, B:1298:0x28b9, B:1300:0x28bf, B:1302:0x28c7, B:1304:0x28d6, B:1310:0x2911, B:1305:0x28e8, B:1307:0x28f2, B:1309:0x2901, B:1311:0x291e, B:1314:0x2931, B:1315:0x2941, B:1317:0x2947, B:1319:0x2951, B:1321:0x2960, B:1327:0x299a, B:1322:0x2972, B:1324:0x297b, B:1326:0x298a, B:1328:0x29a7, B:1332:0x29c9, B:1339:0x29d6, B:1341:0x29dc, B:1343:0x29e5, B:1345:0x29f4, B:1351:0x2a2f, B:1346:0x2a06, B:1348:0x2a10, B:1350:0x2a1f, B:1352:0x2a3c, B:1355:0x2a4f, B:1356:0x2a5f, B:1358:0x2a65, B:1360:0x2a6e, B:1362:0x2a7d, B:1368:0x2ab8, B:1363:0x2a8f, B:1365:0x2a99, B:1367:0x2aa8, B:1369:0x2ac5, B:1382:0x2af5, B:1388:0x2b22, B:1389:0x2b75, B:1391:0x2b84, B:1215:0x26b3, B:1218:0x26be, B:1221:0x26c9, B:1224:0x26d4, B:1227:0x26df, B:1230:0x26eb, B:1233:0x26f5, B:1236:0x2701, B:1239:0x270d, B:1211:0x2656, B:1199:0x2605), top: B:1773:0x25ce }] */
    /* JADX WARN: Removed duplicated region for block: B:1328:0x29a7 A[Catch: Exception -> 0x31c3, TryCatch #36 {Exception -> 0x31c3, blocks: (B:1196:0x25ce, B:1198:0x25fc, B:1200:0x260b, B:1206:0x262f, B:1207:0x2635, B:1212:0x265e, B:1213:0x26ae, B:1243:0x271b, B:1245:0x2721, B:1252:0x2735, B:1254:0x273e, B:1256:0x274d, B:1257:0x2770, B:1260:0x2795, B:1261:0x27a5, B:1263:0x27af, B:1265:0x27be, B:1266:0x27db, B:1273:0x27f9, B:1275:0x2801, B:1277:0x2810, B:1278:0x282f, B:1281:0x2844, B:1282:0x2854, B:1284:0x285e, B:1286:0x286d, B:1287:0x288a, B:1291:0x28ac, B:1298:0x28b9, B:1300:0x28bf, B:1302:0x28c7, B:1304:0x28d6, B:1310:0x2911, B:1305:0x28e8, B:1307:0x28f2, B:1309:0x2901, B:1311:0x291e, B:1314:0x2931, B:1315:0x2941, B:1317:0x2947, B:1319:0x2951, B:1321:0x2960, B:1327:0x299a, B:1322:0x2972, B:1324:0x297b, B:1326:0x298a, B:1328:0x29a7, B:1332:0x29c9, B:1339:0x29d6, B:1341:0x29dc, B:1343:0x29e5, B:1345:0x29f4, B:1351:0x2a2f, B:1346:0x2a06, B:1348:0x2a10, B:1350:0x2a1f, B:1352:0x2a3c, B:1355:0x2a4f, B:1356:0x2a5f, B:1358:0x2a65, B:1360:0x2a6e, B:1362:0x2a7d, B:1368:0x2ab8, B:1363:0x2a8f, B:1365:0x2a99, B:1367:0x2aa8, B:1369:0x2ac5, B:1382:0x2af5, B:1388:0x2b22, B:1389:0x2b75, B:1391:0x2b84, B:1215:0x26b3, B:1218:0x26be, B:1221:0x26c9, B:1224:0x26d4, B:1227:0x26df, B:1230:0x26eb, B:1233:0x26f5, B:1236:0x2701, B:1239:0x270d, B:1211:0x2656, B:1199:0x2605), top: B:1773:0x25ce }] */
    /* JADX WARN: Removed duplicated region for block: B:1369:0x2ac5 A[Catch: Exception -> 0x31c3, TryCatch #36 {Exception -> 0x31c3, blocks: (B:1196:0x25ce, B:1198:0x25fc, B:1200:0x260b, B:1206:0x262f, B:1207:0x2635, B:1212:0x265e, B:1213:0x26ae, B:1243:0x271b, B:1245:0x2721, B:1252:0x2735, B:1254:0x273e, B:1256:0x274d, B:1257:0x2770, B:1260:0x2795, B:1261:0x27a5, B:1263:0x27af, B:1265:0x27be, B:1266:0x27db, B:1273:0x27f9, B:1275:0x2801, B:1277:0x2810, B:1278:0x282f, B:1281:0x2844, B:1282:0x2854, B:1284:0x285e, B:1286:0x286d, B:1287:0x288a, B:1291:0x28ac, B:1298:0x28b9, B:1300:0x28bf, B:1302:0x28c7, B:1304:0x28d6, B:1310:0x2911, B:1305:0x28e8, B:1307:0x28f2, B:1309:0x2901, B:1311:0x291e, B:1314:0x2931, B:1315:0x2941, B:1317:0x2947, B:1319:0x2951, B:1321:0x2960, B:1327:0x299a, B:1322:0x2972, B:1324:0x297b, B:1326:0x298a, B:1328:0x29a7, B:1332:0x29c9, B:1339:0x29d6, B:1341:0x29dc, B:1343:0x29e5, B:1345:0x29f4, B:1351:0x2a2f, B:1346:0x2a06, B:1348:0x2a10, B:1350:0x2a1f, B:1352:0x2a3c, B:1355:0x2a4f, B:1356:0x2a5f, B:1358:0x2a65, B:1360:0x2a6e, B:1362:0x2a7d, B:1368:0x2ab8, B:1363:0x2a8f, B:1365:0x2a99, B:1367:0x2aa8, B:1369:0x2ac5, B:1382:0x2af5, B:1388:0x2b22, B:1389:0x2b75, B:1391:0x2b84, B:1215:0x26b3, B:1218:0x26be, B:1221:0x26c9, B:1224:0x26d4, B:1227:0x26df, B:1230:0x26eb, B:1233:0x26f5, B:1236:0x2701, B:1239:0x270d, B:1211:0x2656, B:1199:0x2605), top: B:1773:0x25ce }] */
    /* JADX WARN: Removed duplicated region for block: B:1416:0x2c59 A[Catch: Exception -> 0x25b2, TryCatch #13 {Exception -> 0x25b2, blocks: (B:1189:0x259a, B:1186:0x2590, B:1413:0x2c4e, B:1416:0x2c59, B:1420:0x2c7a, B:1428:0x2c8c, B:1430:0x2c92, B:1434:0x2ca8, B:1442:0x2d10, B:1435:0x2cca, B:1437:0x2cd9, B:1441:0x2cf0, B:1443:0x2d1d, B:1445:0x2d2c, B:1449:0x2d4b, B:1450:0x2d5b, B:1452:0x2d61, B:1456:0x2d78, B:1464:0x2de1, B:1457:0x2d9a, B:1459:0x2da9, B:1463:0x2dc1, B:1465:0x2df0), top: B:1740:0x029c }] */
    /* JADX WARN: Removed duplicated region for block: B:1467:0x2dff  */
    /* JADX WARN: Removed duplicated region for block: B:1519:0x2fa7 A[Catch: Exception -> 0x3538, TryCatch #5 {Exception -> 0x3538, blocks: (B:1580:0x31ab, B:1468:0x2e10, B:1472:0x2e21, B:1480:0x2e33, B:1482:0x2e39, B:1486:0x2e4f, B:1494:0x2eb8, B:1487:0x2e71, B:1489:0x2e80, B:1493:0x2e98, B:1495:0x2ec5, B:1497:0x2ed4, B:1501:0x2ef3, B:1502:0x2f03, B:1504:0x2f09, B:1508:0x2f21, B:1516:0x2f89, B:1509:0x2f43, B:1511:0x2f52, B:1515:0x2f69, B:1517:0x2f98, B:1519:0x2fa7, B:1527:0x2fcc, B:1531:0x2fe2, B:1532:0x300f, B:1534:0x301e, B:1547:0x30a0, B:1549:0x30a7, B:1558:0x30d4, B:1562:0x30eb, B:1563:0x3118, B:1565:0x3127, B:1578:0x31a4, B:1582:0x31c7), top: B:1740:0x029c }] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x05ba A[Catch: Exception -> 0x0912, TryCatch #27 {Exception -> 0x0912, blocks: (B:103:0x03f5, B:105:0x03fc, B:106:0x0410, B:110:0x043b, B:112:0x044a, B:118:0x046b, B:120:0x0473, B:122:0x0488, B:115:0x0455, B:117:0x0464, B:123:0x04aa, B:127:0x04d5, B:129:0x04e4, B:135:0x0505, B:137:0x051f, B:139:0x0534, B:132:0x04ef, B:134:0x04fe, B:140:0x0544, B:144:0x056f, B:151:0x05a0, B:153:0x05ba, B:155:0x05cf, B:145:0x057e, B:148:0x0589, B:150:0x0599, B:156:0x05df, B:157:0x0625, B:187:0x068d, B:189:0x0696, B:194:0x06a7, B:195:0x06b7, B:159:0x062a, B:162:0x0635, B:165:0x0640, B:168:0x0649, B:171:0x0654, B:174:0x065f, B:177:0x066a, B:180:0x0675, B:183:0x0680, B:341:0x0ae6), top: B:1740:0x029c }] */
    /* JADX WARN: Removed duplicated region for block: B:1549:0x30a7 A[Catch: Exception -> 0x3538, TryCatch #5 {Exception -> 0x3538, blocks: (B:1580:0x31ab, B:1468:0x2e10, B:1472:0x2e21, B:1480:0x2e33, B:1482:0x2e39, B:1486:0x2e4f, B:1494:0x2eb8, B:1487:0x2e71, B:1489:0x2e80, B:1493:0x2e98, B:1495:0x2ec5, B:1497:0x2ed4, B:1501:0x2ef3, B:1502:0x2f03, B:1504:0x2f09, B:1508:0x2f21, B:1516:0x2f89, B:1509:0x2f43, B:1511:0x2f52, B:1515:0x2f69, B:1517:0x2f98, B:1519:0x2fa7, B:1527:0x2fcc, B:1531:0x2fe2, B:1532:0x300f, B:1534:0x301e, B:1547:0x30a0, B:1549:0x30a7, B:1558:0x30d4, B:1562:0x30eb, B:1563:0x3118, B:1565:0x3127, B:1578:0x31a4, B:1582:0x31c7), top: B:1740:0x029c }] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x05cf A[Catch: Exception -> 0x0912, TryCatch #27 {Exception -> 0x0912, blocks: (B:103:0x03f5, B:105:0x03fc, B:106:0x0410, B:110:0x043b, B:112:0x044a, B:118:0x046b, B:120:0x0473, B:122:0x0488, B:115:0x0455, B:117:0x0464, B:123:0x04aa, B:127:0x04d5, B:129:0x04e4, B:135:0x0505, B:137:0x051f, B:139:0x0534, B:132:0x04ef, B:134:0x04fe, B:140:0x0544, B:144:0x056f, B:151:0x05a0, B:153:0x05ba, B:155:0x05cf, B:145:0x057e, B:148:0x0589, B:150:0x0599, B:156:0x05df, B:157:0x0625, B:187:0x068d, B:189:0x0696, B:194:0x06a7, B:195:0x06b7, B:159:0x062a, B:162:0x0635, B:165:0x0640, B:168:0x0649, B:171:0x0654, B:174:0x065f, B:177:0x066a, B:180:0x0675, B:183:0x0680, B:341:0x0ae6), top: B:1740:0x029c }] */
    /* JADX WARN: Removed duplicated region for block: B:1625:0x32a9  */
    /* JADX WARN: Removed duplicated region for block: B:1757:0x0b0b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:186:0x068b A[PHI: r4
  0x068b: PHI (r4v373 ??) = 
  (r4v404 ??)
  (r4v405 ??)
  (r4v406 ??)
  (r4v407 ??)
  (r4v408 ??)
  (r4v409 ??)
  (r4v410 ??)
  (r4v411 ??)
  (r4v412 ??)
  (r4v413 ??)
 binds: [B:184:0x0686, B:181:0x067b, B:178:0x0670, B:175:0x0665, B:172:0x065a, B:169:0x064f, B:166:0x0646, B:163:0x063b, B:160:0x0630, B:158:0x0628] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0ad9 A[Catch: Exception -> 0x09b5, TRY_ENTER, TRY_LEAVE, TryCatch #10 {Exception -> 0x09b5, blocks: (B:198:0x06d2, B:274:0x099f, B:276:0x09a7, B:279:0x09b0, B:286:0x09d1, B:314:0x0a34, B:316:0x0a41, B:339:0x0ad9, B:321:0x0a68, B:322:0x0a7c, B:323:0x0a89, B:326:0x0a94, B:329:0x0ab7, B:289:0x09dc, B:292:0x09e7, B:295:0x09f2, B:298:0x09fb, B:301:0x0a06, B:304:0x0a11, B:307:0x0a1c, B:310:0x0a27), top: B:1740:0x029c }] */
    /* JADX WARN: Removed duplicated region for block: B:348:0x0b00  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x0b3d  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x0b4b A[Catch: Exception -> 0x0b32, TRY_LEAVE, TryCatch #16 {Exception -> 0x0b32, blocks: (B:352:0x0b0b, B:362:0x0b4b), top: B:1757:0x0b0b }] */
    /* JADX WARN: Removed duplicated region for block: B:369:0x0b6e  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x0bf0  */
    /* JADX WARN: Removed duplicated region for block: B:379:0x0bf4  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x0bf9 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:383:0x0bfb  */
    /* JADX WARN: Removed duplicated region for block: B:388:0x0c0a A[Catch: Exception -> 0x0d6c, TryCatch #3 {Exception -> 0x0d6c, blocks: (B:374:0x0b7e, B:386:0x0c01, B:388:0x0c0a, B:390:0x0c30), top: B:1741:0x0b7e }] */
    /* JADX WARN: Removed duplicated region for block: B:399:0x0c5d  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x0c66 A[Catch: Exception -> 0x0df9, TryCatch #32 {Exception -> 0x0df9, blocks: (B:419:0x0d79, B:414:0x0d49, B:398:0x0c52, B:402:0x0c66, B:404:0x0c8c, B:406:0x0ca1, B:408:0x0cb1, B:410:0x0cd7, B:412:0x0cec, B:413:0x0cf6, B:422:0x0d96, B:426:0x0db2), top: B:1771:0x0931 }] */
    /* JADX WARN: Removed duplicated region for block: B:408:0x0cb1 A[Catch: Exception -> 0x0df9, TryCatch #32 {Exception -> 0x0df9, blocks: (B:419:0x0d79, B:414:0x0d49, B:398:0x0c52, B:402:0x0c66, B:404:0x0c8c, B:406:0x0ca1, B:408:0x0cb1, B:410:0x0cd7, B:412:0x0cec, B:413:0x0cf6, B:422:0x0d96, B:426:0x0db2), top: B:1771:0x0931 }] */
    /* JADX WARN: Removed duplicated region for block: B:535:0x10da  */
    /* JADX WARN: Removed duplicated region for block: B:654:0x141f  */
    /* JADX WARN: Removed duplicated region for block: B:814:0x1a1e  */
    /* JADX WARN: Removed duplicated region for block: B:816:0x1a21 A[Catch: Exception -> 0x25b5, TryCatch #8 {Exception -> 0x25b5, blocks: (B:798:0x199c, B:805:0x19d6, B:811:0x19fa, B:812:0x1a00, B:817:0x1a29, B:976:0x1e97, B:816:0x1a21, B:804:0x19d0), top: B:1747:0x199c }] */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.byd.vrassistant.conversation.HvacSetStateHelper] */
    /* JADX WARN: Type inference failed for: r0v432 */
    /* JADX WARN: Type inference failed for: r0v433, types: [com.byd.vrassistant.conversation.HvacSetStateHelper] */
    /* JADX WARN: Type inference failed for: r0v440 */
    /* JADX WARN: Type inference failed for: r0v444 */
    /* JADX WARN: Type inference failed for: r0v448 */
    /* JADX WARN: Type inference failed for: r10v12, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v18 */
    /* JADX WARN: Type inference failed for: r10v19 */
    /* JADX WARN: Type inference failed for: r10v20 */
    /* JADX WARN: Type inference failed for: r10v21 */
    /* JADX WARN: Type inference failed for: r10v22 */
    /* JADX WARN: Type inference failed for: r10v23 */
    /* JADX WARN: Type inference failed for: r10v24 */
    /* JADX WARN: Type inference failed for: r10v25 */
    /* JADX WARN: Type inference failed for: r10v26 */
    /* JADX WARN: Type inference failed for: r10v28, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v29, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v31 */
    /* JADX WARN: Type inference failed for: r10v33, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v34 */
    /* JADX WARN: Type inference failed for: r10v39 */
    /* JADX WARN: Type inference failed for: r10v42 */
    /* JADX WARN: Type inference failed for: r10v49 */
    /* JADX WARN: Type inference failed for: r10v50 */
    /* JADX WARN: Type inference failed for: r10v51 */
    /* JADX WARN: Type inference failed for: r10v52 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r14v64 */
    /* JADX WARN: Type inference failed for: r14v66, types: [int] */
    /* JADX WARN: Type inference failed for: r14v69 */
    /* JADX WARN: Type inference failed for: r14v82 */
    /* JADX WARN: Type inference failed for: r1v0, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v228 */
    /* JADX WARN: Type inference failed for: r1v229 */
    /* JADX WARN: Type inference failed for: r1v246 */
    /* JADX WARN: Type inference failed for: r1v247 */
    /* JADX WARN: Type inference failed for: r2v266, types: [int] */
    /* JADX WARN: Type inference failed for: r2v269, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r2v287 */
    /* JADX WARN: Type inference failed for: r2v427 */
    /* JADX WARN: Type inference failed for: r3v10, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r3v106 */
    /* JADX WARN: Type inference failed for: r3v107 */
    /* JADX WARN: Type inference failed for: r3v108 */
    /* JADX WARN: Type inference failed for: r3v109 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v110 */
    /* JADX WARN: Type inference failed for: r3v111 */
    /* JADX WARN: Type inference failed for: r3v112 */
    /* JADX WARN: Type inference failed for: r3v113 */
    /* JADX WARN: Type inference failed for: r3v114 */
    /* JADX WARN: Type inference failed for: r3v115 */
    /* JADX WARN: Type inference failed for: r3v116 */
    /* JADX WARN: Type inference failed for: r3v117 */
    /* JADX WARN: Type inference failed for: r3v118 */
    /* JADX WARN: Type inference failed for: r3v119 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v120 */
    /* JADX WARN: Type inference failed for: r3v121 */
    /* JADX WARN: Type inference failed for: r3v122 */
    /* JADX WARN: Type inference failed for: r3v123 */
    /* JADX WARN: Type inference failed for: r3v124 */
    /* JADX WARN: Type inference failed for: r3v125 */
    /* JADX WARN: Type inference failed for: r3v126 */
    /* JADX WARN: Type inference failed for: r3v127 */
    /* JADX WARN: Type inference failed for: r3v128 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v22, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r3v23, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r3v24, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r3v28, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference failed for: r3v30, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r3v34 */
    /* JADX WARN: Type inference failed for: r3v35 */
    /* JADX WARN: Type inference failed for: r3v36, types: [cerence.ark.assistant.api.IResponseSender] */
    /* JADX WARN: Type inference failed for: r3v37 */
    /* JADX WARN: Type inference failed for: r3v38 */
    /* JADX WARN: Type inference failed for: r3v39 */
    /* JADX WARN: Type inference failed for: r3v40 */
    /* JADX WARN: Type inference failed for: r3v41 */
    /* JADX WARN: Type inference failed for: r3v43 */
    /* JADX WARN: Type inference failed for: r3v52, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r3v55, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r3v64, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v112, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v116 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v198 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v21 */
    /* JADX WARN: Type inference failed for: r4v232 */
    /* JADX WARN: Type inference failed for: r4v24 */
    /* JADX WARN: Type inference failed for: r4v25 */
    /* JADX WARN: Type inference failed for: r4v264 */
    /* JADX WARN: Type inference failed for: r4v265 */
    /* JADX WARN: Type inference failed for: r4v28 */
    /* JADX WARN: Type inference failed for: r4v29 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v304 */
    /* JADX WARN: Type inference failed for: r4v307, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v308 */
    /* JADX WARN: Type inference failed for: r4v309 */
    /* JADX WARN: Type inference failed for: r4v315 */
    /* JADX WARN: Type inference failed for: r4v32 */
    /* JADX WARN: Type inference failed for: r4v325, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r4v33 */
    /* JADX WARN: Type inference failed for: r4v36 */
    /* JADX WARN: Type inference failed for: r4v361 */
    /* JADX WARN: Type inference failed for: r4v362 */
    /* JADX WARN: Type inference failed for: r4v37 */
    /* JADX WARN: Type inference failed for: r4v373 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v40 */
    /* JADX WARN: Type inference failed for: r4v402 */
    /* JADX WARN: Type inference failed for: r4v403 */
    /* JADX WARN: Type inference failed for: r4v404 */
    /* JADX WARN: Type inference failed for: r4v405 */
    /* JADX WARN: Type inference failed for: r4v406 */
    /* JADX WARN: Type inference failed for: r4v407 */
    /* JADX WARN: Type inference failed for: r4v408 */
    /* JADX WARN: Type inference failed for: r4v409 */
    /* JADX WARN: Type inference failed for: r4v41 */
    /* JADX WARN: Type inference failed for: r4v410 */
    /* JADX WARN: Type inference failed for: r4v411 */
    /* JADX WARN: Type inference failed for: r4v412 */
    /* JADX WARN: Type inference failed for: r4v413 */
    /* JADX WARN: Type inference failed for: r4v414 */
    /* JADX WARN: Type inference failed for: r4v415 */
    /* JADX WARN: Type inference failed for: r4v416 */
    /* JADX WARN: Type inference failed for: r4v417 */
    /* JADX WARN: Type inference failed for: r4v418 */
    /* JADX WARN: Type inference failed for: r4v419 */
    /* JADX WARN: Type inference failed for: r4v420 */
    /* JADX WARN: Type inference failed for: r4v421 */
    /* JADX WARN: Type inference failed for: r4v422 */
    /* JADX WARN: Type inference failed for: r4v423 */
    /* JADX WARN: Type inference failed for: r4v428 */
    /* JADX WARN: Type inference failed for: r4v429 */
    /* JADX WARN: Type inference failed for: r4v430 */
    /* JADX WARN: Type inference failed for: r4v431 */
    /* JADX WARN: Type inference failed for: r4v432 */
    /* JADX WARN: Type inference failed for: r4v433 */
    /* JADX WARN: Type inference failed for: r4v434 */
    /* JADX WARN: Type inference failed for: r4v435 */
    /* JADX WARN: Type inference failed for: r4v436 */
    /* JADX WARN: Type inference failed for: r4v437 */
    /* JADX WARN: Type inference failed for: r4v438 */
    /* JADX WARN: Type inference failed for: r4v439 */
    /* JADX WARN: Type inference failed for: r4v44 */
    /* JADX WARN: Type inference failed for: r4v440 */
    /* JADX WARN: Type inference failed for: r4v45 */
    /* JADX WARN: Type inference failed for: r4v48 */
    /* JADX WARN: Type inference failed for: r4v49 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v52 */
    /* JADX WARN: Type inference failed for: r4v53 */
    /* JADX WARN: Type inference failed for: r4v56 */
    /* JADX WARN: Type inference failed for: r4v57 */
    /* JADX WARN: Type inference failed for: r4v60 */
    /* JADX WARN: Type inference failed for: r4v61 */
    /* JADX WARN: Type inference failed for: r4v64 */
    /* JADX WARN: Type inference failed for: r4v65 */
    /* JADX WARN: Type inference failed for: r4v68 */
    /* JADX WARN: Type inference failed for: r4v69 */
    /* JADX WARN: Type inference failed for: r4v72 */
    /* JADX WARN: Type inference failed for: r4v73 */
    /* JADX WARN: Type inference failed for: r4v74 */
    /* JADX WARN: Type inference failed for: r4v75 */
    /* JADX WARN: Type inference failed for: r4v76 */
    /* JADX WARN: Type inference failed for: r4v77 */
    /* JADX WARN: Type inference failed for: r4v78 */
    /* JADX WARN: Type inference failed for: r4v79 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v80 */
    /* JADX WARN: Type inference failed for: r4v81 */
    /* JADX WARN: Type inference failed for: r4v82 */
    /* JADX WARN: Type inference failed for: r4v83 */
    /* JADX WARN: Type inference failed for: r4v84 */
    /* JADX WARN: Type inference failed for: r4v85 */
    /* JADX WARN: Type inference failed for: r4v86 */
    /* JADX WARN: Type inference failed for: r4v87 */
    /* JADX WARN: Type inference failed for: r4v88 */
    /* JADX WARN: Type inference failed for: r4v89 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r4v90 */
    /* JADX WARN: Type inference failed for: r59v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r59v1 */
    /* JADX WARN: Type inference failed for: r59v10 */
    /* JADX WARN: Type inference failed for: r59v11 */
    /* JADX WARN: Type inference failed for: r59v12 */
    /* JADX WARN: Type inference failed for: r59v13 */
    /* JADX WARN: Type inference failed for: r59v14 */
    /* JADX WARN: Type inference failed for: r59v15 */
    /* JADX WARN: Type inference failed for: r59v16 */
    /* JADX WARN: Type inference failed for: r59v17 */
    /* JADX WARN: Type inference failed for: r59v18 */
    /* JADX WARN: Type inference failed for: r59v2 */
    /* JADX WARN: Type inference failed for: r59v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r59v6 */
    /* JADX WARN: Type inference failed for: r59v8 */
    /* JADX WARN: Type inference failed for: r59v9 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v103 */
    /* JADX WARN: Type inference failed for: r7v109 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v174 */
    /* JADX WARN: Type inference failed for: r7v175 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v19 */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Type inference failed for: r7v201 */
    /* JADX WARN: Type inference failed for: r7v219 */
    /* JADX WARN: Type inference failed for: r7v234 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v49 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v8, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r7v94 */
    /* JADX WARN: Type inference failed for: r7v99 */
    /* JADX WARN: Type inference failed for: r8v209 */
    /* JADX WARN: Type inference failed for: r8v210 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v47 */
    /* JADX WARN: Type inference failed for: r8v48 */
    /* JADX WARN: Type inference failed for: r8v64, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r8v67, types: [int] */
    /* JADX WARN: Type inference failed for: r8v68, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v69, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v70, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v71, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v72, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v73, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v74, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v75, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v76 */
    /* JADX WARN: Type inference failed for: r8v94, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v105 */
    /* JADX WARN: Type inference failed for: r9v23 */
    /* JADX WARN: Type inference failed for: r9v24, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setHvacState(cerence.ark.assistant.controller.domain.hvac.param.SetHvacStateParam r103, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r104) {
        /*
            Method dump skipped, instruction units count: 14418
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.HvacSetStateHelper.setHvacState(cerence.ark.assistant.controller.domain.hvac.param.SetHvacStateParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    private void sleep300() {
        try {
            RLog.i(TAG, "sleep 300ms");
            Thread.sleep(300L);
        } catch (Exception e) {
            RLog.i(TAG, "sleep Exception ", e);
        }
    }

    private boolean isSingleTemperatureControl() {
        int acTemperatureControlMode = this.acDevice.getAcTemperatureControlMode();
        RLog.i(TAG, "isSync: acSync = " + acTemperatureControlMode);
        return (acTemperatureControlMode == 0 || acTemperatureControlMode == 1) ? false : true;
    }

    private void setAcHeatingKey(IResponseSender<BaseResult> iResponseSender, String str, String str2) {
        boolean acHeatingKey = BydSdkHelper.getInstance().getAcHeatingKey(BydVoiceApplication.getContext());
        boolean zEquals = "ON".equals(str);
        if (acHeatingKey == zEquals) {
            TtsUtils.tts(TAG, HVACRequestsEnum.SET_HVAC_STATE, iResponseSender, "ALREADY_EXPECTED");
            return;
        }
        boolean acHeatingKey2 = BydSdkHelper.getInstance().setAcHeatingKey(BydVoiceApplication.getContext(), zEquals);
        String str3 = TAG;
        Log.i(str3, "setSmartFan: isOk = " + acHeatingKey2);
        if (acHeatingKey2) {
            TtsUtils.tts(str3, HVACRequestsEnum.SET_HVAC_STATE, iResponseSender, "OK");
        } else {
            TtsUtils.tts(str3, HVACRequestsEnum.SET_HVAC_STATE, iResponseSender, ResultCodeEnum.ERROR);
        }
    }
}
