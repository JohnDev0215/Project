package defpackage;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.secretcode.R;
import com.byd.secretcode.activity.AutomaticallyActivity;
import java.util.List;

/* JADX INFO: compiled from: AutomaticallyAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class ag extends RecyclerView.g {
    public AutomaticallyActivity c;
    public List<String> d;
    public int e = -1;
    public RecyclerView f = null;
    public View g;

    /* JADX INFO: compiled from: AutomaticallyAdapter.java */
    public class a implements View.OnClickListener {
        public final /* synthetic */ b b;

        public a(b bVar) {
            this.b = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iJ = this.b.j();
            ag.this.v(iJ);
            ag.this.c.q(iJ);
        }
    }

    /* JADX INFO: compiled from: AutomaticallyAdapter.java */
    public class b extends RecyclerView.c0 {
        public TextView t;
        public TextView u;
        public LinearLayout v;

        public b(View view) {
            super(view);
            this.t = (TextView) view.findViewById(R.id.tv1_automatically);
            this.u = (TextView) view.findViewById(R.id.tv2_automatically);
            this.v = (LinearLayout) view.findViewById(R.id.linearlayout);
        }
    }

    public ag(AutomaticallyActivity automaticallyActivity, List<String> list) {
        this.d = list;
        this.c = automaticallyActivity;
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
        bVar.t.setText(this.d.get(i));
        if (this.e == i) {
            bVar.v.setSelected(true);
            bVar.v.setPressed(true);
            bVar.v.setBackgroundColor(R.color.dicare_remind_dialog_warning);
        } else {
            bVar.v.setSelected(false);
            bVar.v.setPressed(false);
            bVar.v.setBackgroundColor(R.color.transparent);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public RecyclerView.c0 l(ViewGroup viewGroup, int i) {
        this.g = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_automatically, viewGroup, false);
        b bVar = new b(this.g);
        bVar.v.setOnClickListener(new a(bVar));
        return bVar;
    }

    public void v(int i) {
        this.e = i;
        h();
    }

    public void w(List<String> list) {
        this.d = list;
    }
}
