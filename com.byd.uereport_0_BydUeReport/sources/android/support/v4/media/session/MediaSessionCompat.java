package android.support.v4.media.session;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.support.v4.media.MediaDescriptionCompat;
import i.b;
import i.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MediaSessionCompat {

    public static final class ResultReceiverWrapper implements Parcelable {
        public static final Parcelable.Creator<ResultReceiverWrapper> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ResultReceiver f190d;

        public static class a implements Parcelable.Creator<ResultReceiverWrapper> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public ResultReceiverWrapper createFromParcel(Parcel parcel) {
                return new ResultReceiverWrapper(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public ResultReceiverWrapper[] newArray(int i8) {
                return new ResultReceiverWrapper[i8];
            }
        }

        public ResultReceiverWrapper(Parcel parcel) {
            this.f190d = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            this.f190d.writeToParcel(parcel, i8);
        }
    }

    public static final class Token implements Parcelable {
        public static final Parcelable.Creator<Token> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Object f191d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public b f192e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Bundle f193f;

        public static class a implements Parcelable.Creator<Token> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Token createFromParcel(Parcel parcel) {
                return new Token(Build.VERSION.SDK_INT >= 21 ? parcel.readParcelable(null) : parcel.readStrongBinder());
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public Token[] newArray(int i8) {
                return new Token[i8];
            }
        }

        public Token(Object obj) {
            this(obj, null, null);
        }

        public static Token f(Object obj) {
            return g(obj, null);
        }

        public static Token g(Object obj, b bVar) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            return new Token(d.a(obj), bVar);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Token)) {
                return false;
            }
            Token token = (Token) obj;
            Object obj2 = this.f191d;
            if (obj2 == null) {
                return token.f191d == null;
            }
            Object obj3 = token.f191d;
            if (obj3 == null) {
                return false;
            }
            return obj2.equals(obj3);
        }

        public b h() {
            return this.f192e;
        }

        public int hashCode() {
            Object obj = this.f191d;
            if (obj == null) {
                return 0;
            }
            return obj.hashCode();
        }

        public Object i() {
            return this.f191d;
        }

        public void j(b bVar) {
            this.f192e = bVar;
        }

        public void k(Bundle bundle) {
            this.f193f = bundle;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            if (Build.VERSION.SDK_INT >= 21) {
                parcel.writeParcelable((Parcelable) this.f191d, i8);
            } else {
                parcel.writeStrongBinder((IBinder) this.f191d);
            }
        }

        public Token(Object obj, b bVar) {
            this(obj, bVar, null);
        }

        public Token(Object obj, b bVar, Bundle bundle) {
            this.f191d = obj;
            this.f192e = bVar;
            this.f193f = bundle;
        }
    }

    public static void a(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MediaSessionCompat.class.getClassLoader());
        }
    }

    public static final class QueueItem implements Parcelable {
        public static final Parcelable.Creator<QueueItem> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final MediaDescriptionCompat f187d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final long f188e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Object f189f;

        public static class a implements Parcelable.Creator<QueueItem> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public QueueItem createFromParcel(Parcel parcel) {
                return new QueueItem(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public QueueItem[] newArray(int i8) {
                return new QueueItem[i8];
            }
        }

        public QueueItem(Object obj, MediaDescriptionCompat mediaDescriptionCompat, long j8) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("Description cannot be null.");
            }
            if (j8 == -1) {
                throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            }
            this.f187d = mediaDescriptionCompat;
            this.f188e = j8;
            this.f189f = obj;
        }

        public static QueueItem f(Object obj) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            return new QueueItem(obj, MediaDescriptionCompat.f(d.a.a(obj)), d.a.b(obj));
        }

        public static List<QueueItem> g(List<?> list) {
            if (list == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(f(it.next()));
            }
            return arrayList;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "MediaSession.QueueItem {Description=" + this.f187d + ", Id=" + this.f188e + " }";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            this.f187d.writeToParcel(parcel, i8);
            parcel.writeLong(this.f188e);
        }

        public QueueItem(Parcel parcel) {
            this.f187d = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
            this.f188e = parcel.readLong();
        }
    }
}
