package android.accessibilityservice;

import android.accessibilityservice.util.AccessibilityUtils;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.util.Xml;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class AccessibilityShortcutInfo {
    public static final String META_DATA = "android.accessibilityshortcut.target";
    private static final String TAG_ACCESSIBILITY_SHORTCUT = "accessibility-shortcut-target";
    private final ActivityInfo mActivityInfo;
    private final int mAnimatedImageRes;
    private final ComponentName mComponentName;
    private final int mDescriptionResId;
    private final int mHtmlDescriptionRes;
    private String mSettingsActivityName;
    private final int mSummaryResId;

    public AccessibilityShortcutInfo(Context context, ActivityInfo activityInfo) throws XmlPullParserException, IOException {
        PackageManager packageManager = context.getPackageManager();
        this.mComponentName = activityInfo.getComponentName();
        this.mActivityInfo = activityInfo;
        try {
            XmlResourceParser xmlResourceParserLoadXmlMetaData = activityInfo.loadXmlMetaData(packageManager, META_DATA);
            try {
                if (xmlResourceParserLoadXmlMetaData == null) {
                    throw new XmlPullParserException("Meta-data accessibility-shortcut-target does not exist");
                }
                for (int next = 0; next != 1 && next != 2; next = xmlResourceParserLoadXmlMetaData.next()) {
                }
                if (!TAG_ACCESSIBILITY_SHORTCUT.equals(xmlResourceParserLoadXmlMetaData.getName())) {
                    throw new XmlPullParserException("Meta-data does not start withaccessibility-shortcut-target tag");
                }
                TypedArray typedArrayObtainAttributes = packageManager.getResourcesForApplication(this.mActivityInfo.applicationInfo).obtainAttributes(Xml.asAttributeSet(xmlResourceParserLoadXmlMetaData), R.styleable.AccessibilityShortcutTarget);
                this.mDescriptionResId = typedArrayObtainAttributes.getResourceId(0, 0);
                this.mSummaryResId = typedArrayObtainAttributes.getResourceId(1, 0);
                this.mAnimatedImageRes = typedArrayObtainAttributes.getResourceId(3, 0);
                this.mHtmlDescriptionRes = typedArrayObtainAttributes.getResourceId(4, 0);
                this.mSettingsActivityName = typedArrayObtainAttributes.getString(2);
                typedArrayObtainAttributes.recycle();
                if ((this.mDescriptionResId == 0 && this.mHtmlDescriptionRes == 0) || this.mSummaryResId == 0) {
                    throw new XmlPullParserException("No description or summary in meta-data");
                }
                if (xmlResourceParserLoadXmlMetaData != null) {
                    xmlResourceParserLoadXmlMetaData.close();
                }
            } finally {
            }
        } catch (PackageManager.NameNotFoundException unused) {
            throw new XmlPullParserException("Unable to create context for: " + this.mActivityInfo.packageName);
        }
    }

    public ActivityInfo getActivityInfo() {
        return this.mActivityInfo;
    }

    public ComponentName getComponentName() {
        return this.mComponentName;
    }

    public String loadSummary(PackageManager packageManager) {
        return loadResourceString(packageManager, this.mActivityInfo, this.mSummaryResId);
    }

    public String loadDescription(PackageManager packageManager) {
        return loadResourceString(packageManager, this.mActivityInfo, this.mDescriptionResId);
    }

    public int getAnimatedImageRes() {
        return this.mAnimatedImageRes;
    }

    public Drawable loadAnimatedImage(Context context) {
        if (this.mAnimatedImageRes == 0) {
            return null;
        }
        return AccessibilityUtils.loadSafeAnimatedImage(context, this.mActivityInfo.applicationInfo, this.mAnimatedImageRes);
    }

    public String loadHtmlDescription(PackageManager packageManager) {
        String strLoadResourceString = loadResourceString(packageManager, this.mActivityInfo, this.mHtmlDescriptionRes);
        if (strLoadResourceString != null) {
            return AccessibilityUtils.getFilteredHtmlText(strLoadResourceString);
        }
        return null;
    }

    public String getSettingsActivityName() {
        return this.mSettingsActivityName;
    }

    private String loadResourceString(PackageManager packageManager, ActivityInfo activityInfo, int i) {
        CharSequence text;
        if (i == 0 || (text = packageManager.getText(activityInfo.packageName, i, activityInfo.applicationInfo)) == null) {
            return null;
        }
        return text.toString().trim();
    }

    public int hashCode() {
        ComponentName componentName = this.mComponentName;
        return (componentName == null ? 0 : componentName.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AccessibilityShortcutInfo accessibilityShortcutInfo = (AccessibilityShortcutInfo) obj;
        ComponentName componentName = this.mComponentName;
        if (componentName == null) {
            if (accessibilityShortcutInfo.mComponentName != null) {
                return false;
            }
        } else if (!componentName.equals(accessibilityShortcutInfo.mComponentName)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "AccessibilityShortcutInfo[activityInfo: " + this.mActivityInfo + "]";
    }
}
