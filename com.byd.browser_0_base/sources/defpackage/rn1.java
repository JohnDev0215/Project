package defpackage;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import java.util.List;

/* JADX INFO: compiled from: NoFilterArrayAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class rn1<T> extends ArrayAdapter<T> {
    public final List<T> e;
    public final rn1<T>.a f;

    /* JADX INFO: compiled from: NoFilterArrayAdapter.java */
    public class a extends Filter {
        public a() {
        }

        @Override // android.widget.Filter
        public final Filter.FilterResults performFiltering(CharSequence charSequence) {
            Filter.FilterResults filterResults = new Filter.FilterResults();
            List<T> list = rn1.this.e;
            filterResults.values = list;
            filterResults.count = list.size();
            return filterResults;
        }

        @Override // android.widget.Filter
        public final void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            rn1.this.notifyDataSetChanged();
        }
    }

    public rn1(int i, Context context, List list) {
        super(context, i, list);
        this.e = list;
        this.f = new a();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Filterable
    public final Filter getFilter() {
        return this.f;
    }
}
