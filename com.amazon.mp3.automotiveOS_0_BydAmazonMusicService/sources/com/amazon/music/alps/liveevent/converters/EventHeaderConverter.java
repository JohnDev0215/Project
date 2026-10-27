package com.amazon.music.alps.liveevent.converters;

import android.content.Context;
import androidx.core.os.EnvironmentCompat;
import com.amazon.music.alps.R$string;
import com.amazon.music.alps.liveevent.LiveEventStatus;
import com.amazon.music.alps.liveevent.ktx.LineupKt;
import com.amazon.music.alps.liveevent.ktx.LiveEventKt;
import com.amazon.music.alps.liveevent.ktx.MsaUrlKt;
import com.amazon.music.alps.liveevent.viewmodel.EventHeaderViewModel;
import com.amazon.music.msautils.MsaUrl;
import com.amazon.music.platform.graphql.generated.fragment.Lineup;
import com.amazon.music.platform.graphql.generated.fragment.LiveEvent;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.ButtonModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.enums.HeaderButtonActionType;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

/* JADX INFO: compiled from: EventHeaderConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0012\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0017\u001a\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0016¨\u0006\u001c"}, d2 = {"Lcom/amazon/music/alps/liveevent/converters/EventHeaderConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;", "data", "Lcom/amazon/music/alps/liveevent/viewmodel/EventHeaderViewModel;", "upcoming", "live", "between", "afterglow", "expired", EnvironmentCompat.MEDIA_UNKNOWN, "", "thumbnailWithLive", "thumbnailWithStartDate", "presentedBy", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/music/views/library/models/ButtonModel;", "getAddToCalendarButton", "()Lcom/amazon/music/views/library/models/ButtonModel;", "addToCalendarButton", "getShareButton", "shareButton", "<init>", "(Landroid/content/Context;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final class EventHeaderConverter implements SingleBaseModelConverter<LiveEvent> {
    private final Context context;

    /* JADX INFO: compiled from: EventHeaderConverter.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LiveEventStatus.values().length];
            iArr[LiveEventStatus.UPCOMING.ordinal()] = 1;
            iArr[LiveEventStatus.LIVE.ordinal()] = 2;
            iArr[LiveEventStatus.BETWEEN.ordinal()] = 3;
            iArr[LiveEventStatus.AFTERGLOW.ordinal()] = 4;
            iArr[LiveEventStatus.EXPIRED.ordinal()] = 5;
            iArr[LiveEventStatus.UNKNOWN.ordinal()] = 6;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private final EventHeaderViewModel expired(LiveEvent data) {
        return null;
    }

    private final EventHeaderViewModel unknown(LiveEvent data) {
        return null;
    }

    public EventHeaderConverter(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(LiveEvent data) {
        Intrinsics.checkNotNullParameter(data, "data");
        switch (WhenMappings.$EnumSwitchMapping$0[LiveEventStatus.INSTANCE.from(data.getStatus()).ordinal()]) {
            case 1:
                return upcoming(data);
            case 2:
                return live(data);
            case 3:
                return between(data);
            case 4:
                return afterglow(data);
            case 5:
                return expired(data);
            case 6:
                return unknown(data);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final EventHeaderViewModel upcoming(LiveEvent data) {
        return new EventHeaderViewModel(null, this.context.getString(R$string.livestream), data.getName(), LiveEventKt.timeInfo(data, this.context), presentedBy(data), thumbnailWithStartDate(data), null, null, getAddToCalendarButton(), getShareButton(), true, LiveEventKt.getNextLineup(data), 1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final EventHeaderViewModel live(LiveEvent data) {
        ButtonModel buttonModel;
        Lineup currentLineup = LiveEventKt.getCurrentLineup(data);
        String primeVideoLink = currentLineup == null ? null : LineupKt.getPrimeVideoLink(currentLineup);
        if (primeVideoLink == null || primeVideoLink.length() == 0) {
            buttonModel = null;
        } else {
            HeaderButtonActionType headerButtonActionType = HeaderButtonActionType.NAVIGATE;
            String string = this.context.getString(R$string.watch_on_prime_video);
            Lineup currentLineup2 = LiveEventKt.getCurrentLineup(data);
            buttonModel = new ButtonModel(null, null, string, null, null, null, headerButtonActionType, null, currentLineup2 == null ? null : LineupKt.getPrimeVideoLink(currentLineup2), null, 699, null);
        }
        Lineup currentLineup3 = LiveEventKt.getCurrentLineup(data);
        String twitchLink = currentLineup3 == null ? null : LineupKt.getTwitchLink(currentLineup3);
        if (!(twitchLink == null || twitchLink.length() == 0)) {
            HeaderButtonActionType headerButtonActionType2 = HeaderButtonActionType.NAVIGATE;
            String string2 = this.context.getString(R$string.watch_on_amazon_music);
            Lineup currentLineup4 = LiveEventKt.getCurrentLineup(data);
            buttonModel = new ButtonModel(null, null, string2, null, null, null, headerButtonActionType2, null, currentLineup4 != null ? LineupKt.getTwitchLink(currentLineup4) : null, null, 699, null);
        }
        return new EventHeaderViewModel(null, this.context.getString(R$string.livestream), data.getName(), LiveEventKt.timeInfo(data, this.context), presentedBy(data), thumbnailWithLive(data), buttonModel, buttonModel, null, getShareButton(), false, LiveEventKt.getNextLineup(data), 1, null);
    }

    private final EventHeaderViewModel between(LiveEvent data) {
        return upcoming(data);
    }

    private final EventHeaderViewModel afterglow(LiveEvent data) {
        new ButtonModel(null, null, this.context.getString(R$string.share), null, null, null, HeaderButtonActionType.NAVIGATE, null, null, null, 955, null);
        return new EventHeaderViewModel(null, this.context.getString(R$string.livestream), data.getName(), this.context.getString(R$string.show_is_over), null, thumbnailWithStartDate(data), null, null, null, null, false, null, 1, null);
    }

    private final String thumbnailWithLive(LiveEvent data) {
        String thumbnail = LiveEventKt.getThumbnail(data);
        if (thumbnail == null) {
            return null;
        }
        String string = this.context.getString(R$string.live_now_live);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.live_now_live)");
        String string2 = this.context.getString(R$string.live_now_now);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.live_now_now)");
        MsaUrl msaUrlWith = MsaUrl.with(thumbnail);
        Intrinsics.checkNotNullExpressionValue(msaUrlWith, "with(it)");
        return MsaUrlKt.toLiveEventUrl(msaUrlWith, string, string2);
    }

    private final String thumbnailWithStartDate(LiveEvent data) {
        String thumbnail = LiveEventKt.getThumbnail(data);
        if (thumbnail == null) {
            return null;
        }
        DateTime startDateTime = LiveEventKt.getStartDateTime(data);
        LocalDate localDate = startDateTime == null ? null : startDateTime.toLocalDate();
        String string = localDate == null ? null : localDate.toString(DateTimeFormat.forPattern("d"));
        String string2 = localDate != null ? localDate.toString(DateTimeFormat.forPattern("MMM")) : null;
        if (!(string == null || string.length() == 0)) {
            if (!(string2 == null || string2.length() == 0)) {
                MsaUrl msaUrlWith = MsaUrl.with(thumbnail);
                Intrinsics.checkNotNullExpressionValue(msaUrlWith, "with(it)");
                thumbnail = MsaUrlKt.toLiveEventUrl(msaUrlWith, string, string2);
            }
        }
        return thumbnail;
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0008  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.String presentedBy(com.amazon.music.platform.graphql.generated.fragment.LiveEvent r5) {
        /*
            r4 = this;
            java.lang.String r0 = r5.getPresentedBy()
            r1 = 1
            r2 = 0
            if (r0 != 0) goto La
        L8:
            r0 = r2
            goto L16
        La:
            int r0 = r0.length()
            if (r0 <= 0) goto L12
            r0 = r1
            goto L13
        L12:
            r0 = r2
        L13:
            if (r0 != r1) goto L8
            r0 = r1
        L16:
            if (r0 == 0) goto L29
            android.content.Context r0 = r4.context
            int r3 = com.amazon.music.alps.R$string.presented_by
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r5 = r5.getPresentedBy()
            r1[r2] = r5
            java.lang.String r5 = r0.getString(r3, r1)
            goto L2a
        L29:
            r5 = 0
        L2a:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.alps.liveevent.converters.EventHeaderConverter.presentedBy(com.amazon.music.platform.graphql.generated.fragment.LiveEvent):java.lang.String");
    }

    private final ButtonModel getAddToCalendarButton() {
        return new ButtonModel(null, null, this.context.getString(R$string.add_to_calendar), null, null, null, HeaderButtonActionType.NAVIGATE, null, null, null, 955, null);
    }

    private final ButtonModel getShareButton() {
        return new ButtonModel(null, null, this.context.getString(R$string.share), null, null, null, HeaderButtonActionType.NAVIGATE, null, null, null, 955, null);
    }
}
