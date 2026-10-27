package com.spotify.navigation.identifier;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.regex.Pattern;
import p.bg1;
import p.py0;
import p.v03;
import p.v81;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new bg1(20);
    public static final v81 e = kotlin.a.c(new py0() { // from class: com.spotify.navigation.identifier.ViewUri$Companion$PATTERN$2
        @Override // p.py0
        public final Object invoke() {
            return Pattern.compile("spotify[\\w_\\-!.~'()*:%]*");
        }
    });
    public final String c;

    public a(String str) {
        v03.j(str, "viewUri");
        this.c = str;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof a) && v03.b(this.c, ((a) obj).c);
    }

    public final int hashCode() {
        return this.c.hashCode();
    }

    public final String toString() {
        return this.c;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        v03.j(parcel, "out");
        parcel.writeString(this.c);
    }
}
