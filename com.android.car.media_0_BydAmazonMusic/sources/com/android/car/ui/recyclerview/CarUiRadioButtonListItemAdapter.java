package com.android.car.ui.recyclerview;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R$layout;
import com.android.car.ui.recyclerview.CarUiListItemAdapter;
import com.android.car.ui.recyclerview.CarUiRadioButtonListItemAdapter;
import java.util.List;
import java.util.Objects;
import spi.l1;
import spi.p1;

/* JADX INFO: loaded from: classes2.dex */
public class CarUiRadioButtonListItemAdapter extends CarUiListItemAdapter {
    public int d;

    public static class a extends CarUiListItemAdapter.c {

        @Nullable
        public InterfaceC0010a p;

        /* JADX INFO: renamed from: com.android.car.ui.recyclerview.CarUiRadioButtonListItemAdapter$a$a, reason: collision with other inner class name */
        public interface InterfaceC0010a {
        }

        public a(@NonNull View view) {
            super(view);
        }

        @Override // com.android.car.ui.recyclerview.CarUiListItemAdapter.c
        public final void a(@NonNull final com.android.car.ui.recyclerview.a aVar) {
            super.a(aVar);
            this.k.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: spi.q1
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    int i;
                    CarUiRadioButtonListItemAdapter.a aVar2 = this.a;
                    com.android.car.ui.recyclerview.a aVar3 = aVar;
                    Objects.requireNonNull(aVar2);
                    aVar3.a(z);
                    CarUiRadioButtonListItemAdapter.a.InterfaceC0010a interfaceC0010a = aVar2.p;
                    if (interfaceC0010a != null) {
                        p1 p1Var = (p1) interfaceC0010a;
                        CarUiRadioButtonListItemAdapter carUiRadioButtonListItemAdapter = p1Var.a;
                        int i2 = p1Var.b;
                        Objects.requireNonNull(carUiRadioButtonListItemAdapter);
                        if (z && (i = carUiRadioButtonListItemAdapter.d) >= 0) {
                            ((com.android.car.ui.recyclerview.b) carUiRadioButtonListItemAdapter.a.get(i)).a(false);
                            carUiRadioButtonListItemAdapter.notifyItemChanged(carUiRadioButtonListItemAdapter.d);
                        }
                        if (z) {
                            carUiRadioButtonListItemAdapter.d = i2;
                            ((com.android.car.ui.recyclerview.b) carUiRadioButtonListItemAdapter.a.get(i2)).a(true);
                            carUiRadioButtonListItemAdapter.notifyItemChanged(carUiRadioButtonListItemAdapter.d);
                        }
                    }
                }
            });
        }

        public void setOnCheckedChangeListener(@Nullable InterfaceC0010a interfaceC0010a) {
            this.p = interfaceC0010a;
        }
    }

    public CarUiRadioButtonListItemAdapter(List<b> list) {
        super(list);
        this.d = -1;
        for (int i = 0; i < list.size(); i++) {
            boolean z = list.get(i).e;
            if (z && this.d >= 0) {
                throw new IllegalStateException("At most one item in a CarUiRadioButtonListItemAdapter can be checked");
            }
            if (z) {
                this.d = i;
            }
        }
    }

    @Override // com.android.car.ui.recyclerview.CarUiListItemAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    @SuppressLint({"Correctness"})
    public final void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder.getItemViewType() != 1) {
            super.onBindViewHolder(viewHolder, i);
            return;
        }
        if (!(viewHolder instanceof a)) {
            throw new IllegalStateException("Incorrect view holder type for list item.");
        }
        l1 l1Var = this.a.get(i);
        if (!(l1Var instanceof b)) {
            throw new IllegalStateException("Expected item to be bound to viewHolder to be instance of CarUiRadioButtonListItem.");
        }
        a aVar = (a) viewHolder;
        aVar.a((b) l1Var);
        aVar.setOnCheckedChangeListener(new p1(this, i));
    }

    @Override // com.android.car.ui.recyclerview.CarUiListItemAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return i == 1 ? new a(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.car_ui_list_item, viewGroup, false)) : super.onCreateViewHolder(viewGroup, i);
    }
}
