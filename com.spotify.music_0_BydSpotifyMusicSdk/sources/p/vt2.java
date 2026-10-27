package p;

import android.view.ContentInfo;
import android.view.View;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class vt2 {
    public static String[] a(View view) {
        return view.getReceiveContentMimeTypes();
    }

    public static jz b(View view, jz jzVar) {
        ContentInfo contentInfoQ = jzVar.a.q();
        Objects.requireNonNull(contentInfoQ);
        ContentInfo contentInfoF = ez.f(contentInfoQ);
        ContentInfo contentInfoPerformReceiveContent = view.performReceiveContent(contentInfoF);
        if (contentInfoPerformReceiveContent == null) {
            return null;
        }
        return contentInfoPerformReceiveContent == contentInfoF ? jzVar : new jz(new p01(contentInfoPerformReceiveContent));
    }

    public static void c(View view, String[] strArr, bq1 bq1Var) {
        if (bq1Var == null) {
            view.setOnReceiveContentListener(strArr, null);
        } else {
            view.setOnReceiveContentListener(strArr, new wt2(bq1Var));
        }
    }
}
