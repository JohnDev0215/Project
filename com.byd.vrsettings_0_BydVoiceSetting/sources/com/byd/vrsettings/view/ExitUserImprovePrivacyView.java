package com.byd.vrsettings.view;

import android.content.Context;
import android.content.DialogInterface;
import android.provider.Settings;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.ViewModelProvider;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.byd.vrsettings.R;
import com.byd.vrsettings.dialog.DetachableDialogDismissListener;
import com.byd.vrsettings.utils.Common;
import com.byd.vrsettings.utils.Constant;
import com.byd.vrsettings.utils.ModeUtils;
import com.byd.vrsettings.utils.MyViewModel;
import com.byd.vrsettings.utils.RLog;

/* JADX INFO: loaded from: classes.dex */
public class ExitUserImprovePrivacyView {
    DetachableDialogDismissListener detachableDialogDismissListener;
    private clickCancel mClickCancel;
    private clickConfirm mClickConfirm;
    private Context mContext;
    private CustomDialog mCustomDialog;
    private View privacy;
    private View privacy_line;
    private ConstraintLayout privacy_policy_layout;
    private TextView tv_btn_cancel_privacy;
    private TextView tv_btn_confirm_privacy;
    private TextView tv_privacy_policy;
    private TextView tv_title_privacy;
    private View v_privacyBackground;
    private View viewPrivacyPolicy;
    private final String TAG = "NEU_ExitUserImprovePrivacyView";
    private ConstraintSet set = new ConstraintSet();
    private MyViewModel mViewModel = (MyViewModel) new ViewModelProvider(BydVoiceSettingApplication.getContext(), ViewModelProvider.AndroidViewModelFactory.getInstance(BydVoiceSettingApplication.getContext())).get(MyViewModel.class);

    public interface clickCancel {
        void clickCancel();
    }

    public interface clickConfirm {
        void clickConfirm();
    }

    public void setLandScape() {
    }

    public void setPortrait() {
    }

    public ExitUserImprovePrivacyView(Context context, clickConfirm clickconfirm, clickCancel clickcancel) {
        this.mContext = context;
        this.mClickConfirm = clickconfirm;
        this.mClickCancel = clickcancel;
        initView();
    }

