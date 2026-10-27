package android.support.v4.media.session;

import a.d.a.a.f.b;
import a.d.a.a.f.d;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.support.v4.media.MediaDescriptionCompat;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MediaSessionCompat {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MediaControllerCompat f140a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList<a> f141b;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final class ResultReceiverWrapper implements Parcelable {
        public static final Parcelable.Creator<ResultReceiverWrapper> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ResultReceiver f145a;

        public static class a implements Parcelable.Creator<ResultReceiverWrapper> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public ResultReceiverWrapper createFromParcel(Parcel parcel) {
                return new ResultReceiverWrapper(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public ResultReceiverWrapper[] newArray(int i) {
                return new ResultReceiverWrapper[i];
            }
        }

        public ResultReceiverWrapper(Parcel parcel) {
            this.f145a = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            this.f145a.writeToParcel(parcel, i);
        }
    }

    public static final class Token implements Parcelable {
        public static final Parcelable.Creator<Token> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f146a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public b f147b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Bundle f148c;

        public static class a implements Parcelable.Creator<Token> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Token createFromParcel(Parcel parcel) {
                return new Token(Build.VERSION.SDK_INT >= 21 ? parcel.readParcelable(null) : parcel.readStrongBinder());
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public Token[] newArray(int i) {
                return new Token[i];
            }
        }

        public Token(Object obj) {
            this(obj, null, null);
        }

        public static Token a(Object obj) {
            return b(obj, null);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static Token b(Object obj, b bVar) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            return new Token(d.a(obj), bVar);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public b c() {
            return this.f147b;
        }

        public Object d() {
            return this.f146a;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void e(b bVar) {
            this.f147b = bVar;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Token)) {
                return false;
            }
            Token token = (Token) obj;
            Object obj2 = this.f146a;
            if (obj2 == null) {
                return token.f146a == null;
            }
            Object obj3 = token.f146a;
            if (obj3 == null) {
                return false;
            }
            return obj2.equals(obj3);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void f(Bundle bundle) {
            this.f148c = bundle;
        }

        public int hashCode() {
            Object obj = this.f146a;
            if (obj == null) {
                return 0;
            }
            return obj.hashCode();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (Build.VERSION.SDK_INT >= 21) {
                parcel.writeParcelable((Parcelable) this.f146a, i);
            } else {
                parcel.writeStrongBinder((IBinder) this.f146a);
            }
        }

        public Token(Object obj, b bVar) {
            this(obj, bVar, null);
        }

        public Token(Object obj, b bVar, Bundle bundle) {
            this.f146a = obj;
            this.f147b = bVar;
            this.f148c = bundle;
        }
    }

    public interface a {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void a(@Nullable Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MediaSessionCompat.class.getClassLoader());
        }
    }

    public void addOnActiveChangeListener(a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.f141b.add(aVar);
    }

    public MediaControllerCompat b() {
        return this.f140a;
    }

    public void removeOnActiveChangeListener(a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.f141b.remove(aVar);
    }

    public static final class QueueItem implements Parcelable {
        public static final Parcelable.Creator<QueueItem> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final MediaDescriptionCompat f142a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final long f143b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Object f144c;

        public static class a implements Parcelable.Creator<QueueItem> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public QueueItem createFromParcel(Parcel parcel) {
                return new QueueItem(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public QueueItem[] newArray(int i) {
                return new QueueItem[i];
            }
        }

        public QueueItem(Object obj, MediaDescriptionCompat mediaDescriptionCompat, long j) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("Description cannot be null.");
            }
            if (j == -1) {
                throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            }
            this.f142a = mediaDescriptionCompat;
            this.f143b = j;
            this.f144c = obj;
        }

        public static QueueItem a(Object obj) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            return new QueueItem(obj, MediaDescriptionCompat.a(d.a.a(obj)), d.a.b(obj));
        }

        public static List<QueueItem> b(List<?> list) {
            if (list == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(a(it.next()));
            }
            return arrayList;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "MediaSession.QueueItem {Description=" + this.f142a + ", Id=" + this.f143b + " }";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            this.f142a.writeToParcel(parcel, i);
            parcel.writeLong(this.f143b);
        }

        public QueueItem(Parcel parcel) {
            this.f142a = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
            this.f143b = parcel.readLong();
        }
    }
}
