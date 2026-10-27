package com.android.systemui.qs;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.base.BaseFragment;
import com.android.systemui.qs.SceneModeIntroduceContract;
import com.android.systemui.statusbar.phone.BlurViewController;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.widget.RecyclerSpace;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SceneModeIntroduceFragment extends BaseFragment<SceneModeIntroducePresenter> implements View.OnClickListener, SceneModeIntroduceContract.View {
    public static final String TAG = "SceneModeIntroduceFragment";
    private SceneModeIntroduceAdapter adapter;
    private BlurViewController mBlurViewController;
    private StatusBar mStatusBarComponent;
    private View rl_recyclerview_container;
    private RelativeLayout rl_title;
    private RecyclerView rv_introduce;
    private TextView tv_back;

    @Override // com.android.systemui.base.BaseFragment
    protected int getLayoutId() {
        return R.layout.activity_scene_mode_introduce;
    }

    @Override // com.android.systemui.base.BaseFragment
    protected void initView(View view) {
        this.rv_introduce = (RecyclerView) view.findViewById(R.id.rv_introduce);
        this.rl_title = (RelativeLayout) view.findViewById(R.id.rl_title);
        this.tv_back = (TextView) view.findViewById(R.id.tv_back);
        this.rl_title.setOnClickListener(this);
        ImageView imageView = (ImageView) view.findViewById(R.id.img_drawer_mask);
        BlurViewController blurViewController = (BlurViewController) Dependency.get(BlurViewController.class);
        this.mBlurViewController = blurViewController;
        if (blurViewController.getBlurBitmap() != null) {
            imageView.setImageBitmap(this.mBlurViewController.getBlurBitmap());
        }
        View viewFindViewById = view.findViewById(R.id.rl_recyclerview_container);
        this.rl_recyclerview_container = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
    }

    @Override // com.android.systemui.base.BaseFragment
    protected void initData() {
        this.mStatusBarComponent = (StatusBar) SysUiServiceProvider.getComponent(getContext(), StatusBar.class);
        this.tv_back.setText(getString(R.string.function_introduce));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.rv_introduce.setLayoutManager(linearLayoutManager);
        this.rv_introduce.addItemDecoration(new RecyclerSpace(0, 0, 0, (int) getResources().getDimension(R.dimen.dp_24)));
        SceneModeIntroduceAdapter sceneModeIntroduceAdapter = new SceneModeIntroduceAdapter();
        this.adapter = sceneModeIntroduceAdapter;
        this.rv_introduce.setAdapter(sceneModeIntroduceAdapter);
        super.initData();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.rl_recyclerview_container) {
            this.mStatusBarComponent.closePanelsIfNeed();
        } else {
            if (id != R.id.rl_title) {
                return;
            }
            this.mStatusBarComponent.backStack(TAG);
        }
    }

    @Override // com.android.systemui.qs.SceneModeIntroduceContract.View
    public void refreshList(List<SceneModeIntroduceBean> list) {
        this.adapter.refreshList(list);
    }
}
