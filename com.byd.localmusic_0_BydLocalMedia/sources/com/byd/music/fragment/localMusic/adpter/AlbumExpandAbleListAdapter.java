package com.byd.music.fragment.localMusic.adpter;

import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.byd.music.R;
import com.byd.music.beans.AlbumBean;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter;
import com.byd.music.fragment.localMusic.utils.LocalMusicUtils;
import com.byd.music.utils.LogUtil;
import com.byd.music.view.CollectLottieView;
import com.byd.music.view.musicPlayingSlideView.PlayingSlideAnimView;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AlbumExpandAbleListAdapter extends ExpandableListAdapterHeader<AlbumBean> {
    private OnExpandListener mExpandListener;
    private int sourceType;

    public interface OnExpandListener {
        void onExpandGroup(AlbumBean albumBean);

        void onGroupCheckBoxClick(int i, boolean z);
    }

    public AlbumExpandAbleListAdapter(List<AlbumBean> list, ExpandableListView expandableListView) {
        super(list, expandableListView);
        this.sourceType = -1;
    }

    public AlbumExpandAbleListAdapter(int i, List<AlbumBean> list, ExpandableListView expandableListView) {
        super(list, expandableListView);
        this.sourceType = -1;
        this.sourceType = i;
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter
    public void addChildView(View view, int i, int i2) {
        ExpandableListAdapter.ChildViewHolder childViewHolder = (ExpandableListAdapter.ChildViewHolder) view.getTag();
        LocalMusicBean child = getChild(i, i2);
        if (child != null) {
            LogUtil.d(this.TAG, "addChildView: " + this.mShowOperate);
            Boolean boolValueOf = Boolean.valueOf(child.equals(MusicPlayHelper.getInstance().getCurrentPlayLocalMusic()));
            if (this.sourceType != -1) {
                boolValueOf = Boolean.valueOf(child.equals(MusicPlayHelper.getInstance().currentLocalMusicMaps.get(Integer.valueOf(this.sourceType))));
            }
            setMusicSlidePlaying(childViewHolder.playSlideView, MusicPlayHelper.getInstance().isPlaying.get(), boolValueOf.booleanValue());
            if (boolValueOf.booleanValue()) {
                childViewHolder.musicTitle.setTextColor(view.getResources().getColor(R.color.text_color_selected));
                childViewHolder.tvIndexChild.setVisibility(4);
            } else {
                childViewHolder.musicTitle.setTextColor(view.getResources().getColor(R.color.color_gray_front));
                childViewHolder.tvIndexChild.setVisibility(0);
            }
            childViewHolder.musicTitle.setText(LocalMusicUtils.BYD_SHOW_CAR_ARTIST.equals(child.getTitle()) ? view.getContext().getString(R.string.unknown_music) : child.getTitle());
            childViewHolder.tvIndexChild.setText(String.valueOf(i2 + 1));
            ExpandableListAdapter.ChildClickListener childClickListener = new ExpandableListAdapter.ChildClickListener(this, child, childViewHolder);
            this.mExpandListView.setOnChildClickListener(childClickListener);
            childViewHolder.deleteIv.setOnClickListener(childClickListener);
            childViewHolder.collectIv.setOnClickListener(childClickListener);
            childViewHolder.collectIv.setImageDrawable(view.getContext().getDrawable(child.isCollect() ? R.drawable.icon_dark_media_playcollection_s_sel_48 : R.drawable.icon_dark_media_playcollection_s_48));
            childViewHolder.childCheckBox.setOnCheckedChangeListener(childClickListener);
            childViewHolder.collectLottiesView.disableAnimation();
            if (this.mShowOperate) {
                childViewHolder.childCheckBox.setVisibility(0);
                childViewHolder.deleteIv.setVisibility(8);
                childViewHolder.collectIv.setVisibility(8);
                if (this.mMusicSelectedSet != null && this.mMusicSelectedSet.contains(child)) {
                    childViewHolder.childCheckBox.setChecked(true);
                    return;
                } else {
                    childViewHolder.childCheckBox.setChecked(false);
                    return;
                }
            }
            childViewHolder.childCheckBox.setVisibility(8);
            childViewHolder.deleteIv.setVisibility(0);
            childViewHolder.collectIv.setVisibility(0);
        }
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter
    public void addGroupView(View view, int i) {
        ExpandableListAdapter.GroupViewHolder groupViewHolder = (ExpandableListAdapter.GroupViewHolder) view.getTag();
        int size = this.mGroupList.size();
        if (this.mGroupList == null || size <= 0 || size <= i || i < 0) {
            return;
        }
        AlbumBean albumBean = (AlbumBean) this.mGroupList.get(i);
        groupViewHolder.albumName.setText(LocalMusicUtils.BYD_SHOW_CAR_ARTIST.equals(albumBean.getName()) ? view.getContext().getString(R.string.unknown_album) : albumBean.getName());
        groupViewHolder.artistName.setText(LocalMusicUtils.BYD_SHOW_CAR_ARTIST.equals(albumBean.getArtist()) ? view.getContext().getString(R.string.unknown_artist) : albumBean.getArtist());
        groupViewHolder.musicNum.setText(String.format(groupViewHolder.musicNum.getContext().getString(R.string.musics_nums), Integer.valueOf(albumBean.getmAllMusics().size())));
        groupViewHolder.tvIndexGroup.setText(String.valueOf(i + 1));
        Glide.with(Utils.getApp().getApplicationContext()).load(LocalMusicUtils.getAlbumUri(albumBean.getId())).diskCacheStrategy(DiskCacheStrategy.RESOURCE).placeholder(R.drawable.album_default_248).dontAnimate().error(R.drawable.album_default_248).into(groupViewHolder.albumCover);
        ExpandSwitchListener expandSwitchListener = new ExpandSwitchListener(i);
        this.mExpandListView.setOnGroupClickListener(expandSwitchListener);
        groupViewHolder.upDownArrow.setOnClickListener(expandSwitchListener);
        if (this.mExpandListView.isGroupExpanded(i)) {
            groupViewHolder.upDownArrow.setImageDrawable(view.getContext().getDrawable(R.drawable.icon_dark_public_uparrow_s_48));
        } else {
            groupViewHolder.upDownArrow.setImageDrawable(view.getContext().getDrawable(R.drawable.icon_dark_public_dowmarrow_s_48));
        }
        groupViewHolder.groupCheckBox.setOnCheckedChangeListener(expandSwitchListener);
        if (this.mShowOperate) {
            groupViewHolder.groupCheckBox.setVisibility(0);
            if (this.mGroupSelectedSet.contains(albumBean)) {
                groupViewHolder.groupCheckBox.setChecked(true);
                return;
            } else {
                groupViewHolder.groupCheckBox.setChecked(false);
                return;
            }
        }
        groupViewHolder.groupCheckBox.setVisibility(8);
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter
    public List<LocalMusicBean> getChildList(int i) {
        if (getGroup(i) == null) {
            return null;
        }
        return getGroup(i).getmAllMusics();
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter
    public View newGroupView(boolean z, ViewGroup viewGroup) {
        ExpandableListAdapter.GroupViewHolder groupViewHolder = new ExpandableListAdapter.GroupViewHolder();
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ablum_group, viewGroup, false);
        groupViewHolder.albumName = (TextView) viewInflate.findViewById(R.id.tv_album_name);
        groupViewHolder.artistName = (TextView) viewInflate.findViewById(R.id.tv_artist_name);
        groupViewHolder.musicNum = (TextView) viewInflate.findViewById(R.id.tv_total_music);
        groupViewHolder.albumCover = (ImageView) viewInflate.findViewById(R.id.img_album);
        groupViewHolder.upDownArrow = (ImageView) viewInflate.findViewById(R.id.iv_expand_arrow);
        groupViewHolder.tvIndexGroup = (TextView) viewInflate.findViewById(R.id.tvIndex);
        groupViewHolder.groupCheckBox = (CheckBox) viewInflate.findViewById(R.id.group_checkbox);
        viewInflate.setTag(groupViewHolder);
        return viewInflate;
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter
    public View newChildView(boolean z, ViewGroup viewGroup) {
        ExpandableListAdapter.ChildViewHolder childViewHolder = new ExpandableListAdapter.ChildViewHolder();
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ablum_child, viewGroup, false);
        childViewHolder.collectIv = (ImageView) viewInflate.findViewById(R.id.iv_collect);
        childViewHolder.deleteIv = (ImageView) viewInflate.findViewById(R.id.iv_delete);
        childViewHolder.musicTitle = (TextView) viewInflate.findViewById(R.id.tv_music_name);
        childViewHolder.tvIndexChild = (TextView) viewInflate.findViewById(R.id.tvIndex);
        childViewHolder.childCheckBox = (CheckBox) viewInflate.findViewById(R.id.child_checkbox);
        childViewHolder.playSlideView = (PlayingSlideAnimView) viewInflate.findViewById(R.id.play_slide_view);
        childViewHolder.collectLottiesView = (CollectLottieView) viewInflate.findViewById(R.id.collectLottiesView);
        viewInflate.setTag(childViewHolder);
        return viewInflate;
    }

    class ExpandSwitchListener implements View.OnClickListener, ExpandableListView.OnGroupClickListener, CompoundButton.OnCheckedChangeListener {
        int mPosition;

        ExpandSwitchListener(int i) {
            this.mPosition = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (AlbumExpandAbleListAdapter.this.mExpandListView.isGroupExpanded(this.mPosition)) {
                AlbumExpandAbleListAdapter.this.mExpandListView.collapseGroup(this.mPosition);
                AlbumExpandAbleListAdapter.this.expandGroupPos = -1;
                ((ImageView) view).setImageResource(R.drawable.icon_dark_public_dowmarrow_s_48);
                return;
            }
            if (AlbumExpandAbleListAdapter.this.mGroupList != null) {
                int size = AlbumExpandAbleListAdapter.this.mGroupList.size();
                int i = 0;
                while (true) {
                    if (i < size) {
                        if (AlbumExpandAbleListAdapter.this.mExpandListView.isGroupExpanded(i) && this.mPosition != i) {
                            AlbumExpandAbleListAdapter.this.mExpandListView.collapseGroup(i);
                            break;
                        }
                        i++;
                    } else {
                        break;
                    }
                }
            }
            AlbumExpandAbleListAdapter.this.mExpandListView.expandGroup(this.mPosition);
            AlbumExpandAbleListAdapter.this.expandGroupPos = this.mPosition;
            ((ImageView) view).setImageResource(R.drawable.icon_dark_public_uparrow_s_48);
        }

        @Override // android.widget.ExpandableListView.OnGroupClickListener
        public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
            if (AlbumExpandAbleListAdapter.this.mGroupList != null && AlbumExpandAbleListAdapter.this.mGroupList.size() > 0) {
                LogUtil.e(AlbumExpandAbleListAdapter.this.TAG, "onGroupClick");
                view.playSoundEffect(0);
                if (AlbumExpandAbleListAdapter.this.mShowOperate) {
                    if (AlbumExpandAbleListAdapter.this.mGroupSelectedSet.contains(AlbumExpandAbleListAdapter.this.mGroupList.get(i))) {
                        AlbumExpandAbleListAdapter.this.selectAllMusicGroup(i, false);
                    } else {
                        AlbumExpandAbleListAdapter.this.selectAllMusicGroup(i, true);
                    }
                } else {
                    MusicPlayHelper.getInstance().setCollectList(false);
                    MusicPlayHelper.getInstance().playListByItem(((AlbumBean) AlbumExpandAbleListAdapter.this.mGroupList.get(i)).getmAllMusics(), ((AlbumBean) AlbumExpandAbleListAdapter.this.mGroupList.get(i)).getmAllMusics().get(0));
                }
            }
            return true;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            LogUtil.e(AlbumExpandAbleListAdapter.this.TAG, "ExpandSwitchListener-onCheckedChanged:" + z + " pressed" + compoundButton.isPressed());
            if (compoundButton.isPressed()) {
                AlbumExpandAbleListAdapter.this.selectAllMusicGroup(this.mPosition, z);
            }
        }
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter
    public void selectAll() {
        super.selectAll();
        Iterator it = this.mGroupSelectedSet.iterator();
        while (it.hasNext()) {
            this.mMusicSelectedSet.addAll(((AlbumBean) it.next()).getmAllMusics());
        }
        notifyDataSetChanged();
        notifySelectedCountChanged();
    }

    public void setOnExpandListener(OnExpandListener onExpandListener) {
        this.mExpandListener = onExpandListener;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter
    public void selectChildItem(LocalMusicBean localMusicBean, boolean z) {
        super.selectChildItem(localMusicBean, z);
        AlbumBean albumById = getAlbumById(localMusicBean.getAlbumId());
        if (z) {
            this.mMusicSelectedSet.add(localMusicBean);
            if (this.mMusicSelectedSet.containsAll(albumById.getmAllMusics())) {
                this.mGroupSelectedSet.add((T) albumById);
            }
        } else {
            this.mMusicSelectedSet.remove(localMusicBean);
            this.mGroupSelectedSet.remove(albumById);
        }
        notifyDataSetChanged();
        notifySelectedCountChanged();
        if (this.mGroupSelectedSet.containsAll(this.mGroupList)) {
            this.mSelectedGroupListener.onAllSelectedStateChanged(true);
        } else {
            this.mSelectedGroupListener.onAllSelectedStateChanged(false);
        }
    }

    private AlbumBean getAlbumById(long j) {
        AlbumBean albumBean = null;
        for (T t : this.mGroupList) {
            if (t.getId() == j) {
                albumBean = t;
            }
        }
        return albumBean;
    }

    @Override // com.byd.music.view.HeaderExpandListview.HeaderAdapter
    public int getHeaderState(int i, int i2) {
        if (i2 == getChildrenCount(i) - 1) {
            return 2;
        }
        return i2 == -1 ? 0 : 1;
    }

    @Override // com.byd.music.view.HeaderExpandListview.HeaderAdapter
    public void configureHeader(View view, int i, int i2, int i3) {
        if (i > -1) {
            AlbumBean albumBean = (AlbumBean) this.mGroupList.get(i);
            LogUtil.e(this.TAG, "groupPosition:" + i);
            TextView textView = (TextView) view.findViewById(R.id.tv_album_name_header);
            if (textView != null) {
                textView.setText(LocalMusicUtils.BYD_SHOW_CAR_ARTIST.equals(albumBean.getName()) ? view.getContext().getString(R.string.unknown_album) : albumBean.getName());
            }
            TextView textView2 = (TextView) view.findViewById(R.id.tvIndex_header);
            if (textView2 != null) {
                textView2.setText(String.valueOf(i + 1));
            }
            TextView textView3 = (TextView) view.findViewById(R.id.tv_album_artist_name_header);
            if (textView3 != null) {
                textView3.setText(LocalMusicUtils.BYD_SHOW_CAR_ARTIST.equals(albumBean.getArtist()) ? view.getContext().getString(R.string.unknown_artist) : albumBean.getArtist());
            }
            TextView textView4 = (TextView) view.findViewById(R.id.tv_album_total_music_header);
            if (textView4 != null) {
                textView4.setText(String.format(textView4.getContext().getString(R.string.musics_nums), Integer.valueOf(getChildrenCount(i))));
            }
            Glide.with(Utils.getApp().getApplicationContext()).load(LocalMusicUtils.getAlbumUri(albumBean.getId())).override(36).diskCacheStrategy(DiskCacheStrategy.RESOURCE).placeholder(R.drawable.album_default_248).dontAnimate().error(R.drawable.album_default_248).into((ImageView) view.findViewById(R.id.img_album));
        }
    }

    @Override // com.byd.music.view.HeaderExpandListview.HeaderAdapter
    public void headerViewClick() {
        this.mExpandListView.setSelectedGroup(ExpandableListView.getPackedPositionGroup(this.mExpandListView.getExpandableListPosition(this.mExpandListView.getFirstVisiblePosition())));
        this.mExpandListView.playSoundEffect(0);
    }

    @Override // com.byd.music.view.HeaderExpandListview.HeaderAdapter
    public void headerViewArrowClick() {
        int packedPositionGroup = ExpandableListView.getPackedPositionGroup(this.mExpandListView.getExpandableListPosition(this.mExpandListView.getFirstVisiblePosition()));
        this.mExpandListView.collapseGroup(packedPositionGroup);
        this.expandGroupPos = -1;
        this.mExpandListView.playSoundEffect(0);
        this.mExpandListView.setSelectedGroup(packedPositionGroup);
        ExpandableListView expandableListView = this.mExpandListView;
        ExpandableListView expandableListView2 = this.mExpandListView;
        expandableListView.playSoundEffect(SoundEffectConstants.getContantForFocusDirection(130));
    }
}
