package com.amazon.music.media.auto;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaDescriptionCompat;
import androidx.media.utils.MediaConstants;
import com.amazon.music.media.auto.feature.AutomotiveFeature;
import com.amazon.music.media.auto.provider.AutoMediaItem;
import com.amazon.music.media.auto.provider.DefaultImageUriProvider;
import com.amazon.music.media.auto.util.AutoLog;
import com.amazon.music.media.auto.util.LibraryExtensionsKt;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MediaItemNodeManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 @2\u00020\u0001:\u0002@AB\u0019\u0012\u0006\u0010,\u001a\u00020+\u0012\b\u0010/\u001a\u0004\u0018\u00010.¢\u0006\u0004\b>\u0010?J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J\u001c\u0010\t\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0002J\u0010\u0010\f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\nJ\u0012\u0010\f\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u0002J\u0018\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002J\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u000fJZ\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u00022\b\u0010\u0015\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0017\u001a\u00020\u00042\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001cJ(\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cJ\u0018\u0010#\u001a\u00020\"2\u0006\u0010 \u001a\u00020\u00062\b\u0010!\u001a\u0004\u0018\u00010\u001aJ\u0006\u0010$\u001a\u00020\u0006J\u0006\u0010%\u001a\u00020\u0006J\u0006\u0010&\u001a\u00020\"J\u0006\u0010'\u001a\u00020\u0006J\u0016\u0010(\u001a\u00020\"2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u0006J\u0006\u0010)\u001a\u00020\"J\u0006\u0010*\u001a\u00020\u0006R\u0014\u0010,\u001a\u00020+8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u0019\u0010/\u001a\u0004\u0018\u00010.8\u0006¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u00102R\u0014\u00103\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00104R\u0014\u00105\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00104R0\u00108\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000606j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0006`78\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u00109R\u0018\u0010:\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b:\u00104R\u0014\u0010<\u001a\u00020;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010=¨\u0006B"}, d2 = {"Lcom/amazon/music/media/auto/MediaItemNodeManager;", "", "", "destId", "", "navigateTo", "Lcom/amazon/music/media/auto/MediaItemNode;", "root", "id", "searchHierarchyForNode", "Lcom/amazon/music/media/auto/MediaItemNodeManager$FirstLayerDrawerId;", "drawerId", "getNode", "mediaId", "rootId", "", "getChildNodes", "Landroid/support/v4/media/MediaBrowserCompat$MediaItem;", "getChildren", "getFlatMapOfContent", "parent", "title", MediaTrack.ROLE_SUBTITLE, "browsable", "Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "autoMediaItem", "Landroid/net/Uri;", "artUri", "Landroid/os/Bundle;", "extras", "createMediaItemNode", "createSeeMoreNode", "node", "uri", "", "updateNodeWithImageUri", "getHomeNode", "getLibraryNode", "resetSearchResults", "getSearchResultParent", "addSearchResultNodeToIndex", "resetRootNode", "getRootNode", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "featureGateProvider", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "getFeatureGateProvider", "()Lcom/amazon/music/platform/providers/FeatureGateProvider;", "rootNode", "Lcom/amazon/music/media/auto/MediaItemNode;", "searchResultsParent", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "searchResultsIndex", "Ljava/util/HashMap;", "currentNode", "Lcom/amazon/music/media/auto/provider/DefaultImageUriProvider;", "defaultUriProvider", "Lcom/amazon/music/media/auto/provider/DefaultImageUriProvider;", "<init>", "(Landroid/content/Context;Lcom/amazon/music/platform/providers/FeatureGateProvider;)V", "Companion", "FirstLayerDrawerId", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class MediaItemNodeManager {
    private static final String TAG = MediaItemNodeManager.class.getSimpleName();
    private final Context context;
    private MediaItemNode currentNode;
    private final DefaultImageUriProvider defaultUriProvider;
    private final FeatureGateProvider featureGateProvider;
    private final MediaItemNode rootNode;
    private final HashMap<String, MediaItemNode> searchResultsIndex;
    private final MediaItemNode searchResultsParent;

    /* JADX INFO: compiled from: MediaItemNodeManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/media/auto/MediaItemNodeManager$FirstLayerDrawerId;", "", "(Ljava/lang/String;I)V", "HOME", "LIBRARY", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum FirstLayerDrawerId {
        HOME,
        LIBRARY
    }

    public MediaItemNodeManager(Context context, FeatureGateProvider featureGateProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.featureGateProvider = featureGateProvider;
        MediaItemNode mediaItemNode = new MediaItemNode(null, new MediaBrowserCompat.MediaItem(new MediaDescriptionCompat.Builder().setMediaId("BROWSER_ROOT").build(), 1), null);
        this.rootNode = mediaItemNode;
        this.searchResultsParent = new MediaItemNode(null, new MediaBrowserCompat.MediaItem(new MediaDescriptionCompat.Builder().setMediaId("SEARCH_RESULTS").build(), 1), null);
        this.searchResultsIndex = new HashMap<>();
        this.currentNode = mediaItemNode;
        this.defaultUriProvider = new DefaultImageUriProvider(context);
    }

    public final synchronized MediaItemNode getNode(FirstLayerDrawerId drawerId) {
        Intrinsics.checkNotNullParameter(drawerId, "drawerId");
        return this.rootNode.findChild(drawerId.name());
    }

    public final synchronized MediaItemNode getNode(String mediaId) {
        MediaItemNode mediaItemNode = this.currentNode;
        MediaItemNode mediaItemNodeFindChild = null;
        MediaItemNode parent = mediaItemNode == null ? null : mediaItemNode.getParent();
        if (parent != null && Intrinsics.areEqual(parent.getMediaItem().getMediaId(), mediaId)) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, Intrinsics.stringPlus("Returning currentParent ", parent.getMediaItem().getMediaId()));
            return parent;
        }
        MediaItemNode mediaItemNode2 = this.currentNode;
        if (mediaItemNode2 != null) {
            mediaItemNodeFindChild = mediaItemNode2.findChild(mediaId);
        }
        if (mediaItemNodeFindChild != null) {
            return mediaItemNodeFindChild;
        }
        AutoLog autoLog2 = AutoLog.INSTANCE;
        String TAG3 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
        autoLog2.debug(TAG3, Intrinsics.stringPlus("Searching hierarchy for node with mediaId ", mediaId));
        MediaItemNode mediaItemNodeSearchHierarchyForNode = this.searchResultsIndex.get(mediaId);
        if (mediaItemNodeSearchHierarchyForNode == null) {
            mediaItemNodeSearchHierarchyForNode = searchHierarchyForNode(this.rootNode, mediaId);
        }
        return mediaItemNodeSearchHierarchyForNode;
    }

    public final synchronized List<MediaItemNode> getChildNodes(String rootId) {
        List<MediaItemNode> arrayList;
        if (navigateTo(rootId)) {
            MediaItemNode mediaItemNode = this.currentNode;
            arrayList = mediaItemNode == null ? null : mediaItemNode.getChildren();
        } else {
            arrayList = new ArrayList<>();
        }
        return arrayList;
    }

    public final synchronized List<MediaBrowserCompat.MediaItem> getChildren(String rootId) {
        ArrayList arrayList;
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        autoLog.debug(TAG2, Intrinsics.stringPlus("getChildren called from id ", rootId));
        MediaItemNodeManager$getChildren$transform$1 mediaItemNodeManager$getChildren$transform$1 = new Function1<MediaItemNode, MediaBrowserCompat.MediaItem>() { // from class: com.amazon.music.media.auto.MediaItemNodeManager$getChildren$transform$1
            @Override // kotlin.jvm.functions.Function1
            public final MediaBrowserCompat.MediaItem invoke(MediaItemNode it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getMediaItem();
            }
        };
        List<MediaItemNode> childNodes = getChildNodes(rootId);
        if (childNodes == null) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(childNodes, 10));
            Iterator<T> it = childNodes.iterator();
            while (it.hasNext()) {
                arrayList2.add(mediaItemNodeManager$getChildren$transform$1.invoke(it.next()));
            }
            arrayList = arrayList2;
        }
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        return arrayList;
    }

    private final boolean navigateTo(String destId) {
        MediaBrowserCompat.MediaItem mediaItem;
        MediaItemNode parent;
        MediaBrowserCompat.MediaItem mediaItem2;
        CopyOnWriteArrayList<MediaItemNode> children;
        MediaItemNode mediaItemNode = this.currentNode;
        if (mediaItemNode != null) {
            MediaItemNode mediaItemNode2 = null;
            Object obj = null;
            mediaItemNode2 = null;
            if ((mediaItemNode == null ? null : mediaItemNode.getMediaItem()) != null) {
                AutoLog autoLog = AutoLog.INSTANCE;
                String TAG2 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                StringBuilder sb = new StringBuilder();
                sb.append("navigating to ");
                sb.append((Object) destId);
                sb.append(" from ");
                MediaItemNode mediaItemNode3 = this.currentNode;
                sb.append((Object) ((mediaItemNode3 == null || (mediaItem = mediaItemNode3.getMediaItem()) == null) ? null : mediaItem.getMediaId()));
                autoLog.debug(TAG2, sb.toString());
                MediaItemNode mediaItemNode4 = this.currentNode;
                if (Intrinsics.areEqual((mediaItemNode4 == null || (parent = mediaItemNode4.getParent()) == null || (mediaItem2 = parent.getMediaItem()) == null) ? null : mediaItem2.getMediaId(), destId)) {
                    MediaItemNode mediaItemNode5 = this.currentNode;
                    this.currentNode = mediaItemNode5 != null ? mediaItemNode5.getParent() : null;
                    return true;
                }
                MediaItemNode mediaItemNode6 = this.currentNode;
                if (mediaItemNode6 != null && (children = mediaItemNode6.getChildren()) != null) {
                    Iterator<T> it = children.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Object next = it.next();
                        if (Intrinsics.areEqual(((MediaItemNode) next).getMediaItem().getMediaId(), destId)) {
                            obj = next;
                            break;
                        }
                    }
                    mediaItemNode2 = (MediaItemNode) obj;
                }
                if (mediaItemNode2 != null) {
                    this.currentNode = mediaItemNode2;
                    return true;
                }
                AutoLog autoLog2 = AutoLog.INSTANCE;
                String TAG3 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
                autoLog2.debug(TAG3, "node wasn't found immediately. searching hierarchy");
                MediaItemNode mediaItemNodeSearchHierarchyForNode = searchHierarchyForNode(this.rootNode, destId);
                this.currentNode = mediaItemNodeSearchHierarchyForNode;
                return mediaItemNodeSearchHierarchyForNode != null;
            }
        }
        AutoLog autoLog3 = AutoLog.INSTANCE;
        String TAG4 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG4, "TAG");
        autoLog3.error(TAG4, "Unable to navigate to destination due to null current media item node");
        return false;
    }

    private final MediaItemNode searchHierarchyForNode(MediaItemNode root, String id) {
        if (Intrinsics.areEqual(root.getMediaItem().getMediaId(), id)) {
            return root;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(root);
        while (!arrayList.isEmpty()) {
            for (MediaItemNode child : ((MediaItemNode) CollectionsKt__MutableCollectionsKt.removeFirst(arrayList)).getChildren()) {
                AutoLog autoLog = AutoLog.INSTANCE;
                String TAG2 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                autoLog.debug(TAG2, Intrinsics.stringPlus("Searching through child ", child.getMediaItem().getMediaId()));
                if (Intrinsics.areEqual(child.getMediaItem().getMediaId(), id)) {
                    Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                    autoLog.debug(TAG2, Intrinsics.stringPlus("Searched and found mediaItem with mediaId ", id));
                    return child;
                }
                Intrinsics.checkNotNullExpressionValue(child, "child");
                arrayList.add(child);
            }
        }
        AutoLog autoLog2 = AutoLog.INSTANCE;
        String TAG3 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
        autoLog2.debug(TAG3, Intrinsics.stringPlus("Could not find mediaItem with mediaId ", id));
        return null;
    }

    public final synchronized List<MediaBrowserCompat.MediaItem> getFlatMapOfContent() {
        ArrayList arrayList = new ArrayList();
        CopyOnWriteArrayList<MediaItemNode> children = this.rootNode.getChildren();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : children) {
            if (((MediaItemNode) obj).getMediaItem().isPlayable()) {
                arrayList2.add(obj);
            }
        }
        ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(((MediaItemNode) it.next()).getMediaItem());
        }
        arrayList.addAll(arrayList3);
        if (arrayList.size() >= 12) {
            return CollectionsKt___CollectionsKt.take(arrayList, 12);
        }
        CopyOnWriteArrayList<MediaItemNode> children2 = this.rootNode.getChildren();
        ArrayList arrayList4 = new ArrayList();
        for (Object obj2 : children2) {
            if (((MediaItemNode) obj2).getMediaItem().isBrowsable()) {
                arrayList4.add(obj2);
            }
        }
        ArrayList arrayList5 = new ArrayList();
        Iterator it2 = arrayList4.iterator();
        while (it2.hasNext()) {
            CopyOnWriteArrayList<MediaItemNode> children3 = ((MediaItemNode) it2.next()).getChildren();
            ArrayList arrayList6 = new ArrayList();
            for (Object obj3 : children3) {
                if (((MediaItemNode) obj3).getMediaItem().isPlayable()) {
                    arrayList6.add(obj3);
                }
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList5, CollectionsKt___CollectionsKt.take(arrayList6, 2));
        }
        ArrayList arrayList7 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList5, 10));
        Iterator it3 = arrayList5.iterator();
        while (it3.hasNext()) {
            arrayList7.add(((MediaItemNode) it3.next()).getMediaItem());
        }
        arrayList.addAll(arrayList7);
        return CollectionsKt___CollectionsKt.take(arrayList, 12);
    }

    public static /* synthetic */ MediaItemNode createMediaItemNode$default(MediaItemNodeManager mediaItemNodeManager, MediaItemNode mediaItemNode, String str, String str2, String str3, boolean z, AutoMediaItem autoMediaItem, Uri uri, Bundle bundle, int i, Object obj) {
        return mediaItemNodeManager.createMediaItemNode(mediaItemNode, str, str2, (i & 8) != 0 ? null : str3, z, (i & 32) != 0 ? null : autoMediaItem, (i & 64) != 0 ? null : uri, (i & 128) != 0 ? null : bundle);
    }

    public final MediaItemNode createMediaItemNode(MediaItemNode parent, String mediaId, String title, String subtitle, boolean browsable, AutoMediaItem autoMediaItem, Uri artUri, Bundle extras) {
        ContentMetadata metadata;
        Intrinsics.checkNotNullParameter(parent, "parent");
        int i = browsable ? 1 : 2;
        if (artUri == null) {
            artUri = (autoMediaItem == null || (metadata = autoMediaItem.getMetadata()) == null) ? null : LibraryExtensionsKt.getImageUrl(metadata);
        }
        if (artUri == null) {
            artUri = Uri.EMPTY;
        }
        return new MediaItemNode(parent, new MediaBrowserCompat.MediaItem(new MediaDescriptionCompat.Builder().setMediaId(mediaId).setTitle(title).setIconUri(artUri).setSubtitle(subtitle).setExtras(extras).build(), i), autoMediaItem);
    }

    public final MediaItemNode createSeeMoreNode(MediaItemNode parent, String mediaId, Uri artUri, Bundle extras) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(mediaId, "mediaId");
        Intrinsics.checkNotNullParameter(artUri, "artUri");
        String string = this.context.getString(R$string.dmusic_automotive_see_more);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…usic_automotive_see_more)");
        return new MediaItemNode(parent, new MediaBrowserCompat.MediaItem(new MediaDescriptionCompat.Builder().setMediaId(mediaId).setTitle(string).setIconUri(artUri).setExtras(extras).build(), 1), null, 4, null);
    }

    public final void updateNodeWithImageUri(MediaItemNode node, Uri uri) {
        Intrinsics.checkNotNullParameter(node, "node");
        if (uri == null) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.error(TAG2, "Cannot update node since uri " + uri + " is null.");
            return;
        }
        AutoLog autoLog2 = AutoLog.INSTANCE;
        String TAG3 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
        autoLog2.debug(TAG3, "Updating node '" + ((Object) node.getMediaItem().getMediaId()) + "' with image URL '" + uri + '\'');
        node.setMediaItem(new MediaBrowserCompat.MediaItem(new MediaDescriptionCompat.Builder().setMediaId(node.getMediaItem().getMediaId()).setTitle(node.getMediaItem().getDescription().getTitle()).setSubtitle(node.getMediaItem().getDescription().getSubtitle()).setExtras(node.getMediaItem().getDescription().getExtras()).setIconUri(uri).build(), node.getMediaItem().getFlags()));
    }

    public final MediaItemNode getHomeNode() {
        String string;
        FirstLayerDrawerId firstLayerDrawerId = FirstLayerDrawerId.HOME;
        MediaItemNode node = getNode(firstLayerDrawerId);
        if (node != null) {
            return node;
        }
        FeatureGateProvider featureGateProvider = this.featureGateProvider;
        if (featureGateProvider != null && AutomotiveFeature.MEDIA_BROWSER_LIBRARY_TAB.isEnabled(featureGateProvider)) {
            string = this.context.getString(R$string.dmusic_automotive_home_tab);
            Intrinsics.checkNotNullExpressionValue(string, "{\n            context.ge…otive_home_tab)\n        }");
        } else {
            string = this.context.getString(R$string.dmusic_media_browser_service_root);
            Intrinsics.checkNotNullExpressionValue(string, "{\n            context.ge…r_service_root)\n        }");
        }
        return createMediaItemNode$default(this, this.rootNode, firstLayerDrawerId.name(), string, null, true, null, this.defaultUriProvider.getHomeTabIconLocalUri(), null, 168, null);
    }

    public final MediaItemNode getLibraryNode() {
        FirstLayerDrawerId firstLayerDrawerId = FirstLayerDrawerId.LIBRARY;
        MediaItemNode node = getNode(firstLayerDrawerId);
        if (node != null) {
            return node;
        }
        MediaItemNode mediaItemNode = this.rootNode;
        String strName = firstLayerDrawerId.name();
        String string = this.context.getString(R$string.dmusic_media_browser_service_library_tab);
        Uri libraryTabIconLocalUri = this.defaultUriProvider.getLibraryTabIconLocalUri();
        Bundle bundle = new Bundle();
        if (Intrinsics.areEqual(this.context.getPackageName(), "com.amazon.mp3.automotiveOS")) {
            bundle.putInt(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_BROWSABLE, 1);
        }
        Unit unit = Unit.INSTANCE;
        return createMediaItemNode$default(this, mediaItemNode, strName, string, null, true, null, libraryTabIconLocalUri, bundle, 40, null);
    }

    public final void resetSearchResults() {
        this.searchResultsParent.removeAllChildren();
        this.searchResultsIndex.clear();
    }

    /* JADX INFO: renamed from: getSearchResultParent, reason: from getter */
    public final MediaItemNode getSearchResultsParent() {
        return this.searchResultsParent;
    }

    public final void addSearchResultNodeToIndex(String mediaId, MediaItemNode node) {
        Intrinsics.checkNotNullParameter(mediaId, "mediaId");
        Intrinsics.checkNotNullParameter(node, "node");
        this.searchResultsIndex.put(mediaId, node);
    }

    public final void resetRootNode() {
        this.rootNode.removeAllChildren();
    }

    public final MediaItemNode getRootNode() {
        return this.rootNode;
    }
}
