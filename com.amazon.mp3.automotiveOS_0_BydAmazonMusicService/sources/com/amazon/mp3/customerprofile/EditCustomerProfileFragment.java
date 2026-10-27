package com.amazon.mp3.customerprofile;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.BauhausActionBarView;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.NavigationActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.customerprofile.CustomerProfileService;
import com.amazon.mp3.customerprofile.viewModels.EditProfileViewModel;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.mylibrary.LibraryPreferences;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.views.library.binders.ImageBinder;
import com.amazon.music.views.library.models.CompactHeaderModel;
import com.amazon.music.views.library.models.CustomerProfileModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.ImageSize;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.amazon.ui.foundations.views.BaseImage;
import com.squareup.picasso.Picasso;
import dagger.android.support.AndroidSupportInjection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Subscription;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public class EditCustomerProfileFragment extends Fragment {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) EditCustomerProfileFragment.class);
    private final String TAG;
    private BauhausActionBarView actionBarView;
    protected AuthenticationProvider authenticationProvider;
    private TextView avatarUpdateButtonView;
    private BaseImage avatarView;
    private Context context;
    private View dialogView;
    private EditProfileViewModel.EditProfileCallback dismissCallback;
    private BaseButton editHandleButton;
    private View editHandleContentView;
    private TextView editHandleView;
    private BaseButton editNameButton;
    private View editNameContentView;
    private TextView editNameView;
    private BaseButton editPrivacyButton;
    private View editPrivacyContentView;
    private TextView editPrivacyView;
    protected FeatureGateProvider featureGateProvider;
    private TextView handleTitleView;
    private View handleViewSeparator;
    private ImageBinder imageBinder;
    private Subscription imageRotationSubscription;
    public CustomerProfileModel mProfileModel;
    protected MetricsProvider metricsProvider;
    private TextView nameTitleView;
    private TextView privacyTitleView;
    private Uri selectedAvatarUri;
    private Boolean showHandle;
    private StyleSheet styleSheet;
    private UpdateProfileAsyncTask updateProfileAsyncTask;
    private EditProfileViewModel viewModel;

    public EditCustomerProfileFragment() {
        this.TAG = getClass().getSimpleName();
        this.context = null;
        this.selectedAvatarUri = null;
        this.imageBinder = null;
        this.dialogView = null;
        this.avatarView = null;
        this.avatarUpdateButtonView = null;
        this.nameTitleView = null;
        this.editNameContentView = null;
        this.editNameView = null;
        this.editNameButton = null;
        this.handleTitleView = null;
        this.editHandleContentView = null;
        this.editHandleView = null;
        this.editHandleButton = null;
        this.handleViewSeparator = null;
        this.privacyTitleView = null;
        this.editPrivacyContentView = null;
        this.editPrivacyView = null;
        this.editPrivacyButton = null;
        this.updateProfileAsyncTask = null;
        this.imageRotationSubscription = null;
        this.actionBarView = null;
    }

    public EditCustomerProfileFragment(final EditProfileViewModel viewModel) {
        String simpleName = getClass().getSimpleName();
        this.TAG = simpleName;
        this.context = null;
        this.selectedAvatarUri = null;
        this.imageBinder = null;
        this.dialogView = null;
        this.avatarView = null;
        this.avatarUpdateButtonView = null;
        this.nameTitleView = null;
        this.editNameContentView = null;
        this.editNameView = null;
        this.editNameButton = null;
        this.handleTitleView = null;
        this.editHandleContentView = null;
        this.editHandleView = null;
        this.editHandleButton = null;
        this.handleViewSeparator = null;
        this.privacyTitleView = null;
        this.editPrivacyContentView = null;
        this.editPrivacyView = null;
        this.editPrivacyButton = null;
        this.updateProfileAsyncTask = null;
        this.imageRotationSubscription = null;
        this.actionBarView = null;
        if (viewModel.getCustomerProfileModel() != null) {
            this.mProfileModel = viewModel.getCustomerProfileModel();
        } else {
            Log.error(simpleName, "CustomerProfileModel is null when creating the fragment");
        }
        if (viewModel.getImageBinder() != null) {
            this.imageBinder = viewModel.getImageBinder();
        }
        if (viewModel.getCallbackHandler() != null) {
            this.dismissCallback = viewModel.getCallbackHandler();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
        this.styleSheet = StyleSheetProvider.getStyleSheet().getValue();
        this.showHandle = Boolean.valueOf(Feature.social_profiles_phase3_enabled.isEnabled());
        EditProfileViewModel editProfileViewModel = (EditProfileViewModel) ViewModelProviders.of(getActivity()).get(EditProfileViewModel.class);
        this.viewModel = editProfileViewModel;
        if (editProfileViewModel != null) {
            CustomerProfileModel customerProfileModel = this.mProfileModel;
            if (customerProfileModel != null) {
                editProfileViewModel.setCustomerProfileModel(customerProfileModel);
            } else if (editProfileViewModel.getCustomerProfileModel() != null) {
                this.mProfileModel = this.viewModel.getCustomerProfileModel();
            }
            ImageBinder imageBinder = this.imageBinder;
            if (imageBinder != null) {
                this.viewModel.setImageBinder(imageBinder);
            } else if (this.viewModel.getImageBinder() != null) {
                this.imageBinder = this.viewModel.getImageBinder();
            }
            EditProfileViewModel.EditProfileCallback editProfileCallback = this.dismissCallback;
            if (editProfileCallback != null) {
                this.viewModel.setCallbackHandler(editProfileCallback);
            } else if (this.viewModel.getCallbackHandler() != null) {
                this.dismissCallback = this.viewModel.getCallbackHandler();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View viewInflate = inflater.inflate(R.layout.customer_profile_edit_layout, container, false);
        this.dialogView = viewInflate;
        this.avatarView = (BaseImage) viewInflate.findViewById(R.id.edit_profile_avatar);
        this.avatarUpdateButtonView = (TextView) this.dialogView.findViewById(R.id.edit_profile_avatar_icon);
        this.nameTitleView = (TextView) this.dialogView.findViewById(R.id.edit_profile_name_text);
        this.handleTitleView = (TextView) this.dialogView.findViewById(R.id.edit_profile_handle_text);
        this.privacyTitleView = (TextView) this.dialogView.findViewById(R.id.edit_profile_privacy_setting_main_text);
        this.editNameContentView = this.dialogView.findViewById(R.id.edit_profile_name_content);
        this.editNameView = (TextView) this.dialogView.findViewById(R.id.edit_profile_name);
        this.editNameButton = (BaseButton) this.dialogView.findViewById(R.id.edit_name_text_icon);
        this.editHandleContentView = this.dialogView.findViewById(R.id.edit_profile_handle_content);
        this.editHandleView = (TextView) this.dialogView.findViewById(R.id.edit_profile_handle);
        this.editHandleButton = (BaseButton) this.dialogView.findViewById(R.id.edit_handle_text_icon);
        this.handleViewSeparator = this.dialogView.findViewById(R.id.edit_profile_handle_separator);
        this.editPrivacyContentView = this.dialogView.findViewById(R.id.edit_profile_privacy_content);
        this.editPrivacyView = (TextView) this.dialogView.findViewById(R.id.edit_profile_privacy_setting_option_text);
        this.editPrivacyButton = (BaseButton) this.dialogView.findViewById(R.id.edit_privacy_icon);
        if (!this.showHandle.booleanValue()) {
            this.handleTitleView.setVisibility(8);
            this.editHandleContentView.setVisibility(8);
            this.editHandleView.setVisibility(8);
            this.editHandleButton.setVisibility(8);
            this.handleViewSeparator.setVisibility(8);
        }
        initTextForTextViews();
        initTextStyling();
        initMargins();
        configureBaseButtons(this.editNameButton, getResources().getDrawable(R.drawable.ic_action_edit));
        configureBaseButtons(this.editPrivacyButton, getResources().getDrawable(R.drawable.ic_navigation_goforward));
        if (this.showHandle.booleanValue()) {
            configureBaseButtons(this.editHandleButton, getResources().getDrawable(R.drawable.ic_action_edit));
        }
        BaseImage.StyleBuilder styleBuilder = new BaseImage.StyleBuilder();
        styleBuilder.withSize(ImageSize.INSTANCE.getRoundImageSize((int) this.context.getResources().getDimension(R.dimen.edit_profile_avatar_size)));
        styleBuilder.withScaleType(ImageView.ScaleType.FIT_CENTER);
        styleBuilder.applyStyle(this.avatarView);
        if (this.mProfileModel != null) {
            this.avatarView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.amazon.mp3.customerprofile.EditCustomerProfileFragment.1
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    EditCustomerProfileFragment.this.avatarView.getViewTreeObserver().removeOnPreDrawListener(this);
                    EditCustomerProfileFragment editCustomerProfileFragment = EditCustomerProfileFragment.this;
                    editCustomerProfileFragment.setSelectedProfileAvatarUrl(editCustomerProfileFragment.mProfileModel.getProfileAvatarUrl());
                    return true;
                }
            });
            setSelectedProfileName(this.mProfileModel.getProfileName());
            if (this.showHandle.booleanValue()) {
                setSelectedProfileHandle(this.mProfileModel.getProfileHandle());
            }
        }
        this.editNameButton.setOnClickListener(routeToEditNameScreen());
        this.editNameContentView.setOnClickListener(routeToEditNameScreen());
        this.editPrivacyButton.setOnClickListener(routeToEditPrivacyScreen());
        this.editPrivacyContentView.setOnClickListener(routeToEditPrivacyScreen());
        if (this.showHandle.booleanValue()) {
            this.editHandleContentView.setOnClickListener(routeToEditHandleScreen());
            this.editHandleButton.setOnClickListener(routeToEditHandleScreen());
        }
        if (AmazonApplication.getCapabilities().isCustomerProfilePictureUploadSupported()) {
            this.avatarUpdateButtonView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.customerprofile.EditCustomerProfileFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(final View v) {
                    CustomerProfileMetrics.emitUiClickMetric(ActionType.CHANGE_PROFILE_PIC);
                    if (AmazonApplication.getCapabilities().isCustomerProfilePictureUploadSupported()) {
                        Intent intent = new Intent("android.intent.action.GET_CONTENT");
                        intent.setType("image/*");
                        intent.addCategory("android.intent.category.OPENABLE");
                        EditCustomerProfileFragment.this.startActivityForResult(intent, 10101);
                    }
                }
            });
        }
        CustomerProfileMetrics.emitProfileStatsViewMetric(PageType.CUSTOMER_PROFILE_EDIT);
        return this.dialogView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        Uri data2;
        LOG.info("onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 10101 || data == null || (data2 = data.getData()) == null) {
            return;
        }
        changeAvatar(data2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof NavigationActivity) {
            NavigationActivity navigationActivity = (NavigationActivity) getActivity();
            BauhausActionBarView bauhausActionBarView = new BauhausActionBarView(navigationActivity, requireContext().getString(R.string.dmusic_amazon_music_editprofile_page));
            this.actionBarView = bauhausActionBarView;
            navigationActivity.setHeaderView(bauhausActionBarView);
            navigationActivity.requestHomeButtonAsBack();
            this.actionBarView.updateActionBarOverlayState(true);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        UpdateProfileAsyncTask updateProfileAsyncTask = this.updateProfileAsyncTask;
        if (updateProfileAsyncTask != null) {
            updateProfileAsyncTask.clear();
        }
        Subscription subscription = this.imageRotationSubscription;
        if (subscription == null || subscription.isUnsubscribed()) {
            return;
        }
        this.imageRotationSubscription.unsubscribe();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.actionBarView.updateActionBarOverlayState(false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.actionBarView.updateActionBarOverlayState(true);
    }

    private void changeAvatar(final Uri avatarUri) {
        this.selectedAvatarUri = avatarUri;
        this.avatarView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.amazon.mp3.customerprofile.EditCustomerProfileFragment.3
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                EditCustomerProfileFragment.this.avatarView.getViewTreeObserver().removeOnPreDrawListener(this);
                EditCustomerProfileFragment.this.setSelectedProfileAvatarUrl(avatarUri.toString());
                return true;
            }
        });
        final CustomerProfileService.Callback callback = new CustomerProfileService.Callback() { // from class: com.amazon.mp3.customerprofile.EditCustomerProfileFragment.4
            @Override // com.amazon.mp3.customerprofile.CustomerProfileService.Callback
            public void onSuccess() {
                if (EditCustomerProfileFragment.this.getActivity() == null || EditCustomerProfileFragment.this.getActivity().isFinishing()) {
                    return;
                }
                EditCustomerProfileFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.amazon.mp3.customerprofile.EditCustomerProfileFragment.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        EditCustomerProfileFragment.this.viewModel.updateProfileModelState(avatarUri.toString());
                        AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                        EditCustomerProfileFragment editCustomerProfileFragment = EditCustomerProfileFragment.this;
                        editCustomerProfileFragment.saveToLocal(avatarUri, editCustomerProfileFragment.mProfileModel.getProfileName());
                        EditCustomerProfileFragment.this.closeAfterSave();
                    }
                });
            }

            @Override // com.amazon.mp3.customerprofile.CustomerProfileService.Callback
            public void onError(final CustomerProfileService.UpdateException exception) {
                int code = exception.getCode();
                EditCustomerProfileFragment.LOG.debug("exception code:" + code);
            }
        };
        String string = avatarUri != null ? avatarUri.toString() : null;
        Subscription subscription = this.imageRotationSubscription;
        if (subscription != null && !subscription.isUnsubscribed()) {
            this.imageRotationSubscription.unsubscribe();
        }
        this.imageRotationSubscription = CustomerProfileDetailManager.getExifRotation(string, this.context).subscribe(new Action1() { // from class: com.amazon.mp3.customerprofile.EditCustomerProfileFragment$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$changeAvatar$0(avatarUri, callback, (Integer) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$changeAvatar$0(Uri uri, CustomerProfileService.Callback callback, Integer num) {
        CustomerProfileModel customerProfileModel = this.mProfileModel;
        if (customerProfileModel != null) {
            customerProfileModel.setProfileAvatarRotation(num.intValue());
        } else {
            Log.error(this.TAG, "mProfileModel is null when saving profile");
        }
        UpdateProfileAsyncTask updateProfileAsyncTask = new UpdateProfileAsyncTask(this.context, uri, null, null, null, null, callback, num.intValue());
        this.updateProfileAsyncTask = updateProfileAsyncTask;
        updateProfileAsyncTask.execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelectedProfileAvatarUrl(final String avatarUrl) {
        Subscription subscription = this.imageRotationSubscription;
        if (subscription != null && !subscription.isUnsubscribed()) {
            this.imageRotationSubscription.unsubscribe();
        }
        this.imageRotationSubscription = CustomerProfileDetailManager.getExifRotation(avatarUrl, this.context).subscribe(new Action1() { // from class: com.amazon.mp3.customerprofile.EditCustomerProfileFragment$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$setSelectedProfileAvatarUrl$1(avatarUrl, (Integer) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setSelectedProfileAvatarUrl$1(String str, Integer num) {
        Picasso.get().load(str).resize(this.avatarView.getMeasuredWidth(), this.avatarView.getMeasuredHeight()).centerCrop(48).rotate(num.intValue()).into(this.avatarView.getImage());
    }

    private void setSelectedProfileName(final String profileName) {
        this.editNameView.setText(profileName, TextView.BufferType.NORMAL);
    }

    private void setSelectedProfileHandle(final String profileHandle) {
        if (!profileHandle.isEmpty()) {
            this.editHandleView.setText("@".concat(profileHandle), TextView.BufferType.NORMAL);
        } else {
            this.editHandleView.setText(getResources().getString(R.string.dmusic_amazon_music_handle));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveToLocal(final Uri profileAvatarUri, final String profileName) {
        LibraryPreferences libraryPreferences = LibraryPreferences.INSTANCE;
        CompactHeaderModel profileModel = libraryPreferences.readProfileModel(getContext());
        if (profileModel != null) {
            libraryPreferences.writeProfileModel(getContext(), new CompactHeaderModel(null, profileName, profileModel.getLabel(), profileAvatarUri != null ? profileAvatarUri.toString() : profileModel.getImageUrl()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeAfterSave() {
        EditProfileViewModel.EditProfileCallback editProfileCallback = this.dismissCallback;
        if (editProfileCallback != null) {
            editProfileCallback.onDismiss(this.viewModel);
        }
    }

    private void initTextStyling() {
        applyFontStyle(this.styleSheet, this.avatarUpdateButtonView, FontStyleKey.ACCENT_TERTIARY);
        StyleSheet styleSheet = this.styleSheet;
        TextView textView = this.nameTitleView;
        FontStyleKey fontStyleKey = FontStyleKey.INDEX_PRIMARY;
        applyFontStyle(styleSheet, textView, fontStyleKey);
        StyleSheet styleSheet2 = this.styleSheet;
        TextView textView2 = this.editNameView;
        FontStyleKey fontStyleKey2 = FontStyleKey.PRIMARY_GLASS;
        applyFontStyle(styleSheet2, textView2, fontStyleKey2);
        applyFontStyle(this.styleSheet, this.privacyTitleView, fontStyleKey);
        applyFontStyle(this.styleSheet, this.editPrivacyView, FontStyleKey.PRIMARY);
        if (this.showHandle.booleanValue()) {
            applyFontStyle(this.styleSheet, this.handleTitleView, fontStyleKey);
            applyFontStyle(this.styleSheet, this.editHandleView, fontStyleKey2);
        }
    }

    private void applyFontStyle(StyleSheet styleSheet, TextView textView, FontStyleKey styleKey) {
        FontUtil.INSTANCE.applyFontStyle(textView, styleSheet.getFontStyle(styleKey));
    }

    private void initTextForTextViews() {
        setTextForTextView(this.avatarUpdateButtonView, this.context.getString(R.string.dmusic_amazon_music_editprofile_imageupdate));
        setTextForTextView(this.nameTitleView, this.context.getString(R.string.dmusic_amazon_music_editprofile_name));
        setTextForTextView(this.privacyTitleView, this.context.getString(R.string.dmusic_amazon_music_editprofile_settings));
        setTextForTextView(this.editPrivacyView, this.context.getString(R.string.dmusic_amazon_music_editprofile_privacy));
        if (this.showHandle.booleanValue()) {
            setTextForTextView(this.handleTitleView, this.context.getString(R.string.dmusic_amazon_music_editprofile_handle));
        }
    }

    private void setTextForTextView(TextView textView, String text) {
        textView.setText(text);
    }

    private void initMargins() {
        setMarginsForView(this.avatarView, -2, -2);
        setMarginsForView(this.avatarUpdateButtonView, -2, -2);
        setMarginsForView(this.nameTitleView, -1, -2);
        setMarginsForView(this.editNameView, -2, -2);
        setMarginsForView(this.editNameButton, -2, -2);
        setMarginsForView(this.privacyTitleView, -1, -2);
        setMarginsForView(this.editPrivacyView, -2, -2);
        setMarginsForView(this.editPrivacyButton, -2, -2);
        if (this.showHandle.booleanValue()) {
            setMarginsForView(this.handleTitleView, -1, -2);
            setMarginsForView(this.editHandleView, -2, -2);
            setMarginsForView(this.editHandleButton, -2, -2);
        }
    }

    private void setMarginsForView(View view, int width, int height) {
        applyMargin(view, width, height, Integer.valueOf(getTopMargin(view)), 0, 0, 0);
        applyPadding(view, getLeftPadding(view), 0, getRightPadding(view), getBottomPadding(view));
    }

    private int getTopMargin(View view) {
        if (view == this.avatarView) {
            return this.styleSheet.getSpacerInPixels(SpacerKey.EPIC).intValue() + this.styleSheet.getSpacerInPixels(SpacerKey.LARGE).intValue();
        }
        if (view == this.nameTitleView) {
            return this.styleSheet.getSpacerInPixels(SpacerKey.LARGE).intValue();
        }
        if (view == this.avatarUpdateButtonView) {
            return this.styleSheet.getSpacerInPixels(SpacerKey.MICRO).intValue();
        }
        if (view == this.editNameView || view == this.privacyTitleView || view == this.editPrivacyView || view == this.handleTitleView || view == this.editHandleView) {
            return this.styleSheet.getSpacerInPixels(SpacerKey.MEDIUM).intValue();
        }
        if (view == this.editNameButton || view == this.editPrivacyButton || view == this.editHandleButton) {
            return this.styleSheet.getSpacerInPixels(SpacerKey.SMALL).intValue();
        }
        return 0;
    }

    private int getBottomPadding(View view) {
        if (view == this.avatarUpdateButtonView) {
            return this.styleSheet.getSpacerInPixels(SpacerKey.MINI).intValue();
        }
        return 0;
    }

    private int getLeftPadding(View view) {
        if (view == this.avatarUpdateButtonView || view == this.nameTitleView || view == this.editNameView || view == this.privacyTitleView || view == this.editPrivacyView || view == this.handleTitleView || view == this.editHandleView) {
            return this.styleSheet.getSpacerInPixels(SpacerKey.MEDIUM).intValue();
        }
        return 0;
    }

    private int getRightPadding(View view) {
        if (view == this.avatarUpdateButtonView || view == this.nameTitleView || view == this.editNameView || view == this.privacyTitleView || view == this.editPrivacyView) {
            return this.styleSheet.getSpacerInPixels(SpacerKey.MEDIUM).intValue();
        }
        return 0;
    }

    private void applyMargin(View textView, int width, int height, Integer top, Integer left, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(textView, width, height, Boolean.FALSE, left, top, right, bottom);
    }

    private void applyPadding(View textView, int leftPadding, int topPadding, int rightPadding, int bottomPadding) {
        textView.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
    }

    private void configureBaseButtons(BaseButton button, Drawable resId) {
        this.styleSheet.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.PRIMARY).withIcon(resId).applyStyle(button);
    }

    private View.OnClickListener routeToEditNameScreen() {
        return new View.OnClickListener() { // from class: com.amazon.mp3.customerprofile.EditCustomerProfileFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                CustomerProfileMetrics.emitUiClickMetric(ActionType.EDIT_NAME);
                if (EditCustomerProfileFragment.this.context instanceof MusicHomeActivity) {
                    new CustomerProfileNavigation((MusicHomeActivity) EditCustomerProfileFragment.this.context).loadEditProfileName(EditCustomerProfileFragment.this.mProfileModel);
                }
            }
        };
    }

    private View.OnClickListener routeToEditHandleScreen() {
        return new View.OnClickListener() { // from class: com.amazon.mp3.customerprofile.EditCustomerProfileFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                CustomerProfileMetrics.emitUiClickMetric(ActionType.EDIT_HANDLE);
                if (EditCustomerProfileFragment.this.context instanceof MusicHomeActivity) {
                    new CustomerProfileNavigation((MusicHomeActivity) EditCustomerProfileFragment.this.context).loadEditProfileHandle(EditCustomerProfileFragment.this.mProfileModel);
                }
            }
        };
    }

    private View.OnClickListener routeToEditPrivacyScreen() {
        return new View.OnClickListener() { // from class: com.amazon.mp3.customerprofile.EditCustomerProfileFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (EditCustomerProfileFragment.this.context instanceof MusicHomeActivity) {
                    new CustomerProfileNavigation((MusicHomeActivity) EditCustomerProfileFragment.this.context).loadEditProfilePrivacy(EditCustomerProfileFragment.this.mProfileModel);
                }
            }
        };
    }
}
