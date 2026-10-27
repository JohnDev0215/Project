package defpackage;

import J.N;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import java.util.ArrayList;
import java.util.Objects;
import org.chromium.android_webview.AwPdfExporter;

/* JADX INFO: renamed from: Vo, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0391Vo extends PrintDocumentAdapter {
    public AwPdfExporter a;
    public PrintAttributes b;
    public String c;

    public C0391Vo(AwPdfExporter awPdfExporter, String str) {
        this.a = awPdfExporter;
        this.c = str;
    }

    @Override // android.print.PrintDocumentAdapter
    public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
        this.b = printAttributes2;
        layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(this.c).build(), true);
    }

    @Override // android.print.PrintDocumentAdapter
    public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
        int[] iArr;
        if (pageRangeArr == null || pageRangeArr.length == 0) {
            writeResultCallback.onWriteFailed(null);
            return;
        }
        AwPdfExporter awPdfExporter = this.a;
        PrintAttributes printAttributes = this.b;
        if (pageRangeArr.length == 1 && PageRange.ALL_PAGES.equals(pageRangeArr[0])) {
            iArr = new int[0];
        } else {
            ArrayList arrayList = new ArrayList();
            for (PageRange pageRange : pageRangeArr) {
                for (int start = pageRange.getStart(); start <= pageRange.getEnd(); start++) {
                    arrayList.add(Integer.valueOf(start));
                }
            }
            int[] iArr2 = new int[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++) {
                iArr2[i] = ((Integer) arrayList.get(i)).intValue();
            }
            iArr = iArr2;
        }
        C0373Uo c0373Uo = new C0373Uo(writeResultCallback, pageRangeArr);
        Objects.requireNonNull(awPdfExporter);
        if (parcelFileDescriptor == null) {
            throw new IllegalArgumentException("fd cannot be null");
        }
        if (awPdfExporter.b != null) {
            throw new IllegalStateException("printing is already pending");
        }
        if (printAttributes.getMediaSize() == null) {
            throw new IllegalArgumentException("attributes must specify a media size");
        }
        if (printAttributes.getResolution() == null) {
            throw new IllegalArgumentException("attributes must specify print resolution");
        }
        if (printAttributes.getMinMargins() == null) {
            throw new IllegalArgumentException("attributes must specify margins");
        }
        long j = awPdfExporter.a;
        if (j == 0) {
            c0373Uo.a(0);
            return;
        }
        awPdfExporter.b = c0373Uo;
        awPdfExporter.c = printAttributes;
        awPdfExporter.d = parcelFileDescriptor;
        N.Mp2j15Oh(j, awPdfExporter, parcelFileDescriptor.getFd(), iArr, cancellationSignal);
    }
}
