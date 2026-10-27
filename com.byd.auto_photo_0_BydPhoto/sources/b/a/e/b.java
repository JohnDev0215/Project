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
import com.byd.bean.AlbumItem;
import com.byd.view.XRoundCornerImageView;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class b extends RecyclerView.Adapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected List<AlbumItem> f379a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected Context f380b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected int f381c;
    protected int d;
    protected int e;
    protected int f;
    protected int g;
    protected boolean h;
    protected int j;
    protected int k;
    private int l;
    private String m;
    protected String o;
    protected int p;
    protected int q;
    protected int r;
    protected boolean s;
    protected boolean t;
    private c w;
    protected boolean n = true;
    protected int u = 8;
    private com.byd.taskmanager.b.b<Integer> v = new a();
    private List<AlbumItem> i = new LinkedList();

    class a implements com.byd.taskmanager.b.b<Integer> {
        a() {
        }

        @Override // com.byd.taskmanager.b.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(String str, Integer num) {
            if (num == null) {
                return;
            }
            b.this.notifyItemChanged(num.intValue());
        }

        @Override // com.byd.taskmanager.b.b
        public void onCanceled(String str) {
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    /* JADX INFO: renamed from: b.a.e.b$b, reason: collision with other inner class name */
    class ViewOnClickListenerC0013b extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        XRoundCornerImageView f383a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        TextView f384b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        TextView f385c;
        ImageView d;
        View e;
        View f;

        public ViewOnClickListenerC0013b(View view) {
            super(view);
            this.f383a = (XRoundCornerImageView) view.findViewById(R.id.item_icon);
            this.f384b = (TextView) view.findViewById(R.id.item_name);
            this.f385c = (TextView) view.findViewById(R.id.item_child_num);
            this.d = (ImageView) view.findViewById(R.id.item_selected);
            this.e = view.findViewById(R.id.item_no_enable);
            this.f = view.findViewById(R.id.item_footer);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.e.getVisibility() == 0 || b.this.w == null) {
                return;
            }
            int layoutPosition = getLayoutPosition();
            List<AlbumItem> list = b.this.f379a;
            if (list == null || layoutPosition < 0 || layoutPosition >= list.size()) {
                return;
            }
            com.byd.util.w.c("onAlbumItem Click : " + layoutPosition);
            b.this.w.onAlbumItemClick(b.this.f379a.get(layoutPosition), layoutPosition);
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (this.e.getVisibility() != 0 && b.this.w != null) {
                int layoutPosition = getLayoutPosition();
                List<AlbumItem> list = b.this.f379a;
                if (list == null || layoutPosition < 0 || layoutPosition >= list.size()) {
                    return false;
                }
                com.byd.util.w.c("onAlbumItemLongClick : " + layoutPosition);
                b.this.w.onAlbumItemLongClick(b.this.f379a.get(layoutPosition), layoutPosition);
            }
            return true;
        }
    }

    public interface c {
        void onAlbumItemClick(AlbumItem albumItem, int i);

        void onAlbumItemLongClick(AlbumItem albumItem, int i);
    }

    class d extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        TextView f386a;

        public d(View view) {
            super(view);
            this.f386a = (TextView) view.findViewById(R.id.item_group_title);
            b.this.r(this);
        }
    }

    public b(Context context, int i, int i2) {
        this.f380b = context;
        this.g = i;
        this.f = i2;
        this.o = com.byd.util.g.i(context, "/");
        int iL = com.byd.util.e.l(context, R.attr.text_color);
        this.p = iL;
        this.q = iL;
        this.r = R.drawable.icon_selected;
    }

    public void A(boolean z) {
        this.n = z;
    }

    public void B() {
        int size = this.i.size();
        for (int i = 0; i < size; i++) {
            this.i.get(i).setSelect(false);
        }
        this.i.clear();
        com.byd.util.w.c("album cancel select all : " + this.i.size());
        notifyDataSetChanged();
    }

    public abstract void b(int i, int i2);

    protected void c(ViewOnClickListenerC0013b viewOnClickListenerC0013b, AlbumItem albumItem) {
        viewOnClickListenerC0013b.itemView.setVisibility(this.h ? 4 : 0);
    }

    protected String d(AlbumItem albumItem) {
        String strD = com.byd.util.x.d(this.f380b, albumItem, this.o);
        if (!this.s || albumItem.getAlbumType() == 1 || albumItem.getUri() == null || com.byd.util.x.f3015b.equals(albumItem.getUri().toString()) || strD.length() <= this.u) {
            return strD;
        }
        return strD.substring(0, this.u) + "...";
    }

    public void e(AlbumItem albumItem, int i) {
        albumItem.setSelect(!albumItem.isSelect());
        notifyItemChanged(i);
        if (albumItem.isSelect()) {
            this.i.add(albumItem);
        } else {
            this.i.remove(albumItem);
        }
    }

    public int f() {
        return this.l;
    }

    public List<AlbumItem> g() {
        return this.f379a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<AlbumItem> list = this.f379a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        List<AlbumItem> list = this.f379a;
        if (list == null || list.size() == 0 || i >= this.f379a.size()) {
            return 0;
        }
        return this.f379a.get(i).getAlbumType();
    }

    protected int h() {
        return this.e;
    }

    public int i() {
        return this.d;
    }

    public List<AlbumItem> j() {
        return this.i;
    }

    public boolean k(String str) {
        List<AlbumItem> list = this.f379a;
        if (list == null) {
            return false;
        }
        int size = list.size() - 1;
        for (int i = 0; i < size; i++) {
            AlbumItem albumItem = this.f379a.get(i);
            if (albumItem.getAlbumType() == 2 && albumItem.getName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean l() {
        return this.i.size() == this.l;
    }

    public boolean m() {
        return this.h;
    }

    public void n(int i, AlbumItem albumItem) {
        if (this.f379a == null) {
            this.f379a = new LinkedList();
        }
        if (i >= this.f379a.size()) {
            this.f379a.add(albumItem);
            i = this.f379a.size() - 1;
        } else {
            this.f379a.add(i, albumItem);
        }
        if (albumItem.getAlbumType() != 4) {
            this.l++;
        }
        notifyItemInserted(i);
    }

    protected void o(ViewOnClickListenerC0013b viewOnClickListenerC0013b, int i) {
        if (this.n) {
            return;
        }
        ((LinearLayout.LayoutParams) viewOnClickListenerC0013b.f.getLayoutParams()).height = this.f380b.getResources().getDimensionPixelSize(R.dimen.album_name_height);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        TextView textView;
        String strValueOf;
        XRoundCornerImageView xRoundCornerImageView;
        ImageView.ScaleType scaleType;
        if (!(viewHolder instanceof ViewOnClickListenerC0013b)) {
            if (viewHolder instanceof d) {
                d dVar = (d) viewHolder;
                dVar.f386a.setText(this.f380b.getResources().getString(R.string.more_album));
                dVar.f386a.setTextColor(this.p);
                return;
            }
            return;
        }
        ViewOnClickListenerC0013b viewOnClickListenerC0013b = (ViewOnClickListenerC0013b) viewHolder;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) viewOnClickListenerC0013b.itemView.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RecyclerView.LayoutParams(this.f381c, this.d);
        } else {
            ((ViewGroup.MarginLayoutParams) layoutParams).width = this.f381c;
            ((ViewGroup.MarginLayoutParams) layoutParams).height = this.d;
        }
        viewOnClickListenerC0013b.itemView.setLayoutParams(layoutParams);
        int iH = h();
        if (this.e > 0) {
            ViewGroup.LayoutParams layoutParams2 = viewOnClickListenerC0013b.f383a.getLayoutParams();
            if (layoutParams2 != null) {
                layoutParams2.width = iH;
                layoutParams2.height = this.e;
            }
            ViewGroup.LayoutParams layoutParams3 = viewOnClickListenerC0013b.f.getLayoutParams();
            if (layoutParams3 != null) {
                layoutParams3.width = iH;
            }
        }
        AlbumItem albumItem = this.f379a.get(i);
        int num = albumItem.getNum();
        if (albumItem.getAlbumType() != 0 && num < 0) {
            com.byd.taskmanager.a.e().d(new b.a.k.b(this.f380b, albumItem, i, this.v));
        }
        viewOnClickListenerC0013b.f384b.setText(d(albumItem));
        viewOnClickListenerC0013b.f384b.setTextColor(this.p);
        if (albumItem.getNum() <= -1) {
            textView = viewOnClickListenerC0013b.f385c;
            strValueOf = "";
        } else if (this.t) {
            textView = viewOnClickListenerC0013b.f385c;
            strValueOf = "(" + num + ")";
        } else {
            textView = viewOnClickListenerC0013b.f385c;
            strValueOf = String.valueOf(num);
        }
        textView.setText(strValueOf);
        viewOnClickListenerC0013b.f385c.setTextColor(this.q);
        if (albumItem.getCoverPath() == null || num <= 0) {
            if (com.byd.auto_photo.a.f1178a.intValue() != 2) {
                xRoundCornerImageView = viewOnClickListenerC0013b.f383a;
                scaleType = ImageView.ScaleType.CENTER_CROP;
            } else {
                xRoundCornerImageView = viewOnClickListenerC0013b.f383a;
                scaleType = ImageView.ScaleType.FIT_CENTER;
            }
            xRoundCornerImageView.setScaleType(scaleType);
            viewOnClickListenerC0013b.f383a.setImageResource(albumItem.getIcon());
        } else {
            viewOnClickListenerC0013b.f383a.setScaleType(ImageView.ScaleType.CENTER_CROP);
            b.a.h.l.i(this.f380b).c(albumItem.getCoverPath()).j(500, 500).g(R.drawable.icon_def_folder).f(true).b(viewOnClickListenerC0013b.f383a);
        }
        if (!this.h || albumItem.getAlbumType() == 1 || albumItem.getAlbumType() == 0) {
            viewOnClickListenerC0013b.d.setVisibility(8);
        } else {
            viewOnClickListenerC0013b.d.setVisibility(0);
            p(viewOnClickListenerC0013b.d, albumItem.isSelect());
        }
        if (albumItem.getAlbumType() == 0) {
            c(viewOnClickListenerC0013b, albumItem);
        }
        if (this.m == null || albumItem.getUri() == null || !this.m.equals(albumItem.getUri().getPath())) {
            viewOnClickListenerC0013b.e.setVisibility(8);
        } else {
            viewOnClickListenerC0013b.e.setVisibility(0);
        }
        o(viewOnClickListenerC0013b, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 4) {
            return new d(LayoutInflater.from(this.f380b).inflate(R.layout.album_item_group_name, (ViewGroup) null));
        }
        ViewOnClickListenerC0013b viewOnClickListenerC0013b = new ViewOnClickListenerC0013b(LayoutInflater.from(this.f380b).inflate(R.layout.item_album, (ViewGroup) null));
        q(viewOnClickListenerC0013b);
        return viewOnClickListenerC0013b;
    }

    protected void p(ImageView imageView, boolean z) {
        imageView.setImageResource(z ? this.r : R.drawable.icon_no_selected);
    }

    protected void q(ViewOnClickListenerC0013b viewOnClickListenerC0013b) {
        if (this.n) {
            return;
        }
        viewOnClickListenerC0013b.f385c.setVisibility(8);
    }

    protected void r(d dVar) {
    }

    public abstract void s(Context context);

    public boolean t() {
        if (this.i.size() >= this.l) {
            return false;
        }
        int itemCount = getItemCount();
        for (int i = 0; i < itemCount; i++) {
            AlbumItem albumItem = g().get(i);
            int albumType = albumItem.getAlbumType();
            if (albumType != 1 && albumType != 4 && albumType != 0 && !albumItem.isSelect()) {
                albumItem.setSelect(true);
                this.i.add(albumItem);
            }
        }
        if (this.i.size() != this.l) {
            this.l = this.i.size();
        }
        com.byd.util.w.c("selectAll , size : " + this.i.size() + " canSelectSize:" + this.l);
        notifyDataSetChanged();
        return true;
    }

    public int u() {
        return this.i.size();
    }

    public void v(String str) {
        this.m = str;
    }

    public void w(List<AlbumItem> list) {
        List<AlbumItem> list2 = this.f379a;
        int i = 0;
        if (list2 == null) {
            this.f379a = new LinkedList();
        } else {
            int size = list2.size();
            this.f379a.clear();
            notifyItemRangeRemoved(0, size);
        }
        this.l = 0;
        if (list == null || list.size() == 0) {
            return;
        }
        this.f379a.addAll(list);
        notifyItemRangeInserted(0, list.size());
        com.byd.util.w.c("album adapter set datas : " + list.size());
        try {
            Iterator<AlbumItem> it = list.iterator();
            while (it.hasNext()) {
                int albumType = it.next().getAlbumType();
                if (albumType == 1) {
                    i++;
                } else if (albumType == 2 || albumType == 3) {
                    this.l++;
                }
            }
            com.byd.util.w.c("set datas :" + list.size() + ", sys album num:" + i + ", can select size:" + this.l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void x(int i) {
        this.g = i;
    }

    public void y(c cVar) {
        this.w = cVar;
    }

    public void z(boolean z) {
        int size;
        this.h = z;
        if (!z && (size = this.i.size()) > 0) {
            for (int i = 0; i < size; i++) {
                this.i.get(i).setSelect(false);
            }
            this.i.clear();
        }
        notifyDataSetChanged();
    }
}
