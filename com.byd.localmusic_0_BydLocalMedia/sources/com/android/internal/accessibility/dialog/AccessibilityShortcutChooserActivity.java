package com.android.internal.accessibility.dialog;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import com.android.internal.accessibility.util.AccessibilityUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class AccessibilityShortcutChooserActivity extends Activity {
    private AlertDialog mMenuDialog;
    private AlertDialog mPermissionDialog;
    private ShortcutTargetAdapter mTargetAdapter;
    private final int mShortcutType = 1;
    private final List<AccessibilityTarget> mTargets = new ArrayList();

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!getTheme().obtainStyledAttributes(R.styleable.Theme).getBoolean(38, false)) {
            requestWindowFeature(1);
        }
        this.mTargets.addAll(AccessibilityTargetHelper.getTargets(this, 1));
        this.mTargetAdapter = new ShortcutTargetAdapter(this.mTargets);
        AlertDialog alertDialogCreateMenuDialog = createMenuDialog();
        this.mMenuDialog = alertDialogCreateMenuDialog;
        alertDialogCreateMenuDialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.android.internal.accessibility.dialog.-$$Lambda$AccessibilityShortcutChooserActivity$QHjVdgLm9vFoFVxKPm9eDeb4m68
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                this.f$0.lambda$onCreate$0$AccessibilityShortcutChooserActivity(dialogInterface);
            }
        });
        this.mMenuDialog.show();
    }

    public /* synthetic */ void lambda$onCreate$0$AccessibilityShortcutChooserActivity(DialogInterface dialogInterface) {
        updateDialogListeners();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.mMenuDialog.dismiss();
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTargetSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.mTargets.get(i).onSelected();
        this.mMenuDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTargetChecked(AdapterView<?> adapterView, View view, int i, long j) {
        AccessibilityTarget accessibilityTarget = this.mTargets.get(i);
        if ((accessibilityTarget instanceof AccessibilityServiceTarget) && !accessibilityTarget.isShortcutEnabled()) {
            AlertDialog alertDialogCreate = new AlertDialog.Builder(this).setView(AccessibilityTargetHelper.createEnableDialogContentView(this, (AccessibilityServiceTarget) accessibilityTarget, new View.OnClickListener() { // from class: com.android.internal.accessibility.dialog.-$$Lambda$AccessibilityShortcutChooserActivity$ecrvRIv0UfpIIECtsMnGvroliEw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.lambda$onTargetChecked$1$AccessibilityShortcutChooserActivity(view2);
                }
            }, new View.OnClickListener() { // from class: com.android.internal.accessibility.dialog.-$$Lambda$AccessibilityShortcutChooserActivity$pZ2DTppLcNVn85LHvHTc3oReX3o
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.lambda$onTargetChecked$2$AccessibilityShortcutChooserActivity(view2);
                }
            })).create();
            this.mPermissionDialog = alertDialogCreate;
            alertDialogCreate.show();
        } else {
            accessibilityTarget.onCheckedChanged(!accessibilityTarget.isShortcutEnabled());
            this.mTargetAdapter.notifyDataSetChanged();
        }
    }

    public /* synthetic */ void lambda$onTargetChecked$1$AccessibilityShortcutChooserActivity(View view) {
        this.mPermissionDialog.dismiss();
        this.mTargetAdapter.notifyDataSetChanged();
    }

    public /* synthetic */ void lambda$onTargetChecked$2$AccessibilityShortcutChooserActivity(View view) {
        this.mPermissionDialog.dismiss();
    }

    private void onDoneButtonClicked() {
        this.mTargets.clear();
        this.mTargets.addAll(AccessibilityTargetHelper.getTargets(this, 1));
        if (this.mTargets.isEmpty()) {
            this.mMenuDialog.dismiss();
            return;
        }
        this.mTargetAdapter.setShortcutMenuMode(0);
        this.mTargetAdapter.notifyDataSetChanged();
        this.mMenuDialog.getButton(-1).setText(getString(com.android.internal.R.string.edit_accessibility_shortcut_menu_button));
        updateDialogListeners();
    }

    private void onEditButtonClicked() {
        this.mTargets.clear();
        this.mTargets.addAll(AccessibilityTargetHelper.getInstalledTargets(this, 1));
        this.mTargetAdapter.setShortcutMenuMode(1);
        this.mTargetAdapter.notifyDataSetChanged();
        this.mMenuDialog.getButton(-1).setText(getString(com.android.internal.R.string.done_accessibility_shortcut_menu_button));
        updateDialogListeners();
    }

    private void updateDialogListeners() {
        boolean z = this.mTargetAdapter.getShortcutMenuMode() == 1;
        this.mMenuDialog.setTitle(getString(z ? com.android.internal.R.string.accessibility_edit_shortcut_menu_volume_title : com.android.internal.R.string.accessibility_select_shortcut_menu_title));
        this.mMenuDialog.getButton(-1).setOnClickListener(z ? new View.OnClickListener() { // from class: com.android.internal.accessibility.dialog.-$$Lambda$AccessibilityShortcutChooserActivity$2QlbmchcPPXlr_l_WdY8qCWUDFc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$updateDialogListeners$3$AccessibilityShortcutChooserActivity(view);
            }
        } : new View.OnClickListener() { // from class: com.android.internal.accessibility.dialog.-$$Lambda$AccessibilityShortcutChooserActivity$Kzygz0R0mJ90YuPwTKYJCTqRAEI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$updateDialogListeners$4$AccessibilityShortcutChooserActivity(view);
            }
        });
        this.mMenuDialog.getListView().setOnItemClickListener(z ? new AdapterView.OnItemClickListener() { // from class: com.android.internal.accessibility.dialog.-$$Lambda$AccessibilityShortcutChooserActivity$4u-1K4iiJhwQxpSdVzqRYO0jECM
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                this.f$0.onTargetChecked(adapterView, view, i, j);
            }
        } : new AdapterView.OnItemClickListener() { // from class: com.android.internal.accessibility.dialog.-$$Lambda$AccessibilityShortcutChooserActivity$Rk3gwLIkvA4fFbJWs4by4DhZtnA
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                this.f$0.onTargetSelected(adapterView, view, i, j);
            }
        });
    }

    public /* synthetic */ void lambda$updateDialogListeners$3$AccessibilityShortcutChooserActivity(View view) {
        onDoneButtonClicked();
    }

    public /* synthetic */ void lambda$updateDialogListeners$4$AccessibilityShortcutChooserActivity(View view) {
        onEditButtonClicked();
    }

    private AlertDialog createMenuDialog() {
        AlertDialog.Builder onDismissListener = new AlertDialog.Builder(this).setTitle(getString(com.android.internal.R.string.accessibility_select_shortcut_menu_title)).setAdapter(this.mTargetAdapter, null).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.internal.accessibility.dialog.-$$Lambda$AccessibilityShortcutChooserActivity$SYqOavSaPTdv3-6SarffTIovKpg
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f$0.lambda$createMenuDialog$5$AccessibilityShortcutChooserActivity(dialogInterface);
            }
        });
        if (AccessibilityUtils.isUserSetupCompleted(this)) {
            onDismissListener.setPositiveButton(getString(com.android.internal.R.string.edit_accessibility_shortcut_menu_button), (DialogInterface.OnClickListener) null);
        }
        return onDismissListener.create();
    }

    public /* synthetic */ void lambda$createMenuDialog$5$AccessibilityShortcutChooserActivity(DialogInterface dialogInterface) {
        finish();
    }
}
