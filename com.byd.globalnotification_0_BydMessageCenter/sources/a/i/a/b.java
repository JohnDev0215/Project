package a.i.a;

import a.b.p.r0;
import android.database.Cursor;
import android.widget.Filter;

/* JADX INFO: loaded from: classes.dex */
public class b extends Filter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f890a;

    public interface a {
    }

    public b(a aVar) {
        this.f890a = aVar;
    }

    @Override // android.widget.Filter
    public CharSequence convertResultToString(Object obj) {
        return ((r0) this.f890a).c((Cursor) obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
    @Override // android.widget.Filter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.widget.Filter.FilterResults performFiltering(java.lang.CharSequence r4) {
        /*
            r3 = this;
            a.i.a.b$a r3 = r3.f890a
            a.b.p.r0 r3 = (a.b.p.r0) r3
            r0 = 0
            if (r3 == 0) goto L4e
            if (r4 != 0) goto Lc
            java.lang.String r4 = ""
            goto L10
        Lc:
            java.lang.String r4 = r4.toString()
        L10:
            androidx.appcompat.widget.SearchView r1 = r3.l
            int r1 = r1.getVisibility()
            if (r1 != 0) goto L37
            androidx.appcompat.widget.SearchView r1 = r3.l
            int r1 = r1.getWindowVisibility()
            if (r1 == 0) goto L21
            goto L37
        L21:
            android.app.SearchableInfo r1 = r3.m     // Catch: java.lang.RuntimeException -> L2f
            r2 = 50
            android.database.Cursor r3 = r3.g(r1, r4, r2)     // Catch: java.lang.RuntimeException -> L2f
            if (r3 == 0) goto L37
            r3.getCount()     // Catch: java.lang.RuntimeException -> L2f
            goto L38
        L2f:
            r3 = move-exception
            java.lang.String r4 = "SuggestionsAdapter"
            java.lang.String r1 = "Search suggestions query threw an exception."
            android.util.Log.w(r4, r1, r3)
        L37:
            r3 = r0
        L38:
            android.widget.Filter$FilterResults r4 = new android.widget.Filter$FilterResults
            r4.<init>()
            if (r3 == 0) goto L48
            int r0 = r3.getCount()
            r4.count = r0
            r4.values = r3
            goto L4d
        L48:
            r3 = 0
            r4.count = r3
            r4.values = r0
        L4d:
            return r4
        L4e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: a.i.a.b.performFiltering(java.lang.CharSequence):android.widget.Filter$FilterResults");
    }

    @Override // android.widget.Filter
    public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        a aVar = this.f890a;
        Cursor cursor = ((a.i.a.a) aVar).f884c;
        Object obj = filterResults.values;
        if (obj == null || obj == cursor) {
            return;
        }
        ((r0) aVar).b((Cursor) obj);
    }
}
