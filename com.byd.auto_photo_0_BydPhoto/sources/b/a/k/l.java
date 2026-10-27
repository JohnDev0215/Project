package b.a.k;

import android.content.Context;
import com.byd.bean.FileBean;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class l extends com.byd.taskmanager.b.a<Integer> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f566a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private FileBean f567b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f568c;

    public l(Context context, FileBean fileBean, int i, com.byd.taskmanager.b.b<Integer> bVar) {
        super((com.byd.taskmanager.b.b) bVar, true);
        this.f568c = context;
        this.f567b = fileBean;
        this.f566a = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.taskmanager.b.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public Integer doTask() {
        String path = this.f567b.getPath();
        if (this.f567b.getType() == 3) {
            path = new File(path).getParent();
        }
        int i = com.byd.util.f.k(this.f568c).o(path) ? 1 : 2;
        this.f567b.setCollected(i);
        return Integer.valueOf(i != 2 ? this.f566a : -1);
    }
}
