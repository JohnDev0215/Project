package com.amazon.mp3.customerprofile;

import android.os.Bundle;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.mp3.customerprofile.fragment.CustomerProfileEditHandleFragment;
import com.amazon.mp3.customerprofile.fragment.CustomerProfileEditNameFragment;
import com.amazon.mp3.customerprofile.fragment.CustomerProfileEditPrivacyFragment;
import com.amazon.mp3.customerprofile.fragment.CustomerProfileFragment;
import com.amazon.mp3.customerprofile.fragment.CustomerProfileListsFragment;
import com.amazon.mp3.customerprofile.viewModels.EditProfileViewModel;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.music.views.library.models.CustomerProfileModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerProfileNavigation.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u000f\u0012\u0006\u0010\u0018\u001a\u00020\u0013¢\u0006\u0004\b\u0019\u0010\u001aJ\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0002J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0002H\u0002J\u0006\u0010\b\u001a\u00020\u0005J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0002J\u000e\u0010\u000b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\tJ\u000e\u0010\r\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\fJ\u000e\u0010\u000e\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\fJ\u000e\u0010\u000f\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\fJ\u000e\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0002J\u000e\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0002J\u000e\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0002R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001c"}, d2 = {"Lcom/amazon/mp3/customerprofile/CustomerProfileNavigation;", "", "", IntlConfiguration.ENDPOINT_T, "profileId", "", "loadBrushFragment", "loadProfileFragment", "loadProfile", "Lcom/amazon/mp3/customerprofile/viewModels/EditProfileViewModel;", "model", "loadEditProfile", "Lcom/amazon/music/views/library/models/CustomerProfileModel;", "loadEditProfileName", "loadEditProfileHandle", "loadEditProfilePrivacy", "loadFollowers", "loadFollowing", "loadPlaylists", "Lcom/amazon/mp3/activity/MusicHomeActivity;", "mMusicHomeActivity", "Lcom/amazon/mp3/activity/MusicHomeActivity;", "getMMusicHomeActivity", "()Lcom/amazon/mp3/activity/MusicHomeActivity;", "musicHomeActivity", "<init>", "(Lcom/amazon/mp3/activity/MusicHomeActivity;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CustomerProfileNavigation {
    private final MusicHomeActivity mMusicHomeActivity;

    public CustomerProfileNavigation(MusicHomeActivity musicHomeActivity) {
        Intrinsics.checkNotNullParameter(musicHomeActivity, "musicHomeActivity");
        this.mMusicHomeActivity = musicHomeActivity;
    }

    private final void loadBrushFragment(String endpoint, String profileId) {
        this.mMusicHomeActivity.requestHomeButtonAsBack();
        Bundle bundle = new Bundle();
        bundle.putString("brush_profileid_extra", profileId);
        this.mMusicHomeActivity.changeScreenFragment(new BrushFragmentNavigation.Builder(endpoint).withBundle(bundle).createFragment(), true, true, true);
    }

    private final void loadProfileFragment(String profileId) {
        Bundle bundle = new Bundle();
        bundle.putString("brush_profileid_extra", profileId);
        CustomerProfileFragment customerProfileFragment = new CustomerProfileFragment();
        customerProfileFragment.setArguments(bundle);
        this.mMusicHomeActivity.changeScreenFragment(customerProfileFragment, true, true, true);
    }

    public final void loadProfile() {
        loadProfile("");
    }

    public final void loadProfile(String profileId) {
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        loadProfileFragment(profileId);
    }

    public final void loadEditProfile(EditProfileViewModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        Bundle bundle = new Bundle();
        EditCustomerProfileFragment editCustomerProfileFragment = new EditCustomerProfileFragment(model);
        editCustomerProfileFragment.setArguments(bundle);
        this.mMusicHomeActivity.changeScreenFragment(editCustomerProfileFragment, true, true, true);
    }

    public final void loadEditProfileName(CustomerProfileModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        Bundle bundle = new Bundle();
        bundle.putSerializable("CustomerProfileModel", model);
        CustomerProfileEditNameFragment customerProfileEditNameFragment = new CustomerProfileEditNameFragment();
        customerProfileEditNameFragment.setArguments(bundle);
        this.mMusicHomeActivity.changeScreenFragment(customerProfileEditNameFragment, true, true, true);
    }

    public final void loadEditProfileHandle(CustomerProfileModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        Bundle bundle = new Bundle();
        bundle.putSerializable("CustomerProfileModel", model);
        CustomerProfileEditHandleFragment customerProfileEditHandleFragment = new CustomerProfileEditHandleFragment();
        customerProfileEditHandleFragment.setArguments(bundle);
        this.mMusicHomeActivity.changeScreenFragment(customerProfileEditHandleFragment, true, true, true);
    }

    public final void loadEditProfilePrivacy(CustomerProfileModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        Bundle bundle = new Bundle();
        bundle.putSerializable("CustomerProfileModel", model);
        CustomerProfileEditPrivacyFragment customerProfileEditPrivacyFragment = new CustomerProfileEditPrivacyFragment();
        customerProfileEditPrivacyFragment.setArguments(bundle);
        this.mMusicHomeActivity.changeScreenFragment(customerProfileEditPrivacyFragment, true, true, true);
    }

    public final void loadFollowers(String profileId) {
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        if (Feature.social_profiles_phase3_enabled.isEnabled()) {
            this.mMusicHomeActivity.changeScreenFragment(new CustomerProfileListsFragment(profileId, Companion.LIST_TYPE.FOLLOWERS), true, true, true);
        } else {
            loadBrushFragment(Intrinsics.stringPlus("profile/follower/", profileId), profileId);
        }
    }

    public final void loadFollowing(String profileId) {
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        if (Feature.social_profiles_phase3_enabled.isEnabled()) {
            this.mMusicHomeActivity.changeScreenFragment(new CustomerProfileListsFragment(profileId, Companion.LIST_TYPE.FOLLOWING), true, true, true);
        } else {
            loadBrushFragment(Intrinsics.stringPlus("profile/following/", profileId), profileId);
        }
    }

    public final void loadPlaylists(String profileId) {
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        loadBrushFragment(Intrinsics.stringPlus("profile/public-playlists/", profileId), profileId);
    }
}
