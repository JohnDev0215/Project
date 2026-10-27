package b.a.e;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import b.a.e.c;
import com.byd.auto_photo.R;
import com.byd.view.RoundCornerContainer;

/* JADX INFO: loaded from: classes.dex */
public class h extends c {
    private int l;

    public h(Context context) {
        super(context);
    }

    public void A(int i, int i2, int i3) {
        int i4;
        int dimensionPixelSize = this.f389b.getResources().getDimensionPixelSize(R.dimen.image_edit_item_def_size);
        int itemCount = getItemCount();
        if (this.f == 1) {
            this.e = i2;
            if (itemCount < 3) {
                i4 = dimensionPixelSize * 2;
            } else {
                if (itemCount <= 6) {
                    int i5 = (i - (itemCount * i3)) / itemCount;
                    this.d = i5;
                    if (i5 < 30) {
                    }
                } else if (itemCount > 7) {
                    i4 = (dimensionPixelSize * 3) / 2;
                }
                this.d = dimensionPixelSize;
            }
            this.d = i4;
        } else {
            this.d = i;
            if (dimensionPixelSize * itemCount <= i2 || itemCount > 6) {
                this.e = dimensionPixelSize;
            } else {
                this.e = (i2 - (itemCount * i3)) / itemCount;
            }
        }
        com.byd.util.w.c("==total:" + (dimensionPixelSize * itemCount) + " width:" + i + " height:" + i2 + " itemHeight:" + this.e + " itemWidth:" + this.d + " itemSpacing:" + i3);
        notifyDataSetChanged();
    }

    @Override // b.a.e.c
    protected Drawable h(int i) {
        return com.byd.util.h.a(this.f389b.getDrawable(i), this.l);
    }

    @Override // b.a.e.c
    public void k() {
        Resources resources;
        int i;
        super.k();
        if ((this.f389b.getResources().getConfiguration().uiMode & 48) != 32) {
            resources = this.f389b.getResources();
            i = R.color.tool_elements_color_day;
        } else {
            resources = this.f389b.getResources();
            i = R.color.tool_elements_color_night;
        }
        this.l = resources.getColor(i);
    }

    @Override // b.a.e.c, androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: m */
    public c.a onCreateViewHolder(ViewGroup viewGroup, int i) {
        c.a aVarOnCreateViewHolder = super.onCreateViewHolder(viewGroup, i);
        if (this.h) {
            ((RoundCornerContainer) aVarOnCreateViewHolder.itemView).setRadius(this.f389b.getResources().getDimensionPixelSize(R.dimen.dp_4_5));
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) aVarOnCreateViewHolder.f391a.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.bottomMargin = 0;
            aVarOnCreateViewHolder.f391a.setLayoutParams(layoutParams);
            aVarOnCreateViewHolder.f391a.setBackgroundColor(-1728053248);
            aVarOnCreateViewHolder.f391a.setMinHeight(this.f389b.getResources().getDimensionPixelSize(R.dimen.dp_21));
            aVarOnCreateViewHolder.f391a.setTextSize(2, 13.5f);
        } else {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) aVarOnCreateViewHolder.f391a.getLayoutParams();
            layoutParams2.removeRule(15);
            layoutParams2.addRule(13);
            aVarOnCreateViewHolder.f391a.setMinWidth(this.f389b.getResources().getDimensionPixelSize(R.dimen.dp_36));
            aVarOnCreateViewHolder.f391a.setCompoundDrawablePadding(0);
            aVarOnCreateViewHolder.f391a.setGravity(1);
        }
        return aVarOnCreateViewHolder;
    }

    @Override // b.a.e.c
    protected void y(TextView textView, int i, boolean z, RelativeLayout.LayoutParams layoutParams, int i2) {
        layoutParams.removeRule(15);
        layoutParams.addRule(13);
        if (z) {
            textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, com.byd.util.h.a(h(i), i2), (Drawable) null, (Drawable) null);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, i, 0, 0);
        }
    }

    @Override // b.a.e.c
    protected void z(TextView textView, int i, boolean z, int i2) {
        y(textView, i, z, (RelativeLayout.LayoutParams) textView.getLayoutParams(), i2);
    }
}
