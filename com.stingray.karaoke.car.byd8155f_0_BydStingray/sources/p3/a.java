package P3;

import a4.AbstractC0925f;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.os.LocaleList;

/* JADX INFO: loaded from: classes.dex */
public final class a extends Paint {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5330a;

    private final void a(LocaleList localeList) {
    }

    @Override // android.graphics.Paint
    public void setAlpha(int i7) {
        switch (this.f5330a) {
            case 0:
                if (Build.VERSION.SDK_INT >= 30) {
                    super.setAlpha(AbstractC0925f.c(i7));
                } else {
                    setColor((AbstractC0925f.c(i7) << 24) | (getColor() & 16777215));
                }
                break;
            default:
                super.setAlpha(i7);
                break;
        }
    }

    @Override // android.graphics.Paint
    public void setTextLocales(LocaleList localeList) {
        switch (this.f5330a) {
            case 0:
                break;
            default:
                super.setTextLocales(localeList);
                break;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(int i7, int i10) {
        super(i7);
        this.f5330a = i10;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(PorterDuff.Mode mode) {
        super(1);
        this.f5330a = 0;
        setXfermode(new PorterDuffXfermode(mode));
    }
}
