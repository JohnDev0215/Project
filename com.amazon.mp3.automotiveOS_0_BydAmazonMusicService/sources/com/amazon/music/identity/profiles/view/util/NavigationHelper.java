package com.amazon.music.identity.profiles.view.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.identity.profiles.view.fragment.ProfilePickerFragment;
import com.amazon.music.identity.profiles.view.fragment.ProfileRecommendationFragment;
import com.amazon.music.identity.profiles.view.fragment.ProfileSettingFragment;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NavigationHelper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u0012\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u0016\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/identity/profiles/view/util/NavigationHelper;", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "ft", "Landroidx/fragment/app/FragmentTransaction;", "getFragmentBy", "Landroidx/fragment/app/Fragment;", ParserUtil.TAG_QUERY_PARAM_NAME, "", "navigateBackTo", "", "navigateTo", "viewId", "", "Companion", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NavigationHelper {
    private final FragmentActivity activity;
    private final FragmentManager fragmentManager;
    private final FragmentTransaction ft;

    public NavigationHelper(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
        FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "activity.supportFragmentManager");
        this.fragmentManager = supportFragmentManager;
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "fragmentManager.beginTransaction()");
        this.ft = fragmentTransactionBeginTransaction;
        fragmentTransactionBeginTransaction.setReorderingAllowed(true);
    }

    public final void navigateTo(int viewId, String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        this.ft.replace(viewId, getFragmentBy(tag), tag);
        this.ft.addToBackStack(tag);
        this.ft.commit();
    }

    public static /* synthetic */ void navigateBackTo$default(NavigationHelper navigationHelper, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        navigationHelper.navigateBackTo(str);
    }

    public final void navigateBackTo(String tag) {
        Unit unit;
        if (tag == null) {
            unit = null;
        } else {
            this.fragmentManager.popBackStack(tag, 0);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            this.fragmentManager.popBackStack();
        }
    }

    public final Fragment getFragmentBy(String tag) {
        return Intrinsics.areEqual(tag, ProfilePickerFragment.INSTANCE.getTAG()) ? new ProfilePickerFragment() : Intrinsics.areEqual(tag, ProfileRecommendationFragment.Companion.getTAG()) ? new ProfileRecommendationFragment() : Intrinsics.areEqual(tag, ProfileSettingFragment.INSTANCE.getTAG()) ? new ProfileSettingFragment() : new ProfilePickerFragment();
    }
}
