package org.chromium.chrome.browser.content_creation.notes;

import J.N;
import android.app.Activity;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import defpackage.bq;
import defpackage.dx0;
import defpackage.eo1;
import defpackage.er;
import defpackage.fo1;
import defpackage.fx0;
import defpackage.go1;
import defpackage.gt0;
import defpackage.jk2;
import defpackage.ke0;
import defpackage.md3;
import defpackage.oj2;
import defpackage.rq;
import defpackage.sk;
import defpackage.so;
import defpackage.wv;
import defpackage.ya1;
import java.text.DateFormat;
import java.util.Date;
import org.chromium.chrome.browser.content_creation.internal.R;
import org.chromium.chrome.browser.profiles.ProfileKey;
import org.chromium.components.browser_ui.share.a;
import org.chromium.components.content_creation.notes.models.NoteTemplate;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: NoteCreationCoordinatorImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements md3 {
    public final Activity a;
    public final WindowAndroid b;
    public final ya1 c;
    public final NoteCreationDialog d;
    public final rq e;
    public final String f;
    public final String g;
    public long h;

    /* JADX INFO: renamed from: org.chromium.chrome.browser.content_creation.notes.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: NoteCreationCoordinatorImpl.java */
    public class C0075a implements a.b {
        public C0075a() {
        }

        @Override // org.chromium.components.browser_ui.share.a.b
        public final void a(ComponentName componentName) {
            a aVar = a.this;
            aVar.getClass();
            oj2.Y(System.currentTimeMillis() - aVar.h, "NoteCreation.TimeTo.ShareCreation");
            oj2.O("NoteCreation.NoteShared", true);
            oj2.U(2, 3, "NoteCreation.Funnel");
            oj2.U(!componentName.equals(er.p) ? 1 : 0, 2, "NoteCreation.ShareDestination");
        }

        @Override // org.chromium.components.browser_ui.share.a.b
        public final void onCancel() {
            a aVar = a.this;
            aVar.getClass();
            oj2.Y(System.currentTimeMillis() - aVar.h, "NoteCreation.TimeTo.DismissShare");
            oj2.O("NoteCreation.NoteShared", false);
        }
    }

    public a(Activity activity, WindowAndroid windowAndroid, go1 go1Var, rq rqVar, String str, String str2, String str3) {
        this.a = activity;
        this.b = windowAndroid;
        this.e = rqVar;
        this.f = str;
        this.g = str3;
        String strA = a(str3);
        ya1 ya1Var = new ya1();
        this.c = ya1Var;
        new eo1(ya1Var, new gt0(activity), go1Var, new fx0(dx0.b(1, ProfileKey.a())));
        String strMpICpYBr = N.MpICpYBr(new GURL(str));
        NoteCreationDialog noteCreationDialog = new NoteCreationDialog();
        this.d = noteCreationDialog;
        ke0 ke0Var = new ke0(9, this);
        wv wvVar = new wv(28, this);
        noteCreationDialog.y0 = ke0Var;
        noteCreationDialog.q0 = strMpICpYBr;
        noteCreationDialog.r0 = str2;
        noteCreationDialog.s0 = strA;
        noteCreationDialog.w0 = true;
        noteCreationDialog.x0 = wvVar;
    }

    public final String a(String str) {
        return this.a.getString(R.string.quotation_mark_prefix) + str + this.a.getString(R.string.quotation_mark_suffix);
    }

    public final void b() {
        ya1 ya1Var = this.c;
        if (ya1Var.size() == 0) {
            return;
        }
        NoteCreationDialog noteCreationDialog = this.d;
        int i = noteCreationDialog.t0;
        long jCurrentTimeMillis = System.currentTimeMillis() - this.h;
        int i2 = noteCreationDialog.v0;
        int i3 = ((NoteTemplate) ya1Var.get(i).b.j(fo1.c)).a;
        oj2.Y(jCurrentTimeMillis, "NoteCreation.TimeTo.SelectTemplate");
        oj2.U(1, 3, "NoteCreation.Funnel");
        oj2.O("NoteCreation.CreationStatus", true);
        oj2.Q(i2, "NoteCreation.NumberOfTemplateChanges");
        sk skVar = bq.a;
        if (N.M09VlOh_("WebNotesDynamicTemplates")) {
            oj2.U(i3, 50, "NoteCreation.SelectedDynamicTemplateID");
        } else {
            if (i3 >= 11) {
                i3 = 0;
            }
            oj2.U(i3, 11, "NoteCreation.SelectedTemplate");
        }
        if (N.M09VlOh_("WebNotesDynamicTemplates")) {
            oj2.U(i, 50, "NoteCreation.SelectedDynamicTemplateIndex");
        }
        View viewV0 = noteCreationDialog.V0(i);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(viewV0.getWidth(), viewV0.getHeight(), Bitmap.Config.ARGB_8888);
        viewV0.draw(new Canvas(bitmapCreateBitmap));
        jk2.b(c(), bitmapCreateBitmap, new so(15, this));
    }

    public final String c() {
        return this.a.getString(R.string.content_creation_note_filename_prefix);
    }

    public final String d() {
        return this.a.getString(R.string.content_creation_note_title_for_share, DateFormat.getDateInstance(3, this.a.getResources().getConfiguration().getLocales().get(0)).format(new Date(System.currentTimeMillis())));
    }
}
