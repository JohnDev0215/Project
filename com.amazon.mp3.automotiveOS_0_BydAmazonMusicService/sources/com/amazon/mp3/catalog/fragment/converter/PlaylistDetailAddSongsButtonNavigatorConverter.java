package com.amazon.mp3.catalog.fragment.converter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.ContainerModelConfiguration;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.ButtonNavigatorModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistDetailAddSongsButtonNavigatorConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0014\u0010\u0015J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/converter/PlaylistDetailAddSongsButtonNavigatorConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/mp3/library/item/AbstractItem;", "Lcom/amazon/mp3/library/item/Playlist;", Environment.PLAYLIST_PATH, "Lcom/amazon/music/views/library/models/ButtonNavigatorModel;", "convertPlaylist", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;", "configuration", "Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;", "getConfiguration", "()Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlaylistDetailAddSongsButtonNavigatorConverter implements SingleBaseModelConverter<AbstractItem> {
    private final ContainerModelConfiguration configuration;
    private final Context context;

    public PlaylistDetailAddSongsButtonNavigatorConverter(Context context, ContainerModelConfiguration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        this.context = context;
        this.configuration = configuration;
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(AbstractItem data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof Playlist) {
            return convertPlaylist((Playlist) data);
        }
        return null;
    }

    private final ButtonNavigatorModel convertPlaylist(Playlist playlist) {
        Resources resources;
        boolean zCanRemovePlaylist = PlaylistUtil.canRemovePlaylist(this.context, playlist.getContentUri());
        Drawable drawable = null;
        SpacerKey spacerKey = this.configuration.shouldShowUpsellOption(playlist) ? SpacerKey.SMALL : null;
        if (!zCanRemovePlaylist || !Feature.pdp_add_songs_button_enabled.isEnabled()) {
            return null;
        }
        String asin = playlist.getAsin();
        String string = AmazonApplication.getContext().getResources().getString(R.string.dmusic_playlist_context_add_songs);
        Context context = this.context;
        if (context != null && (resources = context.getResources()) != null) {
            drawable = resources.getDrawable(R.drawable.ic_action_add);
        }
        Drawable drawable2 = drawable;
        ButtonSizeKey buttonSizeKey = ButtonSizeKey.MEDIUM_WITH_ICON;
        ButtonStyleKey buttonStyleKey = ButtonStyleKey.BORDERLESS_OUTLINE;
        SpacerKey spacerKey2 = SpacerKey.SMALL;
        Intrinsics.checkNotNullExpressionValue(string, "getString(com.amazon.mp3…aylist_context_add_songs)");
        return new ButtonNavigatorModel(asin, string, "AddSongs", drawable2, null, false, buttonSizeKey, buttonStyleKey, spacerKey2, spacerKey, -2, 48, null);
    }
}
