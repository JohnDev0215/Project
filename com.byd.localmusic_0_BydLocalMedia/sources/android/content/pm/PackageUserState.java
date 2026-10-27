package android.content.pm;

import android.content.ComponentName;
import android.content.pm.parsing.component.ParsedMainComponent;
import android.os.BaseBundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Pair;
import android.util.Slog;
import com.android.internal.util.ArrayUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes.dex */
public class PackageUserState {
    private static final boolean DEBUG = false;
    private static final String LOG_TAG = "PackageUserState";
    public int appLinkGeneration;
    private String[] cachedOverlayPaths;
    public int categoryHint;
    public long ceDataInode;
    private ArrayMap<ComponentName, Pair<String, Integer>> componentLabelIconOverrideMap;
    public ArraySet<String> disabledComponents;
    public int distractionFlags;
    public int domainVerificationStatus;
    public int enabled;
    public ArraySet<String> enabledComponents;
    public String harmfulAppWarning;
    public boolean hidden;
    public int installReason;
    public boolean installed;
    public boolean instantApp;
    public String lastDisableAppCaller;
    public boolean notLaunched;
    private String[] overlayPaths;
    private ArrayMap<String, String[]> sharedLibraryOverlayPaths;
    public boolean stopped;
    public ArrayMap<String, SuspendParams> suspendParams;
    public boolean suspended;
    public int uninstallReason;
    public boolean virtualPreload;

    public boolean reportIfDebug(boolean z, int i) {
        return z;
    }

    public PackageUserState() {
        this.categoryHint = -1;
        this.installed = true;
        this.hidden = false;
        this.suspended = false;
        this.enabled = 0;
        this.domainVerificationStatus = 0;
        this.installReason = 0;
        this.uninstallReason = 0;
    }

    public PackageUserState(PackageUserState packageUserState) {
        this.categoryHint = -1;
        this.ceDataInode = packageUserState.ceDataInode;
        this.installed = packageUserState.installed;
        this.stopped = packageUserState.stopped;
        this.notLaunched = packageUserState.notLaunched;
        this.hidden = packageUserState.hidden;
        this.distractionFlags = packageUserState.distractionFlags;
        this.suspended = packageUserState.suspended;
        this.suspendParams = new ArrayMap<>(packageUserState.suspendParams);
        this.instantApp = packageUserState.instantApp;
        this.virtualPreload = packageUserState.virtualPreload;
        this.enabled = packageUserState.enabled;
        this.lastDisableAppCaller = packageUserState.lastDisableAppCaller;
        this.domainVerificationStatus = packageUserState.domainVerificationStatus;
        this.appLinkGeneration = packageUserState.appLinkGeneration;
        this.categoryHint = packageUserState.categoryHint;
        this.installReason = packageUserState.installReason;
        this.uninstallReason = packageUserState.uninstallReason;
        this.disabledComponents = ArrayUtils.cloneOrNull(packageUserState.disabledComponents);
        this.enabledComponents = ArrayUtils.cloneOrNull(packageUserState.enabledComponents);
        String[] strArr = packageUserState.overlayPaths;
        this.overlayPaths = strArr == null ? null : (String[]) Arrays.copyOf(strArr, strArr.length);
        if (packageUserState.sharedLibraryOverlayPaths != null) {
            this.sharedLibraryOverlayPaths = new ArrayMap<>(packageUserState.sharedLibraryOverlayPaths);
        }
        this.harmfulAppWarning = packageUserState.harmfulAppWarning;
        if (packageUserState.componentLabelIconOverrideMap != null) {
            this.componentLabelIconOverrideMap = new ArrayMap<>(packageUserState.componentLabelIconOverrideMap);
        }
    }

    public String[] getOverlayPaths() {
        return this.overlayPaths;
    }

    public void setOverlayPaths(String[] strArr) {
        this.overlayPaths = strArr;
        this.cachedOverlayPaths = null;
    }

    public Map<String, String[]> getSharedLibraryOverlayPaths() {
        return this.sharedLibraryOverlayPaths;
    }

    public void setSharedLibraryOverlayPaths(String str, String[] strArr) {
        if (this.sharedLibraryOverlayPaths == null) {
            this.sharedLibraryOverlayPaths = new ArrayMap<>();
        }
        this.sharedLibraryOverlayPaths.put(str, strArr);
        this.cachedOverlayPaths = null;
    }

