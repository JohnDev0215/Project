package com.android.systemui.qs.tiles;

import android.content.Context;
import android.os.Handler;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.util.AppUtil;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class DeviceAttachmentTile extends QSTileImpl<QSTile.BooleanState> {
    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public int getTileImageRes() {
        return R.mipmap.icon_control_device_attachment;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public DeviceAttachmentTile(Context context) {
        super(context);
        this.mAlwaysUnSelected = true;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public String getTileLabel() {
        return this.mContext.getString(R.string.qs_title_device_attachment);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        StatusBar statusBar = (StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class);
        Handler handler = (Handler) Dependency.get(Dependency.MAIN_HANDLER);
        Objects.requireNonNull(statusBar);
        handler.postDelayed(new $$Lambda$6AFP_jFeRHxKeJbPfqNHVlv_FoI(statusBar), 200L);
        AppUtil.startCarSettings("car_play");
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        ((QSTile.BooleanState) this.mState).value = false;
        refreshState();
    }
}
