package O0;

import android.text.Layout;

/* JADX INFO: loaded from: classes.dex */
public abstract class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Layout.Alignment f5044a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Layout.Alignment f5045b;

    static {
        Layout.Alignment[] alignmentArrValues = Layout.Alignment.values();
        Layout.Alignment alignment = Layout.Alignment.ALIGN_NORMAL;
        Layout.Alignment alignment2 = alignment;
        for (Layout.Alignment alignment3 : alignmentArrValues) {
            if (A7.m.a(alignment3.name(), "ALIGN_LEFT")) {
                alignment = alignment3;
            } else if (A7.m.a(alignment3.name(), "ALIGN_RIGHT")) {
                alignment2 = alignment3;
            }
        }
        f5044a = alignment;
        f5045b = alignment2;
    }
}
