package com.android.systemui.qs.tiles.carcontrol;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.QSIconConstants;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.policy.CarController;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class InteriorLightOn extends QSTileImpl<QSTile.BooleanState> {
    private final int imgRes;
    private final CarController mCarController;
    private final H mHandler;
    private final String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public InteriorLightOn(Context context) {
        super(context);
        BydLogHelper.logD("new InteriorLightOn");
        String string = this.mContext.getString(R.string.car_title_interior_light_on);
        this.tileLabel = string;
        this.imgRes = QSIconConstants.icon.get(string).intValue();
        this.mHandler = new H(this);
        this.mCarController = (CarController) Dependency.get(CarController.class);
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
        updateItemSelected(false);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        BydLogHelper.logD("handlerClick", "setInsideLightDoorState open 2");
        this.mCarController.setInsideLightDoorState(2);
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessageDelayed(1, 100L);
    }

    private class H extends Handler {
        WeakReference<InteriorLightOn> mInteriorLightOn;

        public H(InteriorLightOn interiorLightOn) {
            this.mInteriorLightOn = new WeakReference<>(interiorLightOn);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (this.mInteriorLightOn.get() != null && msg.what == 1) {
                InteriorLightOn.this.updateItemSelected(false);
            }
        }
    }
}
