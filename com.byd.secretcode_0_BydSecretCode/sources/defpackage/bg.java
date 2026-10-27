package defpackage;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.secretcode.R;
import com.byd.secretcode.activity.CountryCodeActivity;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: CountryCodeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class bg extends RecyclerView.g {
    public CountryCodeActivity c;
    public List<HashMap> d;
    public int e = -1;
    public RecyclerView f = null;
    public View g;

    /* JADX INFO: compiled from: CountryCodeAdapter.java */
    public class a implements View.OnClickListener {
        public final /* synthetic */ b b;

        public a(b bVar) {
            this.b = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iJ = this.b.j();
            bg.this.v(iJ);
            bg.this.c.B(iJ);
        }
    }

    /* JADX INFO: compiled from: CountryCodeAdapter.java */
    public class b extends RecyclerView.c0 {
        public TextView t;
        public TextView u;
        public TextView v;
        public LinearLayout w;

        public b(View view) {
            super(view);
            this.t = (TextView) view.findViewById(R.id.tv1);
            this.u = (TextView) view.findViewById(R.id.tv2);
            this.v = (TextView) view.findViewById(R.id.tv3);
            this.w = (LinearLayout) view.findViewById(R.id.linearlayout);
        }
    }

    public bg(CountryCodeActivity countryCodeActivity, List<HashMap> list) {
        this.d = list;
        this.c = countryCodeActivity;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.d.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public long d(int i) {
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    @SuppressLint({"ResourceAsColor"})
    public void j(RecyclerView.c0 c0Var, int i) {
        b bVar = (b) c0Var;
        bVar.t.setText(this.d.get(i).get("countryandregion").toString());
        bVar.u.setText(this.d.get(i).get("internationaldomain").toString());
        bVar.v.setText(this.d.get(i).get("countrycode").toString());
        if (this.e == i) {
            bVar.w.setSelected(true);
            bVar.w.setPressed(true);
            bVar.w.setBackgroundColor(R.color.dicare_remind_dialog_warning);
        } else {
            bVar.w.setSelected(false);
            bVar.w.setPressed(false);
            bVar.w.setBackgroundColor(R.color.transparent);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public RecyclerView.c0 l(ViewGroup viewGroup, int i) {
        this.g = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_countrycode, viewGroup, false);
        b bVar = new b(this.g);
        bVar.w.setOnClickListener(new a(bVar));
        return bVar;
    }

    public void v(int i) {
        this.e = i;
        h();
    }
}
