package b.a.e;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import b.a.e.x.a;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class x<T, V extends a> extends BaseAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected List<T> f452a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected LayoutInflater f453b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected Context f454c;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        View f455a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f456b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f457c;

        public a(View view) {
            this.f455a = view;
        }
    }

    public x(Context context) {
        this.f454c = context;
        this.f453b = LayoutInflater.from(context);
    }

    protected abstract void a(T t, V v);

    protected abstract V b(int i);

    public List<T> c() {
        return this.f452a;
    }

    public void d(List<T> list) {
        this.f452a = list;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<T> list = this.f452a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public T getItem(int i) {
        return this.f452a.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVarB;
        int itemViewType = getItemViewType(i);
        if (view == null || ((aVarB = (a) view.getTag()) != null && aVarB.f457c != itemViewType)) {
            aVarB = b(itemViewType);
            view = aVarB.f455a;
            aVarB.f457c = itemViewType;
            view.setTag(aVarB);
        }
        T item = getItem(i);
        aVarB.f456b = i;
        a(item, aVarB);
        return view;
    }
}
