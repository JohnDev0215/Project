package com.android.internal.accessibility.dialog;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.accessibilityservice.AccessibilityShortcutInfo;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.text.BidiFormatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.internal.R;
import com.android.internal.accessibility.AccessibilityShortcutController;
import com.android.internal.accessibility.util.AccessibilityUtils;
import com.android.internal.accessibility.util.ShortcutUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes4.dex */
public final class AccessibilityTargetHelper {
    private AccessibilityTargetHelper() {
    }

    public static List<AccessibilityTarget> getTargets(Context context, int i) {
        List<AccessibilityTarget> installedTargets = getInstalledTargets(context, i);
        List<String> accessibilityShortcutTargets = ((AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE)).getAccessibilityShortcutTargets(i);
        ArrayList arrayList = new ArrayList();
        for (String str : accessibilityShortcutTargets) {
            for (AccessibilityTarget accessibilityTarget : installedTargets) {
                if (!"com.android.server.accessibility.MagnificationController".contentEquals(str) && ComponentName.unflattenFromString(str).equals(ComponentName.unflattenFromString(accessibilityTarget.getId()))) {
                    arrayList.add(accessibilityTarget);
                } else if (str.contentEquals(accessibilityTarget.getId())) {
                    arrayList.add(accessibilityTarget);
                }
            }
        }
        return arrayList;
    }

    static List<AccessibilityTarget> getInstalledTargets(Context context, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(getAccessibilityFilteredTargets(context, i));
        arrayList.addAll(getWhiteListingFeatureTargets(context, i));
        return arrayList;
    }

