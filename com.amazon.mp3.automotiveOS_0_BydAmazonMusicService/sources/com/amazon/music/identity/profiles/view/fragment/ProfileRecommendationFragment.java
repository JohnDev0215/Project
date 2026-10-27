package com.amazon.music.identity.profiles.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.identity.profiles.R$dimen;
import com.amazon.music.identity.profiles.R$id;
import com.amazon.music.identity.profiles.R$integer;
import com.amazon.music.identity.profiles.R$string;
import com.amazon.music.identity.profiles.datasource.mockprofile.MockProfileManager;
import com.amazon.music.identity.profiles.view.adapter.ProfileRecommendationAdapter;
import com.amazon.music.identity.profiles.view.util.NavigationHelper;
import com.amazon.music.identity.profiles.viewmodel.ProfileListViewModel;
import com.amazon.ui.foundations.views.CustomGridLayoutItemDecorator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProfileRecommendationFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\n\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/identity/profiles/view/fragment/ProfileRecommendationFragment;", "Lcom/amazon/music/identity/profiles/view/fragment/ProfileListFragment;", "()V", "mCloseButton", "Landroid/widget/ImageView;", "mInviteLinkBox", "Landroid/widget/RelativeLayout;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setCloseButton", "", "setSentInviteLink", "Landroid/view/View$OnClickListener;", "Companion", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ProfileRecommendationFragment extends ProfileListFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = ProfileRecommendationFragment.class.getSimpleName();
    private ImageView mCloseButton;
    private RelativeLayout mInviteLinkBox;

    private final View.OnClickListener setSentInviteLink() {
        return new View.OnClickListener() { // from class: com.amazon.music.identity.profiles.view.fragment.ProfileRecommendationFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileRecommendationFragment.m2138setSentInviteLink$lambda5(view);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setSentInviteLink$lambda-5, reason: not valid java name */
    public static final void m2138setSentInviteLink$lambda5(View view) {
    }

    /* JADX INFO: compiled from: ProfileRecommendationFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/identity/profiles/view/fragment/ProfileRecommendationFragment$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return ProfileRecommendationFragment.TAG;
        }
    }

    @Override // com.amazon.music.identity.profiles.view.fragment.ProfileListFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
        ProfileListViewModel profileListViewModel = (ProfileListViewModel) new ViewModelProvider(fragmentActivityRequireActivity).get(ProfileListViewModel.class);
        profileListViewModel.fetchData();
        MockProfileManager value = profileListViewModel.getPageData().getValue();
        View viewFindViewById = viewOnCreateView.findViewById(R$id.profile_picker_close_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "v.findViewById(R.id.profile_picker_close_button)");
        this.mCloseButton = (ImageView) viewFindViewById;
        setCloseButton();
        View viewFindViewById2 = viewOnCreateView.findViewById(R$id.profile_picker_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "v.findViewById(R.id.profile_picker_title)");
        setMTitle((TextView) viewFindViewById2);
        getMTitle().setText(R$string.profile_recommender_title);
        View viewFindViewById3 = viewOnCreateView.findViewById(R$id.profile_picker_sub_text);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "v.findViewById(R.id.profile_picker_sub_text)");
        setMSubText((TextView) viewFindViewById3);
        getMSubText().setText(R$string.profile_recommender_sub_text);
        View viewFindViewById4 = viewOnCreateView.findViewById(R$id.profile_picker_gridview);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "v.findViewById(R.id.profile_picker_gridview)");
        setMRecyclerView((RecyclerView) viewFindViewById4);
        View viewFindViewById5 = viewOnCreateView.findViewById(R$id.profile_picker_invite_link_view);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "v.findViewById(R.id.prof…_picker_invite_link_view)");
        RelativeLayout relativeLayout = (RelativeLayout) viewFindViewById5;
        this.mInviteLinkBox = relativeLayout;
        RelativeLayout relativeLayout2 = null;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mInviteLinkBox");
            relativeLayout = null;
        }
        relativeLayout.setVisibility(0);
        RelativeLayout relativeLayout3 = this.mInviteLinkBox;
        if (relativeLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mInviteLinkBox");
        } else {
            relativeLayout2 = relativeLayout3;
        }
        relativeLayout2.setOnClickListener(setSentInviteLink());
        Context context = viewOnCreateView.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "v.context");
        setMProfileAdapter(new ProfileRecommendationAdapter(value, context, getMStyleSheet(), R$dimen.spacing_125));
        RecyclerView mRecyclerView = getMRecyclerView();
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(getMProfileAdapter());
        }
        RecyclerView mRecyclerView2 = getMRecyclerView();
        if (mRecyclerView2 != null) {
            mRecyclerView2.setLayoutManager(new GridLayoutManager(viewOnCreateView.getContext(), getResources().getInteger(R$integer.grid_columns_2)));
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.spacing_24);
        CustomGridLayoutItemDecorator customGridLayoutItemDecorator = new CustomGridLayoutItemDecorator(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        RecyclerView mRecyclerView3 = getMRecyclerView();
        if (mRecyclerView3 != null) {
            mRecyclerView3.addItemDecoration(customGridLayoutItemDecorator);
        }
        return viewOnCreateView;
    }

    private final void setCloseButton() {
        ImageView imageView = this.mCloseButton;
        ImageView imageView2 = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCloseButton");
            imageView = null;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.identity.profiles.view.fragment.ProfileRecommendationFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileRecommendationFragment.m2137setCloseButton$lambda4(this.f$0, view);
            }
        });
        ImageView imageView3 = this.mCloseButton;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCloseButton");
        } else {
            imageView2 = imageView3;
        }
        imageView2.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setCloseButton$lambda-4, reason: not valid java name */
    public static final void m2137setCloseButton$lambda4(ProfileRecommendationFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        if (activity == null) {
            return;
        }
        NavigationHelper.navigateBackTo$default(new NavigationHelper(activity), null, 1, null);
    }
}
