package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.secretcode.R;
import java.util.ArrayList;

/* JADX INFO: compiled from: NetworkAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class cg extends RecyclerView.g<yg> {
    public int[] c = {R.mipmap.network_message_0, R.mipmap.network_message_2, R.mipmap.network_message_3, R.mipmap.network_message_4};
    public ArrayList<eg> d;
    public Context e;
    public b f;

    /* JADX INFO: compiled from: NetworkAdapter.java */
    public class a implements View.OnClickListener {
        public final /* synthetic */ int b;

        public a(int i) {
            this.b = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            cg cgVar = cg.this;
            b bVar = cgVar.f;
            if (bVar != null) {
                bVar.e(((eg) cgVar.d.get(this.b)).c().getMccMnc(), ((eg) cg.this.d.get(this.b)).c().getSigLevel());
            }
        }
    }

    /* JADX INFO: compiled from: NetworkAdapter.java */
    public interface b {
        void e(String str, int i);
    }

    public cg(ArrayList<eg> arrayList, Context context) {
        this.d = arrayList;
        this.e = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.d.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int e(int i) {
        return (i != 0 || this.d.get(i).a() == 0) ? 1 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
    public void j(yg ygVar, int i) {
        ygVar.w.setBackgroundResource(R.drawable.item_selector);
        ygVar.t.setImageResource(this.c[this.d.get(i).c().getSigLevel()]);
        ygVar.u.setText(this.d.get(i).c().getMccMnc());
        int iA = this.d.get(i).a();
        ygVar.v.setVisibility(iA == 0 ? 8 : 0);
        if (iA != 0) {
            ygVar.v.setText(this.d.get(i).b());
        }
        ygVar.w.setOnClickListener(new a(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
    public yg l(ViewGroup viewGroup, int i) {
        return i == 0 ? new zg(LayoutInflater.from(this.e).inflate(R.layout.network_selected_header_item, viewGroup, false)) : new ah(LayoutInflater.from(this.e).inflate(R.layout.network_selected_list_item, viewGroup, false));
    }

    public void x(b bVar) {
        this.f = bVar;
    }
}
