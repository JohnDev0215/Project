package com.byd.auto_emanual.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.byd.auto_emanual.IStopOrGo;
import com.byd.auto_emanual.MainActivity;
import com.byd.auto_emanual.R;

/* JADX INFO: loaded from: classes.dex */
public class DialogDiffUtil {
    private static final String DISCLAIMERS = "agree_disclaimer";
    private static final String RADIO_SELECTED = "selected";
    private static final String RADIO_UNSELECTED = "unselected";

    public static void setRadioAndConfirm(final Dialog dialog, final Context context, boolean isFirst, final MainActivity activity) {
        Window window = dialog.getWindow();
        RelativeLayout relativeLayout = (RelativeLayout) window.findViewById(R.id.radio);
        final ImageView imageView = (ImageView) window.findViewById(R.id.radio_image);
        final TextView textView = (TextView) window.findViewById(R.id.confirm_only);
        final TextView textView2 = (TextView) window.findViewById(R.id.confirm);
        TextView textView3 = (TextView) window.findViewById(R.id.cancel);
        String strLoad = SharedPrefUtil.load(context, DISCLAIMERS);
        if (TextUtils.isEmpty(strLoad)) {
            strLoad = RADIO_UNSELECTED;
        }
        imageView.setTag(strLoad);
        if (!isFirst) {
            relativeLayout.setVisibility(8);
            textView.setVisibility(0);
            textView2.setVisibility(8);
            textView3.setVisibility(8);
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.util.-$$Lambda$DialogDiffUtil$XprgIXn8hcD2kq__rXug8sCz3FI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        if (!isFirst) {
            dialog.setCancelable(true);
            dialog.getWindow().getDecorView().setOnTouchListener(new View.OnTouchListener() { // from class: com.byd.auto_emanual.util.DialogDiffUtil.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View v, MotionEvent event) {
                    int[] iArr = new int[2];
                    v.getLocationOnScreen(iArr);
                    int rawX = (int) event.getRawX();
                    int rawY = (int) event.getRawY();
                    if (rawX < iArr[0] || rawX > iArr[0] + v.getWidth() || rawY < iArr[1] || rawY > iArr[1] + v.getHeight()) {
                        textView.performClick();
                    }
                    return true;
                }
            });
            updateConfirmAndCancelPosition(textView2, textView3);
        } else {
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.util.-$$Lambda$DialogDiffUtil$4vs9qae6mwUfXhxtqc4MfkWvfTM
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DialogDiffUtil.lambda$setRadioAndConfirm$1(imageView, context, dialog, activity, view);
                }
            });
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.util.-$$Lambda$DialogDiffUtil$NVtSchbQNRiFNKWPaGQD2xbyBLA
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DialogDiffUtil.lambda$setRadioAndConfirm$2(context, dialog, view);
                }
            });
            relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.util.-$$Lambda$DialogDiffUtil$2hh3tHEbvH8g5pT4jBcarAKfTl4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DialogDiffUtil.lambda$setRadioAndConfirm$3(imageView, context, textView2, view);
                }
            });
            updateConfirmAndCancelPosition(textView2, textView3);
        }
    }

    static /* synthetic */ void lambda$setRadioAndConfirm$1(ImageView imageView, Context context, Dialog dialog, MainActivity mainActivity, View view) {
        if (RADIO_SELECTED.equals(imageView.getTag())) {
            SharedPrefUtil.save(context, DISCLAIMERS, RADIO_SELECTED);
            dialog.dismiss();
            if (mainActivity != null) {
                mainActivity.setStopOrGoDialog(DialogUtil.showStopOrGo(mainActivity));
                return;
            }
            return;
        }
        ViewUtil.showToast(Toast.makeText(context, R.string.dialog_disclaimer_radio_toast_text, 0), 2500);
    }

    static /* synthetic */ void lambda$setRadioAndConfirm$2(Context context, Dialog dialog, View view) {
        SharedPrefUtil.remove(context, DISCLAIMERS);
        dialog.dismiss();
        ((Activity) context).finish();
    }

    static /* synthetic */ void lambda$setRadioAndConfirm$3(ImageView imageView, Context context, TextView textView, View view) {
        MLog.v("DialogDiffUtil", "tag " + imageView.getTag());
        if (RADIO_SELECTED.equals(imageView.getTag())) {
            imageView.setTag(RADIO_UNSELECTED);
            updateRadioDrawable(imageView, context, R.drawable.icon_dialog_unselected_dynasty);
            updateConfirmTextColor(textView, false);
            updateConfirmBackground(textView, false);
            return;
        }
        imageView.setTag(RADIO_SELECTED);
        updateRadioDrawable(imageView, context, R.drawable.icon_dialog_selected_dynasty);
        updateConfirmTextColor(textView, true);
        updateConfirmBackground(textView, true);
    }

    public static void updateConfirmAndCancelPosition(TextView confirm, TextView cancel) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) confirm.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) cancel.getLayoutParams();
        if (DeviceUtil.isRightDriver()) {
            layoutParams.removeRule(9);
            layoutParams.addRule(11);
            layoutParams.rightMargin = confirm.getContext().getResources().getDimensionPixelOffset(R.dimen.button_confirm_start);
            layoutParams2.removeRule(11);
            layoutParams2.leftMargin = cancel.getContext().getResources().getDimensionPixelOffset(R.dimen.button_confirm_start);
        } else {
            layoutParams.removeRule(11);
            layoutParams.addRule(9);
            layoutParams.leftMargin = confirm.getContext().getResources().getDimensionPixelOffset(R.dimen.button_confirm_start);
            layoutParams2.addRule(11);
            layoutParams2.rightMargin = cancel.getContext().getResources().getDimensionPixelOffset(R.dimen.button_confirm_start);
        }
        confirm.setLayoutParams(layoutParams);
        cancel.setLayoutParams(layoutParams2);
    }

    private static void updateConfirmTextColor(TextView confirm, boolean canPressed) {
        if (canPressed) {
            confirm.setTextColor(ContextCompat.getColorStateList(confirm.getContext(), R.color.dialog_confirm_text_color_selector));
        } else {
            confirm.setTextColor(ContextCompat.getColor(confirm.getContext(), R.color.button_confirm_text_color_disabled));
        }
    }

    private static void updateConfirmBackground(TextView confirm, boolean isSelected) {
        if (isSelected) {
            confirm.setBackground(ContextCompat.getDrawable(confirm.getContext(), R.drawable.button_confirm_background_shape_selector));
        } else {
            confirm.setBackground(ContextCompat.getDrawable(confirm.getContext(), R.drawable.button_confirm_background_shape_unselected));
        }
    }

    private static void updateRadioDrawable(ImageView radioImage, Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (drawable == null) {
            return;
        }
        radioImage.setImageDrawable(drawable);
    }

    public static void setStopOrGoWindow(Dialog dialog, IStopOrGo activity) {
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = activity.getSelf().getResources().getDimensionPixelOffset(R.dimen.dialog_width);
        attributes.height = activity.getSelf().getResources().getDimensionPixelOffset(R.dimen.dp_wrap_content);
        dialog.getWindow().setAttributes(attributes);
        window.findViewById(R.id.line_horizontal).setVisibility(4);
        ((TextView) window.findViewById(R.id.confirm)).setVisibility(4);
    }

    public static void setHistoryRemovingWaring(Dialog dialog, Context context) {
        Window window = dialog.getWindow();
        TextView textView = (TextView) window.findViewById(R.id.detail);
        textView.setGravity(17);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.topMargin = context.getResources().getDimensionPixelOffset(R.dimen.dp_40);
        layoutParams.bottomMargin = context.getResources().getDimensionPixelOffset(R.dimen.dp_40);
        layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);
        textView.setLayoutParams(layoutParams);
        TextView textView2 = (TextView) window.findViewById(R.id.confirm);
        TextView textView3 = (TextView) window.findViewById(R.id.cancel);
        textView2.setText(context.getString(R.string.dialog_button_confirm));
        updateConfirmAndCancelPosition(textView2, textView3);
    }

    public static void setMobileDataPrompt(Dialog dialog, Context context) {
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = context.getResources().getDimensionPixelOffset(R.dimen.dialog_width);
        attributes.height = context.getResources().getDimensionPixelOffset(R.dimen.dp_wrap_content);
        dialog.getWindow().setAttributes(attributes);
        TextView textView = (TextView) window.findViewById(R.id.detail);
        textView.setGravity(17);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.bottomMargin = context.getResources().getDimensionPixelOffset(R.dimen.dp_40);
        layoutParams.setMargins(layoutParams.leftMargin, 0, layoutParams.rightMargin, layoutParams.bottomMargin);
        textView.setLayoutParams(layoutParams);
        window.findViewById(R.id.line_horizontal).setVisibility(4);
        updateConfirmAndCancelPosition((TextView) window.findViewById(R.id.confirm), (TextView) window.findViewById(R.id.cancel));
    }

    public static void setDownloadPrompt(Dialog dialog, Context context) {
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = context.getResources().getDimensionPixelOffset(R.dimen.dialog_width);
        attributes.height = context.getResources().getDimensionPixelOffset(R.dimen.dp_wrap_content);
        dialog.getWindow().setAttributes(attributes);
        TextView textView = (TextView) window.findViewById(R.id.detail);
        TextView textView2 = (TextView) window.findViewById(R.id.confirm);
        TextView textView3 = (TextView) window.findViewById(R.id.cancel);
        textView.setGravity(17);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.topMargin = context.getResources().getDimensionPixelOffset(R.dimen.dp_20);
        layoutParams.bottomMargin = context.getResources().getDimensionPixelOffset(R.dimen.dp_40);
        layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);
        textView.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) textView2.getLayoutParams();
        layoutParams2.bottomMargin = context.getResources().getDimensionPixelOffset(R.dimen.dp_21);
        layoutParams2.setMargins(layoutParams2.leftMargin, layoutParams2.topMargin, layoutParams2.rightMargin, layoutParams2.bottomMargin);
        textView2.setLayoutParams(layoutParams2);
        updateConfirmAndCancelPosition(textView2, textView3);
    }

    public static void updateDialogColorByConfig(Dialog dialog) {
        Window window = dialog.getWindow();
        TextView textView = (TextView) window.findViewById(R.id.confirm_only);
        TextView textView2 = (TextView) window.findViewById(R.id.confirm);
        TextView textView3 = (TextView) window.findViewById(R.id.cancel);
        RelativeLayout relativeLayout = (RelativeLayout) window.findViewById(R.id.radio);
        ImageView imageView = (ImageView) window.findViewById(R.id.radio_image);
        if (relativeLayout.getVisibility() != 0) {
            updateConfirmTextColor(textView2, true);
            textView2.setBackground(ContextCompat.getDrawable(dialog.getContext(), R.drawable.button_confirm_background_shape_selector));
        } else if (RADIO_UNSELECTED.equals(imageView.getTag())) {
            updateConfirmTextColor(textView2, false);
            textView2.setBackground(ContextCompat.getDrawable(dialog.getContext(), R.drawable.button_confirm_background_shape_unselected));
            updateRadioDrawable(imageView, imageView.getContext(), R.drawable.icon_dialog_unselected_dynasty);
        } else {
            updateConfirmTextColor(textView2, true);
            textView2.setBackground(ContextCompat.getDrawable(dialog.getContext(), R.drawable.button_confirm_background_shape_selector));
            updateRadioDrawable(imageView, imageView.getContext(), R.drawable.icon_dialog_selected_dynasty);
        }
        textView.setBackground(ContextCompat.getDrawable(dialog.getContext(), R.drawable.button_confirm_background_shape_selector));
        textView3.setBackground(ContextCompat.getDrawable(dialog.getContext(), R.drawable.button_cancel_background_shape_selector));
        textView3.setTextColor(ContextCompat.getColorStateList(dialog.getContext(), R.color.dialog_cancel_text_color_selector));
        updateLayoutDirection(dialog);
    }

    public static void updateLayoutDirection(Dialog dialog) {
        Window window = dialog.getWindow();
        TextView textView = (TextView) window.findViewById(R.id.detail);
        RelativeLayout relativeLayout = (RelativeLayout) window.findViewById(R.id.radio);
        if (ViewUtil.isRtl(textView.getContext().getResources().getConfiguration().getLayoutDirection())) {
            textView.setLayoutDirection(1);
            relativeLayout.setLayoutDirection(1);
        } else {
            textView.setLayoutDirection(0);
            relativeLayout.setLayoutDirection(0);
        }
        textView.requestLayout();
        relativeLayout.requestLayout();
    }
}
