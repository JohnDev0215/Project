package com.amazon.mp3.net.cirrus;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.mp3.net.cache.MapTimeoutCache;
import com.amazon.mp3.net.cache.SQLiteTimeoutCache;
import com.amazon.mp3.net.cirrus.AlbumArtworkUrlRequest;
import com.amazon.mp3.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class AlbumArtworkUrlCache {
    private static final String TAG = "AlbumArtworkUrlCache";
    private static AlbumArtworkUrlCache sCache;
    private final Context mContext;
    private SQLiteTimeoutCache mHeavyCache;
    private MapTimeoutCache<String, AlbumArtworkUrlRequest.AlbumMetadata> mLightCache;

    public enum AlbumArtworkUrlCacheType {
        LIGHT,
        HEAVY
    }

    public static synchronized AlbumArtworkUrlCache getCache(Context context) {
        if (sCache == null) {
            sCache = new AlbumArtworkUrlCache(context);
        }
        return sCache;
    }

    private AlbumArtworkUrlCache(Context context) {
        this.mContext = context.getApplicationContext();
    }

    /* JADX INFO: renamed from: com.amazon.mp3.net.cirrus.AlbumArtworkUrlCache$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$net$cirrus$AlbumArtworkUrlCache$AlbumArtworkUrlCacheType;

        static {
            int[] iArr = new int[AlbumArtworkUrlCacheType.values().length];
            $SwitchMap$com$amazon$mp3$net$cirrus$AlbumArtworkUrlCache$AlbumArtworkUrlCacheType = iArr;
            try {
                iArr[AlbumArtworkUrlCacheType.HEAVY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$net$cirrus$AlbumArtworkUrlCache$AlbumArtworkUrlCacheType[AlbumArtworkUrlCacheType.LIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void createCacheIfNecessary(AlbumArtworkUrlCacheType type) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$net$cirrus$AlbumArtworkUrlCache$AlbumArtworkUrlCacheType[type.ordinal()];
        if (i == 1) {
            if (this.mHeavyCache == null) {
                this.mHeavyCache = new SQLiteTimeoutCache(this.mContext, "AlbumArtworkUrlCache.db", "Urls", true, 600000L);
            }
        } else {
            if (i == 2) {
                if (this.mLightCache == null) {
                    this.mLightCache = new MapTimeoutCache<>(600000L);
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("Unknown Cache Type");
        }
    }

    public void put(AlbumArtworkUrlCacheType type, String albumId, AlbumArtworkUrlRequest.AlbumMetadata object) {
        createCacheIfNecessary(type);
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$net$cirrus$AlbumArtworkUrlCache$AlbumArtworkUrlCacheType[type.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return;
            }
            this.mLightCache.put(albumId, object);
        } else {
            try {
                this.mHeavyCache.put(albumId, object.toJSON().toString());
            } catch (JSONException e) {
                Log.warning(TAG, "Unable to add album to url cache", e);
            }
        }
    }

    public AlbumArtworkUrlRequest.AlbumMetadata get(String albumId) {
        SQLiteTimeoutCache sQLiteTimeoutCache;
        if (albumId == null) {
            Log.error(TAG, "Failed to retrieve AlbumMetadata as albumId is null");
            return null;
        }
        MapTimeoutCache<String, AlbumArtworkUrlRequest.AlbumMetadata> mapTimeoutCache = this.mLightCache;
        AlbumArtworkUrlRequest.AlbumMetadata albumMetadata = mapTimeoutCache != null ? mapTimeoutCache.get(albumId) : null;
        if (albumMetadata != null || (sQLiteTimeoutCache = this.mHeavyCache) == null) {
            return albumMetadata;
        }
        try {
            String str = sQLiteTimeoutCache.get(albumId);
            return !TextUtils.isEmpty(str) ? AlbumArtworkUrlRequest.AlbumMetadata.fromJSON(new JSONObject(str)) : albumMetadata;
        } catch (JSONException e) {
            Log.warning(TAG, "Unable to retrieve album from url cache", e);
            return albumMetadata;
        }
    }
}
