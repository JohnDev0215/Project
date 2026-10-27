package com.byd.card.floatcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.R;
import com.byd.card.bean.MediaCardMenuBean;
import com.byd.card.utils.AppUtils;
import com.byd.mediacenter.api.media.MediaControl;
import com.bydux.api.common.views.skin.loader.SkinManager;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MediaCardPopupAdapter extends RecyclerView.Adapter<MediaPopupMenuViewHolder> {
    private static final String TAG = "MediaCardPopupAdapter";
    private OnClickItemListener listener;
    private Context mContext;
    private List<MediaCardMenuBean> mData;

    public interface OnClickItemListener {
        void itemClick();
    }

    public void setOnClickItemListener(OnClickItemListener listener) {
        this.listener = listener;
    }

    public MediaCardPopupAdapter(Context context) {
        this.mContext = context;
    }

    public void updateAdapter(List<MediaCardMenuBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MediaPopupMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewInflate;
        if (AppUtils.isRightRudder()) {
            viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.media_card_menu_right, parent, false);
        } else {
            viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.media_card_menu, parent, false);
        }
        return new MediaPopupMenuViewHolder(viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MediaPopupMenuViewHolder holder, final int position) {
        final MediaCardMenuBean mediaCardMenuBean = this.mData.get(position);
        final int mode = mediaCardMenuBean.getMode();
        setIconAndName(holder, mode, mediaCardMenuBean.getMediaSource());
        if (mediaCardMenuBean.isChecked()) {
            holder.mCheck.setBackground(SkinManager.getInstance().getMipmapDrawable(R.mipmap.ac_bar_defrost_custom_checked));
            holder.mCheck.setVisibility(0);
        } else {
            holder.mCheck.setVisibility(8);
        }
        if (position == this.mData.size() - 1) {
            holder.mDivider.setVisibility(8);
        } else {
            holder.mDivider.setVisibility(0);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.card.floatcard.-$$Lambda$MediaCardPopupAdapter$q5FgwS2K4MYbIP5dtOerTIlzuVU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onBindViewHolder$0$MediaCardPopupAdapter(mode, mediaCardMenuBean, position, view);
            }
        });
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$MediaCardPopupAdapter(int i, MediaCardMenuBean mediaCardMenuBean, int i2, View view) {
        if (i == 1) {
            MediaControl.Param param = new MediaControl.Param();
            param.setParam("source", mediaCardMenuBean.getMediaSource());
            param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            MediaControl.controlMedia(this.mContext, 1, 19, param, null);
        } else {
            MediaControl.controlMedia(this.mContext, i, 19, null, null);
        }
        for (int i3 = 0; i3 < this.mData.size(); i3++) {
            if (i2 == i3) {
                this.mData.get(i3).setChecked(true);
            } else {
                this.mData.get(i3).setChecked(false);
            }
        }
        OnClickItemListener onClickItemListener = this.listener;
        if (onClickItemListener != null) {
            onClickItemListener.itemClick();
        }
        notifyDataSetChanged();
    }

    private void setIconAndName(MediaPopupMenuViewHolder holder, int mode, int localType) {
        if (mode == 1) {
            if (localType == 1) {
                holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_media_local_usb));
                holder.mName.setText(this.mContext.getString(R.string.media_pop_usb));
                return;
            } else if (localType != 2) {
                holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_media));
                holder.mName.setText(this.mContext.getString(R.string.media_pop_music));
                return;
            } else {
                holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_media_local_sd));
                holder.mName.setText(this.mContext.getString(R.string.media_pop_sd_card));
                return;
            }
        }
        if (mode != 8) {
            switch (mode) {
                case 11:
                    holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_fm));
                    holder.mName.setText(this.mContext.getString(R.string.media_pop_fm));
                    break;
                case 12:
                    holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_am));
                    holder.mName.setText(this.mContext.getString(R.string.media_pop_am));
                    break;
                case 13:
                    holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_dab));
                    holder.mName.setText(this.mContext.getString(R.string.media_pop_dab));
                    break;
                default:
                    switch (mode) {
                        case 101:
                            holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_app_android_auto));
                            holder.mName.setText(this.mContext.getString(R.string.media_pop_auto));
                            break;
                        case 102:
                            holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_app_carplay));
                            holder.mName.setText(this.mContext.getString(R.string.media_pop_carplay));
                            break;
                        case 103:
                            holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_app_spotify));
                            holder.mName.setText(this.mContext.getString(R.string.media_pop_spotify));
                            break;
                        case 104:
                            holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_app_amazon));
                            holder.mName.setText(this.mContext.getString(R.string.media_pop_amazon));
                            break;
                        case 105:
                            holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_app_flo));
                            holder.mName.setText(this.mContext.getString(R.string.media_pop_flo));
                            break;
                        case 106:
                            holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_app_audial_play));
                            holder.mName.setText(this.mContext.getString(R.string.media_pop_audials_play));
                            break;
                        case 107:
                            holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_app_audible));
                            holder.mName.setText(this.mContext.getString(R.string.media_pop_audible));
                            break;
                        case 108:
                            holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_app_bloomberg));
                            holder.mName.setText(this.mContext.getString(R.string.media_pop_bloomberg));
                            break;
                        case 109:
                            holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_app_deezer));
                            holder.mName.setText(this.mContext.getString(R.string.media_pop_deezer));
                            break;
                        case 110:
                            holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_app_iheart_radio));
                            holder.mName.setText(this.mContext.getString(R.string.media_pop_iheart_radio));
                            break;
                        case 111:
                            holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_app_libby));
                            holder.mName.setText(this.mContext.getString(R.string.media_pop_libby));
                            break;
                        case 112:
                            holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_app_melon));
                            holder.mName.setText(this.mContext.getString(R.string.media_pop_melon));
                            break;
                        case 113:
                            holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_app_pocket_casts));
                            holder.mName.setText(this.mContext.getString(R.string.media_pop_pocket_casts));
                            break;
                        case 114:
                            holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_app_radioline));
                            holder.mName.setText(this.mContext.getString(R.string.media_pop_radioline));
                            break;
                        case 115:
                            holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_app_tidal));
                            holder.mName.setText(this.mContext.getString(R.string.media_pop_tidal));
                            break;
                        case 116:
                            holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_app_tunein));
                            holder.mName.setText(this.mContext.getString(R.string.media_pop_tuneln_radio));
                            break;
                    }
                    break;
            }
            return;
        }
        holder.mIcon.setBackground(SkinManager.getInstance().getDrawable(R.drawable.icon_music));
        holder.mName.setText(this.mContext.getString(R.string.media_pop_blue));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MediaCardMenuBean> list = this.mData;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    static class MediaPopupMenuViewHolder extends RecyclerView.ViewHolder {
        private ImageView mCheck;
        private View mDivider;
        private ImageView mIcon;
        private TextView mName;

        public MediaPopupMenuViewHolder(View itemView) {
            super(itemView);
            this.mIcon = (ImageView) itemView.findViewById(R.id.media_card_menu_icon);
            this.mName = (TextView) itemView.findViewById(R.id.media_card_menu_name);
            this.mCheck = (ImageView) itemView.findViewById(R.id.media_card_menu_ck);
            this.mDivider = itemView.findViewById(R.id.divider);
        }
    }
}
