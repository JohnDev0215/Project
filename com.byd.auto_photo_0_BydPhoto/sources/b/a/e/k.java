package b.a.e;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.auto_photo.R;
import com.byd.bean.FileBean;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class k extends RecyclerView.Adapter<a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<FileBean> f416a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected Context f417b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f418c;
    private b d;

    class a extends RecyclerView.ViewHolder implements View.OnClickListener {
        public a(View view) {
            super(view);
            view.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (k.this.d != null) {
                k.this.d.a((FileBean) k.this.f416a.get(getLayoutPosition()));
            }
        }
    }

    public interface b {
        void a(FileBean fileBean);
    }

    public k(Context context) {
        this.f417b = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(a aVar, int i) {
        FileBean fileBean = this.f416a.get(i);
        ImageView imageView = (ImageView) aVar.itemView;
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (layoutParams == null) {
            int i2 = this.f418c;
            imageView.setLayoutParams(new ViewGroup.LayoutParams(i2, i2));
        } else {
            int i3 = this.f418c;
            layoutParams.width = i3;
            layoutParams.height = i3;
        }
        if (fileBean.getThumbPath() != null) {
            imageView.setImageURI(Uri.parse(fileBean.getThumbPath()));
        } else {
            b.a.h.l.i(this.f417b).c(fileBean.getPath()).i(R.drawable.icon_load_error).g(R.drawable.icon_load_error).j(500, 500).f(true).b(imageView);
        }
    }

    public void d(int i) {
        this.f418c = i;
    }

    public void e(List<FileBean> list) {
        this.f416a = list;
        notifyDataSetChanged();
    }

    public void f(b bVar) {
        this.d = bVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<FileBean> list = this.f416a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
