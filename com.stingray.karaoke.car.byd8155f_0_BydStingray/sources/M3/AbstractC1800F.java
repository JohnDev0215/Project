package m3;

import android.text.Html;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: m3.F, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1800F {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Pattern f18968a = Pattern.compile("(&#13;)?&#10;");

    public static String a(CharSequence charSequence) {
        return f18968a.matcher(Html.escapeHtml(charSequence)).replaceAll("<br>");
    }
}
