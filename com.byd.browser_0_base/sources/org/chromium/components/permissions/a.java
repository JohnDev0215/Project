package org.chromium.components.permissions;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import defpackage.j90;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: compiled from: DeviceItemAdapter.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a extends ArrayAdapter<j90> implements AdapterView.OnItemClickListener {
    public final LayoutInflater e;
    public final Resources f;
    public final boolean g;
    public final int h;
    public int i;
    public final HashMap j;
    public final HashMap k;
    public boolean l;
    public InterfaceC0139a m;

    /* JADX INFO: renamed from: org.chromium.components.permissions.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DeviceItemAdapter.java */
    public interface InterfaceC0139a {
    }

    /* JADX INFO: compiled from: DeviceItemAdapter.java */
    public static class b {
        public final TextView a;
        public final ImageView b;

        public b(View view) {
            this.b = (ImageView) view.findViewById(R$id.icon);
            this.a = (TextView) view.findViewById(R$id.description);
        }
    }

    public a(int i, Context context, boolean z) {
        super(context, i);
        this.i = -1;
        this.j = new HashMap();
        this.k = new HashMap();
        this.e = LayoutInflater.from(context);
        this.f = context.getResources();
        this.g = z;
        this.h = i;
    }

    public final void a(Drawable drawable, String str, String str2, String str3) {
        Drawable drawable2;
        HashMap map = this.k;
        j90 j90Var = (j90) map.get(str);
        if (j90Var == null) {
            j90 j90Var2 = new j90(drawable, str, str2, str3);
            map.put(str, j90Var2);
            c(j90Var2.b);
            add(j90Var2);
            return;
        }
        boolean zSameAs = false;
        if (TextUtils.equals(j90Var.a, str) && TextUtils.equals(j90Var.b, str2) && TextUtils.equals(j90Var.d, str3)) {
            if (drawable != null && (drawable2 = j90Var.c) != null) {
                Drawable drawableNewDrawable = drawable2.getConstantState().newDrawable();
                Drawable drawableNewDrawable2 = drawable.getConstantState().newDrawable();
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawableNewDrawable.getIntrinsicWidth(), drawableNewDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas();
                canvas.setBitmap(bitmapCreateBitmap);
                drawableNewDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                drawableNewDrawable.draw(canvas);
                Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(drawableNewDrawable2.getIntrinsicWidth(), drawableNewDrawable2.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas();
                canvas2.setBitmap(bitmapCreateBitmap2);
                drawableNewDrawable2.setBounds(0, 0, canvas2.getWidth(), canvas2.getHeight());
                drawableNewDrawable2.draw(canvas2);
                zSameAs = bitmapCreateBitmap.sameAs(bitmapCreateBitmap2);
            } else if (drawable == null && j90Var.c == null) {
                zSameAs = true;
            }
        }
        if (zSameAs) {
            return;
        }
        if (!TextUtils.equals(j90Var.b, str2)) {
            d(j90Var.b);
            j90Var.b = str2;
            c(str2);
        }
        if (!Objects.equals(drawable, j90Var.c)) {
            j90Var.c = drawable;
            j90Var.d = str3;
        }
        notifyDataSetChanged();
    }

    public final void c(String str) {
        HashMap map = this.j;
        map.put(str, Integer.valueOf((map.containsKey(str) ? ((Integer) map.get(str)).intValue() : 0) + 1));
    }

    @Override // android.widget.ArrayAdapter
    public final void clear() {
        this.k.clear();
        this.j.clear();
        e(-1);
        super.clear();
    }

    public final void d(String str) {
        HashMap map = this.j;
        if (map.containsKey(str)) {
            int iIntValue = ((Integer) map.get(str)).intValue();
            if (iIntValue == 1) {
                map.remove(str);
            } else {
                map.put(str, Integer.valueOf(iIntValue - 1));
            }
        }
    }

    public final void e(int i) {
        this.i = i;
        InterfaceC0139a interfaceC0139a = this.m;
        if (interfaceC0139a != null) {
            ((org.chromium.components.permissions.b) interfaceC0139a).i.setEnabled(i != -1);
        }
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = this.e.inflate(this.h, viewGroup, false);
            bVar = new b(view);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        bVar.a.setSelected(i == this.i);
        boolean z = this.g;
        TextView textView = bVar.a;
        textView.setEnabled(z);
        j90 item = getItem(i);
        String string = item.b;
        if (((Integer) this.j.get(string)).intValue() != 1) {
            string = this.f.getString(R$string.item_chooser_item_name_with_id, string, item.a);
        }
        textView.setText(string);
        ImageView imageView = bVar.b;
        if (imageView != null) {
            if (this.l) {
                j90 item2 = getItem(i);
                if (item2.c != null) {
                    imageView.setContentDescription(item2.d);
                    imageView.setImageDrawable(item2.c);
                    imageView.setVisibility(0);
                } else {
                    imageView.setVisibility(4);
                    imageView.setImageDrawable(null);
                    imageView.setContentDescription(null);
                }
                imageView.setSelected(i == this.i);
            } else {
                imageView.setVisibility(8);
            }
        }
        return view;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final int getViewTypeCount() {
        return 1;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public final boolean isEnabled(int i) {
        return this.g;
    }

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        this.l = false;
        Iterator it = this.k.values().iterator();
        while (it.hasNext()) {
            if (((j90) it.next()).c != null) {
                this.l = true;
            }
        }
        super.notifyDataSetChanged();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        e(i);
        notifyDataSetChanged();
    }
}
