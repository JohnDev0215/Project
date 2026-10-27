package org.chromium.chrome.browser.supervised_user.website_approval;

import J.N;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.DialogTitle;
import defpackage.C0173a;
import defpackage.ii;
import defpackage.oj2;
import org.chromium.chrome.browser.supervised_user.R;
import org.chromium.ui.ElidedUrlTextView;
import org.chromium.ui.widget.ButtonCompat;
import org.chromium.url.GURL;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes2.dex */
public class WebsiteApprovalSheetContent implements ii {
    public final Context e;
    public final LinearLayout f;

    public static final class a {
        public final String a;
        public final int b;

        public a(String str, int i) {
            this.a = str;
            this.b = i;
        }
    }

    public WebsiteApprovalSheetContent(Context context) {
        this.e = context;
        this.f = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.website_approval_bottom_sheet, (ViewGroup) null);
    }

    public final ButtonCompat b() {
        return (ButtonCompat) this.f.findViewById(R.id.approve_button);
    }

    public final ButtonCompat c() {
        return (ButtonCompat) this.f.findViewById(R.id.deny_button);
    }

    @Override // defpackage.ii
    public final View d() {
        return this.f;
    }

    @Override // defpackage.ii
    public final int e() {
        return 0;
    }

    @Override // defpackage.ii
    public final View g() {
        return null;
    }

    @Override // defpackage.ii
    public final int h() {
        return 0;
    }

    @Override // defpackage.ii
    public final int i() {
        return R.string.parent_website_approval_closed;
    }

    @Override // defpackage.ii
    public final int j() {
        return -2;
    }

    @Override // defpackage.ii
    public final int m() {
        return 0;
    }

    @Override // defpackage.ii
    public final float n() {
        return -2.0f;
    }

    @Override // defpackage.ii
    public final int p() {
        return R.string.parent_website_approval_content_description;
    }

    @Override // defpackage.ii
    public final int r() {
        return R.string.parent_website_approval_full_height;
    }

    @Override // defpackage.ii
    public final float s() {
        return -1.0f;
    }

    @Override // defpackage.ii
    public final boolean u() {
        return false;
    }

    public final void v(GURL gurl) {
        TextView textView = (TextView) this.f.findViewById(R.id.all_pages_of);
        String string = this.e.getString(R.string.parent_website_approval_all_of_domain, oj2.i(gurl));
        textView.setText(string);
        textView.setContentDescription(string);
    }

    public final void w(Bitmap bitmap) {
        ((ImageView) this.f.findViewById(R.id.favicon)).setImageBitmap(bitmap);
    }

    public final void x(GURL gurl) {
        a aVar;
        final ElidedUrlTextView elidedUrlTextView = (ElidedUrlTextView) this.f.findViewById(R.id.full_url);
        String strMeroQv$e = N.MeroQv$e(gurl.j());
        if (strMeroQv$e.length() <= 256) {
            aVar = new a(strMeroQv$e, strMeroQv$e.length());
        } else if (strMeroQv$e.length() <= 512) {
            aVar = new a(strMeroQv$e, 256);
        } else {
            StringBuilder sbJ = C0173a.j(N.MpICpYBr(gurl));
            int iMin = Math.min(gurl.g().length(), 256);
            if (iMin + 3 < gurl.g().length()) {
                sbJ.append(gurl.g().substring(0, iMin));
                sbJ.append("...");
            } else {
                sbJ.append(gurl.g());
            }
            aVar = new a(sbJ.toString(), 256);
        }
        elidedUrlTextView.q(aVar.b, aVar.a);
        ((LinearLayout) this.f.findViewById(R.id.url_container)).setOnClickListener(new View.OnClickListener() { // from class: vs3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                elidedUrlTextView.r();
            }
        });
        elidedUrlTextView.setContentDescription(aVar.a);
    }

    public final void y(String str) {
        DialogTitle dialogTitle = (DialogTitle) this.f.findViewById(R.id.website_approval_sheet_title);
        String string = this.e.getString(R.string.parent_website_approval_title, str);
        dialogTitle.setText(string);
        dialogTitle.setContentDescription(string);
    }

    @Override // defpackage.ii
    public final void destroy() {
    }
}
