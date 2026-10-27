package z4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.uereport.R;
import g5.k;
import g5.n;
import java.util.List;

/* JADX INFO: compiled from: OilMaintenanceListAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends RecyclerView.g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f9513a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<a5.a> f9514b;

    /* JADX INFO: renamed from: z4.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: OilMaintenanceListAdapter.java */
    public class C0153a implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ a5.a f9515d;

        public C0153a(a5.a aVar) {
            this.f9515d = aVar;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
            if (compoundButton.isPressed()) {
                int iC = this.f9515d.c();
                if (iC == 0) {
                    k.g(a.this.f9513a, "oil_engine", z7);
                    return;
                }
                if (1 == iC) {
                    k.g(a.this.f9513a, "oil_transmission", z7);
                    return;
                }
                if (2 == iC) {
                    k.g(a.this.f9513a, "oil_brake", z7);
                } else if (3 == iC) {
                    k.g(a.this.f9513a, "blade_battery", z7);
                } else if (4 == iC) {
                    k.g(a.this.f9513a, "motor_elec", z7);
                }
            }
        }
    }

    /* JADX INFO: compiled from: OilMaintenanceListAdapter.java */
    public static class b extends RecyclerView.c0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f9517a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f9518b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f9519c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public CheckBox f9520d;

        public b(View view) {
            super(view);
            this.f9517a = (TextView) view.findViewById(R.id.tv_title);
            this.f9518b = (TextView) view.findViewById(R.id.tv_life_remain);
            this.f9520d = (CheckBox) view.findViewById(R.id.cb_oil);
            this.f9519c = (TextView) view.findViewById(R.id.tv_hint);
        }
    }

    public a(Context context) {
        this.f9513a = context;
    }

    public void b(List<a5.a> list) {
        this.f9514b = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemCount() {
        List<a5.a> list = this.f9514b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    @SuppressLint({"StringFormatInvalid"})
    public void onBindViewHolder(RecyclerView.c0 c0Var, int i8) {
        b bVar = (b) c0Var;
        a5.a aVar = this.f9514b.get(i8);
        bVar.f9517a.setText(aVar.b());
        bVar.f9518b.setText(String.format(this.f9513a.getResources().getString(R.string.carhealthy_oil_life_remain), Integer.valueOf(aVar.d())));
        if (aVar.a() == 1) {
            bVar.f9518b.setTextColor(this.f9513a.getResources().getColor(R.color.carmsg_serious));
            n.a(bVar.f9519c);
            bVar.f9520d.setChecked(false);
            n.a(bVar.f9520d);
        } else {
            bVar.f9518b.setTextColor(this.f9513a.getResources().getColor(R.color.text_color_main));
            n.b(bVar.f9519c);
            bVar.f9520d.setChecked(aVar.e());
            n.b(bVar.f9520d);
        }
        bVar.f9520d.setOnCheckedChangeListener(new C0153a(aVar));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public RecyclerView.c0 onCreateViewHolder(ViewGroup viewGroup, int i8) {
        return new b(LayoutInflater.from(this.f9513a).inflate(R.layout.item_oil_maintenance, viewGroup, false));
    }
}
