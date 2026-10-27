package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import org.chromium.components.browser_ui.site_settings.AddExceptionPreference;

/* JADX INFO: compiled from: AddExceptionPreference.java */
/* JADX INFO: loaded from: classes2.dex */
public final class s3 implements TextWatcher {
    public final /* synthetic */ Button e;
    public final /* synthetic */ EditText f;
    public final /* synthetic */ AddExceptionPreference g;

    public s3(AddExceptionPreference addExceptionPreference, Button button, EditText editText) {
        this.g = addExceptionPreference;
        this.e = button;
        this.f = editText;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0040  */
    @Override // android.text.TextWatcher
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onTextChanged(java.lang.CharSequence r6, int r7, int r8, int r9) {
        /*
            r5 = this;
            java.lang.String r6 = r6.toString()
            java.lang.String r6 = r6.trim()
            org.chromium.components.browser_ui.site_settings.AddExceptionPreference r7 = r5.g
            yn2 r8 = r7.W
            int r8 = r8.b
            int r0 = r6.length()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L18
            r8 = r2
            goto L41
        L18:
            java.lang.String r0 = ":"
            boolean r0 = r6.contains(r0)
            if (r0 == 0) goto L2a
            r0 = 24
            if (r8 != r0) goto L26
            r8 = r2
            goto L27
        L26:
            r8 = r1
        L27:
            if (r8 != 0) goto L2a
            goto L40
        L2a:
            java.lang.String r8 = " "
            boolean r8 = r6.contains(r8)
            if (r8 != 0) goto L40
            java.lang.String r8 = "."
            boolean r8 = r6.startsWith(r8)
            if (r8 == 0) goto L3b
            goto L40
        L3b:
            boolean r8 = J.N.Mo$6Pn$c(r6)
            goto L41
        L40:
            r8 = r1
        L41:
            if (r8 != 0) goto L62
            if (r9 == 0) goto L62
            android.content.Context r9 = r7.e
            android.content.ContentResolver r9 = r9.getContentResolver()
            java.lang.String r0 = "haptic_feedback_enabled"
            int r9 = android.provider.Settings.System.getInt(r9, r0, r2)
            if (r9 != r2) goto L62
            android.content.Context r9 = r7.e
            java.lang.String r0 = "vibrator"
            java.lang.Object r9 = r9.getSystemService(r0)
            android.os.Vibrator r9 = (android.os.Vibrator) r9
            r3 = 50
            r9.vibrate(r3)
        L62:
            if (r8 == 0) goto L6b
            int r6 = r6.length()
            if (r6 <= 0) goto L6b
            r1 = r2
        L6b:
            android.widget.Button r6 = r5.e
            r6.setEnabled(r1)
            if (r8 == 0) goto L75
            int r6 = r7.Y
            goto L77
        L75:
            int r6 = r7.X
        L77:
            android.widget.EditText r5 = r5.f
            r5.setTextColor(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.s3.onTextChanged(java.lang.CharSequence, int, int, int):void");
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
