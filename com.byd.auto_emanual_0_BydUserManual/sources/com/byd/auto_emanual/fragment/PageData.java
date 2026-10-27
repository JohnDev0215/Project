package com.byd.auto_emanual.fragment;

import com.byd.auto_emanual.search.LevelMenu;
import com.byd.auto_emanual.search.LevelOne;
import com.byd.auto_emanual.util.ListUtil;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PageData {
    private List<ClassifyData> classifyData;
    private List<LevelOne> menuData;
    private List<LevelMenu> multiMenuData;
    private String netAddress;
    private String page;
    private ZipInfo zipInfo;

    public PageData(String page, List<ClassifyData> classifyData) {
        this.page = page;
        this.classifyData = classifyData;
    }

    public String getPage() {
        return this.page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<ClassifyData> getClassifyData() {
        return this.classifyData;
    }

    public void setClassifyData(List<ClassifyData> classifyData) {
        this.classifyData = classifyData;
    }

    public boolean hasData() {
        boolean z;
        if (ListUtil.isNullOrEmpty(this.classifyData)) {
            z = false;
        } else {
            for (ClassifyData classifyData : this.classifyData) {
                if (classifyData != null && !ListUtil.isNullOrEmpty(classifyData.getContentItems())) {
                    z = true;
                    break;
                }
            }
            z = false;
        }
        if (Page.MANUAL.equals(this.page) && this.zipInfo != null) {
            z = true;
        }
        if (!Page.JSON_DATA.equals(this.page) || ListUtil.isNullOrEmpty(this.menuData)) {
            return z;
        }
        return true;
    }

    public ZipInfo getZipInfo() {
        return this.zipInfo;
    }

    public void setZipInfo(ZipInfo zipInfo) {
        this.zipInfo = zipInfo;
    }

    public List<LevelOne> getMenuData() {
        return this.menuData;
    }

    public void setMenuData(List<LevelOne> menuData) {
        this.menuData = menuData;
    }

    public List<LevelMenu> getMultiMenuData() {
        return this.multiMenuData;
    }

    public void setMultiMenuData(List<LevelMenu> multiMenuData) {
        this.multiMenuData = multiMenuData;
    }

    public String getNetAddress() {
        return this.netAddress;
    }

    public void setNetAddress(String netAddress) {
        this.netAddress = netAddress;
    }
}
