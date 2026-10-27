package defpackage;

import android.R;
import android.content.Context;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class CC extends FrameLayout {
    public final NumberPicker s;
    public final NumberPicker t;
    public BC u;
    public Calendar v;
    public Calendar w;
    public Calendar x;

    public CC(Context context, double d, double d2) {
        super(context, null, R.attr.datePickerStyle);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(AbstractC0425Xm.f1, (ViewGroup) this, true);
        AC ac = new AC(this);
        this.x = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        int iIndexOf = 0;
        if (d >= d2) {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            this.v = calendar;
            calendar.set(0, 0, 1);
            Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            this.w = calendar2;
            calendar2.set(9999, 0, 1);
        } else {
            this.v = a(d);
            this.w = a(d2);
        }
        NumberPicker numberPicker = (NumberPicker) findViewById(AbstractC0371Um.W2);
        this.s = numberPicker;
        numberPicker.setOnLongPressUpdateInterval(200L);
        this.s.setOnValueChangedListener(ac);
        NumberPicker numberPicker2 = (NumberPicker) findViewById(AbstractC0371Um.v5);
        this.t = numberPicker2;
        numberPicker2.setOnLongPressUpdateInterval(100L);
        this.t.setOnValueChangedListener(ac);
        LinearLayout linearLayout = (LinearLayout) findViewById(AbstractC0371Um.U2);
        linearLayout.removeView(this.s);
        linearLayout.removeView(this.t);
        String bestDateTimePattern = DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMMMdd");
        boolean z = false;
        boolean z2 = false;
        while (iIndexOf < bestDateTimePattern.length()) {
            char cCharAt = bestDateTimePattern.charAt(iIndexOf);
            if (cCharAt == '\'') {
                iIndexOf = bestDateTimePattern.indexOf(39, iIndexOf + 1);
                if (iIndexOf == -1) {
                    throw new IllegalArgumentException(Z7.c("Bad quoting in ", bestDateTimePattern));
                }
            } else if ((cCharAt == 'M' || cCharAt == 'L') && !z) {
                linearLayout.addView(this.s);
                z = true;
            } else if (cCharAt == 'y' && !z2) {
                linearLayout.addView(this.t);
                z2 = true;
            }
            iIndexOf++;
        }
        if (!z) {
            linearLayout.addView(this.s);
        }
        if (z2) {
            return;
        }
        linearLayout.addView(this.t);
    }

    public abstract Calendar a(double d);

    public abstract int b(int i);

    public abstract int c();

    public abstract int d(int i);

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    public abstract int e();

    public abstract int f();

    public int g() {
        return this.x.get(1);
    }

    public void h(int i, int i2, BC bc) {
        i(i, i2);
        j();
        this.u = null;
    }

    public abstract void i(int i, int i2);

    public void j() {
        this.s.setDisplayedValues(null);
        this.s.setMinValue(d(g()));
        this.s.setMaxValue(b(g()));
        this.s.setWrapSelectorWheel((this.x.equals(this.v) || this.x.equals(this.w)) ? false : true);
        this.t.setMinValue(e());
        this.t.setMaxValue(c());
        this.t.setWrapSelectorWheel(false);
        this.t.setValue(g());
        this.s.setValue(f());
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.getText().add(DateUtils.formatDateTime(getContext(), this.x.getTimeInMillis(), 20));
    }
}
