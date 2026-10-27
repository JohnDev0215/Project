package o0;

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
import o0.b;

/* JADX INFO: compiled from: CursorAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends BaseAdapter implements Filterable, b.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f4436b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4437c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Cursor f4438d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f4439e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4440f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public C0043a f4441g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public DataSetObserver f4442h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public o0.b f4443i;

    /* JADX INFO: renamed from: o0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CursorAdapter.java */
    public class C0043a extends ContentObserver {
        public C0043a() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z3) {
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
            aVar.f4436b = true;
            aVar.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            a aVar = a.this;
            aVar.f4436b = false;
            aVar.notifyDataSetInvalidated();
        }
    }

    public a(Context context, Cursor cursor, boolean z3) {
        b(context, cursor, z3 ? 1 : 2);
    }

    public abstract void a(View view, Context context, Cursor cursor);

    public void b(Context context, Cursor cursor, int i3) {
        if ((i3 & 1) == 1) {
            i3 |= 2;
            this.f4437c = true;
        } else {
            this.f4437c = false;
        }
        boolean z3 = cursor != null;
        this.f4438d = cursor;
        this.f4436b = z3;
        this.f4439e = context;
        this.f4440f = z3 ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i3 & 2) == 2) {
            this.f4441g = new C0043a();
            this.f4442h = new b();
        } else {
            this.f4441g = null;
            this.f4442h = null;
        }
        if (z3) {
            C0043a c0043a = this.f4441g;
            if (c0043a != null) {
                cursor.registerContentObserver(c0043a);
            }
            DataSetObserver dataSetObserver = this.f4442h;
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
        if (!this.f4437c || (cursor = this.f4438d) == null || cursor.isClosed()) {
            return;
        }
        this.f4436b = this.f4438d.requery();
    }

    public Cursor f(Cursor cursor) {
        Cursor cursor2 = this.f4438d;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            C0043a c0043a = this.f4441g;
            if (c0043a != null) {
                cursor2.unregisterContentObserver(c0043a);
            }
            DataSetObserver dataSetObserver = this.f4442h;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f4438d = cursor;
        if (cursor != null) {
            C0043a c0043a2 = this.f4441g;
            if (c0043a2 != null) {
                cursor.registerContentObserver(c0043a2);
            }
            DataSetObserver dataSetObserver2 = this.f4442h;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.f4440f = cursor.getColumnIndexOrThrow("_id");
            this.f4436b = true;
            notifyDataSetChanged();
        } else {
            this.f4440f = -1;
            this.f4436b = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        Cursor cursor;
        if (!this.f4436b || (cursor = this.f4438d) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // o0.b.a
    public Cursor getCursor() {
        return this.f4438d;
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i3, View view, ViewGroup viewGroup) {
        if (!this.f4436b) {
            return null;
        }
        this.f4438d.moveToPosition(i3);
        if (view == null) {
            view = c(this.f4439e, this.f4438d, viewGroup);
        }
        a(view, this.f4439e, this.f4438d);
        return view;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.f4443i == null) {
            this.f4443i = new o0.b(this);
        }
        return this.f4443i;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i3) {
        Cursor cursor;
        if (!this.f4436b || (cursor = this.f4438d) == null) {
            return null;
        }
        cursor.moveToPosition(i3);
        return this.f4438d;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i3) {
        Cursor cursor;
        if (this.f4436b && (cursor = this.f4438d) != null && cursor.moveToPosition(i3)) {
            return this.f4438d.getLong(this.f4440f);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i3, View view, ViewGroup viewGroup) {
        if (!this.f4436b) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (this.f4438d.moveToPosition(i3)) {
            if (view == null) {
                view = d(this.f4439e, this.f4438d, viewGroup);
            }
            a(view, this.f4439e, this.f4438d);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i3);
    }
}
