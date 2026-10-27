package com.spotify.localfiles.localfiles;

import android.os.Parcel;
import android.os.Parcelable;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.parcelize.Parcelize;
import p.hf2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public interface LocalFilesEndpoint {

    @Parcelize
    public static final class Configuration implements Parcelable {
        public static final Parcelable.Creator<Configuration> CREATOR = new Creator();
        private final boolean duplicateLinkFilter;
        private final Integer lengthFilter;
        private final SortOrder sortOrder;
        private final String textFilter;

        public static final class Creator implements Parcelable.Creator<Configuration> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Configuration createFromParcel(Parcel parcel) {
                v03.j(parcel, "parcel");
                return new Configuration(SortOrder.CREATOR.createFromParcel(parcel), parcel.readString(), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readInt() != 0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Configuration[] newArray(int i) {
                return new Configuration[i];
            }
        }

        public interface SortOrders {
            public static final Companion Companion = Companion.$$INSTANCE;

            public static final class Companion {
                static final /* synthetic */ Companion $$INSTANCE = new Companion();
                private static final List<SortOrder> ALL_AVAILABLE_SORT_ORDERS;
                private static final SortOrder SORT_ADD_TIME;
                private static final SortOrder SORT_ALBUM_NAME;
                private static final SortOrder SORT_ARTIST_NAME;
                private static final SortOrder SORT_NAME;

                static {
                    SortOrder sortOrder = new SortOrder("name", false, null, 6, null);
                    SORT_NAME = sortOrder;
                    SortOrder sortOrder2 = new SortOrder("album.name", false, sortOrder, 2, null);
                    SORT_ALBUM_NAME = sortOrder2;
                    SortOrder sortOrder3 = new SortOrder("artist.name", false, sortOrder2, 2, null);
                    SORT_ARTIST_NAME = sortOrder3;
                    SortOrder sortOrder4 = new SortOrder("addTime", false, sortOrder2, 2, null);
                    SORT_ADD_TIME = sortOrder4;
                    ALL_AVAILABLE_SORT_ORDERS = v03.C(sortOrder, sortOrder2, sortOrder3, sortOrder4);
                }

                private Companion() {
                }

                public final List<SortOrder> getALL_AVAILABLE_SORT_ORDERS() {
                    return ALL_AVAILABLE_SORT_ORDERS;
                }

                public final SortOrder getSORT_ADD_TIME() {
                    return SORT_ADD_TIME;
                }

                public final SortOrder getSORT_ALBUM_NAME() {
                    return SORT_ALBUM_NAME;
                }

                public final SortOrder getSORT_ARTIST_NAME() {
                    return SORT_ARTIST_NAME;
                }

                public final SortOrder getSORT_NAME() {
                    return SORT_NAME;
                }
            }
        }

        public Configuration() {
            this(null, null, null, false, 15, null);
        }

        public static /* synthetic */ Configuration copy$default(Configuration configuration, SortOrder sortOrder, String str, Integer num, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                sortOrder = configuration.sortOrder;
            }
            if ((i & 2) != 0) {
                str = configuration.textFilter;
            }
            if ((i & 4) != 0) {
                num = configuration.lengthFilter;
            }
            if ((i & 8) != 0) {
                z = configuration.duplicateLinkFilter;
            }
            return configuration.copy(sortOrder, str, num, z);
        }

        public final SortOrder component1() {
            return this.sortOrder;
        }

        public final String component2() {
            return this.textFilter;
        }

        public final Integer component3() {
            return this.lengthFilter;
        }

        public final boolean component4() {
            return this.duplicateLinkFilter;
        }

        public final Configuration copy(SortOrder sortOrder, String str, Integer num, boolean z) {
            v03.j(sortOrder, "sortOrder");
            v03.j(str, "textFilter");
            return new Configuration(sortOrder, str, num, z);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Configuration)) {
                return false;
            }
            Configuration configuration = (Configuration) obj;
            return v03.b(this.sortOrder, configuration.sortOrder) && v03.b(this.textFilter, configuration.textFilter) && v03.b(this.lengthFilter, configuration.lengthFilter) && this.duplicateLinkFilter == configuration.duplicateLinkFilter;
        }

        public final boolean getDuplicateLinkFilter() {
            return this.duplicateLinkFilter;
        }

        public final Integer getLengthFilter() {
            return this.lengthFilter;
        }

        public final SortOrder getSortOrder() {
            return this.sortOrder;
        }

        public final String getTextFilter() {
            return this.textFilter;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v2, types: [int] */
        /* JADX WARN: Type inference failed for: r1v3 */
        /* JADX WARN: Type inference failed for: r1v4 */
        public int hashCode() {
            int iB = hf2.b(this.textFilter, this.sortOrder.hashCode() * 31, 31);
            Integer num = this.lengthFilter;
            int iHashCode = (iB + (num == null ? 0 : num.hashCode())) * 31;
            boolean z = this.duplicateLinkFilter;
            ?? r1 = z;
            if (z) {
                r1 = 1;
            }
            return iHashCode + r1;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("Configuration(sortOrder=");
            sb.append(this.sortOrder);
            sb.append(", textFilter=");
            sb.append(this.textFilter);
            sb.append(", lengthFilter=");
            sb.append(this.lengthFilter);
            sb.append(", duplicateLinkFilter=");
            return u4.n(sb, this.duplicateLinkFilter, ')');
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int iIntValue;
            v03.j(parcel, "out");
            this.sortOrder.writeToParcel(parcel, i);
            parcel.writeString(this.textFilter);
            Integer num = this.lengthFilter;
            if (num == null) {
                iIntValue = 0;
            } else {
                parcel.writeInt(1);
                iIntValue = num.intValue();
            }
            parcel.writeInt(iIntValue);
            parcel.writeInt(this.duplicateLinkFilter ? 1 : 0);
        }

        public Configuration(SortOrder sortOrder, String str, Integer num, boolean z) {
            v03.j(sortOrder, "sortOrder");
            v03.j(str, "textFilter");
            this.sortOrder = sortOrder;
            this.textFilter = str;
            this.lengthFilter = num;
            this.duplicateLinkFilter = z;
        }

        public /* synthetic */ Configuration(SortOrder sortOrder, String str, Integer num, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? SortOrders.Companion.getSORT_NAME() : sortOrder, (i & 2) != 0 ? "" : str, (i & 4) != 0 ? null : num, (i & 8) != 0 ? false : z);
        }
    }

    Single<Object> getSources();

    Single<LocalTracksResponse> getTracks(Configuration configuration);

    Completable notify();

    Completable pinLocalFilesInYourLibraryX();

    Observable<LocalTracksResponse> subscribeTracks(Configuration configuration);
}
