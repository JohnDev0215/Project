package com.byd.vrsettings.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import com.byd.vrsettings.R;
import com.byd.vrsettings.databinding.DialogLoadingBinding;
import com.byd.vrsettings.interf.OnBackPressInterface;

/* JADX INFO: loaded from: classes.dex */
public class LoadingDialog extends Dialog {
    OnBackPressInterface onBackPressInterface;

    public LoadingDialog(Context context) {
        this(context, R.style.custom_dialog);
    }

    public LoadingDialog(Context context, int i) {
        super(context, i);
    }

    public LoadingDialog(Context context, OnBackPressInterface onBackPressInterface) {
        super(context, R.style.custom_dialog);
        this.onBackPressInterface = onBackPressInterface;
    }

    public static class Builder {
        DialogLoadingBinding binding;
        View contentView;
        Context context;
        LoadingDialog dialog;
        ConstraintSet set;

        public Builder(Context context) {
            this.context = context;
            DialogLoadingBinding dialogLoadingBindingInflate = DialogLoadingBinding.inflate((LayoutInflater) context.getSystemService("layout_inflater"));
            this.binding = dialogLoadingBindingInflate;
            this.contentView = dialogLoadingBindingInflate.getRoot();
        }

        public LoadingDialog create() {
            create(null);
            return this.dialog;
        }

        public LoadingDialog create(OnBackPressInterface onBackPressInterface) {
            ViewGroup viewGroup;
            View view = this.contentView;
            if (view != null && (viewGroup = (ViewGroup) view.getParent()) != null) {
                viewGroup.removeView(this.contentView);
            }
            if (this.dialog == null) {
                if (onBackPressInterface != null) {
                    this.dialog = new LoadingDialog(this.context, onBackPressInterface);
                } else {
                    this.dialog = new LoadingDialog(this.context);
                }
            }
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setContentView(this.contentView, new ViewGroup.LayoutParams(-2, -2));
            return this.dialog;
        }

        public Builder setLight(boolean z) {
            if (z) {
                this.binding.tvLoading.setTextColor(ContextCompat.getColor(this.context, R.color.voice_setting_text_selected));
                this.binding.loadingBg.setBackgroundResource(R.drawable.bg_setting_dialog);
            } else {
                this.binding.tvLoading.setTextColor(ContextCompat.getColor(this.context, R.color.voice_volume_text_night));
                this.binding.loadingBg.setBackgroundResource(R.drawable.bg_setting_dialog_night);
            }
            return this;
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
