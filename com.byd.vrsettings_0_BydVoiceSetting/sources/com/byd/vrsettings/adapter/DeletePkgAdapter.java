package com.byd.vrsettings.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.content.ContextCompat;
import com.byd.vrsettings.R;
import com.byd.vrsettings.model.LanguageLocal;
import com.byd.vrsettings.utils.DimenUtil;
import com.byd.vrsettings.utils.FileUtil;
import com.byd.vrsettings.utils.LanguageUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

/* JADX INFO: loaded from: classes.dex */
public class DeletePkgAdapter extends BaseQuickAdapter<LanguageLocal, BaseViewHolder> {
    private CustomClickListener btnClickListener;
    private CustomClickListener hotClickListener;
    private boolean isLandscape;
    private boolean isLight;
    private Context mContext;

    public interface CustomClickListener {
        void onClick(int i, int i2);
    }

    public DeletePkgAdapter(Context context) {
        super(R.layout.item_delete_pkg);
        this.isLandscape = true;
        this.isLight = true;
        this.mContext = context;
        addChildClickViewIds(R.id.imageview_delete);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, LanguageLocal languageLocal) {
        ImageView imageView = (ImageView) baseViewHolder.findView(R.id.imageview_delete);
        String str = LanguageUtil.topLanguageConvertBackDoor(this.mContext, languageLocal.getName());
        baseViewHolder.setText(R.id.textview_language, LanguageUtil.sencondLanguageConvertBackDoor(this.mContext, languageLocal.getName()));
        baseViewHolder.setText(R.id.textview_country, str);
        baseViewHolder.setText(R.id.textview_size_data, FileUtil.getFileSize(languageLocal.getPath()));
        baseViewHolder.setText(R.id.textview_version_data, "V." + languageLocal.getVersion());
        if (languageLocal.getName().contains("USA")) {
            imageView.setVisibility(4);
        } else {
            imageView.setVisibility(0);
        }
        setDeleteIcon(imageView, this.isLight);
        switchLandSpace(baseViewHolder, imageView);
        switchMode(baseViewHolder, languageLocal);
    }

    public void setBtnClickListener(CustomClickListener customClickListener) {
        this.btnClickListener = customClickListener;
    }

    public void setHotClickListener(CustomClickListener customClickListener) {
        this.hotClickListener = customClickListener;
    }

    public void setLandscape(boolean z) {
        this.isLandscape = z;
        notifyDataSetChanged();
    }

    public void setLight(boolean z) {
        this.isLight = z;
        notifyDataSetChanged();
    }

