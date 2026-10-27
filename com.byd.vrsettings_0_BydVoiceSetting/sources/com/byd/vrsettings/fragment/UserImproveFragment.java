package com.byd.vrsettings.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import com.byd.vrsettings.BaseFragment;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.byd.vrsettings.R;
import com.byd.vrsettings.dialog.DetachableDialogDismissListener;
import com.byd.vrsettings.eventbus.FinishUserImproveFragmentBean;
import com.byd.vrsettings.eventbus.HideFragmentBean;
import com.byd.vrsettings.eventbus.ShowFragmentBean;
import com.byd.vrsettings.eventbus.SkinEvent;
import com.byd.vrsettings.service.OtaDataManager;
import com.byd.vrsettings.utils.Common;
import com.byd.vrsettings.utils.Constant;
import com.byd.vrsettings.utils.FastClickUtils;
import com.byd.vrsettings.utils.ModeUtils;
import com.byd.vrsettings.utils.MyViewModel;
import com.byd.vrsettings.utils.MyWebView;
import com.byd.vrsettings.utils.RLog;
import com.byd.vrsettings.view.CustomDialog;
import com.byd.vrsettings.view.DeldataUserImprovePrivacyView;
import com.byd.vrsettings.view.ExitUserImprovePrivacyView;
import com.byd.vrsettings.view.JoinUserImprovePrivacyView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class UserImproveFragment extends BaseFragment implements View.OnClickListener {
    private View bottomView;
    private OnBackPressedCallback callback;
    private CustomDialog cerencePrivacyPolicyDialog;
    private View cerence_privacy;
    private ImageView cerence_privacy_BtnX;
    private View cerence_privacy_line;
    private MyWebView cerence_privacy_webView;
    private ConstraintLayout cl_cerence_privacy;
    private ConstraintLayout cl_userimprove;
    private DeldataUserImprovePrivacyView deldataUserImprovePrivacyView;
    private TextView deldata_backgroud;
    private DetachableDialogDismissListener detachableDialogDismissListener_cerencePrivacyPolicyDialog;
    private ExitUserImprovePrivacyView exitUserImprovePrivacyView;
    private ImageView iv_switch_userImprove;
    private ImageView iv_userimprove_back;
    private ImageView iv_usr_improve_icon;
    private JoinUserImprovePrivacyView joinUserImprovePrivacyView;
    private TextView joinUser_backgroud;
    private Context mContext;
    private MyViewModel mViewModel;
    private ConstraintSet set;
    private TextView tv_copyright;
    private TextView tv_join_user_improve_title;
    private TextView tv_title_cerence_privacy;
    private TextView tv_user_improve_privacy_policy;
    private TextView tv_user_improve_title;
    private View v_cerence_privacyBackground;
    private View view;
    private View viewCerencePrivacy;
    private final String TAG = "NEU_UserImproveFragment";
    private int showUserImproveDialogValue = 0;
    private boolean onconfigchange = false;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeSkin(SkinEvent skinEvent) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        RLog.d("NEU_UserImproveFragment", "onCreate");
        if (bundle != null && bundle.containsKey("showUserImproveDialogValue")) {
            this.showUserImproveDialogValue = bundle.getInt("showUserImproveDialogValue");
        }
        super.onCreate(bundle);
        this.callback = new OnBackPressedCallback(true) { // from class: com.byd.vrsettings.fragment.UserImproveFragment.1
            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                RLog.d("NEU_UserImproveFragment", "requireActivity onBackPressed");
                UserImproveFragment.this.mViewModel.showUserImproveDialogValue = 0;
                RLog.d("NEU_UserImproveFragment", "29 mViewModel.showUserImproveDialogValue==" + UserImproveFragment.this.mViewModel.showUserImproveDialogValue);
                EventBus.getDefault().post(new FinishUserImproveFragmentBean());
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, this.callback);
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        RLog.d("NEU_UserImproveFragment", "onSaveInstanceState");
        JoinUserImprovePrivacyView joinUserImprovePrivacyView = this.joinUserImprovePrivacyView;
        if (joinUserImprovePrivacyView != null) {
            joinUserImprovePrivacyView.DismissJoinWebViewOnChange();
            this.joinUserImprovePrivacyView.clearOnDetachJoinView();
        }
        ExitUserImprovePrivacyView exitUserImprovePrivacyView = this.exitUserImprovePrivacyView;
        if (exitUserImprovePrivacyView != null) {
            exitUserImprovePrivacyView.clearOnDetachExitView();
        }
        DeldataUserImprovePrivacyView deldataUserImprovePrivacyView = this.deldataUserImprovePrivacyView;
        if (deldataUserImprovePrivacyView != null) {
            deldataUserImprovePrivacyView.DismissDelDataWebViewOnchange();
            this.deldataUserImprovePrivacyView.clearOnDetachDeldataView();
        }
        bundle.putInt("showUserImproveDialogValue", this.mViewModel.showUserImproveDialogValue);
        super.onSaveInstanceState(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RLog.d("NEU_UserImproveFragment", "onCreateView  view");
        this.mViewModel = (MyViewModel) new ViewModelProvider(BydVoiceSettingApplication.getContext(), ViewModelProvider.AndroidViewModelFactory.getInstance(BydVoiceSettingApplication.getContext())).get(MyViewModel.class);
        this.view = layoutInflater.inflate(R.layout.layout_setting_user_improve, (ViewGroup) null);
        this.mContext = getContext();
        initView();
        if (getResources().getConfiguration().orientation == 2) {
            setLandScape();
        } else {
            setPortrait();
        }
        if (ModeUtils.isNightMode(getContext())) {
            setNight();
        } else {
            setLight();
        }
        int i = this.showUserImproveDialogValue;
        RLog.i("NEU_UserImproveFragment", "onCreateView: dialogValue  dialogValue dialogValue= " + i);
        switch (i) {
            case 9:
                createCerencePrivacyPolicyDialog();
                break;
            case 10:
                createjoinUserImprovePrivacyView();
                break;
            case 11:
                createjoinUserImprovePrivacyView();
                this.joinUserImprovePrivacyView.createCerencePrivacyPolicyDialog();
                break;
            case 12:
                createExitUserImprovePrivacyView();
                break;
            case 13:
                this.mViewModel.showUserImproveDialogValue = 13;
                createdeldataUserImprovePrivacyView();
                break;
            case 14:
                createdeldataUserImprovePrivacyView();
                this.deldataUserImprovePrivacyView.createCerencePrivacyPolicyDialog();
                this.mViewModel.showUserImproveDialogValue = 14;
                break;
        }
        return this.view;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        RLog.d("NEU_UserImproveFragment", "onResume");
        this.callback.setEnabled(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        RLog.d("NEU_UserImproveFragment", "onPause");
        this.callback.setEnabled(false);
    }

    private void initView() {
        RLog.d("NEU_UserImproveFragment", "init view");
        EventBus.getDefault().register(this);
        this.set = new ConstraintSet();
        this.cl_userimprove = (ConstraintLayout) this.view.findViewById(R.id.cl_userimprove);
        this.tv_user_improve_title = (TextView) this.view.findViewById(R.id.tv_user_improve_title);
        this.tv_copyright = (TextView) this.view.findViewById(R.id.tv_copyright);
        this.iv_userimprove_back = (ImageView) this.view.findViewById(R.id.iv_userimprove_back);
        this.iv_usr_improve_icon = (ImageView) this.view.findViewById(R.id.iv_usr_improve_icon);
        this.bottomView = this.view.findViewById(R.id.bottomView);
        this.iv_userimprove_back.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.UserImproveFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UserImproveFragment.this.mViewModel.showUserImproveDialogValue = 0;
                RLog.d("NEU_UserImproveFragment", "30 mViewModel.showUserImproveDialogValue==" + UserImproveFragment.this.mViewModel.showUserImproveDialogValue);
                EventBus.getDefault().post(new FinishUserImproveFragmentBean());
            }
        });
        this.joinUser_backgroud = (TextView) this.view.findViewById(R.id.joinUser_backgroud);
        this.iv_switch_userImprove = (ImageView) this.view.findViewById(R.id.iv_switch_userImprove);
        this.tv_join_user_improve_title = (TextView) this.view.findViewById(R.id.tv_join_user_improve_title);
        this.tv_user_improve_privacy_policy = (TextView) this.view.findViewById(R.id.tv_user_improve_privacy_policy);
        this.deldata_backgroud = (TextView) this.view.findViewById(R.id.deldata_backgroud);
        this.iv_switch_userImprove.setOnClickListener(this);
        this.deldata_backgroud.setOnClickListener(this);
        String string = Settings.System.getString(this.mContext.getContentResolver(), Constant.KEY_USER_IMPROVEMENT);
        if (string == null || string.length() == 0 || string.equals("false")) {
            setSwitchStatus(this.iv_switch_userImprove, false);
        } else {
            setSwitchStatus(this.iv_switch_userImprove, string.equals("true"));
        }
        reloadData();
    }

    @Override // com.byd.vrsettings.BaseFragment
    protected void onConfigChangeUI(Configuration configuration) {
        if (getResources().getConfiguration().orientation == 2) {
            setLandScape();
        } else {
            setPortrait();
        }
        if (ModeUtils.isNightMode(getContext())) {
            setNight();
        } else {
            setLight();
        }
    }

    private void setLandScape() {
        this.set.clone(this.cl_userimprove);
        this.set.setMargin(R.id.iv_userimprove_back, 6, 54);
        this.set.applyTo(this.cl_userimprove);
        JoinUserImprovePrivacyView joinUserImprovePrivacyView = this.joinUserImprovePrivacyView;
        if (joinUserImprovePrivacyView != null) {
            joinUserImprovePrivacyView.setLandScape();
        }
        ExitUserImprovePrivacyView exitUserImprovePrivacyView = this.exitUserImprovePrivacyView;
        if (exitUserImprovePrivacyView != null) {
            exitUserImprovePrivacyView.setLandScape();
        }
        DeldataUserImprovePrivacyView deldataUserImprovePrivacyView = this.deldataUserImprovePrivacyView;
        if (deldataUserImprovePrivacyView != null) {
            deldataUserImprovePrivacyView.setLandScape();
        }
    }

    private void setPortrait() {
        this.set.clone(this.cl_userimprove);
        this.set.setMargin(R.id.iv_userimprove_back, 6, 30);
        this.set.applyTo(this.cl_userimprove);
        JoinUserImprovePrivacyView joinUserImprovePrivacyView = this.joinUserImprovePrivacyView;
        if (joinUserImprovePrivacyView != null) {
            joinUserImprovePrivacyView.setPortrait();
        }
        ExitUserImprovePrivacyView exitUserImprovePrivacyView = this.exitUserImprovePrivacyView;
        if (exitUserImprovePrivacyView != null) {
            exitUserImprovePrivacyView.setPortrait();
        }
        DeldataUserImprovePrivacyView deldataUserImprovePrivacyView = this.deldataUserImprovePrivacyView;
        if (deldataUserImprovePrivacyView != null) {
            deldataUserImprovePrivacyView.setPortrait();
        }
    }

    private void setNight() {
        String string = Settings.System.getString(this.mContext.getContentResolver(), Constant.KEY_USER_IMPROVEMENT);
        if (string == null || string.length() == 0 || string.equals("false")) {
            setSwitchStatus(this.iv_switch_userImprove, false);
        } else {
            setSwitchStatus(this.iv_switch_userImprove, string.equals("true"));
        }
        this.tv_user_improve_title.setTextColor(getResources().getColor(R.color.voice_setting_title_night, null));
        this.joinUser_backgroud.setBackgroundResource(R.drawable.bg_view_user_improve_night);
        this.tv_copyright.setTextColor(getResources().getColor(R.color.voice_setting_tip_night, null));
        this.tv_join_user_improve_title.setTextColor(getResources().getColor(R.color.tv_join_user_improve_title_color_night, null));
        this.tv_user_improve_privacy_policy.setTextColor(getResources().getColor(R.color.tv_user_improve_privacy_policy_color_night, null));
        this.deldata_backgroud.setBackgroundResource(R.drawable.bg_view_user_improve_night);
        this.iv_userimprove_back.setImageDrawable(getResources().getDrawable(R.mipmap.btn_back_n_night, null));
        this.iv_usr_improve_icon.setImageDrawable(getResources().getDrawable(R.mipmap.iv_jiantou_night, null));
        JoinUserImprovePrivacyView joinUserImprovePrivacyView = this.joinUserImprovePrivacyView;
        if (joinUserImprovePrivacyView != null) {
            joinUserImprovePrivacyView.setNight();
        }
        ExitUserImprovePrivacyView exitUserImprovePrivacyView = this.exitUserImprovePrivacyView;
        if (exitUserImprovePrivacyView != null) {
            exitUserImprovePrivacyView.setNight();
        }
        DeldataUserImprovePrivacyView deldataUserImprovePrivacyView = this.deldataUserImprovePrivacyView;
        if (deldataUserImprovePrivacyView != null) {
            deldataUserImprovePrivacyView.setNight();
        }
        if (this.cerence_privacy_webView != null) {
            this.onconfigchange = true;
            RLog.d("NEU_UserImproveFragment", "initDisclaimer start");
            initcreateCerencePrivacyPolicyDialog();
            RLog.d("NEU_UserImproveFragment", "initDisclaimer end");
        }
    }

    private void setLight() {
        String string = Settings.System.getString(this.mContext.getContentResolver(), Constant.KEY_USER_IMPROVEMENT);
        if (string == null || string.length() == 0 || string.equals("false")) {
            setSwitchStatus(this.iv_switch_userImprove, false);
        } else {
            setSwitchStatus(this.iv_switch_userImprove, string.equals("true"));
        }
        this.tv_user_improve_title.setTextColor(getResources().getColor(R.color.voice_setting_title, null));
        this.joinUser_backgroud.setBackgroundResource(R.drawable.bg_view_user_improve);
        this.deldata_backgroud.setBackgroundResource(R.drawable.bg_view_user_improve);
        this.tv_join_user_improve_title.setTextColor(getResources().getColor(R.color.tv_join_user_improve_title_color, null));
        this.tv_user_improve_privacy_policy.setTextColor(getResources().getColor(R.color.tv_user_improve_privacy_policy_color, null));
        this.tv_copyright.setTextColor(getResources().getColor(R.color.voice_setting_tip, null));
        this.iv_userimprove_back.setImageDrawable(getResources().getDrawable(R.mipmap.btn_back_n, null));
        this.iv_usr_improve_icon.setImageDrawable(getResources().getDrawable(R.mipmap.iv_jiantou, null));
        JoinUserImprovePrivacyView joinUserImprovePrivacyView = this.joinUserImprovePrivacyView;
        if (joinUserImprovePrivacyView != null) {
            joinUserImprovePrivacyView.setLight();
        }
        ExitUserImprovePrivacyView exitUserImprovePrivacyView = this.exitUserImprovePrivacyView;
        if (exitUserImprovePrivacyView != null) {
            exitUserImprovePrivacyView.setLight();
        }
        DeldataUserImprovePrivacyView deldataUserImprovePrivacyView = this.deldataUserImprovePrivacyView;
        if (deldataUserImprovePrivacyView != null) {
            deldataUserImprovePrivacyView.setLight();
        }
        if (this.cerence_privacy_webView != null) {
            this.onconfigchange = true;
            RLog.d("NEU_UserImproveFragment", "initDisclaimer start");
            initcreateCerencePrivacyPolicyDialog();
            RLog.d("NEU_UserImproveFragment", "initDisclaimer end");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hideFragment(HideFragmentBean hideFragmentBean) {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.beginTransaction().hide(this).commit();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showFragment(ShowFragmentBean showFragmentBean) {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.beginTransaction().show(this).commit();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        RLog.d("NEU_UserImproveFragment", "UserImproveFragment onDestroyView");
        super.onDestroyView();
        JoinUserImprovePrivacyView joinUserImprovePrivacyView = this.joinUserImprovePrivacyView;
        if (joinUserImprovePrivacyView != null) {
            if (joinUserImprovePrivacyView.isShowing()) {
                this.joinUserImprovePrivacyView.dismiss();
            }
            this.joinUserImprovePrivacyView = null;
        }
        ExitUserImprovePrivacyView exitUserImprovePrivacyView = this.exitUserImprovePrivacyView;
        if (exitUserImprovePrivacyView != null) {
            if (exitUserImprovePrivacyView.isShowing()) {
                this.exitUserImprovePrivacyView.dismiss();
            }
            this.exitUserImprovePrivacyView = null;
        }
        DeldataUserImprovePrivacyView deldataUserImprovePrivacyView = this.deldataUserImprovePrivacyView;
        if (deldataUserImprovePrivacyView != null) {
            if (deldataUserImprovePrivacyView.isShowing()) {
                RLog.d("NEU_UserImproveFragment", "deldataUserImprovePrivacyView onDestroyView dismiss");
                this.deldataUserImprovePrivacyView.dismiss();
            }
            this.deldataUserImprovePrivacyView = null;
        }
        destroyWebView();
        EventBus.getDefault().unregister(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        RLog.d("NEU_UserImproveFragment", "UserImproveFragment onDestroy");
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        RLog.i("NEU_UserImproveFragment", "onHiddenChanged: onHiddenChangedon HiddenChanged ");
        super.onHiddenChanged(z);
        if (z) {
            this.callback.setEnabled(false);
            JoinUserImprovePrivacyView joinUserImprovePrivacyView = this.joinUserImprovePrivacyView;
            if (joinUserImprovePrivacyView != null && joinUserImprovePrivacyView.isShowing()) {
                this.joinUserImprovePrivacyView.dismiss();
                this.joinUserImprovePrivacyView = null;
            }
            ExitUserImprovePrivacyView exitUserImprovePrivacyView = this.exitUserImprovePrivacyView;
            if (exitUserImprovePrivacyView != null && exitUserImprovePrivacyView.isShowing()) {
                this.exitUserImprovePrivacyView.dismiss();
                this.exitUserImprovePrivacyView = null;
            }
            DeldataUserImprovePrivacyView deldataUserImprovePrivacyView = this.deldataUserImprovePrivacyView;
            if (deldataUserImprovePrivacyView == null || !deldataUserImprovePrivacyView.isShowing()) {
                return;
            }
            this.deldataUserImprovePrivacyView.dismiss();
            this.deldataUserImprovePrivacyView = null;
            return;
        }
        this.callback.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createCerencePrivacyPolicyDialog() {
        MyWebView myWebView;
        if (Common.isFastDoubleClick(11, 1000L)) {
            return;
        }
        this.mViewModel.showUserImproveDialogValue = 9;
        this.viewCerencePrivacy = LayoutInflater.from(this.mContext).inflate(R.layout.layout_cerence_privacy_pllicy, (ViewGroup) null);
        OtaDataManager.switchAppLanguage(getActivity());
        this.cl_cerence_privacy = (ConstraintLayout) this.viewCerencePrivacy.findViewById(R.id.cl_cerence_privacy);
        this.cerencePrivacyPolicyDialog = new CustomDialog(this.mContext, this.viewCerencePrivacy);
        View viewFindViewById = this.viewCerencePrivacy.findViewById(R.id.v_cerence_privacyBackground);
        this.v_cerence_privacyBackground = viewFindViewById;
        setClickPlaySound(viewFindViewById, this.cerencePrivacyPolicyDialog);
        this.cerence_privacy = this.viewCerencePrivacy.findViewById(R.id.cerence_privacy);
        MyWebView myWebView2 = (MyWebView) this.viewCerencePrivacy.findViewById(R.id.cerence_privacy_webView);
        this.cerence_privacy_webView = myWebView2;
        myWebView2.setLayerType(1, null);
        if (this.mViewModel.curPos != 0 && (myWebView = this.cerence_privacy_webView) != null) {
            myWebView.scrollTo(0, this.mViewModel.curPos);
        }
        this.cerence_privacy_webView.setWebChromeClient(new WebChromeClient() { // from class: com.byd.vrsettings.fragment.UserImproveFragment.3
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                if (webView.getProgress() == 100) {
                    Common.jumpToY(UserImproveFragment.this.cerence_privacy_webView, UserImproveFragment.this.mViewModel.curPos);
                }
            }
        });
        this.cerence_privacy_webView.setOnOveredScroll(new MyWebView.onOveredScroll() { // from class: com.byd.vrsettings.fragment.UserImproveFragment.4
            @Override // com.byd.vrsettings.utils.MyWebView.onOveredScroll
            public void onOvered(int i) {
                if (UserImproveFragment.this.onconfigchange) {
                    UserImproveFragment.this.onconfigchange = false;
                } else {
                    RLog.d("NEU_UserImproveFragment", "setOnOveredScroll  scrollY==" + i);
                    UserImproveFragment.this.mViewModel.curPos = i;
                }
            }
        });
        this.cerence_privacy_BtnX = (ImageView) this.viewCerencePrivacy.findViewById(R.id.cerence_privacy_BtnX);
        this.tv_title_cerence_privacy = (TextView) this.viewCerencePrivacy.findViewById(R.id.tv_title_cerence_privacy);
        this.cerence_privacy_line = this.viewCerencePrivacy.findViewById(R.id.cerence_privacy_line);
        this.tv_title_cerence_privacy.setText(R.string.text_improve_privacy_policy);
        initcreateCerencePrivacyPolicyDialog();
        DetachableDialogDismissListener detachableDialogDismissListenerWrap = DetachableDialogDismissListener.wrap(new DialogInterface.OnDismissListener() { // from class: com.byd.vrsettings.fragment.UserImproveFragment.5
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                UserImproveFragment.this.destroyWebView();
                UserImproveFragment.this.viewCerencePrivacy = null;
                UserImproveFragment.this.mViewModel.curPos = 0;
                UserImproveFragment.this.mViewModel.showUserImproveDialogValue = 0;
                RLog.d("NEU_UserImproveFragment", "32 mViewModel.showUserImproveDialogValue==" + UserImproveFragment.this.mViewModel.showUserImproveDialogValue);
                UserImproveFragment.this.detachableDialogDismissListener_cerencePrivacyPolicyDialog.clearOnDetach(UserImproveFragment.this.cerencePrivacyPolicyDialog);
            }
        });
        this.detachableDialogDismissListener_cerencePrivacyPolicyDialog = detachableDialogDismissListenerWrap;
        this.cerencePrivacyPolicyDialog.setOnDismissListener(detachableDialogDismissListenerWrap);
        this.cerencePrivacyPolicyDialog.show();
        this.cerence_privacy_BtnX.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.UserImproveFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RLog.e("NEU_UserImproveFragment", "customDialog===cerence_privacy_BtnX");
                UserImproveFragment.this.mViewModel.showUserImproveDialogValue = 0;
                RLog.d("NEU_UserImproveFragment", "33 mViewModel.showUserImproveDialogValue==" + UserImproveFragment.this.mViewModel.showUserImproveDialogValue);
                UserImproveFragment.this.mViewModel.curPos = 0;
                UserImproveFragment.this.destroyWebView();
                UserImproveFragment.this.cerencePrivacyPolicyDialog.dismiss();
                UserImproveFragment.this.viewCerencePrivacy = null;
            }
        });
    }

    public void AddUserImproveFragmentwebviewListen() {
        RLog.d("NEU_UserImproveFragment", "AddUserImproveFragmentwebviewListen  cerencePrivacyPolicyDialog");
        CustomDialog customDialog = this.cerencePrivacyPolicyDialog;
        if (customDialog != null) {
            customDialog.setOnDismissListener(this.detachableDialogDismissListener_cerencePrivacyPolicyDialog);
        }
    }

    private void initcreateCerencePrivacyPolicyDialog() {
        if (ModeUtils.isNightMode(this.mContext)) {
            this.cl_cerence_privacy.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_night, null));
            this.cerence_privacy.setBackground(this.mContext.getResources().getDrawable(R.mipmap.view_dialog_background_night, null));
            this.tv_title_cerence_privacy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_titleTextColor_night, null));
            this.cerence_privacy_line.setBackgroundColor(this.mContext.getResources().getColor(R.color.diglog_line_backColor_night_8155, null));
            this.cerence_privacy_webView.setBackgroundColor(0);
            this.cerence_privacy_webView.getBackground().setAlpha(0);
            this.cerence_privacy_webView.setVerticalScrollbarThumbDrawable(this.mContext.getResources().getDrawable(R.drawable.bg_scrollbar_night, null));
            this.cerence_privacy_webView.loadUrl(Common.getUserExperiencePrivacyPath(true));
            this.cerence_privacy_BtnX.setBackground(this.mContext.getResources().getDrawable(R.mipmap.dialog_icon_back_night, null));
        } else {
            this.cl_cerence_privacy.setBackgroundColor(getResources().getColor(R.color.dialog_backgroud_color_day, null));
            this.cerence_privacy.setBackground(this.mContext.getResources().getDrawable(R.mipmap.view_dialog_background, null));
            this.tv_title_cerence_privacy.setTextColor(this.mContext.getResources().getColor(R.color.dialog_titleTextColor, null));
            this.cerence_privacy_webView.loadUrl(Common.getUserExperiencePrivacyPath(false));
            this.cerence_privacy_line.setBackgroundColor(this.mContext.getResources().getColor(R.color.diglog_line_backColor_8155, null));
            this.cerence_privacy_webView.setBackgroundColor(0);
            this.cerence_privacy_webView.getBackground().setAlpha(0);
            this.cerence_privacy_webView.setVerticalScrollbarThumbDrawable(this.mContext.getResources().getDrawable(R.drawable.bg_scrollbar, null));
            this.cerence_privacy_BtnX.setBackground(this.mContext.getResources().getDrawable(R.mipmap.dialog_icon_back, null));
        }
        this.cerence_privacy.setLayoutParams(new ConstraintLayout.LayoutParams((int) TypedValue.applyDimension(1, 608.0f, this.mContext.getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 592.0f, this.mContext.getResources().getDisplayMetrics())));
        this.set.clone(this.cl_cerence_privacy);
        this.set.connect(R.id.cerence_privacy, 6, R.id.cl_cerence_privacy, 6);
        this.set.connect(R.id.cerence_privacy, 3, R.id.cl_cerence_privacy, 3);
        this.set.connect(R.id.cerence_privacy, 7, R.id.cl_cerence_privacy, 7);
        this.set.connect(R.id.cerence_privacy, 4, R.id.cl_cerence_privacy, 4);
        this.set.applyTo(this.cl_cerence_privacy);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroyWebView() {
        MyWebView myWebView = this.cerence_privacy_webView;
        if (myWebView != null) {
            ((ViewGroup) myWebView.getParent()).removeAllViews();
            this.cerence_privacy_webView.clearHistory();
            this.cerence_privacy_webView.clearCache(true);
            this.cerence_privacy_webView.loadUrl("about:blank");
            this.cerence_privacy_webView.onPause();
            this.cerence_privacy_webView.removeAllViews();
            this.cerence_privacy_webView.destroyDrawingCache();
            this.cerence_privacy_webView.pauseTimers();
            this.cerence_privacy_webView.destroy();
            this.cerence_privacy_webView = null;
        }
    }

    private void jumpToY(final int i) {
        MyWebView myWebView = this.cerence_privacy_webView;
        if (myWebView != null) {
            myWebView.postDelayed(new Runnable() { // from class: com.byd.vrsettings.fragment.UserImproveFragment.7
                @Override // java.lang.Runnable
                public void run() {
                    if (UserImproveFragment.this.cerence_privacy_webView != null) {
                        UserImproveFragment.this.cerence_privacy_webView.scrollTo(0, i);
                    }
                }
            }, 300L);
        }
    }

    public void createjoinUserImprovePrivacyView() {
        RLog.d("NEU_UserImproveFragment", "createjoinUserImprovePrivacyView  show join dialog");
        JoinUserImprovePrivacyView joinUserImprovePrivacyView = this.joinUserImprovePrivacyView;
        if (joinUserImprovePrivacyView == null) {
            JoinUserImprovePrivacyView joinUserImprovePrivacyView2 = new JoinUserImprovePrivacyView(getContext(), new JoinUserImprovePrivacyView.clickConfirm() { // from class: com.byd.vrsettings.fragment.UserImproveFragment.8
                @Override // com.byd.vrsettings.view.JoinUserImprovePrivacyView.clickConfirm
                public void clickConfirm() {
                    RLog.d("NEU_UserImproveFragment", "onclick   join dialog clickConfirm");
                    UserImproveFragment userImproveFragment = UserImproveFragment.this;
                    userImproveFragment.setSwitchStatus(userImproveFragment.iv_switch_userImprove, true);
                    UserImproveFragment.this.joinUserImprovePrivacyView = null;
                }
            }, new JoinUserImprovePrivacyView.clickCancel() { // from class: com.byd.vrsettings.fragment.UserImproveFragment.9
                @Override // com.byd.vrsettings.view.JoinUserImprovePrivacyView.clickCancel
                public void clickCancel() {
                    RLog.d("NEU_UserImproveFragment", "onclick   join dialog clickCancel");
                    UserImproveFragment.this.joinUserImprovePrivacyView = null;
                }
            });
            this.joinUserImprovePrivacyView = joinUserImprovePrivacyView2;
            joinUserImprovePrivacyView2.show(2);
        } else {
            joinUserImprovePrivacyView.AddJoindialogListen();
            this.joinUserImprovePrivacyView.show(2);
        }
    }

    public void createExitUserImprovePrivacyView() {
        RLog.d("NEU_UserImproveFragment", "createExitUserImprovePrivacyView  show exit dialog");
        ExitUserImprovePrivacyView exitUserImprovePrivacyView = this.exitUserImprovePrivacyView;
        if (exitUserImprovePrivacyView == null) {
            ExitUserImprovePrivacyView exitUserImprovePrivacyView2 = new ExitUserImprovePrivacyView(getContext(), new ExitUserImprovePrivacyView.clickConfirm() { // from class: com.byd.vrsettings.fragment.UserImproveFragment.10
                @Override // com.byd.vrsettings.view.ExitUserImprovePrivacyView.clickConfirm
                public void clickConfirm() {
                    RLog.d("NEU_UserImproveFragment", "onclick   exit dialog clickConfirm");
                    UserImproveFragment userImproveFragment = UserImproveFragment.this;
                    userImproveFragment.setSwitchStatus(userImproveFragment.iv_switch_userImprove, false);
                    UserImproveFragment.this.exitUserImprovePrivacyView = null;
                }
            }, new ExitUserImprovePrivacyView.clickCancel() { // from class: com.byd.vrsettings.fragment.UserImproveFragment.11
                @Override // com.byd.vrsettings.view.ExitUserImprovePrivacyView.clickCancel
                public void clickCancel() {
                    RLog.d("NEU_UserImproveFragment", "onclick   exit dialog clickCancel");
                    UserImproveFragment.this.exitUserImprovePrivacyView = null;
                }
            });
            this.exitUserImprovePrivacyView = exitUserImprovePrivacyView2;
            exitUserImprovePrivacyView2.show();
        } else {
            exitUserImprovePrivacyView.AddExitdialogListen();
            this.exitUserImprovePrivacyView.show();
        }
    }

    public void createdeldataUserImprovePrivacyView() {
        RLog.d("NEU_UserImproveFragment", "createdeldataUserImprovePrivacyView  show delete data dialog");
        DeldataUserImprovePrivacyView deldataUserImprovePrivacyView = this.deldataUserImprovePrivacyView;
        if (deldataUserImprovePrivacyView == null) {
            DeldataUserImprovePrivacyView deldataUserImprovePrivacyView2 = new DeldataUserImprovePrivacyView(getContext(), new DeldataUserImprovePrivacyView.clickConfirm() { // from class: com.byd.vrsettings.fragment.UserImproveFragment.12
                @Override // com.byd.vrsettings.view.DeldataUserImprovePrivacyView.clickConfirm
                public void clickConfirm() {
                    RLog.d("NEU_UserImproveFragment", "88  mViewModel.showUserImproveDialogValue==" + UserImproveFragment.this.mViewModel.showUserImproveDialogValue);
                    RLog.d("NEU_UserImproveFragment", "onclick   delete data dialog clickConfirm");
                    UserImproveFragment.this.deldataUserImprovePrivacyView = null;
                }
            }, new DeldataUserImprovePrivacyView.clickCancel() { // from class: com.byd.vrsettings.fragment.UserImproveFragment.13
                @Override // com.byd.vrsettings.view.DeldataUserImprovePrivacyView.clickCancel
                public void clickCancel() {
                    RLog.d("NEU_UserImproveFragment", "89  mViewModel.showUserImproveDialogValue==" + UserImproveFragment.this.mViewModel.showUserImproveDialogValue);
                    RLog.d("NEU_UserImproveFragment", "onclick   deleta data dialog clickConfirm");
                    UserImproveFragment.this.deldataUserImprovePrivacyView = null;
                }
            });
            this.deldataUserImprovePrivacyView = deldataUserImprovePrivacyView2;
            deldataUserImprovePrivacyView2.show();
        } else {
            deldataUserImprovePrivacyView.AddDeldialogListen();
            this.deldataUserImprovePrivacyView.show();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (FastClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.deldata_backgroud) {
            createdeldataUserImprovePrivacyView();
            return;
        }
        if (id != R.id.iv_switch_userImprove) {
            return;
        }
        RLog.d("NEU_UserImproveFragment", "onclick  iv_switch_userImprove");
        String string = Settings.System.getString(this.mContext.getContentResolver(), Constant.KEY_USER_IMPROVEMENT);
        if (string == null || string.length() == 0 || string.equals("false")) {
            RLog.d("NEU_UserImproveFragment", "onclick  show join dialog");
            createjoinUserImprovePrivacyView();
        } else {
            RLog.d("NEU_UserImproveFragment", "EXIT  USER IMPROVE");
            createExitUserImprovePrivacyView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSwitchStatus(ImageView imageView, boolean z) {
        imageView.setClickable(true);
        if (z) {
            imageView.setBackground(getResources().getDrawable(R.mipmap.switch_open, null));
        } else if (ModeUtils.isNightMode(getContext())) {
            imageView.setBackground(getResources().getDrawable(R.mipmap.switch_off_light_night, null));
        } else {
            imageView.setBackground(getResources().getDrawable(R.mipmap.switch_off_light, null));
        }
    }

    private void setClickPlaySound(View view, final CustomDialog customDialog) {
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        view.setLayoutParams(new ConstraintLayout.LayoutParams(displayMetrics.widthPixels, displayMetrics.heightPixels));
        view.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.fragment.UserImproveFragment.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RLog.i("NEU_UserImproveFragment", "onClick: 触发按键音");
                if (view2 == UserImproveFragment.this.v_cerence_privacyBackground) {
                    UserImproveFragment.this.mViewModel.curPos = 0;
                }
                customDialog.dismiss();
            }
        });
    }

    @Override // com.byd.vrsettings.BaseFragment
    protected void reloadData() {
        String string = getContext().getResources().getString(R.string.user_privacy_improvement_text);
        String string2 = getContext().getResources().getString(R.string.join_improvement_click);
        int iLastIndexOf = string.lastIndexOf(string2);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        try {
            spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.byd.vrsettings.fragment.UserImproveFragment.15
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    UserImproveFragment.this.createCerencePrivacyPolicyDialog();
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(Color.parseColor("#00ACEB"));
                    textPaint.setUnderlineText(true);
                }
            }, iLastIndexOf, string2.length() + iLastIndexOf, 33);
            this.tv_user_improve_privacy_policy.setText(spannableStringBuilder);
            TextView textView = this.tv_user_improve_privacy_policy;
            textView.setHighlightColor(ContextCompat.getColor(textView.getContext(), android.R.color.transparent));
            this.tv_user_improve_privacy_policy.setMovementMethod(LinkMovementMethod.getInstance());
        } catch (Exception unused) {
            RLog.d("NEU_UserImproveFragment", "span 崩溃");
        }
    }
}
