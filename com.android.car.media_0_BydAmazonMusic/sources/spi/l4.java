package spi;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.car.media.main.R$id;
import com.android.car.ui.baselayout.Insets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: GuidelinesUpdater.java */
/* JADX INFO: loaded from: classes.dex */
public final class l4 implements z4 {
    public final View a;
    public final Set<z4> b = new HashSet();

    public l4(View view) {
        this.a = view;
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.HashSet, java.util.Set<spi.z4>] */
    @Override // spi.z4
    public final void b(@NonNull Insets insets) {
        View viewFindViewById = this.a.findViewById(R$id.ui_content_start_guideline);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) viewFindViewById.getLayoutParams();
        layoutParams.setMargins(insets.getLeft(), 0, 0, 0);
        viewFindViewById.setLayoutParams(layoutParams);
        View viewFindViewById2 = this.a.findViewById(R$id.ui_content_end_guideline);
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) viewFindViewById2.getLayoutParams();
        layoutParams2.setMargins(0, 0, insets.getRight(), 0);
        viewFindViewById2.setLayoutParams(layoutParams2);
        View viewFindViewById3 = this.a.findViewById(R$id.ui_content_top_guideline);
        ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) viewFindViewById3.getLayoutParams();
        layoutParams3.setMargins(0, insets.getTop(), 0, 0);
        viewFindViewById3.setLayoutParams(layoutParams3);
        View viewFindViewById4 = this.a.findViewById(R$id.ui_content_bottom_guideline);
        ConstraintLayout.LayoutParams layoutParams4 = (ConstraintLayout.LayoutParams) viewFindViewById4.getLayoutParams();
        layoutParams4.setMargins(0, 0, 0, insets.getBottom());
        viewFindViewById4.setLayoutParams(layoutParams4);
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((z4) it.next()).b(insets);
        }
    }
}
