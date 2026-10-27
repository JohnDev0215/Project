package p;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.spotify.automotive.baseui.settings.MusicQuality;
import com.spotify.music.R;

/* JADX INFO: loaded from: classes.dex */
public final class h6 extends ArrayAdapter {
    public final /* synthetic */ int c = 1;

    public h6(Context context) {
        super(context, R.layout.settings_dropdown_item, MusicQuality.n);
    }

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public final View getDropDownView(int i, View view, ViewGroup viewGroup) {
        switch (this.c) {
            case 1:
                View dropDownView = super.getDropDownView(i, view, viewGroup);
                MusicQuality musicQuality = (MusicQuality) getItem(i);
                if (musicQuality != null) {
                    ((TextView) dropDownView.findViewById(android.R.id.text1)).setText(musicQuality.b());
                }
                return dropDownView;
            default:
                return super.getDropDownView(i, view, viewGroup);
        }
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final long getItemId(int i) {
        switch (this.c) {
            case 0:
                return i;
            default:
                return super.getItemId(i);
        }
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        switch (this.c) {
            case 1:
                View view2 = super.getView(i, view, viewGroup);
                MusicQuality musicQuality = (MusicQuality) getItem(i);
                if (musicQuality != null) {
                    ((TextView) view2.findViewById(android.R.id.text1)).setText(musicQuality.b());
                }
                return view2;
            default:
                return super.getView(i, view, viewGroup);
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final boolean hasStableIds() {
        switch (this.c) {
            case 0:
                return true;
            default:
                return super.hasStableIds();
        }
    }

    public h6(Context context, int i) {
        super(context, i, android.R.id.text1, (Object[]) null);
    }
}
