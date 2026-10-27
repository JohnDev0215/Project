package com.amazon.mp3.providers;

import android.content.Context;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.customerprofile.CustomerProfileMetrics;
import com.amazon.mp3.customerprofile.CustomerProfileNavigation;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.views.library.providers.UserPlaylistDetailProfileLinkProvider;
import kotlin.Metadata;

/* JADX INFO: compiled from: UserPlaylistDetailProfileLinkHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u000b\u0010\fJ\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/providers/UserPlaylistDetailProfileLinkHandler;", "Lcom/amazon/music/views/library/providers/UserPlaylistDetailProfileLinkProvider;", "", "profileId", "", "navigateToProfile", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class UserPlaylistDetailProfileLinkHandler implements UserPlaylistDetailProfileLinkProvider {
    private final Context context;

    public UserPlaylistDetailProfileLinkHandler(Context context) {
        this.context = context;
    }

    @Override // com.amazon.music.views.library.providers.UserPlaylistDetailProfileLinkProvider
    public void navigateToProfile(String profileId) {
        if (this.context instanceof MusicHomeActivity) {
            if (profileId == null || profileId.length() == 0) {
                return;
            }
            CustomerProfileMetrics.emitUiClickMetric(ActionType.GO_CUSTOMER_PROFILE_FROM_PLAYLIST);
            new CustomerProfileNavigation((MusicHomeActivity) this.context).loadProfile(profileId);
        }
    }
}
