package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import org.chromium.content.R$id;
import org.chromium.content.R$layout;
import org.chromium.content.R$string;
import org.chromium.content.browser.picker.DateTimeSuggestion;

/* JADX INFO: compiled from: DateTimeSuggestionListAdapter.java */
/* JADX INFO: loaded from: classes3.dex */
public final class y60 extends ArrayAdapter<DateTimeSuggestion> {
    public final Context e;

    public y60(Context context, List<DateTimeSuggestion> list) {
        super(context, R$layout.date_time_suggestion, list);
        this.e = context;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final int getCount() {
        return super.getCount() + 1;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        Context context = this.e;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R$layout.date_time_suggestion, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(R$id.date_time_suggestion_value);
        TextView textView2 = (TextView) view.findViewById(R$id.date_time_suggestion_label);
        if (i == getCount() - 1) {
            textView.setText(context.getText(R$string.date_picker_dialog_other_button_label));
            textView2.setText("");
        } else {
            textView.setText(getItem(i).b);
            textView2.setText(getItem(i).c);
        }
        return view;
    }
}
