package b.a.e;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.auto_photo.R;

/* JADX INFO: loaded from: classes.dex */
public class q extends RecyclerView.Adapter<b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int[] f434a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Drawable[] f435b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f436c = -1;
    private Context d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private a j;

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
            if (q.this.j != null) {
                q.this.j.onStyleItemClick(getLayoutPosition());
            }
        }
    }

    public q(Context context) {
        this.d = context;
    }

    public void b(int i, int i2, int i3) {
        this.h = i;
        this.i = i2;
        this.g = i3;
        if (getItemCount() == 0) {
            return;
        }
        int dimensionPixelSize = this.d.getResources().getDimensionPixelSize(R.dimen.image_edit_paint_style_size);
        this.f = dimensionPixelSize;
        this.e = dimensionPixelSize;
        com.byd.util.w.c("calcItemViewSize " + i + " " + i2 + " itemWidth:" + this.e + " itemHeight:" + this.f + " " + i3);
        notifyDataSetChanged();
    }

    public Drawable[] c() {
        return this.f435b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(b bVar, int i) {
        bVar.itemView.setLayoutParams(new ViewGroup.LayoutParams(this.e, this.f));
        ImageView imageView = (ImageView) bVar.itemView;
        imageView.setImageDrawable(this.f435b[i]);
        imageView.setSelected(i == this.f436c);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public b onCreateViewHolder(ViewGroup viewGroup, int i) {
        ImageView imageView = new ImageView(this.d);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, 80));
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        return new b(imageView);
    }

    public void f(int i) {
        this.f436c = i;
        notifyDataSetChanged();
    }

    public void g(int[] iArr) {
        int i;
        this.f434a = iArr;
        int i2 = this.h;
        if (i2 == 0 || (i = this.i) == 0) {
            notifyDataSetChanged();
        } else {
            b(i2, i, this.g);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int[] iArr = this.f434a;
        if (iArr == null) {
            return 0;
        }
        return iArr.length;
    }

    public void h(Drawable[] drawableArr) {
        this.f435b = drawableArr;
    }

    public void i(a aVar) {
        this.j = aVar;
    }
}
