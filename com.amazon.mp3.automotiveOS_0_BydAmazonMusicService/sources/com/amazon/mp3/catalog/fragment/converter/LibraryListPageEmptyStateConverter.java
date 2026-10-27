package com.amazon.mp3.catalog.fragment.converter;

import android.content.res.Resources;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.EmptyStateButtonType;
import com.amazon.music.views.library.models.EmptyStateModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryListPageEmptyStateConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B=\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015\u0012\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015\u0012\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\u0004\b\u001b\u0010\u001cJ\b\u0010\u0004\u001a\u00020\u0003H\u0002J\b\u0010\u0005\u001a\u00020\u0003H\u0002J\b\u0010\u0006\u001a\u00020\u0003H\u0002J\b\u0010\u0007\u001a\u00020\u0003H\u0002J+\u0010\r\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000f\u001a\u00020\u0002H\u0016R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u0018R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u0018¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/converter/LibraryListPageEmptyStateConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/mp3/library/item/AbstractItem;", "Lcom/amazon/music/views/library/models/EmptyStateModel;", "convertTrack", "convertAlbum", "convertArtist", "convertPlaylist", "", "titleStringID", "messageStringID", "Lcom/amazon/music/views/library/models/EmptyStateButtonType;", "buttonType", "getEmptyStateModel", "(ILjava/lang/Integer;Lcom/amazon/music/views/library/models/EmptyStateButtonType;)Lcom/amazon/music/views/library/models/EmptyStateModel;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "Landroid/content/res/Resources;", "resources", "Landroid/content/res/Resources;", "Lkotlin/Function0;", "", "areRefinementsApplied", "Lkotlin/jvm/functions/Function0;", "userHasSubscription", "isStoreSupported", "<init>", "(Landroid/content/res/Resources;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LibraryListPageEmptyStateConverter implements SingleBaseModelConverter<AbstractItem> {
    private final Function0<Boolean> areRefinementsApplied;
    private final Function0<Boolean> isStoreSupported;
    private final Resources resources;
    private final Function0<Boolean> userHasSubscription;

    public LibraryListPageEmptyStateConverter(Resources resources, Function0<Boolean> areRefinementsApplied, Function0<Boolean> userHasSubscription, Function0<Boolean> isStoreSupported) {
        Intrinsics.checkNotNullParameter(resources, "resources");
        Intrinsics.checkNotNullParameter(areRefinementsApplied, "areRefinementsApplied");
        Intrinsics.checkNotNullParameter(userHasSubscription, "userHasSubscription");
        Intrinsics.checkNotNullParameter(isStoreSupported, "isStoreSupported");
        this.resources = resources;
        this.areRefinementsApplied = areRefinementsApplied;
        this.userHasSubscription = userHasSubscription;
        this.isStoreSupported = isStoreSupported;
    }

    public /* synthetic */ LibraryListPageEmptyStateConverter(Resources resources, Function0 function0, Function0 function02, Function0 function03, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(resources, function0, (i & 4) != 0 ? new Function0<Boolean>() { // from class: com.amazon.mp3.catalog.fragment.converter.LibraryListPageEmptyStateConverter.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(UserSubscriptionUtil.getUserSubscription().hasSubscription());
            }
        } : function02, (i & 8) != 0 ? new Function0<Boolean>() { // from class: com.amazon.mp3.catalog.fragment.converter.LibraryListPageEmptyStateConverter.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(AmazonApplication.getCapabilities().isStoreSupported());
            }
        } : function03);
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(AbstractItem data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (this.areRefinementsApplied.invoke().booleanValue()) {
            String string = this.resources.getString(R.string.dmusic_reset_all_filters);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…dmusic_reset_all_filters)");
            return getEmptyStateModel(R.string.dmusic_filter_empty_screen, null, new EmptyStateButtonType.RESET(string));
        }
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
            return convertTrack();
        }
        return null;
    }

    private final EmptyStateModel convertTrack() {
        boolean zBooleanValue = this.userHasSubscription.invoke().booleanValue();
        Integer numValueOf = Integer.valueOf(R.string.dmusic_empty_songs_subtext);
        if (zBooleanValue) {
            String string = this.resources.getString(R.string.dmusic_button_explore_text);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…usic_button_explore_text)");
            return getEmptyStateModel(R.string.dmusic_empty_songs_header, numValueOf, new EmptyStateButtonType.EXPLORE(string));
        }
        if (this.isStoreSupported.invoke().booleanValue()) {
            Integer numValueOf2 = Integer.valueOf(R.string.dmusic_empty_recently_purchased_subtext);
            String string2 = this.resources.getString(R.string.dmusic_button_buy_text);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…g.dmusic_button_buy_text)");
            return getEmptyStateModel(R.string.dmusic_empty_purchased_music_header, numValueOf2, new EmptyStateButtonType.BUY(string2));
        }
        return getEmptyStateModel(R.string.dmusic_empty_songs_header, numValueOf, null);
    }

    private final EmptyStateModel convertAlbum() {
        boolean zBooleanValue = this.userHasSubscription.invoke().booleanValue();
        Integer numValueOf = Integer.valueOf(R.string.dmusic_empty_albums_subtext);
        if (zBooleanValue) {
            String string = this.resources.getString(R.string.dmusic_button_explore_text);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…usic_button_explore_text)");
            return getEmptyStateModel(R.string.dmusic_empty_albums_header, numValueOf, new EmptyStateButtonType.EXPLORE(string));
        }
        if (this.isStoreSupported.invoke().booleanValue()) {
            Integer numValueOf2 = Integer.valueOf(R.string.dmusic_empty_albums_purchase_subtext);
            String string2 = this.resources.getString(R.string.dmusic_button_buy_text);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…g.dmusic_button_buy_text)");
            return getEmptyStateModel(R.string.dmusic_empty_purchased_music_header, numValueOf2, new EmptyStateButtonType.BUY(string2));
        }
        return getEmptyStateModel(R.string.dmusic_empty_albums_header, numValueOf, null);
    }

    private final EmptyStateModel convertArtist() {
        boolean zBooleanValue = this.userHasSubscription.invoke().booleanValue();
        Integer numValueOf = Integer.valueOf(R.string.dmusic_empty_artists_subtext);
        if (zBooleanValue) {
            String string = this.resources.getString(R.string.dmusic_button_explore_text);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…usic_button_explore_text)");
            return getEmptyStateModel(R.string.dmusic_empty_added_music_header, numValueOf, new EmptyStateButtonType.EXPLORE(string));
        }
        if (this.isStoreSupported.invoke().booleanValue()) {
            Integer numValueOf2 = Integer.valueOf(R.string.dmusic_empty_artists_purchase_subtext);
            String string2 = this.resources.getString(R.string.dmusic_button_buy_text);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…g.dmusic_button_buy_text)");
            return getEmptyStateModel(R.string.dmusic_empty_purchased_music_header, numValueOf2, new EmptyStateButtonType.BUY(string2));
        }
        return getEmptyStateModel(R.string.dmusic_empty_added_music_header, numValueOf, null);
    }

    private final EmptyStateModel convertPlaylist() {
        boolean zBooleanValue = this.userHasSubscription.invoke().booleanValue();
        Integer numValueOf = Integer.valueOf(R.string.dmusic_empty_playlists_subtext);
        if (zBooleanValue) {
            String string = this.resources.getString(R.string.dmusic_button_explore_text);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…usic_button_explore_text)");
            return getEmptyStateModel(R.string.dmusic_empty_playlists_header, numValueOf, new EmptyStateButtonType.EXPLORE(string));
        }
        if (this.isStoreSupported.invoke().booleanValue()) {
            String string2 = this.resources.getString(R.string.dmusic_button_buy_text);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…g.dmusic_button_buy_text)");
            return getEmptyStateModel(R.string.dmusic_empty_purchased_music_header, null, new EmptyStateButtonType.BUY(string2));
        }
        return getEmptyStateModel(R.string.dmusic_empty_playlists_header, numValueOf, null);
    }

    private final EmptyStateModel getEmptyStateModel(int titleStringID, Integer messageStringID, EmptyStateButtonType buttonType) {
        String string;
        String string2 = this.resources.getString(titleStringID);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(titleStringID)");
        if (messageStringID == null) {
            string = null;
        } else {
            string = this.resources.getString(messageStringID.intValue());
        }
        return new EmptyStateModel(string2, string, buttonType);
    }
}
