package com.amazon.music.identity.profiles.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.identity.profiles.R$dimen;
import com.amazon.music.identity.profiles.R$id;
import com.amazon.music.identity.profiles.R$layout;
import com.amazon.music.identity.profiles.datasource.mockprofile.MockProfileManager;
import com.amazon.music.identity.profiles.datasource.mockprofile.ProfileBaseModel;
import com.amazon.music.identity.profiles.view.adapter.ProfileSettingAdapter;
import com.amazon.music.identity.profiles.viewmodel.ProfileListViewModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.CustomGridLayoutItemDecorator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProfileSettingFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J$\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0010H\u0002J\u0010\u0010\u0019\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/identity/profiles/view/fragment/ProfileSettingFragment;", "Landroidx/fragment/app/Fragment;", "()V", "mProfileAdapter", "Lcom/amazon/music/identity/profiles/view/adapter/ProfileSettingAdapter;", "mRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "mStyleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "handleConnectDeviceClick", "", "context", "Landroid/content/Context;", "handleMyProfileClick", "handleSettingClick", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setAllFieldClickable", "v", "setupBackButton", "Companion", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ProfileSettingFragment extends Fragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = ProfileSettingFragment.class.getSimpleName();
    private ProfileSettingAdapter mProfileAdapter;
    private RecyclerView mRecyclerView;
    private StyleSheet mStyleSheet;

    private final void handleConnectDeviceClick(Context context) {
    }

    private final void handleMyProfileClick(Context context) {
    }

    private final void handleSettingClick(Context context) {
    }

    /* JADX INFO: compiled from: ProfileSettingFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/identity/profiles/view/fragment/ProfileSettingFragment$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return ProfileSettingFragment.TAG;
        }
    }

    public ProfileSettingFragment() {
        super(R$layout.profile_setting);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FontStyle fontStyle;
        FontStyle fontStyle2;
        ProfileBaseModel profileBaseModelM2127getCurrentProfile;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View v = inflater.inflate(R$layout.profile_setting, container, false);
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
        ProfileListViewModel profileListViewModel = (ProfileListViewModel) new ViewModelProvider(fragmentActivityRequireActivity).get(ProfileListViewModel.class);
        profileListViewModel.fetchData();
        MockProfileManager value = profileListViewModel.getPageData().getValue();
        View viewFindViewById = v.findViewById(R$id.recycler_view_profile);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "v.findViewById(R.id.recycler_view_profile)");
        this.mRecyclerView = (RecyclerView) viewFindViewById;
        TextView mHeaderName = (TextView) v.findViewById(R$id.header_name);
        RecyclerView recyclerView = null;
        if (mHeaderName != null) {
            mHeaderName.setText((value == null || (profileBaseModelM2127getCurrentProfile = value.m2127getCurrentProfile()) == null) ? null : profileBaseModelM2127getCurrentProfile.getFullName());
        }
        StyleSheet styleSheet = this.mStyleSheet;
        if (styleSheet != null && (fontStyle2 = styleSheet.getFontStyle(FontStyleKey.PRIMARY_TITLE)) != null) {
            FontUtil fontUtil = FontUtil.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(mHeaderName, "mHeaderName");
            fontUtil.applyFontStyle(mHeaderName, fontStyle2);
        }
        TextView mEditProfile = (TextView) v.findViewById(R$id.edit_profile);
        StyleSheet styleSheet2 = this.mStyleSheet;
        if (styleSheet2 != null && (fontStyle = styleSheet2.getFontStyle(FontStyleKey.PRIMARY_2)) != null) {
            FontUtil fontUtil2 = FontUtil.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(mEditProfile, "mEditProfile");
            fontUtil2.applyFontStyle(mEditProfile, fontStyle);
        }
        Intrinsics.checkNotNullExpressionValue(v, "v");
        setAllFieldClickable(v);
        setupBackButton(v);
        Context context = v.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "v.context");
        this.mProfileAdapter = new ProfileSettingAdapter(value, context, this.mStyleSheet, R$dimen.spacing_90);
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
            recyclerView2 = null;
        }
        ProfileSettingAdapter profileSettingAdapter = this.mProfileAdapter;
        if (profileSettingAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProfileAdapter");
            profileSettingAdapter = null;
        }
        recyclerView2.setAdapter(profileSettingAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(v.getContext(), 0, false));
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.spacer_4);
        CustomGridLayoutItemDecorator customGridLayoutItemDecorator = new CustomGridLayoutItemDecorator(0, dimensionPixelSize, 0, dimensionPixelSize);
        RecyclerView recyclerView3 = this.mRecyclerView;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
        } else {
            recyclerView = recyclerView3;
        }
        recyclerView.addItemDecoration(customGridLayoutItemDecorator);
        return v;
    }

    private final void setupBackButton(View v) {
        ImageView imageView = (ImageView) v.findViewById(R$id.button_back);
        imageView.setVisibility(0);
        imageView.setClickable(true);
        imageView.setEnabled(true);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.identity.profiles.view.fragment.ProfileSettingFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileSettingFragment.m2143setupBackButton$lambda5(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupBackButton$lambda-5, reason: not valid java name */
    public static final void m2143setupBackButton$lambda5(ProfileSettingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        if (activity == null) {
            return;
        }
        activity.finish();
    }

    private final void setAllFieldClickable(final View v) {
        StyleSheet styleSheet = this.mStyleSheet;
        FontStyle fontStyle = styleSheet == null ? null : styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        TextView settingItem = (TextView) v.findViewById(R$id.settings);
        if (fontStyle != null) {
            FontUtil fontUtil = FontUtil.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(settingItem, "settingItem");
            fontUtil.applyFontStyle(settingItem, fontStyle);
        }
        settingItem.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.identity.profiles.view.fragment.ProfileSettingFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileSettingFragment.m2141setAllFieldClickable$lambda7(this.f$0, v, view);
            }
        });
        TextView connectDeviceItem = (TextView) v.findViewById(R$id.connect_device);
        if (fontStyle != null) {
            FontUtil fontUtil2 = FontUtil.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(connectDeviceItem, "connectDeviceItem");
            fontUtil2.applyFontStyle(connectDeviceItem, fontStyle);
        }
        connectDeviceItem.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.identity.profiles.view.fragment.ProfileSettingFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileSettingFragment.m2142setAllFieldClickable$lambda9(this.f$0, v, view);
            }
        });
        TextView myProfileItem = (TextView) v.findViewById(R$id.my_profile);
        if (fontStyle != null) {
            FontUtil fontUtil3 = FontUtil.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(myProfileItem, "myProfileItem");
            fontUtil3.applyFontStyle(myProfileItem, fontStyle);
        }
        myProfileItem.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.identity.profiles.view.fragment.ProfileSettingFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileSettingFragment.m2140setAllFieldClickable$lambda11(this.f$0, v, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setAllFieldClickable$lambda-7, reason: not valid java name */
    public static final void m2141setAllFieldClickable$lambda7(ProfileSettingFragment this$0, View v, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(v, "$v");
        Context context = v.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "v.context");
        this$0.handleSettingClick(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setAllFieldClickable$lambda-9, reason: not valid java name */
    public static final void m2142setAllFieldClickable$lambda9(ProfileSettingFragment this$0, View v, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(v, "$v");
        Context context = v.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "v.context");
        this$0.handleConnectDeviceClick(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setAllFieldClickable$lambda-11, reason: not valid java name */
    public static final void m2140setAllFieldClickable$lambda11(ProfileSettingFragment this$0, View v, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(v, "$v");
        Context context = v.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "v.context");
        this$0.handleMyProfileClick(context);
    }
}
