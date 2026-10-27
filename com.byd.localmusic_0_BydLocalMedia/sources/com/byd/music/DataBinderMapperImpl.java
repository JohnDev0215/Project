package com.byd.music;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.byd.music.databinding.ActivityEpgReplaceDialogBindingImpl;
import com.byd.music.databinding.ActivityLocalDialogBindingImpl;
import com.byd.music.databinding.ActivityLocalDialogConfirmBindingImpl;
import com.byd.music.databinding.ActivityMediaMainBindingImpl;
import com.byd.music.databinding.ActivityOpenBtBindingImpl;
import com.byd.music.databinding.FragmentAmBindingImpl;
import com.byd.music.databinding.FragmentDabBindingImpl;
import com.byd.music.databinding.FragmentDabSettingBindingImpl;
import com.byd.music.databinding.FragmentEpgListBindingImpl;
import com.byd.music.databinding.FragmentFmBindingImpl;
import com.byd.music.databinding.FragmentHolderBindingImpl;
import com.byd.music.databinding.FragmentLocalMusicBindingImpl;
import com.byd.music.databinding.FragmentLocalMusiclistBindingImpl;
import com.byd.music.databinding.FragmentLocalRootMusicBindingImpl;
import com.byd.music.databinding.FragmentLocalSdBindingImpl;
import com.byd.music.databinding.FragmentLocalSdusbMusicBindingImpl;
import com.byd.music.databinding.FragmentLocalSearchBindingImpl;
import com.byd.music.databinding.FragmentLocalSearchBindingSw580dpImpl;
import com.byd.music.databinding.FragmentLocalSearchBindingSw720dpImpl;
import com.byd.music.databinding.FragmentLocalSearchBindingSw720dpPortImpl;
import com.byd.music.databinding.FragmentLocalUsbBindingImpl;
import com.byd.music.databinding.FragmentMusicBluetoothBindingImpl;
import com.byd.music.databinding.FragmentTaSettingBindingImpl;
import com.byd.music.databinding.ItemAmCollectBindingImpl;
import com.byd.music.databinding.ItemArtistSearchBindingImpl;
import com.byd.music.databinding.ItemArtistSearchBindingSw580dpImpl;
import com.byd.music.databinding.ItemArtistSearchBindingSw720dpImpl;
import com.byd.music.databinding.ItemCollectMusiclistLocalMusicBindingImpl;
import com.byd.music.databinding.ItemCollectMusiclistLocalSdBindingImpl;
import com.byd.music.databinding.ItemCollectMusiclistLocalSdUsbBindingImpl;
import com.byd.music.databinding.ItemCollectMusiclistLocalUsbBindingImpl;
import com.byd.music.databinding.ItemDabPtyBindingImpl;
import com.byd.music.databinding.ItemDabStationBindingImpl;
import com.byd.music.databinding.ItemEpgCurrentBindingImpl;
import com.byd.music.databinding.ItemEpgDabListBindingImpl;
import com.byd.music.databinding.ItemFmCollectBindingImpl;
import com.byd.music.databinding.ItemGoneBindingImpl;
import com.byd.music.databinding.ItemLocalmusiclistBindingImpl;
import com.byd.music.databinding.ItemMusicListLocalSearchBindingImpl;
import com.byd.music.databinding.ItemMusicListLocalSearchBindingSw720dpImpl;
import com.byd.music.databinding.ItemMusiclistLocalMusicBindingImpl;
import com.byd.music.databinding.ItemMusiclistLocalSdBindingImpl;
import com.byd.music.databinding.ItemMusiclistLocalSdUsbBindingImpl;
import com.byd.music.databinding.ItemMusiclistLocalUsbBindingImpl;
import com.byd.music.databinding.ItemSourceHorizontalBindingImpl;
import com.byd.music.databinding.ItemSourceVerticalBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYEPGREPLACEDIALOG = 1;
    private static final int LAYOUT_ACTIVITYLOCALDIALOG = 2;
    private static final int LAYOUT_ACTIVITYLOCALDIALOGCONFIRM = 3;
    private static final int LAYOUT_ACTIVITYMEDIAMAIN = 4;
    private static final int LAYOUT_ACTIVITYOPENBT = 5;
    private static final int LAYOUT_FRAGMENTAM = 6;
    private static final int LAYOUT_FRAGMENTDAB = 7;
    private static final int LAYOUT_FRAGMENTDABSETTING = 8;
    private static final int LAYOUT_FRAGMENTEPGLIST = 9;
    private static final int LAYOUT_FRAGMENTFM = 10;
    private static final int LAYOUT_FRAGMENTHOLDER = 11;
    private static final int LAYOUT_FRAGMENTLOCALMUSIC = 12;
    private static final int LAYOUT_FRAGMENTLOCALMUSICLIST = 13;
    private static final int LAYOUT_FRAGMENTLOCALROOTMUSIC = 14;
    private static final int LAYOUT_FRAGMENTLOCALSD = 15;
    private static final int LAYOUT_FRAGMENTLOCALSDUSBMUSIC = 16;
    private static final int LAYOUT_FRAGMENTLOCALSEARCH = 17;
    private static final int LAYOUT_FRAGMENTLOCALUSB = 18;
    private static final int LAYOUT_FRAGMENTMUSICBLUETOOTH = 19;
    private static final int LAYOUT_FRAGMENTTASETTING = 20;
    private static final int LAYOUT_ITEMAMCOLLECT = 21;
    private static final int LAYOUT_ITEMARTISTSEARCH = 22;
    private static final int LAYOUT_ITEMCOLLECTMUSICLISTLOCALMUSIC = 23;
    private static final int LAYOUT_ITEMCOLLECTMUSICLISTLOCALSD = 24;
    private static final int LAYOUT_ITEMCOLLECTMUSICLISTLOCALSDUSB = 25;
    private static final int LAYOUT_ITEMCOLLECTMUSICLISTLOCALUSB = 26;
    private static final int LAYOUT_ITEMDABPTY = 27;
    private static final int LAYOUT_ITEMDABSTATION = 28;
    private static final int LAYOUT_ITEMEPGCURRENT = 29;
    private static final int LAYOUT_ITEMEPGDABLIST = 30;
    private static final int LAYOUT_ITEMFMCOLLECT = 31;
    private static final int LAYOUT_ITEMGONE = 32;
    private static final int LAYOUT_ITEMLOCALMUSICLIST = 33;
    private static final int LAYOUT_ITEMMUSICLISTLOCALMUSIC = 35;
    private static final int LAYOUT_ITEMMUSICLISTLOCALSD = 36;
    private static final int LAYOUT_ITEMMUSICLISTLOCALSDUSB = 37;
    private static final int LAYOUT_ITEMMUSICLISTLOCALSEARCH = 34;
    private static final int LAYOUT_ITEMMUSICLISTLOCALUSB = 38;
    private static final int LAYOUT_ITEMSOURCEHORIZONTAL = 39;
    private static final int LAYOUT_ITEMSOURCEVERTICAL = 40;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(40);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_epg_replace_dialog, 1);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_local_dialog, 2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_local_dialog_confirm, 3);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_media_main, 4);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_open_bt, 5);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_am, 6);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_dab, 7);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_dab_setting, 8);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_epg_list, 9);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_fm, 10);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_holder, 11);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_local_music, 12);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_local_musiclist, 13);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_local_root_music, 14);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_local_sd, 15);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_local_sdusb_music, 16);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_local_search, 17);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_local_usb, 18);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_music_bluetooth, 19);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_ta_setting, 20);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_am_collect, 21);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_artist_search, 22);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_collect_musiclist_local_music, 23);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_collect_musiclist_local_sd, 24);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_collect_musiclist_local_sd_usb, 25);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_collect_musiclist_local_usb, 26);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_dab_pty, 27);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_dab_station, 28);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_epg_current, 29);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_epg_dab_list, 30);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_fm_collect, 31);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_gone, 32);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_localmusiclist, 33);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_music_list_local_search, 34);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_musiclist_local_music, 35);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_musiclist_local_sd, 36);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_musiclist_local_sd_usb, 37);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_musiclist_local_usb, 38);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_source_horizontal, 39);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.item_source_vertical, 40);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag == null) {
            throw new RuntimeException("view must have a tag");
        }
        switch (i2) {
            case 1:
                if ("layout/activity_epg_replace_dialog_0".equals(tag)) {
                    return new ActivityEpgReplaceDialogBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_epg_replace_dialog is invalid. Received: " + tag);
            case 2:
                if ("layout/activity_local_dialog_0".equals(tag)) {
                    return new ActivityLocalDialogBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_local_dialog is invalid. Received: " + tag);
            case 3:
                if ("layout/activity_local_dialog_confirm_0".equals(tag)) {
                    return new ActivityLocalDialogConfirmBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_local_dialog_confirm is invalid. Received: " + tag);
            case 4:
                if ("layout/activity_media_main_0".equals(tag)) {
                    return new ActivityMediaMainBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_media_main is invalid. Received: " + tag);
            case 5:
                if ("layout/activity_open_bt_0".equals(tag)) {
                    return new ActivityOpenBtBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_open_bt is invalid. Received: " + tag);
            case 6:
                if ("layout/fragment_am_0".equals(tag)) {
                    return new FragmentAmBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_am is invalid. Received: " + tag);
            case 7:
                if ("layout/fragment_dab_0".equals(tag)) {
                    return new FragmentDabBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_dab is invalid. Received: " + tag);
            case 8:
                if ("layout/fragment_dab_setting_0".equals(tag)) {
                    return new FragmentDabSettingBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_dab_setting is invalid. Received: " + tag);
            case 9:
                if ("layout/fragment_epg_list_0".equals(tag)) {
                    return new FragmentEpgListBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_epg_list is invalid. Received: " + tag);
            case 10:
                if ("layout/fragment_fm_0".equals(tag)) {
                    return new FragmentFmBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_fm is invalid. Received: " + tag);
            case 11:
                if ("layout/fragment_holder_0".equals(tag)) {
                    return new FragmentHolderBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_holder is invalid. Received: " + tag);
            case 12:
                if ("layout/fragment_local_music_0".equals(tag)) {
                    return new FragmentLocalMusicBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_local_music is invalid. Received: " + tag);
            case 13:
                if ("layout/fragment_local_musiclist_0".equals(tag)) {
                    return new FragmentLocalMusiclistBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_local_musiclist is invalid. Received: " + tag);
            case 14:
                if ("layout/fragment_local_root_music_0".equals(tag)) {
                    return new FragmentLocalRootMusicBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_local_root_music is invalid. Received: " + tag);
            case 15:
                if ("layout/fragment_local_sd_0".equals(tag)) {
                    return new FragmentLocalSdBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_local_sd is invalid. Received: " + tag);
            case 16:
                if ("layout/fragment_local_sdusb_music_0".equals(tag)) {
                    return new FragmentLocalSdusbMusicBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_local_sdusb_music is invalid. Received: " + tag);
            case 17:
                if ("layout-sw580dp/fragment_local_search_0".equals(tag)) {
                    return new FragmentLocalSearchBindingSw580dpImpl(dataBindingComponent, view);
                }
                if ("layout-sw720dp/fragment_local_search_0".equals(tag)) {
                    return new FragmentLocalSearchBindingSw720dpImpl(dataBindingComponent, view);
                }
                if ("layout-sw720dp-port/fragment_local_search_0".equals(tag)) {
                    return new FragmentLocalSearchBindingSw720dpPortImpl(dataBindingComponent, view);
                }
                if ("layout/fragment_local_search_0".equals(tag)) {
                    return new FragmentLocalSearchBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_local_search is invalid. Received: " + tag);
            case 18:
                if ("layout/fragment_local_usb_0".equals(tag)) {
                    return new FragmentLocalUsbBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_local_usb is invalid. Received: " + tag);
            case 19:
                if ("layout/fragment_music_bluetooth_0".equals(tag)) {
                    return new FragmentMusicBluetoothBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_music_bluetooth is invalid. Received: " + tag);
            case 20:
                if ("layout/fragment_ta_setting_0".equals(tag)) {
                    return new FragmentTaSettingBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_ta_setting is invalid. Received: " + tag);
            case 21:
                if ("layout/item_am_collect_0".equals(tag)) {
                    return new ItemAmCollectBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_am_collect is invalid. Received: " + tag);
            case 22:
                if ("layout/item_artist_search_0".equals(tag)) {
                    return new ItemArtistSearchBindingImpl(dataBindingComponent, view);
                }
                if ("layout-sw580dp/item_artist_search_0".equals(tag)) {
                    return new ItemArtistSearchBindingSw580dpImpl(dataBindingComponent, view);
                }
                if ("layout-sw720dp/item_artist_search_0".equals(tag)) {
                    return new ItemArtistSearchBindingSw720dpImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_artist_search is invalid. Received: " + tag);
            case 23:
                if ("layout/item_collect_musiclist_local_music_0".equals(tag)) {
                    return new ItemCollectMusiclistLocalMusicBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_collect_musiclist_local_music is invalid. Received: " + tag);
            case 24:
                if ("layout/item_collect_musiclist_local_sd_0".equals(tag)) {
                    return new ItemCollectMusiclistLocalSdBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_collect_musiclist_local_sd is invalid. Received: " + tag);
            case 25:
                if ("layout/item_collect_musiclist_local_sd_usb_0".equals(tag)) {
                    return new ItemCollectMusiclistLocalSdUsbBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_collect_musiclist_local_sd_usb is invalid. Received: " + tag);
            case 26:
                if ("layout/item_collect_musiclist_local_usb_0".equals(tag)) {
                    return new ItemCollectMusiclistLocalUsbBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_collect_musiclist_local_usb is invalid. Received: " + tag);
            case 27:
                if ("layout/item_dab_pty_0".equals(tag)) {
                    return new ItemDabPtyBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_dab_pty is invalid. Received: " + tag);
            case 28:
                if ("layout/item_dab_station_0".equals(tag)) {
                    return new ItemDabStationBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_dab_station is invalid. Received: " + tag);
            case 29:
                if ("layout/item_epg_current_0".equals(tag)) {
                    return new ItemEpgCurrentBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_epg_current is invalid. Received: " + tag);
            case 30:
                if ("layout/item_epg_dab_list_0".equals(tag)) {
                    return new ItemEpgDabListBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_epg_dab_list is invalid. Received: " + tag);
            case 31:
                if ("layout/item_fm_collect_0".equals(tag)) {
                    return new ItemFmCollectBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_fm_collect is invalid. Received: " + tag);
            case 32:
                if ("layout/item_gone_0".equals(tag)) {
                    return new ItemGoneBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_gone is invalid. Received: " + tag);
            case 33:
                if ("layout/item_localmusiclist_0".equals(tag)) {
                    return new ItemLocalmusiclistBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_localmusiclist is invalid. Received: " + tag);
            case 34:
                if ("layout-sw720dp/item_music_list_local_search_0".equals(tag)) {
                    return new ItemMusicListLocalSearchBindingSw720dpImpl(dataBindingComponent, view);
                }
                if ("layout/item_music_list_local_search_0".equals(tag)) {
                    return new ItemMusicListLocalSearchBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_music_list_local_search is invalid. Received: " + tag);
            case 35:
                if ("layout/item_musiclist_local_music_0".equals(tag)) {
                    return new ItemMusiclistLocalMusicBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_musiclist_local_music is invalid. Received: " + tag);
            case 36:
                if ("layout/item_musiclist_local_sd_0".equals(tag)) {
                    return new ItemMusiclistLocalSdBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_musiclist_local_sd is invalid. Received: " + tag);
            case 37:
                if ("layout/item_musiclist_local_sd_usb_0".equals(tag)) {
                    return new ItemMusiclistLocalSdUsbBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_musiclist_local_sd_usb is invalid. Received: " + tag);
            case 38:
                if ("layout/item_musiclist_local_usb_0".equals(tag)) {
                    return new ItemMusiclistLocalUsbBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_musiclist_local_usb is invalid. Received: " + tag);
            case 39:
                if ("layout/item_source_horizontal_0".equals(tag)) {
                    return new ItemSourceHorizontalBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_source_horizontal is invalid. Received: " + tag);
            case 40:
                if ("layout/item_source_vertical_0".equals(tag)) {
                    return new ItemSourceVerticalBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_source_vertical is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = InnerLayoutIdLookup.sKeys.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.telenav.scoutivi.spotify.byd.dx.DataBinderMapperImpl());
        arrayList.add(new com.vivid.sdk.spotify.DataBinderMapperImpl());
        arrayList.add(new me.tatarka.bindingcollectionadapter2.DataBinderMapperImpl());
        arrayList.add(new me.tatarka.bindingcollectionadapter2.recyclerview.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys;

        private InnerBrLookup() {
        }

        static {
            SparseArray<String> sparseArray = new SparseArray<>(16);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sKeys.put(1, "bean");
            sKeys.put(2, "collectLocalSdUsbMusicBean");
            sKeys.put(3, "epgBean");
            sKeys.put(4, "fmCardBean");
            sKeys.put(5, "isCollect");
            sKeys.put(6, "isCollectShow");
            sKeys.put(7, "localMusicBean");
            sKeys.put(8, "localSdUsbMusicBean");
            sKeys.put(9, "localSearchAlbumBean");
            sKeys.put(10, "localSearchMusicBean");
            sKeys.put(11, "programInfo");
            sKeys.put(12, "setBean");
            sKeys.put(13, "titleId");
            sKeys.put(14, "viewModel");
            sKeys.put(15, "vm");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> map = new HashMap<>(46);
            sKeys = map;
            map.put("layout/activity_epg_replace_dialog_0", Integer.valueOf(R.layout.activity_epg_replace_dialog));
            sKeys.put("layout/activity_local_dialog_0", Integer.valueOf(R.layout.activity_local_dialog));
            sKeys.put("layout/activity_local_dialog_confirm_0", Integer.valueOf(R.layout.activity_local_dialog_confirm));
            sKeys.put("layout/activity_media_main_0", Integer.valueOf(R.layout.activity_media_main));
            sKeys.put("layout/activity_open_bt_0", Integer.valueOf(R.layout.activity_open_bt));
            sKeys.put("layout/fragment_am_0", Integer.valueOf(R.layout.fragment_am));
            sKeys.put("layout/fragment_dab_0", Integer.valueOf(R.layout.fragment_dab));
            sKeys.put("layout/fragment_dab_setting_0", Integer.valueOf(R.layout.fragment_dab_setting));
            sKeys.put("layout/fragment_epg_list_0", Integer.valueOf(R.layout.fragment_epg_list));
            sKeys.put("layout/fragment_fm_0", Integer.valueOf(R.layout.fragment_fm));
            sKeys.put("layout/fragment_holder_0", Integer.valueOf(R.layout.fragment_holder));
            sKeys.put("layout/fragment_local_music_0", Integer.valueOf(R.layout.fragment_local_music));
            sKeys.put("layout/fragment_local_musiclist_0", Integer.valueOf(R.layout.fragment_local_musiclist));
            sKeys.put("layout/fragment_local_root_music_0", Integer.valueOf(R.layout.fragment_local_root_music));
            sKeys.put("layout/fragment_local_sd_0", Integer.valueOf(R.layout.fragment_local_sd));
            sKeys.put("layout/fragment_local_sdusb_music_0", Integer.valueOf(R.layout.fragment_local_sdusb_music));
            sKeys.put("layout-sw580dp/fragment_local_search_0", Integer.valueOf(R.layout.fragment_local_search));
            sKeys.put("layout-sw720dp/fragment_local_search_0", Integer.valueOf(R.layout.fragment_local_search));
            sKeys.put("layout-sw720dp-port/fragment_local_search_0", Integer.valueOf(R.layout.fragment_local_search));
            sKeys.put("layout/fragment_local_search_0", Integer.valueOf(R.layout.fragment_local_search));
            sKeys.put("layout/fragment_local_usb_0", Integer.valueOf(R.layout.fragment_local_usb));
            sKeys.put("layout/fragment_music_bluetooth_0", Integer.valueOf(R.layout.fragment_music_bluetooth));
            sKeys.put("layout/fragment_ta_setting_0", Integer.valueOf(R.layout.fragment_ta_setting));
            sKeys.put("layout/item_am_collect_0", Integer.valueOf(R.layout.item_am_collect));
            sKeys.put("layout/item_artist_search_0", Integer.valueOf(R.layout.item_artist_search));
            sKeys.put("layout-sw580dp/item_artist_search_0", Integer.valueOf(R.layout.item_artist_search));
            sKeys.put("layout-sw720dp/item_artist_search_0", Integer.valueOf(R.layout.item_artist_search));
            sKeys.put("layout/item_collect_musiclist_local_music_0", Integer.valueOf(R.layout.item_collect_musiclist_local_music));
            sKeys.put("layout/item_collect_musiclist_local_sd_0", Integer.valueOf(R.layout.item_collect_musiclist_local_sd));
            sKeys.put("layout/item_collect_musiclist_local_sd_usb_0", Integer.valueOf(R.layout.item_collect_musiclist_local_sd_usb));
            sKeys.put("layout/item_collect_musiclist_local_usb_0", Integer.valueOf(R.layout.item_collect_musiclist_local_usb));
            sKeys.put("layout/item_dab_pty_0", Integer.valueOf(R.layout.item_dab_pty));
            sKeys.put("layout/item_dab_station_0", Integer.valueOf(R.layout.item_dab_station));
            sKeys.put("layout/item_epg_current_0", Integer.valueOf(R.layout.item_epg_current));
            sKeys.put("layout/item_epg_dab_list_0", Integer.valueOf(R.layout.item_epg_dab_list));
            sKeys.put("layout/item_fm_collect_0", Integer.valueOf(R.layout.item_fm_collect));
            sKeys.put("layout/item_gone_0", Integer.valueOf(R.layout.item_gone));
            sKeys.put("layout/item_localmusiclist_0", Integer.valueOf(R.layout.item_localmusiclist));
            sKeys.put("layout-sw720dp/item_music_list_local_search_0", Integer.valueOf(R.layout.item_music_list_local_search));
            sKeys.put("layout/item_music_list_local_search_0", Integer.valueOf(R.layout.item_music_list_local_search));
            sKeys.put("layout/item_musiclist_local_music_0", Integer.valueOf(R.layout.item_musiclist_local_music));
            sKeys.put("layout/item_musiclist_local_sd_0", Integer.valueOf(R.layout.item_musiclist_local_sd));
            sKeys.put("layout/item_musiclist_local_sd_usb_0", Integer.valueOf(R.layout.item_musiclist_local_sd_usb));
            sKeys.put("layout/item_musiclist_local_usb_0", Integer.valueOf(R.layout.item_musiclist_local_usb));
            sKeys.put("layout/item_source_horizontal_0", Integer.valueOf(R.layout.item_source_horizontal));
            sKeys.put("layout/item_source_vertical_0", Integer.valueOf(R.layout.item_source_vertical));
        }
    }
}
