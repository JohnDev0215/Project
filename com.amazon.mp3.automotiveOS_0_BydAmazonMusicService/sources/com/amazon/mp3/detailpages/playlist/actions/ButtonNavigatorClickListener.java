package com.amazon.mp3.detailpages.playlist.actions;

import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.detailpages.playlist.playlistSongSearch.PlaylistSongSearchHandler;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.music.views.library.models.enums.TargetType;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ButtonNavigatorClickListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014BI\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0005¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0002J&\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\rH\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/actions/ButtonNavigatorClickListener;", "Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "getCollectionID", "Lkotlin/Function0;", "", "luid", "getPlaylistName", "getPlaylistUri", "Landroid/net/Uri;", "(Landroidx/fragment/app/FragmentActivity;Lkotlin/jvm/functions/Function0;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "addSongsToPlaylist", "", "onClick", "target", "viewId", "targetType", "Lcom/amazon/music/views/library/models/enums/TargetType;", "showUpsell", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ButtonNavigatorClickListener implements BrushUriClickListenerProvider {
    private final FragmentActivity activity;
    private final Function0<String> getCollectionID;
    private final Function0<String> getPlaylistName;
    private final Function0<Uri> getPlaylistUri;
    private final String luid;

    /* JADX WARN: Multi-variable type inference failed */
    public ButtonNavigatorClickListener(FragmentActivity fragmentActivity, Function0<String> getCollectionID, String str, Function0<String> getPlaylistName, Function0<? extends Uri> getPlaylistUri) {
        Intrinsics.checkNotNullParameter(getCollectionID, "getCollectionID");
        Intrinsics.checkNotNullParameter(getPlaylistName, "getPlaylistName");
        Intrinsics.checkNotNullParameter(getPlaylistUri, "getPlaylistUri");
        this.activity = fragmentActivity;
        this.getCollectionID = getCollectionID;
        this.luid = str;
        this.getPlaylistName = getPlaylistName;
        this.getPlaylistUri = getPlaylistUri;
    }

    @Override // com.amazon.music.views.library.providers.BrushUriClickListenerProvider
    public void onClick(String target, String viewId, TargetType targetType) {
        if (Intrinsics.areEqual(target, "ShowUpsell")) {
            showUpsell();
        } else if (Intrinsics.areEqual(target, "AddSongs")) {
            addSongsToPlaylist();
        }
    }

    private final void showUpsell() {
        UpsellUtil.showBlockingUpsell(this.activity, this.getCollectionID.invoke(), UpsellReason.PERSISTENT_UPSELL, UpsellSourceEntity.PLAYLIST);
    }

    private final void addSongsToPlaylist() {
        FragmentActivity fragmentActivity = this.activity;
        if (fragmentActivity == null) {
            return;
        }
        if (AmazonApplication.getCapabilities().isPlaylistSongSearchEnabled() && ConnectivityUtil.hasAnyInternetConnection(fragmentActivity.getApplicationContext())) {
            String str = this.luid;
            if (str == null) {
                str = "";
            }
            String strInvoke = this.getPlaylistName.invoke();
            new PlaylistSongSearchHandler(fragmentActivity, str, strInvoke != null ? strInvoke : "").startPlaylistSongSearchActivty();
            return;
        }
        PlaylistUtil.startEditPlaylist(fragmentActivity, PlaylistUtil.withAppendedScratchArgument(this.getPlaylistUri.invoke()), true);
    }
}
