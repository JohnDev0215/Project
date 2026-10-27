package com.android.settingslib.drawer;

import android.app.ActivityManager;
import android.content.Context;
import android.content.IContentProvider;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.VisibleForTesting;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class g {

    @VisibleForTesting
    static final String SETTING_PKG = "com.android.settings";

    private static Uri a(String str, String str2) {
        return new Uri.Builder().scheme("content").authority(str).appendPath(str2).build();
    }

    static Uri b(String str, String str2, String str3) {
        return new Uri.Builder().scheme("content").authority(str).appendPath(str2).appendPath(str3).build();
    }

    private static Bundle c(Context context, Uri uri, Map<String, IContentProvider> map, Bundle bundle) {
        IContentProvider iContentProviderE;
        Pair<String, String> pairD = d(uri);
        if (pairD == null) {
            return null;
        }
        String str = (String) pairD.first;
        String str2 = (String) pairD.second;
        if (TextUtils.isEmpty(str) || (iContentProviderE = e(context, uri, map)) == null) {
            return null;
        }
        if (!TextUtils.isEmpty(str2)) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString("com.android.settings.keyhint", str2);
        }
        try {
            return iContentProviderE.call(context.getPackageName(), context.getAttributionTag(), uri.getAuthority(), str, uri.toString(), bundle);
        } catch (RemoteException unused) {
            return null;
        }
    }

    private static Pair<String, String> d(Uri uri) {
        List<String> pathSegments;
        if (uri == null || (pathSegments = uri.getPathSegments()) == null || pathSegments.isEmpty()) {
            return null;
        }
        return Pair.create(pathSegments.get(0), pathSegments.size() > 1 ? pathSegments.get(1) : null);
    }

    private static IContentProvider e(Context context, Uri uri, Map<String, IContentProvider> map) {
        if (uri == null) {
            return null;
        }
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            return null;
        }
        if (!map.containsKey(authority)) {
            map.put(authority, context.getContentResolver().acquireUnstableProvider(uri));
        }
        return map.get(authority);
    }

    private static List<Bundle> f(Context context, String str) {
        Bundle bundleC = c(context, a(str, "getSwitchData"), new ArrayMap(), null);
        if (bundleC != null) {
            return bundleC.getParcelableArrayList("switch_data");
        }
        return null;
    }

    private static void g(Context context, UserHandle userHandle, Map<Pair<String, String>, Tile> map, String str, List<Tile> list, Intent intent) {
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivitiesAsUser(intent, 128, userHandle.getIdentifier())) {
            if (resolveInfo.system) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                i(userHandle, map, str, list, intent, activityInfo.metaData, activityInfo);
            }
        }
    }

    private static void h(Context context, UserHandle userHandle, Map<Pair<String, String>, Tile> map, String str, List<Tile> list, Intent intent) {
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentContentProvidersAsUser(intent, 0, userHandle.getIdentifier())) {
            if (resolveInfo.system) {
                ProviderInfo providerInfo = resolveInfo.providerInfo;
                List<Bundle> listF = f(context, providerInfo.authority);
                if (listF != null && !listF.isEmpty()) {
                    Iterator<Bundle> it = listF.iterator();
                    while (it.hasNext()) {
                        i(userHandle, map, str, list, intent, it.next(), providerInfo);
                    }
                }
            }
        }
    }

    private static void i(UserHandle userHandle, Map<Pair<String, String>, Tile> map, String str, List<Tile> list, Intent intent, Bundle bundle, ComponentInfo componentInfo) {
        if (userHandle.getIdentifier() != ActivityManager.getCurrentUser() && Tile.d(componentInfo.metaData)) {
            Log.w("TileUtils", "Found " + componentInfo.name + " for intent " + intent + " is primary profile only, skip loading tile for uid " + userHandle.getIdentifier());
            return;
        }
        if ((bundle == null || !bundle.containsKey("com.android.settings.category")) && str == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Found ");
            sb.append(componentInfo.name);
            sb.append(" for intent ");
            sb.append(intent);
            sb.append(" missing metadata ");
            sb.append(bundle == null ? "" : "com.android.settings.category");
            Log.w("TileUtils", sb.toString());
            return;
        }
        String string = bundle.getString("com.android.settings.category");
        boolean z = componentInfo instanceof ProviderInfo;
        Pair<String, String> pair = z ? new Pair<>(((ProviderInfo) componentInfo).authority, bundle.getString("com.android.settings.keyhint")) : new Pair<>(componentInfo.packageName, componentInfo.name);
        Tile providerTile = map.get(pair);
        if (providerTile == null) {
            providerTile = z ? new ProviderTile((ProviderInfo) componentInfo, string, bundle) : new ActivityTile((ActivityInfo) componentInfo, string);
            map.put(pair, providerTile);
        } else {
            providerTile.f(bundle);
        }
        if (!providerTile.f1352c.contains(userHandle)) {
            providerTile.f1352c.add(userHandle);
        }
        if (list.contains(providerTile)) {
            return;
        }
        list.add(providerTile);
    }

    @VisibleForTesting
    static void loadTilesForAction(Context context, UserHandle userHandle, String str, Map<Pair<String, String>, Tile> map, String str2, List<Tile> list, boolean z) {
        Intent intent = new Intent(str);
        if (z) {
            intent.setPackage(SETTING_PKG);
        }
        g(context, userHandle, map, str2, list, intent);
        h(context, userHandle, map, str2, list, intent);
    }
}
