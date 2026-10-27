package defpackage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import java.util.ArrayList;
import org.chromium.components.autofill.AutofillSuggestion;
import org.chromium.components.autofill.R$string;

/* JADX INFO: compiled from: AutofillPopup.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cb extends qe0 implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, PopupWindow.OnDismissListener {
    public final Context f;
    public final ta g;
    public ArrayList h;
    public final a i;

    /* JADX INFO: compiled from: AutofillPopup.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            cb.this.g.c();
        }
    }

    public cb(Activity activity, View view, ta taVar, sq3 sq3Var) {
        super(activity, view, sq3Var);
        this.i = new a();
        this.f = activity;
        this.g = taVar;
        setOnItemClickListener(this);
        setOnDismissListener(this);
        p4 p4Var = this.e.k;
        p4Var.m = false;
        p4Var.j.setOutsideTouchable(false);
        this.e.j = activity.getString(R$string.autofill_popup_content_description);
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        this.g.d();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.g.b(this.h.indexOf(((ua) adapterView.getAdapter()).getItem(i)));
    }

    @Override // android.widget.AdapterView.OnItemLongClickListener
    public final boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        AutofillSuggestion autofillSuggestion = (AutofillSuggestion) ((ua) adapterView.getAdapter()).getItem(i);
        if (!autofillSuggestion.i) {
            return false;
        }
        this.g.a(this.h.indexOf(autofillSuggestion));
        return true;
    }
}
