package com.android.settingslib.display;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.MathUtils;
import android.view.WindowManagerGlobal;
import com.android.settingslib.R$string;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class DisplayDensityUtils {
    private final int mCurrentIndex;
    private final int mDefaultDensity;
    private final String[] mEntries;
    private final int[] mValues;
    public static final int SUMMARY_DEFAULT = R$string.screen_zoom_summary_default;
    private static final int SUMMARY_CUSTOM = R$string.screen_zoom_summary_custom;
    private static final int[] SUMMARIES_SMALLER = {R$string.screen_zoom_summary_small};
    private static final int[] SUMMARIES_LARGER = {R$string.screen_zoom_summary_large, R$string.screen_zoom_summary_very_large, R$string.screen_zoom_summary_extremely_large};

    public DisplayDensityUtils(Context context) {
        int i;
        int i2;
        int[] iArr = SUMMARIES_SMALLER;
        int[] iArr2 = SUMMARIES_LARGER;
        int defaultDisplayDensity = getDefaultDisplayDensity(0);
        if (defaultDisplayDensity <= 0) {
            this.mEntries = null;
            this.mValues = null;
            this.mDefaultDensity = 0;
            this.mCurrentIndex = -1;
            return;
        }
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getDisplayNoVerify().getRealMetrics(displayMetrics);
        int i3 = displayMetrics.densityDpi;
        float f = defaultDisplayDensity;
        float f2 = 1.0f;
        float fMin = Math.min(1.5f, ((Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) * 160) / 320) / f) - 1.0f;
        int iConstrain = (int) MathUtils.constrain(fMin / 0.09f, 0.0f, iArr2.length);
        int iConstrain2 = (int) MathUtils.constrain(1.6666664f, 0.0f, iArr.length);
        int i4 = iConstrain2 + 1 + iConstrain;
        String[] strArr = new String[i4];
        int[] iArrCopyOf = new int[i4];
        if (iConstrain2 > 0) {
            float f3 = 0.14999998f / iConstrain2;
            int i5 = iConstrain2 - 1;
            i = 0;
            int i6 = -1;
            while (i5 >= 0) {
                int i7 = ((int) ((f2 - ((i5 + 1) * f3)) * f)) & (-2);
                if (i3 == i7) {
                    i6 = i;
                }
                strArr[i] = resources.getString(iArr[i5]);
                iArrCopyOf[i] = i7;
                i++;
                i5--;
                f2 = 1.0f;
            }
            i2 = i6;
        } else {
            i = 0;
            i2 = -1;
        }
        i2 = i3 == defaultDisplayDensity ? i : i2;
        iArrCopyOf[i] = defaultDisplayDensity;
        strArr[i] = resources.getString(SUMMARY_DEFAULT);
        int i8 = i + 1;
        if (iConstrain > 0) {
            float f4 = fMin / iConstrain;
            int i9 = 0;
            while (i9 < iConstrain) {
                int i10 = i9 + 1;
                int i11 = ((int) (((i10 * f4) + 1.0f) * f)) & (-2);
                if (i3 == i11) {
                    i2 = i8;
                }
                iArrCopyOf[i8] = i11;
                strArr[i8] = resources.getString(iArr2[i9]);
                i8++;
                i9 = i10;
            }
        }
        if (i2 >= 0) {
            i8 = i2;
        } else {
            int i12 = i4 + 1;
            iArrCopyOf = Arrays.copyOf(iArrCopyOf, i12);
            iArrCopyOf[i8] = i3;
            strArr = (String[]) Arrays.copyOf(strArr, i12);
            strArr[i8] = resources.getString(SUMMARY_CUSTOM, Integer.valueOf(i3));
        }
        this.mDefaultDensity = defaultDisplayDensity;
        this.mCurrentIndex = i8;
        this.mEntries = strArr;
        this.mValues = iArrCopyOf;
    }

    public String[] getEntries() {
        return this.mEntries;
    }

    public int[] getValues() {
        return this.mValues;
    }

    public int getCurrentIndex() {
        return this.mCurrentIndex;
    }

    public int getDefaultDensity() {
        return this.mDefaultDensity;
    }

    private static int getDefaultDisplayDensity(int i) {
        try {
            return WindowManagerGlobal.getWindowManagerService().getInitialDisplayDensity(i);
        } catch (RemoteException unused) {
            return -1;
        }
    }

    public static void clearForcedDisplayDensity(final int i) {
        final int iMyUserId = UserHandle.myUserId();
        AsyncTask.execute(new Runnable() { // from class: com.android.settingslib.display.-$$Lambda$DisplayDensityUtils$FjSo_v2dJihYeklLmCubVRPf_nw
            @Override // java.lang.Runnable
            public final void run() {
                DisplayDensityUtils.lambda$clearForcedDisplayDensity$0(i, iMyUserId);
            }
        });
    }

    static /* synthetic */ void lambda$clearForcedDisplayDensity$0(int i, int i2) {
        try {
            WindowManagerGlobal.getWindowManagerService().clearForcedDisplayDensityForUser(i, i2);
        } catch (RemoteException unused) {
            Log.w("DisplayDensityUtils", "Unable to clear forced display density setting");
        }
    }

    public static void setForcedDisplayDensity(final int i, final int i2) {
        final int iMyUserId = UserHandle.myUserId();
        AsyncTask.execute(new Runnable() { // from class: com.android.settingslib.display.-$$Lambda$DisplayDensityUtils$jbnNZEy3zYf8rJTNV5wQSa3Z5eQ
            @Override // java.lang.Runnable
            public final void run() {
                DisplayDensityUtils.lambda$setForcedDisplayDensity$1(i, i2, iMyUserId);
            }
        });
    }

    static /* synthetic */ void lambda$setForcedDisplayDensity$1(int i, int i2, int i3) {
        try {
            WindowManagerGlobal.getWindowManagerService().setForcedDisplayDensityForUser(i, i2, i3);
        } catch (RemoteException unused) {
            Log.w("DisplayDensityUtils", "Unable to save forced display density setting");
        }
    }
}
