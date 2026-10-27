package com.android.car.settings.common;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.settingslib.drawer.TileUtils;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ExtraSettingsUtil {
    private static final Logger LOG = new Logger((Class<?>) ExtraSettingsUtil.class);

    public static boolean isIconTintable(Bundle metaData) {
        if (metaData.containsKey(TileUtils.META_DATA_PREFERENCE_ICON_TINTABLE)) {
            return metaData.getBoolean(TileUtils.META_DATA_PREFERENCE_ICON_TINTABLE);
        }
        return false;
    }

    public static Drawable loadDrawableFromPackage(Context context, String resPackage, int resId) {
        try {
            return context.createPackageContext(resPackage, 0).getDrawable(resId);
        } catch (PackageManager.NameNotFoundException ex) {
            LOG.e("loadDrawableFromPackage: package name not found: ", ex);
            return null;
        } catch (Resources.NotFoundException ex2) {
            LOG.w("loadDrawableFromPackage: resource not found with id " + resId, ex2);
            return null;
        }
    }

    public static Uri getCompleteUri(Bundle metaData, String metaDataKey, String defaultMethod) {
        String uriString = metaData.getString(metaDataKey);
        if (TextUtils.isEmpty(uriString)) {
            return null;
        }
        Uri uri = Uri.parse(uriString);
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments != null && !pathSegments.isEmpty()) {
            return uri;
        }
        String key = metaData.getString(TileUtils.META_DATA_PREFERENCE_KEYHINT);
        if (TextUtils.isEmpty(key)) {
            LOG.w("Please specify the meta-data com.android.settings.keyhint in AndroidManifest.xml for " + uriString);
            return buildUri(uri.getAuthority(), defaultMethod);
        }
        return buildUri(uri.getAuthority(), defaultMethod, key);
    }

    private static Uri buildUri(String authority, String method, String key) {
        return new Uri.Builder().scheme("content").authority(authority).appendPath(method).appendPath(key).build();
    }

    private static Uri buildUri(String authority, String method) {
        return new Uri.Builder().scheme("content").authority(authority).appendPath(method).build();
    }
}
