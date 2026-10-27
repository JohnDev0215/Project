package org.chromium.ui.base;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import java.io.File;
import java.util.Locale;
import org.chromium.base.ResourceExtractor;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes4.dex */
@JNINamespace("ui")
public class ResourceBundle {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static ResourceExtractor.ResourceEntry[] sActiveLocaleResources;

    private static String toChromeLocaleName(String str) {
        String strReplace = str.replace(".lpak", ".pak");
        String[] strArrSplit = strReplace.split("_");
        if (strArrSplit.length <= 1) {
            return strReplace;
        }
        int iIndexOf = strArrSplit[1].indexOf(46);
        return strArrSplit[0] + "-" + strArrSplit[1].substring(0, iIndexOf).toUpperCase(Locale.ENGLISH) + strArrSplit[1].substring(iIndexOf);
    }

    public static void initializeLocalePaks(Context context, int i) {
        ThreadUtils.assertOnUiThread();
        Resources resources = context.getResources();
        TypedArray typedArrayObtainTypedArray = resources.obtainTypedArray(i);
        try {
            int length = typedArrayObtainTypedArray.length();
            sActiveLocaleResources = new ResourceExtractor.ResourceEntry[length];
            for (int i2 = 0; i2 < length; i2++) {
                int resourceId = typedArrayObtainTypedArray.getResourceId(i2, 0);
                String string = resources.getString(resourceId);
                sActiveLocaleResources[i2] = new ResourceExtractor.ResourceEntry(resourceId, string, toChromeLocaleName(new File(string).getName()));
            }
        } finally {
            typedArrayObtainTypedArray.recycle();
        }
    }

    public static ResourceExtractor.ResourceEntry[] getActiveLocaleResources() {
        return sActiveLocaleResources;
    }

    private static String getLocalePakResourcePath(String str) {
        if (sActiveLocaleResources == null) {
            return null;
        }
        String str2 = str + ".pak";
        for (ResourceExtractor.ResourceEntry resourceEntry : sActiveLocaleResources) {
            if (str2.equals(resourceEntry.extractedFileName)) {
                return resourceEntry.pathWithinApk;
            }
        }
        return null;
    }
}
