package com.android.systemui.qs.tiles.carcontrol;

import android.content.Context;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.os.Handler;
import android.os.Message;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.QSIconConstants;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.policy.CarController;
import com.android.systemui.widget.CommonDialog;
import com.byd.profile.ToastManager;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class Trunk extends QSTileImpl<QSTile.BooleanState> {
    private static final int TRUNK_DOOR_OPEN_TYPE = 2;
    private static final int TRUNK_OPEN_TYPE = 0;
    private static final int TRUNK_TYPE = 1;
    private final int imgRes;
    private final BYDAutoStudy mBYDAutoStudy;
    private final CarController.Callback mCallback;
    private final CarController mCarController;
    private final H mHandler;
    private final String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public Trunk(Context context) {
        super(context);
        BydLogHelper.logD("new Trunk");
        this.mHandler = new H(this);
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        CarController.Callback callback = new CarController.Callback() { // from class: com.android.systemui.qs.tiles.carcontrol.Trunk.1
            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onDoorStateChanged(final int area, final int state) {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.Trunk.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BydLogHelper.logD("onDoorStateChanged", "area : " + area + " , state : " + state);
                        if (area == 6) {
                            Trunk.this.updateItemStatus(state);
                        }
                    }
                });
            }

            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onMessage5sOnlineStateChanged(int id, int state) {
                BydLogHelper.logD("onMessage5sOnlineStateChanged", "id : " + id + ", state : " + state);
                Trunk.this.refreshCarItem(id, state);
            }

            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onHasMessageChanged(int id, int state) {
                BydLogHelper.logD("onHasMessageChanged", "id : " + id + ", state : " + state);
                Trunk.this.refreshCarItem(id, state);
            }
        };
        this.mCallback = callback;
        carController.addCallback(callback);
        this.mBYDAutoStudy = BYDAutoStudy.getInstance(this.mContext);
        String string = this.mContext.getString(R.string.car_title_trunk_open);
        this.tileLabel = string;
        this.imgRes = QSIconConstants.icon.get(string).intValue();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public String getTileLabel() {
        return this.tileLabel;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public int getTileImageRes() {
        return this.imgRes;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        int iHasMessage = this.mCarController.hasMessage(56);
        BydLogHelper.logD("onBindViewHolder", "hasMessage294 : " + iHasMessage);
        if (iHasMessage == 1) {
            updateItemStatus(this.mCarController.getLuggageDoorState());
        } else {
            updateItemSelected(false);
        }
        int trunkConfiguration = getTrunkConfiguration();
        BydLogHelper.logD("Trunk trunkConfiguration : " + trunkConfiguration);
        if (trunkConfiguration == 1) {
            updateTileLabel(this.mContext.getString(R.string.car_title_trunk));
            updateImgRes(this.imgRes);
        } else if (trunkConfiguration == 0) {
            updateTileLabel(this.mContext.getString(R.string.car_title_trunk_open));
            updateImgRes(this.imgRes);
        } else if (trunkConfiguration == 2) {
            updateTileLabel(this.mContext.getString(R.string.car_title_trunk_door_open));
            updateImgRes(R.mipmap.icon_control_trunk_door_open);
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        int trunkConfiguration = getTrunkConfiguration();
        BydLogHelper.logD("click", "trunkConfiguration : " + trunkConfiguration);
        int luggageDoorState = this.mCarController.getLuggageDoorState();
        if (trunkConfiguration == 1) {
            BydLogHelper.logD("click", "getLuggageDoorState : " + luggageDoorState);
            if (luggageDoorState == 0) {
                showDialog(true, this.mContext.getResources().getString(R.string.open_trunk));
                updateItemSelected(false);
                return;
            } else {
                if (luggageDoorState == 1) {
                    showDialog(false, this.mContext.getResources().getString(R.string.close_trunk));
                    updateItemSelected(true);
                    return;
                }
                return;
            }
        }
        if (trunkConfiguration == 0) {
            BydLogHelper.logD("click", "getLuggageDoorState : " + luggageDoorState);
            if (luggageDoorState == 0) {
                this.mHandler.removeMessages(1);
                this.mHandler.sendEmptyMessageDelayed(1, 100L);
                updateItemSelected(false);
                showDialog(true, this.mContext.getResources().getString(R.string.open_trunk));
                return;
            }
            if (luggageDoorState == 1) {
                ToastManager.show(this.mContext, R.string.trunk_tips_manual_close_trunk);
                updateItemSelected(true);
                return;
            }
            return;
        }
        if (trunkConfiguration == 2) {
            if (luggageDoorState == 0) {
                this.mHandler.removeMessages(1);
                this.mHandler.sendEmptyMessageDelayed(1, 100L);
                updateItemSelected(false);
                showDialog(true, this.mContext.getResources().getString(R.string.trunk_tips_look_out_rear_person));
                return;
            }
            if (luggageDoorState == 1) {
                ToastManager.show(this.mContext, R.string.trunk_tips_manual_close_trunk);
                updateItemSelected(true);
            }
        }
    }

    public void updateItemStatus(int state) {
        int trunkConfiguration = getTrunkConfiguration();
        BydLogHelper.logD("updateItemStatus", "trunkConfiguration : " + trunkConfiguration + " , state : " + state);
        if (trunkConfiguration == 0) {
            if (state == 1) {
                updateItemSelected(true);
                return;
            } else {
                updateItemSelected(false);
                return;
            }
        }
        if (trunkConfiguration == 1) {
            if (state == 1) {
                updateItemSelected(true);
                return;
            } else {
                updateItemSelected(false);
                return;
            }
        }
        if (trunkConfiguration == 2) {
            if (state == 1) {
                updateItemSelected(true);
            } else {
                updateItemSelected(false);
            }
        }
    }

    private int getTrunkConfiguration() {
        int trunkConfigutarion = this.mBYDAutoStudy.getTrunkConfigutarion();
        int tailgateTypeMarking = this.mBYDAutoStudy.getTailgateTypeMarking();
        int iHasMessage = this.mCarController.hasMessage(58);
        int iHasMessage2 = this.mCarController.hasMessage(25);
        BydLogHelper.logD("getTrunkType", "trunkConfiguration : " + trunkConfigutarion + " , tailgateTypeMarking : " + tailgateTypeMarking);
        BydLogHelper.logD("getTrunkType", "hasMessage401 : " + iHasMessage + " , hasMessage4BF : " + iHasMessage2);
        if (trunkConfigutarion == 0) {
            return tailgateTypeMarking == 3 ? 2 : 0;
        }
        if (trunkConfigutarion == 1) {
            return 1;
        }
        return (iHasMessage == 1 || tailgateTypeMarking != 3) ? 0 : 2;
    }

    private class H extends Handler {
        public static final int CLICK_MSG = 2;
        public static final int CLICK_TRUNK_OPEN_MSG = 1;
        private final WeakReference<Trunk> mTrunk;

        public H(Trunk trunk) {
            this.mTrunk = new WeakReference<>(trunk);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (this.mTrunk.get() == null) {
                return;
            }
            if (msg.what == 1) {
                Trunk.this.updateItemSelected(false);
            } else if (msg.what == 2) {
                Trunk.this.handlerClick();
            }
        }
    }

    private void showDialog(boolean showOpen, String message) {
        final CommonDialog commonDialog = new CommonDialog(this.mContext);
        String string = this.mContext.getResources().getString(R.string.on);
        String string2 = this.mContext.getResources().getString(R.string.cancel_s);
        if (!showOpen) {
            string = this.mContext.getResources().getString(R.string.split_screen_close);
        }
        commonDialog.setMessage(message).setTitle((String) null).setBottomStyle(4).setBtnLeft(string2).setBtnRight(string).setBtnRightBgColor(true).setBtnRightTextColor(this.mContext.getColor(R.color.white)).setDoubleButtonStyle(1).setSwitchCountDownBtn(1).setCountTime(5).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.android.systemui.qs.tiles.carcontrol.Trunk.2
            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onPositiveClick() {
                commonDialog.dismiss();
                BydLogHelper.logD("Trunk onPositiveClick : " + commonDialog.getBtnLeft());
                Trunk.this.mHandler.removeMessages(2);
                Trunk.this.mHandler.sendEmptyMessage(2);
            }

            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onNegativeClick() {
                commonDialog.dismiss();
                BydLogHelper.logD("Trunk onNegativeClick : " + commonDialog.getBtnRight());
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerClick() {
        int trunkConfiguration = getTrunkConfiguration();
        BydLogHelper.logD("handlerClick", "trunkConfiguration : " + trunkConfiguration);
        if (trunkConfiguration != 1) {
            if (trunkConfiguration == 0) {
                this.mCarController.settingLuggage(1);
                return;
            } else {
                if (trunkConfiguration == 2) {
                    this.mCarController.settingLuggage(1);
                    return;
                }
                return;
            }
        }
        int luggageDoorState = this.mCarController.getLuggageDoorState();
        BydLogHelper.logD("handlerClick", "getLuggageDoorState : " + luggageDoorState);
        if (luggageDoorState == 0) {
            this.mCarController.voiceCtlBackDoor(1);
        } else if (luggageDoorState == 1) {
            this.mCarController.voiceCtlBackDoor(3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshCarItem(final int id, final int state) {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.-$$Lambda$Trunk$1e6hwHuoR1KltFutDio6Lm6IBeI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$refreshCarItem$0$Trunk(id, state);
            }
        });
    }

    public /* synthetic */ void lambda$refreshCarItem$0$Trunk(int i, int i2) {
        if (i == 56) {
            boolean z = i2 == 1;
            int trunkConfigutarion = this.mBYDAutoStudy.getTrunkConfigutarion();
            BydLogHelper.logD("onHasMessageChanged", "trunkConfiguration : " + trunkConfigutarion + " , state : " + i2);
            if (z) {
                if (trunkConfigutarion == 0) {
                    updateItemSelected(false);
                }
            } else if (trunkConfigutarion == 0) {
                updateItemEnabled(false);
            }
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void release() {
        CarController.Callback callback;
        super.release();
        CarController carController = this.mCarController;
        if (carController == null || (callback = this.mCallback) == null) {
            return;
        }
        carController.removeCallback(callback);
    }
}
