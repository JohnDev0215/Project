package com.amazon.mp3.library.mylibrary;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.BauhausActionBarView;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.customerprofile.CustomerProfileMetrics;
import com.amazon.mp3.customerprofile.CustomerProfileNavigation;
import com.amazon.mp3.feed.FeedButton;
import com.amazon.mp3.inappmessaging.DynamicMessagingManager;
import com.amazon.mp3.library.mylibrary.LibraryFragment;
import com.amazon.mp3.library.mylibrary.LibraryPodcastFragment;
import com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment;
import com.amazon.mp3.library.mylibrary.profileheader.ProfileHeaderViewModel;
import com.amazon.mp3.library.util.MyLibraryPodcastsToggleHelper;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.music.curate.featuregating.CurateFeatureGating;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.picassoimageloader.PicassoImageLoader;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.graphql.generated.UserProfileQuery;
import com.amazon.music.views.library.binders.CompactHeaderBinder;
import com.amazon.music.views.library.models.CompactHeaderModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.CompactHeaderView;
import com.amazon.music.views.library.views.MultiToggleView;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseImage;
import com.apollographql.apollo3.api.ApolloResponse;
import com.squareup.picasso.Picasso;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: compiled from: LibraryFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u0000 a2\u00020\u0001:\u0001aB\u0007¢\u0006\u0004\b_\u0010`J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0002H\u0002J&\u0010\f\u001a\u00020\u00022\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0002J\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\rH\u0002J\b\u0010\u0010\u001a\u00020\u0002H\u0002J\b\u0010\u0011\u001a\u00020\u0002H\u0002J\b\u0010\u0012\u001a\u00020\u0002H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\u0010\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\rH\u0002J\b\u0010\u0017\u001a\u00020\u0002H\u0002J\b\u0010\u0018\u001a\u00020\u0002H\u0002J\u0010\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0013H\u0002J\b\u0010\u001c\u001a\u00020\u001bH\u0002J\u0010\u0010\u001f\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001dH\u0002J\u0018\u0010\"\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u00132\u0006\u0010!\u001a\u00020\nH\u0002J\u0012\u0010%\u001a\u00020\u00022\b\u0010$\u001a\u0004\u0018\u00010#H\u0002J\b\u0010&\u001a\u00020\u0002H\u0002J\b\u0010'\u001a\u00020\nH\u0002J&\u0010.\u001a\u0004\u0018\u00010\u001d2\u0006\u0010)\u001a\u00020(2\b\u0010+\u001a\u0004\u0018\u00010*2\b\u0010-\u001a\u0004\u0018\u00010,H\u0016J\u001a\u0010/\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001d2\b\u0010-\u001a\u0004\u0018\u00010,H\u0016J\b\u00100\u001a\u00020\u0002H\u0016J\b\u00101\u001a\u00020\u0002H\u0016J\u0006\u00102\u001a\u00020\u0002J\b\u00103\u001a\u00020\u0002H\u0004R\u0018\u00105\u001a\u0004\u0018\u0001048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b5\u00106R\u0018\u00108\u001a\u0004\u0018\u0001078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b8\u00109R\u0016\u0010:\u001a\u00020\u001d8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b:\u0010;R\u0016\u0010=\u001a\u00020<8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b=\u0010>R\u0016\u0010?\u001a\u00020\u001d8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b?\u0010;R\u0016\u0010A\u001a\u00020@8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bA\u0010BR\u0018\u0010D\u001a\u0004\u0018\u00010C8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bD\u0010ER\u0018\u0010F\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bF\u0010;R\u0018\u0010H\u001a\u0004\u0018\u00010G8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bH\u0010IR\u0018\u0010K\u001a\u0004\u0018\u00010J8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bK\u0010LR\u0018\u0010N\u001a\u0004\u0018\u00010M8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bN\u0010OR\u0016\u0010P\u001a\u00020\u00018\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bP\u0010QR\u0018\u0010S\u001a\u0004\u0018\u00010R8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bS\u0010TR\u0018\u0010V\u001a\u0004\u0018\u00010U8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bV\u0010WR\"\u0010Y\u001a\u00020X8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\bY\u0010Z\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^¨\u0006b"}, d2 = {"Lcom/amazon/mp3/library/mylibrary/LibraryFragment;", "Landroidx/fragment/app/Fragment;", "", "setupProfileHeaderViewIfEnabled", "initProfileHeaderView", "observeProfileData", "Lcom/amazon/music/platform/Outcome;", "Lcom/apollographql/apollo3/api/ApolloResponse;", "Lcom/amazon/music/platform/graphql/generated/UserProfileQuery$Data;", "response", "", "saveToLocal", "processProfileResponse", "Lcom/amazon/music/views/library/models/CompactHeaderModel;", "model", "saveProfileToLocal", "getProfileFromLocal", "initPodcastToggleIfSupported", "initMusicFragmentView", "", "getDefaultActiveButton", "compactHeaderModel", "bindProfileHeaderView", "setLibraryBackground", "navigateToProfilePage", "defaultActiveButton", "initPodcastToggleView", "Lcom/amazon/music/views/library/views/MultiToggleView$OnValueChangedListener;", "createOnToggleValueChangedListener", "Landroid/view/View;", "view", "displayLibraryTabOpenDynamicMessage", "position", "isLibraryMusicToggleSelected", "flipViewAndWriteTogglePreference", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "sendUiClickMetrics", "sendProfileHeaderUiContentViewEvent", "isProfileHeaderSupported", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/os/Bundle;", "savedInstanceState", "onCreateView", "onViewCreated", "onResume", "onDestroyView", "onLibraryFragmentShownOnBackPressed", "initActionBarView", "Lcom/amazon/mp3/actionbar/view/BauhausActionBarView;", "actionBarView", "Lcom/amazon/mp3/actionbar/view/BauhausActionBarView;", "Lcom/amazon/mp3/feed/FeedButton;", "feedButton", "Lcom/amazon/mp3/feed/FeedButton;", "libraryFragmentView", "Landroid/view/View;", "Landroid/widget/ViewFlipper;", "viewFlipper", "Landroid/widget/ViewFlipper;", "musicFragmentView", "Landroid/widget/LinearLayout;", "profileContainerLayout", "Landroid/widget/LinearLayout;", "Lcom/amazon/mp3/library/mylibrary/profileheader/ProfileHeaderViewModel;", "profileViewModel", "Lcom/amazon/mp3/library/mylibrary/profileheader/ProfileHeaderViewModel;", "podcastFragmentView", "Lcom/amazon/mp3/library/mylibrary/MyLibraryHomeFragment;", "libraryMusicFragmentV1", "Lcom/amazon/mp3/library/mylibrary/MyLibraryHomeFragment;", "Lcom/amazon/mp3/library/mylibrary/MyLibraryV2HomeFragment;", "libraryMusicFragmentV2", "Lcom/amazon/mp3/library/mylibrary/MyLibraryV2HomeFragment;", "Lcom/amazon/mp3/library/mylibrary/LibraryPodcastFragment;", "libraryPodcastFragment", "Lcom/amazon/mp3/library/mylibrary/LibraryPodcastFragment;", "fragment", "Landroidx/fragment/app/Fragment;", "Lcom/amazon/music/views/library/views/MultiToggleView;", "toggleView", "Lcom/amazon/music/views/library/views/MultiToggleView;", "Lcom/amazon/music/views/library/views/CompactHeaderView;", "compactHeaderView", "Lcom/amazon/music/views/library/views/CompactHeaderView;", "Lrx/subscriptions/CompositeSubscription;", "subscriptions", "Lrx/subscriptions/CompositeSubscription;", "getSubscriptions", "()Lrx/subscriptions/CompositeSubscription;", "setSubscriptions", "(Lrx/subscriptions/CompositeSubscription;)V", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class LibraryFragment extends Fragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = LibraryFragment.class.getSimpleName();
    private BauhausActionBarView actionBarView;
    private CompactHeaderView compactHeaderView;
    private FeedButton feedButton;
    private Fragment fragment;
    private View libraryFragmentView;
    private MyLibraryHomeFragment libraryMusicFragmentV1;
    private MyLibraryV2HomeFragment libraryMusicFragmentV2;
    private LibraryPodcastFragment libraryPodcastFragment;
    private View musicFragmentView;
    private View podcastFragmentView;
    private LinearLayout profileContainerLayout;
    private ProfileHeaderViewModel profileViewModel;
    private CompositeSubscription subscriptions = new CompositeSubscription();
    private MultiToggleView toggleView;
    private ViewFlipper viewFlipper;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_library, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater\n               …                   false)");
        this.libraryFragmentView = viewInflate;
        if (viewInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("libraryFragmentView");
            viewInflate = null;
        }
        View viewFindViewById = viewInflate.findViewById(R.id.library_fragment_view_flipper);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "libraryFragmentView.find…ry_fragment_view_flipper)");
        this.viewFlipper = (ViewFlipper) viewFindViewById;
        View view = this.libraryFragmentView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("libraryFragmentView");
            view = null;
        }
        View viewFindViewById2 = view.findViewById(R.id.library_fragment_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "libraryFragmentView.find…brary_fragment_container)");
        this.profileContainerLayout = (LinearLayout) viewFindViewById2;
        View viewInflate2 = inflater.inflate(R.layout.fragment_library_music_container, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate2, "inflater\n               …                   false)");
        this.musicFragmentView = viewInflate2;
        if (MyLibraryPodcastsToggleHelper.INSTANCE.isPodcastsToggleSupported()) {
            this.podcastFragmentView = inflater.inflate(R.layout.fragment_library_podcast_container, container, false);
        }
        View view2 = this.libraryFragmentView;
        if (view2 != null) {
            return view2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("libraryFragmentView");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initActionBarView();
        initMusicFragmentView();
        initPodcastToggleIfSupported();
        setupProfileHeaderViewIfEnabled();
        displayLibraryTabOpenDynamicMessage(view);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Context context = getContext();
        if (context == null) {
            return;
        }
        if (LibraryPreferences.INSTANCE.readProfileModel(context) == null) {
            CompactHeaderView compactHeaderView = this.compactHeaderView;
            if (compactHeaderView != null) {
                compactHeaderView.setVisibility(8);
            }
            ProfileHeaderViewModel profileHeaderViewModel = this.profileViewModel;
            if (profileHeaderViewModel != null) {
                profileHeaderViewModel.clearData();
            }
            ProfileHeaderViewModel profileHeaderViewModel2 = this.profileViewModel;
            if (profileHeaderViewModel2 != null) {
                profileHeaderViewModel2.fetchProfile();
            }
        }
        BauhausActionBarView bauhausActionBarView = this.actionBarView;
        if (bauhausActionBarView == null) {
            return;
        }
        bauhausActionBarView.showProfileIcon();
    }

    private final void setupProfileHeaderViewIfEnabled() {
        Unit unit;
        ProfileHeaderViewModel profileHeaderViewModel;
        if (isProfileHeaderSupported()) {
            initProfileHeaderView();
            if (this.profileViewModel == null) {
                FragmentActivity fragmentActivityRequireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
                this.profileViewModel = (ProfileHeaderViewModel) new ViewModelProvider(fragmentActivityRequireActivity).get(ProfileHeaderViewModel.class);
                observeProfileData();
            }
            ProfileHeaderViewModel profileHeaderViewModel2 = this.profileViewModel;
            if (profileHeaderViewModel2 == null) {
                return;
            }
            Outcome<ApolloResponse<UserProfileQuery.Data>> value = profileHeaderViewModel2.getProfileResponseLiveData().getValue();
            if (value == null) {
                unit = null;
            } else {
                processProfileResponse(value, false);
                unit = Unit.INSTANCE;
            }
            if (unit != null || (profileHeaderViewModel = this.profileViewModel) == null) {
                return;
            }
            profileHeaderViewModel.fetchProfile();
        }
    }

    private final void initProfileHeaderView() {
        this.subscriptions.add(StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.library.mylibrary.LibraryFragment$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LibraryFragment.m1304initProfileHeaderView$lambda4(this.f$0, (StyleSheet) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initProfileHeaderView$lambda-4, reason: not valid java name */
    public static final void m1304initProfileHeaderView$lambda4(LibraryFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.compactHeaderView == null) {
            Context contextRequireContext = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            Intrinsics.checkNotNullExpressionValue(styleSheet, "styleSheet");
            this$0.compactHeaderView = new CompactHeaderView(contextRequireContext, styleSheet);
        }
        CompactHeaderView compactHeaderView = this$0.compactHeaderView;
        if (compactHeaderView != null) {
            LinearLayout linearLayout = this$0.profileContainerLayout;
            LinearLayout linearLayout2 = null;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("profileContainerLayout");
                linearLayout = null;
            }
            if (!(linearLayout.indexOfChild(compactHeaderView) != -1)) {
                LinearLayout linearLayout3 = this$0.profileContainerLayout;
                if (linearLayout3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("profileContainerLayout");
                } else {
                    linearLayout2 = linearLayout3;
                }
                linearLayout2.addView(compactHeaderView, 0);
                compactHeaderView.setVisibility(8);
            }
        }
        this$0.getProfileFromLocal();
    }

    private final void observeProfileData() {
        LiveData<Outcome<ApolloResponse<UserProfileQuery.Data>>> profileResponseLiveData;
        ProfileHeaderViewModel profileHeaderViewModel = this.profileViewModel;
        if (profileHeaderViewModel == null || (profileResponseLiveData = profileHeaderViewModel.getProfileResponseLiveData()) == null) {
            return;
        }
        profileResponseLiveData.observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.library.mylibrary.LibraryFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LibraryFragment.m1305observeProfileData$lambda6(this.f$0, (Outcome) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: observeProfileData$lambda-6, reason: not valid java name */
    public static final void m1305observeProfileData$lambda6(LibraryFragment this$0, Outcome outcome) {
        CompactHeaderView compactHeaderView;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (outcome == null && (compactHeaderView = this$0.compactHeaderView) != null) {
            compactHeaderView.setVisibility(8);
        }
        if (outcome == null) {
            return;
        }
        this$0.processProfileResponse(outcome, true);
    }

    private final void processProfileResponse(Outcome<ApolloResponse<UserProfileQuery.Data>> response, boolean saveToLocal) {
        ProfileHeaderViewModel profileHeaderViewModel = this.profileViewModel;
        if (profileHeaderViewModel == null) {
            return;
        }
        if (response instanceof Outcome.Success) {
            CompactHeaderModel profileModel = profileHeaderViewModel.getProfileModel();
            if (profileModel == null) {
                return;
            }
            if (saveToLocal) {
                saveProfileToLocal(profileModel);
                bindProfileHeaderView(profileModel);
                return;
            } else {
                getProfileFromLocal();
                return;
            }
        }
        if (!(response instanceof Outcome.Failure)) {
            throw new NoWhenBranchMatchedException();
        }
        getProfileFromLocal();
    }

    private final void saveProfileToLocal(CompactHeaderModel model) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        LibraryPreferences.INSTANCE.writeProfileModel(context, model);
    }

    private final void getProfileFromLocal() {
        CompactHeaderModel profileModel;
        Context context = getContext();
        if (context == null || (profileModel = LibraryPreferences.INSTANCE.readProfileModel(context)) == null) {
            return;
        }
        bindProfileHeaderView(profileModel);
        ProfileHeaderViewModel profileHeaderViewModel = this.profileViewModel;
        if (profileHeaderViewModel == null) {
            return;
        }
        profileHeaderViewModel.setProfileModel(profileModel);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        ConstraintLayout constraintLayout;
        LinearLayout linearLayout = this.profileContainerLayout;
        ViewFlipper viewFlipper = null;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("profileContainerLayout");
            linearLayout = null;
        }
        linearLayout.removeView(this.compactHeaderView);
        this.compactHeaderView = null;
        this.podcastFragmentView = null;
        FeedButton feedButton = this.feedButton;
        if (feedButton != null) {
            FragmentActivity fragmentActivityRequireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
            feedButton.removeObservers(fragmentActivityRequireActivity);
        }
        this.feedButton = null;
        MultiToggleView multiToggleView = this.toggleView;
        if (multiToggleView != null) {
            multiToggleView.clearReference();
        }
        BauhausActionBarView bauhausActionBarView = this.actionBarView;
        if (bauhausActionBarView != null && (constraintLayout = (ConstraintLayout) bauhausActionBarView.findViewById(R.id.toggle_view_container)) != null) {
            constraintLayout.removeAllViews();
        }
        this.toggleView = null;
        FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "childFragmentManager.beginTransaction()");
        MyLibraryHomeFragment myLibraryHomeFragment = this.libraryMusicFragmentV1;
        if (myLibraryHomeFragment != null) {
            fragmentTransactionBeginTransaction.remove(myLibraryHomeFragment);
        }
        MyLibraryV2HomeFragment myLibraryV2HomeFragment = this.libraryMusicFragmentV2;
        if (myLibraryV2HomeFragment != null) {
            fragmentTransactionBeginTransaction.remove(myLibraryV2HomeFragment);
        }
        LibraryPodcastFragment libraryPodcastFragment = this.libraryPodcastFragment;
        if (libraryPodcastFragment != null) {
            fragmentTransactionBeginTransaction.remove(libraryPodcastFragment);
        }
        fragmentTransactionBeginTransaction.commitNowAllowingStateLoss();
        this.subscriptions.unsubscribe();
        BauhausActionBarView bauhausActionBarView2 = this.actionBarView;
        if (bauhausActionBarView2 != null) {
            bauhausActionBarView2.clearSubscriptions();
        }
        ViewFlipper viewFlipper2 = this.viewFlipper;
        if (viewFlipper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewFlipper");
        } else {
            viewFlipper = viewFlipper2;
        }
        viewFlipper.removeAllViews();
        super.onDestroyView();
    }

    public final void onLibraryFragmentShownOnBackPressed() {
        initActionBarView();
        initMusicFragmentView();
        initPodcastToggleIfSupported();
        setupProfileHeaderViewIfEnabled();
    }

    private final void initPodcastToggleIfSupported() {
        ViewFlipper viewFlipper = null;
        View view = null;
        ViewFlipper viewFlipper2 = null;
        if (MyLibraryPodcastsToggleHelper.INSTANCE.isPodcastsToggleSupported()) {
            int defaultActiveButton = getDefaultActiveButton();
            View view2 = this.podcastFragmentView;
            if (view2 != null) {
                ViewFlipper viewFlipper3 = this.viewFlipper;
                if (viewFlipper3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewFlipper");
                    viewFlipper3 = null;
                }
                if (!(viewFlipper3.indexOfChild(view2) != -1)) {
                    ViewFlipper viewFlipper4 = this.viewFlipper;
                    if (viewFlipper4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewFlipper");
                        viewFlipper4 = null;
                    }
                    viewFlipper4.addView(view2);
                }
                if (view2.findViewById(R.id.library_podcast_fragment) == null) {
                    FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
                    Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "childFragmentManager.beginTransaction()");
                    Fragment fragment = this.libraryPodcastFragment;
                    if (fragment != null) {
                        fragmentTransactionBeginTransaction.remove(fragment);
                    }
                    LibraryPodcastFragment.Companion companion = LibraryPodcastFragment.INSTANCE;
                    LibraryPodcastFragment libraryPodcastFragmentNewInstance = companion.newInstance(getArguments());
                    fragmentTransactionBeginTransaction.replace(view2.getId(), libraryPodcastFragmentNewInstance, companion.getTAG());
                    this.libraryPodcastFragment = libraryPodcastFragmentNewInstance;
                    fragmentTransactionBeginTransaction.commitNow();
                }
            }
            initPodcastToggleView(defaultActiveButton);
            if (defaultActiveButton == 0) {
                ViewFlipper viewFlipper5 = this.viewFlipper;
                if (viewFlipper5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewFlipper");
                    viewFlipper5 = null;
                }
                ViewFlipper viewFlipper6 = this.viewFlipper;
                if (viewFlipper6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewFlipper");
                    viewFlipper6 = null;
                }
                View view3 = this.musicFragmentView;
                if (view3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicFragmentView");
                } else {
                    view = view3;
                }
                viewFlipper5.setDisplayedChild(viewFlipper6.indexOfChild(view));
                return;
            }
            LibraryPodcastFragment libraryPodcastFragment = this.libraryPodcastFragment;
            if (libraryPodcastFragment != null) {
                libraryPodcastFragment.setPodcastChildFragmentManager();
            }
            ViewFlipper viewFlipper7 = this.viewFlipper;
            if (viewFlipper7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewFlipper");
                viewFlipper7 = null;
            }
            ViewFlipper viewFlipper8 = this.viewFlipper;
            if (viewFlipper8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewFlipper");
            } else {
                viewFlipper2 = viewFlipper8;
            }
            viewFlipper7.setDisplayedChild(viewFlipper2.indexOfChild(this.podcastFragmentView));
            return;
        }
        if (this.podcastFragmentView == null) {
            return;
        }
        ViewFlipper viewFlipper9 = this.viewFlipper;
        if (viewFlipper9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewFlipper");
        } else {
            viewFlipper = viewFlipper9;
        }
        viewFlipper.removeView(this.podcastFragmentView);
    }

    private final void initMusicFragmentView() {
        String simpleName;
        ViewFlipper viewFlipper = this.viewFlipper;
        Fragment fragment = null;
        if (viewFlipper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewFlipper");
            viewFlipper = null;
        }
        View view = this.musicFragmentView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicFragmentView");
            view = null;
        }
        if (!(viewFlipper.indexOfChild(view) != -1)) {
            ViewFlipper viewFlipper2 = this.viewFlipper;
            if (viewFlipper2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewFlipper");
                viewFlipper2 = null;
            }
            View view2 = this.musicFragmentView;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("musicFragmentView");
                view2 = null;
            }
            viewFlipper2.addView(view2);
        }
        FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "childFragmentManager.beginTransaction()");
        if (MyLibraryPodcastsToggleHelper.INSTANCE.isMyLibraryV2Supported()) {
            MyLibraryV2HomeFragment.Companion companion = MyLibraryV2HomeFragment.INSTANCE;
            this.fragment = companion.newInstance(getArguments());
            simpleName = companion.getTAG();
            Intrinsics.checkNotNullExpressionValue(simpleName, "MyLibraryV2HomeFragment.TAG");
        } else {
            MyLibraryHomeFragment myLibraryHomeFragmentNewInstance = MyLibraryHomeFragment.newInstance(getArguments());
            Intrinsics.checkNotNullExpressionValue(myLibraryHomeFragmentNewInstance, "newInstance(arguments)");
            this.fragment = myLibraryHomeFragmentNewInstance;
            simpleName = MyLibraryHomeFragment.class.getSimpleName();
            Intrinsics.checkNotNullExpressionValue(simpleName, "MyLibraryHomeFragment::class.java.simpleName");
        }
        View view3 = this.musicFragmentView;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicFragmentView");
            view3 = null;
        }
        int id = view3.getId();
        Fragment fragment2 = this.fragment;
        if (fragment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
        } else {
            fragment = fragment2;
        }
        fragmentTransactionBeginTransaction.replace(id, fragment, simpleName);
        fragmentTransactionBeginTransaction.commitNow();
    }

    private final int getDefaultActiveButton() {
        Context context = getContext();
        return !Intrinsics.areEqual(context == null ? null : Boolean.valueOf(LibraryPreferences.INSTANCE.readIsLibraryMusicToggleSelected(context)), Boolean.TRUE) ? 1 : 0;
    }

    private final void bindProfileHeaderView(final CompactHeaderModel compactHeaderModel) {
        this.subscriptions.add(StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.library.mylibrary.LibraryFragment$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LibraryFragment.m1300bindProfileHeaderView$lambda25(this.f$0, compactHeaderModel, (StyleSheet) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindProfileHeaderView$lambda-25, reason: not valid java name */
    public static final void m1300bindProfileHeaderView$lambda25(final LibraryFragment this$0, CompactHeaderModel compactHeaderModel, StyleSheet styleSheet) {
        Resources resources;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(compactHeaderModel, "$compactHeaderModel");
        PicassoImageLoader picassoImageLoader = PicassoImageLoader.builder(this$0.getContext(), Picasso.get()).withTag(this$0.getContext()).build();
        Intrinsics.checkNotNullExpressionValue(styleSheet, "styleSheet");
        Intrinsics.checkNotNullExpressionValue(picassoImageLoader, "picassoImageLoader");
        CompactHeaderBinder compactHeaderBinder = new CompactHeaderBinder(styleSheet, picassoImageLoader);
        Context contextRequireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        compactHeaderBinder.createView(contextRequireContext);
        CompactHeaderView compactHeaderView = this$0.compactHeaderView;
        if (compactHeaderView == null) {
            return;
        }
        this$0.setLibraryBackground();
        Integer numValueOf = null;
        if (!CurateFeatureGating.NATIVE_PROFILE_USERNAME_ANDROID.isEnabled()) {
            compactHeaderModel = new CompactHeaderModel(null, compactHeaderView.getContext().getString(R.string.dmusic_profile_header_title), compactHeaderModel.getLabel(), compactHeaderModel.getImageUrl());
        }
        compactHeaderBinder.bind(compactHeaderView, compactHeaderModel);
        if (compactHeaderView.getVisibility() != 0) {
            compactHeaderView.setVisibility(0);
            this$0.sendProfileHeaderUiContentViewEvent();
        }
        BaseImage image = compactHeaderView.getImage();
        if (image == null) {
            return;
        }
        image.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.mylibrary.LibraryFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LibraryFragment.m1301bindProfileHeaderView$lambda25$lambda24$lambda23$lambda21(this.f$0, view);
            }
        });
        Context context = image.getContext();
        if (context != null && (resources = context.getResources()) != null) {
            numValueOf = Integer.valueOf(((int) resources.getDimension(R.dimen.margin)) * 2);
        }
        LayoutParamUtils.INSTANCE.setLayoutMargins(image, -2, -2, Boolean.FALSE, numValueOf == null ? styleSheet.getSpacerInPixels(SpacerKey.MEDIUM) : numValueOf, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindProfileHeaderView$lambda-25$lambda-24$lambda-23$lambda-21, reason: not valid java name */
    public static final void m1301bindProfileHeaderView$lambda25$lambda24$lambda23$lambda21(LibraryFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.navigateToProfilePage();
    }

    private final void setLibraryBackground() {
        ViewParent parent;
        BauhausActionBarView bauhausActionBarView = this.actionBarView;
        if (bauhausActionBarView == null || (parent = bauhausActionBarView.getParent()) == null || !(parent instanceof Toolbar)) {
            return;
        }
        Toolbar toolbar = (Toolbar) parent;
        toolbar.setBackgroundColor(ContextCompat.getColor(toolbar.getContext(), R.color.secondary_glass_3));
        View view = this.libraryFragmentView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("libraryFragmentView");
            view = null;
        }
        view.setBackground(ContextCompat.getDrawable(toolbar.getContext(), R.drawable.gradient_1));
    }

    private final void navigateToProfilePage() {
        if (getContext() instanceof MusicHomeActivity) {
            CustomerProfileMetrics.emitUiClickMetric(ActionType.GO_MY_PROFILE);
            Context context = getContext();
            if (context == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.activity.MusicHomeActivity");
            }
            new CustomerProfileNavigation((MusicHomeActivity) context).loadProfile();
        }
    }

    private final void initPodcastToggleView(final int defaultActiveButton) {
        this.subscriptions.add(StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.library.mylibrary.LibraryFragment$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LibraryFragment.m1302initPodcastToggleView$lambda29(this.f$0, defaultActiveButton, (StyleSheet) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initPodcastToggleView$lambda-29, reason: not valid java name */
    public static final void m1302initPodcastToggleView$lambda29(final LibraryFragment this$0, final int i, final StyleSheet styleSheet) {
        BauhausActionBarView bauhausActionBarView;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        final Context context = this$0.getContext();
        if (context == null || (bauhausActionBarView = this$0.actionBarView) == null) {
            return;
        }
        bauhausActionBarView.post(new Runnable() { // from class: com.amazon.mp3.library.mylibrary.LibraryFragment$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                LibraryFragment.m1303initPodcastToggleView$lambda29$lambda28$lambda27(this.f$0, context, styleSheet, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initPodcastToggleView$lambda-29$lambda-28$lambda-27, reason: not valid java name */
    public static final void m1303initPodcastToggleView$lambda29$lambda28$lambda27(final LibraryFragment this$0, final Context it, final StyleSheet styleSheet, final int i) {
        ViewTreeObserver viewTreeObserver;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "$it");
        final MultiToggleView.OnValueChangedListener onValueChangedListenerCreateOnToggleValueChangedListener = this$0.createOnToggleValueChangedListener();
        BauhausActionBarView bauhausActionBarView = this$0.actionBarView;
        final ConstraintLayout constraintLayout = bauhausActionBarView == null ? null : (ConstraintLayout) bauhausActionBarView.findViewById(R.id.toggle_view_container);
        if (constraintLayout == null || (viewTreeObserver = constraintLayout.getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.mp3.library.mylibrary.LibraryFragment$initPodcastToggleView$1$1$1$1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                ViewParent parent;
                constraintLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = constraintLayout.getWidth();
                if (this$0.toggleView == null) {
                    LibraryFragment libraryFragment = this$0;
                    MyLibraryPodcastsToggleHelper myLibraryPodcastsToggleHelper = MyLibraryPodcastsToggleHelper.INSTANCE;
                    Context it2 = it;
                    Intrinsics.checkNotNullExpressionValue(it2, "it");
                    StyleSheet styleSheet2 = styleSheet;
                    Intrinsics.checkNotNullExpressionValue(styleSheet2, "styleSheet");
                    libraryFragment.toggleView = myLibraryPodcastsToggleHelper.createToggleWidget(it2, styleSheet2, onValueChangedListenerCreateOnToggleValueChangedListener, i, Integer.valueOf(width));
                    ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(-2, -2);
                    boolean z = false;
                    layoutParams.bottomToBottom = 0;
                    layoutParams.endToEnd = 0;
                    layoutParams.startToStart = 0;
                    layoutParams.topToTop = 0;
                    MultiToggleView multiToggleView = this$0.toggleView;
                    if (multiToggleView != null) {
                        multiToggleView.setLayoutParams(layoutParams);
                    }
                    BauhausActionBarView bauhausActionBarView2 = this$0.actionBarView;
                    if (bauhausActionBarView2 != null && (parent = bauhausActionBarView2.getParent()) != null) {
                        LibraryFragment libraryFragment2 = this$0;
                        if (libraryFragment2.isProfileHeaderSupported()) {
                            MultiToggleView multiToggleView2 = libraryFragment2.toggleView;
                            if (multiToggleView2 != null) {
                                multiToggleView2.setBackgroundColor(ContextCompat.getColor(multiToggleView2.getContext(), android.R.color.transparent));
                            }
                        } else if (parent instanceof Toolbar) {
                            Drawable background = ((Toolbar) parent).getBackground();
                            if (background != null) {
                                ColorDrawable colorDrawable = (ColorDrawable) background;
                                MultiToggleView multiToggleView3 = libraryFragment2.toggleView;
                                if (multiToggleView3 != null) {
                                    multiToggleView3.setBackgroundColor(colorDrawable.getColor());
                                }
                            } else {
                                throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.ColorDrawable");
                            }
                        }
                    }
                    if (this$0.toggleView != null) {
                        BauhausActionBarView bauhausActionBarView3 = this$0.actionBarView;
                        if (bauhausActionBarView3 != null) {
                            MultiToggleView multiToggleView4 = this$0.toggleView;
                            Intrinsics.checkNotNull(multiToggleView4);
                            if (!(bauhausActionBarView3.indexOfChild(multiToggleView4) != -1)) {
                                z = true;
                            }
                        }
                        if (z) {
                            constraintLayout.addView(this$0.toggleView);
                        }
                    }
                }
            }
        });
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.mylibrary.LibraryFragment$createOnToggleValueChangedListener$1, reason: invalid class name */
    /* JADX INFO: compiled from: LibraryFragment.kt */
    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/amazon/mp3/library/mylibrary/LibraryFragment$createOnToggleValueChangedListener$1", "Lcom/amazon/music/views/library/views/MultiToggleView$OnValueChangedListener;", "onValueChanged", "", "position", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class AnonymousClass1 implements MultiToggleView.OnValueChangedListener {
        AnonymousClass1() {
        }

        @Override // com.amazon.music.views.library.views.MultiToggleView.OnValueChangedListener
        public void onValueChanged(final int position) {
            MultiToggleView multiToggleView = LibraryFragment.this.toggleView;
            if (multiToggleView == null) {
                return;
            }
            final LibraryFragment libraryFragment = LibraryFragment.this;
            multiToggleView.post(new Runnable() { // from class: com.amazon.mp3.library.mylibrary.LibraryFragment$createOnToggleValueChangedListener$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    LibraryFragment.AnonymousClass1.m1306onValueChanged$lambda0(position, libraryFragment);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onValueChanged$lambda-0, reason: not valid java name */
        public static final void m1306onValueChanged$lambda0(int i, LibraryFragment this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (i == 0) {
                this$0.flipViewAndWriteTogglePreference(i, true);
                this$0.sendUiClickMetrics(ActionType.GO_MY_MUSIC);
                return;
            }
            LibraryPodcastFragment libraryPodcastFragment = this$0.libraryPodcastFragment;
            if (libraryPodcastFragment != null) {
                libraryPodcastFragment.setPodcastChildFragmentManager();
            }
            this$0.flipViewAndWriteTogglePreference(i, false);
            this$0.sendUiClickMetrics(ActionType.GO_PODCASTS_LIBRARY);
        }
    }

    private final MultiToggleView.OnValueChangedListener createOnToggleValueChangedListener() {
        return new AnonymousClass1();
    }

    private final void displayLibraryTabOpenDynamicMessage(View view) {
        DynamicMessagingManager dynamicMessagingManager = new DynamicMessagingManager(view.getContext());
        Fragment fragment = this.fragment;
        if (fragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            fragment = null;
        }
        dynamicMessagingManager.displayDynamicMessage(fragment.getActivity(), new DynamicMessageEvent(Trigger.LIBRARY_TAB_OPEN), new DynamicMessageMetricsAttributes()).subscribe();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void flipViewAndWriteTogglePreference(int position, boolean isLibraryMusicToggleSelected) {
        ViewFlipper viewFlipper = this.viewFlipper;
        if (viewFlipper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewFlipper");
            viewFlipper = null;
        }
        viewFlipper.setDisplayedChild(position);
        Context context = getContext();
        if (context == null) {
            return;
        }
        LibraryPreferences.INSTANCE.writeIsLibraryMusicToggleSelected(context, isLibraryMusicToggleSelected);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendUiClickMetrics(ActionType actionType) {
        if (actionType != null) {
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withPageType(PageType.LIBRARY_LANDING_PAGE).withInteractionType(InteractionType.TOGGLE).withEventTime(System.currentTimeMillis()).build());
        }
    }

    private final void sendProfileHeaderUiContentViewEvent() {
        MetricsLogger.sendEvent(UiContentViewEvent.clientEventBuilder().withContainerType(ContainerType.SECTION).withContentName(ContentName.LIBRARY_LANDING_PROFILE_WIDGET).withImpressionTimestamp(System.currentTimeMillis()).build());
    }

    protected final void initActionBarView() {
        BauhausActionBarView bauhausActionBarView;
        FragmentActivity activity;
        if (this.actionBarView == null && (activity = getActivity()) != null) {
            BauhausActionBarView bauhausActionBarView2 = new BauhausActionBarView(activity, null, Boolean.valueOf(AmazonApplication.getCapabilities().isCustomerProfileSupported()), Boolean.TRUE);
            this.actionBarView = bauhausActionBarView2;
            if (activity instanceof MusicHomeActivity) {
                ((MusicHomeActivity) activity).setHeaderView(bauhausActionBarView2);
            }
            BauhausActionBarView bauhausActionBarView3 = this.actionBarView;
            if (bauhausActionBarView3 != null) {
                bauhausActionBarView3.showToggleView();
            }
            BauhausActionBarView bauhausActionBarView4 = this.actionBarView;
            if (bauhausActionBarView4 == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.View");
            }
            this.feedButton = new FeedButton(bauhausActionBarView4, activity);
        }
        FragmentActivity activity2 = getActivity();
        MusicHomeActivity musicHomeActivity = activity2 instanceof MusicHomeActivity ? (MusicHomeActivity) activity2 : null;
        if (musicHomeActivity != null) {
            musicHomeActivity.setHeaderView(this.actionBarView);
            musicHomeActivity.restoreHomeButton();
        }
        BauhausActionBarView bauhausActionBarView5 = this.actionBarView;
        if (bauhausActionBarView5 != null) {
            bauhausActionBarView5.setBackButtonVisible(false);
        }
        BauhausActionBarView bauhausActionBarView6 = this.actionBarView;
        if (bauhausActionBarView6 != null) {
            bauhausActionBarView6.addTopMargin();
        }
        if (PlatformUtil.isFireOS() || (bauhausActionBarView = this.actionBarView) == null) {
            return;
        }
        bauhausActionBarView.setFeedButtonVisible(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isProfileHeaderSupported() {
        return MyLibraryPodcastsToggleHelper.INSTANCE.isMyLibraryV2Supported() && CurateFeatureGating.NATIVE_PROFILE_MY_LIBRARY_PAGE_ANDROID.isEnabled();
    }

    /* JADX INFO: compiled from: LibraryFragment.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/library/mylibrary/LibraryFragment$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "newInstance", "Lcom/amazon/mp3/library/mylibrary/LibraryFragment;", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LibraryFragment newInstance(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            LibraryFragment libraryFragment = new LibraryFragment();
            libraryFragment.setArguments(intent.getExtras());
            return libraryFragment;
        }
    }
}
