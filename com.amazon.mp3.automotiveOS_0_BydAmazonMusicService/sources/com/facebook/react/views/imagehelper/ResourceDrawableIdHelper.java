package com.facebook.react.views.imagehelper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes6.dex */
@ThreadSafe
public class ResourceDrawableIdHelper {
    private static volatile ResourceDrawableIdHelper sResourceDrawableIdHelper;
    private Map<String, Integer> mResourceDrawableIdMap = new HashMap();

    private ResourceDrawableIdHelper() {
    }

    public static ResourceDrawableIdHelper getInstance() {
        if (sResourceDrawableIdHelper == null) {
            synchronized (ResourceDrawableIdHelper.class) {
                if (sResourceDrawableIdHelper == null) {
                    sResourceDrawableIdHelper = new ResourceDrawableIdHelper();
                }
            }
        }
        return sResourceDrawableIdHelper;
    }

    public int getResourceDrawableId(Context context, @Nullable String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        String strReplace = str.toLowerCase(Locale.ROOT).replace("-", Attributes.PREDEFINED_ATTRIBUTE_PREFIX);
        try {
            return Integer.parseInt(strReplace);
        } catch (NumberFormatException unused) {
            synchronized (this) {
                if (this.mResourceDrawableIdMap.containsKey(strReplace)) {
                    return this.mResourceDrawableIdMap.get(strReplace).intValue();
                }
                int identifier = context.getResources().getIdentifier(strReplace, "drawable", context.getPackageName());
                this.mResourceDrawableIdMap.put(strReplace, Integer.valueOf(identifier));
                return identifier;
            }
        }
    }

    @Nullable
    public Drawable getResourceDrawable(Context context, @Nullable String str) {
        int resourceDrawableId = getResourceDrawableId(context, str);
        if (resourceDrawableId > 0) {
            return context.getResources().getDrawable(resourceDrawableId);
        }
        return null;
    }

    public Uri getResourceDrawableUri(Context context, @Nullable String str) {
        int resourceDrawableId = getResourceDrawableId(context, str);
        if (resourceDrawableId > 0) {
            return new Uri.Builder().scheme("res").path(String.valueOf(resourceDrawableId)).build();
        }
        return Uri.EMPTY;
    }
}
