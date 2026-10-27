package b.a.e;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.auto_photo.R;
import com.byd.bean.ImageEditItem;

/* JADX INFO: loaded from: classes.dex */
public abstract class c extends RecyclerView.Adapter<a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ImageEditItem[] f388a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected Context f389b;
    protected int d;
    protected int e;
    protected int f;
    protected boolean g;
    protected int i;
    private b k;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f390c = -1;
    protected boolean h = false;
    private HandlerC0014c j = new HandlerC0014c(this);

    class a extends RecyclerView.ViewHolder implements View.OnTouchListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        TextView f391a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ImageView f392b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ImageView f393c;
        View d;
        int e;
        private long f;

        public a(View view) {
            super(view);
            this.f391a = (TextView) view.findViewById(R.id.item_name);
            this.f392b = (ImageView) view.findViewById(R.id.item_bg_img);
            this.f393c = (ImageView) view.findViewById(R.id.item_bg_selector);
            this.d = view.findViewById(R.id.item_bg_linear_shape);
            view.setOnTouchListener(this);
            if (c.this.h) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f391a.getLayoutParams();
                layoutParams.addRule(14);
                layoutParams.addRule(12);
                layoutParams.bottomMargin = 20;
                this.f391a.setLayoutParams(layoutParams);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0035  */
        @Override // android.view.View.OnTouchListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean onTouch(android.view.View r11, android.view.MotionEvent r12) {
            /*
                Method dump skipped, instruction units count: 239
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: b.a.e.c.a.onTouch(android.view.View, android.view.MotionEvent):boolean");
        }
    }

    public interface b {
        boolean o(int i);

        boolean onItemClick(int i);

        void p(int i);
    }

    /* JADX INFO: renamed from: b.a.e.c$c, reason: collision with other inner class name */
    private static class HandlerC0014c extends b.a.d<c> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private View f394a;

        public HandlerC0014c(c cVar) {
            super(cVar);
        }

        @Override // b.a.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void handleMessage(c cVar, Message message) {
            int i = message.what;
            if (i == 1) {
                com.byd.util.w.c("===tap===");
                cVar.e(message);
                View view = this.f394a;
                if (view != null) {
                    view.playSoundEffect(0);
                    return;
                }
                return;
            }
            if (i == 2) {
                com.byd.util.w.c("===tap cancel===");
                removeMessages(1);
            } else {
                if (i != 3) {
                    return;
                }
                com.byd.util.w.c("===long click===");
                cVar.f(message);
            }
        }

        public void b(View view) {
            this.f394a = view;
        }
    }

    public c(Context context) {
        this.f389b = context;
        k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Message message) {
        int iIntValue = ((Integer) message.obj).intValue();
        com.byd.util.w.c("doHolderClick : " + iIntValue);
        b bVar = this.k;
        if (bVar != null) {
            if (!bVar.onItemClick(iIntValue) || !this.g || this.f390c == iIntValue) {
                return;
            }
        } else if (!this.g || this.f390c == iIntValue) {
            return;
        }
        this.f390c = iIntValue;
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(Message message) {
        int iIntValue = ((Integer) message.obj).intValue();
        b bVar = this.k;
        if (bVar == null) {
            if (!this.g || this.f390c == iIntValue) {
                return;
            }
            this.f390c = iIntValue;
            notifyDataSetChanged();
            return;
        }
        if (bVar.o(iIntValue) && this.g && this.f390c != iIntValue) {
            this.f390c = iIntValue;
            notifyDataSetChanged();
        }
    }

    private void u(a aVar, ImageEditItem imageEditItem, boolean z) {
        ImageView imageView;
        ImageView.ScaleType scaleType;
        if (z || (imageEditItem.getSelectColor() == 0 && imageEditItem.getSelectIcon() == 0 && imageEditItem.getSelectorDrawable() == null)) {
            imageView = aVar.f392b;
            scaleType = ImageView.ScaleType.CENTER_CROP;
        } else {
            imageView = aVar.f392b;
            scaleType = ImageView.ScaleType.CENTER_INSIDE;
        }
        imageView.setScaleType(scaleType);
    }

    public int g() {
        return this.f390c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ImageEditItem[] imageEditItemArr = this.f388a;
        if (imageEditItemArr == null) {
            return 0;
        }
        return imageEditItemArr.length;
    }

    protected Drawable h(int i) {
        return this.f389b.getResources().getDrawable(i);
    }

    public ImageEditItem i(int i) {
        return this.f388a[i];
    }

    public int j() {
        return this.f;
    }

    public void k() {
        this.i = com.byd.util.e.l(this.f389b, R.attr.text_color);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01c5  */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onBindViewHolder(b.a.e.c.a r8, int r9) {
        /*
            Method dump skipped, instruction units count: 577
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.e.c.onBindViewHolder(b.a.e.c$a, int):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public a onCreateViewHolder(ViewGroup viewGroup, int i) {
        a aVar = new a(LayoutInflater.from(this.f389b).inflate(R.layout.item_image_edit, (ViewGroup) null));
        aVar.e = viewGroup.getMeasuredHeight();
        return aVar;
    }

    public void n(int i) {
        if (this.g && this.f390c != i) {
            this.f390c = i;
            notifyDataSetChanged();
        }
    }

    public void o(int i) {
        this.e = i;
    }

    public void p(int i) {
        this.d = i;
    }

    public void q(ImageEditItem[] imageEditItemArr) {
        this.f388a = imageEditItemArr;
        notifyDataSetChanged();
    }

    public void r(int i) {
        this.i = i;
    }

    public void s(b bVar) {
        this.k = bVar;
    }

    public void t(int i) {
        this.f = i;
    }

    public void v(boolean z) {
        this.g = z;
    }

    public void w(boolean z) {
        this.h = z;
    }

    protected void x(TextView textView, int i, boolean z, RelativeLayout.LayoutParams layoutParams, int i2) {
        layoutParams.removeRule(12);
        layoutParams.removeRule(14);
        layoutParams.addRule(15);
        layoutParams.addRule(9);
        if (z) {
            textView.setCompoundDrawablesWithIntrinsicBounds(com.byd.util.h.a(h(i), i2), (Drawable) null, (Drawable) null, (Drawable) null);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(i, 0, 0, 0);
        }
    }

    protected void y(TextView textView, int i, boolean z, RelativeLayout.LayoutParams layoutParams, int i2) {
        layoutParams.removeRule(9);
        layoutParams.removeRule(15);
        layoutParams.addRule(14);
        layoutParams.addRule(12);
        if (z) {
            textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, com.byd.util.h.a(h(i), i2), (Drawable) null, (Drawable) null);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, i, 0, 0);
        }
    }

    protected void z(TextView textView, int i, boolean z, int i2) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        if (this.f == 1) {
            y(textView, i, z, layoutParams, i2);
        } else {
            x(textView, i, z, layoutParams, i2);
        }
    }
}
