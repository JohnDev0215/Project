package F1;

import android.view.ContentInfo;
import android.view.View;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class G {
    public static String[] a(View view) {
        return view.getReceiveContentMimeTypes();
    }

    public static C0247f b(View view, C0247f c0247f) {
        ContentInfo contentInfoA = c0247f.f2330a.A();
        Objects.requireNonNull(contentInfoA);
        ContentInfo contentInfoPerformReceiveContent = view.performReceiveContent(contentInfoA);
        if (contentInfoPerformReceiveContent == null) {
            return null;
        }
        return contentInfoPerformReceiveContent == contentInfoA ? c0247f : new C0247f(new B2.O(contentInfoPerformReceiveContent));
    }
}
