package b.a.e;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.auto_photo.R;
import com.byd.bean.FileBean;
import com.byd.view.ViewMotionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class t<T> extends RecyclerView.Adapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected List<T> f440a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected Context f441b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f442c;
    private int d;
    private int e;
    private int f;
    private int g;
    private boolean h;
    protected boolean i;
    protected int l;
    private int o;
    protected c p;
    protected int j = -1;
    private com.byd.taskmanager.b.b<Integer> n = new a();
    private List<FileBean> k = new LinkedList();
    protected int m = 0;

    class a implements com.byd.taskmanager.b.b<Integer> {
        a() {
        }

        @Override // com.byd.taskmanager.b.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(String str, Integer num) {
            if (num.intValue() != -1) {
                t.this.notifyItemChanged(num.intValue());
            }
        }

        @Override // com.byd.taskmanager.b.b
        public void onCanceled(String str) {
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    public class b extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, ViewMotionListener.OnDoubleTapListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        ImageView f444a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ImageView f445b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ImageView f446c;
        TextView d;
        TextView e;
        TextView f;
        ImageView g;
        View h;
        int i;

        public b(View view) {
            super(view);
            this.h = view.findViewById(R.id.item_media_content);
            this.f444a = (ImageView) view.findViewById(R.id.item_icon);
            this.f445b = (ImageView) view.findViewById(R.id.item_play_tips);
            this.f446c = (ImageView) view.findViewById(R.id.item_selected);
            this.d = (TextView) view.findViewById(R.id.item_burstshoot_tips);
            this.f = (TextView) view.findViewById(R.id.item_name);
            this.e = (TextView) view.findViewById(R.id.item_video_duration);
            this.g = (ImageView) view.findViewById(R.id.item_file_type);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            view.setOnTouchListener(new ViewMotionListener(this));
            t.this.m(this);
        }

        @Override // com.byd.view.ViewMotionListener.OnDoubleTapListener
        public void onActionDown(View view) {
            t.this.o = Integer.parseInt(view.getTag().toString());
            com.byd.util.w.c("action down : " + t.this.o);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            t tVar = t.this;
            if (tVar.p == null || tVar.f440a == null || tVar.o != this.i) {
                return;
            }
            com.byd.util.w.c("FileBean -> click : " + this.i + " " + this.itemView.getTag());
            int i = this.i;
            if (i < 0 || i >= t.this.f440a.size()) {
                return;
            }
            t tVar2 = t.this;
            t.this.p.onItemClick(tVar2.h(tVar2.f440a.get(this.i)), this.i, view);
        }

        @Override // com.byd.view.ViewMotionListener.OnDoubleTapListener
        public void onDoubleTap(View view) {
            if (t.this.p != null) {
                com.byd.util.w.c("FileBean -> double click : " + this.i);
                int i = this.i;
                if (i < 0 || i >= t.this.f440a.size()) {
                    return;
                }
                t tVar = t.this;
                t.this.p.onItemDoubleClick(tVar.h(tVar.f440a.get(this.i)), this.i, view);
            }
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (t.this.p == null) {
                return true;
            }
            com.byd.util.w.c("FileBean -> long click : " + this.i);
            int i = this.i;
            if (i < 0 || i >= t.this.f440a.size()) {
                return true;
            }
            t tVar = t.this;
            t.this.p.onItemLongClick(tVar.h(tVar.f440a.get(this.i)), this.i, view);
            return true;
        }
    }

    public interface c {
        void onItemClick(FileBean fileBean, int i, View view);

        void onItemDoubleClick(FileBean fileBean, int i, View view);

        void onItemLongClick(FileBean fileBean, int i, View view);
    }

    public t(Context context, int i, int i2) {
        this.f441b = context;
        this.d = i;
        this.f442c = i2;
        this.l = com.byd.util.e.l(context, R.attr.text_color);
        this.g = context.getResources().getDimensionPixelSize(R.dimen.item_media_name_height);
    }

    public void c(int i, int i2) {
        int i3 = this.d;
        int i4 = (i - (this.f442c * i3)) / i3;
        if (this.e != i4) {
            this.e = i4;
            this.f = i2;
            if (i2 == 0) {
                this.f = i4;
            }
            if (getItemCount() != 0) {
                notifyDataSetChanged();
            }
        }
        com.byd.util.w.c("PhotoListBaseAdapter calcItemSize : " + i + " " + this.e + " " + i2 + " " + i4);
    }

    public void d() {
        int size = this.k.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.k.get(i).setSelect(false);
            }
        }
        this.k.clear();
        notifyDataSetChanged();
    }

    public int e(FileBean fileBean, int i) {
        fileBean.setSelect(!fileBean.isSelect());
        notifyItemChanged(i);
        if (fileBean.isSelect()) {
            this.k.add(fileBean);
        } else {
            this.k.remove(fileBean);
        }
        return this.k.size();
    }

    public int f() {
        return this.j;
    }

    public List<T> g() {
        return this.f440a;
    }

    public T getItem(int i) {
        return this.f440a.get(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<T> list = this.f440a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    protected abstract FileBean h(T t);

    public List<FileBean> i() {
        return this.k;
    }

    public boolean j() {
        return this.i;
    }

    public void k(String str) {
        List<T> list = this.f440a;
        if (list == null) {
            return;
        }
        int size = list.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            }
            FileBean fileBeanH = h(this.f440a.get(i));
            if (fileBeanH != null && str.equals(fileBeanH.getPath())) {
                fileBeanH.setCollected(com.byd.util.f.k(this.f441b).o(fileBeanH.getType() == 3 ? new File(str).getParent() : str) ? 1 : 2);
            } else {
                i++;
            }
        }
        com.byd.util.w.c("notifyItem : " + str + " " + i);
        if (i > -1) {
            notifyItemChanged(i);
        }
    }

    protected void l(b bVar, int i) {
    }

    protected abstract void m(t<T>.b bVar);

    public void n() {
    }

    protected abstract void o(t<T>.b bVar, FileBean fileBean);

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        TextView textView;
        TextView textView2;
        T t = this.f440a.get(i);
        t<T>.b bVar = (b) viewHolder;
        bVar.i = i;
        bVar.itemView.setTag(Integer.valueOf(i));
        ViewGroup.LayoutParams layoutParams = bVar.itemView.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RecyclerView.LayoutParams(this.e, this.f);
        } else {
            layoutParams.width = this.e;
            layoutParams.height = this.f;
        }
        bVar.itemView.setLayoutParams(layoutParams);
        FileBean fileBeanH = h(t);
        bVar.f444a.setScaleType(ImageView.ScaleType.CENTER_CROP);
        int i2 = fileBeanH.getType() == 2 ? R.drawable.icon_video_def : R.drawable.icon_load_error;
        b.a.h.l.i(this.f441b).c(fileBeanH.getPath()).g(i2).i(i2).f(true).j(500, 500).b(bVar.f444a);
        if (fileBeanH.getCollected() == 0) {
            fileBeanH.setCollected(2);
            com.byd.taskmanager.a.e().d(new b.a.k.l(this.f441b, fileBeanH, i, this.n));
        }
        if (this.h) {
            ViewGroup.LayoutParams layoutParams2 = bVar.h.getLayoutParams();
            if (layoutParams2 == null) {
                layoutParams2 = new LinearLayout.LayoutParams(this.e, this.f - this.g);
            }
            layoutParams2.height = this.f - this.g;
            bVar.h.setLayoutParams(layoutParams2);
            bVar.f.setVisibility(0);
            String name = fileBeanH.getName();
            if (!fileBeanH.isDvrFile() || name.length() < 19) {
                textView2 = bVar.f;
            } else {
                textView2 = bVar.f;
                name = name.substring(11, 19);
            }
            textView2.setText(name);
        } else {
            bVar.f.setVisibility(8);
        }
        int type = fileBeanH.getType();
        if (type == 2) {
            bVar.f445b.setVisibility(this.m);
            bVar.d.setVisibility(8);
            bVar.f445b.setImageResource(R.drawable.icon_video_grid);
            if (fileBeanH.getVideoDuration() == -1) {
                fileBeanH.setVideoDuration(0L);
                com.byd.taskmanager.a.e().d(new b.a.k.f(fileBeanH, i, this.n));
            }
            if (fileBeanH.getVideoDuration() > 0) {
                bVar.e.setVisibility(0);
                bVar.e.setText(com.byd.util.o.b(fileBeanH.getVideoDuration()));
            } else {
                textView = bVar.e;
                textView.setVisibility(8);
            }
        } else if (type != 3) {
            bVar.f445b.setVisibility(8);
            bVar.e.setVisibility(8);
            textView = bVar.d;
            textView.setVisibility(8);
        } else {
            bVar.f445b.setVisibility(8);
            bVar.e.setVisibility(8);
            bVar.d.setVisibility(0);
        }
        o(bVar, fileBeanH);
        l(bVar, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new b(LayoutInflater.from(this.f441b).inflate(R.layout.item_media_grid, (ViewGroup) null));
    }

    public int p(List<String> list) {
        boolean z;
        int size = list.size();
        int i = 0;
        int i2 = 0;
        while (i < this.f440a.size()) {
            FileBean fileBeanH = h(this.f440a.get(i));
            if (fileBeanH != null) {
                int size2 = list.size();
                int i3 = 0;
                while (true) {
                    z = true;
                    if (i3 >= size2) {
                        z = false;
                        break;
                    }
                    String str = list.get(i3);
                    if (str == null || !fileBeanH.getPath().equals(str)) {
                        i3++;
                    } else {
                        this.f440a.remove(i);
                        list.remove(i3);
                        i2++;
                        if (i > 0) {
                            int i4 = i - 1;
                            if (h(this.f440a.get(i4)) == null) {
                                if (i >= this.f440a.size()) {
                                    this.f440a.remove(i4);
                                } else if (h(this.f440a.get(i)) == null) {
                                    this.f440a.remove(i4);
                                    i--;
                                }
                            }
                        }
                    }
                }
                if (list.size() == 0) {
                    break;
                }
            } else {
                z = false;
            }
            if (!z) {
                i++;
            }
        }
        com.byd.util.w.c("需要删除" + size + ",已删除" + i2);
        notifyDataSetChanged();
        return i2;
    }

    public void q() {
        int itemCount = getItemCount();
        com.byd.util.w.c("size:" + itemCount);
        for (int i = 0; i < itemCount; i++) {
            FileBean fileBeanH = h(this.f440a.get(i));
            if (fileBeanH != null && (!fileBeanH.isSelect() || !this.k.contains(fileBeanH))) {
                fileBeanH.setSelect(true);
                this.k.add(fileBeanH);
            }
        }
        com.byd.util.w.c("select size:" + this.k.size());
        notifyDataSetChanged();
    }

    public FileBean r(String str) {
        return null;
    }

    public int s() {
        List<FileBean> list = this.k;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void t(List<T> list) {
        String str;
        List<T> list2 = this.f440a;
        if (list2 == null) {
            this.f440a = new LinkedList();
        } else {
            int size = list2.size();
            this.f440a.clear();
            notifyItemRangeRemoved(0, size);
        }
        if (list == null || list.size() == 0) {
            str = "data size is 0";
        } else {
            this.f440a.addAll(list);
            notifyItemRangeInserted(0, list.size());
            str = "show data : " + getItemCount();
        }
        com.byd.util.w.c(str);
    }

    public void u(int i) {
        this.d = i;
    }

    public void v(c cVar) {
        this.p = cVar;
    }

    public void w(boolean z) {
        this.i = z;
        if (!z) {
            d();
        }
        notifyDataSetChanged();
    }

    public void x(boolean z) {
        this.h = z;
    }

    public void y(Context context) {
        this.f441b = context;
        this.l = com.byd.util.e.l(context, R.attr.text_color);
        notifyDataSetChanged();
    }
}
