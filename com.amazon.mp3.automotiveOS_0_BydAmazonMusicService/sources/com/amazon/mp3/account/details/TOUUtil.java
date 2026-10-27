package com.amazon.mp3.account.details;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class TOUUtil {
    private static TOUUtil sInstance;
    private final Context mContext;

    private static synchronized void createInstance(final Context context) {
        if (sInstance == null && context != null) {
            sInstance = new TOUUtil(context);
        }
    }

    private TOUUtil(final Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static TOUUtil get(final Context context) {
        if (sInstance == null) {
            createInstance(context);
        }
        return sInstance;
    }

    public String getTOUUrl() {
        Locale locale = Locale.getDefault();
        boolean zEquals = locale.getDisplayLanguage().equals(Locale.ENGLISH.getDisplayLanguage());
        if (AccountDetailUtil.get(this.mContext).isRowMarketplace()) {
            if (AccountDetailUtil.get(this.mContext).isRowPrimeOnlyMarketplace()) {
                Uri.Builder builderBuildUpon = Uri.withAppendedPath(Uri.parse(EndpointsProvider.get().getWebviewRowPrimeOnlyEndpoint()), "gp/help/customer/display.html").buildUpon();
                builderBuildUpon.appendQueryParameter(TetheredMessageKey.nodeId, "201380010");
                if (!zEquals) {
                    builderBuildUpon.appendQueryParameter("language", locale.toString());
                }
                return builderBuildUpon.build().toString();
            }
            String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
            String webviewRowLocalizedEndpoint = EndpointsProvider.get().getWebviewRowLocalizedEndpoint();
            if (!TextUtils.isEmpty(webviewRowLocalizedEndpoint)) {
                Uri.Builder builderBuildUpon2 = Uri.withAppendedPath(Uri.parse(webviewRowLocalizedEndpoint), "gp/help/customer/display.html").buildUpon();
                if (MusicTerritory.AUSTRALIA.value.equals(musicTerritoryOfResidence) || MusicTerritory.NEW_ZEALAND.value.equals(musicTerritoryOfResidence)) {
                    builderBuildUpon2.appendQueryParameter(TetheredMessageKey.nodeId, "202197050");
                    builderBuildUpon2.appendQueryParameter("pop-up", "1");
                    builderBuildUpon2.appendQueryParameter("view-type", "content-only");
                } else {
                    builderBuildUpon2.appendQueryParameter(TetheredMessageKey.nodeId, "201380010");
                    if (!zEquals) {
                        builderBuildUpon2.appendQueryParameter("language", locale.toString());
                    }
                }
                return builderBuildUpon2.build().toString();
            }
            Uri.Builder builderBuildUpon3 = Uri.withAppendedPath(Uri.parse(EndpointsProvider.get().getRowMusicDomain()), "help").buildUpon();
            builderBuildUpon3.appendQueryParameter(TetheredMessageKey.nodeId, "202197050");
            return builderBuildUpon3.build().toString();
        }
        Uri.Builder builderBuildUpon4 = Uri.withAppendedPath(Uri.parse(Environment.WEBVIEW.get().toURL()), "gp/help/customer/display.html").buildUpon();
        builderBuildUpon4.appendQueryParameter(TetheredMessageKey.nodeId, "201380010");
        if (!zEquals) {
            builderBuildUpon4.appendQueryParameter("language", locale.toString());
        }
        return builderBuildUpon4.build().toString();
    }
}
