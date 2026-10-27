package com.android.settingslib.drawer;

import android.app.ActivityManager;
import android.content.Context;
import android.content.IContentProvider;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public class TileUtils {
    private static final boolean DEBUG_TIMING = false;
    static final String EXTRA_CATEGORY_KEY = "com.android.settings.category";
    static final String EXTRA_PREFERENCE_ICON_PACKAGE = "com.android.settings.icon_package";
    public static final String EXTRA_SETTINGS_ACTION = "com.android.settings.action.EXTRA_SETTINGS";
    public static final String IA_SETTINGS_ACTION = "com.android.settings.action.IA_SETTINGS";
    private static final String LOG_TAG = "TileUtils";
    private static final String MANUFACTURER_DEFAULT_CATEGORY = "com.android.settings.category.device";
    private static final String MANUFACTURER_SETTINGS = "com.android.settings.MANUFACTURER_APPLICATION_SETTING";
    public static final String META_DATA_KEY_ORDER = "com.android.settings.order";
    public static final String META_DATA_KEY_PROFILE = "com.android.settings.profile";
    public static final String META_DATA_PREFERENCE_ICON = "com.android.settings.icon";
    public static final String META_DATA_PREFERENCE_ICON_BACKGROUND_ARGB = "com.android.settings.bg.argb";
    public static final String META_DATA_PREFERENCE_ICON_BACKGROUND_HINT = "com.android.settings.bg.hint";
    public static final String META_DATA_PREFERENCE_ICON_TINTABLE = "com.android.settings.icon_tintable";
    public static final String META_DATA_PREFERENCE_ICON_URI = "com.android.settings.icon_uri";
    public static final String META_DATA_PREFERENCE_KEYHINT = "com.android.settings.keyhint";
    public static final String META_DATA_PREFERENCE_SUMMARY = "com.android.settings.summary";
    public static final String META_DATA_PREFERENCE_SUMMARY_URI = "com.android.settings.summary_uri";
    public static final String META_DATA_PREFERENCE_SWITCH_URI = "com.android.settings.switch_uri";
    public static final String META_DATA_PREFERENCE_TITLE = "com.android.settings.title";
    public static final String META_DATA_PREFERENCE_TITLE_URI = "com.android.settings.title_uri";
    private static final String OPERATOR_DEFAULT_CATEGORY = "com.android.settings.category.wireless";
    private static final String OPERATOR_SETTINGS = "com.android.settings.OPERATOR_APPLICATION_SETTING";
    public static final String PROFILE_ALL = "all_profiles";
    public static final String PROFILE_PRIMARY = "primary_profile_only";
    private static final String SETTINGS_ACTION = "com.android.settings.action.SETTINGS";
    static final String SETTING_PKG = "com.android.settings";

    public static List<DashboardCategory> getCategories(Context context, Map<Pair<String, String>, Tile> cache) {
        System.currentTimeMillis();
        boolean setup = Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) != 0;
        ArrayList<Tile> tiles = new ArrayList<>();
        UserManager userManager = (UserManager) context.getSystemService("user");
        for (UserHandle user : userManager.getUserProfiles()) {
            if (user.getIdentifier() == ActivityManager.getCurrentUser()) {
                loadTilesForAction(context, user, SETTINGS_ACTION, cache, null, tiles, true);
                loadTilesForAction(context, user, OPERATOR_SETTINGS, cache, OPERATOR_DEFAULT_CATEGORY, tiles, false);
                loadTilesForAction(context, user, MANUFACTURER_SETTINGS, cache, MANUFACTURER_DEFAULT_CATEGORY, tiles, false);
            }
            if (setup) {
                loadTilesForAction(context, user, EXTRA_SETTINGS_ACTION, cache, null, tiles, false);
                loadTilesForAction(context, user, IA_SETTINGS_ACTION, cache, null, tiles, false);
            }
        }
        HashMap<String, DashboardCategory> categoryMap = new HashMap<>();
        for (Tile tile : tiles) {
            String categoryKey = tile.getCategory();
            DashboardCategory category = categoryMap.get(categoryKey);
            if (category == null) {
                category = new DashboardCategory(categoryKey);
                categoryMap.put(categoryKey, category);
            }
            category.addTile(tile);
        }
        ArrayList<DashboardCategory> categories = new ArrayList<>(categoryMap.values());
        Iterator<DashboardCategory> it = categories.iterator();
        while (it.hasNext()) {
            it.next().sortTiles();
        }
        return categories;
    }

    static void loadTilesForAction(Context context, UserHandle user, String action, Map<Pair<String, String>, Tile> addedCache, String defaultCategory, List<Tile> outTiles, boolean requireSettings) {
        Intent intent = new Intent(action);
        if (requireSettings) {
            intent.setPackage("com.android.settings");
        }
        loadActivityTiles(context, user, addedCache, defaultCategory, outTiles, intent);
        loadProviderTiles(context, user, addedCache, defaultCategory, outTiles, intent);
    }

    private static void loadActivityTiles(Context context, UserHandle user, Map<Pair<String, String>, Tile> addedCache, String defaultCategory, List<Tile> outTiles, Intent intent) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> results = pm.queryIntentActivitiesAsUser(intent, 128, user.getIdentifier());
        for (ResolveInfo resolved : results) {
            if (resolved.system) {
                ActivityInfo activityInfo = resolved.activityInfo;
                Bundle metaData = activityInfo.metaData;
                loadTile(user, addedCache, defaultCategory, outTiles, intent, metaData, activityInfo);
            }
        }
    }

    private static void loadProviderTiles(Context context, UserHandle user, Map<Pair<String, String>, Tile> addedCache, String defaultCategory, List<Tile> outTiles, Intent intent) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> results = pm.queryIntentContentProvidersAsUser(intent, 0, user.getIdentifier());
        for (ResolveInfo resolved : results) {
            if (resolved.system) {
                ProviderInfo providerInfo = resolved.providerInfo;
                List<Bundle> switchData = getSwitchDataFromProvider(context, providerInfo.authority);
                if (switchData != null && !switchData.isEmpty()) {
                    for (Bundle metaData : switchData) {
                        loadTile(user, addedCache, defaultCategory, outTiles, intent, metaData, providerInfo);
                    }
                }
            }
        }
    }

    private static void loadTile(UserHandle user, Map<Pair<String, String>, Tile> addedCache, String defaultCategory, List<Tile> outTiles, Intent intent, Bundle metaData, ComponentInfo componentInfo) {
        Pair<String, String> key;
        Tile activityTile;
        if (user.getIdentifier() != ActivityManager.getCurrentUser() && Tile.isPrimaryProfileOnly(componentInfo.metaData)) {
            Log.w(LOG_TAG, "Found " + componentInfo.name + " for intent " + intent + " is primary profile only, skip loading tile for uid " + user.getIdentifier());
            return;
        }
        String str = EXTRA_CATEGORY_KEY;
        if ((metaData == null || !metaData.containsKey(EXTRA_CATEGORY_KEY)) && defaultCategory == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Found ");
            sb.append(componentInfo.name);
            sb.append(" for intent ");
            sb.append(intent);
            sb.append(" missing metadata ");
            if (metaData == null) {
                str = "";
            }
            sb.append(str);
            Log.w(LOG_TAG, sb.toString());
            return;
        }
        String categoryKey = metaData.getString(EXTRA_CATEGORY_KEY);
        boolean isProvider = componentInfo instanceof ProviderInfo;
        if (isProvider) {
            key = new Pair<>(((ProviderInfo) componentInfo).authority, metaData.getString(META_DATA_PREFERENCE_KEYHINT));
        } else {
            key = new Pair<>(componentInfo.packageName, componentInfo.name);
        }
        Tile tile = addedCache.get(key);
        if (tile == null) {
            if (isProvider) {
                activityTile = new ProviderTile((ProviderInfo) componentInfo, categoryKey, metaData);
            } else {
                activityTile = new ActivityTile((ActivityInfo) componentInfo, categoryKey);
            }
            tile = activityTile;
            addedCache.put(key, tile);
        } else {
            tile.setMetaData(metaData);
        }
        if (!tile.userHandle.contains(user)) {
            tile.userHandle.add(user);
        }
        if (!outTiles.contains(tile)) {
            outTiles.add(tile);
        }
    }

    static Bundle getSwitchDataFromProvider(Context context, String authority, String key) {
        Map<String, IContentProvider> providerMap = new ArrayMap<>();
        Uri uri = buildUri(authority, SwitchesProvider.METHOD_GET_SWITCH_DATA, key);
        return getBundleFromUri(context, uri, providerMap, null);
    }

    private static List<Bundle> getSwitchDataFromProvider(Context context, String authority) {
        Map<String, IContentProvider> providerMap = new ArrayMap<>();
        Uri uri = buildUri(authority, SwitchesProvider.METHOD_GET_SWITCH_DATA);
        Bundle result = getBundleFromUri(context, uri, providerMap, null);
        if (result == null) {
            return null;
        }
        return result.getParcelableArrayList(SwitchesProvider.EXTRA_SWITCH_DATA);
    }

    public static Uri getCompleteUri(Tile tile, String metaDataKey, String defaultMethod) {
        String uriString = tile.getMetaData().getString(metaDataKey);
        if (TextUtils.isEmpty(uriString)) {
            return null;
        }
        Uri uri = Uri.parse(uriString);
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments != null && !pathSegments.isEmpty()) {
            return uri;
        }
        String key = tile.getMetaData().getString(META_DATA_PREFERENCE_KEYHINT);
        if (TextUtils.isEmpty(key)) {
            Log.w(LOG_TAG, "Please specify the meta-data com.android.settings.keyhint in AndroidManifest.xml for " + uriString);
            return buildUri(uri.getAuthority(), defaultMethod);
        }
        return buildUri(uri.getAuthority(), defaultMethod, key);
    }

    static Uri buildUri(String authority, String method, String key) {
        return new Uri.Builder().scheme("content").authority(authority).appendPath(method).appendPath(key).build();
    }

    private static Uri buildUri(String authority, String method) {
        return new Uri.Builder().scheme("content").authority(authority).appendPath(method).build();
    }

    public static Pair<String, Integer> getIconFromUri(Context context, String packageName, Uri uri, Map<String, IContentProvider> providerMap) {
        int resId;
        Bundle bundle = getBundleFromUri(context, uri, providerMap, null);
        if (bundle == null) {
            return null;
        }
        String iconPackageName = bundle.getString(EXTRA_PREFERENCE_ICON_PACKAGE);
        if (TextUtils.isEmpty(iconPackageName) || (resId = bundle.getInt(META_DATA_PREFERENCE_ICON, 0)) == 0) {
            return null;
        }
        if (!iconPackageName.equals(packageName) && !iconPackageName.equals(context.getPackageName())) {
            return null;
        }
        return Pair.create(iconPackageName, Integer.valueOf(resId));
    }

    public static String getTextFromUri(Context context, Uri uri, Map<String, IContentProvider> providerMap, String key) {
        Bundle bundle = getBundleFromUri(context, uri, providerMap, null);
        if (bundle != null) {
            return bundle.getString(key);
        }
        return null;
    }

    public static boolean getBooleanFromUri(Context context, Uri uri, Map<String, IContentProvider> providerMap, String key) {
        Bundle bundle = getBundleFromUri(context, uri, providerMap, null);
        if (bundle != null) {
            return bundle.getBoolean(key);
        }
        return false;
    }

    public static Bundle putBooleanToUriAndGetResult(Context context, Uri uri, Map<String, IContentProvider> providerMap, String key, boolean value) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(key, value);
        return getBundleFromUri(context, uri, providerMap, bundle);
    }

    private static Bundle getBundleFromUri(Context context, Uri uri, Map<String, IContentProvider> providerMap, Bundle bundle) {
        IContentProvider provider;
        Pair<String, String> args = getMethodAndKey(uri);
        if (args == null) {
            return null;
        }
        String method = (String) args.first;
        String key = (String) args.second;
        if (TextUtils.isEmpty(method) || (provider = getProviderFromUri(context, uri, providerMap)) == null) {
            return null;
        }
        if (!TextUtils.isEmpty(key)) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString(META_DATA_PREFERENCE_KEYHINT, key);
        }
        try {
            return provider.call(context.getPackageName(), context.getAttributionTag(), uri.getAuthority(), method, uri.toString(), bundle);
        } catch (RemoteException e) {
            return null;
        }
    }

    private static IContentProvider getProviderFromUri(Context context, Uri uri, Map<String, IContentProvider> providerMap) {
        if (uri == null) {
            return null;
        }
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            return null;
        }
        if (!providerMap.containsKey(authority)) {
            providerMap.put(authority, context.getContentResolver().acquireUnstableProvider(uri));
        }
        return providerMap.get(authority);
    }

    private static Pair<String, String> getMethodAndKey(Uri uri) {
        List<String> pathSegments;
        if (uri == null || (pathSegments = uri.getPathSegments()) == null || pathSegments.isEmpty()) {
            return null;
        }
        String method = pathSegments.get(0);
        String key = pathSegments.size() > 1 ? pathSegments.get(1) : null;
        return Pair.create(method, key);
    }
}
