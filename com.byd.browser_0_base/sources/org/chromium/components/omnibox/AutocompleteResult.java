package org.chromium.components.omnibox;

import J.N;
import com.google.protobuf.d0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class AutocompleteResult {
    public static final AutocompleteResult e = new AutocompleteResult(0, Collections.emptyList(), null);
    public final a a;
    public final List<AutocompleteMatch> b;
    public final boolean c;
    public long d;

    public AutocompleteResult(long j, List<AutocompleteMatch> list, a aVar) {
        this.c = j == 0;
        this.d = j;
        this.b = list == null ? new ArrayList<>() : list;
        this.a = aVar == null ? a.Q().build() : aVar;
    }

    @CalledByNative
    public static AutocompleteResult fromNative(long j, AutocompleteMatch[] autocompleteMatchArr, byte[] bArr) {
        a aVarS;
        try {
            aVarS = a.S(bArr);
        } catch (d0 unused) {
            aVarS = null;
        }
        AutocompleteResult autocompleteResult = new AutocompleteResult(j, null, aVarS);
        autocompleteResult.updateMatches(autocompleteMatchArr);
        return autocompleteResult;
    }

    public final boolean a(int i, int i2) {
        if (this.d == 0) {
            return false;
        }
        List<AutocompleteMatch> list = this.b;
        long[] jArr = new long[list.size()];
        for (int i3 = 0; i3 < list.size(); i3++) {
            jArr[i3] = list.get(i3).w;
        }
        return N.M__nk0t1(this.d, jArr, i, i2);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AutocompleteResult)) {
            return false;
        }
        AutocompleteResult autocompleteResult = (AutocompleteResult) obj;
        if (this.b.equals(autocompleteResult.b)) {
            return this.a.equals(autocompleteResult.a);
        }
        return false;
    }

    public final int hashCode() {
        return this.b.hashCode() ^ this.a.hashCode();
    }

    @CalledByNative
    public void notifyNativeDestroyed() {
        this.d = 0L;
    }

    @CalledByNative
    public final void updateMatches(AutocompleteMatch[] autocompleteMatchArr) {
        List<AutocompleteMatch> list = this.b;
        list.clear();
        Collections.addAll(list, autocompleteMatchArr);
    }
}
