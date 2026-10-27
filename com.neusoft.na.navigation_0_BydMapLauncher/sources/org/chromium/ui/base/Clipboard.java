package org.chromium.ui.base;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import org.bouncycastle.i18n.TextBundle;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.ui.R;
import org.chromium.ui.widget.Toast;

/* JADX INFO: loaded from: classes4.dex */
@JNINamespace("ui")
public class Clipboard {
    private final ClipboardManager mClipboardManager;
    private final Context mContext;

    public Clipboard(Context context) {
        this.mContext = context;
        this.mClipboardManager = (ClipboardManager) context.getSystemService("clipboard");
    }

    private static Clipboard create(Context context) {
        return new Clipboard(context);
    }

    private String getCoercedText() {
        CharSequence charSequenceCoerceToText;
        ClipData primaryClip = this.mClipboardManager.getPrimaryClip();
        if (primaryClip == null || primaryClip.getItemCount() <= 0 || (charSequenceCoerceToText = primaryClip.getItemAt(0).coerceToText(this.mContext)) == null) {
            return null;
        }
        return charSequenceCoerceToText.toString();
    }

    private String getHTMLText() {
        ClipData primaryClip = this.mClipboardManager.getPrimaryClip();
        if (primaryClip == null || primaryClip.getItemCount() <= 0) {
            return null;
        }
        return primaryClip.getItemAt(0).getHtmlText();
    }

    public void setText(String str) {
        setPrimaryClipNoException(ClipData.newPlainText(TextBundle.TEXT_ENTRY, str));
    }

    private void setHTMLText(String str, String str2) {
        setPrimaryClipNoException(ClipData.newHtmlText("html", str2, str));
    }

    private void clear() {
        setPrimaryClipNoException(ClipData.newPlainText(null, null));
    }

    private void setPrimaryClipNoException(ClipData clipData) {
        try {
            this.mClipboardManager.setPrimaryClip(clipData);
        } catch (Exception unused) {
            Toast.makeText(this.mContext, this.mContext.getString(R.string.copy_to_clipboard_failure_message), 0).show();
        }
    }
}
