package org.chromium.ui.base;

import J.N;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.Html;
import android.text.Spanned;
import android.text.style.CharacterStyle;
import android.text.style.ParagraphStyle;
import android.text.style.UpdateAppearance;
import defpackage.AbstractC0566bn;
import defpackage.C1404qs;
import defpackage.C1602uP;
import defpackage.Is;
import defpackage.Jt;
import java.io.IOException;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Clipboard implements ClipboardManager.OnPrimaryClipChangedListener {
    public static Clipboard d;
    public final Context a;
    public final ClipboardManager b;
    public long c;

    public Clipboard() {
        Context context = Is.a;
        this.a = context;
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
        this.b = clipboardManager;
        clipboardManager.addPrimaryClipChangedListener(this);
    }

    private void clear() {
        c(ClipData.newPlainText(null, null));
    }

    private String getCoercedText() {
        try {
            return this.b.getPrimaryClip().getItemAt(0).coerceToText(this.a).toString();
        } catch (Exception unused) {
            return null;
        }
    }

    private String getHTMLText() {
        try {
            return a(this.b.getPrimaryClip());
        } catch (Exception unused) {
            return null;
        }
    }

    private String getImageUriString() {
        Uri uriB = b();
        if (uriB == null) {
            return null;
        }
        return uriB.toString();
    }

    public static Clipboard getInstance() {
        if (d == null) {
            d = new Clipboard();
        }
        return d;
    }

    private void setHTMLText(String str, String str2) {
        c(ClipData.newHtmlText("html", str2, str));
    }

    private void setNativePtr(long j) {
        this.c = j;
    }

    public String a(ClipData clipData) {
        ClipDescription description = clipData.getDescription();
        if (description.hasMimeType("text/html")) {
            return clipData.getItemAt(0).getHtmlText();
        }
        if (description.hasMimeType("text/plain")) {
            CharSequence text = clipData.getItemAt(0).getText();
            if (!(text instanceof Spanned)) {
                return null;
            }
            Spanned spanned = (Spanned) text;
            boolean z = true;
            Class[] clsArr = {CharacterStyle.class, ParagraphStyle.class, UpdateAppearance.class};
            int i = 0;
            while (true) {
                if (i >= 3) {
                    z = false;
                    break;
                }
                if (spanned.nextSpanTransition(-1, spanned.length(), clsArr[i]) < spanned.length()) {
                    break;
                }
                i++;
            }
            if (z) {
                return Build.VERSION.SDK_INT >= 24 ? C1404qs.d(spanned, 0) : Html.toHtml(spanned);
            }
        }
        return null;
    }

    public Uri b() {
        ClipDescription description;
        try {
            ClipData primaryClip = this.b.getPrimaryClip();
            if (primaryClip != null && primaryClip.getItemCount() != 0 && (description = primaryClip.getDescription()) != null && description.hasMimeType("image/*")) {
                return primaryClip.getItemAt(0).getUri();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public void c(ClipData clipData) {
        try {
            this.b.setPrimaryClip(clipData);
        } catch (Exception unused) {
            C1602uP.a(this.a, this.a.getString(AbstractC0566bn.j1), 0).a.show();
        }
    }

    public Bitmap getImage() {
        ThreadUtils.a();
        try {
            Uri uriB = b();
            if (uriB == null) {
                return null;
            }
            return MediaStore.Images.Media.getBitmap(Is.a.getContentResolver(), uriB);
        } catch (IOException | SecurityException unused) {
            return null;
        }
    }

    @Override // android.content.ClipboardManager.OnPrimaryClipChangedListener
    public void onPrimaryClipChanged() {
        Jt.a("MobileClipboardChanged");
        long j = this.c;
        if (j != 0) {
            N.M3YqItLq(j, this);
        }
    }

    public void setText(String str) {
        c(ClipData.newPlainText("text", str));
    }
}
