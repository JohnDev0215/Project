package com.byd.music.activity.main;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.base.BaseViewModel;
import com.byd.music.common.SingleLiveEvent;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.binding.BindingCommand;
import com.byd.music.utils.binding.BindingConsumer;
import java.util.List;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/* JADX INFO: loaded from: classes5.dex */
public class MediaMainViewModel extends BaseViewModel {
    public ObservableInt checkedPosition;
    public ItemBinding<Integer> horizontalItemBinding;
    public ObservableBoolean isBluetoothPage;
    public BindingCommand<Integer> sourceClickCommand = new BindingCommand<>(new BindingConsumer<Integer>() { // from class: com.byd.music.activity.main.MediaMainViewModel.1
        @Override // com.byd.music.utils.binding.BindingConsumer
        public void call(Integer num) {
            MediaMainViewModel.this.sourceClickEvent.setValue(Integer.valueOf(MediaMainViewModel.this.getIndex(num)));
        }
    });
    public SingleLiveEvent<Integer> sourceClickEvent;
    public List<Integer> sourceImageList;
    public List<Integer> titleList;
    public ItemBinding<Integer> verticalItemBinding;

    @Override // com.byd.music.base.BaseViewModel
    protected void onCreate() {
        this.isBluetoothPage = new ObservableBoolean();
        this.checkedPosition = new ObservableInt(0);
        this.sourceClickEvent = new SingleLiveEvent<>();
        ItemBinding<Integer> itemBindingOf = ItemBinding.of(BR.titleId, R.layout.item_source_vertical);
        this.verticalItemBinding = itemBindingOf;
        itemBindingOf.bindExtra(BR.viewModel, this);
        ItemBinding<Integer> itemBindingOf2 = ItemBinding.of(BR.titleId, R.layout.item_source_horizontal);
        this.horizontalItemBinding = itemBindingOf2;
        itemBindingOf2.bindExtra(BR.viewModel, this);
    }

    public int getIndex(Integer num) {
        return this.titleList.indexOf(num);
    }

    @Override // com.byd.music.base.BaseViewModel
    protected void onDestroy() {
        LogUtil.d(this.TAG, "onDestroy with index = " + this.checkedPosition.get());
    }
}
