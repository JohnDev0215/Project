package com.byd.music.fragment.localMusic.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.Utils;
import com.byd.music.R;
import com.byd.music.beans.AlbumBean;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter;
import com.byd.music.fragment.localMusic.utils.LocalMusicUtils;
import com.byd.music.utils.LogUtil;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AlbumExpandableSearchListAdapter extends ExpandableListAdapter<AlbumBean> {
    private OnExpandListener mExpandListener;

    public interface OnExpandListener {
        void onExpandGroup(AlbumBean albumBean);

        void onGroupClick(AlbumBean albumBean);
    }

    public AlbumExpandableSearchListAdapter(List<AlbumBean> list, ExpandableListView expandableListView) {
        super(list, expandableListView);
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter
    public void addChildView(View view, int i, int i2) {
        ExpandableListAdapter.ChildViewHolder childViewHolder = (ExpandableListAdapter.ChildViewHolder) view.getTag();
        LocalMusicBean child = getChild(i, i2);
        if (child != null) {
            childViewHolder.musicTitle.setText(LocalMusicUtils.BYD_SHOW_CAR_ARTIST.equals(child.getTitle()) ? view.getContext().getString(R.string.unknown_music) : child.getTitle());
            childViewHolder.tvIndexChild.setText(String.valueOf(i + i2 + 2));
            ExpandableListAdapter.ChildClickListener childClickListener = new ExpandableListAdapter.ChildClickListener(child);
            this.mExpandListView.setOnChildClickListener(childClickListener);
            childViewHolder.deleteIv.setOnClickListener(childClickListener);
            childViewHolder.collectIv.setOnClickListener(childClickListener);
            childViewHolder.collectIv.setImageDrawable(view.getContext().getDrawable(child.isCollect() ? R.drawable.icon_dark_media_playcollection_sel_48 : R.drawable.icon_dark_media_playcollection_48));
        }
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter
    public void addGroupView(View view, int i) {
        ExpandableListAdapter.GroupViewHolder groupViewHolder = (ExpandableListAdapter.GroupViewHolder) view.getTag();
        if (this.mGroupList == null || this.mGroupList.size() <= 0) {
            return;
        }
        AlbumBean albumBean = (AlbumBean) this.mGroupList.get(i);
        groupViewHolder.albumName.setText(LocalMusicUtils.BYD_SHOW_CAR_ARTIST.equals(albumBean.getName()) ? view.getContext().getString(R.string.unknown_album) : albumBean.getName());
        groupViewHolder.artistName.setText(LocalMusicUtils.BYD_SHOW_CAR_ARTIST.equals(albumBean.getArtist()) ? view.getContext().getString(R.string.unknown_artist) : albumBean.getArtist());
        groupViewHolder.musicNum.setText(String.format(groupViewHolder.musicNum.getContext().getString(R.string.musics_nums), Integer.valueOf(albumBean.getmAllMusics().size())));
        if (this.expandGroupPos != -1 && i > this.expandGroupPos) {
            groupViewHolder.tvIndexGroup.setText(String.valueOf(i + 1 + ((AlbumBean) this.mGroupList.get(this.expandGroupPos)).getmAllMusics().size()));
        } else {
            groupViewHolder.tvIndexGroup.setText(String.valueOf(i + 1));
        }
        groupViewHolder.albumCover.setImageBitmap(LocalMusicUtils.getAlbumPictureFromPath(Utils.getApp().getApplicationContext(), albumBean.getmAllMusics().get(0).getUri()));
        ExpandSwitchListener expandSwitchListener = new ExpandSwitchListener(i);
        this.mExpandListView.setOnGroupClickListener(expandSwitchListener);
        groupViewHolder.upDownArrow.setOnClickListener(expandSwitchListener);
        if (this.mExpandListView.isGroupExpanded(i)) {
            groupViewHolder.upDownArrow.setImageDrawable(view.getContext().getDrawable(R.drawable.icon_dark_public_uparrow_s_48));
        } else {
            groupViewHolder.upDownArrow.setImageDrawable(view.getContext().getDrawable(R.drawable.icon_dark_public_dowmarrow_s_48));
        }
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter
    public List<LocalMusicBean> getChildList(int i) {
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
        viewInflate.setTag(childViewHolder);
        return viewInflate;
    }

    class ExpandSwitchListener implements View.OnClickListener, ExpandableListView.OnGroupClickListener {
        int mPosition;

        ExpandSwitchListener(int i) {
            this.mPosition = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (AlbumExpandableSearchListAdapter.this.mExpandListView.isGroupExpanded(this.mPosition)) {
                AlbumExpandableSearchListAdapter.this.mExpandListView.collapseGroup(this.mPosition);
                AlbumExpandableSearchListAdapter.this.expandGroupPos = -1;
                ((ImageView) view).setImageResource(R.drawable.icon_dark_public_dowmarrow_s_48);
                return;
            }
            if (AlbumExpandableSearchListAdapter.this.mGroupList != null) {
                int size = AlbumExpandableSearchListAdapter.this.mGroupList.size();
                int i = 0;
                while (true) {
                    if (i < size) {
                        if (AlbumExpandableSearchListAdapter.this.mExpandListView.isGroupExpanded(i) && this.mPosition != i) {
                            AlbumExpandableSearchListAdapter.this.mExpandListView.collapseGroup(i);
                            break;
                        }
                        i++;
                    } else {
                        break;
                    }
                }
            }
            AlbumExpandableSearchListAdapter.this.mExpandListView.expandGroup(this.mPosition);
            AlbumExpandableSearchListAdapter.this.expandGroupPos = this.mPosition;
            ((ImageView) view).setImageResource(R.drawable.icon_dark_public_uparrow_s_48);
        }

        @Override // android.widget.ExpandableListView.OnGroupClickListener
        public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
            if (AlbumExpandableSearchListAdapter.this.mGroupList == null || AlbumExpandableSearchListAdapter.this.mGroupList.size() <= 0) {
                return true;
            }
            LogUtil.e(AlbumExpandableSearchListAdapter.this.TAG, "onGroupClick: ");
            MusicPlayHelper.getInstance().playListByItem(((AlbumBean) AlbumExpandableSearchListAdapter.this.mGroupList.get(i)).getmAllMusics(), ((AlbumBean) AlbumExpandableSearchListAdapter.this.mGroupList.get(i)).getmAllMusics().get(0));
            return true;
        }
    }
}
