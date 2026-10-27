package b.a.k;

import com.byd.bean.FileBean;
import com.byd.share.ShareItem;
import com.byd.util.w;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class e extends com.byd.taskmanager.b.a<List<FileBean>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ShareItem f544a;

    public e(ShareItem shareItem) {
        this.f544a = shareItem;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.taskmanager.b.a
    public List<FileBean> doTask() throws InterruptedException {
        LinkedList linkedList = new LinkedList();
        int size = this.f544a.getPaths().size();
        for (int i = 0; i < size; i++) {
            Thread.sleep(0L);
            String str = this.f544a.getPaths().get(i);
            File file = new File(str);
            linkedList.add(new FileBean(file.getName(), str, 2, this.f544a.getDataType(), file.lastModified()));
        }
        w.c("ChangeShareItem : " + size + " result size : " + linkedList.size());
        return linkedList;
    }
}
