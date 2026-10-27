package com.amazon.mp3.customerprofile.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import com.amazon.mp3.actionbar.view.BauhausActionBarView;
import com.amazon.mp3.activity.NavigationActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.customerprofile.CustomerProfileMetrics;
import com.amazon.mp3.customerprofile.viewModels.EditProfileAttributeViewModel;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.views.library.models.CustomerProfileModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerProfileEditPrivacyFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 Q2\u00020\u0001:\u0001QB\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001f\u001a\u00020 H\u0002JI\u0010!\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010$2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010$2\u0006\u0010'\u001a\u00020$2\u0006\u0010(\u001a\u00020$H\u0002¢\u0006\u0002\u0010)J=\u0010*\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\"2\b\u0010+\u001a\u0004\u0018\u00010$2\b\u0010,\u001a\u0004\u0018\u00010$2\b\u0010-\u001a\u0004\u0018\u00010$2\b\u0010.\u001a\u0004\u0018\u00010$H\u0002¢\u0006\u0002\u0010/J\u0017\u00100\u001a\u0004\u0018\u00010$2\u0006\u00101\u001a\u00020\"H\u0002¢\u0006\u0002\u00102J\b\u00103\u001a\u00020$H\u0002J\b\u00104\u001a\u00020$H\u0002J\u0017\u00105\u001a\u0004\u0018\u00010$2\u0006\u00101\u001a\u00020\"H\u0002¢\u0006\u0002\u00102J\u0017\u00106\u001a\u0004\u0018\u00010$2\u0006\u00101\u001a\u00020\"H\u0002¢\u0006\u0002\u00102J\u0017\u00107\u001a\u0004\u0018\u00010$2\u0006\u00101\u001a\u00020\"H\u0002¢\u0006\u0002\u00102J\u0017\u00108\u001a\u0004\u0018\u00010$2\u0006\u00101\u001a\u00020\"H\u0002¢\u0006\u0002\u00102J\u0010\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\u0019H\u0002J\u0017\u0010<\u001a\u0004\u0018\u00010$2\u0006\u00101\u001a\u00020\"H\u0002¢\u0006\u0002\u00102J\u0017\u0010=\u001a\u0004\u0018\u00010$2\u0006\u00101\u001a\u00020\"H\u0002¢\u0006\u0002\u00102J\b\u0010>\u001a\u00020\u001dH\u0002J\b\u0010?\u001a\u00020\u001dH\u0002J\b\u0010@\u001a\u00020\u001dH\u0002J\u0012\u0010A\u001a\u00020\u001d2\b\u0010B\u001a\u0004\u0018\u00010CH\u0016J\u0012\u0010D\u001a\u00020\u001d2\b\u0010B\u001a\u0004\u0018\u00010CH\u0016J&\u0010E\u001a\u0004\u0018\u00010\"2\u0006\u0010F\u001a\u00020G2\b\u0010H\u001a\u0004\u0018\u00010I2\b\u0010B\u001a\u0004\u0018\u00010CH\u0016J\b\u0010J\u001a\u00020\u001dH\u0016J\b\u0010K\u001a\u00020\u001dH\u0016J \u0010L\u001a\u00020\u001d2\u0006\u00101\u001a\u00020\"2\u0006\u0010'\u001a\u00020$2\u0006\u0010(\u001a\u00020$H\u0002J\b\u0010M\u001a\u00020\u001dH\u0002J\b\u0010N\u001a\u00020\u001dH\u0002J\b\u0010O\u001a\u00020\u001dH\u0002J\u0010\u0010P\u001a\u00020\u001d2\u0006\u0010;\u001a\u00020\u0019H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006R"}, d2 = {"Lcom/amazon/mp3/customerprofile/fragment/CustomerProfileEditPrivacyFragment;", "Landroidx/fragment/app/Fragment;", "()V", "actionBarView", "Lcom/amazon/mp3/actionbar/view/BauhausActionBarView;", "featureGateProvider", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "mCommunityContactsDiscoverySupplementalTextView", "Landroid/widget/TextView;", "mCommunityContactsDiscoveryTextView", "mCommunityListeningActivitySupplementalTextView", "mCommunityListeningActivityTextView", "mCommunityTextView", "mContactsDiscoveryCommunitySetting", "Landroid/widget/ToggleButton;", "mListeningActivityCommunitySetting", "mPrivacyFollowSupplementalTextView", "mPrivacyFollowTextView", "mPrivacyTextView", "mProfileFollowPrivacySetting", "mProfileModel", "Lcom/amazon/music/views/library/models/CustomerProfileModel;", "mStyleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "updatedProfilePage", "", "viewModel", "Lcom/amazon/mp3/customerprofile/viewModels/EditProfileAttributeViewModel;", "applyFontStyle", "", "textView", "styleKey", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "applyMargin", "Landroid/view/View;", "top", "", "left", "right", "width", "height", "(Landroid/view/View;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;II)V", "applyPadding", "leftPadding", "topPadding", "rightPadding", "bottomPadding", "(Landroid/view/View;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getBottomPadding", "view", "(Landroid/view/View;)Ljava/lang/Integer;", "getDefaultMargin", "getDefaultPadding", "getLeftMargin", "getLeftPadding", "getRightMargin", "getRightPadding", "getSelectedPrivacyPreference", "", "isChecked", "getTopMargin", "getTopPadding", "initMargins", "initTextForTextViews", "initTextStyling", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onStart", "onStop", "setMarginsForView", "setupContactsDiscoveryPreference", "setupListeningActivityPreference", "setupProfileStatusPrivacyPreference", "updateModelFollowPrivacy", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerProfileEditPrivacyFragment extends Fragment {
    private static String TAG = CustomerProfileEditPrivacyFragment.class.getSimpleName();
    private BauhausActionBarView actionBarView;
    private FeatureGateProvider featureGateProvider;
    private TextView mCommunityContactsDiscoverySupplementalTextView;
    private TextView mCommunityContactsDiscoveryTextView;
    private TextView mCommunityListeningActivitySupplementalTextView;
    private TextView mCommunityListeningActivityTextView;
    private TextView mCommunityTextView;
    private ToggleButton mContactsDiscoveryCommunitySetting;
    private ToggleButton mListeningActivityCommunitySetting;
    private TextView mPrivacyFollowSupplementalTextView;
    private TextView mPrivacyFollowTextView;
    private TextView mPrivacyTextView;
    private ToggleButton mProfileFollowPrivacySetting;
    private CustomerProfileModel mProfileModel;
    private StyleSheet mStyleSheet;
    private boolean updatedProfilePage;
    private EditProfileAttributeViewModel viewModel;

    private final int getDefaultMargin() {
        return 0;
    }

    private final int getDefaultPadding() {
        return 0;
    }

    private final String getSelectedPrivacyPreference(boolean isChecked) {
        return !isChecked ? "PRIVATE" : "PUBLIC";
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.edit_profile_privacy_preference, container, false);
        this.mProfileFollowPrivacySetting = (ToggleButton) viewInflate.findViewById(R.id.customer_profile_edit_follow_privacy_toggle_button);
        this.mPrivacyTextView = (TextView) viewInflate.findViewById(R.id.customer_profile_edit_privacy_text);
        this.mPrivacyFollowTextView = (TextView) viewInflate.findViewById(R.id.customer_profile_edit_privacy_follow_text);
        this.mPrivacyFollowSupplementalTextView = (TextView) viewInflate.findViewById(R.id.customer_profile_edit_follow_privacy_supplemental_text);
        this.mListeningActivityCommunitySetting = (ToggleButton) viewInflate.findViewById(R.id.customer_profile_edit_listening_activity_community_toggle_button);
        this.mCommunityTextView = (TextView) viewInflate.findViewById(R.id.customer_profile_edit_community_text);
        this.mCommunityListeningActivityTextView = (TextView) viewInflate.findViewById(R.id.customer_profile_edit_community_listening_activity_text);
        this.mCommunityListeningActivitySupplementalTextView = (TextView) viewInflate.findViewById(R.id.customer_profile_edit_listening_activity_community_privacy_supplemental_text);
        this.mContactsDiscoveryCommunitySetting = (ToggleButton) viewInflate.findViewById(R.id.customer_profile_edit_contacts_discovery_community_toggle_button);
        this.mCommunityContactsDiscoveryTextView = (TextView) viewInflate.findViewById(R.id.customer_profile_edit_community_contacts_discovery_text);
        this.mCommunityContactsDiscoverySupplementalTextView = (TextView) viewInflate.findViewById(R.id.customer_profile_edit_contacts_discovery_community_supplemental_text);
        FeatureGateProvider featureGateProvider = this.featureGateProvider;
        this.updatedProfilePage = featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.updated_profile_edit_page);
        this.mStyleSheet = StyleSheetProvider.getStyleSheet().getValue();
        initTextForTextViews();
        initTextStyling();
        initMargins();
        setupProfileStatusPrivacyPreference();
        if (this.updatedProfilePage) {
            ToggleButton toggleButton = this.mListeningActivityCommunitySetting;
            if (toggleButton != null) {
                toggleButton.setVisibility(0);
            }
            ToggleButton toggleButton2 = this.mContactsDiscoveryCommunitySetting;
            if (toggleButton2 != null) {
                toggleButton2.setVisibility(0);
            }
            setupListeningActivityPreference();
            setupContactsDiscoveryPreference();
        } else {
            ToggleButton toggleButton3 = this.mListeningActivityCommunitySetting;
            if (toggleButton3 != null) {
                toggleButton3.setVisibility(8);
            }
            ToggleButton toggleButton4 = this.mContactsDiscoveryCommunitySetting;
            if (toggleButton4 != null) {
                toggleButton4.setVisibility(8);
            }
        }
        ToggleButton toggleButton5 = this.mProfileFollowPrivacySetting;
        if (toggleButton5 != null) {
            toggleButton5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.amazon.mp3.customerprofile.fragment.CustomerProfileEditPrivacyFragment$$ExternalSyntheticLambda0
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    CustomerProfileEditPrivacyFragment.m793onCreateView$lambda1(this.f$0, compoundButton, z);
                }
            });
        }
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-1, reason: not valid java name */
    public static final void m793onCreateView$lambda1(CustomerProfileEditPrivacyFragment this$0, CompoundButton compoundButton, boolean z) {
        EditProfileAttributeViewModel editProfileAttributeViewModel;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CustomerProfileMetrics.emitUiClickMetric(z ? ActionType.PROFILE_PRIVACY_TOGGLE_ON : ActionType.PROFILE_PRIVACY_TOGGLE_OFF);
        CustomerProfileEditPrivacyFragment$onCreateView$1$callback$1 customerProfileEditPrivacyFragment$onCreateView$1$callback$1 = new CustomerProfileEditPrivacyFragment$onCreateView$1$callback$1(this$0, z);
        String selectedPrivacyPreference = this$0.getSelectedPrivacyPreference(z);
        CustomerProfileModel customerProfileModel = this$0.mProfileModel;
        int profileAvatarRotation = customerProfileModel == null ? 0 : customerProfileModel.getProfileAvatarRotation();
        Context context = this$0.getContext();
        if (context == null || (editProfileAttributeViewModel = this$0.viewModel) == null) {
            return;
        }
        editProfileAttributeViewModel.updateUserAttributeViaAsyncTask(context, null, null, null, selectedPrivacyPreference, null, customerProfileEditPrivacyFragment$onCreateView$1$callback$1, profileAvatarRotation);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EditProfileAttributeViewModel editProfileAttributeViewModel = (EditProfileAttributeViewModel) new ViewModelProvider(this).get(EditProfileAttributeViewModel.class);
        this.viewModel = editProfileAttributeViewModel;
        if (editProfileAttributeViewModel == null) {
            return;
        }
        Bundle arguments = getArguments();
        Serializable serializable = arguments == null ? null : arguments.getSerializable("CustomerProfileModel");
        if (serializable == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.CustomerProfileModel");
        }
        CustomerProfileModel customerProfileModel = (CustomerProfileModel) serializable;
        this.mProfileModel = customerProfileModel;
        editProfileAttributeViewModel.setCustomerProfileModel(customerProfileModel);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        BauhausActionBarView bauhausActionBarView = this.actionBarView;
        if (bauhausActionBarView == null) {
            return;
        }
        bauhausActionBarView.updateActionBarOverlayState(false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        BauhausActionBarView bauhausActionBarView = this.actionBarView;
        if (bauhausActionBarView == null) {
            return;
        }
        bauhausActionBarView.updateActionBarOverlayState(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = getActivity();
        NavigationActivity navigationActivity = activity instanceof NavigationActivity ? (NavigationActivity) activity : null;
        if (navigationActivity == null) {
            return;
        }
        Context context = getContext();
        BauhausActionBarView bauhausActionBarView = new BauhausActionBarView(navigationActivity, context != null ? context.getString(R.string.dmusic_amazon_music_editprofile_subpage_settings_header) : null);
        this.actionBarView = bauhausActionBarView;
        navigationActivity.setHeaderView(bauhausActionBarView);
        navigationActivity.requestHomeButtonAsBack();
        BauhausActionBarView bauhausActionBarView2 = this.actionBarView;
        if (bauhausActionBarView2 == null) {
            return;
        }
        bauhausActionBarView2.updateActionBarOverlayState(true);
    }

    private final void setupProfileStatusPrivacyPreference() {
        ToggleButton toggleButton = this.mProfileFollowPrivacySetting;
        if (toggleButton == null) {
            return;
        }
        CustomerProfileModel customerProfileModel = this.mProfileModel;
        toggleButton.setChecked(Intrinsics.areEqual(customerProfileModel == null ? null : customerProfileModel.getProfileStatus(), "PUBLIC"));
    }

    private final void setupListeningActivityPreference() {
        CustomerProfileModel customerProfileModel = this.mProfileModel;
        if (!TextUtils.isEmpty(customerProfileModel == null ? null : customerProfileModel.getShareListeningHistory())) {
            ToggleButton toggleButton = this.mListeningActivityCommunitySetting;
            if (toggleButton == null) {
                return;
            }
            CustomerProfileModel customerProfileModel2 = this.mProfileModel;
            toggleButton.setChecked(Intrinsics.areEqual(customerProfileModel2 != null ? customerProfileModel2.getShareListeningHistory() : null, "ALLOW"));
            return;
        }
        ToggleButton toggleButton2 = this.mListeningActivityCommunitySetting;
        if (toggleButton2 == null) {
            return;
        }
        CustomerProfileModel customerProfileModel3 = this.mProfileModel;
        toggleButton2.setChecked(Intrinsics.areEqual(customerProfileModel3 != null ? customerProfileModel3.getProfileStatus() : null, "PUBLIC"));
    }

    private final void setupContactsDiscoveryPreference() {
        ToggleButton toggleButton = this.mContactsDiscoveryCommunitySetting;
        if (toggleButton == null) {
            return;
        }
        toggleButton.setChecked(true);
    }

    private final void initTextForTextViews() {
        TextView textView = this.mPrivacyTextView;
        if (textView != null) {
            Context context = getContext();
            textView.setText(context == null ? null : context.getString(R.string.dmusic_amazon_music_editprofile_privacy));
        }
        TextView textView2 = this.mPrivacyFollowTextView;
        if (textView2 != null) {
            Context context2 = getContext();
            textView2.setText(context2 == null ? null : context2.getString(R.string.dmusic_amazon_music_editprofile_publicprofile));
        }
        TextView textView3 = this.mPrivacyFollowSupplementalTextView;
        if (textView3 != null) {
            Context context3 = getContext();
            textView3.setText(context3 != null ? context3.getString(R.string.dmusic_amazon_music_editprofile_publicprofile_text) : null);
        }
        if (this.updatedProfilePage) {
            TextView textView4 = this.mCommunityTextView;
            if (textView4 != null) {
                textView4.setText("Community");
            }
            TextView textView5 = this.mCommunityListeningActivityTextView;
            if (textView5 != null) {
                textView5.setText("Listening Activity");
            }
            TextView textView6 = this.mCommunityListeningActivitySupplementalTextView;
            if (textView6 != null) {
                textView6.setText("Let people see what you've been listening to");
            }
            TextView textView7 = this.mCommunityContactsDiscoveryTextView;
            if (textView7 != null) {
                textView7.setText("Discoverable by contacts");
            }
            TextView textView8 = this.mCommunityContactsDiscoverySupplementalTextView;
            if (textView8 == null) {
                return;
            }
            textView8.setText("Let your phone contacts discover and follow your Amazon Music profile through your phone number.");
        }
    }

    private final void initTextStyling() {
        TextView textView = this.mPrivacyTextView;
        FontStyleKey fontStyleKey = FontStyleKey.INDEX_PRIMARY;
        applyFontStyle(textView, fontStyleKey);
        TextView textView2 = this.mPrivacyFollowTextView;
        FontStyleKey fontStyleKey2 = FontStyleKey.PRIMARY;
        applyFontStyle(textView2, fontStyleKey2);
        TextView textView3 = this.mPrivacyFollowSupplementalTextView;
        FontStyleKey fontStyleKey3 = FontStyleKey.SECONDARY;
        applyFontStyle(textView3, fontStyleKey3);
        if (this.updatedProfilePage) {
            applyFontStyle(this.mCommunityTextView, fontStyleKey);
            applyFontStyle(this.mCommunityListeningActivityTextView, fontStyleKey2);
            applyFontStyle(this.mCommunityListeningActivitySupplementalTextView, fontStyleKey3);
            applyFontStyle(this.mCommunityContactsDiscoveryTextView, fontStyleKey2);
            applyFontStyle(this.mCommunityContactsDiscoverySupplementalTextView, fontStyleKey3);
        }
    }

    private final void applyFontStyle(TextView textView, FontStyleKey styleKey) {
        StyleSheet styleSheet = this.mStyleSheet;
        FontStyle fontStyle = styleSheet == null ? null : styleSheet.getFontStyle(styleKey);
        if (textView == null || fontStyle == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(textView, fontStyle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateModelFollowPrivacy(boolean isChecked) {
        EditProfileAttributeViewModel editProfileAttributeViewModel = this.viewModel;
        if (editProfileAttributeViewModel == null) {
            return;
        }
        editProfileAttributeViewModel.updateModelFollowPrivacy(isChecked);
    }

    private final void initMargins() {
        Integer spacerInPixels;
        Integer spacerInPixels2;
        TextView textView = this.mPrivacyTextView;
        if (textView != null) {
            setMarginsForView(textView, -2, -2);
        }
        TextView textView2 = this.mPrivacyFollowTextView;
        if (textView2 != null) {
            setMarginsForView(textView2, -1, -2);
        }
        TextView textView3 = this.mPrivacyFollowSupplementalTextView;
        if (textView3 != null) {
            setMarginsForView(textView3, -2, -2);
        }
        StyleSheet styleSheet = this.mStyleSheet;
        Integer numValueOf = null;
        Integer spacerInPixels3 = styleSheet == null ? null : styleSheet.getSpacerInPixels(SpacerKey.MINI);
        int dimension = spacerInPixels3 == null ? (int) getResources().getDimension(R.dimen.bauhaus_spacer_mini) : spacerInPixels3.intValue();
        StyleSheet styleSheet2 = this.mStyleSheet;
        Integer numValueOf2 = (styleSheet2 == null || (spacerInPixels = styleSheet2.getSpacerInPixels(SpacerKey.HUGE)) == null) ? null : Integer.valueOf(spacerInPixels.intValue() + dimension);
        int dimension2 = numValueOf2 == null ? (int) getResources().getDimension(R.dimen.bauhaus_spacer_giant) : numValueOf2.intValue();
        StyleSheet styleSheet3 = this.mStyleSheet;
        if (styleSheet3 != null && (spacerInPixels2 = styleSheet3.getSpacerInPixels(SpacerKey.HUGE)) != null) {
            numValueOf = Integer.valueOf(spacerInPixels2.intValue() + dimension);
        }
        int dimension3 = numValueOf == null ? (int) getResources().getDimension(R.dimen.bauhaus_spacer_giant) : numValueOf.intValue();
        ToggleButton toggleButton = this.mProfileFollowPrivacySetting;
        if (toggleButton != null) {
            setMarginsForView(toggleButton, dimension2, dimension3);
        }
        if (this.updatedProfilePage) {
            TextView textView4 = this.mCommunityTextView;
            if (textView4 != null) {
                setMarginsForView(textView4, -2, -2);
            }
            TextView textView5 = this.mCommunityListeningActivityTextView;
            if (textView5 != null) {
                setMarginsForView(textView5, -1, -2);
            }
            TextView textView6 = this.mCommunityListeningActivitySupplementalTextView;
            if (textView6 != null) {
                setMarginsForView(textView6, -2, -2);
            }
            ToggleButton toggleButton2 = this.mListeningActivityCommunitySetting;
            if (toggleButton2 != null) {
                setMarginsForView(toggleButton2, dimension2, dimension3);
            }
            TextView textView7 = this.mCommunityContactsDiscoveryTextView;
            if (textView7 != null) {
                setMarginsForView(textView7, -1, -2);
            }
            TextView textView8 = this.mCommunityContactsDiscoverySupplementalTextView;
            if (textView8 != null) {
                setMarginsForView(textView8, -2, -2);
            }
            ToggleButton toggleButton3 = this.mContactsDiscoveryCommunitySetting;
            if (toggleButton3 == null) {
                return;
            }
            setMarginsForView(toggleButton3, dimension2, dimension3);
        }
    }

    private final void setMarginsForView(View view, int width, int height) {
        applyMargin(view, getTopMargin(view), getLeftMargin(view), getRightMargin(view), width, height);
        applyPadding(view, getLeftPadding(view), getTopPadding(view), getRightPadding(view), getBottomPadding(view));
    }

    private final Integer getTopMargin(View view) {
        int iIntValue;
        Integer spacerInPixels;
        if (Intrinsics.areEqual(view, this.mPrivacyTextView) ? true : Intrinsics.areEqual(view, this.mCommunityTextView)) {
            StyleSheet styleSheet = this.mStyleSheet;
            Integer spacerInPixels2 = styleSheet == null ? null : styleSheet.getSpacerInPixels(SpacerKey.EPIC);
            if (spacerInPixels2 == null) {
                Resources resources = getResources();
                iIntValue = (int) (resources == null ? null : Float.valueOf(resources.getDimension(R.dimen.bauhaus_spacer_epic))).floatValue();
            } else {
                iIntValue = spacerInPixels2.intValue();
            }
            StyleSheet styleSheet2 = this.mStyleSheet;
            if (styleSheet2 == null || (spacerInPixels = styleSheet2.getSpacerInPixels(SpacerKey.GIANT)) == null) {
                return null;
            }
            return Integer.valueOf(spacerInPixels.intValue() + iIntValue);
        }
        if (Intrinsics.areEqual(view, this.mPrivacyFollowTextView) ? true : Intrinsics.areEqual(view, this.mCommunityListeningActivityTextView) ? true : Intrinsics.areEqual(view, this.mCommunityContactsDiscoveryTextView)) {
            StyleSheet styleSheet3 = this.mStyleSheet;
            if (styleSheet3 == null) {
                return null;
            }
            return styleSheet3.getSpacerInPixels(SpacerKey.BASE);
        }
        if (Intrinsics.areEqual(view, this.mProfileFollowPrivacySetting) ? true : Intrinsics.areEqual(view, this.mListeningActivityCommunitySetting) ? true : Intrinsics.areEqual(view, this.mContactsDiscoveryCommunitySetting)) {
            StyleSheet styleSheet4 = this.mStyleSheet;
            if (styleSheet4 == null) {
                return null;
            }
            return styleSheet4.getSpacerInPixels(SpacerKey.MINI);
        }
        if (!(Intrinsics.areEqual(view, this.mPrivacyFollowSupplementalTextView) ? true : Intrinsics.areEqual(view, this.mCommunityListeningActivitySupplementalTextView) ? true : Intrinsics.areEqual(view, this.mCommunityContactsDiscoverySupplementalTextView))) {
            return Integer.valueOf(getDefaultMargin());
        }
        StyleSheet styleSheet5 = this.mStyleSheet;
        if (styleSheet5 == null) {
            return null;
        }
        return styleSheet5.getSpacerInPixels(SpacerKey.MICRO);
    }

    private final Integer getLeftMargin(View view) {
        if (Intrinsics.areEqual(view, this.mPrivacyFollowTextView) ? true : Intrinsics.areEqual(view, this.mPrivacyFollowSupplementalTextView) ? true : Intrinsics.areEqual(view, this.mCommunityListeningActivityTextView) ? true : Intrinsics.areEqual(view, this.mCommunityListeningActivitySupplementalTextView) ? true : Intrinsics.areEqual(view, this.mCommunityContactsDiscoveryTextView) ? true : Intrinsics.areEqual(view, this.mCommunityContactsDiscoverySupplementalTextView)) {
            StyleSheet styleSheet = this.mStyleSheet;
            if (styleSheet == null) {
                return null;
            }
            return styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        }
        if (!(Intrinsics.areEqual(view, this.mProfileFollowPrivacySetting) ? true : Intrinsics.areEqual(view, this.mListeningActivityCommunitySetting) ? true : Intrinsics.areEqual(view, this.mContactsDiscoveryCommunitySetting))) {
            return Integer.valueOf(getDefaultMargin());
        }
        StyleSheet styleSheet2 = this.mStyleSheet;
        if (styleSheet2 == null) {
            return null;
        }
        return styleSheet2.getSpacerInPixels(SpacerKey.SMALL);
    }

    private final Integer getRightMargin(View view) {
        Integer spacerInPixels;
        Integer spacerInPixels2;
        if (!(Intrinsics.areEqual(view, this.mPrivacyFollowTextView) ? true : Intrinsics.areEqual(view, this.mCommunityListeningActivityTextView) ? true : Intrinsics.areEqual(view, this.mCommunityContactsDiscoveryTextView))) {
            if (!(Intrinsics.areEqual(view, this.mProfileFollowPrivacySetting) ? true : Intrinsics.areEqual(view, this.mListeningActivityCommunitySetting) ? true : Intrinsics.areEqual(view, this.mContactsDiscoveryCommunitySetting))) {
                return Integer.valueOf(getDefaultMargin());
            }
            StyleSheet styleSheet = this.mStyleSheet;
            if (styleSheet == null) {
                return null;
            }
            return styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        }
        StyleSheet styleSheet2 = this.mStyleSheet;
        int iIntValue = 0;
        if (styleSheet2 != null && (spacerInPixels2 = styleSheet2.getSpacerInPixels(SpacerKey.BASE)) != null) {
            iIntValue = spacerInPixels2.intValue();
        }
        StyleSheet styleSheet3 = this.mStyleSheet;
        if (styleSheet3 == null || (spacerInPixels = styleSheet3.getSpacerInPixels(SpacerKey.EPIC)) == null) {
            return null;
        }
        return Integer.valueOf(spacerInPixels.intValue() + iIntValue);
    }

    private final Integer getLeftPadding(View view) {
        if (Intrinsics.areEqual(view, this.mPrivacyTextView) ? true : Intrinsics.areEqual(view, this.mCommunityTextView)) {
            StyleSheet styleSheet = this.mStyleSheet;
            if (styleSheet == null) {
                return null;
            }
            return styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        }
        if (!(Intrinsics.areEqual(view, this.mProfileFollowPrivacySetting) ? true : Intrinsics.areEqual(view, this.mListeningActivityCommunitySetting) ? true : Intrinsics.areEqual(view, this.mContactsDiscoveryCommunitySetting))) {
            return Integer.valueOf(getDefaultPadding());
        }
        StyleSheet styleSheet2 = this.mStyleSheet;
        if (styleSheet2 == null) {
            return null;
        }
        return styleSheet2.getSpacerInPixels(SpacerKey.MINI);
    }

    private final Integer getRightPadding(View view) {
        if (Intrinsics.areEqual(view, this.mPrivacyTextView) ? true : Intrinsics.areEqual(view, this.mCommunityTextView)) {
            StyleSheet styleSheet = this.mStyleSheet;
            if (styleSheet == null) {
                return null;
            }
            return styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        }
        if (!(Intrinsics.areEqual(view, this.mProfileFollowPrivacySetting) ? true : Intrinsics.areEqual(view, this.mListeningActivityCommunitySetting) ? true : Intrinsics.areEqual(view, this.mContactsDiscoveryCommunitySetting))) {
            return Integer.valueOf(getDefaultPadding());
        }
        StyleSheet styleSheet2 = this.mStyleSheet;
        if (styleSheet2 == null) {
            return null;
        }
        return styleSheet2.getSpacerInPixels(SpacerKey.MINI);
    }

    private final Integer getTopPadding(View view) {
        if (!(Intrinsics.areEqual(view, this.mProfileFollowPrivacySetting) ? true : Intrinsics.areEqual(view, this.mListeningActivityCommunitySetting) ? true : Intrinsics.areEqual(view, this.mContactsDiscoveryCommunitySetting))) {
            return Integer.valueOf(getDefaultPadding());
        }
        StyleSheet styleSheet = this.mStyleSheet;
        if (styleSheet == null) {
            return null;
        }
        return styleSheet.getSpacerInPixels(SpacerKey.MINI);
    }

    private final Integer getBottomPadding(View view) {
        if (!(Intrinsics.areEqual(view, this.mProfileFollowPrivacySetting) ? true : Intrinsics.areEqual(view, this.mListeningActivityCommunitySetting) ? true : Intrinsics.areEqual(view, this.mContactsDiscoveryCommunitySetting))) {
            return Integer.valueOf(getDefaultPadding());
        }
        StyleSheet styleSheet = this.mStyleSheet;
        if (styleSheet == null) {
            return null;
        }
        return styleSheet.getSpacerInPixels(SpacerKey.MINI);
    }

    private final void applyMargin(View textView, Integer top, Integer left, Integer right, int width, int height) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(textView, width, height, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : null);
    }

    private final void applyPadding(View textView, Integer leftPadding, Integer topPadding, Integer rightPadding, Integer bottomPadding) {
        textView.setPadding(leftPadding == null ? 0 : leftPadding.intValue(), topPadding == null ? 0 : topPadding.intValue(), rightPadding == null ? 0 : rightPadding.intValue(), bottomPadding != null ? bottomPadding.intValue() : 0);
    }
}
