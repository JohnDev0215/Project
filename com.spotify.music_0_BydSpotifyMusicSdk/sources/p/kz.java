package p;

import com.spotify.automotive.pathfinder.type.ContentRatingLabel;

/* JADX INFO: loaded from: classes.dex */
public final class kz {
    public final ContentRatingLabel a;

    public kz(ContentRatingLabel contentRatingLabel) {
        this.a = contentRatingLabel;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof kz) && this.a == ((kz) obj).a;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "ContentRating(label=" + this.a + ')';
    }
}
