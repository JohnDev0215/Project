package b.a.e;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import b.a.e.k;
import com.byd.auto_photo.R;
import com.byd.view.XRoundCornerImageView;

/* JADX INFO: loaded from: classes.dex */
public class j extends k {
    public j(Context context) {
        super(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public k.a onCreateViewHolder(ViewGroup viewGroup, int i) {
        XRoundCornerImageView xRoundCornerImageView = new XRoundCornerImageView(this.f417b);
        xRoundCornerImageView.setRadius(this.f417b.getResources().getDimensionPixelSize(R.dimen.dp_6));
        xRoundCornerImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new k.a(xRoundCornerImageView);
    }
}
