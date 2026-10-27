package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import b.b.g.o.h.c;
import b.b.m.f.c.a.e;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.dilink.MusicStyleAdapter;
import com.byd.carsettings.sound.model.MusicStyle;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class EqualizerMusicStyleView extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public EqualizerView f3389a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RecyclerView f3390b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public MusicStyleAdapter f3391c;

    public class a implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ List f3392a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ MusicStyleAdapter f3393b;

        public a(List list, MusicStyleAdapter musicStyleAdapter) {
            this.f3392a = list;
            this.f3393b = musicStyleAdapter;
        }

        @Override // b.b.m.f.c.a.e
        public void onItemClicked(int i) {
            b.b.m.c.a.a("EqualizerMusicStyleView", "---onItemClicked---position = " + i);
            for (int i2 = 0; i2 < this.f3392a.size(); i2++) {
                if (i2 == i) {
                    ((MusicStyle) this.f3392a.get(i)).setChecked(true);
                } else {
                    ((MusicStyle) this.f3392a.get(i2)).setChecked(false);
                }
            }
            this.f3393b.notifyDataSetChanged();
        }
    }

    public class b implements b.b.g.q.h.c.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<EqualizerMusicStyleView> f3395a;

        public b(EqualizerMusicStyleView equalizerMusicStyleView) {
            this.f3395a = new WeakReference<>(equalizerMusicStyleView);
        }

        @Override // b.b.g.q.h.c.a
        public void a(c cVar) {
        }
    }

    public EqualizerMusicStyleView(@NonNull Context context) {
        super(context);
        b(context);
    }

    public final void a(Context context, AttributeSet attributeSet) {
    }

    public final void b(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.view_equalizer_music_style, (ViewGroup) null);
        EqualizerView equalizerView = (EqualizerView) viewInflate.findViewById(R.id.ev_equalizer_music_style);
        this.f3389a = equalizerView;
        equalizerView.setOnFreqBandChangedCallback(new b(this));
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.rcv_equalizer_music_style);
        this.f3390b = recyclerView;
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        addView(viewInflate);
    }

    public void setFreqBandValue(int i, int i2) {
        if (this.f3389a == null) {
        }
    }

    public void setFreqBandValueList(List<Integer> list) {
        if (this.f3389a == null) {
        }
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.f3390b.setLayoutManager(layoutManager);
    }

    public void setMusicStyleAdapter(MusicStyleAdapter musicStyleAdapter) {
        if (musicStyleAdapter == null) {
            return;
        }
        this.f3391c = musicStyleAdapter;
        List<MusicStyle> model = musicStyleAdapter.getModel();
        b.b.m.c.a.a("EqualizerMusicStyleView", "---setMusicStyleAdapter---adapter = " + musicStyleAdapter);
        this.f3390b.setAdapter(musicStyleAdapter);
        musicStyleAdapter.setOnItemClickedListener(new a(model, musicStyleAdapter));
        musicStyleAdapter.notifyDataSetChanged();
    }

    public EqualizerMusicStyleView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
        b(context);
    }

    public EqualizerMusicStyleView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
        b(context);
    }
}
