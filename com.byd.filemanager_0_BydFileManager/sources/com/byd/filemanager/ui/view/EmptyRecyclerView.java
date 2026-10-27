package com.byd.filemanager.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.filemanager.R;
import com.byd.filemanager.e;
import com.byd.filemanager.f;
import com.byd.filemanager.k.b;

/* JADX INFO: loaded from: classes.dex */
public class EmptyRecyclerView extends RecyclerView {
    private View F0;
    private boolean G0;
    private boolean H0;
    private final RecyclerView.i I0;

    class a extends RecyclerView.i {
        a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void a() {
            EmptyRecyclerView.this.A1();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void d(int i, int i2) {
            EmptyRecyclerView.this.A1();
        }
    }

    public EmptyRecyclerView(Context context) {
        super(context);
        this.I0 = new a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A1() {
        if (this.F0 == null || getAdapter() == null) {
            return;
        }
        if (this.G0) {
            this.F0.setVisibility(8);
            return;
        }
        if (!(getAdapter().c() == 0)) {
            this.F0.setVisibility(8);
            setVisibility(0);
        } else {
            setVisibility(8);
            this.F0.setVisibility(0);
            B1();
        }
    }

    private void B1() {
        if (this.F0 == null || !this.H0) {
            return;
        }
        int iK = f.h().k();
        ImageView imageView = (ImageView) this.F0.findViewById(R.id.iv_icon_empty);
        TextView textView = (TextView) this.F0.findViewById(R.id.tv_desc_empty);
        b.b("setEmpty rootIndex = " + iK);
        int i = R.string.no_content;
        if (iK == 0) {
            imageView.setImageResource(getEmptyImage());
            textView.setText(getContext().getString(R.string.no_content));
            return;
        }
        if (iK == 1) {
            imageView.setImageResource(getEmptyImageSD());
            Context context = getContext();
            if (!e.f().s(1)) {
                i = R.string.empty_sdcard;
            }
            textView.setText(context.getString(i));
            return;
        }
        if (iK != 2) {
            return;
        }
        imageView.setImageResource(R.drawable.img_empty_usb);
        Context context2 = getContext();
        if (!e.f().s(2)) {
            i = R.string.empty_usb;
        }
        textView.setText(context2.getString(i));
    }

    private int getEmptyImage() {
        return f.h().v() ? R.drawable.img_empty : R.drawable.img_empty_denza;
    }

    private int getEmptyImageSD() {
        return f.h().v() ? R.drawable.img_empty_sd : R.drawable.img_empty_sd_denza;
    }

    @Override // android.view.View
    protected float getTopFadingEdgeStrength() {
        return 0.0f;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.g gVar) {
        RecyclerView.g adapter = getAdapter();
        if (adapter != null) {
            adapter.v(this.I0);
        }
        super.setAdapter(gVar);
        if (gVar != null) {
            gVar.t(this.I0);
        }
    }

    public void setEmptyView(View view) {
        this.F0 = view;
    }

    public void setLoading(boolean z) {
        this.G0 = z;
        A1();
    }

    public void setNeedUpdateEmptyImage(boolean z) {
        this.H0 = z;
    }

    public EmptyRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.I0 = new a();
    }

    public EmptyRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.I0 = new a();
    }
}
