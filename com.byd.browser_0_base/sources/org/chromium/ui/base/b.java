package org.chromium.ui.base;

import J.N;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.PersistableBundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ParagraphStyle;
import android.text.style.UpdateAppearance;
import android.view.textclassifier.TextLinks;
import defpackage.a8;
import defpackage.fu;
import defpackage.i9;
import defpackage.jk2;
import defpackage.ku2;
import defpackage.n3;
import defpackage.oj2;
import defpackage.se3;
import defpackage.xz;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import org.chromium.base.ThreadUtils;
import org.chromium.ui.R$string;
import org.chromium.ui.base.Clipboard;
import org.chromium.ui.widget.Toast;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ClipboardImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b extends Clipboard implements ClipboardManager.OnPrimaryClipChangedListener {
    public final Context c = xz.a;
    public final ClipboardManager d;
    public Clipboard.a e;
    public Clipboard.a.C0157a f;

    /* JADX INFO: compiled from: ClipboardImpl.java */
    public class a extends i9<ClipData> {
        public final /* synthetic */ Uri h;

        public a(Uri uri) {
            this.h = uri;
        }

        @Override // defpackage.i9
        public final ClipData b() {
            return ClipData.newUri(xz.a.getContentResolver(), "image", this.h);
        }

        @Override // defpackage.i9
        public final void l(ClipData clipData) {
            b bVar = b.this;
            bVar.l(clipData);
            ClipDescription primaryClipDescription = bVar.d.getPrimaryClipDescription();
            long timestamp = (primaryClipDescription == null || !primaryClipDescription.hasMimeType("image/*")) ? 0L : primaryClipDescription.getTimestamp();
            Clipboard.a aVar = bVar.e;
            Uri uri = this.h;
            if (aVar == null) {
                bVar.f = new Clipboard.a.C0157a(uri, timestamp);
            } else {
                xz.a.a.edit().putString("Chrome.Clipboard.SharedUri", uri.toString()).putLong("Chrome.Clipboard.SharedUriTimestamp", timestamp).apply();
            }
        }
    }

    public b(ClipboardManager clipboardManager) {
        this.d = clipboardManager;
        clipboardManager.addPrimaryClipChangedListener(this);
    }

    public static boolean k(Spanned spanned) {
        Class[] clsArr = {CharacterStyle.class, ParagraphStyle.class, UpdateAppearance.class};
        for (int i = 0; i < 3; i++) {
            if (spanned.nextSpanTransition(-1, spanned.length(), clsArr[i]) < spanned.length()) {
                return true;
            }
        }
        return false;
    }

    @Override // org.chromium.ui.base.Clipboard
    public final boolean a() {
        return this.d.hasPrimaryClip();
    }

    @Override // org.chromium.ui.base.Clipboard
    public final void b(GURL gurl) {
        if (l(new ClipData("url", new String[]{"text/x-moz-url", "text/plain"}, new ClipData.Item(gurl.j())))) {
            int i = R$string.link_copied;
            if (Build.VERSION.SDK_INT > 32) {
                return;
            }
            Toast.c(this.c, i, 0).e();
        }
    }

    /* JADX WARN: Finally extract failed */
    @Override // org.chromium.ui.base.Clipboard
    public final Uri c() {
        Clipboard.a.C0157a c0157a;
        Uri uri;
        if (this.e == null) {
            return null;
        }
        SharedPreferences sharedPreferences = xz.a.a;
        ku2 ku2VarT = ku2.t();
        try {
            String string = sharedPreferences.getString("Chrome.Clipboard.SharedUri", null);
            if (TextUtils.isEmpty(string)) {
                ku2VarT.close();
                c0157a = null;
            } else {
                c0157a = new Clipboard.a.C0157a(Uri.parse(string), sharedPreferences.getLong("Chrome.Clipboard.SharedUriTimestamp", 0L));
                ku2VarT.close();
            }
            if (c0157a != null && (uri = c0157a.a) != null) {
                ClipDescription primaryClipDescription = this.d.getPrimaryClipDescription();
                long timestamp = (primaryClipDescription == null || !primaryClipDescription.hasMimeType("image/*")) ? 0L : primaryClipDescription.getTimestamp();
                if (timestamp != 0 && this.e != null) {
                    if (timestamp == c0157a.b) {
                        return uri;
                    }
                    sharedPreferences.edit().remove("Chrome.Clipboard.SharedUri").remove("Chrome.Clipboard.SharedUriTimestamp").apply();
                    return null;
                }
            }
            return null;
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    @Override // org.chromium.ui.base.Clipboard
    public final void clear() {
        try {
            this.d.clearPrimaryClip();
        } catch (Exception unused) {
            l(ClipData.newPlainText(null, null));
        }
    }

    @Override // org.chromium.ui.base.Clipboard
    public final void d(boolean z) {
        ClipDescription primaryClipDescription;
        if (!z || (primaryClipDescription = this.d.getPrimaryClipDescription()) == null) {
            return;
        }
        long timestamp = primaryClipDescription.getTimestamp();
        long j = this.a;
        if (j == 0) {
            return;
        }
        N.MWrNP8sy(j, this, timestamp);
    }

    @Override // org.chromium.ui.base.Clipboard
    public final void e(fu fuVar) {
        this.e = fuVar;
        Clipboard.a.C0157a c0157a = this.f;
        if (c0157a != null) {
            xz.a.a.edit().putString("Chrome.Clipboard.SharedUri", c0157a.a.toString()).putLong("Chrome.Clipboard.SharedUriTimestamp", c0157a.b).apply();
            this.f = null;
        }
    }

    @Override // org.chromium.ui.base.Clipboard
    public final void f(Uri uri) {
        if (uri != null) {
            new a(uri).c(i9.e);
            return;
        }
        int i = R$string.copy_to_clipboard_failure_message;
        Context context = this.c;
        Toast.b(0, context, context.getString(i)).e();
    }

    @Override // org.chromium.ui.base.Clipboard
    public final void g(String str) {
        ClipData clipDataNewPlainText = ClipData.newPlainText("password", str);
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putBoolean("android.content.extra.IS_SENSITIVE", true);
        clipDataNewPlainText.getDescription().setExtras(persistableBundle);
        l(clipDataNewPlainText);
    }

    @Override // org.chromium.ui.base.Clipboard
    public final String getCoercedText() {
        try {
            return this.d.getPrimaryClip().getItemAt(0).coerceToText(this.c).toString();
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x003e -> B:17:0x003f). Please report as a decompilation issue!!! */
    @Override // org.chromium.ui.base.Clipboard
    public final String getHTMLText() {
        String html;
        ClipData primaryClip;
        ClipDescription description;
        try {
            primaryClip = this.d.getPrimaryClip();
            description = primaryClip.getDescription();
        } catch (Exception unused) {
        }
        if (description.hasMimeType("text/html")) {
            html = primaryClip.getItemAt(0).getHtmlText();
        } else if (description.hasMimeType("text/plain")) {
            CharSequence text = primaryClip.getItemAt(0).getText();
            if (text instanceof Spanned) {
                Spanned spanned = (Spanned) text;
                if (k(spanned)) {
                    html = Html.toHtml(spanned, 0);
                }
            }
            html = null;
        } else {
            html = null;
        }
        return html;
    }

    @Override // org.chromium.ui.base.Clipboard
    public final String getImageUriString() {
        Uri uriJ = j();
        if (uriJ == null) {
            return null;
        }
        return uriJ.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r2v1, types: [android.content.ContentResolver] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.Closeable] */
    @Override // org.chromium.ui.base.Clipboard
    public final byte[] getPng() throws Throwable {
        AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor;
        Object obj = ThreadUtils.a;
        Uri uriJ = j();
        ?? r1 = 0;
        if (uriJ == null) {
            return null;
        }
        ?? contentResolver = xz.a.getContentResolver();
        if (!"image/png".equalsIgnoreCase(contentResolver.getType(uriJ))) {
            if (!hasImage()) {
                return null;
            }
            try {
                Bitmap bitmapDecodeBitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource((ContentResolver) contentResolver, uriJ));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmapDecodeBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                if (byteArrayOutputStream.size() > 100000000) {
                    return null;
                }
                return byteArrayOutputStream.toByteArray();
            } catch (IOException | OutOfMemoryError unused) {
                return null;
            }
        }
        try {
            assetFileDescriptorOpenAssetFileDescriptor = contentResolver.openAssetFileDescriptor(uriJ, "r");
        } catch (IOException unused2) {
            contentResolver = 0;
        } catch (Throwable th) {
            th = th;
        }
        try {
            if (assetFileDescriptorOpenAssetFileDescriptor != null) {
                try {
                    if (assetFileDescriptorOpenAssetFileDescriptor.getLength() <= 100000000 && assetFileDescriptorOpenAssetFileDescriptor.getLength() != -1) {
                        byte[] bArr = new byte[(int) assetFileDescriptorOpenAssetFileDescriptor.getLength()];
                        FileInputStream fileInputStream = new FileInputStream(assetFileDescriptorOpenAssetFileDescriptor.getFileDescriptor());
                        try {
                            fileInputStream.read(bArr);
                            assetFileDescriptorOpenAssetFileDescriptor.close();
                            a8.s(fileInputStream);
                            return bArr;
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                assetFileDescriptorOpenAssetFileDescriptor.close();
                            } catch (Throwable unused3) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            if (assetFileDescriptorOpenAssetFileDescriptor != null) {
                assetFileDescriptorOpenAssetFileDescriptor.close();
            }
            return null;
        } catch (IOException unused4) {
            a8.s(contentResolver);
            return null;
        } catch (Throwable th4) {
            th = th4;
            r1 = contentResolver;
            a8.s(r1);
            throw th;
        }
    }

    @Override // org.chromium.ui.base.Clipboard
    public final String getUrl() {
        ClipData primaryClip;
        CharSequence charSequenceSubSequence;
        if (!hasUrl()) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 31) {
            return getCoercedText();
        }
        try {
            primaryClip = this.d.getPrimaryClip();
        } catch (Exception unused) {
        }
        if (!primaryClip.getDescription().hasMimeType("text/x-moz-url")) {
            ClipData.Item itemAt = primaryClip.getItemAt(0);
            TextLinks textLinks = itemAt.getTextLinks();
            if (textLinks != null && !textLinks.getLinks().isEmpty()) {
                CharSequence text = itemAt.getText();
                TextLinks.TextLink next = textLinks.getLinks().iterator().next();
                charSequenceSubSequence = text.subSequence(next.getStart(), next.getEnd());
            }
            return null;
        }
        charSequenceSubSequence = getCoercedText();
        if (charSequenceSubSequence == null) {
            return null;
        }
        return oj2.h(charSequenceSubSequence.toString()).j();
        return null;
    }

    @Override // org.chromium.ui.base.Clipboard
    public final void h(String str, String str2) {
        l(ClipData.newPlainText(str, str2));
    }

    @Override // org.chromium.ui.base.Clipboard
    public final boolean hasCoercedText() {
        ClipDescription primaryClipDescription = this.d.getPrimaryClipDescription();
        if (primaryClipDescription == null) {
            return false;
        }
        return primaryClipDescription.hasMimeType("text/plain") || primaryClipDescription.hasMimeType("text/html") || primaryClipDescription.hasMimeType("text/x-moz-url");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0037  */
    @Override // org.chromium.ui.base.Clipboard
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean hasHTMLOrStyledText() {
        /*
            r4 = this;
            android.content.ClipboardManager r4 = r4.d
            android.content.ClipDescription r0 = r4.getPrimaryClipDescription()
            r1 = 0
            if (r0 != 0) goto La
            return r1
        La:
            java.lang.String r2 = "text/plain"
            boolean r2 = r0.hasMimeType(r2)
            if (r2 == 0) goto L37
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 31
            if (r2 < r3) goto L1d
            boolean r4 = defpackage.e6.l(r0)
            goto L35
        L1d:
            android.content.ClipData r4 = r4.getPrimaryClip()     // Catch: java.lang.Exception -> L34
            android.content.ClipData$Item r4 = r4.getItemAt(r1)     // Catch: java.lang.Exception -> L34
            java.lang.CharSequence r4 = r4.getText()     // Catch: java.lang.Exception -> L34
            boolean r2 = r4 instanceof android.text.Spanned
            if (r2 == 0) goto L34
            android.text.Spanned r4 = (android.text.Spanned) r4
            boolean r4 = k(r4)
            goto L35
        L34:
            r4 = r1
        L35:
            if (r4 != 0) goto L3f
        L37:
            java.lang.String r4 = "text/html"
            boolean r4 = r0.hasMimeType(r4)
            if (r4 == 0) goto L40
        L3f:
            r1 = 1
        L40:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.ui.base.b.hasHTMLOrStyledText():boolean");
    }

    @Override // org.chromium.ui.base.Clipboard
    public final boolean hasImage() {
        ClipDescription primaryClipDescription = this.d.getPrimaryClipDescription();
        return primaryClipDescription != null && primaryClipDescription.hasMimeType("image/*");
    }

    @Override // org.chromium.ui.base.Clipboard
    public final boolean hasUrl() {
        if (Build.VERSION.SDK_INT < 31) {
            return new GURL(getCoercedText()).b;
        }
        ClipDescription primaryClipDescription = this.d.getPrimaryClipDescription();
        if (primaryClipDescription == null) {
            return false;
        }
        if (primaryClipDescription.hasMimeType("text/x-moz-url")) {
            return true;
        }
        if (primaryClipDescription.hasMimeType("text/*")) {
            return (primaryClipDescription.getClassificationStatus() == 3) && primaryClipDescription.getConfidenceScore("url") > 0.99f;
        }
        return false;
    }

    @Override // org.chromium.ui.base.Clipboard
    public final void i(String str) {
        if (l(ClipData.newPlainText("text", str))) {
            int i = R$string.copied;
            if (Build.VERSION.SDK_INT > 32) {
                return;
            }
            Toast.c(this.c, i, 0).e();
        }
    }

    public final Uri j() {
        try {
            ClipData primaryClip = this.d.getPrimaryClip();
            if (primaryClip == null || primaryClip.getItemCount() == 0) {
                return null;
            }
            ClipDescription description = primaryClip.getDescription();
            if (description != null && description.hasMimeType("image/*")) {
                return primaryClip.getItemAt(0).getUri();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public final boolean l(ClipData clipData) {
        try {
            ku2 ku2VarQ = Build.MANUFACTURER.toLowerCase(Locale.US).equals("google") ? null : ku2.q();
            try {
                this.d.setPrimaryClip(clipData);
                if (ku2VarQ == null) {
                    return true;
                }
                ku2VarQ.close();
                return true;
            } catch (Throwable th) {
                if (ku2VarQ != null) {
                    try {
                        ku2VarQ.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        } catch (Exception unused2) {
            int i = R$string.copy_to_clipboard_failure_message;
            Context context = this.c;
            Toast.b(0, context, context.getString(i)).e();
            return false;
        }
    }

    @Override // android.content.ClipboardManager.OnPrimaryClipChangedListener
    public final void onPrimaryClipChanged() {
        n3.X0("MobileClipboardChanged");
        long j = this.a;
        if (j == 0) {
            return;
        }
        N.M3YqItLq(j, this);
    }

    @Override // org.chromium.ui.base.Clipboard
    public final void setHTMLText(String str, String str2) {
        l(ClipData.newHtmlText("html", str2, str));
    }

    @Override // org.chromium.ui.base.Clipboard
    public final void setImage(byte[] bArr, String str) {
        Clipboard.a aVar = this.e;
        if (aVar == null) {
            return;
        }
        se3 se3Var = new se3(23, this);
        ((fu) aVar).getClass();
        jk2.c(bArr, str, se3Var);
    }

    @Override // org.chromium.ui.base.Clipboard
    public final void setText(String str) {
        h("text", str);
    }
}
