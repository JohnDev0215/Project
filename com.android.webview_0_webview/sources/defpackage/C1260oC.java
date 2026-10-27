package defpackage;

import android.app.DatePickerDialog;
import android.widget.DatePicker;

/* JADX INFO: renamed from: oC, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1260oC implements DatePickerDialog.OnDateSetListener {
    public final int a;
    public final /* synthetic */ C1589uC b;

    public C1260oC(C1589uC c1589uC, int i) {
        this.b = c1589uC;
        this.a = i;
    }

    @Override // android.app.DatePickerDialog.OnDateSetListener
    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
        this.b.b(this.a, i, i2, i3, 0, 0, 0, 0, 0);
    }
}
