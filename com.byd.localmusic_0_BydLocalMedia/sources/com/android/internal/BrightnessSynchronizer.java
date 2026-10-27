package com.android.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.MathUtils;
import java.util.LinkedList;
import java.util.Queue;

/* JADX INFO: loaded from: classes4.dex */
public class BrightnessSynchronizer {
    public static final float EPSILON = 0.001f;
    private static final int MSG_UPDATE_FLOAT = 1;
    private static final int MSG_UPDATE_INT = 2;
    private static final String TAG = "BrightnessSynchronizer";
    private final Context mContext;
    private float mPreferredSettingValue;
    private static final Uri BRIGHTNESS_URI = Settings.System.getUriFor("screen_brightness");
    private static final Uri BRIGHTNESS_FLOAT_URI = Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS_FLOAT);
    private final Queue<Object> mWriteHistory = new LinkedList();
    private final Handler mHandler = new Handler() { // from class: com.android.internal.BrightnessSynchronizer.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                BrightnessSynchronizer.this.updateBrightnessFloatFromInt(message.arg1);
            } else if (i != 2) {
                super.handleMessage(message);
            }
        }
    };

    public BrightnessSynchronizer(Context context) {
        this.mContext = context;
        new BrightnessSyncObserver(this.mHandler).startObserving();
    }

    public static float brightnessIntToFloat(Context context, int i) {
        PowerManager powerManager = (PowerManager) context.getSystemService(PowerManager.class);
        float brightnessConstraint = powerManager.getBrightnessConstraint(0);
        float brightnessConstraint2 = powerManager.getBrightnessConstraint(1);
        return brightnessIntToFloat(i, Math.round(brightnessFloatToIntRange(brightnessConstraint, 0.0f, 1.0f, 1.0f, 255.0f)), Math.round(brightnessFloatToIntRange(brightnessConstraint2, 0.0f, 1.0f, 1.0f, 255.0f)), brightnessConstraint, brightnessConstraint2);
    }

    public static float brightnessIntToFloat(int i, int i2, int i3, float f, float f2) {
        if (i == 0) {
            return -1.0f;
        }
        if (i == -1) {
            return Float.NaN;
        }
        return MathUtils.constrainedMap(f, f2, i2, i3, i);
    }

    public static int brightnessFloatToInt(Context context, float f) {
        return Math.round(brightnessFloatToIntRange(context, f));
    }

    public static float brightnessFloatToIntRange(Context context, float f) {
        PowerManager powerManager = (PowerManager) context.getSystemService(PowerManager.class);
        float brightnessConstraint = powerManager.getBrightnessConstraint(0);
        float brightnessConstraint2 = powerManager.getBrightnessConstraint(1);
        return brightnessFloatToIntRange(f, brightnessConstraint, brightnessConstraint2, brightnessFloatToIntRange(brightnessConstraint, 0.0f, 1.0f, 1.0f, 255.0f), brightnessFloatToIntRange(brightnessConstraint2, 0.0f, 1.0f, 1.0f, 255.0f));
    }

    private static float brightnessFloatToIntRange(float f, float f2, float f3, float f4, float f5) {
        if (floatEquals(f, -1.0f)) {
            return 0.0f;
        }
        if (Float.isNaN(f)) {
            return -1.0f;
        }
        return MathUtils.constrainedMap(f4, f5, f2, f3, f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static float getScreenBrightnessFloat(Context context) {
        return Settings.System.getFloatForUser(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_FLOAT, Float.NaN, -2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getScreenBrightnessInt(Context context) {
        return Settings.System.getIntForUser(context.getContentResolver(), "screen_brightness", 0, -2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBrightnessFloatFromInt(int i) {
        Object objPeek = this.mWriteHistory.peek();
        if (objPeek != null && objPeek.equals(Integer.valueOf(i))) {
            this.mWriteHistory.poll();
        } else {
            if (brightnessFloatToInt(this.mContext, this.mPreferredSettingValue) == i) {
                return;
            }
            float fBrightnessIntToFloat = brightnessIntToFloat(this.mContext, i);
            this.mWriteHistory.offer(Float.valueOf(fBrightnessIntToFloat));
            this.mPreferredSettingValue = fBrightnessIntToFloat;
            Settings.System.putFloatForUser(this.mContext.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_FLOAT, fBrightnessIntToFloat, -2);
        }
    }

    private void updateBrightnessIntFromFloat(float f) {
        int iBrightnessFloatToInt = brightnessFloatToInt(this.mContext, f);
        Object objPeek = this.mWriteHistory.peek();
        if (objPeek != null && objPeek.equals(Float.valueOf(f))) {
            this.mWriteHistory.poll();
            return;
        }
        this.mWriteHistory.offer(Integer.valueOf(iBrightnessFloatToInt));
        this.mPreferredSettingValue = f;
        Settings.System.putIntForUser(this.mContext.getContentResolver(), "screen_brightness", iBrightnessFloatToInt, -2);
    }

    public static boolean floatEquals(float f, float f2) {
        if (f == f2) {
            return true;
        }
        return (Float.isNaN(f) && Float.isNaN(f2)) || Math.abs(f - f2) < 0.001f;
    }

    private class BrightnessSyncObserver extends ContentObserver {
        BrightnessSyncObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            onChange(z, null);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            if (z) {
                return;
            }
            if (BrightnessSynchronizer.BRIGHTNESS_URI.equals(uri)) {
                BrightnessSynchronizer.this.mHandler.obtainMessage(1, BrightnessSynchronizer.getScreenBrightnessInt(BrightnessSynchronizer.this.mContext), 0).sendToTarget();
            } else if (BrightnessSynchronizer.BRIGHTNESS_FLOAT_URI.equals(uri)) {
                BrightnessSynchronizer.this.mHandler.obtainMessage(2, Float.floatToIntBits(BrightnessSynchronizer.getScreenBrightnessFloat(BrightnessSynchronizer.this.mContext)), 0).sendToTarget();
            }
        }

        public void startObserving() {
            ContentResolver contentResolver = BrightnessSynchronizer.this.mContext.getContentResolver();
            contentResolver.unregisterContentObserver(this);
            contentResolver.registerContentObserver(BrightnessSynchronizer.BRIGHTNESS_URI, false, this, -1);
            contentResolver.registerContentObserver(BrightnessSynchronizer.BRIGHTNESS_FLOAT_URI, false, this, -1);
        }

        public void stopObserving() {
            BrightnessSynchronizer.this.mContext.getContentResolver().unregisterContentObserver(this);
        }
    }
}
