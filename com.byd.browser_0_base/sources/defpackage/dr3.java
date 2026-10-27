package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.feed.webfeed.WebFeedMainMenuItem;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class dr3 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ WebFeedMainMenuItem f;

    public /* synthetic */ dr3(WebFeedMainMenuItem webFeedMainMenuItem, int i) {
        this.e = i;
        this.f = webFeedMainMenuItem;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004b  */
    @Override // org.chromium.base.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onResult(java.lang.Object r8) throws java.lang.Throwable {
        /*
            r7 = this;
            int r0 = r7.e
            org.chromium.chrome.browser.feed.webfeed.WebFeedMainMenuItem r7 = r7.f
            switch(r0) {
                case 0: goto L8;
                default: goto L7;
            }
        L7:
            goto L10
        L8:
            org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$WebFeedMetadata r8 = (org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$WebFeedMetadata) r8
            int r0 = org.chromium.chrome.browser.feed.webfeed.WebFeedMainMenuItem.s
            r7.a(r8)
            return
        L10:
            org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$WebFeedMetadata r8 = (org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$WebFeedMetadata) r8
            xq3 r0 = r7.q
            android.content.Context r1 = r7.e
            android.content.res.Resources r1 = r1.getResources()
            int r2 = org.chromium.chrome.R$dimen.web_feed_icon_size
            int r1 = r1.getDimensionPixelSize(r2)
            android.content.Context r2 = r7.e
            android.content.res.Resources r2 = r2.getResources()
            int r3 = org.chromium.chrome.R$dimen.web_feed_monogram_text_size
            int r2 = r2.getDimensionPixelSize(r3)
            org.chromium.url.GURL r3 = r7.f
            if (r8 == 0) goto L33
            org.chromium.url.GURL r4 = r8.g
            goto L34
        L33:
            r4 = 0
        L34:
            wb r5 = new wb
            r6 = 28
            r5.<init>(r6, r7)
            r0.a(r1, r2, r3, r4, r5)
            if (r8 == 0) goto L4b
            java.lang.String r0 = r8.b
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L4b
            r7.h = r0
            goto L53
        L4b:
            org.chromium.url.GURL r0 = r7.f
            java.lang.String r0 = J.N.MpICpYBr(r0)
            r7.h = r0
        L53:
            android.widget.TextView r0 = r7.o
            java.lang.String r1 = r7.h
            r0.setText(r1)
            sk r0 = defpackage.bq.a
            java.lang.String r0 = "Cormorant"
            boolean r0 = J.N.M09VlOh_(r0)
            r1 = 0
            if (r0 == 0) goto L83
            android.widget.TextView r0 = r7.o
            android.content.Context r2 = r7.e
            int r3 = org.chromium.chrome.R$string.cormorant_creator_preview
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.String r5 = r7.h
            r4[r1] = r5
            java.lang.String r2 = r2.getString(r3, r4)
            r0.setContentDescription(r2)
            android.widget.TextView r0 = r7.o
            br3 r2 = new br3
            r2.<init>(r7, r1)
            r0.setOnClickListener(r2)
        L83:
            r7.a(r8)
            org.chromium.components.browser_ui.widget.chips.ChipView r8 = r7.k
            if (r8 == 0) goto L97
            org.chromium.chrome.browser.tab.Tab r8 = r7.g
            boolean r8 = r8.m()
            if (r8 == 0) goto L97
            org.chromium.components.browser_ui.widget.chips.ChipView r7 = r7.k
            r7.setEnabled(r1)
        L97:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.dr3.onResult(java.lang.Object):void");
    }
}
