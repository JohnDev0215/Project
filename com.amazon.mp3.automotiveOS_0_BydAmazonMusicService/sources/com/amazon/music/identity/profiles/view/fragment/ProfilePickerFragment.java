package com.amazon.music.identity.profiles.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.identity.profiles.R$dimen;
import com.amazon.music.identity.profiles.R$integer;
import com.amazon.music.identity.profiles.R$string;
import com.amazon.music.identity.profiles.datasource.mockprofile.MockProfileManager;
import com.amazon.music.identity.profiles.view.adapter.ProfilePickerAdapter;
import com.amazon.music.identity.profiles.viewmodel.ProfileListViewModel;
import com.amazon.ui.foundations.views.CustomGridLayoutItemDecorator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProfilePickerFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/music/identity/profiles/view/fragment/ProfilePickerFragment;", "Lcom/amazon/music/identity/profiles/view/fragment/ProfileListFragment;", "()V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ProfilePickerFragment extends ProfileListFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = ProfilePickerFragment.class.getSimpleName();

    /* JADX INFO: compiled from: ProfilePickerFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/identity/profiles/view/fragment/ProfilePickerFragment$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return ProfilePickerFragment.TAG;
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
        TextView mTitle = getMTitle();
        if (mTitle != null) {
            mTitle.setText(R$string.profile_picker_title);
        }
        TextView mSubText = getMSubText();
        if (mSubText != null) {
            mSubText.setText(R$string.profile_picker_sub_text);
        }
        Context context = viewOnCreateView.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "v.context");
        setMProfileAdapter(new ProfilePickerAdapter(value, context, getMStyleSheet(), R$dimen.spacing_125));
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
}
