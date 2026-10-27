package kotlin.text;

import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.util.ArrayList;
import java.util.List;
import kotlin.TypeCastException;
import okhttp3.HttpUrl;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Indent.kt */
/* JADX INFO: loaded from: classes.dex */
public class StringsKt__IndentKt extends l {
    private static final kotlin.jvm.b.l<String, String> b(final String str) {
        return str.length() == 0 ? new kotlin.jvm.b.l<String, String>() { // from class: kotlin.text.StringsKt__IndentKt$getIndentFunction$1
            @Override // kotlin.jvm.b.l
            public final String invoke(String str2) {
                kotlin.jvm.internal.i.c(str2, "line");
                return str2;
            }
        } : new kotlin.jvm.b.l<String, String>() { // from class: kotlin.text.StringsKt__IndentKt$getIndentFunction$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.b.l
            public final String invoke(String str2) {
                kotlin.jvm.internal.i.c(str2, "line");
                return str + str2;
            }
        };
    }

    public static final String c(String str, String str2, String str3) {
        int i;
        String strInvoke;
        kotlin.jvm.internal.i.c(str, "$this$replaceIndentByMargin");
        kotlin.jvm.internal.i.c(str2, "newIndent");
        kotlin.jvm.internal.i.c(str3, "marginPrefix");
        if (!(!s.l(str3))) {
            throw new IllegalArgumentException("marginPrefix must be non-blank string.".toString());
        }
        List<String> listU = StringsKt__StringsKt.U(str);
        int length = str.length() + (str2.length() * listU.size());
        kotlin.jvm.b.l<String, String> lVarB = b(str2);
        int iH = kotlin.collections.k.h(listU);
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (Object obj : listU) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                kotlin.collections.k.o();
            }
            String str4 = (String) obj;
            String strSubstring = null;
            if ((i2 == 0 || i2 == iH) && s.l(str4)) {
                str4 = null;
            } else {
                int length2 = str4.length();
                int i4 = 0;
                while (true) {
                    if (i4 >= length2) {
                        i = -1;
                        break;
                    }
                    if (!b.c(str4.charAt(i4))) {
                        i = i4;
                        break;
                    }
                    i4++;
                }
                if (i != -1) {
                    int i5 = i;
                    if (s.v(str4, str3, i, false, 4, null)) {
                        int length3 = i5 + str3.length();
                        if (str4 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        strSubstring = str4.substring(length3);
                        kotlin.jvm.internal.i.b(strSubstring, "(this as java.lang.String).substring(startIndex)");
                    }
                }
                if (strSubstring != null && (strInvoke = lVarB.invoke(strSubstring)) != null) {
                    str4 = strInvoke;
                }
            }
            if (str4 != null) {
                arrayList.add(str4);
            }
            i2 = i3;
        }
        String string = ((StringBuilder) kotlin.collections.s.z(arrayList, new StringBuilder(length), BydAdbHelper.COMMAND_LINE_END, null, null, 0, null, null, 124, null)).toString();
        kotlin.jvm.internal.i.b(string, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
        return string;
    }

    public static final String d(String str, String str2) {
        kotlin.jvm.internal.i.c(str, "$this$trimMargin");
        kotlin.jvm.internal.i.c(str2, "marginPrefix");
        return c(str, HttpUrl.FRAGMENT_ENCODE_SET, str2);
    }

    public static /* synthetic */ String e(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "|";
        }
        return d(str, str2);
    }
}
