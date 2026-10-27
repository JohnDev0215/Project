package com.amazon.music.identity.profiles.view.adapter;

import android.content.Context;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.amazon.music.identity.profiles.R$id;
import com.amazon.music.identity.profiles.R$string;
import com.amazon.music.identity.profiles.datasource.mockprofile.MockProfileManager;
import com.amazon.music.identity.profiles.datasource.mockprofile.ProfileBaseModel;
import com.amazon.music.identity.profiles.view.fragment.ProfileRecommendationFragment;
import com.amazon.music.identity.profiles.view.util.NavigationHelper;
import com.amazon.music.views.library.styles.StyleSheet;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProfileSettingAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B-\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0004¢\u0006\u0004\b\u000f\u0010\u0010J\b\u0010\u0003\u001a\u00020\u0002H\u0014J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0014R\u0016\u0010\b\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/identity/profiles/view/adapter/ProfileSettingAdapter;", "Lcom/amazon/music/identity/profiles/view/adapter/ProfileAdapter;", "Landroid/view/View$OnClickListener;", "getButtonOnclickListener", "", "pid", "getProfileOnClickListener", "Lcom/amazon/music/identity/profiles/datasource/mockprofile/MockProfileManager;", "mockProfileManager", "Lcom/amazon/music/identity/profiles/datasource/mockprofile/MockProfileManager;", "Landroid/content/Context;", "context", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "profilePictureSizeID", "<init>", "(Lcom/amazon/music/identity/profiles/datasource/mockprofile/MockProfileManager;Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;I)V", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0})
public final class ProfileSettingAdapter extends ProfileAdapter {
    private final MockProfileManager mockProfileManager;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileSettingAdapter(MockProfileManager mockProfileManager, Context context, StyleSheet styleSheet, int i) {
        super((ArrayList) (mockProfileManager == null ? null : mockProfileManager.getActiveProfiles()), context, styleSheet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mockProfileManager = mockProfileManager;
        ArrayList<ProfileBaseModel> mList = getMList();
        if (mList == null) {
            return;
        }
        ProfileAdapter.createAddButton$default(this, mList, getMContext().getResources().getString(R$string.profile_setting_button_text), null, 4, null);
    }

    @Override // com.amazon.music.identity.profiles.view.adapter.ProfileAdapter
    protected View.OnClickListener getButtonOnclickListener() {
        return new View.OnClickListener() { // from class: com.amazon.music.identity.profiles.view.adapter.ProfileSettingAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileSettingAdapter.m2135getButtonOnclickListener$lambda1(this.f$0, view);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getButtonOnclickListener$lambda-1, reason: not valid java name */
    public static final void m2135getButtonOnclickListener$lambda1(ProfileSettingAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        NavigationHelper navigationHelper = new NavigationHelper((FragmentActivity) this$0.getMContext());
        int i = R$id.profile_fragment_container;
        String tag = ProfileRecommendationFragment.INSTANCE.getTAG();
        Intrinsics.checkNotNullExpressionValue(tag, "ProfileRecommendationFragment.TAG");
        navigationHelper.navigateTo(i, tag);
    }

    @Override // com.amazon.music.identity.profiles.view.adapter.ProfileAdapter
    protected View.OnClickListener getProfileOnClickListener(final int pid) {
        return new View.OnClickListener() { // from class: com.amazon.music.identity.profiles.view.adapter.ProfileSettingAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileSettingAdapter.m2136getProfileOnClickListener$lambda2(this.f$0, pid, view);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getProfileOnClickListener$lambda-2, reason: not valid java name */
    public static final void m2136getProfileOnClickListener$lambda2(ProfileSettingAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MockProfileManager mockProfileManager = this$0.mockProfileManager;
        if (mockProfileManager != null) {
            mockProfileManager.setCurrentProfile(i);
        }
        ((FragmentActivity) this$0.getMContext()).finish();
    }
}
