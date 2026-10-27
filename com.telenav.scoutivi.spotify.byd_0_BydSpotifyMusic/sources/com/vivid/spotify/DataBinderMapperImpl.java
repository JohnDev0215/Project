package com.vivid.spotify;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import d4.a2;
import d4.a3;
import d4.a4;
import d4.a5;
import d4.b1;
import d4.b2;
import d4.b3;
import d4.b4;
import d4.b5;
import d4.c1;
import d4.c2;
import d4.c3;
import d4.c4;
import d4.c5;
import d4.d0;
import d4.d1;
import d4.d2;
import d4.d4;
import d4.e0;
import d4.e1;
import d4.e3;
import d4.e5;
import d4.f0;
import d4.f2;
import d4.f3;
import d4.f4;
import d4.f5;
import d4.g1;
import d4.g2;
import d4.g3;
import d4.g4;
import d4.g5;
import d4.h0;
import d4.h1;
import d4.h2;
import d4.h3;
import d4.h4;
import d4.h5;
import d4.i0;
import d4.i1;
import d4.i2;
import d4.i4;
import d4.j0;
import d4.j1;
import d4.j3;
import d4.k0;
import d4.k2;
import d4.k3;
import d4.k4;
import d4.l1;
import d4.l2;
import d4.l3;
import d4.l4;
import d4.m0;
import d4.m1;
import d4.m2;
import d4.m3;
import d4.m4;
import d4.n0;
import d4.n1;
import d4.n2;
import d4.n4;
import d4.o0;
import d4.o1;
import d4.p0;
import d4.p2;
import d4.p4;
import d4.q1;
import d4.q2;
import d4.q3;
import d4.q4;
import d4.r0;
import d4.r1;
import d4.r2;
import d4.r3;
import d4.r4;
import d4.s0;
import d4.s1;
import d4.s2;
import d4.s3;
import d4.s4;
import d4.t0;
import d4.t1;
import d4.t3;
import d4.u0;
import d4.u2;
import d4.u4;
import d4.v1;
import d4.v2;
import d4.v3;
import d4.v4;
import d4.w0;
import d4.w1;
import d4.w2;
import d4.w3;
import d4.w4;
import d4.x0;
import d4.x1;
import d4.x2;
import d4.x3;
import d4.x4;
import d4.y0;
import d4.y1;
import d4.y3;
import d4.z0;
import d4.z2;
import d4.z4;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DataBinderMapperImpl extends androidx.databinding.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final SparseIntArray f5182a;

    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final HashMap<String, Integer> f5183a;

        static {
            HashMap<String, Integer> map = new HashMap<>(128);
            f5183a = map;
            int i7 = v.activity_main;
            map.put("layout-w600dp-h480dp/activity_main_0", Integer.valueOf(i7));
            map.put("layout-w840dp-h480dp/activity_main_0", Integer.valueOf(i7));
            map.put("layout/activity_main_0", Integer.valueOf(i7));
            map.put("layout-w600dp-h900dp/activity_main_0", Integer.valueOf(i7));
            int i8 = v.base_list_item;
            map.put("layout-w600dp-h480dp/base_list_item_0", Integer.valueOf(i8));
            map.put("layout-w600dp-h900dp/base_list_item_0", Integer.valueOf(i8));
            map.put("layout-w840dp-h480dp/base_list_item_0", Integer.valueOf(i8));
            map.put("layout/base_list_item_0", Integer.valueOf(i8));
            int i9 = v.base_list_item_viewholder;
            map.put("layout/base_list_item_viewholder_0", Integer.valueOf(i9));
            map.put("layout-w840dp-h480dp/base_list_item_viewholder_0", Integer.valueOf(i9));
            map.put("layout-w600dp-h480dp/base_list_item_viewholder_0", Integer.valueOf(i9));
            map.put("layout-w600dp-h900dp/base_list_item_viewholder_0", Integer.valueOf(i9));
            int i10 = v.common_error_layout;
            map.put("layout-w600dp-h480dp/common_error_layout_0", Integer.valueOf(i10));
            map.put("layout/common_error_layout_0", Integer.valueOf(i10));
            map.put("layout-w840dp-h480dp/common_error_layout_0", Integer.valueOf(i10));
            map.put("layout-w600dp-h900dp/common_error_layout_0", Integer.valueOf(i10));
            int i11 = v.custom_toast;
            map.put("layout-w600dp-h900dp/custom_toast_0", Integer.valueOf(i11));
            map.put("layout-w840dp-h480dp/custom_toast_0", Integer.valueOf(i11));
            map.put("layout-w600dp-h480dp/custom_toast_0", Integer.valueOf(i11));
            map.put("layout/custom_toast_0", Integer.valueOf(i11));
            int i12 = v.detail_error_layout;
            map.put("layout-w600dp-h900dp/detail_error_layout_0", Integer.valueOf(i12));
            map.put("layout-w840dp-h480dp/detail_error_layout_0", Integer.valueOf(i12));
            map.put("layout/detail_error_layout_0", Integer.valueOf(i12));
            map.put("layout-w600dp-h480dp/detail_error_layout_0", Integer.valueOf(i12));
            int i13 = v.fragment_category;
            map.put("layout-w600dp-h480dp/fragment_category_0", Integer.valueOf(i13));
            map.put("layout-w840dp-h480dp/fragment_category_0", Integer.valueOf(i13));
            map.put("layout-w600dp-h900dp/fragment_category_0", Integer.valueOf(i13));
            map.put("layout/fragment_category_0", Integer.valueOf(i13));
            int i14 = v.fragment_category_grid;
            map.put("layout-w600dp-h480dp/fragment_category_grid_0", Integer.valueOf(i14));
            map.put("layout/fragment_category_grid_0", Integer.valueOf(i14));
            map.put("layout-w600dp-h900dp/fragment_category_grid_0", Integer.valueOf(i14));
            map.put("layout-w840dp-h480dp/fragment_category_grid_0", Integer.valueOf(i14));
            int i15 = v.fragment_category_list;
            map.put("layout-w840dp-h480dp/fragment_category_list_0", Integer.valueOf(i15));
            map.put("layout-w600dp-h900dp/fragment_category_list_0", Integer.valueOf(i15));
            map.put("layout-w600dp-h480dp/fragment_category_list_0", Integer.valueOf(i15));
            map.put("layout/fragment_category_list_0", Integer.valueOf(i15));
            int i16 = v.fragment_details_list;
            map.put("layout/fragment_details_list_0", Integer.valueOf(i16));
            map.put("layout-w600dp-h480dp/fragment_details_list_0", Integer.valueOf(i16));
            map.put("layout-w600dp-h900dp/fragment_details_list_0", Integer.valueOf(i16));
            map.put("layout-w840dp-h480dp/fragment_details_list_0", Integer.valueOf(i16));
            int i17 = v.fragment_login;
            map.put("layout-w840dp-h480dp/fragment_login_0", Integer.valueOf(i17));
            map.put("layout/fragment_login_0", Integer.valueOf(i17));
            map.put("layout-w600dp-h480dp/fragment_login_0", Integer.valueOf(i17));
            map.put("layout-w600dp-h900dp/fragment_login_0", Integer.valueOf(i17));
            int i18 = v.fragment_main;
            map.put("layout-w840dp-h480dp/fragment_main_0", Integer.valueOf(i18));
            map.put("layout-w600dp-h480dp/fragment_main_0", Integer.valueOf(i18));
            map.put("layout/fragment_main_0", Integer.valueOf(i18));
            map.put("layout-w600dp-h900dp/fragment_main_0", Integer.valueOf(i18));
            int i19 = v.fragment_main_grid;
            map.put("layout-w600dp-h900dp/fragment_main_grid_0", Integer.valueOf(i19));
            map.put("layout-w840dp-h480dp/fragment_main_grid_0", Integer.valueOf(i19));
            map.put("layout-w600dp-h480dp/fragment_main_grid_0", Integer.valueOf(i19));
            map.put("layout/fragment_main_grid_0", Integer.valueOf(i19));
            int i20 = v.fragment_main_route;
            map.put("layout-w600dp-h900dp/fragment_main_route_0", Integer.valueOf(i20));
            map.put("layout-w840dp-h480dp/fragment_main_route_0", Integer.valueOf(i20));
            map.put("layout/fragment_main_route_0", Integer.valueOf(i20));
            map.put("layout-w600dp-h480dp/fragment_main_route_0", Integer.valueOf(i20));
            int i21 = v.fragment_now_playing;
            map.put("layout-w600dp-h900dp/fragment_now_playing_0", Integer.valueOf(i21));
            map.put("layout/fragment_now_playing_0", Integer.valueOf(i21));
            map.put("layout-w600dp-h480dp/fragment_now_playing_0", Integer.valueOf(i21));
            map.put("layout-w840dp-h480dp/fragment_now_playing_0", Integer.valueOf(i21));
            int i22 = v.fragment_now_playing_rhd;
            map.put("layout/fragment_now_playing_rhd_0", Integer.valueOf(i22));
            map.put("layout-w840dp-h480dp/fragment_now_playing_rhd_0", Integer.valueOf(i22));
            map.put("layout-w600dp-h480dp/fragment_now_playing_rhd_0", Integer.valueOf(i22));
            map.put("layout-w600dp-h900dp/fragment_now_playing_rhd_0", Integer.valueOf(i22));
            int i23 = v.fragment_play_list;
            map.put("layout-w600dp-h900dp/fragment_play_list_0", Integer.valueOf(i23));
            map.put("layout-w840dp-h480dp/fragment_play_list_0", Integer.valueOf(i23));
            map.put("layout/fragment_play_list_0", Integer.valueOf(i23));
            map.put("layout-w600dp-h480dp/fragment_play_list_0", Integer.valueOf(i23));
            int i24 = v.fragment_queue;
            map.put("layout/fragment_queue_0", Integer.valueOf(i24));
            map.put("layout-w840dp-h480dp/fragment_queue_0", Integer.valueOf(i24));
            map.put("layout-w600dp-h900dp/fragment_queue_0", Integer.valueOf(i24));
            map.put("layout-w600dp-h480dp/fragment_queue_0", Integer.valueOf(i24));
            int i25 = v.fragment_queue_rhd;
            map.put("layout/fragment_queue_rhd_0", Integer.valueOf(i25));
            map.put("layout-w600dp-h480dp/fragment_queue_rhd_0", Integer.valueOf(i25));
            map.put("layout-w840dp-h480dp/fragment_queue_rhd_0", Integer.valueOf(i25));
            map.put("layout-w600dp-h900dp/fragment_queue_rhd_0", Integer.valueOf(i25));
            int i26 = v.fragment_search;
            map.put("layout-w600dp-h480dp/fragment_search_0", Integer.valueOf(i26));
            map.put("layout-w600dp-h900dp/fragment_search_0", Integer.valueOf(i26));
            map.put("layout-w840dp-h480dp/fragment_search_0", Integer.valueOf(i26));
            map.put("layout/fragment_search_0", Integer.valueOf(i26));
            int i27 = v.fragment_search_play_list;
            map.put("layout-w600dp-h480dp/fragment_search_play_list_0", Integer.valueOf(i27));
            map.put("layout-w600dp-h900dp/fragment_search_play_list_0", Integer.valueOf(i27));
            map.put("layout-w840dp-h480dp/fragment_search_play_list_0", Integer.valueOf(i27));
            map.put("layout/fragment_search_play_list_0", Integer.valueOf(i27));
            int i28 = v.fragment_search_shimmer;
            map.put("layout-w600dp-h480dp/fragment_search_shimmer_0", Integer.valueOf(i28));
            map.put("layout/fragment_search_shimmer_0", Integer.valueOf(i28));
            map.put("layout-w600dp-h900dp/fragment_search_shimmer_0", Integer.valueOf(i28));
            map.put("layout-w840dp-h480dp/fragment_search_shimmer_0", Integer.valueOf(i28));
            int i29 = v.fragment_welcome;
            map.put("layout-w600dp-h480dp/fragment_welcome_0", Integer.valueOf(i29));
            map.put("layout-w600dp-h900dp/fragment_welcome_0", Integer.valueOf(i29));
            map.put("layout-w840dp-h480dp/fragment_welcome_0", Integer.valueOf(i29));
            map.put("layout/fragment_welcome_0", Integer.valueOf(i29));
            int i30 = v.list_speed;
            map.put("layout-w840dp-h480dp/list_speed_0", Integer.valueOf(i30));
            map.put("layout/list_speed_0", Integer.valueOf(i30));
            map.put("layout-w600dp-h900dp/list_speed_0", Integer.valueOf(i30));
            map.put("layout-w600dp-h480dp/list_speed_0", Integer.valueOf(i30));
            int i31 = v.list_speed_item;
            map.put("layout-w600dp-h480dp/list_speed_item_0", Integer.valueOf(i31));
            map.put("layout-w840dp-h480dp/list_speed_item_0", Integer.valueOf(i31));
            map.put("layout-w600dp-h900dp/list_speed_item_0", Integer.valueOf(i31));
            map.put("layout/list_speed_item_0", Integer.valueOf(i31));
            int i32 = v.media_cover_item;
            map.put("layout/media_cover_item_0", Integer.valueOf(i32));
            map.put("layout-w840dp-h480dp/media_cover_item_0", Integer.valueOf(i32));
            map.put("layout-w600dp-h900dp/media_cover_item_0", Integer.valueOf(i32));
            map.put("layout-w600dp-h480dp/media_cover_item_0", Integer.valueOf(i32));
            int i33 = v.media_list_parent_item;
            map.put("layout/media_list_parent_item_0", Integer.valueOf(i33));
            map.put("layout-w600dp-h900dp/media_list_parent_item_0", Integer.valueOf(i33));
            map.put("layout-w600dp-h480dp/media_list_parent_item_0", Integer.valueOf(i33));
            map.put("layout-w840dp-h480dp/media_list_parent_item_0", Integer.valueOf(i33));
            int i34 = v.media_title_item;
            map.put("layout/media_title_item_0", Integer.valueOf(i34));
            map.put("layout-w600dp-h480dp/media_title_item_0", Integer.valueOf(i34));
            map.put("layout-w600dp-h900dp/media_title_item_0", Integer.valueOf(i34));
            map.put("layout-w840dp-h480dp/media_title_item_0", Integer.valueOf(i34));
            int i35 = v.now_playing_bar;
            map.put("layout-w600dp-h480dp/now_playing_bar_0", Integer.valueOf(i35));
            map.put("layout-w840dp-h480dp/now_playing_bar_0", Integer.valueOf(i35));
            map.put("layout/now_playing_bar_0", Integer.valueOf(i35));
            map.put("layout-w600dp-h900dp/now_playing_bar_0", Integer.valueOf(i35));
            int i36 = v.root_menu_tab_item;
            map.put("layout/root_menu_tab_item_0", Integer.valueOf(i36));
            map.put("layout-w600dp-h480dp/root_menu_tab_item_0", Integer.valueOf(i36));
            map.put("layout-w600dp-h900dp/root_menu_tab_item_0", Integer.valueOf(i36));
            map.put("layout-w840dp-h480dp/root_menu_tab_item_0", Integer.valueOf(i36));
            int i37 = v.rv_double_grid_item;
            map.put("layout-w840dp-h480dp/rv_double_grid_item_0", Integer.valueOf(i37));
            map.put("layout/rv_double_grid_item_0", Integer.valueOf(i37));
            map.put("layout-w600dp-h900dp/rv_double_grid_item_0", Integer.valueOf(i37));
            map.put("layout-w600dp-h480dp/rv_double_grid_item_0", Integer.valueOf(i37));
            int i38 = v.title_item;
            map.put("layout-w600dp-h900dp/title_item_0", Integer.valueOf(i38));
            map.put("layout-w840dp-h480dp/title_item_0", Integer.valueOf(i38));
            map.put("layout-w600dp-h480dp/title_item_0", Integer.valueOf(i38));
            map.put("layout/title_item_0", Integer.valueOf(i38));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(32);
        f5182a = sparseIntArray;
        sparseIntArray.put(v.activity_main, 1);
        sparseIntArray.put(v.base_list_item, 2);
        sparseIntArray.put(v.base_list_item_viewholder, 3);
        sparseIntArray.put(v.common_error_layout, 4);
        sparseIntArray.put(v.custom_toast, 5);
        sparseIntArray.put(v.detail_error_layout, 6);
        sparseIntArray.put(v.fragment_category, 7);
        sparseIntArray.put(v.fragment_category_grid, 8);
        sparseIntArray.put(v.fragment_category_list, 9);
        sparseIntArray.put(v.fragment_details_list, 10);
        sparseIntArray.put(v.fragment_login, 11);
        sparseIntArray.put(v.fragment_main, 12);
        sparseIntArray.put(v.fragment_main_grid, 13);
        sparseIntArray.put(v.fragment_main_route, 14);
        sparseIntArray.put(v.fragment_now_playing, 15);
        sparseIntArray.put(v.fragment_now_playing_rhd, 16);
        sparseIntArray.put(v.fragment_play_list, 17);
        sparseIntArray.put(v.fragment_queue, 18);
        sparseIntArray.put(v.fragment_queue_rhd, 19);
        sparseIntArray.put(v.fragment_search, 20);
        sparseIntArray.put(v.fragment_search_play_list, 21);
        sparseIntArray.put(v.fragment_search_shimmer, 22);
        sparseIntArray.put(v.fragment_welcome, 23);
        sparseIntArray.put(v.list_speed, 24);
        sparseIntArray.put(v.list_speed_item, 25);
        sparseIntArray.put(v.media_cover_item, 26);
        sparseIntArray.put(v.media_list_parent_item, 27);
        sparseIntArray.put(v.media_title_item, 28);
        sparseIntArray.put(v.now_playing_bar, 29);
        sparseIntArray.put(v.root_menu_tab_item, 30);
        sparseIntArray.put(v.rv_double_grid_item, 31);
        sparseIntArray.put(v.title_item, 32);
    }

    @Override // androidx.databinding.e
    public List<androidx.databinding.e> a() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.vivid.labels.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.e
    public ViewDataBinding b(androidx.databinding.f fVar, View view, int i7) {
        int i8 = f5182a.get(i7);
        if (i8 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag == null) {
            throw new RuntimeException("view must have a tag");
        }
        switch (i8) {
            case 1:
                if ("layout-w600dp-h480dp/activity_main_0".equals(tag)) {
                    return new d4.c(fVar, view);
                }
                if ("layout-w840dp-h480dp/activity_main_0".equals(tag)) {
                    return new d4.e(fVar, view);
                }
                if ("layout/activity_main_0".equals(tag)) {
                    return new d4.b(fVar, view);
                }
                if ("layout-w600dp-h900dp/activity_main_0".equals(tag)) {
                    return new d4.d(fVar, view);
                }
                throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
            case 2:
                if ("layout-w600dp-h480dp/base_list_item_0".equals(tag)) {
                    return new d4.h(fVar, view);
                }
                if ("layout-w600dp-h900dp/base_list_item_0".equals(tag)) {
                    return new d4.i(fVar, view);
                }
                if ("layout-w840dp-h480dp/base_list_item_0".equals(tag)) {
                    return new d4.j(fVar, view);
                }
                if ("layout/base_list_item_0".equals(tag)) {
                    return new d4.g(fVar, view);
                }
                throw new IllegalArgumentException("The tag for base_list_item is invalid. Received: " + tag);
            case 3:
                if ("layout/base_list_item_viewholder_0".equals(tag)) {
                    return new d4.l(fVar, view);
                }
                if ("layout-w840dp-h480dp/base_list_item_viewholder_0".equals(tag)) {
                    return new d4.o(fVar, view);
                }
                if ("layout-w600dp-h480dp/base_list_item_viewholder_0".equals(tag)) {
                    return new d4.m(fVar, view);
                }
                if ("layout-w600dp-h900dp/base_list_item_viewholder_0".equals(tag)) {
                    return new d4.n(fVar, view);
                }
                throw new IllegalArgumentException("The tag for base_list_item_viewholder is invalid. Received: " + tag);
            case 4:
                if ("layout-w600dp-h480dp/common_error_layout_0".equals(tag)) {
                    return new d4.r(fVar, view);
                }
                if ("layout/common_error_layout_0".equals(tag)) {
                    return new d4.q(fVar, view);
                }
                if ("layout-w840dp-h480dp/common_error_layout_0".equals(tag)) {
                    return new d4.t(fVar, view);
                }
                if ("layout-w600dp-h900dp/common_error_layout_0".equals(tag)) {
                    return new d4.s(fVar, view);
                }
                throw new IllegalArgumentException("The tag for common_error_layout is invalid. Received: " + tag);
            case 5:
                if ("layout-w600dp-h900dp/custom_toast_0".equals(tag)) {
                    return new d4.x(fVar, view);
                }
                if ("layout-w840dp-h480dp/custom_toast_0".equals(tag)) {
                    return new d4.y(fVar, view);
                }
                if ("layout-w600dp-h480dp/custom_toast_0".equals(tag)) {
                    return new d4.w(fVar, view);
                }
                if ("layout/custom_toast_0".equals(tag)) {
                    return new d4.v(fVar, view);
                }
                throw new IllegalArgumentException("The tag for custom_toast is invalid. Received: " + tag);
            case 6:
                if ("layout-w600dp-h900dp/detail_error_layout_0".equals(tag)) {
                    return new e0(fVar, view);
                }
                if ("layout-w840dp-h480dp/detail_error_layout_0".equals(tag)) {
                    return new f0(fVar, view);
                }
                if ("layout/detail_error_layout_0".equals(tag)) {
                    return new d4.c0(fVar, view);
                }
                if ("layout-w600dp-h480dp/detail_error_layout_0".equals(tag)) {
                    return new d0(fVar, view);
                }
                throw new IllegalArgumentException("The tag for detail_error_layout is invalid. Received: " + tag);
            case 7:
                if ("layout-w600dp-h480dp/fragment_category_0".equals(tag)) {
                    return new i0(fVar, view);
                }
                if ("layout-w840dp-h480dp/fragment_category_0".equals(tag)) {
                    return new k0(fVar, view);
                }
                if ("layout-w600dp-h900dp/fragment_category_0".equals(tag)) {
                    return new j0(fVar, view);
                }
                if ("layout/fragment_category_0".equals(tag)) {
                    return new h0(fVar, view);
                }
                throw new IllegalArgumentException("The tag for fragment_category is invalid. Received: " + tag);
            case 8:
                if ("layout-w600dp-h480dp/fragment_category_grid_0".equals(tag)) {
                    return new n0(fVar, view);
                }
                if ("layout/fragment_category_grid_0".equals(tag)) {
                    return new m0(fVar, view);
                }
                if ("layout-w600dp-h900dp/fragment_category_grid_0".equals(tag)) {
                    return new o0(fVar, view);
                }
                if ("layout-w840dp-h480dp/fragment_category_grid_0".equals(tag)) {
                    return new p0(fVar, view);
                }
                throw new IllegalArgumentException("The tag for fragment_category_grid is invalid. Received: " + tag);
            case 9:
                if ("layout-w840dp-h480dp/fragment_category_list_0".equals(tag)) {
                    return new u0(fVar, view);
                }
                if ("layout-w600dp-h900dp/fragment_category_list_0".equals(tag)) {
                    return new t0(fVar, view);
                }
                if ("layout-w600dp-h480dp/fragment_category_list_0".equals(tag)) {
                    return new s0(fVar, view);
                }
                if ("layout/fragment_category_list_0".equals(tag)) {
                    return new r0(fVar, view);
                }
                throw new IllegalArgumentException("The tag for fragment_category_list is invalid. Received: " + tag);
            case 10:
                if ("layout/fragment_details_list_0".equals(tag)) {
                    return new w0(fVar, view);
                }
                if ("layout-w600dp-h480dp/fragment_details_list_0".equals(tag)) {
                    return new x0(fVar, view);
                }
                if ("layout-w600dp-h900dp/fragment_details_list_0".equals(tag)) {
                    return new y0(fVar, view);
                }
                if ("layout-w840dp-h480dp/fragment_details_list_0".equals(tag)) {
                    return new z0(fVar, view);
                }
                throw new IllegalArgumentException("The tag for fragment_details_list is invalid. Received: " + tag);
            case 11:
                if ("layout-w840dp-h480dp/fragment_login_0".equals(tag)) {
                    return new e1(fVar, view);
                }
                if ("layout/fragment_login_0".equals(tag)) {
                    return new b1(fVar, view);
                }
                if ("layout-w600dp-h480dp/fragment_login_0".equals(tag)) {
                    return new c1(fVar, view);
                }
                if ("layout-w600dp-h900dp/fragment_login_0".equals(tag)) {
                    return new d1(fVar, view);
                }
                throw new IllegalArgumentException("The tag for fragment_login is invalid. Received: " + tag);
            case 12:
                if ("layout-w840dp-h480dp/fragment_main_0".equals(tag)) {
                    return new j1(fVar, view);
                }
                if ("layout-w600dp-h480dp/fragment_main_0".equals(tag)) {
                    return new h1(fVar, view);
                }
                if ("layout/fragment_main_0".equals(tag)) {
                    return new g1(fVar, view);
                }
                if ("layout-w600dp-h900dp/fragment_main_0".equals(tag)) {
                    return new i1(fVar, view);
                }
                throw new IllegalArgumentException("The tag for fragment_main is invalid. Received: " + tag);
            case 13:
                if ("layout-w600dp-h900dp/fragment_main_grid_0".equals(tag)) {
                    return new n1(fVar, view);
                }
                if ("layout-w840dp-h480dp/fragment_main_grid_0".equals(tag)) {
                    return new o1(fVar, view);
                }
                if ("layout-w600dp-h480dp/fragment_main_grid_0".equals(tag)) {
                    return new m1(fVar, view);
                }
                if ("layout/fragment_main_grid_0".equals(tag)) {
                    return new l1(fVar, view);
                }
                throw new IllegalArgumentException("The tag for fragment_main_grid is invalid. Received: " + tag);
            case 14:
                if ("layout-w600dp-h900dp/fragment_main_route_0".equals(tag)) {
                    return new s1(fVar, view);
                }
                if ("layout-w840dp-h480dp/fragment_main_route_0".equals(tag)) {
                    return new t1(fVar, view);
                }
                if ("layout/fragment_main_route_0".equals(tag)) {
                    return new q1(fVar, view);
                }
                if ("layout-w600dp-h480dp/fragment_main_route_0".equals(tag)) {
                    return new r1(fVar, view);
                }
                throw new IllegalArgumentException("The tag for fragment_main_route is invalid. Received: " + tag);
            case 15:
                if ("layout-w600dp-h900dp/fragment_now_playing_0".equals(tag)) {
                    return new x1(fVar, view);
                }
                if ("layout/fragment_now_playing_0".equals(tag)) {
                    return new v1(fVar, view);
                }
                if ("layout-w600dp-h480dp/fragment_now_playing_0".equals(tag)) {
                    return new w1(fVar, view);
                }
                if ("layout-w840dp-h480dp/fragment_now_playing_0".equals(tag)) {
                    return new y1(fVar, view);
                }
                throw new IllegalArgumentException("The tag for fragment_now_playing is invalid. Received: " + tag);
            case 16:
                if ("layout/fragment_now_playing_rhd_0".equals(tag)) {
                    return new a2(fVar, view);
                }
                if ("layout-w840dp-h480dp/fragment_now_playing_rhd_0".equals(tag)) {
                    return new d2(fVar, view);
                }
                if ("layout-w600dp-h480dp/fragment_now_playing_rhd_0".equals(tag)) {
                    return new b2(fVar, view);
                }
                if ("layout-w600dp-h900dp/fragment_now_playing_rhd_0".equals(tag)) {
                    return new c2(fVar, view);
                }
                throw new IllegalArgumentException("The tag for fragment_now_playing_rhd is invalid. Received: " + tag);
            case 17:
                if ("layout-w600dp-h900dp/fragment_play_list_0".equals(tag)) {
                    return new h2(fVar, view);
                }
                if ("layout-w840dp-h480dp/fragment_play_list_0".equals(tag)) {
                    return new i2(fVar, view);
                }
                if ("layout/fragment_play_list_0".equals(tag)) {
                    return new f2(fVar, view);
                }
                if ("layout-w600dp-h480dp/fragment_play_list_0".equals(tag)) {
                    return new g2(fVar, view);
                }
                throw new IllegalArgumentException("The tag for fragment_play_list is invalid. Received: " + tag);
            case 18:
                if ("layout/fragment_queue_0".equals(tag)) {
                    return new k2(fVar, view);
                }
                if ("layout-w840dp-h480dp/fragment_queue_0".equals(tag)) {
                    return new n2(fVar, view);
                }
                if ("layout-w600dp-h900dp/fragment_queue_0".equals(tag)) {
                    return new m2(fVar, view);
                }
                if ("layout-w600dp-h480dp/fragment_queue_0".equals(tag)) {
                    return new l2(fVar, view);
                }
                throw new IllegalArgumentException("The tag for fragment_queue is invalid. Received: " + tag);
            case 19:
                if ("layout/fragment_queue_rhd_0".equals(tag)) {
                    return new p2(fVar, view);
                }
                if ("layout-w600dp-h480dp/fragment_queue_rhd_0".equals(tag)) {
                    return new q2(fVar, view);
                }
                if ("layout-w840dp-h480dp/fragment_queue_rhd_0".equals(tag)) {
                    return new s2(fVar, view);
                }
                if ("layout-w600dp-h900dp/fragment_queue_rhd_0".equals(tag)) {
                    return new r2(fVar, view);
                }
                throw new IllegalArgumentException("The tag for fragment_queue_rhd is invalid. Received: " + tag);
            case 20:
                if ("layout-w600dp-h480dp/fragment_search_0".equals(tag)) {
                    return new v2(fVar, view);
                }
                if ("layout-w600dp-h900dp/fragment_search_0".equals(tag)) {
                    return new w2(fVar, view);
                }
                if ("layout-w840dp-h480dp/fragment_search_0".equals(tag)) {
                    return new x2(fVar, view);
                }
                if ("layout/fragment_search_0".equals(tag)) {
                    return new u2(fVar, view);
                }
                throw new IllegalArgumentException("The tag for fragment_search is invalid. Received: " + tag);
            case 21:
                if ("layout-w600dp-h480dp/fragment_search_play_list_0".equals(tag)) {
                    return new a3(fVar, view);
                }
                if ("layout-w600dp-h900dp/fragment_search_play_list_0".equals(tag)) {
                    return new b3(fVar, view);
                }
                if ("layout-w840dp-h480dp/fragment_search_play_list_0".equals(tag)) {
                    return new c3(fVar, view);
                }
                if ("layout/fragment_search_play_list_0".equals(tag)) {
                    return new z2(fVar, view);
                }
                throw new IllegalArgumentException("The tag for fragment_search_play_list is invalid. Received: " + tag);
            case 22:
                if ("layout-w600dp-h480dp/fragment_search_shimmer_0".equals(tag)) {
                    return new f3(fVar, view);
                }
                if ("layout/fragment_search_shimmer_0".equals(tag)) {
                    return new e3(fVar, view);
                }
                if ("layout-w600dp-h900dp/fragment_search_shimmer_0".equals(tag)) {
                    return new g3(fVar, view);
                }
                if ("layout-w840dp-h480dp/fragment_search_shimmer_0".equals(tag)) {
                    return new h3(fVar, view);
                }
                throw new IllegalArgumentException("The tag for fragment_search_shimmer is invalid. Received: " + tag);
            case 23:
                if ("layout-w600dp-h480dp/fragment_welcome_0".equals(tag)) {
                    return new k3(fVar, view);
                }
                if ("layout-w600dp-h900dp/fragment_welcome_0".equals(tag)) {
                    return new l3(fVar, view);
                }
                if ("layout-w840dp-h480dp/fragment_welcome_0".equals(tag)) {
                    return new m3(fVar, view);
                }
                if ("layout/fragment_welcome_0".equals(tag)) {
                    return new j3(fVar, view);
                }
                throw new IllegalArgumentException("The tag for fragment_welcome is invalid. Received: " + tag);
            case 24:
                if ("layout-w840dp-h480dp/list_speed_0".equals(tag)) {
                    return new t3(fVar, view);
                }
                if ("layout/list_speed_0".equals(tag)) {
                    return new q3(fVar, view);
                }
                if ("layout-w600dp-h900dp/list_speed_0".equals(tag)) {
                    return new s3(fVar, view);
                }
                if ("layout-w600dp-h480dp/list_speed_0".equals(tag)) {
                    return new r3(fVar, view);
                }
                throw new IllegalArgumentException("The tag for list_speed is invalid. Received: " + tag);
            case 25:
                if ("layout-w600dp-h480dp/list_speed_item_0".equals(tag)) {
                    return new w3(fVar, view);
                }
                if ("layout-w840dp-h480dp/list_speed_item_0".equals(tag)) {
                    return new y3(fVar, view);
                }
                if ("layout-w600dp-h900dp/list_speed_item_0".equals(tag)) {
                    return new x3(fVar, view);
                }
                if ("layout/list_speed_item_0".equals(tag)) {
                    return new v3(fVar, view);
                }
                throw new IllegalArgumentException("The tag for list_speed_item is invalid. Received: " + tag);
            case 26:
                if ("layout/media_cover_item_0".equals(tag)) {
                    return new a4(fVar, view);
                }
                if ("layout-w840dp-h480dp/media_cover_item_0".equals(tag)) {
                    return new d4(fVar, view);
                }
                if ("layout-w600dp-h900dp/media_cover_item_0".equals(tag)) {
                    return new c4(fVar, view);
                }
                if ("layout-w600dp-h480dp/media_cover_item_0".equals(tag)) {
                    return new b4(fVar, view);
                }
                throw new IllegalArgumentException("The tag for media_cover_item is invalid. Received: " + tag);
            case 27:
                if ("layout/media_list_parent_item_0".equals(tag)) {
                    return new f4(fVar, view);
                }
                if ("layout-w600dp-h900dp/media_list_parent_item_0".equals(tag)) {
                    return new h4(fVar, view);
                }
                if ("layout-w600dp-h480dp/media_list_parent_item_0".equals(tag)) {
                    return new g4(fVar, view);
                }
                if ("layout-w840dp-h480dp/media_list_parent_item_0".equals(tag)) {
                    return new i4(fVar, view);
                }
                throw new IllegalArgumentException("The tag for media_list_parent_item is invalid. Received: " + tag);
            case 28:
                if ("layout/media_title_item_0".equals(tag)) {
                    return new k4(fVar, view);
                }
                if ("layout-w600dp-h480dp/media_title_item_0".equals(tag)) {
                    return new l4(fVar, view);
                }
                if ("layout-w600dp-h900dp/media_title_item_0".equals(tag)) {
                    return new m4(fVar, view);
                }
                if ("layout-w840dp-h480dp/media_title_item_0".equals(tag)) {
                    return new n4(fVar, view);
                }
                throw new IllegalArgumentException("The tag for media_title_item is invalid. Received: " + tag);
            case 29:
                if ("layout-w600dp-h480dp/now_playing_bar_0".equals(tag)) {
                    return new q4(fVar, view);
                }
                if ("layout-w840dp-h480dp/now_playing_bar_0".equals(tag)) {
                    return new s4(fVar, view);
                }
                if ("layout/now_playing_bar_0".equals(tag)) {
                    return new p4(fVar, view);
                }
                if ("layout-w600dp-h900dp/now_playing_bar_0".equals(tag)) {
                    return new r4(fVar, view);
                }
                throw new IllegalArgumentException("The tag for now_playing_bar is invalid. Received: " + tag);
            case 30:
                if ("layout/root_menu_tab_item_0".equals(tag)) {
                    return new u4(fVar, view);
                }
                if ("layout-w600dp-h480dp/root_menu_tab_item_0".equals(tag)) {
                    return new v4(fVar, view);
                }
                if ("layout-w600dp-h900dp/root_menu_tab_item_0".equals(tag)) {
                    return new w4(fVar, view);
                }
                if ("layout-w840dp-h480dp/root_menu_tab_item_0".equals(tag)) {
                    return new x4(fVar, view);
                }
                throw new IllegalArgumentException("The tag for root_menu_tab_item is invalid. Received: " + tag);
            case 31:
                if ("layout-w840dp-h480dp/rv_double_grid_item_0".equals(tag)) {
                    return new c5(fVar, view);
                }
                if ("layout/rv_double_grid_item_0".equals(tag)) {
                    return new z4(fVar, view);
                }
                if ("layout-w600dp-h900dp/rv_double_grid_item_0".equals(tag)) {
                    return new b5(fVar, view);
                }
                if ("layout-w600dp-h480dp/rv_double_grid_item_0".equals(tag)) {
                    return new a5(fVar, view);
                }
                throw new IllegalArgumentException("The tag for rv_double_grid_item is invalid. Received: " + tag);
            case 32:
                if ("layout-w600dp-h900dp/title_item_0".equals(tag)) {
                    return new g5(fVar, view);
                }
                if ("layout-w840dp-h480dp/title_item_0".equals(tag)) {
                    return new h5(fVar, view);
                }
                if ("layout-w600dp-h480dp/title_item_0".equals(tag)) {
                    return new f5(fVar, view);
                }
                if ("layout/title_item_0".equals(tag)) {
                    return new e5(fVar, view);
                }
                throw new IllegalArgumentException("The tag for title_item is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    @Override // androidx.databinding.e
    public ViewDataBinding c(androidx.databinding.f fVar, View[] viewArr, int i7) {
        if (viewArr == null || viewArr.length == 0 || f5182a.get(i7) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    @Override // androidx.databinding.e
    public int d(String str) {
        Integer num;
        if (str == null || (num = a.f5183a.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }
}
