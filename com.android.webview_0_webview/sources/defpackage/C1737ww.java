package defpackage;

import J.N;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import java.util.List;
import java.util.Objects;
import org.chromium.android_webview.AwAutofillClient;
import org.chromium.components.autofill.AutofillSuggestion;

/* JADX INFO: renamed from: ww, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1737ww extends C1435rN implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, PopupWindow.OnDismissListener, InterfaceC1353pw {
    public final Context t;
    public final InterfaceC1188mw u;
    public List v;
    public final Runnable w;

    public C1737ww(Context context, View view, InterfaceC1188mw interfaceC1188mw) {
        super(context, view);
        this.w = new RunnableC1627uw(this);
        this.t = context;
        this.u = interfaceC1188mw;
        this.s.f(this);
        this.s.j(this);
        this.s.c();
        this.s.d(this.t.getString(AbstractC0566bn.q0));
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        AwAutofillClient awAutofillClient = ((C0845gn) this.u).a;
        N.M2z_jbiy(awAutofillClient.a, awAutofillClient);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        int iIndexOf = this.v.indexOf(((C1243nw) adapterView.getAdapter()).getItem(i));
        AwAutofillClient awAutofillClient = ((C0845gn) this.u).a;
        N.Mg2XGOMM(awAutofillClient.a, awAutofillClient, iIndexOf);
    }

    @Override // android.widget.AdapterView.OnItemLongClickListener
    public boolean onItemLongClick(AdapterView adapterView, View view, int i, long j) {
        AutofillSuggestion autofillSuggestion = (AutofillSuggestion) ((C1243nw) adapterView.getAdapter()).getItem(i);
        if (!autofillSuggestion.f) {
            return false;
        }
        this.v.indexOf(autofillSuggestion);
        Objects.requireNonNull((C0845gn) this.u);
        return true;
    }
}
