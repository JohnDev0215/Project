package android.media;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

/* JADX INFO: loaded from: classes2.dex */
public final class RoutingSessionInfo implements Parcelable {
    public static final Parcelable.Creator<RoutingSessionInfo> CREATOR = new Parcelable.Creator<RoutingSessionInfo>() { // from class: android.media.RoutingSessionInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RoutingSessionInfo createFromParcel(Parcel parcel) {
            return new RoutingSessionInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RoutingSessionInfo[] newArray(int i) {
            return new RoutingSessionInfo[i];
        }
    };
    private static final String TAG = "RoutingSessionInfo";
    final String mClientPackageName;
    final Bundle mControlHints;
    final List<String> mDeselectableRoutes;
    final String mId;
    final boolean mIsSystemSession;
    final CharSequence mName;
    final String mOwnerPackageName;
    final String mProviderId;
    final List<String> mSelectableRoutes;
    final List<String> mSelectedRoutes;
    final List<String> mTransferableRoutes;
    final int mVolume;
    final int mVolumeHandling;
    final int mVolumeMax;

    private static String ensureString(String str) {
        return str != null ? str : "";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    RoutingSessionInfo(Builder builder) {
        Objects.requireNonNull(builder, "builder must not be null.");
        this.mId = builder.mId;
        this.mName = builder.mName;
        this.mOwnerPackageName = builder.mOwnerPackageName;
        this.mClientPackageName = builder.mClientPackageName;
        this.mProviderId = builder.mProviderId;
        this.mSelectedRoutes = Collections.unmodifiableList(convertToUniqueRouteIds(builder.mSelectedRoutes));
        this.mSelectableRoutes = Collections.unmodifiableList(convertToUniqueRouteIds(builder.mSelectableRoutes));
        this.mDeselectableRoutes = Collections.unmodifiableList(convertToUniqueRouteIds(builder.mDeselectableRoutes));
        this.mTransferableRoutes = Collections.unmodifiableList(convertToUniqueRouteIds(builder.mTransferableRoutes));
        this.mVolumeHandling = builder.mVolumeHandling;
        this.mVolumeMax = builder.mVolumeMax;
        this.mVolume = builder.mVolume;
        this.mControlHints = builder.mControlHints;
        this.mIsSystemSession = builder.mIsSystemSession;
    }

    RoutingSessionInfo(Parcel parcel) {
        Objects.requireNonNull(parcel, "src must not be null.");
        this.mId = ensureString(parcel.readString());
        this.mName = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mOwnerPackageName = parcel.readString();
        this.mClientPackageName = ensureString(parcel.readString());
        this.mProviderId = parcel.readString();
        this.mSelectedRoutes = ensureList(parcel.createStringArrayList());
        this.mSelectableRoutes = ensureList(parcel.createStringArrayList());
        this.mDeselectableRoutes = ensureList(parcel.createStringArrayList());
        this.mTransferableRoutes = ensureList(parcel.createStringArrayList());
        this.mVolumeHandling = parcel.readInt();
        this.mVolumeMax = parcel.readInt();
        this.mVolume = parcel.readInt();
        this.mControlHints = parcel.readBundle();
        this.mIsSystemSession = parcel.readBoolean();
    }

    private static <T> List<T> ensureList(List<? extends T> list) {
        if (list != null) {
            return Collections.unmodifiableList(list);
        }
        return Collections.emptyList();
    }

    public String getId() {
        String str = this.mProviderId;
        if (str != null) {
            return MediaRouter2Utils.toUniqueId(str, this.mId);
        }
        return this.mId;
    }

    public CharSequence getName() {
        return this.mName;
    }

    public String getOriginalId() {
        return this.mId;
    }

    public String getOwnerPackageName() {
        return this.mOwnerPackageName;
    }

    public String getClientPackageName() {
        return this.mClientPackageName;
    }

    public String getProviderId() {
        return this.mProviderId;
    }

    public List<String> getSelectedRoutes() {
        return this.mSelectedRoutes;
    }

    public List<String> getSelectableRoutes() {
        return this.mSelectableRoutes;
    }

    public List<String> getDeselectableRoutes() {
        return this.mDeselectableRoutes;
    }

    public List<String> getTransferableRoutes() {
        return this.mTransferableRoutes;
    }

    public int getVolumeHandling() {
        return this.mVolumeHandling;
    }

    public int getVolumeMax() {
        return this.mVolumeMax;
    }

    public int getVolume() {
        return this.mVolume;
    }

    public Bundle getControlHints() {
        return this.mControlHints;
    }

    public boolean isSystemSession() {
        return this.mIsSystemSession;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mId);
        parcel.writeCharSequence(this.mName);
        parcel.writeString(this.mOwnerPackageName);
        parcel.writeString(this.mClientPackageName);
        parcel.writeString(this.mProviderId);
        parcel.writeStringList(this.mSelectedRoutes);
        parcel.writeStringList(this.mSelectableRoutes);
        parcel.writeStringList(this.mDeselectableRoutes);
        parcel.writeStringList(this.mTransferableRoutes);
        parcel.writeInt(this.mVolumeHandling);
        parcel.writeInt(this.mVolumeMax);
        parcel.writeInt(this.mVolume);
        parcel.writeBundle(this.mControlHints);
        parcel.writeBoolean(this.mIsSystemSession);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RoutingSessionInfo)) {
            return false;
        }
        RoutingSessionInfo routingSessionInfo = (RoutingSessionInfo) obj;
        return Objects.equals(this.mId, routingSessionInfo.mId) && Objects.equals(this.mName, routingSessionInfo.mName) && Objects.equals(this.mOwnerPackageName, routingSessionInfo.mOwnerPackageName) && Objects.equals(this.mClientPackageName, routingSessionInfo.mClientPackageName) && Objects.equals(this.mProviderId, routingSessionInfo.mProviderId) && Objects.equals(this.mSelectedRoutes, routingSessionInfo.mSelectedRoutes) && Objects.equals(this.mSelectableRoutes, routingSessionInfo.mSelectableRoutes) && Objects.equals(this.mDeselectableRoutes, routingSessionInfo.mDeselectableRoutes) && Objects.equals(this.mTransferableRoutes, routingSessionInfo.mTransferableRoutes) && this.mVolumeHandling == routingSessionInfo.mVolumeHandling && this.mVolumeMax == routingSessionInfo.mVolumeMax && this.mVolume == routingSessionInfo.mVolume;
    }

    public int hashCode() {
        return Objects.hash(this.mId, this.mName, this.mOwnerPackageName, this.mClientPackageName, this.mProviderId, this.mSelectedRoutes, this.mSelectableRoutes, this.mDeselectableRoutes, this.mTransferableRoutes, Integer.valueOf(this.mVolumeMax), Integer.valueOf(this.mVolumeHandling), Integer.valueOf(this.mVolume));
    }

    public String toString() {
        return "RoutingSessionInfo{ sessionId=" + getId() + ", name=" + getName() + ", selectedRoutes={" + String.join(SmsManager.REGEX_PREFIX_DELIMITER, getSelectedRoutes()) + "}, selectableRoutes={" + String.join(SmsManager.REGEX_PREFIX_DELIMITER, getSelectableRoutes()) + "}, deselectableRoutes={" + String.join(SmsManager.REGEX_PREFIX_DELIMITER, getDeselectableRoutes()) + "}, transferableRoutes={" + String.join(SmsManager.REGEX_PREFIX_DELIMITER, getTransferableRoutes()) + "}, volumeHandling=" + getVolumeHandling() + ", volumeMax=" + getVolumeMax() + ", volume=" + getVolume() + " }";
    }

    private List<String> convertToUniqueRouteIds(List<String> list) {
        if (list == null) {
            Log.w(TAG, "routeIds is null. Returning an empty list");
            return Collections.emptyList();
        }
        if (this.mProviderId == null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(MediaRouter2Utils.toUniqueId(this.mProviderId, it.next()));
        }
        return arrayList;
    }

    public static final class Builder {
        String mClientPackageName;
        Bundle mControlHints;
        final List<String> mDeselectableRoutes;
        final String mId;
        boolean mIsSystemSession;
        CharSequence mName;
        String mOwnerPackageName;
        String mProviderId;
        final List<String> mSelectableRoutes;
        final List<String> mSelectedRoutes;
        final List<String> mTransferableRoutes;
        int mVolume;
        int mVolumeHandling;
        int mVolumeMax;

        public Builder(String str, String str2) {
            this.mVolumeHandling = 0;
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("id must not be empty");
            }
            this.mId = str;
            this.mClientPackageName = (String) Objects.requireNonNull(str2, "clientPackageName must not be null");
            this.mSelectedRoutes = new ArrayList();
            this.mSelectableRoutes = new ArrayList();
            this.mDeselectableRoutes = new ArrayList();
            this.mTransferableRoutes = new ArrayList();
        }

        public Builder(RoutingSessionInfo routingSessionInfo) {
            this.mVolumeHandling = 0;
            Objects.requireNonNull(routingSessionInfo, "sessionInfo must not be null");
            this.mId = routingSessionInfo.mId;
            this.mName = routingSessionInfo.mName;
            this.mClientPackageName = routingSessionInfo.mClientPackageName;
            this.mProviderId = routingSessionInfo.mProviderId;
            ArrayList arrayList = new ArrayList(routingSessionInfo.mSelectedRoutes);
            this.mSelectedRoutes = arrayList;
            ArrayList arrayList2 = new ArrayList(routingSessionInfo.mSelectableRoutes);
            this.mSelectableRoutes = arrayList2;
            ArrayList arrayList3 = new ArrayList(routingSessionInfo.mDeselectableRoutes);
            this.mDeselectableRoutes = arrayList3;
            ArrayList arrayList4 = new ArrayList(routingSessionInfo.mTransferableRoutes);
            this.mTransferableRoutes = arrayList4;
            if (this.mProviderId != null) {
                arrayList.replaceAll(new UnaryOperator() { // from class: android.media.-$$Lambda$Z63XW37XYXOyFxJBAnuRPxyDzFE
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return MediaRouter2Utils.getOriginalId((String) obj);
                    }
                });
                arrayList2.replaceAll(new UnaryOperator() { // from class: android.media.-$$Lambda$Z63XW37XYXOyFxJBAnuRPxyDzFE
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return MediaRouter2Utils.getOriginalId((String) obj);
                    }
                });
                arrayList3.replaceAll(new UnaryOperator() { // from class: android.media.-$$Lambda$Z63XW37XYXOyFxJBAnuRPxyDzFE
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return MediaRouter2Utils.getOriginalId((String) obj);
                    }
                });
                arrayList4.replaceAll(new UnaryOperator() { // from class: android.media.-$$Lambda$Z63XW37XYXOyFxJBAnuRPxyDzFE
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return MediaRouter2Utils.getOriginalId((String) obj);
                    }
                });
            }
            this.mVolumeHandling = routingSessionInfo.mVolumeHandling;
            this.mVolumeMax = routingSessionInfo.mVolumeMax;
            this.mVolume = routingSessionInfo.mVolume;
            this.mControlHints = routingSessionInfo.mControlHints;
            this.mIsSystemSession = routingSessionInfo.mIsSystemSession;
        }

        public Builder setName(CharSequence charSequence) {
            this.mName = charSequence;
            return this;
        }

        public Builder setOwnerPackageName(String str) {
            this.mOwnerPackageName = str;
            return this;
        }

        public Builder setClientPackageName(String str) {
            this.mClientPackageName = str;
            return this;
        }

        public Builder setProviderId(String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("providerId must not be empty");
            }
            this.mProviderId = str;
            return this;
        }

        public Builder clearSelectedRoutes() {
            this.mSelectedRoutes.clear();
            return this;
        }

        public Builder addSelectedRoute(String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("routeId must not be empty");
            }
            this.mSelectedRoutes.add(str);
            return this;
        }

        public Builder removeSelectedRoute(String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("routeId must not be empty");
            }
            this.mSelectedRoutes.remove(str);
            return this;
        }

        public Builder clearSelectableRoutes() {
            this.mSelectableRoutes.clear();
            return this;
        }

        public Builder addSelectableRoute(String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("routeId must not be empty");
            }
            this.mSelectableRoutes.add(str);
            return this;
        }

        public Builder removeSelectableRoute(String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("routeId must not be empty");
            }
            this.mSelectableRoutes.remove(str);
            return this;
        }

        public Builder clearDeselectableRoutes() {
            this.mDeselectableRoutes.clear();
            return this;
        }

        public Builder addDeselectableRoute(String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("routeId must not be empty");
            }
            this.mDeselectableRoutes.add(str);
            return this;
        }

        public Builder removeDeselectableRoute(String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("routeId must not be empty");
            }
            this.mDeselectableRoutes.remove(str);
            return this;
        }

        public Builder clearTransferableRoutes() {
            this.mTransferableRoutes.clear();
            return this;
        }

        public Builder addTransferableRoute(String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("routeId must not be empty");
            }
            this.mTransferableRoutes.add(str);
            return this;
        }

        public Builder removeTransferableRoute(String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("routeId must not be empty");
            }
            this.mTransferableRoutes.remove(str);
            return this;
        }

        public Builder setVolumeHandling(int i) {
            this.mVolumeHandling = i;
            return this;
        }

        public Builder setVolumeMax(int i) {
            this.mVolumeMax = i;
            return this;
        }

        public Builder setVolume(int i) {
            this.mVolume = i;
            return this;
        }

        public Builder setControlHints(Bundle bundle) {
            this.mControlHints = bundle;
            return this;
        }

        public Builder setSystemSession(boolean z) {
            this.mIsSystemSession = z;
            return this;
        }

        public RoutingSessionInfo build() {
            if (this.mSelectedRoutes.isEmpty()) {
                throw new IllegalArgumentException("selectedRoutes must not be empty");
            }
            return new RoutingSessionInfo(this);
        }
    }
}
