package com.android.systemui.qs.tiles;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.policy.VolumeController;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class MuteTile extends QSTileImpl<QSTile.BooleanState> {
    private H mHandler;
    protected VolumeControlCallback mVolumeControlCallback;
    private VolumeController mVolumeController;

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public int getTileImageRes() {
        return R.mipmap.icon_control_mute_tile;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public MuteTile(Context context) {
        super(context);
        BydLogHelper.logD("new MuteTile");
        this.mHandler = new H();
        this.mVolumeControlCallback = new VolumeControlCallback();
        VolumeController volumeController = (VolumeController) Dependency.get(VolumeController.class);
        this.mVolumeController = volumeController;
        volumeController.addCallback(this.mVolumeControlCallback, this.mHandler);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void release() {
        super.release();
        BydLogHelper.logD("MuteTile release");
        VolumeController volumeController = this.mVolumeController;
        if (volumeController != null) {
            volumeController.removeCallback(this.mVolumeControlCallback);
            this.mVolumeController = null;
            this.mVolumeControlCallback = null;
            this.mHandler = null;
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public String getTileLabel() {
        return this.mContext.getString(R.string.qs_title_mute);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        this.mVolumeController.setAllMute(isSelected);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        ((QSTile.BooleanState) this.mState).value = this.mVolumeController.getAllMute();
        refreshState();
    }

    protected final class VolumeControlCallback implements VolumeController.Callbacks {
        protected VolumeControlCallback() {
        }

        @Override // com.android.systemui.statusbar.policy.VolumeController.Callbacks
        public void onAllMuteState(boolean isAllMute) {
            if (((QSTile.BooleanState) MuteTile.this.mState).value != isAllMute) {
                ((QSTile.BooleanState) MuteTile.this.mState).value = isAllMute;
                BydLogHelper.logD("onAllMuteState:" + ((QSTile.BooleanState) MuteTile.this.mState).value);
                MuteTile.this.refreshState();
            }
        }
    }

    private final class H extends Handler {
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
        }

        public H() {
            super(Looper.getMainLooper());
        }
    }
}
