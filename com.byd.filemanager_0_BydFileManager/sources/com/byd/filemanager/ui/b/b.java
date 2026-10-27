package com.byd.filemanager.ui.b;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Priority;
import com.byd.filemanager.R;
import com.byd.filemanager.ThreadManager;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.bean.FileType;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/* JADX INFO: compiled from: FileAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends RecyclerView.g<h> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private SimpleDateFormat f3693c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<FileItem> f3694d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.byd.filemanager.ui.a<FileItem> f3695e;
    private g f;
    private boolean g;
    private int h;
    private Context i;
    private String k;
    private boolean j = true;
    private com.bumptech.glide.request.e l = new com.bumptech.glide.request.e().f(com.bumptech.glide.load.engine.h.f3021a).h().g().O(Priority.HIGH).c().M(200, 120).L();

    /* JADX INFO: compiled from: FileAdapter.java */
    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ FileItem f3696b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ h f3697c;

        a(FileItem fileItem, h hVar) {
            this.f3696b = fileItem;
            this.f3697c = hVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f3696b.P(this.f3697c.v.isChecked());
            if (b.this.f3695e != null) {
                b.this.f3695e.a(this.f3696b, this.f3697c.j());
            }
        }
    }

    /* JADX INFO: renamed from: com.byd.filemanager.ui.b.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FileAdapter.java */
    class ViewOnClickListenerC0088b implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ h f3699b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ FileItem f3700c;

        ViewOnClickListenerC0088b(h hVar, FileItem fileItem) {
            this.f3699b = hVar;
            this.f3700c = fileItem;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f3699b.v.getVisibility() == 0) {
                this.f3699b.v.setChecked(!r3.isChecked());
                this.f3700c.P(this.f3699b.v.isChecked());
            }
            if (b.this.f3695e != null) {
                b.this.f3695e.a(this.f3700c, this.f3699b.j());
            }
        }
    }

    /* JADX INFO: compiled from: FileAdapter.java */
    class c implements View.OnLongClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ FileItem f3702b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ h f3703c;

        c(FileItem fileItem, h hVar) {
            this.f3702b = fileItem;
            this.f3703c = hVar;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            b.this.j = !this.f3702b.L();
            com.byd.filemanager.k.b.b("isMultiSelect = " + b.this.j);
            if (b.this.j && (b.this.f == null || b.this.f.a(this.f3703c.v, this.f3702b))) {
                b.this.N(true);
                b.this.i(this.f3703c.j());
            }
            return true;
        }
    }

    /* JADX INFO: compiled from: FileAdapter.java */
    class d implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ File f3705b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ h f3706c;

        /* JADX INFO: compiled from: FileAdapter.java */
        class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ String f3708b;

            a(String str) {
                this.f3708b = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                d.this.f3706c.w.setText(this.f3708b);
            }
        }

        d(File file, h hVar) {
            this.f3705b = file;
            this.f3706c = hVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.f3693c = new SimpleDateFormat(b.this.i.getString(R.string.date_format), com.byd.filemanager.k.a.a(b.this.i));
            this.f3706c.w.post(new a(b.this.f3693c.format(new Date(this.f3705b.lastModified()))));
        }
    }

    /* JADX INFO: compiled from: FileAdapter.java */
    static /* synthetic */ class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3710a;

        static {
            int[] iArr = new int[FileType.values().length];
            f3710a = iArr;
            try {
                iArr[FileType.DIRECTORY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3710a[FileType.DOWNLOAD_DIRECTORY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3710a[FileType.WORD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3710a[FileType.TXT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3710a[FileType.PDF.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3710a[FileType.EXCEL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3710a[FileType.PPT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f3710a[FileType.AUDIO.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f3710a[FileType.VIDEO.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f3710a[FileType.IMAGE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f3710a[FileType.APK.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f3710a[FileType.COMPRESS.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f3710a[FileType.UNKNOWN.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    /* JADX INFO: compiled from: FileAdapter.java */
    private static class f extends com.bumptech.glide.request.h.b<ImageView, Drawable> {
        private final String h;

        public f(ImageView imageView, String str) {
            super(imageView);
            this.h = str;
        }

        @Override // com.bumptech.glide.request.h.e
        public void e(Drawable drawable) {
            com.byd.filemanager.k.b.b("onLoadFailed");
            T t = this.f3502d;
            if (t == 0 || drawable == null) {
                return;
            }
            ((ImageView) t).setImageDrawable(drawable);
        }

        @Override // com.bumptech.glide.request.h.b
        protected void o(Drawable drawable) {
        }

        @Override // com.bumptech.glide.request.h.e
        /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
        public void d(Drawable drawable, com.bumptech.glide.request.i.b<? super Drawable> bVar) {
            String str = (String) ((ImageView) this.f3502d).getTag();
            if (str == null || str.equals(this.h)) {
                try {
                    ((ImageView) this.f3502d).setImageDrawable(drawable);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: compiled from: FileAdapter.java */
    public interface g {
        boolean a(View view, FileItem fileItem);
    }

    /* JADX INFO: compiled from: FileAdapter.java */
    protected static class h extends RecyclerView.c0 {
        ImageView t;
        TextView u;
        CheckBox v;
        TextView w;

        public h(View view) {
            super(view);
            this.t = (ImageView) view.findViewById(R.id.iv_icon);
            this.u = (TextView) view.findViewById(R.id.tv_name);
            this.v = (CheckBox) view.findViewById(R.id.checkbox);
            this.w = (TextView) view.findViewById(R.id.tv_date);
        }
    }

    public b(Context context, List<FileItem> list) {
        this.i = context;
        this.f3694d = list;
    }

    private int D(FileType fileType) {
        boolean zF = F();
        if (fileType != null) {
            switch (e.f3710a[fileType.ordinal()]) {
                case 1:
                case 2:
                    return zF ? R.drawable.icon_filemanager_file1_100 : R.drawable.icon_filemanager_file_48;
                case 3:
                    return zF ? R.drawable.icon_filemanager_word8_100 : R.drawable.icon_filemanager_word_48;
                case 4:
                    return zF ? R.drawable.icon_filemanager_txt_100 : R.drawable.icon_filemanager_txt_48;
                case 5:
                    return zF ? R.drawable.icon_filemanager_pdf_100 : R.drawable.icon_filemanager_pdf_48;
                case 6:
                    return zF ? R.drawable.icon_filemanager_excel9_100 : R.drawable.icon_filemanager_excel_48;
                case 7:
                    return zF ? R.drawable.icon_filemanager_ppt_100 : R.drawable.icon_filemanager_ppt_48;
                case 8:
                    return zF ? R.drawable.icon_filemanager_music2_100 : R.drawable.icon_filemanager_music_48;
                case 9:
                    return zF ? R.drawable.icon_filemanager_video6_100 : R.drawable.icon_filemanager_video_48;
                case 10:
                    return zF ? R.drawable.icon_filemanager_picture7_100 : R.drawable.icon_filemanager_picture_48;
                case 11:
                    return zF ? R.drawable.icon_filemanager_install4_100 : R.drawable.icon_filemanager_install_48;
                case 12:
                    return zF ? R.drawable.icon_filemanager_compress3_100 : R.drawable.icon_filemanager_compress_48;
            }
        }
        return zF ? R.drawable.icon_filemanager_unknown5_100 : R.drawable.icon_filemanager_unknown_48;
    }

    private String E(FileItem fileItem) {
        String strE = fileItem.E();
        return strE == null ? fileItem.C() : strE;
    }

    private boolean F() {
        return com.byd.filemanager.f.h().i() == 0;
    }

    private void G(ImageView imageView, FileItem fileItem, String str) {
        imageView.setImageResource(D(fileItem.G()));
        if (fileItem.G() == FileType.IMAGE) {
            if (TextUtils.isEmpty(fileItem.E())) {
                return;
            }
            com.bumptech.glide.g gVarF = com.bumptech.glide.c.t(this.i).s(Uri.fromFile(fileItem.w())).f(com.bumptech.glide.load.engine.h.f3022b);
            boolean zF = F();
            int i = R.drawable.icon_filemanager_picture7_100;
            com.bumptech.glide.g gVarN = gVarF.N(zF ? R.drawable.icon_filemanager_picture7_100 : R.drawable.icon_filemanager_picture_48);
            if (!F()) {
                i = R.drawable.icon_filemanager_picture_48;
            }
            gVarN.j(i).a(this.l).i0(new f(imageView, str));
            return;
        }
        if (fileItem.G() != FileType.VIDEO || TextUtils.isEmpty(fileItem.E())) {
            return;
        }
        com.bumptech.glide.g gVarF2 = com.bumptech.glide.c.t(this.i).t(new com.byd.filemanager.glide.f(fileItem.E())).f(com.bumptech.glide.load.engine.h.f3022b);
        boolean zF2 = F();
        int i2 = R.drawable.icon_filemanager_video6_100;
        com.bumptech.glide.g gVarN2 = gVarF2.N(zF2 ? R.drawable.icon_filemanager_video6_100 : R.drawable.icon_filemanager_video_48);
        if (!F()) {
            i2 = R.drawable.icon_filemanager_video_48;
        }
        gVarN2.j(i2).i0(new f(imageView, str));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: H, reason: merged with bridge method [inline-methods] */
    public void l(h hVar, int i) {
        FileItem fileItem = this.f3694d.get(i);
        String strE = E(fileItem);
        hVar.t.setTag(strE);
        fileItem.I();
        G(hVar.t, fileItem, strE);
        String strC = fileItem.C();
        if (this.k == null || strC == null) {
            hVar.u.setText(strC);
        } else {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(strC);
            int iIndexOf = strC.toLowerCase().indexOf(this.k.toLowerCase());
            if (iIndexOf >= 0) {
                int length = strC.length();
                int length2 = this.k.length() + iIndexOf;
                if (length2 <= length) {
                    length = length2;
                }
                if (com.byd.filemanager.f.h().v()) {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(this.i.getResources().getColor(R.color.text_main)), iIndexOf, length, 17);
                    hVar.u.setTextColor(this.i.getColor(R.color.text_item_file_date));
                } else {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(this.i.getResources().getColor(R.color.white)), iIndexOf, length, 17);
                    hVar.u.setTextColor(this.i.getColor(R.color.text_item_file_date_denza));
                }
                hVar.u.setText(spannableStringBuilder);
            } else {
                hVar.u.setText(strC);
            }
        }
        hVar.v.setVisibility((!this.g || fileItem.L()) ? 8 : 0);
        hVar.v.setChecked(fileItem.J());
        hVar.v.setOnClickListener(new a(fileItem, hVar));
        hVar.f1300b.setOnClickListener(new ViewOnClickListenerC0088b(hVar, fileItem));
        hVar.f1300b.setOnLongClickListener(new c(fileItem, hVar));
        if (this.h == 1) {
            File fileW = fileItem.w();
            if (fileW != null) {
                ThreadManager.h().k(new d(fileW, hVar));
            } else {
                hVar.w.setText("");
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
    public h n(ViewGroup viewGroup, int i) {
        return i == 0 ? new h(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_file_item, viewGroup, false)) : com.byd.filemanager.f.h().v() ? new h(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_file_item_row, viewGroup, false)) : new h(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_file_item_row_denza, viewGroup, false));
    }

    public void J(String str) {
        this.k = str;
    }

    public void K(boolean z) {
        this.j = z;
    }

    public void L(com.byd.filemanager.ui.a aVar) {
        this.f3695e = aVar;
    }

    public void M(g gVar) {
        this.f = gVar;
    }

    public void N(boolean z) {
        this.g = z;
        h();
    }

    public void O(int i) {
        this.h = i;
        h();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        List<FileItem> list = this.f3694d;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int e(int i) {
        return this.h;
    }
}