    public boolean overrideLabelAndIcon(ComponentName componentName, String str, Integer num) {
        Integer num2;
        String str2;
        Pair<String, Integer> pair;
        ArrayMap<ComponentName, Pair<String, Integer>> arrayMap = this.componentLabelIconOverrideMap;
        if (arrayMap == null || (pair = arrayMap.get(componentName)) == null) {
            num2 = null;
            str2 = null;
        } else {
            str2 = pair.first;
            num2 = pair.second;
        }
        boolean z = (TextUtils.equals(str2, str) && Objects.equals(num2, num)) ? false : true;
        if (z) {
            if (str == null && num == null) {
                this.componentLabelIconOverrideMap.remove(componentName);
                if (this.componentLabelIconOverrideMap.isEmpty()) {
                    this.componentLabelIconOverrideMap = null;
                }
            } else {
                if (this.componentLabelIconOverrideMap == null) {
                    this.componentLabelIconOverrideMap = new ArrayMap<>(1);
                }
                this.componentLabelIconOverrideMap.put(componentName, Pair.create(str, num));
            }
        }
        return z;
    }

    public void resetOverrideComponentLabelIcon() {
        this.componentLabelIconOverrideMap = null;
    }

    public Pair<String, Integer> getOverrideLabelIconForComponent(ComponentName componentName) {
        if (ArrayUtils.isEmpty(this.componentLabelIconOverrideMap)) {
            return null;
        }
        return this.componentLabelIconOverrideMap.get(componentName);
    }

    public boolean isAvailable(int i) {
        boolean z = (4194304 & i) != 0;
        boolean z2 = (i & 8192) != 0;
        if (z) {
            return true;
        }
        return this.installed && (!this.hidden || z2);
    }

    public boolean isMatch(ComponentInfo componentInfo, int i) {
        return isMatch(componentInfo.applicationInfo.isSystemApp(), componentInfo.applicationInfo.enabled, componentInfo.enabled, componentInfo.directBootAware, componentInfo.name, i);
    }

    public boolean isMatch(boolean z, boolean z2, ParsedMainComponent parsedMainComponent, int i) {
        return isMatch(z, z2, parsedMainComponent.isEnabled(), parsedMainComponent.isDirectBootAware(), parsedMainComponent.getName(), i);
    }

    public boolean isMatch(boolean z, boolean z2, boolean z3, boolean z4, String str, int i) {
        boolean z5 = true;
        boolean z6 = (4202496 & i) != 0;
        if (!isAvailable(i) && (!z || !z6)) {
            return reportIfDebug(false, i);
        }
        if (!isEnabled(z2, z3, str, i)) {
            return reportIfDebug(false, i);
        }
        if ((1048576 & i) != 0 && !z) {
            return reportIfDebug(false, i);
        }
        boolean z7 = ((262144 & i) == 0 || z4) ? false : true;
        boolean z8 = (524288 & i) != 0 && z4;
        if (!z7 && !z8) {
            z5 = false;
        }
        return reportIfDebug(z5, i);
    }

    public boolean isEnabled(ComponentInfo componentInfo, int i) {
        return isEnabled(componentInfo.applicationInfo.enabled, componentInfo.enabled, componentInfo.name, i);
    }

