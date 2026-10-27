package com.android.systemui.qs.tiles;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Constants;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.phone.StatusBarWindowView;
import com.android.systemui.statusbar.policy.BluetoothController;
import com.android.systemui.statusbar.policy.CarController;
import com.android.systemui.util.AppUtil;
import com.android.systemui.util.SharedPreferencesUtil;
import com.android.systemui.util.StringUtil;
import com.android.systemui.widget.CommonDialog;
import com.android.systemui.wireless.CarPlayAndroidAutoTipDialog;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothTile extends QSTileImpl<QSTile.BooleanState> {
    protected BluetoothCallback mBluetoothCallback;
    private BluetoothController mBluetoothController;
    private CarPlayAndroidAutoTipDialog mDialog;

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public int getTileImageRes() {
        return R.mipmap.icon_control_bluetooth;
    }

    public BluetoothTile(Context context) {
        super(context);
        this.mBluetoothCallback = new BluetoothCallback();
        BluetoothController bluetoothController = (BluetoothController) Dependency.get(BluetoothController.class);
        this.mBluetoothController = bluetoothController;
        bluetoothController.addCallback(this.mBluetoothCallback);
        if (SharedPreferencesUtil.getInt(this.mContext, Constants.STATE_CPAA_DIALOG_SHOW, 0) == 2) {
            showDialog();
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void release() {
        super.release();
        this.mBluetoothController.removeCallback(this.mBluetoothCallback);
        this.mBluetoothCallback = null;
        CarPlayAndroidAutoTipDialog carPlayAndroidAutoTipDialog = this.mDialog;
        if (carPlayAndroidAutoTipDialog != null) {
            carPlayAndroidAutoTipDialog.dismiss();
        }
        this.mDialog = null;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public String getTileLabel() {
        return this.mContext.getString(R.string.qs_title_bluetooth);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        if (!isSelected) {
            boolean zIsAndroidAutoConnected = ((CarController) Dependency.get(CarController.class)).isAndroidAutoConnected();
            BydLogHelper.logD("isAAConnect " + zIsAndroidAutoConnected);
            if (zIsAndroidAutoConnected) {
                this.mRlContent.setSelected(true);
                this.mImageView.setSelectedStatus(true);
                showDialog();
                ((StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class)).closePanelsIfNeed();
                return;
            }
        }
        this.mBluetoothController.setEnable(isSelected);
    }

    private void showDialog() {
        BydLogHelper.logD("show");
        CarPlayAndroidAutoTipDialog carPlayAndroidAutoTipDialog = this.mDialog;
        if (carPlayAndroidAutoTipDialog == null) {
            CarPlayAndroidAutoTipDialog carPlayAndroidAutoTipDialog2 = new CarPlayAndroidAutoTipDialog(this.mContext);
            this.mDialog = carPlayAndroidAutoTipDialog2;
            carPlayAndroidAutoTipDialog2.setMessage(this.mContext.getString(R.string.bt_confirm_msg)).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.android.systemui.qs.tiles.BluetoothTile.1
                @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
                public void onPositiveClick() {
                    SharedPreferencesUtil.putInt(BluetoothTile.this.mContext, Constants.STATE_CPAA_DIALOG_SHOW, 0);
                    BluetoothTile.this.mDialog.dismiss();
                }

                @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
                public void onNegativeClick() {
                    SharedPreferencesUtil.putInt(BluetoothTile.this.mContext, Constants.STATE_CPAA_DIALOG_SHOW, 0);
                    BluetoothTile.this.mBluetoothController.setEnable(false);
                    if (BluetoothTile.this.mRlContent != null) {
                        BluetoothTile.this.mRlContent.setSelected(false);
                        BluetoothTile.this.mImageView.setSelectedStatus(false);
                    }
                    BluetoothTile.this.mDialog.dismiss();
                }
            });
            this.mDialog.show();
            SharedPreferencesUtil.putInt(this.mContext, Constants.STATE_CPAA_DIALOG_SHOW, 2);
            return;
        }
        if (!carPlayAndroidAutoTipDialog.isShowing()) {
            this.mDialog.show();
        }
        SharedPreferencesUtil.putInt(this.mContext, Constants.STATE_CPAA_DIALOG_SHOW, 2);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
        if (isLongClickFlag()) {
            final StatusBar statusBar = (StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class);
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.qs.tiles.-$$Lambda$BluetoothTile$gxmOmenDWGlAbR5-HOAm33aixG0
                @Override // java.lang.Runnable
                public final void run() {
                    BluetoothTile.lambda$longClick$0(statusBar);
                }
            }, 200L);
            AppUtil.startCarSettings("Bluetooth");
        }
    }

    static /* synthetic */ void lambda$longClick$0(StatusBar statusBar) {
        EventBus.getDefault().post(new StatusBarWindowView.CancelTouchEvent());
        statusBar.closePanelsIfNeed();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        ((QSTile.BooleanState) this.mState).value = this.mBluetoothController.isEnable();
        refreshState();
        if (((QSTile.BooleanState) this.mState).value && this.mBluetoothController.isConnected()) {
            BluetoothDevice connectedDevice = this.mBluetoothController.getConnectedDevice();
            String name = connectedDevice != null ? connectedDevice.getName() : null;
            BydLogHelper.logD("connectedDeviceName: ***" + name);
            if (!TextUtils.isEmpty(name)) {
                this.mTextView.setText(name);
            } else {
                this.mTextView.setText(getTileLabel());
            }
        } else {
            this.mTextView.setText(getTileLabel());
        }
        this.mTextView.postInvalidate();
    }

    protected final class BluetoothCallback implements BluetoothController.Callback {
        protected BluetoothCallback() {
        }

        @Override // com.android.systemui.statusbar.policy.BluetoothController.Callback
        public void onEnableChanged(final boolean enabled) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.BluetoothTile.BluetoothCallback.1
                @Override // java.lang.Runnable
                public void run() {
                    BydLogHelper.logD("enabled: " + enabled);
                    ((QSTile.BooleanState) BluetoothTile.this.mState).value = enabled;
                    BluetoothTile.this.refreshState();
                    if (BluetoothTile.this.mTextView != null) {
                        BluetoothTile.this.mTextView.setText(BluetoothTile.this.getTileLabel());
                    }
                }
            });
        }

        @Override // com.android.systemui.statusbar.policy.BluetoothController.Callback
        public void onConnectedChanged(final boolean isConnected, String address) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.BluetoothTile.BluetoothCallback.2
                @Override // java.lang.Runnable
                public void run() {
                    BydLogHelper.logD("bluetooth isConnected " + isConnected);
                    if (BluetoothTile.this.mTextView != null) {
                        BluetoothDevice connectedDevice = BluetoothTile.this.mBluetoothController.getConnectedDevice();
                        String name = connectedDevice != null ? connectedDevice.getName() : null;
                        BydLogHelper.logD("connectedDeviceName: " + StringUtil.desensitizeString(name, 0));
                        if (!TextUtils.isEmpty(name)) {
                            BluetoothTile.this.mTextView.setText(name);
                        } else {
                            BluetoothTile.this.mTextView.setText(BluetoothTile.this.getTileLabel());
                        }
                        BluetoothTile.this.mTextView.postInvalidate();
                    }
                }
            });
        }
    }
}
