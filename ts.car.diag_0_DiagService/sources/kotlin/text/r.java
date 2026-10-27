package kotlin.text;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Any;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.AbstractC0121l;
import kotlin.collections.C0118i;
import kotlin.collections.C0122m;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
class r extends q {

    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\n\u0018\u00002\u00020\u0001B\u0011\b\u0010\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\t\u001a\u00020\b8\u0000@\u0000X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lkotlin/text/r$a;", "Lkotlin/collections/l;", "Lkotlin/Char;", "b", "()C", "Lkotlin/Boolean;", "hasNext", "()Z", "Lkotlin/Int;", "c", "I", "Lkotlin/CharSequence;", "p0", "<init>", "(Ljava/lang/CharSequence;)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    public static final class a extends AbstractC0121l {
        private int c;
        final /* synthetic */ CharSequence d;

        a(CharSequence charSequence) {
            this.d = charSequence;
        }

        @Override // kotlin.collections.AbstractC0121l
        public char b() {
            CharSequence charSequence = this.d;
            int i = this.c;
            this.c = i + 1;
            return charSequence.charAt(i);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.c < this.d.length();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair<Integer, String> e(@NotNull CharSequence charSequence, Collection<String> collection, int i, boolean z, boolean z2) {
        Object next;
        Object next2;
        if (!z && collection.size() == 1) {
            String str = (String) C0122m.h(collection);
            int iK = !z2 ? k(charSequence, str, i, false, 4, null) : o(charSequence, str, i, false, 4, null);
            if (iK < 0) {
                return null;
            }
            return Any.a(Integer.valueOf(iK), str);
        }
        kotlin.t.a cVar = !z2 ? new kotlin.t.c(kotlin.t.g.a(i, 0), charSequence.length()) : kotlin.t.g.c(kotlin.t.g.b(i, g(charSequence)), 0);
        if (charSequence instanceof String) {
            int iA = cVar.a();
            int iB = cVar.b();
            int iC = cVar.c();
            if (iC < 0 ? iA >= iB : iA <= iB) {
                while (true) {
                    Iterator<T> it = collection.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            next2 = null;
                            break;
                        }
                        next2 = it.next();
                        String str2 = (String) next2;
                        if (q.c(str2, 0, (String) charSequence, iA, str2.length(), z)) {
                            break;
                        }
                    }
                    String str3 = (String) next2;
                    if (str3 == null) {
                        if (iA == iB) {
                            break;
                        }
                        iA += iC;
                    } else {
                        return Any.a(Integer.valueOf(iA), str3);
                    }
                }
            }
        } else {
            int iA2 = cVar.a();
            int iB2 = cVar.b();
            int iC2 = cVar.c();
            if (iC2 < 0 ? iA2 >= iB2 : iA2 <= iB2) {
                while (true) {
                    Iterator<T> it2 = collection.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it2.next();
                        String str4 = (String) next;
                        if (p(str4, 0, charSequence, iA2, str4.length(), z)) {
                            break;
                        }
                    }
                    String str5 = (String) next;
                    if (str5 == null) {
                        if (iA2 == iB2) {
                            break;
                        }
                        iA2 += iC2;
                    } else {
                        return Any.a(Integer.valueOf(iA2), str5);
                    }
                }
            }
        }
        return null;
    }

    @NotNull
    public static final kotlin.t.c f(@NotNull CharSequence charSequence) {
        kotlin.jvm.internal.q.c(charSequence, "$this$indices");
        return new kotlin.t.c(0, charSequence.length() - 1);
    }

    public static final int g(@NotNull CharSequence charSequence) {
        kotlin.jvm.internal.q.c(charSequence, "$this$lastIndex");
        return charSequence.length() - 1;
    }

    public static final int h(@NotNull CharSequence charSequence, @NotNull String str, int i, boolean z) {
        kotlin.jvm.internal.q.c(charSequence, "$this$indexOf");
        kotlin.jvm.internal.q.c(str, "string");
        return (z || !(charSequence instanceof String)) ? j(charSequence, str, i, charSequence.length(), z, false, 16, null) : ((String) charSequence).indexOf(str, i);
    }

    private static final int i(@NotNull CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2) {
        kotlin.t.a cVar = !z2 ? new kotlin.t.c(kotlin.t.g.a(i, 0), kotlin.t.g.b(i2, charSequence.length())) : kotlin.t.g.c(kotlin.t.g.b(i, g(charSequence)), kotlin.t.g.a(i2, 0));
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            int iA = cVar.a();
            int iB = cVar.b();
            int iC = cVar.c();
            if (iC >= 0) {
                if (iA > iB) {
                    return -1;
                }
            } else if (iA < iB) {
                return -1;
            }
            while (!q.c((String) charSequence2, 0, (String) charSequence, iA, charSequence2.length(), z)) {
                if (iA == iB) {
                    return -1;
                }
                iA += iC;
            }
            return iA;
        }
        int iA2 = cVar.a();
        int iB2 = cVar.b();
        int iC2 = cVar.c();
        if (iC2 >= 0) {
            if (iA2 > iB2) {
                return -1;
            }
        } else if (iA2 < iB2) {
            return -1;
        }
        while (!p(charSequence2, 0, charSequence, iA2, charSequence2.length(), z)) {
            if (iA2 == iB2) {
                return -1;
            }
            iA2 += iC2;
        }
        return iA2;
    }

    static /* synthetic */ int j(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2, int i3, Object obj) {
        if ((i3 & 16) != 0) {
            z2 = false;
        }
        return i(charSequence, charSequence2, i, i2, z, z2);
    }

    public static /* synthetic */ int k(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return h(charSequence, str, i, z);
    }

    public static final int l(@NotNull CharSequence charSequence, @NotNull char[] cArr, int i, boolean z) {
        boolean z2;
        kotlin.jvm.internal.q.c(charSequence, "$this$indexOfAny");
        kotlin.jvm.internal.q.c(cArr, "chars");
        if (!z && cArr.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(C0118i.b(cArr), i);
        }
        int iA = kotlin.t.g.a(i, 0);
        int iG = g(charSequence);
        if (iA > iG) {
            return -1;
        }
        while (true) {
            char cCharAt = charSequence.charAt(iA);
            int length = cArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    z2 = false;
                    break;
                }
                if (c.c(cArr[i2], cCharAt, z)) {
                    z2 = true;
                    break;
                }
                i2++;
            }
            if (z2) {
                return iA;
            }
            if (iA == iG) {
                return -1;
            }
            iA++;
        }
    }

    @NotNull
    public static final AbstractC0121l m(@NotNull CharSequence charSequence) {
        kotlin.jvm.internal.q.c(charSequence, "$this$iterator");
        return new a(charSequence);
    }

    public static final int n(@NotNull CharSequence charSequence, @NotNull String str, int i, boolean z) {
        kotlin.jvm.internal.q.c(charSequence, "$this$lastIndexOf");
        kotlin.jvm.internal.q.c(str, "string");
        return (z || !(charSequence instanceof String)) ? i(charSequence, str, i, 0, z, true) : ((String) charSequence).lastIndexOf(str, i);
    }

    public static /* synthetic */ int o(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = g(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return n(charSequence, str, i, z);
    }

    public static final boolean p(@NotNull CharSequence charSequence, int i, @NotNull CharSequence charSequence2, int i2, int i3, boolean z) {
        kotlin.jvm.internal.q.c(charSequence, "$this$regionMatchesImpl");
        kotlin.jvm.internal.q.c(charSequence2, "other");
        if (i2 < 0 || i < 0 || i > charSequence.length() - i3 || i2 > charSequence2.length() - i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (!c.c(charSequence.charAt(i + i4), charSequence2.charAt(i2 + i4), z)) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    public static final String q(@NotNull CharSequence charSequence, @NotNull kotlin.t.c cVar) {
        kotlin.jvm.internal.q.c(charSequence, "$this$substring");
        kotlin.jvm.internal.q.c(cVar, "range");
        return charSequence.subSequence(cVar.g().intValue(), cVar.f().intValue() + 1).toString();
    }
}
