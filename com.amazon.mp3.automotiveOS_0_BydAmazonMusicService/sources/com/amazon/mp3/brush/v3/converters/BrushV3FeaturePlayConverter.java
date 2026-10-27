package com.amazon.mp3.brush.v3.converters;

import com.amazon.mp3.AmazonApplication;
import com.amazon.music.page.api.model.Entity;
import com.amazon.music.page.api.model.Track;
import com.amazon.music.page.api.model.Widget;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.FeaturePlayModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushV3FeaturePlayConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u001d\u0010\u001eJ\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J*\u0010\u000b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0002J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u001a\u0010\u0010\u001a\u00020\u000f8\u0006X\u0086D¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\"\u0010\u0014\u001a\u00020\f8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001b\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u001c¨\u0006\u001f"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3FeaturePlayConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/page/api/model/Widget;", "widget", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convertTrackWidget", "Lcom/amazon/music/page/api/model/Track;", "item", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadataList", "convertTrack", "", "getUniqueArtistNames", "convert", "", "MAXIMUM_ARTIST_NAMES", "I", "getMAXIMUM_ARTIST_NAMES", "()I", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "", "isFacetedNavUX", "Z", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushV3FeaturePlayConverter implements SingleBaseModelConverter<Widget> {
    private final int MAXIMUM_ARTIST_NAMES = 3;
    private boolean isFacetedNavUX;
    public String title;

    public final int getMAXIMUM_ARTIST_NAMES() {
        return this.MAXIMUM_ARTIST_NAMES;
    }

    public final String getTitle() {
        String str = this.title;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("title");
        return null;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Widget widget) {
        Intrinsics.checkNotNullParameter(widget, "widget");
        List<Entity> entities = widget.getContent().getEntities();
        Intrinsics.checkNotNullExpressionValue(entities, "widget.content.entities");
        Entity entity = (Entity) CollectionsKt___CollectionsKt.first((List) entities);
        String title = widget.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "widget.title");
        setTitle(title);
        this.isFacetedNavUX = AmazonApplication.getCapabilities().isFacetedNavigationEnabled();
        if (entity.getTrack() != null) {
            return convertTrackWidget(widget);
        }
        return null;
    }

    private final BaseViewModel convertTrackWidget(Widget widget) {
        ArrayList arrayList = new ArrayList();
        Iterator<Entity> it = widget.getContent().getEntities().iterator();
        while (it.hasNext()) {
            Track track = it.next().getTrack();
            Intrinsics.checkNotNullExpressionValue(track, "entity.track");
            BaseViewModel baseViewModelConvertTrack = convertTrack(widget, track, arrayList);
            if (baseViewModelConvertTrack == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.FeaturePlayModel");
            }
            arrayList.add(((FeaturePlayModel) baseViewModelConvertTrack).getMetadata());
        }
        List<Entity> entities = widget.getContent().getEntities();
        Intrinsics.checkNotNullExpressionValue(entities, "widget.content.entities");
        Track track2 = ((Entity) CollectionsKt___CollectionsKt.first((List) entities)).getTrack();
        Intrinsics.checkNotNullExpressionValue(track2, "widget.content.entities.first().track");
        return convertTrack(widget, track2, arrayList);
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x0136  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.amazon.music.views.library.models.base.BaseViewModel convertTrack(com.amazon.music.page.api.model.Widget r60, com.amazon.music.page.api.model.Track r61, java.util.List<com.amazon.music.views.library.metadata.ContentMetadata> r62) {
        /*
            Method dump skipped, instruction units count: 522
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.brush.v3.converters.BrushV3FeaturePlayConverter.convertTrack(com.amazon.music.page.api.model.Widget, com.amazon.music.page.api.model.Track, java.util.List):com.amazon.music.views.library.models.base.BaseViewModel");
    }

    private final String getUniqueArtistNames(Widget widget) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<Entity> it = widget.getContent().getEntities().iterator();
        String strStringPlus = "";
        while (it.hasNext()) {
            String artistName = it.next().getTrack().getSecondaryTitle();
            if (!linkedHashSet.contains(artistName)) {
                if (Intrinsics.areEqual(strStringPlus, "")) {
                    strStringPlus = Intrinsics.stringPlus(strStringPlus, artistName);
                } else {
                    strStringPlus = strStringPlus + ", " + ((Object) artistName);
                }
                Intrinsics.checkNotNullExpressionValue(artistName, "artistName");
                linkedHashSet.add(artistName);
            }
            if (linkedHashSet.size() >= this.MAXIMUM_ARTIST_NAMES) {
                break;
            }
        }
        return strStringPlus;
    }
}
