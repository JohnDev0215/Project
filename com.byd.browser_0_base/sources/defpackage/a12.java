package defpackage;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import org.chromium.base.ThreadUtils;
import org.chromium.components.browser_ui.photo_picker.PickerBitmapView;
import org.chromium.components.browser_ui.photo_picker.PickerCategoryView;
import org.chromium.components.browser_ui.photo_picker.R$layout;
import org.chromium.components.browser_ui.photo_picker.c;
import org.chromium.components.browser_ui.photo_picker.i;

/* JADX INFO: compiled from: PickerAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a12 extends RecyclerView.e<RecyclerView.a0> {
    public final PickerCategoryView h;
    public int i;
    public int j;

    public a12(PickerCategoryView pickerCategoryView) {
        this.h = pickerCategoryView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int k() {
        return this.h.getPickerBitmaps().size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void t(RecyclerView.a0 a0Var, int i) {
        if (a0Var instanceof i) {
            i iVar = (i) a0Var;
            PickerCategoryView pickerCategoryView = this.h;
            iVar.y = pickerCategoryView;
            b12 b12Var = pickerCategoryView.getPickerBitmaps().get(i);
            iVar.A = b12Var;
            int i2 = b12Var.g;
            char c = 0;
            if (i2 == 1 || i2 == 2) {
                iVar.z.q(b12Var, null, null, false, -1.0f);
            } else {
                String path = b12Var.e.getPath();
                PickerCategoryView pickerCategoryView2 = iVar.y;
                PickerCategoryView.b bVar = pickerCategoryView2.w ? pickerCategoryView2.getFullScreenBitmaps().get(path) : pickerCategoryView2.getHighResThumbnails().get(path);
                if (bVar != null) {
                    iVar.z.q(iVar.A, bVar.a, bVar.b, false, bVar.c);
                    c = 1;
                } else {
                    int imageWidth = iVar.y.getImageWidth();
                    PickerCategoryView pickerCategoryView3 = iVar.y;
                    PickerCategoryView.b bVar2 = pickerCategoryView3.w ? pickerCategoryView3.getHighResThumbnails().get(path) : null;
                    if (bVar2 == null) {
                        bVar2 = iVar.y.getLowResThumbnails().get(path);
                    }
                    if (bVar2 != null) {
                        Bitmap bitmap = bVar2.a.get(0);
                        long jElapsedRealtime = SystemClock.elapsedRealtime();
                        Bitmap bitmapD = af.d(bitmap, imageWidth);
                        oj2.b0(SystemClock.elapsedRealtime() - jElapsedRealtime, "Android.PhotoPicker.UpscaleLowResBitmap");
                        ArrayList arrayList = new ArrayList(1);
                        arrayList.add(bitmapD);
                        iVar.z.q(iVar.A, arrayList, bVar2.b, true, bVar2.c);
                    } else {
                        iVar.z.q(iVar.A, null, null, true, -1.0f);
                    }
                    c decoderServiceHost = iVar.y.getDecoderServiceHost();
                    b12 b12Var2 = iVar.A;
                    Uri uri = b12Var2.e;
                    int i3 = b12Var2.g;
                    boolean z = iVar.y.w;
                    decoderServiceHost.getClass();
                    Object obj = ThreadUtils.a;
                    decoderServiceHost.t.add(new c.b(uri, imageWidth, z, i3, iVar));
                    if (decoderServiceHost.o == null) {
                        decoderServiceHost.C();
                    }
                    c = 2;
                }
            }
            if (c == 1) {
                this.i++;
            } else if (c == 2) {
                this.j++;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final RecyclerView.a0 v(int i, RecyclerView recyclerView) {
        PickerBitmapView pickerBitmapView = (PickerBitmapView) LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.photo_picker_bitmap_view, (ViewGroup) recyclerView, false);
        pickerBitmapView.setCategoryView(this.h);
        return new i(pickerBitmapView);
    }
}
