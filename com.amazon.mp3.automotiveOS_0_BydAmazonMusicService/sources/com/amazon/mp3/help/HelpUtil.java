package com.amazon.mp3.help;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.config.Configuration;
import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class HelpUtil {
    private static final String TAG = "HelpUtil";
    private static final HashMap<String, String> URL_ROW_LOCALIZED_HELP_MAP;

    public enum HelpType {
        LIBRARY,
        GENERIC
    }

    static {
        HashMap<String, String> map = new HashMap<>();
        URL_ROW_LOCALIZED_HELP_MAP = map;
        map.put(MusicTerritory.INDIA.value, "gp/help/customer/display.html?nodeId=201379540");
        map.put(MusicTerritory.CANADA.value, "gp/help/customer/display.html?nodeId=201379540");
        map.put(MusicTerritory.AUSTRALIA.value, "gp/help/customer/display.html?nodeId=201379540");
        map.put(MusicTerritory.NEW_ZEALAND.value, "gp/help/customer/display.html?nodeId=201379540");
        map.put(MusicTerritory.MEXICO.value, "gp/help/customer/display.html?nodeId=201379540");
        map.put(MusicTerritory.BRAZIL.value, "gp/help/customer/display.html?nodeId=G5ZL5HPKATHSNWKP&view-type=content-only");
    }

    public static Intent getHelpIntent(final Context context, final HelpType type) {
        String helpUrl = getHelpUrl(type, AmazonApplication.getConfiguration(context.getApplicationContext()));
        if (helpUrl != null && helpUrl.length() > 0) {
            Uri uri = Uri.parse(helpUrl);
            if (uri == null) {
                Log.warning(TAG, "Could not parse HELP url");
            } else {
                return new Intent("android.intent.action.VIEW", uri);
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.help.HelpUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$help$HelpUtil$HelpType;

        static {
            int[] iArr = new int[HelpType.values().length];
            $SwitchMap$com$amazon$mp3$help$HelpUtil$HelpType = iArr;
            try {
                iArr[HelpType.LIBRARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$help$HelpUtil$HelpType[HelpType.GENERIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @VisibleForTesting(otherwise = 2)
    protected static String getHelpUrl(final HelpType helpType, final Configuration config) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$help$HelpUtil$HelpType[helpType.ordinal()];
        if (i == 1) {
            return getHelpEndpoint();
        }
        if (i != 2) {
            return null;
        }
        return config.getString("url_help_generic");
    }

    private static String getHelpEndpoint() {
        String str;
        if (AccountDetailUtil.get().isRowMarketplace()) {
            if (AccountDetailUtil.get().isRowPrimeOnlyMarketplace()) {
                str = EndpointsProvider.get().getWebviewRowPrimeOnlyEndpoint() + URL_ROW_LOCALIZED_HELP_MAP.get(AccountDetailUtil.getMusicTerritoryOfResidence());
            } else {
                String str2 = URL_ROW_LOCALIZED_HELP_MAP.get(AccountDetailUtil.getMusicTerritoryOfResidence());
                String webviewRowLocalizedEndpoint = EndpointsProvider.get().getWebviewRowLocalizedEndpoint();
                if (TextUtils.isEmpty(webviewRowLocalizedEndpoint)) {
                    webviewRowLocalizedEndpoint = EndpointsProvider.get().getApexRowMusicEndpoint();
                }
                if (!TextUtils.isEmpty(str2)) {
                    str = webviewRowLocalizedEndpoint + str2;
                } else {
                    str = webviewRowLocalizedEndpoint + "help";
                }
            }
            return getUrlWithLanguageParamsIfNeeded(str, Locale.getDefault());
        }
        return EndpointsProvider.get().getLibraryHelpEndPoint();
    }

    private static String getUrlWithLanguageParamsIfNeeded(final String url, Locale locale) {
        if (TextUtils.isEmpty(url)) {
            return url;
        }
        if (url.contains("?")) {
            if (url.lastIndexOf("?") != url.length() - 1) {
                url = url + "&";
            }
        } else {
            url = url + "?";
        }
        return url + String.format("language=%s", locale.toString());
    }
}
