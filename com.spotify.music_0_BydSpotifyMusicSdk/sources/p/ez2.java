package p;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.datepicker.MaterialCalendar;
import com.spotify.music.R;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class ez2 extends q12 {
    public final MaterialCalendar c;

    public ez2(MaterialCalendar materialCalendar) {
        this.c = materialCalendar;
    }

    @Override // p.q12
    public final int a() {
        return this.c.d0.m;
    }

    @Override // p.q12
    public final void c(n22 n22Var, int i) {
        MaterialCalendar materialCalendar = this.c;
        int i2 = materialCalendar.d0.c.k + i;
        TextView textView = ((dz2) n22Var).t;
        String string = textView.getContext().getString(R.string.mtrl_picker_navigate_to_year_description);
        textView.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(i2)));
        textView.setContentDescription(String.format(string, Integer.valueOf(i2)));
        jh1 jh1Var = materialCalendar.g0;
        if (bs2.b().get(1) == i2) {
            Object obj = jh1Var.f;
        } else {
            Object obj2 = jh1Var.d;
        }
        throw null;
    }

    @Override // p.q12
    public final n22 d(RecyclerView recyclerView) {
        return new dz2((TextView) LayoutInflater.from(recyclerView.getContext()).inflate(R.layout.mtrl_calendar_year, (ViewGroup) recyclerView, false));
    }
}
