package b.a.e;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import b.a.e.f;
import com.byd.auto_photo.R;
import com.byd.view.XRoundCornerImageView;

/* JADX INFO: loaded from: classes.dex */
public class e extends f {
    public e(int i, Context context, int i2, int i3) {
        super(i, context, i2, i3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(f.b bVar, int i) {
        bVar.itemView.setBackgroundResource(0);
        super.onBindViewHolder(bVar, i);
        if (this.f401a == 1) {
            if (!this.f402b.get(i).isSelect()) {
                bVar.f406b.setVisibility(8);
            } else {
                bVar.f406b.setSelected(true);
                bVar.f406b.setVisibility(0);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public f.b onCreateViewHolder(ViewGroup viewGroup, int i) {
        f.b bVarOnCreateViewHolder = super.onCreateViewHolder(viewGroup, i);
        bVarOnCreateViewHolder.itemView.setBackgroundColor(0);
        if (this.f401a == 1) {
            bVarOnCreateViewHolder.f407c.setBackgroundResource(R.drawable.burst_small_item_border);
            ((ImageView) bVarOnCreateViewHolder.f407c).setImageResource(0);
            ((XRoundCornerImageView) bVarOnCreateViewHolder.f405a).setRadius(this.f403c.getResources().getDimensionPixelSize(R.dimen.dp_4_5));
        }
        return bVarOnCreateViewHolder;
    }
}
