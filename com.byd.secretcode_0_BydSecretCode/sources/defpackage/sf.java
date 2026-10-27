package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.byd.secretcode.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.List;

/* JADX INFO: compiled from: SatelliteAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class sf extends BaseAdapter {
    public Context b;
    public List<String> c;

    /* JADX INFO: compiled from: SatelliteAdapter.java */
    public class a {
        public TextView a;

        public a() {
        }
    }

    public sf(List<String> list, Context context) {
        this.c = list;
        this.b = context;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.c.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.c.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        Context context;
        if (view != null || (context = this.b) == null) {
            aVar = (a) view.getTag();
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_rv_satellite, (ViewGroup) null);
            aVar = new a();
            aVar.a = (TextView) view.findViewById(R.id.tv_svinfo);
            view.setTag(aVar);
        }
        BydLogHelper.logD("getView / " + this.c.get(i));
        aVar.a.setText(this.c.get(i));
        return view;
    }
}
