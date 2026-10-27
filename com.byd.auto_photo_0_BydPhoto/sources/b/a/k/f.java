package b.a.k;

import com.byd.bean.FileBean;

/* JADX INFO: loaded from: classes2.dex */
public class f extends com.byd.taskmanager.b.a<Integer> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f545a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private FileBean f546b;

    public f(FileBean fileBean, int i, com.byd.taskmanager.b.b<Integer> bVar) {
        super((com.byd.taskmanager.b.b) bVar, true);
        this.f546b = fileBean;
        this.f545a = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.taskmanager.b.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public Integer doTask() {
        int i;
        long jD = com.byd.util.n.d(this.f546b.getPath());
        if (jD > 0) {
            this.f546b.setVideoDuration(jD);
            i = this.f545a;
        } else {
            i = -1;
        }
        return Integer.valueOf(i);
    }
}