    private void initView() {
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.layout_join_user_policy, (ViewGroup) null);
        this.viewPrivacyPolicy = viewInflate;
        this.privacy_policy_layout = (ConstraintLayout) viewInflate.findViewById(R.id.privacy_policy_layout);
        this.mCustomDialog = new CustomDialog(this.mContext, this.viewPrivacyPolicy);
        View viewFindViewById = this.viewPrivacyPolicy.findViewById(R.id.v_privacyBackground);
        this.v_privacyBackground = viewFindViewById;
        setClickPlaySound(viewFindViewById, this.mCustomDialog);
        this.privacy = this.viewPrivacyPolicy.findViewById(R.id.privacy);
        this.tv_title_privacy = (TextView) this.viewPrivacyPolicy.findViewById(R.id.tv_title_privacy);
        this.tv_privacy_policy = (TextView) this.viewPrivacyPolicy.findViewById(R.id.tv_privacy_policy);
        this.privacy_line = this.viewPrivacyPolicy.findViewById(R.id.privacy_line);
        this.tv_btn_confirm_privacy = (TextView) this.viewPrivacyPolicy.findViewById(R.id.tv_btn_confirm_privacy);
        this.tv_btn_cancel_privacy = (TextView) this.viewPrivacyPolicy.findViewById(R.id.tv_btn_cancel_privacy);
        this.tv_title_privacy.setText(R.string.text_exit_improve_privacy_policy);
        this.tv_privacy_policy.setText(R.string.text_exit_improve_privacy_policy_context);
        this.tv_privacy_policy.setMovementMethod(LinkMovementMethod.getInstance());
        this.tv_btn_confirm_privacy.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.view.ExitUserImprovePrivacyView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Settings.System.putString(ExitUserImprovePrivacyView.this.mContext.getContentResolver(), Constant.KEY_USER_IMPROVEMENT, "false");
                Settings.System.putString(ExitUserImprovePrivacyView.this.mContext.getContentResolver(), Constant.KEY_USER_IMPROVEMENT_EXIT, "true");
                RLog.i("NEU_ExitUserImprovePrivacyView", "tv_btn_confirm_privacy onclick  set KEY_USER_IMPROVEMENT is true");
                ExitUserImprovePrivacyView.this.mViewModel.showUserImproveDialogValue = 0;
                RLog.d("NEU_ExitUserImprovePrivacyView", "36 mViewModel.showUserImproveDialogValue==" + ExitUserImprovePrivacyView.this.mViewModel.showUserImproveDialogValue);
                ExitUserImprovePrivacyView.this.mClickConfirm.clickConfirm();
                ExitUserImprovePrivacyView.this.mCustomDialog.dismiss();
            }
        });
        this.tv_btn_cancel_privacy.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.view.ExitUserImprovePrivacyView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RLog.i("NEU_ExitUserImprovePrivacyView", "tv_btn_cancel_privacy onclick ");
                ExitUserImprovePrivacyView.this.mViewModel.showUserImproveDialogValue = 0;
                RLog.d("NEU_ExitUserImprovePrivacyView", "37 mViewModel.showUserImproveDialogValue==" + ExitUserImprovePrivacyView.this.mViewModel.showUserImproveDialogValue);
                ExitUserImprovePrivacyView.this.mClickCancel.clickCancel();
                ExitUserImprovePrivacyView.this.mCustomDialog.dismiss();
            }
        });
        initViewPrivacyPolicy();
        this.mCustomDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.byd.vrsettings.view.ExitUserImprovePrivacyView.3
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() != 4) {
                    return false;
                }
                ExitUserImprovePrivacyView.this.mViewModel.showUserImproveDialogValue = 0;
                RLog.d("NEU_ExitUserImprovePrivacyView", "38 mViewModel.showUserImproveDialogValue==" + ExitUserImprovePrivacyView.this.mViewModel.showUserImproveDialogValue);
                RLog.i("NEU_ExitUserImprovePrivacyView", "mCustomDialog KEYCODE_BACK");
                ExitUserImprovePrivacyView.this.mCustomDialog.dismiss();
                return true;
            }
        });
    }

    private void initViewPrivacyPolicy() {
        RLog.d("NEU_ExitUserImprovePrivacyView", "initViewPrivacyPolicy ");
        if (ModeUtils.isNightMode(this.mContext)) {
            this.privacy_policy_layout.setBackgroundColor(this.mContext.getResources().getColor(R.color.dialog_backgroud_color_night, null));
            this.tv_btn_cancel_privacy.setBackground(this.mContext.getResources().getDrawable(R.drawable.bg_setting_btn_no_night, null));
            this.tv_btn_confirm_privacy.setBackground(this.mContext.getResources().getDrawable(R.drawable.bg_setting_btn_yes_night, null));
            this.tv_btn_cancel_privacy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_button_cancel_textColor_night, null));
            this.privacy.setBackground(this.mContext.getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_title_privacy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.tv_privacy_policy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_contentTextColor_night, null));
            this.privacy_line.setBackgroundColor(this.mContext.getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
        } else {
            this.privacy_policy_layout.setBackgroundColor(this.mContext.getResources().getColor(R.color.dialog_backgroud_color_day, null));
            this.tv_btn_cancel_privacy.setBackground(this.mContext.getResources().getDrawable(R.drawable.bg_setting_btn_no, null));
            this.tv_btn_confirm_privacy.setBackground(this.mContext.getResources().getDrawable(R.drawable.bg_setting_btn_yes, null));
            this.tv_btn_cancel_privacy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_button_cancel_textColor, null));
            this.privacy.setBackground(this.mContext.getResources().getDrawable(R.mipmap.view_dialog_background, null));
            this.tv_title_privacy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_titleTextColor, null));
            this.tv_privacy_policy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_contentTextColor, null));
            this.privacy_line.setBackgroundColor(this.mContext.getResources().getColor(R.color.diglog_line_backColor_8155, null));
        }
        this.set.clone(this.privacy_policy_layout);
        if (Common.isRightDriver()) {
            this.set.clear(R.id.tv_btn_cancel_privacy, 6);
            this.set.clear(R.id.tv_btn_cancel_privacy, 7);
            this.set.connect(R.id.tv_btn_cancel_privacy, 7, R.id.tv_btn_confirm_privacy, 6, (int) TypedValue.applyDimension(1, 48.0f, this.mContext.getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_cancel_privacy, 6, R.id.privacy, 6, (int) TypedValue.applyDimension(1, 104.0f, this.mContext.getResources().getDisplayMetrics()));
            this.set.clear(R.id.tv_btn_confirm_privacy, 6);
            this.set.clear(R.id.tv_btn_confirm_privacy, 7);
            this.set.connect(R.id.tv_btn_confirm_privacy, 7, R.id.privacy, 7, (int) TypedValue.applyDimension(1, 104.0f, this.mContext.getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_confirm_privacy, 6, R.id.tv_btn_cancel_privacy, 7);
        } else {
            this.set.clear(R.id.tv_btn_confirm_privacy, 6);
            this.set.clear(R.id.tv_btn_confirm_privacy, 7);
            this.set.connect(R.id.tv_btn_confirm_privacy, 6, R.id.privacy, 6, (int) TypedValue.applyDimension(1, 104.0f, this.mContext.getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_confirm_privacy, 7, R.id.tv_btn_cancel_privacy, 6, (int) TypedValue.applyDimension(1, 48.0f, this.mContext.getResources().getDisplayMetrics()));
            this.set.clear(R.id.tv_btn_cancel_privacy, 6);
            this.set.clear(R.id.tv_btn_cancel_privacy, 7);
            this.set.connect(R.id.tv_btn_cancel_privacy, 7, R.id.privacy, 7, (int) TypedValue.applyDimension(1, 104.0f, this.mContext.getResources().getDisplayMetrics()));
            this.set.connect(R.id.tv_btn_cancel_privacy, 6, R.id.tv_btn_confirm_privacy, 7);
        }
        this.set.applyTo(this.privacy_policy_layout);
        DetachableDialogDismissListener detachableDialogDismissListenerWrap = DetachableDialogDismissListener.wrap(new DialogInterface.OnDismissListener() { // from class: com.byd.vrsettings.view.ExitUserImprovePrivacyView.4
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                ExitUserImprovePrivacyView.this.mViewModel.showUserImproveDialogValue = 0;
                RLog.d("NEU_ExitUserImprovePrivacyView", "40 mViewModel.showUserImproveDialogValue==" + ExitUserImprovePrivacyView.this.mViewModel.showUserImproveDialogValue);
                ExitUserImprovePrivacyView.this.detachableDialogDismissListener.clearOnDetach(ExitUserImprovePrivacyView.this.mCustomDialog);
            }
        });
        this.detachableDialogDismissListener = detachableDialogDismissListenerWrap;
        this.mCustomDialog.setOnDismissListener(detachableDialogDismissListenerWrap);
    }

    public void AddExitdialogListen() {
        RLog.d("NEU_ExitUserImprovePrivacyView", "AddExitdialogListen  mCustomDialog");
        CustomDialog customDialog = this.mCustomDialog;
        if (customDialog != null) {
            customDialog.setOnDismissListener(this.detachableDialogDismissListener);
        }
    }

    public void clearOnDetachExitView() {
        if (this.detachableDialogDismissListener == null || this.mCustomDialog == null) {
            return;
        }
        RLog.d("NEU_ExitUserImprovePrivacyView", "ExitViewclearOnDetach detachableDialogDismissListener");
        this.detachableDialogDismissListener.clearOnDetach(this.mCustomDialog);
    }

    public void show() {
        this.mViewModel.showUserImproveDialogValue = 12;
        this.mCustomDialog.show();
    }

    public boolean isShowing() {
        return this.mCustomDialog.isShowing();
    }

    public void setNight() {
        if (this.viewPrivacyPolicy != null) {
            initViewPrivacyPolicy();
        }
    }

    public void setLight() {
        if (this.viewPrivacyPolicy != null) {
            initViewPrivacyPolicy();
        }
    }

    public void dismiss() {
        this.mCustomDialog.dismiss();
    }

    private void setClickPlaySound(View view, final CustomDialog customDialog) {
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        view.setLayoutParams(new ConstraintLayout.LayoutParams(displayMetrics.widthPixels, displayMetrics.heightPixels));
        view.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.view.ExitUserImprovePrivacyView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RLog.i("NEU_ExitUserImprovePrivacyView", "onClick: 触发按键音");
                customDialog.dismiss();
            }
        });
    }
}
