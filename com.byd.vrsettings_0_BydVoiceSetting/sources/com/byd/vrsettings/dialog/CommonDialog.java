package com.byd.vrsettings.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import com.byd.vrsettings.R;
import com.byd.vrsettings.databinding.DialogCommonBinding;
import com.byd.vrsettings.utils.Common;

/* JADX INFO: loaded from: classes.dex */
public class CommonDialog extends Dialog {
    public CommonDialog(Context context) {
        this(context, R.style.custom_dialog8155);
    }

    public CommonDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {
        DialogCommonBinding binding;
        View contentView;
        Context context;
        CommonDialog dialog;
        ConstraintSet set;

        public Builder(Context context) {
            this.context = context;
            DialogCommonBinding dialogCommonBindingInflate = DialogCommonBinding.inflate((LayoutInflater) context.getSystemService("layout_inflater"));
            this.binding = dialogCommonBindingInflate;
            this.contentView = dialogCommonBindingInflate.getRoot();
            ConstraintSet constraintSet = new ConstraintSet();
            this.set = constraintSet;
            constraintSet.clone(this.binding.layoutDialogRoot);
            if (Common.isRightDriver() || Common.isRtl(context)) {
                this.set.clear(R.id.positive, 6);
                this.set.clear(R.id.positive, 7);
                this.set.connect(R.id.positive, 7, 0, 7, (int) TypedValue.applyDimension(1, 104.0f, context.getResources().getDisplayMetrics()));
                this.set.connect(R.id.positive, 6, R.id.negative, 7, (int) TypedValue.applyDimension(1, 48.0f, context.getResources().getDisplayMetrics()));
                this.set.clear(R.id.negative, 6);
                this.set.clear(R.id.negative, 7);
                this.set.connect(R.id.negative, 7, R.id.positive, 6);
                this.set.connect(R.id.negative, 6, 0, 6, (int) TypedValue.applyDimension(1, 104.0f, context.getResources().getDisplayMetrics()));
            } else {
                this.set.clear(R.id.negative, 6);
                this.set.clear(R.id.negative, 7);
                this.set.connect(R.id.negative, 7, 0, 7, (int) TypedValue.applyDimension(1, 104.0f, context.getResources().getDisplayMetrics()));
                this.set.connect(R.id.negative, 6, R.id.positive, 7, (int) TypedValue.applyDimension(1, 48.0f, context.getResources().getDisplayMetrics()));
                this.set.clear(R.id.positive, 6);
                this.set.clear(R.id.positive, 7);
                this.set.connect(R.id.positive, 7, R.id.negative, 6);
                this.set.connect(R.id.positive, 6, 0, 6, (int) TypedValue.applyDimension(1, 104.0f, context.getResources().getDisplayMetrics()));
            }
            this.set.applyTo(this.binding.layoutDialogRoot);
        }

        public CommonDialog create() {
            ViewGroup viewGroup;
            View view = this.contentView;
            if (view != null && (viewGroup = (ViewGroup) view.getParent()) != null) {
                viewGroup.removeView(this.contentView);
            }
            if (this.dialog == null) {
                this.dialog = new CommonDialog(this.context);
            }
            this.dialog.setContentView(this.contentView, new ViewGroup.LayoutParams(-2, -2));
            this.dialog.setCanceledOnTouchOutside(true);
            this.binding.negative.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.dialog.CommonDialog$Builder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.m28lambda$create$0$combydvrsettingsdialogCommonDialog$Builder(view2);
                }
            });
            this.binding.dialogBg.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.dialog.CommonDialog$Builder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.m29lambda$create$1$combydvrsettingsdialogCommonDialog$Builder(view2);
                }
            });
            return this.dialog;
        }

        /* JADX INFO: renamed from: lambda$create$0$com-byd-vrsettings-dialog-CommonDialog$Builder, reason: not valid java name */
        /* synthetic */ void m28lambda$create$0$combydvrsettingsdialogCommonDialog$Builder(View view) {
            this.dialog.dismiss();
        }

        /* JADX INFO: renamed from: lambda$create$1$com-byd-vrsettings-dialog-CommonDialog$Builder, reason: not valid java name */
        /* synthetic */ void m29lambda$create$1$combydvrsettingsdialogCommonDialog$Builder(View view) {
            this.dialog.dismiss();
        }

        public Builder setContent(String str) {
            this.binding.textviewContent.setText(str);
            return this;
        }

        public Builder setCheckBoxText(String str) {
            this.binding.textviewRadio.setText(str);
            return this;
        }

        public Builder setPositiveClickLitener(String str, final View.OnClickListener onClickListener) {
            this.binding.positive.setText(str);
            this.binding.positive.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.dialog.CommonDialog$Builder$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m31xe6bfd6a0(onClickListener, view);
                }
            });
            return this;
        }

        /* JADX INFO: renamed from: lambda$setPositiveClickLitener$2$com-byd-vrsettings-dialog-CommonDialog$Builder, reason: not valid java name */
        /* synthetic */ void m31xe6bfd6a0(View.OnClickListener onClickListener, View view) {
            this.dialog.dismiss();
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }

        public Builder setNegativeClickLitener(String str, final View.OnClickListener onClickListener) {
            this.binding.negative.setText(str);
            this.binding.negative.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.dialog.CommonDialog$Builder$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m30x830d6fc3(onClickListener, view);
                }
            });
            return this;
        }

        /* JADX INFO: renamed from: lambda$setNegativeClickLitener$3$com-byd-vrsettings-dialog-CommonDialog$Builder, reason: not valid java name */
        /* synthetic */ void m30x830d6fc3(View.OnClickListener onClickListener, View view) {
            this.dialog.dismiss();
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }

        public Builder setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
            this.binding.checkbox.setOnCheckedChangeListener(onCheckedChangeListener);
            return this;
        }

        public boolean getChecked() {
            return this.binding.checkbox.isChecked();
        }

        public Builder setChecked(boolean z) {
            this.binding.checkbox.setChecked(z);
            return this;
        }

        public Builder setLight(boolean z) {
            if (z) {
                this.binding.textviewContent.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title));
                this.binding.textviewRadio.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title));
                this.binding.layoutDialogRoot.setBackgroundResource(R.drawable.bg_setting_dialog);
                this.binding.negative.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title));
                this.binding.dialogBg.setBackgroundColor(ContextCompat.getColor(this.context, R.color.dialog_backgroud_color_day));
            } else {
                this.binding.textviewContent.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title_night));
                this.binding.textviewRadio.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title_night));
                this.binding.layoutDialogRoot.setBackgroundResource(R.drawable.bg_setting_dialog_night);
                this.binding.negative.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title_night));
                this.binding.dialogBg.setBackgroundColor(ContextCompat.getColor(this.context, R.color.dialog_backgroud_color_night));
            }
            return this;
        }
    }
}
