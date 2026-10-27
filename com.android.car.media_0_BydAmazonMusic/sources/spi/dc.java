package spi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: UriUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class dc {
    private dc() {
    }

    public static Intent.ShortcutIconResource a(Context context, Uri uri) {
        if ("android.resource".equals(uri.getScheme())) {
            Intent.ShortcutIconResource shortcutIconResource = new Intent.ShortcutIconResource();
            shortcutIconResource.packageName = b(context, uri);
            shortcutIconResource.resourceName = shortcutIconResource.packageName + uri.toString().substring(uri.getAuthority().length() + 19).replaceFirst("/", ":");
            return shortcutIconResource;
        }
        if ("shortcut.icon.resource".equals(uri.getScheme())) {
            Intent.ShortcutIconResource shortcutIconResource2 = new Intent.ShortcutIconResource();
            shortcutIconResource2.packageName = b(context, uri);
            shortcutIconResource2.resourceName = uri.toString().substring(uri.getAuthority().length() + 25 + 1).replaceFirst("/", ":");
            return shortcutIconResource2;
        }
        throw new IllegalArgumentException("Invalid resource URI. " + uri);
    }

    @Nullable
    public static String b(Context context, Uri uri) {
        ProviderInfo providerInfoResolveContentProvider = context.getPackageManager().resolveContentProvider(uri.getAuthority(), 131072);
        return providerInfoResolveContentProvider != null ? providerInfoResolveContentProvider.packageName : uri.getAuthority();
    }
}
