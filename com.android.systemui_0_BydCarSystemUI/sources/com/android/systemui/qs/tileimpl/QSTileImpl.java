package com.android.systemui.qs.tileimpl;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.R;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.qs.QSTile.State;
import com.android.systemui.qs.QSIconConstants;
import com.android.systemui.util.StyleUtil;
import com.android.systemui.widget.BydImageView;
import com.android.systemui.widget.CustomToast;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public abstract class QSTileImpl<TState extends QSTile.State> implements QSTile, View.OnClickListener, View.OnLongClickListener {
    protected static final float ALPHA_DISABLE = 0.45f;
    protected static final float ALPHA_ENABLE = 1.0f;
    private boolean hide;
    private boolean longClickFlag;
    protected boolean mAlwaysSelected;
    protected boolean mAlwaysUnSelected;
    protected Context mContext;
    protected BydImageView mImageView;
    protected RelativeLayout mRlContent;
    protected TState mState = (TState) newTileState();
    protected TextView mTextView;
    private Toast mToast;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public boolean disableDrag() {
        return false;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public int getTileImageRes() {
        return R.mipmap.icon_control_wifi;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public boolean isAvailable() {
        return false;
    }

    public abstract TState newTileState();

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void updateState(boolean isVisible) {
    }

    public boolean isLongClickFlag() {
        return this.longClickFlag;
    }

    public void setLongClickFlag(boolean longClickFlag) {
        this.longClickFlag = longClickFlag;
    }

    public boolean isHide() {
        return this.hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public TextView getTextView() {
        return this.mTextView;
    }

    public QSTileImpl(Context context) {
        this.mContext = context;
    }

    protected int getLayoutId() {
        return StyleUtil.getResLayout(R.layout.item_qs_panel, "item_qs_panel");
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public String getTileId() {
        if (QSIconConstants.tileId.containsKey(getTileLabel())) {
            return QSIconConstants.tileId.get(getTileLabel());
        }
        return null;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public View createTileView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        this.mImageView = (BydImageView) viewHolder.itemView.findViewById(R.id.iv_item);
        this.mTextView = (TextView) viewHolder.itemView.findViewById(R.id.tv_item);
        RelativeLayout relativeLayout = (RelativeLayout) viewHolder.itemView.findViewById(R.id.rl_content);
        this.mRlContent = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.mRlContent.setOnLongClickListener(this);
        this.mTextView.setText(getTileLabel());
        this.mImageView.setImageResource(getTileImageRes());
        this.mImageView.setContentDescription(getTileId());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        boolean zIsSelected = this.mRlContent.isSelected();
        BydLogHelper.logD("isSelected:" + zIsSelected);
        if (this.mAlwaysUnSelected) {
            this.mRlContent.setSelected(false);
            this.mImageView.setSelectedStatus(false);
        } else if (!this.mAlwaysSelected) {
            this.mRlContent.setSelected(!zIsSelected);
            this.mImageView.setSelectedStatus(!zIsSelected);
        }
        click(!zIsSelected);
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View v) {
        longClick();
        return true;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void release() {
        BydLogHelper.logD("release", "release now!");
    }

    protected void refreshState() {
        RelativeLayout relativeLayout;
        TState tstate = this.mState;
        if (tstate == null || !(tstate instanceof QSTile.BooleanState) || (relativeLayout = this.mRlContent) == null || this.mImageView == null) {
            return;
        }
        relativeLayout.setSelected(((QSTile.BooleanState) tstate).value);
        this.mImageView.setSelectedStatus(((QSTile.BooleanState) this.mState).value);
        this.mRlContent.setClickable(true);
        this.mRlContent.setLongClickable(true);
    }

    public void updateTileLabel(String tileLabel) {
        TextView textView = this.mTextView;
        if (textView != null) {
            textView.setText(tileLabel);
        } else {
            BydLogHelper.logD("updateTileLabel", "mTextView is null");
        }
    }

    public void updateImgRes(int imgRes) {
        BydImageView bydImageView = this.mImageView;
        if (bydImageView != null) {
            bydImageView.setImageResource(imgRes);
        } else {
            BydLogHelper.logD("updateImgRes", "mImageView is null");
        }
    }

    public void updateSelected(boolean state) {
        RelativeLayout relativeLayout = this.mRlContent;
        if (relativeLayout != null && this.mImageView != null) {
            relativeLayout.setSelected(state);
            this.mImageView.setSelectedStatus(state);
        } else {
            BydLogHelper.logD("updateSelected", "mRlContent or mImageView is null");
        }
    }

    public void updateEnabled(boolean state) {
        BydImageView bydImageView;
        if (this.mTextView != null && (bydImageView = this.mImageView) != null) {
            bydImageView.setEnabledStatus(state);
            this.mTextView.setAlpha(state ? 1.0f : 0.45f);
        } else {
            BydLogHelper.logD("updateEnabled", "mRlContent or mImageView is null");
        }
    }

    public void updateItemEnabled(boolean state) {
        RelativeLayout relativeLayout;
        if (this.mTextView != null && this.mImageView != null && (relativeLayout = this.mRlContent) != null) {
            relativeLayout.setSelected(state);
            this.mRlContent.setEnabled(state);
            this.mImageView.setEnabled(state);
            this.mImageView.setSelected(state);
            this.mTextView.setAlpha(state ? 1.0f : 0.45f);
            return;
        }
        BydLogHelper.logD("updateItemEnabled", "mRlContent or mImageView is null");
    }

    public void updateItemDisabled() {
        RelativeLayout relativeLayout;
        if (this.mTextView != null && this.mImageView != null && (relativeLayout = this.mRlContent) != null) {
            relativeLayout.setSelected(false);
            this.mImageView.setEnabled(false);
            this.mImageView.setSelected(false);
            this.mTextView.setAlpha(0.45f);
            return;
        }
        BydLogHelper.logD("updateItemDisabled", "mRlContent or mImageView is null");
    }

    public void updateItemSelectState(boolean state) {
        RelativeLayout relativeLayout = this.mRlContent;
        if (relativeLayout != null) {
            relativeLayout.setSelected(state);
        } else {
            BydLogHelper.logD("updateItemDisabled", "mRlContent or mImageView is null");
        }
    }

    public void updateItemSelected(boolean state) {
        RelativeLayout relativeLayout;
        if (this.mTextView != null && this.mImageView != null && (relativeLayout = this.mRlContent) != null) {
            relativeLayout.setSelected(state);
            this.mRlContent.setEnabled(true);
            this.mImageView.setEnabled(state);
            this.mImageView.setSelectedStatus(state);
            this.mImageView.setAlpha(1.0f);
            this.mTextView.setAlpha(1.0f);
            return;
        }
        BydLogHelper.logD("updateItemSelected", "mRlContent or mImageView is null");
    }

    protected void showToast(String text) {
        int dimensionPixelSize;
        Toast toast = this.mToast;
        if (toast != null) {
            toast.cancel();
        }
        this.mToast = new Toast(this.mContext);
        Resources resources = this.mContext.getResources();
        if (isLand(this.mContext)) {
            dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.dp_72);
        } else {
            dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.dp_190);
        }
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.view_custom_toast, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_content);
        this.mToast.setView(viewInflate);
        this.mToast.setGravity(48, 0, dimensionPixelSize);
        textView.setText(text);
        this.mToast.setDuration(0);
        this.mToast.show();
    }

    public boolean isLand(Context context) {
        return context.getResources().getConfiguration().orientation != 1;
    }

    protected void showToastCustom(String text) {
        new CustomToast(this.mContext, text).show();
    }
}
