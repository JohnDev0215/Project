package com.google.android.gms.cast.framework;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.mediarouter.app.MediaRouteActionProvider;
import androidx.mediarouter.app.MediaRouteButton;
import androidx.mediarouter.app.MediaRouteDialogFactory;
import androidx.mediarouter.media.MediaRouteSelector;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzkk;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class CastButtonFactory {
    private static final Logger zza = new Logger("CastButtonFactory");
    private static final List zzb = new ArrayList();
    private static final List zzc = new ArrayList();

    private CastButtonFactory() {
    }

    @NonNull
    public static MenuItem setUpMediaRouteButton(@NonNull Context context, @NonNull Menu menu, int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        Preconditions.checkNotNull(menu);
        MenuItem menuItemFindItem = menu.findItem(i);
        if (menuItemFindItem == null) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "menu doesn't contain a menu item whose ID is %d.", Integer.valueOf(i)));
        }
        try {
            zzb(context, menuItemFindItem, null);
            zzb.add(new WeakReference(menuItemFindItem));
            com.google.android.gms.internal.cast.zzl.zzd(zzkk.CAST_DEFAULT_MEDIA_ROUTER_DIALOG);
            return menuItemFindItem;
        } catch (IllegalArgumentException unused) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "menu item with ID %d doesn't have a MediaRouteActionProvider.", Integer.valueOf(i)));
        }
    }

    public static void zza(@NonNull Context context) {
        Iterator it = zzb.iterator();
        while (it.hasNext()) {
            MenuItem menuItem = (MenuItem) ((WeakReference) it.next()).get();
            if (menuItem != null) {
                try {
                    zzb(context, menuItem, null);
                } catch (IllegalArgumentException e) {
                    zza.w("Unexpected exception when refreshing MediaRouteSelectors for Cast buttons", e);
                }
            }
        }
        Iterator it2 = zzc.iterator();
        while (it2.hasNext()) {
            MediaRouteButton mediaRouteButton = (MediaRouteButton) ((WeakReference) it2.next()).get();
            if (mediaRouteButton != null) {
                zzc(context, mediaRouteButton, null);
            }
        }
    }

    private static void zzb(Context context, @NonNull MenuItem menuItem, @Nullable MediaRouteDialogFactory mediaRouteDialogFactory) throws IllegalArgumentException {
        MediaRouteSelector mergedSelector;
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaRouteActionProvider mediaRouteActionProvider = (MediaRouteActionProvider) MenuItemCompat.getActionProvider(menuItem);
        if (mediaRouteActionProvider == null) {
            throw new IllegalArgumentException();
        }
        CastContext castContextZza = CastContext.zza(context);
        if (castContextZza == null || (mergedSelector = castContextZza.getMergedSelector()) == null) {
            return;
        }
        mediaRouteActionProvider.setRouteSelector(mergedSelector);
    }

    private static void zzc(Context context, MediaRouteButton mediaRouteButton, @Nullable MediaRouteDialogFactory mediaRouteDialogFactory) {
        MediaRouteSelector mergedSelector;
        Preconditions.checkMainThread("Must be called from the main thread.");
        CastContext castContextZza = CastContext.zza(context);
        if (castContextZza == null || (mergedSelector = castContextZza.getMergedSelector()) == null) {
            return;
        }
        mediaRouteButton.setRouteSelector(mergedSelector);
    }

    public static void setUpMediaRouteButton(@NonNull Context context, @NonNull MediaRouteButton mediaRouteButton) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (mediaRouteButton != null) {
            zzc(context, mediaRouteButton, null);
            zzc.add(new WeakReference(mediaRouteButton));
        }
        com.google.android.gms.internal.cast.zzl.zzd(zzkk.CAST_DEFAULT_MEDIA_ROUTER_DIALOG);
    }
}
