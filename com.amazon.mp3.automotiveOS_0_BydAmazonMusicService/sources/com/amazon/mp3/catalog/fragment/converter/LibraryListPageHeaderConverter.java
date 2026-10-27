package com.amazon.mp3.catalog.fragment.converter;

import android.content.Context;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.TrackModelConfiguration;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.DefaultPageHeaderModel;
import com.amazon.music.views.library.models.PageHeaderActionIconModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryListPageHeaderConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0002J\b\u0010\b\u001a\u00020\u0004H\u0002J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0002J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0002J\u0012\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/converter/LibraryListPageHeaderConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/mp3/library/item/AbstractItem;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convertTrack", "convertAlbum", "convertArtist", "convertPlaylist", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel;", "generatePlaylistPageHeaderIcons", "generateTracksPageHeaderIcons", "convert", "Landroid/content/Context;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LibraryListPageHeaderConverter implements SingleBaseModelConverter<AbstractItem> {
    private final Context context;

    public LibraryListPageHeaderConverter(Context context) {
        this.context = context;
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(AbstractItem data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof Album) {
            return convertAlbum();
        }
        if (data instanceof Artist) {
            return convertArtist();
        }
        if (data instanceof Playlist) {
            return convertPlaylist();
        }
        if (data instanceof MusicTrack) {
            return convertTrack(data);
        }
        return null;
    }

    private final BaseViewModel convertTrack(AbstractItem data) {
        Context context = this.context;
        String string = context == null ? null : context.getString(R.string.dmusic_music_ingress_pill_text);
        List<PageHeaderActionIconModel> listGenerateTracksPageHeaderIcons = generateTracksPageHeaderIcons();
        Context context2 = this.context;
        return new DefaultPageHeaderModel("", string, context2 == null ? null : context2.getString(R.string.dmusic_prime_search_result_tracks), Integer.valueOf(R.id.category_text), null, null, null, listGenerateTracksPageHeaderIcons, LibraryContentMetadataConverter.convert$default(data, null, 2, null), null, 112, null);
    }

    private final BaseViewModel convertAlbum() {
        Context context = this.context;
        String string = context == null ? null : context.getString(R.string.dmusic_music_ingress_pill_text);
        List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        Context context2 = this.context;
        return new DefaultPageHeaderModel("", string, context2 != null ? context2.getString(R.string.dmusic_prime_search_result_albums) : null, Integer.valueOf(R.id.category_text), null, null, null, listEmptyList, null, null, 112, null);
    }

    private final BaseViewModel convertArtist() {
        Context context = this.context;
        String string = context == null ? null : context.getString(R.string.dmusic_music_ingress_pill_text);
        List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        Context context2 = this.context;
        return new DefaultPageHeaderModel("", string, context2 != null ? context2.getString(R.string.dmusic_prime_search_result_artists) : null, Integer.valueOf(R.id.category_text), null, null, null, listEmptyList, null, null, 112, null);
    }

    private final BaseViewModel convertPlaylist() {
        Context context = this.context;
        String string = context == null ? null : context.getString(R.string.dmusic_music_ingress_pill_text);
        List<PageHeaderActionIconModel> listGeneratePlaylistPageHeaderIcons = generatePlaylistPageHeaderIcons();
        Context context2 = this.context;
        return new DefaultPageHeaderModel("", string, context2 != null ? context2.getString(R.string.dmusic_prime_search_result_playlists) : null, Integer.valueOf(R.id.category_text), null, null, null, listGeneratePlaylistPageHeaderIcons, null, null, 112, null);
    }

    private final List<PageHeaderActionIconModel> generatePlaylistPageHeaderIcons() {
        ArrayList arrayList = new ArrayList();
        PageHeaderActionIconModel.ActionType actionType = PageHeaderActionIconModel.ActionType.ADD;
        Context context = this.context;
        arrayList.add(new PageHeaderActionIconModel(actionType, false, false, true, context == null ? null : context.getString(R.string.dmusic_context_see_all_new_playlist), Integer.valueOf(R.id.PlaylistName), 6, null));
        return arrayList;
    }

    private final List<PageHeaderActionIconModel> generateTracksPageHeaderIcons() {
        ArrayList arrayList = new ArrayList();
        if (new TrackModelConfiguration(null, 1, null).shouldShowLibrarySongsPageHeader()) {
            arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.PLAY, false, false, false, null, null, 62, null));
            arrayList.add(new PageHeaderActionIconModel(PageHeaderActionIconModel.ActionType.SHUFFLE, false, false, false, null, null, 62, null));
        }
        return arrayList;
    }
}
