package com.ts.car.input;

import android.content.Context;
import android.view.KeyEvent;
import com.ts.car.core.ServiceProvider;
import java.lang.reflect.Field;
import java.util.HashMap;
import ts.car.service.input.HardkeyConstrants;
import ts.car.service.input.IHardKeyAdminManager;

/* JADX INFO: loaded from: classes.dex */
public class HardKeyManagerProxy {
    private static final String HARDKEY_CONSTRANTS_PLATFORM = "ts.car.service.input.HardkeyConstrants";
    private static final String KEYCODE_VR_FLAG = "KEYCODE_VR";
    private static final String KEYEVENT_EXT_PLATFORM = "ts.car.service.input.KeyEventExt";
    private static final String SCENE_CARPLAY_BG_FLAG = "SCENE_CARPLAY_BG";
    private static final String SCENE_ENG_MODE_BG_FLAG = "SCENE_ENG_MODE_BG";
    private static final String SCENE_UNKNOWN_FLAG = "SCENE_UNKNOWN";
    private static volatile HardKeyManagerProxy sInstance;
    private IHardKeyAdminManager hardKeyAdminManager;
    private Context mContext;
    private static final String TAG = HardKeyManagerProxy.class.getSimpleName();
    private static String COMMON_REFLECT_CLASS = null;
    public static HashMap<String, Integer> KEYCODE_MAP = new HashMap<>();
    public static HashMap<String, Integer> SCENE_MAP = new HashMap<>();
    public static int KEYCODE_VR = 0;
    public static int SCENE_UNKNOWN = 0;
    public static int SCENE_ENG_MODE_BG = 0;
    public static int SCENE_CARPLAY_BG = 0;

    private HardKeyManagerProxy() {
        loadHardkeyConstrantsDefine(HARDKEY_CONSTRANTS_PLATFORM);
    }

    public static synchronized HardKeyManagerProxy getInstance() {
        if (sInstance == null) {
            sInstance = new HardKeyManagerProxy();
        }
        return sInstance;
    }

    public void init(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.hardKeyAdminManager = (IHardKeyAdminManager) ServiceProvider.get(IHardKeyAdminManager.class, applicationContext);
    }

