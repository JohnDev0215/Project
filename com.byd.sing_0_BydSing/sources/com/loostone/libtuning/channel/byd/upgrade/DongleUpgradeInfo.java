package com.loostone.libtuning.channel.byd.upgrade;

import com.android.volley.BuildConfig;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class DongleUpgradeInfo {
    public String dongleName = BuildConfig.FLAVOR;
    public String versionCode = BuildConfig.FLAVOR;
    public String fileMd5 = BuildConfig.FLAVOR;
    public String flashMd5 = BuildConfig.FLAVOR;

    public String getDongleName() {
        String str = this.dongleName;
        return str != null ? str.toUpperCase(Locale.getDefault()) : BuildConfig.FLAVOR;
    }

    public String getFileMd5() {
        return this.fileMd5;
    }

    public String getFlashMd5() {
        return this.flashMd5;
    }

    public String getVersion() {
        return this.versionCode;
    }

    public String toString() {
        return "DongleInfo{dongleName='" + this.dongleName + "', versionCode='" + this.versionCode + "', fileMd5='" + this.fileMd5 + "', flashMd5='" + this.flashMd5 + "'}";
    }
}
