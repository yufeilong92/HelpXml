package com.lawyee.helpxml.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lawyee.helpxml.R;
import com.lawyee.helpxml.activity.fragment.ApplyFragment;
import com.lawyee.helpxml.activity.fragment.LitigationFragment;
import com.lawyee.helpxml.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: LitigationActivity.java
 * @Package com.laywee.lawyeehelper2.activity
 * @Description: 诉讼费计算工具类
 * @author: YFL
 * @date: 2017/3/22 14:38
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017/3/22 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */


public class LitigationActivity extends BaseActivity {
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rdoBtn_Case)
    RadioButton rdoBtnCase;
    @Bind(R.id.rdoBtn_Apply)
    RadioButton rdoBtnApply;
    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    private LitigationFragment fragment;
    private ApplyFragment applyFragment;
    private FragmentManager fm;
    Fragment mTempFragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String mIntentValue = getIntent().getStringExtra(TITLE_NAME);
        initToolbar(toolbar, toolbarTitle, mIntentValue, 1);
        rdoBtnCase.setChecked(true);
        addFragment();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_litigation;
    }

    private void addFragment() {
        fragment = new LitigationFragment();
        applyFragment = new ApplyFragment();
        mTempFragment = fragment;
        fm = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fm.beginTransaction();
        beginTransaction.add(R.id.frameLayout,fragment ).commit();

    }

    @OnClick({R.id.toolbar_title, R.id.rdoBtn_Case, R.id.rdoBtn_Apply})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_title:
                break;
            case R.id.rdoBtn_Case:
                switchFragment(applyFragment, fragment);
                break;
            case R.id.rdoBtn_Apply:
                switchFragment(fragment, applyFragment);
                break;
        }
    }

    /**
     * 使用hide和show方法切换Fragment
     *
     * @param fragmentfrom 隐藏fragment
     * @param fragmentto   需要切换的fragment
     */
    private void switchFragment(Fragment fragmentfrom, Fragment fragmentto) {
        if (fragmentfrom != null && fragmentto != null) {
            if (fragmentto != mTempFragment) {
                FragmentTransaction bt = fm.beginTransaction();
                if (!fragmentto.isAdded()) {
                    bt.hide(fragmentfrom)
                            .add(R.id.frameLayout, fragmentto).commit();
                } else {
                    bt.hide(fragmentfrom)
                            .show(fragmentto).commit();
                }
                mTempFragment = fragmentto;
            }
        }
    }
}
