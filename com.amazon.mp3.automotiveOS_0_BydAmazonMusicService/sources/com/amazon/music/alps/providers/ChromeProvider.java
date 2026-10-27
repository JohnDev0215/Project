package com.amazon.music.alps.providers;

import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.music.views.library.views.presentation.TranslucentActionBarDecorator;
import kotlin.Metadata;

/* JADX INFO: compiled from: ChromeProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J'\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0007H&¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/amazon/music/alps/providers/ChromeProvider;", "", "createTranslucentActionBarDecorator", "Lcom/amazon/music/views/library/views/presentation/TranslucentActionBarDecorator;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "showOverflowButton", "", "showProfileButton", "(Landroidx/fragment/app/FragmentActivity;ZLjava/lang/Boolean;)Lcom/amazon/music/views/library/views/presentation/TranslucentActionBarDecorator;", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ChromeProvider {
    TranslucentActionBarDecorator createTranslucentActionBarDecorator(FragmentActivity activity, boolean showOverflowButton, Boolean showProfileButton);
}
