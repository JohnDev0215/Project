package p;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class os0 {
    public final List a;

    public os0(ArrayList arrayList) {
        this.a = arrayList;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof os0) && v03.b(this.a, ((os0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return hf2.e(new StringBuilder("OnUserLibraryPodcastPage(items="), this.a, ')');
    }
}
