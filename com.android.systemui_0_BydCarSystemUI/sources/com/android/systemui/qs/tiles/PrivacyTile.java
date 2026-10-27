package com.android.systemui.qs.tiles;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Constants;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.util.AppUtil;
import com.android.systemui.util.SharedPreferencesUtil;

/* JADX INFO: loaded from: classes.dex */
public class PrivacyTile extends QSTileImpl<QSTile.BooleanState> {
    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public int getTileImageRes() {
        return R.mipmap.icon_control_privacy;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public PrivacyTile(Context context) {
        super(context);
        this.mAlwaysSelected = true;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void release() {
        super.release();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public String getTileLabel() {
        return this.mContext.getString(R.string.qs_title_privacy);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        StatusBar statusBar = (StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class);
        if (statusBar.isShow()) {
            SharedPreferencesUtil.putBoolean(this.mContext, Constants.IS_START_ACTIVITY, true);
        }
        statusBar.closePanelsIfNeed();
        AppUtil.startCarSettings("privacy_manage");
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        ((QSTile.BooleanState) this.mState).value = true;
        refreshState();
    }
}
