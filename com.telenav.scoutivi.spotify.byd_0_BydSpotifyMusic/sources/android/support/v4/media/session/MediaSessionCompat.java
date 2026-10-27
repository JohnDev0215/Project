package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.media.session.MediaSession;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.support.v4.media.MediaDescriptionCompat;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MediaSessionCompat {

    @SuppressLint({"BanParcelableUsage"})
    static final class ResultReceiverWrapper implements Parcelable {
        public static final Parcelable.Creator<ResultReceiverWrapper> CREATOR = new a();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        ResultReceiver f254e;

        class a implements Parcelable.Creator<ResultReceiverWrapper> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public ResultReceiverWrapper createFromParcel(Parcel parcel) {
                return new ResultReceiverWrapper(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public ResultReceiverWrapper[] newArray(int i7) {
                return new ResultReceiverWrapper[i7];
            }
        }

        ResultReceiverWrapper(Parcel parcel) {
            this.f254e = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            this.f254e.writeToParcel(parcel, i7);
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static final class Token implements Parcelable {
        public static final Parcelable.Creator<Token> CREATOR = new a();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Object f255e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final Object f256g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private b f257h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private g1.b f258i;

        class a implements Parcelable.Creator<Token> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Token createFromParcel(Parcel parcel) {
                return new Token(parcel.readParcelable(null));
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public Token[] newArray(int i7) {
                return new Token[i7];
            }
        }

        Token(Object obj) {
            this(obj, null, null);
        }

        public static Token b(Object obj) {
            return c(obj, null);
        }

        public static Token c(Object obj, b bVar) {
            if (obj == null) {
                return null;
            }
            if (obj instanceof MediaSession.Token) {
                return new Token(obj, bVar);
            }
            throw new IllegalArgumentException("token is not a valid MediaSession.Token object");
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
            Object obj2 = this.f256g;
            if (obj2 == null) {
                return token.f256g == null;
            }
            Object obj3 = token.f256g;
            if (obj3 == null) {
                return false;
            }
            return obj2.equals(obj3);
        }

        public b h() {
            b bVar;
            synchronized (this.f255e) {
                bVar = this.f257h;
            }
            return bVar;
        }

        public int hashCode() {
            Object obj = this.f256g;
            if (obj == null) {
                return 0;
            }
            return obj.hashCode();
        }

        public Object j() {
            return this.f256g;
        }

        public void k(b bVar) {
            synchronized (this.f255e) {
                this.f257h = bVar;
            }
        }

        public void m(g1.b bVar) {
            synchronized (this.f255e) {
                this.f258i = bVar;
            }
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            parcel.writeParcelable((Parcelable) this.f256g, i7);
        }

        Token(Object obj, b bVar) {
            this(obj, bVar, null);
        }

        Token(Object obj, b bVar, g1.b bVar2) {
            this.f255e = new Object();
            this.f256g = obj;
            this.f257h = bVar;
            this.f258i = bVar2;
        }
    }

    public static void a(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MediaSessionCompat.class.getClassLoader());
        }
    }

    public static Bundle b(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        a(bundle);
        try {
            bundle.isEmpty();
            return bundle;
        } catch (BadParcelableException unused) {
            Log.e("MediaSessionCompat", "Could not unparcel the data.");
            return null;
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    public static final class QueueItem implements Parcelable {
        public static final Parcelable.Creator<QueueItem> CREATOR = new a();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final MediaDescriptionCompat f251e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final long f252g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private MediaSession.QueueItem f253h;

        class a implements Parcelable.Creator<QueueItem> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public QueueItem createFromParcel(Parcel parcel) {
                return new QueueItem(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public QueueItem[] newArray(int i7) {
                return new QueueItem[i7];
            }
        }

        private QueueItem(MediaSession.QueueItem queueItem, MediaDescriptionCompat mediaDescriptionCompat, long j7) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("Description cannot be null");
            }
            if (j7 == -1) {
                throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            }
            this.f251e = mediaDescriptionCompat;
            this.f252g = j7;
            this.f253h = queueItem;
        }

        public static QueueItem b(Object obj) {
            if (obj == null) {
                return null;
            }
            MediaSession.QueueItem queueItem = (MediaSession.QueueItem) obj;
            return new QueueItem(queueItem, MediaDescriptionCompat.b(queueItem.getDescription()), queueItem.getQueueId());
        }

        public static List<QueueItem> c(List<?> list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(b(it.next()));
            }
            return arrayList;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public MediaDescriptionCompat h() {
            return this.f251e;
        }

        public long j() {
            return this.f252g;
        }

        public String toString() {
            return "MediaSession.QueueItem {Description=" + this.f251e + ", Id=" + this.f252g + " }";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            this.f251e.writeToParcel(parcel, i7);
            parcel.writeLong(this.f252g);
        }

        QueueItem(Parcel parcel) {
            this.f251e = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
            this.f252g = parcel.readLong();
        }
    }
}
