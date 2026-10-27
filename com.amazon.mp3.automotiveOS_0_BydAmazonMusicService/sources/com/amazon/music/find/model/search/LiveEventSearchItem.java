package com.amazon.music.find.model.search;

import android.content.Context;
import com.amazon.music.find.R;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.msautils.MsaUrl;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt__MathJVMKt;

/* JADX INFO: compiled from: LiveEventSearchItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 <2\u00020\u0001:\u0001<BI\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\u0017\u0010.\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\tHÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003JU\u00102\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u00103\u001a\u00020\u001e2\b\u00104\u001a\u0004\u0018\u000105HÖ\u0003J\u000e\u00106\u001a\u00020\u00032\u0006\u00107\u001a\u000208J\t\u00109\u001a\u00020:HÖ\u0001J\t\u0010;\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\f\u001a\u0004\u0018\u00010\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\"\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R(\u0010\u0019\u001a\u0004\u0018\u00010\r2\b\u0010\u0018\u001a\u0004\u0018\u00010\r8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u000f\"\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001fR\u0014\u0010 \u001a\u00020\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u001fR\u0014\u0010!\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u0011R\u0016\u0010#\u001a\u0004\u0018\u00010$8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0016\u0010'\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0014\u0010\n\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0011¨\u0006="}, d2 = {"Lcom/amazon/music/find/model/search/LiveEventSearchItem;", "Lcom/amazon/music/find/model/search/SearchItem;", "artworkUri", "", "blockRef", "contentInfoMap", "", ContentUtils.KEY_EVENT_ID, "startDate", "Ljava/util/Date;", "title", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/util/Date;Ljava/lang/String;)V", "artworkId", "", "getArtworkId", "()Ljava/lang/Long;", "getArtworkUri", "()Ljava/lang/String;", "asin", "getAsin", "getBlockRef", "getContentInfoMap", "()Ljava/util/Map;", "getEventId", "value", "id", "getId", "setId", "(Ljava/lang/Long;)V", "isAvailable", "", "()Z", "isInLibrary", "itemTypeName", "getItemTypeName", "playmodeEligibility", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "getPlaymodeEligibility", "()Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", PageUriUtils.SOURCE_QUERY_PARAM, "getSource", "getStartDate", "()Ljava/util/Date;", "getTitle", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "", "getThumbnailImage", "context", "Landroid/content/Context;", "hashCode", "", "toString", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class LiveEventSearchItem implements SearchItem {
    public static final String LIVE_THUMBNAIL_URL = "https://m.media-amazon.com/images/G/01/alps/0tKQM4mEwLIOdFlg/live_3x.png";
    public static final String UPCOMING_THUMBNAIL_URL = "https://m.media-amazon.com/images/G/01/alps/0tKQM4mEwLIOdFlg/grey_bck_3x.png";
    private final String artworkUri;
    private final String blockRef;
    private final Map<String, String> contentInfoMap;
    private final String eventId;
    private final Date startDate;
    private final String title;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LiveEventSearchItem copy$default(LiveEventSearchItem liveEventSearchItem, String str, String str2, Map map, String str3, Date date, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = liveEventSearchItem.getArtworkUri();
        }
        if ((i & 2) != 0) {
            str2 = liveEventSearchItem.getBlockRef();
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            map = liveEventSearchItem.getContentInfoMap();
        }
        Map map2 = map;
        if ((i & 8) != 0) {
            str3 = liveEventSearchItem.eventId;
        }
        String str6 = str3;
        if ((i & 16) != 0) {
            date = liveEventSearchItem.startDate;
        }
        Date date2 = date;
        if ((i & 32) != 0) {
            str4 = liveEventSearchItem.getTitle();
        }
        return liveEventSearchItem.copy(str, str5, map2, str6, date2, str4);
    }

    public final String component1() {
        return getArtworkUri();
    }

    public final String component2() {
        return getBlockRef();
    }

    public final Map<String, String> component3() {
        return getContentInfoMap();
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getEventId() {
        return this.eventId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Date getStartDate() {
        return this.startDate;
    }

    public final String component6() {
        return getTitle();
    }

    public final LiveEventSearchItem copy(String artworkUri, String blockRef, Map<String, String> contentInfoMap, String eventId, Date startDate, String title) {
        Intrinsics.checkNotNullParameter(blockRef, "blockRef");
        Intrinsics.checkNotNullParameter(eventId, "eventId");
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(title, "title");
        return new LiveEventSearchItem(artworkUri, blockRef, contentInfoMap, eventId, startDate, title);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LiveEventSearchItem)) {
            return false;
        }
        LiveEventSearchItem liveEventSearchItem = (LiveEventSearchItem) other;
        return Intrinsics.areEqual(getArtworkUri(), liveEventSearchItem.getArtworkUri()) && Intrinsics.areEqual(getBlockRef(), liveEventSearchItem.getBlockRef()) && Intrinsics.areEqual(getContentInfoMap(), liveEventSearchItem.getContentInfoMap()) && Intrinsics.areEqual(this.eventId, liveEventSearchItem.eventId) && Intrinsics.areEqual(this.startDate, liveEventSearchItem.startDate) && Intrinsics.areEqual(getTitle(), liveEventSearchItem.getTitle());
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public Long getArtworkId() {
        return null;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public String getAsin() {
        return null;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public Long getId() {
        return null;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public String getItemTypeName() {
        return "LiveEvent";
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public PlaymodeEligibility getPlaymodeEligibility() {
        return null;
    }

    @Override // com.amazon.music.find.model.search.SearchItem, com.amazon.mp3.library.item.CatalogContent
    public String getSource() {
        return null;
    }

    public int hashCode() {
        return ((((((((((getArtworkUri() == null ? 0 : getArtworkUri().hashCode()) * 31) + getBlockRef().hashCode()) * 31) + (getContentInfoMap() != null ? getContentInfoMap().hashCode() : 0)) * 31) + this.eventId.hashCode()) * 31) + this.startDate.hashCode()) * 31) + getTitle().hashCode();
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    /* JADX INFO: renamed from: isAvailable */
    public boolean getIsAvailable() {
        return true;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    /* JADX INFO: renamed from: isInLibrary */
    public boolean getIsInLibrary() {
        return false;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public void setId(Long l) {
    }

    public String toString() {
        return "LiveEventSearchItem(artworkUri=" + ((Object) getArtworkUri()) + ", blockRef=" + getBlockRef() + ", contentInfoMap=" + getContentInfoMap() + ", eventId=" + this.eventId + ", startDate=" + this.startDate + ", title=" + getTitle() + ')';
    }

    public LiveEventSearchItem(String str, String blockRef, Map<String, String> map, String eventId, Date startDate, String title) {
        Intrinsics.checkNotNullParameter(blockRef, "blockRef");
        Intrinsics.checkNotNullParameter(eventId, "eventId");
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(title, "title");
        this.artworkUri = str;
        this.blockRef = blockRef;
        this.contentInfoMap = map;
        this.eventId = eventId;
        this.startDate = startDate;
        this.title = title;
    }

    public /* synthetic */ LiveEventSearchItem(String str, String str2, Map map, String str3, Date date, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, str2, (i & 4) != 0 ? null : map, str3, date, str4);
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public String getArtworkUri() {
        return this.artworkUri;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public String getBlockRef() {
        return this.blockRef;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public Map<String, String> getContentInfoMap() {
        return this.contentInfoMap;
    }

    public final String getEventId() {
        return this.eventId;
    }

    public final Date getStartDate() {
        return this.startDate;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public String getTitle() {
        return this.title;
    }

    public final String getThumbnailImage(Context context) {
        String upperCase;
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.startDate.getTime() <= System.currentTimeMillis()) {
            return LIVE_THUMBNAIL_URL;
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.artwork_default_dimen);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getStartDate());
        String strValueOf = String.valueOf(calendar.get(5));
        String displayName = calendar.getDisplayName(2, 1, Locale.getDefault());
        if (displayName == null) {
            upperCase = null;
        } else {
            upperCase = displayName.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        }
        double d = dimensionPixelSize;
        int iRoundToInt = MathKt__MathJVMKt.roundToInt(0.1d * d);
        String url = MsaUrl.with(UPCOMING_THUMBNAIL_URL).scaleToSquare(dimensionPixelSize).centeredText(strValueOf, 0, iRoundToInt, 0, 0, "AmazonEmberBold", context.getResources().getDimensionPixelSize(R.dimen.text_size_14), 244, 62, 33, 5).centeredText(upperCase, 0, MathKt__MathJVMKt.roundToInt(d * 0.5d), 0, 0, "AmazonEmberBold", context.getResources().getDimensionPixelSize(R.dimen.text_size_12), 255, 255, 255, 5).qualityPercent(100).toUrl();
        Intrinsics.checkNotNullExpressionValue(url, "{\n            val fontNa…       .toUrl()\n        }");
        return url;
    }
}
