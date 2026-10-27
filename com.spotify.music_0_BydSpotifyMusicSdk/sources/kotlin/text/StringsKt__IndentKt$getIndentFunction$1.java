package kotlin.text;

import kotlin.jvm.internal.Lambda;
import p.ry0;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
final class StringsKt__IndentKt$getIndentFunction$1 extends Lambda implements ry0 {
    public static final StringsKt__IndentKt$getIndentFunction$1 c = new StringsKt__IndentKt$getIndentFunction$1();

    public StringsKt__IndentKt$getIndentFunction$1() {
        super(1);
    }

    @Override // p.ry0
    public final Object invoke(Object obj) {
        String str = (String) obj;
        v03.j(str, "line");
        return str;
    }
}
