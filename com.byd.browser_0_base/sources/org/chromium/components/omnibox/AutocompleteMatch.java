package org.chromium.components.omnibox;

import android.text.TextUtils;
import defpackage.q8;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.omnibox.action.OmniboxPedal;
import org.chromium.components.query_tiles.QueryTile;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public class AutocompleteMatch {
    public final int a;
    public final Set<Integer> b;
    public final boolean c;
    public String d;
    public final List<a> e;
    public String f;
    public final List<a> g;
    public SuggestionAnswer h;
    public final String i;
    public GURL j;
    public final GURL k;
    public final String l;
    public final int m;
    public final int n;
    public final boolean o;
    public String p;
    public byte[] q;
    public final int r;
    public final List<QueryTile> s;
    public byte[] t;
    public boolean u;
    public final List<b> v;
    public long w;
    public final List<OmniboxPedal> x;
    public final String y;

    public static class a {
        public final int a;
        public final int b;

        public a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.a == aVar.a && this.b == aVar.b;
        }
    }

    public static class b {
        public final String a;
        public final GURL b;
        public final boolean c;

        public b(String str, GURL gurl, boolean z) {
            this.a = str;
            this.b = gurl;
            this.c = z;
        }
    }

    public AutocompleteMatch(int i, q8 q8Var, boolean z, int i2, int i3, String str, ArrayList arrayList, String str2, ArrayList arrayList2, SuggestionAnswer suggestionAnswer, String str3, GURL gurl, GURL gurl2, String str4, boolean z2, String str5, byte[] bArr, int i4, List list, byte[] bArr2, boolean z3, ArrayList arrayList3, List list2) {
        int i5;
        Set<Integer> setEmptySet;
        if (q8Var == null) {
            setEmptySet = Collections.emptySet();
            i5 = i;
        } else {
            i5 = i;
            setEmptySet = q8Var;
        }
        this.a = i5;
        this.b = setEmptySet;
        this.c = z;
        this.m = i2;
        this.n = i3;
        String str6 = str;
        this.d = str6;
        this.e = arrayList;
        this.f = str2;
        this.g = arrayList2;
        this.h = suggestionAnswer;
        this.i = TextUtils.isEmpty(str3) ? str6 : str3;
        this.j = gurl;
        this.k = gurl2;
        this.l = str4;
        this.o = z2;
        this.p = str5;
        this.q = bArr;
        this.r = i4;
        this.s = list;
        this.t = bArr2;
        this.u = z3;
        this.v = arrayList3;
        this.x = list2 != null ? list2 : Arrays.asList(new OmniboxPedal[0]);
        this.y = gurl.m();
    }

    @CalledByNative
    public static AutocompleteMatch build(long j, int i, int[] iArr, boolean z, int i2, int i3, String str, int[] iArr2, int[] iArr3, String str2, int[] iArr4, int[] iArr5, SuggestionAnswer suggestionAnswer, String str3, GURL gurl, GURL gurl2, String str4, boolean z2, String str5, byte[] bArr, int i4, List<QueryTile> list, byte[] bArr2, boolean z3, String[] strArr, GURL[] gurlArr, int[] iArr6, OmniboxPedal[] omniboxPedalArr) {
        ArrayList arrayList = new ArrayList();
        for (int i5 = 0; i5 < iArr2.length; i5++) {
            arrayList.add(new a(iArr2[i5], iArr3[i5]));
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i6 = 0; i6 < strArr.length; i6++) {
            arrayList2.add(new b(strArr[i6], gurlArr[i6], iArr6[i6] != 0));
        }
        q8 q8Var = new q8(iArr.length);
        for (int i7 : iArr) {
            q8Var.add(Integer.valueOf(i7));
        }
        AutocompleteMatch autocompleteMatch = new AutocompleteMatch(i, q8Var, z, i2, i3, str, arrayList, str2, new ArrayList(), suggestionAnswer, str3, gurl, gurl2, str4, z2, str5, bArr, i4, list, bArr2, z3, arrayList2, Arrays.asList(omniboxPedalArr));
        autocompleteMatch.updateNativeObjectRef(j);
        autocompleteMatch.setDescription(str2, iArr4, iArr5);
        return autocompleteMatch;
    }

    @CalledByNative
    public final void destroy() {
        this.w = 0L;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof AutocompleteMatch)) {
            return false;
        }
        AutocompleteMatch autocompleteMatch = (AutocompleteMatch) obj;
        return this.a == autocompleteMatch.a && this.w == autocompleteMatch.w && Objects.equals(this.b, autocompleteMatch.b) && TextUtils.equals(this.i, autocompleteMatch.i) && TextUtils.equals(this.d, autocompleteMatch.d) && Objects.equals(this.e, autocompleteMatch.e) && TextUtils.equals(this.f, autocompleteMatch.f) && Objects.equals(this.g, autocompleteMatch.g) && this.o == autocompleteMatch.o && this.m == autocompleteMatch.m && Objects.equals(this.h, autocompleteMatch.h) && TextUtils.equals(this.p, autocompleteMatch.p) && Arrays.equals(this.q, autocompleteMatch.q) && this.r == autocompleteMatch.r && Objects.equals(this.s, autocompleteMatch.s);
    }

    public final int hashCode() {
        String str = this.d;
        int iHashCode = str != null ? str.hashCode() : 0;
        String str2 = this.i;
        int iHashCode2 = ((str2 != null ? str2.hashCode() : 0) * 1901) + (iHashCode * 2017) + (this.a * 37) + (this.o ? 1 : 0);
        SuggestionAnswer suggestionAnswer = this.h;
        return suggestionAnswer != null ? iHashCode2 + suggestionAnswer.hashCode() : iHashCode2;
    }

    @CalledByNative
    public final void setAnswer(SuggestionAnswer suggestionAnswer) {
        this.h = suggestionAnswer;
    }

    @CalledByNative
    public final void setDescription(String str, int[] iArr, int[] iArr2) {
        this.f = str;
        List<a> list = this.g;
        list.clear();
        for (int i = 0; i < iArr.length; i++) {
            list.add(new a(iArr[i], iArr2[i]));
        }
    }

    @CalledByNative
    public final void setDestinationUrl(GURL gurl) {
        this.j = gurl;
    }

    public final String toString() {
        return Arrays.asList("mType=" + this.a, "mSubtypes=" + this.b.toString(), "mIsSearchType=" + this.c, "mDisplayText=" + this.d, "mDescription=" + this.f, "mFillIntoEdit=" + this.i, "mUrl=" + this.j, "mImageUrl=" + this.k, "mImageDominatColor=" + this.l, "mRelevance=" + this.m, "mTransition=" + this.n, "mIsDeletable=" + this.o, "mPostContentType=" + this.p, "mPostData=" + Arrays.toString(this.q), "mGroupId=" + this.r, "mDisplayTextClassifications=" + this.e, "mDescriptionClassifications=" + this.g, "mAnswer=" + this.h).toString();
    }

    @CalledByNative
    public final void updateClipboardContent(String str, GURL gurl, String str2, byte[] bArr, byte[] bArr2) {
        this.d = str;
        this.j = gurl;
        this.p = str2;
        this.q = bArr;
        this.t = bArr2;
    }

    @CalledByNative
    public final void updateMatchingTab(boolean z) {
        this.u = z;
    }

    @CalledByNative
    public final void updateNativeObjectRef(long j) {
        this.w = j;
    }
}
