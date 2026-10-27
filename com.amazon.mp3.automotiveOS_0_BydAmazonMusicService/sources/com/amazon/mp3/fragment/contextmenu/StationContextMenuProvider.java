package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.auto.carmode.CarModePresetsUtility;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.search.model.Station;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.StationShareProvider;

/* JADX INFO: loaded from: classes3.dex */
public class StationContextMenuProvider extends ContextMenuProvider<Station> {
    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected int getContextMenuLayoutId() {
        return R.menu.station_context;
    }

    public StationContextMenuProvider(BaseActivity activity) {
        init(activity);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected void updateContextMenuItems(Activity activity, ContextMenu menu, Object tag, int position, String sourceID) {
        Station station = (Station) tag;
        setStyledHeaderTitle(station.getTitle(), menu);
        updatePresetsContextMenu(activity, station, menu, R.id.MenuStationAddToPresets, R.id.MenuStationRemoveFromPresets);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, AdapterView.AdapterContextMenuInfo menuInfo, String sourceID) {
        return onContextMenuItemSelected(activity, item, (Station) menuInfo.targetView.getTag(), sourceID);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, Object tag, String sourceID) {
        return onContextMenuItemSelected(activity, item, (Station) tag, sourceID);
    }

    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, Station station, String sourceID) {
        setClickedItemHolder(station);
        int itemId = item.getItemId();
        setClickedEntityIdAndType(station.getAsin(), EntityIdType.ASIN);
        switch (itemId) {
            case R.id.MenuStationAddToPresets /* 2131427642 */:
                CarModePresetsUtility.addToPresets(activity, station, getPageType());
                break;
            case R.id.MenuStationRemoveFromPresets /* 2131427644 */:
                CarModePresetsUtility.removeFromPresets(activity, station, getPageType());
                break;
            case R.id.MenuStationShare /* 2131427645 */:
                new StationShareProvider(activity, EndpointsProvider.get().getMusicDomain(), station.getKey(), station.getTitle(), station.getArtworkUri()).startShare();
                break;
        }
        return true;
    }
}
