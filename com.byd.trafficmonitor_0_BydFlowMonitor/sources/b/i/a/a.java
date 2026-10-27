package b.i.a;

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
import b.i.a.b;

/* JADX INFO: compiled from: CursorAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends BaseAdapter implements Filterable, b.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f2710b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2711c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Cursor f2712d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f2713e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2714f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public C0047a f2715g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public DataSetObserver f2716h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public b.i.a.b f2717i;

    /* JADX INFO: renamed from: b.i.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CursorAdapter.java */
    public class C0047a extends ContentObserver {
        public C0047a() {
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
            aVar.f2710b = true;
            aVar.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            a aVar = a.this;
            aVar.f2710b = false;
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
            this.f2711c = true;
        } else {
            this.f2711c = false;
        }
        boolean z = cursor != null;
        this.f2712d = cursor;
        this.f2710b = z;
        this.f2713e = context;
        this.f2714f = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i2 & 2) == 2) {
            this.f2715g = new C0047a();
            this.f2716h = new b();
        } else {
            this.f2715g = null;
            this.f2716h = null;
        }
        if (z) {
            C0047a c0047a = this.f2715g;
            if (c0047a != null) {
                cursor.registerContentObserver(c0047a);
            }
            DataSetObserver dataSetObserver = this.f2716h;
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
        if (!this.f2711c || (cursor = this.f2712d) == null || cursor.isClosed()) {
            return;
        }
        this.f2710b = this.f2712d.requery();
    }

    public Cursor f(Cursor cursor) {
        Cursor cursor2 = this.f2712d;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            C0047a c0047a = this.f2715g;
            if (c0047a != null) {
                cursor2.unregisterContentObserver(c0047a);
            }
            DataSetObserver dataSetObserver = this.f2716h;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f2712d = cursor;
        if (cursor != null) {
            C0047a c0047a2 = this.f2715g;
            if (c0047a2 != null) {
                cursor.registerContentObserver(c0047a2);
            }
            DataSetObserver dataSetObserver2 = this.f2716h;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.f2714f = cursor.getColumnIndexOrThrow("_id");
            this.f2710b = true;
            notifyDataSetChanged();
        } else {
            this.f2714f = -1;
            this.f2710b = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        Cursor cursor;
        if (!this.f2710b || (cursor = this.f2712d) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // b.i.a.b.a
    public Cursor getCursor() {
        return this.f2712d;
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        if (!this.f2710b) {
            return null;
        }
        this.f2712d.moveToPosition(i2);
        if (view == null) {
            view = c(this.f2713e, this.f2712d, viewGroup);
        }
        a(view, this.f2713e, this.f2712d);
        return view;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.f2717i == null) {
            this.f2717i = new b.i.a.b(this);
        }
        return this.f2717i;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        Cursor cursor;
        if (!this.f2710b || (cursor = this.f2712d) == null) {
            return null;
        }
        cursor.moveToPosition(i2);
        return this.f2712d;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        Cursor cursor;
        if (this.f2710b && (cursor = this.f2712d) != null && cursor.moveToPosition(i2)) {
            return this.f2712d.getLong(this.f2714f);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (!this.f2710b) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (this.f2712d.moveToPosition(i2)) {
            if (view == null) {
                view = d(this.f2713e, this.f2712d, viewGroup);
            }
            a(view, this.f2713e, this.f2712d);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i2);
    }
}
