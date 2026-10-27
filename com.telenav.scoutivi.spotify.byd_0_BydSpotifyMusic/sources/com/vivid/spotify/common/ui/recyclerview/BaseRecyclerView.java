package com.vivid.spotify.common.ui.recyclerview;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;
import com.vivid.spotify.common.base.e;
import com.vivid.spotify.common.ui.c;
import com.vivid.spotify.r;
import com.vivid.spotify.u;
import com.vivid.spotify.y;
import h3.i;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class BaseRecyclerView extends RecyclerView {
    private final String M0;
    private w3.a N0;
    private int O0;
    private int P0;
    private boolean Q0;
    private boolean R0;
    protected boolean S0;
    private int T0;
    private int U0;
    private int V0;
    private CopyOnWriteArrayList<a> W0;

    public interface a {
        void onConfigurationChanged(Configuration configuration);
    }

    public BaseRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.M0 = "BaseRecyclerView";
        this.Q0 = true;
        this.R0 = false;
        this.S0 = false;
        this.T0 = -1;
        this.U0 = 0;
        this.V0 = 0;
        this.W0 = new CopyOnWriteArrayList<>();
        C1(attributeSet);
    }

    private void A1() {
        int itemDecorationCount = getItemDecorationCount();
        for (int i7 = 0; i7 < itemDecorationCount; i7++) {
            RecyclerView.n nVarL0 = l0(i7);
            if (nVarL0 instanceof c) {
                c cVar = (c) nVarL0;
                if (cVar.k() == 0) {
                    this.U0 += cVar.j();
                }
            }
        }
    }

    private void B1() {
        Context applicationContext = getContext().getApplicationContext();
        if (applicationContext instanceof e) {
            int iJ = (((((e) applicationContext).j() - getResources().getDimensionPixelSize(r.fragment_main_root_menu_width)) - getPaddingStart()) - getPaddingEnd()) - this.V0;
            int integer = getResources().getInteger(u.list_span_count);
            this.T0 = (iJ - (this.U0 * (integer - 1))) / integer;
        }
    }

    private void C1(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, y.VividRecyclerView);
            this.Q0 = typedArrayObtainStyledAttributes.getBoolean(y.VividRecyclerView_pagination_on, true);
            this.S0 = typedArrayObtainStyledAttributes.getBoolean(y.VividRecyclerView_limit_item_size, false);
            this.V0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(y.VividRecyclerView_view_offset_size, 0);
            typedArrayObtainStyledAttributes.recycle();
        }
        if (this.S0) {
            B1();
        }
    }

    private void D1(Configuration configuration) {
        Iterator<a> it = this.W0.iterator();
        while (it.hasNext()) {
            it.next().onConfigurationChanged(configuration);
        }
    }

    public static void F1(BaseRecyclerView baseRecyclerView, boolean z6) {
        baseRecyclerView.R0 = z6;
        baseRecyclerView.invalidate();
    }

    public void E1(a aVar) {
        this.W0.remove(aVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void X0(RecyclerView.n nVar) {
        super.X0(nVar);
        A1();
        B1();
    }

    public int getItemBlankAreaSize() {
        return this.P0;
    }

    public int getItemWidth() {
        return this.T0;
    }

    public int getOccludedHeight() {
        return this.O0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void i(RecyclerView.n nVar, int i7) {
        super.i(nVar, i7);
        A1();
        B1();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        i.b("BaseRecyclerView").g("onConfigurationChanged");
        if (this.S0) {
            B1();
        }
        D1(configuration);
    }

    @Override // android.view.View
    public void onDrawForeground(Canvas canvas) {
        if (this.R0) {
            canvas.save();
            canvas.scale(1.0f, ((getHeight() - getPaddingTop()) - getPaddingBottom()) / getHeight());
            canvas.translate(0.0f, getPaddingTop());
        }
        super.onDrawForeground(canvas);
        if (this.R0) {
            canvas.restore();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    protected void onSizeChanged(int i7, int i8, int i9, int i10) {
        w3.a aVar;
        super.onSizeChanged(i7, i8, i9, i10);
        if (!this.Q0 || (aVar = this.N0) == null) {
            return;
        }
        aVar.C();
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.g gVar) {
        super.setAdapter(gVar);
        if (!this.Q0 || gVar == null || (gVar instanceof x3.a)) {
            return;
        }
        w3.a aVar = this.N0;
        if (aVar == null) {
            this.N0 = new w3.a(this);
        } else {
            aVar.C();
        }
    }

    public void setItemBlankAreaSize(int i7) {
        this.P0 = i7;
    }

    public void setOccludedHeight(int i7) {
        this.O0 = i7;
    }

    public void z1(a aVar) {
        this.W0.addIfAbsent(aVar);
    }
}
