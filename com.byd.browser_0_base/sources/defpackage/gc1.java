package defpackage;

import android.view.View;
import com.google.android.material.datepicker.a;

/* JADX INFO: compiled from: MaterialCalendar.java */
/* JADX INFO: loaded from: classes.dex */
public final class gc1 implements View.OnClickListener {
    public final /* synthetic */ a e;

    public gc1(a aVar) {
        this.e = aVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        a aVar = this.e;
        int i = aVar.e0;
        if (i == 2) {
            aVar.J0(1);
        } else if (i == 1) {
            aVar.J0(2);
        }
    }
}
