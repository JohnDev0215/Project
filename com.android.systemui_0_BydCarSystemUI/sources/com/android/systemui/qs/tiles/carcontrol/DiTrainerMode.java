package com.android.systemui.qs.tiles.carcontrol;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.R;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.QSIconConstants;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class DiTrainerMode extends QSTileImpl<QSTile.BooleanState> {
    private final int imgRes;
    private final String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public DiTrainerMode(Context context) {
        super(context);
        BydLogHelper.logD("new DiTrainerMode");
        String string = this.mContext.getString(R.string.car_title_coach_mode);
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
    }
}
