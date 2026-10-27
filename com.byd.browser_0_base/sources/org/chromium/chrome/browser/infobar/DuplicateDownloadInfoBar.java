package org.chromium.chrome.browser.infobar;

import android.content.Context;
import android.text.style.ClickableSpan;
import android.view.View;
import defpackage.l10;
import defpackage.oj2;
import defpackage.ve0;
import defpackage.xz;
import java.io.File;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.download.DownloadUtils;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.components.infobars.ConfirmInfoBar;
import org.chromium.components.infobars.InfoBar;
import org.chromium.components.infobars.InfoBarLayout;

/* JADX INFO: loaded from: classes.dex */
public class DuplicateDownloadInfoBar extends ConfirmInfoBar {
    public final String r;
    public final boolean s;
    public final String t;
    public final OTRProfileID u;
    public final boolean v;

    public class a extends ClickableSpan {
        public final /* synthetic */ Context e;

        public a(Context context) {
            this.e = context;
        }

        @Override // android.text.style.ClickableSpan
        public final void onClick(View view) {
            DownloadUtils.g(this.e, DuplicateDownloadInfoBar.this.t);
            oj2.U(3, 5, "Download.DuplicateInfobarEvent.OfflinePage");
        }
    }

    public DuplicateDownloadInfoBar(Context context, String str, boolean z, String str2, OTRProfileID oTRProfileID, boolean z2) {
        super(R$drawable.infobar_downloading, R$color.infobar_icon_drawable_color, null, null, null, context.getString(R$string.duplicate_download_infobar_download_button), context.getString(R$string.cancel));
        this.r = str;
        this.s = z;
        this.t = str2;
        this.u = oTRProfileID;
        this.v = z2;
    }

    @CalledByNative
    public static InfoBar createInfoBar(String str, boolean z, String str2, OTRProfileID oTRProfileID, boolean z2) {
        return new DuplicateDownloadInfoBar(xz.a, str, z, str2, oTRProfileID, z2);
    }

    @Override // org.chromium.components.infobars.ConfirmInfoBar, org.chromium.components.infobars.InfoBar
    public final void p(InfoBarLayout infoBarLayout) {
        super.p(infoBarLayout);
        Context context = infoBarLayout.getContext();
        if (this.s) {
            infoBarLayout.setMessage(DownloadUtils.d(context.getString(this.v ? R$string.duplicate_download_request_infobar_text : R$string.duplicate_download_infobar_text), this.r, false, 0L, new a(context)));
        } else {
            l10 l10Var = new l10(21, this);
            OTRProfileID oTRProfileID = this.u;
            String str = this.r;
            infoBarLayout.setMessage(DownloadUtils.d(context.getString(R$string.duplicate_download_infobar_text), new File(str).getName(), false, 0L, new ve0(str, l10Var, oTRProfileID, 5)));
        }
    }
}
