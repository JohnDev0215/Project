package com.android.systemui.qs.tiles;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.phone.StatusBar;
import com.byd.profile.ProfileManager;

/* JADX INFO: loaded from: classes.dex */
public class SceneCommonTile extends QSTileImpl {
    private int imgRes;
    private ImageView mCustomImg;
    private final int mModelId;
    private final ProfileManager.OnModeChangeListener mOnModeChangeListener;
    private boolean showCustomImg;
    private StatusBar statusBar;
    private String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    protected int getLayoutId() {
        return R.layout.item_scene_mode;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public QSTile.State newTileState() {
        return null;
    }

    public boolean isShowCustomImg() {
        return this.showCustomImg;
    }

    public SceneCommonTile setShowCustomImg(boolean showCustomImg) {
        this.showCustomImg = showCustomImg;
        return this;
    }

    public SceneCommonTile(Context context, String tileLabel, int imgRes) {
        super(context);
        ProfileManager.OnModeChangeListener onModeChangeListener = new ProfileManager.OnModeChangeListener() { // from class: com.android.systemui.qs.tiles.SceneCommonTile.1
            @Override // com.byd.profile.ProfileManager.OnModeChangeListener
            public void onModeChange(int mode, boolean isChecked) {
                if (SceneCommonTile.this.mRlContent != null) {
                    if (mode == SceneCommonTile.this.mModelId) {
                        SceneCommonTile.this.mRlContent.setSelected(isChecked);
                    }
                    if (isChecked) {
                        if (mode == 2 || mode == 3 || mode == 5) {
                            SceneCommonTile.this.statusBar.closePanelsIfNeed();
                        }
                    }
                }
            }
        };
        this.mOnModeChangeListener = onModeChangeListener;
        this.tileLabel = tileLabel;
        this.imgRes = imgRes;
        this.statusBar = (StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class);
        this.mModelId = initModelId(context, tileLabel);
        ProfileManager.getInstance(this.mContext).registerOnModeChangeListener(onModeChangeListener);
    }

    public static int initModelId(Context context, String tileLabel) {
        if (TextUtils.equals(tileLabel, context.getString(R.string.dialog_title_rest))) {
            return 2;
        }
        return TextUtils.equals(tileLabel, context.getString(R.string.dialog_title_black)) ? 5 : 0;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public String getTileLabel() {
        return this.tileLabel;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public int getTileImageRes() {
        return this.imgRes;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        handlerClick();
    }

    private void handlerClick() {
        this.mRlContent.setSelected(false);
        ProfileManager.getInstance(this.mContext).operateMode(this.mModelId);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
        ProfileManager.getInstance(this.mContext).editMode(this.mModelId, this.mContext);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        if (this.showCustomImg) {
            this.mCustomImg = (ImageView) viewHolder.itemView.findViewById(R.id.iv_custom);
            if (TextUtils.equals(this.tileLabel, this.mContext.getString(R.string.dialog_title_rest))) {
                this.mCustomImg.setVisibility(0);
            } else {
                this.mCustomImg.setVisibility(8);
            }
            this.mCustomImg.setOnClickListener(new OnClickCustom());
        }
        this.mRlContent.setSelected(ProfileManager.getInstance(this.mContext).getProfileMode() == this.mModelId);
    }

    private class OnClickCustom implements View.OnClickListener {
        private OnClickCustom() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            SceneCommonTile.this.handlerCustomClick();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerCustomClick() {
        ProfileManager.getInstance(this.mContext).editMode(this.mModelId, this.mContext);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void release() {
        super.release();
        ProfileManager.getInstance(this.mContext).unregisterOnModeChangeListener(this.mOnModeChangeListener);
    }

    public String toString() {
        return "SceneCommonTile{tileLabel='" + this.tileLabel + "', mModelId=" + this.mModelId + '}';
    }
}
