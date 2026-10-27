package com.android.permissioncontroller.permission.ui;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.text.BidiFormatter;
import androidx.fragment.app.FragmentActivity;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public final class ReviewAccessibilityServicesActivity extends FragmentActivity {
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AccessibilityManager accessibilityManager = (AccessibilityManager) getSystemService(AccessibilityManager.class);
        final List<AccessibilityServiceInfo> services = accessibilityManager.getEnabledAccessibilityServiceList(-1);
        new AlertDialog.Builder(this).setView(createDialogView(services)).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null).setNeutralButton(R.string.settings, new DialogInterface.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.-$$Lambda$ReviewAccessibilityServicesActivity$91DNWpJsfa1u6Z-V2hb-iwqUg-Y
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onCreate$0$ReviewAccessibilityServicesActivity(services, dialogInterface, i);
            }
        }).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.permissioncontroller.permission.ui.-$$Lambda$ReviewAccessibilityServicesActivity$6Yy9qyNdAKVAWBTRydjN7ifyVXw
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f$0.lambda$onCreate$1$ReviewAccessibilityServicesActivity(dialogInterface);
            }
        }).show();
    }

    public /* synthetic */ void lambda$onCreate$0$ReviewAccessibilityServicesActivity(List services, DialogInterface dialog, int which) {
        if (services.size() == 1) {
            startAccessibilityScreen(((AccessibilityServiceInfo) services.get(0)).getResolveInfo().serviceInfo);
        } else {
            startActivity(new Intent("android.settings.ACCESSIBILITY_SETTINGS"));
        }
    }

    public /* synthetic */ void lambda$onCreate$1$ReviewAccessibilityServicesActivity(DialogInterface dialog) {
        finish();
    }

    private View createDialogView(List<AccessibilityServiceInfo> services) {
        AppOpsManager appOpsManager;
        LayoutInflater layoutInflater;
        AppOpsManager appOpsManager2 = (AppOpsManager) getSystemService(AppOpsManager.class);
        LayoutInflater layoutInflater2 = LayoutInflater.from(this);
        View view = layoutInflater2.inflate(R.layout.accessibility_service_dialog, (ViewGroup) null);
        int numServices = services.size();
        int i = 0;
        while (i < numServices) {
            ResolveInfo resolveInfo = services.get(i).getResolveInfo();
            final ServiceInfo serviceInfo = resolveInfo.serviceInfo;
            ApplicationInfo appInfo = serviceInfo.applicationInfo;
            CharSequence label = getLabel(resolveInfo);
            long lastAccessTime = getLastAccessTime(appInfo, appOpsManager2);
            if (numServices == 1) {
                appOpsManager = appOpsManager2;
                ((TextView) view.requireViewById(R.id.title)).setText(getString(R.string.accessibility_service_dialog_title_single, new Object[]{label}));
                ((TextView) view.requireViewById(R.id.bottom_text)).setText(getString(R.string.accessibility_service_dialog_bottom_text_single, new Object[]{label}));
                ImageView headerIcon = (ImageView) view.requireViewById(R.id.header_icon);
                headerIcon.setImageDrawable(Utils.getBadgedIcon(this, appInfo));
                headerIcon.setVisibility(0);
                if (lastAccessTime != 0) {
                    TextView middleText = (TextView) view.requireViewById(R.id.middle_text);
                    middleText.setText(getString(R.string.app_permission_most_recent_summary, new Object[]{Utils.getAbsoluteTimeString(this, lastAccessTime)}));
                    middleText.setVisibility(0);
                }
                layoutInflater = layoutInflater2;
            } else {
                appOpsManager = appOpsManager2;
                ((TextView) view.requireViewById(R.id.title)).setText(getString(R.string.accessibility_service_dialog_title_multiple, new Object[]{Integer.valueOf(services.size())}));
                ((TextView) view.requireViewById(R.id.bottom_text)).setText(getString(R.string.accessibility_service_dialog_bottom_text_multiple));
                ViewGroup servicesListView = (ViewGroup) view.requireViewById(R.id.items_container);
                View itemView = layoutInflater2.inflate(R.layout.accessibility_service_dialog_item, servicesListView, false);
                ((TextView) itemView.requireViewById(R.id.title)).setText(label);
                ((ImageView) itemView.requireViewById(R.id.icon)).setImageDrawable(Utils.getBadgedIcon(this, appInfo));
                if (lastAccessTime == 0) {
                    itemView.requireViewById(R.id.summary).setVisibility(8);
                    layoutInflater = layoutInflater2;
                } else {
                    layoutInflater = layoutInflater2;
                    ((TextView) itemView.requireViewById(R.id.summary)).setText(getString(R.string.app_permission_most_recent_summary, new Object[]{Utils.getAbsoluteTimeString(this, lastAccessTime)}));
                }
                itemView.setOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.-$$Lambda$ReviewAccessibilityServicesActivity$zQ6BMFscRu9JeNqTXL-HZkAFsk4
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f$0.lambda$createDialogView$2$ReviewAccessibilityServicesActivity(serviceInfo, view2);
                    }
                });
                servicesListView.addView(itemView);
            }
            i++;
            layoutInflater2 = layoutInflater;
            appOpsManager2 = appOpsManager;
        }
        return view;
    }

    public /* synthetic */ void lambda$createDialogView$2$ReviewAccessibilityServicesActivity(ServiceInfo serviceInfo, View v) {
        startAccessibilityScreen(serviceInfo);
    }

    private void startAccessibilityScreen(ServiceInfo serviceInfo) {
        Intent intent = new Intent("android.settings.ACCESSIBILITY_DETAILS_SETTINGS");
        intent.putExtra("android.intent.extra.COMPONENT_NAME", new ComponentName(serviceInfo.packageName, serviceInfo.name).flattenToString());
        startActivity(intent);
    }

    private CharSequence getLabel(ResolveInfo resolveInfo) {
        return BidiFormatter.getInstance().unicodeWrap(TextUtils.makeSafeForPresentation(resolveInfo.loadLabel(getPackageManager()).toString(), 0, 0.0f, 5));
    }

    private static long getLastAccessTime(ApplicationInfo appInfo, AppOpsManager appOpsManager) {
        List<AppOpsManager.PackageOps> ops = appOpsManager.getOpsForPackage(appInfo.uid, appInfo.packageName, new String[]{"android:access_accessibility"});
        long lastAccessTime = 0;
        int numPkgOps = ops.size();
        for (int pkgOpNum = 0; pkgOpNum < numPkgOps; pkgOpNum++) {
            AppOpsManager.PackageOps pkgOp = ops.get(pkgOpNum);
            int numOps = pkgOp.getOps().size();
            for (int opNum = 0; opNum < numOps; opNum++) {
                AppOpsManager.OpEntry op = (AppOpsManager.OpEntry) pkgOp.getOps().get(opNum);
                lastAccessTime = Math.max(lastAccessTime, op.getLastAccessTime(13));
            }
        }
        return lastAccessTime;
    }
}
