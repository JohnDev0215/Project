package defpackage;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.Comparator;
import org.chromium.chrome.browser.privacy_sandbox.Topic;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class no0 implements Comparator {
    public final /* synthetic */ int e;

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.e) {
            case 0:
                byte[] bArr = (byte[]) obj;
                byte[] bArr2 = (byte[]) obj2;
                if (bArr.length != bArr2.length) {
                    return bArr.length - bArr2.length;
                }
                for (int i = 0; i < bArr.length; i++) {
                    byte b = bArr[i];
                    byte b2 = bArr2[i];
                    if (b != b2) {
                        return b - b2;
                    }
                }
                return 0;
            case 1:
                return Integer.compare(((ov) obj2).a(), ((ov) obj).a());
            case 2:
                return ((Topic) obj).c.compareTo(((Topic) obj2).c);
            case 3:
                return ((ii) obj).e() - ((ii) obj2).e();
            default:
                return Integer.compare(((AccessibilityNodeInfoCompat.a) obj).a(), ((AccessibilityNodeInfoCompat.a) obj2).a());
        }
    }
}
