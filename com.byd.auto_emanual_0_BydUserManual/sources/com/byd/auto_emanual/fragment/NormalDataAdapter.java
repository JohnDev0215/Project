package com.byd.auto_emanual.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.byd.auto_emanual.BuildConfig;
import com.byd.auto_emanual.ContentItem;
import com.byd.auto_emanual.MApp;
import com.byd.auto_emanual.MediaActivity;
import com.byd.auto_emanual.Parameters;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.image.KImageView;
import com.byd.auto_emanual.search.LevelOne;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.Utils;
import com.byd.auto_emanual.util.ViewUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class NormalDataAdapter {
    private static final String TAG = "NormalDataAdapter";
    public static Map<String, ClassifyData> cacheData = new HashMap();
    public static List<LevelOne> menuData = new ArrayList();
    public static String netAddress = "";
    public static String localAddress = "";
    public static String zipUrl = "";
    public static int VIDEO_PLAYER_COUNT = 1;

    public static List<ContentItem> getVerticalData(ClassifyData classifyData) {
        List<ContentItem> contentItems = classifyData.getContentItems();
        int i = RecommendVerticalAdapter.FROM_INDEX_IN_ALL;
        if (i >= contentItems.size()) {
            return new ArrayList();
        }
        int size = (RecommendVerticalAdapter.DEFAULT_VERTICAL_COLUMN_COUNT * RecommendVerticalAdapter.DEFAULT_VERTICAL_ROW_COUNT) + i;
        if (size > contentItems.size()) {
            size = contentItems.size();
        }
        List<ContentItem> listSubList = contentItems.subList(i, size);
        cacheData.put(classifyData.getClassify(), classifyData);
        return listSubList;
    }

    public static List<ContentItem> getHorizontalData(ClassifyData classifyData) {
        List<ContentItem> contentItems = classifyData.getContentItems();
        int i = VIDEO_PLAYER_COUNT + (RecommendVerticalAdapter.DEFAULT_VERTICAL_COLUMN_COUNT * RecommendVerticalAdapter.DEFAULT_VERTICAL_ROW_COUNT);
        HorizontalAdapter.FROM_INDEX_IN_ALL = i;
        MLog.d(TAG, "getHorizontalData formIndex = " + i);
        if (i >= contentItems.size()) {
            return new ArrayList();
        }
        List<ContentItem> listSubList = contentItems.subList(i, contentItems.size());
        cacheData.put(classifyData.getClassify(), classifyData);
        return listSubList;
    }

    public static void startMediaActivity(String classify, String imageHeader, String imagePath, String videoPath, ContentItem.ContentItemType type, int position, String modelTypeName) {
        String managerId;
        Intent intent = new Intent();
        intent.setFlags(268435456);
        if (type == ContentItem.ContentItemType.VIDEO || type == ContentItem.ContentItemType.IMAGE || type == ContentItem.ContentItemType.HTML) {
            intent.setClass(MApp.context, MediaActivity.class);
        }
        intent.putExtra(Parameters.CONTENT_CLASSIFY, classify);
        intent.putExtra("1", imageHeader);
        intent.putExtra(Parameters.CONTENT_ITEM_COVER_PATH, imagePath);
        intent.putExtra(Parameters.CONTENT_ITEM_PATH, videoPath);
        intent.putExtra(Parameters.CONTENT_TYPE, type);
        intent.putExtra(Parameters.CONTENT_POSITION, position);
        intent.putExtra(Parameters.CONTENT_MODEL_TYPE_NAME, modelTypeName);
        intent.setComponent(new ComponentName(BuildConfig.APPLICATION_ID, "com.byd.auto_emanual.MediaActivity"));
        MApp.context.startActivity(intent);
        if (type != ContentItem.ContentItemType.VIDEO || Utils.notShowVideo(MApp.context)) {
            return;
        }
        Presenter presenter = new Presenter(null);
        Iterator<ContentItem> it = cacheData.get(classify).getContentItems().iterator();
        while (true) {
            if (!it.hasNext()) {
                managerId = "";
                break;
            }
            ContentItem next = it.next();
            if (videoPath.equals(next.getPath())) {
                managerId = next.getManagerId();
                break;
            }
        }
        if (TextUtils.isEmpty(managerId)) {
            return;
        }
        presenter.viewCount(Classify.VIEW_TIMES, managerId);
    }

    public static void updateItemByConfig(View item, View cover, int orientation) {
        int dimensionPixelOffset;
        int dimensionPixelOffset2;
        if (ViewUtil.notTreatOrientation(orientation)) {
            return;
        }
        if (ViewUtil.isPort(orientation)) {
            dimensionPixelOffset = MApp.context.getResources().getDimensionPixelOffset(R.dimen.recommend_item_width_port);
            dimensionPixelOffset2 = MApp.context.getResources().getDimensionPixelOffset(R.dimen.recommend_item_height_port);
        } else {
            dimensionPixelOffset = MApp.context.getResources().getDimensionPixelOffset(R.dimen.recommend_item_width);
            dimensionPixelOffset2 = MApp.context.getResources().getDimensionPixelOffset(R.dimen.recommend_item_height);
        }
        ViewGroup.LayoutParams layoutParams = item.getLayoutParams();
        layoutParams.width = dimensionPixelOffset;
        layoutParams.height = dimensionPixelOffset2;
        item.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = cover.getLayoutParams();
        layoutParams2.width = dimensionPixelOffset;
        layoutParams2.height = dimensionPixelOffset2;
        cover.setLayoutParams(layoutParams2);
    }

    public static void bindHolderItem(RelativeLayout item, TextView header, KImageView cover, TextView footer, TextView path, ImageView playLogo, List<ContentItem> data, int index, int orientation) {
        updateItemByConfig(item, cover, orientation);
        item.setVisibility(8);
        if (index >= data.size()) {
            return;
        }
        item.setVisibility(0);
        ContentItem.bindHolderItem(header, cover, footer, path, playLogo, data.get(index));
    }

    public static List<ContentItem> getStartData(ClassifyData classifyData) {
        return getContentItems(classifyData);
    }

    private static List<ContentItem> getContentItems(ClassifyData classifyData) {
        List<ContentItem> contentItems = classifyData.getContentItems();
        cacheData.put(classifyData.getClassify(), classifyData);
        return contentItems;
    }

    public static List<ContentItem> getSearchData(ClassifyData classifyData) {
        return getContentItems(classifyData);
    }

    public static void updateDefaultColumnAndRowByConfig(int orientation) {
        if (ViewUtil.isPort(orientation)) {
            RecommendVerticalAdapter.DEFAULT_VERTICAL_COLUMN_COUNT = 0;
            HorizontalAdapter.DEFAULT_HORIZONTAL_COLUMN_COUNT = 2;
        } else {
            RecommendVerticalAdapter.DEFAULT_VERTICAL_COLUMN_COUNT = 2;
            HorizontalAdapter.DEFAULT_HORIZONTAL_COLUMN_COUNT = 4;
        }
    }
}
