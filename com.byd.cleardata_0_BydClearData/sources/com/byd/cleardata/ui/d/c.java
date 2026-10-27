package com.byd.cleardata.ui.d;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.j;
import com.byd.cleardata.R;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class c extends RecyclerView.g<h> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private SimpleDateFormat f2757c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<com.byd.cleardata.d.d> f2758d;
    private com.byd.cleardata.ui.c<com.byd.cleardata.d.d> e;
    private g f;
    private boolean g;
    private int h;
    private Context i;
    private boolean j = true;
    private String k;

    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.byd.cleardata.d.d f2759b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ h f2760c;

        a(com.byd.cleardata.d.d dVar, h hVar) {
            this.f2759b = dVar;
            this.f2760c = hVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f2759b.P(this.f2760c.v.isChecked());
            if (c.this.e != null) {
                c.this.e.a(this.f2759b, this.f2760c.j());
            }
        }
    }

    class b implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ h f2762b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.byd.cleardata.d.d f2763c;

        b(h hVar, com.byd.cleardata.d.d dVar) {
            this.f2762b = hVar;
            this.f2763c = dVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f2762b.v.getVisibility() == 0) {
                this.f2762b.v.setChecked(!r3.isChecked());
                this.f2763c.P(this.f2762b.v.isChecked());
            }
            if (c.this.e != null) {
                c.this.e.a(this.f2763c, this.f2762b.j());
            }
        }
    }

    /* JADX INFO: renamed from: com.byd.cleardata.ui.d.c$c, reason: collision with other inner class name */
    class ViewOnLongClickListenerC0097c implements View.OnLongClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.byd.cleardata.d.d f2765b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ h f2766c;

        ViewOnLongClickListenerC0097c(com.byd.cleardata.d.d dVar, h hVar) {
            this.f2765b = dVar;
            this.f2766c = hVar;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            c.this.j = !this.f2765b.L();
            com.byd.cleardata.i.c.b("isMultiSelect = " + c.this.j);
            if (c.this.j && (c.this.f == null || c.this.f.a(this.f2766c.v, this.f2765b))) {
                c.this.O(true);
                c.this.i(this.f2766c.j());
            }
            return true;
        }
    }

    class d implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ File f2768b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ h f2769c;

        class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ String f2771b;

            a(String str) {
                this.f2771b = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                d.this.f2769c.w.setText(this.f2771b);
            }
        }

        d(File file, h hVar) {
            this.f2768b = file;
            this.f2769c = hVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.f2757c = new SimpleDateFormat(c.this.i.getString(R.string.date_format), com.byd.cleardata.i.a.b(c.this.i));
            this.f2769c.w.post(new a(c.this.f2757c.format(new Date(this.f2768b.lastModified()))));
        }
    }

    static /* synthetic */ class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2773a;

        static {
            int[] iArr = new int[com.byd.cleardata.d.f.values().length];
            f2773a = iArr;
            try {
                iArr[com.byd.cleardata.d.f.DIRECTORY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2773a[com.byd.cleardata.d.f.DOWNLOAD_DIRECTORY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2773a[com.byd.cleardata.d.f.WORD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2773a[com.byd.cleardata.d.f.TXT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2773a[com.byd.cleardata.d.f.PDF.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2773a[com.byd.cleardata.d.f.EXCEL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2773a[com.byd.cleardata.d.f.PPT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f2773a[com.byd.cleardata.d.f.AUDIO.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f2773a[com.byd.cleardata.d.f.VIDEO.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f2773a[com.byd.cleardata.d.f.IMAGE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f2773a[com.byd.cleardata.d.f.APK.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f2773a[com.byd.cleardata.d.f.COMPRESS.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f2773a[com.byd.cleardata.d.f.UNKNOWN.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    private static class f extends com.bumptech.glide.r.j.b<ImageView, Drawable> {
        private final String h;

        public f(ImageView imageView, String str) {
            super(imageView);
            this.h = str;
        }

        @Override // com.bumptech.glide.r.j.e
        public void e(Drawable drawable) {
            com.byd.cleardata.i.c.b("onLoadFailed");
        }

        @Override // com.bumptech.glide.r.j.b
        protected void o(Drawable drawable) {
        }

        @Override // com.bumptech.glide.r.j.e
        /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
        public void d(Drawable drawable, com.bumptech.glide.r.k.b<? super Drawable> bVar) {
            String str = (String) ((ImageView) this.f2521d).getTag();
            if (str == null || str.equals(this.h)) {
                try {
                    ((ImageView) this.f2521d).setImageDrawable(drawable);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    public interface g {
        boolean a(View view, com.byd.cleardata.d.d dVar);
    }

    protected static class h extends RecyclerView.d0 {
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

    public c(Context context, List<com.byd.cleardata.d.d> list) {
        this.i = context;
        this.f2758d = list;
    }

    private int E(com.byd.cleardata.d.f fVar) {
        boolean zG = G();
        if (fVar != null) {
            switch (e.f2773a[fVar.ordinal()]) {
                case 1:
                case 2:
                    return zG ? R.drawable.icon_filemanager_file1_100 : R.drawable.icon_filemanager_file_48;
                case 3:
                    return zG ? R.drawable.icon_filemanager_word8_100 : R.drawable.icon_filemanager_word_48;
                case 4:
                    return zG ? R.drawable.icon_filemanager_txt_100 : R.drawable.icon_filemanager_txt_48;
                case 5:
                    return zG ? R.drawable.icon_filemanager_pdf_100 : R.drawable.icon_filemanager_pdf_48;
                case 6:
                    return zG ? R.drawable.icon_filemanager_excel9_100 : R.drawable.icon_filemanager_excel_48;
                case 7:
                    return zG ? R.drawable.icon_filemanager_ppt_100 : R.drawable.icon_filemanager_ppt_48;
                case 8:
                    return zG ? R.drawable.icon_filemanager_music2_100 : R.drawable.icon_filemanager_music_48;
                case 9:
                    return zG ? R.drawable.icon_filemanager_video6_100 : R.drawable.icon_filemanager_video_48;
                case 10:
                    return zG ? R.drawable.icon_filemanager_picture7_100 : R.drawable.icon_filemanager_picture_48;
                case 11:
                    return zG ? R.drawable.icon_filemanager_install4_100 : R.drawable.icon_filemanager_install_48;
                case 12:
                    return zG ? R.drawable.icon_filemanager_compress3_100 : R.drawable.icon_filemanager_compress_48;
            }
        }
        return zG ? R.drawable.icon_filemanager_unknown5_100 : R.drawable.icon_filemanager_unknown_48;
    }

    private String F(com.byd.cleardata.d.d dVar) {
        String strE = dVar.E();
        return strE == null ? dVar.C() : strE;
    }

    private boolean G() {
        return com.byd.cleardata.b.h().i() == 0;
    }

    private void H(ImageView imageView, com.byd.cleardata.d.d dVar, String str) {
        j jVarF;
        f fVar;
        imageView.setImageResource(E(dVar.G()));
        if (dVar.G() == com.byd.cleardata.d.f.IMAGE) {
            j jVarE = com.bumptech.glide.c.t(this.i).s(Uri.fromFile(dVar.w())).e(com.bumptech.glide.load.n.j.f2142b);
            boolean zG = G();
            int i = R.drawable.icon_filemanager_picture7_100;
            j jVarJ = jVarE.J(zG ? R.drawable.icon_filemanager_picture7_100 : R.drawable.icon_filemanager_picture_48);
            if (!G()) {
                i = R.drawable.icon_filemanager_picture_48;
            }
            jVarF = (j) jVarJ.f(i);
            fVar = new f(imageView, str);
        } else {
            if (dVar.G() != com.byd.cleardata.d.f.VIDEO) {
                return;
            }
            j jVarE2 = com.bumptech.glide.c.t(this.i).t(new com.byd.cleardata.glide.f(dVar.E())).e(com.bumptech.glide.load.n.j.f2142b);
            boolean zG2 = G();
            int i2 = R.drawable.icon_filemanager_video6_100;
            j jVarJ2 = jVarE2.J(zG2 ? R.drawable.icon_filemanager_video6_100 : R.drawable.icon_filemanager_video_48);
            if (!G()) {
                i2 = R.drawable.icon_filemanager_video_48;
            }
            jVarF = jVarJ2.f(i2);
            fVar = new f(imageView, str);
        }
        jVarF.d0(fVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x005a  */
    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m(com.byd.cleardata.ui.d.c.h r6, int r7) {
        /*
            r5 = this;
            java.util.List<com.byd.cleardata.d.d> r0 = r5.f2758d
            java.lang.Object r7 = r0.get(r7)
            com.byd.cleardata.d.d r7 = (com.byd.cleardata.d.d) r7
            java.lang.String r0 = r5.F(r7)
            android.widget.ImageView r1 = r6.t
            r1.setTag(r0)
            r7.I()
            android.widget.ImageView r1 = r6.t
            r5.H(r1, r7, r0)
            java.lang.String r0 = r7.C()
            java.lang.String r1 = r5.k
            if (r1 == 0) goto L5a
            android.text.SpannableStringBuilder r1 = new android.text.SpannableStringBuilder
            r1.<init>(r0)
            java.lang.String r2 = r0.toLowerCase()
            java.lang.String r3 = r5.k
            java.lang.String r3 = r3.toLowerCase()
            int r2 = r2.indexOf(r3)
            if (r2 < 0) goto L5a
            android.text.style.ForegroundColorSpan r0 = new android.text.style.ForegroundColorSpan
            android.content.Context r3 = r5.i
            android.content.res.Resources r3 = r3.getResources()
            r4 = 2131099697(0x7f060031, float:1.7811755E38)
            int r3 = r3.getColor(r4)
            r0.<init>(r3)
            java.lang.String r3 = r5.k
            int r3 = r3.length()
            int r3 = r3 + r2
            r4 = 17
            r1.setSpan(r0, r2, r3, r4)
            android.widget.TextView r0 = r6.u
            r0.setText(r1)
            goto L5f
        L5a:
            android.widget.TextView r1 = r6.u
            r1.setText(r0)
        L5f:
            android.widget.CheckBox r0 = r6.v
            boolean r1 = r5.g
            if (r1 == 0) goto L6d
            boolean r1 = r7.L()
            if (r1 != 0) goto L6d
            r1 = 0
            goto L6f
        L6d:
            r1 = 8
        L6f:
            r0.setVisibility(r1)
            android.widget.CheckBox r0 = r6.v
            boolean r1 = r7.J()
            r0.setChecked(r1)
            android.widget.CheckBox r0 = r6.v
            com.byd.cleardata.ui.d.c$a r1 = new com.byd.cleardata.ui.d.c$a
            r1.<init>(r7, r6)
            r0.setOnClickListener(r1)
            android.view.View r0 = r6.f1454b
            com.byd.cleardata.ui.d.c$b r1 = new com.byd.cleardata.ui.d.c$b
            r1.<init>(r6, r7)
            r0.setOnClickListener(r1)
            android.view.View r0 = r6.f1454b
            com.byd.cleardata.ui.d.c$c r1 = new com.byd.cleardata.ui.d.c$c
            r1.<init>(r7, r6)
            r0.setOnLongClickListener(r1)
            int r0 = r5.h
            r1 = 1
            if (r0 != r1) goto Lb8
            java.io.File r7 = r7.w()
            if (r7 == 0) goto Lb1
            com.byd.cleardata.ThreadManager r0 = com.byd.cleardata.ThreadManager.h()
            com.byd.cleardata.ui.d.c$d r1 = new com.byd.cleardata.ui.d.c$d
            r1.<init>(r7, r6)
            r0.j(r1)
            goto Lb8
        Lb1:
            android.widget.TextView r6 = r6.w
            java.lang.String r7 = ""
            r6.setText(r7)
        Lb8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.cleardata.ui.d.c.m(com.byd.cleardata.ui.d.c$h, int):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: J, reason: merged with bridge method [inline-methods] */
    public h o(ViewGroup viewGroup, int i) {
        return i == 0 ? new h(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_file_item, viewGroup, false)) : new h(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_file_item_row, viewGroup, false));
    }

    public void K(String str) {
        this.k = str;
    }

    public void L(boolean z) {
        this.j = z;
    }

    public void M(com.byd.cleardata.ui.c cVar) {
        this.e = cVar;
    }

    public void N(g gVar) {
        this.f = gVar;
    }

    public void O(boolean z) {
        this.g = z;
        h();
    }

    public void P(int i) {
        this.h = i;
        h();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        List<com.byd.cleardata.d.d> list = this.f2758d;
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
