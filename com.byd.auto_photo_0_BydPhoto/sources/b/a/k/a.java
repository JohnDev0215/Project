package b.a.k;

import android.content.Context;
import android.hardware.bydauto.TsNormalNum;
import android.text.TextUtils;
import com.byd.bean.FileBean;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class a extends com.byd.taskmanager.b.a<String> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private FileBean f523a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f524b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f525c;

    public a(Context context, FileBean fileBean, com.byd.taskmanager.b.b bVar, String str) {
        super(bVar, TsNormalNum.THOUSAND_FIVE);
        this.f524b = context;
        this.f523a = fileBean;
        this.f525c = str;
        this.name = fileBean.getPath();
        this.submitInUIThread = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.taskmanager.b.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public String doTask() throws IOException {
        if (this.f523a.getLat() == 0.0d && this.f523a.getLng() == 0.0d) {
            if (com.byd.util.m.e(this.f523a.getName())) {
                double[] dArrF = com.byd.util.m.f(this.f524b, this.f523a.getPath(), com.byd.util.n.k(this.f524b, new File(this.f523a.getPath())));
                if (dArrF != null) {
                    this.f523a.setLat(dArrF[0]);
                    this.f523a.setLng(dArrF[1]);
                }
            } else {
                com.byd.util.n.w(this.f524b, this.f523a);
            }
        }
        if (this.f523a.getAddress() != null && this.f525c.equals(this.f523a.getAddressLocale())) {
            return this.f523a.getAddress();
        }
        if (this.f523a.getLat() == 0.0d && this.f523a.getLng() == 0.0d) {
            return null;
        }
        String strF = com.byd.util.e.f(this.f524b, this.f523a.getLat(), this.f523a.getLng());
        if (!TextUtils.isEmpty(strF)) {
            this.f523a.setAddress(strF);
            this.f523a.setAddressLocale(this.f525c);
        }
        return strF;
    }
}
