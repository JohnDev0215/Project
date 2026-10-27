package defpackage;

import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/* JADX INFO: renamed from: hC, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AlertDialogC0871hC extends AlertDialog implements DialogInterface.OnClickListener, DatePicker.OnDateChangedListener, TimePicker.OnTimeChangedListener {
    public final DatePicker s;
    public final TimePicker t;
    public final InterfaceC0648dC u;
    public final long v;
    public final long w;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlertDialogC0871hC(Context context, InterfaceC0648dC interfaceC0648dC, int i, int i2, int i3, int i4, int i5, boolean z, double d, double d2) {
        super(context, 0);
        Context context2 = context;
        this.v = (long) d;
        this.w = (long) d2;
        this.u = interfaceC0648dC;
        setButton(-1, context.getText(AbstractC0566bn.m1), this);
        setButton(-2, context.getText(R.string.cancel), (DialogInterface.OnClickListener) null);
        setIcon(0);
        setTitle(context.getText(AbstractC0566bn.o1));
        int i6 = Build.VERSION.SDK_INT;
        View viewInflate = ((LayoutInflater) ((i6 == 21 || i6 == 22) ? new C0815gC(context, null) : context2).getSystemService("layout_inflater")).inflate(AbstractC0425Xm.N, (ViewGroup) null);
        setView(viewInflate);
        DatePicker datePicker = (DatePicker) viewInflate.findViewById(AbstractC0371Um.D0);
        this.s = datePicker;
        AbstractC0480aC.a(datePicker, this, i, i2, i3, this.v, this.w);
        TimePicker timePicker = (TimePicker) viewInflate.findViewById(AbstractC0371Um.R4);
        this.t = timePicker;
        timePicker.setIs24HourView(Boolean.valueOf(z));
        this.t.setCurrentHour(Integer.valueOf(i4));
        this.t.setCurrentMinute(Integer.valueOf(i5));
        this.t.setOnTimeChangedListener(this);
        TimePicker timePicker2 = this.t;
        onTimeChanged(timePicker2, a(timePicker2), b(this.t));
    }

    public static int a(TimePicker timePicker) {
        return timePicker.getCurrentHour().intValue();
    }

    public static int b(TimePicker timePicker) {
        return timePicker.getCurrentMinute().intValue();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.u != null) {
            this.s.clearFocus();
            this.t.clearFocus();
            InterfaceC0648dC interfaceC0648dC = this.u;
            C1315pC c1315pC = (C1315pC) interfaceC0648dC;
            c1315pC.b.b(c1315pC.a, this.s.getYear(), this.s.getMonth(), this.s.getDayOfMonth(), a(this.t), b(this.t), 0, 0, 0);
        }
    }

    @Override // android.widget.DatePicker.OnDateChangedListener
    public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
        TimePicker timePicker = this.t;
        if (timePicker != null) {
            onTimeChanged(timePicker, a(timePicker), b(this.t));
        }
    }

    @Override // android.widget.TimePicker.OnTimeChangedListener
    public void onTimeChanged(TimePicker timePicker, int i, int i2) {
        int year = this.s.getYear();
        int month = this.s.getMonth();
        int dayOfMonth = this.s.getDayOfMonth();
        TimePicker timePicker2 = this.t;
        long j = this.v;
        long j2 = this.w;
        GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        gregorianCalendar.clear();
        gregorianCalendar.set(year, month, dayOfMonth, timePicker2.getCurrentHour().intValue(), timePicker2.getCurrentMinute().intValue(), 0);
        if (gregorianCalendar.getTimeInMillis() < j) {
            gregorianCalendar.setTimeInMillis(j);
        } else if (gregorianCalendar.getTimeInMillis() > j2) {
            gregorianCalendar.setTimeInMillis(j2);
        }
        timePicker2.setCurrentHour(Integer.valueOf(gregorianCalendar.get(11)));
        timePicker2.setCurrentMinute(Integer.valueOf(gregorianCalendar.get(12)));
    }
}
