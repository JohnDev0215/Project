package com.byd.trafficmonitor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import c.a.a.h.a.a;
import com.byd.trafficmonitor.widget.CustomToolBar;
import com.bydux.api.R;
import com.bydux.api.common.views.window.BydActivity;

/* JADX INFO: loaded from: classes.dex */
public class ProtocolActivity extends BydActivity implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public CustomToolBar f4398b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f4399c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Button f4400d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f4401e = "从这两个案例可以看出，互联网参与公益，不仅有趣，也非常体现各自的平台特色。网易有云课堂产品，所以推出“一块屏”项目顺理成章；腾讯的娱乐和社交基因，也是起推出“小花守护小花”、集小红花活动的原因。 \n\n阿里有我们之前提到的，利用支付系统搭建的蚂蚁森林，同时在电商系统下的公益宝贝等形式。《2022阿里巴巴环境、社会和治理报告》显示，2022财年，淘宝天猫上超过220万商家和5亿消费者一起通过“公益宝贝”项目参与公益捐赠。 \n\n京东做公益的抓手在医疗健康上。2020年5月，京东公益联合中国社会扶贫网、京东健康发起“健康中国·医药补助工程”，面向全国建档立卡贫困户提供购药补助。今年6月，京东公益、京东健康联合鱼跃医疗、北京市众安公益基金会，为全国多所高校募集部署AED（自动体外除颤器）。项目详情中介绍，京东健康捐赠了100台AED。 \n\n拼多多因为关注下沉市场，其公益的方向主要在助农上。拼多多APP中有爱心助农的专属入口，受助对象可以通过直播讲解产品，促进购买。今年8月，拼多多还宣布将15亿美元的未来利润投入农业公益项目。\n首先，内容成本\n\n一些公众和公益机构想当然认为通过互联网做公益是不需要成本的，或筹款成本很低，但实际上，搭建一个平台需要很多的人力，以及维护服务器的投入，这些都是巨大的成本。而在短视频时代，内容的制作也需要付出更多的时间、精力和资金。 \n\n在各大互联网公益平台上，那些文案、内容最打动人心的项目，才有可能获得更多的捐助，这些需要公益机构具备一定的内容制作能力，尤其是在短视频平台上，内容的作用就更加重要。 \n\n譬如在最近的DOU爱公益日的发布的规则中，就明确规定了参与活动的公益项目应满足的八项规则，“鼓励解决具有公共性、非特定对象的社会问题，以及可持续性的解决方案”，“暂不支持医疗救助领域以及应急救助领域等临时性、短期项目”。要提交的除了公益组织的资质、项目的执行时间、额度等要求外，公益机构还要在活动期内发布至少一条不少于15秒的有效筹款视频。 \n\n第二，马太效应\n\n这其实跟第一条有关，在互联网时代，越是能获得头部平台合作的项目，越是内容物料制作精美的项目，越是能获得更多的关注和捐助，没有被互联网公司看到的项目，没有通过传播出圈的项目，就很难获得捐助。 \n\n如果不去提升中腰部公益组织利用互联网募款的能力，在经济形势不看好的年份，则很容易筹款不足，从而导致项目无法执行。 \n\n数据支持了这一判断，2021年11月，民政部官网公布了《民政部关于指定第三批慈善组织互联网募捐信息平台的公告》，字节跳动、小米、亲青、B站、平安等10家平台入选。加上已有的腾讯公益，淘宝公益，美团公益，新浪公益，滴滴公益等平台，民政部指定的慈善组织互联网募捐信息平台已有30余家。 \n\n另一方面，中国发展简报2021年对399个一线机构的问卷显示：与2020年同期相比，28.8%的一线公益机构的实际资金收入减少了50%以上，其中有11.3%的公益组织甚至减少了70%或更多。有15-20% 的公益机构受到很大影响，将难以坚持下去；有15-20% 的公益机构甚至可能已经关闭。";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a f4402f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public View f4403g;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void e(View view) {
        finish();
    }

    public final void a() {
        a aVar = new a(this);
        this.f4402f = aVar;
        aVar.setTitle(R.string.activation_title);
        this.f4402f.j(0);
        this.f4402f.k(this.f4403g);
        this.f4402f.h();
    }

    public final void b() {
        this.f4403g = View.inflate(this, R.layout.dialog_flow_activation, null);
    }

    public final void c() {
        setSupportActionBar(this.f4398b);
        f();
        this.f4398b.setNavigationOnClickListener(new View.OnClickListener() { // from class: c.a.d.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3559b.e(view);
            }
        });
        this.f4399c.setText("从这两个案例可以看出，互联网参与公益，不仅有趣，也非常体现各自的平台特色。网易有云课堂产品，所以推出“一块屏”项目顺理成章；腾讯的娱乐和社交基因，也是起推出“小花守护小花”、集小红花活动的原因。 \n\n阿里有我们之前提到的，利用支付系统搭建的蚂蚁森林，同时在电商系统下的公益宝贝等形式。《2022阿里巴巴环境、社会和治理报告》显示，2022财年，淘宝天猫上超过220万商家和5亿消费者一起通过“公益宝贝”项目参与公益捐赠。 \n\n京东做公益的抓手在医疗健康上。2020年5月，京东公益联合中国社会扶贫网、京东健康发起“健康中国·医药补助工程”，面向全国建档立卡贫困户提供购药补助。今年6月，京东公益、京东健康联合鱼跃医疗、北京市众安公益基金会，为全国多所高校募集部署AED（自动体外除颤器）。项目详情中介绍，京东健康捐赠了100台AED。 \n\n拼多多因为关注下沉市场，其公益的方向主要在助农上。拼多多APP中有爱心助农的专属入口，受助对象可以通过直播讲解产品，促进购买。今年8月，拼多多还宣布将15亿美元的未来利润投入农业公益项目。\n首先，内容成本\n\n一些公众和公益机构想当然认为通过互联网做公益是不需要成本的，或筹款成本很低，但实际上，搭建一个平台需要很多的人力，以及维护服务器的投入，这些都是巨大的成本。而在短视频时代，内容的制作也需要付出更多的时间、精力和资金。 \n\n在各大互联网公益平台上，那些文案、内容最打动人心的项目，才有可能获得更多的捐助，这些需要公益机构具备一定的内容制作能力，尤其是在短视频平台上，内容的作用就更加重要。 \n\n譬如在最近的DOU爱公益日的发布的规则中，就明确规定了参与活动的公益项目应满足的八项规则，“鼓励解决具有公共性、非特定对象的社会问题，以及可持续性的解决方案”，“暂不支持医疗救助领域以及应急救助领域等临时性、短期项目”。要提交的除了公益组织的资质、项目的执行时间、额度等要求外，公益机构还要在活动期内发布至少一条不少于15秒的有效筹款视频。 \n\n第二，马太效应\n\n这其实跟第一条有关，在互联网时代，越是能获得头部平台合作的项目，越是内容物料制作精美的项目，越是能获得更多的关注和捐助，没有被互联网公司看到的项目，没有通过传播出圈的项目，就很难获得捐助。 \n\n如果不去提升中腰部公益组织利用互联网募款的能力，在经济形势不看好的年份，则很容易筹款不足，从而导致项目无法执行。 \n\n数据支持了这一判断，2021年11月，民政部官网公布了《民政部关于指定第三批慈善组织互联网募捐信息平台的公告》，字节跳动、小米、亲青、B站、平安等10家平台入选。加上已有的腾讯公益，淘宝公益，美团公益，新浪公益，滴滴公益等平台，民政部指定的慈善组织互联网募捐信息平台已有30余家。 \n\n另一方面，中国发展简报2021年对399个一线机构的问卷显示：与2020年同期相比，28.8%的一线公益机构的实际资金收入减少了50%以上，其中有11.3%的公益组织甚至减少了70%或更多。有15-20% 的公益机构受到很大影响，将难以坚持下去；有15-20% 的公益机构甚至可能已经关闭。");
    }

    public final void f() {
        for (int i2 = 0; i2 < this.f4398b.getChildCount(); i2++) {
            View childAt = this.f4398b.getChildAt(i2);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                textView.setGravity(17);
                textView.getLayoutParams().width = -1;
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.btn_next) {
            return;
        }
        if (this.f4403g == null) {
            b();
        }
        if (this.f4402f == null) {
            a();
        }
        this.f4402f.show();
        this.f4402f.l((int) getResources().getDimension(R.dimen.dp_560), (int) getResources().getDimension(R.dimen.dp_544));
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_protocol);
        this.f4398b = (CustomToolBar) findViewById(R.id.tb_pro);
        this.f4399c = (TextView) findViewById(R.id.tv_protocol);
        Button button = (Button) findViewById(R.id.btn_next);
        this.f4400d = button;
        button.setOnClickListener(this);
        c();
    }
}
