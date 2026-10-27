package org.chromium.chrome.browser.omnibox.suggestions;

import defpackage.it3;
import defpackage.ko0;
import java.util.Objects;

/* JADX INFO: compiled from: OmniboxSuggestionsDropdownEmbedder.java */
/* JADX INFO: loaded from: classes2.dex */
public interface e {

    /* JADX INFO: compiled from: OmniboxSuggestionsDropdownEmbedder.java */
    public static class a {
        public static final a g = new a(-1, -1, -1, -1, -1, -1);
        public final int a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;

        public a(int i, int i2, int i3, int i4, int i5, int i6) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.e = i5;
            this.f = i6;
            this.d = i4;
        }

        public final boolean a(a aVar) {
            if (aVar == null) {
                return false;
            }
            return !(this.a == aVar.a && (this.e == aVar.e || this.f == aVar.f)) && this.b == aVar.b && this.c == aVar.c;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return aVar.a == this.a && aVar.b == this.b && aVar.c == this.c && aVar.e == this.e && aVar.f == this.f;
        }

        public final int hashCode() {
            return Objects.hash(Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.e), Integer.valueOf(this.f));
        }
    }

    a a(ko0 ko0Var);

    boolean b();

    a c();

    it3 d();

    void e(ko0 ko0Var);

    void onAttachedToWindow();

    void onDetachedFromWindow();
}
