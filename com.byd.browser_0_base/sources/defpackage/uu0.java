package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.chromium.chrome.R$layout;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableItemView;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class uu0 implements za1 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ uu0(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // defpackage.za1
    public final View a(ViewGroup viewGroup) {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                ((vu0) obj).getClass();
                SelectableItemView selectableItemView = (SelectableItemView) LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.history_cluster_visit, viewGroup, false);
                selectableItemView.setSelectionDelegate(null);
                return selectableItemView;
            case 1:
                return LayoutInflater.from(((g11) obj).a).inflate(R$layout.instance_switcher_cmd_item, (ViewGroup) null);
            default:
                return LayoutInflater.from(((t63) obj).a).inflate(R$layout.instance_switcher_item, (ViewGroup) null);
        }
    }
}
