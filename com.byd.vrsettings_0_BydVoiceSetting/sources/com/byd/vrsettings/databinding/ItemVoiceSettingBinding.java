package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.AppTextView;
import com.byd.vrsettings.view.CustomScrollView;
import com.byd.vrsettings.view.CustomSettingCell;

/* JADX INFO: loaded from: classes.dex */
public final class ItemVoiceSettingBinding implements ViewBinding {
    public final ConstraintLayout clItemVoiceSetting;
    public final ImageView ivSwitchAudioRegister;
    public final ImageView ivSwitchAudioWakeup;
    public final ImageView ivSwitchContiunueDialog;
    public final ImageView ivSwitchPrivacy;
    public final ImageView ivTabMoreRegisterLock;
    public final ImageView ivTabMoreUserDefine;
    public final ImageView ivTabMoreWakeupFree;
    public final ImageView ivVolumeMove;
    public final ImageView languageIcon;
    private final CustomScrollView rootView;
    public final SeekBar sbVolume;
    public final CustomScrollView svSetting;
    public final AppTextView tvAudioRegisteTitle;
    public final AppTextView tvAudioRegisteTitleTip;
    public final AppTextView tvAudioWakeupTitle;
    public final AppTextView tvAudioWakeupTitleTip;
    public final AppTextView tvContiunueDialogTitle;
    public final AppTextView tvContiunueDialogTitleTip;
    public final AppTextView tvCurLanguage;
    public final AppTextView tvLanguage;
    public final AppTextView tvPrivacyDisclaimer;
    public final AppTextView tvRegisterLockTip;
    public final AppTextView tvRegisterLockTitle;
    public final AppTextView tvUserDefineTip;
    public final AppTextView tvUserDefineTitle;
    public final AppTextView tvUserImporvementDisclaimer;
    public final AppTextView tvVersionDisclaimer;
    public final AppTextView tvVoiceBroadcastVolume;
    public final TextView tvVolume;
    public final AppTextView tvVolumeMove;
    public final AppTextView tvWakeup;
    public final AppTextView tvWakeupFreeTip;
    public final AppTextView tvWakeupFreeTitle;
    public final ConstraintLayout vCard;
    public final CustomSettingCell vContiunueDialog;
    public final CustomSettingCell vLanguage;
    public final CustomSettingCell vPrivacy;
    public final View vRegisterLockHided;
    public final CustomSettingCell vUserImporvement;
    public final ImageView vUserImporvementIcon;
    public final CustomSettingCell vVersion;
    public final CustomSettingCell vVolume;
    public final CustomSettingCell vWakeup;
    public final ImageView versionIcon;
    public final View view;
    public final View viewUpdatePoint;
    public final ConstraintLayout voiceSettingCardRegisterLock;
    public final ConstraintLayout voiceSettingCardUserDefine;
    public final ConstraintLayout voiceSettingCardWakeupFree;

