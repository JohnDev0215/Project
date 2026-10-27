package com.byd.carsettings.adapter.carsettings;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.b.g.n.q.g;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.carsettings.viewholder.LightViewHolder;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class LightAdapter extends RecyclerView.Adapter<LightViewHolder> implements b.b.o.b.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<b.b.g.c.a.a.b> f2655a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f2656b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b f2657c;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2658a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ LightViewHolder f2659b;

        public a(int i, LightViewHolder lightViewHolder) {
            this.f2658a = i;
            this.f2659b = lightViewHolder;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if ("day_time_light".equals(((b.b.g.c.a.a.b) LightAdapter.this.f2655a.get(this.f2658a)).c()) && !LightAdapter.this.e()) {
                Toast.makeText(LightAdapter.this.f2656b, R.string.vehiclesettings_light_control_light_state_rear_day_light_warn, 0).show();
                return;
            }
            if (((b.b.g.c.a.a.b) LightAdapter.this.f2655a.get(this.f2658a)).d()) {
                this.f2659b.f2669a.setChecked(false);
                ((b.b.g.c.a.a.b) LightAdapter.this.f2655a.get(this.f2658a)).e(false);
                b bVar = LightAdapter.this.f2657c;
                if (bVar != null) {
                    bVar.n(this.f2658a, false);
                    return;
                }
                return;
            }
            this.f2659b.f2669a.setChecked(true);
            ((b.b.g.c.a.a.b) LightAdapter.this.f2655a.get(this.f2658a)).e(true);
            b bVar2 = LightAdapter.this.f2657c;
            if (bVar2 != null) {
                bVar2.n(this.f2658a, true);
            }
        }
    }

    public interface b {
        void n(int i, boolean z);
    }

    public LightAdapter(Context context, List<b.b.g.c.a.a.b> list) {
        this.f2655a = new ArrayList();
        this.f2655a = list;
        this.f2656b = context;
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return h();
    }

    public final boolean e() {
        return g.g().b() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NonNull LightViewHolder lightViewHolder, int i) {
        Drawable drawable = this.f2656b.getDrawable(this.f2655a.get(i).a());
        drawable.setBounds(0, 0, 40, 40);
        lightViewHolder.f2669a.setChecked(this.f2655a.get(i).d());
        lightViewHolder.f2669a.setCompoundDrawables(null, drawable, null, null);
        lightViewHolder.f2669a.setText(this.f2655a.get(i).b());
        lightViewHolder.f2669a.setOnClickListener(new a(i, lightViewHolder));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public LightViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LightViewHolder(LayoutInflater.from(this.f2656b).inflate(b.b.o.b.b.a.a(this), viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f2655a.size();
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.car_light_item;
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return h();
    }

    public void setListener(b bVar) {
        this.f2657c = bVar;
    }
}
