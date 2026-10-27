package com.loostone.libtuning.channel.skyworth.old.ai;

import OooO00o.OooO00o.OooO0O0.b;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class KaraokeVoiceAppLogic {
    private static final String TAG = "KaraokeVoiceAppLogic";
    private static KaraokeVoiceAppLogic mInstance;
    private final String CHANGBA_PACKAGE = "com.changba.sd";
    private Context mContext;
    private KaraokeApp mKaraokeApp;

    private KaraokeVoiceAppLogic() {
        KaraokeApp karaokeApp = new KaraokeApp();
        this.mKaraokeApp = karaokeApp;
        karaokeApp.setFirstPackage("com.changba.sd");
        this.mKaraokeApp.setType(KaraokeApp.MODE_DFT);
    }

    private String getInstalledPackage(List<String> list) {
        for (String str : list) {
            if (isAppInstalled(this.mContext, str)) {
                return str;
            }
        }
        return "com.changba.sd";
    }

    public static synchronized KaraokeVoiceAppLogic getInstance() {
        if (mInstance == null) {
            mInstance = new KaraokeVoiceAppLogic();
        }
        return mInstance;
    }

    private boolean isAppInstalled(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public void deinit() {
        b.f41a.b(TAG, "deinit");
        this.mContext = null;
        this.mKaraokeApp = null;
        mInstance = null;
    }

    public String getPackageName() {
        KaraokeApp karaokeApp = this.mKaraokeApp;
        if (karaokeApp == null) {
            b.f41a.b(TAG, "dft package");
            return "com.changba.sd";
        }
        String firstPackage = karaokeApp.getFirstPackage();
        if ((this.mKaraokeApp.getType() != KaraokeApp.MODE_DFT_INSTALL && this.mKaraokeApp.getType() != KaraokeApp.MODE_FIRST_INSTALL) || this.mKaraokeApp.getSupportList() == null || this.mKaraokeApp.getSupportList().size() <= 0) {
            return firstPackage;
        }
        String installedPackage = getInstalledPackage(this.mKaraokeApp.getSupportList());
        b.f41a.b(TAG, "installed package:" + installedPackage);
        return installedPackage;
    }

    public void init(Context context) {
        b.f41a.b(TAG, "init");
        this.mContext = context.getApplicationContext();
    }

    public boolean isForceUsePackageName() {
        KaraokeApp karaokeApp = this.mKaraokeApp;
        if (karaokeApp == null) {
            return false;
        }
        return karaokeApp.getType() == KaraokeApp.MODE_FIRST || this.mKaraokeApp.getType() == KaraokeApp.MODE_FIRST_INSTALL;
    }
}
