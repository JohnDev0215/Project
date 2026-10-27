package b.a.e;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;
import b.a.e.x;
import com.byd.auto_photo.R;
import com.byd.bean.ImageSelectFolderItem;

/* JADX INFO: loaded from: classes.dex */
public class i extends l {
    private int f;

    private static class a extends x.a {
        TextView d;
        View e;

        public a(View view) {
            super(view);
            this.d = (TextView) view.findViewById(R.id.item_name);
            this.e = view.findViewById(R.id.divider_line);
        }
    }

    public i(Context context) {
        super(context);
        this.f = context.getResources().getDimensionPixelSize(R.dimen.dp_45);
    }

    @Override // b.a.e.l, b.a.e.x
    protected x.a b(int i) {
        return new a(this.f453b.inflate(R.layout.item_image_select_folder5, (ViewGroup) null));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // b.a.e.l, b.a.e.x
    /* JADX INFO: renamed from: e */
    public void a(ImageSelectFolderItem imageSelectFolderItem, x.a aVar) {
        a aVar2 = (a) aVar;
        ViewGroup.LayoutParams layoutParams = aVar2.f455a.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = this.f;
        } else {
            aVar2.f455a.setLayoutParams(new AbsListView.LayoutParams(-1, this.f));
        }
        aVar2.d.setTextColor(com.byd.util.e.l(this.f454c, R.attr.image_select_folder_color));
        aVar2.e.setBackgroundColor(com.byd.util.e.l(this.f454c, R.attr.line_color));
        if (this.e.equals(imageSelectFolderItem.getPath())) {
            aVar2.d.setText(R.string.root_path);
        } else {
            aVar2.d.setText(imageSelectFolderItem.getName());
        }
    }
}
