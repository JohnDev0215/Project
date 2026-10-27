package com.byd.vrsettings.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.content.ContextCompat;
import com.byd.vrdownload.model.LanguageUpdate;
import com.byd.vrsettings.R;
import com.byd.vrsettings.dialog.DescribeDialog;
import com.byd.vrsettings.model.LanguageBean;
import com.byd.vrsettings.model.LanguageLocal;
import com.byd.vrsettings.service.OtaDataManager;
import com.byd.vrsettings.utils.DimenUtil;
import com.byd.vrsettings.utils.LanguageUtil;
import com.byd.vrsettings.utils.LogUtils;
import com.byd.vrsettings.utils.ModeUtils;
import com.byd.vrsettings.utils.Tools;
import com.byd.vrsettings.view.AppTextView;
import com.byd.vrsettings.view.MultiFunProgressBar;
import com.chad.library.BuildConfig;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

/* JADX INFO: loaded from: classes.dex */
public class CustomAdapter extends BaseQuickAdapter<LanguageBean, BaseViewHolder> {
    private CustomClickListener btnClickListener;
    DescribeDialog.Builder builder;
    private CustomClickListener hotClickListener;
    private boolean isLandscape;
    private boolean isLight;
    private Context mContext;
    private int mPosition;

    public interface CustomClickListener {
        void onClick(int i, int i2);
    }

    public boolean isInTask(int i) {
        return i == 1 || i == 2 || i == 6;
    }

