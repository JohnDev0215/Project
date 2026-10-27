package b.a.e;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.auto_photo.R;
import com.byd.bean.FileBean;
import com.byd.bean.FileBeanInGroup;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class r extends s<FileBeanInGroup> {
    private SimpleDateFormat r;
    private String s;

    static class a extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        TextView f438a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        TextView f439b;

        public a(View view) {
            super(view);
            this.f438a = (TextView) view.findViewById(R.id.item_group_name);
            TextView textView = (TextView) view.findViewById(R.id.item_loc_name);
            this.f439b = textView;
            textView.setVisibility(8);
        }
    }

    public r(Context context, int i, int i2) {
        super(context, i, i2);
        A(context);
    }

    private String B(long j) {
        if (com.byd.auto_photo.a.f1178a.intValue() < 50) {
            this.r.applyPattern(this.f441b.getResources().getString(R.string.data_format));
            return this.r.format(new Date(j));
        }
        long jD = D();
        long j2 = j - jD;
        com.byd.util.w.c("format group name : " + j + " st of today : " + jD + " offset : " + j2);
        if (j2 >= 0 && j2 < 86400000) {
            return this.f441b.getResources().getString(R.string.today);
        }
        if (j2 < 0) {
            long jAbs = Math.abs(j2);
            if (jAbs <= 86400000) {
                return this.f441b.getResources().getString(R.string.yesterday);
            }
            if (jAbs < 604800000) {
                this.r.applyPattern("E");
                return this.r.format(new Date(j));
            }
        }
        this.r.applyPattern(this.f441b.getResources().getString(R.string.data_format));
        return this.r.format(new Date(j));
    }

    private long D() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public void A(Context context) {
        String language = context.getResources().getConfiguration().locale.getLanguage();
        String str = this.s;
        if (str == null || !str.equals(language)) {
            this.s = language;
            com.byd.util.w.c("PhotoGroupListAdapter configDateFormat : " + this.s);
            this.r = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // b.a.e.t
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public FileBean h(FileBeanInGroup fileBeanInGroup) {
        return fileBeanInGroup.getFileBean();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        List<T> list = this.f440a;
        if (list == 0 || i >= list.size() || this.f440a.get(i) == null) {
            return 0;
        }
        return ((FileBeanInGroup) this.f440a.get(i)).getType();
    }

    @Override // b.a.e.t
    public void n() {
        A(this.f441b);
    }

    @Override // b.a.e.t, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        FileBeanInGroup fileBeanInGroup;
        TextView textView;
        String groupName;
        if (viewHolder == null || (fileBeanInGroup = (FileBeanInGroup) this.f440a.get(i)) == null) {
            return;
        }
        if (fileBeanInGroup.getType() == 2) {
            super.onBindViewHolder(viewHolder, i);
            return;
        }
        a aVar = (a) viewHolder;
        aVar.f438a.setTextColor(this.l);
        if (fileBeanInGroup.getGroupName() == null) {
            textView = aVar.f438a;
            groupName = B(fileBeanInGroup.getDate());
        } else {
            textView = aVar.f438a;
            groupName = fileBeanInGroup.getGroupName();
        }
        textView.setText(groupName);
        if (com.byd.auto_photo.a.f1178a.intValue() != 2) {
            aVar.itemView.findViewById(R.id.item_loc_line).setVisibility(8);
        }
    }

    @Override // b.a.e.t, androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return i == 2 ? super.onCreateViewHolder(viewGroup, i) : new a(LayoutInflater.from(this.f441b).inflate(R.layout.item_group_name, (ViewGroup) null));
    }

    @Override // b.a.e.t
    public FileBean r(String str) {
        List<T> list = this.f440a;
        if (list == 0 || list.size() <= 0) {
            return null;
        }
        int size = this.f440a.size();
        for (int i = 0; i < size; i++) {
            if (((FileBeanInGroup) this.f440a.get(i)).getType() == 2 && ((FileBeanInGroup) this.f440a.get(i)).getFileBean().getPath().equals(str)) {
                this.j = i;
                return ((FileBeanInGroup) this.f440a.get(i)).getFileBean();
            }
        }
        return null;
    }
}
