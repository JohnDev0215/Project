package m3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.stingray.drivemodedetector.R;
import s3.AbstractC2405y;
import s3.U;

/* JADX INFO: renamed from: m3.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1811k extends AbstractC2405y {
    public final String[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float[] f19061e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f19062f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ s f19063g;

    public C1811k(s sVar, String[] strArr, float[] fArr) {
        this.f19063g = sVar;
        this.d = strArr;
        this.f19061e = fArr;
    }

    @Override // s3.AbstractC2405y
    public final int a() {
        return this.d.length;
    }

    @Override // s3.AbstractC2405y
    public final void c(U u4, final int i7) {
        o oVar = (o) u4;
        String[] strArr = this.d;
        if (i7 < strArr.length) {
            oVar.f19071u.setText(strArr[i7]);
        }
        int i10 = this.f19062f;
        View view = oVar.f19072v;
        View view2 = oVar.f22219a;
        if (i7 == i10) {
            view2.setSelected(true);
            view.setVisibility(0);
        } else {
            view2.setSelected(false);
            view.setVisibility(4);
        }
        view2.setOnClickListener(new View.OnClickListener() { // from class: m3.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                C1811k c1811k = this.l;
                int i11 = c1811k.f19062f;
                int i12 = i7;
                s sVar = c1811k.f19063g;
                if (i12 != i11) {
                    sVar.setPlaybackSpeed(c1811k.f19061e[i12]);
                }
                sVar.f19138v.dismiss();
            }
        });
    }

    @Override // s3.AbstractC2405y
    public final U d(ViewGroup viewGroup) {
        return new o(LayoutInflater.from(this.f19063g.getContext()).inflate(R.layout.exo_styled_sub_settings_list_item, viewGroup, false));
    }
}
