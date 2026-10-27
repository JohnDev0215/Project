package com.android.systemui.qs.tiles;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Constants;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.policy.CarController;
import com.android.systemui.widget.CommonDialog;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class RotationLockTile extends QSTileImpl<QSTile.BooleanState> {
    protected CarControlCallback mCarControlCallback;
    private CarController mCarController;
    private ContentObserver mContentObserver;
    private ContentResolver mContentResolver;
    private CommonDialog mDialog;

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public int getTileImageRes() {
        return R.mipmap.icon_control_automatic_rotation;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public RotationLockTile(Context context) {
        super(context);
        this.mContentResolver = this.mContext.getContentResolver();
        this.mContentObserver = new SettingsObserver(null);
        this.mContentResolver.registerContentObserver(Settings.Global.getUriFor(Constants.SYSUI_ROTATION_AUTO), false, this.mContentObserver);
        this.mCarController = (CarController) Dependency.get(CarController.class);
        CarControlCallback carControlCallback = new CarControlCallback();
        this.mCarControlCallback = carControlCallback;
        this.mCarController.addCallback(carControlCallback);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void release() {
        CarControlCallback carControlCallback;
        ContentObserver contentObserver;
        super.release();
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null && (contentObserver = this.mContentObserver) != null) {
            contentResolver.unregisterContentObserver(contentObserver);
            this.mContentObserver = null;
            this.mContentResolver = null;
        }
        CarController carController = this.mCarController;
        if (carController != null && (carControlCallback = this.mCarControlCallback) != null) {
            carController.removeCallback(carControlCallback);
            this.mCarControlCallback = null;
            this.mCarController = null;
        }
        CommonDialog commonDialog = this.mDialog;
        if (commonDialog != null) {
            if (commonDialog.isShowing()) {
                this.mDialog.dismiss();
            }
            this.mDialog = null;
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public String getTileLabel() {
        return this.mContext.getString(R.string.qs_title_automatic_rotation);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, android.view.View.OnClickListener
    public void onClick(View v) {
        int showCarModelType;
        CarController carController = this.mCarController;
        if (carController != null && ((showCarModelType = carController.getShowCarModelType()) == 1 || showCarModelType == 2)) {
            BydLogHelper.logD("is show car mode");
            showDialog(null, this.mContext.getResources().getString(R.string.in_display_mode_limit_auto_rotate), 3);
        } else {
            super.onClick(v);
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean z) {
        BydLogHelper.logD("click:" + z);
        Settings.Global.putInt(this.mContentResolver, Constants.SYSUI_ROTATION_AUTO, !z ? 1 : 0);
    }

    private void showDialog(String title, String message, int bottomStyle) {
        if (this.mDialog == null) {
            this.mDialog = new CommonDialog(this.mContext);
        }
        this.mDialog.setMessage(message).setTitle(title).setBottomStyle(bottomStyle).setBtnLeft(this.mContext.getResources().getString(R.string.know)).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.android.systemui.qs.tiles.RotationLockTile.1
            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onPositiveClick() {
                BydLogHelper.logD("onPositiveClick");
                RotationLockTile.this.mDialog.dismiss();
            }

            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onNegativeClick() {
                RotationLockTile.this.mDialog.dismiss();
            }
        });
        if (this.mDialog.isShowing()) {
            return;
        }
        this.mDialog.show();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        int i = Settings.Global.getInt(this.mContentResolver, Constants.SYSUI_ROTATION_AUTO, 0);
        BydLogHelper.logD("rotationValue:" + i);
        ((QSTile.BooleanState) this.mState).value = i == 0;
        refreshState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    class SettingsObserver extends ContentObserver {
        public SettingsObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange, Uri uri) {
            if (uri != null) {
                String lastPathSegment = uri.getLastPathSegment();
                BydLogHelper.logD("onChange selfChange : " + selfChange + " , option : " + lastPathSegment);
                if (selfChange || !TextUtils.equals(Constants.SYSUI_ROTATION_AUTO, lastPathSegment)) {
                    return;
                }
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.-$$Lambda$RotationLockTile$SettingsObserver$b6cujQDBkxQakz1f3MrHzrz_Rcc
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onChange$0$RotationLockTile$SettingsObserver();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onChange$0$RotationLockTile$SettingsObserver() {
            int i = Settings.Global.getInt(RotationLockTile.this.mContentResolver, Constants.SYSUI_ROTATION_AUTO, 0);
            ((QSTile.BooleanState) RotationLockTile.this.mState).value = i == 0;
            RotationLockTile.this.refreshState();
        }
    }

    protected class CarControlCallback implements CarController.Callback {
        protected CarControlCallback() {
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void showCarModelChange(int value) {
            if (value == 1 || value == 2) {
                if (RotationLockTile.this.mContentResolver != null) {
                    Settings.Global.putInt(RotationLockTile.this.mContentResolver, Constants.SYSUI_ROTATION_AUTO, 0);
                } else {
                    Settings.Global.putInt(RotationLockTile.this.mContext.getContentResolver(), Constants.SYSUI_ROTATION_AUTO, 0);
                }
            }
        }
    }
}
