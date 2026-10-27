package p;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.google.common.base.Optional;
import com.google.protobuf.InvalidProtocolBufferException;
import com.spotify.eventsender.FragmentsContainer;
import com.spotify.eventsender.internalerrors.EventSenderInternalError;
import com.spotify.music.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class gu0 {
    public Object a;
    public Object b;
    public Object c;
    public Object d;
    public Object e;
    public Object f;

    public gu0(int i) {
        if (i != 4 && i != 6) {
            this.a = new int[]{R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha};
            this.b = new int[]{R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_seekbar_tick_mark_material, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha};
            this.c = new int[]{R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material, R.drawable.abc_text_select_handle_left_mtrl, R.drawable.abc_text_select_handle_middle_mtrl, R.drawable.abc_text_select_handle_right_mtrl};
            this.d = new int[]{R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult};
            this.e = new int[]{R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material};
            this.f = new int[]{R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material, R.drawable.abc_btn_check_material_anim, R.drawable.abc_btn_radio_material_anim};
        }
    }

    public static boolean b(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static void f(File file) {
        if (file.exists() && o(file)) {
            file.getPath();
            Log.isLoggable("FirebaseCrashlytics", 3);
        }
    }

    public static ColorStateList g(Context context, int i) {
        int iC = ik2.c(context, R.attr.colorControlHighlight);
        return new ColorStateList(new int[][]{ik2.b, ik2.d, ik2.c, ik2.f}, new int[]{ik2.b(context, R.attr.colorButtonNormal), qu.b(iC, i), qu.b(iC, i), i});
    }

    public static LayerDrawable k(h42 h42Var, Context context, int i) {
        BitmapDrawable bitmapDrawable;
        BitmapDrawable bitmapDrawable2;
        BitmapDrawable bitmapDrawable3;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(i);
        Drawable drawableE = h42Var.e(context, R.drawable.abc_star_black_48dp);
        Drawable drawableE2 = h42Var.e(context, R.drawable.abc_star_half_black_48dp);
        if ((drawableE instanceof BitmapDrawable) && drawableE.getIntrinsicWidth() == dimensionPixelSize && drawableE.getIntrinsicHeight() == dimensionPixelSize) {
            bitmapDrawable = (BitmapDrawable) drawableE;
            bitmapDrawable2 = new BitmapDrawable(bitmapDrawable.getBitmap());
        } else {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawableE.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            drawableE.draw(canvas);
            bitmapDrawable = new BitmapDrawable(bitmapCreateBitmap);
            bitmapDrawable2 = new BitmapDrawable(bitmapCreateBitmap);
        }
        bitmapDrawable2.setTileModeX(Shader.TileMode.REPEAT);
        if ((drawableE2 instanceof BitmapDrawable) && drawableE2.getIntrinsicWidth() == dimensionPixelSize && drawableE2.getIntrinsicHeight() == dimensionPixelSize) {
            bitmapDrawable3 = (BitmapDrawable) drawableE2;
        } else {
            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(bitmapCreateBitmap2);
            drawableE2.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            drawableE2.draw(canvas2);
            bitmapDrawable3 = new BitmapDrawable(bitmapCreateBitmap2);
        }
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{bitmapDrawable, bitmapDrawable3, bitmapDrawable2});
        layerDrawable.setId(0, android.R.id.background);
        layerDrawable.setId(1, android.R.id.secondaryProgress);
        layerDrawable.setId(2, android.R.id.progress);
        return layerDrawable;
    }

    public static synchronized void n(File file) {
        try {
            if (file.exists()) {
                if (file.isDirectory()) {
                    return;
                }
                file.toString();
                Log.isLoggable("FirebaseCrashlytics", 3);
                file.delete();
            }
            if (!file.mkdirs()) {
                Log.e("FirebaseCrashlytics", "Could not create Crashlytics-specific directory: " + file, null);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public static boolean o(File file) {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                o(file2);
            }
        }
        return file.delete();
    }

    public static List p(Object[] objArr) {
        return objArr == null ? Collections.emptyList() : Arrays.asList(objArr);
    }

    public static void s(Drawable drawable, int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilterG;
        int[] iArr = cb0.a;
        Drawable drawableMutate = drawable.mutate();
        if (mode == null) {
            mode = u8.b;
        }
        PorterDuff.Mode mode2 = u8.b;
        synchronized (u8.class) {
            porterDuffColorFilterG = h42.g(i, mode);
        }
        drawableMutate.setColorFilter(porterDuffColorFilterG);
    }

    public final void a(String str, String str2) {
        Map map = (Map) this.f;
        if (map == null) {
            throw new IllegalStateException("Property \"autoMetadata\" has not been set");
        }
        map.put(str, str2);
    }

    public final vh c() {
        String strH = ((Integer) this.b) == null ? " batteryVelocity" : "";
        if (((Boolean) this.c) == null) {
            strH = strH.concat(" proximityOn");
        }
        if (((Integer) this.d) == null) {
            strH = u4.h(strH, " orientation");
        }
        if (((Long) this.e) == null) {
            strH = u4.h(strH, " ramUsed");
        }
        if (((Long) this.f) == null) {
            strH = u4.h(strH, " diskUsed");
        }
        if (strH.isEmpty()) {
            return new vh((Double) this.a, ((Integer) this.b).intValue(), ((Boolean) this.c).booleanValue(), ((Integer) this.d).intValue(), ((Long) this.e).longValue(), ((Long) this.f).longValue());
        }
        throw new IllegalStateException("Missing required properties:".concat(strH));
    }

    public final ai d() {
        String strH = ((String) this.a) == null ? " transportName" : "";
        if (((xd0) this.c) == null) {
            strH = strH.concat(" encodedPayload");
        }
        if (((Long) this.d) == null) {
            strH = u4.h(strH, " eventMillis");
        }
        if (((Long) this.e) == null) {
            strH = u4.h(strH, " uptimeMillis");
        }
        if (((Map) this.f) == null) {
            strH = u4.h(strH, " autoMetadata");
        }
        if (strH.isEmpty()) {
            return new ai((String) this.a, (Integer) this.b, (xd0) this.c, ((Long) this.d).longValue(), ((Long) this.e).longValue(), (Map) this.f);
        }
        throw new IllegalStateException("Missing required properties:".concat(strH));
    }

    public final void e() {
        try {
            if (((AtomicBoolean) this.f).compareAndSet(false, true)) {
                ArrayList<fi0> arrayListQ = ((kk0) this.a).q(((g90) this.c).e());
                LinkedList linkedList = new LinkedList();
                LinkedList linkedList2 = new LinkedList();
                for (fi0 fi0Var : arrayListQ) {
                    if (!((g90) this.c).e().equals(i(fi0Var))) {
                        linkedList.add(Long.valueOf(fi0Var.a));
                        linkedList2.add(fi0Var.d);
                    }
                }
                if (linkedList.size() > 999) {
                    ((oj0) ((pj0) this.e)).g(EventSenderInternalError.TOO_MANY_CLONED_EVENTS);
                }
                ((kk0) this.a).m(linkedList, linkedList2, (bk0) this.b);
            }
        } catch (Exception e) {
            ((u11) ((wc1) this.d)).y("Error when trying to delete cloned events", e);
        }
    }

    public final void h() {
        Messenger messenger;
        wf1 wf1Var = ((cg1) this.f).a;
        g90 g90Var = wf1Var.f;
        if (g90Var != null && (messenger = wf1Var.g) != null) {
            try {
                Message messageObtain = Message.obtain();
                messageObtain.what = 7;
                messageObtain.arg1 = 1;
                messageObtain.setData(null);
                messageObtain.replyTo = messenger;
                ((Messenger) g90Var.e).send(messageObtain);
            } catch (RemoteException unused) {
            }
        }
        wf1Var.b.disconnect();
        ((BroadcastReceiver.PendingResult) this.e).finish();
    }

    public final String i(fi0 fi0Var) {
        String str = fi0Var.h;
        if (str != null) {
            return str;
        }
        FragmentsContainer fragmentsContainerG = FragmentsContainer.g();
        try {
            fragmentsContainerG = FragmentsContainer.j(fi0Var.f);
        } catch (InvalidProtocolBufferException e) {
            ((u11) ((wc1) this.d)).y("Error in parsing contexts.", e);
        }
        com.google.common.collect.a aVarE = com.google.common.collect.a.a(fragmentsContainerG.h()).e(new lj(4));
        lj ljVar = new lj(2);
        Iterable iterableB = aVarE.b();
        iterableB.getClass();
        Iterator it = com.google.common.collect.a.a(new g61(iterableB, ljVar, 0)).b().iterator();
        return (String) (it.hasNext() ? Optional.e(it.next()) : Optional.a()).i(new lj(5)).g("");
    }

    public final File j(String str) {
        File file = new File((File) this.c, str);
        file.mkdirs();
        File file2 = new File(file, "native");
        file2.mkdirs();
        return file2;
    }

    public final File l(String str, String str2) {
        File file = new File((File) this.c, str);
        file.mkdirs();
        return new File(file, str2);
    }

    public final ColorStateList m(Context context, int i) {
        if (i == R.drawable.abc_edit_text_material) {
            return l13.d(context, R.color.abc_tint_edittext);
        }
        if (i == R.drawable.abc_switch_track_mtrl_alpha) {
            return l13.d(context, R.color.abc_tint_switch_track);
        }
        if (i != R.drawable.abc_switch_thumb_material) {
            if (i == R.drawable.abc_btn_default_mtrl_shape) {
                return g(context, ik2.c(context, R.attr.colorButtonNormal));
            }
            if (i == R.drawable.abc_btn_borderless_material) {
                return g(context, 0);
            }
            if (i == R.drawable.abc_btn_colored_material) {
                return g(context, ik2.c(context, R.attr.colorAccent));
            }
            if (i == R.drawable.abc_spinner_mtrl_am_alpha || i == R.drawable.abc_spinner_textfield_background_material) {
                return l13.d(context, R.color.abc_tint_spinner);
            }
            if (b((int[]) this.b, i)) {
                return ik2.d(context, R.attr.colorControlNormal);
            }
            if (b((int[]) this.e, i)) {
                return l13.d(context, R.color.abc_tint_default);
            }
            if (b((int[]) this.f, i)) {
                return l13.d(context, R.color.abc_tint_btn_checkable);
            }
            if (i == R.drawable.abc_seekbar_thumb_material) {
                return l13.d(context, R.color.abc_tint_seek_thumb);
            }
            return null;
        }
        int[][] iArr = new int[3][];
        int[] iArr2 = new int[3];
        ColorStateList colorStateListD = ik2.d(context, R.attr.colorSwitchThumbNormal);
        if (colorStateListD == null || !colorStateListD.isStateful()) {
            iArr[0] = ik2.b;
            iArr2[0] = ik2.b(context, R.attr.colorSwitchThumbNormal);
            iArr[1] = ik2.e;
            iArr2[1] = ik2.c(context, R.attr.colorControlActivated);
            iArr[2] = ik2.f;
            iArr2[2] = ik2.c(context, R.attr.colorSwitchThumbNormal);
        } else {
            int[] iArr3 = ik2.b;
            iArr[0] = iArr3;
            iArr2[0] = colorStateListD.getColorForState(iArr3, 0);
            iArr[1] = ik2.e;
            iArr2[1] = ik2.c(context, R.attr.colorControlActivated);
            iArr[2] = ik2.f;
            iArr2[2] = colorStateListD.getDefaultColor();
        }
        return new ColorStateList(iArr, iArr2);
    }

    public final void q(String str, String str2) {
        aa0 aa0Var = (aa0) this.d;
        synchronized (aa0Var) {
            try {
                if (((o81) ((AtomicMarkableReference) aa0Var.b).getReference()).b(str, str2)) {
                    Object obj = aa0Var.b;
                    ((AtomicMarkableReference) obj).set((o81) ((AtomicMarkableReference) obj).getReference(), true);
                    c31 c31Var = new c31(2, aa0Var);
                    AtomicReference atomicReference = (AtomicReference) aa0Var.c;
                    while (!atomicReference.compareAndSet(null, c31Var)) {
                        if (atomicReference.get() != null) {
                            return;
                        }
                    }
                    ((gh2) ((gu0) aa0Var.d).b).x(c31Var);
                }
            } finally {
            }
        }
    }

    public final void r(xd0 xd0Var) {
        if (xd0Var == null) {
            throw new NullPointerException("Null encodedPayload");
        }
        this.c = xd0Var;
    }

    public gu0(Context context) {
        String str;
        this.a = context.getFilesDir();
        if (Build.VERSION.SDK_INT >= 28) {
            str = ".com.google.firebase.crashlytics.files.v2" + File.pathSeparator + Application.getProcessName().replaceAll("[^a-zA-Z0-9.]", "_");
        } else {
            str = ".com.google.firebase.crashlytics.files.v1";
        }
        File file = new File((File) this.a, str);
        n(file);
        this.b = file;
        File file2 = new File((File) this.b, "open-sessions");
        n(file2);
        this.c = file2;
        File file3 = new File((File) this.b, "reports");
        n(file3);
        this.d = file3;
        File file4 = new File((File) this.b, "priority-reports");
        n(file4);
        this.e = file4;
        File file5 = new File((File) this.b, "native-reports");
        n(file5);
        this.f = file5;
    }

    public gu0(String str, gu0 gu0Var, gh2 gh2Var) {
        this.d = new aa0(this, false);
        this.e = new aa0(this, true);
        this.f = new AtomicMarkableReference(null, false);
        this.c = str;
        this.a = new ak1(gu0Var);
        this.b = gh2Var;
    }
}
