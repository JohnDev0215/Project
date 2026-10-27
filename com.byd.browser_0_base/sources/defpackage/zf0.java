package defpackage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import androidx.appcompat.content.res.AppCompatResources;
import java.util.List;
import org.chromium.chrome.R$dimen;

/* JADX INFO: compiled from: EditorIconsField.java */
/* JADX INFO: loaded from: classes.dex */
public final class zf0 extends BaseAdapter {
    public final Context e;
    public final List<Integer> f = null;
    public final List<Integer> g = null;
    public final int h;
    public final int i;
    public final int j;

    public zf0(Activity activity) {
        this.e = activity;
        this.h = activity.getResources().getDimensionPixelSize(R$dimen.editable_option_section_logo_width);
        this.i = activity.getResources().getDimensionPixelSize(R$dimen.editable_option_section_logo_horizontal_padding);
        this.j = activity.getResources().getDimensionPixelSize(R$dimen.editable_option_section_logo_vertical_padding);
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return this.f.size();
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        return this.f.get(i);
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = (ImageView) view;
        Context context = this.e;
        if (imageView == null) {
            imageView = new ImageView(context);
        }
        imageView.setImageDrawable(AppCompatResources.a(context, this.f.get(i).intValue()));
        imageView.setContentDescription(context.getString(this.g.get(i).intValue()));
        imageView.setAdjustViewBounds(true);
        int i2 = this.h;
        imageView.setMaxWidth(i2);
        imageView.setMaxHeight(i2);
        int i3 = this.i;
        int i4 = this.j;
        imageView.setPadding(i3, i4, i3, i4);
        return imageView;
    }
}
