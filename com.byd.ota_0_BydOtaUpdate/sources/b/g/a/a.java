package b.g.a;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import b.g.a.b;

/* JADX INFO: compiled from: CursorAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends BaseAdapter implements Filterable, b.a {
    public C0033a E3;
    public DataSetObserver F3;
    public b.g.a.b G3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1846c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1847d;
    public Cursor s;
    public Context x;
    public int y;

    /* JADX INFO: renamed from: b.g.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CursorAdapter.java */
    public class C0033a extends ContentObserver {
        public C0033a() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            a.this.e();
        }
    }

    /* JADX INFO: compiled from: CursorAdapter.java */
    public class b extends DataSetObserver {
        public b() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            a aVar = a.this;
            aVar.f1846c = true;
            aVar.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            a aVar = a.this;
            aVar.f1846c = false;
            aVar.notifyDataSetInvalidated();
        }
    }

    public a(Context context, Cursor cursor, boolean z) {
        b(context, cursor, z ? 1 : 2);
    }

    public abstract void a(View view, Context context, Cursor cursor);

    public void b(Context context, Cursor cursor, int i2) {
        if ((i2 & 1) == 1) {
            i2 |= 2;
            this.f1847d = true;
        } else {
            this.f1847d = false;
        }
        boolean z = cursor != null;
        this.s = cursor;
        this.f1846c = z;
        this.x = context;
        this.y = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i2 & 2) == 2) {
            this.E3 = new C0033a();
            this.F3 = new b();
        } else {
            this.E3 = null;
            this.F3 = null;
        }
        if (z) {
            C0033a c0033a = this.E3;
            if (c0033a != null) {
                cursor.registerContentObserver(c0033a);
            }
            DataSetObserver dataSetObserver = this.F3;
            if (dataSetObserver != null) {
                cursor.registerDataSetObserver(dataSetObserver);
            }
        }
    }

    public abstract View c(Context context, Cursor cursor, ViewGroup viewGroup);

    public void changeCursor(Cursor cursor) {
        Cursor cursorF = f(cursor);
        if (cursorF != null) {
            cursorF.close();
        }
    }

    public abstract CharSequence convertToString(Cursor cursor);

    public abstract View d(Context context, Cursor cursor, ViewGroup viewGroup);

    public void e() {
        Cursor cursor;
        if (!this.f1847d || (cursor = this.s) == null || cursor.isClosed()) {
            return;
        }
        this.f1846c = this.s.requery();
    }

    public Cursor f(Cursor cursor) {
        Cursor cursor2 = this.s;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            C0033a c0033a = this.E3;
            if (c0033a != null) {
                cursor2.unregisterContentObserver(c0033a);
            }
            DataSetObserver dataSetObserver = this.F3;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.s = cursor;
        if (cursor != null) {
            C0033a c0033a2 = this.E3;
            if (c0033a2 != null) {
                cursor.registerContentObserver(c0033a2);
            }
            DataSetObserver dataSetObserver2 = this.F3;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.y = cursor.getColumnIndexOrThrow("_id");
            this.f1846c = true;
            notifyDataSetChanged();
        } else {
            this.y = -1;
            this.f1846c = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        Cursor cursor;
        if (!this.f1846c || (cursor = this.s) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // b.g.a.b.a
    public Cursor getCursor() {
        return this.s;
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        if (!this.f1846c) {
            return null;
        }
        this.s.moveToPosition(i2);
        if (view == null) {
            view = c(this.x, this.s, viewGroup);
        }
        a(view, this.x, this.s);
        return view;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.G3 == null) {
            this.G3 = new b.g.a.b(this);
        }
        return this.G3;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        Cursor cursor;
        if (!this.f1846c || (cursor = this.s) == null) {
            return null;
        }
        cursor.moveToPosition(i2);
        return this.s;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        Cursor cursor;
        if (this.f1846c && (cursor = this.s) != null && cursor.moveToPosition(i2)) {
            return this.s.getLong(this.y);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (!this.f1846c) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (this.s.moveToPosition(i2)) {
            if (view == null) {
                view = d(this.x, this.s, viewGroup);
            }
            a(view, this.x, this.s);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i2);
    }
}
