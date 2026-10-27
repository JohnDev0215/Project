package com.amazon.music.curate.skyfire.util;

import CoreInterface.v1_0.Method;
import ExternalActionInterface.v1_0.ImmutableTriggerExternalActionMethod;
import Touch.WidgetsInterface.v1_0.DescriptiveListingElement;
import Touch.WidgetsInterface.v1_0.HorizontalItemElement;
import Touch.WidgetsInterface.v1_0.WidgetElement;
import Touch.WidgetsTemplateInterface.v1_0.WidgetsTemplate;
import android.net.Uri;
import com.amazon.music.find.api.RemoteSearchApi;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.VisualRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WidgetsHelper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/curate/skyfire/util/WidgetsHelper;", "", "()V", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WidgetsHelper {
    private static final String ALBUM_ARTIST_NAME = "albumArtistName";
    private static final String ALBUM_ASIN = "albumAsin";
    private static final String ALBUM_NAME = "albumName";
    private static final String ARTIST_ASIN = "artistAsin";
    private static final String ARTWORK_URL = "artworkUrl";
    private static final String ASIN = "asin";
    private static final String ATMOS_AVAILABLE = "atmosAvailable";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String HD_AVAILABLE = "hdAvailable";
    private static final String IMMERSIVE = "immersive";
    private static final String IS_EXPLICIT = "isExplicit";
    private static final int MAXIMUM_SUGGESTIONS = 6;
    private static final String PLAYLIST_ID = "playlistId";
    private static final String RA360_AVAILABLE = "ra360Available";
    private static final String TRACK_DURATION = "trackDuration";
    private static final String UHD_AVAILABLE = "uhdAvailable";

    @JvmStatic
    public static final List<BaseViewModel> convertToModels(WidgetsTemplate widgetsTemplate) {
        return INSTANCE.convertToModels(widgetsTemplate);
    }

    @JvmStatic
    public static final int getMaximumItems(WidgetsTemplate widgetsTemplate) {
        return INSTANCE.getMaximumItems(widgetsTemplate);
    }

    @JvmStatic
    public static final String getSuggestionsHeader(WidgetsTemplate widgetsTemplate) {
        return INSTANCE.getSuggestionsHeader(widgetsTemplate);
    }

    /* JADX INFO: compiled from: WidgetsHelper.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0007J\u0012\u0010\u0019\u001a\u00020\u000f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0007J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0017\u001a\u00020\u0018H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/curate/skyfire/util/WidgetsHelper$Companion;", "", "()V", "ALBUM_ARTIST_NAME", "", "ALBUM_ASIN", "ALBUM_NAME", "ARTIST_ASIN", Contexts.SetNowPlayingIntroMetadata.artworkUrl, "ASIN", "ATMOS_AVAILABLE", "HD_AVAILABLE", RemoteSearchApi.IMMERSIVE_ASSET_QUALITY, "IS_EXPLICIT", "MAXIMUM_SUGGESTIONS", "", "PLAYLIST_ID", "RA360_AVAILABLE", "TRACK_DURATION", "UHD_AVAILABLE", "convertToModels", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "widgetTemplate", "LTouch/WidgetsTemplateInterface/v1_0/WidgetsTemplate;", "getMaximumItems", "getSuggestionsHeader", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final int getMaximumItems(WidgetsTemplate widgetTemplate) {
            return 6;
        }

        private Companion() {
        }

        @JvmStatic
        public final List<BaseViewModel> convertToModels(WidgetsTemplate widgetTemplate) {
            Intrinsics.checkNotNullParameter(widgetTemplate, "widgetTemplate");
            WidgetElement widgetElement = widgetTemplate.widgets().get(0);
            if (widgetElement == null) {
                throw new NullPointerException("null cannot be cast to non-null type Touch.WidgetsInterface.v1_0.DescriptiveListingElement");
            }
            List<HorizontalItemElement> listItems = ((DescriptiveListingElement) widgetElement).items();
            Intrinsics.checkNotNullExpressionValue(listItems, "suggestionsList.items()");
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listItems, 10));
            for (HorizontalItemElement horizontalItemElement : listItems) {
                Method method = horizontalItemElement.onAddSelected().get(0);
                if (method == null) {
                    throw new NullPointerException("null cannot be cast to non-null type ExternalActionInterface.v1_0.ImmutableTriggerExternalActionMethod");
                }
                Map<String, String> mapContext = ((ImmutableTriggerExternalActionMethod) method).context();
                String strPrimaryText = horizontalItemElement.primaryText();
                String strSecondaryText = horizontalItemElement.secondaryText();
                ArtworkFrameModel artworkFrameModel = new ArtworkFrameModel(null, mapContext.get(WidgetsHelper.ARTWORK_URL), null, null, null, null, null, null, null, null, null, null, 4093, null);
                String str = mapContext.get(WidgetsHelper.ASIN);
                Uri EMPTY = Uri.EMPTY;
                Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
                TrackMetadata trackMetadata = new TrackMetadata(null, EMPTY, null, mapContext.get(WidgetsHelper.ASIN), horizontalItemElement.primaryText(), mapContext.get(WidgetsHelper.ARTWORK_URL), null, mapContext.get(WidgetsHelper.ALBUM_ASIN), mapContext.get(WidgetsHelper.ALBUM_NAME), mapContext.get(WidgetsHelper.ALBUM_ARTIST_NAME), mapContext.get("artistAsin"), horizontalItemElement.secondaryText(), null, null, null, null, null, Boolean.valueOf(Boolean.parseBoolean(mapContext.get("isExplicit"))), null, null, "AUDIO", null, 0, null, null, null, null, null, null, false, null, true, null, null, null, null, 2146299973, 15, null);
                Intrinsics.checkNotNullExpressionValue(strPrimaryText, "primaryText()");
                arrayList.add(new VisualRowItemModel(null, strPrimaryText, strSecondaryText, null, null, null, null, null, null, null, null, null, null, artworkFrameModel, null, null, str, null, null, null, null, Boolean.FALSE, false, null, 2, trackMetadata, false, true, false, null, false, false, -186720263, null));
            }
            return arrayList;
        }

        @JvmStatic
        public final String getSuggestionsHeader(WidgetsTemplate widgetTemplate) {
            Intrinsics.checkNotNullParameter(widgetTemplate, "widgetTemplate");
            WidgetElement widgetElement = widgetTemplate.widgets().get(0);
            if (widgetElement == null) {
                throw new NullPointerException("null cannot be cast to non-null type Touch.WidgetsInterface.v1_0.DescriptiveListingElement");
            }
            return ((DescriptiveListingElement) widgetElement).primaryHeader();
        }
    }
}
