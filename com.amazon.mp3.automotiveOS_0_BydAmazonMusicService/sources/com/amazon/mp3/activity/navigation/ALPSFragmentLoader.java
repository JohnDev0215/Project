package com.amazon.mp3.activity.navigation;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.alps.liveevent.LiveEventDetailFragment;
import com.amazon.music.destination.parser.ParserUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ALPSFragmentLoader.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0014J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\"\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/activity/navigation/ALPSFragmentLoader;", "Lcom/amazon/mp3/activity/navigation/FragmentLoader;", "fragmentActivity", "Landroidx/fragment/app/FragmentActivity;", "fragmentController", "Lcom/amazon/mp3/activity/navigation/FragmentController;", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/activity/navigation/FragmentController;)V", "handleLiveEventDetailFragment", "", "fragment", "Landroidx/fragment/app/Fragment;", ParserUtil.TAG_QUERY_PARAM_NAME, "", "navigateFromIntent", "", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "navigateFromMenu", "menuId", "", "startALPSFragment", "fragmentClass", "extras", "Landroid/os/Bundle;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ALPSFragmentLoader extends FragmentLoader {
    @Override // com.amazon.mp3.activity.navigation.FragmentLoader
    protected boolean navigateFromMenu(int menuId) {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ALPSFragmentLoader(FragmentActivity fragmentActivity, FragmentController fragmentController) {
        super(fragmentActivity, fragmentController);
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        Intrinsics.checkNotNullParameter(fragmentController, "fragmentController");
    }

    @Override // com.amazon.mp3.activity.navigation.FragmentLoader
    protected boolean navigateFromIntent(Intent intent) throws IllegalAccessException, InstantiationException {
        Intrinsics.checkNotNullParameter(intent, "intent");
        String stringExtra = intent.getStringExtra("com.amazon.music.alps.ui.Fragment");
        if (stringExtra == null) {
            return false;
        }
        String stringExtra2 = intent.getStringExtra("com.amazon.music.alps.ui.Tag");
        if (stringExtra2 == null) {
            stringExtra2 = stringExtra;
        }
        startALPSFragment(stringExtra, stringExtra2, intent.getBundleExtra("com.amazon.music.alps.ui.Extras"));
        return true;
    }

    private final void startALPSFragment(String fragmentClass, String tag, Bundle extras) throws IllegalAccessException, InstantiationException {
        Object objNewInstance = Class.forName(fragmentClass).newInstance();
        if (objNewInstance == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.fragment.app.Fragment");
        }
        Fragment fragment = (Fragment) objNewInstance;
        fragment.setArguments(extras);
        if (fragment instanceof LiveEventDetailFragment) {
            handleLiveEventDetailFragment(fragment, tag);
        }
    }

    private final void handleLiveEventDetailFragment(Fragment fragment, String tag) {
        FragmentManager supportFragmentManager = this.mFragmentActivity.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "mFragmentActivity.supportFragmentManager");
        Fragment fragmentFindFragmentById = supportFragmentManager.findFragmentById(R.id.music_screen_container);
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.music_screen_container, fragment, tag);
        if (fragmentFindFragmentById != null) {
            fragmentTransactionBeginTransaction.addToBackStack(tag);
        }
        fragmentTransactionBeginTransaction.commit();
    }
}
