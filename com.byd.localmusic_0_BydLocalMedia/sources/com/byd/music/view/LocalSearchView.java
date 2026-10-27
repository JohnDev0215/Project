package com.byd.music.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.byd.music.R;

/* JADX INFO: loaded from: classes5.dex */
public class LocalSearchView extends ConstraintLayout implements TextWatcher {
    private EditText editText;
    private ImageView ivDelete;
    private TextChangedListener textChangedListener;

    public interface TextChangedListener {
        void afterTextChanged(Editable editable);

        void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3);

        void onTextChanged(CharSequence charSequence, int i, int i2, int i3);
    }

    public LocalSearchView(Context context) {
        this(context, null);
    }

    public LocalSearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LocalSearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView();
    }

    private void initView() {
        View viewInflate = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_local_search, (ViewGroup) this, true);
        this.editText = (EditText) viewInflate.findViewById(R.id.edittext);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.iv_delete);
        this.ivDelete = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.view.-$$Lambda$LocalSearchView$2Q_DChKxVw5UqHHpzKM5EIv2xwQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$0$LocalSearchView(view);
            }
        });
        this.editText.addTextChangedListener(this);
    }

    public /* synthetic */ void lambda$initView$0$LocalSearchView(View view) {
        this.editText.setText("");
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        TextChangedListener textChangedListener = this.textChangedListener;
        if (textChangedListener != null) {
            textChangedListener.beforeTextChanged(charSequence, i, i2, i3);
        }
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence.length() > 0) {
            this.ivDelete.setVisibility(0);
        } else {
            this.ivDelete.setVisibility(8);
        }
        TextChangedListener textChangedListener = this.textChangedListener;
        if (textChangedListener != null) {
            textChangedListener.onTextChanged(charSequence, i, i2, i3);
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        TextChangedListener textChangedListener = this.textChangedListener;
        if (textChangedListener != null) {
            textChangedListener.afterTextChanged(editable);
        }
    }

    public EditText getEditText() {
        return this.editText;
    }

    public void addTextChangedListener(TextChangedListener textChangedListener) {
        this.textChangedListener = textChangedListener;
    }
}
