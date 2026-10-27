package l3;

import android.graphics.Bitmap;
import android.media.Rating;
import android.os.Bundle;
import android.os.Parcelable;

/* JADX INFO: renamed from: l3.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1755k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Bundle f18579a;

    public C1755k() {
        this.f18579a = new Bundle();
    }

    public void a(String str, Bitmap bitmap) {
        Integer num = (Integer) C1741F.f18520n.get(str);
        if (num != null && num.intValue() != 2) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.i("The ", str, " key cannot be used to put a Bitmap"));
        }
        this.f18579a.putParcelable(str, bitmap);
    }

    public void b(String str, long j3) {
        Integer num = (Integer) C1741F.f18520n.get(str);
        if (num != null && num.intValue() != 0) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.i("The ", str, " key cannot be used to put a long"));
        }
        this.f18579a.putLong(str, j3);
    }

    public void c(String str, Y y10) {
        Object obj;
        Integer num = (Integer) C1741F.f18520n.get(str);
        if (num != null && num.intValue() != 3) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.i("The ", str, " key cannot be used to put a Rating"));
        }
        if (y10.f18563n == null) {
            boolean zF = y10.f();
            int i7 = y10.l;
            if (zF) {
                boolean z6 = false;
                float f10 = y10.f18562m;
                switch (i7) {
                    case 1:
                        if (i7 == 1) {
                            z6 = f10 == 1.0f;
                        }
                        y10.f18563n = Rating.newHeartRating(z6);
                        break;
                    case 2:
                        if (i7 == 2) {
                            z6 = f10 == 1.0f;
                        }
                        y10.f18563n = Rating.newThumbRating(z6);
                        break;
                    case 3:
                    case 4:
                    case 5:
                        y10.f18563n = Rating.newStarRating(i7, y10.d());
                        break;
                    case 6:
                        if (i7 != 6 || !y10.f()) {
                            f10 = -1.0f;
                        }
                        y10.f18563n = Rating.newPercentageRating(f10);
                        break;
                    default:
                        obj = null;
                        break;
                }
            } else {
                y10.f18563n = Rating.newUnratedRating(i7);
            }
            obj = y10.f18563n;
        } else {
            obj = y10.f18563n;
        }
        this.f18579a.putParcelable(str, (Parcelable) obj);
    }

    public void d(String str, String str2) {
        Integer num = (Integer) C1741F.f18520n.get(str);
        if (num != null && num.intValue() != 1) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.i("The ", str, " key cannot be used to put a String"));
        }
        this.f18579a.putCharSequence(str, str2);
    }

    public void e(CharSequence charSequence, String str) {
        Integer num = (Integer) C1741F.f18520n.get(str);
        if (num != null && num.intValue() != 1) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.i("The ", str, " key cannot be used to put a CharSequence"));
        }
        this.f18579a.putCharSequence(str, charSequence);
    }

    public C1755k(Bundle bundle) {
        this.f18579a = bundle;
    }
}
