package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;

/* JADX INFO: compiled from: PasswordGenerationPopupAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jx1 extends BaseAdapter {
    public final Context e;
    public final String f;

    public jx1(Context context, String str) {
        this.e = context;
        this.f = str;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public final boolean areAllItemsEnabled() {
        return false;
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return 1;
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return 0L;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final int getItemViewType(int i) {
        return 0;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (view != null) {
            return view;
        }
        View viewInflate = LayoutInflater.from(this.e).inflate(R$layout.password_generation_popup_explanation, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R$id.password_generation_explanation)).setText(this.f);
        return viewInflate;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final int getViewTypeCount() {
        return 1;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public final boolean isEnabled(int i) {
        return false;
    }
}
