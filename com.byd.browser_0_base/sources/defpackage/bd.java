package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.content.res.AppCompatResources;
import org.chromium.chrome.R$attr;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.components.browser_ui.widget.tile.TileView;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class bd implements za1 {
    public final /* synthetic */ int a;

    @Override // defpackage.za1
    public final View a(ViewGroup viewGroup) {
        switch (this.a) {
            case 0:
                Context context = viewGroup.getContext();
                TileView tileView = (TileView) LayoutInflater.from(context).inflate(R$layout.suggestions_tile_view, viewGroup, false);
                tileView.setClickable(true);
                tileView.setBackground(rr1.d(context, 3, R$attr.selectableItemBackground));
                if (pr1.e(context)) {
                    tileView.findViewById(R$id.tile_view_icon_background).setBackground(AppCompatResources.a(context, R$drawable.tile_view_icon_background_modern_updated));
                }
                return tileView;
            default:
                return LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.video_tutorial_large_card, viewGroup, false);
        }
    }
}
