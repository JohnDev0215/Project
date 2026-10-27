package b.a.e;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.auto_photo.R;
import com.byd.view.XRoundCornerImageView;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class w extends RecyclerView.Adapter<a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f447a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f448b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f449c;
    private List<String> d;
    private int e;
    private int f = -1;
    private b g;

    class a extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        XRoundCornerImageView f450a;

        public a(@NonNull View view) {
            super(view);
            this.f450a = (XRoundCornerImageView) view.findViewById(R.id.item_image);
            view.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int layoutPosition = getLayoutPosition();
            w.this.e(layoutPosition);
            if (w.this.g != null) {
                w.this.g.onItemClick(layoutPosition);
            }
        }
    }

    public interface b {
        void onItemClick(int i);
    }

    public w(Context context) {
        this.f447a = context;
        this.f448b = context.getResources().getDimensionPixelSize(R.dimen.dp_96);
        this.f449c = context.getResources().getDimensionPixelSize(R.dimen.dp_54);
    }

    public List<String> b() {
        return this.d;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NonNull a aVar, int i) {
        ViewGroup.LayoutParams layoutParams = aVar.itemView.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = this.f448b;
            layoutParams.height = this.f449c;
        } else {
            aVar.itemView.setLayoutParams(new RecyclerView.LayoutParams(this.f448b, this.f449c));
        }
        String strA = com.byd.util.m.a(this.d.get(i));
        boolean zD = !TextUtils.isEmpty(strA) ? com.byd.util.m.d(strA) : false;
        b.a.h.f fVarC = b.a.h.l.i(this.f447a).c(this.d.get(i));
        int i2 = this.f448b;
        fVarC.j(i2, i2).k(!zD).b(aVar.f450a);
        int i3 = this.e;
        XRoundCornerImageView xRoundCornerImageView = aVar.f450a;
        if (i3 == i) {
            xRoundCornerImageView.setBorderColor(this.f).setBorderWidth(4);
        } else {
            xRoundCornerImageView.setBorderColor(0).setBorderWidth(0);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new a(LayoutInflater.from(this.f447a).inflate(R.layout.item_thumb_indicator, (ViewGroup) null));
    }

    public void e(int i) {
        if (this.e != i) {
            this.e = i;
            notifyDataSetChanged();
        }
    }

    public void f(List<String> list) {
        this.d = list;
        notifyDataSetChanged();
    }

    public void g(b bVar) {
        this.g = bVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<String> list = this.d;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
