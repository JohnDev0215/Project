package com.byd.music.utils.binding;

import android.view.View;

/* JADX INFO: loaded from: classes5.dex */
public class BindingCommand<T> {
    BindingAction action;
    BindingConsumerWithView<T> bindingConsumerWithView;
    BindingConsumer<T> consumer;

    public BindingCommand(BindingConsumer<T> bindingConsumer) {
        this.consumer = bindingConsumer;
    }

    public BindingCommand(BindingAction bindingAction) {
        this.action = bindingAction;
    }

    public BindingCommand(BindingConsumerWithView<T> bindingConsumerWithView) {
        this.bindingConsumerWithView = bindingConsumerWithView;
    }

    public void execute() {
        BindingAction bindingAction = this.action;
        if (bindingAction == null) {
            return;
        }
        bindingAction.call();
    }

    public void execute(T t) {
        BindingConsumer<T> bindingConsumer = this.consumer;
        if (bindingConsumer == null) {
            return;
        }
        bindingConsumer.call(t);
    }

    public void execute(View view, T t) {
        BindingConsumerWithView<T> bindingConsumerWithView = this.bindingConsumerWithView;
        if (bindingConsumerWithView == null) {
            return;
        }
        bindingConsumerWithView.call(view, t);
    }
}
