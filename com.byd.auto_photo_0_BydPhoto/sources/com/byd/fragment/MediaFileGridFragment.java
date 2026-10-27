package com.byd.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.a.d;
import b.a.e.g;
import b.a.e.s;
import b.a.e.t;
import b.a.e.u;
import b.a.h.l;
import com.byd.auto_photo.ImageShowActivity;
import com.byd.auto_photo.MainActivity;
import com.byd.auto_photo.R;
import com.byd.auto_photo.VideoPlayActivity;
import com.byd.bean.FileBean;
import com.byd.bean.FileBeanInGroup;
import com.byd.fragment.ClearHistoryDialogFragment;
import com.byd.taskmanager.b.a;
import com.byd.taskmanager.b.c;
import com.byd.util.e;
import com.byd.util.f;
import com.byd.util.r;
import com.byd.util.w;
import com.byd.view.AnimViewSwitcher;
import com.byd.view.FloatingLayout;
import com.byd.view.GridItemDecoration;
import com.byd.view.WrapGridLayoutManager;
import java.io.File;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MediaFileGridFragment extends MediaFileGridBaseFragment implements View.OnClickListener, MainActivity.c {
    private static final int MSG_CHECK_MODE = 1;
    private g adapterMonth;
    private g adapterYear;
    private ClearHistoryDialogFragment clearDialog;
    private FileDetailDialogFragment dialogDetail;
    private boolean hasSearchResult;
    private View historyContainer;
    private FloatingLayout historyView;
    private List<String> keyWords;
    private RecyclerView listViewMonth;
    private RecyclerView listViewYear;
    private Handler mHandler;
    private AnimViewSwitcher mSwitcher;
    private TextView tvHistoryTitle;
    private TextView tvSearchResultNum;
    private ImageView viewClearHistory;
    private View viewSearchResultTips;
    private int viewSwitchIndex;
    private AnimViewSwitcher.OnViewSwitchListener onViewSwitchListener = new AnimViewSwitcher.OnViewSwitchListener() { // from class: com.byd.fragment.MediaFileGridFragment.2
        @Override // com.byd.view.AnimViewSwitcher.OnViewSwitchListener
        public void onPointerDown() {
            w.c("AnimViewSwitcher onPointerDown");
            MediaFileGridFragment mediaFileGridFragment = MediaFileGridFragment.this;
            mediaFileGridFragment.disableItemTouch = true;
            ((s) mediaFileGridFragment.adapter).z();
        }

        @Override // com.byd.view.AnimViewSwitcher.OnViewSwitchListener
        public void onPointerUp() {
            w.c("AnimViewSwitcher onPointerUp");
            MediaFileGridFragment.this.disableItemTouch = false;
        }

        @Override // com.byd.view.AnimViewSwitcher.OnViewSwitchListener
        public void onViewSwitched(int i) {
            MediaFileGridFragment.this.viewSwitchIndex = i;
            w.c("onViewSwitched : " + MediaFileGridFragment.this.viewSwitchIndex);
            MediaFileGridFragment.this.checkShowLevelConflict();
        }
    };
    private g.c onClassifyAdapterListener = new g.c() { // from class: com.byd.fragment.MediaFileGridFragment.3
        @Override // b.a.e.g.c
        public void onClassifyItemClick(FileBeanInGroup fileBeanInGroup, int i) {
            if (i == 1) {
                MediaFileGridFragment.this.listViewMonth.scrollToPosition(MediaFileGridFragment.this.adapterMonth.e(fileBeanInGroup.getFileBean().getPath()));
                MediaFileGridFragment.this.mSwitcher.switchTo(1, true);
            } else {
                MediaFileGridFragment.this.adapter.r(fileBeanInGroup.getFileBean().getPath());
                MediaFileGridFragment mediaFileGridFragment = MediaFileGridFragment.this;
                mediaFileGridFragment.photoListView.scrollToPosition(mediaFileGridFragment.adapter.f());
                MediaFileGridFragment.this.mSwitcher.switchTo(0, true);
            }
        }
    };
    private FloatingLayout.OnItemClickListener onKeyWordItemClick = new FloatingLayout.OnItemClickListener() { // from class: com.byd.fragment.MediaFileGridFragment.7
        @Override // com.byd.view.FloatingLayout.OnItemClickListener
        public void onItemClick(int i) {
            if (MediaFileGridFragment.this.keyWords == null || i >= MediaFileGridFragment.this.keyWords.size()) {
                return;
            }
            MediaFileGridFragment.this.searchAndSelectFragment.startSearch((String) MediaFileGridFragment.this.keyWords.get(i), 0);
        }
    };

    private static class MHandler extends d<MediaFileGridFragment> {
        public MHandler(MediaFileGridFragment mediaFileGridFragment) {
            super(mediaFileGridFragment);
        }

        @Override // b.a.d
        public void handleMessage(MediaFileGridFragment mediaFileGridFragment, Message message) {
            if (message.what != 1) {
                return;
            }
            mediaFileGridFragment.checkMode();
        }
    }

    public MediaFileGridFragment() {
        this.ITEM_SPACING = 0;
        this.mHandler = new MHandler(this);
    }

    private boolean checkCanSwitch() {
        t tVar = this.adapter;
        return this.isGroup && this.showLevel == 0 && !(tVar != null ? tVar.j() : false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkMode() {
        if (this.viewSwitchIndex == 0 || !this.adapter.j()) {
            return;
        }
        w.c("resume view switcher on select mode");
        this.mSwitcher.switchTo(0, false, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkShowLevelConflict() {
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessageDelayed(1, 500L);
    }

    private int getClassifyItemNum(int i, int i2) {
        return i == 2 ? i2 == 0 ? 10 : 15 : i2 == 0 ? 7 : 11;
    }

    private void initRecyclerView(RecyclerView recyclerView, final g gVar, int i) {
        GridLayoutManager wrapGridLayoutManager;
        int i2 = getResources().getConfiguration().orientation;
        final int classifyItemNum = getClassifyItemNum(i2, gVar.f());
        int mediaShowGridWidth = getMediaShowGridWidth(i2);
        if (recyclerView.getLayoutManager() == null) {
            wrapGridLayoutManager = new WrapGridLayoutManager(getActivity(), classifyItemNum);
            recyclerView.setLayoutManager(wrapGridLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(null);
            recyclerView.addItemDecoration(new GridItemDecoration(i));
        } else {
            wrapGridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            wrapGridLayoutManager.setSpanCount(classifyItemNum);
        }
        gVar.d(mediaShowGridWidth, classifyItemNum, i);
        wrapGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.byd.fragment.MediaFileGridFragment.4
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i3) {
                if (gVar.getItemCount() == 0) {
                    return 0;
                }
                if (gVar.getItemViewType(i3) == 1) {
                    return classifyItemNum;
                }
                return 1;
            }
        });
        recyclerView.setAdapter(gVar);
    }

    private void intoFullScreenPreview(FileBean fileBean, boolean z, boolean z2) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        if (z) {
            f.k(getActivity()).d(((MainActivity) getActivity()).y0());
        }
        if (z2 && fileBean.getType() == 2) {
            Intent intent = new Intent(activity, (Class<?>) VideoPlayActivity.class);
            intent.setData(Uri.fromFile(new File(fileBean.getPath())));
            startActivity(intent);
            return;
        }
        l.m(activity).u();
        Intent intent2 = new Intent(activity, (Class<?>) ImageShowActivity.class);
        intent2.putExtra("from_main", true);
        startActivityForResult(intent2, 2);
        if (this.isGroup) {
            r.h().o(((b.a.e.r) this.adapter).g(), fileBean);
        } else {
            r.h().m(((u) this.adapter).g(), fileBean);
        }
    }

    private void loadHistory() {
        if (!isTaskExist(1)) {
            addTask(1, new a<List<String>>() { // from class: com.byd.fragment.MediaFileGridFragment.5
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.byd.taskmanager.b.a
                public List<String> doTask() {
                    return f.k(MediaFileGridFragment.this.getActivity()).h();
                }
            }, new c<List<String>>() { // from class: com.byd.fragment.MediaFileGridFragment.6
                @Override // com.byd.taskmanager.b.b
                public void onComplete(String str, List<String> list) {
                    if (list == null || list.size() <= 0) {
                        MediaFileGridFragment.this.viewClearHistory.setVisibility(4);
                        MediaFileGridFragment.this.historyContainer.setVisibility(8);
                        return;
                    }
                    w.c("data num:" + MediaFileGridFragment.this.datasNum + " " + MediaFileGridFragment.this.adapter.getItemCount());
                    MediaFileGridFragment mediaFileGridFragment = MediaFileGridFragment.this;
                    int i = mediaFileGridFragment.datasNum;
                    if (i > 0 && i != mediaFileGridFragment.adapter.getItemCount()) {
                        MediaFileGridFragment mediaFileGridFragment2 = MediaFileGridFragment.this;
                        mediaFileGridFragment2.datasNum = mediaFileGridFragment2.adapter.getItemCount();
                        MediaFileGridFragment mediaFileGridFragment3 = MediaFileGridFragment.this;
                        mediaFileGridFragment3.showSearchResultNum(mediaFileGridFragment3.datasNum);
                        MediaFileGridFragment mediaFileGridFragment4 = MediaFileGridFragment.this;
                        if (mediaFileGridFragment4.datasNum == 0) {
                            mediaFileGridFragment4.hasSearchResult = false;
                        }
                    }
                    if (MediaFileGridFragment.this.hasSearchResult || ((MainActivity) MediaFileGridFragment.this.getActivity()).y0() != null) {
                        MediaFileGridFragment.this.historyContainer.setVisibility(8);
                    } else {
                        MediaFileGridFragment.this.historyContainer.setVisibility(0);
                    }
                    if (MediaFileGridFragment.this.viewClearHistory.getVisibility() != 0) {
                        MediaFileGridFragment.this.viewClearHistory.setVisibility(0);
                    }
                    MediaFileGridFragment.this.keyWords = list;
                    MediaFileGridFragment.this.historyView.setTags(list);
                }

                @Override // com.byd.taskmanager.b.b
                public void onStart() {
                }
            });
        }
        executeTask(1);
    }

    private void prepareMonthAndYear() {
        if (this.isGroup && this.showLevel == 0) {
            List listG = this.adapter.g();
            if (listG == null || listG.size() < 3) {
                this.adapterMonth.g(null);
                this.adapterYear.g(null);
                this.mSwitcher.setCanSwitch(false);
                if (this.mSwitcher.getCurrentIndex() != 0) {
                    this.mSwitcher.switchTo(0, false, true);
                    return;
                }
                return;
            }
            int size = listG.size();
            LinkedList linkedList = new LinkedList();
            LinkedList linkedList2 = new LinkedList();
            Calendar calendar = Calendar.getInstance();
            int i = 0;
            int i2 = -1;
            for (int i3 = 0; i3 < size; i3++) {
                FileBeanInGroup fileBeanInGroup = (FileBeanInGroup) listG.get(i3);
                if (fileBeanInGroup.getType() != 1) {
                    linkedList.add(fileBeanInGroup);
                    linkedList2.add(fileBeanInGroup);
                } else {
                    calendar.setTimeInMillis(fileBeanInGroup.getDate());
                    int i4 = calendar.get(1);
                    int i5 = calendar.get(2);
                    boolean z = i == 0 || i != i4;
                    if (i2 == -1 || i2 != i5 || z) {
                        linkedList.add(fileBeanInGroup);
                        i2 = i5;
                    }
                    if (z) {
                        linkedList2.add(fileBeanInGroup);
                        i = i4;
                    }
                }
            }
            this.mSwitcher.setCanSwitch(checkCanSwitch());
            this.adapterMonth.g(linkedList);
            this.adapterYear.g(linkedList2);
        }
    }

    @RequiresApi(api = 29)
    private void setScrollbarStyle(RecyclerView recyclerView, Activity activity) {
        recyclerView.setVerticalScrollbarTrackDrawable(activity.getResources().getDrawable(R.drawable.scrollbar_vertical_track, activity.getTheme()));
        recyclerView.setVerticalScrollbarThumbDrawable(activity.getResources().getDrawable(R.drawable.scrollbar_vertical_thumb, activity.getTheme()));
    }

    private void showClearHistoryDialog() {
        if (this.clearDialog == null) {
            ClearHistoryDialogFragment clearHistoryDialogFragment = new ClearHistoryDialogFragment();
            this.clearDialog = clearHistoryDialogFragment;
            clearHistoryDialogFragment.setOnClearHistoryListener(new ClearHistoryDialogFragment.OnClearHistoryListener() { // from class: com.byd.fragment.MediaFileGridFragment.8
                @Override // com.byd.fragment.ClearHistoryDialogFragment.OnClearHistoryListener
                public void onClearFinish() {
                    MediaFileGridFragment.this.historyView.removeAllViews();
                    if (MediaFileGridFragment.this.viewClearHistory.getVisibility() == 0) {
                        MediaFileGridFragment.this.viewClearHistory.setVisibility(8);
                    }
                    MediaFileGridFragment.this.historyContainer.setVisibility(8);
                    MediaFileGridFragment.this.keyWords = null;
                }
            });
        }
        this.clearDialog.show(getActivity().getSupportFragmentManager(), "clear_history");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSearchResultNum(int i) {
        TextView textView;
        String str;
        if (i < 2) {
            textView = this.tvSearchResultNum;
            str = String.format(getResources().getString(R.string.search_result_single_tips), Integer.valueOf(i));
        } else {
            textView = this.tvSearchResultNum;
            str = String.format(getResources().getString(R.string.search_result_num_tips), Integer.valueOf(i));
        }
        textView.setText(str);
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment, com.byd.fragment.MediaDataBaseFragment
    public void dismissAllDialog() {
        super.dismissAllDialog();
        FileDetailDialogFragment fileDetailDialogFragment = this.dialogDetail;
        if (fileDetailDialogFragment == null || !fileDetailDialogFragment.isShowing()) {
            return;
        }
        this.dialogDetail.dismissAllowingStateLoss();
        this.dialogDetail = null;
    }

    @Override // com.byd.ui.a
    protected int getLayout() {
        return R.layout.fragment_photo3_list;
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment
    protected int getMediaShowGridWidth(int i) {
        int mainContainerWidth = getMainContainerWidth();
        if (i == 2) {
            if (isInSearchMode()) {
                int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.search_page_result_margin_left);
                return ((mainContainerWidth - (getResources().getDimensionPixelSize(R.dimen.content_padding) * 2)) - dimensionPixelSize) - getResources().getDimensionPixelSize(R.dimen.search_page_input_margin_right);
            }
            mainContainerWidth -= getResources().getDimensionPixelSize(R.dimen.tab_size);
        }
        return mainContainerWidth - (getResources().getDimensionPixelSize(R.dimen.content_padding) * 2);
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment
    protected int getNumColumns(int i) {
        return isInSearchMode() ? i == 2 ? 8 : 4 : i == 2 ? 7 : 4;
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment
    protected boolean isOutOfFragment() {
        FileDetailDialogFragment fileDetailDialogFragment = this.dialogDetail;
        if (fileDetailDialogFragment != null && fileDetailDialogFragment.isShowing()) {
            return true;
        }
        ClearHistoryDialogFragment clearHistoryDialogFragment = this.clearDialog;
        if (clearHistoryDialogFragment == null || !clearHistoryDialogFragment.isShowing()) {
            return super.isOutOfFragment();
        }
        return true;
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        w.c("==MediaFileGridFragment onActivityResult==" + i);
        if (i == 2 && this.adapter != null && intent != null && intent.hasExtra("photo_intent_data")) {
            if (this.adapter.r(intent.getStringExtra("photo_intent_data")) != null) {
                this.photoListView.scrollToPosition(this.adapter.f());
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.view_clear_all_history) {
            return;
        }
        showClearHistoryDialog();
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment
    protected void onConfigPastDialog(PastFilesDialogFragment pastFilesDialogFragment) {
        pastFilesDialogFragment.setSoftInputMode(35);
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment, com.byd.fragment.SubBaseFragment, com.byd.ui.a, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((MainActivity) getActivity()).g1(this);
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment, com.byd.util.l.i
    public void onDeleteComplete(List<String> list) {
        super.onDeleteComplete(list);
        prepareMonthAndYear();
        toastMsg(R.string.delete_finish, true);
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment, com.byd.fragment.SubBaseFragment, com.byd.ui.a, androidx.fragment.app.Fragment
    public void onDestroy() {
        this.hasSearchResult = false;
        ((MainActivity) getActivity()).k1(this);
        super.onDestroy();
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment, com.byd.ui.a, androidx.fragment.app.Fragment
    public void onDestroyView() {
        if (isInSearchMode() && this.viewEmpty.getVisibility() == 0) {
            this.viewEmpty.setVisibility(8);
        }
        super.onDestroyView();
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment, b.a.e.t.c
    public void onItemClick(FileBean fileBean, int i, View view) {
        if (this.mSwitcher.isMultiTouch()) {
            return;
        }
        if (isInSearchMode()) {
            intoFullScreenPreview(fileBean, true, false);
        } else {
            if (e.y()) {
                return;
            }
            if (this.adapter.j()) {
                doSelectOrCancel(fileBean, i);
            } else {
                intoFullScreenPreview(fileBean, false, false);
            }
        }
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment, b.a.e.t.c
    public void onItemDoubleClick(FileBean fileBean, int i, View view) {
        if (isInSearchMode()) {
            intoFullScreenPreview(fileBean, true, true);
        } else {
            if (this.adapter.j()) {
                return;
            }
            intoFullScreenPreview(fileBean, false, true);
        }
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment, b.a.e.t.c
    public void onItemLongClick(FileBean fileBean, int i, View view) {
        if (this.mSwitcher.isMultiTouch()) {
            return;
        }
        super.onItemLongClick(fileBean, i, view);
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment
    protected void onLoadDataFinish() {
        prepareMonthAndYear();
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment, com.byd.fragment.SubBaseFragment
    protected void onLocaleChanged(Configuration configuration) {
        super.onLocaleChanged(configuration);
        if (isInSearchMode()) {
            showSearchResultNum(this.datasNum);
            this.tvHistoryTitle.setText(R.string.history);
            return;
        }
        g gVar = this.adapterMonth;
        if (gVar != null) {
            gVar.notifyDataSetChanged();
        }
        g gVar2 = this.adapterYear;
        if (gVar2 != null) {
            gVar2.notifyDataSetChanged();
        }
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (isInSearchMode()) {
            loadHistory();
        }
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment
    public void onSearchEditorAction() {
        List<String> listH = f.k(getActivity()).h();
        this.keyWords = listH;
        FloatingLayout floatingLayout = this.historyView;
        if (floatingLayout != null) {
            floatingLayout.setTags(listH);
            if (this.viewClearHistory.getVisibility() != 0) {
                this.viewClearHistory.setVisibility(0);
            }
        }
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment, com.byd.fragment.SearchAndSelectBaseFragment.OnHeaderListener
    public void onSelectModeChange(boolean z) {
        super.onSelectModeChange(z);
        boolean zCheckCanSwitch = checkCanSwitch();
        t tVar = this.adapter;
        if (tVar != null && tVar.getItemCount() < 3) {
            zCheckCanSwitch = false;
        }
        this.mSwitcher.setCanSwitch(zCheckCanSwitch);
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment
    protected void onSelectSizeChange(int i) {
        super.onSelectSizeChange(i);
        this.searchAndSelectFragment.onSelectedSizeChange(i);
        checkShowLevelConflict();
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment, com.byd.fragment.SearchAndSelectBaseFragment.OnHeaderListener
    public void onShowDetail() {
        List<FileBean> listI = this.adapter.i();
        if (listI == null || listI.size() != 1) {
            return;
        }
        FileBean fileBean = listI.get(0);
        if (this.dialogDetail == null) {
            FileDetailDialogFragment fileDetailDialogFragment = new FileDetailDialogFragment();
            this.dialogDetail = fileDetailDialogFragment;
            fileDetailDialogFragment.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.byd.fragment.MediaFileGridFragment.9
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    MediaFileGridFragment.this.checkToRefresh();
                }
            });
        }
        this.dialogDetail.setFileBean(fileBean);
        this.dialogDetail.show(getActivity().getSupportFragmentManager(), "detail");
    }

    @Override // com.byd.auto_photo.MainActivity.c
    public void onThemeChange() {
        w.c("MediaFileGrid on theme change");
        FragmentActivity activity = getActivity();
        if (activity == null) {
            w.c("MediaFileGrid on theme change : activity is null!!");
            return;
        }
        t tVar = this.adapter;
        if (tVar != null) {
            tVar.y(activity);
        }
        if (isInSearchMode()) {
            this.tvSearchResultNum.setTextColor(e.l(activity, R.attr.search_input_text_color));
            this.tvHistoryTitle.setTextColor(e.l(activity, R.attr.history_text_color));
            this.viewClearHistory.setImageDrawable(e.p(activity, R.attr.icon_clear_history));
            this.historyView.setItemBgRes(R.drawable.tag_bg);
            this.historyView.setTextColor(e.l(activity, R.attr.text_color_for_kw));
            this.historyView.removeAllViews();
            loadHistory();
        } else {
            g gVar = this.adapterMonth;
            if (gVar != null) {
                gVar.i();
            }
            g gVar2 = this.adapterYear;
            if (gVar2 != null) {
                gVar2.i();
            }
        }
        this.viewEmpty.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, e.p(activity, R.attr.empty_drawable), (Drawable) null, (Drawable) null);
        if (Build.VERSION.SDK_INT >= 29) {
            setScrollbarStyle(this.photoListView, activity);
            RecyclerView recyclerView = this.listViewMonth;
            if (recyclerView != null) {
                setScrollbarStyle(recyclerView, activity);
            }
            RecyclerView recyclerView2 = this.listViewYear;
            if (recyclerView2 != null) {
                setScrollbarStyle(recyclerView2, activity);
            }
        }
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        this.viewSearchResultTips = view.findViewById(R.id.layout_search_result_tips);
        this.tvSearchResultNum = (TextView) view.findViewById(R.id.tv_search_result_num_tip);
        this.tvHistoryTitle = (TextView) view.findViewById(R.id.tv_history_title);
        this.mSwitcher = (AnimViewSwitcher) view.findViewById(R.id.switcher);
        if (isInSearchMode()) {
            this.mSwitcher.setCanSwitch(false);
            this.historyContainer = view.findViewById(R.id.history_layout);
            FloatingLayout floatingLayout = (FloatingLayout) findView(R.id.kw_container);
            this.historyView = floatingLayout;
            floatingLayout.setOnItemClickListener(this.onKeyWordItemClick);
            ImageView imageView = (ImageView) view.findViewById(R.id.view_clear_all_history);
            this.viewClearHistory = imageView;
            imageView.setOnClickListener(this);
            super.onViewCreated(view, bundle);
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.search_page_result_margin_left);
            int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.search_page_input_margin_right);
            this.photoListView.setPadding(dimensionPixelSize, 0, dimensionPixelSize2, 0);
            this.viewSearchResultTips.setPadding(dimensionPixelSize, 0, dimensionPixelSize2, 0);
            return;
        }
        super.onViewCreated(view, bundle);
        boolean zCheckCanSwitch = checkCanSwitch();
        t tVar = this.adapter;
        if (tVar != null && tVar.getItemCount() > 0 && this.adapter.getItemCount() < 3) {
            zCheckCanSwitch = false;
        }
        this.mSwitcher.setCanSwitch(zCheckCanSwitch);
        this.mSwitcher.setOnViewSwitchListener(this.onViewSwitchListener);
        this.listViewMonth = (RecyclerView) view.findViewById(R.id.photo_list_month);
        this.listViewYear = (RecyclerView) view.findViewById(R.id.photo_list_year);
        if (this.adapterMonth == null) {
            g gVar = new g(getActivity(), 0);
            this.adapterMonth = gVar;
            gVar.h(this.onClassifyAdapterListener);
        }
        if (this.adapterYear == null) {
            g gVar2 = new g(getActivity(), 1);
            this.adapterYear = gVar2;
            gVar2.h(this.onClassifyAdapterListener);
        }
        initRecyclerView(this.listViewMonth, this.adapterMonth, getResources().getDimensionPixelSize(R.dimen.dp_7_5));
        initRecyclerView(this.listViewYear, this.adapterYear, getResources().getDimensionPixelSize(R.dimen.dp_4_5));
        if (this.viewSwitchIndex != 0) {
            this.mSwitcher.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.byd.fragment.MediaFileGridFragment.1
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    MediaFileGridFragment.this.mSwitcher.getViewTreeObserver().removeOnPreDrawListener(this);
                    MediaFileGridFragment.this.mSwitcher.switchTo(MediaFileGridFragment.this.viewSwitchIndex, false);
                    return true;
                }
            });
        }
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment
    protected void refreshAfterMove() {
        prepareMonthAndYear();
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment
    public void setSearchResultDatas(com.byd.util.u uVar, int i) {
        this.hasSearchResult = uVar != null && uVar.e() > 0;
        if (isInSearchMode() && this.historyContainer != null) {
            if (!this.hasSearchResult) {
                this.photoListView.setVisibility(8);
                this.viewSearchResultTips.setVisibility(8);
                if (((MainActivity) getActivity()).y0() != null) {
                    this.viewEmpty.setVisibility(0);
                    this.historyContainer.setVisibility(8);
                } else {
                    List<String> list = this.keyWords;
                    if (list != null && list.size() > 0) {
                        this.historyContainer.setVisibility(0);
                    }
                    this.viewEmpty.setVisibility(8);
                }
                this.datasNum = 0;
                if (this.adapter != null) {
                    this.photoListView.stopScroll();
                    this.photoListView.stopNestedScroll();
                    this.adapter.t(null);
                    return;
                }
                return;
            }
            this.photoListView.setVisibility(0);
            this.historyContainer.setVisibility(8);
            this.viewEmpty.setVisibility(8);
            this.viewSearchResultTips.setVisibility(0);
            showSearchResultNum(i);
        }
        super.setSearchResultDatas(uVar, i);
    }

    @Override // com.byd.fragment.MediaFileGridBaseFragment
    public void setSearchSource(int i) {
        if (i != 10) {
            this.isGroup = false;
        }
        super.setSearchSource(i);
    }
}