    private boolean loadHardkeyConstrantsDefine(String className) {
        LogUtils.log(TAG, "loadHardkeyConstrantsDefine: ");
        KEYCODE_MAP = HardkeyConstrants.KEYCODE_MAP;
        LogUtils.log(TAG, "KEYCODE_MAP[" + KEYCODE_MAP.size() + "]:" + KEYCODE_MAP.toString());
        SCENE_MAP = HardkeyConstrants.SCENE_MAP;
        LogUtils.log(TAG, "SCENE_MAP[" + SCENE_MAP.size() + "]:" + SCENE_MAP.toString());
        COMMON_REFLECT_CLASS = className;
        loadHardkeyConstrantsSceneDefine();
        loadKeyEventExtDefine();
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void loadHardkeyConstrantsSceneDefine() {
        /*
            r12 = this;
            java.lang.String r0 = com.ts.car.input.HardKeyManagerProxy.TAG
            java.lang.String r1 = "loadHardkeyConstrantsSceneDefine: "
            com.ts.car.input.LogUtils.log(r0, r1)
            java.lang.String r0 = com.ts.car.input.HardKeyManagerProxy.COMMON_REFLECT_CLASS     // Catch: java.lang.Throwable -> L7b
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.Throwable -> L7b
            java.lang.reflect.Field[] r1 = r0.getFields()     // Catch: java.lang.Throwable -> L7b
            int r2 = r1.length     // Catch: java.lang.Throwable -> L7b
            if (r2 > 0) goto L1c
            java.lang.String r2 = com.ts.car.input.HardKeyManagerProxy.TAG     // Catch: java.lang.Throwable -> L7b
            java.lang.String r3 = "HardkeyConstrantsScene field[] is null"
            com.ts.car.input.LogUtils.log(r2, r3)     // Catch: java.lang.Throwable -> L7b
            return
        L1c:
            int r2 = r1.length     // Catch: java.lang.Throwable -> L7b
            r3 = 0
            r4 = r3
        L1f:
            if (r4 >= r2) goto L7a
            r5 = r1[r4]     // Catch: java.lang.Throwable -> L7b
            java.lang.String r6 = r5.getName()     // Catch: java.lang.Throwable -> L7b
            r7 = -1
            int r8 = r6.hashCode()     // Catch: java.lang.Throwable -> L7b
            r9 = -2066689193(0xffffffff84d0d357, float:-4.909465E-36)
            r10 = 2
            r11 = 1
            if (r8 == r9) goto L52
            r9 = 229705357(0xdb1068d, float:1.0910044E-30)
            if (r8 == r9) goto L48
            r9 = 1866780847(0x6f44d0af, float:6.0911345E28)
            if (r8 == r9) goto L3e
        L3d:
            goto L5b
        L3e:
            java.lang.String r8 = "SCENE_CARPLAY_BG"
            boolean r8 = r6.equals(r8)     // Catch: java.lang.Throwable -> L7b
            if (r8 == 0) goto L3d
            r7 = r11
            goto L5b
        L48:
            java.lang.String r8 = "SCENE_ENG_MODE_BG"
            boolean r8 = r6.equals(r8)     // Catch: java.lang.Throwable -> L7b
            if (r8 == 0) goto L3d
            r7 = r10
            goto L5b
        L52:
            java.lang.String r8 = "SCENE_UNKNOWN"
            boolean r8 = r6.equals(r8)     // Catch: java.lang.Throwable -> L7b
            if (r8 == 0) goto L3d
            r7 = r3
        L5b:
            if (r7 == 0) goto L70
            if (r7 == r11) goto L69
            if (r7 == r10) goto L62
            goto L77
        L62:
            int r7 = r5.getInt(r0)     // Catch: java.lang.Throwable -> L7b
            com.ts.car.input.HardKeyManagerProxy.SCENE_ENG_MODE_BG = r7     // Catch: java.lang.Throwable -> L7b
            goto L77
        L69:
            int r7 = r5.getInt(r0)     // Catch: java.lang.Throwable -> L7b
            com.ts.car.input.HardKeyManagerProxy.SCENE_CARPLAY_BG = r7     // Catch: java.lang.Throwable -> L7b
            goto L77
        L70:
            int r7 = r5.getInt(r0)     // Catch: java.lang.Throwable -> L7b
            com.ts.car.input.HardKeyManagerProxy.SCENE_UNKNOWN = r7     // Catch: java.lang.Throwable -> L7b
        L77:
            int r4 = r4 + 1
            goto L1f
        L7a:
            goto L7f
        L7b:
            r0 = move-exception
            r0.printStackTrace()
        L7f:
            java.lang.String r0 = com.ts.car.input.HardKeyManagerProxy.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "SCENE_UNKNOWN: "
            r1.append(r2)
            int r2 = com.ts.car.input.HardKeyManagerProxy.SCENE_UNKNOWN
            r1.append(r2)
            java.lang.String r2 = " , SCENE_CARPLAY_BG: "
            r1.append(r2)
            int r2 = com.ts.car.input.HardKeyManagerProxy.SCENE_CARPLAY_BG
            r1.append(r2)
            java.lang.String r2 = " , SCENE_ENG_MODE_BG: "
            r1.append(r2)
            int r2 = com.ts.car.input.HardKeyManagerProxy.SCENE_ENG_MODE_BG
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.ts.car.input.LogUtils.log(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.car.input.HardKeyManagerProxy.loadHardkeyConstrantsSceneDefine():void");
    }

    private void loadKeyEventExtDefine() {
        LogUtils.log(TAG, "loadKeyEventExtDefine: ");
        try {
            Class<?> keyEventExt = Class.forName(KEYEVENT_EXT_PLATFORM);
            Field field = keyEventExt.getField(KEYCODE_VR_FLAG);
            KEYCODE_VR = field.getInt(keyEventExt);
            LogUtils.log(TAG, "KEYCODE_VR: " + KEYCODE_VR);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException e) {
            LogUtils.log(TAG, "no such KEYCODE_VR: " + KEYCODE_VR);
        }
    }

    public void processHardKeyFromPolicy(KeyEvent keyEvent, int targetDisplay, Context context) {
        try {
            if (this.hardKeyAdminManager == null) {
                this.hardKeyAdminManager = (IHardKeyAdminManager) ServiceProvider.get(IHardKeyAdminManager.class, context);
            }
            this.hardKeyAdminManager.performKeyEvent(keyEvent, targetDisplay);
        } catch (Throwable ex) {
            LogUtils.logwtf(TAG, ex);
        }
    }
}
