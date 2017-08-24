package com.lawyee.helpxml.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lawyee.helpxml.R;
import com.lawyee.helpxml.activity.fragment.HelpFragment;
import com.lawyee.helpxml.activity.fragment.MainFragment;
import com.lawyee.helpxml.activity.fragment.SettingFragment;
import com.lawyee.helpxml.activity.fragment.UpdateFragment;
import com.lawyee.helpxml.base.BaseActivity;
import com.lawyee.helpxml.base.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;

import static com.lawyee.helpxml.R.id.toolbar;


/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Title: MainActivity
 * @Package com.laywee.awyeehelper2.activity
 * @Description: 主界面
 * @author: czq
 * @date: 2017/3/17 10:40
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: ${year} www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar_title)
    TextView mToolbarTitle;
    @Bind(toolbar)
    Toolbar mToolbar;
    @Bind(R.id.frameLayout)
    FrameLayout mFrameLayout;
    @Bind(R.id.rb_home)
    RadioButton mRbHome;
    @Bind(R.id.rb_seting)
    RadioButton mRbSeting;
    @Bind(R.id.rb_help)
    RadioButton mRbHelp;
    @Bind(R.id.rb_update)
    RadioButton mRbUpdate;
    @Bind(R.id.rg_main)
    RadioGroup mRgMain;
    private int mPosition=0;//选中的fragment的位置
    private ArrayList<BaseFragment> mFragments;//所需要的fragment数组
    private BaseFragment mTempFragment;
    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar(mToolbar,mToolbarTitle,this.getResources().getString(R.string.app_name),0);
        initFragment();
        initListener();
    }

    private void initListener() {
        mRgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_home:
                        mPosition=0;
                        break;
                    case R.id.rb_seting:
                        mPosition=1;
                        break;
                    case R.id.rb_help:
                        mPosition=2;
                        break;
                    case R.id.rb_update:
                        mPosition=3;
                        break;
                    default:
                        mPosition=0;
                        break;
                }
                //根据位置去取不同的fragment
                BaseFragment baseFragment=getFragment(mPosition);
                switchFragment(mTempFragment,baseFragment);
            }
        });
        mRgMain.check(R.id.rb_home);
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new MainFragment());
        mFragments.add(new SettingFragment());
        mFragments.add(new HelpFragment());
        mFragments.add(new UpdateFragment());

    }





   private BaseFragment getFragment(int position){
        if(mFragments!=null&& mFragments.size()>0){
            BaseFragment baseFragment=mFragments.get(position);
            return baseFragment;
        }
        return null;

    }

    /**
     * 切换fragment
     * @param fromFragment
     * @param nextFragment
     */
    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (mTempFragment != nextFragment) {
            mTempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                }
                else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }
}