    public CustomAdapter(Context context) {
        super(R.layout.item_ota_upgrade);
        this.isLandscape = true;
        this.isLight = true;
        this.mPosition = 0;
        this.mContext = context;
        addChildClickViewIds(R.id.imageview_delete);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder baseViewHolder, final LanguageBean languageBean) {
        int i;
        String latestPkgSize;
        MultiFunProgressBar multiFunProgressBar = (MultiFunProgressBar) baseViewHolder.findView(R.id.multifun_progressbar);
        ImageView imageView = (ImageView) baseViewHolder.findView(R.id.imageview_delete);
        TextView textView = (TextView) baseViewHolder.findView(R.id.view_desc);
        View viewFindView = baseViewHolder.findView(R.id.view_update_point);
        if (languageBean.getSource() == 0) {
            LanguageLocal languageLocal = languageBean.getLanguageLocal();
            String localVersion = getLocalVersion(languageLocal.getVersion());
            String localPkgSize = languageBean.getLocalPkgSize();
            baseViewHolder.setText(R.id.textview_language, LanguageUtil.sencondLanguageConvert(this.mContext, languageBean.getLanguageLocal().getName()));
            baseViewHolder.setText(R.id.textview_country, LanguageUtil.topLanguageConvert(this.mContext, languageBean.getLanguageLocal().getName()));
            baseViewHolder.setText(R.id.textview_local_version_and_size, localVersion + "/" + localPkgSize);
            LogUtils.i("wens", languageLocal.getName() + "-size:" + localPkgSize);
            if (languageLocal.getUsed() == 1) {
                baseViewHolder.setImageResource(R.id.imageview_select, R.mipmap.icon_checked);
            } else {
                baseViewHolder.setImageResource(R.id.imageview_select, R.mipmap.icon_unchecked);
            }
            textView.setVisibility(8);
            viewFindView.setVisibility(8);
        } else {
            if (languageBean.getSource() == 1) {
                LanguageUpdate languageRemote = languageBean.getLanguageRemote();
                LanguageLocal languageLocal2 = languageBean.getLanguageLocal();
                String localVersion2 = getLocalVersion(languageLocal2.getVersion());
                String localPkgSize2 = languageBean.getLocalPkgSize();
                boolean z = languageBean.getState() == 4 && !languageBean.isUpdate();
                String latestPatchPkgSize = TextUtils.isEmpty(languageRemote.getLatestPatchPkgSize()) ? "0" : languageRemote.getLatestPatchPkgSize();
                latestPkgSize = TextUtils.isEmpty(languageRemote.getLatestPkgSize()) ? "0" : languageRemote.getLatestPkgSize();
                if (!languageRemote.isDifferentialUpdate()) {
                    latestPatchPkgSize = latestPkgSize;
                }
                String dataSizeValue = Tools.getDataSizeValue(latestPatchPkgSize);
                if (z) {
                    dataSizeValue = localPkgSize2;
                }
                String latestPkgVersion = z ? localVersion2 : languageRemote.getLatestPkgVersion();
                baseViewHolder.setText(R.id.textview_language, LanguageUtil.sencondLanguageConvert(this.mContext, languageBean.getLanguageRemote().getLanguage()));
                baseViewHolder.setText(R.id.textview_update_version_and_size, latestPkgVersion + "/" + dataSizeValue);
                baseViewHolder.setText(R.id.textview_local_version_and_size, localVersion2 + "/" + localPkgSize2);
                baseViewHolder.setText(R.id.textview_country, LanguageUtil.topLanguageConvert(this.mContext, languageBean.getLanguageRemote().getLanguage()));
                if (languageLocal2.getUsed() == 1) {
                    baseViewHolder.setImageResource(R.id.imageview_select, R.mipmap.icon_checked);
                } else {
                    baseViewHolder.setImageResource(R.id.imageview_select, R.mipmap.icon_unchecked);
                }
                boolean zCompareVersion = Tools.compareVersion(languageLocal2.getVersion().replace("V.", BuildConfig.FLAVOR), languageRemote.getLatestPkgVersion().replace("V.", BuildConfig.FLAVOR));
                if (languageRemote != null && languageRemote.getLatestPkgDesc() != null) {
                    textView.setVisibility((!zCompareVersion || TextUtils.isEmpty(languageRemote.getLatestPkgDesc().trim())) ? 8 : 0);
                } else {
                    textView.setVisibility(8);
                }
                viewFindView.setVisibility(zCompareVersion ? 0 : 8);
            } else if (languageBean.getSource() == 2) {
                LanguageUpdate languageRemote2 = languageBean.getLanguageRemote();
                if (languageRemote2 != null) {
                    baseViewHolder.setText(R.id.textview_language, LanguageUtil.sencondLanguageConvert(this.mContext, languageBean.getLanguageRemote().getLanguage()));
                    baseViewHolder.setText(R.id.textview_country, LanguageUtil.topLanguageConvert(this.mContext, languageBean.getLanguageRemote().getLanguage()));
                    String latestPatchPkgSize2 = TextUtils.isEmpty(languageRemote2.getLatestPatchPkgSize()) ? "0" : languageRemote2.getLatestPatchPkgSize();
                    latestPkgSize = TextUtils.isEmpty(languageRemote2.getLatestPkgSize()) ? "0" : languageRemote2.getLatestPkgSize();
                    if (!languageRemote2.isDifferentialUpdate()) {
                        latestPatchPkgSize2 = latestPkgSize;
                    }
                    baseViewHolder.setText(R.id.textview_update_version_and_size, languageRemote2.getLatestPkgVersion() + "/" + Tools.getDataSizeValue(latestPatchPkgSize2));
                }
                baseViewHolder.setImageResource(R.id.imageview_select, R.mipmap.icon_unchecked);
                if (languageRemote2 != null && languageRemote2.getLatestPkgDesc() != null) {
                    textView.setVisibility(TextUtils.isEmpty(languageRemote2.getLatestPkgDesc().trim()) ? 8 : 0);
                    i = 8;
                } else {
                    i = 8;
                    textView.setVisibility(8);
                }
                viewFindView.setVisibility(i);
            }
        }
        switchLandSpace(baseViewHolder, imageView, multiFunProgressBar, languageBean.getSource());
        switchMode(baseViewHolder, imageView, languageBean, multiFunProgressBar);
        multiFunProgressBar.setOnProgressClick(new MultiFunProgressBar.OnMultiFunProgressClick() { // from class: com.byd.vrsettings.adapter.CustomAdapter$$ExternalSyntheticLambda0
            @Override // com.byd.vrsettings.view.MultiFunProgressBar.OnMultiFunProgressClick
            public final void onClick(View view, int i2) {
                this.f$0.m25lambda$convert$0$combydvrsettingsadapterCustomAdapter(languageBean, view, i2);
            }
        });
        baseViewHolder.getView(R.id.view_hot_click).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.adapter.CustomAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m26lambda$convert$1$combydvrsettingsadapterCustomAdapter(languageBean, view);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.adapter.CustomAdapter$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m27lambda$convert$2$combydvrsettingsadapterCustomAdapter(languageBean, view);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$convert$0$com-byd-vrsettings-adapter-CustomAdapter, reason: not valid java name */
    /* synthetic */ void m25lambda$convert$0$combydvrsettingsadapterCustomAdapter(LanguageBean languageBean, View view, int i) {
        int itemPosition = getItemPosition(languageBean);
        CustomClickListener customClickListener = this.btnClickListener;
        if (customClickListener == null || itemPosition == -1) {
            return;
        }
        customClickListener.onClick(getItemPosition(languageBean), i);
    }

    /* JADX INFO: renamed from: lambda$convert$1$com-byd-vrsettings-adapter-CustomAdapter, reason: not valid java name */
    /* synthetic */ void m26lambda$convert$1$combydvrsettingsadapterCustomAdapter(LanguageBean languageBean, View view) {
        int itemPosition = getItemPosition(languageBean);
        CustomClickListener customClickListener = this.hotClickListener;
        if (customClickListener == null || itemPosition == -1) {
            return;
        }
        customClickListener.onClick(getItemPosition(languageBean), -1);
    }

    /* JADX INFO: renamed from: lambda$convert$2$com-byd-vrsettings-adapter-CustomAdapter, reason: not valid java name */
    /* synthetic */ void m27lambda$convert$2$combydvrsettingsadapterCustomAdapter(LanguageBean languageBean, View view) {
        DescribeDialog.Builder content = new DescribeDialog.Builder(this.mContext).setLight(!ModeUtils.isNightMode(this.mContext)).setContent(languageBean.getLanguageRemote() != null ? languageBean.getLanguageRemote().getLatestPkgDesc() : BuildConfig.FLAVOR);
        this.builder = content;
        content.create(languageBean).show();
    }

    public void setSelect(int i) {
        this.mPosition = i;
        notifyDataSetChanged();
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

    public void switchLandSpace(BaseViewHolder baseViewHolder, ImageView imageView, MultiFunProgressBar multiFunProgressBar, int i) {
        float f;
        float f2;
        float f3;
        float f4;
        int i2;
        float f5;
        ImageView imageView2 = (ImageView) baseViewHolder.findView(R.id.imageview_select);
        Guideline guideline = (Guideline) baseViewHolder.findView(R.id.guideline_data);
        Guideline guideline2 = (Guideline) baseViewHolder.findView(R.id.guideline_state);
        ImageView imageView3 = (ImageView) baseViewHolder.findView(R.id.textview_update_label);
        AppTextView appTextView = (AppTextView) baseViewHolder.findView(R.id.textview_update_version_and_size);
        AppTextView appTextView2 = (AppTextView) baseViewHolder.findView(R.id.textview_local_version_and_size);
        View viewFindView = baseViewHolder.findView(R.id.view_line);
        int i3 = 18;
        if (this.isLandscape) {
            guideline.setGuidelinePercent(0.36f);
            guideline2.setGuidelinePercent(0.73f);
            f2 = 33.0f;
            f3 = 70.0f;
            f = 48.0f;
            f4 = 110.0f;
            i2 = 18;
            i3 = 20;
            f5 = 60.0f;
        } else {
            guideline.setGuidelinePercent(0.4f);
            guideline2.setGuidelinePercent(0.7f);
            f = 32.0f;
            f2 = 0.0f;
            f3 = 28.0f;
            f4 = 72.0f;
            i2 = 14;
            f5 = 32.0f;
        }
        Guideline guideline3 = (Guideline) baseViewHolder.getView(R.id.guideline_version);
        if (i == 0) {
            imageView3.setVisibility(8);
            appTextView.setVisibility(8);
            appTextView2.setVisibility(0);
            guideline3.setGuidelinePercent(0.32f);
        } else if (i == 1) {
            imageView3.setVisibility(0);
            appTextView.setVisibility(0);
            appTextView2.setVisibility(0);
            guideline3.setGuidelinePercent(0.5f);
        } else {
            imageView3.setVisibility(8);
            appTextView.setVisibility(0);
            appTextView2.setVisibility(8);
            guideline3.setGuidelinePercent(0.68f);
        }
        ((ConstraintLayout.LayoutParams) multiFunProgressBar.getLayoutParams()).setMarginEnd(DimenUtil.dp2px(f2));
        ((ConstraintLayout.LayoutParams) imageView2.getLayoutParams()).setMarginStart(DimenUtil.dp2px(f3));
        ((ConstraintLayout.LayoutParams) imageView.getLayoutParams()).setMarginEnd(DimenUtil.dp2px(f));
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) viewFindView.getLayoutParams();
        layoutParams.setMarginStart(DimenUtil.dp2px(f4));
        layoutParams.setMarginEnd(DimenUtil.dp2px(f5));
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) viewFindView.getLayoutParams();
        layoutParams2.setMarginStart(DimenUtil.dp2px(f4));
        layoutParams2.setMarginEnd(DimenUtil.dp2px(f5));
        ((TextView) baseViewHolder.getView(R.id.textview_country)).setTextSize(1, i3);
        float f6 = i2;
        ((TextView) baseViewHolder.getView(R.id.textview_update_version_and_size)).setTextSize(1, f6);
        ((TextView) baseViewHolder.getView(R.id.textview_local_version_and_size)).setTextSize(1, f6);
        multiFunProgressBar.setLandScape(this.isLandscape);
    }

