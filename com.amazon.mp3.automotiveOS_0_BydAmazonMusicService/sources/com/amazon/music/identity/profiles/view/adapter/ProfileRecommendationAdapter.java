package com.amazon.music.identity.profiles.view.adapter;

import android.content.Context;
import android.view.View;
import com.amazon.music.identity.profiles.R$string;
import com.amazon.music.identity.profiles.datasource.mockprofile.MockProfileManager;
import com.amazon.music.identity.profiles.datasource.mockprofile.ProfileBaseModel;
import com.amazon.music.views.library.styles.StyleSheet;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProfileRecommendationAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B+\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0014J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\tH\u0014R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/identity/profiles/view/adapter/ProfileRecommendationAdapter;", "Lcom/amazon/music/identity/profiles/view/adapter/ProfileAdapter;", "mockProfileManager", "Lcom/amazon/music/identity/profiles/datasource/mockprofile/MockProfileManager;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "profilePictureSizeID", "", "(Lcom/amazon/music/identity/profiles/datasource/mockprofile/MockProfileManager;Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;I)V", "getButtonOnclickListener", "Landroid/view/View$OnClickListener;", "getProfileOnClickListener", "pid", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ProfileRecommendationAdapter extends ProfileAdapter {
    private final MockProfileManager mockProfileManager;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getButtonOnclickListener$lambda-1, reason: not valid java name */
    public static final void m2133getButtonOnclickListener$lambda1(View view) {
    }

    @Override // com.amazon.music.identity.profiles.view.adapter.ProfileAdapter
    protected View.OnClickListener getButtonOnclickListener() {
        return new View.OnClickListener() { // from class: com.amazon.music.identity.profiles.view.adapter.ProfileRecommendationAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileRecommendationAdapter.m2133getButtonOnclickListener$lambda1(view);
            }
        };
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileRecommendationAdapter(MockProfileManager mockProfileManager, Context context, StyleSheet styleSheet, int i) {
        super((ArrayList) (mockProfileManager == null ? null : mockProfileManager.getInactiveProfiles()), context, styleSheet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mockProfileManager = mockProfileManager;
        ArrayList<ProfileBaseModel> mList = getMList();
        if (mList == null) {
            return;
        }
        ProfileAdapter.createAddButton$default(this, mList, getMContext().getResources().getString(R$string.profile_button_text), null, 4, null);
    }

    @Override // com.amazon.music.identity.profiles.view.adapter.ProfileAdapter
    protected View.OnClickListener getProfileOnClickListener(final int pid) {
        return new View.OnClickListener() { // from class: com.amazon.music.identity.profiles.view.adapter.ProfileRecommendationAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileRecommendationAdapter.m2134getProfileOnClickListener$lambda2(this.f$0, pid, view);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getProfileOnClickListener$lambda-2, reason: not valid java name */
    public static final void m2134getProfileOnClickListener$lambda2(ProfileRecommendationAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MockProfileManager mockProfileManager = this$0.mockProfileManager;
        if (mockProfileManager == null) {
            return;
        }
        mockProfileManager.setCurrentInactiveProfile(i);
    }
}
