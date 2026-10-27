package com.byd.music.fragment.localMusic.adpter;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.StringUtils;
import com.byd.music.R;
import com.byd.music.beans.ArtistBean;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter;
import com.byd.music.fragment.localMusic.utils.LocalMusicUtils;
import com.byd.music.utils.LogUtil;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ArtistExpandableSearchListAdapter extends ExpandableListAdapter<ArtistBean> {
    private String keyword;
    private OnGroupClickListener mOnGroupClickListener;

    public interface OnGroupClickListener {
        void onGroupClick();
    }

    public ArtistExpandableSearchListAdapter(List<ArtistBean> list, ExpandableListView expandableListView) {
        super(list, expandableListView);
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter
    public void addChildView(View view, int i, int i2) {
        String string;
        int i3;
        ExpandableListAdapter.ChildViewHolder childViewHolder = (ExpandableListAdapter.ChildViewHolder) view.getTag();
        LocalMusicBean child = getChild(i, i2);
        if (child != null) {
            childViewHolder.musicTitle.setText(LocalMusicUtils.BYD_SHOW_CAR_ARTIST.equals(child.getTitle()) ? view.getContext().getString(R.string.unknown_music) : child.getTitle());
            childViewHolder.artistName.setText(LocalMusicUtils.BYD_SHOW_CAR_ARTIST.equals(child.getArtist()) ? view.getContext().getString(R.string.unknown_artist) : child.getArtist());
            childViewHolder.tvIndexChild.setText(String.valueOf(i + i2 + 2));
            TextView textView = childViewHolder.storage;
            if (child.getStorageType() == 0) {
                i3 = R.string.local_storage;
            } else {
                if (child.getStorageType() != 1) {
                    string = StringUtils.getString(R.string.local_storage_usb);
                    textView.setText(string);
                    this.mExpandListView.setOnChildClickListener(new ExpandableListAdapter.ChildClickListener(child));
                }
                i3 = R.string.local_storage_sdcard;
            }
            string = StringUtils.getString(i3);
            textView.setText(string);
            this.mExpandListView.setOnChildClickListener(new ExpandableListAdapter.ChildClickListener(child));
        }
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter
    public void addGroupView(View view, int i) {
        ExpandableListAdapter.GroupViewHolder groupViewHolder = (ExpandableListAdapter.GroupViewHolder) view.getTag();
        LogUtil.d("Add Group View.");
        if (this.mGroupList == null || this.mGroupList.size() <= 0) {
            return;
        }
        ArtistBean artistBean = (ArtistBean) this.mGroupList.get(i);
        groupViewHolder.musicNum.setText(String.format(groupViewHolder.musicNum.getContext().getString(R.string.musics_nums), Integer.valueOf(artistBean.getmAllMusics().size())));
        setKeyWordColor(groupViewHolder.artistName, LocalMusicUtils.BYD_SHOW_CAR_ARTIST.equals(artistBean.getName()) ? view.getContext().getString(R.string.unknown_artist) : artistBean.getName(), this.keyword);
        groupViewHolder.musicNum.setText(String.format(view.getContext().getString(R.string.musics_nums), Integer.valueOf(artistBean.getmAllMusics().size())));
        if (this.expandGroupPos != -1 && i > this.expandGroupPos) {
            groupViewHolder.tvIndexGroup.setText(String.valueOf(i + 1 + ((ArtistBean) this.mGroupList.get(this.expandGroupPos)).getmAllMusics().size()));
        } else {
            groupViewHolder.tvIndexGroup.setText(String.valueOf(i + 1));
        }
        ArtistExpandSwitchListener artistExpandSwitchListener = new ArtistExpandSwitchListener(i);
        this.mExpandListView.setOnGroupClickListener(artistExpandSwitchListener);
        groupViewHolder.upDownArrow.setOnClickListener(artistExpandSwitchListener);
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
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_artist_search_group, viewGroup, false);
        groupViewHolder.tvIndexGroup = (TextView) viewInflate.findViewById(R.id.tvIndex);
        groupViewHolder.artistName = (TextView) viewInflate.findViewById(R.id.tv_artist_name);
        groupViewHolder.musicNum = (TextView) viewInflate.findViewById(R.id.tv_total_music);
        groupViewHolder.upDownArrow = (ImageView) viewInflate.findViewById(R.id.iv_expand_arrow_artist);
        viewInflate.setTag(groupViewHolder);
        return viewInflate;
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter
    public View newChildView(boolean z, ViewGroup viewGroup) {
        ExpandableListAdapter.ChildViewHolder childViewHolder = new ExpandableListAdapter.ChildViewHolder();
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_artist_search_child, viewGroup, false);
        childViewHolder.tvIndexChild = (TextView) viewInflate.findViewById(R.id.tvIndex);
        childViewHolder.musicTitle = (TextView) viewInflate.findViewById(R.id.tv_music_name);
        childViewHolder.artistName = (TextView) viewInflate.findViewById(R.id.tv_artist_name);
        childViewHolder.storage = (TextView) viewInflate.findViewById(R.id.tv_storage_type);
        viewInflate.setTag(childViewHolder);
        return viewInflate;
    }

    public void setKeyword(String str) {
        this.keyword = str;
    }

    class ArtistExpandSwitchListener implements View.OnClickListener, ExpandableListView.OnGroupClickListener {
        int mPosition;

        public ArtistExpandSwitchListener(int i) {
            this.mPosition = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view == null) {
                return;
            }
            if (ArtistExpandableSearchListAdapter.this.mExpandListView.isGroupExpanded(this.mPosition)) {
                ArtistExpandableSearchListAdapter.this.mExpandListView.collapseGroup(this.mPosition);
                ArtistExpandableSearchListAdapter.this.expandGroupPos = -1;
                ((ImageView) view).setImageResource(R.drawable.icon_dark_public_dowmarrow_s_48);
                return;
            }
            if (ArtistExpandableSearchListAdapter.this.mGroupList != null) {
                int size = ArtistExpandableSearchListAdapter.this.mGroupList.size();
                int i = 0;
                while (true) {
                    if (i < size) {
                        if (ArtistExpandableSearchListAdapter.this.mExpandListView.isGroupExpanded(i) && this.mPosition != i) {
                            ArtistExpandableSearchListAdapter.this.mExpandListView.collapseGroup(i);
                            break;
                        }
                        i++;
                    } else {
                        break;
                    }
                }
            }
            ArtistExpandableSearchListAdapter.this.mExpandListView.expandGroup(this.mPosition);
            ArtistExpandableSearchListAdapter.this.expandGroupPos = this.mPosition;
            ((ImageView) view).setImageResource(R.drawable.icon_dark_public_uparrow_s_48);
        }

        @Override // android.widget.ExpandableListView.OnGroupClickListener
        public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
            if (!(view.getTag() instanceof ExpandableListAdapter.GroupViewHolder)) {
                return true;
            }
            ExpandableListAdapter.GroupViewHolder groupViewHolder = (ExpandableListAdapter.GroupViewHolder) view.getTag();
            this.mPosition = i;
            onClick(groupViewHolder.upDownArrow);
            return true;
        }
    }

    private void setKeyWordColor(TextView textView, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!TextUtils.isEmpty(str2) && str.toLowerCase().contains(str2.toLowerCase())) {
            int iIndexOf = str.toLowerCase().indexOf(str2.toLowerCase());
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new ForegroundColorSpan(ColorUtils.getColor(R.color.text_color_selected)), iIndexOf, str2.length() + iIndexOf, 17);
            textView.setText(spannableString);
            return;
        }
        textView.setText(str);
    }

    public void setOnGroupClickListener(OnGroupClickListener onGroupClickListener) {
        this.mOnGroupClickListener = onGroupClickListener;
    }

    public void removeOnGroupClickListener() {
        this.mOnGroupClickListener = null;
    }

    @Override // android.widget.BaseExpandableListAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
