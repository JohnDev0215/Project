package b.d.a.a.v;

import android.view.View;
import b.d.a.a.v.f;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public class x implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2126a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ y f2127b;

    public x(y yVar, int i) {
        this.f2127b = yVar;
        this.f2126a = i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int i = this.f2126a;
        int i2 = this.f2127b.f2128c.Z.f2105b;
        Calendar calendarE = w.e();
        calendarE.set(1, i);
        calendarE.set(2, i2);
        o oVar = new o(calendarE);
        a aVar = this.f2127b.f2128c.Y;
        if (oVar.compareTo(aVar.f2065a) < 0) {
            oVar = aVar.f2065a;
        } else if (oVar.compareTo(aVar.f2066b) > 0) {
            oVar = aVar.f2066b;
        }
        this.f2127b.f2128c.S(oVar);
        this.f2127b.f2128c.T(f.e.DAY);
    }
}
