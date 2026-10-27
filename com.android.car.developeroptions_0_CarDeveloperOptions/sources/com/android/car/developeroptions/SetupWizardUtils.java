package com.android.car.developeroptions;

import android.content.Intent;
import android.sysprop.SetupWizardProperties;
import com.google.android.setupcompat.util.WizardManagerHelper;

/* JADX INFO: loaded from: classes.dex */
public class SetupWizardUtils {
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static int getTheme(Intent intent) {
        String stringExtra = intent.getStringExtra("theme");
        if (stringExtra == null) {
            stringExtra = (String) SetupWizardProperties.theme().orElse("");
        }
        if (stringExtra == null) {
            return R.style.GlifTheme;
        }
        byte b = 0;
        if (WizardManagerHelper.isAnySetupWizard(intent)) {
            switch (stringExtra.hashCode()) {
                case -2128555920:
                    b = !stringExtra.equals("glif_v2_light") ? (byte) -1 : (byte) 2;
                    break;
                case -1241052239:
                    if (!stringExtra.equals("glif_v3_light")) {
                        b = -1;
                    }
                    break;
                case 3175618:
                    b = !stringExtra.equals("glif") ? (byte) -1 : (byte) 5;
                    break;
                case 115650329:
                    b = !stringExtra.equals("glif_v2") ? (byte) -1 : (byte) 3;
                    break;
                case 115650330:
                    b = !stringExtra.equals("glif_v3") ? (byte) -1 : (byte) 1;
                    break;
                case 767685465:
                    b = !stringExtra.equals("glif_light") ? (byte) -1 : (byte) 4;
                    break;
                default:
                    b = -1;
                    break;
            }
            return b != 0 ? b != 1 ? b != 2 ? b != 3 ? b != 4 ? R.style.GlifTheme : R.style.GlifTheme_Light : R.style.GlifV2Theme : R.style.GlifV2Theme_Light : R.style.GlifV3Theme : R.style.GlifV3Theme_Light;
        }
        switch (stringExtra.hashCode()) {
            case -2128555920:
                b = !stringExtra.equals("glif_v2_light") ? (byte) -1 : (byte) 2;
                break;
            case -1241052239:
                if (!stringExtra.equals("glif_v3_light")) {
                    b = -1;
                }
                break;
            case 3175618:
                b = !stringExtra.equals("glif") ? (byte) -1 : (byte) 5;
                break;
            case 115650329:
                b = !stringExtra.equals("glif_v2") ? (byte) -1 : (byte) 3;
                break;
            case 115650330:
                b = !stringExtra.equals("glif_v3") ? (byte) -1 : (byte) 1;
                break;
            case 767685465:
                b = !stringExtra.equals("glif_light") ? (byte) -1 : (byte) 4;
                break;
            default:
                b = -1;
                break;
        }
        return (b == 0 || b == 1) ? R.style.GlifV3Theme : (b == 2 || b == 3) ? R.style.GlifV2Theme : R.style.GlifTheme;
    }

    public static void copySetupExtras(Intent intent, Intent intent2) {
        WizardManagerHelper.copyWizardManagerExtras(intent, intent2);
    }
}