    public boolean isEnabled(boolean z, ParsedMainComponent parsedMainComponent, int i) {
        return isEnabled(z, parsedMainComponent.isEnabled(), parsedMainComponent.getName(), i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0019, code lost:
    
        if ((r8 & 32768) == 0) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isEnabled(boolean r5, boolean r6, java.lang.String r7, int r8) {
        /*
            r4 = this;
            r0 = r8 & 512(0x200, float:7.17E-43)
            r1 = 1
            if (r0 == 0) goto L6
            return r1
        L6:
            int r0 = r4.enabled
            r2 = 0
            if (r0 == 0) goto L1c
            r3 = 2
            if (r0 == r3) goto L1b
            r3 = 3
            if (r0 == r3) goto L1b
            r3 = 4
            if (r0 == r3) goto L15
            goto L1f
        L15:
            r0 = 32768(0x8000, float:4.5918E-41)
            r8 = r8 & r0
            if (r8 != 0) goto L1c
        L1b:
            return r2
        L1c:
            if (r5 != 0) goto L1f
            return r2
        L1f:
            android.util.ArraySet<java.lang.String> r5 = r4.enabledComponents
            boolean r5 = com.android.internal.util.ArrayUtils.contains(r5, r7)
            if (r5 == 0) goto L28
            return r1
        L28:
            android.util.ArraySet<java.lang.String> r5 = r4.disabledComponents
            boolean r5 = com.android.internal.util.ArrayUtils.contains(r5, r7)
            if (r5 == 0) goto L31
            return r2
        L31:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageUserState.isEnabled(boolean, boolean, java.lang.String, int):boolean");
    }

    public String[] getAllOverlayPaths() {
        if (this.overlayPaths == null && this.sharedLibraryOverlayPaths == null) {
            return null;
        }
        String[] strArr = this.cachedOverlayPaths;
        if (strArr != null) {
            return strArr;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        String[] strArr2 = this.overlayPaths;
        if (strArr2 != null) {
            int length = strArr2.length;
            for (int i = 0; i < length; i++) {
                linkedHashSet.add(this.overlayPaths[i]);
            }
        }
        ArrayMap<String, String[]> arrayMap = this.sharedLibraryOverlayPaths;
        if (arrayMap != null) {
            for (String[] strArr3 : arrayMap.values()) {
                if (strArr3 != null) {
                    for (String str : strArr3) {
                        linkedHashSet.add(str);
                    }
                }
            }
        }
        String[] strArr4 = (String[]) linkedHashSet.toArray(new String[0]);
        this.cachedOverlayPaths = strArr4;
        return strArr4;
    }

    public final boolean equals(Object obj) {
        boolean z;
        String str;
        String str2;
        if (!(obj instanceof PackageUserState)) {
            return false;
        }
        PackageUserState packageUserState = (PackageUserState) obj;
        if (this.ceDataInode != packageUserState.ceDataInode || this.installed != packageUserState.installed || this.stopped != packageUserState.stopped || this.notLaunched != packageUserState.notLaunched || this.hidden != packageUserState.hidden || this.distractionFlags != packageUserState.distractionFlags || (z = this.suspended) != packageUserState.suspended) {
            return false;
        }
        if ((z && !Objects.equals(this.suspendParams, packageUserState.suspendParams)) || this.instantApp != packageUserState.instantApp || this.virtualPreload != packageUserState.virtualPreload || this.enabled != packageUserState.enabled) {
            return false;
        }
        if ((this.lastDisableAppCaller == null && packageUserState.lastDisableAppCaller != null) || (((str = this.lastDisableAppCaller) != null && !str.equals(packageUserState.lastDisableAppCaller)) || this.domainVerificationStatus != packageUserState.domainVerificationStatus || this.appLinkGeneration != packageUserState.appLinkGeneration || this.categoryHint != packageUserState.categoryHint || this.installReason != packageUserState.installReason || this.uninstallReason != packageUserState.uninstallReason)) {
            return false;
        }
        if ((this.disabledComponents == null && packageUserState.disabledComponents != null) || (this.disabledComponents != null && packageUserState.disabledComponents == null)) {
            return false;
        }
        ArraySet<String> arraySet = this.disabledComponents;
        if (arraySet != null) {
            if (arraySet.size() != packageUserState.disabledComponents.size()) {
                return false;
            }
            for (int size = this.disabledComponents.size() - 1; size >= 0; size--) {
                if (!packageUserState.disabledComponents.contains(this.disabledComponents.valueAt(size))) {
                    return false;
                }
            }
        }
        if ((this.enabledComponents == null && packageUserState.enabledComponents != null) || (this.enabledComponents != null && packageUserState.enabledComponents == null)) {
            return false;
        }
        ArraySet<String> arraySet2 = this.enabledComponents;
        if (arraySet2 != null) {
            if (arraySet2.size() != packageUserState.enabledComponents.size()) {
                return false;
            }
            for (int size2 = this.enabledComponents.size() - 1; size2 >= 0; size2--) {
                if (!packageUserState.enabledComponents.contains(this.enabledComponents.valueAt(size2))) {
                    return false;
                }
            }
        }
        return (this.harmfulAppWarning != null || packageUserState.harmfulAppWarning == null) && ((str2 = this.harmfulAppWarning) == null || str2.equals(packageUserState.harmfulAppWarning));
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((Long.hashCode(this.ceDataInode) * 31) + Boolean.hashCode(this.installed)) * 31) + Boolean.hashCode(this.stopped)) * 31) + Boolean.hashCode(this.notLaunched)) * 31) + Boolean.hashCode(this.hidden)) * 31) + this.distractionFlags) * 31) + Boolean.hashCode(this.suspended)) * 31) + Objects.hashCode(this.suspendParams)) * 31) + Boolean.hashCode(this.instantApp)) * 31) + Boolean.hashCode(this.virtualPreload)) * 31) + this.enabled) * 31) + Objects.hashCode(this.lastDisableAppCaller)) * 31) + this.domainVerificationStatus) * 31) + this.appLinkGeneration) * 31) + this.categoryHint) * 31) + this.installReason) * 31) + this.uninstallReason) * 31) + Objects.hashCode(this.disabledComponents)) * 31) + Objects.hashCode(this.enabledComponents)) * 31) + Objects.hashCode(this.harmfulAppWarning);
    }

    public static final class SuspendParams {
        private static final String TAG_APP_EXTRAS = "app-extras";
        private static final String TAG_DIALOG_INFO = "dialog-info";
        private static final String TAG_LAUNCHER_EXTRAS = "launcher-extras";
        public PersistableBundle appExtras;
        public SuspendDialogInfo dialogInfo;
        public PersistableBundle launcherExtras;

        private SuspendParams() {
        }

        public static SuspendParams getInstanceOrNull(SuspendDialogInfo suspendDialogInfo, PersistableBundle persistableBundle, PersistableBundle persistableBundle2) {
            if (suspendDialogInfo == null && persistableBundle == null && persistableBundle2 == null) {
                return null;
            }
            SuspendParams suspendParams = new SuspendParams();
            suspendParams.dialogInfo = suspendDialogInfo;
            suspendParams.appExtras = persistableBundle;
            suspendParams.launcherExtras = persistableBundle2;
            return suspendParams;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SuspendParams)) {
                return false;
            }
            SuspendParams suspendParams = (SuspendParams) obj;
            return Objects.equals(this.dialogInfo, suspendParams.dialogInfo) && BaseBundle.kindofEquals(this.appExtras, suspendParams.appExtras) && BaseBundle.kindofEquals(this.launcherExtras, suspendParams.launcherExtras);
        }

        public int hashCode() {
            int iHashCode = Objects.hashCode(this.dialogInfo) * 31;
            PersistableBundle persistableBundle = this.appExtras;
            int size = (iHashCode + (persistableBundle != null ? persistableBundle.size() : 0)) * 31;
            PersistableBundle persistableBundle2 = this.launcherExtras;
            return size + (persistableBundle2 != null ? persistableBundle2.size() : 0);
        }

        public void saveToXml(XmlSerializer xmlSerializer) throws IOException {
            if (this.dialogInfo != null) {
                xmlSerializer.startTag(null, TAG_DIALOG_INFO);
                this.dialogInfo.saveToXml(xmlSerializer);
                xmlSerializer.endTag(null, TAG_DIALOG_INFO);
            }
            if (this.appExtras != null) {
                xmlSerializer.startTag(null, TAG_APP_EXTRAS);
                try {
                    this.appExtras.saveToXml(xmlSerializer);
                } catch (XmlPullParserException e) {
                    Slog.e(PackageUserState.LOG_TAG, "Exception while trying to write appExtras. Will be lost on reboot", e);
                }
                xmlSerializer.endTag(null, TAG_APP_EXTRAS);
            }
            if (this.launcherExtras != null) {
                xmlSerializer.startTag(null, TAG_LAUNCHER_EXTRAS);
                try {
                    this.launcherExtras.saveToXml(xmlSerializer);
                } catch (XmlPullParserException e2) {
                    Slog.e(PackageUserState.LOG_TAG, "Exception while trying to write launcherExtras. Will be lost on reboot", e2);
                }
                xmlSerializer.endTag(null, TAG_LAUNCHER_EXTRAS);
            }
        }

        public static SuspendParams restoreFromXml(XmlPullParser xmlPullParser) throws IOException {
            int depth = xmlPullParser.getDepth();
            SuspendDialogInfo suspendDialogInfoRestoreFromXml = null;
            PersistableBundle persistableBundleRestoreFromXml = null;
            PersistableBundle persistableBundleRestoreFromXml2 = null;
            while (true) {
                try {
                    int next = xmlPullParser.next();
                    if (next == 1 || (next == 3 && xmlPullParser.getDepth() <= depth)) {
                        break;
                    }
                    if (next != 3 && next != 4) {
                        String name = xmlPullParser.getName();
                        byte b = -1;
                        int iHashCode = name.hashCode();
                        if (iHashCode != -538220657) {
                            if (iHashCode != -22768109) {
                                if (iHashCode == 1627485488 && name.equals(TAG_LAUNCHER_EXTRAS)) {
                                    b = 2;
                                }
                            } else if (name.equals(TAG_DIALOG_INFO)) {
                                b = 0;
                            }
                        } else if (name.equals(TAG_APP_EXTRAS)) {
                            b = 1;
                        }
                        if (b == 0) {
                            suspendDialogInfoRestoreFromXml = SuspendDialogInfo.restoreFromXml(xmlPullParser);
                        } else if (b == 1) {
                            persistableBundleRestoreFromXml = PersistableBundle.restoreFromXml(xmlPullParser);
                        } else if (b == 2) {
                            persistableBundleRestoreFromXml2 = PersistableBundle.restoreFromXml(xmlPullParser);
                        } else {
                            Slog.w(PackageUserState.LOG_TAG, "Unknown tag " + xmlPullParser.getName() + " in SuspendParams. Ignoring");
                        }
                    }
                } catch (XmlPullParserException e) {
                    Slog.e(PackageUserState.LOG_TAG, "Exception while trying to parse SuspendParams, some fields may default", e);
                }
            }
            return getInstanceOrNull(suspendDialogInfoRestoreFromXml, persistableBundleRestoreFromXml, persistableBundleRestoreFromXml2);
        }
    }
}
