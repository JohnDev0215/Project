package defpackage;

import android.app.TimePickerDialog;
import android.widget.TimePicker;

/* JADX INFO: renamed from: tC, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1534tC implements TimePickerDialog.OnTimeSetListener {
    public final int a;
    public final /* synthetic */ C1589uC b;

    public C1534tC(C1589uC c1589uC, int i) {
        this.b = c1589uC;
        this.a = i;
    }

    @Override // android.app.TimePickerDialog.OnTimeSetListener
    public void onTimeSet(TimePicker timePicker, int i, int i2) {
        this.b.b(this.a, 0, 0, 0, i, i2, 0, 0, 0);
    }
}
