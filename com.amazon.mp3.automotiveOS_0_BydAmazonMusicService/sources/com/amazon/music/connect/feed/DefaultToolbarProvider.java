package com.amazon.music.connect.feed;

import androidx.appcompat.widget.Toolbar;
import com.amazon.mp3.reactnativemodule.provider.ToolbarProvider;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultToolbarProvider implements ToolbarProvider {
    private final Toolbar toolbar;

    public DefaultToolbarProvider(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    @Override // com.amazon.mp3.reactnativemodule.provider.ToolbarProvider
    public Toolbar getToolbar() {
        return this.toolbar;
    }
}
