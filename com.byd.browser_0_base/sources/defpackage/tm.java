package defpackage;

import android.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import defpackage.om3;
import java.util.ArrayList;
import java.util.WeakHashMap;
import org.chromium.components.page_info.CertificateViewer;

/* JADX INFO: compiled from: CertificateViewer.java */
/* JADX INFO: loaded from: classes3.dex */
public final class tm extends ArrayAdapter<String> {
    public final /* synthetic */ CertificateViewer e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public tm(CertificateViewer certificateViewer, Context context, ArrayList arrayList) {
        super(context, R.layout.simple_spinner_item, arrayList);
        this.e = certificateViewer;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) super.getView(i, view, viewGroup);
        int i2 = this.e.f;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.e.i(textView, i2, i2, i2 * 2, i2);
        return textView;
    }
}
