package com.amazon.mp3.find.util;

import android.net.Uri;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.find.view.SearchBrushFragment;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.util.MetricsUtil;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.deeplink.UnknownDeeplinkException;
import com.amazon.music.destination.ProgramDetailsDestination;
import com.amazon.music.destination.ProgramsDestination;
import com.amazon.music.destination.core.Destination;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.router.UnknownDestinationException;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.NavigationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: SearchClickEventUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0007J;\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007¢\u0006\u0002\u0010\u0012JE\u0010\u0013\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\f\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007¢\u0006\u0002\u0010\u0018J*\u0010\u0013\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007J:\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\"\u0010 \u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0003¨\u0006\""}, d2 = {"Lcom/amazon/mp3/find/util/SearchClickEventUtil;", "", "()V", "getPlaybackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "fallBack", "handleTrackClickEvent", "", "fragment", "Landroidx/fragment/app/Fragment;", "contentMetadata", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "contentPosition", "", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "(Landroidx/fragment/app/Fragment;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/music/views/library/metadata/TrackMetadata;Ljava/lang/Integer;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;)V", "sendClickEvent", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "entityPos", "(Landroidx/fragment/app/Fragment;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;Lcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/lang/Integer;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;)V", "navigationMetadata", "Lcom/amazon/music/views/library/metadata/NavigationMetadata;", "blockRef", "", ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", ContextMappingConstants.ENTITY_ID, "sendSportClickEvent", ImagesContract.URL, "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchClickEventUtil {
    public static final SearchClickEventUtil INSTANCE = new SearchClickEventUtil();

    /* JADX INFO: compiled from: SearchClickEventUtil.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PageType.values().length];
            iArr[PageType.BROWSE_SEARCH.ordinal()] = 1;
            iArr[PageType.SEE_ALL_SEARCH_BROWSE_TRACKS.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private SearchClickEventUtil() {
    }

    @JvmStatic
    @JvmOverloads
    public static final void handleTrackClickEvent(Fragment fragment, PageType pageType, TrackMetadata contentMetadata, Integer contentPosition, InteractionType interactionType) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        if (contentMetadata == null || contentPosition == null) {
            return;
        }
        sendClickEvent(fragment, pageType, Intrinsics.areEqual(contentMetadata.getAssetType(), PrimePlaylistTracksTable.AssetType.VIDEO.toString()) ? ActionType.PLAY_VIDEO : ActionType.PLAY_SONG, contentMetadata, contentPosition, interactionType);
    }

    public static /* synthetic */ void sendClickEvent$default(Fragment fragment, PageType pageType, NavigationMetadata navigationMetadata, InteractionType interactionType, int i, Object obj) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        if ((i & 8) != 0) {
            interactionType = InteractionType.TAP;
        }
        sendClickEvent(fragment, pageType, navigationMetadata, interactionType);
    }

    @JvmStatic
    @JvmOverloads
    public static final void sendClickEvent(Fragment fragment, PageType pageType, NavigationMetadata navigationMetadata, InteractionType interactionType) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(navigationMetadata, "navigationMetadata");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        if (fragment instanceof SearchBrushFragment) {
            SearchBrushFragment searchBrushFragment = (SearchBrushFragment) fragment;
            SearchBrushFragment.trackNavigationMetadataUiClickEvent$default(searchBrushFragment, searchBrushFragment.getCurrentMode(), navigationMetadata, interactionType, null, 8, null);
            return;
        }
        String target = navigationMetadata.getTarget();
        String viewId = navigationMetadata.getViewId();
        if (StringsKt__StringsKt.contains$default((CharSequence) target, (CharSequence) "sports", false, 2, (Object) null)) {
            sendSportClickEvent(pageType, target, viewId);
        } else {
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(target, viewId).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).build());
        }
    }

    @JvmStatic
    private static final void sendSportClickEvent(PageType pageType, String url, String blockRef) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        ActionType actionType;
        Destination destination = DeeplinksManager.get(AmazonApplication.getContext()).getDestination(Uri.parse(url));
        if (destination instanceof ProgramsDestination) {
            sendClickEvent(ActionType.SELECT_COMPETITION, blockRef, EntityIdType.COMPETITION_ID, ((ProgramsDestination) destination).getCompetitionId(), pageType);
            return;
        }
        if (destination instanceof ProgramDetailsDestination) {
            if (StringsKt__StringsKt.contains$default((CharSequence) url, (CharSequence) "matches", false, 2, (Object) null)) {
                actionType = ActionType.SELECT_MATCH;
            } else if (StringsKt__StringsKt.contains$default((CharSequence) url, (CharSequence) "conferences", false, 2, (Object) null)) {
                actionType = ActionType.SELECT_CONFERENCE;
            } else if (!StringsKt__StringsKt.contains$default((CharSequence) url, (CharSequence) "shows", false, 2, (Object) null)) {
                return;
            } else {
                actionType = ActionType.SELECT_SHOW;
            }
            sendClickEvent(actionType, blockRef, EntityIdType.PROGRAM_ID, ((ProgramDetailsDestination) destination).getProgramId(), pageType);
        }
    }

    public static /* synthetic */ void sendClickEvent$default(Fragment fragment, PageType pageType, ActionType actionType, ContentMetadata contentMetadata, Integer num, InteractionType interactionType, int i, Object obj) {
        if ((i & 16) != 0) {
            num = null;
        }
        Integer num2 = num;
        if ((i & 32) != 0) {
            interactionType = InteractionType.TAP;
        }
        sendClickEvent(fragment, pageType, actionType, contentMetadata, num2, interactionType);
    }

    @JvmStatic
    @JvmOverloads
    public static final void sendClickEvent(Fragment fragment, PageType pageType, ActionType actionType, ContentMetadata contentMetadata, Integer entityPos, InteractionType interactionType) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        MetricsUtil.emitLatencyStartLegMetricForAllowedActionTypes(actionType);
        if (fragment instanceof SearchBrushFragment) {
            SearchBrushFragment searchBrushFragment = (SearchBrushFragment) fragment;
            searchBrushFragment.trackContentMetadataUiClickEvent(searchBrushFragment.getCurrentMode(), actionType, contentMetadata, interactionType, entityPos);
        } else {
            if (contentMetadata == null) {
                return;
            }
            sendClickEvent$default(actionType, contentMetadata.getViewId(), null, null, pageType, 12, null);
        }
    }

    public static /* synthetic */ void sendClickEvent$default(ActionType actionType, String str, EntityIdType entityIdType, String str2, PageType pageType, int i, Object obj) {
        if ((i & 4) != 0) {
            entityIdType = null;
        }
        if ((i & 8) != 0) {
            str2 = null;
        }
        sendClickEvent(actionType, str, entityIdType, str2, pageType);
    }

    @JvmStatic
    public static final void sendClickEvent(ActionType actionType, String blockRef, EntityIdType entityIdType, String entityId, PageType pageType) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withBlockRef(blockRef).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).withPageType(pageType).withEntityIdType(entityIdType).withEntityId(entityId).build());
    }

    @JvmStatic
    public static final PlaybackPageType getPlaybackPageType(PageType pageType, PlaybackPageType fallBack) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(fallBack, "fallBack");
        int i = WhenMappings.$EnumSwitchMapping$0[pageType.ordinal()];
        if (i != 1) {
            return i != 2 ? fallBack : PlaybackPageType.PRIME_BROWSE_SEARCH_SONGS;
        }
        return PlaybackPageType.PRIME_BROWSE_SEARCH;
    }
}
