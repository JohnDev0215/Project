package com.amazon.mp3.catalog.fragment.converter;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.ContainerModelConfiguration;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.ButtonNavigatorModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryDetailButtonNavigatorConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\r\u0010\u000eJ\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailButtonNavigatorConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/mp3/library/item/AbstractItem;", "data", "Lcom/amazon/music/views/library/models/ButtonNavigatorModel;", "convertPlaylist", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;", "configuration", "Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;", "getConfiguration", "()Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;", "<init>", "(Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LibraryDetailButtonNavigatorConverter implements SingleBaseModelConverter<AbstractItem> {
    private final ContainerModelConfiguration configuration;

    public LibraryDetailButtonNavigatorConverter(ContainerModelConfiguration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        this.configuration = configuration;
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(AbstractItem data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if ((data instanceof CatalogPlaylist) || (data instanceof Playlist)) {
            return convertPlaylist(data);
        }
        return null;
    }

    private final ButtonNavigatorModel convertPlaylist(AbstractItem data) {
        if (!this.configuration.shouldShowUpsellOption(data)) {
            return null;
        }
        String asin = data.getAsin();
        String string = AmazonApplication.getContext().getResources().getString(R.string.dmusic_sonicrush_upsell_dialog);
        Intrinsics.checkNotNullExpressionValue(string, "getContext().resources.g…_sonicrush_upsell_dialog)");
        return new ButtonNavigatorModel(asin, string, "ShowUpsell", null, null, false, ButtonSizeKey.MEDIUM, ButtonStyleKey.GLASS_OUTLINE, SpacerKey.MEDIUM, null, 0, 1592, null);
    }
}
