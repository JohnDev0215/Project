package c.a.i.a;

import android.content.Context;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Locale;

/* JADX INFO: compiled from: UpdateProgressDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends a {
    public ForegroundColorSpan E3;
    public ForegroundColorSpan F3;
    public boolean G3;
    public TextView s;
    public ProgressBar x;
    public int y;

    public j(Context context) {
        super(context);
        this.G3 = this.f2786d.getConfiguration().orientation == 1;
        setContentView(c.a.e.d.f2642i);
        TypedValue typedValue = new TypedValue();
        this.f2785c.getTheme().resolveAttribute(c.a.e.a.f2619a, typedValue, true);
        int color = this.f2785c.getColor(typedValue.resourceId);
        this.y = color;
        this.E3 = new ForegroundColorSpan(color);
        this.F3 = new ForegroundColorSpan(this.y);
        this.s = (TextView) findViewById(c.a.e.c.u);
        this.x = (ProgressBar) findViewById(c.a.e.c.t);
    }

    public void a(int i2) {
        this.s.setText(" (" + String.format(Locale.getDefault(), "%d%%", Integer.valueOf(i2)) + ") ");
        this.x.setProgress(i2);
    }
}
