package com.byd.auto_emanual.util;

import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import com.byd.auto_emanual.ActivityManager;
import com.byd.auto_emanual.GearboxListener;
import com.byd.auto_emanual.IStopOrGo;
import com.byd.auto_emanual.MainActivity;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.fragment.OtherStateDialog;
import com.byd.auto_emanual.fragment.SearchHistoryAdapter;
import com.byd.auto_emanual.update.NewVersionInfo;
import com.byd.auto_emanual.update.UpdateCheckUtil;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class DialogUtil {
    private static final String DISCLAIMERS = "agree_disclaimer";
    private static final String RADIO_SELECTED = "selected";
    private static final String RADIO_UNSELECTED = "unselected";

    public static boolean isShowDisclaimer(Context context) {
        return !RADIO_SELECTED.equals(SharedPrefUtil.load(context, DISCLAIMERS));
    }

    public static AlertDialog showDisclaimer(Context context, boolean isFirst, MainActivity activity) {
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context).setView(R.layout.dialog).create();
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.setCancelable(false);
        alertDialogCreate.show();
        Window window = alertDialogCreate.getWindow();
        window.setBackgroundDrawableResource(R.drawable.dialog_background_color);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = context.getResources().getDimensionPixelOffset(R.dimen.dialog_width);
        alertDialogCreate.getWindow().setAttributes(attributes);
        ((TextView) window.findViewById(R.id.detail)).setMovementMethod(ScrollingMovementMethod.getInstance());
        DialogDiffUtil.setRadioAndConfirm(alertDialogCreate, context, isFirst, activity);
        updateDialogColorByConfig(alertDialogCreate);
        return alertDialogCreate;
    }

    public static AlertDialog showNewVersion(Context context, final NewVersionInfo newVersionInfo, OtherStateDialog otherStateDialog, FragmentManager fragmentManager, String tag) {
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(context).setView(R.layout.dialog).create();
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.show();
        Window window = alertDialogCreate.getWindow();
        window.setBackgroundDrawableResource(R.drawable.dialog_background_color);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = context.getResources().getDimensionPixelOffset(R.dimen.dialog_width);
        alertDialogCreate.getWindow().setAttributes(attributes);
        ((TextView) window.findViewById(R.id.title)).setText(R.string.settings_version_update);
        TextView textView = (TextView) window.findViewById(R.id.detail);
        textView.setText(getNewVersionInfo(context, newVersionInfo));
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        TextView textView2 = (TextView) window.findViewById(R.id.confirm);
        textView2.setText(context.getString(R.string.settings_update_now));
        DialogDiffUtil.updateDialogColorByConfig(alertDialogCreate);
        TextView textView3 = (TextView) window.findViewById(R.id.cancel);
        textView3.setText(context.getString(R.string.settings_update_later));
        ((RelativeLayout) window.findViewById(R.id.radio)).setVisibility(4);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.util.-$$Lambda$DialogUtil$9N1Msl3Qir6sOP1d4rxHFgD7mVE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DialogUtil.lambda$showNewVersion$0(alertDialogCreate, newVersionInfo, view);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.util.-$$Lambda$DialogUtil$zpB2aU81OtfibUkPsWkNNdTr4qM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                alertDialogCreate.dismiss();
            }
        });
        return alertDialogCreate;
    }

    static /* synthetic */ void lambda$showNewVersion$0(AlertDialog alertDialog, NewVersionInfo newVersionInfo, View view) {
        alertDialog.dismiss();
        UpdateCheckUtil.getInstance().startDownload(newVersionInfo);
    }

    private static String getNewVersionInfo(Context context, NewVersionInfo info) {
        String str = String.format(Locale.ENGLISH, "%.2f", Float.valueOf(info.getPackageSize() / 1024.0f));
        StringBuilder sb = new StringBuilder();
        sb.append(context.getString(R.string.settings_version)).append(" ").append(info.getVersionName()).append("\n");
        sb.append(context.getString(R.string.settings_version_size)).append(" ").append(str).append("MB").append("\n");
        if (info.getUpgradeContent() != null) {
            sb.append(context.getString(R.string.settings_version_update_instructions)).append("\n").append(info.getUpgradeContent());
        }
        return sb.toString();
    }

    public static AlertDialog showHistoryRemovingWaring(final Context context, final SearchHistoryAdapter searchHistoryAdapter) {
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(context).setView(R.layout.dialog).create();
        alertDialogCreate.setCanceledOnTouchOutside(true);
        alertDialogCreate.setCancelable(true);
        alertDialogCreate.show();
        Window window = alertDialogCreate.getWindow();
        window.setBackgroundDrawableResource(R.drawable.dialog_background_color);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = context.getResources().getDimensionPixelOffset(R.dimen.dialog_width);
        alertDialogCreate.getWindow().setAttributes(attributes);
        ((TextView) window.findViewById(R.id.title)).setText(R.string.dialog_history_removing_title);
        TextView textView = (TextView) window.findViewById(R.id.detail);
        textView.setText(R.string.dialog_history_removing_message);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin * 3, layoutParams.rightMargin, 0);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(1);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        DialogDiffUtil.setHistoryRemovingWaring(alertDialogCreate, context);
        TextView textView2 = (TextView) window.findViewById(R.id.confirm);
        TextView textView3 = (TextView) window.findViewById(R.id.cancel);
        textView3.setText(context.getString(R.string.dialog_button_cancel));
        ((RelativeLayout) window.findViewById(R.id.radio)).setVisibility(8);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.util.-$$Lambda$DialogUtil$u5rUtS9IUj7QifEH6V-eeNBbxM4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DialogUtil.lambda$showHistoryRemovingWaring$2(alertDialogCreate, context, searchHistoryAdapter, view);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.util.-$$Lambda$DialogUtil$4u8ply_HsxwSZci-UjUd-BrjQgg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                alertDialogCreate.dismiss();
            }
        });
        return alertDialogCreate;
    }

    static /* synthetic */ void lambda$showHistoryRemovingWaring$2(AlertDialog alertDialog, Context context, SearchHistoryAdapter searchHistoryAdapter, View view) {
        alertDialog.dismiss();
        SearchHistoryUtil.updateHistory(context, null);
        searchHistoryAdapter.setData(SearchHistoryUtil.getHistory());
    }

    public static void updateDialogTextByConfig(AlertDialog dialog, int titleResId, int detailResID, int radioResId, int confirmResId, int cancelResId) {
        Window window = dialog.getWindow();
        TextView textView = (TextView) window.findViewById(R.id.title);
        TextView textView2 = (TextView) window.findViewById(R.id.detail);
        TextView textView3 = (TextView) window.findViewById(R.id.radio_text);
        TextView textView4 = (TextView) window.findViewById(R.id.confirm);
        TextView textView5 = (TextView) window.findViewById(R.id.confirm_only);
        TextView textView6 = (TextView) window.findViewById(R.id.cancel);
        if (titleResId > 0) {
            textView.setText(titleResId);
        }
        if (detailResID > 0) {
            if (textView2.getTag() != null) {
                try {
                    textView2.setText(textView2.getResources().getString(detailResID, Integer.valueOf(((Integer) textView2.getTag()).intValue())));
                } catch (ClassCastException unused) {
                    textView2.setText(detailResID);
                }
            } else {
                textView2.setText(detailResID);
            }
        }
        if (radioResId > 0) {
            textView3.setText(radioResId);
        }
        if (confirmResId > 0) {
            textView4.setText(confirmResId);
            textView5.setText(confirmResId);
        }
        if (cancelResId > 0) {
            textView6.setText(cancelResId);
        }
    }

    public static void updateDialogColorByConfig(AlertDialog dialog) {
        Window window = dialog.getWindow();
        TextView textView = (TextView) window.findViewById(R.id.title);
        TextView textView2 = (TextView) window.findViewById(R.id.detail);
        RelativeLayout relativeLayout = (RelativeLayout) window.findViewById(R.id.radio);
        TextView textView3 = (TextView) window.findViewById(R.id.radio_text);
        View viewFindViewById = window.findViewById(R.id.line_horizontal);
        View viewFindViewById2 = window.findViewById(R.id.line_vertical);
        window.setBackgroundDrawableResource(R.drawable.dialog_background_color);
        textView.setTextColor(ContextCompat.getColor(dialog.getContext(), R.color.dialog_title_text_color));
        textView2.setTextColor(ContextCompat.getColor(dialog.getContext(), R.color.dialog_detail_text_color));
        if (Build.VERSION.SDK_INT >= 29) {
            textView2.setVerticalScrollbarThumbDrawable(ContextCompat.getDrawable(textView2.getContext(), R.drawable.classify_scrollbar_color));
        }
        if (Utils.is6125F()) {
            textView3.setTextColor(ContextCompat.getColor(dialog.getContext(), R.color.dialog_radio_text_color));
            relativeLayout.setBackgroundColor(ContextCompat.getColor(dialog.getContext(), R.color.dialog_radio_background_color));
        } else {
            textView3.setTextColor(ResourceUtil.getColor(dialog.getContext(), R.attr.dialog_radio_text_color));
        }
        viewFindViewById.setBackgroundColor(ContextCompat.getColor(dialog.getContext(), R.color.dialog_line_color));
        viewFindViewById2.setBackgroundColor(ContextCompat.getColor(dialog.getContext(), R.color.dialog_line_color));
        DialogDiffUtil.updateDialogColorByConfig(dialog);
    }

    private static boolean isShowStopOrGo(IStopOrGo context) {
        return (isShowDisclaimer(context.getSelf()) || GearboxListener.isModeTypeP()) ? false : true;
    }

    public static AlertDialog showStopOrGo(final IStopOrGo activity) {
        if (!isShowStopOrGo(activity) || !activity.isForeground()) {
            return null;
        }
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(activity.getSelf()).setView(R.layout.dialog).create();
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.setCancelable(false);
        alertDialogCreate.show();
        Window window = alertDialogCreate.getWindow();
        window.setBackgroundDrawableResource(R.drawable.dialog_background_color);
        DialogDiffUtil.setStopOrGoWindow(alertDialogCreate, activity);
        ((TextView) window.findViewById(R.id.title)).setVisibility(4);
        TextView textView = (TextView) window.findViewById(R.id.detail);
        textView.setGravity(17);
        textView.setText(R.string.stop_or_go_detail);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        TextView textView2 = (TextView) window.findViewById(R.id.confirm_only);
        textView2.setVisibility(0);
        textView2.setText(activity.getSelf().getString(R.string.stop_or_go_confirm, new Object[]{10}));
        ((TextView) window.findViewById(R.id.cancel)).setVisibility(4);
        ((RelativeLayout) window.findViewById(R.id.radio)).setVisibility(8);
        final CountDownTimer countDownTimer = new CountDownTimer(10000L, 1000L, 10, textView2, activity, alertDialogCreate) { // from class: com.byd.auto_emanual.util.DialogUtil.1
            int countDownTimeNow;
            final /* synthetic */ IStopOrGo val$activity;
            final /* synthetic */ TextView val$confirmOnly;
            final /* synthetic */ int val$countDownTime;
            final /* synthetic */ AlertDialog val$dialog;

            {
                this.val$countDownTime = val$countDownTime;
                this.val$confirmOnly = textView2;
                this.val$activity = activity;
                this.val$dialog = alertDialogCreate;
                this.countDownTimeNow = val$countDownTime;
            }

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                this.val$confirmOnly.setText(this.val$activity.getSelf().getString(R.string.stop_or_go_confirm, new Object[]{Integer.valueOf(this.countDownTimeNow)}));
                this.countDownTimeNow--;
                if (GearboxListener.isModeTypeP()) {
                    this.val$dialog.dismiss();
                    if (Utils.is8155F()) {
                        this.val$activity.showStopOrGoDialog(false);
                    } else {
                        this.val$activity.setStopOrGoDialog(null);
                    }
                    cancel();
                }
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                this.val$dialog.dismiss();
                if (Utils.is8155F()) {
                    this.val$activity.showStopOrGoDialog(false);
                } else {
                    this.val$activity.setStopOrGoDialog(null);
                }
                if (GearboxListener.isModeTypeP()) {
                    return;
                }
                ActivityManager.getInstance().exit();
            }
        };
        countDownTimer.start();
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.util.-$$Lambda$DialogUtil$OAggA367aNGvZ24KdhObG1A28Fw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DialogUtil.lambda$showStopOrGo$4(countDownTimer, alertDialogCreate, activity, view);
            }
        });
        return alertDialogCreate;
    }

    static /* synthetic */ void lambda$showStopOrGo$4(CountDownTimer countDownTimer, AlertDialog alertDialog, IStopOrGo iStopOrGo, View view) {
        countDownTimer.cancel();
        alertDialog.dismiss();
        if (Utils.is8155F()) {
            iStopOrGo.showStopOrGoDialog(false);
        } else {
            iStopOrGo.setStopOrGoDialog(null);
        }
        if (GearboxListener.isModeTypeP()) {
            return;
        }
        ActivityManager.getInstance().exit();
    }

    public static AlertDialog downloadPromptDialog(Context context, final View.OnClickListener listener) {
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(context).setView(R.layout.dialog).create();
        alertDialogCreate.setCanceledOnTouchOutside(true);
        alertDialogCreate.setCancelable(true);
        alertDialogCreate.show();
        Window window = alertDialogCreate.getWindow();
        window.setBackgroundDrawableResource(R.drawable.dialog_background_color);
        DialogDiffUtil.setDownloadPrompt(alertDialogCreate, context);
        ((TextView) window.findViewById(R.id.title)).setText(R.string.download_prompt_title);
        ((TextView) window.findViewById(R.id.detail)).setText(R.string.download_prompt_detail);
        TextView textView = (TextView) window.findViewById(R.id.confirm);
        textView.setText(R.string.dialog_button_confirm);
        TextView textView2 = (TextView) window.findViewById(R.id.cancel);
        ((TextView) window.findViewById(R.id.confirm_only)).setVisibility(8);
        ((RelativeLayout) window.findViewById(R.id.radio)).setVisibility(8);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.util.-$$Lambda$DialogUtil$rEeUdI4XrVHpz0_n2JPlqsmqQA0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DialogUtil.lambda$downloadPromptDialog$5(listener, alertDialogCreate, view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.util.-$$Lambda$DialogUtil$V__qtk0FVOLcg3WeGKBie8YnWNk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                alertDialogCreate.dismiss();
            }
        });
        return alertDialogCreate;
    }

    static /* synthetic */ void lambda$downloadPromptDialog$5(View.OnClickListener onClickListener, AlertDialog alertDialog, View view) {
        onClickListener.onClick(view);
        alertDialog.dismiss();
    }

    public static AlertDialog mobileDataPromptDialog(Context context, int threshHold, final View.OnClickListener listener) {
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(context).setView(R.layout.dialog).create();
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.setCancelable(false);
        alertDialogCreate.show();
        Window window = alertDialogCreate.getWindow();
        window.setBackgroundDrawableResource(R.drawable.dialog_background_color);
        ((TextView) window.findViewById(R.id.title)).setVisibility(4);
        TextView textView = (TextView) window.findViewById(R.id.detail);
        textView.setText(context.getString(R.string.mobile_data_prompt_detail, Integer.valueOf(threshHold)));
        textView.setTag(Integer.valueOf(threshHold));
        TextView textView2 = (TextView) window.findViewById(R.id.confirm);
        textView2.setText(context.getString(R.string.mobile_data_prompt_confirm, 10));
        TextView textView3 = (TextView) window.findViewById(R.id.cancel);
        textView3.setText(R.string.mobile_data_prompt_cancel);
        DialogDiffUtil.setMobileDataPrompt(alertDialogCreate, context);
        ((TextView) window.findViewById(R.id.confirm_only)).setVisibility(8);
        ((RelativeLayout) window.findViewById(R.id.radio)).setVisibility(8);
        final CountDownTimer countDownTimer = new CountDownTimer(10000L, 1000L, 10, textView2, context, alertDialogCreate, listener) { // from class: com.byd.auto_emanual.util.DialogUtil.2
            int countDownTimeNow;
            final /* synthetic */ TextView val$confirm;
            final /* synthetic */ Context val$context;
            final /* synthetic */ int val$countDownTime;
            final /* synthetic */ AlertDialog val$dialog;
            final /* synthetic */ View.OnClickListener val$listener;

            {
                this.val$countDownTime = val$countDownTime;
                this.val$confirm = textView2;
                this.val$context = context;
                this.val$dialog = alertDialogCreate;
                this.val$listener = listener;
                this.countDownTimeNow = val$countDownTime;
            }

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                this.val$confirm.setText(this.val$context.getString(R.string.mobile_data_prompt_confirm, Integer.valueOf(this.countDownTimeNow)));
                this.countDownTimeNow--;
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                this.val$dialog.dismiss();
                this.val$listener.onClick(this.val$confirm);
            }
        };
        countDownTimer.start();
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.util.-$$Lambda$DialogUtil$X5zwUwCVdKZhINhVzdeNmZjIEi8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DialogUtil.lambda$mobileDataPromptDialog$7(countDownTimer, alertDialogCreate, listener, view);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.util.-$$Lambda$DialogUtil$QLy4rAnI5MtyrCa9dpV-ZEOkHIE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DialogUtil.lambda$mobileDataPromptDialog$8(countDownTimer, alertDialogCreate, view);
            }
        });
        return alertDialogCreate;
    }

    static /* synthetic */ void lambda$mobileDataPromptDialog$7(CountDownTimer countDownTimer, AlertDialog alertDialog, View.OnClickListener onClickListener, View view) {
        countDownTimer.cancel();
        alertDialog.dismiss();
        onClickListener.onClick(view);
    }

    static /* synthetic */ void lambda$mobileDataPromptDialog$8(CountDownTimer countDownTimer, AlertDialog alertDialog, View view) {
        countDownTimer.cancel();
        alertDialog.dismiss();
    }
}