    private static List<AccessibilityTarget> getAccessibilityFilteredTargets(Context context, int i) {
        List<AccessibilityTarget> accessibilityServiceTargets = getAccessibilityServiceTargets(context, i);
        List<AccessibilityTarget> accessibilityActivityTargets = getAccessibilityActivityTargets(context, i);
        for (final AccessibilityTarget accessibilityTarget : accessibilityActivityTargets) {
            accessibilityServiceTargets.removeIf(new Predicate() { // from class: com.android.internal.accessibility.dialog.-$$Lambda$AccessibilityTargetHelper$HYBO-owNxe0IKllO3eADO8nKGCY
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return AccessibilityTargetHelper.arePackageNameAndLabelTheSame((AccessibilityTarget) obj, accessibilityTarget);
                }
            });
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(accessibilityServiceTargets);
        arrayList.addAll(accessibilityActivityTargets);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean arePackageNameAndLabelTheSame(AccessibilityTarget accessibilityTarget, AccessibilityTarget accessibilityTarget2) {
        return ComponentName.unflattenFromString(accessibilityTarget2.getId()).getPackageName().equals(ComponentName.unflattenFromString(accessibilityTarget.getId()).getPackageName()) && accessibilityTarget2.getLabel().equals(accessibilityTarget.getLabel());
    }

    private static List<AccessibilityTarget> getAccessibilityServiceTargets(Context context, int i) {
        List<AccessibilityServiceInfo> installedAccessibilityServiceList = ((AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE)).getInstalledAccessibilityServiceList();
        if (installedAccessibilityServiceList == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(installedAccessibilityServiceList.size());
        for (AccessibilityServiceInfo accessibilityServiceInfo : installedAccessibilityServiceList) {
            int i2 = accessibilityServiceInfo.getResolveInfo().serviceInfo.applicationInfo.targetSdkVersion;
            boolean z = (accessibilityServiceInfo.flags & 256) != 0;
            if (i2 > 29 || z || i != 0) {
                arrayList.add(createAccessibilityServiceTarget(context, i, accessibilityServiceInfo));
            }
        }
        return arrayList;
    }

    private static List<AccessibilityTarget> getAccessibilityActivityTargets(Context context, int i) {
        List<AccessibilityShortcutInfo> installedAccessibilityShortcutListAsUser = ((AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE)).getInstalledAccessibilityShortcutListAsUser(context, ActivityManager.getCurrentUser());
        if (installedAccessibilityShortcutListAsUser == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(installedAccessibilityShortcutListAsUser.size());
        Iterator<AccessibilityShortcutInfo> it = installedAccessibilityShortcutListAsUser.iterator();
        while (it.hasNext()) {
            arrayList.add(new AccessibilityActivityTarget(context, i, it.next()));
        }
        return arrayList;
    }

    private static List<AccessibilityTarget> getWhiteListingFeatureTargets(Context context, int i) {
        ArrayList arrayList = new ArrayList();
        InvisibleToggleWhiteListingFeatureTarget invisibleToggleWhiteListingFeatureTarget = new InvisibleToggleWhiteListingFeatureTarget(context, i, ShortcutUtils.isShortcutContained(context, i, "com.android.server.accessibility.MagnificationController"), "com.android.server.accessibility.MagnificationController", context.getString(R.string.accessibility_magnification_chooser_text), context.getDrawable(R.drawable.ic_accessibility_magnification), Settings.Secure.ACCESSIBILITY_DISPLAY_MAGNIFICATION_NAVBAR_ENABLED);
        ToggleWhiteListingFeatureTarget toggleWhiteListingFeatureTarget = new ToggleWhiteListingFeatureTarget(context, i, ShortcutUtils.isShortcutContained(context, i, AccessibilityShortcutController.DALTONIZER_COMPONENT_NAME.flattenToString()), AccessibilityShortcutController.DALTONIZER_COMPONENT_NAME.flattenToString(), context.getString(R.string.color_correction_feature_name), context.getDrawable(R.drawable.ic_accessibility_color_correction), Settings.Secure.ACCESSIBILITY_DISPLAY_DALTONIZER_ENABLED);
        ToggleWhiteListingFeatureTarget toggleWhiteListingFeatureTarget2 = new ToggleWhiteListingFeatureTarget(context, i, ShortcutUtils.isShortcutContained(context, i, AccessibilityShortcutController.COLOR_INVERSION_COMPONENT_NAME.flattenToString()), AccessibilityShortcutController.COLOR_INVERSION_COMPONENT_NAME.flattenToString(), context.getString(R.string.color_inversion_feature_name), context.getDrawable(R.drawable.ic_accessibility_color_inversion), Settings.Secure.ACCESSIBILITY_DISPLAY_INVERSION_ENABLED);
        arrayList.add(invisibleToggleWhiteListingFeatureTarget);
        arrayList.add(toggleWhiteListingFeatureTarget);
        arrayList.add(toggleWhiteListingFeatureTarget2);
        return arrayList;
    }

    private static AccessibilityTarget createAccessibilityServiceTarget(Context context, int i, AccessibilityServiceInfo accessibilityServiceInfo) {
        int accessibilityServiceFragmentType = AccessibilityUtils.getAccessibilityServiceFragmentType(accessibilityServiceInfo);
        if (accessibilityServiceFragmentType == 0) {
            return new VolumeShortcutToggleAccessibilityServiceTarget(context, i, accessibilityServiceInfo);
        }
        if (accessibilityServiceFragmentType == 1) {
            return new InvisibleToggleAccessibilityServiceTarget(context, i, accessibilityServiceInfo);
        }
        if (accessibilityServiceFragmentType == 2) {
            return new ToggleAccessibilityServiceTarget(context, i, accessibilityServiceInfo);
        }
        throw new IllegalStateException("Unexpected fragment type");
    }

    static View createEnableDialogContentView(Context context, final AccessibilityServiceTarget accessibilityServiceTarget, final View.OnClickListener onClickListener, final View.OnClickListener onClickListener2) {
        View viewInflate = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.accessibility_enable_service_encryption_warning, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.accessibility_encryption_warning);
        if (StorageManager.isNonDefaultBlockEncrypted()) {
            textView.setText(context.getString(R.string.accessibility_enable_service_encryption_warning, getServiceName(context, accessibilityServiceTarget.getLabel())));
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
        ((ImageView) viewInflate.findViewById(R.id.accessibility_permissionDialog_icon)).setImageDrawable(accessibilityServiceTarget.getIcon());
        ((TextView) viewInflate.findViewById(R.id.accessibility_permissionDialog_title)).setText(context.getString(R.string.accessibility_enable_service_title, getServiceName(context, accessibilityServiceTarget.getLabel())));
        Button button = (Button) viewInflate.findViewById(R.id.accessibility_permission_enable_allow_button);
        Button button2 = (Button) viewInflate.findViewById(R.id.accessibility_permission_enable_deny_button);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.android.internal.accessibility.dialog.-$$Lambda$AccessibilityTargetHelper$SVJ1QfBb5fsfesctjDXeQ-RKYk4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccessibilityTargetHelper.lambda$createEnableDialogContentView$1(accessibilityServiceTarget, onClickListener, view);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.android.internal.accessibility.dialog.-$$Lambda$AccessibilityTargetHelper$NO5sqIf6nbB_56GdEGG1nLbYwQQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccessibilityTargetHelper.lambda$createEnableDialogContentView$2(accessibilityServiceTarget, onClickListener2, view);
            }
        });
        return viewInflate;
    }

    static /* synthetic */ void lambda$createEnableDialogContentView$1(AccessibilityServiceTarget accessibilityServiceTarget, View.OnClickListener onClickListener, View view) {
        accessibilityServiceTarget.onCheckedChanged(true);
        onClickListener.onClick(view);
    }

    static /* synthetic */ void lambda$createEnableDialogContentView$2(AccessibilityServiceTarget accessibilityServiceTarget, View.OnClickListener onClickListener, View view) {
        accessibilityServiceTarget.onCheckedChanged(false);
        onClickListener.onClick(view);
    }

    private static CharSequence getServiceName(Context context, CharSequence charSequence) {
        return BidiFormatter.getInstance(context.getResources().getConfiguration().getLocales().get(0)).unicodeWrap(charSequence);
    }
}
