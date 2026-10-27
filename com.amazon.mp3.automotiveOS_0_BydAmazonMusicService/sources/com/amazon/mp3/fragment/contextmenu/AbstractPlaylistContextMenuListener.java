package com.amazon.mp3.fragment.contextmenu;

import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.detailpages.playlist.actions.DeleteUserPlaylistAction;
import com.amazon.mp3.detailpages.playlist.actions.PlaylistUnfollowAction;
import com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.playlist.CatalogPlaylist;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AbstractPlaylistContextMenuListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\b\u0010\u0003\u001a\u00020\u0002H\u0016J&\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/AbstractPlaylistContextMenuListener;", "Lcom/amazon/mp3/fragment/contextmenu/PlaylistContextMenuProvider$PlaylistContextMenuProviderListener;", "", "canStartNewTask", "", "position", "Lcom/amazon/mp3/library/item/AbstractItem;", "item", "Lkotlin/Function0;", "", "completionBlock", "deleteContent", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "<init>", "(Landroidx/fragment/app/FragmentActivity;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public abstract class AbstractPlaylistContextMenuListener implements PlaylistContextMenuProvider.PlaylistContextMenuProviderListener {
    private final FragmentActivity activity;

    @Override // com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider.PlaylistContextMenuProviderListener
    public boolean canStartNewTask() {
        return true;
    }

    public AbstractPlaylistContextMenuListener(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
    }

    public final void deleteContent(int position, AbstractItem item, final Function0<Unit> completionBlock) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item instanceof Playlist) {
            FragmentActivity fragmentActivity = this.activity;
            Playlist playlist = (Playlist) item;
            Uri contentUri = playlist.getContentUri();
            Intrinsics.checkNotNullExpressionValue(contentUri, "item.contentUri");
            String name = playlist.getName();
            Intrinsics.checkNotNullExpressionValue(name, "item.name");
            new DeleteUserPlaylistAction(fragmentActivity, contentUri, name).perform(completionBlock);
            return;
        }
        if (item instanceof CatalogPlaylist) {
            CatalogPlaylist catalogPlaylist = (CatalogPlaylist) item;
            new PlaylistUnfollowAction(this.activity, catalogPlaylist.getAsin(), catalogPlaylist.getLuid(), CirrusMediaSource.matchCloud(catalogPlaylist.getContentUri())).unfollowPlaylist(new Function1<Boolean, Unit>() { // from class: com.amazon.mp3.fragment.contextmenu.AbstractPlaylistContextMenuListener.deleteContent.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    Function0<Unit> function0;
                    if (!z || (function0 = completionBlock) == null) {
                        return;
                    }
                    function0.invoke();
                }
            });
        }
    }
}
