package com.android.car.developeroptions.display;

import android.app.UiModeManager;
import android.content.Context;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class DarkUISettingsRadioButtonsController {
    private Preference mFooter;
    UiModeManager mManager;

    public DarkUISettingsRadioButtonsController(Context context, Preference preference) {
        this.mManager = (UiModeManager) context.getSystemService(UiModeManager.class);
        this.mFooter = preference;
    }

    public String getDefaultKey() {
        int nightMode = this.mManager.getNightMode();
        updateFooter();
        return nightMode == 2 ? "key_dark_ui_settings_dark" : "key_dark_ui_settings_light";
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean setDefaultKey(java.lang.String r4) {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            r1 = -1480547472(0xffffffffa7c0a370, float:-5.3467904E-15)
            r2 = 1
            if (r0 == r1) goto L1a
            r1 = 1355284572(0x50c8005c, float:2.6843734E10)
            if (r0 == r1) goto L10
            goto L24
        L10:
            java.lang.String r0 = "key_dark_ui_settings_light"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L24
            r0 = r2
            goto L25
        L1a:
            java.lang.String r0 = "key_dark_ui_settings_dark"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L24
            r0 = 0
            goto L25
        L24:
            r0 = -1
        L25:
            if (r0 == 0) goto L54
            if (r0 != r2) goto L2f
            android.app.UiModeManager r4 = r3.mManager
            r4.setNightMode(r2)
            goto L5a
        L2f:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Not a valid key for "
            r0.append(r1)
            java.lang.Class<com.android.car.developeroptions.display.DarkUISettingsRadioButtonsController> r1 = com.android.car.developeroptions.display.DarkUISettingsRadioButtonsController.class
            java.lang.String r1 = r1.getSimpleName()
            r0.append(r1)
            java.lang.String r1 = ": "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r3.<init>(r4)
            throw r3
        L54:
            android.app.UiModeManager r4 = r3.mManager
            r0 = 2
            r4.setNightMode(r0)
        L5a:
            r3.updateFooter()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.display.DarkUISettingsRadioButtonsController.setDefaultKey(java.lang.String):boolean");
    }

    public void updateFooter() {
        if (this.mManager.getNightMode() == 2) {
            this.mFooter.setSummary(R.string.dark_ui_settings_dark_summary);
        } else {
            this.mFooter.setSummary(R.string.dark_ui_settings_light_summary);
        }
    }

    public static String modeToDescription(Context context, int i) {
        String[] stringArray = context.getResources().getStringArray(R.array.dark_ui_mode_entries);
        if (i == 2) {
            return stringArray[0];
        }
        return stringArray[1];
    }
}
