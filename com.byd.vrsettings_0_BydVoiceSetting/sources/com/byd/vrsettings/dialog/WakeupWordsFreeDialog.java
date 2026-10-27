package com.byd.vrsettings.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import com.byd.vrsettings.R;
import com.byd.vrsettings.databinding.DialogWakeupWordsFreeTipBinding;
import com.byd.vrsettings.interf.OnBackPressInterface;
import com.byd.vrsettings.utils.Common;

/* JADX INFO: loaded from: classes.dex */
public class WakeupWordsFreeDialog extends Dialog {
    OnBackPressInterface onBackPressInterface;

    public WakeupWordsFreeDialog(Context context) {
        this(context, R.style.custom_dialog8155);
    }

    public WakeupWordsFreeDialog(Context context, int i) {
        super(context, i);
    }

    public WakeupWordsFreeDialog(Context context, OnBackPressInterface onBackPressInterface) {
        this(context, R.style.custom_dialog8155);
        this.onBackPressInterface = onBackPressInterface;
    }

    public static class Builder {
        DialogWakeupWordsFreeTipBinding binding;
        View contentView;
        Context context;
        WakeupWordsFreeDialog dialog;
        boolean isTouchOutsizeDismiss = true;
        ConstraintSet set;

        public Builder(Context context) {
            this.context = context;
            DialogWakeupWordsFreeTipBinding dialogWakeupWordsFreeTipBindingInflate = DialogWakeupWordsFreeTipBinding.inflate((LayoutInflater) context.getSystemService("layout_inflater"));
            this.binding = dialogWakeupWordsFreeTipBindingInflate;
            this.contentView = dialogWakeupWordsFreeTipBindingInflate.getRoot();
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

        public WakeupWordsFreeDialog create() {
            create(null);
            return this.dialog;
        }

        public WakeupWordsFreeDialog create(OnBackPressInterface onBackPressInterface) {
            ViewGroup viewGroup;
            View view = this.contentView;
            if (view != null && (viewGroup = (ViewGroup) view.getParent()) != null) {
                viewGroup.removeView(this.contentView);
            }
            if (this.dialog == null) {
                if (onBackPressInterface != null) {
                    this.dialog = new WakeupWordsFreeDialog(this.context, onBackPressInterface);
                } else {
                    this.dialog = new WakeupWordsFreeDialog(this.context);
                }
            }
            this.dialog.setContentView(this.contentView, new ViewGroup.LayoutParams(-2, -2));
            this.dialog.setCanceledOnTouchOutside(this.isTouchOutsizeDismiss);
            if (onBackPressInterface == null && !this.dialog.isShowing()) {
                this.binding.negative.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.dialog.WakeupWordsFreeDialog$Builder$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f$0.m45x48445033(view2);
                    }
                });
            }
            return this.dialog;
        }

        /* JADX INFO: renamed from: lambda$create$0$com-byd-vrsettings-dialog-WakeupWordsFreeDialog$Builder, reason: not valid java name */
        /* synthetic */ void m45x48445033(View view) {
            this.dialog.dismiss();
        }

        public Builder setTouchOutsizeDismiss(boolean z) {
            this.isTouchOutsizeDismiss = z;
            return this;
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
                this.binding.negative.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title));
                this.binding.dialogBg.setBackgroundColor(ContextCompat.getColor(this.context, R.color.dialog_backgroud_color_day));
            } else {
                this.binding.textiewTitle.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title_night));
                this.binding.textviewContent.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title_night));
                this.binding.layoutDialogRoot.setBackgroundResource(R.drawable.bg_setting_dialog_night);
                this.binding.negative.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_title_night));
                this.binding.dialogBg.setBackgroundColor(ContextCompat.getColor(this.context, R.color.dialog_backgroud_color_night));
            }
            return this;
        }

        public Builder setPositiveClickLitener(String str, final View.OnClickListener onClickListener) {
            this.binding.positive.setText(str);
            this.binding.positive.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.dialog.WakeupWordsFreeDialog$Builder$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m48x1c3531fe(onClickListener, view);
                }
            });
            return this;
        }

        /* JADX INFO: renamed from: lambda$setPositiveClickLitener$1$com-byd-vrsettings-dialog-WakeupWordsFreeDialog$Builder, reason: not valid java name */
        /* synthetic */ void m48x1c3531fe(View.OnClickListener onClickListener, View view) {
            ViewGroup viewGroup = (ViewGroup) this.contentView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.contentView);
            }
            this.dialog.dismiss();
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }

        public Builder setNegativeClickLitener(String str, final View.OnClickListener onClickListener) {
            this.binding.negative.setText(str);
            this.binding.negative.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.dialog.WakeupWordsFreeDialog$Builder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m46x512e303b(onClickListener, view);
                }
            });
            return this;
        }

        /* JADX INFO: renamed from: lambda$setNegativeClickLitener$2$com-byd-vrsettings-dialog-WakeupWordsFreeDialog$Builder, reason: not valid java name */
        /* synthetic */ void m46x512e303b(View.OnClickListener onClickListener, View view) {
            ViewGroup viewGroup = (ViewGroup) this.contentView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.contentView);
            }
            this.dialog.dismiss();
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }

        public Builder setNegativeClickLitener(final View.OnClickListener onClickListener) {
            this.binding.negative.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.dialog.WakeupWordsFreeDialog$Builder$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m47x76c2393c(onClickListener, view);
                }
            });
            return this;
        }

        /* JADX INFO: renamed from: lambda$setNegativeClickLitener$3$com-byd-vrsettings-dialog-WakeupWordsFreeDialog$Builder, reason: not valid java name */
        /* synthetic */ void m47x76c2393c(View.OnClickListener onClickListener, View view) {
            this.dialog.dismiss();
            onClickListener.onClick(view);
        }
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        OnBackPressInterface onBackPressInterface = this.onBackPressInterface;
        if (onBackPressInterface != null) {
            onBackPressInterface.onBackPress();
        } else {
            super.onBackPressed();
        }
    }
}
