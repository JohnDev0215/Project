package com.amazon.music.media.auto;

import android.support.v4.media.MediaBrowserCompat;
import com.amazon.music.media.auto.provider.AutoMediaItem;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MediaItemNode.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0000\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b\"\u0010#J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0000H\u0002J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005J\u0006\u0010\t\u001a\u00020\bJ\b\u0010\n\u001a\u0004\u0018\u00010\u0005R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00008\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\"\u0010\u0010\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR(\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00000\u001b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006$"}, d2 = {"Lcom/amazon/music/media/auto/MediaItemNode;", "", "mediaItemNode", "", "addChild", "", "id", "findChild", "", "removeAllChildren", "getMediaId", "parent", "Lcom/amazon/music/media/auto/MediaItemNode;", "getParent", "()Lcom/amazon/music/media/auto/MediaItemNode;", "Landroid/support/v4/media/MediaBrowserCompat$MediaItem;", "mediaItem", "Landroid/support/v4/media/MediaBrowserCompat$MediaItem;", "getMediaItem", "()Landroid/support/v4/media/MediaBrowserCompat$MediaItem;", "setMediaItem", "(Landroid/support/v4/media/MediaBrowserCompat$MediaItem;)V", "Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "autoMediaItem", "Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "getAutoMediaItem", "()Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "Ljava/util/concurrent/CopyOnWriteArrayList;", "children", "Ljava/util/concurrent/CopyOnWriteArrayList;", "getChildren", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "setChildren", "(Ljava/util/concurrent/CopyOnWriteArrayList;)V", "<init>", "(Lcom/amazon/music/media/auto/MediaItemNode;Landroid/support/v4/media/MediaBrowserCompat$MediaItem;Lcom/amazon/music/media/auto/provider/AutoMediaItem;)V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class MediaItemNode {
    private final AutoMediaItem autoMediaItem;
    private CopyOnWriteArrayList<MediaItemNode> children;
    private MediaBrowserCompat.MediaItem mediaItem;
    private final MediaItemNode parent;

    public MediaItemNode(MediaItemNode mediaItemNode, MediaBrowserCompat.MediaItem mediaItem, AutoMediaItem autoMediaItem) {
        Intrinsics.checkNotNullParameter(mediaItem, "mediaItem");
        this.parent = mediaItemNode;
        this.mediaItem = mediaItem;
        this.autoMediaItem = autoMediaItem;
        this.children = new CopyOnWriteArrayList<>();
        if (mediaItemNode == null) {
            return;
        }
        mediaItemNode.addChild(this);
    }

    public /* synthetic */ MediaItemNode(MediaItemNode mediaItemNode, MediaBrowserCompat.MediaItem mediaItem, AutoMediaItem autoMediaItem, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(mediaItemNode, mediaItem, (i & 4) != 0 ? null : autoMediaItem);
    }

    public final AutoMediaItem getAutoMediaItem() {
        return this.autoMediaItem;
    }

    public final MediaBrowserCompat.MediaItem getMediaItem() {
        return this.mediaItem;
    }

    public final MediaItemNode getParent() {
        return this.parent;
    }

    public final void setMediaItem(MediaBrowserCompat.MediaItem mediaItem) {
        Intrinsics.checkNotNullParameter(mediaItem, "<set-?>");
        this.mediaItem = mediaItem;
    }

    public final CopyOnWriteArrayList<MediaItemNode> getChildren() {
        return this.children;
    }

    public final synchronized MediaItemNode findChild(String id) {
        Object next;
        Iterator<T> it = this.children.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(((MediaItemNode) next).getMediaItem().getMediaId(), id)) {
                break;
            }
        }
        return (MediaItemNode) next;
    }

    private final synchronized boolean addChild(MediaItemNode mediaItemNode) {
        if (findChild(mediaItemNode.getMediaId()) != null) {
            return false;
        }
        return this.children.add(mediaItemNode);
    }

    public final void removeAllChildren() {
        this.children = new CopyOnWriteArrayList<>();
    }

    public final String getMediaId() {
        return this.mediaItem.getMediaId();
    }
}
