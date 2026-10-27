package com.byd.vrsettings.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import com.byd.vrsettings.R;
import com.byd.vrsettings.databinding.DialogOneButtonBinding;

/* JADX INFO: loaded from: classes.dex */
public class OneButtonDialog extends Dialog {
    public OneButtonDialog(Context context) {
        this(context, R.style.custom_dialog8155);
    }

    public OneButtonDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {
        DialogOneButtonBinding binding;
        View contentView;
        Context context;
        OneButtonDialog dialog;
        ConstraintSet set;

        public Builder(Context context) {
            this.context = context;
            DialogOneButtonBinding dialogOneButtonBindingInflate = DialogOneButtonBinding.inflate((LayoutInflater) context.getSystemService("layout_inflater"));
            this.binding = dialogOneButtonBindingInflate;
            this.contentView = dialogOneButtonBindingInflate.getRoot();
        }

        public OneButtonDialog create() {
            ViewGroup viewGroup;
            View view = this.contentView;
            if (view != null && (viewGroup = (ViewGroup) view.getParent()) != null) {
                viewGroup.removeView(this.contentView);
            }
            if (this.dialog == null) {
                this.dialog = new OneButtonDialog(this.context);
            }
            this.dialog.setContentView(this.contentView, new ViewGroup.LayoutParams(-2, -2));
            this.dialog.setCanceledOnTouchOutside(true);
            this.binding.dialogBg.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.dialog.OneButtonDialog$Builder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.m38xa2671035(view2);
                }
            });
            return this.dialog;
        }

        /* JADX INFO: renamed from: lambda$create$0$com-byd-vrsettings-dialog-OneButtonDialog$Builder, reason: not valid java name */
        /* synthetic */ void m38xa2671035(View view) {
            this.dialog.dismiss();
        }

        public Builder setTitle(String str) {
            this.binding.textiewTitle.setVisibility(0);
            this.binding.textiewTitle.setText(str);
            return this;
        }

        public Builder setContent(String str) {
            this.binding.textviewContent.setText(str);
            return this;
        }

        public Builder setLight(boolean z) {
            if (z) {
                this.binding.textiewTitle.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title));
                this.binding.textviewContent.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title));
                this.binding.layoutDialogRoot.setBackgroundResource(R.drawable.bg_setting_dialog);
                this.binding.positive.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title));
                this.binding.dialogBg.setBackgroundColor(ContextCompat.getColor(this.context, R.color.dialog_backgroud_color_day));
            } else {
                this.binding.textiewTitle.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title_night));
                this.binding.textviewContent.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title_night));
                this.binding.layoutDialogRoot.setBackgroundResource(R.drawable.bg_setting_dialog_night);
                this.binding.positive.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title_night));
                this.binding.dialogBg.setBackgroundColor(ContextCompat.getColor(this.context, R.color.dialog_backgroud_color_night));
            }
            return this;
        }

        public Builder setPositiveClickLitener(String str, final View.OnClickListener onClickListener) {
            this.binding.positive.setText(str);
            this.binding.positive.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.dialog.OneButtonDialog$Builder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m39x6debe640(onClickListener, view);
                }
            });
            return this;
        }

        /* JADX INFO: renamed from: lambda$setPositiveClickLitener$1$com-byd-vrsettings-dialog-OneButtonDialog$Builder, reason: not valid java name */
        /* synthetic */ void m39x6debe640(View.OnClickListener onClickListener, View view) {
            ViewGroup viewGroup = (ViewGroup) this.contentView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.contentView);
            }
            this.dialog.dismiss();
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }
}
