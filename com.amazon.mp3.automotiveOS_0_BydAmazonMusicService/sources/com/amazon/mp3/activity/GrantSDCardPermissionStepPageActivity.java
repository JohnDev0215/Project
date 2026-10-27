package com.amazon.mp3.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.provider.source.local.SDCardUtil;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes3.dex */
public class GrantSDCardPermissionStepPageActivity extends AppCompatActivity {
    private static final int SDCARD_WRITE_REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    private ViewPager mHintViewPager;
    private Button nextButton;
    private Button skipButton;

    public void performFolderChoose() {
        startActivityForResult(new Intent("android.intent.action.OPEN_DOCUMENT_TREE"), SDCARD_WRITE_REQUEST_CODE);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        Uri data;
        if (requestCode == SDCARD_WRITE_REQUEST_CODE && resultCode == -1 && (data = resultData.getData()) != null && SDCardUtil.isSDCardRootUri(data)) {
            getContentResolver().takePersistableUriPermission(data, 3);
        }
        finish();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grant_sdcard_permission_step_page);
        this.skipButton = (Button) findViewById(R.id.skip_button);
        this.nextButton = (Button) findViewById(R.id.next_button);
        this.mHintViewPager = (ViewPager) findViewById(R.id.hint_view_pager);
        ((TabLayout) findViewById(R.id.tab_dots)).setupWithViewPager(this.mHintViewPager, true);
        HintPagerAdapter hintPagerAdapter = new HintPagerAdapter(this);
        this.mHintViewPager.setAdapter(hintPagerAdapter);
        this.mHintViewPager.setCurrentItem(0);
        this.mHintViewPager.setOffscreenPageLimit(hintPagerAdapter.getCount() - 1);
        this.skipButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.activity.GrantSDCardPermissionStepPageActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$0(view);
            }
        });
        this.nextButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.activity.GrantSDCardPermissionStepPageActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$1(view);
            }
        });
        this.mHintViewPager.addOnPageChangeListener(new AnonymousClass1(hintPagerAdapter));
        this.mHintViewPager.setPageTransformer(false, new HintViewPagerTransformer(this, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        performFolderChoose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        this.mHintViewPager.setCurrentItem(1, true);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.activity.GrantSDCardPermissionStepPageActivity$1, reason: invalid class name */
    class AnonymousClass1 implements ViewPager.OnPageChangeListener {
        final /* synthetic */ HintPagerAdapter val$mHintPagerAdapter;

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int state) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        AnonymousClass1(final HintPagerAdapter val$mHintPagerAdapter) {
            this.val$mHintPagerAdapter = val$mHintPagerAdapter;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(final int position) {
            if (position == this.val$mHintPagerAdapter.getCount() - 1) {
                GrantSDCardPermissionStepPageActivity.this.nextButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.activity.GrantSDCardPermissionStepPageActivity$1$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$onPageSelected$0(view);
                    }
                });
            } else {
                GrantSDCardPermissionStepPageActivity.this.nextButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.activity.GrantSDCardPermissionStepPageActivity$1$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$onPageSelected$1(position, view);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onPageSelected$0(View view) {
            GrantSDCardPermissionStepPageActivity.this.performFolderChoose();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onPageSelected$1(int i, View view) {
            GrantSDCardPermissionStepPageActivity.this.mHintViewPager.setCurrentItem(i + 1, true);
        }
    }

    class HintPagerAdapter extends PagerAdapter {
        private Context mContext;
        private LayoutInflater mLayoutInflater;
        private int[] mImageResources = {R.drawable.hint_step1, R.drawable.hint_step2, R.drawable.hint_step3, R.drawable.hint_step4};
        private int[] mTextResources = {R.string.sdcard_storage_permission_activity_hint_1, R.string.sdcard_storage_permission_activity_hint_2, R.string.sdcard_storage_permission_activity_hint_3, R.string.sdcard_storage_permission_activity_hint_4};

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        HintPagerAdapter(Context context) {
            this.mContext = context;
            this.mLayoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup container, int position) {
            View viewInflate = this.mLayoutInflater.inflate(R.layout.pager_item_grant_sdcard_permission, container, false);
            ImageView imageView = (ImageView) viewInflate.findViewById(R.id.hint_step_page_image_view);
            TextView textView = (TextView) viewInflate.findViewById(R.id.hint_step_page_text_view);
            imageView.setImageResource(this.mImageResources[position]);
            textView.setText(this.mTextResources[position]);
            container.addView(viewInflate);
            return viewInflate;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.mImageResources.length;
        }
    }

    private class HintViewPagerTransformer implements ViewPager.PageTransformer {
        private HintViewPagerTransformer() {
        }

        /* synthetic */ HintViewPagerTransformer(GrantSDCardPermissionStepPageActivity grantSDCardPermissionStepPageActivity, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // androidx.viewpager.widget.ViewPager.PageTransformer
        public void transformPage(View view, float position) {
            TextView textView = (TextView) view.findViewById(R.id.hint_step_page_text_view);
            int width = view.getWidth();
            if (position <= -1.0f || position >= 1.0f) {
                textView.setTranslationX(width * position);
                textView.setAlpha(0.0f);
            } else if (position == 0.0f) {
                textView.setTranslationX(width * position);
                textView.setAlpha(1.0f);
            } else {
                textView.setTranslationX(width * (-position));
                textView.setAlpha(1.0f - Math.abs(position));
            }
        }
    }
}
