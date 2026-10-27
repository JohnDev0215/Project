package defpackage;

import J.N;
import java.util.Comparator;
import org.chromium.components.search_engines.TemplateUrl;

/* JADX INFO: compiled from: SearchEngineAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fg2 implements Comparator<TemplateUrl> {
    public final /* synthetic */ TemplateUrl e;

    public fg2(TemplateUrl templateUrl) {
        this.e = templateUrl;
    }

    @Override // java.util.Comparator
    public final int compare(TemplateUrl templateUrl, TemplateUrl templateUrl2) {
        TemplateUrl templateUrl3 = templateUrl;
        TemplateUrl templateUrl4 = templateUrl2;
        boolean zA = templateUrl3.a();
        long j = templateUrl3.a;
        if (zA && templateUrl4.a()) {
            return N.MhwmJ882(j) - N.MhwmJ882(templateUrl4.a);
        }
        if (!templateUrl3.a()) {
            if (!templateUrl4.a()) {
                if (templateUrl3.equals(templateUrl4)) {
                    return 0;
                }
                TemplateUrl templateUrl5 = this.e;
                if (!templateUrl3.equals(templateUrl5)) {
                    if (!templateUrl4.equals(templateUrl5)) {
                        return Long.compare(N.MzOF1_dp(templateUrl4.a), N.MzOF1_dp(j));
                    }
                }
            }
            return 1;
        }
        return -1;
    }
}
