package com.byd.music.fragment.localMusic.adpter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.ColorUtils;
import com.byd.music.R;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.MusicBgUtil;
import com.byd.music.view.CollectLottieView;
import com.byd.music.view.musicPlayingSlideView.PlayingSlideAnimView;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ExpandableListAdapter<T> extends BaseExpandableListAdapter {
    protected ExpandableListView mExpandListView;
    public List<T> mGroupList;
    protected SelectedGroupListener mSelectedGroupListener;
    private onItemOperateListener onItemOperateListener;
    protected int selectPosition;
    protected String TAG = getClass().getSimpleName();
    protected int expandGroupPos = -1;
    protected boolean mShowOperate = false;
    protected Set<LocalMusicBean> mMusicSelectedSet = new HashSet();
    Set<T> mGroupSelectedSet = new HashSet();

    public interface SelectedGroupListener {
        void onAllSelectedStateChanged(boolean z);

        void onSelectedCountChanged(int i);
    }

    public interface onItemOperateListener {
        void onChildClick(List<LocalMusicBean> list, LocalMusicBean localMusicBean, int i, int i2);

        void onCollectClick(LocalMusicBean localMusicBean);

        void onDeleteClick(LocalMusicBean localMusicBean);
    }

    public abstract void addChildView(View view, int i, int i2);

    public abstract void addGroupView(View view, int i);

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i, int i2) {
        return i2;
    }

    public abstract List<LocalMusicBean> getChildList(int i);

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i) {
        return i;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return false;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public abstract View newChildView(boolean z, ViewGroup viewGroup);

    public abstract View newGroupView(boolean z, ViewGroup viewGroup);

    public ExpandableListAdapter(List<T> list, ExpandableListView expandableListView) {
        this.mGroupList = list;
        this.mExpandListView = expandableListView;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        List<T> list = this.mGroupList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        List<LocalMusicBean> childList = getChildList(i);
        if (childList == null) {
            return 0;
        }
        return childList.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public T getGroup(int i) {
        List<T> list = this.mGroupList;
        if (list == null || list.size() <= 0) {
            return null;
        }
        return this.mGroupList.get(i);
    }

    @Override // android.widget.ExpandableListAdapter
    public LocalMusicBean getChild(int i, int i2) {
        List<LocalMusicBean> childList = getChildList(i);
        if (childList == null) {
            return null;
        }
        return childList.get(i2);
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = newGroupView(z, viewGroup);
        }
        addGroupView(view, i);
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = newChildView(z, viewGroup);
        }
        addChildView(view, i, i2);
        return view;
    }

    public class ChildClickListener implements ExpandableListView.OnChildClickListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        ChildViewHolder childViewHolder;
        LocalMusicBean localMusicBean;

        public ChildClickListener(LocalMusicBean localMusicBean) {
            this.childViewHolder = null;
            this.localMusicBean = localMusicBean;
        }

        public ChildClickListener(ExpandableListAdapter expandableListAdapter, LocalMusicBean localMusicBean, ChildViewHolder childViewHolder) {
            this(localMusicBean);
            this.childViewHolder = childViewHolder;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();
            LogUtil.e(ExpandableListAdapter.this.TAG, "onClick: " + R.id.iv_collect + " =" + view.getId());
            if (R.id.iv_collect == id) {
                if (ExpandableListAdapter.this.onItemOperateListener != null) {
                    if (!MusicPlayHelper.getInstance().isShowCarMusicBean(this.localMusicBean)) {
                        ExpandableListAdapter.this.onItemOperateListener.onCollectClick(this.localMusicBean);
                        LogUtil.e(ExpandableListAdapter.this.TAG, "onClick: " + this.localMusicBean.isCollect());
                        ((ImageView) view).setImageDrawable(view.getContext().getDrawable(this.localMusicBean.isCollect() ? R.drawable.icon_dark_media_playcollection_sel_48 : R.drawable.icon_dark_media_playcollection_48));
                        ChildViewHolder childViewHolder = this.childViewHolder;
                        if (childViewHolder == null || childViewHolder.collectLottiesView == null) {
                            return;
                        }
                        this.childViewHolder.collectLottiesView.startAnimation(this.localMusicBean.isCollect());
                        return;
                    }
                    LogUtil.d(ExpandableListAdapter.this.TAG, "onCollectClick isShowCarMusicBean");
                    return;
                }
                return;
            }
            if (R.id.iv_delete != id || ExpandableListAdapter.this.onItemOperateListener == null) {
                return;
            }
            if (!MusicPlayHelper.getInstance().isShowCarMusicBean(this.localMusicBean)) {
                ExpandableListAdapter.this.onItemOperateListener.onDeleteClick(this.localMusicBean);
            } else {
                LogUtil.d(ExpandableListAdapter.this.TAG, "onDeleteClick isShowCarMusicBean");
            }
        }

        @Override // android.widget.ExpandableListView.OnChildClickListener
        public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
            LocalMusicBean child;
            if (ExpandableListAdapter.this.onItemOperateListener == null || (child = ExpandableListAdapter.this.getChild(i, i2)) == null) {
                return true;
            }
            ExpandableListAdapter.this.onItemOperateListener.onChildClick(ExpandableListAdapter.this.getChildList(i), child, i, i2);
            ExpandableListAdapter.this.notifyDataSetChanged();
            return true;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            LogUtil.d(ExpandableListAdapter.this.TAG, "ChildClickListener onCheckedChanged:buttonView:" + compoundButton.isPressed() + " checked:" + z);
            if (compoundButton.isPressed()) {
                ExpandableListAdapter.this.selectChildItem(this.localMusicBean, z);
            }
        }
    }

    public void setOnItemOperateListener(onItemOperateListener onitemoperatelistener) {
        this.onItemOperateListener = onitemoperatelistener;
    }

    public void removeOnItemOperateListener() {
        this.onItemOperateListener = null;
    }

    class ChildViewHolder {
        TextView artistName;
        CheckBox childCheckBox;
        ImageView collectIv;
        CollectLottieView collectLottiesView;
        ImageView deleteIv;
        ImageView ivPlaying;
        TextView musicTitle;
        PlayingSlideAnimView playSlideView;
        ImageView playingBackGround;
        TextView storage;
        TextView tvIndexChild;

        ChildViewHolder() {
        }
    }

    class GroupViewHolder {
        ImageView albumCover;
        TextView albumName;
        TextView artistName;
        CheckBox groupCheckBox;
        TextView musicNum;
        TextView tvIndexGroup;
        ImageView upDownArrow;

        GroupViewHolder() {
        }
    }

    public int getExpandGroupPos() {
        if (this.mGroupList.size() > 0) {
            return this.expandGroupPos;
        }
        return -1;
    }

    public void setExpandGroupPos(int i) {
        this.expandGroupPos = i;
    }

    public boolean getShowOperate() {
        return this.mShowOperate;
    }

    public void setShowOperate(boolean z) {
        this.mShowOperate = z;
    }

    public int getSelectPosition() {
        return this.selectPosition;
    }

    public void setSelectPosition(int i) {
        this.selectPosition = i;
    }

    public void selectAllMusicGroup(int i, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("selectAllMusic,groupPosition=");
        sb.append(i);
        sb.append(" isChecked=");
        sb.append(z);
        LogUtil.d(this.TAG, sb.toString());
        if (z) {
            this.mGroupSelectedSet.add(getGroup(i));
            List<LocalMusicBean> childList = getChildList(i);
            if (childList != null) {
                this.mMusicSelectedSet.addAll(childList);
            }
            boolean zContainsAll = this.mGroupSelectedSet.containsAll(this.mGroupList);
            sb.append(" isSelectAll:");
            sb.append(zContainsAll);
            LogUtil.d(this.TAG, sb.toString());
            if (zContainsAll) {
                this.mSelectedGroupListener.onAllSelectedStateChanged(true);
            }
        } else {
            if (this.mGroupSelectedSet.contains(getGroup(i))) {
                this.mGroupSelectedSet.remove(getGroup(i));
            }
            List<LocalMusicBean> childList2 = getChildList(i);
            if (childList2 != null) {
                this.mMusicSelectedSet.removeAll(childList2);
            }
            if (this.mGroupSelectedSet.size() < this.mGroupList.size()) {
                this.mSelectedGroupListener.onAllSelectedStateChanged(false);
            }
        }
        notifyDataSetChanged();
        notifySelectedCountChanged();
    }

    public void selectAllMusicGroupLongClick(int i, boolean z) {
        int i2 = this.expandGroupPos;
        if (i2 != -1 && i2 < i) {
            i -= getChildrenCount(i2);
        }
        selectAllMusicGroup(i, z);
    }

    public void selectChildItem(LocalMusicBean localMusicBean, boolean z) {
        LogUtil.d(this.TAG, "selectChildItem localMusicBean=" + localMusicBean.getTitle() + " isChecked=" + z);
    }

    public void selectAll() {
        this.mGroupSelectedSet.addAll(this.mGroupList);
    }

    public void cancelSelectAll() {
        this.mGroupSelectedSet.clear();
        this.mMusicSelectedSet.clear();
        notifyDataSetChanged();
        notifySelectedCountChanged();
    }

    protected void notifySelectedCountChanged() {
        Set<LocalMusicBean> set = this.mMusicSelectedSet;
        this.mSelectedGroupListener.onSelectedCountChanged(set != null ? set.size() : 0);
    }

    public void setSelectedGroupListener(SelectedGroupListener selectedGroupListener) {
        this.mSelectedGroupListener = selectedGroupListener;
    }

    protected void setMusicSlidePlaying(PlayingSlideAnimView playingSlideAnimView, boolean z, boolean z2) {
        MusicBgUtil.traverseViewGroup(playingSlideAnimView, ColorUtils.getColor(R.color.color_text_selected_1));
        if (playingSlideAnimView == null) {
            return;
        }
        playingSlideAnimView.setVisibility(z2 ? 0 : 8);
        if (!z2) {
            playingSlideAnimView.cancel();
        } else if (z) {
            playingSlideAnimView.start();
        } else {
            playingSlideAnimView.pause();
        }
    }

    protected Boolean isPlayMusic(LocalMusicBean localMusicBean) {
        if (localMusicBean.equals(MusicPlayHelper.getInstance().getCurrentPlayLocalMusic())) {
            return true;
        }
        return false;
    }

    public Set<LocalMusicBean> getMusicSelectedSet() {
        return this.mMusicSelectedSet;
    }

    public Set<T> getGroupSelectedSet() {
        return this.mGroupSelectedSet;
    }

    public void setMusicSelectedSet(Set<LocalMusicBean> set) {
        this.mMusicSelectedSet.addAll(set);
    }

    public void setGroupSelectedSet(Set<T> set) {
        this.mGroupSelectedSet.addAll(set);
    }
}
