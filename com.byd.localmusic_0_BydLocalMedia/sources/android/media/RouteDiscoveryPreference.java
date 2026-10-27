package android.media;

import android.media.RouteDiscoveryPreference;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes2.dex */
public final class RouteDiscoveryPreference implements Parcelable {
    public static final Parcelable.Creator<RouteDiscoveryPreference> CREATOR = new Parcelable.Creator<RouteDiscoveryPreference>() { // from class: android.media.RouteDiscoveryPreference.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RouteDiscoveryPreference createFromParcel(Parcel parcel) {
            return new RouteDiscoveryPreference(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RouteDiscoveryPreference[] newArray(int i) {
            return new RouteDiscoveryPreference[i];
        }
    };
    public static final RouteDiscoveryPreference EMPTY = new Builder(Collections.emptyList(), false).build();
    private final Bundle mExtras;
    private final List<String> mPreferredFeatures;
    private final boolean mShouldPerformActiveScan;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    RouteDiscoveryPreference(Builder builder) {
        this.mPreferredFeatures = builder.mPreferredFeatures;
        this.mShouldPerformActiveScan = builder.mActiveScan;
        this.mExtras = builder.mExtras;
    }

    RouteDiscoveryPreference(Parcel parcel) {
        this.mPreferredFeatures = parcel.createStringArrayList();
        this.mShouldPerformActiveScan = parcel.readBoolean();
        this.mExtras = parcel.readBundle();
    }

    public List<String> getPreferredFeatures() {
        return this.mPreferredFeatures;
    }

    public boolean shouldPerformActiveScan() {
        return this.mShouldPerformActiveScan;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(this.mPreferredFeatures);
        parcel.writeBoolean(this.mShouldPerformActiveScan);
        parcel.writeBundle(this.mExtras);
    }

    public String toString() {
        return "RouteDiscoveryRequest{ preferredFeatures={" + String.join(", ", this.mPreferredFeatures) + "}, activeScan=" + this.mShouldPerformActiveScan + " }";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RouteDiscoveryPreference)) {
            return false;
        }
        RouteDiscoveryPreference routeDiscoveryPreference = (RouteDiscoveryPreference) obj;
        return Objects.equals(this.mPreferredFeatures, routeDiscoveryPreference.mPreferredFeatures) && this.mShouldPerformActiveScan == routeDiscoveryPreference.mShouldPerformActiveScan;
    }

    public int hashCode() {
        return Objects.hash(this.mPreferredFeatures, Boolean.valueOf(this.mShouldPerformActiveScan));
    }

    public static final class Builder {
        boolean mActiveScan;
        Bundle mExtras;
        List<String> mPreferredFeatures;

        public Builder(List<String> list, boolean z) {
            Objects.requireNonNull(list, "preferredFeatures must not be null");
            this.mPreferredFeatures = (List) list.stream().filter(new Predicate() { // from class: android.media.-$$Lambda$RouteDiscoveryPreference$Builder$RAMVaK9RAZ5Ai0qMO3YINliBf1o
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return RouteDiscoveryPreference.Builder.lambda$new$0((String) obj);
                }
            }).collect(Collectors.toList());
            this.mActiveScan = z;
        }

        static /* synthetic */ boolean lambda$new$0(String str) {
            return !TextUtils.isEmpty(str);
        }

        public Builder(RouteDiscoveryPreference routeDiscoveryPreference) {
            Objects.requireNonNull(routeDiscoveryPreference, "preference must not be null");
            this.mPreferredFeatures = routeDiscoveryPreference.getPreferredFeatures();
            this.mActiveScan = routeDiscoveryPreference.shouldPerformActiveScan();
            this.mExtras = routeDiscoveryPreference.getExtras();
        }

        public Builder(Collection<RouteDiscoveryPreference> collection) {
            Objects.requireNonNull(collection, "preferences must not be null");
            HashSet hashSet = new HashSet();
            this.mActiveScan = false;
            for (RouteDiscoveryPreference routeDiscoveryPreference : collection) {
                hashSet.addAll(routeDiscoveryPreference.mPreferredFeatures);
                this.mActiveScan = routeDiscoveryPreference.mShouldPerformActiveScan | this.mActiveScan;
            }
            this.mPreferredFeatures = new ArrayList(hashSet);
        }

        public Builder setPreferredFeatures(List<String> list) {
            Objects.requireNonNull(list, "preferredFeatures must not be null");
            this.mPreferredFeatures = (List) list.stream().filter(new Predicate() { // from class: android.media.-$$Lambda$RouteDiscoveryPreference$Builder$Y04J69jgqbK9_Wo7TZhFFWS0ztk
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return RouteDiscoveryPreference.Builder.lambda$setPreferredFeatures$1((String) obj);
                }
            }).collect(Collectors.toList());
            return this;
        }

        static /* synthetic */ boolean lambda$setPreferredFeatures$1(String str) {
            return !TextUtils.isEmpty(str);
        }

        public Builder setShouldPerformActiveScan(boolean z) {
            this.mActiveScan = z;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.mExtras = bundle;
            return this;
        }

        public RouteDiscoveryPreference build() {
            return new RouteDiscoveryPreference(this);
        }
    }
}
