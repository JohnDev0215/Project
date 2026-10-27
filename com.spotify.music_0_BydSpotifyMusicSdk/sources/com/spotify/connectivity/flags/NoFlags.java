package com.spotify.connectivity.flags;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class NoFlags implements Flags {
    private final String situation;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<NoFlags> CREATOR = new Parcelable.Creator<NoFlags>() { // from class: com.spotify.connectivity.flags.NoFlags$Companion$CREATOR$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NoFlags createFromParcel(Parcel parcel) {
            v03.j(parcel, "in");
            String string = parcel.readString();
            if (string == null) {
                string = "";
            }
            return new NoFlags(string);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NoFlags[] newArray(int i) {
            return new NoFlags[i];
        }
    };

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public NoFlags(String str) {
        v03.j(str, "situation");
        this.situation = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.spotify.connectivity.flags.Flags
    public <T extends Serializable> T get(Flag<T> flag) {
        throw new UnsupportedOperationException("You cannot read any flags from this object: " + this.situation);
    }

    @Override // com.spotify.connectivity.flags.Flags
    public <T extends Serializable> T getNonOverriddenValue(Flag<T> flag) {
        v03.j(flag, "flag");
        throw new UnsupportedOperationException("You cannot read any flags from this object: " + this.situation);
    }

    @Override // com.spotify.connectivity.flags.Flags
    public <T extends Serializable> boolean isEnabled(Flag<T> flag) {
        v03.j(flag, "flag");
        throw new UnsupportedOperationException("You cannot read any flags from this object: " + this.situation);
    }

    @Override // com.spotify.connectivity.flags.Flags
    public boolean isLoaded() {
        return false;
    }

    @Override // com.spotify.connectivity.flags.Flags
    public boolean isOverridden(Flag<?> flag) {
        v03.j(flag, "flag");
        throw new UnsupportedOperationException("You cannot read any flags from this object: " + this.situation);
    }

    @Override // com.spotify.connectivity.flags.Flags
    public boolean isSame(Flags flags, Flag<?> flag) {
        v03.j(flags, "otherFlags");
        v03.j(flag, "flag");
        throw new UnsupportedOperationException("You cannot read any flags from this object: " + this.situation);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        v03.j(parcel, "dest");
        parcel.writeString(this.situation);
    }
}
