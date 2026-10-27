package com.amazon.mp3.prime.upsell;

import android.net.Uri;
import android.text.TextUtils;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.music.marketplace.Marketplace;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
public class UpsellUrls {
    private final List<Marketplace> WHITELISTED_MARKETPLACES = Arrays.asList(Marketplace.USA, Marketplace.UK, Marketplace.GERMANY);
    private final HashSet<String> WHITELISTED_HOSTNAMES = new HashSet<>(Arrays.asList("www.amazon.com", "www.amazon.co.uk", "www.amazon.de", "www.amazon.fr", "www.amazon.co.jp", "www.amazon.ca", "www.amazon.es", "www.amazon.it", "www.amazon.cn", "music.amazon.com", "music.amazon.co.uk", "music.amazon.de", "music.amazon.fr", "music.amazon.it", "music.amazon.es", "music.amazon.co.jp", "music.amazon.ca", "music.amazon.com.au", "music.amazon.in", "music.amazon.com.mx", "music.amazon.com.br"));

    public boolean isUpsellUrl(final String url) {
        if (url == null) {
            return false;
        }
        Uri uri = Uri.parse(url);
        return "https".equals(uri.getScheme()) && isWhitelistedMarketplace() && startOfPathIsCorrect(uri.getPath());
    }

    public boolean isSplashUrl(final String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        Uri uri = Uri.parse(url);
        return "https".equals(uri.getScheme()) && url.contains("/splash") && isWhitelistedHostname(uri.getHost());
    }

    private boolean isWhitelistedHostname(String uriHost) {
        return this.WHITELISTED_HOSTNAMES.contains(uriHost);
    }

    private boolean startOfPathIsCorrect(String fullPath) {
        Matcher matcher = Pattern.compile("(.*\\/growth\\/static)([a-zA-Z0-9\\/\\.\\_\\-]*)").matcher(fullPath);
        return matcher.find() && "/growth/static".equals(matcher.group(1));
    }

    private boolean isWhitelistedMarketplace() {
        Iterator<Marketplace> it = this.WHITELISTED_MARKETPLACES.iterator();
        while (it.hasNext()) {
            if (it.next().equals(AccountDetailUtil.get().getHomeMarketPlace())) {
                return true;
            }
        }
        return false;
    }
}
