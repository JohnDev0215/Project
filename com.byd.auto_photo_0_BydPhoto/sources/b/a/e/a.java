package b.a.e;

import android.content.Context;
import android.content.res.Resources;
import android.provider.MediaStore;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import b.a.e.b;
import com.byd.auto_photo.R;
import com.byd.bean.AlbumItem;
import com.byd.view.XRoundCornerImageView;

/* JADX INFO: loaded from: classes.dex */
public class a extends b {
    private int x;

    public a(Context context, int i, int i2) {
        super(context, i, i2);
        this.q = com.byd.util.e.l(context, R.attr.text_color_album_num);
        this.x = com.byd.util.e.l(context, R.attr.album_folder_bg_color);
        if (b.a.a.a(context) == 1) {
            this.r = R.drawable.icon_selected_ts;
        }
    }

    private void C(ImageView imageView, int i) {
        imageView.setImageDrawable(com.byd.util.h.a(this.f380b.getResources().getDrawable(i), this.p));
    }

    @Override // b.a.e.b
    public void b(int i, int i2) {
        Resources resources;
        int i3;
        int i4 = this.g;
        int i5 = (i - (this.f * i4)) / i4;
        if (this.j == 0) {
            this.j = this.f380b.getResources().getDimensionPixelSize(R.dimen.album_name_height);
            if (this.n) {
                resources = this.f380b.getResources();
                i3 = R.dimen.album_num_height;
            } else {
                resources = this.f380b.getResources();
                i3 = R.dimen.dp_4_5;
            }
            this.k = resources.getDimensionPixelSize(i3);
        }
        if (this.f381c != i5) {
            this.f381c = i5;
            if (i2 == 0 || i5 < i2) {
                this.e = i5;
            } else {
                this.e = i2;
            }
            this.d = this.e + this.j + this.k;
            notifyDataSetChanged();
        }
    }

    @Override // b.a.e.b
    protected void o(b.ViewOnClickListenerC0013b viewOnClickListenerC0013b, int i) {
        XRoundCornerImageView xRoundCornerImageView;
        int i2;
        viewOnClickListenerC0013b.f383a.setRoundBgColor(this.x);
        super.o(viewOnClickListenerC0013b, i);
        if (!this.n) {
            viewOnClickListenerC0013b.f384b.setGravity(1);
        }
        AlbumItem albumItem = this.f379a.get(i);
        if (albumItem.getUri() == null && albumItem.getNum() == -1) {
            viewOnClickListenerC0013b.f383a.setScaleType(ImageView.ScaleType.CENTER);
        }
        if (albumItem.getCoverPath() == null) {
            if (MediaStore.Video.Media.EXTERNAL_CONTENT_URI.equals(albumItem.getUri())) {
                xRoundCornerImageView = viewOnClickListenerC0013b.f383a;
                i2 = R.drawable.icon_def_folder_videos;
            } else if (albumItem.getSourceType() == 5) {
                xRoundCornerImageView = viewOnClickListenerC0013b.f383a;
                i2 = R.drawable.icon_def_folder_usb;
            } else if (albumItem.getSourceType() == 4) {
                xRoundCornerImageView = viewOnClickListenerC0013b.f383a;
                i2 = R.drawable.icon_def_folder_sd;
            } else {
                if (albumItem.getUri() == null) {
                    viewOnClickListenerC0013b.f383a.setRoundBgColor(0);
                    if (b.a.a.a(this.f380b) == 0) {
                        viewOnClickListenerC0013b.f383a.setBackgroundDrawable(com.byd.util.e.p(this.f380b, R.attr.bg_new_album_item));
                        return;
                    } else {
                        viewOnClickListenerC0013b.f383a.setBackgroundResource(R.drawable.new_album_item_bg);
                        viewOnClickListenerC0013b.f383a.setImageDrawable(com.byd.util.h.a(this.f380b.getResources().getDrawable(R.drawable.icon_new_folder), -1));
                        return;
                    }
                }
                xRoundCornerImageView = viewOnClickListenerC0013b.f383a;
                i2 = R.drawable.icon_def_folder;
            }
            C(xRoundCornerImageView, i2);
        }
    }

    @Override // b.a.e.b, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        if (viewHolder instanceof b.d) {
            b.d dVar = (b.d) viewHolder;
            ViewGroup.LayoutParams layoutParams = dVar.f386a.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.height = this.f380b.getResources().getDimensionPixelSize(R.dimen.album_more_title_height);
            } else {
                dVar.f386a.setLayoutParams(new RecyclerView.LayoutParams(-1, this.f380b.getResources().getDimensionPixelSize(R.dimen.album_more_title_height)));
            }
        }
    }

    @Override // b.a.e.b
    protected void q(b.ViewOnClickListenerC0013b viewOnClickListenerC0013b) {
        ViewGroup.LayoutParams layoutParams;
        super.q(viewOnClickListenerC0013b);
        if (this.n || (layoutParams = viewOnClickListenerC0013b.f.getLayoutParams()) == null) {
            return;
        }
        ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = this.f380b.getResources().getDimensionPixelSize(R.dimen.dp_4_5);
    }

    @Override // b.a.e.b
    public void s(Context context) {
        this.f380b = context;
        this.p = com.byd.util.e.l(context, R.attr.text_color);
        this.q = com.byd.util.e.l(context, R.attr.text_color_album_num);
        this.x = com.byd.util.e.l(context, R.attr.album_folder_bg_color);
        notifyDataSetChanged();
    }
}
