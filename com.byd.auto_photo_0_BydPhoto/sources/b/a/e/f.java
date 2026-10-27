package b.a.e;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.auto_photo.R;
import com.byd.bean.BurstshootItem;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class f extends RecyclerView.Adapter<b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected int f401a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected List<BurstshootItem> f402b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected Context f403c;
    protected int d;
    protected int e;
    protected int f;
    protected int g;
    private int i;
    private int j;
    private int k;
    private c m;
    private int h = -1;
    private Handler l = new a();

    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (f.this.h != -1) {
                f.this.h = -1;
                f.this.notifyDataSetChanged();
            }
        }
    }

    class b extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        ImageView f405a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ImageView f406b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        View f407c;
        View d;

        public b(View view) {
            super(view);
            this.f405a = (ImageView) view.findViewById(R.id.item_image);
            this.f406b = (ImageView) view.findViewById(R.id.item_check);
            this.f407c = view.findViewById(R.id.item_check_show_layer);
            this.d = view.findViewById(R.id.item_current_tips);
            this.f405a.setOnClickListener(this);
            this.f406b.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int layoutPosition = getLayoutPosition();
            int id = view.getId();
            if (id == R.id.item_check) {
                if (f.this.m != null) {
                    f.this.m.a(f.this.f401a, layoutPosition);
                }
            } else if (id == R.id.item_image && f.this.m != null) {
                f.this.m.b(f.this.f401a, layoutPosition);
            }
        }
    }

    public interface c {
        void a(int i, int i2);

        void b(int i, int i2);
    }

    public f(int i, Context context, int i2, int i3) {
        int dimensionPixelSize;
        this.f401a = i;
        this.f403c = context;
        if (i == 0) {
            this.f = 960;
            this.g = 540;
            dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.burst_big_recycler_height);
        } else {
            int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R.dimen.burst_small_recycler_height);
            this.g = dimensionPixelSize2;
            this.f = dimensionPixelSize2;
            dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.burst_small_recycler_height);
        }
        this.e = dimensionPixelSize;
        this.j = i2;
        this.k = i3;
    }

    public int d() {
        return this.e;
    }

    public int e() {
        return this.d;
    }

    public void f(b bVar, int i) {
        ViewGroup.LayoutParams layoutParams = bVar.itemView.getLayoutParams();
        if (layoutParams == null) {
            bVar.itemView.setLayoutParams(new RecyclerView.LayoutParams(this.d, this.e));
        } else {
            layoutParams.width = this.d;
            layoutParams.height = this.e;
        }
        BurstshootItem burstshootItem = this.f402b.get(i);
        b.a.h.l.i(this.f403c).c(burstshootItem.getPath()).k(true).i(R.drawable.icon_load_error).j(this.f, this.g).b(bVar.f405a);
        if (this.f401a == 0) {
            bVar.d.setVisibility(8);
            bVar.f406b.setVisibility(0);
            bVar.f406b.setSelected(burstshootItem.isSelect());
            int i2 = this.h;
            View view = bVar.itemView;
            if (i2 == i) {
                view.setBackgroundResource(R.drawable.burst_click_bg);
                return;
            } else {
                view.setBackground(null);
                return;
            }
        }
        bVar.f406b.setVisibility(8);
        if (burstshootItem.isSelect()) {
            bVar.f407c.setVisibility(0);
        } else {
            bVar.f407c.setVisibility(8);
        }
        int i3 = this.i;
        View view2 = bVar.d;
        if (i3 == i) {
            view2.setVisibility(0);
        } else {
            view2.setVisibility(4);
        }
    }

    public b g(ViewGroup viewGroup, int i) {
        return new b(LayoutInflater.from(this.f403c).inflate(R.layout.item_burstshoot, (ViewGroup) null));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<BurstshootItem> list = this.f402b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.f401a;
    }

    public void h(int i) {
        if (this.h != i) {
            this.h = i;
            this.i = i;
            this.l.removeCallbacksAndMessages(null);
            notifyDataSetChanged();
            this.l.sendEmptyMessageDelayed(1, 2500L);
        }
    }

    public void i(int i) {
        if (this.i != i) {
            this.i = i;
            notifyDataSetChanged();
        }
    }

    public void j(int i) {
        this.d = i;
        int i2 = (this.k * i) / this.j;
        this.e = i2;
        if (this.f401a == 1) {
            this.e = i2 + this.f403c.getResources().getDimensionPixelSize(R.dimen.burst_current_select_tips_size);
        }
        com.byd.util.w.c("BurstshootBaseAdapter type:" + this.f401a + " itemWidth:" + i + " " + this.e);
        notifyDataSetChanged();
    }

    public void k(List<BurstshootItem> list) {
        this.f402b = list;
        notifyDataSetChanged();
    }

    public void l(c cVar) {
        this.m = cVar;
    }
}
