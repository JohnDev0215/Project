package b.a.e;

import android.content.Context;
import com.byd.bean.FileBean;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class u extends s<FileBean> {
    public u(Context context, int i, int i2) {
        super(context, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // b.a.e.t
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public FileBean h(FileBean fileBean) {
        return fileBean;
    }

    @Override // b.a.e.t
    public FileBean r(String str) {
        List<T> list = this.f440a;
        if (list == 0 || list.size() <= 0) {
            return null;
        }
        int size = this.f440a.size();
        for (int i = 0; i < size; i++) {
            if (str.equals(((FileBean) this.f440a.get(i)).getPath())) {
                this.j = i;
                return (FileBean) this.f440a.get(i);
            }
        }
        return null;
    }
}