    public void switchMode(BaseViewHolder baseViewHolder, ImageView imageView, LanguageBean languageBean, MultiFunProgressBar multiFunProgressBar) {
        if (this.isLight) {
            int color = ContextCompat.getColor(this.mContext, R.color.color8c24313F);
            baseViewHolder.setTextColor(R.id.textview_language, color);
            baseViewHolder.setBackgroundResource(R.id.textview_update_label, R.mipmap.ota_update_light);
            baseViewHolder.setTextColor(R.id.textview_update_version_and_size, color);
            baseViewHolder.setTextColor(R.id.textview_local_version_and_size, color);
            if (languageBean.getState() == 0) {
                baseViewHolder.setTextColor(R.id.textview_country, color);
            } else {
                baseViewHolder.setTextColor(R.id.textview_country, ContextCompat.getColor(this.mContext, R.color.voice_setting_title));
            }
            setDeleteIcon(languageBean, imageView, this.isLight);
            baseViewHolder.setBackgroundColor(R.id.view_line, ContextCompat.getColor(this.mContext, R.color.bg_guideline));
            multiFunProgressBar.setState(languageBean.getState(), true, languageBean.isUpdate());
            multiFunProgressBar.setProgress(languageBean.getProgress());
            DescribeDialog.Builder builder = this.builder;
            if (builder != null) {
                builder.setLight(true);
                return;
            }
            return;
        }
        int color2 = ContextCompat.getColor(this.mContext, R.color.color_8cEBF4FE);
        baseViewHolder.setTextColor(R.id.textview_language, color2);
        baseViewHolder.setBackgroundResource(R.id.textview_update_label, R.mipmap.ota_update_night);
        baseViewHolder.setTextColor(R.id.textview_update_version_and_size, color2);
        baseViewHolder.setTextColor(R.id.textview_local_version_and_size, color2);
        if (languageBean.getState() == 0) {
            baseViewHolder.setTextColor(R.id.textview_country, ContextCompat.getColor(this.mContext, R.color.color_8cffffff));
        } else {
            baseViewHolder.setTextColor(R.id.textview_country, ContextCompat.getColor(this.mContext, R.color.white));
        }
        setDeleteIcon(languageBean, imageView, this.isLight);
        baseViewHolder.setBackgroundColor(R.id.view_line, ContextCompat.getColor(this.mContext, R.color.bg_skills_small_popup_night));
        multiFunProgressBar.setState(languageBean.getState(), false, languageBean.isUpdate());
        multiFunProgressBar.setProgress(languageBean.getProgress());
        DescribeDialog.Builder builder2 = this.builder;
        if (builder2 != null) {
            builder2.setLight(false);
        }
    }

