package defpackage;

import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: MessageDispatcherProvider.java */
/* JADX INFO: loaded from: classes3.dex */
public final class xg1 {
    public static final aj3<a> a = new aj3<>(a.class);

    /* JADX INFO: compiled from: MessageDispatcherProvider.java */
    public interface a extends vg1, zi3 {
    }

    public static vg1 a(WindowAndroid windowAndroid) {
        if (windowAndroid == null) {
            return null;
        }
        return (vg1) a.e(windowAndroid.r);
    }
}
