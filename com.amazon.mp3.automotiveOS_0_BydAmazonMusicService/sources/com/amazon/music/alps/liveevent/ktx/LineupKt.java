package com.amazon.music.alps.liveevent.ktx;

import com.amazon.music.alps.liveevent.LineupEndpointType;
import com.amazon.music.platform.graphql.generated.fragment.Lineup;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;

/* JADX INFO: compiled from: Lineup.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\"\u0017\u0010\u0004\u001a\u0004\u0018\u00010\u0001*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0017\u0010\u0006\u001a\u0004\u0018\u00010\u0001*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0003\"\u0015\u0010\b\u001a\u00020\u0007*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\b\u0010\t\"\u0015\u0010\n\u001a\u00020\u0007*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\n\u0010\t¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Lineup;", "", "getPrimeVideoLink", "(Lcom/amazon/music/platform/graphql/generated/fragment/Lineup;)Ljava/lang/String;", "primeVideoLink", "getTwitchLink", "twitchLink", "", "isCurrent", "(Lcom/amazon/music/platform/graphql/generated/fragment/Lineup;)Z", "isNext", "DMMALPS_release"}, k = 2, mv = {1, 6, 0})
public final class LineupKt {
    public static final String getPrimeVideoLink(Lineup lineup) {
        List<Lineup.Endpoint> endpoints;
        Object next;
        Intrinsics.checkNotNullParameter(lineup, "<this>");
        List<Lineup.Endpoint> endpoints2 = lineup.getEndpoints();
        if ((endpoints2 == null || endpoints2.isEmpty()) || (endpoints = lineup.getEndpoints()) == null) {
            return null;
        }
        Iterator<T> it = endpoints.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            Lineup.Endpoint endpoint = (Lineup.Endpoint) next;
            if (LineupEndpointType.PRIMEVIDEO == LineupEndpointType.INSTANCE.from(endpoint == null ? null : endpoint.getType())) {
                break;
            }
        }
        Lineup.Endpoint endpoint2 = (Lineup.Endpoint) next;
        if (endpoint2 == null) {
            return null;
        }
        return endpoint2.getUrl();
    }

    public static final String getTwitchLink(Lineup lineup) {
        List<Lineup.Endpoint> endpoints;
        Object next;
        Intrinsics.checkNotNullParameter(lineup, "<this>");
        List<Lineup.Endpoint> endpoints2 = lineup.getEndpoints();
        if ((endpoints2 == null || endpoints2.isEmpty()) || (endpoints = lineup.getEndpoints()) == null) {
            return null;
        }
        Iterator<T> it = endpoints.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            Lineup.Endpoint endpoint = (Lineup.Endpoint) next;
            if (LineupEndpointType.TWITCH_ID == LineupEndpointType.INSTANCE.from(endpoint == null ? null : endpoint.getType())) {
                break;
            }
        }
        Lineup.Endpoint endpoint2 = (Lineup.Endpoint) next;
        if (endpoint2 == null) {
            return null;
        }
        return endpoint2.getUrl();
    }

    public static final boolean isCurrent(Lineup lineup) {
        DateTime dateTimeIso8601DateTime;
        DateTime dateTimeIso8601DateTime2;
        Intrinsics.checkNotNullParameter(lineup, "<this>");
        String scheduledStartDate = lineup.getScheduledStartDate();
        String scheduledEndDate = lineup.getScheduledEndDate();
        if (!(scheduledStartDate == null || scheduledStartDate.length() == 0)) {
            if ((scheduledEndDate == null || scheduledEndDate.length() == 0) || (dateTimeIso8601DateTime = StringKt.iso8601DateTime(scheduledStartDate)) == null || (dateTimeIso8601DateTime2 = StringKt.iso8601DateTime(scheduledEndDate)) == null) {
                return false;
            }
            DateTime dateTime = new DateTime();
            return dateTimeIso8601DateTime.compareTo((ReadableInstant) dateTime) <= 0 && dateTime.compareTo((ReadableInstant) dateTimeIso8601DateTime2) < 0;
        }
        return false;
    }

    public static final boolean isNext(Lineup lineup) {
        DateTime dateTimeIso8601DateTime;
        Intrinsics.checkNotNullParameter(lineup, "<this>");
        String scheduledStartDate = lineup.getScheduledStartDate();
        return ((scheduledStartDate == null || scheduledStartDate.length() == 0) || (dateTimeIso8601DateTime = StringKt.iso8601DateTime(scheduledStartDate)) == null || new DateTime().compareTo((ReadableInstant) dateTimeIso8601DateTime) >= 0) ? false : true;
    }
}
