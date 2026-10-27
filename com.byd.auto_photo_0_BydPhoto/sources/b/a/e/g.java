package b.a.e;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.auto_photo.R;
import com.byd.bean.FileBean;
import com.byd.bean.FileBeanInGroup;
import com.byd.view.XRoundCornerImageView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class g extends RecyclerView.Adapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f408a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f409b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<FileBeanInGroup> f410c;
    private int d;
    private int f;
    private c h;
    private SimpleDateFormat e = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
    private com.byd.taskmanager.b.b<Integer> g = new a();

    class a implements com.byd.taskmanager.b.b<Integer> {
        a() {
        }

        @Override // com.byd.taskmanager.b.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(String str, Integer num) {
            if (num.intValue() != -1) {
                g.this.notifyItemChanged(num.intValue());
            }
        }

        @Override // com.byd.taskmanager.b.b
        public void onCanceled(String str) {
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    private class b extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        XRoundCornerImageView f412a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        TextView f413b;

        public b(@NonNull View view) {
            super(view);
            this.f412a = (XRoundCornerImageView) view.findViewById(R.id.item_thumb);
            this.f413b = (TextView) view.findViewById(R.id.item_duration);
            view.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (g.this.h != null) {
                g.this.h.onClassifyItemClick((FileBeanInGroup) g.this.f410c.get(getAdapterPosition()), g.this.f408a);
            }
        }
    }

    public interface c {
        void onClassifyItemClick(FileBeanInGroup fileBeanInGroup, int i);
    }

    private static class d extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        TextView f415a;

        public d(@NonNull View view) {
            super(view);
            this.f415a = (TextView) view;
        }
    }

    public g(Context context, int i) {
        this.f409b = context;
        this.f408a = i;
        this.f = com.byd.util.e.l(context, R.attr.text_color);
    }

    public void d(int i, int i2, int i3) {
        this.d = (i - ((i2 - 1) * i3)) / i2;
        com.byd.util.w.c("classify list type : " + this.f408a + " itemSize:" + this.d);
    }

    public int e(String str) {
        List<FileBeanInGroup> list = this.f410c;
        if (list != null && list.size() != 0) {
            int size = this.f410c.size();
            for (int i = 0; i < size; i++) {
                FileBeanInGroup fileBeanInGroup = this.f410c.get(i);
                if (fileBeanInGroup.getType() == 2 && str.equals(fileBeanInGroup.getFileBean().getPath())) {
                    return i;
                }
            }
        }
        return 0;
    }

    public int f() {
        return this.f408a;
    }

    public void g(List<FileBeanInGroup> list) {
        this.f410c = list;
        notifyDataSetChanged();
        com.byd.util.w.c("classify adapter set data " + this.f408a + " " + getItemCount());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<FileBeanInGroup> list = this.f410c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        List<FileBeanInGroup> list = this.f410c;
        if (list == null || i >= list.size() || this.f410c.get(i) == null) {
            return 0;
        }
        return this.f410c.get(i).getType();
    }

    public void h(c cVar) {
        this.h = cVar;
    }

    public void i() {
        this.f = com.byd.util.e.l(this.f409b, R.attr.text_color);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        SimpleDateFormat simpleDateFormat;
        Resources resources;
        int i2;
        TextView textView;
        String strC;
        FileBeanInGroup fileBeanInGroup = this.f410c.get(i);
        if (fileBeanInGroup == null) {
            return;
        }
        if (fileBeanInGroup.getType() == 2) {
            FileBean fileBean = fileBeanInGroup.getFileBean();
            b bVar = (b) viewHolder;
            ViewGroup.LayoutParams layoutParams = bVar.itemView.getLayoutParams();
            if (layoutParams != null) {
                int i3 = this.d;
                layoutParams.width = i3;
                layoutParams.height = i3;
            } else {
                int i4 = this.d;
                bVar.itemView.setLayoutParams(new RecyclerView.LayoutParams(i4, i4));
            }
            boolean z = fileBean.getType() == 2;
            b.a.h.l.i(this.f409b).c(fileBean.getPath()).g(z ? R.drawable.icon_video_def : R.drawable.icon_load_error).f(true).j(500, 500).b(bVar.f412a);
            if (z) {
                if (fileBean.getVideoDuration() == -1) {
                    fileBean.setVideoDuration(0L);
                    com.byd.taskmanager.a.e().d(new b.a.k.f(fileBean, i, this.g));
                }
                if (fileBean.getVideoDuration() > 0) {
                    bVar.f413b.setVisibility(0);
                    textView = bVar.f413b;
                    strC = com.byd.util.o.c(fileBean.getVideoDuration());
                }
            }
            bVar.f413b.setVisibility(8);
            return;
        }
        d dVar = (d) viewHolder;
        if (this.f408a == 0) {
            simpleDateFormat = this.e;
            resources = this.f409b.getResources();
            i2 = R.string.data_format_month;
        } else {
            simpleDateFormat = this.e;
            resources = this.f409b.getResources();
            i2 = R.string.data_format_year;
        }
        simpleDateFormat.applyPattern(resources.getString(i2));
        dVar.f415a.setTextColor(this.f);
        textView = dVar.f415a;
        strC = this.e.format(new Date(fileBeanInGroup.getDate()));
        textView.setText(strC);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i != 1) {
            return new b(LayoutInflater.from(this.f409b).inflate(R.layout.item_filebean_classify, (ViewGroup) null));
        }
        TextView textView = new TextView(this.f409b);
        textView.setGravity(16);
        textView.setTextSize(0, this.f409b.getResources().getDimensionPixelSize(R.dimen.dp_13));
        textView.setLayoutParams(new RecyclerView.LayoutParams(-1, this.f409b.getResources().getDimensionPixelSize(R.dimen.dp_45)));
        return new d(textView);
    }
}
