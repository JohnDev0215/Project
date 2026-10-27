package defpackage;

import com.google.android.gms.common.Feature;

/* JADX INFO: renamed from: bj, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0562bj {
    public static final Feature a = new Feature("name_ulr_private", 1);
    public static final Feature b;
    public static final Feature[] c;

    static {
        Feature feature = new Feature("name_sleep_segment_request", 1L);
        b = feature;
        c = new Feature[]{a, feature};
    }
}
