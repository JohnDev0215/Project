package p;

import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

/* JADX INFO: loaded from: classes.dex */
public abstract class s40 extends BaseAdapter implements Filterable, t40 {
    public boolean c;
    public boolean e;
    public Cursor k;
    public int l;
    public r40 m;
    public fb1 n;
    public u40 o;

    public abstract void a(View view, Cursor cursor);

    public void b(Cursor cursor) {
        Cursor cursor2 = this.k;
        if (cursor == cursor2) {
            cursor2 = null;
        } else {
            if (cursor2 != null) {
                r40 r40Var = this.m;
                if (r40Var != null) {
                    cursor2.unregisterContentObserver(r40Var);
                }
                fb1 fb1Var = this.n;
                if (fb1Var != null) {
                    cursor2.unregisterDataSetObserver(fb1Var);
                }
            }
            this.k = cursor;
            if (cursor != null) {
                r40 r40Var2 = this.m;
                if (r40Var2 != null) {
                    cursor.registerContentObserver(r40Var2);
                }
                fb1 fb1Var2 = this.n;
                if (fb1Var2 != null) {
                    cursor.registerDataSetObserver(fb1Var2);
                }
                this.l = cursor.getColumnIndexOrThrow("_id");
                this.c = true;
                notifyDataSetChanged();
            } else {
                this.l = -1;
                this.c = false;
                notifyDataSetInvalidated();
            }
        }
        if (cursor2 != null) {
            cursor2.close();
        }
    }

    public abstract String c(Cursor cursor);

    public abstract View d(ViewGroup viewGroup);

    @Override // android.widget.Adapter
    public final int getCount() {
        Cursor cursor;
        if (!this.c || (cursor = this.k) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (!this.c) {
            return null;
        }
        this.k.moveToPosition(i);
        if (view == null) {
            dh2 dh2Var = (dh2) this;
            view = dh2Var.r.inflate(dh2Var.q, viewGroup, false);
        }
        a(view, this.k);
        return view;
    }

    @Override // android.widget.Filterable
    public final Filter getFilter() {
        if (this.o == null) {
            u40 u40Var = new u40();
            u40Var.a = this;
            this.o = u40Var;
        }
        return this.o;
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        Cursor cursor;
        if (!this.c || (cursor = this.k) == null) {
            return null;
        }
        cursor.moveToPosition(i);
        return this.k;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        Cursor cursor;
        if (this.c && (cursor = this.k) != null && cursor.moveToPosition(i)) {
            return this.k.getLong(this.l);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.c) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (!this.k.moveToPosition(i)) {
            throw new IllegalStateException(u4.e("couldn't move cursor to position ", i));
        }
        if (view == null) {
            view = d(viewGroup);
        }
        a(view, this.k);
        return view;
    }
}
