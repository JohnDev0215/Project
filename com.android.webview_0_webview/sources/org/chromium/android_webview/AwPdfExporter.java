package org.chromium.android_webview;

import android.os.ParcelFileDescriptor;
import android.print.PrintAttributes;
import android.util.Log;
import android.view.ViewGroup;
import defpackage.C0373Uo;
import defpackage.InterfaceC0355To;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AwPdfExporter {
    public long a;
    public InterfaceC0355To b;
    public PrintAttributes c;
    public ParcelFileDescriptor d;
    public ViewGroup mContainerView;

    public AwPdfExporter(ViewGroup viewGroup) {
        this.mContainerView = viewGroup;
    }

    private void didExportPdf(int i) {
        ((C0373Uo) this.b).a(i);
        this.b = null;
        this.c = null;
        this.d = null;
    }

    private int getBottomMargin() {
        return this.c.getMinMargins().getBottomMils();
    }

    private int getDpi() {
        PrintAttributes printAttributes = this.c;
        int horizontalDpi = printAttributes.getResolution().getHorizontalDpi();
        int verticalDpi = printAttributes.getResolution().getVerticalDpi();
        if (horizontalDpi != verticalDpi) {
            Log.w("AwPdfExporter", "Horizontal and vertical DPIs differ. Using horizontal DPI  hDpi=" + horizontalDpi + " vDPI=" + verticalDpi);
        }
        return horizontalDpi;
    }

    private int getLeftMargin() {
        return this.c.getMinMargins().getLeftMils();
    }

    private int getPageHeight() {
        return this.c.getMediaSize().getHeightMils();
    }

    private int getPageWidth() {
        return this.c.getMediaSize().getWidthMils();
    }

    private int getRightMargin() {
        return this.c.getMinMargins().getRightMils();
    }

    private int getTopMargin() {
        return this.c.getMinMargins().getTopMils();
    }

    private void setNativeAwPdfExporter(long j) {
        InterfaceC0355To interfaceC0355To;
        this.a = j;
        if (j != 0 || (interfaceC0355To = this.b) == null) {
            return;
        }
        try {
            ((C0373Uo) interfaceC0355To).a(0);
            this.b = null;
        } catch (IllegalStateException unused) {
        }
    }
}
