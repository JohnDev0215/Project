package k3;

import android.os.Bundle;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class z1 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final z1 f18172b = new z1(new HashSet());
    public static final String c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d5.M f18173a;

    static {
        int i7 = AbstractC1343A.f15195a;
        c = Integer.toString(0, 36);
    }

    public z1(HashSet hashSet) {
        this.f18173a = d5.M.v(hashSet);
    }

    public static z1 a(Bundle bundle) {
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(c);
        if (parcelableArrayList == null) {
            AbstractC1346c.z("SessionCommands", "Missing commands. Creating an empty SessionCommands");
            return f18172b;
        }
        HashSet hashSet = new HashSet();
        for (int i7 = 0; i7 < parcelableArrayList.size(); i7++) {
            hashSet.add(y1.a((Bundle) parcelableArrayList.get(i7)));
        }
        return new z1(hashSet);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof z1) {
            return this.f18173a.equals(((z1) obj).f18173a);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.f18173a);
    }
}
