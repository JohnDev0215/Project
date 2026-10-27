package b.a.e;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.view.PaintColorItemView;

/* JADX INFO: loaded from: classes.dex */
public class p extends RecyclerView.Adapter<b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int[] f430a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f431b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f432c;
    private int d;
    private int e;
    private a f;

    public interface a {
        void onStyleItemClick(int i);
    }

    class b extends RecyclerView.ViewHolder implements View.OnClickListener {
        public b(View view) {
            super(view);
            view.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (p.this.f != null) {
                p.this.f.onStyleItemClick(getLayoutPosition());
            }
        }
    }

    public p(Context context) {
        this.f432c = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(b bVar, int i) {
        ViewGroup.LayoutParams layoutParams = bVar.itemView.getLayoutParams();
        if (layoutParams == null) {
            bVar.itemView.setLayoutParams(new RecyclerView.LayoutParams(this.d, this.e));
        } else {
            layoutParams.width = this.d;
            layoutParams.height = this.e;
        }
        PaintColorItemView paintColorItemView = (PaintColorItemView) bVar.itemView;
        paintColorItemView.setColor(this.f430a[i]);
        if (this.f430a[i] == -1) {
            paintColorItemView.setBorderColor(-7829368);
        }
        if (i == this.f431b) {
            paintColorItemView.setBorderSize(4);
            paintColorItemView.setPaintSize(60.0f);
        } else {
            paintColorItemView.setPaintSize(40.0f);
            paintColorItemView.setBorderSize(2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public b onCreateViewHolder(ViewGroup viewGroup, int i) {
        PaintColorItemView paintColorItemView = new PaintColorItemView(this.f432c);
        paintColorItemView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        paintColorItemView.setScaleType(ImageView.ScaleType.CENTER);
        return new b(paintColorItemView);
    }

    public void d(int i) {
        this.f431b = i;
        notifyDataSetChanged();
    }

    public void e(int[] iArr) {
        this.f430a = iArr;
    }

    public p f(int i) {
        this.e = i;
        return this;
    }

    public p g(int i) {
        this.d = i;
        return this;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int[] iArr = this.f430a;
        if (iArr == null) {
            return 0;
        }
        return iArr.length;
    }

    public void h(a aVar) {
        this.f = aVar;
    }
}
