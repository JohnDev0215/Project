package androidx.slice.compat;

import android.annotation.SuppressLint;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.util.Preconditions;
import androidx.slice.Slice;
import androidx.slice.SliceItemHolder;
import androidx.slice.SliceSpec;
import androidx.versionedparcelable.ParcelUtils;
import androidx.versionedparcelable.VersionedParcelable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class SliceProviderCompat {
    public static Slice bindSlice(Context context, Uri uri, Set<SliceSpec> set) {
        ProviderHolder providerHolderAcquireClient = acquireClient(context.getContentResolver(), uri);
        if (providerHolderAcquireClient.mProvider == null) {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putParcelable("slice_uri", uri);
            addSpecs(bundle, set);
            return parseSlice(context, providerHolderAcquireClient.mProvider.call("bind_slice", "supports_versioned_parcelable", bundle));
        } catch (RemoteException e) {
            Log.e("SliceProviderCompat", "Unable to bind slice", e);
            return null;
        } finally {
            providerHolderAcquireClient.close();
        }
    }

    public static void addSpecs(Bundle bundle, Set<SliceSpec> set) {
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        for (SliceSpec sliceSpec : set) {
            arrayList.add(sliceSpec.getType());
            arrayList2.add(Integer.valueOf(sliceSpec.getRevision()));
        }
        bundle.putStringArrayList("specs", arrayList);
        bundle.putIntegerArrayList("revs", arrayList2);
    }

    public static Slice bindSlice(Context context, Intent intent, Set<SliceSpec> set) {
        ActivityInfo activityInfo;
        Bundle bundle;
        Preconditions.checkNotNull(intent, "intent");
        Preconditions.checkArgument((intent.getComponent() == null && intent.getPackage() == null && intent.getData() == null) ? false : true, String.format("Slice intent must be explicit %s", intent));
        ContentResolver contentResolver = context.getContentResolver();
        Uri data = intent.getData();
        if (data != null && "vnd.android.slice".equals(contentResolver.getType(data))) {
            return bindSlice(context, data, set);
        }
        Intent intent2 = new Intent(intent);
        if (!intent2.hasCategory("android.app.slice.category.SLICE")) {
            intent2.addCategory("android.app.slice.category.SLICE");
        }
        List<ResolveInfo> listQueryIntentContentProviders = context.getPackageManager().queryIntentContentProviders(intent2, 0);
        if (listQueryIntentContentProviders == null || listQueryIntentContentProviders.isEmpty()) {
            ResolveInfo resolveInfoResolveActivity = context.getPackageManager().resolveActivity(intent, 128);
            if (resolveInfoResolveActivity == null || (activityInfo = resolveInfoResolveActivity.activityInfo) == null || (bundle = activityInfo.metaData) == null || !bundle.containsKey("android.metadata.SLICE_URI")) {
                return null;
            }
            return bindSlice(context, Uri.parse(resolveInfoResolveActivity.activityInfo.metaData.getString("android.metadata.SLICE_URI")), set);
        }
        Uri uriBuild = new Uri.Builder().scheme("content").authority(listQueryIntentContentProviders.get(0).providerInfo.authority).build();
        ProviderHolder providerHolderAcquireClient = acquireClient(contentResolver, uriBuild);
        if (providerHolderAcquireClient.mProvider == null) {
            throw new IllegalArgumentException("Unknown URI " + uriBuild);
        }
        try {
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("slice_intent", intent);
            addSpecs(bundle2, set);
            return parseSlice(context, providerHolderAcquireClient.mProvider.call("map_slice", "supports_versioned_parcelable", bundle2));
        } catch (RemoteException e) {
            Log.e("SliceProviderCompat", "Unable to bind slice", e);
            return null;
        } finally {
            providerHolderAcquireClient.close();
        }
    }

    @SuppressLint({"WrongConstant"})
    private static Slice parseSlice(final Context context, Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        synchronized (SliceItemHolder.sSerializeLock) {
            try {
                SliceItemHolder.sHandler = new SliceItemHolder.HolderHandler() { // from class: androidx.slice.compat.SliceProviderCompat.2
                    @Override // androidx.slice.SliceItemHolder.HolderHandler
                    public void handle(SliceItemHolder sliceItemHolder, String str) {
                        VersionedParcelable versionedParcelable = sliceItemHolder.mVersionedParcelable;
                        if (versionedParcelable instanceof IconCompat) {
                            IconCompat iconCompat = (IconCompat) versionedParcelable;
                            iconCompat.checkResource(context);
                            if (iconCompat.getType() == 2 && iconCompat.getResId() == 0) {
                                sliceItemHolder.mVersionedParcelable = null;
                            }
                        }
                    }
                };
                bundle.setClassLoader(SliceProviderCompat.class.getClassLoader());
                Parcelable parcelable = bundle.getParcelable("slice");
                if (parcelable == null) {
                    return null;
                }
                if (parcelable instanceof Bundle) {
                    return new Slice((Bundle) parcelable);
                }
                return (Slice) ParcelUtils.fromParcelable(parcelable);
            } finally {
                SliceItemHolder.sHandler = null;
            }
        }
    }

    public static void pinSlice(Context context, Uri uri, Set<SliceSpec> set) {
        ProviderHolder providerHolderAcquireClient = acquireClient(context.getContentResolver(), uri);
        if (providerHolderAcquireClient.mProvider == null) {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
        try {
            try {
                Bundle bundle = new Bundle();
                bundle.putParcelable("slice_uri", uri);
                bundle.putString("pkg", context.getPackageName());
                addSpecs(bundle, set);
                providerHolderAcquireClient.mProvider.call("pin_slice", "supports_versioned_parcelable", bundle);
            } catch (RemoteException e) {
                Log.e("SliceProviderCompat", "Unable to pin slice", e);
            }
        } finally {
            providerHolderAcquireClient.close();
        }
    }

    public static void unpinSlice(Context context, Uri uri, Set<SliceSpec> set) {
        ProviderHolder providerHolderAcquireClient = acquireClient(context.getContentResolver(), uri);
        if (providerHolderAcquireClient.mProvider == null) {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
        try {
            try {
                Bundle bundle = new Bundle();
                bundle.putParcelable("slice_uri", uri);
                bundle.putString("pkg", context.getPackageName());
                addSpecs(bundle, set);
                providerHolderAcquireClient.mProvider.call("unpin_slice", "supports_versioned_parcelable", bundle);
            } catch (RemoteException e) {
                Log.e("SliceProviderCompat", "Unable to unpin slice", e);
            }
        } finally {
            providerHolderAcquireClient.close();
        }
    }

    public static void grantSlicePermission(Context context, String str, String str2, Uri uri) {
        try {
            ProviderHolder providerHolderAcquireClient = acquireClient(context.getContentResolver(), uri);
            try {
                Bundle bundle = new Bundle();
                bundle.putParcelable("slice_uri", uri);
                bundle.putString("provider_pkg", str);
                bundle.putString("pkg", str2);
                providerHolderAcquireClient.mProvider.call("grant_perms", "supports_versioned_parcelable", bundle);
                if (providerHolderAcquireClient != null) {
                    providerHolderAcquireClient.close();
                }
            } finally {
            }
        } catch (RemoteException e) {
            Log.e("SliceProviderCompat", "Unable to get slice descendants", e);
        }
    }

    private static ProviderHolder acquireClient(ContentResolver contentResolver, Uri uri) {
        ContentProviderClient contentProviderClientAcquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient(uri);
        if (contentProviderClientAcquireUnstableContentProviderClient == null) {
            throw new IllegalArgumentException("No provider found for " + uri);
        }
        return new ProviderHolder(contentProviderClientAcquireUnstableContentProviderClient);
    }

    private static class ProviderHolder implements AutoCloseable {
        final ContentProviderClient mProvider;

        ProviderHolder(ContentProviderClient contentProviderClient) {
            this.mProvider = contentProviderClient;
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            ContentProviderClient contentProviderClient = this.mProvider;
            if (contentProviderClient == null) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 24) {
                contentProviderClient.close();
            } else {
                contentProviderClient.release();
            }
        }
    }
}
