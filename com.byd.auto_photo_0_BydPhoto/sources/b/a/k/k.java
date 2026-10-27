package b.a.k;

import android.content.Context;
import android.content.res.Resources;
import com.byd.auto_photo.R;
import com.byd.bean.FileBean;
import com.byd.bean.ImageSelectFolderItem;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class k extends com.byd.taskmanager.b.a<Boolean> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private i f564c;
    private boolean d;
    private Context e;
    private String f;
    private s g = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<FileBean> f562a = new LinkedList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Map<String, List<FileBean>> f563b = new HashMap();

    class a implements s {
        a() {
        }

        @Override // b.a.k.s
        public void a(FileBean fileBean) {
            k.this.f562a.add(fileBean);
            if (fileBean.getPath().startsWith(k.this.f)) {
                return;
            }
            String parent = new File(fileBean.getPath()).getParent();
            if (k.this.f563b.containsKey(parent)) {
                ((List) k.this.f563b.get(parent)).add(fileBean);
                return;
            }
            LinkedList linkedList = new LinkedList();
            linkedList.add(fileBean);
            k.this.f563b.put(parent, linkedList);
        }
    }

    public k(Context context, boolean z) {
        this.d = z;
        this.f564c = new i(context, false, z ? 1 : 2);
        this.e = context;
        this.f = com.byd.util.g.f(context);
    }

    public void e() {
        this.f562a.clear();
        this.f563b.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.taskmanager.b.a
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public Boolean doTask() {
        this.f562a.clear();
        this.f563b.clear();
        try {
            this.f564c.c(this.g);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.TRUE;
    }

    public List<FileBean> g() {
        return this.f562a;
    }

    public List<FileBean> h(String str) {
        return this.f563b.get(str);
    }

    public List<ImageSelectFolderItem> i() {
        Resources resources;
        int i;
        Map<String, List<FileBean>> map = this.f563b;
        if (map == null || map.size() == 0) {
            return null;
        }
        int size = this.f563b.size();
        ArrayList arrayList = new ArrayList(size + 1);
        if (this.d) {
            resources = this.e.getResources();
            i = R.string.all_images;
        } else {
            resources = this.e.getResources();
            i = R.string.videos;
        }
        arrayList.add(new ImageSelectFolderItem(resources.getString(i), null, this.f562a.size(), this.f562a.get(0)));
        if (!this.f563b.isEmpty()) {
            String[] strArr = new String[size];
            this.f563b.keySet().toArray(strArr);
            for (int i2 = 0; i2 < size; i2++) {
                File file = new File(strArr[i2]);
                arrayList.add(new ImageSelectFolderItem(file.getName(), file.getPath(), this.f563b.get(strArr[i2]).size(), this.f563b.get(strArr[i2]).get(0)));
            }
        }
        return arrayList;
    }

    public boolean j() {
        return this.d;
    }
}
