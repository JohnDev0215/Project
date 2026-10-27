package com.byd.music.fragment.localMusic.adpter;

import android.view.LayoutInflater;
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
import com.byd.music.beans.ArtistBean;
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
public class ArtistExpandAbleListAdapter extends ExpandableListAdapterHeader<ArtistBean> {
    private OnExpandListener mExpandListener;
    private int sourceType;

    public interface OnExpandListener {
        void onExpandGroup(ArtistBean artistBean);

        void onGroupClick(ArtistBean artistBean);
    }

    public ArtistExpandAbleListAdapter(List<ArtistBean> list, ExpandableListView expandableListView) {
        super(list, expandableListView);
        this.sourceType = -1;
    }

    public ArtistExpandAbleListAdapter(int i, List<ArtistBean> list, ExpandableListView expandableListView) {
        super(list, expandableListView);
        this.sourceType = -1;
        this.sourceType = i;
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter
    public void addChildView(View view, int i, int i2) {
        ExpandableListAdapter.ChildViewHolder childViewHolder = (ExpandableListAdapter.ChildViewHolder) view.getTag();
        LocalMusicBean child = getChild(i, i2);
        if (child != null) {
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
            childViewHolder.collectIv.setOnClickListener(childClickListener);
            childViewHolder.deleteIv.setOnClickListener(childClickListener);
            childViewHolder.collectIv.setImageDrawable(view.getContext().getDrawable(child.isCollect() ? R.drawable.icon_dark_media_playcollection_s_sel_48 : R.drawable.icon_dark_media_playcollection_s_48));
            childViewHolder.childCheckBox.setOnCheckedChangeListener(childClickListener);
            childViewHolder.collectLottiesView.disableAnimation();
            LogUtil.d(this.TAG, "addChildView: " + this.mShowOperate);
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
        if (this.mGroupList == null || this.mGroupList.size() <= 0) {
            return;
        }
        ArtistBean artistBean = (ArtistBean) this.mGroupList.get(i);
        groupViewHolder.artistName.setText(artistBean.getName());
        groupViewHolder.artistName.setText(LocalMusicUtils.BYD_SHOW_CAR_ARTIST.equals(artistBean.getName()) ? view.getContext().getString(R.string.unknown_artist) : artistBean.getName());
        groupViewHolder.musicNum.setText(String.format(view.getContext().getString(R.string.musics_nums), Integer.valueOf(artistBean.getmAllMusics().size())));
        groupViewHolder.tvIndexGroup.setText(String.valueOf(i + 1));
        ArtistExpandSwitchListener artistExpandSwitchListener = new ArtistExpandSwitchListener(i);
        this.mExpandListView.setOnGroupClickListener(artistExpandSwitchListener);
        groupViewHolder.upDownArrow.setOnClickListener(artistExpandSwitchListener);
        groupViewHolder.groupCheckBox.setOnCheckedChangeListener(artistExpandSwitchListener);
        if (this.mExpandListView.isGroupExpanded(i)) {
            groupViewHolder.upDownArrow.setImageDrawable(view.getContext().getDrawable(R.drawable.icon_dark_public_uparrow_s_48));
        } else {
            groupViewHolder.upDownArrow.setImageDrawable(view.getContext().getDrawable(R.drawable.icon_dark_public_dowmarrow_s_48));
        }
        if (this.mShowOperate) {
            groupViewHolder.groupCheckBox.setVisibility(0);
            if (this.mGroupSelectedSet.contains(artistBean)) {
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
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_aritsit_group, viewGroup, false);
        groupViewHolder.tvIndexGroup = (TextView) viewInflate.findViewById(R.id.tvIndex);
        groupViewHolder.artistName = (TextView) viewInflate.findViewById(R.id.tv_artist_name);
        groupViewHolder.musicNum = (TextView) viewInflate.findViewById(R.id.tv_total_music);
        groupViewHolder.upDownArrow = (ImageView) viewInflate.findViewById(R.id.iv_expand_arrow_artist);
        groupViewHolder.groupCheckBox = (CheckBox) viewInflate.findViewById(R.id.group_artist_checkbox);
        groupViewHolder.albumCover = (ImageView) viewInflate.findViewById(R.id.img_artist);
        viewInflate.setTag(groupViewHolder);
        return viewInflate;
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter
    public View newChildView(boolean z, ViewGroup viewGroup) {
        ExpandableListAdapter.ChildViewHolder childViewHolder = new ExpandableListAdapter.ChildViewHolder();
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_aritsit_child, viewGroup, false);
        childViewHolder.tvIndexChild = (TextView) viewInflate.findViewById(R.id.tvIndex);
        childViewHolder.musicTitle = (TextView) viewInflate.findViewById(R.id.tv_music_name);
        childViewHolder.collectIv = (ImageView) viewInflate.findViewById(R.id.iv_collect);
        childViewHolder.deleteIv = (ImageView) viewInflate.findViewById(R.id.iv_delete);
        childViewHolder.childCheckBox = (CheckBox) viewInflate.findViewById(R.id.child_artist_checkbox);
        childViewHolder.playSlideView = (PlayingSlideAnimView) viewInflate.findViewById(R.id.play_slide_view);
        childViewHolder.collectLottiesView = (CollectLottieView) viewInflate.findViewById(R.id.collectLottiesView);
        viewInflate.setTag(childViewHolder);
        return viewInflate;
    }

    class ArtistExpandSwitchListener implements View.OnClickListener, ExpandableListView.OnGroupClickListener, CompoundButton.OnCheckedChangeListener {
        int mPosition;

        public ArtistExpandSwitchListener(int i) {
            this.mPosition = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ArtistExpandAbleListAdapter.this.mExpandListView.isGroupExpanded(this.mPosition)) {
                ArtistExpandAbleListAdapter.this.mExpandListView.collapseGroup(this.mPosition);
                ArtistExpandAbleListAdapter.this.expandGroupPos = -1;
                ((ImageView) view).setImageResource(R.drawable.icon_dark_public_dowmarrow_s_48);
                return;
            }
            if (ArtistExpandAbleListAdapter.this.mGroupList != null) {
                int size = ArtistExpandAbleListAdapter.this.mGroupList.size();
                int i = 0;
                while (true) {
                    if (i < size) {
                        if (ArtistExpandAbleListAdapter.this.mExpandListView.isGroupExpanded(i) && this.mPosition != i) {
                            ArtistExpandAbleListAdapter.this.mExpandListView.collapseGroup(i);
                            break;
                        }
                        i++;
                    } else {
                        break;
                    }
                }
            }
            ArtistExpandAbleListAdapter.this.mExpandListView.expandGroup(this.mPosition);
            ArtistExpandAbleListAdapter.this.expandGroupPos = this.mPosition;
            ((ImageView) view).setImageResource(R.drawable.icon_dark_public_uparrow_s_48);
        }

        @Override // android.widget.ExpandableListView.OnGroupClickListener
        public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
            if (ArtistExpandAbleListAdapter.this.mGroupList != null && ArtistExpandAbleListAdapter.this.mGroupList.size() > 0) {
                LogUtil.e(ArtistExpandAbleListAdapter.this.TAG, "onGroupClick: ");
                view.playSoundEffect(0);
                if (ArtistExpandAbleListAdapter.this.mShowOperate) {
                    if (ArtistExpandAbleListAdapter.this.mGroupSelectedSet.contains(ArtistExpandAbleListAdapter.this.mGroupList.get(i))) {
                        ArtistExpandAbleListAdapter.this.selectAllMusicGroup(i, false);
                    } else {
                        ArtistExpandAbleListAdapter.this.selectAllMusicGroup(i, true);
                    }
                } else {
                    MusicPlayHelper.getInstance().setCollectList(false);
                    MusicPlayHelper.getInstance().playListByItem(((ArtistBean) ArtistExpandAbleListAdapter.this.mGroupList.get(i)).getmAllMusics(), ((ArtistBean) ArtistExpandAbleListAdapter.this.mGroupList.get(i)).getmAllMusics().get(0));
                }
            }
            return true;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            StringBuilder sb = new StringBuilder();
            sb.append("ExpandSwitchListener-onCheckedChanged:");
            sb.append(z);
            sb.append(" pressed");
            sb.append(compoundButton.isPressed());
            if (compoundButton.isPressed()) {
                ArtistExpandAbleListAdapter.this.selectAllMusicGroup(this.mPosition, z);
            }
        }
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter
    public void selectAll() {
        super.selectAll();
        Iterator it = this.mGroupSelectedSet.iterator();
        while (it.hasNext()) {
            this.mMusicSelectedSet.addAll(((ArtistBean) it.next()).getmAllMusics());
        }
        notifyDataSetChanged();
        notifySelectedCountChanged();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter
    public void selectChildItem(LocalMusicBean localMusicBean, boolean z) {
        super.selectChildItem(localMusicBean, z);
        ArtistBean artistById = getArtistById(localMusicBean.getArtistId());
        if (z) {
            this.mMusicSelectedSet.add(localMusicBean);
            if (this.mMusicSelectedSet.containsAll(artistById.getmAllMusics())) {
                this.mGroupSelectedSet.add((T) artistById);
            }
        } else {
            this.mMusicSelectedSet.remove(localMusicBean);
            this.mGroupSelectedSet.remove(artistById);
        }
        notifyDataSetChanged();
        notifySelectedCountChanged();
        if (this.mGroupSelectedSet.containsAll(this.mGroupList)) {
            this.mSelectedGroupListener.onAllSelectedStateChanged(true);
        } else {
            this.mSelectedGroupListener.onAllSelectedStateChanged(false);
        }
    }

    private ArtistBean getArtistById(long j) {
        ArtistBean artistBean = null;
        for (T t : this.mGroupList) {
            if (t.getId() == j) {
                artistBean = t;
            }
        }
        return artistBean;
    }

    @Override // com.byd.music.view.HeaderExpandListview.HeaderAdapter
    public int getHeaderState(int i, int i2) {
        int childrenCount = getChildrenCount(i);
        if (this.mShowOperate) {
            return 0;
        }
        if (i2 == childrenCount - 1) {
            return 2;
        }
        return i2 == -1 ? 0 : 1;
    }

    @Override // com.byd.music.view.HeaderExpandListview.HeaderAdapter
    public void configureHeader(View view, int i, int i2, int i3) {
        if (i > -1) {
            ArtistBean artistBean = (ArtistBean) this.mGroupList.get(i);
            TextView textView = (TextView) view.findViewById(R.id.tv_artist_name_header);
            if (textView != null) {
                textView.setText(LocalMusicUtils.BYD_SHOW_CAR_ARTIST.equals(artistBean.getName()) ? view.getContext().getString(R.string.unknown_artist) : artistBean.getName());
            }
            TextView textView2 = (TextView) view.findViewById(R.id.tv_total_music_header);
            if (textView2 != null) {
                textView2.setText(String.format(textView2.getContext().getString(R.string.musics_nums), Integer.valueOf(artistBean.allMusicSize())));
            }
            TextView textView3 = (TextView) view.findViewById(R.id.tvIndex_header);
            if (textView3 != null) {
                textView3.setText(String.valueOf(i + 1));
            }
            Glide.with(Utils.getApp().getApplicationContext()).load(LocalMusicUtils.getAlbumUri(artistBean.getmAllMusics().get(0).getAlbumId())).diskCacheStrategy(DiskCacheStrategy.RESOURCE).placeholder(R.drawable.icon_dark_status_bar_personal_48).dontAnimate().error(R.drawable.icon_dark_status_bar_personal_48).into((ImageView) view.findViewById(R.id.img_artist_header));
        }
    }

    @Override // com.byd.music.view.HeaderExpandListview.HeaderAdapter
    public void headerViewClick() {
        int packedPositionGroup = ExpandableListView.getPackedPositionGroup(this.mExpandListView.getExpandableListPosition(this.mExpandListView.getFirstVisiblePosition()));
        this.mExpandListView.playSoundEffect(0);
        this.mExpandListView.setSelectedGroup(packedPositionGroup);
    }

    @Override // com.byd.music.view.HeaderExpandListview.HeaderAdapter
    public void headerViewArrowClick() {
        int packedPositionGroup = ExpandableListView.getPackedPositionGroup(this.mExpandListView.getExpandableListPosition(this.mExpandListView.getFirstVisiblePosition()));
        this.mExpandListView.collapseGroup(packedPositionGroup);
        this.expandGroupPos = -1;
        this.mExpandListView.playSoundEffect(0);
        this.mExpandListView.setSelectedGroup(packedPositionGroup);
    }
}