    public void switchLandSpace(BaseViewHolder baseViewHolder, ImageView imageView) {
        ImageView imageView2 = (ImageView) baseViewHolder.findView(R.id.imageview_select);
        Guideline guideline = (Guideline) baseViewHolder.findView(R.id.guideline_data);
        Guideline guideline2 = (Guideline) baseViewHolder.findView(R.id.guideline_state);
        View viewFindView = baseViewHolder.findView(R.id.view_line);
        if (this.isLandscape) {
            guideline.setGuidelinePercent(0.36f);
            guideline2.setGuidelinePercent(0.73f);
            baseViewHolder.setGone(R.id.textview_size_label, false);
            baseViewHolder.setGone(R.id.textview_version_label, false);
            ((ConstraintLayout.LayoutParams) imageView2.getLayoutParams()).setMarginStart(DimenUtil.dp2px(70.0f));
            ((ConstraintLayout.LayoutParams) imageView.getLayoutParams()).setMarginEnd(DimenUtil.dp2px(48.0f));
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) viewFindView.getLayoutParams();
            layoutParams.setMarginStart(DimenUtil.dp2px(110.0f));
            layoutParams.setMarginEnd(DimenUtil.dp2px(60.0f));
            ((TextView) baseViewHolder.getView(R.id.textview_country)).setTextSize(1, 20.0f);
            ((TextView) baseViewHolder.getView(R.id.textview_size_label)).setTextSize(1, 18.0f);
            ((TextView) baseViewHolder.getView(R.id.textview_size_data)).setTextSize(1, 18.0f);
            ((TextView) baseViewHolder.getView(R.id.textview_version_label)).setTextSize(1, 18.0f);
            ((TextView) baseViewHolder.getView(R.id.textview_version_data)).setTextSize(1, 18.0f);
            return;
        }
        guideline.setGuidelinePercent(0.43f);
        guideline2.setGuidelinePercent(0.7f);
        baseViewHolder.setGone(R.id.textview_size_label, true);
        baseViewHolder.setGone(R.id.textview_version_label, true);
        ((ConstraintLayout.LayoutParams) imageView2.getLayoutParams()).setMarginStart(DimenUtil.dp2px(28.0f));
        ((ConstraintLayout.LayoutParams) imageView.getLayoutParams()).setMarginEnd(DimenUtil.dp2px(32.0f));
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) viewFindView.getLayoutParams();
        layoutParams2.setMarginStart(DimenUtil.dp2px(72.0f));
        layoutParams2.setMarginEnd(DimenUtil.dp2px(32.0f));
        ((TextView) baseViewHolder.getView(R.id.textview_country)).setTextSize(1, 18.0f);
        ((TextView) baseViewHolder.getView(R.id.textview_size_label)).setTextSize(1, 14.0f);
        ((TextView) baseViewHolder.getView(R.id.textview_size_data)).setTextSize(1, 14.0f);
        ((TextView) baseViewHolder.getView(R.id.textview_version_label)).setTextSize(1, 14.0f);
        ((TextView) baseViewHolder.getView(R.id.textview_version_data)).setTextSize(1, 14.0f);
    }

    public void switchMode(BaseViewHolder baseViewHolder, LanguageLocal languageLocal) {
        if (this.isLight) {
            int color = ContextCompat.getColor(this.mContext, R.color.color8c24313F);
            baseViewHolder.setTextColor(R.id.textview_language, color);
            baseViewHolder.setTextColor(R.id.textview_size_label, color);
            baseViewHolder.setTextColor(R.id.textview_size_data, color);
            baseViewHolder.setTextColor(R.id.textview_version_label, color);
            baseViewHolder.setTextColor(R.id.textview_version_data, color);
            baseViewHolder.setTextColor(R.id.textview_country, ContextCompat.getColor(this.mContext, R.color.voice_setting_title));
            baseViewHolder.setImageResource(R.id.imageview_delete, R.drawable.icon_delete_selector_light);
            baseViewHolder.setBackgroundColor(R.id.view_line, ContextCompat.getColor(this.mContext, R.color.bg_guideline));
            return;
        }
        int color2 = ContextCompat.getColor(this.mContext, R.color.color_8cEBF4FE);
        baseViewHolder.setTextColor(R.id.textview_language, color2);
        baseViewHolder.setTextColor(R.id.textview_size_label, color2);
        baseViewHolder.setTextColor(R.id.textview_size_data, color2);
        baseViewHolder.setTextColor(R.id.textview_version_label, color2);
        baseViewHolder.setTextColor(R.id.textview_version_data, color2);
        baseViewHolder.setTextColor(R.id.textview_country, ContextCompat.getColor(this.mContext, R.color.white));
        baseViewHolder.setImageResource(R.id.imageview_delete, R.drawable.icon_delete_selector_night);
        baseViewHolder.setBackgroundColor(R.id.view_line, ContextCompat.getColor(this.mContext, R.color.bg_skills_small_popup_night));
    }

    private void setDeleteIcon(ImageView imageView, boolean z) {
        if (getData().size() <= 1) {
            imageView.setEnabled(false);
            return;
        }
        imageView.setEnabled(true);
        if (z) {
            imageView.setImageResource(R.drawable.icon_delete_selector_light);
        } else {
            imageView.setImageResource(R.drawable.icon_delete_selector_night);
        }
    }
}
