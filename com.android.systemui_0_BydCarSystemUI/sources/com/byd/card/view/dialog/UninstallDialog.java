package com.byd.card.view.dialog;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.systemui.R;
import com.byd.card.db.CardDatabase;
import com.byd.card.drag.ApplicationInfo;
import com.byd.card.floatcard.Executors;
import com.byd.card.utils.AppUtils;
import com.byd.card.utils.BitmapUtils;
import com.byd.card.view.activity.MoreAppActivity;

/* JADX INFO: loaded from: classes.dex */
public class UninstallDialog extends BaseDialog {
    private ImageView mAppIcon;
    private TextView mAppName;
    private TextView mUninstallCancel;
    private TextView mUninstallOk;

    public UninstallDialog(MoreAppActivity activity, int layoutId, ApplicationInfo applicationInfo) {
        super(activity, layoutId, applicationInfo);
    }

    @Override // com.byd.card.view.dialog.BaseDialog
    public void finishInitView(Dialog view) {
        this.mAppIcon = (ImageView) view.findViewById(R.id.uninstall_app_icon);
        this.mAppName = (TextView) view.findViewById(R.id.uninstall_content);
        this.mUninstallOk = (TextView) view.findViewById(R.id.uninstall_ok);
        this.mUninstallCancel = (TextView) view.findViewById(R.id.uninstall_cancel);
        this.mAppName.setText(this.mAppName.getText().toString() + " “" + this.appInfo.title + "” ?");
        setAppIcon();
        initListener();
    }

    private void setAppIcon() {
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.view.dialog.UninstallDialog.1
            @Override // java.lang.Runnable
            public void run() {
                ApplicationInfo appByPackageName = CardDatabase.getInstance().getWorkSpaceItemDao().getAppByPackageName(UninstallDialog.this.appInfo.getPackageName());
                if (appByPackageName != null) {
                    final Drawable drawableAdapterMultiTheme = BitmapUtils.adapterMultiTheme(appByPackageName.getPackageName(), appByPackageName.getIconBytes());
                    if (drawableAdapterMultiTheme == null) {
                        drawableAdapterMultiTheme = BitmapUtils.bytes2Drawable(appByPackageName.getIconBytes());
                    }
                    if (drawableAdapterMultiTheme != null) {
                        Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.view.dialog.UninstallDialog.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                UninstallDialog.this.mAppIcon.setBackground(drawableAdapterMultiTheme);
                            }
                        });
                    }
                }
            }
        });
    }

    @Override // com.byd.card.view.dialog.BaseDialog
    public void onViewClick(View view) {
        int id = view.getId();
        if (id == R.id.uninstall_cancel) {
            cancel();
            return;
        }
        if (id != R.id.uninstall_ok) {
            return;
        }
        if (this.appInfo != null) {
            if (this.appInfo.getType() == 3) {
                AppUtils.uninstallShortcut(this.activity, this.appInfo);
            } else {
                AppUtils.uninstallApk(this.activity, this.appInfo.getPackageName());
            }
        }
        cancel();
    }

    private void initListener() {
        setOnClickListener(this.mUninstallOk);
        setOnClickListener(this.mUninstallCancel);
    }
}
