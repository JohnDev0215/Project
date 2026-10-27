package com.byd.carsettings.adapter.dipilot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;
import com.byd.carsettings.model.dipilot.CockpitPerceptionAssistModel;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CockpitPerceptionAssistAdapter extends RecyclerView.Adapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f2766a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f2767b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<CockpitPerceptionAssistModel> f2768c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2769d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2770e;

    public interface a {
        void a(CockpitPerceptionAssistModel cockpitPerceptionAssistModel, int i);

        default void b(CockpitPerceptionAssistModel cockpitPerceptionAssistModel, int i) {
        }

        void c(CockpitPerceptionAssistModel cockpitPerceptionAssistModel, boolean z, int i);
    }

    public class b extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ImageView f2771a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ImageView f2772b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f2773c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public TextView f2774d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public TextView f2775e;

        public b(View view) {
            super(view);
            this.f2771a = (ImageView) view.findViewById(R.id.iv_assist_notice);
            this.f2773c = (TextView) view.findViewById(R.id.tv_assist_title);
            this.f2774d = (TextView) view.findViewById(R.id.tv_assist_des);
            this.f2775e = (TextView) view.findViewById(R.id.tv_assist_select);
            this.f2772b = (ImageView) view.findViewById(R.id.iv_assist_more);
        }
    }

    public class c extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public BydSwitch f2777a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ImageView f2778b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f2779c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public TextView f2780d;

        public c(View view) {
            super(view);
            this.f2777a = (BydSwitch) view.findViewById(R.id.switch_recognition_intelligent_speed_control);
            this.f2778b = (ImageView) view.findViewById(R.id.iv_assist_notice);
            this.f2779c = (TextView) view.findViewById(R.id.tv_assist_title);
            this.f2780d = (TextView) view.findViewById(R.id.tv_assist_des);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(CockpitPerceptionAssistModel cockpitPerceptionAssistModel, int i, CompoundButton compoundButton, boolean z) {
        a aVar = this.f2767b;
        if (aVar != null) {
            aVar.c(cockpitPerceptionAssistModel, z, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void e(CockpitPerceptionAssistModel cockpitPerceptionAssistModel, int i, View view) {
        a aVar = this.f2767b;
        if (aVar != null) {
            aVar.a(cockpitPerceptionAssistModel, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void g(CockpitPerceptionAssistModel cockpitPerceptionAssistModel, int i, View view) {
        a aVar = this.f2767b;
        if (aVar != null) {
            aVar.a(cockpitPerceptionAssistModel, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void j(CockpitPerceptionAssistModel cockpitPerceptionAssistModel, int i, View view) {
        a aVar = this.f2767b;
        if (aVar != null) {
            aVar.b(cockpitPerceptionAssistModel, i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<CockpitPerceptionAssistModel> list = this.f2768c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return (i == 0 || i == 1) ? this.f2769d : this.f2770e;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof c) {
            final CockpitPerceptionAssistModel cockpitPerceptionAssistModel = this.f2768c.get(i);
            c cVar = (c) viewHolder;
            cVar.f2779c.setText(cockpitPerceptionAssistModel.getAssistTitle());
            cVar.f2780d.setText(cockpitPerceptionAssistModel.getAssistDes());
            cVar.f2777a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: b.b.g.c.b.g
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    this.f747a.b(cockpitPerceptionAssistModel, i, compoundButton, z);
                }
            });
            cVar.f2778b.setOnClickListener(new View.OnClickListener() { // from class: b.b.g.c.b.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f738a.e(cockpitPerceptionAssistModel, i, view);
                }
            });
            return;
        }
        if (viewHolder instanceof b) {
            final CockpitPerceptionAssistModel cockpitPerceptionAssistModel2 = this.f2768c.get(i);
            b bVar = (b) viewHolder;
            bVar.f2773c.setText(cockpitPerceptionAssistModel2.getAssistTitle());
            bVar.f2774d.setText(cockpitPerceptionAssistModel2.getAssistDes());
            bVar.f2775e.setText(cockpitPerceptionAssistModel2.getAssistMode());
            bVar.f2771a.setOnClickListener(new View.OnClickListener() { // from class: b.b.g.c.b.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f741a.g(cockpitPerceptionAssistModel2, i, view);
                }
            });
            bVar.f2772b.setOnClickListener(new View.OnClickListener() { // from class: b.b.g.c.b.f
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f744a.j(cockpitPerceptionAssistModel2, i, view);
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (this.f2769d == i) {
            return new b(LayoutInflater.from(this.f2766a).inflate(R.layout.item_advance_driver_assist2, viewGroup, false));
        }
        if (this.f2770e == i) {
            return new c(LayoutInflater.from(this.f2766a).inflate(R.layout.item_advance_driver_assist, viewGroup, false));
        }
        return null;
    }
}
