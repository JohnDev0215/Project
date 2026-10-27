package com.amazon.mp3.customerlookup;

import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.navigation.FragmentController;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.customerprofile.CustomerProfileNavigation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: CustomerLookupNavigation.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0018\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/customerlookup/CustomerLookupNavigation;", "", "", "profileId", "", "loadCustomerProfile", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "TAG", "Ljava/lang/String;", "Lcom/amazon/mp3/activity/navigation/FragmentController;", "fragmentController", "Lcom/amazon/mp3/activity/navigation/FragmentController;", "<init>", "(Landroidx/fragment/app/FragmentActivity;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CustomerLookupNavigation {
    private String TAG;
    private final FragmentActivity activity;
    private FragmentController fragmentController;

    public CustomerLookupNavigation(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
        this.TAG = Reflection.getOrCreateKotlinClass(CustomerLookupNavigation.class).getSimpleName();
        this.fragmentController = new FragmentController(activity, R.id.music_screen_container);
    }

    public final void loadCustomerProfile(String profileId) {
        if (profileId == null) {
            return;
        }
        new CustomerProfileNavigation((MusicHomeActivity) this.activity).loadProfile(profileId);
    }
}
