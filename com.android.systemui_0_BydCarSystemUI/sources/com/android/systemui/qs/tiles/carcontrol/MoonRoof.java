package com.android.systemui.qs.tiles.carcontrol;

import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.net.Uri;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Constants;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.QSIconConstants;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.util.SharedPreferencesUtil;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class MoonRoof extends QSTileImpl<QSTile.BooleanState> {
    private final int imgRes;
    private final StatusBar statusBar;
    private final String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public MoonRoof(Context context) {
        super(context);
        BydLogHelper.logD("new MoonRoof");
        String string = this.mContext.getString(R.string.car_title_skylight);
        this.tileLabel = string;
        this.imgRes = QSIconConstants.icon.get(string).intValue();
        this.statusBar = (StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class);
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
        int moonRoofConfig = BYDAutoBodyworkDevice.getInstance(this.mContext).getMoonRoofConfig();
        BydLogHelper.logW("onBindViewHolder", "tileLabel : " + this.tileLabel + " , getMoonRoofConfig moonRoofConfig : " + moonRoofConfig);
        if (moonRoofConfig == 1 || moonRoofConfig == 6 || moonRoofConfig == 7 || moonRoofConfig == 10 || moonRoofConfig == 11) {
            updateTileLabel(this.mContext.getResources().getString(R.string.car_title_skylight_and_sunshade));
            return;
        }
        if (moonRoofConfig == 2 || moonRoofConfig == 8 || moonRoofConfig == 9) {
            updateTileLabel(this.mContext.getResources().getString(R.string.car_title_sunshade));
        } else if (moonRoofConfig == 3 || moonRoofConfig == 4) {
            updateTileLabel(this.mContext.getResources().getString(R.string.car_title_skylight));
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        BydLogHelper.logD("MoonRoof click");
        Intent intent = new Intent();
        intent.setAction("android.intent.action.CAR");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(268435456);
        intent.setData(Uri.parse("mycar://byd.car.sunroof"));
        this.mContext.startActivity(intent);
        if (this.statusBar.isShow()) {
            SharedPreferencesUtil.putBoolean(this.mContext, Constants.IS_START_ACTIVITY, true);
        }
        this.statusBar.closePanelsIfNeed();
        updateItemSelected(false);
    }
}
