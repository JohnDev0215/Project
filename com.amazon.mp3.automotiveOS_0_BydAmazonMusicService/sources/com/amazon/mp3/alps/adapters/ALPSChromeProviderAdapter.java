package com.amazon.mp3.alps.adapters;

import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.music.alps.providers.ChromeProvider;
import com.amazon.music.views.library.views.presentation.TranslucentActionBarDecorator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ALPSChromeProviderAdapter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J'\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/alps/adapters/ALPSChromeProviderAdapter;", "Lcom/amazon/music/alps/providers/ChromeProvider;", "chromeProvider", "Lcom/amazon/music/skyfire/ui/providers/ChromeProvider;", "(Lcom/amazon/music/skyfire/ui/providers/ChromeProvider;)V", "createTranslucentActionBarDecorator", "Lcom/amazon/music/views/library/views/presentation/TranslucentActionBarDecorator;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "showOverflowButton", "", "showProfileButton", "(Landroidx/fragment/app/FragmentActivity;ZLjava/lang/Boolean;)Lcom/amazon/music/views/library/views/presentation/TranslucentActionBarDecorator;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ALPSChromeProviderAdapter implements ChromeProvider {
    private final com.amazon.music.skyfire.ui.providers.ChromeProvider chromeProvider;

    public ALPSChromeProviderAdapter(com.amazon.music.skyfire.ui.providers.ChromeProvider chromeProvider) {
        Intrinsics.checkNotNullParameter(chromeProvider, "chromeProvider");
        this.chromeProvider = chromeProvider;
    }

    @Override // com.amazon.music.alps.providers.ChromeProvider
    public TranslucentActionBarDecorator createTranslucentActionBarDecorator(FragmentActivity activity, boolean showOverflowButton, Boolean showProfileButton) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return this.chromeProvider.createTranslucentActionBarDecorator(activity, showOverflowButton, showProfileButton);
    }
}
