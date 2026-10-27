package com.amazon.music.alps.liveevent.ktx;

import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import com.amazon.music.alps.config.URLConfig;
import com.amazon.music.alps.liveevent.LiveEventStatus;
import com.amazon.music.alps.liveevent.enums.ImageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageSubType;
import com.amazon.music.platform.graphql.generated.fragment.Image;
import com.amazon.music.platform.graphql.generated.fragment.Lineup;
import com.amazon.music.platform.graphql.generated.fragment.LiveEvent;
import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/* JADX INFO: compiled from: LiveEvent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0012\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001\u001a\n\u0010\b\u001a\u00020\u0007*\u00020\u0000\"\u0017\u0010\u000b\u001a\u0004\u0018\u00010\u0005*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\t\u0010\n\"\u0017\u0010\r\u001a\u0004\u0018\u00010\u0005*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\f\u0010\n\"\u0017\u0010\u000f\u001a\u0004\u0018\u00010\u0005*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\n\"\u0017\u0010\u0013\u001a\u0004\u0018\u00010\u0010*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012\"\u0017\u0010\u0015\u001a\u0004\u0018\u00010\u0010*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012\"\u001d\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019\"\u0017\u0010\u001d\u001a\u0004\u0018\u00010\u0017*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\"\u0017\u0010\u001f\u001a\u0004\u0018\u00010\u0017*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001c\"\u0015\u0010!\u001a\u00020 *\u00020\u00008F¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;", "Landroid/content/Context;", "context", "", "createCalendarEvent", "", "timeInfo", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageSubType;", "pageSubType", "getThumbnail", "(Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;)Ljava/lang/String;", "thumbnail", "getBackgroundImageUrl", "backgroundImageUrl", "getSharingImageUrl", "sharingImageUrl", "Lorg/joda/time/DateTime;", "getStartDateTime", "(Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;)Lorg/joda/time/DateTime;", "startDateTime", "getEndDateTime", "endDateTime", "", "Lcom/amazon/music/platform/graphql/generated/fragment/Lineup;", "getLineups", "(Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;)Ljava/util/List;", "lineups", "getCurrentLineup", "(Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;)Lcom/amazon/music/platform/graphql/generated/fragment/Lineup;", "currentLineup", "getNextLineup", "nextLineup", "", "isFestival", "(Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;)Z", "DMMALPS_release"}, k = 2, mv = {1, 6, 0})
public final class LiveEventKt {

    /* JADX INFO: compiled from: LiveEvent.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LiveEventStatus.values().length];
            iArr[LiveEventStatus.UPCOMING.ordinal()] = 1;
            iArr[LiveEventStatus.LIVE.ordinal()] = 2;
            iArr[LiveEventStatus.BETWEEN.ordinal()] = 3;
            iArr[LiveEventStatus.AFTERGLOW.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void createCalendarEvent(LiveEvent liveEvent, Context context) {
        Intrinsics.checkNotNullParameter(liveEvent, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        DateTime startDateTime = getStartDateTime(liveEvent);
        Long lValueOf = startDateTime == null ? null : Long.valueOf(startDateTime.getMillis());
        DateTime endDateTime = getEndDateTime(liveEvent);
        Long lValueOf2 = endDateTime != null ? Long.valueOf(endDateTime.getMillis()) : null;
        if (lValueOf == null || lValueOf2 == null) {
            return;
        }
        String str = URLConfig.SHARE_BASE_URL.getValue() + "live/events/" + ((Object) liveEvent.getId()) + "?ref=dm_aml_cal";
        String str2 = str + "\n\n" + ((Object) liveEvent.getDescription());
        Intent intent = new Intent("android.intent.action.INSERT");
        intent.setData(CalendarContract.Events.CONTENT_URI);
        intent.putExtra("beginTime", lValueOf.longValue());
        intent.putExtra("endTime", lValueOf2.longValue());
        intent.putExtra("title", liveEvent.getName());
        intent.putExtra(MediaTrack.ROLE_DESCRIPTION, str2);
        intent.putExtra("eventLocation", str);
        context.startActivity(intent);
    }

    public static final String getThumbnail(LiveEvent liveEvent) {
        LiveEvent.Image image;
        Image image2;
        Intrinsics.checkNotNullParameter(liveEvent, "<this>");
        List<LiveEvent.Image> images = liveEvent.getImages();
        if (images == null || (image = (LiveEvent.Image) CollectionsKt___CollectionsKt.getOrNull(images, 0)) == null || (image2 = image.getImage()) == null) {
            return null;
        }
        return image2.getUrl();
    }

    public static final String getBackgroundImageUrl(LiveEvent liveEvent) {
        Object next;
        Image image;
        Image image2;
        Intrinsics.checkNotNullParameter(liveEvent, "<this>");
        List<LiveEvent.Image> images = liveEvent.getImages();
        if (images == null) {
            return null;
        }
        Iterator<T> it = images.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            LiveEvent.Image image3 = (LiveEvent.Image) next;
            if (ImageType.INSTANCE.from((image3 != null && (image2 = image3.getImage()) != null) ? image2.getImageType() : null) == ImageType.BACKGROUND) {
                break;
            }
        }
        LiveEvent.Image image4 = (LiveEvent.Image) next;
        if (image4 == null || (image = image4.getImage()) == null) {
            return null;
        }
        return image.getUrl();
    }

    public static final String getSharingImageUrl(LiveEvent liveEvent) {
        Object next;
        Image image;
        Image image2;
        Intrinsics.checkNotNullParameter(liveEvent, "<this>");
        List<LiveEvent.Image> images = liveEvent.getImages();
        if (images == null) {
            return null;
        }
        Iterator<T> it = images.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            LiveEvent.Image image3 = (LiveEvent.Image) next;
            if (ImageType.INSTANCE.from((image3 != null && (image2 = image3.getImage()) != null) ? image2.getImageType() : null) == ImageType.SHARING) {
                break;
            }
        }
        LiveEvent.Image image4 = (LiveEvent.Image) next;
        if (image4 == null || (image = image4.getImage()) == null) {
            return null;
        }
        return image.getUrl();
    }

    public static final String timeInfo(LiveEvent liveEvent, Context context) {
        DateTime endDateTime;
        Intrinsics.checkNotNullParameter(liveEvent, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        DateTime startDateTime = getStartDateTime(liveEvent);
        if (startDateTime == null || (endDateTime = getEndDateTime(liveEvent)) == null) {
            return null;
        }
        DateTimeFormatter dateTimeFormatterMediumDate = DateTimeFormat.mediumDate();
        String string = startDateTime.toString(dateTimeFormatterMediumDate);
        if (isFestival(liveEvent)) {
            return ((Object) string) + " - " + ((Object) endDateTime.toString(dateTimeFormatterMediumDate));
        }
        return ((Object) string) + " • " + ((Object) startDateTime.toString(DateTimeFormat.forPattern("hh:mm a z")));
    }

    public static final DateTime getStartDateTime(LiveEvent liveEvent) {
        Intrinsics.checkNotNullParameter(liveEvent, "<this>");
        String startDate = liveEvent.getStartDate();
        if (startDate == null) {
            return null;
        }
        return StringKt.iso8601DateTime(startDate);
    }

    public static final DateTime getEndDateTime(LiveEvent liveEvent) {
        Intrinsics.checkNotNullParameter(liveEvent, "<this>");
        String endDate = liveEvent.getEndDate();
        if (endDate == null) {
            return null;
        }
        return StringKt.iso8601DateTime(endDate);
    }

    public static final List<Lineup> getLineups(LiveEvent liveEvent) {
        Intrinsics.checkNotNullParameter(liveEvent, "<this>");
        List<LiveEvent.LineupGroup> lineupGroups = liveEvent.getLineupGroups();
        if (lineupGroups == null) {
            return null;
        }
        ArrayList<LiveEvent.Lineup> arrayList = new ArrayList();
        for (LiveEvent.LineupGroup lineupGroup : lineupGroups) {
            List<LiveEvent.Lineup> lineups = lineupGroup == null ? null : lineupGroup.getLineups();
            if (lineups == null) {
                lineups = CollectionsKt__CollectionsKt.emptyList();
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, lineups);
        }
        ArrayList arrayList2 = new ArrayList();
        for (LiveEvent.Lineup lineup : arrayList) {
            Lineup lineup2 = lineup == null ? null : lineup.getLineup();
            if (lineup2 != null) {
                arrayList2.add(lineup2);
            }
        }
        return arrayList2;
    }

    public static final Lineup getCurrentLineup(LiveEvent liveEvent) {
        Intrinsics.checkNotNullParameter(liveEvent, "<this>");
        List<Lineup> lineups = getLineups(liveEvent);
        Object obj = null;
        if (lineups == null) {
            return null;
        }
        Iterator<T> it = lineups.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (LineupKt.isCurrent((Lineup) next)) {
                obj = next;
                break;
            }
        }
        return (Lineup) obj;
    }

    public static final Lineup getNextLineup(LiveEvent liveEvent) {
        Intrinsics.checkNotNullParameter(liveEvent, "<this>");
        List<Lineup> lineups = getLineups(liveEvent);
        Object obj = null;
        if (lineups == null) {
            return null;
        }
        Iterator<T> it = lineups.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (LineupKt.isNext((Lineup) next)) {
                obj = next;
                break;
            }
        }
        return (Lineup) obj;
    }

    public static final boolean isFestival(LiveEvent liveEvent) {
        Intrinsics.checkNotNullParameter(liveEvent, "<this>");
        List<Lineup> lineups = getLineups(liveEvent);
        if (lineups == null || lineups.isEmpty()) {
            return false;
        }
        List<Lineup> lineups2 = getLineups(liveEvent);
        Integer numValueOf = lineups2 == null ? null : Integer.valueOf(lineups2.size());
        Intrinsics.checkNotNull(numValueOf);
        return numValueOf.intValue() > 1;
    }

    public static final PageSubType pageSubType(LiveEvent liveEvent) {
        Intrinsics.checkNotNullParameter(liveEvent, "<this>");
        int i = WhenMappings.$EnumSwitchMapping$0[LiveEventStatus.INSTANCE.from(liveEvent.getStatus()).ordinal()];
        if (i == 1) {
            return PageSubType.LIVE_EVENT_UPCOMING;
        }
        if (i == 2) {
            return PageSubType.LIVE_EVENT_LIVE;
        }
        if (i == 3) {
            return PageSubType.LIVE_EVENT_BETWEEN;
        }
        if (i == 4) {
            return PageSubType.LIVE_EVENT_AFTERGLOW;
        }
        return PageSubType.UNAVAILABLE_UNAVAILABLE;
    }
}
