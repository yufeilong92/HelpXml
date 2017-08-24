package com.lawyee.helpxml.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Title: BaseFragment
 * @Package com.laywee.lawyeehelper2.base
 * @Description: fragment的基类
 * @author: czq
 * @date: 2017/3/17 14:51
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: ${year} www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

public abstract  class BaseFragment extends Fragment{
    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=initView();
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 有子类实现，实现特有效果
     * @return
     */
    public abstract View initView();

    /**
     * 初始化数据
     */

    protected  void initData(){

    }

    /**
     * @Description: 调转到activity
     * @param: classs
     * @return:
     * @throws
     */
    public void  FragToActivity(Class classs){
        Intent intent =new Intent(mContext,classs);
        mContext.startActivity(intent);
    }
}
