package defpackage;

import android.database.Cursor;
import android.widget.Filter;

/* JADX INFO: compiled from: CursorFilter.java */
/* JADX INFO: loaded from: classes.dex */
public final class k20 extends Filter {
    public final a a;

    /* JADX INFO: compiled from: CursorFilter.java */
    public interface a {
        void a(Cursor cursor);

        String c(Cursor cursor);

        Cursor d(CharSequence charSequence);
    }

    public k20(a aVar) {
        this.a = aVar;
    }

    @Override // android.widget.Filter
    public final CharSequence convertResultToString(Object obj) {
        return this.a.c((Cursor) obj);
    }

    @Override // android.widget.Filter
    public final Filter.FilterResults performFiltering(CharSequence charSequence) {
        Cursor cursorD = this.a.d(charSequence);
        Filter.FilterResults filterResults = new Filter.FilterResults();
        if (cursorD != null) {
            filterResults.count = cursorD.getCount();
            filterResults.values = cursorD;
        } else {
            filterResults.count = 0;
            filterResults.values = null;
        }
        return filterResults;
    }

    @Override // android.widget.Filter
    public final void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        a aVar = this.a;
        Cursor cursor = ((j20) aVar).g;
        Object obj = filterResults.values;
        if (obj == null || obj == cursor) {
            return;
        }
        aVar.a((Cursor) obj);
    }
}
