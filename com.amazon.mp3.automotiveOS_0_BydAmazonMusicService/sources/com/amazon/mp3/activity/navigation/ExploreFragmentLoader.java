package com.amazon.mp3.activity.navigation;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.skyfire.ui.IntentKeys;
import com.amazon.music.skyfire.ui.fragment.TemplateFragment;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExploreFragmentLoader.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0014J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0014J\u001a\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\"\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/activity/navigation/ExploreFragmentLoader;", "Lcom/amazon/mp3/activity/navigation/FragmentLoader;", "fragmentActivity", "Landroidx/fragment/app/FragmentActivity;", "fragmentController", "Lcom/amazon/mp3/activity/navigation/FragmentController;", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/activity/navigation/FragmentController;)V", "navigateFromIntent", "", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "navigateFromMenu", "menuId", "", "shouldNavigate", ParserUtil.TAG_QUERY_PARAM_NAME, "", "extras", "Landroid/os/Bundle;", "startExploreFragment", "", "fragmentClass", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExploreFragmentLoader extends FragmentLoader {
    @Override // com.amazon.mp3.activity.navigation.FragmentLoader
    protected boolean navigateFromMenu(int menuId) {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExploreFragmentLoader(FragmentActivity fragmentActivity, FragmentController fragmentController) {
        super(fragmentActivity, fragmentController);
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        Intrinsics.checkNotNullParameter(fragmentController, "fragmentController");
    }

    @Override // com.amazon.mp3.activity.navigation.FragmentLoader
    protected boolean navigateFromIntent(Intent intent) throws IllegalAccessException, InstantiationException {
        Intrinsics.checkNotNullParameter(intent, "intent");
        String stringExtra = intent.getStringExtra(IntentKeys.EXTRA_FRAGMENT);
        if (stringExtra == null) {
            return false;
        }
        String stringExtra2 = intent.getStringExtra(IntentKeys.EXTRA_TAG);
        if (stringExtra2 == null) {
            stringExtra2 = stringExtra;
        }
        Bundle bundleExtra = intent.getBundleExtra(IntentKeys.EXTRA_EXTRAS);
        if (!shouldNavigate(stringExtra2, bundleExtra)) {
            return false;
        }
        startExploreFragment(stringExtra, stringExtra2, bundleExtra);
        return true;
    }

    private final void startExploreFragment(String fragmentClass, String tag, Bundle extras) throws IllegalAccessException, InstantiationException {
        Object objNewInstance = Class.forName(fragmentClass).newInstance();
        if (objNewInstance == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.skyfire.ui.fragment.TemplateFragment<*>");
        }
        TemplateFragment templateFragment = (TemplateFragment) objNewInstance;
        templateFragment.setArguments(extras);
        FragmentManager supportFragmentManager = this.mFragmentActivity.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "mFragmentActivity.supportFragmentManager");
        Fragment fragmentFindFragmentById = supportFragmentManager.findFragmentById(R.id.music_screen_container);
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.music_screen_container, templateFragment, tag);
        if (fragmentFindFragmentById != null) {
            fragmentTransactionBeginTransaction.addToBackStack(tag);
        }
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    private final boolean shouldNavigate(String tag, Bundle extras) {
        Bundle arguments;
        String string = null;
        Integer numValueOf = extras == null ? null : Integer.valueOf(extras.getInt(TemplateFragment.ARG_NAVIGATION_BEHAVIOR));
        if (numValueOf == null || numValueOf.intValue() != 1) {
            return true;
        }
        List<Fragment> fragments = this.mFragmentActivity.getSupportFragmentManager().getFragments();
        Intrinsics.checkNotNullExpressionValue(fragments, "mFragmentActivity.supportFragmentManager.fragments");
        Fragment fragment = (Fragment) CollectionsKt___CollectionsKt.firstOrNull((List) fragments);
        if (fragment != null && (arguments = fragment.getArguments()) != null) {
            string = arguments.getString(TemplateFragment.ARG_SCREEN_ID);
        }
        return !Intrinsics.areEqual(tag, string);
    }
}
