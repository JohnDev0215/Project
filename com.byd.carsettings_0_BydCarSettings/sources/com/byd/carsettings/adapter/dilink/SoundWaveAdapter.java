package com.byd.carsettings.adapter.dilink;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import b.b.m.f.c.a.b;
import b.b.m.f.c.a.e;
import com.byd.carsettings.R;
import com.byd.carsettings.sound.model.SoundWave;
import com.byd.mvp.ui.adapter.BydAdapter;
import com.byd.mvp.ui.widget.TintRadioButton;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SoundWaveAdapter extends BydAdapter<SoundWave> {

    public class a extends BydAdapter<SoundWave>.BaseViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TintRadioButton f2742a;

        public a(View view) {
            super(view);
            this.f2742a = (TintRadioButton) view.findViewById(R.id.rbtn_item_sound_wave);
        }
    }

    public SoundWaveAdapter(List<SoundWave> list) {
        super(list);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter, b.b.m.f.c.a.a
    public void drag(int i, int i2) {
        b bVar = this.mDragItemCallback;
        if (bVar != null) {
            bVar.a(i, i2);
        }
        notifyItemRemoved(i);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public BydAdapter<SoundWave>.BaseViewHolder initViewHolder(View view) {
        return new a(view);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int loadLayoutId() {
        return R.layout.item_sound_wave;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        a aVar = (a) viewHolder;
        aVar.id = i;
        SoundWave soundWave = (SoundWave) this.data.get(i);
        aVar.f2742a.setText(soundWave.getName());
        aVar.f2742a.setChecked(soundWave.isChecked());
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter, android.view.View.OnClickListener
    public void onClick(View view) {
        e eVar = this.mItemClickListener;
        if (eVar != null) {
            eVar.onItemClicked(((BydAdapter.BaseViewHolder) view.getTag()).getId());
        }
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public void removeItemByPosition(int i) {
        b bVar = this.mDragItemCallback;
        if (bVar != null) {
            bVar.d(i);
        }
        notifyItemRemoved(i);
    }
}
