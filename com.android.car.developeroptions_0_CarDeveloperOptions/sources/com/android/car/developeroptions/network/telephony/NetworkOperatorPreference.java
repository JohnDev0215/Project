package com.android.car.developeroptions.network.telephony;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.telephony.CellInfo;
import android.telephony.CellSignalStrength;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.settingslib.graph.SignalDrawable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NetworkOperatorPreference extends Preference {
    private static final Drawable EMPTY_DRAWABLE = new ColorDrawable(0);
    private CellInfo mCellInfo;
    private List<String> mForbiddenPlmns;
    private int mLevel;
    private boolean mShow4GForLTE;

    public NetworkOperatorPreference(CellInfo cellInfo, Context context, List<String> list, boolean z) {
        super(context);
        this.mLevel = -1;
        this.mCellInfo = cellInfo;
        this.mForbiddenPlmns = list;
        this.mShow4GForLTE = z;
        refresh();
    }

    public CellInfo getCellInfo() {
        return this.mCellInfo;
    }

    public void refresh() {
        String networkTitle = CellInfoUtil.getNetworkTitle(this.mCellInfo);
        if (CellInfoUtil.isForbidden(this.mCellInfo, this.mForbiddenPlmns)) {
            networkTitle = networkTitle + " " + getContext().getResources().getString(R.string.forbidden_network);
        }
        setTitle(networkTitle);
        CellSignalStrength cellSignalStrength = this.mCellInfo.getCellSignalStrength();
        int level = cellSignalStrength != null ? cellSignalStrength.getLevel() : -1;
        if (this.mLevel != level) {
            this.mLevel = level;
            updateIcon(level);
        }
    }

    @Override // androidx.preference.Preference
    public void setIcon(int i) {
        updateIcon(i);
    }

    private int getIconIdForCell(CellInfo cellInfo) {
        int type = cellInfo.getCellIdentity().getType();
        if (type == 1) {
            return R.drawable.signal_strength_g;
        }
        if (type == 2) {
            return R.drawable.signal_strength_1x;
        }
        if (type == 3) {
            return this.mShow4GForLTE ? R.drawable.ic_signal_strength_4g : R.drawable.signal_strength_lte;
        }
        if (type == 4 || type == 5) {
            return R.drawable.signal_strength_3g;
        }
        return 0;
    }

    private void updateIcon(int i) {
        Drawable drawable;
        if (i < 0 || i >= 5) {
            return;
        }
        Context context = getContext();
        SignalDrawable signalDrawable = new SignalDrawable(getContext());
        signalDrawable.setLevel(SignalDrawable.getState(i, 5, false));
        int iconIdForCell = getIconIdForCell(this.mCellInfo);
        if (iconIdForCell == 0) {
            drawable = EMPTY_DRAWABLE;
        } else {
            drawable = getContext().getResources().getDrawable(iconIdForCell, getContext().getTheme());
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.signal_strength_icon_size);
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{drawable, signalDrawable});
        layerDrawable.setLayerGravity(0, 51);
        layerDrawable.setLayerGravity(1, 85);
        layerDrawable.setLayerSize(1, dimensionPixelSize, dimensionPixelSize);
        setIcon(layerDrawable);
    }
}
