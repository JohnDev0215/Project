package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import org.chromium.content.browser.picker.DateTimeSuggestion;

/* JADX INFO: renamed from: iC, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0927iC extends ArrayAdapter {
    public final Context s;

    public C0927iC(Context context, List list) {
        super(context, AbstractC0425Xm.O, list);
        this.s = context;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        return super.getCount() + 1;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.s).inflate(AbstractC0425Xm.O, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(AbstractC0371Um.G0);
        TextView textView2 = (TextView) view.findViewById(AbstractC0371Um.F0);
        if (i == getCount() - 1) {
            textView.setText(this.s.getText(AbstractC0566bn.l1));
            textView2.setText("");
        } else {
            textView.setText(((DateTimeSuggestion) getItem(i)).b);
            textView2.setText(((DateTimeSuggestion) getItem(i)).c);
        }
        return view;
    }
}
