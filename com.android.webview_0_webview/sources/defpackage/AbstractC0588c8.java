package defpackage;

import android.webkit.WebSettings;

/* JADX INFO: renamed from: c8, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class AbstractC0588c8 {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[WebSettings.LayoutAlgorithm.values().length];
        a = iArr;
        try {
            iArr[WebSettings.LayoutAlgorithm.NORMAL.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[WebSettings.LayoutAlgorithm.SINGLE_COLUMN.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[WebSettings.LayoutAlgorithm.NARROW_COLUMNS.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            a[WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
    }
}
