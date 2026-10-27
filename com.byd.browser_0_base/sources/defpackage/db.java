package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ListView;
import defpackage.cb;

/* JADX INFO: compiled from: AutofillPopup.java */
/* JADX INFO: loaded from: classes2.dex */
public final class db extends View.AccessibilityDelegate {
    public final /* synthetic */ cb a;

    public db(cb cbVar) {
        this.a = cbVar;
    }

    @Override // android.view.View.AccessibilityDelegate
    public final boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        cb cbVar = this.a;
        ListView listView = cbVar.e.m;
        cb.a aVar = cbVar.i;
        listView.removeCallbacks(aVar);
        if (accessibilityEvent.getEventType() == 65536) {
            cbVar.e.m.postDelayed(aVar, 100L);
        }
        return super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }
}