    private ItemVoiceSettingBinding(CustomScrollView customScrollView, ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, ImageView imageView8, ImageView imageView9, SeekBar seekBar, CustomScrollView customScrollView2, AppTextView appTextView, AppTextView appTextView2, AppTextView appTextView3, AppTextView appTextView4, AppTextView appTextView5, AppTextView appTextView6, AppTextView appTextView7, AppTextView appTextView8, AppTextView appTextView9, AppTextView appTextView10, AppTextView appTextView11, AppTextView appTextView12, AppTextView appTextView13, AppTextView appTextView14, AppTextView appTextView15, AppTextView appTextView16, TextView textView, AppTextView appTextView17, AppTextView appTextView18, AppTextView appTextView19, AppTextView appTextView20, ConstraintLayout constraintLayout2, CustomSettingCell customSettingCell, CustomSettingCell customSettingCell2, CustomSettingCell customSettingCell3, View view, CustomSettingCell customSettingCell4, ImageView imageView10, CustomSettingCell customSettingCell5, CustomSettingCell customSettingCell6, CustomSettingCell customSettingCell7, ImageView imageView11, View view2, View view3, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5) {
        this.rootView = customScrollView;
        this.clItemVoiceSetting = constraintLayout;
        this.ivSwitchAudioRegister = imageView;
        this.ivSwitchAudioWakeup = imageView2;
        this.ivSwitchContiunueDialog = imageView3;
        this.ivSwitchPrivacy = imageView4;
        this.ivTabMoreRegisterLock = imageView5;
        this.ivTabMoreUserDefine = imageView6;
        this.ivTabMoreWakeupFree = imageView7;
        this.ivVolumeMove = imageView8;
        this.languageIcon = imageView9;
        this.sbVolume = seekBar;
        this.svSetting = customScrollView2;
        this.tvAudioRegisteTitle = appTextView;
        this.tvAudioRegisteTitleTip = appTextView2;
        this.tvAudioWakeupTitle = appTextView3;
        this.tvAudioWakeupTitleTip = appTextView4;
        this.tvContiunueDialogTitle = appTextView5;
        this.tvContiunueDialogTitleTip = appTextView6;
        this.tvCurLanguage = appTextView7;
        this.tvLanguage = appTextView8;
        this.tvPrivacyDisclaimer = appTextView9;
        this.tvRegisterLockTip = appTextView10;
        this.tvRegisterLockTitle = appTextView11;
        this.tvUserDefineTip = appTextView12;
        this.tvUserDefineTitle = appTextView13;
        this.tvUserImporvementDisclaimer = appTextView14;
        this.tvVersionDisclaimer = appTextView15;
        this.tvVoiceBroadcastVolume = appTextView16;
        this.tvVolume = textView;
        this.tvVolumeMove = appTextView17;
        this.tvWakeup = appTextView18;
        this.tvWakeupFreeTip = appTextView19;
        this.tvWakeupFreeTitle = appTextView20;
        this.vCard = constraintLayout2;
        this.vContiunueDialog = customSettingCell;
        this.vLanguage = customSettingCell2;
        this.vPrivacy = customSettingCell3;
        this.vRegisterLockHided = view;
        this.vUserImporvement = customSettingCell4;
        this.vUserImporvementIcon = imageView10;
        this.vVersion = customSettingCell5;
        this.vVolume = customSettingCell6;
        this.vWakeup = customSettingCell7;
        this.versionIcon = imageView11;
        this.view = view2;
        this.viewUpdatePoint = view3;
        this.voiceSettingCardRegisterLock = constraintLayout3;
        this.voiceSettingCardUserDefine = constraintLayout4;
        this.voiceSettingCardWakeupFree = constraintLayout5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CustomScrollView getRoot() {
        return this.rootView;
    }

    public static ItemVoiceSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemVoiceSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_voice_setting, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemVoiceSettingBinding bind(View view) {
        int i = R.id.cl_item_voice_setting;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.cl_item_voice_setting);
        if (constraintLayout != null) {
            i = R.id.iv_switch_audio_register;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_switch_audio_register);
            if (imageView != null) {
                i = R.id.iv_switch_audio_wakeup;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_switch_audio_wakeup);
                if (imageView2 != null) {
                    i = R.id.iv_switch_contiunue_dialog;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_switch_contiunue_dialog);
                    if (imageView3 != null) {
                        i = R.id.iv_switch_privacy;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_switch_privacy);
                        if (imageView4 != null) {
                            i = R.id.iv_tab_more_register_lock;
                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tab_more_register_lock);
                            if (imageView5 != null) {
                                i = R.id.iv_tab_more_user_define;
                                ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tab_more_user_define);
                                if (imageView6 != null) {
                                    i = R.id.iv_tab_more_wakeup_free;
                                    ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tab_more_wakeup_free);
                                    if (imageView7 != null) {
                                        i = R.id.iv_volume_move;
                                        ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_volume_move);
                                        if (imageView8 != null) {
                                            i = R.id.language_icon;
                                            ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(view, R.id.language_icon);
                                            if (imageView9 != null) {
                                                i = R.id.sb_volume;
                                                SeekBar seekBar = (SeekBar) ViewBindings.findChildViewById(view, R.id.sb_volume);
                                                if (seekBar != null) {
                                                    CustomScrollView customScrollView = (CustomScrollView) view;
                                                    i = R.id.tv_audio_registe_title;
                                                    AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_audio_registe_title);
                                                    if (appTextView != null) {
                                                        i = R.id.tv_audio_registe_title_tip;
                                                        AppTextView appTextView2 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_audio_registe_title_tip);
                                                        if (appTextView2 != null) {
                                                            i = R.id.tv_audio_wakeup_title;
                                                            AppTextView appTextView3 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_audio_wakeup_title);
                                                            if (appTextView3 != null) {
                                                                i = R.id.tv_audio_wakeup_title_tip;
                                                                AppTextView appTextView4 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_audio_wakeup_title_tip);
                                                                if (appTextView4 != null) {
                                                                    i = R.id.tv_contiunue_dialog_title;
                                                                    AppTextView appTextView5 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_contiunue_dialog_title);
                                                                    if (appTextView5 != null) {
                                                                        i = R.id.tv_contiunue_dialog_title_tip;
                                                                        AppTextView appTextView6 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_contiunue_dialog_title_tip);
                                                                        if (appTextView6 != null) {
                                                                            i = R.id.tv_cur_language;
                                                                            AppTextView appTextView7 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_cur_language);
                                                                            if (appTextView7 != null) {
                                                                                i = R.id.tv_language;
                                                                                AppTextView appTextView8 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_language);
                                                                                if (appTextView8 != null) {
                                                                                    i = R.id.tv_privacy_disclaimer;
                                                                                    AppTextView appTextView9 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_privacy_disclaimer);
                                                                                    if (appTextView9 != null) {
                                                                                        i = R.id.tv_register_lock_tip;
                                                                                        AppTextView appTextView10 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_register_lock_tip);
                                                                                        if (appTextView10 != null) {
                                                                                            i = R.id.tv_register_lock_title;
                                                                                            AppTextView appTextView11 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_register_lock_title);
                                                                                            if (appTextView11 != null) {
                                                                                                i = R.id.tv_user_define_tip;
                                                                                                AppTextView appTextView12 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_user_define_tip);
                                                                                                if (appTextView12 != null) {
                                                                                                    i = R.id.tv_user_define_title;
                                                                                                    AppTextView appTextView13 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_user_define_title);
                                                                                                    if (appTextView13 != null) {
                                                                                                        i = R.id.tv_user_Imporvement_disclaimer;
                                                                                                        AppTextView appTextView14 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_user_Imporvement_disclaimer);
                                                                                                        if (appTextView14 != null) {
                                                                                                            i = R.id.tv_version_disclaimer;
                                                                                                            AppTextView appTextView15 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_version_disclaimer);
                                                                                                            if (appTextView15 != null) {
                                                                                                                i = R.id.tv_voice_broadcast_volume;
                                                                                                                AppTextView appTextView16 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_voice_broadcast_volume);
                                                                                                                if (appTextView16 != null) {
                                                                                                                    i = R.id.tv_volume;
                                                                                                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_volume);
                                                                                                                    if (textView != null) {
                                                                                                                        i = R.id.tv_volume_move;
                                                                                                                        AppTextView appTextView17 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_volume_move);
                                                                                                                        if (appTextView17 != null) {
                                                                                                                            i = R.id.tv_wakeup;
                                                                                                                            AppTextView appTextView18 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_wakeup);
                                                                                                                            if (appTextView18 != null) {
                                                                                                                                i = R.id.tv_wakeup_free_tip;
                                                                                                                                AppTextView appTextView19 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_wakeup_free_tip);
                                                                                                                                if (appTextView19 != null) {
                                                                                                                                    i = R.id.tv_wakeup_free_title;
                                                                                                                                    AppTextView appTextView20 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_wakeup_free_title);
                                                                                                                                    if (appTextView20 != null) {
                                                                                                                                        i = R.id.v_card;
                                                                                                                                        ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.v_card);
                                                                                                                                        if (constraintLayout2 != null) {
                                                                                                                                            i = R.id.v_contiunue_dialog;
                                                                                                                                            CustomSettingCell customSettingCell = (CustomSettingCell) ViewBindings.findChildViewById(view, R.id.v_contiunue_dialog);
                                                                                                                                            if (customSettingCell != null) {
                                                                                                                                                i = R.id.v_language;
                                                                                                                                                CustomSettingCell customSettingCell2 = (CustomSettingCell) ViewBindings.findChildViewById(view, R.id.v_language);
                                                                                                                                                if (customSettingCell2 != null) {
                                                                                                                                                    i = R.id.v_privacy;
                                                                                                                                                    CustomSettingCell customSettingCell3 = (CustomSettingCell) ViewBindings.findChildViewById(view, R.id.v_privacy);
                                                                                                                                                    if (customSettingCell3 != null) {
                                                                                                                                                        i = R.id.v_register_lock_hided;
                                                                                                                                                        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.v_register_lock_hided);
                                                                                                                                                        if (viewFindChildViewById != null) {
                                                                                                                                                            i = R.id.v_user_Imporvement;
                                                                                                                                                            CustomSettingCell customSettingCell4 = (CustomSettingCell) ViewBindings.findChildViewById(view, R.id.v_user_Imporvement);
                                                                                                                                                            if (customSettingCell4 != null) {
                                                                                                                                                                i = R.id.v_user_Imporvement_icon;
                                                                                                                                                                ImageView imageView10 = (ImageView) ViewBindings.findChildViewById(view, R.id.v_user_Imporvement_icon);
                                                                                                                                                                if (imageView10 != null) {
                                                                                                                                                                    i = R.id.v_version;
                                                                                                                                                                    CustomSettingCell customSettingCell5 = (CustomSettingCell) ViewBindings.findChildViewById(view, R.id.v_version);
                                                                                                                                                                    if (customSettingCell5 != null) {
                                                                                                                                                                        i = R.id.v_volume;
                                                                                                                                                                        CustomSettingCell customSettingCell6 = (CustomSettingCell) ViewBindings.findChildViewById(view, R.id.v_volume);
                                                                                                                                                                        if (customSettingCell6 != null) {
                                                                                                                                                                            i = R.id.v_wakeup;
                                                                                                                                                                            CustomSettingCell customSettingCell7 = (CustomSettingCell) ViewBindings.findChildViewById(view, R.id.v_wakeup);
                                                                                                                                                                            if (customSettingCell7 != null) {
                                                                                                                                                                                i = R.id.version_icon;
                                                                                                                                                                                ImageView imageView11 = (ImageView) ViewBindings.findChildViewById(view, R.id.version_icon);
                                                                                                                                                                                if (imageView11 != null) {
                                                                                                                                                                                    i = R.id.view;
                                                                                                                                                                                    View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.view);
                                                                                                                                                                                    if (viewFindChildViewById2 != null) {
                                                                                                                                                                                        i = R.id.view_update_point;
                                                                                                                                                                                        View viewFindChildViewById3 = ViewBindings.findChildViewById(view, R.id.view_update_point);
                                                                                                                                                                                        if (viewFindChildViewById3 != null) {
                                                                                                                                                                                            i = R.id.voice_setting_card_register_lock;
                                                                                                                                                                                            ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.voice_setting_card_register_lock);
                                                                                                                                                                                            if (constraintLayout3 != null) {
                                                                                                                                                                                                i = R.id.voice_setting_card_user_define;
                                                                                                                                                                                                ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.voice_setting_card_user_define);
                                                                                                                                                                                                if (constraintLayout4 != null) {
                                                                                                                                                                                                    i = R.id.voice_setting_card_wakeup_free;
                                                                                                                                                                                                    ConstraintLayout constraintLayout5 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.voice_setting_card_wakeup_free);
                                                                                                                                                                                                    if (constraintLayout5 != null) {
                                                                                                                                                                                                        return new ItemVoiceSettingBinding(customScrollView, constraintLayout, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, seekBar, customScrollView, appTextView, appTextView2, appTextView3, appTextView4, appTextView5, appTextView6, appTextView7, appTextView8, appTextView9, appTextView10, appTextView11, appTextView12, appTextView13, appTextView14, appTextView15, appTextView16, textView, appTextView17, appTextView18, appTextView19, appTextView20, constraintLayout2, customSettingCell, customSettingCell2, customSettingCell3, viewFindChildViewById, customSettingCell4, imageView10, customSettingCell5, customSettingCell6, customSettingCell7, imageView11, viewFindChildViewById2, viewFindChildViewById3, constraintLayout3, constraintLayout4, constraintLayout5);
                                                                                                                                                                                                    }
                                                                                                                                                                                                }
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
