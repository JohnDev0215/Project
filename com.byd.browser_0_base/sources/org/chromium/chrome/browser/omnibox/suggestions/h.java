package org.chromium.chrome.browser.omnibox.suggestions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.components.omnibox.AutocompleteMatch;

/* JADX INFO: compiled from: SearchAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h extends RecyclerView.e<a> {
    public final Context h;
    public final ArrayList i;
    public b j;

    /* JADX INFO: compiled from: SearchAdapter.java */
    public class a extends RecyclerView.a0 {
        public final View A;
        public final TextView y;
        public final ImageView z;

        public a(View view) {
            super(view);
            this.y = (TextView) view.findViewById(R$id.tv_card);
            this.z = (ImageView) view.findViewById(R$id.iv_del);
            this.A = view.findViewById(R$id.search_rl);
        }
    }

    /* JADX INFO: compiled from: SearchAdapter.java */
    public interface b {
    }

    public h(Context context, ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        this.i = arrayList2;
        this.h = context;
        arrayList2.addAll(arrayList);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int k() {
        return this.i.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    @SuppressLint({"RecyclerView"})
    public final void t(RecyclerView.a0 a0Var, int i) {
        a aVar = (a) a0Var;
        aVar.y.setText(((AutocompleteMatch) this.i.get(i)).d);
        aVar.z.setOnClickListener(new f(this, i));
        aVar.A.setOnClickListener(new g(this, i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final RecyclerView.a0 v(int i, RecyclerView recyclerView) {
        return new a(LayoutInflater.from(this.h).inflate(R$layout.item_search, (ViewGroup) recyclerView, false));
    }
}
