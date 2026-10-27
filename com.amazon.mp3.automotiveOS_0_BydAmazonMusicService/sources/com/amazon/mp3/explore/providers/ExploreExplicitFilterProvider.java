package com.amazon.mp3.explore.providers;

import android.content.Context;
import android.content.DialogInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.dialog.ExplicitFilterDialogBuilder;
import com.amazon.mp3.elf.ExplicitLanguageManager;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.music.explore.providers.ExplicitFilterProvider;
import com.amazon.music.skyfire.ui.fragment.TemplateFragment;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExploreExplicitFilterProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J,\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/explore/providers/ExploreExplicitFilterProvider;", "Lcom/amazon/music/explore/providers/ExplicitFilterProvider;", "()V", "isExplicitContentFiltered", "", "openBlockingExplicitFilterDialog", "", "context", "Landroid/content/Context;", "callback", "Lcom/amazon/music/explore/providers/ExplicitFilterProvider$ExplicitFilterDialogCallback;", "title", "", "message", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExploreExplicitFilterProvider implements ExplicitFilterProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final void refreshTemplateFragmentIfPresent(Context context) {
        INSTANCE.refreshTemplateFragmentIfPresent(context);
    }

    /* JADX INFO: compiled from: ExploreExplicitFilterProvider.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/explore/providers/ExploreExplicitFilterProvider$Companion;", "", "()V", "refreshTemplateFragmentIfPresent", "", "context", "Landroid/content/Context;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void refreshTemplateFragmentIfPresent(Context context) {
            FragmentActivity fragmentActivity = context instanceof FragmentActivity ? (FragmentActivity) context : null;
            if (fragmentActivity == null) {
                return;
            }
            List<Fragment> fragments = fragmentActivity.getSupportFragmentManager().getFragments();
            Intrinsics.checkNotNullExpressionValue(fragments, "activity\n               …               .fragments");
            Fragment fragment = (Fragment) CollectionsKt___CollectionsKt.firstOrNull((List) fragments);
            if (fragment instanceof TemplateFragment) {
                ((TemplateFragment) fragment).refresh();
            }
        }
    }

    @Override // com.amazon.music.explore.providers.ExplicitFilterProvider
    public boolean isExplicitContentFiltered() {
        return AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled();
    }

    @Override // com.amazon.music.explore.providers.ExplicitFilterProvider
    public void openBlockingExplicitFilterDialog(Context context, ExplicitFilterProvider.ExplicitFilterDialogCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        openBlockingExplicitFilterDialog(context, callback, null, null);
    }

    @Override // com.amazon.music.explore.providers.ExplicitFilterProvider
    public void openBlockingExplicitFilterDialog(final Context context, final ExplicitFilterProvider.ExplicitFilterDialogCallback callback, String title, String message) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        ExplicitFilterDialogBuilder.builder(context).withContentUnavailable().withExplicitFilterEnabled(true).withTitle(title).withMessage(message).withPositiveButtonOnClickListener(new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.explore.providers.ExploreExplicitFilterProvider$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ExploreExplicitFilterProvider.m973openBlockingExplicitFilterDialog$lambda0(context, callback, dialogInterface, i);
            }
        }).withNegativeButtonOnClickListener(new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.explore.providers.ExploreExplicitFilterProvider$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ExploreExplicitFilterProvider.m974openBlockingExplicitFilterDialog$lambda1(callback, dialogInterface, i);
            }
        }).build().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: openBlockingExplicitFilterDialog$lambda-0, reason: not valid java name */
    public static final void m973openBlockingExplicitFilterDialog$lambda0(Context context, ExplicitFilterProvider.ExplicitFilterDialogCallback callback, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        if (ConnectivityUtil.checkConnectivityAndShowDialog(context)) {
            new ExplicitLanguageManager(context).setExplicitFilterSetting(false, null);
            callback.onDisabled();
        } else {
            callback.onEnabled();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: openBlockingExplicitFilterDialog$lambda-1, reason: not valid java name */
    public static final void m974openBlockingExplicitFilterDialog$lambda1(ExplicitFilterProvider.ExplicitFilterDialogCallback callback, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(callback, "$callback");
        callback.onEnabled();
    }
}
