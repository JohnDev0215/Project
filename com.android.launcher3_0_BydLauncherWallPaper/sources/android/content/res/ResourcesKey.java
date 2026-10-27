package android.content.res;

import android.content.res.loader.ResourcesLoader;
import android.telephony.SmsManager;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class ResourcesKey {
    public final CompatibilityInfo mCompatInfo;
    public final int mDisplayId;
    private final int mHash;
    public final String[] mLibDirs;
    public final ResourcesLoader[] mLoaders;
    public final String[] mOverlayDirs;
    public final Configuration mOverrideConfiguration;
    public final String mResDir;
    public final String[] mSplitResDirs;

    public ResourcesKey(String str, String[] strArr, String[] strArr2, String[] strArr3, int i, Configuration configuration, CompatibilityInfo compatibilityInfo, ResourcesLoader[] resourcesLoaderArr) {
        this.mResDir = str;
        this.mSplitResDirs = strArr;
        this.mOverlayDirs = strArr2;
        this.mLibDirs = strArr3;
        if (resourcesLoaderArr != null && resourcesLoaderArr.length == 0) {
            resourcesLoaderArr = null;
        }
        this.mLoaders = resourcesLoaderArr;
        this.mDisplayId = i;
        Configuration configuration2 = new Configuration(configuration == null ? Configuration.EMPTY : configuration);
        this.mOverrideConfiguration = configuration2;
        compatibilityInfo = compatibilityInfo == null ? CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO : compatibilityInfo;
        this.mCompatInfo = compatibilityInfo;
        this.mHash = ((((((((((((((527 + Objects.hashCode(str)) * 31) + Arrays.hashCode(strArr)) * 31) + Arrays.hashCode(strArr2)) * 31) + Arrays.hashCode(strArr3)) * 31) + i) * 31) + Objects.hashCode(configuration2)) * 31) + Objects.hashCode(compatibilityInfo)) * 31) + Arrays.hashCode(resourcesLoaderArr);
    }

    public ResourcesKey(String str, String[] strArr, String[] strArr2, String[] strArr3, int i, Configuration configuration, CompatibilityInfo compatibilityInfo) {
        this(str, strArr, strArr2, strArr3, i, configuration, compatibilityInfo, null);
    }

    public boolean hasOverrideConfiguration() {
        return !Configuration.EMPTY.equals(this.mOverrideConfiguration);
    }

    public boolean isPathReferenced(String str) {
        String str2 = this.mResDir;
        return (str2 != null && str2.startsWith(str)) || anyStartsWith(this.mSplitResDirs, str) || anyStartsWith(this.mOverlayDirs, str) || anyStartsWith(this.mLibDirs, str);
    }

    private static boolean anyStartsWith(String[] strArr, String str) {
        if (strArr != null) {
            for (String str2 : strArr) {
                if (str2 != null && str2.startsWith(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        return this.mHash;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ResourcesKey)) {
            return false;
        }
        ResourcesKey resourcesKey = (ResourcesKey) obj;
        return this.mHash == resourcesKey.mHash && Objects.equals(this.mResDir, resourcesKey.mResDir) && Arrays.equals(this.mSplitResDirs, resourcesKey.mSplitResDirs) && Arrays.equals(this.mOverlayDirs, resourcesKey.mOverlayDirs) && Arrays.equals(this.mLibDirs, resourcesKey.mLibDirs) && this.mDisplayId == resourcesKey.mDisplayId && Objects.equals(this.mOverrideConfiguration, resourcesKey.mOverrideConfiguration) && Objects.equals(this.mCompatInfo, resourcesKey.mCompatInfo) && Arrays.equals(this.mLoaders, resourcesKey.mLoaders);
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder().append("ResourcesKey{");
        sbAppend.append(" mHash=").append(Integer.toHexString(this.mHash));
        sbAppend.append(" mResDir=").append(this.mResDir);
        sbAppend.append(" mSplitDirs=[");
        String[] strArr = this.mSplitResDirs;
        if (strArr != null) {
            sbAppend.append(TextUtils.join(SmsManager.REGEX_PREFIX_DELIMITER, strArr));
        }
        sbAppend.append("]");
        sbAppend.append(" mOverlayDirs=[");
        String[] strArr2 = this.mOverlayDirs;
        if (strArr2 != null) {
            sbAppend.append(TextUtils.join(SmsManager.REGEX_PREFIX_DELIMITER, strArr2));
        }
        sbAppend.append("]");
        sbAppend.append(" mLibDirs=[");
        String[] strArr3 = this.mLibDirs;
        if (strArr3 != null) {
            sbAppend.append(TextUtils.join(SmsManager.REGEX_PREFIX_DELIMITER, strArr3));
        }
        sbAppend.append("]");
        sbAppend.append(" mDisplayId=").append(this.mDisplayId);
        sbAppend.append(" mOverrideConfig=").append(Configuration.resourceQualifierString(this.mOverrideConfiguration));
        sbAppend.append(" mCompatInfo=").append(this.mCompatInfo);
        sbAppend.append(" mLoaders=[");
        ResourcesLoader[] resourcesLoaderArr = this.mLoaders;
        if (resourcesLoaderArr != null) {
            sbAppend.append(TextUtils.join(SmsManager.REGEX_PREFIX_DELIMITER, resourcesLoaderArr));
        }
        sbAppend.append("]}");
        return sbAppend.toString();
    }
}
