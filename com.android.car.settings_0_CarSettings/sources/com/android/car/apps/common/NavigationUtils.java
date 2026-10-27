package com.android.car.apps.common;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;

/* JADX INFO: loaded from: classes5.dex */
public class NavigationUtils {
    public static Intent getViewAddressIntent(Resources res, String address) {
        String formattedAddress = String.format(res.getString(R.string.config_address_uri_format), Uri.encode(address));
        Uri addressUri = Uri.parse(formattedAddress);
        return new Intent("android.intent.action.VIEW", addressUri);
    }

    public static Intent getViewAddressIntent(Context context, String address) {
        Resources resources = context.getResources();
        return getViewAddressIntent(resources, address);
    }

    public static Intent getNavigationIntent(Resources res, String address) {
        String formattedAddress = String.format(res.getString(R.string.config_navigation_uri_format), Uri.encode(address));
        Uri addressUri = Uri.parse(formattedAddress);
        return new Intent("android.intent.action.VIEW", addressUri);
    }

    public static Intent getNavigationIntent(Context context, String address) {
        Resources resources = context.getResources();
        return getNavigationIntent(resources, address);
    }
}
