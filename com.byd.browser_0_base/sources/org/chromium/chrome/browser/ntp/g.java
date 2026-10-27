package org.chromium.chrome.browser.ntp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.im;
import defpackage.vj1;
import java.util.ArrayList;
import java.util.List;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;

/* JADX INFO: compiled from: MyAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g extends RecyclerView.e<a> {
    public final Context h;
    public final ArrayList i;
    public b j;

    /* JADX INFO: compiled from: MyAdapter.java */
    public class a extends RecyclerView.a0 {
        public final ImageView A;
        public final ImageView B;
        public final RelativeLayout C;
        public final ImageView y;
        public final TextView z;

        public a(View view) {
            super(view);
            this.z = (TextView) view.findViewById(R$id.tv_card);
            this.A = (ImageView) view.findViewById(R$id.img);
            this.B = (ImageView) view.findViewById(R$id.add_img);
            this.y = (ImageView) view.findViewById(R$id.web_img);
            this.C = (RelativeLayout) view.findViewById(R$id.card_rl);
        }
    }

    /* JADX INFO: compiled from: MyAdapter.java */
    public interface b {
    }

    public g(Context context, ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        this.i = arrayList2;
        this.h = context;
        arrayList2.addAll(arrayList);
    }

    public final void D(List<im> list) {
        ArrayList arrayList = this.i;
        arrayList.clear();
        arrayList.addAll(list);
        Log.e("MyAdapter", "refresh" + list.size());
        o();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int k() {
        return this.i.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    @SuppressLint({"RecyclerView"})
    public final void t(RecyclerView.a0 a0Var, int i) {
        a aVar = (a) a0Var;
        Context context = this.h;
        int i2 = context.getResources().getConfiguration().orientation;
        RelativeLayout relativeLayout = aVar.C;
        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) relativeLayout.getLayoutParams();
        vj1.h.getClass();
        if (vj1.o((Activity) context) || i2 == 1 || i2 == 0) {
            Resources resources = context.getResources();
            int i3 = R$dimen.tab_page_item_margin_portrait;
            layoutParams.setMarginStart(resources.getDimensionPixelSize(i3));
            layoutParams.setMarginEnd(context.getResources().getDimensionPixelSize(i3));
        } else {
            Resources resources2 = context.getResources();
            int i4 = R$dimen.tab_page_item_margin_landscape;
            layoutParams.setMarginStart(resources2.getDimensionPixelSize(i4));
            layoutParams.setMarginEnd(context.getResources().getDimensionPixelSize(i4));
        }
        ArrayList arrayList = this.i;
        aVar.z.setText(((im) arrayList.get(i)).d);
        boolean zEquals = ((im) arrayList.get(i)).d.equals(context.getString(R$string.card_add));
        ImageView imageView = aVar.B;
        ImageView imageView2 = aVar.y;
        ImageView imageView3 = aVar.A;
        if (zEquals) {
            imageView3.setVisibility(8);
            imageView2.setVisibility(8);
            imageView.setVisibility(0);
        } else if (((im) arrayList.get(i)).b == -1) {
            imageView2.setVisibility(0);
            imageView3.setVisibility(8);
            imageView.setVisibility(8);
            imageView2.setImageBitmap(((im) arrayList.get(i)).a);
        } else {
            imageView2.setVisibility(8);
            imageView3.setVisibility(0);
            imageView.setVisibility(8);
            imageView3.setImageBitmap(((im) arrayList.get(i)).a);
        }
        if (this.j != null) {
            relativeLayout.setOnLongClickListener(new e(this, i));
            relativeLayout.setOnClickListener(new f(this, i));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final RecyclerView.a0 v(int i, RecyclerView recyclerView) {
        return new a(LayoutInflater.from(this.h).inflate(R$layout.item_card, (ViewGroup) recyclerView, false));
    }
}
