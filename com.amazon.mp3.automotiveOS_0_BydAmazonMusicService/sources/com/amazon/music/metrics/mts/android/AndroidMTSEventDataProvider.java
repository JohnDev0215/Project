package com.amazon.music.metrics.mts.android;

import android.content.Context;
import android.content.pm.PackageManager;
import com.amazon.music.metrics.mts.MTSEventDataProvider;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AndroidMTSEventDataProvider implements MTSEventDataProvider {
    private static final DateFormat TIMEZONE_DATE_FORMAT = new SimpleDateFormat("ZZZZZ");
    private String mAppVersion;
    private Context mContext;
    private String mPlatformName;

    public AndroidMTSEventDataProvider(Context context, String str) {
        this.mContext = context.getApplicationContext();
        this.mPlatformName = str;
    }

    @Override // com.amazon.music.metrics.mts.MTSEventDataProvider
    public String getAppVersion() {
        if (this.mAppVersion == null) {
            synchronized (this) {
                if (this.mAppVersion == null) {
                    try {
                        this.mAppVersion = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName;
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                }
            }
        }
        return this.mAppVersion;
    }

    @Override // com.amazon.music.metrics.mts.MTSEventDataProvider
    public String getPlatformName() {
        return this.mPlatformName;
    }

    @Override // com.amazon.music.metrics.mts.MTSEventDataProvider
    public String getLocale() {
        return URLEncoder.encode(this.mContext.getResources().getConfiguration().locale.toString());
    }

    @Override // com.amazon.music.metrics.mts.MTSEventDataProvider
    public String getDeviceTimeZone() {
        return TIMEZONE_DATE_FORMAT.format(Calendar.getInstance(Locale.US).getTime());
    }
}
