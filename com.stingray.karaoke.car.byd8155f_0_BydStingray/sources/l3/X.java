package l3;

import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import d5.C1078G;
import d5.d0;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class X implements Parcelable {
    public static final Parcelable.Creator<X> CREATOR = new android.support.v4.media.a(21);
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f18552m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f18553n;
    public final float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final long f18554p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f18555q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final CharSequence f18556r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final long f18557s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final AbstractCollection f18558t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final long f18559u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final Bundle f18560v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public PlaybackState f18561w;

    public X(int i7, long j3, long j10, float f10, long j11, int i10, CharSequence charSequence, long j12, ArrayList arrayList, long j13, Bundle bundle) {
        AbstractCollection arrayList2;
        this.l = i7;
        this.f18552m = j3;
        this.f18553n = j10;
        this.o = f10;
        this.f18554p = j11;
        this.f18555q = i10;
        this.f18556r = charSequence;
        this.f18557s = j12;
        if (arrayList == null) {
            C1078G c1078g = d5.I.f13384m;
            arrayList2 = d0.f13415p;
        } else {
            arrayList2 = new ArrayList(arrayList);
        }
        this.f18558t = arrayList2;
        this.f18559u = j13;
        this.f18560v = bundle;
    }

    public static X a(PlaybackState playbackState) {
        ArrayList arrayList = null;
        if (playbackState == null) {
            return null;
        }
        List<PlaybackState.CustomAction> customActions = playbackState.getCustomActions();
        if (customActions != null) {
            arrayList = new ArrayList(customActions.size());
            for (PlaybackState.CustomAction customAction : customActions) {
                if (customAction != null) {
                    PlaybackState.CustomAction customAction2 = customAction;
                    Bundle extras = customAction2.getExtras();
                    P.o(extras);
                    W w10 = new W(customAction2.getAction(), customAction2.getName(), customAction2.getIcon(), extras);
                    w10.f18551p = customAction2;
                    arrayList.add(w10);
                }
            }
        }
        Bundle extras2 = playbackState.getExtras();
        P.o(extras2);
        X x8 = new X(playbackState.getState(), playbackState.getPosition(), playbackState.getBufferedPosition(), playbackState.getPlaybackSpeed(), playbackState.getActions(), 0, playbackState.getErrorMessage(), playbackState.getLastPositionUpdateTime(), arrayList, playbackState.getActiveQueueItemId(), extras2);
        x8.f18561w = playbackState;
        return x8;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PlaybackState {state=");
        sb.append(this.l);
        sb.append(", position=");
        sb.append(this.f18552m);
        sb.append(", buffered position=");
        sb.append(this.f18553n);
        sb.append(", speed=");
        sb.append(this.o);
        sb.append(", updated=");
        sb.append(this.f18557s);
        sb.append(", actions=");
        sb.append(this.f18554p);
        sb.append(", error code=");
        sb.append(this.f18555q);
        sb.append(", error message=");
        sb.append(this.f18556r);
        sb.append(", custom actions=");
        sb.append(this.f18558t);
        sb.append(", active item id=");
        return android.hardware.bydauto.ac.tsimpl.a.f(this.f18559u, "}", sb);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.l);
        parcel.writeLong(this.f18552m);
        parcel.writeFloat(this.o);
        parcel.writeLong(this.f18557s);
        parcel.writeLong(this.f18553n);
        parcel.writeLong(this.f18554p);
        TextUtils.writeToParcel(this.f18556r, parcel, i7);
        parcel.writeTypedList(this.f18558t);
        parcel.writeLong(this.f18559u);
        parcel.writeBundle(this.f18560v);
        parcel.writeInt(this.f18555q);
    }

    public X(Parcel parcel) {
        this.l = parcel.readInt();
        this.f18552m = parcel.readLong();
        this.o = parcel.readFloat();
        this.f18557s = parcel.readLong();
        this.f18553n = parcel.readLong();
        this.f18554p = parcel.readLong();
        this.f18556r = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        AbstractCollection abstractCollectionCreateTypedArrayList = parcel.createTypedArrayList(W.CREATOR);
        if (abstractCollectionCreateTypedArrayList == null) {
            C1078G c1078g = d5.I.f13384m;
            abstractCollectionCreateTypedArrayList = d0.f13415p;
        }
        this.f18558t = abstractCollectionCreateTypedArrayList;
        this.f18559u = parcel.readLong();
        this.f18560v = parcel.readBundle(P.class.getClassLoader());
        this.f18555q = parcel.readInt();
    }
}
