package defpackage;

import android.print.PageRange;
import android.print.PrintDocumentAdapter;

/* JADX INFO: renamed from: Uo, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0373Uo implements InterfaceC0355To {
    public final PrintDocumentAdapter.WriteResultCallback a;
    public final PageRange[] b;

    public C0373Uo(PrintDocumentAdapter.WriteResultCallback writeResultCallback, PageRange[] pageRangeArr) {
        this.a = writeResultCallback;
        this.b = pageRangeArr;
    }

    public void a(int i) {
        PrintDocumentAdapter.WriteResultCallback writeResultCallback = this.a;
        PageRange[] pageRangeArr = this.b;
        if (i <= 0) {
            writeResultCallback.onWriteFailed(null);
            return;
        }
        if (pageRangeArr.length == 1 && PageRange.ALL_PAGES.equals(pageRangeArr[0])) {
            pageRangeArr = new PageRange[]{new PageRange(0, i - 1)};
        }
        writeResultCallback.onWriteFinished(pageRangeArr);
    }
}
