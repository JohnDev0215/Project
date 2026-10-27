package p;

import com.spotify.player.model.ContextTrack;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class ji1 {
    public static final ji1 c = new ji1("", EmptyList.c);
    public final List a;
    public final String b;

    public ji1(String str, List list) {
        v03.j(list, "queueList");
        this.a = list;
        this.b = str;
    }

    public final ContextTrack a(int i) {
        Object next;
        Iterator it = this.a.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((pz1) next).k == i) {
                break;
            }
        }
        pz1 pz1Var = (pz1) next;
        if (pz1Var != null) {
            return pz1Var.a;
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ji1)) {
            return false;
        }
        ji1 ji1Var = (ji1) obj;
        return v03.b(this.a, ji1Var.a) && v03.b(this.b, ji1Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("MediaSessionQueue(queueList=");
        sb.append(this.a);
        sb.append(", title=");
        return hf2.d(sb, this.b, ')');
    }
}
