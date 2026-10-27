package com.byd.carsettings;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.motion.widget.Key;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.byd.carsettings.databinding.DialogAdasCommonBindingImpl;
import com.byd.carsettings.databinding.FragmentActiveSafeBindingImpl;
import com.byd.carsettings.databinding.FragmentActiveSafeBindingLandImpl;
import com.byd.carsettings.databinding.FragmentActiveSafeBindingLdrtlLandImpl;
import com.byd.carsettings.databinding.FragmentActiveSafeBindingLdrtlPortImpl;
import com.byd.carsettings.databinding.FragmentActiveSafeBindingRrRKPLandImpl;
import com.byd.carsettings.databinding.FragmentActiveSafeBindingRrRKPPortImpl;
import com.byd.carsettings.databinding.FragmentDipilotBindingImpl;
import com.byd.carsettings.databinding.FragmentDipilotBindingLandImpl;
import com.byd.carsettings.databinding.FragmentDriverAssistBindingImpl;
import com.byd.carsettings.databinding.FragmentDriverAssistBindingLandImpl;
import com.byd.carsettings.databinding.FragmentParkAssistBindingImpl;
import com.byd.carsettings.databinding.FragmentParkAssistBindingLandImpl;
import com.byd.carsettings.databinding.ItemDipilotAssistBindingImpl;
import com.byd.carsettings.databinding.ItemDipilotAssistBindingLandImpl;
import com.byd.carsettings.databinding.ItemDipilotAssistBindingLdrtlLandImpl;
import com.byd.carsettings.databinding.ItemDipilotAssistBindingLdrtlPortImpl;
import com.byd.carsettings.databinding.PagerDitrainerBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DataBinderMapperImpl extends DataBinderMapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SparseIntArray f2581a;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final SparseArray<String> f2582a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(13);
            f2582a = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, Key.ALPHA);
            sparseArray.put(2, "assistState");
            sparseArray.put(3, "enable");
            sparseArray.put(4, "m");
            sparseArray.put(5, "model");
            sparseArray.put(6, "onCheckedChangeListener");
            sparseArray.put(7, "onClickListener");
            sparseArray.put(8, "onItemCheck");
            sparseArray.put(9, "pos");
            sparseArray.put(10, "tag");
            sparseArray.put(11, "touch");
            sparseArray.put(12, "viewModel");
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final HashMap<String, Integer> f2583a;

        static {
            HashMap<String, Integer> map = new HashMap<>(18);
            f2583a = map;
            map.put("layout/dialog_adas_common_0", Integer.valueOf(R.layout.dialog_adas_common));
            Integer numValueOf = Integer.valueOf(R.layout.fragment_active_safe);
            map.put("layout-rr-rKP-port/fragment_active_safe_0", numValueOf);
            map.put("layout-rr-rKP-land/fragment_active_safe_0", numValueOf);
            map.put("layout-ldrtl-land/fragment_active_safe_0", numValueOf);
            map.put("layout/fragment_active_safe_0", numValueOf);
            map.put("layout-land/fragment_active_safe_0", numValueOf);
            map.put("layout-ldrtl-port/fragment_active_safe_0", numValueOf);
            Integer numValueOf2 = Integer.valueOf(R.layout.fragment_dipilot);
            map.put("layout/fragment_dipilot_0", numValueOf2);
            map.put("layout-land/fragment_dipilot_0", numValueOf2);
            Integer numValueOf3 = Integer.valueOf(R.layout.fragment_driver_assist);
            map.put("layout-land/fragment_driver_assist_0", numValueOf3);
            map.put("layout/fragment_driver_assist_0", numValueOf3);
            Integer numValueOf4 = Integer.valueOf(R.layout.fragment_park_assist);
            map.put("layout-land/fragment_park_assist_0", numValueOf4);
            map.put("layout/fragment_park_assist_0", numValueOf4);
            Integer numValueOf5 = Integer.valueOf(R.layout.item_dipilot_assist);
            map.put("layout-ldrtl-port/item_dipilot_assist_0", numValueOf5);
            map.put("layout/item_dipilot_assist_0", numValueOf5);
            map.put("layout-ldrtl-land/item_dipilot_assist_0", numValueOf5);
            map.put("layout-land/item_dipilot_assist_0", numValueOf5);
            map.put("layout/pager_ditrainer_0", Integer.valueOf(R.layout.pager_ditrainer));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(7);
        f2581a = sparseIntArray;
        sparseIntArray.put(R.layout.dialog_adas_common, 1);
        sparseIntArray.put(R.layout.fragment_active_safe, 2);
        sparseIntArray.put(R.layout.fragment_dipilot, 3);
        sparseIntArray.put(R.layout.fragment_driver_assist, 4);
        sparseIntArray.put(R.layout.fragment_park_assist, 5);
        sparseIntArray.put(R.layout.item_dipilot_assist, 6);
        sparseIntArray.put(R.layout.pager_ditrainer, 7);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.byd.base.DataBinderMapperImpl());
        arrayList.add(new com.byd.car.theme.vehiclelibrary.DataBinderMapperImpl());
        arrayList.add(new com.byd.digitalkey.DataBinderMapperImpl());
        arrayList.add(new com.byd.dipilot.bpresenter.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return a.f2582a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = f2581a.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag == null) {
            throw new RuntimeException("view must have a tag");
        }
        switch (i2) {
            case 1:
                if ("layout/dialog_adas_common_0".equals(tag)) {
                    return new DialogAdasCommonBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_adas_common is invalid. Received: " + tag);
            case 2:
                if ("layout-rr-rKP-port/fragment_active_safe_0".equals(tag)) {
                    return new FragmentActiveSafeBindingRrRKPPortImpl(dataBindingComponent, view);
                }
                if ("layout-rr-rKP-land/fragment_active_safe_0".equals(tag)) {
                    return new FragmentActiveSafeBindingRrRKPLandImpl(dataBindingComponent, view);
                }
                if ("layout-ldrtl-land/fragment_active_safe_0".equals(tag)) {
                    return new FragmentActiveSafeBindingLdrtlLandImpl(dataBindingComponent, view);
                }
                if ("layout/fragment_active_safe_0".equals(tag)) {
                    return new FragmentActiveSafeBindingImpl(dataBindingComponent, view);
                }
                if ("layout-land/fragment_active_safe_0".equals(tag)) {
                    return new FragmentActiveSafeBindingLandImpl(dataBindingComponent, view);
                }
                if ("layout-ldrtl-port/fragment_active_safe_0".equals(tag)) {
                    return new FragmentActiveSafeBindingLdrtlPortImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_active_safe is invalid. Received: " + tag);
            case 3:
                if ("layout/fragment_dipilot_0".equals(tag)) {
                    return new FragmentDipilotBindingImpl(dataBindingComponent, view);
                }
                if ("layout-land/fragment_dipilot_0".equals(tag)) {
                    return new FragmentDipilotBindingLandImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_dipilot is invalid. Received: " + tag);
            case 4:
                if ("layout-land/fragment_driver_assist_0".equals(tag)) {
                    return new FragmentDriverAssistBindingLandImpl(dataBindingComponent, view);
                }
                if ("layout/fragment_driver_assist_0".equals(tag)) {
                    return new FragmentDriverAssistBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_driver_assist is invalid. Received: " + tag);
            case 5:
                if ("layout-land/fragment_park_assist_0".equals(tag)) {
                    return new FragmentParkAssistBindingLandImpl(dataBindingComponent, view);
                }
                if ("layout/fragment_park_assist_0".equals(tag)) {
                    return new FragmentParkAssistBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_park_assist is invalid. Received: " + tag);
            case 6:
                if ("layout-ldrtl-port/item_dipilot_assist_0".equals(tag)) {
                    return new ItemDipilotAssistBindingLdrtlPortImpl(dataBindingComponent, view);
                }
                if ("layout/item_dipilot_assist_0".equals(tag)) {
                    return new ItemDipilotAssistBindingImpl(dataBindingComponent, view);
                }
                if ("layout-ldrtl-land/item_dipilot_assist_0".equals(tag)) {
                    return new ItemDipilotAssistBindingLdrtlLandImpl(dataBindingComponent, view);
                }
                if ("layout-land/item_dipilot_assist_0".equals(tag)) {
                    return new ItemDipilotAssistBindingLandImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_dipilot_assist is invalid. Received: " + tag);
            case 7:
                if ("layout/pager_ditrainer_0".equals(tag)) {
                    return new PagerDitrainerBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for pager_ditrainer is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = b.f2583a.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || f2581a.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
}
