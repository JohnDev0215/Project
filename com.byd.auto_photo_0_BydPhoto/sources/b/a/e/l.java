package b.a.e;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import b.a.e.x;
import com.byd.auto_photo.R;
import com.byd.bean.ImageSelectFolderItem;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class l extends x<ImageSelectFolderItem, x.a> {
    protected int d;
    protected String e;

    static class a extends x.a {
        ImageView d;
        TextView e;
        TextView f;
        ImageView g;

        public a(View view) {
            super(view);
            this.d = (ImageView) view.findViewById(R.id.item_icon);
            this.e = (TextView) view.findViewById(R.id.item_name);
            this.f = (TextView) view.findViewById(R.id.item_num);
            this.g = (ImageView) view.findViewById(R.id.item_checked);
        }
    }

    public l(Context context) {
        super(context);
        this.d = -1;
        this.e = com.byd.util.g.i(context, "/");
    }

    @Override // b.a.e.x
    protected x.a b(int i) {
        return new a(this.f453b.inflate(R.layout.item_image_select_folder, (ViewGroup) null));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // b.a.e.x
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public void a(ImageSelectFolderItem imageSelectFolderItem, x.a aVar) {
        ImageView imageView;
        int i;
        a aVar2 = (a) aVar;
        if (imageSelectFolderItem.getFirstBean().getThumbPath() != null) {
            aVar2.d.setImageURI(Uri.parse(imageSelectFolderItem.getFirstBean().getThumbPath()));
        } else {
            b.a.h.l.i(this.f454c).c(imageSelectFolderItem.getFirstBean().getPath()).g(R.drawable.icon_load_error).j(500, 500).f(true).b(aVar2.d);
        }
        if (this.e.equals(imageSelectFolderItem.getPath())) {
            aVar2.e.setText(R.string.root_path);
        } else {
            aVar2.e.setText(imageSelectFolderItem.getName());
        }
        aVar2.f.setText(String.valueOf(imageSelectFolderItem.getChildCount()));
        if (aVar2.f456b == this.d) {
            imageView = aVar2.g;
            i = 0;
        } else {
            imageView = aVar2.g;
            i = 8;
        }
        imageView.setVisibility(i);
    }

    public ImageSelectFolderItem f() {
        int i;
        List<T> list = this.f452a;
        if (list != 0 && (i = this.d) >= 0 && i < list.size()) {
            return (ImageSelectFolderItem) this.f452a.get(this.d);
        }
        return null;
    }

    public void g(int i) {
        if (this.d != i) {
            this.d = i;
            notifyDataSetChanged();
        }
    }
}
