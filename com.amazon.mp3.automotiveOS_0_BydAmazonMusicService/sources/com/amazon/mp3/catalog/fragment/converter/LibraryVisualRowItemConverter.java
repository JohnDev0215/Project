package com.amazon.mp3.catalog.fragment.converter;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.item.TrackPlaceHolder;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.view.model.BadgeModel;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.DownloadStateModel;
import com.amazon.music.views.library.models.VisualRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryVisualRowItemConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b#\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BS\u0012\u0006\u0010$\u001a\u00020#\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010-\u001a\u00020\t\u0012\b\b\u0002\u0010/\u001a\u00020\t\u0012\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u001a\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u000102¢\u0006\u0004\bS\u0010TJ\u0018\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u001a\u0010\r\u001a\u0004\u0018\u00010\u00072\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0013\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0017\u001a\u00020\u0002H\u0016J#\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0017\u001a\u00020\u00022\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a¢\u0006\u0004\b\u0019\u0010\u001cJ\u0016\u0010 \u001a\u00020\u001f2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\tJ#\u0010!\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0017\u001a\u00020\u00022\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0014¢\u0006\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R$\u0010&\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u0016\u0010,\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010'R\u0014\u0010-\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.R\u0014\u0010/\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u0010.R\u0016\u00100\u001a\u0004\u0018\u00010\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u00101R\u0016\u00103\u001a\u0004\u0018\u0001028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00104R\"\u0010\u001d\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010.\u001a\u0004\b\u001d\u00105\"\u0004\b6\u00107R\"\u0010\u001e\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010.\u001a\u0004\b\u001e\u00105\"\u0004\b8\u00107R$\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u00109\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R$\u0010>\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b>\u0010?\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\"\u0010D\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bD\u0010.\u001a\u0004\bD\u00105\"\u0004\bE\u00107R$\u0010F\u001a\u0004\u0018\u00010\u001a8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bF\u00101\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR$\u0010K\u001a\u0004\u0018\u00010\u001a8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bK\u00101\u001a\u0004\bL\u0010H\"\u0004\bM\u0010JR\u001b\u0010R\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bN\u0010O\u001a\u0004\bP\u0010Q¨\u0006U"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/converter/LibraryVisualRowItemConverter;", "", "Lcom/amazon/mp3/library/item/AbstractItem;", "Lcom/amazon/mp3/library/item/MusicTrack;", "track", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "Lcom/amazon/music/views/library/models/VisualRowItemModel;", "convertTrack", "", "isTrackInLibrary", "Lcom/amazon/mp3/library/item/Album;", "album", "convertAlbum", "Lcom/amazon/mp3/library/item/Artist;", "artist", "convertArtist", "convertTrackPlaceHolder", "Lcom/amazon/mp3/library/item/Playlist;", Environment.PLAYLIST_PATH, "convertPlaylist", "Lcom/amazon/mp3/playlist/CatalogPlaylist;", "convertCatalogPlaylist", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "", "position", "(Lcom/amazon/mp3/library/item/AbstractItem;Ljava/lang/Integer;)Lcom/amazon/music/views/library/models/base/BaseViewModel;", "isIvy", "isNightwingOnly", "", "refreshUserSubscriptionValues", "getContentMetadata", "(Lcom/amazon/mp3/library/item/AbstractItem;Ljava/lang/Integer;)Lcom/amazon/music/views/library/metadata/ContentMetadata;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "canDrag", "Ljava/lang/Boolean;", "getCanDrag", "()Ljava/lang/Boolean;", "setCanDrag", "(Ljava/lang/Boolean;)V", "displayRowNumber", "displaysDownloadState", "Z", "needHorizontalMargin", "presetPlayState", "Ljava/lang/Integer;", "Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;", "cachedActionProviderImpl", "Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;", "()Z", "setIvy", "(Z)V", "setNightwingOnly", "Lcom/amazon/mp3/library/item/AbstractItem;", "getPlaylist", "()Lcom/amazon/mp3/library/item/AbstractItem;", "setPlaylist", "(Lcom/amazon/mp3/library/item/AbstractItem;)V", "containerMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getContainerMetadata", "()Lcom/amazon/music/views/library/metadata/ContentMetadata;", "setContainerMetadata", "(Lcom/amazon/music/views/library/metadata/ContentMetadata;)V", "isOnDemandGoldenPlaylist", "setOnDemandGoldenPlaylist", "artworkId", "getArtworkId", "()Ljava/lang/Integer;", "setArtworkId", "(Ljava/lang/Integer;)V", "titleId", "getTitleId", "setTitleId", "trackPlaceHolder$delegate", "Lkotlin/Lazy;", "getTrackPlaceHolder", "()Lcom/amazon/music/views/library/models/VisualRowItemModel;", "trackPlaceHolder", "<init>", "(Landroid/content/Context;Ljava/lang/Boolean;Ljava/lang/Boolean;ZZLjava/lang/Integer;Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class LibraryVisualRowItemConverter implements SingleBaseModelConverter {
    private Integer artworkId;
    private final CachedCapabilityActionProviderImpl cachedActionProviderImpl;
    private Boolean canDrag;
    private ContentMetadata containerMetadata;
    private final Context context;
    private final Boolean displayRowNumber;
    private final boolean displaysDownloadState;
    private boolean isIvy;
    private boolean isNightwingOnly;
    private boolean isOnDemandGoldenPlaylist;
    private final boolean needHorizontalMargin;
    private AbstractItem playlist;
    private final Integer presetPlayState;
    private Integer titleId;

    /* JADX INFO: renamed from: trackPlaceHolder$delegate, reason: from kotlin metadata */
    private final Lazy trackPlaceHolder;

    public LibraryVisualRowItemConverter(Context context, Boolean bool, Boolean bool2, boolean z, boolean z2, Integer num, CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.canDrag = bool;
        this.displayRowNumber = bool2;
        this.displaysDownloadState = z;
        this.needHorizontalMargin = z2;
        this.presetPlayState = num;
        this.cachedActionProviderImpl = cachedCapabilityActionProviderImpl;
        this.isIvy = UserSubscriptionUtil.getUserSubscription().isIvy();
        this.isNightwingOnly = UserSubscriptionUtil.getUserSubscription().isNightwingOnly();
        this.trackPlaceHolder = LazyKt__LazyJVMKt.lazy(new Function0<VisualRowItemModel>() { // from class: com.amazon.mp3.catalog.fragment.converter.LibraryVisualRowItemConverter$trackPlaceHolder$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final VisualRowItemModel invoke() {
                ContentMetadata contentMetadata = new ContentMetadata() { // from class: com.amazon.mp3.catalog.fragment.converter.LibraryVisualRowItemConverter$trackPlaceHolder$2.1
                    private final List<String> tags = CollectionsKt__CollectionsKt.emptyList();
                    private Uri uri;
                    private final String viewId;

                    {
                        Uri EMPTY = Uri.EMPTY;
                        Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
                        this.uri = EMPTY;
                        this.viewId = "";
                    }

                    @Override // com.amazon.music.views.library.metadata.ContentMetadata
                    public List<String> getTags() {
                        return this.tags;
                    }

                    @Override // com.amazon.music.views.library.metadata.ContentMetadata
                    public Uri getUri() {
                        return this.uri;
                    }

                    @Override // com.amazon.music.views.library.metadata.ContentMetadata
                    public String getViewId() {
                        return this.viewId;
                    }
                };
                return new VisualRowItemModel(null, "...", null, null, null, null, null, null, null, null, null, null, null, new ArtworkFrameModel(null, null, null, null, null, null, null, Integer.valueOf(R.drawable.song_placeholder), null, null, null, null, 3967, null), null, null, null, null, Boolean.FALSE, null, null, null, this.this$0.needHorizontalMargin, null, 2, contentMetadata, false, false, false, null, false, false, -927211523, null);
            }
        });
    }

    public /* synthetic */ LibraryVisualRowItemConverter(Context context, Boolean bool, Boolean bool2, boolean z, boolean z2, Integer num, CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? Boolean.FALSE : bool, (i & 4) != 0 ? Boolean.FALSE : bool2, (i & 8) != 0 ? true : z, (i & 16) == 0 ? z2 : true, (i & 32) != 0 ? null : num, (i & 64) == 0 ? cachedCapabilityActionProviderImpl : null);
    }

    public final void setCanDrag(Boolean bool) {
        this.canDrag = bool;
    }

    public final void setPlaylist(AbstractItem abstractItem) {
        this.playlist = abstractItem;
    }

    public final void setContainerMetadata(ContentMetadata contentMetadata) {
        this.containerMetadata = contentMetadata;
    }

    public final void setOnDemandGoldenPlaylist(boolean z) {
        this.isOnDemandGoldenPlaylist = z;
    }

    public final void setArtworkId(Integer num) {
        this.artworkId = num;
    }

    public final void setTitleId(Integer num) {
        this.titleId = num;
    }

    public final VisualRowItemModel getTrackPlaceHolder() {
        return (VisualRowItemModel) this.trackPlaceHolder.getValue();
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(AbstractItem data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return convert(data, null);
    }

    public final BaseViewModel convert(AbstractItem data, Integer position) {
        VisualRowItemModel visualRowItemModelConvertArtist;
        Intrinsics.checkNotNullParameter(data, "data");
        ContentMetadata contentMetadata = getContentMetadata(data, position);
        if (contentMetadata == null) {
            return null;
        }
        if (data instanceof TrackPlaceHolder) {
            visualRowItemModelConvertArtist = convertTrackPlaceHolder(contentMetadata);
        } else if (data instanceof MusicTrack) {
            visualRowItemModelConvertArtist = convertTrack((MusicTrack) data, contentMetadata);
        } else if (data instanceof Playlist) {
            visualRowItemModelConvertArtist = convertPlaylist((Playlist) data, contentMetadata);
        } else if (data instanceof CatalogPlaylist) {
            visualRowItemModelConvertArtist = convertCatalogPlaylist((CatalogPlaylist) data, contentMetadata);
        } else if (data instanceof Album) {
            visualRowItemModelConvertArtist = convertAlbum((Album) data, contentMetadata);
        } else {
            if (!(data instanceof Artist)) {
                return null;
            }
            visualRowItemModelConvertArtist = convertArtist((Artist) data, contentMetadata);
        }
        return visualRowItemModelConvertArtist;
    }

    public final void refreshUserSubscriptionValues(boolean isIvy, boolean isNightwingOnly) {
        this.isIvy = isIvy;
        this.isNightwingOnly = isNightwingOnly;
    }

    protected ContentMetadata getContentMetadata(AbstractItem data, Integer position) {
        Intrinsics.checkNotNullParameter(data, "data");
        return LibraryContentMetadataConverter.convert(data, position);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00d3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.amazon.music.views.library.models.VisualRowItemModel convertTrack(com.amazon.mp3.library.item.MusicTrack r43, com.amazon.music.views.library.metadata.ContentMetadata r44) {
        /*
            Method dump skipped, instruction units count: 443
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.converter.LibraryVisualRowItemConverter.convertTrack(com.amazon.mp3.library.item.MusicTrack, com.amazon.music.views.library.metadata.ContentMetadata):com.amazon.music.views.library.models.VisualRowItemModel");
    }

    private final boolean isTrackInLibrary(MusicTrack track) {
        return track.isInLibrary() || !track.isAddableToLibrary().booleanValue();
    }

    private final VisualRowItemModel convertAlbum(Album album, ContentMetadata metadata) {
        BadgeModel encodingBadgeModel = ContentEncodingDecoder.getEncodingBadgeModel(album.getContentEncodings(), this.context, 8);
        Intrinsics.checkNotNullExpressionValue(encodingBadgeModel, "getEncodingBadgeModel(al…ings, context, View.GONE)");
        BadgeModel immersiveContentBadgeModel = ContentEncodingDecoder.getImmersiveContentBadgeModel(album.getContentEncodings(), this.context, 8, Boolean.valueOf(this.isIvy));
        Intrinsics.checkNotNullExpressionValue(immersiveContentBadgeModel, "getImmersiveContentBadge…ontext, View.GONE, isIvy)");
        ArtworkFrameModel artworkFrameModelConvert$default = LibraryArtworkFrameConverter.convert$default(LibraryArtworkFrameConverter.INSTANCE, album, metadata, null, 4, null);
        Integer num = this.artworkId;
        String title = album.getTitle();
        Integer num2 = this.titleId;
        String artistName = album.getArtistName();
        boolean zIsInLibrary = album.isInLibrary();
        String badgeText = encodingBadgeModel.getBadgeText();
        String badgeText2 = immersiveContentBadgeModel.getBadgeText();
        DownloadStateModel downloadStateModel = album.isDownloaded() ? new DownloadStateModel(DownloadStateModel.DownloadState.DOWNLOADED, 0) : null;
        boolean z = this.needHorizontalMargin;
        Intrinsics.checkNotNullExpressionValue(title, "title");
        Boolean bool = Boolean.TRUE;
        Boolean bool2 = Boolean.FALSE;
        return new VisualRowItemModel(null, title, artistName, null, null, null, null, bool, badgeText, badgeText2, bool2, null, bool2, artworkFrameModelConvert$default, num, num2, null, null, null, null, null, null, z, null, 0, metadata, zIsInLibrary, false, false, downloadStateModel, false, false, -658569095, null);
    }

    private final VisualRowItemModel convertArtist(Artist artist, ContentMetadata metadata) {
        boolean z = artist.getFollowStatus() == 1;
        ArtworkFrameModel artworkFrameModelConvert$default = LibraryArtworkFrameConverter.convert$default(LibraryArtworkFrameConverter.INSTANCE, artist, metadata, null, 4, null);
        Integer num = this.artworkId;
        String name = artist.getName();
        Integer num2 = this.titleId;
        boolean zIsInLibrary = artist.isInLibrary();
        DownloadStateModel downloadStateModel = artist.isDownloaded() ? new DownloadStateModel(DownloadStateModel.DownloadState.DOWNLOADED, 0) : null;
        boolean z2 = this.needHorizontalMargin;
        Intrinsics.checkNotNullExpressionValue(name, "name");
        return new VisualRowItemModel(null, name, null, null, null, null, null, null, null, null, null, null, null, artworkFrameModelConvert$default, num, num2, null, null, null, null, null, null, z2, null, 4, metadata, zIsInLibrary, false, false, downloadStateModel, false, z, 1488920573, null);
    }

    private final VisualRowItemModel convertTrackPlaceHolder(ContentMetadata metadata) {
        return getTrackPlaceHolder();
    }

    private final VisualRowItemModel convertPlaylist(Playlist playlist, ContentMetadata metadata) {
        ArtworkFrameModel artworkFrameModelConvert$default = LibraryArtworkFrameConverter.convert$default(LibraryArtworkFrameConverter.INSTANCE, playlist, metadata, null, 4, null);
        Integer num = this.artworkId;
        String name = playlist.getName();
        Integer num2 = this.titleId;
        String strStringPlus = Intrinsics.stringPlus(playlist.getVisibilityText(), this.context.getResources().getString(R.string.dmusic_library_playlists_songs_count, Long.valueOf(playlist.getTrackCount())));
        boolean zIsInLibrary = playlist.isInLibrary();
        DownloadStateModel downloadStateModel = playlist.isDownloaded() ? new DownloadStateModel(DownloadStateModel.DownloadState.DOWNLOADED, 0) : null;
        boolean z = this.needHorizontalMargin;
        Intrinsics.checkNotNullExpressionValue(name, "name");
        return new VisualRowItemModel(null, name, strStringPlus, null, null, null, null, null, null, null, null, null, null, artworkFrameModelConvert$default, num, num2, null, null, null, null, null, null, z, null, 1, metadata, zIsInLibrary, false, false, downloadStateModel, false, false, -658563079, null);
    }

    private final VisualRowItemModel convertCatalogPlaylist(CatalogPlaylist playlist, ContentMetadata metadata) {
        ArtworkFrameModel artworkFrameModelConvert$default = LibraryArtworkFrameConverter.convert$default(LibraryArtworkFrameConverter.INSTANCE, playlist, metadata, null, 4, null);
        Integer num = this.artworkId;
        String title = playlist.getTitle();
        Integer num2 = this.titleId;
        String strValueOf = String.valueOf(playlist.getTrackCount());
        boolean zIsInLibrary = playlist.isInLibrary();
        DownloadStateModel downloadStateModel = playlist.isDownloaded() ? new DownloadStateModel(DownloadStateModel.DownloadState.DOWNLOADED, 0) : null;
        boolean z = this.needHorizontalMargin;
        Intrinsics.checkNotNullExpressionValue(title, "title");
        return new VisualRowItemModel(null, title, strValueOf, null, null, null, null, null, null, null, null, null, null, artworkFrameModelConvert$default, num, num2, null, null, null, null, null, null, z, null, 1, metadata, zIsInLibrary, false, false, downloadStateModel, false, false, -658563079, null);
    }
}
