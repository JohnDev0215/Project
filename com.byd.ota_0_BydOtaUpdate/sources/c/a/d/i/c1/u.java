package c.a.d.i.c1;

import android.content.Context;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.time.tsimpl.TimeManagerImpl;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.byd.ota.R;
import com.byd.ota.master.VehicleVersionInfo;
import com.byd.ota.view.widget.DatePicker;
import com.byd.ota.view.widget.MyNumberPicker;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: compiled from: UpdateTimePickerDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class u extends n implements MyNumberPicker.i {
    public static final String G3 = u.class.getSimpleName();
    public int H3;
    public int I3;
    public int J3;
    public int K3;
    public int L3;
    public int M3;
    public boolean N3;
    public final DateFormat O3;
    public final DateFormat P3;
    public Calendar Q3;
    public Calendar R3;
    public Calendar S3;
    public TextView T3;
    public DatePicker U3;
    public DatePicker V3;
    public DatePicker W3;
    public DatePicker X3;
    public DatePicker Y3;
    public RelativeLayout Z3;
    public RelativeLayout a4;
    public b b4;

    /* JADX INFO: compiled from: UpdateTimePickerDialog.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            u.this.dismissDialog();
            if (u.this.b4 != null) {
                u.this.b4.b();
            }
        }
    }

    /* JADX INFO: compiled from: UpdateTimePickerDialog.java */
    public interface b {
        void a(long j);

        void b();
    }

    public u(Context context) {
        super(context);
        this.O3 = DateFormat.getDateInstance(0, Locale.getDefault());
        this.P3 = DateFormat.getDateTimeInstance();
        if (this.f2531d) {
            setContentView(R.layout.dialog_update_time_picker_rh);
        } else {
            setContentView(R.layout.dialog_update_time_picker);
        }
        m(getWindow().getDecorView());
        ((Button) findViewById(R.id.action_confirm)).setOnClickListener(new View.OnClickListener() { // from class: c.a.d.i.c1.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f2527c.p(view);
            }
        });
        ((Button) findViewById(R.id.action_cancel)).setOnClickListener(new a());
        this.Z3 = (RelativeLayout) findViewById(R.id.content_rl);
        this.a4 = (RelativeLayout) findViewById(R.id.container_btn_rl);
        if (this.f2531d) {
            this.Z3.setLayoutDirection(0);
        }
        if (this.f2530c) {
            return;
        }
        this.a4.setLayoutDirection(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void p(View view) {
        long timeInMillis = this.Q3.getTimeInMillis();
        if (Calendar.getInstance().getTimeInMillis() >= timeInMillis) {
            j(getContext().getString(R.string.toast_time_expired), 4000L);
            return;
        }
        dismissDialog();
        b bVar = this.b4;
        if (bVar != null) {
            bVar.a(timeInMillis);
        }
    }

    @Override // com.byd.ota.view.widget.MyNumberPicker.i
    public void a(MyNumberPicker myNumberPicker, int i2, int i3) {
        String str = G3;
        c.a.b.d.e.a(str, "oldVal = " + i2 + ", newVal = " + i3);
        int id = myNumberPicker.getId();
        boolean zN = n();
        if (id == R.id.number_picker_month) {
            if (i3 > i2) {
                this.V3.setMinValue(1);
                this.V3.setMaxValue(this.S3.get(5));
            } else {
                this.V3.setMinValue(this.R3.get(5));
                this.V3.setMaxValue(this.R3.getActualMaximum(5));
            }
        } else if (id == R.id.number_picker_day) {
            this.J3 = i3;
        } else if (id == R.id.number_picker_ap) {
            this.K3 = i3;
            if (i3 == 0) {
                this.X3.setMinValue(0);
                this.X3.setMaxValue(11);
                if (this.X3.getValue() > 11) {
                    DatePicker datePicker = this.X3;
                    datePicker.setValue(datePicker.getValue() - 12);
                }
            } else if (i3 == 1) {
                if (zN) {
                    this.X3.setMinValue(0);
                    this.X3.setMaxValue(11);
                } else {
                    this.X3.setMinValue(12);
                    this.X3.setMaxValue(23);
                    if (this.X3.getValue() < 12) {
                        DatePicker datePicker2 = this.X3;
                        datePicker2.setValue(datePicker2.getValue() + 12);
                    }
                }
            }
        } else if (id == R.id.number_picker_hour) {
            this.L3 = i3;
        } else if (id == R.id.number_picker_minute) {
            this.M3 = i3;
        }
        if (this.N3) {
            this.H3 = (this.U3.getValue() > 0 ? this.S3 : this.R3).get(1);
            this.I3 = this.U3.getValue() <= 0 ? 11 : 0;
        } else {
            this.H3 = this.R3.get(1);
            this.I3 = this.U3.getValue() - 1;
        }
        if (this.W3.getValue() != 0 && zN) {
            this.Q3.set(this.H3, this.I3, this.V3.getValue(), this.X3.getValue() + 12, this.Y3.getValue(), 0);
        } else {
            this.Q3.set(this.H3, this.I3, this.V3.getValue(), this.X3.getValue(), this.Y3.getValue(), 0);
        }
        c.a.b.d.e.a(str, "onChange time:" + this.P3.format(this.Q3.getTime()));
        this.T3.setText(this.O3.format(this.Q3.getTime()));
    }

    public final void m(View view) {
        this.Q3 = Calendar.getInstance();
        this.R3 = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        this.S3 = calendar;
        calendar.set(5, this.R3.get(5) + 29);
        c.a.b.d.e.c(G3, "startCalendar = " + this.O3.format(this.R3.getTime()) + ",endCalendar = " + this.O3.format(this.S3.getTime()));
        this.U3 = (DatePicker) view.findViewById(R.id.number_picker_month);
        this.V3 = (DatePicker) view.findViewById(R.id.number_picker_day);
        this.W3 = (DatePicker) view.findViewById(R.id.number_picker_ap);
        this.X3 = (DatePicker) view.findViewById(R.id.number_picker_hour);
        this.Y3 = (DatePicker) view.findViewById(R.id.number_picker_minute);
        TextView textView = (TextView) view.findViewById(R.id.tv_date);
        this.T3 = textView;
        textView.setText(this.O3.format(this.Q3.getTime()));
        if (this.R3.get(2) + 1 == 12) {
            this.N3 = true;
            this.U3.setDisplayedValues(new String[]{TimeManagerImpl.TIME_FORMAT_TWELVE, VehicleVersionInfo.FORCE_UPGRADE});
            this.U3.setMaxValue(1);
            this.U3.setValue(0);
        } else {
            this.N3 = false;
            this.U3.setMinValue(this.R3.get(2) + 1);
            this.U3.setMaxValue(this.S3.get(2) + 1);
            this.U3.setValue(this.R3.get(2) + 1);
        }
        this.U3.setWrapSelectorWheel(false);
        this.U3.setOnValueChangedListener(this);
        this.V3.setMinValue(this.R3.get(5));
        this.V3.setMaxValue(this.R3.getActualMaximum(5));
        this.V3.setValue(this.R3.get(5));
        this.V3.setWrapSelectorWheel(false);
        this.V3.setOnValueChangedListener(this);
        this.W3.setDisplayedValues(new String[]{getContext().getString(R.string.am), getContext().getString(R.string.pm)});
        this.W3.setMaxValue(1);
        this.W3.setOnValueChangedListener(this);
        this.W3.setValue(this.R3.get(9) == 0 ? 0 : 1);
        this.W3.setTextSize(getContext().getResources().getDimension(R.dimen.sp_20));
        boolean zN = n();
        if (this.R3.get(9) == 0) {
            this.X3.setMinValue(0);
            this.X3.setMaxValue(11);
            this.X3.setValue(this.R3.get(10));
        } else if (this.R3.get(9) == 1) {
            if (zN) {
                this.X3.setMinValue(0);
                this.X3.setMaxValue(11);
                this.X3.setValue(this.R3.get(10));
            } else {
                this.X3.setMinValue(12);
                this.X3.setMaxValue(23);
                this.X3.setValue(this.R3.get(11));
            }
        }
        this.X3.setWrapSelectorWheel(true);
        this.X3.setOnValueChangedListener(this);
        this.Y3.setMinValue(0);
        this.Y3.setMaxValue(59);
        this.Y3.setValue(this.R3.get(12));
        this.Y3.setWrapSelectorWheel(true);
        this.Y3.setOnValueChangedListener(this);
    }

    public final boolean n() {
        String string = Settings.System.getString(this.s.getContentResolver(), BYDAutoSysPropertiesDevice.TIME_12_24);
        c.a.b.d.e.a(G3, "strTimeFormat:" + string);
        return TextUtils.equals(TimeManagerImpl.TIME_FORMAT_TWELVE, string);
    }

    public void q(b bVar) {
        this.b4 = bVar;
    }
}