    private void setDeleteIcon(LanguageBean languageBean, ImageView imageView, boolean z) {
        boolean zIsInTask = isInTask(languageBean.getState());
        LanguageLocal languageLocal = languageBean.getLanguageLocal();
        if (OtaDataManager.originalLocalData(false).size() <= 1 && !zIsInTask) {
            imageView.setVisibility(4);
            return;
        }
        int source = languageBean.getSource();
        if (source != 0) {
            if (source != 1) {
                if (source == 2) {
                    if (zIsInTask) {
                        imageView.setVisibility(0);
                    } else {
                        imageView.setVisibility(4);
                    }
                }
            } else if (zIsInTask) {
                imageView.setVisibility(0);
            } else if (languageBean.getState() == 7 || languageLocal.getName().contains("USA") || languageLocal.getUsed() == 1) {
                imageView.setVisibility(4);
            } else {
                imageView.setVisibility(0);
            }
        } else if (languageLocal.getUsed() == 1 || languageLocal.getName().contains("USA")) {
            imageView.setVisibility(4);
        } else {
            imageView.setVisibility(0);
        }
        if (z) {
            imageView.setImageResource(!zIsInTask ? R.drawable.icon_delete_selector_light : R.mipmap.ic_download_close);
        } else {
            imageView.setImageResource(!zIsInTask ? R.drawable.icon_delete_selector_night : R.mipmap.ic_download_close_dark);
        }
    }

    private String getLocalVersion(String str) {
        return !TextUtils.isEmpty(str) ? "V." + str.replaceFirst("V.", BuildConfig.FLAVOR) : "error";
    }
}
