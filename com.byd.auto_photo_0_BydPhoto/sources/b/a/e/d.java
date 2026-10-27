package b.a.e;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.auto_photo.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class d extends RecyclerView.Adapter<b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected List<ResolveInfo> f395a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected Context f396b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected int f397c;
    protected int d;
    private PackageManager e;
    protected int f;
    protected int g;
    protected boolean h;
    protected boolean i;
    private a j;

    public interface a {
        void onShareItemClick(ResolveInfo resolveInfo, int i);
    }

    class b extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        ImageView f398a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        TextView f399b;

        public b(View view) {
            super(view);
            this.f398a = (ImageView) view.findViewById(R.id.item_icon);
            this.f399b = (TextView) view.findViewById(R.id.item_name);
            view.setOnClickListener(this);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f398a.getLayoutParams();
            if (layoutParams == null) {
                int i = d.this.g;
                layoutParams = new LinearLayout.LayoutParams(i, i);
            } else {
                int i2 = d.this.g;
                layoutParams.width = i2;
                layoutParams.height = i2;
            }
            this.f398a.setLayoutParams(layoutParams);
            if (d.this.i) {
                this.f399b.setLineSpacing(0.0f, 0.8f);
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (d.this.j != null) {
                int layoutPosition = getLayoutPosition();
                d.this.j.onShareItemClick(d.this.f395a.get(layoutPosition), layoutPosition);
            }
        }
    }

    public d(Context context, boolean z) {
        this.f396b = context;
        this.h = z;
        this.e = context.getPackageManager();
        this.i = context.getResources().getInteger(R.integer.share_item_text_max_line) > 1;
    }

    public int b() {
        return this.d;
    }

    public List<ResolveInfo> c() {
        return this.f395a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(b bVar, int i) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) bVar.itemView.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RecyclerView.LayoutParams(this.f397c, this.d);
        } else {
            ((ViewGroup.MarginLayoutParams) layoutParams).width = this.f397c;
            ((ViewGroup.MarginLayoutParams) layoutParams).height = this.d;
        }
        bVar.itemView.setLayoutParams(layoutParams);
        if (this.h && i == 0) {
            bVar.f399b.setText(R.string.share_qr_code);
            bVar.f398a.setImageDrawable(com.byd.util.e.p(this.f396b, R.attr.icon_share_qr_code));
        } else {
            ResolveInfo resolveInfo = this.f395a.get(i);
            bVar.f398a.setImageDrawable(resolveInfo.loadIcon(this.e));
            bVar.f399b.setText(resolveInfo.loadLabel(this.e));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public b onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new b(LayoutInflater.from(this.f396b).inflate(R.layout.item_share_layout, (ViewGroup) null));
    }

    public void f() {
        this.f396b = null;
        this.j = null;
        this.e = null;
    }

    public void g(int i, int i2, int i3, int i4) {
        int i5 = (i3 - (i2 * i)) / i;
        if (this.f397c != i5) {
            this.f = i;
            this.f397c = i5;
            if (i4 > 0) {
                this.g = i4;
            } else {
                this.g = i5;
            }
            this.d = this.g + this.f396b.getResources().getDimensionPixelSize(R.dimen.share_text_height) + this.f396b.getResources().getDimensionPixelSize(R.dimen.share_text_top_margin);
            notifyDataSetChanged();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<ResolveInfo> list = this.f395a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void h(List<ResolveInfo> list) {
        this.f395a = list;
        notifyDataSetChanged();
    }

    public void i(a aVar) {
        this.j = aVar;
    }
}
