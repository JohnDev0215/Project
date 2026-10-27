package defpackage;

import android.content.Context;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: renamed from: vC, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1644vC extends CC {
    public final String[] y;

    public C1644vC(Context context, double d, double d2) {
        super(context, d, d2);
        this.s.setContentDescription(getResources().getString(AbstractC0566bn.P));
        String[] shortMonths = DateFormatSymbols.getInstance(Locale.getDefault()).getShortMonths();
        this.y = shortMonths;
        if (Character.isDigit(shortMonths[0].charAt(0))) {
            int i = 0;
            while (true) {
                String[] strArr = this.y;
                if (i >= strArr.length) {
                    break;
                }
                int i2 = i + 1;
                strArr[i] = String.format("%d", Integer.valueOf(i2));
                i = i2;
            }
        }
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        h(calendar.get(1), calendar.get(2), null);
    }

    public static Calendar k(double d) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        calendar.set((int) Math.min((d / 12.0d) + 1970.0d, 2.147483647E9d), (int) (d % 12.0d), 1);
        return calendar;
    }

    @Override // defpackage.CC
    public Calendar a(double d) {
        return k(d);
    }

    @Override // defpackage.CC
    public int b(int i) {
        if (i == this.w.get(1)) {
            return this.w.get(2);
        }
        return 11;
    }

    @Override // defpackage.CC
    public int c() {
        return this.w.get(1);
    }

    @Override // defpackage.CC
    public int d(int i) {
        if (i == this.v.get(1)) {
            return this.v.get(2);
        }
        return 0;
    }

    @Override // defpackage.CC
    public int e() {
        return this.v.get(1);
    }

    @Override // defpackage.CC
    public int f() {
        return this.x.get(2);
    }

    @Override // defpackage.CC
    public void i(int i, int i2) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.set(i, i2, 1);
        if (calendar.before(this.v)) {
            this.x = this.v;
        } else if (calendar.after(this.w)) {
            this.x = this.w;
        } else {
            this.x = calendar;
        }
    }

    @Override // defpackage.CC
    public void j() {
        super.j();
        this.s.setDisplayedValues((String[]) Arrays.copyOfRange(this.y, this.s.getMinValue(), this.s.getMaxValue() + 1));
    }
}
