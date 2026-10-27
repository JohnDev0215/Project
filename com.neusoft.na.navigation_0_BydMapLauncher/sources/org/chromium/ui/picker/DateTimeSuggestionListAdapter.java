package org.chromium.ui.picker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import org.chromium.ui.R;

/* JADX INFO: loaded from: classes4.dex */
class DateTimeSuggestionListAdapter extends ArrayAdapter<DateTimeSuggestion> {
    private final Context mContext;

    DateTimeSuggestionListAdapter(Context context, List<DateTimeSuggestion> list) {
        super(context, R.layout.date_time_suggestion, list);
        this.mContext = context;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.date_time_suggestion, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.date_time_suggestion_value);
        TextView textView2 = (TextView) view.findViewById(R.id.date_time_suggestion_label);
        if (i == getCount() - 1) {
            textView.setText(this.mContext.getText(R.string.date_picker_dialog_other_button_label));
            textView2.setText("");
        } else {
            textView.setText(getItem(i).localizedValue());
            textView2.setText(getItem(i).label());
        }
        return view;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        return super.getCount() + 1;
    }
}
