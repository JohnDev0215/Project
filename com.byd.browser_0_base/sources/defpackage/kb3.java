package defpackage;

import java.util.Comparator;
import org.chromium.components.bookmarks.BookmarkItem;
import org.chromium.components.browser_ui.photo_picker.c;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class kb3 implements Comparator {
    public final /* synthetic */ int e;

    public /* synthetic */ kb3(int i) {
        this.e = i;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.e) {
            case 0:
                byte[] bArr = (byte[]) obj;
                byte[] bArr2 = (byte[]) obj2;
                if (bArr != bArr2) {
                    if (bArr == null) {
                        return -1;
                    }
                    if (bArr2 == null) {
                        return 1;
                    }
                    for (int i = 0; i < Math.min(bArr.length, bArr2.length); i++) {
                        byte b = bArr[i];
                        byte b2 = bArr2[i];
                        if (b != b2) {
                            return b - b2;
                        }
                    }
                    if (bArr.length != bArr2.length) {
                        return bArr.length - bArr2.length;
                    }
                }
                return 0;
            case 1:
                yf yfVar = (yf) obj;
                yfVar.b.getClass();
                BookmarkItem bookmarkItem = ((yf) obj2).b;
                BookmarkItem bookmarkItem2 = yfVar.b;
                boolean z = bookmarkItem2.i;
                if (z != bookmarkItem.i) {
                    if (!z) {
                        return -1;
                    }
                } else if (bookmarkItem2.h > bookmarkItem.h) {
                    return -1;
                }
                return 1;
            default:
                c.b bVar = (c.b) obj;
                c.b bVar2 = (c.b) obj2;
                int i2 = c.u;
                int i3 = bVar.d;
                int i4 = bVar2.d;
                if (i3 != i4) {
                    return i3 - i4;
                }
                if (i3 == 3) {
                    boolean z2 = bVar2.e;
                    boolean z3 = bVar.e;
                    if (z3 != z2) {
                        return z3 ? -1 : 1;
                    }
                }
                return bVar.f - bVar2.f;
        }
    }
}
