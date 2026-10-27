package b.a.e;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.palette.graphics.Palette;
import b.a.e.y;
import com.byd.auto_photo.R;
import com.byd.bean.FileBean;
import com.byd.view.XImageView;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import pl.droidsonroids.gif.GifImageView;

/* JADX INFO: loaded from: classes.dex */
public class m extends y<FileBean> {
    protected Context d;
    private int e;
    protected boolean f;
    private Bitmap g;
    private ByteArrayOutputStream h;
    protected int i = 0;
    private boolean j;
    private boolean k;
    private d l;
    private e m;

    private class b extends Handler implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        FileBean f420a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        f f421b;

        b(FileBean fileBean, f fVar) {
            super(Looper.getMainLooper());
            this.f420a = fileBean;
            this.f421b = fVar;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            m.this.C(this.f420a, message.arg1, false, message.arg2, this.f421b);
        }

        @Override // java.lang.Runnable
        public void run() {
            int iP = b.a.h.a.p(this.f420a.getPath());
            obtainMessage(1, iP > 1 ? m.this.e : 0, iP).sendToTarget();
        }
    }

    private class c extends Handler implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        XImageView f423a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        FileBean f424b;

        public c(XImageView xImageView, FileBean fileBean) {
            super(Looper.getMainLooper());
            this.f423a = xImageView;
            this.f424b = fileBean;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Bitmap bitmap = (Bitmap) message.obj;
            if (bitmap != null) {
                this.f423a.setImageBitmap(bitmap);
            } else {
                this.f423a.setImageResource(R.drawable.icon_load_error);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            obtainMessage(1, this.f424b.getType() == 2 ? b.a.h.l.m(m.this.d).o(this.f424b.getPath(), m.this.e, m.this.e, false, true, false) : b.a.h.a.h(this.f424b.getPath(), m.this.e, m.this.e, 1, Bitmap.Config.RGB_565, false)).sendToTarget();
        }
    }

    private class d implements Palette.PaletteAsyncListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f426a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private XImageView f427b;

        private d() {
        }

        public void a(XImageView xImageView, int i) {
            this.f427b = xImageView;
            this.f426a = i;
        }

        @Override // androidx.palette.graphics.Palette.PaletteAsyncListener
        public void onGenerated(Palette palette) {
            m.this.v(palette);
            List<Palette.Swatch> swatches = palette.getSwatches();
            if (swatches == null || swatches.size() == 0) {
                com.byd.util.w.c("ImageShowPagerAdapter reload image ");
                m.this.x(this.f427b, this.f426a);
            }
        }
    }

    public interface e {
        void a();

        void b();

        void c(List<FileBean> list, FileBean fileBean);

        void d(FileBean fileBean);

        void e(FileBean fileBean);
    }

    public class f extends y.a implements View.OnClickListener {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        XImageView f429c;
        View d;
        TextView e;
        GifImageView f;
        ProgressBar g;

        public f(View view) {
            super(view);
            view.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            XImageView xImageView = (XImageView) view.findViewById(R.id.item_thumb);
            this.f429c = xImageView;
            xImageView.setOnClickListener(this);
            View viewFindViewById = view.findViewById(R.id.item_play_video);
            this.d = viewFindViewById;
            viewFindViewById.setOnClickListener(this);
            this.e = (TextView) view.findViewById(R.id.item_burstshoot_num);
            this.f = (GifImageView) view.findViewById(R.id.item_gif);
            this.g = (ProgressBar) view.findViewById(R.id.item_load_progress);
            View view2 = this.d;
            if (view2 instanceof ImageView) {
                ((ImageView) view2).setImageDrawable(com.byd.util.e.p(m.this.d, R.attr.icon_play_big));
            } else {
                view2.setBackground(com.byd.util.e.p(m.this.d, R.attr.icon_play_big));
            }
            m.this.t(this);
        }

        @Override // b.a.e.y.a
        public /* bridge */ /* synthetic */ View a() {
            return super.a();
        }

        @Override // b.a.e.y.a
        protected void c() {
            this.f429c.setImageDrawable(null);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            m mVar = m.this;
            if (mVar.f) {
                if (mVar.m != null) {
                    m.this.m.a();
                    return;
                }
                return;
            }
            List<T> list = mVar.f460c;
            if (list == 0 || this.f462b >= list.size()) {
                return;
            }
            int id = view.getId();
            if (id == R.id.item_burstshoot_num) {
                if (m.this.m != null) {
                    m.this.m.d((FileBean) m.this.f460c.get(this.f462b));
                    return;
                }
                return;
            }
            if (id == R.id.item_play_video) {
                com.byd.util.w.c("====click to play video====");
                FileBean fileBean = (FileBean) m.this.f460c.get(this.f462b);
                if (m.this.m == null) {
                    return;
                }
                if (new File(fileBean.getPath()).exists()) {
                    m.this.m.c(m.this.f460c, fileBean);
                    return;
                } else {
                    m.this.m.b();
                    return;
                }
            }
            if (id != R.id.item_thumb) {
                return;
            }
            com.byd.util.w.c("==onImageClick==" + this.f462b);
            FileBean fileBean2 = (FileBean) m.this.f460c.get(this.f462b);
            if (m.this.m != null) {
                m.this.m.e(fileBean2);
            }
        }
    }

    public m(Context context, int i) {
        this.e = 0;
        this.d = context;
        this.e = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C(FileBean fileBean, int i, boolean z, int i2, f fVar) {
        b.a.h.l.i(this.d).c(fileBean.getPath()).j(i, i).g(R.drawable.icon_load_error).i(R.drawable.icon_loading_def).k(!z).h(i2).e(Bitmap.Config.ARGB_8888).b(fVar.f429c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v(Palette palette) {
        Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
        if (vibrantSwatch != null) {
            com.byd.util.w.c("Vibrant:0x" + Integer.toHexString(vibrantSwatch.getRgb()));
        }
        Palette.Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();
        if (darkVibrantSwatch != null) {
            com.byd.util.w.c("DarkVibrant:0x" + Integer.toHexString(darkVibrantSwatch.getRgb()));
        }
        Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
        if (lightVibrantSwatch != null) {
            com.byd.util.w.c("LightVibrant:0x" + Integer.toHexString(lightVibrantSwatch.getRgb()));
        }
        Palette.Swatch mutedSwatch = palette.getMutedSwatch();
        if (mutedSwatch != null) {
            com.byd.util.w.c("Muted:0x" + Integer.toHexString(mutedSwatch.getRgb()));
        }
        Palette.Swatch darkMutedSwatch = palette.getDarkMutedSwatch();
        if (darkMutedSwatch != null) {
            com.byd.util.w.c("DarkMuted:0x" + Integer.toHexString(darkMutedSwatch.getRgb()));
        }
        Palette.Swatch lightMutedSwatch = palette.getLightMutedSwatch();
        if (lightMutedSwatch != null) {
            com.byd.util.w.c("LightMuted:0x" + Integer.toHexString(lightMutedSwatch.getRgb()));
        }
    }

    private void w() {
        ByteArrayOutputStream byteArrayOutputStream = this.h;
        if (byteArrayOutputStream != null) {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            this.h = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x(XImageView xImageView, int i) {
        if (i < 0 || i >= this.f460c.size()) {
            return;
        }
        new Thread(new c(xImageView, (FileBean) this.f460c.get(i))).start();
    }

    public void A(Bitmap bitmap) {
        this.g = bitmap;
        this.f = true;
        this.j = false;
        notifyDataSetChanged();
    }

    public void B(InputStream inputStream) {
        this.f = true;
        this.j = true;
        this.h = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                try {
                    try {
                        int i = inputStream.read(bArr);
                        if (i == -1) {
                            break;
                        } else {
                            this.h.write(bArr, 0, i);
                        }
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                    inputStream.close();
                }
                notifyDataSetChanged();
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                throw th;
            }
        }
        this.h.flush();
        inputStream.close();
        notifyDataSetChanged();
    }

    @Override // b.a.e.y
    protected void d(int i, y.a aVar) {
        f fVar = (f) aVar;
        if (!this.f) {
            FileBean fileBean = (FileBean) this.f460c.get(i);
            if (fileBean.getName().toLowerCase().endsWith(".gif")) {
                fVar.f.setVisibility(0);
                try {
                    com.byd.util.w.c("load gif by GifDrawable : " + fileBean.getPath());
                    fVar.f.setImageDrawable(new pl.droidsonroids.gif.c(fileBean.getPath()));
                    fVar.f429c.setImageBitmap(null);
                } catch (IOException e2) {
                    e2.printStackTrace();
                    com.byd.util.w.c("load gif exception : " + e2.getMessage());
                    fVar.f.setVisibility(8);
                    fVar.f429c.loadGifPath(fileBean.getPath());
                }
            } else {
                fVar.f.setVisibility(8);
                if (com.byd.util.m.e(fileBean.getName())) {
                    C(fileBean, 0, true, 1, fVar);
                } else {
                    new Thread(new b(fileBean, fVar)).start();
                }
            }
            if (fileBean.getType() == 3) {
                fVar.d.setVisibility(8);
                fVar.f429c.setMode(0);
                fVar.e.setVisibility(this.i);
            } else if (fileBean.getType() == 2) {
                fVar.e.setVisibility(8);
                fVar.d.setVisibility(0);
                fVar.d.requestFocus();
                fVar.f429c.setMode(-1);
            } else {
                fVar.e.setVisibility(8);
                fVar.d.setVisibility(8);
                fVar.f429c.setMode(0);
            }
        } else if (this.j) {
            fVar.f.setVisibility(0);
            try {
                com.byd.util.w.c("load gif by GifDrawable : stream");
                fVar.f.setImageDrawable(new pl.droidsonroids.gif.c(new ByteArrayInputStream(this.h.toByteArray())));
            } catch (Exception e3) {
                e3.printStackTrace();
                com.byd.util.w.c("load gif stream exception : " + e3.getMessage());
                fVar.f.setVisibility(8);
                fVar.f429c.loadGifStream(new ByteArrayInputStream(this.h.toByteArray()));
            }
        } else {
            fVar.f.setVisibility(8);
            if (this.g != null) {
                com.byd.util.w.c("show stream bitmap");
                fVar.f429c.setImageBitmap(this.g);
            } else {
                fVar.f429c.setImageResource(R.drawable.icon_load_error);
            }
        }
        u(i, fVar);
    }

    @Override // b.a.e.y
    protected y.a e() {
        return new f(LayoutInflater.from(this.d).inflate(R.layout.pager_image_show, (ViewGroup) null));
    }

    @Override // b.a.e.y
    public void f() {
        super.f();
        w();
    }

    @Override // b.a.e.y, androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        if (this.f) {
            return 1;
        }
        return super.getCount();
    }

    @Override // b.a.e.y
    public void h(List<FileBean> list) {
        int size;
        if (!this.k || list == null || list.size() <= 0 || (size = list.size()) <= 1) {
            super.h(list);
            return;
        }
        int i = size + 2;
        ArrayList arrayList = new ArrayList(i);
        FileBean fileBean = list.get(0);
        arrayList.add(list.get(size - 1));
        for (int i2 = 1; i2 < i - 1; i2++) {
            arrayList.add(list.get(i2 - 1));
        }
        arrayList.add(fileBean);
        super.h(arrayList);
    }

    @Override // b.a.e.y
    protected void i(y.a aVar) {
        f fVar = (f) aVar;
        fVar.f429c.setTag(Integer.valueOf(fVar.b()));
    }

    public boolean o(String str) {
        List<T> list = this.f460c;
        if (list == 0) {
            return false;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (((FileBean) this.f460c.get(i)).getPath().startsWith(str)) {
                return true;
            }
        }
        return false;
    }

    public void p(int i) {
        f fVar;
        List<T> list = this.f460c;
        if (list == 0 || list.size() == 0 || i >= this.f460c.size() || ((FileBean) this.f460c.get(i)).getName().toLowerCase().endsWith(".gif") || (fVar = (f) c(i)) == null) {
            return;
        }
        XImageView xImageView = fVar.f429c;
        Drawable drawable = xImageView.getDrawable();
        if (drawable == null || !(drawable instanceof BitmapDrawable)) {
            com.byd.util.w.c("ImageShowBaseAdapter-->has no image");
            x(xImageView, i);
            return;
        }
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        if (this.l == null) {
            this.l = new d();
        }
        this.l.a(xImageView, i);
        try {
            Palette.from(bitmap).generate(this.l);
        } catch (Exception unused) {
        }
    }

    public void q(String str, String str2) {
        int size = this.f460c.size();
        for (int i = 0; i < size; i++) {
            if (str.equals(((FileBean) this.f460c.get(i)).getPath())) {
                FileBean fileBean = (FileBean) this.f460c.get(i);
                File file = new File(str2);
                fileBean.setName(file.getName());
                fileBean.setPath(file.getPath());
                return;
            }
        }
    }

    public int r(String str, int i) {
        List<T> list = this.f460c;
        if (list == 0) {
            return i;
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((FileBean) this.f460c.get(i2)).getPath().equals(str)) {
                return i2;
            }
        }
        return i;
    }

    public boolean s() {
        return this.f;
    }

    protected void t(f fVar) {
    }

    protected void u(int i, f fVar) {
        throw null;
    }

    public void y(boolean z) {
        this.k = z;
    }

    public void z(e eVar) {
        this.m = eVar;
    }
}
