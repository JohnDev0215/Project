package defpackage;

import android.graphics.Bitmap;
import android.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import org.chromium.base.Callback;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.download.e;
import org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$WebFeedMetadata;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ad0 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Callback f;

    public /* synthetic */ ad0(Callback callback, int i) {
        this.e = i;
        this.f = callback;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        Callback callback = this.f;
        switch (i) {
            case 0:
                Integer num = (Integer) obj;
                if (num.intValue() == 3) {
                    e.k(3);
                } else if (num.intValue() == 4) {
                    e.k(2);
                } else if (num.intValue() != 1) {
                    e.k(4);
                }
                callback.onResult(Boolean.FALSE);
                break;
            case 1:
                ArrayList arrayList = new ArrayList();
                for (Object obj2 : (Object[]) obj) {
                    arrayList.add((WebFeedBridge$WebFeedMetadata) obj2);
                }
                callback.onResult(arrayList);
                break;
            case 2:
                callback.onResult((Bitmap) obj);
                break;
            case 3:
                callback.onResult(Arrays.asList((String[]) obj));
                break;
            case 4:
                callback.onResult((a10) obj);
                break;
            case 5:
                callback.onResult((Pair) obj);
                break;
            default:
                Integer num2 = (Integer) obj;
                int iIntValue = num2.intValue();
                if (iIntValue == R$id.close_tab) {
                    n3.X0("MobileMenuCloseTab.LongTapMenu");
                } else if (iIntValue == R$id.new_tab_menu_id) {
                    n3.X0("MobileMenuNewTab.LongTapMenu");
                } else if (iIntValue == R$id.new_incognito_tab_menu_id) {
                    n3.X0("MobileMenuNewIncognitoTab.LongTapMenu");
                }
                callback.onResult(num2);
                break;
        }
    }
}
