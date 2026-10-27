package defpackage;

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
import defpackage.k20;

/* JADX INFO: compiled from: CursorAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j20 extends BaseAdapter implements Filterable, k20.a {
    public k20 k;
    public boolean f = true;
    public Cursor g = null;
    public boolean e = false;
    public int h = -1;
    public a i = new a();
    public b j = new b();

    /* JADX INFO: compiled from: CursorAdapter.java */
    public class a extends ContentObserver {
        public a() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public final boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            Cursor cursor;
            j20 j20Var = j20.this;
            if (!j20Var.f || (cursor = j20Var.g) == null || cursor.isClosed()) {
                return;
            }
            j20Var.e = j20Var.g.requery();
        }
    }

    /* JADX INFO: compiled from: CursorAdapter.java */
    public class b extends DataSetObserver {
        public b() {
        }

        @Override // android.database.DataSetObserver
        public final void onChanged() {
            j20 j20Var = j20.this;
            j20Var.e = true;
            j20Var.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public final void onInvalidated() {
            j20 j20Var = j20.this;
            j20Var.e = false;
            j20Var.notifyDataSetInvalidated();
        }
    }

    public j20(Context context) {
    }

    public void a(Cursor cursor) {
        Cursor cursor2 = this.g;
        if (cursor == cursor2) {
            cursor2 = null;
        } else {
            if (cursor2 != null) {
                a aVar = this.i;
                if (aVar != null) {
                    cursor2.unregisterContentObserver(aVar);
                }
                b bVar = this.j;
                if (bVar != null) {
                    cursor2.unregisterDataSetObserver(bVar);
                }
            }
            this.g = cursor;
            if (cursor != null) {
                a aVar2 = this.i;
                if (aVar2 != null) {
                    cursor.registerContentObserver(aVar2);
                }
                b bVar2 = this.j;
                if (bVar2 != null) {
                    cursor.registerDataSetObserver(bVar2);
                }
                this.h = cursor.getColumnIndexOrThrow("_id");
                this.e = true;
                notifyDataSetChanged();
            } else {
                this.h = -1;
                this.e = false;
                notifyDataSetInvalidated();
            }
        }
        if (cursor2 != null) {
            cursor2.close();
        }
    }

    public abstract String c(Cursor cursor);

    public abstract void e(View view, Cursor cursor);

    public abstract View f(ViewGroup viewGroup);

    @Override // android.widget.Adapter
    public final int getCount() {
        Cursor cursor;
        if (!this.e || (cursor = this.g) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        Cursor cursor;
        if (!this.e || (cursor = this.g) == null) {
            return null;
        }
        cursor.moveToPosition(i);
        if (view == null) {
            qb2 qb2Var = (qb2) this;
            view = qb2Var.n.inflate(qb2Var.m, viewGroup, false);
        }
        e(view, this.g);
        return view;
    }

    @Override // android.widget.Filterable
    public final Filter getFilter() {
        if (this.k == null) {
            this.k = new k20(this);
        }
        return this.k;
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        Cursor cursor;
        if (!this.e || (cursor = this.g) == null) {
            return null;
        }
        cursor.moveToPosition(i);
        return this.g;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        Cursor cursor;
        if (this.e && (cursor = this.g) != null && cursor.moveToPosition(i)) {
            return this.g.getLong(this.h);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.e) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        Cursor cursor = this.g;
        if (cursor == null) {
            throw new IllegalStateException("this should only be called when the cursor is non-null");
        }
        if (!cursor.moveToPosition(i)) {
            throw new IllegalStateException(gq2.e("couldn't move cursor to position ", i));
        }
        if (view == null) {
            view = f(viewGroup);
        }
        e(view, this.g);
        return view;
    }
}
