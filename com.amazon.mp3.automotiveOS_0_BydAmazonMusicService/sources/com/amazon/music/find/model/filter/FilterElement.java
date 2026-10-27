package com.amazon.music.find.model.filter;

import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.google.firebase.perf.util.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FilterElement.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\b\u0010\u0010\u001a\u00020\u0000H\u0016J\u0013\u0010\u0011\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\u0006\u0010\u0016\u001a\u00020\u0017J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\nR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/find/model/filter/FilterElement;", "Lcom/amazon/music/find/model/filter/AbstractFilterElement;", "id", "", "title", Constants.ENABLE_DISABLE, "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getId", "()Ljava/lang/String;", "()Z", "getTitle", "component1", "component2", "component3", "copy", "deepCopy", "equals", "other", "", "hashCode", "", "toContentEncoding", "Lcom/amazon/music/contentEncoding/ContentEncoding;", "toString", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class FilterElement extends AbstractFilterElement {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String id;
    private final boolean isEnabled;
    private final String title;

    public static /* synthetic */ FilterElement copy$default(FilterElement filterElement, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = filterElement.getId();
        }
        if ((i & 2) != 0) {
            str2 = filterElement.getTitle();
        }
        if ((i & 4) != 0) {
            z = filterElement.getIsEnabled();
        }
        return filterElement.copy(str, str2, z);
    }

    public final String component1() {
        return getId();
    }

    public final String component2() {
        return getTitle();
    }

    public final boolean component3() {
        return getIsEnabled();
    }

    public final FilterElement copy(String id, String title, boolean isEnabled) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        return new FilterElement(id, title, isEnabled);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FilterElement)) {
            return false;
        }
        FilterElement filterElement = (FilterElement) other;
        return Intrinsics.areEqual(getId(), filterElement.getId()) && Intrinsics.areEqual(getTitle(), filterElement.getTitle()) && getIsEnabled() == filterElement.getIsEnabled();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    public int hashCode() {
        int iHashCode = ((getId().hashCode() * 31) + getTitle().hashCode()) * 31;
        boolean isEnabled = getIsEnabled();
        ?? r1 = isEnabled;
        if (isEnabled) {
            r1 = 1;
        }
        return iHashCode + r1;
    }

    public String toString() {
        return "FilterElement(id=" + getId() + ", title=" + getTitle() + ", isEnabled=" + getIsEnabled() + ')';
    }

    public /* synthetic */ FilterElement(String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? true : z);
    }

    @Override // com.amazon.music.find.model.filter.AbstractFilterElement
    public String getId() {
        return this.id;
    }

    @Override // com.amazon.music.find.model.filter.AbstractFilterElement
    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.music.find.model.filter.AbstractFilterElement
    /* JADX INFO: renamed from: isEnabled, reason: from getter */
    public boolean getIsEnabled() {
        return this.isEnabled;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FilterElement(String id, String title, boolean z) {
        super(id, title, z);
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        this.id = id;
        this.title = title;
        this.isEnabled = z;
    }

    /* JADX INFO: compiled from: FilterElement.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/find/model/filter/FilterElement$Companion;", "", "()V", "fromContentEncoding", "", ContextMappingConstants.CONTENT_ENCODING, "Lcom/amazon/music/contentEncoding/ContentEncoding;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: FilterElement.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ContentEncoding.values().length];
                iArr[ContentEncoding.UHD.ordinal()] = 1;
                iArr[ContentEncoding.SONY_360.ordinal()] = 2;
                iArr[ContentEncoding.DOLBY_ATMOS.ordinal()] = 3;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String fromContentEncoding(ContentEncoding contentEncoding) {
            Intrinsics.checkNotNullParameter(contentEncoding, "contentEncoding");
            int i = WhenMappings.$EnumSwitchMapping$0[contentEncoding.ordinal()];
            if (i == 1) {
                return FilterMetadata.UHD_FILTER_ID;
            }
            if (i == 2) {
                return FilterMetadata.SONY_360_FILTER_ID;
            }
            if (i != 3) {
                return null;
            }
            return FilterMetadata.DOLBY_ATMOS_FILTER_ID;
        }
    }

    @Override // com.amazon.music.find.model.filter.AbstractFilterElement
    public FilterElement deepCopy() {
        return new FilterElement(getId(), getTitle(), getIsEnabled());
    }

    public final ContentEncoding toContentEncoding() {
        String id = getId();
        int iHashCode = id.hashCode();
        if (iHashCode != -2072154266) {
            if (iHashCode != -143223838) {
                if (iHashCode == 455969952 && id.equals(FilterMetadata.DOLBY_ATMOS_FILTER_ID)) {
                    return ContentEncoding.DOLBY_ATMOS;
                }
            } else if (id.equals(FilterMetadata.SONY_360_FILTER_ID)) {
                return ContentEncoding.SONY_360;
            }
        } else if (id.equals(FilterMetadata.UHD_FILTER_ID)) {
            return ContentEncoding.UHD;
        }
        return ContentEncoding.UNKNOWN;
    }
}
