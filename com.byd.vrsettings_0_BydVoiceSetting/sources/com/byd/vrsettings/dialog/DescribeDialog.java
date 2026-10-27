package com.byd.vrsettings.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import com.byd.vrsettings.R;
import com.byd.vrsettings.databinding.DialogDescribeBinding;
import com.byd.vrsettings.model.LanguageBean;
import com.byd.vrsettings.utils.LanguageUtil;
import com.chad.library.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class DescribeDialog extends Dialog {
    public DescribeDialog(Context context) {
        this(context, R.style.custom_dialog8155);
    }

    public DescribeDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {
        DialogDescribeBinding binding;
        View contentView;
        Context context;
        DescribeDialog dialog;

        public Builder(Context context) {
            this.context = context;
            DialogDescribeBinding dialogDescribeBindingInflate = DialogDescribeBinding.inflate((LayoutInflater) context.getSystemService("layout_inflater"));
            this.binding = dialogDescribeBindingInflate;
            this.contentView = dialogDescribeBindingInflate.getRoot();
        }

        public DescribeDialog create(LanguageBean languageBean) {
            ViewGroup viewGroup;
            View view = this.contentView;
            if (view != null && (viewGroup = (ViewGroup) view.getParent()) != null) {
                viewGroup.removeView(this.contentView);
            }
            if (this.dialog == null) {
                this.dialog = new DescribeDialog(this.context);
            }
            this.dialog.setContentView(this.contentView, new ViewGroup.LayoutParams(-2, -2));
            this.dialog.setCanceledOnTouchOutside(true);
            this.binding.textPositive.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.dialog.DescribeDialog$Builder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.m36lambda$create$0$combydvrsettingsdialogDescribeDialog$Builder(view2);
                }
            });
            this.binding.textviewTitle.setText(getTitle(languageBean));
            this.binding.dialogBg.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.dialog.DescribeDialog$Builder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.m37lambda$create$1$combydvrsettingsdialogDescribeDialog$Builder(view2);
                }
            });
            return this.dialog;
        }

        /* JADX INFO: renamed from: lambda$create$0$com-byd-vrsettings-dialog-DescribeDialog$Builder, reason: not valid java name */
        /* synthetic */ void m36lambda$create$0$combydvrsettingsdialogDescribeDialog$Builder(View view) {
            this.dialog.dismiss();
        }

        /* JADX INFO: renamed from: lambda$create$1$com-byd-vrsettings-dialog-DescribeDialog$Builder, reason: not valid java name */
        /* synthetic */ void m37lambda$create$1$combydvrsettingsdialogDescribeDialog$Builder(View view) {
            this.dialog.dismiss();
        }

        public Builder setContent(String str) {
            this.binding.textviewContent.setText(str);
            return this;
        }

        public Builder setLight(boolean z) {
            if (z) {
                this.binding.textviewTitle.setTextColor(ContextCompat.getColor(this.context, R.color.color262d33));
                this.binding.textviewContent.setTextColor(ContextCompat.getColor(this.context, R.color.color70black));
                this.binding.layoutRoot.setBackground(ContextCompat.getDrawable(this.context, R.drawable.shape_corner_8dp));
                this.binding.viewLine.setBackgroundColor(ContextCompat.getColor(this.context, R.color.color121F2326));
                this.binding.dialogBg.setBackgroundColor(ContextCompat.getColor(this.context, R.color.dialog_backgroud_color_day));
            } else {
                this.binding.textviewTitle.setTextColor(ContextCompat.getColor(this.context, R.color.white));
                this.binding.textviewContent.setTextColor(ContextCompat.getColor(this.context, R.color.color_b3EBF4FE));
                this.binding.layoutRoot.setBackground(ContextCompat.getDrawable(this.context, R.drawable.shape_corner_8dp_night));
                this.binding.viewLine.setBackgroundColor(ContextCompat.getColor(this.context, R.color.voice_setting_title));
                this.binding.dialogBg.setBackgroundColor(ContextCompat.getColor(this.context, R.color.dialog_backgroud_color_night));
            }
            return this;
        }

        private String getTitle(LanguageBean languageBean) {
            if (languageBean.getLanguageLocal() == null || TextUtils.isEmpty(languageBean.getLanguageLocal().getName())) {
                return (languageBean.getLanguageRemote() == null || TextUtils.isEmpty(languageBean.getLanguageRemote().getLanguage())) ? BuildConfig.FLAVOR : LanguageUtil.getLanguage(this.context, languageBean.getLanguageRemote().getLanguage());
            }
            return LanguageUtil.getLanguage(this.context, languageBean.getLanguageLocal().getName());
        }
    }
}
